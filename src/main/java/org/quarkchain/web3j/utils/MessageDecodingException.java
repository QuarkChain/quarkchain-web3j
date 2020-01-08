package org.quarkchain.web3j.utils;

public class MessageDecodingException extends RuntimeException {
	private static final long serialVersionUID = 7800272221719886831L;

	public MessageDecodingException(String message) {
		super(message);
	}

	public MessageDecodingException(String message, Throwable cause) {
		super(message, cause);
	}
}
