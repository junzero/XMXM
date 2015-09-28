package com.gotop.tyjg.roleprivilege.struts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.eos.system.utility.XmlUtil;
import com.gotop.crm.util.BaseAction;
import com.gotop.taglib.auth.RoleManager;
import com.gotop.tyjg.roleprivilege.model.AcApplication;
import com.gotop.tyjg.roleprivilege.model.AcFuncGroup;
import com.gotop.tyjg.roleprivilege.model.AcFunction;
import com.gotop.tyjg.roleprivilege.model.AcRole;
import com.gotop.tyjg.roleprivilege.model.AcRoleFunc;
import com.gotop.tyjg.roleprivilege.model.AcRoleFuncLock;
import com.gotop.tyjg.roleprivilege.service.IRoleService;
import com.gotop.util.XmlConvert;
import com.gotop.util.exception.GotopException;
import com.gotop.vo.system.MUOUserSession;
import com.opensymphony.xwork2.ActionContext;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

public class RoleAction extends BaseAction{
    private IRoleService roleService;
    private AcRole acRole;
    private List<AcFuncGroup> funcGroupList;
    private List<AcFuncGroup> subFuncGroupList;
    private List<AcFunction> funcList;
    private List<AcApplication> appList;
    private List<AcRoleFunc> acRoleFuncList;
    private List<AcRoleFunc> acRoleFuncGroupList;
    private List<AcRoleFuncLock> acRoleFuncLockList;
    /**
     * 保存权限选择信息
     * @return
     * @throws Exception 
     */
    public String savePrivelege() throws Exception {
    	HttpServletRequest request=ServletActionContext.getRequest();
		String ajax = request.getParameter("ajax");
		Document document = XmlConvert.getAjax();
		HashMap hmps = new HashMap();
		if(ajax.indexOf("<root><params><param>")>0){
			Node pageNode = XmlUtil.findNode(document, "root/params/param/");
			NodeList funcNodeList = XmlUtil.findNodes(document, "root/params/param/value/func");
			NodeList funcGroupNodeList = XmlUtil.findNodes(document, "root/params/param/value/funcGroup");
			NodeList subFuncGroupNodeList = XmlUtil.findNodes(document, "root/params/param/value/subFuncGroup");
			NodeList acRoleNodeList = XmlUtil.findNodes(document, "root/params/param/value/acRole");
			if(funcNodeList.getLength()>0){
				List funcList = XmlConvert.getMapList(funcNodeList);
				hmps.put("funcList", funcList);
			}
			if(funcGroupNodeList.getLength()>0){
				List funcGroupList = XmlConvert.getMapList(funcGroupNodeList);
				hmps.put("funcGroupList", funcGroupList);
			}
			if(subFuncGroupNodeList.getLength()>0){
				List subFuncGroupList = XmlConvert.getMapList(subFuncGroupNodeList);
				hmps.put("subFuncGroupList", subFuncGroupList);
			}
			if(acRoleNodeList.getLength()>0){
				List acRole = XmlConvert.getMapList(acRoleNodeList);
				hmps.put("acRole", acRole.get(0));
			}
		}
		this.roleService.savePrivilege(hmps);
/*    	System.out.println("==================================");
    	AjaxParam ap = XmlConvert.queryDatacell();
		HashMap hm = ap.getParams();*/
		/**
		 * 角色功能变更
		 * 角色功能组变更
		 */
		String result = "<root><data><flag>1</flag></data></root>";
		this.write(result);
    	return null;
    }
    /**
     * 进入功能权限操作前的数据准备
     * @return
     * @throws Exception
     */
    public String toManagePrivelege() throws Exception {
    	
    	Map request = (Map)ActionContext.getContext().get("request");
    	String roleReadonly = (String)request.get("roleReadonly");
    	//查询所有应用
    	AcApplication acApplication = new AcApplication();
    	acApplication.setAppId("121");
    	appList = this.roleService.queryAcApplicationList(acApplication);
    	//查询所有一级功能组com.gotop.tyjg.menumanagement.model.AcFunctionGroup.java
    	AcFuncGroup acFuncGroup = new AcFuncGroup();
    	funcGroupList = this.roleService.queryAcFuncGroupList(acFuncGroup);
    	//查询二级功能组
    	subFuncGroupList = this.roleService.querySubAcFuncGroupList(acFuncGroup);
    	AcFunction acFunction = new AcFunction();
    	//查询功能列表
    	funcList = this.roleService.queryAcFunctionList(acFunction);
    	
    	//查询角色功能对应关系列表
    	AcRoleFunc acRoleFunc = new AcRoleFunc();
    	acRoleFunc.setRoleId(acRole.getRoleId());
    	acRoleFuncList = this.roleService.queryAcRoleFuncList(acRoleFunc);
    	
    	acRoleFuncGroupList = this.roleService.queryAcRoleFuncGroupList(acRoleFunc);
    	
    	//查询角色功能组对应关系列表
    	AcRoleFuncLock acRoleFuncLock = new AcRoleFuncLock();
    	acRoleFuncLock.setRoleId(acRole.getRoleId());
    	acRoleFuncLockList = this.roleService.queryAcRoleFuncLockList(acRoleFuncLock);
    	this.roleService.setRoleCheck(funcGroupList, subFuncGroupList, funcList, acRoleFuncList, acRoleFuncLockList);
    	
    	return "success";
    }
	/**
	 * 分页查询角色列表
	 * @return
	 * @throws Exception
	 */
	public String queryRolePage() throws Exception {
		AjaxParam ap = XmlConvert.queryDatacell();
		Page page = ap.getPage();
		HashMap hm = ap.getParams();
		List<AcRole> acRoleList = this.getRoleService().queryRoleList(hm, page);
		pageCondExpand pce = new pageCondExpand();
		pce.putPageCond(page);
		String xmlStr = XmlConvert.getXmlListBean(page,acRoleList);
		this.write(xmlStr);
		return null;
	}
	
