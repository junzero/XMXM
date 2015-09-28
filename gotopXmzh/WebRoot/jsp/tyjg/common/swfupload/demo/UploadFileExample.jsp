<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>批量附件上传</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="/common/scripts/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="/common/scripts/swfupload/swfupload.swfobject.js"></script>
		<script type="text/javascript" src="/common/scripts/swfupload/swfupload.queue.js"></script>
		<script type="text/javascript" src="/common/scripts/swfupload/fileprogress.js"></script>
		<script type="text/javascript" src="/common/scripts/swfupload/handlers.js"></script>
	</head>
	<body leftmargin="0" rightmargin="0" topmargin="0" scroll="no">
		<!-- 内容开始 -->
		<div id="content">
			<!-- 控制按钮 -->
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td>
						<span id="spanButtonPlaceholder"></span>
						<input id="btnUpload" type="button" value="提交上传" class="button"/>
						<input id="btnCancel" type="button" value="取消全部" disabled="disabled" class="button"/>
						等待上传<span id="idFileListCount">0</span> 个 ，
						成功上传<span id="idFileListSuccessUploadCount">0</span> 个，
						成功删除<span id="idFileListDelCount">0</span> 个
					</td>
				</tr>
			</table>
			
			<!-- 文件列表 -->
			<table id="idFileList" width="100%" class="EOS_table" border="0">
				<tr>
					<th width=20>
						<nobr>&nbsp;</nobr>
					</th>
					<th align="center">
						<nobr>文件名</nobr>
					</th>
					<th align="center" width="100px">
						<nobr>文件大小</nobr>
					</th>
					<th width="200px" align="center">
						<nobr>状态</nobr>
					</th>
					<th width="35px" align="center">
						<nobr>操作</nobr>
					</th>
				</tr>
			</table>
			<div id="divSWFUploadUI" style="visibility: hidden;"></div>
			<noscript
				style="display: none; margin: 10px 25px; padding: 10px 15px;">
				很抱歉，附件上传界面无法载入，请将浏览器设置成支持JavaScript。
			</noscript>
			<div id="divLoadingContent" class="content">
				附件上传界面正在载入，请稍后...
			</div>
			<div id="divLongLoading" class="content">
				附件上传界面载入失败，请确保浏览器已经开启对JavaScript的支持，并且已经安装可以工作的Flash插件版本。
			</div>
			<div id="divAlternateContent" class="content">
				很抱歉，附件上传界面无法载入，请安装或者升级您的Flash插件。 请访问：
				<a href="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash"
					target="_blank">Adobe网站</a> 获取最新的Flash插件。
			</div>
		</div>
		<script type="text/javascript">
			// uploadCheckBox  写入文档	
			settings.post_params["atFileupload.relationId"]="11";
			settings.post_params["atFileupload.groupId"]="11";
			settings.post_params["atFileupload.attachmentSrcId"]="11";
			settings.post_params["atFileupload.attachmentSrcCd"]="11";
			settings.post_params["atFileupload.fileSave"]="1";
			settings.post_params["atFileupload.fileCatalog"]="signimage";
			settings.file_upload_limit=10;//最多上传条数
			settings.file_queue_limit=1;//每次上传数
			settings.auto_upload=true;//自动上传
			settings.file_types_description="上传文件";//弹出选择框时类型说明
			settings.file_types="*.*";//上传文件类型
			settings.file_size_limit="10 MB";//上传文件大小限制,防攻击服务器设置了最大500M限制
			settings.custom_settings.delAuth = "2";//是否允许删除。ifDel 0：不限制 1:仅人员  2、仅限权限字段 3、人员及权限字段  。其它不允许
			settings.custom_settings.dowAuth = "0";//是否允许下载。
			
			//以前为可选参数
			settings.custom_settings.submitName = "uname";//必填参数
			var swfu = new SWFUpload(settings);
			__createSwtUploadData();
		</script>
		<input type="button" onclick="__createSwtUploadData()" value="swtUploadData"/>
	</body>
</html>
