package com.smoothai.smoothai.storage;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = -2167380214631881453L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
