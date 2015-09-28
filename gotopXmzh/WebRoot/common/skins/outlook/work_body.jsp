<%@include file="/common.jsp"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String work_body_leftTreeJspStr = contextPathStr +"/common/skins/outlook/work_body_leftTree.jsp";
	String tool_midJspStr = contextPathStr +"/common/skins/outlook/tool_mid.jsp";
%>

<html>

<frameset rows="*" cols="180,10,*" framespacing="0" frameborder="no" border="0" id="oa_frame">
	<frame src="<%=work_body_leftTreeJspStr %>" name="leftFrame" noresize>
	<frame src="<%=tool_midJspStr %>" scrolling="no" name="middleqwe" noresize>
	<frame src="" name="bodyFrame">
</frameset>
</html>