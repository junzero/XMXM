<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <script type="text/javascript" src="/ReportServer?op=emb&resource=finereport.js&inter=zh_CN"></script>
    <script type="text/javascript" src="/common/gotop/applet_print.js"></script>
    <title>My JSP 'prrr.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
		<object id="flashMovie" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="https://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab"
		 width="300" height="300" align="middle">
		<param name="movie" value="/ReportServer?op=resource&amp;resource=/com/fr/web/core/printer.swf&amp;gzip=false">
		<param name="quality" value="high">
		<param name="bgcolor" value="#ffffff">
		<embed src="/ReportServer?op=resource&amp;resource=/com/fr/web/core/printer.swf&amp;gzip=false" 
				quality="high" bgcolor="#ffffff" width="300" height="300" name="flashMovie" 
				align="middle" type="application/x-shockwave-flash" pluginspage="https://www.macromedia.com/go/getflashplayer">
		</object>

	  
	  <input type="button" value="FR打印测试" onclick="testFun()">
	  <input type="button" value="IE浏览器" onclick="IE_Flash()">
	  <input type="button" value="其它浏览器" onclick="FF_or_Chrome_Flash()">
	  <script type="text/javascript">
//	  	/ReportServer, 12493, 1, false, 0:595,841, false
		function testFun2(){
			var fm1 = window.flashMovie;
			var fm2 = document.flashMovie;
			alert(fm1+"----"+fm2);
			alert(fm1.doLoadFlash+"----"+fm2.doLoadFlash);
		}
		function testFun(){
			var sessionID = getSessionID();
			alert("--执行序列--"+sessionID);
			var fm = ($.browser.msie && parseInt($.browser.version) < 9) ? window.flashMovie: document.flashMovie;
			fm.doLoadFlash("/ReportServer", sessionID, 1, false, "0:595,841",false);
		}
		
		function getSessionID(){
			var sessionID;
			var url = "<%=basePath%>/ReportServer?reportlet=WorkBook1.cpt&op=getSessionID&time="+new Date().getTime();
			$.ajax({
				url: url,
				type: "POST",
				async: false,
				complete: function(res, status) {
					if (status == "success") {
						sessionID = res.responseText
					}
				}
			});
			return sessionID;
		}
	  </script>
	  
	  
		<script type="text/javascript"> 
//		<!-- 
			// U can change this number to check specific version of flash 
			var MM_contentVersion = 6; 
			var plugin = (navigator.mimeTypes && navigator.mimeTypes["application/x-shockwave-flash"]) ? 
			navigator.mimeTypes["application/x-shockwave-flash"].enabledPlugin : 0; 
			if ( plugin ) { 
				var words = navigator.plugins["Shockwave Flash"].description.split(" "); 
				for (var i = 0; i < words.length; ++i){ 
					if (isNaN(parseInt(words[i]))) 
						continue; 
					var MM_PluginVersion = words[i]; 
				} 
				var MM_FlashCanPlay = MM_PluginVersion >= MM_contentVersion; 
			} 
			else if( navigator.userAgent && navigator.userAgent.indexOf("MSIE")>=0 && (navigator.appVersion.indexOf("Win") != -1) ) { 
				//FS hide this from IE4.5 Mac by splitting the tag 
				document.write('<SCR' + 'IPT LANGUAGE=VBScript\> \n'); 
				document.write('on error resume next \n'); 
				document.write('MM_FlashCanPlay = ( IsObject(CreateObject("ShockwaveFlash.ShockwaveFlash." & MM_contentVersion)))\n'); 
				document.write('</SCR' + 'IPT\> \n'); 
			} 
			if ( MM_FlashCanPlay ) { 
				alert("falsh安装完成，可正常使用"); 
			} 
			else{ 
				alert("Flash检测失败。需要下载特定版本。"); 
			} 
		//--> 
		</script> 
		
		<script type="text/javascript"> 
			//自己总结的 
			//IE中判断是否安装插件 
			var swf; 
			function IE_Flash() 
			{ 
				try 
				{ 
					var swf=new ActiveXObject("ShockwaveFlash.ShockwaveFlash"); 
					alert("已安装插件"); 
				}catch(e){ 
					alert("没有安装插件"); 
				} 
			} 
			//FireFox,Chrome中判断是否安装插件 
			function FF_or_Chrome_Flash() 
			{ 
				var swf=navigator.plugins["Shockwave Flash"]; 
				(swf)?alert("已安装插件"):alert("没有安装插件"); 
			}
		</script> 
	  
  </body>
</html>
