<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.eos.web.taglib.util.XpathUtil"%>
<%@page import="com.gotop.tyjg.empMngr.model.validate"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%
	//获取标签中使用的国际化资源信息
	String empInfo=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("empOrgMaintain_l_title_empInfo");
	String oprInfo=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("empOrgMaintain_l_title_oprInfo");

	String pleaseSelect=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_pleaseSelect");
	String selectMngOrg=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("empOrgMaintain_l_title_selectMngOrg");
	String selectReleaseRole=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("empOrgMaintain_l_title_selectReleaseRole");
	
	
	String scope = "request";
	Object rootObj = XpathUtil.getDataContextRoot(scope, pageContext);
	Object propertyValue = XpathUtil.getObjectByXpath(rootObj, "omEmpTm.empCode");
	boolean disabled = false;
	if(propertyValue!=null && StringUtils.isNotBlank(propertyValue.toString())){
		disabled = true;
	}
	
	request.setAttribute("disabled",disabled);
	String execType =request.getParameter("execType");
	String orgid =request.getParameter("orgid");
	String orgDegree =request.getParameter("orgDegree");
	String jgsx =request.getParameter("jgsx");
	//execType ="insert";
%>
  <body style="margin: 0px;">
  	<h:form name="dataform1"
	action="/empMngr/queryEmpBaseAndFjxx_empMngr.action"
	checkType="blur" target="_self" method="post"
	onsubmit="return checkForm(this);">
	<h:hidden property="execType" value="<%=execType%>" />
	
	<h:hidden property="omEmpTm.empid" name="empid"/>
	<h:hidden property="tomorganiz.orgid" />
	<h:hidden property="tomorganiz.jgsx" value="${param.jgsx}"/>
	<h:hidden property="tomorganiz.orgDegree" name="orgDegree" value="${param.orgDegree}"/>
	<h:hidden property="omEmpTm.orgid" name="orgid" value="${param.orgid}"/>
	<h:hidden property="opType" />
	<h:hidden property="orgidlist" name="listorg" />
	<h:text property="omEmpTm.displayOrder" name="displayOrder" style="display: none;"/>
	<e:datasource name="changeTree" type="bean" path="com.gotop.tyjg.common.model.ChangeTree"/>
	<table align="center" border="0" width="100%" class="form_table">
		<b:write property="infoFlag" />
		<l:notEmpty property="omEmpTm.empid">
			<tr>
				<td colspan="4" class="eos-panel-title">&nbsp;<%=empInfo %></td>
			</tr>
		</l:notEmpty>
		<tr>
			<td class="form_label" width="20%"><!--人员代码--> 人员工号：</td>
			<td colspan="1" width="30%">
			<l:equal value="<%=execType%>" targetValue="insert">
				<h:text
				property="omEmpTm.empCode" id="empCode" name="empCode" validateAttr="type=formatStr;regExpr=^[a-zA-Z0-9_]{1,30}$;message=只能字母数字下划线组成;allowNull=false;" onblur="checkEmpcode(this);"/>
			</l:equal>
			<l:notEqual value="<%=execType%>" targetValue="insert">
				<h:text
				property="omEmpTm.empCode" id="empCode" name="empCode" validateAttr="allowNull=false;maxLength=30" onchange="replaceFun(this)" disabled="true"/>
			</l:notEqual>
			<font style="color:red">*</font> 
				<h:hidden property="acOper.userid" validateAttr="maxLength=64" />
				<h:hidden property="omEmpTm.userid" name="userid" validateAttr="maxLength=64" />
				<h:hidden property="acOper.operatorid" name="operatorid" value="omEmpTm.empid"/>
			</td>
			<td class="form_label" width="20%"><!--人员姓名--> 人员姓名：</td>
			<td colspan="1" width="30%"><h:text property="omEmpTm.empName" name="empName"
				validateAttr="allowNull=false;maxLength=50"
				onblur="realnameFun(this)" /><font style="color:red">*</font></td>
		</tr>
		<tr>
			<td class="form_label">姓名简拼：</td>
			<td colspan="1"><h:text property="omEmpTm.realName" name="realName" style="width:133" /></td>
			<td class="form_label">
			<!--人员状态--> 
			<b:message key="empOrgMaintain_oEmp.empstatus" />：</td>
			<td colspan="1"><d:select dictTypeId="ABF_EMPSTATUS"
				property="omEmpTm.empStatus" name="empStatus" style="width:133" value="on" /><font
				style="color:red">*</font>
			 <h:hidden property="acOper.status" name="status" /></td>
		</tr>
		<tr>
			<td class="form_label"><!--性别--> <b:message
				key="empOrgMaintain_oEmp.gender" />：</td>
			<td colspan="1"><d:select dictTypeId="ABF_GENDER"
				property="omEmpTm.gender" name="gender" style="width:133"
				nullLabel="<%=pleaseSelect%>" /></td>
			<td class="form_label"><!--出生日期--> <b:message
				key="empOrgMaintain_oEmp.birthdate" />：</td>
			<td colspan="1"><w:date property="omEmpTm.birthDate" name="birthDate"/></td>
		</tr>
		<tr>
			<td class="form_label"><!--证件类型--> <b:message
				key="empOrgMaintain_oEmp.cardtype" />：</td>
			<td colspan="1">
			<d:select id="cardType" dictTypeId="ABF_CARDTYPE" value="01" filterOp="=" filterStr="01" property="omEmpTm.cardType" name="cardType" style="width:133"
				/>
			<l:equal property="tomorganiz.jgsx" targetValue="1">
				<font style="color:red;">*</font>
			</l:equal>	
			
			</td>
			<td class="form_label"><!--证件号码--> 证件号码：</td>
			<td colspan="1">
				<%
					 Object obj = request.getAttribute("omEmpTm.cardNo");
				if(obj != null){
					String cardNo = String.valueOf(obj);
				 
				  boolean falg =new validate().vertifyId(cardNo);
				  if(falg){
					  StringBuffer showCardNo = new StringBuffer();
						if(cardNo != null && !"".equals(cardNo)){
							String beforeCardNo = cardNo.substring(0,2);
							String afterCardNo = cardNo.substring(cardNo.length()-2,cardNo.length());
							showCardNo.append(beforeCardNo).append("**************").append(afterCardNo);
						}
				%>
				<%=showCardNo.toString() %>
				
				<%}%>
				<%}else{ %>
					<h:text id="cardNo" property="omEmpTm.cardNo" name="cardNo" validateAttr="maxLength=20" />
				<%} %>
			<l:equal property="tomorganiz.jgsx" targetValue="1">
				<font style="color:red;">*</font>
			</l:equal>	
			</td>
		</tr>
		<tr>
			<td class="form_label"><!--入职日期--> <b:message
				key="empOrgMaintain_oEmp.indate" />：</td>
			<td colspan="1"><w:date property="omEmpTm.indate" name="indate" /></td>
			<td class="form_label"><!--离职日期--> <b:message
				key="empOrgMaintain_oEmp.outdate" />：</td>
			<td colspan="1"><w:date property="omEmpTm.outdate" name="outdate" /></td>
		</tr>
		<tr>
			<td class="form_label"><!--职级-->职务：</td>
			<td ><d:select dictTypeId="ABF_EMPLEVEL"
				property="omEmpTm.degree" name="degree" style="width:133"
				nullLabel="<%=pleaseSelect%>" /></td>
			<td class="form_label"><!--接收短信待级-->接收信息等级：</td>
			<td>
			<%if("insert".equals(execType)){%>
				特急:<h:checkbox name="expNote" value="1" checked="true"/>
				紧急:<h:checkbox name="emergNote" value="1" checked="true"/>
				一般:<h:checkbox name="genNote" value="1" checked="true"/>
			<%}else{%>
				特急:<h:checkbox name="expNote" property="omEmpTm.expNote" value="1"/>
				紧急:<h:checkbox name="emergNote" property="omEmpTm.emergNote" value="1"/>
				一般:<h:checkbox name="genNote" property="omEmpTm.genNote" value="1"/>
			<%}%>
			</td>
		</tr>
		<tr>
			<td class="form_label"><!--可管理机构--> 
				<b:message key="empOrgMaintain_oEmp.orgidlist" />：
			</td>
			<td colspan="3">
				<b:write property="omEmpTm.orgnamelist"/>
			</td>
		</tr>
		<%-- /l:equal --%>
		<%-- l:equal property="isyy" targetValue="1" --%>
		<tr>
			<td class="form_label">拥有系统角色<b:message
				key="l_colon" /></td>
			<td colspan="3">
				<b:write property="xtAcRole.roleName"/>
			</td>
		</tr>
		<tr>
			<td class="form_label">拥有业务角色<b:message
				key="l_colon" /></td>
			<td colspan="3">
				<b:write property="ywAcRole.roleName"/>
			</td>
		</tr>
		<tr>
			<td class="form_label"><!--可授权岗位--> 拥有的岗位<b:message
				key="l_colon" /></td>
			<td colspan="3">
				<b:write property="omPosition.posiName"/>
			</td>
		</tr>
	</table>
	<table align="center" border="0" width="100%" class="form_table">
		<tr>
			<td class="form_label" style="text-align: left;"><input
				type="checkbox" id="checkoEmp" checked="checked"
				onclick="isDisplay('oEmpDiv',this);" /> 个人详细信息</td>
		</tr>
	</table>
