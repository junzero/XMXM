package com.gotop.tyjg.roleprivilege.dao.imp;

import java.sql.SQLException;
import java.util.List;

import com.gotop.tyjg.roleprivilege.dao.IRoleDao;
import com.gotop.tyjg.roleprivilege.model.AcOperatorRole;
import com.gotop.tyjg.roleprivilege.model.AcRole;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;

public class RoleDao extends SqlMapClientDao implements IRoleDao{
    
	/**
	 * 删除角色与实体字段的关系
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean deleteAcEntityFieldRole(AcRole acRole) throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_acentityfieldrole", acRole);
		return true;
	}
	
	/**
	 * 删除角色与实体关系
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean deleteAcEntityRole(AcRole acRole) throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_acentityrole", acRole);
		return false;
	}

	/**
	 * 删除角色与功能的关系
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean deleteAcFunc(AcRole acRole) throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_acfunc", acRole);
		return false;
	}
	public Integer queryAcOperatorRole(AcOperatorRole acOperatorRole) throws SQLException {
		Integer qfo = (Integer)this.queryForObject("ROLEPRIVILEGE.query_acoperatorrole", acOperatorRole);
		return qfo;
	}
	/**
	 * 删除人员角色关系
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean deleteAcOperatorRole1(AcRole acRole) throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_acoperatorrole1", acRole);
		return false;
	}

	/**
	 * 删除角色1
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean deleteAcRole1(AcRole acRole) throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_acrole1", acRole);
		return false;
	}

	/**
	 * 删除角色具有的数据权限
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean deleteAcRoleDatapriv(AcRole acRole) throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_acroledatapriv", acRole);
		return false;
	}

	/**
	 * 删除角色与组织对象的关系 delete_acrolefunc
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean deleteAcRoleFunc(AcRole acRole) throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_acrolefunc", acRole);
		return false;
	}

	/**
	 * 删除角色组织对应关系（联级授权）
	 * @param acRole
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean deleteAcRoleFunclock(AcRole acRole) throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_acrolefunclock", acRole);
		return false;
	}

    /**
     * 查询角色列表用于校验是否有该条记录（非分页查询）
     * @param acRole 查询条件角色对象
     * @return 
     * @throws SQLException
     */
	@Override
	public List<AcRole> queryRoleList(AcRole acRole) throws SQLException {
		return this.queryForList("ROLEPRIVILEGE.query_acrole_list1", acRole);
	}

    /**
     * 删除角色表
     * @param acRole
     * @return
     * @throws SQLException
     */
	@Override
	public boolean deleteAcRole(AcRole acRole) throws SQLException {
		this.delete("ROLEPRIVILEGE.delete_acrole", acRole);
		return true;
	}

    /**
     * 新增角色表
     * @param acRole
     * @return
     * @throws SQLException
     */
	@Override
	public boolean insertAcRole(AcRole acRole) throws SQLException {
		this.insert("ROLEPRIVILEGE.insert_acrole", acRole);
		return true;
	}

    /**
     * 查询角色记录的总记录数
     * @param acRole
     * @return
     * @throws SQLException
     */
	@Override
	public int queryRoleCount(AcRole acRole) throws SQLException {
		int result = (Integer)this.queryForObject("ROLEPRIVILEGE.query_acrole_count", acRole);
		return result;
	}

    /**
     * 修改角色表
     * @param acRole
     * @return
     * @throws SQLException
     */
	@Override
	public boolean updateAcRole(AcRole acRole) throws SQLException {
		this.update("ROLEPRIVILEGE.update_acrole", acRole);
		return true;
	}

    /**
     * 查询角色列表（分页查询）
     * @param acRole 查询条件角色对象
     * @param page
     * @return
     * @throws SQLException
     */
	@Override
	public List<AcRole> queryRoleList(AcRole acRole, Page page) throws SQLException {
		List<AcRole> acRoleList = this.queryForList("ROLEPRIVILEGE.query_acrole_list", acRole, page.getBegin(), page.getLength());
		return acRoleList;
	}
    
	/**
	 * 查询操作人员的权限类型 （通过数组中的数据比较得出类型）
	 * @param operateId  操作元id或empid
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<String> queryRoleTypeByOperid(String operateId) throws Exception {
		List<String> roleTypeList = this.queryForList("ROLEPRIVILEGE.query_roletype_byoperid", operateId);
		return roleTypeList;
	}
	
    /**
     * 新增角色操作员关系记录（新增该条角色记录的人员）
     * @param acOperatorRole 操作员角色表
     * @return
     * @throws SQLException
     */
	@Override
    public boolean insertAcOperatorRole(AcOperatorRole acOperatorRole) throws SQLException {
		this.insert("ROLEPRIVILEGE.insert_acoperatorrole", acOperatorRole);
		return true;
	}

	/**
	 * 删除角色操作员关系记录
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
}
