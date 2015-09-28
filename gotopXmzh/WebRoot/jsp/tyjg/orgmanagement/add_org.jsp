<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      新增机构
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body style="margin: 0px;">
  <e:datasource name="tomorganization" type="bean" path="com.gotop.tyjg.orgmanagement.model.Tomorganization"/>
  <e:datasource name="abftjgfjxx" type="bean" path="com.gotop.tyjg.orgmanagement.model.Abftjgfjxx"/>
  <h:form action="/orgmanagement/organizationAction_addOrgInfo.action" method="post" checkType="blur"
   id="dataform1" target="hidFrame" onsubmit="return checkForm(this);">
  <table align="center" border="0" width="100%" class="form_table">
  		<l:notEqual property="tomorganization.parentOrgId" targetValue="0">
  			 <h:hidden property="tomorganization.parentOrgId" value="${param.parentOrgId}"/>
  		</l:notEqual>
      <h:hidden property="tomorganization.orgId"/>
      <h:hidden property="tomorganization.subCount"/>
      <h:hidden property="tomorganization.orgLevel"/>
      <h:hidden property="tomorganization.orgSeq" value="${param.orgSeq}"/>
      <h:hidden property="tomorganization.orgManager"/>
      <h:hidden property="tomorganization.isLeaf"/>     
      <h:hidden property="abftjgfjxx.orgId"/> 
      <tr>
        <td class="form_label">
          单位代码：
        </td>
        <td colspan="1">
          <h:text property="tomorganization.orgCode" validateAttr="maxLength=9;allowNull=false" value="${param.orgCode}"/>
          <font style="color:red">*</font>
        </td>
        <td class="form_label">
        	单位简称：
        </td>
        <td>
        	<h:text property="tomorganization.remark"/>
        </td>
      </tr>
      <tr>
      	<td class="form_label">
          机构名称：       
        </td>
        <td colspan="3">
          <h:text property="tomorganization.orgName"  
         	 validateAttr="allowNull=false;maxLength=64" onchange="realnameFun(this)" 
   			/>
          <font style="color:red">*</font>
        </td>
      </tr>
      <tr>
        <td class="form_label">
         上级单位：     
        </td>
        <td colspan="1">
          <h:text property="tomorganization.parentName" disabled="true" value="${param.parentName}"/>
        </td>
        <td class="form_label">
          单位简码：
        </td>
        <td colspan="1">
          <h:text property="tomorganization.realName"/>
        </td>
      </tr>
      <tr>  
        <td class="form_label">
          单位类型：
        </td>
        <td colspan="1">
         <d:select dictTypeId="ABF_ORGTYPE" property="tomorganization.orgType" 
         	style="width:133" nullLabel="请选择"/>
        </td>
        <td class="form_label">
          邮政编码：
        </td>
        <td colspan="1">
          <h:text property="tomorganization.zipCode" validateAttr="type=chinaZipcode;"/>
        </td>
      </tr>
      <tr> 
        <td class="form_label">
          单位等级：
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_ORGDEGREE" property="tomorganization.orgDegree" 
          	style="width:133"/>
          <l:empty property="tomorganization.orgDegree">
          	<font style="color:red">*</font>
          </l:empty>
        </td>             
        <td class="form_label">
          机构状态：
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_ORGSTATUS" property="tomorganization.status" id="tomorganization.status" style="width:133" value="${param.status}"/>
        </td> 
      </tr>
    <%--   <tr> 
        <td class="form_label">
          单位分类：
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_ORG_TYPEFIELD" property="tomorganization.orgTypeField" style="width:133" nullLabel="请选择"/>
        </td>             
        <td class="form_label">
          所属地域：
        </td>
        <td colspan="1">
          	<h:text property="tomorganization.area" id="oOrg/area" validateAttr="maxLength=40" value="${param.area}"/>
        </td> 
      </tr>
      <tr>
        <td class="form_label">
          三农标志：
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_SNBZ" nullLabel="请选择" property="tomorganization.snbz" />
        </td>
        <td class="form_label">
          支行级别：
        </td>
        <td colspan="3">
        	<d:select dictTypeId="ABF_ZHJB" nullLabel="请选择" property="tomorganization.zhjb" />
        </td>
      </tr> --%>
      <tr>
        <td class="form_label">
          单位属性：
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_JGSX" property="tomorganization.jgsx"></d:select>
          <l:empty property="tomorganization.jgsx">
	          <font style="color:red">*</font>
          </l:empty>
        </td>
        <td class="form_label">
          映射机构编号：
        </td>
        <td colspan="3">
	        <h:text property="tomorganization.ysjgbh" maxlength="9"/>
        </td>
      </tr>
      <tr>
        <td class="form_label">
          显示顺序：
        </td>
        <td colspan="1">
          <h:text property="tomorganization.displayOrder" validateAttr="maxLength=100" size="10"/>
        </td>
        <td class="form_label">
          区域属性：
        </td>
        <td colspan="1">
          <d:select property="tomorganization.areaType" nullLabel="请选择" dictTypeId="ABF_AREA"></d:select>
        </td>
      </tr>
      <tr>
        <td class="form_label">
          创建人员：
        </td>
        <td colspan="3">
	      <h:text property="tomorganization.whry" value="${sessionScope.login_user.empcode}"/>
        </td>
      </tr>
    </table>
    <table align="center" border="0" width="100%" class="form_table">
		<tr><td class="form_label" style="text-align: left;">
			<input type="checkbox" id="checkoEmp" checked="checked" onclick="isDisplay('oEmpDiv',this);"/>
			机构详细信息
		</td></tr>
	</table>
