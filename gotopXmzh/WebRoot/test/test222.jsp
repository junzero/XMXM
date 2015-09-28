<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html style="height:100%;width:100%;">
	<head>
		<%@ include file="/common.jsp"%>
		<title>jQuery EasyUI tree</title>
		<script>
			$(function(){
				$(document).ready(function(){
				 $("#pdate").wrDate();
				 
				 $(".pdate").wrDate();
				 //$.wrDate($(".pdate")); //这样传jQuery对象也行，与上句效果相同
				});
				
				$.extend({
				 wrDate : function(objs) { //给jQuery增加一个新函数名称是wrDate，参数是1个或者一组jQuery对象
				  objs.each(function(i) { //遍历jQuery对象
				   var inputObj = $(this); //把dom对象this转化成jQuery对象
				   inputObj.datepicker(); //给每个jQuery对象实现日历控件
				  });
				 }
				});
				
				$.fn.extend({
				 wrDate : function() { //定义一个名称为wrDate的jQuery插件
				  $.wrDate(this); //调用之前自定义的jQuery函数
				 }
				});
			});
		</script>
	</head>
	<body>
		<input type="text" class="pdate" />
		<input type="text" class="pdate" />
		<input type="text" class="pdate" />
		<input type="text" id="pdate" />
	</body>
</html>
