package com.gotop.monthReport.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.monthReport.model.TWorkMonthReports;
import com.gotop.monthReport.service.ITWorkMonthReportsService;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.opinion.model.TDefaultOpinion;
import com.gotop.opinion.service.ITDefaultOpinionService;
import com.gotop.util.Struts2Utils;
import com.gotop.vo.system.MUOUserSession;
import com.informix.util.stringUtil;

import java.io.File;
import java.util.List;

public class TWorkMonthReportsAction extends BaseAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITWorkMonthReportsService tWorkMonthReportsService;
    
    /**
     * 月报
     */
    private TWorkMonthReports monthReports;
    
    /**
     * 月报列表
     */
    private List<TWorkMonthReports> returns;
    
    /**
     * 审核意见
     */
    private TApproveOpninion opninion;
    
    /**
     * 意见列表
     */
    private List<TApproveOpninion> opninions;
    
    /**
     * 节点
     */
    private TaskAssgineeDto taskAssgineeDto;
    
    /**
     * 提交类型
     */
    private String btnType;
    
    /**
     * 添加本月计划的附件
     */
    private File[] monthReportFiles;
    
    /**
     * 添加完成情况的附件
     */
    private File[] completionFiles;
    
    /**
     * 添加汇总的附件
     */
    private File[] gatherFiles;
    /**
     * 添加的本月计划附件的名称
     */
    private String[] monthReportFilesFileName;
    
    /**
     * 添加的完成情况附件的名称
     */
    private String[] completionFilesFileName;
   
   /**
    * 添加的汇总附件的名称
    */
    private String[] gatherFilesFileName;
    /**
     * 节点ID
     */
    private String nodeId;

    /**
     * 节点名称
     */
    private String nodeName1;
    
    /**
     * 流程ID .
     * @abatorgenerated
     */
    private String flowId;
    private String[] uploadContentType;
    private String taskId;
    
    private Long reportId;
    
    /**
     * 1为只读状态， 默认为空
     */
    private String isView;
    
    //是否为第一个节点20140905
    private String isFirst;
    
    /**
     * 流程定义id
     */
    private String definitionId;
    
    /**
	 * 默认意见
	 * 2014.9.19 新建 
	 */
	private List<TDefaultOpinion> defaultOps;
	
	private ITDefaultOpinionService tDefaultOpinionService;

	public List<TDefaultOpinion> getDefaultOps() {
		return defaultOps;
	}

	public void setDefaultOps(List<TDefaultOpinion> defaultOps) {
		this.defaultOps = defaultOps;
	}

    public ITDefaultOpinionService gettDefaultOpinionService() {
		return tDefaultOpinionService;
	}

	public void settDefaultOpinionService(
			ITDefaultOpinionService tDefaultOpinionService) {
		this.tDefaultOpinionService = tDefaultOpinionService;
	}
	
	public void queryDefault(){
		try {
			defaultOps = tDefaultOpinionService.queryDefaultOpsForshow(this.getCurrentOnlineUser().getEmpid());
		} catch (Exception e) {
			log.error("[获取默认意见失败]",e);
			e.printStackTrace();
		}
	}
	//2014.9.19新增结束标记
    private String businessType;
    public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settWorkMonthReportsService(ITWorkMonthReportsService tWorkMonthReportsService) {
        this.tWorkMonthReportsService = tWorkMonthReportsService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITWorkMonthReportsService gettWorkMonthReportsService() {
        return this.tWorkMonthReportsService;
    }

    /**
     * 查询月报列表
     * @return
     */
    /*public String querytMonthReportsList(){
        try {
            MUOUserSession muo=getCurrentOnlineUser();
            returns=tWorkMonthReportsService.queryTWorkMonthReportsList(muo, monthReports, this.getPage());
        } catch (Exception e) {
        log.error("查询发布信息列表出现异常", e);
        }
        return "list";
    }*/
    
    /**
     * 保存月报
     * 
     */
    public String insertMonthReportsInfo() throws Exception{
        String info ="success";
        try {
            MUOUserSession muo=getCurrentOnlineUser();
            taskAssgineeDto=tWorkMonthReportsService.insertMonthReportsInfo(monthReports, monthReportFiles,
                     completionFiles,gatherFiles,monthReportFilesFileName, completionFilesFileName,gatherFilesFileName,
                     muo, btnType, taskAssgineeDto,definitionId,businessType,isFirst);
        } catch (Exception e) {
            info="fails";
            log.error("[添加月报失败！]", e);
            throw e;
        }finally{
        }
        Struts2Utils.renderText(info);
        return null;    
    }
    /**
     * 申请界面
     * @return
     */
    public String monthReportInput(){
        try {
            if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
            { 
                reportId=taskAssgineeDto.getBusinessKey();
                monthReports=tWorkMonthReportsService.queryMonthReportInfo(reportId);
            }
            else if (taskAssgineeDto!=null && taskAssgineeDto.getExecutionId()!=null && !"".equals(taskAssgineeDto.getExecutionId())) 
            {
                flowId=taskAssgineeDto.getExecutionId();
                monthReports = tWorkMonthReportsService.getMonthReportByFlowId(flowId);
            }
           } catch (Exception e) {
                log.error("获取客户详情报错。", e);
            }
        return "input";
    }
    
    
    /**
     * 部门领导审核界面
     * @return
     */
    public String monthReportApprove1(){
        try {
            if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
            {
                reportId=taskAssgineeDto.getBusinessKey();
                monthReports=tWorkMonthReportsService.queryMonthReportInfo(reportId);
            }
            else if (taskAssgineeDto!=null && taskAssgineeDto.getExecutionId()!=null && !"".equals(taskAssgineeDto.getExecutionId())) 
            {
                flowId=taskAssgineeDto.getExecutionId();
                monthReports = tWorkMonthReportsService.getMonthReportByFlowId(flowId);
            }
        } catch (Exception e) {
            log.error("获取客户详情报错。", e);
        }finally{
            
        }
    	queryDefault();
        return "approve";
    }
    
    /**
     * 分管领导审核界面
     * @return
     */
    public String monthReportApprove2(){
        try {
            if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey())){
                reportId=taskAssgineeDto.getBusinessKey();
                monthReports=tWorkMonthReportsService.queryMonthReportInfo(reportId);
            }
            else if (taskAssgineeDto!=null && taskAssgineeDto.getExecutionId()!=null && !"".equals(taskAssgineeDto.getExecutionId())) 
            {
                flowId=taskAssgineeDto.getExecutionId();
                monthReports = tWorkMonthReportsService.getMonthReportByFlowId(flowId);
            }
        } catch (Exception e) {
            log.error("获取客户详情报错。", e);
        }finally{
            
        }
    	queryDefault();
        return "approve";
    }
    
    /**
     * 行领导批示
     * @return
     */
    public String monthReportInstructions(){
        try {
            if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey())){
                reportId=taskAssgineeDto.getBusinessKey();
                monthReports=tWorkMonthReportsService.queryMonthReportInfo(reportId);
            }
            else if (taskAssgineeDto!=null && taskAssgineeDto.getExecutionId()!=null && !"".equals(taskAssgineeDto.getExecutionId())) 
            {
                flowId=taskAssgineeDto.getExecutionId();
                monthReports = tWorkMonthReportsService.getMonthReportByFlowId(flowId);
            }
            taskAssgineeDto.setBeginOrg(monthReports.getOrgid());
        } catch (Exception e) {
            log.error("获取客户详情报错。", e);
        }finally{
            
        }
    	queryDefault();
        return "instructions";
    }
    /**
     * 办公室汇总
     * @return
     */
    public String monthReportGather(){
        try {
            if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey())){
                reportId=taskAssgineeDto.getBusinessKey();
                monthReports=tWorkMonthReportsService.queryMonthReportInfo(reportId);
             }
	        else if (taskAssgineeDto!=null && taskAssgineeDto.getExecutionId()!=null && !"".equals(taskAssgineeDto.getExecutionId())) 
	        {
	            flowId=taskAssgineeDto.getExecutionId();
	            monthReports = tWorkMonthReportsService.getMonthReportByFlowId(flowId);
	        }
            taskAssgineeDto.setBeginOrg(monthReports.getOrgid());
        } catch (Exception e) {
            log.error("获取客户详情报错。", e);
        }finally{
            
        }
    	queryDefault();
        return "gather";
    }
    
        
    public TWorkMonthReports getMonthReports() {
        return monthReports;
    }

    public void setMonthReports(TWorkMonthReports monthReports) {
        this.monthReports = monthReports;
    }

    public String getBtnType() {
        return btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType;
    }

    public String[] getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String[] uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public TApproveOpninion getOpninion() {
        return opninion;
    }

    public void setOpninion(TApproveOpninion opninion) {
        this.opninion = opninion;
    }

    public List<TApproveOpninion> getOpninions() {
        return opninions;
    }

    public void setOpninions(List<TApproveOpninion> opninions) {
        this.opninions = opninions;
    }

    /**
     * @return the monthReportFiles
     * 本月计划附件
     */
    public File[] getMonthReportFiles() {
        return monthReportFiles;
    }

    /**
     * @param monthReportFiles the monthReportFiles to set
     * 本月计划附件
     */
    public void setMonthReportFiles(File[] monthReportFiles) {
        this.monthReportFiles = monthReportFiles;
    }

    /**
     * @return the completionFiles
     * 完成情况附件
     */
    public File[] getCompletionFiles() {
        return completionFiles;
    }

    /**
     * @param completionFiles the completionFiles to set
     * 完成情况附件
     */
    public void setCompletionFiles(File[] completionFiles) {
        this.completionFiles = completionFiles;
    }

    /**
     * @return the monthReportFilesFileName
     * 本月计划附件的名称
     */
    public String[] getMonthReportFilesFileName() {
        return monthReportFilesFileName;
    }

    /**
     * @param monthReportFilesFileName the monthReportFilesFileName to set
     * 本月计划附件的名称
     */
    public void setMonthReportFilesFileName(String[] monthReportFilesFileName) {
        this.monthReportFilesFileName = monthReportFilesFileName;
    }

    /**
     * @return the completionFilesFileName
     * 完成情况附件的名称
     */
    public String[] getCompletionFilesFileName() {
        return completionFilesFileName;
    }

    /**
     * @param completionFilesFileName the completionFilesFileName to set
     * 完成情况附件的名称
     */
    public void setCompletionFilesFileName(String[] completionFilesFileName) {
        this.completionFilesFileName = completionFilesFileName;
    }

    /**
     * @return the nodeName1
     */
    public String getNodeName1() {
        return nodeName1;
    }

    /**
     * @param nodeName1 the nodeName1 to set
     */
    public void setNodeName1(String nodeName1) {
        this.nodeName1 = nodeName1;
    }

    /**
     * @return the nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the returns
     */
    public List<TWorkMonthReports> getReturns() {
        return returns;
    }

    /**
     * @param returns the returns to set
     */
    public void setReturns(List<TWorkMonthReports> returns) {
        this.returns = returns;
    }

    /**
     * @return the taskAssgineeDto
     */
    public TaskAssgineeDto getTaskAssgineeDto() {
        return taskAssgineeDto;
    }

    /**
     * @param taskAssgineeDto the taskAssgineeDto to set
     */
    public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
        this.taskAssgineeDto = taskAssgineeDto;
    }

    /**
     * @return the reportId
     */
    public Long getReportId() {
        return reportId;
    }

    /**
     * @param reportId the reportId to set
     */
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    /**
     * @return the flowId
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * @param flowId the flowId to set
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * @return the isView
     */
    public String getIsView() {
        return isView;
    }

    /**
	 * @return isFirst
	 */
	public String getIsFirst() {
		return isFirst;
	}

	/**
	 * @param isFirst 要设置的 isFirst
	 */
	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	/**
     * @param isView the isView to set
     */
    public void setIsView(String isView) {
        this.isView = isView;
    }

    public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	/**
     * @return the gatherFiles
     */
    public File[] getGatherFiles() {
        return gatherFiles;
    }

    /**
     * @param gatherFiles the gatherFiles to set
     */
    public void setGatherFiles(File[] gatherFiles) {
        this.gatherFiles = gatherFiles;
    }

    /**
     * @return the gatherFilesFileName
     */
    public String[] getGatherFilesFileName() {
        return gatherFilesFileName;
    }

    /**
     * @param gatherFilesFileName the gatherFilesFileName to set
     */
    public void setGatherFilesFileName(String[] gatherFilesFileName) {
        this.gatherFilesFileName = gatherFilesFileName;
    }
    
    
}