<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<%
	//获取标签中使用的国际化资源信息
	String save  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_save");                 //保存
	String close = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_close");                //关闭
	String estop = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("EosDictEntry.status.value_estop");    //保存
	String open  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("EosDictEntry.status.value_open");     //关闭
%>
<html>
<head>
<title><b:message key="DictManager_l_add_type"/><b:message key="DictManager_l_item"/></title><!-- 增加字典类型项 -->
<script language="javascript">
	
	/*
	 * 功能：保存业务字典类型项
	 *
	 * return 保存成败标志
	 */
	function saveDictEntry()
	{
		var frm = $name("data_form");
        
        //表单验证
        if( !checkForm(frm) ) {
            return;
        }
	    var myAjax = null;
	    var message;
			myAjax = new Ajax("/tyjg/datadictionary/updateDictEntry_action.action");
			// myAjax.addParam("dictEntry.rank",$name("dictEntry.rank").value);
			//myAjax.addParam("dictEntry.seqno",$name("dictEntry.seqno").value);
			message = '<b:message key="DictManager_l_update_dict_type"/>' + '<b:message key="DictManager_l_item"/>' + '<b:message key="DictManager_l_failed"/>';//修改业务字典类型项失败。
	    //增加参数
	    myAjax.addParam("dictEntry.dicttypeid",$name("dictEntry.dicttypeid").value);
	    myAjax.addParam("dictEntry.parentid",$name("dictEntry.parentid").value);
	    myAjax.addParam("dictEntry.dictid",$name("dictEntry.dictid").value);
	    myAjax.addParam("dictEntry.dictname",$name("dictEntry.dictname").value);
	    myAjax.addParam("dictEntry.sortno",$name("dictEntry.sortno").value);
	    myAjax.addParam("dictEntry.status",$name("dictEntry.status").value);

	    //开始调用
	    myAjax.submit();
	    
	    //取得调用后的结果(xml对象)
	    var returnNode =myAjax.getResponseXMLDom();
	    
	    var reCode;
	    if(returnNode)
	    {
	    	//获取指定的节点值
	    	reCode = myAjax.getValue("/root/data/flag");
	    }
	    //判断刷新业务字典信息成败
    	if(reCode == 1)
	    {
	    	alert('<b:message key="l_m_save_success"></b:message>'); //保存成功。
    		window.closeD();
	    }else{
    		alert(message);
    		return false;
    	}
    	window.closeD();
	}
	
	/*
	  * 功能：初始化页面
	  *
	  *
	  */
     function custInit()
     {
     	//初始化页面按钮样式
     	initButtonStyle();
     	//初始化焦点
     	$id("dictid").focus();
     }
</script>
</head>
<body class="eos-panel-table">
	<h:form name="data_form" action="org.gocom.abframe.tools.DictManager.flow" method="post" onsubmit="return checkForm(this);">
        <h:hidden property="dictEntry.dicttypeid"/>
        <h:hidden property="dictEntry.parentid"/>
 		<table align="center" border="1" width="296" class="form_table">
 		<l:notEmpty property="dictEntry.parentid">
          <tr>
            <td class="form_label" align="left">
              &nbsp;<b:message key="DictManager_l_superior"/><b:message key="EosDictEntry.dictid"/><b:message key="DictManager_l_colon"/><!-- 上级类型项代码： -->
            </td>
            <td colspan="1">
            	<b:write property="dictEntry.parentid"/>
            </td>
          </tr>
          </l:notEmpty>
          <tr>
            <td class="form_label" align="left">
              &nbsp;<b:message key="DictManager_l_dict"/><b:message key="EosDictEntry.dictid"/><b:message key="DictManager_l_colon"/><!-- 字典类型项代码： -->
            </td>
            <td colspan="1">
            	<h:text property="dictEntry.dictid" size="20" readonly="true"/>
            </td>
          </tr>
          <tr>
             <td class="form_label" align="left">
              &nbsp;<b:message key="DictManager_l_dict"/><b:message key="EosDictEntry.dictname"/><b:message key="DictManager_l_colon"/><!-- 字典类型项名称： -->
            </td>
            <td colspan="1">
            	<h:text property="dictEntry.dictname" size="20" validateAttr="maxLength=255;allowNull=false;" id="dictid"/><font style="color:red">*</font>
            </td>
          </tr>
          <tr>
             <td class="form_label" align="left">
              &nbsp;<b:message key="EosDictEntry.sortnotemp"/><b:message key="DictManager_l_colon"/><!-- 显示排序： -->
            </td>
            <td colspan="1">
              <h:text property="dictEntry.sortno" size="20" validateAttr="maxLength=10;type=number;"/>
            </td>
          </tr>
          <tr>
             <td class="form_label" align="left">
              &nbsp;<b:message key="EosDictEntry.status"/><b:message key="DictManager_l_colon"/><!-- 是否封存： -->
            </td>
            <td colspan="1">
              	<h:select id="eosDictEntry/status" value="1" style="width=132" property="dictEntry.status">
					<h:option label="<%=estop%>" value="0"/><!-- 禁止 -->
					<h:option label="<%=open%>" value="1"/><!-- 启用 -->
				</h:select>
            </td>
          </tr>
          <tr class="form_bottom">
            <td colspan="6">
              <input class="button" type="button" value="<%=save%>" onclick="saveDictEntry();"><!-- 保存 -->
              <input class="button" type="button" value="<%=close%>" onclick="javascript:window.closeD();"><!-- 关闭 -->
            </td>
          </tr>
        </table>
    </h:form>
</body>
</html>
<script language="javascript">
	
	//初始化页面按钮样式
    eventManager.add(window,"load",custInit);
</script>