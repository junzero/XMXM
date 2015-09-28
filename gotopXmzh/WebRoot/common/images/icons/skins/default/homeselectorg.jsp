<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>
<h:script src="/common/gotop/showModal_patch.js"></h:script>
<html>
<!-- 
  - Author(s): lz
  - Date: 2010-05-31 14:50:49
  - Description:
-->
<head>
<title>Title</title>
</head>
<script type="text/javascript">
	var temp=0;
	var orgid="";
	window.onload=function(){
		maskTop();
		loadFun();
	}
	function callBack(value){
		var url = "org.gocom.abframe.auth.Frame.flow";
		if(value){
			var valueArra = (""+value).split(",");
			url += "?param/orgid="+valueArra[0];
			var menuFrame = window.parent.document.getElementById("toolbarFrame");
			var mfurl = contextPath+"/common/skins/default/toolbar.jsp?orgname="+encodeURI(valueArra[1]);
			menuFrame.contentWindow.location.href=mfurl;			
			
			orgid = valueArra[0];
		}else{
			url += "?param/orgid=&param/gvtxdm=&param/bc=";
		}
		var menuFrame = window.parent.document.getElementById("menuFrame");
		menuFrame.contentWindow.location.href=url;

	}
	function loadFun(){
		try{
			var s = 0;
			<l:notEmpty property="userIntegrated/userOrgs" scope="s">
				s = '<b:size property="userIntegrated/userOrgs" scope="s"/>';
			</l:notEmpty>
			if(s<1){
				alert("您没有任何可登录机构!");
				callBack();
				return;
			}else if(s==1){
				return callBack('<b:write property="userIntegrated/userOrgs[1]/orgid" scope="s"/>,<b:write property="userIntegrated/userOrgs[1]/orgname" scope="s"/>');
			}
			var url = "com.primeton.purview.loginOrganSelect.flow?time="+new Date().getTime();
			if(temp==0){
				temp = 1;
				var dialogArguments = [];
				dialogArguments.maximizeButton=false;
				dialogArguments.closeButton=false;
				showModalCenter(url, dialogArguments, callBack, 740, 310, '选择登录机构');
			}
		}catch(e){
//			alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
			temp=0;
			window.setTimeout("loadFun()",5000);
		}
	}
	function showModalCallBack(windowNow,windowObj,name){
		try{
			var g = windowNow.$id("group1");
			orgid = g.getParam("organization/orgid");
			orgname = g.getParam("organization/orgname");
			callBack(orgid+","+orgname);
		}catch(e){}
	}
	function goHomeFun(){
		window.location.href="com.gotop.cmms.home.homeManage.flow";
	}
</script>
<body>
</body>
</html>