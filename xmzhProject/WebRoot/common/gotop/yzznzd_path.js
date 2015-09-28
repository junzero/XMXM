/**
* 删除后提示
**/
Datacell.prototype.afterDelMess = function(A) {
//	alert("请点击【保存】按钮进行提交");
};
/**
* 提交之后验证
**/
Datacell.prototype.afterSubmit = function(ajax){
	if(ajax.isFailureState==false && ajax.sumbitType==3){
//		alert('保存成功！');
	}
}
/**
* 使用datacell checkbox处于单选模式
**/
function _lookUp_selectRadioFunc(cgp){
	var csRows = cgp.getSelectRows();
	for(var i=0;i<csRows.length;i++){
		var row = csRows[i];
		row.reverseSelect();
	}
}