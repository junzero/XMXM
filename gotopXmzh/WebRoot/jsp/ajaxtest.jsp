<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.gotop.util.Global"%>
<%
	int flag = Global.DEFAULT_USER_UNUSED;
	request.setAttribute("flag", flag);
%>
<html>
	<head>
		<title>用户管理</title>
		<%@ include file="/common/meta.jsp"%>
</head>
<body>
<script type="text/javascript">
function User(userId, name) { 
	this.userId = userId; 
	this.name = name; 
}
function check(id){
	var user = JSON.stringify(new User(101,"阿猫")); 
	alert(user);
//	jQuery.post("/link/ajaxJsp.jsp", user);
	var urlStr = "/link/ajaxJsp.jsp";
	$.ajax({
	    type:"POST", 
	   url:urlStr, 
	   data:user, 
	   dataType: "jason",//此处要设置成jason 
	   success: callback});//回调函数 
}
function callback(jasonObj) 
{ 
	var str = jasonObj; 
    var obj = JSON.parse(str);//调用Json2.js中提供的JSON解析器来解析成JSONObject 
    alert(obj.errNum); 
}
</script>
<input type="button" onclick="check()" value="ch" />
</body>
</html>