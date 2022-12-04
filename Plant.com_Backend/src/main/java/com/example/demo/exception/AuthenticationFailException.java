package com.example.demo.exception;

public class AuthenticationFailException extends IllegalArgumentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationFailException(String msg) {
		super(msg);
	}
}
