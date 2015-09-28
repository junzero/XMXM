<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<%
	//获取标签中使用的国际化资源信息
	String OmEmporg_omOrganization_orgname=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_OmEmporg.omOrganization.orgname");
	String OmEmporg_ismain               =com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_OmEmporg.ismain");
	String OmEmporg_omOrganization_orgtype=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_OmEmporg.omOrganization.orgtype");
	
	String orgid =request.getParameter("orgid");
	String empid =request.getParameter("empid");
%>
<html>
<script>
	function custInit(){
		var orgids="";
		registerEvent();
		$id("dcEmporg").beforeSubmit = function(){
			var arr = $id("dcEmporg").getAllRows(true);
			var count =1;
			for(var i=0;i<arr.length;i++){
				var entitys = $id("dcEmporg").getEntity(arr[i]);
				if(entitys.status != 1 && entitys.status !=2){
					var orgid = entitys.getProperty("orgid");
					if(orgids.toString().indexOf(orgid) !=-1){
							count = 0;
							continue;
					}
				}
			}
			if(count==1){
				return true;
			}else{
				alert("添加的机构已经存在，请选择其它机构");
				return false;
			}
		}
		
		$id("dcEmporg").beforeEdit=function(cell,colIndex,rowIndex){
			//获得编辑行的entity
			var entity=this.getEntityByCell(cell);
			var arr = $id("dcEmporg").getAllRows(true);
			for(var i=0;i<arr.length;i++){
				var entitys = $id("dcEmporg").getEntity(arr[i]); 
				var orgid = entitys.getProperty("orgid");
				if(entitys.status==1){
					if(i==0){
						orgids = orgid;
					}else{
						orgids +=","+orgid;
					}
				}
			}
			//如果编辑是0001返回false,取消编辑操作
			if(entity.getProperty("ismain")=="y" && colIndex==1){
				return false;
			}
		}
		
		$id("dcEmporg").beforeDel = function(row) {
			var entity = this.getEntity(row);
			var ismain = entity.getProperty("ismain");
			if(ismain=='y'){
				alert('操作失败！机构不能删除');
				return false;
			}
		};
	}
	
	
		
	function registerEvent() {	
		$id("dcEmporg").beforeDel = function(){
			return window.confirm("<b:message key='orgSubMaintain_l_m_alert_isRmvCurRow' />");
		}
	}

	//获得人员机构当前活动行，把lookUp返回值设置到datacell的单元格中
	function returnFuncOrg(arg){
		arg = returnFuncOrgLuss(arg);
	
		var lookup = $id("lookupID");
		lookup.value = arg[1];
		lookup.displayValue = arg[1];

		var row = $id("dcEmporg").getActiveRow();
		var entity = $id("dcEmporg").getEntity(row);
		entity.setProperty("orgid",arg[0]);		
		entity.setProperty("orgname",arg[1]);
		entity.setProperty("orgtype", arg[2]);
		entity.setProperty("empid",$name("oEmp.empid").value );
		entity.setProperty("ismain", "n");
		$id("dcEmporg").refreshRow(row);
	}
    function orgBeforeSubmit(lookup){
       lookup.clearParam();
       lookup.addParam('changeTree.lookupType',4);
       
       lookup.addParam('changeTree.showTabOrg',1);
       lookup.addParam('changeTree.orgType',4);
       lookup.addParam('changeTree.showSelBox',4);
       lookup.addParam('changeTree.lookupTypeStr',4);
       
       var myAjax = new Ajax("/empMngr/initEntityParam_empMngr.action");
       myAjax.submit();
       var orgpentityid =myAjax.getValue("root/data/orgpentityid");
       
       lookup.addParam('changeTree.startOrgid',orgpentityid);
       lookup.addParam('changeTree.checkTitle','说明：可管理机构为已选机构的本级及下级的所有机构(含部门)');
       lookup.addParam('tm',new Date().getTime());
       return true;
    }
	function returnFuncOrgLuss(arg){//如果点击右上角关闭则不进入该方法    
		var orgid = "";
		var orgname = "";
		if(arg.length<2 || arg[0].length<1){
		
		}else{
			for(var i=0;i<arg[0].length;i++){
				orgid += arg[0][i];
				orgname += arg[1][i];
				if(i<(arg[0].length-1)){
					orgid += ",";
					orgname += ",";
				}
			}
		}
		arg = [orgid,orgname];
		return arg;
	}
	//获得人员岗位当前活动行，把lookUp返回值设置到datacell的单元格中
	function returnFuncPosi(arg){    
		var lookup = $id("lookupID2");
		lookup.value = arg[1];
		lookup.displayValue = arg[1];
		var row = $id("dcEmpposi").getActiveRow();
		var entity = $id("dcEmpposi").getEntity(row);
		entity.setProperty("omPosition.positionid",arg[0]);
		entity.setProperty("omPosition.posiname",arg[1]);
		entity.setProperty("omPosition.positype", arg[2]);
		entity.setProperty("oEmp.empid",$name("oEmp.empid").value );
		entity.setProperty("ismain", "n");
		
		$id("dcEmpposi").refreshRow(row);
	}
	function selChangeFun(obj){
		var dcEmporg = $id("dcEmporg");
		var pcount = dcEmporg.getPageRowCount();
		var tcount = dcEmporg.getCurrentRowCount();
		if(tcount>pcount){
			pcount = tcount;
		}
		for(var i=0;i<pcount;i++){
			var row = dcEmporg.getRow(i);
			var entity = dcEmporg.getEntity(row);
			var ismain = entity.getProperty("ismain");
			if(ismain=='y'){
				var cell = dcEmporg.getCellByRow(row,1);
				dcEmporg.setCellValue(cell,'n');
			}
		}
		if(obj.value=='n'){
			alert('操作失败！有且仅有一个主机构');
			obj.value='y';			
		}
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Title</title>
</head>
<body topmargin="0" leftmargin="0" rightmargin="0">

<h:form id="qfEmpopr">
	<h:hidden name="oEmp.empid" property="oEmp.empid" value="${param.empid }"/>
</h:form>

<r:datacell id="dcEmporg" showIndex="false" 
    queryAction="/empMngr/empOrgBelongDataCell_empMngr.action" paramFormId="qfEmpopr"
	xpath="com.gotop.tyjg.empMngr.model.OmEmporg" width="100%" pageSize="1000" pageSizeList="1000" 
	submitAction="/empMngr/saveEmpOrg_empMngr.action"  >

    <r:toolbar tools="nav:,edit:add del save reload,pagesize,info" location="botton" />

	<r:field fieldName="orgname" width="300" label="<%=OmEmporg_omOrganization_orgname %>" editId="lookupID" allowModify="false">  
        <w:lookup id="lookupID" property="omEmpTm.orgidlist" readonly="true" displayProperty="omEmpTm.orgnamelist" 
    		validateAttr="allowNull=false" width="380" height="430" lookupWidth="380"
        	lookupUrl="/tree/initMainTree_mainTree.action?changeTree.lookupType=4&changeTree.showTabOrg=1&changeTree.orgType=4&changeTree.showSelBox=4&changeTree.lookupTypeStr=4&changeTree.checkcount=1"
        	dialogTitle="选择组织归属" onReturnFunc="returnFuncOrg">
		</w:lookup>
	</r:field>	
	<r:field fieldName="ismain" width="100" label="<%=OmEmporg_ismain %>" defaultValue="n">
		<d:select dictTypeId="ABF_YESORNO" onchange="selChangeFun(this)"/>
	</r:field>
	<r:field fieldName="orgtype" width="200" label="<%=OmEmporg_omOrganization_orgtype %>" allowModify="false"><d:select dictTypeId="ABF_ORGTYPE"/></r:field>
</r:datacell>
  
</body>
</html>

<script>
	eventManager.add(window,"load",custInit);
</script>

