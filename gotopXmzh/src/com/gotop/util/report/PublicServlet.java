package com.gotop.util.report;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.struts.ContextLoaderPlugIn;

public class PublicServlet extends HttpServlet{

	private static WebApplicationContext ctx;
	
	protected WebApplicationContext getWebApplicationContext(HttpServletRequest request,HttpServletResponse response) {
		if (ctx == null)
			ctx = initWebApplicationContext(request,response);
		if (ctx == null)
			throw new IllegalStateException(
					"No WebApplicationContext found: no ContextLoaderListener registered?");
		else
			return ctx;
	}
	
	protected Object getBean(String name,HttpServletRequest request,HttpServletResponse response) {
		return getWebApplicationContext(request,response).getBean(name);
	}

	protected WebApplicationContext initWebApplicationContext(HttpServletRequest request,HttpServletResponse response)
			throws IllegalStateException {
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null,true, 8192, true);
		ServletContext sc = pageContext.getServletContext();
		WebApplicationContext wac = (WebApplicationContext) sc
				.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX);
		if (wac == null)
			wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		return wac;
	}
}
