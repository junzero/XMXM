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
<title>请假审核</title>
</head>
  
  <body>
    <h:form id="leaveForm" name="leaveForm" action="" method="post">
<table align="center" border="0" width="100%" class="form_table">
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		 请假单
        	<h:hidden id="executionId" name="tapplyLeave.flowId" property="taskAssgineeDto/executionId"/>
        	<h:hidden id="taskId" name="taskId" property="taskAssgineeDto/nextTaskId"/>
        	<h:hidden id="taskAssingee" name="taskAssgineeDto.taskAssingee" property="taskAssgineeDto/taskAssingee"/>
        	<h:hidden id="taskName" name="taskName" property="taskAssgineeDto/taskName"/>
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right">
         标　　　 题：
        </td>
        <td colspan="3">
          <b:write property="tapplyLeave.lvTitle" />
        </td>
      </tr>
      <tr>
        <td class="form_label" align="right" style="width:14%">
          申　 请　人：
        </td>
        <td colspan="1" style="width:36%">
        	<b:write property="tapplyLeave.empName" />
        </td>
        <td class="form_label" align="right" style="width:14%">
         联 系 电 话：
        </td>
        <td colspan="1" style="width:36%">
	       	<b:write property="tapplyLeave.phoneNo" />	
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right">
      	<nobr>
      请 假 类 型：</nobr>
      	</td>
        <td colspan="1"> 
        	<h:hidden property="tapplyLeave.lvId" id="lvid" name="approveOpninion.resourceId" />
         	<d:write dictTypeId="ZHPT_LEAVE_TYPE" property="tapplyLeave.lvType"/>
        </td>
         <td class="form_label" align="right">
       <nobr>
          审 核 日 期：</nobr>
        </td>
        <td colspan="1">
          <input type="text" id="otime" readonly="readonly" style="width:148px;" />
          <input type="hidden" id="otime2" name="otime" readonly="readonly"/>
		</td>
      </tr>
     <tr>
     	<td class="form_label" align="right">请 假 日 期：</td>
     	<td>
     		<b:write property="tapplyLeave.lvStart" formatPattern="yyyy-MM-dd" />	
     		至
     		<b:write property="tapplyLeave.lvEnd" formatPattern="yyyy-MM-dd" />
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right">请 假 原 因：</td>
     	<td colspan="3">
	     	<h:textarea  extAttr="class='h80'"  property="tapplyLeave.lvReason" readonly="true" rows="4" style="width:90%"/>
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right">附            件：</td>
     	<td colspan="3">
     		<div id="tag"></div>
     		<%-- <table>
	     		
		     	<l:iterate id="file" property="fileList">
		     		<tr>
		     		<td>
		     		<a href="/file/tFileResourceTableAction_download.action?filePath=<b:write property="filePath" iterateId="file"/>&fileName=<b:write property="fileName" iterateId="file"/>"><b:write property="fileName" iterateId="file"/></a>
		     		</td>
		     		</tr>
		     		<h:hidden id="fileId" name="fileId" property="fileId" iterateId="file" />
		     	</l:iterate>
		     	
	     	</table> --%>
     	</td>
     </tr>
     <tr id="opinionTr">
     	<td class="form_label" align="right">审核意见：</td>
     	<td colspan="3">
	     	<h:textarea  extAttr="class='h80' "  rows="4" validateAttr="maxLength=512;allowNull=false" style="width:90%" id="opinion" name="approveOpninion.opninionContent"/>
	     	<h:hidden name="approveOpninion.operatorType" />
     	</td>
     </tr>
     	<%@include file="/jsp/util/default_opinionUtil.jsp" %>
      <tr class="form_bottom">
        <td colspan="4">
          <input id="sButton" type="button" value="提交" class="button" onclick="pass();" />
         <input type="button" value="查看流程" onclick="doflowpic();" class="button" id="flowpic" />
        </td>
      </tr>
      <tr>
     <td class="form_label" align="right"><nobr>流 程 列 表：</nobr></td>
     <td colspan="3">
     <%@include file="/jsp/util/opinionUtil.jsp"%>
		</td>
     </tr>
    </table>
    </h:form>
      <iframe name="hiframe" style="display:none"></iframe>
  </body>
  <script type="text/javascript">
  		
		 $(document).ready(function(){
			 var date = new Date();
				var result = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
				$("#otime").val(result);
				$("#otime2").val(date.getFullYear()+''+(date.getMonth()+1)+''+date.getDate());

				var isView = '${isView}';
				if(isView!=''){
					$("#sButton").hide();
					$("#opinionTr").hide();
				}
				
				$.ajax({
			        url: '/file/tFileResourceTableAction_queryFileList.action',
			        async: false,
			        type: 'post',
			        data: "resourceType=08&resourceId=<b:write property="tapplyLeave.lvId" />",
			        dataType: 'json',
			        timeout: 60000,
			        success: function (files) {
				        if(files!=null){
				         	$.each(files,function( i,item ){
				    	        $("#tag").fileDown({filename:item.fileName,filevalue:item.fileId});
				          		});	
				        }
			        }
		      });
		});

		 function initPlanCell20(){
				var queryCond="";
					queryCond += "<resourceId>${tapplyLeave.lvId}</resourceId>";
					queryCond += "<resourceType>08</resourceType>";
					return queryCond;
				}
			
		function pass(){
			if(checkForm($id("leaveForm"))){
				var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value+"&taskAssgineeDto.beginOrg=<b:write property="taskAssgineeDto.beginOrg" />"+"&taskAssgineeDto.beginAssingee=<b:write property="taskAssgineeDto.beginAssingee" />";
	    		showModalCenter(strUrl, null, taskAssigneeCallBack, 700, 400, '节点选择');
			}
		}
		
		function taskAssigneeCallBack(arg){
			if(arg!=''){
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
		      					    WEB.turnMainFrame();
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
		  				  alert("审核失败请联系管理员！");
						  		unMaskTop();
		  			   	}  
		  		};
				var url = "/leave/tApplyLeaveAction_leaveApprove.action?";
				arg=encodeURI(arg);
				url+=arg;
				var _form = $id("leaveForm");
	  	  	    _form.action =url;
		  	  	$("#leaveForm").ajaxSubmit(options);
			}
  	   }
  </script>
</html>
