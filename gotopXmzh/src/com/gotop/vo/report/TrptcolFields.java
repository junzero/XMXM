package com.gotop.vo.report;

import java.io.Serializable;

public class TrptcolFields implements Serializable
{
	private Integer  id;
	private Integer  parentid;	//主表id
	private String   tblname;	//表名
	private String   colfield;	//统计字段
	private String   coltype;	//统计类型（sum,avg,min,max）
	private TrptCustom trptCustom;
	public TrptcolFields()
	{
		super();
	}
	
	public TrptcolFields(Integer id, Integer parentid, String tblname,
			String colfield, String coltype, TrptCustom trptCustom)
	{
		super();
		this.id = id;
		this.parentid = parentid;
		this.tblname = tblname;
		this.colfield = colfield;
		this.coltype = coltype;
		this.trptCustom = trptCustom;
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

	public String getTblname()
	{
		return tblname;
	}

	public void setTblname(String tblname)
	{
		this.tblname = tblname;
	}

	public String getColfield()
	{
		return colfield;
	}

	public void setColfield(String colfield)
	{
		this.colfield = colfield;
	}

	public String getColtype()
	{
		return coltype;
	}

	public void setColtype(String coltype)
	{
		this.coltype = coltype;
	}

	public TrptCustom getTrptCustom()
	{
		return trptCustom;
	}

	public void setTrptCustom(TrptCustom trptCustom)
	{
		this.trptCustom = trptCustom;
	}


	
}
