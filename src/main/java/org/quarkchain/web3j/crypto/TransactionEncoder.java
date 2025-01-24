package org.quarkchain.web3j.crypto;

import java.util.ArrayList;
import java.util.List;
import org.quarkchain.web3j.crypto.Sign.SignatureData;
import org.quarkchain.web3j.protocol.core.request.EvmTransaction;
import org.quarkchain.web3j.protocol.core.request.EvmTransaction.TxDataUnsigned;
import org.quarkchain.web3j.protocol.core.request.TxData;
import org.quarkchain.web3j.rlp.RlpEncoder;
import org.quarkchain.web3j.rlp.RlpList;
import org.quarkchain.web3j.rlp.RlpString;
import org.quarkchain.web3j.rlp.RlpType;
import org.quarkchain.web3j.rlp.RlpUint32;
import org.quarkchain.web3j.utils.Numeric;


public class TransactionEncoder {

	public static SignatureData signMessage(TxDataUnsigned rawTransaction, ECKeyPair keyPair) {
		byte[] encodedTransaction = encode(rawTransaction);
		Sign.SignatureData signatureData = Sign.signMessage(encodedTransaction, keyPair);

		return signatureData;
	}

	private static byte[] encode(TxDataUnsigned rawTransaction) {
		List<RlpType> values = asRlpValues(rawTransaction);
		RlpList rlpList = new RlpList(values);
		return RlpEncoder.encode(rlpList);
	}

	public static byte[] encode(EvmTransaction evmTransaction) {
		List<RlpType> values = asRlpValues(evmTransaction);
		RlpList rlpList = new RlpList(values);
		return RlpEncoder.encode(rlpList);
	}

	static List<RlpType> asRlpValues(TxDataUnsigned rawTransaction) {
		List<RlpType> result = new ArrayList<>();
		result.add(RlpString.create(rawTransaction.getNonce()));
		result.add(RlpString.create(rawTransaction.getGasPrice()));
		result.add(RlpString.create(rawTransaction.getGas()));

		// an empty to address (contract creation) should not be encoded as a numeric 0
		// value
		String to = rawTransaction.getTo();
		if (to.length() > 0) {
			result.add(RlpString.create(Numeric.toBigInt(to)));
		} else {
			result.add(RlpString.create(""));
		}

		result.add(RlpString.create(rawTransaction.getValue()));

		// value field will already be hex encoded, so we need to convert into binary
		// first
		result.add(RlpString.create(Numeric.hexStringToByteArray(rawTransaction.getData())));
		result.add(RlpString.create(Numeric.hexStringToByteArray(rawTransaction.getNetworkid())));
		result.add(RlpUint32.create(Numeric.toBigInt(rawTransaction.getFromFullShardKey())));
		result.add(RlpUint32.create(Numeric.toBigInt(rawTransaction.getToFullShardKey())));
		result.add(RlpString.create(Numeric.hexStringToByteArray(rawTransaction.getGasTokenID())));
		result.add(RlpString.create(Numeric.hexStringToByteArray(rawTransaction.getTransferTokenID())));
		return result;
	}

	private static List<RlpType> asRlpValues(EvmTransaction evmTransaction) {
		List<RlpType> result = new ArrayList<>();

		TxData txData = evmTransaction.getData();
		result.add(RlpString.create(txData.getNonce()));
		result.add(RlpString.create(txData.getGasPrice()));
		result.add(RlpString.create(txData.getGas()));
		// an empty to address (contract creation) should not be encoded as a numeric 0
		// value
		String to = txData.getTo();
        if (to != null && to.length() > 0) {
            result.add(RlpString.create(Numeric.hexStringToByteArray(to)));
        } else {
            result.add(RlpString.create(""));
        }
		result.add(RlpString.create(txData.getValue()));
		result.add(RlpString.create(Numeric.hexStringToByteArray(txData.getInput())));
		result.add(RlpString.create(Numeric.hexStringToByteArray(txData.getNetworkId())));
		result.add(RlpUint32.create(Numeric.toBigInt(txData.getFromFullShardKey())));
		result.add(RlpUint32.create(Numeric.toBigInt(txData.getToFullShardKey())));
		result.add(RlpString.create(Numeric.hexStringToByteArray(txData.getGasTokenId())));
		result.add(RlpString.create(Numeric.hexStringToByteArray(txData.getTransferTokenId())));
		result.add(RlpString.create(txData.getVersion()));
		result.add(RlpString.create(txData.getV()));
		result.add(RlpString.create(txData.getR()));
		result.add(RlpString.create(txData.getS()));
		return result;
	}

	public static List<RlpType> asRlpValuesPub(EvmTransaction evmTransaction) {
		return asRlpValues(evmTransaction);
	}
}
