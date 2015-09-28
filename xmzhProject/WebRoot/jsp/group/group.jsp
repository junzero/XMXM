<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script type="text/javascript" src="/js/jquery.form.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增群组</title>
</head>
<body topmargin="0" leftmargin="0">
<h:form name="data_form"  id="data_form" action="/group/tGroupAction_addGroup.action"  enctype="multipart/form-data" method="post" >
		<table align="center" border="0" width="100%" class="form_table">
			<h:hidden id="recId" property="group.recId"  name="group.recId"/>
			<h:hidden id="createRole" property="group.createRole"  name="group.createRole"/>
			<h:hidden id="isWholebankR" name="isWholebankR"  property="group.isWholebank" />
			<tr>
				<td class="form_label" align="right" width="15%">群组名称</td>
				<td colspan="1" width="30%"><h:text
						validateAttr="allowNull=false;maxLength=15" id="groupName"
						property="group.groupName" /></td>
			</tr>
			<tr>
				<td class="form_label" align="right">群组描述：</td>
				<td colspan="1"><h:textarea property="group.groupDetail"
						id="groupDetail" extAttr="class='h80' " 
						validateAttr="maxLength=512;allowNull=false" rows="4"
						style="width:90%;" /> 
			</tr>
			<tr>
				<td class="form_label" align="right">群组人员：</td>
				<td colspan="1"><h:textarea property="group.empNames"
						id="empNames" extAttr="class='h80' " readonly="true"
						validateAttr="maxLength=512;allowNull=false" rows="4"
						style="width:90%;" /> 
						<h:hidden id="empIds" property="group.empIds" /><br/>
					<a href="#" onclick="selectEmpByOrg()">按机构选择</a>
					<a href="#" onclick="selectEmpByPosition()">按岗位选择</a>
					<a href="#" onclick="cleanEmp()">清空</a></td>
			</tr>
			<tr id="isWholebankTr">
				<td class="form_label" align="right"><nobr>是否全行应用：</nobr></td>
				<td colspan="1" width="30%"><d:radio dictTypeId="ZHPT_GROUP_ISWHOLE"   property="group.isWholebank" name="group.isWholebank" style="width:133"  value="1"/></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><input type="button" value="保存"
					onclick="save();"  class="button"> <input type="button" value="关闭"
					onclick="window.close();" class="button"></td>
			</tr>
		</table>
	</h:form>
<script type="text/javascript">
	
	//按岗位选择人员
	function selectEmpByPosition() {
		var strUrl = "/tree/initMainTree_mainTree.action?changeTree.orgType=5&changeTree.showTabOrg=0&changeTree.showTabPositionEmp=1&time="
				+ new Date().getTime();
		var peArgument = [];
		//人员
		var paramEntity = new ParamEntity('Employee');
		paramEntity.setProperty('empid', $id("empIds").value);
		paramEntity.setProperty('empname', $id("empNames").value);
		peArgument[0] = [ paramEntity, 'empname', 'empid' ];

		showModalCenter(strUrl, peArgument, callBack, 500, 430, '选择使用范围')
	}

	function cleanEmp(){
		 $("#empIds").val("");	
		 $("#empNames").val("");	
	}
	
	function callBack(arg){
		if(arg!=''){
			if(arg['Employee']){
		  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
		  		argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("empid"));
					argRes[1].push(sorgidArra[i].getProperty("empname"));
				}
				$id("empIds").value = argRes[0];
				$id("empNames").value = argRes[1];
	    	}else{
				$id("empIds").value = "";
				$id("empNames").value = "";
	    	}
		}
	}
	//按机构选择人员
	function selectEmpByOrg() {
		var strUrl = "/tree/initMainTree_mainTree.action?changeTree.orgType=5&changeTree.showTabOrg=1&time="
				+ new Date().getTime();
		var peArgument = [];
		//人员
		var paramEntity = new ParamEntity('Employee');
		paramEntity.setProperty('empid', $id("empIds").value);
		paramEntity.setProperty('empname', $id("empNames").value);
		peArgument[0] = [ paramEntity, 'empname', 'empid' ];

		showModalCenter(strUrl, peArgument, callBack, 500, 430, '选择使用范围')
	}

	function save(){
		var frm=$name("data_form");
 		if(!checkForm(frm)){
			 return ;
		 }
		 var createRole = $id("createRole").value;
		 var isWholebankR = $id("isWholebankR").value;
 		$.ajax({
		      url: "/group/tGroupAction_isSysadmin.action",
		      async: false,
		      type: 'post',
		      data: "",
		      timeout: 60000,
		      success: function (data) {
		    	  if (data.indexOf("success") >= 0) {
			    		 //当前登录者为系统管理员
		    		  ajaxsubmitO(); 
				} else if (data.indexOf("false") >= 0) {
						//当前登陆者非系统管理员
						if(createRole == 'SYSADMIN' && isWholebankR=='1'){
							alert('该群组为全行群组，只有系统管理员才执行修改操作');
							return;
						}else{
							ajaxsubmitO(); 
						}
				} 
						  	
		      }
		}); 
		 
 		
	}

	function ajaxsubmitO() {
		maskTop();
		var options = {
			type : "post",
			cache : "false",
			success : function(data) {
				try {
					if (data.indexOf("success") >= 0) {
						alert("保存群组成功!");
						unMaskTop();
						window.parent.location.reload();
						window.close();
					} else if (data.indexOf("fails") >= 0) {
						alert("保存群组失败!");
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

	function custInit(){
		$.ajax({
		      url: "/group/tGroupAction_isSysadmin.action",
		      async: false,
		      type: 'post',
		      data: "",
		      timeout: 60000,
		      success: function (data) {
		    	  if (data.indexOf("success") >= 0) {
			    		 //当前登录者为系统管理员
			    		 $("#isWholebankTr").show();
				} else if (data.indexOf("false") >= 0) {
						//当前登陆者非系统管理员
					 $("#isWholebankTr").hide();
				} 
						  	
		      }
		}); 
	}
	eventManager.add(window,"load",custInit);
</script>
</body>
</html>