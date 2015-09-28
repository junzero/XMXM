package com.gotop.vo.report;

import java.io.Serializable;

public class TrptOrderby implements Serializable
{
	private Integer	 id;
	private Integer  parentid;	//主表id
	private String   orderfield;//排序字段
	private String   tblname;	//表名
	private String   ordertype;	//排序类型
	public TrptOrderby()
	{
		super();
	}
	public TrptOrderby(Integer id, Integer parentid, String orderfield,
			String tblname, String ordertype)
	{
		super();
		this.id = id;
		this.parentid = parentid;
		this.orderfield = orderfield;
		this.tblname = tblname;
		this.ordertype = ordertype;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getParentid()
	{
		return parentid;
	}
	public void setParentid(Integer parentid)
	{
		this.parentid = parentid;
	}
	public String getOrderfield()
	{
		return orderfield;
	}
	public void setOrderfield(String orderfield)
	{
		this.orderfield = orderfield;
	}
	public String getTblname()
	{
		return tblname;
	}
	public void setTblname(String tblname)
	{
		this.tblname = tblname;
	}
	public String getOrdertype()
	{
		return ordertype;
	}
	public void setOrdertype(String ordertype)
	{
		this.ordertype = ordertype;
	}



}
