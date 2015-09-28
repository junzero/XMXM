package com.gotop.monthReport.service.impl;

import com.gotop.file.model.FileBean;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.messagePublish.dao.ITMessagePublishDAO;
import com.gotop.monthReport.dao.ITWorkMonthReportsDAO;
import com.gotop.monthReport.model.TWorkMonthReports;
import com.gotop.monthReport.service.ITWorkMonthReportsService;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class TWorkMonthReportsService implements ITWorkMonthReportsService {
    /**
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TWorkMonthReportsService.class);
    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITWorkMonthReportsDAO tWorkMonthReportsDAO;
    
    private ITMessagePublishDAO tMessagePublishDAO;
    
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITFileResourceTableService tFileResourceTableService;
    
    /**
     * 工作流service
     */
    protected JbpmService jbpmService;
    
    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITApproveOpninionDAO tApproveOpninionDAO;         //审核意见dao

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
   
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settWorkMonthReportsDAO(ITWorkMonthReportsDAO tWorkMonthReportsDAO) throws Exception {
        this.tWorkMonthReportsDAO = tWorkMonthReportsDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITWorkMonthReportsDAO gettWorkMonthReportsDAO() throws Exception {
        return this.tWorkMonthReportsDAO;
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
     * @return the tFileResourceTableService
     */
    public ITFileResourceTableService gettFileResourceTableService() {
        return tFileResourceTableService;
    }

    /**
     * @param tFileResourceTableService the tFileResourceTableService to set
     */
    public void settFileResourceTableService(
            ITFileResourceTableService tFileResourceTableService) {
        this.tFileResourceTableService = tFileResourceTableService;
    }

    /**
     * @return the tApproveOpninionDAO
     */
    public ITApproveOpninionDAO gettApproveOpninionDAO() {
        return tApproveOpninionDAO;
    }

    /**
     * @param tApproveOpninionDAO the tApproveOpninionDAO to set
     */
    public void settApproveOpninionDAO(ITApproveOpninionDAO tApproveOpninionDAO) {
        this.tApproveOpninionDAO = tApproveOpninionDAO;
    }

    /**
     * @return the jbpmService
     */
    public JbpmService getJbpmService() {
        return jbpmService;
    }

    /**
     * @param jbpmService the jbpmService to set
     */
    public void setJbpmService(JbpmService jbpmService) {
        this.jbpmService = jbpmService;
    }

    /**
     * TODO 保存个人周报。
     *
     * @return
     * @throws Exception
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-29    杨艺祥    新建
     * </pre>
     */
    @Override
    public TaskAssgineeDto insertMonthReportsInfo(TWorkMonthReports monthReports,File[] monthReportFiles,
            File[] completionFiles, File[] gatherFiles, String[] monthReportFilesFileName,String[] completionFilesFileName, 
            String[] gatherFilesFileName, MUOUserSession muo,String btnType,TaskAssgineeDto taskAssgineeDto, String definitionId, String businessType, String isFirst) throws Exception {
        try{
        	String submitType = "";
    		TaskAssgineeDto taskAssgineeDtoTmp = null;
    		String taskId = null;
    		taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
    		if(monthReports.getFlowId()==null || "".equals(monthReports.getFlowId())){
				HashMap< String, Object> map = new HashMap<String, Object>();
				map.put("user", String.valueOf(muo.getEmpid()));
	            taskAssgineeDtoTmp = jbpmService.startProcessByDefinition(definitionId,map);
	            monthReports.setFlowId(taskAssgineeDtoTmp.getExecutionId());
	            taskId=taskAssgineeDtoTmp.getNextTaskId();
	            taskAssgineeDto.setTaskId(taskAssgineeDtoTmp.getNextTaskId());
	            taskAssgineeDto.setExecutionId(taskAssgineeDtoTmp.getExecutionId());
    		}else{
    			taskId = taskAssgineeDto.getNextTaskId();
    			taskAssgineeDto.setTaskId(taskAssgineeDto.getNextTaskId());
    		}
            monthReports.setNodeName1(jbpmService.getTaskById(taskId).getName());
    		TProcessBusiness processBusiness = new TProcessBusiness();
    		processBusiness.setBusinessTitle(monthReports.getReportTitle());
    		processBusiness.setBusinessType(businessType);
           /* if(taskAssgineeDto.getTransitionName()!=null&&!"".equals(taskAssgineeDto.getTransitionName()))*/
                /*taskAssgineeDto.setTransitionName(new String(taskAssgineeDto.getTransitionName().getBytes("iso-8859-1"),"UTF-8"));*/
            if(monthReports.getReportId()==null||"".equals(monthReports.getReportId())){
                monthReports.setCreator(muo.getEmpid());                        //设置创建人
                monthReports.setOrgid(muo.getOrgid());                      //设置归属机构
                monthReports.setNodeId(taskId);                                 //设置节点ID
                monthReports.setFlowId(taskAssgineeDto.getExecutionId());       //设置流程ID
                monthReports.setCreateName(muo.getEmpname());                   //设置创建人名字
                monthReports.setCreateDate(TimeUtil.today());   //获得设置日期
                monthReports.setCreateTime(TimeUtil.now());      //获得创建时间
    			monthReports.setFlowId(taskAssgineeDtoTmp.getExecutionId());
    			//20140905
    			if(monthReports.getOpninion()==null || "null".equals(monthReports.getOpninion()))
       			 monthReports.setOpninion("");
	            submitType = "05";
                Long id = tWorkMonthReportsDAO.insert(monthReports);
        		processBusiness.setBusinessKey(id);
        		processBusiness.setExecutionId(monthReports.getFlowId());
        		jbpmService.saveProcessBusiness(muo,processBusiness);
            }else{
            	//20140905
            	if(monthReports.getOpninion()==null || "null".equals(monthReports.getOpninion()))
       			 monthReports.setOpninion("");
	            submitType = "05";
                tWorkMonthReportsDAO.update(monthReports);
                processBusiness = jbpmService.findProcessBusiness(taskAssgineeDto);
        		processBusiness.setBusinessTitle(monthReports.getReportTitle());
        		processBusiness.setBusinessType(businessType);
        		processBusiness.setBusinessKey(monthReports.getReportId());
        		processBusiness.setExecutionId(monthReports.getFlowId());
        		jbpmService.updateProcessBusiness(processBusiness);
            }
            /*20140905 合并月报
             * tFileResourceTableService.fileUploads(makeFileBean(monthReports,"1"),monthReportFiles, monthReportFilesFileName, muo);//添加本月计划附件
            tFileResourceTableService.fileUploads(makeFileBean(monthReports,"2"),completionFiles, completionFilesFileName, muo);  //添加上月计划附件
*/            tFileResourceTableService.fileUploads(makeFileBean(monthReports,"0"),monthReportFiles, monthReportFilesFileName, muo);  //添加上月计划附件
            if(!"1".equals(btnType)){//1为单纯保存发布信息
    			jbpmService.assignTask(taskAssgineeDto);
                jbpmService.completeTask(taskId, taskAssgineeDto.getTransitionName(), null);
                taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
        		jbpmService.updateTaskAssigneeState(taskAssgineeDto);
                if(!"退回".equals(taskAssgineeDto.getTransitionName())){//进入下一步
                	if("null".equals(taskAssgineeDto.getEmpNames()))
    					taskAssgineeDto.setEmpNames("");
                	if(isFirst==null && taskAssgineeDto.getEmpIds()!=null && !"".equals(taskAssgineeDto.getEmpIds()))
    					submitType = "01";
                    if(!"结束".equals(taskAssgineeDto.getTransitionName())){/*//部门领导、分管领导审核通过
                        String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId()); 
                        monthReports.setNodeId(taskId);
                        jbpmService.saceTaskAssignee(makeTaskAssgineeDto(monthReports, nextTaskId, muo,taskAssgineeDto));
                    }else if("批示".equals(taskAssgineeDto.getTransitionName())){//行领导批示
                        String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
                        monthReports.setNodeId(taskId); 
                        jbpmService.saceTaskAssignee(makeTaskAssgineeDto(monthReports, nextTaskId, muo,taskAssgineeDto));
                    }else if("提交".equals(taskAssgineeDto.getTransitionName())){//办公室汇总完下发
                    	submitType = "01";*/
                    	String nextTaskId = jbpmService.getNextTaskId(taskAssgineeDto.getExecutionId());
                        monthReports.setNodeId(taskId); 
                    	jbpmService.saceTaskAssignee(makeTaskAssgineeDto(monthReports, nextTaskId, muo,taskAssgineeDto));
                    }else{
                    	submitType = "08";
                        monthReports.setNodeId(taskId); 
                    	jbpmService.saceTaskAssignee(makeTaskAssgineeDto(monthReports, "", muo,taskAssgineeDto));
                    }
                }else{//退回
                	submitType = "02";
                    jbpmService.turnBackTaskAssignee(makeTaskAssgineeDtoBack(taskAssgineeDto, monthReports, muo));
                }

                insertApproveOpninion(monthReports, muo, taskId,submitType,taskAssgineeDto);
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        return null;
    }

    /**
    *
    * TODO 获取月报详情.
    *
    * @param reportId
    * @return
    *
    * <pre>
    * 修改日期        修改人    修改原因
    * 2014-7-31    张怡    新建
    * </pre>
    */
    @Override
    public TWorkMonthReports queryMonthReportInfo(Long reportId){
        return tWorkMonthReportsDAO.queryMonthReportInfo(reportId);
    }

    /**
     * 
     *
     * TODO 保存审核意见.
     *
     * @param monthReports
     * @param muo
     * @param taskId
     * @param type
     * @throws Exception
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-31    yyx    新建
     * </pre>
     */
    private void insertApproveOpninion(TWorkMonthReports monthReports,
            MUOUserSession muo, String taskId, String type, TaskAssgineeDto taskAssgineeDto) throws Exception {
        try {
            if(monthReports!=null&&monthReports.getReportId()!=null&&!"".equals(monthReports.getReportId())){
                if(monthReports.getOpninion()!=null/*&&!"".equals(monthReports.getOpninion())*/){
                    TApproveOpninion opninion=new TApproveOpninion();
                    opninion.setResourceId(monthReports.getReportId());
                    opninion.setOperator(muo.getEmpid());
                    opninion.setOrgid(String.valueOf(muo.getOrgid()));
                    opninion.setResourceType("03");
                    opninion.setOperaterDate(TimeUtil.today());
                    opninion.setOperaterTime(TimeUtil.now());
                    opninion.setOpninionContent(monthReports.getOpninion());
                    opninion.setOperatorType(type);
			    	/**
			    	 * 20140903
			    	 */
			    	opninion.setNextorgname("");
			    	opninion.setNextOprName("");
			    	if(taskAssgineeDto.getEmpNames()!=null&&!"".equals(taskAssgineeDto.getEmpNames())){
			    		opninion.setNextOprName(taskAssgineeDto.getEmpNames());
			    		List<HashMap<String,Object>> list = tMessagePublishDAO.queryOrgName(taskAssgineeDto.getEmpIds());
			    		for(int i=0;i<list.size();i++){
			    			opninion.setNextorgname(opninion.getNextorgname()+(String) list.get(i).get("ORGNAME"));
			    			if(i!=list.size()-1){
			    				opninion.setNextorgname(opninion.getNextorgname()+",");
			    			}
			    		}
			    		
			    	}
                    //需要taskId
                    opninion.setNodeId(taskId);
                    opninion.setNodeName(monthReports.getNodeName1());
                    tApproveOpninionDAO.insert(opninion);
                }
            }
            
        } catch (Exception e) {
        //保存审核意见失败。
            log.error("保存审核意见失败。", e);
            throw e;
        }
    }


    /**
     * 
     *
     * TODO 创建fileBean对象.
     *
     * @param monthReports
     * @param fileType
     * @return
     * @throws Exception
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-29    杨艺祥    新建
     * </pre>
     */
    @Override
    public FileBean makeFileBean(TWorkMonthReports monthReports, String fileType) {
        FileBean tfile = new FileBean();
        try {
            tfile.setResourceId(monthReports.getReportId());
            tfile.setResourceType("03");
            tfile.setNodeId(monthReports.getNodeId());
            tfile.setNodeName(monthReports.getNodeName1());
            tfile.setFileType(fileType);
        } catch (Exception e) {
            log.error("生成FileBean文件实体失败", e);
        }
        return tfile;
    }

    
    /**
     * 回退时的dto生成
     * @param dto
     * @param message
     * @param muo
     * @return
     */
    public TaskAssgineeDto makeTaskAssgineeDtoBack(TaskAssgineeDto dto,TWorkMonthReports monthReports,MUOUserSession muo){
        dto.setPreTaskAssingee(muo.getEmpid());
        //回退前 当前办理人机构id
        dto.setPreTaskOrg(muo.getOrgid());
        //回退前 当前节点id
        dto.setPreTaskId(dto.getNextTaskId());
        //回退前 当前办理时间
        dto.setPreTaskTime(TimeUtil.today()+TimeUtil.now());
        //回退后 办理节点id
        dto.setNextTaskId(jbpmService.getNextTaskId(dto.getExecutionId()));
        dto.setBusinessKey(monthReports.getReportId());
        dto.setTaskAssingee(Long.valueOf(dto.getEmpIds()));
        dto.setBusinessType("03");
        return dto;
    }
    
    /**
     * 生成任务实体。[jbpm]
     * @return
     */
    public TaskAssgineeDto makeTaskAssgineeDto(TWorkMonthReports monthReports,String id,MUOUserSession muo,
            TaskAssgineeDto dto) throws Exception{
        TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
        try {   
        	System.out.println();
            taskAssgineeDto.setExecutionId(dto.getExecutionId());
            taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
            taskAssgineeDto.setPreTaskId(monthReports.getNodeId());
            taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
            if(monthReports.getCreateDate()!=null&&!"".equals(monthReports.getCreateDate())){
                taskAssgineeDto.setPreTaskTime(monthReports.getCreateDate()+monthReports.getCreateTime());
            }else{
                taskAssgineeDto.setPreTaskTime(TimeUtil.today()+TimeUtil.now());
            }
            taskAssgineeDto.setNextTaskId(id);
            taskAssgineeDto.setBusinessKey(monthReports.getReportId());
            taskAssgineeDto.setBusinessType("03");
            taskAssgineeDto.setEmpIds(dto.getEmpIds());
            taskAssgineeDto.setEmpNames(dto.getEmpNames());
            taskAssgineeDto.setTargetName(dto.getTargetName());
            taskAssgineeDto.setTaskExeConfigId(dto.getTaskExeConfigId());
        } catch (Exception e) {
            log.error("获取任务实体", e);
        }
        return taskAssgineeDto;
    }

    /**
     * TODO 动态查询，分页查询数据并返回list.
     *
     * @param muo
     * @param monthReports
     * @param page
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-30   杨艺祥    新建
     * </pre>
     */
    @Override
    public List<TWorkMonthReports> queryTWorkMonthReportsList(MUOUserSession muo,
            TWorkMonthReports monthReports, Page page) {
        if(monthReports==null){
            monthReports=new TWorkMonthReports(); 
        }
        monthReports.setOracleStart(page.getBegin());                       //获得查询的起始数据
        monthReports.setOracleEnd(page.getBegin()+page.getLength());        //获得查询的结束数据
        monthReports.setCreator(muo.getEmpid());                            //设置创建人
        monthReports.setEmpId(muo.getEmpid());                              //设置当前操作人ID
        monthReports.setOrgid(muo.getOrgid());                          //设置机构
        monthReports.setJgsx(muo.getOrgJgsx());                             //1、银行/2、邮政
        String roleid="";                                                   //获得操作者的角色
        for(int i=0;i<muo.getRoleid().length;i++){
            roleid+="'"+muo.getRoleid()[i]+"',";
        }

        roleid=roleid.substring(0, roleid.length()-1);
        monthReports.setRoleId(roleid);
        List<TWorkMonthReports> list=tWorkMonthReportsDAO.queryMessagePublishList(monthReports, page);
        return list;
    }

    /**
     * 
     *
     * TODO 根据流程ID获取月报实例.
     *
     * @param executionId
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-31    yyx    新建
     * </pre>
     */
    @Override
    public TWorkMonthReports getMonthReportByFlowId(String flowId) {
        // TODO Auto-generated method stub
        return tWorkMonthReportsDAO.getMonthReportByFlowId(flowId);
    }
}