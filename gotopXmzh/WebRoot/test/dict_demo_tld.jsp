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
      EOS dict 整合
    </title>
    <%
    	request.setAttribute("g",3);
    %>
  </head>
  <body>
  <body topmargin="0">
  	<w:panel id="panel1" width="100%" title="d:write">
			业务代码翻译标签，提供将业务代码翻译成代码名称的功能。<br/>
			<br/>
			<d:write dictTypeId="JHLY" value="2"/><br/>
			<d:write dictTypeId="JHLY" value="2,3" /> <!-- 输出 女,男 默认分隔符,--><br/>
			<d:write dictTypeId="JHLY" value="2|3" seperator="|"/> <!-- 输出 女,男 分隔符|--><br/>

			
  	</w:panel>
  	<w:panel id="panel2" width="100%" title="d:select">
			该标签用来生成一个业务字典类型的列表框（select对象），option的value为dictid，option的text为dictName。可支持用户多选，多选时可选择标准的form提交或者以分隔符把提交值拼成字符串（如name=value1,value2,value3）的方式提交。<br/>
			<d:select dictTypeId="JHLY"/> 
		        <!-- 生成性别的列表框 -->
			<d:select dictTypeId="JHLY" multiple="true" property="g" /> 
		        <!-- 生成性别的列表框，设置初始选中值 -->
		    <d:select dictTypeId="JHLY" multiple="true" value="3,4"/>
        		<!-- 生成性别的列表框，设置初始默认选中值，多选 -->
  	</w:panel>
  	<w:panel id="panel3" width="100%" title="d:checkbox">
			该标签用来根据业务字典类型生成多选checkbox标签，该标签生成一组checkbox，业务字典的dictid为checkbox的value值，业务字典的dictName为checkbox的label值。<br/>
			<d:checkbox dictTypeId="JHLY" />
		        <!-- 生成性别的checkboxGroup -->
			<d:checkbox dictTypeId="JHLY" property="g" />
		        <!-- 生成性别的checkboxGroup，设置初始选中值 -->
			<d:checkbox dictTypeId="JHLY" property="x" value="2" perrow="1"/>
		        <!-- 生成性别的checkboxGroup，设置初始默认选中值 perrow设置每行显示checkbox个数，缺省一行输出全部-->
  	</w:panel>
  	<w:panel id="panel4" width="100%" title="d:radio">
			该标签用来根据业务字典类型生成一组radio按钮。业务字典的dictid做为radio的value，业务字典的dictname做为radio的label。
			<d:radio dictTypeId="JHLY" />
		        <!-- 生成性别的radio -->
			<d:radio dictTypeId="JHLY" property="g" />
		        <!-- 生成性别的radio，设置初始选中值 -->
			<d:radio dictTypeId="JHLY" property="x" value="3" perrow="1"/>
		        <!-- 生成性别的radio，设置初始默认选中值 perrow设置每行显示radio个数，缺省一行输出全部-->
  	</w:panel>
  	<w:panel id="panel5" width="100%" title="d:json">
			以json对象方式输出
			<textarea rows="3" cols="88"><gotop:Dictionary fldm="YHZT" beanName="cyhzt" /></textarea>
  	</w:panel>
  </body>
</html>
