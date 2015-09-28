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

<title>请假新增</title>
</head>
  
  <body>
    <h:form id="leaveForm" name="leaveForm" action="" method="post" enctype="multipart/form-data">
<table align="center" border="0" width="100%" class="form_table">
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		 请假单
        		 <h:hidden id="executionId" name="tapplyLeave.flowId" property="taskAssgineeDto/executionId"/>
        		 <h:hidden id="taskId" name="taskId" property="taskAssgineeDto/nextTaskId"/>
        		 <h:hidden id="beforeId" name="tapplyLeave.lvId" property="taskAssgineeDto/businessKey"/>
        		 <h:hidden id="submitReason" name="submitReason"/>
        		 <h:hidden id="definitionId" name="definitionId" property="taskAssgineeDto/definitionId"/>
        		 <h:hidden id="businessType" name="businessType" property="taskAssgineeDto/businessType"/>
              <h:hidden id="templateFileIds" property="taskAssgineeDto.templateFileIds"/>
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right">
         <nobr>标　　　 题：</nobr>
        </td>
        <td colspan="3">
          <h:text name="tapplyLeave.lvTitle" validateAttr="allowNull=false;maxLength=64" maxlength="128" style="width:90%;" property="tapplyLeave.lvTitle" /><font style="color: red">*</font>	
        </td>
      </tr>
      <tr>
        <td class="form_label" align="right">
          <nobr>申　 请　人：</nobr>
        </td>
        <td colspan="1">
          <b:write property="login_user/empname" scope="session"/>
        </td>
        <td class="form_label" align="right">
         <nobr>联 系 电 话：</nobr>
        </td>
        <td colspan="1">
        <!-- input里的value需要改为value="<b:write property="" scope="session" />" -->
          <h:text id="phoneNo" value="${sessionScope.login_user.mobi }" name="tapplyLeave.phoneNo" property="tapplyLeave.phoneNo" validateAttr="type=number;maxLength=20" style="width:148px;" onkeyup="this.value=this.value.replace(/\D/g,'')" />	
        </td>
      </tr>
      <tr>
         <td class="form_label" align="right">
          <nobr>申 请 部 门：</nobr>
        </td>
        <td colspan="1"> 
        	<b:write property="login_user/orgname" scope="session"/>
        </td>
         <td class="form_label" align="right">
       <nobr>
          <nobr>申 请 日 期：</nobr>
        </td>
        <td colspan="1">
          <h:text id="atime" name="atime" readonly="readonly" style="width:148px;" />
          <h:hidden id="atime2" name="atime2"/>
          <h:hidden name="tapplyLeave.atime" property="tapplyLeave.atime"/>
		</td>
      </tr>
      <tr>
       <td class="form_label" align="right"><nobr>请 假 类 型：</nobr></td>
     	<td colspan="3">
     		<d:select dictTypeId="ZHPT_LEAVE_TYPE" style="width:148px;" id="lvType" name="tapplyLeave.lvType" property="tapplyLeave.lvType" extAttr="validateAttr='allowNull=false'" nullLabel="请选择" ></d:select>
     	</td>
         </tr>
     <tr>
     	<td class="form_label" align="right"><nobr>请 假 日 期：</nobr></td>
     	<td colspan="3">
     		<w:date format="yyyy-MM-dd" submitFormat="yyyyMMdd" id="d1" name="tapplyLeave.lvStart" property="tapplyLeave.lvStart" allowNull="false" />
     		-
     		<w:date format="yyyy-MM-dd" submitFormat="yyyyMMdd" id="d2" name="tapplyLeave.lvEnd" property="tapplyLeave.lvEnd" allowNull="false" />
     		<font style="color: red">*</font>	
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right"><nobr>请 假 原 因：</nobr></td>
     	<td colspan="3">
     	<h:hidden property="approveOpninion.opninionContent"/>
     	<h:textarea name="tapplyLeave.lvReason"  extAttr="class='h80' "  property="tapplyLeave.lvReason" validateAttr="maxLength=512;allowNull=false" rows="4" style="width:90%"/>
