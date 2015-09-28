package com.gotop.jbpm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.jbpm.api.Configuration;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.model.Activity;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;
import org.jbpm.jpdl.internal.activity.TaskActivity;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.history.model.HistoryActivityInstanceImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.task.TaskDefinitionImpl;
import org.springframework.transaction.annotation.Transactional;

import com.gotop.jbpm.dto.ActivityDto;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessTaskExeConfig;
import com.gotop.jbpm.service.JbpmDemoService;
import com.gotop.jbpm.service.JbpmService;

public class JbpmDemoServiceImpl implements JbpmDemoService{

	private ProcessEngine processEngine;

	private RepositoryService repositoryService;

	private ExecutionService executionService;
	
	private TaskService taskService;
	
	private HistoryService historyService;
	
	private JbpmService jbpmService;
	
	public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
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
	public void deployProcess(String filePath) {
		ZipInputStream zipInputStream = new ZipInputStream(this.getClass()
				.getResourceAsStream(filePath));
		repositoryService.createDeployment().addResourcesFromZipInputStream(zipInputStream).deploy();
		//.addResourceFromClasspath(filePath).deploy();
			/*	.addResourcesFromZipInputStream(zipInputStream).deploy();*/
	}

	@Override
	public ProcessInstance startProcess(String definitionId, Map<String, Object> params) {
		return executionService.startProcessInstanceById(definitionId, params);
	}

