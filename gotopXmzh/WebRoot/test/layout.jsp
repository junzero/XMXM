<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客服关系管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<%@ include file="/common.jsp" %>

<script>
$(document).ready(function(){
	$('#tt2').tree({
		checkbox: false,
		url: '<%=basePath%>menu/show',
		onClick:function(node){
			$(this).tree('toggle', node.target);
			
		}
	});
	$('#userTable').datagrid({
		title:'客户资料',
		iconCls:'icon-save',
		method:'post',
		width:650,
		height:555,
		nowrap: false,
		striped: true,
		collapsible:false,
		url: '<%=basePath%>customer/list',
		idField:'id',
		
		columns:[[
			{field:'ck',checkbox:true,width:2}, 
			{field:'name',title:'联系人',width:120},
			{field:'address',title:'地址',width:120},
			{field:'email',title:'Email',width:150},
			{field:'phone',title:'联系电话',width:150}
		]],
		pagination:true,
		pageSize:20,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('add')
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#userTable').datagrid('getSelected');
				if (row){
					var index = $('#userTable').datagrid('getRowIndex', row);
					$('#userTable').datagrid('deleteRow', index);
					//发送请求
				}
			}
		},'-',{
			id:'btnsave',
			text:'保存',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	
});
$(function(){
    $.extend($.fn.validatebox.defaults.rules,{
        mobile:{
            validator:function(value,param){
                return /^(13[0-9]|15[0|1|2|3|6|7|8|9]|18[6|8|9])\d{8}$/.test(value);
            },
            message:'请输入正确的11位手机号码.格式:13120002221'
        },
        postcode:{
        validator:function(value,param){
            return /^\d{6}$/.test(value);
        },
        message:'请输入正确的6位邮政编码'
       }
    });
    
})
function addTab(title, href){   
    var tt = $('#main-center');   
    	if (tt.tabs('exists', title)){   
    	tt.tabs('select', title);   
    } else {   
	    if (href){   
	    var content = '<iframe scrolling="yes" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>';   
	    } else {   
	    var content = '未实现';   
	    }   
	    tt.tabs('add',{   
	    title:title,   
	    closable:true,   
	    content:content   
	    });   
    }   
}  

</script>

<style type="text/css">

</style>
  </head>
  
<body class="easyui-layout">  
<div region="north" style="background:#fafafa;color:#5F7006;height:40px;">  
    <div style="font-size:16px;font-weight:bold;width:400px;padding:10px 0 0 10px;">CRM2011</div>  
</div>  
<div region="west" title="导航菜单11" split="true" style="width:150px;">  
    <!-- 树形菜单 -->
     <div style="margin-top:3px">
     <ul id="tt2"></ul>
     </div>
</div>  

<div region="center" style="overflow:auto;">  
    <div id="main-center" class="easyui-tabs" fit="true" border="true">  
        <div  title="首页" style="padding:20px;">  
	           <div style="padding:10;float:left;">
			   	<table id="userTable"></table>
			   </div>
			  <div style="width:280px;height:300px;float:left;margin-left:10px">
					<div id="p" class="easyui-panel" title="资讯" icon="icon-save" collapsible="true"  style="padding:10px;">
						<p>getRequestURI：<%ForUtil.outObj(out,request.getRequestURI());%></p>
						<p>getRequestURL：<%ForUtil.outObj(out,request.getRequestURL());%></p>
						<p>getServletPath：<%ForUtil.outObj(out,request.getServletPath());%></p>
						<p>getPathInfo：<%ForUtil.outObj(out,request.getPathInfo());%></p>
						<p>getQueryString：<%ForUtil.outObj(out,request.getQueryString());%></p>
						
					</div>
				</div>
         </div>
     </div>      
</div>  

  </body>
</html>
