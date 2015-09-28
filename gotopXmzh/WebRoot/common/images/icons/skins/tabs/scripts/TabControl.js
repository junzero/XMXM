/********************************************************
 功能描述:
	实现横向的动态Tab页
 接口说明:
    1.初始化Tab集
    2.动态加载Tab页(三个接口，在新页打开，在本页打开，
	  如果存在选中本页否则打开新页)
 修改时间:
	2003/11/6
 使用注意:
	实现原理是用表格来模拟Tab页的标题，用层（里面包含
	iFrame）来打开页；为了实现Tab页标题的移动，所以表
	格和层可以在不同的框架页面中，在本功能中应包含在
	表格页中,tableName为表格id号 objDiv:层所在父对象（
	不在同一页面中）
 ********************************************************/

/// <summary>
/// 初始化Tab页
/// </summary>
/// <param name="tableName" type="string">tab页所在Table的id</param>
/// <param name="objTitle" type="Array()">tab页的唯一标识名，可以找到他的地址
///    <see> objTtle = "缺陷登记.缺陷管理.缺陷通知"; </see>
/// </param>
/// <param name="objDiv" type="object">层对象的父对象</param>
function initTabPage(tableName, objTitle, objDiv)
{　　
	var tabPage = window.document.all(tableName);	///表格对象
	//debugger;
	//清除tabPage第一行表格中的所有内容
	for(i=0; i<tabPage.tBodies[0].rows.length; i++)
	{
		var row = tabPage.tBodies[0].rows[i];
		row.removeNode(true);
	}
	
	var objValue = new Array();		///GUID和URL对象
	var objTemp;
	//for(i=0; i<objTitle.length; i++)
	//{
	//	objTemp = new Object();
	//	objTemp = window.parent.ADSearch(objTitle[i]);
	//	if (objTemp != null)
	//	{
	//		objValue[i] = objTemp;
	//	}
	//	else
	//	{
	//		alert(objTitle[i] + "  所在页面还没有注册！");
	//		return;
	//	}
	//为了加快速度上面代码加载主页的程序先写死，即初始化只加载主页，不能动态加载N页；
	var objAtt = new Object();
	objAtt.DC = "主页.系统";
	objAtt.DN = "主页";
	objAtt.URL = "bodymain.jsp";
	objValue[0] = objAtt;
	//}
		
	//设置表格的开始图标
	var row=tabPage.tBodies[0].insertRow();
	var cell=row.insertCell();
	cell.firstcell=true;
	cell.innerHTML="<img src=\""+contextPath+"/common/skins/tabs/tab_images/start_tab.gif\" valign=\"top\">";
	
	//在父页面上插入层
	for(i=0; i<objTitle.length; i++)
	{
		var divName = tableName + "_" + (i+1);
		var newDiv = objDiv.document.createElement("<div id='" + divName + "' style=\"PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 100%; BORDER-TOP-STYLE: none; PADDING-TOP: 0px; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; POSITION: static; HEIGHT: 100%; BORDER-BOTTOM-STYLE: none\"></div>");
		var newObject = objDiv.document.all("CPISContent");
		if (newObject != null)
		{
			newObject.insertBefore(newDiv);
		}
	}
	
	//设置Tab页图标
	var label,desc, tmpURL, tabcode, index;
	for(i=0; i<objTitle.length; i++)
	{
		label = objValue[i].DN;
		desc = objTitle[i];
		tmpURL = objValue[i].URL;
		tabcode = i + 1;
		
		//标题单元格图标
		cell=row.insertCell();
		
		cell.background=contextPath+"/common/skins/tabs/tab_images/tab_button.gif";
		cell.tab_index=i;
		cell.tab_code=tabcode;
		cell.tab_GUID=desc;

		//标题主体内容，背景图
		btn=document.createElement("<DIV hideFocus=true nowrap class=tabpagebtn style=\"CURSOR: hand; FONT-SIZE: 9pt; FONT-FAMILY: 宋体; TEXT-ALIGN: center\"></DIV>");
		btn.innerText=label;
		btn.tabIndex=-1;
		btn.id=tabcode;	
		btn.tab_GUID=desc;	
		btn.onclick=_tabpage_onclick;
		btn.ondblclick=_tabpage_ondblclick;
		btn.onmouseover=_tabpage_onmouseover;
		btn.onmouseout=_tabpage_onmouseout;
		btn.tab=cell;
		cell.appendChild(btn);

		cell=row.insertCell();
		if (i!=objTitle.length-1){
			cell.innerHTML="<img src=\""+contextPath+"/common/skins/tabs/tab_images/tab.gif\">";
		}
		else{
			cell.lastcell=true;
			cell.innerHTML="<img src=\""+contextPath+"/common/skins/tabs/tab_images/end_tab.gif\">";
		}
		
		var evalStr = "if (typeof(parent."+tabPage.id+"_"+tabcode+")!=\"undefined\"){ parent."+
		tabPage.id+"_"+tabcode+".style.visibility=\"hidden\";parent."+
		tabPage.id+"_"+tabcode+".style.position=\"absolute\";}"

		eval(evalStr);
		
		//在tab的父对象中插入层,这里层上级对象的id一定叫CPISContent，否则不能调用
		//在层中插入框架
		var divName = tableName + "_" + (i+1);
		var newFrame = objDiv.document.createElement("<iframe id=" + divName + "_1 src='" + tmpURL + "' scrolling='no' frameborder=0 style=\"PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 100%; BORDER-TOP-STYLE: none; PADDING-TOP: 0px; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; POSITION: static; HEIGHT: 100%; BORDER-BOTTOM-STYLE: none\"></iframe>");
					
		var freObject = objDiv.document.all(divName);
		if (freObject != null)
			freObject.insertBefore(newFrame);
				
	}
	
	//定义最后图标
	cell=row.insertCell();
	cell.width="100%";
	//cell.background="/internet/dl_images/tab_blank.gif";
	
	//将第一页激活
	setActiveTabIndex(tabPage, 0);
}

