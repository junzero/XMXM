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
      顶部图片
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
		body{
			background-image: url("${pageContext.request.contextPath }/common/skins/default/images/main_2_bg.png");
		}
	</style>
  </head>
<body class="bc4 w mt0 m0">
  	<input type="hidden" id="msess" name="msess" value="${sessionScope.login_session_id}"/>
  	<img src="${pageContext.request.contextPath }/common/skins/default/images/main_2.png">   
  </body>
</html>
