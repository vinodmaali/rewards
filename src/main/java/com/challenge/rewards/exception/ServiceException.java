package com.challenge.rewards.exception;

public class ServiceException extends Exception {

	String message;
	String details;
	
	private static final long serialVersionUID = 1L;

	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String msg){
		this.message=msg;
	}
	
	public ServiceException(String msg,String details){
		this.message=msg;
		this.details= details;
	}
}
