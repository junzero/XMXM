package com.gotop.dto;

import java.io.File;

import com.gotop.opinion.model.TApproveOpninion;

public class BusinessDto {
	/**
	 * 文件数组
	 */
	private File[] upload;
	
	/**
	 * 文件名数组
	 */
	private String[] uploadFileName;
	
	/**
	 * 任务编号
	 */
	private String taskId;
	
	/**
	 * 数据使用人员编号，有多个则中间用逗号隔开
	 */
	private String dataUser;
	
	/**
	 * 数据表来源编号
	 */
	private Long resourceId;
	
	/**
	 * 表单提交原因0：保存；1：提交
	 */
	private String submitReason;
	
	/**
	 * 流程定义id
	 */
	private String definitionId;
	
	/**
	 * 业务类型
	 */
	private String businessType;
	
	/**
	 * 分析报告
	 */
	private String analysisReport;
	
	/**
	 * 意见
	 */
	private TApproveOpninion approveOpninion;
	
	/**
	 * 传递时间
	 */
	private String time;

	/**
	 * 文件数组
	 */
	public File[] getUpload() {
		return upload;
	}

	/**
	 * 文件数组
	 */
	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	/**
	 * 文件名数组
	 */
	public String[] getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * 文件名数组
	 */
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	/**
	 * 任务编号
	 */
	public String getTaskId() {
		return taskId;
	}
	
	/**
	 * 任务编号
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * 数据使用人员编号，有多个则中间用逗号隔开
	 */
	public String getDataUser() {
		return dataUser;
	}
	
	/**
	 * 数据使用人员编号，有多个则中间用逗号隔开
	 */
	public void setDataUser(String dataUser) {
		this.dataUser = dataUser;
	}
	
	/**
	 * 数据表来源编号
	 */
	public Long getResourceId() {
		return resourceId;
	}
	
	/**
	 * 数据表来源编号
	 */
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
	/**
	 * 表单提交原因0：保存；1：提交
	 */
	public String getSubmitReason() {
		return submitReason;
	}
	
	/**
	 * 表单提交原因0：保存；1：提交
	 */
	public void setSubmitReason(String submitReason) {
		this.submitReason = submitReason;
	}
	
	/**
	 * 流程定义id
	 */
	public String getDefinitionId() {
		return definitionId;
	}
	
	/**
	 * 流程定义id
	 */
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
	
	/**
	 * 业务类型
	 */
	public String getBusinessType() {
		return businessType;
	}
	
	/**
	 * 业务类型
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	/**
	 * 分析报告
	 */
	public String getAnalysisReport() {
		return analysisReport;
	}
	
	/**
	 * 分析报告
	 */
	public void setAnalysisReport(String analysisReport) {
		this.analysisReport = analysisReport;
	}

	/**
	 * @return approveOpninion
	 */
	public TApproveOpninion getApproveOpninion() {
		return approveOpninion;
	}

	/**
	 * @param approveOpninion 要设置的 approveOpninion
	 */
	public void setApproveOpninion(TApproveOpninion approveOpninion) {
		this.approveOpninion = approveOpninion;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
