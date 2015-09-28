<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<html>
<!-- 
  - Author(s): lz
  - Date: 2009-05-06 14:39:06
  - Description:
-->
<head>
<title>Title</title>
<link href="/eos-default/css/style1/style-custom.css" type="text/css" rel="stylesheet" >
<h:css href="css/style1/style-custom.css"/>
<h:script src="/common/gotop/datacellHtml.js" /><!-- 引用导出函数 -->
</head>
<script type="text/javascript">
	function createDemoHTML(){
		var tableStr = "<table class=EOS_table width='560' align=center border=1><TBODY>";
		var excelHead = "<tr>";
		var starpage = window.parent.$id("Target").options;
	  	//[0]=text,[1]=value,[2]=width,[3]=colspan,[4]=rowspan
	  	for(var i=0;i<starpage.length;i++){
	  		excelHead += "<th title='"+starpage[i].value+"'";
	  		if(starpage[i].width){
	  			excelHead += "width='"+starpage[i].width+"'";
	  		}
	  		if(starpage[i].colspan){
	  			excelHead += "colspan='"+starpage[i].colspan+"'";
	  		}
	  		if(starpage[i].rowspan){
	  			excelHead += "rowspan='"+starpage[i].rowspan+"'";
	  		}
			excelHead += ">"+starpage[i].text+"</th>";
	  	}
	  	excelHead +="</tr>";
	  	var excelBody=$id("excelBody").value;
	  	
	  	tableStr += excelHead + excelBody;
	  	tableStr += "</TBODY></table>";
	  	
	  	$id('tableHTML').innerHTML = tableStr;
	}
	eventManager.add(window, "load", createDemoHTML);
</script>
<body>
	<form name="submitForm" id="submitForm" method="post">
		<h:textarea style="display: none;" id="excelBody" name="excelBody" property="excelBody"/>
		<table width="100%" border="1" cellpadding="0" cellspacing="1" bordercolor="#CECECE" style="word-wrap: break-word;word-break: break-all;border-collapse:collapse;" align="center">
			<tr>
				<td colspan="4" align="center">
					 <table border="0" style="" bordercolor="#111111" cellpadding="1" cellspacing="2">
						 <tr>
							<td id="tableHTML" valign="middle" align="center">
							</td>
						 </tr>
					 </table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>