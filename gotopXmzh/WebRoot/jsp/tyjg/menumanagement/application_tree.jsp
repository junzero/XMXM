<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      应用功能管理
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body style="margin: 0px;">
  	<%
	//获取标签中使用的国际化资源信息
	String appFunc_manager = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_title_appFunc_manager");
	String application_add = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_applicationAdd");
	String funcGroup_add = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_funcGroupAdd");
	String subfuncGroup_add = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_subFuncGroupAdd");
	String application_delete = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_appDelete");
	String funcGroup_delete = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_funcGroupDelete");
	String refresh = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_refresh");
	String addFunction = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_functionAdd");//新增功能
	%>
	<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
		<tr>       
			<td width="30%" height="100%" valign="top">
				<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
					<tr height="4%">
						<td class="eos-panel-title">&nbsp;&nbsp;<%=appFunc_manager%></td>
					</tr>
					<tr>
					<td width="25%" height="100%" valign="top" class="eos-panel-body">
					<r:rtree height="110%" id="DTree">
					  <r:treeRoot action="/menumanagement/menuManagementAction_queryAppRoot.action" 
					  childEntities="AcApplication" display="<%=appFunc_manager%>" 
					  onClickFunc="queryFunc" icon="/common/images/icons/root.png" >
					    <r:treeMenu>
					      <r:treeMenuItem display="<%=refresh%>" onClickFunc="refresh"/>
					    </r:treeMenu>
					  </r:treeRoot>
					  <r:treeNode nodeType="AcApplication" showField="appName"
					  action="/menumanagement/menuManagementAction_queryMenuGroup.action" 
					  submitXpath="acApplication" childEntities="AcFunctionGroup"  onClickFunc="queryApp"
					  icon="/common/images/icons/application.gif" preload="true">
					    <r:treeMenu>
					      <r:treeMenuItem display="<%=funcGroup_add%>" onClickFunc="addFuncGroup"/>
					      <r:treeMenuItem display="<%=refresh%>" onClickFunc="refreshNode"/>
					    </r:treeMenu>
					  </r:treeNode>
					  <r:treeNode nodeType="AcFunctionGroup" showField="funcGroupName" 
					  action="/menumanagement/menuManagementAction_queryMenuChildGroup.action" submitXpath="AcFunctionGroup" 
					  childEntities="AcFunctionGroup;AcFunction" onClickFunc="queryFuncGroup"  preload="true" 
					  icon="/common/images/icons/function_group_open.gif,/common/images/icons/function_group_close.gif">
					    <r:treeMenu>
					      <r:treeMenuItem display="<%=subfuncGroup_add%>" onClickFunc="addSubFuncGroup"/>
					      <r:treeMenuItem display="<%=funcGroup_delete%>" onClickFunc="deleteFuncGroup"/>
					      <r:treeMenuItem display="<%=addFunction%>" onClickFunc="addFunction"/>
					      <r:treeMenuItem display="<%=refresh%>" onClickFunc="refreshNode"/>
					    </r:treeMenu>
					    <r:treeMove toNode="AcFunctionGroup" action="/menumanagement/menuManagementAction_moveTreeNode.action?execType=groupToGroup"/>
					    <r:treeMove toNode="AcApplication" action="/menumanagement/menuManagementAction_moveTreeNode.action?execType=groupToApp"/>
					  </r:treeNode>
					  <r:treeNode nodeType="AcFunction" showField="funcName" preload="true" onClickFunc="queryFuncResource" 
					  	icon="/common/images/icons/function.gif" >
					  	<r:treeMenu>
					      <r:treeMenuItem display="删除功能" onClickFunc="deleteFunction"/>
					    </r:treeMenu>
					    <r:treeMove toNode="AcFunctionGroup" action="/menumanagement/menuManagementAction_moveTreeNode.action?execType=menuToGroup"/>
					  </r:treeNode>
					</r:rtree>
					</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	 <script type="text/javascript">
	 	//新增功能组
		function addFuncGroup(node) {
			var url = "/jsp/tyjg/menumanagement/add_func_group.jsp";
		    url = url + "?appid="+node.getProperty("appId");
	        url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	        //弹出新增应用模态框
	        showModalCenter(url, "", callBackFuncGroup, 500, 350, '新增功能组');//增加功能组
		} 
		//新增完成回调函数
		function callBackFuncGroup() {
	    	$id("DTree").getSelectNode().reloadChild();
	    }
	    	//刷新节点
	  	function refreshNode(node) {
	    	node.reloadChild();
	  	}
		//查询功能
	  	function queryFunc(node){
//	  		var appId = node.getProperty("appId");
	  		var appId = 121;
	  		if(appId){
		    	parent.window.frames["IFRAMEAppFuncInfo"].location.href="/jsp/tyjg/menumanagement/application_tab.jsp?appid="+appId;
	  		}
	  	} 
	  	    //查询应用
  		function queryApp(node){
    		parent.window.frames["IFRAMEAppFuncInfo"].location.href="/jsp/tyjg/menumanagement/application_tab.jsp?appid="+node.getProperty("appId");
  		} 
  			//查询功能组信息及其子功能组列表
	  	function queryFuncGroup(node) {
	    	var url ="/jsp/tyjg/menumanagement/group_tab.jsp?appid="+node.getProperty("appId")+"&seq="+node.getProperty("funcGroupSeq")+"&funcgroupid="
	    	+node.getProperty("funcGroupId")+"&groupid="+node.getProperty("funcGroupId")+"&funcgrouplevel="+node.getProperty("groupLevel")+"&_ts="+new Date();
	    	parent.window.frames["IFRAMEAppFuncInfo"].location.href = encodeURI(url);
	  	}
	  	function queryFuncResource(node){
	  		var strUrl = "/menumanagement/menuManagementAction_querySingleFuncMenuInfo.action?acFunction.funcCode="+node.getProperty("funcCode")
		  			+"&acFunction.funcGroupId="+node.getProperty("funcGroupId")+"&execType=1&_ts="+new Date();
		  	parent.window.frames["IFRAMEAppFuncInfo"].location.href = encodeURI(strUrl);		
	  	}
	  	function deleteFunction(node){
	  		if(window.confirm("选中的菜单以及赋予角色的菜单也会同时被删除，是否确定要删除？")){
		  			var funccodes =  node.getProperty("funcCode") ;
		  			var myAjax = new Ajax("/menumanagement/menuManagementAction_deleteMenu.action");
		  			myAjax.addParam("funccodes",funccodes);
		  			myAjax.submit();
		  			var rtunVal = myAjax.getValue("root/data/rtunVal");
		  			if(rtunVal == "success"){
		  				alert("菜单删除成功");
		    			$id("DTree").getSelectNode().getParent().reloadChild();
		    			queryFuncGroup(node);
		  			}else{
		  				alert("菜单删除失败");
		  			}
		  	}
	  	}
	  	function addFuncGroup(node){
	  		var appid = node.getProperty("appId");
			var url = "/jsp/tyjg/menumanagement/add_func_group.jsp";
		    url = url + "?appid="+appid;
	        url += '&_ts='+(new Date()).getTime();  
			showModalCenter(url, "", callBackFuncGroup, 500, 220, '新增功能组');
	  	}
	  	//新增完成回调函数
		function callBackFuncGroup()  {
	    	$id("DTree").getSelectNode().reloadChild();
	    } 
	    function addSubFuncGroup(node){
	   	    var appid = node.getProperty("appId");
			var url = "/jsp/tyjg/menumanagement/add_func_group.jsp";
		    url += "?appid="+appid+"&groupid="+node.getProperty("funcGroupId")
		    		+"&seq="+node.getProperty("funcGroupSeq")+"&groupLevel="+(parseInt(node.getProperty("groupLevel"))+1);
	        url += '&_ts='+(new Date()).getTime();  
			showModalCenter(url, "", callBackFuncGroup, 500, 220, '新增功能组');
		}
		function deleteFuncGroup(node){
				if(window.confirm("选中的工作组中若包含子工作组以及下属菜单也会同时被删除，是否确定要删除？")){
		  			var groupids = node.getProperty("funcGroupId");
		  			var myAjax = new Ajax("/menumanagement/menuManagementAction_deleteBeathGroup.action");
		  			myAjax.addParam("groupids",groupids);
		  			myAjax.submit();
		  			var rtunVal = myAjax.getValue("root/data/rtunVal");
		  			if(rtunVal == "success"){
		  				alert("工作组删除成功");
		    			$id("DTree").getSelectNode().getParent().reloadChild();
		  			}else{
		  				alert("工作组删除失败");
		  			}
		  		}
		}
		
		function addFunction(node){
			var url="/jsp/tyjg/menumanagement/add_or_update_emnu.jsp?funcgroupid="+node.getProperty("funcGroupId")+"&_ts="+new Date(); 
			showModal(url,null,reQueryFuncResource,520,250,"","",'<b:message key="applicationManager_l_functionAdd"></b:message>');
		}
  		function reQueryFuncResource(){
  			$id("DTree").getSelectNode().reloadChild();
 	 	} 
		function custInit(){
			var height = document.body.clientHeight - 22;
			$id("DTree_container").style.height =height;
		}
		setTimeout(custInit,1000);
	 </script>
  </body>
</html>
