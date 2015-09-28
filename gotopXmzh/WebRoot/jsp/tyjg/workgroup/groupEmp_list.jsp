<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
<%
	//获取标签中使用的国际化资源信息
	String title=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_title_groupEmpList");
 %>
<title><%=title%></title> <!--工作组员工查询 -->
<script>
	 //刷新指定树的选中节点 
	function refreshTreeNode(tree){
		try{
			tree.getSelectNode().reloadChild();
		}catch(e){
			//alert(e);
		}
	}
	 
	 //实现全选复选框
	 function checkSelectAll(){
		if ($id("checkSelect").checked){
			selectAll("group1");
		}else{
			selectNone("group1");
		}
	 } 

      //模态窗口回调函数，关闭模态窗口时，刷新父窗口的数据
    function callBack() {
        var frm = $name("viewlist1");
        frm.elements["_eosFlowAction"].value = "reQuery";
        frm.submit();
    }
    //增加工作组员工
    function addRecord() {
		if(window.parent.$name("group/empid").value!=$name("userIntegrated/empid").value){
			alert('操作失败！只有创建人才有删除权限');
			return;
		}
        var url = "org.gocom.abframe.org.workgroup.GroupEmpMaintain.flow";
        url += "?_eosFlowAction=addEmp";
        url += "&groupID=";
        url += '<b:write property="groupID" scope="flow"/>';
        url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	    showModalCenter(url, "", callBack, 550, 400, "增加群组员工"); <!--增加工作组员工 -->
    }
    //删除工作组员工
    function delRecord() {
		if(window.parent.$name("group/empid").value!=$name("userIntegrated/empid").value){
			alert('操作失败！只有创建人才有删除权限');
			return;
		}
        var frm = $name("viewlist1");
        var g = $id("group1");
        if(  g.getSelectLength() < 1 ) {
            alert("<b:message key='l_m_alert_mustSelectOneOrMore' />");//<!--至少选择一行！ -->
            return;
        }
	    if( !confirm("<b:message key='workgroupManager_m_delete_emp_confirm' />") ) { //<!--您确认要删除选中的员工？ -->
	         return;
	    }        
        frm.elements["_eosFlowAction"].value = "delete";
        frm.submit();
    }
      /* load初始化函数
      *
      */
	 function custInit(){  
	 	var isReload = '<b:write property="isReload"/>';
	 	if(isReload){
	 		refreshTreeNode(parent.parent.window.frames['groupTree'].groupTree);
	 	}
		initButtonStyle();
	 }
</script>
</head>
<body  leftmargin="0" topmargin="0" rightmargin="0">

<%--queryform configurationID:20080821154419 --%>
<h:form name="queryForm" action="org.gocom.abframe.org.workgroup.GroupEmpMaintain.flow" checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
  <input type="hidden" name="_eosFlowAction" value="pageQuery"/>
  <h:hidden name="criteria/_entity" value="org.gocom.abframe.datasetExp.organization.OmEmpgroup"/>
  <h:hidden name="criteria/_expr[1]/omGroup.groupid" property="groupID" scope="flow"/>
  <w:panel id="panel1" width="100%" title="群组员工查询"> <!--工作组员工查询 -->
    <table align="center" border="0" width="100%" class="form_table">
      <tr>
        <td class="form_label">  
           <b:message key="workgroupManager_l_omEmployee.userid"></b:message><b:message key="l_colon"></b:message> <!--  登陆名 -->
        </td>
        <td colspan="1">
          <h:text property="criteria/_expr[2]/omEmployee.userid"/>
          <h:hidden property="criteria/_expr[2]/_op" value="like"/>
          <h:hidden property="criteria/_expr[2]/_likeRule" value="all"/>       
        </td>
        <td class="form_label">  
          <b:message key="workgroupManager_l_omEmployee.empname"></b:message><b:message key="l_colon"></b:message><!-- 操作员姓名 -->
        </td>
        <td colspan="1">
          <h:text property="criteria/_expr[3]/omEmployee.empname"/>
          <h:hidden property="criteria/_expr[3]/_op" value="like"/>
          <h:hidden property="criteria/_expr[3]/_likeRule" value="all"/>
        </td>
      </tr>
      <tr class="form_bottom">
        <td colspan="4" class="form_bottom">
          <b:message key="l_pageDisplay" /> <!--每页显示 -->
          <h:text size="2" property="page/length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
          <input type="hidden" name="page/begin" value="0">
          <input type="hidden" name="page/isCount" value="true">
          <input type="submit" value="<b:message key='l_query' />">&nbsp;
              <input type="button" value='<b:message key="l_reset"></b:message>' onclick="javascript:$name('query_form').reset();">
        </td>
      </tr>
    </table>
  </w:panel>
