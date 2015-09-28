package com.gotop.tyjg.common.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.gotop.crm.util.BaseAction;
import com.gotop.tyjg.common.model.ChangeTree;
import com.gotop.tyjg.common.model.Employee;
import com.gotop.tyjg.common.model.Group;
import com.gotop.tyjg.common.model.Organization;
import com.gotop.tyjg.common.service.IMainTreeService;
import com.gotop.util.XmlConvert;
import com.gotop.vo.system.MUOUserSession;
import com.gotop.vo.tyjg.OmPosition;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

/**
 * *******************************
 * <p>Title: </p>
 * 
 * <p> Description: </p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date Apr 10, 2012
 * 
 * @version 1.0
 * 
 * HISTORY Apr 10, 2012 xuxh 创建文件
 * 
 * *******************************
 */
public class MainTreeAction extends BaseAction {

	private static final long serialVersionUID = -3078064156578841386L;

	private ChangeTree changeTree;
	
	private IMainTreeService mainTreeService;
	
	private List<Employee> employees;
	
	private String empid;
	
	private String positionId;
	
	private String orgIds;
	
	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public ChangeTree getChangeTree() {
		return changeTree;
	}

	public void setChangeTree(ChangeTree changeTree) {
		this.changeTree = changeTree;
	}

	public IMainTreeService getMainTreeService() {
		return mainTreeService;
	}

