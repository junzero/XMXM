package com.gotop.tyjg.menumanagement.model;

import java.io.Serializable;
/**
 * *******************************
 * <p>Title: 应用菜单组信息模型</p>
 * 
 * <p> Description:   应用菜单组信息模型</p>
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
public class AcFunctionGroup implements Serializable {
	private static final long serialVersionUID = 424655310092956108L;
	private String funcGroupId;
	private String appId;
	private String funcGroupName;
	private String parentGroup;
	private String groupLevel;
	private String funcGroupSeq;
	private String isLeaf;
	private String subCount;
	private String isLock;
	private String displayOrder;
	private String groupAction;
	private String groupIcon;
	private String parentGroupName;
	private String type;
	
	public String getParentGroupName() {
		return parentGroupName;
	}
	public void setParentGroupName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}
	public String getFuncGroupId() {
		return funcGroupId;
	}
	public void setFuncGroupId(String funcGroupId) {
		this.funcGroupId = funcGroupId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getFuncGroupName() {
		return funcGroupName;
	}
	public void setFuncGroupName(String funcGroupName) {
		this.funcGroupName = funcGroupName;
	}
	public String getParentGroup() {
		return parentGroup;
	}
	public void setParentGroup(String parentGroup) {
		this.parentGroup = parentGroup;
	}
	public String getGroupLevel() {
		return groupLevel;
	}
	public void setGroupLevel(String groupLevel) {
		this.groupLevel = groupLevel;
	}
	public String getFuncGroupSeq() {
		return funcGroupSeq;
	}
	public void setFuncGroupSeq(String funcGroupSeq) {
		this.funcGroupSeq = funcGroupSeq;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getSubCount() {
		return subCount;
	}
	public void setSubCount(String subCount) {
		this.subCount = subCount;
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
	public String getGroupAction() {
		return groupAction;
	}
	public void setGroupAction(String groupAction) {
		this.groupAction = groupAction;
	}
	public String getGroupIcon() {
		return groupIcon;
	}
	public void setGroupIcon(String groupIcon) {
		this.groupIcon = groupIcon;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