/// <summary>
/// 激活相应的Tab页对象
/// </summary>
/// <param name="table" type="Object">table对象</param>
/// <param name="index" type="int">选中的cell索引</param> 
function setActiveTabIndex(table, index)
{
	for(var i=0; i<table.cells.length; i++){
		if (table.cells[i].tab_index==index)
		{
			_setActiveTab(table.cells[i]);
			break;
		}
	}
}
	
/// <summary>
/// 得到单元格的行
/// </summary>
/// <param name="cell" type="Object">表格中的cell对象</param>
/// <returns>返回cell所在的行对象</returns>
function getRowByCell(cell)
{
	return cell.parentElement;
}

/// <summary>
/// 得到行的Table对象
/// </summary>
/// <param name="row" type="Object">表格中的 row对象</param>	
/// <returns>返回row所在的Table对象</returns>
function getTableByRow(row)
{
	var tbody=row.parentElement;
	if (tbody) return tbody.parentElement;
}

/// <summary>
/// 将cell激活
/// </summary>
/// <param name="cell" type="Object">表格中的cell对象</param>	
function _setActiveTab(cell)
{
	try
	{
		var row=getRowByCell(cell);
		var tabpage=getTableByRow(row);
		var selectCell=tabpage.selectTab;
		if (selectCell==cell) return;
		
		var oldCode=(selectCell)?selectCell.tab_code:"";
		var newCode=cell.tab_code;
		if (selectCell)
		{
			var prevCell=row.cells[selectCell.cellIndex-1];
			var nextCell=row.cells[selectCell.cellIndex+1];
			selectCell.background=contextPath+"/common/skins/tabs/tab_images/tab_button.gif";
			if (prevCell.firstcell)
				prevCell.firstChild.src=contextPath+"/common/skins/tabs/tab_images/start_tab.gif";
			else
				prevCell.firstChild.src=contextPath+"/common/skins/tabs/tab_images/tab.gif";
		
			if (nextCell.lastcell)
				nextCell.firstChild.src=contextPath+"/common/skins/tabs/tab_images/end_tab.gif";
			else
				nextCell.firstChild.src=contextPath+"/common/skins/tabs/tab_images/tab.gif";
			selectCell.style.color="#FFFFFF";
			eval("if (typeof(parent."+tabpage.id+"_"+oldCode+")!=\"undefined\") parent."+tabpage.id+"_"+oldCode+".style.visibility=\"hidden\"");
		}

		var prevCell=row.cells[cell.cellIndex-1];
		var nextCell=row.cells[cell.cellIndex+1];
		cell.background=contextPath+"/common/skins/tabs/tab_images/active_tab_button.gif";
		cell.style.color="#000000";
		if (prevCell.firstcell)
			prevCell.firstChild.src=contextPath+"/common/skins/tabs/tab_images/active_start_tab.gif";
		else
			prevCell.firstChild.src=contextPath+"/common/skins/tabs/tab_images/active_tab1.gif";
		if (nextCell.lastcell)
			nextCell.firstChild.src=contextPath+"/common/skins/tabs/tab_images/active_end_tab.gif";
		else
			nextCell.firstChild.src=contextPath+"/common/skins/tabs/tab_images/active_tab2.gif";
		eval("if (typeof(parent."+tabpage.id+"_"+newCode+")!=\"undefined\") parent."+tabpage.id+"_"+newCode+".style.visibility=\"\"");
		tabpage.selectTab=cell;
		tabpage.selectCode=cell.tab_code;
		tabpage.selectIndex=cell.tab_index;
		
		//var objDesc = window.parent.self.document.all("Description");		 ///给出页面的注释部分
		//if (objDesc != null)
		//{
		//	objDesc.innerText = "  " + cell.tab_GUID;
		//}
		
		self.parent.BindPliot();			///调用父页面的邦定导航条
		}
	catch(e)
	{
		//processException(e);
	}
}

