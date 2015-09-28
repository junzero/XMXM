
/**
  *函数名：date_Control()
  *
  *功能说明：
  *         控制页面查询起止日期的范围
  *
  *参数说明：
  *			days 起止时间间隔天数，(参数为空：默认30天)
  *			nyLogo 跨年标志   默认0,允许跨年；1,不允许跨年
  *
  *控件ID说明：
  *			起日期ID为：startDate
  *			止日期ID为：endDate
  *
  *使用说明：页面onload事件须添加 common_date_control()方法
  *
  *开发员： 郑智宝
  *
  *生成日期：2011-02-15
  *
  */
function common_date_control(days, nyLogo){
	if(days=="" || days==undefined){
		days = 30;
	}
	if(nyLogo=="" || nyLogo ==undefined){
		nyLogo = 0;
	}
	
	var startDate = $id("startDate").getValue();
   	var endDate = $id("endDate").getValue();
   	
   	//计算止日期的最大值
	var arys= new Array();
	arys=startDate.split('-');
	var date=new Date(arys[0],arys[1]-1,arys[2]); 
    date.setDate(date.getDate() + days); 
    
    //设置止日期的最小值为起日期的值
    $id("endDate").minValue = $id("startDate").getValue();
    
    var m = "";
    var d = "";
    //判断月 日值的位数，如果值的位数=1则在值前面补0，否则取原值
    if((date.getMonth()+1) < 10){
		m = "0"+ (Number(date.getMonth())+1);
	}else{
		m = date.getMonth()+1;
	}
	if(date.getDate() < 10){
		d = "0" + date.getDate();
	}else{
		d = date.getDate();
	}
	//设置止日期的最大值
	if(nyLogo==0){
	   	$id("endDate").maxValue = (date.getYear() + "-" + m + "-" + d);
	}else{
		if(Number(date.getYear) != arys[0]){
			$id("endDate").maxValue = (arys[0] + "-12-31");
		}else{
			$id("endDate").maxValue = (date.getYear() + "-" + m + "-" + d);
		}
	}
   	 //判断选择的起日期和止日期值是否在范围内.如果不在范围内则设置止日期与起日期同日
   	//补充：1.如果止日期小于起日期则触发内部代码
   	//补充：2.如果起日期与止日期值超出查询范围则触发内部代码
   	if(!common_date_compare(startDate,endDate)){
   		$id("endDate").setValue($id("startDate").getValue());
   	}/*else if(common_date_range(startDate,endDate,days)){
   		$id("endDate").setValue(date.getYear() + "-" + m + "-" + d);
   	}*/
   	
   	
}

/**
  *函数名：dateCompare()
  *
  *功能说明：
  *         两个日期值比较大小
  *
  *参数说明：
  *			startDate 起日期
  *			endDate	  止日期
  *
  *返回结果：boolean		true  起日期<=止日期
  *						false 起日期>止日期
  *
  *开发员： 郑智宝
  *
  *生成日期：2011-02-15
  *
  */
function common_date_compare(startDate,endDate)   
{   
	var sDate = startDate.split(" ");
	var arr=sDate[0].split("-");    
	var starttime=new Date(arr[0],arr[1],arr[2]);    
	var starttimes=starttime.getTime();   
  	
	var eDate = endDate.split(" ");
	var arrs=eDate[0].split("-");    
	var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
	var lktimes=lktime.getTime();   
  
	if(starttimes>lktimes){   
		return false;   
	}   
	else  
		return true;   
}  

/**
  *函数名：common_date_range()
  *
  *功能说明：
  *         用于判断起日期，止日期，间隔天之间的关系
  *
  *参数说明：
  *			startDate 起日期
  *			endDate	  止日期
  *			days	  间距天数
  *
  *返回结果：boolean		true  止日期-起日期 > 间隔天数
  *						false 止日期-起日期 < 间隔天数
  *
  *开发员： 郑智宝
  *
  *生成日期：2011-02-15
  *
  */
function common_date_range(starDate,endDate,days){
	var arr=starDate.split("-");    
	var starttime=new Date(arr[0],arr[1],arr[2]);
	var starttimes=starttime.getTime();   
  	
	var arrs=endDate.split("-");    
	var lktime=new Date(arrs[0],arrs[1],arrs[2]);
    lktime.setDate(lktime.getDate() - days);    
	var lktimes=lktime.getTime();   
  
	if(starttimes>=lktimes){   
		return false;   
	}   
	else  
		return true;   
}


/**
  *函数名：  common_Format_Currency()
  *
  *功能：    对页面上的货币类型数据进行格式化
  *
  *参数说明：obj 
  *				控件对象
  *
  *返回结果：无返回值
  *
  *使用方式：需满足以下几点
  *         1.要格式化的控件需设置className="FormatCurrency"  
  *         2.在加载数据之后 调用次方法（修改的时候用到）
  *         3.在控件onBlur事件调用此方法 obBlur="FormatCurrency(this)"
  *
  *开发员：  郑智宝
  *
  *生成日期：2011-03-24
*/
function common_Format_Currency(obj){
	//判断是否指定了控件
	if(obj==null){ //无指定控件
	
		//获取页面上所有的输入控件
		var inputObjs = document.getElementsByTagName("input");
		for(var i = 0; i <inputObjs.length;i++){
			if(inputObjs[i].className=="FormatCurrency"){ //判断控制是否为货币类型的控件
				var reg = /^\d*\.?\d*$/;
				if(inputObjs[i].value!=null && inputObjs[i].value!="" && reg.test(inputObjs[i].value)){
				 	inputObjs[i].value=formatNumber(inputObjs[i].value,'###,###,###.00');
			 	}
			}
		}
	}else{ //有指定控件
		var reg = /^\d*\.?\d*$/;
		if(obj.value!=null && obj.value!="" && reg.test(obj.value)){
		 	obj.value=formatNumber(obj.value,'###,###,###.00');
	 	}
	}
}

