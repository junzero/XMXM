<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.gotop.util.Global"%>
<%@page import="com.gotop.vo.system.MUOUserSession"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="/js/commonUtil.js"></script>
    <title>
      顶部工具条
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <%
  	MUOUserSession userSession = (MUOUserSession) session.getAttribute(Global.LOGON_USER_KEY);
  	HashMap<String,String> hmp = userSession.getLoginOrg();
  	int size = hmp.size();
	StringBuffer table = new StringBuffer(500);
	table.append("<table bgcolor='#FFFFFF' width='100%'>");
	Iterator iter = hmp.entrySet().iterator();
	while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
		Object key = entry.getKey();
		Object val = entry.getValue();
		table.append("<tr style='font-size:10pt;cursor:hand;height:20px;'>");
		table.append("<td width='100%' onmouseover=\\\"this.bgColor='#EECCEE'\\\" onmouseout=\\\"this.bgColor='#FFFFFF'\\\" onclick=\\\"selectLoginOrg('"+key+"');\\\">"+val+"</td>");
		table.append("</tr>");
	}
	table.append("</table>");
  %>
  <body class="bc4 w mt0 m0">
  <div class="h22 lh30 bc4 w mt0">
	  <span>
	  	<div id="loginDate" style="float: left;">
	  	</div>
	  	<div align="right" style="float: right;">
	  	    <img src="${pageContext.request.contextPath }/common/images/icons/arrow_refresh.png" width="16" height="16" border="0"/>
			<nobr><a href="#" onclick="ref();">刷新</a></nobr>
			<img src="${pageContext.request.contextPath }/common/images/icons/house.png" width="16" height="16" border="0"/>
			<nobr><a href="#" onclick="showIndex();">返回首页</a></nobr>
			<%if(size > 1){ %>
				<img src="${pageContext.request.contextPath }/common/images/icons/arrow_refresh.png" width="16" height="16" border="0"/>
				<nobr><a id='loginorg' href="#" onclick="showLoginOrg();">
				切换机构
				</a></nobr>
			<%} %>
			<img src="${pageContext.request.contextPath }/images/e_edit.gif" width="16" height="16" border="0"/>
			<nobr><a href="#" onClick="javascript:modify_emp();return false;">密码修改</a>&nbsp;&nbsp;</nobr>
			<img src="${pageContext.request.contextPath }/common/skins/default/images/relogin.gif" width="16" height="16" border="0"/>
			<a href="#" onclick="exitSystem();">注销</a>
			&nbsp;&nbsp;
		</div>
	  </span>
	</div>
  	<script type="text/javascript">
  	$(function(){
  	  var str="欢迎您：${sessionScope.login_user.orgname } ${sessionScope.login_user.empname} 登录日期:"+WEB.today()
  		$("#loginDate").text(str);
  	});
 			function modify_emp(){
 				
  				var bodyFrame = window.parent.document.getElementById("mainFrame");
  				bodyFrame.contentWindow.unMaskTop();
  				// var url = "org.gocom.abframe.org.EmployeeManager.flow?oPartyrole/partytype=employee&_eosFlowAction=baseinfo";
  				var url = "/jsp/tyjg/empMngr/modfily_password.jsp";
  				bodyFrame.contentWindow.showModalCenter(url, null, null, 250, 120, '密码修改');
  			}
  		function exitSystem(){
  			if(confirm("是否要退出系统？")){
  				var myAjax = new Ajax("/login/systemExit_login.action");
  				myAjax.submit();
  				var returnValue = myAjax.getValue("/root/data/falg");
  				window.top.location.href="/jsp/tyjg/login/login.jsp"
  			}
  		}
  		//刷新
  		function ref(){
  			window.top.frames["mainFrame"].location.reload();
  		}
  		//返回首页
  		function showIndex(){
  			window.top.frames["mainFrame"].location.href = "${pageContext.request.contextPath }/jsp/tyjg/login/rightmain.jsp";
  		}
  		
  		//切换机构
  		function showLoginOrg(){
  			 dymiScipt();
  			 var loginorg = document.getElementById("loginorg");
  			 var oRect = loginorg.getBoundingClientRect();   
		     var left = oRect.left-20;
		     var top = oRect.top+18;           
		     var SPAN=window.top.frames["mainFrame"].document.createElement("div");
		     SPAN.id = "LOGIN_ORG_SPAN";
		     SPAN.style.cssText = "opacity: 0.80; -moz-opacity: 0.80; filter:alpha(opacity=80);border-radius:10px;border-width: 1px;border:1px solid #B2D0EA;background-color:transparent;Z-INDEX: 12000000000000000000009; POSITION: absolute;border-color:#000000; width:180;height:;overflow:auto;left:"+(left-310)+";top:0;";
		     window.top.frames["mainFrame"].document.body.appendChild(SPAN);
		     var show_info = "<%=table.toString()%>";
		     SPAN.onmouseleave=function() {    
		        colseOrg();
		     }
		    SPAN.innerHTML = show_info;
  		}
  		 function colseOrg() {
     		var msgObj= window.top.frames["mainFrame"].document.getElementById("LOGIN_ORG_SPAN");
         	window.top.frames["mainFrame"].document.body.removeChild(msgObj);
 		}
 		function dymiScipt(){
 			var scriptBlock = window.top.frames["mainFrame"].document.createElement("script"); 
       		scriptBlock.type = "text/javascript"; 
       		var funcInfo = "function colseOrg(){var msgObj= window.top.frames['mainFrame'].document.getElementById('LOGIN_ORG_SPAN');window.top.frames['mainFrame'].document.body.removeChild(msgObj);};function selectLoginOrg(orgid){window.top.location.href='${pageContext.request.contextPath}/login/login_login.action?acOperator.userId=<%ForUtil.outObj(out,userSession.getEmpcode());%>&acOperator.password=<%ForUtil.outObj(out,userSession.getPassword());%>&loginType=changs&acOperator.orgid='+orgid;login.submit();colseOrg();}";
       		scriptBlock.text=funcInfo;
       		window.top.frames["mainFrame"].document.getElementsByTagName("head")[0].appendChild(scriptBlock); 
 		}
 		setTimeout(dymiScipt,2000);//延迟2秒的目的是为了让window.top.frames["mainFrame"]加载完毕在修改dom
  	</script>
  </body>
</html>
