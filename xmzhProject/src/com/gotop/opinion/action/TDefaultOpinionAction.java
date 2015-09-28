package com.gotop.opinion.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.opinion.model.TDefaultOpinion;
import com.gotop.opinion.service.ITDefaultOpinionService;
import com.gotop.util.XmlConvert;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class TDefaultOpinionAction extends BaseAction {
    /**
	 * 通过spring注入的Service对象.
	 * @abatorgenerated
	 */
	protected ITDefaultOpinionService tDefaultOpinionService;
	
	/**
	 * 意见
	 */
	private String opinion;
	
	/**
	 * 编号
	 */
	private Long recId;
	
	/**
	 * 标志是否成功
	 */
	private String flag = "success";

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return recId
	 */
	public Long getRecId() {
		return recId;
	}

	/**
	 * @param recId 要设置的 recId
	 */
	public void setRecId(Long recId) {
		this.recId = recId;
	}

	/**
	 * @return opinion
	 */
	public String getOpinion() {
		return opinion;
	}

	/**
	 * @param opinion 要设置的 opinion
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	/**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settDefaultOpinionService(ITDefaultOpinionService tDefaultOpinionService) {
        this.tDefaultOpinionService = tDefaultOpinionService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITDefaultOpinionService gettDefaultOpinionService() {
        return this.tDefaultOpinionService;
    }

    /**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public void queryDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = tDefaultOpinionService.queryDataGrid(hm , page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }

    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        tDefaultOpinionService.updateDataGrid(hmp);
    }

    /**
     * viewDataList说明.
     * @abatorgenerated
     */
    public String queryViewList() throws Exception {
        HttpServletRequest request=ServletActionContext.getRequest();
        Long empid = this.getCurrentOnlineUser().getEmpid();
        List<TDefaultOpinion> opinionList = tDefaultOpinionService.queryAllDataList(empid);
        request.setAttribute("opinionList", opinionList);
        return "viewlist";
    }
    
    public String saveOrUpdate() throws Exception{
    	try{
	    	TDefaultOpinion tDefaultOpinion = new TDefaultOpinion();
	    	MUOUserSession muo = this.getCurrentOnlineUser();
	    	if(recId==null || "".equals(recId)){
	    		tDefaultOpinion.setOpinion(opinion);
	    		tDefaultOpinion.setEmpId(muo.getEmpid());
	    		this.tDefaultOpinionService.insert(tDefaultOpinion);
	    	}else{
	    		tDefaultOpinion.setRecId(recId);
	    		tDefaultOpinion.setOpinion(opinion);
	    		tDefaultOpinionService.update(tDefaultOpinion);
	    	}
    	} catch (Exception e) {
    		log.error("[插入或更新默认意见出错]", e);
    		flag="fails";
    		throw e;
    	}
    	return "opinions";
    }
    
    public String deleteOp() throws Exception{
    	try{
	    	TDefaultOpinion tDefaultOpinion = new TDefaultOpinion();
	    	if(recId!=null && !"".equals(recId)){
	    		tDefaultOpinion.setRecId(recId);
	    		this.tDefaultOpinionService.delete(tDefaultOpinion);
	    	}
    	} catch (Exception e) {
    		log.error("[删除意见出错]", e);
    		flag="fails";
    		throw e;
    	}
    	return "opinions";
    }
}