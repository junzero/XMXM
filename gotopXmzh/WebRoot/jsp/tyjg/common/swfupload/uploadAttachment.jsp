<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<h:css href="/css/oldstyle/yw_table.css"/>
<h:css href="/css/oldstyle/style-button.css"/>
<h:script src="/common/gotop/showModal_patch.js"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>问题单附件上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
<%--  <h:form id ="form1" name="form1" action="/common/attachmentAction_uploadWithOutDb.action" --%>
  <h:form id ="form1" name="form1" action="/common/attachmentAction_uploadWithDB.action" 
  	method="post" enctype="multipart/form-data" onsubmit="return checkAttachement(this);">
  	<table class="YW_panel_body" width="100%">

  	<input type="hidden" name="attachment.srcCd" value="<%ForUtil.outPm(out,request,"BH");%>"/>
			<tr>
					<td class="YW_panel_head" align="center" style="background-color:#184CB4">
						问题单反馈查询
					</td>
				</tr>
				<tr>
					<td>
					    <table  id="tabtest" align="center" border="0" width="100%" class="form_table">
					    	<tr>
					    		<td>
					    			<input type="file" name="uploadfile" id="uploadfile" style="width:80%"/>
									<input type="button" class="button" value="新增附件" onclick="addMore();"/>
					    		</td>
					    	</tr>
					    	<tr>
					    		<td colspan="2" id="td">&nbsp;</td>
					    	</tr>
					    	<tr  align="center" >
					    		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    			<input type="submit" class="button" value="上传"/>
					    			<input type="button" class="button" value="关闭" onclick="javascript:window.closeD();"/>
					    		</td>
					    	</tr>
					    </table>
    				</td>
    			</tr>
    	</table>
    </h:form>
    <iframe id="iframe1" name="iframe1" style="display: none;"></iframe>
    <script type="text/javascript">
	    var i=1;
		function addMore()
		{
		   var td = document.getElementById("td");
		   var br = document.createElement("br");
		   var input = document.createElement("input");
		   var button = document.createElement("input");
		   input.name="uploadfile";
		   input.id="uploadfile";
		   input.type="file";
		   input.style.width="90%";
		   button.type="button";
		   button.value="删除";
		   var classss = document.createAttribute("class");
		   classss.value="button";
		   button.setAttributeNode(classss);
		  	
		  // button.setAttributeNode(classss);
		   button.onclick=function()
		   {
		   	if(confirm("确定移除该文件吗"))
		   	{
		   		td.removeChild(br);
		   		td.removeChild(input);
		   		td.removeChild(button);
		   		i--;
		   	}
		   }
		   	td.appendChild(br);
		   	td.appendChild(input);
		   	td.appendChild(button);
		   i++;
		}
		function checkAttachement(objForm){
			var fileArray = $names("uploadfile");
			var size = fileArray.length;
			for(var i = 0 ;i < size; i++){
				var fileOne = fileArray[i];
				if(fileOne.value==""){
					alert("请选择上传的附件");
					return false;
				}
			}
			return true;
  	  	}
    </script>
  </body>
</html>
