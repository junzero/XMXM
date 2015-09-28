package com.gotop.messagePublish.action;

import com.fr.design.insert.formula.FunctionNAD;
import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.opinion.model.TDefaultOpinion;
import com.gotop.opinion.service.ITDefaultOpinionService;
import com.gotop.messagePublish.model.TMessagePublish;
import com.gotop.messagePublish.service.ITMessagePublishService;
import com.gotop.tyjg.common.model.Employee;
import com.gotop.util.Struts2Utils;
import com.gotop.util.XmlConvert;
import com.gotop.util.encode.EncodeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

public class TMessagePublishAction extends BaseAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITMessagePublishService tMessagePublishService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settMessagePublishService(ITMessagePublishService tMessagePublishService) {
        this.tMessagePublishService = tMessagePublishService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITMessagePublishService gettMessagePublishService() {
        return this.tMessagePublishService;
    }
    
    private List<TMessagePublish> results;
    private List<TMessagePublish> emplist;
    private TMessagePublish message;
    private List<TApproveOpninion> opninions;
    //任务流程有关的实体
    private TaskAssgineeDto taskAssgineeDto;
    
    private File[] files;
    private String[] filesFileName;
    private String filename;
    private String messageId;
    private String nodeId;
    private String nodeName1;
    private String btnType;
    
    private String  taskId;
    
    private String isView;
    private String status;
    /**
     * 区分是否第一个节点
     * 20140902
     */
    private String isFirst;
   
    /**
	 * 下载文件
	 */
	public String downLoad() {
        return SUCCESS;
    }
    public InputStream getInputStream() throws Exception {
        String path = ServletActionContext.getServletContext().getRealPath("/upload/"+URLDecoder.decode(filename,"UTF-8"));
        return new FileInputStream(path);    //如果dir是绝对路径
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
	 * 查询信息发布列表
	 * @return
	 */
    public String queryTMessagePublishList(){
    	try {
			MUOUserSession muo=getCurrentOnlineUser();
			results=tMessagePublishService.queryTMessagePublishList(muo, message, this.getPage());
		} catch (Exception e) {
		log.error("查询发布信息列表出现异常", e);
		}
		return "list";
    }
    
    /**
     * 插入信息
     * @throws Exception
     */
    public String insertTMessagePublish()throws Exception{
    	String info ="success";
		try{
			MUOUserSession muo=getCurrentOnlineUser();
		    message.setCreateEmpid(muo.getEmpid());
		    message.setOrgid(String.valueOf(muo.getOrgid()));
		    taskAssgineeDto=tMessagePublishService.insertTMessagePublish(message,files,filesFileName,muo,btnType,taskAssgineeDto,isFirst);  
		}catch (Exception e) {
			info="fails";
			log.error("[发布消息失败！]", e);
			throw e;
		}finally{	
		}
		Struts2Utils.renderText(info);
		return null;
    }
    
    /**
     * 加载意见列表
     */
    public void  queryOpninions(){
    	try {
    		  //AjaxParam apm = XmlConvert.queryDatacell();
    		  HashMap<String, String> hmp = XmlConvert.getParamsAjax();
    		    //Page page = apm.getPage();
    			//HashMap hm = apm.getParams();
    		 // String path = hmp.get("resourceId");
  			  //String src_cd = hmp.get("resourceType");
    	        opninions = tMessagePublishService.queryApproveOpninions(hmp);
    	        String xmlStr =XmlConvert.getXmlListBean(opninions);
    	        MUO.write(xmlStr);
    		
		} catch (Exception e) {
			log.error("查询意见列表出错", e);
		}
    	
    }
   
    
    
    /**
     * 申请界面
     * @return
     */
    public String messagePublish(){
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			messageId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		    message=tMessagePublishService.queryMessageInfo(messageId,flowId);
    			} catch (Exception e) {
    				log.error("获取客户详情报错。", e);
    			}
    	return "input";
    }
    
