<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      应用系统下功能组的TAB
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%
		//获取标签中使用的国际化资源信息
		String funcGroup_info = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_funcGroup_info");
	    String subFuncGroup_list = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_subFuncGroup_list");
	    String function_list = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_function_list");
	            
	%>
	</head>
	<body leftmargin="0" topmargin="0" rightmargin="0">
		<w:tabPanel height="100%" id="funcGroup_panel" width="100%">
		  <w:tabPage cache="false" id="funcGroupInfo" tabType="iframe" title="<%= funcGroup_info %>" url="/menumanagement/menuManagementAction_selectByGroupidInfo.action">
		    <h:param name="acFunctionGroup.funcGroupId" value="${param.funcgroupid}"/>
		  </w:tabPage>
		  <w:tabPage cache="false" id="sunFuncGroup" tabType="iframe" title="<%= subFuncGroup_list %>" url="/menumanagement/menuManagementAction_queryGroupChildGroupList.action">
		   <h:param name="acFunctionGroup.funcGroupId" value="${param.funcgroupid}"/>
		   <h:param name="acFunctionGroup.parentGroup" value="${param.funcgroupid}"/>
		   <h:param name="acFunctionGroup.appId" value="${param.appid}"/>
		   <h:param name="acFunctionGroup.funcGroupSeq" value="${param.seq}"/>
		   <h:param name="acFunctionGroup.groupLevel" value="${param.funcgrouplevel}"/>
		  </w:tabPage>
		  <w:tabPage cache="false" id="funcList" tabType="iframe" title="<%= function_list %>" url="/menumanagement/menuManagementAction_queryGroupFunMenu.action">
		    <h:param name="acFunction.funcGroupId" value="${param.funcgroupid}"/> 
		    <h:param name="acFunction.appId" value="${param.appid}"/>
		  </w:tabPage>
		</w:tabPanel>
	</body>
</html>
