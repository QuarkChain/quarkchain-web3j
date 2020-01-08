package org.quarkchain.web3j.protocol.core.request;

import java.math.BigInteger;
 

public class TxData {
	@Override
	public String toString() {
		return "TxData [nonce=" + nonce + ", gasPrice=" + gasPrice + ", gas=" + gas + ", to=" + to + ", value=" + value
				+ ", input=" + input + ", networkid=" + networkid + ", fromfullshardkey=" + fromfullshardkey
				+ ", tofullshardkey=" + tofullshardkey + ", gas_token_id=" + gas_token_id + ", transfer_token_id="
				+ transfer_token_id + ", version=" + version + ", v=" + v + ", r=" + r + ", s=" + s + ", hash=" + hash
				+ "]";
	}
	public void setNonce(BigInteger nonce) {
		this.nonce = nonce;
	}
	public void setGasPrice(BigInteger gasPrice) {
		this.gasPrice = gasPrice;
	}
	public void setGas(BigInteger gas) {
		this.gas = gas;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setValue(BigInteger value) {
		this.value = value;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public void setNetworkid(String networkid) {
		this.networkid = networkid;
	}
	public void setFromfullshardkey(BigInteger fromfullshardkey) {
		this.fromfullshardkey = fromfullshardkey;
	}
	public void setTofullshardkey(BigInteger tofullshardkey) {
		this.tofullshardkey = tofullshardkey;
	}
	public void setGas_token_id(String gas_token_id) {
		this.gas_token_id = gas_token_id;
	}
	public void setTransfer_token_id(String transfer_token_id) {
		this.transfer_token_id = transfer_token_id;
	}
	public void setVersion(BigInteger version) {
		this.version = version;
	}
	public void setV(BigInteger v) {
		this.v = v;
	}
	public void setR(BigInteger r) {
		this.r = r;
	}
	public void setS(BigInteger s) {
		this.s = s;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	private BigInteger nonce;
	private BigInteger gasPrice;
	private BigInteger gas;
	private String to;
	private BigInteger value;
	public TxData(BigInteger nonce, BigInteger gasPrice, BigInteger gas, String to, BigInteger value, String input,
			String networkid, BigInteger fromfullshardkey, BigInteger tofullshardkey, String gas_token_id,
			String transfer_token_id) {
		super();
		this.nonce = nonce;
		this.gasPrice = gasPrice;
		this.gas = gas;
		this.to = to;
		this.value = value;
		this.input = input;
		this.networkid = networkid;
		this.fromfullshardkey = fromfullshardkey;
		this.tofullshardkey = tofullshardkey;
		this.gas_token_id = gas_token_id;
		this.transfer_token_id = transfer_token_id;
	}
	public BigInteger getNonce() {
		return nonce;
	}
	public BigInteger getGasPrice() {
		return gasPrice;
	}
	public BigInteger getGas() {
		return gas;
	}
	public String getTo() {
		return to;
	}
	public BigInteger getValue() {
		return value;
	}
	public String getInput() {
		return input;
	}
	public String getNetworkid() {
		return networkid;
	}
	public BigInteger getFromfullshardkey() {
		return fromfullshardkey;
	}
	public BigInteger getTofullshardkey() {
		return tofullshardkey;
	}
	public String getGas_token_id() {
		return gas_token_id;
	}
	public String getTransfer_token_id() {
		return transfer_token_id;
	}
	public BigInteger getVersion() {
		return version;
	}
	public BigInteger getV() {
		return v;
	}
	public BigInteger getR() {
		return r;
	}
	public BigInteger getS() {
		return s;
	}
	public String getHash() {
		return hash;
	}
	private String input;
	private String networkid;
	private BigInteger fromfullshardkey;
	private BigInteger tofullshardkey;
	private String gas_token_id;
	private String transfer_token_id;
	private BigInteger version=BigInteger.ZERO;
	private BigInteger v;
	private BigInteger r;
	private BigInteger s;
	private String hash;

}
