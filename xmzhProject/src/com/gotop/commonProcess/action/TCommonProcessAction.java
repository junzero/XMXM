package com.gotop.commonProcess.action;

import com.gotop.commonProcess.model.TCommonProcess;
import com.gotop.commonProcess.service.ITCommonProcessService;
import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.dto.BusinessDto;
import com.gotop.euipApply.model.TApplyEuip;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.opinion.model.TDefaultOpinion;
import com.gotop.opinion.service.ITDefaultOpinionService;
import com.gotop.supervise.model.TSuperviseTable;
import com.gotop.util.Struts2Utils;
import com.gotop.util.XmlConvert;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.struts2.ServletActionContext;

public class TCommonProcessAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITCommonProcessService tCommonProcessService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settCommonProcessService(ITCommonProcessService tCommonProcessService) {
        this.tCommonProcessService = tCommonProcessService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITCommonProcessService gettCommonProcessService() {
        return this.tCommonProcessService;
    }
    
    /**
     * 通用流程list
     */
    private List<TCommonProcess> results;
    
    /**
     * 对象
     */
    private TCommonProcess  commonProcess;
    
    private TaskAssgineeDto taskAssgineeDto;
    
    private String recId;
    
    private String btnType;
    
    private File[] files;
    
    private String[] filesFileName;
    
    private String filename;
    
    private String isView;
    /**
	 * @return results
	 */
	public List<TCommonProcess> getResults() {
		return results;
	}

	/**
	 * @param results 要设置的 results
	 */
	public void setResults(List<TCommonProcess> results) {
		this.results = results;
	}

	/**
	 * @return commonProcess
	 */
	public TCommonProcess getCommonProcess() {
		return commonProcess;
	}

	/**
	 * @param commonProcess 要设置的 commonProcess
	 */
	public void setCommonProcess(TCommonProcess commonProcess) {
		this.commonProcess = commonProcess;
	}

	/**
	 * @return taskAssgineeDto
	 */
	public TaskAssgineeDto getTaskAssgineeDto() {
		return taskAssgineeDto;
	}

