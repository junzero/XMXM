<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<%@page import="com.gotop.vo.system.MUOUserSession"%>
<h:css href="/css/style1/style-custom.css"/>
<%
	//角色查询条件
	String role_queryCond = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_title_role")
	                      + com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_queryCond");
	//请选择
	String pleaseSelect = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_pleaseSelect");
	//角色查询结果
	String roleList = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_title_role")
					+ com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_query_result");
	//角色编号
	String roleid = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_AcRole.roleid");
	//String roledesc = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_AcRole.roledesc");
	//角色名称
	String rolename = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_AcRole.rolename");
	//角色类别
	String roletype = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_AcRole.roletype");
	//所属应用
	String appid = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_AcRole.appid(appname)");
	//操作
	String operation = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_operation");
	
	String roleReadonly = request.getParameter("roleReadonly");
	if(roleReadonly==null || "".equals(roleReadonly)){
		roleReadonly = "0";
	}
	String currentTab = "func";
	if("1".equals(roleReadonly)){
		currentTab = "assign";
	}
	
	String roletypeObj = (String)request.getAttribute("roletype");
	String roletypeValue = "0";
	if(roletypeObj==null || roletypeObj.toString().equals("")){
		roletypeValue = "0";
	}else{
		roletypeValue = roletypeObj.toString(); 
	}
	Boolean allowModify = false;
	if("1".equals(roletypeValue) || "0".equals(roleReadonly)){
		allowModify = true;
	}
	request.setAttribute("allowModify",allowModify);
	
	
	MUOUserSession muous = (MUOUserSession)session.getAttribute("login_user");
	String jgsx = muous.getOrgJgsx();
	System.out.println("-----------jgsx-"+jgsx);
%>
<html>
<head>
<title></title>
	<h:script src="/common/gotop/gotop-datacell.js"/>
</head>
<body leftmargin="0" topmargin="0" style="overflow: auto;">
<TABLE border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
	<TR>
		<TD valign="top" width="520px" height="100%">
			<w:panel width="520" id="roleQuery" title="<%=role_queryCond %>">
				<h:form name="queryForm" id="queryForm" checkType="blur"  method="post" action="">
					<table border="0" width="100%" class="form_table" cellpadding="0" cellspacing="0">
						<tr>
							<td class="form_label"><nobr><b:message key="roleManager_AcRole.rolename" /></nobr></td>
							<!-- 角色名称 -->
							<td><input id="roleName" name="acRole.roleName" type="text"></td>
							<td class="form_label"><nobr><b:message key="roleManager_AcRole.roletype" /></nobr></td>
							<!-- 角色类别 -->
							<td><d:select id="acRole.roleType" style="WIDTH:123;" name="acRole.roleType"
								dictTypeId="ABF_ROLETYPE" nullLabel="<%=pleaseSelect %>" /></td>
							<td><input calss="button" type="button" style="width:40" value="<b:message key="l_query"/>"
								onclick="javascript:queryRole('roleListCell')">
								<input calss="button" type="button" style="width:60" value="清除缓存" onclick="cleanRole()" />
							</td>
							<input type="hidden" value="<%=roletypeValue%>" name="roletype" id="roletype" />
						</tr>
					</table>
			    </h:form>
			</w:panel>
			<table border="0" width="520px" height="100%" cellpadding="0" cellspacing="0">
				<tr height="4%">
					<td class="eos-panel-title" width="520px">&nbsp;&nbsp;<%=roleList%></td>
				</tr>
				<tr>
					<td width="520px" height="100%" valign="top" class="eos-panel-body">
						<h:form name="page_form" id="page_form" checkType="blur" target="_self" method="post" action="">
						<div style="width: 520px;overflow: hidden;">
							<r:datacell id="roleListCell"  allowAdd="true" allowDel="true"   
								readonly="false" showIndex="true" isCount="true"
								pageSize="10" pageSizeList="10,20,30,50,60" freezeNumber="0"
								entityType="com.gotop.tyjg.roleprivilege.model.AcRole"
								paramFormId="queryForm" width="520px"
								queryAction="/tyjg/roleprivilege/roleAction_queryRolePage.action"
								xpath="com.gotop.tyjg.roleprivilege.model.AcRole"
								submitAction="/tyjg/roleprivilege/roleAction_saveRole.action"
								>
								
								<%if("0".equals(roleReadonly) || "2".equals(roleReadonly)){%>
									<r:toolbar location="bottom" tools="nav:first prev next last goto,edit:add del save reload,pagesize,custom,info"/>
								<%}else{%>
									<r:toolbar location="bottom" tools="nav:first prev next last goto,edit:reload,info,pagesize,custom"/>
								<%}%>
								
								<r:field align="left" width="110" allowModify="false" allowResize="true" 
									fieldName="roleId" label="<%=roleid %>">
									<!-- 角色编号 -->
									<h:text name="roleId" validateAttr="allowNull=false;message=该输入项是必输项"/>
								</r:field>
								<r:field align="left" width="140" allowModify="true" allowResize="true" fieldName="roleName" label="<%=rolename %>">
									<!-- 角色名称 -->
									<h:text name="roleName" id="roleName" validateAttr="allowNull=false;message=该输入项是必输项" />
								</r:field>
								<r:field align="left" width="60" allowModify="${allowModify}" allowResize="true" align="center" fieldName="roleType" label="<%=roletype %>" defaultValue="0">
									<!-- 角色类别 -->
									<d:select id="roleType" style="WIDTH:100;" dictTypeId="ABF_ROLETYPE" disabled="${!allowModify}"/>
								</r:field>
								<r:field align="left" width="60" allowResize="true" align="center" fieldName="roleTypeField" label="角色性质" defaultValue="${sessionScope.login_user.orgJgsx}">
									<!-- 角色性质 --> 
									<d:select id="roleTypeField" style="WIDTH:100;" dictTypeId="ABF_ROLETYPEFIELD" disabled="true" value="${sessionScope.login_user.orgJgsx}"/>
								</r:field>
								<r:field align="left" width="60" allowResize="true" align="center" fieldName="auto" label="角色等级" defaultValue="4">
									<!-- 操作等级 --> 
									<d:select id="auto" style="WIDTH:100;" dictTypeId="ABF_ROLEDEGREE"/>
								</r:field>
								<r:field align="center" width="60" allowModify="true" allowResize="true" 
									fieldName="function" label="<%=operation %>"
									onRefreshFunc="showOperation" >
									<!-- 操作 -->
								</r:field>
							</r:datacell>
						</div>
						</h:form>
					</td>
				</tr>
			</table>
		</TD>
		<TD width="52%" height="100%" valign="top">
			<iframe frameBorder="0" style="width:100%;height:100%" scrolling="no" src="" name="privilegeTab"> </iframe>
		</TD>
	</TR>
