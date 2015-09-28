<%@include file="/common/common.jsp"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.Date" %>
<%@page import="com.eos.foundation.eoscommon.OnlineUserManagerUtil" %>
<%@include file="/common.jsp"%>
<html>
<head>
<title>Title</title>
<style>
body{margin: 0;padding: 0;font-size: small;}
</style>
</head>
<%
	Date date = new Date();
	request.setAttribute("date",date);
	String style_sysCssStr = "/common/skins/"+_headskin +"/theme/style-sys.css";
%>
<script language="JavaScript">
var flag = false;
function shift_status()
{   
	var pframe = parent.document.getElementById("frameMain");
    var prowsArra = pframe.rows.split(",");;
    if(flag){
    	prowsArra[3]=36;
    }else{
    	prowsArra[3]=10;
    }
	pframe.rows = prowsArra.toString();
	flag = !flag;
}
</script>
<h:css href='<%=style_sysCssStr %>'/>
<body background="images/bottom_2.jpg" onkeydown="if(event.keyCode==9){ event.keyCode=13;};return false;">
<table width="100%" height="20" border="0" cellspacing="0" cellpadding="0" bordercolordark="#FFFFFF" bordercolorlight="#FFFFFF" background="images/bot.jpg">
  <tr> 
    <td align="left" width="15%"><nobr>&nbsp;&nbsp;在线用户数：<font color=red>[<%=OnlineUserManagerUtil.getUserObjects().length%>]</font></nobr></td>
	<td align="center"><font color="#000000">中国邮政集团公司</font></td>
    <td align="right" width="15%"><nobr><b:write formatPattern="yyyy年MM月dd日" property="date"/>&nbsp;&nbsp;</nobr></td>
  </tr>
</table>
</body>
</html>