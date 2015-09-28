package com.gotop.vo.report;

import java.math.BigDecimal;

/**
 * Tclassgroup entity. @author MyEclipse Persistence Tools
 */

public class Tclassgroup implements java.io.Serializable
{

	// Fields

	private Integer id;			//id
	private String groupname;	//分组名
	private String grouptblname;//组表
	private String grouptbldesc;//分组明细描述

	// Constructors

	/** default constructor */
	public Tclassgroup()
	{
	}

	public Tclassgroup(Integer id, String groupname, String grouptblname,
			String grouptbldesc)
	{
		super();
		this.id = id;
		this.groupname = groupname;
		this.grouptblname = grouptblname;
		this.grouptbldesc = grouptbldesc;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getGroupname()
	{
		return groupname;
	}

	public void setGroupname(String groupname)
	{
		this.groupname = groupname;
	}

	public String getGrouptblname()
	{
		return grouptblname;
	}

	public void setGrouptblname(String grouptblname)
	{
		this.grouptblname = grouptblname;
	}

	public String getGrouptbldesc()
	{
		return grouptbldesc;
	}

	public void setGrouptbldesc(String grouptbldesc)
	{
		this.grouptbldesc = grouptbldesc;
	}


}