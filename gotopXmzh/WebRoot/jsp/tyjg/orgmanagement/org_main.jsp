<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.gotop.tyjg.orgmanagement.model.Tomorganization"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      机构管理主页面
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body style="margin: 0;">
	<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
		<tr>       
			<td width="25%" height="100%" valign="top">
				<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
					<tr height="4%">
						<td class="eos-panel-title">&nbsp;单位机构树</td>
					</tr>
					<tr>
						<td width="25%" height="100%" valign="top" class="eos-panel-body" >
						  	<r:rtree id="orgTrees"  width="100%" hasRoot="true">
								<r:treeRoot childEntities="Tomorganization" display="机构树" 
									icon="/common/images/icons/chart_organisation.png"
									onClickFunc="modifySelfOrg"
									action="/orgmanagement/organizationAction_queryChildOrg.action">
									<r:treeMenu>
										<r:treeMenuItem display="增加下级机构" onClickFunc="addSubOrg" />
										<r:treeMenuItem display="刷新" onClickFunc="refreshSubnode"/>
									</r:treeMenu>
								</r:treeRoot>
								<r:treeNode preload="true" nodeType="Tomorganization" showField="orgName"
									childEntities="Tomorganization"
									icon="/common/images/icons/chart_organisation.png"
									submitXpath="oParentOrg"
									onClickFunc="modifySelfOrg"
									action="/orgmanagement/organizationAction_queryChildOrg.action">
									<r:treeMenu>
										<r:treeMenuItem display="增加下级机构" onClickFunc="addSubOrg" />
										<r:treeMenuItem display="当前机构置顶" onClickFunc="moveSelfOrgTop" />
										<r:treeMenuItem display="刷新" onClickFunc="refreshSubnode"/>
									</r:treeMenu>
									<r:treeMove toNode="Tomorganization" initParamFunc="getMoveInitParam" 
									action="/orgmanagement/organizationAction_moveOrg.action" />
								</r:treeNode>
							</r:rtree>
						</td>
				    </tr>
				</table>
			<td width="75%" height="100%" valign="top">
				<iframe id="tab" style="width:100%;height:100%" frameBorder="0" scrolling="no"></iframe>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		var isMoveOrCopy = "move";
	    var m;
	    var mt;
	    
	    /* load初始化函数
	    *
	    */   
	    function custInit(){  
	    	//树高度调整
	        var height = document.body.clientHeight - TREE_MODIFY_HEIGHT;
			$id("orgTrees").style.height =height;
		}
	    //初始化树的高度
	    eventManager.add(window,"load",custInit);
		function refreshSubnode(node){//刷新
			node.reloadChild();
		}
		function modifySelfOrg(node){
			if(treeAuthCheck(node)){
				if(!node.isRootNode()){
					document.getElementById("tab").src = "/jsp/tyjg/orgmanagement/org_tab.jsp?orgid="+node.getProperty("orgId")+"&dateTime="+new Date();
				}
			}
		}
		 /* 
     *  重写树移动的接口
     *  param0: 待移动的树节点
     *  param1: 移动的目标节点
     */
    function move(move_node,moveto_node) {
    	if(treeAuthCheck(moveto_node)){
		    var tree=move_node.getTree();
		    var moveAction = tree.model.getMoveAction( move_node, moveto_node );
		    if( moveAction ) {//可以移动,执行设置的动作
		       var beforeMove=move_node.getTree().beforeMove;
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
	 }
	     
	 /*  
      *  初始化树移动前的方法
      */
     function initTreeBeforeMove() {
         document.getElementById("orgTrees").beforeMove = function(movenode, toNode) {
		     var url = "/jsp/tyjg/orgmanagement/select_dialog.jsp";
		     showModalCenter(url, "", nodeMoveCallBack, 280, 120, '<b:message key="l_title_select_oper"/>'); 
			 movenode.entity.__type="";
			 toNode.entity.__type=""; 
		     m = movenode;
             mt = toNode;
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
    function getMoveInitParam(node){
    	 if(treeAuthCheck(node)){
		    var str = "<isMoveOrCopy>" + isMoveOrCopy + "</isMoveOrCopy>";
			return str;
    	 }else{
    	 	return;
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
	   	var orgtempseq ="${sessionScope.login_user.orgseq}";
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
	 //--初始化树的高度-------------------------------------
	function custInit(){
        var height = document.body.clientHeight - 22;
        var treeOrg = document.getElementById("orgTrees");
		document.getElementById("orgTrees_container").style.height =height;
		var rootNode = document.getElementById("orgTrees").getRootNode();
		findLoginOrg(rootNode);		
		initTreeBeforeMove();
	}
  	function getLength(D,I) {
		var C;
		D.isloadData=false;
		if (D.isloadData !== true) {
			var A = this.getNodeExpandAction(D), O = this.getExpandParam(D);
			if(I){
				I = I.split(",");
			}else{
				I = this.getExpandRetXpaths(D);
			}
			if (!(A && I)) {
				D.icon.src = D.openIconSrc;
				D.loadFinished = true;
				return
			}
			var K = new HideSubmit(A);
			K.submitXML(O);
			var B = K.getXMLDom();
			C = Dataset.create(B, "root/data/" + I[0]);
			for (var G = 1; G < I.length; G++){
				C.appendDataset(Dataset.create(B, "root/data/" + I[G]))
			}
		} else
			C = D.datasetExp;
		return C.values;
	}
	//机构置顶
	function moveSelfOrgTop(node){
		try{
			if(treeAuthCheck(node)){
				var myAjax = new Ajax("/orgmanagement/organizationAction_moveOrgTop.action");
				myAjax.addParam("orgid", node.getProperty("orgId"));
				myAjax.submit();
				var returnNode =myAjax.getValue("root/data/falg");
		           if (returnNode=="true"){
		               alert("操作成功!")
					   document.getElementById("orgTrees").getRootNode().reloadChild();
		           }else{
		               alert("操作失败!");
		           }
		    }
		}catch(e){
			alert(e.name + ": " + e.message + "异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
		}
	}
	//添加机构
	function addSubOrg(node){
	    if(treeAuthCheck(node)){//验证有无可管理权限
	        var sUrl="/jsp/tyjg/orgmanagement/add_org.jsp";
		    if(!node.isRootNode()){   
		       sUrl += "?parentOrgId="+node.getProperty("orgId")+"&status=running";
		       sUrl += "&orgCode="+node.getProperty("orgCode").substr(0,6);
		       sUrl += "&parentName="+node.getProperty("orgName");
		       sUrl += "&orgSeq="+node.getProperty("orgSeq");
		       sUrl += "&area="+node.getProperty("area");
	   		}else{
	   		   sUrl=sUrl+"?status=running";
	   		}	   		
	   		sUrl +='&_ts='+new Date();   //防止IE缓存，在每次打开时加个时间差的参数,并且作为判断是否弹出窗口的标志
		    showModalCenter(encodeURI(sUrl),null,addOrgBack,770,428,"<b:message key='orgSubMaintain_l_menu_addSubOrg'/>");
	   }
	}
	function addOrgBack(values){
		document.getElementById("orgTrees").getSelectNode().reloadChild();
	}
		var mngorgs = new Array();
		<%
			List<Tomorganization> list = (List<Tomorganization>)session.getAttribute("managerOrg");
			if(list != null){
				for(Tomorganization om : list){
		%>
					mngorgs.push("<%=om.getOrgSeq()%>");
				<%}%>
				<%
					session.removeAttribute("managerOrg");
				%>
    	<%}%>
    //判断用户是否有权操作机构
    function checkAuth(orgseq){
    	var operatorid = '${sessionScope.login_user.empid}';
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
	     if(orgseq!=null &&orgseq!=""&&orgseq!=undefined){//如果是机构
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
   function treeAuthCheck(node){
	  if(!node.isRootNode()){      
	   var orgseq = node.getProperty("orgSeq");
	   if(orgseq!=null &&orgseq!=""&&orgseq!=undefined){	        
	     return checkAuth(orgseq);      
	   }else{	        
	     return checkAuthInclude(node.getParent());
	   }
	  }else{
	       return true;
	  }
  }
	setTimeout(custInit,1000);
	</script>
  </body>
</html>
