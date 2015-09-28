<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>流程节点配置</title>
	</head>
	<body topmargin="0" leftmargin="0">
	<DIV class="divList">
			<w:panel id="panel" width="100%" title="流程节点列表">
				<viewlist id="e2c61865-3b56-470d-bd42-fff792fb9493">
				<h:form name="page_form"
					action="/jbpm/jbpmDemoAction_toProcessTaskConfig.action" method="post">
					<table align="center" border="0" width="100%" class="EOS_table">
						<h:hidden id="isUpdate" property="taskAssgineeDto.isUpdate" />  
						<tr>
							<th align="center" nowrap="nowrap">
								<b:message key="l_select"></b:message>
							</th>
							<th nowrap="nowrap">
								节点名称
							</th>
							<th nowrap="nowrap">
								节点对象
							</th>
						</tr>
					<w:radioGroup id="group1">
                          <l:iterate property="taskAssgineeDtos" id="id1">
							<tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />">
								<td align="center" nowrap="nowrap" width="5%">
								<w:rowRadio>
										<h:param name='taskName' iterateId='id1' property='taskName' />
										<h:param name='definitionId' iterateId='id1' property='definitionId' />
										<h:param name='objName' iterateId='id1' property='objName' />
									</w:rowRadio>
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="taskName" />
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="objName" />
								</td>
							</tr>
						</l:iterate>
					</w:radioGroup>
						<tr>
              <td colspan="23" class="command_sort_area">
							<div class="h3">
								<input type="button" class="button" value="节点对象配置"
									onclick="taskConfig();" />
									&nbsp; &nbsp;
									<input type="button" class="button" value="节点别名配置"
									onclick="taskOtherNameConfig();" />
									&nbsp; &nbsp;
								<input type="button" class="button" value="返回"
									onclick="backProcessConfig();" />
							</div>
              </td>
            </tr>
					</table>
				</h:form>
				</viewlist>
			</w:panel>		
		</DIV>
		<script type="text/javascript">

	  	function taskConfig(){
	  		var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("请选择一条节点信息");
	  			return;
	  		}else{
	  			var rows=gop.getSelectRow();
		  		var definitionId=rows.getParam("definitionId");
		  		var name = rows.getParam("taskName");
		  		var isUpdate = $id("isUpdate").value;
		  		 var strUrl = "";
		  		if(isUpdate == "isUpdate"){
		  			 strUrl = "/jbpm/jbpmDemoAction_toTaskConfigUpt.action?taskAssgineeDto.definitionId="+definitionId + "&taskAssgineeDto.taskName=" + name + "&taskAssgineeDto.isUpdate=" + isUpdate +"&date="+new Date();
				}else{
					 strUrl = "/jbpm/jbpmDemoAction_toTaskConfig.action?taskAssgineeDto.definitionId="+definitionId + "&taskAssgineeDto.taskName=" + name +"&date="+new Date();
				}
		  		strUrl = encodeURI(strUrl);
		  		showModalCenter(strUrl, null,  null, 800, 300, '流程节点配置'); 
			  	}
		 }

	  	//流程显示别名配置
	  	function taskOtherNameConfig(){
	  		var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("请选择一条节点信息");
	  			return;
	  		}else{
	  			var rows=gop.getSelectRow();
		  		var definitionId=rows.getParam("definitionId");
		  		var name = rows.getParam("taskName");
		  		var isUpdate = $id("isUpdate").value;
		  		 var strUrl = "";
		  		if(isUpdate == "isUpdate"){
		  			// strUrl = "/jbpm/jbpmDemoAction_toTaskConfigUpt.action?taskAssgineeDto.definitionId="+definitionId + "&taskAssgineeDto.taskName=" + name + "&taskAssgineeDto.isUpdate=" + isUpdate;
				}else{
					 strUrl = "/jbpm/jbpmDemoAction_toTaskOtherNameConfig.action?taskAssgineeDto.definitionId="+definitionId + "&taskAssgineeDto.taskName=" + name +"&date="+new Date();
				}
		  		strUrl = encodeURI(strUrl);
		  		showModalCenter(strUrl, null,  null, 800, 300, '流程节点别名配置'); 
			  	}
		}
	  	function backProcessConfig(){
	  		window.history.go(-1);
		 }

		</script>
	</body>
</html>