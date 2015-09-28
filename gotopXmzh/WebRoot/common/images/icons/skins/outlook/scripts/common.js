/**
 * 功能：检查两个日期控件生成的日期大小
 * 参数：arg1-被比较值
 *		arg2-比较值
 *		fmt-日期格式
 * 返回值：0-两个日期相等
 *		1-第一个日期大于第二个日期
 *		2-第一个日期小于第二个日期
 *		-1-某个参数不是一个日期
 *		-2-发生了其他的异常
*/ 
function f_compare_date(arg1,arg2,fmt){
	try{
		if((!isDate(arg1,fmt))||(!isDate(arg2,fmt))){
			return -1;
		}
		var v1 = stringToDate(arg1,fmt);
		var v2 = stringToDate(arg2,fmt);
		if(v1==v2){
			return 0;
		}
		if(v1>v2){
			return 1;
		}
		if(v1<v2){
			return 2;
		}    		
	}catch(e){
		return -2;
	}
}
/*
 *  实现全选复选框
 *  param0: 复选框对象
 *  param1: 组对象
 */
function checkSelectAll( checkObj, groupObj ){

    if( $id( checkObj ) != null && $id( groupObj ) != null ) {
		if ($name( checkObj ).checked){
			selectAll(groupObj);
		}else{
			selectNone(groupObj);
		}
	}
}
/*
 *返回对象中选中的值
 */
function selectone(name){
if(name==null) return false;
var objs =$names(name);
   var ok = false;
   for(var i=0;i<objs.length;i++){
      if(objs[i].checked){
        ok = true;
        break;
      }
   }
   return ok;
}

function getSelectedValue(name){
if(name==null) return "";
var objs =$names(name);
   for(var i=0;i<objs.length;i++){
      if(objs[i].checked){
        return objs[i].value;
        break;
      }
   }
   return null;
}

/*
 *  初始化页面button的样式
 */
function initButtonStyle() {
    var tagnames = ["INPUT","input"];
    for(var i=0;i<tagnames.length;i++){
      var objs = document.getElementsByTagName(tagnames[i]);
      for(var j=0;j<objs.length;j++){
      var obj =objs[j];
      switch(obj.type){
        case 'button':
        case 'submit':
        case 'reset':
           obj.className="button";
         break;
      }   
    }
   }
}
/*
 *用于获取多行的文本
 *用法： 
 * var str = function(){\/\*<b:write property="abc" />\*\/}
 */
Function.prototype.getMultiLine = function() {   
	    var lines = new String(this);   
	    lines = lines.substring(lines.indexOf("/*") + 2, lines.lastIndexOf("*/"));   
	    return lines;   
}
/*
 *  单击复选框时，自动屏蔽或打开修改和删除按钮
 */
function clickCheck(  groupObj, updateObj, deleteObj ) {

    if( groupObj.getSelectLength() < 1 ) {
        if( updateObj != null )
            updateObj.disabled = true;
        if( deleteObj != null )
            deleteObj.disabled = true;

        return function(){};
    }

    if( groupObj.getSelectLength() == 1 ) {
        if( updateObj != null ) {
            updateObj.disabled = false;
        }
        if( deleteObj != null )
            deleteObj.disabled = false;

        return function(){};
    }

    if( groupObj.getSelectLength() > 1 ) {
        if( updateObj != null )
            updateObj.disabled = true;
        if( deleteObj != null )
            deleteObj.disabled = false;

        return function(){};
    }
}

//固定自动设置iframe大小，以适应当前屏幕宽高
function iframeAutoFit(){
        try
        {
            if(window!=parent)
            {
                var a = parent.document.getElementsByTagName("IFRAME");
                for(var i=0; i<a.length; i++) //author:meizz
                {
                    if(a[i].contentWindow==window)
                    {
                        var h1=0, h2=0;
                        a[i].parentNode.style.height = a[i].offsetHeight +"px";
                        a[i].style.height = "0px";
                        if(document.documentElement&&document.documentElement.scrollHeight)
                        {
                            h1=document.documentElement.scrollHeight;
                        }
                        if(document.body) h2=document.body.scrollHeight;

                        var h=Math.max(h1, h2);

                        if(document.all) {h += 5;}
                        if(window.opera) {h += 1;}
                        a[i].style.height = a[i].parentNode.style.height = h +"px";
                    }
                }
            }
        }
        catch (ex){}
 }
 function toQueryString(frm){
 /*
    var elements = Form.getElements($(form));   
    var queryComponents = new Array();         
    for (var i = 0; i < elements.length; i++){   
      var queryComponent = Form.Element.serialize(elements[i]);   
      if (queryComponent)   
        queryComponents.push(queryComponent);   
    }   
       
    return queryComponents.join('&');  
    */
 }
 //Given a form name, grab it's object and loop through all of them setting their disabled attribute to true.
function disableForm(formName){
  var obj = document.forms[formName];
  if(!obj) return; //Return if we didn't get a valid form object...to prevent some js errors.
  for(var i = 0; i < obj.length; i++) {
    obj.elements[i].disabled = true;
  }

}
function enableForm(formName){
  var obj = document.forms[formName];
  if(!obj) return; //Return if we didn't get a valid form object...to prevent some js errors.
  for(var i = 0; i < obj.length; i++) {
    obj.elements[i].disabled = false;
  }

}

function nullToBlank(obj){
   if(obj=="null"||obj==null){
      return "";
   }else{
     return obj;
   }
}
