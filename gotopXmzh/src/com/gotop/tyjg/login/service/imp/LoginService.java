package com.gotop.tyjg.login.service.imp;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gotop.tyjg.login.dao.ILoginDao;
import com.gotop.tyjg.login.service.ILoginService;
import com.gotop.util.Global;
import com.gotop.util.string.Obj2StrUtils;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.XmlHelper;
import org.apache.log4j.Logger;
/**
 * *******************************
 * <p>Title: 对登录用户信息的查询逻辑处理</p>
 * 
 * <p> Description:   对登录用户信息的查询逻辑处理</p>
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
public class LoginService implements ILoginService {
	protected static Logger log = Logger.getLogger(LoginService.class);
	/**
	 * 数据操作对象
	 */
	private ILoginDao loginDao;
	/**
	 * 保存用户登录信息的对象
	 */
	private MUOUserSession userSession;
	
	public ILoginDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}
	public MUOUserSession getUserSession() {
		return userSession;
	}
	public void setUserSession(MUOUserSession userSession) {
		this.userSession = userSession;
	}
	/**
	 * 根据用户中文名查询机构信息
	 * @param userName 用户中文名称
	 * @return 包含机构信息的xml字符串
	 * @throws Exception
	 */
	@Override
	public String selectByLoginUserOrg(String userName) throws Exception {
		List orgList = this.getLoginDao().selectByUserOrg(userName);
		String orgResult = XmlHelper.getXmlListMap(orgList);
		return orgResult;
	}
	/**
	 * 校验用户登录密码是否正确
	 * @param hmp 包含的用户信息
	 * @return true:正确 false:错误
	 * @throws Exception
	 */
	@Override
	public boolean vaildataPassword(HashMap hmp) throws Exception {
		return this.getLoginDao().validatePassword(hmp);
	}
	/**
	 * 根据用户id，机构id查询并设置登录用户session信息
	 * @param userId 用户ID
	 * @param orgId 机构ID
	 * @return 包含用户基本信息，机构信息，角色信息的对象
	 * @throws Exception
	 */
	@Override
	public MUOUserSession selectByUserInfo(String userId, String orgId)
			throws Exception {
		List userBases = this.getLoginDao().selectByUserBaseInfo(userId, orgId);
		Map userBaseMap = (Map)userBases.get(0);
		List orgEntitys = this.getLoginDao().selectCriteriaOrg(orgId);
		int jgsx = Integer.parseInt(String.valueOf(userBaseMap.get("JGSX")));
		if(Global.PSBC_JGSX == jgsx){//银行
			this.setUserSession(this.setEntityOrgInfo(userBaseMap, orgEntitys,jgsx));
		}else{
			//邮政
			this.setUserSession(this.setEntityOrgInfo(userBaseMap, orgEntitys,jgsx));
		}
		if(userBaseMap.get("MOBILENO")==null||userBaseMap.get("MOBILENO").equals("")){
		    
		}else{
		    this.getUserSession().setMobi(String.valueOf(userBaseMap.get("MOBILENO")));
		}
		//查询用户拥有的角色
		List roleList = this.getLoginDao().selectByUserRole(String.valueOf(userBaseMap.get("OPERATORID")));
		int size = roleList.size();
		String[] roles = new String[size];
		for(int i = 0 ; i < size; i++){
			Map roleMap = (Map)roleList.get(i);
			roles[i] = String.valueOf(roleMap.get("ROLEID"));
		}
		this.getUserSession().setRoleid(roles);
		
		//查询用户可登录的机构
		List loginList = this.getLoginDao().selectLoginOrg(String.valueOf(userBaseMap.get("EMPID")));
		HashMap<String, String> logOrgMap = new HashMap<String, String>(20);
		for(int i = 0, len = loginList.size(); i < len; i++){
			HashMap temp = (HashMap)loginList.get(i);
			String orgid = String.valueOf(temp.get("ORGID"));
			String orgName = String.valueOf(temp.get("ORGNAME"));
			logOrgMap.put(orgid, orgName);
		}
		
		this.getUserSession().setLoginOrg(logOrgMap);
		
		//查询用户所属岗位
		List positionList = this.getLoginDao().selectPositionByEmp(String.valueOf(userBaseMap.get("EMPID")));
		size=positionList.size();
		String[] posicode = new String[size];
		String[] posiname = new String[size];
		String[] positionid = new String[size];
		for(int i = 0, len = positionList.size(); i < len; i++){
			HashMap temp = (HashMap)positionList.get(i);
			 positionid[i] = String.valueOf(temp.get("POSITIONID"));
			 posicode[i] = String.valueOf(temp.get("POSICODE"));
			 posiname[i] = String.valueOf(temp.get("POSINAME"));
		}
        this.getUserSession().setPosiCode(posicode);
        this.getUserSession().setPosiName(posiname);
        this.getUserSession().setPositionId(positionid);
		HashMap<String, String> hmp = new HashMap<String, String>(1);
		hmp.put("empid",String.valueOf(userBaseMap.get("EMPID")));
		List<HashMap<String, String>> groupList = this.getLoginDao().selectEmpGroup(hmp);
		int groupSize = groupList.size();
		if(groupSize > 0){
			String[] groupIdArray = new String[groupSize];
			for(int i = 0; i < groupSize; i++){
				HashMap<String,String> tempHashMap = (HashMap<String, String>)groupList.get(i);
				groupIdArray[i] = String.valueOf(tempHashMap.get("GROUPID"));
			}
			this.getUserSession().setGroupid(groupIdArray);
		}
		return this.getUserSession();
	}
	/**
	 * 设置用户的实体机构信息
	 * @param userBaseMap 用户基本信息
	 * @param orgEntitys 用户机构上级实体机构信息
	 * @return session对象
	 */
	private MUOUserSession setEntityOrgInfo(Map userBaseMap,List orgEntitys, int jgsx) throws Exception{
		MUOUserSession muous = new MUOUserSession();
		
		long orgEntityId = 0;
		String orgEntityCode = null;
		String orgEntityName = null;
		int size = orgEntitys.size();
		if(size < 1){//说明没有上级实体机构用本机构信息
			orgEntityId =Long.parseLong(String.valueOf(userBaseMap.get("ORGID")));
			orgEntityCode = String.valueOf(userBaseMap.get("ORGCODE"));
			orgEntityName = String.valueOf(userBaseMap.get("ORGNAME"));
		}else{
			Map orgEntityMap = (Map)orgEntitys.get(0);
			orgEntityId =Long.parseLong(String.valueOf(orgEntityMap.get("ORGID")));
			orgEntityCode = String.valueOf(orgEntityMap.get("ORGCODE"));
			orgEntityName = String.valueOf(orgEntityMap.get("ORGNAME"));
		}
		if(Global.PSBC_JGSX == jgsx){//银行
			muous.setOrgentityid(orgEntityId);
			muous.setOrgentitycode(orgEntityCode);
			muous.setOrgentityname(orgEntityName);
		}else{//邮政
			muous.setOrgpostentityid(orgEntityId);
			muous.setOrgpostentitycode(orgEntityCode);
			muous.setOrgpostentityname(orgEntityName);
			Object tempObj = userBaseMap.get("YSJGBH");
			if(tempObj == null){//映射机构为空使用当前机构
				muous.setOrgentityid(orgEntityId);
				muous.setOrgentitycode(orgEntityCode);
				muous.setOrgentityname(orgEntityName);
			}else{
				String ysjgbh = String.valueOf(tempObj);
				List mappOrgList = this.getLoginDao().selectMappingOrg(ysjgbh);
				if(mappOrgList.size()<1){
					muous.setOrgentityid(orgEntityId);
					muous.setOrgentitycode(orgEntityCode);
					muous.setOrgentityname(orgEntityName);
				}else{
					Map mappOrgMap = (Map)mappOrgList.get(0);
					orgEntityId =Long.parseLong(String.valueOf(mappOrgMap.get("ORGID")));;
					orgEntityCode = String.valueOf(mappOrgMap.get("ORGCODE"));
					orgEntityName =String.valueOf(mappOrgMap.get("ORGNAME"));
					muous.setOrgentityid(orgEntityId);
					muous.setOrgentitycode(orgEntityCode);
					muous.setOrgentityname(orgEntityName);
				}
			}
		}
		muous.setOrgJgsx(String.valueOf(userBaseMap.get("JGSX")));
		muous.setEmpid(Long.parseLong(String.valueOf(userBaseMap.get("EMPID"))));
		muous.setEmpcode(String.valueOf(userBaseMap.get("USERID")));
		muous.setEmpname(String.valueOf(userBaseMap.get("OPERATORNAME")));
		muous.setOrgid(Long.parseLong(String.valueOf(userBaseMap.get("ORGID"))));
		muous.setOrgcode(String.valueOf(userBaseMap.get("ORGCODE")));
		muous.setOrgname(String.valueOf(userBaseMap.get("ORGNAME")));
		muous.setOrgdegree(String.valueOf(userBaseMap.get("ORGDEGREE")));
		muous.setOrgysjgid(String.valueOf(userBaseMap.get("ORGYSJGID")));
		muous.setHeadicon(String.valueOf(userBaseMap.get("HEADICON")));
		muous.setPassword(String.valueOf(userBaseMap.get("PASSWORD")));
		muous.setOrgseq(String.valueOf(userBaseMap.get("ORGSEQ")));
		return muous;
	}
	
	/**
	 * 根据角色获取菜单根
	 * @param roles 角色数组
	 * @return 菜单组信息
	 * @throws Exception
	 */
	public List selectMenuRoot(String[] roles) throws Exception{
//		String roleStr = Obj2StrUtils.join(roles, String.class, ",");
		return this.getLoginDao().selectMenuRoot(roles);
	}
	/**
	 * 根据角色、菜单组ID获取子菜单
	 * @param roles 角色数组
	 * @param groupid 菜单组ID
	 * @return 子菜单信息
	 * @throws Exception
	 */
	public List selectMenuChild(String[] roles,String groupid,String groupLevel)throws Exception{
		//String roleStr = Obj2StrUtils.join(roles, String.class, ",");
		return this.getLoginDao().selectMenuChild(roles, groupid,groupLevel);
	}
	/**
	 * 记录登录日志
	 * @param hmp 登录日志信息
	 * @throws Exception
	 */
	public void insertLoginLog(HashMap<String, String> hmp) throws Exception{
		this.getLoginDao().insertLoginLog(hmp);
	}
	/**
	 * 查询登录用户拥有的群组信息
	 * @param hmp 登录人员编号
	 * @return 群组列表
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectEmpGroup(HashMap<String, String> hmp)throws Exception{
		return this.getLoginDao().selectEmpGroup(hmp);
	}
}
