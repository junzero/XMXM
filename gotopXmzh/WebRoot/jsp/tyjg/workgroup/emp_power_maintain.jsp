<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<%
	//获取标签中使用的国际化资源信息
	String maintainOperFunc=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_maintainOperFunc");

	String pnHave=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_pnHave");
	String pnHavenot=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_pnHavenot");
	String pnOperfunc=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_pnOperfunc");

	String pnHave_acRole_roleid=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnHave.acRole.roleid");
	String pnHave_acRole_rolename=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnHave.acRole.rolename");
	String pnHave_acRole_appname=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnHave.acRole.appname");

	String pnHavenot_roleid=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnHavenot.roleid");
	String pnHavenot_rolename=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnHavenot.rolename");
	String pnHavenot_appname=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnHavenot.appname");

	String pnOperfunc_acFunction_funcname=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnOperfunc.acFunction.funcname");
	String pnOperfunc_funcgroupname=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnOperfunc.funcgroupname");
	String pnOperfunc_appname=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnOperfunc.appname");
	String pnOperfunc_authtype=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnOperfunc.authtype");
	String pnOperfunc_startdate=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnOperfunc.startdate");
	String pnOperfunc_enddate=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnOperfunc.enddate");

	String pnFrom_acRole_roleid=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnFrom.acRole.roleid");
	String pnFrom_acRole_rolename=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnFrom.acRole.rolename");
	String pnFrom_appname=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnFrom.appname");
	
	String pnFrom_Role=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("bizqueryEmployeeRole_l_from_role");
	
	String desc=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("bizqueryEmployeeRole_l_desc");
	
	String delete_iconPngStr = contextPathStr + "/common/images/icons/delete_icon.png";
	String addPngStr = contextPathStr + "/common/images/icons/add.png";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body topmargin="0" leftmargin="0" rightmargin="0">
<table align="left" border="1" cellpadding="0" cellspacing="0" height="100%" width="100%">
	<tr><%-- 直接设置的角色 --%>
		<td width="50%" height="30%" valign="top">
			<w:panel id="pnHave" title="<%=pnHave %>" height="100%">   
			<form id="qfHave">
			    <h:hidden name="omEmployee/empid" property="oPartyrole/partyid" />
			    <h:hidden name="acOperator/operatorid" property="emp/operatorid" />
			    <h:hidden property="type" value="self"/>
			</form>
			<r:datacell id="dcHave" rowStyleClass="rowclass2" 
				rowEvenStyleClass="rowclass1" showIndex="false"
				queryAction="org.gocom.abframe.org.party.PartyManager.queryEmployeeRole.biz"
				paramFormId="qfHave" xpath="roles" submitXpath="oOperrole/acRole"
				width="100%" height="100%" pageSize="10" pageSizeList="10,20,30"
				submitAction="org.gocom.abframe.org.party.PartyManager.saveOperrole.biz">

				<r:toolbar tools="nav,custom,edit:reload,info" location="botton" />
				<r:field fieldName="roleid" label="<%=pnHave_acRole_roleid%>" sortAt="client" width="0" /> 
				<r:field fieldName="rolename" label="<%=pnHave_acRole_rolename%>" />
				<r:field fieldName="acApplication/appname" label="<%=pnHave_acRole_appname%>" />
			</r:datacell>
		</w:panel>
		</td>
		<%-- 未授权的角色 --%>
		<td width="50%" height="30%" valign="top">
			<w:panel id="pnHavenot" height="100%"	title="<%=pnHavenot %>">
				<form id="qfHavenot">
					<h:hiddendata name="oPartyroleHavenot" property="oPartyrole" />	
					<h:hidden name="user/empid" property="userObject/attributes/empid" scope="s"/>				
					<h:hidden name="user/operatorid" property="userObject/attributes/operatorid" scope="s"/>				
					<h:hidden name="user/userid" property="userObject/userId" scope="s"/>
				</form> 
				<r:datacell id="dcHavenot" rowStyleClass="rowclass2"
					rowEvenStyleClass="rowclass1" showIndex="false"
					queryAction="org.gocom.abframe.org.party.PartyManager.queryOperroleHavenot.biz"
					paramFormId="qfHavenot" xpath="oaPartyroleHavenot" width="100%"
					height="100%" pageSize="10" pageSizeList="10,20,30"
					submitAction="org.gocom.abframe.org.party.PartyManager.saveOperroleHavenot.biz" > 
	
					<r:toolbar tools="nav,custom,edit:reload,info" location="botton" />
					<r:field fieldName="roleid" label="<%=pnHavenot_roleid%>" sortAt="client" width="0" /> 
					<r:field fieldName="rolename" label="<%=pnHavenot_rolename%>" /> 
					<r:field fieldName="appname" label="<%=pnHavenot_appname%>" />  
				</r:datacell>
			</w:panel>
		</td>
	</tr>
	<tr><%-- 直接授予权限 --%>
		<td width="100%" height="30%" valign="top" colspan="2">
			<w:panel id="pnOperfunc" title="<%=pnOperfunc %>" height="100%">
				<h:form id="qfOperfunc">
					<h:hidden name="oOperfunc/acOperator/operatorid" property="emp/operatorid" />
				</h:form>
				<r:datacell id="dcOperfunc" rowStyleClass="rowclass2"
					rowEvenStyleClass="rowclass1" showIndex="false"
					queryAction="org.gocom.abframe.org.party.PartyManager.queryOperfunc.biz"
					paramFormId="qfOperfunc" xpath="oaOperfunc" width="100%" height="100%"
					pageSize="10" pageSizeList="10,20,30" submitAction="">
					<r:toolbar tools="nav,custom,info" location="botton" />
	                <r:field fieldName="authtype" label="<%=pnOperfunc_authtype%>" allowModify="false"><d:select dictTypeId="ABF_FUNCAUTHTYPE" /></r:field>
					<r:field fieldName="acFunction/funcname" label="<%=pnOperfunc_acFunction_funcname%>" /> 
					<r:field fieldName="funcgroupname" label="<%=pnOperfunc_funcgroupname%>" /> 
					<r:field fieldName="appname" label="<%=pnOperfunc_appname%>" />				
					<r:field fieldName="startdate" label="<%=pnOperfunc_startdate%>" />  
					<r:field fieldName="enddate" label="<%=pnOperfunc_enddate%>" /> 
				</r:datacell>
			</w:panel>
		</td>
	</tr>
	<tr><%-- 从其他地方获取的角色，包括从以下几个方面获取的角色：岗位、机构、岗位职务、工作组 --%>
		<td width="100%" height="40%" valign="top" colspan="2">
		<w:panel id="pnFromAll"	title="<%=pnFrom_Role %>" height="100%"> 
			<h:form id="qfFromAll">
				<h:hidden name="omEmployee/empid" property="oPartyrole/partyid" />
			    <h:hidden property="type" value="allFrom"/>			
			</h:form>
			<r:datacell id="dcFromAll" rowStyleClass="rowclass2"
				rowEvenStyleClass="rowclass1" showIndex="false"
				queryAction="org.gocom.abframe.org.party.PartyManager.queryEmployeeRole.biz"
				paramFormId="qfFromAll" xpath="roles" width="100%" height="100%"
				pageSize="10" pageSizeList="10,20,30" submitAction="">

				<r:field fieldName="roleid" label="<%=pnFrom_acRole_roleid%>" sortAt="client" width="0" />
				<r:field fieldName="rolename" label="<%=pnFrom_acRole_rolename%>" />
				<r:field fieldName="appname" label="<%=pnFrom_appname%>" />
				<r:field fieldName="roledesc" label="<%=desc%>" />
			</r:datacell>
		</w:panel></td>
	</tr>
