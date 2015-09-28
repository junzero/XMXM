/**
*	初始化comBoSelect过滤参数。控制comBoSelect输入几位后才去库时查找数据，需与web-common.js、eos-web.js合做
*   comboId comBoSelect的ID(必选)
*   marker  标示，默认某个值的FilterModel的值,当marker无值时到comboId（可选）
*   length  输入的位数,当有这个参数时，marker失效（可选）
*/
   function initComboFilterModel(comboId,marker,length){
   	if(length){}else{
	   	if(marker){}else{
	   		marker = comboId;
	   	}
   		if(marker=='get_user_jdzh'){//使用标识
   			length=2;
   		}
   	}
	var obj = [];
	obj.filterModel = length;
	window[comboId]=obj;
   }