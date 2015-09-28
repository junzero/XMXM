<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>   
<%@include file="/common/common.jsp"%>                                     
<%@include file="/common/skins/skin1/component.jsp" %>                     
<h:css href="/css/style1/style-custom.css"/>                               
<%--                                                                         
- Author(s):                                                     
- Date: 
- Description:                                                               
--%>                                                                         
<html>                                                                       
  <head>                                                                     
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/> 
    <title>                                                                  
                                                       
    </title>                                                                 
  </head>                                                                    
  <script type="text/javascript">
	  function uploadFile(){
	  	var strUrl = "/jsp/tyjg/common/swfupload/uploadAttachment.jsp?BH=0102&_ts=" +new Date();
	  	showModalCenter(strUrl, null, uploadCallBackParent, 500, 400, '附件上传'); 
	  }
	  function uploadFile2(){
	  	var strUrl = "/jsp/tyjg/common/swfupload/uploadAttachment.jsp?BH=0102&_ts=" +new Date();
	  	showModalCenter(strUrl, null, uploadCallBackParent, 500, 400, '附件上传'); 
	  }
	  function removeTr(obj,path){
	  	if(confirm("是否确定删除该附件？")){
	  		var testTable = document.getElementById("uploadlIST");
	  		//0101
	  		var myAjax = new Ajax("/common/attachmentAction_deleteSingleAttachment.action");
	  		myAjax.addParam("path", path);
	  		myAjax.addParam("srccd", "0102");
	  		myAjax.submit();
	  		var returnValue = myAjax.getValue("root/data/isSucc");
	  		if(returnValue=="success"){
	  			testTable.deleteRow(obj);
	  		}else{
	  			alert("删除文件失败");
	  			return;
	  		}
	  	}
	  }
	  
		function removeHistoryTr(obj,attachementid){
			if(confirm("是否确定删除该附件？")){
				var testTable = document.getElementById("uploadlIST");
				//0101
				var myAjax = new Ajax("/attachment/attachment_delete.action");
				myAjax.addParam("fileid", attachementid);
				myAjax.submit();
				var returnValue = myAjax.getValue("root/data/rtun");
				if(returnValue=="success"){
					testTable.deleteRow(obj);
				}else{
					alert("删除文件失败");
					return;
				}
			}
		}
	  //上传附件回调方法
	  function uploadCallBackParent(args){
		  if(args){
			  	var paramArray = args.split("#");
			  	var realNameArray = paramArray[0].split("*");
			  	var pathArray = paramArray[1].split("*");
			  	var spaceSizeArray = paramArray[2].split("*");
			  	var attachementIdArray = paramArray[3].split("*");
			  	var uploadStatusArray = paramArray[4].split("*");
			  	var doc = document;
			  	var table = doc.getElementById("uploadlIST");
			  	for(var i = 0;i < realNameArray.length; i++){
			  		var tr = table.insertRow();
			  		var rowIndex2 = tr.rowIndex;
			  		var td = tr.insertCell();
			  		var str = realNameArray[i] +"&nbsp;<input type='button' value='删除' class='button' onclick='removeTr("+rowIndex2+",\""+pathArray[i]+"\");'/>";
			  		str += "<input type='hidden' name='uploadRealName' value='"+realNameArray[i]+"'/>";
			  		str += "<input type='hidden' name='uploadPath' value='"+pathArray[i]+"'/>";
			  		str += "<input type='hidden' name='uploadSpacesize' value='"+spaceSizeArray[i]+"'/>";
			  		str += "<input type='hidden' name='attachementId' value='"+attachementIdArray[i]+"'/>";
			  		str += "<input type='hidden' name='uploadStatus' value='"+uploadStatusArray[i]+"'/>";
		  			td.innerHTML = str;
			  	}
		  }
	  }
  </script>
  <body topmargin="0">                                                     
	     <table id="uploadlIST" border="1" width="900">
			<tbody id="uploadBody">
			</tbody>
		</table>
		<input type="button" class="button" value="选择" onclick="uploadFile();"/>
		<input type="button" class="button" value="选择2" onclick="uploadFile2();"/>
  </body>
</html>
