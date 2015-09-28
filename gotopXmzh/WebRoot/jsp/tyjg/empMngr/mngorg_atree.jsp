<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="com.gotop.vo.system.MUOUserSession"%>
<%@page import="com.gotop.util.Global"%>
<html>
<!-- 
  - Author(s): lz
  - Date: 2010-05-06 17:18:08
  - Description:
-->
<head>
<title>机构树弹出窗口demo</title>
<h:css href="/css/style1/style-custom.css"/>
</head>
<h:script src="/common/skins/default/scripts/common.js" />
<h:script src="/common/gotop/eos-rtree2.js" />
<%
		String showSelBoxStr = (String)request.getAttribute("showSelBox");
		String orgTypeStr = (String)request.getAttribute("orgType");
		String posiTypeStr = (String)request.getAttribute("posiType");
		String lookupTypeStr = (String)request.getAttribute("lookupType");
		String checkcountStr = (String)request.getAttribute("checkcount");
		String checkTitle = (String)request.getAttribute("checkTitle");
		int showSelBox=0;
		int orgType = 3+4;
		int posiType = 3;
		if(showSelBoxStr!=null && !showSelBoxStr.equals("")){
			showSelBox = Integer.parseInt(showSelBoxStr);
		}
		if(orgTypeStr!=null && !orgTypeStr.equals("")){
			orgType = Integer.parseInt(orgTypeStr);
		}
		if(posiTypeStr!=null && !posiTypeStr.equals("")){
			posiType = Integer.parseInt(posiTypeStr);
		}
		if(lookupTypeStr!=null && !lookupTypeStr.equals("") && !lookupTypeStr.equals("null")){
			showSelBox = Integer.parseInt(lookupTypeStr);
		}else{
			lookupTypeStr = "";
		}
		String tdSelOrg = "none";
		String tdSelPosi = "none";
		String tdSelEmp = "none";

		if(showSelBox==4 || showSelBox==(4+3)
			             || showSelBox==(3+4)
				         || showSelBox==(2+3+4)){
			tdSelOrg="";
		}
		if((showSelBox==3 || showSelBox==(2+3)
			              || showSelBox==(3+4)
			              || showSelBox==(2+3+4)) &&
		  (orgType==(2+3) || orgType==(3+4) 
			              || orgType==(2+3+4)
			              || posiType==(3)  
			              || posiType==(3+4)
						  || posiType==(2+3+4))){
			tdSelPosi="";
		}
		if((showSelBox==2||showSelBox==(2+3)
					     ||showSelBox==(2+4) 
					     || showSelBox==(2+3+4))&&
			  (orgType==2||orgType==(2+3)
					     ||orgType==(2+4) 
					     || orgType==(2+3+4)
					     || posiType==(2+3)
					     || posiType==(2+3+4))){
			tdSelEmp="";
		}
		
		int tdSelEmpSize = 10;
		int tdSelPosiSize = 10;
		if("none".equals(tdSelOrg) || "none".equals(tdSelPosi)){
			tdSelEmpSize=20;
		}
		if("none".equals(tdSelOrg) || "none".equals(tdSelEmp)){
			tdSelPosiSize=20;
		}
