<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>操作成功</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="3;url=${forwardURL}"/>  
<link href="/css/default_common.css" rel="stylesheet" rev="stylesheet" media="all" />
<link href="/css/default_tipbox.css" rel="stylesheet" rev="stylesheet" media="all" />
</head>
<body>
	<center>
	<div id="Tipbox">
    	<div class="Tipbox_L">
        	<div class="Tipbox_R"><span>信息提示</span><a href="#">关闭</a></div>
        </div>
        <div class="Tipbody">
        	<div class="inside">
            	<div class="Tip_icon_win"></div>
            	<div class="Tip_msg">
            		<p><logic:present name="message">
			 			<bean:write name="message" />
		   			</logic:present>  
		  			 三秒钟后自动返回............</p>
           			<p><a href="#" onclick="window.location=${forwardURL}">返回首页</a></p>
           		</div>
            </div>
        </div>
    </div>
    </center>
</body>
</html>


