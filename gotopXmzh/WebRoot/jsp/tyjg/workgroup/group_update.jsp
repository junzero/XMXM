<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
<%
	//获取标签中使用的国际化资源信息
	String title=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("groupUpdate_l_title_group_update");
	String selectOrg=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("groupUpdate_l_selectOrg");
 %>
<title><%=title%></title> <!--工作组录入 -->
<script>
     /* load初始化函数
      *
      */
	 function custInit(){  
		initButtonStyle();
	 }
</script>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0">
<%--dataform configurationID:20080820194550 --%>
<h:form name="dataform1" action="org.gocom.abframe.org.workgroup.GroupUpdate.flow" checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
  <h:hidden property="group/groupid" />
  <input type="hidden" name="_eosFlowAction" value="update"/>
  <w:panel id="panel1" width="100%" title="<%=title%>"><!--工作组录入 -->
    <table align="center" border="0" width="100%" class="form_table">
      <tr>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.groupname" /><b:message key="l_colon"></b:message> <!--工作组名称 -->
        </td>
        <td colspan="1">
          <h:text  property="group/groupname" maxlength="50" validateAttr="allowNull=false;maxLength=50"/>
        </td>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.manager" /><b:message key="l_colon"></b:message> <!--负责人 -->
        </td>
        <td colspan="1">
          <h:text property="group/manager" maxlength="30" validateAttr="maxLength=30" />
        </td>
      </tr>
      <tr>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.orgid(Organization.orgname)" /><b:message key="l_colon"></b:message> <!--隶属机构 -->
        </td>
        <td colspan="1">																										<!--选择隶属机构 -->
          <w:lookup property="group/omOrganization/orgid"   readonly="true" displayProperty="group/omOrganization/orgname"  dialogTitle="<%=selectOrg%>"  width="300" height="450" lookupUrl="org/org/org_tree_select.jsp" ></w:lookup>
        </td>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.grouptype" /><b:message key="l_colon"></b:message> <!--工作组类型 -->
        </td>
        <td colspan="1">
          <d:select  dictTypeId="ABF_GROUPTYPE" style="width:133" property="group/grouptype" />
        </td>
      </tr>
      <tr>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.groupstatus" /><b:message key="l_colon"></b:message> <!--工作组状态 -->
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_GROUPSTATUS" style="width:133" property="group/groupstatus"/>
        </td>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.updator" /><b:message key="l_colon"></b:message> <!--最近更新人员 -->
        </td>
        <td colspan="1">
          <h:text  property="group/updator" readonly="true"/>
        </td>
      </tr>
      <tr>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.startdate" /><b:message key="l_colon"></b:message> <!--有效开始日期 -->
        </td>
        <td colspan="1">
          <w:date property="group/startdate"/>
        </td>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.enddate" /><b:message key="l_colon"></b:message> <!--有效截止日期 -->
        </td>
        <td colspan="1">
          <w:date property="group/enddate" readonly="true" />
        </td>
      </tr>
      <tr>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.createtime" /><b:message key="l_colon"></b:message> <!--创建时间 -->
        </td>
        <td colspan="1">
          <input type="text" readonly="readonly" name="group/createtime" value='<b:write property="group/createtime" formatPattern="yyyy-MM-dd"/>'>
        </td>
        <td class="form_label">
           <b:message key="workgroupManager_OmGroup.lastupdate" /><b:message key="l_colon"></b:message> <!--最近更新时间 -->
        </td>
        <td colspan="1">
          <input type="text" readonly="readonly" name="group/lastupdate" value='<b:write property="group/lastupdate" formatPattern="yyyy-MM-dd"/>'>
        </td>
      </tr>
      <tr>
        <td class="form_label">
          <b:message key="workgroupManager_OmGroup.groupdesc" /><b:message key="l_colon"></b:message> <!--工作组描述 -->
        </td>
        <td colspan="3">
          <h:textarea  property="group/groupdesc" style="width:350" rows="3"  validateAttr="maxLength=512" />
        </td>
      </tr>
      <tr class="form_bottom">
        <td colspan="6">
          <input type="submit" value="<b:message key="l_save" />"> <!--保存 --> 
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