package org.quarkchain.web3j.protocol.core.response;

import java.math.BigInteger;

import org.quarkchain.web3j.protocol.core.Response;
import org.quarkchain.web3j.utils.Numeric;

/**
 * eth_estimateGas
 */
public class EstimateGas extends Response<String> {
    public BigInteger getAmountUsed() {
        return Numeric.decodeQuantity(getResult());
    }
}
