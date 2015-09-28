package com.gotop.tyjg.workgroup.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.tyjg.common.model.ChangeTree;
import com.gotop.tyjg.common.model.Employee;
import com.gotop.tyjg.common.model.Group;
import com.gotop.tyjg.common.model.Organization;
import com.gotop.tyjg.common.service.IMainTreeService;
import com.gotop.tyjg.workgroup.service.IManagertreeService;
import com.gotop.util.XmlConvert;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;


public class ManagertreeAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected IManagertreeService managertreeService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void setManagertreeService(IManagertreeService managertreeService) {
        this.managertreeService = managertreeService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public IManagertreeService getManagertreeService() {
        return this.managertreeService;
    }
    
	private ChangeTree changeTree;
	
	public ChangeTree getChangeTree() {
		return changeTree;
	}

	public void setChangeTree(ChangeTree changeTree) {
		this.changeTree = changeTree;
	}
	
	/**
	 * 初始化树主界面
	 * @return
	 * @throws Exception
	 */
	public String initMainTree() throws Exception{
		this.getManagertreeService().initMainTree(this.getChangeTree());
		return "initMainTree";
	}
	/**
	 * 判断是行政树还是业务树
	 * @return
	 * @throws Exception
	 */
	public String isAdminOrBusessTree() throws Exception{
		StringBuffer paramBuffer = new StringBuffer(100);
		paramBuffer.append("<script>window.location.href='");
		boolean isAdmin = StringUtils.isBlank(this.getChangeTree().getTopID());
		boolean falg = false;
		if(!isAdmin){//防止格式化null出错
			falg = Integer.parseInt(this.getChangeTree().getTopID()) < 1 ? true : false;
		}
		//topID为空或小于1显示行政树
		if(isAdmin || falg){//行政树
			paramBuffer.append("/workgroup/managertreeAction_initAdminTree.action?_ts=");
		}else{
			//业务树
			paramBuffer.append("/workgroup/managertreeAction_initBusessTree.action?_ts=");
		}
		paramBuffer.append(new Date());
		if(StringUtils.isNotBlank(changeTree.getCheckcount())){
			paramBuffer.append("&changeTree.checkcount=").append(changeTree.getCheckcount());
		}
		if(StringUtils.isNotBlank(changeTree.getOrgType())){
			paramBuffer.append("&changeTree.orgType=").append(changeTree.getOrgType());
		}
		if(StringUtils.isNotBlank(changeTree.getCheckTitle())){
			paramBuffer.append("&changeTree.checkTitle=").append(changeTree.getCheckTitle());
		}
		if(StringUtils.isNotBlank(changeTree.getShowTabOrg())){
			paramBuffer.append("&changeTree.showTabOrg=").append(changeTree.getShowTabOrg());
		}
		if(StringUtils.isNotBlank(changeTree.getShowTabGroup())){
			paramBuffer.append("&changeTree.showTabGroup=").append(changeTree.getShowTabGroup());
		}
		if(StringUtils.isNotBlank(changeTree.getShowTabRole())){
			paramBuffer.append("&changeTree.showTabRole=").append(changeTree.getShowTabRole());
		}
		if(StringUtils.isNotBlank(changeTree.getShowSelBox())){
			paramBuffer.append("&changeTree.showSelBox=").append(changeTree.getShowSelBox());
		}
		if(StringUtils.isNotBlank(changeTree.getPosiType())){
			paramBuffer.append("&changeTree.posiType=").append(changeTree.getPosiType());
		}
		if(StringUtils.isNotBlank(changeTree.getLookupType())){
			paramBuffer.append("&changeTree.lookupType=").append(changeTree.getLookupType());
		}
		if(StringUtils.isNotBlank(changeTree.getLookupTypeStr())){
			paramBuffer.append("&changeTree.lookupTypeStr=").append(changeTree.getLookupTypeStr());
		}
		if(StringUtils.isNotBlank(changeTree.getOrgdegree())){
			paramBuffer.append("&changeTree.orgdegree=").append(changeTree.getOrgdegree());
		}
		if(StringUtils.isNotBlank(changeTree.getStartOrgid())){
			paramBuffer.append("&changeTree.startOrgid=").append(changeTree.getStartOrgid());
		}
		if(StringUtils.isNotBlank(changeTree.getStartOrgcode())){
			paramBuffer.append("&changeTree.startOrgcode=").append(changeTree.getStartOrgcode());
		}
		if(StringUtils.isNotBlank(changeTree.getTopID())){
			paramBuffer.append("&changeTree.topID=").append(changeTree.getTopID());
		}
		if(StringUtils.isNotBlank(changeTree.getTopName())){
			paramBuffer.append("&changeTree.topName=").append(changeTree.getTopName());
		}
		if(StringUtils.isNotBlank(changeTree.getGroupID())){
			paramBuffer.append("&changeTree.groupID=").append(changeTree.getGroupID());
		}
		paramBuffer.append("';</script>");
		this.write(paramBuffer.toString());
		return null;
	}
	/**
	 * 初始化行政树界面
	 * @return
	 * @throws Exception
	 */
	public String initAdminTree() throws Exception{
		this.getManagertreeService().initAdminTree(changeTree);
		return "adminTree";
	}
	
