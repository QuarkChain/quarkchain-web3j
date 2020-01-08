package org.quarkchain.web3j.protocol.core.response;

import java.io.IOException;
import java.util.List;

import org.quarkchain.web3j.protocol.ObjectMapperFactory;
import org.quarkchain.web3j.protocol.core.Response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class GetAccountData extends Response<GetAccountData.AccountData> {

	public static class AccountData {
		private AccountDataInfo primary;
		private List<AccountDataInfo> shards;

		public AccountDataInfo getPrimary() {
			return primary;
		}

		public void setPrimary(AccountDataInfo primary) {
			this.primary = primary;
		}

		public List<AccountDataInfo> getShards() {
			return shards;
		}

		public void setShards(List<AccountDataInfo> shards) {
			this.shards = shards;
		}

		@Override
		public String toString() {
			return "AccountData [primary=" + primary + ", shards=" + shards + "]";
		}
	}

	public static class AccountDataInfo {
		private String fullShardId;
		private String shardId;
		private String chainId;
		private List<GetBalances.Balance> balances;
		private String transactionCount;
		private boolean isContract;
		private String minedBlocks;
		private String poswMineableBlocks;

		public String getMinedBlocks() {
			return minedBlocks;
		}

		public void setMinedBlocks(String minedBlocks) {
			this.minedBlocks = minedBlocks;
		}

		public String getPoswMineableBlocks() {
			return poswMineableBlocks;
		}

		public void setPoswMineableBlocks(String poswMineableBlocks) {
			this.poswMineableBlocks = poswMineableBlocks;
		}

		public String getFullShardId() {
			return fullShardId;
		}

		public void setFullShardId(String fullShardId) {
			this.fullShardId = fullShardId;
		}

		public String getShardId() {
			return shardId;
		}

		public void setShardId(String shardId) {
			this.shardId = shardId;
		}

		public String getChainId() {
			return chainId;
		}

		public void setChainId(String chainId) {
			this.chainId = chainId;
		}

		public List<GetBalances.Balance> getBalances() {
			return balances;
		}

		public void setBalances(List<GetBalances.Balance> balances) {
			this.balances = balances;
		}

		public String getTransactionCount() {
			return transactionCount;
		}

		public void setTransactionCount(String transactionCount) {
			this.transactionCount = transactionCount;
		}

		public boolean getIsContract() {
			return isContract;
		}

		public void setIsContract(boolean isContract) {
			this.isContract = isContract;
		}

		@Override
		public String toString() {
			return "AccountData [fullShardId=" + fullShardId + ", shardId=" + shardId + ", chainId=" + chainId
					+ ", balances=" + balances + ", transactionCount=" + transactionCount + ", isContract=" + isContract
					+ "]";
		}

	}

	@Override
	@JsonDeserialize(using = GetAccountData.ResponseDeserialiser.class)
	public void setResult(AccountData result) {
		super.setResult(result);
	}

	public AccountData getAccountData() {
		return getResult();
	}

	public static class ResponseDeserialiser extends JsonDeserializer<AccountData> {

		private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

		@Override
		public AccountData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException {
			if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
				return objectReader.readValue(jsonParser, AccountData.class);
			} else {
				return null; // null is wrapped by Optional in above getter
			}
		}
	}
}
