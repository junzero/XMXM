package com.gotop.util.hibernate;

/**
 * <p>Title:库表信息DTO </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: </p>
 *
 * @author phc
 *
 * @date 2011-3-18
 *
 * @version 1.0
 **/
public class TableInfoDTO {
	 private String tTableName;//表名
     private String tFieldName;//表字段名
     private int tFieldType;//字段类型
     private int tInputType;//输入框类型
     private String tDisplayName;//显示名称
     private int tDisplayNum;//显示序号
     private int tState;//状态
     private String valuedatatype;//值校验类型
     
	public int gettFieldType() {
		return tFieldType;
	}
	public void settFieldType(int tFieldType) {
		this.tFieldType = tFieldType;
	}
	public String gettTableName() {
		return tTableName;
	}
	public void settTableName(String tTableName) {
		this.tTableName = tTableName;
	}
	public String gettFieldName() {
		return tFieldName;
	}
	public void settFieldName(String tFieldName) {
		this.tFieldName = tFieldName;
	}
	public int gettInputType() {
		return tInputType;
	}
	public void settInputType(int tInputType) {
		this.tInputType = tInputType;
	}
	public String gettDisplayName() {
		return tDisplayName;
	}
	public void settDisplayName(String tDisplayName) {
		this.tDisplayName = tDisplayName;
	}
	public int gettDisplayNum() {
		return tDisplayNum;
	}
	public void settDisplayNum(int tDisplayNum) {
		this.tDisplayNum = tDisplayNum;
	}
	public int gettState() {
		return tState;
	}
	public void settState(int tState) {
		this.tState = tState;
	}
	public String getValuedatatype() {
		return valuedatatype;
	}
	public void setValuedatatype(String valuedatatype) {
		this.valuedatatype = valuedatatype;
	}
}
