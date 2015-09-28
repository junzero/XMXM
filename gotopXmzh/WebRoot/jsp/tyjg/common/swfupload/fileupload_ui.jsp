<%@page pageEncoding="UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>

<%
    //获取标签中使用的国际化资源信息
    String pleaseSelect = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_pleaseSelect");
    String uploadTitle = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("FileuploadManager_l_title_fileUpload");
    String deleteTitle = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("FileuploadManager_l_title_delete");
    
%>
<body topmargin="0" leftmargin="0">

<input type="hidden" id="t_rownum" value="1">

<h:form name="query_form" action="/common/attachmentAction_uploadWithDB.action" method="post" onsubmit="return checkForm(this);" enctype="multipart/form-data">
    <input type="hidden" name="atFileupload.filePath" value="true"/>
	<table align="center" border="0" width="100%" class="form_table" id="table_file">
		<tr height="4%">
		    <td class="eos-panel-title" colspan="8">&nbsp;&nbsp;<%=uploadTitle%></td>
	    </tr>
		<tr>
			<td class="form_label">
				<b:message key="FileuploadManager_AtFileUpload.fileCatalog"></b:message><b:message key="l_colon"></b:message>
			</TD>
			<td colspan="1">
				<d:select property="atFileupload.fileCatalog" dictTypeId="ABF_FILE_CATALOG" style="width:133" nullLabel="<%=pleaseSelect %>" extAttr="validateAttr='allowNull=false'"/>				
			</TD>
			<TD class="form_label">
				<b:message key="FileuploadManager_AtFileUpload.fileSave"></b:message><b:message key="l_colon"></b:message>
			</TD>
			<td colspan="1">
				<d:select property="atFileupload.fileSave" dictTypeId="ABF_FILE_SAVE" style="width:133" nullLabel="<%=pleaseSelect %>" extAttr="validateAttr='allowNull=false'"/>				
			</TD>
		</tr>	
		<tr>
			<td class="form_label">
				与业务关联ID<b:message key="l_colon"></b:message>
			</TD>
			<td colspan="1">
				<h:text property="atFileupload.relationId" />			
			</TD>
			<TD class="form_label">
				<b:message key="FileuploadManager_AtFileUpload.groupId"></b:message><b:message key="l_colon"></b:message>
			</TD>
			<td colspan="1">
				<h:text property="atFileupload.groupId" validateAttr="type=naturalNumber;allowNull=true;"/>	
			</TD>
		</tr>	
		<tr>
			<td class="form_label">
				源记录编号(权限ID)<b:message key="l_colon"></b:message>
			</TD>
			<td colspan="1">
				<h:text property="atFileupload.attachmentSrcId"/>
			</TD>
			<TD class="form_label">
				来源模块编号<b:message key="l_colon"></b:message>
			</TD>
			<td colspan="1">
				<h:text property="atFileupload.attachmentSrcCd"/>
			</TD>
		</tr>	
		<tr>
			<td class="form_label">
				操作人名<b:message key="l_colon"></b:message>
			</TD>
			<td colspan="1">
				<b:write property="atFileupload.operatorname" />
			</TD>
			<TD class="form_label">
				上传时间<b:message key="l_colon"></b:message>
			</TD>
			<td colspan="1">
				<b:write property="atFileupload.fileTime" formatPattern="yyyy-MM-dd"/>
			</TD>
		</tr>	
		<tr>
			<TD  colspan="4">
				<input id="files" type='file' name="uploadfile" size='90' unselectable="on"> 
				<input type='button' value='<b:message key="FileuploadManager_l_title_appendFile"/>' onclick='AddFileSelectInput("table_file","t_rownum");'  size="20">
			</TD>
		</tr>
	</TABLE>
	<table align="center" border="0" width="100%" class="form_table" id="table_file">
		<tr class="form_bottom">
			<TD class="form_bottom" colspan="2">
		        <input type="button" value='<b:message key="FileuploadManager_l_title_upload"/>' onclick="saveFileload();">
			</TD>
		</tr>	
	</TABLE>
</h:form>	
</body>
<script>
<l:present property="retCode">	
	<l:equal property="retCode" targetValue="1">
      alert('<b:message key="FileuploadManager_msg_file_succeed"/>');//文件上传成功!
	</l:equal>	
</l:present>

function saveFileload()
{
	var frm = $name("query_form");
        
    //表单验证
    if( !checkForm(frm) ) {
        return;
    }
    
    if($id("files").value == "")
    {
    	alert('<b:message key="FileuploadManager_msg_select_file"/>'); //请选择上传的文件!
    	return false;
    }
    
    frm.submit();
}

function DeleteFileSelectInput(table_id,rowId) {//删除表格
	var myTable = $id(table_id);
	myTable.deleteRow(eval(rowId).rowIndex);
	return;
}

function AddFileSelectInput(table_id,hidden_rownum) {//增加表格(附件)
	
	var myTable = $id(table_id);
	var t_rownum= eval(document.getElementById(hidden_rownum).value);
	//向表格中增加一行
	var myNewRow = myTable.insertRow(myTable.rows.length);
	//取得表格的总行数
	var aRows=myTable.rows;
	//取得表格的总网格数
	var aCells=myNewRow.cells;

	var oCell1_2=aRows(myNewRow.rowIndex).insertCell();
	oCell1_2.colSpan=2;   
	t_rownum=t_rownum+1;
	document.getElementById(hidden_rownum).value=t_rownum;
	var rowId="rowId"+t_rownum;
	myNewRow.id=rowId;

	str="<input type='file' id='files' unselectable='on' name=\"uploadfile\" size='90'>&nbsp;<input type='button' value='<%=deleteTitle %>' class='button' onclick='DeleteFileSelectInput(\""+table_id+"\",\""+rowId+"\");'>";
	oCell1_2.innerHTML=str;	
}
	    
//初始化页面按钮样式
eventManager.add(window,"load",initButtonStyle);
</script>