/// <summary>
/// 点击Tab页事件
/// </summary>	
function _tabpage_onclick(){
	_setActiveTab(event.srcElement.tab);
}

//双击Tab页事件
function _tabpage_ondblclick(){
	
	var tableName = "tabSub";
	var objDiv = window.parent;
	if (objDiv){
		_deletePage(tableName, objDiv);
	}	
}
/// <summary>
/// 当鼠标移到时事件
/// </summary>
function _tabpage_onmouseover(){
	if (event.srcElement.parentElement.style.color != "#000000")
		event.srcElement.parentElement.style.color="#000000";
	//event.srcElement.style.textDecorationUnderline=true;
}

/// <summary>
/// 鼠标移走时事件
/// </summary>
function _tabpage_onmouseout(){
	if (event.srcElement.parentElement.style.color != "#ffffff")
		event.srcElement.parentElement.style.color="#ffffff";
	//style=\"FONT-WEIGHT: bold; FONT-SIZE: 9pt; COLOR: captiontext; FONT-FAMILY: 宋体; TEXT-ALIGN: center\"
	//event.srcElement.style.textDecorationUnderline=false;
}

/// <summary>
/// 删除当前选中的tab页对象
/// </summary>
/// <param name="tableName" type="string">Tab表格的id</param>
/// <param name="objDiv" type="Object">Tab页层所在父对象</param>
function _deletePage(tableName, objDiv)
{
    try
    {
		var tabPage = document.all(tableName);
		var selectCell = tabPage.selectTab;
		var row=getRowByCell(selectCell);
		var curIndex = selectCell.cellIndex;
		var prevCell=row.cells[selectCell.cellIndex-1];
		var nextCell=row.cells[selectCell.cellIndex+1];
		var tabcode = selectCell.tab_code;
		if (prevCell.firstcell && nextCell.lastcell)
		{
			//window.alert("最后一页，不能删除");
			_currentOpen(tableName, "bodymain.jsp", '主页', objDiv)
			return;
		}
		if(prevCell.firstcell)									 //删除Tab标题页中的内容
		{
			var nextNode = row.cells[selectCell.cellIndex+2];
			if (nextNode == null) return;
			if (nextNode.tab_index == null) return;
			setActiveTabIndex(tabPage, nextNode.tab_index);
			row.cells[0].firstcell=true;
			row.cells[0].innerHTML="<img src=\""+contextPath+"/common/skins/tabs/tab_images/active_start_tab.gif\">";
			row.deleteCell(curIndex);
			row.deleteCell(curIndex);
		}
		else if(nextCell.lastcell)
		{
			var prevNode = row.cells[selectCell.cellIndex-2];
			if (prevNode == null) return;
			if (prevNode.tab_index == null) return;
			setActiveTabIndex(tabPage, prevNode.tab_index);
			row.cells[curIndex+1].lastcell=true;
			row.cells[curIndex+1].innerHTML="<img src=\""+contextPath+"/common/skins/tabs/tab_images/active_end_tab.gif\">"
			row.deleteCell(curIndex-1);
			row.deleteCell(curIndex-1);
		}
		else
		{
			var prevNode = row.cells[selectCell.cellIndex-2];
			if (prevNode == null) return;
			if (prevNode.tab_index == null) return;
			setActiveTabIndex(tabPage, prevNode.tab_index);
			row.deleteCell(curIndex);
			row.deleteCell(curIndex);
		}
		//alert(tableName + "_" + tabcode);
		if(objDiv)
			objDiv.document.all(tableName + "_" + tabcode).removeNode(true);	///删除相应的层	
	}
	catch(e)
	{
		//
	}
}

