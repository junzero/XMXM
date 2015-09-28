<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
  	<a href="##" onclick="aaaa('/tree/initMainTree_mainTree.action?changeTree.startOrgid=304&changeTree.topID=3&changeTree.showTabOrg=1&changeTree.orgType=6');">金融模块树</a>
  	</br>
  	</br>
  	<a href="#" onclick="aaaa('/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.showTabGroup=1&changeTree.showTabRole=1&changeTree.orgType=6&changeTree.showSelBox=9');">机构、角色、群组</a>
  	</br></br>
  	<a href="#" onclick="aaaa('/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2');">行政树下选人</a>
  	</br></br>
  	<a href="#" onclick="aaaa('/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=4&changeTree.showSelBox=4');">仅选机构弹出树</a>
  	</br></br>
  	<a href="#" onclick="aaaa('/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.startOrgid=304&changeTree.orgType=4&changeTree.checkcount=1');">带参数选人弹出树</a>
  	<br/></br>
  	<h:hidden property="treeEmpid"/>
  	<h:hidden property="treeEmpname"/>
  	<h:hidden property="treeOrgid"/>
  	<h:hidden property="treeOrgname"/>
  	<h:hidden property="treeGroupid"/>
  	<h:hidden property="treeGroupname"/>
  	<h:hidden property="treeRoleid"/>
  	<h:hidden property="treeRoleName"/>
  	选中的人员：<span id="emp"></span>
  		<br/>
  	选中的机构：<span id="org"></span>
  		<br/>
  	选择的群组：<span id="group"></span>
  	</br>
  	选择的业务角色：<span id="role"></span>
  	<script type="text/javascript">
  		function aaaa(strUrl){
			var peArgument = [];
			var paramEntity = new ParamEntity('Employee');
			paramEntity.setProperty('empid',$name("treeEmpid").value);			
			paramEntity.setProperty('empname',$name("treeEmpname").value);
			peArgument[0]=[paramEntity,'empname','empid'];
			//机构
			var paramEntity = new ParamEntity('Organization');
			paramEntity.setProperty('orgid',$name("treeOrgid").value);
			paramEntity.setProperty('orgname',$name("treeOrgname").value);
			peArgument[1]=[paramEntity,'orgname','orgid'];
		//群组
		
		    var paramEntity = new ParamEntity('Group'); //oaGroup：群组类型
			paramEntity.setProperty('groupid',$name("treeGroupid").value);
			paramEntity.setProperty('groupname',$name("treeGroupname").value);
			peArgument[2]=[paramEntity,'groupname','groupid'];
			
		    var paramEntity = new ParamEntity('Role'); //oaGroup：群组类型
			paramEntity.setProperty('roleid',$name("treeRoleid").value);
			paramEntity.setProperty('rolename',$name("treeRoleName").value);
			peArgument[3]=[paramEntity,'rolename','roleid'];
			
			strUrl += "&time="+new Date().getTime();
			showModalCenter(strUrl,peArgument,changeCallBack,550,430,'选择使用范围');
	  		}
	  		
	  		function changeCallBack(arg){
	  			if(arg['Employee']){ //原写法无需判断是否为空
		  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
		  		argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("empid"));
					argRes[1].push(sorgidArra[i].getProperty("empname"));
				}
				$name("treeEmpid").value = argRes[0];
				$name("treeEmpname").value = argRes[1];
				$id("emp").innerText =argRes[1];
	    	}else{
				$name("treeEmpid").value = "";
				$name("treeEmpname").value = "";
				$id("emp").innerText = "";
	    	}
	    	
	    	if(arg['Organization']){ //原写法无需判断是否为空
		  		var sorgidArra  = arg['Organization'].slice(0);//人员数组
		  		argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("orgid"));
					argRes[1].push(sorgidArra[i].getProperty("orgname"));
				}
				$name("treeOrgid").value = argRes[0];
				$name("treeOrgname").value = argRes[1];
				$id("org").innerText = argRes[1];
	    	}else{
				$name("treeOrgid").value = "";
				$name("treeOrgname").value = "";
				$id("org").innerText = "";
	    	}
	    	if(arg['Group']){ //原写法无需判断是否为空
		  		var sorgidArra  = arg['Group'].slice(0);//人员数组
		  		argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("groupid"));
					argRes[1].push(sorgidArra[i].getProperty("groupname"));
				}
				$name("treeGroupid").value = argRes[0];
				$name("treeGroupname").value = argRes[1];
				$id("group").innerText = argRes[1];
	    	}else{
				$name("treeGroupid").value = "";
				$name("treeGroupname").value = "";
				$id("group").innerText = "";
	    	}
	    	if(arg['Role']){ //原写法无需判断是否为空
		  		var sorgidArra  = arg['Role'].slice(0);//人员数组
		  		argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArra.length;i++){
					argRes[0].push(sorgidArra[i].getProperty("roleid"));
					argRes[1].push(sorgidArra[i].getProperty("rolename"));
				}
				$name("treeRoleid").value = argRes[0];
				$name("treeRoleName").value = argRes[1];
				$id("role").innerText = argRes[1];
	    	}else{
				$name("treeRoleid").value = "";
				$name("treeRoleName").value = "";
				$id("role").innerText = "";
	    	}
  		}
  	</script>
  </body>
</html>
