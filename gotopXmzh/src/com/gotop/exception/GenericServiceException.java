package com.gotop.exception;


public class GenericServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenericServiceException (String message){
		super(message);
	}
}
