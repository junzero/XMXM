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
<title>设备申请</title>
</head>
  <body>
  <e:datasource name="euip" type="bean" path="com.gotop.euipApply.model.TApplyEuip" />
    <h:form name="form1" id="form1" action="/euipApply/tApplyEuipAction_insertEuipInfo.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
    <div id="content">
         <h:hidden property="euip.epId"/>
         <h:hidden property="euip.flowId"/>
	     <h:hidden property="euip.createDate"/>
	     <h:hidden property="euip.empId" id="createor"/>
        <h:hidden id="executionId" property="taskAssgineeDto.executionId"/>
        <h:hidden id="taskId"  property="taskAssgineeDto.nextTaskId"/>
        <h:hidden id="taskAssingee" property="taskAssgineeDto.taskAssingee"/>
        <h:hidden id="beginOrg" property="taskAssgineeDto.beginOrg"/>
        <h:hidden id="definitionId" property="taskAssgineeDto.definitionId"/>
        <h:hidden id="businessType" property="taskAssgineeDto.businessType"/>
          <h:hidden id="templateFileIds" property="taskAssgineeDto.templateFileIds"/>
		 <input type="hidden" id="btnType" name="btnType" />
		   <h:hidden name="isFirst" value="1" />
		<table align="center" border="0" width="100%" class="form_table" >
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		设备申请单
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right" style="width:120px;">
                                                     申请标题：
        </td>
        <td colspan="3">
          <h:text property="euip.epTitle" id="epTitle" validateAttr="allowNull=false" style="width:90%;" /><font style="color: red">*</font>	
        </td>
      </tr>
<%--       <tr>
      	<td class="form_label" align="right" style="width:120px;">
                                                     申请事项：
        </td>
        <td colspan="1">
          <h:text property="euip.epMatter" id="epMatter" validateAttr="allowNull=false" /><font style="color: red">*</font>	
        </td>
        	<td class="form_label" align="right" style="width:120px;">
                                                    设备申请类型：
        </td>
        <td colspan="1">
        <div id="selt" >
        <d:select  dictTypeId="ZHPT_EPAPPLY_TYPE"  property="euip.epType" extAttr="validateAttr='allowNull=false'" id="messageType" style="width:130px;" nullLabel="请选择"></d:select><font style="color: red">*</font>
        </div>
       <div id="dwite" >
        <d:write  dictTypeId="ZHPT_EPAPPLY_TYPE"  property="euip.epType" />
       
       </div>
        </td>
      </tr> --%>
