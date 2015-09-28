<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html style="height:100%;width:100%;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common.jsp" %>
<script type="text/javascript" src="<%=cbasePath%>/js/test/combo.js"></script>
<script type="text/javascript" src="<%=cbasePath%>/js/test/combobox.js"></script>
<title>jQuery EasyUI comboselect</title>
<script type="text/javascript">
	function loadData(){
		jQuery('#cc').combobox({
			url:'http://127.0.0.1:8083/sign/combobox_data.json',//该文件内容在下面
			valueField:'id',
			textField:'text'
		});
	}
	function getValue(){
		var sai = jQuery('#dd').combobox('getThisFun');
		var sagi = jQuery('#cc').combobox('getValue');
		var datacombo = jQuery.data(sai, "combo");
		jQuery('#cc').combobox('select',3);
		
		
		var _18 = jQuery.data(sai, "combobox").options;
		
	}

	(function(jQuery) {
		jQuery.extend(jQuery.fn.combo.methods, {
	        getThisFun: function(jq) {
	        	return jq[0];
	        }
		});
	})(jQuery);

	function testFun(){
		jQuery("#ff").val("苹果");
		alert(jQuery("#ff").val());
		jQuery("#dd").combobox('showPanel');
	}
</script>
</head>
  <body>
<select id="cc" class="easyui-combobox" name="state" style="width:200px;" required="true">
	<option value="">==请选择==</option>
	<option value="0">苹果</option>
	<option value="1">香蕉</option>
	<option value="2">鸭梨</option>
	<option value="3">西瓜</option>
	<option value="4">芒果</option>
</select>

	<input class="easyui-combobox" id="dd"
			name="language"
			url="http://127.0.0.1:8083/sign/combobox_data.json" 
			valueField="id" 
			textField="text" 
			multiple="true" 
			panelHeight="auto">
			
	<input id="ff" type="text">

	<input type="button" onclick="alert(jQuery('#cc').attr('value'))">
	<input type="button" onclick="loadData()">
	<input type="button" onclick="getValue()">
	<input type="button" onclick="testFun()">
	
  </body>
</html>
