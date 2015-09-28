package com.gotop.mettingApply.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.mettingApply.model.TMettingApply;
import com.gotop.mettingApply.service.ITMettingApplyService;
import com.gotop.opinion.model.TDefaultOpinion;
import com.gotop.opinion.service.ITDefaultOpinionService;
import com.gotop.util.Struts2Utils;
import com.gotop.util.XmlConvert;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class TMettingApplyAction extends BaseAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITMettingApplyService tMettingApplyService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settMettingApplyService(ITMettingApplyService tMettingApplyService) {
        this.tMettingApplyService = tMettingApplyService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITMettingApplyService gettMettingApplyService() {
        return this.tMettingApplyService;
    }

    //任务流程有关的实体
    private TaskAssgineeDto taskAssgineeDto;
    
    private List<TMettingApply> results;
    private TMettingApply meet;
    private File[] files;
    private String[] filesFileName;
    private String filename;
    private String mettingId;
    private String flowId;
    private String nodeId;
    private String nodeName1;
    private String btnType;
    private String  taskId; 
    private String isView;
    private String isFirst;
    /**
	 * 默认意见
	 * 2014.9.19 新建 
	 */
	private List<TDefaultOpinion> defaultOps;
	
	private ITDefaultOpinionService tDefaultOpinionService;

	public List<TDefaultOpinion> getDefaultOps() {
		return defaultOps;
	}

	public void setDefaultOps(List<TDefaultOpinion> defaultOps) {
		this.defaultOps = defaultOps;
	}

    public ITDefaultOpinionService gettDefaultOpinionService() {
		return tDefaultOpinionService;
	}

	public void settDefaultOpinionService(
			ITDefaultOpinionService tDefaultOpinionService) {
		this.tDefaultOpinionService = tDefaultOpinionService;
	}
	
	public void queryDefault(){
		try {
			defaultOps = tDefaultOpinionService.queryDefaultOpsForshow(this.getCurrentOnlineUser().getEmpid());
		} catch (Exception e) {
			log.error("[获取默认意见失败]",e);
			e.printStackTrace();
		}
	}
	//2014.9.19新增结束标记
    
    /**
     * 查询列表
     * @return
     */
    public String queryMettingApplyList(){
    	try {
			MUOUserSession muo=getCurrentOnlineUser();
			results=tMettingApplyService.queryMettingApplyList(meet, muo, this.getPage());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "viewlist";
    }
    
    /**
     * 处理流程信息
     * @return
     */
    public String insertMettingInfo(){
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
    		tMettingApplyService.insertMettingInfo(meet, taskAssgineeDto, btnType, muo, files, filesFileName, isFirst);
			info="success";
		} catch (Exception e) {
			info="fails";
			log.error("处理审核信息", e);
		}
		Struts2Utils.renderText(info);
		return null;
    }
    
    /**
     * 查询信息
     * @return
     */
    public String input1(){
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			mettingId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
			meet=tMettingApplyService.queryMettingInfo(mettingId, flowId);
			if(meet!=null){
				String date = meet.getMettingTime().substring(0,10);
				String time = meet.getMettingTime().substring(11);
				meet.setMettingDate(date);
				meet.setMettingTime(time);
			}
		} catch (Exception e) {
			log.error("查询信息", e);
		}
    	queryDefault();
    	return "input";
    }

    /**
     * 查询信息2
     * @return
     */
    public String input2(){
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			mettingId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
			meet=tMettingApplyService.queryMettingInfo(mettingId, flowId);
		} catch (Exception e) {
			log.error("查询信息", e);
		}
    	taskAssgineeDto.setBeginAssingee(taskAssgineeDto.getTaskAssingee());
    	queryDefault();
    	return "approve";
    }
    
    /**
     * 办公室会议管理员
     * @return
     */
    public String manage(){
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			mettingId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
			meet=tMettingApplyService.queryMettingInfo(mettingId, flowId);
			if(meet!=null){
				String date = meet.getMettingTime().substring(0,10);
				String time = meet.getMettingTime().substring(11);
				meet.setMettingDate(date);
				meet.setMettingTime(time);
			}
		} catch (Exception e) {
			log.error("查询信息", e);
		}
    	taskAssgineeDto.setBeginAssingee(taskAssgineeDto.getTaskAssingee());
    	queryDefault();
    	return "manage";
    }
    
    /**
     * 查询信息详情
     * @return
     */
    public String info(){
    	try {
    		meet.setEmpid(this.getCurrentOnlineUser().getEmpid());
			meet=tMettingApplyService.querySuperviseRecive(meet);
		} catch (Exception e) {
			log.error("查询信息详情", e);
		}
    	queryDefault();
    	return "info";
    }
    
    /**
     * 会议转发
     * @return
     */
    public String saveTransmitEmp(){
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
    		tMettingApplyService.insertMessageTransmit(meet, muo);
			info="success";
		} catch (Exception e) {
			info="fails";
			log.error("保存转发信息失败", e);
		}
		Struts2Utils.renderText(info);
		return null;
    }
    
	public TaskAssgineeDto getTaskAssgineeDto() {
		return taskAssgineeDto;
	}

	public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
		this.taskAssgineeDto = taskAssgineeDto;
	}

	public List<TMettingApply> getResults() {
		return results;
	}

	public void setResults(List<TMettingApply> results) {
		this.results = results;
	}

	public TMettingApply getMeet() {
		return meet;
	}

	public void setMeet(TMettingApply meet) {
		this.meet = meet;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	

	public String getMettingId() {
		return mettingId;
	}

	public void setMettingId(String mettingId) {
		this.mettingId = mettingId;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName1() {
		return nodeName1;
	}

	public void setNodeName1(String nodeName1) {
		this.nodeName1 = nodeName1;
	}

	public String getBtnType() {
		return btnType;
	}

	public void setBtnType(String btnType) {
		this.btnType = btnType;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	/**
	 * @return isFirst
	 */
	public String getIsFirst() {
		return isFirst;
	}

	/**
	 * @param isFirst 要设置的 isFirst
	 */
	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}
    
   
}