package com.gotop.vo.report;

import java.io.Serializable;

public class RptXtdmObj implements Serializable
{
	private String sm;
	private String zfdm;
	public RptXtdmObj()
	{
		super();
	}
	public RptXtdmObj(String sm, String zfdm)
	{
		super();
		this.sm = sm;
		this.zfdm = zfdm;
	}
	public String getSm()
	{
		return sm;
	}
	public void setSm(String sm)
	{
		this.sm = sm;
	}
	public String getZfdm()
	{
		return zfdm;
	}
	public void setZfdm(String zfdm)
	{
		this.zfdm = zfdm;
	}
	
}
