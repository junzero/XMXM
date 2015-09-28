<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common.jsp"%>
<%
	String topJspStr = contextPathStr + "/common/skins/"+_headskin+"/top.jsp";
	String toolbarJspStr = contextPathStr + "/common/skins/"+_headskin+"/toolbar.jsp";
	String bodyJspStr = contextPathStr + "/common/skins/"+_headskin+"/body.jsp";
	String copyrightJspStr = contextPathStr + "/common/skins/"+_headskin+"/copyright.jsp";
%>
<html>
<head>
<title>Title</title>
</head>
<frameset rows="50,21,*,19" frameborder="no" border="0" framespacing="0">
	<frame src="<%=topJspStr %>" scrolling="NO" frameborder="0" name="topFrame"	noresize />
	<frame src="<%=toolbarJspStr %>" scrolling="NO"name="toolbarFrame" noresize />
    <frame src="<%=bodyJspStr %>" name="bodyFrame">
	<frame src="<%=copyrightJspStr %>" scrolling="no" name="copyrightFrame" />
</frameset>
<body>
<b:message key="mframesetsupport" />
</body>
</html>