<%@include file="/common.jsp"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<HTML>
	<HEAD>
		<TITLE>CPISTabPageTitle</TITLE>
		<meta content="Microsoft Visual Studio 7.0" name="GENERATOR">
		<meta content="C#" name="CODE_LANGUAGE">
		<meta content="JavaScript" name="vs_defaultClientScript">
		<meta content="http://schemas.microsoft.com/intellisense/ie5" name="vs_targetSchema">
		<SCRIPT>
			<!--  
				if (window.Event)  
				document.captureEvents(Event.MOUSEUP);  
				function nocontextmenu(){  
				event.cancelBubble = true  
				event.returnValue = false;  
				return false;  
				}   
				function norightclick(e){  
				if (window.Event){  
				if (e.which == 2 || e.which == 3)  
				return false;  
				}  
				else  
				if (event.button == 2 || event.button == 3){  
				event.cancelBubble = true  
				event.returnValue = false;  
				return false;  
				}  
				}   
				document.oncontextmenu = nocontextmenu; // for IE5+  
				document.onmousedown = norightclick; // for all others  
			//-->
		</SCRIPT>
		
		<SCRIPT language="JavaScript">
			document.oncontextmenu=new Function("event.returnValue=false;");
			document.onselectstart=new Function("event.returnValue=false;");
			function stop(){
				alert('此处禁止右键。')//把这里换成你想要看到的话。。
				return false;
			}
			document.oncontextmenu=stop;
		</script>
		
		<noscript></noscript>
		
		<script language="javascript">
			/// <summary>
			/// 初始化Tab页,初始时只有一页，主页
			/// </summary>
			function InitPageLoad()
			{
				var tableName = "tabSub";
				var objTitle = new Array();
				objTitle[0] = "主页.系统";
				
				var objDiv = window.frameElement;
				//window.parent.ADBind();
				initTabPage(tableName, objTitle, objDiv);
				//window.parent.ADBind();
			}
			
			/// <summary>
			/// 得到激活状态下Tab页的GUID
			/// </summary>
			function getActiveTabGUID()
			{
				var tableName = "tabSub";
				var tabPage = document.all(tableName);
				var selectCell = tabPage.selectTab;
				if (selectCell != null)
				{
					return selectCell.tab_GUID;
				}
				else
				{
					return null;
				}
			}
			
			/// <summary>
			/// 得到激活状态下Tab页的标题
			/// </summary>
			function getActiveTabName()
			{
				var tableName = "tabSub";
				var tabPage = document.all(tableName);
				var selectCell = tabPage.selectTab;
				if (selectCell != null)
				{
					return selectCell.innerText;
				}
				else
				{
					return null;
				}
			}
			
			/// <summary>
			/// 得到激活状态下Tab页的tab_code
			/// </summary>
			function getActiveCode()
			{
				var tableName = "tabSub";
				var tabPage = document.all(tableName);
				var selectCell = tabPage.selectTab;
				if (selectCell != null)
				{
					return selectCell.tab_code;
				}
				else
				{
					return null;
				}	
			}
		</script>
		<h:script src="/common/skins/tabs/scripts/TabControl.js"></h:script>
	</HEAD>
	<body bottomMargin="0" leftMargin="0" topMargin="0" onload="InitPageLoad();" rightMargin="0" MS_POSITIONING="GridLayout" bgcolor="#edf0f7">
		<form id="CPISTabPageTitle" method="post" runat="server">
			<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" id="tabSub" >
		    </TABLE>
		</form>
	</body>
</HTML>