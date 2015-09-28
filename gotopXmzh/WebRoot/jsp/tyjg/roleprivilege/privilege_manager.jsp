<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
    <head>
        <title></title>
        <%
        	//权限功能树
        	String privilegeTree = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("roleManager_l_title_privilegeTree");
        	String roleReadonly = request.getParameter("roleReadonly");
			if(roleReadonly==null || "".equals(roleReadonly)){
				roleReadonly = "0";
			}
        %>
    <script>
    /*
     * 保存角色具有的功能
     */
    function addPrivilege() {
    	var roleid = $name("acRole.roleId").value;
    	if( roleid ==""){
    	    alert('<b:message key="roleManager_l_pleaseSelectRole"/>'); 
    	    return;
    	}
    	
        var myAjax = new Ajax("/tyjg/roleprivilege/savePrivelege_action.action");     
 		var treeObj = $id("abframe");
 		treeObj.createHiddenData();
 		var funcsize = '<b:size property="funcList"/>';
 		var arra = new Array();
 		var str = "";
 		for( var i = 1 ; i <=funcsize; i++ ) {
 		    if( $name("func[" + i + "]/funcGroupId" ) != null ) {
 		        str = str + "<func>";
 		        str = str + "<funcGroupId>" + $name("func[" + i + "]/funcGroupId" ).value + "</funcGroupId>";
 		        str = str + "<appId>" + $name("func[" + i + "]/appId" ).value + "</appId>";
 		        str = str + "<funcCode>" + $name("func[" + i + "]/funcCode" ).value + "</funcCode>";
 		        str = str + "<roleId>" + $name("acRole.roleId" ).value + "</roleId>";
 		        if($name("func[" + i + "]/isLock")!=null){
	 		        str = str + "<isLock>" + $name("func[" + i + "]/isLock" ).value + "</isLock>";
 		        }
 		        
 		        str = str + "</func>";       
<%-- 		        myAjax.addParam("func[" + i + "].funcGroupId", $name("func[" + i + "]/funcGroupId" ).value);
 		        myAjax.addParam("func[" + i + "].appId", $name("func[" + i + "]/appId" ).value);
 		        myAjax.addParam("func[" + i + "].funcCode", $name("func[" + i + "]/funcCode" ).value);
 		        myAjax.addParam("func[" + i + "].roleId", $name("acRole.roleId" ).value);
 		        if($name("func[" + i + "].isLock")!=null){
	 		        myAjax.addParam("func[" + i + "].isLock", $name("func[" + i + "].isLock" ).value);
 		        }--%>
 		    }
 		}
 		var funcsize = '<b:size property="funcGroupList"/>';//funcgroupid
 		var temp = funcsize;
 		for( var i = 1 ; i <=funcsize; i++ ) {
 			var type = $name("funcGroup[" + i + "]/type" );
 		    if( $name("funcGroup[" + i + "]/funcGroupId" ) != null && type != null && type.value==1) {
 		        str = str + "<funcGroup>";
 		        str = str + "<funcGroupId>" + $name("funcGroup[" + i + "]/funcGroupId" ).value + "</funcGroupId>";
 		        str = str + "<appId>" + $name("funcGroup[" + i + "]/appId" ).value + "</appId>";
 		        str = str + "<roleId>" + $name("acRole.roleId" ).value + "</roleId>";
 		        str = str + "<type>" + $name("funcGroup[" + i + "]/type").value + "</type>";
 		        
 		        if($name("funcGroup[" + i + "]/isLock")!=null){
	 		        str = str + "<isLock>" + $name("funcGroup[" + i + "]/isLock" ).value + "</isLock>";
 		        }
 		        str = str + "</funcGroup>";
<%-- 		        myAjax.addParam("funcGroup[" + i + "].funcGroupId", $name("funcGroup[" + i + "]/funcGroupId" ).value);
 		        myAjax.addParam("funcGroup[" + i + "].appId", $name("funcGroup[" + i + "]/appId" ).value);
 		        myAjax.addParam("funcGroup[" + i + "].roleId", $name("acRole.roleId" ).value);
 		        if($name("funcGroup[" + i + "]/isLock")!=null){
	 		        myAjax.addParam("funcGroup[" + i + "].isLock", $name("funcGroup[" + i + "]/isLock" ).value);
 		        }--%>
 		    }	    
 		}
 		var funcsize = '<b:size property="subFuncGroupList"/>';
 		for( var i = 1 ; i <=funcsize; i++ ) {
 			var type = $name("subFuncGroup[" + i + "]/type");
 		    if( $name("subFuncGroup[" + i + "]/funcGroupId" ) != null && type != null && type.value==1) {
 		        str = str + "<subFuncGroup>";
 		        str = str + "<funcGroupId>" + $name("subFuncGroup[" + i + "]/funcGroupId" ).value + "</funcGroupId>";
 		        str = str + "<appId>" + $name("subFuncGroup[" + i + "]/appId" ).value + "</appId>";
 		        str = str + "<roleId>" + $name("acRole.roleId" ).value + "</roleId>";
 		        str = str + "<isLock>" + $name("subFuncGroup[" + i + "]/isLock" ).value + "</isLock>";
 		        str = str + "<type>" + $name("subFuncGroup[" + i + "]/type").value + "</type>";
 		        if($name("subFuncGroup[" + i + "]/islock")!=null){
	 		        str = str + "<isLock>" + $name("subFuncGroup[" + i + "]/isLock" ).value + "</isLock>";
 		        }
 		        str = str + "</subFuncGroup>"		        
<%-- 		        myAjax.addParam("subFuncGroup[" + i + "].funcGroupId", $name("subFuncGroup[" + i + "]/funcGroupId" ).value);
 		        myAjax.addParam("subFuncGroup[" + i + "].appId", $name("subFuncGroup[" + i + "]/appId" ).value);
 		        myAjax.addParam("subFuncGroup[" + i + "].roleId", $name("acRole.roleId" ).value);
 		        if($name("subFuncGroup[" + i + "]/islock")!=null){
	 		        myAjax.addParam("subFuncGroup[" + i + "].isLock", $name("subFuncGroup[" + i + "]/isLock" ).value);
 		        }--%>
 		    }	    
 		}
 		str = str + "<acRole><roleId>" + $name("acRole.roleId" ).value + "</roleId></acRole>"
 		myAjax.addParam("map", str);
		myAjax.submit();
 		
 		//调运业务逻辑后的返回值myAjax.submitForm(frm)
 		var returnNode = myAjax.getResponseXMLDom();
        if( returnNode ) {
            if( myAjax.getValue("root/data/flag") == 1 )
                alert( '<b:message key="l_m_save_success"/>' );  //<!--  保存成功 -->
            else {
                alert( '<b:message key="l_m_save_fail"/>' );      //   <!-- 保存失败 -->
            }
        } else {
            alert( '<b:message key="l_m_save_fail"/>' );        // <!-- 保存失败 -->
        }
     }
	/*
	 *  自定义初始化按钮样式
	 */
	function custInit(){  
		initButtonStyle();       
	}
	
	/**
	*  选定入库
	**/
	function checkObj(butObj){
		var treeObj = $id("abframe").getRootNode().getTree();
		var sn = treeObj.getSelectedNode();
		
		if(sn.entity.getProperty("islock")==1){
			sn.entity.setProperty("islock",0);
			var text = sn.getText();
			var ssIndex = text.lastIndexOf("(级联授权)");
			var ttstr = text.substr(0,ssIndex);
			sn.setText(ttstr);
			butObj.value="级联授权";
		}else{
			sn.entity.setProperty("islock",1);
			var text = sn.getText();
			var ssIndex = text.lastIndexOf("(级联授权)");
			if(ssIndex<0){
				sn.setText(sn.getText()+"(级联授权)");
			}
			butObj.value="取消级联授权";
		}
		if(sn.entity.checked==0 || sn.entity.checked==undefined){
			sn.entity.checked = 2;
			sn.checkbox.src = STREE_CHECKBOX_TRUE_ICON1;
		}
		
	}
	/**
	*   初始化树
	**/
	function initTreeClock(treeObj){
		if(treeObj){}else{
			treeObj = $id("abframe").getRootNode();
		}
		var child = treeObj.getChildren();
		
		for(var i=0;i<child.length;i++){
			try{
				var islock = child[i].entity.getProperty("islock");
				
				if (!child[i].isChildLoaded){
					child[i].addChildNode();
					child[i].childrenContainer.style.display = "none"
				}
				
				if((child[i].entity.name=='funcGroupList' || child[i].entity.name=='subFuncGroupList' || child[i].entity.name=='funcList') && islock==1){
					var ssIndex = child[i].getText().lastIndexOf("(级联授权)");
					if(ssIndex<0){
						child[i].setText(child[i].getText()+"(级联授权)");
						if(child[i].entity.checked==0 || child[i].entity.checked==undefined){
							child[i].entity.checked = 2;
							child[i].checkbox.src = STREE_CHECKBOX_TRUE_ICON1;
						}
					}
					if(child[i].entity.name=='funcList'){
						child[i].oncontextmenu = WTreeOnContextmenu;
						child[i].menuItems = new Array();
						child[i].menu = new EOSTreeMenu(child[i]);
						child[i].menuItems.push(new EOSTreeMenuItem("新增虚拟项", "testModel"));
					}
				}
				initTreeClock(child[i]);
			}catch(e){}
		}
	}
	/**
	* 选中事件
	**/
	function _streeNode_selected() {
		var A = this.tree;
		if (A.cur_node)
			A.cur_node.cell.className = "RC_TREE_CELL";
		this.cell.className = "RC_TREE_ACTIVENODE";
		A.cur_node = this
		changeBution(this);
	}
	/**
	* 锁定与解锁
	**/
	function changeBution(node){
		var checkObj = $id("checkObj");
		if(checkObj==null){
			return null;
		}
		ent = node.entity;
		var islock = ent.getProperty("islock");
		if(ent.name=='funcDummyList' || ent.name=="appList"){
			checkObj.disabled = true;
		}else{
			checkObj.disabled = false;
			if(islock==1){
				checkObj.value = "取消级联授权";
			}else{
				checkObj.value = "级联授权";
			}
		}
	}
	
	function testModel(node){
		alert("#####");
	}
	/**
	* 菜单树
	**/
	function WTreeOnContextmenu(){
		this.select();
		_rtreemodel_showNodeMenu(this);
		return false
	}
	/**
	* 显示菜单树
	*/
	function _rtreemodel_showNodeMenu(F) {
		var D = F.getTree();
		if (D.beforeShowMenu && D.beforeShowMenu(F) === false)
			return;
		var H = F.entity.name, G=F.menuItems;
		if (G.length > 0) {
			for (var A = 0; A < G.length; A++) {
				if (A != 0) {
					var C = _get_top_window(), B = C.document.createElement("div");
					B.className = "rtree-popmenu-item-line";
					F.menu.insertItem(B)
				}
				F.menu.insertItem(G[A])
			}
			D.afterShowMenu && D.afterShowMenu(F, F.menu);
			F.menu.show();
		}
	}
	/**
	* 单击事件
	**/
	function _treemenuitem_onclick() {
		var A = this.parentNode.parentNode;
		removeClass(this, "rtree-popmenu-item-mouseover");
		A.hide();
		A.model.onmenuclick=new fireUserEvent(this.onclickFunction,[A]);
		A.style.backgroundColor = "";
		A.style.color = "black";
	}

	</script>
	<script type="text/javascript">
		STree.prototype.createHiddenData = function() {
		    var C = new StringBuffer();
		    for (entityType in this.datasetList) {
		        if (entityType == "root") continue;
		        var A = 1,
		        F = this.getDataset(entityType);
		        if (F == null) {
		            alert(entityType + STREE_ENTITY_NOT_EXIST);
		            return
		        }
		        var H = F.values,
		        I = F.getLength();
		        for (var B = 0; B < I; B++) {
		            var G = H[B].checked;
		            if (G == "1" || G == "2") {
		                var E = H[B].type,
		                D = this.getEntityInfo(E).submitXpath;
		                if (D != "") E = D;
		                C.append(_stree_create_hiddenStr(A, H[B], E));
		                A = A + 1
		            }
		        }
		    }
		    this.hidden.innerHTML = C
		};
	</script>
