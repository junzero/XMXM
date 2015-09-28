<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      
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
	String application_query = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_applicationQuery");
    String select = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_query_select");
    String application_list = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_applicationList");
	String application_add = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_applicationAdd");
	String application_update = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_applicationUpdate");
	%>
	<script type="text/javascript">
  	// 新增应用
  	function addRecord() {
		var url = "/jsp/tyjg/menumanagement/add_application.jsp?_dates="+ new Date();
    	showModalCenter(url, "", callBackTypeAdd, 500, 420, '<%=application_add%>');//增加应用
  	}
	//新增完成回调函数
	function callBackTypeAdd() {
    	$id("btn").click();
    	//window.parent.parent.parent.frames["IFRAMEAppFuncTree"].DTree.getRootNode().reloadChild();
    }
	// 删除应用
  	function delRecord() {
	    var g = $id("groupArr");
	    var rows = g.getSelectRows();
	    if (g.getSelectLength() < 1) {
	      alert('<b:message key="m_delete_illegalSelect"/>');
	      return;
	    }
	    var frm = $name("app_viewlist");
	    if(confirm('删除应用系统会同时删除角色与菜单的关系、菜单、菜单组信息,<b:message key="applicationManager_m_applicationdelete_confirm"/>')){
	    	var selectRowSize = rows.length;
	    	var selectParam = "";
	    	for(var i = 0; i < selectRowSize; i++){
	    		if(i==0){
	    			selectParam = rows[i].getParam("app_selectedObjects.appid");
	    		}else{
	    			selectParam += "," + rows[i].getParam("app_selectedObjects.appid");
	    		}
	    	}
	    	var myAjax = new Ajax("/menumanagement/menuManagementAction_deleteApp.action");
	    	myAjax.addParam("appids",selectParam);
	    	myAjax.submit();
	    	var returnVal = myAjax.getValue("root/data/isValid");
	    	if(parseInt(returnVal) == 1){
	    		alert("应用系统删除成功");
	    		callBackTypeAdd();
	    	}else{
	    		alert("应用系统删除失败");
	    	}
	    }
  	}

  	/*
   	*  自定义初始化按钮样式
   	*/
  	function custInit(){  
		initButtonStyle();
  	}
  	function allItem(){
		var group =$id("groupArr");
		if(document.getElementById("xuanze").checked){
			group.selectAll();
		}else{
			group.disSelectAll();
		}
	}
