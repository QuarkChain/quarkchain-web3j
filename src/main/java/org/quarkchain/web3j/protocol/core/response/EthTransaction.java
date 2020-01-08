package org.quarkchain.web3j.protocol.core.response;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectReader;

import org.quarkchain.web3j.protocol.ObjectMapperFactory;
import org.quarkchain.web3j.protocol.core.Response;

/**
 * Transaction object returned by:
 * <ul>
 * <li>eth_getTransactionByHash</li>
 * <li>eth_getTransactionByBlockHashAndIndex</li>
 * <li>eth_getTransactionByBlockNumberAndIndex</li>
 * </ul>
 *
 * <p>This differs slightly from the request {@link SendTransaction} Transaction object.</p>
 *
 * <p>See
 * <a href="https://github.com/ethereum/wiki/wiki/JSON-RPC#eth_gettransactionbyhash">docs</a>
 * for further details.</p>
 */
public class EthTransaction extends Response<TransactionRes> {

    public Optional<TransactionRes> getTransaction() {
        return Optional.ofNullable(getResult());
    }

    public static class ResponseDeserialiser extends JsonDeserializer<TransactionRes> {

        private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

        @Override
        public TransactionRes deserialize(
                JsonParser jsonParser,
                DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                return objectReader.readValue(jsonParser, TransactionRes.class);
            } else {
                return null;  // null is wrapped by Optional in above getter
            }
        }
    }
}
