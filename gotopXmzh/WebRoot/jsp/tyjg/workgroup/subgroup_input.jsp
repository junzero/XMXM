<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<%
	//获取标签中使用的国际化资源信息
	String title=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("groupUpdate_l_title_group_update");
%>
<head>
<title><%=title%></title><!--工作组录入 -->
</head>
<h:script src="/common/gotop/web-common.js" />
<body leftmargin="0px;" topmargin="0px;">
<%--dataform configurationID:20080820194550 --%>
<h:form name="dataform1" action="" checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
  <h:hidden property="group.groupid" />
  <h:hidden property="parentGroupid" scope="flow"/>
  <h:hidden property="group.parentgroupid" />
  
    <input type="hidden" name="_eosFlowAction" value="updateSubmit" >
    <table align="center" border="0" width="100%" class="form_table">
      <tr>
        <td class="form_label" nowrap="nowrap">
         <%-- <b:message key="workgroupManager_OmGroup.groupname" /><b:message key="l_colon"/> --%><!--工作组名称 -->
         群组名称：
        </td>
        <td colspan="1">
          <h:text property="group.groupname" maxlength="50" validateAttr="allowNull=false;maxLength=50"/><font style="color:red">*</font>
        </td>
        <td class="form_label" nowrap="nowrap">
          创建人<b:message key="l_colon"/> <!--负责人 -->
        </td>
        <td colspan="1">
      		<h:text property="group.manager" readonly="true"/>
			<h:hidden property="group.empid" />
        </td>
      </tr>
      <tr>
        <td class="form_label" nowrap="nowrap">
          <%--<b:message key="workgroupManager_OmGroup.grouptype" />--%>群组类型<b:message key="l_colon"/> <!--工作组类型 -->
        </td>
        <td colspan="1">
         <l:empty property="group.grouptype">
          <l:empty property="grouptype">
	          <l:empty property="dictID">
	          <d:select dictTypeId="ABF_GROUPTYPE" style="width:133" property="group.grouptype"></d:select> 
	          </l:empty>
	          <l:notEmpty property="dictID">
	          <d:write dictTypeId="ABF_GROUPTYPE" property="group.grouptype" />
	          <h:hidden property="group.grouptype"/>
	          </l:notEmpty>
          </l:empty>
          <l:notEmpty property="grouptype">
          	<d:write dictTypeId="ABF_GROUPTYPE" value="私有群组"/>
            <h:hidden property="group.grouptype" value="personal"/>
          </l:notEmpty>
         </l:empty>
         <l:notEmpty property="group.grouptype">
         	<d:write dictTypeId="ABF_GROUPTYPE" property="group.grouptype"/>
	         <h:hidden property="group.grouptype"/>
         </l:notEmpty>
        </td>      
        <td class="form_label" nowrap="nowrap">
          <%--<b:message key="workgroupManager_OmGroup.groupstatus" />--%>群组状态<b:message key="l_colon"/><!--工作组状态 -->
        </td>
        <td colspan="1">
          <d:select dictTypeId="ABF_GROUPSTATUS" property="group.groupstatus" value="run" style="width: 150"/>
        </td>
      </tr>
      <l:notEqual property="isAdd" targetValue="true">
	      <l:equal property="group.grouptype" targetValue="public">
		      <tr>
		        <td class="form_label" nowrap="nowrap">
		          使用范围<b:message key="l_colon"/><br/>(拥有该群组的)
		        </td>
		        <td colspan="3">
		        	<table border="0" height="100%" cellpadding="0" cellspacing="0">
						<tr>
						  <th>
						    使用机构
						  </th>
						  <th>
						    使用角色
						  </th>
						  <th>
						    使用人员
						  </th>
						  <th>
						  </th>
						</tr>
		        		<tr>
			        		<td valign="top">
					        	<h:textarea id="orgnames" name="orgnames" property="group.orgnames" readonly="true" cols="19" rows="15"/>
			        		</td>
			        		<td>
					        	<h:textarea id="rolenames" name="rolenames" property="group.rolenames" readonly="true" cols="19" rows="15"/>
			        		</td>
			        		<td>
					        	<h:textarea id="empnames" name="empnames" property="group.empnames" readonly="true" cols="19" rows="15"/>
					        	<h:hidden id="empids" name="empids" property="group.empids"/>
					        	<h:hidden id="orgids" name="orgids" property="group.orgids"/>
					        	<h:hidden id="roleids" name="roleids" property="group.roleids"/>
			        		</td>
		        			<td	colspan="3" valign="bottom" >
					            <input type="button" value="使用选择范围" onclick="scopeFun2()" />
		        			</td>
		        		</tr>
		        	</table>
		        </td>
		      </tr>
	      </l:equal>
      </l:notEqual>
      <tr>
        <td class="form_label" nowrap="nowrap">
          <%--<b:message key="workgroupManager_OmGroup.groupdesc" />--%>群组描述<b:message key="l_colon"/> <!--工作组描述 -->
        </td>
        <td colspan="3">
          <h:textarea  property="group.groupdesc" validateAttr="maxLength=512" cols="70"/>
        </td>
      </tr>
      <tr class="form_bottom">
        <td colspan="6">
       	  <l:equal property="isEdit" targetValue="true">
              <input type="button" value="<b:message key='l_save' />" onclick="saveGroup()">
          </l:equal>
              <input type="button" value="<b:message key='l_close' />" onclick="javascript:window.closeD();">
         
        </td>
      </tr>
    </table>
