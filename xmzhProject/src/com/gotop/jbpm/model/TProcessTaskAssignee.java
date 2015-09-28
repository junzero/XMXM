package com.gotop.jbpm.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TProcessTaskAssignee implements Serializable {
    /**
     * 主键 .
     * @abatorgenerated
     */
    private Long id;

    /**
     * 流程实例id .
     * @abatorgenerated
     */
    private String executionId;

    /**
     * 上个节点id .
     * @abatorgenerated
     */
    private String preTaskId;

    /**
     * 提交人id .
     * @abatorgenerated
     */
    private String preTaskAssingee;

    /**
     * 提交结构 .
     * @abatorgenerated
     */
    private Long preTaskOrg;

    /**
     * 提交时间 .
     * @abatorgenerated
     */
    private String preTaskTime;

    /**
     * 下个节点id .
     * @abatorgenerated
     */
    private String nextTaskId;

    /**
     * 节点id .
     * @abatorgenerated
     */
    private String taskId;

    /**
     * 业务主键 .
     * @abatorgenerated
     */
    private Long businessKey;

    /**
     * 业务类型 .
     * @abatorgenerated
     */
    private String businessType;
    
    /**
     * 提交人姓名-页面展示
     */
    private String preTaskAssingeeName;
    
    /**
     * 提交人机构名称-页面展示
     */
    private String preTaskOrgName;
    
    /**
     * 流程名称-页面展示
     */
    private String processName;
    
    private String activityName;
    
    private String startTime;
    
    private String startTimeAfter;
    
    private String endTime;
    
    private String endTimeAfter;
    
    /**
     * 提交开始时间-页面查询条件
     */
    private String preTaskTimeStart;
    
    /**
     * 提交结束时间-页面查询条件
     */
    private String preTaskTimeEnd;
    
    
    private String businessTitle;
    
    /**
     * 当前节点办理人
     */
    private String currentAssingee;
    
    /**
     * 当前节点名称
     */
    private String currentActivityName;
    
    /**
     * 下个节点名称
     */
    private String nextActivityName;
    
    /**
     * 下个节点办理人
     */
    private String nextAssingeeName;
    
    /**
     * 流程类型-名称
     */
    private String businessName;
    
    /**
     * 流程节点对象主键
     */
    private Long taskExeConfigId;
    
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
     * 上个节点id .
     * @abatorgenerated
     */
    public String getPreTaskId() {
        return preTaskId;
    }

    /**
     * 上个节点id .
     * @abatorgenerated
     */
    public void setPreTaskId(String preTaskId) {
        this.preTaskId = preTaskId;
    }

    /**
     * 提交人id .
     * @abatorgenerated
     */
    public String getPreTaskAssingee() {
        return preTaskAssingee;
    }

    /**
     * 提交人id .
     * @abatorgenerated
     */
    public void setPreTaskAssingee(String preTaskAssingee) {
        this.preTaskAssingee = preTaskAssingee;
    }

    /**
     * 提交结构 .
     * @abatorgenerated
     */
    public Long getPreTaskOrg() {
        return preTaskOrg;
    }

    /**
     * 提交结构 .
     * @abatorgenerated
     */
    public void setPreTaskOrg(Long preTaskOrg) {
        this.preTaskOrg = preTaskOrg;
    }

    /**
     * 提交时间 .
     * @abatorgenerated
     */
    public String getPreTaskTime() {
        return preTaskTime;
    }

    /**
     * 提交时间 .
     * @abatorgenerated
     */
    public void setPreTaskTime(String preTaskTime) {
        this.preTaskTime = preTaskTime;
    }

    /**
     * 下个节点id .
     * @abatorgenerated
     */
    public String getNextTaskId() {
        return nextTaskId;
    }

    /**
     * 下个节点id .
     * @abatorgenerated
     */
    public void setNextTaskId(String nextTaskId) {
        this.nextTaskId = nextTaskId;
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

	public String getPreTaskAssingeeName() {
		return preTaskAssingeeName;
	}

	public void setPreTaskAssingeeName(String preTaskAssingeeName) {
		this.preTaskAssingeeName = preTaskAssingeeName;
	}

	public String getPreTaskOrgName() {
		return preTaskOrgName;
	}

	public void setPreTaskOrgName(String preTaskOrgName) {
		this.preTaskOrgName = preTaskOrgName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getPreTaskTimeStart() {
		return preTaskTimeStart;
	}

	public void setPreTaskTimeStart(String preTaskTimeStart) {
		this.preTaskTimeStart = preTaskTimeStart;
	}

	public String getPreTaskTimeEnd() {
		return preTaskTimeEnd;
	}

	public void setPreTaskTimeEnd(String preTaskTimeEnd) {
		this.preTaskTimeEnd = preTaskTimeEnd;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBusinessTitle() {
		return businessTitle;
	}

	public void setBusinessTitle(String businessTitle) {
		this.businessTitle = businessTitle;
	}

	public String getCurrentAssingee() {
		return currentAssingee;
	}

	public void setCurrentAssingee(String currentAssingee) {
		this.currentAssingee = currentAssingee;
	}

	public String getCurrentActivityName() {
		return currentActivityName;
	}

	public void setCurrentActivityName(String currentActivityName) {
		this.currentActivityName = currentActivityName;
	}

	/**
	 * @return nextActivityName
	 */
	public String getNextActivityName() {
		return nextActivityName;
	}

	/**
	 * @param nextActivityName 要设置的 nextActivityName
	 */
	public void setNextActivityName(String nextActivityName) {
		this.nextActivityName = nextActivityName;
	}

	/**
	 * @return nextAssingeeName
	 */
	public String getNextAssingeeName() {
		return nextAssingeeName;
	}

	/**
	 * @param nextAssingeeName 要设置的 nextAssingeeName
	 */
	public void setNextAssingeeName(String nextAssingeeName) {
		this.nextAssingeeName = nextAssingeeName;
	}

	public String getStartTimeAfter() {
		return startTimeAfter;
	}

	public void setStartTimeAfter(String startTimeAfter) {
		this.startTimeAfter = startTimeAfter;
	}

	public String getEndTimeAfter() {
		return endTimeAfter;
	}

	public void setEndTimeAfter(String endTimeAfter) {
		this.endTimeAfter = endTimeAfter;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Long getTaskExeConfigId() {
		return taskExeConfigId;
	}

	public void setTaskExeConfigId(Long taskExeConfigId) {
		this.taskExeConfigId = taskExeConfigId;
	}

}