/**
 * 基本方法js
 * author：yz
 */
var WEB = {};
/**
 * 查看当前流程
 * @return
 */
function doflowpic(){
	var strUrl="";
            if($("#executionId").val()){
            	 strUrl = "/jbpm/jbpmDemoAction_viewTask.action?executionId="+$("#executionId").val();
            }else{
            	 strUrl = "/jbpm/jbpmDemoAction_viewProcess.action?definitionId="+$("#definitionId").val();
            }
        	showModalCenter(strUrl, null, null, 900, 500, '当前流程进度'); 
		}

/**
 * 刷新当前页面
 * @return
 */
function reload(){
	  location.reload();
}

/**
 * 返回上一个界面
 */
function goback(){
	window.history.go(-1);}

/**
 * 查看信息详情
 * @param messageId
 * @return
 */
function doLink(messageId,status,businessType,executionId){
	var strUrl = "/messagePublish/tMessagePublishAction_messagePublishInfo.action?_ts=" +new Date()+"&messageId="+messageId+"&status="+status+
	"&taskAssgineeDto.businessKey="+messageId+"&taskAssgineeDto.businessType="+businessType+"&taskAssgineeDto.executionId="+executionId;
	showModalCenter(strUrl, null, callBack, clientX*0.8, clientY*0.65, '详情');  
}

/**
 * 返回代办列表到要内容页
 * @return
 */
WEB.turnMainFrame=function(){
	/*window.parent.frames["mainFrame"].location.href="/jbpm/tProcessTaskAssigneeAction_queryMyToDoTasksList.action";*/
	window.close();
}
/**
 * 返回其他地址到主要内容页
 * @param url
 * @return
 */
 WEB.turnOtherUrl=function(url){
	window.parent.frames["mainFrame"].location.href=url;
}

/**
 * 当前日期（yyyy-MM-dd）
 */
 WEB.today = function(){
   var d = new Date();
   var y = d.getFullYear();
   var m = d.getMonth() + 1;
       m = (m>=10)?m:"0"+m;
   var dy = d.getDate();
       dy= (dy>=10)?dy:"0"+dy;
   var s = y+"-"+m+"-"+dy;
   return s;	
 };

/**
 * 当前时间
 */
 WEB.now = function(){
   var d = new Date();	
   var t = WEB.today()
   var h = d.getHours();
   h = (h>=10)?h:"0"+h;
   var m= d.getMinutes();
   m = (m>=10)?m:"0"+m;
   var s = d.getSeconds();
   s = (s>=10)?s:"0"+s;
   return t+" "+h+":"+m+":"+s;	
 };

/**
 * checkBox 多选中
 */ 
 WEB.checkMObj = function (oid,vstr){
		var arrobj = $("input[id='"+oid+"']");
		var v = vstr.split(","); 
		$.each(arrobj,function( i,item ){
			$.each(v,function(j,vitem){
				if(item.value==WEB.trim(vitem))
                    //item.checked = true;
					item.setAttribute('checked','true');
			});
		});
	};
	
/**
 * 去空格
 */	
 WEB.trim = function (strValue){
		   return strValue.replace(/^\s*|\s*$/g,"");
	};
