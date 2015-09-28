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
	<body leftmargin="0" topmargin="0" style="overflow: auto">
  	<%
	//获取标签中使用的国际化资源信息
	String application_list = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_applicationList");
  	String url = "/menumanagement/menuManagementAction_querySystemApp.action?dates="+new Date();
	%>
	<w:tabPanel height="130%" id="applications_panel" width="100%">
	  <w:tabPage cache="false" id="appList" tabType="iframe" title="<%= application_list %>" 
	  	url="<%=url %>">
	  </w:tabPage>
	</w:tabPanel>
</body>
</html>