package com.gotop.exception.customizeException;

public class CustomizeException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomizeException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CustomizeException(Throwable cause)
	{
		super(cause);
	}

	public CustomizeException()
	{
		super();
	}

	public CustomizeException(String exp)
	{
		super(exp);
	}
}
