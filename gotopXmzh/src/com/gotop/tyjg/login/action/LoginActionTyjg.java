package com.gotop.tyjg.login.action;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gotop.crm.util.BaseAction;
import com.gotop.tyjg.login.model.AcOperator;
import com.gotop.tyjg.login.service.ILoginService;
import com.gotop.tyjg.menumanagement.model.AcFunction;
import com.gotop.tyjg.menumanagement.model.AcFunctionGroup;
import com.gotop.util.Global;
import com.gotop.util.XmlConvert;
import com.gotop.vo.system.MUOUserSession;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * *******************************
 * <p>Title: 处理用户登录与注销登录请求</p>
 * 
 * <p> Description:   处理用户登录与注销登录请求</p>
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
public class LoginActionTyjg extends BaseAction {
	/**
	 * 识别号
	 */
	private static final long serialVersionUID = -6659152809188190042L;
	
	/**
	 * 操作员对象
	 */
	private AcOperator acOperator;
	
	/**
	 * 登录与注销登录逻辑处理对象
	 */
	private ILoginService loginService;
	
	/**
	 * 登录类型 
	 */
	private String loginType;
	
    private AcFunction acFunction;
	
    private AcFunctionGroup acFunctionGroup;
    private List childMenuList;
    
    
    
	/**
     * @return the childMenuList
     */
    public List getChildMenuList() {
        return childMenuList;
    }

    /**
     * @param childMenuList the childMenuList to set
     */
    public void setChildMenuList(List childMenuList) {
        this.childMenuList = childMenuList;
    }

    /**
     * @return the acFunctionGroup
     */
    public AcFunctionGroup getAcFunctionGroup() {
        return acFunctionGroup;
    }

    /**
     * @param acFunctionGroup the acFunctionGroup to set
     */
    public void setAcFunctionGroup(AcFunctionGroup acFunctionGroup) {
        this.acFunctionGroup = acFunctionGroup;
    }

    /**
     * @return the acFunction
     */
    public AcFunction getAcFunction() {
        return acFunction;
    }

    /**
     * @param acFunction the acFunction to set
     */
    public void setAcFunction(AcFunction acFunction) {
        this.acFunction = acFunction;
    }

    public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public AcOperator getAcOperator() {
		return acOperator;
	}

