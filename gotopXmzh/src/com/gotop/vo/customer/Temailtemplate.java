package com.gotop.vo.customer;

import java.lang.Integer;
import java.util.Date;

/**
 * Temailtemplate entity. @author MyEclipse Persistence Tools
 */

public class Temailtemplate implements java.io.Serializable
{

	// Fields

	private Integer id;			//id
	private String orgid;		//组织机构id
	private String tempname;	//模板名称
	private String tempremark;	//模板备注
	private String tmpcontent;	//模板内容
	private String datastate;	//数据状态
	private Integer createrid;	//创建人
	private Date createdate;	//创建日期

	// Constructors

	/** default constructor */
	public Temailtemplate()
	{
	}

	/** minimal constructor */
	public Temailtemplate(Integer id)
	{
		this.id = id;
	}

	/** full constructor */
	public Temailtemplate(Integer id, String orgid, String tempname,
			String tempremark, String tmpcontent, String datastate,
			Integer createrid, Date createdate)
	{
		this.id = id;
		this.orgid = orgid;
		this.tempname = tempname;
		this.tempremark = tempremark;
		this.tmpcontent = tmpcontent;
		this.datastate = datastate;
		this.createrid = createrid;
		this.createdate = createdate;
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

	public String getTempname()
	{
		return this.tempname;
	}

	public void setTempname(String tempname)
	{
		this.tempname = tempname;
	}

	public String getTempremark()
	{
		return this.tempremark;
	}

	public void setTempremark(String tempremark)
	{
		this.tempremark = tempremark;
	}

	public String getTmpcontent()
	{
		return this.tmpcontent;
	}

	public void setTmpcontent(String tmpcontent)
	{
		this.tmpcontent = tmpcontent;
	}

	public String getDatastate()
	{
		return this.datastate;
	}

	public void setDatastate(String datastate)
	{
		this.datastate = datastate;
	}

	public Integer getCreaterid()
	{
		return this.createrid;
	}

	public void setCreaterid(Integer createrid)
	{
		this.createrid = createrid;
	}

	public Date getCreatedate()
	{
		return this.createdate;
	}

	public void setCreatedate(Date createdate)
	{
		this.createdate = createdate;
	}

}