</table>
</body>
</html>
<script>
	function custInit(){
		//增加"直接授予的权限"Datacell定制BUTTON
		$id("dcHave").setCustomTool('<img src="<%=delete_iconPngStr %>" alt=\"<b:message key="orgSubMaintain_l_btnadd_maintainPowerOrg"/>\" onclick="delRec();" />');
		$id("dcHavenot").setCustomTool('<img src="<%=addPngStr %>" alt=\'<b:message key="orgSubMaintain_l_btndel_maintainPowerOrg"/>\' onclick="addRec();"/>');
    	$id("dcOperfunc").setCustomTool('<img src="<%=addPngStr %>" alt=\'<b:message key="l_modify"/>\' onclick="modifyFunc();"><img src="<%=delete_iconPngStr %>" alt=\'<b:message key="l_modify"/>\' onclick="modifyFunc();">');	
        registerEvent();         
    }
	function registerEvent() {	
		//1.		
		$id("dcHave").afterDel = function(){
			$id("dcHave").submit();
		}	
		$id("dcHave").afterSubmit = function(){
			$id("dcHavenot").reload();
		}
	
		//2.		
		$id("dcHavenot").afterDel = function(){
			$id("dcHavenot").submit();
		}	
		$id("dcHavenot").afterSubmit = function(){
			$id("dcHave").reload();
		}	
		//3 维护直接授权表
		$id("dcOperfunc").afterDel = function(){
			$id("dcOperfunc").submit();
		}
		//4.选中角色显示角色对应功能列表
		function showFunc(row){
		//	 var id = $name("rolefunc/_expr[1]/roleid");
		//	 id.value = this.getEntity(row).getProperty("roleid");
		//	 if(id.value!=null&&id.value!=""){
	    //      $id("dcFunc").reload();	
	    //     }
	    }      
	    $id("dcHave").afterSelectRow= showFunc;
	    
		$id("dcHavenot").afterSelectRow = showFunc;
	}
	function delRec(){
	  $id("dcHave").deleteRow();
	}
	function addRec(){
	  $id("dcHavenot").deleteRow();
	}	
	function modifyFunc() {
		var parm='oEmp/empid=<b:write property="emp/empid" />'; 
		var sUrl="org.gocom.abframe.org.organization.FuncAssign.flow?"+parm;   
		showModalCenter(sUrl,null,callBack,600,400,"<%=maintainOperFunc %>");
	}
	function callBack(){
        $id("dcOperfunc").reload();	
	}
	function dispAllFunc(){	    
	    var id = $name("rolefunc/_expr[1]/roleid");
        var	rows = $id("dcHave").getAllRows(true);
        var strs = new Array(rows.length);
        for(var i=0;i<rows.length;i++){
           strs[i]=$id("dcHave").getEntity(rows[i]).getProperty("acRole/roleid");
        }
        id.value=strs.join(",");
	    $id("dcFunc").reload();		
	}	
</script>
<script>
	eventManager.add(window,"load",custInit);		
</script>
