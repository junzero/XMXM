	if(PageControl){
		PageControl.setFocus = function(B, E) {
	      try {
		    var D;
		    if (B) D = B.id;
		    var A = top.currStack.length - 1;
		    for (; A > -1; A--) {
		        var C = top.currStack[A];
		        if (C) if (C == B) break;
		        else if (C == E) break;
		        else {
		            try {
			            if (C.hide) {
			                    C.hide()
			            }
		            } catch(F) {}
		            if (A >= 0) {
		                    top.currStack.splice(A, 1)
		            }
		        }
		    }
		    if (B) top.currStack.push(B)
	      } catch(F) {}
		};
		PageControl.addtoStack = function(A) {
			if (!$contains(top.currStack, A)){
				try{
					top.currStack.push(A);
				}catch(F){
					try{
						top.currStack=[];
						top.currStack.push(A);
					}catch(e){}
				}
			}
		};
	}
    function document_onkeydown_fun(){
    	if(document.onkeydown){}else{
		    document.onkeydown=function(){
		    	if(event.keyCode==8){
		    		var srce = event.srcElement;
					if(srce.tagName=='INPUT' || srce.tagName=='TEXTAREA'){
						if((srce.type!='password'
							&& srce.type!='text' 
							&& srce.type!='file' 
							&& srce.tagName!='TEXTAREA') || srce.readOnly==true){
					    		return false;
						}else{
							return true;
						}
					}else{
						return false;
					}
		    	}
		    }
    	}
    }
    eventManager.add(window,"load",document_onkeydown_fun);
	function init_showmodal_tab_js(){
		if(window["_eos_dialog_id"]){
			var e3 = document.createElement("a");
			e3.href="#";
			e3.onkeydown=function(){
				if(event.keyCode==9){
					event.keyCode=13;
				}
			}
			document.body.appendChild(e3);
		}
	}
	eventManager.add(window,"load",init_showmodal_tab_js);
	
	
	
  	Ajax.prototype.wrapperUrl = function() {
  		var url = this.url;
  		if(url && (url.substr(url.length-4)!='.biz')){
	  		return this.url;
  		}else if(url){
	    	var iof = url.lastIndexOf(".",url.length-5);
	    	var flow = url.substr(0,iof);
	    	var action = url.substring(iof+1,url.length-4);
	    	url = "/"+flow+".do?method="+action;
			return url;
  		}
//	    return this.isCommonUrl ? this.url: this.url + ".ajax?time=" + (new Date())
	};
	Ajax.prototype.submit = function(A,isToken) {
	    if(isToken){
	    	var eelt = null;
			try{
				eelt = eventManager.getElement()
			}catch(e){
				eelt = document.body;
			};
			var tsi = eelt.getAttribute("_tokenSessionId");
			if(tsi){
				alert('已执行了提交！');
				return;				
			}
			eelt.setAttribute("_tokenSessionId",new Date().getTime());
			this.tokenSessionObj = eelt;
	    }
	    this.running = true;
	    this.encoding = A || this.encoding;
	    this.initParam();
	    try {
	        var B = this.wrapperUrl();
	        this.xmlHttp.open(this.method, B, this.async);
	        var D = this.wrapperData(),
	        E = this;
	        if (this.async) this.xmlHttp.onreadystatechange = function() {
	            E.onStateChange.apply(E, arguments)
	        };
	        this.setRequestHeaders();
	        $debug("submit url:" + this.url + "\n submit XML:\n" + EOSLog.formatXML(D));
			var paramStr = "";
			if(this.sumbitType){
				paramStr = "submitType="+this.sumbitType+"&";
			}else{
				paramStr = "submitType=1&";
			}
			paramStr += "ajax=";
	        this.xmlHttp.send(paramStr+D);
	        if (!this.async) return this.onStateChange()
	    } catch(F) {
	        $error("xmlHttp ajax request error.");
	        this.running = false
	    }
	};
	
	Ajax.prototype.resetToken = function() {
		eelt = this.tokenSessionObj;
		eelt.setAttribute("_tokenSessionId",null);
		this.tokenSessionObj = null;
	}
	Ajax.prototype.getClosePage = function() {
	    var B = "/root/exceptions/closePage",
	    A = this.getReturnNodeValue(B);
	    if(A==null){
			return false;	    
	    }else if(A==1){
	    	var m = "/root/exceptions/closeMess";
	    	var ma = this.getReturnNodeValue(m);
	    	if(ma){
		    	alert(ma);
	    	}
	    	var rvm = "/root/exceptions/returnValue";
	    	var rv = this.getReturnNodeValue(rvm);
	    	window['returnValue'] = rv;
		    return true;
	    }
	};
	Ajax.prototype.callBack = function(){
			var gotoLoginPage=false , loginPageUrl=null;
			var exceptionXML= this.getExceptionInvalid();
			if (exceptionXML===true || exceptionXML==="true"){
				loginPageUrl= this.getLoginPage();
				if (loginPageUrl){
					gotoLoginPage=true;
					alert('登录超时，请重新登录！！！');
					exceptionXML= AJAX_EXCEPTION_INVALID+"\n\n"+this.url;
					top.location.href="/";
				}else{
					var cPage = this.getClosePage();
					if(cPage){
						window.closeD();
						return;
					}
				}
//				this.getActionMessage();
			}else{
				exceptionXML= this.getExceptionMessage() || this.getException() ;
			}
			if (gotoLoginPage /* && confirm( GOTO_LOGIN_PAGE ) */ ){
				if (window.contextPath && loginPageUrl.indexOf(window.contextPath+'/')!=0){
					loginPageUrl=window.contextPath+(loginPageUrl.indexOf('/')==0?'':'/')+loginPageUrl;
				}
				top.location=loginPageUrl;
				return;
			}
			if (exceptionXML && this.onFailure) {
				this.isFailureState = true;
				return this.onFailure(exceptionXML);
			}else{
				this.isFailureState = false;
			}
			return this.onSuccess();
	}
	Ajax.prototype.addParam1 = function(A, B) {
	    var index = A.lastIndexOf("/");
	    if(index>0){
	    	A = A.substring(index+1);
	    }
	    this.param.push({
	        key: A,
	        value: B
	    })
	};
	Ajax.prototype.getEntitys = function(D) {
	    var B = new Array();
	    if (this.retDom == null) return B;
	    if (D == null || D == "") return B;
	    var E = this.retDom.selectNodes(D);
	    for (var A = 0; A < E.length; A++) {
	        B[A] = E[A];
	    }
	    return B
	};
	Ajax.prototype.getNodeValue = function(node,property){
		if(node){
			var nnode = node.selectSingleNode(property);
		    return getNodeValue(nnode);
		}else{
			return null;
		}
	};
	Ajax.prototype.getNodeValue2 = function(node,property){
		if(node){
			var nnode = node.selectNodes(property);
			var varra = [];
			for(var i=0;i<nnode.length;i++){
				varra.push(getNodeValue(nnode[i]));
			}
		    return varra;
		}else{
			return null;
		}
	};
	Ajax.prototype.submitFormById = function(D, C) {
	    var E = "";
	    D = $id(D);
	    for (i = 0; i < D.elements.length; i++) {
	        var B = D.elements[i];
	        if (B.type == "radio" || B.type == "checkbox") if (!B.checked) continue;
	        var F = D.elements[i].id,
	        A = getElementValue(D.elements[i]);
	        if (F) this.addParam(F, A)
	    }
	    this.submit(C);
	    if (this.retDom == null) return false;
	    return true
	};
	EOSLOG_LEVEL="error";

