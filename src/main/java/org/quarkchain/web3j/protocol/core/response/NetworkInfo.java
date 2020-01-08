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
 * networkInfo
 */
public class NetworkInfo extends Response<NetworkInfo.Info> {

    @Override
    @JsonDeserialize(using = NetworkInfo.ResponseDeserialiser.class)
    public void setResult(Info result) {
        super.setResult(result);
    }

    public Optional<Info> getInfo() {
        return Optional.ofNullable(getResult());
    }

    public static class Info {
		@Override
		public String toString() {
			return "Info [networkId=" + networkId + ", chainSize=" + chainSize + ", syncing=" + syncing + ", mining="
					+ mining + ", shardServerCount=" + shardServerCount + ", shardSizes=" + shardSizes + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((chainSize == null) ? 0 : chainSize.hashCode());
			result = prime * result + ((mining == null) ? 0 : mining.hashCode());
			result = prime * result + ((networkId == null) ? 0 : networkId.hashCode());
			result = prime * result + ((shardServerCount == null) ? 0 : shardServerCount.hashCode());
			result = prime * result + ((shardSizes == null) ? 0 : shardSizes.hashCode());
			result = prime * result + ((syncing == null) ? 0 : syncing.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Info other = (Info) obj;
			if (chainSize == null) {
				if (other.chainSize != null)
					return false;
			} else if (!chainSize.equals(other.chainSize))
				return false;
			if (mining == null) {
				if (other.mining != null)
					return false;
			} else if (!mining.equals(other.mining))
				return false;
			if (networkId == null) {
				if (other.networkId != null)
					return false;
			} else if (!networkId.equals(other.networkId))
				return false;
			if (shardServerCount == null) {
				if (other.shardServerCount != null)
					return false;
			} else if (!shardServerCount.equals(other.shardServerCount))
				return false;
			if (shardSizes == null) {
				if (other.shardSizes != null)
					return false;
			} else if (!shardSizes.equals(other.shardSizes))
				return false;
			if (syncing == null) {
				if (other.syncing != null)
					return false;
			} else if (!syncing.equals(other.syncing))
				return false;
			return true;
		}
		public String getNetworkId() {
			return networkId;
		}
		public void setNetworkId(String networkId) {
			this.networkId = networkId;
		}
		public String getChainSize() {
			return chainSize;
		}
		public void setChainSize(String chainSize) {
			this.chainSize = chainSize;
		}
		public String getSyncing() {
			return syncing;
		}
		public void setSyncing(String syncing) {
			this.syncing = syncing;
		}
		public String getMining() {
			return mining;
		}
		public void setMining(String mining) {
			this.mining = mining;
		}
		public String getShardServerCount() {
			return shardServerCount;
		}
		public void setShardServerCount(String shardServerCount) {
			this.shardServerCount = shardServerCount;
		}
		public List<String> getShardSizes() {
			return shardSizes;
		}
		public void setShardSizes(List<String> shardSizes) {
			this.shardSizes = shardSizes;
		}
		private String networkId;
    	private String chainSize;
    	private String syncing;
    	private String mining;
    	private String shardServerCount;   
    	private List<String>shardSizes; 
    }
    public Info networkInfo() {
        return getResult();
    }


    public static class ResponseDeserialiser extends JsonDeserializer<Info> {

        private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

        @Override
        public Info deserialize(
                JsonParser jsonParser,
                DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                return objectReader.readValue(jsonParser, Info.class);
            } else {
                return null;  // null is wrapped by Optional in above getter
            }
        }
    }
}
