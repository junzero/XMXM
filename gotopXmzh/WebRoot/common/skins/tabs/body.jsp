<%@include file="/common.jsp"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<h:script src="/common/skins/tabs/scripts/TabControl.js"></h:script>
<HTML>
	<HEAD>
		<title>frmClient</title>
		
		<script language="JavaScript" type="text/JavaScript">
		<!--
		function MM_swapImgRestore() { //v3.0
		var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
		}

		function MM_preloadImages() { //v3.0
		var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
			var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
		}

		function MM_findObj(n, d) { //v4.01
		var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
			d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
		if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
		for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
		if(!x && d.getElementById) x=d.getElementById(n); return x;
		}

		function MM_swapImage() { //v3.0
		var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
		if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
		}
		//-->
		</script>
		<script language="javascript">
			/// <summary>
			/// 页标题滚动条移动
			/// </summary>
			/// <param name="b" type="bool">移动的方向 true 左</param>
			function taskMove(b)
			{
				
				i = taskbarContainer.document.body.scrollLeft;

				if (!b)//向前
				{
					i-=50;
			
					if (i<0) i=0;
					taskbarContainer.document.body.scrollLeft = i;
				}
				else
				{
					i+=50;
					taskbarContainer.document.body.scrollLeft = i;
				}
			}
			
			/// <summary>
			/// 移动到最后
			/// </summary>
			function taskMoveEnd()
			{
				i = taskbarContainer.document.body.scrollWidth - tdBar.document.body.offsetWidth;
				if (i > 0)
				{
					taskbarContainer.document.body.scrollLeft += i;
				}
			}
			
			/// <summary>
			/// 得到激活状态下的iFrame对象
			/// </summary>
			function GetActiveFrame()
			{
				var tabName = "tabSub_" + self.frames["taskbarContainer"].window.getActiveCode() + "_1";
				alert(tabName);
				if (self.frames[tabName])
				{
					return self.frames[tabName];
				}
				else
				{
					return null;
				}
			}
			
			/// <summary>
			/// 第一次加载导航条
			/// </summary>
			function FirstBind()
			{
				//debugger;
				return;
				var objFrame = GetActiveFrame();
				if (objFrame != null)
				{
					try
					{
						if (objFrame.document.readyState == "complete")///　如果加载完就邦定
						{
							BindPliot();
						}
						else
						{
							window.setTimeout("FirstBind();", 1000);
						}
					}
					catch(e)
					{
						return;
					}
				}
			}
			
			/// <summary>
			/// 新打开一个页对象(对外接口)
			/// </summary>
			/// <param name="objID" type="number">页面ID</param>
			/// <param name="objTitle" type="string">Tab页的标题字符串</param>
			/// <param name="objParam" type="string">打开新页的QueryString</param>
			function newOpen(objID, objTitle, objParam)
			{
				//debugger;
				var tableName = "tabSub";
				var objDiv = window.self;
				self.frames["taskbarContainer"].window.newOpen(tableName, padContextpath(objID), objTitle, objDiv, objParam);
				taskMoveEnd();
				//FirstBind();
				///BindPliot();		///邦定导航条
			}
			
			/// <summary>
			/// 在当前的Tab对象中打开页(对外接口)
			/// </summary>
			/// <param name="objID" type="number">页面ID</param>
			/// <param name="objTitle" type="string">Tab页的标题字符串</param>
			/// <param name="objParam" type="string">打开新页的QueryString</param>
			function currentOpen(objID, objTitle, objParam)
			{
				var tableName = "tabSub";
				var objDiv = window.self;
				self.frames["taskbarContainer"].window.currentOpen(tableName, padContextpath(objID), objTitle, objDiv, objParam);
				//FirstBind();
			}
	
			/// <summary>
			/// 存在选中，不存在打开
			/// </summary>
			/// <param name="objID" type="number">页面ID</param>
			/// <param name="objTitle" type="string">Tab页的标题字符串</param>
			/// <param name="objParam" type="string">打开新页的QueryString</param>
			function otherOpen(objID, objTitle, objParam)
			{
				//debugger;
				var tableName = "tabSub";
				var objDiv = window.self;
				self.frames["taskbarContainer"].window.otherOpen(tableName, padContextpath(objID), objTitle, objDiv, objParam);
				//FirstBind();
				///BindPliot();		///邦定导航条
			}
			
			/// <summary>
			/// 存在选中，不存在打开带有审核信息的页面
			/// </summary>
			/// <param name="objID" type="number">页面ID</param>
			/// <param name="objTitle" type="string">Tab页的标题字符串</param>
			/// <param name="objParam" type="string">打开新页的QueryString</param>
			/// <param name="instId" type="string">流程实例ID</param>
			function otherOpenInfo(objID, objTitle, objParam, instId)
			{
				//debugger;
				var tableName = "tabSub";
				var objDiv = window.self;
				self.frames["taskbarContainer"].window.otherOpenInfo(tableName, objID, objTitle, objDiv, padContextpath(objParam), instId);
				//FirstBind();
				///BindPliot();		///邦定导航条
			}

			///删除一个页对象
			///说明：调用子框架页中的页对象删除方法
			///tableName:页标题表格的id objDiv:框架所在对象（传递这个参数是为了在子框架页中操作父页面的元素对象）
			function deletePageNode()
			{
				try
				{
					var tableName = "tabSub";
					var objDiv = window.self;
					if (objDiv)
						self.frames["taskbarContainer"].window._deletePage(tableName, objDiv);
				}
				catch(e)
				{
					throw e;
				}
			}
			
						///删除所有页对象
			///说明：调用子框架页中的页对象删除方法
			///tableName:页标题表格的id objDiv:框架所在对象（传递这个参数是为了在子框架页中操作父页面的元素对象）
			function deleteAllPageNode()
			{
				try
				{
					var tableName = "tabSub";
					var objDiv = window.self;
					if (objDiv)
						self.frames["taskbarContainer"].window._deleteAllPage(tableName, objDiv);
				}
				catch(e)
				{
					throw e;
				}
			}
	
			///将当前激活的Tab页信息添加到收藏夹中
			///新增一条收藏夹记录，有两个参数：Tab页的标题和相对应的框架的地址
			///调用收藏夹子页面的增加记录方法
			function AddCPISFavorites()
			{
				if (confirm('确实要将当前页放入会员收藏夹吗？'))
				{
					//插入记录;
					var tabName = self.frames["taskbarContainer"].window.getActiveTabName();
					var tabActive = self.frames["taskbarContainer"].window.getActiveCode();
					var tabGUID = self.frames["taskbarContainer"].window.getActiveTabGUID();
					tabActive = "tabSub" + "_" + tabActive + "_1"; //URL地址
					var frmObj = document.all(tabActive);
					if (frmObj != null)
					{				
						//tabURL = frmObj.src;
						tabURL = tabGUID;
						if (!parent.frames["fraLeftFrame"].window.frames["kptabSub_3_1"]){
							parent.frames["fraLeftFrame"].window.ptabNew("3");
							setTimeout("addRecord(\"" + tabName + "\", \"" + tabGUID + "\")", 1000);
						}
						else
							parent.frames["fraLeftFrame"].window.frames["kptabSub_3_1"].window.CPISAddRecord(tabName,tabURL);		
						return true;
					}
					else
					{
						return false;
					}
					parent.frames["fraLeftFrame"].window.frames["kptabSub_3_1"].window.CPISAddRecord(tabName, tabGUID);
				}
				else
				{
					return false;
				}
			}
			
			function addRecord(tabName, tabGUID){
				if (parent.frames["fraLeftFrame"].window.frames["kptabSub_3_1"].document.readyState == "complete")
					parent.frames["fraLeftFrame"].window.frames["kptabSub_3_1"].window.CPISAddRecord(tabName, tabGUID);
				else
					setTimeout("addRecord(\"" + tabName + "\", \"" + tabGUID + "\")", 1000);
					
			}

			///打开系统收藏夹添加界面
			function AddFav()
			{
				//window.external.AddFavorite("http://www.sina.com.cn","新浪");
				window.alert(window.location.hostname);
			}


			///显示，隐藏功能栏（设置表格单元的可见）
			///在表格单元中放一个<span>对象,设置一个初始值，这个值用来控制可见还是不可见；
			///将这个单元格的点击事件映射到这个函数
			function switchSysBar(){
			if (switchPoint.innerText==3){
				switchPoint.innerText=4
				document.all("tdLeft").style.display="none"
			}else{
				switchPoint.innerText=3
				document.all("tdLeft").style.display="block"
			}}

			function showTable()
			{
				var obj = window.document.all("tdLeft");
				obj.style.display="block";
			}

			function hideTable()
			{
				var obj = window.document.all("tdLeft");
				obj.style.display="none";
			}

			function AddFavorties()
			{
				window.external.AddFavorite(window.parent.window.location.href,'信息管理系统');
			}
			
			function CPISExchange()
			{
				showTable();
			}

			function CPISPrint()
			{
				alert("打开打印页!");
			}

			function CPISEmail()
			{
				alert("打开邮件页");
			}
			
			/// <summary>
			/// 邦定导航条
			/// </summary>
			/// <returns>邦定成返回true</returns>
			function BindPliot()
			{
				return;
				var objFrameName = "tabSub_" + self.frames["taskbarContainer"].window.getActiveCode() + "_1";
				var objPilot = document.all("pilotMain");
				if (objPilot.datasetExp != null)
				{
					setElementDataset(objPilot, null);
					refreshDataPilot(objPilot);
				}
				
				//var objFrame = self.frames[objFrameName].window.document.forms(0);
				//debugger;
				if (self.frames[objFrameName]._array_dataset)
				{
					var objFrame = self.frames[objFrameName]._array_dataset;

					// 遍历整个表单的控件，发现DataSet控件，如果是受控的
					// debugger;
					for (i=0; i<objFrame.length; i++)
					{
						//if (属性是要受控制的)
						///设置导航条（导航条对象，dataset对象）
						if (objFrame[i].isControl)
						{
							setElementDataset(objPilot, objFrame[i]);
							objFrame[0].refreshControls();
							return;
						}
						//objFrame[0].refreshDataPilot(objPilot);
						
					}
				}
			}
			
			/// 5秒刷新一下导航条
			/*function BindShow()
			{
				if (self.frames["taskbarContainer"])
				{
					if (newPageType)
					{
						BindPliot();
					}
				}
				window.setTimeout("BindShow();", 5000);
			}
			var newPageType = false;
			window.setTimeout("BindShow();", 10000);*/
			
			
			//对URL增加Context
			function padContextpath(url){
			  var headexpr = new RegExp("^/");
			  var tailexpr = new RegExp("\\.jsp(\\?.*){0,1}$");
			  var root = "<%=contextPathStr %>";
			  if(root=="/") root="";
			  if(tailexpr.test(url)){
			    if(headexpr.test(url)){
			      return root+url;
			    }else{
			      return root+"/"+url;
			    }
			  }else{
			    return url;
			  }
			}
		</script>
		
	</HEAD>
	<body bottomMargin="0" leftMargin="0" topMargin="0" scroll="no" rightMargin="0" MS_POSITIONING="GridLayout" background="tab_images/tch1.gif">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	  <tr> 
	    <td width="1"><img src="images/spacer.gif" width="1" height="1"></td>
	    <td>
			<TABLE id="tabMain" height="100%" cellSpacing="0" cellPadding="0" width="100%" border="0">
				<TR>
					<td align="left" height="29" width="100%" style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; BORDER-TOP-STYLE: none; PADDING-TOP: 0px; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: none">
						<table cellSpacing="0" cellPadding="0" border="0" style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 100%; BORDER-TOP-STYLE: none; PADDING-TOP: 0px; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; POSITION: static; HEIGHT: 100%; BORDER-BOTTOM-STYLE: none">
							<tr height="5">
								<td colspan="2"><img src="images/spacer.gif" width="1" height="1"></td>
							</tr>
							<tr id="trBar" height="24">
								<td id="tdBar">
									<IFRAME id="taskbarContainer" style="WIDTH: 100%; HEIGHT: 23" tabIndex="0" src="frmTabPageTitle.jsp" frameBorder="0" width="100%" scrolling="no" height="100%"></IFRAME>
								</td>
								<td width="125">
									<table height="100%" cellSpacing="0" cellPadding="0" width="100%" align="left" >
										<tr valign="middle">
											<td width="25" style="cursor:hand"><a onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('imgPrev','','<%=request.getContextPath()%>/common/skins/tabs/top_images/pretab-1.gif',1)"><img id="imgPrev" title="向前翻页"  onclick="taskMove(false);" src="<%=request.getContextPath()%>/common/skins/tabs/top_images/pretab.gif"></a></td>
											<td width="25" style="cursor:hand"><a onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('imgNext','','<%=request.getContextPath()%>/common/skins/tabs/top_images/nexttab-1.gif',1)"><img id="imgNext" title="向后翻页" onclick="taskMove(true);" src="<%=request.getContextPath()%>/common/skins/tabs/top_images/nexttab.gif" border="0"></a></td>
											<td width="25" style="cursor:hand"><a onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('imgClose','','<%=request.getContextPath()%>/common/skins/tabs/top_images/closetab-1.gif',1)"><img id="imgClose" title="关闭" onclick='setTimeout("deletePageNode();",0)' src="<%=request.getContextPath()%>/common/skins/tabs/top_images/closetab.gif" border="0"></a></td>
											<td width="25" style="cursor:hand"><a onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('imgClose','','<%=request.getContextPath()%>/common/skins/tabs/top_images/closetab1-1.gif',1)"><img id="imgClose" title="全部关闭" onclick='setTimeout("deleteAllPageNode();",0)' src="<%=request.getContextPath()%>/common/skins/tabs/top_images/closetab1.gif" border="0"></a></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</TR>
				<tr id="trContent">
					<td id="CPISContent"></td>
				</tr>
			</TABLE>
		</td>
	  </tr>
	</table>
	</body>
</HTML>