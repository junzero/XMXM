<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
<%
	//获取标签中使用的国际化资源信息
	String groupQuery=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_groupQuery");
	String selectPrompt=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("groupQuery_l_selectPrompt");
	String groupList=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("groupQuery_l_groupList");

 %>
<title><b:message key="workgroupManager_l_groupQuery" /> </title>
<script>
  function addRecord() {
    var g = $id("group1");
    var frm = $name("viewlist1");
    frm.elements["_eosFlowAction"].value = "addRecord";
    frm.submit();
  }
  function modiRecord() {
    var g = $id("group1");
    if (g.getSelectLength() != 1) {
      alert("<b:message key='l_m_alert_mustAdnOnlySelectOne' />");
      return;
    }
    var frm = $name("viewlist1");
    frm.elements["_eosFlowAction"].value = "modiRecord";
    frm.submit();
  }
  function delRecord() {
    var g = $id("group1");
    if (g.getSelectLength() < 1) {
      alert("<b:message key='l_m_alert_mustSelectOneOrMore' />");
      return;
    }
    var frm = $name("viewlist1");
    frm.elements["_eosFlowAction"].value = "delRecord";
    frm.submit();
  }
  
 //实现全选复选框
 function checkSelectAll(){
	if ($id("checkSelect").checked){
		selectAll("group1");
	}else{
		selectNone("group1");
	}
 }   
  
 /* load初始化函数
  *
  */
 function custInit(){  
	initButtonStyle();
 }  
</script>
</head>
<body leftmargin="0" topmargin="0">

<h:form name="form1" action="org.gocom.abframe.org.workgroup.GroupQuery.flow" checkType="blur" method="post" onsubmit="return checkForm(this);">
  <input type="hidden" name="_eosFlowAction" value="pageQuery"/>
  <w:panel id="panel1" width="100%" title="<%=groupQuery%>">
    <table align="center" border="0" width="100%" class="form_table" >
      <tr>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.groupname" /><b:message key="l_colon"/>
        </td>
        <td colspan="1">
          <h:text property="criteria/_expr[1]/groupname" maxlength="50" validateAttr="maxLength=50" />
          <h:hidden property="criteria/_expr[1]/_op" value="like"/>
          <h:hidden property="criteria/_expr[1]/_likeRule" value="all"/>
        </td>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.grouptype" /><b:message key="l_colon"/>
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_GROUPTYPE" nullLabel="<%=selectPrompt%>" style="width:133" property="criteria/_expr[2]/grouptype"/>
          
          <h:hidden property="criteria/_expr[2]/_op" value="="/>
        </td>
      </tr>
      <tr>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.groupstatus" /><b:message key="l_colon"/>
        </td>
        <td colspan="3">
          <d:select dictTypeId="ABF_GROUPSTATUS" nullLabel="<%=selectPrompt%>" style="width:133" property="criteria/_expr[3]/groupstatus"/>
          <h:hidden property="criteria/_expr[3]/_op" value="="/>
        </td>
      </tr>
      <tr class="form_bottom">
        <td colspan="4" class="form_bottom">
          <input type="hidden" name="criteria/_entity" value="org.gocom.abframe.datasetExp.organization.OmGroup">
          <b:message key="l_pageDisplay" />
          <h:text size="2" property="page/length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
          <input type="hidden" name="page/begin" value="0">
          <input type="hidden" name="page/isCount" value="true">
          <input type="submit" value="<b:message key="l_query" />">&nbsp;
              <input type="button" value='<b:message key="l_reset"></b:message>' onclick="javascript:$name('form1').reset();">
        </td>
      </tr>
    </table>
  </w:panel>
  <h:hidden property="criteria/_orderby[1]/_sort" value="asc"/>
  <h:hidden property="criteria/_orderby[1]/_property" value="grouplevel"/>
  <h:hidden property="criteria/_orderby[2]/_sort" value="asc"/>
  <h:hidden property="criteria/_orderby[2]/_property" value="groupid"/>
