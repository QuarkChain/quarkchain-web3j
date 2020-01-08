package org.quarkchain.web3j.protocol.core.response;

import java.io.IOException;
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

/**
 * eth_getBalance
 */
public class GetBalances extends Response<GetBalances.BalanceInfo> {
	public static class Balance {
		private String tokenId;
		private String tokenStr;
		private String balance;

		public String getTokenId() {
			return tokenId;
		}

		public void setTokenId(String tokenId) {
			this.tokenId = tokenId;
		}

		public String getTokenStr() {
			return tokenStr;
		}

		public void setTokenStr(String tokenStr) {
			this.tokenStr = tokenStr;
		}

		public String getBalance() {
			return balance;
		}

		public void setBalance(String balance) {
			this.balance = balance;
		}

		@Override
		public String toString() {
			return "Balance [tokenId=" + tokenId + ", tokenStr=" + tokenStr + ", balance=" + balance + "]";
		}

	}

	public static class BalanceInfo {
		private String branch;
		private String fullShardId;
		private String shardId;
		private String chainId;
		private List<Balance> balances;

		public String getBranch() {
			return branch;
		}

		public void setBranch(String branch) {
			this.branch = branch;
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

		public List<Balance> getBalances() {
			return balances;
		}

		public void setBalances(List<Balance> balances) {
			this.balances = balances;
		}

		@Override
		public String toString() {
			return "BalanceInfo [branch=" + branch + ", fullShardId=" + fullShardId + ", shardId=" + shardId
					+ ", chainId=" + chainId + ", balances=" + balances + "]";
		}

	}

	@Override
	@JsonDeserialize(using = GetBalances.ResponseDeserialiser.class)
	public void setResult(BalanceInfo result) {
		super.setResult(result);
	}

//	public Optional<BalanceInfo> getBalanceInfo() {
//		return Optional.ofNullable(getResult());
//	}

	public BalanceInfo getBalances() {
		return getResult();
	}

	public static class ResponseDeserialiser extends JsonDeserializer<BalanceInfo> {

		private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

		@Override
		public BalanceInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException {
			if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
				return objectReader.readValue(jsonParser, BalanceInfo.class);
			} else {
				return null; // null is wrapped by Optional in above getter
			}
		}
	}
}
