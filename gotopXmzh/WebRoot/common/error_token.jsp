<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<table align="center" border="0" width="100%" class="form_table">
			<tr>
				<td>
					<font style="color: red;">
						系统异常，请联系系统管理员(姓名：XXX，tel：0591-83325858-8000)！点击下面的按钮可查看详细信息！ </font>
				</td>
			</tr>
		</table>
		<p>
			<w:panel id="e" title="请检查是否有重复提交" expand="false">
				<s:actionerror/>
			</w:panel>
			<style>
				.over {
					text-decoration: underline;
				}
			</style>
	</body>
</html>
