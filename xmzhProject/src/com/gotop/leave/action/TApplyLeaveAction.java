package com.gotop.leave.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.dto.BusinessDto;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.leave.model.TApplyLeave;
import com.gotop.leave.service.ITApplyLeaveService;
import com.gotop.messagePublish.dao.ITMessagePublishDAO;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.opinion.model.TDefaultOpinion;
import com.gotop.opinion.service.ITDefaultOpinionService;
import com.gotop.util.Struts2Utils;
import com.gotop.util.XmlConvert;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class TApplyLeaveAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITApplyLeaveService tapplyLeaveService;
    protected ITFileResourceTableService fileResourceTableService;
    protected JbpmService jbpmService;

    private TApplyLeave tapplyLeave;
    private TaskAssgineeDto taskAssgineeDto;
    private ITMessagePublishDAO tMessagePublishDAO;
    private ITApproveOpninionDAO tApproveOpninionDAO;
    private TApproveOpninion approveOpninion;
    
    private List<TFileResourceTable> fileResourceTables;
	private String[] fileName;
	private File[] upload;
    private String[] uploadFileName;
	private String[] uploadContentType;
	private String taskId;
	
	private String fileType;
	private String isView;
	private String definitionId;
	private String businessType;
	
	private String submitReason;
	private List<TFileResourceTable> fileList;
	private List<TApplyLeave> leaveList;

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
    public List<TApplyLeave> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(List<TApplyLeave> leaveList) {
		this.leaveList = leaveList;
	}

	public List<TFileResourceTable> getFileList() {
		return fileList;
	}

	public void setFileList(List<TFileResourceTable> fileList) {
		this.fileList = fileList;
	}

	/**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void setFileResourceTableService(ITFileResourceTableService tFileResourceTableService) {
        this.fileResourceTableService = tFileResourceTableService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITFileResourceTableService getFileResourceTableService() {
        return this.fileResourceTableService;
    }
    
    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void setTapplyLeaveService(ITApplyLeaveService tApplyLeaveService) {
        this.tapplyLeaveService = tApplyLeaveService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITApplyLeaveService getTapplyLeaveService() {
        return this.tapplyLeaveService;
    }

    public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public TApplyLeave getTapplyLeave() {
		return tapplyLeave;
	}

	public void setTapplyLeave(TApplyLeave tApplyLeave) {
		this.tapplyLeave = tApplyLeave;
	}

	public TaskAssgineeDto getTaskAssgineeDto() {
		return taskAssgineeDto;
	}

	public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
		this.taskAssgineeDto = taskAssgineeDto;
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

	/**
	 * @return tApproveOpninionDAO
	 */
	public ITApproveOpninionDAO gettApproveOpninionDAO() {
		return tApproveOpninionDAO;
	}

	/**
	 * @param tApproveOpninionDAO 要设置的 tApproveOpninionDAO
	 */
	public void settApproveOpninionDAO(ITApproveOpninionDAO tApproveOpninionDAO) {
		this.tApproveOpninionDAO = tApproveOpninionDAO;
	}

	public List<TFileResourceTable> getFileResourceTables() {
		return fileResourceTables;
	}

	public void setFileResourceTables(List<TFileResourceTable> fileResourceTables) {
		this.fileResourceTables = fileResourceTables;
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getSubmitReason() {
		return submitReason;
	}

	public void setSubmitReason(String submitReason) {
		this.submitReason = submitReason;
	}

	public TApproveOpninion getApproveOpninion() {
		return approveOpninion;
	}

	public void setApproveOpninion(TApproveOpninion approveOpninion) {
		this.approveOpninion = approveOpninion;
	}

	/**
     * 保存或修改请假表
     * @return
     * @throws Exception
     */
    public String saveOrUpdateLeave() throws Exception{
    	String info ="success";
    	try{
    		MUOUserSession muo=getCurrentOnlineUser();
	    	tapplyLeave.setEmpId(muo.getEmpid());
	    	tapplyLeave.setOrgid(muo.getOrgid());
	    	tapplyLeave.setCreateDate(TimeUtil.today());
	    	tapplyLeave.setCreateTime(TimeUtil.now());
	    	
	    	tapplyLeave.setNodeId(taskId);
	    	if(taskAssgineeDto!=null){
	    		tapplyLeave.setNodeName(taskAssgineeDto.getTaskName());
	    		taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
	    		taskAssgineeDto.setExecutionId(tapplyLeave.getFlowId());
	    		taskAssgineeDto.setTaskId(taskId);
	    		taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
		    	packApproveOpinion("05");
	    	}
	    	BusinessDto businessDto = new BusinessDto();
	    	businessDto.setUpload(upload);
	    	businessDto.setUploadFileName(uploadFileName);
	    	businessDto.setTaskId(taskId);
	    	businessDto.setSubmitReason(submitReason);
	    	businessDto.setDefinitionId(definitionId);
	    	businessDto.setBusinessType(businessType);
	    	businessDto.setApproveOpninion(approveOpninion);
	    	if(tapplyLeave.getLvId()==null || tapplyLeave.getLvId()==0){
	    		tapplyLeaveService.insert(tapplyLeave,muo,businessDto,taskAssgineeDto);
	    	}else{
	    		tapplyLeaveService.update(tapplyLeave,muo,businessDto,taskAssgineeDto);
	    	}
    	}catch (Exception e) {
    		info="fails";
    		log.error("[新增失败]", e);
			throw e;
    	}
    	Struts2Utils.renderText(info);
    	return null;
    }
    
    /**
     * 请假审核
     * @return
     * @throws Exception
     */
    public String leaveApprove() throws Exception {
    	String info ="success";
    	try{
	    	packApproveOpinion("");
	    	packTaskAssgineeDto(approveOpninion.getResourceId());
	    	
	    	if("同意".equals(taskAssgineeDto.getTransitionName()) ){
	    		approveOpninion.setOperatorType("01");
	    		tapplyLeaveService.insertApprove(approveOpninion,taskAssgineeDto,taskId,1);
	    	}else if( "审核结束".equals(taskAssgineeDto.getTransitionName())){
	    		approveOpninion.setOperatorType("08");
	    		tapplyLeaveService.insertApprove(approveOpninion,taskAssgineeDto,taskId,0);
	    	}else if( "退回".equals(taskAssgineeDto.getTransitionName())){
	    		approveOpninion.setOperatorType("02");
	    		tapplyLeaveService.insertApprove(approveOpninion,taskAssgineeDto,taskId,2);
	    	}
    	}catch (Exception e){
    		info="fails";
    		log.error("[审核失败]", e);
			throw e;
    	}
    	Struts2Utils.renderText(info);
    	return null;
    }
    
    /**
     * 获取所有请假表
     * @return
     * @throws Exception
     */
    public String queryAllDataList() throws Exception {
    	leaveList = tapplyLeaveService.queryAllDataList(this.getPage());
    	return "queryAll";
    }
    
/*    public String searchByMap() throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("title", lv_title);
    	map.put("applicant", applicant);
    	map.put("lv_reason", lv_reason);
    	map.put("lv_type", lv_type);
    	List<TApplyLeave> leaveList = tapplyLeaveService.queryByMap(this.getPage(),map);
    	this.setPage(this.getPage());
    	ServletActionContext.getRequest().setAttribute("leaveList", leaveList);
    	return "queryAll";
    }*/
    
    /**
     * 获取请假表信息
     * @return
     * @throws Exception
     */
    public String getLeaveforApply() throws Exception{
    	MUOUserSession muo=getCurrentOnlineUser();
    	if(taskAssgineeDto!=null && taskAssgineeDto.getBusinessKey()!=null && 0l!=taskAssgineeDto.getBusinessKey()){
    		tapplyLeave = tapplyLeaveService.getLeaveById(taskAssgineeDto.getBusinessKey());
    		tapplyLeave.setFlowId(taskAssgineeDto.getExecutionId());
    		//fileList = fileResourceTableService.queryFileByIdandType(taskAssgineeDto.getBusinessKey(),"08",fileType);
    	}
    	if(taskAssgineeDto!=null && taskAssgineeDto.getExecutionId()!=null && !"".equals(taskAssgineeDto.getExecutionId())){
    		tapplyLeave = tapplyLeaveService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
    	}
		if(tapplyLeave!=null){
	   		tapplyLeave.setFlowId(taskAssgineeDto.getExecutionId());
	    	taskAssgineeDto.setBusinessKey(tapplyLeave.getLvId());
		}
    	taskAssgineeDto.setBeginOrg(muo.getOrgid());
    	queryDefault();
    	return "getOldLeave";
    }
    
    /**
     * 通过主键获取请假表信息
     * @return
     * @throws Exception
     */
    public String getLeaveById() throws Exception{
		try {
			if(taskAssgineeDto.getNextTaskId()!=null)
				taskAssgineeDto.setTaskName(jbpmService.getTaskNameById(taskAssgineeDto.getNextTaskId()));
			if(taskAssgineeDto.getBusinessKey()!=null)
				tapplyLeave = tapplyLeaveService.getLeaveById(taskAssgineeDto.getBusinessKey());
			else
				tapplyLeave = tapplyLeaveService.getLeaveByFlowId(taskAssgineeDto.getExecutionId());
			tapplyLeave.setFlowId(taskAssgineeDto.getExecutionId());
	    	taskAssgineeDto.setBeginAssingee(tapplyLeave.getEmpId());
	    	taskAssgineeDto.setBeginOrg(tapplyLeave.getOrgid());
			//fileList = fileResourceTableService.queryFileByIdandType(taskAssgineeDto.getBusinessKey(),"08",fileType);
		} catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
    	queryDefault();
    	return "info";
    }

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
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

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
    


	/**
	 * 给taskAssgineeDto赋值
	 * @return
	 */
	private TaskAssgineeDto packTaskAssgineeDto(Long businessKey) throws Exception {
		//设置dto信息
    	MUOUserSession muo=getCurrentOnlineUser();
		taskAssgineeDto.setExecutionId(tapplyLeave.getFlowId());
    	taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
    	taskAssgineeDto.setPreTaskId(taskId);
    	taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
    	taskAssgineeDto.setBusinessKey(approveOpninion.getResourceId());
    	taskAssgineeDto.setPreTaskTime(approveOpninion.getOperaterDate()+approveOpninion.getOperaterTime());
    	taskAssgineeDto.setBusinessType("08");
		taskAssgineeDto.setTaskId(taskId);
		taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
		return taskAssgineeDto;
	}
	
	/**
	 * 给opinion赋值
	 */
	private void packApproveOpinion(String submitType) {
		MUOUserSession muo=getCurrentOnlineUser();
		if(approveOpninion==null)
    		approveOpninion = new TApproveOpninion();
    	approveOpninion.setOperator(muo.getEmpid());
    	approveOpninion.setOrgid(String.valueOf(muo.getOrgid()));
    	approveOpninion.setResourceType("08");
    	approveOpninion.setOperaterDate(TimeUtil.today());
    	approveOpninion.setOperaterTime(TimeUtil.now());
    	approveOpninion.setNodeId(taskId);
    	if(taskId!=null && !"".equals(taskId) && !"null".equals(taskId))
    		approveOpninion.setNodeName(jbpmService.getTaskById(taskId).getName());
    	approveOpninion.setNextOprName("");
    	approveOpninion.setOperatorType(submitType);
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
	}
    
    
}