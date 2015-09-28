package test.gotop.demo.system.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import test.gotop.demo.system.dao.ITuserBiz;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.util.XmlConvert;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

public class TuserAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     */
    protected ITuserBiz tuserBiz;
    
    public ITuserBiz getTuserBiz() {
		return tuserBiz;
	}

	public void setTuserBiz(ITuserBiz tuserBiz) {
		this.tuserBiz = tuserBiz;
	}
	/**
     * 查询datacell列表.
     */
	
    public void queryUserDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = tuserBiz.selectUserByMap(hm , page);
        pageCondExpand pce = new pageCondExpand();
        pce.putPageCond(page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }
    /**
     * 查询datacell列表.
     */
    public void queryFunDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
    	List data = tuserBiz.selectFunByMap(hm , page);
    	pageCondExpand pce = new pageCondExpand();
    	pce.putPageCond(page);
    	String xmlStr = XmlConvert.getXmlListBean(page,data);
    	MUO.write(xmlStr);
    }

    /**
     * comboselect演示.
     */
    public void comboUserSelect() throws Exception {
    	HashMap params = new HashMap();
    	log.info(params);
    	List combo = tuserBiz.selectUserByMapBox(params);
    	String dataresult = XmlConvert.getXmlListBean(combo);
    	MUO.write(dataresult);
    }
    /**
     * 功能comboselect演示.
     */
    public void comboFunSelect() throws Exception {
    	HashMap combopara = this.getCombopara();
    	if(combopara!=null){
    		List combo = tuserBiz.selectFunByMapBox(combopara);
    		
    		
    		String dataresult = XmlConvert.getXmlListBean(combo);
    		MUO.write(dataresult);
    	}
    }
    /**
     * 角色comboselect演示.
     */
    public void comboRoleSelect() throws Exception {
    	HashMap params = new HashMap();
    	List combo = tuserBiz.selectRoleByMapBox(params);
    	String dataresult = XmlConvert.getXmlListBean(combo);
    	MUO.write(dataresult);
    }
    
    public void selectRoleById() throws Exception {
    	String rid = MUO.getParameter("rid");
//    	log.info(rid);
    	if(StringUtils.isBlank( rid)){
    		MUO.write("RID不能为空");
    		return;
    	}
    	HashMap hm = tuserBiz.selectRoleById(Integer.valueOf(rid));
    	MUO.write(hm.toString());
    }
    /**
     * JDBC只读访问方式
     * @throws Exception
     */
    public void findJdbcOnly() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
    	List data = tuserBiz.queryJdbcOnly(hm , page);
    	pageCondExpand pce = new pageCondExpand();
    	pce.putPageCond(page);
    	String xmlStr = XmlConvert.getXmlListBean(page,data);
    	MUO.write(xmlStr);
    }
    /**
     * JDBC滚动模式
     * @throws Exception
     */
    public void findJdbcScroll() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
    	List data = tuserBiz.queryJdbcScroll(hm , page);
    	pageCondExpand pce = new pageCondExpand();
    	pce.putPageCond(page);
    	String xmlStr = XmlConvert.getXmlListBean(page,data);
    	MUO.write(xmlStr);
    }
    
    public void findSqlMap() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
    	List data = tuserBiz.querySqlMap(hm , page);
    	pageCondExpand pce = new pageCondExpand();
    	pce.putPageCond(page);
    	String xmlStr = XmlConvert.getXmlListBean(page,data);
    	MUO.write(xmlStr);
    }
    
    public void findSqlHasNext() throws Exception {
    	AjaxParam apm = XmlConvert.queryDatacell();
    	Page page = apm.getPage();
    	HashMap hm = apm.getParams();
    	List data = tuserBiz.findSqlHasNext(hm , page);
    	pageCondExpand pce = new pageCondExpand();
    	pce.putPageCond(page);
    	String xmlStr = XmlConvert.getXmlListBean(page,data);
    	MUO.write(xmlStr);
    }
    
    public void updateTest(){
    	tuserBiz.update_test(1);
    }
}