<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>流程节点配置</title>
</head>
<body topmargin="0" leftmargin="0">
<h:form name="data_form"  id="data_form"  action="/jbpm/jbpmDemoAction_saveTaskConfig.action" method="post" onsubmit="return checkForm(this);">
		<table align="center" border="0" width="100%" class="form_table">
		<h:hidden id="definitionId" property="taskAssgineeDto.definitionId" /> 
		<h:hidden id="businessKey" property="taskAssgineeDto.businessKey" />
			<tr>
				<td class="form_label" align="right" width="15%">节点名称</td>
				<td colspan="1" width="30%"><h:text
						validateAttr="allowNull=false;maxLength=15" id="taskName"
						property="taskAssgineeDto.taskName" readonly="true"/></td>
			</tr>
			<tr>
				<td class="form_label" align="right" width="15%">节点办理类型</td>
				<td colspan="1" width="30%"><d:select
						dictTypeId="ZHPT_TASK_CONFIG_TYPE" id="taskConfigType"
						name="taskAssgineeDto.taskConfigType" onchange="changeTaskConfigType(this.value)"  property="taskAssgineeDto.taskConfigType"/>
			</tr>
			<tr>
				<td class="form_label" align="right">办理对象选择：</td>
				<td colspan="1">
				<h:text id="objName" property="taskAssgineeDto.objName" readonly="true" validateAttr="allowNull=false;" /> 
				<h:hidden id="objCode" property="taskAssgineeDto.objCode" /> 
				 <h:hidden id="objId" property="taskAssgineeDto.objId" /> 
				 <a href="#"  id="selectFun" onclick="open_newyw_tree_fun()">选择</a></td>
			</tr>
			<tr>
			<td class="form_label" align="right">记录提交人：</td>
			<td colspan="1">
			<input name ="isRecordSubmit"  type="checkbox"  id="isRecordSubmit"/>	
			</td>
			</tr>
			<tr class="form_bottom">
				<td colspan="2"><input type="button" value="保存" onclick="saveTaskConfig()"
					" class="button" > <input
					type="button" value="关闭" onclick="window.close();" class="button"></td>
			</tr>
		</table>
	</h:form>
<script type="text/javascript">

function custInit(){
	var value = $id("taskConfigType").value;
	var isUpdate = "";
	if($id("isUpdate")  != null){
		isUpdate = $id("isUpdate").value;
		}
	if("isUpdate" == isUpdate){
		$("#taskName").attr("readonly",false);
	}

	var isRecordSubmit = '${isRecordSubmit}';
	if( isRecordSubmit !=null){
		if(isRecordSubmit == "1"){
			$id("isRecordSubmit").checked="checked";
		}
	}
	if("01" == value){
		//指定岗位
			$id("selectFun").disabled="";
			$id("objName").disabled="";
			$("#selectFun").show();
		}else if("02" == value){
		//页面动态机构
			$("#selectFun").hide();
			$id("objName").disabled="disabled";
		}else if("09" == value){
		//部门会签
			$("#selectFun").hide();
			$id("selectFun").disabled="disabled";
			$id("objName").disabled="disabled";
		}else if ("03" == value){
		//指定部门领导
			$id("selectFun").disabled="";
			$id("objName").disabled="";
			$("#selectFun").show();
		}else if ("10" == value){
		//指定部门所有人
			$id("selectFun").disabled="";
			$id("objName").disabled="";
			$("#selectFun").show();
		}else if("04" == value){
		//起草人所属机构领导
			$("#selectFun").hide();
			$id("objName").disabled="disabled";
		}else if("05" == value){
		//起草人
			$("#selectFun").hide();
			$id("objName").disabled="disabled";
		}else if("06" == value){
		//不配置人员
			$("#selectFun").hide();
			$id("objName").disabled="disabled";
		}
}

