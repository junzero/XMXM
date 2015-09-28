<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      功能组信息
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%
		//获取标签中使用的国际化资源信息
		String function_info = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_function_info");      
	%>
	</head>
	<e:datasource type="bean" name="acFunctionGroup" path="com.gotop.tyjg.menumanagement.model.AcFunctionGroup"/>
	<body leftmargin="0" topmargin="0" >
	<h:form name="funcGroupUpdate_datafrm" action="/menumanagement/menuManagementAction_updateGroupInfo.action" 
	method="post" checkType="blur" onsubmit="return checkForm();">
	<h:hidden property = "acFunctionGroup.funcGroupId"/>
	  <table align="center" border="0" width="100%" class="form_table">
	    <tr>
	       <td colspan="6" class="eos-panel-title">&nbsp;&nbsp;<%=function_info%></td>
	    </tr>
	    <tr>
	      <td class="form_label">
	        <b:message key="applicationManager_AcFuncGroup.funcgroupname"></b:message><b:message key="l_colon"></b:message>
	      </td>
	      <td colspan="1">
	        <h:text property="acFunctionGroup.funcGroupName" validateAttr="allowNull=false;"/><font style="color:red">*</font>
	      </td>
	      <td class="form_label">
	        <b:message key="applicationManager_AcFunction.parentFuncGroup"></b:message><b:message key="l_colon"></b:message>
	      </td>
	      <td colspan="1" width="30%">
	        <b:write property="acFunctionGroup.parentGroupName"/>
	      </td>
	     </tr>
	    <tr>
	      <td class="form_label">
	        显示顺序
	      </td>
	      <td width="30%">
	        <h:text property="acFunctionGroup.displayOrder"/>
	      </td>
	      <td class="form_label">
	        是否为虚拟目录
	      </td>
	      <td width="30%">
	         <d:select property="acFunctionGroup.type" dictTypeId="ABF_ISFLAG"></d:select>
	      </td>
	     </tr>
	     <tr>
	        <td class="form_label" width="20%">
	          功能组调用接口:
	        </td>
	        <td colspan="3">
	          <h:text property="acFunctionGroup.groupAction" validateAttr="allowNull=false;" style="width: 90%"/><font style="color:red">*</font>
	        </td>
	      </tr>
	    <tr class="form_bottom">
	      <td colspan="6">
	        <input type="submit" class="button" value='<b:message key="l_save"></b:message>'>
	      </td>
	    </tr>
	  </table>
	</h:form>
	</body>
</html>
