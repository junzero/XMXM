<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h:css href="/css/style1/style-custom.css" />
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  <DIV style="width: 100%; height: 300px; OVERFLOW: auto">
			<w:panel id="panel" width="100%" title="请假申请单">
					<h:form name="data_form"
					action="/jbpm/jbpmDemoAction_completeTask.action" method="post">
					<table align="center" border="0" width="100%" class="form_table" onsubmit="return checkForm(this);">
					<input id="taskId" name="taskId" type="hidden" value="<s:property value='taskId'/>">
					<input id="userId" name="userId" type="hidden" value="<s:property value='userId'/>">
			<tr>
				<td class="form_label" width="15%"  align="right">申请人：</td>
				<td colspan="1" width="35%"><s:property value='userId'/></td>
			</tr>
			<tr>
				<td class="form_label"  align="right">请假原因：</td>
				<td colspan="3"><h:textarea style="width:99%" cols="3" rows="5"  property="reason" validateAttr="allowNull=true"/></td>
			</tr>										
        <tr class="form_bottom">
			<td colspan="4"><input type="button" value="提交"  onclick="addLedger()" class="button"> <input type="button"
				value="关闭" onclick="window.close();" class="button"></td>
		</tr>
		</table>
				</h:form>
				</w:panel>
	</DIV>
		<script type="text/javascript">
function addLedger(){
 /*  var frm=$name("data_form");
 if(!checkForm(frm)){
	 return ;
 } 
 frm.submit();
 window.close(); */
	
	showModalCenter("/jbpm/jbpmDemoAction_toTaskConfig.action?processInstanceId=<s:property value='processInstanceId'/>", null, null, 800, 500, '节点办理人选择');  

}
</script>
  </body>
</html>
