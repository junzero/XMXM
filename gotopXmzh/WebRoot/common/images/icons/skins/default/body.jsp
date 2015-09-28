<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
<title>Title</title>
<h:script src="/common/gotop/eos-web.js" />
</head>
<script type="text/javascript">
	var temp=0;
//	window.onload=function(){
//		remFunc();
//	}
	eventManager.add(window, "load", remFunc);
	function callBack(value){
		var url = "org.gocom.abframe.auth.Frame.flow";
		if(value){
			var valueArra = (""+value).split(",");
			url += "?param/orgid="+valueArra[0];
			var menuFrame = window.parent.document.getElementById("toolbarFrame");
		}else{
			url += "?param/orgid=&param/gvtxdm=&param/bc=";
		}
		var menuFrame = window.parent.document.getElementById("menuFrame");
		menuFrame.contentWindow.location.href=url;
	}
	function loadFun(){
		try{
			var s = 0;
			<l:notEmpty property="userIntegrated/userOrgs" scope="s">
				s = '<b:size property="userIntegrated/userOrgs" scope="s"/>';
			</l:notEmpty>
			if(s<1){
				alert("您没有任何可登录机构!");
				callBack();
				return;
			}else if(s==1){
				return callBack('<b:write property="userOrgs[1]/orgid" scope="s"/>,<b:write property="userOrgs[1]/orgname" scope="s"/>');
			}
			var url = "com.primeton.purview.loginOrganSelect.flow?time="+new Date().getTime();
			if(temp==0){
				temp = 1;
				showModalCenter(url, "", callBack, 740, 310, '选择登录机构');
			}
		}catch(e){
//			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
			temp=0;
			window.setTimeout("loadFun()",5000);
		}
	}
	function showModalCallBack(windowNow,windowObj,name){
		var g = windowNow.$id("group1");
		orgid = g.getParam("organization/orgid");
		orgname = g.getParam("organization/orgname");
		callBack(orgid+","+orgname);
	}
	function remFunc(){
		try{
			var toolbarFrame = window.parent.document.getElementById("menuFrame");
			if(toolbarFrame.contentWindow.temp){
				toolbarFrame.contentWindow.temp=2;
			}else{
				loadFun();
				window.setTimeout("remFunc()",5000);
			}
		}catch(e){
//			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
			window.setTimeout("remFunc()",5000);
		}
	}
</script>
<body margin="0" leftmargin="0" topmargin="0" background="images/body.jpg">
	<form action="" name="form1" method="post">
		<input type="hidden" name="workitemID">
		<input type="hidden" name="showType">
