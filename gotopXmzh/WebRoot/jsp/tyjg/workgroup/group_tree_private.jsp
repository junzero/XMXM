<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<%@page import="commonj.sdo.DataObject" %>
<html>
<head>
<%
	String contextPath = request.getContextPath();

	//获取标签中使用的国际化资源信息
	String title=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_title_group_tree");
	String groupManage=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_title_group_manager");
	String addGroup=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_addGroup");
	String addEmp=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_addEmp");
	String refresh=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_refresh");
	String deleteGroup=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_deleteGroup");
	String deletePosition=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("workgroupManager_l_deletePosition");
	
	
	
	
//	System.out.println("---userid--"+userid);
//	System.out.println("---obj[0]--"+obj[0]);

%>
<title><%=title%></title>  <!--工作组树 -->
<script>
	//被选中节点
	var selectedNode;
	
	//刷新
	function refreshSubnode(node){
		node.reloadChild();
	}
	
	//双击树节点，重载树
	function dbClickNode( node ) {
	    if(node.isExpanded())
		{
			//关闭当前节点
	    	node.collapseNode();
	    }else{
	        //展开当前节点的下级子节点
	    	node.expandNode();
	    }
	}
	
	//刷新人员节点根据不同性别区分图片
	function empListRefresh(node){
		if(node.getProperty("gender") == "1")
		{
			node.setIcon("/common/images/icons/user.gif");
		}else{
			node.setIcon("/common/images/icons/user_female.gif");
		} 
	}
	function orgListRefresh(node){
		node.setText(node.getText()+"（机构）");
	}
	function roleListRefresh(node){
		node.setText(node.getText()+"（角色）");
	}
	function groupListRefresh(node){
		if(node.getProperty("groupstatus")!='run'){
			node.setText(node.getText()+"（关闭状态）");
		}
	}
	
	//功能：动态树移动时弹出模态窗口，用户选择是执行“移动”还是“复制”操作
	//实现：在beforeMove方法弹出模态窗口，提供用户选择并将选择结果返回；但由于模态窗口的实现是异步的，
	//如果在beforeMove方法中返回true，在用户选择前将立即执行默认移动操作,因而需要返回false，先停止默认移动操作
	//然后在模态窗口的回调函数setOperation方法中设置用户选择操作，然后继续执行重写的move方法
	
	//复制或移动标识
	var moveOrCopy="move";
	
	//移动操作赋初始值
	function getInitParam()
	{
	    var str = "<moveOrCopy>"+moveOrCopy+"</moveOrCopy>";
		return str;
	}
	
	var m,mt  //用于记录用户操作的移动节点及其目标节点
	//设置用户选择操作标识，并重新启动移动操作
	function setOperation(returnValue) {
		if(returnValue == "cancel"){
			return;
		}
		moveOrCopy=returnValue;
		move(m,mt)	
	}
	
	//树的移动操作覆盖方法
	function move(move_node,moveto_node)
	{
		var tree=move_node.getTree();
		var moveAction = tree.model.getMoveAction( move_node, moveto_node );
		if( moveAction )
		{//可以移动,执行设置的动作
		    var beforeMove=move_node.getTree().beforeMove;
		    //if(beforeMove) if( beforeMove(move_node,moveto_node)==false) return; //执行拖动前的自定义方法
		    var moveParam = tree.model.getMoveParam( move_node, moveto_node );
		    var hs=new HideSubmit(moveAction)
		      hs.submitXML(moveParam);	  
		    var isReloadMoveNodeParent;
		    var isReloadMoveToNodeParent; 
		    isReloadMoveNodeParent=!move_node.getParent().isChildOf(moveto_node);  
		    isReloadMoveToNodeParent=!moveto_node.isChildOf(move_node.getParent());   
		    var afterMove=tree.afterMove;   
		    if(isReloadMoveNodeParent)move_node.getParent().reloadChild();
		    if(isReloadMoveToNodeParent) moveto_node.reloadChild();   
		    if(afterMove) tree.afterMove();
	    }
	}
	//end对树的操作方法
	
	
	//显示工作组查询页面
	function queryGroup(node) {
		var params = "&defaultTab=queryGroup";
		parent.window.frames["groupTabs"].location.href="org.gocom.abframe.org.workgroup.priworkgroupManager.flow?_eosFlowAction=toTab"+params;
	}
	//显示GroupTabs页面
	function displayGroupTabs(node) {
		selectedNode = node;
		var params = "&defaultTab=modifySelfGroup&nodeType=group&group/groupid="+node.getProperty("groupid");
		params += "&group/empid="+node.getProperty("empid");
		parent.window.frames["groupTabs"].location.href="org.gocom.abframe.org.workgroup.WorkgroupManager.flow?_eosFlowAction=toTab"+params;
	}
	
	//显示岗位tab页面
	function displayPositionTabs(node) {
		selectedNode = node;
		var params = "&defaultTab=basepositioninfo&nodeType=position&position/positionid="+node.getProperty("positionid");
		parent.window.frames["groupTabs"].location.href="org.gocom.abframe.org.workgroup.WorkgroupManager.flow?_eosFlowAction=toTab"+params;
	}
	
	//增加工作组
	function addGroup(node){
		selectedNode = node;
		
		var title;
	    var url = "org.gocom.abframe.org.workgroup.GroupMain.flow";
	    url += "?_eosFlowAction=addGroup";
		var gtype = node.entity.name;
		var dicttype = "";
	    if(gtype=='grouptype'){
			dicttype = node.entity.getProperty("dictID");
		    url += "&dictID="+node.entity.getProperty("dictID");
	    }else{
	    	dicttype = node.entity.getProperty("grouptype");
	    	url += "&dictID="+node.entity.getProperty("grouptype");
	    }
	    if(dicttype == 'public'){
	    	alert('操作失败！您没有权限增加公共群组');
	    	return;
	    }
	    if(gtype=='grouptype'){
	    	title="<b:message key='workgroupManager_l_addGroup' />";	     	
	    }else{
	    	title="<b:message key='workgroupManager_l_addSubGroup' />";
	     	url += "&parentGroupid=";
	     	url += node.getProperty("groupid");
	    }
	     url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	     showModalCenter(url, "", refreshSelectedNode, 610, 200, "新增群组");  <!--增加子工作组 -->
	}
	
	//增加岗位
	function addPosition(node){
		selectedNode = node;
	     var url = "org.gocom.abframe.org.workgroup.GroupPositionMaintain.flow";
	     url += "?_eosFlowAction=insert";
	     url += "&parentGroupid=";
	     url += node.getProperty("groupid");
	     url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	     showModalCenter(url, "", refreshSelectedNode, 600, 200, "增加群组岗位");    <!--增加工作组岗位 -->  
	}
	
	//增加员工
	function addEmp(node){
		selectedNode = node;  
		if(node.getProperty("empid")!=$name("userIntegrated/empid").value){
			alert('操作失败！只有创建人才有删除权限');
			return;
		}
	    var url = "org.gocom.abframe.org.workgroup.GroupEmpMaintain.flow";
	    url += "?_eosFlowAction=addEmp";
	    url += "&groupID=";
	    url += node.getProperty("groupid");
	    url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	    showModalCenter(url, "", refreshSelectedNode, 550, 450, "增加群组员工");      <!--增加工作组员工 -->  
	}
	
	//刷新选中节点
	function refreshSelectedNode(returnValue) {
	 	selectedNode.reloadChild();
	 	
	}
	
	//增加一级工作组
	function addTopGroup(node) {
		 selectedNode=node;
	     var url = "org.gocom.abframe.org.workgroup.GroupAdd.flow";
	     url += '?_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	     showModalCenter(url, "", refreshSelectedNode, 470, 230, "新增群组");  <!--新增工作组 -->
	}
	
	//删除工作组
	function deleteGroup(node){
		if(node.getProperty("empid")!=$name("userIntegrated/empid").value){
			alert('操作失败！只有创建人才有删除权限');
			return;
		}
		var parentNode=node.getParent();
		var nodeName=node.getProperty("groupname");
		if(confirm("您确认要删除选中的群组？注意：删除群组将删除该群组下的所有子群组、岗位及人员")){ 
	//	<!--#您确认要删除选中的工作组？注意：删除工作组将删除该工作组下的所有子工作组、岗位及人员 -->
			var myAjax = new Ajax("org.gocom.abframe.org.workgroup.WorkgroupManager.deleteGroups.biz");  
			myAjax.addParam("groups[1]/groupid", node.getProperty("groupid"));
			myAjax.submit();
			var returnNode =myAjax.getResponseXMLDom()
	
	        if( returnNode ) {
	            if( (myAjax.getValue("root/data/oprResult") == 1)||(myAjax.getValue("root/data/retCode") == 1 ))
	                alert( '<b:message key="l_m_delete_success"/>' ); // <!--  删除成功 -->
	            else {
	                alert( '<b:message key="l_m_delete_fail"/>' ); //    <!-- 删除失败 -->   
	            }
	        } else {
	            alert( '<b:message key="l_m_delete_fail"/>' );   //  <!-- 删除失败 -->  
	        }		
			parentNode.reloadChild();	
		}
	}
	
	//增加岗位员工
	function addPositionEmp(node){
		selectedNode = node;      
	
	    var url = "org.gocom.abframe.org.position.PositionEmp.flow";	    
	    url += "?_eosFlowAction=pageQuery";
	    url += "&parentPosID=";
	    url += node.getProperty("positionid");;
	    url += "&_ts="+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
	    showModalCenter(url, "", refreshSelectedNode, 550, 450, "<b:message key='workgroupManager_l_addPositionEmp' />");   <!--增加岗位员工 -->     
	}
	
	//删除岗位
	function deletePosition(node){
		var parentNode=node.getParent();
		var nodeName=node.getProperty("groupname");
		if(confirm("<b:message key = 'workgroupManager_m_delete_position_confirm'/>\n<b:message key = 'workgroupManager_m_delete_position_note'/>")){  
	//	<!--您确认要删除选中的工作组岗位？注意：删除工作组岗位将删除该岗位下的所有人员 -->
			var myAjax = new Ajax("org.gocom.abframe.org.PositionManager.deletePositions.biz");
			myAjax.addParam("pos[1]/positionid", node.getProperty("positionid"));
			myAjax.submit();
			var returnNode =myAjax.getResponseXMLDom()
	
	        if( returnNode ) {
	            if( (myAjax.getValue("root/data/oprResult") == 1)||(myAjax.getValue("root/data/retCode") == 1 ))
	                alert( '<b:message key="l_m_delete_success"/>' );//  <!--  删除成功 -->
	            else {
	                alert( '<b:message key="l_m_delete_fail"/>' );  //   <!-- 删除失败 -->    
	            }
	        } else {
	            alert( '<b:message key="l_m_delete_fail"/>' );    // <!-- 删除失败 -->  
	        }		
			parentNode.reloadChild();	
		}
	}
   /* 初始化树移动方法，因为调用了$id('groupTree')
   *
   */
   function initTreeBeforeMove() {
       //让用户选择是复制还是移动节点
		$id('groupTree').beforeMove=function(node,toNode)
		{
			m=node;
			mt=toNode;
			//传递给模态窗口，如果是工作组，则不显示复制操作
			var isGroup = false;
			if (node.getProperty("groupid")){
				isGroup = true;
			}
			//让用户选择是复制还是移动节点,并调用setOperation方法重启移动操作																  <!--请选择操作 -->
			showModalCenter("<%=contextPath %>/org/workgroup/select_dialog.jsp?_ts="+(new Date()).getTime(),isGroup,setOperation,180,120,"<b:message key='workgroupManager_l_selectOperation'/>");
			//返回false，先取消移动操作
			return false;
		}
   }
    /* load初始化函数
    *
    */   
    function custInit(){  
    	//树高度调整
        var height = document.body.clientHeight - TREE_MODIFY_HEIGHT;
		$id("groupTree").style.height =height;
		
		initTreeBeforeMove();
	}
