package com.gotop.vo.util;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class AttachmentInfo implements Serializable
{
	private Integer attid;//附件ID
	private String upattmodcd;//上传附件模块代码
	private Integer primarytbleid;//主表中的代码
	private String  attname;//附件名称
	private String  attstoragepath;//附件存储路径
	private Long attsize;//附件大小
	private String orgid;	//组织机构id
	private Integer userid;	//用户id
	private String attstatus;//附件状态
	private Date createdate;//创建日期
	private Date updatedate;//更新日期
	public AttachmentInfo()
	{
		super();
	}
	
	public AttachmentInfo(Integer attid, String upattmodcd,
			Integer primarytbleid, String attname, String attstoragepath,
			Long attsize, String orgid, Integer userid, String attstatus,
			Date createdate, Date updatedate)
	{
		super();
		this.attid = attid;
		this.upattmodcd = upattmodcd;
		this.primarytbleid = primarytbleid;
		this.attname = attname;
		this.attstoragepath = attstoragepath;
		this.attsize = attsize;
		this.orgid = orgid;
		this.userid = userid;
		this.attstatus = attstatus;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}
	public Integer getAttid()
	{
		return attid;
	}
	public void setAttid(Integer attid)
	{
		this.attid = attid;
	}
	public String getUpattmodcd()
	{
		return upattmodcd;
	}
	public void setUpattmodcd(String upattmodcd)
	{
		this.upattmodcd = upattmodcd;
	}
	public Integer getPrimarytbleid()
	{
		return primarytbleid;
	}
	public void setPrimarytbleid(Integer primarytbleid)
	{
		this.primarytbleid = primarytbleid;
	}
	public String getAttname()
	{
		return attname;
	}
	public void setAttname(String attname)
	{
		this.attname = attname;
	}
	public String getAttstoragepath()
	{
		return attstoragepath;
	}
	public void setAttstoragepath(String attstoragepath)
	{
		this.attstoragepath = attstoragepath;
	}
	public Long getAttsize()
	{
		return attsize;
	}
	public void setAttsize(Long attsize)
	{
		this.attsize = attsize;
	}
	public String getAttstatus()
	{
		return attstatus;
	}
	public void setAttstatus(String attstatus)
	{
		this.attstatus = attstatus;
	}
	public Date getCreatedate()
	{
		return createdate;
	}
	public void setCreatedate(Date createdate)
	{
		this.createdate = createdate;
	}
	public Date getUpdatedate()
	{
		return updatedate;
	}
	public void setUpdatedate(Date updatedate)
	{
		this.updatedate = updatedate;
	}

	public String getOrgid()
	{
		return orgid;
	}

	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}

	public Integer getUserid()
	{
		return userid;
	}

	public void setUserid(Integer userid)
	{
		this.userid = userid;
	}
	
	
}
