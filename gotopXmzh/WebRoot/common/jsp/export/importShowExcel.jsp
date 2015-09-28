<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="com.eos.web.taglib.util.XpathUtil"%>
<html>
<!-- 
  - Author(s): lz
  - Date: 2009-10-06 20:17:18
  - Description:
-->
<head>
<title>Title</title>
</head>
<script type="text/javascript">
<%
	String opType="";
	
	Object obj = XpathUtil.getDataContextRoot("f", pageContext);
	HashMap<String,Object> hm = (HashMap<String,Object>)XpathUtil.getObjectByXpath(obj, "hm");
	if(hm==null){
		hm = new HashMap<String,Object>();
	}
	
	List<String> logError = (List<String>)hm.get("logErrorList");
	List<String> logInfo = (List<String>)hm.get("logInfoList");
	List<String> sqlL = (List<String>)hm.get("sqlList");
	
	if((logError==null || logError.size()<1) && sqlL!=null && sqlL.size()>0 ){
		opType = "''";
	}else{
		opType = "'disabled'";
	}
%>
	window.onload = function(){
		var btok = window.parent.$id("btok");
		if(btok!=null){
			btok.disabled=<%=opType%>
		}
		var url = "http://127.0.0.1:8083/eos-default/tools/downloadExcel.jsp";
		url += "?dsoFilePath="+$name("dsoFilePath").value;
		FramerControl1.Open(encodeURI(url),false, "Excel.Sheet");
	}
	
	/**
	* 执行导出功能
	*/
	function exportSql(){ 
	  //判断是否有空
		var frm = $name("submitForm");
		frm.action = "com.primeton.purview.importSimpleExcel.flow";
		frm.elements["_eosFlowAction"].value = "runExcel";
		frm.submit();
	}
	
	if (window.Event){
<%--		document.captureEvents(Event.MOUSEUP);  --%>
	}
	
	function nocontextmenu()  
	{ 
		event.cancelBubble = true 
		event.returnValue = false; 
		return false; 
	} 
	
	function norightclick(e)  
	{ 
		if (window.Event)  
		{ 
			try{
				if (e.which == 2 || e.which == 3){
					return false; 
				}
			}catch(e){return false}
		}else if (event.button == 2 || event.button == 3){ 
			event.cancelBubble = true 
			event.returnValue = false; 
			return false; 
		} 
	}
	function loadFun(){
		document.oncontextmenu = nocontextmenu; // for IE5+ 
		document.onmousedown = norightclick; // for all others
	}
	eventManager.add(window,"load",loadFun);
	
</script>
<body>
<form action="com.primeton.purview.importSimpleExcel.flow" name="submitForm" method="post" width="100%" >
	<input name="_eosFlowAction" id="_eosFlowAction" type="hidden" value="runExcel"/>
	<h:hidden property="dsoFilePath"/>
	<h:hidden property="purview"/>
	<noscript><iframe src=*.html></iframe></noscript>
	<w:panel id="logError" width="100%" title="异常信息">
		<textarea rows="5" cols="147"><%
				for(int i=0;logError!=null && i<logError.size();i++){
					out.println(logError.get(i));
				}
		%></textarea>
	</w:panel>
	<w:panel id="logInfo" width="100%" title="提示信息">
		<textarea rows="5" cols="147"><%
				for(int i=0;logInfo!=null && i<logInfo.size();i++){
					out.println(logInfo.get(i));
				}
		%></textarea>
	</w:panel>
	<w:panel id="sqlL" width="100%" title="SQL语句">
		<textarea rows="11" cols="147" style="width: 100%"><%
				for(int i=0;sqlL!=null && i<sqlL.size();i++){
					out.println(sqlL.get(i)+";");
				}
		%></textarea>
	</w:panel>
	<w:panel id="logExcel" width="100%" title="Excel信息">
		<div width="100%" height="500" webOfficeWidth="100%" webOfficeHeight="500"><script type="text/javascript" src="<%=request.getContextPath()%>/common/gotop/res.js"></script>
		</div>
	</w:panel>
</form>
</body>
</html>