</script>
</head>
<body leftmargin="0" topmargin="0"><h:hidden property="userIntegrated/empid" scope="session" />
<table align="left" border="0" height="100%" width="100%" cellpadding="0" cellspacing="0">
<tr >
	<td height="22px;" class="eos-panel-title">&nbsp;<%--<%=groupManage%>--%>群组管理</td>
</tr>
<tr>
<td class="eos-panel-body">
<r:rtree id="groupTree" width="100%">
  <r:treeRoot action="org.gocom.abframe.org.workgroup.WorkgroupManager.queryRootGroupType.biz" onDblclickFunc="dbClickNode"
  	childEntities="grouptype" display = "群组树" icon="/common/images/icons/application_home.png" onClickFunc="queryGroup">  <!-- -->
    <r:treeMenu>
	  <r:treeMenuItem display="<%=refresh%>" onClickFunc="refreshSubnode"/>  <!--刷新 -->
    </r:treeMenu>
  </r:treeRoot> 
  
  <r:treeNode action="org.gocom.abframe.org.workgroup.WorkgroupManager.queryRootGroupPrivat.biz" childEntities="groupList"
  		nodeType="grouptype" showField="dictName"
  		icon="/common/images/icons/application.gif" submitXpath="dtype">
    <r:treeMenu>
  	  <r:treeMenuItem display="新增群组" onClickFunc="addGroup"/>  <!--新增工作组 -->
	  <r:treeMenuItem display="<%=refresh%>" onClickFunc="refreshSubnode"/>  <!--刷新 -->
    </r:treeMenu>
  </r:treeNode>
   	
  <r:treeNode action="org.gocom.abframe.org.workgroup.WorkgroupManager.queryGroupPositionEmpByParent.biz" onDblclickFunc="dbClickNode"
  	childEntities="empList;orgList;roleList" nodeType="groupList" onClickFunc="displayGroupTabs" 
  	onRefreshFunc="groupListRefresh" showField="groupname" icon="/common/images/icons/chart_organisation.gif" submitXpath="group">
  	<r:treeMenu>  
