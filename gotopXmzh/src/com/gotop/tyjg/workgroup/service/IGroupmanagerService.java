package com.gotop.tyjg.workgroup.service;

import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.stable.model.OmGroup;
import com.gotop.tyjg.workgroup.dao.IGroupmanagerDAO;
import com.primeton.utils.Page;

public interface IGroupmanagerService {
    /**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	OmGroup querySGroupBeanByMap(HashMap map) throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List manageGroupEmpBeanByMapAndPage(HashMap map, Page page)
			throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List manageGroupRoleBeanByMapAndPage(HashMap map, Page page)
			throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List managePositionRoleBeanByMapAndPage(HashMap map, Page page)
			throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List queryPositionEmpBeanByMapAndPage(HashMap map, Page page)
			throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List queryGroupEmpOrgBeanByMapAndPage(HashMap map, Page page)
			throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List queryGroupPosiBeanByMapAndPage(HashMap map, Page page)
			throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List updateSelfGroupBeanByMapAndPage(HashMap map, Page page)
			throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List queryGroupBeanByMapAndPage(HashMap map, Page page) throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	List queryGroupMemberByEmpBeanByMap(HashMap map) throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	List queryGroupMemberByRoleBeanByMap(HashMap map) throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List queryGroupMemberByOrgBeanByMapAndPage(HashMap map)
			throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	List queryGroupPositionEmpByParentBeanByMap(HashMap map) throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	List queryRootGroupBeanByMap(HashMap map) throws Exception;

	/**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void setGroupmanagerDAO(IGroupmanagerDAO groupmanagerDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    IGroupmanagerDAO getGroupmanagerDAO() throws Exception;

    /**
     * 通过Map方式的查询返回Bean，查询分页数据
     * @abatorgenerated
     */
    List queryRootGroupTypeBeanByMapAndPage(HashMap map, Page page) throws Exception;
    
    /**
     * 增加一条新记录
     * @param record
     * @return
     */
    public boolean addGroup(OmGroup record);
    /**
     * 更新一条新记录
     * @param record
     * @return
     */
    public boolean updateGroup(OmGroup record) throws Exception;
    /**
     * 删除一组记录
     * @param record
     * @return
     */
    public void deleteGroups(List groupid) throws Exception;
    
    public void addGroupEmpOrg(HashMap param) throws Exception;
    
    public void deleteGroupEmpOrg(HashMap param) throws Exception;
    
    public void queryOmGrouprange(OmGroup group)
	throws Exception;
}