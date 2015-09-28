package com.gotop.jbpm.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import java_cup.internal_error;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.model.Activity;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.cmd.CommandService;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.id.DatabaseDbidGenerator;
import org.jbpm.pvm.internal.id.DbidGenerator;
import org.jbpm.pvm.internal.id.PropertyImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.repository.RepositoryCache;
import org.jbpm.pvm.internal.svc.ManagementServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fr.report.core.A.e;
import com.fr.script.function.TRIM;
import com.gotop.crm.util.MUO;
import com.gotop.jbpm.dao.ITProcessBusinessDAO;
import com.gotop.jbpm.dao.ITProcessTaskAssigneeDAO;
import com.gotop.jbpm.dao.ITProcessTaskAssigneePersonDAO;
import com.gotop.jbpm.dao.ITProcessTaskExeConfigDAO;
import com.gotop.jbpm.dto.ActivityDto;
import com.gotop.jbpm.dto.PathDto;
import com.gotop.jbpm.dto.RecDto;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.jpdl.JpdlModelDrawer;
import com.gotop.jbpm.jpdl.model.JpdlModel;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.model.TProcessTaskAssignee;
import com.gotop.jbpm.model.TProcessTaskAssigneePerson;
import com.gotop.jbpm.model.TProcessTaskExeConfig;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.tyjg.datadictionary.dao.IDictEntryDao;
import com.gotop.tyjg.datadictionary.model.DictEntry;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import org.hibernate.Session;
public class JbpmServiceImpl implements JbpmService{

	private ProcessEngine processEngine;

	private RepositoryService repositoryService;

	private ExecutionService executionService;
	
	private TaskService taskService;
	
    protected ITProcessTaskAssigneeDAO tProcessTaskAssigneeDAO;
    
    protected ITProcessTaskAssigneePersonDAO tProcessTaskAssigneePersonDAO;
    
    protected ITProcessTaskExeConfigDAO tProcessTaskExeConfigDAO;
    
    protected ITProcessBusinessDAO tProcessBusinessDAO;

    private IDictEntryDao dictEntryDao;
    
	public IDictEntryDao getDictEntryDao() {
		return dictEntryDao;
	}

	public void setDictEntryDao(IDictEntryDao dictEntryDao) {
		this.dictEntryDao = dictEntryDao;
	}

	public ITProcessBusinessDAO gettProcessBusinessDAO() {
		return tProcessBusinessDAO;
	}

	public void settProcessBusinessDAO(ITProcessBusinessDAO tProcessBusinessDAO) {
		this.tProcessBusinessDAO = tProcessBusinessDAO;
	}

	public ITProcessTaskExeConfigDAO gettProcessTaskExeConfigDAO() {
		return tProcessTaskExeConfigDAO;
	}

	public void settProcessTaskExeConfigDAO(
			ITProcessTaskExeConfigDAO tProcessTaskExeConfigDAO) {
		this.tProcessTaskExeConfigDAO = tProcessTaskExeConfigDAO;
	}

	public ITProcessTaskAssigneeDAO gettProcessTaskAssigneeDAO() {
		return tProcessTaskAssigneeDAO;
	}

	public void settProcessTaskAssigneeDAO(
			ITProcessTaskAssigneeDAO tProcessTaskAssigneeDAO) {
		this.tProcessTaskAssigneeDAO = tProcessTaskAssigneeDAO;
	}

	public ITProcessTaskAssigneePersonDAO gettProcessTaskAssigneePersonDAO() {
		return tProcessTaskAssigneePersonDAO;
	}

	public void settProcessTaskAssigneePersonDAO(
			ITProcessTaskAssigneePersonDAO tProcessTaskAssigneePersonDAO) {
		this.tProcessTaskAssigneePersonDAO = tProcessTaskAssigneePersonDAO;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public ExecutionService getExecutionService() {
		return executionService;
	}

	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public ProcessEngine getProcessEngine() {
		return processEngine;
	}
	
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}
	
	@Override
	public ProcessDefinition deployProcess(String filePath) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(filePath); 
		ZipInputStream zis = new ZipInputStream(fis);  
		
