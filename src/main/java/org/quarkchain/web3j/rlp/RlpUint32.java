package org.quarkchain.web3j.rlp;

import java.math.BigInteger;
import java.util.Arrays;

public class RlpUint32 implements RlpType {
	private static final int UINT32_OFFSET = 0x84;
	private static final int UINT32_LENGTH = 5;

	private final int value;

	private RlpUint32(int value) {
		this.value = value;
	}

	public byte[] encode() {
		byte[] result = new byte[UINT32_LENGTH];
		result[0] = (byte) (UINT32_OFFSET);
		BigInteger bigInt = BigInteger.valueOf(this.value);
		byte[] bytesValue = bigInt.toByteArray();
		System.arraycopy(bytesValue, 0, result, 1, bytesValue.length);
		return result;
	}

	public static RlpType create(BigInteger value) {
		// RLP encoding only supports positive integer values
		if (value.signum() < 1) {
			return new RlpUint32(0);
		} else {
			return new RlpUint32(value.intValue());
		}
	}

}
