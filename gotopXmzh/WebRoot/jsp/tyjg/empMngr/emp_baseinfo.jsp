<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="com.gotop.vo.system.MUOUserSession"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.eos.web.taglib.util.XpathUtil"%>
<%@page import="com.gotop.util.Global"%>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>

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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Title</title>
<%
	MUOUserSession musous = (MUOUserSession)request.getSession().getAttribute(Global.LOGON_USER_KEY);
%>
</head>
<h:script src="/common/gotop/showModal_patch.js"/>
<body topmargin="0" leftmargin="0">
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
	<h:text property="omEmpTm.cardType" name="cardType" style="display: none;"/>
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
			<td class="form_label"  align="right" width="20%"><!--人员代码--> 人员工号：</td>
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
			<td class="form_label"  align="right" width="20%"><!--人员姓名--> 人员姓名：</td>
			<td colspan="1" width="30%"><h:text property="omEmpTm.empName" name="empName"
				validateAttr="allowNull=false;maxLength=50"
				onblur="realnameFun(this)" /><font style="color:red">*</font></td>
		</tr>
		<tr>
			<td class="form_label"  align="right">姓名简拼：</td>
			<td colspan="1"><h:text property="omEmpTm.realName" name="realName" style="width:133" /></td>
			<td class="form_label"  align="right">
			<!--人员状态--> 
			<b:message key="empOrgMaintain_oEmp.empstatus" />：</td>
			<td colspan="1"><d:select dictTypeId="ABF_EMPSTATUS"
				property="omEmpTm.empStatus" name="empStatus" style="width:133" value="on" /><font
				style="color:red">*</font>
			 <h:hidden property="acOper.status" name="status" /></td>
		</tr>
		<tr>
			<td class="form_label"  align="right"><!--性别--> <b:message
				key="empOrgMaintain_oEmp.gender" />：</td>
			<td colspan="1"><d:select dictTypeId="ABF_GENDER"
				property="omEmpTm.gender" name="gender" style="width:133"
				nullLabel="<%=pleaseSelect%>" /></td>
			<td class="form_label"  align="right"><!--出生日期--> <b:message
				key="empOrgMaintain_oEmp.birthdate" />：</td>
			<td colspan="1"><w:date property="omEmpTm.birthDate" name="birthDate"/></td>
		</tr>
		<%if("insert".equals(execType)){%>	
		<tr>
			<td class="form_label"  align="right"><!--证件类型--> <b:message
				key="empOrgMaintain_oEmp.cardtype" />：</td>
			<td colspan="1">
			<d:select id="cardType" dictTypeId="ABF_CARDTYPE" value="01" filterOp="=" filterStr="01" property="omEmpTm.cardType" name="cardType" style="width:133"
				/>
			<l:equal property="tomorganiz.jgsx" targetValue="1">
				<font style="color:red;">*</font>
			</l:equal>	
			
			</td>
			<td class="form_label"  align="right"><!--证件号码--> 证件号码：</td>
			<td colspan="1"><h:text id="cardNo" property="omEmpTm.cardNo" name="cardNo"
				validateAttr="maxLength=20" />
			<l:equal property="tomorganiz.jgsx" targetValue="1">
				<font style="color:red;">*</font>
			</l:equal>	
			<l:equal value="${param.jgsx }" targetValue="1">
				<font style="color:red;">*</font>
			</l:equal>	
			</td>
		</tr>
		<%} %>
		<tr>
			<td class="form_label"  align="right"><!--入职日期--> <b:message
				key="empOrgMaintain_oEmp.indate" />：</td>
			<td colspan="1"><w:date property="omEmpTm.indate" name="indate" /></td>
			<td class="form_label"  align="right"><!--离职日期--> <b:message
				key="empOrgMaintain_oEmp.outdate" />：</td>
			<td colspan="1"><w:date property="omEmpTm.outdate" name="outdate" /></td>
		</tr>
		<%--<tr>
			<td class="form_label"  align="right"><!--职级-->职务：</td>
			<td ><d:select dictTypeId="ABF_EMPLEVEL"
				property="omEmpTm.degree" name="degree" style="width:133"
				nullLabel="<%=pleaseSelect%>" /></td>
			<td class="form_label"  align="right"><!--接收短信待级-->接收信息等级：</td>
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
		</tr>--%>
		<%-- l:equal property="isyy" targetValue="1" --%>
		<tr>
			<td class="form_label"  align="right"><!--可管理机构--> 
				可管理机构：
			</td>
			<td colspan="3">
				<%--				
				<w:lookup id="wl_mngorg" name="orgidlist"
					property="omEmpTm.orgidlist" validateAttr="allowNull=true;message=可管理部门可以为空"
					readonly="true" displayProperty="omEmpTm.orgnamelist"
					lookupUrl="/empMngr/setMngOrgInit_empMngr.action"
					dialogTitle="" width="550" height="440"
					lookupWidth="380">
				</w:lookup>
				<br/>
				--%>
        			<w:lookup id="wl_mngorg" name="orgidlist" property="omEmpTm.orgidlist" readonly="true" 
        					displayProperty="omEmpTm.orgnamelist" style="hidden=ture;" width="500" height="430" lookupWidth="380"
				        	lookupUrl="/tree/initMainTree_mainTree.action"
				        	dialogTitle="<%=selectMngOrg %>" onReturnFunc="returnFuncOrgLuss">
					</w:lookup>
			</td>
		</tr>
		<%-- /l:equal --%>
		<%-- l:equal property="isyy" targetValue="1" --%>
