<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
<%--<h:script src="/common/gotop/showModal_patch.js" />--%>
<title></title>
<script>
	/*
	 * 实现全选复选框
	 */
	function checkSelectAll(){
		if ($id("checkSelect").checked){
			selectAll("group1");
		}else{
			selectNone("group1");
		}
	}
    /*
     * 新增操作员后的回调函数
     */
    function callBack(arg) {
    	if(arg){}else{
			return;
    	}
        var strEmpIds = "";
        var strEmpNames = "";
        if(arg['Employee']){ //原写法无需判断是否为空
	  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
	  		argRes=[[],[],[],[]];
			for(var i=0;i<sorgidArra.length;i++){
				argRes[0].push(sorgidArra[i].getProperty("empid"));
				argRes[1].push(sorgidArra[i].getProperty("empname"));
			}
			strEmpIds = argRes[0];
			strEmpNames = argRes[1];
    	}else{
			strEmpIds = "";
			strEmpNames = "";
    	}
    	var myAjax = new Ajax("/tyjg/roleprivilege/saveOperatorRole_roleprivilege.action");
    	myAjax.addParam("strEmpIds", strEmpIds);
    	myAjax.addParam("opFlag", opFlag);
    	myAjax.addParam("acRole.roleId", $name("acRole.roleId").value);
    	myAjax.addParam("acRole.roleType", $name("acRole.roleType").value);
    	myAjax.submit();
    	var flag =myAjax.getValue("root/data/flag");
    	if(flag != "1"){
    	    alert('更新人员失败!');
		    initButtonStyle();   
			hideProgressBar("doAction");
    		
    	}else{
    	    alert('更新人员成功!');
	        var frm = $name("viewlist1");
	        frm.submit();
    	}
    }
    var opFlag = null;
    /*
     * 增加操作员
     */
    function addRecord() {
    
    	var roleReadonly = $name('roleReadonly').value;
    	var url = "";
    	var roleid = $name("acRole.roleId").value;
    	var auto = $name("acRole.auto").value;
    	var roletype = $name("acRole.roleType").value;
    	if(roletype==0 && auto==3){
    		auto=4;
    	}
    	if(roleReadonly==0){
    	    url = "/tree/initMainTree_mainTree.action";
    	    url += "?changeTree.showTabOrg=1";
    	    url += "&changeTree.orgType=6";
    	    url += "&changeTree.showSelBox=5";
    	    url += "&changeTree.roleids=" + roleid;
	        url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
    	}else{
			var url = "com.primeton.purview.AcOperatorSelect.flow";
	        url += "?_eosFlowAction=query";
	        url += "&acRole/roleid=<b:write property="acRole/roleid"/>";
	        url += "&acRole/roletype="+roletype;
//	        url += '<b:write property="acRole/roleid"/>';
	        url += "&acRole/auto="+auto;
			url += '&_ts='+(new Date()).getTime(); //防止IE缓存，在每次打开时加个时间差的参数
    	}
    	var myAjax = new Ajax("/tyjg/roleprivilege/queryOperatorRoleEmp_roleprivilege.action");
    	myAjax.addParam("acRole.roleId", roleid);
    	myAjax.submit();
    	var flag = myAjax.getValue("root/data/flag");
    	var strEmpIds = "";
    	var strEmpNames = ""; 
    	if(flag == "1") {
    	    strEmpIds = myAjax.getValue("root/data/empIds");
    	    strEmpNames = myAjax.getValue("root/data/empNames");
    	}
    	opFlag = flag;
    	var peArgument = [];
    	var paramEntity = new ParamEntity('Employee');
    	paramEntity.setProperty('empid', strEmpIds);			
    	paramEntity.setProperty('empname', strEmpNames);
		peArgument[0]=[paramEntity,'empname','empid'];
	    showModalCenter(url, peArgument, callBack, 500, 430, '<b:message key="roleManager_l_addOperator"/>');
    }
    /*
     * 删除操作员
     */
    function delRecord() {
         var g = $id("group1");
         var rows = g.getSelectRows();
         if (rows.length < 1) {
            alert('<b:message key="l_m_alert_mustSelectOneOrMore"/>'); // <!-- 至少选择一行！ -->
            return;
         }
         if( !confirm('<b:message key="roleManager_l_m_alert_confirmDeleteOperator"/>') || !testjs(g)) {
             return;
         }
         var strOperatorIds = "";
         var strRoleIds = "";
         var strAgencys = "";
         for(var i=0; i<rows.length; i++) {
             if(rows[i].getParam("operatorid") != null && rows[i].getParam("operatorid") != "") {
                 strRoleIds = (strOperatorIds == "") ? rows[i].getParam("roleid") : strRoleIds + "," + rows[i].getParam("roleid");
                 strAgencys = (strOperatorIds == "") ? rows[i].getParam("agency") : strAgencys + "," + rows[i].getParam("agency");
                 strOperatorIds = (strOperatorIds == "") ? rows[i].getParam("operatorid") : strOperatorIds + "," + rows[i].getParam("operatorid");
             }
         }
         var myAjax = new Ajax("/tyjg/roleprivilege/deleteOperatorRole_roleprivilege.action");
		 myAjax.addParam("strOperatorIds",strOperatorIds);
		 myAjax.addParam("strRoleIds",strRoleIds);
		 myAjax.addParam("strAgencys",strAgencys);
		 myAjax.submit();
		 var rtun = myAjax.getValue("root/data/flag");
		 if(rtun == "1"){
		     alert("角色人员删除成功！");
		  	 var frm = $name("viewlist1");
		  	 frm.submit();
		 }else{
		  	 alert("角色人员删除失败！");
		 }
     }
	/*
	*  自定义初始化按钮样式
	*/
	function custInit(){  
	    initButtonStyle();   
		hideProgressBar("doAction");
		unMaskTop();
	}
	
	function testjs(g){
		if(g){}else{
			g = $id('group1');
		}
        var res = g.getSelectRows();
        var retStr = ",";
        for(i=0;res!=null && i<res.length;i++){
        	retStr += res[i].getParam("operatorid")+",";
        }
        if(retStr.indexOf(',1,')>-1){
        	if(!confirm('你正在删除超级管理员，强行删除可能会影响系统应用！是否继续？')){
        		return false;
        	}
        }
        return true;
	}
