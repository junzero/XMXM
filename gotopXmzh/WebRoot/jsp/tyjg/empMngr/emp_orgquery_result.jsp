<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="com.gotop.vo.system.MUOUserSession"%>
<%@page import="com.gotop.util.Global"%>
<%
	String addEmp=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("empOrgMaintain_l_title_addEmp");
	String mdfEmp=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("empOrgMaintain_l_title_mdfEmp");
	String empQryRes=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("empOrgMaintain_l_title_empQryRes");
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>人员信息</title>
<%
	String orgid =request.getParameter("orgid");
%>
<h:css href="/css/style1/style-custom.css"/>
</head>
<body topmargin="0" leftmargin="0">
<form name="query_form" id="query_form">
   <h:hidden property="orgid" value="<%=orgid%>" />
</form>
<h:form name="page_form" id="page_form" action="${pageContext.request.contextPath }/tyjg/empMngr/queryEmpOrgDataGrid_empMngr.action" checkType="blur" target="_self" method="post">
  <h:hiddendata property="page" />
	<r:datacell pageSize="15" entityType="com.gotop.vo.tyjg.OmEmpOrg"
		id="cell1" initParamFunc="initParam"
		queryAction="/empMngr/queryEmpOrgDataGrid_empMngr.action"
		submitAction="/empMngr/upDataGridOrder_empMngr.action"
		width="100%" height="380" xpath="com.gotop.tyjg.empMngr.model.EmpUnOrgBean" paramFormId="query_form">
		<%
			MUOUserSession muous = (MUOUserSession)request.getSession().getAttribute(Global.LOGON_USER_KEY);
			String[] roleids = muous.getRoleid();
			boolean falg =false;
			for(String roleid : roleids){
				if("eosadmin".equals(roleid)){
					falg=true;
				}
			}
			if(falg){
		%>
			<r:toolbar/>
		  <%}else{%>
		    <r:toolbar tools="nav:first prev next last goto,pagesize,info"/>
		  <%}%>
		
		
		<r:field align="center" allowResize="true" fieldName="empcode" allowModify="false" label="人员工号" width="200">
			<h:text validateAttr="allowNull=false;message=该输入项是必输项"/>
		</r:field>
		<r:field align="center" fieldName="empname" label="人员姓名" width="200" allowModify="false" >
			<h:text validateAttr="allowNull=false;message=该输入项是必输项" onblur="realnameFun(this)"/>
		</r:field>
		<r:field align="center" fieldName="empstatus" label="人员状态" width="100" defaultValue="on" allowModify="false" >
			<d:radio dictTypeId="ABF_EMPSTATUS"  id="empstatus"/>
		</r:field>
		<r:field align="center" fieldName="orgname" label="所属单位" width="100" allowModify="false" />
		<r:field align="center" fieldName="displayorder" label="显示顺序" width="100" sortAt="client" allowModify="false" >
			<h:text/>
		</r:field>
	</r:datacell>
	<input type="button" class="button" onclick="openOrderWin()" value="整调顺序" />
</h:form>
</body>
</html>
<script>
  function callBack(returnValue) {
	parent.parent.orgTree.getSelectNode().reloadChild();
 	window.location.href=window.location.href+"&";
  }

  
  function initParam(){
  	var str = "<oOrg><orgid>"+$name("orgid").value+"</orgid></oOrg>";
  	return str;
  }
  //eventManager.add(window,"load",initButtonStyle);	
 // setTimeout(initButtonStyle,1000);
  
	
	Datacell.prototype.onUpdateCell = function(activeCell,activeEntity,activeFieldName,newValue) {
		var empstatus = $id("empstatus");
		if(empstatus!=null){
			activeEntity.setProperty("status","running");
			if(empstatus.getValue()=="on"){//在岗
				activeEntity.setProperty("status","running");//正常
			}else{
				activeEntity.setProperty("status","stop");;//不正常
			}
			this.activeEntity=activeEntity;
		}	
	};
	function openOrderWin(){
		var strUrl = "/empMngr/repOrderEmp_empMngr.action?time="+new Date().getTime();
		strUrl += "&iOrgid=<%ForUtil.outObj(out,orgid);%>";
		showModalCenter(strUrl,null,reQueryResource,300,450,'人员排序');
	}
	function reQueryResource(){
		$id("cell1").reload(true);
	}
	String.prototype.replaceAll = function(AFindText,ARepText){
		raRegExp = new RegExp(AFindText,"g");
		return this.replace(raRegExp,ARepText)
	}
	function realnameFun(obj){
    	var objstr = obj.value;
    	if(objstr!=null){
	    	obj.value = objstr.replaceAll(" ", "");
    	}
	}
	Datacell.prototype.afterEdit = function(newValue,oldValue) {
		var acell = this.activeCell;
		if(newValue){
			newValue = newValue.replaceAll(" ", "");
		}
		var entity = this.getEntityByCell(acell);
		var status = entity.status;
		this.setCellValue(acell,newValue);
		entity.status = status;
	};
</script>
