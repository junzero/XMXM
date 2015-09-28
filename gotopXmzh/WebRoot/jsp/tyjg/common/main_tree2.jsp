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
	//重选功能
	function reSelect(){
		//获取目标节点名称
		var destinationName = parent.window.document.getElementById("destinationName").value;
		//获取开始节点名称
		var beforeName = parent.window.document.getElementById("beforeName").value;
		var executionId = parent.window.document.getElementById("executionId").value;
		var beginOrg = parent.window.document.getElementById("beginOrg").value;
		var beginAssingee = parent.window.document.getElementById("beginAssingee").value;
		var dynamicOrgIds = parent.window.document.getElementById("dynamicOrgIds").value;
		var definitionId = parent.window.document.getElementById("definitionId").value;
		var taskAssingee = parent.window.document.getElementById("taskAssingee").value;
		
		var url=encodeURI("/jbpm/jbpmDemoAction_getNextTaskAssigneeConfig.action?taskAssgineeDto.executionId="+executionId + "&taskAssgineeDto.beforeName="+beforeName+"&taskAssgineeDto.targetName="+destinationName + "&taskAssgineeDto.beginOrg=" + beginOrg + "&taskAssgineeDto.beginAssingee=" + beginAssingee + "&taskAssgineeDto.dynamicOrgIds=" + dynamicOrgIds + "&taskAssgineeDto.definitionId="+definitionId +"&taskAssgineeDto.taskAssingee=" + taskAssingee + "&taskAssgineeDto.reSelectFlag=1");
		url=encodeURI(url);
		var myAjax = new Ajax(url);
		myAjax.submit();

		var rtun1 = myAjax.getValue("root/empIds");
		var rtun2 = myAjax.getValue("root/empNames");
		var rtun3 =  myAjax.getValue("root/taskType");
		var rtun4 =  myAjax.getValue("root/isEnd");
		var rtun5 =  myAjax.getValue("root/dynamicOrgIds");
		var rtun6 =  myAjax.getValue("root/taskExeConfigId");
		parent.window.document.getElementById("taskExeConfigId").value=rtun6;
		parent.window.document.getElementById("taskType").value=rtun3;
		var strUrl = "";
		parent.window.document.getElementById("isEnd").value=rtun4;
		parent.window.document.getElementById("taskType").value=rtun3;
		if(rtun3 != null){
			if("09" == rtun3){
				strUrl = "/tree/initMainTree2_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2&changeTree.positioncode=BMFZR";		
			}else{
				strUrl = "/tree/initMainTree2_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2&changeTree.lookupTypeStr=2";
				parent.window.document.getElementById("empIds").value = rtun1;
				parent.window.document.getElementById("empNames").value = rtun2;
			}
		}else{
			if("end" == rtun4){
				//最后节点
				strUrl = "/tree/initMainTree2_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6";
			}else{
				//节点没配置
				strUrl = "/tree/initMainTree2_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2";
			}
		}
		parent.window.document.getElementById("assignerTab").src=strUrl;
	}
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
			var empids ="";
			var empNames ="";
			if(selObj!=null && selObj.options.length>0){
					//for (var i = selObj.options.length-1; i >= 0; i--) {
					//20140905 选择人员时按照手选的顺序返回
					for (var i = 0;i<=selObj.options.length-1;  i++) {
					var soption = selObj.options[i];
					var NEntity = soption.treeNode.entity;
					sorgidArra[0].push(NEntity.getProperty(butObj.getAttribute("entityProperty")));
					sorgidArra[1].push(NEntity.getProperty(butObj.getAttribute("displayProperty")));
				}
			}
			empids = sorgidArra[0];
			empNames = sorgidArra[1];
			var transitionName = parent.window.document.getElementById("transitionName").value;
			var isEnd = parent.window.document.getElementById("isEnd").value;
			var taskExeConfigId = parent.window.document.getElementById("taskExeConfigId").value;
			//20140901 添加目标节点名称返回给业务
			var destinationName = parent.window.document.getElementById("destinationName").value;
			var str = "taskAssgineeDto.transitionName="+transitionName + "&taskAssgineeDto.targetName=" + destinationName + "&taskAssgineeDto.taskExeConfigId=" + taskExeConfigId;
			if(empids != ""&&empids != null){
				if(empids == "null"){
					alert('下个操作人没有配置，请联系管理员进行相关配置操作');
					location.reload();
				}else{
					str += "&taskAssgineeDto.empIds="+ empids + "&taskAssgineeDto.empNames="+ empNames;
					window.parent.returnValue = str;
					window.parent.close();
				}
			}else{
				if("end" == isEnd){
					empids="";
					empNames="";
					str += "&taskAssgineeDto.empIds="+ empids + "&taskAssgineeDto.empNames="+ empNames;
					window.parent.returnValue = str;
					window.parent.close();
				}else{
					var taskType = parent.window.document.getElementById("taskType").value;
					var empid = parent.window.document.getElementById("empIds").value;
					var empNames = parent.window.document.getElementById("empNames").value;
					if(taskType == "02" || taskType== "07"){
						//动态机构
						//主要负责人
						str += "&taskAssgineeDto.empIds="+ empid + "&taskAssgineeDto.empNames="+ empNames;
						window.parent.returnValue = str;
						window.parent.close();
					}else{
						alert("请选择人员");
						location.reload();
					}
					
				}
			}
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
			var empids;
			var empnames;
			if(sorgidArra['Employee']){ 
				var sorgidArras  = sorgidArra['Employee'].slice(0);//人员数组
				argRes=[[],[],[],[]];
				for(var i=0;i<sorgidArras.length;i++){
					argRes[0].push(sorgidArras[i].getProperty("empid"));
					argRes[1].push(sorgidArras[i].getProperty("empname"));
				}
				empids = argRes[0];
				empnames=argRes[1];
			}
			var transitionName = parent.window.document.getElementById("transitionName").value;
			//20140901 添加目标节点名称返回给业务
			var destinationName = parent.window.document.getElementById("destinationName").value;
			
			var taskExeConfigId = parent.window.document.getElementById("taskExeConfigId").value;
			var str = "taskAssgineeDto.transitionName="+transitionName + "&taskAssgineeDto.targetName=" + destinationName + "&taskAssgineeDto.taskExeConfigId=" + taskExeConfigId;;
			var isEnd = parent.window.document.getElementById("isEnd").value;
			if((empids != ""&&empids != null) || isEnd != null){
				if(empids == "null"){
					alert('下个操作人没有配置，请联系管理员进行相关配置操作');
					location.reload();
				}else{
				str += "&taskAssgineeDto.empIds="+ empids + "&taskAssgineeDto.empNames="+ empnames;
				window.parent.returnValue = str;
			 	window.parent.close();
				}
			}else{
				alert('请选择人员');
				location.reload();
			}
			
		 }
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
		var isPerson = $id("isPerson").value;
		if(isPerson == "true"){
			var s2 = document.frames('modifySelfOrg').document.getElementsByTagName('tbody')[0];
			var rowLength = s2.rows.length;  
			if(selObj.value == ""){
				 for (var i = 0; i < rowLength; i += 1) {  
				      var row = s2.rows[i];
				      s2 .removeChild(row);
				  }
			}else{
				 for (var i = 0; i < rowLength; i += 1) {  
				      var row = s2.rows[i];
				       var cellLength = row.cells.length;      
				       var cell = row.cells[1].innerHTML;
				       if(cell == selObj.value){
				    	  s2 .removeChild(row);
						}
				  }
			}
			 
		}
		
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
			<td width="50%" valign="top" id="ss">
				<w:tabPanel id="tabs1" >
					<l:equal value="${changeTree.tabOrg}" targetValue="true">
					<w:tabPage cache="true" tabType="iframe" id="modifySelfOrg" title="人员选择" url="/jsp/tyjg/common/blankk.jsp">
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
				</w:tabPanel>
			</td>
			<td width="50%" height="30">
			<table>
					<tr id="s2">
						<td>
							<table>
								<tr id="tdSelEmp">
									<td>
										已选项:
										<SELECT style="width: 200px; height: 270px;" size=21 name="relatedSel" id="relatedSel" multiple="multiple" ondblclick="deleteOption(this)">
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
							<input type="button" class="button" id="reSelectBut" value="重选" onclick="javascript:reSelect()"/>
							<input type="hidden" id="empid" value="">
							<input type="hidden" id="empname" value="">
							<input type="hidden" id="dynamicOrgIds" value="">
							<input type="hidden" id="isOrg" value="">
							<input type="hidden" id="isPerson" value="">
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
	function lookupInit(peArgument){
		//var dialogArguments = window["dialogArguments"];
		if(lookupTypeStr){
	    	var peArgument = [];
			if(lookupTypeStr==2){
		    	var paramEntity = new ParamEntity('Employee');
				paramEntity.setProperty('empid',peArgument[0]);
				paramEntity.setProperty('empname',peArgument[1]);
				peArgument[0]=[paramEntity,'empname','empid'];
			}
			if(lookupTypeStr==3){
		    	var paramEntity = new ParamEntity('oaPosi');
				paramEntity.setProperty('positionid',peArgument[0]);
				paramEntity.setProperty('posiname',peArgument[1]);
				peArgument[0]=[paramEntity,'posiname','positionid'];
			}
			if(lookupTypeStr==4){
		    	var paramEntity = new ParamEntity('Organization');
				paramEntity.setProperty('orgid',peArgument[0]);
				paramEntity.setProperty('orgname',peArgument[1]);
				peArgument[0]=[paramEntity,'orgname','orgid'];
			}
			if(lookupTypeStr==5){
		    	var paramEntity = new ParamEntity('Role');
				paramEntity.setProperty('roleid',peArgument[0]);
				paramEntity.setProperty('rolename',peArgument[1]);
				peArgument[0]=[paramEntity,'rolename','roleid'];
			}
			if(lookupTypeStr==6){
		    	var paramEntity = new ParamEntity('Group');
				paramEntity.setProperty('groupid',peArgument[0]);
				paramEntity.setProperty('groupname',peArgument[1]);
				peArgument[0]=[paramEntity,'groupname','groupid'];
			}
			window["dialogArguments"]=peArgument;
		}
	}
	function _rteenode_init_addOptions(peArgument){
		lookupInit(peArgument);
		//var dialogArguments = window["dialogArguments"];
		var dialogArguments = peArgument;
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
						//pstr += "（人员）";
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
	function selectInitFun(peArgument,strs){
		if(strs.length>1){

		}else{
			_rteenode_init_addOptions(peArgument);
		}
	
		var search = window.location.search;
		var modifySelfOrg = $id("modifySelfOrg");
		var modifySelfGroup = $id("modifySelfGroup");
		var modifySelfRole = $id("modifySelfRole");
		if(modifySelfOrg){
			var isEnd = parent.window.document.getElementById("isEnd").value;
			var taskType = parent.window.document.getElementById("taskType").value;
			var empid = parent.window.document.getElementById("empIds").value;
			var dynamicOrgIds = parent.window.document.getElementById("dynamicOrgIds").value;
			if(taskType != null && taskType != "null"){
				if(taskType == "02" || taskType == "07"){
					//动态机构
					//主要负责人
					$("#ss").hide();
					$("#s2").hide();
					$("#deleteSelectedBut").hide();
					$("#deleteSelAllBut").hide();
					$("#rtnSelectButBack").hide();
					$("#reSelectBut").hide();
				}
				 if(taskType == "08"){
					//办理部室 回退  选择部门
					$.ajax({
				        url: "/tree/checkEmployee_mainTree.action",
				        async: false,
				        type: 'post',
				        data: "empid="+empid,
				        dataType: 'text',
				        timeout: 60000,
				        success: function (data) {
					     if(data == "success"){
					    		modifySelfOrg.src="/tree/initOrgList_mainTree.action"+search;
					    		$id("isOrg").value="true";
								modifySelfOrg.reload(); 
						    }else if(data == "fails"){
						    	modifySelfOrg.src="/tree/isAdminOrBusessTree_mainTree.action"+search;
								modifySelfOrg.reload(); 
							}
				        }
					});
				}
				 if(taskType == "09"){
					//部门会签
					 modifySelfOrg.src="/tree/isAdminOrBusessTree_mainTree.action"+search;
					 modifySelfOrg.reload();
					}
				else{
				if(empid == "null"){
						modifySelfOrg.src="/tree/isAdminOrBusessTree_mainTree.action"+search;
								modifySelfOrg.reload(); 
				}else{
				$.ajax({
				        url: "/tree/checkEmployee_mainTree.action",
				        async: false,
				        type: 'post',
				        data: "empid="+empid,
				        dataType: 'text',
				        timeout: 60000,
				        success: function (data) {
					     if(data == "success"){
					    		modifySelfOrg.src="/tree/initPersonList_mainTree.action"+search;
					    		$id("isPerson").value="true";
								modifySelfOrg.reload(); 
						    }else if(data == "fails"){
						    
						    	modifySelfOrg.src="/tree/isAdminOrBusessTree_mainTree.action"+search;
								modifySelfOrg.reload(); 
							}
				        }
					});
				}
				}
			}else{
				if("end" == isEnd){
					$("#ss").hide();
					$("#s2").hide();
					$("#deleteSelectedBut").hide();
					$("#deleteSelAllBut").hide();
					$("#rtnSelectButBack").hide();
					$("#reSelectBut").hide();
					
				}else{
					modifySelfOrg.src="/tree/isAdminOrBusessTree_mainTree.action"+search;
					modifySelfOrg.reload(); 
				}
			}
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
		var empid = parent.window.document.getElementById("empIds").value;
		var empname = parent.window.document.getElementById("empNames").value;
		var dynamicOrgIds = parent.window.document.getElementById("dynamicOrgIds").value;
		$id("empid").value=empid;
		$id("empname").value=empname;
		$id("dynamicOrgIds").value=dynamicOrgIds
		var strs= new Array(); 
		strs=empid.split(","); 
			if(empid == "null"){
			empid = "";
			empname ="";
		} 
			var peArgument = [];
			//人员
	    	var paramEntity = new ParamEntity('Employee');
			paramEntity.setProperty('empid',empid);
			paramEntity.setProperty('empname',empname);
			peArgument[0]=[paramEntity,'empname','empid']; 
			selectInitFun(peArgument,strs);
	
		//initButtonStyle();
	}
	eventManager.add(window,"load",custInit);
</script>
  </body>
</html>
