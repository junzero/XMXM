<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
	<%
		//获取标签中使用的国际化资源信息
		String title=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("groupPositionMaintain_l_title_subPosition_list");
		String posi_query_cond = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("positionManager_l_title_position")
	                       + com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_query_cond");
		String posi_query_result = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("positionManager_l_title_position")
	                         + com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_query_result");
	 %>
 
	<title><%=title%></title> <!-- 下级岗位列表 -->

	<script>
	//刷新指定树的选中节点 
	function refreshTreeNode(tree){
		try{
			tree.getSelectNode().reloadChild();
		}catch(e){
			//alert(e);
		}
	}
	
	//模态窗口回调函数，关闭模态窗口时，刷新父窗口的数据
	function callBack() {
	    refreshTreeNode(parent.parent.window.frames['groupTree'].groupTree);
	    var frm = $name("viewlist1");
	    frm.elements["_eosFlowAction"].value = "pageQuery";
	    frm.submit();
	}
	
	 //增加工作组岗位
	 function addRecord() {
	     var frm = $name("viewlist1");
	     //frm.elements["_eosFlowAction"].value = "insert";
	     //frm.submit();
	     
	     var url = "org.gocom.abframe.org.workgroup.GroupPositionMaintain.flow";
	     url += "?_eosFlowAction=insert";
	     url += "&parentGroupid=";
	     url += '<b:write property="parentGroupid" scope="flow"/>';
	     url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	     showModalCenter(url, "", callBack, 700, 200, "<b:message key='workgroupManager_l_addGroupPosition' />");    <!--增加工作组岗位 --> 
	 }
	 //更新工作组岗位
	 function modiRecord() {
	     var g = $id("group1");
	     var frm = $name("viewlist1");
	     if( g.getSelectLength() != 1 ) {
	          alert("<b:message key='l_m_alert_mustAdnOnlySelectOne' />");// <!--必须且只能选择一行！ -->
	          return;
	     }
	     //取单个id的值
	     var positionid = g.getSelectParams("selectedObjects/positionid");
	     var url = "org.gocom.abframe.org.workgroup.GroupPositionMaintain.flow";
	     url += "?_eosFlowAction=update";
	     url += "&position/positionid=" + positionid;
	     url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	     showModalCenter(url, "", callBack, 700, 200, "<b:message key='workgroupManager_l_addGroupPosition' />");  <!--修改工作组岗位 --> 
	 }
	 //实现全选复选框
	 function checkSelectAll(){
		if ($id("checkSelect").checked){
			selectAll("group1");
		}else{
			selectNone("group1");
		}
	 } 
	 //删除工作组岗位
	 function delRecord() {
	     var g = $id("group1");
	     if( g.getSelectLength() < 1 ) {
	          alert("<b:message key='l_m_alert_mustSelectOneOrMore' />");//<!--至少选择一行！ -->
	          return;
	     }
	     if( !confirm("<b:message key='workgroupManager_m_delete_position_confirm' />") ) {//<!--您确认要删除选中的工作组岗位？ -->
	         return;
	     }
	     var myAjax = new Ajax("org.gocom.abframe.org.workgroup.WorkgroupManager.deleteGroupPositions.biz");
	     var id =  g.getSelectParams("selectedObjects/positionid");
	     //删除可以多选，返回的是数组
	     if( id != null && id != "" && id.length > 0 ) {
	         for(var i = 0 ; i < id.length; i++) {
	             myAjax.addParam("positions[" + (i+1) + "]/positionid", id[i] );
	         }
	         myAjax.addParam("group/groupid", $name("parentGroupid").value);
	         myAjax.submit();
	         var returnNode = myAjax.getResponseXMLDom();
	         if( returnNode ) {
	            if( myAjax.getValue("root/data/oprResult") == 1 ) {
	                alert( '<b:message key="l_m_delete_success"/>' ); // <!--  删除成功 -->
	            } else {
	                alert( '<b:message key="l_m_delete_fail"/>' );   //  <!-- 删除失败 -->   
	            }
	         } else {
	            alert( '<b:message key="l_m_delete_fail"/>' );    // <!-- 删除失败 -->   
	         }
	         
	     }
	     //删除记录后，重新刷新页面
	     callBack();
	 }
	 
	  /*自定义初始化按钮样式
      *
      */
	 function custInit(){  
		initButtonStyle();
	 }
	</script> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0">
    <h:form name="query_form" action="org.gocom.abframe.org.workgroup.GroupPositionMaintain.flow" checkType="blur" target="_self" method="post">
      <input type="hidden" name="_eosFlowAction" value="pageQuery"/>
      <h:hidden name="criteria/_entity" value="org.gocom.abframe.datasetExp.organization.OmGroupposi"/>
	  <h:hidden name="criteria/_expr[1]/omGroup.groupid" property="parentGroupid" scope="flow"/>
	  <h:hidden property="parentGroupid" scope="flow"/>
       
      <w:panel id="panel1" width="100%" title="<%=posi_query_cond %>">
        <table align="center" border="0" width="100%" class="form_table">          
          <tr>
            <td class="form_label">  <!--  岗位名称 -->
              <b:message key="positionManager_OmPosition.posiname"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td>
                <h:text property="criteria/_expr[2]/omPosition.posiname"/>
                <h:hidden property="criteria/_expr[2]/_op" value="like"/>
                <h:hidden property="criteria/_expr[2]/_likeRule" value="all"/>
            </td>
          </tr>
          <tr class="form_bottom">
            <td colspan="6" class="form_bottom">
              <b:message key="l_pageDisplay"></b:message>
              <h:text size="2" property="page/length" value="10" scope="flow" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
              <input type="hidden" name="page/begin" value="0" scope="flow" >
              <input type="hidden" name="page/isCount" value="true" scope="flow" >
              <input type="submit" value='<b:message key="l_query"></b:message>'>&nbsp;
              <input type="button" value='<b:message key="l_reset"></b:message>' onclick="javascript:$name('query_form').reset();">
            </td>
          </tr>
        </table>
      </w:panel>
    </h:form>
	<%--viewlist configurationID:20080821111408 --%>
	<h:form name="viewlist1" action="org.gocom.abframe.org.workgroup.GroupPositionMaintain.flow" checkType="blur" target="_self" method="post">
	  <input type="hidden" name="_eosFlowAction" value="pageQuery" >
	  <!-- 隐藏匿分页及查询信息 -->
	  <h:hiddendata property="page" scope="flow"  />
	  <h:hiddendata property="criteria"  />
	    <table align="center" border="0" width="100%" class="EOS_table">
	      <tr>
	       <td colspan="5" class="eos-panel-title">&nbsp;<%=title %></td>
	      </tr>
	      <tr>
	        <th align="center">
	          <l:greaterThan property="page/size" targetValue="0" compareType="number" scope="f">
		      	<input  type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll();"> 
		      </l:greaterThan>        
	          <b:message key="l_select" /> <!--选择 -->
	        </th>
	        <th>
	          <b:message key="groupPositionMaintain_l_Omposition.posiname" /> <!--岗位名称 -->
	        </th>
	        <th>
	          <b:message key="groupPositionMaintain_l_Omposition.positype" /> <!--岗位类别 -->
	        </th>
	        <th>
	          <b:message key="groupPositionMaintain_l_Omposition.startdate" /> <!--岗位有效开始日期 -->
	        </th>
	        <th>
	          <b:message key="groupPositionMaintain_l_Omposition.enddate" /> <!--岗位有效截止日期 -->
	        </th>
	      </tr>
	      <w:checkGroup id="group1">
	        <l:iterate property="positions" id="id1">
	          <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
	            <td align="center">
	              <w:rowCheckbox afterSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))" afterUnSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))">
	                <h:param name='selectedObjects/positionid' iterateId='id1' property='positionid' indexed="true" />
	              </w:rowCheckbox>
	            </td>
	            <td>
	              <b:write iterateId="id1" property="posiname"/>
	            </td>
	            <td>
	              <d:write dictTypeId="ABF_POSITYPE" iterateId="id1" property="positype"/>
	            </td>
	            <td>
	              <b:write iterateId="id1" property="startdate" formatPattern="yyyy-MM-dd"/>
	            </td>
	            <td>
	              <b:write iterateId="id1" property="enddate" formatPattern="yyyy-MM-dd"/>
	            </td>
	          </tr>
	        </l:iterate>
	      </w:checkGroup>
	      <tr>
	        <td colspan="6" class="command_sort_area">
	        <div class="h3">
	          <input type="button" value="<b:message key = 'l_add'/>" onclick="addRecord();"> <!--新增 -->
	          <l:greaterThan property="page/size" targetValue="0" compareType="number" scope="flow">
	            <input type="button" value="<b:message key = 'l_update'/>" onclick="modiRecord();" id="updateButton"> <!--修改 -->
	          </l:greaterThan>
	          <l:greaterThan property="page/size" targetValue="0" compareType="number" scope="flow">
	            <input type="button" value="<b:message key = 'l_delete'/>" onclick="delRecord();" id="deleteButton"> <!--删除 -->
	          </l:greaterThan>
             </div>
             <div class="h4">
	            <l:equal property="page/isCount" targetValue="true" scope="flow">
	              <b:message key = 'l_total'/>       <!--共 -->
	              <b:write property="page/count" scope="flow"/>
	              <b:message key = 'l_recordNO.'/>   <!--条记录 第 -->
	              <b:write property="page/currentPage" scope="flow"/>
	              <b:message key = 'l_page'/>/       <!--页 -->
	              <b:write property="page/totalPage" scope="flow"/>
	              <b:message key = 'l_page'/>        <!--页 -->
	            </l:equal>
	            <l:equal property="page/isCount" targetValue="false" scope="flow">
	              <b:message key = 'l_NO.'/>         <!--第 -->
	              <b:write property="page/currentPage" scope="flow"/>
	              <b:message key = 'l_page'/>        <!--页 -->
	            </l:equal>
	            <!--首页 上页 下页 尾页 -->           
	            <input type="button" onclick="firstPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_firstPage'/>"  <l:equal property="page/isFirst" targetValue="true" scope="flow">disabled</l:equal> >
	            <input type="button" onclick="prevPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_upPage'/>" <l:equal property="page/isFirst" targetValue="true" scope="flow">disabled</l:equal> >
	            <input type="button" onclick="nextPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_nextPage'/>" <l:equal property="page/isLast" targetValue="true" scope="flow">disabled</l:equal> >
	            <l:equal property="page/isCount" targetValue="true" scope="flow">
	              <input type="button" onclick="lastPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_lastPage'/>" <l:equal property="page/isLast" targetValue="true" scope="flow">disabled</l:equal> >
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
     eventManager.add(window, "load", custInit); 
</script>