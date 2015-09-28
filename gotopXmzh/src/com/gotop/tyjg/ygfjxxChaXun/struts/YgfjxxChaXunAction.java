package com.gotop.tyjg.ygfjxxChaXun.struts;

import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.eos.system.utility.XmlUtil;
import com.gotop.crm.util.BaseAction;
import com.gotop.tyjg.ygfjxxChaXun.service.IYgfjxxChaXunService;
import com.gotop.util.XmlConvert;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;

public class YgfjxxChaXunAction extends BaseAction{

	private static final long serialVersionUID = -4647069548195095580L;
	
	private IYgfjxxChaXunService ygfjxxService;

	public IYgfjxxChaXunService getYgfjxxService() {
		return ygfjxxService;
	}

	public void setYgfjxxService(IYgfjxxChaXunService ygfjxxService) {
		this.ygfjxxService = ygfjxxService;
	}
	
	/**
	 * datacell中submitAction方法
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String dataCellSubmit() throws Exception{
		AjaxParam apm = XmlConvert.queryDatacell();
		HashMap hmp = XmlConvert.updateDatacell();
		String ajax = this.getRequest().getParameter("ajax");
		this.ygfjxxService.dataCellSubmitSave(hmp);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String queryYgfjxxByCondit() throws Exception{
		HashMap cdMap =new HashMap();
		AjaxParam apm =  XmlConvert.queryDatacell();
		Page page = apm.getPage();
		List dataList =this.ygfjxxService.queryYgfjxxByCondit(cdMap,page);
		this.setPage(page);
		String abftygfjxxbs = XmlConvert.getXmlListBean(dataList);
		this.write(abftygfjxxbs);
		return null;
	}
	
	
	/**
	 * datacell更新修改获取,HashMap方式
	 * @param request
	 * @return insertEntities updateEntities deleteEntities
	 */
	@SuppressWarnings("unchecked")
	public static HashMap getRowByHashMap(String ajax) throws Exception{
		Document document = XmlUtil.parseString(ajax.toString());
		NodeList updateEntities = XmlUtil.findNodes(document, "root/data/entry/string/updateEntities");
		NodeList updateEntities2 = XmlUtil.findNodes(document, "root/data/entry/updateEntities");
		NodeList updateEntities3 = XmlUtil.findNodes(document, "root/entry/updateEntities");
		NodeList deleteEntities = XmlUtil.findNodes(document, "root/entry/deleteEntities");
		NodeList insertEntities = XmlUtil.findNodes(document, "root/entry/insertEntities");
		HashMap hmps = new HashMap();
		if(updateEntities.getLength()>0){
			List updateList = XmlConvert.getMapList(updateEntities);
			hmps.put("updateEntities", updateList);
		}
		if(deleteEntities.getLength()>0){
			List updateList = XmlConvert.getMapList(deleteEntities);
			hmps.put("deleteEntities", updateList);
		}
		if(insertEntities.getLength()>0){
			List updateList = XmlConvert.getMapList(insertEntities);
			hmps.put("insertEntities", updateList);
		}
		return hmps;
	}
	
	
	
	
}
