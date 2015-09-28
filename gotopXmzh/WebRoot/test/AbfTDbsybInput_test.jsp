<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<%@page import="com.primeton.utils.Page"%>
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
      AbfTDbsyb录入
    </title>
  </head>
  <body>
  <%
  	Page pages = new Page();
  	pages.setLength(10);
  	request.setAttribute("pages",pages);
  %>
  <bean:write property="length" name="pages"/>--------
  <b:write property="pages/length"/>
  </body>
</html>
