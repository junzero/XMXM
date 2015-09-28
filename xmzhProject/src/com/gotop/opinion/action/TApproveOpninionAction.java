package com.gotop.opinion.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.opinion.service.ITApproveOpninionService;
import com.gotop.util.Struts2Utils;
import com.gotop.util.XmlConvert;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class TApproveOpninionAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITApproveOpninionService tApproveOpninionService;
    protected JbpmService jbpmService;

    TApproveOpninion opninion;
    
    private String resourceId;
    
    private String resourceType;
    
    private String resourceFlow;
    
    public TApproveOpninion getOpninion() {
		return opninion;
	}

	public void setOpninion(TApproveOpninion opninion) {
		this.opninion = opninion;
	}

	/**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settApproveOpninionService(ITApproveOpninionService tApproveOpninionService) {
        this.tApproveOpninionService = tApproveOpninionService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITApproveOpninionService gettApproveOpninionService() {
        return this.tApproveOpninionService;
    }

    public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceFlow() {
		return resourceFlow;
	}

	public void setResourceFlow(String resourceFlow) {
		this.resourceFlow = resourceFlow;
	}

	/**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public void queryDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = tApproveOpninionService.queryDataGrid(hm , page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }

    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        tApproveOpninionService.updateDataGrid(hmp);
    }

    /**
     * comboselect演示.
     * @abatorgenerated
     */
    public void comboSelect() throws Exception {
        HashMap combopara = this.getCombopara();
        if(combopara!=null){
            	List combo = tApproveOpninionService.queryAllDataList(combopara);
            	String dataresult = XmlConvert.getXmlListBean(combo);
            	MUO.write(dataresult);
        }
    }

    /**
     * viewDataList说明.
     * @abatorgenerated
     */
    public String queryViewList() throws Exception {
        HttpServletRequest request=ServletActionContext.getRequest();
        Page page = this.getPage();
        HashMap hm = new HashMap();
        if(resourceId==null || "".equals(resourceId)){
        	TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
        	taskAssgineeDto.setExecutionId(resourceFlow);
    		TProcessBusiness processBusiness = jbpmService.findProcessBusiness(taskAssgineeDto);
        	resourceId = String.valueOf(processBusiness.getBusinessKey());
        }
        hm.put("resourceId", resourceId);
        hm.put("resourceType", resourceType);
        List data = tApproveOpninionService.queryOpninions(hm,page);
        request.setAttribute("data", data);
        request.setAttribute("page", page);
        return "viewlist";
    }
    
    public String downExl() throws Exception {
    	HttpServletRequest request=ServletActionContext.getRequest();
        HashMap hm = new HashMap();
        if(resourceId==null || "".equals(resourceId)){
        	TaskAssgineeDto taskAssgineeDto = new TaskAssgineeDto();
        	taskAssgineeDto.setExecutionId(resourceFlow);
    		TProcessBusiness processBusiness = jbpmService.findProcessBusiness(taskAssgineeDto);
        	resourceId = String.valueOf(processBusiness.getBusinessKey());
        }
        hm.put("resourceId", resourceId);
        hm.put("resourceType", resourceType);
        List data = tApproveOpninionService.queryOpninions(hm,null);
        request.setAttribute("data", data);
        return "downExl";
    }
    
	/**
	 * 异步获取所有请假审核表
	 * @throws Exception
	 */
	public void queryOpninions() throws Exception{
		try {
  		  	AjaxParam apm = XmlConvert.queryDatacell();
  		  	Page page = apm.getPage();
  			HashMap hm = apm.getParams();
  			List data = tApproveOpninionService.queryOpninions(hm , page);
  	        String xmlStr = XmlConvert.getXmlListBean(page,data);
  	        MUO.write(xmlStr);
		} catch (Exception e) {
			log.error("查询意见列表出错", e);
		}
	}
	
	/**
	 * 结束流程意见插入
	 * @throws Exception 
	 */
	public String insertDeleteProcessOpninions() throws Exception{
		String info ="success";
		try {
		MUOUserSession muo=getCurrentOnlineUser();
		//ResourceId-业务传递
		//ResourceType-业务传递
    	opninion.setOperator(muo.getEmpid());
    	opninion.setOrgid(String.valueOf(muo.getOrgid()));
    	//结束-08
    	opninion.setOperatorType("08");
    	String currDate=TimeUtil.getCntDtStr(new Date(), "yyyyMMddHHmmss");
    	opninion.setOperaterDate(currDate.substring(0,8));
    	opninion.setOperaterTime(currDate.substring(8));
    	opninion.setOpninionContent("起草人结束流程");
    	opninion.setNextOprName("");
		opninion.setNextorgname("");
		tApproveOpninionService.insertDeleteProcessOpninions(opninion);
		} catch (Exception e) {
			info="fails";
			log.error("[插入意见失败！]", e);
			throw e;
		}
		Struts2Utils.renderText(info);
		return null;
	}
}