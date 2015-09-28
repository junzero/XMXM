<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>

<%
	//获取标签中使用的国际化资源信息
	String fileManager = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("FileuploadManager_l_title_fileManager");
	String fileUpload =  com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("FileuploadManager_l_title_fileUpload");
%>
<html>
<head>
<title><b:message key="FileuploadManager_l_title_fileManager"></b:message></title>
</head>
<body topmargin="0" leftmargin="0">
    <w:tabPanel id="fileuploadManagerTab" height="100%" width="100%">
        <w:tabPage id="filemanager" tabType="iframe" title="<%=fileManager%>" url="/common/attachmentAction_queryWithDB.action" >
        </w:tabPage>
        <w:tabPage id="fileupload" tabType="iframe" title="<%=fileUpload%>" url="/common/attachmentAction_uploadFile.action" >
        </w:tabPage>
        <w:tabPage id="fileupload_ui" tabType="iframe" title="上传实例_带UI_Tld" url="/jsp/tyjg/common/swfupload/demo/UploadFileExample_tld.jsp" >
        </w:tabPage>
    </w:tabPanel>
</body>
</html>
