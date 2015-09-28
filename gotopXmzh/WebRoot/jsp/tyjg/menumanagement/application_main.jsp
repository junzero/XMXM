<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      应用功能维护入口
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body style="margin: 0px;">
  	<table align="left" border="0" id="app_table" height="100%" width="100%" cellpadding="0" cellspacing="0">
		<tr>
		   <td id="frmAppFuncTree" valign="top" width="25%">
		      <IFRAME style="width:100%; height:100%" name="IFRAMEAppFuncTree" frameBorder=0 scrolling="no"
		              src="/jsp/tyjg/menumanagement/application_tree.jsp"> </IFRAME>
		   </td>
		   <td valign="top" width="75%">
		      <IFRAME style="width:100%;height:100%" name="IFRAMEAppFuncInfo" frameborder=0 scrolling="auto"
		              src="/jsp/tyjg/menumanagement/applications_tab.jsp"></IFRAME>
		   </td>
		</tr>
		</table>
  </body>
</html>