</h:form>
 

<%--viewlist configurationID:20080821114714 --%>
<h:form name="viewlist1" action="org.gocom.abframe.org.workgroup.GroupEmpMaintain.flow" checkType="blur" target="_self" method="post">
  <input type="hidden" name="_eosFlowAction" value="pageQuery" >
  <!-- 隐藏匿分页及查询信息，使用“page_form”提交时可会自动提交查询信息变量 -->
  <h:hiddendata property="page"  />
  <h:hidden property="userIntegrated/empid" scope="session" />
  <h:hiddendata property="criteria"  />
    <table align="center" border="0" width="100%" class="EOS_table">
         <tr>
	       <td colspan="4" class="eos-panel-title">&nbsp;群组员工列表</td>
	      </tr>
         
         <tr>
            <th align="center">
            <l:greaterThan property="page/size" targetValue="0" compareType="number">
               <input type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll();"> 
            </l:greaterThan>
              <b:message key="l_select"></b:message>
            </th>
            <th>                 <!-- 登陆名 -->
              <b:message key="workgroupManager_l_omEmployee.userid"></b:message>
            </th>
            <th>                 <!-- 操作员姓名 -->
              <b:message key="workgroupManager_l_omEmployee.empname"></b:message>
            </th>
            <th align="center">  <!-- 工作组名称 -->
              <b:message key="workgroupManager_OmGroup.groupname"></b:message>
            </th>
          </tr>
      <w:checkGroup id="group1">
        <l:iterate property="empGroups" id="id1">
          <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
            <td align="center">
              <w:rowCheckbox afterSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))" afterUnSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))">
                <h:param name='selectedObjects[*]/omEmployee/empid' iterateId='id1' property='omEmployee/empid' indexed='true' />
              </w:rowCheckbox>
            </td>
                <td>
                  <b:write iterateId="id1" property="omEmployee/userid"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="omEmployee/empname"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="omGroup/groupname"/>
                </td>
          </tr>
        </l:iterate>
      </w:checkGroup>
      <tr>
        <td colspan="4" class="command_sort_area">
        <div class="h3">
          <input type="button" value="<b:message key = 'l_add'/>" onclick="addRecord();"> <!--新增 -->
          <l:greaterThan property="page/size" targetValue="0" compareType="number"  >
            <input type="button" value="<b:message key = 'l_delete'/>" onclick="delRecord();" id="deleteButton"> <!--删除 -->
          </l:greaterThan>
        </div>
        <div class="h4">
            <l:equal property="page/isCount" targetValue="true">
              <b:message key = 'l_total'/>       <!--共 -->
              <b:write property="page/count"/>
              <b:message key = 'l_recordNO.'/>   <!--条记录 第 -->
              <b:write property="page/currentPage"/>
              <b:message key = 'l_page'/>/       <!--页 -->
              <b:write property="page/totalPage"/>
              <b:message key = 'l_page'/>        <!--页 -->
            </l:equal>
            <l:equal property="page/isCount" targetValue="false">
              <b:message key = 'l_NO.'/>         <!--第 -->
              <b:write property="page/currentPage"/>
              <b:message key = 'l_page'/>        <!--页 -->
            </l:equal>
            <!--首页 上页 下页 尾页 -->
            <input type="button" onclick="firstPage('page', 'pageQuery', null, null, 'page_form');" value="<b:message key = 'l_firstPage'/>"  <l:equal property="page/isFirst" targetValue="true">disabled</l:equal> >
            <input type="button" onclick="prevPage('page', 'pageQuery', null, null, 'page_form');" value="<b:message key = 'l_upPage'/>" <l:equal property="page/isFirst" targetValue="true">disabled</l:equal> >
            <input type="button" onclick="nextPage('page', 'pageQuery', null, null, 'page_form');" value="<b:message key = 'l_nextPage'/>" <l:equal property="page/isLast" targetValue="true">disabled</l:equal> >
            <l:equal property="page/isCount" targetValue="true">
              <input type="button" onclick="lastPage('page', 'pageQuery', null, null, 'page_form');" value="<b:message key = 'l_lastPage'/>" <l:equal property="page/isLast" targetValue="true">disabled</l:equal> >
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