<%--  	  <r:treeMenuItem display="<%=addGroup%>" onClickFunc="addGroup"/>  <!--新增工作组 -->--%>
      <r:treeMenuItem display="<%=addEmp%>" onClickFunc="addEmp"/>  <!--新增员工 -->
      <r:treeMenuItem display="删除群组" onClickFunc="deleteGroup"/>  <!--删除工作组 -->
	  <r:treeMenuItem display="<%=refresh%>" onClickFunc="refreshSubnode"/>  <!--刷新 -->
    </r:treeMenu>
    <r:treeMove toNode="groupList" initParamFunc="getInitParam" action="org.gocom.abframe.org.workgroup.WorkgroupManager.moveOrCopyGroupToGroup.biz"/>
  </r:treeNode>
    
  <r:treeNode action="org.gocom.abframe.org.workgroup.WorkgroupManager.queryEmpByPosition.biz" onDblclickFunc="dbClickNode"
  	childEntities="empOfPositionList" nodeType="positionList" onClickFunc="displayPositionTabs" 
  	onRefreshFunc="" showField="posiname" icon="/common/images/icons/group.png" submitXpath="position">
  	<r:treeMenu>  
      <r:treeMenuItem display="<%=addEmp%>" onClickFunc="addPositionEmp"/>  <!--新增员工 -->
      <r:treeMenuItem display="<%=deletePosition%>" onClickFunc="deletePosition"/>  <!--删除岗位 -->
	  <r:treeMenuItem display="<%=refresh%>" onClickFunc="refreshSubnode"/>  <!--刷新 -->
    </r:treeMenu>
    <r:treeMove toNode="groupList" initParamFunc="getInitParam" action="org.gocom.abframe.org.workgroup.WorkgroupManager.moveOrCopyPositionToGroup.biz"/>
  </r:treeNode>  
  
  <r:treeNode nodeType="empList" onRefreshFunc="empListRefresh" showField="empname" icon="/common/images/icons/user.gif" submitXpath="emp">
    <r:treeMove toNode="groupList" initParamFunc="getInitParam" action="org.gocom.abframe.org.workgroup.WorkgroupManager.moveOrCopyEmpFromGroupToGroup.biz"/>
  </r:treeNode>  
  
  <r:treeNode nodeType="orgList" showField="orgname" icon="/common/images/icons/building_link.png" onRefreshFunc="orgListRefresh" submitXpath="org">
    <r:treeMove toNode="groupList" initParamFunc="getInitParam" action="org.gocom.abframe.org.workgroup.WorkgroupManager.moveOrCopyEmpFromGroupToGroup.biz"/>
  </r:treeNode>  
  
  <r:treeNode nodeType="roleList" showField="rolename" icon="/common/images/icons/medal_bronze_1.png" onRefreshFunc="roleListRefresh" submitXpath="org">
    <r:treeMove toNode="groupList" initParamFunc="getInitParam" action="org.gocom.abframe.org.workgroup.WorkgroupManager.moveOrCopyEmpFromGroupToGroup.biz"/>
  </r:treeNode>  
  
  <r:treeNode  
  	childEntities="" nodeType="empOfPositionList" onClickFunc="" 
  	onRefreshFunc="empListRefresh" showField="empname" icon="/common/images/icons/user.gif" submitXpath="emp">
    <r:treeMove toNode="groupList" initParamFunc="getInitParam" action="org.gocom.abframe.org.workgroup.WorkgroupManager.moveOrCopyEmpFromPositionToGroup.biz"/>
    <r:treeMove toNode="positionList" initParamFunc="getInitParam" action="org.gocom.abframe.org.workgroup.WorkgroupManager.moveOrCopyEmpFromPositionToPosition.biz"/>
  </r:treeNode>  
