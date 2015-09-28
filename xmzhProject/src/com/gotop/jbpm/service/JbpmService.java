package com.gotop.jbpm.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.json.JSONException;
import org.json.JSONObject;

import com.gotop.crm.util.MUO;
import com.gotop.jbpm.dto.ActivityDto;
import com.gotop.jbpm.dto.ProcessDeployDto;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.model.TProcessTaskAssigneePerson;
import com.gotop.jbpm.model.TProcessTaskExeConfig;
import com.gotop.tyjg.datadictionary.model.DictEntry;
import com.gotop.vo.system.MUOUserSession;

public interface JbpmService {

	/**
	 * 部署流程定义
	 * @param filePath	流程配置文件路径
	 * @return
	 */
	public ProcessDefinition deployProcess(String filePath) throws FileNotFoundException ;

	/**
	 *  启动流程实例
	 * @param definitionId		流程定义id
	 * @param params				流程变量参数
	 * @return
	 */
	public ProcessInstance startProcess(String definitionId, Map<String, Object> params);

	/**
	 * 级联删除流程
	 * @param deploymentId		流程部署id
	 */
	public void deleteProcess(String deploymentId);


	/**
	 * 
	 * 根据userId查询任务
	 * 
	 * @param userId
	 * @return
	 */
	public List<Task> findPersonalTasks(String userId);
	
	/**
	 * 
	 * 根据groupId查询任务
	 * 
	 * @param groupId
	 * @return
	 */
	public List<Task> findGroupTasks(String groupId);
	
	public List<ProcessDefinition> getProcessDefinitions();
	
	public List<ProcessInstance> getProcessInstances();
	
	public ProcessEngine getProcessEngine();
	
	public RepositoryService getRepositoryService();
	
	public ExecutionService getExecutionService();
	
	public TaskService getTaskService();

	public ProcessInstance getProcessInstanceById(String definitionId);

	public Task getTaskById(String taskId);
	/**
	 * 
	 * 结束任务
	 * 
	 * @param taskId
	 */
	public void completeTask(String taskId);

	public String getNextTaskId(String processInstanceId);
	
	public String getTaskNameById(String taskId);
	
	public void completeTask(String taskId, String target ,Map<String, Object> params);

	public Task getTask(String activityName,ProcessInstance processInstance);
	
	public void turnBackTaskAssignee(TaskAssgineeDto taskAssgineeDto);
	
	public void saceTaskAssignee(TaskAssgineeDto taskAssgineeDto);
	
	/**
	 * 更新节点办理状态
	 * @param taskAssgineeDto
	 */
	public void updateTaskAssigneeState(TaskAssgineeDto taskAssgineeDto);
	
	/**
	 * 部门内部会签，更新节点状态
	 * @param taskAssgineeDto
	 */
	public void updateTaskAssigneeState1(TaskAssgineeDto taskAssgineeDto);
	
	/**
	 * 查找已完成任务个数
	 * @param taskAssgineeDto
	 * @return
	 */
	public List<TProcessTaskAssigneePerson> getCompleted(TaskAssgineeDto taskAssgineeDto);
	
	/**
	 * 查找未完成任务个数
	 * @param taskAssgineeDto
	 * @return
	 */
	public List<TProcessTaskAssigneePerson> getUnCompleted(TaskAssgineeDto taskAssgineeDto);

	/**
	 * 查找下个节点执行人配置列表
	 * @param taskAssgineeDto
	 * @return
	 */
	public List<TProcessTaskExeConfig> getNextTaskAssigneeConfigs(TaskAssgineeDto taskAssgineeDto);

	/**
	 * 查询指定岗位的人员
	 * @param taskAssgineeDto
	 * @return
	 */
	public List<Map<String, Object>> getEmpPosition(TaskAssgineeDto taskAssgineeDto);

	public List<Map<String, Object>> getEmpOrg(TaskAssgineeDto taskAssgineeDto);

	public List<Map<String, Object>> getEmpRole(TaskAssgineeDto taskAssgineeDto);

	/**
	 * 查询起草人
	 * @param taskAssgineeDto
	 * @return
	 */
	public List<Map<String, Object>> getEmp(TaskAssgineeDto taskAssgineeDto);

