<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.gotop.util.security.ForUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <script type="text/javascript" src="/ReportServer?op=emb&resource=finereport.js"></script>
    <script type="text/javascript" src="/common/gotop/applet_print.js"></script>
    <title>My JSP 'prrr.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
	  <script type="text/javascript">
			FR_doURLFlashPrint('<%ForUtil.outObj(out,session.getId());%>','<%ForUtil.outObj(out,basePath);%>','<%ForUtil.outPm(out,request,"reportUrl");%>');
	  </script>
  </body>
</html>
