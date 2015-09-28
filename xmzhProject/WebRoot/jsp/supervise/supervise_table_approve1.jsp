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
<title>信息发布</title>
</head>
  <body>
  <e:datasource name="supervise" type="bean" path="com.gotop.supervise.model.TSuperviseTable" />
    <h:form name="form1" id="form1" action="/supervise/tSuperviseTableAction_insertSuperviseInfo.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
         <h:hidden property="supervise.superviseId"/>
         <h:hidden property="supervise.flowId"/>
	     <h:hidden property="supervise.createDate"/>
	      <h:hidden property="supervise.createor" id="createor"/>
        <h:hidden id="executionId" property="taskAssgineeDto.executionId"/>
        <h:hidden id="taskId"  property="taskAssgineeDto.nextTaskId"/>
        <h:hidden id="beginOrg" property="taskAssgineeDto.beginOrg"/>
        <h:hidden property="supervise.createor"/>
        <h:hidden id="taskAssingee" property="taskAssgineeDto.taskAssingee"/>
        <h:hidden id="definitionId" property="taskAssgineeDto.definitionId"/>
        <h:hidden id="businessType" property="taskAssgineeDto.businessType"/>
        <h:hidden id="processTaskAssigneeId" property="taskAssgineeDto.processTaskAssigneeId"/>
        <h:hidden id="parentId" property="taskAssgineeDto.parentId"/>
        <h:hidden id="isChild" property="taskAssgineeDto.isChild"/>
		 <input type="hidden" id="btnType" name="btnType" />
		<table align="center" border="0" width="100%" class="form_table" >
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		 督办单
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right" style="width:120px;" readonly="true">
                                                     督办事项：
        </td>
        <td colspan="3">
          <h:text property="supervise.superviseItem" id="superviseTitle" validateAttr="allowNull=false" style="width:90%;" /><font style="color: red">*</font>	
        </td>
      </tr>
       <tr>
        <td class="form_label" align="right">主办机构：</td>
			<td colspan="3">
			 <h:text id="orgname" property="supervise.impUnitName" readonly="true" />
			  <h:hidden id="orgcode" property="supervise.impUnit" />
			  <h:hidden id="orgid" property="supervise.impOrgid" />
			  <!--
			  <a href="#" onclick="open_newyw_tree_fun1()">选择</a>
			--></td>
	   </tr>
       <tr>
        <td class="form_label" align="right">协办机构：</td>
			<td colspan="3">
			 <h:text id="orgname1" property="supervise.conUnitName" readonly="true" />
			  <h:hidden id="orgcode1" property="supervise.coUnit" />
			  <h:hidden id="orgid1" property="supervise.coOrgid" />
			  <!--<a href="#" onclick="open_newyw_tree_fun2();">选择</a>
			--></td>
        </tr>
        <tr>
        <td class="form_label" align="right" style="width:14%;">
                                                     完成时限：
        </td>
        <td colspan="1" style="width:36%;">
        <b:write property="supervise.completeDate" formatPattern="yyyy-MM-dd" />
        <h:hidden property="supervise.completeDate"/>
        </td>
      	<td class="form_label" align="right" style="width:14%;">
                                                    联系电话：
        </td>
        <td colspan="1" style="width:36%;">
          <h:text property="supervise.linkerPhone" id="linkerPhone"  readonly="true" validateAttr="allowNull=false" style="width:130px;" /><font style="color: red">*</font>	
        </td>
      </tr>
      <tr>
       <td class="form_label" align="right" style="width:120px;">
                                                   提醒行领导：
        </td>
        <td colspan="3">
        <div id="rec1">
			 </div>
        </td>
      </tr>
      <tr>
     	<td class="form_label" align="right">内容：</td>
     	<td colspan="3">
	     	<h:textarea property="supervise.content"  readonly="true" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;" />
		    <font style="color: red">*</font>
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
      <tr id="row3">
     	<td class="form_label" align="right">意见：</td>
     	<td colspan="3">
	     	<h:textarea property="supervise.opninion" id="opinion"   name="supervise.opninion" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;"/>
		    <font style="color: red">*</font>
     	</td>
      </tr>
     	<%@include file="/jsp/util/default_opinionUtil.jsp" %>
      <tr class="form_bottom">
        <td colspan="4">
            <input type="button" value="提交" onclick="doSave(2);" class="button" id="smit" />
          <!--   <input type="button" value="保存" onclick="saveSupervise();" class="button" id="smit2" /> -->
         <!--   <input type="button" value="内部督办" onclick="dosupervise();" class="button" id="smit1" /> -->
         <!-- 20140907 修改名称 -->
          <input type="button" value="反馈" onclick="saveSupervise();" class="button" id="smit2" />
           <input type="button" value="部室处理" onclick="dosupervise();" class="button" id="smit1" />
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
    </h:form>
  </body>
 <script type="text/javascript">
 var chk_value = [];
 $(document).ready(function(){
	  $.ajax({
	        url: '/supervise/tSuperviseTableAction_queryLeader.action',
	        async: false,
	        type: 'post',
	        data: "positionCode=FHLD",
	        dataType: 'json',
	        timeout: 60000,
	        success: function (emplist) {
	    	   var options="";
	    	   if(emplist!=null){
			    		  $.each(emplist,function( i,item ){
	    						  options += "<input name ='empLeaderIds' type='checkbox' id='emp"+i+"'value='"+WEB.trim(item.empid)+"'><span id='span"+i+"'>"+WEB.trim(item.empname)+"</span>";
  		        		});
			    		  $('#rec1').append(options); 
				    }
	    			}
});
		//选中行领导
		var remindIds = '${supervise.remindId}';
		var remindIdsArray= new Array();   
		remindIdsArray=remindIds.split(",");     
		   for (i=0;i<remindIdsArray.length ;i++ ) {    
			 $('input[name="empLeaderIds"]').each(function(){//遍历每一个名字为empLeaderIds的复选框，其中选中的执行函数    
				if($(this).val() == remindIdsArray[i]){
					$(this).attr("checked",'true');
				} 
				$(this).attr("disabled",'disabled');
			 }); 
		   }
 	 if('${supFlag}'){
		 $("#smit1").hide();
		 $("#smit2").hide();
		 $("#smit").hide();
		 WEB.hideFile();
	}
		 $.ajax({
		        url: '/file/tFileResourceTableAction_queryFileList.action',
		        async: false,
		        type: 'post',
		        data: "resourceType=${taskAssgineeDto.businessType}&resourceId=${supervise.superviseId}",
		        dataType: 'json',
		        timeout: 60000,
		        success: function (files) {
			        if(files!=""){
			         	$.each(files,function( i,item ){
			         		if('${sessionScope.login_user.empid}'!=item.creator)
			    	        	$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId});
				         	else
				         		 if('${isView}'!=''){
					         			$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId});
						         	}else{
						         		$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId,remove:1});
							         }
			          		});	
			        }else{
			        	 $("#row1").css("display","none"); 
			        }
		        }
	    });
		 $("#beginOrg").val("${supervise.orgid}");	
		 if('${isView}'!=''){
			 
			 $("#smit").css("display","none");
			 $("#smit1").css("display","none");
			 $("#smit2").css("display","none");
			 WEB.hideFile();
		 }else{
			 
			 if('${taskAssgineeDto.isChild}'=='0'){
				 $("#smit2").css("display","none");
			 }else{
				 $("#smit").css("display","none");
			 }
				 
		 }
});

