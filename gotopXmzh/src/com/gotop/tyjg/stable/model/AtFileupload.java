package com.gotop.tyjg.stable.model;

import java.util.Date;


public class AtFileupload {
    /**
	 * null .
	 * @abatorgenerated
	 */
	private String fileId;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private String fileCatalog;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private String fileName;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private String fileNewName;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private String filePath;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private String absolutepath;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private Long fileSize;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private String fileType;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private Date fileTime;
	/**
	 * 1：文件系统，2：数据库 .
	 * @abatorgenerated
	 */
	private String fileSave;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private byte[] content;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private Long operatorid;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private String operatorname;
	/**
	 * null .
	 * @abatorgenerated
	 */
	private String relationId;
	/**
	 * 如果多个文件同时上传，则会产生一个上传的组编号 .
	 * @abatorgenerated
	 */
	private String groupId;
	/**
	 * 附件源记录编号 .
	 * @abatorgenerated
	 */
	private String attachmentSrcId;
	/**
	 * 附件来源模块编号（菜单编号） .
	 * @abatorgenerated
	 */
	private String attachmentSrcCd;

	/**
	 * @abatorgenerated
	 */
	public String getAttachmentSrcId() {
		return attachmentSrcId;
	}

	/**
	 * @abatorgenerated
	 */
	public void setAttachmentSrcId(String attachmentSrcId) {
		this.attachmentSrcId = attachmentSrcId;
	}

	/**
	 * @abatorgenerated
	 */
	public String getAttachmentSrcCd() {
		return attachmentSrcCd;
	}

	/**
	 * @abatorgenerated
	 */
	public void setAttachmentSrcCd(String attachmentSrcCd) {
		this.attachmentSrcCd = attachmentSrcCd;
	}

	/**
     * @abatorgenerated*/
    public String getFileId() {
        return fileId;
    }

    /**
     * @abatorgenerated*/
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * @abatorgenerated*/
    public String getFileCatalog() {
        return fileCatalog;
    }

    /**
     * @abatorgenerated*/
    public void setFileCatalog(String fileCatalog) {
        this.fileCatalog = fileCatalog;
    }

    /**
     * @abatorgenerated*/
    public String getFileName() {
        return fileName;
    }

    /**
     * @abatorgenerated*/
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @abatorgenerated*/
    public String getFileNewName() {
        return fileNewName;
    }

    /**
     * @abatorgenerated*/
    public void setFileNewName(String fileNewName) {
        this.fileNewName = fileNewName;
    }

    /**
     * @abatorgenerated*/
    public String getFilePath() {
        return filePath;
    }

    /**
     * @abatorgenerated*/
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @abatorgenerated*/
    public String getAbsolutepath() {
        return absolutepath;
    }

    /**
     * @abatorgenerated*/
    public void setAbsolutepath(String absolutepath) {
        this.absolutepath = absolutepath;
    }

    /**
     * @abatorgenerated*/
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * @abatorgenerated*/
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @abatorgenerated*/
    public String getFileType() {
        return fileType;
    }

    /**
     * @abatorgenerated*/
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * @abatorgenerated*/
    public Date getFileTime() {
        return fileTime;
    }

    /**
     * @abatorgenerated*/
    public void setFileTime(Date fileTime) {
        this.fileTime = fileTime;
    }

    /**
     * @abatorgenerated*/
    public String getFileSave() {
        return fileSave;
    }

    /**
     * @abatorgenerated*/
    public void setFileSave(String fileSave) {
        this.fileSave = fileSave;
    }

    public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
     * @abatorgenerated*/
    public Long getOperatorid() {
        return operatorid;
    }

    /**
     * @abatorgenerated*/
    public void setOperatorid(Long operatorid) {
        this.operatorid = operatorid;
    }

    /**
     * @abatorgenerated*/
    public String getOperatorname() {
        return operatorname;
    }

    /**
     * @abatorgenerated*/
    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    /**
     * @abatorgenerated*/
    public String getRelationId() {
        return relationId;
    }

    /**
     * @abatorgenerated*/
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    /**
     * @abatorgenerated*/
    public String getGroupId() {
        return groupId;
    }

    /**
     * @abatorgenerated*/
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}