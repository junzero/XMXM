<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script type="text/javascript" src="/js/jquery.form.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>发布流程</title>
</head>
<body topmargin="0" leftmargin="0">
<h:form name="data_form"  id="data_form" action="/jbpm/jbpmDemoAction_deployProcess.action"  enctype="multipart/form-data" method="post" >
<table align="center" border="0" width="100%" class="form_table">
			<tr>
				<td class="form_label" align="right" width="15%">流程名称：</td>
				<td colspan="1" width="30%"><h:text
						validateAttr="allowNull=false;maxLength=15" id="processName"
						property="processDeployDto.processName" /></td>
			</tr>
			<tr>
				<td class="form_label" align="right" width="15%">排序号：</td>
				<td colspan="1" width="30%"><h:text
						validateAttr="allowNull=false;maxLength=15" id="orderNo"
						property="processDeployDto.orderNo" /></td>
			</tr>
			<tr>
				<td class="form_label" align="right" width="15%">工作事项类型：</td>
				<td colspan="1" width="30%">
	<%-- 			<h:select id="businessType"
						name="processDeployDto.businessType" value="01"
						validateAttr="allowNull=false;">
						<h:option label="信息发布" value="01" />
						<h:option label="督办管理" value="02" />
						<h:option label="工作月报" value="03" />
						<h:option label="会议申请" value="04" />
						<h:option label="数据申请" value="05" />
						<h:option label="数据下发" value="06" />
						<h:option label="设备申请" value="07" />
						<h:option label="请假流程" value="08" />
					</h:select> --%>
					<d:select
						dictTypeId="ZHPT_BUSINESS_TYPE"  id="businessType"
						name="processDeployDto.businessType"  property="processDeployDto.businessType" value="01"
						/>
					</td>
			</tr>
			<tr>
				<td class="form_label" align="right" width="15%">发布人员类型：</td>
				<td colspan="1" width="30%"><h:select style="width:148px;"
						id="deployType" name="processDeployDto.deployType"
						validateAttr="allowNull=false;">
						<%-- <h:option value="01" label="角色"></h:option> --%>
						<h:option value="02" label="人员"></h:option>
						<h:option value="03" label="机构"></h:option>
						<h:option value="04" label="岗位"></h:option>
					</h:select>
			</tr>
			<tr>
				<td class="form_label" align="right" width="15%">发布状态：</td>
				<td colspan="1" width="30%"><h:select style="width:148px;"
						id="processState" name="processDeployDto.processState"
						validateAttr="allowNull=false;">
						<h:option value="01" label="启用"></h:option>
						<h:option value="02" label="停用"></h:option>
					</h:select>
			</tr>
			<tr>
				<td class="form_label" align="right">发布人员范围：</td>
				<td colspan="3"><h:text id="objName"
						property="processDeployDto.objName" readonly="true"
						validateAttr="allowNull=false;" /> <h:hidden id="deployRange"
						property="processDeployDto.deployRange" /> <h:hidden id="orgcode"
						property="processDeployDto.orgcode" /> <a href="#"
					onclick="open_newyw_tree_fun()">选择</a></td>
			</tr>
			<tr>
				<td class="form_label" align="right" width="15%">文件上传：</td>
				<td colspan="1" width="30%"><input type="file" id="upload"
					name="upload" style="width:50%" validateAttr="allowNull=false;" />
				</td>
			</tr>
			<tr>
				<td class="form_label" align="right" width="15%">模板文件上传：</td>
				<td colspan="1" width="30%"><input type="file" id="uploadTemplate"
					name="uploadTemplate" style="width:50%"/>
				</td>
			</tr>
			<tr class="form_bottom">
			<td colspan="4"><input type="button" value="保存并发布" " class="button"  onclick="saveAndPub();"> <input type="button"
				value="关闭" onclick="window.close();" class="button"></td>
		</tr>
		</table>
</h:form>
<script type="text/javascript">
//获取文件后缀
function test(file_name){
	var result =/\.[^\.]+/.exec(file_name);
	return result;
	}
