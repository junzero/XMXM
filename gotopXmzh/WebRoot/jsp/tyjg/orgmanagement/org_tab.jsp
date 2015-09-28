<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      机构信息TAB
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body style="margin: 0px;">
  	<e:datasource name="tomorganization" type="bean" path="com.gotop.tyjg.orgmanagement.model.Tomorganization"/>
  	<w:tabPanel defaultTab="modifySelfOrg" id="tabPan" height="100%" width="100%">
  		<w:tabPage cache="false" id="modifySelfOrg" tabType="iframe" title="机构信息" 
  			url="/orgmanagement/organizationAction_querySingleOrgInfo.action">
  			<h:param property="tomorganization.orgId" value="${param.orgid}"/>
		</w:tabPage>
  		<w:tabPage cache="false" id="maintainSubOrg" tabType="iframe" title="下级机构" 
  		url="/orgmanagement/organizationAction_queryOrgAllChildOrg.action">
  			<h:param property="tomorganization.orgId" value="${param.orgid}"/>
		</w:tabPage>
  	</w:tabPanel>
  </body>
</html>
