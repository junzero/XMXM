<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0">
<%
	//获取默认Tab项
	String defaultTab="operatorQuery";
	//操作员
	String operator = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_title_operator");
	//机构
	String organization = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_title_organization");
	
	String roleReadonly = request.getParameter("roleReadonly");
	String roleId = request.getParameter("acRole.roleId");
	String roleType = request.getParameter("acRole.roleType");
	String auto = request.getParameter("acRole.auto");
	if(roleReadonly==null || "".equals(roleReadonly)){
		roleReadonly = "0";
	}
	request.setAttribute("acRole.roleId",roleId);
	request.setAttribute("acRole.roleType",roleType);
	request.setAttribute("acRole.auto",auto);
	request.setAttribute("roleReadonly",roleReadonly);
 %>
<w:tabPanel bodyStyle="" defaultTab="<%=defaultTab%>" height="100%" id="orgInfo" titleStyle="" width="100%">
  <w:tabPage cache="false" id="operatorQuery" tabType="iframe" title="<%=operator %>" url="/tyjg/roleprivilege/toDistributeOperatorRole_roleprivilege.action">    <!-- 操作员 -->

  	<h:param name="acRole.roleId" property="acRole.roleId"/>
  	<h:param name="acRole.roleType" property="acRole.roleType"/>
  	<h:param name="acRole.auto" property="acRole.auto"/>
  	<h:param name="roleReadonly" property="roleReadonly"/>
  </w:tabPage>
  <w:tabPage cache="false" id="orgQuery" tabType="iframe" title="<%=organization %>" url="/tyjg/roleprivilege/toDistributeOrgRole_roleprivilege.action">    <!-- 机构 -->

   <h:param name="acRole.roleId" property="acRole.roleId"/>
   <h:param name="acRole.roleType" property="acRole.roleType"/>
   <h:param name="acRole.auto" property="acRole.auto"/>
   <h:param name="roleReadonly" property="roleReadonly"/>
  </w:tabPage>

<%--  <w:tabPage cache="false" id="groupQuery" tabType="iframe" title="工作组" url="org.gocom.abframe.rights.role.AssignRole.flow">    <!-- 工作组 -->
  <h:param name="_eosFlowAction" value="groupQuery"/>
   <h:param name="acRole/roleid" property="acRole/roleid"/>   
  </w:tabPage>--%>
<%-- 
  <w:tabPage cache="false" id="positionQuery" tabType="iframe" title="<%=position %>" url="org.gocom.abframe.rights.role.AssignRole.flow">    <!-- 岗位 -->
  <h:param name="_eosFlowAction" value="positionQuery"/>
   <h:param name="acRole/roleid" property="acRole/roleid"/>   
  </w:tabPage>

  <w:tabPage cache="false" id="dutyQuery" tabType="iframe" title="<%=duty %>" url="org.gocom.abframe.rights.role.AssignRole.flow">    <!-- 职务 -->
  <h:param name="_eosFlowAction" value="dutyQuery"/>
   <h:param name="acRole/roleid" property="acRole/roleid"/>  
  </w:tabPage>
 --%>
</w:tabPanel>
</body>
</html>