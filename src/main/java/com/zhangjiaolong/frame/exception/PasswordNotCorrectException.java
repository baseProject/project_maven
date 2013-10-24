package com.zhangjiaolong.frame.exception;

public class PasswordNotCorrectException extends Exception {

	private static final long serialVersionUID = -673981269957579443L;

	public PasswordNotCorrectException() {
	}
	
	public PasswordNotCorrectException(Throwable cause) {
		super(cause);
	}
	
	public PasswordNotCorrectException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PasswordNotCorrectException(String message) {
		super(message);
	}
	
}
