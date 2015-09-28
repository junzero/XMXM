<%@include file="/common.jsp"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String initPanelJspStr = contextPathStr +"/common/skins/outlook/initPanel.jsp";
	String CopyrightJspStr = contextPathStr +"/common/skins/outlook/Copyright.jsp";
%>
<html>
<head>
<title>ABFrame System</title>
</head>
<frameset rows="70,*,19" frameborder="NO" border="0" framespacing="0">
  <frame src="org.gocom.abframe.auth.Frame.flow" name="topFrame" scrolling="NO" noresize/>
  <frame src="<%=initPanelJspStr %>" name="bodyFrame_all">
  <frame src="<%=CopyrightJspStr %>" scrolling="no">
</frameset>
</html>