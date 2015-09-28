<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common.jsp"%>
<% 
   String topJspStr = contextPathStr+"/common/skins/"+_headskin +"/top.jsp";
   String toolbarJspStr = contextPathStr+"/common/skins/"+_headskin +"/toolbar.jsp";
   String slideJspStr = contextPathStr+"/common/skins/"+_headskin +"/slide.jsp";
   String bodyJspStr = contextPathStr+"/common/skins/"+_headskin +"/body.jsp";
   String copyrightJspStr = contextPathStr+"/common/skins/"+_headskin +"/copyright.jsp";
%>
<html>
<head>
<title>ABFrame System</title>
</head>
<frameset rows="50,21,*,19" frameborder="no" border="0" framespacing="0">
	<frame src='<%=topJspStr %>' scrolling="NO" frameborder="0" name="topFrame"	noresize />
	<frame src='<%=toolbarJspStr %>' scrolling="NO"name="toolbarFrame" noresize />

	<frameset cols="200,10,*" frameborder="0"  border="0" framespacing="0"	id="contentFrame">
		<frame src='org.gocom.abframe.auth.Frame.flow' name="menuFrame" />
		<frame src='<%=slideJspStr %>' name="slideFrame" noresize="noresize" scrolling="no"/>
		<frame src='<%=bodyJspStr %>' name="bodyFrame" />
	</frameset>
	<frame src='<%=copyrightJspStr %>' scrolling="no" name="copyrightFrame" />
</frameset>
<body>
<b:message key="mframesetsupport" />
</body>
</html>
