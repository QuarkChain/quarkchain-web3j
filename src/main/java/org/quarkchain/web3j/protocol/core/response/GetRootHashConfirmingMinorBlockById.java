package org.quarkchain.web3j.protocol.core.response;

import org.quarkchain.web3j.protocol.core.Response;

public class GetRootHashConfirmingMinorBlockById extends Response<String> {

	public String getRootHash() {
		return getResult();
	}
}
