<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      新增或修改岗位信息
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  	<e:datasource name="omPosition" type="bean" path="com.gotop.vo.tyjg.OmPosition"/>
  	 <h:form method="post" id="form1" name="form1" target="tempFrame" checkType="blur" onsubmit="return checkForm(this);"> 
    	<h:hidden property="omPosition.positionId"/>
    	<h:hidden property="omPosition.updator" value="${sessionScope.login_user.empid}"/>
        <table align="center" border="0" width="100%" class="form_table">
           <tr>
             <td class="form_label">   <!--  岗位名称 -->
              <b:message key="positionManager_OmPosition.posiname"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td colspan="1">
              <h:text property="omPosition.posiName" validateAttr="maxLength=128;allowNull=false;"/><font style="color:red">*</font>
            </td>
             <td class="form_label">  <!-- 岗位代码  -->
              <b:message key="positionManager_OmPosition.posicode"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td colspan="1">
              <h:text property="omPosition.posiCode" validateAttr="maxLength=20;allowNull=false;"/><font style="color:red">*</font>
            </td>
          </tr>
            <tr>
          	<td class="form_label">岗位说明：</td>
          	<td colspan="3">
          		<h:text style="width:92%;" property="omPosition.posiDesc"/>
          	</td>
          </tr>
          <tr>
            <td class="form_label">   <!--  所属职务 -->
             岗位属性：
            </td>
            <td colspan="1">
            	<d:select style="width:148px;" dictTypeId="ABF_ROLETYPEFIELD" property="omPosition.posiSort" nullLabel="请选择"></d:select>
            </td>
            <td class="form_label">
            	机构级别：
            </td>
            <td>
            <d:select dictTypeId="ABF_ORGDEGREE" property="omPosition.posiLevel" style="width:133" nullLabel="请选择"/>
           </td>
          </tr>
          <tr>
          	  <td class="form_label">
            	排序级别：
             </td>
          	 <td>
            	<h:text id="levelsort" property="omPosition.sortLevel"/>
            </td>
          </tr>
        
          <tr class="form_bottom">
            <td colspan="6">
              <input type="button" class="button" value='<b:message key="l_save"/>' onclick="doSave();">
              <input type="button" class="button" value='<b:message key="l_close"/>' onclick="javascript:window.closeD();">              
            </td>
          </tr>
        </table> 
  </h:form>
  <iframe id="tempFrame" name="tempFrame" style="display: none"></iframe>
  <script type="text/javascript">
  		function cusInit(){
  			var _form = $id("form1");
  			if("<b:write property="omPosition.positionId"/>" == ""){
  				_form.action = "/positionmanagement/positionAction_insertPosition.action";//新增岗位
  			}else{
  				_form.action = "/positionmanagement/positionAction_updatePosition.action";//修改岗位
  			}
  		}
  		function doSave(){
  			var _form = $id("form1");
  			_form.submit();
  		}
  		eventManager.add(window, "load", cusInit);
  </script>
  </body>
</html>
