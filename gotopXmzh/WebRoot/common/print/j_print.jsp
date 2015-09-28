<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <script type="text/javascript" src="/ReportServer?op=emb&resource=finereport.js&inter=zh_CN"></script>
    <script type="text/javascript" src="/common/gotop/applet_print.js"></script>
    
    <title>My JSP 'j_print.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <iframe id="ifre" style="display: none;"></iframe>
  <script type="text/javascript">
<%-- 		var ifre = document.getElementById("ifre");
  		if(ifre.contentDocument){}else{//QQ浏览器7.3/baidu浏览器2/世界之窗    bug
	  		$.browser.version =0;
  		}
  		var vDyfs = '1';
		var url="/ReportServer?reportlet=<%ForUtil.outPm(out,request,"reportUrl");%>&newdate="+new Date().getTime();
		if(vDyfs == 1){
			FR.doURLFlashPrint(url,1); //参数false表明不弹对话
		}else{
			FR_doURLAppletPrint('<%ForUtil.outObj(out,session.getId());%>','<%ForUtil.outObj(out,basePath);%>','<%ForUtil.outObj(out,request,"reportUrl");%>');
		} --%>
  </script>
    	
  </body>
</html>
