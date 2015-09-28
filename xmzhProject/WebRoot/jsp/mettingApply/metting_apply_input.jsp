<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="/css/fileDown.css">
<script type="text/javascript" src="/common/gotop/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/fileDown.js"></script>
<script type="text/javascript" src="/js/commonUtil.js"></script>
<title>会议信息</title>
</head>
  <body>
  <e:datasource name="meet" type="bean" path="com.gotop.mettingApply.model.TMettingApply" />
    <h:form name="form1" id="form1" action="/mettingApply/tMettingApplyAction_insertMettingInfo.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
    <div id="content">
         <h:hidden property="meet.mettingId"/>
         <h:hidden property="meet.flowId"/>
	     <h:hidden property="meet.createDate"/>
	    <%--  <h:hidden property="meet.createor" id="createor"/> --%>
	     <h:hidden property="meet.creator" id="createor"/>
        <h:hidden id="executionId" property="taskAssgineeDto.executionId"/>
        <h:hidden id="taskId"  property="taskAssgineeDto.nextTaskId"/>
        <h:hidden id="taskAssingee" property="taskAssgineeDto.taskAssingee"/>
        <h:hidden id="beginOrg" property="taskAssgineeDto.beginOrg"/>
        <h:hidden id="definitionId" property="taskAssgineeDto.definitionId"/>
        <h:hidden id="businessType" property="taskAssgineeDto.businessType"/>
           <h:hidden id="templateFileIds" property="taskAssgineeDto.templateFileIds"/>
		 <input type="hidden" id="btnType" name="btnType" />
		 <input type="hidden" id="isFirst" name="isFirst" value="1"/>
		<table align="center" border="0" width="100%" class="form_table" >
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		会议信息
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right">
                                                     会议标题：
        </td>
        <td colspan="3">
          <h:text property="meet.title" id="title" validateAttr="allowNull=false" style="width:90%;" /><font style="color: red">*</font>	
        </td>
      </tr>
      <tr>
        	<td class="form_label" align="right" style="width:20%;">
                                                    会议类型：
        </td>
        <td colspan="1" style="width:20%;">
        <div id="dis1" >
        	<d:select dictTypeId="ZHPT_METTING_TYPE" property="meet.type" extAttr="validateAttr='allowNull=false'"  id="mettingType" nullLabel="请选择"></d:select><font style="color: red">*</font>
        </div>
        </td>
        <td class="form_label" align="right" style="width:20%;">
                                                    会议室名称：
        </td>
        <td style="width:40%;">
        <d:select dictTypeId="ZHPT_METTING_NAME" property="meet.mname" extAttr="validateAttr='allowNull=false'"  id="mettingName" nullLabel="请选择"></d:select><font style="color: red">*</font>
        <td>
      </tr>
       <tr>
        <td class="form_label" align="right">
                                                   申请人：
        </td>
        <td colspan="1">
         <h:text property="meet.empName" id="empName" validateAttr="allowNull=false" readonly="true" style="width:130px;" /><font style="color: red">*</font>	
        </td>
      	<td class="form_label" align="right">
                                                    会议时间：
        </td>
        <td colspan="1">
        <div id="inputTime">
	         <w:date  id="mettingDate" submitFormat="yyyy-MM-dd" format="yyyy-MM-dd" readonly="true" allowNull="false" property="meet.mettingDate"/>
	         <h:text property="meet.mettingTime" id="mettingTime" size="8" validateAttr="allowNull=false"/>
	         <font style="color: red">*时间格式为HH:mm(24小时制)</font>
         </div>
         <div id="hiddenTime" style="display: none">
         	<h:text id="showTime"  readonly="true"/>
         </div>
        </td>
      </tr>
      <tr>
     	<td class="form_label" align="right">内容：</td>
     	<td colspan="3">
	     	<h:textarea property="meet.content" id="content"  extAttr="class='h80' "  validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;" />
			  <h:hidden name="meet.opninion" value=""/>
		    <font style="color: red">*</font>
     	</td>
      </tr>
      <tr>
        <td class="form_label" align="right">参会行领导：</td>
			<td colspan="3">
			  <h:textarea property="meet.joinLeadername" id="leadername"  extAttr="class='h80' "  readonly="true" validateAttr="maxLength=512" rows="4"  style="width:90%;" />
			  <h:hidden id="leaderid" property="meet.joinLeader" />
			  <div align="left">
				  <a href="#" onclick="open_newyw_tree_fun2()">选择</a>
				  <a href="#" onclick="cleanLeader()">清空</a>
			  </div>
			</td>
	   </tr>
       <tr>
        <td class="form_label" align="right">参会人员：</td>
			<td colspan="3">
			  <h:textarea property="meet.joinEmpname" id="joinEmpname"  extAttr="class='h80' "  readonly="true" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;" />
			  <h:hidden id="empid" property="meet.joinEmp" />
			  <div align="left">
				  <a href="#" onclick="open_newyw_tree_fun1()">选择</a>
				  <a id="selectG" href="#" onclick="open_group_tree_fun()">选择群组</a>
				  <a href="#" onclick="cleanEmp()">清空</a>
			  </div>
			</td>
	   </tr>
         <tr id="row1">
      <td class="form_label" align="right">附件下载：</td>
      <td colspan="3">
      <div id="tag"></div>
      </td>
      </tr>
     <tr id="rowFile">
     	<td class="form_label" align="right">附件：</td>
     	<td colspan="3">
				<input type="button" onclick="addFile('tabtest','files');return false;" value="新增附件" 
					style="margin-left:2px;vertical-align:middle;cursor:hand;"/>
				<font style="color: red">(说明：最多上传5个附件)</font>
				<br/>
				<table border=0 id="tabtest">
				</table>
     	</td>
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
        <input type="button" value="结束流程" class="button" onclick="doDeleteProcess('<b:write property="taskAssgineeDto/businessKey" />','04');" id="deleteProcessBtn">
          <input type="button" value="保存" class="button" id="save1" onclick="doSave(1);"  />
          <input type="button" value="提交" onclick="doSave(2);" class="button" id="smit" />
          <input type="button" value="查看流程" onclick="doflowpic();" class="button" id="flowpic" />
         </td>
      </tr>
       <tr id="row2">
     <td class="form_label" align="right">流程列表：</td>
     <td colspan="3">
     <%@include file="/jsp/util/opinionUtil.jsp"%>
		</td>
     </tr>
    </table>
    </div>
    </h:form>
  </body>
 <script type="text/javascript">
 $(document).ready(function(){
	 var templateFileIds = $("#templateFileIds").val();
		if(templateFileIds == "" || templateFileIds == null){
			$("#rowTemplate").hide();
		}
	//隐藏结束流程按钮
		$("#deleteProcessBtn").hide();
		if('${sessionScope.login_user.empid}'=='${meet.creator}'){
			  if($("#executionId").val()){
				  //当登录人等于发起人，显示结束流程按钮
				  $("#deleteProcessBtn").show();
		  	    }
		}
	 if('${meet.mettingId}'!=""){
		 $("#showTime").val( '${meet.mettingDate}'+' ${meet.mettingTime}');
		 if('${meet.creator}'!='${sessionScope.login_user.empid}')
		  $("#save1").css("display","none"); 
		 $.ajax({
		        url: '/file/tFileResourceTableAction_queryFileList.action',
		        async: false,
		        type: 'post',
		        data: "resourceType=${taskAssgineeDto.businessType}&resourceId=${meet.mettingId}",
		        dataType: 'json',
		        timeout: 60000,
		        success: function (files) {
			        if(files!=""){
			         	$.each(files,function( i,item ){
				         	if('${isView}'!='')
			    	        	$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId});
				         	else
				         		$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId,remove:1});
			          		});	
			        }else{
			        	 $("#row1").css("display","none");  
			        }
		        }
	    });	
		  $("#beginOrg").val("${meet.orgid}");	
		  //判断
		  WEB.doDisabledAttr('${isView}','${meet.creator}','${sessionScope.login_user.empid}',"opinion");  
		  WEB.doConditionDisplay('${meet.creator}','${sessionScope.login_user.empid}',"row3","none");//隐藏意见
		  if('${meet.creator}'!='${sessionScope.login_user.empid}'){
				 //$("#opninion").val("已处理");
				 }
	 }else{
		 $("#row1").css("display","none");  
		 $("#row2").css("display","none");  
		 $("#row3").css("display","none");  
		 $("#beginOrg").val('${sessionScope.login_user.orgid}'); 
	 }
	 if('${meet.empName}'==""){
		 $("#empName").val('${sessionScope.login_user.empname}');
	 }

	 if('${isView}'!=''){
		 $("#deleteProcessBtn").hide();
			$("#save1").hide();
			$("#hiddenTime").show();
			$("#inputTime").hide();
			$("a").hide();
			$("#mettingType").attr("disabled","true");
			$("#mettingName").attr("disabled","true");
			$("#rowTemplate").hide();
			WEB.hideFile();
		}
});

 $("#mettingTime").blur(function(){
	 var val = $("#mettingTime").val();
	 if(!val.test(/^([0-1][0-9]|[2][0-3]):([0-5][0-9])$/)){
			alert("格式错误");
		 }
	 });

 
