package com.gotop.tyjg.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gotop.vo.system.MUOUserSession;
/**
 * *******************************
 * <p>Title: 对登录用户信息的查询逻辑处理接口</p>
 * 
 * <p> Description:   对登录用户信息的查询逻辑处理接口</p>
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
public interface ILoginService {
	/**
	 * 根据用户中文名查询机构信息
	 * @param userName 用户中文名称
	 * @return 包含机构信息的xml字符串
	 * @throws Exception
	 */
	public String selectByLoginUserOrg(String userName) throws Exception;
	/**
	 * 校验用户登录密码是否正确
	 * @param hmp 包含的用户信息
	 * @return true:正确 false:错误
	 * @throws Exception
	 */
	public boolean vaildataPassword(HashMap hmp) throws Exception;
	/**
	 * 根据用户id，机构id查询并设置登录用户session信息
	 * @param userId 用户ID
	 * @param orgId 机构ID
	 * @return 包含用户基本信息，机构信息，角色信息的对象
	 * @throws Exception
	 */
	public MUOUserSession selectByUserInfo(String userId,String orgId) throws Exception;
	/**
	 * 根据角色获取菜单根
	 * @param roles 角色数组
	 * @return 菜单组信息
	 * @throws Exception
	 */
	public List selectMenuRoot(String[] roles) throws Exception;
	/**
	 * 根据角色、菜单组ID获取子菜单
	 * @param roles 角色数组
	 * @param groupid 菜单组ID
	 * @param groupLevel 菜单组ID
	 * @return 子菜单信息
	 * @throws Exception
	 */
	public List selectMenuChild(String[] roles,String groupid,String groupLevel)throws Exception;
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
}
