package com.gotop.tyjg.menumanagement.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gotop.util.dataSource.SqlMapClientDao;

import com.gotop.tyjg.menumanagement.dao.IMenuDao;
import com.gotop.tyjg.menumanagement.model.AcApplication;
import com.gotop.tyjg.menumanagement.model.AcFunction;
import com.gotop.tyjg.menumanagement.model.AcFunctionGroup;
import com.ibatis.sqlmap.client.SqlMapClient;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
/**
 * *******************************
 * <p>Title: 应用菜单管理数据操作</p>
 * 
 * <p> Description:  应用菜单管理数据操作</p>
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
public class MenuDao extends SqlMapClientDao implements IMenuDao {
	protected static Logger log = Logger.getLogger(MenuDao.class);
	/**
	 * 查询需要同步的应用系统编号
	 * @return 需要同步的应用系统编号字符串
	 * @throws Exception
	 */
	public String selectIsSynApp() throws Exception{
		return String.valueOf(this.queryForObject("MENUMANAGEMENT.queryIssynApp"));
	}
	/**
	 * 查询应用菜单的根
	 * @param appIds 应用系统编号字符串用逗号隔开
	 * @return 应用系统信息的集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AcApplication> selectAppRoot(String[] appIds) throws Exception{
		HashMap<String, Object> hmp = new HashMap<String, Object>(1);
		if(appIds!=null && appIds.length>0){
			hmp.put("appid", appIds);
			List result = this.queryForList("MENUMANAGEMENT.queryAppRoot", hmp);
			return result;
		}else{
			return new ArrayList();
		}
	}
	/**
	 * 根据应用系统编号查询菜单组
	 * @param appid 应用系统编号
	 * @return 菜单组集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AcFunctionGroup> selectMenuGroup(String appid) throws Exception{
		HashMap<String, Object> hmp = new HashMap<String, Object>(1);
		hmp.put("appid", appid);
		return this.queryForList("MENUMANAGEMENT.queryMenuGroup", hmp);
	}
	/**
	 * 查询子功能组信息
	 * @param groupid 功能组编号
	 * @param groupLevel 功能组级别
	 * @return	菜单组集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AcFunctionGroup> selectMenuChildGroup(String groupid,String groupLevel) throws Exception{
		HashMap<String, Object> hmp = new HashMap<String, Object>(2);
		hmp.put("groupid", groupid);
		hmp.put("grouplevel", groupLevel);
		return this.queryForList("MENUMANAGEMENT.queryMenuChildGroup", hmp);
	}
	/**
	 * 查询子菜单信息
	 * @param groupid 功能组编号
	 * @return	菜单集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AcFunction> selectMenu(String groupid) throws Exception{
		HashMap<String, Object> hmp = new HashMap<String, Object>(1);
		hmp.put("groupid", groupid);
		return this.queryForList("MENUMANAGEMENT.queryMenu", hmp);
	}
	/**
	 * 查询应用系统
	 * @param paramMap 查询条件
	 * @return 应用系统列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AcApplication> selectSystemApp(HashMap paramMap) throws Exception{
		return this.queryForList("MENUMANAGEMENT.querySystemApp", paramMap);
	}
	/**
	 * 新增应用系统
	 * @param acApplication 应用系统信息
	 * @return	true:成功 false：失败
	 * @throws Exception
	 */
	public boolean insertApp(AcApplication acApplication)throws Exception{
		this.insert("MENUMANAGEMENT.addApp", acApplication);
		return true;
	}
	/**
	 * 验证新增的应用代码是否存在
	 * @param appCode 应用代码
	 * @param appId 应用编号
	 * @return 数量
	 * @throws Exception
	 */
	public long checkAppCode(String appCode,String appId)throws Exception{
		HashMap<String, Object> hmp = new HashMap<String, Object>(5);
		hmp.put("appcode", appCode);
		if(appId != null){
			hmp.put("appid", appId);
		}
		Object obj = this.queryForObject("MENUMANAGEMENT.checkAppCode", hmp);
		String count = String.valueOf(obj);
		return Long.parseLong(count);
	}
	/**
	 * 删除应用系统
	 * @param appids 待删除的应用系统编号数组
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean deleteApp(String[] appids) throws Exception{
		boolean falg = false;
		if(appids == null){
			return falg;
		}
		this.startBatch();
		int len = appids.length;
		HashMap<String, Object> hmp = new HashMap<String, Object>(10);
		for(int i = 0; i < len; i++){
			hmp.clear();
			hmp.put("appid", appids[i]);
			this.delete("MENUMANAGEMENT.delApp", hmp);
			this.delete("MENUMANAGEMENT.delMenuRole", hmp);
			this.delete("MENUMANAGEMENT.delAppFuncMenu", hmp);
			this.delete("MENUMANAGEMENT.delAppGroup", hmp);
		}
		this.executeBatch();
		falg = true;
		return falg;
	}
	/**
	 * 更新应用系统信息
	 * @param acApplication 应用系统信息
	 * @return 更新记录数
	 * @throws Exception
	 */
	public int updateApp(AcApplication acApplication) throws Exception{
		return this.update("MENUMANAGEMENT.updateApp", acApplication);
	}
	/**
	 * 根据应用id查询应用系统信息
	 * @param appid 应用系统编号
	 * @return 应用系统信息
	 * @throws Exception
	 */
	public AcApplication selectByAppid(String appid) throws Exception{
		HashMap<String, Object> hmp = new HashMap<String, Object>(1);
		hmp.put("appid", appid);
		return (AcApplication)this.queryForObject("MENUMANAGEMENT.querySignleApp", hmp);
	}
	/**
	 * 新增菜单功能组
	 * @param acFunctionGroup 待新增的功能组信息
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean insertFuncGroup(AcFunctionGroup acFunctionGroup) throws Exception{
		this.insert("MENUMANAGEMENT.addFuncGroup", acFunctionGroup);
		return true;
	}
	/**
	 * 根据应用编号查询该应用下的工作组列表
	 * @param paramMap 应用系统编号以及分页信息
	 * @return 应用系统下工作组列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AcFunctionGroup> selectByAppChildGroupList(HashMap<String, Object> paramMap) throws Exception{
		return this.queryForList("MENUMANAGEMENT.selectAppChildGroup", paramMap);
	}
	/**
	 * 更新应用系统的工作组显示顺序
	 * @param groupids 包含工作组编号的数组
	 * @param orderValues 工作组显示顺序的数组
	 * @return 更新的条数
	 * @throws Exception
	 */
	public int updateAppGroupDisplayOrder(String[] groupids,String[] orderValues) throws Exception{
		this.startBatch();
		int groupSize = groupids.length;
		int orderValuesSize = orderValues.length;
		int count = 0;
		HashMap<String, Object> hmp = new HashMap<String, Object>(10);
		for(int i = 0; i < groupSize; i++){
			hmp.clear();
			if(i >= orderValuesSize){//说明只有部分排序字段有输入值或全部为输入排序值
				hmp.put("displayOrder", "");
			}else{
				hmp.put("displayOrder", orderValues[i]);
			}
			hmp.put("groupid", groupids[i]);
			count += this.update("MENUMANAGEMENT.updateAppGroupDisplayOrder", hmp);
		}
		this.executeBatch();
		count++;
		return count;
	}
	/**
	 * 修改工作组名称
	 * @param functionGroup 待修改的工作组信息
	 * @return 更新条数
	 * @throws Exception
	 */
	public int updateGroupName(AcFunctionGroup functionGroup)throws Exception{
		return this.update("MENUMANAGEMENT.updateGroupName", functionGroup);
	}
	/**
	 * 批量删除工作组,同时删除子组、菜单、授权给角色的菜单
	 * @param groupids 待删除工作组编号数组
	 * @return 删除条数
	 * @throws Exception
	 */
	public int deleteBeathGroup(String[] groupids) throws Exception{
		this.startBatch();
		int count = 0;
		HashMap<String, Object> hmp = new HashMap<String, Object>(10);
		for(int i = 0,len = groupids.length; i < len; i++){
			hmp.clear();
			hmp.put("groupid",groupids[i]);
			count += this.delete("MENUMANAGEMENT.deleteGroup", hmp);
			count += this.delete("MENUMANAGEMENT.deleteFunc", hmp);
			count += this.delete("MENUMANAGEMENT.deleteRoleFunc", hmp);
			count++;
		}
		return count;
	}
	/**
	 * 查询工作组信息
	 * @param functionGroup 待查询工作组信息
	 * @return 工作组信息
	 * @throws Exception
	 */
	public AcFunctionGroup selectByGroupidInfo(AcFunctionGroup functionGroup) throws Exception{
		return (AcFunctionGroup)this.queryForObject("MENUMANAGEMENT.queryGroupinfo", functionGroup);
	}
	/**
	 * 更新工作组信息
	 * @param functionGroup 待更新的工作组信息
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean updateGrupInfo(AcFunctionGroup functionGroup)throws Exception{
		this.update("MENUMANAGEMENT.updateGroupInfo", functionGroup);
		return true;
	}
	/**
	 * 查询功能组下的子组列表
	 * @param paramMap 分页参数与应用编号、父工作组编号、组级别
	 * @return 某功能组下子功能组列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AcFunctionGroup> selectFuncChildGroupList(HashMap<String, Object> paramMap) throws Exception{
		return this.queryForList("MENUMANAGEMENT.selectGroupChildGroup", paramMap);
	}

	/**
	 * 查询工作组下的功能菜单列表
	 * @param hmp 待查询功能信息,分页信息
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AcFunction> selectGroupFuncMenu(HashMap<String, Object> hmp)throws Exception{
		return this.queryForList("MENUMANAGEMENT.queryGroupFunc", hmp);
	}
	/**
	 * 统计列表记录数
	 * @param hmp 查询参数、分页信息参数
	 * @param namingSql 命名SQL的名称
	 * @return 总记录数
	 * @throws Exception
	 */
	public int countRecord(HashMap<String, Object> hmp,String namingSql) throws Exception{
		Object obj = this.queryForObject(namingSql, hmp);
		return Integer.parseInt(String.valueOf(obj));
	}
	/**
	 * 查询单个功能菜单信息
	 * @param acFunction 包含待查询的菜单编号
	 * @return 菜单信息
	 * @throws Exception
	 */
	public AcFunction selectSingleFuncMenuInfo(AcFunction acFunction)throws Exception{
		return (AcFunction)this.queryForObject("MENUMANAGEMENT.querySingleFuncMenu", acFunction);
	}
	/**
	 * 新增功能菜单
	 * @param function 待新增的菜单信息
	 * @return 新增的条数
	 * @throws Exception
	 */
	public int insertMenu(AcFunction function) throws Exception{
		this.insert("MENUMANAGEMENT.addMenu", function);
		int count = 0;
		count++;
		return count;
	}
	/**
	 * 删除菜单
	 * @param acFunction 待删除菜单信息
	 * @return 删除条数
	 * @throws Exception
	 */
	public int deleteMenu(AcFunction acFunction)throws Exception{
		this.startBatch();
		this.delete("MENUMANAGEMENT.deleteFun", acFunction);
		this.delete("MENUMANAGEMENT.deleteRoleFun", acFunction);
		int count = this.executeBatchNum();
		count++;
		return count;
	}
	/**
	 * 更新菜单的显示顺序
	 * @param funccodes 菜单编码
	 * @param orderValues 排序值
	 * @return 更新条数
	 * @throws Exception
	 */
	public int updateMenuDisplayOrder(String[] funccodes,String[] orderValues) throws Exception{
		this.startBatch();
		int groupSize = funccodes.length;
		int orderValuesSize = orderValues.length;
		int count = 0;
		HashMap<String, Object> hmp = new HashMap<String, Object>(10);
		for(int i = 0; i < groupSize; i++){
			hmp.clear();
			if(i >= orderValuesSize){//说明只有部分排序字段有输入值或全部为输入排序值
				hmp.put("displayOrder", "");
			}else{
				hmp.put("displayOrder", orderValues[i]);
			}
			hmp.put("funcCode", funccodes[i]);
			count += this.update("MENUMANAGEMENT.updateMenuDisplayOrder", hmp);
		}
		this.executeBatch();
		count++;
		return count;
	}
	/**
	 * 更新菜单信息
	 * @param acFunction 待更新的菜单信息
	 * @return 更新条数
	 * @throws Exception
	 */
	public int updateMenu(AcFunction acFunction)throws Exception{
		int count = this.update("MENUMANAGEMENT.updateMenu", acFunction);
		count++;
		return count;
	}
	/**
	 * 验证新增或修改的菜单编码是否存在
	 * @param acFunction 待验证的菜单编码信息
	 * @return 数量
	 * @throws Exception
	 */
	public int checkFuncCode(AcFunction acFunction)throws Exception{
		Object obj = this.queryForObject("MENUMANAGEMENT.checkFuncCode", acFunction);
		return Integer.parseInt(String.valueOf(obj));
	}
	/**
	 * 移动树节点
	 * @param paramMap 移动参数
	 * @throws Exception
	 */
	public void updateMoveTreeNode(HashMap<String, String> paramMap) throws Exception{
		this.update("MENUMANAGEMENT.moveTreeNode", paramMap);
	}
}