		String deploymentId = repositoryService.createDeployment().addResourcesFromZipInputStream(zis).deploy();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).uniqueResult();
		return processDefinition;
	}
	

	@Override
	public ProcessInstance startProcess(String definitionId, Map<String, Object> params) {
		return executionService.startProcessInstanceById(definitionId, params);
	}

	@Override
	public void deleteProcess(String deploymentId) {
		repositoryService.deleteDeploymentCascade(deploymentId);
	}

	@Override
	public void completeTask(String taskId) {
		taskService.completeTask(taskId);		
	}

	@Override
	public List<Task> findPersonalTasks(String userId) {
		List<Task> tasks = taskService.findPersonalTasks(userId);
		return tasks;
	}

	@Override
	public List<Task> findGroupTasks(String groupId) {
		List<Task> tasks = taskService.findGroupTasks(groupId);
		return tasks;
	}

	@Override
	public List<ProcessDefinition> getProcessDefinitions() {
		List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
		return processDefinitions;
	}

	@Override
	public List<ProcessInstance> getProcessInstances() {
		List<ProcessInstance> processInstances = executionService.createProcessInstanceQuery().list();
		return processInstances;
	}

	@Override
	public ProcessInstance getProcessInstanceById(String definitionId) {
		ProcessInstance processInstance = executionService.findProcessInstanceById(definitionId);
		return processInstance;
	}

	@Override
	public Task getTaskById(String taskId) {
		return taskService.getTask(taskId);
	}

	@Override
	public void completeTask(String taskId, String target ,Map<String, Object> params) {
		taskService.setVariables(taskId,params);
		taskService.completeTask(taskId, target,params);
	}

	@Override
	public Task getTask(String activityName, ProcessInstance processInstance) {
		return taskService.createTaskQuery().processInstanceId(processInstance.getId()).activityName(activityName).uniqueResult();
	}

	@Override
	public String getNextTaskId(String processInstanceId) {
		List<Task> list = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		if(list!=null && list.size()>0)
			return list.get(0).getId();
		else
			return "";
	}

	@Override
	public void turnBackTaskAssignee(TaskAssgineeDto taskAssgineeDto) {
		TProcessTaskAssignee tProcessTaskAssignee = new TProcessTaskAssignee();
		// 流程实例id
		tProcessTaskAssignee.setExecutionId(taskAssgineeDto.getExecutionId());
		// 回退前 当前办理人id
		tProcessTaskAssignee.setPreTaskAssingee(String.valueOf(taskAssgineeDto
				.getPreTaskAssingee()));
		// 回退前 当前办理人机构id
		tProcessTaskAssignee.setPreTaskOrg(Long.valueOf(taskAssgineeDto
				.getPreTaskOrg()));
		// 回退前 当前节点id
		tProcessTaskAssignee.setPreTaskId(taskAssgineeDto.getPreTaskId());
		// 回退前 当前办理时间
		tProcessTaskAssignee.setPreTaskTime(taskAssgineeDto.getPreTaskTime());
		// 回退后 办理节点id
		tProcessTaskAssignee.setNextTaskId(taskAssgineeDto.getNextTaskId());
		tProcessTaskAssignee.setBusinessKey(taskAssgineeDto.getBusinessKey());
		tProcessTaskAssignee.setBusinessType(taskAssgineeDto.getBusinessType());

		
		tProcessTaskAssignee.setTaskExeConfigId(taskAssgineeDto.getTaskExeConfigId());
		// 20140901 添加下个节点名称
		tProcessTaskAssignee.setNextActivityName(taskAssgineeDto
				.getTargetName());
		String empNames = "";
		if(taskAssgineeDto.getEmpIds() != null && !"".equals(taskAssgineeDto.getEmpIds())){
			String[] empIdArray = taskAssgineeDto.getEmpIds().split(",");
			// 20140902 添加下个节点办理人
			if (empIdArray.length != 0) {
				if (!"null".equals(empIdArray[0]) && !"".equals(empIdArray[0])) {
					for (String empId : empIdArray) {
						TaskAssgineeDto temp = new TaskAssgineeDto();
						temp.setObjId(empId);
						List<Map<String, Object>> objs = getEmp1(temp);
						if (objs.size() != 0) {
							for (Map<String, Object> map : objs) {
								empNames += (String) map.get("objName") + ",";
							}
						}
					}
					empNames = empNames.substring(0, empNames.length() - 1);
				}
			}
		}else{
			TaskAssgineeDto temp = new TaskAssgineeDto();
			temp.setObjId(String.valueOf(taskAssgineeDto.getTaskAssingee()));
			List<Map<String, Object>> objs = getEmp1(temp);
			if (objs.size() != 0) {
				for (Map<String, Object> map : objs) {
					empNames += (String) map.get("objName") + ",";
				}
			}
			empNames = empNames.substring(0, empNames.length() - 1);
		}
		if (empNames != null && !"".equals(empNames)) {
			tProcessTaskAssignee.setNextAssingeeName(empNames);
		}
		
	
		Long id = this.tProcessTaskAssigneeDAO.insert(tProcessTaskAssignee);

		if (taskAssgineeDto.getTaskAssingee() != null) {
			TProcessTaskAssigneePerson tProcessTaskAssigneePerson = new TProcessTaskAssigneePerson();
			tProcessTaskAssigneePerson.setExecutionId(taskAssgineeDto
					.getExecutionId());
			tProcessTaskAssigneePerson.setProcessTaskAssigneeId(id);
			tProcessTaskAssigneePerson.setTaskAssigneeId(taskAssgineeDto
					.getTaskAssingee());
			// 存下个节点办理节点
			tProcessTaskAssigneePerson.setTaskId(taskAssgineeDto
					.getNextTaskId());
			this.tProcessTaskAssigneePersonDAO
					.insert(tProcessTaskAssigneePerson);
		} else {

		}

	}

	@Override
	public String getTaskNameById(String taskId) {
		Task task = this.taskService.getTask(taskId);
		return task.getActivityName();
	}

	@Override
	public void saceTaskAssignee(TaskAssgineeDto taskAssgineeDto) {
		String[] empIdArray = taskAssgineeDto.getEmpIds().split(",");
		
    	TProcessTaskAssignee tProcessTaskAssignee = new TProcessTaskAssignee();
		tProcessTaskAssignee.setExecutionId(taskAssgineeDto.getExecutionId());
		
	
		if(taskAssgineeDto.getTaskExeAssginee() != null){
			tProcessTaskAssignee.setPreTaskAssingee(taskAssgineeDto.getTaskExeAssginee());
		}else{
			tProcessTaskAssignee.setPreTaskAssingee(String.valueOf(taskAssgineeDto.getPreTaskAssingee()));
		}
		tProcessTaskAssignee.setPreTaskId(taskAssgineeDto.getPreTaskId());
		tProcessTaskAssignee.setPreTaskOrg(Long.valueOf(taskAssgineeDto.getPreTaskOrg()));
		tProcessTaskAssignee.setPreTaskTime(taskAssgineeDto.getPreTaskTime());
		tProcessTaskAssignee.setNextTaskId(taskAssgineeDto.getNextTaskId());

		tProcessTaskAssignee.setBusinessKey(taskAssgineeDto.getBusinessKey());
		tProcessTaskAssignee.setBusinessType(taskAssgineeDto.getBusinessType());
		
		//20140901 添加下个节点名称
		tProcessTaskAssignee.setNextActivityName(taskAssgineeDto.getTargetName());
		//20140902 添加下个节点办理人
		String empNames = "";
		if(empIdArray.length != 0){
			if(!"null".equals(empIdArray[0])&&!"".equals(empIdArray[0])){
				for (String empId : empIdArray) {
					TaskAssgineeDto temp = new TaskAssgineeDto();
					temp.setObjId(empId);
					List<Map<String, Object>> objs = getEmp1(temp);
					if (objs.size() != 0) {
						for (Map<String, Object> map : objs) {
							empNames+=(String) map.get("objName")+",";
						}
					}
				}
				empNames = empNames.substring(0, empNames.length()-1);
						
			}
		}
		Long taskAssigneeId=Long.valueOf(0);
	/*	if(empNames!=null&&!"".equals(empNames)){
			tProcessTaskAssignee.setNextAssingeeName(empNames);
			 taskAssigneeId = this.tProcessTaskAssigneeDAO.insert(tProcessTaskAssignee);
		}*/
		//20140907 为了显示最后一个节点
		tProcessTaskAssignee.setNextAssingeeName(empNames);
		//存储节点执行对象主键
		tProcessTaskAssignee.setTaskExeConfigId(taskAssgineeDto.getTaskExeConfigId());
		
		taskAssigneeId = this.tProcessTaskAssigneeDAO.insert(tProcessTaskAssignee);
		
		if(empIdArray.length != 0){
			if(!"null".equals(empIdArray[0])&&!"".equals(empIdArray[0])){
				for (String empId : empIdArray) {
		    		TProcessTaskAssigneePerson tProcessTaskAssigneePerson = new TProcessTaskAssigneePerson();
		    		tProcessTaskAssigneePerson.setExecutionId(taskAssgineeDto.getExecutionId());
		    		tProcessTaskAssigneePerson.setProcessTaskAssigneeId(taskAssigneeId);
		    		tProcessTaskAssigneePerson.setTaskAssigneeId(Long.valueOf(empId));
		    		//存下个节点办理节点
		    		tProcessTaskAssigneePerson.setTaskId(taskAssgineeDto.getNextTaskId());
		    		this.tProcessTaskAssigneePersonDAO.insert(tProcessTaskAssigneePerson);
		    	}
			}
			
		}
	}

	@Override
	public void updateTaskAssigneeState(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(taskAssgineeDto.getTaskAssigneeState()!=null){
			map.put("taskAssgineeState",taskAssgineeDto.getTaskAssigneeState());
		}else{
			map.put("taskAssgineeState","1");
		}
		map.put("executionId", taskAssgineeDto.getExecutionId());
		//map.put("taskAssingee", taskAssgineeDto.getTaskAssingee());
		//当前办理者
		map.put("taskAssingee", taskAssgineeDto.getPreTaskAssingee());
		this.tProcessTaskAssigneePersonDAO.updateTaskAssigneeState(map);
	}
	
