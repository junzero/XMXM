<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%
		//获取标签中使用的国际化资源信息
		String function_list = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_function_list");
	%>
  </head>
  <body style="margin: 0px;">
  	<h:form name="function_viewlist" action="/menumanagement/menuManagementAction_queryGroupFunMenu.action" method="post">
		 <h:hidden id = "funcGroup_id" property="acFunction.funcGroupId"/>
		 <h:hidden property="page.begin"/>
	     <h:hidden property="page.length"/>
	     <h:hidden property="page.count"/>
	     <h:hidden property="page.isCount"/>
		  <table align="center" border="0" width="100%" class="EOS_table">
		    <tr>
		       <td colspan="6" class="eos-panel-title">&nbsp;&nbsp;<%=function_list%></td>
		    </tr>
		    <tr>
		      <th width="8%" align="center">
				  <input type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll();"> 
				  <b:message key="l_select"></b:message>
			  </th>
		      <th>
		        <b:message key="applicationManager_AcFunction.funcname"></b:message>
		      </th>
		      <th>
		        <b:message key="applicationManager_AcFunction.functype"></b:message>
		      </th>
		      <th>
		        <b:message key="applicationManager_AcFunction.ismenu"></b:message>
		      </th>
		      <th>
		        <b:message key="applicationManager_AcFunction.parentFuncGroup"></b:message>
		      </th>
		      <th>
		        显示顺序
		      </th>
		    </tr>
		    <w:checkGroup id="group1">
		      <l:iterate property="acFunctionList" id="id1">
		        <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
		          <td align="center">
		            <w:rowCheckbox>
		              <h:param name='funccode' iterateId='id1' property='funcCode'/>
		              <h:param name='funcGroupId' iterateId='id1' property='funcGroupId'/>
		            </w:rowCheckbox>
		          </td>
		          <td>
		            <b:write iterateId="id1" property="funcName"/>
		          </td>
		          <td>
		            <d:write iterateId="id1" property="funcType" dictTypeId="ABF_FUNCTYPE"/>
		          </td>
		          <td>
		            <d:write iterateId="id1" property="isMenu" dictTypeId="ABF_YESORNO"/>
		          </td>
		          <td>
		            <b:write iterateId="id1" property="parentGroupName"/>
		          </td>
		          <td onclick="cancelEven()">
		            <h:text iterateId="id1" property="displayOrder" id="displayorder" name="displayorder" size="4"/>
		          </td>
		        </tr>
		      </l:iterate>
		    </w:checkGroup>
		    <tr>
		      <td colspan="6" class="command_sort_area">
		      <div class="h3">
		        <input type="button"  class="button" value='<b:message key="l_add"></b:message>' onclick="addRecord();">
		        <l:greaterThan property="page.count" targetValue="0" compareType="number">
		          <input type="button" class="button" value='<b:message key="l_update"></b:message>' onclick="modiRecord();" id="updateButton">
		        </l:greaterThan>
		        <l:greaterThan property="page.count" targetValue="0" compareType="number">
		          <input type="button" class="button" value='<b:message key="l_delete"></b:message>' onclick="delRecord();" id="deleteButton">
		        </l:greaterThan>
		        <l:greaterThan property="page.count" targetValue="0" compareType="number">
		          <input type="button" class="button" onclick="saveOrder()" value="保存顺序" />
		        </l:greaterThan>
              </div>
              <div class="h4">
		            <l:equal property="page.isCount" targetValue="true">
		              <b:message key="l_total"/>
		              <b:write property="page.count"/>
		              <b:message key="l_recordNO."/>
		              <b:write property="page.currentPage"/>
		              <b:message key="l_page"/>/
		              <b:write property="page.totalPage"/>
		              <b:message key="l_page"/>
		            </l:equal>
		            <l:equal property="page.isCount" targetValue="false">
		              <b:message key="l_NO."/>
		              <b:write property="page.currentPage"/>
		              <b:message key="l_page"/>
		            </l:equal>
		            <input type="button" class="button" onclick="firstPage('page', '', null, null, 'function_viewlist');" value='<b:message key="l_firstPage"/>'  <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
		            <input type="button" class="button" onclick="prevPage('page', '', null, null, 'function_viewlist');" value='<b:message key="l_upPage"/>' <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
		            <input type="button" class="button" onclick="nextPage('page', '', null, null, 'function_viewlist');" value='<b:message key="l_nextPage"/>' <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
		            <l:equal property="page.isCount" targetValue="true">
		              <input type="button" class="button" onclick="lastPage('page', '', null, null, 'function_viewlist');" value='<b:message key="l_lastPage"/>' <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
		            </l:equal>
		          </div>
		      </td>
		    </tr>
		  </table>
	</h:form>
	<script type="text/javascript">
		function addRecord() {
    		var url="/jsp/tyjg/menumanagement/add_or_update_emnu.jsp?funcgroupid="+$id("funcGroup_id").value+"&_ts="+new Date(); 
			showModal(url,null,reQueryFuncResource,520,250,"","",'<b:message key="applicationManager_l_functionAdd"></b:message>');
  		} 
  		function reQueryFuncResource(){
  			window.parent.parent.frames['IFRAMEAppFuncTree'].DTree.getSelectNode().reloadChild();
  			var _forms = $name("function_viewlist");
  			if(_forms){
  				_forms.submit();
  			}
 	 	} 
 	 	function delRecord(){
 	 		var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("至少选择一个菜单");
	  			return;
	  		}else{
	  			if(window.confirm("选中的菜单以及赋予角色的菜单也会同时被删除，是否确定要删除？")){
		  			var rows = gop.getSelectRows();
		  			var funccodes = null;
		  			for(var i = 0,len = rows.length; i < len; i++){
		  				var funccode = rows[i].getParam("funccode");
		  				if(i == 0){
		  					funccodes = "'"+funccode+"'";
		  				}else{
		  					funccodes += "," + "'"+funccode+"'";
		  				}
		  			}
		  			var myAjax = new Ajax("/menumanagement/menuManagementAction_deleteMenu.action");
		  			myAjax.addParam("funccodes",funccodes);
		  			myAjax.submit();
		  			var rtunVal = myAjax.getValue("root/data/rtunVal");
		  			if(rtunVal == "success"){
		  				alert("菜单删除成功");
		    			window.parent.parent.frames["IFRAMEAppFuncTree"].DTree.getSelectNode().reloadChild();
		  				$name("function_viewlist").submit();
		  			}else{
		  				alert("菜单删除失败");
		  			}
		  		}
	  		}
 	 	}
 	 	 function saveOrder(){
		  		var displayorder = document.getElementsByName("displayorder");
		  		var _funccodes = null;
		  		var _orderValue = null;
		  		for(var i=0;i<displayorder.length;i++){
		  			var index = displayorder[i].parentNode.parentNode.rowIndex;//tr的rowIndex
		      		var g = $id("group1");
		      		var funccode= g.get(index-1-1).getParam("funccode");//-1为扣掉表头，再-1为0开始
		      		if(i == 0){
		      			_funccodes = funccode;
		      			_orderValue = displayorder[i].value;
		      		}else{
		      			_funccodes += ","+funccode;
		      			_orderValue += ","+displayorder[i].value;
		      		}
		  		}
		  		var myAjax = new Ajax("/menumanagement/menuManagementAction_updateMenuDispleyOrder.action");
		  		myAjax.addParam("funccodes",_funccodes);
		  		myAjax.addParam("orderValue",_orderValue);
		  		myAjax.submit();
		  		var returnNode = myAjax.getResponseXMLDom();
				if( returnNode ) {
				    if( myAjax.getValue("root/data/funcResult") == "success" ) {
				        alert('保存成功！');
				        window.parent.parent.frames["IFRAMEAppFuncTree"].DTree.getSelectNode().reloadChild();
				        $name("function_viewlist").submit();
				    } 
				    else {
				        alert( '保存失败！');
				    }
				}
		  }
		  function modiRecord(){
			  	var gop = $id("group1");
		  		var len = gop.getSelectLength();
		  		if(len == 0){
		  			alert("至少选择一个菜单");
		  			return;
		  		} else if(len > 1){
		  			alert("只能选择一个菜单进行修改");
		  			return;
		  		}else{
		  			var rows = gop.getSelectRows();
		  			var funccode = rows[0].getParam("funccode");
		  			var gruopId = rows[0].getParam("funcgroupid");
		  			var strUrl = "/menumanagement/menuManagementAction_querySingleFuncMenuInfo.action?acFunction.funcCode="+funccode
		  			+"&acFunction.funcGroupId"+gruopId+"&_ts="+new Date();
		  			showModalCenter(encodeURI(strUrl), null, callBackFuncGroup, 500, 250, '修改菜单');
		  		}
		  }
		  function callBackFuncGroup(){
		  		window.parent.parent.frames["IFRAMEAppFuncTree"].DTree.getSelectNode().reloadChild();
				$name("function_viewlist").submit();
		  }
		  
		  function checkSelectAll(){
			if ($id("checkSelect").checked){
				selectAll("group1");
			}else{
				selectNone("group1");
			}
	  	 }
	  	 function cancelEven(){
	  		window.event.cancelBubble=true;
	  		event.returnValue = false; 
	  		return false;
  		}
	</script>
  </body>
</html>
