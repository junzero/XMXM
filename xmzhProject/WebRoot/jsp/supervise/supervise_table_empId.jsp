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
	        url: '/supervise/tSuperviseTableAction_queryTaskAssignPerson.action',
	        async: false,
	        type: 'post',
	        data: "possionId=3",
	        dataType: 'json',
	        timeout: 60000,
	        success: function (emplist) {
	    	   var options="";
	    	   if(emplist!=null){
	    		  // $('#rec1').css("display","block");
					// $('#rec2').css("display","none");
	    		   $.each(emplist,function( i,item ){
	        			options += "<input type='checkbox' id='emp"+i+"' name='taskAssgineeDto.empIds' value='"+WEB.trim(item.empid)+"'><span id='span"+i+"'>"+WEB.trim(item.empname)+"</span><br>";
	        		});
	        	$('#rec1').append(options); 
	    	   }//else{
	    		  // $('#rec2').css("display","block");
					// $('#rec1').css("display","none");
	    	  // }
	        	
	        }
    });
});


 function open_emp_checkcount_fun(){
	 var strUrl="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2&changeTree.startOrgid=${sessionScope.login_user.orgid}";	
		var peArgument = {};
    	  var paramEntity = new ParamEntity('Employee');	
    	paramEntity.setProperty("empid",$id("empid").value);
    	paramEntity.setProperty("empname",$id("empname").value);
    	peArgument[2]=[paramEntity,'empname','empid'];
    	showModalCenter(strUrl,peArgument,openEmpCheckcountCallBack,500,430,'选择人员');
    }
    function openEmpCheckcountCallBack(arg){
  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
  		argRes=[[],[],[],[]];
		for(var i=0;i<sorgidArra.length;i++){
			argRes[0].push(sorgidArra[i].getProperty("empid"));
			argRes[1].push(sorgidArra[i].getProperty("empname"));
		}
			 $("#empid").val(argRes[0]);
			 $("#empname").val(argRes[1]);

    }
/*     function doSave(){
     
        var empObj=$("input[name='taskAssgineeDto.empIds']");
        if(empObj!=null){
            var empnames="";
        	 for(var i=0;i<empObj.length;i++){
                 if(empObj[i].checked){
                	 empnames+=$("#span"+i).text()+",";   
             }
        }
        	 $("#empNames").val(empnames.substring(0,empnames.length-1));
        }
        if($("#empNames").val()==""){
            alert("请选择人员");
            return false;
        }
		var options = {
		  type : "post",
		  cache : "false",
		  timeout: 60000,
		  success : function(data){
			  try{
					if(data.indexOf("success")>0){
						alert("转内部督办成功！！");
						window.close();
					}else if(data.indexOf("fails")>0){
					    alert("系统出错！");
					}else{
						alert(data);
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

	//20140907 修改
	function doSave(){
	     
        var empObj=$("input[name='taskAssgineeDto.empIds']");
        if(empObj!=null){
            var empnames="";
        	 for(var i=0;i<empObj.length;i++){
                 if(empObj[i].checked){
                	 empnames+=$("#span"+i).text()+",";   
             }
        }
        	 $("#empNames").val(empnames.substring(0,empnames.length-1));
        }
        if($("#empNames").val()==""){
            alert("请选择人员");
            return false;
        }
    
		var options = {
		  type : "post",
		  cache : "false",
		  timeout: 60000,
		  success : function(data){
			  try{
					if(data.indexOf("success")>0){
						alert("操作成功！！");
					 	//刷新首页
						//window.top.frames["mainFrame"].location.href = "${pageContext.request.contextPath }/home/homeInfoAction_getHomeInfo.action";
						window.close();
					}else if(data.indexOf("fails")>0){
					    alert("操作失败！");
					}else{
						alert("系统出错！");
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
	action="/supervise/tSuperviseTableAction_insertTaskAssignPerson.action" method="post"
	enctype="multipart/form-data" checkType="blur" target="_self" onsubmit="return checkForm(this);">
	<input type="hidden" name="_eosFlowAction" value="move">
	<h:hidden id="processTaskAssigneeId" property="taskAssgineeDto.processTaskAssigneeId"/>
    <h:hidden id="parentId" property="taskAssgineeDto.parentId"/>
     <h:hidden id="empNames" property="taskAssgineeDto.empNames"/>
    <h:hidden id="executionId" property="taskAssgineeDto.executionId"/>
    <h:hidden id="taskId"  property="taskAssgineeDto.taskId"/>
    <h:hidden property="supervise.superviseId"/>
    <h:hidden id="businessType" property="taskAssgineeDto.businessType"/>
    
	<h:hidden property="supervise.opninion"/>
	
	<table align="center" border="0" class="form_table" width="100%">
		<tr>
			<td class="form_label" align="right" width="25%" id="queryName">部门人员:</td>
			<td colspan="2">
			<div id="rec1">
			
			 </div>
			<!--<div id="rec2">
			<h:hidden property="empId" id="empid" /> 
			<h:text property="empName" id="empname" readonly="true" name="message.empName" />
			 <input type="button" value="选择" class="button" onclick="open_emp_checkcount_fun()" />
			 </div>
			 --></td>
		</tr>
		<tr class="form_bottom">
		<td colspan="3">
	       <input type="button" value="提交" onclick="doSave()" class="button"/> 
	    </td>
		</tr>

	</table>
</h:form>
</body>
</html>
