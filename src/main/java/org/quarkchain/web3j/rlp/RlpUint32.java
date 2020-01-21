package org.quarkchain.web3j.rlp;

import java.math.BigInteger;

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
		result[1] = (byte)(this.value >> 24);
		result[2] = (byte)(this.value >> 16);
		result[3] = (byte)(this.value >> 8);
		result[4] = (byte)(this.value);  
		return result;
	}

	public static RlpType create(BigInteger value) {
		// RLP encoding only supports positive integer values
		if (value.signum() < 1) {
			return new RlpUint32(0);
		} else {
			return new RlpUint32(value.intValue() & 0x0FFFFFFFF);
		}
	}

}
