<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<h:css href="/css/style1/style-custom.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="/css/fileDown.css">
<script type="text/javascript" src="/common/gotop/jquery.min.js"></script>
<script type="text/javascript" src="/js/fileDown.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/commonUtil.js"></script>
<title>新增数据使用申请单</title>
</head>
  
  <body>
  <%-- <w:panel title="数据使用申请"> --%>
    <h:form id="applyDataForm" name="applyDataForm" action="" method="post" enctype="multipart/form-data">
<table align="center" border="0" width="100%" class="form_table">
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >数据申请
       		 <h:hidden id="executionId" name="applyData.flowId" property="taskAssgineeDto/executionId"/>
       		 <h:hidden id="taskId" name="taskId" property="taskAssgineeDto/nextTaskId"/>
       		 <h:hidden id="beforeId" name="applyData.daId" property="taskAssgineeDto/businessKey"/>
       		 <h:hidden id="submitReason" name="submitReason"/>
       		 <h:hidden id="definitionId" name="definitionId" property="taskAssgineeDto/definitionId"/>
        	 <h:hidden id="businessType" name="businessType" property="taskAssgineeDto/businessType"/>
        	    <h:hidden id="templateFileIds" property="taskAssgineeDto.templateFileIds"/>
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right">
        <nobr> 标　　　 题：</nobr>        </td>
        <td colspan="3">
          <h:text name="applyData.daTitle" validateAttr="allowNull=false;maxLength=64" style="width:90%;" property="applyData.daTitle"/>
          <font style="color: red">*</font>        </td>
      </tr>
      <tr>
        <td class="form_label" align="right" style="width:14%">
          <nobr>申　 请　人：</nobr>        </td>
        <td colspan="1" style="width:36%">
        	<b:write property="login_user/empname" scope="session" />
     	</td>
        <td class="form_label" align="right" style="width:14%">
       <nobr>  联  系  电  话：   </nobr>     </td>
        <td colspan="1" style="width:36%">
        	<h:text id="phoneNo"  value="${sessionScope.login_user.mobi }" name="applyData.phoneNo" property="applyData.phoneNo"/></td>
      </tr>
      <tr>
         <td class="form_label" align="right">
          <nobr>申 请 部 门： </nobr>       </td>
        <td>
         <b:write property="login_user/orgname" scope="session" />        </td>
        <td class="form_label" align="right"><nobr>是否涉及客户敏感数据：</nobr></td>
        <td><d:radio dictTypeId="ZHPT_SENSITIVE_DATA" name="applyData.isSdata" property="applyData.isSdata" value="02"></d:radio></td>
      </tr>
	   <tr>
       <td class="form_label" align="right"><nobr>申 请 事 项：</nobr></td>
     	<td colspan="3">
     		<h:textarea name="applyData.daMatter"  extAttr="class='h80' " validateAttr="maxLength=64;allowNull=false" property="applyData.daMatter" rows="4" style="width:90%;"/>
     		<font style="color: red">*</font></td>
     </tr>
      <tr>
       <td class="form_label" align="right"><nobr>申 请 内 容：</nobr></td>
     	<td colspan="3">
     		<h:textarea name="applyData.daContent" extAttr="class='h80' " validateAttr="maxLength=512;allowNull=false" property="applyData.daContent" rows="4" style="width:90%;"/>
     		<font style="color: red">*</font></td>
     </tr>
	  <tr>
       <td class="form_label" align="right"><nobr>提 取 时 限：</nobr></td>
     	<td colspan="1">
     	<w:date id="daTlimit" name="applyData.daTlimit" property="applyData.daTlimit" format="yyyy-MM-dd" submitFormat="yyyyMMdd" allowNull="false"/>
     	<font style="color: red">*</font>
     	</td>
		<td class="form_label" align="right"><nobr>提 取 频 度：</nobr></td>
     	<td colspan="1">
     	<d:select dictTypeId="ZHPT_FREQUENCY_EXT" id="daFreq" name="applyData.daFreq" property="applyData.daFreq" extAttr="validateAttr='allowNull=false'" nullLabel="请选择" onchange="freqChange();"></d:select>
     	<d:select id="freqType" dictTypeId="ZHPT_FREQUENCY_TYPE" property="applyData.freqType" nullLabel="请选择"></d:select>
     	<h:text id="freqNo" property="applyData.freqNo" size="3" validateAttr="type=number"/>次
     	</td>
     </tr>
	 <tr>
	 <td class="form_label" align="right"><nobr>提 取 范 围：</nobr></td>
     	<td colspan="3">
     		<h:textarea name="applyData.daRange"  extAttr="class='h80' " validateAttr="maxLength=512;allowNull=false" property="applyData.daRange" rows="4" style="width:90%;"/>
     		<font style="color: red">*</font>
     	</td>
	</tr>
     <tr>
     <td class="form_label" align="right"><nobr>数据使用人员：</nobr></td>
     	<td colspan="3">
     		<h:textarea id="dataUser_show" name="applyData.username"  extAttr="class='h80' " validateAttr="maxLength=512;allowNull=false" property="applyData.username" rows="4" style="width:90%" readonly="true"/>
     		<h:hidden id="dataUser" name="applyData.dataUser" property="applyData.dataUser" />
     		<a href="#" onclick="openTree1();">选择</a><font style="color: red">*</font>     	</td>
     </tr>
	  <tr>
     <td class="form_label" align="right"><nobr>数据主管部门：</nobr></td>
     	<td colspan="1">
     		<h:text id="org_show" name="applyData.eOrgName" validateAttr="allowNull=false" property="applyData.eOrgName" style="width:70%" readonly="true"/>
     		<h:hidden id="orgId" name="applyData.eOrgId" property="applyData.eOrgId" />
     		<a href="#" onclick="openTree2();">选择</a><font style="color: red">*</font>     	</td>
     	<td class="form_label" align="right"><nobr>数据主管人员：</nobr></td>
     	<td colspan="1">
     		<h:text id="emp_show" name="applyData.eEmpName" validateAttr="allowNull=false" property="applyData.eEmpName" style="width:70%" readonly="true"/>
     		<h:hidden id="eEmpId" name="applyData.eEmpId" property="applyData.eEmpId" />
     		<a href="#" onclick="openTree3();">选择</a><font style="color: red">*</font>     	</td>
     </tr>
	  <tr>
     <td class="form_label" align="right"><nobr>数据使用期限：</nobr></td>
     	<td colspan="1">
     	<w:date id="useTlimit" name="applyData.useTlimit" property="applyData.useTlimit" submitFormat="yyyyMMdd" allowNull="false" />
     	<font style="color: red">*</font></td>
     </tr>
     <tr>
     	<td class="form_label" align="right"><nobr>附　　　 件：</nobr></td>
     	<td colspan="3">
	     	<input type="button" onclick="addFile('tabtest','upload');return false;" value="新增附件" 
					style="margin-left:2px;vertical-align:middle;cursor:hand;"/>
			<font style="color: red">(说明：最多上传5个附件)</font>
			<br/>
			<table border=0 id="tabtest">
			</table>     	</td>
     </tr>
      <tr style="display:none" id="beforeFile">
     	<td class="form_label" align="right"><nobr>之前上传的附件：</nobr></td>
     	<td colspan="3">
     	<div id="tag"></div>     	</td>
     </tr>
     <tr id="rowTemplate">
					<td class="form_label" align="right">模板文件：</td>
					<td colspan="3"><input type="button" value="下载模板"
						class="button"
						onclick="downLoadTemplateFiles('<b:write property="taskAssgineeDto/templateFileIds" />');">
					</td>
				</tr>
      <tr class="form_bottom">
        <td colspan="4">
          <input type="button" value="结束流程" class="button" onclick="doDeleteProcess('<b:write property="taskAssgineeDto/businessKey" />','05');" id="deleteProcessBtn">
          <input type="button" value="保存" class="button" onclick="doSubmit(0);" id="savess">
          <input type="button" value="提交" onclick="doSubmit(1);" class="button" id="querytu">
         <input type="button" value="查看流程" onclick="doflowpic();" class="button" id="flowpic" />         </td>
      </tr>
      <tr id="opinionTr" style="display: none">
     	<td class="form_label" align="right"><nobr>流 程 列 表：</nobr></td>
     <td colspan="3">
     <%@include file="/jsp/util/opinionUtil.jsp"%>
		</td>
     </tr>
    </table>
    </h:form>
    <%-- </w:panel> --%>
    <iframe name="hiframe" style="display:none"></iframe>
  </body>
  <script type="text/javascript">
		$(document).ready(function(){
			var templateFileIds = $("#templateFileIds").val();
			if(templateFileIds == "" || templateFileIds == null){
				$("#rowTemplate").hide();
			}
			//隐藏结束流程按钮
			$("#deleteProcessBtn").hide();
			if('${sessionScope.login_user.empid}'=='${applyData.empId}'){
				  if($("#executionId").val()){
					  //当登录人等于发起人，显示结束流程按钮
					  $("#deleteProcessBtn").show();
			  	    }
			}
			freqChange();
			var beforeId = $id("beforeId").value;
			var isView = '${isView}';
			if(isView!=''){
				$("#deleteProcessBtn").hide();
				$("#savess").hide();
				$("#querytu").hide();
				$("#rowTemplate").hide();
				}
			if(beforeId != ""){
				$("#beforeFile").show();
				$("#opinionTr").show();
				$.ajax({
			        url: '/file/tFileResourceTableAction_queryFileList.action',
			        async: false,
			        type: 'post',
			        data: "resourceType=05&resourceId=<b:write property="taskAssgineeDto/businessKey" />",
			        dataType: 'json',
			        timeout: 60000,
			        success: function (files) {
				        if(files!=null){
				         	$.each(files,function( i,item ){
				         		if(isView!=''){
				         			$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId});
				          		}else{
				    	        	$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId,remove:1});
				          		}
				          		});	
				        }
			        }
		      });
			}
		});

		 function initPlanCell20(){
				var queryCond="";
					queryCond += "<resourceId>${applyData.daId}</resourceId>";
					queryCond += "<resourceType>05</resourceType>";
					return queryCond;
				}
			
		function doSubmit(type){
			$name("submitReason").value = type;
			if(checkForm($id("applyDataForm"))){
				if(type==0){
					var url = "/dataApply/tApplyDataAction_saveOrUpdateApply.action";
					var _form = $id("applyDataForm");
		  	  	    _form.action =url;
					ajaxsubmitO(0);
			   		return true;
				}
				var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value+"&taskAssgineeDto.beginOrg=<b:write property="taskAssgineeDto.beginOrg" />"+"&taskAssgineeDto.beginAssingee=<b:write property="taskAssgineeDto.beginAssingee" />"+"&taskAssgineeDto.definitionId=<b:write property="taskAssgineeDto.definitionId" />";
	    		showModalCenter(strUrl, null, taskAssigneeCallBack, 700, 400, '节点选择');
			}
		}
		
		var rowId = 0;
		function addFile(tabid,varName){
		    var tab,row,td,fName,fId,tdStr,g;
		    if ($id("group1")==null){ 
		    	g=0;
		    } else{
		    	g = $id("group1").getLength(); 
		    }
		    var zs=g+rowId;
		    tab = $id(tabid);
		    if (zs>=5){
		    	alert("新增附件不能超过5个");
		    	return false;
		    }
		    fName = varName;
		    fId = varName+rowId;
		    row =  tab.insertRow();
		    row.id = "fileRow"+rowId;
		    td = row.insertCell(); 
		    
		    tdStr="<input type=\"file\" name=\""+fName+"\" id=\""+fId+"\" onchange=\"CheckUpLoadFile(this,2);\" size='70' class=smallInput validateAttr=\"allowNull=false\">";
		    tdStr += "<input type=\"button\" onclick=\"delTr('fileRow"+rowId+"');return false;\" name='button"+rowId+"' value=\"删除\" style=\"margin-left:2px;vertical-align:middle;cursor:hand;\"/>";
		    td.innerHTML = tdStr;
		    rowId = rowId+1;    
		}
		
		function delTr(row_id){
			$("#"+row_id).remove();
		    rowId = rowId-1;
		}
		
		function taskAssigneeCallBack(arg){
			//arg ="taskAssgineeDto.empids=1,2&taskAssgineeDto.empnames=张三，李四&taskAssgineeDto.transitionName=同意";
			var url = "/dataApply/tApplyDataAction_saveOrUpdateApply.action?";
			if(arg!=''){
				arg=encodeURI(arg);
				url+=arg;
				var _form = $id("applyDataForm");
	  	  	    _form.action =url;
				ajaxsubmitO(1);
			}
  	   }

		function ajaxsubmitO(flag){
			maskTop();
	  	  	 var options = {
	  		  		type : "post",
	  		  		cache : "false",
	  			  	success : function(data){
	  					try {  
	  					  	if(data.indexOf("success")>=0){
	      					  	alert("操作成功!");
  	 					  		unMaskTop();
	  					  		//reload();
	      					    //WEB.turnMainFrame();20140905改
	      					    if(flag==1)
	      					     window.close();
	  					  	}else if(data.indexOf("fails")>=0){
	      					  	alert("操作失败!");
  	 					  		unMaskTop();
	  					  	}else{	 
	  					  	    alert("操作失败!"); 
  	 					  		unMaskTop();
	  					  	}
	  	   				} catch (Exception) {  
	  					}  
	  				},  
	  			  	error : function(data){
	  				  alert("操作失败请联系管理员！");
					  		unMaskTop();
	  			   	}  
	  			}; 
	  	  	$("#applyDataForm").ajaxSubmit(options);
	  	  	}	

	  	   //展开树		
		 function openTree1(){
			  var strUrl = "/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.showTabGroup=0&changeTree.showTabRole=0&changeTree.orgType=6&changeTree.showSelBox=2";
				var peArgument = [];
				//人员
		    	var paramEntity = new ParamEntity('Employee');
				paramEntity.setProperty('empid',$id("dataUser").value);
				paramEntity.setProperty('empname',$id("dataUser_show").value);
				peArgument[0]=[paramEntity,'empname','empid'];
				//调用并传参
				strUrl += "&time="+new Date().getTime();
				//$name("assignerTab").src = strUrl;	 
				showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack1,500,430,'数据使用人员选择');
		}

		 $id("useTlimit").onValidate=function(hiddenValue,TextValue)
			{
				if(this.getValue()=="") 
				 return true;
				else
				{
					var result = WEB.today();
				  if(this.getValue()<result) 
				     {
				      f_alert($id("useTlimit").text,"日期要大于当前时间");
				      return false;
				      }
				      else
				      return true;
				}

			}

		 $id("daTlimit").onValidate=function(hiddenValue,TextValue)
			{
				if(this.getValue()=="") 
				 return true;
				else
				{
					var result = WEB.today();
				  if(this.getValue()<result) 
				     {
				      f_alert($id("daTlimit").text,"日期要大于当前时间");
				      return false;
				      }
				      else
				      return true;
				}

			}
			
		function openNewEmpTreeCallBack1(arg){
			//回调方法
			if(arg!=''){
				if(arg['Employee']){ 
			  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
			  		argRes=[[],[],[],[]];
					for(var i=0;i<sorgidArra.length;i++){
						argRes[0].push(sorgidArra[i].getProperty("empid"));
						argRes[1].push(sorgidArra[i].getProperty("empname"));
					}
					$id("dataUser").value = argRes[0];
					$id("dataUser_show").value = argRes[1];
				}else{
					$id("dataUser").value = "";
					$id("dataUser_show").value = "";
				}
			}
		}
		function openTree2(){
			var strUrl = "/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.showTabGroup=0&changeTree.showTabRole=0&changeTree.orgType=4&changeTree.showSelBox=4&changeTree.checkcount=1";
			var peArgument = [];
			//机构
	    	var paramEntity = new ParamEntity('Organization');
			paramEntity.setProperty('orgid',$id("orgId").value);
			paramEntity.setProperty('orgname',$id("org_show").value);
			peArgument[0]=[paramEntity,'orgname','orgid'];
			//调用并传参
			strUrl += "&time="+new Date().getTime();
			//$name("assignerTab").src = strUrl;	 
			showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack2,500,430,'主管部门选择');
		}
	
		function openNewEmpTreeCallBack2(arg){
			//回调方法
			if(arg!=''){
				if(arg['Organization']){ 
			  		var sorgidArra  = arg['Organization'].slice(0);//机构数组
			  		argRes=[[],[],[],[]];
					for(var i=0;i<sorgidArra.length;i++){
						argRes[0].push(sorgidArra[i].getProperty("orgid"));
						argRes[1].push(sorgidArra[i].getProperty("orgname"));
					}
					$id("orgId").value = argRes[0];
					$id("org_show").value = argRes[1];
				}else{
					$id("orgId").value = "";
					$id("org_show").value = "";
				}
			}
		}
		function openTree3(){
			if($id("org_show")==null || $id("org_show").value=='null' || $id("org_show").value=='' || $id("org_show").value=='undefined'){
				alert("请先选择主管部门");
				return;
			}else{
				var strUrl = "/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.showTabGroup=0&changeTree.showTabRole=0&changeTree.orgType=6&changeTree.showSelBox=2&changeTree.checkcount=1&changeTree.startOrgid="+$id("orgId").value+"&changeTree.positioncode=BMLD";
				var peArgument = [];
				//人员
		    	var paramEntity = new ParamEntity('Employee');
				paramEntity.setProperty('empid',$id("eEmpId").value);
				paramEntity.setProperty('empname',$id("emp_show").value);
				peArgument[0]=[paramEntity,'empname','empid'];
				//调用并传参
				strUrl += "&time="+new Date().getTime();
				//$name("assignerTab").src = strUrl;	 
				showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack3,500,430,'主管人员选择');
			}
		}
	
		function openNewEmpTreeCallBack3(arg){
			//回调方法
			if(arg!=''){
				if(arg['Employee']){ 
			  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
			  		argRes=[[],[],[],[]];
					for(var i=0;i<sorgidArra.length;i++){
						argRes[0].push(sorgidArra[i].getProperty("empid"));
						argRes[1].push(sorgidArra[i].getProperty("empname"));
					}
					$id("eEmpId").value = argRes[0];
					$id("emp_show").value = argRes[1];
				}else{
					$id("eEmpId").value = "";
					$id("emp_show").value = "";
				}
			}
		}

		function freqChange(){
			if($("#daFreq").val()=='02'){
				$("#freqNo").removeAttr("disabled");
				$("#freqType").removeAttr("disabled");
			}
			else{
				$("#freqNo").attr("disabled","true");
				$("#freqType").attr("disabled","true");
			}
		}
  </script>
</html>
