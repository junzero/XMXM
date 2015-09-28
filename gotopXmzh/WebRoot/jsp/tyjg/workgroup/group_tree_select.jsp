<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
<title></title>
<%
	//工作组树
	String groupTree = com.eos.foundation.eoscommon.ResourcesMessageUtil
			.getI18nResourceMessage("workgroupManager_l_title_group_tree");
%>
<script>
	
    var groupid = ""; //工作组编号
    var groupname = ""; //工作组名称
    
    
	/*
     * 单击树节点，给选择框赋值
     */
    function clickNode( node ) {
        setReturnValue( node );
    }
    
    /*
     * 赋值函数
     */
    function setReturnValue( node ) {
        groupid = node.getProperty("groupid");
        groupname   = node.getProperty("groupname");
        
        $name("groupid").value = groupid;
        $name("groupname").value = groupname;
    }
    
    /*
     * 给弹出窗口赋返回值，并关闭窗口,返回值为：工作组编号，工作组名称
     */
    function closeWin( idValue, nameValue) {
        returnValue = [ idValue, nameValue];
        window.closeD();
    }
    /*
     * 确定按钮，如果没选择值，提示是否关闭，是退出，否取消
     */
    function ok() {
        if( groupid == "" || groupid == null || groupname == "" || groupname == null ) {
            if( confirm('<l_m_alert_notSelectValueExit"/>') ) {
                closeWin( groupid, groupname);    
            }
        } else {
            closeWin( groupid, groupname);    
        }
    }
	
	/*
     *  双击树节点，相当于单击并返回值
     */
    function dbClickNode( node ) {
        if( groupid == "" || groupid == null || groupname == "" || groupname == null ) {
            if( confirm('<l_m_alert_notSelectValueExit"/>') ) {
                closeWin( groupid, groupname);    
            }
        } else {
            closeWin( groupid, groupname);    
        }
    }
    	
	 /*自定义初始化按钮样式
      *
      */
	 function custInit(){  
		initButtonStyle();
	 }
    
</script>
</head>
<body>
<TABLE border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
	<TR>
		<TD width="346" height="370" valign="top" class="eos-panel-table">
		<r:rtree id="groupTree" width="346" height="370">
			<r:treeRoot
				action="org.gocom.abframe.org.workgroup.WorkgroupManager.queryRootGroup.biz"
				childEntities="groupList" display="<%=groupTree %>"
				icon="/common/images/icons/brickss.png">
			</r:treeRoot>

			<r:treeNode
				action="org.gocom.abframe.org.workgroup.WorkgroupManager.queryGroupPositionEmpByParent.biz"
				onDblclickFunc="dbClickNode" preload="true" 
				childEntities="groupList;positionList;empList" nodeType="groupList"
				onClickFunc="clickNode" showField="groupname"
				icon="/common/images/icons/group.png" submitXpath="group">
			</r:treeNode>

			<r:treeNode
				action="org.gocom.abframe.org.workgroup.WorkgroupManager.queryEmpByPosition.biz"
				onDblclickFunc="dbClickNode" childEntities="empOfPositionList"
				nodeType="positionList" showField="posiname" preload="true" preload="true" 
				icon="/common/images/icons/position.png" submitXpath="position">
			</r:treeNode>

			<r:treeNode childEntities="" nodeType="empList" showField="empname" preload="true" 
				icon="/common/images/icons/emp.png" submitXpath="emp"> 
			</r:treeNode>

			<r:treeNode childEntities="" nodeType="empOfPositionList" preload="true" 
				showField="empname" icon="/common/images/icons/emp.png"
				submitXpath="emp">
			</r:treeNode>
		</r:rtree></TD>
	</TR>

	<TR>
	    <TD>
		   <b:message key="l_selectValueIs"></b:message><input type="text" name="groupname" readonly="readonly" value=""/> 
		   <input type="hidden" name="groupid" value=""/>
		   <input type="button" value='<b:message key="l_cofirm"/>' onclick="javascript:ok();"/>
		   <input type="button" value='<b:message key="l_empty"/>' onclick="javascript:closeWin('','');"/>
		   <input type="button" value='<b:message key="l_close"/>' onclick="javascript:window.closeD();"/>
		</TD>
	</TR>
</TABLE>

</body>
</html>
<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
</script>