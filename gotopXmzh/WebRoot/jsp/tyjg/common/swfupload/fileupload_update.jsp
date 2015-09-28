<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common.jsp"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><b:message key="OperatorManager_l_operator"></b:message><b:message key="OperatorManager_l_manager"></b:message></title><!-- 操作员管理 -->
    <script language="javascript">
		
		/*
		 * 功能：保存操作员信息
		 *
		 * return 保存成败标志
		 */
		function fileUpdate()
		{
			var frm = $name("data_form");
	        
	        //表单验证
	        if(!checkForm(frm)) {
	            return;
	        }
			
		    var myAjax = new Ajax("/common/attachmentAction_updateFileupload.action");
		    //增加参数
		    myAjax.addParam("atFileupload.fileId",$name("atFileupload.fileId").value);
		    myAjax.addParam("atFileupload.fileName",$name("atFileupload.fileName").value);
		    myAjax.addParam("atFileupload.fileCatalog",$name("atFileupload.fileCatalog").value);
		    myAjax.addParam("atFileupload.relationId",$name("atFileupload.relationId").value);
		    myAjax.addParam("atFileupload.groupId",$name("atFileupload.groupId").value);
		    myAjax.addParam("atFileupload.attachmentSrcId",$name("atFileupload.attachmentSrcId").value);
		    myAjax.addParam("atFileupload.attachmentSrcCd",$name("atFileupload.attachmentSrcCd").value);
		    
		    //开始调用
		    myAjax.submit();
		    
		    //取得调用后的结果(xml对象)
		    var returnNode =myAjax.getResponseXMLDom();
		    
		    var reCode;
		    if(returnNode)
		    {
		    	//获取指定的节点值
		    	reCode = myAjax.getValue("result");
		    }
		    
		    //判断刷新业务字典信息成败
		    if(reCode == 1)
		    {
		    	alert('<b:message key="l_m_save_success"></b:message>'); //保存成功。
	    		window.closeD();
		    }else{
	    		alert('<b:message key="FileuploadManager_msg_update_failed"/>');
	    		return false;
	    	}
		}
		
		/*
		 * 功能：初始化页面
		 *
		 *
		 */
	    function custInit()
	    {
	    	//初始化页面中有按钮样式
	    	initButtonStyle();
	    }
	</script>
  </head>
  <body leftmargin="0" rightmargin="0" topmargin="0" class="eos-panel-table">
    <e:datasource name="atFileupload" type="entity" path="org.gocom.abframe.datasetExp.tools.AtFileupload" />
    <h:form name="data_form" action="org.gocom.abframe.tools.FileUploadManager.flow" method="post" onsubmit="return checkForm(this);">
      <h:hidden property="atFileupload.fileId"/>
      <input type="hidden" name="_eosFlowAction" value="update">
        <table align="center" border="0" width="516" class="form_table">
          <tr>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileName"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <h:text property="atFileupload.fileName" validateAttr="maxLength=64;allowNull=false;"/>
            </td>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileCatalog"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <d:select dictTypeId="ABF_FILE_CATALOG" property="atFileupload.fileCatalog" style="width:133"/>
            </td>
          </tr>
          <tr>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.relationId"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <h:text property="atFileupload.relationId" validateAttr="maxLength=64;allowNull=true;"/>
            </td>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.groupId"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <h:text property="atFileupload.groupId" validateAttr="maxLength=64;allowNull=true;"/>
            </td>
          </tr>
          <tr>
            <td class="form_label"><nobr>
              &nbsp;源记录编号(权限ID)<b:message key="l_colon"/></nobr>
            </td>
            <td colspan="1">
              <h:text property="atFileupload.attachmentSrcId" validateAttr="maxLength=64;allowNull=true;"/>
            </td>
            <td class="form_label">
              &nbsp;来源模块编号<b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <h:text property="atFileupload.attachmentSrcCd" validateAttr="maxLength=64;allowNull=true;"/>
            </td>
          </tr>
          <tr>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileSize"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <b:write property="atFileupload.fileSize"/>
            </td>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileType"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <b:write property="atFileupload.fileType"/>
            </td>
          </tr>
          <tr>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileSave"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <d:write dictTypeId="ABF_FILE_SAVE" property="atFileupload.fileSave"/>
            </td>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileTime"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <b:write  property="atFileupload.fileTime" formatPattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
          <tr>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.operatorid"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <b:write property="atFileupload.operatorid"/>
            </td>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.operatorname"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <b:write property="atFileupload.operatorname"/>
            </td>
          </tr>
          <tr>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.filePath"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <b:write  property="atFileupload.filePath"/>
            </td>
          </tr>
          <tr class="form_bottom">
            <td colspan="6">
              <input type="button" value='<b:message key="l_save"></b:message>' onclick="fileUpdate();">  <!-- 保存 -->
              <input type="button" value='<b:message key="l_close"></b:message>' onclick="javascript:window.closeD();"> <!-- 关闭 -->
            </td>
          </tr>
        </table>
    </h:form>
  </body>
</html>
<script language="javascript">
	
	//初始化页面js
    eventManager.add(window,"load",custInit);
</script>
