/**
 * 这段代码检测浏览器.
 */

(function(){
window.undefined = window.undefined;
document.head = document.getElementsByTagName('head')[0];

window.fAppVersion = parseFloat(navigator.appVersion);
window.sUserAgent = navigator.userAgent.toLowerCase();
var ua= navigator.userAgent.toLowerCase();

window.isIE = ua.indexOf("msie") > -1;
window.isIE7 = ua.indexOf("msie 7") > -1;
window.isFF = ua.indexOf("firefox") > -1 ;
window.isOpera = ua.indexOf("opera") > -1;

window.isWebkit = (/webkit|khtml/).test(ua);
window.isSafari = ua.indexOf("safari") > -1 || window.isWebkit ;
window.isGecko = window.isMoz =!window.isSafari && ua.indexOf("gecko") > -1;

window.isStrict = document.compatMode == "CSS1Compat";
window.isBorderBox = window.isIE && !window.isStrict;
window.isSecure = window.location.href.toLowerCase().indexOf("https") === 0;

window.isWindows = (ua.indexOf("windows") != -1 || ua.indexOf("win32") != -1);
window.isMac = (ua.indexOf("macintosh") != -1 || ua.indexOf("mac os x") != -1);
window.isLinux = (ua.indexOf("linux") != -1);

/**
 * 如果Array没有push方法则添加push方法.
 */
if (!Array.prototype.push){
    Array.prototype.push = function(elem)
    {
        this[this.length] = elem;
    }
}

	if ((window.isGecko || window.isFF) && HTMLElement){
		HTMLElement.prototype.__defineGetter__("innerText", function(){	return this.textContent;});
	}
if (window.isMoz) {

    Document.prototype.readyState = 0;
    Document.prototype.onreadystatechange = null;

    Document.prototype.__changeReadyState__ = function (iReadyState) {
        this.readyState = iReadyState;

        if (typeof this.onreadystatechange == "function") {
            this.onreadystatechange();
        }
    };

    Document.prototype.__initError__ = function () {
        this.parseError.errorCode = 0;
        this.parseError.filepos = -1;
        this.parseError.line = -1;
        this.parseError.linepos = -1;
        this.parseError.reason = null;
        this.parseError.srcText = null;
        this.parseError.url = null;
    };

    Document.prototype.__checkForErrors__ = function () {

        if (this.documentElement.tagName == "parsererror") {

            var reError = />([\s\S]*?)Location:([\s\S]*?)Line Number (\d+), Column (\d+):<sourcetext>([\s\S]*?)(?:\-*\^)/;

            reError.test(this.xml);

            this.parseError.errorCode = -999999;
            this.parseError.reason = RegExp.$1;
            this.parseError.url = RegExp.$2;
            this.parseError.line = parseInt(RegExp.$3);
            this.parseError.linepos = parseInt(RegExp.$4);
            this.parseError.srcText = RegExp.$5;
        }
    };


    Document.prototype.loadXML = function (sXml) {

        this.__initError__();

        this.__changeReadyState__(1);

        var oParser = new DOMParser();
        var oXmlDom = oParser.parseFromString(sXml, "text/xml");

        while (this.firstChild) {
            this.removeChild(this.firstChild);
        }

        for (var i=0; i < oXmlDom.childNodes.length; i++) {
            var oNewNode = this.importNode(oXmlDom.childNodes[i], true);
            this.appendChild(oNewNode);
        }

        this.__checkForErrors__();

        this.__changeReadyState__(4);

    };

    Document.prototype.__load__ = Document.prototype.load;

    Document.prototype.load = function (sURL) {
        this.__initError__();
        this.__changeReadyState__(1);
        this.__load__(sURL);
    };

    Node.prototype.__defineGetter__("xml", function () {
        //var oSerializer = new XMLSerializer();
        return (new XMLSerializer()).serializeToString(this, "text/xml");
    });

}

/**
 * 封装解析xpath的selectNodes 及selectSingleNode方法.
 */
if( document.implementation.hasFeature("XPath", "3.0") )
{
	XMLDocument.prototype.selectNodes = function(cXPathString, xNode)
	{
		if( !xNode ) { xNode = this; }

		var oNSResolver = this.createNSResolver(this.documentElement)
		var aItems = this.evaluate(cXPathString, xNode, oNSResolver, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null)
		var aResult = [];
		for( var i = 0; i < aItems.snapshotLength; i++)
		{
			aResult[i] =  aItems.snapshotItem(i);
		}

		return aResult;
	}
	XMLDocument.prototype.selectSingleNode = function(cXPathString, xNode)
	{
		if( !xNode ) { xNode = this; }

		var xItems = this.selectNodes(cXPathString, xNode);
		if( xItems.length > 0 )
		{
			return xItems[0];
		}
		else
		{
			return null;
		}
	}
	Element.prototype.selectNodes = function(cXPathString)
	{
		if(this.ownerDocument.selectNodes)
		{
			return this.ownerDocument.selectNodes(cXPathString, this);
		}
		else{
			$warn("dom元素不支持selectNodes方法.");
		}
	}

	Element.prototype.selectSingleNode = function(cXPathString)
	{
		if(this.ownerDocument.selectSingleNode)
		{
			return this.ownerDocument.selectSingleNode(cXPathString, this);
		}
		else{
			$warn("dom元素不支持selectSingleNode方法.");
		}
	}

}


})();






