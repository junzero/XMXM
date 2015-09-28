package test.gotop.crm.demo.action.celltest;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.util.XmlConvert;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import test.gotop.crm.demo.service.celltest.IAcOperatorService;

public class AcOperatorAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected IAcOperatorService acOperatorService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void setAcOperatorService(IAcOperatorService acOperatorService) {
        this.acOperatorService = acOperatorService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public IAcOperatorService getAcOperatorService() {
        return this.acOperatorService;
    }

    /**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public void queryDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = acOperatorService.queryDataGrid(hm , page);
        pageCondExpand pce = new pageCondExpand();
        pce.putPageCond(page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }

    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        acOperatorService.updateDataGrid(hmp);
    }

    /**
     * comboselect演示.
     * @abatorgenerated
     */
    public void comboSelect() throws Exception {
        HashMap combopara = this.getCombopara();
        if(combopara!=null){
            	List combo = acOperatorService.queryAllDataList(combopara);
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
        Page page = this.getPageObj(request);
        HashMap hm = new HashMap();
        List orgs = acOperatorService.queryPageDataList(hm,page);
        pageCondExpand pce = new pageCondExpand();
        pce.putPageCond(page);
        request.setAttribute("orgs", orgs);
        request.setAttribute("page", page);
        return "viewlist";
    }
}