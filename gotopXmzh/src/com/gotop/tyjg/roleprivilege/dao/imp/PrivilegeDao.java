package com.gotop.tyjg.roleprivilege.dao.imp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.gotop.util.dataSource.SqlMapClientDao;

import com.gotop.tyjg.roleprivilege.model.AcApplication;
import com.gotop.tyjg.roleprivilege.model.AcFunction;
import com.gotop.tyjg.roleprivilege.model.AcFuncGroup;
import com.gotop.tyjg.roleprivilege.model.AcRoleFunc;
import com.gotop.tyjg.roleprivilege.model.AcRoleFuncLock;
import com.gotop.tyjg.roleprivilege.dao.IPrivilegeDao;

public class PrivilegeDao extends SqlMapClientDao implements IPrivilegeDao{
    /**
     * 删除角色功能关系
     * @param acRoleFunc
     * @return
     * @throws SQLException
     */
    @Override
	public boolean deleteAcRoleFunc(AcRoleFunc acRoleFunc) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
    /**
     * 删除角色功能组关系
     * @param acRoleFuncLock
     * @return
     * @throws SQLException
     */
	@Override
	public boolean deleteAcRoleFuncLock(AcRoleFuncLock acRoleFuncLock)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
    /**
     * 新增角色功能关系
     * @param acRoleFunc
     * @return
     * @throws SQLException
     */
	@Override
	public boolean insertAcRoleFunc(HashMap acRoleFunc) throws SQLException {
		this.insert("ROLEPRIVILEGE.insert_acrolefunc", acRoleFunc);
		return true;
	}
	/**
	 * 新增功能组与角色关系
	 * @param acRoleFunc
	 * @return
	 * @throws SQLException
	 */
	public boolean insertRoleFuncOfFuncGroup(HashMap acRoleFunc) throws SQLException {
		this.insert("ROLEPRIVILEGE.insert_acroleFuncGroup", acRoleFunc);
		return true;
	}
    /**
     * 新增角色功能组关系
     * @param acRoleFuncLock
     * @return
     * @throws SQLException
     */
	@Override
	public boolean insertAcRoleFuncLock(HashMap acRoleFuncLock)
			throws SQLException {
		this.insert("ROLEPRIVILEGE.insert_acrolefunclock", acRoleFuncLock);
		return true;
	}
	/**
     * 查询指定应用
     * @param acApplication
     * @return
     * @throws SQLException
     */
	@Override
	public List<AcApplication> queryAcApplicationList(
			AcApplication acApplication) throws SQLException {
		return this.queryForList("ROLEPRIVILEGE.query_acapplication_list", acApplication);
	}
	/**
	 * 查询顶级功能组列表
	 * @param acFunctionGroup
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<AcFuncGroup> queryAcFuncGroupList(
			AcFuncGroup acFuncGroup) throws SQLException {
		List<AcFuncGroup> acFuncGroupList = this.queryForList("ROLEPRIVILEGE.query_acfuncgroup_list", acFuncGroup);
		return acFuncGroupList;
	}
    /**
     * 查询功能列表
     * @param acFunction
     * @return
     * @throws SQLException
     */
	@Override
	public List<AcFunction> queryAcFunctionList(AcFunction acFunction)
			throws SQLException {
		List<AcFunction> acFunctionList = this.queryForList("ROLEPRIVILEGE.query_acfunction_list", acFunction);
		return acFunctionList;
	}
    /**
     * 查询子功能组列表
     * @param acFunctionGroup
     * @return
     * @throws SQLException
     */
	@Override
	public List<AcFuncGroup> querySubAcFuncGroupList(
			AcFuncGroup acFuncGroup) throws SQLException {
		List<AcFuncGroup> acSubFuncGroupList = this.queryForList("ROLEPRIVILEGE.query_acfuncgroup_sublist", acFuncGroup);
		return acSubFuncGroupList;
	}
	
    /**
     * 角色功能对应关系列表查询
     * @param acRoleFunc
     * @return
     * @throws SQLException
     */
	@Override
	public List<AcRoleFunc> queryAcRoleFuncList(AcRoleFunc acRoleFunc)
			throws SQLException {
		return this.queryForList("ROLEPRIVILEGE.query_acrolefunc_list", acRoleFunc);
		
	}
    /**
     * 角色功能组对应关系列表查询
     * @param acRoleFunc
     * @return
     * @throws SQLException
     */
	@Override
	public List<AcRoleFunc> queryAcRoleFuncGroupList(AcRoleFunc acRoleFunc) throws SQLException {
		return this.queryForList("ROLEPRIVILEGE.query_acrolefuncgroup_list", acRoleFunc);
		
	}
	
    /**
     * 角色权限关系表查询（功能组的）
     * @param acRoleFuncLock
     * @return
     * @throws SQLException
     */
	@Override
	public List<AcRoleFuncLock> queryAcRoleFuncLockList(
			AcRoleFuncLock acRoleFuncLock) throws SQLException {
		// TODO Auto-generated method stub
		return this.queryForList("ROLEPRIVILEGE.query_acrolefunclock_list", acRoleFuncLock);
	}

}
