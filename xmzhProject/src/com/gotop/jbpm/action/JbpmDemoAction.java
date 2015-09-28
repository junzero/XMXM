package com.gotop.jbpm.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;
import org.jbpm.jpdl.internal.activity.TaskActivity;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.json.JSONException;
import org.json.JSONObject;
import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.dto.ActivityDto;
import com.gotop.jbpm.dto.ProcessDeployDto;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.jpdl.JpdlModelDrawer;
import com.gotop.jbpm.jpdl.model.JpdlModel;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.model.TProcessConfig;
import com.gotop.jbpm.model.TProcessConfigPerson;
import com.gotop.jbpm.model.TProcessTaskAssignee;
import com.gotop.jbpm.model.TProcessTaskExeConfig;
import com.gotop.jbpm.service.ITProcessBusinessService;
import com.gotop.jbpm.service.ITProcessConfigPersonService;
import com.gotop.jbpm.service.ITProcessConfigService;
import com.gotop.jbpm.service.ITProcessTaskAssigneeService;
import com.gotop.jbpm.service.ITProcessTaskExeConfigService;
import com.gotop.jbpm.service.JbpmDemoService;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.leave.model.TApplyLeave;
import com.gotop.leave.service.ITApplyLeaveService;
import com.gotop.util.Struts2Utils;
import com.gotop.util.XmlConvert;
import com.gotop.util.string.Obj2StrUtils;
import com.gotop.util.time.TimeUtil;
import com.primeton.utils.Page;

public class JbpmDemoAction extends BaseAction {

	/**
	 * 流程配置服务
	 */
	protected ITProcessConfigService processConfigService;

	protected ITProcessConfigPersonService processConfigPersonService;

	protected ITApplyLeaveService applyLeaveService;

	protected ITFileResourceTableService fileResourceTableService;
	
	protected ITProcessTaskExeConfigService processTaskExeConfigService;

	protected ITProcessBusinessService processBusinessService;
	
	private JbpmDemoService jbpmDemoService;

	private JbpmService jbpmService;

	private ITProcessTaskAssigneeService processTaskAssigneeService;
	
	private String taskUrl;

	private String nameSpace;
	
	private String data;
	
	private String isRecordSubmit;

	/**
	 * 流程上传文件
	 */
	private File upload;
	
	/**
	 * 模板上传文件
	 */
	private File uploadTemplate;
	
	private String uploadFileName;
	
	private String uploadTemplateFileName;

	private TProcessConfig processConfig;

	private String isView;
	/**
	 * 发布流程列表
	 */
	private List<TProcessConfig> processConfigs;

	private ProcessDeployDto processDeployDto;

	private List<ProcessDefinition> processDefinitions;
	private List<ProcessInstance> processInstances;
	private List<Task> tasks;

	private String deploymentId;
	private String processInstanceId;
	private String definitionId;
	private String executionId;
	private String taskId;
	private String activityName;
	private String id;
	private String userId;
	private String reason;

	private ActivityCoordinates activityCoordinates;
	private List<ActivityCoordinates> activityHisCoordinates;

	private String target;

	private String empid;
	private String empname;
	private String orgid;
	private String orgname;

	private TaskAssgineeDto taskAssgineeDto;
	
	private List<TaskAssgineeDto> taskAssgineeDtos;

	private TApplyLeave leave;

	private List<ActivityDto> activityList;

	private List<ActivityImpl> activityImplList;

	private String pname;
	
	/**
	 * 节点执行对象主键
	 */
	private Long taskExeConfigId;
	
	public Long getTaskExeConfigId() {
		return taskExeConfigId;
	}

	public void setTaskExeConfigId(Long taskExeConfigId) {
		this.taskExeConfigId = taskExeConfigId;
	}

	public String getIsRecordSubmit() {
		return isRecordSubmit;
	}

	public void setIsRecordSubmit(String isRecordSubmit) {
		this.isRecordSubmit = isRecordSubmit;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUploadTemplateFileName() {
		return uploadTemplateFileName;
	}

	public void setUploadTemplateFileName(String uploadTemplateFileName) {
		this.uploadTemplateFileName = uploadTemplateFileName;
	}

	public File getUploadTemplate() {
		return uploadTemplate;
	}

	public void setUploadTemplate(File uploadTemplate) {
		this.uploadTemplate = uploadTemplate;
	}

	public List<TaskAssgineeDto> getTaskAssgineeDtos() {
		return taskAssgineeDtos;
	}

	public void setTaskAssgineeDtos(List<TaskAssgineeDto> taskAssgineeDtos) {
		this.taskAssgineeDtos = taskAssgineeDtos;
	}

	public TProcessConfig getProcessConfig() {
		return processConfig;
	}

	public void setProcessConfig(TProcessConfig processConfig) {
		this.processConfig = processConfig;
	}

	public ITProcessConfigPersonService getProcessConfigPersonService() {
		return processConfigPersonService;
	}

	public void setProcessConfigPersonService(
			ITProcessConfigPersonService processConfigPersonService) {
		this.processConfigPersonService = processConfigPersonService;
	}

	public ITFileResourceTableService getFileResourceTableService() {
		return fileResourceTableService;
	}

	public void setFileResourceTableService(
			ITFileResourceTableService fileResourceTableService) {
		this.fileResourceTableService = fileResourceTableService;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getTaskUrl() {
		return taskUrl;
	}

	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}

	public List<ActivityImpl> getActivityImplList() {
		return activityImplList;
	}

	public void setActivityImplList(List<ActivityImpl> activityImplList) {
		this.activityImplList = activityImplList;
	}

	public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public List<ActivityDto> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityDto> activityList) {
		this.activityList = activityList;
	}

	public TApplyLeave getLeave() {
		return leave;
	}

	public void setLeave(TApplyLeave leave) {
		this.leave = leave;
	}

	public ITApplyLeaveService getApplyLeaveService() {
		return applyLeaveService;
	}

	public void setApplyLeaveService(ITApplyLeaveService applyLeaveService) {
		this.applyLeaveService = applyLeaveService;
	}

	public TaskAssgineeDto getTaskAssgineeDto() {
		return taskAssgineeDto;
	}

