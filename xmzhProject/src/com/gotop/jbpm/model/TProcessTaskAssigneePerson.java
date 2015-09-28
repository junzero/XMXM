package com.gotop.jbpm.model;

import java.io.Serializable;

public class TProcessTaskAssigneePerson implements Serializable {
    /**
     * 主键 .
     * @abatorgenerated
     */
    private Long id;

    /**
     * 流程节点办理主键 .
     * @abatorgenerated
     */
    private Long processTaskAssigneeId;

    /**
     * 流程实例id .
     * @abatorgenerated
     */
    private String executionId;

    /**
     * 节点id .
     * @abatorgenerated
     */
    private String taskId;

    /**
     * 节点办理人 .
     * @abatorgenerated
     */
    private Long taskAssigneeId;

    /**
     * 节点办理状态
     */
    private String taskAssigneeState;
    
    /**
     * 父节点
     */
    private Long parentId;
    /**
     *是否是子节点
     */
    private String isChild;
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
     * 流程节点办理主键 .
     * @abatorgenerated
     */
    public Long getProcessTaskAssigneeId() {
        return processTaskAssigneeId;
    }

    /**
     * 流程节点办理主键 .
     * @abatorgenerated
     */
    public void setProcessTaskAssigneeId(Long processTaskAssigneeId) {
        this.processTaskAssigneeId = processTaskAssigneeId;
    }

    /**
     * 流程实例id .
     * @abatorgenerated
     */
    public String getExecutionId() {
        return executionId;
    }

    /**
     * 流程实例id .
     * @abatorgenerated
     */
    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    /**
     * 节点id .
     * @abatorgenerated
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 节点id .
     * @abatorgenerated
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 节点办理人 .
     * @abatorgenerated
     */
    public Long getTaskAssigneeId() {
        return taskAssigneeId;
    }

    /**
     * 节点办理人 .
     * @abatorgenerated
     */
    public void setTaskAssigneeId(Long taskAssigneeId) {
        this.taskAssigneeId = taskAssigneeId;
    }

	public String getTaskAssigneeState() {
		return taskAssigneeState;
	}

	public void setTaskAssigneeState(String taskAssigneeState) {
		this.taskAssigneeState = taskAssigneeState;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getIsChild() {
		return isChild;
	}

	public void setIsChild(String isChild) {
		this.isChild = isChild;
	}
    
	
}