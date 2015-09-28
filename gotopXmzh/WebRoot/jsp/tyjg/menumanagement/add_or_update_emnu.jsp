<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      添加或修改菜单信息
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body style="margin: 0px;">
  		<%
  			String actions = null;
  		%>
  	<l:empty property="acFunction.funcCode">
  		 <%
  		 	actions ="/menumanagement/menuManagementAction_addMenu.action";//新增菜单
  		 %>
  	</l:empty>
  	<l:notEmpty property="acFunction.funcCode">
  		 <%
  		 	actions ="/menumanagement/menuManagementAction_updateMenu.action";//修改菜单
  		 %>
  	</l:notEmpty>
  	<e:datasource type="bean" name="acFunction" path="com.gotop.tyjg.menumanagement.model.AcFunction"/>
  	<h:form name="function_datafrm" action="<%=actions %>"
 		method="post" enctype="multipart/form-data" checkType="blur" onsubmit="return checkForm(this);" target="tempFrame">
		<h:hidden id = "funcGroup_id" property="acFunction.funcGroupId" value="${param.funcgroupid}"/>
		<h:hidden id = "funcGroup_code" property="acFunction.oldFuncCode" value="${acFunction.funcCode}"/>
		<h:hidden property="execType"/>
		  <table align="center" border="0" width="100%" class="form_table">
  			<l:notEmpty property="execType">
			   <tr>
			       <td colspan="6" class="eos-panel-title">&nbsp;&nbsp;菜单信息</td>
			    </tr>
		     </l:notEmpty>
		    <tr>
		      <td class="form_label" nowrap="nowrap">
		        <b:message key="applicationManager_AcFunction.funcname"></b:message><b:message key="l_colon"></b:message>
		      </td>
		      <td colspan="1">
		        <h:text property="acFunction.funcName" validateAttr="maxLength=128;allowNull=false;"/><font style="color:red">*</font>
		      </td>
		      <td class="form_label" nowrap="nowrap">
		        <b:message key="applicationManager_AcFunction.funccode"></b:message><b:message key="l_colon"></b:message>
		      </td>
		      <td colspan="1">
		        <h:text id="checkCodes" property="acFunction.funcCode" validateAttr="maxLength=40;allowNull=false" 
		        onblur="checkInputName(this)"/><font style="color:red">*</font>
		      </td>
		    </tr>
		    <tr>
		      <td class="form_label" nowrap="nowrap">
		        <b:message key="applicationManager_AcFunction.functype"></b:message><b:message key="l_colon"></b:message>
		      </td>
		      <td colspan="1">
		        <d:select dictTypeId="ABF_FUNCTYPE" property="acFunction.funcType" style="width:133"/>
		      </td>
		      <td class="form_label" nowrap="nowrap">
		        <b:message key="applicationManager_AcFunction.ismenu"></b:message><b:message key="l_colon"></b:message>
		      </td>
		      <td colspan="1">
		        <d:select property="acFunction.isMenu" dictTypeId="ABF_YESORNO" value="n" style="width:133"/>
		      </td>
		    </tr>
		    	<l:notEmpty property="acFunction.funcCode">
		    		<tr>
		    			<td class="form_label" nowrap="nowrap">原菜单图标：</td>
		    			<td colspan="3">
		    				<b:write property="acFunction.funIcon"/>
	    				</td>
		    		</tr>
		    	</l:notEmpty>
		    <tr>
		      <td class="form_label" nowrap="nowrap">
		         菜单图标：
		      </td>
		      <td colspan="3">
		      	<input type="file" name="upload" style="width:96%"/>
		      </td>
		    </tr>
		    <tr>
		      <td class="form_label" nowrap="nowrap">
		        <b:message key="applicationManager_AcFunction.funcaction"></b:message><b:message key="l_colon"></b:message>
		      </td>
		      <td colspan="3">
		        <h:text property="acFunction.funcAction"  validateAttr="allowNull=false" style="width:96%"/>
		        <font style="color:red">*</font>
		      </td>
		    </tr>
		    <tr>
		      <td class="form_label" nowrap="nowrap">
		        <b:message key="applicationManager_AcFunction.parainfo"></b:message><b:message key="l_colon"></b:message>
		      </td>
		      <td colspan="3">
		        <h:text property="acFunction.paraInfo" style="width:96%"/>
		      </td>
		    </tr>
		    <tr>
		      <td class="form_label" nowrap="nowrap">
		        <b:message key="applicationManager_AcFunction.funcdesc"></b:message><b:message key="l_colon"></b:message>
		      </td>
		      <td colspan="3">
		        <h:text property="acFunction.funcDesc" style="width:96%"/>
		      </td>
		    </tr>
		    <l:notEmpty property="execType">
		    <tr>
		    	<td class="form_label" nowrap="nowrap">显示顺序：</td>
		    	<td colspan="3">
		    		<h:text property="acFunction.displayOrder"/>
		    	</td>
		    </tr>
		    </l:notEmpty>
		    <tr class="form_bottom">
		      <td colspan="6">
		        <input id="save_btn" class="button" type="button" value='<b:message key="l_save"></b:message>' onclick="javascript:checkFuncCode();">
		        <l:empty property="execType">
		        <input type="button" class="button" value='<b:message key="l_close"></b:message>' onclick="javascript:window.closeD();">
		        </l:empty>
		      </td>
		    </tr>
		  </table>
	</h:form>
	<iframe id="tempFrame" name="tempFrame" style="display: none;"></iframe>
	<script type="text/javascript">
		function checkFuncCode(){
			var falg = checkInputName($id("checkCodes"));
			if(falg){
				$name("function_datafrm").submit();
			
			}
		}
		function checkInputName(obj){
			funTrim(obj);
			var myAjax = new Ajax("/menumanagement/menuManagementAction_checkFuncCode.action");
			myAjax.addParam("funcCode",obj.value);
			myAjax.addParam("oldFuncCode",$name("acFunction.oldFuncCode").value);
			myAjax.submit();
			var rtun = myAjax.getValue("root/data/isValid");
			if(parseInt(rtun) > 0){
				alert("该菜单编码已经存在！");
				return false;
			}
			if(parseInt(rtun) == -1){
				alert("服务端验证失败");
				return false;
			}
			return true;
		}
		function funTrim(obj){ //删除左右两端的空格
		  	var str = obj.value;
		  	str = str.replace(/(^\s*)|(\s*$)/g, "");
			obj.value=str;
 		}
	</script>
  </body>
</html>
