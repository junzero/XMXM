/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.gotop.util.strtsu2;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsConstants;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * 
 * @author sl
 * email：shileijava@sohu.com
 */
@SuppressWarnings("serial")
public class ConfigDebugInterceptor implements Interceptor {
	private boolean devMode;// plugin switch:true(print debug info on console),false(do not print info on console)
	public static String configFileLocation;
	@Inject(StrutsConstants.STRUTS_DEVMODE)
	public void setDevMode(String mode) {
		this.devMode = "true".equals(mode);
	}

	public void setConfigFileLocation(String configFileLocation){
		this.configFileLocation = configFileLocation;
	}
	/**
	 * interceptor implements
	 */
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		String result = "";
		if (devMode) {// config in struts.xml [<constant name="struts.devMode" value="true"/>]
			// get option value
			String accessMethodTip = PropertyReader.getValue("accessMethodTip");
			String debugUrlTip = PropertyReader.getValue("debugUrlTip");
			String parametersMultirowTip = PropertyReader.getValue("parametersMultirowTip");
			String actionAndMethodTip = PropertyReader.getValue("actionAndMethodTip");
			String executeTimeTip = PropertyReader.getValue("executeTimeTip");
			String configFilePathTip = PropertyReader.getValue("configFilePathTip");
			String configFileLineNumberTip = PropertyReader.getValue("configFileLineNumberTip");
			String actionReturnResultTip = PropertyReader.getValue("actionReturnResultTip");
			String actionReturnViewTip = PropertyReader.getValue("actionReturnViewTip");
			String sessionTip = PropertyReader.getValue("sessionTip");

			String showSession = PropertyReader.getValue("showSession");

			String tipPrefix = PropertyReader.getValue("tipPrefix");

			// handler debug url
			String originalAccessUrl = ServletActionContext.getRequest().getRequestURL().toString();// if access method is GET,do not include parameters
			String accessMethod = ServletActionContext.getRequest().getMethod();// get access method ,possible value is POST,GET,PUT
			String parameters = "";// parameters with access
			String debugUrl = "";// use for debug on browser address bar,if access method is POST ,will recombine GET
									// style
			if ("get".equalsIgnoreCase(accessMethod)) {
				parameters = ServletActionContext.getRequest().getQueryString();// get GET parameters
				debugUrl = originalAccessUrl + (null == parameters || "".equals(parameters.trim()) ? "" : "?" + parameters);
			} else {
				parameters = this.getParameters(ServletActionContext.getRequest());// get POST parameters
				debugUrl = originalAccessUrl + (null == parameters || "".equals(parameters.trim()) ? "" : "?" + parameters);
			}

			// invoke action and compute execute time
			long startTime = System.currentTimeMillis();

			result = actionInvocation.invoke();// invoke action

			long executionTime = System.currentTimeMillis() - startTime;// compute action execute time

			// print debug info
			System.out.println(generateTip(tipPrefix, 20) + "struts2 debug info begin" + generateTip(tipPrefix, 20));
			int i = 1; // order number
			System.out.println(tipPrefix + (i++) + "." + accessMethodTip + "：" + accessMethod);
			System.out.println(tipPrefix + (i++) + "." + debugUrlTip + "：" + debugUrl);
			if (null != parameters && !"".equals(parameters.trim())) {
				System.err.println(tipPrefix + (i++) + "." + parametersMultirowTip + "：" + "\n" + tipPrefix + "  "
						+ parameters.replaceAll("&", "\n" + tipPrefix + "  "));// replace & with newline
			}

			try {
				System.out.println(tipPrefix + (i++) + "." + actionAndMethodTip + ":"
						+ actionInvocation.getProxy().getAction().getClass().getName() + "."
						+ actionInvocation.getProxy().getMethod() + "()");
				System.out.println(tipPrefix + (i++) + "." + executeTimeTip + ":" + executionTime + "ms");

				Map<String, ResultConfig> results = actionInvocation.getProxy().getConfig().getResults();
				ResultConfig resultConfig = null;
				resultConfig = results.get(result);
				if (null != resultConfig) {
					if(null != resultConfig.getLocation()){
						System.out.println(tipPrefix + (i++) + "." + configFilePathTip + ":"
								+ resultConfig.getLocation().getURI());
						System.out.println(tipPrefix + (i++) + "." + configFileLineNumberTip + ":"
								+ resultConfig.getLocation().getLineNumber());
					}
					System.out.println(tipPrefix + (i++) + "." + actionReturnResultTip + ":" + resultConfig.getName());
					System.out.println(tipPrefix + (i++) + "." + actionReturnViewTip + ":" + resultConfig.getParams());
				} else {
					System.out.println(tipPrefix + (i++) + ".there is no result config !!!");
				}
				if ("true".equalsIgnoreCase(showSession.trim())) {
					System.out.println(tipPrefix + (i++) + "." + sessionTip + ":");
					Map<String, Object> httpSession = actionInvocation.getInvocationContext().getSession();
					for (Iterator iterator = httpSession.keySet().iterator(); iterator.hasNext();) {
						String key = (String) iterator.next();
						System.out.println(tipPrefix + "  " + key + "=" + httpSession.get(key));
					}
				}
			} catch (Exception e) {
			}
			System.out.println(generateTip(tipPrefix, 20) + "struts2 debug info end" + generateTip(tipPrefix, 21));
		} else {
			result = actionInvocation.invoke();// if devMode=false ，invoke action direct
		}

