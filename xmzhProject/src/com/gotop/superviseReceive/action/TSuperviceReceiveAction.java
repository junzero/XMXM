package com.gotop.superviseReceive.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.superviseReceive.service.ITSuperviceReceiveService;
import com.gotop.util.XmlConvert;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class TSuperviceReceiveAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITSuperviceReceiveService tSuperviceReceiveService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settSuperviceReceiveService(ITSuperviceReceiveService tSuperviceReceiveService) {
        this.tSuperviceReceiveService = tSuperviceReceiveService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITSuperviceReceiveService gettSuperviceReceiveService() {
        return this.tSuperviceReceiveService;
    }

    /**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public void queryDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = tSuperviceReceiveService.queryDataGrid(hm , page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }

    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        tSuperviceReceiveService.updateDataGrid(hmp);
    }

    /**
     * comboselect演示.
     * @abatorgenerated
     */
    public void comboSelect() throws Exception {
        HashMap combopara = this.getCombopara();
        if(combopara!=null){
            	List combo = tSuperviceReceiveService.queryAllDataList(combopara);
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
        List orgs = tSuperviceReceiveService.queryPageDataList(hm,page);
        request.setAttribute("orgs", orgs);
        request.setAttribute("page", page);
        return "viewlist";
    }
}