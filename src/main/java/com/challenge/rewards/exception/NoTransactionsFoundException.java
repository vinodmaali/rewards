package com.challenge.rewards.exception;

public class NoTransactionsFoundException extends Exception {
	String message;
	String details;

	private static final long serialVersionUID = 1L;

	public NoTransactionsFoundException() {
		super();
	}

	public NoTransactionsFoundException(String msg) {
		this.message = msg;
	}

	public NoTransactionsFoundException(String msg, String details) {
		this.message = msg;
		this.details = details;
	}
}