/// <summary>
/// 删除除主页外的所有的tab页对象
/// </summary>
/// <param name="tableName" type="string">Tab表格的id</param>
/// <param name="objDiv" type="Object">Tab页层所在父对象</param>
function _deleteAllPage(tableName, objDiv)
{
    try
    {
		var tabPage = document.all(tableName);
		var selectCell = tabPage.selectTab;
		var row=getRowByCell(selectCell);
		var curIndex = selectCell.cellIndex;
		var prevCell=row.cells[selectCell.cellIndex-1];
		var nextCell=row.cells[selectCell.cellIndex+1];
		var tabcode = selectCell.tab_code;
		if (prevCell.firstcell && nextCell.lastcell)
		{
			//window.alert("最后一页，不能删除");
			_currentOpen(tableName, "bodymain.jsp", '主页', objDiv)
			return;
		}
		
		var mm=row.cells.length;
		mm=mm-2;
		if(mm>1)mm=mm/2;
		for(var i=0;i<mm;i++){
			
			_deletePage(tableName, objDiv);
		}
				
	}
	catch(e)
	{
		//
	}
}
/// <summary>
/// 新打开一个页对象
/// </summary>
/// <param name="tableName" type="string">Tab页所在表格的id</param>
/// <param name="objID" type="number">页面ID</param>
/// <param name="objTitle" type="string">Tab页的标题字符串</param>
/// <param name="objDiv" type="Object">Tab所在层的父对象</param>
/// <param name="objParam" type="string">打开新页的QueryString</param>
function _newOpen(tableName, objID, objTitle, objDiv, objParam)
{
	var tabPage = document.all(tableName);
	var j = tabPage.rows[0].cells.length;
	var row = tabPage.rows[0];
	var lcell = row.cells[j-3];
	var tmpObj = new Object();
	//tmpObj = window.parent.ADSearch(objID);					///查找对象
	if (tmpObj != null)
	{
		label = objTitle;
		tmpURL = objID;				//tmpURL = tmpObj;
		if (objParam)
		{
			if (tmpURL.indexOf("?") < 0)
				tmpURL = tmpURL + "?" + objParam;
			else
				tmpURL = tmpURL + "&" + objParam;
		}
	}
	else
	{
		alert(objTitle + "所在页面还没有注册！");
		return;
	}
	row.deleteCell(j-1);
	row.deleteCell(j-2);
	tabcode = lcell.tab_code + 1;
	var divName = tableName + "_" + tabcode;					/////在父页面上插入层
	var newDiv = objDiv.document.createElement("<div id='" + divName + "' style=\"PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 100%; BORDER-TOP-STYLE: none; PADDING-TOP: 0px; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; POSITION: static; HEIGHT: 100%; BORDER-BOTTOM-STYLE: none\"></div>");
	var newObject = objDiv.document.all("CPISContent");
	newObject.insertBefore(newDiv);
	cell=row.insertCell();
	cell.innerHTML="<img src=\""+contextPath+"/common/skins/tabs/tab_images/tab.gif\">";
	cell=row.insertCell();
	cell.background=contextPath+"/common/skins/tabs/tab_images/tab_button.gif";
	cell.tab_index = tabcode;
	cell.tab_code=tabcode;
	cell.tab_GUID=objID;
	cell.tab_Param=objParam;

	btn=document.createElement("<DIV hideFocus=true nowrap class=tabpagebtn style=\"CURSOR: hand; FONT-SIZE: 9pt; FONT-FAMILY: 宋体; TEXT-ALIGN: center\"></DIV>");
	btn.innerText=label;
	btn.tabIndex=-1;
	btn.id=tabcode;	
	btn.tab_GUID=objID;	
	btn.onclick=_tabpage_onclick;
	btn.ondblclick=_tabpage_ondblclick;
	btn.onmouseover=_tabpage_onmouseover;
	btn.onmouseout=_tabpage_onmouseout;
	btn.tab=cell;
	cell.appendChild(btn);
	cell=row.insertCell();
	cell.lastcell=true;
	cell.innerHTML="<img src=\""+contextPath+"/common/skins/tabs/tab_images/end_tab.gif\">";
	
	var evalStr = "if (typeof(parent."+tabPage.id+"_"+tabcode+")!=\"undefined\"){ parent."+
		tabPage.id+"_"+tabcode+".style.visibility=\"hidden\";parent."+
		tabPage.id+"_"+tabcode+".style.position=\"absolute\";}"
	eval(evalStr);			
	cell=row.insertCell();
	cell.width="100%";
	//cell.background="/internet/dl_images/tab_blank.gif";
	
	
	var divName = tableName + "_" + tabcode;
	var newFrame = objDiv.document.createElement("<iframe id=" + divName + "_1 name=" + divName + "_1 src='" + tmpURL + "' frameborder=0 scrolling='no' style=\"PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 100%; BORDER-TOP-STYLE: none; PADDING-TOP: 0px; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; POSITION: static; HEIGHT: 100%; BORDER-BOTTOM-STYLE: none\"></iframe>");	
	var freObject = objDiv.document.all(divName);
	freObject.insertBefore(newFrame);
	setActiveTabIndex(tabPage, tabcode);
	//alert(divName);
}

