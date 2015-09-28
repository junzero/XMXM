/**
*获取机构的首拼音
*/
function realnameFun(obj){
	var myAjax = new Ajax("/orgmanagement/organizationAction_tohypyInitial.action");
	myAjax.addParam("orgname",obj.value);
	myAjax.submit();
	var rtun = myAjax.getValue("root/data/py");
	if(rtun != null){
		document.getElementById("tomorganization.realName").value=rtun;
	}
}
/**
  * 查看是否重复
  */
function checkOrgCode(obj){
  	if(obj.value){}else{return;}
  	var myAjax = new Ajax("/orgmanagement/organizationAction_checkOrgCode.action");
  	myAjax.addParam("orgcode",obj.value);
	myAjax.submit();
	var returnNode =myAjax.getValue("root/data/orgCode");
 	if(parseInt(returnNode)>=1){
 		return false;
 	}
 	return true;
}
/**
*新增机构
*/
function checkForm(frm){
	if(!checkOrgCode(document.getElementById("tomorganization.orgCode"))){
		alert("单位代码已存在，请重新输入");	
		return false;
	}
	if(document.getElementById("tomorganization.orgName").value==""){
		alert("机构名称不能为空！");
		return false;
	}
	return true;
}
 /**
    * 隐藏域
    */
    function isDisplay(OPObj,checkOpr) {	
	   var flag = checkOpr.checked;
	   document.getElementById(OPObj).style.display = flag?"":"none";	    	   
	}