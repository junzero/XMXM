package com.gotop.util.report;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("unchecked")
public class RequestMapUtil implements Serializable
{
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request=null;
	
	private Map paramMap=null;
	private Object[] oArr=null;
	public RequestMapUtil(HttpServletRequest request)
	{
		super();
		this.request = request;
	}
	public Object[] getObjValueList(String key)
	{
		paramMap=request.getParameterMap();
		Object[] objs=(Object[])paramMap.get(key);
		oArr =null;
		if(null != objs && objs.length > 0)
		{
			int maxIndex=objs.length-1;
			oArr =(Object[]) objs[maxIndex];
		}
		return oArr;
	}
	public Object getObjValue(String key)
	{
		Object obj=null;
		paramMap=request.getParameterMap();
		oArr =null;
		oArr = (Object[])paramMap.get(key);
		if(null != oArr && oArr.length > 0)
		{
			int maxIndex=oArr.length-1;
			obj=oArr[maxIndex];
		}
		return obj;
	}
	public String getStrValue(String key)
	{
		String str=null;
		paramMap=request.getParameterMap();
		oArr =null;
		oArr = (Object[])paramMap.get(key);
		if(null != oArr && oArr.length > 0)
		{
			int maxIndex=oArr.length-1;
			str=oArr[maxIndex].toString();
		}
		return str;
	}
	public double getDblNumValue(String key)
	{
		double num=0.0f;
		paramMap=request.getParameterMap();
		oArr =null;
		oArr = (Object[])paramMap.get(key);
		if(null != oArr && oArr.length > 0)
		{
			int maxIndex=oArr.length-1;
			num=Double.valueOf(oArr[maxIndex].toString());
		}
		return num;
	}
	
	public int getIntNumValue(String key)
	{
		int num=0;
		paramMap=request.getParameterMap();
		oArr =null;
		oArr = (Object[])paramMap.get(key);
		if(null != oArr && oArr.length > 0)
		{
			int maxIndex=oArr.length-1;
			num=Integer.valueOf(oArr[maxIndex].toString());
		}
		return num;
	}
}
