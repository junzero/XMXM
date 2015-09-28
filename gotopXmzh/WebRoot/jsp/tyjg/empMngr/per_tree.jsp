<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<%@page import="com.gotop.vo.system.MUOUserSession"%>
<%@page import="com.gotop.util.Global"%>
<%
	
	//获取标签中使用的国际化资源信息
	String title = com.eos.foundation.eoscommon.ResourcesMessageUtil
			.getI18nResourceMessage("orgSubMaintain_l_title_orgTree");
	String orgEmpTree = com.eos.foundation.eoscommon.ResourcesMessageUtil
			.getI18nResourceMessage("orgSubMaintain_l_title_orgEmpTree");
	String addSubPosi = com.eos.foundation.eoscommon.ResourcesMessageUtil
			.getI18nResourceMessage("orgSubMaintain_l_menu_addSubPosi");
	String addEmpOprOrg = com.eos.foundation.eoscommon.ResourcesMessageUtil
			.getI18nResourceMessage("orgSubMaintain_l_menu_addEmpOprOrg");
	String addEmpOprPosi = com.eos.foundation.eoscommon.ResourcesMessageUtil
			.getI18nResourceMessage("orgSubMaintain_l_menu_addEmpOprPosi");
	String refresh=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_refresh");

	String removeEmpOpr = com.eos.foundation.eoscommon.ResourcesMessageUtil
			.getI18nResourceMessage("orgSubMaintain_l_menu_removeEmpOpr");
			
	String select_dialogJspStr = "/jsp/tyjg/empMngr/select_dialog.jsp";
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=title%></title>
<h:css href="/css/style1/style-custom.css"/>
</head>
<%
	MUOUserSession seObj = (MUOUserSession)request.getSession().getAttribute(Global.LOGON_USER_KEY);
	Long empid =seObj.getEmpid();
	String empcode = seObj.getEmpcode();
%>

<script type="text/javascript">
	
	function getInitParam(){
	    var ret = '<oEmp>'
	            + '<orgId><%=seObj.getOrgid()%></orgId>'
	            + '<userid><%=seObj.getEmpid()%></userid>'
	            + '<operatorid><%=seObj.getEmpid()%></operatorid>'
	            + '<empid><%=seObj.getEmpid()%></empid>'
	            + '</oEmp>'; 
		return ret ;
	}	
</script>
<body leftmargin="0" topmargin="0" rightmargin="0">
<table border="0" width="99%" height="98%" cellpadding="0"
	cellspacing="0">
	
	<tr>       
		<td width="25%" height="100%" valign="top">
			<table border="0" width="98%" height="98%" cellpadding="0" cellspacing="0">
				<tr height="4%">
					<td class="eos-panel-title">&nbsp;<%=orgEmpTree%></td>
				</tr>
				<tr>
				<td width="25%" height="100%" valign="top" class="eos-panel-body" >
				<r:rtree id="orgTree"  width="100%">
					<r:treeRoot childEntities="Tomorganization" display="机构树"
						initParamFunc="getInitParam"
						icon="/common/images/icons/arrow_merge.png"
						action="/empMngr/queryOrgNodes_empMngr.action">
						<r:treeMenu>
							<r:treeMenuItem display="<%=refresh%>" onClickFunc="refreshSubnode"/>  <!--刷新 -->
						</r:treeMenu>
					</r:treeRoot>
					<r:treeNode nodeType="Tomorganization" showField="orgName"
						childEntities="Tomorganization;Omemployee"
						icon="/common/images/icons/chart_organisation.gif"
						submitXpath="oParentOrg" onRefreshFunc=""
						onClickFunc="modifySelfOrg"
						action="/empMngr/queryOrgAndEmp_empMngr.action">
						<r:treeMenu>
							<r:treeMenuItem display="<%=addEmpOprOrg%>" onClickFunc="addEmpOprOrg" />
							<r:treeMenuItem display="<%=refresh%>" onClickFunc="refreshSubnode"/>  <!--刷新 -->
						</r:treeMenu>
					</r:treeNode>
					<r:treeNode nodeType="Omemployee" showField="empName"
						icon="/common/images/icons/user.gif" submitXpath="oEmp"
						onClickFunc="modifyEmpOpr" onRefreshFunc="empListRefresh">
						<r:treeMove toNode="Tomorganization" initParamFunc="getMoveInitParam"
							action="/empMngr/moveEmpNode_empMngr.action" />
						<r:treeMenu>
						<r:treeMenuItem display="设置为主机构"
							onClickFunc="setEmpOprIsMain" />
					</r:treeMenu>
				</r:treeNode>
			</r:rtree></td></tr></table>
		<td width="75%" height="100%" valign="top"><iframe id="tab"
			style="width:100%;height:100%" frameBorder="0" scrolling="no">
		</iframe></td>
	</tr>