<%--		<w:panel id="dbsy" width="100%" title="公告信息">
			<r:datacell entityType="com.primeton.purview.tools.AbfTDbsyb" height="170" id="cell1" queryAction="org.gocom.abframe.tools.OperlogManager.queryAbfTDbsyb.biz" width="1050" xpath="abfTdbsyb">
			  <r:toolbar location="bottom" tools="nav,pagesize,info"/>
			  <r:field fieldName="vDbsymc" label="公告事宜名称" width="120">
			  </r:field>
			  <r:field fieldName="vXtmc" label="系统名称" width="100">
			  </r:field>
			  <r:field fieldName="dDbrq" label="公告日期"  width="130" allowModify="false">
			    <w:date format="yyyy-MM-dd HH:mm:ss"/>
			  </r:field>
			  <r:field fieldName="vDbzy" label="公告摘要" width="300">
			  </r:field>
			  <r:field fieldName="vDbrbh" label="员工编号" width="120" >
			  </r:field>
			  <r:field fieldName="cYdbz" label="是否阅读标志"width="100" allowModify="false">
			  	<d:select dictTypeId="ABF_YESORNO"></d:select>
			  </r:field>
			  <r:field align="center" width="40" allowModify="true" allowResize="true" fieldName="filler" label="<%=operation %>" onRefreshFunc="dbsyShowOperation" >
			  </r:field>
			</r:datacell>
		</w:panel>
		<w:panel id="ggxx" width="100%" title="待办事宜">
			<r:datacell entityType="com.eos.workflow.data.WFWorkItem" height="250" id="cell2" queryAction="org.gocom.abframe.tools.OperlogManager.queryPersonWorkItems.biz" width="1050" xpath="workitems" >
			  <r:toolbar location="bottom" tools="nav,pagesize,info"/>
			  <r:field fieldName="processChName" label="流程名称" width="120">
			  </r:field>
			  <r:field fieldName="workItemName" label="当前环节" width="100">
			  </r:field>
			  <r:field fieldName="createTime" label="创建时间"  width="130" allowModify="false">
			    <w:date format="yyyy-MM-dd HH:mm:ss"/>
			  </r:field>
			  <r:field fieldName="workItemDesc" label="事宜摘要" width="300">
			  </r:field>
			  <r:field fieldName="participant" label="流程参与人" width="120">
			  </r:field>
			  <r:field fieldName="currentState" label="业务状态" width="100" allowModify="false" >
			    <d:select dictTypeId="WFDICT_WorkItemState" />
			  </r:field>
			  <r:field align="center" width="160" allowModify="true" allowResize="true" fieldName="filler" label="<%=operation %>" onRefreshFunc="ggxxShowOperation" >
			  </r:field>
			</r:datacell>
		</w:panel>--%>
		<w:panel id="dbsy" width="100%" title="公告信息">
			<r:datacell entityType="com.primeton.purview.tools.AbfTDbsyb" pageSize="5" height="170" id="cell1" queryAction="org.gocom.abframe.tools.OperlogManager.queryAbfTGgb.biz" width="1050" xpath="abftggb">
			  <r:toolbar location="bottom" tools="nav,pagesize,info"/>
			  <r:field fieldName="vGgbt" label="公告标题" width="120" onRefreshFunc="ggsyShowOperation">
			  </r:field>
			  <r:field fieldName="dFbsj" label="发布日期" width="120">
			  	<w:date format="yyyy-MM-dd HH:mm:ss"/>
			  </r:field>
			  <r:field fieldName="vGgxx" label="公告摘要" width="360">
			  </r:field>
			  <r:field fieldName="vFbr" label="发布人" width="120" >
			  </r:field>
			</r:datacell>
		</w:panel>
		<w:panel id="ggxx" width="100%" title="待办事宜">
			<r:datacell entityType="com.primeton.purview.tools.AbfTDbsyb" height="230" id="cell2" queryAction="org.gocom.abframe.tools.OperlogManager.queryAbfTDbsyb.biz" width="1050" xpath="abfTdbsyb">
			  <r:toolbar location="bottom" tools="nav,pagesize,info"/>
			  <r:field fieldName="vDbsymc" label="待办事宜名称" width="120" onRefreshFunc="dbsyShowOperation">
			  </r:field>
			  <r:field fieldName="dDbrq" label="待办日期"  width="120" allowModify="false">
			    <w:date format="yyyy-MM-dd HH:mm:ss"/>
			  </r:field>
			  <r:field fieldName="vDbzy" label="待办摘要" width="360">
			  </r:field>
			  <r:field fieldName="vDbrbh" label="发布人" width="120" >
			  </r:field>
			</r:datacell>
		</w:panel>
	</form>
<script type="text/javascript">

