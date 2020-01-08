package org.quarkchain.web3j.protocol.core.response;

import org.quarkchain.web3j.protocol.core.Response;

/**
 * eth_sendTransaction
 */
public class SendTransaction extends Response<String> {
    public String getTransactionID() {
        return getResult();
    }
}
