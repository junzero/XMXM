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
	 <h:hidden id="messageId" property="message.messageId"/>
	 <h:hidden id="executionId" property="message.flowId"/>
    <table align="center" border="0" width="100%" class="form_table" >
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        	信息详情
         </td>
      </tr>
      <tr>
      	<td class="form_label" align="right" style="width:120px;">
                                                     <nobr>标 题：</nobr>
        </td>
        <td colspan="3">
          <h:text property="message.messageTitle" id="messageTitle" readonly="true" validateAttr="allowNull=false" style="width:90%;" />
        </td>
      </tr><tr>
      	<td class="form_label" align="right">
                                                     <nobr>发布人：</nobr>
        </td>
        <td colspan="3">
        	<h:text property="message.createname" size="15" readonly="true" />
          	<%-- <b:write property="message.createname"/> --%>
        </td>
        </tr>
        <tr>
	        <td class="form_label" align="right">
	                                                     <nobr>发布机构：</nobr>
	        </td>
	        <td colspan="3">
	          	<h:text property="message.orgname" size="15" readonly="true" />
	          	<%-- <b:write property="message.orgname"/> --%>
	        </td>
      </tr>
      <tr>
       <td class="form_label" align="right"><nobr>信息类型：</nobr></td>
     	<td colspan="3">
     		<d:select dictTypeId="ZHPT_MESSAGE_TYPE" property="message.messageType" id="messageType" style="width:130px;" disabled="true" nullLabel="请选择"></d:select>
     	</td>
     	<%-- <td class="form_label" align="right">
                                                     发布人：
        </td>
        <td>
        	<h:text property="message.createname" size="15" readonly="true" />
          	<b:write property="message.createname"/>
        </td> --%>
      </tr>
      <tr>
       <td class="form_label" align="right"><nobr>发布类型：</nobr></td>
     	<td colspan="3">
     		<d:radio dictTypeId="ZHPT_PUBLISH_TYPE" property="message.publishType" onchange="doChange();"  disabled="true" value="01" />
     	</td>
        <%-- <td class="form_label" align="right">
          发布机构：
        </td>
        <td>
        <h:text property="message.orgname" size="15" readonly="true" />
          	<b:write property="message.orgname"/>
        </td> --%>
      </tr>
      <tr>
        <td class="form_label" align="right"><nobr>发布范围：</nobr></td>
            <td colspan="3">
               <h:textarea id="objName" property="message.objName"  extAttr="class='h80'" readonly="true"  validateAttr="allowNull=false;" rows="4"  style="width:90%;" />
			  <h:hidden id="publishRange" property="message.publishRange" validateAttr="allowNull=false;" />
			  <h:hidden id="orgid" property="message.orgid" />
			  <h:hidden name="message.status" property="status"/>
			  <!--
			  <a href="#" onclick="open_newyw_tree_fun()">选择</a>
            -->
            </td>
      </tr>
      <tr>
     	<td class="form_label" align="right"><nobr>内容：</nobr></td>
     	<td colspan="3">
	     	<h:textarea  extAttr="class='h80' "  property="message.content" validateAttr="maxLength=512"  rows="4"  style="width:90%;" />
		    <font style="color: red">*</font>
     	</td>
      </tr>
      <tr id="row1">
      <td class="form_label" align="right"><nobr>附件下载：</nobr></td>
      <td colspan="3">
      <div id="tag"></div>
      </td>
      </tr>
      <tr id="row_opinion">
     	<td class="form_label" align="right"><nobr>意见：</nobr></td>
     	<td colspan="3">
	     	<h:textarea  extAttr="class='h80' "  property="message.opninion" name="message.opninion" id="opinion" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;"/>
		    <font style="color: red">*</font>
     	</td>
      </tr>
     	<%@include file="/jsp/util/default_opinionUtil.jsp" %>
      <tr class="form_bottom">
        <td colspan="4">
          <input type="button" value="关闭" class="button" id="savebtn1" onclick="window.close();"  />
          <input type="button" value="转发" class="button" id="savebtn2" onclick="transmit();"  />
          <input type="button" value="转督办" class="button" id="savebtn3" onclick="transup();" style="display:none;">
          <input type="button" value="阅毕" class="button" id="savebtn4" onclick="readlyRead();"  />
          <input type="button" value="查看流程" onclick="doflowpic();" class="button" id="flowpic" />
         </td>
      </tr>
       <tr>
     <td class="form_label" align="right"><nobr>流程列表：</nobr></td>
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
	        data: "resourceType=01&resourceId=${message.messageId}",
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
	  if('${status}'=='1'){
		  $("#savebtn4").css("display","none");}
	  var positionId = ${sessionScope.login_user.positionId[0]};
		if(positionId=='4'){
			$("#savebtn3").show();};
  });
  function initPlanCell20(){
		var queryCond="";
		queryCond += "<resourceId>${message.messageId}</resourceId>";
		queryCond += "<resourceType>01</resourceType>";
			return queryCond;
		}
  function transmit(){
			var strUrl = "/messagePublish/tMessagePublishAction_transmitEmp.action?messageId=${message.messageId}&message.opninion="+encodeURI($("#opinion").val());
	  		showModalCenter(strUrl, null, closeWindow, 400, 150, '转发'); 
	}

	function closeWindow(arg){
		if(arg!='')
		window.close();}

  function readlyRead(){
	  if('${status}'!='1'){
		  $.ajax({
		        url: "/messagePublish/tMessagePublishAction_insertMessageReadPer.action?message.messageId="+$("#messageId").val()+"&message.opninion="+encodeURI($("#opinion").val()),
		        async: false,
		        type: 'post',
		        dataType: 'text',
		        timeout: 60000,
				success : function(data){
				    if(data.indexOf("success")>=0){
					    alert("已阅毕!");
					    window.close();
				    }else{
					   alert("操作失败!");
				    }
					},  
				error : function(data){
					 alert("系统出错，请联系管理员");
				   }  
		    });
	  }else{
		  alert("该信息已阅毕,无须操作!!");
	  }
		
  	
	}

	function transup(){
		var strUrl = "/messagePublish/tMessagePublishAction_openWinSup.action?message.messageId=${message.messageId}&message.flowId=${message.flowId}&taskAssgineeDto.businessType=${taskAssgineeDto.businessType}&btnType=02" +"&message.opninion="+$('#opinion').val();
		strUrl = encodeURI(strUrl);
			showModalCenter(strUrl, null, winClose, 500, 150, '转督办');
		}

	function winClose(arg){
		if(arg!='')
	  	  	window.close();
	  	}

	function readlyRead(){
		  if('${status}'!='1'){
			  $.ajax({
			        url: "/messagePublish/tMessagePublishAction_insertMessageReadPer.action?message.messageId="+$("#messageId").val()+"&message.opninion="+encodeURI($("#opinion").val()),
			        async: false,
			        type: 'post',
			        dataType: 'text',
			        timeout: 60000,
					success : function(data){
					    if(data.indexOf("success")>=0){
						    alert("已阅毕!");
						    window.close();
					    }else{
						   alert("操作失败!");
					    }
						},  
					error : function(data){
						 alert("系统出错，请联系管理员");
					} 
			    });
		  }else{
			  alert("该信息已阅毕,无须操作!!");
		  }
	}	
 </script>
</html>
