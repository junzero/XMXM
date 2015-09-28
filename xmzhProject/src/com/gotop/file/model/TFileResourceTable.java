package com.gotop.file.model;

import java.io.Serializable;

public class TFileResourceTable implements Serializable {
    /**
     * 文件编号 .
     * @abatorgenerated
     */
    private Long fileId;

    /**
     * 来源编号 .
     * @abatorgenerated
     */
    private Long resourceId;

    /**
     * 01：信息发布02：督办单管理 03：工作月报 04：会议申请 05：数据申请流程 06：数据下发流程 07:社保申请流程 08：员工请假流程 .
     * @abatorgenerated
     */
    private String resourceType;

    /**
     * 文件名称 .
     * @abatorgenerated
     */
    private String fileName;

    /**
     * 文件路径 .
     * @abatorgenerated
     */
    private String filePath;

    /**
     * 文件类型 .
     * @abatorgenerated
     */
    private String fileType;

    /**
     * 节点编号 .
     * @abatorgenerated
     */
    private String nodeId;

    /**
     * 节点名称 .
     * @abatorgenerated
     */
    private String nodeName;

    /**
     * 上传人员 .
     * @abatorgenerated
     */
    private Long creator;

    /**
     * 上传日期 .
     * @abatorgenerated
     */
    private String createDate;

    /**
     * 上传时间 .
     * @abatorgenerated
     */
    private String createTime;
    
    private String fileReName;

    /**
     * 文件编号 .
     * @abatorgenerated
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 文件编号 .
     * @abatorgenerated
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * 来源编号 .
     * @abatorgenerated
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 来源编号 .
     * @abatorgenerated
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 01：信息发布02：督办单管理 03：工作月报 04：会议申请 05：数据申请流程 06：数据下发流程 07:社保申请流程 08：员工请假流程 .
     * @abatorgenerated
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 01：信息发布02：督办单管理 03：工作月报 04：会议申请 05：数据申请流程 06：数据下发流程 07:社保申请流程 08：员工请假流程 .
     * @abatorgenerated
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 文件名称 .
     * @abatorgenerated
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名称 .
     * @abatorgenerated
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 文件路径 .
     * @abatorgenerated
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 文件路径 .
     * @abatorgenerated
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 文件类型 .
     * @abatorgenerated
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 文件类型 .
     * @abatorgenerated
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 节点编号 .
     * @abatorgenerated
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 节点编号 .
     * @abatorgenerated
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 节点名称 .
     * @abatorgenerated
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 节点名称 .
     * @abatorgenerated
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * 上传人员 .
     * @abatorgenerated
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 上传人员 .
     * @abatorgenerated
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * 上传日期 .
     * @abatorgenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 上传日期 .
     * @abatorgenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * 上传时间 .
     * @abatorgenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 上传时间 .
     * @abatorgenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

	public String getFileReName() {
		return fileReName;
	}

	public void setFileReName(String fileReName) {
		this.fileReName = fileReName;
	}
    
    
}