	public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
		this.taskAssgineeDto = taskAssgineeDto;
	}

	public ProcessDeployDto getProcessDeployDto() {
		return processDeployDto;
	}

	public void setProcessDeployDto(ProcessDeployDto processDeployDto) {
		this.processDeployDto = processDeployDto;
	}

	public List<TProcessConfig> getProcessConfigs() {
		return processConfigs;
	}

	public void setProcessConfigs(List<TProcessConfig> processConfigs) {
		this.processConfigs = processConfigs;
	}

	public ITProcessConfigService getProcessConfigService() {
		return processConfigService;
	}

	public void setProcessConfigService(
			ITProcessConfigService processConfigService) {
		this.processConfigService = processConfigService;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public ActivityCoordinates getActivityCoordinates() {
		return activityCoordinates;
	}

	public void setActivityCoordinates(ActivityCoordinates activityCoordinates) {
		this.activityCoordinates = activityCoordinates;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ProcessInstance> getProcessInstances() {
		return processInstances;
	}

	public void setProcessInstances(List<ProcessInstance> processInstances) {
		this.processInstances = processInstances;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public List<ProcessDefinition> getProcessDefinitions() {
		return processDefinitions;
	}

	public void setProcessDefinitions(List<ProcessDefinition> processDefinitions) {
		this.processDefinitions = processDefinitions;
	}

	public JbpmDemoService getJbpmDemoService() {
		return jbpmDemoService;
	}

	public void setJbpmDemoService(JbpmDemoService jbpmDemoService) {
		this.jbpmDemoService = jbpmDemoService;
	}

	public List<ActivityCoordinates> getActivityHisCoordinates() {
		return activityHisCoordinates;
	}

	public void setActivityHisCoordinates(
			List<ActivityCoordinates> activityHisCoordinates) {
		this.activityHisCoordinates = activityHisCoordinates;
	}

	public ITProcessTaskExeConfigService getProcessTaskExeConfigService() {
		return processTaskExeConfigService;
	}

	public void setProcessTaskExeConfigService(
			ITProcessTaskExeConfigService processTaskExeConfigService) {
		this.processTaskExeConfigService = processTaskExeConfigService;
	}

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	public ITProcessBusinessService getProcessBusinessService() {
		return processBusinessService;
	}

	public void setProcessBusinessService(
			ITProcessBusinessService processBusinessService) {
		this.processBusinessService = processBusinessService;
	}

	public ITProcessTaskAssigneeService getProcessTaskAssigneeService() {
		return processTaskAssigneeService;
	}

	public void setProcessTaskAssigneeService(
			ITProcessTaskAssigneeService processTaskAssigneeService) {
		this.processTaskAssigneeService = processTaskAssigneeService;
	}

	/**
	 * 跳转流程管理界面
	 * 
	 * @abatorgenerated
	 */
	public String getProcessManager() throws Exception {
		return "processManager";
	}

	public String deployProcess() throws Exception {
		String info ="success";
		try {
			String urlstr = fileResourceTableService.getFileUpload(upload,
					uploadFileName);
			
			TFileResourceTable tFileResourceTable = new TFileResourceTable();
			tFileResourceTable.setFilePath(urlstr);
			tFileResourceTable.setFileName(uploadFileName);
			tFileResourceTable.setResourceType("10");
			String fileId = fileResourceTableService.insert(tFileResourceTable);
			
			TProcessConfig tProcessConfig = new TProcessConfig();
			
			TFileResourceTable templateFile = new TFileResourceTable();
			if(uploadTemplate != null && uploadTemplateFileName != null){
				//模板文件
				String urlstrTemplate = fileResourceTableService.getFileUpload(uploadTemplate,
						uploadTemplateFileName);
				templateFile.setFilePath(urlstrTemplate);
				templateFile.setFileName(uploadTemplateFileName);
				fileResourceTableService.insert(templateFile);
				tProcessConfig.setFileIds(String.valueOf(templateFile.getFileId()));
			}else{
				tProcessConfig.setFileIds("");
			}
		
		/*	TFileResourceTable tFileResourceTable2 = fileResourceTableService
					.getFileResource(fileId);*/

			// 根据路径加载配置文件
			// 部署流程
			ProcessDefinition processDefinition = jbpmService
					.deployProcess(tFileResourceTable.getFilePath());

	
			tProcessConfig.setDefinitionId(processDefinition.getId());
			tProcessConfig.setRoleOrgPerson(this.processDeployDto
					.getDeployType());
			tProcessConfig.setProcessName(this.processDeployDto
					.getProcessName());
			tProcessConfig.setBusinessType(this.processDeployDto.getBusinessType());
			tProcessConfig.setOrderNo(this.processDeployDto.getOrderNo());
			// 流程状态-可用
			tProcessConfig.setState(this.processDeployDto.getProcessState());
			tProcessConfig.setUploadOrg(this.getCurrentOnlineUser().getOrgid());
			//流程上传时间
			tProcessConfig.setUploadTime(TimeUtil.today()+TimeUtil.now());
			
			tProcessConfig.setUserId(this.getCurrentOnlineUser().getEmpid());

			//记录配置文件id
			//tProcessConfig.setFileIds(String.valueOf(templateFile.getFileId()));
			
			Long tProcessConfigId = this.processConfigService
					.insert(tProcessConfig);
			tFileResourceTable.setResourceId(tProcessConfigId);
			this.fileResourceTableService.update(tFileResourceTable);
			
			// 保存流程配置与流程发布人员配置
			if (this.processDeployDto.getDeployRange() != null) {
				String[] empIdArray = this.processDeployDto.getDeployRange()
						.split(",");
				for (String empId : empIdArray) {
					TProcessConfigPerson tProcessConfigPerson = new TProcessConfigPerson();
					tProcessConfigPerson.setProcessConfigId(tProcessConfigId);
					tProcessConfigPerson.setRelationId(String.valueOf(empId));
					this.processConfigPersonService
							.insert(tProcessConfigPerson);
				}
			}
		} catch (Exception e) {
				info="fails";
				log.error("[发布消息失败！]", e);
				throw e;
			}finally{	
			}
			Struts2Utils.renderText(info);
			return null;
		// return "jbpm_process_deploy_list";
	}

	public String getProcessInstancesList() {
		List<ProcessInstance> processInstances = jbpmDemoService
				.getProcessInstances();
		this.page = new Page();
		this.page.setCount(processInstances.size());
		this.setPage(page);
		this.setProcessInstances(processInstances);
		return "jbpm_instance_list";
	}

	public void deleteProcessDefinition() throws Exception {
		StringBuffer buffer = new StringBuffer(100);
		try {
			buffer.append("<root><data><rtun>");
			jbpmDemoService.deleteProcess(deploymentId);
			buffer.append("success");
		} catch (Exception e) {
			buffer.append("fairl");
			log.error("[删除流程部署失败]", e);
			throw e;
		} finally {
			buffer.append("</rtun></data></root>");
			this.getResponse().getWriter().write(buffer.toString());
		}
	}

	public String findPersonalTasksList() {
		String empId = String.valueOf(this.getCurrentOnlineUser().getEmpid());
		List<Task> tasksList = jbpmDemoService.findPersonalTasks(empId);
		this.page = new Page();
		this.page.setCount(tasksList.size());
		this.setPage(page);
		this.setTasks(tasksList);
		return "jbpm_task_list";
	}

	/**
	 * 跳转到查看流程进度的页面
	 * 
	 * @return
	 */
	public String viewTask() {
		ExecutionService executionService = jbpmDemoService
				.getExecutionService();
		RepositoryService repositoryService = jbpmDemoService
				.getRepositoryService();
		HistoryService historyService = jbpmDemoService.getHistoryService();
		ProcessInstance processInstance = null;
		HistoryProcessInstance historyProcessInstance = null;
		Set<String> activityNames = null;
		Execution execution = executionService.findExecutionById(executionId);
		if (execution == null) {
			historyProcessInstance = historyService
					.createHistoryProcessInstanceQuery()
					.processInstanceId(executionId).uniqueResult();
			List<HistoryActivityInstance> historyActivityInstances = historyService
					.createHistoryActivityInstanceQuery()
					.executionId(executionId).list();
			activityHisCoordinates = new ArrayList<ActivityCoordinates>();
			for (Iterator<HistoryActivityInstance> iter = historyActivityInstances
					.iterator(); iter.hasNext();) {
				activityHisCoordinates
						.add(repositoryService.getActivityCoordinates(
								historyProcessInstance.getProcessDefinitionId(),
								iter.next().getActivityName()));
			}
			this.setActivityHisCoordinates(activityHisCoordinates);
		} else {
			processInstance = executionService
					.findProcessInstanceById(execution.getProcessInstance()
							.getId());
			activityNames = processInstance.findActiveActivityNames();
			activityCoordinates = repositoryService.getActivityCoordinates(
					processInstance.getProcessDefinitionId(), activityNames
							.iterator().next());
			this.setActivityCoordinates(activityCoordinates);
		}
		this.setExecutionId(executionId);
		return "jbpm_task_view";
	}

	/**
	 * 我的流程列表-跳转到查看流程图的页面
	 * @throws Exception 
	 */
	public String viewProcess() throws Exception {
		this.setDefinitionId(definitionId);
		return "jbpm_process_view";
	/*	RepositoryService repositoryService = jbpmDemoService
				.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(definitionId).uniqueResult();
		jbpmService.getFilePath(processDefinition.getDeploymentId(), "xml");
		InputStream inputStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),
				processDefinition.getImageResourceName());
		PrintWriter pw = null;
		if (inputStream == null) {
			try {
				pw = this.getResponse().getWriter();
				pw.write("error");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				pw.close();
			}
		} else {
			byte[] b = new byte[1024];
			int len = -1;
			ServletOutputStream sos = null;
			try {
				sos = this.getResponse().getOutputStream();
				while ((len = inputStream.read(b, 0, 1024)) != -1) {
					sos.write(b, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (sos != null) {
					try {
						sos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}*/
	}
	
	/**
	 * 查看流程图
	 * @throws Exception
	 */
	public void view() throws Exception {
		RepositoryService repositoryService = jbpmDemoService
				.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(definitionId).uniqueResult();
		jbpmService.getFilePath(processDefinition.getDeploymentId(), "xml");
		InputStream inputStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),
				processDefinition.getImageResourceName());
		PrintWriter pw = null;
		if (inputStream == null) {
			try {
				pw = this.getResponse().getWriter();
				pw.write("error");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				pw.close();
			}
		} else {
			byte[] b = new byte[1024];
			int len = -1;
			ServletOutputStream sos = null;
			try {
				sos = this.getResponse().getOutputStream();
				while ((len = inputStream.read(b, 0, 1024)) != -1) {
					sos.write(b, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (sos != null) {
					try {
						sos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
	}

	public void toTask() throws IOException {
		Task task = jbpmDemoService.getTaskById(taskId);
		TaskService taskService = jbpmDemoService.getTaskService();
		this.setUserId(String.valueOf(this.getCurrentOnlineUser().getEmpid()));
		this.setTaskId(taskId);
		this.getResponse().sendRedirect(task.getFormResourceName());
	}

	public String completeTask() throws UnsupportedEncodingException {
		Task task = jbpmDemoService.getTaskById(taskId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("reason", reason);
		if (target != null) {
			if ("agree".equals(target)) {
				target = "同意";
			}
			if ("disagree".equals(target)) {
				target = "不同意";
			}
		}
		jbpmDemoService.completeTask(task.getId(), target, params);
		return "jbpm_task_list";
	}

	/**
	 * 我的待办列表-输出和展示流程进度图
	 */
	public void viewTaskProcess() {
		RepositoryService repositoryService = jbpmDemoService
				.getRepositoryService();
		ExecutionService executionService = jbpmDemoService
				.getExecutionService();
		HistoryService historyService = jbpmDemoService.getHistoryService();
		Execution execution = executionService.findExecutionById(executionId);

		ProcessDefinition processDefinition = null;
		if (execution == null) {
			HistoryProcessInstance historyProcessInstance = historyService
					.createHistoryProcessInstanceQuery()
					.processInstanceId(executionId).uniqueResult();
			processDefinition = repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(
							historyProcessInstance.getProcessDefinitionId())
					.uniqueResult();
		} else {
			processDefinition = repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(execution.getProcessDefinitionId())
					.uniqueResult();
		}
		InputStream inputStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),
				processDefinition.getImageResourceName());
		/*
		 * byte[] b = new byte[1024]; int len = -1; try { while((len =
		 * inputStream.read(b, 0, 1024)) != -1){
		 * this.getResponse().getOutputStream().write(b,0,len); } } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

		PrintWriter pw = null;
		if (inputStream == null) {
			try {
				pw = this.getResponse().getWriter();
				pw.write("error");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				pw.close();
			}
		} else {
			byte[] b = new byte[1024];
			int len = -1;
			ServletOutputStream sos = null;
			try {
				sos = this.getResponse().getOutputStream();
				while ((len = inputStream.read(b, 0, 1024)) != -1) {
					sos.write(b, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (sos != null) {
					try {
						sos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
	}

	/**
	 * 我的流程列表-发起流程
	 * 发起流程时同时发起实例
	 * 
	 * @return
	 * @throws IOException
	 */
	public String startProcess1() throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user",
				String.valueOf(this.getCurrentOnlineUser().getEmpid()));
		// 获取流程实例
		ProcessInstance processInstance = jbpmDemoService.startProcess(
				definitionId, params);
		// 查找可用流程节点名称
		Set<String> activityNames = processInstance.findActiveActivityNames();
		Task task = null;
		for (String activityNameStr : activityNames) {
			// 获取节点实例
			task = jbpmDemoService.getTask(activityNameStr, processInstance);
		}
		// this.setProcessInstanceId();
		// this.setTaskId();
		this.taskAssgineeDto = new TaskAssgineeDto();
		this.taskAssgineeDto.setExecutionId(processInstance.getId());

		this.taskAssgineeDto.setNextTaskId(task.getId());
		this.setTaskAssgineeDto(taskAssgineeDto);
		taskUrl = task.getFormResourceName();
		nameSpace = taskUrl.substring(0, taskUrl.indexOf("/", 1));
		String taskUrl1 = taskUrl.substring(nameSpace.length(),
				taskUrl.length());
		String ss = getTaskUrlBuffer(taskAssgineeDto);
		this.setTaskUrl(taskUrl1 + ss);
		this.setNameSpace(nameSpace);
		return "success";
	}

	public String toTaskActivity() {
		this.setProcessInstanceId(processInstanceId);
		return "task_activity";
	}

	/**
	 * 发布流程列表
	 * 
	 * @return
	 */
	public String queryProcessDeployList() {
		List<TProcessConfig> list = processConfigService
				.queryProcessConfigList(processConfig, this.getPage());
		this.setProcessConfigs(list);
		return "jbpm_process_deploy_list";
	}

	/**
	 * 跳转到发布流程页面
	 * 
	 * @return
	 */
	public String toProcessDeploy() {
		return "jbpm_process_deploy";
	}

	/**
	 * 我的待办列表-办理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String handle() throws Exception {
		String taskId = this.taskAssgineeDto.getNextTaskId();
		Task task = jbpmDemoService.getTaskById(taskId);
		taskAssgineeDto.setTaskName(task.getActivityName());
		this.setTaskAssgineeDto(taskAssgineeDto);
		taskUrl = task.getFormResourceName();
		nameSpace = taskUrl.substring(0, taskUrl.indexOf("/", 1));
		String taskUrl1 = taskUrl.substring(nameSpace.length(),
				taskUrl.length());
		
		ProcessInstance instance = jbpmService.getExecutionService().createProcessInstanceQuery().processInstanceId(taskAssgineeDto.getExecutionId()).uniqueResult();
		TProcessTaskExeConfig tProcessTaskExeConfig = this.processTaskExeConfigService.getTaskExeConfig(instance.getProcessDefinitionId(),task.getName());
		taskAssgineeDto.setTaskConfigType(tProcessTaskExeConfig.getTaskAssType());
		//20140906
		taskAssgineeDto.setTaskName(null);
		String ss = getTaskUrlBuffer(taskAssgineeDto);
		this.setNameSpace(nameSpace);
		/**
		 * 20140904督办窗口
		 */
		String positionId = Obj2StrUtils.join(this.getCurrentOnlineUser().getPositionId(), String.class, ",");
		Long a = this.getCurrentOnlineUser().getEmpid();
		if(!this.getCurrentOnlineUser().getEmpid().equals(taskAssgineeDto.getPreTaskAssingee()))
			this.setTaskUrl(taskUrl1 + ss+"&supFlag=1");
		else
			this.setTaskUrl(taskUrl1 + ss);
		return "success";
	}

	/**
	 * 拼接获取task的表单url参数
	 * 
	 * @param taskAssgineeDto
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getTaskUrlBuffer(TaskAssgineeDto taskAssgineeDto)
			throws UnsupportedEncodingException {
		StringBuffer buffer = new StringBuffer(1000);
		buffer.append("?");
		if (taskAssgineeDto != null) {
			if(taskAssgineeDto.getDefinitionId()!=null){
				buffer.append("taskAssgineeDto.definitionId=");
				buffer.append(taskAssgineeDto.getDefinitionId());
				buffer.append("&");
			}
			if (taskAssgineeDto.getBusinessKey() != null) {
				buffer.append("taskAssgineeDto.businessKey=");
				buffer.append(taskAssgineeDto.getBusinessKey());
				buffer.append("&");
			}
			if (taskAssgineeDto.getBusinessType() != null) {
				buffer.append("taskAssgineeDto.businessType=");
				buffer.append(taskAssgineeDto.getBusinessType());
				buffer.append("&");
			}
			if (taskAssgineeDto.getExecutionId() != null) {
				buffer.append("taskAssgineeDto.executionId=");
				buffer.append(taskAssgineeDto.getExecutionId());
				buffer.append("&");
			}
			if (taskAssgineeDto.getNextTaskId() != null) {
				buffer.append("taskAssgineeDto.nextTaskId=");
				buffer.append(taskAssgineeDto.getNextTaskId());
				buffer.append("&");
			}
			if (taskAssgineeDto.getPreTaskAssingee() != null) {
				buffer.append("taskAssgineeDto.preTaskAssingee=");
				buffer.append(taskAssgineeDto.getPreTaskAssingee());
				buffer.append("&");
			}
			if (taskAssgineeDto.getPreTaskId() != null) {
				buffer.append("taskAssgineeDto.preTaskId=");
				buffer.append(taskAssgineeDto.getPreTaskId());
				buffer.append("&");
			}
			if (taskAssgineeDto.getPreTaskOrg() != null) {
				buffer.append("taskAssgineeDto.preTaskOrg=");
				buffer.append(taskAssgineeDto.getPreTaskOrg());
				buffer.append("&");
			}
			if (taskAssgineeDto.getPreTaskTime() != null) {
				buffer.append("taskAssgineeDto.preTaskTime=");
				buffer.append(taskAssgineeDto.getPreTaskTime());
				buffer.append("&");
			}
			if (taskAssgineeDto.getTaskAssingee() != null) {
				buffer.append("taskAssgineeDto.taskAssingee=");
				buffer.append(taskAssgineeDto.getTaskAssingee());
				buffer.append("&");
			}
			if (taskAssgineeDto.getTaskName() != null) {
				buffer.append("taskAssgineeDto.taskName=");
				buffer.append(taskAssgineeDto.getTaskName());
				buffer.append("&");
			}
			if (taskAssgineeDto.getProcessTaskAssigneeId()!= null) {
				buffer.append("taskAssgineeDto.processTaskAssigneeId=");
				buffer.append(taskAssgineeDto.getProcessTaskAssigneeId());
				buffer.append("&");
			}
			if (taskAssgineeDto.getParentId()!= null) {
				buffer.append("taskAssgineeDto.parentId=");
				buffer.append(taskAssgineeDto.getParentId());
				buffer.append("&");
			}
			if (taskAssgineeDto.getIsChild()!= null) {
				buffer.append("taskAssgineeDto.isChild=");
				buffer.append(taskAssgineeDto.getIsChild());
				buffer.append("&");
			}
			if (taskAssgineeDto.getTemplateFileIds()!= null) {
				buffer.append("taskAssgineeDto.templateFileIds=");
				buffer.append(taskAssgineeDto.getTemplateFileIds());
				buffer.append("&");
			}
			if (taskAssgineeDto.getProcessName()!= null) {
				buffer.append("taskAssgineeDto.processName=");
				//中文参数传递给业务时进行转码
				buffer.append(java.net.URLEncoder.encode(taskAssgineeDto.getProcessName(), "utf-8"));
				buffer.append("&");
			}
			if (taskAssgineeDto.getTaskConfigType()!= null) {
				buffer.append("taskAssgineeDto.taskConfigType=");
				buffer.append(taskAssgineeDto.getTaskConfigType());
				buffer.append("&");
			}
		}
		String string = buffer.substring(0, buffer.length() - 1);
		return string;
	}

	public String toNextTaskConfig() {
		activityList = this.jbpmDemoService.getNextTaskList(taskAssgineeDto);
		this.setActivityList(activityList);
		this.setTaskAssgineeDto(taskAssgineeDto);
		return "task_person_assigner";
	}

	/**
	 * 节点人员选择
	 * @return
	 * @throws IOException
	 */
	public String getNextTaskAssigneeConfig() throws IOException {
		ExecutionService executionService = jbpmService.getExecutionService();
		String targetName = java.net.URLDecoder.decode(
				taskAssgineeDto.getTargetName(), "utf-8");
		String beforeName = java.net.URLDecoder.decode(
				taskAssgineeDto.getBeforeName(), "utf-8");
		//获取流程定义id
		String processDefinitionId = null;
		 if(taskAssgineeDto.getExecutionId() != null && !"".equals(taskAssgineeDto.getExecutionId())){
			 ProcessInstance processInstance = executionService
						.createProcessInstanceQuery()
						.processInstanceId(this.taskAssgineeDto.getExecutionId())
						.uniqueResult();
				
				this.taskAssgineeDto.setDefinitionId(processInstance
						.getProcessDefinitionId());
				processDefinitionId = processInstance.getProcessDefinitionId();
		 }else{
			 if(taskAssgineeDto.getDefinitionId()!=null && !"".equals(taskAssgineeDto.getDefinitionId())){
				 processDefinitionId = taskAssgineeDto.getDefinitionId();
			 }
		 }
		this.taskAssgineeDto.setTargetName(targetName);
		this.taskAssgineeDto.setBeforeName(beforeName);
	
		ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) jbpmService
				.getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).uniqueResult();
		List<ActivityImpl> list = (List<ActivityImpl>) processDefinitionImpl.getActivities();
		
		String isEnd = null;
		for (ActivityImpl activityImpl : list) {
			if(targetName.equals(activityImpl.getName())){
				if("end".equals(activityImpl.getType())){
					isEnd = "end";
				}
			}
		}
		List<TProcessTaskExeConfig> tProcessTaskExeConfigs = null;
		tProcessTaskExeConfigs = jbpmService
				.getNextTaskAssigneeConfigs2(this.taskAssgineeDto);
		if(tProcessTaskExeConfigs.size() == 0){
			tProcessTaskExeConfigs = jbpmService
					.getNextTaskAssigneeConfigs(this.taskAssgineeDto);
		}
		//获取当前节点的执行对象
		//判断当前节点是否记录提交人
		TProcessTaskExeConfig preTaskExe = processTaskExeConfigService.getTaskExeConfig(processDefinitionId, beforeName);
		if(preTaskExe != null){
			if("1".equals(preTaskExe.getIsRecordSubmit())){
				//是
				this.setTaskExeConfigId(preTaskExe.getId());
			}else{
				this.setTaskExeConfigId(0l);
			}
		}else{
			this.setTaskExeConfigId(0l);
		}
		
		//判断目标节点是否记录提交人
		TProcessTaskExeConfig nextTaskExe = processTaskExeConfigService.getTaskExeConfig(processDefinitionId, targetName);
		List<TProcessTaskAssignee> processTaskAssignees = null;
		
		List<String> empIds = new ArrayList<String>();
		List<String> empNames = new ArrayList<String>();
		String taskType = null;
		String dynamicOrgIds="";
		boolean flag = false;
		if(nextTaskExe != null){
			if("1".equals(nextTaskExe.getIsRecordSubmit())){
				//是
				//判断是否重新选择
				if(taskAssgineeDto.getReSelectFlag() != null && "1".equals(taskAssgineeDto.getReSelectFlag())){
					//是
					
				}else{
					//否
					//根据nextTaskExe主键找到提交人id
					processTaskAssignees = processTaskAssigneeService.selectByTaskExeConfigId(nextTaskExe.getId(),taskAssgineeDto.getExecutionId());
					if(processTaskAssignees.size() != 0){
						taskAssgineeDto.setObjId(processTaskAssignees.get(0).getPreTaskAssingee());
						List<Map<String, Object>> objs = jbpmService.getEmp1(taskAssgineeDto);
						if (objs.size() != 0) {
							for (Map<String, Object> map : objs) {
								empIds.add(String.valueOf(map.get("objId")));
								empNames.add((String) map.get("objName"));
							}
						}
						taskType="11";
						flag = true;
					}
				}
				
			}
		}
		
		if(flag == false){
			if(!"end".equals(isEnd)){
				// 根据节点执行配置的类型区分
				if (tProcessTaskExeConfigs.size() != 0) {
					for (TProcessTaskExeConfig tProcessTaskExeConfig : tProcessTaskExeConfigs) {
						if ("02".equals(tProcessTaskExeConfig.getTaskAssType())) {
							// 动态机构(主办和辅办)
							dynamicOrgIds = this.taskAssgineeDto
									.getDynamicOrgIds();
							String[] dynamicOrgIdArray = dynamicOrgIds.split(",");
							if (dynamicOrgIdArray.length != 0) {
								for (String dynamicOrgId : dynamicOrgIdArray) {
									this.taskAssgineeDto
											.setTaskExeAssginee(dynamicOrgId);
									// 查找对应人员
									List<Map<String, Object>> empids = jbpmService
											.getEmpOrgByPosition(taskAssgineeDto);
									if (empids.size() != 0) {
										for (Map<String, Object> map : empids) {
											empIds.add(String.valueOf(map.get("empId")));
											empNames.add((String) map.get("empName"));
										}
									}
								}

							}
							taskType="02";
						} 
						if ("07".equals(tProcessTaskExeConfig.getTaskAssType())) {
							
							// 主要负责人(主办和辅办)
							dynamicOrgIds = this.taskAssgineeDto
									.getDynamicOrgIds();
							String[] dynamicOrgIdArray = dynamicOrgIds.split(",");
							if (dynamicOrgIdArray.length != 0) {
								for (String dynamicOrgId : dynamicOrgIdArray) {
									this.taskAssgineeDto
											.setTaskExeAssginee(dynamicOrgId);
									// 查找对应人员
									List<Map<String, Object>> empids = jbpmService
											.getEmpOrgByMain(taskAssgineeDto);
									if (empids.size() != 0) {
										for (Map<String, Object> map : empids) {
											empIds.add(String.valueOf(map.get("empId")));
											empNames.add((String) map.get("empName"));
										}
									}
								}

							}
							taskType="07";
						}  
						if ("08".equals(tProcessTaskExeConfig.getTaskAssType())) {
							//回退时主要负责人可选显示部门
							// 主要负责人(主办和辅办)
							dynamicOrgIds = this.taskAssgineeDto
									.getDynamicOrgIds();
							String[] dynamicOrgIdArray = dynamicOrgIds.split(",");
							if (dynamicOrgIdArray.length != 0) {
								for (String dynamicOrgId : dynamicOrgIdArray) {
									this.taskAssgineeDto
											.setTaskExeAssginee(dynamicOrgId);
									// 查找对应人员
									List<Map<String, Object>> empids = jbpmService
											.getEmpOrgByMain(taskAssgineeDto);
									if (empids.size() != 0) {
										for (Map<String, Object> map : empids) {
											empIds.add(String.valueOf(map.get("empId")));
											empNames.add((String) map.get("empName"));
										}
									}
								}

							}
							taskType="08";
						}if ("09".equals(tProcessTaskExeConfig.getTaskAssType())) {
							//部门会签
							//页面用户选择机构和主要负责人
							taskType="09";
						}else if ("03".equals(tProcessTaskExeConfig.getTaskAssType())) {
							// 指定部门（部门领导）
							// 获取人员id
							String taskAssId = tProcessTaskExeConfig.getTaskAssId();
							this.taskAssgineeDto.setTaskExeAssginee(taskAssId);
							// 查找对应人员
							List<Map<String, Object>> empids = jbpmService
									.getEmpOrgByPosition(taskAssgineeDto);
							if (empids.size() != 0) {
								for (Map<String, Object> map : empids) {
									empIds.add(String.valueOf(map.get("empId")));
									empNames.add((String) map.get("empName"));
								}
							}
							taskType="03";
						} else if ("10".equals(tProcessTaskExeConfig.getTaskAssType())) {
							// 指定部门的所有人
							// 获取人员id
							String taskAssId = tProcessTaskExeConfig.getTaskAssId();
							this.taskAssgineeDto.setTaskExeAssginee(taskAssId);
							// 查找对应人员
							List<Map<String, Object>> empids = jbpmService
									.getEmpOrg(taskAssgineeDto);
							if (empids.size() != 0) {
								for (Map<String, Object> map : empids) {
									empIds.add(String.valueOf(map.get("empId")));
									empNames.add((String) map.get("empName"));
								}
							}
							taskType="10";
						} else if ("01".equals(tProcessTaskExeConfig.getTaskAssType())) {
							// 指定岗位(分行领导，行领导)
							// 获取岗位id
							String taskAssId = tProcessTaskExeConfig.getTaskAssId();
							this.taskAssgineeDto.setTaskExeAssginee(taskAssId);
							// 查找对应岗位和起草人机构的
							List<Map<String, Object>> empids = jbpmService
									.getEmpPosition(taskAssgineeDto);
							if (empids.size() != 0) {
								for (Map<String, Object> map : empids) {
									if(this.taskAssgineeDto.getTaskAssingee() != null){
										String taskAssingee = String.valueOf(this.taskAssgineeDto.getTaskAssingee());
										//获取上一节点提交人id
										
										if(String.valueOf(map.get("empId")).equals(taskAssingee)){
											empIds.add(String.valueOf(map.get("empId")));
											empNames.add((String) map.get("empName"));
										}
									}else{
										empIds.add(String.valueOf(map.get("empId")));
										empNames.add((String) map.get("empName"));
									}
								}
							}
							taskType="01";
						} else if ("04".equals(tProcessTaskExeConfig.getTaskAssType())) {
							// 起草人所属机构的部门领导
							String taskAssId = tProcessTaskExeConfig.getTaskAssId();
							this.taskAssgineeDto.setTaskExeAssginee(taskAssId);
							// 查找对应岗位和起草人机构的
							List<Map<String, Object>> empids = jbpmService
									.getEmpPositionByOrg(taskAssgineeDto);
							if (empids.size() != 0) {
								for (Map<String, Object> map : empids) {
									empIds.add(String.valueOf(map.get("empId")));
									empNames.add((String) map.get("empName"));
								}
							}
							taskType="04";
						} else if ("05".equals(tProcessTaskExeConfig.getTaskAssType())) {
							// 起草人
							// String taskAssId = tProcessTaskExeConfig.getTaskAssId();
							// this.taskAssgineeDto.setTaskExeAssginee(taskAssId);
							// 查找对应人员
							// if(tProcessTaskExeConfig.getTaskAssId().equals(String.valueOf(taskAssgineeDto.getBeginAssingee()))){
							taskAssgineeDto.setDefinitionId(tProcessTaskExeConfig
									.getDefinitionId());
							List<Map<String, Object>> empids = jbpmService
									.getEmp(taskAssgineeDto);
							if (empids.size() != 0) {
								for (Map<String, Object> map : empids) {
									empIds.add(String.valueOf(map.get("empId")));
									empNames.add((String) map.get("empName"));
								}
							}
					//		 }
							taskType="05";
						}
						/*else if("06".equals(tProcessTaskExeConfig.getTaskAssType())){
							taskType="06";
							//最后节点
						}*/
						
					}
				} else {

				}
			}else{
				
			}
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("<root>");
		buffer.append("<taskExeConfigId>");
		buffer.append(taskExeConfigId);
		buffer.append("</taskExeConfigId>");
		buffer.append("<taskType>");
		buffer.append(taskType);
		buffer.append("</taskType>");
		buffer.append("<dynamicOrgIds>");
		buffer.append(dynamicOrgIds);
		buffer.append("</dynamicOrgIds>");
		buffer.append("<isEnd>");
		buffer.append(isEnd);
		buffer.append("</isEnd>");
		if (empIds.size() != 0 && empNames.size() != 0) {
			buffer.append("<empIds>");
			try {
				StringBuffer sb = new StringBuffer();

				for (int i = 0; i < empIds.size(); i++) {
					sb.append(empIds.get(i));
					sb.append(",");
				}
				String s = sb.toString();
				s = s.substring(0, s.length() - 1);
				buffer.append(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
			buffer.append("</empIds>");
			buffer.append("<empNames>");
			try {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < empNames.size(); i++) {
					sb.append(empNames.get(i));
					sb.append(",");
				}
				String s = sb.toString();
				s = s.substring(0, s.length() - 1);
				buffer.append(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
			buffer.append("</empNames>");
		}
		buffer.append("</root>");
		this.write(buffer.toString());
		return null;
	}

	/**
	 * 流程管理-流程节点配置列表
	 * @return
	 * @throws Exception 
	 */
	public String toProcessTaskConfig() throws Exception {
		String definitionId = this.taskAssgineeDto.getDefinitionId();
		RepositoryService repositoryService = jbpmService
				.getRepositoryService();
		ProcessDefinitionImpl processDefinition = (ProcessDefinitionImpl) repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(definitionId).uniqueResult();
		List<TaskAssgineeDto> activityList = new ArrayList<TaskAssgineeDto>();
		List list = processDefinition.getActivities();
		for (int i = 0; i < list.size(); i++) {
			ActivityImpl activityImpl = (ActivityImpl) list.get(i);
			String type = activityImpl.getType();
			if (type.equals("task")) {
	/*			TaskActivity taskActivity = (TaskActivity) activityImpl
						.getActivityBehaviour();*/
				TaskAssgineeDto assgineeDto = new TaskAssgineeDto();
				TProcessTaskExeConfig tProcessTaskExeConfig = this.processTaskExeConfigService.getTaskExeConfig(definitionId,activityImpl.getName());
				if(tProcessTaskExeConfig == null){
					assgineeDto.setDefinitionId(definitionId);
					assgineeDto.setTaskName(activityImpl.getName());
					
				}else{
					assgineeDto.setDefinitionId(definitionId);
					assgineeDto.setTaskName(activityImpl.getName());
					taskAssgineeDto.setObjId(tProcessTaskExeConfig.getTaskAssId());
					if("01".equals(tProcessTaskExeConfig.getTaskAssType())){
						//指定岗位
						List<Map<String, Object>> objs = jbpmService.getPosition(taskAssgineeDto);
						if (objs.size() != 0) {
							for (Map<String, Object> map : objs) {
								assgineeDto.setObjName((String) map.get("objName"));
							}
						}
					}else if("03".equals(tProcessTaskExeConfig.getTaskAssType())){
						//指定部门
						List<Map<String, Object>> objs = jbpmService.getOrg(taskAssgineeDto);
						if (objs.size() != 0) {
							for (Map<String, Object> map : objs) {
								assgineeDto.setObjName((String) map.get("objName"));
							}
						}
					}else {
						assgineeDto.setObjName(this.jbpmService.getDictEntry("ZHPT_TASK_CONFIG_TYPE",tProcessTaskExeConfig.getTaskAssType()).getDictname());
					}
					
				}
				activityList.add(assgineeDto);
				//activityList.add(activityImpl);
			}
			/*
			 * if(type.equals("start")){ StartActivity activityBehaviour =
			 * (StartActivity)activityImpl.getActivityBehaviour();
			 * activityList.add(activityImpl); }
			 */
/*		if (type.equals("end")) {
				EndActivity activityBehaviour = (EndActivity) activityImpl
						.getActivityBehaviour();
				activityList.add(activityImpl);
			}*/
		}

		this.setTaskAssgineeDtos(activityList);
		return "process_task_config_list";
	}

	public String toTaskConfig() {
		String definitionId = taskAssgineeDto.getDefinitionId();
		String taskName = taskAssgineeDto.getTaskName();
		this.taskAssgineeDto.setDefinitionId(definitionId);
		this.taskAssgineeDto.setTaskName(taskName);
		//查询数据库判断是否有
		TProcessTaskExeConfig tProcessTaskExeConfig = this.processTaskExeConfigService.getTaskExeConfig(definitionId,taskName);
		if(tProcessTaskExeConfig != null){
			if(tProcessTaskExeConfig.getIsRecordSubmit() == null){
				this.setIsRecordSubmit("");
			}else{
				this.setIsRecordSubmit(tProcessTaskExeConfig.getIsRecordSubmit());
			}
			
			this.taskAssgineeDto.setBusinessKey(tProcessTaskExeConfig.getId());
			this.taskAssgineeDto.setObjId(tProcessTaskExeConfig.getTaskAssId());
			this.taskAssgineeDto.setTaskConfigType(tProcessTaskExeConfig.getTaskAssType());
			if("01".equals(tProcessTaskExeConfig.getTaskAssType())){
				//指定岗位
				List<Map<String, Object>> objs = jbpmService.getPosition(taskAssgineeDto);
				if (objs.size() != 0) {
					for (Map<String, Object> map : objs) {
						this.taskAssgineeDto.setObjName((String) map.get("objName"));
					}
				}
			}else if("03".equals(tProcessTaskExeConfig.getTaskAssType()) || "10".equals(tProcessTaskExeConfig.getTaskAssType())){
				//指定部门
				List<Map<String, Object>> objs = jbpmService.getOrg(taskAssgineeDto);
				if (objs.size() != 0) {
					for (Map<String, Object> map : objs) {
						this.taskAssgineeDto.setObjName((String) map.get("objName"));
					}
				}
			}
		}
		this.setTaskAssgineeDto(taskAssgineeDto);
		return "process_task_config";
	}

	/**
	 * 流程管理-流程节点配置保存
	 * @return
	 * @throws Exception
	 */
	public String saveTaskConfig() throws Exception {
		String info ="success";
		TProcessTaskExeConfig tProcessTaskExeConfig = null;
		String taskConfigType = taskAssgineeDto.getTaskConfigType();
		if(taskAssgineeDto.getBusinessKey() != null){
			tProcessTaskExeConfig = processTaskExeConfigService.getProcessTaskExeConfigById(this.taskAssgineeDto.getBusinessKey());
			tProcessTaskExeConfig.setTaskAssId(null);
		}else{
			tProcessTaskExeConfig = new TProcessTaskExeConfig();
		}
		if(isRecordSubmit == null){
			tProcessTaskExeConfig.setIsRecordSubmit("");
		}else{
			tProcessTaskExeConfig.setIsRecordSubmit(isRecordSubmit);
		}
		tProcessTaskExeConfig.setTaskAssType(taskConfigType);
		tProcessTaskExeConfig.setDefinitionId(taskAssgineeDto.getDefinitionId());
		tProcessTaskExeConfig.setActivityName(taskAssgineeDto.getTaskName());
		if ("01".equals(taskConfigType)) {
			// 指定岗位
			tProcessTaskExeConfig.setTaskAssId(taskAssgineeDto.getObjId());
		} else if ("02".equals(taskConfigType)) {
			// 动态机构
		} else if ("03".equals(taskConfigType)) {
			// 指定部门领导
			tProcessTaskExeConfig.setTaskAssId(taskAssgineeDto.getObjId());
		}  else if ("10".equals(taskConfigType)) {
			// 指定部门所有人
			tProcessTaskExeConfig.setTaskAssId(taskAssgineeDto.getObjId());
		} else if ("04".equals(taskConfigType)) {
			// 起草人所属机构领导
			tProcessTaskExeConfig.setTaskAssId("3");
		} else if ("05".equals(taskConfigType)) {
			// 起草人
		}
		else if ("09".equals(taskConfigType)) {
			// 部门会签
		}
		try {
			if(taskAssgineeDto.getBusinessKey() != null){
				this.processTaskExeConfigService.update(tProcessTaskExeConfig);
			}else{
				this.processTaskExeConfigService.insert(tProcessTaskExeConfig);
			}
		} catch (Exception e) {
			info="fails";
			log.error("[流程节点配置保存失败！]", e);
			throw e;
		}finally{	
		}
		Struts2Utils.renderText(info);
		return null;
	}
	
	/**
	 * 我的已办-查看详情
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String viewBussinessDetail() throws UnsupportedEncodingException{
		this.setTaskAssgineeDto(taskAssgineeDto);
		taskUrl = this.jbpmDemoService.getFormName(taskAssgineeDto);
		nameSpace = taskUrl.substring(0, taskUrl.indexOf("/", 1));
		String taskUrl1 = taskUrl.substring(nameSpace.length(),
				taskUrl.length());
		//20140904 修复了因为redirectAction传递中文乱码导致业务接收不到isView的值
		taskAssgineeDto.setTaskName(null);
		String ss = getTaskUrlBuffer(taskAssgineeDto)+"&isView=1";
		this.setTaskUrl(taskUrl1 + ss);
		this.setNameSpace(nameSpace);
		return "success";
	}
	
	/**
	 * 我的流程-发起
	 * 不启动流程实例，获取流程节点action，跳转到业务表单页面
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String startProcess() throws UnsupportedEncodingException {
		//获取流程定义的实现对象
		ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) jbpmService
				.getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(this.taskAssgineeDto.getDefinitionId()).uniqueResult();
		List<ActivityImpl> list = (List<ActivityImpl>) processDefinitionImpl.getActivities();
		TaskActivity taskActivity = null;
		for (ActivityImpl activityImpl : list) {
			String type = activityImpl.getType();
			if (type.equals("start")) {
				TransitionImpl transitionImpl = activityImpl.getDefaultOutgoingTransition();
				ActivityImpl activityImpl1 = transitionImpl.getDestination();
				taskActivity = (TaskActivity)activityImpl1.getActivityBehaviour();
				break;
			}
		}
		String taskUrl = taskActivity.getTaskDefinition()
				.getFormResourceName();
		//this.taskAssgineeDto.setDefinitionId(processDefinitionImpl.getId());
		nameSpace = taskUrl.substring(0, taskUrl.indexOf("/", 1));
		String taskUrl1 = taskUrl.substring(nameSpace.length(),
				taskUrl.length());
		TProcessConfig config = this.processConfigService.getProcessConfigByDefinitionId(this.taskAssgineeDto.getDefinitionId());
		if(config != null){
			this.taskAssgineeDto.setTemplateFileIds(config.getFileIds());
		}
		String ss = getTaskUrlBuffer(taskAssgineeDto);
		this.setTaskUrl(taskUrl1 + ss);
		this.setNameSpace(nameSpace);
		
		return "success";

	}
	
	/**
	 * 我的草稿-删除草稿
	 * @throws Exception 
	 */
	public String deleteDraft() throws Exception{
		String executionId = this.taskAssgineeDto.getExecutionId();
		TProcessBusiness processBusiness = jbpmService.findProcessBusiness(taskAssgineeDto);
		String info ="success";
		try {
			if(processBusiness != null){
				//调用服务拼接删除sql
				//删除业务数据
				jbpmService.deleteBusiness(processBusiness);
				//删除附件
				TProcessBusiness processBusinessFile = jbpmService.findProcessBusinessFile(processBusiness);
				if(processBusinessFile != null){
					File file = new File(processBusinessFile.getFilePath());
					if(file.isFile()){
						file.delete();
					}
					jbpmService.deleteBusinessFile(processBusinessFile);
				}
				processBusinessService.delete(processBusiness);
				//级联删除流程实例
				jbpmService.deleteProcessInstancCascadeeById(executionId);
			}
		} catch (Exception e) {
			info="fails";
			log.error("[删除草稿失败！]", e);
			throw e;
		}finally{	
		}
		Struts2Utils.renderText(info);
		return null;
	}
	
	public String toTaskConfigUpt() throws IOException {
		String definitionId = taskAssgineeDto.getDefinitionId();
		String taskName = taskAssgineeDto.getTaskName();
		this.taskAssgineeDto.setDefinitionId(definitionId);
		this.taskAssgineeDto.setTaskName(taskName);
		//查询数据库判断是否有
		TProcessTaskExeConfig tProcessTaskExeConfig = this.processTaskExeConfigService.getTaskExeConfig(definitionId,taskName);
		if(tProcessTaskExeConfig != null){
			if(tProcessTaskExeConfig.getIsRecordSubmit() == null){
				this.setIsRecordSubmit("");
			}else{
				this.setIsRecordSubmit(tProcessTaskExeConfig.getIsRecordSubmit());
			}
			this.taskAssgineeDto.setBusinessKey(tProcessTaskExeConfig.getId());
			this.taskAssgineeDto.setObjId(tProcessTaskExeConfig.getTaskAssId());
			this.taskAssgineeDto.setTaskConfigType(tProcessTaskExeConfig.getTaskAssType());
			if("01".equals(tProcessTaskExeConfig.getTaskAssType())){
				//指定岗位
				List<Map<String, Object>> objs = jbpmService.getPosition(taskAssgineeDto);
				if (objs.size() != 0) {
					for (Map<String, Object> map : objs) {
						this.taskAssgineeDto.setObjName((String) map.get("objName"));
					}
				}
			}else if("03".equals(tProcessTaskExeConfig.getTaskAssType()) || "10".equals(tProcessTaskExeConfig.getTaskAssType())){
				//指定部门
				List<Map<String, Object>> objs = jbpmService.getOrg(taskAssgineeDto);
				if (objs.size() != 0) {
					for (Map<String, Object> map : objs) {
						this.taskAssgineeDto.setObjName((String) map.get("objName"));
					}
				}
			}
		}
		this.setTaskAssgineeDto(taskAssgineeDto);
		return "process_task_config_upt";
	}
	
	/**
	 * 跳转到复制流程的界面
	 * @return
	 */
	public String toCopyProcessConfig(){
		//获取流程定义id
		String definitionId = taskAssgineeDto.getDefinitionId();
		TProcessConfig processConfig = this.processConfigService.getProcessConfigByDefinitionId(definitionId);
		processDeployDto = new ProcessDeployDto();
		processDeployDto.setId(processConfig.getId());
		processDeployDto.setBusinessType(processConfig.getBusinessType());//业务类型
		processDeployDto.setDeployType(processConfig.getRoleOrgPerson());//发布类型
		processDeployDto.setOrderNo(processConfig.getOrderNo());//序号
		processDeployDto.setProcessDefinitionId(processConfig.getDefinitionId());//流程定义id
		processDeployDto.setProcessName(processConfig.getProcessName());//流程名称
		processDeployDto.setProcessState(processConfig.getState()); //流程状态
		String deployRange = "";
		String objName = "";
		List<TProcessConfigPerson> processConfigPersons = this.processConfigPersonService.getProcessConfigPersons(processConfig.getId());
		if(processConfigPersons.size()>0){
			for (TProcessConfigPerson tProcessConfigPerson : processConfigPersons) {
				deployRange+=tProcessConfigPerson.getRelationId()+",";	
				taskAssgineeDto = new TaskAssgineeDto();
				taskAssgineeDto.setObjId(tProcessConfigPerson.getRelationId());
				if("02".equals(processConfig.getRoleOrgPerson())){
					//人员
					List<Map<String, Object>> objs = jbpmService.getEmp1(taskAssgineeDto);
					if (objs.size() != 0) {
						for (Map<String, Object> map : objs) {
							objName+=(String) map.get("objName")+",";
						}
					}
				}else if("03".equals(processConfig.getRoleOrgPerson())){
					//机构
					List<Map<String, Object>> objs = jbpmService.getOrg(taskAssgineeDto);
					if (objs.size() != 0) {
						for (Map<String, Object> map : objs) {
							objName+=(String) map.get("objName")+",";
						}
					}
				}else if("04".equals(processConfig.getRoleOrgPerson())){
					//岗位
					List<Map<String, Object>> objs = jbpmService.getPosition(taskAssgineeDto);
					if (objs.size() != 0) {
						for (Map<String, Object> map : objs) {
							objName+=(String) map.get("objName")+",";
						}
					}
				}
			}
		}
		processDeployDto.setDeployRange(deployRange.substring(0, deployRange.length()-1));
		processDeployDto.setObjName(objName.substring(0, objName.length()-1));
		return "jbpm_process_deploy_copy";
	}
	
	/**
	 * 执行复制流程
	 * @return
	 * @throws Exception
	 */
	public String deployUptProcess() throws Exception{
		String info ="success";
		String newPdId = "";
		try {
		//旧流程修改为隐藏
		TProcessConfig config = this.processConfigService.getProcessConfigByDefinitionId(this.processDeployDto.getProcessDefinitionId());
		//config.setProcessUserState("2");
		//config.setState("03");
		//this.processConfigService.update(config);
		
		//发布新流程
		ProcessDefinition definition = this.jbpmService.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(this.processDeployDto.getProcessDefinitionId()).uniqueResult();
		
		String deploymentId = definition.getDeploymentId();

		String xmlPath = this.jbpmService.getFilePath(deploymentId,"xml");
		String pngPath = this.jbpmService.getPngPath();
		 File file=new File(pngPath);
			if(!file.isDirectory()){
				file.mkdir();
			}
			File file2 = new File(pngPath+"/jbpmOut.png");
			file2.createNewFile();
			//生成流程图片
			JpdlModel jpdlModel;
			try {
				  jpdlModel = new JpdlModel (xmlPath);
				  ImageIO.write(new JpdlModelDrawer().draw(jpdlModel), "png", file2);  
			} catch (Exception e) {
				e.printStackTrace();
			}
		String pngPath2 = file2.getPath();
		File xmlFile = new File(xmlPath);
		File pngFile = new File(pngPath2);
		
		ProcessDefinition definition2 = this.jbpmService.deployProcess(xmlFile,pngFile);
		
		TProcessConfig tProcessConfig = new TProcessConfig();
		
		if(uploadTemplate != null && uploadTemplateFileName != null){
			//模板文件
			String urlstrTemplate = fileResourceTableService.getFileUpload(uploadTemplate,
					uploadTemplateFileName);
			
			TFileResourceTable templateFile = new TFileResourceTable();
			templateFile.setFilePath(urlstrTemplate);
			templateFile.setFileName(uploadTemplateFileName);
			fileResourceTableService.insert(templateFile);
			tProcessConfig.setFileIds(String.valueOf(templateFile.getFileId()));
		}else{
			tProcessConfig.setFileIds("");
		}
		
		
		
		
		// 根据路径加载配置文件
		// 部署流程
	
		tProcessConfig.setDefinitionId(definition2.getId());
		tProcessConfig.setRoleOrgPerson(this.processDeployDto
				.getDeployType());
		tProcessConfig.setProcessName(this.processDeployDto
				.getProcessName());
		tProcessConfig.setBusinessType(this.processDeployDto.getBusinessType());
		tProcessConfig.setOrderNo(this.processDeployDto.getOrderNo());
		// 流程状态-可用
		tProcessConfig.setState(this.processDeployDto.getProcessState());
		tProcessConfig.setUploadOrg(this.getCurrentOnlineUser().getOrgid());
		tProcessConfig.setUserId(this.getCurrentOnlineUser().getEmpid());
		
		//流程上传时间
		tProcessConfig.setUploadTime(TimeUtil.today()+TimeUtil.now());
		
		//tProcessConfig.setFileIds(String.valueOf(templateFile.getFileId()));
		
		Long tProcessConfigId = this.processConfigService
				.insert(tProcessConfig);
		
		// 保存流程配置与流程发布人员配置
		if (this.processDeployDto.getDeployRange() != null) {
			String[] empIdArray = this.processDeployDto.getDeployRange()
					.split(",");
			for (String empId : empIdArray) {
				TProcessConfigPerson tProcessConfigPerson = new TProcessConfigPerson();
				tProcessConfigPerson.setProcessConfigId(tProcessConfigId);
				tProcessConfigPerson.setRelationId(String.valueOf(empId));
				this.processConfigPersonService
						.insert(tProcessConfigPerson);
			}
		}
		newPdId= definition2.getId();
		info=info+","+newPdId;
		} catch (Exception e) {
			info="fails";
			log.error("[复制流程失败！]", e);
			throw e;
		}finally{	
		}
		Struts2Utils.renderText(info);
		return null;
		
	}
	
	public String updateTaskConfig() throws Exception{
		String info ="success";
		try {
		String taskName = taskAssgineeDto.getTaskName();
		String[] taskNames = taskName.split(",");
		String oldTaskName = taskNames[0];
		String newTaskName = taskNames[1];
		newTaskName=newTaskName.trim();
		ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) jbpmService
				.getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(this.taskAssgineeDto.getDefinitionId()).uniqueResult();
		//修改流程节点和流程图
		jbpmService.toTaskConfig3(processDefinitionImpl.getDeploymentId(),oldTaskName,newTaskName);
		
		String ss = jbpmService.getFilePath(processDefinitionImpl.getDeploymentId(), "xml");
		System.out.println(ss);
		//修改节点执行对象信息
		
		TProcessTaskExeConfig tProcessTaskExeConfig = null;
		tProcessTaskExeConfig = processTaskExeConfigService.getTaskExeConfig(this.taskAssgineeDto.getDefinitionId(),oldTaskName);
		
		String taskConfigType = taskAssgineeDto.getTaskConfigType();
		if(tProcessTaskExeConfig != null){
			tProcessTaskExeConfig.setTaskAssId(null);
		}else{
			tProcessTaskExeConfig = new TProcessTaskExeConfig();
		}
		if(isRecordSubmit == null){
			tProcessTaskExeConfig.setIsRecordSubmit("");
		}else{
			tProcessTaskExeConfig.setIsRecordSubmit(isRecordSubmit);
		}
		tProcessTaskExeConfig.setTaskAssType(taskConfigType);
		tProcessTaskExeConfig.setDefinitionId(taskAssgineeDto.getDefinitionId());
		tProcessTaskExeConfig.setActivityName(newTaskName);
		if ("01".equals(taskConfigType)) {
			// 指定岗位
			tProcessTaskExeConfig.setTaskAssId(taskAssgineeDto.getObjId());
		} else if ("02".equals(taskConfigType)) {
			// 动态机构
		} else if ("03".equals(taskConfigType)) {
			// 指定部门领导
			tProcessTaskExeConfig.setTaskAssId(taskAssgineeDto.getObjId());
		}else if ("10".equals(taskConfigType)) {
			// 指定部门所有人
			tProcessTaskExeConfig.setTaskAssId(taskAssgineeDto.getObjId());
		}  else if ("04".equals(taskConfigType)) {
			// 起草人所属机构领导
			tProcessTaskExeConfig.setTaskAssId("3");
		} else if ("05".equals(taskConfigType)) {
			// 起草人
		}
			if(taskAssgineeDto.getBusinessKey() != null){
				this.processTaskExeConfigService.update(tProcessTaskExeConfig);
			}else{
				this.processTaskExeConfigService.insert(tProcessTaskExeConfig);
			}
		} catch (Exception e) {
			info="fails";
			log.error("[流程节点配置保存失败！]", e);
			throw e;
		}finally{	
		}
		Struts2Utils.renderText(info);
		return null;
	}
	
	/**
	 * 跳转到修改流程的界面
	 * @return
	 */
	public String toUpdateProcessConfig(){
		//获取流程定义id
		String definitionId = taskAssgineeDto.getDefinitionId();
		TProcessConfig processConfig = this.processConfigService.getProcessConfigByDefinitionId(definitionId);
		processDeployDto = new ProcessDeployDto();
		processDeployDto.setId(processConfig.getId());
		processDeployDto.setBusinessType(processConfig.getBusinessType());//业务类型
		processDeployDto.setDeployType(processConfig.getRoleOrgPerson());//发布类型
		processDeployDto.setOrderNo(processConfig.getOrderNo());//序号
		processDeployDto.setProcessDefinitionId(processConfig.getDefinitionId());//流程定义id
		processDeployDto.setProcessName(processConfig.getProcessName());//流程名称
		processDeployDto.setProcessState(processConfig.getState()); //流程状态
		String deployRange = "";
		String objName = "";
		List<TProcessConfigPerson> processConfigPersons = this.processConfigPersonService.getProcessConfigPersons(processConfig.getId());
		if(processConfigPersons.size()>0){
			for (TProcessConfigPerson tProcessConfigPerson : processConfigPersons) {
				deployRange+=tProcessConfigPerson.getRelationId()+",";	
				taskAssgineeDto = new TaskAssgineeDto();
				taskAssgineeDto.setObjId(tProcessConfigPerson.getRelationId());
				if("02".equals(processConfig.getRoleOrgPerson())){
					//人员
					List<Map<String, Object>> objs = jbpmService.getEmp1(taskAssgineeDto);
					if (objs.size() != 0) {
						for (Map<String, Object> map : objs) {
							objName+=(String) map.get("objName")+",";
						}
					}
				}else if("03".equals(processConfig.getRoleOrgPerson())){
					//机构
					List<Map<String, Object>> objs = jbpmService.getOrg(taskAssgineeDto);
					if (objs.size() != 0) {
						for (Map<String, Object> map : objs) {
							objName+=(String) map.get("objName")+",";
						}
					}
				}else if("04".equals(processConfig.getRoleOrgPerson())){
					//岗位
					List<Map<String, Object>> objs = jbpmService.getPosition(taskAssgineeDto);
					if (objs.size() != 0) {
						for (Map<String, Object> map : objs) {
							objName+=(String) map.get("objName")+",";
						}
					}
				}
			}
		}
		processDeployDto.setDeployRange(deployRange.substring(0, deployRange.length()-1));
		processDeployDto.setObjName(objName.substring(0, objName.length()-1));
		return "jbpm_process_deploy_upt";
	}
	
	/**
	 * 执行修改流程
	 * 备注
	 * 对选择的流程的名称、排序、发布人员类型、发布状态、范围、模板附件进行修改
	 * @return
	 * @throws Exception
	 */
	public String uptateProcess() throws Exception{
		String info ="success";
		try {
			TProcessConfig config = this.processConfigService.getProcessConfigByDefinitionId(this.processDeployDto.getProcessDefinitionId());
			//业务类型、定义id不变
			//config.setBusinessType(this.processDeployDto.getBusinessType());
			//流程状态
			config.setState(this.processDeployDto.getProcessState());
			//流程名称
			config.setProcessName(this.processDeployDto
					.getProcessName());
			//排序序号
			config.setOrderNo(this.processDeployDto.getOrderNo());
			//修改上传人员机构
			config.setUploadOrg(this.getCurrentOnlineUser().getOrgid());
			//修改上传人员id
			config.setUserId(this.getCurrentOnlineUser().getEmpid());
			//修改流程时间
			config.setUploadTime(TimeUtil.today()+TimeUtil.now());
			//修改流程发布人员类型
			config.setRoleOrgPerson(this.processDeployDto
					.getDeployType());
			
			TFileResourceTable templateFile = new TFileResourceTable();
			if(uploadTemplate != null && uploadTemplateFileName != null){
				//模板文件
				String urlstrTemplate = fileResourceTableService.getFileUpload(uploadTemplate,
						uploadTemplateFileName);
				templateFile.setFilePath(urlstrTemplate);
				templateFile.setFileName(uploadTemplateFileName);
				fileResourceTableService.insert(templateFile);
				config.setFileIds(String.valueOf(templateFile.getFileId()));
			}else{
				config.setFileIds("");
			}
			
			this.processConfigService.update(config);
			
			Long tProcessConfigId  = config.getId();

			//删除之前TProcessConfigPerson的信息
			List<TProcessConfigPerson> tProcessConfigPersons = this.processConfigPersonService.getProcessConfigPersons(tProcessConfigId);
			if(tProcessConfigPersons.size() != 0){
				for (TProcessConfigPerson tProcessConfigPerson : tProcessConfigPersons) {
					this.processConfigPersonService.delete(tProcessConfigPerson);
				}
			}
			
			// 重新保存流程配置与流程发布人员配置
			if (this.processDeployDto.getDeployRange() != null) {
				String[] empIdArray = this.processDeployDto.getDeployRange()
						.split(",");
				for (String empId : empIdArray) {
					TProcessConfigPerson tProcessConfigPerson = new TProcessConfigPerson();
					tProcessConfigPerson.setProcessConfigId(tProcessConfigId);
					tProcessConfigPerson.setRelationId(String.valueOf(empId));
					this.processConfigPersonService
							.insert(tProcessConfigPerson);
				}
			}
			} catch (Exception e) {
				info="fails";
				log.error("[修改流程失败！]", e);
				throw e;
			}finally{	
			}
			Struts2Utils.renderText(info);
			return null;
	}
	
	/**
	 * 删除流程
	 * @throws Exception
	 */
	public String deleteProcess() throws Exception{
		String info ="success";
		try {
			jbpmService.deleteProcessInstanceById(executionId);
		} catch (Exception e) {
			info="fails";
			log.error("[删除流程失败！]", e);
			throw e;
		}
		Struts2Utils.renderText(info);
		return null;
	}
	
	public String toTaskOtherNameConfig(){
		return "process_task_name_config";
	}
	
	public void queryTaskNameConfig() throws Exception{
		HashMap<String, String> hm = XmlConvert.getParamsAjax();
		activityList = this.jbpmService.getTaskNameConfigList(hm);
       String xmlStr = XmlConvert.getXmlListBean(page,activityList);
        MUO.write(xmlStr);
	}
	
	public String saveTaskNameConfig() throws Exception{
		HashMap hm = XmlConvert.updateDatacell();
		HashMap hm1 = XmlConvert.getParamsAjax();
		String definitionId = (String) hm1.get("taskAssgineeDto.definitionId");
		List<ActivityDto> updateList = (List<ActivityDto>)hm.get("updateEntities");
		processTaskExeConfigService.saveTaskNameConfig(definitionId,updateList);
		return null;
	}
	
	/**
	 * 调整到jbpm在线编辑页面
	 * @return
	 */
	public String toJbpmOnlineDesign(){
		return "jbpm_online_design";
	}
	
	/**
	 * 根据在线编辑器保存生成xml和png，更新流程xml和png
	 * @return
	 * @throws Exception
	 */
	public String makeAndUpdateJbpm() throws Exception {
		String info ="success";
		String newPdId = "";
		BufferedWriter buffWri = null;
		String str = null;
		String xmlPath = this.jbpmService.getFilePath(deploymentId, "xml");
		try {
			File f = new File(xmlPath);
			if (!f.exists()) {
				f.createNewFile();
			}
			// 解决xml中文乱码问题
			OutputStreamWriter write = new OutputStreamWriter(
					new FileOutputStream(f), "UTF-8");
			buffWri = new BufferedWriter(write);
			// 将data数据转换成符合jbpm的JsonObject
			if (data != null) {
				str = jbpmService.makeJbpmJsonByData(data,pname);
			}
			// jbpm文件的开头
			buffWri.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffWri.newLine();
			//buffWri.write("<process name=\"\" xmlns=\"http://jbpm.org/4.4/jpdl\">");
			buffWri.newLine();
			// jbpm文件的结尾
			buffWri.write(str);
			buffWri.newLine();
			//buffWri.write("</process>");
			buffWri.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffWri != null)
					buffWri.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String pngPath = this.jbpmService.getPngPath();
		 File file=new File(pngPath);
			if(!file.isDirectory()){
				file.mkdir();
			}
			File file2 = new File(pngPath+"/jbpmOut.png");
			file2.createNewFile();
			//生成流程图片
			JpdlModel jpdlModel;
			try {
				  jpdlModel = new JpdlModel (xmlPath);
				  ImageIO.write(new JpdlModelDrawer().draw(jpdlModel), "png", file2);  
			} catch (Exception e) {
				e.printStackTrace();
			}
			String pngPath2 = file2.getPath();
			File xmlFile = new File(xmlPath);
			File pngFile = new File(pngPath2);
			
			//更新流程xml和png
			jbpmService.updateProcessXmlPng(deploymentId,xmlFile,pngFile);
			
			newPdId= definitionId;
			info=info+","+newPdId;
			Struts2Utils.renderText(info);
		return null;
	}
	
	/**
	 * 根据选择的流程跳转到流程图可配置页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toProcessImgConfig() throws Exception {
		//获得流程定义对象
		ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) jbpmService
				.getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(this.taskAssgineeDto.getDefinitionId())
				.uniqueResult();
		//获取部署id
		String deploymentId = processDefinitionImpl.getDeploymentId();
		this.deploymentId = deploymentId;
		this.definitionId = processDefinitionImpl.getId();
		//从数据库读取对应的xml文件并输出
		String xmlPath = this.jbpmService.getFilePath(deploymentId, "xml");
		File xmlFile = new File(xmlPath);
		SAXReader sr = new SAXReader();
		Document doc = sr.read(xmlFile);
		//获取根节点
		Element el_root = doc.getRootElement();
		List<Attribute> elAttributes = el_root.attributes();
		for (Attribute attribute : elAttributes) {
			if("name".equals(attribute.getName())){
				pname = attribute.getValue();
			}
		}
		JSONObject obj = jbpmService.getJbpmJson(xmlFile,pname);
		System.out.println(obj);
		this.setData(obj.toString());
		this.setPname(pname);
		this.setDeploymentId(deploymentId);
		this.setDefinitionId(definitionId);
		return "jbpm_online_design_upt";
	}
	 
}
