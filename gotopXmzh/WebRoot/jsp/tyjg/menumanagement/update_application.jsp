<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      修改应用系统信息
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
<%
	//获取标签中使用的国际化资源信息
	String illegalIP = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("m_illegalIP");
	String attributeIP="type=IP;allowNull=true;message="+illegalIP;
	String illegalPort = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("m_illegalPort");
	String attributePort="maxLength=5;minLength=1;allowNull=true;minValue=0;maxValue=65535;type=port;message="+illegalPort;
%>
  
  <body style="margin: 0px;">
  <e:datasource type="bean" name="acApplication" path="com.gotop.tyjg.menumanagement.model.AcApplication"/>
  	<h:form name="appAdd" action="/menumanagement/menuManagementAction_updateApp.action" checkType="blur"  enctype="multipart/form-data" 
  	method="post" onsubmit="return checkForm(this);" target="result">
  	<h:hidden property="acApplication.appId"/>
  <table align="center" border="0" width="100%" class="form_table">
    <tr>
      <td class="form_label" nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.appname"></b:message><b:message key="l_colon"></b:message>
      </td>
      <td colspan="1">
        <h:text property="acApplication.appName" validateAttr="maxLength=50;allowNull=false;"/><font style="color:red">*</font>
      </td>
      <td class="form_label" nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.appcode"></b:message><b:message key="l_colon"></b:message>
      </td>
      <td colspan="1">
        <h:text id="appcode" property="acApplication.appCode" onblur="checkInputName(this)" validateAttr="maxLength=10;allowNull=false;"/><font style="color:red">*</font>
      </td>
    </tr>
    <tr>
      <td class="form_label" nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.apptype"></b:message><b:message key="l_colon"></b:message>
      </td>
      <td colspan="1">
        <d:select dictTypeId="ABF_APPTYPE" property="acApplication.apptype" style="width:133"/>
      </td>
      <td class="form_label" nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.isopen"></b:message><b:message key="l_colon"></b:message>
      </td>
      <td colspan="1">
        <d:select dictTypeId="ABF_YESORNO" property="acApplication.isOpen" value="y" style="width:133"/>
      </td>
    </tr>
    <tr>
      <td class="form_label" nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.opendate"></b:message><b:message key="l_colon"></b:message>
      </td>
      <td colspan="1">
        <w:date property="acApplication.openDate"/>
      </td>
      <td class="form_label" nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.url"></b:message><b:message key="l_colon"></b:message>
      </td>
      <td colspan="1">
        <h:text property="acApplication.url" validateAttr="maxLength=256;"/>
      </td>
    </tr>
    <tr>
      <td class="form_label" nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.ipaddr"></b:message><b:message key="l_colon"></b:message>
      </td>
      <td colspan="1">
        <h:text property="acApplication.ipAddr"  validateAttr="<%=attributeIP %>" onblur="checkInput(this)" validateAttr="maxLength=50;"/>
      </td>
      <td class="form_label" nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.ipport"></b:message><b:message key="l_colon"></b:message>
      </td>
      <td colspan="1">
        <h:text property="acApplication.ipPort" validateAttr="<%=attributePort %>" onblur="checkInput(this)" validateAttr="maxLength=10;"/>
      </td>
    </tr>
    <l:equal property="acApplication.from" targetValue="noClose">
	    <tr>
	    	<td class="form_label" nowrap="nowrap">是否同步数据：</td>
	    	<td colspan="3">
	    		<d:select dictTypeId="ABF_YESORNO" property="acApplication.isSyn" nullLabel="--请选择--"/>
	    	</td>
	    </tr>
    </l:equal>
    <tr>
      <td class="form_label" nowrap="nowrap">
        <b:message key="applicationManager_AcApplication.appdesc"></b:message><b:message key="l_colon"></b:message>
      </td>
      <td colspan="3">
        <h:text property="acApplication.appDesc" style="width: 92%" validateAttr="maxLength=512;"/>
      </td>
    </tr>
   <tr>
    	<Td class="form_label" nowrap="nowrap">
    	预览图片
    	</td>
    	<td colspan="3"><input type="file" name="upload" onkeydown="return false;" style="width: 90%"/></td>
    </tr>
    <tr>
      <td class="form_label" nowrap="nowrap">
        单点方式
      </td>
      <td>
        <d:select property="acApplication.ssoMode" dictTypeId="ABF_SSO_TYPE"  nullLabel="---请选择---" style="width: 83"/>
      </td>
      <td class="form_label" nowrap="nowrap">
        参数个数
      </td>
      <td colspan="1">
        <h:select name="paraCount" property="acApplication.paraCount" style="width: 83" onchange="chanTableFun(this)" disabled="true" >
			<h:option label="0" value="0"/>
			<h:option label="1" value="1"/>
			<h:option label="2" value="2"/>
			<h:option label="4" value="4"/>
			<h:option label="5" value="5"/>
			<h:option label="6" value="6"/>
			<h:option label="7" value="7"/>
			<h:option label="8" value="8"/>
        </h:select>
        <h:hidden property="acApplication.paraCount"/>
      </td>
    </tr>
    <tr>
      <td class="form_label" nowrap="nowrap">
        参数简码1
      </td>
      <td colspan="1">
        <h:text property="acApplication.paraCode1" readonly="true"/>
      </td>
      <td class="form_label" nowrap="nowrap">
        参数名称1
      </td>
      <td colspan="1">
        <h:text property="acApplication.paraName1" readonly="true"/>
      </td>
    </tr>
    <tr>
      <td class="form_label" nowrap="nowrap">
        参数简码2
      </td>
      <td colspan="1">
        <h:text property="acApplication.paraCode2" readonly="true"/>
      </td>
      <td class="form_label" nowrap="nowrap">
        参数名称2
      </td>
      <td colspan="1">
        <h:text property="acApplication.paraName2" readonly="true"/>
      </td>
    </tr>
    <tr>
      <td class="form_label" nowrap="nowrap">
        参数简码3
      </td>
      <td colspan="1">
        <h:text property="acApplication.paraCode3" readonly="true"/>
      </td>
      <td class="form_label" nowrap="nowrap">
        参数名称3
      </td>
      <td colspan="1">
        <h:text property="acApplication.paraName3" readonly="true"/>
      </td>
    </tr>
    <tr>
      <td class="form_label" nowrap="nowrap">
        参数简码4
      </td>
      <td colspan="1">
        <h:text property="acApplication.paraCode4" readonly="true"/>
      </td>
      <td class="form_label" nowrap="nowrap">
        参数名称4
      </td>
      <td colspan="1">
        <h:text property="acApplication.paraName4" readonly="true"/>
      </td>
    </tr>
    <tr>
      <td class="form_label" nowrap="nowrap">
        参数简码5
      </td>
      <td colspan="1">
        <h:text property="acApplication.paraCode5" readonly="true"/>
      </td>
      <td class="form_label" nowrap="nowrap">
        参数名称5
      </td>
      <td colspan="1">
        <h:text property="acApplication.paraName5" readonly="true"/>
      </td>
    </tr>
    <tr>
      <td class="form_label" nowrap="nowrap">
        参数拼接效果
      </td>
      <td colspan="3">
        <h:text style="width: 92%" validateAttr="maxLength=512;" onblur="para_ext_fun(this)" title="自动更新简码/名称/数量" property="acApplication.paraNameStr"/><br/>
        <font style="color: red;">规则:简码1=名称1&简码2=名称2 例如：userid=账号&password=密码</font>
      </td>
    </tr>
    <tr class="form_bottom">
      <td colspan="6">
        <input id="save_btn" type="submit" class="button" disabled="true" value="<b:message key="l_save"></b:message>">
        <l:notEqual property="acApplication.from" targetValue="noClose">
        	<input type="button" class="button" value='<b:message key="l_close"></b:message>' onclick="javascript:window.closeD();"><!-- 关闭 -->
        </l:notEqual>
      </td>
    </tr>
  </table>
  <iframe width="0" height="0" name="result"></iframe>
