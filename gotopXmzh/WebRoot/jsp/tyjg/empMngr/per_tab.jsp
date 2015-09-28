<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%
	String sDeftab="queryCondOrg";	
	Object rootObj = com.eos.web.taglib.util.XpathUtil.getDataContextRoot("request", pageContext);
	String propertyValue=(String)com.eos.web.taglib.util.XpathUtil.getObjectByXpath(rootObj, "sDeftab");
	if(propertyValue!=null && !"".equals(propertyValue)){
		sDeftab=propertyValue;
	};
	
	//获取标签中使用的国际化资源信息
	String title=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_orgMng");
	String queryCondOrg=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_queryCondOrg");
	String queryCondEmp=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_queryCondEmp");
	String modifySelfOrg=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_modifySelfOrg");
	String maintainSubOrg=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_maintainSubOrg");
	String maintainSubPosi=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_maintainSubPosi");
	String maintainEmpOrg=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_maintainEmpOrg");
	String maintainPowerOrg=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_maintainPowerOrg");
	%>	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=title%></title>
<%
	String orgid =request.getParameter("orgId");
%>
<h:css href="/css/style1/style-custom.css"/>
</head>
<body topmargin="0" leftmargin="0">
 
  	<w:tabPanel defaultTab="modifySelfOrg" id="tabPan" height="100%" width="100%">
  		<w:tabPage cache="false" id="modifySelfOrg" tabType="iframe" title="人员信息" url="/jsp/tyjg/empMngr/emp_orgquery_result.jsp">
  			<h:param property="orgid" name="orgid" value="<%=orgid%>" />
		</w:tabPage>
  		<w:tabPage cache="false" id="maintainSubOrg" tabType="iframe" title="本级单位" url="/empMngr/queryOrgObjInfo_empMngr.action">
  			<h:param property="orgid" name="orgid" value="<%=orgid%>" />
		</w:tabPage>
  	</w:tabPanel>  
  
</body>
</html>
