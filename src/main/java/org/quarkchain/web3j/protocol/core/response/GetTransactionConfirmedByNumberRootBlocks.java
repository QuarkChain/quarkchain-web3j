package org.quarkchain.web3j.protocol.core.response;

import org.quarkchain.web3j.protocol.core.Response;
import org.quarkchain.web3j.utils.Numeric;

public class GetTransactionConfirmedByNumberRootBlocks extends Response<String> {
	public int getTxCount() {
		String result = getResult();
		if (result == null) {
			return -1;
		}
		return Numeric.decodeQuantity(result).intValueExact();
	}
}
