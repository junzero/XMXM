<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      下级机构列表
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<h:script src="/js/tyjg/orgmanagement/orgorder.js"/>
  </head>
  <body style="margin: 0px;">
  	<e:datasource name="page" type="entity" path="com.eos.foundation.PageCond" />
  	 <h:form name="viewlist1" action="/orgmanagement/organizationAction_queryOrgAllChildOrg.action" method="post">
  		<h:hidden property="tomorganization.orgId" id="tomorganization.orgId"/>
	  	<h:hidden property="page.begin"/>
	    <h:hidden property="page.length"/>
	    <h:hidden property="page.count"/>
	    <h:hidden property="page.isCount"/>
  		<table align="center" border="0" width="100%" class="EOS_table">
	  		<tr>
	      	 	<td colspan="6" class="eos-panel-title">单位查询结果</td>
	        </tr>
  			<th>单位名称</th>
  			<th>单位代码</th>
  			<th>单位类型</th>
  			<th>单位状态</th>
  			<th>显示顺序</th>
  			<l:iterate id="p1" property="orgList">
  				<tr>
  					<td>
  						<b:write property="orgName" iterateId="p1"/>
  					</td>
  					<td>
  						<b:write property="orgCode" iterateId="p1"/>
  					</td>
  					<td>
  						<d:write dictTypeId="ABF_ORGTYPE" property="orgType" iterateId="p1"/>
  					</td>
  					<td>
  						<d:write dictTypeId="ABF_ORGSTATUS" property="status" iterateId="p1"/>
  					</td>
  					<td>
  						<b:write property="displayOrder" iterateId="p1"/>
  					</td>
  				</tr>
  			</l:iterate>
  			<tr>
          <td colspan="5" class="command_sort_area">
            <div class="h3">	
            	<input type="button" value="调整顺序" onclick="openOrderWin();" class="button"
            	<l:lessEqual property="page.count" targetValue="0" > disabled="disabled"</l:lessEqual> >
             </div>
             <div class="h4">
              <l:equal property="page.isCount" targetValue="true">
                共
                <b:write property="page.count"/>
                条记录 第
                <b:write property="page.currentPage"/>
                页/
                <b:write property="page.totalPage"/>
                页
              </l:equal>
              <l:equal property="page.isCount" targetValue="false">
                第
                <b:write property="page.currentPage"/>
                页
              </l:equal>
              <input type="button" class="button" onclick="firstPage('page', '', null, null, 'viewlist1');" value="首页"  <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
              <input type="button" class="button" onclick="prevPage('page', '', null, null, 'viewlist1');" value="上页" <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
              <input type="button" class="button" onclick="nextPage('page', '', null, null, 'viewlist1');" value="下页" <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
              <l:equal property="page.isCount" targetValue="true">
                <input type="button" class="button" onclick="lastPage('page', '', null, null, 'viewlist1');" value="尾页" <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
              </l:equal>
            </div>
          </td>
        </tr>
  		</table>
  	</h:form>
  </body>
</html>