<%-- 		<tr>
			<td class="form_label"  align="right">拥有系统角色<b:message
				key="l_colon" /></td>
			<td colspan="3">
			<w:lookup id="xt_mngrole"
				property="xtAcRole.roleId" readonly="true"
				displayProperty="xtAcRole.roleName" name="xt_mngrole"
				lookupUrl="/empMngr/setXtAcRoleInit_empMngr.action" 
				dialogTitle="<%=selectReleaseRole %>" width="720" height="450"
				lookupWidth="380">
				<h:param name="roleReadonly" value="1" />
				<h:param name="roleType" value="1" />
			</w:lookup> 
			<l:equal property="opType" targetValue="1" compareType="string">
			</l:equal></td>
		</tr> --%>
		<%--  /l:equal --%>
		<%-- l:equal property="isyy" targetValue="1" --%>
		<tr>
			<td class="form_label"  align="right">拥有业务角色<b:message
				key="l_colon" /></td>
			<td colspan="3">
			<w:lookup id="yw_mngrole"
				property="ywAcRole.roleId" readonly="true"
				displayProperty="ywAcRole.roleName" name="yw_mngrole"
				lookupUrl="/empMngr/setYwAcRoleInit_empMngr.action"
				dialogTitle="<%=selectReleaseRole %>" width="720" height="450"
				lookupWidth="380" onReturnFunc="returnFunc">
				<h:param name="mngroles" property="omEmpTm.specialtyYW" />
				<h:param name="roleReadonly" value="1" />
				<h:param name="roleType" value="0" />
			</w:lookup> 
			<l:equal property="opType" targetValue="1" compareType="string">
			</l:equal></td>
		</tr>
		<%-- /l:equal --%>
	<tr>
			<td class="form_label"  align="right"><!--可授权岗位--> 拥有的岗位<b:message
				key="l_colon" /></td>
			<td colspan="3">
			<w:lookup id="wl_mngposi"
				property="omPosition.positionId" readonly="true" name="wl_mngposi"
				displayProperty="omPosition.posiName" lookupUrl="/empMngr/positionSelectWin2_empMngr.action"
				dialogTitle="请选择岗位" width="680" height="430"
				lookupWidth="380" onReturnFunc="returnFunc">
			</w:lookup>
			</td>
		</tr> 
	</table>
	<table align="center" border="0" width="100%" class="form_table">
		<tr>
			<td class="form_label"  align="right" style="text-align: left;"><input
				type="checkbox" id="checkoEmp" checked="checked"
				onclick="isDisplay('oEmpDiv',this);" /> 个人详细信息1</td>
		</tr>
	</table>
