package com.gotop.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.gotop.util.Global;

public class ReloginFilter implements Filter {
	private String includeString = ".jsp";

	public void destroy() {
	}

	public void init(FilterConfig config) throws ServletException {
		this.includeString = config.getInitParameter("include");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpreq = (HttpServletRequest) request;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(
				(HttpServletResponse) response);
		Object o = httpreq.getSession().getAttribute(Global.LOGON_USER_KEY);
		String path = httpreq.getRequestURL().toString().toLowerCase();
//		String[] includeList = includeString.split(";");
		String loginUrl = "login";
		// 判断有没有带后缀名的访问.
		if (path.indexOf(loginUrl)<0) {
			if (o == null && path.indexOf(includeString)>-1) {
				wrapper.sendRedirect("/jsp/tyjg/login/temp_login.jsp");
			} else
				chain.doFilter(request, response);
		} else
			chain.doFilter(request, response);
	}

	public static boolean isContains(String container, String[] regx) {
		container = container.toUpperCase();
		boolean result = false;
		for (int i = 0; i < regx.length; i++) {
			if (container.indexOf(regx[i].toUpperCase()) != -1) {
				return true;
			}
		}
		return result;
	}
}
