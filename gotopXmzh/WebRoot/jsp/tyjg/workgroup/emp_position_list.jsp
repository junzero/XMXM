<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<html>
<head>
<title></title>

<%
	//岗位查询条件 
	String posi_query_cond = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("positionEmp_l_title_posiEmp")
	                       + com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_query_cond");
	//岗位查询结果
	String posi_query_result = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("positionEmp_l_title_posiEmp")
	                         + com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_query_result");
%>

<script language="javascript">
    //刷新指定树的选中节点 
	function refreshTreeNode(tree){
		try{
			tree.getSelectNode().reloadChild();
		}catch(e){
			//alert(e);
		}
	}
   
    /*  
     *  模态窗口回调函数，关闭模态窗口时，刷新父窗口的数据
     */
    function callBack() {
        try{
	        refreshTreeNode(parent.parent.window.frames['groupTree'].groupTree);	        
        }catch(e){
        }
        try{
        	parent.parent.orgTree.getSelectNode().getParent().reloadChild();
        }catch(e){
        }
        var frm = $name("form1");
        frm.elements["_eosFlowAction"].value = "action2";
        frm.submit();
    }
    
    /*  
     *  增加岗位人员
     */
    function addRecord() {
        var url = "org.gocom.abframe.org.position.PositionEmp.flow";
        url += "?_eosFlowAction=pageQuery";
        url += "&parentPosID=";
        url += '<b:write property="parentPosID" scope="flow"/>';
        url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	    showModalCenter(url, "", callBack, 550, 420, '<b:message key="positionEmp_l_title_addPositionEmp"/>');
    }
    
    /*  
     *  删除岗位人员
     */
    function deleteRecord() {
        var frm = $name("form1");
        var g = $id("group1");
        if(  g.getSelectLength() < 1 ) {
            alert( '<b:message key="l_m_alert_mustSelectOneOrMore"/>' ); // <!-- 必须至少选择一行 -->
            return;
        }
        if( !confirm("<b:message key='workgroupManager_m_delete_emp_confirm' />") ) {// <!--您确认要删除选中的员工？ -->
	         return;
	    }  
        frm.elements["_eosFlowAction"].value = "delete";
        frm.submit();   
        try{
	        refreshTreeNode(parent.parent.window.frames['groupTree'].groupTree);	        
        }catch(e){
        }
        try{
        	parent.parent.orgTree.getSelectNode().getParent().reloadChild();
        }catch(e){
        }
    }
    
    /* 
     *  实现全选复选框
     */
     function checkSelectAll(){
		if ($id("checkSelect").checked){
			selectAll("group1");
		}else{
			selectNone("group1");
		}
     } 
     
     /*
      *  自定义初始化按钮样式
      */
	 function custInit(){  
		initButtonStyle();
	 }
</script>

