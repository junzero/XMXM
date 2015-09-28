<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="com.gotop.vo.system.MUOUserSession"%>
<%@page import="com.gotop.util.Global"%>
<%@page import="java.util.*"%>
<%@page import="com.gotop.tyjg.orgmanagement.model.Abftjgfjxx"%>
<html>
<%
	//获取标签中使用的国际化资源信息
	String title=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_modifyOrg");
	String pleaseSelect=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_pleaseSelect");
	
	String opType = request.getParameter("opType");
	if(!"1".equals(opType)){
		opType = "0";
	}
	request.setAttribute("opType",opType);
	
	request.setAttribute("cDate",new java.util.Date());
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<h:css href="/css/style1/style-custom.css"/>
<title>本级单位</title>
<%
	MUOUserSession muous = (MUOUserSession)request.getSession().getAttribute(Global.LOGON_USER_KEY);
%>
<script>

	function mdfOrg(){
    	var frm = $name("dataform1");
		dateResult = 2;
      	if(dateResult==1){
      		f_alert( $name('tomorganiz.endDate'),"<b:message key='orgSubMaintain_OmOrganization.enddate' /><b:message key='l_date_check' /><b:message key='orgSubMaintain_OmOrganization.startdate' />" );
      		return false;
      	}
      	setOrgAreaValue();//设置所属地域值
    	frm.submit();
    }
    
        /**
    *	获取这两个 所属地域 联动的下拉
    **/
  	function setOrgAreaValue(){
  		var OrgArea = $id("tomorganiz.area");
      	OrgArea.value = $o('countryList').getValue() + ";" + $o('provinceList').getValue() + ";" + $o('countyList').getValue();
      	if(OrgArea.value==';'){
      		OrgArea.value = "";
      	}
  	}
    
    function initComboPosiParam(){
    	return "<oPosi><omOrganization><orgid>"+$name("tomorganiz.orgid").value+"</orgid></omOrganization></oPosi>";
    }
    
    function initComboEmpParam(){
    	var orgid = $name("tomorganiz.orgid").value;
    	if(orgid){}else{
    		orgId = '<%=muous.getEmpid()%>';
    	}
    	return "<oEmp><orgid>"+orgid+"</orgid></oEmp>" ;
    } 
    
   /**
    *	设置这两个 所属地域 联动的下拉
    **/
  	function getOrgAreaValue(){
  		var OrgArea = $id("tomorganiz.area").value;
  		if(OrgArea){
      		OrgArea = OrgArea.split(";");
      		$o('countryList').setValue(OrgArea[0],true);
      		$o('provinceList').setValue(OrgArea[1],true);
      		$o('countyList').setValue(OrgArea[2],false);
  		}
  	}
    function showModalCallBack(windowNow,windowObj,name){
    	var grantedSelect = windowNow.$id("grantedSelect");
    	var opt = grantedSelect.options;
    	var roleids=new Array();
		var rolenames=new Array();
    	for(var i=0;i<opt.length;i++){
    		var optObj = opt[i];
			roleids.push(optObj.value);
			rolenames.push(optObj.text);
    	}
    	$id('wl_mngrole').value=roleids.join(",");
    	$id("wl_mngrole").displayValue=rolenames.join(",");
    	$id("wl_mngrole").refreshInput();
    }
    
   /**
    * 隐藏域
    */
    function isDisplay(OPObj,checkOpr) {	
	   var flag = checkOpr.checked;
	   $id(OPObj).style.display = flag?"":"none";	    	   
	}
    
</script>
</head>
<body leftmargin="0" topmargin="0">
   <e:datasource name="tomorganization" type="bean" path="com.gotop.tyjg.orgmanagement.model.Tomorganization"/>
   <e:datasource name="abftjgfjxx" type="bean" path="com.gotop.tyjg.orgmanagement.model.Abftjgfjxx"/>
   <e:datasource name="partyRole" type="bean" path="com.gotop.tyjg.orgmanagement.model.PartyRole"/>
