package com.gotop.util.exception;

/**
 * <p>Title:DAO异常类 </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author phc
 *
 * @date Jul 20, 2009
 *
 * @version 1.0
 **/
public class DaoException extends Exception {
	
	private static final long serialVersionUID = -4207809664398966737L;

	public DaoException(){
	}
	
	public DaoException(String msg){
		super(msg);
	}
	
	public DaoException(Throwable cause){
		super(cause);
	}

	public DaoException(String msg, Throwable cause){
		super(msg, cause);
	}
}
