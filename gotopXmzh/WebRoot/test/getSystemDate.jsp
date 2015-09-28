<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<%--
- Author(s): Administrator
- Date: 2011-10-29 22:26:27
- Description:
--%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
     	获到系统时间
    </title>
  </head>
  <body>
  	<h:text id="DateStyle" validateAttr="message=日期格式;allowNull=false" value="YYYY-MM-DD HH24:MI:SS" size="25"/><br/>
	<input type="button" value="获取服务器时间" onclick="getSystemDate()" class="button">
	<script>
	        function getSystemDate(){
	        	var myAjax = new Ajax("/richweb_demo.do?method=getSystemDate");
				myAjax.addParam("dateStyle", $id("DateStyle").value);
				myAjax.submit();
				var returnNode =myAjax.getText();
				alert(returnNode);
	}
	</script>
  </body>
</html>
