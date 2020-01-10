package org.quarkchain.web3j.protocol.core.request;

import org.quarkchain.web3j.utils.Numeric;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionReq {

	private String nonce;
	private String from;
	private String to;
	private String gas;
	private String gasPrice;
	private String value;
	private String data;
	private String networkId;
	private String fromFullShardKey;
	private String toFullShardKey;
	private String v;
	private String r;
	private String s;
	private String gasTokenId;
	private String transferTokenId;

	public TransactionReq() {
		super();
	}

	public TransactionReq(String from, String to, String gas, String gasPrice, String value, String data,
			String transferTokenId, String gasTokenId) {
		super();
		this.from = from;
		this.to = to;
		this.gas = gas;
		this.gasPrice = gasPrice;
		this.value = value;
		this.gasTokenId = gasTokenId;
		this.transferTokenId = transferTokenId;
		if (data != null) {
			this.data = Numeric.prependHexPrefix(data);
		}
	}

	public TransactionReq(String fromAddress, String toAddress, String data, String transferTokenId,
			String gasTokenId) {
		super();
		this.from = fromAddress;
		this.to = toAddress;
		this.gasTokenId = gasTokenId;
		this.transferTokenId = transferTokenId;
		if (data != null) {
			this.data = Numeric.prependHexPrefix(data);
		}
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getGas() {
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}

	public String getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(String gasPrice) {
		this.gasPrice = gasPrice;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public String getToFullShardKey() {
		return toFullShardKey;
	}

	public void setToFullShardKey(String toFullShardKey) {
		this.toFullShardKey = toFullShardKey;
	}

	public String getFromFullShardKey() {
		return fromFullShardKey;
	}

	public void setFromFullShardKey(String fromFullShardKey) {
		this.fromFullShardKey = fromFullShardKey;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	@Override
	public String toString() {
		return "TransactionReq [nonce=" + nonce + ", from=" + from + ", to=" + to + ", gas=" + gas + ", gasPrice="
				+ gasPrice + ", value=" + value + ", data=" + data + ", networkId=" + networkId + ", fromFullShardKey="
				+ fromFullShardKey + ", toFullShardKey=" + toFullShardKey + ", v=" + v + ", r=" + r + ", s=" + s
				+ ", gasTokenId=" + gasTokenId + ", transferTokenId=" + transferTokenId + "]";
	}

}
