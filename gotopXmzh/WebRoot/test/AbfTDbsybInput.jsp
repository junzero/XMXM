<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<%--
- Author(s): Administrator
- Date: 2011-10-29 22:26:27
- Description:
--%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      AbfTDbsyb录入
    </title>
  </head>
  <body>
    <e:datasource name="abftdbsyb" type="entity" path="com.primeton.purview.tools.AbfTDbsyb" />
    <h:form name="data_form" action="com.primeton.purview.AbfTDbsybMaintain.flow" method="post" onsubmit="return checkForm(this);">
      <h:hidden property="abftdbsyb/vXtmc" />
      <h:hidden property="abftdbsyb/iDbsybh" />
      <h:hidden property="abftdbsyb/vDbsymc" />
      <h:hidden property="abftdbsyb/dDbrq" />
      <h:hidden property="abftdbsyb/vDbrbh" />
      <l:equal property="_eosLastAccessAction" targetValue="update">
        <input type="hidden" name="_eosFlowAction" value="updateSubmit" >
      </l:equal>
      <l:equal property="_eosLastAccessAction" targetValue="insert">
        <input type="hidden" name="_eosFlowAction" value="insertSubmit" >
      </l:equal>
      <h:hiddendata property="criteria" />
      <h:hidden property="page/begin"/>
      <h:hidden property="page/length"/>
      <h:hidden property="page/isCount"/>
      <dataform id="797331ec-3ef9-4c58-aa24-4c1697e67c87">
		  <w:panel id="panel1" width="100%" title="录入">
		    <table align="center" border="0" width="100%" class="form_table">
		      <tr>
		        <td class="form_label">
		          预留项
		        </td>
		        <td colspan="1">
		          <h:text property="abftdbsyb/filler"/>
		        </td>
		        <td class="form_label">
		          系统名称
		        </td>
		        <td colspan="1">
		          <h:text property="abftdbsyb/vXtmc"/>
		        </td>
		      </tr>
		      <tr>
		        <td class="form_label">
		          待办事宜编号
		        </td>
		        <td colspan="1">
		          <h:text property="abftdbsyb/iDbsybh"/>
		        </td>
		        <td class="form_label">
		          待办事宜名称
		        </td>
		        <td colspan="1">
		          <h:text property="abftdbsyb/vDbsymc"/>
		        </td>
		      </tr>
		      <tr class="form_bottom">
		        <td colspan="4">
		          <input type="submit" value="保存" class="button">
		          <input type="button" value="返回" onclick="javascript:history.go(-1);" class="button">
		        </td>
		      </tr>
		    </table>
		  </w:panel>
		</dataform>
    </h:form>
  </body>
</html>
