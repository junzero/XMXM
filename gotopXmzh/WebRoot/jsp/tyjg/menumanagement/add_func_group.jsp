<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      新增功能组
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  <e:datasource name="acFunctionGroup" type="bean" path="com.gotop.tyjg.menumanagement.model.AcFunctionGroup"/>
  	<h:form name="subFuncGroup_dataform" action="/menumanagement/menuManagementAction_addFuncGroup.action" 
  		target="tempFrame" method="post"  onsubmit="return checkForm(this);">
	  	<h:hidden name="acFunctionGroup.appId" value="${param.appid}"/>
	  	<h:hidden name="acFunctionGroup.funcGroupSeq" value="${param.seq}"/>
	  	<h:hidden name="acFunctionGroup.parentGroup" value="${param.groupid}"/>
	  	<h:hidden name="acFunctionGroup.groupLevel" value="${param.groupLevel}"/>
	    <table align="center" border="0" width="100%" class="form_table">
	      <tr>
	        <td class="form_label" width="20%">
	          <b:message key="applicationManager_AcFuncGroup.funcgroupname"></b:message><b:message key="l_colon"></b:message>
	        </td>
	        <td colspan="1">
	          <h:text property="acFunctionGroup.funcGroupName" validateAttr="allowNull=false;"/><font style="color:red">*</font>
	        </td>
	      </tr>
	      <tr>
	        <td class="form_label" width="20%">
	          功能组调用接口:
	        </td>
	        <td colspan="1">
	          <h:text property="acFunctionGroup.groupAction" validateAttr="allowNull=false;" style="width: 90%"/><font style="color:red">*</font>
	        </td>
	      </tr>
	      <tr class="form_bottom">
	        <td colspan="6">
	          <input type="submit" class="button" value='<b:message key="l_save"></b:message>'>
	          <input type="button" class="button" value='<b:message key="l_close"></b:message>' onclick="javascript:window.closeD();"><!-- 关闭 -->
	        </td>
	      </tr>
	    </table>
	</h:form>
	<iframe id="tempFrame" name="tempFrame" style="display: none;"></iframe>
  </body>
</html>
