<%@page pageEncoding="UTF-8" %>
<%@include file="/common.jsp"%>

<html>
  <head>
    <title>文件上传 管理</title>

<%
    //获取标签中使用的国际化资源信息
    String pleaseSelect = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_pleaseSelect");
    String fileQueryCond = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("FileuploadManager_l_title_fileQueryCond");
    String fileQueryResult = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("FileuploadManager_l_title_fileQueryResult");

%>
	<h:script src="/common/gotop/datacellHtml.js"></h:script><!-- 引用导出函数 -->
  <script>

      function updateRecord()
      {
        var g = $id("group1");
        var frm = $name("page_form");
        if (g.getSelectLength() != 1) {
          alert('<b:message key="l_m_alert_mustAdnOnlySelectOne"/>'); //必须且只能选择一行
          return;
        }
        
        var fileId = g.getSelectParams("select_objs.fileId");
        var url = "/common/attachmentAction_fileuploadUpdate.action";
        url += "?_eosFlowAction=prepareUpdate";
        url += "&atFileupload.fileId=" + fileId;
        url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间戳的参数
        
    	showModalCenter(url, "", callBack, 520, 288, '<b:message key="l_update"/>' + '<b:message key="FileuploadManager_l_title_fileUpload"/>');//修改操作员
      }
      

      function deleteRecord()
      {
          var g = $id("group1");
          var frm = $name("page_form");
          if (g.getSelectLength() < 1) {
              alert( '<b:message key="l_m_alert_mustSelectOneOrMore"/>' );
           	  return;
       	  }
       	if( confirm( '<b:message key="FileuploadManager_msg_deleteFileConfirm"/>' ) ) {
		    var fileId = g.getSelectParams("select_objs.fileId");
		    var myAjax = new Ajax("/common/attachmentAction_deleteWithDB.action?fileIds="+fileId);
		    //开始调用
		    myAjax.submit();
		    //取得调用后的结果(xml对象)
		    var returnNode =myAjax.getResponseXMLDom();
		    if(returnNode)
		    {
		    	//获取指定的节点值
		    	var reCode = myAjax.getValue("result");
		    	//判断刷新业务字典信息成败
			    if(reCode == 1)
			    {
			    	alert('<b:message key="l_m_save_success"></b:message>'); //保存成功。
			    }else{
		    		alert('<b:message key="FileuploadManager_msg_update_failed"/>');
		    	}
                frm.submit();
           }
       	}
     }
      
       /*
       * 功能：新增、修改完成后回调函数－重新刷新页面
       *
       * return 
       */
      function callBack()
      {
      	var frm = $name("page_form");
      	frm.elements["_eosFlowAction"].value = "pageQuery";
      	frm.submit();
      }
      
      /*
      * 功能：实现全选复选框
      *
      * return 
      */
      function checkSelectAll()
      {
		 if ($id("checkSelect").checked)
		 {
			 selectAll("group1");
		 }else{
			 selectNone("group1");
		 }
      }
     
      /*
       * 自定义初始化按钮样式
       */
	  function custInit(){
		    initButtonStyle();
	  }
    </script>
  </head>
  <body topmargin="0" leftmargin="0">
    <h:form name="query_form" action="/common/attachmentAction_queryWithDB.action" method="post" onsubmit="return checkForm(this);">
      <input type="hidden" name="_eosFlowAction" value="pageQuery"/>
      <w:panel id="panel1" width="100%" title="<%=fileQueryCond %>">
        <table align="center" border="0" width="100%" class="form_table">
          <tr>
            <td class="form_label" nowrap="nowrap">  <!-- 操作员姓名 -->
              <b:message key="FileuploadManager_AtFileUpload.operatorname"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td>
              <h:text property="atFileupload.operatorname"/>
            </td>
            <td class="form_label" nowrap="nowrap">    <!-- 文件分类 -->
              <b:message key="FileuploadManager_AtFileUpload.fileCatalog"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td >
              <d:select dictTypeId="ABF_FILE_CATALOG" style="width:133" nullLabel="<%=pleaseSelect %>" property="atFileupload.fileCatalog"></d:select>
            </td>
            <td class="form_label" nowrap="nowrap">    <!-- 文件上传日期 -->
              <b:message key="FileuploadManager_AtFileUpload.fileTime"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td colspan="7">
              <table style="border:0px;background-color:transparent" border="0" cellspacing="0" cellpadding="0">
                <tr style="border: 0px none ; background-color: transparent;">
                  <td style="border: 0px none ; background-color: transparent;">
                    <b:message key="configManager_l_label_from"></b:message>
                  </td>
                  <td style="border: 0px none ; background-color: transparent;">
                    <w:date property="attachment.fileTimeStart" defaultNull="true"/>
                  </td>
                  <td style="border: 0px none ; background-color: transparent;">
                    <b:message key="configManager_l_label_to"></b:message>
                  </td>
                  <td style="border: 0px none ; background-color: transparent;">
                    <w:date property="attachment.fileTimeEnd" defaultNull="true"/>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td class="form_label" nowrap="nowrap">    <!-- 业务相关标识 -->
              <b:message key="FileuploadManager_AtFileUpload.relationId"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td >
			  <h:text property="atFileupload.relationId"/>
            </td>
            <td class="form_label" nowrap="nowrap">  <!-- 文件存储模式 -->
              <b:message key="FileuploadManager_AtFileUpload.fileSave"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td>
 			  <d:select dictTypeId="ABF_FILE_SAVE" style="width:133" nullLabel="<%=pleaseSelect %>" property="atFileupload.fileSave"></d:select>
            </td>
            <td class="form_label" nowrap="nowrap">    <!-- 文件大小 -->
              <b:message key="FileuploadManager_AtFileUpload.fileSize"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td colspan="7">
              <table style="border:0px;background-color:transparent" border="0" cellspacing="0" cellpadding="0">
                <tr style="border: 0px none ; background-color: transparent;">
                  <td style="border: 0px none ; background-color: transparent;">
                    <b:message key="configManager_l_label_from"></b:message>
                  </td>
                  <td style="border: 0px none ; background-color: transparent;">
                    <h:text property="attachment.fileSizeStart" validateAttr="type=naturalNumber;minValue=1;allowNull=true;"/>
                  </td>
                  <td style="border: 0px none ; background-color: transparent;">
                    <b:message key="configManager_l_label_to"></b:message>
                  </td>
                  <td style="border: 0px none ; background-color: transparent;">
                    <h:text property="attachment.fileSizeEnd" validateAttr="type=naturalNumber;minValue=1;allowNull=true;"/>字节
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr class="form_bottom">
            <td colspan="12" class="form_bottom">
              <b:message key="l_pageDisplay"></b:message>
              <h:text size="2" property="page.length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
              <input type="hidden" name="page.begin" value="0">
              <input type="hidden" name="page.isCount" value="true">
              <input type="submit" value='<b:message key="l_query"></b:message>'>&nbsp;
              <input type="button" value='<b:message key="l_reset"></b:message>' onclick="javascript:$name('query_form').reset();">
            </td>
          </tr>
        </table>
      </w:panel>
    </h:form>
    <h:form name="page_form" action="/common/attachmentAction_queryWithDB.action" method="post">
      <input type="hidden" name="_eosFlowAction" value="pageQuery"/>
      <h:hiddendata property="criteria" />
      <h:hiddendata property="atFileupload" />
      <h:hidden property="page.begin"/>
      <h:hidden property="page.length"/>
      <h:hidden property="page.count"/>
        <table align="center" border="0" width="100%" id="tableID" class="EOS_table">
          <tr height="4%">
			<td class="eos-panel-title" colspan="10">&nbsp;&nbsp;<%=fileQueryResult%></td>
		  </tr>
          <tr>
            <th align="center" width="5%">
              <l:greaterThan property="page.size" targetValue="0" compareType="number">
	          	<input type="checkbox" id="checkSelect" name="checkSelect" title='<b:message key="l_select"></b:message>' onclick="checkSelectAll();"><!-- 选择 -->
	          </l:greaterThan>
              <b:message key="l_select"></b:message>
            </th>
            <th>         <!-- 文件名 -->
              <b:message key="FileuploadManager_AtFileUpload.fileName"></b:message>
            </th>
            <th>         <!-- 文件分类 -->
               <b:message key="FileuploadManager_AtFileUpload.fileCatalog"></b:message>
            </th>
            <th>         <!-- 文件类型 -->
              <b:message key="FileuploadManager_AtFileUpload.fileType"></b:message>
            </th>
            <th>         <!-- 操作员姓名 -->
              <b:message key="FileuploadManager_AtFileUpload.operatorname"></b:message>
            </th>
            <th>         <!-- 文件存储模式 -->
              <b:message key="FileuploadManager_AtFileUpload.fileSave"></b:message>
            </th>
            <th>         <!-- 业务相关标识 -->
              <b:message key="FileuploadManager_AtFileUpload.relationId"></b:message>
            </th>
            <th>         <!-- 文件大小 -->
              <b:message key="FileuploadManager_AtFileUpload.fileSize"></b:message>
            </th>
            <th>         <!-- 上传时间 -->
              <b:message key="FileuploadManager_AtFileUpload.fileTime"></b:message>
            </th>
            <th width="3%">         <!-- 操作 -->
              <b:message key="FileuploadManager_l_title_operation"></b:message>
            </th>
          </tr>
          <w:checkGroup id="group1">
            <l:iterate property="uploadFiles" id="id1">
              <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
                <td align="center">
                  <w:rowCheckbox afterSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))" afterUnSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))">
                    <h:param name='select_objs.fileId' iterateId='id1' property='fileId' indexed='true' />
                  </w:rowCheckbox>
                </td>
                <td>
                  <b:write iterateId="id1" property="fileName"/>
                </td>
                <td>
                  <d:write dictTypeId="ABF_FILE_CATALOG" property="fileCatalog" iterateId="id1" />
                </td>
                <td>
                  <b:write iterateId="id1" property="fileType"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="operatorname"/>
                </td>
                <td>
                  <d:write iterateId="id1" property="fileSave" dictTypeId="ABF_FILE_SAVE"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="relationId"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="fileSize"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="fileTime" formatPattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td align="center">
	                <a target="_blank" href="/common/attachmentAction_downloadWithDB.action?fileIds=<b:write iterateId="id1" property="fileId"/>">下载</a>
                </td>
              </tr>
            </l:iterate>
          </w:checkGroup>
          <tr>
            <td colspan="11" class="command_sort_area">
              <div class="h3">
                <input type="button" value='<b:message key="l_update"/>' onclick="updateRecord();" id="updateButton" disabled="true">
                <input type="button" value='<b:message key="l_delete"/>' onclick="deleteRecord();" id="deleteButton" disabled="true">
              </div>
              <div class="h4">
                <l:equal property="page.isCount" targetValue="true">
                  <b:message key="l_total"></b:message>
                  <b:write property="page.count"/>
                  <b:message key="l_recordNO."></b:message>
                  <b:write property="page.currentPage"/>
                  <b:message key="l_page"></b:message>/
                  <b:write property="page.totalPage"/>
                  <b:message key="l_page"></b:message>
                </l:equal>
                <l:equal property="page.isCount" targetValue="false">
                  <b:message key="l_NO."></b:message>
                  <b:write property="page.currentPage"/>
                  <b:message key="l_page"></b:message>
                </l:equal>
                <input type="button" onclick="firstPage('page', 'pageQuery', null, null, 'page_form');" value='<b:message key="l_firstPage"></b:message>'  <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
                <input type="button" onclick="prevPage('page', 'pageQuery', null, null, 'page_form');" value='<b:message key="l_upPage"></b:message>' <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
                <input type="button" onclick="nextPage('page', 'pageQuery', null, null, 'page_form');" value='<b:message key="l_nextPage"></b:message>' <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
                <l:equal property="page.isCount" targetValue="true">
                  <input type="button" onclick="lastPage('page', 'pageQuery', null, null, 'page_form');" value='<b:message key="l_lastPage"></b:message>' <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
                </l:equal>
              </div>
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
<script type="text/javascript">
		//弹出导出窗口函数
		function openExportWinHtml(){
			var argument=new Array(3);
			argument[0] = "tableID";//table的ID
			argument[1] = "pageBack";//回调函数 一般用于处理分页信息
			argument[2] = window;//这个为必须，table所在窗口
//			argument[3] = "pageCond";//分页对应属性名
			
			var page_form = $name('page_form');//取得table对应的提交form
			
			var tableObj = $id(argument[0]);//获到表格对象
			tableObj.xpath="uploadFiles";//biz返回的数据集xpath
			tableObj.fields="fileId;fileName;fileCatalog;fileType;operatorname;fileTime;fileName";//从xpath取数据的字段名
			tableObj.queryAction="org.gocom.abframe.tools.FileUploadManager.queryAllFile.biz";//取数据的逻辑方法
			tableObj.entityType="org.gocom.abframe.datasetExp.tools.AtFileupload";
			tableObj.params = "<root><data>"+getTHead(argument[0])+"</data><params>"+getFormParamByHtml(page_form)+"</params><selectTable>"+getTableParamByHtml(tableObj,"select_objs")+"</selectTable></root>";//需要传递给biz的参数，getTHead取得table标题，getFormParamByHtml取得form里的参数。如果存在操作非TH表头trExportThear
			tableObj.params = tableObj.params.replaceAll("<selectTable></selectTable>","");
			tableObj.pageName = "page";////分页对应属性名
			tableObj.totalRow=$name(tableObj.pageName+"/count").value;//总记录数
			tableObj.pageSize=$name(tableObj.pageName+"/length").value;//每页大小
			
			showModalCenter("com.primeton.purview.htmlExport.flowx?_eosFlowAction=start",argument,null,570,251,"导出页数选择");//调用导出窗口,strUrl：弹出窗口的地址 dialogArguments：传递给模态对话框的参数 callBack：回调函数 winWidth：弹出窗口的宽度 winHeight：弹出窗口的高度 title：弹出窗口的title
		}

</script>