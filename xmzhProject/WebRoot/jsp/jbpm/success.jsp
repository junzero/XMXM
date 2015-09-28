<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0">
<l:equal property="ostr.flag" targetValue="1">
	<script>
  		alert("<b:write property="ostr.fstr"/>");
  		window.returnValue = "1";
  		window.close();
	</script>
</l:equal>
<l:equal property="ostr.flag" targetValue="2">
	<script>
  		alert("<b:write property="ostr.fstr"/>");
  		history.back(-1);
	</script>
</l:equal>

</body>
</html>