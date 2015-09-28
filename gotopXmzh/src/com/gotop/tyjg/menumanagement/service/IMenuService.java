package com.gotop.tyjg.menumanagement.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.menumanagement.model.AcApplication;
import com.gotop.tyjg.menumanagement.model.AcFunction;
import com.gotop.tyjg.menumanagement.model.AcFunctionGroup;
import com.primeton.utils.Page;
/**
 * *******************************
 * <p>Title: 应用菜单服务</p>
 * 
 * <p> Description:  应用菜单服务</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date 2012-3-30
 * 
 * @version 1.0
 * 
 * HISTORY 2012-3-30 xuxh 创建文件
 * 
 * *******************************
 */
public interface IMenuService extends Serializable {
	/**
	 * 获取应用菜单系统的根
	 * @return 应用菜单根集合
	 * @throws Exception
	 */
	public List<AcApplication> queryAppRoot() throws Exception;
	/**
	 * 查询菜单组信息
	 * @param acApplication 应用系统信息
	 * @return 菜单组集合
	 * @throws Exception
	 */
	public List<AcFunctionGroup> queryMenuGroup(AcApplication acApplication) throws Exception;
	/**
	 * 获取子功能组信息
	 * @param acFunctionGroup 菜单父组信息
	 * @return 菜单组集合
	 * @throws Exception
	 */
	public List<AcFunctionGroup> queryMenuChildGroup(AcFunctionGroup acFunctionGroup) throws Exception;
	/**
	 * 获取子功能组信息
	 * @param acFunctionGroup 菜单父组信息
	 * @return 菜单集合
	 * @throws Exception
	 */
	public List<AcFunction> queryMenu(AcFunctionGroup acFunctionGroup) throws Exception;
	/**
	 * 查询应用系统列表
	 * @param acApplication 查询条件
	 * @param page 分页信息
	 * @return 应用系统列表
	 * @throws Exception
	 */
	public List<AcApplication> queryAppList(AcApplication acApplication,Page page) throws Exception;
	/**
	 * 新增应用系统
	 * @param acApplication 应用系统信息
	 * @return	true:成功 false：失败
	 * @throws Exception
	 */
	public boolean insertApp(AcApplication acApplication)throws Exception;
	/**
	 * 验证新增的应用代码是否存在
	 * @param appCode 应用代码
	 * @param appId 应用编号
	 * @return 数量
	 * @throws Exception
	 */
	public long checkAppCode(String appCode,String appId)throws Exception;
	/**
	 * 删除应用系统
	 * @param appids 待删除应用系统编号使用逗号分割的字符串
	 * @return true：删除成功 false:删除失败
	 * @throws Exception
	 */
	public boolean deleteApp(String appids) throws Exception;
	/**
	 * 更新应用系统信息
	 * @param acApplication 应用系统信息
	 * @return true:修改成功 false:修改失败
	 * @throws Exception
	 */
	public boolean updateApp(AcApplication acApplication) throws Exception;
	/**
	 * 查询单个应用系统信息
	 * @param acApplication 应用系统信息
	 * @return 应用系统信息
	 * @throws Exception
	 */
	public AcApplication querySingleApp(AcApplication acApplication) throws Exception;
	/**
	 * 新增应用功能组
	 * @param acFunctionGroup 待新增功能组信息
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean insertFuncGroup(AcFunctionGroup acFunctionGroup) throws Exception;
	/**
	 * 根据应用编号查询该应用下的工作组列表
	 * @param appid 应用系统编号
	 * @param page 分页信息
	 * @return 应用系统下工作组列表
	 * @throws Exception
	 */
	public List<AcFunctionGroup> selectByAppChildGroupList(String appid,Page page) throws Exception;
	/**
	 * 更新应用系统工作组的显示顺序
	 * @param groupids 工作组编号
	 * @param orderValues 新排序的值
	 * @return true：更新成功 false:更新失败
	 * @throws Exception
	 */
	public boolean updateAppGroupDisplayOrder(String groupids,String orderValues) throws Exception;
	/**
	 * 修改工作组名称
	 * @param functionGroup 待修改的工作组信息
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean updateGroupName(AcFunctionGroup functionGroup)throws Exception;
	/**
	 * 批量删除工作组
	 * @param groupids 待删除工作组编号数组
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean deleteBeathGroup(String[] groupids) throws Exception;
	/**
	 * 查询工作组信息
	 * @param functionGroup 待查询工作组信息
	 * @return 工作组信息
	 * @throws Exception
	 */
	public AcFunctionGroup selectByGroupidInfo(AcFunctionGroup functionGroup) throws Exception;
	/**
	 * 更新工作组信息
	 * @param functionGroup 待更新的工作组信息
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean updateGrupInfo(AcFunctionGroup functionGroup)throws Exception;
	/**
	 * 查询某个组下子组列表
	 * @param acFunctionGroup 父功能组信息
	 * @param page 分页信息
	 * @return 子组列表
	 * @throws Exception
	 */
	public List<AcFunctionGroup> selectGroupChildGroupList(AcFunctionGroup acFunctionGroup,Page page) throws Exception;
	/**
	 * 查询某个功能组下的功能菜单列表
	 * @param acFunction 待查功能信息
	 * @param page 分页信息
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public List<AcFunction> selectGroupFunMenu(AcFunction acFunction,Page page)throws Exception;
	/**
	 * 查询单个功能菜单信息
	 * @param acFunction 包含待查询的菜单编号
	 * @return 菜单信息
	 * @throws Exception
	 */
	public AcFunction selectSingleFuncMenuInfo(AcFunction acFunction)throws Exception;
	/**
	 * 新增功能菜单
	 * @param function 待新增的菜单信息
	 * @return 新增的条数
	 * @throws Exception
	 */
	public int insertMenu(AcFunction function) throws Exception;
	/**
	 * 删除菜单
	 * @param acFunction 待删除菜单信息
	 * @return 删除条数
	 * @throws Exception
	 */
	public int deleteMenu(AcFunction acFunction)throws Exception;
	/**
	 * 更新菜单的显示顺序
	 * @param funccodes 菜单编码
	 * @param orderValues 排序值
	 * @return 更新条数
	 * @throws Exception
	 */
	public int updateMenuDisplayOrder(String[] funccodes,String[] orderValues) throws Exception;
	/**
	 * 更新菜单信息
	 * @param acFunction 待更新的菜单信息
	 * @return 更新条数
	 * @throws Exception
	 */
	public int updateMenu(AcFunction acFunction)throws Exception;
	/**
	 * 验证新增或修改的菜单编码是否存在
	 * @param acFunction 待验证的菜单编码信息
	 * @return 数量
	 * @throws Exception
	 */
	public int checkFuncCode(AcFunction acFunction)throws Exception;
	/**
	 * 树几节点移动
	 * @param fromMap 移动起始节点信息
	 * @param toMap 移动终点信息
	 * @param type 移动类型
	 * @throws Exception
	 */
	public void updateMoveTreeNode(HashMap<String, String> fromMap,HashMap<String, String> toMap,String type) throws Exception;
}
