<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      应用系统功能组列表
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body style="margin: 0px;">
    <e:datasource name="acFunctionGroup" type="bean" path="com.gotop.tyjg.menumanagement.model.AcFunctionGroup"/>
  	<h:form name="appFuncGroup_viewlist" action="/menumanagement/menuManagementAction_queryAppChildGroupList.action" 
  		checkType="blur" target="_self" method="post">
  		<h:hidden id = "application_id" property = "acFunctionGroup.appId"/>
  		<h:hidden property="page.begin"/>
	    <h:hidden property="page.length"/>
	    <h:hidden property="page.count"/>
	    <h:hidden property="page.isCount"/>
	    <table align="center" border="0" width="100%" class="EOS_table">
	      <tr>
	       <td colspan="6" class="eos-panel-title">&nbsp;&nbsp;功能组列表</td>
	      </tr>
	      <tr>
	        <th width="8%" align="center">
			  <input type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll();"> 
			  <b:message key="l_select"></b:message>
		    </th>
	        <th>
	          <b:message key="applicationManager_AcFuncGroup.funcgroupname"></b:message>
	        </th>
	        <th>
	          <b:message key="applicationManager_AcFuncGroup.grouplevel"></b:message>
	        </th>
	        <th>
	          <b:message key="applicationManager_AcFuncGroup.funcgroupseq"></b:message>
	        </th>
	        <th>
	          <b:message key="applicationManager_AcFuncGroup.isleaf"></b:message>
	        </th>
			<th>
				显示顺序
			</th>
	      </tr>
	      <w:checkGroup id="group1">
	        <l:iterate property="acFunGroupList" id="id1">
	          <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
	            <td align="center">
	              <w:rowCheckbox>
	                <h:param name='appid' property='acFunctionGroup.appId'/>
	                <h:param name='funcgroupid' iterateId='id1' property='funcGroupId'/>
	                <h:param name='funcGroupName' iterateId='id1' property='funcGroupName'/>
	                <h:param name='parentGroup' iterateId='id1' property='parentGroup'/>
	                <h:param name='parentGroupName' iterateId='id1' property='parentGroupName'/>
	              </w:rowCheckbox>
	            </td>
	            <td>
	              <b:write iterateId="id1" property="funcGroupName"/>
	            </td>
	            <td>
	              <b:write iterateId="id1" property="groupLevel"/>
	            </td>
	            <td>
	              <b:write iterateId="id1" property="funcGroupSeq"/>
	            </td>
	            <td>
	              <d:write iterateId="id1" property="isLeaf" dictTypeId="ABF_YESORNO"/>
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
	          <input type="button" class="button" value='<b:message key="l_add"></b:message>' onclick="addRecord();">
	          <l:greaterThan property="page.size" targetValue="0" compareType="number">
	            <input type="button" class="button"   value='<b:message key="l_update"></b:message>' 
	            		onclick="modiRecord();" id="updateButton">
	          </l:greaterThan>
	          <l:greaterThan property="page.size" targetValue="0" compareType="number">
	            <input type="button" class="button"   value='<b:message key="l_delete"></b:message>'
	             onclick="delRecord();" id="deleteButton">
	          </l:greaterThan>
			<l:greaterThan property="page.size" targetValue="0" compareType="number">
			  <input type="button" class="button"   onclick="saveOrder()" value="保存顺序" />
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
	            <input type="button" class="button"   
	            	onclick="firstPage('page', '', null, null, 'appFuncGroup_viewlist');" 
	            	value='<b:message key="l_firstPage"/>'  
	            	<l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
	            <input type="button" class="button" 
	              onclick="prevPage('page', '', null, null, 'appFuncGroup_viewlist');" 
	              value='<b:message key="l_upPage"/>' 
	              <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
	            <input type="button" class="button"   
	            onclick="nextPage('page', '', null, null, 'appFuncGroup_viewlist');" 
	            value='<b:message key="l_nextPage"/>' <l:equal property="page.isLast" 
	            targetValue="true">disabled</l:equal> >
	            <l:equal property="page.isCount" targetValue="true">
	              <input type="button" class="button"   
	              onclick="lastPage('page', '', null, null, 'appFuncGroup_viewlist');"
	               value='<b:message key="l_lastPage"/>' 
	               <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
	            </l:equal>
	          </div>
	        </td>
	      </tr>
	    </table>
	</h:form>
	<script type="text/javascript">
		  function saveOrder(){
		  		var displayorder = document.getElementsByName("displayorder");
		  		var _groupids = null;
		  		var _orderValue = null;
		  		for(var i=0;i<displayorder.length;i++){
		  			var index = displayorder[i].parentNode.parentNode.rowIndex;//tr的rowIndex
		      		var g = $id("group1");
		      		var funcgroupid= g.get(index-1-1).getParam("funcgroupid");//-1为扣掉表头，再-1为0开始
		      		if(i == 0){
		      			_groupids = funcgroupid;
		      			_orderValue = displayorder[i].value;
		      		}else{
		      			_groupids += ","+funcgroupid
		      			_orderValue += ","+displayorder[i].value;
		      		}
		  		}
		  		if(_orderValue == null || _orderValue==""){
		  			_orderValue = ",";
		  		}
		  		var myAjax = new Ajax("/menumanagement/menuManagementAction_updateGroupDisplayOrder.action");
		  		myAjax.addParam("groupids",_groupids);
		  		myAjax.addParam("orderValue",_orderValue);
		  		myAjax.submit();
		  		var returnNode = myAjax.getResponseXMLDom();
				if( returnNode ) {
				    if( myAjax.getValue("root/data/funcResult") == "success" ) {
				        alert('保存成功！');
				        window.parent.parent.frames["IFRAMEAppFuncTree"].DTree.getSelectNode().reloadChild();
				        $name("appFuncGroup_viewlist").submit();
				    } 
				    else {
				        alert( '保存失败！');
				    }
				}
		  }
		  //新增功能组
		function addRecord(){
			var appid = $id('application_id').value;
			var url = "/jsp/tyjg/menumanagement/add_func_group.jsp";
		    url = url + "?appid="+appid;
	        url += '&_ts='+(new Date()).getTime();  
			showModalCenter(url, "", callBackFuncGroup, 500, 220, '新增功能组');
		} 
		
		//新增完成回调函数
		function callBackFuncGroup()  {
	    	$name("appFuncGroup_viewlist").submit();
	    	window.parent.parent.frames["IFRAMEAppFuncTree"].DTree.getSelectNode().reloadChild();
	    } 
	    function checkSelectAll(){
			if ($id("checkSelect").checked){
				selectAll("group1");
			}else{
				selectNone("group1");
			}
	  	}
	  	function modiRecord(){
	  		var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("至少选择一个工作组");
	  			return;
	  		} else if(len > 1){
	  			alert("只能选择一个工作组进行修改");
	  			return;
	  		}else{
	  			var rows = gop.getSelectRows();
	  			var appid = rows[0].getParam("appid");
	  			var gruopName = rows[0].getParam("funcGroupName");
	  			var gruopId = rows[0].getParam("funcgroupid");
	  			var parentGroup = rows[0].getParam("parentGroup");
	  			var parentGroupName = rows[0].getParam("parentGroupName");
	  			var strUrl = "/jsp/tyjg/menumanagement/update_func_group.jsp?appid="+appid+"&groupName="+gruopName
	  							+"&gruopId="+gruopId+"&parentGroup="+parentGroup+"&parentGroupName="+parentGroupName+"&_ts="+new Date();
	  			showModalCenter(encodeURI(strUrl), null, null, 500, 220, '修改功能组');
	  		}
	  	}
	  	function delRecord(){
	  		var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("至少选择一个工作组");
	  			return;
	  		}else{
	  			if(window.confirm("选中的工作组中若包含子工作组以及下属菜单也会同时被删除，是否确定要删除？")){
		  			var rows = gop.getSelectRows();
		  			var groupids = null;
		  			for(var i = 0,len = rows.length; i < len; i++){
		  				var gruopId = rows[i].getParam("funcgroupid");
		  				if(i == 0){
		  					groupids = gruopId;
		  				}else{
		  					groupids += "," + gruopId;
		  				}
		  			}
		  			var myAjax = new Ajax("/menumanagement/menuManagementAction_deleteBeathGroup.action");
		  			myAjax.addParam("groupids",groupids);
		  			myAjax.submit();
		  			var rtunVal = myAjax.getValue("root/data/rtunVal");
		  			if(rtunVal == "success"){
		  				alert("工作组删除成功");
		    			window.parent.parent.frames["IFRAMEAppFuncTree"].DTree.getSelectNode().reloadChild();
		  				$name("appFuncGroup_viewlist").submit();
		  			}else{
		  				alert("工作组删除失败");
		  			}
		  		}
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
