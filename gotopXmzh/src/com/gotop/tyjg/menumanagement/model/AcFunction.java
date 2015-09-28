package com.gotop.tyjg.menumanagement.model;

import java.io.Serializable;
/**
 * *******************************
 * <p>Title: 菜单信息模型</p>
 * 
 * <p> Description:  菜单信息模型</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date 2012-3-30
 * 
 * @version 1.0
 * 
 * HISTORY 2012-3-30 xuxh 创建文件
 * 
 * *******************************
 */
public class AcFunction implements Serializable {
	private static final long serialVersionUID = -4546660395007612835L;
	private String funcCode;
	private String funcGroupId;
	private String funcName;
	private String funcDesc;
	private String funcAction;
	private String paraInfo;
	private String isCheck;
	private String funcType;
	private String isMenu;
	private String isLock;
	private String displayOrder;
	private String funIcon;
	private String parentGroupName;
	private String parentGroupId;
	private String oldFuncCode;
	private String appId;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOldFuncCode() {
		return oldFuncCode;
	}
	public void setOldFuncCode(String oldFuncCode) {
		this.oldFuncCode = oldFuncCode;
	}
	public String getParentGroupId() {
		return parentGroupId;
	}
	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}
	public String getParentGroupName() {
		return parentGroupName;
	}
	public void setParentGroupName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}
	public String getFuncCode() {
		return funcCode;
	}
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}
	public String getFuncGroupId() {
		return funcGroupId;
	}
	public void setFuncGroupId(String funcGroupId) {
		this.funcGroupId = funcGroupId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getFuncDesc() {
		return funcDesc;
	}
	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}
	public String getFuncAction() {
		return funcAction;
	}
	public void setFuncAction(String funcAction) {
		this.funcAction = funcAction;
	}
	public String getParaInfo() {
		return paraInfo;
	}
	public void setParaInfo(String paraInfo) {
		this.paraInfo = paraInfo;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public String getFuncType() {
		return funcType;
	}
	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}
	public String getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}
	public String getIsLock() {
		return isLock;
	}
	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getFunIcon() {
		return funIcon;
	}
	public void setFunIcon(String funIcon) {
		this.funIcon = funIcon;
	}
}
