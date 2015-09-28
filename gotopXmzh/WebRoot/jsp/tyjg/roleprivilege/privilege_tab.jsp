<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
<title></title>
</head>
<body leftmargin="0" topmargin="0">
<% 
	//获取默认Tab项
	String str = request.getParameter("currentTab"); 
	//功能权限
	String func = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_title_funcPrivilege");
	//String data = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_title_dataPrivilege");
	//权限分配
	String assign = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_title_privilegeAssign");
	
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
<script>
   /*
    * 选中功能权限Tab
    */
   function selectFunc() {      
       parent.currentTab = "func";
   }
   /*
    * 选中权限分配Tab
    */
   function selectAssign() {         
       parent.currentTab = "assign";
   }
   /*
    * 选中权限分配Tab
    */
   function selectAcrole() {         
       parent.currentTab = "action0";
   }
	/*
	 * 初始化按钮样式以及调用各初始化函数
	 */ 
	function custInit() {
		hideProgressBar("doAction");
		unMaskTop();
	}
     //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
</script> 
<!-- defaultTab="@currentTab"  -->
<w:tabPanel id="roleAuth" defaultTab="@currentTab">
  <%if("0".equals(roleReadonly) || "2".equals(roleReadonly)){%>
  <w:tabPage id="func" tabType="iframe" title="<%=func %>"  url="/tyjg/roleprivilege/toManagePrivelege_acton.action" onSelectFunc="selectFunc">    <!-- 功能权限 -->
    <h:param name="acRole.roleId" property="acRole.roleId"/>
    <h:param name="acRole.auto" property="acRole.auto"/>
    <h:param name="acRole.roleType" property="acRole.roleType"/>
    <h:param name="roleReadonly" property="roleReadonly"/>
  </w:tabPage>
  <%}%> 
  <%if(!"2".equals(roleReadonly)){%>
  <w:tabPage id="assign" tabType="iframe" title="<%=assign %>"  url="/jsp/tyjg/roleprivilege/role_tab_frame.jsp" onSelectFunc="selectAssign">    <!-- 权限分配 -->
    <h:param name="acRole.roleId" property="acRole.roleId"/>
    <h:param name="acRole.roleType" property="acRole.roleType"/>
    <h:param name="acRole.auto" property="acRole.auto"/>
    <h:param name="roleReadonly" property="roleReadonly"/>
  </w:tabPage>
  <%}%>
  <%--<w:tabPage id="data" tabType="iframe" title="<%=data %>" url=""></w:tabPage>--%>    <!-- 数据权限 -->

<%-- 
  <w:tabPage id="acrole" tabType="iframe" title="批量赋权"  url="com.primeton.purview.AcRSubmit.flow" onSelectFunc="selectAcrole">    <!-- 批量赋权 -->
    <h:param name="acRole/roleid" property="acRole/roleid"/>
    <h:param name="acRole/appid" property="acRole/appid"/>
  </w:tabPage>  
  <w:tabPage id="evaluate" tabType="iframe" title="权限授予"  url="com.primeton.purview.evaluateRole.flow" onSelectFunc="selectAcrole">    <!-- 权限授予 -->
    <h:param name="acRole/roleid" property="acRole/roleid"/>
    <h:param name="acRole/appid" property="acRole/appid"/>
  </w:tabPage>
--%>
</w:tabPanel>
</body>
</html>