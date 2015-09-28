package com.gotop.vo.customize;

import java.lang.Integer;
import java.util.Date;

/**
 * Tcustomfield entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Tcustomfield implements java.io.Serializable
{

	// Fields

	private Integer id;				//id
	private String orgid;			//企业id
	private String biztable;		//业务表
	private String fieldname;		//字段名称
	private String fieldnamech;		//字段中文名称
	private Integer fieldtype;		//字段类型
	private String bandcode;		//字段业务字典
	private Integer isshow;			//是否显示
	private Integer isselect;		//是否可以查询
	private Integer iscollect;		//是否可以统计
	private Integer creater;		//创建人
	private Date creatdate;			//创建日期
	private Integer datastate;		//数据状态
	private String defaultval;		//字段默认值

	// Constructors

	/** default constructor */
	public Tcustomfield()
	{
	}

	/** minimal constructor */
	public Tcustomfield(Integer id)
	{
		this.id = id;
	}

	public Tcustomfield(Integer id, String orgid, String biztable,
			String fieldname, String fieldnamech, Integer fieldtype,
			String bandcode,String defaultval, Integer isshow, Integer isselect,
			Integer iscollect, Integer creater, Date creatdate,
			Integer datastate)
	{
		super();
		this.id = id;
		this.orgid = orgid;
		this.biztable = biztable;
		this.fieldname = fieldname;
		this.fieldnamech = fieldnamech;
		this.fieldtype = fieldtype;
		this.bandcode = bandcode;
		this.defaultval=defaultval;
		this.isshow = isshow;
		this.isselect = isselect;
		this.iscollect = iscollect;
		this.creater = creater;
		this.creatdate = creatdate;
		this.datastate = datastate;
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
		return orgid;
	}

	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}

	public String getBiztable()
	{
		return this.biztable;
	}

	public void setBiztable(String biztable)
	{
		this.biztable = biztable;
	}

	public String getFieldname()
	{
		return this.fieldname;
	}

	public void setFieldname(String fieldname)
	{
		this.fieldname = fieldname;
	}

	public String getFieldnamech()
	{
		return this.fieldnamech;
	}

	public void setFieldnamech(String fieldnamech)
	{
		this.fieldnamech = fieldnamech;
	}

	public Integer getFieldtype()
	{
		return this.fieldtype;
	}

	public void setFieldtype(Integer fieldtype)
	{
		this.fieldtype = fieldtype;
	}

	public String getBandcode()
	{
		return this.bandcode;
	}

	public void setBandcode(String bandcode)
	{
		this.bandcode = bandcode;
	}
	
	public String getDefaultval()
	{
		return defaultval;
	}

	public void setDefaultval(String defaultval)
	{
		this.defaultval = defaultval;
	}

	public Integer getIsshow()
	{
		return this.isshow;
	}

	public void setIsshow(Integer isshow)
	{
		this.isshow = isshow;
	}

	public Integer getIsselect()
	{
		return this.isselect;
	}

	public void setIsselect(Integer isselect)
	{
		this.isselect = isselect;
	}

	public Integer getIscollect()
	{
		return this.iscollect;
	}

	public void setIscollect(Integer iscollect)
	{
		this.iscollect = iscollect;
	}

	public Integer getCreater()
	{
		return this.creater;
	}

	public void setCreater(Integer creater)
	{
		this.creater = creater;
	}

	public Date getCreatdate()
	{
		return this.creatdate;
	}

	public void setCreatdate(Date creatdate)
	{
		this.creatdate = creatdate;
	}

	public Integer getDatastate()
	{
		return this.datastate;
	}

	public void setDatastate(Integer datastate)
	{
		this.datastate = datastate;
	}

}