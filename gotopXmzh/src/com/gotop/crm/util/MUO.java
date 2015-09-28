package com.gotop.crm.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import uk.ltd.getahead.dwr.WebContextFactory;

import com.gotop.util.Global;
import com.gotop.util.security.ForUtil;
import com.gotop.vo.system.MUOUserSession;
import com.gotop.vo.system.Tonline;
import com.opensymphony.xwork2.ActionContext;

public class MUO {

	static Logger log = Logger.getLogger(MUO.class);
	public static String USER_SESSION_KEY = "user";

	public static PrintWriter getResponseWriter() throws IOException {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");
		return response.getWriter();
	}

	public static boolean isBlankOrNull(String str) {
		return org.apache.commons.validator.GenericValidator.isBlankOrNull(str);
	}

	public static HttpServletResponse getResponse() throws IOException {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");
		return response;
	}

	public static HttpServletRequest getRequest() throws IOException {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
//		request.setCharacterEncoding("text/html;charset=UTF-8");
		return request;
	}

	/*
	 * 设置Session对象
	 */
	public static void setDWRSession(String key, Object obj) {
		HttpSession session = WebContextFactory.get().getSession();
		if (session != null)
			session.putValue(key, obj);
	}

	/*
	 * 获取Session 对象
	 */
	public static Object getDWRSession(String key) {
		HttpSession session = WebContextFactory.get().getSession();
		return session.getValue(key);
	}
	public static Object getSession(String key) {
		Map sessionMap = ActionContext.getContext().getSession();
		return sessionMap.get(key);

	}
	public static void setSession(String key,Object value) {
		Map sessionMap = ActionContext.getContext().getSession();
		sessionMap.put(key, value);
	}

	public static void removeSession() {

		HttpSession session = ServletActionContext.getRequest().getSession();
		session.invalidate();
	}

	public static void removeDWRSession(String key) {
		HttpSession session = WebContextFactory.get().getSession();
		if (session.getValue(key) != null)
			session.removeValue(key);
	}
	public static String getParameter(String key) {
		try {
			return getRequest().getParameter(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void write(String result){
		ForUtil.write(result);
	}
	
	public static void mapToBean(HashMap hmp,String mapName,Class cob,String objName){
		if(hmp==null){
			log.info("--map不为空-");
		}
		Set set = hmp.keySet();
		Field[] fields = cob.getDeclaredFields();
		Method[] methods = cob.getDeclaredMethods();
		StringBuffer filesb = new StringBuffer();
		StringBuffer setsb = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fname = field.getName();
			Method fmethod = null;
			String mapKey = null;
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				String name = (String) iterator.next();
				if(fname.equals(name)){
					mapKey = name;
					break;
				}
			}
			if(mapKey==null){
				continue;
			}
			for (int j = 0; j < methods.length; j++) {
				Method method = methods[j];
				if(method.getName().equalsIgnoreCase("set"+fname)){
					fmethod = method;
					break;
				}
			}
			if(fmethod!=null){
				Class type = fmethod.getParameterTypes()[0];
				filesb.append("String "+mapKey+"Str = (String)"+mapName+".get(\""+mapKey+"\");\n");
				if(type==String.class){
					setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str==null?\"\":"+mapKey+"Str);\n");
				}else if(type==Date.class){
					setsb.append(objName+"."+fmethod.getName()+"(TimeUtil.getDate("+mapKey+"Str));\n");
				}else if(type==Long.class){
					setsb.append(objName+"."+fmethod.getName()+"(Long.valueOf("+mapKey+"Str));\n");
				}else if(type==Double.class){
					setsb.append(objName+"."+fmethod.getName()+"(Double.valueOf("+mapKey+"Str));\n");
				}else{
					setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
				}
			}
		}
		System.out.println(filesb.toString());
		System.out.println(setsb.toString());
	}
	
	public static void listToFieldTld(List resultList){
//		JOptionPane.showMessageDialog(null, "HELLO WORD!!", "消息提示",JOptionPane.INFORMATION_MESSAGE);
		if(resultList==null && resultList.size()>0){
			log.info("--list不为空-");
		}
		Object result = resultList.get(0);
		StringBuffer filesb = new StringBuffer();
		if(result instanceof Map){
			HashMap hmp = new HashMap();
			Set keys = hmp.keySet();
			for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
				String name = (String) iterator.next();
				Object value = hmp.get(name);
				filesb.append("<r:field fieldName=\""+name+"\" label=\""+name+"\">\n");
				if(value instanceof Date){
					filesb.append("  <w:date/>\n");
				}else{
					filesb.append("  <h:text/>\n");
				}
				filesb.append("</r:field>\n");
			}
		}else{
			Field[] fields = result.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Method[] methods = result.getClass().getDeclaredMethods();
				Class mclass = null;
				for (int j = 0; j < methods.length; j++) {
					Method method = methods[j];
					if(method.getName().equalsIgnoreCase("get"+field.getName())){
						mclass = method.getReturnType();
						break;
					}
				}
				if(mclass!=null){
					filesb.append("<r:field fieldName=\""+field.getName()+"\" label=\""+field.getName()+"\">\n");
					if(mclass==Date.class){
						filesb.append("  <w:date/>\n");
					}else{
						filesb.append("  <h:text/>\n");
					}
					filesb.append("</r:field>\n");
				}
			}
		}
		System.out.println(filesb.toString());
	}
	public static Tonline getOnlineUser() {
		HttpServletRequest request=ServletActionContext.getRequest();
		Tonline obj = (Tonline)request.getSession().getAttribute(Global.LOGON_USER_KEY);
		return obj;
	}
	
	public static MUOUserSession getCurrentOnlineUser() {
		Map session = ActionContext.getContext().getSession();
		MUOUserSession obj = (MUOUserSession)session.get(Global.LOGON_USER_KEY);
		return obj;
	}
	/**
	 * 获取参数
	 * @param param
	 * @return
	 */
	public static String getParamStr(String param){
		HttpServletRequest request=ServletActionContext.getRequest();
		return request.getParameter(param);
	}
	/**
	 * 获取随机数
	 * @return
	 */
	public static double rRandom(){
		return ForUtil.rRandom();
	}
}