	public void setAcOperator(AcOperator acOperator) {
		this.acOperator = acOperator;
	}
	/**
	 * 登录操作
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception{
		Map session =  ActionContext.getContext().getSession();
//		session.remove(Global.LOGON_USER_KEY);
		if(acOperator==null){
			return "login";
		}
		//String userName = this.getAcOperator().getOperatorName();
		String userId = this.getAcOperator().getUserId();
		String passw = this.getAcOperator().getPassword();
		
//		System.out.println("----------userId:"+userId);
//		System.out.println("---------password:"+passw);
		
//		userId = "fj_chenlx";
//		passw = "888888";
		
		if(passw==null){
			return "login";
		}
		String orgId = String.valueOf(this.getAcOperator().getOrgid());
		System.out.println("---------orgId:"+orgId);
 
		String	Encryption = null;
		if(loginType == null){//说明是通过登录界面，否则就是切换机构
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
			Encryption= baseEncoder.encode(md5.digest(passw.getBytes("utf-8")));
		}else{
			Encryption = passw;
		}
		HashMap hmp = new HashMap();
		hmp.put("userid", userId);
		hmp.put("password", Encryption);
		boolean isPass = this.getLoginService().vaildataPassword(hmp);
		if(isPass){
			MUOUserSession userSession =  this.getLoginService().selectByUserInfo(userId, orgId);
			HashMap<String, String> logMap = new HashMap<String, String>(5);
			logMap.put("userId", userSession.getEmpcode());
			logMap.put("empName", userSession.getEmpname());
			logMap.put("loginIp", getIpAddr(this.getRequest()));
			this.getLoginService().insertLoginLog(logMap);
			
			sessionBakToMap(session,userSession);
		}else{
			this.write("<script>alert('用户密码错误');window.location.href='/jsp/tyjg/login/login.jsp';</script>");
			return null;
		}
		return ActionSupport.SUCCESS;
	}
	/**
	 * 将对象存入session中
	 * @param session
	 * @param userSession
	 */
	private void sessionBakToMap(Map session,MUOUserSession userSession){
		Object ssobj = session.get(Global.LOGON_USER_KEY);
		if(ssobj!=null){
			HashMap ssmap = (HashMap)session.get(Global.LOGON_SESSION_MAP);
			String ssid = (String)session.get(Global.LOGON_SESSION_ID);
			if(ssmap==null){
				ssmap = new HashMap();
				session.put(Global.LOGON_SESSION_MAP, ssmap);
			}
			if(ssmap.size()>10){//仅支持同时登录10个账号
				ssmap.remove(ssmap.keySet().iterator().next());
			}
			ssmap.put(ssid, ssobj);
		}
	     UUID uuid = UUID.randomUUID();
	     session.put(Global.LOGON_SESSION_ID, uuid.toString());
	     session.put(Global.LOGON_USER_KEY, userSession);
	}
	/**
	 *根据中文名查询登录人的机构信息 
	 * @return
	 * @throws Exception
	 */
	public String queryLoginUserOrg() throws Exception{
		HashMap hmp =  XmlConvert.getParamsAjax(XmlConvert.getAjax());
		String aminOrg = this.loginService.selectByLoginUserOrg(String.valueOf(hmp.get("username")));
//		log.info(aminOrg);
		this.write(aminOrg);
		return null;
	}
	/**
	 * 根据登录用户的角色获取菜单根
	 * @return
	 * @throws Exception
	 */
	public String queryMenuRoot() throws Exception{
		MUOUserSession userSession = this.getCurrentOnlineUser();
		String[] roles = userSession.getRoleid();
		List rootMenuList =  this.getLoginService().selectMenuRoot(roles);
		String rootMenus = XmlConvert.getXmlListBean(rootMenuList);
		this.write(rootMenus);
		return null;
	}
	/**
	 * 根据菜单组id获取子菜单
	 * @return
	 * @throws Exception
	 */
	public String queryMenuChild() throws Exception{
		MUOUserSession userSession = this.getCurrentOnlineUser();
		String[] roles = userSession.getRoleid();
		List<HashMap> groupList = XmlConvert.getMapAjax("acfuncgroup");
		HashMap groupMap = groupList.get(0);
		String groupid = String.valueOf(groupMap.get("FUNCCODE"));
		String groupLevel = String.valueOf(Integer.parseInt(String.valueOf(groupMap.get("GROUPLEVEL")))+1);
		List childMenuList =  this.getLoginService().selectMenuChild(roles, groupid,groupLevel);
		String childMenus = XmlConvert.getXmlListBean(childMenuList);
		this.write(childMenus);
		return null;
	}
	/**
	 * 注销登录
	 * @return
	 * @throws Exception
	 */
	public String systemExit() throws Exception{
		Map session = ActionContext.getContext().getSession();
		session.remove(Global.LOGON_USER_KEY);
		session.remove(Global.LOGON_SESSION_ID);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<root><data>");
		buffer.append("<falg>1");
		buffer.append("</falg>");
		buffer.append("</data></root>");
		this.write(buffer.toString());
		return null;
	}
	/**
	 * 获取登录IP
	 * @param request
	 * @return
	 */
	private  String getIpAddr(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	} 
	/**
	 * 判断历史记录需是否存在
	 * @param jsessid
	 * @param session
	 */
	private void isSessionObj(String jsessid,HttpSession session,String nsessid,Object osess){
		Map bakMap = (Map)session.getAttribute(Global.LOGON_SESSION_MAP);
		if(bakMap==null){
			this.write("0");
			return;
		}
		Object nsess = bakMap.get(jsessid);
		if(nsess==null){
			this.write("0");
		}else{
			session.setAttribute(Global.LOGON_USER_KEY, nsess);
			session.setAttribute(Global.LOGON_SESSION_ID, jsessid);
			
			bakMap.remove(jsessid);
			if(nsessid!=null){
				bakMap.put(nsessid, osess);
			}
			this.write("1");
		}
	}
	/**
	 * 判断是否登录
	 */
	public void isLoginFun(){
		try{
			HttpSession session = this.getSession();
			String jsessid = this.getRequest().getParameter("jsessid");
			String nsessid = (String)session.getAttribute(Global.LOGON_SESSION_ID);
	
			if(jsessid==null){
				this.write("0");
				return;
			}
			Object nsess = session.getAttribute(Global.LOGON_USER_KEY);
			if(nsessid!=null && jsessid.equals(nsessid)){
				if(nsess==null){
					isSessionObj(jsessid,session,nsessid,nsess);
				}else{
					this.write("1");
				}
			}else{
				isSessionObj(jsessid,session,nsessid,nsess);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			HttpSession session = this.getSession();
//			MUOUserSession obj = (MUOUserSession)session.getAttribute(Global.LOGON_USER_KEY);
//			String jsessid = this.getRequest().getParameter("jsessid");
//			Map bakMap = (Map)session.getAttribute(Global.LOGON_SESSION_MAP);
//			if(obj!=null){
//				System.out.println(obj.getEmpname()+"---\n--"+jsessid);
//			}else{
//				System.out.println(obj+"---\n--"+jsessid);
//			}
//			System.out.println(bakMap);
		}
	}
/**
 * 
 *
 *得到菜单信息，包括菜单组
 *
 * @return
 * @throws Exception
 *
 * <pre>
 * 修改日期        修改人    修改原因
 * 2014-7-18    黄开忠    新建
 * </pre>
 */
    public String getFuncGroupInfo() throws Exception{
        //得到时有可能也是菜单组或者是具体菜单
        //用户对应所有角色
        MUOUserSession userSession = this.getCurrentOnlineUser();
        String[] roles = userSession.getRoleid();
        String groupid = this.getAcFunctionGroup().getFuncGroupId();
        String groupLevel = String.valueOf(Integer.parseInt(String.valueOf(this.getAcFunctionGroup().getGroupLevel()))+1);
        this.setChildMenuList(this.getLoginService().selectMenuChild(roles, groupid,groupLevel));
        return "getFuncGroupInfo";
    }
}