	public void setMainTreeService(IMainTreeService mainTreeService) {
		this.mainTreeService = mainTreeService;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * 初始化树主界面
	 * @return
	 * @throws Exception
	 */
	public String initMainTree() throws Exception{
		this.getMainTreeService().initMainTree(this.getChangeTree());
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
			paramBuffer.append("/tree/initAdminTree_mainTree.action?_ts=");
		}else{
			//业务树
			paramBuffer.append("/tree/initBusessTree_mainTree.action?_ts=");
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
		if(StringUtils.isNotBlank(changeTree.getPositioncode())){
			paramBuffer.append("&changeTree.positioncode=").append(changeTree.getPositioncode());
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
		this.getMainTreeService().initAdminTree(changeTree);
		return "adminTree";
	}
	
	public String initBusessTree() throws Exception{
		this.getMainTreeService().initBusessTree(changeTree);
		return "initBusessTree";
	}
	
	/**
	 * 初始化岗位人员树
	 * @return
	 * @throws Exception
	 */
	public String initPositionEmp() throws Exception{
		this.getMainTreeService().initPositionEmp(changeTree);
		return "initPositionEmp";
	}
	
	/**
	 * 查询行政树根机构
	 * @return
	 * @throws Exception
	 */
	public String selectAdminTreeRoot() throws Exception{
		HashMap<String, String> hmp = XmlConvert.getParamsAjax();
		List<Organization> orgList = this.getMainTreeService().selectAdminTreeNode(hmp);
		String xml = XmlConvert.getXmlListBean(orgList);
		this.write(xml);
		return null;
	}
	
	public String selectAdminTreeChild() throws Exception{
		HashMap paramMap = XmlConvert.getParamsAjax();
		String orgType = String.valueOf(paramMap.get("orgType"));
		String positioncode = String.valueOf(paramMap.get("p1"));
		paramMap = (HashMap)paramMap.get("oParentOrg");
		String parentid = String.valueOf(paramMap.get("orgid"));
		paramMap.put("parentid", parentid);
		List<Organization> orgList = new ArrayList<Organization>();
		if(!"2".equals(orgType)){
			orgList = this.getMainTreeService().selectAdminTreeNode(paramMap);
		}
		List<Employee> empList = new ArrayList<Employee>();
		if(!"4".equals(orgType)){
			if(!"".equals(positioncode) && !"null".equals(positioncode)){
				paramMap.put("positioncode", positioncode);
				empList = this.getMainTreeService().selectPositionTreeEmpNode(paramMap);
			}else{
				empList = this.getMainTreeService().selectAdminTreeEmpNode(paramMap);
			}
			
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
		this.getMainTreeService().initGroupTree(changeTree);
		return "initGroupTree";
	}
	/**
	 * 查询登录人可选择群组
	 * @return
	 * @throws Exception
	 * 
	 * 
	 * 2014-11-09 施政改
	 * 改变查询表和查询方式
	 */
	public String selectGroupTree() throws Exception{
		MUOUserSession userSession =  this.getCurrentOnlineUser();
		/*String[] groupArray = userSession.getGroupid();*/
		String xml = null;
		/*if(groupArray != null && groupArray.length > 0){*/
		List<Group> groupList = this.getMainTreeService().selectGroupTree(userSession.getEmpid());
		xml = XmlConvert.getXmlListBean(groupList);
		/*}else{
			xml = "";
		}*/
		this.write(xml);
		return null;
	}
	
	public String selectGroupOrgEmpRole() throws Exception{
		HashMap<String, Object> hmp = XmlConvert.getParamsAjax();
		String orgType = String.valueOf(hmp.get("orgType"));
		hmp = (HashMap<String, Object>)hmp.get("group");
		String groupid = String.valueOf(hmp.get("groupid"));
		//this.write(this.getMainTreeService().selectGroupOrgEmpRole(orgType, groupid));
		this.write(this.getMainTreeService().selectGroupOrgEmpRole(groupid));//2014-11-09 改变选择群组树下的人员
		return null;
	}
	
	public String selectBusessOrgEmp() throws Exception{
		HashMap hmp = XmlConvert.getParamsAjax();
		this.write(this.getMainTreeService().selectBusessTreeOrgEmp(hmp));
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
		String xml = this.getMainTreeService().selectBusessRole(hmp,page);
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
		buffer.append(this.getMainTreeService().selectRoleAuto(roleid));
		buffer.append("</auto></data></root>");
		this.write(buffer.toString());
		return null;
	}
	
	/**
	 * 初始化树主界面2
	 * 工作流选择人员界面
	 * @return
	 * @throws Exception
	 */
	public String initMainTree2() throws Exception{
		this.getMainTreeService().initMainTree(this.getChangeTree());
		return "initMainTree2";
	}
	
	/**
	 * 初始化树主界面2
	 * 行领导人员选择界面
	 * @return
	 * @throws Exception
	 */
	public String initMainTree3() throws Exception{
		this.getMainTreeService().initMainTree(this.getChangeTree());
		return "initMainTree3";
	}
	
	/**
	 * 初始化行领导人员选择界面
	 * @return
	 * @throws Exception
	 */
	public String initEmpPositionList() throws Exception{
		return "initEmpPositionList";
	}
	
	/**
	 * 初始化节点人员选择列表界面
	 * @return
	 * @throws Exception
	 */
	public String initPersonList() throws Exception{
		return "initPersonList";
	}
	
	/**
	 * 初始化节点部门选择列表界面
	 * @return
	 * @throws Exception
	 */
	public String initOrgList() throws Exception{
		return "initOrgList";
	}
	
	/**
	 * 查询节点部门和人员
	 * @return
	 * @throws Exception
	 */
	public String selectOrgList() throws Exception{
		try {
			HashMap<String, Object> hmp = new HashMap<String, Object>();
			hmp.put("orgIds", orgIds);
			List<Employee> list  =  this.getMainTreeService().selectOrgList(hmp);
			this.setEmployees(list);
		} catch (Exception e) {
			log.error("查询节点人员失败！！", e);
		}
		return "list1";
	}
	
	/**
	 * 查询节点人员
	 * @return
	 * @throws Exception
	 */
	public String selectPersonList() throws Exception{
		try {
			HashMap<String, Object> hmp = new HashMap<String, Object>();
			hmp.put("empid", empid);
			List<Employee> list  =  this.getMainTreeService().selectPersonList(hmp);
			this.setEmployees(list);
		} catch (Exception e) {
			log.error("查询节点人员失败！！", e);
		}
		return "list1";
	}
	
	/**
	 * 查询行领导人员
	 * @return
	 * @throws Exception
	 */
	public String selectPersonPositionList() throws Exception{
		try {
			HashMap<String, Object> hmp = new HashMap<String, Object>();
			hmp.put("positionId", positionId);
			List<Employee> list  =  this.getMainTreeService().selectPersonPositionList(hmp);
			this.setEmployees(list);
		} catch (Exception e) {
			log.error("查询节点人员失败！！", e);
		}
		return "list1";
	}
	
	public void checkEmployee(){
		String info ="success";
		if(empid != null && !empid.equals("")){
		}else{
			info="fails";
		}
		this.write(info);
	}
	
	/**
	 * 查询岗位树
	 * @return
	 * @throws Exception
	 */
	public String selectPositionEmpTree() throws Exception{
		String xml = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<OmPosition> positionList = this.getMainTreeService()
				.selectPositionTree(map);
		xml = XmlConvert.getXmlListBean(positionList);
		this.write(xml);
		return null;
	}
	
	/**
	 * 查询岗位树节点下的人员
	 * @return
	 * @throws Exception
	 */
	public String selectPositionEmp() throws Exception{
		String xml = "";
		HashMap<String, Object> hmp = XmlConvert.getParamsAjax();
		hmp = (HashMap<String, Object>)hmp.get("oParentOrg");
		String posiCode = String.valueOf(hmp.get("posiCode"));
		xml = XmlConvert.getXmlListBean(this.getMainTreeService().selectPositionEmp(posiCode));
		this.write(xml);
		return null;
	}
	
	
}
