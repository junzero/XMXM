<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<script type="text/javascript" src="/js/commonUtil.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>我的群组列表</title>
	</head>
	<body topmargin="0" leftmargin="0">
	<h:form name="appQuery"	action="/group/tGroupAction_myGroupList.action" method="post">
		<w:panel id="panel1" title="我的群组列表">
			<table align="center" border="0" width="100%" class="form_table">
				<tr>
					<td class="form_label" align="right" width="20%">群组名称：</td>
					<td colspan="1"  width="30%"><h:text id="groupName" property="group.groupName" /></td>
				</tr>
				<tr class="form_bottom">
						<td colspan="4" class="form_bottom">
						    <b:message key="l_display_per_page"></b:message>
					        <h:text size="2" property="page.length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
					        <input type="hidden" name="page.begin" value="0">
					        <input type="hidden" name="page.isCount" value="true">
							<input id="querys" type="submit" value="查 询" class="button">
							<input type="button" value="清 空" class="button" onclick="clears();">
							</td>
					</tr>			
			</table>
		</w:panel>
	</h:form>
	<DIV class="divList">
			<w:panel id="panel" width="100%" title="查询结果">
				<viewlist id="e2c61865-3b56-470d-bd42-fff792fb9493">
				<h:form name="page_form"
					action="/group/tGroupAction_myGroupList.action" method="post">
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
								群组名称
							</th>
							<th nowrap="nowrap">
								群组描述
							</th>
							<th nowrap="nowrap">
								是否全行应用
							</th>
						</tr>
						<w:radioGroup id="group1">
                            <l:iterate property="groups" id="id1">
							 <tr class="<l:output oddOutput="EOS_table_row_o" evenOutput='EOS_table_row' />">
								<td align="center" nowrap="nowrap">
									<w:rowRadio>
											<h:param name='recId' iterateId='id1' property='recId' />
											<h:param name='createRole' iterateId='id1' property='createRole' />
											<h:param name='isWholebank' iterateId='id1' property='isWholebank' />
									</w:rowRadio>
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="groupName" />
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="groupDetail" />
								</td>
								<td nowrap="nowrap"> 
								     <d:write dictTypeId="ZHPT_GROUP_ISWHOLE" property="isWholebank" iterateId="id1" />
								</td>
							</tr>
						</l:iterate>
						</w:radioGroup>
							<tr>
              <td colspan="23" class="command_sort_area">
							<div class="h3">
							<input type="button" class="button" value="新增"
										onclick="addGroup();" />
								<l:greaterThan property="page.count" targetValue="0"
									compareType="number">
							<input type="button" class="button" value="修改"
										onclick="uptGroup();" />
								</l:greaterThan>
								<l:greaterThan property="page.count" targetValue="0"
									compareType="number">
							<input type="button" class="button" value="删除"
										onclick="delGroup();" />
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
			//新增群组
			function addGroup(){
				  var url="/group/tGroupAction_toGroup.action";
				  showModalCenter(url, null,null, 700, 300, '新增群组');
			}

			//修改群组
			function uptGroup(){
				var gop = $id("group1");
		  		var len = gop.getSelectLength();
		  		if(len == 0){
		  			alert("请选择一个群组");
		  			return;
		  		}else{
		  			var rows=gop.getSelectRow();
			  		var recId=rows.getParam("recId");
		  			var strUrl = "/group/tGroupAction_toGroup.action?group.recId="+recId;
		  			showModalCenter(strUrl, null, null, 700, 300, '修改群组');  
			  	}
			}

			//删除群组
			function delGroup(){
				var gop = $id("group1");
		  		var len = gop.getSelectLength();
		  		if(len == 0){
		  			alert("请选择一个群组");
		  			return;
		  		}else{
		  			var rows=gop.getSelectRow();
			  		var recId=rows.getParam("recId");
			  		var createRole=rows.getParam("createRole");
			  		var isWholebank=rows.getParam("isWholebank");
			  		$.ajax({
					      url: "/group/tGroupAction_isSysadmin.action",
					      async: false,
					      type: 'post',
					      data: "",
					      timeout: 60000,
					      success: function (data) {
					    	  if (data.indexOf("success") >= 0) {
						    		 //当前登录者为系统管理员
						    		 deleteOper(recId);
							} else if (data.indexOf("false") >= 0) {
									//当前登陆者非系统管理员
									if(createRole == 'SYSADMIN' && isWholebank=='1'){
										alert('该群组为全行群组，只有系统管理员才执行删除操作');
										return;
									}else{
										deleteOper(recId);
									}
							} 
									  	
					      }
					}); 
			 		
			  	}
			}

			function deleteOper(recId){
				$.ajax({
				      url: "/group/tGroupAction_deleteGroup.action",
				      async: false,
				      type: 'post',
				      data: "group.recId="+recId,
				      timeout: 60000,
				      dataType:"text",
				      success: function (data) {
				    	  if (data.indexOf("success") >= 0) {
				    		  alert("删除群组成功");
				    		  window.location.reload();
						} else if (data.indexOf("fails") >= 0) {
							alert("删除群组失败!");
						} else {
							alert("操作失败!");
						}
								  	
				      }
				}); 
				}

			function clears(){
				$id("groupName").value="";
			}
		</script>
	</body>
</html>