<h:form name="dataform1" action="${pageContext.request.contextPath }/tyjg/empMngr/queryOrgObjInfo_empMngr.action" checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
    <table align="center" border="0" width="100%" class="form_table">
      <h:hidden name="tomorganiz.orgSeq" property="tomorganiz.orgSeq"/>
      <h:hidden property="tomorganiz.orgid"/>
      <h:hidden property="tomorganiz.subCount"/>
      <h:hidden property="tomorganiz.orgLevel"/>
      <h:hidden property="tomorganiz.orgSeq"/>
      <h:hidden property="tomorganiz.orgManager"/>
      <h:hidden property="tomorganiz.isLeaf"/>
		
      <tr>
        <td class="form_label"  align="right" width="15%">
          <b:message key="orgSubMaintain_OmOrganization.orgcode"></b:message><b:message key="l_colon"></b:message>
          <!--机构代码-->
        </td>
        <td colspan="1">
          <h:text property="tomorganiz.orgCode" disabled="true"/><font style="color:red">*</font>
        </td>
        <td class="form_label"  align="right" width="15%">
          <b:message key="orgSubMaintain_OmOrganization.orgname"></b:message><b:message key="l_colon"></b:message>
          <!--机构名称-->
        </td>
        <td colspan="1">
          <h:text property="tomorganiz.orgName"/><font style="color:red">*</font>
        </td>
      </tr>
      <tr>
        <td class="form_label"  align="right" width="15%">
          <b:message key="orgSubMaintain_OmOrganization.omOrganization.orgid"></b:message><b:message key="l_colon"></b:message>        
          <!--上级机构-->
        </td>
        <td colspan="1">
          <h:text property="tomorganiz.parentName" disabled="true" />
        </td>
        <td class="form_label"  align="right" width="15%">
          单位简称<b:message key="l_colon"/>
        </td>
        <td colspan="1">
          <h:text property="tomorganiz.realName"/>
        </td>
      </tr>
      <tr>  
        <td class="form_label"  align="right" width="15%">
          <!--机构类型-->
          <b:message key="orgSubMaintain_OmOrganization.orgtype"></b:message><b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_ORGTYPE" property="tomorganiz.orgType" style="width:133" nullLabel='<%=pleaseSelect%>'/>
        </td>
        <td class="form_label"  align="right" width="15%">
          <!--邮政编码-->
          邮政编码<b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <h:text property="tomorganiz.zipCode"/><font style="color:red">*</font>
        </td>
      </tr>
      <tr> 
        <td class="form_label"  align="right" width="15%">
          <b:message key="orgSubMaintain_OmOrganization.orgdegree"></b:message><b:message key="l_colon"></b:message>
          <!--机构等级-->
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_ORGDEGREE" property="tomorganiz.orgDegree" style="width:133" nullLabel="<%=pleaseSelect%>"/>
        </td>             
        <td class="form_label"  align="right" width="15%">
          <!--机构状态-->
          <b:message key="orgSubMaintain_OmOrganization.status"></b:message><b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_ORGSTATUS" property="tomorganiz.status" style="width:133"/>
        </td> 
      </tr>
      <tr> 
        <td class="form_label"  align="right" width="15%">
          <b:message key="orgSubMaintain_OmOrganization.orgtypefield"></b:message><b:message key="l_colon"></b:message>
          <!--机构分类-->
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_ORG_TYPEFIELD" property="tomorganiz.orgTypeField" style="width:133" nullLabel="<%=pleaseSelect%>"/>
        </td>             
        <td class="form_label"  align="right" width="15%">
          <!--所属地域-->
          <b:message key="orgSubMaintain_OmOrganization.area"></b:message><b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
			<b:write property="tomorganiz.area" />
        </td> 
      </tr>
      <tr>
        <td class="form_label"  align="right" width="15%">
          <!--显示顺序-->
          显示顺序<b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <b:write property="tomorganiz.displayOrder"/>
        </td>
        <td class="form_label"  align="right" width="15%">
          <!--可授权角色-->
          拥有的角色<b:message key="l_colon"/>
        </td>
        <td colspan="1">
          <b:write property="partyRole.specialTyname"/>
        </td>
      </tr>
      <tr>
        <td class="form_label"  align="right" width="15%">
          三农标志<b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <d:write dictTypeId="ABF_SNBZ" property="tomorganiz.snbz"/>
        </td>
        <td class="form_label"  align="right" width="15%">
          <!--可授权角色-->
          支行级别<b:message key="l_colon"/>
        </td>
        <td colspan="3">
        	<d:write dictTypeId="ABF_ZHJB" property="tomorganiz.zhjb"/>
        </td>
      </tr>
      <tr>
        <td class="form_label"  align="right" width="15%">
          机构属性<b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <d:write dictTypeId="ABF_JGSX" property="tomorganiz.jgsx"/>
        </td>
        <td class="form_label"  align="right" width="15%">
          映射机构编号<b:message key="l_colon"/>
        </td>
        <td colspan="3">
	        <b:write property="tomorganiz.ysjgbh"/>
        </td>
      </tr>
      <tr>
        <td class="form_label"  align="right" width="15%">
          维护时间<b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <b:write property="tomorganiz.whsj" formatPattern="yyyy-MM-dd"/>
        </td>
        <td class="form_label"  align="right" width="15%">
          维护人员<b:message key="l_colon"/>
        </td>
        <td>
	      <b:write property="tomorganiz.whry" />
        </td>
      </tr>
    </table>
    <table align="center" border="0" width="100%" class="form_table">
		<tr><td class="form_label"  align="right" style="text-align: left;">
			<input type="checkbox" id="checkoEmp" checked="checked" onclick="isDisplay('oEmpDiv',this);"/>
			机构详细信息
		</td></tr>
	</table>
