<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;width:100%;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/common.jsp" %>
		<script type="text/javascript" src="<%=cbasePath%>/js/test/jquery.tabs.js"></script>
		<title>jQuery EasyUI tabs</title>
	<script type="text/javascript">
		jQuery(function(){
			jQuery('#tt').tabs({
				tools:[{
					iconCls:'icon-add',
					handler: function(){
						alert('add');
					}
				},{
					iconCls:'icon-save',
					handler: function(){
						alert('save');
					}
				}]
			});
		});
		
		var index = 0;
		function addTab(){
			index++;
			jQuery('#tt').tabs('add',{
				title:'New Tab ' + index,
				content:'Tab Body ' + index,
				iconCls:'icon-save',
				closable:true
			});
		}
		function getSelected(){
			var tab = jQuery('#tt').tabs('getSelected');
			alert('Selected: '+tab.panel('options').title);
		}
		function update(){
			index++;
			var tab = jQuery('#tt').tabs('getSelected');
			jQuery('#tt').tabs('update', {
				tab: tab,
				options:{
					title:'new title'+index,
					iconCls:'icon-save'
				}
			});
		}
	</script>
</head>
<body>
	<h1>Tabs</h1>
	<div>
		<a class="easyui-linkbutton" icon="icon-add" href="javascript:void(0)" onclick="addTab()">add tab</a>
		<a class="easyui-linkbutton" href="javascript:void(0)" onclick="getSelected()">getSelected</a>
		<a class="easyui-linkbutton" href="javascript:void(0)" onclick="update()">Update</a>
	</div>
	<br/>
	
	<div id="tt" style="width:500px;height:250px;">
		<div title="Tab1" style="padding:20px;">
		</div>
		<div title="Tab2" closable="true" style="padding:20px;" cache="false" href="tabs_href_test.html">
			This is Tab2 with close button.
		</div>
		<div title="Tab3" iconCls="icon-reload" closable="true" style="padding:20px;">
			<table id="test" class="easyui-datagrid" fit="true">
				<thead>
					<tr>
						<th field="f1" width="60">field1</th>
						<th field="f2" width="60">field2</th>
						<th field="f3" width="60">field3</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>d1</td>
						<td>d2</td>
						<td>d3</td>
					</tr>
					<tr>
						<td>d11</td>
						<td>d21</td>
						<td>d31</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div title="Tab4 with iframe" closable="true">
			<iframe scrolling="yes" frameborder="0"  src="http://www.google.com" style="width:100%;height:100%;"></iframe>
		</div>
		<div title="Tab5 with sub tabs" closable="true" iconCls="icon-cut" style="padding:10px;">
			<div class="easyui-tabs" fit="true" plain="true" style="height:100px;width:300px;">
				<div title="Title1" style="padding:10px;">Content 1</div>
				<div title="Title2" style="padding:10px;">Content 2</div>
				<div title="Title3" style="padding:10px;">Content 3</div>
			</div>
		</div>
	</div>
</body>
</html>