package com.gotop.vo.report;

/**
 * Tgroupunion entity. @author MyEclipse Persistence Tools
 */

public class Tgroupunion implements java.io.Serializable
{

	// Fields

	private Integer id;			//id
	private String groupname;	//分组名称
	private String unionnmae;	//关联名称
	private String relationship;//关联关系

	// Constructors

	/** default constructor */
	public Tgroupunion()
	{
	}

	public Tgroupunion(Integer id, String groupname, String unionnmae,String relationship)
	{
		super();
		this.id = id;
		this.groupname = groupname;
		this.unionnmae = unionnmae;
		this.relationship=relationship;
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

	public String getUnionnmae()
	{
		return unionnmae;
	}

	public void setUnionnmae(String unionnmae)
	{
		this.unionnmae = unionnmae;
	}

	public String getRelationship()
	{
		return relationship;
	}

	public void setRelationship(String relationship)
	{
		this.relationship = relationship;
	}



}