/**
 * 控制
 * isView :1、为只读状态， 默认为空
 * createEmpid：创建者
 * currentEmpid：修改人
 * parameIds:为不设限制的选项(ID用逗号","分割)
 */
	WEB.doDisabledAttr=function(isView,createEmpid,currentEmpid,parameIds){
		 if(isView!=""){
			  $(":text").each(function(a,b){
			        $(b).attr("readonly",true);
			    });
			  $('select').attr("readonly",true);
			  $('textarea').attr("readonly",true);
			  $("#smit").css("display","none"); 
	 }else{
		  if(createEmpid!=currentEmpid){
			  $(":text").each(function(a,b){
			        $(b).attr("readonly",true);
			    });
			  $('select').attr("readonly",true);
			  $('textarea').attr("readonly",true);
			  if(parameIds!=""){
				  var str=parameIds.split(",");
				  for(var i=0;i<str.length;i++){
					  $("#"+str[i]).attr("readonly",false);
				  }
			  }
			  if( document.getElementById("dis2")!=null){
				  document.getElementById("dis2").style.display='block';
				  document.getElementById("dis1").style.display='none';
			  }
		  }else{
			  $('#dis1').css("display","block");
				 $('#dis2').css("display","none");
		  }
			  
	 }
	 };
 
 /**
  * 控制为不可编辑状态
  */
 WEB.doDisabledattr=function(){
	  $(":text").each(function(a,b){
	        $(b).attr("readonly",true);
	    });
	  $('select').attr("readonly",true);
	  $('textarea').attr("readonly",true);
 };
 
 /**
  * 隐藏对象
  * parameIds：
  * ID用逗号","分割
  */
 WEB.doDisplay=function(parameIds,bl){
	  if(parameIds!=""){
		  var str=parameIds.split(",");
		  for(var i=0;i<str.length;i++){
			  $("#"+str[i]).css("display",bl);
		  }
	  }
 };
 
 /**
  * 按规定条件隐藏
  * con1 条件1
  * con2 条件2
  * parameIds  字符参数
  * bl:none或者其他
  */
 WEB.doConditionDisplay=function(con1,con2,parameIds,bl){
	 if(con1==con2){
		  if(parameIds!=""){
			  var str=parameIds.split(",");
			  for(var i=0;i<str.length;i++){
				  $("#"+str[i]).css("display",bl);
			  }
		  }
	  }
 };
 
 /**
  * 已办隐藏新增附件按钮
  */
 WEB.hideFile=function(){
	 $("#rowFile").hide();
 };
 
	//结束流程
	function doDeleteProcess(bkey,resourceType){
		if (confirm('确定要结束流程吗？')){
			var strUrl="";
	  	    if($("#executionId").val()){
	  	    	 strUrl = "/jbpm/jbpmDemoAction_deleteProcess.action?";
	  	    }
	  	  maskTop();
	  		$.ajax({
	  	        url: strUrl,
	  	        async: false,
	  	        type: 'post',
	  	        data: "executionId="+$("#executionId").val(),
	  	        timeout: 60000,
	  	        success: function (data) {
	  	      	  if(data.indexOf("success")>=0){
		  	      	  //调用业务结束流程方法
		  	      	  //流程列表增加一行当前操作人结束流程记录，操作类型为结束
		  	      	  //意见默认为起草人结束流程
			$.ajax({
										url : '/opinion/tApproveOpninionAction_insertDeleteProcessOpninions.action?',
										async : false,
										type : 'post',
										data : "opninion.resourceId=" + bkey
												+ "&opninion.resourceType="+ resourceType,
										timeout : 60000,
										success : function(data2) {
											if (data2.indexOf("success") >= 0) {
												alert("操作成功");
												unMaskTop();
												window.close();
											} else {
												alert("操作失败");
												unMaskTop();
											}
										}
									}); 
						} else {
							unMaskTop();
							alert("操作失败");
						}
					}
				});
	  	}
		}
 

	/*20140914长意见弹框*/
	/************************************************** 
	* DivWindow.js 
	**************************************************/ 
	var DivWindow= function(popup/*最外层div id*/,popup_drag/*拖动div id*/,popup_exit/*退出按钮id*/ ,exitButton/*触发服务器端退出按钮id*/,varwidth,varheight,zindex,str){ 

	    this.popup =popup ;    //窗口名称 
	     
	    this.height =varheight ; //窗口高度，并没用来设置窗口高度宽度，用来定位在屏幕的位置 
	    this.width =varwidth ;    //窗口宽度 
	this.popup_exit=popup_exit; 
	this.exitButton=exitButton;
	this.str=str;
	this.zindex=zindex; 
	    this.init = function(){    //初始化窗口 
	         
	         
	        this.popupShow(); 
	        this.startDrag();    //设置拖动 
	         
	        this.setCommond();    //设置关闭 
	        DivWindow.ArrayW.push(document.getElementById(this.popup));    //存储窗口到数组 
	    };this.init(); 
	}; 
	//存储窗口到数组 
	DivWindow.ArrayW = new Array(); 
	//字符串连接类 
	DivWindow.StringBuild = function(){ 
	    this.arr = new Array(); 
	    this.push = function(str){ 
	        this.arr.push(str); 
	    }; 
	    this.toString = function(){ 
	        return this.arr.join(""); 
	    }; 
	}; 
	//拖动类 
	DivWindow.Drag = function(o ,oRoot){ 
	    var _self = this; 
	    //拖动对象 
	    this.obj = (typeof oRoot != "undefined") ?oRoot : o; 
	    this.relLeft = 0;    //记录横坐标 
	    this.relTop = 0;    //记录纵坐标 
	    o.onselectstart = function(){ 
	        return false; 
	    }; 
	    o.onmousedown = function(e){    //鼠标按下 
	        e = _self.fixE(e); 
	        _self.relLeft = e.clientX - _self.fixU(_self.obj.style.left); 
	        _self.relTop = e.clientY - _self.fixU(_self.obj.style.top); 
	        document.onmousemove = function(e){ 
	            _self.drag(e); 
	            //_self.obj.style.border = "1px dashed #000000"; 
	            //_self.obj.style.filter = "alpha(opacity=30)"; 
	            //_self.obj.style.opacity = "0.3"; 
	        }; 
	        document.onmouseup     = function(){ 
	            _self.end(); 
	            //_self.obj.style.border = "1px solid #cccccc"; 
	            //_self.obj.style.borderBottom = "2px solid #E0E0E0"; 
	            //_self.obj.style.borderRight = "2px solid #E0E0E0"; 
	            //_self.obj.style.filter = "alpha(opacity=100)"; 
	            //_self.obj.style.opacity = "1"; 
	        }; 
	    }; 
	    this.drag = function(e){    //拖动 
	        e = this.fixE(e); 
	        var l = e.clientX - this.relLeft; 
	        var t = e.clientY - this.relTop; 
	        if (t < 0) 
	        { 
	            t = 0;    //防止头部消失 
	        } 
	        this.obj.style.left = l +"px"; 
	        this.obj.style.top = t +"px";     
	    }; 
	    this.end = function(){    //结束拖动 
	        document.onmousemove = null; 
	        document.onmouseup = null; 
	    }; 
	    this.fixE = function(e){    //修复事件 
	        if (typeof e == "undefined") e = window.event; 
	        return e; 
	    }; 
	    this.fixU = function(u){    //处理px单位 
	        return parseInt(u.split("p")[0]); 
	    }; 
	}; 

	//窗口拖动 
	DivWindow.prototype.startDrag = function(){ 
	    var obj = document.getElementById(this.popup); 
	    new DivWindow.Drag(obj.childNodes[0] ,obj); 
	}; 

	//设定窗口优先级 
	DivWindow.prototype.setTop = function(){ 
	    document.getElementById(this.popup).onclick = 
	    document.getElementById(this.popup).onmousedown = 
	    function(){ 
	        for(var i=0;i<DivWindow.ArrayW.length;i++) 
	        { 
	            DivWindow.ArrayW[i].style.zIndex = 1; 
	        } 
	        this.style.zIndex = 100; 
	    };     
	}; 
	//显示 
	DivWindow.prototype.popupShow=function() 
	{ document.all.mask.style.display="block"; 
	document.all.mask.style.width=window.screen.width +20; 
	document.all.mask.style.height=window.screen.width +20; 
	var element = document.getElementById(this.popup); 
	var ct = document.getElementById("ct"); 
	ct.innerHTML =this.str;
	element.style.position = "absolute"; 
	element.style.visibility = "visible"; 
	element.style.display = "block"; 
	element.style.width=this.width; 
	element.style.height='auto'; 
	element.style.left = this.width/2+"px"; 
	element.style.top =this.height/3+"px";
	//element.style.top =20+"px"; 
	element.style.zIndex=this.zindex; 
	} 
	//设置关闭 
	DivWindow.prototype.setCommond = function(){ 
	    var _self = this; 
	    //根对象 
	    var obj = document.getElementById(this.popup); 
	    var exit = document.getElementById(this.popup_exit); 
	    var triggServerEvent=document.getElementById(this.exitButton); 
	    //设置关闭 
	     exit.onclick = function(){ 
	     obj.style.display = "none"; 
	     obj.style.visibility = 'hidden'; 
		 document.all.mask.style.display='none'//关闭遮罩层 
	     triggServerEvent.click();//触发服务器端退出事件 
	     }; 
	}; 
	function coptct(){
	   copyToClipBoard(document.getElementById("ct").innerHTML);
	   var exit = document.getElementById("popup_exit"); 
	   exit.onclick();
	}
	
	 //复制到剪切板js代码
    function copyToClipBoard(s) {
        //alert(s);
        if (window.clipboardData) {
            window.clipboardData.setData("Text", s);
        } else if (navigator.userAgent.indexOf("Opera") != -1) {
            window.location = s;
        } else if (window.netscape) {
            try {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            } catch (e) {
              
            }
            var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
            if (!clip)
                return;
            var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
            if (!trans)
                return;
            trans.addDataFlavor('text/unicode');
            var str = new Object();
            var len = new Object();
            var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
            var copytext = s;
            str.data = copytext;
            trans.setTransferData("text/unicode", str, copytext.length * 2);
            var clipid = Components.interfaces.nsIClipboard;
            if (!clip)
                return false;
            clip.setData(trans, null, clipid.kGlobalClipboard);
           
        }
    }
    

	function freshFunc(value,entity,rowNo,cellNo){
			if(value==undefined || value == 'null' || value == null){
				value='';
			}
		return "<span onclick=\"showOpinion('"+value+"');\">"+value+"</span>";
		}

	function showOpinion(value){
		new DivWindow('popup','popup_drag','popup_exit','exitButton','500','700',4,value);
		}
	
	function showAll(value,entity,rowNo,cellNo){
		if(value==undefined || value == 'null' || value == null){
			value='';
		}
		return "<span title=\""+value+"\">"+value+"</span>";
	}
	
	function showDetailopinion(resourceId,resourceType,flowId){
		var url = "/opinion/tApproveOpninionAction_queryViewList.action?resourceId="+resourceId+"&resourceType="+resourceType+"&resourceFlow="+flowId;
		showModalCenter(url, null, null, 700, 400, '展示流程列表');
	}
	
	function downLoadTemplateFiles(templateFileIds){
		if(templateFileIds == null || templateFileIds == ""){
			alert("没有模板文件，请在修改流程模块上传模板文件");
		}else{
			window.open('/file/tFileResourceTableAction_downLoadFile.action?fileId='+templateFileIds);
		}
	}
	
	function savedefault(){
		var opinion = $("#opinion").val();
		var recId = $("#recId").val();
		if(recId=='undefined' || recId == undefined){
			recId = "";
		}
		if(opinion!=""){
			$.ajax({
		        url: '/opinion/tDefaultOpinionAction_saveOrUpdate.action',
		        async: false,
		        type: 'post',
		        data: "opinion="+opinion+"&recId="+recId,
		        dataType: 'json',
		        timeout: 60000,
		        success: function (data) {
		        	if(data.indexOf("success")>=0){
						  	alert("当前意见已保存!");
						  	adddefault();
					  		//reload();
					  	//WEB.turnMainFrame();20140905改
					  	}else if(data.indexOf("fails")>=0){
						  	alert("操作失败!");
					  	}else{	 
					  	    alert("操作失败!"); 
					  	}
		        }
	      });
		}else{
			alert('请先填写意见');
		}
	}
	
	function showDefault(){
		if($("#default_op").text()!=null && "null"!=$("#default_op").text()){
			$("#opinion").val($("#default_op").find('option:selected').text());
		}
	}
	
	function adddefault(){
		var strUrl = "/opinion/tDefaultOpinionAction_queryViewList.action";
		showModalCenter(strUrl,null,callbackUtil,800,450,'默认意见列表');
	}
	
	function callbackUtil(){
		
	}
	

