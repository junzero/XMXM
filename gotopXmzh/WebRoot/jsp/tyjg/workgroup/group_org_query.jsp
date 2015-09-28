<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common.jsp"%>
<html>
<%
	//获取标签中使用的国际化资源信息
	String emp_list = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_title_EmpList");
%>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><b:message key='workgroupManager_l_addPositionEmp' /></title> <!--增加岗位员工 -->
    <script>
	 //实现全选复选框
	 function checkSelectAll(){
		if ($id("checkSelect").checked){
			selectAll("group1");
		}else{
			selectNone("group1");
		}
	 }    
	     
    function addEmpPosition() {
        var g = $id("group1");
        if (g.getSelectLength() < 1) {
            alert("<b:message key='l_m_alert_mustSelectOneOrMore' />");//<!--至少选择一行！ -->
            return;
        }
        var frm = $name("page_form");
        frm.elements["_eosFlowAction"].value = "insert";
        frm.submit();
        window.closeD();
        parent.parent.parent.tree1.getSelectNode().reloadChild();
    }
     /*
      *  自定义初始化按钮样式
      */
	 function custInit(){  
		initButtonStyle();
	 }
    </script>
  </head>
  <body class="eos-panel-table">
    <h:form name="query_form" action="org.gocom.abframe.org.workgroup.GroupOrgAdd.flow" method="post">
      <input type="hidden" name="_eosFlowAction" value="pageQuery"/>
        <table align="center" border="0" width="100%" class="form_table">
          <tr>
	        <td class="form_label">
				机构编号
			</td>
            <td colspan="1">
              <h:text property="hmGroup/orgcode"/>  
            </td>
	        <td class="form_label">  
	           机构名称
	        </td>
            <td colspan="1">
              <h:text property="hmGroup/orgname"/> 
            </td>
          </tr>
          <tr class="form_bottom">
            <td colspan="6" class="form_bottom">
              <b:message key="l_pageDisplay" /> <!--每页显示 -->
              <h:text size="2" property="page/length"  scope="flow" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
              <input type="hidden" name="page/begin" scope="flow" value="0">
              <input type="hidden" name="page/isCount" scope="flow" value="true">
              <input type="submit" value="查询">&nbsp;<input type="button" value='<b:message key="l_reset"></b:message>' onclick="javascript:$name('query_form').reset();">
            </td>
          </tr>
        </table>
    </h:form>
    <h:form name="page_form" action="org.gocom.abframe.org.workgroup.GroupOrgAdd.flow" method="post">
      <input type="hidden" name="_eosFlowAction" value="pageQuery"/>
	  <h:hiddendata property="page" scope="flow"/>
        <table align="center" border="0" width="100%" class="EOS_table">
          <tr>
	       <td colspan="4" class="eos-panel-title">&nbsp;机构列表</td><!--员工列表 -->
	      </tr>
          <tr>
	        <th align="center">
	          <l:greaterThan property="page/size" targetValue="0" compareType="number" scope="f">
		      	<input  type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll();"> 
		      </l:greaterThan>        
	          <b:message key="l_select" /> <!--选择 -->
	        </th>
            <th>
              机构编号
            </th>
            <th>   
			  机构名称
            </th>
            <th>
              机构等级
            </th>
          </tr>
          <w:checkGroup id="group1">
            <l:iterate property="orgGroups" id="id1">
              <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
                <td align="center">
                  <w:rowCheckbox afterSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))" afterUnSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))">
                    <h:param name='select_objs/orgid' iterateId='id1' property='ORGID' indexed='true' />
                  </w:rowCheckbox>
                </td>
                <td>
                  <b:write iterateId="id1" property="ORGCODE"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="ORGNAME"/>
                </td>
                <td>
                  <d:write iterateId="id1" property="ORGDEGREE" dictTypeId="ABF_ORGDEGREE"/>
                </td>
              </tr>
            </l:iterate>
          </w:checkGroup>
          <tr>
            <td colspan="5" class="command_sort_area">
            <div class="h3">  
              <l:notEqual property="page/count" targetValue="0" scope="flow">
              		<!--  增加  关闭-->
                  <input type="button" value="<b:message key='l_add'/>" onclick="addEmpPosition();" id="deleteButton"> <input type="button" value="<b:message key='l_close'/>" onclick="window.closeD()">   
                  <h:hidden  id="updateButton"/>
              </l:notEqual>
              </div>
              <div class="h4">
	            <l:equal property="page/isCount" targetValue="true" scope="flow" >
	              <b:message key = 'l_total'/>       <!--共 -->
	              <b:write property="page/count" scope="flow" />
	              <b:message key = 'l_recordNO.'/>   <!--条记录 第 -->
	              <b:write property="page/currentPage" scope="flow" />
	              <b:message key = 'l_page'/>/       <!--页 -->
	              <b:write property="page/totalPage" scope="flow" />
	              <b:message key = 'l_page'/>        <!--页 -->
	            </l:equal>
	            <l:equal property="page/isCount" targetValue="false" scope="flow" >
	              <b:message key = 'l_NO.'/>         <!--第 -->
	              <b:write property="page/currentPage" scope="flow" />
	              <b:message key = 'l_page'/>        <!--页 -->
	            </l:equal>
	            <!--首页 上页 下页 尾页 -->
	            <input type="button" onclick="firstPage('page', 'pageQuery', null, null, 'page_form');" value="<b:message key = 'l_firstPage'/>"  <l:equal property="page/isFirst" targetValue="true" scope="flow" >disabled</l:equal> >
	            <input type="button" onclick="prevPage('page', 'pageQuery', null, null, 'page_form');" value="<b:message key = 'l_upPage'/>" <l:equal property="page/isFirst" targetValue="true" scope="flow" >disabled</l:equal> >
	            <input type="button" onclick="nextPage('page', 'pageQuery', null, null, 'page_form');" value="<b:message key = 'l_nextPage'/>" <l:equal property="page/isLast" targetValue="true" scope="flow" >disabled</l:equal> >
	            <l:equal property="page/isCount" targetValue="true" scope="flow" >
	              <input type="button" onclick="lastPage('page', 'pageQuery', null, null, 'page_form');" value="<b:message key = 'l_lastPage'/>" <l:equal property="page/isLast" targetValue="true" scope="flow" >disabled</l:equal> >
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