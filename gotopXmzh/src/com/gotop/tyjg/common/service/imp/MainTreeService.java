package com.gotop.tyjg.common.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.gotop.tyjg.common.dao.IMainTreeDao;
import com.gotop.tyjg.common.model.ChangeTree;
import com.gotop.tyjg.common.model.Employee;
import com.gotop.tyjg.common.model.Group;
import com.gotop.tyjg.common.model.Organization;
import com.gotop.tyjg.common.model.Role;
import com.gotop.tyjg.common.service.IMainTreeService;
import com.gotop.util.XmlConvert;
import com.gotop.vo.tyjg.OmPosition;
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
public class MainTreeService implements IMainTreeService {
	private IMainTreeDao mainTreeDao;
	
	public IMainTreeDao getMainTreeDao() {
		return mainTreeDao;
	}
	public void setMainTreeDao(IMainTreeDao mainTreeDao) {
		this.mainTreeDao = mainTreeDao;
	}
	/**
	 * 初始化树主页
	 * @param changeTree
	 * @throws Exception
	 */
	public void initMainTree(ChangeTree changeTree) throws Exception{
		String showSelBoxStr = changeTree.getShowSelBox();
		String orgTypeStr = changeTree.getOrgType();
		String posiTypeStr = changeTree.getPosiType();
		String lookupTypeStr = changeTree.getLookupType();
		String checkcountStr = changeTree.getCheckcount();
		String checkTitle = changeTree.getCheckTitle();
		String showTabOrg = changeTree.getShowTabOrg();
		String showTabGroup = changeTree.getShowTabGroup();
		String showTabRole = changeTree.getShowTabRole();
		String showTabPositionEmp = changeTree.getShowTabPositionEmp();
		String positioncode = changeTree.getPositioncode();
		String checkcount = changeTree.getCheckcount();
		int showSelBox=0;
		int orgType = 3+4;
		int posiType = 3;
		if(showSelBoxStr!=null && !showSelBoxStr.equals("")){
			showSelBox = Integer.parseInt(showSelBoxStr);
		}
		if(orgTypeStr!=null && !orgTypeStr.equals("")){
			orgType = Integer.parseInt(orgTypeStr);
		}
		if(posiTypeStr!=null && !posiTypeStr.equals("")){
			posiType = Integer.parseInt(posiTypeStr);
		}
		if(lookupTypeStr!=null && !lookupTypeStr.equals("") && !lookupTypeStr.equals("null")){
			showSelBox = Integer.parseInt(lookupTypeStr);
		}else{
			lookupTypeStr = "";
		}
		String tdSelOrg = "none";
		String tdSelPosi = "none";
		String tdSelEmp = "none";
		if(showSelBox==4 || showSelBox==(4+3)
			             || showSelBox==(3+4)
				         || showSelBox==(2+3+4)){
			tdSelOrg="";
		}
		if((showSelBox==3 || showSelBox==(2+3)
			              || showSelBox==(3+4)
			              || showSelBox==(2+3+4)) &&
		  (orgType==(2+3) || orgType==(3+4) 
			              || orgType==(2+3+4)
			              || posiType==(3)  
			              || posiType==(3+4)
						  || posiType==(2+3+4))){
			tdSelPosi="";
		}
		if((showSelBox==2||showSelBox==(2+3)
					     ||showSelBox==(2+4) 
					     || showSelBox==(2+3+4))&&
			  (orgType==2||orgType==(2+3)
					     ||orgType==(2+4) 
					     || orgType==(2+3+4)
					     || posiType==(2+3)
					     || posiType==(2+3+4))){
			tdSelEmp="";
		}
		int tdSelEmpSize = 22;
		int tdSelPosiSize = 10;
		boolean tabOrg = false;
		boolean tabGroup = false;
		boolean tabRole = false;
		boolean tabPositionEmp = false;
		if("1".equals(showTabOrg)){
			tabOrg = true;
		}
		if("1".equals(showTabGroup)){
			tabGroup = true;
		}
		if("1".equals(showTabRole)){
			tabRole = true;
		}
		if("1".equals(showTabPositionEmp)){
			tabPositionEmp = true;
		}
		
		if(!tabOrg && !tabGroup && !tabRole){
			//tabOrg=true;
		}
		changeTree.setLookupTypeStr(lookupTypeStr);
		changeTree.setTabOrg(String.valueOf(tabOrg));
		changeTree.setTabGroup(String.valueOf(tabGroup));
		changeTree.setTabRole(String.valueOf(tabRole));
		changeTree.setTabPositionEmp(String.valueOf(tabPositionEmp));
		changeTree.setPositioncode(positioncode);
		changeTree.setCheckcount(checkcount);
	}
	/**
	 * 初始化行政树
	 * @param changeTree 行政树参数
	 * @throws Exception
	 */
	public void initAdminTree(ChangeTree changeTree) throws Exception{
		String showSelBoxStr = changeTree.getShowSelBox();
		String orgTypeStr = changeTree.getOrgType();
		String posiTypeStr = changeTree.getPosiType();
		String lookupTypeStr = changeTree.getLookupType();
		String checkcountStr = changeTree.getCheckcount();
		String checkTitle = changeTree.getCheckTitle();
		String positioncode = changeTree.getPositioncode();
		int showSelBox=0;
		int orgType = 3+4;
		int posiType = 3;
		if(showSelBoxStr!=null && !showSelBoxStr.equals("")){
			showSelBox = Integer.parseInt(showSelBoxStr);
		}else{
			showSelBox = 0;
		}
		if(orgTypeStr!=null && !orgTypeStr.equals("")){
			orgType = Integer.parseInt(orgTypeStr);
		}
		if(posiTypeStr!=null && !posiTypeStr.equals("")){
			posiType = Integer.parseInt(posiTypeStr);
		}
		if(lookupTypeStr!=null && !lookupTypeStr.equals("") && !lookupTypeStr.equals("null")){
			showSelBox = Integer.parseInt(lookupTypeStr);
		}else{
			lookupTypeStr = "";
		}
		String tdSelOrg = "";
		String tdSelGroup = "";
		String tdSelEmp = "";

		if((showSelBox==0||showSelBox==4 
						 || showSelBox==(4+3)
						 || showSelBox==(2+4)
			             || showSelBox==(3+4)
				         || showSelBox==(2+3+4)) &&
			 (orgType==4 || orgType==(4+3)
						 || orgType==(2+4)
			             || orgType==(3+4)
				         || orgType==(2+3+4))){
			tdSelOrg=",Organization,";
		}
		if((showSelBox==0||showSelBox==3 
						  || showSelBox==(2+3)
			              || showSelBox==(3+4)
			              || showSelBox==(2+3+4))){
			tdSelGroup=",Group,";
		}
		if((showSelBox==0||showSelBox==2
						 ||showSelBox==(2+3)
					     ||showSelBox==(2+4) 
					     ||showSelBox==(2+3+4)) &&
		      (orgType==2||orgType==(2+3)
					     ||orgType==(2+4) 
					     ||orgType==(2+3+4))){
			tdSelEmp=",Employee,";
		}
		String showCheckType = tdSelOrg + tdSelGroup + tdSelEmp;
		changeTree.setShowCheckType(showCheckType);
		changeTree.setTdSelEmp(tdSelEmp);
		changeTree.setTdSelGroup(tdSelGroup);
		changeTree.setTdSelOrg(tdSelOrg);
		changeTree.setPositioncode(positioncode);
	}
	/**
	 * 查询行政树的机构节点
	 * @param hmp 查询条件
	 * @return 符合条件的机构集合
	 * @throws Exception
	 */
	public List<Organization> selectAdminTreeNode(HashMap<String, String> hmp) throws Exception{
		HashMap<String, String> paramMap = new HashMap<String, String>(10);
		if(hmp.containsKey("startorgid")){
			paramMap.put("startorgid", hmp.get("startorgid"));
		}else if(hmp.containsKey("parentid")){
			paramMap.put("parentid", hmp.get("parentid"));
		}else{
			paramMap.put("parent", "parent");
		}
		if(hmp.containsKey("startorgcode")){
			paramMap.put("startorgcode", hmp.get("startorgcode"));
		}else if(hmp.containsKey("parentid")){
			paramMap.put("parentid", hmp.get("parentid"));
		}else if(!hmp.containsKey("startorgid")){
			paramMap.put("parent", "parent");
		}
		if(hmp.containsKey("jgsx")){
			paramMap.put("jgsx", hmp.get("jgsx"));
		}
		List<Organization> orgList =  this.getMainTreeDao().selectAdminTreeNode(paramMap);
		return orgList;
	}
	/**
	 * 查询机构下的人员
	 * @param hmp 查询条件
	 * @return 符合条件的人员集合
	 * @throws Exception
	 */
	public List<Employee> selectAdminTreeEmpNode(HashMap<String, String> hmp) throws Exception{
		return this.getMainTreeDao().selectAdminTreeEmpNode(hmp);
	}
	
