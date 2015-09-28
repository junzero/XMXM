<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      树主页面
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  	<script type="text/javascript">
	var lookupTypeStr = '<%ForUtil.outPm(out,request,"changeTree.lookupTypeStr");%>';
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
			selObj = $id("relatedSel");
			if(lookupTypeStr==2){
				butObj = $id("mngemps");
			}
			if(lookupTypeStr==4){
				butObj = $id("mngorgs");
			}
			if(lookupTypeStr==5){
				butObj = $id("mngroles");
			}
			if(lookupTypeStr==6){
				butObj = $id("mngroups");
			}
			if(selObj!=null && selObj.options.length>0){
					//for (var i = selObj.options.length-1; i >= 0; i--) {
					//20140905 选择人员时按照手选的顺序返回
					for (var i = 0;i<=selObj.options.length-1;  i++) {
					var soption = selObj.options[i];
					var NEntity = soption.treeNode.entity;
					sorgidArra[0].push(NEntity.getProperty(butObj.getAttribute("entityProperty")));
					sorgidArra[1].push(NEntity.getProperty(butObj.getAttribute("displayProperty")));
					if(lookupTypeStr==4){
						sorgidArra[2].push(NEntity.getProperty("orgcode"));
					}
				}
			}
			
			window.returnValue = sorgidArra;
		}else{
			var sorgidArra = {};
			var selObj = $id("relatedSel");
			if(selObj!=null && selObj.options.length>0){
				//for (var i = selObj.options.length-1; i >= 0; i--) {
				//20140905 选择人员时按照手选的顺序返回
				for (var i = 0;i<=selObj.options.length-1;  i++) {
					var soption = selObj.options[i];
					var entity = null;
					if(soption.selType!='Role'){
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
    	if(name=='Organization'){
	    	return 4;
    	}else if(name=='Group'){
    		return 3;
    	}else if(name=='Employee'){
    		return 2;
    	}
    }
    
	function deleteOption(selObj,index){
		if(index>=0){}else{
			index = selObj.options.selectedIndex;
		}
		if(index>=0){
			var option = selObj.options[index];
			if(option.selType=='Employee' || option.selType=='Organization' || option.selType=='Group'){
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
					<l:equal value="${changeTree.tabOrg}" targetValue="true">
						<w:tabPage cache="true" tabType="iframe" id="modifySelfOrg" title="组织机构" url="/jsp/tyjg/common/blankk.jsp">
						</w:tabPage>
					</l:equal>
					<l:equal value="${changeTree.tabGroup}" targetValue="true">
						<w:tabPage cache="true" tabType="iframe" id="modifySelfGroup" title="群组" url="/jsp/tyjg/common/blankk.jsp">
						</w:tabPage>
					</l:equal>
					<l:equal  value="${changeTree.tabRole}" targetValue="true">
						<w:tabPage  cache="true" tabType="iframe" id="modifySelfRole" title="业务角色" url="/jsp/tyjg/common/blankk.jsp">
						</w:tabPage>
					</l:equal>
					<l:equal  value="${changeTree.tabPositionEmp}" targetValue="true">
						<w:tabPage  cache="true" tabType="iframe" id="modifySelfPositionEmp" title="岗位人员" url="/jsp/tyjg/common/blankk.jsp">
						</w:tabPage>
					</l:equal>
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
										<SELECT style="width: 200px;" size=21 name="relatedSel" id="relatedSel" multiple="multiple" ondblclick="deleteOption(this)">
										</SELECT>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" class="button" id="rtnSelectBut" value="确 定" onclick="javascript:rtnSelectVal();" style="font-weight: bold;"/>
							<input type="button" class="button" id="deleteSelectedBut" value="移除" onclick="batchDeleteOption();" title="返回已选内容,并关闭窗口"/>
							<input type="button" class="button" id="deleteSelAllBut" value="清空" onclick="allDeleteOption();" title="返回已选内容,并关闭窗口"/>
							<input type="button" class="button" id="rtnSelectButBack" value="重置" onclick="location.reload();"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="mngemps" name="mngemps" entityName="Employee"  entityProperty="empid" style="display: none;" displayProperty="empname"
		relatedSelID="relatedSel" displayOtherProperty="empcode" checkcount="${changeTree.checkcount}"/>
	<input type="hidden" id="mngroups" name="mngroups" entityName="Group" entityProperty="groupid" style="display: none;" displayProperty="groupname"
		relatedSelID="relatedSel" displayOtherProperty="groupid" checkcount="${changeTree.checkcount}";/>
	<input type="hidden" id="mngorgs" name="mngorgs" entityName="Organization" entityProperty="orgid" style="display: none;" displayProperty="orgname"
		relatedSelID="relatedSel" displayOtherProperty="orgid,orgcode" checkcount="${changeTree.checkcount}";/>
    <input type="hidden" id="mngroles" name="mngroles" entityName="Role"  entityProperty="roleid" style="display: none;" displayProperty="rolename"
		relatedSelID="relatedSel" displayOtherProperty="roleid" checkcount="${changeTree.checkcount}"/>
<h:script src="/common/skins/default/scripts/common.js" />
<script type="text/javascript">
	function lookupInit(){
		var dialogArguments = window["dialogArguments"];
		if(lookupTypeStr){
	    	var peArgument = [];
			if(lookupTypeStr==2){
		    	var paramEntity = new ParamEntity('Employee');
				paramEntity.setProperty('empid',dialogArguments[0]);
				paramEntity.setProperty('empname',dialogArguments[1]);
				peArgument[0]=[paramEntity,'empname','empid'];
			}
			if(lookupTypeStr==3){
		    	var paramEntity = new ParamEntity('oaPosi');
				paramEntity.setProperty('positionid',dialogArguments[0]);
				paramEntity.setProperty('posiname',dialogArguments[1]);
				peArgument[0]=[paramEntity,'posiname','positionid'];
			}
			if(lookupTypeStr==4){
		    	var paramEntity = new ParamEntity('Organization');
				paramEntity.setProperty('orgid',dialogArguments[0]);
				paramEntity.setProperty('orgname',dialogArguments[1]);
				peArgument[0]=[paramEntity,'orgname','orgid'];
			}
			if(lookupTypeStr==5){
		    	var paramEntity = new ParamEntity('Role');
				paramEntity.setProperty('roleid',dialogArguments[0]);
				paramEntity.setProperty('rolename',dialogArguments[1]);
				peArgument[0]=[paramEntity,'rolename','roleid'];
			}
			if(lookupTypeStr==6){
		    	var paramEntity = new ParamEntity('Group');
				paramEntity.setProperty('groupid',dialogArguments[0]);
				paramEntity.setProperty('groupname',dialogArguments[1]);
				peArgument[0]=[paramEntity,'groupname','groupid'];
			}
			window["dialogArguments"]=peArgument;
		}
	}
	function _rteenode_init_addOptions(){
		lookupInit();
		var dialogArguments = window["dialogArguments"];
		var oOption = null;
		var selObj = $id("relatedSel");
		var mngHidden = {};
		mngHidden["Employee"] = "mngemps";
		mngHidden["Organization"] = "mngorgs";
		mngHidden["Role"] = "mngroles";
		mngHidden["Group"] = "mngroups";
		for(var i=0;i<dialogArguments.length;i++){
			var peArgument = dialogArguments[i];
			if(peArgument){}else{
				continue;
			}
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
					infoObj.setAttribute("entityProperty", entityProperty);
				}else{
					entityProperty = infoObj.getAttribute("entityProperty");
				}
				if(displayProperty){
					infoObj.setAttribute("displayProperty",displayProperty);
				}else{
					displayProperty = infoObj.getAttribute("displayProperty");
				}
			    if(args && args.length>0){
			    	infoObj.setAttribute("displayOtherProperty", args.toString());
			    }else{
			    	args = [];
					args[0] = infoObj.getAttribute("displayOtherProperty");
			    }
				for(var j=0;j<arraEntity.length;j++){
					var entity = arraEntity[j];
					var pstr = entity.getProperty(displayProperty);
					if(pstr){}else{
						continue;
					}
					oOption = document.createElement("OPTION");
					oOption.setAttribute("selType",entity.name);
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
					var selType = oOption.getAttribute("selType");
					if(selType == "Employee"){
						pstr += "（人员）";
					}
					if(selType == "Organization"){
						pstr += "（机构）";
					}
					if(selType == "Role"){
						pstr += "（角色）";
					}
					if(selType == "Group"){
						pstr += "（群组）";
					}
					oOption.innerText = pstr;
					oOption.setAttribute("showText",showText);
					oOption.title = titlestr;
					oOption.value = valueStr;
					if(selType == 'Employee' || selType == 'Organization' || selType == 'Group'){
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
		var modifySelfPositionEmp = $id("modifySelfPositionEmp");
		if(modifySelfOrg){
			modifySelfOrg.src="/tree/isAdminOrBusessTree_mainTree.action"+search;
			modifySelfOrg.reload();
		}
		if(modifySelfPositionEmp){
			modifySelfPositionEmp.src="/tree/initPositionEmp_mainTree.action"+search;
			modifySelfPositionEmp.reload();
		}
		if(modifySelfGroup){
			modifySelfGroup.src="/tree/initGroupTree_mainTree.action"+search;
			if(modifySelfOrg){}else{
				modifySelfGroup.reload();
			}
		}
		if(modifySelfRole){
			modifySelfRole.src="/tree/initBusessRole_mainTree.action"+search;
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
