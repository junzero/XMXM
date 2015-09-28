<%@include file="/common.jsp"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
function menuListRefresh(node){
  if(/[Yy]/.test(node.getProperty("isleaf"))&&node.getProperty("menuaction")==null){
  node.setIcon("<%=application_deletePngStr %>");
  }
 }
 
function menuClick(node){
  var target = 'bodyFrame';
  var name = node.getProperty("menuname");
  var url = node.getProperty("menuaction");
  if(url!=null){
  	window.parent.frames[target].window.otherOpen(url,name, '');
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
</head>
<body topmargin="0" background="<%=menubgGifStr %>">
	<w:tree id="menutree">
	    <w:treeRoot display="应用菜单" icon='<%=application_homePngStr %>' ></w:treeRoot>
	    <w:treeNode nodeType="menu" showField="menulabel" icon='<%=menuPngStr %>' xpath="menulist" onRefreshFunc="menuListRefresh" onClickFunc="menuClick" >
	        <w:treeRelation parentNodeType="root" field="menulevel" value="1" />
	        <w:treeRelation parentNodeType="menu" field="PARENTSID" parentField="menuid"   />
	    </w:treeNode>
	</w:tree>
</body>
</html>