function initPlanCell20(){
		var queryCond="";
		queryCond += "<resourceId>${supervise.superviseId}</resourceId>";
		queryCond += "<resourceType>${taskAssgineeDto.businessType}</resourceType>";
			return queryCond;
		}

/* function dosupervise(){
	var strUrl = "/supervise/tSuperviseTableAction_queryEmpJsp.action?taskAssgineeDto.executionId=${taskAssgineeDto.executionId}&taskAssgineeDto.taskId=${taskAssgineeDto.nextTaskId}&taskAssgineeDto.parentId=${taskAssgineeDto.parentId}&taskAssgineeDto.processTaskAssigneeId=${taskAssgineeDto.processTaskAssigneeId}&taskAssgineeDto.businessType=${taskAssgineeDto.businessType}&supervise.superviseId=${supervise.superviseId}";
	showModalCenter(strUrl, null, null, 500, 150, '转内部督办');
} */
/* //20140907 修改
function dosupervise(){
	var strUrl = "/supervise/tSuperviseTableAction_queryEmpJsp.action?taskAssgineeDto.executionId=${taskAssgineeDto.executionId}&taskAssgineeDto.taskId=${taskAssgineeDto.nextTaskId}&taskAssgineeDto.parentId=${taskAssgineeDto.parentId}&taskAssgineeDto.processTaskAssigneeId=${taskAssgineeDto.processTaskAssigneeId}&taskAssgineeDto.businessType=${taskAssgineeDto.businessType}&supervise.superviseId=${supervise.superviseId}";
	showModalCenter(strUrl, null, winClose, 500, 150, '部室办理');
} */

//20140907 修改
function dosupervise(){
	if($("#opinion").val()==""){
		alert("意见不能为空！");
		$("#opinion").focus();
		return false;
	}
	var strUrl = "/supervise/tSuperviseTableAction_queryEmpJsp.action?taskAssgineeDto.executionId=${taskAssgineeDto.executionId}&taskAssgineeDto.taskId=${taskAssgineeDto.nextTaskId}&taskAssgineeDto.parentId=${taskAssgineeDto.parentId}&taskAssgineeDto.processTaskAssigneeId=${taskAssgineeDto.processTaskAssigneeId}&taskAssgineeDto.businessType=${taskAssgineeDto.businessType}&supervise.superviseId=${supervise.superviseId}&supervise.opninion="+encodeURI($("#opinion").val());
	showModalCenter(strUrl, null, winClose, 600,300, '部室办理');
}