</TABLE>
<h:hidden property="roleReadonly"/>
<script type="text/javascript" id="roleListCell_script">
    var isFirst = true;
	/*
	 * 刷新datacell
	 */
	function queryRole(datacellId){
		var resultDatacell=$id(datacellId);
		resultDatacell.reload(true); //重载datacell
	}
	/* 
	 * datacell的初始化查询条件
	 */
	function initRoleListCell(){
		var queryCond="";
		var roleReadonly = $name("roleReadonly").value;
		if(roleReadonly){}else{
			$name("roleReadonly").value = 0;
		}
		queryCond += "<roleReadonly>"+$name("roleReadonly").value+"</roleReadonly>"
		queryCond = queryCond+"<acRole.roleName>"+$id("acRole.roleName").value+"</acRole.roleName>";
		queryCond = queryCond+"<acRole.roleType>"+$id("acRole.roleType").value+"</acRole.roleType>";
		alert(queryCond)
		return queryCond;
	}
	
	var currentTab = "<%=currentTab%>";
	
	/*
	 * 在页面上显示编辑按钮
	 */
	function showOperation(value,entity,rno,cno,datacell){
		var roleid = entity.getProperty('roleId');
		var appid = entity.getProperty('appId');
		var roletype = entity.getProperty('roleType');
		var auto = entity.getProperty('auto');
		
		if(entity.status == Entity.STATUS_NEW){
			return "";
		}
		if(appid != null){
		    if( isFirst ) {
		        isFirst = false;
		        if( typeof(roleid) == "undefined" || roleid == "" ||  roleid == null )
		            roleid = "";
		        if( typeof(appid) == "undefined" || appid == "" ||  appid == null )
		            appid = "";    
//		        modifyRole( roleid, appid );
		    }
		    var roleReadonly = $name("roleReadonly").value;
		    if(roleReadonly=='1'){
				return "<input style='margin-top:-4' type='button' class='button'  value='授权' onclick='javascript:modifyRole(this,\""+roleid+"\",\""+appid+"\",\""+roletype+"\",\""+auto+"\");'>";
		    }else{
				return "<input style='margin-top:-4' type='button' class='button'  value='<b:message key="roleManager_l_edit"/>' onclick='javascript:modifyRole(this,\""+roleid+"\",\""+appid+"\",\""+roletype+"\",\""+auto+"\");'>";
		    }
	
		}else{
		    if( isFirst ) {
		        isFirst = false;
		        if( typeof(roleid) == "undefined" || roleid == "" ||  roleid == null )
		            roleid = "";
//		        modifyRole1( roleid );
		    }
		    var roleReadonly = $name("roleReadonly").value;
		    if(roleReadonly=='1'){
				return "<input style='margin-top:-4' type='button' class='button' value='授权' onclick='javascript:modifyRole1(this,\""+roleid+"\",\""+roletype+"\",\""+auto+"\");'>";
		    }else{
				return "<input style='margin-top:-4' type='button' class='button' value='<b:message key="roleManager_l_edit"/>' onclick='javascript:modifyRole1(this,\""+roleid+"\",\""+roletype+"\",\""+auto+"\");'>";
		    }
		}
	}	
	/*
	 * 点击编辑按钮时(应用编号不为空时)调用的Url
	 */
	function modifyRole(objBut,roleid,appid,roletype,auto){
		var url ="/jsp/tyjg/roleprivilege/privilege_tab.jsp?acRole.roleId="+roleid;
		url += "&acRole/appId="+appid;
		url += "&acRole.roleType="+roletype;
		url += "&acRole.auto="+auto;
		url += "&currentTab=" + currentTab; 
		
		var roleReadonly = $name("roleReadonly").value;
		url += "&roleReadonly="+roleReadonly;
		
		showProgressBar("doAction","<img src='/common/images/loading.gif'> 正在执行操作....");
		maskTop();		
		$name("privilegeTab").src = url;	  
		
		onTable(objBut);  
	}
	/*
	 * 点击编辑按钮时(应用编号为空时)调用的Url
	 */
	function modifyRole1(objBut,roleid,roletype,auto){
		var url ="/jsp/tyjg/roleprivilege/privilege_tab.jsp?acRole.roleId="+roleid;
		url += "&acRole.roleType="+roletype;
		url += "&acRole.auto="+auto;
		url += "&currentTab=" + currentTab;
		
		var roleReadonly = $name("roleReadonly").value;
		url += "&roleReadonly="+roleReadonly;
		
		showProgressBar("doAction","<img src='/common/images/loading.gif'> 正在执行操作....");
		maskTop();
		$name("privilegeTab").src = url;
		
		onTable(objBut);		
	}
	
	function onTable(objBut){
		var pnode = objBut.parentNode;

		if(pnode==undefined){
			return null;
		}
		var temp = 0;
		var tabelTemp = null;
		var trIndex = null;
		for(var i=0;temp<2 && i<20;i++){
			pnode = pnode.parentNode;
			if(pnode==undefined){
				return null;
			}
			if(pnode.tagName=='TR'){
				temp += 1;
				if(temp==1){
					trIndex = pnode.rowIndex;
				}
			}
			if(pnode.tagName=='TABLE'){
				tabelTemp = pnode;
			}
		}
		var tableArra = pnode.getElementsByTagName("table");
		
		for(var i=0;i<tableArra[3].rows.length;i++){
			var rowObj = tableArra[3].rows[i];
			for(var j=0;j<rowObj.cells.length;j++){
				var celObj = rowObj.cells[j];
				var chiObj = celObj.childNodes;
				chiObj[0].innerHTML="&nbsp;";
			}
		}
		tableArra[3].rows[trIndex].cells[0].firstChild.innerHTML="<span style='vertical-align: middle;font-size: smaller'>*</span>";

	}
	
	var datacell = null;
	var activeCellValue = "";
	var row = "";
	var entity = "";
	
	
	/*
	 * 验证当前页面输入的值是否有重复
	 */
	function checkInputValue( value ){			
		var tmpValue = "";		
		var len = datacell.getCurrentRowCount();  //获取当前页的有效行数		
		var tmpRow = null;		
		for (var r = 0; r < len; r++){
		    tmpRow = datacell.getRow( r );
		    row = datacell.getActiveRow();
		    
		    //排除编辑的行和自身进行比较
		    if( tmpRow.rowIndex == row.rowIndex ) {
		    	continue;
		    }
		        		        
    		var cell = datacell.getCell(r,0); //行号，列号都从0开始, 但列也可写单元格的name名称
    		tmpValue = datacell.getCellValue( cell );
        		
                
        		if( value == tmpValue && value != "" ) {
   
        		    alert('<b:message key="roleManager_l_m_alert_roleidExist"/>'); //  <!-- 该角色编号已经存在! -->
        		    //this.activeMe();
					entity = datacell.getEntity(row);
					entity.setProperty("roleId","");   // <!-- 角色编号存在时清空单元格! -->
								
        		   	return false;
        		}   
        }		
		return true;
	}
	/*
	 * 查询输入的角色编号是否和数据库的记录重复
	 */
    function checkDBValue() {
    
        var myAjax = new Ajax("org.gocom.abframe.rights.role.RoleManager.queryRoleAll.biz");
		myAjax.addParam("acRole.roleId",activeCellValue);
		
		myAjax.submit();
		var returnNode = myAjax.getResponseXMLDom();
		if( returnNode ) {
		    if( myAjax.getValue("root/data/retCode") == "0" ) {
		        return true;
		    } else {
		    	alert('<b:message key="roleManager_l_m_alert_roleidExist"/>');  // <!-- 该角色编号已经存在! -->
		    	row = datacell.getActiveRow();
				entity = datacell.getEntity(row);
				entity.setProperty("roleid","");
		        return false;
		    }
		}
    } 
    /*
	 * 提交之后获取逻辑流返回值
	 */
    function initDatacellAfterSubmit() {
	    datacell.afterSubmit = function( ajax ) {
	        var retCode = ajax.getValue("root/data/flag");
	        if( retCode == "1" ) {
	            alert( '<b:message key="l_m_save_success"/>' ); // <!--  保存成功 -->
	        } else {
	            alert( '<b:message key="l_m_save_fail"/>' );    //     <!-- 保存失败 --> 
	        }
	        //保存后，再刷新功能列表
	        //querySubmit( funcDatalcell );//方法无法找到，不知道是什么功能 耿大伟
	        
	    }
	    datacell.afterRefresh  = function() {
	        //初始化页面按钮样式
            initButtonStyle();
             
	    } 
    }
    /*
     * 初始化按钮样式以及调用各初始化函数
     */ 
    function custInit() {
		hideProgressBar("doAction");
		unMaskTop();
    	datacell = $id("roleListCell");
        initDatacellAfterSubmit();
        initButtonStyle();
		/*
		 * 验证角色编号时候有重复记录

			$id("roleListCell").afterEdit = function () {
			    var activeCell = datacell.getActiveCell();//获取活动单元格
			    activeCellValue = datacell.getCellValue( activeCell );//获取活动单元格的值
			    
			    if( activeCell != null  ) {
				    var cellIndex = activeCell.cellIndex;
				    if( cellIndex == 0  ) {
				        if( checkInputValue(activeCellValue) && checkDBValue()  )  {
				            return true;
				        } else {
				            return false;
				        }
				    } 
			    }
			     
			}
		 */
    }

     //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
	/**
	* 权限不足的不允许编辑
	**/
	$id("roleListCell").beforeEdit=function(cell,colIndex,rowIndex){
		
		var entity = this.getEntityByCell(cell);
	
/*
		if(entity.getProperty("roletype")=="1" 
		   && ($name("roletype").value!="1"
		   || $name("roleReadonly").value!="0")){
			return false;
		}

		if(colIndex==4 && entity.getProperty("roletype")!="0"){
			return false;
		}
*/
		if($name("roletype").value!="1"
		   && (entity.getProperty("roletype")=="1")
		   && ($name("roleReadonly").value!="0")){
			return false;
		}
	}
