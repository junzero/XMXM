package com.gotop.tyjg.roleprivilege.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.roleprivilege.model.AcApplication;
import com.gotop.tyjg.roleprivilege.model.AcFunction;
import com.gotop.tyjg.roleprivilege.model.AcFuncGroup;
import com.gotop.tyjg.roleprivilege.model.AcRoleFunc;
import com.gotop.tyjg.roleprivilege.model.AcRoleFuncLock;
public interface IPrivilegeDao {
	
	/**
	 * 查询顶级功能组列表
	 * @param acFunctionGroup
	 * @return
	 * @throws SQLException
	 */
    public List<AcFuncGroup> queryAcFuncGroupList(AcFuncGroup acFuncGroup) throws SQLException;
    
    /**
     * 查询子功能组列表
     * @param acFunctionGroup
     * @return
     * @throws SQLException
     */
    public List<AcFuncGroup> querySubAcFuncGroupList(AcFuncGroup acFuncGroup) throws SQLException;
    
    /**
     * 查询功能列表
     * @param acFunction
     * @return
     * @throws SQLException
     */
    public List<AcFunction> queryAcFunctionList(AcFunction acFunction) throws SQLException;
    
    /**
     * 角色功能对应关系列表查询
     * @param acRoleFunc
     * @return
     * @throws SQLException
     */
    public List<AcRoleFunc> queryAcRoleFuncList(AcRoleFunc acRoleFunc) throws SQLException;
    
    /**
     * 角色权限关系表查询（功能组的）
     * @param acRoleFuncLock
     * @return
     * @throws SQLException
     */
    public List<AcRoleFuncLock> queryAcRoleFuncLockList(AcRoleFuncLock acRoleFuncLock) throws SQLException;
    
    /**
     * 查询指定应用
     * @param acApplication
     * @return
     * @throws SQLException
     */
    public List<AcApplication> queryAcApplicationList(AcApplication acApplication) throws SQLException;
    
    /**
     * 新增角色功能关系
     * @param acRoleFunc
     * @return
     * @throws SQLException
     */
    public boolean insertAcRoleFunc(HashMap acRoleFunc) throws SQLException;
	/**
	 * 新增功能组与角色关系
	 * @param acRoleFunc
	 * @return
	 * @throws SQLException
	 */
	public boolean insertRoleFuncOfFuncGroup(HashMap acRoleFunc) throws SQLException;
    /**
     * 新增角色功能组关系
     * @param acRoleFuncLock
     * @return
     * @throws SQLException
     */
    public boolean insertAcRoleFuncLock(HashMap acRoleFuncLock) throws SQLException;
    
    /**
     * 删除角色功能关系
     * @param acRoleFunc
     * @return
     * @throws SQLException
     */
    public boolean deleteAcRoleFunc(AcRoleFunc acRoleFunc) throws SQLException;
    
    /**
     * 删除角色功能组关系
     * @param acRoleFuncLock
     * @return
     * @throws SQLException
     */
    public boolean deleteAcRoleFuncLock(AcRoleFuncLock acRoleFuncLock) throws SQLException;
    
    /**
     * 角色功能组对应关系列表查询
     * @param acRoleFunc
     * @return
     * @throws SQLException
     */
	public List<AcRoleFunc> queryAcRoleFuncGroupList(AcRoleFunc acRoleFunc) throws SQLException;
    
}
