package com.gotop.tyjg.common.model;

import java.io.Serializable;

/**
 * *******************************
 * <p>Title: 群组</p>
 * 
 * <p> Description: 群组 </p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date Apr 12, 2012
 * 
 * @version 1.0
 * 
 * HISTORY Apr 12, 2012 xuxh 创建文件
 * 
 * *******************************
 */
public class Group implements Serializable {

	private static final long serialVersionUID = -565514754270832387L;

	private String groupid;
	
	private String groupname;

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
