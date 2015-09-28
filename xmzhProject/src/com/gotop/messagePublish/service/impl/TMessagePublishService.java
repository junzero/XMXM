package com.gotop.messagePublish.service.impl;

import com.gotop.file.model.FileBean;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.OpninionBean;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.messagePublish.dao.ITMessagePublishDAO;
import com.gotop.messagePublish.model.TMessagePublish;
import com.gotop.messagePublish.service.ITMessagePublishService;
import com.gotop.messageReceive.dao.ITMessageReceiveDAO;
import com.gotop.messageReceive.model.TMessageReceive;
import com.gotop.supervise.dao.impl.TSuperviseTableDAO;
import com.gotop.supervise.model.TSuperviseTable;
import com.gotop.tyjg.empMngr.dao.IEmpMngrDao;
import com.gotop.util.string.Obj2StrUtils;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.codehaus.xfire.client.MessageIdCorrelator;

public class TMessagePublishService implements ITMessagePublishService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TMessagePublishService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITMessagePublishDAO tMessagePublishDAO;
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
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITMessageReceiveDAO tMessageReceiveDAO;
    
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITFileResourceTableService tFileResourceTableService;
    /**
     * 督办单Dao层注入
     */
    protected TSuperviseTableDAO tSuperviseTableDAO;
    
    private IEmpMngrDao empMngrDao;
    
    

    public IEmpMngrDao getEmpMngrDao() {
		return empMngrDao;
	}

	public void setEmpMngrDao(IEmpMngrDao empMngrDao) {
		this.empMngrDao = empMngrDao;
	}

	/**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settMessagePublishDAO(ITMessagePublishDAO tMessagePublishDAO) throws Exception {
        this.tMessagePublishDAO = tMessagePublishDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITMessagePublishDAO gettMessagePublishDAO() throws Exception {
        return this.tMessagePublishDAO;
    }
    
    

	public TSuperviseTableDAO gettSuperviseTableDAO() {
		return tSuperviseTableDAO;
	}

	public void settSuperviseTableDAO(TSuperviseTableDAO tSuperviseTableDAO) {
		this.tSuperviseTableDAO = tSuperviseTableDAO;
	}

	public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}
	


	public ITMessageReceiveDAO gettMessageReceiveDAO() {
		return tMessageReceiveDAO;
	}

	public void settMessageReceiveDAO(ITMessageReceiveDAO tMessageReceiveDAO) {
		this.tMessageReceiveDAO = tMessageReceiveDAO;
	}

	@Override
	public TaskAssgineeDto insertTMessagePublish(TMessagePublish message,File[] files,String[] filesFileName,MUOUserSession muo,String btnType,TaskAssgineeDto taskAssgineeDto,String isFirst) throws Exception {
		String taskId=taskAssgineeDto.getNextTaskId();
		String submitType = "";
		if(message.getMessageId()==null||"".equals(message.getMessageId())){
			if(filesFileName!=null&&!"".equals(filesFileName.toString()))
			   message.setExistsFile("1");
			   HashMap<String, Object> map= new HashMap<String, Object>();
			   map.put("user",String.valueOf(muo.getEmpid()));
			   TaskAssgineeDto dto1=jbpmService.startProcessByDefinition(taskAssgineeDto.getDefinitionId(),map);
			   taskId=dto1.getNextTaskId();
			   message.setNodeId(taskId);
			   message.setNodeName1(jbpmService.getTaskById(taskId).getName());
			   message.setFlowId(dto1.getExecutionId());
			   taskAssgineeDto.setExecutionId(dto1.getExecutionId());
				tMessagePublishDAO.insertMessagePublish(message);
				   /**
				    * 将提交也插入意见列表
				    * 20140902
				    */
				message.setOpninion("");
				submitType="05";
				/*insertApproveOpninion(message,muo,taskId, "05",taskAssgineeDto);*/
				//保存流程的信息
				jbpmService.saveProcessBusiness(muo,insertProcessBusiness(message,taskAssgineeDto));
			
		   }else{
			   //修改
			   if(filesFileName!=null&&!"".equals(filesFileName.toString()))
					message.setExistsFile("1");
			   /**
			    * 2014.9.1 不需要更新发起人的机构
			    */
			   message.setOrgid(null);
			   submitType = "05";
			   tMessagePublishDAO.updateMessageInfo(message);
			   /**
			    * 将提交也插入意见列表
			    * 20140902
			    */
			   if(message.getOpninion()==null || "null".equals(message.getOpninion())){
				   message.setOpninion("");
			   }
				/*insertApproveOpninion(message,muo,taskId, "05",taskAssgineeDto);*/
			   //修改流程的信息
			   TProcessBusiness business = jbpmService.findProcessBusiness(taskAssgineeDto);
	            business.setBusinessTitle(message.getMessageTitle());
			jbpmService.updateProcessBusiness(business);
		   }
		//生成文件
		tFileResourceTableService.fileUploads(makeFileBean(message,taskAssgineeDto), files, filesFileName, muo);
		if(!"1".equals(btnType)){//1为单纯保存发布信息
				message.setNodeName1(jbpmService.getTaskById(taskId).getName());
			//审核人信息记录
				taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
				taskAssgineeDto.setTaskId(taskId);
				jbpmService.assignTask(taskAssgineeDto);
			jbpmService.completeTask(taskId, taskAssgineeDto.getTransitionName(), null);
			if(!"退回".equals(taskAssgineeDto.getTransitionName())){
				if("null".equals(taskAssgineeDto.getEmpNames()))
					taskAssgineeDto.setEmpNames("");
				if(isFirst==null && taskAssgineeDto.getEmpIds()!=null && !"".equals(taskAssgineeDto.getEmpIds()))
					submitType = "01";
				if("直接发布".equals(taskAssgineeDto.getTransitionName())){
					String type="0";
					message.setNodeId(taskId);
					TaskAssgineeDto taskAssginee = makeTaskAssgineeDto(message, muo,taskAssgineeDto);
					taskAssginee.setNextTaskId("");
					jbpmService.saceTaskAssignee(taskAssginee);
			    	//生成发布文件信息
					submitType = "06";
			    	insertMessageRecive(message,muo,taskId,type);
				}else if("批示".equals(taskAssgineeDto.getTransitionName())){
					submitType = "05";
					jbpmService.turnBackTaskAssignee(makeTaskAssgineeDtoBack(taskAssgineeDto,message,muo));
				}else{
					String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
					taskAssgineeDto.setNextTaskId(nextTaskId);
					message.setNodeId(taskId);
					jbpmService.saceTaskAssignee(makeTaskAssgineeDto(message, muo,taskAssgineeDto));	
				}
				insertApproveOpninion(message,muo,taskId, submitType,taskAssgineeDto);
			}else{
				insertApproveOpninion(message,muo,taskId, "02",taskAssgineeDto);
				jbpmService.turnBackTaskAssignee(makeTaskAssgineeDtoBack(taskAssgineeDto,message,muo));
			}	
		} 
		return null;
	}
	
	/**
	 * 生成与流程相对应的业务信息
	 * @param message
	 * @return
	 */
	public  TProcessBusiness insertProcessBusiness(TMessagePublish message,TaskAssgineeDto dto){
		 TProcessBusiness processBusiness= new TProcessBusiness();
		try {
			processBusiness.setBusinessKey(message.getMessageId());
			processBusiness.setBusinessType(dto.getBusinessType());
			processBusiness.setBusinessTitle(message.getMessageTitle());
			processBusiness.setExecutionId(message.getFlowId());
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
	public FileBean makeFileBean(TMessagePublish message,TaskAssgineeDto dto){
		FileBean tfile= new FileBean();
		try{
			tfile.setResourceId(message.getMessageId());
			tfile.setResourceType(dto.getBusinessType());
			tfile.setNodeId(message.getNodeId());
			tfile.setNodeName(message.getNodeName1());
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
	public void insertApproveOpninion(TMessagePublish message,MUOUserSession muo,String taskId,String type,TaskAssgineeDto dto){
		try {
			if(message!=null&&message.getMessageId()!=null&&!"".equals(message.getMessageId())){
				/**
				 * 2014.9.1转发修改
				 */
				if(message.getOpninion()!=null/*&&!"".equals(message.getOpninion())*/){
					String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
			    	TApproveOpninion opninion=new TApproveOpninion();
			    	opninion.setResourceId(message.getMessageId());
			    	opninion.setOperator(muo.getEmpid());
			    	opninion.setOrgid(String.valueOf(muo.getOrgid()));
			    	opninion.setResourceType(dto.getBusinessType());
			    	opninion.setOperatorType(type);
			    	opninion.setOperaterDate(currDate.substring(0,8));
			    	opninion.setOperaterTime(currDate.substring(8));
			    	opninion.setOpninionContent(message.getOpninion());
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
			    	//需要taskId
			    	opninion.setNodeId(taskId);
			    	opninion.setNodeName(message.getNodeName1());
			    	tApproveOpninionDAO.insert(opninion);
				}
			}
			
		} catch (Exception e) {
		//保存审核意见失败。
			log.error("保存审核意见失败。", e);
		}
	}
	
	
	
	/**
	 * 生成接收的发布信息
	 * @param muo
	 * @param taskId
	 */
	public void insertMessageRecive(TMessagePublish message,MUOUserSession muo,String taskId,String type){
		try{
			if(message!=null){
				 if(message.getPublishRange()!=null&&!"".equals(message.getPublishRange())){
					String publishRange=message.getPublishRange();
					String[] str=publishRange.split(",");
					for(int i=0;i<str.length;i++){
						 TMessageReceive rec=new TMessageReceive();
							rec.setMessageId(message.getMessageId());
							rec.setType("0");
							rec.setReceiveObject(str[i]);
							rec.setReceiveStatus("0");				
							rec.setSubmitEmp(muo.getEmpid());
							tMessageReceiveDAO.insert(rec);
					}					
				 }	
			}
			
		}catch (Exception e) {
		log.error("生成接收发布信息失败", e);
		}
	}
	
	/**
	 * 回退时的dto生成
	 * @param dto
	 * @param message
	 * @param muo
	 * @return
	 */
	public TaskAssgineeDto makeTaskAssgineeDtoBack(TaskAssgineeDto dto,TMessagePublish message,MUOUserSession muo){
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
		dto2.setBusinessKey(message.getMessageId());
//		dto2.setTaskAssingee(dto.getTaskAssingee());
	     dto2.setTaskAssingee(Long.valueOf(dto.getEmpIds()));
	     dto2.setTargetName(dto.getTargetName());
		dto2.setBusinessType(dto.getBusinessType());
		   //存储节点配置对象主键
        dto2.setTaskExeConfigId(dto.getTaskExeConfigId());
		return dto2;
	}
	
	/**
	 * 生成任务实体。[jbpm]
	 * @return
	 */
	public TaskAssgineeDto makeTaskAssgineeDto(TMessagePublish message,MUOUserSession muo,TaskAssgineeDto dto){
		TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
		try {	
	    	taskAssgineeDto.setExecutionId(message.getFlowId());
	    	taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
	    	taskAssgineeDto.setPreTaskId(message.getNodeId());
	    	taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
	    	//if(message.getCreateDate()!=null&&!"".equals(message.getCreateDate())){
	    		//taskAssgineeDto.setPreTaskTime(message.getCreateDate());
	    	//}else{
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    		taskAssgineeDto.setPreTaskTime(sdf.format(new java.util.Date()));
	    	//}
	    	taskAssgineeDto.setNextTaskId(dto.getNextTaskId());
	    	taskAssgineeDto.setBusinessKey(message.getMessageId());
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
	
	@Override
	public List<TMessagePublish> queryTMessagePublishList(MUOUserSession muo,
			TMessagePublish message, Page page) throws Exception {
	 if(message==null){
		 message=new TMessagePublish(); 
	 }
	 message.setOracleStart(page.getBegin());
	 message.setOracleEnd(page.getBegin()+page.getLength());
	// message.setCreateEmpid(muo.getEmpid());
	 message.setEmpId(String.valueOf(muo.getEmpid()));
	 message.setOrgid(String.valueOf(muo.getOrgid()));
	 String roleid="";
	 for(int i=0;i<muo.getRoleid().length;i++){
		 roleid+="'"+muo.getRoleid()[i]+"',";
	 }
	 roleid=roleid.substring(0, roleid.length()-1);
	 message.setRoleId(roleid);
	 message.setJgsx(muo.getOrgJgsx());
	 /**
	  * 2014.9.1 发布岗位
	  */
	 message.setPublishRange(Obj2StrUtils.join(muo.getPositionId(), String.class, ","));
	 List<TMessagePublish> list=tMessagePublishDAO.queryMessagePublishList(message, page);
	 return list;
	}

	@Override
	public void insertTFileResource(TFileResourceTable tfile) throws Exception {
		if(tfile!=null){
			tMessagePublishDAO.insertTFileResource(tfile);
		}
	}

	@Override
	public Long queryBatchSeq() throws Exception {
		return tMessagePublishDAO.queryBatchSeq();
		
	}

	/**
	 * 获取意见信息
	 */
	@Override
	public List<TApproveOpninion> queryApproveOpninions(HashMap op)
			throws Exception {
		return tMessagePublishDAO.queryApproveOpninions(op);
	}

	/**
	 * 获取信息详情
	 */
	@Override
	public TMessagePublish queryMessageInfo(String messageId,String flowId) throws Exception {
		return tMessagePublishDAO.queryMessageInfo(messageId,flowId);
	}

	/**
	 * 阅毕
	 */
	@Override
	public void insertMessageRedPer(String messageId,String opninion,MUOUserSession muo) throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("empId", muo.getEmpid());
		String[] messages=messageId.split(",");
		TaskAssgineeDto dto=new TaskAssgineeDto();
		dto.setBusinessType("01");
		for(int i=0;i<messages.length;i++){
			map.put("messageId", messages[i]);
			tMessagePublishDAO.insertMessageRedPer(map);
			//插入意见
			TMessagePublish message=tMessagePublishDAO.queryMessageInfo(messages[i], null);
			if(opninion!=null&&!"".equals(opninion))
			message.setOpninion(opninion);
			else {
				message.setOpninion("阅毕");
			}
			message.setNodeName1("阅毕");
			insertApproveOpninion(message, muo, "", "03", dto);
		}
		
		
	}
 
    /**
     *阅毕，在转督办时使用，
     */

    public void insertMessageRedPer_zdb(String messageId,String opninion,MUOUserSession muo) throws Exception {
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("empId", muo.getEmpid());
        String[] messages=messageId.split(",");
        TaskAssgineeDto dto=new TaskAssgineeDto();
        dto.setBusinessType("01");
        for(int i=0;i<messages.length;i++){
            map.put("messageId", messages[i]);
            //插入之前先判断是否已经阅读过，如果存在就不在插入数据
           Long l= tMessagePublishDAO.queryMessageRed(map);
           if(l<1){
               tMessagePublishDAO.insertMessageRedPer(map);
           }
        }
        
        
    }
	/**
	 * 转发
	 */
	@Override
	public void insertMessageTransmit(TMessagePublish message,
			MUOUserSession muo,String messageId, String status) throws Exception {
		 /**
		  * 2014.8.30 转发修改
		  */
/*	    if(message!=null){
	    	String[] empids=message.getEmpId().split(",");
	    	String[] empnames=message.getEmpName().split(",");
	    	String[] messageIds=messageId.split(",");
	    	String[] statusAlone = status.split(",");
	    	TMessageReceive rec=new TMessageReceive();
			rec.setMessageId(message.getMessageId());
			rec.setType("1");
			rec.setReceiveStatus("0");				
			rec.setSubmitEmp(muo.getEmpid());
			TaskAssgineeDto dto=new TaskAssgineeDto();
			dto.setBusinessType("01");
		    for(int i=0;i<empids.length;i++){
		    	for(int j=0;j<messageIds.length;j++){
		    		rec.setReceiveObject(empids[i]);
		    		rec.setMessageId(Long.valueOf(messageIds[j]));
		    		message.setMessageId(Long.valueOf(messageIds[j]));
		    		dto.setEmpIds(empids[i]);
		    		dto.setEmpNames(empnames[i]);
			    	tMessageReceiveDAO.insert(rec);
				    insertApproveOpninion(message, muo, "", "04", dto); 
		    	} 
		    }
		    for(int i=0;i<messageIds.length;i++){
		    	HashMap<String, Object> map=new HashMap<String, Object>();
				map.put("empId", muo.getEmpid());
				map.put("messageId", messageIds[i]);
				if("1".equals(statusAlone[i]))//转发已阅的状态不要改为阅闭
					continue;
				tMessagePublishDAO.insertMessageRedPer(map);
		    }
	    }*/
	    
	    /**
	     * 2014年9月15日
	     * 修改信息发布-转发功能
	     * 说明
	     * 转发前判断接收人是否已经接收了信息
	     * 若已接收-判断接收人是否阅闭
	     * 					若已阅闭- 删除接收人阅闭记录
	     * 					若未阅闭- 不操作
	     * 
	     * 若未接受-新增接收人接收记录
	     * 
	     * 完成上述操作后
	     * 判断转发人是否阅闭
	     * 
	     * 若已阅闭-不操作
	     * 若未阅闭-新增转发人阅闭记录
	     */
	    
	    if(message != null){
	    	String[] empids=message.getEmpId().split(",");
	    	String[] empnames=message.getEmpName().split(",");
	    	String[] messageIds=messageId.split(",");
	    	String[] statusAlone = status.split(",");
	    	
	    	TMessageReceive rec=new TMessageReceive();
			rec.setMessageId(message.getMessageId());
			rec.setType("1");
			rec.setReceiveStatus("0");				
			rec.setSubmitEmp(muo.getEmpid());
			TaskAssgineeDto dto=new TaskAssgineeDto();
			dto.setBusinessType("01");
			
		    for(int i=0;i<empids.length;i++){
		    	for(int j=0;j<messageIds.length;j++){
		    		//判断empid是否接收
		    		if(judgeIsReceived(empids[i],messageIds[j]).size() != 0){
		    			//接收人已接收
		    			//判断empid是否阅闭
		    			if(judgeIsReaded(empids[i],messageIds[j])){
		    				//已阅闭-删除阅闭记录
		    				deleteReaded(empids[i],messageIds[j]);
		    			}else{
		    				//未阅闭
		    			}
		    			message.setMessageId(Long.valueOf(messageIds[j]));
		    			dto.setEmpIds(empids[i]);
			    		dto.setEmpNames(empnames[i]);
			    		insertApproveOpninion(message, muo, "", "04", dto); 
		    		}else{
		    			//接收人未接收-新增接收人接收信息
		    			rec.setReceiveObject(empids[i]);
			    		rec.setMessageId(Long.valueOf(messageIds[j]));
			    		message.setMessageId(Long.valueOf(messageIds[j]));
			    		dto.setEmpIds(empids[i]);
			    		dto.setEmpNames(empnames[i]);
				    	tMessageReceiveDAO.insert(rec);
					    insertApproveOpninion(message, muo, "", "04", dto); 
		    		}
		    		
		    		//判断转发人是否阅闭记录
		    		if(judgeIsReaded(String.valueOf(muo.getEmpid()), messageIds[j])){
		    			//已阅闭
		    		}else{
		    			//未阅闭
		    			HashMap<String, Object> map=new HashMap<String, Object>();
						map.put("empId", muo.getEmpid());
						map.put("messageId", messageIds[j]);
						tMessagePublishDAO.insertMessageRedPer(map);
		    		}
		    	} 
		    }
		    
		 
		    
	    }
	}
	
	/**
	 * 判断empId的用户是否接收到信息
	 * 
	 * @param empId				接收人empId
	 * @param messageId		接收信息的Id
	 * @return
	 */
	public List<TMessageReceive> judgeIsReceived(String empId,String messageId){
		TMessageReceive messageReceive = new TMessageReceive();
		messageReceive.setMessageId(Long.valueOf(messageId));
		messageReceive.setReceiveObject(empId);
		return this.tMessageReceiveDAO.judgeIsReceived(messageReceive);
	}
	
	/**
	 * 判断empId的用户是否阅闭信息
	 * 
	 * @param empId				接收人empId
	 * @param messageId		接收信息的Id
	 * @return
	 */
	public boolean judgeIsReaded(String empId,String messageId){
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("empId", Long.valueOf(empId));
		map.put("messageId", Long.valueOf(messageId));
		return this.tMessageReceiveDAO.judgeIsReaded(map);
	}
	
	/**
	 * 删除empId的用户的已阅闭信息
	 * 
	 * @param empId				接收人empId
	 * @param messageId		接收信息的Id
	 * @return
	 */
	public void deleteReaded(String empId,String messageId){
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("empId", Long.valueOf(empId));
		map.put("messageId", Long.valueOf(messageId));
		this.tMessageReceiveDAO.deleteReaded(map);
	}
	
	//20140908 修改
	/**
	 * 转发
	 */
	@Override
	public void insertMessageTransmit(TMessagePublish message,
			MUOUserSession muo,String messageId) throws Exception {
		
	    if(message!=null){
	    	String[] empids=message.getEmpId().split(",");
	    	String[] empnames=message.getEmpName().split(",");
	    	String[] messageIds=messageId.split(",");
	    	TMessageReceive rec=new TMessageReceive();
			rec.setMessageId(message.getMessageId());
			rec.setType("1");
			rec.setReceiveStatus("0");				
			rec.setSubmitEmp(muo.getEmpid());
			TaskAssgineeDto dto=new TaskAssgineeDto();
			dto.setBusinessType("01");
		    for(int i=0;i<empids.length;i++){
		    	for(int j=0;j<messageIds.length;j++){
		    		rec.setReceiveObject(empids[i]);
		    		rec.setMessageId(Long.valueOf(messageIds[j]));
		    		message.setMessageId(Long.valueOf(messageIds[j]));
		    		dto.setEmpIds(empids[i]);
		    		dto.setEmpNames(empnames[i]);
			    	tMessageReceiveDAO.insert(rec);
				    insertApproveOpninion(message, muo, "", "04", dto); 
		    	} 
		    }
		   // for(int i=0;i<messageIds.length;i++){
		    //	HashMap<String, Object> map=new HashMap<String, Object>();
				//map.put("empId", muo.getEmpid());
				//map.put("messageId", messageIds[i]);
				//tMessagePublishDAO.insertMessageRedPer(map);
		   // }
	    }
	}

/*	*//**
	 * 转督办
	 *//*
	@Override
	public String insertMegTstSup(TMessagePublish message, MUOUserSession muo,TaskAssgineeDto taskAssgineeDto)
			throws Exception {
			if("1".equals(message.getStatus())){//转发已阅的状态不要改为阅闭,未阅的要改为已阅
				HashMap<String, Object> map=new HashMap<String, Object>();
				map.put("empId", muo.getEmpid());
				map.put("messageId", message.getMessageId());
				tMessagePublishDAO.insertMessageRedPer(map);
			}
		   //查询旧信息发布的信息。
		    String taskId="";
		    String type="0";
		    String title="";
		    String content="";
        	TMessagePublish message1=queryMessageInfo(String.valueOf(message.getMessageId()), message.getFlowId());	
        	if(message1!=null){
        		title="关于'"+message1.getMessageTitle()+"'的督办单";
        		content="关于'"+message1.getMessageTitle()+"'的督办单,请到草稿箱中发起督办单。";
			   message.setMessageTitle(title);
			   message.setContent(content);
			   message.setPublishType("02");//发布类型
			   message.setPublishRange(taskAssgineeDto.getEmpIds());//发布范围
			   message.setObjName(taskAssgineeDto.getEmpNames());
			   message.setFlowId("");
			   message.setMessageType("99");
			   message.setExistsFile(message1.getExistsFile());//文件标志
			   String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
			   message.setCreateDate(currDate.substring(0, 8));
			   message.setCreateTime(currDate.substring(8));
			   message.setMessageType("06");
			   tMessagePublishDAO.insertMessagePublish(message);
        	//插入意见
			   message.setOpninion("");
			   insertApproveOpninion(message,muo,taskId, "04",taskAssgineeDto);//04表示转发
			 //插入文件
			   //查询当前文件
			List<TFileResourceTable> files= tFileResourceTableService.queryFileByIdandType(message1.getMessageId(), taskAssgineeDto.getBusinessType(),"");
			if(files.size()>0){//插入文件
				for(int i=0;i<files.size();i++){
					files.get(i).setResourceId(message.getMessageId());
					files.get(i).setCreator(muo.getEmpid());
					tFileResourceTableService.insert(files.get(i));
				}
			}
			//生成接收信息
			insertMessageRecive(message, muo, taskId, type);
			//生成督办草稿
			 //定义ID暂时无地方取
			taskAssgineeDto.setBusinessType("02");
			TSuperviseTable supervise=new TSuperviseTable();
			supervise.setOrgid(empMngrDao.queryEmpOrgid(taskAssgineeDto.getEmpIds()));//获取督办人的机构
    		supervise.setCreateor(taskAssgineeDto.getEmpIds());//督办人
    	    supervise.setCreateDate(currDate.substring(0, 8));
    	    supervise.setSuperviseItem(title);
    	    supervise.setContent(content);
    	    supervise.setCreateTime(currDate.substring(8));
    		HashMap<String, Object> map= new HashMap<String, Object>();
			map.put("user",taskAssgineeDto.getEmpIds());
			TaskAssgineeDto dto1=jbpmService.startProcessByDefinition(taskAssgineeDto.getDefinitionId(),map);
			taskId=dto1.getNextTaskId();
			supervise.setNodeId(taskId);
			supervise.setNodeName1(jbpmService.getTaskById(taskId).getName());
			supervise.setFlowId(dto1.getExecutionId());
			taskAssgineeDto.setExecutionId(dto1.getExecutionId());
			supervise.setConUnitName("");
			supervise.setImpOrgid("");
			supervise.setImpUnitName("");
			supervise.setCoOrgid("");
    		tSuperviseTableDAO.insertSuperviseTable(supervise);
    		//保存流程的信息
			jbpmService.saveProcessBusiness(muo,insertProcessBusiness(supervise,taskAssgineeDto));
			//保存督办文件信息
			if(files.size()>0){//插入文件
				for(int i=0;i<files.size();i++){
					files.get(i).setResourceId(supervise.getSuperviseId());
					files.get(i).setResourceType(taskAssgineeDto.getBusinessType());
					files.get(i).setCreator(muo.getEmpid());
					tFileResourceTableService.insert(files.get(i));
				}
			}
			return "success";
        	}else{
        		return "ifnull";
        	}
	} */
	
	//20140908 修改
	/**
	 * 转督办
	 */
	@Override
	public String insertMegTstSup(TMessagePublish message, MUOUserSession muo,TaskAssgineeDto taskAssgineeDto)
			throws Exception {
		   //查询旧信息发布的信息。
		    String taskId="";
		    String type="0";
		    String title="";
		    String content="";
        	TMessagePublish message1=queryMessageInfo(String.valueOf(message.getMessageId()), message.getFlowId());	
        	if(message1!=null){
        		title="关于'"+message1.getMessageTitle()+"'的督办单";
        		content="关于'"+message1.getMessageTitle()+"'的督办单,请到草稿箱中发起督办单。";
			   message.setMessageTitle(title);
			   message.setContent(content);
			   message.setPublishType("02");//发布类型
			   message.setPublishRange(taskAssgineeDto.getEmpIds());//发布范围
			   message.setObjName(taskAssgineeDto.getEmpNames());
			   message.setFlowId("");
			   message.setMessageType("99");
			   message.setExistsFile(message1.getExistsFile());//文件标志
			   String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
			   message.setCreateDate(currDate.substring(0, 8));
			   message.setCreateTime(currDate.substring(8));
			   tMessagePublishDAO.insertMessagePublish(message);
        	//插入意见
			   if(message.getOpninion()==null||"".equals(message.getOpninion()))
			   message.setOpninion("");
			   if(message1.getOpninion()==null||"".equals(message1.getOpninion()))
				   message1.setOpninion("");
			   taskAssgineeDto.setBusinessType("01");
			   //11 - 操作类型为转督办
			   insertApproveOpninion(message,muo,taskId, "11",taskAssgineeDto);//04表示转发 
			   taskAssgineeDto.setBusinessType("01");
			   //message1.setOpninion(message.getOpninion());
			   insertApproveOpninion(message1,muo,taskId, "11",taskAssgineeDto);//11表示转督办 插入旧信息表中
			   insertMessageRedPer_zdb(String.valueOf(message1.getMessageId()),message1.getOpninion(),muo);
			 //插入文件
			   //查询当前文件
			List<TFileResourceTable> files= tFileResourceTableService.queryFileByIdandType(message1.getMessageId(), taskAssgineeDto.getBusinessType(),"");
			if(files.size()>0){//插入文件
				for(int i=0;i<files.size();i++){
					files.get(i).setResourceId(message.getMessageId());
					files.get(i).setCreator(muo.getEmpid());
					tFileResourceTableService.insert(files.get(i));
				}
			}
			//生成接收信息
			insertMessageRecive(message, muo, taskId, type);
			//生成督办草稿
			 //定义ID暂时无地方取
			taskAssgineeDto.setBusinessType("02");
			TSuperviseTable supervise=new TSuperviseTable();
			String empid=taskAssgineeDto.getEmpIds();
			supervise.setOrgid(empMngrDao.queryEmpOrgid(empid));//获取督办人的机构
    		supervise.setCreateor(taskAssgineeDto.getEmpIds());//督办人
    	    supervise.setCreateDate(currDate.substring(0, 8));
    	    supervise.setSuperviseItem(title);
    	    //supervise.setContent(content);
    	    //督办单草稿内容
    	    supervise.setContent(title + "," +message.getOpninion());
    	    supervise.setCreateTime(currDate.substring(8));
    	    supervise.setRemindId(taskAssgineeDto.getEmpIds());
    		HashMap<String, Object> map= new HashMap<String, Object>();
			map.put("user",taskAssgineeDto.getEmpIds());
			TaskAssgineeDto dto1=jbpmService.startProcessByDefinition(taskAssgineeDto.getDefinitionId(),map);
			taskId=dto1.getNextTaskId();
			supervise.setNodeId(taskId);
			supervise.setNodeName1(jbpmService.getTaskById(taskId).getName());
			supervise.setFlowId(dto1.getExecutionId());
			taskAssgineeDto.setExecutionId(dto1.getExecutionId());
			supervise.setConUnitName("");
			supervise.setImpOrgid("");
			supervise.setImpUnitName("");
			supervise.setCoOrgid("");
    		tSuperviseTableDAO.insertSuperviseTable(supervise);
    		
    		//保存流程的信息    	
    		TProcessBusiness processBusiness=new TProcessBusiness();
    		processBusiness=insertProcessBusiness(supervise,taskAssgineeDto);
    		processBusiness.setSubmitId(Long.valueOf(taskAssgineeDto.getEmpIds()));
			jbpmService.saveProcessBusiness(muo,processBusiness);
			//保存督办文件信息
			if(files.size()>0){//插入文件
				for(int i=0;i<files.size();i++){
					files.get(i).setResourceId(supervise.getSuperviseId());
					files.get(i).setResourceType(taskAssgineeDto.getBusinessType());
					files.get(i).setCreator(Long.valueOf(taskAssgineeDto.getEmpIds()));
					tFileResourceTableService.insert(files.get(i));
				}
			}
			return "success";
        	}else{
        		return "ifnull";
        	}
	} 

	 /**
	 * 生成与流程相对应的业务信息
	 * @param message
	 * @return
	 */
	public  TProcessBusiness insertProcessBusiness(TSuperviseTable sup,TaskAssgineeDto dto){
		 TProcessBusiness processBusiness= new TProcessBusiness();
		try {
			processBusiness.setBusinessKey(sup.getSuperviseId());
			processBusiness.setBusinessType(dto.getBusinessType());
			processBusiness.setBusinessTitle(sup.getSuperviseItem());
			processBusiness.setExecutionId(sup.getFlowId());
		} catch (Exception e) {
			log.error("生成实例标题信息", e);
		}
		return processBusiness;
	}


	public ITFileResourceTableService gettFileResourceTableService() {
		return tFileResourceTableService;
	}

	public void settFileResourceTableService(
			ITFileResourceTableService tFileResourceTableService) {
		this.tFileResourceTableService = tFileResourceTableService;
	}

	public ITApproveOpninionDAO gettApproveOpninionDAO() {
		return tApproveOpninionDAO;
	}

	public void settApproveOpninionDAO(ITApproveOpninionDAO tApproveOpninionDAO) {
		this.tApproveOpninionDAO = tApproveOpninionDAO;

	}

	@Override
	public List querysupervise() {
		return this.tMessagePublishDAO.querysupervise();
		
	}

   
}