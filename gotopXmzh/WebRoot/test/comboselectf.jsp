<%@page pageEncoding="UTF-8"%><%@page import="com.primeton.utils.ComboSelect_Data_Demo"%><%
	ComboSelect_Data_Demo cdd = new ComboSelect_Data_Demo();
	out.print(cdd.getDataStr(null));
%>
