<%@taglib uri="http://eos.primeton.com/tags/report" prefix="report"%>
<%@include file="/common.jsp"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.primeton.report.client.api.*" language="java"%>
<%
	String stylepath = "/common/skins/"+_headskin+"/";
	String application_deletePngStr = stylepath+"images/application_delete.png";
	String application_homePngStr = stylepath+"images/application_home.png";
	String menuPngStr = stylepath+"images/menu.png";
	String menubgGifStr = contextPathStr+"/common/skins/default/images/menubg.gif";
%>
<html>
<head>
<title>Title</title>
<style type="text/css">
<!--
A.ssmItems:link		{color:black;text-decoration:none;}
A.ssmItems:hover	{color:black;text-decoration:none;}
A.ssmItems:active	{color:black;text-decoration:none;}
A.ssmItems:visited	{color:black;text-decoration:none;}
.RC_TREE_ACTIVENODE { /*选中节点的风格*/
	vertical-align: middle;
	FONT-SIZE: 10pt;
	font-weight:bold;
	COLOR: #000080;
	height: 18px;
	WHITE-SPACE: nowrap;
	PADDING-LEFT: 2px;
	PADDING-TOP: 2px;
	background-color: #DEF8FE;
	cursor: pointer;
	border: 1px solid #C1D6E4;
	margin-Top: -1px;
	margin-Bottom: -1px;
}
//-->
</style>
<h:script src="/common/skins/default/scripts/ssm.js" />
<%--<h:script src="org.gocom.abframe.auth.Frame.flow?_eosFlowAction=quickmenu" />--%>
<script>
	var temp=1;
	function menuListRefresh(node){
	  if(/[Yy]/.test(node.getProperty("isleaf"))&&node.getProperty("funcaction")==null){
	  node.setIcon("<%=application_deletePngStr %>");
	  }
	 }
	function menuClick(node){
	  var target = 'bodyFrame';
	  var url = node.getProperty("funcaction");
	  var isleaf = node.getProperty("isleaf");
	  var parainfo = node.getProperty('parainfo');
	  if(isleaf=='y' && url!=null){
	     parent.frames[target].location.href = padContextpath(url,parainfo);
	  }else{
	  	node.expandNode();
	  }
	}
	function padContextpath(url,parainfo){
	  var headexpr = new RegExp("^/");
	  var tailexpr = new RegExp("\\.jsp(\\?.*){0,1}$");
	  var root = "<%=contextPathStr%>";
	  if(root=="/") root="";
	  if(parainfo){
	  	if(url.indexOf("?")>=0){
	  		url += "&"+parainfo;
	  	}else{
	  		url += "?"+parainfo;
	  	}
	  }
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
<script type="text/javascript">
function _stree_linkToParnet(O, D, B) {
	var P = D.getLength(), N = D.values, H = B.getLength(), C = B.values, E = O.entityName, J = O.parnetFeild, K = O.entityField, G = J
			.split(","), A = K.split(",");
			
	if (G.length == 1) {
		for (var I = 0; I < P; I++) {
			var F = N[I].getProperty(K);
			var Fstr = N[I].getProperty("funcname");
			for (var M = 0; M < H; M++) {
				var L = C[M].getProperty(J);
				var Lstr = C[M].getProperty("funcname");
				if (L!=undefined && F!=undefined && L == F) {
					N[I].type = E;
					if (C[M].childList == null)
						C[M].childList = new Array();
					C[M].childList.push(N[I]);
					break
				}
			}
		}
	} else
		for (I = 0; I < P; I++) {
			F = N[I].getProperty(K);
			for (M = 0; M < H; M++) {
				L = C[M].getProperty(J);
				if (L!=undefined && F!=undefined && L == F) {
					N[I].type = E;
					if (C[M].childList == null)
						C[M].childList = new Array();
					C[M].childList.push(N[I]);
					break
				}
			}
		}
}
function init(){
	var menuFrame = window.parent.document.getElementById("bodyFrame");
	var homefun = menuFrame.contentWindow
	if(homefun.goHomeFun){
		homefun.goHomeFun();
	}
	unMaskTop();
}
eventManager.add(window, "load", init);
function initTopParam(){
	return "<roleStrings>${requestScope.roleStrings}</roleStrings>";
}
function treeNodeRefresh(node){//    
   //设为叶子节点
   if(node.getProperty("isleaf")=="y"){
   	   node.setIcon("/common/skins/default/images/building.gif");
	   node.setLeaf();
   }
}
</script>
</head>
<body topmargin="0" background="<%=menubgGifStr %>" onkeydown="if(event.keyCode==9){ event.keyCode=13;};return false;">
<r:rtree id="tree1">
	<r:treeRoot icon="<%=application_homePngStr %>"
		action="org.gocom.abframe.auth.PermissionManager.getRolefuncTopid.biz"
		childEntities="menus" display="应用菜单" initParamFunc="initTopParam" />
	<r:treeNode nodeType="menus" submitXpath="acfuncgroup" showField="funcname" icon="/common/skins/default/images/menu.gif"
		childEntities="menus" initParamFunc="initTopParam" onRefreshFunc="treeNodeRefresh" onClickFunc="menuClick"
		action="org.gocom.abframe.auth.PermissionManager.getRolefuncByGroupid.biz"/>
</r:rtree>

</body>
</html>