		// System.out.println(actionInvocation.getStack().peek().toString());
		// System.out.println(actionInvocation.getStack().toString());
		// Map<String,Object> m = actionInvocation.getStack().getContext();
		// for (Iterator iterator = m.keySet().iterator(); iterator.hasNext();) {
		// String key = (String) iterator.next();
		// if("session".equals(key) || "application".equals(key)
		// ||"com.opensymphony.xwork2.ActionContext.application".equals(key)){
		//					
		// }else{
		// System.out.println(key+"="+m.get(key));
		// }
		//				
		// }

		// System.out.println("application begin@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		// Map<String,Object> application = actionInvocation.getInvocationContext().getApplication();
		// for (Iterator iterator = application.keySet().iterator(); iterator.hasNext();) {
		// String key = (String) iterator.next();
		// System.out.println("application:"+key+"="+application.get(key));
		// }
		// System.out.println("application end@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		// System.out.println("actionContextMap begin@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		// Map<String,Object> actionContext = actionInvocation.getInvocationContext().getContextMap();
		// for (Iterator iterator = actionContext.keySet().iterator(); iterator.hasNext();) {
		// String key = (String) iterator.next();
		// System.out.println("actionContext:"+key+"="+actionContext.get(key));
		// }
		// System.out.println("actionContextMap end@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		// System.out.println("httpServletRequest begin@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		// Map<String,Object> httpServletRequest = actionInvocation.getInvocationContext().getParameters();
		// for (Iterator iterator = httpServletRequest.keySet().iterator(); iterator.hasNext();) {
		// String key = (String) iterator.next();
		// System.out.println("httpServletRequest:"+key+"="+httpServletRequest.get(key).toString());
		// }
		// System.out.println("httpServletRequest end@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		// System.out.println("actionInvocation.getInvocationContext().getClass():"+actionInvocation.getInvocationContext().getClass().getName());
		// System.out.println(""+actionInvocation.getProxy().getConfig().getClassName());
		// System.err.println("1"+actionInvocation.getAction()+"."+actionInvocation.getProxy().getActionName());
		// System.err.println("2"+actionInvocation.getProxy().getMethod());
		// System.err.println("3"+actionInvocation.getProxy().getNamespace());
		// System.err.println("4"+actionInvocation.getProxy().getAction().getClass().getName());
		// System.err.println("4"+actionInvocation.getProxy().getAction().getClass().getPackage().getName());
		// System.err.println("5"+actionInvocation.getProxy().getClass().getName());
		// System.err.println("6"+actionInvocation.getProxy().getConfig());
		//			
		// System.err.println("7"+actionInvocation.getProxy().getConfig().getClassName());
		// System.err.println("8"+actionInvocation.getProxy().getConfig().getMethodName());
		// System.err.println("9"+actionInvocation.getProxy().getConfig().getName());
		// System.err.println("10"+actionInvocation.getProxy().getConfig().getPackageName());
		// System.err.println("12"+actionInvocation.getProxy().getConfig().getLocation());
		// System.err.println("13"+actionInvocation.getProxy().getConfig().getParams());
		//			
		// System.err.println("14"+actionInvocation.getProxy().getConfig().getResults());

		return result;
	}

	/**
	 * get POST access method parameters
	 * 
	 * @param request
	 * @return recombine GET style string
	 */
	@SuppressWarnings("unchecked")
	private String getParameters(ServletRequest request) {
		Enumeration paramNames = request.getParameterNames();// get parameters
		StringBuffer params = new StringBuffer();
		while (null != paramNames && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();// parameter name
			String[] values = request.getParameterValues(paramName);// parameter value
			if (null == values || values.length == 0) {

			} else if (values.length == 1) {
				params.append(paramName + "=" + values[0] + "&");
			} else {
				for (int i = 0; i < values.length; i++) {
					params.append(paramName + "=" + values[i] + "&");// handle checkbox page elements
				}
			}
		}
		if(params.length() > 0){//处理post提交时，没有参数的情况，没有参数params.length()=0，那么执行params.substring(0, params.length() - 1);，就会抛出Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: -1
			return params.substring(0, params.length() - 1);
		}
		return params.toString();
	}

	private String generateTip(String tipPrefix, int count) {
		StringBuffer tip = new StringBuffer();
		for (int i = 0; i < count; i++) {
			tip.append(tipPrefix.trim());
		}
		return tip.toString();
	}

	public void init() {

	}

	public void destroy() {

	}
}
