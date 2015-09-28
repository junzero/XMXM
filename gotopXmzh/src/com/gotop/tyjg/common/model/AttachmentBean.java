package com.gotop.tyjg.common.model;

import java.io.Serializable;

public class AttachmentBean implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * null .
     * @abatorgenerated
     */
    private String fileid;
    /**
     * null .
     * @abatorgenerated
     */
    private String srcId;
    /**
     * null .
     * @abatorgenerated
     */
    private String srcCd;
    
    private String fileTimeStart;
    private String fileTimeEnd;
    private String fileSizeStart;
    private String fileSizeEnd;
	/**
	 * @return the srcId
	 */
	public String getSrcId() {
		return srcId;
	}
	/**
	 * @param srcId the srcId to set
	 */
	public void setSrcId(final String srcId) {
		this.srcId = srcId;
	}
	/**
	 * @return the srcCd
	 */
	public String getSrcCd() {
		return srcCd;
	}
	/**
	 * @param srcCd the srcCd to set
	 */
	public void setSrcCd(final String srcCd) {
		this.srcCd = srcCd;
	}
	/**
	 * @return the fileid
	 */
	public String getfileid() {
		return fileid;
	}
	/**
	 * @param fileid the fileid to set
	 */
	public void setfileid(final String fileid) {
		this.fileid = fileid;
	}
	public String getFileTimeStart() {
		return fileTimeStart;
	}
	public void setFileTimeStart(String fileTimeStart) {
		this.fileTimeStart = fileTimeStart;
	}
	public String getFileTimeEnd() {
		return fileTimeEnd;
	}
	public void setFileTimeEnd(String fileTimeEnd) {
		this.fileTimeEnd = fileTimeEnd;
	}
	public String getFileSizeStart() {
		return fileSizeStart;
	}
	public void setFileSizeStart(String fileSizeStart) {
		this.fileSizeStart = fileSizeStart;
	}
	public String getFileSizeEnd() {
		return fileSizeEnd;
	}
	public void setFileSizeEnd(String fileSizeEnd) {
		this.fileSizeEnd = fileSizeEnd;
	}
   
}