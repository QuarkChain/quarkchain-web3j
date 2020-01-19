package org.quarkchain.web3j.protocol.core.request;

import java.math.BigInteger;

import org.quarkchain.web3j.crypto.ECKeyPair;
import org.quarkchain.web3j.crypto.TransactionEncoder;
import org.quarkchain.web3j.crypto.Sign.SignatureData;
import org.quarkchain.web3j.utils.Numeric;

//}
public class EvmTransaction {
	@Override
	public String toString() {
		return "EvmTransaction [data=" + data + ", updated=" + updated + ", hash=" + hash + ", size=" + size + ", from="
				+ from + ", FromShardSize=" + FromShardSize + ", ToShardSize=" + ToShardSize + "]";
	}

	private TxData data;

	public TxData getData() {
		return data;
	}

	public void setData(TxData data) {
		this.data = data;
	}

	private boolean updated;
	private String hash;
	private String size;
	private String from;
	public int FromShardSize;
	public int ToShardSize;

	public static class TxDataUnsigned {

		private BigInteger nonce;
		private BigInteger gasPrice;
		private BigInteger gas;
		private String to;
		private BigInteger value;
		private String input;
		private String networkId;
		private String fromFullShardKey;
		private String toFullShardKey;
		private String gasTokenID;
		private String transferTokenID;

		private TxDataUnsigned(BigInteger nonce, BigInteger gasPrice, BigInteger gas, String to, BigInteger value,
				String input, String networkId, String fromFullShardKey, String toFullShardKey, String transferTokenID,
				String gasTokenID) {
			super();

			this.nonce = nonce;
			this.gasPrice = gasPrice;
			this.gas = gas;
			this.to = to;
			this.value = value;
			if (input != null) {
				this.input = Numeric.cleanHexPrefix(input);
			}
			this.networkId = networkId;
			this.fromFullShardKey = fromFullShardKey;
			this.toFullShardKey = toFullShardKey;
			this.gasTokenID = gasTokenID;
			this.transferTokenID = transferTokenID;

		}

		public BigInteger getNonce() {
			return nonce;
		}

		public void setNonce(BigInteger nonce) {
			this.nonce = nonce;
		}

		public BigInteger getGasPrice() {
			return gasPrice;
		}

		public void setGasPrice(BigInteger gasPrice) {
			this.gasPrice = gasPrice;
		}

		public BigInteger getGas() {
			return gas;
		}

		public void setGas(BigInteger gas) {
			this.gas = gas;
		}

		public BigInteger getValue() {
			return value;
		}

		public void setValue(BigInteger value) {
			this.value = value;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public String getData() {
			return input;
		}

		public void setData(String data) {
			this.input = data;
		}

		public String getNetworkid() {
			return networkId;
		}

		public void setNetworkid(String networkId) {
			this.networkId = networkId;
		}

		public String getInput() {
			return input;
		}

		public void setInput(String input) {
			this.input = input;
		}

		public String getFromFullShardKey() {
			return fromFullShardKey;
		}

		public void setFromFullShardKey(String fromFullShardKey) {
			this.fromFullShardKey = fromFullShardKey;
		}

		public String getToFullShardKey() {
			return toFullShardKey;
		}

		public void setToFullShardKey(String toFullShardKey) {
			this.toFullShardKey = toFullShardKey;
		}

		public String getGasTokenID() {
			return gasTokenID;
		}

		public void setGasTokenID(String gasTokenID) {
			this.gasTokenID = gasTokenID;
		}

		public String getTransferTokenID() {
			return transferTokenID;
		}

		public void setTransferTokenID(String transferTokenID) {
			this.transferTokenID = transferTokenID;
		}
	}

	public static EvmTransaction createQKCThansferTransaction(BigInteger nonce, BigInteger gasPrice,
			BigInteger gasLimit, String to, BigInteger value, String networkId, String fromFullShardKey,
			String toFullShardKey, String transferTokenID, String gasTokenID) {
		TxData txData = new TxData(nonce, gasPrice, gasLimit, to, value, "", networkId, fromFullShardKey,
				toFullShardKey, transferTokenID, gasTokenID);
		EvmTransaction evmTx = new EvmTransaction();
		evmTx.data = txData;
		return evmTx;
	}

	public static EvmTransaction createSmartContractTransaction(BigInteger nonce, BigInteger gasPrice,
			BigInteger gasLimit, String networkId, String fromFullShardKey, String toFullShardKey,
			String transferTokenID, String gasTokenID, String contractBytecode) {

		TxData txData = new TxData(nonce, gasPrice, gasLimit, "", BigInteger.ZERO, contractBytecode, networkId,
				fromFullShardKey, toFullShardKey, transferTokenID, gasTokenID);
		EvmTransaction evmTx = new EvmTransaction();
		evmTx.data = txData;
		return evmTx;
	}

	public static EvmTransaction createSmartContractFunctionCallTransaction(BigInteger nonce, BigInteger gasPrice,
			BigInteger gasLimit, String contractAddress, BigInteger value, String networkId, String fullShardKeyFrom,
			String fullShardKeyTo, String transferTokenID, String gasTokenID, String data) {
		TxData txData = new TxData(nonce, gasPrice, gasLimit, contractAddress, value, data, networkId, fullShardKeyFrom,
				fullShardKeyTo, transferTokenID, gasTokenID);
		EvmTransaction evmTx = new EvmTransaction();
		evmTx.data = txData;
		return evmTx;
	}

	public TxDataUnsigned UnsignedData() {
		return new TxDataUnsigned(this.data.getNonce(), this.data.getGasPrice(), this.data.getGas(), this.data.getTo(),
				this.data.getValue(), this.data.getInput(), this.data.getNetworkId(), this.data.getFromFullShardKey(),
				this.data.getToFullShardKey(), this.data.getTransferTokenId(), this.data.getGasTokenId());
	}

	public void sign(ECKeyPair keyPair) {
		SignatureData signatureData = TransactionEncoder.signMessage(this.UnsignedData(), keyPair);
		BigInteger r = new BigInteger(1, signatureData.getR());
		BigInteger s = new BigInteger(1, signatureData.getS());
		BigInteger v = BigInteger.valueOf(signatureData.getV());

		TxData txData = this.getData();
		txData.setV(v);
		txData.setS(s);
		txData.setR(r);
	}

	public TransactionReq getSignedTx(ECKeyPair keyPair) {
		TxData txData = this.getData();
		TransactionReq tx = new TransactionReq();
		tx.setNonce(Numeric.toHexStringWithPrefix(txData.getNonce()));
		tx.setGasPrice(Numeric.toHexStringWithPrefix(txData.getGasPrice()));
		tx.setGas(Numeric.toHexStringWithPrefix(txData.getGas()));
		tx.setValue(Numeric.toHexStringWithPrefix(txData.getValue()));
		tx.setData(txData.getInput());
		tx.setFromFullShardKey((txData.getFromFullShardKey()));
		tx.setToFullShardKey((txData.getToFullShardKey()));
		tx.setNetworkId(txData.getNetworkId());
		tx.setTo(txData.getTo());
		tx.setGasTokenId(txData.getGasTokenId());
		tx.setTransferTokenId((txData.getTransferTokenId()));
		SignatureData signatureData = TransactionEncoder.signMessage(this.UnsignedData(), keyPair);
		tx.setR(Numeric.toHexString(signatureData.getR()));
		tx.setS(Numeric.toHexString(signatureData.getS()));
		tx.setV(Numeric.toHexString(new byte[] { signatureData.getV() }));
		return tx;
	}

}
