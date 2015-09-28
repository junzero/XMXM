<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>

<html>
<head>
<title></title>

<script language="javascript">
    var opertion="move";
    /* 
     *  确定关闭页面
     */
    function ok(){
    	var result = [];
    	if(window["dialogArguments"].slice){
	    	result = window["dialogArguments"].slice(0);
    	}else{
    		result[0]=window["dialogArguments"];
    	}
    	var orgType = 0;
    	var opArra = $id("orgType").getValue().split(",");
    	for(var i=0;i<opArra.length;i++){
    		if(opArra[i]){
    			orgType += parseInt(opArra[i],10);
    		}
    	}
    	result.push(orgType);
        //定义窗口关闭时的返回值
        window['returnValue'] = result;
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
		initButtonStyle();
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
	   			<d:checkbox id="orgType" name="orgType" dictTypeId="ABF_PARENTORGTYPE" filterStr="${param.orgType}" filterOp="<="/>
		   </td>
	   </tr>
	   <tr>
			<td  class="form_bottom">
			<input type="button" value='<b:message key="l_cofirm" />' onclick="ok()" />
			</td>
	   </tr>
	</table>

</body>
</html>
<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
</script>