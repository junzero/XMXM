<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.fr.function.ParamLongStr"%>
<%@ page import="com.gotop.util.security.ForUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common.jsp"%>
<html>
  <head>
    <h:script src="/ReportServer?op=emb&resource=finereport.js"/>
    
    <title>My JSP 'c_print.jsp' starting page</title>
    
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
  <%
  	String key = session.getId()+"_"+System.currentTimeMillis();
	ParamLongStr.setKeyValue(key,request.getParameter("reportTxt"));
	String reportUrl = (String)request.getParameter("reportUrl");
  %>
  <iframe id="ifre" style="display: none;"></iframe>
  <script type="text/javascript">
<%-- 		var ifre = document.getElementById("ifre");
  		if(ifre.contentDocument){}else{//QQ浏览器7.3/baidu浏览器2/世界之窗    bug
	  		$.browser.version =0;
  		}
  		
  	    var vKey = '<%ForUtil.outObj(out,key)%>';
  	    var reportUrl = '<%ForUtil.outObj(out,reportUrl)%>';
		var url="/ReportServer?reportlet="+reportUrl+"&vKey="+vKey;
		var vDyfs = '1';
		if(vDyfs == 1){
			FR.doURLFlashPrint(url,1); //参数false表明不弹对话
		}else{
			FR.doURLAppletPrint(url,false); //参数false表明不弹对话
		} --%>
  </script>
  </body>
</html>
