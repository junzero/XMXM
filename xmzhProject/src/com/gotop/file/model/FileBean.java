package com.gotop.file.model;

import java.io.Serializable;

public class FileBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 来源Id
	 */
	public Long resourceId;
	/**
	 * 来源类型  例如:01:信息发布
	 */
	public String resourceType;
	/**
	 * 节点编号 ：用于标识在那个流程节点上传的文件
	 */
	public String nodeId;
	/**
	 * 节点名称
	 */
	public String nodeName;
	
	/**
	 * 文件类型（1：用来标识不同的文件类型。）0 ：基本 1:本月  2 上月
	 */
	public String fileType;
	
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
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
}
