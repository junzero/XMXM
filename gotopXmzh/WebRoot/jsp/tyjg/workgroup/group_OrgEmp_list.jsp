<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common.jsp"%>
<h:script src="/common/gotop/alertbox.js"/>
<h:script src="/common/gotop/web-common.js" />
<html>
<head>
<%
	//获取标签中使用的国际化资源信息
	String title=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_title_groupEmpList");
 %>
<title><%=title%></title> <!--工作组员工查询 -->
<script>
	 //刷新指定树的选中节点 
	function refreshTreeNode(tree){
		try{
			tree.getSelectNode().reloadChild();
		}catch(e){
			//alert(e);
		}
	}
	 
	 //实现全选复选框
	 function checkSelectAll(){
		if ($id("checkSelect").checked){
			selectAll("group1");
		}else{
			selectNone("group1");
		}
	 } 

      //模态窗口回调函数，关闭模态窗口时，刷新父窗口的数据
    function callBack() {
        var frm = $name("viewlist1");
        frm.elements["_eosFlowAction"].value = "reQuery";
        frm.submit();
    }
    //增加工作组员工
    function addRecord() {
		if(window.parent.$name("group.empid").value!=$name("group.empid").value){
			alert('操作失败！只有创建人才有操作权限');
			return;
		}
		open_new_tree_fun();
    }
    
    //删除工作组员工
    function delRecord() {
		if(window.parent.$name("group.empid").value!=$name("group.empid").value){
			alert('操作失败！只有创建人才有操作权限');
			return;
		}
        var frm = $name("viewlist1");
        var g = $id("group1");
        if(  g.getSelectLength() < 1 ) {
            alert("至少选择一行");//<!--至少选择一行！ -->
            return;
        }
	    if( !confirm("您确认要删除选中的员工或机构？") ) { //<!--您确认要删除选中的员工？ -->
	         return;
	    }
	    
	    var orgids = new Array();
	    var empids = new Array();
	    var roleids = new Array();
	    
	    g.getSelectRows().forEach(function(s){
 			var eoid = s.getParam("eoid");
 			var code = s.getParam("code");
 			var eotype = s.getParam("eotype");
 			
 			if(eotype  == 1 ){
 				orgids.push(eoid);
 			}else if(eotype == 2){
 				empids.push(eoid);
 			}else if(eotype == 3){
 				roleids.push(code);
 			}
 			
 			
 		});
	    
	    var myAjax = new Ajax("/workgroup/groupmanagerAction_deleteGroupEmpOrg.action");
	 	myAjax.addParam("groupid",$name("group.groupid").value);
	 	myAjax.addParam("orgids",orgids.toString());
	 	myAjax.addParam("empids",empids.toString());
	 	myAjax.addParam("roleids",roleids.toString());
	 	myAjax.submit();
	 	if(myAjax.getValue("issucc")==1){
			var frm = $name("queryForm"); 
    		frm.submit();
    		alert('操作成功');
	 	}else{
	 		alert('操作失败');
	 	}
	    
    }
      /* load初始化函数
      *
      */
	 function custInit(){  
	 	var isReload = '<b:write property="isReload"/>';
	 	if(isReload){
	 		refreshTreeNode(parent.parent.window.frames['groupTree'].groupTree);
	 	}
		initButtonStyle();
	 }
	 
	 
	 
	 
     
function open_new_tree_fun() {
	var strUrl = "/workgroup/managertreeAction_initMainTree.action?changeTree.orgType=9&changeTree.showTabOrg=1&changeTree.showTabGroup=0&changeTree.showTabRole=1";
	strUrl+= "&changeTree.groupID="+$name("group.groupid").value;
	var peArgument = [];
	var empid = "";
	var empname = "";
	var orgid = "";
	var orgname = "";
	// 人员
	var paramEntity = new ParamEntity('oaEmp');
	paramEntity.setProperty('empid',empid);
	paramEntity.setProperty('empname', empname);
	peArgument[0] = [paramEntity, 'empname', 'empid'];
	// 机构
	var paramEntity = new ParamEntity('oaOrg');
	paramEntity.setProperty('orgid',orgid);
	paramEntity.setProperty('orgname',orgname);
	peArgument[1] = [paramEntity, 'orgname', 'orgid'];

	strUrl += "&time=" + new Date().getTime();
	showModalCenter(strUrl, peArgument, openNewEmpywTreeCallBack, 500, 430,'选择使用范围')
}



