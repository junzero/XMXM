package com.gotop.util.session;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gotop.util.Global;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.Tonline;

public class OnlineUserManager {
	
	protected static Logger log = Logger.getLogger(OnlineUserManager.class);
	public final static String LOGON_USER_IP = "__RemoteAddr_IP";
	public final static String LOGON_USER_Time = "__Create_TimeMillis";
	public final static String LOGON_USER_Online = "__OnlineUserBindingListener";
	private static HashMap<String,HttpSession> userObje = new HashMap<String,HttpSession>();
	public static int managerProt = 0;
	/**
	 * 增加session
	 * @param session
	 */
	public static void addUserObje(HttpSession session) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = getIpAddr(request);
		session.setAttribute(LOGON_USER_IP, ip);
		session.setAttribute(LOGON_USER_Time, System.currentTimeMillis());
		OnlineUserManager.userObje.put(session.getId(),session);
	}
	/**
	 * 移除session
	 * @param session
	 */
	public static void removeUserObje(HttpSession session) {
		OnlineUserManager.userObje.remove(session.getId());
	}
	/**
	 * 用户与session绑定
	 */
	public static void onlineUser(HttpSession session) {
	    session.setAttribute(OnlineUserManager.LOGON_USER_Online, new OnlineUserBindingListener());
    }
	/**
	 * 用户与session绑定,并T除已存在对象
	 */
	public static void onlineUserT(HttpSession newSession) {
	    Tonline obj = (Tonline)newSession.getAttribute(Global.LOGON_USER_KEY);
        HashMap<String,HttpSession> uo = OnlineUserManager.userObje;//已绑定的权限列表
        Set<Map.Entry<String,HttpSession>> smeuo = uo.entrySet();
	    for (Iterator<Map.Entry<String,HttpSession>> iterator = smeuo.iterator(); iterator.hasNext();) {
	        Map.Entry<String,HttpSession> meshs = iterator.next();
	        String osKey = meshs.getKey();
	        HttpSession oSession = meshs.getValue();
	        Tonline objo = (Tonline)oSession.getAttribute(Global.LOGON_USER_KEY);
	        log.info(obj.getUsid()+"=="+objo.getUsid());
	        log.info(obj.getYhmc()+"=="+objo.getYhmc());
	        if(obj.getUsid().equals(objo.getUsid())){//如果ID相同则，刚使其它失效
	            uo.remove(osKey);
	            oSession.invalidate(); 
	        }
	    }
	    newSession.setAttribute(OnlineUserManager.LOGON_USER_Online, new OnlineUserBindingListener());
	}
    /**
     * 强制下线
     */
    public static void LoginOutUser(String sesid) {
        HttpSession hsession = userObje.get(sesid);
        hsession.invalidate();
    }
	/**
	 * 统计数
	 * @return
	 */
	public static int getCount(){
//		System.out.println(OnlineUserManager.class.getProtectionDomain().getCodeSource().getLocation());
		return OnlineUserManager.userObje.size();
	}
	/**
	 * 获取在线人数
	 * @return
	 */
	public static String getAllUser(){
		StringBuffer sbr = new StringBuffer();
//		sbr.append("<table align='center' width='100%' border='2'>");
//		sbr.append("<tr><th><nobr>用户名</nobr></th><th><nobr>登录时间</nobr></th><th width='780'>登录IP</th></tr>");
		String luser = getLocalUser();
		sbr.append(luser);
//		sbr.append("</table>");
		return sbr.toString();
	}
	public static String getLocalUser(){
		StringBuffer sbr = new StringBuffer();
//		sbr.append("<table align='center' width='100%' border='2'>");
//		sbr.append("<tr><th><nobr>用户名</nobr></th><th><nobr>登录时间</nobr></th><th width='780'>登录IP</th></tr>");
		HashMap<String,HttpSession> uo = OnlineUserManager.userObje;
        Set<Map.Entry<String,HttpSession>> smeuo = uo.entrySet();
        for (Iterator<Map.Entry<String,HttpSession>> iterator = smeuo.iterator(); iterator.hasNext();) {
            Map.Entry<String,HttpSession> meshs = iterator.next();
            String mlog = meshs.getKey();
            HttpSession session = meshs.getValue();
			Tonline obj = (Tonline)session.getAttribute(Global.LOGON_USER_KEY);
			Long lutime = (Long)session.getAttribute(LOGON_USER_Time);
			Date fdate = new Date(lutime);
			sbr.append("<tr>");
			sbr.append("<td><nobr>"+obj.getYhmc()+"("+obj.getUsid()+")"+"</nobr></td>");
			String drtime = TimeUtil.getCntDtStr(fdate, "yyyy-MM-dd HH:mm:ss:SSS");
			sbr.append("<td><nobr>"+drtime+"</nobr></td>");
			sbr.append("<td><nobr>"+session.getAttribute(LOGON_USER_IP)+"</nobr></td>");
			sbr.append("<td><input value=\"T.走你\" type=\"button\" onclick=\"alert('"+mlog+"')\"></td>");//mlog
			sbr.append("</tr>");
		}
//		sbr.append("</table>");
		return sbr.toString();
	}
	/**
	 * 获取用户IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {       
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
}