</h:form>
<h:form name="viewlist1" action="org.gocom.abframe.org.workgroup.GroupQuery.flow" checkType="blur" target="_self" method="post">
  <input type="hidden" name="_eosFlowAction" value="pageQuery" >
  <h:hiddendata property="criteria" />
  <h:hiddendata property="page" />
  <w:panel id="list_panel" width="100%" title="<%=groupList%>">
    <table align="center" border="0" width="100%" class="EOS_table">
      <tr>
        <th align="center">
          <l:greaterThan property="page/size" targetValue="0" compareType="number" >
	      	<input  type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll();"> 
	      </l:greaterThan>         
          <b:message key="l_select" />
        </th>
        <th>
          <b:message key="workgroupManager_OmGroup.groupname" />
        </th>      
        <th>
          <b:message key="workgroupManager_OmGroup.orgid(Organization.orgname)" />
        </th>        
        <th>
          <b:message key="workgroupManager_OmGroup.grouptype" />
        </th>
        <th>
          <b:message key="workgroupManager_OmGroup.groupstatus" />
        </th>

        <th>
          <b:message key="workgroupManager_OmGroup.startdate" />
        </th>
        <th>
          <b:message key="workgroupManager_OmGroup.enddate" />
        </th>
      </tr>
      <w:checkGroup id="group1">
        <l:iterate property="groups" id="id1">
          <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
            <td align="center">
              <w:rowCheckbox>
                <h:param name='selectedObjects/groupid' iterateId='id1' property='groupid' indexed='true' />
              </w:rowCheckbox>
            </td>
            <td>
              <b:write iterateId="id1" property="groupname"/>
            </td>
            <td>
              <b:write iterateId="id1" property="Organization/orgname"/>
            </td>                       
            <td>
              <d:write dictTypeId="ABF_GROUPTYPE" iterateId="id1" property="grouptype"/>
            </td>
            <td>
              <d:write dictTypeId="ABF_GROUPSTATUS" iterateId="id1" property="groupstatus"/>
            </td>

            <td>
              <b:write iterateId="id1" property="startdate" formatPattern="yyyy-MM-dd"/>
            </td>
            <td>
              <b:write iterateId="id1" property="enddate" formatPattern="yyyy-MM-dd"/>
            </td>
          </tr>
        </l:iterate>
      </w:checkGroup>
      <tr>
      <tr>
        <td colspan="18" class="command_sort_area" align="right">
        <div class="h3">
            <l:equal property="page/isCount" targetValue="true">
              <b:message key = 'l_total'/>       <!--共 -->
              <b:write property="page/count"/>
              <b:message key = 'l_recordNO.'/>   <!--条记录 第 -->
              <b:write property="page/currentPage"/>
              <b:message key = 'l_page'/>/       <!--页 -->
              <b:write property="page/totalPage"/>
              <b:message key = 'l_page'/>        <!--页 -->
            </l:equal>
            <l:equal property="page/isCount" targetValue="false">
              <b:message key = 'l_NO.'/>         <!--第 -->
              <b:write property="page/currentPage"/>
              <b:message key = 'l_page'/>        <!--页 -->
            </l:equal>
            <!--首页 上页 下页 尾页 -->
            <input type="button" onclick="firstPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_firstPage'/>"  <l:equal property="page/isFirst" targetValue="true"  >disabled</l:equal> >
            <input type="button" onclick="prevPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_upPage'/>" <l:equal property="page/isFirst" targetValue="true"  >disabled</l:equal> >
            <input type="button" onclick="nextPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_nextPage'/>" <l:equal property="page/isLast" targetValue="true"  >disabled</l:equal> >
            <l:equal property="page/isCount" targetValue="true"  >
              <input type="button" onclick="lastPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_lastPage'/>" <l:equal property="page/isLast" targetValue="true"  >disabled</l:equal> >
            </l:equal>
         </div>
        </td>
      </tr>
    </table>
  </w:panel>
</h:form>

</body>
</html>

<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
</script>