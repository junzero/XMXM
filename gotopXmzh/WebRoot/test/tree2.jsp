<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/common.jsp" %>
		<script type="text/javascript" src="<%=cbasePath%>/js/test/jquery.tree.js"></script>
		<title>jQuery EasyUI tree</title>
		<script type="text/javascript">
			jQuery(function(){
				jQuery('#tt').tree({
					onClick:function(node){
						alert(node.iconCls);
						alert(node.attributes);
						jQuery('#tt').tree('beginEdit', node.target);
					}
				});
				
			});
			
			function ffff(){
				var s = jQuery('#tt').tree('getSelected');
				alert(s.id);
				alert(s.text);
				alert(s.iconCls);
				alert(s.attributes);
				alert("--state-"+s.state);
				alert(s.checked);
				alert("--children--"+s.children);
				
				var children = jQuery('#tt2').tree('getChildren', s.target);
				alert(children.length);
				var c = jQuery('#tt').tree('getChecked');
			}
		</script>
</head>
<body>
	<h1>Editable Tree</h1>
	<div style="margin-bottom:10px">
		Click the node to begin edit, press enter key to stop edit and esc key to cancel edit.
	</div>
	<ul id="tt" url="tree_data.jsp" animate="true" dnd="true" checkbox="true"></ul>
	
	<input type="button" onclick="ffff()"/>
	
	<br/>
	
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
</body>
</html>