</table>
</body>
</html>
<script>
	
	
	
	var mngorgs = new Array();
 <l:iterate id="mng" property="orgList" >
       mngorgs.push('<b:write property="ORGSEQ" iterateId="mng"/>');
    </l:iterate>
    //判断用户是否有权操作机构
    function checkAuth(orgseq){
    	var operatorid = <%=seObj.getEmpid()%>;
    	if(operatorid==1){
    		return true;
    	}
      for(var i=0;i<mngorgs.length;i++){
          if(orgseq.test('^'+mngorgs[i])){
             return true;
          }
      }
      alert('<b:message key="mngorg_no_permission" />');
      return false;
    }
    function checkAuthInclude(node){
    if(!node.isRootNode()){      
     var orgseq = node.getProperty("orgSeq");
     if(orgseq!=null &&orgseq!="" && orgseq!=undefined){//如果是机构
        for(var i=0;i<mngorgs.length;i++){
          if(orgseq.test('^'+mngorgs[i])){
             return true;
          }
        }       
       alert('<b:message key="mngorg_no_permission" />');
       return false;   
     }else{//不是机构判断父节点是否是
        return checkAuthInclude(node.getParent());
     }   
     }else{
      return true;
     }  
    }
    //
    function treeAuthCheck(node){
      if(!node.isRootNode()){   
	      var orgseq = node.getProperty("orgSeq");
	      if(orgseq!=null &&orgseq!=""&&orgseq!=undefined){	        
	        return checkAuth(orgseq);      
	      }else{	        
	        var pnode =node.getParent();
	        return checkAuth(pnode.getProperty("orgSeq"));
//	        return checkAuthInclude(node.getParent());
	      }
      }else{
           return true;
      }
    }
	//刷新人员节点根据不同性别区分图片
	function empListRefresh(node)
	{
		if(node.getProperty("gender") == "1")
		{
			node.setIcon("/common/images/icons/user.gif");
		}else{
			node.setIcon("/common/images/icons/user_female.gif");
		} 
		node.title=node.getEntity().getProperty("userid");
	}


	 var isMoveOrCopy = "move";
	 var m;
	 var mt;
	 
	 //刷新
	function refreshSubnode(node){
		node.reloadChild();
	}
	  
	  /* 
     *  重写树移动的接口
     *  param0: 待移动的树节点
     *  param1: 移动的目标节点
     */
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
	 
	 function addEmporgByEmp(move_node,moveto_node){
	    var tree=move_node.getTree();
	    var moveAction = tree.model.getMoveAction( move_node, moveto_node );
	    if( moveAction )
	    {//可以移动,执行设置的动作
	       var beforeMove=move_node.getTree().beforeMove;
	  
	       //if(beforeMove) if( beforeMove(move_node,moveto_node)==false) return; //执行拖动前的自定义方法
	 
	       var moveParam = tree.model.getMoveParam( move_node, moveto_node );
	       var hs=new HideSubmit(moveAction)
//	       hs.submitXML(moveParam);
	    }
	 }
	     
	 /*  
      *  初始化树移动前的方法
      */
     function initTreeBeforeMove() {
         $id("orgTree").beforeMove = function(movenode, toNode) {
         	 if(treeAuthCheck(movenode) && treeAuthCheck(toNode)){
			     var url = "<%=select_dialogJspStr %>";
			     url += '?_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
			     showModalCenter(url, "", nodeMoveCallBack, 280, 120, '<b:message key="l_title_select_oper"/>'); 
			     m = movenode;
	             mt = toNode;
		     }
		     return false;
	    }
     }
     
     /*
     *  移动树节点的回调函数
     *  param0: arg lookup返回的值
     */
    function nodeMoveCallBack( arg ) {
        //如果选择取消，则退出
        if( arg == "cancel" ) 
            return ;
        isMoveOrCopy = arg;
	    move(m, mt)
    }
    
    /*  
     *  树移动的初始参数
     */
    function getMoveInitParam()
	{
	    var str = "<isMoveOrCopy>" + isMoveOrCopy + "</isMoveOrCopy>";
		return str;
	}
 
 
	//  点击机构节点查询机构信息
	function modifySelfOrg(node){
		var orgSeq = node.getProperty("orgSeq");
		if(treeAuthCheck(node)){
			var orgId=node.getProperty("orgId");
			// 右侧显示机构人员信息
			$id("tab").src="/jsp/tyjg/empMngr/per_tab.jsp?orgId="+orgId+"&dateTime="+new Date();
		}
	}
	

	//  点击员工节点查询员工信息
	function modifyEmpOpr(node){
		if(treeAuthCheck(node)){
			var empid =node.getProperty("empid");
			var param= "&sNodetype=emp&empid="+empid;
			var orgId = node.getParent().getProperty("orgId");
			if(orgId){
				param += "&orgId=" + orgId;
			}
			var jgsx = node.getParent().getProperty("jgsx");
			if(jgsx){
				param += "&jgsx=" + jgsx;
			}
			var orgDegree = node.getParent().getProperty("orgDegree");
			if(orgDegree){
				param += "&orgDegree=" + orgDegree;
			}
			var userid = node.getProperty("userid");
			if(userid){
				param += "&userid=" + userid;
			}
			try{
				$id("tab").src="/jsp/tyjg/empMngr/emp_info.jsp?param="+param+"&dtime="+new Date().getTime();
			}catch(e){
			 alert("modifyEmpOpr error!"); 
			}
		}
	}
	
	
	function addEmpOprOrg(node){
	    var orgid =node.getProperty("orgId");
	    if(treeAuthCheck(node)){
	       var sUrl="/jsp/tyjg/empMngr/emp_baseinfo.jsp?execType=insert";
	       sUrl+='&orgid='+orgid;
	       sUrl+='&orgDegree='+node.getProperty("orgDegree");
	       sUrl+='&jgsx='+node.getProperty("jgsx");
//	       sUrl+='&add=1';
           sUrl+='&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数,并且作为判断是否弹出窗口的标志
    		showModalCenter(sUrl,window,callBack,700,500,"<b:message key='orgSubMaintain_l_menu_addEmpOprOrg'/>");
    	}
    }
	
	
	function maintainPowerEmp2(node){
	
	}
	
	function removeEmpOpr(node){
	
	}
	
	

    
    
       

	
    //回调函数，用于弹出窗口修改过数据后的树的更新
	function callBack(returnValue){
	   //if(returnValue){
  	     $id("orgTree").getSelectNode().reloadChild();
	  // }
	}

	
	//--初始化树的高度-------------------------------------
	function custInit(){
        var height = document.body.clientHeight - 22;
        var tree = $id("orgTree_container");
		tree.style.height =height;
		getInitParam();
		var rootNode = $id("orgTree").getRootNode();
		findLoginOrg(rootNode);
		initTreeBeforeMove();
	}
    setTimeout(custInit,1000);
