package com.gotop.vo.timetask;

import java.lang.Integer;
import java.util.Date;

/**
 * Texectask entity. @author MyEclipse Persistence Tools
 */

public class Texectask implements java.io.Serializable
{

	// Fields

	private Integer id;			//id
	private Integer foreignid;	//主表id
	private Date exectime;		//执行时间
	private String isexec;			//是否已经执行
	private String execstate;		//执行状态
	private String sendtype;	//发送类型
	private Integer contactid;	//联系人id
	private String targetaddr;	//发送地址

	// Constructors

	/** default constructor */
	public Texectask()
	{
	}

	/** minimal constructor */
	public Texectask(Integer id, Integer foreignid)
	{
		this.id = id;
		this.foreignid = foreignid;
	}
	/** full constructor */
	public Texectask(Integer id, Integer foreignid, Date exectime,
			String isexec, String execstate, String sendtype, Integer contactid, String targetaddr)
	{
		super();
		this.id = id;
		this.foreignid = foreignid;
		this.exectime = exectime;
		this.isexec = isexec;
		this.execstate = execstate;
		this.sendtype = sendtype;
		this.contactid=contactid;
		this.targetaddr = targetaddr;
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

	public Integer getForeignid()
	{
		return this.foreignid;
	}

	public void setForeignid(Integer foreignid)
	{
		this.foreignid = foreignid;
	}

	public Date getExectime()
	{
		return this.exectime;
	}

	public void setExectime(Date exectime)
	{
		this.exectime = exectime;
	}

	public String getIsexec()
	{
		return this.isexec;
	}

	public void setIsexec(String isexec)
	{
		this.isexec = isexec;
	}

	public String getExecstate()
	{
		return this.execstate;
	}

	public void setExecstate(String execstate)
	{
		this.execstate = execstate;
	}

	public String getSendtype()
	{
		return sendtype;
	}

	public void setSendtype(String sendtype)
	{
		this.sendtype = sendtype;
	}
	public Integer getContactid()
	{
		return contactid;
	}

	public void setContactid(Integer contactid)
	{
		this.contactid = contactid;
	}

	public String getTargetaddr()
	{
		return targetaddr;
	}

	public void setTargetaddr(String targetaddr)
	{
		this.targetaddr = targetaddr;
	}

}