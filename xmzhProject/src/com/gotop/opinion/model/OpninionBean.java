package com.gotop.opinion.model;

import java.io.Serializable;

public class OpninionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 来源Id
	 */
	public Long resourceId;
	/**
	 * 来源类型
	 */
	public String resourceType;
	
	/**
	 * 操作类型
	 */
	public String operatorType;
	
	/**
	 * 意见内容
	 */
	public String opninionContent;
	
	/**
	 * 节点
	 */
	public String nodeId;
	
	/**
	 * 节点名称
	 */
	public String nodeName;
	
	/**
	 * 下个节点人姓名
	 */
	private String nextOprName;

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public String getOpninionContent() {
		return opninionContent;
	}

	public void setOpninionContent(String opninionContent) {
		this.opninionContent = opninionContent;
	}

	

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNextOprName() {
		return nextOprName;
	}

	public void setNextOprName(String nextOprName) {
		this.nextOprName = nextOprName;
	}
	
	
	

}
