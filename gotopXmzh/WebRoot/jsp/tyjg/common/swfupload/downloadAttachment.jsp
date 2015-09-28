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
  <body topmargin="0">                                                     
	<h:link href="/common/attachmentAction_download.action" property="ATTACHMENT_NAME" iterateId="id5">
			<h:param property="ATTACHMENT_INFO_ID" name="fileid" iterateId="id5" />
	</h:link>
  </body>
</html>
