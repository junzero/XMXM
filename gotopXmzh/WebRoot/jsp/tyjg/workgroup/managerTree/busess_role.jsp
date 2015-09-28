<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      业务角色
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<h:script src="/common/gotop/datacellCheckBox.js"></h:script><!-- 引用datacell单选函数 -->
	<h:script src="/common/gotop/lookup-group1.js"/>
  </head>
 <script type="text/javascript">
  	var checkgroup = new CheckGroup("dcHavenot_group");
	checkgroup.cellnum = 0;//指定那一个列为复选框
	checkgroup.beforeSelectFunc="lookUp_selectFunc(this)";
	checkgroup.beforeUnSelectFunc="lookUp_unSelectFunc(this)";
	/*
	*	初始化选中框
	*/	
	Datacell.prototype.afterRefresh = function() {
		this.checkgroup.selectBoxID = window.parent.$id("relatedSel");//已选框ID
		this.checkgroup.submitParamName = "roleid";//lookup的值与CheckGroup的对应h:param
		this.checkgroup.displayParamName = "rolename";//lookup的显示名与CheckGroup的对应h:param
		this.checkgroup.hiddenParamName = "auto";//lookup的显示名与CheckGroup的对应h:param
		this.checkgroup.initTitle=true;
		this.checkgroup.callType='parent';
		this.checkgroup.entityName = "Role";
		this.checkgroup.checkcount='${changeTree.checkcount}';
		this.checkgroup.initLookUp_Datacell("mngroles");
	}
	function rtnSelectVal() {
		window.returnValue = checkgroup.getReturnValue();//从CheckGroup中取得返回值
		window.closeD();
	}
	rowCheckBox.prototype.reverseSelect = function() {
	    if (this.value){
	    	var roleid = this.getParam("auto");
			if(roleid=='0'){
				alert(this.getParam("rolename")+'为指定分配角色。不能私自删除');
			}else{
			    this.disSelected();
			}
	    }else{
		    this.setSelected()
	    }
	};
	CheckGroup.prototype.deleteOption = function(selObj){
		var index = selObj.options.selectedIndex;
		if(index>=0){
			var row = selObj.options[index].row;
			if(row){
				selObj.options[index].row.reverseSelect();
			}else{
				var obj = selObj.options[index];
				var value = getOrgdegreeByRoleid(obj.value);
				if(value==0){
					alert(obj.innerText+'为指定分配角色。不能私自删除');
				}else{
				    selObj.options[index]=null;	
				}
			}
		}
	}
	CheckGroup.prototype.selectedDeleteOption = function(selObj){
		for (var i = selObj.options.length-1; i >= 0; i--) {
			if(selObj.options[i].selected){
				var row = selObj.options[i].row;
				if(row){
					selObj.options[i].row.reverseSelect();
				}else{
					var obj = selObj.options[i];
					var value = getOrgdegreeByRoleid(obj.value);
					if(value==0){
						alert(obj.innerText+'为指定分配角色。不能私自删除');
					}else{
					    selObj.options[i]=null;
					}
				}
			} 
		}
	}
	/*
	* 获取角色的机构等级
	*/
	function getOrgdegreeByRoleid(roleid){
        var myAjax = new Ajax("/tree/selectRoleAuto_mainTree.action");
        myAjax.addParam("roleid", roleid);
        myAjax.submit();
        var auto =myAjax.getValue("root/data/auto");
		return auto;
	}
	
	function paramFunc(){
		return '<jgsx>${sessionScope.login_user.orgJgsx}</jgsx><groupID>${changeTree.groupID}</groupID>';
	}
	
  </script>
<body topmargin="0" leftmargin="0" rightmargin="0">
	<%
		String pnHavenot_rolename=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_pnHavenot.rolename");
	%>
   <table align="center" border="0" width="100%">
   		<tr>
   			<td valign="top" ondblclick="window.parent.rtnSelectVal();">
				<r:datacell id="dcHavenot" showIndex="false" initParamFunc="paramFunc"
					paramFormId="qfHavenot" xpath="com.gotop.tyjg.common.model.Role" width="100%"
					height="370" pageSize="20"
					queryAction="/workgroup/managertreeAction_selectBusessRole.action"> 
					<r:toolbar tools="nav:first prev next last goto,pagesize,custom,info" location="botton" />
					<r:field fieldName="roleid" label="操作" sortAt="client" width="30" align="center" /> 
				    <r:field fieldName="rolename" label="<%=pnHavenot_rolename%>" width="250"/> 
				</r:datacell>
   			</td>
   		</tr>
   	</table>
  </body>
</html>