</h:form>
<script>
	//r:comboSelect初始化数据
	function initComboEmpParam()
	{
		var groupid = $name("parentGroupid").value;
		if(groupid == "")
		{
			groupid = $name("group/groupid").value;
		}
		return "<groupid>"+groupid+"</groupid>" ;
	}
	
	// 提交新增或更新子工作组
    function saveGroup() {
        var frm = $name("dataform1");
        
	    //表单验证
	    if( !checkForm(frm) ) {
	        return;
	    }
	
        var myAjax = null;
        if($name("group.groupid").value) {
            myAjax = new Ajax("/workgroup/groupmanagerAction_updateGroup.action");
        } else {
            myAjax = new Ajax("/workgroup/groupmanagerAction_addGroup.action");
        }

        myAjax.submitForm(frm);
        var returnNode = myAjax.getResponseXMLDom();
        if( returnNode ) {
            if( myAjax.getValue("oprResult") == 1 ){
                alert( "<b:message key='l_m_save_success' />" );// <!--  保存成功 -->
                try{
                	parent.parent.window.frames['groupTree'].groupTree.getSelectNode().getParent().reloadChild();  //刷新左侧树，注意：要刷新的是选中节点的上级节点
                }catch(e){}
               
            }else {
                alert( "<b:message key='l_m_save_fail' />" );   // <!-- 保存失败 -->     
            }
        } else {
            alert( "<b:message key='l_m_save_fail' />" ); //   <!-- 保存失败 --> 
        }
        window.closeD();
    }
    function scopeFun(){
    	var strUrl = "org.gocom.abframe.org.publicom.mngorgAssignTree.flow?orgType="+(2+3+4)+"&showSelBox="+(2+3+4);
		var peArgument = {};
    	var paramEntity = new ParamEntity('Employee');
		paramEntity.setProperty('empid',$id("empids").value);
		paramEntity.setProperty('empname',$id("empnames").value);
		peArgument['Employee']=[paramEntity,'empname','empid'];
		
    	var paramEntity = new ParamEntity('Organization');
		paramEntity.setProperty('orgid',$id("orgids").value);
		paramEntity.setProperty('orgname',$id("orgnames").value);
		peArgument['Organization']=[paramEntity,'orgname','orgid'];
		
    	showModalCenter(strUrl,peArgument,openEmpTreeCallBack,500,430,'选择使用范围')
    }
    function scopeFun2(){
    	var strUrl = "/tree/initMainTree_mainTree.action?changeTree.orgType=9&changeTree.showTabOrg=1&changeTree.showTabGroup=0&changeTree.showTabRole=1&time="+new Date().getTime();
		var peArgument = [];
//人员
    	var paramEntity = new ParamEntity('Employee');
		paramEntity.setProperty('empid',$id("empids").value);
		paramEntity.setProperty('empname',$id("empnames").value);
		peArgument[0]=[paramEntity,'empname','empid'];
//机构		
    	var paramEntity = new ParamEntity('Organization');
		paramEntity.setProperty('orgid',$id("orgids").value);
		paramEntity.setProperty('orgname',$id("orgnames").value);
		peArgument[1]=[paramEntity,'orgname','orgid'];
//角色		
    	var paramEntity = new ParamEntity('Role');
		paramEntity.setProperty('roleid',$id("roleids").value);
		paramEntity.setProperty('rolename',$id("rolenames").value);
		peArgument[2]=[paramEntity,'rolename','roleid'];
		
    	showModalCenter(strUrl,peArgument,openEmpTreeCallBack,500,430,'选择使用范围')
    }
    function openEmpTreeCallBack(arg){
    	if(arg['Employee']){
	  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
	  		argRes=[[],[],[],[]];
			for(var i=0;i<sorgidArra.length;i++){
				argRes[0].push(sorgidArra[i].getProperty("empid"));
				argRes[1].push(sorgidArra[i].getProperty("empname"));
			}
			$id("empids").value = argRes[0];
			$id("empnames").value = argRes[1];
    	}else{
			$id("empids").value = "";
			$id("empnames").value = "";
    	}
		if(arg['Role']){
	  		var sorgidArra  = arg['Role'].slice(0);//角色数组
	  		argRes=[[],[],[],[],[]];
			for(var i=0;i<sorgidArra.length;i++){
				argRes[0].push(sorgidArra[i].getProperty("roleid"));
				argRes[1].push(sorgidArra[i].getProperty("rolename"));
			}
			$id("roleids").value = argRes[0];
			$id("rolenames").value = argRes[1];
		}else{
			$id("roleids").value = "";
			$id("rolenames").value = "";
		}
		if(arg['Organization']){
	  		var sorgidArra  = arg['Organization'].slice(0);//机构数组
	  		argRes=[[],[],[],[],[]];
			for(var i=0;i<sorgidArra.length;i++){
				argRes[0].push(sorgidArra[i].getProperty("orgid"));
				argRes[1].push(sorgidArra[i].getProperty("orgname"));
			}
			$id("orgids").value = argRes[0];
			$id("orgnames").value = argRes[1];
		}else{
			$id("orgids").value = "";
			$id("orgnames").value = "";
		}
    }
</script>
</body>
<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window, "load", initButtonStyle); 
</script>
</html>