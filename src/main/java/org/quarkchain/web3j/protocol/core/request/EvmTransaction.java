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
				+ from + ", FromShardsize=" + FromShardsize + ", ToShardsize=" + ToShardsize + "]";
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
	public int FromShardsize;
	public int ToShardsize;

	public static class TxDataUnsigned {

		private BigInteger nonce;
		private BigInteger gasPrice;
		private BigInteger gas;
		private String to;
		private BigInteger value;
		private String input;
		private String networkid;
		private BigInteger fromfullshardid;
		private BigInteger tofullshardid;
		private String gasTokenID;
		private String transferTokenID;

		private TxDataUnsigned(BigInteger nonce, BigInteger gasPrice, BigInteger gas, String to, BigInteger value,
				String input, String networkid, BigInteger fromfullshardid, BigInteger tofullshardid,
				String transferTokenID, String gasTokenID) {
			super();

			this.nonce = nonce;
			this.gasPrice = gasPrice;
			this.gas = gas;
			this.to = to;
			this.value = value;
			if (input != null) {
				this.input = Numeric.cleanHexPrefix(input);
			}
			this.networkid = networkid;
			this.fromfullshardid = fromfullshardid;
			this.tofullshardid = tofullshardid;
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
			return networkid;
		}

		public void setNetworkid(String networkid) {
			this.networkid = networkid;
		}

		public String getInput() {
			return input;
		}

		public void setInput(String input) {
			this.input = input;
		}

		public BigInteger getFromfullshardid() {
			return fromfullshardid;
		}

		public void setFromfullshardid(BigInteger fromfullshardid) {
			this.fromfullshardid = fromfullshardid;
		}

		public BigInteger getTofullshardid() {
			return tofullshardid;
		}

		public void setTofullshardid(BigInteger tofullshardid) {
			this.tofullshardid = tofullshardid;
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

	public static EvmTransaction createQKCThansferTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit,
			String to, BigInteger value, String networkid, BigInteger fromfullshardkey, BigInteger tofullshardkey,
			String transferTokenID, String gasTokenID) {
		TxData txData = new TxData(nonce, gasPrice, gasLimit, to, value, "", networkid, fromfullshardkey,
				tofullshardkey, transferTokenID, gasTokenID);
		EvmTransaction evmTx = new EvmTransaction();
		evmTx.data = txData;
		return evmTx;
	}

	public static EvmTransaction createSmartContractTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit,
			String networkid, BigInteger fromfullshardkey, BigInteger tofullshardkey,
			String transferTokenID, String gasTokenID, String contractBytecode) {

		TxData txData = new TxData(nonce, gasPrice, gasLimit, "",BigInteger.ZERO, contractBytecode, networkid, fromfullshardkey,
				tofullshardkey, transferTokenID, gasTokenID);
		EvmTransaction evmTx = new EvmTransaction();
		evmTx.data = txData;
		return evmTx;
	}

	public TxDataUnsigned UnsignedData() {
		return new TxDataUnsigned(this.data.getNonce(), this.data.getGasPrice(), this.data.getGas(), this.data.getTo(),
				this.data.getValue(), this.data.getInput(), this.data.getNetworkid(), this.data.getFromfullshardkey(),
				this.data.getTofullshardkey(), this.data.getTransfer_token_id(), this.data.getGas_token_id());
	}

	public void Sign(ECKeyPair keyPair) {
		SignatureData signatureData = TransactionEncoder.signMessage(this.UnsignedData(), keyPair);
		BigInteger r = new BigInteger(1, signatureData.getR());
		BigInteger s = new BigInteger(1, signatureData.getS());
		BigInteger v = BigInteger.valueOf(signatureData.getV());

		TxData txData = this.getData();
		txData.setV(v);
		txData.setS(s);
		txData.setR(r);
	}

}
