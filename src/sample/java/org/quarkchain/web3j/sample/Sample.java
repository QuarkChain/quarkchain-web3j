package org.quarkchain.web3j.sample;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import org.quarkchain.web3j.protocol.core.response.EstimateGas;
import org.quarkchain.web3j.protocol.core.response.EthLog;
import org.quarkchain.web3j.protocol.core.response.EthLog.LogResult;
import org.quarkchain.web3j.protocol.core.response.GetBalances;
import org.quarkchain.web3j.protocol.core.response.GetBalances.Balance;
import org.quarkchain.web3j.protocol.core.response.GetMinorBlock;
import org.quarkchain.web3j.protocol.core.response.GetRootBlock;
import org.quarkchain.web3j.protocol.core.response.GetRootHashConfirmingMinorBlockById;
import org.quarkchain.web3j.protocol.core.response.GetTransactionConfirmedByNumberRootBlocks;
import org.quarkchain.web3j.protocol.core.response.GetTransactionCount;
import org.quarkchain.web3j.protocol.core.response.GetTransactionReceipt;
import org.quarkchain.web3j.protocol.core.response.GetTransactionReceipt.TransactionReceipt;
import org.quarkchain.web3j.protocol.core.response.Log;
import org.quarkchain.web3j.protocol.core.response.NetworkInfo;
import org.quarkchain.web3j.protocol.core.response.NetworkInfo.Info;
import org.quarkchain.web3j.protocol.core.response.SendTransaction;
import org.quarkchain.web3j.utils.Convert;
import org.quarkchain.web3j.utils.Numeric;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.Utils;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;

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

	public static final String CONTRACT_BYTECODE = "60806040526040805190810160405280600881526020017f546f6b656e4142430000000000000000000000000000000000000000000000008152506000908051906020019061004f92919061010a565b5034801561005c57600080fd5b50604051602080610b178339810180604052810190808051906020019092919050505080600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555033600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506101af565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061014b57805160ff1916838001178555610179565b82800160010185558215610179579182015b8281111561017857825182559160200191906001019061015d565b5b509050610186919061018a565b5090565b6101ac91905b808211156101a8576000816000905550600101610190565b5090565b90565b610959806101be6000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde031461008857806317d7de7c14610118578063313ce567146101a857806370a08231146101d957806395d89b4114610230578063a9059cbb146102c0578063c47f002714610325575b600080fd5b34801561009457600080fd5b5061009d61038e565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100dd5780820151818401526020810190506100c2565b50505050905090810190601f16801561010a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561012457600080fd5b5061012d61042c565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561016d578082015181840152602081019050610152565b50505050905090810190601f16801561019a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101b457600080fd5b506101bd6104ce565b604051808260ff1660ff16815260200191505060405180910390f35b3480156101e557600080fd5b5061021a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506104d3565b6040518082815260200191505060405180910390f35b34801561023c57600080fd5b5061024561051c565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561028557808201518184015260208101905061026a565b50505050905090810190601f1680156102b25780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156102cc57600080fd5b5061030b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610555565b604051808215151515815260200191505060405180910390f35b34801561033157600080fd5b5061038c600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506107a9565b005b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104245780601f106103f957610100808354040283529160200191610424565b820191906000526020600020905b81548152906001019060200180831161040757829003601f168201915b505050505081565b606060008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104c45780601f10610499576101008083540402835291602001916104c4565b820191906000526020600020905b8154815290600101906020018083116104a757829003601f168201915b5050505050905090565b601281565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6040805190810160405280600381526020017f414243000000000000000000000000000000000000000000000000000000000081525081565b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205482111515156105a557600080fd5b81600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205403600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205482600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054011115156106b557fe5b81600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205401600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a36001905092915050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561086e576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601a8152602001807f6f6e6c79206f776e65722063616e206368616e6765206e616d6500000000000081525060200191505060405180910390fd5b8060009080519060200190610884929190610888565b5050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106108c957805160ff19168380011785556108f7565b828001600101855582156108f7579182015b828111156108f65782518255916020019190600101906108db565b5b5090506109049190610908565b5090565b61092a91905b8082111561092657600081600090555060010161090e565b5090565b905600a165627a7a723058206bc879584edfb69465dcc064b0f071a8244945b6091f6cf841bd8149a3784cdb0029";

	public static final String DEFAULT_TOKEN_ID = "0x8bb0";
	public static final String DEFAULT_GAS = Numeric.toHexStringWithPrefix(BigInteger.valueOf(21000));
	public static final BigInteger GAS_PRICE = BigInteger.valueOf(10_000_000_000L);
	public static final BigInteger GAS_LIMIT = BigInteger.valueOf(2_000_000);

	private static TransactionReceipt transferQKC(Web3j devnet) throws Exception {
		GetTransactionCount getTransactionCount = devnet.getTransactionCount(FROM_ADDRESS).sendAsync().get();
		BigInteger nonce = getTransactionCount.getTransactionCount();

		NetworkInfo network = devnet.networkInfo().send();
		Info networkInfo = network.getResult();
		System.out.println("networkInfo=" + networkInfo);
		String networkId = networkInfo.getNetworkId();

		String fullShardKey = "0x00000000";
		BigInteger value = Convert.toWei("0.01", Convert.Unit.ETHER).toBigInteger();
		EvmTransaction evmTransaction = EvmTransaction.createQKCThansferTransaction(nonce, GAS_PRICE, GAS_LIMIT,
				TO_ADDRESS, value, networkId, fullShardKey, fullShardKey, DEFAULT_TOKEN_ID, DEFAULT_TOKEN_ID);

		evmTransaction.sign(KEY_PAIR);
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

	private static String deploySmartContract(Web3j devnet, String params) throws Exception {
		GetTransactionCount getTransactionCount = devnet.getTransactionCount(FROM_ADDRESS).sendAsync().get();
		BigInteger nonce = getTransactionCount.getTransactionCount();

		NetworkInfo network = devnet.networkInfo().send();
		Info networkInfo = network.getResult();
		String networkId = networkInfo.getNetworkId();
		String fullShardKey = "0x00000000";
		EvmTransaction evmTransaction = EvmTransaction.createSmartContractTransaction(nonce, GAS_PRICE, GAS_LIMIT,
				networkId, fullShardKey, fullShardKey, DEFAULT_TOKEN_ID, DEFAULT_TOKEN_ID, CONTRACT_BYTECODE + params);
		evmTransaction.sign(KEY_PAIR);
		byte[] signedMessage = TransactionEncoder.encode(evmTransaction);
		String hexValue = Numeric.toHexString(signedMessage);
		SendTransaction ethSendTransaction = devnet.sendRawTransaction(hexValue).sendAsync().get();
		String transactionId = ethSendTransaction.getTransactionID();
		TransactionHelper helper = new TransactionHelper(devnet);
		GetTransactionReceipt.TransactionReceipt transactionReceipt = helper.waitForTransactionReceipt(transactionId);
		System.out.println("deploySmartContract status=" + transactionReceipt.getStatus());
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
		System.out.println("transfer() data=" + data);

		String fullShardKey = "0x00000000";
		EvmTransaction evmTransaction = EvmTransaction.createSmartContractFunctionCallTransaction(nonce, GAS_PRICE,
				GAS_LIMIT, address, BigInteger.ZERO, networkId, fullShardKey, fullShardKey, DEFAULT_TOKEN_ID,
				DEFAULT_TOKEN_ID, data);
		TransactionReq td = evmTransaction.getSignedTx(KEY_PAIR);
		SendTransaction ethSendTransaction = web3.sendTransaction(td).sendAsync().get();
		Error err = ethSendTransaction.getError();
		if (err != null) {
			throw new Exception(err.getMessage());
		}
		String transactionId = ethSendTransaction.getTransactionID();
		TransactionHelper helper = new TransactionHelper(web3);
		GetTransactionReceipt.TransactionReceipt transactionReceipt = helper.waitForTransactionReceipt(transactionId);
		System.out.println("call transfer done: " + transactionReceipt);
		return transactionReceipt.getStatus();
	}

	private static String execSmartContractFunctionSetName(Web3j web3, String address, String name) throws Exception {
		GetTransactionCount getTransactionCount = web3.getTransactionCount(FROM_ADDRESS).sendAsync().get();
		BigInteger nonce = getTransactionCount.getTransactionCount();

		NetworkInfo network = web3.networkInfo().send();
		Info networkInfo = network.getResult();
		String networkId = networkInfo.getNetworkId();

		Function function = new Function("setName", Arrays.<Type>asList(new Utf8String(name)),
				Collections.<TypeReference<?>>emptyList());
		String data = FunctionEncoder.encode(function);
		System.out.println("setName data=" + data);

		String fullShardKey = "0x00000000";
		EvmTransaction evmTransaction = EvmTransaction.createSmartContractFunctionCallTransaction(nonce, GAS_PRICE,
				GAS_LIMIT, address, BigInteger.ZERO, networkId, fullShardKey, fullShardKey, DEFAULT_TOKEN_ID,
				DEFAULT_TOKEN_ID, data);
		TransactionReq td = evmTransaction.getSignedTx(KEY_PAIR);
		SendTransaction ethSendTransaction = web3.sendTransaction(td).sendAsync().get();
		Error err = ethSendTransaction.getError();
		if (err != null) {
			throw new Exception(err.getMessage());
		}
		String transactionId = ethSendTransaction.getTransactionID();
		TransactionHelper helper = new TransactionHelper(web3);
		GetTransactionReceipt.TransactionReceipt transactionReceipt = helper.waitForTransactionReceipt(transactionId);
		System.out.println("call setName done: " + transactionReceipt);
		return transactionReceipt.getStatus();
	}

	private static String callSmartContractGetName(Web3j web3, String address) throws Exception {
		String data = TransactionHelper.buildMethodId("getName()");
		TransactionReq tx = new TransactionReq(FROM_ADDRESS, address, Numeric.toHexStringWithPrefix(GAS_LIMIT),
				Numeric.toHexStringWithPrefix(GAS_PRICE), "0x0", data, DEFAULT_TOKEN_ID, DEFAULT_TOKEN_ID);
		Call call = web3.call(tx, DefaultBlockParameterName.LATEST).sendAsync().get();
		String result = call.getResult();
		if (result != null) {
			List<Type> response = FunctionReturnDecoder.decode(result,
					Utils.convert(Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
					})));
			return response.toString();
		}
		throw new Exception("err:" + call.getError().getMessage());
	}

	private static List<String> getLogs(Web3j web3, String qkcAddress) throws Exception {
		EthFilter ethFilter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST,
				qkcAddress);
		String encodedEvent = TransactionHelper.encodeEvent("Transfer(address,address,uint256)");
		ethFilter.addSingleTopic(encodedEvent);
		EthLog ethLog = web3.getLogs(ethFilter, "0x00000000").send();
		if (ethLog.getError() != null) {
			throw new Exception(ethLog.getError().getMessage());
		}
		List<LogResult> logs = ethLog.getLogs();
		List<String> result = new ArrayList<String>();
		for (LogResult log : logs) {
			StringBuilder msg = new StringBuilder("transfered ");
			Log l = (Log) log;
			msg.append(Numeric.toBigInt(l.getData()));
			msg.append(" wei from ");
			msg.append("0x" + l.getTopics().get(1).substring(26));
			msg.append(" to ");
			msg.append("0x" + l.getTopics().get(2).substring(26));
			msg.append(". \n");
			result.add(msg.toString());
		}
		return result;
	}

	private static void getConfirmedBlocks(Web3j web3, String txId) throws IOException {
		GetTransactionConfirmedByNumberRootBlocks confirm = web3.getTransactionConfirmedByNumberRootBlocks(txId).send();

		int blocks = confirm.getTxCount();
		if (confirm.getError() != null) {
			System.out.println("GetTransactionConfirmedByNumberRootBlocks failed:" + confirm.getError().getMessage());
		} else if (blocks == -1) {
			System.out.println("GetTransactionConfirmedByNumberRootBlocks failed: probably tx not found.");
		} else {
			System.out.println("The transferQKC tx has been confirmed by " + blocks + " root blocks.");
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Connecting to Quarkchain: " + URL);
		Web3j web3 = Web3j.build(new HttpService(URL));
		System.out.println("Successfuly connected to Quarkchain");

		// getRootBlockByHeight
		GetRootBlock rBlock = web3.getRootBlockByHeight(null).send();
		if (rBlock.getBlock().isPresent()) {
			GetRootBlock.RootBlock block = rBlock.getBlock().get();
			System.out.println("block=" + block);
		}

		// make sure FROM_ADDRESS has pre allocated QKC for tx tests.
		BigInteger qkc = getQKCBalance(web3);
		if (qkc == null || qkc.compareTo(BigInteger.ONE) == 0) {
			System.out.println("Not enough QKC to continue!");
			return;
		}

		// estimateGas
		EstimateGas est = web3.estimateGas(
				new TransactionReq(FROM_ADDRESS, TO_ADDRESS + "00000000", "", DEFAULT_TOKEN_ID, DEFAULT_TOKEN_ID))
				.send();
		BigInteger estGas = est.getAmountUsed();
		System.out.println("Estimated gas=" + estGas);

		// sendRawTransaction
		TransactionReceipt transactionReceipt = transferQKC(web3);
		System.out.println("transferQKC status=" + transactionReceipt.getStatus());
		String txId = transactionReceipt.getTransactionId();

		// getMinorBlockByHeight
//		String h = transactionReceipt.getBlockHeight();
		String h = "0x1"; 
		GetMinorBlock mBlock = web3.getMinorBlockByHeight(BigInteger.valueOf(1), Numeric.toBigInt(h), true).sendAsync().get();
		if (mBlock.getBlock().isPresent()) {
			GetMinorBlock.MinorBlock block = mBlock.getBlock().get();
			System.out.println("getMinorBlockByHeight:" + block);
			String mbid = block.getIdPrevMinorBlock();
			GetRootHashConfirmingMinorBlockById confirmingRoot = web3.getRootHashConfirmingMinorBlockById(mbid).send();
			String root = confirmingRoot.getRootHash();
			System.out.println(
					"getRootHashConfirmingMinorBlockById: root block " + root + " includes minor block " + mbid);
		}else {
			System.out.println("getMinorBlockByHeight: not found");
		}

		// deploy contract with constructor argument 2000000000000000
		byte[] totalSupply = Numeric.toBytesPadded(BigInteger.valueOf(2_000_000_000_000_000L), 32);
		String totalSupplyParam = Numeric.toHexString(totalSupply, 0, totalSupply.length, false);
		String address = deploySmartContract(web3, totalSupplyParam);

		// sendTransaction
		String ethAddress = address.substring(0, 42);
		String status = execSmartContractFunction(web3, ethAddress, BigInteger.TEN);
		System.out.println("status=" + status);

		String status1 = execSmartContractFunctionSetName(web3, ethAddress, "NewToken");
		System.out.println("status=" + status1);

		// call
		BigInteger blc1 = callSmartContract(web3, address);
		System.out.println("balanceOf " + FROM_ADDRESS + ":" + blc1);

		System.out.println("new name is now " + callSmartContractGetName(web3, address));

		List<String> logs = getLogs(web3, address);
		System.out.println("log parsed:" + logs);

		getConfirmedBlocks(web3, txId);

		System.out.println("All set!");
	}

}
