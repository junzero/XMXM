<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<%
	String sDeftab="queryCondOrg";	
	Object rootObj = com.eos.web.taglib.util.XpathUtil.getDataContextRoot("request", pageContext);
	String propertyValue=(String)com.eos.web.taglib.util.XpathUtil.getObjectByXpath(rootObj, "sDeftab");
	if(propertyValue!=null && !"".equals(propertyValue)){
		sDeftab=propertyValue;
	};

	//人员基本信息
	String baseinfoTitle=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_modifyEmpOpr");
	//所属机构
	String empBelongTitle=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_maintainEmpBelong");
	//人员权限
	String rightTitle=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_maintainPowerEmp");
	
	
	String empid = request.getParameter("empid");
	String orgId = request.getParameter("orgId");
	String userid = request.getParameter("userid");
	
	String opType = request.getParameter("opType");
	if(!"1".equals(opType)){
		opType = "0";
	}
	request.setAttribute("opType",opType);
	
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=baseinfoTitle%></title>
</head>
<body topmargin="0" leftmargin="0">
<e:datasource name="omemployee" type="bean" path="com.gotop.vo.tyjg.Omemployee"/>
  	<w:tabPanel id="posiInfoTab" defaultTab="empBaseinfo" height="100%" width="100%">
  		<w:tabPage cache="false" id="empBaseinfo" tabType="iframe" title="基本信息" url="/empMngr/queryEmpBaseAndFjxx_empMngr.action">
  			<h:param property="empid" value="<%=empid %>"/>
  			<h:param property="orgId" value="<%=orgId %>"/>
  			<h:param property="userid" value="<%=userid %>"/>
  			<h:param property="execType" value="update"/>
		</w:tabPage>
  		<w:tabPage cache="false" id="maintainSubOrg" tabType="iframe" title="组织归属"  
  		url="/jsp/tyjg/empMngr/empopr_belong_maintain.jsp">
  			<h:param property="empid" value="<%=empid %>"/>
  			<h:param property="orgid" value="<%=orgId %>"/>
		</w:tabPage>
  	</w:tabPanel>
  
</body>
</html>
