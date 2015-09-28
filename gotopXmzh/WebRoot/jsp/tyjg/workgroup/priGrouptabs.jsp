<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<body leftmargin="0" topmargin="0" rightmargin="0">
<%
	String defaultTab="queryGroup";
	String nodeType="group";
	//处理默认tab页
	String inputTab=com.eos.foundation.data.DataContextUtil.getString("defaultTab");	
	if(inputTab!=null && !"".equals(inputTab)){
		defaultTab=inputTab;
	};	
	String inputType=com.eos.foundation.data.DataContextUtil.getString("nodeType");	
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
<h:hidden property="group/empid"/>
<l:equal value="<%=defaultTab%>"  targetValue="queryGroup" compareType="string"  >  
		<w:tabPage cache="false" id="queryGroup" tabType="iframe" title="群组查询" url="org.gocom.abframe.org.workgroup.priworkgroupManager.flow">
			<h:param name="_eosFlowAction" value="queryPriGroup" />		    <!--工作组查询 -->		
			<h:param property="grouptype" />		
		</w:tabPage>
</l:equal>  

<l:notEqual value="<%=defaultTab%>"  targetValue="queryGroup" compareType="string"  >  
	<l:equal value="<%=nodeType%>"  targetValue="group" compareType="string" > 
		<w:tabPage cache="false" id="modifySelfGroup" tabType="iframe" title="<%=basegroup%>" url="org.gocom.abframe.org.workgroup.WorkgroupManager.flow">
			<h:param name="_eosFlowAction" value="updateSelfGroup" />    <!--本级工作组 -->
			<h:param name="group/groupid" property="group/groupid" />
		</w:tabPage>
<%--		<w:tabPage cache="false" id="maintainSubGroup" tabType="iframe" title="<%=subgroup%>" url="org.gocom.abframe.org.workgroup.WorkgroupManager.flow">
			<h:param name="_eosFlowAction" value="querySubGroup" />     <!--下级工作组 -->
			<h:param name="group/groupid" property="group/groupid" />
		</w:tabPage>
		<w:tabPage cache="false" id="maintainSubPosi" tabType="iframe" title="<%=subposition%>" url="org.gocom.abframe.org.workgroup.WorkgroupManager.flow">
			<h:param name="_eosFlowAction" value="queryGroupPosi" />     <!--下级岗位 -->
			<h:param name="group/groupid" property="group/groupid" />
		</w:tabPage>--%>
		<w:tabPage cache="false" id="grouporgpinfo" tabType="iframe" title="机构信息" 	url="org.gocom.abframe.org.workgroup.WorkgroupManager.flow">
			<h:param name="_eosFlowAction" value="queryGroupOrg" />     <!--机构信息 -->
			<h:param name="group/groupid" property="group/groupid" />
		</w:tabPage>
		
		<w:tabPage cache="false" id="groupempinfo" tabType="iframe" title="<%=empInfo%>" 	url="org.gocom.abframe.org.workgroup.WorkgroupManager.flow">
			<h:param name="_eosFlowAction" value="queryGroupEmp" />     <!--人员信息 -->
			<h:param name="group/groupid" property="group/groupid" />
		</w:tabPage>
		<w:tabPage cache="false" id="groupemporginfo" tabType="iframe" title="人员/机构信息" 	url="org.gocom.abframe.org.workgroup.WorkgroupManager.flow">
			<h:param name="_eosFlowAction" value="queryEmpOrg" />     <!--人员信息 -->
			<h:param name="group/groupid" property="group/groupid" />
		</w:tabPage>
<%--		<w:tabPage cache="false" id="grouproleinfo" tabType="iframe" title="<%=roleInfo%>" url="org.gocom.abframe.org.workgroup.WorkgroupManager.flow">
			<h:param name="_eosFlowAction" value="manageGroupRole" />     <!--权限信息 -->
			<h:param name="oPartyrole/partytype" value="workgroup" />
			<h:param name="oPartyrole/partyid" property="group/groupid" />	
		</w:tabPage>--%>
	</l:equal> 	
	
	<l:equal value="<%=nodeType%>"  targetValue="position" compareType="string" > 
<%--		<w:tabPage cache="false" id="basepositioninfo" tabType="iframe" title="<%=basePosition%>" url="org.gocom.abframe.org.workgroup.WorkgroupManager.flow">
			<h:param name="_eosFlowAction" value="updatePosition" />     <!--本岗位信息 -->
			<h:param name="position/positionid" property="position/positionid" />
		</w:tabPage>--%>
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
