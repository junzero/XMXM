package com.gotop.tyjg.roleprivilege.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.roleprivilege.model.AcOperatorRole;
import com.gotop.tyjg.roleprivilege.model.OmPartyRole;
import com.primeton.utils.Page;



public interface IOperatorRoleDao {
	
    /**
     * 删除角色机构关系
     * @param hashMap
     * @return
     * @throws SQLException
     */
    public boolean deleteOmPartyRole(OmPartyRole omPartyRole) throws SQLException;
    
    /**
     * 新增角色机构关系
     * @param hashMap
     * @return
     * @throws SQLException
     */
    public boolean insertOmPartyRole(OmPartyRole omPartyRole) throws SQLException;
    
	/**
	 * 查询人员角色关系列表（分页查询）
	 * @param acOperatorRole
	 * @param page
	 * @return
	 * @throws SQLException
	 */
    public List<AcOperatorRole> queryAcOperatorRoleList(AcOperatorRole acOperatorRole, Page page) throws SQLException;
    
    
    /**
     * 查询人员角色关系数量
     * @param acOperatorRole
     * @return
     * @throws SQLException
     */
    public int queryAcOperatorRoleCount(AcOperatorRole acOperatorRole) throws SQLException;
    
    /**
     * 删除人员角色关系
     * @param acOperatorRole
     * @return
     * @throws SQLException
     */
    public boolean deleteAcOperatorRole(AcOperatorRole acOperatorRole) throws SQLException;
    
    /**
     * 新增人员角色关系
     * @param acOperatorRole
     * @return
     * @throws SQLException
     */
    public boolean insertAcOperatorRole(AcOperatorRole acOperatorRole) throws SQLException;
    
    /**
     * 查询一个角色下的人员
     * @param acOperatorRole
     * @return
     * @throws SQLException
     */
    public List<AcOperatorRole> queryAcOperatorRoleEmp(AcOperatorRole acOperatorRole) throws SQLException;
    
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
    
    public int queryOmPartyRoleNotCount(OmPartyRole omPartyRole) throws SQLException;
    public int queryOmPartyRoleCount(OmPartyRole omPartyRole) throws SQLException;
}