/*	
	//替换掉 应用级
	$id("roleListCell").afterRefresh=function(){
		var crc = this.getCurrentRowCount();
		for(var i=0;i<crc;i++){
			var cell = this.getCell(i,2);
			if(cell.innerText=='0'){
				cell.innerText = "应用级";
			}
		}
	}
*/
</script>
<script>

<%--	window.onbeforeunload = function(event){
		var dataCell = $o("roleListCell");
    	if(dataCell.isModefied){
			event = event || window.event;
//			event.returnValue = "［角色列表已修改但未保存！］";
			event.returnValue = false;
//			event.cancelBubble=true;
//			alert("数据已修改，但未提交，要离开当前页面吗?");
    	}
	}--%>


Dataset.prototype.getModifiedEntities = function() {
	var C = [];
	for (var A = 0; A < this.values.length; A++) {
		var B = this.values[A];
		if (B.status == Entity.STATUS_MODIFIED)
			C.push(B)
	}
	return C
};
$id("roleListCell").afterEdit = function (newValue,oldValue) {
/*
	var cellIndex = this.activeCell.cellIndex;
	if(cellIndex==2){
		var entity = this.getEntityByCell(this.activeCell);
		var roletype = entity.getProperty('roletype');
		if(roletype!=0){
			entity.setProperty('auto',null);
		}
	}
*/
}
function cleanRole(){
	var myAjax = new Ajax("/tyjg/roleprivilege/roleAction_reloadAuth.action");
	myAjax.submit();
	var mat = myAjax.getText();
	alert(mat);
}
</script>
</body>
</html>