    /**
     * 部门领导审核界面
     * @return
     */
    public String messagePublishApprove1(){
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			messageId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
    		 message=tMessagePublishService.queryMessageInfo(messageId,flowId);
    			} catch (Exception e) {
    				log.error("获取客户详情报错。", e);
    			}
    	queryDefault();
    	return "approve1";
    }
    
    /**
     * 分管领导审核界面
     * @return
     */
    public String messagePublishApprove2(){
    	try {
    		String flowId="";
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getBusinessKey()!=null&&!"".equals(taskAssgineeDto.getBusinessKey()))
    			messageId=String.valueOf(taskAssgineeDto.getBusinessKey());
    		if(taskAssgineeDto!=null&&taskAssgineeDto.getExecutionId()!=null&&!"".equals(taskAssgineeDto.getExecutionId()))
    			flowId=taskAssgineeDto.getExecutionId();
	 message=tMessagePublishService.queryMessageInfo(messageId,flowId);
		} catch (Exception e) {
			log.error("获取客户详情报错。", e);
		}
    	queryDefault();
    	return "approve2";
    }
    
    /**
     * 详情
     * @return
     */
    public String messagePublishInfo(){
    	try {
    		 message=tMessagePublishService.queryMessageInfo(messageId,"");
		} catch (Exception e) {
			log.error("查询客户信息失败", e);
		}
    	queryDefault();
		return "detail";
    }
    
    /**
     * 阅毕
     */
    public void insertMessageReadPer(){
    	String info="";
    	try {
			MUOUserSession muo=getCurrentOnlineUser();
			String a =null;
			/**
			 * 2014.9.1 批量阅闭
			 */
			if(message!=null){
				if(!"null".equals(message.getOpninion()) && message.getOpninion()!=null)
					a = new String (URLDecoder.decode(message.getOpninion(),"UTF-8"));
				else
					a = "";
				tMessagePublishService.insertMessageRedPer(String.valueOf(message.getMessageId()),a, muo);
			}else{
				tMessagePublishService.insertMessageRedPer(messageId,a, muo);
			}
			info="success";
		} catch (Exception e) {
			info="fails";
			log.error("阅毕操作失败", e);
		}
		Struts2Utils.renderText(info);
    }
    /**
     * 转发(页面跳转)
     * @return
     */
    public String transmitEmp(){
    	return "transmit";
    }
    
    public String saveTransmitEmp(){
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
    		tMessagePublishService.insertMessageTransmit(message, muo,messageId,status);
			info="success";
		} catch (Exception e) {
			info="fails";
			log.error("保存转发信息失败", e);
		}
		Struts2Utils.renderText(info);
		return null;
    }
    
    /**
     * 查询督办人员
     * @return
     */
    public String querySuperviseEmp(){
    	try{
    		List<Map<String, Object>> list = tMessagePublishService.querysupervise();
    		emplist=new ArrayList<TMessagePublish>();
    		if(list.size()>0){
    			for(int i=0;i<list.size();i++){
    				TMessagePublish message1=new TMessagePublish();
        			message1.setEmpId(String.valueOf(list.get(i).get("EMPID")));
        			message1.setEmpName(String.valueOf(list.get(i).get("EMPNAME")));
        			emplist.add(message1);
        		}
    		}
    	}catch (Exception e) {
		log.error("查询督办员出错", e);
		}
    	return "supEmp";
    }
    
    public String openWinSup(){
    	return "trunsup";
    }
    
    /**
     * 转督办
     * 2014.9.2
     */
    public void transup(){
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
    		List<Map<String, Object>> list = tMessagePublishService.querysupervise();
    		message.setEmpId("");
    		message.setEmpName("");
    		for(int i=0;i<list.size();i++){
    			message.setEmpId(message.getEmpId() +list.get(i).get("EMPID"));
    			message.setEmpName(message.getEmpName() + list.get(i).get("EMPNAME"));
    			if(i!=list.size()-1){
    				message.setEmpId(message.getEmpId() +",");
    				message.setEmpName(message.getEmpName() +",");
    			}
    		}
    		messageId = String.valueOf(message.getMessageId());
    		tMessagePublishService.insertMessageTransmit(message, muo,messageId,"");
			info="success";
		} catch (Exception e) {
			info="fails";
			log.error("保存转发信息失败", e);
		}
		Struts2Utils.renderText(info);
    }
    
    /**
     * 转督办
     * @return
     */
    public void megTstSup(){
    	String info="";
    	try {
    		MUOUserSession muo=getCurrentOnlineUser();
		    message.setCreateEmpid(muo.getEmpid());
		    message.setOrgid(String.valueOf(muo.getOrgid()));
		   info= tMessagePublishService.insertMegTstSup(message, muo, taskAssgineeDto);
		} catch (Exception e) {
			info="fails";
          log.error("行长转督办", e);
        
		}
		Struts2Utils.renderText(info);
    }
   

	public List<TMessagePublish> getResults() {
		return results;
	}

	public void setResults(List<TMessagePublish> results) {
		this.results = results;
	}

	public TMessagePublish getMessage() {
		return message;
	}

	public void setMessage(TMessagePublish message) {
		this.message = message;
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

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
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

	public List<TApproveOpninion> getOpninions() {
		return opninions;
	}

	public void setOpninions(List<TApproveOpninion> opninions) {
		this.opninions = opninions;
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

	public TaskAssgineeDto getTaskAssgineeDto() {
		return taskAssgineeDto;
	}

	public void setTaskAssgineeDto(TaskAssgineeDto taskAssgineeDto) {
		this.taskAssgineeDto = taskAssgineeDto;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<TMessagePublish> getEmplist() {
		return emplist;
	}

	public void setEmplist(List<TMessagePublish> emplist) {
		this.emplist = emplist;
	}
    
	
}