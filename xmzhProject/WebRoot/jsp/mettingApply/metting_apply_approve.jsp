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
		 <input type="hidden" id="btnType" name="btnType" />
		 <input type="hidden" id="isFirst" name="isFirst" />
		<table align="center" border="0" width="100%" class="form_table" >
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		会议信息
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right" style="width:120px;">
                                                     会议标题：
        </td>
        <td colspan="3">
          <h:text property="meet.title" id="title" validateAttr="allowNull=false" style="width:90%;" /><font style="color: red">*</font>	
        </td>
      </tr>
      <tr>
        	<td class="form_label" align="right" style="width:120px;">
                                                    会议类型：
        </td>
        <td colspan="1">
        	<d:write property="meet.type" dictTypeId="ZHPT_METTING_TYPE" />
        	<h:hidden property="meet.type"/>
        </td>
        <td class="form_label" align="right">
                                                    会议室名称：
        </td>
        <td>
             <d:write  property="meet.mname" dictTypeId="ZHPT_METTING_NAME" />
             <h:hidden property="meet.mname"/>
        </td>
      </tr>
       <tr>
        <td class="form_label" align="right" style="width:120px;">
                                                   申请人：
        </td>
        <td colspan="1">
         <h:text property="meet.empName" id="empName" validateAttr="allowNull=false" readonly="true" style="width:130px;" /><font style="color: red">*</font>	
        </td>
      	<td class="form_label" align="right" >
                                                    会议时间：
        </td>
        <td colspan="1">
         <h:text id="showTime"  readonly="true" property="meet.mettingTime"/>
        </td>
      </tr>
      <tr>
     	<td class="form_label" align="right">内容：</td>
     	<td colspan="3">
	     	<h:textarea   extAttr="class='h80' "  property="meet.content" id="content" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;" />
		    <font style="color: red">*</font>
     	</td>
      </tr>
      <tr>
        <td class="form_label" align="right">参会行领导：</td>
			<td colspan="3">
			  <h:textarea  extAttr="class='h80' "  property="meet.joinLeadername" id="leadername" readonly="true" validateAttr="maxLength=512" rows="4"  style="width:90%;" />
			  <h:hidden id="leaderid" property="meet.joinLeader" />
			</td>
	   </tr>
       <tr>
        <td class="form_label" align="right">参会人员：</td>
			<td colspan="3">
			  <h:textarea  extAttr="class='h80' "  property="meet.joinEmpname" id="empname" readonly="true" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;" />
			  <h:hidden id="empid" property="meet.joinEmp" />
	   </tr>
         <tr id="row1">
      <td class="form_label" align="right">附件下载：</td>
      <td colspan="3">
      <div id="tag"></div>
      </td>
      </tr>
     <tr>
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
	     	<h:textarea property="meet.opninion" extAttr="class='h80'" name="meet.opninion" id="opinion" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;"/>
		    <font style="color: red">*</font>
     	</td>
      </tr>
     	<%@include file="/jsp/util/default_opinionUtil.jsp" %>
      <tr class="form_bottom">
        <td colspan="4">
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
	 if('${meet.mettingId}'!=""){
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
			    	        	$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId});
			          		});	
			        }else{
			        	 $("#row1").css("display","none");  
			        }
		        }
	    });	
		  $("#beginOrg").val("${meet.orgid}");	
		  //判断
		  WEB.doDisabledAttr('${isView}','${meet.creator}','${sessionScope.login_user.empid}',"opinion");  
		  //WEB.doConditionDisplay('${meet.creator}','${sessionScope.login_user.empid}',"row3","none");//隐藏意见
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
		$("#save1").hide();}
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
    			    ajaxsubmitO();
    	  	 	}
    		}
     }
 	function taskAssigneeCallBack(arg){
  	 	var _form = $id("form1");
  	 	if(arg!=""){
  	  	 	url="/mettingApply/tMettingApplyAction_insertMettingInfo.action?"+arg;	
  	  	    _form.action =url
  	        // 异步提交请求 
  	  	    ajaxsubmitO();
  	 	}
  	   }

  	  	function ajaxsubmitO(){
  	  	maskTop();
  	  	 var options = {
  		  		type : "post",
  		  		cache : "false",
  			  	success : function(data){
  					try {  
  					  	if(data.indexOf("success")>=0){
      					  	alert("操作成功!")
  					  		unMaskTop();
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
				paramEntity.setProperty('empname',$("#empname").val());
				peArgument[2]=[paramEntity,'empname','empid'];
	        //调用并传参
	        strUrl += "&time="+new Date().getTime();
			showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack1,500,430,'选择参会人员');
	}
	function openNewEmpTreeCallBack1(arg){//回调方法
		if(arg['Employee']){ //原写法无需判断是否为空
		  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
		  		argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("empid"));
					argRes[1].push(sorgidArra[i].getProperty("empname"));
				}
				$id("empid").value = argRes[0];
				$("#empname").val(argRes[1]);
			}else{
				$id("empid").value = "";
				$id("empname").value = "";
			}
	}

	//20140905 清空参会人员选择框
	function cleanEmp(){
		 $("#empid").val("");	
		 $("#empname").val("");	
	}
	
 </script>
</html>