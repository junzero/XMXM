<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      密码修改
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  <h:form id="form1" name="form1" method="post" action="/empMngr/modifyPassword_empMngr.action" checkType="blur" onsubmit="return checkForm(this);">
  		<h:hidden property="empid" value="${sessionScope.login_user.empid}"/>
	  	<table align="center" border="0" width="100%" class="form_table">
	  		<tr>
	  			<td>新密码：</td>
	  			<td>
	  				<h:password property="newPassword" extAttr="" validateAttr="allowNull=false;"/>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td>确认新密码：</td>
	  			<td>
	  				<h:password property="surePassword" extAttr="" validateAttr="allowNull=false;"/>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td colspan="2" align="center">
	  				<input type="button" class="button" onclick="modifyPassword();" value="修改"/>
	  				<input type="button" class="button" onclick="javascript:window.closeD();" value="关闭"/>
	  			</td>
	  		</tr>
	  	</table>
  	</h:form>
  	<script type="text/javascript">
  		function modifyPassword(){
  			var newPass = $name("newPassword").value;
  			var surePass = $name("surePassword").value;
  			if(checkForm($id("form1"))){
	  			if(newPass != surePass){
	  				alert("新密码与确认密码不一致");
	  				return;
	  			}
	  			var myAjax = new Ajax("/empMngr/modifyPassword_empMngr.action");
	  			myAjax.submitForm($id("form1"));
	  			var rtun = myAjax.getValue("root/data/execFlag");
	  			if(rtun == "success"){
	  				alert("用户密码修改成功");
	  				window.closeD();
	  			}else{
	  				alert("用户密码修改失败");
	  			}
		  	}
  		}
  	</script>
  </body>
</html>
