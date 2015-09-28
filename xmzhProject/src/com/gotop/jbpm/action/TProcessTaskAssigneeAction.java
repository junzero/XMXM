package com.gotop.jbpm.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessTaskAssignee;
import com.gotop.jbpm.model.TProcessTaskAssigneePerson;
import com.gotop.jbpm.service.ITProcessTaskAssigneePersonService;
import com.gotop.jbpm.service.ITProcessTaskAssigneeService;
import com.gotop.jbpm.service.JbpmDemoService;
import com.gotop.jbpm.service.impl.TProcessTaskAssigneePersonService;
import com.gotop.util.XmlConvert;
import com.gotop.util.string.Obj2StrUtils;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryTask;

public class TProcessTaskAssigneeAction extends BaseAction {
	
	protected JbpmDemoService jbpmDemoService;
	
	protected ITProcessTaskAssigneePersonService tProcessTaskAssigneePersonService;
	
	//待办任务列表
	private List<TProcessTaskAssignee> processTaskAssignees;
		
	//已办任务列表
	private List<HistoryTask> historyTasks;
	
	//流程实例id
	private String executionId;
	private String preTaskId;
	private String preTaskAssingee;
	private String preTaskOrg;
	private String preTaskTime;
	private String taskId;
	private String empids;
	
	private TaskAssgineeDto taskAssgineeDto;
	
	private TProcessTaskAssignee taskAssignee;
	
	public TProcessTaskAssignee getTaskAssignee() {
		return taskAssignee;
	}

	public void setTaskAssignee(TProcessTaskAssignee taskAssignee) {
		this.taskAssignee = taskAssignee;
	}

	public TaskAssgineeDto getTaskAssgineeDto() {
		return taskAssgineeDto;
	}

