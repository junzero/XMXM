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
  <script type="text/javascript">
  	function comboSelectFun(){
  		alert('1111');
  	}
  </script>
  <body>
         	<r:comboSelect id="countryList" 
				queryAction="${pageContext.request.contextPath}/test/comboselectf.jsp" 
				textField="title" 
				valueField="id"
				xpath="BagendaImpl" 
				width="160"
				filterField="id"
				onChangeFunc="comboSelectFun"
				nullText="------请选择-------"
				property="sfwdTDsfysjgb.vYsjgmc"
				validateAttr="allowNull=false"
			/>
  </body>
</html>
