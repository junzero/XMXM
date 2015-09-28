<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<%--
- Author(s): laiwz
- Date: 2012-04-11 15:54:59
- Description:
--%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      员工附加信息表
    </title>
  </head>
  <body>
    <queryform id= "bdb37bce-d73d-489e-ab58-8ed1f6043736">
      <h:form  id="queryFormDatacell"  name="query_form" action="" checkType="blur" target="_self" method="post" onsubmit="return submitQuery(this);">
        <w:panel id="panel1" width="100%" title="员工附加信息表查询">
          <table align="center" border="0" width="100%" class="form_table">
            <tr>
              <td class="form_label">
                员工ID
              </td>
              <td colspan="1">
                <h:text property="empid"/>
              </td>
              <td class="form_label">
                出生日期
              </td>
              <td colspan="1">
                <w:date property="birthdate"/>
              </td>
            </tr>
            <tr>
              <td class="form_label">
                办公电话
              </td>
              <td colspan="1">
                <h:text property="otel"/>
              </td>
              <td class="form_label">
                办公地址
              </td>
              <td colspan="1">
                <h:text property="oaddress"/>
              </td>
            </tr>
            <tr>
              <td class="form_label">
                办公邮编
              </td>
              <td colspan="1">
                <h:text property="ozipcode"/>
              </td>
              <td class="form_label">
                办公邮件
              </td>
              <td colspan="1">
                <h:text property="oemail"/>
              </td>
            </tr>
            <tr>
              <td class="form_label">
                传真号码
              </td>
              <td colspan="1">
                <h:text property="faxno"/>
              </td>
              <td class="form_label">
                手机号码
              </td>
              <td colspan="1">
                <h:text property="mobileno"/>
              </td>
            </tr>
            <tr>
              <td class="form_label">
                MSN号码
              </td>
              <td colspan="1">
                <h:text property="msn"/>
              </td>
              <td class="form_label">
                家庭电话
              </td>
              <td colspan="1">
                <h:text property="htel"/>
              </td>
            </tr>
            <tr>
              <td class="form_label">
                家庭地址
              </td>
              <td colspan="3">
                <h:text property="haddress"/>
              </td>
            </tr>
            <tr class="form_bottom">
              <td colspan="4" class="form_bottom">
                <input type="submit" value="查询" class="button">
              </td>
            </tr>
          </table>
        </w:panel>
      </h:form>
    </queryform>
    <script>
      function submitQuery(frm) {
          var a = $id('cell1');
          a.reload();
        return false;
      }
    </script>
    <br/>
    <r:datacell 
        entityType="com.gotop.vo.tyjg.Abftygfjxxb"
        id="ygfjxxbCell1"
        paramFormId="queryFormDatacell"
        queryAction="/ygfjxxChaXun/queryYgfjxxByCondit_ygfjxxChaXun.action"
        submitAction="/ygfjxxChaXun/dataCellSubmit_ygfjxxChaXun.action"
        width="100%" pageSize="10"
        xpath="Abftygfjxxb">

      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>
      <r:field fieldName="empid" label="员工ID">
        <h:text/>
      </r:field>
      <r:field fieldName="birthDate" label="出生日期">
        <w:date/>
      </r:field>
      <r:field fieldName="otel" label="办公电话">
        <h:text/>
      </r:field>
      <r:field fieldName="oaddress" label="办公地址">
        <h:text/>
      </r:field>
      <r:field fieldName="ozipCode" label="办公邮编">
        <h:text/>
      </r:field>
      <r:field fieldName="oemail" label="办公邮件">
        <h:text/>
      </r:field>
      <r:field fieldName="faxNo" label="传真号码">
        <h:text/>
      </r:field>
      <r:field fieldName="mobileNo" label="手机号码">
        <h:text/>
      </r:field>
      <r:field fieldName="msn" label="MSN号码">
        <h:text/>
      </r:field>
      <r:field fieldName="htel" label="家庭电话">
        <h:text/>
      </r:field>
      <r:field fieldName="haddress" label="家庭地址">
        <h:text/>
      </r:field>
      <r:field fieldName="hzipCode" label="家庭邮编">
        <h:text/>
      </r:field>
      <r:field fieldName="pemail" label="私人电子邮箱">
        <h:text/>
      </r:field>
      <r:field fieldName="party" label="政治面貌">
        <h:text/>
      </r:field>
      <r:field fieldName="major" label="直接主管">
        <h:text/>
      </r:field>
      <r:field fieldName="workExp" label="工作描述">
        <w:date/>
      </r:field>
      <r:field fieldName="regDate" label="注册日期">
        <w:date/>
      </r:field>
      <r:field fieldName="createTime" label="创建时间">
        <w:date/>
      </r:field>
      <r:field fieldName="lastModyTime" label="最新更新时间">
        <w:date/>
      </r:field>
      <r:field fieldName="remark" label="备注">
        <h:text/>
      </r:field>
    </r:datacell>
  </body>
</html>
