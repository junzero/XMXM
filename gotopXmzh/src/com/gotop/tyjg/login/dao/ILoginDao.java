package com.gotop.tyjg.login.dao;

import java.util.HashMap;
import java.util.List;
/**
 * *******************************
 * <p>Title: 对登录用户信息的查询接口</p>
 * 
 * <p> Description:  对登录用户信息的查询的接口</p>
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
public interface ILoginDao {
	/**
	 * 根据用户中文名获取同名所在主机构
	 * @param userName 用户姓名
	 * @return
	 * @throws Exception
	 */
	public List selectByUserOrg(String userName) throws Exception;
	/**
	 * 验证用户登录密码
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	public boolean validatePassword(HashMap hmp) throws Exception;
	/**
	 * 查询用户基本信息
	 * @param userId 用户id
	 * @param orgId 机构id
	 * @return 用户信息
	 * @throws Exception
	 */
	public List selectByUserBaseInfo(String userId,String orgId) throws Exception;
	
	/**
	 * 查询用户所在的实体机构
	 * @param orgId 机构编号
	 * @return 机构信息
	 * @throws Exception
	 */
	public List selectCriteriaOrg(String orgId) throws Exception;
	/**
	 * 查询用户的映射机构信息（邮政人员）
	 * @param mappingOrgid 映射编号
	 * @return 映射机构信息
	 * @throws Exception
	 */
	public List selectMappingOrg(String mappingOrgid) throws Exception;
	/**
	 * 查询用户拥有的角色
	 * @param operatorId 操作员编号
	 * @return 角色信息列表
	 * @throws Exception
	 */
	public List selectByUserRole(String operatorId) throws Exception;
	/**
	 * 根据角色获取菜单根
	 * @param roles 角色数组
	 * @return 菜单组信息
	 * @throws Exception
	 */
	public List selectMenuRoot(String[] role) throws Exception;
	/**
	 * 根据角色、菜单组ID获取子菜单
	 * @param roles 角色数组
	 * @param groupid 菜单组ID
	 * @return 子菜单信息
	 * @throws Exception
	 */
	public List selectMenuChild(String[] role,String groupid,String groupLevel) throws Exception;
	/**
	 * 查询用户可登录机构
	 * @param empid 人员编号
	 * @return 可登录机构集合
	 * @throws Exception
	 */
	public List selectLoginOrg(String empid)throws Exception;
	/**
	 * 记录登录日志
	 * @param hmp 登录日志信息
	 * @throws Exception
	 */
	public void insertLoginLog(HashMap<String, String> hmp) throws Exception;
	/**
	 * 查询登录用户拥有的群组信息
	 * @param hmp 登录人员编号
	 * @return 群组列表
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectEmpGroup(HashMap<String, String> hmp)throws Exception;
	/**
	 * 查询登录用户所属岗位
	 * @param empId	用户编号
	 * @return 岗位列表
	 * @throws Exception
	 */
	public List selectPositionByEmp(String empId)throws Exception;
}