	/**
	 * 初始化群组树
	 * @param changeTree 群组条件
	 * @throws Exception
	 */
	public void initGroupTree(ChangeTree changeTree) throws Exception{
		String showSelBoxStr = changeTree.getShowSelBox();
		String orgTypeStr = changeTree.getOrgType();
		String posiTypeStr = changeTree.getPosiType();
		String lookupTypeStr = changeTree.getLookupType();
		String checkcountStr = changeTree.getCheckcount();
		String checkTitle = changeTree.getCheckTitle();
		int showSelBox=0;
		int orgType = 3+4;
		int posiType = 3;
		if(showSelBoxStr!=null && !showSelBoxStr.equals("")){
			showSelBox = Integer.parseInt(showSelBoxStr);
		}else{
			showSelBox = 0;
		}
		if(orgTypeStr!=null && !orgTypeStr.equals("")){
			orgType = Integer.parseInt(orgTypeStr);
		}
		if(posiTypeStr!=null && !posiTypeStr.equals("")){
			posiType = Integer.parseInt(posiTypeStr);
		}
		if(lookupTypeStr!=null && !lookupTypeStr.equals("") && !lookupTypeStr.equals("null")){
			showSelBox = Integer.parseInt(lookupTypeStr);
		}else{
			lookupTypeStr = "";
		}
		String tdSelOrg = "";
		String tdSelGroup = "";
		String tdSelEmp = "";

		if((showSelBox==0||showSelBox==4 
						 || showSelBox==(4+3)
						 || showSelBox==(2+4)
			             || showSelBox==(3+4)
				         || showSelBox==(2+3+4)) &&
			 (orgType==4 || orgType==(4+3)
						 || orgType==(2+4)
			             || orgType==(3+4)
				         || orgType==(2+3+4))){
			tdSelOrg=",Organization,";
		}
		if((showSelBox==0||showSelBox==3 
						  || showSelBox==(2+3)
			              || showSelBox==(3+4)
			              || showSelBox==(2+3+4))){
			tdSelGroup=",Group,";
		}
		if((showSelBox==0||showSelBox==2
						 ||showSelBox==(2+3)
					     ||showSelBox==(2+4) 
					     ||showSelBox==(2+3+4)) &&
		      (orgType==2||orgType==(2+3)
					     ||orgType==(2+4) 
					     ||orgType==(2+3+4))){
			tdSelEmp=",Employee,";
		}
		
		int tdSelEmpSize = 22;
		int tdSelPosiSize = 10;
		String showCheckType = tdSelOrg + tdSelGroup + tdSelEmp;
		changeTree.setShowCheckType(showCheckType);
		changeTree.setTdSelEmp(tdSelEmp);
		changeTree.setTdSelGroup(tdSelGroup);
		changeTree.setTdSelOrg(tdSelOrg);
	}
	/**
	 * 查询当前用户可操作的群组
	 * @param groupArray 拥有的可操作群组
	 * @return 可操作群组集合
	 * @throws Exception
	 */
	public List<Group> selectGroupTree(String[] groupArray) throws Exception{
		return this.getMainTreeDao().selectGroupTree(groupArray);
	}
	/**
	 * 查询群组下的机构、人员、角色
	 * @param groupid 群组编号
	 * @param orgType 选择类型
	 * @return 包含机构、人员、角色的XML
	 * @throws Exception
	 */
	public String selectGroupOrgEmpRole(String orgType, String groupid) throws Exception{
		List<Employee> empList = new ArrayList<Employee>(100);
		List<Organization> orgList = new ArrayList<Organization>(100);
		if("0".equals(orgType)  || "2".equals(orgType) || "6".equals(orgType) || "9".equals(orgType)){
			empList = this.getMainTreeDao().selectGroupEmp(groupid);
		}
		if("0".equals(orgType) || "3".equals(orgType) || "7".equals(orgType) || "9".equals(orgType)){
			orgList = this.getMainTreeDao().selectGroupOrg(groupid);
		}
		if(1==2){
			
		}
		return XmlConvert.getXmlListBean(empList,orgList);
	}
	
