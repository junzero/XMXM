<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      行政树
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<h:script src="/common/gotop/eos-rtree5.js" />
	<h:script src="/common/skins/default/scripts/common.js" />
  </head>
  <script type="text/javascript">
	var lookupTypeStr = '${changeTree.lookupTypeStr}';
	function _rteemodel_before_init(model){
		model.hasCheckbox=true;
		model.checkType="simple";
		model.checkButton="checkTypeBut";
		model.showCheckType = '${changeTree.showCheckType}';
		lookupInit();
		model.initInfoList("mngemps");
		model.initInfoList("mngorgs");
	}
	function lookupInit(){
		if(lookupTypeStr){
	    	var peArgument = {};
			if(lookupTypeStr==2){
		    	var paramEntity = new ParamEntity();
				paramEntity.setProperty('empid',dialogArguments[0]);
				paramEntity.setProperty('empname',dialogArguments[1]);
				peArgument['Employee']=[paramEntity,'empname','empid'];
			}
			if(lookupTypeStr==3){
		    	var paramEntity = new ParamEntity();
				paramEntity.setProperty('positionid',dialogArguments[0]);
				paramEntity.setProperty('posiname',dialogArguments[1]);
				peArgument['oaPosi']=[paramEntity,'posiname','positionid'];
			}
			if(lookupTypeStr==4){
		    	var paramEntity = new ParamEntity();
				paramEntity.setProperty('orgid',dialogArguments[0]);
				paramEntity.setProperty('orgname',dialogArguments[1]);
				peArgument['Organization']=[paramEntity,'orgname','orgid'];
			}
			window["dialogArguments"]=peArgument;
		}
	}
	function orgTree2InitParam(){
		var orgType = '${changeTree.orgType}';
		if(isBlank(orgType)){
			orgType = 3+4;
		}
		var param = "<orgType>"+orgType+"</orgType><groupID>${changeTree.groupID}</groupID>";
		return param;
	}
	function posiTree2InitParam(sNode){
		var entity = sNode.entity;
		var orgType = '${requestScope.posiType}';
		if(isBlank(orgType)){
			orgType = 3;
		}
		var param = "<roleids>${requestScope.roleids}</roleids><oParentOrg><id>"+
						entity.getProperty("id")+"</id></oParentOrg><orgType>"+orgType+"</orgType>";
		return param;
	}
	function _rtreemodel_showNodeMenu( rtreeNode )
	{
	    var tree=rtreeNode.getTree();
	    if(tree.beforeShowMenu&&tree.beforeShowMenu(rtreeNode)===false) return;
		var entityName = rtreeNode.entity.name;
		var entityInfo = this.getEntityInfo( entityName );
		var menuItems = entityInfo.menuItems;
		if( menuItems.length > 0 )
		{
			for( var i = 0; i < menuItems.length; i++ )
			{
				if(i!=0){
					var topwindow=_get_top_window();
		            var div = topwindow.document.createElement( "div" );
		            div.className="rtree-popmenu-item-line";
				  this.menu.insertItem(div);
				  }
				this.menu.insertItem( menuItems[i] );
			}
		    tree.afterShowMenu&&tree.afterShowMenu(rtreeNode,this.menu);
			this.menu.show();
			try{
				this.menu.style.zIndex = 2147483647;
			}catch(e){}
		}
	}
	
	
	function deleteOption(selObj){
		var index = selObj.options.selectedIndex;
		if(index>=0){
			if(selObj.options[index].treeNode){
				selObj.options[index].treeNode.changeCheckboxState();
			}
			selObj.options[index]=null;
		}
//		var oOption = document.createElement("OPTION");
//		form1.chooseSel.options.add(oOption);
//		oOption.innerText = form1.useSel.options[form1.useSel.options.selectedIndex].text;
//		oOption.value = form1.useSel.options[form1.useSel.options.selectedIndex].value;
	}
	function batchDeleteOption(selObj){
		var selObj = $id("selOrg");
		if(selObj){
			singleDeleteOption(selObj);
		}
		var selObj = $id("relatedSel");
		if(selObj){
			singleDeleteOption(selObj);
		}
		var selObj = $id("selPosi");
		if(selObj){
			singleDeleteOption(selObj);
		}
	}
	function singleDeleteOption(selObj){
		for (var i = selObj.options.length-1; i >= 0; i--) {
			if(selObj.options[i].selected){
				if(selObj.options[i].treeNode){
					selObj.options[i].treeNode.changeCheckboxState();
				}
				selObj.options[i]=null;
			} 
		}
	}
	function allDeleteOption(selObj){
		var selObj = $id("selOrg");
		if(selObj){
			deleteOptionOfAll(selObj);
		}
		var selObj = $id("relatedSel");
		if(selObj){
			deleteOptionOfAll(selObj);
		}
		var selObj = $id("selPosi");
		if(selObj){
			deleteOptionOfAll(selObj);
		}
	}
	function deleteOptionOfAll(selObj){
		for (var i = selObj.options.length-1; i >= 0; i--) {
			if(selObj.options[i].treeNode){
				selObj.options[i].treeNode.changeCheckboxState();
			}
			selObj.options[i]=null;
		}
	}
