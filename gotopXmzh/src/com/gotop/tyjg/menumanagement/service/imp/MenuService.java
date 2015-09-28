package com.gotop.tyjg.menumanagement.service.imp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.menumanagement.dao.IMenuDao;
import com.gotop.tyjg.menumanagement.model.AcApplication;
import com.gotop.tyjg.menumanagement.model.AcFunction;
import com.gotop.tyjg.menumanagement.model.AcFunctionGroup;
import com.gotop.tyjg.menumanagement.service.IMenuService;
import com.primeton.utils.Page;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
public class MenuService implements IMenuService,Serializable {
	private static final long serialVersionUID = 1397357119507270645L;
	protected static Logger log = Logger.getLogger(MenuService.class);
	private IMenuDao menuDao;
	public IMenuDao getMenuDao() {
		return menuDao;
	}
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}
	/**
	 * 获取应用菜单系统的根
	 * @return 应用系统
	 * @throws Exception
	 */
	public List<AcApplication> queryAppRoot() throws Exception{
		String appIds = this.getMenuDao().selectIsSynApp();
		String[] app={appIds};
		return this.getMenuDao().selectAppRoot(app);
	}
	/**
	 * 查询菜单组信息
	 * @param acApplication 应用系统信息
	 * @return 菜单组集合
	 * @throws Exception
	 */
	public List<AcFunctionGroup> queryMenuGroup(AcApplication acApplication) throws Exception{
		return this.getMenuDao().selectMenuGroup(acApplication.getAppId());
	}
	/**
	 * 获取子功能组信息
	 * @param acApplication 应用系统信息
	 * @return 菜单组集合
	 * @throws Exception
	 */
	public List<AcFunctionGroup> queryMenuChildGroup(AcFunctionGroup acFunctionGroup) throws Exception{
		String groupid = acFunctionGroup.getFuncGroupId();
		int tempLeve = Integer.parseInt(acFunctionGroup.getGroupLevel())+1;
		String groupLevel = String.valueOf(tempLeve);
		return this.getMenuDao().selectMenuChildGroup(groupid, groupLevel);
	}
	/**
	 * 获取子功能组信息
	 * @param acFunctionGroup 菜单父组信息
	 * @return 菜单集合
	 * @throws Exception
	 */
	public List<AcFunction> queryMenu(AcFunctionGroup acFunctionGroup) throws Exception{
		return this.getMenuDao().selectMenu(acFunctionGroup.getFuncGroupId());
	}
	/**
	 * 查询应用系统列表
	 * @param acApplication 查询条件
	 * @param page 分页信息
	 * @return 应用系统列表
	 * @throws Exception
	 */
	public List<AcApplication> queryAppList(AcApplication acApplication,Page page) throws Exception{
		HashMap<String,Object> hmp = new HashMap<String,Object> ();
		String appName = acApplication.getAppName();
		String appType = acApplication.getApptype();
		if(appName != null && !"".equals(appName)){
			hmp.put("appname", appName);
		}
		if(appType != null && !"".equals(appType)){
			hmp.put("apptype", appType);
		}
		hmp.put("oracleStart", page.getBegin());
		hmp.put("oracleEnd", page.getBegin()+page.getLength());
		long count = this.getMenuDao().countRecord(hmp, "MENUMANAGEMENT.querySystemAppCount");
		page.setCount(Integer.parseInt(String.valueOf(count)));
		return this.getMenuDao().selectSystemApp(hmp);
	}
	/**
	 * 新增应用系统
	 * @param acApplication 应用系统信息
	 * @return	true:成功 false：失败
	 * @throws Exception
	 */
	public boolean insertApp(AcApplication acApplication)throws Exception{
		return this.getMenuDao().insertApp(acApplication);
	}
	/**
	 * 验证新增的应用代码是否存在
	 * @param appCode 应用代码
	 * @return 数量
	 * @throws Exception
	 */
	public long checkAppCode(String appCode,String appId)throws Exception{
		if(StringUtils.isBlank(appCode)){
			log.error("错误信息：应用系统代码为空");
			throw new RuntimeException("应用系统代码为空");
		}
		return this.getMenuDao().checkAppCode(appCode,appId);
	}
	/**
	 * 删除应用系统
	 * @param appids 待删除应用系统编号使用逗号分割的字符串
	 * @return true：删除成功 false:删除失败
	 * @throws Exception
	 */
	public boolean deleteApp(String appids) throws Exception{
		if(StringUtils.isBlank(appids)){
			log.error("错误信息：待删除的应用系统编号为空");
			throw new RuntimeException("待删除应用系统编号为空");
		}
		String[] appidArray = appids.split(",");
		return this.getMenuDao().deleteApp(appidArray);
	}
	/**
	 * 更新应用系统信息
	 * @param acApplication 应用系统信息
	 * @return true:修改成功 false:修改失败
	 * @throws Exception
	 */
	public boolean updateApp(AcApplication acApplication) throws Exception{
		int count = this.getMenuDao().updateApp(acApplication);
		if(count < 1){
			return false;
		}
		return true;
	}
	/**
	 * 查询单个应用系统信息
	 * @param acApplication 应用系统信息
	 * @return 应用系统信息
	 * @throws Exception
	 */
	public AcApplication querySingleApp(AcApplication acApplication) throws Exception{
		String appid = acApplication.getAppId();
		if(StringUtils.isBlank(appid) || "null".equals(appid)){
			log.error("错误信息：应用系统编号为空");
			throw new RuntimeException("应用系统编号为空");
		}
		acApplication = this.getMenuDao().selectByAppid(appid);
		String paraCode1 = acApplication.getParaCode1();
		String paraCode2 = acApplication.getParaCode2();
		String paraCode3 = acApplication.getParaCode3();
		String paraCode4 = acApplication.getParaCode4();
		String paraCode5 = acApplication.getParaCode5();
		String paraName1 = acApplication.getParaName1();
		String paraName2 = acApplication.getParaName2();
		String paraName3 = acApplication.getParaName3();
		String paraName4 = acApplication.getParaName4();
		String paraName5 = acApplication.getParaName5();
		String[] codeArray = {paraCode1,paraCode2,paraCode3,paraCode4,paraCode5};
		String[] nameArray = {paraName1,paraName2,paraName3,paraName4,paraName5};
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < 5; i++){
			if(!StringUtils.isBlank(codeArray[i])){
				if(!StringUtils.isBlank(nameArray[i])){
					int size = buffer.length();
					if(size > 0){
						buffer.append("&").append(codeArray[i]).append("=").append(nameArray[i]);
					}else{
						buffer.append(codeArray[i]).append("=").append(nameArray[i]);
					}
				}
			}
		}
		acApplication.setParaNameStr(buffer.toString());
		return acApplication;
	}
	/**
	 * 新增应用功能组
	 * @param acFunctionGroup 待新增功能组信息
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean insertFuncGroup(AcFunctionGroup acFunctionGroup) throws Exception{
		if(acFunctionGroup.getGroupLevel() == null || "".equals(acFunctionGroup.getGroupLevel())){
			acFunctionGroup.setGroupLevel("1");//为空赋值1说明是在应用系统下新增子功能组，否则就是在子组下新增
		}
		return this.getMenuDao().insertFuncGroup(acFunctionGroup);
	}
	/**
	 * 根据应用编号查询该应用下的工作组列表
	 * @param appid 应用系统编号
	 * @param page 分页信息
	 * @return 应用系统下工作组列表
	 * @throws Exception
	 */
	public List<AcFunctionGroup> selectByAppChildGroupList(String appid,Page page) throws Exception{
		if(StringUtils.isBlank(appid)){
			log.info("参数：appid为空");
			throw new RuntimeException("参数：appid为空");
		}
		HashMap<String , Object> hmp = new HashMap<String , Object>(3);
		hmp.put("appid", appid);
		hmp.put("oracleStart", page.getBegin());
		hmp.put("oracleEnd", page.getBegin()+page.getLength());
		int count = this.getMenuDao().countRecord(hmp, "MENUMANAGEMENT.countAppChildGroup");
		page.setCount(count);
		return this.getMenuDao().selectByAppChildGroupList(hmp);
	}
	/**
	 * 更新应用系统工作组的显示顺序
	 * @param groupids 工作组编号
	 * @param orderValues 新排序的值
	 * @return true：更新成功 false:更新失败
	 * @throws Exception
	 */
	public boolean updateAppGroupDisplayOrder(String groupids,String orderValues) throws Exception{
		String[] groupIdArray = groupids.split(",");
		String[] orderValAray = orderValues.split(",");
		int count = this.getMenuDao().updateAppGroupDisplayOrder(groupIdArray, orderValAray);
		if(count <= 0){
			return false;
		}
		return true;
	}
	/**
	 * 修改工作组名称
	 * @param functionGroup 待修改的工作组信息
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean updateGroupName(AcFunctionGroup functionGroup)throws Exception{
		int count = this.getMenuDao().updateGroupName(functionGroup);
		if(count <= 0){
			return false;
		}
		return true;
	}
	/**
	 * 批量删除工作组
	 * @param groupids 待删除工作组编号数组
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean deleteBeathGroup(String[] groupids) throws Exception{
		int count = this.getMenuDao().deleteBeathGroup(groupids);
		if(count > 0){
			return true;
		}
		return false;
	}
	/**
	 * 查询工作组信息
	 * @param functionGroup 待查询工作组信息
	 * @return 工作组信息
	 * @throws Exception
	 */
	public AcFunctionGroup selectByGroupidInfo(AcFunctionGroup functionGroup) throws Exception{
		return this.getMenuDao().selectByGroupidInfo(functionGroup);
	}
	/**
	 * 更新工作组信息
	 * @param functionGroup 待更新的工作组信息
	 * @return true:成功 false:失败
	 * @throws Exception
	 */
	public boolean updateGrupInfo(AcFunctionGroup functionGroup)throws Exception{
		return this.getMenuDao().updateGrupInfo(functionGroup);
	}
	/**
	 * 查询某个组下子组列表
	 * @param acFunctionGroup 父功能组信息
	 * @param page 分页信息
	 * @return 子组列表
	 * @throws Exception
	 */
	public List<AcFunctionGroup> selectGroupChildGroupList(AcFunctionGroup acFunctionGroup,Page page) throws Exception{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(10);
		int level = Integer.parseInt(acFunctionGroup.getGroupLevel()) + 1;
		paramMap.put("appid", acFunctionGroup.getAppId());
		paramMap.put("funcGroupId", acFunctionGroup.getFuncGroupId());
		paramMap.put("groupLevel", level);
		paramMap.put("oracleStart", page.getBegin());
		paramMap.put("oracleEnd", page.getBegin()+page.getLength());
		int count = this.getMenuDao().countRecord(paramMap, "MENUMANAGEMENT.countGroupChildGroup");
		page.setCount(count);
		return this.getMenuDao().selectFuncChildGroupList(paramMap);
	}
	/**
	 * 查询某个功能组下的功能菜单列表
	 * @param acFunction 待查功能信息
	 * @param page 分页信息
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public List<AcFunction> selectGroupFunMenu(AcFunction acFunction,Page page)throws Exception{
		HashMap<String, Object> hmp = new HashMap<String, Object>(3);
		hmp.put("funcGroupId", acFunction.getFuncGroupId());
		hmp.put("oracleStart", page.getBegin());
		hmp.put("oracleEnd", page.getBegin()+page.getLength());
		int count = this.getMenuDao().countRecord(hmp, "MENUMANAGEMENT.countGroupFuncMenu");
		page.setCount(count);
		return this.getMenuDao().selectGroupFuncMenu(hmp);
	}
	/**
	 * 查询单个功能菜单信息
	 * @param acFunction 包含待查询的菜单编号
	 * @return 菜单信息
	 * @throws Exception
	 */
	public AcFunction selectSingleFuncMenuInfo(AcFunction acFunction)throws Exception{
		return this.getMenuDao().selectSingleFuncMenuInfo(acFunction);
	}
	/**
	 * 新增功能菜单
	 * @param function 待新增的菜单信息
	 * @return 新增的条数
	 * @throws Exception
	 */
	public int insertMenu(AcFunction function) throws Exception{
		return this.getMenuDao().insertMenu(function);
	}
	/**
	 * 删除菜单
	 * @param acFunction 待删除菜单信息
	 * @return 删除条数
	 * @throws Exception
	 */
	public int deleteMenu(AcFunction acFunction)throws Exception{
		return this.getMenuDao().deleteMenu(acFunction);
	}
	/**
	 * 更新菜单的显示顺序
	 * @param funccodes 菜单编码
	 * @param orderValues 排序值
	 * @return 更新条数
	 * @throws Exception
	 */
	public int updateMenuDisplayOrder(String[] funccodes,String[] orderValues) throws Exception{
		return this.getMenuDao().updateMenuDisplayOrder(funccodes, orderValues);
	}
	/**
	 * 更新菜单信息
	 * @param acFunction 待更新的菜单信息
	 * @return 更新条数
	 * @throws Exception
	 */
	public int updateMenu(AcFunction acFunction)throws Exception{
		return this.getMenuDao().updateMenu(acFunction);
	}
	/**
	 * 验证新增或修改的菜单编码是否存在
	 * @param acFunction 待验证的菜单编码信息
	 * @return 数量
	 * @throws Exception
	 */
	public int checkFuncCode(AcFunction acFunction)throws Exception{
		return this.getMenuDao().checkFuncCode(acFunction);
	}
	/**
	 * 树几节点移动
	 * @param fromMap 移动起始节点信息
	 * @param toMap 移动终点信息
	 * @param type 移动类型
	 * @throws Exception
	 */
	public void updateMoveTreeNode(HashMap<String, String> fromMap,HashMap<String, String> toMap,String type) throws Exception{
		String toFuncGroupId = null;
		if("groupToApp".equals(type)){
			toFuncGroupId = fromMap.get("funcGroupId");
		}else{
			toFuncGroupId = toMap.get("funcGroupId");
		}
		HashMap<String, String> paramMap = new HashMap<String, String>(5);
		paramMap.put("toFuncGroupId", toFuncGroupId);
		//都不满足的就是移动到应用系统下
		if("menuToGroup".equals(type)){//菜单移动到组下
			String fromFuncCode = fromMap.get("funcCode");
			paramMap.put("fromFuncCode", fromFuncCode);
		}else{
			if("groupToGroup".equals(type)){//组移动到组下
				String groupLevel = toMap.get("groupLevel");
				String fromFuncGroupId = fromMap.get("funcGroupId");
				String toFuncGroupSeq = toMap.get("funcGroupSeq");
				int tempLevel =  Integer.parseInt(groupLevel)+1;
				groupLevel = String.valueOf(tempLevel);
				paramMap.put("groupLevel", groupLevel);
				paramMap.put("fromFuncGroupId", fromFuncGroupId);
				paramMap.put("toFuncGroupSeq", toFuncGroupSeq);
			}
		}
		paramMap.put("execType", type);
		this.getMenuDao().updateMoveTreeNode(paramMap);
	}
}
