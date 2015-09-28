package com.gotop.tyjg.workgroup.model;

import java.math.BigDecimal;
import java.util.Date;

/**
* 查询一级工作组
*/
public class QueryRootGroupTypeBean {
    private String grouptype;
    private String dictName;
    
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
    
}