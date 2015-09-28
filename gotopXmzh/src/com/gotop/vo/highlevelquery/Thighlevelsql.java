package com.gotop.vo.highlevelquery;

import java.lang.Integer;
import java.util.Date;

/**
 * Thighlevelsql entity. @author MyEclipse Persistence Tools
 */

public class Thighlevelsql implements java.io.Serializable
{

	// Fields

	private Integer id;			//id
	private String mkdm;		//模块代码
	private String selectsql;	//查询sql
	private Date updatedate;	//更新日期
	private String remark;		//备注
	private String iscustdefau;		//是否自定义权限
	private String custdefsql;		//自定义sql
	// Constructors

	/** default constructor */
	public Thighlevelsql()
	{
	}

	/** minimal constructor */
	public Thighlevelsql(Integer id)
	{
		this.id = id;
	}

	/** full constructor */
	public Thighlevelsql(Integer id, String mkdm, String selectsql,
			Date updatedate, String remark, String iscustdefau,
			String custdefsql)
	{
		super();
		this.id = id;
		this.mkdm = mkdm;
		this.selectsql = selectsql;
		this.updatedate = updatedate;
		this.remark = remark;
		this.iscustdefau = iscustdefau;
		this.custdefsql = custdefsql;
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

	public String getMkdm()
	{
		return this.mkdm;
	}

	public void setMkdm(String mkdm)
	{
		this.mkdm = mkdm;
	}

	public String getSelectsql()
	{
		return this.selectsql;
	}

	public void setSelectsql(String selectsql)
	{
		this.selectsql = selectsql;
	}
	
	public Date getUpdatedate()
	{
		return updatedate;
	}

	public void setUpdatedate(Date updatedate)
	{
		this.updatedate = updatedate;
	}

	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getIscustdefau()
	{
		return iscustdefau;
	}

	public void setIscustdefau(String iscustdefau)
	{
		this.iscustdefau = iscustdefau;
	}

	public String getCustdefsql()
	{
		return custdefsql;
	}

	public void setCustdefsql(String custdefsql)
	{
		this.custdefsql = custdefsql;
	}

}