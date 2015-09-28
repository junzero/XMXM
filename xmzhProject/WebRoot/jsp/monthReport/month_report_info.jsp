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
<title>月报详情</title>
</head>
  <body>
  <e:datasource name="monthReports" type="bean" path="/xmzhProject/src/com/gotop/monthReport/model/TWorkMonthReports.java" />
    <h:form name="form1" id="form1" action="/monthReport/TWorkMonthReportsAction_insertMonthReportsInfo.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
         <h:hidden property="monthReports.reportId"/>
         <h:hidden property="monthReports.flowId"/>
	     <h:hidden property="monthReports.createDate"/>
	     <!-- 流程实例ID -->
         <h:hidden id="executionId" property="taskAssgineeDto.executionId"/>
         <!-- 下一节点ID -->
         <h:hidden id="taskId"  property="taskAssgineeDto.nextTaskId"/>
         <!-- 办理人ID -->
         <h:hidden id="taskAssingee" property="taskAssgineeDto.taskAssingee"/>
         
         <h:hidden property="taskAssgineeDto.businessType" name="businessType"/>
		<table align="center" border="0" width="100%" class="form_table" >
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		 月报详情
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right" style="width:120px;">
                                                     标 题：
        </td>
        <td colspan="3">
          <h:text property="monthReports.reportTitle" id="reportTitle" validateAttr="allowNull=false" readonly="true" style="width:90%;" />
        </td>
      </tr>
      <%-- <tr>
       <td class="form_label" align="right">月份：</td>
        <td colspan="1">
         <h:text property="monthReports.reportMonth" id="reportMonth" validateAttr="allowNull=false" readonly="true" style="width:40%;"/>	
        </td>
      </tr> --%>
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
	     	<h:textarea property="monthReports.content" validateAttr="maxLength=512" rows="4" readonly="true" style="width:90%;" />
     	</td>
      </tr>
     <tr>
      <td class="form_label" align="right">下载附件：</td>
      <td colspan="3">
      <div id="tag"></div>
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
	 $.ajax({//获得当前
	        url: '/file/tFileResourceTableAction_queryFileList.action',
	        async: false,
	        type: 'post',
	        data: "resourceType=03&resourceId=${monthReports.reportId}",
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
	 if('${isView}'!=''){
		    $("#save2").hide();}	
 });

 </script>
</html>
