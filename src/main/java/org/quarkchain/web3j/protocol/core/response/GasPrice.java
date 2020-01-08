package org.quarkchain.web3j.protocol.core.response;

import java.math.BigInteger;

import org.quarkchain.web3j.protocol.core.Response;
import org.quarkchain.web3j.utils.Numeric;

/**
 * eth_gasPrice
 */
public class GasPrice extends Response<String> {
    public BigInteger getGasPrice() {
        return Numeric.decodeQuantity(getResult());
    }
}
