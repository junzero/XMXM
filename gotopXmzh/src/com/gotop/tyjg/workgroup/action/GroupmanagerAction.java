package com.gotop.tyjg.workgroup.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.gotop.crm.util.BaseAction;
import com.gotop.tyjg.stable.model.OmGroup;
import com.gotop.tyjg.workgroup.model.QueryGroupBean;
import com.gotop.tyjg.workgroup.service.IGroupmanagerService;
import com.gotop.util.XmlConvert;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;

/**
* 查询一级工作组
*/
public class GroupmanagerAction extends BaseAction {
    /**
	 * 通过spring注入的Service对象.
	 * @abatorgenerated
	 */
	protected IGroupmanagerService groupmanagerService;
	private String parentGroupid;
	private OmGroup group;
	private List<OmGroup> groups;
	private QueryGroupBean queryPara;
	private String isAdd;
	
	public QueryGroupBean getQueryPara() {
		return queryPara;
	}

	public void setQueryPara(QueryGroupBean queryPara) {
		this.queryPara = queryPara;
	}

	public String getParentGroupid() {
		return parentGroupid;
	}

	public void setParentGroupid(String parentGroupid) {
		this.parentGroupid = parentGroupid;
	}
	
	public OmGroup getGroup() {
		return group;
	}

	public void setGroup(OmGroup group) {
		this.group = group;
	}

