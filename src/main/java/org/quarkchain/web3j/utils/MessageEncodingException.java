package org.quarkchain.web3j.utils;

public class MessageEncodingException extends RuntimeException {
	private static final long serialVersionUID = -7483374186204138819L;

	public MessageEncodingException(String message) {
		super(message);
	}

	public MessageEncodingException(String message, Throwable cause) {
		super(message, cause);
	}
}
