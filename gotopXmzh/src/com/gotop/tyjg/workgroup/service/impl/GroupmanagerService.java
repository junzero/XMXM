package com.gotop.tyjg.workgroup.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.gotop.tyjg.stable.dao.IOmEmpgroupDAO;
import com.gotop.tyjg.stable.dao.IOmGroupDAO;
import com.gotop.tyjg.stable.dao.IOmGroupmemberDAO;
import com.gotop.tyjg.stable.dao.IOmGrouprangeDAO;
import com.gotop.tyjg.stable.model.OmEmpgroupKey;
import com.gotop.tyjg.stable.model.OmGroup;
import com.gotop.tyjg.stable.model.OmGroupmemberKey;
import com.gotop.tyjg.stable.model.OmGrouprangeExample;
import com.gotop.tyjg.stable.model.OmGrouprangeKey;
import com.gotop.tyjg.workgroup.dao.IGroupmanagerDAO;
import com.gotop.tyjg.workgroup.service.IGroupmanagerService;
import com.primeton.utils.Page;

/**
* 查询一级工作组
*/
public class GroupmanagerService implements IGroupmanagerService {
    /**
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(GroupmanagerService.class);
	/**
	 * 通过spring注入的DAO对象.
	 * @abatorgenerated
	 */
	protected IGroupmanagerDAO groupmanagerDAO;
	protected IOmGroupDAO omGroupDAO;
	protected IOmEmpgroupDAO omEmpgroupDAO;
	protected IOmGroupmemberDAO omGroupmemberDAO;
	protected IOmGrouprangeDAO omGrouprangeDAO;
	public IOmGroupDAO getOmGroupDAO() {
		return omGroupDAO;
	}

	public void setOmGroupDAO(IOmGroupDAO omGroupDAO) {
		this.omGroupDAO = omGroupDAO;
	}

	public IOmEmpgroupDAO getOmEmpgroupDAO() {
		return omEmpgroupDAO;
	}

	public void setOmEmpgroupDAO(IOmEmpgroupDAO omEmpgroupDAO) {
		this.omEmpgroupDAO = omEmpgroupDAO;
	}

	public IOmGroupmemberDAO getOmGroupmemberDAO() {
		return omGroupmemberDAO;
	}

	public void setOmGroupmemberDAO(IOmGroupmemberDAO omGroupmemberDAO) {
		this.omGroupmemberDAO = omGroupmemberDAO;
	}
	public IOmGrouprangeDAO getOmGrouprangeDAO() {
		return omGrouprangeDAO;
	}

