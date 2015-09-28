<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>
<html>
	<head>
		<title>批量附件上传</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body leftmargin="0" rightmargin="0" topmargin="0" scroll="no">
		<!-- name为必填项，其它为可选项 -->
		<h:sFile id="swfu" name="uname" relationId="33" groupId="33" attachmentSrcId="33" attachmentSrcCd="44"
				 fileSave="2" fileCatalog="signimage" uploadLimit="3" queueLimit="0" autoUpload="true" types="*.*"
				 sizeLimit="500 MB" typesDescription="上传文件" width="100%" height="100%" autoQueryData="true"
				 delAuth="1" dowAuth="0" autoConfirm="true"
		/>
		<script type="text/javascript">
			function checkform(){
				var wait = __isWaitUploadQueued();
				if(wait){
					alert('请等待文件上传后再点击提交！');
				}else{
					alert('执行提交！');
				}
			}
			function downloadByGroup(){
				var relationId="";
				var groupId="";
				__downloadByGroup(relationId,groupId);
			}
			function deleteCheckBox(){
				var result = __deleteUploadCheckBox();
				if(result){
					alert('删除成功！');
				}
			}
		</script>
		<input onclick="checkform()" value="提交前检测是否上传完成" type="button" class="button"/>
		<input onclick="downloadByGroup()" value="批量下载" type="button" class="button"/>
		<input onclick="deleteCheckBox()" value="批量删除" type="button" class="button"/>
		
		<iframe id="ifid" src="" width="0" height="0"></iframe>
	</body>
</html>
