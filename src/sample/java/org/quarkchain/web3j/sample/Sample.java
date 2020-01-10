package org.quarkchain.web3j.sample;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.quarkchain.web3j.crypto.ECKeyPair;
import org.quarkchain.web3j.crypto.TransactionEncoder;
import org.quarkchain.web3j.protocol.HttpService;
import org.quarkchain.web3j.protocol.Web3j;
import org.quarkchain.web3j.protocol.core.DefaultBlockParameterName;
import org.quarkchain.web3j.protocol.core.Response.Error;
import org.quarkchain.web3j.protocol.core.request.EthFilter;
import org.quarkchain.web3j.protocol.core.request.EvmTransaction;
import org.quarkchain.web3j.protocol.core.request.TransactionReq;
import org.quarkchain.web3j.protocol.core.response.Call;
import org.quarkchain.web3j.protocol.core.response.EthLog;
import org.quarkchain.web3j.protocol.core.response.EthLog.LogResult;
import org.quarkchain.web3j.protocol.core.response.GetBalances;
import org.quarkchain.web3j.protocol.core.response.GetBalances.Balance;
import org.quarkchain.web3j.protocol.core.response.GetMinorBlock;
import org.quarkchain.web3j.protocol.core.response.GetRootBlock;
import org.quarkchain.web3j.protocol.core.response.GetTransactionCount;
import org.quarkchain.web3j.protocol.core.response.GetTransactionReceipt;
import org.quarkchain.web3j.protocol.core.response.GetTransactionReceipt.TransactionReceipt;
import org.quarkchain.web3j.protocol.core.response.Log;
import org.quarkchain.web3j.protocol.core.response.NetworkInfo;
import org.quarkchain.web3j.protocol.core.response.NetworkInfo.Info;
import org.quarkchain.web3j.protocol.core.response.SendTransaction;
import org.quarkchain.web3j.utils.Convert;
import org.quarkchain.web3j.utils.Numeric;

public class Sample {
	public static final String URL = "http://localhost:38391";
//	public static final String URL = "http://jrpc.devnet.quarkchain.io:38391";

	// 32 byte hex value
	public static final String PRIVATE_KEY = "ca0143c9aa51c3013f08e83f3b6368a4f3ba5b52c4841c6e0c22c300f7ee6827";
	// 64 byte hex value
	public static final String PUBLIC_KEY = "0x9f0d6b3a0775412ca4c9b880c8cef9132b23198b5ef443685a54d36c3fa23a8ca127b7f0764b2e88849de947ca5f44a309617f86fc73a1559e276d437c7453ff";
	// 24 byte hex address - must have 0x prefix
	public static final String FROM_ADDRESS = "0xb067ac9ebeeecb10bbcd1088317959d58d1e38f600000000";
	// 20 byte hex value
	public static final String TO_ADDRESS = "0xfee3d08216f0bfe96a5c0f5df9ec9b2577ad64f0";

	public static final ECKeyPair KEY_PAIR = new ECKeyPair(Numeric.toBigInt(PRIVATE_KEY), Numeric.toBigInt(PUBLIC_KEY));

	public static final String CONTRACT_BYTECODE = "0x608060405234801561001057600080fd5b5060405160208061063d83398101806040528101908080519060200190929190505050806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550506105b7806100866000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde0314610072578063313ce5671461010257806370a082311461013357806395d89b411461018a578063a9059cbb1461021a575b600080fd5b34801561007e57600080fd5b5061008761027f565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100c75780820151818401526020810190506100ac565b50505050905090810190601f1680156100f45780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561010e57600080fd5b506101176102b8565b604051808260ff1660ff16815260200191505060405180910390f35b34801561013f57600080fd5b50610174600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506102bd565b6040518082815260200191505060405180910390f35b34801561019657600080fd5b5061019f610305565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101df5780820151818401526020810190506101c4565b50505050905090810190601f16801561020c5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561022657600080fd5b50610265600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061033e565b604051808215151515815260200191505060405180910390f35b6040805190810160405280600881526020017f546f6b656e41424300000000000000000000000000000000000000000000000081525081565b601281565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6040805190810160405280600381526020017f414243000000000000000000000000000000000000000000000000000000000081525081565b60008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054821115151561038d57600080fd5b816000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054036000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054826000808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020540111151561049957fe5b816000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054016000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a360019050929150505600a165627a7a72305820b5779b002977c3b7b2f08b6c927663dfbd5e6b5e8053e8d7097908b0932215a40029";

	public static final String DEFAULT_TOKEN_ID = "0x8bb0";
	public static final String DEFAULT_GAS = Numeric.toHexStringWithPrefix(BigInteger.valueOf(21000));
	public static final BigInteger GAS_PRICE = BigInteger.valueOf(10_000_000_000L);
	public static final BigInteger GAS_LIMIT = BigInteger.valueOf(2_000_000);

