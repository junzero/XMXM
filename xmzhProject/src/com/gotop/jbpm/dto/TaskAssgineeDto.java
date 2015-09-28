package com.gotop.jbpm.dto;

public class TaskAssgineeDto {

	/**
     * 节点id .
     */
    private String taskId;

    /**
     * 上个节点id .
     */
    private String preTaskId;

    /**
     * 提交人id .
     */
    private Long preTaskAssingee;

    /**
     * 提交结构 .
     */
    private Long preTaskOrg;

    /**
     * 提交时间 .
     */
    private String preTaskTime;

    /**
     * 下个节点id .
     */
    private String nextTaskId;

    /**
     * 流程实例id .
     */
    private String executionId;
    
    /**
     * 流程定义id .
     */
    private String definitionId;
    
    /**
     * 业务主键
     */
    private Long businessKey;
    
    /**
     * 业务类型
     */
    private String businessType;
    
    /**
     * 办理人id
     */
    private Long taskAssingee;
    
    /**
     * 当前节点名称
     */
    private String taskName;
    
    /**
     * 人员选择-下个节点办理人id
     */
    private String empIds;
    
    /**
     * 人员选择-下个节点办理人名字
     */
    private String empNames;
    
    /**
     * 转向目标的名称
     */
    private String transitionName;
    
    /**
     * 目标节点名称
     */
    private String targetName;
    
    /**
     * 节点办理状态
     */
    private String taskAssigneeState;
    
    /**
     * 起草人id .
     */
    private Long beginAssingee;

    /**
     * 起草人机构 .
     */
    private Long beginOrg;
    
    /**
     * 节点执行人id
     */
    private String taskExeAssginee;
    
    /**
     * 动态的机构id(用逗号隔开)
     */
    private String dynamicOrgIds;
    
    /**
     * 节点配置类型
     */
    private String taskConfigType;
    
    /**
     * 流程发布-对象code
     */
    private String objCode;
    
    /**
     * 流程发布-对象名称
     */
    private String objName;
    
    /**
     * 流程发布-对象id
     */
    private String objId;
    
    /**
     * 节点对象id
     */
    private String taskAssId;
    
    
    private String isUpdate;
    
    private String oldTaskName;
    
    private String beforeName;
    
    /**
     * 流程名称
     */
    private String processName;
    
    /**
     * 任务签收人主键
     */
    private Long parentId;
    /**
     * 判断是否是子处理人。
     */
    private String isChild;
    
    /**
     * 模板文件id
     */
    private String templateFileIds;
    
    /**
     * 流程节点办理主键 .
     * 20140905新增
     */
    private Long processTaskAssigneeId;
    
    /**
     * 流程节点配置对象主键
     */
    private Long taskExeConfigId;
    
    /**
     * 重新标志，1-重选
     */
    private String reSelectFlag;
    
	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getObjCode() {
		return objCode;
	}

	public void setObjCode(String objCode) {
		this.objCode = objCode;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getTaskConfigType() {
		return taskConfigType;
	}

	public void setTaskConfigType(String taskConfigType) {
		this.taskConfigType = taskConfigType;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getPreTaskId() {
		return preTaskId;
	}

	public void setPreTaskId(String preTaskId) {
		this.preTaskId = preTaskId;
	}

	public String getPreTaskTime() {
		return preTaskTime;
	}

	public void setPreTaskTime(String preTaskTime) {
		this.preTaskTime = preTaskTime;
	}

	public String getNextTaskId() {
		return nextTaskId;
	}

	public void setNextTaskId(String nextTaskId) {
		this.nextTaskId = nextTaskId;
	}

	public Long getPreTaskAssingee() {
		return preTaskAssingee;
	}

	public void setPreTaskAssingee(Long preTaskAssingee) {
		this.preTaskAssingee = preTaskAssingee;
	}

	public Long getPreTaskOrg() {
		return preTaskOrg;
	}

	public void setPreTaskOrg(Long preTaskOrg) {
		this.preTaskOrg = preTaskOrg;
	}

	public Long getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(Long businessKey) {
		this.businessKey = businessKey;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Long getTaskAssingee() {
		return taskAssingee;
	}

	public void setTaskAssingee(Long taskAssingee) {
		this.taskAssingee = taskAssingee;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getEmpIds() {
		return empIds;
	}

	public void setEmpIds(String empIds) {
		this.empIds = empIds;
	}

	public String getEmpNames() {
		return empNames;
	}

	public void setEmpNames(String empNames) {
		this.empNames = empNames;
	}

	public String getTransitionName() {
		return transitionName;
	}

	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}

	public String getTaskAssigneeState() {
		return taskAssigneeState;
	}

	public void setTaskAssigneeState(String taskAssigneeState) {
		this.taskAssigneeState = taskAssigneeState;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public Long getBeginAssingee() {
		return beginAssingee;
	}

	public void setBeginAssingee(Long beginAssingee) {
		this.beginAssingee = beginAssingee;
	}

	public Long getBeginOrg() {
		return beginOrg;
	}

	public void setBeginOrg(Long beginOrg) {
		this.beginOrg = beginOrg;
	}

	public String getTaskExeAssginee() {
		return taskExeAssginee;
	}

	public void setTaskExeAssginee(String taskExeAssginee) {
		this.taskExeAssginee = taskExeAssginee;
	}

	public String getDynamicOrgIds() {
		return dynamicOrgIds;
	}

	public void setDynamicOrgIds(String dynamicOrgIds) {
		this.dynamicOrgIds = dynamicOrgIds;
	}

	public String getTaskAssId() {
		return taskAssId;
	}

	public void setTaskAssId(String taskAssId) {
		this.taskAssId = taskAssId;
	}

	public String getOldTaskName() {
		return oldTaskName;
	}

	public void setOldTaskName(String oldTaskName) {
		this.oldTaskName = oldTaskName;
	}

	public Long getProcessTaskAssigneeId() {
		return processTaskAssigneeId;
	}

	public void setProcessTaskAssigneeId(Long processTaskAssigneeId) {
		this.processTaskAssigneeId = processTaskAssigneeId;
	}


	public String getIsChild() {
		return isChild;
	}

	public void setIsChild(String isChild) {
		this.isChild = isChild;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getBeforeName() {
		return beforeName;
	}

	public void setBeforeName(String beforeName) {
		this.beforeName = beforeName;
	}

	public String getTemplateFileIds() {
		return templateFileIds;
	}

	public void setTemplateFileIds(String templateFileIds) {
		this.templateFileIds = templateFileIds;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Long getTaskExeConfigId() {
		return taskExeConfigId;
	}

	public void setTaskExeConfigId(Long taskExeConfigId) {
		this.taskExeConfigId = taskExeConfigId;
	}

	public String getReSelectFlag() {
		return reSelectFlag;
	}

	public void setReSelectFlag(String reSelectFlag) {
		this.reSelectFlag = reSelectFlag;
	}

}
