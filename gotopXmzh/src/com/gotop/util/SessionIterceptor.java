package com.gotop.util;

import java.lang.management.ManagementFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gotop.crm.util.MUO;
import com.gotop.util.exception.GotopException;
import com.gotop.vo.system.Tonline;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionIterceptor extends AbstractInterceptor {
	protected static Logger log = Logger.getLogger(SessionIterceptor.class);
	private static final long serialVersionUID = -5718615213552072523L;
	private static boolean isDebug = false;
	static{
		List<String> mfia = ManagementFactory.getRuntimeMXBean().getInputArguments();
		for (int i = 0; i < mfia.size(); i++) {
			String jdwpStr = mfia.get(i);
			if(jdwpStr.indexOf("jdwp")>-1 && jdwpStr.indexOf("address")>-1){
				if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1){//是否为window
					isDebug = true;
				}
			}
		}
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
//		ConfigDebugInterceptor cdir = new ConfigDebugInterceptor();
//		cdir.intercept(actionInvocation);
		
		//登录action不拦截
		String actionName = actionInvocation.getProxy().getActionName();
        if ("login_login".equals(actionName) || "queryLoginUserOrg_login".equals(actionName)|| "isLoginFun_login".equals(actionName)) {
            return actionInvocation.invoke();
        }
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		Object olUser = (Object)session.get(Global.LOGON_USER_KEY);
		if (olUser == null) {
			HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
			String ajax = request.getParameter("ajax");
			if(StringUtils.isBlank(ajax)){
				return Action.LOGIN;
			}else{
				request.setAttribute("loginPage", true);
				return "errorAjax";
			}
		} else {
			HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
			String result = null;
			try{
				boolean stud = sessionTokenUuid(request);
				if(stud){
					startActionInvocation(actionInvocation);
					String resultStr = actionInvocation.invoke();
					getActionInvocation(actionInvocation,resultStr);
					return resultStr;
				}else{
					MUO.write("已执行过了提交");
				}
			}catch(GotopException e){
				e.printStackTrace();
				String ajax = request.getParameter("ajax");
				request.setAttribute("_exception", e);
				if(StringUtils.isBlank(ajax)){
					return "errorAction";
				}else{
					return "errorAjax";
				}
			}catch(Exception e1){
				e1.printStackTrace();
				String ajax = request.getParameter("ajax");
				request.setAttribute("_exception", e1);
				if(StringUtils.isBlank(ajax)){
					return "errorAction";
				}else{
					return "errorAjax";
				}
			}
			return result;
		}
	}
	/**
	 * 令牌
	 * @param ajax
	 * @param request
	 */
	private boolean sessionTokenUuid(HttpServletRequest request){
		String tokenSessionId = request.getParameter("_tokenSessionId");
		if(StringUtils.isNotBlank(tokenSessionId)){
			ActionContext ctx = ActionContext.getContext();
			Map session = ctx.getSession();
			Set<String> tokenUuid = (Set<String>)session.get(Global.LOGON_USER_Token);
			if(tokenUuid!=null){
				if(!tokenUuid.contains(tokenSessionId)){
					return false;
				}else{
					tokenUuid.remove(tokenSessionId);
				}
			}else{
				return false;
			}
		}
		return true;
	}
	
	private boolean VaildatorActionOfRole(ActionInvocation actionInvocation,Map session,Tonline olUser){
		String actionName = actionInvocation.getProxy().getActionName();
		
//		YzjmwdGlobal ygl = (YzjmwdGlobal)session.get("yzjmwdGlobal");
//		int jgjb = ygl.GV_JGJB();
		int jgjb = 0;
		Set<String> rolealiasList = olUser.getRolealiasList();
		
		Map<String, ActionRoleBean> funlist = ActionRoleManager.getFunlist();
		ActionRoleBean arb = funlist.get(actionName);
		if(arb!=null){
			Integer orglevel = arb.getOrglevel();
			if(orglevel!=null && orglevel>0){
				if(orglevel<jgjb){
					return false;
				}
			}
			Set<String> role = arb.getRolealias();
			if(role!=null && !containsAll(rolealiasList,role)){
				return false;
			}
		}else{
			String className  = actionInvocation.getProxy().getConfig().getClassName();
			Map<String, ActionRoleBean> actionlist = ActionRoleManager.getActionlist();
			ActionRoleBean arba = actionlist.get(className);
			if(arba!=null){
				Integer orglevel = arba.getOrglevel();
				if(orglevel!=null && orglevel>0){
					if(orglevel<jgjb){
						return false;
					}
				}
				Set<String> role = arba.getRolealias();
				if(role!=null && !containsAll(rolealiasList,role)){
					return false;
				}
			}else{
				String namespace = actionInvocation.getProxy().getNamespace();
		        if(namespace==null){
		        	namespace = actionName;
		        }
				Map<String, ActionRoleBean> modulelist = ActionRoleManager.getModulelist();
				ActionRoleBean arbm = modulelist.get(namespace);
				if(arbm!=null){
					Integer orglevel = arbm.getOrglevel();
					if(orglevel!=null && orglevel>0){
						if(orglevel<jgjb){
							return false;
						}
					}
					Set<String> role = arbm.getRolealias();
					if(role!=null && !containsAll(rolealiasList,role)){
						return false;
					}
				}
			}
		}
		return true;
	}
	private boolean containsAll(Set<String> rolealiasList,Set<String> role){
		for (Iterator iterator = role.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			if(rolealiasList.contains(key)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 执行的类
	 * @param actionInvocation
	 */
	private void startActionInvocation(ActionInvocation actionInvocation){
		if(isDebug){
			Class classObj = actionInvocation.getProxy().getAction().getClass();
			String className = classObj.getName();
			String methodName = actionInvocation.getProxy().getMethod();
			System.out.println("---------执行的action类: "+className+"."+methodName+"("+classObj.getSimpleName()+".java:0) \n");
		}
	}
	/**
	 * 返回的页面
	 * @param actionInvocation
	 * @param result
	 */
	private void getActionInvocation(ActionInvocation actionInvocation,String result){
		if(isDebug){
			if(StringUtils.isNotBlank(result)){
				Map<String, ResultConfig> results = actionInvocation.getProxy().getConfig().getResults();
				ResultConfig resultConfig = results.get(result);
				if (resultConfig!=null) {
					Map<String,String> params = resultConfig.getParams();
					System.out.println("---------返回的页面: "+params+"\n");
				}
			}
		}
	}
}
