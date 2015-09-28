<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>Title</title>
<script language="JavaScript">
var flag = false;
function shift_status()
{   var pframe = parent.document.getElementById("contentFrame");
    var img = document.getElementById("menuSwitch");
    var opencols = "200,10,*";
    var closecols="0,10,*";
	if(flag)
	{
		if(screen.width>1024)
			pframe.cols = opencols
		else if(screen.width>800)
			pframe.cols = opencols;
		else
			pframe.cols = opencols;
		img.src='images/slideopened.gif';
		img.title='隐藏';
	}
	else
	{
		pframe.cols = closecols;
		img.src='images/slideclosed.gif';
		img.title='显示';
	}

	flag = !flag;
}
</script>
</head>
<body onclick="shift_status()" leftmargin="0" topmargin="0" background="images/sideline.gif">
    <div style="Position : Absolute ;Left : 0px ;Top : 150px ;" ><img src="images/slideopened.gif" id="menuSwitch" title='隐藏' ></div>
</body>
</html>