</script>

<script>
	var level = 0;
	function tts(){
		var orgTree = $id('orgTree');
//		orgTree.expandLevel(2);
		
		var tree = orgTree.getRootNode().getChildren();
		
//		alert(tree);
		
//		alert(tree.getText());
		
		readTree(level,tree);
	}
	function readTree(level,tree){
	   for(var i=0;i<tree.length;i++){
			if(tree[i].getText() != 'dd2'){
				tree[i].getTree().afterExpand = aftere;
				tree[i].expandNode();
			}else{
//				tree[i].select();
//				tree[i].onclick();
				
				
    			var parNode = tree[i].getParent();
//				alert(parNode);
				
//				parNode.collapseNode();
				
				tree[i].parentNode.removeChild(tree[i]);
				//parNode.removeChild(tree[i].parentNode);
				
				var entity = parNode.getEntity();
				
//				alert(entity.name);
				
//				alert(parNode.entity.getProperty("orgid"));
				
				return;
			}
		}
	}
	function aftere(nodeTree){
		var child = nodeTree.getChildren();
		if(child.length>0){
			readTree(level,child);
		}
		
	}
	
	function _rtreenode_onclick() {
//		alert(this.getText());
		var A = findRTree(this), D = A.model;
		D.menu.hide();
		function E() {
			if (B.isleaf)
				return;
			if (B.childrenContainer.style.display == "none")
				B.expandNode();
			else
				B.collapseNode()
		}
		var B = this, C = eventManager.getElement();
		if (C == this.cell || C == this.expandIcon || C == this.icon)
			this.selected();
		if (C == this.cell) {
			var F = D.getEntityInfo(this.entity.name).onclick;
			if (F)
				fireUserEvent(F, [this])
		}
		if (C == this.expandIcon)
			E()
	}
	
	function _rtreeNode_expandNode(B) {
		if (this.isleaf)
			return;
		var A = this.getTree().beforeExpand;
		if (A)
			if (A(this) == false)
				return;
		var C = this, D = findRTree(C).model;
		this.expandIcon.src = this.minusIconSrc;
		if (!C.childLoaded) {
			this.icon.src = RTREE_DROP_LOADING;
			setTimeout(function() {
				return _rtreemodel_loadNodeChild.apply(D, [C, B])
			}, 1);
			C.childLoaded = true
		} else if (!C.isleaf) {
			C.childrenContainer.style.display = "";
			this.icon.src = this.openIconSrc
		}
		var E = this.getTree().afterExpand;
		if (E)
			E(this)
	}
	
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
	
	/**
	* entity = F;
	**/
	function _popedomExpand(D, R, L, E, J, H, N, hasRoot, P){
		var empid = E.getProperty("empid");
		if(empid != undefined){
//			alert(empid);
		}
		return true;
	}
	
	function setEmpOprIsMain(node){
		if(checkAuthInclude(node)){
			var myAjax = new Ajax("/empMngr/setMainOrg_empMngr.action");
			myAjax.addParam("orgid",node.getParent().getProperty("orgId"));
			myAjax.addParam("empid",node.getProperty("empid"));
			myAjax.submit();
			var rtu = myAjax.getValue("root/data/flag");
			if(rtu == "success"){
				alert("设为主机构成功");
			}else{
				alert("设为主机构失败");
			}
		}
	}
    /**
    * 递归查询登录人员的机构
    **/
    function findLoginOrg(node){
    	if(node){}else{
    		return;
    	}
    	var children = node.getChildren();
    	if(!node.isExpanded()){
	    	window.setTimeout(findLoginOrg,100,node);
			return;
    	}
    	var orgtempseq = '${sessionScope.login_user.orgseq}';
    	for(var i=0;i<children.length;i++){
    		var tempNode = children[i];
    		var orgseq = tempNode.getProperty("orgSeq");
    		if(orgtempseq==orgseq){
    			tempNode.select();
    			return;
    		}else if(orgtempseq.test('^'+orgseq)){
		    	if(!tempNode.hasChildNode()){
		    		return;
		    	}
		    	tempNode.expandNode();
				findLoginOrg(tempNode);
				return;
			}
    	}
    	node.select();
    }
    
	$id("orgTree").afterShowMenu=function(node,menu)
	{
	   if(node.getProperty("ismain")=="n"){
	   		menu.addMenuItem("删除人员附机构","removeEmpOpr");//新增菜单项
	   }
	}
</script>
