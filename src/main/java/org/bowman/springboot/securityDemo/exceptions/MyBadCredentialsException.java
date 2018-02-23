package org.bowman.springboot.securityDemo.exceptions;

public class MyBadCredentialsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MyBadCredentialsException(String message) {
		super(message);
	}
}
