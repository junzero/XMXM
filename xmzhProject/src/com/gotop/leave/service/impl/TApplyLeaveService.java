package com.gotop.leave.service.impl;

import com.gotop.dataIssued.model.TSendData;
import com.gotop.dto.BusinessDto;
import com.gotop.file.model.FileBean;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.leave.dao.ITApplyLeaveDAO;
import com.gotop.leave.model.TApplyLeave;
import com.gotop.leave.model.TApplyLeaveExample;
import com.gotop.leave.service.ITApplyLeaveService;
import com.gotop.messagePublish.model.TMessagePublish;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.util.FileUploadUtil;
import com.gotop.util.file.FileUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessInstance;

public class TApplyLeaveService implements ITApplyLeaveService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TApplyLeaveService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITApplyLeaveDAO tApplyLeaveDAO;
    protected ITApproveOpninionDAO tApproveOpninionDAO;
    
    private ITFileResourceTableService fileResourceTableService;
    private JbpmService jbpmService;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settApplyLeaveDAO(ITApplyLeaveDAO tApplyLeaveDAO) throws Exception {
        this.tApplyLeaveDAO = tApplyLeaveDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITApplyLeaveDAO gettApplyLeaveDAO() throws Exception {
        return this.tApplyLeaveDAO;
    }

    public ITApproveOpninionDAO gettApproveOpninionDAO() {
		return tApproveOpninionDAO;
	}

	public void settApproveOpninionDAO(ITApproveOpninionDAO tApproveOpninionDAO) {
		this.tApproveOpninionDAO = tApproveOpninionDAO;
	}

	public ITFileResourceTableService getFileResourceTableService() {
		return fileResourceTableService;
	}

	public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public void setFileResourceTableService(
			ITFileResourceTableService fileResourceTableService) {
		this.fileResourceTableService = fileResourceTableService;
	}

	@Override
	public List queryAllDataList(Page page) throws Exception {
		
		List list = tApplyLeaveDAO.selectAllLeave(page);
		return list;
	}

	@Override
	public List<TApplyLeave> queryByMap(Page page,HashMap<String,Object> map) throws Exception {
		List list = tApplyLeaveDAO.queryByMap(page,map);
		return list;
	}

	@Override
	public TApplyLeave getLeaveById(Long id) throws Exception {
		TApplyLeave leave = tApplyLeaveDAO.getLeaveById(id);
		return leave;
	}

	@Override
	public Long insert(TApplyLeave tapplyLeave, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception {
		TaskAssgineeDto taskAssgineeDtoTmp = null;
		if(tapplyLeave.getFlowId()==null || "".equals(tapplyLeave.getFlowId())){
			HashMap< String, Object> map = new HashMap<String, Object>();
			map.put("user", String.valueOf(muo.getEmpid()));
			taskAssgineeDtoTmp = jbpmService.startProcessByDefinition(businessDto.getDefinitionId(),map);
			tapplyLeave.setFlowId(taskAssgineeDtoTmp.getExecutionId());
		}
		Long lvId = null;
		lvId = this.tApplyLeaveDAO.insert(tapplyLeave);
		if(businessDto.getUpload()!=null){
    		//saveFile(tapplyLeave, upload, uploadFileName, muo, taskId);
			fileResourceTableService.fileUploads(makeFileBean(tapplyLeave), businessDto.getUpload(), businessDto.getUploadFileName(), muo);
		}
		//需要获得taskId,完成这个节点
		if("1".equals(businessDto.getSubmitReason())){
			taskAssgineeDto.setTaskId(taskAssgineeDtoTmp.getNextTaskId());
			jbpmService.assignTask(taskAssgineeDto);
			jbpmService.completeTask(taskAssgineeDtoTmp.getNextTaskId(), taskAssgineeDto.getTransitionName(), null);
			jbpmService.updateTaskAssigneeState(taskAssgineeDto);
			TaskAssgineeDto taskAssgineeDto2 = this.getTaskAssgineeDto(tapplyLeave, muo, taskAssgineeDtoTmp.getNextTaskId());
			taskAssgineeDto2.setEmpIds(taskAssgineeDto.getEmpIds());
			taskAssgineeDto2.setEmpNames(taskAssgineeDto.getEmpNames());
			taskAssgineeDto2.setTargetName((taskAssgineeDto.getTargetName()));
			jbpmService.saceTaskAssignee(taskAssgineeDto2);
			businessDto.getApproveOpninion().setResourceId(lvId);
	    	tApproveOpninionDAO.insert(businessDto.getApproveOpninion());
		}
		TProcessBusiness processBusiness = new TProcessBusiness();
		processBusiness.setBusinessKey(lvId);
		processBusiness.setBusinessTitle(tapplyLeave.getLvTitle());
		processBusiness.setBusinessType(businessDto.getBusinessType());
		processBusiness.setExecutionId(tapplyLeave.getFlowId());
		jbpmService.saveProcessBusiness(muo,processBusiness);
		return lvId;
	}

	@Override
	public int update(TApplyLeave tapplyLeave, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception {
		this.tApplyLeaveDAO.update(tapplyLeave);
		if(businessDto.getUpload()!=null){
    		//saveFile(tapplyLeave, upload, uploadFileName, muo, taskId);
			fileResourceTableService.fileUploads(makeFileBean(tapplyLeave), businessDto.getUpload(), businessDto.getUploadFileName(), muo);
		}
		//需要获得taskId,完成这个节点
		if("1".equals(businessDto.getSubmitReason())){
			jbpmService.assignTask(taskAssgineeDto);
			jbpmService.completeTask(businessDto.getTaskId(), taskAssgineeDto.getTransitionName(), null);
			jbpmService.updateTaskAssigneeState(taskAssgineeDto);
			TaskAssgineeDto taskAssgineeDto2 = this.getTaskAssgineeDto(tapplyLeave, muo, businessDto.getTaskId());
			taskAssgineeDto2.setEmpIds(taskAssgineeDto.getEmpIds());
			taskAssgineeDto2.setEmpNames(taskAssgineeDto.getEmpNames());
			taskAssgineeDto2.setTargetName((taskAssgineeDto.getTargetName()));
			jbpmService.saceTaskAssignee(taskAssgineeDto2);
			businessDto.getApproveOpninion().setResourceId(tapplyLeave.getLvId());
	    	tApproveOpninionDAO.insert(businessDto.getApproveOpninion());
		}
		if(taskAssgineeDto==null)
			taskAssgineeDto = new TaskAssgineeDto();
		taskAssgineeDto.setExecutionId(tapplyLeave.getFlowId());
		TProcessBusiness processBusiness = jbpmService.findProcessBusiness(taskAssgineeDto);
		processBusiness.setBusinessKey(tapplyLeave.getLvId());
		processBusiness.setBusinessTitle(tapplyLeave.getLvTitle());
		processBusiness.setBusinessType(businessDto.getBusinessType());
		processBusiness.setExecutionId(tapplyLeave.getFlowId());
		jbpmService.updateProcessBusiness(processBusiness);
		return 0;
	}
	
	/**
	 * 生成FileBean文件实体
	 * @param message
	 * @return
	 */
	public FileBean makeFileBean(TApplyLeave tapplyLeave){
		FileBean tfile= new FileBean();
		try{
			tfile.setResourceId(tapplyLeave.getLvId());
			tfile.setResourceType("08");
			tfile.setNodeId(tapplyLeave.getNodeId());
			tfile.setNodeName(tapplyLeave.getNodeName());
		}catch (Exception e) {
			log.error("生成FileBean文件实体失败",e);
		}
		return tfile;
	}
	
	/**
	 * 保存文件
	 * @param leave
	 * @param files
	 * @param filesFileName
	 * @param muo
	 * @param taskId
	 */
	public void saveFile(TApplyLeave leave,File[] files,String[] filesFileName,MUOUserSession muo,String taskId){
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
	       			tfile.setResourceId(leave.getLvId());
	       			tfile.setResourceType("08");
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
	public TaskAssgineeDto getTaskAssgineeDto(TApplyLeave tapplyLeave, MUOUserSession muo, String taskId) {
		String nextTaskId = jbpmService.getNextTaskId(tapplyLeave.getFlowId());
		TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
    	taskAssgineeDto.setExecutionId(tapplyLeave.getFlowId());
    	taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
    	taskAssgineeDto.setPreTaskId(taskId);
    	taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
    	taskAssgineeDto.setPreTaskTime(tapplyLeave.getCreateDate()+tapplyLeave.getCreateTime());
    	taskAssgineeDto.setNextTaskId(nextTaskId);
    	taskAssgineeDto.setBusinessKey(tapplyLeave.getLvId());
    	taskAssgineeDto.setBusinessType("08");
    	return taskAssgineeDto;
	}

	@Override
	public void insertApprove(TApproveOpninion approveOpninion,
			TaskAssgineeDto taskAssgineeDto, String taskId, int flag)
			throws Exception {
		tApproveOpninionDAO.insert(approveOpninion);
		jbpmService.assignTask(taskAssgineeDto);
		jbpmService.completeTask(taskId, taskAssgineeDto.getTransitionName(), null);
		jbpmService.updateTaskAssigneeState(taskAssgineeDto);
		if(flag == 0){
			taskAssgineeDto.setNextTaskId("");
			jbpmService.saceTaskAssignee(taskAssgineeDto);
		}else{
			String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
			taskAssgineeDto.setNextTaskId(nextTaskId);
			jbpmService.saceTaskAssignee(taskAssgineeDto);
		}
		
	}

	@Override
	public TApplyLeave getLeaveByFlowId(String executionId) throws Exception {
		return this.tApplyLeaveDAO.getLeaveByFlowId(executionId);
	}
}