function otherOpenAAA(){

	alert("0000");
}

/// <summary>
/// 新打开一个带有审核信息的页对象
/// </summary>
/// <param name="tableName" type="string">Tab页所在表格的id</param>
/// <param name="objID" type="number">页面ID</param>
/// <param name="objTitle" type="string">Tab页的标题字符串</param>
/// <param name="objDiv" type="Object">Tab所在层的父对象</param>
/// <param name="objParam" type="string">打开新页的QueryString</param>
/// <param name="instId" type="string">流程实例ID</param>
function _newOpenInfo(tableName, objID, objTitle, objDiv, objParam, infoConditions)
{
	var tabPage = document.all(tableName);
	var j = tabPage.rows[0].cells.length;
	var row = tabPage.rows[0];
	var lcell = row.cells[j-3];
	var tmpObj = new Object();
	//tmpObj = window.parent.ADSearch(objID);					///查找对象
	if (tmpObj != null)
	{
		label = objTitle;
		tmpURL = objID;				//tmpURL = tmpObj;
		if (objParam)
		{
			if (tmpURL.indexOf("?") < 0)
				tmpURL = tmpURL + "?" + objParam;
			else
				tmpURL = tmpURL + "&" + objParam;
		}
	}
	else
	{
		alert(objTitle + "所在页面还没有注册！");
		return;
	}
	row.deleteCell(j-1);
	row.deleteCell(j-2);
	tabcode = lcell.tab_code + 1;
	var divName = tableName + "_" + tabcode;					/////在父页面上插入层
	var newDiv = objDiv.document.createElement("<div id='" + divName + "' style=\"PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 100%; BORDER-TOP-STYLE: none; PADDING-TOP: 0px; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; POSITION: static; HEIGHT: 100%; BORDER-BOTTOM-STYLE: none\"></div>");
	var newObject = objDiv.document.all("CPISContent");
	newObject.insertBefore(newDiv);
	cell=row.insertCell();
	cell.innerHTML="<img src=\""+contextPath+"/common/skins/tabs/tab_images/tab.gif\">";
	cell=row.insertCell();
	cell.background=contextPath+"/common/skins/tabs/tab_images/tab_button.gif";
	cell.tab_index = tabcode;
	cell.tab_code=tabcode;
	cell.tab_GUID=objID;
	cell.tab_Param=objParam;

	btn=document.createElement("<DIV hideFocus=true nowrap class=tabpagebtn style=\"CURSOR: hand; FONT-SIZE: 9pt; FONT-FAMILY: 宋体; TEXT-ALIGN: center\"></DIV>");
	btn.innerText=label;
	btn.tabIndex=-1;
	btn.id=tabcode;	
	btn.tab_GUID=objID;	
	btn.onclick=_tabpage_onclick;
	btn.ondblclick=_tabpage_ondblclick;
	btn.onmouseover=_tabpage_onmouseover;
	btn.onmouseout=_tabpage_onmouseout;
	btn.tab=cell;
	cell.appendChild(btn);
	cell=row.insertCell();
	cell.lastcell=true;
	cell.innerHTML="<img src=\""+contextPath+"/common/skins/tabs/tab_images/end_tab.gif\">";
	
	var evalStr = "if (typeof(parent."+tabPage.id+"_"+tabcode+")!=\"undefined\"){ parent."+
		tabPage.id+"_"+tabcode+".style.visibility=\"hidden\";parent."+
		tabPage.id+"_"+tabcode+".style.position=\"absolute\";}"
	eval(evalStr);			
	cell=row.insertCell();
	cell.width="100%";
	//cell.background="/internet/dl_images/tab_blank.gif";
	
	
	var divName = tableName + "_" + tabcode;
	//var newFrame = objDiv.document.createElement("<iframe id=" + divName + "_1 src='frmFrame.aspx?url=" + escape(tmpURL) + "&conditions=" + infoConditions + "' frameborder=0 style=\"PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 100%; BORDER-TOP-STYLE: none; PADDING-TOP: 0px; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; POSITION: static; HEIGHT: 100%; BORDER-BOTTOM-STYLE: none\"></iframe>");
	var newFrame = objDiv.document.createElement("<iframe id=" + divName + "_1 src='' frameborder=0 style=\"PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 100%; BORDER-TOP-STYLE: none; PADDING-TOP: 0px; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; POSITION: static; HEIGHT: 100%; BORDER-BOTTOM-STYLE: none\"></iframe>");
	var freObject = objDiv.document.all(divName);
	freObject.insertBefore(newFrame);
	setActiveTabIndex(tabPage, tabcode);
}