</script>
<body topmargin="0" leftmargin="0" scroll="no">
	<form action="">
		<table align="left" border="0" height="100%" width="100%" style="vertical-align: top;">
			<tr valign="top">
				<td width="100%" valign="top">
					<h:hidden property="topID"/>
					<r:rtree id="orgSrcTree"  width="100%" height="370">
						<r:treeRoot childEntities="Organization" display="行政机构树"
							initParamFunc="getInitParam" onDblclickFunc="rtnSelectVal"
							icon="/common/images/icons/arrow_merge.png"
							action="/workgroup/managertreeAction_selectAdminTreeRoot.action">
						</r:treeRoot>
						<r:treeNode nodeType="Organization" showField="orgname"
							childEntities="Organization;oaPosi;Employee"
							icon="/common/images/icons/chart_organisation.gif" initParamFunc="orgTree2InitParam"
							submitXpath="oParentOrg" onDblclickFunc="rtnSelectVal"
							action="/workgroup/managertreeAction_selectAdminTreeChild.action">
							<r:treeMenu>
								<l:equal value="${changeTree.tdSelOrg}" targetValue=",Organization,">
									<r:treeMenuItem display="全选下级机构" onClickFunc="selDowOrgFun"/>
									<r:treeMenuItem display="不选下级机构" onClickFunc="nselDowOrgFun"/>
							    </l:equal>
								<l:equal  value="${changeTree.tdSelEmp}" targetValue=",Employee,">
									<r:treeMenuItem display="全选下级人员" onClickFunc="selDowEmpFun"/>
									<r:treeMenuItem display="不选下级人员" onClickFunc="nselDowEmpFun"/>
								</l:equal>
							</r:treeMenu>
						</r:treeNode>
						<r:treeNode nodeType="oaPosi" showField="posiname" initParamFunc="getInitPosiParam"
							childEntities="oaPosi;oaEmp" icon="/common/images/icons/application_form.png"
							submitXpath="oParentPosi" 
							action="org.gocom.abframe.org.publicom.abftzzjggxsbbiz.queryChileNodeOfPosiAll.biz"
							>
						</r:treeNode>
						<r:treeNode nodeType="Employee" showField="empname" onDblclickFunc="rtnSelectVal"
							icon="/common/images/icons/user.gif" onRefreshFunc="empListRefresh">
						</r:treeNode>
					</r:rtree>
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript">
	//刷新人员节点根据不同性别区分图片
	function empListRefresh(node){
		if(node.getProperty("gender") == "1")
		{
			node.setIcon("/common/images/icons/user.gif");
		}else{
			node.setIcon("/common/images/icons/user_female.gif");
		} 
		node.title=node.getEntity().getProperty("empcode");
	}
	function getInitParam(){
	    var ret = '<startorgid>${changeTree.startOrgid}</startorgid><startorgcode>${changeTree.startOrgcode}</startorgcode>';
	    	ret += "<jgsx>${changeTree.jgsx}</jgsx><groupID>${changeTree.groupID}</groupID>";
		return ret ;
	}	
	/**
	*	级联按钮
	*/
	function changeButionFun(type,sNode){
		if($id("checkTypeBut").checkType=="simple"){
			$id("checkTypeBut").value="级联选取";
			$id("checkTypeBut").checkType="associated";
		}else{
			$id("checkTypeBut").value="单步选取";
			$id("checkTypeBut").checkType="simple";
		}
	}
	function getInitOrgParam(){
		var orgType = '${changeTree.orgType}';
		if(isBlank(orgType)){
			orgType = 2+3+4;
		}
		var ret = "<orgdegree>${requestScope.orgdegree}</orgdegree><startID>${requestScope.startID}</startID>"
						+"<roleids>${requestScope.roleids}</roleids><orgType>"+orgType+"</orgType>";
			ret +="<jgsx>${requestScope.jgsx}</jgsx><orgDType>${requestScope.orgDType}</orgDType><groupID>${changeTree.groupID}</groupID>";
			//ret +="<estatus>${requestScope.estatus}</estatus><ostatus>${requestScope.ostatus}</ostatus>";
		return ret;
	}
	function getInitPosiParam(){
		var posiType = '${requestScope.posiType}';
		if(isBlank(posiType)){
			posiType = 3;
		}
		var ret = "<orgdegree>${requestScope.orgdegree}</orgdegree><startID>${requestScope.startID}</startID>"
						+"<roleids>${requestScope.roleids}</roleids><orgType>"+posiType+"</orgType><groupID>${changeTree.groupID}</groupID>";
		return ret;
	}
	function deleteTargetAFun(sNode){
		var nodeType = getOrgTypeByEntity(sNode);
		var nodeParam = [];
		nodeParam[0]=sNode;
		nodeParam[1]=nodeType;
		var url = "org.gocom.abframe.org.publicom.selectDialogOrgType.flow?time="+new Date().getTime() + "&filterStr=" + nodeType;
		showModalCenter(url, nodeParam, null, 300, 120, '<b:message key="l_title_select_oper"/>');
	}
    function getOrgTypeByEntity(sNode){
    	var name = sNode.entity.name;
    	if(name=='Organization'){
	    	return 4;
    	}else if(name=='oaPosi'){
    		return 3;
    	}else if(name=='Employee'){
    		return 2;
    	}
    }
    function selDowEmpFun(node){
    	if(!node.isExpanded()){
    		alert('下级未展开!操作失败');
    		return;
    	}
    	var childs = node.getChildren();
    	for(var i=0;i<childs.length;i++){
    		if(childs[i].entity.name=='Employee' && !childs[i].isChecked()){
    			childs[i].checkbox_click();
    		}
    	}
    }
    function selDowOrgFun(node){
    	if(!node.isExpanded()){
    		alert('下级未展开!操作失败');
    		return;
    	}
    	var childs = node.getChildren();
    	for(var i=0;i<childs.length;i++){
    		if(childs[i].entity.name=='Organization' && !childs[i].isChecked()){
    			childs[i].checkbox_click();
    		}
    	}
    }
    function nselDowEmpFun(node){
    	if(!node.isExpanded()){
    		alert('下级未展开!操作失败');
    		return;
    	}
    	var childs = node.getChildren();
    	for(var i=0;i<childs.length;i++){
    		if(childs[i].entity.name=='Employee' && childs[i].isChecked()){
    			childs[i].checkbox_click();
    		}
    	}
    }
    function nselDowOrgFun(node){
    	if(!node.isExpanded()){
    		alert('下级未展开!操作失败');
    		return;
    	}
    	var childs = node.getChildren();
    	for(var i=0;i<childs.length;i++){
    		if(childs[i].entity.name=='Organization' && childs[i].isChecked()){
    			childs[i].checkbox_click();
    		}
    	}
    }
	function custInit(){
		var rootNode = $id("orgSrcTree").getRootNode();
		findLoginOrg(rootNode);
//		SelectInit();
		initButtonStyle();
	}
	
	function rtnSelectVal(){
		window.parent.rtnSelectVal();
	}
    /**
    * 递归查询登录人员的机构
    **/
    function findLoginOrg(node){
		var orgtempseq = '<b:write property="loginOrgSeq"/>';
    	if(node){}else{
    		return;
    	}
    	if(orgtempseq){}else{
    		return;
    	}
    	var children = node.getChildren();
    	if(!node.isExpanded()){
	    	window.setTimeout(findLoginOrg,100,node);
			return;
    	}
    	
    	for(var i=0;i<children.length;i++){
    		var tempNode = children[i];
    		var orgseq = tempNode.getProperty("orgseq");
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
	eventManager.add(window,"load",custInit);
</script>
  </body>
</html>