/**
 * 封装不同浏览器创建xmlDocument的不同,并为其加上load(sUrl),loadXml(xmlStr)等方法.
 * @return 返回xmlDocument对象.
 * @type xmlDocument
 */
function createXmlDom(xmlStr) {
    if (window.ActiveXObject) {
        var arrSignatures = ["MSXML2.DOMDocument.5.0", "MSXML2.DOMDocument.4.0",
                             "MSXML2.DOMDocument.3.0", "MSXML2.DOMDocument",
                             "Microsoft.XmlDom"];
        for (var i=0; i < arrSignatures.length; i++) {
            try {
                var oXmlDom = new ActiveXObject(arrSignatures[i]);
				if (xmlStr){
					oXmlDom.loadXML(xmlStr);
				}
                return oXmlDom;
            } catch (oError) {
                //ignore
            }
        }
        throw new Error("MSXML is not installed on your system.");
    } else if (document.implementation && document.implementation.createDocument) {
        var oXmlDom = document.implementation.createDocument("","",null);
        oXmlDom.parseError = {
            valueOf: function () { return this.errorCode; },
            toString: function () { return this.errorCode.toString() }
        };
        oXmlDom.__initError__();
        oXmlDom.addEventListener("load", function () {
            this.__checkForErrors__();
            this.__changeReadyState__(4);
        }, false);

		if (xmlStr){
			oXmlDom.loadXML(xmlStr);
		}
        return oXmlDom;

    } else {
        throw new Error("Your browser doesn't support an XML DOM object.");
    }
}


/**
 * @private
 */
function $e(id,doc) {
	doc = doc||document;
	if (typeof(id)=='object'){
		return id;
	}
	var el=$name(id)||$id(id);
	return el;
}

/**
 * 根据ID获取对象的方法.
 * @param {String} id
 * @param {Document} doc
 * @type Element
 * @return 返回ID相对应的元素.
 */
function $id(id,doc) {
	doc = doc||document;
	if (typeof(id)=='object'){
		return id;
	}
    var elem = doc.getElementById(id);
    var returnObj;
    if(isIE&&elem){
    	if(elem.id===id){
    		returnObj = elem;
    	}else{
			var elems = doc.all[id];
			if(elems && elems.length){
				for(var i=0;i<elems.length;i++){
	    			if(elems[i].id===id){
	    				returnObj = elems[i];
	    				break;
	    			}
	    		}
			}
    	}
    }else{
    	returnObj = elem;
    }
    return returnObj || $o(id) || null;
}
/**
 * 根据ID获取对象的方法.regId该方法仅用于使用EOS的迭代生成带下标的ID对象的获取。
 * 如：<input id="cust[1]/name"/> , <input id="cust[2]/name/>等一组对象的获取。
 * 页面元素的id在EOS中是不允许重复的。
 * @param {String} regId id的表达式
 * @type Array
 * @return 返回符合条件的数组.
 */
