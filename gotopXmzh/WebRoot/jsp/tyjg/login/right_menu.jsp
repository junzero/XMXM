<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
<style type="text/css">
.tab {
	border-collapse: collapse;
	border: none;
	margin: 0;
}

.left {
	background:url("/common/images/funicons/skins/default/lefttop.png");
}

.right {
	background:url("/common/images/funicons/skins/default/righttop.png");
}

.centerleft {
	background:url("/common/images/funicons/skins/default/line.png") repeat-y left;
	margin: 20;
}

.centerright {
	background:url("/common/images/funicons/skins/default/line.png") repeat-y right;
	
}

.downleft {
	background:url("/common/images/funicons/skins/default/leftbottom.png");
}

.downright {
	background:url("/common/images/funicons/skins/default/rightbottom.png");
}

.center {
	background:url("/common/images/funicons/skins/default/line.png") repeat-x top;
}

.centerdown {
	background: url("/common/images/funicons/skins/default/line.png") repeat-x bottom;
}

.menuleft {
	background: url("/common/images/funicons/skins/default/leftmenu.png") no-repeat bottom left;
	width: 10px;
	height: 25px;
}

.menucenter {
	background: url("/common/images/funicons/skins/default/middle.png") repeat-x center;
}

.menuright {
	background: url("/common/images/funicons/skins/default/rightmenu.png") no-repeat bottom right;
	width: 10px;
	height: 25px;
}

a {
	font-family: 微软雅黑;
}

body{
			/* background-image: url("/common/skins/default/images/sideline.gif"); */
}
</style>
<script>
	function  link(src){location.href=src;}
	function showTab(action,name){window.top.portalUtil.showWindowOnTab(action,name);}
	function display(objimg,i,url){if (i==1){objimg.src=url;objimg.className="imgcssonmouseover";}else{objimg.src=url;objimg.className="imgcss";}}
	function textdisplay(std,i){if (i==1){std.className="txtcssonmouseover";}else{std.className="txtcss";}}
  function js_openpage(wname,url,openMode){if(openMode==0){var newwin=window.open(url,"superwindowname","toolbar=no,resizable=yes,location=no,directories=no,status=no,menubar=no,scrollbars=yes,top="+(screen.availHeight-768)/2+",left="+(screen.availWidth-1024)/2+",width=1024,height=768");newwin.focus();}else if(openMode==1){window.top.portalUtil.showWindowOnTab(url,wname);}else if(openMode==2){ window.top.portalUtil.showWindowOnSameTab(url,wname);}}
</script> 
<title>菜单</title>
</head>
<body style="margin: 6pt 4pt 5pt 4pt;">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td  height="25" width="25">&nbsp;&nbsp;&nbsp;</td>
			<td >&nbsp;</td>
			<td  height="25" width="25">&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td >&nbsp;</td>
			<td align="left">
				<table width="85%" height="100%" border="0" cellpadding="0" cellspacing="0">	
				  <tr>
					<l:iterate id="id4" property="childMenuList" indexId="index">
     						<td align="center" width="150">
								<table>
									<tr>
										<td align="center">
											<l:empty property="FUNICON" iterateId="id4">
												<l:equal property="ISLEAF" iterateId="id4" targetValue="y"> 
													<img src="/common/images/funicons/default.png" onclick="link('<b:write property="FUNCACTION" iterateId="id4"/>');" width="100" height="100">
												</l:equal>
												<l:equal property="ISLEAF" iterateId="id4" targetValue="n">
													<img src="/common/images/funicons/default.png" onclick="link('/login/getFuncGroupInfo_login.action?acFunctionGroup.funcGroupId=<b:write property="FUNCCODE" iterateId="id4"/>&acFunctionGroup.groupLevel=<b:write property="GROUPLEVEL" iterateId="id4"/>');" width="100" height="100">
												</l:equal>
											</l:empty> 
											<l:notEmpty property="FUNICON" iterateId="id4">
												<l:equal property="ISLEAF" iterateId="id4" targetValue="y"> 
													<img src='/common/images/funicons/<b:write property="FUNICON" iterateId="id4"/>' onclick="link('<b:write property="FUNCACTION" iterateId="id4"/>');"  width="100" height="100">
												</l:equal>
												<l:equal property="ISLEAF" iterateId="id4" targetValue="n">
													<img src='/common/images/funicons/<b:write property="FUNICON" iterateId="id4"/>' onclick="link('/login/getFuncGroupInfo_login.action?acFunctionGroup.funcGroupId=<b:write property="FUNCCODE" iterateId="id4"/>&acFunctionGroup.groupLevel=<b:write property="GROUPLEVEL" iterateId="id4"/>');"  width="100" height="100">
												</l:equal>
											</l:notEmpty>
										</td>
									</tr>
									<tr>
										<td align="center">
										    <l:equal property="ISLEAF" iterateId="id4" targetValue="y"> 
												<a style="font-size:15px" href="javascript:link('<b:write property="FUNCACTION" iterateId="id4"/>');"><b:write property="FUNCNAME" iterateId="id4" /></a>
											</l:equal>
											<l:equal property="ISLEAF" iterateId="id4" targetValue="n">
												<a style="font-size:15px" href="javascript:link('/login/getFuncGroupInfo_login.action?acFunctionGroup.funcGroupId=<b:write property="FUNCCODE" iterateId="id4"/>&acFunctionGroup.groupLevel=<b:write property="GROUPLEVEL" iterateId="id4"/>');"><b:write property="FUNCNAME" iterateId="id4" /></a>
											</l:equal>
										</td>
									</tr>
								</table>
							</td>
					   <l:modeEqual property="index"  divisor="4" remainder="0" scope="p">
					      </tr>
					      <tr>
					    </l:modeEqual>						
					</l:iterate>
					</tr>
				</table>
			</td>
			<td >&nbsp;</td>
		</tr>
		<tr>
			<td height="25" width="25">&nbsp;</td>
			<td >&nbsp;</td>
			<td height="25" width="25">&nbsp;</td>
		</tr>
	</table>
</body>
</html>