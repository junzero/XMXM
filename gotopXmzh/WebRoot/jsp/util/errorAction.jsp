<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common.jsp"%>
<%@page import="com.gotop.util.exception.GotopException"%>
<html>
<body>
<table align="center" border="0" width="100%" class="form_table">
<tr>
<td>
<font style="color: red;">
系统异常，请联系系统开发商(公司：福建国通信息科技有限公司，tel：0591-83325858-8000)！点击下面的按钮可查看详细信息！
</font>
</td>
</tr>
</table>
<p>
<%
	Object eobj =request.getAttribute("_exception");
	if(eobj instanceof GotopException) {
		GotopException gen = (GotopException) eobj;
		String message = gen.getMessage();
		message = message.replace("\n"," ").replace("'","\\'");
		ForUtil.outObjLn(out,"<script type='text/javascript'>alert('"+message+"');");
		if(gen.getForward()!=1){
			ForUtil.outObjLn(out,"window.history.go(-1);");
		}
		ForUtil.outObjLn(out,"</script>");
	}else{
%>
<w:panel id="e" title="详细信息" expand="false" >
<h:exception showStacktrace="true"/>
</w:panel>
<%}%>
<style>
.over
{
text-decoration:underline;
}
</style>
<script>
var td=$id('stack');
var table=$id('table');
var div=$createElement("div");
div.style.position="absolute";
var img=$createElement("img");
img.src=contextPath+"/common/skins/skin0/images/basic/reverse.png"
img.style.cursor="pointer";
img.title="reverse";
div.appendChild(img);
document.body.appendChild(div);
var pos = getPosition( td );
div.style.top=pos.top;
div.style.left=pos.left+td.offsetWidth-32;

function reverse()
{
var length=td.childNodes.length
for(var i=length-1;i>=0;i--)
{
td.appendChild(td.childNodes[i]);
}
}

function showline()
{
addClass(this,"over");
}

function hideline()
{
removeClass(this,"over")
}


//var length=td.childNodes.length

//for(var i=length-1;i>=0;i--)
//{
//td.appendChild(td.childNodes[i]);
//}
img.onclick=reverse;

//var length=td.childNodes.length
/*
for(var i=length-1;i>=0;i--)
{
td.childNodes[i].onmouseover=showline;
td.childNodes[i].onmouseout=hideline;
alert(td.childNodes[i].tagName)
td.childNodes[i].style.cursor="hand"
}
*/

window.onload= function(){ 
try{
unMaskTop(); 
}
catch(e)
{
}
}
</script>
</body>
</html>