	/**
	 * 初始化业务树
	 * @param changeTree 业务树条件
	 * @throws Exception
	 */
	public void initBusessTree(ChangeTree changeTree) throws Exception{
		String showSelBoxStr = changeTree.getShowSelBox();
		String orgTypeStr = changeTree.getOrgType();
		String posiTypeStr = changeTree.getPosiType();
		String lookupTypeStr = changeTree.getLookupType();
		String checkcountStr = changeTree.getCheckcount();
		String checkTitle = changeTree.getCheckTitle();
		int showSelBox=0;
		int orgType = 3+4;
		int posiType = 3;
		if(showSelBoxStr!=null && !showSelBoxStr.equals("")){
			showSelBox = Integer.parseInt(showSelBoxStr);
		}else{
			showSelBox = 0;
		}
		if(orgTypeStr!=null && !orgTypeStr.equals("")){
			orgType = Integer.parseInt(orgTypeStr);
		}
		if(posiTypeStr!=null && !posiTypeStr.equals("")){
			posiType = Integer.parseInt(posiTypeStr);
		}
		if(lookupTypeStr!=null && !lookupTypeStr.equals("") && !lookupTypeStr.equals("null")){
			showSelBox = Integer.parseInt(lookupTypeStr);
		}else{
			lookupTypeStr = "";
		}
		String tdSelOrg = "";
		String tdSelGroup = "";
		String tdSelEmp = "";
		if((showSelBox==0||showSelBox==4 
						 || showSelBox==(4+3)
						 || showSelBox==(2+4)
			             || showSelBox==(3+4)
				         || showSelBox==(2+3+4)) &&
			 (orgType==4 || orgType==(4+3)
						 || orgType==(2+4)
			             || orgType==(3+4)
				         || orgType==(2+3+4))){
			tdSelOrg=",Organization,";
		}
		if((showSelBox==0||showSelBox==3 
						  || showSelBox==(2+3)
			              || showSelBox==(3+4)
			              || showSelBox==(2+3+4))){
			tdSelGroup=",Group,";
		}
		if((showSelBox==0||showSelBox==2
						 ||showSelBox==(2+3)
					     ||showSelBox==(2+4) 
					     ||showSelBox==(2+3+4)) &&
		      (orgType==2||orgType==(2+3)
					     ||orgType==(2+4) 
					     ||orgType==(2+3+4))){
			tdSelEmp=",Employee,";
		}
		int tdSelEmpSize = 22;
		int tdSelPosiSize = 10;
		String showCheckType = tdSelOrg + tdSelGroup + tdSelEmp;
		changeTree.setShowCheckType(showCheckType);
		changeTree.setTdSelEmp(tdSelEmp);
		changeTree.setTdSelGroup(tdSelGroup);
		changeTree.setTdSelOrg(tdSelOrg);
		String topid = changeTree.getTopID();
		List busessTreeList = this.getMainTreeDao().selectBusessTreeInfo(topid);
		/*
		 * 1、根据TOPID查询业务树，业务树busessTreeList不为null并且大于0 
		 * 2、没传topname取业务树名称，否则使用传的topname
		 * 3、根据startorgid和startorgcode为空时设置id为topid
		 * 4、startorgid和startorgcode不为空查询startid
		 * 5、starIdList为null并且大于0时取出id赋予startid,否则startid为0
		 */
		if(busessTreeList != null && busessTreeList.size() > 0){
			if(changeTree.getTopName() == null){
				String topName = String.valueOf(((HashMap)busessTreeList.get(0)).get("ZZJGSMC"));
				changeTree.setTopName(topName);
			}
		}
		String startOrgcode = changeTree.getStartOrgcode();
		String startorgid = changeTree.getStartOrgid();
		if((StringUtils.isNotBlank(startOrgcode) && StringUtils.isNotEmpty(startOrgcode)) 
				|| (StringUtils.isNotBlank(startorgid) && StringUtils.isNotEmpty(startorgid))){
			HashMap<String, String> hmp = new HashMap<String, String>(3);
			hmp.put("topid", topid);
			if(StringUtils.isNotBlank(startOrgcode) && StringUtils.isNotEmpty(startOrgcode)){
				hmp.put("startorgcode", startOrgcode);
			}
			if(StringUtils.isNotBlank(startorgid) && StringUtils.isNotEmpty(startorgid)){
				hmp.put("startorgid", startorgid);
			}
			List starIdList = this.getMainTreeDao().selectStartId(hmp);
			if(starIdList != null && starIdList.size() > 0){
				String startid = String.valueOf(((HashMap)starIdList.get(0)).get("ID"));
				changeTree.setStartid(startid);
			}else{
				changeTree.setStartid("0");
			}
		}else{
			changeTree.setId(topid);
		}
		
	}
	/**
	 * 获取业务树的机构与人员
	 * @param hmp 查询条件
	 * @return 包含机构、人员的XML
	 * @throws Exception
	 */
	public String selectBusessTreeOrgEmp(HashMap hmp) throws Exception{
		String orgType = String.valueOf(hmp.get("orgType"));
		List<Organization> orgList = new ArrayList<Organization>(100);
		List<Employee> empList = new ArrayList<Employee>(100);
		HashMap  paramMap = new HashMap(6);
		HashMap<String ,String>  empMap = new HashMap<String ,String> (1);
		if("2".equals(orgType) || "5".equals(orgType) || "6".equals(orgType) || "9".equals(orgType)){
			if(hmp.containsKey("oParentOrg")){
				HashMap temp = (HashMap)hmp.get("oParentOrg");
				String sourceorgtype = String.valueOf(temp.get("sourceorgtype"));
				if("4".equals(sourceorgtype)){
					empMap.put("orgid", String.valueOf(temp.get("orgid")));
					empList = this.getMainTreeDao().selectAdminTreeEmpNode(empMap);
				}
			}
		}
		if("4".equals(orgType) || "6".equals(orgType) || "7".equals(orgType) ||"9".equals(orgType)){
			String id = null;
			Object objId  = null;
			if(hmp.containsKey("oParentOrg")){
				HashMap temp = (HashMap)hmp.get("oParentOrg");
				objId =temp.get("id");
			}
			if(objId != null){
				id = String.valueOf(objId);
			}
			if(StringUtils.isBlank(id) && StringUtils.isEmpty(id)){
				paramMap.put("startid", hmp.get("startID"));
			}else{
				paramMap.put("parentid", id);
			}
			Object orgtypeObj = paramMap.get("orgtype");
			if(orgtypeObj!=null){
				String otStr = orgtypeObj.toString();
				if(StringUtils.isNotBlank(otStr)){
					String[] orgtypeArra = otStr.split(",");
					paramMap.put("orgtype", orgtypeArra);
				}
			}
			orgList = this.getMainTreeDao().selectBusessTreeOrg(paramMap);
		}
		return XmlConvert.getXmlListBean(orgList,empList);
	}
	/**
	 * 查询业务角色
	 * @param hmp 参数
	 * @param page 分页参数
	 * @return xml
	 * @throws Exception
	 */
	public String selectBusessRole(HashMap hmp,Page page)throws Exception{
		hmp.put("oracleStart", page.getBegin());
		hmp.put("oracleEnd", page.getBegin()+page.getLength());
		page.setCount(this.getMainTreeDao().countBusessRole(hmp));
		List<Role> roleList = this.getMainTreeDao().selectBusessRole(hmp);
		pageCondExpand pce = new pageCondExpand();
		pce.putPageCond(page);
		return XmlConvert.getXmlListBean(page,roleList);
	}
	/**
	 * 查询auto信息
	 * @param roleid 角色编码
	 * @return auto值
	 * @throws Exception
	 */
	public String selectRoleAuto(String roleid) throws Exception{
		return this.getMainTreeDao().selectRoleAuto(roleid);
	}
	