<div style="display:none">
			<w:lookup id="xt_mngrole"
				property="xtAcRole.roleId" readonly="true"
				displayProperty="xtAcRole.roleName" name="xt_mngrole"
				lookupUrl="/empMngr/setXtAcRoleInit_empMngr.action" 
				dialogTitle="<%=selectReleaseRole %>" width="720" height="450"
				lookupWidth="380">
				<h:param name="roleReadonly" value="1" />
				<h:param name="roleType" value="1" />
			</w:lookup> 	
</div>
<%--<DIV id="oEmpDiv" style="WIDTH: 100%; HEIGHT: 150px; OVERFLOW: auto">--%>
<DIV id="oEmpDiv" style="WIDTH: 100%; ">
	<table align="center" border="0" width="100%"
		class="form_table">
		<tr>
			<td class="form_label"  align="right" width="20%">是否显示手机：</td>
			<td colspan="1" width="30%"><d:radio dictTypeId="ABF_YESORNO" property="abfygfjxx.mobShow" name="mobShow" style="width:133" value="y"/></td>
			<td class="form_label"  align="right" width="20%">是否显示家庭电话：</td>
			<td colspan="1" width="30%"><d:radio dictTypeId="ABF_YESORNO" property="abfygfjxx.htShow" name="htShow" style="width:133" value="y"/></td>
      	</tr>
		<tr>
			<td class="form_label"  align="right"><!--手机号码--> <b:message
				key="empOrgMaintain_oEmp.mobileno" />：</td>
			<td colspan="1"><h:text property="abfygfjxx.mobileNo" name="mobileNo" maxlength="20"
				validateAttr="allowNull=true" /></td>
			<td class="form_label"  align="right"><!--家庭电话--> <b:message
				key="empOrgMaintain_oEmp.htel" />：</td>
			<td colspan="1"><h:text property="abfygfjxx.htel" name="htel" 
				validateAttr="maxLength=13;allowNull=true" style="width:250px;"/></td>
		</tr>
		<tr>
			<td class="form_label"  align="right"><!--办公电话--> <b:message
				key="empOrgMaintain_oEmp.otel" />：</td>
			<td colspan="1"><h:text property="abfygfjxx.otel" name="otel"
				validateAttr="maxLength=13" /></td>
			<td class="form_label"  align="right"><!--办公邮件--> <b:message
				key="empOrgMaintain_oEmp.oemail" />：</td>
			<td colspan="1"><h:text property="abfygfjxx.oemail" name="oemail"
				validateAttr="maxLength=128;type=email" style="width:250px;"/></td>
		</tr>
		<tr>
			<td class="form_label"  align="right"><!--传真号码--> <b:message
				key="empOrgMaintain_oEmp.faxno" />：</td>
			<td colspan="1"><h:text property="abfygfjxx.faxNo" name="faxNo"
				validateAttr="maxLength=14" /></td>
			<td class="form_label"  align="right"><!--MSN号码--> <b:message
				key="empOrgMaintain_oEmp.msn" />：</td>
			<td colspan="1"><h:text property="abfygfjxx.msn" name="msn" 
				validateAttr="maxLength=14" style="width:250px;"/></td>
		</tr>
		<tr>
			<td class="form_label"  align="right"><!--家庭地址--> <b:message
				key="empOrgMaintain_oEmp.haddress" />：</td>
			<td colspan="3"><h:textarea property="abfygfjxx.haddress" name="haddress" 
				cols="50" rows="1" validateAttr="maxLength=128" /></td>
		</tr>
		<tr>
			<td class="form_label"  align="right"><!--办公地址--> <b:message
				key="empOrgMaintain_oEmp.oaddress" />：</td>
			<td colspan="3"><h:textarea property="abfygfjxx.oaddress" name="oaddress" 
				cols="50" rows="1" validateAttr="maxLength=128" /></td>
		</tr>
		<tr>
			<td class="form_label"  align="right"><!--家庭邮编--> <b:message
				key="empOrgMaintain_oEmp.hzipcode" />：</td>
			<td colspan="1"><h:text property="abfygfjxx.hzipCode" name="hzipCode" 
				validateAttr="maxLength=10;type=chinaZipcode" /></td>
			<td class="form_label"  align="right"><!--私人邮箱--> <b:message
				key="empOrgMaintain_oEmp.pemail" />：</td>
			<td colspan="1"><h:text property="abfygfjxx.pemail" name="pemail" 
				validateAttr="maxLength=128;type=email;allowNull=true" style="width:250px;"/></td>
		</tr>
		<tr>
			<td class="form_label"  align="right"><!--政治面貌--> <b:message
				key="empOrgMaintain_oEmp.party" />：</td>
			<td colspan="1"><d:select dictTypeId="ABF_PARTYVISAGE"
				property="abfygfjxx.party" name="party" style="width:133"
				nullLabel="<%=pleaseSelect%>" /></td>
			<td class="form_label"  align="right"><!--办公邮编--> <b:message
				key="empOrgMaintain_oEmp.ozipcode" />：</td>
			<td colspan="1"><h:text property="abfygfjxx.ozipCode" name="ozipCode" 
				validateAttr="maxLength=10;type=chinaZipcode" style="width:250px;"/></td>
		</tr>
		
		<tr>
			<td class="form_label"  align="right"><!--本地密码认证--> <b:message
				key="empOrgMaintain_oOpr.authmode" />：</td>
			<td colspan="1"><d:select dictTypeId="ABF_AUTHMODE"
				property="acOper.authmode" name="authmode" style="width:133"
				nullLabel="<%=pleaseSelect%>" /></td>
			<td class="form_label"  align="right"><!--当状态为锁定时，解锁的时间--> 解锁的时间：</td>
			<td colspan="1"><w:date property="acOper.unlocktime" name="unlocktime" style="width:120px;" format="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td class="form_label"  align="right"><!--有效开始日志--> <b:message
				key="empOrgMaintain_oOpr.startdate" />：</td>
			<td colspan="1"><w:date property="acOper.startdate" name="startdate" style="width:120px;"/></td>
			<td class="form_label"  align="right"><!--有效截止日期--> <b:message
				key="empOrgMaintain_oOpr.enddate" />：</td>
			<td colspan="1"><w:date property="acOper.enddate" name="enddate" style="width:200px;"/></td>
		</tr>

	</table>
