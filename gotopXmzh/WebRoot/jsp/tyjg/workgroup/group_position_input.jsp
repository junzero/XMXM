<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<%
	//获取标签中使用的国际化资源信息
	String title=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("groupPositionMaintain_l_title_groupPositionInput");
	String selectDuty = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("positionManager_l_title_selectDuty");
%>
<head>
<title><%=title%></title>  <!--工作组岗位录入 -->
<h:css href="/css/style1/style-custom.css"/>
</head>
<body>
<%--dataform configurationID:20080824100942 --%>
<h:form name="dataform1" action="org.gocom.abframe.org.workgroup.GroupPositionMaintain.flow" checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
  <h:hidden property="position/positionid" />
  <h:hidden property="group/groupid" />
  <h:hidden property="parentGroupid" scope="flow"/>
  <l:equal property="_eosLastAccessAction" targetValue="update">
      <input type="hidden" name="_eosFlowAction" value="updateSubmit" >
  </l:equal>
  <l:equal property="_eosLastAccessAction" targetValue="insert">
    <input type="hidden" name="_eosFlowAction" value="insertSubmit" >
  </l:equal>
  <input type="hidden" name="_eosFlowAction" value="insertSubmit"/>
    <table align="center" border="0" width="100%" class="form_table">
      <tr>
        <td class="form_label">
          <b:message key="groupPositionMaintain_l_Omposition.posiname" /> <!--岗位名称 -->
        </td>
        <td colspan="1">
          <h:text property="position/posiname"/>
        </td>
        <td class="form_label">
          <b:message key="groupPositionMaintain_l_Omposition.positype" /> <!--岗位类别 -->
        </td>
        <td colspan="1">
          <d:write dictTypeId="ABF_POSITYPE" property="position/positype" value="workgroup"/>
        </td>
      </tr>
      <tr>
        <td class="form_label">  <!-- 岗位代码  -->
          <b:message key="groupPositionMaintain_l_OmPosition.posicode"></b:message>
        </td>
        <td colspan="1">
          <h:text property="position/posicode" validateAttr="maxLength=20;allowNull=false;"/>
        </td>
        <td class="form_label">   <!--  所属职务 -->
         <b:message key="groupPositionMaintain_l_OmPosition.omDuty.dutyid(dutyname)"></b:message>
        </td>
        <td colspan="1">																				<!--  选择职务 -->
          <w:lookup property="position/omDuty/dutyid" validateAttr="allowNull=false;" readonly="true" displayProperty="position/omDuty/dutyname"  dialogTitle="<%=selectDuty %>"  width="350" height="430" lookupUrl="/org/duty/duty_tree.jsp" onReturnFunc="lookupRetFun"></w:lookup>
        </td>
      </tr>
      <tr>
        <td class="form_label">
          <b:message key="groupPositionMaintain_l_Omposition.startdate" /> <!--岗位有效开始日期 -->
        </td>
        <td colspan="1">
          <w:date property="position/startdate"/>
        </td>
        <td class="form_label">
          <b:message key="groupPositionMaintain_l_Omposition.enddate" /> <!--岗位有效截止日期 -->
        </td>
        <td colspan="1">
          <w:date property="position/enddate"/>
        </td>
      </tr>
      <tr class="form_bottom">
        <td colspan="6">
              <input type="button" value="<b:message key='l_save' />" onclick="savePosition()">
              <input type="button" value="<b:message key='l_close' />" onclick="javascript:window.closeD();">
        </td>
      </tr>
    </table>
</h:form>
<script>
    //lookup 控件返回函数
    function lookupRetFun( arg ) {
        return true;
    }
    
    function savePosition() {
        var frm = $name("dataform1");
	    //表单验证
	    if( !checkForm(frm) ) {
	        return;
	    }
        var myAjax = null;
        if( $name("_eosFlowAction").value == "insertSubmit"  ) {
            myAjax = new Ajax("org.gocom.abframe.org.workgroup.WorkgroupManager.addGroupPosition.biz");
            myAjax.addParam("position/positionid", $name("position/positionid").value );
	        myAjax.addParam("position/posiname", $name("position/posiname").value );
	        myAjax.addParam("position/positype", 'workgroup');
	        myAjax.addParam("position/posicode", $name("position/posicode").value  );
			if($name("position/omDuty/dutyid").value !=="") {
				myAjax.addParam("position/omDuty/dutyid", $name("position/omDuty/dutyid").value  );
			}
	        
	        myAjax.addParam("position/startdate", $name("position/startdate").value  );
	        myAjax.addParam("position/enddate", $name("position/enddate").value);
	        myAjax.addParam("group/groupid", $name("parentGroupid").value);
        } else {
            myAjax = new Ajax("org.gocom.abframe.org.position.PositionManager.updatePosition.biz");
            myAjax.addParam("position/positionid", $name("position/positionid").value );
	        myAjax.addParam("position/posiname", $name("position/posiname").value );
	        myAjax.addParam("position/positype", 'workgroup');
	        myAjax.addParam("position/posicode", $name("position/posicode").value  );
	        if($name("position/omDuty/dutyid").value !=="") {
				myAjax.addParam("position/omDuty/dutyid", $name("position/omDuty/dutyid").value  );
			}
	        myAjax.addParam("position/startdate", $name("position/startdate").value  );
	        myAjax.addParam("position/enddate", $name("position/enddate").value);     
        }

        myAjax.submit();
        var returnNode = myAjax.getResponseXMLDom();
        if( returnNode ) {
            if( (myAjax.getValue("root/data/oprResult") == 1)||(myAjax.getValue("root/data/retCode") == 1 ))
                alert( "<b:message key='l_m_save_success' />" );// <!--  保存成功 -->
            else {
                alert( "<b:message key='l_m_save_fail' />" );  //  <!-- 保存失败 -->    
            }
        } else {
            alert( "<b:message key='l_m_save_fail' />" );   // <!-- 保存失败 -->  
        }
        
        window.closeD();
    }
</script>
</body>
<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window, "load", initButtonStyle); 
</script>
</html>