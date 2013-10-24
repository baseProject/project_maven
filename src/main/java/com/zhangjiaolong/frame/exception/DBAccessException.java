package com.zhangjiaolong.frame.exception;

public class DBAccessException extends Exception {

	private static final long serialVersionUID = -8050527137532285876L;

	public DBAccessException() {
	}

	public DBAccessException(String message) {
		super(message);
	}

	public DBAccessException(Throwable cause) {
		super(cause);
	}

	public DBAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