function gotoPage(F, K, C, B, D, A) {
    var H;
    if (A) H = $id(A) || $name(A);
    var L, J, I;
    if (H) {
        L = H.elements[F + ".begin"];
        J = H.elements[F + ".length"];
        I = H.elements[F + ".count"]
    } else {
        L = $e(F + ".begin");
        J = $e(F + ".length");
        I = $e(F + ".count");
        H = getParentByTagName(L, "form")
    }
    var G = 0,
    K = !K ? 1 : Number(K);
    if (typeof(K) != "number") K = $e(K).value;
    K = parseInt(K / 1);
    K = isNaN(K) || K < 1 ? 1 : K;
    var E = G / 1 + J.value / 1 * (K - 1) / 1;
    L.value = E < 0 ? 0 : E;
    submitFormBy(H, C, B, D)
}
function firstPage(E, C, B, D, A) {
    var G;
    if (A) G = $id(A) || $name(A);
    var F;
    if (G) F = G.elements[E + ".begin"];
    else {
        F = $e(E + ".begin");
        G = getParentByTagName(F, "form")
    }
    F.value = 0;
    submitFormBy(G, C, B, D)
}
function prevPage(I, C, B, G, A) {
    var K;
    if (A) K = $id(A) || $name(A);
    var J, H, D;
    if (K) {
        J = K.elements[I + ".begin"];
        H = K.elements[I + ".length"]
    } else {
        J = $e(I + ".begin");
        H = $e(I + ".length");
        K = getParentByTagName(J, "form")
    }
    var F = H && H.value ? H.value: 0,
    E = Number(J.value) - Number(H.value);
    J.value = E < 0 ? 0 : E;
    submitFormBy(K, C, B, G)
}
function nextPage(H, C, B, F, A) {
    var J;
    if (A) J = $id(A) || $name(A);
    var I, G, D;
    if (J) {
        I = J.elements[H + ".begin"];
        G = J.elements[H + ".length"]
    } else {
        I = $e(H + ".begin");
        G = $e(H + ".length");
        J = getParentByTagName(I, "form")
    }
    var E = G && G.value ? G.value: 0;
    I.value = Number(I.value) + Number(G.value);
    submitFormBy(J, C, B, F)
}
function lastPage(H, C, B, F, A) {
    var J;
    if (A) J = $id(A) || $name(A);
    var I, G, D;
    if (J) {
        I = J.elements[H + ".begin"];
        G = J.elements[H + ".length"];
        D = J.elements[H + ".count"]
    } else {
        I = $e(H + ".begin");
        G = $e(H + ".length");
        D = $e(H + ".count");
        J = getParentByTagName(I, "form")
    }
    var E = Math.floor(((D.value / 1) + (G.value / 1) - 1) / (G.value / 1));
    I.value = (E - 1) * (G.value / 1);
    J = getParentByTagName(I, "form");
    submitFormBy(J, C, B, F)
}
function isChinaMobileNo(C) {
    if (typeof(C) == "object") C = C.value;
    var A = /^(13[0-9]|15[0-9]|18[0-9]|147)\d{8}$/;
    B = new RegExp(A);
    if (B.test(C)) return true;
    return false
}
function _get_top_window() {
    if (top.document && (top.document.getElementsByTagName("frameset").length == 0)) return top;
    var B = window,
    A = window;
    while (B && top != B) {
        B = B["parent"];
        if (B && B.document && (B.document.getElementsByTagName("frameset").length == 0)) A = B
    }
    return A
}
function f_check_stringLength(A) {
    return true;
}
Datacell.prototype.addHiddenByArray = function(array,container){
	for(var i=0;i<array.length;i++){
	    if(array[i].value==null)continue;
	    var akey = array[i].key;
	    akey = akey.replaceAll('/','.');
		var hidden = $createElement("input", {type:"hidden", name:akey , value:array[i].value });
		container.appendChild(hidden);
	}
}
/**
 * 载入数据的方法.
 */
 // this.afterLoadData();
