package org.quarkchain.web3j.protocol.core.response;

public class TransactionRes {

	public TransactionRes() {
	}

	private String blockHeight;
	private String blockId;
	private String chainId;
	private String data;
	private String from;
	private String fromFullShardKey;
	private String fullShardId;
	private String gas;
	private String gasPrice;
	private String gasTokenId;
	private String gasTokenStr;
	private String hash;
	private String id;
	private String networkId;
	private String nonce;
	private String r;
	private String s;
	private String v;
	private String shardId;
	private String timestamp;
	private String to;
	private String toFullShardKey;
	private String transactionIndex;
	private String transferTokenId;
	private String transferTokenStr;
	private String value;

	public String getBlockHeight() {
		return blockHeight;
	}

	public void setBlockHeight(String blockHeight) {
		this.blockHeight = blockHeight;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFromFullShardKey() {
		return fromFullShardKey;
	}

	public void setFromFullShardKey(String fromFullShardKey) {
		this.fromFullShardKey = fromFullShardKey;
	}

	public String getFullShardId() {
		return fullShardId;
	}

	public void setFullShardId(String fullShardId) {
		this.fullShardId = fullShardId;
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

	public String getGasTokenId() {
		return gasTokenId;
	}

	public void setGasTokenId(String gasTokenId) {
		this.gasTokenId = gasTokenId;
	}

	public String getGasTokenStr() {
		return gasTokenStr;
	}

	public void setGasTokenStr(String gasTokenStr) {
		this.gasTokenStr = gasTokenStr;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
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

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getShardId() {
		return shardId;
	}

	public void setShardId(String shardId) {
		this.shardId = shardId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getToFullShardKey() {
		return toFullShardKey;
	}

	public void setToFullShardKey(String toFullShardKey) {
		this.toFullShardKey = toFullShardKey;
	}

	public String getTransactionIndex() {
		return transactionIndex;
	}

	public void setTransactionIndex(String transactionIndex) {
		this.transactionIndex = transactionIndex;
	}

	public String getTransferTokenId() {
		return transferTokenId;
	}

	public void setTransferTokenId(String transferTokenId) {
		this.transferTokenId = transferTokenId;
	}

	public String getTransferTokenStr() {
		return transferTokenStr;
	}

	public void setTransferTokenStr(String transferTokenStr) {
		this.transferTokenStr = transferTokenStr;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TransactionObject [blockHeight=" + blockHeight + ", blockId=" + blockId + ", chainId=" + chainId
				+ ", data=" + data + ", from=" + from + ", fromFullShardKey=" + fromFullShardKey + ", fullShardId="
				+ fullShardId + ", gas=" + gas + ", gasPrice=" + gasPrice + ", gasTokenId=" + gasTokenId
				+ ", gasTokenStr=" + gasTokenStr + ", hash=" + hash + ", id=" + id + ", networkId=" + networkId
				+ ", nonce=" + nonce + ", r=" + r + ", s=" + s + ", v=" + v + ", shardId=" + shardId + ", timestamp="
				+ timestamp + ", to=" + to + ", toFullShardKey=" + toFullShardKey + ", transactionIndex="
				+ transactionIndex + ", transferTokenId=" + transferTokenId + ", transferTokenStr=" + transferTokenStr
				+ ", value=" + value + "]";
	}

}