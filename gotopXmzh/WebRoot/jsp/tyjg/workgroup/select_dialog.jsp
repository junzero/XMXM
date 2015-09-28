<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<html>
<%
	//获取标签中使用的国际化资源信息
	String title=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("bizOrgManager_l_title_busidomain_add");		
%>
<head>
<title><b:message key = '<%=title%>'/></title>

<script>
    var opertion="move";
   
    function init() {
	    var isGroup = window["dialogArguments"];
	    //如果是工作组就不提供复制操作
	    if ( isGroup == true ) {
	    	$id("changeRadio2").disabled=true;
	    }  
    }
  
    function ok(){
        //定义窗口关闭时的返回值
        window['returnValue'] = opertion;
        window.closeD();
    }

	function changeStatus( op ) {
		opertion=op;
	}
    /*
     *  自定义初始化按钮样式
     */
	 function custInit(){  
		initButtonStyle();
	 }
	 eventManager.add(window, "load", init);
</script>

</head>

<body scroll="no" leftmargin="0px;" topmargin="0px;" rightmargin="0px;" bottommargin="0px;">

	<TABLE class="form_table" width="100%" height="100%">
		<tr>
			<td  class="form_label" style="text-align: left">
			    <b:message key="l_selectOperation" /> <!--请选择您需要的操作： -->
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
			<input type="button" value='<b:message key="l_cofirm" />' onclick="ok()" /> <!--确定 -->
			</td>
	   </tr>
	</table>

</body>
</html>

<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
</script>