</div>



	<%--是否拥有操作者 y/n --%>
	<table align="center" border="0" width="100%" class="form_table"
		style="display: none;">
		<tr>
			<td class="form_label"  align="right" style="text-align: left;"><input
				type="checkbox" id="checkOpr" checked="checked"
				onclick="isDisplay('tableDiv',this);" /> <%=oprInfo %></td>
		</tr>
	</table>
	<div style="display: none;">
	<table align="center" border="0" width="100%" class="form_table"
		id="tableDiv">
		<tr>
			<td class="form_label"  align="right"><!--用户密码--> <b:message
				key="empOrgMaintain_oOpr.passw" />：</td>
			<td>
			<%--<h:hidden name="acOper.password" value=""/>--%>
			<h:password
				name="password" property="acOper.password"
				onchange="$name('acOper.password').value=this.value;"
				validateAttr="maxLength=100" title="默认为密码为111111" id="password" /></td>
			<td class="form_label"  align="right" width="15%"><!--菜单风格--> <b:message
				key="empOrgMaintain_oOpr.menutype" />：</td>
			<td colspan="1"><d:select dictTypeId="ABF_LAYOUTSTYLE"
				property="acOper.menuType" style="width:133" name="menuType"
				nullLabel="<%=pleaseSelect%>" /></td>
		</tr>
		<tr>
			<td class="form_label"  align="right"><!--密码失效日--> <b:message
				key="empOrgMaintain_oOpr.invaldate" />：</td>
			<td colspan="1"><w:date property="acOper.invalDate" name="invalDate" /></td>
			<td class="form_label"  align="right"><!--认证模式--> <b:message
				key="empOrgMaintain_oOpr.authmode" />：</td>
			<td colspan="1"><d:select dictTypeId="ABF_AUTHMODE"
				property="acOper.authMode" style="width:133" name="authMode"
				nullLabel="<%=pleaseSelect%>" /><h:hidden property="omEmpTm.headPicicon" name="headPicicon" /></td>
		</tr>
	</table>
	</div>
	
	<%-- 教育信息 --%>
	
	<table align="center" border="0" width="100%" class="form_table">
		<tr class="form_bottom">
			<td colspan="6"><input type="button" class="button"
				value="<b:message key='l_save' />" name="saveBtn"
				onclick="saveEmpOpr();"> 
				<l:present property="_ts" propertyType="parameter">
					<input type="button" value='<b:message key="l_close"/>' 
						class="button" onclick="window.closeD();">
				</l:present> 
				<l:present property="omEmpTm.empCode">
					<input type="button" value="密码重置" name="resetBut" class="button"
						onclick="resetButFun()" />
				</l:present>
			</td>
		</tr>
	</table>