<DIV id="oEmpDiv" style="WIDTH: 100%; HEIGHT: 150px; OVERFLOW: auto">
  <table align="center" border="0" width="100%" class="form_table">
      <tr>
        <td class="form_label">
          网站地址
        </td>
        <td colspan="3">
          <h:text property="abftjgfjxx.webUrl" validateAttr="maxLength=200" size="60"/>
        </td>        
      </tr>
      <tr>
        <td class="form_label">
          生效日期
        </td>
        <td>
          <w:date property="abftjgfjxx.startDate" submitFormat="yyyy-MM-dd"/>
        </td>        
        <td class="form_label">
          失效日期 
        </td>
        <td>
          <w:date property="abftjgfjxx.endDate" submitFormat="yyyy-MM-dd"/>
        </td>
      </tr>
      <tr>
        <td class="form_label">
          联系人
        </td>
        <td colspan="1">
          <h:text property="abftjgfjxx.linkMan"/>
        </td>
        <td class="form_label">
          联系电话
        </td>
        <td colspan="1">
          <h:text property="abftjgfjxx.linkTel" maxlength="14" validateAttr="allowNull=true"/>
        </td>        
      </tr>
      <tr>
        <td class="form_label">
          电子邮件
        </td>
        <td colspan="4">
          <h:text property="abftjgfjxx.email" validateAttr="maxLength=128;type=email;allowNull=true"/>
        </td>  
      </tr>
      <tr>        
        <td class="form_label">
          地    址
        </td>
        <td colspan="3">  
          <h:textarea property="abftjgfjxx.orgAddr" cols="50" rows="2"  validateAttr="maxLength=512"/>
        </td>      
      </tr>
      </table>
</div>
        <table align="center" border="0" width="100%" class="form_table">  
      <tr class="form_bottom">
        <td colspan="4">
          <input type="submit" class="button" value="<b:message key="l_save" />"> 
          <input type="button" class="button" value="关闭" onclick="javascript:window.closeD();"> 
        </td>
      </tr>
  </table>
  </h:form>
  <iframe id="hidFrame" name="hidFrame" style="display: none;"></iframe>
  <h:script src="/js/tyjg/orgmanagement/orgmanager.js"/>
  </body>
</html>
