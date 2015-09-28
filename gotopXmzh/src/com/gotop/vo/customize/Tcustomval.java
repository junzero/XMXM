package com.gotop.vo.customize;

import java.lang.Integer;

/**
 * Tcustomval entity. @author MyEclipse Persistence Tools
 */

public class Tcustomval implements java.io.Serializable
{

	// Fields

	private Integer id;			//id
	private Integer biztblid;	//业务表中对应记录id
	private Integer customtblid;//字段对应字段表中的id
	private String fieldval;	//字段值

	// Constructors

	/** default constructor */
	public Tcustomval()
	{
	}

	/** minimal constructor */
	public Tcustomval(Integer id)
	{
		this.id = id;
	}

	/** full constructor */
	public Tcustomval(Integer id, Integer biztblid,
			Integer customtblid, String fieldval)
	{
		this.id = id;
		this.biztblid = biztblid;
		this.customtblid = customtblid;
		this.fieldval = fieldval;
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

	public Integer getBiztblid()
	{
		return this.biztblid;
	}

	public void setBiztblid(Integer biztblid)
	{
		this.biztblid = biztblid;
	}

	public Integer getCustomtblid()
	{
		return this.customtblid;
	}

	public void setCustomtblid(Integer customtblid)
	{
		this.customtblid = customtblid;
	}

	public String getFieldval()
	{
		return this.fieldval;
	}

	public void setFieldval(String fieldval)
	{
		this.fieldval = fieldval;
	}

}