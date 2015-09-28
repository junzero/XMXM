package com.gotop.dataIssued.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gotop.dataIssued.dao.ITSendDataDAO;
import com.gotop.dataIssued.model.TSendData;
import com.gotop.dataIssued.service.ITSendDataService;
import com.gotop.dataUser.dao.ITRangeUserDAO;
import com.gotop.dataUser.model.TRangeUser;
import com.gotop.dto.BusinessDto;
import com.gotop.file.model.FileBean;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.leave.model.TApplyLeave;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.util.FileUploadUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

public class TSendDataService implements ITSendDataService {
	
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TSendDataService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITSendDataDAO tSendDataDAO;
    protected ITApproveOpninionDAO tApproveOpninionDAO;
    protected ITRangeUserDAO rangeUserDAO;
    
    private ITFileResourceTableService fileResourceTableService;
    private JbpmService jbpmService;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settSendDataDAO(ITSendDataDAO tSendDataDAO) throws Exception {
        this.tSendDataDAO = tSendDataDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITSendDataDAO gettSendDataDAO() throws Exception {
        return this.tSendDataDAO;
    }

    public ITApproveOpninionDAO gettApproveOpninionDAO() {
		return tApproveOpninionDAO;
	}

	public void settApproveOpninionDAO(ITApproveOpninionDAO tApproveOpninionDAO) {
		this.tApproveOpninionDAO = tApproveOpninionDAO;
	}

	public ITRangeUserDAO getRangeUserDAO() {
		return rangeUserDAO;
	}

	public void setRangeUserDAO(ITRangeUserDAO rangeUserDAO) {
		this.rangeUserDAO = rangeUserDAO;
	}

	public ITFileResourceTableService getFileResourceTableService() {
		return fileResourceTableService;
	}

	public void setFileResourceTableService(
			ITFileResourceTableService fileResourceTableService) {
		this.fileResourceTableService = fileResourceTableService;
	}

	public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TSendData obj) throws Exception {
        this.tSendDataDAO.insert(obj);
    }