</script>
	<h:form name="appQuery" action="/menumanagement/menuManagementAction_querySystemApp.action" method="post">
	<w:panel id="panel1" title="<%= application_query %>">
	  <table align="center" border="0" width="100%" class="form_table">
	    <tr>
	      <td class="form_label">
	        <b:message key="applicationManager_AcApplication.appname"></b:message><b:message key="l_colon"></b:message>
	      </td>
	      <td colspan="1">
	        <h:text property="acApplication.appName"/>
	      </td>
	      <td class="form_label">
	        <b:message key="applicationManager_AcApplication.apptype"></b:message><b:message key="l_colon"></b:message>
	      </td>
	      <td colspan="3">
	        <d:select dictTypeId="ABF_APPTYPE" property="acApplication.apptype" nullLabel="<%= select %>" style="width:133"/>
	      </td>
	    </tr>
	    <tr class="form_bottom">
	      <td colspan="6" class="form_bottom">
	        <b:message key="l_display_per_page"></b:message>
	        <h:text size="2" property="page.length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
	        <input type="hidden" name="page.begin" value="0">
	        <input type="hidden" name="page.isCount" value="true">
	        <input type="submit" id="btn" class="button" value='<b:message key="l_query"></b:message>'>
	      </td>
	    </tr>
	  </table>
	</w:panel>
	</h:form>
	<h:form name="app_viewlist" action="/menumanagement/menuManagementAction_querySystemApp.action" method="post" >
		<h:hidden property="page.begin"/>
	    <h:hidden property="page.length"/>
	    <h:hidden property="page.count"/>
	    <h:hidden property="page.isCount"/>
	    <h:hidden property="acApplication.appName"/>
	    <h:hidden property="acApplication.apptype"/>
  <table align="center" border="0" width="100%" class="EOS_table">
    <tr>
       <td colspan="10" class="eos-panel-title">&nbsp;&nbsp;<%=application_list %></td>
    </tr>
    <tr>
      <th align="center" nowrap="nowrap">
      <l:greaterThan property="page.size" targetValue="0" compareType="number">
        	<h:checkbox id="xuanze" onclick="allItem();"/>
        </l:greaterThan>
      </th>
      <th nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.appname"></b:message>
      </th>
      <th nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.appcode"></b:message>
      </th width="10%">
      <th nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.apptype"></b:message>
      </th>
      <th nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.isopen"></b:message>
      </th>
      <th nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.opendate"></b:message>
      </th>
      <th nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.url"></b:message>
      </th>
      <th nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.ipaddr"></b:message>
      </th>
      <th nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.ipport"></b:message>
      </th>
      <th nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.appdesc"></b:message>
      </th>
    </tr>
    <w:checkGroup id="groupArr">
      <l:iterate property="appList" id="id1">
        <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
          <td align="center" nowrap="nowrap">
          	<w:rowCheckbox>
              <h:param name='app_selectedObjects.appid' iterateId='id1' property='appId' indexed='true' />
            </w:rowCheckbox>
          </td>
          <td nowrap="nowrap">
            <div class="box" title="<b:write iterateId="id1" property="appName"/>"><b:write iterateId="id1" property="appName"/>&nbsp;</div>
          </td>
          <td nowrap="nowrap">
            <b:write iterateId="id1" property="appCode"/>&nbsp;
          </td>
          <td nowrap="nowrap">
            <d:write iterateId="id1" property="apptype" dictTypeId="ABF_APPTYPE"/>&nbsp;
          </td>
          <td nowrap="nowrap">
            <d:write iterateId="id1" property="isOpen" dictTypeId="ABF_YESORNO"/>&nbsp;
          </td>
          <td nowrap="nowrap">
            <b:write iterateId="id1" property="openDate"/>&nbsp;
          </td>
          <td nowrap="nowrap">
            <div class="box" title="<b:write iterateId="id1" property="url"/>"><b:write iterateId="id1" property="url"/>&nbsp;</div>
          </td>
          <td nowrap="nowrap">
            <b:write iterateId="id1" property="ipAddr"/>&nbsp;
          </td>
          <td nowrap="nowrap">
            <b:write iterateId="id1" property="ipPort"/>&nbsp;
          </td>
          <td nowrap="nowrap">
            <div class="box" title="<b:write iterateId="id1" property="appDesc"/>"><b:write iterateId="id1" property="appDesc"/>&nbsp;</div>
          </td>
        </tr>
      </l:iterate>
    </w:checkGroup>
    <tr>
      <td colspan="10" class="command_sort_area">
        <div class="h3">
          <input type="button" class="button" value='<b:message key="l_add"/>' onclick="addRecord();">
          <input type="button" class="button" value='<b:message key="l_update"/>' onclick="modiRecord();" id="updateButton">
          <input type="button" class="button" value='<b:message key="l_delete"/>' onclick="delRecord();" id="deleteButton">
         </div>
         <div class="h4">
          <l:equal property="page.isCount" targetValue="true">
            <b:message key="l_total"/>
            <b:write property="page/count"/>
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
          <input type="button" class="button" onclick="firstPage('page', '', null, null, 'app_viewlist');" value='<b:message key="l_firstPage"/>'  <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
          <input type="button" class="button" onclick="prevPage('page', '', null, null, 'app_viewlist');" value='<b:message key="l_upPage"/>' <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
          <input type="button" class="button" onclick="nextPage('page', '', null, null, 'app_viewlist');" value='<b:message key="l_nextPage"/>' <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
          <l:equal property="page.isCount" targetValue="true">
            <input type="button" class="button" onclick="lastPage('page','', null, null, 'app_viewlist');" value='<b:message key="l_lastPage"/>' <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
          </l:equal>
        </div>
      </td>
    </tr>
  </table>
</h:form>
<script type="text/javascript">
	//修改应用
  	function modiRecord() {
  		var appids = $id("groupArr").getSelectRows();
  		var selectRows = appids.length;
  		if(selectRows == 1){
	  		var appid = appids[0].getParam("app_selectedObjects.appid");
	    	var url = "/menumanagement/menuManagementAction_querySingleApp.action";
			url += "?acApplication.appId=" + appid;
	    	url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	    	//弹出新增业务字典类型模态框
	    	showModalCenter(url, "", callBackTypeAdd, 500, 420, '<%=application_update%>');//修改应用
    	}else if(selectRows > 1){
    		alert("不能同时选择多个应用系统，只能选择一个应用系统进行修改");
    		return;
    	}else{
    		alert("至少选择一个应用系统");
    		return;
    	}
  	}
</script>
  </body>
</html>
