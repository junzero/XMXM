<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="/common/gotop/jquery.min.js"></script>
<title>请假新增</title>
</head>
  
  <body>
    <h:form action="" method="post">
<table align="center" border="0" width="100%" class="form_table">
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		 请假单
        </td>
      </tr>
      <tr>
      	<td class="form_label">
         标　　　 题：
        </td>
        <td colspan="3">
          <input type="text" name="tbusiness/lv_title" validateAttr="allowNull=false" style="width:90%;" value="<b:write property="lv_title" />" readonly="true">
        </td>
      </tr>
      <tr>
        <td class="form_label">
          申　 请　人：
        </td>
        <td colspan="1">
          <span id="applicant" name="applicant"><b:write property="lv_title" scope="session" /></span>
        </td>
        <td class="form_label">
         联 系 电 话：
        </td>
        <td colspan="1">
          <input type="text" name="tbusiness/applyTelephone" value="<b:write property="lv_title" scope="session" />" validateAttr="maxLength=11" style="width:148px;">	
        </td>
      </tr>
      <tr>
         <td class="form_label">
          申 请 部 门：
        </td>
        <td colspan="1">
         <h:text id="org" name="org" style="width:148px;" />
          <input type="hidden" name="tbusiness/applyOrgCd" value="304">
           <input type="hidden" name="tbusiness/applyUserRole" value="[Ljava.lang.String;@272484">
        </td>
         <td class="form_label">
       <nobr>
          申 请 日 期：</nobr>
        </td>
        <td colspan="1">
          <input type="text" id="appDate" name="appDate" readonly="readonly" style="width:148px;" value="<b:write property="lv_start" />" />
		</td>
      </tr>
      <tr>
       <td class="form_label">请 假 类 型：</td>
     	<td colspan="3">
     		<span id="ckb5202516_container"><div><h:select style="width:148px;" value="<b:write property="lv_type" />"><h:option value="1" label="病假"></h:option><h:option value="2" label="事假"></h:option><h:option value="3" label="其他"></h:option></h:select></div></span>
     	</td>
         </tr>
     <tr>
     	<td class="form_label">请 假 日 期：</td>
     	<td>
     		<div id="startDate_container" class="eos-ic-container"><input class="eos-calendar-editor-text" type="text" id="startDate_input" validateAttr="type=calendar;allowNull=true" tagformat="yyyy-MM-dd" submitFormat="yyyyMMdd" allowNull="true" readOnly="false"/><img id='startDate_button' class="eos-ic-button"/><input type="hidden" name="tsafeapply/startDate" id="startDate_hidden"/></div><script language="javascript">
var _date =new Calendar("startDate","yyyy-MM-dd");
_date.submitFormat = "yyyyMMdd";
_date.readOnly = false;
_date.init();
</script>
     		<font style="color: red">*</font>	
     	</td>
     	<td class="form_label">请 假 天 数：</td>
     	<td>
     		<input type="text" name="tleave/leaveDateNum" validateAttr="minValue=0;maxValue=100;type=float;allowNull=false" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')">
     		<font style="color: red">*</font>
     	</td>
     </tr>
     <tr>
     	<td class="form_label">请假原因：</td>
     	<td colspan="3">
     	<textarea name="tcomments/reason" validateAttr="maxLength=4000" class="wid" rows="4"  style="width:90%;"></textarea>
	       
	        	<font style="color: red">*</font>
	       
     	</td>
     </tr>
      <tr class="form_bottom">
              <td colspan="4">
                <input type="button" value="保存" class="button" onclick="doSubmit();" id="savess">
                <input type="button" name="bt1" value="提交" class="button" onclick="execFlow(this,'manual','qicao')" id="tijiao">
                <input type="button" value="查看流程图" onclick="addRecord();" class="button" id="querytu">
               </td>
            </tr>
    </table>
    </h:form>
  </body>
  <script type="text/javascript">
		$(document).ready(function(){
				var date = new Date(+new Date()+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');
				$("#appDate").val(date);
			});
		var idArray = [$id("savess"),$id("tijiao"),$id("querytu")];
		function disableAndenable(idArray,type){
			var size=idArray.length;
			for(var i=0;i<size;i++){
				switch(type){
					case "yes" : idArray[i].removeAttribute("disabled");break;
					default : idArray[i].setAttribute("disabled","disabled");break;
				}
			}
		}
		function execFlow(actObj,atype,isApproval){
			disableAndenable(idArray,"no");
			actObj.title=$name("tbusiness/title").value;
			tool.openActivityDialog(actObj,atype,isApproval);
			disableAndenable(idArray,"yes");
		}
  </script>
</html>
