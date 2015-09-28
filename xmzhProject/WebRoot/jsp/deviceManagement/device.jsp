<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script type="text/javascript" src="/js/jquery.form.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增设备</title>
</head>
<body topmargin="0" leftmargin="0">
<h:form name="data_form"  id="data_form" action="/deviceManagement/deviceManagementAction_save.action"  enctype="multipart/form-data" method="post" >
		<table align="center" border="0" width="100%" class="form_table">
			<h:hidden id="deviceId" property="device.deviceId"  name="device.deviceId"/>
			<tr>
				<td class="form_label" align="right">机构/部门：</td>
				 <td colspan="1">
				        <h:text id="orgname" property="device.orgname"   readonly="true"  validateAttr="allowNull=false"  />
			            <h:hidden id="orgcode" property="device.orgcode" />
			      <a href="#" onclick="open_newyw_tree_fun1();">选择</a>
				</td>
			</tr>
			<tr>
				<td class="form_label" align="right" width="15%">设备名称：</td>
				<td colspan="1" width="30%">
					<d:select  id="deviceName"  dictTypeId="DEVICE_NAME" property="device.deviceName" nullLabel="请选择"  ></d:select>
				</td>
			</tr>
			<tr>
				<td class="form_label" align="right" width="15%">设备型号：</td>
				<td colspan="1" width="30%">
					<d:select  id="deviceModel"  dictTypeId="DEVICE_MODEL" property="device.deviceModel" nullLabel="请选择"></d:select>
				</td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">IP地址：</td>
				<td colspan="1" width="30%"><h:text id="ipAdress"
						property="device.ipAdress" /></td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">生产机器名称：</td>
				<td colspan="1" width="30%"><h:text id="productionMachineName"
						property="device.productionMachineName" /></td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">CPU型号：</td>
				<td colspan="1" width="30%"><h:text id="cpuCode"
						property="device.cpuCode" /></td>						
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">内存容量：</td>
				<td colspan="1" width="30%"><h:text  id="memory"
						property="device.memory"  size="3" />G</td>				
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">硬盘容量：</td>
				<td colspan="1" width="30%"><h:text id="hardDisk"
						property="device.hardDisk"  size="3" />T</td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">操作系统版本：</td>
				<td colspan="1" width="30%">
					<d:select  id="osVersion"  dictTypeId="DEVICE_OS_VERSION" property="device.osVersion" nullLabel="请选择"></d:select>
				</td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">内置软件版本：</td>
				<td colspan="1" width="30%"><h:text id="softwareVersion"
						property="device.softwareVersion" /></td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">IE版本：</td>
				<td colspan="1" width="30%">
					<d:select id="ieVersion"  dictTypeId="DEVICE_IE_VERSION" property="device.ieVersion" nullLabel="请选择"></d:select>
				</td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">用途：</td>
				<td colspan="1" width="30%">
					<d:checkbox  id="useful"  dictTypeId="DEVICE_USEFUL" property="device.useful" />
				</td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">终端号：</td>
				<td colspan="1" width="30%"><h:text id="terminalNumber"
						property="device.terminalNumber" /></td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">使用人：</td>
				<td colspan="1" width="30%"><h:text id="user"
						property="device.user" /></td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">安装的插件：</td>
				<td colspan="1" width="30%">
					<d:checkbox  id="plugIn"  dictTypeId="DEVICE_PLUGIN" property="device.plugIn"  />
				</td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">对应的外设：</td>
				<td colspan="1" width="30%">
					<d:checkbox  id="peripheral"  dictTypeId="DEVICE_PERIPHERAL" property="device.peripheral"  />
				</td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">其他属性：</td>
				<td colspan="1" width="30%">
					<d:select id="otherOne"  dictTypeId="DEVICE_OTHERONE" property="device.otherOne" nullLabel="请选择"></d:select>
				</td>
			</tr>
			<tr>	
				<td class="form_label" align="right" width="15%">备注：</td>
				<td colspan="1" width="30%"><h:textarea property="device.remarksOne"
						id="remarksOne" extAttr="class='h80' "  rows="4" style="width:90%;" /> </td>
			</tr>
			
			<l:notEmpty property="device.deviceState" >
			<tr>	
				<td class="form_label" align="right" width="15%">设备状态：</td>
				<td colspan="1" width="30%">
					<d:select dictTypeId="DEVICE_STATE" property="device.deviceState"  />
				</td>
			</tr>
			</l:notEmpty>
			
			<tr>
				<td colspan="2" style="text-align: center"><input type="button" value="保存"
					onclick="save();"  class="button"> <input type="button" value="关闭"
					onclick="window.close();" class="button"></td>
			</tr>
		</table>
	</h:form>
<script type="text/javascript">

	function save(){
		var frm=$name("data_form");
 		if(!checkForm(frm)){
			 return ;
		 }
		 
 		ajaxsubmitO();
	}

	function ajaxsubmitO() {
		maskTop();
		var options = {
			type : "post",
			cache : "false",
			success : function(data) {
				try {
					if (data.indexOf("success") >= 0) {
						alert("保存成功!");
						unMaskTop();
						window.close();
					} else if (data.indexOf("fails") >= 0) {
						alert("保存失败!");
					} else {
						alert("操作失败!");
					}
				} catch (Exception) {
				}
			},
			error : function(data) {
				alert("添加失败请联系管理员！");
			}
		};
		
		$("#data_form").ajaxSubmit(options);
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