function openNewEmpywTreeCallBack(arg) {
	if(arg==null || arg['Employee']==null){
		return;
	}

	var empid = "";
	var empname = "";
	var orgid = "";
	var orgname = "";
	var roleid = "";
	var rolename = "";
	
	if (arg['Employee']) {
		var sorgidArra = arg['Employee'].slice(0);// 人员数组
		argRes = [[], [], [], []];
		for (var i = 0; i < sorgidArra.length; i++) {
			argRes[0].push(sorgidArra[i].getProperty("empid"));
			argRes[1].push(sorgidArra[i].getProperty("empname"));
		}
		empid = argRes[0];
		empname = argRes[1];
	} else {
		empid = "";
		empname = "";
	}
	
	if (arg['Organization']) {
		var sorgidArra = arg['Organization'].slice(0);// 机构数组
		argRes = [[], [], [], [], []];
		for (var i = 0; i < sorgidArra.length; i++) {
			argRes[0].push(sorgidArra[i].getProperty("orgid"));
			argRes[1].push(sorgidArra[i].getProperty("orgname"));
		}
		orgid = argRes[0];
		orgname = argRes[1];
	} else {
		orgide = "";
		orgname = "";
	}
	
	if(arg['Role']){
		var sorgidArra = arg['Role'].slice(0);
		argRes = [[], [], [], [], []];
		for (var i = 0; i < sorgidArra.length; i++) {
			argRes[0].push(sorgidArra[i].getProperty("roleid"));
			argRes[1].push(sorgidArra[i].getProperty("rolename"));
		}
		roleid = argRes[0];
		rolename = argRes[1];
	}
	
	
	  var myAjax = new Ajax("/workgroup/groupmanagerAction_addGroupEmpOrg.action");
	 	myAjax.addParam("groupid",$name("group.groupid").value);
	 	myAjax.addParam("orgids",orgid.toString());
	 	myAjax.addParam("empids",empid.toString());
	 	myAjax.addParam("roleids",roleid.toString());
	 	myAjax.submit();
	 	if(myAjax.getValue("issucc")==1){
			var frm = $name("queryForm"); 
    		frm.submit();
    		alert('操作成功');
	 	}else{
	 		alert('操作失败');
	 	}
}


</script>
</head>
<body  leftmargin="0" topmargin="0" rightmargin="0">

<%--queryform configurationID:20080821154419 --%>
<h:form name="queryForm" action="/workgroup/groupmanagerAction_queryPositionEmpViewList.action" checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
  <input type="hidden" name="_eosFlowAction" value="pageQuery"/>
  <h:hidden property="group.groupid"/>
  <w:panel id="panel1" width="100%" title="群组人员/机构查询"> <!--工作机构工查询 -->
    <table align="center" border="0" width="100%" class="form_table">
      <tr>
        <td class="form_label">  
           人员/机构代码
        </td>
        <td colspan="1">
          <h:text property="group.groupname"/>
        </td>
        <td class="form_label">  
          人员/机构名称
        </td>
        <td colspan="1">
          <h:text property="group.groupdesc"/>
        </td>
      </tr>
      <tr class="form_bottom">
        <td colspan="4" class="form_bottom">
          <b:message key="l_pageDisplay" /> <!--每页显示 -->
          <h:text size="2" property="page.length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
          <input type="hidden" name="page.begin" value="0">
          <input type="hidden" name="page.isCount" value="true">
          <input type="submit" value="<b:message key='l_query' />">&nbsp;
        </td>
      </tr>
    </table>
  </w:panel>
