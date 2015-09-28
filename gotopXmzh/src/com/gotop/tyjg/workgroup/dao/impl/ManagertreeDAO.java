package com.gotop.tyjg.workgroup.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.gotop.tyjg.common.model.Employee;
import com.gotop.tyjg.common.model.Group;
import com.gotop.tyjg.common.model.Organization;
import com.gotop.tyjg.common.model.Role;
import com.gotop.tyjg.workgroup.dao.IManagertreeDAO;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;

public class ManagertreeDAO extends SqlMapClientDao implements IManagertreeDAO {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(ManagertreeDAO.class);

	/**
	 * 查询行政树的机构节点
	 * @param hmp 查询条件
	 * @return 符合条件的根机构集合
	 * @throws Exception
	 */
	public List<Organization> selectAdminTreeNode(HashMap<String, String> hmp) throws Exception{
		return this.queryForList("Managertree_SqlMap.selectAdminTreeRoot", hmp);
	}
	/**
	 * 查询机构下的人员
	 * @param hmp 查询条件
	 * @return 符合条件的人员集合
	 * @throws Exception
	 */
	public List<Employee> selectAdminTreeEmpNode(HashMap<String, String> hmp) throws Exception{
		return this.queryForList("Managertree_SqlMap.selectEmp", hmp);
	}
	/**
	 * 查询当前用户可操作的群组
	 * @param groupids 可操作群组编号
	 * @return 可操作群组集合
	 * @throws Exception
	 */
	public List<Group> selectGroupTree(String groupids) throws Exception{
		if(StringUtils.isNotBlank(groupids)){
			HashMap<String,String[]> gmap = new HashMap<String,String[]>();
			gmap.put("groupids", groupids.split(","));
			return this.queryForList("Managertree_SqlMap.selectGroup", gmap);
		}else{
			return new ArrayList();
		}
	}
	/**
	 * 根据群组编号查询该组下的人员
	 * @param groupid 群组编号
	 * @return 该组下的人员集合
	 * @throws Exception
	 */
	public List<Employee> selectGroupEmp(String groupid) throws Exception{
		return this.queryForList("Managertree_SqlMap.selectGroupEmp", groupid);
	}
	/**
	 * 根据群组编号查询该组下的机构
	 * @param groupid 群组编号
	 * @return 该组下的机构集合
	 * @throws Exception
	 */
	public List<Organization> selectGroupOrg(String groupid) throws Exception{
		return this.queryForList("Managertree_SqlMap.selectGroupOrg", groupid);
	}
	/**
	 * 查询业务树信息
	 * @param topid 树编号
	 * @return 树信息
	 * @throws Exception
	 */
	public List selectBusessTreeInfo(String topid) throws Exception{
		return this.queryForList("Managertree_SqlMap.selectBusessTreeInfo", topid);
	}
	/**
	 * 获取业务树的开始ID
	 * @param hmp 条件
	 * @return 符合条件的集合
	 * @throws Exception
	 */
	public List selectStartId(HashMap<String, String> hmp) throws Exception{
		return this.queryForList("Managertree_SqlMap.selectStartId", hmp);
	}
	/**
	 * 查询业务树机构
	 * @param hmp 查询条件
	 * @return 机构集合
	 * @throws Exception
	 */
	public List<Organization> selectBusessTreeOrg(HashMap hmp)throws Exception{
		return this.queryForList("Managertree_SqlMap.selectBusessTreeOrg", hmp);
	}
	/**
	 * 根据机构属性查询业务角色
	 * @param hmp 机构属性、分页参数
	 * @return 业务角色集合
	 * @throws Exception
	 */
	public List<Role> selectBusessRole(HashMap hmpm,Page page)throws Exception{
		return queryForList("Managertree_SqlMap.selectBusessRole", hmpm,page);
	}
	/**
	 * 统计业务角色记录数
	 * @param hmp 机构属性
	 * @return 记录数
	 * @throws Exception
	 */
	public int countBusessRole(HashMap hmp)throws Exception{
		Object obj = this.queryForObject("Managertree_SqlMap.countBusessRole", hmp);
		if(obj == null){
			return 0;
		}
		return Integer.parseInt(String.valueOf(obj));
	}
	/**
	 * 查询auto信息
	 * @param roleid 角色编码
	 * @return auto值
	 * @throws Exception
	 */
	public String selectRoleAuto(String roleid) throws Exception{
		return (String)this.queryForObject("Managertree_SqlMap.selectAuto", roleid);
	}
}