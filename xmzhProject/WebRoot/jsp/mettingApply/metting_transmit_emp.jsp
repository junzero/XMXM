<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script>
 function open_emp_checkcount_fun(){
	 var strUrl="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2&changeTree.startOrgid="+${sessionScope.login_user.orgid};	
		var peArgument = [];
    	  var paramEntity = new ParamEntity('Employee');	
    	paramEntity.setProperty("empid",$("#empid").val());
    	paramEntity.setProperty("empname",$("#empname").val());
    	peArgument[2]=[paramEntity,'empname','empid'];
    	showModalCenter(strUrl,peArgument,openEmpCheckcountCallBack,500,430,'选择人员');
    }
    function openEmpCheckcountCallBack(arg){
        if(arg!=''){
	  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
	  		argRes=[[],[],[],[]];
			for(var i=0;i<sorgidArra.length;i++){
				argRes[0].push(sorgidArra[i].getProperty("empid"));
				argRes[1].push(sorgidArra[i].getProperty("empname"));
			}
				 $("#empid").val(argRes[0]);
				 $("#empname").val(argRes[1]);
        }

    }
    function doSave(){
		var options = {
		  type : "post",
		  cache : "false",
		  timeout: 60000,
		  success : function(data){
			  try{
					if(data.indexOf("success")>0){
						alert("转发成功！！");
						window.close();
					}else{
					    alert("转发失败！");
					}
				}catch(Exception){
				
				}
			},  
		  error : function(data){
			  alert("保存出错，请联系管理员！");
		   }  
		}; 
		// 异步提交请求  
		$("#inputForm").ajaxSubmit(options);
	}
    
</script>
<title>信息转发</title>
</head>
<body>

<h:form name="inputForm" id="inputForm"
	action="/mettingApply/tMettingApplyAction_saveTransmitEmp.action" method="post"
	enctype="multipart/form-data" checkType="blur" target="_self" onsubmit="return checkForm(this);">
	<input type="hidden" name="_eosFlowAction" value="move">
	<h:hidden property="meet.mettingId"  value="${param.mettingId }"/>
	<h:hidden name="meet.opninion" property="meet.opninion" value="${param.opninion }"/>
	<table align="center" border="0" class="form_table" width="100%">
		<tr>
			<td class="form_label" align="right" width="25%" id="queryName">接 收 人:</td>
			<td colspan="2"><h:hidden
				property="meet.joinEmp" id="empid" /> 
				<h:text property="meet.joinEmpname" validateAttr="allowNull=false" id="empname"
				readonly="true" name="meet.joinEmpname" /> <input type="button" value="选择" class="button"
				onclick="open_emp_checkcount_fun()" /></td>
		</tr>
		<tr class="form_bottom">
		<td colspan="3">
	       <input type="button" value="提交" onclick="doSave()" class="button"> 
	    </td>
		</tr>

	</table>
</h:form>
</body>
</html>
