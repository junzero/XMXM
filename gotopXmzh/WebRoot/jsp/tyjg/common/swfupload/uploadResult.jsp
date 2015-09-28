<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'uploadResult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
   <%
   HashMap hmp = (HashMap)request.getAttribute("resultMap");
	String falg = String.valueOf(hmp.get("isSucc"));
   %>
   <script type="text/javascript">
   		if("<%=falg%>"=="true"){
			alert("附件上传成功！");
			window.location.href="/common/attachmentAction_uploadFile.action";
   	   	}else{
			alert("附件上传失败！");
			window.history.go(-1);
   	   	}
   </script>
    	<%
    		List<String> list = (List<String>)request.getAttribute("fileList");
    		int size = list.size();
    		StringBuffer realFileName = new StringBuffer(50);
    		StringBuffer path = new StringBuffer(50);
    		StringBuffer spaceSize = new StringBuffer(50);
    		StringBuffer attachementId = new StringBuffer(50);
    		StringBuffer uploadStatusArray = new StringBuffer(50);
    		for(int i = 0; i < size;i++){
    			String tempMap = list.get(i);
    			uploadStatusArray.append(tempMap);
    		}
    	%>
    	<% %>
    	<script type="text/javascript">
    		var realFileName = "<%=realFileName.toString()%>";
    		var  path = "<%=path.toString()%>";
    		var  spaceSize = "<%=spaceSize.toString()%>";
    		var  attachementId = "<%=attachementId.toString()%>";
    		var  uploadStatusArray = "<%=uploadStatusArray.toString()%>";
    		window['returnValue'] =realFileName+"#"+path+"#"+spaceSize+"#"+attachementId+"#"+uploadStatusArray;
    		window.closeD(); 
    	</script>
    	
  
  </body>
</html>
