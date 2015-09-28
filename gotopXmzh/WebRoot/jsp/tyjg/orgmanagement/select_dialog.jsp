<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      	移动选择
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body style="margin: 0px;">
  	<table class="form_table" width="100%" height="100%">
		<tr>
			<td  class="form_table" style="text-align: left">
			    <b:message key="l_selectOperation" /> <!-- 请选择您需要的操作：   -->
			</td>
	   </tr>
	   	<tr>
			<td>
	   			<h:radio name="changeRadio"  id="changeRadio1" checked="true" onclick="changeStatus('move');"/><b:message key="l_move" /> <!--移动 -->
	   			<h:radio name="changeRadio" id="changeRadio3" onclick="changeStatus('cancel');"/><b:message key="l_cancel" /> <!--取消 -->
		   </td>

	   </tr>
	   <tr>
			<td  class="form_bottom">
			<input type="button" class="button" value='<b:message key="l_cofirm" />' onclick="ok()" />
			</td>
	   </tr>
	</table>
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
	</script>
  </body>
</html>