	public String initBusessTree() throws Exception{
		this.getManagertreeService().initBusessTree(changeTree);
		return "initBusessTree";
	}
	/**
	 * 查询行政树根机构
	 * @return
	 * @throws Exception
	 */
	public String selectAdminTreeRoot() throws Exception{
		HashMap<String, String> hmp = XmlConvert.getParamsAjax();
		List<Organization> orgList = this.getManagertreeService().selectAdminTreeNode(hmp);
		String xml = XmlConvert.getXmlListBean(orgList);
		this.write(xml);
		return null;
	}
	
	public String selectAdminTreeChild() throws Exception{
		HashMap paramMap = XmlConvert.getParamsAjax();
		String orgType = String.valueOf(paramMap.get("orgType"));
		String groupID = String.valueOf(paramMap.get("groupID"));
		paramMap = (HashMap)paramMap.get("oParentOrg");
		String parentid = String.valueOf(paramMap.get("orgid"));
		paramMap.put("parentid", parentid);
		paramMap.put("groupID", groupID);
		List<Organization> orgList = this.getManagertreeService().selectAdminTreeNode(paramMap);
		List<Employee> empList = new ArrayList<Employee>(10);
		if(!"4".equals(orgType)){
			empList = this.getManagertreeService().selectAdminTreeEmpNode(paramMap);
		}
		String xml = XmlConvert.getXmlListBean(orgList,empList);
		this.write(xml);
		return null;
	}
	/**
	 * 初始化群组树
	 * @return
	 * @throws Exception
	 */
	public String initGroupTree() throws Exception{
		this.getManagertreeService().initGroupTree(changeTree);
		return "initGroupTree";
	}
	/**
	 * 查询登录用户可操作的群组
	 * @return
	 * @throws Exception
	 */
	public String selectGroupTree() throws Exception{
		MUOUserSession userSession =  this.getCurrentOnlineUser();
		String[] groupArray = userSession.getGroupid();
		String xml = null;
		if(groupArray != null && groupArray.length > 0){
			List<Group> groupList = this.getManagertreeService().selectGroupTree(groupArray);
			xml = XmlConvert.getXmlListBean(groupList);
		}else{
			xml = "";
		}
		this.write(xml);
		return null;
	}
	
	public String selectGroupOrgEmpRole() throws Exception{
		HashMap<String, Object> hmp = XmlConvert.getParamsAjax();
		String orgType = String.valueOf(hmp.get("orgType"));
		hmp = (HashMap<String, Object>)hmp.get("group");
		String groupid = String.valueOf(hmp.get("groupid"));
		this.write(this.getManagertreeService().selectGroupOrgEmpRole(orgType, groupid));
		return null;
	}
	
	public String selectBusessOrgEmp() throws Exception{
		HashMap hmp = XmlConvert.getParamsAjax();
		this.write(this.getManagertreeService().selectBusessTreeOrgEmp(hmp));
		return null;
	}
	/**
	 * 初始化业务角色界面
	 * @return
	 * @throws Exception
	 */
	public String initBusessRole() throws Exception{
		return "initBusessRole";
	}
	/**
	 * 查询业务角色
	 * @return
	 * @throws Exception
	 */
	public String selectBusessRole() throws Exception{
		HashMap hmp  = XmlConvert.getParamsAjax();
		AjaxParam apm = XmlConvert.queryDatacell();
		Page page = apm.getPage();
		String xml = this.getManagertreeService().selectBusessRole(hmp,page);
		this.write(xml);
		return null;
	}
	/**
	 * 查询角色auto
	 * @return
	 * @throws Exception
	 */
	public String selectRoleAuto() throws Exception{
		HashMap hmp = XmlConvert.getParamsAjax();
		String roleid = String.valueOf(hmp.get("roleid"));
		StringBuffer buffer = new StringBuffer(30);
		buffer.append("<root><data><auto>");
		buffer.append(this.getManagertreeService().selectRoleAuto(roleid));
		buffer.append("</auto></data></root>");
		this.write(buffer.toString());
		return null;
	}
}