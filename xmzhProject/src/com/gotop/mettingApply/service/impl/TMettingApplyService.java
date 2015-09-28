package com.gotop.mettingApply.service.impl;

import com.gotop.euipApply.model.TApplyEuip;
import com.gotop.file.model.FileBean;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.messagePublish.dao.impl.TMessagePublishDAO;
import com.gotop.messageReceive.model.TMessageReceive;
import com.gotop.mettingApply.dao.ITMettingApplyDAO;
import com.gotop.mettingApply.model.TMettingApply;
import com.gotop.mettingApply.service.ITMettingApplyService;
import com.gotop.mettingRec.dao.ITMettingReceiveDAO;
import com.gotop.mettingRec.model.TMettingReceive;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.OpninionBean;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java_cup.terminal;

import org.apache.log4j.Logger;

public class TMettingApplyService implements ITMettingApplyService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TMettingApplyService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITMettingApplyDAO tMettingApplyDAO;
    
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
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITMettingReceiveDAO tMettingReceiveDAO;
    
    private TMessagePublishDAO tMessagePublishDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settMettingApplyDAO(ITMettingApplyDAO tMettingApplyDAO) throws Exception {
        this.tMettingApplyDAO = tMettingApplyDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITMettingApplyDAO gettMettingApplyDAO() throws Exception {
        return this.tMettingApplyDAO;
    }

	

	/**
	 * @return tMessagePublishDAO
	 */
	public TMessagePublishDAO gettMessagePublishDAO() {
		return tMessagePublishDAO;
	}

	/**
	 * @param tMessagePublishDAO 要设置的 tMessagePublishDAO
	 */
	public void settMessagePublishDAO(TMessagePublishDAO tMessagePublishDAO) {
		this.tMessagePublishDAO = tMessagePublishDAO;
	}

	@Override
	public List<TMettingApply> queryMettingApplyList(TMettingApply meet,
			MUOUserSession muo, Page page) throws Exception {
		if(meet==null){
			meet=new TMettingApply();
		}
		meet.setOracleStart(page.getBegin());
		meet.setOracleEnd(page.getBegin()+page.getLength());
		meet.setEmpid(muo.getEmpid());
		 List<TMettingApply> list= tMettingApplyDAO.queryMettingAppList(meet, page);
		 return list;
	}

	@Override
	public void insertMettingInfo(TMettingApply meet, TaskAssgineeDto dto,
			String btnType, MUOUserSession muo,File[] files,String[] filesFileName, String isFirst) throws Exception {
		    String taskId=dto.getNextTaskId();
		    String submitType = "";
		    //20140910将日期和时间分开，不改变数据库，拼装
		    if(meet.getMettingDate()!=null && !"".equals(meet.getMettingDate()) && !"null".equals(meet.getMettingDate())){
		    	String mettingTime = meet.getMettingDate()+" "+meet.getMettingTime();
		    	meet.setMettingTime(mettingTime);
		    }
		    	
		if(meet.getMettingId()==null||"".equals(meet.getMettingId())){//新增
			meet.setOrgid(muo.getOrgid());
			meet.setCreator(muo.getEmpid());
			String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
			meet.setCreateDate(currDate.substring(0, 8));
			meet.setCreateTime(currDate.substring(8));
			   //启动流程
			TaskAssgineeDto dto1=jbpmService.startProcessByDefinition(dto.getDefinitionId(),null);
			taskId=dto1.getNextTaskId();
			meet.setNodeId(taskId);
			meet.setNodeName1(jbpmService.getTaskById(taskId).getName());
			meet.setFlowId(dto1.getExecutionId());
			dto.setExecutionId(dto1.getExecutionId());
			tMettingApplyDAO.insert(meet);
			//保存流程的信息
			/**
		    * 将提交也插入意见列表
		    * 20140902
		    */
			meet.setOpninion("");
			submitType="05";
			jbpmService.saveProcessBusiness(muo,insertProcessBusiness(meet,dto));
		}else{//修改
			/**
			    * 2014.9.1 不需要更新发起人的机构
			    */
			meet.setOrgid(null);
			   submitType = "05";
			   tMettingApplyDAO.updateMetingInfo(meet);
			   /**
			    * 将提交也插入意见列表
			    * 20140902
			    */
			   if(meet.getOpninion()==null || "null".equals(meet.getOpninion())){
				   meet.setOpninion("");
			   }
			   TProcessBusiness business = jbpmService.findProcessBusiness(dto);
	            business.setBusinessTitle(meet.getTitle());
			jbpmService.updateProcessBusiness(business);
		}
		tFileResourceTableService.fileUploads(makeFileBean(meet,dto), files, filesFileName, muo);
		//审核人信息记录
		dto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
		dto.setTaskId(taskId);
		jbpmService.assignTask(dto);
		if(!"1".equals(btnType)){//
			meet.setNodeName1(jbpmService.getTaskById(taskId).getName());
			jbpmService.completeTask(taskId, dto.getTransitionName(), null);
			if(!"退回".equals(dto.getTransitionName())){	
				if("null".equals(dto.getEmpNames()))
					dto.setEmpNames("");
				if(isFirst==null && dto.getEmpIds()!=null && !"".equals(dto.getEmpIds())){
					submitType = "01";
				}
				if("发布".equals(dto.getTransitionName())){
					//生成信息接收信息
					TMettingReceive recv=new TMettingReceive();
					recv.setMettingId(meet.getMettingId());
					recv.setStatus("0");
					String[] recvemp=meet.getJoinEmp().split(",");
					String[] recvlea = meet.getJoinLeader().split(",");
					for(int i=0;i<recvemp.length;i++){
					 recv.setRecEmp(Long.valueOf(recvemp[i]));
					 tMettingReceiveDAO.insert(recv);
					}
					if(!"".equals(recvlea[0])){
						for(int i=0;i<recvlea.length;i++){
							 recv.setRecEmp(Long.valueOf(recvlea[i]));
							 tMettingReceiveDAO.insert(recv);
							}
					}
					submitType = "08";
					meet.setNodeId(taskId);
					dto.setNextTaskId("");
					jbpmService.saceTaskAssignee(makeTaskAssgineeDto(meet, muo,dto));
				}else{
					String nextTaskId = jbpmService.getNextTaskId(dto.getExecutionId()); 
					dto.setNextTaskId(nextTaskId);
					meet.setNodeId(taskId);
					jbpmService.saceTaskAssignee(makeTaskAssgineeDto(meet, muo,dto));
				}			
			}else{//退出
				submitType = "02";
				jbpmService.turnBackTaskAssignee(makeTaskAssgineeDtoBack(dto,meet,muo));
			}

			insertApproveOpninion(meet,muo,taskId, submitType,dto);
		}
		
	}

	@Override
	public TMettingApply queryMettingInfo(String mettingId, String flowId)
			throws Exception {
		TMettingApply meet = new TMettingApply();
		if(mettingId!=null&&!"".equals(mettingId))
		meet.setMettingId(Long.valueOf(mettingId));
		if(flowId!=null&&!"".equals(flowId))
	    meet.setFlowId(flowId);
		return tMettingApplyDAO.queryMettingApply(meet);
	}
	
	 /**
	 * 生成与流程相对应的业务信息
	 * @param message
	 * @return
	 */
	public  TProcessBusiness insertProcessBusiness(TMettingApply meet,TaskAssgineeDto dto){
		 TProcessBusiness processBusiness= new TProcessBusiness();
		try {
			processBusiness.setBusinessKey(meet.getMettingId());
			processBusiness.setBusinessType(dto.getBusinessType());
			processBusiness.setBusinessTitle(meet.getTitle());
			processBusiness.setExecutionId(meet.getFlowId());
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
	public FileBean makeFileBean(TMettingApply meet,TaskAssgineeDto dto){
		FileBean tfile= new FileBean();
		try{
			tfile.setResourceId(meet.getMettingId());
			tfile.setResourceType(dto.getBusinessType());
			tfile.setNodeId(meet.getNodeId());
			tfile.setNodeName(meet.getNodeName1());
		}catch (Exception e) {
			log.error("生成FileBean文件实体失败",e);
		}
		return tfile;
	}
	
	/**
	 * 生成意见
	 * @param muo
	 * @param taskId
	 */
	public void insertApproveOpninion(TMettingApply meet,MUOUserSession muo,String taskId,String type,TaskAssgineeDto dto){
		try {
			if(meet!=null&&meet.getMettingId()!=null&&!"".equals(meet.getMettingId())){
				if(meet.getOpninion()!=null){
//					if(meet.getOpninion()!=null&&!"".equals(meet.getOpninion())){
					String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
			    	TApproveOpninion opninion=new TApproveOpninion();
			    	opninion.setResourceId(meet.getMettingId());
			    	opninion.setOperator(muo.getEmpid());
			    	opninion.setOrgid(String.valueOf(muo.getOrgid()));
			    	opninion.setResourceType(dto.getBusinessType());
			    	opninion.setOperatorType(type);
			    	opninion.setOperaterDate(currDate.substring(0, 8));
			    	opninion.setOperaterTime(currDate.substring(8));
			    	opninion.setOpninionContent(meet.getOpninion());
			    	/**
			    	 * 20140903
			    	 */
			    	opninion.setNextorgname("");
			    	if(dto.getEmpNames()!=null&&!"".equals(dto.getEmpNames())){
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
			    	// 20140908 会议申请下一操作人没存储
			    	/*if(dto.getEmpNames()!=null&&!"".equals(dto.getEmpNames())){
			    		opninion.setNextOprName(dto.getEmpNames());
			    		List<HashMap<String,Object>> list = tMessagePublishDAO.queryOrgName(dto.getEmpIds());
			    		for(int i=0;i<list.size();i++){
			    			opninion.setNextorgname(opninion.getNextorgname()+(String) list.get(i).get("ORGNAME"));
			    		}
			    	}*/
			    	//需要taskId
			    	opninion.setNodeId(taskId);
			    	opninion.setNodeName(meet.getNodeName1());
			    	tApproveOpninionDAO.insert(opninion);
				}
			}
			
		} catch (Exception e) {
		//保存审核意见失败。
			log.error("保存审核意见失败。", e);
		}
	}
	
	/**
	 * 生成意见
	 * @param muo
	 * @param taskId
	 */
	public OpninionBean insertApproveOpninion(TMettingApply meet,String taskId,String type,TaskAssgineeDto dto){
		OpninionBean bean=new OpninionBean();
		try {     if(meet!=null&&meet.getMettingId()!=null&&!"".equals(meet.getMettingId())){
//			          if(meet.getOpninion()!=null&&!"".equals(meet.getOpninion())){
			              if(meet.getOpninion()!=null){
			        	  bean.setResourceId(meet.getMettingId());
							bean.setResourceType(dto.getBusinessType());
							bean.setOpninionContent(meet.getOpninion());
							bean.setOperatorType(type);
							bean.setNodeId(taskId);
							bean.setNodeName(jbpmService.getTaskById(taskId).getName());
			          }
		}
					
			
		} catch (Exception e) {
		//保存审核意见失败。
			log.error("保存审核意见失败。", e);
		}
		return bean;
	}
	
	
	/**
	 * 回退时的dto生成
	 * @param dto
	 * @param message
	 * @param muo
	 * @return
	 */
	public TaskAssgineeDto makeTaskAssgineeDtoBack(TaskAssgineeDto dto,TMettingApply meet,MUOUserSession muo){
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
		dto2.setBusinessKey(meet.getMettingId());
		dto2.setTaskAssingee(Long.valueOf(dto.getEmpIds()));
		dto2.setBusinessType(dto.getBusinessType());
		dto2.setTargetName(dto.getTargetName());
		   //存储节点配置对象主键
        dto2.setTaskExeConfigId(dto.getTaskExeConfigId());
		return dto2;
	}
	
	/**
	 * 生成任务实体。[jbpm]
	 * @return
	 */
	public TaskAssgineeDto makeTaskAssgineeDto(TMettingApply meet,MUOUserSession muo,TaskAssgineeDto dto){
		TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
		try {	
	    	taskAssgineeDto.setExecutionId(meet.getFlowId());
	    	taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
	    	taskAssgineeDto.setPreTaskId(meet.getNodeId());
	    	taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
	    	//if(message.getCreateDate()!=null&&!"".equals(message.getCreateDate())){
	    		//taskAssgineeDto.setPreTaskTime(message.getCreateDate());
	    	//}else{
	    	String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
	    		taskAssgineeDto.setPreTaskTime(currDate);
	    	//}
	    	taskAssgineeDto.setNextTaskId(dto.getNextTaskId());
	    	taskAssgineeDto.setBusinessKey(meet.getMettingId());
	    	taskAssgineeDto.setBusinessType(dto.getBusinessType());
	    	taskAssgineeDto.setEmpIds(dto.getEmpIds());
	    	taskAssgineeDto.setEmpNames(dto.getEmpNames());
	    	taskAssgineeDto.setTargetName(dto.getTargetName());
	    	
	    	   //存储节点配置对象主键
	    	taskAssgineeDto.setTaskExeConfigId(dto.getTaskExeConfigId());
		} catch (Exception e) {
			log.error("获取任务实体", e);
		}
		return taskAssgineeDto;
	}
	
	/**
	 * 查询待阅读的信息
	 */
	@Override
	public TMettingApply querySuperviseRecive(TMettingApply meet)
			throws Exception {
		if(meet!=null){//未阅状态
			if("0".equals(meet.getStatus())||"".equals(meet.getStatus()))
			tMettingReceiveDAO.updateMettingRecvInfo(meet);
		}
		return tMettingApplyDAO.queryMettingApply1(meet);
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

	public ITMettingReceiveDAO gettMettingReceiveDAO() {
		return tMettingReceiveDAO;
	}

	public void settMettingReceiveDAO(ITMettingReceiveDAO tMettingReceiveDAO) {
		this.tMettingReceiveDAO = tMettingReceiveDAO;
	}

	@Override
	public void insertMessageTransmit(TMettingApply meet, MUOUserSession muo) {
		if(meet!=null){
	    	TMettingReceive recv=new TMettingReceive();
			recv.setMettingId(meet.getMettingId());
			recv.setStatus("0");
			String[] recvemp=meet.getJoinEmp().split(",");
			String[] empnames=meet.getJoinEmpname().split(",");
			TaskAssgineeDto dto = new TaskAssgineeDto();
			dto.setBusinessType("04");
			for(int i=0;i<recvemp.length;i++){
					recv.setRecEmp(Long.valueOf(recvemp[i]));
					tMettingReceiveDAO.insert(recv);
		    		dto.setEmpIds(recvemp[i]);
		    		dto.setEmpNames(empnames[i]);
				    insertApproveOpninion(meet, muo, "", "04", dto); 
		    	} 
		    }
	    }

	@Override
	public List<TMettingApply> queryHomeMettingList(TMettingApply meet,
			MUOUserSession muo, Page page) {
		if(meet==null){
			meet=new TMettingApply();
		}
		meet.setOracleStart(page.getBegin());
		meet.setOracleEnd(page.getBegin()+page.getLength());
		meet.setEmpid(muo.getEmpid());
		 List<TMettingApply> list= tMettingApplyDAO.queryHomeMettingList(meet, page);
		 return list;
	}
}