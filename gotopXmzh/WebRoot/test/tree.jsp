<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(cbasePath);
%>

<html style="height:100%;width:100%;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/common.jsp" %>
		<script type="text/javascript" src="<%=cbasePath%>/js/test/jquery.tree.js"></script>
		<title>jQuery EasyUI tree</title>
	<script type="text/javascript">
		jQuery(function(){
			jQuery('#tt2').tree({
				checkbox: true,
				url: 'tree_data.jsp',
				onClick:function(node){
					jQuery(this).tree('toggle', node.target);
					alert('you dbclick '+node.text);
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					jQuery('#tt2').tree('select', node.target);
					jQuery('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				},
				cascadeCheck:true
			});
		});
		function reload(){
			var node = jQuery('#tt2').tree('getSelected');
			if (node){
				jQuery('#tt2').tree('reload', node.target);
			} else {
				jQuery('#tt2').tree('reload');
			}
		}
		function getChildren(){
			var node = jQuery('#tt2').tree('getSelected');
			if (node){
				var children = jQuery('#tt2').tree('getChildren', node.target);
			} else {
				var children = jQuery('#tt2').tree('getChildren');
			}
			var s = '';
			for(var i=0; i<children.length; i++){
				s += children[i].text + ',';
			}
			alert(s);
		}
		function getChecked(){
			var nodes = jQuery('#tt2').tree('getChecked');
			var s = '';
			for(var i=0; i<nodes.length; i++){
				if (s != '') s += ',';
				s += nodes[i].text;
			}
			alert(s);
		}
		function getSelected(){
			var node = jQuery('#tt2').tree('getSelected');
			alert(node.text);
		}
		function collapse(){
			var node = jQuery('#tt2').tree('getSelected');
			jQuery('#tt2').tree('collapse',node.target);
		}
		function expand(){
			var node = jQuery('#tt2').tree('getSelected');
			jQuery('#tt2').tree('expand',node.target);
		}
		function collapseAll(){
			var node = jQuery('#tt2').tree('getSelected');
			if (node){
				jQuery('#tt2').tree('collapseAll', node.target);
			} else {
				jQuery('#tt2').tree('collapseAll');
			}
		}
		function expandAll(){
			var node = jQuery('#tt2').tree('getSelected');
			if (node){
				jQuery('#tt2').tree('expandAll', node.target);
			} else {
				jQuery('#tt2').tree('expandAll');
			}
		}
		function append(){
			var node = jQuery('#tt2').tree('getSelected');
			jQuery('#tt2').tree('append',{
				parent: (node?node.target:null),
				data:[{
					text:'new1',
					checked:true
				},{
					text:'new2',
					state:'closed',
					children:[{
						text:'subnew1'
					},{
						text:'subnew2'
					}]
				}]
			});
		}
		function remove(){
			var node = jQuery('#tt2').tree('getSelected');
			jQuery('#tt2').tree('remove', node.target);
		}
		function update(){
			var node = jQuery('#tt2').tree('getSelected');
			if (node){
				node.text = '<span style="font-weight:bold">new text</span>';
				node.iconCls = 'icon-save';
				jQuery('#tt2').tree('update', node);
			}
		}
		function isLeaf(){
			var node = jQuery('#tt2').tree('getSelected');
			var b = jQuery('#tt2').tree('isLeaf', node.target);
			alert(b)
		}
	</script>
</head>
<body>
	<h1>Tree</h1>
	<p>Create from HTML markup</p>
	<ul id="tt1" class="easyui-tree" animate="true" dnd="true">
		<li>
			<span>Folder</span>
			<ul>
				<li state="closed">
					<span>Sub Folder 1</span>
					<ul>
						<li>
							<span><a href="#">File 11</a></span>
						</li>
						<li>
							<span>File 12</span>
						</li>
						<li>
							<span>File 13</span>
						</li>
					</ul>
				</li>
				<li>
					<span>File 2</span>
				</li>
				<li>
					<span>File 3</span>
				</li>
				<li>File 4</li>
				<li>File 5</li>
			</ul>
		</li>
		<li>
			<span>File21</span>
		</li>
	</ul>
	<hr></hr>
	<p>Create from JSON data</p>
	<div style="margin:10px;">
		<a href="#" onclick="reload()">reload</a>
		<a href="#" onclick="getChildren()">getChildren</a>
		<a href="#" onclick="getChecked()">getChecked</a>
		<a href="#" onclick="getSelected()">getSelected</a>
		<a href="#" onclick="collapse()">collapse</a>
		<a href="#" onclick="expand()">expand</a>
		<a href="#" onclick="collapseAll()">collapseAll</a>
		<a href="#" onclick="expandAll()">expandAll</a>
		<a href="#" onclick="append()">append</a>
		<a href="#" onclick="remove()">remove</a>
		<a href="#" onclick="update()">update</a>
		<a href="#" onclick="isLeaf()">isLeaf</a>
	</div>

	<ul id="tt2"></ul>
	
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="append()" iconCls="icon-add">Append</div>
		<div onclick="remove()" iconCls="icon-remove">Remove</div>
		<div class="menu-sep"></div>
		<div onclick="expand()">Expand</div>
		<div onclick="collapse()">Collapse</div>
	</div>
</body>
</html>
