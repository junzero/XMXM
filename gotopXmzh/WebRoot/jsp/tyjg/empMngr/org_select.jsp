<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="com.gotop.vo.system.MUOUserSession"%>
<%@page import="com.gotop.util.Global"%>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>机构树</title>
<%
	MUOUserSession musous = (MUOUserSession)request.getSession().getAttribute(Global.LOGON_USER_KEY);
	String startOrgid =musous.getOrgentityid().toString();
	System.out.println("startOrgid = "+startOrgid);
%>
<script>
	var orgid = "";
	var orgName = "";
	var orgType = "";
	var status = "";
	function getInitParam()
	{
		return "<omEmp><empid>"+ $name("empid").value +"</empid></omEmp>"; //
	}
	
	function getInitParams(){
	    <%--var ret = '<startOrgid><%=startOrgid%></startOrgid><startOrgcode>${requestScope.startOrgcode}</startOrgcode>';
	    	ret += "<jgsx>${requestScope.jgsx}</jgsx>";--%>
	    var ret = "<empid>"+${sessionScope.login_user.empid}+"</empid><manager>manager</manager>";
		return ret ;
	}	
	
	function clickNode( node ) {
		setReturnValue( node );
	}	
	function dbClickNode( node ) {
		setReturnValue( node );
		returnValue = [orgid, orgName,orgType,status];
		window.closeD();
	}
	function setReturnValue( node ) {
		orgid = node.getProperty("orgId");
		orgName   = node.getProperty("orgName");
		orgType = node.getProperty("orgType");
		status   = node.getProperty("status");	            
		$name("orgName").value = orgName;
		$name("orgid").value = orgid;
		$name("orgType").value = orgType;
		$name("status").value = status;		
	}
       
	function closeWin( idValue1, nameValue2,idValue3, nameValue4 ) {
		returnValue = [ idValue1, nameValue2,idValue3, nameValue4 ];
		window.closeD();
	}
        
	function ok() {
		if( orgid == "" || orgid == null || orgName == "" || orgName == null ) {
			if( confirm("您没有选择值，确定退出么？") ) {
				closeWin( orgid, orgName,orgType,status );    
			}
		} else {
			closeWin( orgid, orgName,orgType,status );    
		}
	} 
	
</script>
</head>
<body>

<h:hidden property="empid" scope="s" />

<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td class="eos-panel-table" width="345" height="360" valign="top">
		<r:rtree id="orgTreeSelect"> 
			<r:treeRoot childEntities="Tomorganization" display="可管理机构" initParamFunc="getInitParams"
				icon="/common/images/icons/arrow_merge.png"
				action="/empMngr/queryMngorgOfLogon_empMngr.action" >
			</r:treeRoot>	
			<r:treeNode  nodeType="Tomorganization" showField="orgName" childEntities="Tomorganization" 
				icon="/common/images/icons/chart_organisation.gif"
				submitXpath="oParentOrg"
				onDblclickFunc="dbClickNode" onClickFunc="clickNode" 
 				action="/empMngr/querySelectTreeNodes_empMngr.action" >
			</r:treeNode>	
		</r:rtree>
		</td>    
	</tr>
	<tr>
	    <td>
		   您选择的值为：<input type="text" name="orgName" readonly="readonly" value=""/> 
		   <input type="hidden" name="orgid" value=""/>
		   <input type="hidden" name="orgType" value=""/>
		   <input type="hidden" name="status" value=""/>		   		   
		   &nbsp;&nbsp;
		   <input type="button" class="button" value="确定" onclick="javascript:ok();"/>
		   &nbsp;&nbsp;
		   <input type="button" class="button" value="清空" onclick="javascript:closeWin('','','','');"/>
		</td>
	</tr>	
</table>
</body>
