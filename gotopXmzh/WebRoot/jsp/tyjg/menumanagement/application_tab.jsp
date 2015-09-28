<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body style="margin: 0px;">
  	<w:tabPanel height="100%" id="application_panel" width="100%" defaultTab="applicationInfo">
	  <w:tabPage cache="false" id="applicationInfo" tabType="iframe" title="应用信息"
	  	 url="/menumanagement/menuManagementAction_querySingleApp.action">
	    <h:param  property = "acApplication.appId" value="${param.appid}"/>
	    <h:param  property = "acApplication.from" value="noClose"/>
	  </w:tabPage>
	  <w:tabPage cache="false" id="funcGroupList" tabType="iframe" title="功能组列表" 
	  	url="/menumanagement/menuManagementAction_queryAppChildGroupList.action">
	    <h:param  property = "acFunctionGroup.appId" value="${param.appid}"/>
	  </w:tabPage>
	</w:tabPanel>
  </body>
</html>
