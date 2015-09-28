package com.gotop.vo.report;

import java.io.Serializable;

public class TrptRelation implements Serializable
{
	private Integer  id;
	private Integer  parentid;		//主表id
	private String   field;			//字段名
	private String   fieldnamech;	//字段中文名
	private String   tblname;		//表名
	private String   isgroupfield;	//是否为分组字段
	public TrptRelation()
	{
		super();
	}
	

	public TrptRelation(Integer id, Integer parentid, String field,
			String fieldnamech, String tblname, String isgroupfield)
	{
		super();
		this.id = id;
		this.parentid = parentid;
		this.field = field;
		this.fieldnamech = fieldnamech;
		this.tblname = tblname;
		this.isgroupfield = isgroupfield;
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


	public String getField()
	{
		return field;
	}


	public void setField(String field)
	{
		this.field = field;
	}


	public String getFieldnamech()
	{
		return fieldnamech;
	}


	public void setFieldnamech(String fieldnamech)
	{
		this.fieldnamech = fieldnamech;
	}


	public String getTblname()
	{
		return tblname;
	}


	public void setTblname(String tblname)
	{
		this.tblname = tblname;
	}


	public String getIsgroupfield()
	{
		return isgroupfield;
	}


	public void setIsgroupfield(String isgroupfield)
	{
		this.isgroupfield = isgroupfield;
	}



}