	/**
	 * 查询起草人所属机构的部门领导
	 * @param taskAssgineeDto
	 * @return
	 */
	public List<Map<String, Object>> getEmpPositionByOrg(
			TaskAssgineeDto taskAssgineeDto);

	/**
	 * 查询动态机构(主办和辅办)、指定部门
	 * @param taskAssgineeDto
	 * @return
	 */
	public List<Map<String, Object>> getEmpOrgByPosition(
			TaskAssgineeDto taskAssgineeDto);
	
	/**
	 * 根据流程定义id，启动流程获取流程实例id
	 * @param definitionId
	 * @return
	 */
	public TaskAssgineeDto startProcessByDefinition(String definitionId,Map<String, Object> map);

	public List<Map<String, Object>> getPosition(TaskAssgineeDto taskAssgineeDto);

	public List<Map<String, Object>> getOrg(TaskAssgineeDto taskAssgineeDto);

	public TProcessBusiness findProcessBusiness(TaskAssgineeDto taskAssgineeDto);

	public void deleteBusiness(TProcessBusiness processBusiness);

	public void deleteProcessInstancCascadeeById(String executionId);

	public TProcessBusiness findProcessBusinessFile(
			TProcessBusiness processBusiness);

	public void deleteBusinessFile(TProcessBusiness processBusinessFile);
	
	/**
	 * 保存流程与业务关联数据
	 * @param processBusiness
	 */
	public void saveProcessBusiness(MUOUserSession userSession,TProcessBusiness processBusiness);
	
	/**
	 * 更新流程与业务关联数据
	 * @param processBusiness
	 */
	public void updateProcessBusiness(TProcessBusiness processBusiness);
	
	/**
	 * 分配节点办理人<br/>
	 * taskAssgineeDto.taskId&nbsp;&nbsp;当前节点id<br/>
	 * taskAssgineeDto.taskExeAssginee&nbsp;&nbsp;当前执行人empId<br/>
	 * @param taskAssgineeDto
	 */
	public void assignTask(TaskAssgineeDto taskAssgineeDto);

	public void toTaskConfig1(String xml, String deploymentId) throws UnsupportedEncodingException;

	public void toTaskConfig3(String deploymentId,String oldTaskName,String newTaskName)throws Exception;

	public List<Map<String, Object>> getEmp1(TaskAssgineeDto taskAssgineeDto);
	
	public String getFilePath(String deploymentId,String type) throws Exception;

	public String getPngPath() throws Exception;

	public ProcessDefinition deployProcess(File xmlFile, File pngFile)throws Exception;

	public DictEntry getDictEntry(String string, String taskAssType)throws Exception;
	
	/**
	 * 插入内部会签信息
	 * @param taskAssgineeDto
	 * @return
	 * @throws Exception
	 */
	public String insertTaskAssignPerson(TaskAssgineeDto taskAssgineeDto)throws Exception;

	/**
	 * 查找主要负责人
	 * @param taskAssgineeDto
	 * @return
	 */
	public List<Map<String, Object>> getEmpOrgByMain(
			TaskAssgineeDto taskAssgineeDto);

	/**
	 * 增加beforeName节点后的查找方式
	 * 
	 * @param assgineeDto
	 * @return
	 */
	public List<TProcessTaskExeConfig> getNextTaskAssigneeConfigs2(
			TaskAssgineeDto assgineeDto);
	
	
	/**
	 * 查找部室内部办理未完成任务个数
	 * @param taskAssgineeDto
	 * @return
	 */
	public List<TProcessTaskAssigneePerson> getBsUnCompleted(TaskAssgineeDto taskAssgineeDto);

	/**
	 * 删除流程实例
	 * @param executionId
	 */
	public void deleteProcessInstanceById(String executionId);

	public List<ActivityDto> getTaskNameConfigList(HashMap<String, String> hm);

	/**
	 * 根据json数据data构造符合jbpm4的JSONObject对象
	 * 
	 * @param data
	 * @return
	 */
	public String makeJbpmJsonByData(String data,String pname) throws JSONException;

	public void updateProcessXmlPng(String deploymentId, File xmlFile,
			File pngFile);

	/**
	 * 根据流程获取对应的json数据
	 * @return
	 */
	public JSONObject getJbpmJson(File xmlFile,String pname) throws JSONException;

}