<%-- 	     	<d:select id="lvType" name="tapplyLeave.lvType" property="tapplyLeave.lvType"></d:select> --%>
		    <font style="color: red">*</font>
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right"><nobr>附　　　 件：</nobr></td>
     	<td colspan="3">
	     	<input type="button" onclick="addFile('tabtest','upload');return false;" value="新增附件" 
					style="margin-left:2px;vertical-align:middle;cursor:hand;"/>
			<font style="color: red">(说明：最多上传5个附件)</font>
			<br/>
			<table border=0 id="tabtest">
			</table>
     	</td>
     </tr>
     <tr style="display:none" id="beforeFile">
     	<td class="form_label" align="right"><nobr>之前上传的附件：</nobr></td>
     	<td colspan="3">
     	<div id="tag"></div>
     	</td>
     </tr>
               <tr id="rowTemplate">
					<td class="form_label" align="right">模板文件：</td>
					<td colspan="3"><input type="button" value="下载模板"
						class="button"
						onclick="downLoadTemplateFiles('<b:write property="taskAssgineeDto/templateFileIds" />');">
					</td>
				</tr>
      <tr class="form_bottom">
        <td colspan="4">
        <input type="button" value="结束流程" class="button" onclick="doDeleteProcess('<b:write property="taskAssgineeDto/businessKey" />','08');" id="deleteProcessBtn">
          <input type="button" value="保存" class="button" onclick="doSubmit(0);" id="savess">
          <input type="button" value="提交" onclick="doSubmit(1);" class="button" id="querytu">
         <input type="button" value="查看流程" onclick="doflowpic();" class="button" id="flowpic" />
         </td>
      </tr>
     <tr id="opinionTr" style="display: none">
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
			var templateFileIds = $("#templateFileIds").val();
			if(templateFileIds == "" || templateFileIds == null){
				$("#rowTemplate").hide();
			}
			//隐藏结束流程按钮
			$("#deleteProcessBtn").hide();
			if('${sessionScope.login_user.empid}'=='${tapplyLeave.empId}'){
				  if($("#executionId").val()){
					  //当登录人等于发起人，显示结束流程按钮
					  $("#deleteProcessBtn").show();
			  	    }
			}
				var date = new Date();
				var result = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
				$("#atime").val(result);
				$("#atime2").val(date.getFullYear()+''+(date.getMonth()+1)+''+date.getDate());

				var isView = '${isView}';
				if(isView!=''){
					$("#deleteProcessBtn").hide();
					$("#savess").hide();
					$("#querytu").hide();
					$("#rowTemplate").hide();
					}
				var beforeId = $id("beforeId").value;
				if(beforeId != ""){
					$("#beforeFile").show();
					$("#opinionTr").show();
					$.ajax({
					        url: '/file/tFileResourceTableAction_queryFileList.action',
					        async: false,
					        type: 'post',
					        data: "resourceType=08&resourceId=<b:write property="taskAssgineeDto/businessKey" />",
					        dataType: 'json',
					        timeout: 60000,
					        success: function (files) {
						        if(files!=null){
						         	$.each(files,function( i,item ){
										if(isView!=''){
						    	        	$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId});
										}else{
							    	        $("#tag").fileDown({filename:item.fileName,filevalue:item.fileId,remove:1});
											}
						          		});	
						        }
					        }
				     });
				}
			});

		 function initPlanCell20(){
				var queryCond="";
					queryCond += "<resourceId>${tapplyLeave.lvId}</resourceId>";
					queryCond += "<resourceType>08</resourceType>";
					return queryCond;
				}
		
		function doSubmit(type){
			$name("submitReason").value = type;
			if(checkForm($id("leaveForm"))){
				if($id("d1").getValue()>$id("d2").getValue())
				{
				    f_alert($id("d1").text,"开始日期不能比结束日期大");
			        return false;
				}
				if(type==0){
					var _form = $id("leaveForm");
					var url = "/leave/tApplyLeaveAction_saveOrUpdateLeave.action";	
		  	  	    _form.action =url;
				    ajaxsubmitO(0);
				}else{
					var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value+"&taskAssgineeDto.beginOrg=<b:write property="taskAssgineeDto.beginOrg" />"+"&taskAssgineeDto.beginAssingee=<b:write property="taskAssgineeDto.beginAssingee" />"+"&taskAssgineeDto.definitionId=<b:write property="taskAssgineeDto.definitionId" />";
		    		showModalCenter(strUrl, null, taskAssigneeCallBack, 900, 430, '节点选择');
				}
			}
		}
		
		var rowId = 0;
		function addFile(tabid,varName){
		    var tab,row,td,fName,fId,tdStr,g;
		    if ($id("group1")==null){ 
		    	g=0;
		    } else{
		    	g = $id("group1").getLength(); 
		    }
		    var zs=g+rowId;
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
		    tdStr += "<input type=\"button\" onclick=\"delTr('fileRow"+rowId+"');return false;\" name='button"+rowId+"' value=\"删除\" style=\"margin-left:2px;vertical-align:middle;cursor:hand;\"/>";
		    td.innerHTML = tdStr;
		    rowId = rowId+1;    
		}
		
		function delTr(row_id){
			$("#"+row_id).remove();
		    rowId = rowId-1;
		}

		$id("d1").onValidate=function(hiddenValue,TextValue)
		{

			if($id("d1").getValue()==""||this.getValue()=="") 
			   return true;
			  else
				{
				if(this.getValue()>$id("d2").getValue())
				   {
				    f_alert($id("d1").text,"结束日期要大于开始日期");
			        return false;
				   }
				   return true;
				}

		}

		$id("d2").onValidate=function(hiddenValue,TextValue)
		{

			if($id("d1").getValue()==""||this.getValue()=="") 
			 return true;
			else
			{
			  if(this.getValue()<=$id("d1").getValue()) 
			     {
			      f_alert($id("d2").text,"结束日期要大于开始日期");
			      return false;
			      }
			      else
			      return true;
			}

		}

		function taskAssigneeCallBack(arg){
			var url = "/leave/tApplyLeaveAction_saveOrUpdateLeave.action?";
			if(arg!=''){
				arg=encodeURI(arg);
				url+=arg;
				var _form = $id("leaveForm");
	  	  	    _form.action =url;
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
	  					  		//reload();
  	 					  		unMaskTop();
  	 					  	if(flag==1)
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
	  				  alert("添加失败请联系管理员！");
					  		unMaskTop();
	  			   	}  
	  			}; 
	  	  	$("#leaveForm").ajaxSubmit(options);
	  	  	}
  </script>
</html>
