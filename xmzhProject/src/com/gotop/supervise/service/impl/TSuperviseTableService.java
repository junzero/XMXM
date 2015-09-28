package com.gotop.supervise.service.impl;
import com.gotop.file.model.FileBean;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dao.IJbpm4ExecutionDAO;
import com.gotop.jbpm.dao.IJbpm4HistActinstDAO;
import com.gotop.jbpm.dao.IJbpm4HistTaskDAO;
import com.gotop.jbpm.dao.IJbpm4TaskDAO;
import com.gotop.jbpm.dao.ITProcessTaskAssigneeDAO;
import com.gotop.jbpm.dao.ITProcessTaskAssigneePersonDAO;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.Jbpm4Execution;
import com.gotop.jbpm.model.Jbpm4HistActinst;
import com.gotop.jbpm.model.Jbpm4HistTask;
import com.gotop.jbpm.model.Jbpm4Task;
import com.gotop.jbpm.model.Jbpm4TaskExample;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.model.TProcessTaskAssignee;
import com.gotop.jbpm.model.TProcessTaskAssigneePerson;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.messagePublish.dao.impl.TMessagePublishDAO;
import com.gotop.supervise.dao.ITSuperviseTableDAO;
import com.gotop.supervise.model.TSuperviseTable;
import com.gotop.supervise.service.ITSuperviseTableService;
import com.gotop.superviseReceive.dao.ITSuperviceReceiveDAO;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.jbpm.api.ProcessEngine;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.id.DbidGenerator;
import org.jbpm.pvm.internal.id.PropertyImpl;

