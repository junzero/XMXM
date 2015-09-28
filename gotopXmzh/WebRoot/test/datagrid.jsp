<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html style="height:100%;width:100%;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/common.jsp" %>
		<script type="text/javascript" src="<%=cbasePath%>/js/test/jquery.datagrid.js"></script>
		<title>jQuery EasyUI grid</title>
	<script>
		jQuery(function(){
			jQuery('#test').datagrid({
				title:'My Title',
				iconCls:'icon-save',
				width:600,
				height:350,
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'datagrid_data.jsp',
				sortName: 'code',
				sortOrder: 'desc',
				remoteSort: false,
				idField:'code',
				frozenColumns:[[
	                {field:'ck',checkbox:true},
	                {title:'code',field:'code',width:80,sortable:true}
				]],
				columns:[[
			        {title:'Base Information',colspan:3},
					{field:'opt',title:'Operation',width:100,align:'center', rowspan:2,
						formatter:function(value,rec){
							return '<span style="color:red">Edit Delete</span>';
						}
					}
				],[
					{field:'name',title:'Name',width:120},
					{field:'addr',title:'Address',width:120,rowspan:2,sortable:true,
						sorter:function(a,b){
							return (a>b?1:-1);
						}
					},
					{field:'col4',title:'Col41',width:150,rowspan:2}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'Add',
					iconCls:'icon-add',
					handler:function(){
						jQuery('#btnsave').linkbutton('enable');
						alert('add')
					}
				},{
					id:'btncut',
					text:'Cut',
					iconCls:'icon-cut',
					handler:function(){
						jQuery('#btnsave').linkbutton('enable');
						alert('cut')
					}
				},'-',{
					id:'btnsave',
					text:'Save',
					disabled:true,
					iconCls:'icon-save',
					handler:function(){
						jQuery('#btnsave').linkbutton('disable');
						alert('save')
					}
				}]
			});
			var p = jQuery('#test').datagrid('getPager');
			if (p){
				jQuery(p).pagination({
					onBeforeRefresh:function(){
						alert('before refresh');
					}
				});
			}
		});
		function resize(){
			jQuery('#test').datagrid('resize', {
				width:700,
				height:400
			});
		}
		function getSelected(){
			var selected = jQuery('#test').datagrid('getSelected');
			if (selected){
				alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
			}
		}
		function getSelections(){
			var ids = [];
			var rows = jQuery('#test').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].code);
			}
			alert(ids.join(':'));
		}
		function clearSelections(){
			jQuery('#test').datagrid('clearSelections');
		}
		function selectRow(){
			jQuery('#test').datagrid('selectRow',2);
		}
		function selectRecord(){
			jQuery('#test').datagrid('selectRecord','002');
		}
		function unselectRow(){
			jQuery('#test').datagrid('unselectRow',2);
		}
		function mergeCells(){
			jQuery('#test').datagrid('mergeCells',{
				index:2,
				field:'addr',
				rowspan:2,
				colspan:2
			});
		}
	</script>
</head>
<body>
	<h1>DataGrid</h1>
	<div style="margin-bottom:10px;">
		<a href="#" onclick="resize()">resize</a>
		<a href="#" onclick="getSelected()">getSelected</a>
		<a href="#" onclick="getSelections()">getSelections</a>
		<a href="#" onclick="clearSelections()">clearSelections</a>
		<a href="#" onclick="selectRow()">selectRow</a>
		<a href="#" onclick="selectRecord()">selectRecord</a>
		<a href="#" onclick="unselectRow()">unselectRow</a>
		<a href="#" onclick="mergeCells()">mergeCells</a>
	</div>
	
	<table id="test"></table>
	
</body>
</html>