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
<title>信息发布</title>
</head>
  <body>
  <e:datasource name="message" type="bean" path="com.gotop.messagePublish.model.TMessagePublish" />
    <h:form name="form1" id="form1" action="/messagePublish/tMessagePublishAction_insertTMessagePublish.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
         <h:hidden property="message.messageId"/>
         <h:hidden property="message.flowId"/>
	     <h:hidden property="message.createDate"/>
	     <h:hidden property="message.createEmpid" id="createor"/>
         <h:hidden id="executionId" property="taskAssgineeDto.executionId"/>
         <h:hidden id="taskId"  property="taskAssgineeDto.nextTaskId"/>
         <h:hidden id="taskAssingee" property="taskAssgineeDto.taskAssingee"/>
         <h:hidden id="beginOrg" property="taskAssgineeDto.beginOrg"/>
         <h:hidden id="definitionId" property="taskAssgineeDto.definitionId"/>
         <h:hidden id="businessType" property="taskAssgineeDto.businessType"/>
           <h:hidden id="templateFileIds" property="taskAssgineeDto.templateFileIds"/>
         <h:hidden name="isFirst" value="1" />
		<input type="hidden" id="btnType" name="btnType" />
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
          <h:text property="message.messageTitle" id="messageTitle" validateAttr="allowNull=false" style="width:90%;" /><font style="color: red">*</font>	
        </td>
      </tr>
      <tr>
       <td class="form_label" align="right">信息类型：</td>
     	<td colspan="3">
	     	<div id="dis1" >
	     		<d:select dictTypeId="ZHPT_MESSAGE_TYPE" property="message.messageType" id="messageType" style="width:130px;" nullLabel="请选择"></d:select><font style="color: red">*</font>	
	     	</div>
	     	<div id="dis2" style="display:none;" >
	        	<d:write property="message.messageType" dictTypeId="ZHPT_MESSAGE_TYPE" />
	        </div>
     	</td>
      </tr>
      <tr>
       <td class="form_label" align="right">发布类型：</td>
     	<td colspan="3">
     		<d:radio dictTypeId="ZHPT_PUBLISH_TYPE" property="message.publishType" onclick="doChange(this.value);" value="01" />
     	</td>
      </tr>
      <tr>
        <td class="form_label" align="right">发布范围：</td>
            <td colspan="3">
              <h:textarea id="objName" property="message.objName"  extAttr="class='h80' "  validateAttr="allowNull=false;" rows="4"  style="width:90%;" />
			  <h:hidden id="publishRange" property="message.publishRange" />
			  <h:hidden id="orgid" property="message.orgid" />
			  <div align="left">
				  <a id="selectR" href="#" onclick="open_newyw_tree_fun()">选择</a>
				  <a id="selectG" href="#" onclick="open_group_tree_fun()" style="display: none">选择群组</a>
			  </div>
            </td>
      </tr>
      <tr>
     	<td class="form_label" align="right">内容：</td>
     	<td colspan="3">
	     	<h:textarea property="message.content"  extAttr="class='h80' "  validateAttr="maxLength=1024" rows="4"  style="width:90%;" />
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
     <tr id="rowTemplate">
					<td class="form_label" align="right">模板文件：</td>
					<td colspan="3"><input type="button" value="下载模板"
						class="button"
						onclick="downLoadTemplateFiles('<b:write property="taskAssgineeDto/templateFileIds" />');">
					</td>
				</tr>
      <tr class="form_bottom">
        <td colspan="4">
         <input type="button" value="结束流程" class="button" onclick="doDeleteProcess('<b:write property="taskAssgineeDto/businessKey" />','01');" id="deleteProcessBtn">
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
    </h:form>
  </body>
 <script type="text/javascript">
 var publishRange01="";
 var objName01="";
 var orgid01="";
 var publishRange02="";
 var objName02="";
 var publishRange03="";
 var objName03="";
 
 $(function(){
	 var templateFileIds = $("#templateFileIds").val();
		if(templateFileIds == "" || templateFileIds == null){
			$("#rowTemplate").hide();
		}
	//隐藏结束流程按钮
		$("#deleteProcessBtn").hide();
		if('${sessionScope.login_user.empid}'=='${message.createEmpid}'){
			  if($("#executionId").val()){
				  //当登录人等于发起人，显示结束流程按钮
				  $("#deleteProcessBtn").show();
		  	    }
		}
	 if('${message.messageId}'!=""){
		 if('${message.createEmpid}'!='${sessionScope.login_user.empid}')
		 $("#save1").css("display","none"); 
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
				         	if('${isView}'!='')
			    	        	$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId});
				         	else
				         		$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId,remove:1});
			          		});	
			        }else{
			        	 $("#row1").css("display","none");  
			        }    
		        }
	    });	
		 $("#beginOrg").val("${message.orgid}");
		 WEB.doDisabledAttr('${isView}','${message.createEmpid}','${sessionScope.login_user.empid}',"");
	 }else{
		 $("#row1").css("display","none");  
		 $("#row2").css("display","none"); 
		 $("#beginOrg").val('${sessionScope.login_user.orgid}'); 
	 }
	 if('${isView}'!=''){
		 $("#deleteProcessBtn").hide();
			$("#save1").hide();
			$("#smit").hide();
			$("#row1").show();
			$("#rowTemplate").hide();
			WEB.hideFile();
			document.getElementById("dis2").style.display='block';
			  document.getElementById("dis1").style.display='none';
			  $("input[name='message.publishType']").attr("disabled","true");
			  $("#selectR").hide();
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
    		$("#btnType").val(value);
    		if(value!="1"){
    		if(checkForm($id("form1"))){
    			var strUrl = "/jbpm/jbpmDemoAction_toNextTaskConfig.action?taskAssgineeDto.executionId="+$id("executionId").value+"&taskAssgineeDto.beginOrg="+$("#beginOrg").val()+"&taskAssgineeDto.beginAssingee="+$("#createor").val()+"&taskAssgineeDto.definitionId=${taskAssgineeDto.definitionId}";
        		showModalCenter(strUrl, null, taskAssigneeCallBack, 700, 400, '节点选择');
    		}	
    		}else{
    			var _form = $id("form1");
    	  	  	 	url="/messagePublish/tMessagePublishAction_insertTMessagePublish.action";	
    	  	  	    _form.action =url
    	  	  	if(checkForm($id("form1")))
    			    ajaxsubmitO(0);
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
		
		function doChange(value){
			if(value=="01"){
				$id("publishRange").value="";
				$id("objName").value="";
				$id("orgid").value="";
				$id("publishRange").value = publishRange01;
				$id("objName").value = objName01;
				$id("orgid").value=orgid01;
				$("#selectG").hide();
			}else if(value=='02'){
				$id("publishRange").value="";
				$id("objName").value="";
				$id("orgid").value="";
				$id("publishRange").value = publishRange02;
				$id("objName").value = objName02;
				$("#selectG").show();
			}else if(value=='03'){
				$id("publishRange").value="";
				$id("objName").value="";
				$id("orgid").value="";
				$id("publishRange").value = publishRange03;
				$id("objName").value = objName03;
				$("#selectG").hide();
			}else{
				return false;
			}
			
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
				/* strUrl = "/tree/initMainTree_mainTree.action?orgType=4&changeTree.showTabOrg=0&changeTree.showTabGroup=0&changeTree.showTabRole=1&showSelBox=4";
				objName="角色发布";
				var paramEntity = new ParamEntity('Role');
				paramEntity.setProperty('roleid',$id("publishRange").value);
				paramEntity.setProperty('rolename',$id("objName").value);
				peArgument[2]=[paramEntity,'rolename','roleid']; */
				/**
  				*2014.9.1 添加岗位发布
  				*/
				strUrl = "/empMngr/positionSelectWin2_empMngr.action";
				peArgument[0]=$id("publishRange").value;
				peArgument[1]=$id("objName").value;
				objName="岗位发布";
				showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack,700,500,objName); 
				return;
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
  		       /*  strUrl += "&time="+new Date().getTime(); */
  				showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack,500,430,objName);
  		}
  		function openNewEmpTreeCallBack(arg){//回调方法
            if(objvalue=="03"){
                if(arg!=""){
	            	if(arg['Role']){ //原写法无需判断是否为空
	      		  		var sorgidArra  = arg['Role'].slice(0);//角色数组
	      		  		argRes=[[],[],[],];
	      				for(var i=0;i<sorgidArra.length;i++){
	      					argRes[0].push(sorgidArra[i].getProperty("roleid"));
	      					argRes[1].push(sorgidArra[i].getProperty("rolename"));
	      				}
	      				$id("publishRange").value = argRes[0];
	      				$id("objName").value = argRes[1];
	      				 publishRange03=argRes[0];
	    				 objName03=argRes[1];
	      			}else{
	      				 /* $id("publishRange").value = "";
	      				$id("objName").value = ""; */ 
	
	      				/**
	      				*2014.9.1 添加岗位发布
	      				*/
	      				$id("publishRange").value =arg[0];
	      				$id("objName").value =arg[1];
	      				$id("orgcode").value=arg[2];
	      			}
                }
            }else if(objvalue=="02"){
                if(arg!=""){
	            	if(arg['Employee']){ //原写法无需判断是否为空
	      		  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
	      		  		argRes=[[],[],[],[]];
	      				for(var i=0;i<sorgidArra.length;i++){
	      					argRes[0].push(sorgidArra[i].getProperty("empid"));
	      					argRes[1].push(sorgidArra[i].getProperty("empname"));
	      				}
	      				$id("publishRange").value = argRes[0];
	      				$id("objName").value = argRes[1];
	      				publishRange02=argRes[0];
	   				    objName02=argRes[1];
	      				//$id("relativeEmpid").value = argRes[1];
	      			}else{
	      				$id("objName").value = "";
	      				$id("publishRange").value = "";
	      			}
                }
            }else{
                if(arg!=""){
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
	      				publishRange01=argRes[0];
	   				    objName01=argRes[1];
	   				    orgid01=argRes[2];
	      				//$id("obtainObj").value=argRes[1];
	      			}else{
	      				$id("publishRange").value = "";
	      				$id("objName").value = "";
	      				$id("orgid").value="";
	      			}
                }
            }
  			
  		}

  		function open_group_tree_fun(){
  				strUrl ="/tree/initMainTree_mainTree.action?changeTree.showTabGroup=1&changeTree.showSelBox=2&changeTree.orgType=2";
				objName="群组选择";  
				var peArgument = [];
				var paramEntity = new ParamEntity('Employee');
				paramEntity.setProperty('empid',$id("publishRange").value);
				paramEntity.setProperty('empname',$id("objName").value);
				peArgument[2]=[paramEntity,'empname','empid'];	
				showModalCenter(strUrl,peArgument,openNewGroupTreeCallBack,500,430,objName);
  	  	}

	  	function openNewGroupTreeCallBack(arg){
	  		if(arg!=""){
            	if(arg['Employee']){ //原写法无需判断是否为空
      		  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
      		  		argRes=[[],[],[],[]];
      				for(var i=0;i<sorgidArra.length;i++){
      					argRes[0].push(sorgidArra[i].getProperty("empid"));
      					argRes[1].push(sorgidArra[i].getProperty("empname"));
      				}
      				$id("publishRange").value = argRes[0];
      				$id("objName").value = argRes[1];
      				publishRange02=argRes[0];
   				    objName02=argRes[1];
      				//$id("relativeEmpid").value = argRes[1];
      			}else{
      				$id("objName").value = "";
      				$id("publishRange").value = "";
      			}
            }
		  }
  		
  	  	function taskAssigneeCallBack(arg){
  	 	var _form = $id("form1");
  	 	if(arg!=""){
  	  	 	url="/messagePublish/tMessagePublishAction_insertTMessagePublish.action?"+arg;	
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
      					  /**
 					  		*2014.9.1 改成弹出的方式
 					  		*/
 					  		if(flag==1)
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
  	  	$("#form1").ajaxSubmit(options);
  	  	}

  		function goback(){
  			window.history.go(-1);
  		}

 </script>
</html>
