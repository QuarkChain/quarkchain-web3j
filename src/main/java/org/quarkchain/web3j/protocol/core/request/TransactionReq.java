package org.quarkchain.web3j.protocol.core.request;

import org.quarkchain.web3j.utils.Numeric;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionReq {

	private String from;
	private String to;
	private String gas;
	private String gasPrice;
	private String value;
	private String data;
	private String gasTokenId;
	private String transferTokenId;

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

}
