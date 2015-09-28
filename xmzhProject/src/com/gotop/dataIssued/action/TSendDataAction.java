package com.gotop.dataIssued.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.dataIssued.model.TSendData;
import com.gotop.dataIssued.service.ITSendDataService;
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
import com.gotop.util.string.Obj2StrUtils;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;

public class TSendDataAction extends BaseAction {
    
	protected ITSendDataService sendDataService;
	protected ITFileResourceTableService fileResourceTableService;
    protected JbpmService jbpmService;
    private ITMessagePublishDAO tMessagePublishDAO;
    private TSendData sendData;
    
    private String userData;
    
    private TaskAssgineeDto taskAssgineeDto;
    
    private List<TFileResourceTable> fileResourceTables;
	private String[] fileName;
	private File[] upload;
    private String[] uploadFileName;
	private String[] uploadContentType;
	private String processInstanceId;
	private Long taskAssingee;
	private String taskId;
	private String dataUser;
	private Long resourceId;
	private String fileType;
	
	private String submitReason;
	private String isView;
	private String definitionId;
	private String businessType;
	
	private String isDes;
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
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	private TApproveOpninion approveOpninion;
	//private List<TFileResourceTable> fileList;
	private List<TSendData> sendDataList;
   	
    public List<TSendData> getSendDataList() {
		return sendDataList;
	}

	public void setSendDataList(List<TSendData> sendDataList) {
		this.sendDataList = sendDataList;
	}

	/*public List<TFileResourceTable> getFileList() {
		return fileList;
	}

	public void setFileList(List<TFileResourceTable> fileList) {
		this.fileList = fileList;
	}*/

	public TApproveOpninion getApproveOpninion() {
		return approveOpninion;
	}

	public void setApproveOpninion(TApproveOpninion approveOpninion) {
		this.approveOpninion = approveOpninion;
	}

	public ITFileResourceTableService getFileResourceTableService() {
		return fileResourceTableService;
	}

	public void setFileResourceTableService(
			ITFileResourceTableService fileResourceTableService) {
		this.fileResourceTableService = fileResourceTableService;
	}

	public List<TFileResourceTable> getFileResourceTables() {
		return fileResourceTables;
	}

