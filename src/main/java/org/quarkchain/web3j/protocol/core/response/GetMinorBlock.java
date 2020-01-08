package org.quarkchain.web3j.protocol.core.response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.quarkchain.web3j.protocol.ObjectMapperFactory;
import org.quarkchain.web3j.protocol.core.Response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class GetMinorBlock extends Response<GetMinorBlock.MinorBlock> {

	@Override
	@JsonDeserialize(using = GetMinorBlock.ResponseDeserialiser.class)
	public void setResult(MinorBlock result) {
		super.setResult(result);
	}

	public Optional<MinorBlock> getBlock() {
		return Optional.ofNullable(getResult());
	}

	public static class MinorBlock {
		private String id;
		private String height;
		private String hash;
		private String fullShardId;
		private String chainId;
		private String shardId;
		private String hashPrevMinorBlock;
		private String idPrevMinorBlock;
		private String hashPrevRootBlock;
		private String nonce;
		private String difficulty;
		private String miner;
		private List<GetBalances.Balance> coinbase;
		private String timestamp;
		private String extraData;
		private String gasLimit;
		private String gasUsed;
		private String sealHash;
		private String hashEvmStateRoot;
		private String hashMerkleRoot;
		private String size;
		private List<TransactionRes> transactions;

		public MinorBlock() {
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getHeight() {
			return height;
		}

		public void setHeight(String height) {
			this.height = height;
		}

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		public String getFullShardId() {
			return fullShardId;
		}

		public void setFullShardId(String fullShardId) {
			this.fullShardId = fullShardId;
		}

		public String getChainId() {
			return chainId;
		}

		public void setChainId(String chainId) {
			this.chainId = chainId;
		}

		public String getShardId() {
			return shardId;
		}

		public void setShardId(String shardId) {
			this.shardId = shardId;
		}

		public String getHashPrevMinorBlock() {
			return hashPrevMinorBlock;
		}

		public void setHashPrevMinorBlock(String hashPrevMinorBlock) {
			this.hashPrevMinorBlock = hashPrevMinorBlock;
		}

		public String getIdPrevMinorBlock() {
			return idPrevMinorBlock;
		}

		public void setIdPrevMinorBlock(String idPrevMinorBlock) {
			this.idPrevMinorBlock = idPrevMinorBlock;
		}

		public String getHashPrevRootBlock() {
			return hashPrevRootBlock;
		}

		public void setHashPrevRootBlock(String hashPrevRootBlock) {
			this.hashPrevRootBlock = hashPrevRootBlock;
		}

		public String getNonce() {
			return nonce;
		}

		public void setNonce(String nonce) {
			this.nonce = nonce;
		}

		public String getDifficulty() {
			return difficulty;
		}

		public void setDifficulty(String difficulty) {
			this.difficulty = difficulty;
		}

		public String getMiner() {
			return miner;
		}

		public void setMiner(String miner) {
			this.miner = miner;
		}

		public List<GetBalances.Balance> getCoinbase() {
			return coinbase;
		}

		public void setCoinbase(List<GetBalances.Balance> coinbase) {
			this.coinbase = coinbase;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getExtraData() {
			return extraData;
		}

		public void setExtraData(String extraData) {
			this.extraData = extraData;
		}

		public String getGasLimit() {
			return gasLimit;
		}

		public void setGasLimit(String gasLimit) {
			this.gasLimit = gasLimit;
		}

		public String getGasUsed() {
			return gasUsed;
		}

		public void setGasUsed(String gasUsed) {
			this.gasUsed = gasUsed;
		}

		public String getSealHash() {
			return sealHash;
		}

		public void setSealHash(String sealHash) {
			this.sealHash = sealHash;
		}

		public String getHashEvmStateRoot() {
			return hashEvmStateRoot;
		}

		public void setHashEvmStateRoot(String hashEvmStateRoot) {
			this.hashEvmStateRoot = hashEvmStateRoot;
		}

		public String getHashMerkleRoot() {
			return hashMerkleRoot;
		}

		public void setHashMerkleRoot(String hashMerkleRoot) {
			this.hashMerkleRoot = hashMerkleRoot;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public List<TransactionRes> getTransactions() {
			return transactions;
		}

		@JsonDeserialize(using = TransactionDeserialiser.class)
		public void setTransactions(List<TransactionRes> transactions) {
			this.transactions = transactions;
		}

		@Override
		public String toString() {
			return "MinorBlock [id=" + id + ", height=" + height + ", hash=" + hash + ", fullShardId=" + fullShardId
					+ ", chainId=" + chainId + ", shardId=" + shardId + ", hashPrevMinorBlock=" + hashPrevMinorBlock
					+ ", idPrevMinorBlock=" + idPrevMinorBlock + ", hashPrevRootBlock=" + hashPrevRootBlock + ", nonce="
					+ nonce + ", difficulty=" + difficulty + ", miner=" + miner + ", coinbase=" + coinbase
					+ ", timestamp=" + timestamp + ", extraData=" + extraData + ", gasLimit=" + gasLimit + ", gasUsed="
					+ gasUsed + ", sealHash=" + sealHash + ", hashEvmStateRoot=" + hashEvmStateRoot
					+ ", hashMerkleRoot=" + hashMerkleRoot + ", size=" + size + ", transactions=" + transactions + "]";
		}

	}

	public static class TransactionDeserialiser extends JsonDeserializer<List<TransactionRes>> {

		private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

		@Override
		public List<TransactionRes> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException {

			List<TransactionRes> transactionResults = new ArrayList<>();
			JsonToken nextToken = jsonParser.nextToken();
			if (nextToken == JsonToken.END_ARRAY) {
				return transactionResults;
			}
			Iterator<TransactionRes> transactionObjectIterator = objectReader.readValues(jsonParser,
					TransactionRes.class);
			while (transactionObjectIterator.hasNext()) {
				transactionResults.add(transactionObjectIterator.next());
			}
			return transactionResults;
		}
	}

	public static class ResponseDeserialiser extends JsonDeserializer<MinorBlock> {

		private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

		@Override
		public MinorBlock deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException {
			if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
				return objectReader.readValue(jsonParser, MinorBlock.class);
			} else {
				return null; // null is wrapped by Optional in above getter
			}
		}
	}
}
