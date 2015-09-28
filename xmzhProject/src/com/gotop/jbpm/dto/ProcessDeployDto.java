package com.gotop.jbpm.dto;

/**
 * 流程发布DTO
 * @author wsd
 *
 */
public class ProcessDeployDto {

	  private Long id;
	  
	/**
	 * 流程定义id
	 */
	private String processDefinitionId;
	
	/**
	 * 流程名称
	 */
	private String processName;
	
	/**
	 * 发布类型
	 */
	private String deployType;
	
	/**
	 * 流程发布范围
	 */
	private String deployRange;
	
	private String objName;
	
	/**
	 * 流程状态
	 */
	private String processState;
	
	/**
	 * 流程类型
	 */
	private String businessType;

	/**
	 * 序号
	 */
	private String orderNo;
	
	public String getDeployType() {
		return deployType;
	}

	public void setDeployType(String deployType) {
		this.deployType = deployType;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getDeployRange() {
		return deployRange;
	}

	public void setDeployRange(String deployRange) {
		this.deployRange = deployRange;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
