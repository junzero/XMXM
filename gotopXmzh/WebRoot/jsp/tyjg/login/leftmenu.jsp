<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      应用菜单
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
		body{
			/* background-image: url("/common/skins/default/images/sideline.gif"); */
		}
	</style>
  </head>
<body topmargin="0">
<h:script src="/js/tyjg/login/menu.js"/>
<r:rtree id="tree1" >
	<r:treeRoot action="/login/queryMenuRoot_login.action"  childEntities="map" display="应用菜单" icon="/common/skins/outlook/images/application_home.png" />
	<r:treeNode  nodeType="map" submitXpath="acfuncgroup" showField="FUNCNAME" icon="/common/skins/default/images/menu.gif"	childEntities="map" onClickFunc="menuClick" onRefreshFunc="treeNodeRefresh"	action="/login/queryMenuChild_login.action"/>
</r:rtree>
</body>
