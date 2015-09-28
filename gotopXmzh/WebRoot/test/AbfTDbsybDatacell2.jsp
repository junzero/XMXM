<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>
<%--
- Author(s): lz
- Date: 2011-10-05 16:16:53
- Description:
--%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      EOS datacell 整合
    </title>
  </head>
  <h:script src="/common/gotop/datacellHtml.js"></h:script><!-- 引用导出函数 -->
  <body>
    <queryform id="a10bfe7d-2f04-4fa8-b77d-dc5726c4c30c">
  <h:form  id="queryFormDatacell"  name="queryFormDatacell" action="" checkType="blur" target="_self" method="post" onsubmit="return submitQuery(this);">
    <w:panel id="panel1" width="100%" title="AbfTDbsyb查询">
      <table align="center" border="0" width="100%" class="form_table">
        <tr>
          <td class="form_label">
            系统名称
          </td>
          <td colspan="1">
			<r:comboSelect id="countryList" 
				queryAction="/com.gotop.struts.action.tDbsybAction.do?method=comboSelect" 
				xpath="BagendaImpl" 
				textField="title" 
				valueField="id"
				width="160"
				filterField="id"
				nullText="------请选择-------"
				initParamFunc="iparamfunc"
			/>
            <h:hidden property="criteria/_expr[1]/_op" value="="/>
          </td>
          <td class="form_label">
            待办事宜名称
          </td>
          <td colspan="1">
            <h:text property="criteria/_expr[2]/vDbsymc"/>
            <h:hidden property="criteria/_expr[2]/_op" value="="/>
          </td>
        </tr>
        <tr>
          <td class="form_label">
            待办日期
          </td>
          <td colspan="1">
            <w:date property="criteria/_expr[3]/dDbrq" format="yyyy-MM-dd HH:mm:ss"/>
            <h:hidden property="criteria/_expr[3]/_op" value="="/>
            <h:hidden property="criteria/_expr[3]/_pattern" value="yyyy-MM-dd"/>
          </td>
          <td class="form_label">
            来源人部门
          </td>
          <td colspan="1">
            <w:lookup property="criteria/_expr[4]/vLyrbm"/>
            <h:hidden property="criteria/_expr[4]/_op" value="="/>
          </td>
        </tr>
        <tr class="form_bottom">
          <td colspan="4" class="form_bottom">
            <input type="hidden" name="criteria/_entity" value="com.primeton.purview.tools.AbfTDbsyb">
            每页显示
            <h:text size="2" property="page/length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
            <input type="hidden" name="page/isCount" value="true">
            <input type="submit" value="查询" class="button">
          </td>
        </tr>
      </table>
    </w:panel>
  </h:form>
</queryform>
    <script>
      function submitQuery(frm) {
        if (checkForm(frm)) {
          var a = $id('cell1');
          a.reload();
        }
        return false;
      }
    </script>
    <br/>
    <r:datacell id="cell1" initParamFunc="paramFunc" paramFormId="queryFormDatacell" queryAction="/tDbsybAction.do?method=query" submitAction="/tDbsybAction.do?method=updateRows" width="100%" xpath="com.primeton.utils.abftdbsybs">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>
      <r:field fieldName="vXtmc" label="系统名称">
        <h:text validateAttr="type=chinaMobile;message=1;allowNull=false"/>
      </r:field>
      <r:field fieldName="vDbsymc" label="待办事宜名称">
        <h:text/>
      </r:field>
      <r:field fieldName="dDbrq" label="待办日期">
        <w:date format="yyyy-MM-dd HH:mm:ss"/>
      </r:field>
      <r:field fieldName="vDbrbh" label="待办人编号">
        <h:text/>
      </r:field>
      <r:field fieldName="vDbzy" label="待办摘要">
        <h:text/>
      </r:field>
      <r:field fieldName="vDbsywebljggxx" label="待办事宜WEB链接">
        <h:text/>
      </r:field>
      <r:field fieldName="vLyrbm" label="来源人部门" onRefreshFunc="freshCell">
      </r:field>
    </r:datacell>
    
<script type="text/javascript">
	function freshCell(value,entity,rowNo,cellNo){
        return "<input type='button' onclick='testFunc("+rowNo+")' value='查看'/>";
    }
	function testFunc(rowNo){
		var cell1 = $id("cell1");
		var row = cell1.getRow(rowNo);
		var entity = cell1.getEntity(row);
		alert(entity);
	}
	function testAjaxFun(){
		alert($id("cell1").getActiveEntity());
		return;
		var myAjax = new Ajax("${pageContext.request.contextPath}/test/datacellf.jsp");
		myAjax.isCommonUrl=true;
		myAjax.addParam("param", "abc123");
//		myAjax.method = "get";
	    myAjax.submit();
		var returnNode =myAjax.getValues("/root/data/abftdbsybs");
		alert(returnNode);
		alert('---'+myAjax.responseText);
	}
	function iparamfunc(){
		return "<remid>1</remid>";
	}
</script>
<input type="button" value="获取选中行实体" onclick="testAjaxFun()"/>
<input type="button" value="导出" onclick="XlsFun()"/>
<script type="text/javascript">
	function XlsFun(){
		var argument=new Array(3);
		argument[0] = "cell1";//datacell的ID
		argument[1] = "pageBack";//回调函数 一般用于处理分页信息
		argument[2] = window;//这个为必须，table所在窗口
		argument.beanid = "tDbsybDaoImpl";
		argument.funid = "findByPage";
		argument.action = "/common/commonAction_exportExcel.action";
		showModalCenter("/common/jsp/exportExcel.jsp",argument,null,600,360,"导出字段选择");
	}
</script>
  </body>
</html>
