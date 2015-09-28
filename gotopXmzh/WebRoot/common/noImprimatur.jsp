<!-- saved from url=(0022)http://internet.e-mail -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.gotop.util.security.ForUtil"%>
<html>
<head>
<title>noImprimatur</title>
</head>
<body>
<table width="100%" height="100%" bgcolor="#FFFFFF">
	<tr height="80%">
		<td width="100%" height="80%" align="center" style="padding-top:-60px">
		<table border="0" width="575px" height="111px"
			style="background:url(<%ForUtil.outObj(out,request.getContextPath());%>/common/skins/skin0/images/basic/warn.jpg)">
			<tr height="25px" >
				<td width="80px"style="padding-left:10px;font:12px">提示信息</td>
				<td></td>
				<td></td>
			</tr>
		    <tr >
				<td></td>
				<td style="font:16px">您没有使用<%ForUtil.outPm(out,request,"module"); %>的许可!</td>
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
