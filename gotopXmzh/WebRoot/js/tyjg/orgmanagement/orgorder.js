function openOrderWin(){
	var orgid = document.getElementById("tomorganization.orgId").value;
	var strUrl = "/orgmanagement/organizationAction_queryOrgOrder.action?tomorganization.orgId="+orgid+"&time="+new Date().getTime();
	showModalCenter(strUrl,null,callBackOrder,300,450,'机构排序');
}

function callBackOrder(values){
	var formObj = document.getElementById("viewlist1");
	formObj.submit();
}
function up(obj,but){
	but.disabled=true;
	var options = obj.options;
	for(var i=1;i<options.length;i++){
		if(options[i].selected){
			obj.options[i].swapNode(obj.options[i-1]);//向上移动
		}
	}
	but.disabled=false;
}
function down(obj,but){
	but.disabled=true;
	var options = obj.options;
	for(var i=options.length-2;i>=0;i--){
		if(options[i].selected){
			obj.options[i].swapNode(obj.options[i+1]);//向上移动
		}
	}
	but.disabled=false;
}
function saveOrder(obj,but){
	var options = obj.options;
	var params = "";
	var n = (""+options.length).length;
	var parentOrgId = document.getElementById("tomorganization.orgId").value;
	for(var i=0;i<options.length;i++){
		var pad = i < Math.pow(10, n-1) ? new Array(n-(''+i).length+1).join('0') + i : i;
   		//submitXml += "<DOGroup __type='sdo:org.gocom.abframe.datasetExp.organization.OmOrganization'><orgid>"+options[i].value+"</orgid><displayorder>"+pad+"</displayorder><parentorgid><%=(String)request.getAttribute("iOrgid") %></parentorgid></DOGroup>";
		if(i==0){
			params +=options[i].value+","+pad+","+parentOrgId;
		}else{
			params +="~#"+options[i].value+","+pad+","+parentOrgId;
		}
	}
	var myAjax = new Ajax("/orgmanagement/organizationAction_updateChildOrgOrder.action");
	myAjax.addParam("orginfo",params);
	myAjax.submit();
	var returnValue = myAjax.getValue("/root/data/falg");
	if(returnValue){
		alert("机构顺序保存成功!");
		window.closeD();
	}else{
		alert("机构顺序保存失败!");
	}
	
	
}