</head>
<body topmargin="0" leftmargin="0">
    <e:datasource name="criteria" type="entity" path="com.primeton.das.criteria.criteraiType" />
    <h:form name="query_form" action="org.gocom.abframe.org.position.PositionEmp.flow" method="post">
      <input type="hidden" name="_eosFlowAction" value="query"/>
       <h:hidden property="parentPosID" scope="flow"/>
       <h:hidden name="criteria/_entity" value="org.gocom.abframe.datasetExp.organization.OmEmpposition"/>
       <h:hidden name="criteria/_expr[1]/omPosition.positionid" property="parentPosID" scope="flow"/>
      
      <w:panel id="panel1" width="100%" title="群组员工查询">
        <table align="center" border="0" width="100%" class="form_table">
                   
          <tr>
            <td class="form_label">  <!--  登陆名 -->
              <b:message key="positionEmp_omEmployee.userid"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td >
                <input type="text" name="criteria/_ref[1]/_expr[1]/userid"/>
                <h:hidden property="criteria/_expr[2]/_ref" value="1"/>
                <h:hidden property="criteria/_expr[2]/_op" value="in"/>
                <h:hidden property="criteria/_expr[2]/omEmployee.empid"/>

                <h:hidden property="criteria/_ref[1]/_expr[1]/_op" value="like"/>
                <h:hidden property="criteria/_ref[1]/_expr[1]/_likeRule" value="all"/>
                <h:hidden property="criteria/_ref[1]/_id" value="1"/>
                <h:hidden property="criteria/_ref[1]/_entity" value="org.gocom.abframe.datasetExp.organization.OmEmployee"/>
                               
            </td>
            <td class="form_label">  <!-- 操作员姓名 -->
              <b:message key="positionEmp_omEmployee.empname"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td >
                <input type="text" name="criteria/_ref[1]/_expr[2]/empname"/>
                 
                <h:hidden property="criteria/_ref[1]/_expr[2]/_op" value="like"/>
                <h:hidden property="criteria/_ref[1]/_expr[2]/_likeRule" value="all"/>
            </td>
          </tr>
          <tr class="form_bottom">
            <td colspan="6" class="form_bottom">
              <b:message key="l_pageDisplay"></b:message>
              <h:text size="2" property="page/length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
              <input type="hidden" name="page/begin" value="0">
              <input type="hidden" name="page/isCount" value="true">
              <input type="submit" value='<b:message key="l_query"></b:message>'>&nbsp;
              <input type="button" value='<b:message key="l_reset"></b:message>' onclick="javascript:$name('query_form').reset();">
            </td>
          </tr>
        </table>
      </w:panel>
    </h:form>
            
    <h:form name="form1" action="org.gocom.abframe.org.position.PositionEmp.flow" method="post">
         <input type="hidden" name="_eosFlowAction" value="query"/>
         <table align="center" border="0" width="100%" class="EOS_table">
               
         <h:hidden property="parentPosID" name="parentPosID" scope="flow"/>
         
         <!-- 隐藏分页信息 -->
         <h:hidden property="page/length"/>
         <h:hidden property="page/begin"/>
         <h:hidden property="page/isCount"/>
         <h:hidden property="page/count"/>
         
         <!-- 隐藏匿查询信息 -->
         <h:hidden name="criteria/_entity" value="org.gocom.abframe.datasetExp.organization.OmEmpposition"/>
         <h:hidden name="criteria/_expr[1]/omPosition.positionid" property="parentPosID" scope="flow"/>
         <h:hidden property="criteria/_ref[1]/_expr[1]/userid"/>
         <h:hidden property="criteria/_expr[2]/_ref" value="1"/>
         <h:hidden property="criteria/_expr[2]/_op" value="in"/>
         <h:hidden property="criteria/_expr[2]/omEmployee.empid"/>

         <h:hidden property="criteria/_ref[1]/_expr[1]/_op" value="like"/>
         <h:hidden property="criteria/_ref[1]/_expr[1]/_likeRule" value="all"/>
         <h:hidden property="criteria/_ref[1]/_id" value="1"/>
         <h:hidden property="criteria/_ref[1]/_entity" value="org.gocom.abframe.datasetExp.organization.OmEmployee"/>
         
         <h:hidden property="criteria/_ref[1]/_expr[2]/empname"/>
         <h:hidden property="criteria/_ref[1]/_expr[2]/_op" value="like"/>
         <h:hidden property="criteria/_ref[1]/_expr[2]/_likeRule" value="all"/>
         <tr>
	       <td colspan="4" class="eos-panel-title">&nbsp;<%=posi_query_result %></td>
	      </tr> 
         <tr>
            <th align="center">
            <l:greaterThan property="page/size" targetValue="0" compareType="number">
               <input type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll();"> 
            </l:greaterThan>
              <b:message key="l_select"></b:message>
            </th>
            <th>                 <!-- 登陆名 -->
              <b:message key="positionEmp_omEmployee.userid"></b:message>
            </th>
            <th>                 <!-- 操作员姓名 -->
              <b:message key="positionEmp_omEmployee.empname"></b:message>
            </th>
            <th align="center">  <!-- 岗位名称 -->
              <b:message key="positionManager_OmPosition.posiname"></b:message>
            </th>
          </tr>
          <w:checkGroup id="group1">
          <l:iterate property="empPosList" id="id1">
              <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
                <td align="center">
                    <w:rowCheckbox afterSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))" afterUnSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))">
                        <h:param name='select_objs[*]/omEmployee/empid' iterateId='id1' property='omEmployee/empid' indexed='true' />
                    </w:rowCheckbox>
                </td>
                <td>
                  <b:write iterateId="id1" property="omEmployee/userid"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="omEmployee/empname"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="omPosition/posiname"/>
                </td>
                </tr>
            </l:iterate>
            </w:checkGroup>
            <tr>
              <td colspan="5" class="command_sort_area">
                <div class="h3">
                <input type="button" value='<b:message key="l_add"/>' onclick="addRecord();">
                <l:greaterThan property="page/size" targetValue="0" compareType="number">
                   <input type="button" value='<b:message key="l_delete"/>' onclick="deleteRecord();" id="deleteButton" disabled="true">
                </l:greaterThan>
              </div>
              <div class="h4">
                <l:equal property="page/isCount" targetValue="true" >
                  <b:message key="l_total"></b:message>
                  <b:write property="page/count" />
                  <b:message key="l_recordNO."></b:message>
                  <b:write property="page/currentPage" />
                  <b:message key="l_page"></b:message>/
                  <b:write property="page/totalPage" />
                  <b:message key="l_page"></b:message>
                </l:equal>
                <l:equal property="page/isCount" targetValue="false" >
                  <b:message key="l_NO."></b:message>
                  <b:write property="page/currentPage" />
                  <b:message key="l_page"></b:message>
                </l:equal>
                <input type="button" onclick="firstPage('page', 'query', null, null, 'form1');" value='<b:message key="l_firstPage"></b:message>'  <l:equal property="page/isFirst"  targetValue="true">disabled</l:equal> >
                <input type="button" onclick="prevPage('page', 'query', null, null, 'form1');" value='<b:message key="l_upPage"></b:message>' <l:equal property="page/isFirst"  targetValue="true">disabled</l:equal> >
                <input type="button" onclick="nextPage('page', 'query', null, null, 'form1');" value='<b:message key="l_nextPage"></b:message>' <l:equal property="page/isLast"  targetValue="true">disabled</l:equal> >
                <l:equal property="page/isCount" targetValue="true">
                  <input type="button" onclick="lastPage('page', 'query', null, null, 'form1');" value='<b:message key="l_lastPage"></b:message>' <l:equal property="page/isLast"  targetValue="true">disabled</l:equal> >
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