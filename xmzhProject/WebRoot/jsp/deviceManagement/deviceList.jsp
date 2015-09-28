<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<script type="text/javascript" src="/js/commonUtil.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>设备列表</title>
	</head>
	<body topmargin="0" leftmargin="0">
	<h:form name="query_form"	action="/deviceManagement/deviceManagementAction_deviceList.action" method="post">
	<!-- 	<h:hidden property="device.useful" id="useful" /> -->
		<w:panel id="panel1" title="设备列表">
			<table align="center" border="0" width="100%" class="form_table">
				<tr>
					<td class="form_label" align="right" width="20%">机构/部门</td>
					<td colspan="1"  width="30%">
						<h:text id="orgname" property="device.orgname"   readonly="true"  />
			            <h:hidden id="orgcode" property="device.orgcode" />
			      		<a href="#" onclick="open_newyw_tree_fun1();">选择</a>
					</td>
					<td class="form_label" align="right" width="20%">设备名称：</td>
					<td colspan="1"  width="30%">
						<d:select  id="deviceName"  dictTypeId="DEVICE_NAME" property="device.deviceName" nullLabel="请选择"></d:select>
					</td>
				</tr>
				<tr>
					<td class="form_label" align="right" width="20%">设备型号：</td>
					<td colspan="1"  width="30%">
						<d:select  id="deviceModel"  dictTypeId="DEVICE_MODEL" property="device.deviceModel" nullLabel="请选择"></d:select>
					</td>
					<td class="form_label" align="right" width="20%">设备状态：</td>
					<td colspan="1"  width="30%">
						<d:select  id="deviceState"  dictTypeId="DEVICE_STATE" property="device.deviceState" nullLabel="请选择"></d:select>
					</td>
				</tr>
				<tr>
					<td class="form_label" align="right" width="20%">内存：</td>
					<td colspan="1"  width="30%">
						<h:text id="memoryMin" property="device.memoryMin"  size="2"  />G
						＜内存容量(G)＜
						<h:text id="memoryMax" property="device.memoryMax"  size="2" />G
					</td>
					<td class="form_label" align="right" width="20%">硬盘：</td>
					<td colspan="1"  width="30%">
						<h:text id="hardDiskMin" property="device.hardDiskMin"  size="2" />T
						＜硬盘容量(T)＜
						<h:text id="hardDiskMax" property="device.hardDiskMax" size="2"  />T
					</td>
				</tr>
				<tr>
					<td class="form_label" align="right" width="20%">操作系统版本：</td>
					<td colspan="1"  width="30%">
						<d:select  id="osVersion"  dictTypeId="DEVICE_OS_VERSION" property="device.osVersion" nullLabel="请选择"></d:select>
					</td>
					<td class="form_label" align="right" width="20%">内置软件版本：</td>
					<td colspan="1"  width="30%">
						<h:text id="softwareVersion" property="device.softwareVersion"   />
					</td>
				</tr>
				<tr>
					<td class="form_label" align="right" width="20%">IE版本：</td>
					<td colspan="1"  width="30%">
						<d:select  id="ieVersion"  dictTypeId="DEVICE_IE_VERSION" property="device.ieVersion" nullLabel="请选择"></d:select>
					</td>
					<td class="form_label" align="right" width="20%">用途：</td>
					<td colspan="1"  width="30%">
					<d:checkbox id="useful"  dictTypeId="DEVICE_USEFUL" property="device.useful" />
					<!-- 
					<l:iterate property="usefuls" id="id1" indexId="index">
							<input type="checkbox" name="useful" value='<b:write iterateId="id1" property="DICTID" />'><b:write iterateId="id1" property="DICTNAME" />&nbsp;&nbsp;
					</l:iterate>
					 -->
					</td>
				</tr>
				<tr>
					<td class="form_label" align="right" width="20%">安装的插件：</td>
					<td colspan="1"  width="30%">
						<d:checkbox  id="plugIn"  dictTypeId="DEVICE_PLUGIN" property="device.plugIn"  />
					</td>
					<td class="form_label" align="right" width="20%">对应的外设：</td>
					<td colspan="1"  width="30%">
						<d:checkbox  id="peripheral"  dictTypeId="DEVICE_PERIPHERAL" property="device.peripheral"  />
					</td>
				</tr>
				<tr class="form_bottom">
						<td colspan="4" class="form_bottom">
						    <b:message key="l_display_per_page"></b:message>
					        <h:text size="2" property="page.length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
					        <input type="hidden" name="page.begin" value="0">
					        <input type="hidden" name="page.isCount" value="true">
							<input id="querys" type="button" value="查 询" class="button" onclick="mysubmit()">
							<input type="button" value="清 空" class="button" onclick="clears();">
							</td>
					</tr>			
			</table>
		</w:panel>
	</h:form>
	<DIV class="divList" >
			<w:panel id="panel" width="100%" title="查询结果">
				<viewlist id="e2c61865-3b56-470d-bd42-fff792fb9493">
				<h:form name="page_form"
					action="/deviceManagement/deviceManagementAction_deviceList.action" method="post">
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
								机构/部门
							</th>
							<th nowrap="nowrap">
								设备名称
							</th>
							<th nowrap="nowrap">
								型号
							</th>
							<th nowrap="nowrap">
								IP地址
							</th>
							<th nowrap="nowrap">
								生产机器名称
							</th>
							<th nowrap="nowrap">
								CPU型号
							</th>
							<th nowrap="nowrap">
								内存容量(G)
							</th>
							<th nowrap="nowrap">
								硬盘容量(T)
							</th>
							<th nowrap="nowrap">
								操作系统版本
							</th>
							<th nowrap="nowrap">
								内置软件版本
							</th>
							<th nowrap="nowrap">
								IE版本
							</th>
							<th nowrap="nowrap">
								用途
							</th>
							<th nowrap="nowrap">
								终端号
							</th>
							<th nowrap="nowrap">
								使用人
							</th>
							<th nowrap="nowrap">
								安装的插件
							</th>
							<th nowrap="nowrap">
								对应的外设
							</th>
							<th nowrap="nowrap">
								其他属性
							</th>
							<th nowrap="nowrap">
								备注
							</th>
							<th nowrap="nowrap">
								设备状态
							</th>
						</tr>
						<w:radioGroup id="group1">
                            <l:iterate property="devices" id="id1">
							 <tr class="<l:output oddOutput="EOS_table_row_o" evenOutput='EOS_table_row' />">
								<td align="center" nowrap="nowrap">
									<w:rowRadio>
											<h:param name='deviceId' iterateId='id1' property='deviceId' />
									</w:rowRadio>
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="orgname" />
								</td>
								<td nowrap="nowrap"> 
									<d:write iterateId="id1" property="deviceName"  dictTypeId="DEVICE_NAME"/>
								</td>
								<td nowrap="nowrap"> 
									<d:write iterateId="id1" property="deviceModel"  dictTypeId="DEVICE_MODEL" />
								</td>
								<td nowrap="nowrap"> 
								     <b:write iterateId="id1" property="ipAdress" />
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="productionMachineName" />
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="cpuCode" />
								</td>
								<td nowrap="nowrap"> 
								     <b:write iterateId="id1" property="memory" />
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="hardDisk" />
								</td>
								<td nowrap="nowrap"> 
									<d:write iterateId="id1" property="osVersion"  dictTypeId="DEVICE_OS_VERSION"/>
								</td>
								<td nowrap="nowrap"> 
								     <b:write iterateId="id1" property="softwareVersion" />
								</td>
								<td nowrap="nowrap"> 
									<d:write iterateId="id1" property="ieVersion"  dictTypeId="DEVICE_IE_VERSION"/>
								</td>
								<td nowrap="nowrap"> 
									<d:write iterateId="id1" property="useful"  dictTypeId="DEVICE_USEFUL"/>
								</td>
								<td nowrap="nowrap"> 
								     <b:write iterateId="id1" property="terminalNumber" />
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="user" />
								</td>
								<td nowrap="nowrap"> 
									<d:write iterateId="id1" property="plugIn" dictTypeId="DEVICE_PLUGIN" />
								</td>
								<td nowrap="nowrap"> 
								     <d:write iterateId="id1" property="peripheral"  dictTypeId="DEVICE_PERIPHERAL" />
								</td>
								<td nowrap="nowrap"> 
								     <d:write iterateId="id1" property="otherOne"  dictTypeId="DEVICE_OTHERONE"/>
								</td>
								<td nowrap="nowrap"> 
								     <b:write iterateId="id1" property="remark" />
								</td>
								<td nowrap="nowrap">
										<d:write  iterateId="id1"  dictTypeId="DEVICE_STATE" property="deviceState" />
								</td>
							</tr>
						</l:iterate>
					</w:radioGroup>
							<tr>
              <td colspan="23" class="command_sort_area">
							<div class="h3">
							<input type="button" class="button" value="新增"
										onclick="add();" />
								<l:greaterThan property="page.count" targetValue="0"
									compareType="number">
							<input type="button" class="button" value="修改"
										onclick="upt();" />
								</l:greaterThan>
								<l:greaterThan property="page.count" targetValue="0"
									compareType="number">
							<input type="button" class="button" value="删除"
										onclick="del();" />
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
	/*	var B= $id("useful").value;
		with(document.form1)
		{
		for (i=0;i<A.length ;i++ )
		{
		  tmpB=B.split(", ");
		  for (j=0;j<tmpB.length ;j++ )
		  {
		   if(tmpB[j]==A[i].value)
		   {A[i].checked=true;break;}
		  }
		}
		}*/
		//提交
		function mysubmit(){
		/*	var items = document.getElementsByName("useful");
			  {
			     var bq = new Array();
			  	 for(var i = 0; i < items.length; i++)
			  	 {
			  		if(items[i].checked)
			  		{
			  			bq.push(items[i].value);
			  		}
			  	 }
			  	 if(bq.length > 0)
			  	 {
			  	 	$id("useful").value = bq.join("-");
			  	 }
			  	 else
			  	 {
			  	 	$id("useful").value = "";
			  	 }
			  } */
			  var str = $id("useful").value;
			  var useful = str.replace(", ","-");
			  alert(222);
			  $id("useful").value = useful;
			  alert(useful);
			  var frm = $name("query_form");
	            frm.submit();
			}

		//清空
			function clears(){
				$id("orgcode").value="";
				$id("orgname").value="";
				$id("deviceName").value="";
				$id("deviceModel").value="";
				$id("deviceState").value="";
				$id("memoryMin").value="";
				$id("memoryMax").value="";
				$id("hardDiskMin").value="";
				$id("hardDiskMax").value="";
				$id("osVersion").value="";
				$id("softwareVersion").value="";
				$id("ieVersion").value="";
				$id("useful").value="";
				$id("plugIn").value="";
				$id("peripheral").value="";
			}

			//新增
			function add(){
				  var url="/deviceManagement/deviceManagementAction_toDevice.action";
				  showModalCenter(url, null,callBackFunc, 700, 600, '新增设备');
			}

			//修改
			function upt(){
				var gop = $id("group1");
		  		var len = gop.getSelectLength();
		  		if(len == 0){
		  			alert("请选择一条记录");
		  			return;
		  		}else{
		  			var rows=gop.getSelectRow();
			  		var deviceId=rows.getParam("deviceId");
		  			var strUrl = "/deviceManagement/deviceManagementAction_toDevice.action?device.deviceId="+deviceId;
		  			showModalCenter(strUrl, null, callBackFunc, 700, 600, '修改设备');  
			  	}
			}
			
			function callBackFunc(){
		        var frm = $name("query_form");
		            frm.submit();
		    }

			//删除
			function del(){
				var gop = $id("group1");
		  		var len = gop.getSelectLength();
		  		if(len == 0){
		  			alert("请选择一条记录");
		  			return;
		  		}else{
			  	  if(confirm("确定要删除该设备吗？")){
		  			var rows=gop.getSelectRow();
			  		var deviceId=rows.getParam("deviceId");
			  		$.ajax({
					      url: "/deviceManagement/deviceManagementAction_delete.action",
					      async: false,
					      type: 'post',
					      data: "device.deviceId="+deviceId,
					      timeout: 60000,
					      dataType:"text",
					      success: function (data) {
					    	  if (data.indexOf("success") >= 0) {
					    		  alert("删除成功");
					    		  callBackFunc();
							} else if (data.indexOf("fails") >= 0) {
								alert("删除失败!");
							} else {
								alert("操作失败!");
							}
									  	
					      }
					}); 
			 	 }	
			  	}
			}

			//选择 部门/机构
			function open_newyw_tree_fun1(){//方法名
			     strUrl ="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.checkcount=1&changeTree.orgType=4&changeTree.showSelBox=4";
				var peArgument = [];
		   		//机构
		   		var paramEntity = new ParamEntity('Organization');
		   		paramEntity.setProperty('orgcode',$id("orgcode").value);
				paramEntity.setProperty('orgname',$id("orgname").value);
				/* paramEntity.setProperty('orgid',$id("orgid").value);
				peArgument[3]=[paramEntity,'orgname','orgcode','orgid'];  */
				peArgument[3]=[paramEntity,'orgname','orgcode'];
				
		       //调用并传参
		        strUrl += "&time="+new Date().getTime();
				showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack1,500,430,'选择机构');
			}
			
		function openNewEmpTreeCallBack1(arg){//回调方法
			if(arg['Organization']){ //原写法无需判断是否为空
		 		var sorgidArra  = arg['Organization'].slice(0);//人员数组
		 		argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("orgcode"));
					argRes[1].push(sorgidArra[i].getProperty("orgname"));
					//argRes[2].push(sorgidArra[i].getProperty("orgid"));
				}
				$id("orgcode").value = argRes[0];
				$id("orgname").value = argRes[1];
				//$id("orgid").value = argRes[2];
			}else{
				$id("orgcode").value = "";
				$id("orgname").value = "";
				//$id("orgid").value = "";
			}
		}	
		</script>
	</body>
</html>