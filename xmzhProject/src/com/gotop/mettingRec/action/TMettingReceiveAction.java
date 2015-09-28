package com.gotop.mettingRec.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.mettingRec.service.ITMettingReceiveService;
import com.gotop.util.XmlConvert;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class TMettingReceiveAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITMettingReceiveService tMettingReceiveService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settMettingReceiveService(ITMettingReceiveService tMettingReceiveService) {
        this.tMettingReceiveService = tMettingReceiveService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITMettingReceiveService gettMettingReceiveService() {
        return this.tMettingReceiveService;
    }

    /**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public void queryDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = tMettingReceiveService.queryDataGrid(hm , page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }

    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        tMettingReceiveService.updateDataGrid(hmp);
    }

    /**
     * comboselect演示.
     * @abatorgenerated
     */
    public void comboSelect() throws Exception {
        HashMap combopara = this.getCombopara();
        if(combopara!=null){
            	List combo = tMettingReceiveService.queryAllDataList(combopara);
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
        List orgs = tMettingReceiveService.queryPageDataList(hm,page);
        request.setAttribute("orgs", orgs);
        request.setAttribute("page", page);
        return "viewlist";
    }
}