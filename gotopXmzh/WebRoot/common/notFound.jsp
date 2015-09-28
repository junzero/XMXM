<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
 <%String v=request.getRequestURL().toString();String m=(String)request.getAttribute("javax.servlet.error.message");
         %>


<html>
<head>
<title>页面未找到</title>
</head>
<body>
<table width="100%" height="100%" bgcolor="#FFFFFF">
	<tr height="80%">
		<td width="100%" height="80%" align="center" style="padding-top:-60px">
		<table border="0" width="575px" height="111px"
			style="background:url(<%ForUtil.outObj(out,request.getContextPath());%>/common/skins/skin0/images/basic/warn.jpg)">
			<tr height="25px" >
				<td width="80px"style="padding-left:10px;font:12px">提示信息</td>
				<td ></td>
				<td></td>
			</tr>
		    <tr >
				<td></td>
				<td id="msg" style="font:16px;word-break:break-all">您访问的地址:
				<%
				  if(m.endsWith(".jsp")) ForUtil.outObj(out,v.replaceAll("/common/notFound.jsp",m));
				  else
					  ForUtil.outObj(out,v.replaceAll(request.getContextPath()+"/common/notFound.jsp",m));
				  %>
				不存在.</td>
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
<script>
if(isFF){
msg.innerHTML="<div id='msg1' style='overflow-X:auto;width:450px;white-space:nowrap>"+msg.innerHTML+"</div>";
wordbreak(msg1);
}
function wordbreak(x)
{
 var oldStr=x.innerHTML
 var width=x.offsetWidth
 x.innerHTML="";
 for(var i=0;i<oldStr.length;i++)
 {
  var str=x.innerHTML;
  s=oldStr.charAt(i)
  x.innerHTML=str+s;
  if(x.scrollWidth>width) {x.innerHTML=str+"<br/>"+s;}
 }
}
</script>
</body>
</html>