%>
<script type="text/javascript">
	var lookupTypeStr = '<%=lookupTypeStr%>';
	function rtnSelectVal(){
		var rtnSelectBut = $id("rtnSelectBut");
		var checkTypeBut = $id("checkTypeBut");
		var deleteSelectedBut = $id("deleteSelectedBut");
		var deleteSelAllBut = $id("deleteSelAllBut");
		if(rtnSelectBut){
			rtnSelectBut.disabled=true;
		}
		if(checkTypeBut){
			checkTypeBut.disabled=true;
		}
		if(deleteSelectedBut){
			deleteSelectedBut.disabled=true;
		}
		if(deleteSelAllBut){
			deleteSelAllBut.disabled=true;
		}
		if(lookupTypeStr){
			var sorgidArra = [[],[],[],[],[]];
			var selObj = null;
			var butObj = null;
			if(lookupTypeStr==4){
				selObj = $id("selOrg");
				butObj = $id("mngorgs");
			}else{
				return false;
			}
			if(selObj!=null && selObj.options.length>0){
				for (var i = selObj.options.length-1; i >= 0; i--) {
					var soption = selObj.options[i];
					var NEntity = soption.treeNode.entity;
					sorgidArra[0].push(NEntity.getProperty(butObj.entityProperty));
					sorgidArra[1].push(NEntity.getProperty(butObj.displayProperty));
				}
			}
			window.returnValue = sorgidArra;
		}else{
			var sorgidArra = {};
			var checkList = $id("orgSrcTree").getCheckedList(1);
			var infoObjArra = $id("orgSrcTree").model.infoObjArra;
			for(var k=0;infoObjArra!=null && k<infoObjArra.length;k++){
				infoObj = infoObjArra[k];
				sorgidArra[infoObj.entityName]=[];
				if(infoObj.relatedSelID){
					var selObj = $id(infoObj.relatedSelID);
					if(selObj!=null && selObj.options.length>0){
						for (var i = selObj.options.length-1; i >= 0; i--) {
							var soption = selObj.options[i];
							var pNode = soption.treeParentNode
							if(pNode){
								if(getOrgTypeByEntity(pNode)==4){
									soption.treeNode.entity.setProperty("parentId",pNode.entity.getProperty("orgId"));
									soption.treeNode.entity.setProperty("parentMappId",pNode.entity.getProperty("ysjgbh"));
									soption.treeNode.entity.setProperty("parentCode",pNode.entity.getProperty("orgcode"));
								}else{
									soption.treeNode.entity.setProperty("parentId",pNode.entity.getProperty("positionId"));
									soption.treeNode.entity.setProperty("parentMappId",null);
									soption.treeNode.entity.setProperty("posiCode",pNode.entity.getProperty("posiCode"));
								}
							}
							sorgidArra[infoObj.entityName].push(soption.treeNode.entity);
						}
					}
				}
			}
			window.returnValue = sorgidArra;
		}
		window.closeD();
	
	}
	function _rteemodel_before_init(model){
		model.hasCheckbox=true;
		model.checkType="simple";
		model.checkButton="checkTypeBut";
		lookupInit();
		model.initInfoList("mngorgs");
	}
	function lookupInit(){
		if(lookupTypeStr){
	    	var peArgument = {};
			if(lookupTypeStr==4){
		    	var paramEntity = new ParamEntity();
				paramEntity.setProperty('orgId',dialogArguments[0]);
				paramEntity.setProperty('orgName',dialogArguments[1]);
				peArgument['Tomorganization']=[paramEntity,'orgName','orgId'];
			}
			window["dialogArguments"]=peArgument;
		}
	}
	function orgTree2InitParam(){
		var orgType = '${requestScope.orgType}';
		if(isBlank(orgType)){
			orgType = 3+4;
		}
		var param = "<roleids>${requestScope.roleids}</roleids>"+
						"<oParentOrg><id>${requestScope.startID}</id></oParentOrg><orgType>"+orgType+"</orgType>";
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
	}
	function batchDeleteOption(selObj){
		var selObj = $id("selOrg");
		if(selObj){
			singleDeleteOption(selObj);
		}
		var selObj = $id("selEmp");
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
		var selObj = $id("selEmp");
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

<%
	MUOUserSession musous = (MUOUserSession)request.getSession().getAttribute(Global.LOGON_USER_KEY);
	String startOrgid =musous.getOrgentityid().toString();
%>
<body>
	<form action="">
	<table align="left" border="0" height="100%" width="100%" style="vertical-align: top;">
		<tr class="form_bottom">
			<td colspan="2">
				<input type="button" id="rtnSelectBut" value="打勾后，确认返回" onclick="javascript:rtnSelectVal();" title="返回已选内容,并关闭窗口"/>
				<%
					if(!"1".equals(checkcountStr)){
				%>
				<input type="button"  id="checkTypeBut" value="单步选取" onclick="javascript:changeButionFun(0,null);" checkType="simple" 
					title="级联选取:当选中或取消父节点,则可使已展开的子节点一同联动选中与取消。单步选取：只是单纯选中与取消当级，并不联动"/>
				<input type="button" id="deleteSelectedBut" value="移除选中" onclick="batchDeleteOption();" title="将在列表中的选中对象移除"/>
				<input type="button" id="deleteSelAllBut" value="移除全部" onclick="allDeleteOption();" title="将在列表中的全部对象移除"/>
				<%}%>
				<%
					if(checkTitle!=null && !"".equals(checkTitle.trim())){
				%>
					<div style="color:red" align="left">
						<%=checkTitle%>
					</div>
				<%}%>
			</td>
		</tr>  
		<tr valign="top">
			<td width="100%" valign="top">
				<h:hidden property="topID"/>
				<r:rtree id="orgSrcTree"  width="100%" height="375">
					<r:treeRoot childEntities="Tomorganization" display="单位机构树"
						initParamFunc="getInitParam"
						icon="/common/images/icons/arrow_merge.png"
						action="/empMngr/queryMngorgOfLogon_empMngr.action">
					</r:treeRoot>
					<r:treeNode nodeType="Tomorganization" showField="orgName"
						childEntities="Tomorganization;OmPosition;Omemployee" initParamFunc="getInitOrgParam"
						icon="/common/images/icons/chart_organisation.gif"
						submitXpath="oParentOrg"
						action="/empMngr/queryTreeChildNodes_empMngr.action">
						
					</r:treeNode>
				</r:rtree>
			</td>
			<td>
				<table>
					<tr>
						<td id="tdSelOrg" style="display: <%=tdSelOrg%>;">
							<table>
								<tr>
									<td>
										已选项机构:
										<SELECT style="width: 180px" size=22 name="selOrg" id="selOrg" multiple="multiple" ondblclick="deleteOption(this)">
										</SELECT>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="mngorgs" name="mngorgs" entityName="Tomorganization" entityProperty="orgId" style="display: none;" displayProperty="orgName"
		relatedSelID="selOrg" displayOtherProperty="orgId,orgCode" checkcount="<%ForUtil.outPm(out,request,"checkcount");%>";
		entitySplit=""/>
	</form>
<script type="text/javascript">
	//刷新人员节点根据不同性别区分图片
	function empListRefresh(node)
	{
		if(node.getProperty("gender") == "1")
		{
			node.setIcon("/common/images/icons/user.gif");
		}else{
			node.setIcon("/common/images/icons/user_female.gif");
		} 
		node.title=node.getEntity().getProperty("empCode");
	}
	function getInitParam(){
	    var ret = "<startOrgid><%=startOrgid%></startOrgid>";
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
		var orgType = <%=orgType%>;
		if(isBlank(orgType)){
			orgType = 3+4;
		}
		var ret = "<orgDegree>${requestScope.orgDegree}</orgDegree><startID>${requestScope.startID}</startID>"
						+"<roleids>${requestScope.roleids}</roleids><orgType>"+orgType+"</orgType>";
			ret +="<jgsx>${requestScope.jgsx}</jgsx><orgDType>${requestScope.orgDType}</orgDType>";
			ret +="<estatus>${requestScope.estatus}</estatus><ostatus>${requestScope.ostatus}</ostatus>";
		return ret;
	}
	function getInitPosiParam(){
		var posiType = '${requestScope.posiType}';
		if(isBlank(posiType)){
			posiType = 3;
		}
		var ret = "<orgDegree>${requestScope.orgDegree}</orgDegree><startID>${requestScope.startID}</startID>"
						+"<roleids>${requestScope.roleids}</roleids><orgType>"+posiType+"</orgType>";
		return ret;
	}
	function deleteTargetAFun(sNode){
		var nodeType = getOrgTypeByEntity(sNode);
		var nodeParam = [];
		nodeParam[0]=sNode;
		nodeParam[1]=nodeType;
		var url = "/empMngr/selectDialogOrgType_empMngr.action?time ="+new Date().getTime() + "&nodeType=" + nodeType;
		showModalCenter(url, nodeParam, null, 300, 120, '<b:message key="l_title_select_oper"/>');
	}
    function getOrgTypeByEntity(sNode){
    	var name = sNode.entity.name;
    	if(name=='Tomorganization'){
	    	return 4;
    	}else if(name=='OmPosition'){
    		return 3;
    	}else if(name=='Omemployee'){
    		return 2;
    	}
    }
	function custInit(){
		var rootNode = $id("orgSrcTree").getRootNode();
		findLoginOrg(rootNode);
//		SelectInit();
		initButtonStyle();
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
	eventManager.add(window,"load",custInit);
</script>
</body>
</html>