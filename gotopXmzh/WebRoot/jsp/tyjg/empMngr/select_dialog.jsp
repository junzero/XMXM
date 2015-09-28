<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<html>
<head>
<title></title>
<h:css href="/css/style1/style-custom.css"/>
<script language="javascript">
    var opertion="move";
    /* 
     *  确定关闭页面
     */
    function ok(){
        //定义窗口关闭时的返回值
        window['returnValue'] = opertion;
        window.closeD();
    }

    /* 
     *  选择操作时给变量赋值
     */
	function changeStatus( op ) {
		opertion=op;
	}
	
	/*
     *  自定义初始化按钮样式
     */
	 function custInit(){  
//		initButtonStyle();
	 }
</script>

</head>

<body scroll="no" leftmargin="0px;" topmargin="0px;" rightmargin="0px;" bottommargin="0px;">

	<TABLE class="form_table" width="100%" height="100%">
		<tr>
			<td  class="form_table" style="text-align: left">
			    <b:message key="l_selectOperation" /> <!-- 请选择您需要的操作：   -->
			</td>
	   </tr>
	   	<tr>
			<td>
	   			<h:radio name="changeRadio"  id="changeRadio1" checked="true" onclick="changeStatus('move');"/><b:message key="l_move" /> <!--移动 -->
	   			<h:radio name="changeRadio" id="changeRadio2" onclick="changeStatus('copy');"/><b:message key="l_copy" /> <!--复制 -->
	   			<h:radio name="changeRadio" id="changeRadio3" onclick="changeStatus('cancel');"/><b:message key="l_cancel" /> <!--取消 -->
		   </td>

	   </tr>
	   <tr>
			<td  class="form_bottom">
			<input type="button" class="button" value='<b:message key="l_cofirm" />' onclick="ok()" />
			</td>
	   </tr>
	</table>

</body>
</html>