</h:form>
 

<%--viewlist configurationID:20080821114714 --%>
<h:form name="viewlist1" action="/workgroup/groupmanagerAction_queryPositionEmpViewList.action" checkType="blur" target="_self" method="post">
  <input type="hidden" name="_eosFlowAction" value="pageQuery" >
  <!-- 隐藏匿分页及查询信息，使用“page_form”提交时可会自动提交查询信息变量 -->
  <%--<h:hiddendata property="page"  />--%>
  <h:hidden property="page.begin"/>
  <h:hidden property="page.length"/>
  <h:hidden property="page.count"/>
  <h:hidden property="page.isCount"/>
  <h:hidden property="group.empid"/>
  <h:hidden property="group.groupid"/>
  <h:hidden property="group.groupname"/>
  <h:hidden property="group.groupdesc"/>
  
  <h:hiddendata property="criteria"/>
    <table align="center" border="0" width="100%" class="EOS_table">
         <tr>
	       <td colspan="4" class="eos-panel-title">&nbsp;群组人员/机构列表</td>
	      </tr>
         
         <tr>
            <th align="center">
            <l:greaterThan property="page.size" targetValue="0" compareType="number">
               <input type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll();"> 
            </l:greaterThan>
              <b:message key="l_select"></b:message>
            </th>
            <th align="center">
            	人员/机构代码
            </th>
            <th align="center">
            	人员/机构名称
            </th>
            <th align="center">
            	类型	
            </th>
          </tr>
      <w:checkGroup id="group1">
        <l:iterate property="groupOrgs" id="id1">
          <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />">
            <td align="center">
              <w:rowCheckbox afterSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))" afterUnSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))">
                <h:param iterateId='id1' property='code' indexed='true' />
                <h:param iterateId='id1' property='eotype' indexed='true' />
                <h:param iterateId='id1' property='eoid' indexed='true' />
              </w:rowCheckbox>
            </td>
                <td>
                  <b:write iterateId="id1" property="code"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="name"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="eotypename" />
                </td>
          </tr>
        </l:iterate>
      </w:checkGroup>
      <tr>
        <td colspan="4" class="command_sort_area">
        <div class="h3">
          <input type="button" value="<b:message key = 'l_add'/>" onclick="addRecord();"> <!--新增 -->
          <l:greaterThan property="page.size" targetValue="0" compareType="number"  >
            <input type="button" value="<b:message key = 'l_delete'/>" onclick="delRecord();" id="deleteButton"> <!--删除 -->
          </l:greaterThan>
          </div>
          <div class="h4">
            <l:equal property="page.isCount" targetValue="true">
              <b:message key = 'l_total'/>       <!--共 -->
              <b:write property="page.count"/>
              <b:message key = 'l_recordNO.'/>   <!--条记录 第 -->
              <b:write property="page.currentPage"/>
              <b:message key = 'l_page'/>/       <!--页 -->
              <b:write property="page.totalPage"/>
              <b:message key = 'l_page'/>        <!--页 -->
            </l:equal>
            <l:equal property="page.isCount" targetValue="false">
              <b:message key = 'l_NO.'/>         <!--第 -->
              <b:write property="page.currentPage"/>
              <b:message key = 'l_page'/>        <!--页 -->
            </l:equal>
            <!--首页 上页 下页 尾页 -->
            <input type="button" onclick="firstPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_firstPage'/>"  <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
            <input type="button" onclick="prevPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_upPage'/>" <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
            <input type="button" onclick="nextPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_nextPage'/>" <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
            <l:equal property="page.isCount" targetValue="true">
              <input type="button" onclick="lastPage('page', 'pageQuery', null, null, 'viewlist1');" value="<b:message key = 'l_lastPage'/>" <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
            </l:equal>
         </div>
        </td>
      </tr>
    </table>
</h:form>


</body>
</html>
<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
</script>