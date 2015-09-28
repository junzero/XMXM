<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
<title></title>
<%	
	//机构列表
	String organList = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_organList");
	
%>
<script>
  /*
   * 新增机构后的回调函数
   */
  function callBack() {
        var frm = $name("viewlist1");
        frm.submit();
  }
  /*
   * 增加机构
   */
  function addRecord() {
    	var roleReadonly = $name('roleReadonly').value;
    	var url = "";
    	var auto = $name("acRole.auto").value;
    	var roleid = $name("acRole.roleId").value;
    	var roletype = $name("acRole.roleType").value;
    	if(roletype==0 && auto==3){
    		auto=4;
    	}
    	if(roleReadonly==0){
	        var url = "/tyjg/roleprivilege/toInsertOmPartyRole_roleprivilege.action";
	        url += "?acRole.roleId=" + roleid;
	        url += "&acRole.auto="+auto;
	        url += "&acRole.roleType="+roletype;
	        url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
    	}else{
			var url = "com.primeton.purview.AcOrganSelect.flow";
	        url += "?_eosFlowAction=query";
	        url += "&acRole/roleid=";
	        url += "&acRole/auto="+auto;
	        url += "&acRole/roletype="+roletype;
			url += '&_ts='+(new Date()).getTime(); //防止IE缓存，在每次打开时加个时间差的参数
    	}
	    showModalCenter(url, "", callBack, 700, 400, '<b:message key="roleManager_l_addOrganization"/>');
  }
  /*
   * 删除机构
   */
  function delRecord() {
    var g = $id("group1");
    var rows = g.getSelectRows();
    if (rows.length < 1) {
      alert('<b:message key="l_m_alert_mustSelectOneOrMore"/>');  //<!-- 至少选择一行！ -->
      return;
    }
    if( !confirm('<b:message key="roleManager_l_m_alert_confirmDeleteOrgan"/>' ) ) {
      return;
    }
    var strRoleIds = "";
    var strPartyTypes = "";
    var strPartyIds = "";
    for(var i=0; i<rows.length; i++) {
        if(rows[i].getParam("roleId") != null && rows[i].getParam("roleId") != "") {
            strPartyTypes = (strRoleIds == "") ? rows[i].getParam("partyType") : strPartyTypes + "," + rows[i].getParam("partyType");
            strPartyIds = (strRoleIds == "") ? rows[i].getParam("partyId") : strPartyIds + "," + rows[i].getParam("partyId");
            strRoleIds = (strRoleIds == "") ? rows[i].getParam("roleId") : strRoleIds + "," + rows[i].getParam("roleId");
        }
    }
    if(strRoleIds == "") {
        return;
    }
    var myAjax = new Ajax("/tyjg/roleprivilege/deleteOmPartyRole_roleprivilege.action");
    myAjax.addParam("strRoleIds",strRoleIds);
    myAjax.addParam("strPartyTypes",strPartyTypes);
	myAjax.addParam("strPartyIds",strPartyIds);
	myAjax.submit();
		 var rtun = myAjax.getValue("root/data/flag");
		 if(rtun == "1"){
		     alert("角色机构删除成功！");
		  	 var frm = $name("viewlist1");
		  	 frm.submit();
		 }else{
		  	 alert("角色机构删除失败！");
		 }
  }
  
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
   *  自定义初始化按钮样式
   */
  function custInit(){  
	initButtonStyle();       
  }
</script>
</head>
<body leftmargin="0" topmargin="0">
<form name="viewlist1" action="/tyjg/roleprivilege/toDistributeOrgRole_roleprivilege.action" method="post">
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
        <th>	<!-- 机构名称 -->
          <b:message key="roleManager_OmOrganization.orgname"/>
        </th>
        <th>	<!-- 机构类别 -->
          <b:message key="roleManager_OmOrganization.orgtype"/>
        </th>
        <th>	<!-- 机构状态 -->
          <b:message key="roleManager_OmOrganization.status"/>
        </th>
        <th>	<!-- 所属地域 -->
          <b:message key="roleManager_OmOrganization.area"/>
        </th>
      </tr>
      <w:checkGroup id="group1">
        <l:iterate property="omPartyRoleList" id="id1">
          <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />">
            <td align="center">
              <w:rowCheckbox afterSelectFunc="clickCheck($id('group1'), null, $id('deleteButton'))" afterUnSelectFunc="clickCheck($id('group1'), null, $id('deleteButton'))">
				<h:param name='roleId' iterateId='id1' property='roleId' indexed='true' />
				<h:param name='partyType' iterateId='id1' property='partyType' indexed='true' />
				<h:param name='partyId' iterateId='id1' property='partyId' indexed='true' />
              </w:rowCheckbox>
            </td>
            <td>
              <b:write iterateId="id1" property="orgName"/>
            </td>
            <td>
              <d:write dictTypeId="ABF_ORGTYPE" iterateId="id1" property="orgType"/>
            </td>
            <td>
              <d:write dictTypeId="ABF_ORGSTATUS" iterateId="id1" property="status"/>
            </td>
            <td>
              <b:write iterateId="id1" property="area"/>
            </td>
          </tr>
        </l:iterate>
      </w:checkGroup>
      <tr>
        <td colspan="5" class="command_sort_area" nowrap="nowrap">
        <div class="h3">
          <input class="button" type="button" value="<b:message key = 'l_add'/>" onclick="addRecord();">
          <l:greaterThan property="page.size" targetValue="0" compareType="number">
            <input class="button" type="button" value="<b:message key = 'l_delete'/>" onclick="delRecord();">
          </l:greaterThan>
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