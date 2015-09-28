<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>流程管理</title>
	</head>
	<body topmargin="0" leftmargin="0">
	<h:form name="deployProcess" action="/jbpm/jbpmDemoAction_deployProcess.action" method="post">
		<input type="submit" id="btn" class="button" value='加载流程配置'>
	</h:form>
	<script type="text/javascript">

		</script>
	</body>
</html>