	@Override
	public void deleteProcess(String deploymentId) {
		//repositoryService.deleteDeployment(deploymentId);
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
	public List<HistoryTask> findHistoryTasksList(String userId) {
		List<HistoryTask> historyTasks = historyService.createHistoryTaskQuery().assignee(userId).state("completed").list();
		return historyTasks;
	}

	@Override
	public List<ActivityDto> getNextTaskList(TaskAssgineeDto taskAssgineeDto) {
		List<ActivityDto>addList = null;
		String definitionId = null;
    	EnvironmentImpl envImpl = ((EnvironmentFactory) processEngine) 
                 .openEnvironment();
    	 try {
    		 ActivityImpl activityImpl = null;
    		 if(taskAssgineeDto.getExecutionId() != null && !"".equals(taskAssgineeDto.getExecutionId())){
    			 ExecutionImpl executionImpl = (ExecutionImpl)executionService.findExecutionById(taskAssgineeDto.getExecutionId());
    			activityImpl = executionImpl.getActivity();
    			definitionId = executionImpl.getProcessDefinitionId();
    		 }else{
    			 if(taskAssgineeDto.getDefinitionId()!=null && !"".equals(taskAssgineeDto.getDefinitionId())){
    				 ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) this.repositoryService.createProcessDefinitionQuery()
    							.processDefinitionId(taskAssgineeDto.getDefinitionId()).uniqueResult();
    					definitionId = processDefinitionImpl.getId();
    					List<ActivityImpl> list = (List<ActivityImpl>) processDefinitionImpl.getActivities();
    					for (ActivityImpl activityImpl1 : list) {
    						String type = activityImpl1.getType();
    						if (type.equals("start")) {
    							TransitionImpl transitionImpl = activityImpl1.getDefaultOutgoingTransition();
    							activityImpl = transitionImpl.getDestination();
    							break;
    						}
    					}
    			 }
    		 }
    	    
    	 	addList = new ArrayList<ActivityDto>();
            List list = activityImpl.getOutgoingTransitions();  
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {  
                Transition ts = (Transition) iterator.next(); 
                Activity activity = ts.getDestination();
                if("task".equals(activity.getType())){
                	ActivityDto activityDto = new ActivityDto();
                	TaskAssgineeDto assgineeDto = new TaskAssgineeDto();
                	assgineeDto.setDefinitionId(definitionId);
                	assgineeDto.setTargetName(activity.getName());
                	assgineeDto.setBeforeName(activityImpl.getName());
                	activityDto.setBeforeName(activityImpl.getName());
                	List<TProcessTaskExeConfig> tProcessTaskExeConfigs2 = jbpmService
            				.getNextTaskAssigneeConfigs2(assgineeDto);
                	
                	if(tProcessTaskExeConfigs2.size() !=0){
                		if(tProcessTaskExeConfigs2.get(0).getActShowName() != null){
                			activityDto.setActShowName(tProcessTaskExeConfigs2.get(0).getActShowName());
                		}else{
                			activityDto.setActShowName(activity.getName());
                		}
                		
            			activityDto.setDestinationName(activity.getName());
                    	activityDto.setTransitionName(ts.getName());
                		/*if(tProcessTaskExeConfigs.get(0).getActShowName() != null && ("退回".equals(ts.getName()) || "回退".equals(ts.getName()))){
                			//回退或者退回时显示别名
                			activityDto.setActShowName(tProcessTaskExeConfigs.get(0).getActShowName());
                			activityDto.setDestinationName(activity.getName());
                        	activityDto.setTransitionName(ts.getName());
                		}else{

                			//20140904 添加别名显示标志，用于提供审核通过的别名展示
                			if("1".equals(tProcessTaskExeConfigs.get(0).getActShowFlag())){
                				activityDto.setActShowName(tProcessTaskExeConfigs.get(0).getActShowName());
                			}else{
                				activityDto.setActShowName(activity.getName());
                			}
                			activityDto.setDestinationName(activity.getName());
                        	activityDto.setTransitionName(ts.getName());
                		}*/
                	}else{
                		List<TProcessTaskExeConfig> tProcessTaskExeConfigs = jbpmService
                				.getNextTaskAssigneeConfigs(assgineeDto);
                		if(tProcessTaskExeConfigs.size() !=0){
                			if(tProcessTaskExeConfigs.get(0).getActShowName() != null && ("退回".equals(ts.getName()) || "回退".equals(ts.getName()))){
                    			//回退或者退回时显示别名
                    			activityDto.setActShowName(tProcessTaskExeConfigs.get(0).getActShowName());
                    			activityDto.setDestinationName(activity.getName());
                            	activityDto.setTransitionName(ts.getName());
                    		}else{

                    			//20140904 添加别名显示标志，用于提供审核通过的别名展示
                    			if("1".equals(tProcessTaskExeConfigs.get(0).getActShowFlag())){
                    				activityDto.setActShowName(tProcessTaskExeConfigs.get(0).getActShowName());
                    			}else{
                    				activityDto.setActShowName(activity.getName());
                    			}
                    			activityDto.setDestinationName(activity.getName());
                            	activityDto.setTransitionName(ts.getName());
                    		}
                		}
                	}
                	addList.add(activityDto);  
                }else{
                	List<Transition> list2 = (List<Transition>) activity.getOutgoingTransitions();
                	if(list2.size() == 0){
                		ActivityDto activityDto = new ActivityDto();
                		TaskAssgineeDto assgineeDto = new TaskAssgineeDto();
                    	assgineeDto.setDefinitionId(definitionId);
                    	assgineeDto.setTargetName(activity.getName());
                    	List<TProcessTaskExeConfig> tProcessTaskExeConfigs = jbpmService
                				.getNextTaskAssigneeConfigs(assgineeDto);
                    	if(tProcessTaskExeConfigs.size() !=0){
                    		if(tProcessTaskExeConfigs.get(0).getActShowName() != null){
                    			activityDto.setActShowName(tProcessTaskExeConfigs.get(0).getActShowName());
                    			activityDto.setDestinationName(activity.getName());
                            	activityDto.setTransitionName(ts.getName());
                    		}else{
                    			activityDto.setActShowName(activity.getName());
                    			activityDto.setDestinationName(activity.getName());
                            	activityDto.setTransitionName(ts.getName());
                    		}
                    	}else{
                    		activityDto.setActShowName(activity.getName());
                			activityDto.setDestinationName(activity.getName());
                        	activityDto.setTransitionName(ts.getName());
                    	}
	                	addList.add(activityDto);  
                	}else{
                		for (Transition transition : list2) {
    						Activity activity2 = transition.getDestination();
    						ActivityDto activityDto = new ActivityDto();
    						TaskAssgineeDto assgineeDto = new TaskAssgineeDto();
                        	assgineeDto.setDefinitionId(definitionId);
                        	assgineeDto.setTargetName(activity2.getName());
                        	List<TProcessTaskExeConfig> tProcessTaskExeConfigs = jbpmService
                    				.getNextTaskAssigneeConfigs(assgineeDto);
                        	if(tProcessTaskExeConfigs.size() !=0){
                        		if(tProcessTaskExeConfigs.get(0).getActShowName() != null && ("退回".equals(transition.getName()) || "回退".equals(transition.getName()))){
                        			activityDto.setActShowName(tProcessTaskExeConfigs.get(0).getActShowName());
                        			activityDto.setDestinationName(activity2.getName());
            	                	activityDto.setTransitionName(transition.getName());
                        		}else{
                        			activityDto.setActShowName(activity2.getName());
                        			activityDto.setDestinationName(activity2.getName());
            	                	activityDto.setTransitionName(transition.getName());
                        		}
                        	}
    	                	addList.add(activityDto);  
    					}
                	}
                }
            }  
		} catch (Exception e) {
			  e.printStackTrace();  
        } finally {  
            envImpl.close();  
        }  
		return addList;
	}

	@Override
	public String getFormName(TaskAssgineeDto taskAssgineeDto) {
		String formName = null;
		List<HistoryProcessInstance> historyProcessInstance = historyService
					.createHistoryProcessInstanceQuery().list();
			String ss = null;
			for (HistoryProcessInstance historyProcessInstance2 : historyProcessInstance) {
				if(historyProcessInstance2.getProcessInstanceId().equals(taskAssgineeDto.getExecutionId())){
					ss =historyProcessInstance2.getProcessDefinitionId();
				}
			}
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(ss).uniqueResult();
			ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) processDefinition;
			List<Activity> activities = (List<Activity>) processDefinitionImpl.getActivities();
			for (Activity activity : activities) {
				ActivityImpl activityImpl = (ActivityImpl) activity;
				if(taskAssgineeDto.getTaskName().equals(activityImpl.getName())){
					TaskActivity taskActivity = (TaskActivity) activityImpl.getActivityBehaviour();
					TaskDefinitionImpl taskDefinitionImpl = taskActivity.getTaskDefinition();
					formName = taskDefinitionImpl.getFormResourceName();
				}
			}
			return formName;
	}

}
