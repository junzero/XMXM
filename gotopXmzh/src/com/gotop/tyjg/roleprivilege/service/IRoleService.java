package com.gotop.tyjg.roleprivilege.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.roleprivilege.model.AcApplication;
import com.gotop.tyjg.roleprivilege.model.AcFunction;
import com.gotop.tyjg.roleprivilege.model.AcFuncGroup;
import com.gotop.tyjg.roleprivilege.model.AcRole;
import com.gotop.tyjg.roleprivilege.model.AcRoleFunc;
import com.gotop.tyjg.roleprivilege.model.AcRoleFuncLock;
import com.primeton.utils.Page;

public interface IRoleService {
	
	
	/**
	 * 根据操作元的id（empid）查找对应的权限类型
	 * @param operateId
	 * @return
	 * @throws Exception
	 */
	public String queryRoleTypeByOperid(String operateId) throws Exception;
	
	/**
	 * 分页查找角色列表
	 * @param object
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public List<AcRole> queryRoleList(HashMap object, Page page) throws SQLException;
	
	/**
	 * datacell中提交的数据保存操作
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public boolean saveRole(HashMap object) throws SQLException;
	
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
	 * 查询角色功能对应关系列表
	 * @param acRoleFunc
	 * @return
	 * @throws SQLException
	 */
	public List<AcRoleFunc> queryAcRoleFuncList(AcRoleFunc acRoleFunc) throws SQLException;
	
	/**
	 * 查询角色功能组对应关系列表
	 * @param acRoleFuncLock
	 * @return
	 * @throws SQLException
	 */
	public List<AcRoleFuncLock> queryAcRoleFuncLockList(AcRoleFuncLock acRoleFuncLock) throws SQLException;
	
	/**
	 * 设置角色所拥有的权限功能  用isLock
	 * @param funcGroupList 顶级功能组
	 * @param subFuncGroupList 子功能组
	 * @param funcList 功能
	 * @param acRoleFuncList 角色功能关系
	 * @param acRoleFuncLockList 角色功能组关系
	 * @return
	 */
	public void setRoleCheck(List<AcFuncGroup> funcGroupList, List<AcFuncGroup> subFuncGroupList, List<AcFunction> funcList, List<AcRoleFunc> acRoleFuncList, List<AcRoleFuncLock> acRoleFuncLockList);

    /**
     * 查询指定应用
     * @param acApplication
     * @return
     * @throws SQLException
     */
    public List<AcApplication> queryAcApplicationList(AcApplication acApplication) throws SQLException;
    
    /**
     * 角色权限保存
     * @param hm
     * @return
     * @throws SQLException
     */
    public boolean savePrivilege(HashMap hm) throws SQLException;
    
	/**
	 * 角色功能组对应关系列表查询
	 * @param acRoleFunc
	 * @return
	 * @throws SQLException
	 */
	public List<AcRoleFunc> queryAcRoleFuncGroupList(AcRoleFunc acRoleFunc) throws SQLException;
}
