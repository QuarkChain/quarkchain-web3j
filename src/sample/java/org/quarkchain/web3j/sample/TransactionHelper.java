package org.quarkchain.web3j.sample;

import java.math.BigInteger;
import java.util.Optional;

import org.quarkchain.web3j.crypto.ECKeyPair;
import org.quarkchain.web3j.crypto.Hash;
import org.quarkchain.web3j.protocol.Web3j;
import org.quarkchain.web3j.protocol.core.response.GetTransactionReceipt;
import org.quarkchain.web3j.utils.Numeric;

public class TransactionHelper {

	private static final int SLEEP_DURATION = 10000;
	private static final int ATTEMPTS = 20;
	private Web3j web3j;

	public TransactionHelper(Web3j web3j) {
		super();
		this.web3j = web3j;
	}

	public GetTransactionReceipt.TransactionReceipt waitForTransactionReceipt(String transactionHash) throws Exception {

		Optional<GetTransactionReceipt.TransactionReceipt> transactionReceiptOptional = getTransactionReceipt(
				transactionHash, SLEEP_DURATION, ATTEMPTS);

		if (!transactionReceiptOptional.isPresent()) {
			throw new Exception("Transaction reciept not generated after " + ATTEMPTS + " attempts");
		}

		return transactionReceiptOptional.get();
	}

	private Optional<GetTransactionReceipt.TransactionReceipt> getTransactionReceipt(String transactionHash,
			int sleepDuration, int attempts) throws Exception {

		Optional<GetTransactionReceipt.TransactionReceipt> receiptOptional = sendTransactionReceiptRequest(
				transactionHash);
		for (int i = 0; i < attempts; i++) {
			if (!receiptOptional.isPresent() || receiptOptional.get().getTimestamp().equals("0x0")) {
				Thread.sleep(sleepDuration);
				receiptOptional = sendTransactionReceiptRequest(transactionHash);
			} else {
				break;
			}
		}
		return receiptOptional;
	}

	private Optional<GetTransactionReceipt.TransactionReceipt> sendTransactionReceiptRequest(String transactionHash)
			throws Exception {
		GetTransactionReceipt transactionReceipt = web3j.getTransactionReceipt(transactionHash).sendAsync().get();
		return transactionReceipt.getTransactionReceipt();
	}

	public static void getPubKey(String privKey) {
		BigInteger privateKeyBI = Numeric.toBigInt(privKey);
		ECKeyPair kp = ECKeyPair.create(privateKeyBI);
		BigInteger pkBI = kp.getPublicKey();
		System.out.println("pubkey=" + Numeric.toHexStringWithPrefix(pkBI));
	}

	public static String buildMethodId(String methodSignature) {
		byte[] input = methodSignature.getBytes();
		byte[] hash = Hash.sha3(input);
		return Numeric.toHexString(hash).substring(0, 10);
	}

	public static String encodeEvent(String methodSignature) {
		byte[] input = methodSignature.getBytes();
		byte[] hash = Hash.sha3(input);
		return Numeric.toHexString(hash);
	}

}