function $ids(regId){
 	if(regId.indexOf("[*]")>-1){
		var result = [];
		for(var i=1;i<Number.MAX_VALUE;i++){
			var id = regId.replace("[*]","[" + i + "]");
			var elem = $id(id);
			if(elem){
				result.push(elem);
			}else{
				break;
			}
		}
		return result;
	}else{
		var result = [];
		var elem = $id(regId);
		if(elem){
			result.push(elem);
		}
		return result;
	}

}
/**
 * 返回第一个name属性等于name参数的元素.当regName它有[*]时,
 * 它会将[*]替换为[1],[2],[3]...查找元素,返回查找到的数组.
 * @param {String} regName regName可以有一个[*]表达式.
 * @type {Element}
 * @return 返回符合条件的数组.
 */
function $names(regName){
	if (typeof(regName)=='object'){
		return regName;
	}
	if(regName.indexOf("[*]")>-1){
		var result = [];
		for(var i=1;i<Number.MAX_VALUE;i++){
			var name = regName.replace("[*]","[" + i + "]");
			var elem = $name(name);
			if(elem){
				result.push(elem);
			}else{
				break;
			}
		}
		return result;
	}else{
		var elems = document.getElementsByName(regName);
		if(isIE){
			var result = [];
			for(var i=0;i<elems.length;i++){
				if(elems[i].name == regName){
					result.push(elems[i]);
				}
			}
			return result;
		}else{
			return elems;
		}
	}
}
/**
 * 增加$name函数来根据name获取元素
 * @param {Object} elemName
 */
function $name(elemName){
	if (typeof(elemName)=='object'){
		return elemName;
	}
	var elems = document.getElementsByName(elemName);
	if (!elems) {
		return null;
	}
	if(isIE){
		for(var i=0;i<elems.length;i++){
			if(elems[i].name == elemName){
				return elems[i];
			}
		}
	}else if(elems.length>0){
		return elems[0];
	}
	return null;
}


function $indexOf(arr,item){
    for (var i = 0, length = arr.length; i < length; i++)
	   if (arr[i] == item) return i;
    return -1;
}

function $contains(arr,item){
	return $indexOf(arr,item)>=0;

}


function $remove(arr,item){
	var idx= $indexOf(arr,item);
	if (idx>=0){
		return arr.splice(idx, 1);
	}
}

function isArray(obj){
	return  obj  && typeof(obj.sort) === 'function' && typeof(obj.join) === 'function';
}

/**
 * 跨浏览器设置innerHTML的方法，该方法可以执行里面的javascript,
 * 但该方法执行过程中，不能再执行设置innerHTML或对dom进行操作.
 * 如果htmlCode中有window的onload事件，则不会执行.
 * 该方法在ie下禁用了日志功能.
 * 该方法不支持document.write
 * 实现说明：ie下读取所有script,将带src的放入一个数组,
 * 用loadNext方法一个一个地载入,最后再执行其它脚本.
 * @param {Object} el 要设置的dom元素,一般为div
 * @param {Object} htmlCode 要设置的innerHTML
 * @param {Object} doc 要设置的document
 */
