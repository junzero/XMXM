<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery EasyUI</title>
	<%@ include file="/common.jsp" %>
	<script type="text/javascript">
		jQuery(function(){
			jQuery('#tt').tree({
				onClick:function(node){
					jQuery('#tt').tree('beginEdit', node.target);
				}
			});
		});
	</script>
</head>
<body>
	<h1>Editable Tree</h1>
	<div style="margin-bottom:10px">
		Click the node to begin edit, press enter key to stop edit and esc key to cancel edit.
	</div>
	<ul id="tt" url="tree_data.jsp" animate="true"></ul>
</body>
</html>