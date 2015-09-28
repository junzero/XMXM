package com.gotop.tyjg.roleprivilege.dao;

import java.sql.SQLException;
import java.util.List;

import com.gotop.tyjg.roleprivilege.model.AcOperatorRole;
import com.gotop.tyjg.roleprivilege.model.AcRole;
import com.primeton.utils.Page;

public interface IRoleDao {
	
	/**
	 * 查询操作人员的权限类型 （通过数组中的数据比较得出类型）
	 * @param operateId  操作元id或empid
	 * @return
	 * @throws Exception
	 */
    public List<String> queryRoleTypeByOperid(String operateId) throws Exception;
    
    /**
     * 查询角色列表（分页查询）
     * @param acRole 查询条件角色对象
     * @param page
     * @return
     * @throws SQLException
     */
    public List<AcRole> queryRoleList(AcRole acRole, Page page) throws SQLException;
    
    /**
     * 查询角色列表（非分页查询）
     * @param acRole 查询条件角色对象
     * @return 
     * @throws SQLException
     */
    public List<AcRole> queryRoleList(AcRole acRole) throws SQLException;
    
    /**
     * 查询角色记录的总记录数
     * @param acRole
     * @return
     * @throws SQLException
     */
    public int queryRoleCount(AcRole acRole) throws SQLException;
    
    /**
     * 新增角色表
     * @param acRole
     * @return
     * @throws SQLException
     */
    public boolean insertAcRole(AcRole acRole) throws SQLException;
    
    /**
     * 删除角色表
     * @param acRole
     * @return
     * @throws SQLException
     */
    public boolean deleteAcRole(AcRole acRole) throws SQLException;
    
    /**
     * 修改角色表
     * @param acRole
     * @return
     * @throws SQLException
     */
    public boolean updateAcRole(AcRole acRole) throws SQLException;
    
    /**
     * 新增角色操作员关系记录（新增该条角色记录的人员）
     * @param acOperatorRole 操作员角色表
     * @return
     * @throws SQLException
     */
	public boolean insertAcOperatorRole(AcOperatorRole acOperatorRole) throws SQLException;
	
	/**
	 * 删除角色操作员关系记录
	 * @param acOperatorRole
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAcOperatorRole(AcOperatorRole acOperatorRole) throws SQLException;
	
	/**
	 * 删除角色组织对应关系（联级授权）
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAcRoleFunclock(AcRole acRole) throws SQLException;
	/**
	 * 删除角色具有的数据权限
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAcRoleDatapriv(AcRole acRole) throws SQLException;
	
	/**
	 * 删除角色与实体字段的关系
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAcEntityFieldRole(AcRole acRole) throws SQLException;
	
	/**
	 * 删除角色与实体关系
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAcEntityRole(AcRole acRole) throws SQLException;
	
	/**
	 * 删除人员角色关系
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAcOperatorRole1(AcRole acRole) throws SQLException;
	
	/**
	 * 删除角色与功能的关系
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAcFunc(AcRole acRole) throws SQLException;
	
	/**
	 * 删除角色与组织对象的关系 delete_acrolefunc
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAcRoleFunc(AcRole acRole) throws SQLException;
	
	/**
	 * 删除角色1
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAcRole1(AcRole acRole) throws SQLException;
	
	public Integer queryAcOperatorRole(AcOperatorRole acOperatorRole) throws SQLException;
}