Datacell.prototype.loadData = function(){
	if (this.beforeLoadData()===false){
		return;
	}
	if (this.dataXML=="<json/>"){
		return;
	}
	if (!this.linkId && !this.queryAction && this.xpath){
		var xmlZone=document.getElementById(this.id+"_xml");
		this.dataXML= xmlZone?xmlZone.innerHTML:null;
	}else if (this.linkId && this.linkId.indexOf('xml:')==0){
		var xmlZone=document.getElementById(this.linkId.substring(4));
		this.dataXML= xmlZone?xmlZone.innerHTML:null;
	}else if (this.linkId && this.isFirstLoad ){
		this.isFirstLoad=false;
		return;
	}
	var xmlDom;
	if(this.dataXML && this.dataXML!=="<json/>"){
		this.datasetExp = Dataset.create(this.dataXML,this.xpath);
		xmlDom=createXmlDom();
		xmlDom.loadXML(this.dataXML);
		var pagecond = xmlDom.selectSingleNode("/root/data/page");
		this.freshPagePilot(pagecond);
		return;
	}
	if(this.onLoadData()!==false){
		if(!this.queryAction){
			return ;
		}

		var ajax = new HideSubmit(this.queryAction);
		ajax.sumbitType="2";
		var param = this.getQueryForm();
		if(this.queryParam){
			param += this.queryParam;
		}
		if(this.pageParam){
			param += this.pageParam;
		}
		if(this.initParamFunc){
			try{
				param += eval(this.initParamFunc + "()");
			}catch(e){
				$handle(e);
			}
		}
		if(param ==""){
			param = null;
		}
		if(this.paramFormId){
			var form = $id(this.paramFormId);
			if(form){
				for(var i=0;i<form.elements.length;i++){
					var elem = form.elements[i];
					if(elem.name){
						ajax.addParam(elem.name, getElementValue(elem) );
					}
				}
			}
		}
		if(this.paramList){
			for(var i=0;i< this.paramList.length;i++){
				var elem = this.paramList[i];
				if(elem){
					ajax.addParam(elem.key,elem.value);
				}
			}
		}
		ajax.loadData(param);
		xmlDom = ajax.retDom;
		this.datasetExp = Dataset.create(xmlDom,this.xpath,this.getSubmitXpath());
		var pagecond = xmlDom.selectSingleNode("/root/data/page");
		if(pagecond){
			this.freshPagePilot(pagecond);
		}
		this.afterLoadData(ajax);
	}
}
Datacell.prototype.submit = function() {
    if (this.beforeSubmit() === false || !this.submitAction) return;
    this.locked();
    this.endEdit();
    if (!this.isModefied) alert(DATACELL_MODIFY_NO);
    else {
        if (!this.validateAll()) return false;
        var A = false;
        if (!A) {
            var G = new HideSubmit(this.submitAction);
            G.sumbitType="3";
            if (this.paramFormId) {
                var E = $id(this.paramFormId);
                if (E) for (var B = 0; B < E.elements.length; B++) {
                    var C = E.elements[B];
                    if (C.name) G.addParam(C.name, getElementValue(C))
                }
            }
            if (this.paramList) for (B = 0; B < this.paramList.length; B++) {
                C = this.paramList[B];
                if (C) G.addParam(C.key, C.value)
            }
            G.loadData(this.datasetExp.getSubmitXML(this));
            if (G.retDom) {
                var D = G.retDom.selectNodes("\rootexception");
                if (D && D.length > 0) {
                    var F = getNodeValue(D[0]);
                    if (F && ("" + F).indexOf("e") >= 0) alert(DATACELL_SAVE_ERR)
                }
            }
            this.afterSubmit(G);
            this.dataXML = null;
            this.loadData();
            this.refresh()
        }
    }
    this.unlocked();
    return true
};
Datacell.prototype.clearParam = function() {
	if(this.paramList){
		this.paramList.length=0;
	}
    return true
};
Datacell.prototype.addParam = function(key, value){
	this.paramList=this.paramList || [];
	var keyJson = eval("this.paramList."+key);
	if(keyJson){
		keyJson.value = value;
	}else{
		keyJson = {key : key ,value : value};
		this.paramList.push(keyJson);
		eval("this.paramList."+key+"=keyJson;");
	}
}
if(Calendar){
	/**
	 * 符合控件开发规范的函数.
	 * @param {Object} value
	 */
	Calendar.prototype.setValue=function (value){
		if(!value||value==""){
			this.value = null;
			this.inputObject.value = "";
			this.hiddenObject.value="";
			return ;
		}
		if((typeof(value))!="string" ){
			this.value = dateToString(value,this.__privateFormat);
			this.refreshDisplayValue();
	  	this.refreshHiddenValue();
		}else{
			if(isDate(value,this.__privateFormat)){
				this.value = value;
				this.refreshDisplayValue();
	  		this.refreshHiddenValue();
			}else if(this.submitFormat&&isDate(value,this.submitFormat)){
				this.value = dateFormat(value,this.submitFormat,this.__privateFormat);
				this.refreshDisplayValue();
	  		    this.refreshHiddenValue();
			}
			if(isDate(value,"yyyy-MM-dd HH:mm:ss.S")||isDate(value,"yyyy-MM-dd HH:mm:ss")||isDate(value,"yyyyMMddHH")||isDate(value,"yyyyMMddHHmm")
				||isDate(value,"yyyyMMddHHmmss")||isDate(value,"yyyyMMddHHmmssS")||isDate(value,"yyyy-MM-dd")||isDate(value,"yyyy-MM-dd HH")
				||isDate(value,"yyyy-MM-dd HH:mm")
				){
				this.value = dateFormat(value,"yyyy-MM-dd HH:mm:ss",this.__privateFormat);
				this.refreshDisplayValue();
	  		    this.refreshHiddenValue();
			}
			else{
				this.inputObject.value = "";
				this.hiddenObject.value="";
			}
			
	
		}
	}
	/**
	 * 符合控件开发规范的函数.
	 */
	Calendar.prototype.getValue=function (){
		if(this.inDatacell){
			return this.hiddenObject.value;
		}else{
			return this.value;
		}
	}
}
window.closeD = function(){
	hideModelDialog(window["_eos_dialog_id"]);
}
function $id(F, B) {
	B = B || document;
	if (typeof(F) == "object") return F;
	var C = B.getElementById(F),
	E;
	if ((isIE || isIE9) && C) {
		if (C.id === F) E = C;
		else {
			var D = B.all[F];
			if (D && D.length) for (var A = 0; A < D.length; A++) if (D[A].id === F) {
				E = D[A];
				break
			}
		}
	} else E = C;
	return E || $o(F) || null
}
function $name(C) {
	if (typeof(C) == "object") return C;
	var B = document.getElementsByName(C);
	if (!B) return null;
	if (isIE || isIE9) {
		for (var A = 0; A < B.length; A++) if (B[A].name == C) return B[A]
	} else if (B.length > 0) return B[0];
	return null
}
/**
* 取消事件向上传播
**/
function cancelEventFun(e){
	if(e){}else{
		if(window.event){
			e=window.event;
		}else{
			e = event;
		}
	}
	if(e){}else{
		return;
	}
	if(e.stopPropagation){
		e.preventDefault();
		e.stopPropagation();
	}else{
		e.returnValue=false;
		e.cancelBubble=true;
	}
}
Ajax.prototype.initParamPost = function() {
    var B = "";
    for (var A = 0; A < this.param.length; A++) {
        var D = this.param[A];
        B += "&"+D.key + "=" + encodeURIComponent(encodeURI(D.value));
    }
    if(this.xmlParam){
	    B += "&this.xmlParam=" + encodeURIComponent(encodeURI(this.xmlParam));
    }
    this.submitParam = B;
};
Ajax.prototype.submitPost = function(A) {
    this.running = true;
    this.encoding = A || this.encoding;
    this.initParamPost();
    try {
        var B = this.wrapperUrl();
        this.xmlHttp.open(this.method, B, this.async);
        var D = this.wrapperData(),
        E = this;
        if (this.async) this.xmlHttp.onreadystatechange = function() {
            E.onStateChange.apply(E, arguments)
        };
        this.setRequestHeaders();
		var paramStr = "";
		if(this.sumbitType){
			paramStr = "submitType="+this.sumbitType+"&";
		}else{
			paramStr = "submitType=1&";
		}
		paramStr += this.submitParam;
        this.xmlHttp.send(paramStr);
        if (!this.async) return this.onStateChange()
    } catch(F) {
        $error("xmlHttp ajax request error.");
        this.running = false
    }
};
Ajax.prototype.submitFormPost = function(D, C) {
    var E = "";
    D = $id(D);
    for (i = 0; i < D["elements"].length; i++) {
        var B = D["elements"][i];
        if (B.type == "radio" || B.type == "checkbox") if (!B.checked) continue;
        var F = D["elements"][i].name || D["elements"][i].id,
        A = getElementValue(D["elements"][i]);
        if (F) this.addParam(F, A)
    }
    this.submitPost(C);
    if (this.retDom == null) return false;
    return true
};
ComboSelect.prototype.addParam = function(key, value){
	this.paramList=this.paramList || [];
	var keyJson = eval("this.paramList."+key);
	if(keyJson){
		keyJson.value = value;
	}else{
		keyJson = {key : key ,value : value};
		this.paramList.push(keyJson);
		eval("this.paramList."+key+"=keyJson;");
	}
}
LookUp.prototype.validate = function() {
	if(this.lookupText){
		return checkInput(this.lookupText);
	}
    return true
};
//--------------------------------新打印办法---------------------------------------

		function Load_doURLAppletPrint(jsessionid,basePath,reportUrl){//载入先后顺序
			window._jsessionid = jsessionid;
			window._basePath = basePath;
			var appletPrinter_html = '<applet id="PlusPrintApplet" code="com.fr.report.print.Plus2PrintApplet.class" archive="'+basePath+'fr-applet-7.0.jar" width="0" height="0">'
								 +'	<param name="isIE9" value="0">'
								 +'	<param name="isShowDialog" value="false">'
								 +'	<param name="jsessionid" value="'+jsessionid+'">'
								 +'	<param name="isSingleSheet" value="false">'
								 +'	<param name="codebase_lookup" value="false">'
								 +'</applet>';
				
			var mydiv = window.document.createElement("div");
			document.body.appendChild(mydiv);
			mydiv.innerHTML = appletPrinter_html;
			window.mydiv=mydiv;
		}
		/**
		*	获取打印session,（ 该功能已被放弃
		**/
		function plus_getReportSessionID(reportUrl){
			var url = "/ReportServer?reportlet="+reportUrl+"&op=getSessionID&time="+new Date().getTime();
			var myAjax =new Ajax(url);
			myAjax.submit();
			var sessionID =myAjax.getText();
			return sessionID;
		}
		function doAppletReportTxtFun(_jsessionid,reportTxt){
			if(reportTxt){
				var url = "/plusPrintServlet;jsessionid="+_jsessionid;
				var myAjax =new Ajax(url);
				myAjax.addParam("reportTxt",reportTxt);
				myAjax.submitPost();
				var vkey =myAjax.getText();
				return "&vKey="+vkey;
			}else{
				return "";
			}
		}
		/**
		*   进入applet打印
		**/
		window._queueArra=[];
		function doAppletPrint(reportUrl,reportTxt,isZoomSize){
			if(isZoomSize){
				isZoomSize = "true";
			}else{
				isZoomSize = "false";
			}
			var bottomFrame = top.document.getElementById("topFrame");
			var _cwd = bottomFrame.contentWindow || bottomFrame.contentDocument;
			var PlusPrintApplet = _cwd["PlusPrintApplet"];//获取applet对象
			var isLOK = isAppletLoadOK(_cwd,PlusPrintApplet);
			if(isLOK==-10){
				return;
			}
			if(isLOK){
				var vkeyStr = doAppletReportTxtFun(_cwd._jsessionid,reportTxt);//提交文本内容
				var urlTemp = _cwd._basePath+'ReportServer;jsessionid='+_cwd._jsessionid+'?reportlet='+reportUrl+'&op=getSessionID'+vkeyStr;
				PlusPrintApplet.printUrlJs(urlTemp,isZoomSize);
			}else{//如果未载入则放入等待打印队列
				var tempArra={};
				tempArra.reportUrl=reportUrl;
				tempArra.reportTxt=reportTxt;
				window._queueArra.push(tempArra);//存入临时空间
				var timer1 =window.setInterval(function(){
					while(window._queueArra.length>0){
						var PlusPrintApplet = _cwd["PlusPrintApplet"];
						var isLOK = isAppletLoadOK(_cwd,PlusPrintApplet);
						if(isLOK==-10){
							window.clearInterval(timer1);
							return;
						}
						if(isLOK){
							var tempArra = window._queueArra.shift();//从临时空间取值
							var vkeyStr = doAppletReportTxtFun(_cwd._jsessionid,tempArra.reportTxt);
							var urlTemp = _cwd._basePath+'ReportServer;jsessionid='+_cwd._jsessionid+'?reportlet='+tempArra.reportUrl+'&op=getSessionID'+vkeyStr;
							PlusPrintApplet.printUrlJs(urlTemp,isZoomSize);
						}else{
							break;
						}
					}
					if(window._queueArra.length<1){
						window.clearInterval(timer1);
					}
				},2000);
			}
		}
		function isAppletLoadOK(_cwd,PlusPrintApplet){
			var isLOK = _cwd._basePath && _cwd._jsessionid && PlusPrintApplet;
			if(isLOK){
				try{
					if(PlusPrintApplet.getInitState()){
						isLOK = true;
					}else{
						isLOK = false;
					}
				}catch(e){
					alert("请确认是否安装了jdk或者联系管理员！");
					isLOK = -10;				
				}
			}
			return isLOK;
		}
		/**
		* 调用打印
		**/
		function doFRPrint(reportUrl, reportTxt, isEncode, isZoomSize){
			var bottomFrame = top.document.getElementById("topFrame");
			var _cwd = bottomFrame.contentWindow || bottomFrame.contentDocument;
			if(_cwd.getVDyfs()!=1){
				doAppletPrint(reportUrl,reportTxt,isZoomSize);
			}else{
				var printForm = _cwd.document.getElementById("printForm");
				var rtObj = _cwd.document.getElementById("reportTxt");
				var ruObj = _cwd.document.getElementById("reportUrl");
				ruObj.value=reportUrl;
				if(reportTxt){
					printForm.action="/common/print/c_print.jsp";
					rtObj.value=reportTxt;
				}else{
					printForm.action="/common/print/j_print.jsp";
				}
				printForm.submit();
			}
		}
		/**
		*	其它页面载入applet打印器
		**/
		function Load_Bottom_AppletPrint(){
			var bottomFrame = top.document.getElementById("topFrame");
			var _cwd = bottomFrame.contentWindow || bottomFrame.contentDocument;
			window.setTimeout(
				function(){
					_cwd.Load_Applet_doURLAppletPrint();
				},500
			);
		}
		/**
		*	获取状态
		**/
		function isOK_Bottom_AppletPrint(){
			var bottomFrame = top.document.getElementById("topFrame");
			var _cwd = bottomFrame.contentWindow || bottomFrame.contentDocument;
			var PlusPrintApplet = _cwd["PlusPrintApplet"];
			if(PlusPrintApplet){
				window.status = "---applet-init:"+PlusPrintApplet.getInitState()+"---当前活动数："+PlusPrintApplet.getActiveCount()+"----当前等待数："+PlusPrintApplet.getQueueSize()+"---------时间："+new Date().getTime();
			}else{
				window.status = "---applet-未初始化";
			}
		}
