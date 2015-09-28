package com.gotop.tyjg.login.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.gotop.util.dataSource.SqlMapClientDao;

import com.gotop.tyjg.login.dao.ILoginDao;
/**
 * *******************************
 * <p>Title: 对登录用户信息的查询</p>
 * 
 * <p> Description:  对登录用户信息的查询</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date 2012-3-21
 * 
 * @version 1.0
 * 
 * HISTORY 2012-3-21 xuxh 创建文件
 * 
 * *******************************
 */
public class LoginDao extends SqlMapClientDao implements ILoginDao {
	/**
	 * 查询用户基本信息
	 * @param userId 用户id
	 * @param orgId 机构id
	 * @return 用户信息
	 * @throws Exception
	 */
	@Override
	public List selectByUserBaseInfo(String userId, String orgId)
			throws Exception {
		HashMap<String, String> hmp = new HashMap<String, String>(2);
		hmp.put("userid", userId);
		hmp.put("orgid", orgId);
		return this.queryForList("LOGIN.select_emp_base_info", hmp);
	}
	/**
	 * 根据用户中文名获取同名所在主机构
	 * @param userName 用户姓名
	 * @return
	 * @throws Exception
	 */
	@Override
	public List selectByUserOrg(String userName) throws Exception {
		HashMap<String, String> hmp = new HashMap<String, String>(1);
		hmp.put("username",userName);
		return this.queryForList("LOGIN.queryUserOrg", hmp);
	}
	/**
	 * 查询用户拥有的角色
	 * @param operatorId 操作员编号
	 * @return 角色信息列表
	 * @throws Exception
	 */
	@Override
	public List selectByUserRole(String operatorId) throws Exception {
		if("1".equals(operatorId)){
			return this.queryForList("LOGIN.select_emp_sysadmin_role_list",null);
		}else{
			HashMap<String, String> hmp = new HashMap<String, String>(1);
			hmp.put("operatorid", operatorId);
			return this.queryForList("LOGIN.select_emp_all_role_list",hmp);
		}
	}
	/**
	 * 查询用户所在的实体机构
	 * @param orgId 机构编号
	 * @return 机构信息
	 * @throws Exception
	 */
	@Override
	public List selectCriteriaOrg(String orgId) throws Exception {
		HashMap<String, String> hmp = new HashMap<String, String>(1);
		hmp.put("orgid",orgId);
		return this.queryForList("LOGIN.select_emp_org_info", hmp);
	}
	/**
	 * 查询用户的映射机构信息（邮政人员）
	 * @param mappingOrgid 映射编号
	 * @return 映射机构信息
	 * @throws Exception
	 */
	@Override
	public List selectMappingOrg(String mappingOrgid) throws Exception {
		HashMap<String, String> hmp = new HashMap<String, String>(1);
		hmp.put("ysjgbh", mappingOrgid);
		return this.queryForList("LOGIN.select_emp_ysjg_org_info", hmp);
	}
	/**
	 * 验证用户登录密码
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean validatePassword(HashMap hmp) throws Exception {
		int count = Integer.parseInt(String.valueOf(this.queryForObject("LOGIN.validataPassword", hmp)));
		if(count > 0){
			return true;
		}
		return false;
	}
	/**
	 * 根据角色获取菜单根
	 * @param roles 角色数组
	 * @return 菜单组信息
	 * @throws Exception
	 */
	public List selectMenuRoot(String[] role) throws Exception{
		HashMap<String, String[]> hmp = new HashMap<String, String[]>(1);
		if(role!=null && role.length>0){
			hmp.put("roles", role);
			List result = this.queryForList("LOGIN.select_getRolefuncTopid", hmp);
			return result;
		}else{
			return new ArrayList();
		}
	}
	/**
	 * 根据角色、菜单组ID获取子菜单
	 * @param roles 角色数组
	 * @param groupid 菜单组ID
	 * @return 子菜单信息
	 * @throws Exception
	 */
	public List selectMenuChild(String[] role,String groupid,String groupLevel) throws Exception{
		HashMap<String, Object> hmp = new HashMap<String, Object>(3);
		if(role!=null && role.length>0){
			hmp.put("roles", role);
			hmp.put("parentgroup", groupid);
			hmp.put("grouplevel", groupLevel);
			List result = this.queryForList("LOGIN.select_rolefuncByGroupid", hmp);
			return result;
		}else{
			return new ArrayList();
		}
	}
	/**
	 * 查询用户可登录机构
	 * @param empid 人员编号
	 * @return 可登录机构集合
	 * @throws Exception
	 */
	public List selectLoginOrg(String empid)throws Exception{
		HashMap<String, String> hmp = new HashMap<String, String>(1);
		hmp.put("empid", empid);
		return this.queryForList("LOGIN.loginOrg", hmp);
	}
	/**
	 * 记录登录日志
	 * @param hmp 登录日志信息
	 * @throws Exception
	 */
	public void insertLoginLog(HashMap<String, String> hmp) throws Exception{
		this.insert("LOGIN.insertLoginLog", hmp);
	}
	/**
	 * 查询登录用户拥有的群组信息
	 * @param hmp 登录人员编号
	 * @return 群组列表
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectEmpGroup(HashMap<String, String> hmp)throws Exception{
		return this.queryForList("LOGIN.queryLoginGroup", hmp);
	}
	@Override
	public List selectPositionByEmp(String empId) throws Exception {
		HashMap<String, String> hmp = new HashMap<String, String>(1);
		hmp.put("empid", empId);
		return this.queryForList("LOGIN.queryPositionByEmp", hmp);
	}
}