</script>
</head>
<body leftmargin="0" topmargin="0">
	<form name="viewlist1" action="/tyjg/roleprivilege/toDistributeOperatorRole_roleprivilege.action" method="post">
		<w:panel width="100%" id="roleQuery" title="查询条件">
			<table border="0" width="100%" class="form_table" cellpadding="0" cellspacing="0">
				<tr>
					<td class="form_label"><nobr>所属机构</nobr></td>
					<td>
	        			<w:lookup id="wl_mngorg" property="acRole.orgId"  readonly="true" 
	        					displayProperty="acRole.orgName" style="hidden=ture;" width="500" height="430" lookupWidth="380"
					        	lookupUrl="/tree/initMainTree_mainTree.action?startOrgid=${sessionScope.login_user.orgid}&changeTree.showTabOrg=1&changeTree.checkcount=1&changeTree.orgType=4&changeTree.showSelBox=4&changeTree.lookupTypeStr=4"
					        	dialogTitle="查询机构" onReturnFunc="returnFuncOrgLuss">
						</w:lookup>
						<input id="findOrgid" name="findOrgid" type="hidden">
					</td>
					<td>
						<input calss="button" type="button" style="width:40" value="查询" onclick="javascript:queryRoleUserByOrg()">
					</td>
				</tr>
			</table>
		</w:panel>
	  <h:hidden property="acRole.roleId"/>
	  <h:hidden property="acRole.roleType"/>
	  <h:hidden property="acRole.auto"/>
	  <h:hidden property="page.begin"/>
	  <h:hidden property="page.length"/>
	  <h:hidden property="page.count"/>
	  <h:hidden property="roleReadonly"/>
	  
	    <table align="center" border="0" width="100%" class="EOS_table">
	      <tr>
	        <th align="center">
	          <l:greaterThan property="page.size" targetValue="0" compareType="number">
		        <input  type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll();"> 
		      </l:greaterThan><b:message key="l_select"/>
	        </th>
	        <th>	<!-- 登录名称 -->
	          <b:message key="roleManager_OmEmployee.userid"/>
	        </th>
	        <th>	<!-- 操作员名称 -->
	          <b:message key="roleManager_OmEmployee.empname"/>
	        </th>
	        <th>	<!-- 所属机构 -->
	          <b:message key="roleManager_OmEmployee.orgid(orgname)"/>
	        </th>
	      </tr>
	      <w:checkGroup id="group1">
	        <l:iterate property="acOperatorRoleList" id="id1">
	          <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
	            <td align="center">
	              <w:rowCheckbox afterSelectFunc="" afterUnSelectFunc="">
					<h:param name='operatorid' iterateId='id1' property='operatorId' indexed='true' />
					<h:param name='roleid' value="${param['acRole.roleId']}" indexed='true'/>			
					<h:param name='agency' value="${param['acRole.roleType']}" indexed='true'/>			
	              </w:rowCheckbox>
	            </td>
	            <td>
	              <b:write iterateId="id1" property="userId"/>
	            </td>
	            <td>
	              <b:write iterateId="id1" property="empName"/>
	            </td>
	            <td>
	              <b:write iterateId="id1" property="orgName"/>
	            </td>
	          </tr>
	        </l:iterate>
	      </w:checkGroup>
	      <tr>
	        <td colspan="4" class="command_sort_area" nowrap="nowrap">
	          <div class="h3">
	          	<input class="button" type="button" value="<b:message key = 'l_add'/>" onclick="addRecord();">
	            <input class="button" type="button" value="<b:message key = 'l_delete'/>" onclick="delRecord();">
	          
		        <l:equal property="page.isCount" targetValue="true">
		          <b:message key="l_total"/>
		          <b:write property="page.count"/>
		          <b:message key="l_recordNO."/>
		          <b:write property="page.currentPage"/>
		          <b:message key="l_page"/>/
		          <b:write property="page.totalPage"/>
		          <b:message key="l_page"/>
		        </l:equal>
		        <l:equal property="page.isCount" targetValue="false">
		          <b:message key="l_NO."/>
		          <b:write property="page.currentPage"/>
		          <b:message key="l_page"/>
		        </l:equal>
		        <input class="button" type="button" onclick="firstPage('page', '', null, null, 'viewlist1');" value="<b:message key="l_firstPage"/>"  <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
		        <input class="button" type="button" onclick="prevPage('page', '', null, null, 'viewlist1');" value="<b:message key="l_upPage"/>" <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
		        <input class="button" type="button" onclick="nextPage('page', '', null, null, 'viewlist1');" value="<b:message key="l_nextPage"/>" <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
		        <l:equal property="page.isCount" targetValue="true">
		          <input class="button" type="button" onclick="lastPage('page', '', null, null, 'viewlist1');" value="<b:message key="l_lastPage"/>" <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
		        </l:equal>     
		      </div>                                                                     
	        </td>
	      </tr>
	    </table>
	</form>
</body>
</html>
<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
     
	function returnFuncOrgLuss(arg){//如果点击右上角关闭则不进入该方法    
		var orgid = "";
		var orgname = "";
		if(arg==null || arg.length<2 || arg[0].length<1){
		
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
	
	function queryRoleUserByOrg(){
		var frm = $name("viewlist1");
		var wmng = $id("wl_mngorg").getValue();
		$name("acRole.orgId").value=wmng;
	    frm.submit();
	}
</script>