package com.gotop.dataApply.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.dataApply.model.TApplyData;
import com.gotop.dataApply.service.ITApplyDataService;
import com.gotop.dataIssued.model.TSendData;
import com.gotop.dto.BusinessDto;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.messagePublish.dao.ITMessagePublishDAO;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.opinion.model.TDefaultOpinion;
import com.gotop.opinion.service.ITDefaultOpinionService;
import com.gotop.util.Struts2Utils;
import com.gotop.util.XmlConvert;
import com.gotop.util.string.Obj2StrUtils;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import junit.awtui.Logo;

import org.apache.struts2.ServletActionContext;

public class TApplyDataAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITApplyDataService tApplyDataService;
    
    /**
     * jbmp服务
     */
	protected JbpmService jbpmService;
    
	/**
	 * 与流程有关的数据传输对象
	 */
    private TaskAssgineeDto taskAssgineeDto;
    
    /**
     * 信息发布Dao
     */
    private ITMessagePublishDAO tMessagePublishDAO;
    
    /**
     * 数据申请类
     */
    private TApplyData applyData;
    
    /**
     * 文件名称
     */
    private String[] fileName;
    
    /**
     * 上传的文件
     */
	private File[] upload;
	
	/**
	 * 上传的文件名称
	 */
    private String[] uploadFileName;
    
    /**
     * 上传的文件内容类型
     */
	private String[] uploadContentType;
	
	/**
	 * 流程实例id
	 */
	private String processInstanceId;
	
	/**
	 * 操作人
	 */
	private Long taskAssingee;
	
	/**
	 * 任务id
	 */
	private String taskId;
	
	/**
	 * 数据使用人员
	 */
	private String dataUser;
	
	/**
	 * 数据表来源
	 */
	private Long resourceId;
	
	/**
	 * 文件类型
	 */
	private String fileType;
	
	/**
	 * 提交原因
	 */
	private String submitReason;
	
	/**
	 * 分析报告
	 */
	private String analysisReport;
	
	/**
	 * 是否已办
	 */
	private String isView;
	
	/**
	 * 流程定义id
	 */
	private String definitionId;
	
	/**
	 * 业务类型
	 */
	private String businessType;

	/**
	 * 审核意见
	 */
	private TApproveOpninion approveOpninion;
	
	private List<TApplyData> applyDataList;
	
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

	/**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settApplyDataService(ITApplyDataService tApplyDataService) {
        this.tApplyDataService = tApplyDataService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITApplyDataService gettApplyDataService() {
        return this.tApplyDataService;
    }

    public TaskAssgineeDto getTaskAssgineeDto() {
		return taskAssgineeDto;
	}

	public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
		this.taskAssgineeDto = taskAssgineeDto;
	}

	public TApplyData getApplyData() {
		return applyData;
	}

	public void setApplyData(TApplyData applyData) {
		this.applyData = applyData;
	}

	public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public String[] getFileName() {
		return fileName;
	}

	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Long getTaskAssingee() {
		return taskAssingee;
	}

	public void setTaskAssingee(Long taskAssingee) {
		this.taskAssingee = taskAssingee;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getDataUser() {
		return dataUser;
	}

	public void setDataUser(String dataUser) {
		this.dataUser = dataUser;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getSubmitReason() {
		return submitReason;
	}

	public void setSubmitReason(String submitReason) {
		this.submitReason = submitReason;
	}

	public String getAnalysisReport() {
		return analysisReport;
	}

	public void setAnalysisReport(String analysisReport) {
		this.analysisReport = analysisReport;
	}

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public TApproveOpninion getApproveOpninion() {
		return approveOpninion;
	}

	public void setApproveOpninion(TApproveOpninion approveOpninion) {
		this.approveOpninion = approveOpninion;
	}
	
	/**
	 * 获取数据申请表为跳转到申请页
	 * @return
	 * @throws Exception
	 */
	public String getApplyDataforApply() throws Exception {
		try{
			MUOUserSession muo=getCurrentOnlineUser();
	    	if(taskAssgineeDto!=null && taskAssgineeDto.getBusinessKey()!=null && 0l!=taskAssgineeDto.getBusinessKey()){
	    		applyData = tApplyDataService.getApplyDataById(taskAssgineeDto.getBusinessKey());
	    		applyData.setFlowId(taskAssgineeDto.getExecutionId());
	    	}
	    	if(taskAssgineeDto!=null && taskAssgineeDto.getExecutionId()!=null && !"".equals(taskAssgineeDto.getExecutionId())){
	    		applyData = tApplyDataService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
	    	}
			if(applyData!=null){
				applyData.setFlowId(taskAssgineeDto.getExecutionId());
		    	taskAssgineeDto.setBusinessKey(applyData.getDaId());
			}
			taskAssgineeDto.setBeginOrg(muo.getOrgid());
			queryDefault();
    	}catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
    	return "getOldApply";
    }
	
	/**
	 * 获取数据申请表为跳转到部门领导审核页
	 * @return
	 * @throws Exception
	 */
	public String getApplyDataforApprove1() throws Exception {
		try{
			if(taskAssgineeDto.getNextTaskId()!=null)
				taskAssgineeDto.setTaskName(jbpmService.getTaskNameById(taskAssgineeDto.getNextTaskId()));
			if(taskAssgineeDto.getBusinessKey()!=null)
				applyData = tApplyDataService.getApplyDataById(taskAssgineeDto.getBusinessKey());
			else
				applyData = tApplyDataService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
    		applyData.setFlowId(taskAssgineeDto.getExecutionId());
    		taskAssgineeDto.setBeginOrg(applyData.getOrgId());
    		taskAssgineeDto.setBeginAssingee(applyData.getEmpId());
			queryDefault();
		}catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "approve1";
	}
	
	/**
	 * 获取数据申请表为跳转到风险管理部部门领导审核页
	 * @return
	 * @throws Exception
	 */
	public String getApplyDataforApprove2() throws Exception {
		try{
			if(taskAssgineeDto.getNextTaskId()!=null)
				taskAssgineeDto.setTaskName(jbpmService.getTaskNameById(taskAssgineeDto.getNextTaskId()));
			if(taskAssgineeDto.getBusinessKey()!=null)
				applyData = tApplyDataService.getApplyRiskAnalyst(taskAssgineeDto.getBusinessKey());
			else
				applyData = tApplyDataService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
    		applyData.setFlowId(taskAssgineeDto.getExecutionId());
    		taskAssgineeDto.setBeginOrg(applyData.getOrgId());
    		taskAssgineeDto.setBeginAssingee(applyData.getEmpId());
			queryDefault();
		}catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "approve2";
	}
	
	/**
	 * 获取数据申请表为跳转到信息科技部部门领导审核页
	 * @return
	 * @throws Exception
	 */
	public String getApplyDataforApprove3() throws Exception {
		try{
			if(taskAssgineeDto.getNextTaskId()!=null)
				taskAssgineeDto.setTaskName(jbpmService.getTaskNameById(taskAssgineeDto.getNextTaskId()));
			if(taskAssgineeDto.getBusinessKey()!=null)
				applyData = tApplyDataService.getApplyDataAnalyst(taskAssgineeDto.getBusinessKey());
			else
				applyData = tApplyDataService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
    		applyData.setFlowId(taskAssgineeDto.getExecutionId());
    		taskAssgineeDto.setBeginAssingee(taskAssgineeDto.getPreTaskAssingee());
			queryDefault();
		}catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "approve3";
	}
	
	/**
	 * 获取数据申请表为跳转到分管行领导审核页
	 * @return
	 * @throws Exception
	 */
	public String getApplyDataforApprove4() throws Exception {
		try{
			if(taskAssgineeDto.getNextTaskId()!=null)
				taskAssgineeDto.setTaskName(jbpmService.getTaskNameById(taskAssgineeDto.getNextTaskId()));
			if(taskAssgineeDto.getBusinessKey()!=null)
				applyData = tApplyDataService.getApplyAnalyst(taskAssgineeDto.getBusinessKey());
			else
				applyData = tApplyDataService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
    		/*String taskName = taskAssgineeDto.getTaskName();
    		if(taskName.equals("信息科技部部门领导审核")){
    			applyData.setRisk("");
    		}*/
    		applyData.setFlowId(taskAssgineeDto.getExecutionId());
    		taskAssgineeDto.setBeginAssingee(applyData.getEmpId());
    		taskAssgineeDto.setBeginOrg(applyData.getOrgId());
			queryDefault();
    		
		}catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "approve4";
	}
	
	/**
	 * 获取数据申请表为跳转到展示表
	 * @return
	 * @throws Exception
	 */
	public String getApplyDataforShow() throws Exception {
		try{
			if(taskAssgineeDto.getNextTaskId()!=null)
				taskAssgineeDto.setTaskName(jbpmService.getTaskNameById(taskAssgineeDto.getNextTaskId()));
			if(taskAssgineeDto.getBusinessKey()!=null)
				applyData = tApplyDataService.getApplyDataById(taskAssgineeDto.getBusinessKey());
			else
				applyData = tApplyDataService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
    		applyData.setFlowId(taskAssgineeDto.getExecutionId());
			queryDefault();
		}catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "show";
	}
	/**
	 * 获取数据申请表为跳转到数据分析员展示
	 * @return
	 * @throws Exception
	 */
	public String getApplyDataforShow2() throws Exception {
		try{
			if(taskAssgineeDto.getNextTaskId()!=null)
				taskAssgineeDto.setTaskName(jbpmService.getTaskNameById(taskAssgineeDto.getNextTaskId()));
			if(taskAssgineeDto.getBusinessKey()!=null)
				applyData = tApplyDataService.getApplyDataById(taskAssgineeDto.getBusinessKey());
			else
				applyData = tApplyDataService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
    		applyData.setFlowId(taskAssgineeDto.getExecutionId());
			queryDefault();
		}catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "show";
	}
	
	/**
	 * 获取数据申请表为跳转到风险可行性分析
	 * @return
	 * @throws Exception
	 */
	public String getApplyDataforRisk() throws Exception {
		try{
			MUOUserSession muo = getCurrentOnlineUser();
			if(taskAssgineeDto.getNextTaskId()!=null)
				taskAssgineeDto.setTaskName(jbpmService.getTaskNameById(taskAssgineeDto.getNextTaskId()));
			if(taskAssgineeDto.getBusinessKey()!=null)
				applyData = tApplyDataService.getApplyDataById(taskAssgineeDto.getBusinessKey());
			else
				applyData = tApplyDataService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
    		applyData.setFlowId(taskAssgineeDto.getExecutionId());
    		taskAssgineeDto.setBeginOrg(applyData.getOrgId());
    		taskAssgineeDto.setBeginAssingee(applyData.getEmpId());
			queryDefault();
		}catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "analysis";
	}
	
	/**
	 * 获取数据申请表为跳转到提取可行性分析
	 * @return
	 * @throws Exception
	 */
	public String getApplyDataforFetch() throws Exception {
		try{
			MUOUserSession muo = getCurrentOnlineUser();
			if(taskAssgineeDto.getNextTaskId()!=null)
				taskAssgineeDto.setTaskName(jbpmService.getTaskNameById(taskAssgineeDto.getNextTaskId()));
			if(taskAssgineeDto.getBusinessKey()!=null)
				applyData = tApplyDataService.getApplyRiskAnalyst(taskAssgineeDto.getBusinessKey());
			else
				applyData = tApplyDataService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
    		applyData.setFlowId(taskAssgineeDto.getExecutionId());
    		taskAssgineeDto.setBeginOrg(applyData.getOrgId());
    		taskAssgineeDto.setBeginAssingee(applyData.getEmpId());
			queryDefault();
		}catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "analysis";
	}
	
	/**
	 * 保存或更行数据申请表
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdateApply() throws Exception {
    	String info ="success";
		try{
    		MUOUserSession muo=getCurrentOnlineUser();
    		applyData.setEmpId(muo.getEmpid());
    		applyData.setOrgId(muo.getOrgid());
	    	
	    	applyData.setNodeId(taskId);
	    	applyData.setCreateDate(TimeUtil.today());
	    	applyData.setCreateTime(TimeUtil.now());
	    	if(taskAssgineeDto!=null){
	    		applyData.setNodeName(taskAssgineeDto.getTaskName());
	    		taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
	    		taskAssgineeDto.setExecutionId(applyData.getFlowId());
	    		taskAssgineeDto.setTaskId(taskId);
	    		taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
	    	}
	    	BusinessDto businessDto = new BusinessDto();
	    	businessDto.setUpload(upload);
	    	businessDto.setUploadFileName(uploadFileName);
	    	businessDto.setTaskId(taskId);
	    	businessDto.setSubmitReason(submitReason);
	    	businessDto.setDefinitionId(definitionId);
	    	businessDto.setBusinessType(businessType);
	    	
	    	//插入可修改
	    	if("1".equals(submitReason))
	    		packApproveOpinion("05");
	    	if(applyData.getDaId()==null || applyData.getDaId()==0){
	    		tApplyDataService.insert(applyData,muo,businessDto,taskAssgineeDto,approveOpninion);
	    	}else{
	    		tApplyDataService.update(applyData,muo,businessDto,taskAssgineeDto,approveOpninion);
	    	}
	    	//putDtoTojsp(dsId,muo,sdf);
    	}catch (Exception e) {
    		info="fails";
    		log.error("[新增失败]", e);
			throw e;
    	}
    	Struts2Utils.renderText(info);
    	return null;
	}
	
	/**
	 * 意见审核
	 * @return
	 * @throws Exception
	 */
	public String approveApply() throws Exception {
    	String info ="success";
		try{
			//opinion赋值
			if("退回".equals(taskAssgineeDto.getTransitionName()))
				packApproveOpinion("02");
			else if("同意".equals(taskAssgineeDto.getTransitionName()))
				packApproveOpinion("01");
			else
				packApproveOpinion("05");
    		//为传递的对象赋值
	    	BusinessDto businessDto = new BusinessDto();
	    	businessDto.setTaskId(taskId);
	    	businessDto.setDataUser(dataUser);
	    	businessDto.setResourceId(resourceId);
	    	if(applyData!=null){
		    	if(applyData.getrEmpName()!=null && !"".equals(applyData.getrEmpName()) && !"null".equals(applyData.getrEmpName()))
		    		if(applyData.getdEmpName()!=null && !"".equals(applyData.getdEmpName()) && !"null".equals(applyData.getdEmpName())){
		    			applyData.setDleader(MUO.getCurrentOnlineUser().getEmpname());
		    			applyData.setDopin(approveOpninion.getOpninionContent());
		    		}else{
		    			applyData.setRleader(MUO.getCurrentOnlineUser().getEmpname());
		    			applyData.setRopin(approveOpninion.getOpninionContent());
		    		}
	    	}
    		tApplyDataService.insertApprove(applyData,approveOpninion,businessDto,packTaskAssgineeDto(approveOpninion.getResourceId()));
	    	
    	}catch (Exception e){
    		info="fails";
    		log.error("[审核失败]", e);
			throw e;
    	}
    	Struts2Utils.renderText(info);
    	return null;
	}

	/**
	 * 申请人将流程提交到下一个办理人
	 * @throws Exception
	 */
	public void completeTask() throws Exception {
    	String info ="success";
		try {
			tApplyDataService.completeTask(taskId,packTaskAssgineeDto(resourceId));
		} catch (Exception e) {
    		info="fails";
			log.error("[无法提交]",e);
			throw e;
		}
    	Struts2Utils.renderText(info);
	}
	
	/**
	 * 提交分析员信息
	 * @throws Exception
	 */
	public void submitAnalysis() throws Exception {
    	String info ="success";
		try {
    		//为传递的对象赋值
	    	BusinessDto businessDto = new BusinessDto();
	    	businessDto.setTaskId(taskId);
	    	businessDto.setAnalysisReport(analysisReport);
	    	businessDto.setResourceId(resourceId);
	    	if("退回".equals(taskAssgineeDto.getTransitionName()))
				packApproveOpinion("02");
			else
				packApproveOpinion("01");
	    	businessDto.setTime(applyData.getDaTlimit());
			tApplyDataService.submitAnalysis(packTaskAssgineeDto(resourceId),businessDto,approveOpninion);
		} catch (Exception e) {
    		info="fails";
			log.error("[无法提交]",e);
			throw e;
		}
    	Struts2Utils.renderText(info);
	}

	/**
	 * 给taskAssgineeDto赋值
	 * @return
	 */
	private TaskAssgineeDto packTaskAssgineeDto(Long businessKey) throws Exception {
		//设置dto信息
    	MUOUserSession muo=getCurrentOnlineUser();
    	taskAssgineeDto.setExecutionId(processInstanceId);
		taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
		taskAssgineeDto.setPreTaskId(taskId);
		taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
		taskAssgineeDto.setPreTaskTime(TimeUtil.today()+TimeUtil.now());
		taskAssgineeDto.setBusinessKey(businessKey);
		taskAssgineeDto.setBusinessType("05");
		taskAssgineeDto.setTaskAssingee(taskAssingee);
		taskAssgineeDto.setTaskId(taskId);
		taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
		return taskAssgineeDto;
	}
	
	/**
	 * 给opinion赋值
	 */
	private void packApproveOpinion(String submitType) {
		MUOUserSession muo=getCurrentOnlineUser();
    	//设置审核表信息
		if(approveOpninion == null)
			approveOpninion = new TApproveOpninion();
    	approveOpninion.setOperator(muo.getEmpid());
    	approveOpninion.setOrgid(String.valueOf(muo.getOrgid()));
    	approveOpninion.setResourceType("05");
    	approveOpninion.setOperaterDate(TimeUtil.today());
    	approveOpninion.setOperaterTime(TimeUtil.now());
    	approveOpninion.setNodeId(taskId);
    	if(taskId!=null && !"".equals(taskId) && !"null".equals(taskId))
    		approveOpninion.setNodeName(jbpmService.getTaskById(taskId).getName());
    	approveOpninion.setResourceId(resourceId);
    	approveOpninion.setNextOprName("");
    	approveOpninion.setNextorgname("");
    	/**
    	 * 20140903
    	 */
    	approveOpninion.setNextorgname("");
    	if(taskAssgineeDto.getEmpNames()!=null&&!"".equals(taskAssgineeDto.getEmpNames())){
    		approveOpninion.setNextOprName(taskAssgineeDto.getEmpNames());
    		List<HashMap<String,Object>> list = tMessagePublishDAO.queryOrgName(taskAssgineeDto.getEmpIds());
    		for(int i=0;i<list.size();i++){
    			approveOpninion.setNextorgname(approveOpninion.getNextorgname()+(String) list.get(i).get("ORGNAME"));
    			if(i!=list.size()-1){
    				approveOpninion.setNextorgname(approveOpninion.getNextorgname()+",");
    			}
    		}
    		
    	}
    	approveOpninion.setOperatorType(submitType);
    	/*if(approveOpninion.getOpninionContent()==null || "".equals(approveOpninion.getOpninionContent())){
    		approveOpninion.setOpninionContent("已处理");
    	}
    	if("回退".equals(taskAssgineeDto.getTransitionName())){
			approveOpninion.setOperatorType("02");
		}else{
			approveOpninion.setOperatorType("01");
		}*/
	}
	
	/**
	 * 按条件查询所有申请表
	 * @return
	 * @throws Exception 
	 */
	public String queryAllDataApply() throws Exception{
		try{
			if(applyData == null){
				applyData = new TApplyData();
			}
			MUOUserSession muo = this.getCurrentOnlineUser();
			String positionCode = Obj2StrUtils.join(muo.getPosiCode(), String.class, ",");
			if(positionCode.indexOf("XXKJBR")<=-1){
				applyData.setEmpId(muo.getEmpid());
			}
			applyDataList = this.tApplyDataService.queryAllDataApply(applyData,this.getPage());
			return "list";
		}catch(Exception e){
			log.error("[获取数据申请列表失败]",e);
			throw e;
		}
	}
	
	/**
	 * 导出列表
	 * @return
	 * @throws Exception
	 */
	public String downexl() throws Exception{
		MUOUserSession muo = getCurrentOnlineUser();
		if(applyData == null)
			applyData = new TApplyData();
		applyData.setEmpId(muo.getEmpid());
		try {
			String positionId  = Obj2StrUtils.join(muo.getPosiCode(), String.class, ",");
			if(positionId.indexOf("'XXKJBR'")>-1)
				applyData.setEmpId(null);
			applyDataList = tApplyDataService.queryAllDataApply(applyData,null);
		} catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "downexl";
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * @return tMessagePublishDAO
	 */
	public ITMessagePublishDAO gettMessagePublishDAO() {
		return tMessagePublishDAO;
	}

	/**
	 * @param tMessagePublishDAO 要设置的 tMessagePublishDAO
	 */
	public void settMessagePublishDAO(ITMessagePublishDAO tMessagePublishDAO) {
		this.tMessagePublishDAO = tMessagePublishDAO;
	}

	public List<TApplyData> getApplyDataList() {
		return applyDataList;
	}

	public void setApplyDataList(List<TApplyData> applyDataList) {
		this.applyDataList = applyDataList;
	}
    
    
}