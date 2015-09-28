<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>
<%--
- Author(s): Administrator
- Date: 2011-10-29 22:26:26
- Description:
--%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      viewlist维护
    </title>
  </head>
  <body topmargin="0">
    <e:datasource name="criteria" type="entity" path="com.primeton.das.criteria.criteriaType" />
    <e:datasource name="page" type="entity" path="com.eos.foundation.PageCond" />
    <e:datasource name="abftdbsybs" type="entity" path="com.primeton.purview.tools.AbfTDbsyb" />
    <queryform id= "2ab716f5-146f-47da-9ce6-8b93609903ed">
      <h:form  name="query_form" action="/richweb_demo.do" checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
        <input type="hidden" name="method" value="viewDataList" />
        <w:panel id="panel1" width="100%" title="viewlist查询">
          <table align="center" border="0" width="100%" class="form_table">
            <tr>
              <td class="form_label">
                系统名称
              </td>
              <td colspan="1">
                <h:text property="criteria/_expr[1]/vXtmc"/>
                <h:hidden property="criteria/_expr[1]/_op" value="="/>
              </td>
              <td class="form_label">
                待办事宜名称
              </td>
              <td colspan="1">
                <h:text property="criteria/_expr[3]/vDbsymc"/>
                <h:hidden property="criteria/_expr[3]/_op" value="="/>
              </td>
            </tr>
            <tr class="form_bottom">
              <td colspan="4" class="form_bottom">
                <input type="hidden" name="criteria/_entity" value="com.primeton.purview.tools.AbfTDbsyb">
                每页显示
                <h:text size="2" property="page/length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
                <input type="hidden" name="page/begin" value="0">
                <input type="hidden" name="page/isCount" value="true">
                <input type="submit" value="查询" class="button">
              </td>
            </tr>
          </table>
        </w:panel>
      </h:form>
    </queryform>
    <br/>
    <viewlist id= "200d7bee-647b-417a-b45b-8987ca35041f">
      <h:form name="page_form" action="/richweb_demo.do" method="post">
        <input type="hidden" name="method" value="viewDataList" />
        <h:hiddendata property="criteria" />
        <h:hidden property="page/begin"/>
        <h:hidden property="page/length"/>
        <h:hidden property="page/count"/>
        <h:hidden property="page/isCount"/>
        <w:panel id="list_panel" width="100%" title="viewlist列表">
          <table align="center" border="0" width="100%" class="EOS_table">
            <tr>
              <th align="center">
                选择
              </th>
              <th>
                来源人部门
              </th>
              <th>
                预留项
              </th>
            </tr>
            <w:checkGroup id="group1">
              <l:iterate property="orgs" id="id1">
                <tr class="<l:output evenOutput='EOS_table_row' />">
                  <td align="center">
                    <w:rowCheckbox>
                      <h:param name='select_objs/orgid' iterateId='id1' property='orgid' indexed='true' />
                    </w:rowCheckbox>
                  </td>
                  <td>
                    <b:write iterateId="id1" property="orgid"/>
                  </td>
                  <td>
                    <b:write iterateId="id1" property="orgname"/>
                  </td>
                </tr>
              </l:iterate>
            </w:checkGroup>
            <tr>
              <td colspan="3" class="command_sort_area">
              <div class="h3">
                <input type="button" value="增加" onclick="addRecord();" class="button">
                <l:greaterThan property="page/size" targetValue="0" compareType="number">
                  <input type="button" value="修改" onclick="updateRecord();" class="button">
                </l:greaterThan>
                <l:greaterThan property="page/size" targetValue="0" compareType="number">
                  <input type="button" value="删除" onclick="deleteRecord();" class="button">
                </l:greaterThan>
              </div>
              <div class="h4">
                  <l:equal property="page/isCount" targetValue="true">
                    共
                    <b:write property="page/count"/>
                    条记录 第
                    <b:write property="page/currentPage"/>
                    页/
                    <b:write property="page/totalPage"/>
                    页
                  </l:equal>
                  <l:equal property="page/isCount" targetValue="false">
                    第
                    <b:write property="page/currentPage"/>
                    页
                  </l:equal>
                  <input type="button" onclick="firstPage('page', 'pageQuery', null, null, 'page_form');" value="首页"  <l:equal property="page/isFirst" targetValue="true">disabled</l:equal> >
                  <input type="button" onclick="prevPage('page', 'pageQuery', null, null, 'page_form');" value="上页" <l:equal property="page/isFirst" targetValue="true">disabled</l:equal> >
                  <input type="button" onclick="nextPage('page', 'pageQuery', null, null, 'page_form');" value="下页" <l:equal property="page/isLast" targetValue="true">disabled</l:equal> >
                  <l:equal property="page/isCount" targetValue="true">
                    <input type="button" onclick="lastPage('page', 'pageQuery', null, null, 'page_form');" value="尾页" <l:equal property="page/isLast" targetValue="true">disabled</l:equal> >
                  </l:equal>
                </div>
              </td>
            </tr>
          </table>
        </w:panel>
      </h:form>
    </viewlist>
    <script>
      function updateRecord()
      {
        var g = $id("group1");
        var frm = $name("page_form");
        if (g.getSelectLength() != 1) {
          alert("请选择一行记录！");
          return;
        }
        frm.elements["method"].value = "update";
		alert('案例开发中！');
//        frm.submit();
      }
      function addRecord()
      {
        var frm = $name("page_form");
        frm.elements["method"].value = "insert";
		alert('案例开发中！');
//        frm.submit();
      }
      function deleteRecord()
      {
        var g = $id("group1");
        var frm = $name("page_form");
        if (g.getSelectLength() < 1) {
          alert("请选择记录！");
          return;
        }
//        frm.elements["_eosFlowAction"].value = "delete";
        frm.elements["method"].value = "delete";
		alert('案例开发中！');
//        frm.submit();
      }
    </script>
  </body>
</html>
