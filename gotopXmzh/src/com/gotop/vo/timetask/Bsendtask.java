package com.gotop.vo.timetask;

import java.lang.Integer;
import java.util.Date;



/*********************************
 * <p>Title:Bsendtask.java</p>
 *
 * <p>Description:com.gotop.vo.marketactive</p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author huxj
 *
 * @date 2011-5-29 上午10:37:36
 *
 * @version 1.0
 *
 * HISTORY 2011-5-29 上午10:37:36 huxj 创建文件
 *
 *********************************/
public class Bsendtask implements java.io.Serializable
{

	// Fields

	private Integer id;			//id
	private String title;		//计划名称
	private String content;		//操作内容
	private String sendtype;	//发送类型
	private String fileurl;		//附件url
	private Date exectime;		//将要执行时间
	private Date senddate;		//发送日期
	private Integer execstate;	//执行状态
	private String mkdm;		//模块代码
	private Integer creater;	//创建者
	private String orgid;		//企业id
	private String datastate;	//数据状态

	// Constructors

	/** default constructor */
	public Bsendtask()
	{
	}

	/** minimal constructor */
	public Bsendtask(Integer id)
	{
		this.id = id;
	}

	/** full constructor */
	public Bsendtask(Integer id, String title, String content,
			String sendtype, String fileurl, Date exectime,
			Date senddate, Integer execstate, String mkdm,
			Integer creater, String orgid, String datastate)
	{
		this.id = id;
		this.title = title;
		this.content = content;
		this.sendtype = sendtype;
		this.fileurl = fileurl;
		this.exectime = exectime;
		this.senddate = senddate;
		this.execstate = execstate;
		this.mkdm = mkdm;
		this.creater = creater;
		this.orgid = orgid;
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

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getSendtype()
	{
		return this.sendtype;
	}

	public void setSendtype(String sendtype)
	{
		this.sendtype = sendtype;
	}

	public String getFileurl()
	{
		return this.fileurl;
	}

	public void setFileurl(String fileurl)
	{
		this.fileurl = fileurl;
	}

	public Date getExectime()
	{
		return this.exectime;
	}

	public void setExectime(Date exectime)
	{
		this.exectime = exectime;
	}

	public Date getSenddate()
	{
		return this.senddate;
	}

	public void setSenddate(Date senddate)
	{
		this.senddate = senddate;
	}

	public Integer getExecstate()
	{
		return this.execstate;
	}

	public void setExecstate(Integer execstate)
	{
		this.execstate = execstate;
	}

	public String getMkdm()
	{
		return this.mkdm;
	}

	public void setMkdm(String mkdm)
	{
		this.mkdm = mkdm;
	}

	public Integer getCreater()
	{
		return this.creater;
	}

	public void setCreater(Integer creater)
	{
		this.creater = creater;
	}

	public String getOrgid()
	{
		return this.orgid;
	}

	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}

	public String getDatastate()
	{
		return this.datastate;
	}

	public void setDatastate(String datastate)
	{
		this.datastate = datastate;
	}

}