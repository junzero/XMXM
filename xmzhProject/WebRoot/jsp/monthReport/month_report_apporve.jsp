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
<title>月报审核</title>
</head>
  <body>
  <e:datasource name="monthReports" type="bean" path="/xmzhProject/src/com/gotop/monthReport/model/TWorkMonthReports.java" />
    <h:form name="form1" id="form1" action="/monthReport/TWorkMonthReportsAction_insertMonthReportsInfo.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
         <h:hidden property="monthReports.reportId"/>
         <h:hidden property="monthReports.creator" id="creator"/>
         <h:hidden property="monthReports.flowId"/>
	     <h:hidden property="monthReports.createDate"/>
         <h:hidden property="monthReports.createTime"/>
         <h:hidden property="taskAssgineeDto.businessType" name="businessType"/>
         <!-- 起草人机构 -->
	     <h:hidden id="beginOrg" property="taskAssgineeDto.beginOrg"/>
	     <!-- 流程实例ID -->
         <h:hidden id="executionId" name="taskAssgineeDto.executionId" property="taskAssgineeDto.executionId"/>
         <!-- 下一节点ID -->
         <h:hidden id="taskId" name="taskAssgineeDto.nextTaskId"  property="taskAssgineeDto.nextTaskId"/>
         <!-- 办理人ID -->
         <h:hidden id="taskAssingee" name="taskAssgineeDto.taskAssingee" property="taskAssgineeDto.taskAssingee"/>
         <!-- 操作类型 1为保存、2为提交审核 -->
		<!-- <input type="hidden" id="btnType" name="btnType" /> -->
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
	     	<h:textarea property="monthReports.content"  extAttr="class='h80' "  validateAttr="maxLength=512;allowNull=false" rows="4" readonly="true" style="width:90%;" />
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
      <tr id="rowOpinion">
     	<td class="form_label" align="right">意见：</td>
     	<td colspan="3">
	     	<h:textarea  extAttr="class='h80' " id="opinion" property="monthReports.opninion" name="monthReports.opninion" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;"/>
		    <font style="color: red">*</font>
     	</td>
      </tr>
     	<%@include file="/jsp/util/default_opinionUtil.jsp" %>
      <tr class="form_bottom">
        <td colspan="4">
          <input type="button" value="提交" onclick="doSave();" class="button" id="save2" />
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
				$.ajax({//获得当前
						url : '/file/tFileResourceTableAction_queryFileList.action',
						async : false,
						type : 'post',
						data : "resourceType=03&resourceId=${monthReports.reportId}&fileType=0",
						dataType : 'json',
						timeout : 60000,
						success : function(files) {
							if (files != null) {
								$.each(files, function(i, item) {
									$("#tag1").fileDown({
										filename : item.fileName,filevalue:item.fileId
									});
								});
							}
						}
					});
				$("#beginOrg").val("${monthReports.orgid}");
				if('${isView}'!=''){
				    $("#save2").hide();
				    $("#rowOpinion").hide();
				}	
		});

 function initPlanCell20(){
		var queryCond="";
			queryCond += "<resourceId>${monthReports.reportId}</resourceId>";
			queryCond += "<resourceType>03</resourceType>";
			return queryCond;
		}

		/* 提交审核 */
		function doSave() {
			if(checkForm($id("form1"))){
				var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value+"&taskAssgineeDto.beginOrg="+$("#beginOrg").val()+"&taskAssgineeDto.beginAssingee="+$("#creator").val();
				showModalCenter(strUrl, null, taskAssigneeCallBack, 700, 400,
						'节点选择');
			}
		}

		var rowId = 0;
		function addFile(tabid, varName) {
			var tab, row, td, fName, fId, tdStr;
			var zs = $("#tabtest tbody tr").length;
			tab = $id(tabid);
			if (zs >= 5) {
				alert("新增附件不能超过5个");
				return false;
			}
			fName = varName;
			fId = varName + rowId;
			row = tab.insertRow();
			row.id = "fileRow" + rowId;
			td = row.insertCell();

			tdStr = "<input type=\"file\" name=\""
					+ fName
					+ "\" id=\""
					+ fId
					+ "\" onchange=\"CheckUpLoadFile(this,2);\" size='70' class=smallInput validateAttr=\"allowNull=false\">";
			tdStr += "<input type=\"button\" onclick=\"delTr('fileRow"
					+ rowId
					+ "');\" name='button"
					+ rowId
					+ "' value=\"删除\" style=\"margin-left:2px;vertical-align:middle;cursor:hand;\"/>";
			td.innerHTML = tdStr;
			rowId = rowId + 1;
		}

		function delTr(id) {
			$("#" + id).remove();
		}

		function taskAssigneeCallBack(arg) {
			var _form = $id("form1");
			if (arg != "") {
				url = "/monthReport/tWorkMonthReportsAction_insertMonthReportsInfo.action?"
						+ arg;
				_form.action = url
				// 异步提交请求 
				ajaxsubmitO();
			}
		}

		function ajaxsubmitO() {
			maskTop();
			var options = {
				type : "post",
				cache : "false",
				success : function(data) {
					try {
						if (data.indexOf("success") >= 0) {
							alert("操作成功!");
	 					  	unMaskTop();
							WEB.turnMainFrame();
						} else if (data.indexOf("fails") >= 0) {
							alert("操作失败!");
	 					  	unMaskTop();
						} else {
							alert("操作失败!");
	 					  	unMaskTop();
						}
					} catch (Exception) {
					}
				},
				error : function(data) {
					alert("添加失败请联系管理员！");
				  	unMaskTop();
				}
			};
			$("#form1").ajaxSubmit(options);
		}
	</script>
</html>