</head>
<body leftmargin="0" topmargin="0" style="overflow: auto;">
    <table width="100%" height="100%">
      <tr>
        <td>
        <table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
			<tr height="4%">
				<td class="eos-panel-title">&nbsp;&nbsp;<%=privilegeTree%></td>
			</tr>
			<tr>
			<td width="25%" height="100%" valign="top" class="eos-panel-body">
	    	<h:hidden property="acRole.roleId"/><br>&nbsp;&nbsp;
	        <%if(!"1".equalsIgnoreCase(roleReadonly)){%>
	        <input class="button" type="button" value="<b:message key="l_save"/>" onclick="addPrivilege();">
	        <input class="button" type="button" id="checkObj" value="级联授权" onclick="checkObj(this);" disabled="disabled">
	        <input class="button" type="button" id="initTreeClock" value="测试" onclick="testModel();" style="display: none;">
	        <%}%>
	        <w:tree id="abframe" hasCheckBox="true" checkBoxType="associated" hasRoot="false" width="100%">
	            <w:treeRoot display="<%=privilegeTree %>"/>
	            <w:treeNode nodeType="app" submitXpath="app" showField="appName" xpath="appList" icon="/common/images/icons/application.gif">
	                <w:treeRelation parentNodeType="root" field="appId"/>
	            </w:treeNode>
	            <w:treeNode nodeType="funcGroup" submitXpath="funcGroup"  showField="funcGroupName" xpath="funcGroupList" icon="/common/images/icons/brickss.gif">
	                <w:treeRelation parentNodeType="app"  field="appId" parentField="appId" />    
	                <w:treeCheckbox field="funcGroupId" checkedXpath="acRoleFuncGroupList" checkedField="funcGroupId"/>        
	            </w:treeNode>         
	            <w:treeNode nodeType="subFuncGroup" submitXpath="subFuncGroup" showField="funcGroupName" xpath="subFuncGroupList" icon="/common/images/icons/brickss.gif">
	                <w:treeRelation parentNodeType="funcGroup"  field="parentGroup" parentField="funcGroupId" />
	                <w:treeRelation parentNodeType="subFuncGroup"  field="parentGroup" parentField="funcGroupId" />
	                <w:treeCheckbox field="funcGroupId" checkedXpath="acRoleFuncGroupList" checkedField="funcGroupId"/>
	            </w:treeNode>
	            <w:treeNode nodeType="func" submitXpath="func" showField="funcName" xpath="funcList" icon="/common/images/icons/application_form.gif">
	            	<w:treeRelation parentNodeType="funcGroup"  field="funcGroupId" parentField="funcGroupId" />
	                <w:treeRelation parentNodeType="subFuncGroup"  field="funcGroupId" parentField="funcGroupId" />
	                <w:treeCheckbox field="funcCode" checkedXpath="acRoleFuncList" checkedField="funcCode"/>
	            </w:treeNode>
	        </w:tree><br>&nbsp;&nbsp;
	        <h:hidden property="roleReadonly"/>
	        <%if(!"1".equalsIgnoreCase(roleReadonly)){%>
	        <input class="button" type="button" value="<b:message key="l_save"/>" onclick="addPrivilege();"><br>
	        <%}%>
       		</td>
			</tr>
	    </table>
        </td>
    </tr>
  </table>
</body>
</html>
<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
    //初始化树上锁操作
     initTreeClock();
</script>
 