//20140907 新增
function winClose(arg){
	if(arg!=''){
		var _form = $id("form1");
	 	url="/supervise/tSuperviseTableAction_insertSuperviseFile.action";	
	    _form.action =url
      // 异步提交请求 
	    ajaxsubmitForFile();
	}
	//window.close();
}

/* function saveSupervise(){
	maskTop();
	  $.ajax({
	        url: '/supervise/tSuperviseTableAction_bushiSaveHandle.action',
	        async: false,
	        type: 'post',
	        data: "taskAssgineeDto.executionId=${taskAssgineeDto.executionId}&taskAssgineeDto.taskId=${taskAssgineeDto.nextTaskId}&taskAssgineeDto.parentId=${taskAssgineeDto.parentId}&taskAssgineeDto.businessType=${taskAssgineeDto.businessType}&supervise.superviseId=${supervise.superviseId}",
	        dataType: 'text',
	        timeout: 60000,
	        success: function (data) {
	    	  if(data.indexOf("success")>=0){
		    	  alert("操作成功!");
		    	  unMaskTop();
			  		window.close();
	    	  }else{
	    		  unMaskTop();
		    	  alert("操作失败");
	    	  }
	        }
  });
} */
/*
 *  部门人员办理反馈给部门主要负责人
 */
function saveSupervise(){
	if($("#opinion").val()==""){
		alert("意见不能为空!");
		$("#opinion").focus();
		return false;
	}
maskTop();
$.ajax({
	        url: '/supervise/tSuperviseTableAction_bushiSaveHandle.action',
	        async: false,
	        type: 'post',
	        data: "taskAssgineeDto.executionId=${taskAssgineeDto.executionId}&taskAssgineeDto.processTaskAssigneeId=${taskAssgineeDto.processTaskAssigneeId}&taskAssgineeDto.taskId=${taskAssgineeDto.nextTaskId}&taskAssgineeDto.parentId=${taskAssgineeDto.parentId}&taskAssgineeDto.businessType=${taskAssgineeDto.businessType}&supervise.superviseId=${supervise.superviseId}&supervise.opninion="+encodeURI($("#opinion").val()),
	        dataType: 'text',
	        timeout: 60000,
	        success: function (data) {
	    	  if(data.indexOf("success")>=0){
		    	  alert("操作成功!");
		    	  unMaskTop();

			  		 winClose();
	    	  }else{
	    		  unMaskTop();
		    	  alert("操作失败");
	    	  }
	        }											
  });		  									
}


function doSave(value){
	 chk_value = [];
     $('input[name="empLeaderIds"]:checked').each(function(){//遍历每一个名字为empLeaderIds的复选框，其中选中的执行函数    
     chk_value.push($(this).val());//将选中的值添加到数组chk_value中    
     });
	if($("#orgcode").val()==""){
 	alert("主办单位不能为空！");
		$("#superviseType").focus();
		return false;
	}
/* 	if($("#orgcode1").val()==""){
    	alert("协办单位不能为空！");
		return false;
		} */
	if($("#opinion").val()==""){
		alert("意见不能为空!");
		$("#opinion").focus();
		return false;
	}
	$("#btnType").val(value);
	if(value!="1"){
		if(checkForm($id("form1"))){
		var dynamicOrgIds=$("#orgid").val()+","+$("#orgid1").val();
		var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value+"&taskAssgineeDto.beginOrg="+$("#beginOrg").val()+"&taskAssgineeDto.dynamicOrgIds="+dynamicOrgIds+"&taskAssgineeDto.beginAssingee="+$("#createor").val();
		showModalCenter(strUrl, null, taskAssigneeCallBack, 700, 400, '节点选择');
		}
	}else{
		var _form = $id("form1");
  	  	 	url="/supervise/tSuperviseTableAction_insertSuperviseInfo.action"+ "?supervise.remindId=" + chk_value;	
  	  	    _form.action =url
  	  	if(checkForm($id("form1")))
		    ajaxsubmitO();
  	 	}
}
function taskAssigneeCallBack(arg){
	var _form = $id("form1");
	if(arg!=""){
	 	url="/supervise/tSuperviseTableAction_insertSuperviseInfo.action?"+arg+ "&supervise.remindId=" + chk_value;		
	    _form.action =url
      // 异步提交请求 
	    ajaxsubmitO();
	}
 }
function ajaxsubmitForFile(){
	maskTop();
 var options = {
  		type : "post",
  		cache : "false",
	  	success : function(data){
			try {  
			  	if(data.indexOf("success")>=0){
				  	 unMaskTop();
				  		/**
				  		*2014.9.1 改成弹出的方式
				  		*/
				  		window.close();
				  /* WEB.turnMainFrame(); */
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
	function ajaxsubmitO(){
		maskTop();
	 var options = {
	  		type : "post",
	  		cache : "false",
		  	success : function(data){
				try {  
				  	if(data.indexOf("success")>=0){
					  	alert("操作成功!");
					  	 unMaskTop();
					  		/**
					  		*2014.9.1 改成弹出的方式
					  		*/
					  		window.close();
					  /* WEB.turnMainFrame(); */
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


 </script>
</html>