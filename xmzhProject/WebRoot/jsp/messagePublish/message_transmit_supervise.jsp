<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/commonUtil.js"></script>
<script>
$(document).ready(function(){
	  $.ajax({
	        url: '/messagePublish/tMessagePublishAction_querySuperviseEmp.action',
	        async: false,
	        type: 'post',
	       // data: "resourceType=${taskAssgineeDto.businessType}&resourceId=${message.messageId}",
	        dataType: 'json',
	        timeout: 60000,
	        success: function (emplist) {
	    	   var options="";
	    	   if(emplist!=null){
	    		   $('#rec1').css("display","block");
					 $('#rec2').css("display","none");
	    		   $.each(emplist,function( i,item ){
	        			options += "<option value='"+item.empId+"'>"+item.empName+"</option>";
	        		});
	        	$('#emp').append(options); 
	    	   }else{
	    		   $('#rec2').css("display","block");
					 $('#rec1').css("display","none");
	    	   }
	        	
	        }
    });
	  $.ajax({
	        url: '/jbpm/tProcessConfigAction_queryDefinitionId.action',
	        async: false,
	        type: 'post',
	        data: "businessType=${btnType}",
	        dataType: 'json',
	        timeout: 60000,
	        success: function (configs) {
	    	   var options="";
	    	   if(configs!=null){
	    		   $.each(configs,function( i,item ){
	        			options += "<option value='"+item.definitionId+"'>"+item.processName+"</option>";
	        		});
	        	$('#defind').append(options); 
	    	   }
	        }
  });
});


 function open_emp_checkcount_fun(){
	 var strUrl="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2";	
		var peArgument = {};
    	  var paramEntity = new ParamEntity('Employee');	
    	paramEntity.setProperty("empid",$id("empid").value);
    	paramEntity.setProperty("empname",$id("empname").value);
    	peArgument[2]=[paramEntity,'empname','empid'];
    	showModalCenter(strUrl,peArgument,openEmpCheckcountCallBack,500,430,'选择人员');
    }
    function openEmpCheckcountCallBack(arg){

		if(arg!=''){
	  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
	  		argRes=[[],[],[],[]];
			for(var i=0;i<sorgidArra.length;i++){
				argRes[0].push(sorgidArra[i].getProperty("empid"));
				argRes[1].push(sorgidArra[i].getProperty("empname"));
			}
				 $("#empid").val(argRes[0]);
				 $("#empname").val(argRes[1]);
		}

    }
/*     function doSave(){
        if($("#defind").val()==""){
            alert("请选择流程名称!");
            return false;
        }
        if($("#emp").val()!=""&&$("#emp").val()!=null){
            $("#empNames").val(WEB.trim($("#emp").text()));
            $("#empIds").val($("#emp").val());
        }else{
            if($id("empname").value==""){
                return false;
            }
        	 $("#empNames").val($id("empname").value);
             $("#empIds").val($id("empid").value);
        }
		var options = {
		  type : "post",
		  cache : "false",
		  timeout: 60000,
		  success : function(data){
			  try{
					if(data.indexOf("success")>0){
						alert("转督办成功！！");
						window.close();
					}else if(data.indexOf("ifnull")>0){
					    alert("查询不到该发布信息！");
					}else{
						alert("转督办失败！");
					}
				}catch(Exception){
				
				}
			},  
		  error : function(data){
			  alert("转督办出错，请联系管理员！");
		   }  
		}; 
		// 异步提交请求  
		$("#inputForm").ajaxSubmit(options);
	} */

	//20140908 修改
	function doSave(){
        if($("#defind").val()==""){
            alert("请选择流程名称!");
            return false;
        }
        if($("#emp").val()!=""&&$("#emp").val()!=null){
            $("#empNames").val(WEB.trim($("#emp option:selected").text()));
            $("#empIds").val($("#emp").val());
        }else{
            if($id("empname").value==""){
                return false;
            }
        	 $("#empNames").val($id("empname").value);
             $("#empIds").val($id("empid").value);
        }
		var options = {
		  type : "post",
		  cache : "false",
		  timeout: 60000,
		  success : function(data){
			  try{
					if(data.indexOf("success")>0){
						alert("转督办成功！！");
						window.close();
					}else if(data.indexOf("ifnull")>0){
					    alert("查询不到该发布信息！");
					}else{
						alert("转督办失败！");
					}
				}catch(Exception){
				
				}
			},  
		  error : function(data){
			  alert("转督办出错，请联系管理员！");
		   }  
		}; 
		// 异步提交请求  
		$("#inputForm").ajaxSubmit(options);
	}
	
    
</script>
<title>转督办</title>
</head>
<body>

<h:form name="inputForm" id="inputForm"
	action="/messagePublish/tMessagePublishAction_megTstSup.action" method="post"
	enctype="multipart/form-data" checkType="blur" target="_self" onsubmit="return checkForm(this);">
	<input type="hidden" name="_eosFlowAction" value="move">
	<h:hidden property="message.messageId" />
	<h:hidden property="message.opninion" />
	<h:hidden property="taskAssgineeDto.empNames" id="empNames"/>
	<h:hidden property="taskAssgineeDto.empIds" id="empIds"/>
	<h:hidden property="taskAssgineeDto.businessType"/>
	<h:hidden property="message.flowId"/>
	
	<table align="center" border="0" class="form_table" width="100%">
	    <tr>
			<td class="form_label" align="right" width="25%" id="queryName">流程名称:</td>
			<td colspan="2">
			  <select name="taskAssgineeDto.definitionId" id="defind"> </select>
			 </td>
		</tr>
		<tr>
			<td class="form_label" align="right" width="25%" id="queryName">督办员:</td>
			<td colspan="2">
			<div id="rec1">
			  <select name="emp" id="emp"> </select>
			 </div>
			<div id="rec2">
			<h:hidden property="empId" id="empid" /> 
			<h:text property="empName" id="empname" readonly="true" name="message.empName" />
			 <input type="button" value="选择" class="button" onclick="open_emp_checkcount_fun()" />
			 </div>
			 </td>
		</tr>
		<tr class="form_bottom">
		<td colspan="3">
	       <input type="button" value="提交" onclick="doSave()" class="button">
	    </td>
		</tr>

	</table>
</h:form>
</body>
</html>
