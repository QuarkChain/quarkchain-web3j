package org.quarkchain.web3j.protocol.core.request;

import java.math.BigInteger;

public class TxData {
	private BigInteger nonce;
	private BigInteger gasPrice;
	private BigInteger gas;
	private String to;
	private BigInteger value;
	private String input;
	private String networkId;
	private BigInteger fromFullShardKey;
	private BigInteger toFullShardKey;
	private String gasTokenId;
	private String transferTokenId;
	private BigInteger version = BigInteger.ZERO;
	private BigInteger v;
	private BigInteger r;
	private BigInteger s;
	private String hash;

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

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigInteger getValue() {
		return value;
	}

	public void setValue(BigInteger value) {
		this.value = value;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public BigInteger getFromFullShardKey() {
		return fromFullShardKey;
	}

	public void setFromFullShardKey(BigInteger fromFullShardKey) {
		this.fromFullShardKey = fromFullShardKey;
	}

	public BigInteger getToFullShardKey() {
		return toFullShardKey;
	}

	public void setToFullShardKey(BigInteger toFullShardKey) {
		this.toFullShardKey = toFullShardKey;
	}

	public String getGasTokenId() {
		return gasTokenId;
	}

	public void setGasTokenId(String gasTokenId) {
		this.gasTokenId = gasTokenId;
	}

	public String getTransferTokenId() {
		return transferTokenId;
	}

	public void setTransferTokenId(String transferTokenId) {
		this.transferTokenId = transferTokenId;
	}

	public BigInteger getVersion() {
		return version;
	}

	public void setVersion(BigInteger version) {
		this.version = version;
	}

	public BigInteger getV() {
		return v;
	}

	public void setV(BigInteger v) {
		this.v = v;
	}

	public BigInteger getR() {
		return r;
	}

	public void setR(BigInteger r) {
		this.r = r;
	}

	public BigInteger getS() {
		return s;
	}

	public void setS(BigInteger s) {
		this.s = s;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public TxData(BigInteger nonce, BigInteger gasPrice, BigInteger gas, String to, BigInteger value, String input,
			String networkId, BigInteger fromFullShardKey, BigInteger toFullShardKey, String gasTokenId,
			String transferTokenId, BigInteger v, BigInteger r, BigInteger s) {
		super();
		this.nonce = nonce;
		this.gasPrice = gasPrice;
		this.gas = gas;
		this.to = to;
		this.value = value;
		this.input = input;
		this.networkId = networkId;
		this.fromFullShardKey = fromFullShardKey;
		this.toFullShardKey = toFullShardKey;
		this.gasTokenId = gasTokenId;
		this.transferTokenId = transferTokenId;
		this.v = v;
		this.r = r;
		this.s = s;
	}

	public TxData(BigInteger nonce, BigInteger gasPrice, BigInteger gas, String to, BigInteger value, String input,
			String networkId, BigInteger fromFullShardKey, BigInteger toFullShardKey, String transferTokenId,
			String gasTokenId) {
		super();
		this.nonce = nonce;
		this.gasPrice = gasPrice;
		this.gas = gas;
		this.to = to;
		this.value = value;
		this.input = input;
		this.networkId = networkId;
		this.fromFullShardKey = fromFullShardKey;
		this.toFullShardKey = toFullShardKey;
		this.gasTokenId = gasTokenId;
		this.transferTokenId = transferTokenId;
	}

	@Override
	public String toString() {
		return "TxData [nonce=" + nonce + ", gasPrice=" + gasPrice + ", gas=" + gas + ", to=" + to + ", value=" + value
				+ ", input=" + input + ", networkId=" + networkId + ", fromFullShardKey=" + fromFullShardKey
				+ ", toFullShardKey=" + toFullShardKey + ", gasTokenId=" + gasTokenId + ", transferTokenId="
				+ transferTokenId + ", version=" + version + ", v=" + v + ", r=" + r + ", s=" + s + ", hash=" + hash
				+ "]";
	}

}