function changeTaskConfigType(value){
	$id("objCode").value="";
	$id("objName").value="";
	$id("objId").value="";
	if("01" == value){
	//指定岗位
		$id("selectFun").disabled="";
		$id("objName").disabled="";
		$("#selectFun").show();
	}else if("02" == value){
	//页面动态机构
		$("#selectFun").hide();
		$id("objName").disabled="disabled";
	}else if("09" == value){
	//部门会签
		$("#selectFun").hide();
		$id("objName").disabled="disabled";
	}else if ("03" == value){
	//指定部门领导
		$id("selectFun").disabled="";
		$id("objName").disabled="";
		$("#selectFun").show();
	}else if ("10" == value){
		//指定部门所有人
		$id("selectFun").disabled="";
		$id("objName").disabled="";
		$("#selectFun").show();
	}else if("04" == value){
	//起草人所属机构领导
		$("#selectFun").hide();
		$id("objName").disabled="disabled";
	}else if("05" == value){
	//起草人
		$("#selectFun").hide();
		$id("objName").disabled="disabled";
	}else if("06" == value){
	//不配置人员
		$("#selectFun").hide();
		$id("objName").disabled="disabled";
	}
}

	function open_newyw_tree_fun() {//方法名
		var strUrl = "";
		var objName = "";
		var peArgument = [];
		var taskConfigType = $id("taskConfigType").value;
		if (taskConfigType == "01") {
			//指定岗位
			var strUrl = "/empMngr/positionSelectWin2_empMngr.action?date="+new Date();
			peArgument[0]=$id("objId").value;
			peArgument[1]=$id("objName").value;
			showModalCenter(strUrl, peArgument, openNewEmpTreeCallBack, 600,
					450, "岗位选择");
		} else if (taskConfigType == "03" || taskConfigType == "10") {
			//指定部门领导或指定部门所有人
			var strUrl = "/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=4&changeTree.showSelBox=4&date="+new Date();
			var paramEntity = new ParamEntity('Organization');
			//paramEntity.setProperty('orgcode', $id("objCode").value);
			paramEntity.setProperty('orgname', $id("objName").value);
			paramEntity.setProperty('orgid', $id("objId").value);
			//peArgument[0] = [ paramEntity, 'orgname', 'orgcode', 'orgid' ];
			peArgument[0] = [ paramEntity, 'orgname', 'orgid' ];
			showModalCenter(strUrl, peArgument, openNewEmpTreeCallBack, 500,
					430, "部门选择");
		}
	}

	function openNewEmpTreeCallBack(arg) {
		//回调方法
			if(arg!=''){
		if (arg['Organization']) {
			var sorgidArra = arg['Organization'].slice(0);//机构数组
			argRes = [ [], [], [], [] ];
			for ( var i = 0; i < sorgidArra.length; i++) {
				argRes[0].push(sorgidArra[i].getProperty("orgcode"));
				argRes[1].push(sorgidArra[i].getProperty("orgname"));
				argRes[2].push(sorgidArra[i].getProperty("orgid"));
			}
			
			$id("objCode").value = argRes[0];
			$id("objName").value = argRes[1];
			$id("objId").value = argRes[2];
		
		} else {
			//指定岗位
			$id("objCode").value = arg[2];
			$id("objName").value = arg[1];
			$id("objId").value = arg[0];
		}
			}
	}

	function saveTaskConfig() {
		var frm = $name("data_form");
		var url = frm.action;
		if (!checkForm(frm)) {
			return;
		}

		 if($id("isRecordSubmit").checked){
			$id("isRecordSubmit").value=1;
		}else{
			$id("isRecordSubmit").value="";
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
						window.parent.location.reload();
						window.close();
					} else if (data.indexOf("fails") >= 0) {
						alert("保存失败!");
						unMaskTop();
					} else {
						alert("操作失败!");
						unMaskTop();
					}
				} catch (Exception) {
				}
			},
			error : function(data) {
				alert("添加失败请联系管理员！");
				unMaskTop();
			}
		};
		$("#data_form").ajaxSubmit(options);
	}

	eventManager.add(window,"load",custInit);
</script>
</body>
</html>