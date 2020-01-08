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

public class GetRootBlock extends Response<GetRootBlock.RootBlock> {

	@Override
	@JsonDeserialize(using = GetRootBlock.ResponseDeserialiser.class)
	public void setResult(RootBlock result) {
		super.setResult(result);
	}

	public static class MinorBlockHeader {
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
		private String sealHash;

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

		@Override
		public String toString() {
			return "MinorBlockHeader [id=" + id + ", height=" + height + ", hash=" + hash + ", fullShardId="
					+ fullShardId + ", chainId=" + chainId + ", shardId=" + shardId + ", hashPrevMinorBlock="
					+ hashPrevMinorBlock + ", idPrevMinorBlock=" + idPrevMinorBlock + ", hashPrevRootBlock="
					+ hashPrevRootBlock + ", nonce=" + nonce + ", difficulty=" + difficulty + ", miner=" + miner
					+ ", coinbase=" + coinbase + ", timestamp=" + timestamp + "]";
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

		public String getSealHash() {
			return sealHash;
		}

		public void setSealHash(String sealHash) {
			this.sealHash = sealHash;
		}
	}

	public Optional<RootBlock> getBlock() {
		return Optional.ofNullable(getResult());
	}

	public static class RootBlock {
		private String id;
		private String hash;
		private String height;
		private String idPrevBlock;
		private String hashPrevBlock;
		private String nonce;
		private String hashMerkleRoot;
		private String miner;
		private List<GetBalances.Balance> coinbase;
		private String difficulty;
		private String timestamp;
		private String size;
		private List<GetRootBlock.MinorBlockHeader> minorBlockHeaders;
		private String sealHash;
		private String signature;


		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		public String getHeight() {
			return height;
		}

		public void setHeight(String height) {
			this.height = height;
		}

		public String getIdPrevBlock() {
			return idPrevBlock;
		}

		public void setIdPrevBlock(String idPrevBlock) {
			this.idPrevBlock = idPrevBlock;
		}

		public String getHashPrevBlock() {
			return hashPrevBlock;
		}

		public void setHashPrevBlock(String hashPrevBlock) {
			this.hashPrevBlock = hashPrevBlock;
		}

		public String getNonce() {
			return nonce;
		}

		public void setNonce(String nonce) {
			this.nonce = nonce;
		}

		public String getHashMerkleRoot() {
			return hashMerkleRoot;
		}

		public void setHashMerkleRoot(String hashMerkleRoot) {
			this.hashMerkleRoot = hashMerkleRoot;
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

		public String getDifficulty() {
			return difficulty;
		}

		public void setDifficulty(String difficulty) {
			this.difficulty = difficulty;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public List<GetRootBlock.MinorBlockHeader> getMinorBlockHeaders() {
			return minorBlockHeaders;
		}

		@JsonDeserialize(using = MinorBlockHeaderDeserialiser.class)
		public void setMinorBlockHeaders(List<GetRootBlock.MinorBlockHeader> minorBlockHeaders) {
			this.minorBlockHeaders = minorBlockHeaders;
		}

		public RootBlock() {
		}

		@Override
		public String toString() {
			return "RootBlock [id=" + id + ", hash=" + hash + ", height=" + height + ", idPrevBlock=" + idPrevBlock
					+ ", hashPrevBlock=" + hashPrevBlock + ", nonce=" + nonce + ", hashMerkleRoot=" + hashMerkleRoot
					+ ", miner=" + miner + ", coinbase=" + coinbase + ", difficulty=" + difficulty + ", timestamp="
					+ timestamp + ", size=" + size + ", minorBlockHeaders=" + minorBlockHeaders + "]";
		}

		public String getSealHash() {
			return sealHash;
		}

		public void setSealHash(String sealHash) {
			this.sealHash = sealHash;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature(String signature) {
			this.signature = signature;
		}

	}

	public static class MinorBlockHeaderDeserialiser extends JsonDeserializer<List<MinorBlockHeader>> {

		private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

		@Override
		public List<MinorBlockHeader> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException {

			List<MinorBlockHeader> results = new ArrayList<>();
			JsonToken nextToken = jsonParser.nextToken();

			Iterator<MinorBlockHeader> iterator = objectReader.readValues(jsonParser, MinorBlockHeader.class);
			while (iterator.hasNext()) {
				results.add(iterator.next());
			}

			return results;
		}
	}

	public static class ResponseDeserialiser extends JsonDeserializer<RootBlock> {

		private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

		@Override
		public RootBlock deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException {
			if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
				return objectReader.readValue(jsonParser, RootBlock.class);
			} else {
				return null; // null is wrapped by Optional in above getter
			}
		}
	}
}
