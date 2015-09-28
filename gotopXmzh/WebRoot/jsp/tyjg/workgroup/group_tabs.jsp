<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<body leftmargin="0" topmargin="0" rightmargin="0">
<%
	String defaultTab="queryGroup";
	String nodeType="group";
	//处理默认tab页
	String inputTab=request.getParameter("defaultTab");	
	if(inputTab!=null && !"".equals(inputTab)){
		defaultTab=inputTab;
	};	
	String inputType=request.getParameter("nodeType");	
	if(inputType!=null && !"".equals(inputType)){
		nodeType=inputType;
	};
	
	//获取标签中使用的国际化资源信息
	String groupQuery=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_groupQuery");
	String basegroup=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_basegroup");
	String subgroup=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_subgroup");
	String subposition=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_subposition");
	String empInfo=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_empInfo");
	String roleInfo=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_roleInfo");
 %>
<w:tabPanel bodyStyle="" defaultTab="<%=defaultTab%>" height="100%"  id="orgInfo" titleStyle="" width="100%">
<h:hidden property="group.empid"/>
<l:equal value="<%=defaultTab%>"  targetValue="queryGroup" compareType="string"  >  
		<w:tabPage cache="false" id="queryGroup" tabType="iframe" title="群组查询" url="/workgroup/groupmanagerAction_queryGroupViewList.action">
			<h:param name="_eosFlowAction" value="queryGroup" />		    <!--工作组查询 -->		
			<h:param property="grouptype" />		
		</w:tabPage>
</l:equal>  

<l:notEqual value="<%=defaultTab%>"  targetValue="queryGroup" compareType="string"  >  
	<l:equal value="<%=nodeType%>"  targetValue="group" compareType="string" > 
		<w:tabPage cache="false" id="modifySelfGroup" tabType="iframe" title="<%=basegroup%>" url="/workgroup/groupmanagerAction_updateSelfGroupViewList.action">
			<h:param name="_eosFlowAction" value="updateSelfGroup" />    <!--本级工作组 -->
			<h:param name="group.groupid" property="group.groupid" />
		</w:tabPage>
		<w:tabPage cache="false" id="groupemporginfo" tabType="iframe" title="群组里人员/机构/角色" 	url="/workgroup/groupmanagerAction_queryPositionEmpViewList.action">
			<h:param name="_eosFlowAction" value="queryGroupEmpOrg" />     <!--人员信息 -->
			<h:param name="group.groupid" property="group.groupid" />
		</w:tabPage>
	</l:equal> 	
	
	<l:equal value="<%=nodeType%>"  targetValue="position" compareType="string" > 
		<w:tabPage cache="false" id="positionempinfo" tabType="iframe" title="<%=empInfo%>" 	url="org.gocom.abframe.org.workgroup.WorkgroupManager.flow">
			<h:param name="_eosFlowAction" value="queryPositionEmp" />     <!--人员信息 -->
			<h:param name="position/positionid" property="position/positionid" />
		</w:tabPage>
		<w:tabPage cache="false" id="positionroleinfo" tabType="iframe" title="<%=roleInfo%>" url="org.gocom.abframe.org.workgroup.WorkgroupManager.flow">
			<h:param name="_eosFlowAction" value="managePositionRole" />     <!--权限信息 -->
			<h:param name="oPartyrole/partytype" value="position" />
			<h:param name="oPartyrole/partyid" property="position/positionid" />
		</w:tabPage>
	</l:equal>	
</l:notEqual> 
</w:tabPanel>

</body>
</html>
