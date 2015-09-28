<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title></title>
<style>
li {float:left; margin:10;font-size: 9pt;}
body{margin: 0;padding: 0;font-size: small;}
</style>
</head>
<script language="JavaScript">
var flag = false;
function shift_status()
{   
	var pframe = parent.document.getElementById("frameMain");
    var prowsArra = pframe.rows.split(",");;
    if(flag){
    	prowsArra[0]="70";
    }else{
    	prowsArra[0]="10";
    }
	pframe.rows = prowsArra.toString();
	flag = !flag;
}
</script>
<body background="<%=request.getContextPath()%>/images/main_2_bg.png" ondblclick="shift_status()" onkeydown="if(event.keyCode==9){ event.keyCode=13;};return false;">
<div style="float:left"><img src="<%=request.getContextPath()%>/common/skins/default/images/main_bkls.jpg"></img></div>
</body>
</html>