</h:form>
<script type="text/javascript">
	function checkInputName(obj){
		if(obj.value==""){
			return true;
		}
	  	var myAjax = new Ajax("/menumanagement/menuManagementAction_checkAppCode.action");
	  	myAjax.addParam("appcode",obj.value);
	  	myAjax.addParam("appid",$name("acApplication.appId").value);
	  	myAjax.submit();
	  	var returnValue = myAjax.getValue("/root/data/isValid");
		if(parseInt(returnValue) >= 1){
		 		f_alert(obj,'<b:message key="applicationManager_m_addUpdateApp_isValid"></b:message>');
		 		$id("save_btn").disabled = true;
		 		return false;
		}else {
		 	$id("save_btn").disabled = false;
		 	return true;
		}
	  checkInput(obj);
	}
	function para_ext_fun(pef){
		var pefStr = pef.value;
		var i=0;
		if(pefStr){
			pefArra = pefStr.split("\&");
			for(;i<pefArra.length;i++){
				if(pefArra[i]){
					var farra = pefArra[i].split("=");
					if(farra.length==2 && farra[0] && farra[1]){
						if($name("acApplication.paraCode"+(i+1))){
							$name("acApplication.paraCode"+(i+1)).value = farra[0];
							$name("acApplication.paraName"+(i+1)).value = farra[1];
						}else{
							alert("超过填写个数!");
							break;
						}
					}else {
						alert("填写错误! 规则如下：userid=账号&password=密码");
						break;
					}
				}else{
					alert("填写错误! 规则如下：userid=账号&password=密码");
					break;
				}
			}
		}
		$name("paraCount").value=i;
		$name("acApplication.paraCount").value=i;
		for(var j=i;j<100;j++){
			if($name("acApplication.paraCode"+(j+1))){
				document.getElementById("acApplication.paraCode"+(j+1)).value = "";
				document.getElementById("acApplication.paraName"+(j+1)).value = "";
			}else{
				break;
			}
		}
	}
	checkInputName($id("appcode"));
</script>
  </body>
</html>