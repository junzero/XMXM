<!-- saved from url=(0022)http://internet.e-mail -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp"%>

<html>
<head>
<title>访问并发超过限制!</title>
</head>
<body>
<script>
if(top!=window)top.location.href=window.location.href;
</script>
<table width="100%" height="100%" bgcolor="#FFFFFF">
	<tr height="80%">
		<td width="100%" height="80%" align="center" style="padding-top:-60px">
		<table border="0" width="575px" height="111px"
			style="background:url(<%=request.getContextPath() %>/common/skins/skin0/images/basic/warn.jpg)">
			<tr height="25px" >
				<td width="80px"style="padding-left:10px;font:12px">提示信息</td>
				<td></td>
				<td></td>
			</tr>
		    <tr >
				<td></td>
				<td style="font:16px">访问并发超过限制!</td>
				<td></td>
			</tr>
		</table>
		
		</td>
	</tr>
	<tr height="20%">
	<td>
	
	</td>
	</tr>
</table>

</body>
</html>