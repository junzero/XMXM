<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html style="height:100%;width:100%;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=basePath%>/js/test/jquery.calendar.js"></script>
		<%@ include file="/common.jsp" %>
		<script type="text/javascript" src="../js/test/jquery-calendar.js"></script>
		<link rel="stylesheet" type="text/css" href="../js/test/jquery-calendar.css" />
	</head>
	<script type="text/javascript">
   function ajaxupload()
   { jQuery.blockUI({message:"<img src='imgs/loading.gif'/>"});
   }
  </script>
	<script type="text/javascript">
		 
			jQuery(document).ready(function (){ 
				jQuery("#calendar1, #calendar2").calendar();
				jQuery("#calendar1_alert").click(function(){alert(popUpCal.parseDate(jQuery('#calendar1').val()))});
			});
		 
		</script>
	<body>
		 
		<br>
		<input type="text" id="calendar1" class="calendarFocus" />
		<input type="button" id="calendar1_alert" value="Alert datetime object"/>
	</body>
</html>
