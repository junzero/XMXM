package com.gotop.tyjg.roleprivilege.service.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.roleprivilege.model.AcApplication;
import com.gotop.tyjg.roleprivilege.model.AcFunction;
import com.gotop.tyjg.roleprivilege.model.AcFuncGroup;
import com.gotop.tyjg.roleprivilege.model.AcRoleFunc;
import com.gotop.tyjg.roleprivilege.model.AcRoleFuncLock;
import com.gotop.tyjg.roleprivilege.model.OmPartyRole;
import com.gotop.tyjg.roleprivilege.dao.IOperatorRoleDao;
import com.gotop.tyjg.roleprivilege.dao.IPrivilegeDao;
import com.gotop.tyjg.roleprivilege.dao.IRoleDao;
import com.gotop.tyjg.roleprivilege.dao.imp.RoleDao;
import com.gotop.tyjg.roleprivilege.model.AcOperatorRole;
import com.gotop.tyjg.roleprivilege.model.AcRole;
import com.gotop.tyjg.roleprivilege.service.IRoleService;
import com.primeton.utils.Page;
import org.apache.log4j.Logger;

public class RoleService implements IRoleService{
	protected static Logger log = Logger.getLogger(RoleService.class);

	private IOperatorRoleDao operatorRoleDao;
    @Override
	public boolean savePrivilege(HashMap hm) throws SQLException {
		List<HashMap> funcList = (List)hm.get("funcList");
		List<HashMap> funcGroupList = (List)hm.get("funcGroupList");
		List<HashMap> subFuncGroupList = (List)hm.get("subFuncGroupList");
	    HashMap hashMap = (HashMap)hm.get("acRole");
	    AcRole acRole = new AcRole();
	    acRole.setRoleId((String)hashMap.get("roleId"));
	    this.roleDao.deleteAcRoleFunc(acRole);
	    if(funcList != null) {
	    	for(int i=0; i<funcList.size(); i++) {
		    	this.privilegeDao.insertAcRoleFunc(funcList.get(i));
		    }
	    }
	    log.info("---funcGroupList:"+funcGroupList);
	    log.info("---subFuncGroupList:"+subFuncGroupList);
	    if(funcGroupList != null) {
	    	for(int i=0; i<funcGroupList.size(); i++) {
		    	this.privilegeDao.insertRoleFuncOfFuncGroup(funcGroupList.get(i));
		    }
	    }
	    if(subFuncGroupList != null) {
	    	for(int i=0; i<subFuncGroupList.size(); i++) {
		    	this.privilegeDao.insertRoleFuncOfFuncGroup(subFuncGroupList.get(i));
		    }
	    }
/*	    
	    this.roleDao.deleteAcRoleFunclock(acRole);
	    if(funcGroupList != null) {
	    	for(int i=0; i<funcGroupList.size(); i++) {
		    	this.privilegeDao.insertAcRoleFuncLock(funcGroupList.get(i));
		    }
	    }
	    if(subFuncGroupList != null) {
	    	for(int i=0; i<subFuncGroupList.size(); i++) {
		    	this.privilegeDao.insertAcRoleFuncLock(subFuncGroupList.get(i));
		    }
	    }
*/	    
		return true;
	}

	private IPrivilegeDao privilegeDao;

	private IRoleDao roleDao;
	
