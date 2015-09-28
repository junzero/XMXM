package com.gotop.vo.report;

import java.lang.Integer;
import java.util.Date;;

/**
 * Trptdir entity. @author MyEclipse Persistence Tools
 */

public class Trptdir implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String rptdirname;
	private Date createdate;
	private String isvalid;
	private Integer userid;
	private String orgid;

	// Constructors

	/** default constructor */
	public Trptdir()
	{
	}

	/** minimal constructor */
	public Trptdir(Integer id)
	{
		this.id = id;
	}

	/** full constructor */
	public Trptdir(Integer id, String rptdirname, Date createdate,
			String isvalid, Integer userid, String orgid)
	{
		this.id = id;
		this.rptdirname = rptdirname;
		this.createdate = createdate;
		this.isvalid = isvalid;
		this.userid = userid;
		this.orgid = orgid;
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

	public String getRptdirname()
	{
		return this.rptdirname;
	}

	public void setRptdirname(String rptdirname)
	{
		this.rptdirname = rptdirname;
	}

	public Date getCreatedate()
	{
		return this.createdate;
	}

	public void setCreatedate(Date createdate)
	{
		this.createdate = createdate;
	}

	public String getIsvalid()
	{
		return this.isvalid;
	}

	public void setIsvalid(String isvalid)
	{
		this.isvalid = isvalid;
	}

	public Integer getUserid()
	{
		return this.userid;
	}

	public void setUserid(Integer userid)
	{
		this.userid = userid;
	}

	public String getOrgid()
	{
		return this.orgid;
	}

	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}

}