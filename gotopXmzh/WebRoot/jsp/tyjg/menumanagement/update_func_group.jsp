<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      修改功能组
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  	<e:datasource name="acFunctionGroup" type="bean" path="com.gotop.tyjg.menumanagement.model.AcFunctionGroup"/>
  	<h:form name="updateFuncGroup" action="/menumanagement/menuManagementAction_updateGroupName.action" method="post" target="tempFrame">
		<h:hidden id = "funcGroup_id" property="acFunctionGroup.funcGroupId" value="${param.gruopId}"/>
		  <table align="center" border="0" width="100%" class="form_table">
		    <tr>
		      <td class="form_label">
		        <b:message key="applicationManager_AcFuncGroup.funcgroupname"></b:message><b:message key="l_colon"></b:message>
		      </td>
		      <td colspan="1">
		        <h:text property="acFunctionGroup.funcGroupName" value="${param.groupName}"/>
		      </td>
		      <td class="form_label">
		        <b:message key="applicationManager_AcFunction.parentFuncGroup"></b:message><b:message key="l_colon"></b:message>
		      </td>
		      <td colspan="3" width="30%">
		      	<%
		      		String parentGroupName = request.getParameter("parentGroupName");
		      		if(parentGroupName == null || "".equals(parentGroupName)){
		      	%>
		      		<%ForUtil.outPm(out,request,"appid");%>
		      	<%}else{ %>
		      		<%ForUtil.outPm(out,request,"parentGroupName");%>
		        <%} %>
		      </td>
		    </tr>
		    <tr class="form_bottom">
		      <td colspan="6">
		        <input type="submit" class="button" value='<b:message key="l_save"></b:message>'>
		        <input type="button" class="button" value='<b:message key="l_close"></b:message>' onclick="javascript:window.closeD();">
		      </td>
		    </tr>
		  </table>
	</h:form>
	<iframe id="tempFrame" name="tempFrame" style="display: none;"></iframe>
  </body>
</html>
