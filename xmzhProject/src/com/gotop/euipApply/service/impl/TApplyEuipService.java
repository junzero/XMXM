package com.gotop.euipApply.service.impl;

import com.fr.report.core.A.f;
import com.gotop.euipApply.dao.ITApplyEuipDAO;
import com.gotop.euipApply.model.TApplyEuip;
import com.gotop.euipApply.service.ITApplyEuipService;
import com.gotop.file.model.FileBean;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.messagePublish.dao.impl.TMessagePublishDAO;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.supervise.model.TSuperviseTable;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class TApplyEuipService implements ITApplyEuipService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TApplyEuipService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITApplyEuipDAO tApplyEuipDAO;
    
    private TMessagePublishDAO tMessagePublishDAO;
    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected JbpmService jbpmService;
    
    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITApproveOpninionDAO tApproveOpninionDAO;
    
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITFileResourceTableService tFileResourceTableService;
    
    


    /**
     * 处理设备申请信息
     */
	@Override
	public void insertEuipInfo(TApplyEuip euip, File[] files,
			String[] filesName, String btnType, MUOUserSession muo,TaskAssgineeDto taskAssgineeDto,String isFirst)
			throws Exception {
		String taskId=taskAssgineeDto.getNextTaskId();
		String submitType = "";
    	//if(taskAssgineeDto.getTransitionName()!=null&&!"".equals(taskAssgineeDto.getTransitionName()))
    		//taskAssgineeDto.setTransitionName(new String(taskAssgineeDto.getTransitionName().getBytes("iso-8859-1"),"UTF-8"));
    	if(euip.getEpId()==null||"".equals(euip.getEpId())){//插入设备申请信息
    		euip.setEmpId(muo.getEmpid());
    		String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
     	    euip.setCreateDate(currDate.substring(0, 8));
     	    euip.setCreateTime(currDate.substring(8));
     	    euip.setOrgid(String.valueOf(muo.getOrgid()));
     	    //启动流程
     	    HashMap<String, Object> map= new HashMap<String, Object>();
			map.put("user",String.valueOf(muo.getEmpid()));
			TaskAssgineeDto dto1=jbpmService.startProcessByDefinition(taskAssgineeDto.getDefinitionId(),map);
			taskId=dto1.getNextTaskId();
			euip.setNodeId(taskId);
			euip.setNodeName1(jbpmService.getTaskById(taskId).getName());
			euip.setFlowId(dto1.getExecutionId());
			taskAssgineeDto.setExecutionId(dto1.getExecutionId());
		    //保存信息
    		tApplyEuipDAO.insertEuipInfo(euip);
    		//提交
    		submitType="05";
    		//保存流程的信息
			jbpmService.saveProcessBusiness(muo,insertProcessBusiness(euip,taskAssgineeDto));
    	}else{
    		//修改
    		tApplyEuipDAO.updateApplyEuip(euip);
    		 submitType = "05";
    		   if(euip.getOpninion()==null || "null".equals(euip.getOpninion())){
    			   euip.setOpninion("");
			   }
    		//修改流程信息
    		TProcessBusiness business = jbpmService.findProcessBusiness(taskAssgineeDto);
            business.setBusinessTitle(euip.getEpTitle());
		jbpmService.updateProcessBusiness(business);
    	}
    	tFileResourceTableService.fileUploads(makeFileBean(euip,taskAssgineeDto), files, filesName, muo);//保存文件
    	if(!"1".equals(btnType)){//不为保存状态
    		euip.setNodeName1(jbpmService.getTaskById(taskId).getName());
    		//审核人信息记录
			taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
			taskAssgineeDto.setTaskId(taskId);
			jbpmService.assignTask(taskAssgineeDto);
			jbpmService.completeTask(taskId, taskAssgineeDto.getTransitionName(), null);
    		if(!"退回".equals(taskAssgineeDto.getTransitionName())){//进入下一步
    			/*insertApproveOpninion(euip, muo, taskId,"01",taskAssgineeDto);*/
    			if(isFirst==null && taskAssgineeDto.getEmpIds()!=null && !"".equals(taskAssgineeDto.getEmpIds()))
					submitType = "01";
    			if("采购".equals(taskAssgineeDto.getTransitionName())){//正常是转采购流程
    				//String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId()); 
					//jbpmService.saceTaskAssignee(makeTaskAssgineeDto(euip, nextTaskId, muo,taskAssgineeDto));
    				euip.setNodeId(taskId);
					TaskAssgineeDto taskAssginee = makeTaskAssgineeDto(euip, muo,taskAssgineeDto);
					taskAssginee.setNextTaskId("");
					jbpmService.saceTaskAssignee(taskAssginee);
    				submitType = "06";
    			}else{//正常下一步
    				String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId()); 
    				taskAssgineeDto.setNextTaskId(nextTaskId);
    				euip.setNodeId(taskId);
					jbpmService.saceTaskAssignee(makeTaskAssgineeDto(euip, muo,taskAssgineeDto));
    			}
    			insertApproveOpninion(euip, muo, taskId,submitType,taskAssgineeDto);
    		}else{
    			insertApproveOpninion(euip, muo, taskId,"02",taskAssgineeDto);
    			jbpmService.turnBackTaskAssignee(makeTaskAssgineeDtoBack(taskAssgineeDto, euip, muo));
    		}
    		
    	}
	}
	
	 /**
	 * 生成与流程相对应的业务信息
	 * @param message
	 * @return
	 */
	public  TProcessBusiness insertProcessBusiness(TApplyEuip euip,TaskAssgineeDto dto){
		 TProcessBusiness processBusiness= new TProcessBusiness();
		try {
			processBusiness.setBusinessKey(euip.getEpId());
			processBusiness.setBusinessType(dto.getBusinessType());
			processBusiness.setBusinessTitle(euip.getEpTitle());
			processBusiness.setExecutionId(euip.getFlowId());
		} catch (Exception e) {
			log.error("生成实例标题信息", e);
		}
		return processBusiness;
	}
	
	/**
	 * 生成FileBean文件实体
	 * @param message
	 * @return
	 */
	public FileBean makeFileBean(TApplyEuip t,TaskAssgineeDto dto){
		FileBean tfile= new FileBean();
		try{
			tfile.setResourceId(t.getEpId());
			tfile.setResourceType(dto.getBusinessType());
			tfile.setNodeId(t.getNodeId());
			tfile.setNodeName(t.getNodeName1());
		}catch (Exception e) {
			log.error("生成FileBean文件实体失败",e);
		}
		return tfile;
	}
	
	/**
	 * 生成任务实体。[jbpm]
	 * @return
	 */
	public TaskAssgineeDto makeTaskAssgineeDto(TApplyEuip euip,MUOUserSession muo,TaskAssgineeDto dto){
		TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
		try {	
	    	taskAssgineeDto.setExecutionId(euip.getFlowId());
	    	taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
	    	taskAssgineeDto.setPreTaskId(euip.getNodeId());
	    	taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
	    	//if(euip.getCreateDate()!=null&&!"".equals(euip.getCreateDate())){
	    		//taskAssgineeDto.setPreTaskTime(euip.getCreateDate());
	    	//}else{
	    	String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
	    		taskAssgineeDto.setPreTaskTime(currDate);
	    	///}
	    	taskAssgineeDto.setEmpIds(dto.getEmpIds());
	    	taskAssgineeDto.setEmpNames(dto.getEmpNames());
	    	taskAssgineeDto.setNextTaskId(dto.getNextTaskId());
	    	taskAssgineeDto.setBusinessKey(euip.getEpId());
	    	taskAssgineeDto.setBusinessType(dto.getBusinessType());
	    	taskAssgineeDto.setTargetName(dto.getTargetName());
	    	   //存储节点配置对象主键
	    	taskAssgineeDto.setTaskExeConfigId(dto.getTaskExeConfigId());
		} catch (Exception e) {
			log.error("获取任务实体", e);
		}
		return taskAssgineeDto;
	}
	
	/**
	 * 生成意见
	 * @param muo
	 * @param taskId
	 */
	public void insertApproveOpninion(TApplyEuip euip,MUOUserSession muo,String taskId,String type,TaskAssgineeDto dto){
		try {
			if(euip!=null&&euip.getEpId()!=null&&!"".equals(euip.getEpId())){
				/*if(euip.getOpninion()!=null&&!"".equals(euip.getOpninion())){*/
				if(euip.getOpninion()!=null){
					String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
			    	TApproveOpninion opninion=new TApproveOpninion();
			    	opninion.setResourceId(euip.getEpId());
			    	opninion.setOperator(muo.getEmpid());
			    	opninion.setOrgid(String.valueOf(muo.getOrgid()));
			    	opninion.setResourceType(dto.getBusinessType());
			    	opninion.setOperatorType(type);
			    	opninion.setOperaterDate(currDate.substring(0, 8));
			    	opninion.setOperaterTime(currDate.substring( 8));
			    	opninion.setOpninionContent(euip.getOpninion());
			    	/*if(dto.getEmpNames()!=null&&!"".equals(dto.getEmpNames()))
				    	opninion.setNextOprName(dto.getEmpNames());
				    	else
				    		opninion.setNextOprName("");*/
			    	
			    	/**
			    	 * 20140903
			    	 */
			    	opninion.setNextorgname("");
			    	if(dto.getEmpNames()!=null&&!"null".equals(dto.getEmpNames())){
			    		opninion.setNextOprName(dto.getEmpNames());
			    		List<HashMap<String,Object>> list = tMessagePublishDAO.queryOrgName(dto.getEmpIds());
			    		for(int i=0;i<list.size();i++){
			    			opninion.setNextorgname(opninion.getNextorgname()+(String) list.get(i).get("ORGNAME"));
			    			if(i!=list.size()-1){
			    				opninion.setNextorgname(opninion.getNextorgname()+",");
			    			}
			    		}
			    		
			    	}
			    	else
			    		opninion.setNextOprName("");
			    	//需要taskId
			    	opninion.setNodeId(taskId);
			    	opninion.setNodeName(euip.getNodeName1());
			    	tApproveOpninionDAO.insert(opninion);
				}
			}
			
		} catch (Exception e) {
		//保存审核意见失败。
			log.error("保存审核意见失败。", e);
		}
	}
	
	
	/**
	 * 回退时的dto生成
	 * @param dto
	 * @param message
	 * @param muo
	 * @return
	 */
	public TaskAssgineeDto makeTaskAssgineeDtoBack(TaskAssgineeDto dto,TApplyEuip euip,MUOUserSession muo){
		TaskAssgineeDto dto2=new TaskAssgineeDto();
		dto2.setExecutionId(dto.getExecutionId());
		dto2.setPreTaskAssingee(muo.getEmpid());
		//回退前 当前办理人机构id
		dto2.setPreTaskOrg(muo.getOrgid());
		//回退前 当前节点id
		dto2.setPreTaskId(dto.getNextTaskId());
		//回退前 当前办理时间
		String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
		dto2.setPreTaskTime(currDate);
		//回退后 办理节点id
		dto2.setNextTaskId(jbpmService.getNextTaskId(dto.getExecutionId()));
		dto2.setBusinessKey(euip.getEpId());
//		dto2.setTaskAssingee(dto.getTaskAssingee());
	     dto2.setTaskAssingee(Long.valueOf(dto.getEmpIds()));
		dto2.setBusinessType(dto.getBusinessType());
		dto2.setTargetName(dto.getTargetName());
		   //存储节点配置对象主键
        dto2.setTaskExeConfigId(dto.getTaskExeConfigId());
		return dto2;
	}

	/**
	 * 查询设备申请信息
	 */
	@Override
	public TApplyEuip queryEuipInfo(String epId,String flowId) throws Exception {
		TApplyEuip euip= new TApplyEuip();
		if(epId!=null&&!"".equals(epId))
		euip.setEpId(Long.valueOf(epId));
		if(flowId!=null&&!"".equals(flowId))
		euip.setFlowId(flowId);
		return tApplyEuipDAO.queryApplyEuip(euip);
	}


	
	
    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITApplyEuipDAO gettApplyEuipDAO() throws Exception {
        return this.tApplyEuipDAO;
    }

	
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settApplyEuipDAO(ITApplyEuipDAO tApplyEuipDAO) throws Exception {
        this.tApplyEuipDAO = tApplyEuipDAO;
    }

	public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public ITApproveOpninionDAO gettApproveOpninionDAO() {
		return tApproveOpninionDAO;
	}

	public void settApproveOpninionDAO(ITApproveOpninionDAO tApproveOpninionDAO) {
		this.tApproveOpninionDAO = tApproveOpninionDAO;
	}

	public ITFileResourceTableService gettFileResourceTableService() {
		return tFileResourceTableService;
	}

	public void settFileResourceTableService(
			ITFileResourceTableService tFileResourceTableService) {
		this.tFileResourceTableService = tFileResourceTableService;
	}

	public TMessagePublishDAO gettMessagePublishDAO() {
		return tMessagePublishDAO;
	}

	public void settMessagePublishDAO(TMessagePublishDAO tMessagePublishDAO) {
		this.tMessagePublishDAO = tMessagePublishDAO;
	}
    
}