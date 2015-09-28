<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>我的草稿</title>
	</head>
	<body topmargin="0" leftmargin="0">
	<h:form name="appQuery"	action="/jbpm/tProcessTaskAssigneeAction_queryMyDraftsList.action" method="post">
		<w:panel id="panel1" title="我的草稿">
			<table align="center" border="0" width="100%" class="form_table">
			<tr>
					<td class="form_label" align="right" width="20%">流程名称：</td>
					<td colspan="1"  width="30%"><h:text id="processName" property="taskAssignee.processName" /></td>
					<td class="form_label" align="right" width="15%">工作事项类型：</td>
				<td colspan="1" width="30%">
					<d:select dictTypeId="ZHPT_BUSINESS_TYPE" id="businessType" property="taskAssignee.businessType" nullLabel="请选择"></d:select>
				</td>
				</tr>
				<tr class="form_bottom">
						<td colspan="4" class="form_bottom">
						    <b:message key="l_display_per_page"></b:message>
					        <h:text size="2" property="page.length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
					        <input type="hidden" name="page.begin" value="0">
					        <input type="hidden" name="page.isCount" value="true">
							<input id="querys" type="submit" value="查 询" class="button">
							<input type="button" value="清 空" class="button" onclick="clears();"></td>
					</tr>			
			</table>
		</w:panel>
	</h:form>
	<DIV class="divList">
			<w:panel id="panel" width="100%" title="查询结果">
				<viewlist id="e2c61865-3b56-470d-bd42-fff792fb9493">
				<h:form name="page_form"
					action="/jbpm/tProcessTaskAssigneeAction_queryMyDraftsList.action" method="post">
			<h:hiddendata property="taskAssignee.processName"/>
			<h:hiddendata property="taskAssignee.businessType"/>
            <h:hidden property="page.begin"/>
		    <h:hidden property="page.length"/>
		    <h:hidden property="page.count"/>
		    <h:hidden property="page.isCount"/>
					<table align="center" border="0" width="100%" class="EOS_table">
						<tr>
							<th align="center" nowrap="nowrap">
								<b:message key="l_select"></b:message>
							</th>
							<th nowrap="nowrap">
								标题
							</th>
							<th nowrap="nowrap">
								工作事项类型
							</th>
							<th nowrap="nowrap">
								流程名称
							</th>
							<th nowrap="nowrap">
								创建时间
							</th>
						</tr>
						<w:radioGroup id="group1">
                           <l:iterate property="processTaskAssignees" id="id1">
							<tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />">
								<td align="center" nowrap="nowrap">
									<w:rowRadio>
										<h:param name='id' iterateId='id1' property='id' />
										<h:param name='executionId' iterateId='id1' property='executionId' />
										<h:param name='taskId' iterateId='id1' property='taskId' />
										<h:param name='businessType' iterateId='id1' property='businessType' />
									</w:rowRadio>
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="businessTitle" />
								</td>
									<td nowrap="nowrap"> 
									<d:write dictTypeId="ZHPT_BUSINESS_TYPE"  property="businessType" iterateId="id1" />
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="processName" />
								</td>
									<td nowrap="nowrap">
									<b:write iterateId="id1" property="startTime" formatPattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
						</l:iterate>
						</w:radioGroup>
							<tr>
              <td colspan="23" class="command_sort_area">
              	<div class="h3"> 
							<l:greaterThan property="page.count" targetValue="0" compareType="number" >
								&nbsp; &nbsp;
									<input type="button" class="button" value="办理" onclick="goHandleTask();"/>
								</l:greaterThan>
								<l:greaterThan property="page.count" targetValue="0" compareType="number" >
								&nbsp; &nbsp;
									<input type="button" class="button" value="删除草稿" onclick="deleteDraft();"/>
								</l:greaterThan>
								<l:greaterThan property="page.count" targetValue="0" compareType="number" >
								&nbsp; &nbsp;
									<input type="button" class="button" value="查看流程图" onclick="viewTaskProcess();"/>
								</l:greaterThan>
								</div>
                <div class="h4">
	                <l:equal property="page.isCount" targetValue="true" >
	                  <b:message key="l_total"></b:message>
	                  <b:write property="page.count" />
	                  <b:message key="l_recordNO."></b:message>
	                  <b:write property="page.currentPage" />
	                  <b:message key="l_page"></b:message>
	                  <b:write property="page.totalPage" />
	                  <b:message key="l_page"></b:message>
	                </l:equal>
	                <l:equal property="page.isCount" targetValue="false" >
	                  <b:message key="l_NO."></b:message>
	                  <b:write property="page.currentPage" />
	                  <b:message key="l_page"></b:message>
	                </l:equal>
	                <input type="button" class="button" onclick="firstPage('page', '', null, null, 'page_form');" value='<b:message key="l_firstPage"></b:message>'  <l:equal property="page.isFirst"  targetValue="true">disabled</l:equal> >
	                <input type="button" class="button" onclick="prevPage('page', '', null, null, 'page_form');" value='<b:message key="l_upPage"></b:message>' <l:equal property="page.isFirst"  targetValue="true">disabled</l:equal> >
	                <input type="button" class="button" onclick="nextPage('page', '', null, null, 'page_form');" value='<b:message key="l_nextPage"></b:message>' <l:equal property="page.isLast"  targetValue="true">disabled</l:equal> >
	                <l:equal property="page.isCount" targetValue="true">
	                  <input type="button" class="button" onclick="lastPage('page', '', null, null, 'page_form');" value='<b:message key="l_lastPage"></b:message>' <l:equal property="page.isLast"  targetValue="true">disabled</l:equal> >
	                </l:equal>
              </div>
              </td>
            </tr>
					</table>
				</h:form>
				</viewlist>
			</w:panel>		
		</DIV>
		<script type="text/javascript">
		function goHandleTask(){
			var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("请选择一条草稿信息");
	  			return;
	  		}else{
		  		var rows=gop.getSelectRow();
		  		var executionId = rows.getParam("executionId");
		  		var taskId = rows.getParam("taskId");
		  		var businessType = rows.getParam("businessType");
		  		var strUrl = "/jbpm/jbpmDemoAction_handle.action?"+"&taskAssgineeDto.executionId=" + executionId +"&taskAssgineeDto.nextTaskId=" + taskId +"&taskAssgineeDto.businessType=" + businessType+"&taskAssgineeDto.preTaskAssingee="+'${sessionScope.login_user.empid}';
		  		/* parent.window.frames["mainFrame"].location.href = encodeURI(strUrl); */	
		  		/**
				* 2014.9.1 改为弹出框
				**/
		  		showModalCenter(encodeURI(strUrl), null, callBack, clientX*0.8, clientY*0.65, '草稿信息');
			}
			}
	  	function callBack(){
	  		location.reload();
	  	}

		function deleteDraft(){
			var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("请选择一条草稿信息");
	  			return;
	  		}else{
		  		var rows=gop.getSelectRow();
		  		var executionId = rows.getParam("executionId");
		  		$.ajax({
			        url: "/jbpm/jbpmDemoAction_deleteDraft.action?"+"&taskAssgineeDto.executionId=" + executionId,
			        async: false,
			        type: 'post',
			        data: "",
			        dataType: 'text',
			        timeout: 60000,
			        success: function (data) {
			        	if(data.indexOf("success")>=0){
      					  	alert("删除草稿成功!");
      					  window.location.reload();
  					  	}else if(data.indexOf("fails")>=0){
      					  	alert("删除草稿失败!");
  					  	}else{	 
  					  	    alert("操作失败!"); 
  					  	}
			        }
		      });
			}
		}

		function viewTaskProcess(){
	  		var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("请选择一条草稿信息");
	  			return;
	  		}else{
	  			var rows=gop.getSelectRow();
		  		var executionId = rows.getParam("executionId");
		  		var strUrl = "/jbpm/jbpmDemoAction_viewTask.action?executionId="+executionId;
		  		showModalCenter(strUrl, null, null, clientX*0.8, clientY*0.65, '当前流程进度'); 
			  	}
		  	}
		</script>
	</body>
</html>