	@Override
	public void update(TSendData obj) throws Exception {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Long insert(TSendData sendData, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception {
		TaskAssgineeDto taskAssgineeDtoTmp = null;
		if(sendData.getFlowId()==null || "".equals(sendData.getFlowId())){
			HashMap< String, Object> map = new HashMap<String, Object>();
			map.put("user", String.valueOf(muo.getEmpid()));
			taskAssgineeDtoTmp = jbpmService.startProcessByDefinition(businessDto.getDefinitionId(),map);
			sendData.setFlowId(taskAssgineeDtoTmp.getExecutionId());
		}
		Long dsId = null;
		dsId = this.tSendDataDAO.insert(sendData);
		if(businessDto.getUpload()!=null){
			//saveFile(sendData, upload, uploadFileName, muo, taskId);
			fileResourceTableService.fileUploads(makeFileBean(sendData), businessDto.getUpload(), businessDto.getUploadFileName(), muo);
		}
			//需要获得taskId,完成这个节点
		if("1".equals(businessDto.getSubmitReason())){
			businessDto.getApproveOpninion().setOpninionContent("");
			businessDto.getApproveOpninion().setResourceId(sendData.getDsId());
			businessDto.getApproveOpninion().setOperatorType("05");
			tApproveOpninionDAO.insert(businessDto.getApproveOpninion());
			taskAssgineeDto.setTaskId(taskAssgineeDtoTmp.getNextTaskId());
			jbpmService.assignTask(taskAssgineeDto);
			jbpmService.completeTask(taskAssgineeDtoTmp.getNextTaskId(), taskAssgineeDto.getTransitionName(), null);
			jbpmService.updateTaskAssigneeState(taskAssgineeDto);
			TaskAssgineeDto taskAssgineeDto2 = this.getTaskAssgineeDto(sendData, muo, taskAssgineeDtoTmp.getNextTaskId());
			taskAssgineeDto2.setEmpIds(taskAssgineeDto.getEmpIds());
			taskAssgineeDto2.setEmpNames(taskAssgineeDto.getEmpNames());
			taskAssgineeDto2.setTargetName(taskAssgineeDto.getTargetName());
			jbpmService.saceTaskAssignee(taskAssgineeDto2);
		}
		TProcessBusiness processBusiness = new TProcessBusiness();
		processBusiness.setBusinessKey(sendData.getDsId());
		processBusiness.setBusinessTitle(sendData.getDsTitle());
		processBusiness.setBusinessType(businessDto.getBusinessType());
		processBusiness.setExecutionId(sendData.getFlowId());
		jbpmService.saveProcessBusiness(muo,processBusiness);
		return dsId;
	}
	
	@Override
	public TSendData getIssuedById(Long businessKey) throws Exception {
		TSendData sendData = tSendDataDAO.getIssuedById(businessKey);
		return sendData;
	}

	@Override
	public int insertApprove(TApproveOpninion approveOpninion,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception {
		tApproveOpninionDAO.insert(approveOpninion);
		jbpmService.assignTask(taskAssgineeDto);
		jbpmService.completeTask(businessDto.getTaskId(), taskAssgineeDto.getTransitionName(), null);
		jbpmService.updateTaskAssigneeState(taskAssgineeDto);
		if("同意".equals(taskAssgineeDto.getTransitionName())){
			if(businessDto.getDataUser()!=null && !"".equals(businessDto.getDataUser())){
				String []userId = businessDto.getDataUser().split(",");
				for(int i=0;i<userId.length;i++){
					TRangeUser user  = new TRangeUser();
					user.setEmpId(Long.valueOf(userId[i]));
					user.setResourceId(businessDto.getResourceId());
					user.setResourceType("02");
					user.setOrgName(rangeUserDAO.queryOrgName(Long.valueOf(userId[i])).get("ORGNAME"));
					this.rangeUserDAO.insert(user);
				}
				jbpmService.saceTaskAssignee(taskAssgineeDto);
			}
			return 0;
		}
		String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
		taskAssgineeDto.setNextTaskId(nextTaskId);
		/*jbpmService.saceTaskAssignee(taskAssgineeDto);*/
		jbpmService.turnBackTaskAssignee(taskAssgineeDto);
		return 1;
	}

	@Override
	public int update(TSendData sendData, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception {
		int count = 0;
		count = this.tSendDataDAO.update(sendData);
		if(businessDto.getUpload()!=null){
			//saveFile(sendData, upload, uploadFileName, muo, taskId);
			fileResourceTableService.fileUploads(makeFileBean(sendData), businessDto.getUpload(), businessDto.getUploadFileName(), muo);
		}
		if("1".equals(businessDto.getSubmitReason())){
			businessDto.getApproveOpninion().setOpninionContent("");
			businessDto.getApproveOpninion().setResourceId(sendData.getDsId());
			businessDto.getApproveOpninion().setOperatorType("05");
			tApproveOpninionDAO.insert(businessDto.getApproveOpninion());
			jbpmService.assignTask(taskAssgineeDto);
			jbpmService.completeTask(businessDto.getTaskId(), taskAssgineeDto.getTransitionName(), null);
			jbpmService.updateTaskAssigneeState(taskAssgineeDto);
			TaskAssgineeDto taskAssgineeDto2 = this.getTaskAssgineeDto(sendData, muo, businessDto.getTaskId());
			taskAssgineeDto2.setEmpIds(taskAssgineeDto.getEmpIds());
			taskAssgineeDto2.setEmpNames(taskAssgineeDto.getEmpNames());
			taskAssgineeDto2.setTargetName(taskAssgineeDto.getTargetName());
			jbpmService.saceTaskAssignee(taskAssgineeDto2);
		}
		if(taskAssgineeDto==null)
			taskAssgineeDto = new TaskAssgineeDto();
		taskAssgineeDto.setExecutionId(sendData.getFlowId());
		TProcessBusiness processBusiness = jbpmService.findProcessBusiness(taskAssgineeDto);
		processBusiness.setBusinessKey(sendData.getDsId());
		processBusiness.setBusinessTitle(sendData.getDsTitle());
		processBusiness.setBusinessType(businessDto.getBusinessType());
		processBusiness.setExecutionId(sendData.getFlowId());
		jbpmService.updateProcessBusiness(processBusiness);
		return count;
	}

	@Override
	public List<TSendData> queryIssuedByEmpId(Page page,TSendData sendData) throws Exception {
		// TODO 自动生成的方法存根
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("empid", sendData.getUserId());
		map.put("userOrg", sendData.getUserOrg());
		map.put("startTime", sendData.getStartTime());
		map.put("endTime", sendData.getEndTime());
		
		map.put("beginTime", sendData.getBeginTime());
		map.put("closeTime", sendData.getCloseTime());
		if(page!=null){
			map.put("oracleStart", page.getBegin());
			map.put("oracleEnd", page.getBegin()+page.getLength());
		}
		map.put("isDes", sendData.getIsDes());
		map.put("dataType", sendData.getDataType());
		return tSendDataDAO.queryIssuedByEmpId(page, map);
	}

	@Override
	public void updateUser(Long desId, String desTime, String userId, String dsId)
			throws Exception {
		TRangeUser record = new TRangeUser();
		TRangeUser example = new TRangeUser();
		record.setDesDate(desTime.substring(0, 8));
		record.setDesTime(desTime.substring(8));
		record.setDesId(desId);
		record.setIsDes("01");
		example.setResourceType("02");
		example.setResourceId(Long.valueOf(dsId));
		example.setEmpId(Long.valueOf(userId));
		rangeUserDAO.updateEntityByTemplate(record, example);
	}

	/**
	 * 生成FileBean文件实体
	 * @param message
	 * @return
	 */
	public FileBean makeFileBean(TSendData sendData){
		FileBean tfile= new FileBean();
		try{
			tfile.setResourceId(sendData.getDsId());
			tfile.setResourceType("06");
			tfile.setNodeId(sendData.getNodeId());
			tfile.setNodeName(sendData.getNodeName());
		}catch (Exception e) {
			log.error("生成FileBean文件实体失败",e);
		}
		return tfile;
	}
	
	/**
	 * 保存文件
	 * @param sendData
	 * @param files
	 * @param filesFileName
	 * @param muo
	 * @param taskId
	 */
	public void saveFile(TSendData sendData,File[] files,String[] filesFileName,MUOUserSession muo,String taskId){
		try {
			if (filesFileName!=null && !"".equals(filesFileName)) { // 判断是否有附件
	   			String suffixStr = null;
	   			String address=ServletActionContext.getServletContext().getRealPath("/");
	       		String serverName = ServletActionContext.getRequest().getScheme()+"://"+ServletActionContext.getRequest().getServerName()+":"+ServletActionContext.getRequest().getServerPort()+ServletActionContext.getRequest().getContextPath()+"/";
	       		String uuid = UUID.randomUUID().toString();
	       		for(int i=0;i<filesFileName.length;i++){
	       			TFileResourceTable tfile=new TFileResourceTable();
	       			tfile.setFileName(filesFileName[i]);
	       			suffixStr = filesFileName[i].substring(filesFileName[i].indexOf("."), filesFileName[i].length());
	       			tfile.setFileReName(uuid+suffixStr);
	       			tfile.setFilePath("upload/"+uuid+suffixStr);
	       			tfile.setResourceId(sendData.getDsId());
	       			tfile.setResourceType("06");
	       			tfile.setCreator(muo.getEmpid());
	       			tfile.setNodeId(taskId);
	       			tfile.setNodeName(jbpmService.getTaskById(taskId).getName());
	       			FileUploadUtil.uploadFile(uuid, serverName, address, filesFileName[i],files[i],suffixStr);
	       			fileResourceTableService.insert(tfile);
	       		}
			}
		} catch (Exception e) {
			log.error("文件保存错误", e);
		}
	}
	
	public TaskAssgineeDto getTaskAssgineeDto(TSendData sendData, MUOUserSession muo, String taskId) {
		String nextTaskId = jbpmService.getNextTaskId(sendData.getFlowId());
		TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
    	taskAssgineeDto.setExecutionId(sendData.getFlowId());
    	taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
    	taskAssgineeDto.setPreTaskId(taskId);
    	taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
    	taskAssgineeDto.setPreTaskTime(sendData.getCreateDate()+sendData.getCreateTime());
    	taskAssgineeDto.setNextTaskId(nextTaskId);
    	taskAssgineeDto.setBusinessKey(sendData.getDsId());
    	taskAssgineeDto.setBusinessType("06");
    	return taskAssgineeDto;
	}

	@Override
	public TSendData getIssuedByFlowId(String executionId) throws Exception {
		return this.tSendDataDAO.getIssuedByFlowId(executionId);
	}

	@Override
	public TSendData selectForDown(Long resourceId, Long userId) {
		return this.tSendDataDAO.selectForDown(resourceId,userId);
	}
}