<%--<DIV id="oEmpDiv" style="WIDTH: 100%; HEIGHT: 150px; OVERFLOW: auto">--%>
<DIV id="oEmpDiv" style="WIDTH: 100%; ">
	<table align="center" border="0" width="100%"
		class="form_table">
		<tr>
			<td class="form_label" width="28%">是否显示手机：</td>
			<td colspan="1" width="22%"><d:radio dictTypeId="ABF_YESORNO" property="abfygfjxx.mobShow" name="mobShow" style="width:133" value="y"/></td>
			<td class="form_label" width="28%">是否显示家庭电话：</td>
			<td colspan="1" width="22%"><d:radio dictTypeId="ABF_YESORNO" property="abfygfjxx.htShow" name="htShow" style="width:133" value="y"/></td>
      	</tr>

	</table>
</div>



</h:form>
	<script type="text/javascript">
		function isDisplay(OPObj,checkOpr) {
		   var flag = checkOpr.checked;
		   $id(OPObj).style.display = flag?"":"none";	    	   
		}
		function modfilyPassword(){
			var strUrl = "/jsp/tyjg/empMngr/modfily_password.jsp?_ts="+ new Date();
			showModalCenter(strUrl,null,null,350,100,"密码修改") 
		}
		 function saveEmpOpr(){
    	var frm = $name("dataform1");
    	var cardType = $id("cardType");
    	var cardNo = $id("cardNo");
    	var jgsx = "<b:write property="tomorganiz.jgsx"/>";
    	
    	if(cardType && cardNo&& cardType.value == "" && cardNo.value!=""){
    		alert("请选择证件类型");
    		return;
    	}
    	if(cardNo){
    		if(jgsx==1 && cardNo.value==""){
    			alert("身份证号码不能为空");
    			cardNo.focus();
    			return;
    		}
    	}
    	if(cardType && cardNo&& cardNo.value != ""){
    		if(cardType.value !='01'){
    			alert("证件类型不是身份证");
    			return;
    		}else{
	    		if(cardNo.value==""){
	    			alert("身份证号码不能为空");
	    			cardNo.focus();
	    			return;
	    		}else{
	    			if(!isChinaIDNo(cardNo.value)){
	    				alert("身份证号码不合法");
	    				cardNo.focus();
	    				return;
	    			}else{
	    				var myAjax = new Ajax("/empMngr/cardNoIsExist_empMngr.action");
						myAjax.addParam("cardNo", cardNo.value);
						myAjax.addParam("empid", $name("empid").value);
						myAjax.submit();
						var empid_count =parseInt(myAjax.getValue("root/data/empid_count"));
						var promtMess = myAjax.getValue("root/data/promtMess")
						if(empid_count>0){
							alert(promtMess);
							return;
						}
	    			}
	    		}
    		}
    	}
    	if(checkForm(frm)){
    		var result = f_compare_date($name("indate").value,$name("outdate").value,'yyyy-MM-dd');
		    if(result==1){
		    	f_alert($name("omEmpTm.outdate"),'<b:message key="empOrgMaintain_oEmp.outdate" /><b:message key="l_date_check" /><b:message key="empOrgMaintain_oEmp.indate" />');
		    	return;
		    }
		    $name('userid').value = $id('empCode').value;
			var status = $name("status");
			var empStatus = $name("empStatus");
			if(empStatus.value=="on"){//在岗
				status.value = "running";//正常
			}else{
				status.value = "stop";//不正常
			}
			if($name("orgid").value){}else{
				if($name("tomorganiz.orgid").value){
					$name("orgid").value = $name("tomorganiz.orgid").value;
				}
				
			}
			var myAjax = new Ajax("/empMngr/updateSaveEmpInfo_empMngr.action");
			myAjax.submitForm(frm);        
			var returnNode = myAjax.getResponseXMLDom();
			if(returnNode && parseInt(myAjax.getValue("root/data/iRtn")) == 0 ) {
			        alert( '<b:message key="l_m_save_success" />' );  //保存成功             
			}else{
			      alert( '<b:message key="l_m_save_fail"/>' );         //保存失败 
			}
       }  
    }
    /**
   *  获取人员信息的首拼信息 
   */
    function realnameFun(obj){
   	 if(obj.value != ""){
	    	var myAjax = new Ajax("/empMngr/getShouSpell_empMngr.action");
	    	replaceFun(obj)
	    	myAjax.addParam("cnStr",obj.value);
			myAjax.submit();
		    var returnNode =myAjax.getResponseXMLDom();
		    if(myAjax.isSuccess()){
			    $name("realName").value=myAjax.getValue("root/data/spStr");
		    }else{
		    	alert('获取首拼失败！');
		    }
	    }
    }
     /**
    *	置空单元值
    **/
    function replaceFun(obj){
    	var objstr = obj.value;
    	if(objstr!=null){
	    	obj.value = objstr.replaceAll(" ", "");
    	}
    }
	</script>
  </body>
</html>
