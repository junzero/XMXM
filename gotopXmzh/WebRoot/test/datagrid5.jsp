<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html style="height:100%;width:100%;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/common.jsp" %>
		<script type="text/javascript" src="<%=cbasePath%>/js/test/jquery.datagrid.js"></script>
		<title>jQuery EasyUI grid</title>
	<script>
		jQuery(function(){
			jQuery('#tt').datagrid({
				url: 'datagrid_data3.jsp',
				title: 'DataGrid',
				width: 600,
				height: 220,
				fitColumns: true,
				nowrap:false,
				rownumbers:true,
				showFooter:true,
				columns:[[
					{field:'itemid',title:'Item ID',width:80},
					{field:'productid',title:'Product ID',width:100},
					{field:'listprice',title:'List Price',width:80,align:'right'},
					{field:'unitcost',title:'Unit Cost',width:80,align:'right'},
					{field:'attr1',title:'Attribute',width:150},
					{field:'status',title:'Status',width:60,align:'center'}
				]]
			});
		});
	</script>
</head>
<body>
	<h1>DataGrid - Footer Row</h1>
	
	<table id="tt"></table>
</body>
</html>