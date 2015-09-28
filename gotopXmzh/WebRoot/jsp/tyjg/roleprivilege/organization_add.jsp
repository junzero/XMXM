<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
<title></title>
<%
	//机构查询条件
	String org_queryCond = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_title_organization")
					 + com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_queryCond");
	//机构查询结果
	String organList = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_title_organization")
					 + com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_query_result");
%>
<script type="text/javascript">
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
	 * 增加机构
	 */
	function addRecord()
	{
		var g = $id("group1");
		var rows = g.getSelectRows();
	    if (rows.length < 1) {
	      alert('<b:message key="l_m_alert_mustSelectOneOrMore"/>'); 
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
    var myAjax = new Ajax("/tyjg/roleprivilege/insertOmPartyRole_roleprivilege.action");
    myAjax.addParam("strRoleIds",strRoleIds);
    myAjax.addParam("strPartyTypes",strPartyTypes);
	myAjax.addParam("strPartyIds",strPartyIds);
	myAjax.submit();
		 var rtun = myAjax.getValue("root/data/flag");
		 if(rtun == "1"){
		     alert("角色机构新增成功！");
		  	 //var frm = $name("viewlist1");
		  	 //frm.submit();
		  	 
		  	 var subButton = document.getElementById("subButton");
		  	 subButton.click();
		 }else{
		  	 alert("角色机构新增失败！");
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
<body class="eos-panel-table">
<form name="form1" action="/tyjg/roleprivilege/toInsertOmPartyRole_roleprivilege.action" method="post" onsubmit="return checkForm(this);">
  <h:hidden property="acRole.roleId"/>
  <h:hidden property="acRole.auto"/>
  <h:hidden property="acRole.roleType"/>
    <table align="center" border="0" width="100%" class="form_table">
      <tr>
        <td class="form_label" align="right">
          机构代码<b:message key="l_colon"/>
          <!--机构代码-->
        </td>
        <td colspan="1">
          <h:text property="omPartyRole.orgCode"/>
        </td>
        <td class="form_label" align="right">	<!-- 机构名称 -->
          <b:message key="roleManager_OmOrganization.orgname"/><b:message key="l_colon"/>
        </td>
        <td colspan="1">
          <h:text property="omPartyRole.orgName"/>
        </td>
      </tr>
      <tr class="form_bottom">
        <td colspan="4" class="form_bottom">
          <b:message key="l_pageDisplay"/>
          <h:text size="2" property="page.length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
          <input type="hidden" name="page.begin" value="0">
          <input type="hidden" name="page.isCount" value="true">
          <input class="button" id="subButton" type="submit" value="<b:message key="l_query"/>">&nbsp;
          <input class="button" type="button" value='<b:message key="l_reset"></b:message>' onclick="javascript:$name('form1').reset();">
        </td>
      </tr>
    </table>
</form>
<form name="viewlist1" action="/tyjg/roleprivilege/toInsertOmPartyRole_roleprivilege.action" method="post">
  <h:hidden property="acRole.roleId"/>
  <h:hidden property="acRole.auto"/>
  <h:hidden property="acRole.roleType"/>
  <h:hidden property="page.begin"/>
  <h:hidden property="page.length"/>
  <h:hidden property="page.count"/>
    <table align="center" border="0" width="100%" class="EOS_table">
      <tr height="4%">
		<td class="eos-panel-title" colspan="6">&nbsp;&nbsp;<%=organList%></td>
	  </tr>
      <tr>
        <th align="center">
         <l:greaterThan property="page.size" targetValue="0" compareType="number">
	        <input type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll();"> 
	     </l:greaterThan><b:message key="l_select"/>	<!-- 选择 -->
        </th>
        <th>	<!-- 机构名称 -->
          机构代码
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
          <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
            <td align="center">
              <w:rowCheckbox afterSelectFunc="" afterUnSelectFunc="">
				<h:param name='partyId' iterateId='id1' property='orgId' indexed='true' />
				<h:param name='partyType' value='organization'  indexed='true' />
				<h:param name='roleId' property="acRole.roleId" indexed='true'/>
              </w:rowCheckbox>
            </td>
            <td>
              <b:write iterateId="id1" property="orgCode"/>
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
          </tr >
        </l:iterate>
      </w:checkGroup>
      <tr>
        <td colspan="6" class="command_sort_area">
         <div class="h3"> 
          <input class="button" type="button" value="<b:message key = 'l_add'/>" onclick="addRecord();">
          <input class="button" type="button" value="<b:message key="l_close"/>" onclick="window.closeD()">
         </div>
         <div class="h4">
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
</script>