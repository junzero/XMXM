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
  </head>
  <body>
   <DIV style="width: 100%; height: 300px; OVERFLOW: auto">
			<w:panel id="panel" width="100%" title="请假审批单">
					<h:form name="data_form"
					action="/jbpm/jbpmDemoAction_completeTask.action?target=agree" method="post">
					<table align="center" border="0" width="100%" class="form_table" >
					<input id="taskId" name="taskId" type=hidden value="<s:property value='taskId'/>">
			<tr>
				<td class="form_label" width="15%"  align="right">申请人：</td>
				<td colspan="1" width="35%"><s:property value='userId'/></td>
			</tr>
			<tr>
				<td class="form_label"  align="right">请假原因：</td>
				<td colspan="3"><h:textarea style="width:99%" cols="3" rows="5"  property="reason"  value="<s:property value='reason'/>" validateAttr="allowNull=true"/></td>
			</tr>										
        <tr class="form_bottom">
			<td colspan="4"><input type="submit" value="同意"  class="button"  onclick="agree();"> <input type="button"
				value="不同意" onclick="disagree();" class="button"></td>
		</tr>
		</table>
				</h:form>
				</w:panel>
	</DIV>
  </body>
  <script type="text/javascript">
	function agree(){
		var frm=$name("data_form");
		 frm.submit();
		 window.close();
		}

	function disagree(){
		var taskId = "<s:property value='taskId'/>"
		var myAjax = new Ajax("/jbpm/jbpmDemoAction_completeTask.action?taskId="+taskId +"&target="+"disagree");
		myAjax.submit();
		 window.close();
		}
  </script>
</html>