function initPlanCell20(){
		var queryCond="";
			queryCond += "<resourceId>${meet.mettingId}</resourceId>";
		 	queryCond += "<resourceType>${taskAssgineeDto.businessType}</resourceType>";
			return queryCond;
		}

     function doSave(value){
    		$("#btnType").val(value);
    		if(checkForm($id("form1"))){
    		if(value!="1"){
    			var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value+"&taskAssgineeDto.beginOrg="+$("#beginOrg").val()+"&taskAssgineeDto.beginAssingee="+$("#createor").val()+"&taskAssgineeDto.definitionId=${taskAssgineeDto.definitionId}";
        		showModalCenter(strUrl, null, taskAssigneeCallBack, 700, 400, '节点选择');
    		}else{
    			var _form = $id("form1");
    	  	  	 	url="/mettingApply/tMettingApplyAction_insertMettingInfo.action";	
    	  	  	    _form.action =url
    			    ajaxsubmitO(0);
    	  	 	}
    		}
     }
 	function taskAssigneeCallBack(arg){
  	 	var _form = $id("form1");
  	 	if(arg!=""){
  	  	 	url="/mettingApply/tMettingApplyAction_insertMettingInfo.action?"+arg;	
  	  	    _form.action =url
  	        // 异步提交请求 
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
      					  	alert("操作成功!")
  					  		unMaskTop();
      					  if(flag==1)
      					  WEB.turnMainFrame();
  					  	}else if(data.indexOf("fails")>=0){
  					  	unMaskTop();
      					  	alert("操作失败!");
  					  	}else{	
  					  	unMaskTop(); 
  					  	    alert("操作失败!"); 	
  					  	}
  	   				} catch (Exception) {  
  					}  
  				},  
  			  	error : function(data){
  					unMaskTop();
  				  alert("添加失败请联系管理员！");
  			   	}  
  			}; 
  	  	$("#form1").ajaxSubmit(options);
  	  	}
     

		var rowId = 0;
		function addFile(tabid,varName){
		    var tab,row,td,fName,fId,tdStr;
		    var zs=$("#tabtest tbody tr").length;
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
		    tdStr += "<input type=\"button\" onclick=\"delTr('fileRow"+rowId+"');\" name='button"+rowId+"' value=\"删除\" style=\"margin-left:2px;vertical-align:middle;cursor:hand;\"/>";
		    td.innerHTML = tdStr;
		    rowId = rowId+1;    
		}
		
		function delTr(id){
			$("#"+id).remove();
		}

		function open_newyw_tree_fun1(){//方法名
		    var strUrl ="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2";
			var peArgument = [];
	   		 //机构
	    	 var paramEntity = new ParamEntity('Employee');
				paramEntity.setProperty('empid',$id("empid").value);
				paramEntity.setProperty('empname',$id("joinEmpname").value);
				peArgument[2]=[paramEntity,'empname','empid'];
	        //调用并传参
	        strUrl += "&time="+new Date().getTime();
			showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack1,500,430,'选择参会人员');
	}

		function open_newyw_tree_fun2(){//leader201409010
			var  strUrl = "/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=2&changeTree.showSelBox=2&changeTree.positioncode=FHLD";		
			var peArgument = [];
	    //机构
	    	 var paramEntity = new ParamEntity('Employee');
				paramEntity.setProperty('empid',$id("leaderid").value);
				paramEntity.setProperty('empname',$id("leadername").value);
				peArgument[0]=[paramEntity,'empname','empid'];
	        //调用并传参
	        strUrl += "&time="+new Date().getTime();
			showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack2,500,430,'选择参会领导');
	}
		
	function openNewEmpTreeCallBack1(arg){//回调方法
		if(arg!=''){
			if(arg['Employee']){ //原写法无需判断是否为空
			  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
			  		var argRes=[[],[],[],[]];
					for(var i=0;i<sorgidArra.length;i++){
						argRes[0].push(sorgidArra[i].getProperty("empid"));
						argRes[1].push(sorgidArra[i].getProperty("empname"));
					}
					$id("empid").value = argRes[0];
					$id("joinEmpname").value=argRes[1];
				}else{
					$id("empid").value = "";
					$id("joinEmpname").value = "";
				}
		}
	}

	function openNewEmpTreeCallBack2(arg){//回调方法
		if(arg!=''){
			if(arg['Employee']){ //原写法无需判断是否为空
		  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
		  		var argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("empid"));
					argRes[1].push(sorgidArra[i].getProperty("empname"));
				}
				$id("leaderid").value = argRes[0];
				$id("leadername").value=argRes[1];
			}else{
				$id("leaderid").value = "";
				$id("leadername").value = "";
			}
		}
	}

	//20140905 清空参会人员选择框
	function cleanEmp(){
		 $("#empid").val("");	
		 $("#joinEmpname").val("");	
	}

	//20140905 清空参会人员选择框
	function cleanLeader(){
		 $("#leaderid").val("");	
		 $("#leadername").val("");	
	}

	//20140923 限定会议时间大于当前时间
	 $id("mettingDate").onValidate=function(hiddenValue,TextValue)
		{
			if(this.getValue()=="") 
			 return true;
			else
			{
				var result = WEB.today();
			  if(this.getValue()<result) 
			     {
			      f_alert($id("mettingDate").text,"日期要大于当前时间");
			      return false;
			      }
			      else
			      return true;
			}

		}
	 function open_group_tree_fun(){
			strUrl ="/tree/initMainTree_mainTree.action?changeTree.showTabGroup=1&changeTree.showSelBox=2&changeTree.orgType=2";
			objName="群组选择";  
			var peArgument = [];
			var paramEntity = new ParamEntity('Employee');
			paramEntity.setProperty('empid',$id("empid").value);
			paramEntity.setProperty('empname',$id("joinEmpname").value);
			peArgument[2]=[paramEntity,'empname','empid'];	
			showModalCenter(strUrl,peArgument,openNewGroupTreeCallBack,500,430,objName);
 	}

	function openNewGroupTreeCallBack(arg){
		if(arg!=""){
     	if(arg['Employee']){ //原写法无需判断是否为空
		  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
		  		argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("empid"));
					argRes[1].push(sorgidArra[i].getProperty("empname"));
				}
				$id("empid").value = argRes[0];
				$id("joinEmpname").value = argRes[1];
				publishRange02=argRes[0];
			    objName02=argRes[1];
				//$id("relativeEmpid").value = argRes[1];
			}else{
				$id("joinEmpname").value = "";
				$id("empid").value = "";
			}
     }
	  }
 </script>
</html>