	/**
	 * @param taskAssgineeDto 要设置的 taskAssgineeDto
	 */
	public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
		this.taskAssgineeDto = taskAssgineeDto;
	}

	/**
	 * @return recId
	 */
	public String getRecId() {
		return recId;
	}

	/**
	 * @param recId 要设置的 recId
	 */
	public void setRecId(String recId) {
		this.recId = recId;
	}

	/**
	 * @return btnType
	 */
	public String getBtnType() {
		return btnType;
	}

	/**
	 * @param btnType 要设置的 btnType
	 */
	public void setBtnType(String btnType) {
		this.btnType = btnType;
	}

	/**
	 * @return files
	 */
	public File[] getFiles() {
		return files;
	}

	/**
	 * @param files 要设置的 files
	 */
	public void setFiles(File[] files) {
		this.files = files;
	}

	/**
	 * @return filesFileName
	 */
	public String[] getFilesFileName() {
		return filesFileName;
	}

	/**
	 * @param filesFileName 要设置的 filesFileName
	 */
	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	/**
	 * @return filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename 要设置的 filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return isView
	 */
	public String getIsView() {
		return isView;
	}

	/**
	 * @param isView 要设置的 isView
	 */
	public void setIsView(String isView) {
		this.isView = isView;
	}

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
	 * 返回输入页
	 * @return
	 * @throws Exception
	 */
    public String getCommonProcessForinput() throws Exception {
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			recId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		commonProcess=tCommonProcessService.queryCommonProcess(recId,flowId);
		} catch (Exception e) {
			log.error("查询通用流程信息失败",e);
		}
    	queryDefault();
		return "input";
    }


	/**
     * 取出数据到审核页
     * @return
     * @throws Exception
     */
    public String getCommonProcessForapprove() throws Exception {
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			recId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		commonProcess=tCommonProcessService.queryCommonProcess(recId,flowId);
		} catch (Exception e) {
			log.error("查询通用流程信息失败",e);
		}
    	queryDefault();
		return "approve";
		
    }
    
    /**
	 * 给taskAssgineeDto赋值
	 * @return
	 */
	private TaskAssgineeDto packTaskAssgineeDto(Long businessKey) throws Exception {
		//设置dto信息
    	MUOUserSession muo=getCurrentOnlineUser();
		taskAssgineeDto.setPreTaskAssingee(muo.getEmpid());
		taskAssgineeDto.setPreTaskId(taskAssgineeDto.getNextTaskId());
		taskAssgineeDto.setPreTaskOrg(muo.getOrgid());
		taskAssgineeDto.setPreTaskTime(TimeUtil.today()+TimeUtil.now());
		taskAssgineeDto.setBusinessKey(businessKey);
		taskAssgineeDto.setTaskId(taskAssgineeDto.getNextTaskId());
		taskAssgineeDto.setTaskExeAssginee(String.valueOf(muo.getEmpid()));
		return taskAssgineeDto;
	}
    
    /**
     * 处理公共页
     * @throws Exception
     */
    public void insertCommonProcess() throws Exception {
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
    		BusinessDto businessDto = new BusinessDto();
    		businessDto.setUpload(files);
    		businessDto.setUploadFileName(filesFileName);
    		businessDto.setSubmitReason(btnType);
    		commonProcess.setEmpId(muo.getEmpid());
    		commonProcess.setOrgId(muo.getOrgid());
    		commonProcess.setCreateDate(TimeUtil.today());
    		commonProcess.setCreateTime(TimeUtil.now());
    		commonProcess.setBussinessType(taskAssgineeDto.getBusinessType());
    		tCommonProcessService.insertCommonProcess(commonProcess, businessDto, muo , taskAssgineeDto);
			info="success";
		} catch (Exception e) {
			info="fails";
			log.error("保存通用流程报错", e);
		}
		Struts2Utils.renderText(info);
    }
    
    /**
     * 审核公用流程
     * @throws Exception
     */
    public void approveCommonProcess() throws Exception {
    	String info ="success";
		try{
    		//为传递的对象赋值
			MUOUserSession muo = this.getCurrentOnlineUser();
	    	BusinessDto businessDto = new BusinessDto();
	    	businessDto.setTaskId(taskAssgineeDto.getNextTaskId());
	    	businessDto.setResourceId(commonProcess.getRecId());
	    	businessDto.setUpload(files);
	    	businessDto.setUploadFileName(filesFileName);
	    	tCommonProcessService.insertApprove(commonProcess,businessDto,packTaskAssgineeDto(commonProcess.getRecId()),muo);
	    	
    	}catch (Exception e){
    		info="fails";
    		log.error("[审核失败]", e);
			throw e;
    	}
    	Struts2Utils.renderText(info);
    }

    /**
     * viewDataList说明.
     * @abatorgenerated
     */
    public String queryViewList() throws Exception {
        HttpServletRequest request=ServletActionContext.getRequest();
        Page page = this.getPage();
        HashMap hm = new HashMap();
        List orgs = tCommonProcessService.queryPageDataList(hm,page);
        request.setAttribute("orgs", orgs);
        request.setAttribute("page", page);
        return "viewlist";
    }
    
    /**
     * 跳转到部室内部会签选人界面
     * @return
     */
    public String queryEmpJsp(){
    	return "queryemp";
    }
    
    /**
     * 各部室--添加内部会签,部门负责人提交本部门相关人员处理，人员从页面选择得到。
     * @return
     */
    public String insertTaskAssignPerson(){
    	String info="";
    	try {
    	MUOUserSession muo=getCurrentOnlineUser();
		commonProcess.setEmpId(muo.getEmpid());
		commonProcess.setOrgId(muo.getOrgid());
		commonProcess.setCreateDate(TimeUtil.today());
		commonProcess.setCreateTime(TimeUtil.now());
		commonProcess.setBussinessType(taskAssgineeDto.getBusinessType());
    	info=tCommonProcessService.insertTaskAssignePerson(taskAssgineeDto,muo,commonProcess);
    		
		} catch (Exception e) {
			info="fails";
			log.error("部室内部会签出错", e);
		}
	     Struts2Utils.renderText(info); 
		return null;
    }
    
    /**
     * 查询部室下的人
     * @return
     */
    public String queryTaskAssignPerson() throws Exception{
    	try {
    		results=new ArrayList<TCommonProcess>();
    		MUOUserSession muo=getCurrentOnlineUser();
    		String positionCode = Struts2Utils.getParameter("positionCode");
    		results=tCommonProcessService.queryTaskAssignPerson(positionCode, muo);	
		} catch (Exception e) {
			log.error("获取部室人员出错！", e);
			throw e;
		}
		return "persones";
    }
    
    /**
     * 部室反馈
     * @return
     */
    public String bushiSaveHandle(){
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
    		commonProcess.setEmpId(muo.getEmpid());
    		commonProcess.setOrgId(muo.getOrgid());
    		commonProcess.setCreateDate(TimeUtil.today());
    		commonProcess.setCreateTime(TimeUtil.now());
    		commonProcess.setBussinessType(taskAssgineeDto.getBusinessType());
			info=tCommonProcessService.updateBuShiAssignStatus(taskAssgineeDto, muo, commonProcess);
		} catch (Exception e) {
		info="fails";
		log.error("部室内部保存操作失败", e);
		}
		Struts2Utils.renderText(info);
		return null;
    }
}