	@Override
	public List<Employee> selectPersonList(HashMap hmp) {
		List<Employee> empList = this.getMainTreeDao().selectPersonList(hmp);
		return empList;
	}
	@Override
	public List<Employee> selectOrgList(HashMap<String, Object> hmp) {
		List<Employee> empList = this.getMainTreeDao().selectOrgList(hmp);
		return empList;
	}
	@Override
	public List<Employee> selectPersonPositionList(HashMap<String, Object> hmp) {
		List<Employee> empList = this.getMainTreeDao().selectPersonPositionList(hmp);
		return empList;
	}
	
	@Override
	public void initPositionEmp(ChangeTree changeTree) {
		String showSelBoxStr = changeTree.getShowSelBox();
		String orgTypeStr = changeTree.getOrgType();
		String posiTypeStr = changeTree.getPosiType();
		String lookupTypeStr = changeTree.getLookupType();
		String checkcountStr = changeTree.getCheckcount();
		String checkTitle = changeTree.getCheckTitle();
		int showSelBox=0;
		int orgType = 3+4;
		int posiType = 3;
		if(showSelBoxStr!=null && !showSelBoxStr.equals("")){
			showSelBox = Integer.parseInt(showSelBoxStr);
		}else{
			showSelBox = 0;
		}
		if(orgTypeStr!=null && !orgTypeStr.equals("")){
			orgType = Integer.parseInt(orgTypeStr);
		}
		if(posiTypeStr!=null && !posiTypeStr.equals("")){
			posiType = Integer.parseInt(posiTypeStr);
		}
		if(lookupTypeStr!=null && !lookupTypeStr.equals("") && !lookupTypeStr.equals("null")){
			showSelBox = Integer.parseInt(lookupTypeStr);
		}else{
			lookupTypeStr = "";
		}
		String tdSelOrg = "";
		String tdSelGroup = "";
		String tdSelEmp = "";

		if((showSelBox==0||showSelBox==4 
						 || showSelBox==(4+3)
						 || showSelBox==(2+4)
			             || showSelBox==(3+4)
				         || showSelBox==(2+3+4)) &&
			 (orgType==4 || orgType==(4+3)
						 || orgType==(2+4)
			             || orgType==(3+4)
				         || orgType==(2+3+4))){
			tdSelOrg=",Organization,";
		}
		if((showSelBox==0||showSelBox==3 
						  || showSelBox==(2+3)
			              || showSelBox==(3+4)
			              || showSelBox==(2+3+4))){
			tdSelGroup=",Group,";
		}
		if((showSelBox==0||showSelBox==2
						 ||showSelBox==(2+3)
					     ||showSelBox==(2+4) 
					     ||showSelBox==(2+3+4)) &&
		      (orgType==2||orgType==(2+3)
					     ||orgType==(2+4) 
					     ||orgType==(2+3+4))){
			tdSelEmp=",Employee,";
		}
		String showCheckType = tdSelOrg + tdSelGroup + tdSelEmp;
		changeTree.setShowCheckType(showCheckType);
		changeTree.setTdSelEmp(tdSelEmp);
		changeTree.setTdSelGroup(tdSelGroup);
		changeTree.setTdSelOrg(tdSelOrg);
		
	}
	@Override
	public List<Employee> selectPositionTreeEmpNode(HashMap paramMap) {
		return this.getMainTreeDao().selectPositionTreeEmpNode(paramMap);
	}
	@Override
	public List<OmPosition> selectPositionTree(HashMap paramMap) {
		return this.getMainTreeDao().selectPositionTree(paramMap);
	}
	@Override
	public List<Employee> selectPositionEmp(String posiCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("posiCode", posiCode);
		return this.getMainTreeDao().selectPositionEmp(map);
	}
	@Override
	public List<Group> selectGroupTree(Long empid) {
		return this.getMainTreeDao().selectGroupTree(empid);
	}
	@Override
	public String selectGroupOrgEmpRole(String groupid) throws Exception {
		List<Employee> empList = new ArrayList<Employee>();
		empList = this.getMainTreeDao().selectGroupEmp(groupid);
		return XmlConvert.getXmlListBean(empList);
	}
}