/*	*//**
	 * 部门内部会签,更新节点状态
	 *//*
	@Override
	public void updateTaskAssigneeState1(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskAssgineeState",taskAssgineeDto.getTaskAssigneeState());
		map.put("executionId", taskAssgineeDto.getExecutionId());
	    if(taskAssgineeDto.getTaskAssingee()!=null&&!"".equals(taskAssgineeDto.getTaskAssingee()))
		map.put("taskAssingee", taskAssgineeDto.getTaskAssingee());
	    if(taskAssgineeDto.getParentId()!=null&&!"".equals(taskAssgineeDto.getParentId()))
	    	map.put("parentId", taskAssgineeDto.getParentId());
	    if(taskAssgineeDto.getIsUpdate()!=null&&!"".equals(taskAssgineeDto.getIsUpdate()))
	    	map.put("isUpdate", taskAssgineeDto.getIsUpdate());
		//当前办理者
		this.tProcessTaskAssigneePersonDAO.updateTaskAssigneeState1(map);
	}*/
	
	/**
	 * 部门内部会签,更新节点状态 20140907 修改
	 */
	@Override
	public void updateTaskAssigneeState1(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskAssgineeState",taskAssgineeDto.getTaskAssigneeState());
		map.put("executionId", taskAssgineeDto.getExecutionId());
	    if(taskAssgineeDto.getTaskAssingee()!=null&&!"".equals(taskAssgineeDto.getTaskAssingee()))
		map.put("taskAssingee", taskAssgineeDto.getTaskAssingee());
	    if(taskAssgineeDto.getParentId()!=null&&!"".equals(taskAssgineeDto.getParentId()))
	    	map.put("parentId", taskAssgineeDto.getParentId());
	    if(taskAssgineeDto.getIsUpdate()!=null&&!"".equals(taskAssgineeDto.getIsUpdate()))
	    	map.put("isUpdate", taskAssgineeDto.getIsUpdate());
	    if(taskAssgineeDto.getObjId()!=null&&!"".equals(taskAssgineeDto.getObjId()))
	    	map.put("objId", taskAssgineeDto.getObjId());
	    if(taskAssgineeDto.getIsChild()!=null&&!"".equals(taskAssgineeDto.getIsChild()))
	    	map.put("ischild", taskAssgineeDto.getIsChild());
		//当前办理者
		this.tProcessTaskAssigneePersonDAO.updateTaskAssigneeState1(map);
	}


	@Override
	public List<TProcessTaskAssigneePerson> getUnCompleted(
			TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskAssgineeState", "0");
		map.put("taskId", taskAssgineeDto.getNextTaskId());
		map.put("executionId", taskAssgineeDto.getExecutionId());
		return this.tProcessTaskAssigneePersonDAO.getUnCompleted(map);
	}

	@Override
	public List<TProcessTaskExeConfig> getNextTaskAssigneeConfigs(
			TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("definitionId", taskAssgineeDto.getDefinitionId());
		map.put("targetName", taskAssgineeDto.getTargetName());
		return this.tProcessTaskExeConfigDAO.getNextTaskAssigneeConfigs(map);
	}

	@Override
	public List<Map<String, Object>> getEmpPosition(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
	/*	map.put("orgId", taskAssgineeDto.getBeginOrg());*/
		map.put("positionId", taskAssgineeDto.getTaskExeAssginee());
		return this.tProcessTaskExeConfigDAO.getEmpPosition(map);
	}

	@Override
	public List<Map<String, Object>> getEmpOrg(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", taskAssgineeDto.getTaskExeAssginee());
		return this.tProcessTaskExeConfigDAO.getEmpOrg(map);
	}

	@Override
	public List<Map<String, Object>> getEmpRole(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", taskAssgineeDto.getBeginOrg());
		map.put("roleId", taskAssgineeDto.getTaskExeAssginee());
		return this.tProcessTaskExeConfigDAO.getEmpRole(map);
	}

	@Override
	public List<Map<String, Object>> getEmp(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empId", taskAssgineeDto.getBeginAssingee());
		map.put("orgId", taskAssgineeDto.getBeginOrg());
		map.put("definitionId", taskAssgineeDto.getDefinitionId());
		return this.tProcessTaskExeConfigDAO.getEmp(map);
	}

	@Override
	public List<Map<String, Object>> getEmpPositionByOrg(
			TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgId", taskAssgineeDto.getBeginOrg());
			map.put("positionId", taskAssgineeDto.getTaskExeAssginee());
			return this.tProcessTaskExeConfigDAO.getEmpPositionByOrg(map);
	}

	@Override
	public List<Map<String, Object>> getEmpOrgByPosition(
			TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", taskAssgineeDto.getTaskExeAssginee());
		return this.tProcessTaskExeConfigDAO.getEmpOrgByPosition(map);
	}

	@Override
	public TaskAssgineeDto startProcessByDefinition(String definitionId,Map<String, Object> map) {
		ProcessInstance processInstance = null;
		if(map != null){
			processInstance = executionService.startProcessInstanceById(definitionId,map);
		}else{
			processInstance = executionService.startProcessInstanceById(definitionId);
		}
		Set<String> activityNames = processInstance.findActiveActivityNames();
		Task task = null;
		for (String activityNameStr : activityNames) {
			// 获取节点实例
			task = this.getTask(activityNameStr, processInstance);
		}
		TaskAssgineeDto assgineeDto = new TaskAssgineeDto();
		assgineeDto.setExecutionId(processInstance.getId());
		assgineeDto.setNextTaskId(task.getId());
		return assgineeDto;
	}

	@Override
	public List<Map<String, Object>> getPosition(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objId", taskAssgineeDto.getObjId());
		return this.tProcessTaskExeConfigDAO.getPosition(map);
	}

	@Override
	public List<Map<String, Object>> getOrg(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objId", taskAssgineeDto.getObjId());
		return this.tProcessTaskExeConfigDAO.getOrg(map);
	}

	@Override
	public TProcessBusiness findProcessBusiness(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("executionId", taskAssgineeDto.getExecutionId());
		return this.tProcessBusinessDAO.findProcessBusiness(map);
	}

	@Override
	public void deleteBusiness(TProcessBusiness processBusiness) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("businessTable", processBusiness.getBusinessTable());
		map.put("businessColumn", processBusiness.getBusinessColumn());
		map.put("businessKey", processBusiness.getBusinessKey());
		this.tProcessBusinessDAO.deleteBusiness(map);
	}

	@Override
	public void deleteProcessInstancCascadeeById(String executionId) {
		this.executionService.deleteProcessInstanceCascade(executionId);
	}

	@Override
	public TProcessBusiness findProcessBusinessFile(
			TProcessBusiness processBusiness) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("businessKey", processBusiness.getBusinessKey());
		map.put("businessType", processBusiness.getBusinessType());
		return this.tProcessBusinessDAO.findProcessBusinessFile(map);
	}

	@Override
	public void deleteBusinessFile(TProcessBusiness processBusinessFile) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", processBusinessFile.getFileId());
		this.tProcessBusinessDAO.deleteBusinessFile(map);
	}

	@Override
	public void saveProcessBusiness(MUOUserSession userSession,TProcessBusiness processBusiness) {
		if(processBusiness.getSubmitId() == null){
			processBusiness.setSubmitId(userSession.getEmpid());
		}
		processBusiness.setSubmitTime(TimeUtil.today()+TimeUtil.now());
		this.tProcessBusinessDAO.insert(processBusiness);
	}

	@Override
	public void updateProcessBusiness(TProcessBusiness processBusiness) {
		this.tProcessBusinessDAO.updateByPrimaryKey(processBusiness);
	}

	@Override
	public void assignTask(TaskAssgineeDto taskAssgineeDto) {
		taskService.assignTask(taskAssgineeDto.getTaskId(), taskAssgineeDto.getTaskExeAssginee());
	}

	@Override
	public void toTaskConfig1(String xml, String deploymentId) throws UnsupportedEncodingException{
		this.tProcessBusinessDAO.toTaskConfig1(xml,deploymentId);
	}

	@Override
	public void toTaskConfig3(String deploymentId,String oldTaskName,String newTaskName) throws Exception {
		//生成流程xml和更改节点
		String filePath = this.getFilePath(deploymentId,"xml");
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(new File(filePath));
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			for (Element element : elements) {
				List content = element.content();
				for (Object object : content) {
					if(object instanceof DefaultElement){
						Element element2 = (Element) object;
						String nameValue = element2.attributeValue("name"); 
						if(nameValue != null){
							if(nameValue.equals(oldTaskName)){
								element2.attribute("name").setValue(newTaskName);
							}
						}
						String toValue = element2.attributeValue("to"); 
						if(toValue != null){
							if(toValue.equals(oldTaskName)){
								element2.attribute("to").setValue(newTaskName);
							}
						}
					
					}
				}
			if(element.getName().equals("task")){
					String value = element.attributeValue("name"); 
					if(value.equals(oldTaskName)){
						element.attribute("name").setValue(newTaskName);
					}
				}
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileOutputStream(
					filePath), format);
			writer.write(document);
			writer.close();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		 String address="";
    	 DictEntry dict=new DictEntry();
    	 dict.setDicttypeid("ZHPT_JBPM_XML_FILE");
    	 Properties props=System.getProperties();
	    System.out.println(props.getProperty("os.name"));
    	 List<DictEntry> list1=dictEntryDao.queryDictEntryList(dict);
    	 if(list1==null||list1.size()<1){
    		 address=ServletActionContext.getServletContext().getRealPath("/uploadfile");
     	 	address=address+"/jbpmOutPng.png";
    	 }
    	 else {
    		 address=list1.get(0).getDictname();//字典中的文件路径
    	    	if(props.getProperty("os.name").indexOf("Windows")>=0){
    	    		address="D:"+address;
    	    	}
    	 }  
    	 File file=new File(address);
			if(!file.isDirectory()){
				file.mkdir();
			}
			File file2 = new File(address+"/jbpmOut.png");
			file2.createNewFile();
		//生成流程图片
		JpdlModel jpdlModel;
		try {
			  jpdlModel = new JpdlModel (filePath);
			  ImageIO.write(new JpdlModelDrawer().draw(jpdlModel), "png", file2);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//获取xml文件 更新jbpm的xml数据
		byte[] fileByte = getBytes(filePath);
		updateJbpm(fileByte, deploymentId,"xml");
		
		//获取png图片 更新jbpm的png数据
		byte[] pngByte = getBytes(file2.getPath());
		updateJbpm(pngByte, deploymentId,"png");
		
		
		//移除当前流程的部署
		//刷新流程部署缓存
		EnvironmentImpl envImpl = ((EnvironmentFactory) processEngine) 
                .openEnvironment();
		 try {  
			 RepositoryCache repositoryCache = envImpl.getFromCurrent(RepositoryCache.class);  
		        repositoryCache.remove(deploymentId);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            envImpl.close();  
	        }  
	}
	
	/**
	 * 获取jbpm的xml文件
	 * @param deploymentId
	 * @return
	 * @throws Exception 
	 */
	public String getFilePath(String deploymentId,String type) throws Exception {
		List<Map<String, Object>> list = this.tProcessBusinessDAO.toTaskConfig3(deploymentId);
		//将xml文件输出到指定位置
		 String address="";
		 String detailinfo = null;
    	 DictEntry dict=new DictEntry();
    	dict.setDicttypeid("ZHPT_JBPM_XML_FILE");
    	 Properties props=System.getProperties();
	    System.out.println(props.getProperty("os.name"));
    	 List<DictEntry> list1=dictEntryDao.queryDictEntryList(dict);
    	 if(list1==null||list1.size()<1){
    		 address=ServletActionContext.getServletContext().getRealPath("/uploadfile");
    	 }
    	 else {
    		 address=list1.get(0).getDictname();//字典中的文件路径
    	    	if(props.getProperty("os.name").indexOf("Windows")>=0){
    	    		address="D:"+address;
    	    	}
    	 }  
		InputStream inStream = null;
		BufferedInputStream in = null;
		String filePath = null;
		for (Map<String, Object> map2 : list) {
			Blob blobValue = (Blob) map2.get("BLOBVALUE");
			
			ObjectInputStream objStream = null;
			 byte[] data = null;  
			 try {
				 Clob blobName = (Clob) map2.get("BLOBNAME");
					int i = 0;
					if (blobName != null) {
						InputStream input;
							input = blobName.getAsciiStream();
							int len1 = (int) blobName.length();
							byte by[] = new byte[len1];
							while (-1 != (i = input.read(by, 0, by.length))) {
								input.read(by, 0, i);
							}
							
							detailinfo = new String(by, "utf-8");
							if(detailinfo.endsWith(".xml") && "xml".equals(type)){
								 in = new BufferedInputStream(blobValue.getBinaryStream());
								 inStream = blobValue.getBinaryStream();  
								 	//输出到文件
									File file=new File(address);
									if(!file.isDirectory()){
										file.mkdir();
									}
								File file2 = new File(address+"/jbpmOut.jpdl.xml");
									file2.createNewFile();
									OutputStream fout=new FileOutputStream(file2);
									//下面将BLOB数据写入文件
									byte[]b=new byte[1024];
									int len=0;
									while((len=inStream.read(b))!=-1){
									fout.write(b,0,len);
									}
									filePath = file2.getPath();
									fout.close();
					                inStream.close();  
					                break;
							}
					}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		
		}
	return filePath;
	}

	/**
	 * 根据文件获得byte数组
	 * @param filePath
	 * @return
	 */
	 public static byte[] getBytes(String filePath){  
	        byte[] buffer = null;  
	        try {  
	            File file = new File(filePath);  
	            FileInputStream fis = new FileInputStream(file);  
	            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	            byte[] b = new byte[1000];  
	            int n;  
	            while ((n = fis.read(b)) != -1) {  
	                bos.write(b, 0, n);  
	            }  
	            fis.close();  
	            bos.close();  
	            buffer = bos.toByteArray();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        return buffer;  
	    }

	 /**
	  * 更新jbpm的二进制数据
	  * @param fileByte
	  * @param deploymentId
	  * @param type
	  */
	 private void updateJbpm(byte[] fileByte, String deploymentId,String type){
		 //获取jbpm的xml和png的二进制数据
		 List<Map<String, Object>> list = this.tProcessBusinessDAO.toTaskConfig3(deploymentId);
			for (Map<String, Object> map2 : list) {
				Clob blobName = (Clob) map2.get("BLOBNAME");
				int i = 0;
				if (blobName != null) {
					InputStream input;
					try {
						input = blobName.getAsciiStream();
						int len = (int) blobName.length();
						byte by[] = new byte[len];
						while (-1 != (i = input.read(by, 0, by.length))) {
							input.read(by, 0, i);
						}
						String detailinfo = null;
						//获取二进制数据对应的clob字段名称
						detailinfo = new String(by, "utf-8");
						if(detailinfo.endsWith(".png") && type.equals("png")){
							//更新流程图的二进制数据
							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("blobName", detailinfo);
							map1.put("deploymentId", deploymentId);
							map1.put("fileByte", fileByte);
							this.tProcessBusinessDAO.updateJbpm(map1);
						}else if(detailinfo.endsWith(".xml") && type.equals("xml")){
							//更新xml的二进制数据
							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("blobName", detailinfo);
							map1.put("deploymentId", deploymentId);
							map1.put("fileByte", fileByte);
							this.tProcessBusinessDAO.updateJbpm(map1);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	 }
	 
	@Override
	public List<Map<String, Object>> getEmp1(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objId", taskAssgineeDto.getObjId());
		return this.tProcessTaskExeConfigDAO.getEmp1(map);
	}

	@Override
	public String getPngPath() throws Exception {
		 String address="";
    	 DictEntry dict=new DictEntry();
    	 dict.setDicttypeid("ZHPT_JBPM_XML_FILE");
    	 Properties props=System.getProperties();
	    System.out.println(props.getProperty("os.name"));
    	 List<DictEntry> list1=dictEntryDao.queryDictEntryList(dict);
    	 if(list1==null||list1.size()<1){
    		 address=ServletActionContext.getServletContext().getRealPath("/uploadfile");
     	 	address=address+"/jbpmOutPng.png";
    	 }
    	 else {
    		 address=list1.get(0).getDictname();//字典中的文件路径
    	    	if(props.getProperty("os.name").indexOf("Windows")>=0){
    	    		address="D:"+address;
    	    	}
    	 } 
    	 return address;
	}

	@Override
	public ProcessDefinition deployProcess(File xmlFile, File pngFile) throws Exception {
		
		String zipName = pngFile.getName().substring(0, pngFile.getName().indexOf("."));
		String zipPath = getPngPath();
		zipPath = getPngPath()+"/"+zipName+".zip";
		 ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));   
		 File[] file1 = {xmlFile,pngFile};   
		  byte[] buffer = new byte[1024];   
		  for(int i=0;i<file1.length;i++) {   
	           FileInputStream fis = new FileInputStream(file1[i]);   
	           out.putNextEntry(new ZipEntry(file1[i].getName()));   
	           int len;   
	          while((len = fis.read(buffer))>0) {   
	           out.write(buffer,0,len);    
	          }   
	           out.closeEntry();   
	           fis.close();   
	       }   
	        out.close();   
	        FileInputStream fileInputStream = new FileInputStream(new File(zipPath));
	        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
		String dpId = repositoryService.createDeployment().addResourcesFromZipInputStream(zipInputStream).deploy();
		ProcessDefinition newPd = repositoryService.createProcessDefinitionQuery().deploymentId(dpId).uniqueResult();
		return newPd;
	}

	@Override
	public DictEntry getDictEntry(String string, String taskAssType) throws Exception {
		 DictEntry dict=new DictEntry();
		 DictEntry dict2=new DictEntry();
    	 dict.setDicttypeid(string);
    	 List<DictEntry> list1=dictEntryDao.queryDictEntryList(dict);
    	 for (DictEntry dictEntry : list1) {
			if(dictEntry.getDictid().equals(taskAssType)){
				dict2 = dictEntry;
				break;
			}
		}
		return dict2;
	}

	@Override
	public List<TProcessTaskAssigneePerson> getCompleted(
			TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskAssgineeState", "1");
		map.put("taskId", taskAssgineeDto.getNextTaskId());
		map.put("executionId", taskAssgineeDto.getExecutionId());
		return this.tProcessTaskAssigneePersonDAO.getCompleted(map);
	}

	/**
	 * 转内部督办
	 */
	@Override
	public String insertTaskAssignPerson(TaskAssgineeDto taskAssgineeDto)
			throws Exception {
		if(taskAssgineeDto!=null){
			String[] empIdArray = taskAssgineeDto.getEmpIds().split(",");
			if(empIdArray==null||"".equals(empIdArray))
				return "请选择要办理的人员。";
			if(taskAssgineeDto.getExecutionId()==null||"".equals(taskAssgineeDto.getExecutionId()))
				return "无法获取实例编号。";
			if(taskAssgineeDto.getTaskId()==null||"".equals(taskAssgineeDto.getTaskId()))
				return "无当前任务ID。";
			TProcessTaskAssigneePerson tap=new TProcessTaskAssigneePerson();
			tap.setExecutionId(taskAssgineeDto.getExecutionId());
			tap.setTaskId(taskAssgineeDto.getTaskId());
			if (taskAssgineeDto.getTaskAssigneeState()==null||"".equals(taskAssgineeDto.getTaskAssigneeState())){
			    tap.setTaskAssigneeState("10");
            }else{
                
                tap.setTaskAssigneeState(taskAssgineeDto.getTaskAssigneeState());
            }
			
			tap.setProcessTaskAssigneeId(taskAssgineeDto.getProcessTaskAssigneeId());
			tap.setParentId(taskAssgineeDto.getParentId());
			if (taskAssgineeDto.getIsChild()==null||"".equals(taskAssgineeDto.getIsChild())){
			    tap.setIsChild("1");
			}else{
			    
			    tap.setIsChild(taskAssgineeDto.getIsChild());
			}
			for(int i=0;i<empIdArray.length;i++){
				tap.setTaskAssigneeId(Long.valueOf(empIdArray[i].trim()));
				tProcessTaskAssigneePersonDAO.insert1(tap);
			}
			return "success";
		}else{
			return "无法获取dto相关信息";
		}
	}

	@Override
	public List<Map<String, Object>> getEmpOrgByMain(
			TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", taskAssgineeDto.getTaskExeAssginee());
		return this.tProcessTaskExeConfigDAO.getEmpOrgByMain(map);
	}

	@Override
	public List<TProcessTaskExeConfig> getNextTaskAssigneeConfigs2(
			TaskAssgineeDto assgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("definitionId", assgineeDto.getDefinitionId());
		map.put("targetName", assgineeDto.getTargetName());
		map.put("beforeName", assgineeDto.getBeforeName());
		return this.tProcessTaskExeConfigDAO.getNextTaskAssigneeConfigs2(map);
	}

	
	/**
	 * 部室内部办理子未完成个数
	 */
	@Override
	public List<TProcessTaskAssigneePerson> getBsUnCompleted(TaskAssgineeDto taskAssgineeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskAssgineeState", "0");
		map.put("taskId", taskAssgineeDto.getTaskId());
		map.put("executionId", taskAssgineeDto.getExecutionId());
		map.put("isChild", "1");
		return this.tProcessTaskAssigneePersonDAO.getBsUnCompleted(map);
	}

	@Override
	public void deleteProcessInstanceById(String executionId) {
		this.executionService.deleteProcessInstance(executionId);
	}

	@Override
	public List<ActivityDto> getTaskNameConfigList(HashMap<String, String> hm) {
	
		List<ActivityDto> activityDtos = new ArrayList<ActivityDto>();
		
		String definitionId = hm.get("definitionId");
		String taskName = hm.get("taskName");
		
		 ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) this.repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(definitionId).uniqueResult();
			definitionId = processDefinitionImpl.getId();
			List<ActivityImpl> list = (List<ActivityImpl>) processDefinitionImpl.getActivities();
			for (ActivityImpl activityImpl : list) {
				if(activityImpl.getName().equals(taskName)){
					
					List  list2 = activityImpl.getOutgoingTransitions();
				      for (Iterator iterator = list2.iterator(); iterator.hasNext();) {  
			                Transition ts = (Transition) iterator.next(); 
			                Activity activity = ts.getDestination();
			         /*       ActivityDto activityDto = new ActivityDto();
							activityDto.setBeforeName(activityImpl.getName());
							activityDto.setDestinationName(activity.getName());
							activityDto.setDefinitionId(definitionId);
							activityDtos.add(activityDto);*/
			            	ActivityDto activityDto = new ActivityDto();
			                if("task".equals(activity.getType())){
			                	TaskAssgineeDto assgineeDto = new TaskAssgineeDto();
			                	assgineeDto.setDefinitionId(definitionId);
			                	assgineeDto.setTargetName(activity.getName());
			                	assgineeDto.setBeforeName(activityImpl.getName());
			                	List<TProcessTaskExeConfig> tProcessTaskExeConfigs2 = getNextTaskAssigneeConfigs2(assgineeDto);
			                	
			                	if(tProcessTaskExeConfigs2.size() !=0){
			                		if(tProcessTaskExeConfigs2.get(0).getActShowName() != null){
			                			activityDto.setActShowName(tProcessTaskExeConfigs2.get(0).getActShowName());
			                		}else{
			                			//activityDto.setActShowName();
			                		}
			                	}
			                	activityDto.setBeforeName(activityImpl.getName());
								activityDto.setDestinationName(activity.getName());
								activityDto.setDefinitionId(definitionId);
								activityDtos.add(activityDto);
			                }
				      }
				}
			}
			return activityDtos;
	}

	@Override
	public String makeJbpmJsonByData(String data,String pname) throws JSONException {
		JSONObject jsonObject = new JSONObject(data);
		/*JSONObject propsPJson = jsonObject.getJSONObject("props");*/
		JSONObject statesJson = jsonObject.getJSONObject("states");
		JSONObject pathsJson = jsonObject.getJSONObject("paths");
		String str = "";
	/*	String pName = propsPJson.getJSONObject("props").getJSONObject("name").getString("value");*/
		str+="<process name=\""+pname+"\" xmlns=\"http://jbpm.org/4.4/jpdl\">";
		 Iterator it = statesJson.keys();  
         while (it.hasNext()) {  
             String key = (String) it.next();  
             JSONObject j = statesJson.getJSONObject(key);
             String type = (String) j.get("type");
             if("start".equals(type)){
            	 	//开始节点
            	 str += "<start";
            	 if(j.getJSONObject("text") != null){
            		 JSONObject textJson = j.getJSONObject("text");
            		 if(textJson.getString("text") != null){
            			 String name = textJson.getString("text");
                		 str += " name=" +"\""+ name +"\""; 
            		 }
            	 }
            	 if(j.getJSONObject("attr") != null){
            		 JSONObject attrJson = j.getJSONObject("attr");
            		 str += " g=\"";
            		 if(attrJson.getString("x") != null){
            			 str += attrJson.getString("x");
            			 str +=",";
            		 }
            		 if(attrJson.getString("y") != null){
            			 str += attrJson.getString("y");
            			 str +=",";
            		 }
            		 if(attrJson.getString("width") != null){
            			 str += attrJson.getString("width");
            			 str +=",";
            		 }
            		 if(attrJson.getString("height") != null){
            			 str += attrJson.getString("height");
            		 }
            		 str +="\">";
            		 str = makePath(key, str, statesJson, pathsJson);
            		 str +="</start>";
            	 }
             }else if("task".equals(type)){
            	 	//任务节点
            	 str += "<task";
            	 if(j.getJSONObject("text") != null){
            		 JSONObject textJson = j.getJSONObject("text");
            		 if(textJson.getString("text") != null && !"".equals(textJson.getString("text"))){
            			 String name = textJson.getString("text");
                		 str += " name=" +"\""+ name +"\""; 
            		 }
            	 }
            	 if(j.getJSONObject("attr") != null){
            		 JSONObject attrJson = j.getJSONObject("attr");
            		 str += " g=\"";
            		 if(attrJson.getString("x") != null && !"".equals(attrJson.getString("x"))){
            			 str += attrJson.getString("x");
            			 str +=",";
            		 }
            		 if(attrJson.getString("y") != null && !"".equals(attrJson.getString("y"))){
            			 str += attrJson.getString("y");
            			 str +=",";
            		 }
            		 if(attrJson.getString("width") != null && !"".equals(attrJson.getString("width"))){
            			 str += attrJson.getString("width");
            			 str +=",";
            		 }
            		 if(attrJson.getString("height") != null && !"".equals(attrJson.getString("height"))){
            			 str += attrJson.getString("height");
            		 }
            		 str +="\"";
            	 }
            	 if(j.getJSONObject("props") != null){
            		 JSONObject propsJson = j.getJSONObject("props");
            		 if(propsJson.getJSONObject("desc") != null && !"".equals(propsJson.getJSONObject("desc").getString("value"))){
            			 //拼接desc描述内容
            			 str += " desc=\"" +propsJson.getJSONObject("desc").getString("value") + "\"";
            		 }
            		 if(propsJson.getJSONObject("form") != null){
            			 if(!"".equals(propsJson.getJSONObject("form").getString("value"))){
            				//拼接form表单内容
                			 str += " form=\"" +propsJson.getJSONObject("form").getString("value") + "\"";
            			 }else{
            				 str += " form=\"" +"/commonProcess/tCommonProcessAction_getCommonProcessForapprove.action" + "\"";
            			 }
            		 }
            		 if(propsJson.getJSONObject("assignee") != null && !"".equals(propsJson.getJSONObject("assignee").getString("value"))){
            			//拼接assignee内容
            			 str += " assignee=\"" +propsJson.getJSONObject("assignee").getString("value") + "\"";
            		 }
            	 }
            	 str +=" >";
            	 str = makePath(key, str, statesJson, pathsJson);
        		 str +="</task>";
             }else if("end".equals(type)){
            	   //结束节点
            	 str += "<end";
            	 if(j.getJSONObject("text") != null){
            		 JSONObject textJson = j.getJSONObject("text");
            		 if(textJson.getString("text") != null){
            			 String name = textJson.getString("text");
                		 str += " name=" +"\""+ name +"\""; 
            		 }
            	 }
            	 if(j.getJSONObject("attr") != null){
            		 JSONObject attrJson = j.getJSONObject("attr");
            		 str += " g=\"";
            		 if(attrJson.getString("x") != null){
            			 str += attrJson.getString("x");
            			 str +=",";
            		 }
            		 if(attrJson.getString("y") != null){
            			 str += attrJson.getString("y");
            			 str +=",";
            		 }
            		 if(attrJson.getString("width") != null){
            			 str += attrJson.getString("width");
            			 str +=",";
            		 }
            		 if(attrJson.getString("height") != null){
            			 str += attrJson.getString("height");
            		 }
            		 str +="\">";
            		 str =  makePath(key, str, statesJson, pathsJson);
            		 str +="</end>";
            	 }
             }
         }  
         str+="</process>";
         System.out.println(str);
		return str;
	}

	/**
	 * 根据页面的在线编辑器和节点的json构造线条的xml信息
	 * 
	 * @param key
	 * @param str
	 * @param statesJson
	 * @param pathsJson
	 * @return
	 * @throws JSONException
	 */
	public String makePath(String key,String str,JSONObject statesJson,JSONObject pathsJson) throws JSONException{
		Iterator  it2 = pathsJson.keys();  
        while (it2.hasNext()) {  
            String key2 = (String) it2.next();  
            JSONObject j2 = pathsJson.getJSONObject(key2);
            String from = j2.getString("from");
            if(from.equals(key)){
           	 String to = j2.getString("to");
	             if(to != null && !"".equals(to)){
	            	  str += "<transition";
	                  String toName = statesJson.getJSONObject(to).getJSONObject("text").getString("text");
	                  str += " to=\"" + toName + "\"";
	             }
	             str += " g=\"";
	             if(j2.getJSONArray("dots") != null && j2.getJSONArray("dots").length()>0){
	            	 JSONArray dotsJson = j2.getJSONArray("dots");
	        		 if(dotsJson.getJSONObject(0) != null){
	        			 if( !"".equals(dotsJson.getJSONObject(0).getString("x"))){
	        				 str += dotsJson.getJSONObject(0).getString("x");
		        			 str +=",";
	        			 }
	        			 if(!"".equals(dotsJson.getJSONObject(0).getString("y"))){
		        			 str += dotsJson.getJSONObject(0).getString("y");
		        		 }
	        		 }
	        		 str+=":";
	             }
	             if(j2.getJSONObject("textPos") != null){
	            	 JSONObject textPosJson = j2.getJSONObject("textPos");
	        		 if(textPosJson.getString("x") != null && !"".equals(textPosJson.getString("x"))){
	        			 str += textPosJson.getString("x");
	        			 str +=",";
	        		 }
	        		 if(textPosJson.getString("y") != null && !"".equals(textPosJson.getString("y"))){
	        			 str += textPosJson.getString("y");
	        		 }
	             }
	             str +="\"";
	             if(j2.getJSONObject("text") != null && !"".equals(j2.getJSONObject("text").getString("text"))){
	            	 str += " name=\"" + j2.getJSONObject("text").getString("text") + "\"";
	             }
	             str +="/>";
            }
        }
        return str;
	}

	@Override
	public void updateProcessXmlPng(String deploymentId, File xmlFile,
			File pngFile) {
		// 获取xml文件 更新jbpm的xml数据
		byte[] fileByte = getBytes(xmlFile.getPath());
		updateJbpm(fileByte, deploymentId, "xml");

		// 获取png图片 更新jbpm的png数据
		byte[] pngByte = getBytes(pngFile.getPath());
		updateJbpm(pngByte, deploymentId, "png");

		// 移除当前流程的部署
		// 刷新流程部署缓存
		EnvironmentImpl envImpl = ((EnvironmentFactory) processEngine)
				.openEnvironment();
		try {
			RepositoryCache repositoryCache = envImpl
					.getFromCurrent(RepositoryCache.class);
			repositoryCache.remove(deploymentId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			envImpl.close();
		}
	}

	@Override
	public JSONObject getJbpmJson(File xmlFile,String pname) throws JSONException {
		JSONObject obj = new JSONObject();
		//在线编辑器的节点对象json
		JSONObject statesJson = new JSONObject();
		//在线编辑器的线条对象json
		JSONObject pathsJson = new JSONObject();
		//在线编辑器的流程对象json
		JSONObject propsJson = new JSONObject();
		
		// 存放transition节点的列表
		List<Element> pathElements = new ArrayList<Element>();
		List<PathDto> pathDtos = new ArrayList<PathDto>();
		
		try {
			SAXReader sr = new SAXReader();
			Document doc = sr.read(xmlFile);
			//获取根节点
			Element el_root = doc.getRootElement();
			List<Attribute> elAttributes = el_root.attributes();
			for (Attribute attribute : elAttributes) {
				if("name".equals(attribute.getName())){
					pname = attribute.getValue();
					/*JSONObject v = new JSONObject();
					JSONObject d = new JSONObject();
					JSONObject s = new JSONObject();
					d.put("value", "");
					v.put("value", attribute.getValue());
					s.put("name", v);
					s.put("key", v);
					s.put("desc", d);
					propsJson.put("props", s);*/
				}
			}
			Iterator it = el_root.elementIterator();
			int i = 0;
			while (it.hasNext()) {
				Object o = it.next();
				Element el_row = (Element) o;
				// 解析节点下的元素
				List<Element> elements = el_row.elements();
				for (Element element : elements) {
					pathElements.add(element);
				}
				RecDto dto = new RecDto();
				// 获取节点名称-对应json中的type
				String type = el_row.getName();
				// 节点类型
				dto.setType(type);
				List<Attribute> attributes = el_row.attributes();
				// 解析节点属性
				JSONObject props = new JSONObject();
				for (Attribute attribute : attributes) {
					
					if ("g".equals(attribute.getName())) {
						// 节点对应的坐标
						String value = attribute.getStringValue();
						// 从左到右，分别为gValues[0]为x，gValues[1]为y，gValues[2]为width，gValues[3]为height
						String[] gValues = value.split(",");
						JSONObject attr = new JSONObject();
						// 坐标采用Long类型存储,若存为String类型页面会显示变大
						attr.put("x", Long.valueOf(gValues[0]));
						attr.put("y", Long.valueOf(gValues[1]));
						attr.put("width", Long.valueOf(gValues[2]));
						attr.put("height", Long.valueOf(gValues[3]));
						dto.setAttr(attr);
					}
					if ("name".equals(attribute.getName())) {
						// 节点的显示名称
						String value = attribute.getStringValue();
						JSONObject text = new JSONObject();
						text.put("text", value);
						dto.setText(text);
					
						JSONObject valueJson = new JSONObject();
						valueJson.put("value", value);
						props.put("text", valueJson);
						//dto.setProps(props);
					}
					if ("form".equals(attribute.getName())) {
						// 节点的表单内容
						String value = attribute.getStringValue();
						JSONObject form = new JSONObject();
						form.put("value", value);
						props.put("form", form);
						//dto.setProps(props);
					}if ("assignee".equals(attribute.getName())) {
						// 节点的表单内容
						String value = attribute.getStringValue();
						JSONObject assignee = new JSONObject();
						assignee.put("value", value);
						props.put("assignee", assignee);
					}
					
				}
				dto.setProps(props);
				JSONObject jsonObject = new JSONObject(dto);
				jsonObject.putOpt("attr", dto.getAttr());
				jsonObject.put("text", dto.getText());
				jsonObject.put("props", dto.getProps());
				statesJson.put("rect" + i, jsonObject);

				for (Element element : elements) {
					// 查询线条的其他属性
					List<Attribute> attributes2 = element.attributes();
					PathDto pathDto = new PathDto();
					pathDto.setFrom("rect" + i);
					for (Attribute attribute : attributes2) {
						if ("g".equals(attribute.getName())) {
							// 节点对应的坐标
							String value = attribute.getStringValue();
							if (value.contains(":")) {
								// 回退
								String[] gValues = value.split(":");
								String gp = gValues[0];
								String[] gps = gp.split(",");
								JSONObject dots = new JSONObject();
								dots.put("x", Long.valueOf(gps[0]));
								dots.put("y", Long.valueOf(gps[1]));
								JSONArray array = new JSONArray();
								array.put(dots);
								pathDto.setDots(array);
								String gn = gValues[1];
								String[] gns = gn.split(",");
								JSONObject textPos = new JSONObject();
								textPos.put("x", Long.valueOf(gns[0]));
								textPos.put("y", Long.valueOf(gns[1]));
								pathDto.setTextPos(textPos);
							} else {
								// 前进
								String[] gValue1s = value.split(",");
								JSONObject textPos = new JSONObject();
								// 坐标采用Long类型存储,若存为String类型页面会显示变大
								textPos.put("x", Long.valueOf(gValue1s[0]));
								textPos.put("y", Long.valueOf(gValue1s[1]));
								pathDto.setTextPos(textPos);
							}
						}
						if ("name".equals(attribute.getName())) {
							// 节点的显示名称
							String value = attribute.getStringValue();
							JSONObject text = new JSONObject();
							text.put("text", value);
							pathDto.setText(text);
						}
						if ("to".equals(attribute.getName())) {
							// 目标节点
							String value = attribute.getStringValue();
							pathDto.setTo(value);
						}
					}
					if(pathDto.getText() == null){
						JSONObject object = new JSONObject();
						object.put("text", "");
						pathDto.setText(object);
					}
					pathDtos.add(pathDto);
				}
				i++;
			}
			// 赋值states的json对象
			obj.put("states", statesJson);

			for (PathDto pathDto : pathDtos) {
				Iterator it1 = statesJson.keys();  
		         while (it1.hasNext()) {  
		        	  String key = (String) it1.next();  
		              JSONObject j1 = statesJson.getJSONObject(key);
		              String name = j1.getJSONObject("text").getString("text");
		              if(name.equals(pathDto.getTo())){
		            	  pathDto.setTo(key);
		              }
		         }
				JSONObject jsonObject = new JSONObject(pathDto);
				jsonObject.put("from", pathDto.getFrom());
				jsonObject.put("to", pathDto.getTo());
				// 考虑dots为空的情况，若不为""，则页面不显示线条
				if (pathDto.getDots() == null) {
					jsonObject.put("dots", "");
				} else {
					jsonObject.put("dots", pathDto.getDots());
				}

				jsonObject.put("text", pathDto.getText());
				jsonObject.put("textPos", pathDto.getTextPos());
				jsonObject.put("props", pathDto.getProps());

				pathsJson.put("path" + i, jsonObject);
				i++;
			}
			obj.put("paths", pathsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("props", propsJson);
		return obj;
	}
}
