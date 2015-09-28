/*
* 营业管理ajax_common的过滤条件
* 
* 命名规则：YYGL + 包路径 + 具体的功能名称 
* （如：机构列表 -->  YYGL_COMMONS_JGLB_JG）
*/


/****************
 * 公共功能模块     “YYGL_COMMONS” 开头
 ****************/

	//-->公共逻辑构件 "COMMON" 开头
	
		//备注列表
		var  YYGL_COMMONS_COMMON_BZ = 0;

		//付费方式列表
		var  YYGL_COMMONS_COMMON_FFFS = 0;

		//父类产品列表
		var  YYGL_COMMONS_COMMON_FLCP = 1;
		
		//关联产品列表
		var  YYGL_COMMONS_COMMON_GLCP = 1;
		
		//继承业务产品代码
		var  YYGL_COMMONS_COMMON_JCYWCPDM = 1;
		
		//寄达区域列表
		var  YYGL_COMMONS_COMMON_JDQY = 1;
		
		//计费区
		var  YYGL_COMMONS_COMMON_JFQ = 0;
		
		//交易种类列表
		var  YYGL_COMMONS_COMMON_JYZL = 0;
		
		//省份代码列表
		var  YYGL_COMMONS_COMMON_SFDM = 1;
		
		//业务产品列表
		var  YYGL_COMMONS_COMMON_YWCP = 1;
		
		//交易字段列表
		var YYGL_COMMONS_COMMON_JYZD = 0;
		
		
	//-->机构管理逻辑构件 "JGGL" 开头	
	
		//地市列表
		var YYGL_COMMONS_JGGL_DS = 1;
		
		//机构列表
		var YYGL_COMMONS_JGGL_JG  = 1;
		
		//省级列表
		var YYGL_COMMONS_JGGL_SJ = 1;

		//县级列表
		var YYGL_COMMONS_JGGL_XJ = 1;
		
		//支局机构列表
		var YYGL_COMMONS_JGGL_ZJJG = 1;
		
	//-->名址管理逻辑构件  "MZGL" 开头
		
		//地名信息
		var	YYGL_COMMONS_MZGL_MD = 1;
		
		//行政区划
		var YYGL_COMMONS_MZGL_XZQH = 1;
		
	//-->资费管理逻辑构件  "ZFGL"	 开头
		
		//资费列表
		var YYGL_COMMONS_ZFGL_ZF = 0;
		 
/****************
 * 日常业务管理模块 “YYGL_RCYWGL” 开头
 ****************/

	//标准总包种类列表
	var YYGL_RCYWGL_BZZBZL = 0;
	
	//大宗用户信息列表
	var YYGL_RCYWGL_DZYHXX  =1;
	
	//封发班次列表
	var YYGL_RCYWGL_FFBC = 0;
	
	//封发总类列表
	var YYGL_RCYWGL_FFZL = 0;

/****************
 * 营业基础数据模块 “YYGL_YYCJSJGL” 开头
 ****************/
 
 	// 未维护的参数代码列表
 	var YYGL_YYCJSJGL_COMMON_WWHCSDM  = 0;
 	
 	// 发票种类列表
 	var YYGL_YYCJSJGL_FPZL  = 0;
 	
 	
 
 
 
/**
	* 机要用户初始化_临时方法,之后可能将由new comboselect替换,目前暂定第二位开始过滤
	*/
    function paramFunc(comboSobj){
     	var filterModel = Number(window[comboSobj.id].filterModel);
     	if(filterModel <= 0){ filterModel = 0;}else{
     		if(filterModel == 1){
     			filterModel = 1;
     		}else{
     			filterModel = 2;
     		}
		}
     	comboSobj.filterModel=filterModel;
   		var strFf = "<filterModel>"+filterModel+"</filterModel>";
   		if(comboSobj.version){}else{
   			return strFf;
   		}
   		
   		var ff = comboSobj.filterField;
   		var ffArra = ff.split(",");
   		var giv = comboSobj.inputObject.value;
   		comboSobj.oldIOV = giv;
   		if(comboSobj.valueField && comboSobj.hiddenObject.value && comboSobj.initInput){
   			strFf += "<"+comboSobj.valueField+">"+comboSobj.initInput+"</"+comboSobj.valueField+">";
   		}else{
   			for(var i=0;i<ffArra.length;i++){
   				strFf += "<"+ffArra[i]+">"+giv+"</"+ffArra[i]+">";
   			}
   		}
   		
		return strFf;
   }