<%--	function dbsyPanel(value,entity,rowNo,cellNo){
		var tt = "<table align='center' border='0' width='100%'> <tr> <td  nowrap='nowrap' align='left' class='eos-panel-title'>公告信息</td><td nowrap='nowrap' align='right' class='eos-panel-title'>"
		tt = tt+ '<a href="org.gocom.abframe.dbsy.AbfTDbsybMaintain.flow?_eosFlowAction=query" target="_blank" class="eos-panel-title">更多……</a>';
		tt =tt+"</td></tr></table>";
	    $id("_dbsy_panel_table").rows[0].cells[0].getElementsByTagName("TABLE")[0].rows[0].cells[3].innerHTML = tt;
	}

	function ggxxPanel(value,entity,rowNo,cellNo){
		var tt = "<table align='center' border='0' width='100%'> <tr> <td  nowrap='nowrap' align='left' class='eos-panel-title'>待办事宜</td><td nowrap='nowrap' align='right' class='eos-panel-title'>"
		tt = tt+ '<a href="<%=request.getContextPath()%>/auth/dbsy/myTask.jsp" target="_blank" class="eos-panel-title">更多……</a>';
		tt =tt+"</td></tr></table>";
	    $id("_ggxx_panel_table").rows[0].cells[0].getElementsByTagName("TABLE")[0].rows[0].cells[3].innerHTML = tt;
	}--%>
	function dbsyPanel(value,entity,rowNo,cellNo){
		var tt = "<table align='center' border='0' width='100%'> <tr> <td  nowrap='nowrap' align='left' class='eos-panel-title'>公告信息</td><td nowrap='nowrap' align='right' class='eos-panel-title'>"
		tt = tt+'<a href="org.gocom.abframe.dbsy.AbfTGgbMaintain.flow?_eosFlowAction=query" target="_blank" class="eos-panel-title">更多……</a>';
		tt =tt+"</td></tr></table>";
	    $id("_dbsy_panel_table").rows[0].cells[0].getElementsByTagName("TABLE")[0].rows[0].cells[3].innerHTML = tt;
	}
	function ggxxPanel(value,entity,rowNo,cellNo){
		var tt = "<table align='center' border='0' width='100%'> <tr> <td  nowrap='nowrap' align='left' class='eos-panel-title'>待办事宜</td><td nowrap='nowrap' align='right' class='eos-panel-title'>"
		tt = tt+ '<a href="org.gocom.abframe.dbsy.AbfTDbsybMaintain.flow?_eosFlowAction=query" target="_blank" class="eos-panel-title">更多……</a>';
		tt =tt+"</td></tr></table>";
	    $id("_ggxx_panel_table").rows[0].cells[0].getElementsByTagName("TABLE")[0].rows[0].cells[3].innerHTML = tt;
	}	
	

	/*
	 * 在页面上显示编辑按钮
	 */
	function ggsyShowOperation(value,entity,rno,cno,datacell){
		return "<a href='#' onclick=ggsyButtion('"+entity.getProperty("iGgbh")+"')>"+value+"</a>"
//		return "<input style='margin-top:-4' type='button' class='button' value='查看' onclick=dbsyButtion('"+entity.getProperty("iDbsybh")+"') />";
	}
	/*
	 * 在页面上显示编辑按钮
	 */
	function dbsyShowOperation(value,entity,rno,cno,datacell){
		return "<a href='#' onclick=dbsyButtion('"+entity.getProperty("iDbsybh")+"')>"+value+"</a>"
//		return "<input style='margin-top:-4' type='button' class='button' value='查看' onclick=dbsyButtion('"+entity.getProperty("iDbsybh")+"') />";
	}
	
	function ggsyButtion(iDbsybh){
		var url = "org.gocom.abframe.dbsy.DBGGSYBodyOpenWin.flow?_eosFlowAction=ggsy&ystggb/iGgbh="+iDbsybh+"&_tm="+new Date().getTime();
		showModalCenter(url,"",dbsyRefreshFun,660, 450,"公告信息查看");
	}
	
	function dbsyButtion(iDbsybh){
		var url = "org.gocom.abframe.dbsy.DBGGSYBodyOpenWin.flow?_eosFlowAction=dbsy&abftdbsyb/iDbsybh="+iDbsybh+"&_tm="+new Date().getTime();
		showModalCenter(url,"",dbsyRefreshFun,600,300,"待办信息查看");
	}
	
	function dbsyRefreshFun(dataCellName){
		if(dataCellName && $id(dataCellName)){
			$id(dataCellName).reload(false);
		}
	}
	/*
	 * 在页面上显示编辑按钮
	 */
	function ggxxShowOperation(value,entity,rno,cno,datacell){
		var workItemID = entity.getProperty("workItemID");
		var title = "PI_"+entity.getProperty("processInstID")+"|AI_"+entity.getProperty("activityInstID")+"|WI_" + workItemID;
		var button = "<input style='margin-top:-4' type='button' title='"+title+"' class='button' value='进入流程' onclick=doExecute("+workItemID+") />";
		button += "<input style='margin-top:-4' type='button' class='button' value='查看流程图' onclick=showDetail("+workItemID+") />";
		return button;
	}

	function doExecute(id){
		var form1 = $name('form1');
		form1.action="org.gocom.abframe.dbsy.executeTask.flow?_eosFlowAction=action0";
	    form1.workitemID.value=id;
		form1.submit();
	}
	
	function showDetail(id){
		var form1 = $name('form1');
		form1.action="org.gocom.abframe.dbsy.watchTask.flow?_eosFlowAction=action0";
	    form1.workitemID.value=id;
	    form1.showType.value="exe";
		form1.submit();
	}
    eventManager.add(window,"load",dbsyPanel);
    eventManager.add(window,"load",ggxxPanel);

</script>
</body>
</html>