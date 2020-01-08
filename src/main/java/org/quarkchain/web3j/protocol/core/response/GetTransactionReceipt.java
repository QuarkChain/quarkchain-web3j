package org.quarkchain.web3j.protocol.core.response;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectReader;

import org.quarkchain.web3j.protocol.ObjectMapperFactory;
import org.quarkchain.web3j.protocol.core.Response;
import org.quarkchain.web3j.utils.Numeric;

/**
 * eth_getTransactionReceipt
 */
public class GetTransactionReceipt extends Response<GetTransactionReceipt.TransactionReceipt> {

	public Optional<TransactionReceipt> getTransactionReceipt() {
		return Optional.ofNullable(getResult());
	}

	public static class TransactionReceipt {
		private String transactionId;
		private String transactionHash;
		private String transactionIndex;
		private String blockId;
		private String blockHash;
		private String blockHeight;
		private String blockNumber;
		private String cumulativeGasUsed;
		private String gasUsed;
		private String status;
		private String timestamp;
		private String contractAddress; // this is present in the spec
		private List<Log> logs;

		public TransactionReceipt() {
		}
 
		public String getTransactionHash() {
			return transactionHash;
		}

		public void setTransactionHash(String transactionHash) {
			this.transactionHash = transactionHash;
		}

		public BigInteger getTransactionIndex() {
			return Numeric.decodeQuantity(transactionIndex);
		}

		public void setTransactionIndex(String transactionIndex) {
			this.transactionIndex = transactionIndex;
		}

		public String getBlockHash() {
			return blockHash;
		}

		public void setBlockHash(String blockHash) {
			this.blockHash = blockHash;
		}

		public BigInteger getBlockNumber() {
			return Numeric.decodeQuantity(blockNumber);
		}

		public void setBlockNumber(String blockNumber) {
			this.blockNumber = blockNumber;
		}

		public BigInteger getCumulativeGasUsed() {
			return Numeric.decodeQuantity(cumulativeGasUsed);
		}

		public void setCumulativeGasUsed(String cumulativeGasUsed) {
			this.cumulativeGasUsed = cumulativeGasUsed;
		}

		public BigInteger getGasUsed() {
			return Numeric.decodeQuantity(gasUsed);
		}

		public void setGasUsed(String gasUsed) {
			this.gasUsed = gasUsed;
		}

		public Optional<String> getContractAddress() {
			return Optional.ofNullable(contractAddress);
		}

		public void setContractAddress(String contractAddress) {
			this.contractAddress = contractAddress;
		}

		public List<Log> getLogs() {
			return logs;
		}

		public void setLogs(List<Log> logs) {
			this.logs = logs;
		}

		public String getBlockHeight() {
			return blockHeight;
		}

		public void setBlockHeight(String blockHeight) {
			this.blockHeight = blockHeight;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((blockHash == null) ? 0 : blockHash.hashCode());
			result = prime * result + ((blockHeight == null) ? 0 : blockHeight.hashCode());
			result = prime * result + ((blockId == null) ? 0 : blockId.hashCode());
			result = prime * result + ((blockNumber == null) ? 0 : blockNumber.hashCode());
			result = prime * result + ((contractAddress == null) ? 0 : contractAddress.hashCode());
			result = prime * result + ((cumulativeGasUsed == null) ? 0 : cumulativeGasUsed.hashCode());
			result = prime * result + ((gasUsed == null) ? 0 : gasUsed.hashCode());
			result = prime * result + ((logs == null) ? 0 : logs.hashCode());
			result = prime * result + ((status == null) ? 0 : status.hashCode());
			result = prime * result + ((transactionHash == null) ? 0 : transactionHash.hashCode());
			result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
			result = prime * result + ((transactionIndex == null) ? 0 : transactionIndex.hashCode());
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
			TransactionReceipt other = (TransactionReceipt) obj;
			if (blockHash == null) {
				if (other.blockHash != null)
					return false;
			} else if (!blockHash.equals(other.blockHash))
				return false;
			if (blockHeight == null) {
				if (other.blockHeight != null)
					return false;
			} else if (!blockHeight.equals(other.blockHeight))
				return false;
			if (blockId == null) {
				if (other.blockId != null)
					return false;
			} else if (!blockId.equals(other.blockId))
				return false;
			if (blockNumber == null) {
				if (other.blockNumber != null)
					return false;
			} else if (!blockNumber.equals(other.blockNumber))
				return false;
			if (contractAddress == null) {
				if (other.contractAddress != null)
					return false;
			} else if (!contractAddress.equals(other.contractAddress))
				return false;
			if (cumulativeGasUsed == null) {
				if (other.cumulativeGasUsed != null)
					return false;
			} else if (!cumulativeGasUsed.equals(other.cumulativeGasUsed))
				return false;
			if (gasUsed == null) {
				if (other.gasUsed != null)
					return false;
			} else if (!gasUsed.equals(other.gasUsed))
				return false;
			if (logs == null) {
				if (other.logs != null)
					return false;
			} else if (!logs.equals(other.logs))
				return false;
			if (status == null) {
				if (other.status != null)
					return false;
			} else if (!status.equals(other.status))
				return false;
			if (transactionHash == null) {
				if (other.transactionHash != null)
					return false;
			} else if (!transactionHash.equals(other.transactionHash))
				return false;
			if (transactionId == null) {
				if (other.transactionId != null)
					return false;
			} else if (!transactionId.equals(other.transactionId))
				return false;
			if (transactionIndex == null) {
				if (other.transactionIndex != null)
					return false;
			} else if (!transactionIndex.equals(other.transactionIndex))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "TransactionReceipt [transactionId=" + transactionId + ", transactionHash=" + transactionHash
					+ ", transactionIndex=" + transactionIndex + ", blockId=" + blockId + ", blockHash=" + blockHash
					+ ", blockHeight=" + blockHeight + ", blockNumber=" + blockNumber + ", cumulativeGasUsed="
					+ cumulativeGasUsed + ", gasUsed=" + gasUsed + ", status=" + status + ", contractAddress="
					+ contractAddress + ", logs=" + logs + "]";
		}

		public String getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		public String getBlockId() {
			return blockId;
		}

		public void setBlockId(String blockId) {
			this.blockId = blockId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public static class ResponseDeserialiser extends JsonDeserializer<TransactionReceipt> {

			private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

			@Override
			public TransactionReceipt deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
					throws IOException {
				if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
					return objectReader.readValue(jsonParser, TransactionReceipt.class);
				} else {
					return null; // null is wrapped by Optional in above getter
				}
			}
		}
	}
}