	public void setFileResourceTables(List<TFileResourceTable> fileResourceTables) {
		this.fileResourceTables = fileResourceTables;
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

	public String getDataUser() {
		return dataUser;
	}

	public void setDataUser(String dataUser) {
		this.dataUser = dataUser;
	}

	/**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void setSendDataService(ITSendDataService sendDataService) {
        this.sendDataService = sendDataService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITSendDataService getSendDataService() {
        return this.sendDataService;
    }

    public TSendData getSendData() {
		return sendData;
	}

	public void setSendData(TSendData sendData) {
		this.sendData = sendData;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public TaskAssgineeDto getTaskAssgineeDto() {
		return taskAssgineeDto;
	}

	public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
		this.taskAssgineeDto = taskAssgineeDto;
	}

	public String getSubmitReason() {
		return submitReason;
	}

	public void setSubmitReason(String submitReason) {
		this.submitReason = submitReason;
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

	/**
	 * 获取下发表给申请界面
	 * @return
	 * @throws Exception
	 */
	public String getIssuedforjsp() throws Exception{
    	MUOUserSession muo = getCurrentOnlineUser();
    	if(taskAssgineeDto!=null && taskAssgineeDto.getBusinessKey()!=null && 0l!=taskAssgineeDto.getBusinessKey()){
    		sendData = sendDataService.getIssuedById(taskAssgineeDto.getBusinessKey());
    		sendData.setFlowId(taskAssgineeDto.getExecutionId());
    		//fileList = fileResourceTableService.queryFileByIdandType(taskAssgineeDto.getBusinessKey(),"06",fileType);
    	}
    	if(taskAssgineeDto!=null && taskAssgineeDto.getExecutionId()!=null && !"".equals(taskAssgineeDto.getExecutionId())){
    		sendData = sendDataService.getIssuedByFlowId(taskAssgineeDto.getExecutionId());
    	}
    	if(sendData!=null){
    		sendData.setFlowId(taskAssgineeDto.getExecutionId());
    		taskAssgineeDto.setBusinessKey(sendData.getDsId());
    	}
		taskAssgineeDto.setBeginOrg(muo.getOrgid());
    	return "getOldIssued";
    }
	
    /**
     * 保存或更新下发表，并完成任务
     * @return
     * @throws Exception
     */
    public String saveOrUpdateIssued() throws Exception{
    	String info ="success";
    	try{
    		MUOUserSession muo=getCurrentOnlineUser();
    		sendData.setsEmpId(muo.getEmpid());
    		sendData.setOrgid(muo.getOrgid());
	    	Long dsId = null;
	    	sendData.setCreateDate(TimeUtil.today());
	    	sendData.setCreateTime(TimeUtil.now());
	    	sendData.setNodeId(taskId);
	    	if(taskAssgineeDto!=null){
	    		sendData.setNodeName(taskAssgineeDto.getTaskName());
	    		taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
	    		taskAssgineeDto.setExecutionId(sendData.getFlowId());
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
	    	packApproveOpinion();
	    	businessDto.setApproveOpninion(approveOpninion);
	    	if(sendData.getDsId()==null || sendData.getDsId()==0){
	    		dsId = sendDataService.insert(sendData,muo,businessDto,taskAssgineeDto);
	    	}else{
    			sendDataService.update(sendData,muo,businessDto,taskAssgineeDto);
	    	}
	    	//putDtoTojsp(dsId,muo,sdf);
    	}catch (Exception e) {
    		info ="fails";
    		log.error("[新增失败]", e);
			throw e;
    	}
    	Struts2Utils.renderText(info);
    	return null;
    }
    
    /**
     * 获取下发表详情
     * @return
     * @throws Exception
     */
    public String getIssuedforApprove() throws Exception {
		try {
			if(taskAssgineeDto.getNextTaskId()!=null)
		    	taskAssgineeDto.setTaskName(jbpmService.getTaskNameById(taskAssgineeDto.getNextTaskId()));
			if(taskAssgineeDto.getBusinessKey()!=null)
				sendData = sendDataService.getIssuedById(taskAssgineeDto.getBusinessKey());
			else
				sendData = sendDataService.getIssuedByFlowId(taskAssgineeDto.getExecutionId());
			sendData.setFlowId(taskAssgineeDto.getExecutionId());
				
			taskAssgineeDto.setBeginAssingee(sendData.getsEmpId());
			//fileList = fileResourceTableService.queryFileByIdandType(taskAssgineeDto.getBusinessKey(),"06",fileType);
		} catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		taskAssgineeDto.setBeginOrg(sendData.getOrgid());
    	queryDefault();
    	return "info";
    }
    
    /**
     * 审核下发表操作
     * @return
     * @throws Exception
     */
    public String approveIssued() throws Exception {
    	String info ="success";
    	try{
    		//给opinion赋值
	    	packApproveOpinion();
	    	//给taskAssgineeDto赋值
    		packTaskAssgineeDto(approveOpninion.getResourceId());
	    	BusinessDto businessDto = new BusinessDto();
	    	businessDto.setTaskId(taskId);
	    	businessDto.setDataUser(dataUser+","+taskAssingee);
	    	businessDto.setResourceId(resourceId);
	    	if(approveOpninion.getNextOprName().equals("null")){
	    		approveOpninion.setNextOprName("");
	    	}
    		sendDataService.insertApprove(approveOpninion,businessDto,taskAssgineeDto);
	   	
    	}catch (Exception e){
    		info ="fails";
    		log.error("[审核失败]", e);
			throw e;
    	}
    	Struts2Utils.renderText(info);
    	return null;
    }

	
	/**
	 * 根据用户编号获取其可以下载的所有下发表
	 * @return
	 * @throws Exception 
	 */
	public String queryIssuedByEmpId() throws Exception{
		MUOUserSession muo = getCurrentOnlineUser();
		if(sendData == null)
			sendData = new TSendData();
		sendData.setUserId(muo.getEmpid());
		try {
			String positionId  = Obj2StrUtils.join(muo.getPosiCode(), String.class, ",");
			if(positionId.indexOf("'XXKJBR'")>-1)
				sendData.setUserId(null);
			sendDataList = sendDataService.queryIssuedByEmpId(this.getPage(),sendData);
		} catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "queryIssued";
	}
	
	public String downexl() throws Exception{
		MUOUserSession muo = getCurrentOnlineUser();
		if(sendData == null)
			sendData = new TSendData();
		sendData.setUserId(muo.getEmpid());
		try {
			String positionId  = Obj2StrUtils.join(muo.getPosiCode(), String.class, ",");
			if(positionId.indexOf("'XXKJBR'")>-1)
				sendData.setUserId(null);
			sendDataList = sendDataService.queryIssuedByEmpId(null,sendData);
		} catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "downexl";
	}
	/**
	 * 获取下发表信息给用户下载
	 * @return
	 * @throws Exception
	 */
	public String getIssuedByid() throws Exception {
		try {
			Long userId = Long.valueOf(ServletActionContext.getRequest().getParameter("userId"));
			sendData = sendDataService.selectForDown(resourceId,userId);
			//fileList = fileResourceTableService.queryFileByIdandType(resourceId,"06",fileType);
			sendData.setUserId(userId);
			if(!this.getCurrentOnlineUser().getEmpid().equals(userId)){
				sendData.setDesId("0");
			}
		} catch (Exception e) {
			log.error("[获取失败]", e);
			throw e;
		}
		return "download";
	}
	
	/**
	 * 更新使用人员表的销毁信息
	 * @return
	 * @throws Exception
	 */
	public void destroyIssued() throws Exception {
    	String info ="success";
		Long desId = Long.valueOf(ServletActionContext.getRequest().getParameter("dataUser"));
		String desTime = ServletActionContext.getRequest().getParameter("desTime");
		String userId = ServletActionContext.getRequest().getParameter("userId");
		String dsId = ServletActionContext.getRequest().getParameter("dsId");
		try {
			sendDataService.updateUser(desId,desTime,userId,dsId);
			/*this.write("<script>alert('销毁信息登记成功');window.returnValue='1';window.close();;</script>");*/
		} catch (Exception e) {
			log.error("[更新失败]", e);
			info = "fails";
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
		taskAssgineeDto.setBusinessKey(approveOpninion.getResourceId());
		taskAssgineeDto.getTaskName();
		taskAssgineeDto.setBusinessType("06");
		taskAssgineeDto.setTaskAssingee(taskAssingee);
		taskAssgineeDto.setTaskId(taskId);
		taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
		return taskAssgineeDto;
	}
	
	/**
	 * 给opinion赋值
	 */
	private void packApproveOpinion() {
    	MUOUserSession muo=getCurrentOnlineUser();
    	if(approveOpninion==null)
    		approveOpninion = new TApproveOpninion();
    	approveOpninion.setOperator(muo.getEmpid());
    	approveOpninion.setOrgid(String.valueOf(muo.getOrgid()));
    	approveOpninion.setResourceType("06");
    	approveOpninion.setOperaterDate(TimeUtil.today());
    	approveOpninion.setOperaterTime(TimeUtil.now());
    	approveOpninion.setNodeId(taskId);
    	if(taskId!=null && !"".equals(taskId) && !"null".equals(taskId))
    		approveOpninion.setNodeName(jbpmService.getTaskById(taskId).getName());
    	approveOpninion.setNextOprName("");
    	approveOpninion.setNextorgname("");
    	if(approveOpninion.getOpninionContent()==null || "".equals(approveOpninion.getOpninionContent())){
    		approveOpninion.setOpninionContent("已处理");
    	}
    	/**
    	 * 20140903
    	 */
    	approveOpninion.setNextorgname("");
    	if(taskAssgineeDto!=null&&taskAssgineeDto.getEmpNames()!=null&&!"".equals(taskAssgineeDto.getEmpNames())){
    		approveOpninion.setNextOprName(taskAssgineeDto.getEmpNames());
    		List<HashMap<String,Object>> list = tMessagePublishDAO.queryOrgName(taskAssgineeDto.getEmpIds());
    		for(int i=0;i<list.size();i++){
    			approveOpninion.setNextorgname(approveOpninion.getNextorgname()+(String) list.get(i).get("ORGNAME"));
    			if(i!=list.size()-1){
    				approveOpninion.setNextorgname(approveOpninion.getNextorgname()+",");
    			}
    		}
    	}
    	if(taskAssgineeDto!=null){
	    	if("同意".equals(taskAssgineeDto.getTransitionName())){
				approveOpninion.setOperatorType("08");
			}else{
				approveOpninion.setOperatorType("02");
			}
    	}
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
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
	 * @return isDes
	 */
	public String getIsDes() {
		return isDes;
	}

	/**
	 * @param isDes 要设置的 isDes
	 */
	public void setIsDes(String isDes) {
		this.isDes = isDes;
	}
    
    
}