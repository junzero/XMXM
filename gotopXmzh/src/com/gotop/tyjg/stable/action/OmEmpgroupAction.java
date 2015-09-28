package com.gotop.tyjg.stable.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.tyjg.stable.dao.ITestWebService;
import com.gotop.tyjg.stable.service.IOmEmpgroupService;
import com.gotop.util.XmlConvert;
import com.gotop.util.exception.GotopException;
import com.opensymphony.xwork2.Action;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;

public class OmEmpgroupAction extends BaseAction {
	
	private ITestWebService testWebService;
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected IOmEmpgroupService omEmpgroupService;
    
    private String oes;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void setOmEmpgroupService(IOmEmpgroupService omEmpgroupService) {
        this.omEmpgroupService = omEmpgroupService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public IOmEmpgroupService getOmEmpgroupService() {
        return this.omEmpgroupService;
    }

    /**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public String queryDataGrid() throws Exception {
    	try{
    		int a = Integer.parseInt("a");
    	}catch(Exception e){
    		log.error("异常测试",e);
    		throw new GotopException("异常测试",1);
    	}
        AjaxParam apm = XmlConvert.queryDatacell();
        if(apm==null){
        	return Action.INPUT;
        }
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = omEmpgroupService.queryDataGrid(hm , page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
        
        return null;
    }

    public String ttttt(){
    	
    	return "viewlist";
    }
    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        omEmpgroupService.updateDataGrid(hmp);
    }

    /**
     * comboselect演示.
     * @abatorgenerated
     */
    public void comboSelect() throws Exception {
        HashMap combopara = this.getCombopara();
        if(combopara!=null){
            	List combo = omEmpgroupService.queryAllDataList(combopara);
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
        List orgs = omEmpgroupService.queryPageDataList(hm,page);
        request.setAttribute("orgs", orgs);
        request.setAttribute("page", page);
        return "viewlist";
    }

	public String getOes() {
		return oes;
	}

	public void setOes(String oes) {
		this.oes = oes;
	}

	public ITestWebService getTestWebService() {
		return testWebService;
	}

	public void setTestWebService(ITestWebService testWebService) {
		this.testWebService = testWebService;
	}
	
	public void runWebS(){
		this.testWebService.runWeb();
	}
}