/// <summary>
/// 在当前页打开一个页对象
/// </summary>
/// <param name="tableName" type="string">Tab页所在表格的id</param>
/// <param name="objID" type="number">页面ID</param>
/// <param name="objTitle" type="string">Tab页的标题字符串</param>
/// <param name="objDiv" type="Object">Tab所在层的父对象</param>
/// <param name="objParam" type="string">打开新页的QueryString</param>
function _currentOpen(tableName, objID, objTitle, objDiv, objParam)
{
	var tabPage = document.all(tableName);
	var selectCell = tabPage.selectTab;
	var tmpObj = new Object();
	//tmpObj = window.parent.ADSearch(objID);
	if (tmpObj != null)
	{
		label = objTitle;
		tmpURL = objID;				//tmpURL = tmpObj;
		if (objParam)
		{
			if (tmpURL.indexOf("?") < 0)
				tmpURL = tmpURL + "?" + objParam;
			else
				tmpURL = tmpURL + "&" + objParam;
		}
	}
	else
	{
		alert(objTitle + "所在页面还没有注册！");
		return;
	}
	var divObject = document.all((selectCell.tab_code).toString());
	if (divObject != null)
	{
		divObject.innerText = label;
		selectCell.tab_GUID = objID;
		selectCell.tab_Param = objParam;
	}
	//var selectIndex = tableName + "_" + (selectCell.tab_index + 1) + "_1";
	var selectIndex = tableName + "_" + (selectCell.tab_code) + "_1";
	var currentObj = objDiv.document.all(selectIndex);
	if(currentObj != null)
	{
		currentObj.src = tmpURL;	
	}
	
	//var objDesc = window.parent.self.document.all("Description");		 ///给出页面的注释部分
	//if (objDesc != null)
	//{
	//	objDesc.innerText = "  " + objTitle;
	//}
}

