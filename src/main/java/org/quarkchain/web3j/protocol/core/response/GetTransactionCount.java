package org.quarkchain.web3j.protocol.core.response;

import java.math.BigInteger;

import org.quarkchain.web3j.protocol.core.Response;
import org.quarkchain.web3j.utils.Numeric;

/**
 * eth_getTransactionCount
 */
public class GetTransactionCount extends Response<String> {
    public BigInteger getTransactionCount() {
        return Numeric.decodeQuantity(getResult());
    }
}