	public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
		this.taskAssgineeDto = taskAssgineeDto;
	}

	public ITProcessTaskAssigneePersonService gettProcessTaskAssigneePersonService() {
		return tProcessTaskAssigneePersonService;
	}

	public void settProcessTaskAssigneePersonService(
			ITProcessTaskAssigneePersonService tProcessTaskAssigneePersonService) {
		this.tProcessTaskAssigneePersonService = tProcessTaskAssigneePersonService;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getEmpids() {
		return empids;
	}

	public void setEmpids(String empids) {
		this.empids = empids;
	}

	public String getPreTaskId() {
		return preTaskId;
	}

	public void setPreTaskId(String preTaskId) {
		this.preTaskId = preTaskId;
	}

	public String getPreTaskAssingee() {
		return preTaskAssingee;
	}

	public void setPreTaskAssingee(String preTaskAssingee) {
		this.preTaskAssingee = preTaskAssingee;
	}

	public String getPreTaskOrg() {
		return preTaskOrg;
	}

	public void setPreTaskOrg(String preTaskOrg) {
		this.preTaskOrg = preTaskOrg;
	}

	public String getPreTaskTime() {
		return preTaskTime;
	}

	public void setPreTaskTime(String preTaskTime) {
		this.preTaskTime = preTaskTime;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public JbpmDemoService getJbpmDemoService() {
		return jbpmDemoService;
	}

	public void setJbpmDemoService(JbpmDemoService jbpmDemoService) {
		this.jbpmDemoService = jbpmDemoService;
	}
	
	public List<HistoryTask> getHistoryTasks() {
		return historyTasks;
	}

	public void setHistoryTasks(List<HistoryTask> historyTasks) {
		this.historyTasks = historyTasks;
	}
	
    public List<TProcessTaskAssignee> getProcessTaskAssignees() {
		return processTaskAssignees;
	}

	public void setProcessTaskAssignees(
			List<TProcessTaskAssignee> processTaskAssignees) {
		this.processTaskAssignees = processTaskAssignees;
	}

	/**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITProcessTaskAssigneeService tProcessTaskAssigneeService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settProcessTaskAssigneeService(ITProcessTaskAssigneeService tProcessTaskAssigneeService) {
        this.tProcessTaskAssigneeService = tProcessTaskAssigneeService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITProcessTaskAssigneeService gettProcessTaskAssigneeService() {
        return this.tProcessTaskAssigneeService;
    }

    /**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public void queryDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = tProcessTaskAssigneeService.queryDataGrid(hm , page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }

    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        tProcessTaskAssigneeService.updateDataGrid(hmp);
    }

    /**
     * comboselect演示.
     * @abatorgenerated
     */
    public void comboSelect() throws Exception {
        HashMap combopara = this.getCombopara();
        if(combopara!=null){
            	List combo = tProcessTaskAssigneeService.queryAllDataList(combopara);
            	String dataresult = XmlConvert.getXmlListBean(combo);
            	MUO.write(dataresult);
        }
    }

    /**
     * viewDataList说明.
     * @abatorgenerated
     */
    public String queryViewList() throws Exception {
        HttpServletRequest request=ServletActionContext.getRequest();
        Page page = this.getPage();
        HashMap hm = new HashMap();
        List orgs = tProcessTaskAssigneeService.queryPageDataList(hm,page);
        request.setAttribute("orgs", orgs);
        request.setAttribute("page", page);
        return "viewlist";
    }
    
    /**
     * 我的已办任务列表
     * @throws Exception 
     */
    public String queryMyCompletedTasksList() throws Exception{
    	//获取用户empId
    	String empId = String.valueOf(this.getCurrentOnlineUser().getEmpid());
    	//获取角色id数组
    	String[] roleIdArray = this.getCurrentOnlineUser().getRoleid();
    	//获取机构代码
    	String orgCode = this.getCurrentOnlineUser().getOrgcode();
    	//将角色id数组转换成用","分割的字符串
    	String roleIds = Obj2StrUtils.join(roleIdArray, String.class, ",");
    	//角色id、人员id、机构id
    	String relationids = "'" + empId + "'" + "," + "'"+ orgCode+"'" + "," + roleIds;
    	
    	//获取岗位id数组
    	String[] positionIdArray = this.getCurrentOnlineUser().getPositionId();
    	//将岗位id数组转换成用","分割的字符串
    	String positionIds = Obj2StrUtils.join(positionIdArray, String.class, ",");
    	//角色id、人员id、机构id
    	if(roleIds!=null&&!"".equals(roleIds))
    		relationids+="," + roleIds ;
    	if(positionIds!=null&&!"".equals(positionIds))
    		relationids+="," + positionIds;
    	
    	List<TProcessTaskAssignee> tProcessTaskAssignees = this.tProcessTaskAssigneeService.queryMyCompletedTasksList(relationids,empId,taskAssignee,this.getPage());
    	this.setProcessTaskAssignees(tProcessTaskAssignees);
    	return "my_completed_tasks";
    }
    
    public String downexl() throws Exception{
    	String empId = String.valueOf(this.getCurrentOnlineUser().getEmpid());
    	//获取角色id数组
    	String[] roleIdArray = this.getCurrentOnlineUser().getRoleid();
    	//获取机构代码
    	String orgCode = this.getCurrentOnlineUser().getOrgcode();
    	//将角色id数组转换成用","分割的字符串
    	String roleIds = Obj2StrUtils.join(roleIdArray, String.class, ",");
    	//角色id、人员id、机构id
    	String relationids = "'" + empId + "'" + "," + "'"+ orgCode+"'" + "," + roleIds;
    	
    	//获取岗位id数组
    	String[] positionIdArray = this.getCurrentOnlineUser().getPositionId();
    	//将岗位id数组转换成用","分割的字符串
    	String positionIds = Obj2StrUtils.join(positionIdArray, String.class, ",");
    	//角色id、人员id、机构id
    	if(roleIds!=null&&!"".equals(roleIds))
    		relationids+="," + roleIds ;
    	if(positionIds!=null&&!"".equals(positionIds))
    		relationids+="," + positionIds;
    	
    	List<TProcessTaskAssignee> tProcessTaskAssignees = this.tProcessTaskAssigneeService.downexl(relationids,empId,taskAssignee);
    	this.setProcessTaskAssignees(tProcessTaskAssignees);
    	return "downexl";
    }
    
    /**
     * 我的待办列表
     * @return
     * @throws Exception 
     */
    public String queryMyToDoTasksList() throws Exception{
    	//获取用户empId
    	String empId = String.valueOf(this.getCurrentOnlineUser().getEmpid());
    	//获取角色id数组
    	String[] roleIdArray = this.getCurrentOnlineUser().getRoleid();
    	//获取机构代码
    	String orgCode = this.getCurrentOnlineUser().getOrgcode();
    	//将角色id数组转换成用","分割的字符串
    	String roleIds = Obj2StrUtils.join(roleIdArray, String.class, ",");
    	//角色id、人员id、机构id
    	String relationids = "'" + empId + "'" + "," + "'"+ orgCode+"'" + "," + roleIds;
    	
    	//获取岗位id数组
    	String[] positionIdArray = this.getCurrentOnlineUser().getPositionId();
    	//将岗位id数组转换成用","分割的字符串
    	String positionIds = Obj2StrUtils.join(positionIdArray, String.class, ",");
    	//角色id、人员id、机构id
    	if(roleIds!=null&&!"".equals(roleIds))
    		relationids+="," + roleIds ;
    	if(positionIds!=null&&!"".equals(positionIds))
    		relationids+="," + positionIds;
    	
    	List<TProcessTaskAssignee> tProcessTaskAssignees = this.tProcessTaskAssigneeService.queryMyToDoTasksList(relationids,taskAssignee,this.getPage());
    	this.setProcessTaskAssignees(tProcessTaskAssignees);
    	return "my_todo_tasks";
    }
    
    /**
     * 我的督办列表
     * @return
     * @throws Exception 
     */
    public String queryMySupTasksList() throws Exception{
        HashMap<String, Object> map= new HashMap<String, Object>();
        map.put("empid", this.getCurrentOnlineUser().getEmpid());
        map.put("orgcode", this.getCurrentOnlineUser().getOrgcode());
    	this.getPage().setLength(10);
    	List<TProcessTaskAssignee> tProcessTaskAssignees = this.tProcessTaskAssigneeService.querySuperviseRemMore(map,this.getCurrentOnlineUser().getPosiCode(),taskAssignee,this.getPage());
    	this.setProcessTaskAssignees(tProcessTaskAssignees);
    	return "my_sup_tasks";
    }
    
    /**
     * 我的草稿
     * @return
     * @throws Exception
     */
	public String queryMyDraftsList() throws Exception{
		//获取用户empId
    	String empId = String.valueOf(this.getCurrentOnlineUser().getEmpid());
    	List<TProcessTaskAssignee> tProcessTaskAssignees = this.tProcessTaskAssigneeService.queryMyDraftsList(empId,taskAssignee,this.getPage());
    	this.setProcessTaskAssignees(tProcessTaskAssignees);
    	return "my_drafts";
	}
    
    /**
     * 配置流程节点办理人
     * @return
     * @throws Exception 
     */
    public String saveTaskAssignee() throws Exception{
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("<flag>");
    	try {    	
    	//获取流程实例对象
    	String[] empIdArray = empids.split(",");
    	TProcessTaskAssignee tProcessTaskAssignee = new TProcessTaskAssignee();
		tProcessTaskAssignee.setExecutionId(taskAssgineeDto.getExecutionId());
		
		tProcessTaskAssignee.setPreTaskAssingee(String.valueOf(taskAssgineeDto.getPreTaskAssingee()));
		tProcessTaskAssignee.setPreTaskId(taskAssgineeDto.getPreTaskId());
		tProcessTaskAssignee.setPreTaskOrg(Long.valueOf(taskAssgineeDto.getPreTaskOrg()));
		tProcessTaskAssignee.setPreTaskTime(taskAssgineeDto.getPreTaskTime());
		//tProcessTaskAssignee.setTaskId(taskAssgineeDto.getTaskId());
		
		tProcessTaskAssignee.setNextTaskId(taskAssgineeDto.getNextTaskId());

		tProcessTaskAssignee.setBusinessKey(taskAssgineeDto.getBusinessKey());
		tProcessTaskAssignee.setBusinessType(taskAssgineeDto.getBusinessType());
		
		Long taskAssigneeId = this.tProcessTaskAssigneeService.insert(tProcessTaskAssignee);
		
    	for (String empId : empIdArray) {
    		TProcessTaskAssigneePerson tProcessTaskAssigneePerson = new TProcessTaskAssigneePerson();
    		tProcessTaskAssigneePerson.setExecutionId(taskAssgineeDto.getExecutionId());
    		tProcessTaskAssigneePerson.setProcessTaskAssigneeId(taskAssigneeId);
    		tProcessTaskAssigneePerson.setTaskAssigneeId(Long.valueOf(empId));
    		//存下个节点办理节点
    		tProcessTaskAssigneePerson.setTaskId(taskAssgineeDto.getNextTaskId());
    		this.tProcessTaskAssigneePersonService.insert(tProcessTaskAssigneePerson);
    		//委派任务
		}
    	buffer.append("success");	
    	}catch (Exception e) {
			buffer.append("failer");	
			e.printStackTrace();
		}
    	buffer.append("</flag>");
    	this.getResponse().getWriter().write(buffer.toString());
    	return null;
    }
    
    
	/**
	 * 20140906 我的流程
	 * 备注：当前登陆者所发起的流程
	 */
	public String myStartProcessList(){
		//获取用户empId
    	Long empId = this.getCurrentOnlineUser().getEmpid();
    	processTaskAssignees = tProcessTaskAssigneeService.myStartProcessList(empId,taskAssignee,this.getPage());
		return "myStartProcessList";
	}
}