	public IPrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}
	public IRoleDao getRoleDao() {
		return roleDao;
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
		return this.privilegeDao.queryAcApplicationList(acApplication);
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
		return this.getPrivilegeDao().queryAcFuncGroupList(acFuncGroup);
		
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
		return this.getPrivilegeDao().queryAcFunctionList(acFunction);
	}
	
	/**
	 * 查询角色功能对应关系列表
	 * @param acRoleFunc
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<AcRoleFunc> queryAcRoleFuncList(AcRoleFunc acRoleFunc)
			throws SQLException {
		return this.privilegeDao.queryAcRoleFuncList(acRoleFunc);
	}
	/**
	 * 角色功能组对应关系列表查询
	 * @param acRoleFunc
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<AcRoleFunc> queryAcRoleFuncGroupList(AcRoleFunc acRoleFunc) throws SQLException {
		return this.privilegeDao.queryAcRoleFuncGroupList(acRoleFunc);
	}
	/**
	 * 查询角色功能组对应关系列表
	 * @param acRoleFuncLock
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<AcRoleFuncLock> queryAcRoleFuncLockList(
			AcRoleFuncLock acRoleFuncLock) throws SQLException {
		return this.privilegeDao.queryAcRoleFuncLockList(acRoleFuncLock);
	}

	/**
	 * 分页查找角色列表
	 * @param object
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<AcRole> queryRoleList(HashMap object, Page page) throws SQLException {
		AcRole acRole = new AcRole();
		if(object.get("acRole.roleName") != null) {
			acRole.setRoleName(object.get("acRole.roleName").toString());
		}
		if(object.get("acRole.roleType") != null && !"".equals(object.get("acRole.roleType").toString())) {
			acRole.setRoleType(object.get("acRole.roleType").toString());
		}
		int count = this.getRoleDao().queryRoleCount(acRole);
		page.setCount(count);
		List<AcRole> acRoleList = this.getRoleDao().queryRoleList(acRole, page);
		return acRoleList;
	}
	/**
	 * 根据操作元的id（empid）查找对应的权限类型
	 * @param operateId
	 * @return
	 * @throws Exception
	 */
	@Override
	public String queryRoleTypeByOperid(String operateId) throws Exception {
		List<String> roleTypeList = this.getRoleDao().queryRoleTypeByOperid(operateId);
		if (roleTypeList == null) {
			return "0";
		} else {
			for (int i = 0; i < roleTypeList.size(); i++) {
				Object str = roleTypeList.get(i);
				if (str!=null && "1".equals(str.toString())) {
					return "1";
				}
			}
		}
		return "0";
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
		return this.getPrivilegeDao().querySubAcFuncGroupList(acFuncGroup);
	}
	
	/**
	 * datacell中提交的数据保存操作
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean saveRole(HashMap object) throws SQLException {
		List<AcRole> insertAcRoleList = (List<AcRole>)object.get("insertEntities");
		List<AcRole> updateAcRoleList = (List<AcRole>)object.get("updateEntities");
		List<AcRole> deleteAcRoleList = (List<AcRole>)object.get("deleteEntities");
		AcRole acRole = null;
		for(int i=0; i<deleteAcRoleList.size(); i++) {
			acRole = deleteAcRoleList.get(i);
			//删除角色组织对应关系（联级授权）
			this.roleDao.deleteAcRoleFunclock(acRole);
			//删除角色具有的数据权限
			this.roleDao.deleteAcRoleDatapriv(acRole);
			//删除角色与实体字段的关系
			this.roleDao.deleteAcEntityFieldRole(acRole);
			//删除角色与实体关系
			this.roleDao.deleteAcEntityRole(acRole);
			//删除人员角色关系
			this.roleDao.deleteAcOperatorRole1(acRole);
			//删除角色与功能的关系
			this.roleDao.deleteAcFunc(acRole);
			//删除角色与组织对象的关系
			this.roleDao.deleteAcRoleFunc(acRole);
			//删除角色机构关系
			OmPartyRole omPartyRole = new OmPartyRole();
			omPartyRole.setRoleId(acRole.getRoleId());
			this.operatorRoleDao.deleteOmPartyRole(omPartyRole);
			//删除角色
			this.roleDao.deleteAcRole1(acRole);
			
		}
		AcOperatorRole acOperatorRole = new AcOperatorRole();
		for(int i=0; i<insertAcRoleList.size(); i++) {
			acRole = insertAcRoleList.get(i);
			if(this.getRoleDao().queryRoleList(acRole).size() == 0) {
				//新增角色信息
				this.getRoleDao().insertAcRole(acRole);
				acOperatorRole.setOperatorId((String)object.get("operatorId"));
				acOperatorRole.setRoleId(acRole.getRoleId());
				acOperatorRole.setAgency("0");//默认为0
			    //新增角色操作人员关系
				this.getRoleDao().insertAcOperatorRole(acOperatorRole);
			}
		}
		for(int i=0; i<updateAcRoleList.size(); i++) {
			acRole = updateAcRoleList.get(i);
			//修改角色信息
			this.getRoleDao().updateAcRole(acRole);
		}
		return false;
	}
	public void setPrivilegeDao(IPrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}

	/**
	 * 设置角色所拥有的权限功能  用isLock
	 * @param funcGroupList 顶级功能组
	 * @param subFuncGroupList 子功能组
	 * @param funcList 功能
	 * @param acRoleFuncList 角色功能关系
	 * @param acRoleFuncLockList 角色功能组关系
	 * @return
	 */
    @Override
	public void setRoleCheck(List<AcFuncGroup> funcGroupList,
			List<AcFuncGroup> subFuncGroupList, List<AcFunction> funcList,
			List<AcRoleFunc> acRoleFuncList,
			List<AcRoleFuncLock> acRoleFuncLockList) {
		if (acRoleFuncLockList == null) {
			return;
		}
		AcFuncGroup acFuncGroup = null;
		AcFunction acFunction = null;
		AcRoleFuncLock acRoleFuncLock = null;
		AcRoleFunc acRoleFunc = null;
		//功能组
		if (funcGroupList != null) {
			for (int i = 0; i < funcGroupList.size(); i++) {
				acFuncGroup = funcGroupList.get(i);
				if (acFuncGroup == null) {
					continue;
				}
				String funcGroupId = acFuncGroup.getFuncGroupId();
				String appId = acFuncGroup.getAppId();
				for (int j = 0; j < acRoleFuncLockList.size(); j++) {
					acRoleFuncLock = acRoleFuncLockList.get(j);
					if (acRoleFuncLock == null) {
						continue;
					}
					String tempFunGI = acRoleFuncLock.getFuncGroupId();
					String tempAppId = acRoleFuncLock.getAppId();
					if (funcGroupId.equals(tempFunGI)  && appId.equals(tempAppId) && acRoleFuncLock.getIsLock() != null) {
						String isLock = acRoleFuncLock.getIsLock();
						acFuncGroup.setIsLock(isLock);
						break;
					}
				}
			}
		}
		//子功能组
		if (subFuncGroupList != null) {
			for (int i = 0; i < subFuncGroupList.size(); i++) {
				acFuncGroup = subFuncGroupList.get(i);
				if (acFuncGroup == null) {
					continue;
				}
				String funcGroupId = acFuncGroup.getFuncGroupId();
				String appId = acFuncGroup.getAppId();
				for (int j = 0; j < acRoleFuncLockList.size(); j++) {
					acRoleFuncLock = acRoleFuncLockList.get(j);
					if (acRoleFuncLock == null) {
						continue;
					}
					String tempFunGI = acRoleFuncLock.getFuncGroupId();
					String tempAppId = acRoleFuncLock.getAppId();
					if (funcGroupId.equals(tempFunGI) && appId.equals(tempAppId) && acRoleFuncLock.getIsLock() != null) {
						String isLock = acRoleFuncLock.getIsLock();
						acFuncGroup.setIsLock(isLock);
						break;
					}
				}
			}
		}

		//功能锁定管理
		if (acRoleFuncList == null) {
			return;
		}
		if (funcList != null) {
			for (int i = 0; i < funcList.size(); i++) {
				acFunction = funcList.get(i);
				if (acFunction == null) {
					continue;
				}
				String funcCode = acFunction.getFuncCode();
				for (int j = 0; j < acRoleFuncList.size(); j++) {
					acRoleFunc = acRoleFuncList.get(j);
					if (acRoleFunc == null) {
						continue;
					}
					String tempFunGI = acRoleFunc.getFuncCode();

					if (funcCode.equalsIgnoreCase(tempFunGI)) {
						String isLock = acRoleFunc.getIsLock();
						acFunction.setIsLock(isLock);
						break;
					}
				}
			}
		}
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}
	public IOperatorRoleDao getOperatorRoleDao() {
		return operatorRoleDao;
	}
	public void setOperatorRoleDao(IOperatorRoleDao operatorRoleDao) {
		this.operatorRoleDao = operatorRoleDao;
	}



}
