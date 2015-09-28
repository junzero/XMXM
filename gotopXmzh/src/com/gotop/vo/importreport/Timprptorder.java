package com.gotop.vo.importreport;

import java.lang.Integer;

/**
 * Timprptorder entity. @author MyEclipse Persistence Tools
 */

public class Timprptorder implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String orgid;
	private String tablename;
	private String fieldname;
	private Integer orderno;
	private String displayname;

	// Constructors

	/** default constructor */
	public Timprptorder()
	{
	}

	/** minimal constructor */
	public Timprptorder(Integer id)
	{
		this.id = id;
	}

	/** full constructor */
	public Timprptorder(Integer id, String orgid, String tablename,
			String fieldname, Integer orderno,String displayname)
	{
		this.id = id;
		this.orgid = orgid;
		this.tablename = tablename;
		this.fieldname = fieldname;
		this.orderno = orderno;
		this.displayname=displayname;
	}

	// Property accessors

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getOrgid()
	{
		return this.orgid;
	}

	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}

	public String getTablename()
	{
		return this.tablename;
	}

	public void setTablename(String tablename)
	{
		this.tablename = tablename;
	}

	public String getFieldname()
	{
		return this.fieldname;
	}

	public void setFieldname(String fieldname)
	{
		this.fieldname = fieldname;
	}

	public Integer getOrderno()
	{
		return this.orderno;
	}

	public void setOrderno(Integer orderno)
	{
		this.orderno = orderno;
	}

	public String getDisplayname()
	{
		return displayname;
	}

	public void setDisplayname(String displayname)
	{
		this.displayname = displayname;
	}

}