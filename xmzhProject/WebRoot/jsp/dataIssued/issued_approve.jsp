<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<h:css href="/css/style1/style-custom.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="/common/gotop/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/fileDown.css">
<script type="text/javascript" src="/js/fileDown.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/commonUtil.js"></script>
<title>审核数据下发申请</title>
</head>
  
  <body>
  <w:panel title="审核数据下发申请">
    <h:form id="issuedForm" name="issuedForm" action="" method="post" enctype="multipart/form-data">
<table align="center" border="0" width="100%" class="form_table">
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		 下发单
        	<h:hidden id="executionId" name="processInstanceId" property="taskAssgineeDto/executionId"/>
        	<h:hidden id="taskId" name="taskId" property="taskAssgineeDto/nextTaskId"/>
        	<h:hidden id="taskAssingee" name="taskAssingee" property="taskAssgineeDto/taskAssingee"/>
        	<h:hidden id="resourceId" name="resourceId" property="sendData.dsId" />
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right">
         标　　　 题：
        </td>
        <td colspan="3">
          <b:write property="sendData.dsTitle"/>
        </td>
      </tr>
      <tr>
       <%--  <td class="form_label" align="right">
          下　 发　人：
        </td>
        <td colspan="1">
        	<b:write property="sendData.empName"/>
        </td> --%>
        <td class="form_label" align="right">
         数 据 来 源：
        </td>
        <td colspan="3">
        	<h:hidden property="sendData.dsId" id="dsId" name="approveOpninion.resourceId" />
        	<d:write dictTypeId="ZHPT_DATA_RESOURCE" property="sendData.dataSource"/>
        </td>
      </tr>
      <%-- <tr>
         <td class="form_label" align="right">
          申 请 部 门：
        </td>
        <td colspan="1">
         <b:write property="login_user/orgentityname" scope="session" />
        </td>
        <td class="form_label" align="right">
      	<nobr>
       下    发    表：</nobr>
      	</td>
        <td colspan="1"> 
        	<h:hidden property="sendData.dsId" id="dsId" name="approveOpninion.resourceId" />
        </td>
      </tr> --%>
      <tr>
       <td class="form_label" align="right">申 请 流 程：</td>
     	<td colspan="3">
     		<b:write property="sendData.daPro" />
     	</td>
     </tr>
     <tr>
     <td class="form_label" align="right">使 用 人 员：</td>
     	<td colspan="3">
     		<b:write property="sendData.username"/>
     		<h:hidden name="dataUser" property="sendData.dataUser" />
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right" style="width:14%">数 据 类 型：</td>
     	<td colspan="1" style="width:36%">
     		<d:write dictTypeId="ZHPT_DATA_TYPE" property="sendData.dataType"/>
     	</td>
     	<td class="form_label" align="right" style="width:14%">使 用 期 限：</td>
     	<td style="width:36%">
     		<b:write property="sendData.timeLimit" formatPattern="yyyy-MM-dd"/>	
     	</td>
     	<td class="form_label" name="other" align="right" style="display:none">其 他 类 型：</td>
     	<td colspan="1" name="other" style="display:none">
     		<h:text id="diyDt" name="sendData.diyDt" /><font style="color: red">*</font>
     	</td>
     </tr>
   <%--   <tr>
     	<td class="form_label" align="right">接 收 类 型：</td>
     	<td>
     		<d:write dictTypeId="ZHPT_DATA_RECEIVE_TYPE" property="sendData.rvType"/>
     	</td>
     </tr> --%>
     <tr>
     	<td class="form_label" align="right">下 发 说 明：</td>
     	<td colspan="3">
	     	<b:write property="sendData.dsExpl"/>
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right">附　　　 件：</td>
     	<td colspan="3">
	     	<div id="tag"></div>
     	</td>
     </tr>
      <tr id="opinionTr">
     	<td class="form_label" align="right">审 核 意 见：</td>
     	<td colspan="3">
	     	<h:textarea  extAttr="class='h80' "  rows="5" cols="" style="width:90%" validateAttr="maxLength=512;allowNull=false" id="opinion" name="approveOpninion.opninionContent"/>
	     	<font style="color: red">*</font>
     	</td>
     </tr>
     	<%@include file="/jsp/util/default_opinionUtil.jsp" %>
     <tr class="form_bottom">
       <td colspan="4">
         <input type="button" value="提交" id="pass" class="button" onclick="doSubmit();">
         <input type="button" value="查看流程" onclick="doflowpic();" class="button" id="flowpic" />
        </td>
     </tr>
     <tr>
     	<td class="form_label" align="right">流 程 列 表：</td>
     <td colspan="3">
     <%@include file="/jsp/util/opinionUtil.jsp"%>
		</td>
     </tr>
    </table>
    </h:form>
    </w:panel>
    <iframe name="hiframe" style="display:none"></iframe>
    <table align="left" border="0" id="app_table" height="100%" width="100%" cellpadding="0" cellspacing="0">
		<tr>	
			<!-- 人员id -->
			<input type="hidden" id="empid" name="empid" value="<s:property value='empid'/>">
			<!-- 人员名称 -->
			<input type="hidden" id="empname" name="empname" value="<s:property value='empname'/>">
			<!-- 机构id -->
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>">
			<!-- 机构名称 -->
			<input type="hidden" id="orgname" name="orgname" value="<s:property value='orgname'/>">
				
		  <!--  <td id="" valign="top" width="60%" >
		      <w:panel height="450">
		      <iframe frameBorder="0" style="width:100%;height:100%" scrolling="no" src="" name="assignerTab"> </iframe>
		      </w:panel>
		   </td> -->
		</tr>
		</table>
  </body>
  <script type="text/javascript">
		$(document).ready(function(){
			var date = new Date();
			var result = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
			$("#atime").val(result);
			$("#atime2").val(date.getFullYear()+''+(date.getMonth()+1)+''+date.getDate());
			
			var isView = '${isView}';
			if(isView!=''){
				$("#pass").hide();
				$("#opinionTr").hide();
			}
			$.ajax({
		        url: '/file/tFileResourceTableAction_queryFileList.action',
		        async: false,
		        type: 'post',
		        data: "resourceType=06&resourceId=<b:write property="sendData.dsId" />",
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
					queryCond += "<resourceId>${sendData.dsId}</resourceId>";
					queryCond += "<resourceType>06</resourceType>";
					return queryCond;
				}
			
		function doSubmit(){
			if(checkForm($id("issuedForm"))){
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
			var url = "/dataIssued/tSendDataAction_approveIssued.action?";
			arg=encodeURI(arg);
			url+=arg;
			var _form = $id("issuedForm");
  	  	    _form.action =url;
	  	  	$("#issuedForm").ajaxSubmit(options);
			}
  	   }			
  </script>
</html>
