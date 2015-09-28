<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script src="/ReportServer?op=emb&resource=finereport.js&inter=zh_CN"></script>
    
    <title>无预览打印页面</title>
    
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
  <script type="text/javascript">
		<%--var url="/ReportServer?reportlet=<%ForUtil.outObj(out,request,"reportUrl")%>;--%>
	//	FR.doURLAppletPrint(url,false); //参数true表明不弹对话
		//FR.doURLFlashPrint(url,true);doURLAppletPrint
  </script>
    	
  </body>
</html>