<%--        <tr>
      	<td class="form_label" align="right" style="width:120px;">
                                                     数量：
        </td>
        <td colspan="1">
          <h:text property="euip.epNumber" id="epNumber" validateAttr="allowNull=false" /><font style="color: red">*</font>	
        </td>
        	<td class="form_label" align="right" style="width:120px;">
                                                   预算金额：
        </td>
        <td colspan="1">
         <h:text property="euip.epBudget" id="epBudget" validateAttr="allowNull=false" /><font style="color: red">*</font>	
        </td>
      </tr> --%>
       <tr>
        <td class="form_label" align="right" style="width:120px;">
                                                   申请人：
        </td>
        <td colspan="1">
         <h:text property="euip.empName" id="empName" validateAttr="allowNull=false" readonly="true" style="width:130px;" /><font style="color: red">*</font>	
        </td>
        <td class="form_label" align="right" style="width:120px;">
                                                   申请部门：
        </td>
        <td colspan="1">
         <h:text property="euip.orgName" id="orgName" validateAttr="allowNull=false" readonly="true" style="width:130px;" /><font style="color: red">*</font>	
        </td>
      </tr>
      <tr>
      <td class="form_label" align="right" style="width:120px;">
                                                    联系电话：
        </td>
        <td colspan="1">
          <h:text property="euip.phoneNo" id="phoneNo" value="${sessionScope.login_user.mobi }" validateAttr="allowNull=false" style="width:130px;" /><font style="color: red">*</font>	
        </td>
        
        <td class="form_label" align="right" style="width:120px;">
                                                    申请时间：
        </td>
        <td colspan="1">
        <div id="inputTime">
	         <w:date  id="applyTime" submitFormat="yyyy-MM-dd" format="yyyy-MM-dd" readonly="true" allowNull="false" property="euip.applyTime"/>
         </div>
        <%--   <div id="hiddenTime" style="display: none">
         	<h:text property="euip.applyTime" readonly="true"/>
         </div><font style="color: red">*</font> --%>
        </td>
      </tr>
      <tr>
     	<td class="form_label" align="right">内容：</td>
     	<td colspan="3">
	     	<h:textarea property="euip.epReason" id="epReason"  extAttr="class='h80' "  validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;" />
		    <font style="color: red">*</font>
     	</td>
      </tr>
         <tr id="row1">
      <td class="form_label" align="right">附件下载：</td>
      <td colspan="3">
      <div id="tag"></div>
      </td>
      </tr>
     <tr id="fujian">
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
      <tr id="row3">
     	<td class="form_label" align="right">意见：</td>
     	<td colspan="3">
	     	<h:textarea property="euip.opninion"   extAttr="class='h80' " name="euip.opninion" id="opinion" validateAttr="maxLength=512" rows="4"  style="width:90%;"/>
		    <font style="color: red">*</font>
     	</td>
      </tr>
      <tbody id="dopiRow">
     	<%@include file="/jsp/util/default_opinionUtil.jsp" %>
     </tbody>
      <tr class="form_bottom">
        <td colspan="4">
          <input type="button" value="结束流程" class="button" onclick="doDeleteProcess('<b:write property="taskAssgineeDto/businessKey" />','07');" id="deleteProcessBtn">
          <input type="button" value="保存" class="button" id="save1" onclick="doSave(1);"  />
          <input type="button" value="提交" onclick="doSave(2);" class="button" id="smit" />
          <input type="button" value="查看流程" onclick="doflowpic();" class="button" id="flowpic" />
         </td>
      </tr>
       <tr id="row2">
     <td class="form_label" align="right">流 程 列 表：</td>
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
		if('${sessionScope.login_user.empid}'=='${euip.empId}'){
			  if($("#executionId").val()){
				  //当登录人等于发起人，显示结束流程按钮
				  $("#deleteProcessBtn").show();
		  	    }
		}
	 if('${euip.epId}'!=""){
		 if('${euip.empId}'!='${sessionScope.login_user.empid}')
		 $("#save1").css("display","none"); 
		 $.ajax({
		        url: '/file/tFileResourceTableAction_queryFileList.action',
		        async: false,
		        type: 'post',
		        data: "resourceType=${taskAssgineeDto.businessType}&resourceId=${euip.epId}",
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
		  $("#beginOrg").val("${euip.orgid}");	
		  //判断
		  WEB.doDisabledAttr('${isView}','${euip.empId}','${sessionScope.login_user.empid}',"opinion");  
		  WEB.doConditionDisplay('${euip.empId}','${sessionScope.login_user.empid}',"row3,dopiRow","none");//隐藏意见
		  if('${euip.empId}'!='${sessionScope.login_user.empid}'){
			 //$("#opninion").val("已处理");
			 }
	 }else{
		
		 $("#row1").css("display","none");  
		 $("#row2").css("display","none");  
		 $("#row3").css("display","none");  
		 $("#dopiRow").css("display","none");  
		 $("#beginOrg").val('${sessionScope.login_user.orgid}'); 
	 }
	 if('${euip.empName}'==""){
		 $("#empName").val('${sessionScope.login_user.empname}');
	 }
	 if('${euip.orgName}'==""){
		 $("#orgName").val('${sessionScope.login_user.orgname}');
	 }
	 if('${isView}'!=''){
		 $("#deleteProcessBtn").hide();
		$("#save1").hide();
		$("#smit").hide();
		$("#fujian").hide();
		$("#row1").show();
		$("#hiddenTime").show();
		$("#rowTemplate").hide();
	}
});

function initPlanCell20(){
		var queryCond="";
			queryCond += "<resourceId>${euip.epId}</resourceId>";
		 	queryCond += "<resourceType>${taskAssgineeDto.businessType}</resourceType>";
			return queryCond;
		}

     function doSave(value){
    		$("#btnType").val(value);
    		if(value!="1"){
    			if(checkForm($id("form1"))){
    			var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value+"&taskAssgineeDto.beginOrg="+$("#beginOrg").val()+"&taskAssgineeDto.beginAssingee="+$("#createor").val()+"&taskAssgineeDto.definitionId=${taskAssgineeDto.definitionId}";
        		showModalCenter(strUrl, null, taskAssigneeCallBack, 700, 400, '节点选择');
    			}
    		}else{
    			var _form = $id("form1");
    	  	  	 	url="/euipApply/tApplyEuipAction_insertEuipInfo.action";	
    	  	  	    _form.action =url
    	  	  	if(checkForm($id("form1")))
    			    ajaxsubmitO(0);
    	  	 	}
     }
 	function taskAssigneeCallBack(arg){
  	 	var _form = $id("form1");
  	 	if(arg!=""){
  	  	 	url="/euipApply/tApplyEuipAction_insertEuipInfo.action?"+arg;	
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

		$id("applyTime").onValidate=function(hiddenValue,TextValue)
		{
			if(this.getValue()=="") 
			 return true;
			else
			{
				var result = WEB.today();
			  if(this.getValue()<result) 
			     {
			      f_alert($id("applyTime").text,"日期要大于当前时间");
			      return false;
			      }
			      else
			      return true;
			}

		}
		
 </script>
</html>