</h:form>
<h:script src="/common/skins/default/scripts/common.js"/>
<script>
	function returnFuncOrgLuss(arg){//如果点击右上角关闭则不进入该方法    
		var orgid = "";
		var orgname = "";
		if(arg.length<2 || arg[0].length<1){
			return;
		}else{
//			orgid = arg[2][0];
//			orgname = arg[1][0];
			for(var i=0;i<arg[0].length;i++){
				orgid += arg[0][i];
				orgname += arg[1][i];
				if(i<(arg[0].length-1)){
					orgid += ",";
					orgname += ",";
				}
			}
		}
		arg = [orgid,orgname];
		return arg;
	}


    function saveEmpOpr(){
    	var frm = $name("dataform1");
    	var cardType = $id("cardType");
    	var cardNo = $id("cardNo");
    	var jgsx = $name("tomorganiz.jgsx").value;
    	
    	if(cardType && cardType.value == "" && cardNo.value!=""){
    		alert("请选择证件类型");
    		return;
    	}
    	if(jgsx==1){
    		if(cardNo && cardNo.value ==""){
    			alert("身份证号码不能为空");
    			return;
    		}
    	}
    	if(cardType && cardNo && cardNo.value != ""){
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
			var password = $name('password');
			var newpassword = $id("password").value;
			if(newpassword){}else{
				password.value = "111111";
			}
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
			var myAjax;
			var orgidlist;
			var execType =$name("execType").value;
			if(execType=="insert"){
				myAjax = new Ajax("/empMngr/insertSaveEmpInfo_empMngr.action");
			}else{
				myAjax = new Ajax("/empMngr/updateSaveEmpInfo_empMngr.action");
			}
			if($id("wl_mngorg")){
				orgidlist= $name("orgidlist").value;
			}
			var rs="";
			if(orgidlist)
			{
				if(orgidlist.substr(0,2)=='0,'){
					rs = orgidlist.substr(2);
				}else{
					rs = orgidlist;		
				}
			}
			setexpNote(myAjax);
//			myAjax.addParam("orgidlist",rs);
//			myAjax.addParam("yw_mngrole",$id("yw_mngrole").value);
//			myAjax.addParam("xt_mngrole",$id("xt_mngrole").value);
			myAjax.addParam("position",$name("wl_mngposi").value);
			
			myAjax.submitForm(frm);        
			var returnNode = myAjax.getResponseXMLDom();
			if(returnNode && parseInt(myAjax.getValue("root/data/iRtn")) == 0 ) {
			        alert( '<b:message key="l_m_save_success" />' );  //保存成功 
			        try{
			        	parent.parent.orgTree.getSelectNode().getParent().reloadChild();
			        }catch(e){}
			        <l:present property="_ts" propertyType="parameter">
			        	window.closeD();
			        </l:present>	            
			}else{
			      alert( '<b:message key="l_m_save_fail"/>' );         //保存失败 
			}
       }  
    }
	function setexpNote(myAjax){
		var expNote = $name("expNote");
		if(expNote){
			if(expNote.checked){
				myAjax.addParam("omEmpTm.expNote",'1');
			}else{
				myAjax.addParam("omEmpTm.expNote",'0');
			}
		}
		var emergNote = $name("emergNote");
		if(emergNote){
			if(emergNote.checked){
				myAjax.addParam("omEmpTm.emergNote",'1');
			}else{
				myAjax.addParam("omEmpTm.emergNote",'0');
			}
		}
		var genNote = $name("genNote");
		if(genNote){
			if(genNote.checked){
				myAjax.addParam("omEmpTm.genNote",'1');
			}else{
				myAjax.addParam("omEmpTm.genNote",'0');
			}
		}
	}
	function isDisplay(OPObj,checkOpr) {
	   var flag = checkOpr.checked;
	   $id(OPObj).style.display = flag?"":"none";	    	   
	}
	function setAllowNull(vAttr,flag){
	  var options = vAttr.split(';');
	  var found=false;
	  for(var i=0;i<options.length;i++){
	     if(options[i].test('^allowNull=')){
	         options[i]='allowNull='+flag;
	         found = true;
	         break;
	     }
	  }
	  if(!found){
	   options.push('allowNull='+flag);
	  }
	  return options.join(';');
	}
    function roleBeforeSubmit_xt(lookup){
       lookup.clearParam();
       lookup.addParam('partyRole.partyType',"omEmp");
       lookup.addParam('partyRole.jgsx',$name("tomorganiz.jgsx").value);
       lookup.addParam('partyRole.orgDegree',$name("orgDegree").value);
       lookup.addParam('partyRole.roleId',$name("xt_mngrole").value);
       lookup.addParam('partyRole.roleType','1');
       return true;
    }
    function roleBeforeSubmit_yw(lookup){
       lookup.clearParam();
       lookup.addParam('partyRole.partyType',"omEmp");
       lookup.addParam('partyRole.jgsx',$name("tomorganiz.jgsx").value);
       lookup.addParam('partyRole.orgDegree',$name("orgDegree").value);
       lookup.addParam('partyRole.roleId',$name("xt_mngrole").value);
       lookup.addParam('partyRole.roleType','0');
       return true;
    }
    function orgBeforeSubmit(lookup){
       lookup.clearParam();
       lookup.addParam('changeTree.lookupType',4);
       
       lookup.addParam('changeTree.showTabOrg',1);
       lookup.addParam('changeTree.orgType',4);
       lookup.addParam('changeTree.showSelBox',4);
       lookup.addParam('changeTree.lookupTypeStr',4);
       
       var myAjax = new Ajax("/empMngr/initEntityParam_empMngr.action");
       myAjax.submit();
       var orgpentityid =myAjax.getValue("root/data/orgpentityid");
       
       lookup.addParam('changeTree.startOrgid',orgpentityid);
       lookup.addParam('changeTree.checkTitle','说明：可管理机构为已选机构的本级及下级的所有机构(含部门)');
       lookup.addParam('tm',new Date().getTime());
       return true;
    }
    function initComboEmpParam(){
    	var orgid = $name("tomorganiz.orgid").value;
    	if(orgid){}else{
    		orgid = '<%=musous.getOrgid()%>';
    	}
    	return "<omEmp><orgid>"+orgid+"</orgid></omEmp>" ;
    }
    function custInit(){
     // initButtonStyle(); 
      if($id('xt_mngrole')){
      	$id('xt_mngrole').beforeOpenDialog=roleBeforeSubmit_xt;
      }
      if($id('yw_mngrole')){
      	$id('yw_mngrole').beforeOpenDialog=roleBeforeSubmit_yw;
      }
      if($id('wl_mngorg')){
      	$id('wl_mngorg').beforeOpenDialog=orgBeforeSubmit;  
      }   
      var test = $name("listorg").value;
      var myArray=new Array();
       myArray = test.split(",");
       var list="";
       for(var i=0;i<myArray.length;i++)
       {
       		var temp = myArray[i];
       		temp = temp.substring(1,temp.length-1)+",";
       		list += temp;
       }
       if($id('wl_mngorg')){
      		$id("wl_mngorg").value = list;
       }
    }
   /**
    *   密码重置
    **/
    function resetButFun(){
    	if(window.confirm("是否确定重置密码！")){
	    	var myAjax = new Ajax("/empMngr/resetPwd_empMngr.action");
	    	myAjax.addParam("operatorid", $name("empid").value);
			myAjax.addParam("password", "111111");
			myAjax.submit();
	    	var returnNode =myAjax.getResponseXMLDom();
	    	if(parseInt(myAjax.getValue("root/data/result")) == 1){
	    		alert("重置密码成功！");
	    	}else{
	    		alert("重置密码失败！");
	    	}
    	}
    	
    }
	String.prototype.replaceAll = function(AFindText,ARepText){
		raRegExp = new RegExp(AFindText,"g");
		return this.replace(raRegExp,ARepText)
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
   /**
    *	获取扩展字段并显示出来
    **/
     function showfiled()
     {
     	var code = $id("codes").value;
     	var name = $id("names").value;
     	var arr = code.split(",");
     	var arrname = name.split(",");
     	for(var i=0;i<arr.length;i++)
    	{
			if(arr[i] && arrname[i])
			{
				if(i%2==0){
					line = oEmpDiv.insertRow();
				}
				var td1 = line.insertCell();
				td1.className = "form_label";
				var td2 = line.insertCell();
				td1.innerHTML = arrname[i]; 
				var test = "<input type='text' id='"+arr[i]+ "' name='abfygfjxx."+arr[i]+ "' />";
				td2.innerHTML =test;	
				td1.colspan ="1";
				td1.align="right";     					
			}
    	}
     }
     function getvalues()
     {
     	var code = $id("codes").value;
     	var name = $id("names").value;
     	var arr = new Array();
     	arr = code.split(",");
     	var arrname = new Array();
     	arrname = name.split(",");
     	var len = arr.length;
     	for(var i=0;i<len;i++)
     	{
     		var ts = document.getElementById("abfygfjxx."+arr[i]);
     	}
     	
     } 
     function setHeadpicFun(){
  //      url="org.gocom.abframe.org.synchronization.setheadpicflow.flow?empid="+$name("omEmp.empid").value+"&dtime="+new Date().getTime();
  //      showModalCenter(url,null,null,350,215,"设置头像");
     }
     function checkEmpcode(obj){
     	if(obj.value != ""){
     		var myAjax = new Ajax("/empMngr/checkEmpCode_empMngr.action");
     		myAjax.addParam("empcode",obj.value);
     		myAjax.submit();
     		var rtun = parseInt(myAjax.getValue("root/data/flag"));
     		if(rtun == -1){
     			alert("人员工号检查失败");
     			obj.focus();
     		}else if(rtun > 0){
     			alert("该员工号已被其它用户使用");
     			obj.focus();
     		}
     	}
     }
  	eventManager.add(window,"load",custInit);

  	function returnFunc(arg){
  	  	if(arg!=""){
  	  	  	return arg;
  	  	  	}
  	}
    
</script>
</body>
</html>


