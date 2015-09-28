<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String cbasePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;width:100%;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/common.jsp"%>
		<script type="text/javascript"
			src="<%=cbasePath%>/js/test/jquery.panel.js"></script>
		<script type="text/javascript"
			src="<%=cbasePath%>/js/test/jquery.window.js"></script>
		<title>jQuery EasyUI test1111</title>
	</head>
	<script type="text/javascript">
	$(function(){
		$('#dd').resizable();
	})
	</script>
	<body>

<div id="dd" style="width:100px;height:100px;border:1px
solid #ccc;"></div>

	</body>
</html>