<DIV id="oEmpDiv" style="WIDTH: 100%; HEIGHT: 150px; OVERFLOW: auto">
  <table align="center" border="0" width="100%" class="form_table">
      <tr>
        <td class="form_label"  align="right" width="15%">
          网站地址<b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <h:text property="abftjgfjxx.webUrl" validateAttr="maxLength=200"/>
        </td>
        <td class="form_label"  align="right" width="15%">
          创建时间<b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <w:date property="abftjgfjxx.createTime" allowNull="false"/>
        </td>        
      </tr>
      <tr>
        <td class="form_label"  align="right" width="15%">
          生效日期<b:message key="l_colon"></b:message>
        </td>
        <td>
          <w:date property="abftjgfjxx.startDate" />
        </td>        
        <td class="form_label"  align="right" width="15%">
          失效日期<b:message key="l_colon"></b:message>
        </td>
        <td>
          <w:date property="abftjgfjxx.endDate" />
        </td>
      </tr>
      <tr>
        <td class="form_label"  align="right" width="15%">
          联系人<b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <h:text property="abftjgfjxx.linkMan"/>
        </td>
        <td class="form_label"  align="right" width="15%">
          联系电话<b:message key="l_colon"></b:message>
        </td>
        <td colspan="1">
          <h:text property="abftjgfjxx.linkTel" validateAttr="maxLength=128;type=email;allowNull=true"/>
        </td>        
      </tr>
      <tr>
        <td class="form_label"  align="right" width="15%">
          电子邮件<b:message key="l_colon"></b:message>
        </td>
        <td colspan="4">
          <h:text property="abftjgfjxx.email" validateAttr="maxLength=128;type=email;allowNull=true"/>
        </td>  
      </tr>
      <tr>        
        <td class="form_label"  align="right" width="15%">
          地    址<b:message key="l_colon"></b:message>
        </td>
        <td colspan="3">  
          <h:textarea property="abftjgfjxx.orgAddr" cols="50" rows="2"  validateAttr="maxLength=512"/>
        </td>      
      </tr>

    </table>
</DIV>
    
</h:form>
</body>
</html>
