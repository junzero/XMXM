		function FR_getSessionID(reportUrl){
			var sessionID;
			var url = "/ReportServer?reportlet="+reportUrl+"&op=getSessionID&time="+new Date().getTime();
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
		function FR_doURLFlashPrint(jsessionid,basePath,reportUrl){
			var sid = FR_getSessionID(reportUrl);
			var appletPrinter_html = 
			     '		<object name="AppletPrinter" width="0" height="0" id="AppletPrinter" classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" codeBase="'+basePath+'/jre.exe">'
				+'			<param name="code" value="com.fr.report.print.PrintApplet"/>         '
				+'			<param name="archive" value="'+basePath+'/fr-applet-7.0.jar"/>       '
				+'			<param name="url" value="'+basePath+'/ReportServer;jsessionid='+jsessionid+'?sessionID='+sid+'&op=fr_applet&cmd=print"/>        '
				+'			<param name="isIE9" value="8.0"/>    '
				+'			<param name="isShowDialog" value="false"/>   '
				+'			<param name="index" value="0"/>      '
				+'			<param name="isSingleSheet" value="false"/>  '
				+'		</object>      ';
				
			var mydiv = window.document.createElement("div");
			document.body.appendChild(mydiv);
			mydiv.innerHTML = appletPrinter_html;
			window.mydiv=mydiv;
		}