package com.gotop.tyjg.workgroup.dao;

import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.stable.model.OmGroup;
import com.primeton.utils.Page;

public interface IGroupmanagerDAO {
    /**
	 * 通过Map方式的查询返回Bean，查询记录不分页
	 * @abatorgenerated
	 */
	OmGroup querySGroupBeanByMap(HashMap map) throws Exception;

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List manageGroupEmpBeanByMapAndPage(HashMap map, Page page);

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List manageGroupRoleBeanByMapAndPage(HashMap map, Page page);

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List managePositionRoleBeanByMapAndPage(HashMap map, Page page);

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List queryPositionEmpBeanByMapAndPage(HashMap map, Page page);

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List queryGroupEmpOrgBeanByMapAndPage(HashMap map, Page page);

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List queryGroupPosiBeanByMapAndPage(HashMap map, Page page);

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List updateSelfGroupBeanByMapAndPage(HashMap map, Page page);

	/**
	 * 通过Map方式的查询返回Bean，查询分页数据
	 * @abatorgenerated
	 */
	List queryGroupBeanByMapAndPage(HashMap map, Page page);

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
	List queryGroupMemberByOrgBeanByMapAndPage(HashMap map);

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
     * 通过Map方式的查询返回Bean，查询分页数据
     * @abatorgenerated
     */
    List queryRootGroupTypeBeanByMapAndPage(HashMap map, Page page);
    /**
     * 使用范围人员
     * @param groupid
     * @return empname
     *         empid
     */
    public List<HashMap> queryEmpOfOmGrouprange(Long groupid);
    /**
     * 使用范围机构
     * @param groupid
     * @return orgname
     *         orgid
     */
    public List<HashMap> queryOrgOfOmGrouprange(Long groupid);
    /**
     * 使用范围角色
     * @param groupid
     * @return rolename
     *         roleid
     */
    public List<HashMap> queryRoleOfOmGrouprange(Long groupid);
}