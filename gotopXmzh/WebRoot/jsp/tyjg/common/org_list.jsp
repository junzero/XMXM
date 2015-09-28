<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      机构选择
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<h:script src="/common/gotop/datacellCheckBox.js"></h:script><!-- 引用datacell单选函数 -->
	<h:script src="/common/gotop/lookup-group1.js"/>
  </head>
 <script type="text/javascript">
	function custInit(){
	      var empid = window.parent.$id("empid").value;
		  var empname = window.parent.$id("empname").value;
		  var orgIds = window.parent.$id("dynamicOrgIds").value;
		 $.ajax({
		        url: '/tree/selectOrgList_mainTree.action',
		        async: false,
		        type: 'post',
		        data: "orgIds="+orgIds,
		        dataType: 'json',
		        timeout: 60000,
		        success: function (files) {
			        if(files!=null){
				        if(files.length == 1){
				        	$.each(files,function( i,item ){
				    	        $("#personList").append("<tr><td><input type='checkbox' checked='checked' onClick='javascript:checkPerson(this,"+item.empid+");'/></td><td style='display:none'>"+item.empid+"</td><td style='display:none'>"+item.empname+"</td><td>"+item.orgname+"</td></tr>");
				          		});	
						 }else{
							 $.each(files,function( i,item ){
					    	        $("#personList").append("<tr><td><input type='checkbox' onClick='javascript:checkPerson(this,"+item.empid+");'/><input type='hidden' value='"+item.empid+"'></td><td style='display:none'>"+item.empname+"</td><td>"+item.orgname+"</td></tr>");
					          		});	
						}
			        }
		        }
	    });	
	}

	function checkPerson(check,empid){
		var selObj = window.parent.$id("relatedSel");
		var tr = check.parentNode.parentNode;  
        var tds = tr.cells;
		var s = check.checked;
		
		if(s == false){
			for (var i = selObj.options.length-1; i >= 0; i--) {
				if(selObj.options[i].value == empid){
					selObj.options[i] = null;
				}
					 
			}
		}else{
			var oOption = null; 
			var ee = empid.toString();
			var peArgument = [];
			var paramEntity = new ParamEntity('Employee');
			paramEntity.setProperty('empid',ee);
			paramEntity.setProperty('empname',tds[1].innerText);
			peArgument[0]=[paramEntity,'empname','empid']; 
		
			var ss = peArgument[0];
			var arraEntity = ss[0].arraEntity;
			var displayProperty = ss[1];
			var displayOtherProperty = ss[1];
			var entityProperty = ss[2];

			var entity = arraEntity[0];
			oOption = window.parent.document.createElement("OPTION");
			oOption.setAttribute("selType",entity.name);
			selObj.options.add(oOption);
			
			var valueStr = entity.getProperty(entityProperty);
			var pstr = entity.getProperty(displayProperty);
			
			var selType = oOption.getAttribute("selType");
			oOption.innerText = pstr;
			oOption.setAttribute("showText",pstr);
			oOption.value = valueStr;
			if(selType == 'Employee'){
				oOption.treeNode={};
				oOption.treeNode.entity=entity;	
				oOption.treeNode.changeCheckboxState=function(){};
			}
		}
	}
	//加载页面初始化
	eventManager.add(window,"load",custInit);
  </script>
<body topmargin="0" leftmargin="0" rightmargin="0">
<table align="center" border="0" width="100%" class="EOS_table" id="personTable">
    <tbody id="personList" >
    </tbody>
</table>

  </body>
</html>