	public String getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}

	/**
	 * viewDataList Action.
	 * @abatorgenerated
	 */
	public String manageGroupEmpViewList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap hm = new HashMap();
		List data = groupmanagerService.manageGroupEmpBeanByMapAndPage(hm, this
				.getPage());
		request.setAttribute("rdata", data);
		return "manageGroupEmp";
	}

	/**
	 * viewDataList Action.
	 * @abatorgenerated
	 */
	public String manageGroupRoleViewList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap hm = new HashMap();
		List data = groupmanagerService.manageGroupRoleBeanByMapAndPage(hm,
				this.getPage());
		request.setAttribute("rdata", data);
		return "manageGroupRole";
	}

	/**
	 * viewDataList Action.
	 * @abatorgenerated
	 */
	public String managePositionRoleViewList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap hm = new HashMap();
		List data = groupmanagerService.managePositionRoleBeanByMapAndPage(hm,
				this.getPage());
		request.setAttribute("rdata", data);
		return "managePositionRole";
	}

	/**
	 * viewDataList Action.
	 * @abatorgenerated
	 */
	public String queryPositionEmpViewList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap hm = new HashMap();
		hm.put("groupid", group.getGroupid());
		hm.put("code", group.getGroupname());
		hm.put("name", group.getGroupdesc());
		group.setEmpid(this.getCurrentOnlineUser().getEmpid());
		List data = groupmanagerService.queryPositionEmpBeanByMapAndPage(hm, this.getPage());
		request.setAttribute("groupOrgs", data);
		return "queryGroupEmpOrg";
	}

	/**
	 * viewDataList Action.
	 * @abatorgenerated
	 */
	public String queryGroupEmpOrgViewList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap hm = new HashMap();
		List data = groupmanagerService.queryGroupEmpOrgBeanByMapAndPage(hm,
				this.getPage());
		request.setAttribute("rdata", data);
		return "queryGroupEmpOrg";
	}

	/**
	 * viewDataList Action.
	 * @abatorgenerated
	 */
	public String queryGroupPosiViewList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap hm = new HashMap();
		List data = groupmanagerService.queryGroupPosiBeanByMapAndPage(hm, this
				.getPage());
		request.setAttribute("rdata", data);
		return "queryGroupPosi";
	}

	/**
	 * viewDataList Action.
	 * @abatorgenerated
	 */
	public String updateSelfGroupViewList() throws Exception {
		addGroupTemp();
		return "updateSelfGroup";
	}

	/**
	 * 查询全部工作组
	 */
	public String queryGroupViewList() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap param = new HashMap();
		MUOUserSession muous = this.getCurrentOnlineUser();
		param.put("empid", muous.getEmpid());
		param.put("orgid", muous.getOrgid());
		if(queryPara!=null){
			param.put("groupname", queryPara.getGroupname());
			param.put("grouptype", queryPara.getGrouptype());
			param.put("groupstatus", queryPara.getGroupstatus());
		}
		List data = groupmanagerService.queryGroupBeanByMapAndPage(param, this.getPage());
		request.setAttribute("groups", data);
		return "queryGroup";
	}

	/**
	 * 通过spring注入Service的get类.
	 * @abatorgenerated
	 */
	public IGroupmanagerService getGroupmanagerService() {
		return this.groupmanagerService;
	}

	/**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void setGroupmanagerService(IGroupmanagerService groupmanagerService) {
        this.groupmanagerService = groupmanagerService;
    }

	/**
	 * 查询群组下的所有下级
	 */
	public void queryGroupPositionEmpByParentTree() throws Exception {
		HashMap param = XmlConvert.getParamsAjax();
		HashMap group = (HashMap)param.get("group");
		
		List groupList = groupmanagerService.queryGroupPositionEmpByParentBeanByMap(group);
		List orgList = groupmanagerService.queryGroupMemberByOrgBeanByMapAndPage(group);
		List roleList = groupmanagerService.queryGroupMemberByRoleBeanByMap(group);
		List empList = groupmanagerService.queryGroupMemberByEmpBeanByMap(group);
		HashMap<String, Object> hmpl = new HashMap<String, Object>();
		hmpl.put("groupList", groupList);
		hmpl.put("empList", empList);
		hmpl.put("orgList", orgList);
		hmpl.put("roleList", roleList);
		String xmlStr = XmlConvert.getXmlListBean(hmpl);
		this.write(xmlStr);
	}
    /**
     * 获取分组类型
     * @throws Exception
     */
    public void queryRootGroupTypeTree() throws Exception{
        HashMap param = XmlConvert.getParamsAjax();
		List resultData = groupmanagerService.queryRootGroupTypeBeanByMapAndPage(param , page);
		HashMap resultMap = new HashMap();
		resultMap.put("rdata", resultData);
		String resultStr = XmlConvert.getXmlListBean(resultMap);
		this.write(resultStr);
    }
	/**
	 * 获取所有群组
	 */
	public void queryRootGroupTree() throws Exception {
		HashMap param = XmlConvert.getParamsAjax();
		HashMap dtype = (HashMap)param.get("dtype");
		String grouptype = (String)dtype.get("grouptype");
		if("personal".equals(grouptype) || "public".equals(grouptype)){
			dtype.put("empid", this.getCurrentOnlineUser().getEmpid());
		}
		List resultData = groupmanagerService.queryRootGroupBeanByMap(dtype);
		HashMap resultMap = new HashMap();
		resultMap.put("groupList", resultData);
		String resultStr = XmlConvert.getXmlListBean(resultMap);
		this.write(resultStr);
	}
    /**
     * datacell Action.
     * @abatorgenerated
     */
    public void queryRootGroupTypeDatacell() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = groupmanagerService.queryRootGroupTypeBeanByMapAndPage(hm , page);
        HashMap<String,Object> hmpl = new HashMap<String,Object>();
        hmpl.put("page", page);
        hmpl.put("grouptype", data);
        String xmlStr = XmlConvert.getXmlListBean(hmpl);
        this.write(xmlStr);
    }
    /**
     * 增加工作组,中转
     * @throws Exception
     */
    public String addGroupTemp() throws Exception {
    	boolean isEdit = true;
    	if(group!=null){
	    	Long groupid = group.getGroupid();
	    	HashMap param = new HashMap();
	    	param.put("groupid", groupid);
	    	group = groupmanagerService.querySGroupBeanByMap(param);
	    	groupmanagerService.queryOmGrouprange(group);
    	}else{
    		group = new OmGroup();
    		String grouptype = this.getRequest().getParameter("grouptype");
    		if(StringUtils.isNotBlank(grouptype)){
    			group.setGrouptype(grouptype);
    		}
    	}
    	MUOUserSession muous = this.getCurrentOnlineUser();
    	group.setManager(muous.getEmpname());
    	group.setEmpid(muous.getEmpid());
    	
    	
    	this.getRequest().setAttribute("isEdit", isEdit);
    	return "addGroup";
    }
    /**
     * 增加一条新记录
     * @param record
     * @return
     */
    public String addGroup(){
		HashMap param = XmlConvert.getParamsAjax();
		HashMap groupMap = (HashMap)param.get("group");
    	if(group==null){
    		mapToBean(param);
    		group.setCreatetime(new Date());
    		group.setLastupdate(new Date());
    	}
    	this.groupmanagerService.addGroup(group);
    	this.write("<oprResult>1</oprResult>");
    	return "addGroup";
    }
    /**
     * 更新一条记录
     * @param record
     * @return
     * @throws Exception 
     */
    public String updateGroup() throws Exception{
		HashMap param = XmlConvert.getParamsAjax();
    	if(group==null){
    		mapToBean(param);
    		group.setLastupdate(new Date());
    	}
    	this.groupmanagerService.updateGroup(group);
    	this.write("<oprResult>1</oprResult>");
    	return "addGroup";
    }
    /**
     * 从map中取值
     * @param param
     */
    private void mapToBean(HashMap param){
		group = new OmGroup();
		String group_grouptype = (String)param.get("group.grouptype");
		String group_parentgroupid = (String)param.get("group.parentgroupid");
		String group_groupstatus = (String)param.get("group.groupstatus");
		String group_empid = (String)param.get("group.empid");
		String group_manager = (String)param.get("group.manager");
		String group_groupid = (String)param.get("group.groupid");
		String group_groupdesc = (String)param.get("group.groupdesc");
		String group_groupname = (String)param.get("group.groupname");
		String group_empids = (String)param.get("empids");
		String group_orgids = (String)param.get("orgids");
		String group_roleids = (String)param.get("roleids");
		group.setGrouptype(group_grouptype);
		group.setGroupstatus(group_groupstatus);
		if(StringUtils.isNotBlank(group_groupid)){
			group.setGroupid(Long.parseLong(group_groupid));
		}
		group.setGroupdesc(group_groupdesc);
		group.setGroupname(group_groupname);
		if(StringUtils.isNotBlank(group_empid)){
			group.setEmpid(Long.parseLong(group_empid));
		}
		group.setManager(group_manager);
		if(StringUtils.isNotBlank(group_parentgroupid)){
			group.setParentgroupid(Long.parseLong(group_parentgroupid));
		}
		group.setEmpids(group_empids);
		group.setOrgids(group_orgids);
		group.setRoleids(group_roleids);
    }

	public List<OmGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<OmGroup> groups) {
		this.groups = groups;
	}
	/**
	 * 批量删除
	 * @throws Exception
	 */
	public void deleteGroups() throws Exception{
		HashMap param = XmlConvert.getParamsAjax();
		Object groupid = param.get("groupid");
		if(groupid instanceof List){
			groupmanagerService.deleteGroups((List)groupid);
		}else{
			List groups = new ArrayList();
			groups.add((String)groupid);
			groupmanagerService.deleteGroups(groups);
		}
		this.write("<oprResult>1</oprResult>");
	}
	public String toTab(){
		
		return "toTab";
	}
	
	public String toMngorgMainTree(){
		
		return "mngorgMainTree";
	}
	/**
	 * empids=1, groupid=634, roleids=null, orgids=112254,113010
	 * 挂在群组下的人员机构角色
	 * @throws Exception
	 */
	public void addGroupEmpOrg() throws Exception{
		HashMap param = XmlConvert.getParamsAjax();
		groupmanagerService.addGroupEmpOrg(param);
		this.write("<issucc>1</issucc>");
	}
	
	public void deleteGroupEmpOrg() throws Exception{
		HashMap param = XmlConvert.getParamsAjax();
		groupmanagerService.deleteGroupEmpOrg(param);
		this.write("<issucc>1</issucc>");
	} 
}