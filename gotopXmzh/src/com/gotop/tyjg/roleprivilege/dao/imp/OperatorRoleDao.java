package com.gotop.tyjg.roleprivilege.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.roleprivilege.dao.IOperatorRoleDao;
import com.gotop.tyjg.roleprivilege.model.AcOperatorRole;
import com.gotop.tyjg.roleprivilege.model.OmPartyRole;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;

public class OperatorRoleDao  extends SqlMapClientDao implements IOperatorRoleDao{
    
    @Override
	public int queryOmPartyRoleCount(OmPartyRole omPartyRole)
			throws SQLException {
		int count = (Integer)this.queryForObject("ROLEPRIVILEGE.query_ompartyrole_count", omPartyRole);
		return count;
	}

	@Override
	public int queryOmPartyRoleNotCount(OmPartyRole omPartyRole)
			throws SQLException {
		int count = (Integer)this.queryForObject("ROLEPRIVILEGE.query_ompartyrolenot_count", omPartyRole);
		return count;
	}

	/**
     * 删除角色机构关系
     * @param hashMap
     * @return
     * @throws SQLException
     */
    @Override
	public boolean deleteOmPartyRole(OmPartyRole omPartyRole) throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_ompartyrole", omPartyRole);
		return true;
	}

    /**
     * 新增角色机构关系
     * @param hashMap
     * @return
     * @throws SQLException
     */
	@Override
	public boolean insertOmPartyRole(OmPartyRole omPartyRole) throws SQLException {
		this.insert("ROLEPRIVILEGE.insert_ompartyrole", omPartyRole);
		return true;
	}

	/**
     * 删除角色功能关系
     * @param acRoleFunc
     * @return
     * @throws SQLException
     */
    @Override
	public List<OmPartyRole> queryOmPartyRoleList(OmPartyRole omPartyRole,
			Page page) throws SQLException {
		return this.queryForList("ROLEPRIVILEGE.query_ompartyrole_list", omPartyRole, page.getBegin(), page.getLength());
	}
    
    /**
     * 删除角色功能组关系
     * @param acRoleFuncLock
     * @return
     * @throws SQLException
     */
	@Override
	public List<OmPartyRole> queryOmPartyRoleNotList(OmPartyRole omPartyRole,
			Page page) throws SQLException {
		return this.queryForList("ROLEPRIVILEGE.query_ompartyrolenot_list", omPartyRole, page.getBegin(), page.getLength());
	}

	/**
     * 查询一个角色下的人员
     * @param acOperatorRole
     * @return
     * @throws SQLException
     */
    @Override
	public List<AcOperatorRole> queryAcOperatorRoleEmp(
			AcOperatorRole acOperatorRole) throws SQLException {
		Integer qfo = (Integer)this.queryForObject("ROLEPRIVILEGE.query_acoperatorrole_count", acOperatorRole);
		if(qfo<500){
			return this.queryForList("ROLEPRIVILEGE.query_acoperatorrole_emp", acOperatorRole);
		}else{
			return null;
		}
	}

	/**
     * 新增人员角色关系
     * @param acOperatorRole
     * @return
     * @throws SQLException
     */
    @Override
	public boolean insertAcOperatorRole(AcOperatorRole acOperatorRole)
			throws SQLException {
		this.insert("ROLEPRIVILEGE.insert_acoperatorrole", acOperatorRole);
		return true;
	}

	/**
     * 删除人员角色关系
     * @param acOperatorRole
     * @return
     * @throws SQLException
     */
    @Override
	public boolean deleteAcOperatorRole(AcOperatorRole acOperatorRole)
			throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_acoperatorrole", acOperatorRole);
		return true;
	}

	/**
     * 查询人员角色关系数量
     * @param acOperatorRole
     * @return
     * @throws SQLException
     */
	@Override
	public int queryAcOperatorRoleCount(AcOperatorRole acOperatorRole)
			throws SQLException {
		
		return (Integer)this.queryForObject("ROLEPRIVILEGE.query_acoperatorrole_count", acOperatorRole);
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
//		return this.queryForList("ROLEPRIVILEGE.query_acoperatorrole_list", acOperatorRole, page.getBegin(), page.getLength());
		List<HashMap> empids = this.queryForList("ROLEPRIVILEGE.query_acoperatorrole_list_br", acOperatorRole, page);
		if(empids.size()>0){
			String empidStr = "";
			for(int i=0;i<empids.size();i++){
				HashMap empid = empids.get(i);
				Object operatorId = empid.get("operatorId");
				empidStr += operatorId;
				if(i<empids.size()-1){
					empidStr += ",";
				}
			}
			String[] empIds = empidStr.split(",");
			HashMap<String,String[]> param = new HashMap<String,String[]>();
			param.put("empIds", empIds);
			List rqal = this.queryForList("ROLEPRIVILEGE.query_acoperatorrole_list_ls", param);
			return rqal;
		}else{
			return new ArrayList();
		}
	}

}
