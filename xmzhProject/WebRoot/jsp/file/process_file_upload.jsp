<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增流程配置</title>
</head>
<body topmargin="0" leftmargin="0">
<h:form name="data_form" action="/file/tFileResourceTableAction_addProcessFile.action"  enctype="multipart/form-data" method="post" onsubmit="return checkForm(this);">
<table align="center" border="0" width="100%" class="form_table">
	
				<tr>
				<td class="form_label"  align="right" width="15%">文件名称</td>
					<td colspan="1"  width="30%"><h:text validateAttr="allowNull=false;maxLength=15"  id="fileName" property="fileName" /></td>
			</tr>
			<tr>
					<td class="form_label" align="right" width="15%">文件上传：</td>
					<td colspan="1"  width="30%"><input type="file" name="upload" style="width:96%"/></td>
					</tr>
			        <tr class="form_bottom">
			<td colspan="4"><input type="submit" value="保存" " class="button"  > <input type="button"
				value="关闭" onclick="window.close();" class="button"></td>
		</tr>
		</table>
</h:form>
<script type="text/javascript">
</script>
</body>
</html>