<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="3;url='javascript:window.closeD()'">     
<link rel="stylesheet" type="text/css" href="/themes/web.css">
<title>操作成功</title>
<style type="text/css">
body,html{height: 100%; }
</style>
<script>
	setTimeout("window.closeD()",3000);
	function closeW(){
		window.closeD();
	}
</script>
</head>
<body>
<div id="rightout">
<!--star menu-->
  <div id="Rmenu">
    <div class="menu">
      <ul>

      </ul>
    </div>
  </div>
<div id="ts">
  <div id="ts_middle">
    <div id="ts_inner" class="greenBorder">
	<div id="ts_bar"><span>&nbsp;</span>系统提示</div>
	<div id="ts_content">
		<logic:present name="message">
			<span class="style1"><strong><bean:write name="message" /></strong></span>
		</logic:present>
		   三秒钟后自动关闭当前窗口............ 
    </div>
	<div id="ts_foot">
	 <input type="button"  value="关闭" class="button" onclick="window.closeD()"/>
	</div>
    </div>
  </div>
</div>
</div>
</body>
</html>


