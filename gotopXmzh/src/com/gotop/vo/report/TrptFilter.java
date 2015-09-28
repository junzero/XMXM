package com.gotop.vo.report;

import java.io.Serializable;

public class TrptFilter implements Serializable
{
	private Integer  id;
	private Integer  parentid;//主表id
	private String   andor;
	private String   tblname;//表名
	private String   field;//过滤字段
	private String   fieldop;//过滤方式
	private String   fieldval;//过滤值
	public TrptFilter()
	{
		super();
	}
	public TrptFilter(Integer id, Integer parentid, String andor,
			String tblname, String field, String fieldop, String fieldval)
	{
		super();
		this.id = id;
		this.parentid = parentid;
		this.andor = andor;
		this.tblname = tblname;
		this.field = field;
		this.fieldop = fieldop;
		this.fieldval = fieldval;
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
	public String getAndor()
	{
		return andor;
	}
	public void setAndor(String andor)
	{
		this.andor = andor;
	}
	public String getTblname()
	{
		return tblname;
	}
	public void setTblname(String tblname)
	{
		this.tblname = tblname;
	}
	public String getField()
	{
		return field;
	}
	public void setField(String field)
	{
		this.field = field;
	}
	public String getFieldop()
	{
		return fieldop;
	}
	public void setFieldop(String fieldop)
	{
		this.fieldop = fieldop;
	}
	public String getFieldval()
	{
		return fieldval;
	}
	public void setFieldval(String fieldval)
	{
		this.fieldval = fieldval;
	}
	
	
}
