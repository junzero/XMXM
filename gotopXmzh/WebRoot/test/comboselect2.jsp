	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<%
	String path = request.getContextPath();
	String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	
	<html style="height:100%;width:100%;">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/common.jsp" %>
	<script type="text/javascript" src="<%=cbasePath%>/js/test/jquery.combobox.js"></script>

		<script>
			
			function loadData() {
			
			    jQuery('#cc').combobox({
			
			        url: 'comboselect_main_data.jsp',
			        //该文件内容在下面
			
			        valueField: 'id',
			
			        textField: 'text'
			
			    });
			    jQuery('#su').combobox({
			
			        url: 'comboselect_sub_data.jsp',
			        //该文件内容在下面
			
			        valueField: 'id',
			
			        textField: 'text'
			
			    });
			
			}
			
			function setValue() {
			
			    jQuery('#cc').combobox('setValue', '2');
			
			}
			
			function getValue() {
			
			    var val = jQuery('#cc').combobox('getValue');
			
			    alert(val);
			
			}
			
			function disable() {
			
			    jQuery('#cc').combobox('disable');
			
			}
			
			function enable() {
			
			    jQuery('#cc').combobox('enable');
			
			}
			
			jQuery(function() {
			    jQuery('#cc').combobox({
			
			        width: 150,
			
			        listWidth: 150,
			
			        listHeight: 100,
			
			        valueField:'id',
			
			        textField:'text',
			
			        url:'comboselect_main_data.jsp',
			        
			        mode:'remote',
			
			        editable: false,
			        
			        onChange : function(record){
			        	jQuery("#su").combobox("reload","comboselect_sub_data.jsp?pmain="+jQuery(this).combobox("getValue"))
			        				 .combobox('clear');
			        }
			
			    });
			    jQuery('#su').combobox({
			
			        width: 150,
			
			        listWidth: 150,
			
			        listHeight: 100,
			
			        valueField:'id',
			
			        textField:'text',
			
			        url:'comboselect_sub_data.jsp',
			        
			        mode:'remote',
			
			        editable: false
			
			    });
			});
			
		</script>

	</head>

	<body>

		<h1>
			ComboBox
		</h1>

		<div style="margin-bottom: 10px;">
			<a href="#" onclick="loadData()">loadData</a>

			<a href="#" onclick="setValue()">setValue</a>
			<a href="#" onclick="getValue()">getValue</a>
			<a href="#" onclick="disable()">disable</a>

			<a href="#" onclick="enable()">enable</a>
		</div>


		<span>Options: </span>

		<input id="cc" name="dept" />
		
		<select id="su" name="su" required="true">
		</select>


	</body>

</html>

