package com.zhangjiaolong.frame.exception;

public class EmployeeNotExistException extends Exception {

	private static final long serialVersionUID = -7906432764056459971L;

	public EmployeeNotExistException() {
	}

	public EmployeeNotExistException(String message) {
		super(message);
	}

	public EmployeeNotExistException(Throwable cause) {
		super(cause);
	}
	
	public EmployeeNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
