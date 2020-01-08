package org.quarkchain.web3j.protocol.core.response;

import org.quarkchain.web3j.protocol.core.Response;

/**
 * eth_sendRawTransaction
 */
public class SendRawTransaction extends Response<String> {
    public String getTransactionHash() {
        return getResult();
    }
}
