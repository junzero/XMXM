package com.gotop.tyjg.roleprivilege.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.roleprivilege.model.AcFuncGroup;
import com.gotop.tyjg.roleprivilege.model.AcOperatorRole;
import com.gotop.tyjg.roleprivilege.model.OmPartyRole;
import com.primeton.utils.Page;

public interface IOperatorRoleService {
    
	/**
	 * 查询人员角色关系列表（分页查询）
	 * @param acOperatorRole
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public List<AcOperatorRole> queryAcOperatorRoleList(AcOperatorRole acOperatorRole, Page page) throws SQLException;
	
    /**
     * 删除人员角色关系
     * @param acOperatorRole
     * @return
     * @throws SQLException
     */
	public boolean deleteOperatorRole(HashMap hm) throws SQLException;
	
	/**
	 * 新增人员角色关系
	 * @param acOperatorRole
	 * @return
	 * @throws SQLException
	 */
	public boolean insertOperatorRole(AcOperatorRole acOperatorRole) throws SQLException;
	
	/**
	 * 根据角色查询人员（并将人员信息组合成字符串）
	 * @param acOperatorRole
	 * @return
	 * @throws SQLException
	 */
	public HashMap queryAcOperatorRoleEmp(HashMap hm) throws SQLException;
	
	/**
	 * 保存角色人员关系
	 * @param hm
	 * @return
	 * @throws SQLException
	 */
	public boolean saveOperatorRole(HashMap hm) throws SQLException;
	
    /**
     * 分页查询角色机构关系
     * @param omPartyRole
     * @param page
     * @return
     * @throws SQLException
     */
    public List<OmPartyRole> queryOmPartyRoleList(OmPartyRole omPartyRole, Page page) throws SQLException;
    
    /**
     * 查询没有设置角色机构关系的机构列表（包括实体机构和部门）
     * @param omPartyRole
     * @param page
     * @return
     * @throws SQLException
     */
    public List<OmPartyRole> queryOmPartyRoleNotList(OmPartyRole omPartyRole, Page page) throws SQLException;
    
    /**
     * 删除角色机构关系
     * @param hashMap
     * @return
     * @throws SQLException
     */
    public boolean deleteOmPartyRole(HashMap hashMap) throws SQLException;
    
    /**
     * 新增角色机构关系
     * @param hashMap
     * @return
     * @throws SQLException
     */
    public boolean insertOmPartyRole(HashMap hashMap) throws SQLException;
}
