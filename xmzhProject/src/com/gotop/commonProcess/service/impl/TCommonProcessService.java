package com.gotop.commonProcess.service.impl;

import com.gotop.commonProcess.dao.ITCommonProcessDAO;
import com.gotop.commonProcess.model.TCommonProcess;
import com.gotop.commonProcess.model.TCommonProcessExample;
import com.gotop.commonProcess.service.ITCommonProcessService;
import com.gotop.dto.BusinessDto;
import com.gotop.file.model.FileBean;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dao.ITProcessTaskAssigneeDAO;
import com.gotop.jbpm.dao.ITProcessTaskAssigneePersonDAO;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.model.TProcessTaskAssignee;
import com.gotop.jbpm.model.TProcessTaskAssigneePerson;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.messagePublish.dao.ITMessagePublishDAO;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.supervise.dao.ITSuperviseTableDAO;
import com.gotop.supervise.model.TSuperviseTable;
import com.gotop.supervise.service.ITSuperviseTableService;
import com.gotop.supervise.service.impl.TSuperviseTableService;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TCommonProcessService implements ITCommonProcessService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TCommonProcessService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITCommonProcessDAO tCommonProcessDAO;
    
    private JbpmService jbpmService;
    
    private ITFileResourceTableService fileResourceTableService;
    
    private ITSuperviseTableService tSuperviseTableService;
    
    private ITMessagePublishDAO tMessagePublishDAO;
    
    private ITApproveOpninionDAO tApproveOpninionDAO;

    private ITProcessTaskAssigneePersonDAO tProcessTaskAssigneePersonDAO;
    
    private ITProcessTaskAssigneeDAO tProcessTaskAssigneeDAO;
    
    private ITSuperviseTableDAO tSuperviseTableDAO;
    
    /**
	 * @return tSuperviseTableDAO
	 */
	public ITSuperviseTableDAO gettSuperviseTableDAO() {
		return tSuperviseTableDAO;
	}

	/**
	 * @param tSuperviseTableDAO 要设置的 tSuperviseTableDAO
	 */
	public void settSuperviseTableDAO(ITSuperviseTableDAO tSuperviseTableDAO) {
		this.tSuperviseTableDAO = tSuperviseTableDAO;
	}

	/**
	 * @return tProcessTaskAssigneePersonDAO
	 */
	public ITProcessTaskAssigneePersonDAO gettProcessTaskAssigneePersonDAO() {
		return tProcessTaskAssigneePersonDAO;
	}

	/**
	 * @param tProcessTaskAssigneePersonDAO 要设置的 tProcessTaskAssigneePersonDAO
	 */
	public void settProcessTaskAssigneePersonDAO(
			ITProcessTaskAssigneePersonDAO tProcessTaskAssigneePersonDAO) {
		this.tProcessTaskAssigneePersonDAO = tProcessTaskAssigneePersonDAO;
	}

	/**
	 * @return tProcessTaskAssigneeDAO
	 */
	public ITProcessTaskAssigneeDAO gettProcessTaskAssigneeDAO() {
		return tProcessTaskAssigneeDAO;
	}

	/**
	 * @param tProcessTaskAssigneeDAO 要设置的 tProcessTaskAssigneeDAO
	 */
	public void settProcessTaskAssigneeDAO(
			ITProcessTaskAssigneeDAO tProcessTaskAssigneeDAO) {
		this.tProcessTaskAssigneeDAO = tProcessTaskAssigneeDAO;
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
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settCommonProcessDAO(ITCommonProcessDAO tCommonProcessDAO) throws Exception {
        this.tCommonProcessDAO = tCommonProcessDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITCommonProcessDAO gettCommonProcessDAO() throws Exception {
        return this.tCommonProcessDAO;
    }

    /**
	 * @return jbpmService
	 */
	public JbpmService getJbpmService() {
		return jbpmService;
	}

	/**
	 * @param jbpmService 要设置的 jbpmService
	 */
	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	/**
	 * @return fileResourceTableService
	 */
	public ITFileResourceTableService getFileResourceTableService() {
		return fileResourceTableService;
	}

	/**
	 * @param fileResourceTableService 要设置的 fileResourceTableService
	 */
	public void setFileResourceTableService(
			ITFileResourceTableService fileResourceTableService) {
		this.fileResourceTableService = fileResourceTableService;
	}

	/**
	 * @return tSuperviseTableService
	 */
	public ITSuperviseTableService gettSuperviseTableService() {
		return tSuperviseTableService;
	}

	/**
	 * @param tSuperviseTableService 要设置的 tSuperviseTableService
	 */
	public void settSuperviseTableService(
			ITSuperviseTableService tSuperviseTableService) {
		this.tSuperviseTableService = tSuperviseTableService;
	}

	/**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TCommonProcessExample example = new TCommonProcessExample();
        TCommonProcessExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TCommonProcess record = new TCommonProcess();
        List list = tCommonProcessDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TCommonProcess obj) throws Exception {
        this.tCommonProcessDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TCommonProcess obj) throws Exception {
        this.tCommonProcessDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TCommonProcess obj) throws Exception {
        java.lang.Long key = obj.getRecId();
        this.tCommonProcessDAO.deleteByPrimaryKey(key);
    }

    /**
     * 批量更新数据
     * @abatorgenerated
     */
    public void updateBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	TCommonProcess tObject = (TCommonProcess)abs.get(i);
            	this.update(tObject);
        }
    }

    /**
     * 批量插入数据
     * @abatorgenerated
     */
    public void insertBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	TCommonProcess tObject = (TCommonProcess)abs.get(i);
            	this.insert(tObject);
        }
    }

    /**
     * 批量删除数据
     * @abatorgenerated
     */
    public void deleteBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	TCommonProcess tObject = (TCommonProcess)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tCommonProcessDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tCommonProcessDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TCommonProcessExample example = new TCommonProcessExample();
        TCommonProcessExample.Criteria criteria = example.createCriteria();
        List list = tCommonProcessDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tCommonProcessDAO.selectByDynamic(map,page);
        return list;
    }
    
    /**
	 * 生成FileBean文件实体
	 * @param message
	 * @return
	 */
	public FileBean makeFileBean(TCommonProcess commonProcess){
		FileBean tfile= new FileBean();
		try{
			tfile.setResourceId(commonProcess.getRecId());
			tfile.setResourceType(commonProcess.getBussinessType());
			tfile.setNodeId(commonProcess.getNodeId());
			tfile.setNodeName(commonProcess.getNodeName());
		}catch (Exception e) {
			log.error("生成FileBean文件实体失败",e);
		}
		return tfile;
	}
	
	/**
     * 生成与流程相对应的业务信息
     * @param message
     * @return
     */
    public  TProcessBusiness insertProcessBusiness(TCommonProcess commonProcess,TaskAssgineeDto dto){
         TProcessBusiness processBusiness= new TProcessBusiness();
        try {
            processBusiness.setBusinessKey(commonProcess.getRecId());
            processBusiness.setBusinessType(dto.getBusinessType());
            processBusiness.setBusinessTitle(commonProcess.getComTitle());
            processBusiness.setExecutionId(commonProcess.getFlowId());
        } catch (Exception e) {
            log.error("生成实例标题信息", e);
        }
        return processBusiness;
    }
    
    /**
     * 生成意见
     * @param muo
     * @param taskId
     */
    public void insertApproveOpninion(TCommonProcess commonProcess,MUOUserSession muo,String taskId,String type,TaskAssgineeDto dto){
        try {
            if(commonProcess!=null&&commonProcess.getRecId()!=null&&!"".equals(commonProcess.getRecId())){
                if(commonProcess.getOpinion()!=null/*&&!"".equals(sup.getOpninion())*/){
                    TApproveOpninion opninion=new TApproveOpninion();
                    opninion.setResourceId(commonProcess.getRecId());
                    opninion.setOperator(muo.getEmpid());
                    opninion.setOrgid(String.valueOf(muo.getOrgid()));
                    opninion.setResourceType(dto.getBusinessType());
                    opninion.setOperaterDate(TimeUtil.today());
                    opninion.setOperaterTime(TimeUtil.now());
                    opninion.setOpninionContent(commonProcess.getOpinion());
                    opninion.setOperatorType(type);
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
                    //需要taskId
                    opninion.setNodeId(taskId);
                    opninion.setNodeName(commonProcess.getNodeName());
                    tApproveOpninionDAO.insert(opninion);
                }
            }
            
        } catch (Exception e) {
        //保存审核意见失败。
            log.error("保存审核意见失败。", e);
        }
    }
    
    /**
     * 生成任务实体。[jbpm]
     * @return
     */
    public TaskAssgineeDto makeTaskAssgineeDto(TCommonProcess commonProcess,MUOUserSession muo,TaskAssgineeDto dto){
        TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
        try {   
            taskAssgineeDto.setExecutionId(commonProcess.getFlowId());
            taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
            taskAssgineeDto.setPreTaskId(commonProcess.getNodeId());
            taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
            //if(sup.getCreateDate()!=null&&!"".equals(sup.getCreateDate())){
                //taskAssgineeDto.setPreTaskTime(sup.getCreateDate());
            //}else{
            String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
                taskAssgineeDto.setPreTaskTime(currDate);
            //}
            taskAssgineeDto.setEmpIds(dto.getEmpIds());
            taskAssgineeDto.setEmpNames(dto.getEmpNames());
            taskAssgineeDto.setNextTaskId(dto.getNextTaskId());
            taskAssgineeDto.setBusinessKey(commonProcess.getRecId());
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
     * 查询公用流程信息
     */
	@Override
	public TCommonProcess queryCommonProcess(String recId, String flowId)
			throws Exception {
		try {
			TCommonProcess commonProcess= new TCommonProcess();
			if(recId!=null&&!"".equals(recId))
				commonProcess.setRecId((Long.valueOf(recId)));
			if(flowId!=null&&!"".equals(flowId))
				commonProcess.setFlowId(flowId);
			return tCommonProcessDAO.queryCommonProcess(commonProcess);
		} catch (Exception e) {
			log.error("[获取公共类错误]", e);
			throw e;
		}
	}

	/**
	 * 插入或更新公用流程信息
	 */
	@Override
	public void insertCommonProcess(TCommonProcess commonProcess,
			BusinessDto businessDto, MUOUserSession muo,
			TaskAssgineeDto taskAssgineeDto) throws Exception {
		TaskAssgineeDto dto1=null;
		String taskId=taskAssgineeDto.getNextTaskId();
		if(commonProcess.getRecId()!=null && !"".equals(commonProcess.getRecId())){//更新
			tCommonProcessDAO.updateByPrimaryKeySelective(commonProcess);
			TProcessBusiness business = jbpmService.findProcessBusiness(taskAssgineeDto);
            business.setBusinessTitle(commonProcess.getComTitle());
             jbpmService.updateProcessBusiness(business);
		}else{//新增
			HashMap<String, Object> map= new HashMap<String, Object>();
            map.put("user",String.valueOf(muo.getEmpid()));
            dto1=jbpmService.startProcessByDefinition(taskAssgineeDto.getDefinitionId(),map);
            taskId=dto1.getNextTaskId();
            taskAssgineeDto.setExecutionId(dto1.getExecutionId());
            commonProcess.setFlowId(dto1.getExecutionId());
            taskAssgineeDto.setExecutionId(dto1.getExecutionId());
			tCommonProcessDAO.insert(commonProcess);
            //保存流程的信息
            jbpmService.saveProcessBusiness(muo,insertProcessBusiness(commonProcess,taskAssgineeDto));
		}
		if(businessDto.getUpload()!=null){
			fileResourceTableService.fileUploads(makeFileBean(commonProcess), businessDto.getUpload(), businessDto.getUploadFileName(), muo);
		}
        taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
        taskAssgineeDto.setTaskId(taskId);
        jbpmService.assignTask(taskAssgineeDto);//分配节点办理人
		if(!"1".equals(businessDto.getSubmitReason())){
			commonProcess.setNodeName(jbpmService.getTaskById(taskId).getName());
            taskAssgineeDto.setTaskAssigneeState("0");
            jbpmService.updateTaskAssigneeState(taskAssgineeDto);//更新节点状态
            commonProcess.setNodeId(taskId);
            commonProcess.setNodeName(jbpmService.getTaskNameById(taskId));
            if(commonProcess.getOpinion()==null || "null".equals(commonProcess.getOpinion()))
            	commonProcess.setOpinion("");
            insertApproveOpninion(commonProcess, muo, taskId,"05",taskAssgineeDto);
            jbpmService.completeTask(taskId, taskAssgineeDto.getTransitionName(), null);
            String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId()); 
            taskAssgineeDto.setNextTaskId(nextTaskId);
            commonProcess.setNodeId(taskId);
            jbpmService.saceTaskAssignee(makeTaskAssgineeDto(commonProcess, muo,taskAssgineeDto));
		}
		
	}

	/**
	 * 公用流程审核
	 */
	@Override
	public void insertApprove(TCommonProcess commonProcess, BusinessDto businessDto,
			TaskAssgineeDto taskAssgineeDto, MUOUserSession muo)throws Exception {
		commonProcess.setNodeId(taskAssgineeDto.getPreTaskId());
		commonProcess.setNodeName(jbpmService.getTaskNameById(taskAssgineeDto.getPreTaskId()));
		commonProcess.setBussinessType(taskAssgineeDto.getBusinessType());
		List<TProcessTaskAssigneePerson> task=jbpmService.getUnCompleted(taskAssgineeDto);
		jbpmService.assignTask(taskAssgineeDto);
		jbpmService.updateTaskAssigneeState(taskAssgineeDto);
		if(businessDto.getUpload()!=null){
			fileResourceTableService.fileUploads(makeFileBean(commonProcess), businessDto.getUpload(), businessDto.getUploadFileName(), muo);
		}
		if("09".equals(taskAssgineeDto.getTaskConfigType())){//部室会签执行的操作
	        if(task!=null&&task.size()<=1){//用于所有人都操作完成后进入下一个节点
	             jbpmService.completeTask(taskAssgineeDto.getPreTaskId(), taskAssgineeDto.getTransitionName(), null);
	             String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
	             taskAssgineeDto.setNextTaskId(nextTaskId);
	             taskAssgineeDto.setTaskAssigneeState(String.valueOf(muo.getEmpid()));
	             commonProcess.setNodeId(taskAssgineeDto.getPreTaskId());
	             jbpmService.saceTaskAssignee(taskAssgineeDto);
	         }else{
	             //删除当前节点办理人员,表示已经办理过
	             TProcessTaskAssigneePerson tpap=new TProcessTaskAssigneePerson();
	             tpap.setTaskAssigneeId(muo.getEmpid());
	             tpap.setExecutionId(taskAssgineeDto.getExecutionId());
	             this.tProcessTaskAssigneePersonDAO.deleteByTemplate(tpap);
	         }
	        //保存提交意见
	        insertApproveOpninion(commonProcess, muo, taskAssgineeDto.getPreTaskId(),"05",taskAssgineeDto);
	        return;
		}
		jbpmService.completeTask(taskAssgineeDto.getPreTaskId(), taskAssgineeDto.getTransitionName(), null);
		String submitType = "05";
		String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
		taskAssgineeDto.setNextTaskId(nextTaskId);
		if("".equals(nextTaskId)){
			submitType = "08";
		}
        insertApproveOpninion(commonProcess, muo, taskAssgineeDto.getPreTaskId(),submitType,taskAssgineeDto);
		jbpmService.saceTaskAssignee(taskAssgineeDto);
	}

	/**
	 * 获取不是部门领导的所有人
	 */
	@Override
	public List<TCommonProcess> queryTaskAssignPerson(String positionCode,
			MUOUserSession muo) throws Exception {
		HashMap< String, Object> hmp=new HashMap<String, Object>();
        hmp.put("positionCode", positionCode);
        hmp.put("orgid", muo.getOrgid());
        hmp.put("empid", muo.getEmpid());
        return tCommonProcessDAO.queryTaskAssignPerson(hmp);
	}

	@Override
	public String insertTaskAssignePerson(TaskAssgineeDto dto,
			MUOUserSession muo, TCommonProcess commonProcess) throws Exception {
		 //插入意见到意见表
        String type="09";
        commonProcess.setNodeName(jbpmService.getTaskById(dto.getTaskId()).getName());
        if(commonProcess.getOpinion()==null||"".equals(commonProcess.getOpinion()))
        commonProcess.setOpinion("");
        insertApproveOpninion(commonProcess, muo, dto.getTaskId(), type, dto);
        String preTaskId=dto.getTaskId();
        //插入下一个节点信息
        tSuperviseTableService.insertTask(dto,muo);
        //更新当前执行人信息
        dto.setTaskAssigneeState("0");
        TProcessTaskAssignee tptagee=new TProcessTaskAssignee();
        tptagee.setId(dto.getProcessTaskAssigneeId());
        tptagee.setExecutionId(dto.getExecutionId());
        tptagee.setPreTaskId(preTaskId);
        tptagee.setPreTaskAssingee(muo.getEmpid().toString());
        tptagee.setPreTaskOrg(muo.getOrgid());
        tptagee.setPreTaskTime(TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss"));
        tptagee.setNextTaskId(dto.getTaskId());
        tptagee.setBusinessKey(commonProcess.getRecId());
        this.gettProcessTaskAssigneeDAO().updateByPrimaryKeySelective(tptagee);
        //插入下一步执行人员,同时更新原来办理人员的信息，由于nextTaskId改变了       
        jbpmService.insertTaskAssignPerson(dto);
        TProcessTaskAssigneePerson record=new TProcessTaskAssigneePerson();
        TProcessTaskAssigneePerson example=new TProcessTaskAssigneePerson();
        record.setExecutionId(dto.getExecutionId());
        record.setTaskId(dto.getTaskId());
        example.setExecutionId(dto.getExecutionId());
        example.setProcessTaskAssigneeId(dto.getProcessTaskAssigneeId());
        this.gettProcessTaskAssigneePersonDAO().updateEntityByTemplate(record, example);
        //删除当前已办人员
        TProcessTaskAssigneePerson tad=new TProcessTaskAssigneePerson();
        tad.setTaskAssigneeId(muo.getEmpid());
        tad.setExecutionId(dto.getExecutionId());
        this.tProcessTaskAssigneePersonDAO.deleteByTemplate(tad);
        return "success";
	}

	@Override
	/*
     * (non-Javadoc)
     * 部门人员办理反馈信息
     *1.填写流程列表
     *2.删除当前办理信息
     *3.判断部门所有人员是否办理完成
     *4.如果部门人员全部完成，则更新节点信息，并将部门主要负责人写入待办
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-9-10    黄开忠    新建
     * </pre>
     */
        public String updateBuShiAssignStatus(TaskAssgineeDto dto,
                MUOUserSession muo, TCommonProcess commonProcess) throws Exception {
            if(dto.getExecutionId()==null||"".equals(dto.getExecutionId()))
                return "无法获取该实例ID!";
            String type="10";
            commonProcess.setNodeName(jbpmService.getTaskById(dto.getTaskId()).getName());
            if(commonProcess.getOpinion()==null||"".equals(commonProcess.getOpinion()))
            	commonProcess.setOpinion("");

        
    
            
            //删除当前人员办理信息
             //删除当前已办人员
            TProcessTaskAssigneePerson tpap=new TProcessTaskAssigneePerson();
            tpap.setTaskAssigneeId(muo.getEmpid());
            tpap.setExecutionId(dto.getExecutionId());
            this.tProcessTaskAssigneePersonDAO.deleteByTemplate(tpap);

            String preTaskId=dto.getTaskId();
            //插入下一个节点信息
            tSuperviseTableService.insertTask(dto,muo);
            //更新当前执行人信息
            
            TProcessTaskAssignee tptagee=new TProcessTaskAssignee();
            tptagee.setId(dto.getProcessTaskAssigneeId());
            tptagee.setExecutionId(dto.getExecutionId());
            tptagee.setPreTaskId(preTaskId);
            tptagee.setPreTaskAssingee(muo.getEmpid().toString());
            tptagee.setPreTaskOrg(muo.getOrgid());
            tptagee.setPreTaskTime(TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss"));
            tptagee.setNextTaskId(dto.getTaskId());
            tptagee.setBusinessKey(commonProcess.getRecId());
             
            //插入下一步执行人员      
            this.gettProcessTaskAssigneeDAO().updateByPrimaryKeySelective(tptagee);
            TProcessTaskAssigneePerson record=new TProcessTaskAssigneePerson();
            TProcessTaskAssigneePerson example=new TProcessTaskAssigneePerson();
            record.setExecutionId(dto.getExecutionId());
            record.setTaskId(dto.getTaskId());
            example.setExecutionId(dto.getExecutionId());
            example.setProcessTaskAssigneeId(dto.getProcessTaskAssigneeId());
            this.gettProcessTaskAssigneePersonDAO().updateEntityByTemplate(record, example);
            dto.setTaskAssigneeState("0");
              //判断内部办理是否是所有人都已经办理
            List<TProcessTaskAssigneePerson> task=jbpmService.getBsUnCompleted(dto);
            if(task.size()<1){
                //获取下个节点人id,姓名
                HashMap<String, Object> hmp= new HashMap<String, Object>();
                hmp.put("empid", muo.getEmpid());
                //获取部门主要负责人信息
                TSuperviseTable sp=tSuperviseTableDAO.queryTaskAssignZyPerson(hmp);
                if(sp!=null&&sp.getEmpid()!=null&&!"".equals(sp.getEmpid()))
                    dto.setEmpIds(sp.getEmpid());
                if(sp!=null&&sp.getEmpname()!=null&&!"".equals(sp.getEmpname()))
                    dto.setEmpNames(sp.getEmpname());
                dto.setIsChild("0");//用来判断是否负责人，显示对应按钮信息
                dto.setTaskAssigneeState("0");//部室提交下一步使用，如果是多个为0，则流程不走下一个节点。
                if(!(dto.getEmpIds()==null||dto.getEmpIds().equals(""))){
                 jbpmService.insertTaskAssignPerson(dto);
                 }
            }
             //插入办理意见
            insertApproveOpninion(commonProcess, muo, dto.getTaskId(), type, dto);
             return "success";
        }
}