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
<title>信息审核</title>
</head>
  <body>
    <e:datasource name="message" type="bean" path="com.gotop.messagePublish.model.TMessagePublish" />
    <h:form name="form1" id="form1" action="/messagePublish/tMessagePublishAction_insertTMessagePublish.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
	  <input type="hidden" id="btnType" name="btnType" />
	 <h:hidden property="message.messageId"/>
	 <h:hidden property="message.flowId"/>
	 <h:hidden property="message.createDate"/>
	 <h:hidden property="message.messageType"/>
	 <h:hidden property="message.publishType"/>
	  <h:hidden property="message.createEmpid" id="createor"/>
	 <h:hidden id="executionId" property="taskAssgineeDto.executionId"/>
     <h:hidden id="taskId" property="taskAssgineeDto.nextTaskId"/>
     <h:hidden id="taskAssingee" property="taskAssgineeDto.taskAssingee"/>
     <h:hidden id="beginOrg" property="taskAssgineeDto.beginOrg"/>
     <h:hidden id="definitionId" property="taskAssgineeDto.definitionId"/>
     <h:hidden id="businessType" property="taskAssgineeDto.businessType"/>
    <table align="center" border="0" width="100%" class="form_table" >
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		 信息详情
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right" style="width:120px;">
                                                     标 题：
        </td>
        <td colspan="3">
          <h:text property="message.messageTitle" id="messageTitle" readonly="true" validateAttr="allowNull=false" style="width:90%;" />
        </td>
      </tr>
      <tr>
       <td class="form_label" align="right">信息类型：</td>
     	<td colspan="3">
     		<d:select dictTypeId="ZHPT_MESSAGE_TYPE" value="${message.messageType}" disabled="true" id="messageType" style="width:130px;"  nullLabel="请选择"></d:select>
     	</td>
      </tr>
      <tr>
       <td class="form_label" align="right">发布类型：</td>
     	<td colspan="3">
         <d:write dictTypeId="ZHPT_PUBLISH_TYPE" property="message.publishType"   /></td>
      </tr>
      <tr>
        <td class="form_label" align="right">发布范围：</td>
            <td colspan="3">
              <%-- <h:text id="objName" property="message.objName" readonly="true" style="width:50%" /> --%>
              <h:textarea id="objName" property="message.objName"  extAttr="class='h80'" readonly="true"  validateAttr="allowNull=false;" rows="4"  style="width:90%;" />
			  <h:hidden id="publishRange" property="message.publishRange" validateAttr="allowNull=false;" />
			  <h:hidden id="orgid" property="message.orgid" />
			  <!--
			  <a href="#" onclick="open_newyw_tree_fun()">选择</a>
            -->
            </td>
      </tr>
      <tr>
     	<td class="form_label" align="right">内容：</td>
     	<td colspan="3">
	     	<h:textarea  extAttr="class='h80'"  property="message.content" validateAttr="maxLength=512" readonly="true" rows="4"  style="width:90%;" />
		    <font style="color: red">*</font>
     	</td>
      </tr>
      <tr id="row1">
      <td class="form_label" align="right">附件下载：</td>
      <td colspan="3">
      <div id="tag"></div>
      </td>
      </tr>
     <tr id="rowFile">
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
     <tr>
     	<td class="form_label" align="right">意见：</td>
     	<td colspan="3">
	     	<h:textarea property="message.opninion" extAttr="class='h80'" id="opinion" name="message.opninion" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;"/>
		    <font style="color: red">*</font>
     	</td>
      </tr>
     	<%@include file="/jsp/util/default_opinionUtil.jsp" %>
      <tr class="form_bottom">
        <td colspan="4">
          <input type="button" value="提交" onclick="doSave(2);" class="button" id="smit" />
          <input type="button" value="查看流程" onclick="doflowpic();" class="button" id="flowpic" />
         </td>
      </tr>
       <tr>
     <td class="form_label" align="right">流程列表：</td>
     <td colspan="3">
     <%@include file="/jsp/util/opinionUtil.jsp"%>
		</td>
     </tr>
    </table>
    </h:form>
  </body>
  <script type="text/javascript">
  $(document).ready(function(){
	  $.ajax({
	        url: '/file/tFileResourceTableAction_queryFileList.action',
	        async: false,
	        type: 'post',
	        data: "resourceType=${taskAssgineeDto.businessType}&resourceId=${message.messageId}",
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
	  $("#beginOrg").val("${message.orgid}");	
	  if('${isView}'!=""){
			 $("#smit").css("display","none");
			 WEB.hideFile();
		 }
  });
  function initPlanCell20(){
		var queryCond="";
		queryCond += "<resourceId>${message.messageId}</resourceId>";
		queryCond += "<resourceType>${taskAssgineeDto.businessType}</resourceType>";
			return queryCond;
		}
	  
  function doSave(value){
		if($("#messageType").val()==""){
  			alert("信息类型为必填选项！");
  		$("#messageType").focus();
			return false;
		}
		if($("#publishRange").val()==""){
      	alert("发布范围为必填选项！");
  		return false;
  		}
		if(checkForm($id("form1"))){
			$("#btnType").val(value);
			var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value+"&taskAssgineeDto.beginOrg="+$("#beginOrg").val()+"&taskAssgineeDto.beginAssingee="+$("#createor").val();
			showModalCenter(strUrl, null, taskAssigneeCallBack, 700, 400, '节点选择');
		}
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
		
	
		
		function doChange(){
			$id("publishRange").value = "";
			$id("objName").value = "";
			$id("orgid").value="";
		}
		var objvalue="";
		function open_newyw_tree_fun(){//方法名
			var strUrl ="";
			var objName="";
			var peArgument = [];
			var arrobj = $("input[name='message.publishType']");
			 for (var i=0; i<arrobj.length;i++){
				 if(arrobj[i].checked){
					 objvalue=arrobj[i].value;
				 }
			 }
			 if(objvalue=="02"){
					//changeTree.startOrgcode=${sessionScope.login_user.orgentitycode}&
			     strUrl="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2";
			     objName="人员发布";
			     var paramEntity = new ParamEntity('Employee');
					paramEntity.setProperty('empid',$id("publishRange").value);
					paramEntity.setProperty('empname',$id("objName").value);
					peArgument[2]=[paramEntity,'empname','empid'];
				}else if(objvalue=="03"){
				 strUrl = "/tree/initMainTree_mainTree.action?orgType=4&changeTree.showTabOrg=0&changeTree.showTabGroup=0&changeTree.showTabRole=1&showSelBox=4";
				 objName="角色发布";
				 var paramEntity = new ParamEntity('Role');
					paramEntity.setProperty('roleid',$id("publishRange").value);
					paramEntity.setProperty('rolename',$id("objName").value);
					peArgument[2]=[paramEntity,'rolename','roleid'];
				}else{
				strUrl ="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=4&changeTree.showSelBox=4"
				objName="机构发布";  
				var paramEntity = new ParamEntity('Organization');
					paramEntity.setProperty('orgcode',$id("publishRange").value);
					paramEntity.setProperty('orgname',$id("objName").value);
					paramEntity.setProperty('orgid',$id("orgid").value);
					peArgument[3]=[paramEntity,'orgname','orgcode',"orgid"];	
				}		
  		        //调用并传参
  		        strUrl += "&time="+new Date().getTime();
  				showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack,500,430,objName);
  		}
  		function openNewEmpTreeCallBack(arg){//回调方法
            if(objvalue=="03"){
            	if(arg['Role']){ //原写法无需判断是否为空
      		  		var sorgidArra  = arg['Role'].slice(0);//角色数组
      		  		argRes=[[],[],[],];
      				for(var i=0;i<sorgidArra.length;i++){
      					argRes[0].push(sorgidArra[i].getProperty("roleid"));
      					argRes[1].push(sorgidArra[i].getProperty("rolename"));
      				}
      				$id("publishRange").value = argRes[0];
      				$id("objName").value = argRes[1];
      			}else{
      				$id("publishRange").value = "";
      				$id("objName").value = "";
      			}
            }else if(objvalue=="02"){
            	if(arg['Employee']){ //原写法无需判断是否为空
      		  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
      		  		argRes=[[],[],[],[]];
      				for(var i=0;i<sorgidArra.length;i++){
      					argRes[0].push(sorgidArra[i].getProperty("empid"));
      					argRes[1].push(sorgidArra[i].getProperty("empname"));
      				}
      				$id("publishRange").value = argRes[0];
      				$id("objName").value = argRes[1];
      				//$id("relativeEmpid").value = argRes[1];
      			}else{
      				$id("objName").value = "";
      				$id("publishRange").value = "";
      			}
            }else{
            	if(arg['Organization']){ //原写法无需判断是否为空
      		  		var sorgidArra  = arg['Organization'].slice(0);//人员数组
      		  		argRes=[[],[],[],[]];
      				for(var i=0;i<sorgidArra.length;i++){
      					argRes[0].push(sorgidArra[i].getProperty("orgcode"));
      					argRes[1].push(sorgidArra[i].getProperty("orgname"));
      					argRes[2].push(sorgidArra[i].getProperty("orgid"));
      				}
      				$id("publishRange").value = argRes[0];
      				$id("objName").value = argRes[1];
      				$id("orgid").value = argRes[2];
      				//$id("obtainObj").value=argRes[1];
      			}else{
      				$id("publishRange").value = "";
      				$id("objName").value = "";
      				$id("orgid").value="";
      			}
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
  	     					  	alert("操作成功!")
  	 					  		unMaskTop();
  	 					  		/**
  	 					  		*2014.9.1 改成弹出的方式
  	 					  		*/
  	 					  		window.close();
  	     					 /* WEB.turnMainFrame(); */
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
  	  	 	var _form = $id("form1");
  	  	 	if(arg!=""){
  	  	  	 	url="/messagePublish/tMessagePublishAction_insertTMessagePublish.action?"+arg;	
  	  	  	    _form.action =url
  	  	        // 异步提交请求 
  				$("#form1").ajaxSubmit(options);
  	  	 	}
  				//arg ="taskAssgineeDto.empids=1,2&taskAssgineeDto.empnames=张三，李四&taskAssgineeDto.transitionName=同意";  	  	  
  	  	   }
  		}
 </script>
</html>
