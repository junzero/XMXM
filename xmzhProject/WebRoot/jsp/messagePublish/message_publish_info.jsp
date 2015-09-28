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

<style type="text/css">
.mask{position: absolute; 
top: 0px; 
left: 0px; 
filter: alpha(opacity=50); 
-moz-opacity:0.5; 
opacity:0.5; 
background-color: #ffffff; 
z-index: 2; 
display: none;} 
/* 弹出基本资料div */ 
div.sample_popup {height:auto; border: 1px solid #327eca; width: 300px; } 
div.menu_form_header{ 

} 
div.sample_popup div.menu_form_header 
{ 
border-bottom: 0px; 
cursor: default; 
width:100%; 
height: 22px; 
line-height: 22px; 
vertical-align: middle; 
text-decoration: none; 
font-family: "Times New Roman", Serif; 
font-weight: 800; 
font-size: 13px; 
color: #206040; 
} 
div.menu_form_body 
{ 
width:100%; 
height:150px; 
font-size:12px;
word-wrap: break-word;
} 
div.sample_popup input.menu_form_exit 
{ 
float: right; 
margin: 4px 5px 0px 0px; 
cursor: pointer; 
} 
</style>
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
                                                     标 题：
        </td>
        <td colspan="3">
          <h:text property="message.messageTitle" id="messageTitle" readonly="true" validateAttr="allowNull=false" style="width:90%;" />
        </td>
      </tr>
      <tr>
       <td class="form_label" align="right">信息类型：</td>
     	<td colspan="3">
     		<d:select dictTypeId="ZHPT_MESSAGE_TYPE" property="message.messageType" id="messageType" style="width:130px;" disabled="true" nullLabel="请选择"></d:select>
     	</td>
      </tr>
      <tr>
       <td class="form_label" align="right">发布类型：</td>
     	<td colspan="3">
     		<d:radio dictTypeId="ZHPT_PUBLISH_TYPE" property="message.publishType" onchange="doChange();"  disabled="true" value="01" />
     	</td>
      </tr>
      <tr>
        <td class="form_label" align="right">发布范围：</td>
            <td colspan="3">
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
	     	<h:textarea  extAttr="class='h80' "  property="message.content" validateAttr="maxLength=512"  rows="4"  style="width:90%;" />
		    <font style="color: red">*</font>
     	</td>
      </tr>
      <tr id="row1">
      <td class="form_label" align="right">附件下载：</td>
      <td colspan="3">
      <div id="tag"></div>
      </td>
      </tr>
      <tr id="row_opinion">
     	<td class="form_label" align="right">意见：</td>
     	<td colspan="3">
	     	<h:textarea  extAttr="class='h80' "  property="message.opninion"  name="message.opninion" id="opinion" validateAttr="maxLength=512;allowNull=false" rows="4"  style="width:90%;"/>
		    <font style="color: red">*</font>
     	</td>
      </tr>
     	<%@include file="/jsp/util/default_opinionUtil.jsp" %>
      <tr class="form_bottom">
        <td colspan="4">
          <input type="button" value="关闭" class="button" id="savebtn1" onclick="window.close();"  />
          <input type="button" value="转发" class="button" id="savebtn2" onclick="transmit();"  />
          <input type="button" value="阅毕" class="button" id="savebtn3" onclick="readlyRead();"  />
          <input type="button" value="查看流程" onclick="doflowpic();" class="button" id="flowpic" />
         </td>
      </tr>
       <tr>
     <td class="form_label" align="right">流程列表：</td>
     <td colspan="3">
     <!-- 遮罩层 --> 
<div id="mask" class="mask"> 
</div> 
<!-- 弹出基本资料详细DIV层 --> 
<div class="sample_popup" id="popup" style="visibility: hidden; display: none;"> 
<div class="menu_form_header" id="popup_drag"> 
<input type="button" id="popup_exit" value="退出" /> 
 <span id="tx">详细意见</span> 
</div> 
<div class="menu_form_body" id="ct">
</div> 
<input type="button" value="复制" onclick="coptct();">
</div> 
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
		  $("#savebtn3").hide();}
  });
  function initPlanCell20(){
		var queryCond="";
		queryCond += "<resourceId>${message.messageId}</resourceId>";
		queryCond += "<resourceType>01</resourceType>";
			return queryCond;
		}
  function transmit(){
			var strUrl = "/messagePublish/tMessagePublishAction_transmitEmp.action?messageId=${message.messageId}";
	  		showModalCenter(strUrl, null, closeWindow, 400, 150, '转发'); 
	}
  function closeWindow(arg){
	  alert(arg);
		if(arg!=''){
			window.close();
		}
	}

  
 </script>
</html>
