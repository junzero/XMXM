package com.gotop.dataApply.service.impl;

import com.gotop.dataApply.dao.ITApplyDataDAO;
import com.gotop.dataApply.model.TApplyData;
import com.gotop.dataApply.model.TApplyDataExample;
import com.gotop.dataApply.service.ITApplyDataService;
import com.gotop.dataIssued.model.TSendData;
import com.gotop.dataUser.dao.ITRangeUserDAO;
import com.gotop.dataUser.model.TRangeUser;
import com.gotop.dto.BusinessDto;
import com.gotop.file.model.FileBean;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.util.FileUploadUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class TApplyDataService implements ITApplyDataService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TApplyDataService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITApplyDataDAO tApplyDataDAO;
    protected ITApproveOpninionDAO tApproveOpninionDAO;
    protected ITRangeUserDAO rangeUserDAO;
    
    private ITFileResourceTableService fileResourceTableService;
    private JbpmService jbpmService;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settApplyDataDAO(ITApplyDataDAO tApplyDataDAO) throws Exception {
        this.tApplyDataDAO = tApplyDataDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITApplyDataDAO gettApplyDataDAO() throws Exception {
        return this.tApplyDataDAO;
    }

	@Override
	public TApplyData getApplyDataById(Long businessKey) throws Exception {
		TApplyData applyData = this.tApplyDataDAO.selectByPrimaryKey(businessKey);
		return applyData;
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

	//更新申请表
	@Override
	public void update(TApplyData applyData, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto, TApproveOpninion approveOpninion) throws Exception {
		this.tApplyDataDAO.updateByPrimaryKey(applyData);
		if(businessDto.getUpload()!=null){
			fileResourceTableService.fileUploads(makeFileBean(applyData), businessDto.getUpload(), businessDto.getUploadFileName(), muo);
		}
		if("1".equals(businessDto.getSubmitReason())){
			approveOpninion.setOpninionContent("");
			approveOpninion.setResourceId(applyData.getDaId());
			tApproveOpninionDAO.insert(approveOpninion);
			jbpmService.assignTask(taskAssgineeDto);
			jbpmService.completeTask(businessDto.getTaskId(), taskAssgineeDto.getTransitionName(), null);
			jbpmService.updateTaskAssigneeState(taskAssgineeDto);
			TaskAssgineeDto taskAssgineeDto2 = this.getTaskAssgineeDto(applyData, muo, businessDto.getTaskId());
			taskAssgineeDto2.setEmpIds(taskAssgineeDto.getEmpIds());
			taskAssgineeDto2.setEmpNames(taskAssgineeDto.getEmpNames());
			taskAssgineeDto2.setTargetName((taskAssgineeDto.getTargetName()));
			jbpmService.saceTaskAssignee(taskAssgineeDto2);
		}
		if(taskAssgineeDto==null)
			taskAssgineeDto = new TaskAssgineeDto();
		taskAssgineeDto.setExecutionId(applyData.getFlowId());
		TProcessBusiness processBusiness = jbpmService.findProcessBusiness(taskAssgineeDto);
		processBusiness.setBusinessKey(applyData.getDaId());
		processBusiness.setBusinessTitle(applyData.getDaTitle());
		processBusiness.setBusinessType(businessDto.getBusinessType());
		processBusiness.setExecutionId(applyData.getFlowId());
		jbpmService.updateProcessBusiness(processBusiness);
	}

	//插入申请表
	@Override
	public Long insert(TApplyData applyData, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto, TApproveOpninion approveOpninion) throws Exception {
		TaskAssgineeDto taskAssgineeDtoTmp = null;
		if(applyData.getFlowId()==null || "".equals(applyData.getFlowId())){
			HashMap< String, Object> map = new HashMap<String, Object>();
			map.put("user", String.valueOf(muo.getEmpid()));
			taskAssgineeDtoTmp = jbpmService.startProcessByDefinition(businessDto.getDefinitionId(),map);
			applyData.setFlowId(taskAssgineeDtoTmp.getExecutionId());
		}
		Long daId = null;
		daId = this.tApplyDataDAO.insert(applyData);
		if(businessDto.getUpload()!=null){
			fileResourceTableService.fileUploads(makeFileBean(applyData), businessDto.getUpload(), businessDto.getUploadFileName(), muo);
		}
			//需要获得taskId,完成这个节点
		if("1".equals(businessDto.getSubmitReason())){
			approveOpninion.setOpninionContent("");
			approveOpninion.setResourceId(applyData.getDaId());
			tApproveOpninionDAO.insert(approveOpninion);
			taskAssgineeDto.setTaskId(taskAssgineeDtoTmp.getNextTaskId());
			jbpmService.assignTask(taskAssgineeDto);
			jbpmService.completeTask(taskAssgineeDtoTmp.getNextTaskId(), taskAssgineeDto.getTransitionName(), null);
			jbpmService.updateTaskAssigneeState(taskAssgineeDto);
			TaskAssgineeDto taskAssgineeDto2 = this.getTaskAssgineeDto(applyData, muo, taskAssgineeDtoTmp.getNextTaskId());
			taskAssgineeDto2.setEmpIds(taskAssgineeDto.getEmpIds());
			taskAssgineeDto2.setEmpNames(taskAssgineeDto.getEmpNames());
			taskAssgineeDto2.setTargetName((taskAssgineeDto.getTargetName()));
			jbpmService.saceTaskAssignee(taskAssgineeDto2);
		}
		TProcessBusiness processBusiness = new TProcessBusiness();
		processBusiness.setBusinessKey(applyData.getDaId());
		processBusiness.setBusinessTitle(applyData.getDaTitle());
		processBusiness.setBusinessType(businessDto.getBusinessType());
		processBusiness.setExecutionId(applyData.getFlowId());
		jbpmService.saveProcessBusiness(muo,processBusiness);
		return daId;
	}

	/**
	 * 生成FileBean文件实体
	 * @param message
	 * @return
	 */
	public FileBean makeFileBean(TApplyData applyData){
		FileBean tfile= new FileBean();
		try{
			tfile.setResourceId(applyData.getDaId());
			tfile.setResourceType("05");
			tfile.setNodeId(applyData.getNodeId());
			tfile.setNodeName(applyData.getNodeName());
		}catch (Exception e) {
			log.error("生成FileBean文件实体失败",e);
		}
		return tfile;
	}
	
	/**
	 * 获取完成节点所需的信息
	 * @param applyData
	 * @param muo
	 * @param taskId
	 * @return
	 */
	public TaskAssgineeDto getTaskAssgineeDto(TApplyData applyData, MUOUserSession muo, String taskId) {
		String nextTaskId = jbpmService.getNextTaskId(applyData.getFlowId());
		TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
    	taskAssgineeDto.setExecutionId(applyData.getFlowId());
    	taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
    	taskAssgineeDto.setPreTaskId(taskId);
    	taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
    	taskAssgineeDto.setPreTaskTime(applyData.getCreateDate()+applyData.getCreateTime());
    	taskAssgineeDto.setNextTaskId(nextTaskId);
    	taskAssgineeDto.setBusinessKey(applyData.getDaId());
    	taskAssgineeDto.setBusinessType("05");
    	return taskAssgineeDto;
	}

	@Override
	/**
	 * 数据申请审核
	 */
	public int insertApprove(TApplyData applyData,TApproveOpninion approveOpninion,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto)
			throws Exception {
		if(applyData!=null){
	    	applyData.setDaId(businessDto.getResourceId());
			tApplyDataDAO.updateByPrimaryKey(applyData);
		}
		jbpmService.assignTask(taskAssgineeDto);
		jbpmService.completeTask(businessDto.getTaskId(), taskAssgineeDto.getTransitionName(), null);
		jbpmService.updateTaskAssigneeState(taskAssgineeDto);
		String transition = taskAssgineeDto.getTransitionName();
			if("结束".equals(transition)){
				approveOpninion.setOperatorType("08");//意见类型
				approveOpninion.setNextOprName("");
				if(businessDto.getDataUser()!=null && !"".equals(businessDto.getDataUser())){
					String []userId = businessDto.getDataUser().split(",");
					for(int i=0;i<userId.length;i++){
						TRangeUser user  = new TRangeUser();
						user.setEmpId(Long.valueOf(userId[i]));
						user.setResourceId(businessDto.getResourceId());
						user.setResourceType("01");
						user.setOrgName(rangeUserDAO.queryOrgName(Long.valueOf(userId[i])).get("ORGNAME"));
						this.rangeUserDAO.insert(user);
					}
				}
			}else{
				String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
				taskAssgineeDto.setNextTaskId(nextTaskId);
			}
			tApproveOpninionDAO.insert(approveOpninion);
			jbpmService.saceTaskAssignee(taskAssgineeDto);
			return 0;
		/*tApproveOpninionDAO.insert(approveOpninion);
		jbpmService.saceTaskAssignee(taskAssgineeDto);
		jbpmService.turnBackTaskAssignee(taskAssgineeDto);
		return 1;*/
	}

	/**
	 * 通过流程id获得数据申请表
	 */
	@Override
	public TApplyData getLeaveByFlowId(String executionId) throws Exception {
		return this.tApplyDataDAO.getLeaveByFlowId(executionId);
	}

	/**
	 * 通过主键获得含提取可行性分析的数据申请表
	 */
	@Override
	public TApplyData getApplyDataAnalyst(Long businessKey) throws Exception {
		return tApplyDataDAO.getApplyDataAnalyst(businessKey);
	}
	
	/**
	 * 通过主键获取含风险可行性分析的数据申请表
	 */
	@Override
	public TApplyData getApplyRiskAnalyst(Long businessKey) throws Exception {
		// TODO 自动生成的方法存根
		return tApplyDataDAO.getApplyRiskAnalyst(businessKey);
	}

	/**
	 * 通过主键获得含分析报告的数据申请表
	 */
	@Override
	public TApplyData getApplyAnalyst(Long businessKey) throws Exception {
		// TODO 自动生成的方法存根
		return tApplyDataDAO.getApplyAnalyst(businessKey);
	}

	@Override
	public void completeTask(String taskId, TaskAssgineeDto taskAssgineeDto)
			throws Exception {
		jbpmService.assignTask(taskAssgineeDto);
		jbpmService.completeTask(taskId, taskAssgineeDto.getTransitionName(), null);
		jbpmService.updateTaskAssigneeState(taskAssgineeDto);
		String transition = taskAssgineeDto.getTransitionName();
		if("结束".equals(transition)){
			return;
		}
		String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
		taskAssgineeDto.setNextTaskId(nextTaskId);
		jbpmService.saceTaskAssignee(taskAssgineeDto);
	}

	//分析报告
	@Override
	public void submitAnalysis(TaskAssgineeDto taskAssgineeDto, BusinessDto businessDto,
									TApproveOpninion approveOpninion) throws Exception {
		// TODO 自动生成的方法存根
		TApplyData applyData = new TApplyData();
		applyData.setDaId(businessDto.getResourceId());
		String taskName = jbpmService.getTaskNameById(businessDto.getTaskId());
		if("风险可行性分析".equals(taskName)){
			applyData.setRisk(businessDto.getAnalysisReport());
			applyData.setrEmpId(taskAssgineeDto.getPreTaskAssingee());
		}else{
			applyData.setDaTlimit(businessDto.getTime());
			applyData.setDaFetch(businessDto.getAnalysisReport());
			applyData.setdEmpId(taskAssgineeDto.getPreTaskAssingee());
		}
		if(applyData.getRisk()!=null)
			approveOpninion.setOpninionContent(applyData.getRisk());
		else
			approveOpninion.setOpninionContent(applyData.getDaFetch());
		tApproveOpninionDAO.insert(approveOpninion);
		tApplyDataDAO.updateByPrimaryKey(applyData);
		jbpmService.assignTask(taskAssgineeDto);
		jbpmService.completeTask(businessDto.getTaskId(), taskAssgineeDto.getTransitionName(), null);
		jbpmService.updateTaskAssigneeState(taskAssgineeDto);
		if(!"结束".equals(taskAssgineeDto.getTransitionName())){
			String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
			taskAssgineeDto.setNextTaskId(nextTaskId);
		}
		jbpmService.saceTaskAssignee(taskAssgineeDto);
	}

	@Override
	public List<TApplyData> queryAllDataApply(TApplyData applyData, Page page) {
		return this.tApplyDataDAO.queryAllDataApply(applyData,page);
	}
}