<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@page import="java.util.*"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h:css href="/css/style1/style-custom.css"/>
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body topmargin="0" leftmargin="0">
   <w:panel id="panel" width="100%" title="节点列表">
				<viewlist id="e2c61865-3b56-470d-bd42-fff792fb9493">
				<h:form name="page_form"
					action="/jbpm/jbpmDemoAction_getActivitys.action" method="post">
					<table align="center" border="0" width="100%" class="EOS_table">
					<input id="processInstanceId" name="processInstanceId" value="<b:write property="processInstanceId"/>">
						<tr>
							<th align="center" nowrap="nowrap">
								<l:greaterThan property="page.count" targetValue="0" compareType="number">
									<h:checkbox id="xuanze" onclick="allItem();" />
								</l:greaterThan>
								<b:message key="l_select"></b:message>
							</th>
							<th nowrap="nowrap">
								节点名称
							</th>
						</tr>
						<w:checkGroup id="group1">
                           <l:iterate property="processDefinitions" id="id1">
							<tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />">
								<td align="center" nowrap="nowrap">
									<w:rowCheckbox>
										<h:param name='activityName' iterateId='id1' property='activityName' />
									</w:rowCheckbox>
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="activityName" />
								</td>
							</tr>
						</l:iterate>
						</w:checkGroup>
					</table>
				</h:form>
				</viewlist>
			</w:panel>	
</body>
<script type="text/javascript">
$(function(){
	var frm=$name("page_form");
	 frm.submit(); 
})
</script>
</html>