/**
* 是否设置
**/
function setTokenOfForm(formObj,isToken){
	if(formObj==null){
		return;
	}
	if(typeof(formObj) == "string"){
		formObj = $id(formObj) || $name(formObj);
	}
	
	if(isToken=='false' || isToken==false || isToken==0){
		formObj._isToken_hidden=false;
	}else{
		formObj._isToken_hidden=true;
	}
	
	if(formObj.oldsubmit){}else{
		checkOnsubmit(formObj);
	}
}
/**
*判断是否需要去服务器重取
**/
function isLoginFun(){
	try{
		var bottomFrame = top.document.getElementById("topFrame");
		if(bottomFrame==null){
			return;
		}
		var _cwd = bottomFrame.contentWindow || bottomFrame.contentDocument;
		var msessObj = _cwd["msess"];
		if(msessObj && msessObj.value){
			var isLocalWindow = msessObj.getAttribute("isNotLocalWindow");
			if(isLocalWindow=='true'){
				var myAjax = new Ajax("/login/isLoginFun_login.action?jsessid="+msessObj.value);
				myAjax.submit();
				var result = myAjax.getText();
			}
		}
	}catch(e){}
}
eventManager.add(window, "focus", isLoginFun);
/**
*模拟失焦事件
**/
function blurfun(){
	try{
		var bottomFrame = top.document.getElementById("topFrame");
		var _cwd = bottomFrame.contentWindow || bottomFrame.contentDocument;
		var msessObj = _cwd["msess"];
		msessObj.setAttribute("isNotLocalWindow",false);
		msessObj.setAttribute("isNotLocalwta",null);
		var wta = window.setTimeout(function(){
			msessObj.setAttribute("isNotLocalWindow",true);
		},300);
	}catch(e){}
//	msessObj.setAttribute("isNotLocalwta",wta);
}
eventManager.add(window, "blur", blurfun);