var setInnerHTML = function (el, htmlCode,doc) {

	el.innerHTML = "";
	doc = doc||document;
	var back_write = doc.write;
	doc.write = function() {
   		/*var body = doc.getElementsByTagName('body')[0];
	    for (var i = 0; i < arguments.length; i++) {
	        argument = arguments[i];
	        if (typeof argument == 'string') {
	            var el = doc.body.appendChild(doc.createElement('div'));
	            setInnerHTML(el, argument,doc)
	        }
    	}*/
	}
	var cacheScripts = doc.getElementsByTagName("script");
	var cacheSRC = {};
	for(var i=0;i<cacheScripts.length;i++){
		if(cacheScripts[i].src){
			cacheSRC[cacheScripts[i].src] = true;
		}else{
			cacheSRC[cacheScripts[i].src] = false;
		}
	}
    if (isIE) {
        htmlCode = '<div style="display:none">for IE</div>' + htmlCode;
		var div = $create("div",doc);
		div.innerHTML = htmlCode;
		var scripts = div.getElementsByTagName("script");
		var execScripts = [];
		var srcArray = [];
		for(var i=scripts.length-1;i>-1;i--){
			var script = scripts[i];
			if(script.src){
				if(!cacheSRC[script.src]){
					srcArray.push(script.src);
					cacheSRC[script.src] = true;
				}
				script.parentNode.removeChild(script);
			}else{
				execScripts.push(script);
				//script.defer = true;
			}
		}
		function setHTML(){
			//$debug("set html" + div.innerHTML);
       		el.innerHTML = div.innerHTML;
        	el.removeChild(el.firstChild);
			for(var i=execScripts.length-1;i>=0;i--){
				eval(execScripts[i].innerHTML);
			}
		}
		var index=srcArray.length-1;
		loadNext();
		function loadNext(){
			if(srcArray[index]){
				var sc = $create("script",doc);
				sc.src = srcArray[index];
				var first = doc.body.firstChild;
				if(first){
					doc.body.insertBefore(sc,first);
				}else{
					doc.body.appendChild(sc);
				}
				index--;
				//$debug("load script:" + sc.src + index);
				sc.onreadystatechange = function(){
					if(sc.readyState=="loaded"||sc.readyState=="complete"){
						sc.onreadystatechange = null;
						loadNext();
					}
				}
			}else{
				setHTML();
			}
		}
    }
    else {
        var el_next = el.nextSibling;
        var el_parent = el.parentNode;
        el_parent.removeChild(el);
		el.innerHTML = htmlCode;
        if (el_next) {
            el_parent.insertBefore(el, el_next)
        } else {
            el_parent.appendChild(el);
        }
    }
}


/**
 * @private 私有方法，设置庶盖层的宽和高.
 * @param {Object} mask
 */
function setMaskSize(mask) {
	var theBody = document.getElementsByTagName("BODY")[0];

	var fullHeight = getViewportHeight();
	var fullWidth = getViewportWidth();

	// Determine what's bigger, scrollHeight or fullHeight / width
	if (fullHeight > theBody.scrollHeight) {
		popHeight = fullHeight;
	} else {
		popHeight = theBody.scrollHeight;
	}
	if (fullWidth > theBody.scrollWidth) {
		popWidth = fullWidth;
	} else {
		popWidth = theBody.scrollWidth;
	}
	mask.style.height = popHeight + "px";
	mask.style.width = popWidth + "px";
}
/**
 * @private
 * 私有方法，得到可见区的高.
 */
function getViewportHeight() {
	if (window.innerHeight!=window.undefined) return window.innerHeight;
	if (document.compatMode=='CSS1Compat') return document.documentElement.clientHeight;
	if (document.body) return document.body.clientHeight;
	return window.undefined;
}
/**
 * @private
 * 私有方法，得到可见区的宽.
 */
function getViewportWidth() {
	if (window.innerWidth!=window.undefined) return window.innerWidth;
	if (document.compatMode=='CSS1Compat') return document.documentElement.clientWidth;
	if (document.body) return document.body.clientWidth;
	return window.undefined;
}

/**
 * 将宽或高转换为可以使用的类型，如果输入为数字则加px
 * 如：length=1则返回"1px"
 * 如果输入为字符串，带单位的返回本身，否则加px
 * @param {Object} length
 */
