<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<%
	//获取标签中使用的国际化资源信息
	String dict_import  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_dict_type_import");  //业务字典导入
	String dict_item_label  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_dict_item_import");  //业务字典导入
%>
<html>
<head>
<title><b:message key="DictManager_l_dict_import"></b:message><b:message key="OperatorManager_l_manager"></b:message></title><!-- 业务字典导入管理 -->
</head>
<script language="JavaScript" type="text/javascript">
	/*
	 * 功能：导入Excel到业务字典类型表内
	 */
	function dictImportForType(){
		var frm = $id("importFormForType");
		var excelFile = $name("readFile").value;
		if (excelFile=="") {
			alert('<b:message key="DictManager_m_import_excel"></b:message>');//请选择您要导入的Excel文件！
			return;
		}
		var re= /.xls$/;
		if (!re.test(excelFile))
		{
			alert('<b:message key="DictManager_m_excel_file"></b:message>'); //请选择Excel文件！
			return;
		}
		excelFile = excelFile.substr(excelFile.lastIndexOf("\\") + 1);
		frm.action = "/tyjg/datadictionary/importDictTypeExcel_action.action";
		frm.submit();
	}
	
	/*
	 * 功能：导入Excel到业务字典项内
	 */
	function dictImport(){
		var frm = $id("importForm");
		var excelFile = $name("readFile").value;
		if (excelFile=="") {
			alert('<b:message key="DictManager_m_import_excel"></b:message>');//请选择您要导入的Excel文件！
			return;
		}
		var re= /.xls$/;
		if (!re.test(excelFile))
		{
			alert('<b:message key="DictManager_m_excel_file"></b:message>'); //请选择Excel文件！
			return;
		}
		excelFile = excelFile.substr(excelFile.lastIndexOf("\\") + 1);
		frm.action = "/tyjg/datadictionary/importDictEntryExcel_action.action";
		frm.submit();
	}

	/*
	  * 功能：初始化页面
	  */
     function custInit(){
     	//初始化页面按钮样式
     	initButtonStyle();
     }
</script>
<body leftmargin="0" rightmargin="0" topmargin="0">
	<!-- 业务字典导入 -->
	<table align="center" border="0" width="100%" class="form_table">
		<tr>
			<td  colSpan="4" class="eos-panel-title">
				<b:message key="DictManager_m_dict_type_excel"></b:message><b:message key="DictManager_l_dict_import_button"></b:message><!-- 业务字典Excel文件导入 -->
			</td>
		</tr>
		<h:form id="importFormForType" action="org.gocom.abframe.tools.DictManager.flow?_eosFlowAction=importTypeExcel" method="post" enctype="multipart/form-data" >
			<tr>
				<td class="form_label" align="center" width="30%">
					<b:message key="DictManager_m_dict_type_excel"></b:message><b:message key="DictManager_l_colon"></b:message><!-- 业务字典Excel文件： -->
				</td>
				<td class="form_label" style="text-align: left;">
					<input type="file" name="readFile" size="60">
				</td>
			</tr>
			<tr>
				<td colSpan="4" align="center">
					<input class="button" type="button" value='<b:message key="DictManager_l_dict_import_button"></b:message>' onclick="dictImportForType();"/><!-- 导入 -->
					<input class="button" type="button" value='<b:message key="DictManager_l_reset"></b:message>' onclick="onResetType();"/><!-- 重置 -->
				</td>
			 </tr>
		</h:form>
	</table>
	<br>
	<!-- 业务字典导入 -->
	<table align="center" border="0" width="100%" class="form_table">
		<tr>
			<td  colSpan="4" class="eos-panel-title">
				<b:message key="DictManager_m_dict_item_excel"></b:message><b:message key="DictManager_l_dict_import_button"></b:message><!-- 业务字典Excel文件导入 -->
			</td>
		</tr>
		<h:form id="importForm" action="org.gocom.abframe.tools.DictManager.flow?_eosFlowAction=importItemFile" method="post" enctype="multipart/form-data" >
		<tr>
			<td class="form_label" align="center" width="30%">
				<b:message key="DictManager_m_dict_item_excel"></b:message><b:message key="DictManager_l_colon"></b:message><!-- 业务字典Excel文件： -->
			</td>
			<td class="form_label" style="text-align: left;">
				<input type="file" name="readFile" size="60">
			</td>
		</tr>
		<tr>
			<td colSpan="4" align="center">
				<input class="button" type="button" value='<b:message key="DictManager_l_dict_import_button"></b:message>' onclick="dictImport();"/><!-- 导入 -->
				<input class="button" type="button" value='<b:message key="DictManager_l_reset"></b:message>' onclick="onResetType();"/><!-- 重置 -->
			</td>
		 </tr>
		</h:form>
	</table>
</body>
</html>
<script>
	//初始化页面按钮样式
    eventManager.add(window,"load",custInit);
</script>