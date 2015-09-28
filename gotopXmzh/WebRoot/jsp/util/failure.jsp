<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>操作失败</title>
<link rel="stylesheet" type="text/css" href="/themes/web.css">
<meta http-equiv="refresh" content="3;url=javascript:window.history.back()"/>  
<style type="text/css">
body,html{height: 100%; }
</style>
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
	  <img src="/images/worn.gif"/>
		   <logic:present name="message">
			 <bean:write name="message" />
		   </logic:present>  
		   三秒钟后自动返回............
    </div>
	<div id="ts_foot">
	 <input type="button"  value="返回" class="button" onclick="window.history.back()"/>	 
	</div>
    </div>
  </div>
</div>
</div>
</body>
</html>