//判断文件是否为zip类型的流程文件
function checkForm1(frm){
	 var f =document.getElementById("upload").value;  
	 var FileExt=test(f);
	 if(FileExt ==".zip"){
		return true;
	}else{
		return false;
	}
}
function open_newyw_tree_fun(){//方法名
	var strUrl ="";
	var objName="";
	var peArgument = [];
	var deployType = $id("deployType").value;
	if(deployType=="02"){
     strUrl="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2";
     objName="人员发布";
     var paramEntity = new ParamEntity('Employee');
		paramEntity.setProperty('empid',$id("deployRange").value);
		paramEntity.setProperty('empname',$id("objName").value);
		peArgument[2]=[paramEntity,'empname','empid'];
	}else if(deployType=="01"){
	 strUrl = "/tree/initMainTree_mainTree.action?orgType=4&changeTree.showTabOrg=0&changeTree.showTabGroup=0&changeTree.showTabRole=1&showSelBox=4";
	 objName="角色发布";
	 var paramEntity = new ParamEntity('Role');
		paramEntity.setProperty('roleid',$id("deployRange").value);
		paramEntity.setProperty('rolename',$id("objName").value);
		peArgument[2]=[paramEntity,'rolename','roleid'];
	}else if(deployType=="03"){
	strUrl ="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=4&changeTree.showSelBox=4"
	objName="机构发布";  
	var paramEntity = new ParamEntity('Organization');
		paramEntity.setProperty('orgid',$id("deployRange").value);
		paramEntity.setProperty('orgname',$id("objName").value);
		paramEntity.setProperty('orgcode',$id("orgcode").value);
		peArgument[3]=[paramEntity,'orgname','orgcode',"orgid"];	
	}else if(deployType=="04"){
		strUrl = "/empMngr/positionSelectWin2_empMngr.action"
		peArgument[0]=$id("deployRange").value;
		peArgument[1]=$id("objName").value;
			objName="岗位发布";  
	}			
			showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack,600,430,objName); 
	}

function openNewEmpTreeCallBack(arg){//回调方法
	var deployType = $id("deployType").value;
    if(deployType=="01"){
        //角色
		if(arg!=''){
    	if(arg['Role']){ //原写法无需判断是否为空
		  		var sorgidArra  = arg['Role'].slice(0);//角色数组
		  		argRes=[[],[],[],];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("roleid"));
					argRes[1].push(sorgidArra[i].getProperty("rolename"));
				}
				$id("deployRange").value = argRes[0];
				$id("objName").value = argRes[1];
			}else{
				 $id("deployRange").value = "";
				$id("objName").value = ""; 
			}
		}
    }else if(deployType=="02"){
        //人员
		if(arg!=''){
    	if(arg['Employee']){ //原写法无需判断是否为空
		  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
		  		argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("empid"));
					argRes[1].push(sorgidArra[i].getProperty("empname"));
				}
				$id("deployRange").value = argRes[0];
				$id("objName").value = argRes[1];
			}else{
				$id("objName").value = "";
				$id("deployRange").value = "";
			}
		}
    }else{
		if(arg!=''){
        	if(arg['Organization']){ //原写法无需判断是否为空
  		  		var sorgidArra  = arg['Organization'].slice(0);//人员数组
  		  		argRes=[[],[],[],[]];
  				for(var i=0;i<sorgidArra.length;i++){
  					argRes[0].push(sorgidArra[i].getProperty("orgcode"));
  					argRes[1].push(sorgidArra[i].getProperty("orgname"));
  					argRes[2].push(sorgidArra[i].getProperty("orgid"));
  				}
  				$id("deployRange").value = argRes[2];
  				$id("objName").value = argRes[1];
  				$id("orgcode").value = argRes[0];
  			}else{
  				//指定岗位
  				$id("deployRange").value =arg[0];
  				$id("objName").value =arg[1];
  				$id("orgcode").value=arg[2];
  			}
		}
    }
	}

	function saveAndPub(){
		var frm=$name("data_form");
 		if(!checkForm(frm)){
			 return ;
		 }
 		if(!checkForm1(frm)){
 	 		alert("请上传zip格式的流程部署文件！");
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
						window.parent.location.reload();
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
</script>
</body>
</html>