public class TSuperviseTableService implements ITSuperviseTableService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TSuperviseTableService.class);
    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITProcessTaskAssigneePersonDAO tProcessTaskAssigneePersonDAO;
    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITSuperviseTableDAO tSuperviseTableDAO;
    private TMessagePublishDAO tMessagePublishDAO;
    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected JbpmService jbpmService;
    private ProcessEngine processEngine;
    public ITProcessTaskAssigneePersonDAO gettProcessTaskAssigneePersonDAO() {
        return tProcessTaskAssigneePersonDAO;
    }

    public void settProcessTaskAssigneePersonDAO(
            ITProcessTaskAssigneePersonDAO tProcessTaskAssigneePersonDAO) {
        this.tProcessTaskAssigneePersonDAO = tProcessTaskAssigneePersonDAO;
    }
    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITApproveOpninionDAO tApproveOpninionDAO;
    
    protected ITSuperviceReceiveDAO tSuperviceReceiveDAO;
    protected IJbpm4HistActinstDAO jbpm4HistActinstDAO;
    protected IJbpm4HistTaskDAO jbpm4HistTaskDAO;
    protected IJbpm4ExecutionDAO jbpm4ExecutionDAO;
    protected IJbpm4TaskDAO jbpm4TaskDAO;
    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITProcessTaskAssigneeDAO tProcessTaskAssigneeDAO;
    
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settProcessTaskAssigneeDAO(ITProcessTaskAssigneeDAO tProcessTaskAssigneeDAO) throws Exception {
        this.tProcessTaskAssigneeDAO = tProcessTaskAssigneeDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITProcessTaskAssigneeDAO gettProcessTaskAssigneeDAO() throws Exception {
        return this.tProcessTaskAssigneeDAO;
    }
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setJbpm4TaskDAO(IJbpm4TaskDAO jbpm4TaskDAO) throws Exception {
        this.jbpm4TaskDAO = jbpm4TaskDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IJbpm4TaskDAO getJbpm4TaskDAO() throws Exception {
        return this.jbpm4TaskDAO;
    }
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setJbpm4HistTaskDAO(IJbpm4HistTaskDAO jbpm4HistTaskDAO) throws Exception {
        this.jbpm4HistTaskDAO = jbpm4HistTaskDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IJbpm4HistTaskDAO getJbpm4HistTaskDAO() throws Exception {
        return this.jbpm4HistTaskDAO;
    }

  

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setJbpm4ExecutionDAO(IJbpm4ExecutionDAO jbpm4ExecutionDAO) throws Exception {
        this.jbpm4ExecutionDAO = jbpm4ExecutionDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IJbpm4ExecutionDAO getJbpm4ExecutionDAO() throws Exception {
        return this.jbpm4ExecutionDAO;
    }

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setJbpm4HistActinstDAO(IJbpm4HistActinstDAO jbpm4HistActinstDAO) throws Exception {
        this.jbpm4HistActinstDAO = jbpm4HistActinstDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IJbpm4HistActinstDAO getJbpm4HistActinstDAO() throws Exception {
        return this.jbpm4HistActinstDAO;
    }
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITFileResourceTableService tFileResourceTableService;

    /**
     * @return the processEngine
     */
    public ProcessEngine getProcessEngine() {
        return processEngine;
    }

    /**
     * @param processEngine the processEngine to set
     */
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    /**
     * 督办处理2
     */
    @Override
    public TaskAssgineeDto insertSuperviseTable2(TSuperviseTable supervise,
            File[] files, String[] filesFileName, MUOUserSession muo,
            String btnType, TaskAssgineeDto taskAssgineeDto, String isFirst) throws Exception {
        String taskId=taskAssgineeDto.getNextTaskId();
        //if(taskAssgineeDto.getTransitionName()!=null&&!"".equals(taskAssgineeDto.getTransitionName()))
            //taskAssgineeDto.setTransitionName(new String(taskAssgineeDto.getTransitionName().getBytes("iso-8859-1"),"UTF-8"));
        //生成督办信息
        String submitType = "";
        if(supervise.getSuperviseId()==null||"".equals(supervise.getSuperviseId())){
            supervise.setOrgid(String.valueOf(muo.getOrgid()));
            supervise.setCreateor(String.valueOf(muo.getEmpid()));
            String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
            supervise.setCreateDate(currDate.substring(0, 8));
            supervise.setCreateTime(currDate.substring(8));
            HashMap<String, Object> map= new HashMap<String, Object>();
            map.put("user",String.valueOf(muo.getEmpid()));
            TaskAssgineeDto dto1=jbpmService.startProcessByDefinition(taskAssgineeDto.getDefinitionId(),map);
            taskId=dto1.getNextTaskId();
            supervise.setNodeId(taskId);
            supervise.setNodeName1(jbpmService.getTaskById(taskId).getName());
            supervise.setFlowId(dto1.getExecutionId());
            taskAssgineeDto.setExecutionId(dto1.getExecutionId());
            tSuperviseTableDAO.insertSuperviseTable(supervise);
            //保存流程的信息
            jbpmService.saveProcessBusiness(muo,insertProcessBusiness(supervise,taskAssgineeDto));
            supervise.setOpninion("");
            submitType = "05";
        }else{
            //修改
            tSuperviseTableDAO.updateSuperviseTable(supervise);
            //20140902修改督办单更新时出现一倍数据的问题
            TProcessBusiness business = jbpmService.findProcessBusiness(taskAssgineeDto);
            business.setBusinessTitle(supervise.getSuperviseItem());
            
             jbpmService.updateProcessBusiness(business);
             submitType = "05";
             /**
              * 2014093
              */
             if(supervise.getOpninion()==null || "null".equals(supervise.getOpninion()))
                 supervise.setOpninion("");
            /* jbpmService.updateProcessBusiness(insertProcessBusiness(supervise,taskAssgineeDto));*/
        }
        tFileResourceTableService.fileUploads(makeFileBean(supervise,taskAssgineeDto), files, filesFileName, muo);//保存文件
        if(!"1".equals(btnType)){
            supervise.setNodeName1(jbpmService.getTaskById(taskId).getName());
            taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
            taskAssgineeDto.setTaskId(taskId);
            jbpmService.assignTask(taskAssgineeDto);
            if(!"退回".equals(taskAssgineeDto.getTransitionName()) && !"退回办理".equals(taskAssgineeDto.getTransitionName())){//进入下一步
                taskAssgineeDto.setTaskAssigneeState("0");
                //taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
                jbpmService.updateTaskAssigneeState(taskAssgineeDto);
                //20140903 解决下个操作人为null的问题
                
                if("null".equals(taskAssgineeDto.getEmpNames()))
                    taskAssgineeDto.setEmpNames("");
                if(isFirst==null && taskAssgineeDto.getEmpIds()!=null && !"".equals(taskAssgineeDto.getEmpIds()))
                    submitType = "01";
                /*if("处理".equals(taskAssgineeDto.getTransitionName())){//
                    //暂无
                    
                }else if("批示".equals(taskAssgineeDto.getTransitionName())){
                    //String[] receiveEmp=taskAssgineeDto.getEmpIds().split(",");
                    //for(int i=0;i<receiveEmp.length;i++){
                //  TSuperviceReceive record=new TSuperviceReceive();
                    //record.setNodeId(taskId);
                    //record.setNodeName(jbpmService.getTaskById(taskId).getName());
                    //record.setContent(supervise.getContent());
                    //record.setSuperviseId(supervise.getSuperviseId());
                    //record.setReceiveEmp(receiveEmp[i]);
                    //record.setReceiveDate("");
                    //record.setReceiveTime("");
                    //生成督办的人。
                    //tSuperviceReceiveDAO.insert(record);
                //  }
                }else*/
                if("反馈".equals(taskAssgineeDto.getTransitionName())){
                    submitType = "07";
                    List<TProcessTaskAssigneePerson> task=jbpmService.getUnCompleted(taskAssgineeDto);
/*                  taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
                    jbpmService.updateTaskAssigneeState(taskAssgineeDto);
                    TaskAssgineeDto tad=new TaskAssgineeDto();
                    tad.setParentId(taskAssgineeDto.getParentId());
                    tad.setExecutionId(taskAssgineeDto.getExecutionId());
                    tad.setIsUpdate("10");//10:子处理节点未完成的
                    tad.setTaskAssigneeState("3");//3:作废
                    jbpmService.updateTaskAssigneeState1(tad);//将部室下子处理节点未签的前辈作废
*/                  if(task!=null&&task.size()<=1){//用于所有人都操作完成后进入下一个节点
                        jbpmService.completeTask(taskId, taskAssgineeDto.getTransitionName(), null);
                        String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
                        taskAssgineeDto.setNextTaskId(nextTaskId);
                        supervise.setNodeId(taskId);
                        TaskAssgineeDto dto = makeTaskAssgineeDto(supervise, muo,taskAssgineeDto);
                        jbpmService.saceTaskAssignee(dto);
                    }else{
                        //删除当前节点办理人员,表示已经办理过
                        TProcessTaskAssigneePerson tpap=new TProcessTaskAssigneePerson();
                        tpap.setTaskAssigneeId(muo.getEmpid());
                        tpap.setExecutionId(taskAssgineeDto.getExecutionId());
                        this.tProcessTaskAssigneePersonDAO.deleteByTemplate(tpap);
                    }

                }else if("结束".equals(taskAssgineeDto.getTransitionName())){
                    supervise.setNodeId(taskId);
                    TaskAssgineeDto taskAssginee = makeTaskAssgineeDto(supervise, muo,taskAssgineeDto);
                    taskAssginee.setNextTaskId("");
                    jbpmService.saceTaskAssignee(taskAssginee);
                    jbpmService.completeTask(taskId, taskAssgineeDto.getTransitionName(), null);
                    //20140904 添加操作类型为结束
                    submitType = "08";
                }else{//进入下一步
                	if("批示".equals(taskAssgineeDto.getTransitionName()))
                    	submitType="05";
                    jbpmService.completeTask(taskId, taskAssgineeDto.getTransitionName(), null);
                    String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId()); 
                    taskAssgineeDto.setNextTaskId(nextTaskId);
                    supervise.setNodeId(taskId);
                    jbpmService.saceTaskAssignee(makeTaskAssgineeDto(supervise, muo,taskAssgineeDto));
                }

                insertApproveOpninion(supervise, muo, taskId,submitType,taskAssgineeDto);
            }else{//退回
                insertApproveOpninion(supervise, muo, taskId,"02",taskAssgineeDto);
                jbpmService.completeTask(taskId, taskAssgineeDto.getTransitionName(), null);
                jbpmService.turnBackTaskAssignee(makeTaskAssgineeDtoBack(taskAssgineeDto, supervise, muo));
            }
        }
        return null;
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
  
 
    /**
     * 督办单处理
     */
    @Override
    public TaskAssgineeDto insertSuperviseTable(TSuperviseTable supervise, File[] files,
            String[] filesFileName, MUOUserSession muo, String btnType,
            TaskAssgineeDto taskAssgineeDto) throws Exception {
        String taskId=taskAssgineeDto.getNextTaskId();
       return null;
    }

    /**
     * 查询督办信息
     */
    @Override
    public TSuperviseTable querySuperviseTable(String superviseId,String flowId)
            throws Exception {
        TSuperviseTable sup=new TSuperviseTable();
        if(superviseId!=null&&!"".equals(superviseId))
        sup.setSuperviseId(Long.valueOf(superviseId));
        if(flowId!=null&&!"".equals(flowId))
        sup.setFlowId(flowId);
        return tSuperviseTableDAO.querySuperviseTable(sup);
    }
    /**
     * 生成FileBean文件实体
     * @param message
     * @return
     */
    public FileBean makeFileBean(TSuperviseTable t,TaskAssgineeDto dto){
        FileBean tfile= new FileBean();
        try{
            tfile.setResourceId(t.getSuperviseId());
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
    public TaskAssgineeDto makeTaskAssgineeDto(TSuperviseTable sup,MUOUserSession muo,TaskAssgineeDto dto){
        TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
        try {   
            taskAssgineeDto.setExecutionId(sup.getFlowId());
            taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
            taskAssgineeDto.setPreTaskId(sup.getNodeId());
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
            taskAssgineeDto.setBusinessKey(sup.getSuperviseId());
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
    public void insertApproveOpninion(TSuperviseTable sup,MUOUserSession muo,String taskId,String type,TaskAssgineeDto dto){
        try {
            if(sup!=null&&sup.getSuperviseId()!=null&&!"".equals(sup.getSuperviseId())){
                if(sup.getOpninion()!=null/*&&!"".equals(sup.getOpninion())*/){
                    TApproveOpninion opninion=new TApproveOpninion();
                    opninion.setResourceId(sup.getSuperviseId());
                    opninion.setOperator(muo.getEmpid());
                    opninion.setOrgid(String.valueOf(muo.getOrgid()));
                    opninion.setResourceType(dto.getBusinessType());
                    opninion.setOperaterDate(TimeUtil.today());
                    opninion.setOperaterTime(TimeUtil.now());
                    opninion.setOpninionContent(sup.getOpninion());
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
                    opninion.setNodeName(sup.getNodeName1());
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
    public TaskAssgineeDto makeTaskAssgineeDtoBack(TaskAssgineeDto dto,TSuperviseTable sup,MUOUserSession muo){
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
        dto2.setBusinessKey(sup.getSuperviseId());
        // if(dto.getTaskAssingee()!=null&&!"".equals(dto.getTaskAssingee()))
//        dto2.setTaskAssingee(dto.getTaskAssingee());
        dto2.setTaskAssingee(Long.valueOf(dto.getEmpIds()));
        // else {
            //dto2.setTaskAssingee(Long.valueOf(dto.getEmpIds()));
        //}
        dto2.setBusinessType(dto.getBusinessType());
        dto2.setTargetName(dto.getTargetName());
        //存储节点配置对象主键
        dto2.setTaskExeConfigId(dto.getTaskExeConfigId());
        return dto2;
    }
    
       /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settSuperviseTableDAO(ITSuperviseTableDAO tSuperviseTableDAO) throws Exception {
        this.tSuperviseTableDAO = tSuperviseTableDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITSuperviseTableDAO gettSuperviseTableDAO() throws Exception {
        return this.tSuperviseTableDAO;
    }
    
    

    public TMessagePublishDAO gettMessagePublishDAO() {
        return tMessagePublishDAO;
    }

    public void settMessagePublishDAO(TMessagePublishDAO tMessagePublishDAO) {
        this.tMessagePublishDAO = tMessagePublishDAO;
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


    public ITSuperviceReceiveDAO gettSuperviceReceiveDAO() {
        return tSuperviceReceiveDAO;
    }


    public void settSuperviceReceiveDAO(ITSuperviceReceiveDAO tSuperviceReceiveDAO) {
        this.tSuperviceReceiveDAO = tSuperviceReceiveDAO;
    }


    public ITFileResourceTableService gettFileResourceTableService() {
        return tFileResourceTableService;
    }


    public void settFileResourceTableService(
            ITFileResourceTableService tFileResourceTableService) {
        this.tFileResourceTableService = tFileResourceTableService;
    }


    /**
     * 查询督办信息列表
     */
    @Override
    public List<TSuperviseTable> querySuperviseTabls(TSuperviseTable siup,
            MUOUserSession muo, Page page) throws Exception {
        if(siup==null){
            siup= new TSuperviseTable();
        }
        siup.setOracleStart(page.getBegin());
        siup.setOracleEnd(page.getBegin()+page.getLength());
        siup.setEmpid(String.valueOf(muo.getEmpid()));
        return tSuperviseTableDAO.querySuperviseTables(siup, page);
    }


    /**
     * 查询待阅读的信息
     */
    @Override
    public TSuperviseTable querySuperviseRecive(TSuperviseTable sup)
            throws Exception {
        if(sup!=null){//未阅状态
            if("0".equals(sup.getStatus())||"".equals(sup.getStatus()))
            tSuperviseTableDAO.updateSuperviseStatus(sup);
        }
        return tSuperviseTableDAO.querySuperviseTable1(sup);
    }

/*  *//**
     * 部室内部会签
     *//*
    @Override
    public String insertTaskAssignePerson(TaskAssgineeDto dto,MUOUserSession muo,TSuperviseTable sup) throws Exception {
        //插入意见到意见表
        String type="09";
        sup.setNodeName1(jbpmService.getTaskById(dto.getTaskId()).getName());
        sup.setOpninion("");
        insertApproveOpninion(sup, muo, dto.getTaskId(), type, dto);
        return  jbpmService.insertTaskAssignPerson(dto);    
    }*/
    
    //20140907 修改
    /**
     * 
     *
     * 操作流程相关表，自动增加节点信息
     *
     * @param dto
     * @param muo
     * @throws Exception
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-9-10    黄开忠    新建
     * </pre>
     */
    public void insertTask(TaskAssgineeDto dto,MUOUserSession muo) throws Exception{
        /**
         * 黄开忠增加，20140910，处理部室办理时，将办理人员的信息加到节点流水表中。，该部分可以写成公共部分，
         */
        //移除当前流程的部署
       //刷新流程部署缓存
        /**手工插入流程步骤
         * 1.根据EXECUTION_ID_从表JBPM4_EXECUTION得到dbid_;
         * 2.根据得到dbid_更新表jbpm4_hist_actinst的值
         * 3.根据taskid更新表JBPM4_HIST_TASK的值
         * 4.更新表JBPM4_TASK的dbid_
         * 5.插入表JBPM4_HIST_ACTINST
         * 6.插入表JBPM4_HIST_TASK的值
         * 7.更新表JBPM4_EXECUTION数据
         * 1.SELECT n.hisactinst_ from JBPM4_EXECUTION n WHERE n.id_='superviseTable.1110030';
         * 2.SELECT t.htask_ FROM jbpm4_hist_actinst t WHERE t.dbid_=1110035;
         * 3.SELECT * FROM jbpm4_hist_task k WHERE k.dbid_=1110034;
         * 4.SELECT * FROM JBPM4_TASK sk WHERE sk.dbid_=1110034;
         * 5.UPDATE JBPM4_TASK;
         * 6.INSERT JBPM4_HIST_ACTINST;
         * 7.INSERT JBPM4_HIST_TASK;
         * 8.UPDATE JBPM4_EXECUTION; 
         */
        //JBPM4_TASK的当前dbid_,做更新jbpm4_hist_task表使用
        String dbid_0=dto.getTaskId();
        Jbpm4Execution example=new Jbpm4Execution();
        String executionId=dto.getExecutionId();
        example.setId(executionId);
        BigDecimal dbid_init=new BigDecimal(executionId.substring(executionId.indexOf(".")+1));
        BigDecimal dbid_hist_task=new BigDecimal(this.getNextDbid());       
        BigDecimal hact_dbid=this.getJbpm4ExecutionDAO().expandEntityByTemplate(example).getHisactinst();
        //更新表jbpm4_hist_actinst的状态
        Jbpm4HistActinst jhact=new Jbpm4HistActinst();
        jhact.setDbid(hact_dbid);
        jhact.setNextidx((long)1);
        jhact.setEnd(new Date());
        jhact.setDbversion((long)1);
        jhact.setTransition("部室办理");
        jhact.setDuration(new BigDecimal(20));
        this.jbpm4HistActinstDAO.updateByPrimaryKeySelective(jhact);
        
        //更新表JBPM4_HIST_TASK
        Jbpm4HistTask jhtask=new Jbpm4HistTask();
        jhtask.setDbid(new BigDecimal(dbid_0));
        jhtask.setOutcome("部室办理");
        jhtask.setAssignee(muo.getEmpid().toString());
        jhtask.setState("completed");
        jhtask.setEnd(new Date());
        jhtask.setDbversion((long)1);
        jhtask.setDuration(new BigDecimal(20));
        this.jbpm4HistTaskDAO.updateByPrimaryKeySelective(jhtask);
        
        //更新JBPM4_TASK信息
        Jbpm4Task task=new Jbpm4Task();
        Jbpm4Task ex=new Jbpm4Task();
        //BigDecimal dbid_task=new BigDecimal(this.getNextDbid());
        task.setDbid(dbid_hist_task);
        task.setclassType("T");
        ex.setclassType("T");
        ex.setDbid(new BigDecimal(dbid_0));
        this.jbpm4TaskDAO.updateEntityByTemplate(task, ex);
     //插入表JBPM4_HIST_TASK
        
        jhtask.setDbid(dbid_hist_task);
        jhtask.setDbversion((long)0);
        jhtask.setExecution(executionId);
        jhtask.setOutcome("");
        jhtask.setAssignee("");
        jhtask.setPriority((long)0);
        jhtask.setState("");
        jhtask.setCreate(new Date());
        jhtask.setDbversion((long)0);
        jhtask.setNextidx((long)1);

        this.jbpm4HistTaskDAO.insert(jhtask);
        //插入表JBPM4_HIST_ACTINST
        BigDecimal dbid_hist_act=new BigDecimal(this.getNextDbid());

        jhact.setDbid(dbid_hist_act);
        jhact.setDbversion((long)0);
        jhact.setTransition("办理部室");
        jhact.setClassType("task");
        jhact.setHproci(dbid_init);
        jhact.setStart(new Date());
        //jhact.setActivityName("办理部室");
        //节点名称不固定
        String a = jbpmService.getTaskNameById(dto.getTaskId());
        jhact.setActivityName(a);
        jhact.setDbversion((long)0);
        jhact.setNextidx((long)1);
        jhact.setHtask(dbid_hist_task);
        jhact.setType("task");
        jhact.setExecution(executionId);
        jhact.setDuration(new BigDecimal(20));//默认写一个值，没什么用，如果为空时，用流程本身函数下不去
        this.jbpm4HistActinstDAO.insert(jhact);
   
        
        //更新表JBPM4_EXECUTION
        Jbpm4Execution jexec=new Jbpm4Execution();
        jexec.setDbid(dbid_init);
        jexec.setDbversion((long)2);
        jexec.setHisactinst(dbid_hist_act);        
        this.jbpm4ExecutionDAO.updateByPrimaryKeySelective(jexec);
        dto.setTaskId(dbid_hist_task.toString());
        dto.setNextTaskId(dbid_hist_act.toString());
        
    }
    /**
     * 部室内部会签
     */
    @Override
    public String insertTaskAssignePerson(TaskAssgineeDto dto,MUOUserSession muo,TSuperviseTable sup) throws Exception {
        //插入意见到意见表
        String type="09";
        sup.setNodeName1(jbpmService.getTaskById(dto.getTaskId()).getName());
        if(sup.getOpninion()==null||"".equals(sup.getOpninion()))
        sup.setOpninion("");
        insertApproveOpninion(sup, muo, dto.getTaskId(), type, dto);
        String preTaskId=dto.getTaskId();
        //插入下一个节点信息
        insertTask(dto,muo);
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
        tptagee.setBusinessKey(sup.getSuperviseId());
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

   private Long getNextDbid(){
       EnvironmentImpl envImpl = ((EnvironmentFactory) processEngine).openEnvironment();
       Long nextDbid=(long)0;
       try {  
           Session session = EnvironmentImpl.getFromCurrent(Session.class);
           nextDbid = EnvironmentImpl.getFromCurrent(DbidGenerator.class).getNextId();
          } catch (Exception e) {  
              e.printStackTrace();  
          } finally {  
              envImpl.close();  
          }  
       return nextDbid;
   }
    @Override
    public List<TSuperviseTable> queryTaskAssignPerson(String possionId,
            MUOUserSession muo) throws Exception {
        HashMap< String, Object> hmp=new HashMap<String, Object>();
        hmp.put("positionid", possionId);
        hmp.put("orgid", muo.getOrgid());
        hmp.put("empid", muo.getEmpid());
        return tSuperviseTableDAO.queryTaskAssignPerson(hmp);
    }

    /*@Override
    public String updateBuShiAssignStatus(TaskAssgineeDto dto,
            MUOUserSession muo, TSuperviseTable sup) throws Exception {
        String type="10";
        sup.setNodeName1(jbpmService.getTaskById(dto.getTaskId()).getName());
        sup.setOpninion("");
        insertApproveOpninion(sup, muo, dto.getTaskId(), type, dto);
        dto.setTaskAssigneeState("11");
        if(dto.getExecutionId()==null||"".equals(dto.getExecutionId()))
            return "无法获取该实例ID!";
        dto.setTaskAssingee(muo.getEmpid());
        jbpmService.updateTaskAssigneeState1(dto);
         return "success";
    }*/
    
/*  //20140907 修改
    @Override
    public String updateBuShiAssignStatus(TaskAssgineeDto dto,
            MUOUserSession muo, TSuperviseTable sup) throws Exception {
        String type="10";
        sup.setNodeName1(jbpmService.getTaskById(dto.getTaskId()).getName());
        if(sup.getOpninion()==null||"".equals(sup.getOpninion()))
        sup.setOpninion("");
        insertApproveOpninion(sup, muo, dto.getTaskId(), type, dto);
        dto.setTaskAssigneeState("11");
        if(dto.getExecutionId()==null||"".equals(dto.getExecutionId()))
            return "无法获取该实例ID!";
        dto.setTaskAssingee(muo.getEmpid());
        dto.setIsChild("1");
        jbpmService.updateTaskAssigneeState1(dto); //反馈
        //修改主要负责人的状态为1
        TaskAssgineeDto tad=new TaskAssgineeDto();
        tad.setObjId(String.valueOf(dto.getParentId()));//主键
        tad.setExecutionId(dto.getExecutionId());
        tad.setTaskAssigneeState("1");//3:作废
        jbpmService.updateTaskAssigneeState1(tad); //=更改主要负责人的状态为已办状态 1：签收 已办
         return "success";
    }*/
    
    //20140908 修改
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
                MUOUserSession muo, TSuperviseTable sup) throws Exception {
            if(dto.getExecutionId()==null||"".equals(dto.getExecutionId()))
                return "无法获取该实例ID!";
            String type="10";
            sup.setNodeName1(jbpmService.getTaskById(dto.getTaskId()).getName());
            if(sup.getOpninion()==null||"".equals(sup.getOpninion()))
            sup.setOpninion("");

        
    
            
            //删除当前人员办理信息
             //删除当前已办人员
            TProcessTaskAssigneePerson tpap=new TProcessTaskAssigneePerson();
            tpap.setTaskAssigneeId(muo.getEmpid());
            tpap.setExecutionId(dto.getExecutionId());
            this.tProcessTaskAssigneePersonDAO.deleteByTemplate(tpap);

            String preTaskId=dto.getTaskId();
            //插入下一个节点信息
            insertTask(dto,muo);
            //更新当前执行人信息
            
            TProcessTaskAssignee tptagee=new TProcessTaskAssignee();
            tptagee.setId(dto.getProcessTaskAssigneeId());
            tptagee.setExecutionId(dto.getExecutionId());
            tptagee.setPreTaskId(preTaskId);
            tptagee.setPreTaskAssingee(muo.getEmpid().toString());
            tptagee.setPreTaskOrg(muo.getOrgid());
            tptagee.setPreTaskTime(TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss"));
            tptagee.setNextTaskId(dto.getTaskId());
            tptagee.setBusinessKey(sup.getSuperviseId());
             
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
            insertApproveOpninion(sup, muo, dto.getTaskId(), type, dto);
             return "success";
        }

        @Override
        public void insertSuperviseFile(TSuperviseTable supervise,File[] files, String[] filesFileName, MUOUserSession muo,TaskAssgineeDto taskAssgineeDto) throws Exception {
            // TODO 自动生成的方法存根
            tFileResourceTableService.fileUploads(makeFileBean(supervise,taskAssgineeDto), files, filesFileName, muo);//保存文件    
        }

		@Override
		public List<TSuperviseTable> queryLeader(String positionCode,
				MUOUserSession muo) {
			  HashMap< String, Object> hmp=new HashMap<String, Object>();
		        hmp.put("positionCode", positionCode);
		        return tSuperviseTableDAO.queryLeader(hmp);
		}
    
}