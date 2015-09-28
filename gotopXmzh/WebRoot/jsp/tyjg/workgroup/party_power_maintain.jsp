<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
<title>Title</title>

<script>

    function initGroupDC() {
    	var param="<group><groupid><b:write property="group/groupid"/></groupid></group>";
    	return param;
    }
    
    var datacellObj = null;
    
    //双击角色，查看角色功能列表
    function dbclick( value ) {
        $name("rolefunc/_expr[1]/roleid").value = value;
        datacellObj.queryAction = "org.gocom.abframe.org.party.PartyManager.queryPartyRolefuncView.biz";
        datacellObj.reload();
    }
    var groupid = '<b:write property="group/groupid" />';
    function allFunc() {
        datacellObj.queryAction = "org.gocom.abframe.org.workgroup.WorkgroupManager.queryAllGroupRoleFunction.biz";
        datacellObj.reload();
    }
    
    //删除已经授予角色
    function deleteRecord( ) {
        var bSelect = false;
        var s1 = $id("grantedSelect");
        var myAjax = new Ajax("org.gocom.abframe.org.party.PartyManager.deletePartyRole.biz");
        
        for (i=0; i < s1.options.length; i++) {
		    if (s1.options[i].selected) {
		        bSelect = true;
		        myAjax.addParam("omPartyrole[" + (i+1) + "]/acRole/roleid", s1.options[i].value);
		    }
		}
		if( !bSelect ) {
		    alert("没有选择角色！");
		    return;
		}
		myAjax.addParam("oPartyrole/partyid", '<b:write property="oPartyrole/partyid"/>');
		myAjax.addParam("oPartyrole/partytype", '<b:write property="oPartyrole/partytype"/>');
		myAjax.submit();
        var returnNode = myAjax.getResponseXMLDom(); 
        if( returnNode ) {
            if( myAjax.getValue("root/data/retCode") == 1 ) {
	            moveSelectedOptions($id('grantedSelect'), $id('allowSelect'), 20);
	            alert("角色删除成功！");
		        //刷新DataCell
		        datacellObj.reload();            
	        } else {
	            alert("角色删除失败！");
	        }
        }else {
            alert("角色删除失败！");
        }

    }
    //给岗位增加未授予的角色
    function addRecord(  ) {
        var bSelect = false;
        var s1 = $id("allowSelect");
        var myAjax = new Ajax("org.gocom.abframe.org.party.PartyManager.addPartyRole.biz");
        
        for (i=0; i < s1.options.length; i++) {
		    if (s1.options[i].selected) {
		        bSelect = true;
		        myAjax.addParam("omPartyrole[" + (i+1) + "]/acRole/roleid", s1.options[i].value);
		    }
		}
		if( !bSelect ) {
		    alert("没有选择角色！");
		    return;
		}
        myAjax.addParam("oPartyrole/partyid", '<b:write property="oPartyrole/partyid"/>');
		myAjax.addParam("oPartyrole/partytype", '<b:write property="oPartyrole/partytype"/>');
        myAjax.submit();
        var returnNode = myAjax.getResponseXMLDom(); 
        if( returnNode ) {
            if( myAjax.getValue("root/data/retCode") == 1 ) {
                moveSelectedOptions($id('allowSelect'),$id('grantedSelect'),20);
                alert("角色增加成功！");
                //刷新DataCell
        		datacellObj.reload();
            } else {
                alert("角色增加失败！");
            }
        } else {
            alert("角色增加失败！");
        }
    }
    
    /*
      *  自定义初始化按钮样式
      */
	 function custInit(){  
		initButtonStyle();
		
		datacellObj = $id("id1");
		//给datacell设定自定义按钮
        datacellObj.setCustomTool("<a href='javascript:allFunc();'>All</a>");
	 }
</script>

</head>
<body topmargin="0" leftmargin="0">

    <TABLE border="0" width="100%" >
		<TR>
		    <TD width="150" height="380" valign="top">
		        <h:form name="orgRoleForm" action="org.gocom.abframe.org.party.PartyRightManitain.flow" method="post">
		            <!--<w:panel id="groupRoleP" width="160"  title="已授予角色">-->
		                 <table align="center" border="0" width="100%" class="EOS_table">
				             <tr>
						       <td class="eos-panel-title">&nbsp;已授予角色</td>
						      </tr>
				             
				             <tr>
				                 <td>
				                     <h:select  multiple="true" style="width:150;height:380" id="grantedSelect" ondblclick="dbclick(this.value)">
				                         <l:iterate id="oPartyroles" property="oPartyroles" >
				                             <h:option labelField="acRole/rolename" valueField="acRole/roleid" iterateId="oPartyroles"/>
				                         </l:iterate>
				                 	 </h:select>
				                  </td>
				             </tr>
				         </table>
		           <!-- </w:panel>-->
		        </h:form>
		    </td>   
		    <td>
         	    <input type="button" value="&nbsp;&gt;&gt;&nbsp;" style="width:30" title="删除" onclick="deleteRecord();"/><br><br>
         	    <input type="button" value="&nbsp;&lt;&lt;&nbsp;" style="width:30" title="增加" onclick="addRecord();"/>
         	</td>
         	<td valign="top" width="150" height="380" >
		        <h:form name="allowOrgRoleForm" action="org.gocom.abframe.org.party.PartyRightManitain.flow" method="post">
		            <!--<w:panel id="allowGroupRoleP" width="160" title="未授予的角色">-->
		                <table align="center" border="0" width="100%" class="EOS_table">
				          <tr>
						       <td class="eos-panel-title">&nbsp;未授予角色</td>
						      </tr>
				          <tr>
				              <td>
			                     <h:select  multiple="true" style="width:150;height:380" id="allowSelect" ondblclick="dbclick(this.value)">
			                         <l:iterate id="omPartyroles" property="omPartyroles" >
			                             <h:option labelField="rolename"  valueField="roleid" iterateId="omPartyroles"/>
			                         </l:iterate>
			                 	 </h:select>	 
				              </td>
				          </tr>
				         </table>
		           <!-- </w:panel>-->
		        </h:form>
		    </TD>
		    <td valign="top">
		        <!--<w:panel title="角色功能列表" width="200" id="roleFunction" >-->
		        	<table align="center" border="0" width="100%" class="EOS_table">
				          <tr>
						       <td height="20px"class="eos-panel-title">&nbsp;角色功能列表</td>
						      </tr>
				         </table>
		            <r:datacell id="id1"
			            queryAction="org.gocom.abframe.org.party.PartyManager.queryPartyRolefuncView.biz"
			            xpath="rolefuncs" pageSize="13"
			            width="100%" 
			            height="382" initParamFunc="" paramFormId="datacellForm">
			            <r:field width="350px" fieldName="funcname" label="功能名称"/>
			            <r:toolbar tools="nav:first prev next last goto,edit: reload"  location="bottom" />     
			        </r:datacell>
			
		       <!-- </w:panel>-->
		        
		    </td>
		</TR>    
	</TABLE>
	<form id="datacellForm">
		        	<h:hidden property="rolefunc/_entity" value="org.gocom.abframe.datasetExp.organization.AcRolefuncView" />	      
			       	<h:hidden property="rolefunc/_expr[1]/roleid" />
			       	<h:hidden property="rolefunc/_expr[1]/_op" value="in" />
		        </form>
</body>
</html>

<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
</script>