package com.gotop.tyjg.roleprivilege.service.imp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.gotop.tyjg.roleprivilege.dao.IOperatorRoleDao;
import com.gotop.tyjg.roleprivilege.dao.IPrivilegeDao;
import com.gotop.tyjg.roleprivilege.dao.IRoleDao;
import com.gotop.tyjg.roleprivilege.model.AcOperatorRole;
import com.gotop.tyjg.roleprivilege.model.OmPartyRole;
import com.gotop.tyjg.roleprivilege.service.IOperatorRoleService;
import com.primeton.utils.Page;

public class OperatorRoleService implements IOperatorRoleService{
	
    /**
     * 删除角色机构关系
     * @param hashMap
     * @return
     * @throws SQLException
     */
    @Override
	public boolean deleteOmPartyRole(HashMap hashMap) throws SQLException {
    	String strRoleIds = (String)hashMap.get("strRoleIds");
    	String strPartyTypes = (String)hashMap.get("strPartyTypes");
    	String strPartyIds = (String)hashMap.get("strPartyIds");
    	String[] roleIds = strRoleIds.split(",");
    	String[] partyTypes = strPartyTypes.split(",");
    	String[] partyIds = strPartyIds.split(",");
    	OmPartyRole omPartyRole = new OmPartyRole();
    	for(int i=0; i<roleIds.length; i++) {
    		omPartyRole.setPartyId(partyIds[i]);
    		omPartyRole.setPartyType(partyTypes[i]);
    		omPartyRole.setRoleId(roleIds[i]);
    		this.operatorRoleDao.deleteOmPartyRole(omPartyRole);
    	}
		
		return true;
	}

    /**
     * 新增角色机构关系
     * @param hashMap
     * @return
     * @throws SQLException
     */
	@Override
	public boolean insertOmPartyRole(HashMap hashMap) throws SQLException {
    	String strRoleIds = (String)hashMap.get("strRoleIds");
    	String strPartyTypes = (String)hashMap.get("strPartyTypes");
    	String strPartyIds = (String)hashMap.get("strPartyIds");
    	String[] roleIds = strRoleIds.split(",");
    	String[] partyTypes = strPartyTypes.split(",");
    	String[] partyIds = strPartyIds.split(",");
    	OmPartyRole omPartyRole = new OmPartyRole();
    	for(int i=0; i<roleIds.length; i++) {
    		omPartyRole.setPartyId(partyIds[i]);
    		omPartyRole.setPartyType(partyTypes[i]);
    		omPartyRole.setRoleId(roleIds[i]);
    		this.operatorRoleDao.insertOmPartyRole(omPartyRole);
    	}
		return true;
	}

	/**
     * 分页查询角色机构关系
     * @param omPartyRole
     * @param page
     * @return
     * @throws SQLException
     */
	@Override
	public List<OmPartyRole> queryOmPartyRoleList(OmPartyRole omPartyRole,
			Page page) throws SQLException {
		int count = this.operatorRoleDao.queryOmPartyRoleCount(omPartyRole);
		page.setCount(count);
		page.setCount(false);
		List<OmPartyRole> lopr = this.operatorRoleDao.queryOmPartyRoleList(omPartyRole, page);
		page.setCount(true);
		return lopr;
	}
	
