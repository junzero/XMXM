<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>流程管理</title>
	</head>
	<body topmargin="0" leftmargin="0">
	<input type="button" id="btn" class="button" onclick="queryTasks();" value='查询'>
	<DIV class="divList">
			<w:panel id="panel" width="100%" title="个人待办列表">
				<viewlist id="e2c61865-3b56-470d-bd42-fff792fb9493">
				<h:form name="page_form"
					action="/jbpm/jbpmDemoAction_getProcessInstancesList.action" method="post">
					<h:hidden property="page.begin" />
					<h:hidden property="page.length" />
					<h:hidden property="page.count" />
					<h:hidden property="page.isCount" />
					<h:hiddendata property="integral" />
					<table align="center" border="0" width="100%" class="EOS_table">
						<tr>
							<th align="center" nowrap="nowrap">
								<l:greaterThan property="page.count" targetValue="0" compareType="number">
									<h:checkbox id="xuanze" onclick="allItem();" />
								</l:greaterThan>
								<b:message key="l_select"></b:message>
							</th>
							<th nowrap="nowrap">
								id
							</th>
							<th nowrap="nowrap">
								名称
							</th>
							<th nowrap="nowrap">
								ActivityName
							</th>
							<!-- <th nowrap="nowrap">
								创建时间
							</th> -->
							<th nowrap="nowrap">
								ExecutionId
							</th>
						</tr>
						<w:checkGroup id="group1">
                           <l:iterate property="tasks" id="id1">
							<tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />">
								<td align="center" nowrap="nowrap">
									<w:rowCheckbox>
										<h:param name='id' iterateId='id1' property='id' />
										<h:param name='name' iterateId='id1' property='name' />
										<h:param name='activityName' iterateId='id1' property='activityName' />
									<%-- 	<h:param name='createTime' iterateId='id1' property='createTime' /> --%>
										<h:param name='executionId' iterateId='id1' property='executionId' />
									</w:rowCheckbox>
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="id" />
								</td>
									<td nowrap="nowrap">
									<b:write iterateId="id1" property="name" />
								</td>
								<td nowrap="nowrap">
									<b:write iterateId="id1" property="activityName" />
								</td>
								<%-- 	<td nowrap="nowrap">
									<b:write iterateId="id1" property="createTime" />
								</td> --%>
								<td nowrap="nowrap">
									<b:write iterateId="id1" property="executionId" />
								</td>
							</tr>
						</l:iterate>
						</w:checkGroup>
						<tr>
							<td colspan="18" class="command_sort_area">
							 &nbsp;
								<l:greaterThan property="page.count" targetValue="0" compareType="number" >
								&nbsp; &nbsp;
								<input type="button" class="button" value="办理" onclick="doTask();"/>
								</l:greaterThan>
								<l:greaterThan property="page.count" targetValue="0" compareType="number" >
								&nbsp; &nbsp;
								<input type="button" class="button" value="查看流程图" onclick="viewProcess();"/>
								</l:greaterThan>
								<h4>
									<l:equal property="page.isCount" targetValue="true"> 共   <b:write
											property="page.count" /> 条记录 第 <b:write
											property="page.currentPage" />页/<b:write
											property="page.totalPage" />                页
                  </l:equal>
									<l:equal property="page.isCount" targetValue="false"> 第<b:write
											property="page.currentPage" />
                    页
                  </l:equal>
                  <l:equal property="page.isCount" targetValue="true">
									<input type="button" class="button"
										onclick="firstPage('page', 'pageQuery', null, null, 'page_form');"
										value="首页"
										<l:equal property="page.isFirst" targetValue="true">disabled</l:equal>>
									<input type="button" class="button"
										onclick="prevPage('page', 'pageQuery', null, null, 'page_form');"
										value="上页"
										<l:equal property="page.isFirst" targetValue="true">disabled</l:equal>>
									<input type="button" class="button"
										onclick="nextPage('page', 'pageQuery', null, null, 'page_form');"
										value="下页"
										<l:equal property="page.isLast" targetValue="true">disabled</l:equal>>
									<l:equal property="page.isCount" targetValue="true">
										<input type="button" class="button"
											onclick="lastPage('page', 'pageQuery', null, null, 'page_form');"
											value="尾页"
											<l:equal property="page.isLast" targetValue="true">disabled</l:equal>>
									</l:equal>
									</l:equal>
								</h4>
							</td>
						</tr>
					</table>
				</h:form>
				</viewlist>
			</w:panel>		
		</DIV>
		<script type="text/javascript">
/* 		$(function(){
			queryTasks()
		}) */
		function allItem(){
			var group =$id("group1");
			if(document.getElementById("xuanze").checked){
				group.selectAll();
			}else{
				group.disSelectAll();
			}
		}
		function queryTasks(){
			  var frm= $name("page_form");
		       frm.submit();
		 }
	  	function doTask(){
	  		var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("至少选一条待办事项");
	  			return;
	  		}else if(len>1){
		  		alert("只能选择一条待办记录");
		  		return false;
		  	}else{
		  		var rows = gop.getSelectRows();
		  		var id=rows[0].getParam("id");
		  		//var activityName = rows[0].getParam("activityName");
		  		//var myAjax = new Ajax("/jbpm/jbpmDemoAction_completeTask.action?taskId="+id);
				//myAjax.submit();
		  		var strUrl = "/jbpm/jbpmDemoAction_toTask.action?taskId="+id;
		  		showModalCenter(strUrl, null, null, 500, 400, '办理');  
				
			  	}
		  	}

	  	function viewProcess(){
	  		var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("至少选一条记录");
	  			return;
	  		}else if(len>1){
		  		alert("只能选择一条记录");
		  		return false;
		  	}else{
		  		var rows = gop.getSelectRows();
		  		var executionId=rows[0].getParam("executionId");
		  	 	var strUrl = "/jbpm/jbpmDemoAction_viewTask.action?executionId="+executionId;
		  		showModalCenter(strUrl, null, null, 800, 500, '流程图'); 
			  	}
		  	}
		</script>
	</body>
</html>