	public void setOmGrouprangeDAO(IOmGrouprangeDAO omGrouprangeDAO) {
		this.omGrouprangeDAO = omGrouprangeDAO;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	public OmGroup querySGroupBeanByMap(HashMap map) throws Exception {
		OmGroup list = groupmanagerDAO.querySGroupBeanByMap(map);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	public List manageGroupEmpBeanByMapAndPage(HashMap map, Page page)
			throws Exception {
		List list = groupmanagerDAO.manageGroupEmpBeanByMapAndPage(map, page);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	public List manageGroupRoleBeanByMapAndPage(HashMap map, Page page)
			throws Exception {
		List list = groupmanagerDAO.manageGroupRoleBeanByMapAndPage(map, page);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	public List managePositionRoleBeanByMapAndPage(HashMap map, Page page)
			throws Exception {
		List list = groupmanagerDAO.managePositionRoleBeanByMapAndPage(map,
				page);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	public List queryPositionEmpBeanByMapAndPage(HashMap map, Page page)
			throws Exception {
		List list = groupmanagerDAO.queryPositionEmpBeanByMapAndPage(map, page);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	public List queryGroupEmpOrgBeanByMapAndPage(HashMap map, Page page)
			throws Exception {
		List list = groupmanagerDAO.queryGroupEmpOrgBeanByMapAndPage(map, page);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	public List queryGroupPosiBeanByMapAndPage(HashMap map, Page page)
			throws Exception {
		List list = groupmanagerDAO.queryGroupPosiBeanByMapAndPage(map, page);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	public List updateSelfGroupBeanByMapAndPage(HashMap map, Page page)
			throws Exception {
		List list = groupmanagerDAO.updateSelfGroupBeanByMapAndPage(map, page);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	public List queryGroupBeanByMapAndPage(HashMap map, Page page)
			throws Exception {
		List list = groupmanagerDAO.queryGroupBeanByMapAndPage(map, page);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	public List queryGroupMemberByEmpBeanByMap(HashMap map) throws Exception {
		List list = groupmanagerDAO.queryGroupMemberByEmpBeanByMap(map);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	public List queryGroupMemberByRoleBeanByMap(HashMap map) throws Exception {
		List list = groupmanagerDAO.queryGroupMemberByRoleBeanByMap(map);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	public List queryGroupMemberByOrgBeanByMapAndPage(HashMap map)
			throws Exception {
		List list = groupmanagerDAO.queryGroupMemberByOrgBeanByMapAndPage(map);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	public List queryGroupPositionEmpByParentBeanByMap(HashMap map)
			throws Exception {
		List list = groupmanagerDAO.queryGroupPositionEmpByParentBeanByMap(map);
		return list;
	}

	/**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	public List queryRootGroupBeanByMap(HashMap map) throws Exception {
		List list = groupmanagerDAO.queryRootGroupBeanByMap(map);
		return list;
	}

	/**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setGroupmanagerDAO(IGroupmanagerDAO groupmanagerDAO) throws Exception {
        this.groupmanagerDAO = groupmanagerDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IGroupmanagerDAO getGroupmanagerDAO() throws Exception {
        return this.groupmanagerDAO;
    }

    /**
     * 通过Map方式的查询返回Bean，查询分页数据
     * @abatorgenerated
     */
    public List queryRootGroupTypeBeanByMapAndPage(HashMap map, Page page) throws Exception {
        List list = groupmanagerDAO.queryRootGroupTypeBeanByMapAndPage(map,page);
        return list;
    }
    /**
     * 增加一条新记录
     * @param record
     * @return
     */
    public boolean addGroup(OmGroup record){
    	Long result = omGroupDAO.insert(record);
    	if(result!=null && result>0){
    		return true;
    	}else{
    		return false;
    	}
    }
    /**
     * 更新一条新记录
     * @param record
     * @return
     */
    public boolean updateGroup(OmGroup record) throws Exception{
    	if("public".equals(record.getGrouptype())){
    		updateOmGrouprange(record);
    	}
    	int result = omGroupDAO.updateByPrimaryKeySelective(record);
    	if(result>0){
    		return true;
    	}else{
    		return false;
    	}
    }
    public void deleteGroups(List groupids) throws Exception{
    	omGroupDAO.startBatch();
    	for (int i = 0; i < groupids.size(); i++) {
    		omGroupDAO.deleteByPrimaryKey(Long.valueOf((String)groupids.get(i)));
    	}
    	omGroupDAO.executeBatch();
    }
    /**
     * empids=1, groupid=634, roleids=null, orgids=112254,113010
     * 增加挂在群组下的人员机构角色
     */
    public void addGroupEmpOrg(HashMap param) throws Exception{
    	String empids = (String)param.get("empids");
    	String groupid = (String)param.get("groupid");
    	String roleids = (String)param.get("roleids");
    	String orgids = (String)param.get("orgids");
    	//人员
    	if(StringUtils.isNotBlank(empids)){
    		omEmpgroupDAO.startBatch();
    		String[] empidArra = empids.split(",");
    		for (int i = 0; i < empidArra.length; i++) {
    			OmEmpgroupKey record = new OmEmpgroupKey();
    			record.setGroupid(Long.parseLong(groupid));
    			record.setEmpid(Long.parseLong(empidArra[i]));
    			omEmpgroupDAO.insert(record);
			}
    		omEmpgroupDAO.executeBatch();
    	}
    	omGroupmemberDAO.startBatch();
    	//角色
    	if(StringUtils.isNotBlank(roleids)){
    		String[] roleidArra = roleids.split(",");
    		for (int i = 0; i < roleidArra.length; i++) {
    			OmGroupmemberKey record = new OmGroupmemberKey();
    			record.setGroupid(Long.parseLong(groupid));
    			record.setMemberid(roleidArra[i]);
    			record.setFlag("3");
    			omGroupmemberDAO.insert(record);
    		}
    	}
    	//机构
    	if(StringUtils.isNotBlank(orgids)){
    		String[] orgidArra = orgids.split(",");
    		for (int i = 0; i < orgidArra.length; i++) {
    			OmGroupmemberKey record = new OmGroupmemberKey();
    			record.setGroupid(Long.parseLong(groupid));
    			record.setMemberid(orgidArra[i]);
    			record.setFlag("2");
    			omGroupmemberDAO.insert(record);
    		}
    	}
    	omGroupmemberDAO.executeBatch();
    }
    /**
     * empids=1, groupid=634, roleids=null, orgids=112254,113010
     * 删除挂在群组下的人员机构角色
     */
    public void deleteGroupEmpOrg(HashMap param) throws Exception{
    	String empids = (String)param.get("empids");
    	String groupid = (String)param.get("groupid");
    	String roleids = (String)param.get("roleids");
    	String orgids = (String)param.get("orgids");
    	//人员
    	if(StringUtils.isNotBlank(empids)){
    		omEmpgroupDAO.startBatch();
    		String[] empidArra = empids.split(",");
    		for (int i = 0; i < empidArra.length; i++) {
    			OmEmpgroupKey record = new OmEmpgroupKey();
    			record.setGroupid(Long.parseLong(groupid));
    			record.setEmpid(Long.parseLong(empidArra[i]));
    			omEmpgroupDAO.deleteByPrimaryKey(record);
			}
    		omEmpgroupDAO.executeBatch();
    	}
    	omGroupmemberDAO.startBatch();
    	//角色
    	if(StringUtils.isNotBlank(roleids)){
    		String[] roleidArra = roleids.split(",");
    		for (int i = 0; i < roleidArra.length; i++) {
    			OmGroupmemberKey record = new OmGroupmemberKey();
    			record.setGroupid(Long.parseLong(groupid));
    			record.setMemberid(roleidArra[i]);
    			record.setFlag("3");
    			omGroupmemberDAO.deleteByPrimaryKey(record);
    		}
    	}
    	//机构
    	if(StringUtils.isNotBlank(orgids)){
    		String[] orgidArra = orgids.split(",");
    		for (int i = 0; i < orgidArra.length; i++) {
    			OmGroupmemberKey record = new OmGroupmemberKey();
    			record.setGroupid(Long.parseLong(groupid));
    			record.setMemberid(orgidArra[i]);
    			record.setFlag("2");
    			omGroupmemberDAO.deleteByPrimaryKey(record);
    		}
    	}
    	omGroupmemberDAO.executeBatch();
    }
    /**
     * 查询拥有当前群组的人员/机构/角色
     * @param dsName
     * @param groupid
     * @param group
     * @throws Exception
     */
	public void queryOmGrouprange(OmGroup group)
	throws Exception {
		List<HashMap> empOog = groupmanagerDAO.queryEmpOfOmGrouprange(group.getGroupid());//人员群组
		List<HashMap> orgOog = groupmanagerDAO.queryOrgOfOmGrouprange(group.getGroupid());//机构群组
		List<HashMap> roleOog = groupmanagerDAO.queryRoleOfOmGrouprange(group.getGroupid());//角色群组
//		人员群组
		String empnames = "";
		String empids = "";
		for (int i = 0; i < empOog.size(); i++) {
			HashMap empGroup = empOog.get(i);
			empnames += empGroup.get("empname");
			empids += empGroup.get("empid");
			if (i!=empOog.size()-1) {
				empnames += ",";
				empids += ",";
			}
		}
		group.setEmpnames(empnames);
		group.setEmpids(empids);
		
//		机构群组
		String orgids = "";
		String orgnames = "";
		for (int i = 0; i < orgOog.size(); i++) {
			HashMap orgGroup = orgOog.get(i);
			orgids += orgGroup.get("orgid");
			orgnames += orgGroup.get("orgname");
			if (i!=orgOog.size()-1) {
				empnames += ",";
				empids += ",";
			}
		}
		group.setOrgids(orgids);
		group.setOrgnames(orgnames);
// 		取角色群组
		String roleids = "";
		String rolenames = "";
		for (int i = 0; i < roleOog.size(); i++) {
			HashMap roleGroup = roleOog.get(i);
			roleids += roleGroup.get("roleid");
			rolenames += roleGroup.get("rolename");
			if (i!=roleOog.size()-1) {
				roleids += ",";
				rolenames += ",";
			}
		}
		group.setRoleids(roleids);
		group.setRolenames(rolenames);
	}
	private void updateOmGrouprange(OmGroup group) throws Exception{
    	Long groupid = group.getGroupid();
    	String empids = group.getEmpids();
    	String roleids = group.getRoleids();
    	String orgids = group.getOrgids();
    	
    	omGrouprangeDAO.startBatch();
//    	主键删除
    	OmGrouprangeExample example = new OmGrouprangeExample();
    	example.createCriteria().andGroupidEqualTo(groupid);
    	omGrouprangeDAO.deleteByExample(example);
//    	人员
    	if(StringUtils.isNotBlank(empids)){
			String[] empidArra = empids.split(",");
			for (int i = 0; i < empidArra.length; i++) {
				OmGrouprangeKey record = new OmGrouprangeKey();
				record.setGroupid(groupid);
				record.setGrangeid(empidArra[i]);
				record.setFlag("1");
				omGrouprangeDAO.insert(record);
			}
    	}
//    	机构
    	if(StringUtils.isNotBlank(orgids)){
    		String[] orgArra = orgids.split(",");
    		for (int i = 0; i < orgArra.length; i++) {
    			OmGrouprangeKey record = new OmGrouprangeKey();
    			record.setGroupid(groupid);
    			record.setGrangeid(orgArra[i]);
    			record.setFlag("2");
    			omGrouprangeDAO.insert(record);
    		}
    	}
//    	角色
    	if(StringUtils.isNotBlank(roleids)){
			String[] roleArra = roleids.split(",");
			for (int i = 0; i < roleArra.length; i++) {
				OmGrouprangeKey record = new OmGrouprangeKey();
				record.setGroupid(groupid);
				record.setGrangeid(roleArra[i]);
				record.setFlag("3");
				omGrouprangeDAO.insert(record);
			}
    	}
    	omGrouprangeDAO.startBatch();
	}
}