	public static TransactionReceipt transferQKC(Web3j devnet) throws Exception {
		GetTransactionCount getTransactionCount = devnet.getTransactionCount(FROM_ADDRESS).sendAsync().get();
		BigInteger nonce = getTransactionCount.getTransactionCount();

		NetworkInfo network = devnet.networkInfo().send();
		Info networkInfo = network.getResult();
		String networkId = networkInfo.getNetworkId();

		BigInteger fullShardKey = BigInteger.ZERO;
		BigInteger value = Convert.toWei("1", Convert.Unit.ETHER).toBigInteger();
		EvmTransaction evmTransaction = EvmTransaction.createQKCThansferTransaction(nonce, GAS_PRICE, GAS_LIMIT,
				TO_ADDRESS, value, networkId, fullShardKey, fullShardKey, DEFAULT_TOKEN_ID, DEFAULT_TOKEN_ID);

		evmTransaction.Sign(KEY_PAIR);
		System.out.println("EvmTransaction=" + evmTransaction);
		byte[] signedMessage = TransactionEncoder.encode(evmTransaction);
		String hexValue = Numeric.toHexString(signedMessage);
		SendTransaction ethSendTransaction = devnet.sendRawTransaction(hexValue).sendAsync().get();
		if (ethSendTransaction.getError() != null) {
			throw new Exception(ethSendTransaction.getError().getMessage());
		}
		String transactionId = ethSendTransaction.getTransactionID();
		if (Numeric.toBigInt(transactionId).equals(BigInteger.ZERO)) {
			throw new Exception("transfer failed: probably insufficient balance");
		}
		TransactionHelper helper = new TransactionHelper(devnet);
		GetTransactionReceipt.TransactionReceipt transactionReceipt = helper.waitForTransactionReceipt(transactionId);
		return transactionReceipt;
	}

	public static String deploySmartContract(Web3j devnet, String params) throws Exception {
		GetTransactionCount getTransactionCount = devnet.getTransactionCount(FROM_ADDRESS).sendAsync().get();
		BigInteger nonce = getTransactionCount.getTransactionCount();

		NetworkInfo network = devnet.networkInfo().send();
		Info networkInfo = network.getResult();
		String networkId = networkInfo.getNetworkId();
		BigInteger fullShardKey = BigInteger.ZERO;
		EvmTransaction evmTransaction = EvmTransaction.createSmartContractTransaction(nonce, GAS_PRICE, GAS_LIMIT,
				networkId, fullShardKey, fullShardKey, DEFAULT_TOKEN_ID, DEFAULT_TOKEN_ID, CONTRACT_BYTECODE + params);
		evmTransaction.Sign(KEY_PAIR);
		System.out.println("EvmTransaction=" + evmTransaction);
		byte[] signedMessage = TransactionEncoder.encode(evmTransaction);
		String hexValue = Numeric.toHexString(signedMessage);
		SendTransaction ethSendTransaction = devnet.sendRawTransaction(hexValue).sendAsync().get();
		String transactionId = ethSendTransaction.getTransactionID();
		TransactionHelper helper = new TransactionHelper(devnet);
		GetTransactionReceipt.TransactionReceipt transactionReceipt = helper.waitForTransactionReceipt(transactionId);
		System.out.println("status=" + transactionReceipt.getStatus());
		String address = transactionReceipt.getContractAddress().get();
		System.out.println("contract address=" + address);
		return address;
	}

	private static BigInteger getQKCBalance(Web3j web3) throws IOException {
		GetBalances balances = web3.getBalances(FROM_ADDRESS).send();
		System.out.println("balances" + balances.getResult());
		List<Balance> bs = balances.getResult().getBalances();
		BigInteger qkc = null;
		for (Balance balance : bs) {
			if (balance.getTokenId().equalsIgnoreCase(DEFAULT_TOKEN_ID)) {
				qkc = Numeric.toBigInt(balance.getBalance());
			}
		}
		return qkc;
	}

	private static BigInteger callSmartContract(Web3j web3, String address) throws Exception {
		String data = TransactionHelper.buildMethodId("balanceOf(address)");
		BigInteger addrBI = new BigInteger(Numeric.hexStringToByteArray(FROM_ADDRESS.substring(0, 42)));
		byte[] input = Numeric.toBytesPadded(addrBI, 32);
		String params = Numeric.toHexString(input, 0, input.length, false);
		data += params;
		System.out.println("data=" + data);
		TransactionReq tx = new TransactionReq(FROM_ADDRESS, address, Numeric.toHexStringWithPrefix(GAS_LIMIT),
				Numeric.toHexStringWithPrefix(GAS_PRICE), "0x0", data, DEFAULT_TOKEN_ID, DEFAULT_TOKEN_ID);
		Call call = web3.call(tx, DefaultBlockParameterName.LATEST).sendAsync().get();
		String result = call.getResult();
		if (result != null) {
			return Numeric.toBigInt(result);
		}
		throw new Exception("err:" + call.getError().getMessage());
	}

