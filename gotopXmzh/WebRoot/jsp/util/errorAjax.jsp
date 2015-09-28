<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%><%@page import="com.gotop.util.exception.GotopException"%><%@page import="com.gotop.util.security.ForUtil"%><%
	Object eobj =request.getAttribute("_exception");
	if(eobj instanceof GotopException) {
		GotopException gen = (GotopException) eobj;
		String message = gen.getMessage();
		int forward = gen.getForward();
		message = message.replace("\n"," ").replace("'","\\'");
		ForUtil.outObj(out,"<root><exceptions><forward>"+forward+"</forward><exception><message>"+message+"</message></exception></exceptions></root>");
	}else{
		Object loginPage =request.getAttribute("loginPage");
		if(loginPage!=null){
			ForUtil.outObj(out,"<root><exceptions><invalid>true</invalid><loginPage>/</loginPage></exceptions></root>");
		}else{
			String message = eobj.toString();
			message = message.replace("\n"," ").replace("'","\\'");
			ForUtil.outObj(out,"<root><exceptions><exception><message>"+message+"</message></exception></exceptions></root>");
		}
	}
%>