package org.quarkchain.web3j.sample;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.quarkchain.web3j.crypto.ECKeyPair;
import org.quarkchain.web3j.crypto.TransactionEncoder;
import org.quarkchain.web3j.protocol.HttpService;
import org.quarkchain.web3j.protocol.Web3j;
import org.quarkchain.web3j.protocol.core.request.EvmTransaction;
import org.quarkchain.web3j.protocol.core.response.GetTransactionCount;
import org.quarkchain.web3j.protocol.core.response.GetTransactionReceipt.TransactionReceipt;
import org.quarkchain.web3j.protocol.core.response.NetworkInfo;
import org.quarkchain.web3j.protocol.core.response.NetworkInfo.Info;
import org.quarkchain.web3j.protocol.core.response.SendTransaction;
import org.quarkchain.web3j.utils.Convert;
import org.quarkchain.web3j.utils.Numeric;

public class Batch {
	// 32 byte hex value
	public static final String PRIVATE_KEY = "ca0143c9aa51c3013f08e83f3b6368a4f3ba5b52c4841c6e0c22c300f7ee6827";
	// 64 byte hex value
	public static final String PUBLIC_KEY = "0x9f0d6b3a0775412ca4c9b880c8cef9132b23198b5ef443685a54d36c3fa23a8ca127b7f0764b2e88849de947ca5f44a309617f86fc73a1559e276d437c7453ff";

	public static final String FROM_ADDRESS = "0xb067ac9ebeeecb10bbcd1088317959d58d1e38f6";
	// 20 byte hex value
	public static final String TO_ADDRESS = "0xfee3d08216f0bfe96a5c0f5df9ec9b2577ad64f0";

	public static final ECKeyPair KEY_PAIR = new ECKeyPair(Numeric.toBigInt(PRIVATE_KEY), Numeric.toBigInt(PUBLIC_KEY));
	public static final String DEFAULT_TOKEN_ID = "0x8bb0";
	public static final String DEFAULT_GAS = Numeric.toHexStringWithPrefix(BigInteger.valueOf(21000));
	public static final BigInteger GAS_PRICE = BigInteger.valueOf(10_000_000_000L);
	public static final BigInteger GAS_LIMIT = BigInteger.valueOf(2_000_000);

	public long txsPerShard;
	private String networkId;

	public Batch(String networkId, long txsPerShard) {
		this.networkId = networkId;
		this.txsPerShard = txsPerShard;
	}

	private TransactionReceipt transferQKC(Web3j web3j, String fullShardKey, long non) throws Exception {
		BigInteger nonce = BigInteger.valueOf(non);
		BigInteger value = Convert.toWei("0.01", Convert.Unit.ETHER).toBigInteger();
		EvmTransaction evmTransaction = EvmTransaction.createQKCThansferTransaction(nonce, GAS_PRICE, GAS_LIMIT,
				TO_ADDRESS, value, networkId, fullShardKey, fullShardKey, DEFAULT_TOKEN_ID, DEFAULT_TOKEN_ID);

		evmTransaction.sign(KEY_PAIR);
		byte[] signedMessage = TransactionEncoder.encode(evmTransaction);
		String hexValue = Numeric.toHexString(signedMessage);
		SendTransaction ethSendTransaction = web3j.sendRawTransaction(hexValue).send();
		if (ethSendTransaction.getError() != null) {
			throw new Exception(ethSendTransaction.getError().getMessage());
		}
//		String transactionId = ethSendTransaction.getTransactionID();
//		if (Numeric.toBigInt(transactionId).equals(BigInteger.ZERO)) {
//			throw new Exception("transfer failed: probably insufficient balance");
//		}
//		TransactionHelper helper = new TransactionHelper(web3j);
//		GetTransactionReceipt.TransactionReceipt transactionReceipt = helper.waitForTransactionReceipt(transactionId);
//		System.out.println("transactionReceipt="+transactionReceipt.getStatus());
		return null;
	}

	public static void main(String[] args) throws Exception {
		String[] url = new String[] { "http://localhost:38391", "http://localhost:39391" };

		HashMap<String, Long> nonces = new HashMap<String, Long>();
		Web3j[] web3j = new Web3j[url.length];
		for (int i = 0; i < url.length; i++) {
			System.out.println("Connecting to Quarkchain: " + url[i]);
			Web3j web3 = Web3j.build(new HttpService(url[i]));
			System.out.println("Successfuly connected to Quarkchain");
			web3j[i] = web3;
		}
		NetworkInfo network = web3j[0].networkInfo().send();
		Info networkInfo = network.getResult();
		System.out.println("networkInfo=" + networkInfo);
		String networkId = networkInfo.getNetworkId();
		Batch b = new Batch(networkId, 1000000);
		List<String> fullShardKeyArray = new ArrayList<String>();
		List<String> shardSizes = networkInfo.getShardSizes();
		long chainSize = Numeric.toBigInt(networkInfo.getChainSize()).longValue();
		for (int i = 0; i < chainSize; i++) {
			long shardSize = Numeric.toBigInt(shardSizes.get(i)).longValue();
			System.out.println("shardSize=" + shardSize);
			for (int j = 0; j < shardSize; j++) {
				String fullShardKey = Numeric
						.toHexStringNoPrefix(Numeric.toBytesPadded(BigInteger.valueOf(i << 16 | j), 4));
				System.out.println("FullShardKey=" + fullShardKey);
				fullShardKeyArray.add(fullShardKey);
			}
		}
		String[] fullShardKeys = fullShardKeyArray.toArray(new String[fullShardKeyArray.size()]);
		ExecutorService taskList = Executors.newFixedThreadPool(fullShardKeys.length);
		for (int i = 0; i < fullShardKeys.length; i++) {
			String fullShardKey = fullShardKeys[i];
			Web3j w3j = web3j[i % url.length];
			GetTransactionCount getTransactionCount = w3j.getTransactionCount(FROM_ADDRESS + fullShardKey).send();
			nonces.put(fullShardKey, getTransactionCount.getTransactionCount().longValue());
			taskList.execute(b.new InnerClass(w3j, nonces.get(fullShardKey), fullShardKey));
		}
		taskList.shutdown();
	}

	public class InnerClass implements Runnable {
		private Web3j web3j;
		private long nonce;
		private String fullShardKey;

		InnerClass(Web3j web3j, long nonce, String fullShardKey) {
			this.web3j = web3j;
			this.nonce = nonce;
			this.fullShardKey = fullShardKey;
		}

		@Override
		public void run() {
			for (long i = 0; i < txsPerShard; i++) {
				try {
					transferQKC(web3j, fullShardKey, nonce + i);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
				if (i % 100 == 0) {
					System.out.println(fullShardKey + " => " + i);
				}
			}
		}
	}

}
