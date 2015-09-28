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
<title>新增下发单</title>
</head>
  
  <body>
    <h:form id="issuedForm" name="issuedForm" action="" method="post" enctype="multipart/form-data">
<table align="center" border="0" width="100%" class="form_table">
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		 下发单
        		 <h:hidden id="executionId" name="sendData.flowId" property="taskAssgineeDto/executionId"/>
        		 <h:hidden id="taskId" name="taskId" property="taskAssgineeDto/nextTaskId"/>
        		 <h:hidden id="beforeId" name="sendData.dsId" property="taskAssgineeDto/businessKey"/>
        		 <h:hidden id="submitReason" name="submitReason"/>
        		 <h:hidden id="businessType" name="businessType" property="taskAssgineeDto/businessType"/>
        		 <h:hidden id="definitionId" name="definitionId" property="taskAssgineeDto/definitionId"/>
        		  <h:hidden id="templateFileIds" property="taskAssgineeDto.templateFileIds"/>
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right">
         标　　　 题：
        </td>
        <td colspan="3">
          <h:text name="sendData.dsTitle" validateAttr="allowNull=false" style="width:90%;" property="sendData.dsTitle"/>
          <font style="color: red">*</font>	
        </td>
      </tr>
      <tr>
        <td class="form_label" align="right" style="width:14%">
          下　 发　人：
        </td>
        <td colspan="1" style="width:36%">
        	<b:write property="login_user/empname" scope="session" />
        </td>
        <td class="form_label" align="right" style="width:14%">
         数 据 来 源：
        </td>
        <td colspan="1" style="width:36%">
        	<d:select dictTypeId="ZHPT_DATA_RESOURCE"  id="dataSource" name="sendData.dataSource" property="sendData.dataSource" extAttr="validateAttr='allowNull=false'" nullLabel="请选择" onchange="" ></d:select>
        	<font style="color: red">*</font>	
        </td>
      </tr>
      <tr id="recordRow" >
       <td class="form_label" align="right">数据申请记录：</td>
     	<td colspan="3">
     		<h:textarea id="daPro" name="sendData.daPro"  extAttr="class='h80' "  validateAttr="maxLength=512" property="sendData.daPro" rows="4" style="width:90%;"/>
     		<a href="#" onclick="showDataApply();">选择</a>
     	</td>
     </tr>
     <tr>
     <td class="form_label" align="right">数据使用人员：</td>
     	<td colspan="3">
     		<h:textarea id="dataUser_show" name="sendData.username"   extAttr="class='h80' " validateAttr="maxLength=512;allowNull=false" property="sendData.username" rows="4"  style="width:90%;" readonly="true"/>
     		<h:hidden id="dataUser" name="sendData.dataUser" property="sendData.dataUser" />
     		<a href="#" onclick="openTree();">选择</a><font style="color: red">*</font>
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right">数 据 类 型：</td>
     	<td colspan="1">
     		<d:select dictTypeId="ZHPT_DATA_TYPE" id="dataType" name="sendData.dataType" property="sendData.dataType" extAttr="validateAttr='allowNull=false'" nullLabel="请选择" ></d:select>
     		<font style="color: red">*</font>	
     	</td>
     	<td class="form_label" name="other" align="right" style="display:none">其 他 类 型：</td>
     	<td colspan="1" name="other" style="display:none">
     		<h:text id="diyDt" name="sendData.diyDt" /><font style="color: red">*</font>
     	</td>
     	<td class="form_label" align="right">使 用 期 限：</td>
     	<td colspan="1">
     	<h:hidden property="sendData.rvType" value=""/>
     	<w:date name="sendData.timeLimit" id="tlimit" format="yyyy-MM-dd" submitFormat="yyyyMMdd" property="sendData.timeLimit" allowNull="false" />
     		<font style="color: red">*</font>	
     	</td>
     </tr>
     	<%-- <td class="form_label" align="right">数据接收类型：</td>
     	<td>
     		<d:select dictTypeId="ZHPT_DATA_RECEIVE_TYPE" id="rvType" name="sendData.rvType" property="sendData.rvType" extAttr="validateAttr='allowNull=false'" nullLabel="请选择" ></d:select>
     		<font style="color: red">*</font>	
     	</td> --%>
     <tr>
     	<td class="form_label" align="right">数据下发说明：</td>
     	<td colspan="3">
	     	<h:textarea name="sendData.dsExpl"  extAttr="class='h80' "  property="sendData.dsExpl" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;"/>
		    <font style="color: red">*</font>
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right">附　　　 件：</td>
     	<td colspan="3">
	     	<input type="button" onclick="addFile('tabtest','upload');return false;" value="新增附件" 
					style="margin-left:2px;vertical-align:middle;cursor:hand;"/>
			<font style="color: red">(说明：至少上传一个附件，最多上传5个附件)</font>
			<br/>
			<table border=0 id="tabtest">
			</table>
     	</td>
     </tr>
      <tr style="display:none" id="beforeFile">
     	<td class="form_label" align="right">之前上传的附件：</td>
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
        <input type="button" value="结束流程" class="button" onclick="doDeleteProcess('<b:write property="taskAssgineeDto/businessKey" />','06');" id="deleteProcessBtn">
          <input type="button" value="保存" class="button" onclick="doSubmit(0);" id="savess">
          <input type="button" value="提交" onclick="doSubmit(1);" class="button" id="querytu">
         <input type="button" value="查看流程" onclick="doflowpic();" class="button" id="flowpic" />
         </td>
      </tr> 
      <tr id="opinionTr" style="display: none">
     	<td class="form_label" align="right">流 程 列 表：</td>
     <td colspan="3">
     <%@include file="/jsp/util/opinionUtil.jsp"%>
		</td>
     </tr>
    </table>
    </h:form>
    <iframe name="hiframe" style="display:none"></iframe>
			<!-- 人员id -->
			<h:hidden id="empid" name="empid" property="sendData.dataUser" />
			<!-- 人员名称 -->
			<h:hidden id="empname" name="empname" property="sendData.username"/>
  </body>
  <script type="text/javascript">
		$(document).ready(function(){
			var templateFileIds = $("#templateFileIds").val();
			if(templateFileIds == "" || templateFileIds == null){
				$("#rowTemplate").hide();
			}
			//隐藏结束流程按钮
			$("#deleteProcessBtn").hide();
			if('${sessionScope.login_user.empid}'=='${sendData.sEmpId}'){
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
				$("#deleteProcessBtn").hide();
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
				        data: "resourceType=06&resourceId=<b:write property="taskAssgineeDto/businessKey" />",
				        dataType: 'json',
				        timeout: 60000,
				        success: function (files) {
					        if(files!=null){
					         	$.each(files,function( i,item ){
					         		if(isView!=''){
					    	        	$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId});
					         		}else{
					         			$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId,remove:1});}
					          		});	
					        }
				        }
			      });
				}
		});

		 function initPlanCell20(){
				var queryCond="";
					queryCond += "<resourceId>${sendData.dsId}</resourceId>";
					queryCond += "<resourceType>06</resourceType>";
					return queryCond;
				}
		
		function doSubmit(type){
			$name("submitReason").value = type;
			if(checkForm($id("issuedForm"))){
				var fileCount = $("#tag").children().length;
				if(rowId==0 && fileCount ==0){
					alert('请至少上传一个附件');
					return false;
				}
				if(type==0){
					var url = "/dataIssued/tSendDataAction_saveOrUpdateIssued.action";
					$("#issuedForm").attr("action",url);
					ajaxsubmitO(0);
			   		return true;
				}
				var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value+"&taskAssgineeDto.beginOrg=<b:write property="taskAssgineeDto.beginOrg" />"+"&taskAssgineeDto.definitionId=<b:write property="taskAssgineeDto.definitionId" />";
	    		showModalCenter(strUrl, null, taskAssigneeCallBack, 700, 400, '节点选择');
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
		
		function openTree(){
			  var strUrl = "/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.showTabGroup=0&changeTree.showTabRole=0&changeTree.orgType=6&changeTree.showSelBox=2";
				var peArgument = [];
				//人员
		    	var paramEntity = new ParamEntity('Employee');
				paramEntity.setProperty('empid',$id("empid").value);
				paramEntity.setProperty('empname',$id("empname").value);
				peArgument[0]=[paramEntity,'empname','empid'];
				//调用并传参
				strUrl += "&time="+new Date().getTime();
				//$name("assignerTab").src = strUrl;	 
				showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack,500,430,'使用人选择')
		}
	
		function openNewEmpTreeCallBack(arg){
			var empids;
			var empnames;
			
			//回调方法
			if(arg!=''){
				if(arg['Employee']){ 
			  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
			  		argRes=[[],[],[],[]];
					for(var i=0;i<sorgidArra.length;i++){
						argRes[0].push(sorgidArra[i].getProperty("empid"));
						argRes[1].push(sorgidArra[i].getProperty("empname"));
					}
					$id("empid").value = argRes[0];
					$id("empname").value = argRes[1];
					empids = argRes[0];
					empnames=argRes[1];
					$id("dataUser_show").value=empnames;
					$id("dataUser").value=empids;
				}else{
					$id("empid").value = "";
					$id("empname").value = "";
				}
			}
		}
		
		function taskAssigneeCallBack(arg){
			//arg ="taskAssgineeDto.empids=1,2&taskAssgineeDto.empnames=张三，李四&taskAssgineeDto.transitionName=同意";
			var url = "/dataIssued/tSendDataAction_saveOrUpdateIssued.action?";
			if(arg!=''){
				arg=encodeURI(arg);
				url+=arg;
				var _form = $id("issuedForm");
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
	      					  	alert("操作成功!");
  	 					  		unMaskTop();
	  					  		//reload();
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
	  	  	$("#issuedForm").ajaxSubmit(options);
	  	  	}

  	  	function showDataApply(){
  	  	  	var url = "/dataApply/tApplyDataAction_queryAllDataApply.action";
  	  		window.parent.showModalCenter(url,null,callBackParam,1050,450,'数据申请单选择');
  	  	  }		
	  	  function callBackParam(arg){
		  	  $("#daPro").val(arg.title);
		  	  $("#dataUser_show").val(arg.userName);
		  	  $("#dataUser").val(arg.dataUser);
		  	 $("#empname").val(arg.userName);
		  	  $("#empid").val(arg.dataUser);
		  	  $("#tlimit_hidden").val(arg.useTlimit);
		  	  var tLimit = arg.useTlimit.substring(0,4);
		  	tLimit+="-";
		  	tLimit+=arg.useTlimit.substring(4,6);
		  	tLimit+="-";
		  	tLimit+=arg.useTlimit.substring(6);
		  	  $("#tlimit_input").val(tLimit);
		  }
	  	$id("tlimit").onValidate=function(hiddenValue,TextValue)
		{
			if(this.getValue()=="") 
			 return true;
			else
			{
				var result = WEB.today();
			  if(this.getValue()<result) 
			     {
			      f_alert($id("tlimit").text,"日期要大于当前时间");
			      return false;
			      }
			      else
			      return true;
			}

		}

		</script>
</html>
