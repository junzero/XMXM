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
<title>月报申请</title>
<style type="text/css">
#tag1 {
width: 100%;
height: 30px;
text-align: left;
padding: 10px;
line-height: 25px;
}
#tag2 {
width: 100%;
height: 30px;
text-align: left;
padding: 10px;
line-height: 25px;
}
#reportMonth_input_input{
width: 0px;
}

</style>
</head>
  <body>
  <e:datasource name="monthReports" type="bean" path="/xmzhProject/src/com/gotop/monthReport/model/TWorkMonthReports.java" />
    <h:form name="form1" id="form1" action="/monthReport/tWorkMonthReportsAction_insertMonthReportsInfo.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
         <h:hidden property="monthReports.reportId"/>
         <h:hidden property="monthReports.createor" id="createor"/>
         <h:hidden property="monthReports.flowId"/>
	     <h:hidden property="monthReports.createDate"/>
         <h:hidden property="monthReports.createTime"/>
         <h:hidden property="isFirst"/>
         <!-- 起草人机构 -->
	     <h:hidden id="beginOrg" property="taskAssgineeDto.beginOrg"/>
	     <!-- 流程实例ID -->
         <h:hidden id="executionId" name="taskAssgineeDto.executionId" property="taskAssgineeDto.executionId"/>
         <!-- 下一节点ID -->
         <h:hidden id="taskId" name="taskAssgineeDto.nextTaskId"  property="taskAssgineeDto.nextTaskId"/>
         <!-- 办理人ID -->
         <h:hidden id="taskAssingee" name="taskAssgineeDto.taskAssingee" property="taskAssgineeDto.taskAssingee"/>
         <!-- 操作类型 1为保存、2为提交审核 -->
		<h:hidden id="btnType" name="btnType" />
        <h:hidden id="definitionId" name="definitionId" property="taskAssgineeDto/definitionId"/>
        <h:hidden id="businessType" name="businessType" property="taskAssgineeDto/businessType"/>
             <h:hidden id="templateFileIds" property="taskAssgineeDto.templateFileIds"/>
        <h:hidden id="isFirst" name="isFirst" value="1"/>
		<table align="center" border="0" width="100%" class="form_table" >
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		 工作月报
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right" style="width:120px;">
                                                     标 题：
        </td>
        <td colspan="3">
          <h:text property="monthReports.reportTitle" id="reportTitle" validateAttr="allowNull=false" style="width:90%;" /><font style="color: red">*</font>	
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right" style="width:120px;">
                                                     上报人：
        </td>
        <td colspan="1">
          <h:text property="monthReports.createName" id="createName" readonly="true" value="${sessionScope.login_user.empname }" />	
        </td>
        <td class="form_label" align="right">
                                                     上报部门：
        </td>
        <td colspan="1">
          <h:text property="monthReports.orgname" id="orgname" readonly="true" value="${sessionScope.login_user.orgname }" />	
        </td>
      </tr>
      <tr>
     	<td class="form_label" align="right">内容：</td>
     	<td colspan="3">
     	        <h:hidden property="monthReports.reportMonth" id="reportMonth" />
	     	<h:textarea property="monthReports.content"  extAttr="class='h80' "  validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;" />
		    <font style="color: red">*</font>
     	</td>
      </tr>
      <tr id="row1">
      <td class="form_label" align="right">月报附件：</td>
      <td colspan="3">
      <div id="tag1"></div>
      </td>
      </tr>
      <!-- <tr id="row3">
      <td class="form_label" align="right">上月完成情况附件：</td>
      <td colspan="3">
      <div id="tag2"></div>
      </td>
      </tr> -->
     <tr>
     <tr id="rowadd">
     	<td class="form_label" align="right">工作月报：</td>
     	<td colspan="3">
				<input type="button" onclick="addFile('tabtest1','monthReportFiles');return false;" value="新增附件" 
					style="margin-left:2px;vertical-align:middle;cursor:hand;"/>
				<font style="color: red">(说明：最多上传5个附件)</font>
				<br/>
				<table border=0 id="tabtest1">
				</table>
     	</td>
     </tr>
     <!-- <tr>
     	<td class="form_label" align="right">上月完成情况：</td>
     	<td colspan="3">
				<input type="button" onclick="addFile('tabtest2','completionFiles');return false;" value="新增附件" 
					style="margin-left:2px;vertical-align:middle;cursor:hand;"/>
				<font style="color: red">(说明：最多上传5个附件)</font>
				<br/>
				<table border=0 id="tabtest2">
				</table>
     	</td>
     </tr> -->
                         <tr id="rowTemplate">
					<td class="form_label" align="right">模板文件：</td>
					<td colspan="3"><input type="button" value="下载模板"
						class="button"
						onclick="downLoadTemplateFiles('<b:write property="taskAssgineeDto/templateFileIds" />');">
					</td>
				</tr>
      <tr class="form_bottom">
        <td colspan="4">
          <input type="button" value="结束流程" class="button" onclick="doDeleteProcess('<b:write property="taskAssgineeDto/businessKey" />','03');" id="deleteProcessBtn">
          <input type="button" value="保存" class="button" id="save1" onclick="doSave(1);"  />
          <input type="button" value="提交" onclick="doSave(2);" class="button" id="save2" />
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
 $(function(){
	 var templateFileIds = $("#templateFileIds").val();
		if(templateFileIds == "" || templateFileIds == null){
			$("#rowTemplate").hide();
		}
	//隐藏结束流程按钮
		$("#deleteProcessBtn").hide();
		if('${sessionScope.login_user.empid}'=='${monthReports.creator}'){
			  if($("#executionId").val()){
				  //当登录人等于发起人，显示结束流程按钮
				  $("#deleteProcessBtn").show();
		  	    }
		}
		
	 if('${monthReports.reportId}'!=""){
		 $.ajax({
		        url: '/file/tFileResourceTableAction_queryFileList.action',
		        async: false,
		        type: 'post',
		        data: "resourceType=03&resourceId=${monthReports.reportId}&fileType=0",
		        dataType: 'json',
		        timeout: 60000,
		        success: function (files) {
			        if(files!=null){
			         	$.each(files,function( i,item ){
				         	if('${isView}'!='')
			    	        	$("#tag1").fileDown({filename:item.fileName,filevalue:item.fileId});
				         	else
				         		$("#tag1").fileDown({filename:item.fileName,filevalue:item.fileId,remove:1});
			          		});	
			        }
		        }
	    });
		 $("#beginOrg").val("${monthReports.orgid}");
	 } else {
		 $("#row1").css("display","none");  
		 $("#row2").css("display","none"); 
		 $("#beginOrg").val('${sessionScope.login_user.orgid}');  
	 }
	 var isView = '${isView}';
	if(isView!=''){
		$("#deleteProcessBtn").hide();
		$("#save1").css("display","none");
	 	$("#save2").hide();
	 	$("#rowadd").hide();
		$("#rowTemplate").hide();
	}
 });
 function initPlanCell20(){
		var queryCond="";
			queryCond += "<resourceId>${monthReports.reportId}</resourceId>";
			queryCond += "<resourceType>03</resourceType>";
			return queryCond;
		}
	/* 保存或提交月报 */
     function doSave(value){
    		$("#btnType").val(value);
    		/* 将月报提交到部门领导处进行审核 */
			if(checkForm($id("form1"))){
	    		if(value!="1"){
	    			var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value
	    			+"&taskAssgineeDto.beginOrg="+$("#beginOrg").val()+"&taskAssgineeDto.beginAssingee="+$("#createor").val()+"&taskAssgineeDto.definitionId=<b:write property="taskAssgineeDto.definitionId" />";
	        		showModalCenter(strUrl, null, taskAssigneeCallBack, 700, 400, '节点选择');
	    		}else{
	    			/* 保存月报 */
	    			var _form = $id("form1");
	    	  	  	 	url="/monthReport/tWorkMonthReportsAction_insertMonthReportsInfo.action";	
	    	  	  	    _form.action =url
	    			    ajaxsubmitO(0);
	    	  	 	}
			}
    	
     }

		var rowId = 0;
		/* 添加附件  */
		function addFile(tabid,varName){
		    var tab,row,td,fName,fId,tdStr;
		    var zs=$("#"+tabid+" tbody tr").length;
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

  	  	function taskAssigneeCallBack(arg){
  	 	var _form = $id("form1");
  	 	if(arg!=""){
  	  	 	url="/monthReport/tWorkMonthReportsAction_insertMonthReportsInfo.action?"+arg;	
  	  	    _form.action =url
  	        // 异步提交请求 
  	  	    ajaxsubmitO(1);
  	 	}
  	   }

  	  	function ajaxsubmitO(flag){
			maskTop();
  	  	  	//此处可添加遮盖层
  	  	 var options = {
  		  		type : "post",
  		  		cache : "false",
  			  	success : function(data){
  					try {  
  					  	if(data.indexOf("success")>=0){
      					  	alert("操作成功!");
      					  	//若有添加则在此处去掉遮盖层
	 					  	unMaskTop();
	 					  	//20140905
	 					  	if(flag==1)
      					  	window.close();
  					  	}else if(data.indexOf("fails")>=0){
      					  	alert("保存失败!");
	 					  	unMaskTop();
      					//若有添加则在此处去掉遮盖层
  					  	}else{	 
  					  	    alert("操作失败!");
	 					  	unMaskTop(); 	
  					  	//若有添加则在此处去掉遮盖层
  					  	}
  	   				} catch (Exception) {  
  					}  
  				},  
  			  	error : function(data){
  				  alert("添加失败请联系管理员！");
				  	unMaskTop();
  			   	}  
  			}; 
  	  	$("#form1").ajaxSubmit(options);
  	  	}

 </script>
</html>