	public String isRoleExist() {
		
		return null;
	}
	
	public void reloadAuth() throws Exception{
		boolean ror = RoleManager.isOpRole(null, "roleManage"); 
		if(ror){
			RoleManager.reloadAuth();
			this.write("操作成功");
		}else{
			throw new GotopException("操作失败！");
		}
	}
	/**
	 * datacell数据保存时的增删改操作
	 * @return
	 * @throws Exception
	 */
	public String saveRole() throws Exception {
		HashMap hm = XmlConvert.updateDatacell();
    	Map session =  ActionContext.getContext().getSession();
    	MUOUserSession userSession = (MUOUserSession)session.get("login_user");
    	String operatorId = userSession.getEmpid().toString();
    	hm.put("operatorId", operatorId);
		this.getRoleService().saveRole(hm);
		String result = "<root><data><flag>1</flag></data></root>";
		this.write(result);
		return null;
	}
	
	/**
	 * 进入角色管理页面时的数据操作
	 * @return
	 * @throws Exception
	 */
    public String toQueryRolePage() throws Exception {
    	//operatorId默认为1
    	Map session =  ActionContext.getContext().getSession();
    	MUOUserSession userSession = (MUOUserSession)session.get("login_user");
    	String operitorid = userSession.getEmpid().toString();
    	String roleType = this.getRoleService().queryRoleTypeByOperid(operitorid);
    	return "success";
    }
	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public AcRole getAcRole() {
		return acRole;
	}
	public void setAcRole(AcRole acRole) {
		this.acRole = acRole;
	}

	public List<AcFunction> getFuncList() {
		return funcList;
	}
	public void setFuncList(List<AcFunction> funcList) {
		this.funcList = funcList;
	}
	public List<AcFuncGroup> getFuncGroupList() {
		return funcGroupList;
	}
	public void setFuncGroupList(List<AcFuncGroup> funcGroupList) {
		this.funcGroupList = funcGroupList;
	}
	public List<AcFuncGroup> getSubFuncGroupList() {
		return subFuncGroupList;
	}
	public void setSubFuncGroupList(List<AcFuncGroup> subFuncGroupList) {
		this.subFuncGroupList = subFuncGroupList;
	}
	public List<AcApplication> getAppList() {
		return appList;
	}
	public void setAppList(List<AcApplication> appList) {
		this.appList = appList;
	}
	public List<AcRoleFunc> getAcRoleFuncList() {
		return acRoleFuncList;
	}
	public void setAcRoleFuncList(List<AcRoleFunc> acRoleFuncList) {
		this.acRoleFuncList = acRoleFuncList;
	}
	public List<AcRoleFuncLock> getAcRoleFuncLockList() {
		return acRoleFuncLockList;
	}
	public void setAcRoleFuncLockList(List<AcRoleFuncLock> acRoleFuncLockList) {
		this.acRoleFuncLockList = acRoleFuncLockList;
	}
	public List<AcRoleFunc> getAcRoleFuncGroupList() {
		return acRoleFuncGroupList;
	}
	public void setAcRoleFuncGroupList(List<AcRoleFunc> acRoleFuncGroupList) {
		this.acRoleFuncGroupList = acRoleFuncGroupList;
	}
}
