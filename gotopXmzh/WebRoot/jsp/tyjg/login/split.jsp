<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      分割条
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
		body{
			/* background-image: url("${pageContext.request.contextPath }/common/skins/default/images/sideline.gif"); */
		}
	</style>
  </head>
  <body style="margin: 0px;margin-top: 150">
  	<img src="${pageContext.request.contextPath }/common/skins/tabs/images/slideclosed.gif" id='slideId' onclick="showAndHidden(this);" title="显示"/>
  	<script type="text/javascript">
  		var flag = false;
  		function showAndHidden(obj){
  			var doc = document; 
  			/* var tar = window.top.frames["bottomFrame"];
  			var opencols = "245,10,*";
   			var closecols="0,10,*"; */
   			//window.document.getElementById("framecontentLeft").style.diaplay = 'none';
   			if(flag){
				if(screen.width>1024){
		   			$("#framecontentLeft",window.parent.document).show();
		   			$("#framecontentSplit",window.parent.document).css("left","190px");
		   			$("#maincontent",window.parent.document).css("left","200px");
		   		}
				else if(screen.width>800){
		   			$("#framecontentLeft",window.parent.document).show();
		   			$("#framecontentSplit",window.parent.document).css("left","190px");
		   			$("#maincontent",window.parent.document).css("left","200px");
		   		}
				else{
		   			$("#framecontentLeft",window.parent.document).hide();
		   			$("#framecontentSplit",window.parent.document).css("left","0px");
		   			$("#maincontent",window.parent.document).css("left","10px");
		   		}
				obj.src='${pageContext.request.contextPath }/common/skins/tabs/images/slideopened.gif';
				obj.title='隐藏';
			}else{
	   			$("#framecontentLeft",window.parent.document).hide();
	   			$("#framecontentSplit",window.parent.document).css("left","0px");
	   			$("#maincontent",window.parent.document).css("left","10px");
				obj.src='${pageContext.request.contextPath }/common/skins/tabs/images/slideclosed.gif';
				obj.title='显示';
			}
			flag = !flag;
  		}
  	</script>
  </body>
</html>
