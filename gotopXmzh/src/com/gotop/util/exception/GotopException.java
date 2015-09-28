/**
 * 
 */
package com.gotop.util.exception;

/**
 * 
 * 
 * 类说明 :GOTOP通用异常描述类 .
 * 用于GOTOP平台的异常描述
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2012-9-14      张怡       新建
 * 2012-10-11      林钟       修改
 * </pre>
 */
public class GotopException extends Exception {
	
	private static final long serialVersionUID = -7747735541672165271L;
	
	/**
	 * 普通异常
	 */
	public static final int ERROR_NORMAL = -1;
	
	/**
	 * 特殊异常
	 */
	public static final int ERROR_SPECIAL = -2;
	
	/**
	 * 业务特殊异常
	 */
	public static final int ERROR_BUSI_SPECIAL = 99;
	
	/**
	 * 错误代码说明 -2:错误(特殊情况) -1:错误(正常情况) 1:警告(正常情况) 2:警告(特殊情况) 9:其它错误
	 */
	private int errCode = ERROR_NORMAL;
	
	/**
	 * 1、是跳转到统一页面显示异常，2、自定义的input页面。无论ajax还是action 默认均留在当前页面
	 */
	private int forward;

  /**
    *
    * 构造器
    * @param errMsg 错误信息
    *
    * <pre>
    * 修改日期      修改人    修改原因
    * 2010-3-13    陈建榕                新建
    * </pre>
    */
	public GotopException(String errMsg) {
		super(errMsg);
	}

	/**
    *
    * 构造器
    * @param errCode 错误代码
    * @param errMsg 错误信息
    * 
    * <pre>
    * 修改日期      修改人    修改原因
    * 2010-3-13    陈建榕                新建
    * </pre>
    */
	public GotopException(int errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
	}
	/**
	 *
	 * 构造器
	 * @param errMsg 错误信息
	 * @param forward 0：不跳转到，在当前页显示异常； 1：跳转，在统一异常页面显示
	 * 
	 * <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-10-11    林钟       新建
	 * </pre>
	 */
	public GotopException(String errMsg,int forward) {
		super(errMsg);
		this.forward = forward;
	}

	/**
    *
    * 构造器
    * @param th 原始异常
    * 
    * <pre>
    * 修改日期      修改人    修改原因
    * 2010-3-13    陈建榕                新建
    * </pre>
    */
	public GotopException(Throwable th) {
		super(th);
	}
	/**
	 *
	 * 构造器
	 * @param th 原始异常
	 * @param forward 0：不跳转到，在当前页显示异常； 1：跳转，在统一异常页面显示
	 * 
	 * <pre>
	 * 修改日期      修改人    修改原因
	 * 2010-10-11    林钟       新建
	 * </pre>
	 */
	public GotopException(Throwable th,int forward) {
		super(th);
		this.forward = forward;
	}

	/**
	 * 错误代码
	 * @return 错误代码 
	 */
	public int getErrCode() {
		return errCode;
	}

	/**
	 * 错误代码
	 * @param 错误代码
	 */
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	/**
	 * 是否跳转到统一异常页面
	 * @return
	 */
	public int getForward() {
		return forward;
	}
	/**
	 * 是否跳转到统一异常页面,1:跳转。其它不跳转
	 * @param forward
	 */
	public void setForward(int forward) {
		this.forward = forward;
	}
}