    /**
     * 查询没有设置角色机构关系的机构列表（包括实体机构和部门）
     * @param omPartyRole
     * @param page
     * @return
     * @throws SQLException
     */
	@Override
	public List<OmPartyRole> queryOmPartyRoleNotList(OmPartyRole omPartyRole,
			Page page) throws SQLException {
		int count = this.operatorRoleDao.queryOmPartyRoleNotCount(omPartyRole);
		page.setCount(count);
		return this.operatorRoleDao.queryOmPartyRoleNotList(omPartyRole, page);
	}
	/**
	 * 保存角色人员关系
	 * @param hm
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean saveOperatorRole(HashMap hm) throws SQLException {
		String opFlag = (String)hm.get("opFlag");
		String strEmpIds = (String)hm.get("strEmpIds");
		if(StringUtils.isBlank(strEmpIds)){
			strEmpIds="";
		}
		String roleId = (String)hm.get("acRole.roleId");
		String roleType = (String)hm.get("acRole.roleType");
		String[] empIds = strEmpIds.split(",");
		AcOperatorRole acOperatorRole = new AcOperatorRole();
		System.out.println(strEmpIds);
		if("2".equals(opFlag)){
			for(int i=0; i<empIds.length; i++) {
				//原有的对应关系中没有的记录需要新增
				acOperatorRole.setAgency(roleType);
				acOperatorRole.setOperatorId(empIds[i]);
				acOperatorRole.setRoleId(roleId);
				Integer qar = this.roleDao.queryAcOperatorRole(acOperatorRole);
				if(qar<1){
					this.roleDao.insertAcOperatorRole(acOperatorRole);
				}
			}
			return true;
		}
		HashMap hashMap = queryAcOperatorRoleEmp(hm);
		String strOriginalEmpIds = (String)hashMap.get("empIds");
		String[] originalEmpIds = strOriginalEmpIds.split(",");
		System.out.println(strOriginalEmpIds);
		
		for(int i=0; i<empIds.length; i++) {
			//原有的对应关系中没有的记录需要新增
			if(strOriginalEmpIds.indexOf(empIds[i]) < 0) {
				acOperatorRole.setAgency(roleType);
				acOperatorRole.setOperatorId(empIds[i]);
				acOperatorRole.setRoleId(roleId);
				this.roleDao.insertAcOperatorRole(acOperatorRole);
			}
		}
		for(int i=0; i<originalEmpIds.length; i++) {
			//原有的记录中有但是新记录中没有的则要删除
			if(strEmpIds.indexOf(originalEmpIds[i]) < 0) {
				acOperatorRole.setAgency(roleType);
				acOperatorRole.setOperatorId(originalEmpIds[i]);
				acOperatorRole.setRoleId(roleId);
				this.roleDao.deleteAcOperatorRole(acOperatorRole);
			}
		}
		return true;
	}
	/**
	 * 根据角色查询人员（并将人员信息组合成字符串）
	 * @param acOperatorRole
	 * @return
	 * @throws SQLException
	 */
	@Override
	public HashMap queryAcOperatorRoleEmp(HashMap hm)
			throws SQLException {
		AcOperatorRole acOperatorRole = new AcOperatorRole();
		acOperatorRole.setRoleId((String)hm.get("acRole.roleId"));
		List<AcOperatorRole> acOperatorRoleList = this.operatorRoleDao.queryAcOperatorRoleEmp(acOperatorRole);
		if(acOperatorRoleList==null){
			return null;
		}
		String empIds = "";
		String empNames = "";
		AcOperatorRole acOperatorRole1 = null;
		int length = acOperatorRoleList.size();
		if(length > 0) {
			for(int i=0; i<length; i++) {
				acOperatorRole1 = acOperatorRoleList.get(i);
				empNames = ("".equals(empIds)) ? acOperatorRole1.getEmpName() : empNames + "," + acOperatorRole1.getEmpName();
				empIds = ("".equals(empIds)) ? acOperatorRole1.getOperatorId() : empIds + "," + acOperatorRole1.getOperatorId();
			}
		}
		hm.put("empIds", empIds);
		hm.put("empNames", empNames);
		return hm;
	}
	/**
	 * 新增人员角色关系
	 * @param acOperatorRole
	 * @return
	 * @throws SQLException
	 */
    @Override
	public boolean insertOperatorRole(AcOperatorRole acOperatorRole)
			throws SQLException {
		this.operatorRoleDao.insertAcOperatorRole(acOperatorRole);
		return true;
	}
	IOperatorRoleDao operatorRoleDao;
    private IPrivilegeDao privilegeDao;

	private IRoleDao roleDao;
	public IPrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}
	public void setPrivilegeDao(IPrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}
	public IRoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}
	/**
	 * 查询人员角色关系列表（分页查询）
	 * @param acOperatorRole
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<AcOperatorRole> queryAcOperatorRoleList(
			AcOperatorRole acOperatorRole, Page page) throws SQLException {
		int count = this.operatorRoleDao.queryAcOperatorRoleCount(acOperatorRole);
		
		page.setCount(count);
		page.setCount(false);
		List<AcOperatorRole> lar = this.operatorRoleDao.queryAcOperatorRoleList(acOperatorRole, page);
		page.setCount(true);
		return lar;
	}
	public IOperatorRoleDao getOperatorRoleDao() {
		return operatorRoleDao;
	}
	public void setOperatorRoleDao(IOperatorRoleDao operatorRoleDao) {
		this.operatorRoleDao = operatorRoleDao;
	}
	
    /**
     * 删除人员角色关系
     * @param acOperatorRole
     * @return
     * @throws SQLException
     */
	@Override
	public boolean deleteOperatorRole(HashMap hm)
			throws SQLException {
		String strOperatorIds = (String)hm.get("strOperatorIds");
		String strRoleIds = (String)hm.get("strRoleIds");
		String strAgencys = (String)hm.get("strAgencys");
		
		if(strOperatorIds != null && !"".equals(strOperatorIds)) {
			AcOperatorRole acOperatorRole = new AcOperatorRole();
			String[] operatorIds = strOperatorIds.split(",");
			String[] roleIds = strRoleIds.split(",");
			String[] agencys = strAgencys.split(",");
			for(int i=0; i<operatorIds.length; i++) {
				acOperatorRole.setOperatorId(operatorIds[i]);
				acOperatorRole.setRoleId(roleIds[i]);
				acOperatorRole.setAgency(agencys[i]);
				this.operatorRoleDao.deleteAcOperatorRole(acOperatorRole);
			}
		}
		return true;
	}

}
