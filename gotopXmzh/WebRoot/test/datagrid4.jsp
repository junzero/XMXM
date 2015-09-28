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
				url: 'datagrid_data2.jsp',
				title: 'DataGrid',
				width: 600,
				height: 300,
				fitColumns: true,
				nowrap:false,
				columns:[[
					{field:'itemid',title:'Item ID',width:80},
					{field:'productid',title:'Product ID',width:100},
					{field:'listprice',title:'List Price',width:80,align:'right',
						styler:function(value,row,index){
							if (value < 20){
								return 'background-color:#ffee00;color:red;';
							}
						}
					},
					{field:'unitcost',title:'Unit Cost',width:80,align:'right'},
					{field:'attr1',title:'Attribute',width:150},
					{field:'status',title:'Status',width:60,align:'center'}
				]],
				rowStyler:function(index,row,css){
					if (row.listprice>80){
						return 'background-color:#6293BB;color:#fff;font-weight:bold;';
					}
				}
			});
		});
	</script>
</head>
<body>
	<h1>DataGrid - Custom Row Style</h1>
	
	<table id="tt"></table>
</body>
</html>