<%@page pageEncoding="UTF-8"%>

<%@include file="/common/common.jsp"%>

<%@include file="/common/skins/skin1/component.jsp"%>

<h:css href="/css/style1/style-custom.css" />

<html>

	<head>

		<title>Title</title>

		<script>

function closeWindow(){

	returnValue = [$id('submitValue').value,$id('displayValue').value];

	window.closeD();

}

function loadFun(){

	$id("displayValue").value = dialogArguments[1];

	$id("submitValue").value = dialogArguments[0];

}
eventManager.add(window, "load", loadFun);
</script>

	</head>

	<body>

		通过url传递的参数为：
		<span style="color: red"><%ForUtil.outPm(out,request,"param1");%></span>

		<table class="EOS_table" width="100%">

			<tr>

				<td class="style1">
					显示值:
				</td>

				<td>
					<input type="text" id="displayValue" />
				</td>

			</tr>

			<tr>

				<td class="style1">
					提交值:
				</td>

				<td>

					<input type="text" id="submitValue" />

				</td>

			</tr>

			<tr class="style1">

				<td class="style1"></td>

				<td>
					<input type="button" onclick="closeWindow();" value="确定" />
				</td>

			</tr>

		</table>

	</body>

</html>
