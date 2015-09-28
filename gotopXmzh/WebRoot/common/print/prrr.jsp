<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.gotop.util.security.ForUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
  	<script type="text/javascript">
  		var dTime_str = new Date().getTime();
  	</script>
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
	  <script type="text/javascript">
			function FR_doURLAppletPrint(jsessionid,basePath,reportUrl){
				var dTime_start = new Date().getTime();
				var sid = FR_getSessionID(reportUrl);
				var dTime_end = new Date().getTime();
				var appletPrinter_html = 
				     '		<object name="AppletPrinter" width="400" height="400" id="AppletPrinter" classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" codeBase="'+basePath+'/jre.exe">'
					+'			<param name="code" value="com.fr.report.print.PrintApplet"/>         '
					+'			<param name="archive" value="'+basePath+'fr-applet-7.0.jar"/>       '
					+'			<param name="url" value="'+basePath+'ReportServer;jsessionid='+jsessionid+'?sessionID='+sid+'&op=fr_applet&cmd=print"/>        '
					+'			<param name="isIE9" value="8.0"/>    '
					+'			<param name="isShowDialog" value="false"/>   '
					+'			<param name="index" value="0"/>      '
					+'			<param name="isSingleSheet" value="false"/>  '
					+'		</object>      ';
					
				var appletPrinter_html='<applet name="AppletPrinter" id="AppletPrinter" code="com.fr.report.print.Plus2PrintApplet.class" archive="'+basePath+'fr-applet-7.0.jar?=<%ForUtil.outObj(out,new java.util.Date().getTime());%>" width="400" height="400">'
								 +'	<param name="url" value="'+basePath+'ReportServer;jsessionid='+jsessionid+'?sessionID='+sid+'&op=fr_applet&cmd=print">'
								 +'	<param name="isIE9" value="0">'
								 +'	<param name="index" value="0">'
								 +'	<param name="jsessionid" value="'+jsessionid+'">'
								 +'	<param name="isShowDialog" value="false">'
								 +'	<param name="isSingleSheet" value="true">'
								 +'	<param name="codebase_lookup" value="false">'
								 +'</applet>';
					
				var mydiv = window.document.createElement("div");
				document.body.appendChild(mydiv);
				mydiv.innerHTML = appletPrinter_html;
				var dTime_all = new Date().getTime();
				
//				alert((dTime_start-dTime_str)/1000+"--秒-"+(dTime_end-dTime_start)/1000 +"--秒--" + (dTime_all-dTime_end)/1000+"秒");
				window.mydiv=mydiv;
				
				var at = document.getElementsByTagName("APPLET");
//				alert(at[0]);
				var urlStr = basePath+"ReportServer;jsessionid="+jsessionid+"?reportlet="+reportUrl+"&op=getSessionID";
//				alert("---urlStr-"+urlStr);
				alert(at[0].printUrlJs(urlStr,false));
			}
	  
	  
//			var url="/ReportServer?reportlet=<%ForUtil.outPm(out,request,"reportUrl");%>";
//			FR.doURLFlashPrint(url,1); //参数true表明不弹对话
			var opType = "<%ForUtil.outPm(out,request,"opType");%>";
			opType=3;
			if(opType==1){
				FR_doURLFlashPrint('<%ForUtil.outObj(out,session.getId());%>','<%ForUtil.outObj(out,basePath);%>','<%ForUtil.outPm(out,request,"reportUrl");%>');
			}else if(opType=='2'){
				FR.doURLPDFPrint('<%ForUtil.outPm(out,request,"reportUrl");%>',false);//；true时弹出打印对话框，false不弹出
			}else{
				FR_doURLAppletPrint('<%ForUtil.outObj(out,session.getId());%>','<%ForUtil.outObj(out,basePath);%>','<%ForUtil.outPm(out,request,"reportUrl");%>');
			}
	  </script>
  </body>
</html>
