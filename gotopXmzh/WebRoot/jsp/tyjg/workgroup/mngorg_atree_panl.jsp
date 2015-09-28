<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp" %>
<html>
<!-- 
  - Author(s): lz
  - Date: 2010-05-06 17:18:08
  - Description:
-->
<head>
<title>机构树弹出窗口demo</title>
</head>
<%
		String showSelBoxStr = (String)request.getAttribute("showSelBox");
		String orgTypeStr = (String)request.getAttribute("orgType");
		String posiTypeStr = (String)request.getAttribute("posiType");
		String lookupTypeStr = (String)request.getAttribute("lookupType");
		String checkcountStr = (String)request.getAttribute("checkcount");
		String checkTitle = (String)request.getAttribute("checkTitle");
		String showTabOrg = (String)request.getAttribute("showTabOrg");
		String showTabGroup = (String)request.getAttribute("showTabGroup");
		String showTabRole = (String)request.getAttribute("showTabRole");
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
		String tdSelRole = "none";

		if(showSelBox==4 || showSelBox==(4+3)
			             || showSelBox==(3+4)
				         || showSelBox==(2+3+4)){
			tdSelOrg="";
			tdSelRole="";
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
		
		int tdSelEmpSize = 22;
		int tdSelPosiSize = 10;
		
		boolean tabOrg = false;
		boolean tabGroup = false;
		boolean tabRole = false;
		if("1".equals(showTabOrg)){
			tabOrg = true;
		}
		if("1".equals(showTabGroup)){
			tabGroup = true;
		}
		if("1".equals(showTabRole)){
			tabRole = true;
		}
		if(!tabOrg && !tabGroup && !tabRole){
			tabOrg=true;
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
			if(lookupTypeStr==2){
				selObj = $id("relatedSel");
				butObj = $id("mngemps");
			}alert(selObj);
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
			var selObj = $id("relatedSel");
			if(selObj!=null && selObj.options.length>0){
				for (var i = selObj.options.length-1; i >= 0; i--) {
					var soption = selObj.options[i];
					var entity = null;
					if(soption.selType!='oaRole'){
						var pNode = soption.treeParentNode
						entity = soption.treeNode.entity;
						if(pNode){
							var orgType = getOrgTypeByEntity(pNode);
							if(orgType==4){
								soption.treeNode.entity.setProperty("parentid",pNode.entity.getProperty("orgid"));
								soption.treeNode.entity.setProperty("parentmappid",pNode.entity.getProperty("ysjgbh"));
								soption.treeNode.entity.setProperty("parentcode",pNode.entity.getProperty("orgcode"));
							}
						}
					}else{
						entity = new Entity(soption.selType);
						entity.setProperty("roleid",soption.value);
						entity.setProperty("rolename",soption.showText);
						if(soption.showText==null){
						entity.setProperty("rolename",soption.treeNode.entity.getProperty("rolename"));
						}
					}
					if(sorgidArra[entity.name]){}else{
						sorgidArra[entity.name]=[];
					}
					sorgidArra[entity.name].push(entity);
				}
			}
			window.returnValue = sorgidArra;
		}
	window.closeD();
	
	}
    function getOrgTypeByEntity(sNode){
    	var name = sNode.entity.name;
    	if(name=='oaOrg'){
	    	return 4;
    	}else if(name=='oaGroup'){
    		return 3;
    	}else if(name=='oaEmp'){
    		return 2;
    	}
    }
    
	function deleteOption(selObj,index){
		if(index>=0){}else{
			index = selObj.options.selectedIndex;
		}
		if(index>=0){
			var option = selObj.options[index];
			if(option.selType=='oaEmp' || option.selType=='oaOrg' || option.selType=='oaGroup'|| option.selType=='oaRole'){
				if(selObj.options[index].treeNode){
					selObj.options[index].treeNode.changeCheckboxState();
				}
				selObj.options[index]=null;
			}else{
				var row = option.row;
				if(row){
					selObj.options[index].row.reverseSelect();
				}else{
					selObj.options[index]=null;
				}
			}
		}
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
		var selObj = $id("selRole");
		if(selObj){
			singleDeleteOption(selObj);
		}
	}
	function singleDeleteOption(selObj){
		for (var i = selObj.options.length-1; i >= 0; i--) {
			if(selObj.options[i].selected){
				deleteOption(selObj,i);
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
		var selObj = $id("oaGroup");
		if(selObj){
			deleteOptionOfAll(selObj);
		}
	}
	function deleteOptionOfAll(selObj){
		for (var i = selObj.options.length-1; i >= 0; i--) {
			deleteOption(selObj,i);
		}
	}
</script>
<body topmargin="0" leftmargin="0" scroll="no">
	<table align="left" border="0" height="100%" width="100%" style="vertical-align: top;">
		<tr valign="top">
			<td width="100%" valign="top">
				<w:tabPanel id="tabs1">
					<%if(tabOrg){%>
					<w:tabPage cache="true" tabType="iframe" id="modifySelfOrg" title="组织机构" url="org.gocom.abframe.tools.blank_space.flow">
					</w:tabPage>
					<%}%>
					<%if(tabGroup){%>
					<w:tabPage cache="true" tabType="iframe" id="modifySelfGroup" title="群组" url="org.gocom.abframe.tools.blank_space.flow">
					</w:tabPage>
					<%}%>
					<%if(tabRole){%>
					<w:tabPage  cache="true" tabType="iframe" id="modifySelfRole" title="业务角色" url="org.gocom.abframe.tools.blank_space.flow">
					</w:tabPage>
					<%}%>
				</w:tabPanel>
			</td>
			<td>
				<table>
					<tr>
						<td>
							<table>
								<tr id="tdSelEmp">
									<td>
										已选项:
										<SELECT style="width: 162px;" size=21 name="relatedSel" id="relatedSel" multiple="multiple" ondblclick="deleteOption(this)">
										</SELECT>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" id="rtnSelectBut" value="确 定" onclick="javascript:rtnSelectVal();" style="font-weight: bold;"/>
							<input type="button" id="deleteSelectedBut" value="移除" onclick="batchDeleteOption();" title="返回已选内容,并关闭窗口"/>
							<input type="button" id="deleteSelAllBut" value="清空" onclick="allDeleteOption();" title="返回已选内容,并关闭窗口"/>
							<input type="button" id="rtnSelectButBack" value="重置" onclick="location.reload();"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="mngemps" name="mngemps" entityName="oaEmp"  entityProperty="empid" style="display: none;" displayProperty="empname"
		relatedSelID="relatedSel" displayOtherProperty="empcode" checkcount="<%ForUtil.outPm(out,request,"checkcount");%>";/>
	<input type="hidden" id="mngroups" name="mngroups" entityName="oaGroup" entityProperty="groupid" style="display: none;" displayProperty="groupname"
		relatedSelID="relatedSel" displayOtherProperty="groupid" checkcount="<%ForUtil.outPm(out,request,"checkcount");%>";/>
	<input type="hidden" id="mngorgs" name="mngorgs" entityName="oaOrg" entityProperty="orgid" style="display: none;" displayProperty="orgname"
		relatedSelID="relatedSel" displayOtherProperty="orgid,orgcode" checkcount="<%ForUtil.outPm(out,request,"checkcount");%>";/>
    <input type="hidden" id="mngroles" name="mngroles" entityName="oaRole"  entityProperty="roleid" style="display: none;" displayProperty="rolename"
		relatedSelID="relatedSel" displayOtherProperty="roleid" checkcount="<%ForUtil.outPm(out,request,"checkcount");%>"/>
<script type="text/javascript">
	function _rteenode_init_addOptions(){
		var dialogArguments = window["dialogArguments"];
		var oOption = null;
		var selObj = $id("relatedSel");
		var mngHidden = {};
		mngHidden["oaEmp"] = "mngemps";
		mngHidden["oaGroup"] = "mngroups";
		mngHidden["oaOrg"] = "mngorgs";
		mngHidden["oaRole"] = "mngroles";
		for(var i=0;i<dialogArguments.length;i++){
			var peArgument = dialogArguments[i];
			var arraEntity = peArgument[0].arraEntity;
			var displayProperty = peArgument[1];
			var displayOtherProperty = peArgument[1];
			var entityProperty = peArgument[2];
		    var args = Array.prototype.slice.call(peArgument,2);//截取从第三个参数开始的所有参数
			if(arraEntity.length>0){
				var aname = arraEntity[0].name;
				var infoObj = $id(mngHidden[aname]);
				if(infoObj){}else{
					continue;
				}
				if(entityProperty){
					infoObj.entityProperty = entityProperty;
				}else{
					entityProperty = infoObj.entityProperty;
				}
				if(displayProperty){
					infoObj.displayProperty = displayProperty;
				}else{
					displayProperty = infoObj.displayProperty;
				}
			    if(args && args.length>0){
			    	infoObj.displayOtherProperty = args.toString();
			    }else{
			    	args = [];
					args[0] = infoObj.displayOtherProperty;
			    }
				for(var j=0;j<arraEntity.length;j++){
					var entity = arraEntity[j];
					var pstr = entity.getProperty(displayProperty);
					if(pstr){}else{
						continue;
					}
					oOption = document.createElement("OPTION");
					oOption.selType=entity.name;
					selObj.options.add(oOption);
					var titlestr = "";
					if(args && args.length>0){
						var dopara = [];
						for(var k=0;k<args.length;k++){
							dopara.push(entity.getProperty(args[k]));
						}
						titlestr += "("+dopara.join(",")+")";
					}else if(displayProperty){
						titlestr = entity.getProperty(displayOtherProperty);
					}
					var valueStr = entity.getProperty(entityProperty);
					var showText = pstr;
					if(oOption.selType == "oaEmp"){
						pstr += "（人员）";
					}
					if(oOption.selType == "oaOrg"){
						pstr += "（机构）";
					}
					if(oOption.selType == "oaRole"){
						pstr += "（角色）";
					}
					if(oOption.selType == "oaGroup"){
						pstr += "（群组）";
					}
					oOption.innerText = pstr;
					oOption.showText = showText;
					oOption.title = titlestr;
					oOption.value = valueStr;
					if(oOption.selType == 'oaEmp' || oOption.selType == 'oaOrg' || oOption.selType == 'oaGroup' || oOption.selType == 'oaRole'){
						oOption.treeNode={};
						oOption.treeNode.entity=entity;	
						oOption.treeNode.changeCheckboxState=function(){};
					}
				}	
			}	
		}
	}
	function selectInitFun(){
		_rteenode_init_addOptions();
		var search = window.location.search;
		var modifySelfOrg = $id("modifySelfOrg");
		var modifySelfGroup = $id("modifySelfGroup");
		var modifySelfRole = $id("modifySelfRole");
		if(modifySelfOrg){
			modifySelfOrg.src="org.gocom.abframe.org.publicom.mngorgAssignTree1.flow"+search;
			modifySelfOrg.reload();
		}
		if(modifySelfGroup){
			modifySelfGroup.src="org.gocom.abframe.org.publicom.mnggroup_atree.flow"+search;
			if(modifySelfOrg){}else{
				modifySelfGroup.reload();
			}
		}
		if(modifySelfRole){
			modifySelfRole.src="org.gocom.abframe.org.publicom.party_maintain_role.flow"+search;
			if(modifySelfOrg || modifySelfGroup){}else{
				modifySelfRole.reload();
			}
		}
	}
	function custInit(){
		selectInitFun();
		initButtonStyle();
	}
	eventManager.add(window,"load",custInit);
</script>
</body>
</html>