package org.quarkchain.web3j.protocol.core.response;

import org.quarkchain.web3j.protocol.core.Response;

public class Call extends Response<String> {
	public String getValue() {
		return getResult();
	}
}