	private static String execSmartContractFunction(Web3j web3, String address, BigInteger amount) throws Exception {
		GetTransactionCount getTransactionCount = web3.getTransactionCount(FROM_ADDRESS).sendAsync().get();
		BigInteger nonce = getTransactionCount.getTransactionCount();

		NetworkInfo network = web3.networkInfo().send();
		Info networkInfo = network.getResult();
		String networkId = networkInfo.getNetworkId();

		String data = TransactionHelper.buildMethodId("transfer(address,uint256)");
		BigInteger addrBI = new BigInteger(Numeric.hexStringToByteArray(TO_ADDRESS.substring(0, 42)));
		byte[] input0 = Numeric.toBytesPadded(addrBI, 32);
		String param0 = Numeric.toHexString(input0, 0, input0.length, false);
		byte[] input1 = Numeric.toBytesPadded(amount, 32);
		String param1 = Numeric.toHexString(input1, 0, input1.length, false);
		data = data + param0 + param1;
		System.out.println("data=" + data);

		BigInteger fullShardKey = BigInteger.ZERO;
		EvmTransaction evmTransaction = EvmTransaction.createSmartContractFunctionCallTransaction(nonce, GAS_PRICE,
				GAS_LIMIT, address, BigInteger.ZERO, networkId, fullShardKey, fullShardKey, DEFAULT_TOKEN_ID,
				DEFAULT_TOKEN_ID, data);
		evmTransaction.Sign(KEY_PAIR);
		System.out.println("EvmTransaction=" + evmTransaction);
		byte[] signedMessage = TransactionEncoder.encode(evmTransaction);
		String hexValue = Numeric.toHexString(signedMessage);
		SendTransaction ethSendTransaction = web3.sendRawTransaction(hexValue).sendAsync().get();
		Error err = ethSendTransaction.getError();
		if (err != null) {
			throw new Exception(err.getData());
		}
		String transactionId = ethSendTransaction.getTransactionID();
		TransactionHelper helper = new TransactionHelper(web3);
		GetTransactionReceipt.TransactionReceipt transactionReceipt = helper.waitForTransactionReceipt(transactionId);
		System.out.println("call transfer done: " + transactionReceipt);
		return transactionReceipt.getStatus();
	}

	private static List<String> getLogs(Web3j web3, String ethAddress) throws Exception {
//		EthFilter ethFilter = new EthFilter();//devnet
		EthFilter ethFilter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST,
				ethAddress);

		String encodedEvent = TransactionHelper.encodeEvent("Transfer(address,address,uint256)");
		System.out.println("encodedEvent=" + encodedEvent);
		ethFilter.addSingleTopic(encodedEvent);

		EthLog ethLog = web3.getLogs(ethFilter, "0x0").send();

		if (ethLog.getError() != null) {
			throw new Exception(ethLog.getError().getMessage());
		}
		List<LogResult> logs = ethLog.getLogs();
		System.out.println("logs=" + logs);
		List<String> result = new ArrayList<String>();
		for (LogResult log : logs) {
			StringBuilder msg = new StringBuilder("transfered ");
			Log l = (Log) log;
			msg.append(Numeric.toBigInt(l.getData()));
			msg.append("wei from ");
			msg.append("0x" + l.getTopics().get(1).substring(26));
			msg.append(" to ");
			msg.append("0x" + l.getTopics().get(2).substring(26));
			msg.append(". \n");
			result.add(msg.toString());
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Connecting to Quarkchain ...");
		Web3j web3 = Web3j.build(new HttpService(URL));
		System.out.println("Successfuly connected to Quarkchain");

		// getRootBlockByHeight
		GetRootBlock rBlock = web3.getRootBlockByHeight((BigInteger.valueOf(2))).send();
		if (rBlock.getBlock().isPresent()) {
			GetRootBlock.RootBlock block = rBlock.getBlock().get();
			System.out.println("block=" + block);
		}

		// make sure FROM_ADDRESS has pre allocated QKC for tx tests.
		BigInteger qkc = getQKCBalance(web3);
		if (qkc == null || qkc.compareTo(BigInteger.ZERO) == 0) {
			System.out.println("Not enough QKC to continue!");
			return;
		}

		// sendRawTransaction
		TransactionReceipt transactionReceipt = transferQKC(web3);
		System.out.println("status=" + transactionReceipt.getStatus());

		// getMinorBlockByHeight
		String h = transactionReceipt.getBlockHeight();
		GetMinorBlock mBlock = web3.getMinorBlockByHeight("0x00001", Numeric.toBigInt(h), true).send();
		if (mBlock.getBlock().isPresent()) {
			GetMinorBlock.MinorBlock block = mBlock.getBlock().get();
			System.out.println("block=" + block);
		}

		// deploy contract with constructor argument 2000000000000000
		byte[] totalSupply = Numeric.toBytesPadded(BigInteger.valueOf(2_000_000_000_000_000L), 32);
		String totalSupplyParam = Numeric.toHexString(totalSupply, 0, totalSupply.length, false);
		String address = deploySmartContract(web3, totalSupplyParam);

		// exec transfer
		String ethAddress = address.substring(0, 42);
		String status = execSmartContractFunction(web3, ethAddress, BigInteger.TEN);
		System.out.println("status=" + status);

		// call
		BigInteger blc1 = callSmartContract(web3, address);
		System.out.println("balanceOf " + FROM_ADDRESS + ":" + blc1);

		List<String> logs = getLogs(web3, ethAddress);
		System.out.println(logs);

		System.out.println("All set!");
	}
}
