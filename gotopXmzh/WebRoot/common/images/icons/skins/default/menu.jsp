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
//-->
</style>
<h:script src="/common/skins/default/scripts/ssm.js" />
<h:script src="org.gocom.abframe.auth.Frame.flow?_eosFlowAction=quickmenu" />
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
	  if(url!=null){
	     parent.frames[target].location.href = padContextpath(url);
	  }else{
	  	node.expandNode();
	  }
	}
	function padContextpath(url){
	  var headexpr = new RegExp("^/");
	  var tailexpr = new RegExp("\\.jsp(\\?.*){0,1}$");
	  var root = "<%=contextPathStr%>";
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
</script>
</head>
<body topmargin="0" background="<%=menubgGifStr %>">
<w:tree id="menutree">

    <w:treeRoot display="应用菜单" icon='<%=application_homePngStr %>' ></w:treeRoot>
    <w:treeNode nodeType="menu" showField="funcname" icon='<%=menuPngStr %>' xpath="menulist" onRefreshFunc="menuListRefresh" onClickFunc="menuClick" >
        <w:treeRelation parentNodeType="root" field="grouplevel" value="1" />
        <w:treeRelation parentNodeType="menu" field="parentgroup" parentField="groupid"/>
    </w:treeNode>
</w:tree>
</body>
</html>