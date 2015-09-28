package com.gotop.jbpm.service;

import java.util.List;
import java.util.Map;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.model.Activity;
import org.jbpm.api.task.Task;

import com.gotop.jbpm.dto.ActivityDto;
import com.gotop.jbpm.dto.TaskAssgineeDto;

public interface JbpmDemoService {

	/**
	 * 
	 * 部署流程定义
	 * 
	 * @param filePath
	 */
	public void deployProcess(String filePath);

	/**
	 * 
	 *  启动流程实例
	 *  
	 * @param definitionId
	 * @param params
	 * @return
	 */
	public ProcessInstance startProcess(String definitionId, Map<String, Object> params);

	/**
	 * 
	 * 删除流程定义
	 * 
	 * @param deploymentId
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
	
	public HistoryService getHistoryService() ;
	
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

	public void completeTask(String taskId, String target ,Map<String, Object> params);

	public Task getTask(String activityName,ProcessInstance processInstance);

	public List<HistoryTask> findHistoryTasksList(String userId);

	public List<ActivityDto> getNextTaskList(TaskAssgineeDto taskAssgineeDto);

	public String getFormName(TaskAssgineeDto taskAssgineeDto);
}
