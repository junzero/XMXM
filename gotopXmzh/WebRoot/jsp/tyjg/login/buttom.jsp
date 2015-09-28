<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      厦门综合管理系统
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
		.big{
			background-image: url("/common/skins/default/images/buttom.jpg");
			background-repeat: no-repeat;
			background-size: 100% 100%;
		}
		body{
			background-image: url("${pageContext.request.contextPath}/common/skins/default/images/main_2_bg.png");
		}
	</style>
  </head>
<body class="m0 mt0 w">
<div class="h18 tc fb  big w mt0">厦门分行综合管理系统</div>
   
</body>
  </html>