</r:rtree>
</td>
</tr>
</table>

</body>
</html>
<SCRIPT language="javascript">
    //初始化树的高度
    eventManager.add(window,"load",custInit);
	
</SCRIPT>

<SCRIPT language="javascript">
	function _rtreemodel_loadNodeChild(D, M) {
		var C;
		if (D.isloadData !== true) {
			var A = this.getNodeExpandAction(D), O = this.getExpandParam(D), I = this
					.getExpandRetXpaths(D);
			if (!(A && I)) {
				D.icon.src = D.openIconSrc;
				D.loadFinished = true;
				return
			}
			var K = new HideSubmit(A);
			K.submitXML(O);
			var B = K.getXMLDom();
			C = Dataset.create(B, "root/data/" + I[0]);
			for (var G = 1; G < I.length; G++)
				C.appendDataset(Dataset.create(B, "root/data/" + I[G]))
		} else
			C = D.datasetExp;
		var Q = C.values;
		
		if (!D.isroot) {
			if (Q.length == 0) {
				D.hasChild = false;
				D.isleaf = true;
				D.refreshExpendIcon();
				D.icon.src = D.openIconSrc;
				D.childrenContainer.style.display = "none";
				D.loadFinished = true;
				if (M)
					M(D);
				return
			} else {
				D.hasChild = true;
				D.isleaf = false;
				D.refreshExpendIcon()
			}
		} else if (Q.length != 0) {
			D.hasChild = true;
			D.isleaf = false
		}
		var F, E, R, L, J, H, N, P;
		for (G = 0; G < Q.length; G++) {
			if (G == 0)
				J = true;
			else
				J = false;
			if (G == Q.length - 1)
				H = true;
			else
				H = false;
			E = Q[G], R = this.getTreeNodeName(E), L = this.getTreeNodeIcon(E.name), P = this
					.getEntityInfo(E.name).iconType;
			if (!(this.getEntityInfo(E.name)).expandAction)
				N = false;
			else
				N = true;

			
			var tempObj = true;
			
			if(window._popedomExpand){
				tempObj = window._popedomExpand(D, R, L, E, J, H, N, this.hasRoot, P);
			}
			if(tempObj){
				F = new RTreeNode(D, R, L, E, J, H, N, this.hasRoot, P);
				D.childrenContainer.appendChild(F);
				if (N && this.getEntityInfo(E.name).preload == true)
					if (this.preLoad(F) == false)
						F.setLeaf();
				F.refresh()
			}
		}
		D.childrenContainer.style.display = "";
		D.icon.src = D.openIconSrc;
		if (M)
			M(D);
		D.loadFinished = true
	}
	var roletype = '';
	/**
	* entity = F;
	**/
	function _popedomExpand(D, R, L, E, J, H, N, hasRoot, P){
		if(roletype<3){
			return true;
		}
		var empid = E.getProperty("empid");
		if(empid != undefined){
			alert(empid);
		}
		//roletype
		return true;
	}
</SCRIPT>


