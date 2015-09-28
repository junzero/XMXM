package com.gotop.jbpm.model;

import java.io.Serializable;

public class TProcessBusiness implements Serializable {
    /**
     * 主键 .
     * @abatorgenerated
     */
    private Long id;

    /**
     * 业务类型 .
     * @abatorgenerated
     */
    private String businessType;

    /**
     * 业务主键 .
     * @abatorgenerated
     */
    private Long businessKey;

    /**
     * 业务标题 .
     * @abatorgenerated
     */
    private String businessTitle;

    /**
     * 业务字段 .
     * @abatorgenerated
     */
    private String businessColumn;

    /**
     * 业务表 .
     * @abatorgenerated
     */
    private String businessTable;
    
    /**
     * 流程实例id
     */
    private String executionId;

    private Long fileId;
    
    private String filePath;
    
    private String blobValue;
    
    private String blobName;
    
    /**
     * 起草人提交时间
     */
    private String submitTime;
    
    private String submitTimeAfter;
    
    /**
     * 起草人id
     */
    private Long submitId;
    
	public String getBlobValue() {
		return blobValue;
	}

	public void setBlobValue(String blobValue) {
		this.blobValue = blobValue;
	}

	public String getBlobName() {
		return blobName;
	}

	public void setBlobName(String blobName) {
		this.blobName = blobName;
	}

	/**
     * 主键 .
     * @abatorgenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键 .
     * @abatorgenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 业务类型 .
     * @abatorgenerated
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 业务类型 .
     * @abatorgenerated
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * 业务主键 .
     * @abatorgenerated
     */
    public Long getBusinessKey() {
        return businessKey;
    }

    /**
     * 业务主键 .
     * @abatorgenerated
     */
    public void setBusinessKey(Long businessKey) {
        this.businessKey = businessKey;
    }

    /**
     * 业务标题 .
     * @abatorgenerated
     */
    public String getBusinessTitle() {
        return businessTitle;
    }

    /**
     * 业务标题 .
     * @abatorgenerated
     */
    public void setBusinessTitle(String businessTitle) {
        this.businessTitle = businessTitle;
    }

	public String getBusinessColumn() {
		return businessColumn;
	}

	public void setBusinessColumn(String businessColumn) {
		this.businessColumn = businessColumn;
	}

	public String getBusinessTable() {
		return businessTable;
	}

	public void setBusinessTable(String businessTable) {
		this.businessTable = businessTable;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public Long getSubmitId() {
		return submitId;
	}

	public void setSubmitId(Long submitId) {
		this.submitId = submitId;
	}

	public String getSubmitTimeAfter() {
		return submitTimeAfter;
	}

	public void setSubmitTimeAfter(String submitTimeAfter) {
		this.submitTimeAfter = submitTimeAfter;
	}
	
}