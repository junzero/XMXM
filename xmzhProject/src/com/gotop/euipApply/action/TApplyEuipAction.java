package com.gotop.euipApply.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.euipApply.model.TApplyEuip;
import com.gotop.euipApply.service.ITApplyEuipService;
import com.gotop.jbpm.dto.TaskAssgineeDto;
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

public class TApplyEuipAction extends BaseAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  /**
     * 区分是否第一个节点
     * 20140902
     */
    private String isFirst;
    
	/**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITApplyEuipService tApplyEuipService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settApplyEuipService(ITApplyEuipService tApplyEuipService) {
        this.tApplyEuipService = tApplyEuipService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITApplyEuipService gettApplyEuipService() {
        return this.tApplyEuipService;
    }
    
    private List<TApplyEuip> results;
    
    private TApplyEuip  euip;
    
    private TaskAssgineeDto taskAssgineeDto;
    
    private String epId;
    
    private String btnType;
    
    private File[] files;
    
    private String[] filesFileName;
    
    private String filename;
    
    private String isView;
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
     * 处理设备申请信息
     */
    public void insertEuipInfo(){
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
    		tApplyEuipService.insertEuipInfo(euip, files, filesFileName, btnType, muo,taskAssgineeDto,isFirst);
			info="success";
		} catch (Exception e) {
			info="fails";
			log.error("插入社保申请信息失败！", e);
		}
    	queryDefault();
		Struts2Utils.renderText(info);
    }
    
    /**
     * 查询设备申请详情
     */
    public String input(){
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			epId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		euip=tApplyEuipService.queryEuipInfo(epId,flowId);
		} catch (Exception e) {
			log.error("查询设备申请信息失败",e);
		}
    	queryDefault();
		return "input";
    }
    
    /**
     * 查询设备申请详情
     */
    public String input1(){
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			epId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		euip=tApplyEuipService.queryEuipInfo(epId,flowId);
		} catch (Exception e) {
			log.error("查询设备申请信息失败",e);
		}
    	queryDefault();
    	//return "input";
		return "input1";
    }
    
    /**
     * 查询设备申请详情
     */
    public String input2(){
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			epId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		euip=tApplyEuipService.queryEuipInfo(epId,flowId);
		} catch (Exception e) {
			log.error("查询设备申请信息失败",e);
		}
    	queryDefault();
		return "input";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	public List<TApplyEuip> getResults() {
		return results;
	}

	public void setResults(List<TApplyEuip> results) {
		this.results = results;
	}

	public TApplyEuip getEuip() {
		return euip;
	}

	public void setEuip(TApplyEuip euip) {
		this.euip = euip;
	}

	public String getEpId() {
		return epId;
	}

	public void setEpId(String epId) {
		this.epId = epId;
	}

	public String getBtnType() {
		return btnType;
	}

	public void setBtnType(String btnType) {
		this.btnType = btnType;
	}

	public TaskAssgineeDto getTaskAssgineeDto() {
		return taskAssgineeDto;
	}

	public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
		this.taskAssgineeDto = taskAssgineeDto;
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

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}
    
    
}