/// <summary>
/// 新打开一个页对象(对外接口)
/// </summary>
/// <param name="tableName" type="string">Tab页所在表格的id</param>
/// <param name="objID" type="number">页面ID</param>
/// <param name="objTitle" type="string">Tab页的标题字符串</param>
/// <param name="objDiv" type="Object">Tab所在层的父对象</param>
/// <param name="objParam" type="string">打开新页的QueryString</param>
function newOpen(tableName, objID, objTitle, objDiv, objParam)
{
	if (tableName == "")
	{
		tableName = "tabSub";
	}
	if (objTitle == "")
	{
		tabName = "出错.系统";
	}
	if(objDiv == null)
	{
		window.frameElement;
	}
	_newOpen(tableName, objID, objTitle, objDiv, objParam);
}

/// <summary>
/// 在当前页打开一个页对象(对外接口)
/// </summary>
/// <param name="tableName" type="string">Tab页所在表格的id</param>
/// <param name="objID" type="number">页面ID</param>
/// <param name="objTitle" type="string">Tab页的标题字符串</param>
/// <param name="objDiv" type="Object">Tab所在层的父对象</param>
/// <param name="objParam" type="string">打开新页的QueryString</param>
function currentOpen(tableName, objID, objTitle, objDiv, objParam)
{
	if (tableName == "")
	{
		tableName = "tabSub";
	}
	if (objTitle == "")
	{
		tabName = "出错.系统";
	}
	if(objDiv == null)
	{
		window.frameElement;
	}
	_currentOpen(tableName, objID, objTitle, objDiv, objParam);
}

/// <summary>
/// 如果存在在当前页打开，如果没有打开新的一页(对外接口)
/// </summary>
/// <param name="tableName" type="string">Tab页所在表格的id</param>
/// <param name="objID" type="number">页面ID</param>
/// <param name="objTitle" type="string">Tab页的标题字符串</param>
/// <param name="objDiv" type="Object">Tab所在层的父对象</param>
/// <param name="objParam" type="string">打开新页的QueryString</param>
function otherOpen(tableName, objID, objTitle, objDiv, objParam)
{
	//判断是否存在这样标题的页，如果存在切换到信息页,不存在在新页中打开；（采用统一的名字编码才不会重复）
	if (tableName == "")
	{
		tableName = "tabSub";
	}
	if (objTitle == "")
	{
		tableName = "出错.系统";
	}
	if (objDiv == null)
	{
		window.frameElement;
	}
	var table = document.all(tableName);
	for(var i=0; i<table.cells.length; i++){
	
		if (table.cells[i].tab_GUID==objID && table.cells[i].tab_Param==objParam)
		{
			_setActiveTab(table.cells[i]);
			return;
		}
	}	
	_newOpen(tableName, objID, objTitle, objDiv, objParam);
}

/// <summary>
/// 如果存在在当前页打开，如果没有打开新的带有审核信息的页(对外接口)
/// </summary>
/// <param name="tableName" type="string">Tab页所在表格的id</param>
/// <param name="objID" type="number">页面ID</param>
/// <param name="objTitle" type="string">Tab页的标题字符串</param>
/// <param name="objDiv" type="Object">Tab所在层的父对象</param>
/// <param name="objParam" type="string">打开新页的QueryString</param>
/// <param name="instId" type="string">流程实例ID</param>
function otherOpenInfo(tableName, objID, objTitle, objDiv, objParam, infoConditions)
{
	//判断是否存在这样标题的页，如果存在切换到信息页,不存在在新页中打开；（采用统一的名字编码才不会重复）
	if (tableName == "")
	{
		tableName = "tabSub";
	}
	if (objTitle == "")
	{
		tableName = "出错.系统";
	}
	if (objDiv == null)
	{
		window.frameElement;
	}
	var table = document.all(tableName);
	for(var i=0; i<table.cells.length; i++){
	
		if (table.cells[i].tab_GUID==objID && table.cells[i].tab_Param==objParam)
		{
			_setActiveTab(table.cells[i]);
			return;
		}
	}
	_newOpenInfo(tableName, objID, objTitle, objDiv, objParam, infoConditions);
}
