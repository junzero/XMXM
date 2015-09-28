<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html style="height:100%;width:100%;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=basePath%>/js/test/jquery.calendar.js"></script>
		<%@ include file="/common.jsp" %>
		<title>jQuery EasyUI calendar</title>
	</head>
<body>
	<h1>Calendar</h1>
	<div class="easyui-calendar" style="width:180px;height:180px;"></div>
</body>
</html>