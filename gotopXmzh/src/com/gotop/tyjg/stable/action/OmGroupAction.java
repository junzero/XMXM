package com.gotop.tyjg.stable.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.tyjg.stable.service.IOmGroupService;
import com.gotop.util.XmlConvert;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class OmGroupAction extends BaseAction {
    /**
	 * 通过spring注入的Service对象.
	 * @abatorgenerated
	 */
	protected IOmGroupService omGroupService;

	/**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void setOmGroupService(IOmGroupService omGroupService) {
        this.omGroupService = omGroupService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public IOmGroupService getOmGroupService() {
        return this.omGroupService;
    }

    /**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public void queryDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = omGroupService.queryDataGrid(hm , page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }

    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        omGroupService.updateDataGrid(hmp);
    }

    /**
     * comboselect演示.
     * @abatorgenerated
     */
    public void comboSelect() throws Exception {
        HashMap combopara = this.getCombopara();
        if(combopara!=null){
            	List combo = omGroupService.queryAllDataList(combopara);
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
        List orgs = omGroupService.queryPageDataList(hm,page);
        request.setAttribute("orgs", orgs);
        request.setAttribute("page", page);
        return "viewlist";
    }
    
    /**
     * viewDataList说明.
     * @abatorgenerated
     */
    public String queryViewListTest() throws Exception {
        return "viewlist";
    }
}