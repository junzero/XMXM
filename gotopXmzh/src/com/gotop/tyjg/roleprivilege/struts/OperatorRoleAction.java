package com.gotop.tyjg.roleprivilege.struts;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gotop.crm.util.BaseAction;
import com.gotop.tyjg.roleprivilege.model.AcOperatorRole;
import com.gotop.tyjg.roleprivilege.model.AcRole;
import com.gotop.tyjg.roleprivilege.model.OmPartyRole;
import com.gotop.tyjg.roleprivilege.service.IOperatorRoleService;
import com.gotop.util.XmlConvert;
import com.gotop.vo.system.MUOUserSession;
import com.opensymphony.xwork2.ActionContext;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

public class OperatorRoleAction extends BaseAction{
	private IOperatorRoleService operatorRoleService;
    private AcRole acRole;
    private String roleReadonly;
    private OmPartyRole omPartyRole;
    private List<AcOperatorRole> acOperatorRoleList;
    private List<OmPartyRole> omPartyRoleList;
    
    public String deleteOmPartyRole() throws Exception {
    	AjaxParam ap = XmlConvert.queryDatacell();
		HashMap hm = ap.getParams();
		this.operatorRoleService.deleteOmPartyRole(hm);
		String flag = "1";
		String result = "<root><data><flag>" + flag + "</flag></data></root>";
		this.write(result);
    	return null;
    }
    public String insertOmPartyRole() throws Exception {
    	Map session =  ActionContext.getContext().getSession();
    	MUOUserSession userSession = (MUOUserSession)session.get("login_user");
    	AjaxParam ap = XmlConvert.queryDatacell();
		HashMap hm = ap.getParams();
		this.operatorRoleService.insertOmPartyRole(hm);
		String flag = "1";
		String result = "<root><data><flag>" + flag + "</flag></data></root>";
		this.write(result);
    	return null;
    }
    /**
     * 添加机构时需要查询的数据没有与当前角色相关联的机构列表
     * @return
     * @throws Exception
     */
    public String toInsertOmPartyRole() throws Exception {
    	Map session =  ActionContext.getContext().getSession();
    	MUOUserSession userSession = (MUOUserSession)session.get("login_user");
    	OmPartyRole omPartyRole1 = new OmPartyRole();
    	omPartyRole1.setRoleId(this.acRole.getRoleId());
    	omPartyRole1.setJgsx(userSession.getOrgJgsx());
    	if(omPartyRole != null) {
    		omPartyRole1.setOrgName(omPartyRole.getOrgName());
    		omPartyRole1.setOrgCode(omPartyRole.getOrgCode());
    	}
    	
		Page page = this.getPage();
		
		String orgseq = this.getCurrentOnlineUser().getOrgseq();
		omPartyRole1.setOrgId(orgseq);
		
		this.omPartyRoleList = this.operatorRoleService.queryOmPartyRoleNotList(omPartyRole1, page);
		pageCondExpand pce = new pageCondExpand();
		pce.putPageCond(page);
    	return "success";
    }
    /**
     * 在进入保存角色机构关系前的数据准备
     * @return
     * @throws Exception
     */
    public String toDistributeOrgRole() throws Exception {
    	OmPartyRole omPartyRole = new OmPartyRole();
    	omPartyRole.setRoleId(this.acRole.getRoleId());
    	omPartyRole.setOrgId(this.acRole.getOrgId());
		Page page = this.getPage();
		this.omPartyRoleList = this.operatorRoleService.queryOmPartyRoleList(omPartyRole, page);
		pageCondExpand pce = new pageCondExpand();
		pce.putPageCond(page);
    	return "success";
    }
    /**
     * 保存角色人员关系（包括新增和删除）
     * @return
     * @throws Exception 
     * @throws Exception
     */
    public String saveOperatorRole() throws Exception {
    	AjaxParam ap = XmlConvert.queryDatacell();
		HashMap hm = ap.getParams();
		String flag = "1";
    	try {
			this.operatorRoleService.saveOperatorRole(hm);
		} catch (SQLException e) {
			flag = "0";
			e.printStackTrace();
		}
    	String result = "<root><data><flag>" + flag + "</flag></data></root>";
		this.write(result);
    	return null;
    }
    
    /**
     * 查询当前角色下的人员（将人员组成字符串）
     * @return
     * @throws Exception
     */
    public String queryOperatorRoleEmp() throws Exception {
    	AjaxParam ap = XmlConvert.queryDatacell();
		HashMap hm = ap.getParams();
    	HashMap hm1 = this.operatorRoleService.queryAcOperatorRoleEmp(hm);
    	String result = "<root><data>";
    	if(hm1!=null){
    		result += "<flag>1</flag>";
    		result += "<empIds>" + (String)hm1.get("empIds") + "</empIds>";
    		result += "<empNames>" + (String)hm1.get("empNames") + "</empNames>";
    	}else{
    		result += "<flag>2</flag>";
    	}
    	result += "</data></root>";
    	this.write(result);
    	return null;
    }
    
    /**
     * 进入人员角色列表页面前的查询工作
     * @return
     * @throws SQLException
     */
	public String toDistributeOperatorRole() throws SQLException {
		AcOperatorRole acOperatorRole = new AcOperatorRole();
		acOperatorRole.setRoleId(this.acRole.getRoleId());
		acOperatorRole.setOrgId(this.acRole.getOrgId());
		Page page = this.getPage();
		this.acOperatorRoleList = this.operatorRoleService.queryAcOperatorRoleList(acOperatorRole, page);
		pageCondExpand pce = new pageCondExpand();
		pce.putPageCond(page);
		return "success";
	}
	
	/**
	 * 人员角色关系删除
	 * @return
	 * @throws Exception 
	 */
	public String deleteOperatorRole() throws Exception {
		AjaxParam ap = XmlConvert.queryDatacell();
		HashMap hm = ap.getParams();
		this.operatorRoleService.deleteOperatorRole(hm);
		String result = "<root><data><flag>1</flag></data></root>";
		this.write(result);
		return null;
	}
	public AcRole getAcRole() {
		return acRole;
	}
	public void setAcRole(AcRole acRole) {
		this.acRole = acRole;
	}
	public String getRoleReadonly() {
		return roleReadonly;
	}
	public void setRoleReadonly(String roleReadonly) {
		this.roleReadonly = roleReadonly;
	}
	public IOperatorRoleService getOperatorRoleService() {
		return operatorRoleService;
	}
	public void setOperatorRoleService(IOperatorRoleService operatorRoleService) {
		this.operatorRoleService = operatorRoleService;
	}
	public List<AcOperatorRole> getAcOperatorRoleList() {
		return acOperatorRoleList;
	}
	public void setAcOperatorRoleList(List<AcOperatorRole> acOperatorRoleList) {
		this.acOperatorRoleList = acOperatorRoleList;
	}
	public List<OmPartyRole> getOmPartyRoleList() {
		return omPartyRoleList;
	}
	public void setOmPartyRoleList(List<OmPartyRole> omPartyRoleList) {
		this.omPartyRoleList = omPartyRoleList;
	}
	public OmPartyRole getOmPartyRole() {
		return omPartyRole;
	}
	public void setOmPartyRole(OmPartyRole omPartyRole) {
		this.omPartyRole = omPartyRole;
	}

}