function toLength(length){
	if(isNaN(Number(length))){
		return length;
	}else{
		return length + "px";
	}
}



/* ============ PageControl 相关函数 ============ */
var __page__components;
if(typeof(__page__components)=="undefined"){
	__page__components = {};
}




/**
 * @class 页面控制类,该类控制页面中的js对象.
 */
function PageControl(){}




PageControl.add = function (id,obj){
	if(!__page__components[id]){
		__page__components[id] = obj;
	}else{
		if(__page__components[id + "__is__array__"]){
			__page__components[id].push(obj);
		}else{
			var temp = [];
			temp.push(__page__components[id]);
			temp.push(obj);
			__page__components[id] = temp;
			__page__components[id + "__is__array__"] = true;
		}
	}

	var rel =__page__components_rel;
	if (obj.registerSubComponent && rel[id]){
		for (var i=0;i<rel[id].length;i++ ){
			if (rel[id][i]){
				obj.registerSubComponent(rel[id][i]);
				rel[id][i]=null;
			}
		}
	}

}


var __page__components_rel;
if(typeof(__page__components_rel)=="undefined"){
	__page__components_rel = {};
}

PageControl.registerRelation = function(pid,id){
	if (!pid || !id ){ 
		return;
	}
	var pObj = PageControl.getOne(pid);
	var obj = PageControl.getOne(id);
	if (pObj && pObj.registerSubComponent){
		pObj.registerSubComponent(id);
	}else if ((pid+'').indexOf('xml:')!=0){
		var rel =__page__components_rel;
		rel[pid]=rel[pid]||[];
		rel[pid].push(id);
	}

}

top.currStack = top.currStack||[];
PageControl.setFocus = function(obj,parent){
	var id;
	if(obj){
		id = obj.id;
	}
	var index = top.currStack.length-1;
	for(;index>-1;index--){
		var stackObj = top.currStack[index];
		if(stackObj){
			if(stackObj==obj){
				break;
			}else if(stackObj==parent){
				break;
			}else{
				if(stackObj.hide){
					try{
						stackObj.hide();
					}catch(e){

					}
				}
				if(index>=0){
					try{
					top.currStack.splice(index,1);
					}catch(e){

					}
				}

			}
		}
	}
	if(obj){
		top.currStack.push(obj);
	}
}

PageControl.getCurrComp = function(){
	if(top.currStack.length>0){
		return top.currStack[top.currStack.length-1];
	}else{
		return null;
	}
}

PageControl.addtoStack = function(obj){
	if(!$contains(top.currStack,obj)){
		top.currStack.push(obj);
	}
}

/**
 * 根据id获得js对象的方法，获得对象的方法,
 * 如果id对应单个对象则返回对象本身,
 * 如果对应是数组则返回数组.
 * @param {Object} id
 * @return 返回js对象或数组.
 */
PageControl.get = function(id){
	return __page__components[id];
}

/**
 * 获取一个对象的方法,
 * 如果id对应的是一个数组则返回第一个.
 * @param {Object} id
 * @return 返回js对象或数组的第一项.
 */
PageControl.getOne = function(id){
	if(__page__components[id + "__is__array__"]){
		return __page__components[id][0] || null;
	}else{
		return __page__components[id] || null;
	}
}
$o = PageControl.getOne;


var EOS_FunctionCache={};

function $function(funName , thisObj, argumentList){
	var func= $getFunction(funName) || window[funName];
	if (typeof(func)=='function'){
		return func.apply(thisObj||this,argumentList )
	}
}

function $setFunction(funName ,func ){
	EOS_FunctionCache[funName]=func;
}

function $getFunction(funName){
	return EOS_FunctionCache[funName];
}

function $removeFunction(funName){
	EOS_FunctionCache[funName]=null;
	delete EOS_FunctionCache[funName];
}