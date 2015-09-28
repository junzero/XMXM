<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
    	厦门综合管理系统
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
		.txt_input{
		  	height:25px;
		  	width:140px;
		  	font-size:20px;
		  	background:#FFFFFF;
		  	border:1px solid #7fb8ed;
		  	color:#0c3569;
		}
	</style>
  </head>
  <body style="margin: 0px;">
  		<e:datasource name="acOperator" type="bean" path="com.gotop.tyjg.login.model.AcOperator"/>
  		<div align="center" style="margin-top: 150px;z-index: 1">
  			<img src="/common/skins/tabs/images/login.jpg" />
  		</div>
  		<div height="100%" width="100%" align="center" style="margin-top: -270;z-index: 1000;" >
	  		<h:form action="/login/login_login.action" method="post">
	  			<table id="logintable">
	  				<tr>
	  					<td>
	  						我的姓名
	  					</td>
	  					<td>
	  						<input id="loginname" name="acOperator.operatorName"  class="txt_input" style="padding-left:15px;
	  						background: url('/common/skins/tabs/common/images/icons/user.png') no-repeat;" 
	  						size="25" tabindex="1" autocomplete="false" htmlEscape="true" onKeyDown="nextTag()" value=""/>
	  						<h:hidden id="userid" property="acOperator.userId"/>
	  						<h:hidden id="orgid" property="acOperator.orgid"/>
	  					</td>
	  					<td rowspan="2">
	  						<input type="button" onclick="login()" id="check" name="check"  tabindex="3" style="width:60px;height:60px;border: none;background-image: url('/common/skins/tabs/images/summer.png');cursor: pointer"/>
	  					</td>
	  				</tr>
	  				<tr>
	  					<td>
	  						密  　 码
	  					</td>
	  					<td>
	  						<h:password id="pwd" property="acOperator.password" onkeydown="nextTag()"  tabindex="2" styleClass="txt_input" value="" style="padding-left:15px; 
	  						background: url('/common/skins/tabs/common/images/icons/password.png') no-repeat;"/>
	  					</td>
	  				</tr>
	  			</table>
	  		</h:form>
  		</div>
	<h:script src="/js/tyjg/login/login.js"/>
  </body>
</html>
