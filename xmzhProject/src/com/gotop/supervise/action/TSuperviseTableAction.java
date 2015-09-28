package com.gotop.supervise.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.opinion.model.TDefaultOpinion;
import com.gotop.opinion.service.ITDefaultOpinionService;
import com.gotop.supervise.model.TSuperviseTable;
import com.gotop.supervise.service.ITSuperviseTableService;
import com.gotop.util.Struts2Utils;
import com.gotop.vo.system.MUOUserSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TSuperviseTableAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITSuperviseTableService tSuperviseTableService;
    
    private List<TSuperviseTable> results;
    private TSuperviseTable supervise;
    private TaskAssgineeDto taskAssgineeDto;
    private String btnType;
    private File[] files;
    private String[] filesFileName;
    private String filename;
    private String superviseId;
    private String isView;
    private String status;
    
    private String isFirst;
    private String supFlag;
    private String possionId;
    
    /**
     * 岗位code
     */
    private String positionCode;
    
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
     * 处理督办单
     * @return
     */
    public String insertSuperviseInfo(){
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
			tSuperviseTableService.insertSuperviseTable2(supervise, files, filesFileName, muo, btnType, taskAssgineeDto,isFirst);
			info="success";
		} catch (Exception e) {
			info="fails";
			log.error("处理督办单报错", e);
		}
		Struts2Utils.renderText(info);
		return null;
    }
    
    /**
     * 提交申请
     */
    @Override
	public String input(){
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			superviseId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		supervise=tSuperviseTableService.querySuperviseTable(superviseId,flowId);
		} catch (Exception e) {
			log.error("查询申请出错", e);
		}
    	queryDefault();
    	return "input";
    }
    
    /**
     * 分管领导审核
     * @return
     */
    public String input2(){
    	try{
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			superviseId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		supervise=tSuperviseTableService.querySuperviseTable(superviseId,flowId);
    	}catch(Exception e){
    		log.error("审核-查询审核信息失败", e);
    	}
    	queryDefault();
    	return "show";
    }
    
    /**
     * 部室接收
     * @return
     */
    public String input3(){
    	try{
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			superviseId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		supervise=tSuperviseTableService.querySuperviseTable(superviseId,flowId);
    	}catch(Exception e){
    		log.error("部室-查询审核信息失败", e);
    	}
    	queryDefault();
    	return "approve1";
    }
    
    /**
     * 部室接收
     * @return
     */
    public String input4(){
    	try{
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			superviseId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		supervise=tSuperviseTableService.querySuperviseTable(superviseId,flowId);
    	}catch(Exception e){
    		log.error("部室-查询审核信息失败", e);
    	}
    	queryDefault();
    	return "input1";
    }
    
    /**
     * 查询待办督办信息
     * @return
     */
    public String querySuperviseTables(){
    	try {
			MUOUserSession muoUserSession=getCurrentOnlineUser();
		results=tSuperviseTableService.querySuperviseTabls(supervise, muoUserSession, this.getPage());
		} catch (Exception e) {
			log.error("查询待办督办信息", e);
		}
		return "viewlist";
    }
    
    /**
     * 查看接收督办信息
     * @return
     */
    public String querySuperviseRecive(){
    	try {
		supervise=tSuperviseTableService.querySuperviseRecive(supervise);
		} catch (Exception e) {
			log.error("查看接收督办信息异常", e);
		}
    	queryDefault();
		return "info";
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
    	info=tSuperviseTableService.insertTaskAssignePerson(taskAssgineeDto,muo,supervise);
    		
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
    public String queryTaskAssignPerson(){
    	try {
    		results=new ArrayList<TSuperviseTable>();
    		MUOUserSession muo=getCurrentOnlineUser();
    		results=tSuperviseTableService.queryTaskAssignPerson(possionId, muo);	
		} catch (Exception e) {
			log.error("获取部室人员出错！", e);
		}
		return "persones";
    }
    
    /**
     * 查询岗位code为行领导的人
     * @return
     */
    public String queryLeader(){
    	try {
    		results=new ArrayList<TSuperviseTable>();
    		MUOUserSession muo=getCurrentOnlineUser();
    		results=tSuperviseTableService.queryLeader(positionCode, muo);	
		} catch (Exception e) {
			log.error("获取行领导人员出错！", e);
		}
		return "persones";
    }
    public String bushiSaveHandle(){
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
			info=tSuperviseTableService.updateBuShiAssignStatus(taskAssgineeDto, muo, supervise);
		} catch (Exception e) {
		info="fails";
		log.error("部室内部保存操作失败", e);
		}
		Struts2Utils.renderText(info);
		return null;
    }
    
    public String insertSuperviseFile(){
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
			tSuperviseTableService.insertSuperviseFile(supervise, files, filesFileName, muo,taskAssgineeDto);
			info="success";
		} catch (Exception e) {
			info="fails";
			log.error("上传督办单附件报错", e);
		}
		Struts2Utils.renderText(info);
		return null;
    }
    
    
    
    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settSuperviseTableService(ITSuperviseTableService tSuperviseTableService) {
        this.tSuperviseTableService = tSuperviseTableService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITSuperviseTableService gettSuperviseTableService() {
        return this.tSuperviseTableService;
    }

	public List<TSuperviseTable> getResults() {
		return results;
	}

	public void setResults(List<TSuperviseTable> results) {
		this.results = results;
	}

	public TSuperviseTable getSupervise() {
		return supervise;
	}

	public void setSupervise(TSuperviseTable supervise) {
		this.supervise = supervise;
	}

	public TaskAssgineeDto getTaskAssgineeDto() {
		return taskAssgineeDto;
	}

	public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
		this.taskAssgineeDto = taskAssgineeDto;
	}

	public String getBtnType() {
		return btnType;
	}

	public void setBtnType(String btnType) {
		this.btnType = btnType;
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

	public String getSuperviseId() {
		return superviseId;
	}

	public void setSuperviseId(String superviseId) {
		this.superviseId = superviseId;
	}

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	/**
	 * @return supFlag
	 */
	public String getSupFlag() {
		return supFlag;
	}

	/**
	 * @param supFlag 要设置的 supFlag
	 */
	public void setSupFlag(String supFlag) {
		this.supFlag = supFlag;
	}

	public String getPossionId() {
		return possionId;
	}

	public void setPossionId(String possionId) {
		this.possionId = possionId;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
    
    

   
}