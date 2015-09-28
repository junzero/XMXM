<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp"%>
<%@page import="java.util.*"%>
<%@page import="com.gotop.vo.system.MUOUserSession"%>
<%@page import="com.gotop.util.Global"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script
	src="<%=request.getContextPath()%>/common/gotop/jquery.min.js"></script>
<title>选择下一节点办理人</title>
</head>
 <body style="margin: 0px;">
  	<table align="left" border="0" id="app_table" height="100%" width="100%" cellpadding="0" cellspacing="0">
		<tr>
					<!-- 流程定义id -->
					<input type="hidden" id="definitionId" name="definitionId" value="<b:write property="taskAssgineeDto/definitionId"/>">
					<!-- 流程实例id -->
					<input type="hidden" id="executionId" name="executionId" value="<b:write property="taskAssgineeDto/executionId"/>">
					<!-- 当前节点id -->
					<input type="hidden" id="nextTaskId" name="nextTaskId" value="<b:write property="taskAssgineeDto/nextTaskId"/>">
					<!-- 上个节点id -->
					<input type="hidden" id="preTaskId" name="preTaskId" value="<b:write property="taskAssgineeDto/preTaskId"/>">
				
					<input type="hidden" id="preTaskAssingee" name="preTaskAssingee" value="<b:write property="taskAssgineeDto/preTaskAssingee"/>">
						<!-- 提交人id -->
					<input type="hidden"   id="taskAssingee" name="taskAssingee"  value="<b:write property="taskAssgineeDto/taskAssingee"/>"/>
					<!-- 提交结构 -->
					<input type="hidden" id="preTaskOrg" name="preTaskOrg" value="<b:write property="taskAssgineeDto/preTaskOrg"/>">
					<!-- 提交时间 -->
					<input type="hidden" id="preTaskTime" name="preTaskTime" value="<b:write property="taskAssgineeDto/preTaskTime"/>">
					<!-- 业务主键 -->
					<input type="hidden" id="businessKey" name="businessKey" value="<b:write property="taskAssgineeDto/businessKey"/>">
					<!-- 业务类型 -->
					<input type="hidden" id="businessType" name="businessType" value="<b:write property="taskAssgineeDto/businessType"/>">
					
					
					<!-- 人员id -->
					<input type="hidden" id="empid" name="empid" value="<s:property value='empid'/>">
					<!-- 人员名称 -->
					<input type="hidden" id="empname" name="empname" value="<s:property value='empname'/>">
					<!-- 机构id -->
					<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>">
					<!-- 机构名称 -->
					<input type="hidden" id="orgname" name="orgname" value="<s:property value='orgname'/>">
				
					
					<input type="hidden" id="isEnd" name="isEnd" value="">
					<input type="hidden" id="taskType" name="taskType" value="">
					<!-- 查询到的empId -->
					<input type="hidden" id="empIds" name="empIds" value="">
					<input type="hidden" id="empNames" name="empNames" value="">
					<input type="hidden" id="beginOrg" name="beginOrg" value="<b:write property="taskAssgineeDto/beginOrg"/>">
					<input type="hidden" id="beginAssingee" name="beginAssingee" value="<b:write property="taskAssgineeDto/beginAssingee"/>">
					<input type="hidden" id="dynamicOrgIds" name="dynamicOrgIds" value="<b:write property="taskAssgineeDto/dynamicOrgIds"/>">
					<!-- 节点配置对象主键 -->
					<input type="hidden" id="taskExeConfigId" name="taskExeConfigId" value="">
			
					<!-- 流程走向名称 -->
					<input type="hidden" id="transitionName" name="transitionName" value="">
					<!-- 走向目标名称 -->
					<input type="hidden" id="destinationName" name="destinationName" value="">
					<!-- 其实目标名称 -->
					<input type="hidden" id="beforeName" name="beforeName" value="">
					
					
			<td id="taskList" valign="top" height="40%" width="30%">
			<w:panel id="er1">
				
					<l:iterate id="abc" property="activityList">
				<tr>
				<td>
					<input type="radio" name="aaa" onclick="getEmp('<b:write iterateId="abc" property="beforeName" />','<b:write iterateId="abc" property="transitionName" />','<b:write iterateId="abc" property="destinationName" />');" />
						<b:write iterateId="abc" property="actShowName" />
						</td>
					</tr>
					</l:iterate>
					
				</w:panel>
				</td>
			<td id="assigner" valign="top" width="50%" height="40%">
			<w:panel
					height="400" id="erer">
					<iframe frameBorder="0" style="width:100%;height:100%"
						scrolling="no" src="" name="assignerTab"  id="assignerTab"> </iframe>
				</w:panel></td>
		</tr>
		</table>
  </body>
<script type="text/javascript">
function getEmp(beforeName,transitionName,destinationName){
	$id("empIds").value="";
	$id("empNames").value="";
	$id("beforeName").value = beforeName;
	$id("transitionName").value = transitionName;
	//20140901 添加目标节点名称
	$id("destinationName").value = destinationName;
	var url=encodeURI("/jbpm/jbpmDemoAction_getNextTaskAssigneeConfig.action?taskAssgineeDto.executionId="+$id("executionId").value + "&taskAssgineeDto.beforeName="+beforeName+"&taskAssgineeDto.targetName="+destinationName + "&taskAssgineeDto.beginOrg=" + $id("beginOrg").value + "&taskAssgineeDto.beginAssingee=" + $id("beginAssingee").value) + "&taskAssgineeDto.dynamicOrgIds=" + $id("dynamicOrgIds").value + "&taskAssgineeDto.definitionId="+$id("definitionId").value    +"&taskAssgineeDto.taskAssingee=" + $id("taskAssingee").value;
	url=encodeURI(url);
	var myAjax = new Ajax(url);
	myAjax.submit();
	var rtun1 = myAjax.getValue("root/empIds");
	var rtun2 = myAjax.getValue("root/empNames");
	var rtun3 =  myAjax.getValue("root/taskType");
	var rtun4 =  myAjax.getValue("root/isEnd");
	var rtun5 =  myAjax.getValue("root/dynamicOrgIds");
	var rtun6 =  myAjax.getValue("root/taskExeConfigId");
	$id("taskExeConfigId").value=rtun6;
	$id("taskType").value=rtun3;
	//$id("dynamicOrgIds").value=rtun5;
	var strUrl = "";
	$id("isEnd").value=rtun4;
	$id("taskType").value=rtun3;
	if(rtun3 != null){
		if("09" == rtun3){
			strUrl = "/tree/initMainTree2_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2&changeTree.positioncode=BMFZR";		
		}else{
			strUrl = "/tree/initMainTree2_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2&changeTree.lookupTypeStr=2";
			$id("empIds").value = rtun1;
			$id("empNames").value = rtun2;
		}
	}else{
		if("end" == rtun4){
			//最后节点
			strUrl = "/tree/initMainTree2_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6";
		}else{
			//节点没配置
			strUrl = "/tree/initMainTree2_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2";
		}
	}
	$name("assignerTab").src=strUrl;
	
}

</script>
</html>