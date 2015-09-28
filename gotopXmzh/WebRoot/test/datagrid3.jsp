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
				title: 'DataGrid - ContextMenu',
				width: 600,
				height: 300,
				fitColumns: true,
				columns:[[
					{field:'itemid',title:'Item ID',width:80},
					{field:'productid',title:'Product ID',width:100},
					{field:'listprice',title:'List Price',width:80,align:'right'},
					{field:'unitcost',title:'Unit Cost',width:80,align:'right'},
					{field:'attr1',title:'Attribute',width:150},
					{field:'status',title:'Status',width:60,align:'center'}
				]],
				onHeaderContextMenu: function(e, field){
					e.preventDefault();
					if (!jQuery('#tmenu').length){
						createColumnMenu();
					}
					jQuery('#tmenu').menu('show', {
						left:e.pageX,
						top:e.pageY
					});
				}
			});
		});
		
		function createColumnMenu(){
			var tmenu = jQuery('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
			var fields = jQuery('#tt').datagrid('getColumnFields');
			for(var i=0; i<fields.length; i++){
				jQuery('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
			}
			tmenu.menu({
				onClick: function(item){
					if (item.iconCls=='icon-ok'){
						jQuery('#tt').datagrid('hideColumn', item.text);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-empty'
						});
					} else {
						jQuery('#tt').datagrid('showColumn', item.text);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-ok'
						});
					}
				}
			});
		}
	</script>
</head>
<body>
	<h1>DataGrid - ContextMenu</h1>
	<p>Right click the header of datagrid to show context menu.</p>
	
	<table id="tt"></table>
	
</body>
</html>