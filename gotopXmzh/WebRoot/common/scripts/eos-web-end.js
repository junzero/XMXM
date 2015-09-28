if(EOSKey!=null){
  alert(EOSCANNOTINCLUDE);
}
function isIEFun(){
    if (!!window.ActiveXObject || "ActiveXObject" in window){
        return true;  
    }else{
        return false;  
    }
}
var EOSKey={BACKSPACE:8,TAB:9,ENTER:13,SHIFT:16,CTRL:17,PAUSE:19,CAPSLOCK:20,ESC:27,SPACE:33,PAGEUP:33,PAGEDOWN:34,END:35,HOME:36,LEFT:37,UP:38,RIGHT:39,DOWN:40,INSERT:45,DELETE:46,WIN:91,WIN_R:92,MENU:93,F1:112,F2:113,F3:114,F4:115,F5:116,F6:117,F7:118,F8:119,F9:120,F10:121,F11:122,F12:123,NUMLOCK:144,SCROLLLOCK:145},EOSKeyPress={};
(function(){
  window.undefined=window.undefined;
  document.head=document.getElementsByTagName("head")[0];
  window.fAppVersion=parseFloat(navigator.appVersion);
  window.sUserAgent=navigator.userAgent.toLowerCase();
  var $=navigator.userAgent.toLowerCase();
  window.isIE9=$.indexOf("msie 9.0")>-1;
  window.isIE10=$.indexOf("msie 10.0")>-1;
  if(window.isIE9||window.isIE10){
    window.isIE=false;
  }else {
    window.isIE=$.indexOf("msie")>-1;
  }
  window.isIE7=$.indexOf("msie 7")>-1;
  window.isFF=$.indexOf("firefox")>-1;
  window.isOpera=$.indexOf("opera")>-1;
  window.isWebkit=(/webkit|khtml/).test($);
  window.isSafari=$.indexOf("safari")>-1||window.isWebkit;
  window.isGecko=window.isMoz=!window.isSafari&&$.indexOf("gecko")>-1;
  window.isStrict=document.compatMode=="CSS1Compat";
  window.isBorderBox=window.isIE&&!window.isStrict;
  window.isSecure=window.location.href.toLowerCase().indexOf("https")===0;
  window.isWindows=($.indexOf("windows")!=-1||$.indexOf("win32")!=-1);
  window.isMac=($.indexOf("macintosh")!=-1||$.indexOf("mac os x")!=-1);
  window.isLinux=($.indexOf("linux")!=-1);
  if(!Array.prototype.push){
    Array.prototype.push=function($){
      this[this.length]=$;
    };
  }
  if(isIEFun() && window.isIE==false){
  	window.isIE=false;
  	window.isIE11=true;
  	window.isGecko = false;
  	window.isMoz = false;
  	window.isWebkit = true;
  }
  if((window.isGecko||window.isFF)&&HTMLElement){
    HTMLElement.prototype.__defineGetter__("innerText",function(){
      return this.textContent;
    });
  }
  if(window.isMoz){
    Document.prototype.readyState=0;
    Document.prototype.onreadystatechange=null;
    Document.prototype.__changeReadyState__=function($){
      this.readyState=$;
      if(typeof this.onreadystatechange=="function"){
        this.onreadystatechange();
      }
    };
    Document.prototype.__initError__=function(){
      this.parseError.errorCode=0;
      this.parseError.filepos=-1;
      this.parseError.line=-1;
      this.parseError.linepos=-1;
      this.parseError.reason=null;
      this.parseError.srcText=null;
      this.parseError.url=null;
    };
    Document.prototype.__checkForErrors__=function(){
      if(this.documentElement.tagName=="parsererror"){
        var $=/>([\s\S]*?)Location:([\s\S]*?)Line Number (\d+), Column (\d+):<sourcetext>([\s\S]*?)(?:\-*\^)/;
        $.test(this.xml);
        this.parseError.errorCode=-999999;
        this.parseError.reason=RegExp.$1;
        this.parseError.url=RegExp.$2;
        this.parseError.line=parseInt(RegExp.$3);
        this.parseError.linepos=parseInt(RegExp.$4);
        this.parseError.srcText=RegExp.$5;
      }
    };
    Document.prototype.loadXML=function(_){
      this.__initError__();
      this.__changeReadyState__(1);
      var $=new DOMParser(),B=$.parseFromString(_,"text/xml");
      while(this.firstChild){
        this.removeChild(this.firstChild);
      }
      for(var C=0;C<B.childNodes.length;C++){
        var A=this.importNode(B.childNodes[C],true);
        this.appendChild(A);
      }
      this.__checkForErrors__();
      this.__changeReadyState__(4);
    };
    Document.prototype.__load__=Document.prototype.load;
    Document.prototype.load=function($){
      this.__initError__();
      this.__changeReadyState__(1);
      this.__load__($);
    };
    Node.prototype.__defineGetter__("xml",function(){
      return (new XMLSerializer()).serializeToString(this,"text/xml");
    });
  }
  if(document.implementation.hasFeature("XPath","3.0")){
    XMLDocument.prototype.selectNodes=function(A,D){
      if(!D){
        D=this;
      }
      var _=this.createNSResolver(this.documentElement),$=this.evaluate(A,D,_,XPathResult.ORDERED_NODE_SNAPSHOT_TYPE,null),C=[];
      for(var B=0;B<$.snapshotLength;B++){
        C[B]=$.snapshotItem(B);
      }
      return C;
    };
    XMLDocument.prototype.selectSingleNode=function(A,_){
      if(!_){
        _=this;
      }
      var $=this.selectNodes(A,_);
      if($.length>0){
        return $[0];
      }else {
        return null;
      }
    };
    Element.prototype.selectNodes=function($){
      if(this.ownerDocument.selectNodes){
        return this.ownerDocument.selectNodes($,this);
      }else {
        $warn("dom\u934f\u51aa\u790c\u6d93\u5d86\u656e\u93b8\u4e7belectNodes\u93c2\u89c4\u7876.");
      }
    };
    Element.prototype.selectSingleNode=function($){
      if(this.ownerDocument.selectSingleNode){
        return this.ownerDocument.selectSingleNode($,this);
      }else {
        $warn("dom\u934f\u51aa\u790c\u6d93\u5d86\u656e\u93b8\u4e7belectSingleNode\u93c2\u89c4\u7876.");
      }
    };
  }
})();
function createXmlDom(_){
  if(isIEFun()){
    var B=["MSXML2.DOMDocument.5.0","MSXML2.DOMDocument.4.0","MSXML2.DOMDocument.3.0","MSXML2.DOMDocument","Microsoft.XmlDom"];
    for(var $=0;$<B.length;$++){
      try{
        var A=new ActiveXObject(B[$]);
        if(_){
          A.loadXML(_);
        }
        return A;
      }
      catch(C){
      }
    }
    throw new Error("MSXML is not installed on your system.");
  }else {
    if(document.implementation&&document.implementation.createDocument){
      A=document.implementation.createDocument("","",null);
      A.parseError={valueOf:function(){
          return this.errorCode;
        },toString:function(){
          return this.errorCode.toString();
        }};
      A.__initError__();
      A.addEventListener("load",function(){
        this.__checkForErrors__();
        this.__changeReadyState__(4);
      },false);
      if(_){
        A.loadXML(_);
      }
      return A;
    }else {
      throw new Error("Your browser doesn't support an XML DOM object.");
    }
  }
}
function $e(A,$){
  $=$||document;
  if(typeof (A)=="object"){
    return A;
  }
  var _=$name(A)||$id(A);
  return _;
}
function $id(A,C){
  C=C||document;
  if(typeof (A)=="object"){
    return A;
  }
  var D=C.getElementById(A),_;
  if((isIE||isIE9||isIE10)&&D){
    if(D.id===A){
      _=D;
    }else {
      var $=C.all[A];
      if($&&$.length){
        for(var B=0;B<$.length;B++){
          if($[B].id===A){
            _=$[B];
            break ;
          }
        }
      }
    }
  }else {
    _=D;
  }
  return _||$o(A)||null;
}
function $ids($){
  if($.indexOf("[*]")>-1){
    var C=[];
    for(var A=1;A<Number.MAX_VALUE;A++){
      var _=$.replace("[*]","["+A+"]"),B=$id(_);
      if(B){
        C.push(B);
      }else {
        break ;
      }
    }
    return C;
  }else {
    C=[],B=$id($);
    if(B){
      C.push(B);
    }
    return C;
  }
}
function $names(_){
  if(typeof (_)=="object"){
    return _;
  }
  if(_.indexOf("[*]")>-1){
    var $=[];
    for(var B=1;B<Number.MAX_VALUE;B++){
      var A=_.replace("[*]","["+B+"]"),C=$name(A);
      if(C){
        $.push(C);
      }else {
        break ;
      }
    }
    return $;
  }else {
    var D=document.getElementsByName(_);
    if((isIE||isIE9||isIE10)){
      $=[];
      for(B=0;B<D.length;B++){
        if(D[B].name==_){
          $.push(D[B]);
        }
      }
      return $;
    }else {
      return D;
    }
  }
}
function $name(A){
  if(typeof (A)=="object"){
    return A;
  }
  var _=document.getElementsByName(A);
  if(!_){
    return null;
  }
  if(isIE||isIE9||isIE10){
    for(var $=0;$<_.length;$++){
      if(_[$].name==A){
        return _[$];
      }
    }
  }else {
    if(_.length>0){
      return _[0];
    }
  }
  return null;
}
function $indexOf($,_){
  for(var A=0,B=$.length;A<B;A++){
    if($[A]==_){
      return A;
    }
  }
  return -1;
}
function $contains(_,$){
  return $indexOf(_,$)>=0;
}
function $remove(A,$){
  var _=$indexOf(A,$);
  if(_>=0){
    return A.splice(_,1);
  }
}
function isArray($){
  return $&&typeof ($.sort)==="function"&&typeof ($.join)==="function";
}
var setInnerHTML=function(el,htmlCode,doc){
  el.innerHTML="";
  doc=doc||document;
  var back_write=doc.write;
  doc.write=function(){
  };
  var cacheScripts=doc.getElementsByTagName("script"),cacheSRC={};
  for(var i=0;i<cacheScripts.length;i++){
    if(cacheScripts[i].src){
      cacheSRC[cacheScripts[i].src]=true;
    }else {
      cacheSRC[cacheScripts[i].src]=false;
    }
  }
  if((isIE||isIE9||isIE10)){
    htmlCode="<div style=\"display:none\">for IE</div>"+htmlCode;
    var div=$create("div",doc);
    div.innerHTML=htmlCode;
    var scripts=div.getElementsByTagName("script"),execScripts=[],srcArray=[];
    for(i=scripts.length-1;i>-1;i--){
      var script=scripts[i];
      if(script.src){
        if(!cacheSRC[script.src]){
          srcArray.push(script.src);
          cacheSRC[script.src]=true;
        }
        script.parentNode.removeChild(script);
      }else {
        execScripts.push(script);
      }
    }
    function setHTML(){
      el.innerHTML=div.innerHTML;
      el.removeChild(el.firstChild);
      for(var i=execScripts.length-1;i>=0;i--){
        eval(execScripts[i].innerHTML);
      }
    }
    var index=srcArray.length-1;
    loadNext();
    function loadNext(){
      if(srcArray[index]){
        var $=$create("script",doc);
        $.src=srcArray[index];
        var _=doc.body.firstChild;
        if(_){
          doc.body.insertBefore($,_);
        }else {
          doc.body.appendChild($);
        }
        index--;
        $.onreadystatechange=function(){
          if($.readyState=="loaded"||$.readyState=="complete"){
            $.onreadystatechange=null;
            loadNext();
          }
        };
      }else {
        setHTML();
      }
    }
  }else {
    var el_next=el.nextSibling,el_parent=el.parentNode;
    el_parent.removeChild(el);
    el.innerHTML=htmlCode;
    if(el_next){
      el_parent.insertBefore(el,el_next);
    }else {
      el_parent.appendChild(el);
    }
  }
};
function setMaskSize(B){
  var _=document.getElementsByTagName("BODY")[0],$=getViewportHeight(),A=getViewportWidth();
  if($>_.scrollHeight){
    popHeight=$;
  }else {
    popHeight=_.scrollHeight;
  }
  if(A>_.scrollWidth){
    popWidth=A;
  }else {
    popWidth=_.scrollWidth;
  }
  B.style.height=popHeight+"px";
  B.style.width=popWidth+"px";
}
function getViewportHeight(){
  if(window.innerHeight!=window.undefined){
    return window.innerHeight;
  }
  if(document.compatMode=="CSS1Compat"){
    return document.documentElement.clientHeight;
  }
  if(document.body){
    return document.body.clientHeight;
  }
  return window.undefined;
}
function getViewportWidth(){
  if(window.innerWidth!=window.undefined){
    return window.innerWidth;
  }
  if(document.compatMode=="CSS1Compat"){
    return document.documentElement.clientWidth;
  }
  if(document.body){
    return document.body.clientWidth;
  }
  return window.undefined;
}
function toLength($){
  if(isNaN(Number($))){
    return $;
  }else {
    return $+"px";
  }
}
var __page__components;
if(typeof (__page__components)=="undefined"){
  __page__components={};
}
function PageControl(){
}
PageControl.add=function(_,B){
  if(!__page__components[_]){
    __page__components[_]=B;
  }else {
    if(__page__components[_+"__is__array__"]){
      __page__components[_].push(B);
    }else {
      var $=[];
      $.push(__page__components[_]);
      $.push(B);
      __page__components[_]=$;
      __page__components[_+"__is__array__"]=true;
    }
  }
  var C=__page__components_rel;
  if(B.registerSubComponent&&C[_]){
    for(var A=0;A<C[_].length;A++){
      if(C[_][A]){
        B.registerSubComponent(C[_][A]);
        C[_][A]=null;
      }
    }
  }
};
var __page__components_rel;
if(typeof (__page__components_rel)=="undefined"){
  __page__components_rel={};
}
PageControl.registerRelation=function($,_){
  if(!$||!_){
    return ;
  }
  var C=PageControl.getOne($),A=PageControl.getOne(_);
  if(C&&C.registerSubComponent){
    C.registerSubComponent(_);
  }else {
    if(($+"").indexOf("xml:")!=0){
      var B=__page__components_rel;
      B[$]=B[$]||[];
      B[$].push(_);
    }
  }
};
top.currStack=top.currStack||[];
PageControl.setFocus=function(C,_){
  try{
    var $;
    if(C){
      $=C.id;
    }
    var B=top.currStack.length-1;
    for(;B>-1;B--){
      var D=top.currStack[B];
      if(D){
        if(D==C){
          break ;
        }else {
          if(D==_){
            break ;
          }else {
            try{
              if(D.hide){
                D.hide();
              }
            }
            catch(A){
            }
            if(B>=0){
              top.currStack.splice(B,1);
            }
          }
        }
      }
    }
    if(C){
      top.currStack.push(C);
    }
  }
  catch(A){
  }
};
PageControl.getCurrComp=function(){
  if(top.currStack.length>0){
    return top.currStack[top.currStack.length-1];
  }else {
    return null;
  }
};
PageControl.addtoStack=function(A){
  if(!$contains(top.currStack,A)){
    try{
      top.currStack.push(A);
    }
    catch($){
      try{
        top.currStack=[];
        top.currStack.push(A);
      }
      catch(_){
      }
    }
  }
};
PageControl.get=function($){
  return __page__components[$];
};
PageControl.getOne=function($){
  if(__page__components[$+"__is__array__"]){
    return __page__components[$][0]||null;
  }else {
    return __page__components[$]||null;
  }
};
$o=PageControl.getOne;
var EOS_FunctionCache={};
function $function($,B,A){
  var _=$getFunction($)||window[$];
  if(typeof (_)=="function"){
    return _.apply(B||this,A);
  }
}
function $setFunction(_,$){
  EOS_FunctionCache[_]=$;
}
function $getFunction($){
  return EOS_FunctionCache[$];
}
function $removeFunction($){
  EOS_FunctionCache[$]=null;
  delete EOS_FunctionCache[$];
}
var EVENTMANAGER_CONTIANS_ERROR="Element \u9428\ufffd tontains\u7481\u5267\u7586\u6fb6\u8fab\u89e6!";
if(!Array.prototype.push){
  Array.prototype.push=function($){
    this[this.length]=$;
  };
}
if(navigator.product=="Gecko"){
  Document.prototype.elementFromPoint=function(_,$){
    this.addEventListener("mousemove",this.elementFromPoint__handler,false);
    var C=this.createEvent("MouseEvents"),B=this.getBoxObjectFor(this.documentElement),A={x:B.screenX,y:B.screenY};
    C.initMouseEvent("mousemove",true,false,this.defaultView,0,_+A.x,$+A.y,_,$,false,false,false,false,0,null);
    this.dispatchEvent(C);
    this.removeEventListener("mousemove",this.elementFromPoint__handler,false);
    return this.elementFromPoint__target;
  };
  Document.prototype.elementFromPoint__handler=function($){
    this.elementFromPoint__target=$.explicitOriginalTarget;
    if(this.elementFromPoint__target.nodeType==Node.TEXT_NODE){
      this.elementFromPoint__target=this.elementFromPoint__target.parentNode;
    }
    if(this.elementFromPoint__target.nodeName.toUpperCase()=="HTML"&&this.documentElement.nodeName.toUpperCase()=="HTML"){
      this.elementFromPoint__target=this.getElementsByTagName("BODY").item(0);
    }
    $.preventDefault();
    $.stopPropagation();
  };
  Document.prototype.elementFromPoint__target=null;
}
if(!document.all){
  try{
    if(window.Node){
      Node.prototype.contains=function($){
        if(!$){
          return false;
        }
        if(this==$){
          return true;
        }
        var C=$.tagName,A=this.getElementsByTagName(C),_=A.length;
        for(var B=0;B<_;B++){
          if(A[B]==$){
            return true;
          }
        }
        return false;
      };
    }
  }
  catch(e){
    alert(EVENTMANAGER_CONTIANS_ERROR);
  }
}
if(typeof (_registryEvent)=="undefined"){
  _registryEvent=[];
}
if(typeof (_registryOutClick)=="undefined"){
  _registryOutClick=[];
}
function eventManager(){
}
eventManager.onLoadFuncList=[];
eventManager.onLoadFunc=function(){
  var A=this;
  for(var $=0;$<eventManager.onLoadFuncList.length;$++){
    var _=eventManager.onLoadFuncList[$];
    _.apply(A,arguments);
  }
};
eventManager.onLoadFunc.hasAdd=false;
eventManager.onLoad=function($,A,B){
  eventManager.onLoadFuncList.push(A);
  if(!eventManager.onLoadFunc.hasAdd){
    eventManager.onLoadFunc.hasAdd=true;
    var _="load";
    A=eventManager.onLoadFunc;
    if($.addEventListener){
      $.addEventListener(_,A,B);
      _registryEvent.push({obj:$,type:_,fn:A,useCapture:B});
      return true;
    }else {
      if($.attachEvent&&$.attachEvent("on"+_,A)){
        B=false;
        _registryEvent.push({obj:$,type:_,fn:A,useCapture:B});
        return true;
      }
    }
  }
  return false;
};
eventManager.add=function(_,A,$,B){
  if(typeof _=="string"){
    _=document.getElementById(_);
  }
  if(_==null||$==null){
    return false;
  }
  if(A=="mousewheel"||A=="mousescroll"){
    A=(window.isGecko)?"DOMMouseScroll":"mousewheel";
  }else {
    if(A=="load"){
      return eventManager.onLoad(_,$,B);
    }
  }
  if(_.addEventListener){
    _.addEventListener(A,$,B);
    _registryEvent.push({obj:_,type:A,fn:$,useCapture:B});
    return true;
  }
  if(_.attachEvent&&_.attachEvent("on"+A,$)){
    _registryEvent.push({obj:_,type:A,fn:$,useCapture:false});
    return true;
  }
  return false;
};
eventManager.addOutClick=function($,A,_){
  _registryOutClick.push({obj:$,fn:A,params:_});
};
eventManager.outClick=function(){
  PageControl.setFocus();
};
eventManager.remove=function(_,A,$,B){
  if(_==null){
    return ;
  }
  if(_.removeEventListener){
    _.removeEventListener(A,$,B);
  }else {
    if(_.detachEvent){
      _.detachEvent("on"+A,$);
    }
  }
  if(_["on"+A]){
    _["on"+A]=null;
  }
};
eventManager.CleanUp=function(){
  if(_registryEvent){
    for(var $=0;$<_registryEvent.length;$++){
      eventManager.remove(_registryEvent[$].obj,_registryEvent[$].type,_registryEvent[$].fn);
      _registryEvent[$].obj=null;
      _registryEvent[$].fn=null;
    }
  }
  ReleaseEvents();
  _registryEvent=null;
  _registryOutClick=null;
  __page__components=null;
  window.onerror=null;
  eventManager.outClick=null;
  if(window["CollectGarbage"]){
    CollectGarbage();
  }
};
eventManager.getElement=function(A){
  A=A||window;
  var _;
  if(A.event){
    _=A.event.srcElement;
  }else {
    var $=eventManager.getEvent(A);
    _=$.target;
  }
  return _.nodeType==3?_.parentNode:_;
};
eventManager.stopPropagation=function(_){
  _=_||window;
  var $=_.event?_.event:eventManager.getEvent(_);
  if(!$){
    return ;
  }
  var A=$.target||$.srcElement;
  if($.stopPropagation){
    if(A.type!="select-one"){
      $.preventDefault();
    }
    $.stopPropagation();
  }else {
    if(A.type!="select-one"){
      $.returnValue=false;
    }
    $.cancelBubble=true;
  }
};
eventManager.stopEvent=eventManager.stopPropagation;
eventManager.getPageScroll=function(_){
  _=_||window;
  var A=_.document.documentElement,B=_.document.body,$=0,C=0;
  $=Math.max(A.scrollLeft,B.scrollLeft);
  C=Math.max(A.scrollTop,B.scrollTop);
  return [$,C];
};
eventManager.getPointX=function(A){
  A=A||window;
  var $=eventManager.getEvent(A),_=$.pageX;
  if(!_&&0!==_){
    _=$.clientX||0;
    if((isIE||isIE9||isIE10)){
      _+=eventManager.getPageScroll(A)[0];
    }
  }
  return _;
};
eventManager.getPointY=function(A){
  A=A||window;
  var $=eventManager.getEvent(A),_=$.pageY;
  if(!_&&0!==_){
    _=$.clientY||0;
    if((isIE||isIE9||isIE10)){
      _+=eventManager.getPageScroll(A)[1];
    }
  }
  return _;
};
eventManager.getWheel=function($){
  $=$||window.event||eventManager.getEvent();
  return ($.wheelDelta)?$.wheelDelta/120:-($.detail||0)/3;
};
eventManager.getEvent=function(A){
  A=A||window;
  if(A.event){
    return A.event;
  }else {
    var $=eventManager.getEvent.caller;
    while($!=null){
      var _=$.arguments[0];
      if(_){
        if(window["Event"]&&_ instanceof Event){
          return _;
        }
      }
      $=$.caller;
    }
    return getEvent();
  }
};
function getEvent(){
  if(document.all){
    return window.event;
  }
  var _=getEvent.caller;
  while(_!=null){
    var $=_.arguments[0];
    if($){
      if(($.constructor==Event||$.constructor==MouseEvent)||(typeof ($)=="object"&&$.preventDefault&&$.stopPropagation)){
        return $;
      }
    }
    _=_.caller;
  }
  return null;
}
eventManager.getKeyCode=function(_){
  _=_||window;
  if(_.event){
    return _.event.keyCode;
  }else {
    var $=eventManager.getEvent(_);
    if(!$){
      return null;
    }
    return $.keyCode;
  }
};
function ReleaseEvents(){
  var A=["focus","blur","click","mousedown","mouseup","mouseover","keydown","keypress","keyup","mouseout","change"],_=function(_){
    var $;
    for($=0;$<A.length;$++){
      if(_["on"+A[$]]){
        _["on"+A[$]]=null;
      }
    }
  },$=document.all||document.getElementsByTagName("*");
  if($){
    for(var B=0;B<$.length;B++){
      _($[B]);
    }
  }
}
function ReleaseElemEvents(C){
  var B=["focus","blur","click","mousedown","mouseup","mouseover","keydown","keypress","keyup","mouseout","change"],A=function(_){
    var $;
    for($=0;$<B.length;$++){
      if(_["on"+B[$]]){
        _["on"+B[$]]=null;
      }
    }
  };
  A(C);
  var _=C.getElementsByTagName("*");
  if(_){
    for(var $=0;$<_.length;$++){
      A(_[$]);
    }
  }
}
eventManager.add(window,"unload",eventManager.CleanUp);
eventManager.add(document,"mousedown",eventManager.outClick);
eventManager.stopBubble=function(_){
  _=_||window;
  var $=_.event?_.event:eventManager.getEvent(_);
  if(!$){
    return ;
  }
  if($.stopPropagation){
    $.stopPropagation();
  }else {
    $.cancelBubble=true;
  }
};
function StringBuffer(){
  this.values=new Array();
}
StringBuffer.prototype.append=function($){
  this.values.push($);
  return this;
};
StringBuffer.prototype.clear=function(){
  return this.values=[];
};
StringBuffer.prototype.toString=function(){
  return this.values.join("");
};
function allTrim($){
  if($){
    return ($+"").replace(/ /g,"");
  }else {
    if($==""){
      return $;
    }
    return null;
  }
}
function trim(A,$){
  if(A!==""&&(!A.replace||!A.length)){
    return A;
  }
  var _=($>0)?(/^\s+/):($<0)?(/\s+$/):(/^\s+|\s+$/g);
  return A.replace(_,"");
}
function lTrim($){
  return trim($,1);
}
function rTrim($){
  return trim($,-1);
}
function substringAfter(A,_){
  if(!A||!_){
    return null;
  }else {
    var $=A.indexOf(_);
    if($>=0){
      $=$+_.length;
      return A.substr($);
    }else {
      return "";
    }
  }
}
function substringBefore(A,_){
  if(!A||!_){
    return null;
  }else {
    var $=A.indexOf(_);
    if($>=0){
      return A.substring(0,$);
    }else {
      return "";
    }
  }
}
function getBytesLen(_){
  if(_){
    var $=/[^\x00-\xff]/g;
    return (_+"").replace($,"aa").length;
  }else {
    if(_==""){
      return 0;
    }
    return -1;
  }
}
function get2BytesCharsLen($){
  if($){
    return $.length;
  }else {
    if($==""){
      return 0;
    }
    return -1;
  }
}
function xmlConversion($){
  if($){
    return ($+"").replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/\'/g,"&apos;").replace(/\"/g,"&quot;");
  }else {
    if($==""){
      return $;
    }
    return null;
  }
}
function htmlConversion($){
  if($){
    return ($+"").replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(" ","&nbsp;");
  }else {
    if($===""){
      return $;
    }
    return null;
  }
}
function dateFormat(B,$,_){
  var A=stringToDate(B,$);
  if(isNaN(A)){
    alert(DATE_FORMAT_ERROR);
  }else {
    return dateToString(A,_);
  }
  return null;
}
function isDate($,A){
  if($===null||$===undefined||$==""){
    return false;
  }
  $=formatSupport($,A);
  var C=A.replace("yyyy","\\d{4}").replace(/MM/,"\\d{2}").replace("dd","\\d{2}").replace("HH","\\d{2}").replace(/mm/,"\\d{2}").replace("ss","\\d{2}").replace(".S",".\\d{1}");
  if(!(new RegExp(C)).test($)){
    return false;
  }
  var B=isFormatDate($,A),_=isFormatTime($,A);
  if(B||_){
    return true;
  }
  return false;
}
function isTimeFormat($){
  if(typeof ($)=="string"){
    return $.indexOf("HH")!=-1&&$.indexOf("mm")!=-1&&$.indexOf("ss")!=-1;
  }else {
    return false;
  }
}
function isDateFormat($){
  if(typeof ($)=="string"){
    return $.indexOf("yyyy")!=-1&&$.indexOf("MM")!=-1&&$.indexOf("dd")!=-1;
  }else {
    return false;
  }
}
function timeFormat(B,$,_){
  var A="00:00:00";
  if(isFormatTime(B,$)){
    if(isTimeFormat(_)){
      A=setTime(B,$,_);
    }
    return A;
  }else {
    alert(TIME_FORMAT_ERROR);
  }
  return A;
}
function isFormatTime(_,$){
  if(!_||!$){
    return false;
  }
  if(_.length!=$.length){
    return false;
  }
  var E=$.indexOf("HH");
  if(E==-1){
    return false;
  }
  var D=_.substring(E,E+2),C=$.indexOf("mm");
  if(C==-1){
    return false;
  }
  var A=_.substring(C,C+2),B=$.indexOf("ss");
  if(B==-1){
    return false;
  }
  var F=_.substring(B,B+2);
  if(!isNumber(D)||D<0||D>23){
    return false;
  }
  if(!isNumber(A)||A<0||A>59){
    return false;
  }
  if(!isNumber(F)||F<0||F>59){
    return false;
  }
  return true;
}
function isFormatDate(F,_){
  if(!F||!_){
    return false;
  }
  if(F.length!=_.length){
    return false;
  }
  var B=_.indexOf("yyyy");
  if(B==-1){
    return false;
  }
  var $=F.substring(B,B+4),D=_.indexOf("MM");
  if(D==-1){
    return false;
  }
  var C=F.substring(D,D+2),A=_.indexOf("dd");
  if(A==-1){
    return false;
  }
  var E=F.substring(A,A+2);
  if(!isNumber($)||$>10000||$<999){
    return false;
  }
  if(!isNumber(C)||C>12||C<1){
    return false;
  }
  if(E>getMaxDay($,C)||E<1){
    return false;
  }
  return true;
}
function formatSupport(_,$){
  if($=="yyyyMMddHHmmss"){
    if(_.length==8){
      _=_+"000000";
    }
  }else {
    if($=="yyyyMMdd"){
      if(_.length==14){
        _=_.substring(0,8);
      }
    }else {
      if($=="yyyy-MM-dd HH:mm:ss"){
        if(_.length==10){
          _=_+" 00:00:00";
        }
      }else {
        if($=="yyyy-MM-dd"){
          if(_.length==20){
            _=_.substring(0,10);
          }
        }
      }
    }
  }
  return _;
}
function stringToDate(V,E){
  var K=new Date();
  if(!V||V==""){
    return K;
  }
  if(!E||E==""){
    E="yyyy-MM-dd";
  }
  var _=E.replace(/[^y|Y]/g,""),B=E.replace(/[^M]/g,""),H=E.replace(/[^d]/g,""),R=E.indexOf(_),P=_.length,T=V.substring(R,R+P)*1;
  if(P==2){
    if(T<50){
      T+=2000;
    }else {
      T+=1900;
    }
  }
  var U=E.indexOf(B),J=V.substring(U,U+B.length)*1-1,M=E.indexOf(H),L=V.substring(M,M+H.length)*1,O=E.replace(/[^H]/g,""),F=E.replace(/[^h]/g,""),N=E.replace(/[^m]/g,""),A=E.replace(/[^S|s]/g,""),S=0;
  if(O&&O!=""){
    var I=E.indexOf(O);
    if(I>-1){
      S=V.substring(I,I+O.length);
    }
  }
  if(F&&F!=""){
    var C=E.indexOf(F);
    if(C>-1){
      S=V.substring(C,C+F.length);
    }
  }
  var Q=0;
  if(N&&N!=""){
    var D=E.indexOf(N);
    if(D>-1){
      Q=V.substring(D,D+N.length);
    }
  }
  var G=0;
  if(A&&A!=""){
    var $=E.indexOf(A);
    if($>-1){
      G=V.substring($,$+A.length);
    }
  }
  return new Date(T,J,L,S,Q,G);
}
function dateToString(E,_){
  if(!_||_==""){
    _="yyyy-MM-dd";
  }
  E=E||new Date();
  var K=E.getFullYear().toString(),C=(E.getMonth()+1).toString(),D=E.getDate().toString(),H=E.getMinutes().toString(),A=E.getSeconds().toString(),J=E.getHours().toString(),G=_.replace(/[^y|Y]/g,"");
  if(K.length<4){
    K="0"+K;
  }
  if(G.length==2){
    K=K.substring(2,4);
  }
  var N=_.replace(/[^M]/g,"");
  if(N.length>1){
    if(C.length==1){
      C="0"+C;
    }
  }
  var B=_.replace(/[^d]/g,"");
  if(B.length>1){
    if(D.length==1){
      D="0"+D;
    }
  }
  var L=_.replace(/[^H]/g,"");
  if(L&&L.length>1){
    if(J.length==1){
      J="0"+J;
    }
  }
  var $=_.replace(/[^h]/g,"");
  if($&&$.length>1){
    if(J-12>0){
      J=(J-12)+"";
    }
    if(J.length==1){
      J="0"+J;
    }
  }
  var M=_.replace(/[^m]/g,"");
  if(M&&M.length>1){
    if(H.length==1){
      H="0"+H;
    }
  }
  var F=_.replace(/[^S|s]/g,"");
  if(F&&F.length>1){
    if(A.length==1){
      A="0"+A;
    }
  }
  var I=_;
  if(G){
    I=I.replace(G,K);
  }
  if(N){
    I=I.replace(N,C);
  }
  if(B){
    I=I.replace(B,D);
  }
  if($){
    I=I.replace($,J);
  }
  if(L){
    I=I.replace(L,J);
  }
  if(M){
    I=I.replace(M,H);
  }
  if(F){
    I=I.replace(F,A);
  }
  return I;
}
function dateToStringValue($){
  var _=String($);
  if(_.length==1){
    _="0"+_;
  }
  return _;
}
function getMaxDay(_,$){
  if($==4||$==6||$==9||$==11){
    return "30";
  }
  if($==2){
    if(_%4==0&&_%100!=0||_%400==0){
      return "29";
    }else {
      return "28";
    }
  }
  return "31";
}
function isNumber(_){
  var $=/^(\d+)$/;
  return $.test(_);
}
function setDate(G,H,E){
  var F=H.indexOf("yyyy"),_=G.substring(F,F+4),D=H.indexOf("MM"),C=G.substring(D,D+2),B=H.indexOf("dd"),$=G.substring(B,B+2),A=E;
  A=A.replace(/yyyy/i,_);
  A=A.replace(/MM/i,C);
  A=A.replace(/dd/i,$);
  return A;
}
function setTime(F,G,A){
  var E="00",_="00",D="00",$=G.indexOf("HH"),C=G.indexOf("mm"),B=G.indexOf("ss");
  if(B!=-1&&$!=-1&&C!=-1){
    D=F.substring(B,B+2);
    _=F.substring(C,C+2);
    E=F.substring($,$+2);
    if(!isNumber(E)||E>"23"||E<"00"){
      alert(DATE_FORMAT_ERROR);
      return "";
    }
    if(!isNumber(_)||_>"59"||_<"00"){
      alert(DATE_FORMAT_ERROR);
      return "";
    }
    if(!isNumber(D)||D>"59"||D<"00"){
      alert(DATE_FORMAT_ERROR);
      return "";
    }
  }else {
    if(!(B==-1&&$==-1&&C==-1)){
      alert(DATE_FORMAT_ERROR);
      return "";
    }
  }
  A=A.replace(/HH/i,E);
  A=A.replace(/mm/i,_);
  A=A.replace(/ss/i,D);
  return A;
}
Number.prototype.NAN0=function(){
  if(isNaN(this)){
    return 0;
  }else {
    return this;
  }
};
function numberToMoney(A,$){
  A=A.toString();
  $=$?$:NUMBER_MONEY_PREFIX;
  if(isNaN(A)){
    A="0";
  }
  var _=(A==(A=Math.abs(A)));
  A=Math.floor(A*100+0.50000000001);
  var C=A%100;
  A=Math.floor(A/100).toString();
  if(C<10){
    C="0"+C;
  }
  for(var B=0;B<Math.floor((A.length-(1+B))/3);B++){
    A=A.substring(0,String(A).length-(4*B+3))+","+A.substring(A.length-(4*B+3));
  }
  return (((_)?"":"-")+$+A+"."+C);
}
function numberToChinese(L){
  var I=99999999999.99,K,M,H="",D,G,$,E,_,A,F,J,C,N,B,O=L;
  O=O.toString();
  if(O==""){
    alert(NUMBER_CHINESE_ERROR_NULL);
    return "";
  }
  if(O.indexOf("-")==0){
    O=O.substr(1);
    H=CN_MINUS;
  }
  if(O.match(/[^,.\d]/)!=null){
    alert(NUMBER_CHINESE_ERROR_CHARATER);
    return "";
  }
  if((O).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/)==null){
    alert(NUMBER_CHINESE_ERROR_CHARATER);
    return "";
  }
  O=O.replace(/,/g,"");
  O=O.replace(/^0+/,"");
  if(Number(O)>I){
    alert(NUMBER_CHINESE_ERROR_LARGE);
    return "";
  }
  D=O.split(".");
  if(D.length>1){
    K=D[0];
    M=D[1];
    M=M.substr(0,2);
  }else {
    K=D[0];
    M="";
  }
  G=new Array(CN_ZERO,CN_ONE,CN_TWO,CN_THREE,CN_FOUR,CN_FIVE,CN_SIX,CN_SEVEN,CN_EIGHT,CN_NINE);
  $=new Array("",CN_TEN,CN_HUNDRED,CN_THOUSAND);
  E=new Array("",CN_TEN_THOUSAND,CN_HUNDRED_MILLION);
  _=new Array(CN_TEN_CENT,CN_CENT);
  if(Number(K)>0){
    A=0;
    for(F=0;F<K.length;F++){
      J=K.length-F-1;
      C=K.substr(F,1);
      N=J/4;
      B=J%4;
      if(C=="0"){
        A++;
      }else {
        if(A>0){
          H+=G[0];
        }
        A=0;
        H+=G[Number(C)]+$[B];
      }
      if(B==0&&A<4){
        H+=E[N];
      }
    }
    H+=CN_DOLLAR;
  }
  if(M!=""){
    for(F=0;F<M.length;F++){
      C=M.substr(F,1);
      if(C!="0"){
        H+=G[Number(C)]+_[F];
      }
    }
  }
  if(H==""){
    H=CN_ZERO+CN_DOLLAR;
  }
  if(M==""){
    H+=CN_INTEGER;
  }
  H=CN_SYMBOL+H;
  return H;
}
function formatNumber(A,_,C){
  var $=_.split(";"),B;
  if($.length==1){
    B=singleFormat(A,_,C);
  }else {
    if($.length==2){
      if(A>0){
        B=singleFormat(A,$[0],C);
      }else {
        B=singleFormat(A,$[1],C);
      }
    }else {
      if($.length==3){
        if(A<0){
          B=singleFormat(A,$[1],C);
        }else {
          if(A>0){
            B=singleFormat(A,$[0],C);
          }else {
            B=singleFormat(A,$[2],C);
          }
        }
      }else {
        alert("error format "+_);
      }
    }
  }
  return B;
}
function singleFormat(L,P,G){
  L=Number(L);
  if(isNaN(L)){
    return L;
  }
  var M,A,F,D,J="",K="",$="",_="",B,I,H,O,C=0,E=0,N="";
  P=(P)?P+"":"";
  if(P.indexOf(",")>=0){
    D=",";
  }
  if(P.indexOf("%")>=0){
    J="%";
    L=L/100;
  }
  if(G){
    N=G;
  }
  s=P.split(".");
  B=((s[0]==""||s[0]==null||s[0]=="undefinded")?"":s[0]);
  B=B.split("").reverse().join("");
  I=(s[1]==""||s[1]==null||s[1]=="undefinded")?"":s[1];
  L=toFixedFunc(L,I.length);
  if(L<0){
    K="-";
  }
  L+="";
  if(K!=""){
    L=L.replace("-","");
  }
  s=L.split(".");
  H=((s[0]==""||s[0]==null||s[0]=="undefinded")?"":s[0]);
  H=H.split("").reverse().join("");
  O=(s[1]==""||s[1]==null||s[1]=="undefinded")?"":s[1];
  if(H){
    C=H.length;
  }
  if(B.length>C){
    C=B.length;
  }
  for(M=0;M<C;M++){
    A=H.charAt(M);
    F=B.charAt(M);
    E++;
    if(E==4&&D&&(A||F=="0")){
      _+=D;
    }
    if(F=="0"&&!A){
      _+="0";
    }else {
      if(A){
        _+=A;
      }
    }
    if(E==4){
      E=1;
    }
  }
  if(I){
    C=I.length;
  }
  for(M=0;M<C;M++){
    A=O.charAt(M);
    F=I.charAt(M);
    if(F=="0"&&!A){
      $+="0";
    }else {
      if((F=="#"||F=="0")&&A){
        $+=A;
      }
    }
  }
  F=((_+"").split("").reverse().join(""))+(($)?"."+$:"");
  if(J=="%"){
    F+=J;
  }else {
    F=J+F;
  }
  return N+K+F;
}
function toFixedFunc($,_){
  return $.toFixed(_);
}
function linkConfirm(A,B,_){
  var $=$id("__eos_confirm_form");
  if(!$){
    $=$create("form");
    $.id="__eos_confirm_form";
    bodyAddNode($);
  }
  $.action=B;
  $.method="post";
  $.target=_;
  if(window.confirm(A)){
    $.submit();
  }
}
function _get_top_window(){
  if(top.document&&(top.document.getElementsByTagName("frameset").length==0)){
    return top;
  }
  var _=window,$=window;
  while(_&&top!=_){
    _=_["parent"];
    if(_&&_.document&&(_.document.getElementsByTagName("frameset").length==0)){
      $=_;
    }
  }
  return $;
}
function moveScript(A,C){
  var _=A.getElementsByTagName("script");
  if(C.parentWindow&&A.parentWindow){
    C.parentWindow.contextPath=A.parentWindow.contextPath;
  }
  var $="";
  for(var B=0;B<_.length;B++){
    if(_[B].src!=""){
      $+="<script src='"+_[B].src+"'></script>";
    }
  }
  var D=$create("div",C);
  D.style.display="none";
  C.body.appendChild(D);
  setInnerHTML(D,$,C);
}
function moveCss(F,D){
  if(true){
    return ;
  }
  var B=F.getElementsByTagName("link");
  for(var C=0;C<B.length;C++){
    var A=B[C].href;
    if(D.createStyleSheet){
      var $=D.createStyleSheet();
      $.addImport(A);
      D.styleSheets.item[D.styleSheets.length]=$;
    }else {
      var E=D.styleSheets.length,_=$create("style",D);
      _.setAttribute("type","text/css");
      D.documentElement.childNodes[0].appendChild(_);
      D.styleSheets[E].insertRule("@import url("+A+");",0);
    }
  }
}
function addContextPath($){
  if($){
    if($.indexOf("/")==0){
      var _=window["contextPath"];
      if($.indexOf(_)!=0){
        return _+$;
      }
    }
  }
  return $;
}
function getParentByTagName($,_){
  if(_&&$){
    _=_.toLowerCase();
    while(($=$.parentNode)){
      if($.tagName&&$.tagName.toLowerCase()==_){
        return $;
      }
    }
    return null;
  }else {
    return $?$:null;
  }
}
function getEventTargetByTagName(A,$,B){
  var _=A.srcElement||A.target;
  if(!_){
    return null;
  }
  B=B||5;
  var $=$.toLowerCase();
  do {
    if(_.tagName&&_.tagName.toLowerCase()==$){
      return _;
    }
  }while((_=_.parentNode)&&((B--)>0));
  return null;
}
function getScreenPosition(C,_){
  var A=window,$=getElementXY(C),D=$[0],B=$[1];
  while(A!=_&&A!=top){
    if(A.frameElement){
      $=getElementXY(A.frameElement);
      D=D+$[0];
      B=B+$[1];
    }
    A=A.parent;
  }
  return [D,B];
}
function addButtonToText(M){
  if(M){
    var A=$create("div");
    A.hideFocus=true;
    A.style.display="block";
    A.style.zIndex=99999;
    M.className="eos-drop-down-text";
    function G(){
      bodyAddNode(A);
      document.body.appendChild(A);
    }
    if((isIE||isIE9||isIE10)){
      if(document.readyState=="complete"){
        G();
      }else {
        eventManager.add(window,"load",G);
      }
    }else {
      try{
        G();
      }
      catch(H){
        eventManager.add(window,"load",G);
      }
    }
    function _(){
      if(!M.getAttribute("_isFocus")&&!M.disabled){
        A.className="eos-drop-down-button-over";
        setButtonPosition(M,A);
        M.className="eos-drop-down-text-over";
      }
    }
    function B(){
      if(!M.disabled&&!M.readOnly){
        A.className="eos-drop-down-button-focus";
        setButtonPosition(M,A);
        M.className="eos-drop-down-text-focus";
      }
    }
    function J(){
      if(!M.getAttribute("_isOver")&&!M.getAttribute("_isFocus")&&!A.getAttribute("_isOver")){
        M.className="eos-drop-down-text";
      }
    }
    function E(){
      M.setAttribute("_isOver",true);
      _();
    }
    function L(){
      M.setAttribute("_isFocus",true);
      B();
      eventManager.stopPropagation();
    }
    function I(){
      A.setAttribute("_isOver",true);
      _();
    }
    function C(){
      A.setAttribute("_isFocus",true);
      B();
    }
    function K(){
      M.setAttribute("_isOver",false);
      setTimeout(J,300);
    }
    function $(){
      M.setAttribute("_isFocus",false);
      setTimeout(J,300);
    }
    function F(){
      A.setAttribute("_isOver",false);
      setTimeout(J,300);
    }
    function D(){
      A.setAttribute("_isFocus",false);
      setTimeout(J,300);
    }
    eventManager.add(M,"mouseover",E);
    eventManager.add(M,"focus",L);
    eventManager.add(M,"click",L);
    eventManager.add(A,"mouseover",I);
    eventManager.add(A,"focus",C);
    eventManager.add(A,"click",C);
    eventManager.add(M,"mouseout",K);
    eventManager.add(M,"blur",$);
    eventManager.add(A,"mouseout",F);
    eventManager.add(A,"blur",D);
    eventManager.addOutClick(M,$);
    return A;
  }
  return null;
}
function hideButton(A,$){
  var B=A.offsetHeight,_=A.offsetWidth;
  A.style.width=_+"px";
}
function setButtonPosition(E,B){
  var $=E.offsetHeight,D=E.offsetWidth;
  E.style.width=D+"px";
  var A=getElementXY(E);
  if(A){
    var C=A[1];
    B.style.position="absolute";
    var _=A[0]*1+D*1-$;
    $=$-(isBorderBox?0:2);
    B.style.width=$+"px";
    B.style.height=$+"px";
    B.style.overflow="hidden";
    B.style.top="0px";
    B.style.left="0px";
    B.style.display="";
    setElementXY(B,[_,C]);
  }
}
function getRect(A){
  var B=getPosition(A),_=B.left+A.offsetWidth,$=B.top+A.offsetHeight;
  return {left:B.left,top:B.top,right:_,bottom:$};
}
function moveSelectedOptions(D,_,C){
  C=Number(C);
  if(isNaN(C)){
    C=Number.MAX_VALUE;
  }
  var A=[];
  for(var B=0;B<D.options.length;B++){
    var $=D.options[B];
    if($.selected){
      A.push($);
    }
  }
  for(B=0;B<A.length;B++){
    if(_.options.length>=C){
      break ;
    }else {
      $=A[B];
      _.appendChild($);
    }
  }
}
function moveAllOptions(D,_,C){
  C=Number(C);
  if(isNaN(C)){
    C=Number.MAX_VALUE;
  }
  var A=[];
  for(var B=0;B<D.options.length;B++){
    var $=D.options[B];
    A.push($);
  }
  for(B=0;B<A.length;B++){
    if(_.options.length>=C){
      break ;
    }
    $=A[B];
    _.appendChild($);
  }
}
function compileTemplate(template,t_start,t_end,t_script,varName){
  var TEMPLATE_START=t_start||"$[",TEMPLATE_END=t_end||"]",TEMPLATE_SCRIPT=t_script||"$",startLength=TEMPLATE_START.length,endLength=TEMPLATE_END.length,scriptLength=TEMPLATE_SCRIPT.length,templateC=[],snippets=[],current=0;
  while(true){
    var start=template.indexOf(TEMPLATE_START,current),sBegin=start+startLength,sEnd=template.indexOf(TEMPLATE_END,sBegin);
    if(sBegin>=startLength&&sEnd>sBegin){
      templateC.push(template.substring(current,start));
      var sn=template.substring(sBegin,sEnd);
      if(sn.indexOf(TEMPLATE_SCRIPT)==0){
        sn=eval(sn.substring(scriptLength));
      }else {
        snippets.push(templateC.length);
      }
      templateC.push(sn);
    }else {
      templateC.push(template.substring(current));
      break ;
    }
    current=sEnd+endLength;
  }
  templateC.snippets=snippets;
  templateC.varName=varName;
  return templateC;
}
function runExpression(E,A){
  var D=E.snippets,B=[];
  for(var C=0,_=0,$=E.length;C<$;C++){
    if(D[_]==C){
      B[C]=A.getProperty(E[C]);
      _++;
    }else {
      B[C]=E[C];
    }
  }
  return B.join("");
}
function sortTableByCol(L,D,J){
  L=$id(L);
  var E=getParentByTagName(L,"table");
  if(!L||!E){
    return ;
  }
  var B;
  try{
    B=E.getElementsByTagName("thead");
    B=B?B[0]:null;
  }
  catch(N){
    B=null;
  }
  var A=E.tBodies[0],M=B&&B.rows.length>0?0:(L.parentNode.rowIndex+1),$=A.rows,H=[];
  for(var G=M;G<$.length;G++){
    H[G-M]=$[G];
    if(!H[G-M].getAttribute("__eos_orgorder")){
      H[G-M].setAttribute("__eos_orgorder",G+1+"");
    }
  }
  var O=K;
  function _($){
    return $.getAttribute("value");
  }
  function K($,_){
    return $.innerText;
  }
  function C($,_){
    return _.getAttribute("__eos_orgorder");
  }
  J=J=="asc"?"desc":"asc";
  if(E.sortCol==L){
    J=L.getAttribute("__eos_sort");
    if(J=="desc"){
      O=C;
      J="default";
      H.sort(sortCompareTRs(L.cellIndex,D,J,O));
    }else {
      if(J=="default"){
        J="asc";
        H.sort(sortCompareTRs(L.cellIndex,D,J,O));
      }else {
        H.reverse();
        J=J=="asc"?"desc":"asc";
      }
    }
  }else {
    H.sort(sortCompareTRs(L.cellIndex,D,J,O));
  }
  var I=document.createDocumentFragment();
  for(G=0;G<H.length;G++){
    I.appendChild(H[G]);
  }
  A.appendChild(I);
  if(E.sortFlag&&E.sortCol!=L){
    E.sortFlag.className="eos-sorttable-default";
  }
  E.sortCol=L;
  var F=L.getElementsByTagName("span");
  if(F.length<1||(F[F.length-1].className+"").indexOf("eos-sorttable-")<0){
    E.sortFlag=$createElement("span");
    L.appendChild(E.sortFlag);
  }else {
    E.sortFlag=F[F.length-1];
  }
  L.setAttribute("__eos_sort",J);
  L.style.position="relative";
  E.sortFlag.className="eos-sorttable-"+J;
  E.sortFlag.innerHTML="&#160;";
}
function sortCompareTRs(_,E,B,D){
  var A=1;
  if(B=="desc"||B=="d"){
    A=-1;
  }
  function C(_,$){
    switch($){
    case "int":
      return parseInt(_);
    case "float":
      return parseFloat(_);
    case "date":
      return ""+_;
    default:
      return ""+_;
    }
  }
  return function $($,G){
    var B,F;
    B=C(D($.cells[_],$),E);
    F=C(D(G.cells[_],G),E);
    if(B<F){
      return -1*A;
    }else {
      if(B>F){
        return 1*A;
      }else {
        return 0;
      }
    }
  };
}
function submitFormBy($,A,_,B){
  if($){
    $.action=_||$.action;
    $.target=B||$.target;
    if($.elements["_eosFlowAction"]){
      $.elements["_eosFlowAction"].value=A;
    }
    $.submit();
  }
}
function gotoPage(A,J,E,D,$,C){
  var G;
  if(C){
    G=$id(C)||$name(C);
  }
  var F,I,H;
  if(G){
    F=G.elements[A+".begin"];
    I=G.elements[A+".length"];
    H=G.elements[A+".count"];
  }else {
    F=$e(A+".begin");
    I=$e(A+".length");
    H=$e(A+".count");
    G=getParentByTagName(F,"form");
  }
  var B=0,J=!J?1:Number(J);
  if(typeof (J)!="number"){
    J=$e(J).value;
  }
  J=parseInt(J/1);
  J=isNaN(J)||J<1?1:J;
  var _=B/1+I.value/1*(J-1)/1;
  F.value=_<0?0:_;
  submitFormBy(G,E,D,$);
}
function firstPage(_,E,D,$,C){
  var B;
  if(C){
    B=$id(C)||$name(C);
  }
  var A;
  if(B){
    A=B.elements[_+".begin"];
  }else {
    A=$e(_+".begin");
    B=getParentByTagName(A,"form");
  }
  A.value=0;
  submitFormBy(B,E,D,$);
}
function prevPage(G,E,D,B,C){
  var I;
  if(C){
    I=$id(C)||$name(C);
  }
  var H,F,$;
  if(I){
    H=I.elements[G+".begin"];
    F=I.elements[G+".length"];
  }else {
    H=$e(G+".begin");
    F=$e(G+".length");
    I=getParentByTagName(H,"form");
  }
  var A=F&&F.value?F.value:0,_=Number(H.value)-Number(F.value);
  H.value=_<0?0:_;
  submitFormBy(I,E,D,B);
}
function nextPage(F,E,D,A,C){
  var H;
  if(C){
    H=$id(C)||$name(C);
  }
  var G,B,$;
  if(H){
    G=H.elements[F+".begin"];
    B=H.elements[F+".length"];
  }else {
    G=$e(F+".begin");
    B=$e(F+".length");
    H=getParentByTagName(G,"form");
  }
  var _=B&&B.value?B.value:0;
  G.value=Number(G.value)+Number(B.value);
  submitFormBy(H,E,D,A);
}
function lastPage(F,E,D,A,C){
  var H;
  if(C){
    H=$id(C)||$name(C);
  }
  var G,B,$;
  if(H){
    G=H.elements[F+".begin"];
    B=H.elements[F+".length"];
    $=H.elements[F+".count"];
  }else {
    G=$e(F+".begin");
    B=$e(F+".length");
    $=$e(F+".count");
    H=getParentByTagName(G,"form");
  }
  var _=Math.floor((($.value/1)+(B.value/1)-1)/(B.value/1));
  G.value=(_-1)*(B.value/1);
  H=getParentByTagName(G,"form");
  submitFormBy(H,E,D,A);
}
function getMaxZindex(_){
  _=_||document;
  var E=_.all||_.getElementsByTagName("*"),D=E.length,B=0;
  if(isIE||isIE9||isIE10){
    for(var $=0;;$++){
      var A=E[$];
      if(A==null){
        break ;
      }
      var C=parseInt(GetCurrentStyle(A,"zIndex"));
      if(!isNaN(C)){
        if(C>B){
          B=C;
        }
      }
    }
  }else {
    for($=0;$<D;$++){
      A=E[$],C=parseInt(GetCurrentStyle(A,"zIndex"));
      if(!isNaN(C)){
        if(C>B){
          B=C;
        }
      }
    }
  }
  return B+1;
}
function getCurrentStyle($,_){
  if($.currentStyle){
    return $.currentStyle[_];
  }else {
    if(window.getComputedStyle){
      _=_.replace(/([A-Z])/g,"-$1");
      _=_.toLowerCase();
      var A=window.getComputedStyle($,"");
      if(A){
        return A.getPropertyValue(_);
      }else {
        return null;
      }
    }
  }
  return null;
}
var GetCurrentStyle=getCurrentStyle;
function setOpacity(_,$){
  _=$e(_);
  if(!_){
    return _;
  }
  $=$>1?1:($<0?0:$);
  if(!_.currentStyle||!_.currentStyle.hasLayout){
    _.style.zoom=1;
  }
  if((isIE||isIE9||isIE10)){
    _.style.filter=($==1)?"":"alpha(opacity="+$*100+")";
  }
  _.style.opacity=$;
  if($===0){
    if(_.style.visibility!="hidden"){
      _.style.visibility="hidden";
    }
  }else {
    if(_.style.visibility!="visible"){
      _.style.visibility="visible";
    }
  }
  return _;
}
function fx_size(B,E,A,D,C){
  var _=$c(B),$=_.effects({duration:C||400}),F={};
  if(E!==null&&E!==undefined){
    F.width=E;
  }
  if(A!==null&&A!==undefined){
    F.height=A;
  }
  $.start(F).chain(D||function(){
  });
}
function fx_fadeIn(_,C,B){
  var $=$c(_);
  $.setOpacity(0);
  $.setStyle("display","");
  var D=$.effects({duration:B||400}),A={"opacity":1};
  D.start(A).chain(C||function(){
  });
}
function fx_fadeOut(_,C,B){
  var $=$c(_),D=$.effects({duration:B||400}),A={"opacity":0};
  D.start(A).chain(C||function(){
    $.setOpacity(0);
    $.setStyle("display","none");
  });
}
function fx_slideIn($,_){
  _=_||new Fx.Slide($);
  _.slideIn();
  return _;
}
function fx_slideOut($,_){
  _=_||new Fx.Slide($);
  _.slideOut();
  return _;
}
function fx_DD(_,A){
  A=A||{};
  var $=new Drag.Move($c(_),A);
}
function getScreenPosition(C,_){
  var A=window,$=getElementXY(C),D=$[0],B=$[1];
  while(A!=_&&A!=top){
    if(A.frameElement){
      $=getElementXY(A.frameElement,A.parent);
      D=D+$[0];
      B=B+$[1];
    }
    A=A.parent;
  }
  return [D,B];
}
function accordionCollapse(A){
  if(A.processaccordion){
    return ;
  }
  if(A.style.display=="none"){
    return ;
  }
  A.processaccordion=true;
  var _=A.nextSibling;
  if(_==null||!_.isAccordion){
    _=document.createElement("div");
    _.isAccordion=true;
    _.style.overflow="hidden";
    var B=A.parentNode;
    B.insertBefore(_,A.nextSibling);
  }
  _.style.width=A.offsetWidth;
  _.style.height=A.offsetHeight;
  _.style.display="";
  A.style.width=A.clientWidth;
  A.style.position="absolute";
  var $=1;
  setTimeout(function(){
    return accordionIn.apply(A,[{"div":_,"acc":A,"height":A.offsetHeight,"progress":0,"step":$}]);
  },41);
}
function accordionExpand(D){
  if(D.processaccordion){
    return ;
  }
  if(D.style.display!="none"){
    return ;
  }
  D.processAccoridion=true;
  var C=D.nextSibling;
  if(C==null||!C.isAccordion){
    C=document.createElement("div");
    C.isAccordion=true;
    var E=D.parentNode;
    C.style.overflow="hidden";
    E.insertBefore(C,D.nextSibling);
  }
  D.style.display="";
  D.style.width=D.clientWidth;
  D.style.position="absolute";
  C.style.width=D.offsetWidth;
  C.style.height=0;
  C.style.display="";
  var _=D.offsetHeight,A=5,$=5;
  while(true){
    $=$+A*3;
    if($>=_){
      break ;
    }else {
      A=A*3;
    }
  }
  var B=_-($-A*3);
  C.style.height=B;
  D.style.marginTop=B*-1;
  D.style.clip="rect("+B+" auto auto auto)";
  setTimeout(function(){
    return accordionOut.apply(D,[{"div":C,"acc":D,"height":_,"progress":B,"step":A}]);
  },41);
}
function accordionIn($){
  $.progress=$.progress+$.step;
  if($.step<12){
    $.step=$.step+1;
  }else {
    $.step=$.step*3;
  }
  $.acc.style.clip="rect("+$.progress+" auto auto auto)";
  $.acc.style.marginTop=$.progress*-1;
  if($.height-$.progress<0){
    $.div.style.display="none";
  }else {
    $.div.style.height=$.height-$.progress;
  }
  if($.progress>=$.height){
    $.acc.style.display="none";
    $.div.style.display="none";
    $.acc.processaccordion=false;
  }else {
    setTimeout(function(){
      return accordionIn.apply($,[$]);
    },41);
  }
}
function accordionOut($){
  if($.height-$.progress<=5){
    $.progress=$.progress+1;
  }else {
    $.progress=$.progress+$.step;
    $.step=$.step/3;
  }
  $.acc.style.clip="rect("+($.height-$.progress)+" auto auto auto)";
  $.acc.style.marginTop=$.progress-$.height;
  $.div.style.height=$.progress;
  if($.progress>=$.height){
    $.acc.style.position="static";
    $.div.style.display="none";
    $.acc.processaccordion=false;
    return ;
  }else {
    setTimeout(function(){
      return accordionOut.apply($,[$]);
    },41);
  }
}
function msgbox($){
  showModalCenter(contextPath+"/common/jsp/msgBox.jsp",[$],null,null,null,"\u93bb\u612e\u305a\u6dc7\u2103\u4f05");
}
function checkOnsubmit(_){
  var A=_.submit,$=_.onsubmit;
  _.oldsubmit=A;
  _.oldOnsubmit=$;
  _.submit=function(){
    if(!checkForm(this)){
      return ;
    }else {
      _tokenSessionFun(_,_._isToken_hidden);
      this.oldsubmit();
    }
  };
  _.onsubmit=function(){
    if(checkForm(this)){
      _tokenSessionFun(_,_._isToken_hidden);
      if(this.oldOnsubmit){
        return this.oldOnsubmit();
      }
    }else {
      return false;
    }
  };
}
function _tokenSessionFun(C,A){
  var D=C.elements["_tokenSessionId"];
  if(A){
    if(D){
    }else {
      var B=new Ajax("/common/attachmentAction_getTokenUuid.action");
      B.submit();
      var _=B.getText();
      if(_){
      }else {
        alert("\u4ee4\u724c\u4e3a\u7a7a");
      }
      var $=document.createElement("input");
      $.setAttribute("type","hidden");
      $.setAttribute("value",_);
      $.setAttribute("id","_tokenSessionId");
      $.setAttribute("name","_tokenSessionId");
      C.appendChild($);
    }
  }else {
    if(D){
      B=new Ajax("/common/attachmentAction_rmTokenUuid.action?tokenId="+D.value);
      B.submit();
      C.removeChild(D);
      alert("111");
    }
  }
}
var moveDivToCenter=function(_){
  var $=_get_top_window()||window;
  _.style.top=(($.document.body.clientHeight-_.offsetHeight)/2+$.document.body.scrollTop)+"px";
  _.style.left=(($.document.body.clientWidth-_.offsetWidth)/2+$.document.body.scrollLeft)+"px";
};
function initShadow(E,_){
  if(isFF){
    _=_||document;
    var D=E.nextSibling;
    if(D==null||!D.isShadow){
      var $=E.parentNode,A=E.offsetWidth,B=E.offsetHeight,C=$createElement("div",{doc:_});
      C.isShadow=true;
      $.style.width=A;
      $.style.height=B;
      C.style.width=A+5;
      C.style.height=B+5;
      C.style.position="absolute";
      C.style.overflow="hidden";
      C.style.left=0;
      C.style.top=0;
      C.style.zIndex=-999;
      $.insertBefore(C,E.nextSibling);
      E.shadowContainter=C;
      C.innerHTML="<TABLE style=\"width: 100%;height:100%\" cellspacing=\"0\" cellpadding=\"0\"><TR height=\"5px\"><TD width=\"5px\"></TD><TD></TD><TD width=\"5px\" class=\"eos-shadow-right-top\"></TD></TR><TR><TD></TD><TD ><div ></div></TD><TD width=\"5px\"  class=\"eos-shadow-right\"></TD></TR><TR height=\"5px\"><TD width=\"5px\" class=\"eos-shadow-left-bottom\"></TD><TD class=\"eos-shadow-bottom\"></TD><TD width=\"5px\" class=\"eos-shadow-corner\"></TD></TR></TABLE>";
      var F=C.getElementsByTagName("div")[0];
      F.style.width=A-5;
      F.style.height=B-5;
      C.center=F;
      $.style.width=A+5;
      $.style.height=B+5;
    }else {
      $=E.parentNode,A=E.offsetWidth,B=E.offsetHeight,C=E.shadowContainter;
      C.center.style.width=A-5;
      C.center.style.height=B-5;
      C.style.width=A+5;
      C.style.height=B+5;
      C.style.left=0;
      C.style.top=0;
      $.style.width=A+5;
      $.style.height=B+5;
    }
  }else {
    _=_||document;
    D=E.nextSibling;
    if(D==null||!D.isShadow){
      $=E.parentNode,A=E.offsetWidth,B=E.offsetHeight,C=$createElement("div",{doc:_});
      C.isShadow=true;
      $.style.width=A;
      $.style.height=B;
      C.style.width=A-4;
      C.style.height=B-4;
      C.style.position="absolute";
      C.style.left=0;
      C.style.top=0;
      C.style.zIndex=-999;
      C.style.background="#777";
      C.style.filter="progid:DXImageTransform.Microsoft.alpha(opacity=50) progid:DXImageTransform.Microsoft.Blur(pixelradius=4)";
      $.insertBefore(C,E.nextSibling);
      E.shadowContainter=C;
      if(E.style.width==""){
        E.style.width=A;
      }
    }else {
      $=E.parentNode,A=E.offsetWidth,B=E.offsetHeight,C=E.shadowContainter;
      C.isShadow=true;
      $.style.width=A;
      $.style.height=B;
      C.style.width=A-4;
      C.style.height=B-4;
    }
  }
}
function buttonMouseOver($){
  addClass($,"eos-button-over");
  addClass($.firstChild,"eos-button-inner-over");
}
function buttonMouseOut($){
  removeClass($,"eos-button-over");
  removeClass($.firstChild,"eos-button-inner-over");
}
function buttonMouseUp($){
  if(isIE){
    removeClass($,"eos-button-down");
    removeClass($.firstChild,"eos-button-inner-down");
  }
}
function buttonMouseDown($){
  if(isIE){
    addClass($,"eos-button-down");
    addClass($.firstChild,"eos-button-inner-down");
  }
}
function buttonForFF($){
  addClass($,"eos-button-ff");
  addClass($.firstChild,"eos-button-inner-ff");
}
function setEosControlStyleforIE(){
  if(isIE){
    var A=document["styleSheets"],$=A.length;
    for(var B=0;B<$;B++){
      var D=A[B].rules,C=D.length;
      for(var _=0;_<C;_++){
        if(D[_].selectorText==".eos-ic-button"){
          D[_].style.verticalAlign="text-bottom";
        }
      }
    }
  }
}
eventManager.add(window,"load",setEosControlStyleforIE);
function flowSubmit($){
  var A=$.parentNode;
  while(A!=null){
    if(A.tagName&&A.tagName.toLowerCase()=="form"){
      break ;
    }else {
      A=A.parentNode;
    }
  }
  if(A==null){
    alert("can not find form tag!");
    return ;
  }
  var _=$name("_eosFlowAction",A);
  if(_==null){
    _=$createElement("input",{type:"hidden",name:"_eosFlowAction"});
    A.appendChild(_);
  }
  _.value=$.getAttribute("flowAction");
  A.submit();
}
function getCurrentStyle($,_){
  if($.currentStyle){
    return $.currentStyle[_];
  }else {
    if(window.getComputedStyle){
      propprop=_.replace(/([A-Z])/g,"-$1");
      propprop=_.toLowerCase();
      return document.defaultView.getComputedStyle($,null)[_];
    }
  }
}
var Drag={obj:null,init:function(C,F,H,D,$,G,B,A,_,E){
    C.onmousedown=Drag.start;
    C.hmode=B?false:true;
    C.vmode=A?false:true;
    C.root=F&&F!=null?F:C;
    if(C.hmode&&isNaN(parseInt(C.root.style.left))){
      C.root.style.left="0px";
    }
    if(C.vmode&&isNaN(parseInt(C.root.style.top))){
      C.root.style.top="0px";
    }
    if(!C.hmode&&isNaN(parseInt(C.root.style.right))){
      C.root.style.right="0px";
    }
    if(!C.vmode&&isNaN(parseInt(C.root.style.bottom))){
      C.root.style.bottom="0px";
    }
    C.minX=typeof H!="undefined"?H:null;
    C.minY=typeof $!="undefined"?$:null;
    C.maxX=typeof D!="undefined"?D:null;
    C.maxY=typeof G!="undefined"?G:null;
    C.xMapper=_?_:null;
    C.yMapper=E?E:null;
    C.root.onDragStart=new Function();
    C.root.onDragEnd=new Function();
    C.root.onDrag=new Function();
  },start:function($){
    var _=Drag.obj=this;
    $=Drag.fixE($);
    var A=parseInt(_.vmode?_.root.style.top:_.root.style.bottom),B=parseInt(_.hmode?_.root.style.left:_.root.style.right);
    _.root.onDragStart(B,A);
    _.lastMouseX=$.clientX;
    _.lastMouseY=$.clientY;
    if(_.hmode){
      if(_.minX!=null){
        _.minMouseX=$.clientX-B+_.minX;
      }
      if(_.maxX!=null){
        _.maxMouseX=_.minMouseX+_.maxX-_.minX;
      }
    }else {
      if(_.minX!=null){
        _.maxMouseX=-_.minX+$.clientX+B;
      }
      if(_.maxX!=null){
        _.minMouseX=-_.maxX+$.clientX+B;
      }
    }
    if(_.vmode){
      if(_.minY!=null){
        _.minMouseY=$.clientY-A+_.minY;
      }
      if(_.maxY!=null){
        _.maxMouseY=_.minMouseY+_.maxY-_.minY;
      }
    }else {
      if(_.minY!=null){
        _.maxMouseY=-_.minY+$.clientY+A;
      }
      if(_.maxY!=null){
        _.minMouseY=-_.maxY+$.clientY+A;
      }
    }
    document.onmousemove=Drag.drag;
    document.onmouseup=Drag.end;
    return false;
  },drag:function(F){
    F=Drag.fixE(F);
    var C=Drag.obj,D=F.clientY,A=F.clientX,$=parseInt(C.vmode?C.root.style.top:C.root.style.bottom),B=parseInt(C.hmode?C.root.style.left:C.root.style.right),_,E;
    if(C.minX!=null){
      A=C.hmode?Math.max(A,C.minMouseX):Math.min(A,C.maxMouseX);
    }
    if(C.maxX!=null){
      A=C.hmode?Math.min(A,C.maxMouseX):Math.max(A,C.minMouseX);
    }
    if(C.minY!=null){
      D=C.vmode?Math.max(D,C.minMouseY):Math.min(D,C.maxMouseY);
    }
    if(C.maxY!=null){
      D=C.vmode?Math.min(D,C.maxMouseY):Math.max(D,C.minMouseY);
    }
    _=B+((A-C.lastMouseX)*(C.hmode?1:-1));
    E=$+((D-C.lastMouseY)*(C.vmode?1:-1));
    if(C.xMapper){
      _=C.xMapper($);
    }else {
      if(C.yMapper){
        E=C.yMapper(B);
      }
    }
    Drag.obj.root.style[C.hmode?"left":"right"]=_+"px";
    Drag.obj.root.style[C.vmode?"top":"bottom"]=E+"px";
    Drag.obj.lastMouseX=A;
    Drag.obj.lastMouseY=D;
    Drag.obj.root.onDrag(_,E);
    return false;
  },end:function(){
    document.onmousemove=null;
    document.onmouseup=null;
    Drag.obj.root.onDragEnd(parseInt(Drag.obj.root.style[Drag.obj.hmode?"left":"right"]),parseInt(Drag.obj.root.style[Drag.obj.vmode?"top":"bottom"]));
    Drag.obj=null;
  },fixE:function($){
    if(typeof $=="undefined"){
      $=window.event;
    }
    if(typeof $.layerX=="undefined"){
      $.layerX=$.offsetX;
    }
    if(typeof $.layerY=="undefined"){
      $.layerY=$.offsetY;
    }
    return $;
  }};
function $create(A,$){
  $=$||document;
  if(A.indexOf("<")>-1){
    var _=$.createElement("div");
    _.innerHTML=A;
    return firstElement(_);
  }
  return $.createElement(A);
}
function $createElement(A,_){
  var D=_?(_.doc||document):document;
  if(A.indexOf("<")>-1){
    var E=D.createElement("div");
    E.innerHTML=A;
    A=E.firstChild;
  }else {
    if(isIE&&_&&(_.name||_.type)){
      var B=(_.name)?" name=\""+_.name+"\"":"",$=(_.type)?" type=\""+_.type+"\"":"";
//      A="<"+A+B+$+">";
//      A=D.createElement(A);

		var eName = _.name;
		var eType = _.type;
		A.name= _.name;
		A.type= _.type;

        delete _.name;
        delete _.type;
        
		A = D.createElement(A);
		
		if(eName){
			A.name= eName;
		}
		if(eType){
			A.type= eType;
		}
    }else {
      A=D.createElement(A);
    }
  }
  if(_){
    if(_.style){
      for(var C in _.style){
        A.style[C]=_.style[C];
      }
      delete _.style;
    }
    for(C in _){
      A[C]=_[C];
    }
    if(_.value){
      A.value=_.value;
    }
  }
  return A;
}
function createSelect($,D,_){
  var A=$createElement("select",_||{});
  for(var C in $){
    var B=$createElement("option",{"value":C,"text":""+$[C],innerHTML:$[C]});
    if((D||D===0)&&C==D){
      B.selected=true;
    }
    A.appendChild(B);
  }
  return A;
}
function getViewportHeight(){
  if(window.innerHeight!=window.undefined){
    return window.innerHeight;
  }
  if(document.compatMode=="CSS1Compat"){
    return document.documentElement.clientHeight;
  }
  if(document.body){
    return document.body.clientHeight;
  }
  return window.undefined;
}
function getViewportWidth(){
  if(window.innerWidth!=window.undefined){
    return window.innerWidth;
  }
  if(document.compatMode=="CSS1Compat"){
    return document.documentElement.clientWidth;
  }
  if(document.body){
    return document.body.clientWidth;
  }
  return window.undefined;
}
function bodyAddNode(_,$){
  if(!_){
    return ;
  }
  $=$||document;
  var A=$.body.firstChild;
  if(A){
    $.body.insertBefore(_,A);
  }else {
    $.body.appendChild(_);
  }
}
function addClass(A,$){
  if(!A){
    return ;
  }
  var B=A.className.split(" ");
  for(var _=0;_<B.length;_++){
    if(B[_]==$){
      return ;
    }
  }
  B.push($);
  A.className=B.join(" ");
}
function removeClass(A,$){
  if(!A){
    return ;
  }
  var B=A.className.split(" ");
  for(var _=0;_<B.length;_++){
    if(B[_]==$){
      B.splice(_,1);
    }
  }
  A.className=B.join(" ");
}
function getPosition(_){
  var A=_.offsetTop,B=_.offsetLeft,$=_;
  while($=$.offsetParent){
    if($.tagName=="BODY"){
      continue ;
    }
    A+=$.offsetTop-$.scrollTop;
    B+=$.offsetLeft-$.scrollLeft;
  }
  return {"top":A,"left":B};
}
function getAbsPos(E){
  var D=E.offsetTop,_=E.offsetLeft,A=E;
  while(A=A.offsetParent){
    if(A&&A.tagName=="BODY"){
      D-=A.scrollTop;
      _-=A.scrollLeft;
      continue ;
    }
    D+=A.offsetTop-A.scrollTop;
    _+=A.offsetLeft-A.scrollLeft;
  }
  var $=_get_top_window();
  if($!=window){
    if((isIE||isIE9||isIE10)){
      var F=window;
      do {
        var C=F.frameElement;
        if(C){
          var B=getPosition(C);
          D=D+B.top-(F.scrollTop||0);
          _=_+B.left-(F.scrollLeft||0);
        }
        F=F.parent;
      }while(F&&F!=$);
      _+=$.scrollLeft||0;
      D+=$.scrollTop||0;
      if(isBorderBox){
        _=_+2;
        D=D+2;
      }
    }else {
      D=window.screen.top-$.screen.top+D;
      _=window.screen.left-$.screen.left+_;
    }
  }
  return {"top":D,"left":_};
}
function getScreenPos(A,E){
  var _=0,C=0;
  if(A==E){
    return {left:0,top:0};
  }else {
    var F=A;
    while(F&&F!=E){
      var D=F.frameElement,B=F.parent;
      if(D){
        var $=getPosition(D);
        _=_+$.left;
        C=C+$.top;
      }
      F=F.parent;
    }
  }
  return {left:_,top:C};
}
function createStyle(F,D,_){
  var A="display:",$;
  D=D||document;
  if(D.styleSheets.length==0){
    var E=D.createElement("style");
    D.body.appendChild(E);
  }
  var B=D.styleSheets[0];
  if(!_){
    F="."+F;
  }
  if(B.insertRule){
    var C=B.insertRule(F+"{"+A+"}",B.length);
    return B.cssRules[C];
  }else {
    C=B.addRule(F,A,-1);
    if(C==-1){
      C=B.rules.length-1;
    }
    return B.rules[C];
  }
}
function getScroll(C){
  var A=C,B=document;
  if(A==B||A==B.body){
    var _=window.pageXOffset||B.documentElement.scrollLeft||B.body.scrollLeft||0,$=window.pageYOffset||B.documentElement.scrollTop||B.body.scrollTop||0;
    return [_,$];
  }else {
    return [A.scrollLeft,A.scrollTop];
  }
}
function translatePoints(D,B,$){
  if(typeof B=="object"||B instanceof Array){
    $=B[1];
    B=B[0];
  }
  var _=D.style.position,C=getElementXY(D),A=parseInt(D.style.left,10),E=parseInt(D.style.top,10);
  if(isNaN(A)){
    A=(_=="relative")?0:D.offsetLeft;
  }
  if(isNaN(E)){
    E=(_=="relative")?0:D.offsetTop;
  }
  return {left:(B-C[0]+A),top:($-C[1]+E)};
}
function getElementXY(I,H){
  var D,A,$,L,_=document.body;
  if(I&&I.style.display=="none"){
    return [0,0];
  }
  if(I.getBoundingClientRect){
    try{
      $=I.getBoundingClientRect();
      L=getScroll(document);
      return [$.left+L[0],$.top+L[1]];
    }
    catch(G){
    }
  }
  var F=0,K=0;
  D=I;
  H=H||_;
  var B=I.style.position=="absolute";
  while(D){
    F+=D.offsetLeft;
    K+=D.offsetTop;
    if(!B&&D.style.position=="absolute"){
      B=true;
    }
    if(isGecko){
      A=D;
      var J=parseInt(A.style.borderTopWidth,10)||0,E=parseInt(A.style.borderLeftWidth,10)||0;
      F+=E;
      K+=J;
      if(D!=I&&A.style.overflow!="visible"){
        F+=E;
        K+=J;
      }
    }
    D=D.offsetParent;
  }
  if(isSafari&&B){
    F-=_.offsetLeft;
    K-=_.offsetTop;
  }
  if(isGecko&&!B){
    var C=_;
    F+=parseInt(C.style.borderTopWidth,10)||0;
    K+=parseInt(C.style.borderTopWidth,10)||0;
  }
  D=I.parentNode;
  while(D&&D!=_){
    if(!isOpera||(D.tagName.toUpperCase()!="TR"&&D.style.display!="inline")){
      F-=D.scrollLeft||0;
      K-=D.scrollTop||0;
    }
    D=D.parentNode;
  }
  return [F,K];
}
function setElementXY(B,A,_){
  if(B.style.position=="static"){
    B.style.position="relative";
  }
  if(_!==true){
    B.style.left="0px";
    B.style.top="0px";
  }
  var $=translatePoints(B,A);
  if(A[0]!==false){
    B.style.left=$.left+"px";
  }
  if(A[1]!==false){
    B.style.top=$.top+"px";
  }
}
function firstElement(B,_){
  if(B){
    _=_||1;
    var A=0,$=B.firstChild;
    while($){
      if($.nodeType==1){
        if(++A==_){
          return $;
        }
      }
      $=$.nextSibling;
    }
  }
  return null;
}
function prevElement(_,$){
  if(_){
    var A=_.previousSibling;
    while(A){
      if(A.nodeType==1){
        return A;
      }
      A=A.previousSibling;
    }
  }
  return null;
}
function nextElement(_,$){
  if(_){
    var A=_.nextSibling;
    while(A){
      if(A.nodeType==1){
        return A;
      }
      A=A.nextSibling;
    }
  }
  return null;
}
function getNodeValue(A){
  var _=null;
  if(!A){
    return null;
  }
  var $=A.getAttribute("__isNullOrEmpty");
  if($=="null"){
    return null;
  }
  if($=="empty"){
    return "";
  }
  if(A.text){
    _=A.text;
  }else {
    if(A.childNodes.length>1){
      _=A.xml.replace(/<\/?[^>]+>/gi,"");
    }else {
      if(A.firstChild){
        _=A.firstChild.data;
      }
    }
  }
  return _;
}
function removeElement(A){
  if(!A){
    return ;
  }
  var _=["onclick","ondblclick","onmousedown","onmouseup","onmouseover","onmouseout","onchange","onfocus","onblur","onkeypress","onkeydown","onkeyup"];
  for(var $=0;$<_.length;$++){
    A.setAttribute(_[$],null);
    A[_[$]]=null;
  }
  if(A.parentNode){
    A.parentNode.removeChild(A);
  }
}
function getFormElementsValue(C){
  C=$e(C);
  var $={};
  for(var B=0;B<C.elements.length;B++){
    var A=C.elements[B];
    if(!A.disabled&&A.name){
      var _=getElementValue(A);
      if($[A.name]!==undefined){
        $[A.name]=[].concat($[A.name]);
        $[A.name].push(_);
      }else {
        $[A.name]=_;
      }
    }
  }
  return $;
}
function getElementValue(B){
  B=$e(B);
  var _=B.tagName.toLowerCase();
  function C($){
    return $.checked?$.value:null;
  }
  function D($){
    return $.getAttribute("value")!=null?$.value:$.text;
  }
  var $={input:function($){
      switch($.type.toLowerCase()){
      case "checkbox":
      case "radio":
        return C($);
      default:
        return $.value;
      }
    },textarea:function($){
      return $.value;
    },select:function(E){
      switch(E.type.toLowerCase()){
      case "select-one":
        var C=E.selectedIndex;
        return C>=0?D(E.options[C]):null;
      default:
        var A,_=E.length;
        if(!_){
          return null;
        }
        for(var $=0,A=[];$<_;$++){
          var B=E.options[$];
          if(B.selected){
            A.push(D(B));
          }
        }
        return A;
      }
      return this[E.type=="select-one"?"selectOne":"selectMany"](E);
    }},A=$[_];
  if(A==null){
    return null;
  }
  return A(B);
}
var CSSUtil=function($){
  var C=null,B=$||document,_=/(-[a-z])/gi,A=function($,_){
    return _.charAt(1).toUpperCase();
  };
  return {createStyleSheet:function(D,F,A){
      var C;
      A=A||B;
      var _=A.getElementsByTagName("head");
      if(!_||_.length<1){
        A.documentElement.insertBefore(A.createElement("head"),A.body);
        _=A.getElementsByTagName("head");
      }
      var $=_[0],E=A.createElement("style");
      E.setAttribute("type","text/css");
      if(F){
        E.setAttribute("id",F);
      }
      if(isIE){
        $.appendChild(E);
        C=E.styleSheet;
        try{
          C.cssText=D;
        }
        catch(G){
        }
      }else {
        try{
          E.appendChild(A.createTextNode(D));
        }
        catch(G){
          E.cssText=D;
        }
        $.appendChild(E);
        C=E.styleSheet?E.styleSheet:(E.sheet||A.styleSheets[A.styleSheets.length-1]);
      }
      this.cacheStyleSheet(C);
      return C;
    },removeStyleSheet:function($,A){
      A=A||B;
      var _=A.getElementById($);
      if(_){
        _.parentNode.removeChild(_);
      }
    },swapStyleSheet:function(_,A,C){
      C=C||B;
      this.removeStyleSheet(_);
      var $=C.createElement("link");
      $.setAttribute("rel","stylesheet");
      $.setAttribute("type","text/css");
      $.setAttribute("id",_);
      $.setAttribute("href",A);
      C.getElementsByTagName("head")[0].appendChild($);
    },refreshCache:function(){
      return this.getRules(true);
    },cacheStyleSheet:function($){
      if(!C){
        C={};
      }
      try{
        var A=$.cssRules||$.rules;
        for(var B=A.length-1;B>=0;--B){
          C[A[B].selectorText]=A[B];
        }
      }
      catch(_){
      }
    },getRules:function(A,D){
      D=D||B;
      if(C==null||A){
        C={};
        var _=D.styleSheets;
        for(var $=0,E=_.length;$<E;$++){
          try{
            this.cacheStyleSheet(_[$]);
          }
          catch(F){
          }
        }
      }
      return C;
    },getRule:function(B,A){
      var $=this.getRules(A);
      if(!(B instanceof Array)){
        return $[B];
      }
      for(var _=0;_<B.length;_++){
        if($[B[_]]){
          return $[B[_]];
        }
      }
      return null;
    },updateRule:function(B,C,E){
      if(!(B instanceof Array)){
        var $=this.getRule(B);
        if($){
          $.style[C.replace(_,A)]=E;
          return true;
        }
      }else {
        for(var D=0;D<B.length;D++){
          if(this.updateRule(B[D],C,E)){
            return true;
          }
        }
      }
      return false;
    },getCssText:function(_){
      var $=CSSUtil.getRule(_);
      return $?_+" { "+CSSUtil.getRule(_).style.cssText+" } ":"";
    }};
}(),EOSLOG_LEVEL=EOSLOG_LEVEL||"debug",EOSDEBUG=EOSDEBUG==undefined?false:EOSDEBUG;
function escapeHTML($){
  if(typeof ($)=="string"){
    return $.replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/\t/g,"&nbsp;&nbsp;").replace(/\n/g,"<br>");
  }else {
    return $;
  }
}
if(typeof (EOSLog)!="function"){
  EOSLog=function(){
  };
  EOSLog.styles=new Array();
  EOSLog.messages=[];
  EOSLog.ID="EOS_LOG_CONSOLE";
  EOSLog.BODY_ID="EOS_LOG_CONSOLE_BODY";
  EOSLog.BUTTONS_ID="EOS_LOG_CONSOLE_BUTTONS";
  EOSLog.EVAL_ID="EOS_LOG_EVAL_ID";
  EOSLog.EVAL_INPUT_ID="EOS_LOG_EVAL_INPUT_ID";
  EOSLog.EVAL_RUN_ID="EOS_LOG_EVAL_RUN_ID";
  EOSLog.EVAL_CLEAR_ID="EOS_LOG_EVAL_CLEAR_ID";
  EOSLog.EVAL_SIGN_ID="EVAL_SIGN_ID";
  EOSLog.EVAL_SIGN_SELECT_ID="EVAL_SIGN_SELECT_ID";
  EOSLog.index=0;
  EOSLog.iDiffX=0;
  EOSLog.iDiffY=0;
  EOSLog.hasOut=false;
  EOSLog.timeLog=new Array();
  EOSLog.panel=null;
  EOSLog.topWin=window;
  EOSLog.doc=document;
}
EOSLog.log=function($,A){
  var _=new Date();
  EOSLog.messages.push({level:$,time:_,message:A,out:false});
  if(EOSLog.panel){
    EOSLog.refresh();
  }
};
EOSLog.refresh=function(){
  if(EOSLog.panel){
    var $;
    for($=EOSLog.index;$<EOSLog.messages.length;$++){
      EOSLog.print(EOSLog.messages[$]);
    }
    EOSLog.index=$==0?0:$-1;
  }
};
EOSLog.info=function($){
  EOSLog.log("info",escapeHTML($));
};
EOSLog.error=function($){
  EOSLog.log("error",escapeHTML($));
};
EOSLog.debug=function($){
  EOSLog.log("debug",escapeHTML($));
};
EOSLog.warn=function($){
  EOSLog.log("warn",escapeHTML($));
};
EOSLog.handle=function(_){
  var $=new StringBuffer();
  $.append(EOSLOG_ERROR_TYPE).append(_.name).append(".");
  $.append(EOSLOG_ERROR_INFO).append(_.message).append(".");
  if(_.number){
    $.append(EOSLOG_ERROR_CODE).append(_.number).append(".>");
  }
  if(_.fileName){
    $.append(EOSLOG_ERROR_URL).append(_.fileName).append(".");
  }
  if(_.lineNumber){
    $.append(EOSLOG_ERROR_LINE).append(_.lineNumber).append(".");
  }
  if(_.stack){
    $.append(EOSLOG_ERROR_STACK).append(_.stack.replace(/ /g,"&nbsp;").replace(/\n/g,"<br>").replace(/\t/g,"&nbsp;&nbsp;&nbsp;&nbsp;")).append("<br>");
  }
  if(_.description){
    $.append(EOSLOG_ERROR_DETAIL).append(_.description).append("<br>");
  }
  if(EOSLog.handle.caller&&EOSLog.handle.caller.caller){
    $.append(EOSLOG_ERROR_FUNCTION).append("<br>").append(EOSLog.handle.caller.caller.toString().replace(/ /g,"&nbsp;").replace(/\n/g,"<br>").replace(/\t/g,"&nbsp;&nbsp;&nbsp;&nbsp;")).append("<br>");
  }
  EOSLog.log("error",$.toString());
};
window.onerror=function(_,C,D){
  if(EOSDEBUG){
    var $=new StringBuffer();
    if(isIE){
      try{
        var B=EOSLog.getDetailError(window.onerror.caller,_,C,D);
        $.append(EOSLOG_ERROR_INFO).append(B.sMessage).append("<br> ");
        $.append(EOSLOG_ERROR_URL).append(B.sUrl).append("<br> ");
        $.append(EOSLOG_ERROR_LINE).append(B.sLine).append("<br> ");
        $.append(EOSLOG_ERROR_FUNCTION).append("<br>").append(B.errFunc).append("<br>");
      }
      catch(A){
      }
    }else {
      $.append(EOSLOG_ERROR_INFO).append(_).append("<br> ");
      $.append(EOSLOG_ERROR_URL).append(C).append("<br> ");
      $.append(EOSLOG_ERROR_LINE).append(D).append("<br> ");
    }
    EOSLog.log("error",$.toString());
  }
};
EOSLog.isShow=function(A,$){
  var _=EOSLog.styles[A];
  if($.checked){
    _.style.display="block";
  }else {
    _.style.display="none";
  }
};
EOSLog.clear=function(){
  var $=$id(EOSLog.BODY_ID,EOSLog.doc);
  $.innerHTML="";
};
EOSLog.console=function(){
  var F="";
  if(window.isWebkit){
    F="\"eos-button-gc\"";
  }else {
    F="\"eos-button\"";
  }
  var _=$create("div",EOSLog.doc);
  _.style.position="absolute";
  _.style.left="300px";
  _.style.top="300px";
  var E=new StringBuffer();
  if(isIE){
    E.append("<iframe style=\"position:absolute;z-index:-1;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>");
  }
  var B="",$=navigator.userAgent.toLowerCase().indexOf("msie 6")!=-1;
  if($){
    B="style=\"filter:alpha(opacity=50)\"";
  }
  E.append("<div style=\"z-index:1000\">");
  E.append("<TABLE  cellspacing=\"0\" cellpadding=\"0\"  class=\"eos-dialog\"><TR><TD "+B+" class=\"left-top-conner\"><div class=\"ieBlank\"></div></TD><TD  "+B+" class=\"top\"></TD><TD  "+B+" class=\"right-top-conner\"><div class=\"ieBlank\"></div></TD></TR><TR><TD  "+B+" class=\"left\"><div class=\"ieBlank\"></div></TD><TD style=\"background-color: #eaf0f2;\">");
  E.append("<div style='display:none;position: absolute;height=200px;overflow-y:auto; z-index: 1000;' id='").append(EOSLog.EVAL_SIGN_ID).append("'>");
  E.append("<select class='EOS-LOG-SIGN-DIV' size='10' id='").append(EOSLog.EVAL_SIGN_SELECT_ID).append("'></select></div>");
  E.append("<div onmouseup=\"style.cursor='auto'\" onmousedown=\"style.cursor='move';\" class='EOS-LOG-BUTTONS' style=\"width:100%\"id='");
  E.append(EOSLog.BUTTONS_ID).append("'><table width=\"100%\"align='right' border='0' style='font-size:9pt'><tr><td width=\"300px\">");
  E.append("<input type=\"checkbox\" checked onmousedown=\"EOSLog.isShow('time',this)\" name=\"time\" value=\"1\">TIME");
  E.append("<input type=\"checkbox\" checked onmousedown=\"EOSLog.isShow('info',this)\" name=\"info\" value=\"1\">INFO");
  E.append("<input type=\"checkbox\" checked onmousedown=\"EOSLog.isShow('debug',this)\" name=\"degbug\" value=\"1\">DEBUG");
  E.append("<input type=\"checkbox\" checked onmousedown=\"EOSLog.isShow('warn',this)\" name=\"warn\" value=\"1\">WARN");
  E.append("<input type=\"checkbox\" checked onmousedown=\"EOSLog.isShow('error',this)\" name=\"error\" value=\"1\">ERROR</td><td align='right'>");
  E.append("&nbsp;<div  id=\"eos_log_button0\"  class="+F+" onclick=\"EOSLog.clear()\" onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">Clear</div></div>&nbsp;");
  E.append("<div id=\"eos_log_button1\"  class="+F+" onclick=\"EOSLog.command()\" onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">Cmd</div></div>&nbsp;");
  E.append("<div id=\"eos_log_button2\"  class="+F+" onclick=\"EOSLog.hide()\"onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">Close</div></div>&nbsp;");
  E.append("</td></tr></table></div>");
  E.append("<div status='hide' class='EOS-LOG-EVAL' id='").append(EOSLog.EVAL_ID).append("' style='width:100%;display:none'><div>");
  E.append("<textarea style='width:100%;height:100px;font-size: 12px' id='").append(EOSLog.EVAL_INPUT_ID).append("'></textarea>");
  E.append("</div><div height='20'>");
  E.append("<div  id=\"eos_log_button3\"  class="+F+" onclick=\"EOSLog.runEval()\"onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">Run</div></div>&nbsp;");
  E.append("<div  id=\"eos_log_button4\"  class="+F+" onclick=\"EOSLog.clearEval()\"onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">Clear</div></div>&nbsp;");
  E.append("</div>");
  E.append("</div>");
  E.append("<div style='border-top:#C0C0C0 solid 1px;overflow:scroll' class='EOS-LOG-BODY' id='").append(EOSLog.BODY_ID).append("' style='overflow:auto'></div>");
  E.append("</TD><TD  "+B+" class=\"right\" id=\"eos_log_right\"><div class=\"ieBlank\"></div></TD></TR><TR><TD  "+B+" class=\"left-bottom-conner\"></TD><TD  "+B+" class=\"bottom\" id=\"eos_log_bottom\"></TD><TD  "+B+" class=\"right-bottom-conner\" id=\"eos_log_rightBottom\"></TD></TR></TABLE>");
  E.append("</div>");
  _.innerHTML=E.toString();
  _.id=EOSLog.ID;
  EOSLog.doc.body.appendChild(_);
  var A=$id(EOSLog.BUTTONS_ID,EOSLog.doc);
  _.style.display="none";
  fx_DD(_,{handle:A});
  EOSLog.hide();
  var D=$id(EOSLog.EVAL_INPUT_ID,EOSLog.doc);
  eventManager.add(D,"keyup",EOSLog.evalKeyUp);
  eventManager.add(D,"keydown",EOSLog.evalKeyDown);
  EOSLog.hasOut=true;
  EOSLog.panel=$id(EOSLog.BODY_ID,EOSLog.doc);
  EOSLog.refresh();
  initDrag(_);
  if(isFF){
    for(var C=0;C<5;C++){
      buttonForFF(EOSLog.doc.getElementById("eos_log_button"+C));
    }
  }
};
EOSLog.command=function(){
  var $=$id(EOSLog.EVAL_ID,EOSLog.doc);
  if($.style.display=="none"){
    $.style.display="";
  }else {
    $.style.display="none";
  }
};
EOSLog.runEval=function(){
  var cmd=$id(EOSLog.EVAL_INPUT_ID,EOSLog.doc),value=cmd.value;
  try{
    eval(value);
  }
  catch(err){
    var buffer=new StringBuffer();
    buffer.append(EOSLOG_ERROR_TYPE).append(err.name).append("<br>");
    buffer.append(EOSLOG_ERROR_INFO).append(err.message).append("<br>");
    if(err.number){
      buffer.append(EOSLOG_ERROR_CODE).append(err.number).append("<br>");
    }
    if(err.fileName){
      buffer.append(EOSLOG_ERROR_URL).append(err.fileName).append("<br>");
    }
    if(err.lineNumber){
      buffer.append(EOSLOG_ERROR_LINE).append(err.lineNumber).append("<br>");
    }
    if(err.stack){
      buffer.append(EOSLOG_ERROR_STACK).append(err.stack).append("<br>");
    }
    if(err.description){
      buffer.append(EOSLOG_ERROR_DETAIL).append(err.description).append("<br>");
    }
    buffer.append(EOSLOG_ERROR_FUNCTION).append("<br>").append(value).append("<br>");
    EOSLog.log("error",EOSLOG_EVAL_ERROR+"<br>"+buffer.toString());
  }
};
EOSLog.clearEval=function(){
  var $=$id(EOSLog.EVAL_INPUT_ID,EOSLog.doc);
  $.value="";
};
EOSLog.print=function(_){
  if(_.out){
    return ;
  }
  var B=$create("div",EOSLog.doc);
  B.className="EOS-LOG-"+_.level;
  var A=new StringBuffer();
  A.append("<table border='0'><tr><td valign='top' width='20px'>").append("<div class='EOS-LOG-TITLE-").append(_.level).append("'>");
  A.append(_.level).append("</div></td><td class='EOS-LOG-WORD'>").append("<div class='EOS-LOG-TIME'>runtime:").append(_.time.toLocaleTimeString());
  A.append("</div>").append(_.message).append("</td></tr></table>");
  var $=EOSLog.panel;
  B.innerHTML=A.toString();
  if($.firstChild){
    $.insertBefore(B,$.firstChild);
  }else {
    $.appendChild(B);
  }
  _.out=true;
};
EOSLog.init=function(){
  if(!EOSDEBUG){
    return false;
  }
  if(window["EOSLog_inited"]){
    return ;
  }
  EOSLog.topWin=_get_top_window();
  EOSLog.doc=EOSLog.topWin.document;
  eventManager.add(document,"keyup",EOSLog.keypress);
  if(EOSLog.topWin==window){
    EOSLog.styles["debug"]=createStyle("EOS-LOG-debug",EOSLog.doc);
    EOSLog.styles["info"]=createStyle("EOS-LOG-info",EOSLog.doc);
    EOSLog.styles["error"]=createStyle("EOS-LOG-error",EOSLog.doc);
    EOSLog.styles["time"]=createStyle("EOS-LOG-time",EOSLog.doc);
    EOSLog.styles["warn"]=createStyle("EOS-LOG-warn",EOSLog.doc);
    EOSLog.console();
    EOSLog.hide();
  }else {
    if(!EOSLog.topWin["EOSLog"]){
      moveScript(document,EOSLog.doc);
      moveCss(document,EOSLog.doc);
    }
  }
  window["EOSLog_inited"]=true;
};
EOSLog.keypress=function(){
  var $=eventManager.getKeyCode();
  if($==123){
    EOSLog.show();
    return false;
  }
};
EOSLog.show=function(){
  var $=$id(EOSLog.ID,EOSLog.doc);
  $.style.display="block";
  $.style.zIndex=getMaxZindex(EOSLog.doc);
};
EOSLog.hide=function(){
  var $=$id(EOSLog.ID,EOSLog.doc);
  $.style.display="none";
};
EOSLog.time=function(F,B,_){
  if(!F){
    F="SYSTEM";
  }
  if(!EOSLog.timeLog[F]){
    var $=new Date();
    EOSLog.timeLog[F]={time:$,msg:B};
  }else {
    var A=new Date(),E=EOSLog.timeLog[F],C=A-E.time,D=new StringBuffer();
    D.append("ID:").append(F).append("<br>");
    if(E.msg){
      D.append("start:").append(E.msg).append("<br>");
    }
    D.append(" \u9470\u6941\u6902:<b>").append(C).append("ms</b><br>");
    if(B){
      D.append("end:").append(B);
    }
    EOSLog.log("time",D.toString());
    if(_){
      EOSLog.timeLog[F]=null;
    }
  }
};
function $info(_){
  if("info".indexOf(EOSLOG_LEVEL)>=0){
    var $=EOSLog.topWin["EOSLog"]||EOSLog;
    $.info(_);
  }
}
function $debug(_){
  if("info,debug".indexOf(EOSLOG_LEVEL)>=0){
    var $=EOSLog.topWin["EOSLog"]||EOSLog;
    $.debug(_);
  }
}
function $warn(_){
  if("info,debug,warn".indexOf(EOSLOG_LEVEL)>=0){
    var $=EOSLog.topWin["EOSLog"]||EOSLog;
    $.warn(_);
  }
}
function $error(_){
  if("info,debug,warn,error".indexOf(EOSLOG_LEVEL)>=0){
    var $=EOSLog.topWin["EOSLog"]||EOSLog;
    $.error(_);
  }
}
function $time(_){
  var $=EOSLog.topWin["EOSLog"]||EOSLog;
  $.time(_);
}
function $handle(_){
  var $=EOSLog.topWin["EOSLog"]||EOSLog;
  $.handle(_);
}
EOSLog.enable=function(){
};
EOSLog.evalKeyUp=function(){
  var B=$id(EOSLog.EVAL_INPUT_ID,EOSLog.doc),$=eventManager.getKeyCode(),A=$id(EOSLog.EVAL_SIGN_ID,EOSLog.doc);
  if($==190){
    var _=EOSLog.getkeyWord(B);
    EOSLog.sign(B,_);
  }
};
EOSLog.selectUpOption=function(A){
  var $=A.selectedIndex-1;
  if($>-1){
    var _=A.options[$];
    if(_){
      _.selected=true;
    }
  }else {
    _=A.options[0];
    if(_){
      _.selected=true;
    }
  }
};
EOSLog.selectDownOption=function(A){
  var $=A.selectedIndex+1,_=A.options[$];
  if(_){
    _.selected=true;
  }else {
    _=A.options[0];
    if(_){
      _.selected=true;
    }
  }
};
EOSLog.evalKeyDown=function(){
  var B=$id(EOSLog.EVAL_INPUT_ID,EOSLog.doc),$=eventManager.getKeyCode(),A=$id(EOSLog.EVAL_SIGN_SELECT_ID,EOSLog.doc),_=$id(EOSLog.EVAL_SIGN_ID,EOSLog.doc);
  if($==9){
    eventManager.stopPropagation();
    EOSLog.insertAtCaret(B,"    ");
  }else {
    if(_.style.display!="none"){
      if($==38){
        eventManager.stopPropagation();
        EOSLog.selectUpOption(A);
        return false;
      }else {
        if($==40){
          eventManager.stopPropagation();
          EOSLog.selectDownOption(A);
          return false;
        }else {
          if($==13){
            eventManager.stopPropagation();
            EOSLog.insertAtCaret(B,A.value);
            _.style.display="none";
            return false;
          }else {
            _.style.display="none";
          }
        }
      }
    }
  }
};
EOSLog.sign=function(ob,keyword){
  var div=$id(EOSLog.EVAL_SIGN_ID,EOSLog.doc),props=false;
  try{
    var tempObj;
    if(keyword){
      if(keyword.indexOf(".")>=0){
        var arr=keyword.split(".");
        tempObj=window[arr[0]]||eval(arr[0]);
        for(var i=1;i<arr.length;i++){
          if(tempObj){
            tempObj=tempObj[arr[i]];
          }else {
            tempObj=null;
          }
        }
      }else {
        tempObj=window[keyword]||eval(keyword);
      }
    }
    var select1=$id(EOSLog.EVAL_SIGN_SELECT_ID,EOSLog.doc);
    select1.length=0;
    var values=[];
    for(var a in tempObj){
      if(!(a+"").indexOf("__")==0){
        values.push(a);
        props=true;
      }
    }
    values.sort();
    for(i=0;i<values.length;i++){
      var option,a=values[i];
      option=new Option(a,a);
      select1.options.add(option);
    }
    if(select1.length<10){
      select1.size=select1.length;
    }else {
      select1.size=10;
    }
    if(props){
      div.style.display="block";
      function select1Change(){
        EOSLog.insertAtCaret(ob,select1.value);
        select1.length=0;
        div.style.display="none";
      }
      eventManager.add(select1,"change",select1Change);
    }else {
      div.style.display="none";
    }
  }
  catch(e){
    div.style.display="none";
  }
};
EOSLog.getkeyWord=function(E){
  var H=$id(EOSLog.EVAL_SIGN_ID,EOSLog.doc);
  if(EOSLog.doc.selection){
    var $=EOSLog.doc.selection.createRange(),_=EOSLog.doc.body.createTextRange();
    _.moveToElementText(E);
    for(end=0;_.compareEndPoints("StartToEnd",$)<0;end++){
      _.moveStart("character",1);
      for(var B=0;B<=end;B++){
        if(E.value.charAt(B)=="\n"){
          end++;
        }
      }
    }
    var G=EOSLog.getSingWordByLeft(E,end);
    if(H.parentNode.tagName.toLocaleUpperCase()!="BODY"){
      EOSLog.doc.body.appendChild(H);
    }
    H.style.top=$["offsetTop"]+16+EOSLog.doc.body.scrollTop;
    H.style.left=$["offsetLeft"]+EOSLog.doc.body.scrollLeft;
    return G;
  }else {
    var F=E;
    if(typeof (F.selectionStart)=="number"){
      start=F.selectionStart;
      end=F.selectionEnd;
      if(start==end){
        var J=F.value.substring(0,end),K=EOSLog.getSingWordByLeft(F,end),C=J.split("\n"),D=C[C.length-1],A=D.length*6-F.scrollLeft,I=C.length*16-F.scrollTop;
        if(A>F.offsetWidth){
          while(A>0&&A>F.offsetWidth){
            A=A-F.offsetWidth;
            I=I+16;
          }
        }
        H.style.top=E.offsetTop+I;
        H.style.left=E.offsetLeft+A;
        return K;
      }
    }
  }
  return null;
};
EOSLog.getSingWordByLeft=function($,B){
  var _=$.value.substring(0,end),A=/([\w|\.]+)\.$/;
  A.test(_);
  return RegExp.$1;
};
EOSLog.insertAtCaret=function(B,C){
  if(isIE){
    B.focus();
    EOSLog.doc.selection.createRange().text+=C;
  }else {
    if(B.setSelectionRange){
      var A=B.selectionStart,E=B.selectionEnd,D=B.value.substring(0,A),_=B.value.substring(E);
      B.value=D+C+_;
      var $=A+C.length;
      B.focus();
      B.setSelectionRange($,$);
    }else {
      alert("This   version   of   Mozilla   based   browser   does   not   support   setSelectionRange");
    }
  }
};
if(!window["EOSLog_inited"]){
  try{
    EOSLog.init();
  }
  catch(e){
    if(eventManager){
      eventManager.add(window,"load",EOSLog.init);
    }
  }
}
EOSLog.scriptObject={};
EOSLog.getScriptStr=function($){
  if(!EOSLog.scriptObject[$]){
    EOSLog.scriptObject[$]=getRemoteResource($);
  }
  return EOSLog.scriptObject[$];
};
function EOSDebugInfo(){
  this.sLine=null;
  this.sUrl=null;
  this.sMessage=null;
  this.errFunc=null;
  this.errLine=null;
  this.stackFunc=[];
}
EOSLog.getDetailError=function(A,I,B,H){
  var J=new EOSDebugInfo();
  J.sLine=H;
  J.sUrl=B;
  J.sMessage=I;
  J.errFunc=A;
  var C=null;
  if(A){
    var F=document.documentElement.outerHTML;
    if(F.indexOf(A)>0){
      C=getRemoteResource(window.location);
    }else {
      J.sLine=H-1;
      var D=document.getElementsByTagName("script");
      for(var G=0;G<D.length;G++){
        var E=D[G];
        if(E.src){
          var _=EOSLog.getScriptStr(E.src);
          if(_&&_.indexOf(A.toString())>=0){
            J.sUrl=E.src;
            C=_;
            break ;
          }
        }
      }
    }
    if(C){
      var $=C.split("\n");
      J.errLine=$[J.sLine-1];
      J.errFunc=getDisplayStr(J.errFunc);
      J.errLine=getDisplayStr(J.errLine);
      if(J.errLine&&J.errFunc){
        if(J.errFunc.indexOf(J.errLine)>=0){
          J.errFunc=J.errFunc.replace(J.errLine,"<b><span style='color:red;background-color:#eeeeee;font-size:larger'>"+J.errLine+"</span></b>");
        }
      }
    }
  }
  return J;
};
function getDisplayStr($){
  if($){
    $=$.toString();
    return $.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/ /g,"&nbsp;").replace(/\n/g,"<br>").replace(/\t/g,"&nbsp;&nbsp;&nbsp;&nbsp;");
  }
  return $;
}
function getRemoteResource($){
  var _=createXMLHttp();
  _.open("GET",$,false);
  _.send(null);
  return _.responseText;
}
function initDrag(C){
  var B=EOSLog.topWin.fx_DD||fx_DD,$=$id("eos_log_right",EOSLog.doc),_=$id("eos_log_bottom",EOSLog.doc),A=$id("eos_log_rightBottom",EOSLog.doc);
  B(C,{handle:_,modifiers:true,onDrag:function(){
      var _=this.mouse.start["y"],$=this.mouse.now["y"];
      if($-_+this.value.now["y"]>=27){
        this.element.style.height=$-_+this.value.now["y"];
      }
      resizeLogPanel(parseInt(C.style.height));
    },onComplete:function(){
    },onStart:function(){
      this.value.now["y"]=this.element.getStyle("height").toInt();
    }});
  B(C,{handle:$,modifiers:true,onDrag:function(){
      var _=this.mouse.start["x"],$=this.mouse.now["x"];
      if($-_+this.value.now["x"]>=150){
        this.element.style.width=$-_+this.value.now["x"];
      }
      resizeLogPanel(null,parseInt(C.style.width));
    },onComplete:function(){
    },onStart:function(){
      this.value.now["x"]=this.element.getStyle("width").toInt();
    }});
  B(C,{handle:A,modifiers:true,onStart:function(){
      this.value.now["x"]=this.element.getStyle("width").toInt();
      this.value.now["y"]=this.element.getStyle("height").toInt();
    },onDrag:function(){
      var _=this.mouse.start["y"],A=this.mouse.now["y"];
      if(A-_+this.value.now["y"]>=27){
        C.style.height=A-_+this.value.now["y"];
      }
      var $=this.mouse.start["x"],B=this.mouse.now["x"];
      if(B-$+this.value.now["x"]>=150){
        C.style.width=B-$+this.value.now["x"];
      }
      resizeLogPanel(parseInt(C.style.height),parseInt(C.style.width));
    },onComplete:function(){
    }});
}
function resizeLogPanel(_,$){
  if(_!=null&&_!=""){
    EOSLog.panel.style.height=_-27;
  }
  if($!=null&&$!=""){
    EOSLog.panel.style.width=$;
  }
}
EOSLog.formatXML=function(F){
  return F;
  try{
    var $=F.split(">"),B="",A=0;
    for(var C=0;C<$.length-1;C++){
      var D=$[C];
      if(D.indexOf("<")!=0){
        var E=D.split("<");
        for(var _=0;_<A;_++){
          B=B+"\t";
        }
        B=B+E[0]+"\n";
        A=A-1;
        for(_=0;_<A;_++){
          B=B+"\t";
        }
        B=B+"<"+E[1]+">\n";
      }else {
        if(D.indexOf("</")!=0){
          for(_=0;_<A;_++){
            B=B+"\t";
          }
          B=B+$[C]+">\n";
          if(D.indexOf("<!")==-1&&D.indexOf("<?")==-1){
            A=A+1;
          }
        }else {
          A=A-1;
          for(_=0;_<A;_++){
            B=B+"\t";
          }
          B=B+$[C]+">\n";
        }
      }
    }
    return B;
  }
  catch(G){
    return F;
  }
};
var EOSResizeObjects=[],EOSResizeContainer=[];
function getTopContainer($){
  var _=null;
  while(true){
    $=$.offsetParent;
    if($==null){
      if(_!=null){
        if(_.getAttribute("height")!=null||_.style.height!=""){
          return _;
        }
      }
      break ;
    }
    if($.tagName.toLowerCase()=="table"){
      _=$;
    }
  }
  return null;
}
function registerTopContainer(_){
  var A=getTopContainer(_);
  if(A==null){
    return false;
  }
  for(var $=0;$<EOSResizeContainer.length;$++){
    if(EOSResizeContainer[$]==A){
      return true;
    }
  }
  EOSResizeContainer.push(A);
  return true;
}
function eos_layout_doHResize(){
  if(isFF){
    for(var $=0;$<EOSResizeObjects.length;$++){
      if(_eos_curr_open_panel!=null){
        if(!EOSResizeObjects[$].isInCurrPanel()){
          EOSResizeObjects[$].doAutoResizeS2=false;
          continue ;
        }
      }
      EOSResizeObjects[$].autoResizeS1();
    }
    for($=0;$<EOSResizeContainer.length;$++){
      EOSResizeContainer[$].style.height=EOSResizeContainer[$].offsetHeight-1;
    }
    setTimeout(_layout_auto_resize,1);
  }
}
function _layout_auto_resize(){
  for(var $=0;$<EOSResizeContainer.length;$++){
    EOSResizeContainer[$].style.height=EOSResizeContainer[$].getAttribute("height");
  }
  for($=0;$<EOSResizeObjects.length;$++){
    if(EOSResizeObjects[$].doAutoResizeS2===false){
      EOSResizeObjects[$].doAutoResizeS2=true;
      continue ;
    }
    EOSResizeObjects[$].autoResizeS2();
  }
}
function _layout_setResised(){
  for(var $=0;$<_eos_colsed_panel.length;$++){
    _eos_colsed_panel[$].needResize=true;
  }
}
eventManager.add(window,"resize",_layout_setResised);
if(isFF){
  eventManager.add(window,"load",eos_layout_doHResize);
  eventManager.add(window,"resize",eos_layout_doHResize);
}
function createXMLHttp(){
  if(window.XMLHttpRequest){
    return (new XMLHttpRequest());
  }
  var _=["MSXML2.XMLHTTP.3.0","MSXML2.XMLHTTP","Microsoft.XMLHTTP"];
  for(var $=0;$<_.length;$++){
    try{
      xmlhttp_ver=_[$];
      return new ActiveXObject(_[$]);
    }
    catch(A){
    }
  }
  return null;
}
function Ajax($,_){
  this.url=$;
  this.param=[];
  this.method="POST";
  this.encoding="utf-8";
  this.async=_||false;
  this.xmlParam="";
  this.submitParam=null;
  this.isHTML=false;
  this.submitType="xml";
  this.isCommonUrl=false;
  this.reset();
}
Ajax.prototype.formToAjax=function(C){
  var $="";
  D=$id(C);
  if(D){
  }else {
    D=$name(C);
  }
  if(D){
  }else {
    alert(D+" \u5bf9\u8c61\u672a\u627e\u5230\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458!");
    return ;
  }
  for(i=0;i<D.elements.length;i++){
    var B=D.elements[i];
    if(B.type=="radio"||B.type=="checkbox"){
      if(!B.checked){
        continue ;
      }
    }
    var _=D.elements[i].name||D.elements[i].id,A=getElementValue(D.elements[i]);
    if(_){
      this.addParam(_,A);
    }
  }
};
Ajax.prototype.initParam=function(){
  var B="",_="<root>";
  for(var A=0;A<this.param.length;A++){
    var $=this.param[A];
    B+="<param><key>"+$.key+"</key><value>"+$.value+"</value></param>";
  }
  _+="<params>"+B+"</params>\n";
  var C=this.xmlParam;
  if(C&&C.indexOf("<root><data>")==0){
    C=C.substr("<root><data>".length);
    C=C.substr(0,C.lastIndexOf("</data></root>"));
  }
  _+="<data>"+C+"</data></root>";
  this.submitParam=_;
};
Ajax.prototype.setAsync=function($){
  if($==null){
    $=true;
  }
  this.async=$;
};
Ajax.prototype.wrapperUrl=function(){
  var B=this.url;
  if(B&&(B.substr(B.length-4)!=".biz")){
    return this.url;
  }else {
    if(B){
      var _=B.lastIndexOf(".",B.length-5),A=B.substr(0,_),$=B.substring(_+1,B.length-4);
      B="/"+A+".do?method="+$;
      return B;
    }
  }
};
Ajax.prototype.wrapperData=function(){
  return "<?xml version=\"1.0\" encoding=\""+this.encoding+"\"?>"+this.submitParam;
};
Ajax.prototype.setRequestHeaders=function(){
  if(isMoz&&this.xmlHttp.overrideMimeType){
    this.xmlHttp.overrideMimeType("text/html;charset=utf-8");
  }
  this.xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset="+this.encoding);
  this.xmlHttp.setRequestHeader("X-Requested-With","XMLHttpRequest");
  this.xmlHttp.setRequestHeader("_eosAjax",this.submitType);
  this.xmlHttp.setRequestHeader("encoding",this.encoding);
};
Ajax.prototype.reset=function(){
  this.retDom=null;
  this.responseText=null;
  this.xmlHttp=createXMLHttp();
};
Ajax.prototype.cancel=function(){
  if(!this.running){
    return this;
  }
  this.running=false;
  this.xmlHttp.abort();
  this.reset();
  return this;
};
Ajax.prototype.isSuccess=function(){
  return !!!this.getException();
};
Ajax.prototype.onSuccess=function(){
};
Ajax.prototype.onFailure=function($){
  alert(AJAX_EXCEPTION+"\n\n"+$);
};
Ajax.prototype.callBack=function(){
  var $=false,_=null,A=this.getExceptionInvalid();
  if(A===true||A==="true"){
    _=this.getLoginPage();
    if(_){
      $=true;
    }
    alert("\u767b\u5f55\u8d85\u65f6\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\uff01\uff01\uff01");
    A=AJAX_EXCEPTION_INVALID+"\n\n"+this.url;
  }else {
    A=this.getExceptionMessage()||this.getException();
  }
  if($){
    if(window.contextPath&&_.indexOf(window.contextPath+"/")!=0){
      _=window.contextPath+(_.indexOf("/")==0?"":"/")+_;
    }
    top.location=_;
    return ;
  }
  if(A&&this.onFailure){
    return this.onFailure(A);
  }
  return this.onSuccess();
};
Ajax.prototype.onStateChange=function(){
  if(!this.xmlHttp||this.xmlHttp.readyState!=4||!this.running){
    return ;
  }
  try{
    $debug("\n return XML:\n "+EOSLog.formatXML(this.xmlHttp.responseText));
    this.responseText=this.xmlHttp.responseText;
    this.status=this.xmlHttp.readyState;
    this.retDom=createXmlDom();
    this.retDom.loadXML(this.responseText);
    return this.callBack.apply(this,arguments);
  }
  catch($){
    $handle($);
    return this.onFailure($);
  }
  finally{
    this.running=false;
    this.xmlHttp.onreadystatechange=function(){
    };
  }
};
Ajax.prototype.submit=function(C){
  this.running=true;
  this.encoding=C||this.encoding;
  this.initParam();
  try{
    var D=this.wrapperUrl();
    this.xmlHttp.open(this.method,D,this.async);
    var $=this.wrapperData(),_=this;
    if(this.async){
      this.xmlHttp.onreadystatechange=function(){
        _.onStateChange.apply(_,arguments);
      };
    }
    this.setRequestHeaders();
    $debug("submit url:"+this.url+"\n submit XML:\n"+EOSLog.formatXML($));
    var B="";
    if(this.sumbitType){
      B="submitType="+this.sumbitType+"&";
    }else {
      B="submitType=1&";
    }
    B+="ajax=";
    this.xmlHttp.send(B+escape($));
    if(!this.async){
      return this.onStateChange();
    }
  }
  catch(A){
    $error("xmlHttp ajax request error.");
    this.running=false;
  }
};
Ajax.prototype.getXMLDom=function(){
  if(this.retDom){
    return this.retDom;
  }
};
Ajax.prototype.getText=function(){
  if(this.xmlHttp){
    return this.xmlHttp.responseText;
  }
};
Ajax.prototype.getValue=function(_){
  if(this.retDom==null){
    return null;
  }
  if(_==null||_==""){
    return null;
  }
  var $=this.retDom.selectSingleNode(_);
  if($!=null){
    return getNodeValue($);
  }else {
    return null;
  }
};
Ajax.prototype.getValues=function($){
  var B=new Array();
  if(this.retDom==null){
    return B;
  }
  if($==null||$==""){
    return B;
  }
  var _=this.retDom.selectNodes($);
  for(var A=0;A<_.length;A++){
    var C=_[A];
    B[A]=getNodeValue(C);
  }
  return B;
};
Ajax.prototype.getDataset=function(_,A){
  var A=A||_,$=new Dataset(A);
  $.init(this.retDom.selectNodes(_));
};
Ajax.prototype.setForm=function(B,$){
  for(var _=0;_<B.elements.length;_++){
    var A=B.elements[_];
    this.setObjectValue(A,null,$);
  }
};
Ajax.prototype.setObjectValue=function(D,_,A){
  if(!(D)||D==null){
    return ;
  }
  var E=_;
  if(E==null){
    if(D.name&&D.name!=null&&D.name!=""){
      if(A!=null){
        E=A+"/"+D.name;
      }else {
        E=D.name;
      }
    }
  }
  if(!(D.type)){
    if(E==null){
      if(D.name&&D.name!=""){
        E=D.name;
      }else {
        if(D.id&&D.id!=""){
          E=D.id;
        }
      }
    }
    if(E!=null){
      var G=this.getProperty(E);
      if(G!=null){
        D.innerText=G;
      }
    }
    return ;
  }
  if(E!=null){
    if(D.type=="text"||D.type=="hidden"){
      G=this.getProperty(E);
      if(G!=null){
        D.value=G;
      }
      return ;
    }
    if(D.type=="radio"||D.type=="checkbox"){
      var F=this.dom.selectNodes("root/data/"+E);
      for(var B=0;B<F.length;B++){
        if(D.value==F[B].text){
          D.checked=true;
        }else {
          D.checked=false;
        }
      }
      return ;
    }
    if(D.type=="select-one"){
      G=this.getProperty(E);
      for(B=0;B<D.options.length;B++){
        if(G==D.options[B].value){
          D.options[B].selected=true;
        }
      }
      return ;
    }
    if(D.type=="select-multiple"){
      var $=this.getValues("root/data/"+E);
      if($.length>0){
        for(B=0;B<D.options.length;B++){
          D.options[B].selected=false;
          for(var C=0;C<$.length;C++){
            if($[C]==D.options[B].value){
              D.options[B].selected=true;
            }
          }
        }
      }
      return ;
    }
  }
};
Ajax.prototype.Updater=function(A){
  this.isHTML=true;
  this.submitType="html";
  this.submit("utf-8");
  var _=this.retDom.selectSingleNode("root/data"),$=getNodeValue(_);
  setInnerHTML(A,$);
};
Ajax.prototype.addParam=function($,_){
  this.param.push({key:$,value:_});
};
Ajax.prototype.clearParam=function(){
  this.param=[];
};
Ajax.prototype.getProperty=function($){
  if(this.retDom==null){
    return null;
  }
  if($==null||$==""){
    return null;
  }
  var _=this.retDom.selectSingleNode("root/data/"+$);
  if(_!=null){
    return getNodeValue(_);
  }else {
    return null;
  }
};
Ajax.prototype.submitForm=function($,D){
  var _="";
  $=$id($);
  for(i=0;i<$.elements.length;i++){
    var C=$.elements[i];
    if(C.type=="radio"||C.type=="checkbox"){
      if(!C.checked){
        continue ;
      }
    }
    var A=$.elements[i].name||$.elements[i].id,B=getElementValue($.elements[i]);
    if(A){
      this.addParam(A,B);
    }
  }
  this.submit(D);
  if(this.retDom==null){
    return false;
  }
  return true;
};
Ajax.prototype.loadData=function($){
  this.submitXML($);
};
Ajax.prototype.submitXML=function(_,$){
  if(_){
    this.xmlParam=_;
  }
  this.submit($);
};
Ajax.prototype.getResponseXMLDom=function(){
  return this.retDom;
};
Ajax.prototype.getReturnNodeValue=function(_){
  var $=this.getResponseXMLDom(),A=$?$.selectNodes(_):null;
  if(A&&A.length>0){
    return getNodeValue(A[0]);
  }
  return null;
};
Ajax.prototype.getException=function(){
  var $="/root/exceptions";
  return this.getReturnNodeValue($);
};
Ajax.prototype.getExceptionCode=function(){
  var $="/root/exceptions/exception/code";
  return this.getReturnNodeValue($);
};
Ajax.prototype.getExceptionMessage=function(){
  var $="/root/exceptions/exception/message";
  return this.getReturnNodeValue($);
};
Ajax.prototype.getExceptionInvalid=function(){
  var $="/root/exceptions/invalid";
  return this.getReturnNodeValue($);
};
Ajax.prototype.getLoginPage=function(){
  var _="/root/exceptions/loginPage",$=this.getReturnNodeValue(_);
  $=$?trim($+""):null;
  return $;
};
var HideSubmit=Ajax;
function ServiceCaller(){
  this.service_URL_SUFFIX=".service";
  this.ajax=null;
  this.encoding="utf-8";
}
ServiceCaller.submitXMLTemplate=function(A,$,_){
  var B=["<root><data>","\t<ajaxCall>","\t\t<serviceName>"+xmlConversion(A)+"</serviceName>","\t\t<operateName>"+xmlConversion($)+"</operateName>","\t\t<params>",_||"","\t\t</params>","\t\t<return></return>","\t\t<exception></exception>","\t</ajaxCall>","</data></root>"];
  return B.join("\n");
};
ServiceCaller.getParamesArray=function($,B,C){
  var _=[];
  $=$||[];
  C=C||$.length;
  for(var A=B;A<C;A++){
    _.push($[A]);
  }
  return _;
};
ServiceCaller.returnConversion=function($){
  var A=$.getAttribute("__category"),D=$.getAttribute("__type");
  if(A=="xml"){
    return $.xml;
  }else {
    if(A=="entity"){
      return Dataset.initEntity($);
    }else {
      if(A=="dataset"){
        var _=new Dataset($.tagName);
        _.init($.childNodes);
        return _;
      }else {
        if(A=="collection"){
          var B=$.selectNodes("./entry"),E=[];
          for(var C=0;C<B.length;C++){
            var $=B[C];
            if($.nodeType==1){
              E.push(getNodeValue($));
            }
          }
          return E;
        }
      }
    }
  }
  return getNodeValue($);
};
ServiceCaller.parameConversion=function(A){
  var E=null,B=null,_=typeof (A),F="primeval",$={data:"",type:F};
  if(A===null||A===undefined){
    B="null";
  }else {
    if(A===""){
      B="empty";
    }
  }
  if(B){
    $.data="<value __isNullOrEmpty=\""+B+"\" />";
    $.type=F;
    return $;
  }
  if(_=="string"||_=="number"||_=="boolean"){
    F="primeval";
    $.data=xmlConversion(""+A);
  }else {
    if(_=="object"&&A.xml&&(A.async===false||A.async===true)){
      F="xml";
      $.data=A.xml;
    }else {
      if(A instanceof Entity){
        F="entity";
        $.data=A.toXMLString(false);
      }else {
        if(_=="object"&&A.tagName&&A.tagName.toUpperCase()=="FORM"){
          A=getFormElementsValue(A);
        }
        if(A instanceof Dataset){
          A=A.getEntities();
        }
        if(A instanceof Array){
          F="collection";
          E=[];
          for(var D=0;D<A.length;D++){
            E.push(ServiceCaller.parameConversion(A[D]).data);
          }
          $.data=E.join("\n");
        }else {
          F="form";
          E=[];
          for(var C in A){
            E.push("<property>");
            E.push("<key>");
            E.push(xmlConversion(C));
            E.push("</key>");
            E.push("<value>");
            E.push(xmlConversion(A[C]+""));
            E.push("</value>");
            E.push("</property>");
          }
          $.data=E.join("\n");
        }
      }
    }
  }
  $.data=(F=="collection"||F=="form")?$.data:"<value>"+$.data+"</value>";
  $.type=F;
  return $;
};
ServiceCaller.getSubmitXML=function(_){
  var A=[];
  for(var $=0;$<_.length;$++){
    result=ServiceCaller.parameConversion(_[$]);
    A.push("<param __category=\""+result.type+"\">");
    A.push(result.data);
    A.push("</param>");
  }
  return A.join("\n");
};
ServiceCaller.prototype.callBack=function(){
  var $=false,A=null,_=this.getExceptionInvalid();
  if(_===true||_==="true"){
    A=this.getLoginPage();
    if(A){
      $=true;
    }
    _=AJAX_EXCEPTION_INVALID+"\n\n"+this.serviceName+"."+this.operateName;
  }else {
    _=this.getExceptionMessage()||this.getException();
  }
  if($){
    if(window.contextPath&&A.indexOf(window.contextPath+"/")!=0){
      A=window.contextPath+(A.indexOf("/")==0?"":"/")+A;
    }
    top.location=A;
    return ;
  }
  if(_&&this.onFailure){
    return this.onFailure(_);
  }
  this.onSuccess();
  return this.getReturnValue();
};
ServiceCaller.prototype._callCore=function($,A,B,_,C){
  this.ajax=new Ajax($+this.service_URL_SUFFIX,B);
  this.ajax.callBack=_||this.ajax.callBack;
  var D=ServiceCaller.getSubmitXML(C);
  this.serviceName=$;
  this.operateName=A;
  this.ajax.submitXML(ServiceCaller.submitXMLTemplate($,A,D),this.encoding);
};
function form2Object(F,_){
  var $={};
  $=getFormElementsValue(F);
  var D=$;
  if(_){
    D={};
    _=_+"";
    _=_.lastIndexOf("/")==_.length-1?_:_+"/";
    if(_.indexOf("[*]")<0){
      for(var A in $){
        if(A.indexOf(_)==0){
          D[A]=$[A];
        }
      }
    }else {
      var E=false;
      for(A in $){
        for(var C=1;C<Number.MAX_VALUE;C++){
          var B=_.replace("[*]","["+C+"]");
          if(A.indexOf(B)==0){
            D[A]=$[A];
            break ;
          }
        }
      }
    }
  }
  return D;
}
ServiceCaller.prototype.call=function(_,A,$){
  this._callCore(_,A,false,null,ServiceCaller.getParamesArray(arguments,2));
  return this.callBack();
};
ServiceCaller.prototype.isSuccess=function(){
  return !!!this.getException();
};
ServiceCaller.prototype.onSuccess=function(){
};
ServiceCaller.prototype.onFailure=function($){
  alert(AJAX_EXCEPTION+"\n\n"+$);
};
ServiceCaller.prototype.callAsyn=function(B,$,A){
  var _=this;
  this._callCore(B,$,true,function(){
    _.callBack();
  },ServiceCaller.getParamesArray(arguments,2));
};
ServiceCaller.prototype.getResponseXMLDom=function(){
  return this.ajax.getResponseXMLDom();
};
ServiceCaller.prototype.getReturnValue=function(C,_){
  var A="/root/data/ajaxCall/return/value";
  _=_||this.getResponseXMLDom();
  var B=_?_.selectNodes(A):[],$=[];
  for(var D=0;D<B.length;D++){
    var E=B[D];
    if(E.nodeType==1){
      E=C===false?E:ServiceCaller.returnConversion(E);
      $.push(E);
    }
  }
  if($.length==0){
    $=null;
  }
  return $;
};
ServiceCaller.prototype.getReturnNodeValue=function($){
  return this.ajax?this.ajax.getReturnNodeValue($):null;
};
ServiceCaller.prototype.getReturn=function(){
  var $="/root/data/ajaxCall/return";
  return this.getReturnNodeValue($);
};
ServiceCaller.prototype.getException=function(){
  var $="/root/data/ajaxCall/exceptions/ecxeption";
  return this.getReturnNodeValue($);
};
ServiceCaller.prototype.getExceptionMessage=function(){
  var $="/root/data/ajaxCall/exceptions/exception/message";
  return this.getReturnNodeValue($);
};
ServiceCaller.prototype.getExceptionCode=function(){
  var $="/root/data/ajaxCall/exceptions/exception/code";
  return this.getReturnNodeValue($);
};
ServiceCaller.prototype.getExceptionInvalid=function(){
  var $="/root/data/ajaxCall/exceptions/invalid";
  return this.getReturnNodeValue($);
};
ServiceCaller.prototype.getLoginPage=function(){
  var _="/root/data/ajaxCall/exceptions/loginPage",$=this.getReturnNodeValue(_);
  $=$?trim($+""):null;
  return $;
};
var Eos_All_Message=new Array();
function isNull(_){
  if(typeof (_)=="object"){
    _=_.value;
  }
  var $;
  if(_.length==0){
    return true;
  }
  for($=0;$<_.length;$++){
    if(_.charAt($)!=" "){
      return false;
    }
  }
  return true;
}
function isIP(_){
  if(typeof (_)=="object"){
    _=_.value;
  }
  var $=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
  if($.test(_)){
    if(RegExp.$1.length>3||RegExp.$2.length>3||RegExp.$3.length>3||RegExp.$4.length>3){
      return false;
    }
    if(RegExp.$1>0&&RegExp.$1<256&&RegExp.$2<256&&RegExp.$3<256&&RegExp.$4<255){
      return true;
    }
  }
  return false;
}
function isInteger($){
  if(typeof ($)=="object"){
    $=$.value;
  }
  if(/^(\+|-)?\d+$/.test($)){
    return true;
  }else {
    return false;
  }
}
function isPositiveInteger($){
  if(typeof ($)=="object"){
    $=$.value;
  }
  if(/^(\+)?\d+$/.test($)){
    return true;
  }else {
    return false;
  }
}
function isURL(_){
  if(typeof (_)=="object"){
    _=_.value;
  }
  if(_.indexOf("://")!=-1){
    var $=_.substr(0,_.indexOf("://"));
    if($==""){
      return false;
    }
    $=_.substr(_.indexOf("://")+3,$.length);
    if($==""){
      return false;
    }
  }else {
    return false;
  }
  return true;
}
function isDecimal($,A,C){
  if(typeof ($)=="object"){
    $=$.value;
  }
  if(/^(\+|-)?\d+($|\.\d+$)/.test($)){
    var B=$.indexOf(".");
    if(A!=null){
      var _=$.length;
      if(B!=-1){
        _=_-1;
      }
      if(A<_){
        return false;
      }
    }
    if(C!=null){
      if(B==-1){
        return true;
      }
      if($.length-B-1>C){
        return false;
      }
    }
    return true;
  }else {
    return false;
  }
}
function isPort($){
  if(typeof ($)=="object"){
    $=$.value;
  }
  if(/^\d+$/.test($)){
    if(parseInt($,10)>65535||parseInt($,10)<=0){
      return false;
    }
    return true;
  }else {
    return false;
  }
}
function isEmail(_){
  if(typeof (_)=="object"){
    _=_.value;
  }
  var $=/^([-_A-Za-z0-9\.]+)@[A-Za-z0-9]{1}[_A-Za-z0-9\.]*\.[A-Za-z0-9]+$/;
  if($.test(_)){
    return true;
  }
  return false;
}
function isLastMatch(A,_){
  if(typeof (A)=="object"){
    A=A.value;
  }
  if(typeof (_)=="object"){
    _=_.value;
  }
  var $=new RegExp(_+"$");
  if($.test(A)){
    return true;
  }else {
    return false;
  }
}
function isFirstMatch(A,_){
  if(typeof (A)=="object"){
    A=A.value;
  }
  if(typeof (_)=="object"){
    _=_.value;
  }
  var $=new RegExp("^"+_);
  if($.test(A)){
    return true;
  }else {
    return false;
  }
}
function isMatch(A,_){
  if(typeof (A)=="object"){
    A=A.value;
  }
  if(typeof (_)=="object"){
    _=_.value;
  }
  var $=new RegExp(_);
  if($.test(A)){
    return true;
  }else {
    return false;
  }
}
function isChinaMobileNo(_){
  if(typeof (_)=="object"){
    _=_.value;
  }
  var $=/^(13[0-9]|15[0-9]|18[0-9]|147)\d{8}$/;
  B=new RegExp($);
  if(B.test(_)){
    return true;
  }
  return false;
}
function isPhoneNo(A){
  if(typeof (A)=="object"){
    A=A.value;
  }
  var $=/(^([0][1-9]{2,3}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0][1-9]{2,3}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/,_=new RegExp($);
  if(_.test(A)){
    return true;
  }
  return false;
}
function isNumberOr_Letter($){
  if(typeof ($)=="object"){
    $=$.value;
  }
  if(/[^(\w*)]/.test($)){
    return false;
  }
  return true;
}
function isNumberOrLetter($){
  if(typeof ($)=="object"){
    $=$.value;
  }
  if(/[^(a-z)*(A-Z)*(0-9)*]/.test($)){
    return false;
  }
  return true;
}
function isFolder(A){
  if(typeof (A)=="object"){
    A=A.value;
  }
  var $=/(^[^\.])/,_=new RegExp($);
  if(!_.test(A)){
    return false;
  }
  $=/(^[^\\\/\?\*\"\<\>\:\|]*$)/;
  _=new RegExp($);
  if(_.test(A)){
    return true;
  }
  return false;
}
function isChinaOrNumbOrLett(A){
  if(typeof (A)=="object"){
    A=A.value;
  }
  var $="^[0-9a-zA-Z\u4e00-\u9fa5]+$",_=new RegExp($);
  if(_.test(A)){
    return true;
  }
  return false;
}
function isChinaZipcode($){
  if(typeof ($)=="object"){
    $=$.value;
  }
  if(!isInteger($)){
    return false;
  }
  if($.length!=6){
    return false;
  }
  return true;
}
function isChinaIDNo(G){
  if(typeof (G)=="object"){
    G=G.value;
  }
  var F=EOS_CITY_LIST,A=0,$="",B=G,_=B.length;
  if(!/^\d{17}(\d|X|x)$/i.test(B)&&!/^\d{15}$/i.test(B)){
    return false;
  }
  B=B.replace(/X|x$/i,"a");
  if(F[parseInt(B.substr(0,2))]==null){
    return false;
  }
  if(_==18){
    sBirthday=B.substr(6,4)+"-"+Number(B.substr(10,2))+"-"+Number(B.substr(12,2));
    var D=new Date(sBirthday.replace(/-/g,"/"));
    if(sBirthday!=(D.getFullYear()+"-"+(D.getMonth()+1)+"-"+D.getDate())){
      return false;
    }
    for(var E=17;E>=0;E--){
      A+=(Math.pow(2,E)%11)*parseInt(B.charAt(17-E),11);
    }
    if(A%11!=1){
      return false;
    }
  }else {
    if(_==15){
      sBirthday="19"+B.substr(6,2)+"-"+Number(B.substr(8,2))+"-"+Number(B.substr(10,2));
      var D=new Date(sBirthday.replace(/-/g,"/")),C=D.getFullYear().toString()+"-"+(D.getMonth()+1)+"-"+D.getDate();
      if(sBirthday!=C){
        return false;
      }
    }
  }
  return true;
}
function checkPeriod(_,$){
  if(typeof (_)=="object"){
    _=_.value;
  }
  if(typeof ($)=="object"){
    $=$.value;
  }
  if(!isDate(_)){
    return false;
  }
  if(!isDate($)){
    return false;
  }
  if($>=_){
    return true;
  }else {
    return false;
  }
}
function matchRegExp($,B,A){
  if(typeof ($)=="object"){
    $=$.value;
  }
  if(B==null||B==""){
    return false;
  }
  var _;
  if(A==null||A==""){
    _=new RegExp(B);
  }else {
    if(/[^mig]/.test(A)){
      return false;
    }
    _=new RegExp(B,A);
  }
  if(_.test($)){
    return true;
  }
  return false;
}
function checkInput(obj){
  if(processExtAttr(obj)){
    return true;
  }
  var is_Null=obj.getAttr("allowNull");
  if(is_Null=="false"){
    if(isNull(obj)){
      f_alert(obj,CHECK_MUST_INPUT);
      return false;
    }
  }else {
    if(isNull(obj)){
      f_alert_verify_successful(obj);
      return true;
    }
  }
  var maxLength=obj.getAttr("maxLength"),minLength=obj.getAttr("minLength");
  if(maxLength!=null){
    if(obj.value.length>maxLength){
      f_alert(obj,CHECK_INPUT_LENGTH_CANNOT_MORE_THAN+maxLength);
      return false;
    }
  }
  if(minLength!=null){
    if(obj.value.length<minLength){
      f_alert(obj,CHECK_INPUT_LENGTH_CANNOT_LESS_THAN+minLength);
      return false;
    }
  }
  var type=obj.getAttr("type");
  if(type!=null){
    try{
      if(!eval("f_check_"+type+"(obj)")){
        return false;
      }
    }
    catch(o){
      alert(type+":"+CHECK_ERROROCCUR);
      return false;
    }
  }
  var maxValue=obj.getAttr("maxValue"),minValue=obj.getAttr("minValue");
  if(!(maxValue==null&&minValue==null)){
    var inputValue,oMinValue,oMaxValue;
    if(isNaN(parseFloat(obj.value))){
      inputValue=obj.value;
    }else {
      inputValue=parseFloat(obj.value);
    }
    if(maxValue!=null){
      if(isNaN(parseFloat(maxValue))||minValue==null||isNaN(parseFloat(minValue))||isNaN(parseFloat(obj.value))){
        oMaxValue=maxValue;
      }else {
        oMaxValue=parseFloat(maxValue);
      }
      if(inputValue>oMaxValue){
        f_alert(obj,CHECK_NOT_MORE_THAN+maxValue);
        return false;
      }
    }
    if(minValue!=null){
      if(isNaN(parseFloat(minValue))||maxValue==null||isNaN(parseFloat(maxValue))||isNaN(parseFloat(obj.value))){
        oMinValue=minValue;
      }else {
        oMinValue=parseFloat(minValue);
      }
      if(inputValue<oMinValue){
        f_alert(obj,CHECK_NOT_LESS_THAN+minValue);
        return false;
      }
    }
  }
  f_alert_verify_successful(obj);
  return true;
}
function processExtAttr(E){
  if(E.EOS_extendedAttribute!=null){
    return false;
  }
  var D=E.validateAttr||E.getAttribute("validateAttr");
  if(!D){
    return true;
  }
  var $=new Object(),F=D.split(";");
  for(var C=0;C<F.length;C++){
    var _=F[C].split("=");
    if(_.length!=2){
      continue ;
    }
    var B=_[0],A=_[1];
    $[B]=A;
  }
  E.EOS_extendedAttribute=$;
  E.getAttr=function($){
    return this.EOS_extendedAttribute[$];
  };
}
function f_check_number($){
  if(/^\d+$/.test($.value)){
    return true;
  }else {
    f_alert($,CHECK_INPUT_NUMBER);
    return false;
  }
}
function f_check_naturalNumber($){
  var _=$.value;
  if(_.length>1&&_.charAt(0)=="0"){
    f_alert($,CHECK_INPUT_NATURALNUMBER);
    return false;
  }
  if(/^[0-9]+$/.test(_)){
    return true;
  }else {
    f_alert($,CHECK_INPUT_NATURALNUMBER);
    return false;
  }
}
function f_check_integer($){
  if(isInteger($)){
    return true;
  }else {
    f_alert($,CHECK_IUPUT_INT);
    return false;
  }
}
function f_check_float($){
  if(/^(\+|-)?\d+($|\.\d+$)/.test($.value)){
    return true;
  }else {
    f_alert($,CHECK_INPUT_FLOAT);
    return false;
  }
}
function f_check_zh($){
  if(/^[\u4e00-\u9fa5]+$/.test($.value)){
    return true;
  }
  f_alert($,CHECK_INPUT_ZH);
  return false;
}
function f_check_letter($){
  if(/^[A-Za-z]+$/.test($.value)){
    return true;
  }
  f_alert($,CHECK_INPUT_LETTER);
  return false;
}
function f_check_IP($){
  if(isIP($.value)){
    return true;
  }
  f_alert($,CHECK_INVALID_IP);
  return false;
}
function f_check_port($){
  if(isPort($.value)){
    return true;
  }
  f_alert($,CHECK_INVALID_PORT);
  return false;
}
function f_check_URL($){
  if(isURL($.value)){
    return true;
  }
  f_alert($,CHECK_INVALID_WEBSITE);
  return false;
}
function f_check_email($){
  if(isEmail($.value)){
    return true;
  }
  f_alert($,CHECK_INVALID_EMAIL);
  return false;
}
function f_check_folder($){
  if(isFolder($.value)){
    return true;
  }
  f_alert($,CHECK_INVALID_FOLDER);
  return false;
}
function f_check_chinaMobile($){
  if(isChinaMobileNo($.value)){
    return true;
  }
  f_alert($,CHECK_INVALD_HANDPHONE);
  return false;
}
function f_check_phone($){
  if(isPhoneNo($.value)){
    return true;
  }
  f_alert($,CHECK_INVALID_PHONE);
  return false;
}
function f_check_chinaZipcode($){
  if(!/^\d+$/.test($.value)){
    f_alert($,CHECK_POSTALCODE_MUST_NUMBER);
    return false;
  }
  if($.value.length!=6){
    f_alert($,CHECK_INVAILID_POSTALCODE_LENGTH);
    return false;
  }
  return true;
}
function f_check_chinaIDNo($){
  if(isChinaIDNo($)){
    return true;
  }else {
    f_alert($,CHECK_INVALID_ID);
    return false;
  }
}
function f_check_formatStr($){
  var _=$.getAttr("regExpr");
  if(matchRegExp($,_)){
    return true;
  }
  f_alert($,CHECK_INVALID_EXP);
  return false;
}
function f_check_double(A){
  var _=A.getAttr("totalDigit"),$=A.getAttr("fracDigit");
  if(isDecimal(A,_,$)){
    return true;
  }
  var B=CHECK_INPUT_NUMBER;
  if(_!=null){
    B=B+CHECK_LEGNT_NOT_THAN+_+CHECK_WEI;
  }
  if($!=null){
    B=B+CHECK_FRACTION_LENGTH_NOT_THAN+$+CHECK_WEI;
  }
  f_alert(A,B);
  return false;
}
function f_alert(_,B){
  try{
    checkTabPage(_);
  }
  catch($){
  }
  var A;
  if(_.getAttr){
    A=_.getAttr("message");
  }
  if(A!=null){
    B=A;
  }
  f_alert_verify_failure(_,B);
  f_alert_resetMessagePos();
}
function checkTabPage(B){
  var $=B.parentNode;
  while($&&$.getAttribute){
    var C=$.eos_tabpage_id||$.getAttribute("eos_tabpage_id");
    if(C){
      var A=$id(C);
      if(A){
        try{
          A.getTabPane().selectTab(A);
        }
        catch(_){
        }
      }
      break ;
    }
    $=$.parentNode;
  }
}
function checkForm(_){
  var $=true,C=null,A;
  for(A=_.elements.length-1;A>=0;A--){
    if((_.elements[A].validateAttr||_.elements[A].getAttribute("validateAttr"))+""=="undefined"){
      continue ;
    }
    if(_.elements[A].disabled==true){
      continue ;
    }
    if(_.elements[A].name==null||_.elements[A].name==""){
      if(!(_.elements[A].id!=null&&_.elements[A].id.indexOf("_input")!=-1)){
        continue ;
      }
    }
    if(checkInput(_.elements[A])==false){
      C=_.elements[A];
      $=false;
    }
  }
  if($){
    return $;
  }
  var B=C.type;
  if(B=="text"||B=="TEXT"||B=="textarea"||B=="TEXTAREA"){
    C.select();
  }
  return $;
}
function f_alert_getPosition(D){
  var B=D.id;
  if(B!=null){
    var _=B.indexOf("_input");
    if(_!=-1){
      D=$id(B.substr(0,_)+"_container")||D;
    }
  }
  var C=D.offsetTop,E=D.offsetLeft,$=D,A=true;
  while($=$.offsetParent){
    if($.tagName=="BODY"){
      continue ;
    }
    C+=$.offsetTop-$.scrollTop;
    E+=$.offsetLeft-$.scrollLeft;
    if($.style.position.toLowerCase()=="absolute"){
      A=false;
    }
  }
  E=E+D.offsetWidth+10;
  return {left:E,top:C};
}
function f_alert_create_Message_plane(_,$){
  var A=document.createElement("DIV");
  A.className="alert_message";
  var B=f_alert_getPosition(_);
  A.style.top=B.top;
  A.style.left=B.left;
  A.style.height=isFF?_.offsetHeight-4:_.offsetHeight;
  A.style.whiteSpace="nowrap";
  A.onclick=function(){
    this.hidden();
  };
  A.show=function($){
    this.innerHTML="&nbsp;"+$+"&nbsp;";
    if(A.isLoading){
      if(A.timeout){
        clearTimeout(A.timeout);
      }
      A.timeout=setTimeout(function(){
        return _showMessage.apply(A,[A,$]);
      },10);
    }else {
      if(!this.isShow){
        document.body.appendChild(this);
        this.isShow=true;
        this.isLoading=true;
        setOpacity(this,0);
        fx_fadeIn(this,function(){
          return loadingFinished.apply(A,[A]);
        },500);
      }
    }
  };
  A.hidden=function(){
    if(A.isLoading){
      if(A.timeout){
        clearTimeout(A.timeout);
      }
      A.timeout=setTimeout(function(){
        return _hiddenMessage.apply(A,[A]);
      },10);
    }else {
      this.isLoading=true;
      this.isShow=false;
      fx_fadeOut(this,function(){
        try{
          document.body.removeChild(A);
        }
        catch($){
        }
        return loadingFinished.apply(A,[A]);
      },400);
    }
  };
  A.setPos=function($){
    this.style.top=$.top;
    this.style.left=$.left;
  };
  document.body.appendChild(A);
  A.show($);
  _.Eos_Message=A;
  Eos_All_Message[Eos_All_Message.length]=_;
}
function loadingFinished($){
  this.isLoading=false;
}
function _hiddenMessage($){
  if($.isLoading){
    $.timeout=setTimeout(function(){
      return _hiddenMessage.apply($,[$]);
    },10);
  }else {
    $.hidden();
  }
}
function _showMessage($,_){
  if($.isLoading){
    $.timeout=setTimeout(function(){
      return _showMessage.apply($,[$,_]);
    },10);
  }else {
    $.show(_);
  }
}
function f_alert_show_message($,_){
  if($.Eos_Message!=null){
    f_alert_resetMessagePos();
    $.Eos_Message.show(_);
  }else {
    f_alert_create_Message_plane($,_);
  }
}
function f_alert_hidden_message($){
  if($.Eos_Message!=null){
    $.Eos_Message.hidden();
  }
}
function f_alert_verify_failure($,_){
  removeClass($,"verify_successful");
  addClass($,"verify_failure");
  f_alert_show_message($,_);
}
function f_alert_verify_successful($){
  removeClass($,"verify_failure");
  addClass($,"verify_successful");
  f_alert_hidden_message($);
}
function f_alert_resetMessagePos(){
  for(var $=0;$<Eos_All_Message.length;$++){
    var _=f_alert_getPosition(Eos_All_Message[$]);
    Eos_All_Message[$].Eos_Message.setPos(_);
  }
}
function f_alert_hidden_all_message(){
  f_alert_resetMessagePos();
  for(var $=0;$<Eos_All_Message.length;$++){
    Eos_All_Message[$].Eos_Message.hidden();
  }
}
eventManager.add(window,"resize",f_alert_resetMessagePos);
function regCheckEvent($,B){
  if($==null){
    return ;
  }
  B=B||"blur";
  var _;
  for(_=$.elements.length-1;_>=0;_--){
    if(($.elements[_].validateAttr||$.elements[_].getAttribute("validateAttr"))+""=="undefined"){
      continue ;
    }
    var A=$.elements[_];
    eventManager.add(A,B,function(_){
      _=eventManager.getEvent()||_;
      var $=_.srcElement||_.target;
      checkInput($);
    });
  }
}
function checkOnblur($){
  regCheckEvent($);
}
function checkOnkeypress($){
  regCheckEvent($,"keyup");
  regCheckEvent($,"focus");
}
var EOSMaskCache={};
function Mask(A,_){
  var $=this;
  this.id=A;
  this.inited=false;
  EOSMaskCache[this.id]=this;
  this.isFrameSetPage=false;
  this.win=_||window;
  this.init=function(){
    this.frameList=this.win.document.getElementsByTagName("frame");
    if(this.frameList.length>0){
      this.isFrameSetPage=true;
      this.inited=true;
      return this.inited;
    }
    this.container=this.container||this.win.document.body;
    if(!this.inited&&this.win.document.body){
      this.core=this.win.document.createElement("div");
      this.core.id=this.id+"_div";
      this.core.onmousedown=function(){
        eventManager.stopBubble();
      };
      if(isIE){
        var $="<iframe style=\"filter:Alpha(Opacity=0);position:absolute;z-index:-1;width:expression(this.parentNode.offsetWidth);height:expression(this.parentNode.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>";
        $+="<div style=\"width:expression(this.parentNode.offsetWidth);height:expression(this.parentNode.offsetHeight);z-index:1\" class=\"eos-page-mask\"></div>";
        this.core.innerHTML=$;
      }
      this.core.className="eos-page-mask";
      this.core.style.display="none";
      this.win.document.body.appendChild(this.core);
      this.inited=true;
    }
    return this.inited;
  };
  this.setContainer=function($){
    if(this.container&&this.container.setAttribute){
      this.container.setAttribute("_mask",null);
    }
    this.container=$;
    if(this.container&&this.container.setAttribute){
      this.container.setAttribute("_mask",this.id);
    }
  };
  this.isHidden=function(){
    return this.core.style.display=="none";
  };
  this.autoResize=function(){
    if(this.isHidden()){
      return ;
    }
    var _=parseInt(this.container.style.marginTop),B=parseInt(this.container.style.marginLeft);
    _=_||0;
    B=B||0;
    var E=0,A=0,C,D;
    if(this.container==this.win.document.body){
      C=this.win.screen.width;
      D=this.win.screen.height;
    }else {
      E=getElementXY(this.container);
      A=E[0];
      E=E[1];
      C=this.container.offsetWidth;
      D=this.container.offsetHeight;
    }
    C+=_;
    D+=B;
    var $=this.core;
    $.style.top=E-_+"px";
    $.style.left=A-B+"px";
    $.style.width=C+"px";
    $.style.height=D+"px";
    if(this.container==this.win.document.body){
      this.container.scrollTop=0;
      this.container.scrollLeft=0;
    }
  },this.show=function(A){
    if(this.isFrameSetPage){
      for(var _=0;_<this.frameList.length;_++){
        try{
          if(!this.frameList[_].contentWindow.eos_pageMask){
            this.frameList[_].contentWindow.eos_pageMask=new Mask("eos_pageMask"+"_f_"+_,this.frameList[_].contentWindow);
          }
          this.frameList[_].contentWindow.eos_pageMask.show();
        }
        catch($){
        }
      }
      return ;
    }
    if(!this.init()||!this.isHidden()){
      return ;
    }
    if(A){
      this.setContainer(A);
    }
    this.oldOverFlowX=this.container.style.overflowX;
    this.oldOverFlowY=this.container.style.overflowY;
    this.container.style.overflowX="hidden";
    this.container.style.overflowY="hidden";
    var B=getMaxZindex(this.container.ownerDocument||document);
    B++;
    this.container.style.zIndex=B;
    this.core.style.display="block";
    B++;
    this.core.style.zIndex=B;
    this.core.focus();
    this.autoResize();
  };
  this.hide=function(){
    if(this.isFrameSetPage){
      for(var $=0;$<this.frameList.length;$++){
        try{
          if(!this.frameList[$].contentWindow.eos_pageMask){
            this.frameList[$].contentWindow.eos_pageMask=new Mask("frame_"+this.frameList[$].contentWindow.location,this.frameList[$].contentWindow);
          }
          this.frameList[$].contentWindow.eos_pageMask.hide();
        }
        catch(_){
        }
      }
      return ;
    }
    if(this.isHidden()||!this.container){
      return ;
    }
    this.container.style.overflowX=this.oldOverFlowX;
    this.container.style.overflowY=this.oldOverFlowY;
    this.core.style.display="none";
  };
  this.hideAfterTime=function(_){
    this.win.setTimeout(function(){
      $.hide();
    },_||100);
  };
  if(window.addEventListener){
    window.addEventListener("load",function(){
      $.init();
    },false);
  }else {
    window.attachEvent("onload",function(){
      $.init();
    });
  }
}
window.eos_pageMask=new Mask("eos_pageMask");
function maskElement(_,B){
  B=B||window;
  _=$id(_);
  _.maskId="_eos_"+_.id+"_mask";
  var E=$id(_.maskId,B.document),D=_.offsetWidth,$=_.offsetHeight;
  if(!E){
    E=$create("div",B.document);
    E.style.display="none";
    E.id=_.maskId;
    if(document!=B.document){
      moveCss(document,B.document);
    }
    B.document.body.appendChild(E);
  }
  E.style.position="absolute";
  E.style.width=D+"px";
  E.style.height=$+"px";
  var C=getElementXY(_);
  setElementXY(E,C);
  E.className="eos-page-mask";
  E.style.display="block";
  B._bakscroll=B.onscroll;
  var A=getMaxZindex(B.document);
  E.onmousedown=function(){
    eventManager.stopBubble();
  };
  E.onkeydown=function(){
    eventManager.stopBubble();
  };
  E.onkeypress=function(){
    eventManager.stopBubble();
  };
  E.style.zIndex=A+2;
  E.pActiveElement=document.activeElement;
  try{
    E.focus();
  }
  catch(F){
  }
  try{
    E.getElementsByTagName("a")[0].focus();
  }
  catch(F){
  }
}
function unMaskElement(_,A){
  A=A||window;
  _=$id(_);
  var $=$id(_.maskId);
  if($){
    $.style.display="none";
  }
}
function maskTop(){
  maskWindow(top);
}
function maskWindow(A){
  A=A||window;
  hideSelect(A);
  if(isFrameSet(A)){
    var $=A.document.getElementsByTagName("frame");
    for(var C=0;C<$.length;C++){
      try{
        maskWindow($[C].contentWindow);
      }
      catch(B){
      }
    }
  }else {
    var D=$id("_eos_page_mask",A.document);
    if(!D){
      D=$create("div",A.document);
      D.style.display="none";
      D.id="_eos_page_mask";
      D.className="eos-page-mask";
      if(document!=A.document){
        moveCss(document,A.document);
      }
      A.document.body.appendChild(D);
    }
    function E(){
      var B=parseInt(A.document.body.style.marginTop),H=parseInt(A.document.body.style.marginLeft);
      B=B||0;
      H=H||0;
      var _=0,F=0,C,$,E=(Math.max(A.document.documentElement.scrollLeft,A.document.body.scrollLeft)||0),G=(Math.max(A.document.documentElement.scrollTop,A.document.body.scrollTop)||0);
      C=Math.max(A.document.body.scrollWidth,(A.document.body.clientWidth+E));
      $=Math.max(A.document.body.scrollHeight,(A.document.body.clientHeight+G));
      C+=B;
      $+=H;
      D.style.top=_-B+"px";
      D.style.left=F-H+"px";
      D.style.width=C+"px";
      D.style.height=$+"px";
    }
    D.className="eos-page-mask";
    D.style.display="block";
    E();
    A._bakresize=A.onresize;
    window.onresize=function(){
      E();
    };
    var _=getMaxZindex(A.document);
    D.onmousedown=function(){
      eventManager.stopBubble();
    };
    D.onkeydown=function(){
      eventManager.stopBubble();
    };
    D.onkeypress=function(){
      eventManager.stopBubble();
    };
    D.style.zIndex=_+2;
    D.pActiveElement=document.activeElement;
    try{
      D.focus();
    }
    catch(B){
    }
    try{
      D.getElementsByTagName("a")[0].focus();
    }
    catch(B){
    }
  }
}
function hideSelect($){
}
function showSelect($){
}
function unMaskWindow($){
  $=$||window;
  showSelect($);
  if(isFrameSet($)){
    var C=$.document.getElementsByTagName("frame");
    for(var A=0;A<C.length;A++){
      try{
        unMaskWindow(C[A].contentWindow);
      }
      catch(_){
      }
    }
  }else {
    var B=$id("_eos_page_mask",$.document);
    if(B){
      try{
        B.style.display="none";
        B.pActiveElement&&B.pActiveElement.focus();
        B.pActiveElement=null;
      }
      catch(_){
      }
    }
    $.onresize=$._bakresize;
  }
}
function unMaskTop(){
  unMaskWindow(top);
}
function isFrameSet($){
  return $.document.getElementsByTagName("frameset").length!=0;
}
var EOSProgressCache={},ProgressBar=function($){
  $=$||"eos_pageProgressBar";
  this.winWidth=160;
  this.winHeight=60;
  this.id=$;
  var _="__progressbar_"+$+"_title";
  EOSProgressCache[$]=this;
  var A=$id($);
  if(!A){
    A=$create("div");
    A.id=$;
    A.className="eos-popwin";
    A.style.width="100px";
    var B=["<div  class=\"eos-popwin-body\">","<div style=\"margin:5px;margin-top:15px\" ><div class=\"eos-progress-icon\"></div>","<span id=\"__progressbar_"+$+"_title\" ></span></div>","</div>"].join("\n");
    A.innerHTML=B;
    document.body.appendChild(A);
  }
  this.isHidden=true;
  this.container=A;
  this.titleBar=$id("__progressbar_"+$+"_title");
  this.show=function(B,$,C){
    B=B||"Please Waiting ...";
    this.titleBar.innerHTML=B;
    var A;
    this.container.style.width=this.winWidth+"px";
    this.container.style.height=this.winHeight+"px";
    this.container.style.display="";
    if($&&C){
      this.container.style.left=$+"px";
      this.container.style.top=C+"px";
    }else {
      if(typeof ($)=="object"&&$.offsetLeft){
        A=$;
        $=A.offsetLeft+(A.offsetWidth-this.winWidth)/2;
        C=A.offsetTop+(A.offsetHeight-this.winHeight)/2;
        this.container.style.left=$+"px";
        this.container.style.top=C+"px";
      }else {
        moveDivToCenter(this.container);
      }
    }
    try{
      this.container.focus();
    }
    catch(_){
    }
    this.isHidden=false;
  };
  this.hide=function(){
    if(this.container){
      this.container.style.display="none";
    }
    this.isHidden=true;
  };
};
function showProgressBar(_,B,$,C){
  var A=EOSProgressCache[_]||null;
  if(!A){
    A=new ProgressBar(_);
  }
  A.show(B,$,C);
  return A;
}
function hideProgressBar(_){
  _=_||"eos_pageProgressBar";
  var $=EOSProgressCache[_]||null;
  if($){
    $.hide();
  }
  return $;
}
function hideAllProgressBar(){
  for(var $ in EOSProgressCache){
    EOSProgressCache[$].hide();
  }
}
var EOStopWin=EOStopWin||_get_top_window()||window;
EOStopWin["_eos_modal_dialog"]=EOStopWin["_eos_modal_dialog"]||[];
function getModalDialogId(){
  return "_eos_modal_dialog"+Math.random()+EOStopWin["_eos_modal_dialog"].length;
}
var getCurrentModalArguments=function(){
  var $=EOStopWin["_eos_modal_dialog"].length;
  if($>0){
    return EOStopWin["_eos_modal_dialog"][$-1];
  }else {
    return null;
  }
},getModalDialog=function($){
  var _=EOStopWin["_eos_modal_dialog"].length;
  for(var A=0;A<_;A++){
    var B=EOStopWin["_eos_modal_dialog"][A];
    if(B.id==$){
      return B;
    }
  }
  return null;
},lockLatestModelDialog=function(){
  var $=getCurrentModalArguments();
  if($){
    $.showMask();
    hideSelect($.win);
  }else {
    maskTop();
  }
},unLockLatestModelDialog=function(){
  var $=getCurrentModalArguments();
  if($){
    $.hideMask();
    showSelect($.win);
  }else {
    unMaskTop();
  }
},hideModelDialog=function(_,B){
  try{
    var $=getModalDialog(_);
    EOStopWin["_eos_modal_dialog"].pop();
    if(!$){
      return ;
    }
    var D=$.frameWin||$.iframe.contentWindow;
    top.currStack=D.bfCurrStack||[];
    try{
      var C=D["returnValue"];
    }
    catch(A){
    }
    if($.container){
      $.container.style.display="none";
    }
    if($.win){
      unLockLatestModelDialog();
    }
  }
  catch(A){
  }
  if(!B){
    try{
      if($["callBack"]){
        $["callBack"](C);
      }
    }
    catch(A){
    }
  }
  try{
    $.iframe.src="";
    $.ddDiv.style.display="none";
    ReleaseElemEvents($.container);
    ReleaseElemEvents($.ddDiv);
    $.ddDiv.parentNode.removeChild($.ddDiv);
    $.container.parentNode.removeChild($.container);
  }
  catch(A){
  }
},resizeModelDialog=function(A,E,$){
  var _=getModalDialog(A);
  _.container.style.width=E+"px";
  _.container.style.height=$+"px";
  _.maskDiv.style.width=E+"px";
  _.maskDiv.style.height=$+"px";
  var D=$id(_.id+"__model_dialog_body",_.win.document);
  if($-27>0){
    D.style.height=$-27-7+"px";
  }
  var C=$id(_.id+"__model_dialog_frame",_.win.document);
  if(C){
    if($-28>=0){
      C.style.height=$-32+"px";
    }
    C.style.width=E-14+"px";
  }
  try{
    _.ddDiv.style.width=E+"px";
    _.ddDiv.style.height=$+"px";
  }
  catch(B){
  }
},moveModelDialog=function($,B,A){
  var C=getModalDialog($);
  C.container.style.left=B+"px";
  C.container.style.top=A+"px";
  try{
    C.ddDiv.style.left=B+"px";
    C.ddDiv.style.top=A+"px";
  }
  catch(_){
  }
},moveModelDialogToCenter=function(_){
  var $=getModalDialog(_);
  $.container.style.top=((EOStopWin.document.body.clientHeight-$.container.offsetHeight)/2+EOStopWin.document.body.scrollTop)+"px";
  $.container.style.left=((EOStopWin.document.body.clientWidth-$.container.offsetWidth)/2+EOStopWin.document.body.scrollLeft)+"px";
};
function ModalArguments($){
  this.id=$;
  this.win=null;
  this.parentWin=null;
  this.callBack=null;
  this.Argument=null;
  this.iframe=null;
  this.container=null;
  this.mask=null;
  this.frameWin=null;
  this.maskDiv=null;
  this.ddDiv=null;
}
ModalArguments.prototype.init=function(){
};
ModalArguments.prototype.showMask=function(){
  this.maskDiv.style.display="";
};
ModalArguments.prototype.hideMask=function(){
  this.maskDiv.style.display="none";
};
function showModalCenter(F,A,C,_,B,D){
  var $=null,E=null;
  return showModal(F,A,C,_,B,$,E,D);
}
function showModal(B,$,I,_,F,L,K,D){
  _=_?parseInt(_,10):300;
  F=F?parseInt(F,10):200;
  _=_+14;
  F=F+14;
  var J=_get_top_window(),C=J.document.getElementsByTagName("BODY")[0];
  if(L==null||L==""){
    L=((J.document.body.clientWidth-_)/2+J.document.body.scrollLeft);
    if(L<0){
      L=0;
    }
  }else {
    L=parseInt(L,10);
  }
  if(K==null||K==""){
    K=((EOStopWin.document.body.clientHeight-F)/2+J.document.body.scrollTop);
    if(K<0){
      K=0;
    }
  }else {
    K=parseInt(K,10);
  }
  var A=J.document.body.scrollTop,G=getModalDialogId(),E=new ModalArguments(G);
  E.parentWin=window;
  E.callBack=I;
  E.Argument=$;
  E.win=J;
  E.init();
  try{
    lockLatestModelDialog();
  }
  catch(H){
  }
  EOStopWin["_eos_modal_dialog"].push(E);
  __showModal(G,B,E,_,F,L,K,D);
  J.document.body.scrollTop=A;
}
function __showModal(X,B,E,I,V,F,Q,D){
  var L=$create("div",E.win.document);
  L.id=X+"__model_dialog_container";
  L.className="eos-popwin";
  L.onfocus=function(){
    L.blur();
  };
  var A="";
  if(isIE){
    A="<iframe style=\"z-index:-1;filter:Alpha(Opacity=0);position:absolute;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>";
  }
  var C=navigator.userAgent.toLowerCase().indexOf("msie 6")!=-1,G="";
  if(C){
    G="style=\"filter:alpha(opacity=50)\"";
  }
  var T=[A,"<div style=\"width:100%;height:100%\">","<div id=\""+X+"__model_dialog_mask\" class=\"eos-div-mask\" style=\"position:absolute;top:0px;left:0px;display:none;\"></div>","<TABLE  cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" height=\"100%\" class=\"eos-dialog\"><TR><TD ",G," class=\"left-top-conner\"><div class=\"ieBlank\"></div></TD><TD  ",G," class=\"top\"></TD><TD  ",G," class=\"right-top-conner\"><div class=\"ieBlank\"></div></TD></TR><TR><TD  ",G," class=\"left\"><div class=\"ieBlank\"></div></TD><TD style=\"background-color: #eaf0f2;\">","<div style=\"width:100%;cursor:move\" id=\""+X+"__model_dialog_head\" class=\"eos-popwin-head\" ><a id=\""+X+"__model_dialog_focus\" href=\"#f\"></a>","<div class=\"eos-popwin-head-icon\">&#160;</div>","<div class=\"eos-popwin-head-title\">"+(D||" Dialog Window")+"</div>","<div id=\"popupControls\" class=\"eos-popwin-head-button\"  >","<a href=\"javascript:void(0);\" id=\""+X+"__model_dialog_closebutton\" onmousedown=\"window.returnValue=undefined;hideModelDialog('"+X+"',true)\">&#160;</a>","</div>","</div>","<div id=\""+X+"__model_dialog_body\" class=\"eos-popwin-body\">","</div>","</TD><TD  ",G," class=\"right\" id=\"",X,"_right\"><div class=\"ieBlank\"></div></TD></TR><TR><TD  ",G," class=\"left-bottom-conner\"></TD><TD  ",G," class=\"bottom\" id=\"",X,"_bottom\"></TD><TD  ",G," class=\"right-bottom-conner\" id=\"",X,"_rightBottom\"></TD></TR></TABLE>","</div>"].join(""),S=["<iframe type=\"_eos_modal_dialog\" eosid=\""+X+"\" name=\""+X+"__model_dialog_frame\" id=\""+X+"__model_dialog_frame\" scrolling=\"auto\" ","  HSPACE =\"0\" VSPACE=\"0\" MARGINHEIGHT=\"0\" MARGINWIDTH=\"0\" "," class=\"eos-popwin-body-iframe\"  border =\"0\"   ","frameborder=\"0\" allowtransparency=\"true\" ></iframe>"].join("\n"),$=getMaxZindex(E.win.document);
  L.style.width=I+"px";
  L.style.height=V+"px";
  L.style.left=F+"px";
  L.style.top=Q+"px";
  L.style.overflow="hidden";
  L.innerHTML=T;
  E.win.document.body.appendChild(L);
  var J=getMaxZindex(E.win.document||document);
  J++;
  L.tabIndex=J;
  L.onmousedown=function(){
    try{
      this.focus();
    }
    catch($){
    }
    eventManager.stopEvent();
  };
  L.onkeypress=function(){
    eventManager.stopEvent();
  };
  L.onkeydown=function(_){
    var $=(eventManager.getEvent(E.win)||_).keyCode;
    if($==27){
      eventManager.stopEvent();
      window.returnValue=undefined;
      hideModelDialog(X);
    }
  };
  var Y=$id(X+"__model_dialog_mask",E.win.document);
  Y.style.width=I+"px";
  Y.style.height=V+"px";
  E.maskDiv=Y;
  Y.style.zIndex=$+2;
  L.style.zIndex=$+1;
  var H=$id(X+"__model_dialog_body",E.win.document),U=H.offsetWidth;
  H.style.height=(V-27)>0?(V-27-7):0+"px";
  H.innerHTML=S;
  var O=$id(X+"__model_dialog_frame",E.win.document);
  if(O){
    O.src=B;
    O.style.height=(V-28)>0?(V-28-7):0+"px";
    O.style.width=U;
    O.style.zIndex=$+1;
  }
  var W=$id(X+"__model_dialog_head",E.win.document);
  W.onmousedown=function(){
    try{
      L.focus();
    }
    catch($){
    }
    eventManager.stopEvent();
  };
  var R=$create("div",E.win.document);
  R.style.position="absolute";
  R.style.width=I+"px";
  R.style.height=V+"px";
  R.style.left=F+"px";
  R.style.top=(Q)+"px";
  R.style.cursor="move";
  R.onmousedown=function(){
    eventManager.stopEvent();
  };
  R.style.zIndex=$;
  E.win.document.body.appendChild(R);
  var N=E.win.fx_DD||fx_DD;
  if(N){
    N(R,{handle:W,limit:{x:[0,E.win.offsetWidth],y:[0,E.win.offsetHeight]},onStart:function(){
        R.style.height=L.style.height;
        R.style.zIndex=(R.style.zIndex*1)+2;
        E.win.document.body.onselectstart=function(){
          return false;
        };
      },onDrag:function(){
        R.style.border="1px dotted #000093";
        L.style.display="none";
      },onComplete:function(){
        this.isdraging=false;
        R.style.height="24px";
        L.style.left=R.style.left;
        L.style.top=R.style.top;
        R.style.zIndex=R.style.zIndex-2;
        R.style.border="";
        L.style.display="";
        E.win.document.body.onselectstart=null;
      }});
  }
  E.iframe=O;
  E.container=L;
  E.ddDiv=R;
  L.style.display="none";
  var P=$id(X+"__model_dialog_focus",E.win.document),M=$id(X+"_right",E.win.document),_=$id(X+"_bottom",E.win.document),K=$id(X+"rightBottom",E.win.document);
  N(L,{handle:_,modifiers:true,onDrag:function(){
      var _=this.mouse.start["y"],$=this.mouse.now["y"];
      if($-_+this.value.now["y"]>=27){
        this.element.style.height=$-_+this.value.now["y"];
      }
      resizeModelDialog(X,parseInt(L.style.width),parseInt(L.style.height));
      E.iframe.style.display="none";
    },onComplete:function(){
      E.iframe.style.display="";
    },onStart:function(){
      this.value.now["y"]=this.element.getStyle("height").toInt();
      E.iframe.style.display="none";
    }});
  N(L,{handle:M,modifiers:true,onDrag:function(){
      var _=this.mouse.start["x"],$=this.mouse.now["x"];
      if($-_+this.value.now["x"]>=150){
        this.element.style.width=$-_+this.value.now["x"];
      }
      resizeModelDialog(X,parseInt(L.style.width),parseInt(L.style.height));
      E.iframe.style.display="none";
    },onComplete:function(){
      E.iframe.style.display="";
    },onStart:function(){
      this.value.now["x"]=this.element.getStyle("width").toInt();
      E.iframe.style.display="none";
    }});
  N(L,{handle:K,modifiers:true,onStart:function(){
      this.value.now["x"]=this.element.getStyle("width").toInt();
      this.value.now["y"]=this.element.getStyle("height").toInt();
      E.iframe.style.display="none";
    },onDrag:function(){
      var _=this.mouse.start["y"],A=this.mouse.now["y"];
      if(A-_+this.value.now["y"]>=27){
        L.style.height=A-_+this.value.now["y"];
      }
      var $=this.mouse.start["x"],B=this.mouse.now["x"];
      if(B-$+this.value.now["x"]>=150){
        L.style.width=B-$+this.value.now["x"];
      }
      resizeModelDialog(X,parseInt(L.style.width),parseInt(L.style.height));
      E.iframe.style.display="none";
    },onComplete:function(){
      E.iframe.style.display="";
    }});
  fx_fadeIn(L,function(){
  },550);
}
function openNewWindow(H,F,G,B,A,_,D,E,$,C){
  _=(_||_=="yes")?"yes":"no";
  D=(D||D=="yes")?"yes":"no";
  E=(E||E=="yes")?"yes":"no";
  $=($||$=="yes")?"yes":"no";
  C=(C||C=="yes")?"yes":"no";
  newwin=window.open(H,"popupnav","width="+F+","+"height="+G+","+"left="+B+","+"top="+A+","+"status="+_+","+"menubar="+D+","+"location="+E+","+"toolbar="+$+","+"scrollbars="+C);
  newwin.focus();
  return newwin;
}
var dialogArguments;
if(window.frameElement){
  var windowType,eosid;
  if(isIE){
    windowType=window.frameElement.type;
    eosid=window.frameElement.eosid;
  }else {
    windowType=window.frameElement.getAttribute("type");
    eosid=window.frameElement.getAttribute("eosid");
  }
  if(windowType=="_eos_modal_dialog"){
    var modal=getModalDialog(eosid);
    window["dialogArguments"]=modal["Argument"];
    window["returnValue"]=null;
    window["parentWindow"]=modal.parentWin;
    window["_eos_dialog_id"]=modal.id;
    modal.frameWin=window;
    initDialogWindowEvent();
    window.bfCurrStack=top.currStack;
    top.currStack=[];
    eventManager.add(window,"unload",function(){
      top.currStack=window.bfCurrStack||[];
    });
  }
}
function initDialogWindowEvent(){
  var $=window.close;
  window.close=function(){
    hideModelDialog(window["_eos_dialog_id"]);
  };
  window.resize=function($,_){
    resizeModelDialog(window["_eos_dialog_id"],$,_);
  };
  var _=window.moveTo;
  window.moveTo=function(_,$){
    moveModelDialog(window["_eos_dialog_id"],_,$);
  };
  window.moveCenter=function(){
    moveModelDialogToCenter(window["_eos_dialog_id"]);
  };
}
function loadParent($){
  if(window.parentWindow){
    if($){
      window.parentWindow.location=$;
    }else {
      window.parentWindow.location=window.parentWindow.location;
    }
  }
}
function hideAllSelect(A){
  if(isIE&&A){
    var $=A.getElementsByTagName("select");
    for(var _=0;_<$.length;_++){
      $[_].back_display=$[_].style.display;
      $[_].style.display="none";
    }
    var B=getFrameDocuments(A);
    for(_=0;_<B.length;_++){
      hideAllSelect(B[_]);
    }
  }
}
function showAllSelect(A){
  if(isIE&&A){
    var $=A.getElementsByTagName("select");
    for(var _=0;_<$.length;_++){
      $[_].style.display=$[_].back_display;
    }
    var B=getFrameDocuments(A);
    for(_=0;_<B.length;_++){
      showAllSelect(B[_]);
    }
  }
}
function getFrameDocuments(A){
  var $=[];
  if(isIE){
    var B=A.frames;
    for(var _=0;_<B.length;_++){
      $.push(B[_].document);
    }
  }else {
    B=A.getElementsByTagName("iframe");
    for(_=0;_<B.length;_++){
      $.push(B[_].contentDocument);
    }
    B=A.getElementsByTagName("frame");
    for(_=0;_<B.length;_++){
      $.push(B[_].contentDocument);
    }
  }
  return $;
}
var _eos_colsed_panel=[],_eos_curr_open_panel=null;
function Panel($){
  this.id=$;
  PageControl.add($,this);
  this.container=null;
  this.title=null;
  this.onExpandFunc=null;
  this.onCloseFunc=null;
  this.status=null;
  this.button=null;
}
Panel.OPEN_STYLE_BUTTON="PANEL_OPEN_BUTTON";
Panel.CLOSE_STYLE_BUTTON="PANEL_CLOSE_BUTTON";
Panel.prototype.init=function(){
  this.container=$id(this.id+"_container");
  this.button=$id(this.id+"_button");
  this.titleObj=$id(this.id+"_title");
  this.table=$id("_"+this.id+"_panel_table");
  this.freshButton();
  var $=this;
  this.button.onclick=function(){
    $.changeStatus();
  };
};
Panel.prototype.setTitle=function($){
  this.title=$;
  this.titleObj.innerHTML=$;
};
Panel.prototype.getTitle=function(){
  return this.title;
};
Panel.prototype.freshButton=function(){
  if(this.status){
    this.button.className=Panel.CLOSE_STYLE_BUTTON;
  }else {
    this.button.className=Panel.OPEN_STYLE_BUTTON;
  }
};
Panel.prototype.changeStatus=function(){
  var $=this.button;
  if($){
    if(!this.status){
      this.open($);
    }else {
      this.close($);
    }
  }
};
Panel.prototype.open=function(obj){
  var table=$id("_"+this.id+"_panel_table");
  if(!table){
    return ;
  }
  _eos_curr_open_panel=this;
  if(this.onExpandFunc){
    try{
      var result=eval(this.onExpandFunc+"();");
      if(result!=false){
        table.rows[1].style.display="";
        if(table.backupHeight!=null){
          table.height=table.backupHeight;
          this.container.style.height=this.height;
        }
        this.status=true;
        this.freshButton();
      }
    }
    catch(e){
    }
  }else {
    table.rows[1].style.display="";
    if(table.backupHeight!=null){
      table.height=table.backupHeight;
      this.container.style.height=this.height;
    }
    this.status=true;
    this.freshButton();
    if(this.needResize===true){
      _eos_colsed_panel.pop(this);
      for(var i=0;i<_registryEvent.length;i++){
        var e=_registryEvent[i];
        if(e.obj==window&&e.type=="resize"){
          e.fn();
        }
      }
      this.needResize=false;
    }
  }
  _eos_curr_open_panel=null;
};
Panel.prototype.close=function(obj){
  var table=$id("_"+this.id+"_panel_table");
  if(!table){
    return ;
  }
  if(this.onCloseFunc){
    try{
      _eos_colsed_panel.push(this);
      var result=eval(this.onCloseFunc+"();");
      if(result!=false){
        table.rows[1].style.display="none";
        table.backupHeight=table.height;
        table.height="20px";
        this.height=this.container.style.height;
        this.container.style.height="20px";
        this.status=false;
        this.freshButton();
      }
    }
    catch(e){
    }
  }else {
    _eos_colsed_panel.push(this);
    table.rows[1].style.display="none";
    table.backupHeight=table.height;
    table.height="20px";
    this.height=this.container.style.height;
    this.container.style.height="20px";
    this.status=false;
    this.freshButton();
  }
};
Panel.prototype.collapse=Panel.prototype.close;
Panel.prototype.expand=Panel.prototype.open;
function RichTextEditor($){
  this.id=$;
  this.name="";
  this.value="";
  this.width="100%";
  this.height=200;
  this.value="";
  this.BasePath=contextPath+"/common/fckeditor/";
  this.hiddenInput=null;
  this.toolbarSet="Default";
  this.container=null;
  this.richEditor=null;
  PageControl.add($,this);
}
RichTextEditor.prototype.init=function(){
  var $=new FCKeditor(this.id+"_textarea");
  $.BasePath=this.BasePath;
  this.container=$id(this.id+"_container");
  if(isNaN(parseInt(this.width))){
    this.width="100%";
  }
  if(isNaN(parseInt(this.height))){
    this.height=200;
  }
  $.Height=this.height;
  $.Width=this.width;
  $.Value=this.value;
  $.ToolbarSet=this.toolbarSet;
  $.ReplaceTextarea();
  this.richEditor=$;
};
RichTextEditor.prototype.getValue=function(){
  var _=this.getFCKEditor(),$=_.GetHTML();
  if($&&this.value){
    if($=="<p>"+this.value+"</p>"){
      return this.value;
    }
  }
  if($==""||$=="<p></p>"){
    return this.value;
  }
  return $;
};
RichTextEditor.prototype.getFCKEditor=function(){
  var $=FCKeditorAPI.GetInstance(this.id+"_textarea");
  return $;
};
RichTextEditor.prototype.setValue=function($){
  if($!=null&&$!=undefined){
    var _=this.getFCKEditor();
    _.SetHTML($);
  }else {
    _.SetHTML("");
  }
  this.value=$;
};
RichTextEditor.prototype.setFocus=function(){
};
RichTextEditor.prototype.lostFocus=function(){
};
RichTextEditor.prototype.showEditor=function(){
  this.container.style.display="";
};
RichTextEditor.prototype.hideEditor=function(){
  this.container.style.display="none";
};
RichTextEditor.prototype.hide=function(){
  this.hideEditor();
};
RichTextEditor.prototype.validate=function(){
  return true;
};
RichTextEditor.prototype.isFocus=function(){
  return false;
};
RichTextEditor.prototype.getDisplayValue=function($){
  return $;
};
RichTextEditor.prototype.setPosition=function($,A,B,C){
  this.container.style.position="absolute";
  this.container.style.top="0px";
  this.container.style.left="0px";
  var _=getMaxZindex(document);
  if(this.container.style.zIndex!=_){
    this.container.style.zIndex=_;
  }
  setElementXY(this.container,[$,A]);
};
function DictRadioGroup($){
  this.id=$;
  this.value=null;
  this.container=$id(this.id+"_container");
  this.radioes=this.container.getElementsByTagName("input");
  this.container.onmousedown=function(){
    eventManager.stopPropagation();
  };
  this.hiddenInput=new Object();
  this.jsonObj=null;
  this.initEvent();
  PageControl.add($,this);
}
DictRadioGroup.prototype.init=function(){
};
DictRadioGroup.prototype.initEvent=function(){
  var B=this;
  function $(){
    B.refreshValue();
  }
  for(var _=0;_<this.radioes.length;_++){
    var A=this.radioes[_];
    eventManager.add(A,"click",$);
  }
  eventManager.add(this.container,"keypress",$);
};
DictRadioGroup.prototype.refreshInput=function(){
  str=this.hiddenInput.value;
  for(var $=0;$<this.radioes.length;$++){
    var _=this.radioes[$];
    if(_.value==str){
      _.checked=true;
    }else {
      _.checked=false;
    }
  }
};
DictRadioGroup.prototype.refreshValue=function(){
  var A;
  for(var $=0;$<this.radioes.length;$++){
    var _=this.radioes[$];
    if(_.checked){
      A=_.value;
      break ;
    }
  }
  this.hiddenInput.value=A;
};
DictRadioGroup.prototype.setValue=function($){
  this.hiddenInput.value=$;
  this.refreshInput();
};
DictRadioGroup.prototype.getValue=function(){
  this.refreshValue();
  return this.hiddenInput.value;
};
DictRadioGroup.prototype.setFocus=function(){
  var _=false;
  for(var $=0;$<this.radioes.length;$++){
    var A=this.radioes[$];
    if(A.checked==true){
      A.focus();
      _=true;
      break ;
    }
    if(!_){
      this.radioes[0].focus();
    }
  }
};
DictRadioGroup.prototype.lostFocus=function(){
};
DictRadioGroup.prototype.showEditor=function(){
  var $=getMaxZindex();
  this.container.style.zIndex=$;
  this.container.style.display="";
  addClass(this.container.firstChild,"dict_comp");
  if(this.isDcEdit==true){
    var _=this.container.firstChild;
    _.style.width=_.firstChild.offsetWidth;
    initShadow(_);
  }
  this.setFocus();
};
DictRadioGroup.prototype.hideEditor=function(){
  this.container.style.display="none";
};
DictRadioGroup.prototype.setPosition=function($,A,_,B){
  this.container.style.position="absolute";
  this.container.zIndex=9999;
  this.container.style.left=$+"px";
  this.container.style.top=A+"px";
};
DictRadioGroup.prototype.isFocus=function(){
  return true;
};
DictRadioGroup.prototype.validate=function(){
  return true;
};
DictRadioGroup.prototype.getDisplayValue=function($){
  if($==null){
    $=this.getValue();
  }
  if(!this.jsonObj){
    return $;
  }
  var _=this.jsonObj[$];
  return _!==undefined&&_!==null?_:$;
};
function CheckGroup($){
  this.id=$;
  PageControl.add($,this);
  this.subCheck=new Array();
  this.selectCheck=new Array();
}
CheckGroup.prototype.getRows=function(){
  return this.subCheck;
};
CheckGroup.prototype.init=function(){
  for(var $=0;$<this.subCheck.length;$++){
    var _=this.subCheck[$];
    _.inited=true;
    if(_.isChecked){
      _.setSelected();
    }else {
      _.disSelected();
    }
  }
  this.afterInit();
};
CheckGroup.prototype.getSelectLength=function(){
  var _=0;
  for(var $=0;$<this.subCheck.length;$++){
    if(this.subCheck[$].getStatus()){
      _++;
    }
  }
  return _;
};
CheckGroup.prototype.getIndex=function(_){
  var A=-1;
  for(var $=0;$<this.subCheck.length;$++){
    if(this.subCheck[$]==_){
      A=$;
      break ;
    }
  }
  return A;
};
CheckGroup.prototype.getLength=function(){
  return this.subCheck.length;
};
CheckGroup.prototype.getSelectRows=function(){
  var _=[];
  for(var $=0;$<this.subCheck.length;$++){
    if(this.subCheck[$].getStatus()){
      _.push(this.subCheck[$]);
    }
  }
  return _;
};
CheckGroup.prototype.getSelectParams=function($){
  var _=this.getSelectRows(),C=[];
  for(var A=0;A<_.length;A++){
    var B=_[A].getParam($);
    C.push(B);
  }
  return C;
};
CheckGroup.prototype.selectAll=function(){
  for(var $=0;$<this.subCheck.length;$++){
    var _=this.subCheck[$];
    _.setSelected();
  }
};
CheckGroup.prototype.selectReverse=function(){
  for(var $=0;$<this.subCheck.length;$++){
    var _=this.subCheck[$];
    _.reverseSelect();
  }
};
CheckGroup.prototype.disSelectAll=function(){
  for(var $=0;$<this.subCheck.length;$++){
    var _=this.subCheck[$];
    _.disSelected();
  }
};
CheckGroup.prototype.get=function($){
  return this.subCheck[$];
};
CheckGroup.prototype.register=function($){
  this.subCheck.push($);
};
CheckGroup.prototype.afterInit=function(){
};
function rowCheckBox($){
  this.id=$;
  this.isChecked=false;
  PageControl.add($,this);
  this.params=[];
  this.checkbox=null;
  this.container=null;
  this.subboxes=new Array();
  this.row=null;
  this.selectStyle="";
  this.rowSelect=true;
  this.rowEvent="click";
  this.onSelectFunc=null;
  this.onUnSelectFunc=null;
  this.groupid=null;
  this.tagName="tr";
  this.showCheckBox=true;
  this.inited=false;
  this.backClassName=null;
  this.beforeSelectFunc=null;
  this.afterSelectFunc=null;
  this.beforeUnSelectFunc=null;
  this.afterUnSelectFunc=null;
}
rowCheckBox.prototype.init=function(){
  this.container=$id(this.id+"_container");
  this.checkbox=$createElement("input",{id:this.id+"_checkbox",type:"checkbox"});
  this.container.appendChild(this.checkbox);
  for(var C=0;C<this.params.length;C++){
    var E=$createElement("input",{name:this.params[C].name,value:this.params[C].value,type:"checkbox",style:{display:"none"}});
    this.container.appendChild(E);
  }
  if(this.showCheckBox){
    this.checkbox.style.display="";
  }else {
    this.checkbox.style.display="none";
  }
  var B=this.container.getElementsByTagName("input");
  if(B){
    for(C=0;C<B.length;C++){
      var A=B[C];
      if(A.id!=this.id){
        this.subboxes.push(A);
        A.checked=this.value;
      }
    }
  }
  var _=this;
  this.checkbox.onclick=function(){
    setTimeout($,1);
    eventManager.stopPropagation();
    return false;
  };
  function $(){
    _.reverseSelect();
  }
  if(this.rowSelect){
    this.row=getRow(this.container,this.tagName);
    this.backClassName=this.row.className;
    eventManager.add(this.row,this.rowEvent,$);
  }
  var D=PageControl.getOne(this.groupid);
  D.register(this);
};
rowCheckBox.prototype.changeStatus=function(){
  for(var $=0;$<this.subboxes.length;$++){
    var _=this.subboxes[$];
    _.checked=this.value;
  }
  if(this.row){
    if(this.value){
      this.row.className=this.selectStyle;
    }else {
      this.row.className=this.backClassName;
    }
  }
};
rowCheckBox.prototype.getStatus=function(){
  return this.value;
};
rowCheckBox.prototype.getIndex=function(){
  return this.group.getIndex(this);
};
rowCheckBox.prototype.getParam=function(A){
  for(var $=0;$<this.subboxes.length;$++){
    var _=this.subboxes[$];
    if(_.name.replace(/\[\d*\]/,"[*]")==A||_.name.replace(/\[\d*\]/,"")==A){
      return _.value;
    }
  }
  return null;
};
rowCheckBox.prototype.reverseSelect=function(){
  if(this.value){
    this.disSelected();
  }else {
    this.setSelected();
  }
};
rowCheckBox.prototype.setSelected=function(){
  if(this.beforeSelectFunc&&this.inited){
    if(eval(this.beforeSelectFunc+"(this)")===false){
      return ;
    }
  }
  this.checkbox.checked=true;
  this.value=true;
  this.changeStatus();
  if(this.onSelectFunc&&this.inited){
    if(typeof (this.onSelectFunc)=="string"){
      eval(this.onSelectFunc+"(this)");
    }else {
      this.onSelectFunc(this);
    }
  }
  if(this.afterSelectFunc&&this.inited){
    eval(this.afterSelectFunc+"(this)");
  }
};
rowCheckBox.prototype.disSelected=function(){
  if(this.beforeUnSelectFunc&&this.inited){
    if(eval(this.beforeUnSelectFunc+"(this)")===false){
      return ;
    }
  }
  if(this.onUnSelectFunc&&this.inited){
    if(typeof (this.onUnSelectFunc)=="string"){
      eval(this.onUnSelectFunc+"(this)");
    }else {
      this.onUnSelectFunc(this);
    }
  }
  this.checkbox.removeAttribute("checked");
  this.value=false;
  this.changeStatus();
  if(this.afterUnSelectFunc&&this.inited){
    eval(this.afterUnSelectFunc+"(this)");
  }
};
function getRow(_,A){
  A=A?A.toUpperCase():"TR";
  var $=_;
  while($){
    if($.tagName.toUpperCase()==A){
      return $;
    }
    $=$.parentNode;
  }
}
function selectAll(_){
  var $=PageControl.getOne(_);
  $.selectAll();
}
function selectOther(_){
  var $=PageControl.getOne(_);
  $.selectReverse();
}
function selectReverse($){
  selectOther($);
}
function selectNone(_){
  var $=PageControl.getOne(_);
  $.disSelectAll();
}
function disSelectAll($){
  selectNone($);
}
function controlCheckGroup($,_){
  if($.checked){
    selectAll(_);
  }else {
    selectNone(_);
  }
}
function RadioGroup($){
  this.id=$;
  PageControl.add($,this);
  this.subRadio=new Array();
  this.currRadio=null;
  this.defaultRow=null;
}
RadioGroup.prototype.getRows=function(){
  return this.subRadio;
};
RadioGroup.prototype.init=function(){
  for(var $=0;$<this.subRadio.length;$++){
    if(this.subRadio[$].isChecked){
      this.subRadio[$].reverseSelect();
    }else {
      this.subRadio[$].disSelected();
    }
    this.subRadio[$].inited=true;
  }
  this.afterInit();
};
RadioGroup.prototype.getParam=function(_){
  var $=this.getSelectRow();
  if($){
    return $.getParam(_);
  }
  return null;
};
RadioGroup.prototype.getIndex=function(_){
  var A=-1;
  for(var $=0;$<this.subRadio.length;$++){
    if(this.subRadio[$]==_){
      return $;
    }
  }
  return A;
};
RadioGroup.prototype.getSelectRow=function(){
  return this.currRadio;
};
RadioGroup.prototype.get=function($){
  return this.subRadio[$];
};
RadioGroup.prototype.getLength=function(){
  return this.subRadio.length;
};
RadioGroup.prototype.getSelectLength=function(){
  if(this.currRadio){
    return 1;
  }else {
    return 0;
  }
};
RadioGroup.prototype.register=function($){
  this.subRadio.push($);
};
RadioGroup.prototype.refresh=function(jsRadio){
  if(this.currRadio!=jsRadio){
    if(jsRadio.setSelected()!==false){
      if(this.currRadio){
        this.currRadio.disSelected();
      }
      this.currRadio=jsRadio;
      if(jsRadio.afterSelectFunc&&jsRadio.inited){
        eval(jsRadio.afterSelectFunc+"(this)");
      }
    }
  }
};
RadioGroup.prototype.afterInit=function(){
};
function rowRadio($){
  this.id=$;
  this.isChecked=false;
  PageControl.add($,this);
  this.params=[];
  this.radio=null;
  this.container=null;
  this.subboxes=new Array();
  this.row=null;
  this.selectStyle="";
  this.rowSelect=true;
  this.rowEvent="click";
  this.onSelectFunc=null;
  this.onUnSelectFunc=null;
  this.groupid=null;
  this.group=null;
  this.tagName="tr";
  this.showRadio=true;
  this.inited=false;
  this.backClassName=null;
  this.beforeSelectFunc=null;
  this.afterSelectFunc=null;
}
rowRadio.prototype.init=function(){
  this.container=$id(this.id+"_container");
  this.radio=$createElement("input",{id:this.id+"_radio",type:"radio"});
  this.container.appendChild(this.radio);
  for(var C=0;C<this.params.length;C++){
    var E=$createElement("input",{name:this.params[C].name,value:this.params[C].value,type:"checkbox",style:{display:"none"}});
    this.container.appendChild(E);
  }
  if(this.showRadio){
    this.radio.style.display="";
  }else {
    this.radio.style.display="none";
  }
  var B=this.container.getElementsByTagName("input");
  if(B){
    for(C=0;C<B.length;C++){
      var A=B[C];
      if(A.id!=this.id){
        this.subboxes.push(A);
        A.checked=this.value;
      }
    }
  }
  var _=this;
  this.radio.onclick=function(){
    eventManager.stopPropagation();
    setTimeout($,1);
    return false;
  };
  function $(){
    _.reverseSelect();
  }
  if(this.rowSelect){
    this.row=getRow(this.container,this.tagName);
    this.backClassName=this.row.className;
    eventManager.add(this.row,this.rowEvent,$);
  }
  var D=$o(this.groupid);
  D.register(this);
  this.group=D;
};
rowRadio.prototype.changeStatus=function(){
  for(var $=0;$<this.subboxes.length;$++){
    var _=this.subboxes[$];
    _.checked=this.value;
  }
  if(this.row){
    if(this.value){
      this.row.className=this.selectStyle;
    }else {
      this.row.className=this.backClassName;
    }
  }
};
rowRadio.prototype.getStatus=function(){
  return this.value;
};
rowRadio.prototype.getParam=function(A){
  for(var $=0;$<this.subboxes.length;$++){
    var _=this.subboxes[$];
    if(_.name.replace(/\[\d*\]/,"[*]")==A||_.name.replace(/\[\d*\]/,"")==A){
      return _.value;
    }
  }
  return null;
};
rowRadio.prototype.reverseSelect=function(){
  this.group.refresh(this);
};
rowRadio.prototype.getIndex=function(){
  return this.group.getIndex(this);
};
rowRadio.prototype.setSelected=function(){
  if(this.beforeSelectFunc&&this.inited){
    if(eval(this.beforeSelectFunc+"(this)")===false){
      return false;
    }
  }
  this.radio.checked=true;
  this.value=true;
  this.changeStatus();
  if(this.onSelectFunc&&this.inited){
    if(typeof (this.onSelectFunc)=="string"){
      eval(this.onSelectFunc+"(this)");
    }else {
      this.onSelectFunc(this);
    }
  }
};
rowRadio.prototype.disSelected=function(){
  if(this.onUnSelectFunc&&this.inited){
    if(typeof (this.onUnSelectFunc)=="string"){
      eval(this.onUnSelectFunc+"(this)");
    }else {
      this.onUnSelectFunc(this);
    }
  }
  this.radio.removeAttribute("checked");
  this.value=false;
  this.changeStatus();
};
function Entity($){
  if(!$){
    $="entity";
  }
  this.status=Entity.STATUS_INIT;
  this.name=$;
  this.values={};
  this.parent=new Dataset($);
  this.keys=[];
  this.__keys={};
  this.__type=null;
  this.id=null;
}
Entity.STATUS_NEW=0;
Entity.STATUS_INIT=1;
Entity.STATUS_MODIFIED=2;
Entity.STATUS_DELT=3;
Entity.STATUS_HIDDEN=4;
Entity.XPATH_LEVEL=2;
Entity.LINK_SIGN="__collection";
Entity.prototype.getPropertyByFieldName=function($){
  return this.values[$];
};
Entity.prototype.setPropertyByFieldName=function(A,$){
  var _=false;
  _=this.setValue(A,$);
  if(_){
    this.update();
  }
};
Entity.prototype.setPropertyByXpath=function(B,A){
  var C=(B+"").split("/"),_=C[C.length-1];
  if(C.length==1){
    this.setPropertyByFieldName(_,A);
    return ;
  }
  C=C.slice(0,C.length-1);
  B=C.join("/");
  var $=this.getPropertyByXpath(B);
  if($ instanceof Entity){
    $.setPropertyByFieldName(_,A);
  }else {
    if($!==undefined&&$!==null){
      oldValue=$[_];
      $[_]=A;
    }else {
      $=this.createEntityTree(B);
      $.setPropertyByFieldName(_,A);
    }
  }
};
Entity.prototype.createEntityTree=function(D){
  var $=(D+"").split("/"),A=this,C;
  for(var B=0;B<$.length;B++){
    var _=$[B];
    C=A.getPropertyByFieldName(_);
    if(C===null||C===undefined){
      C=new Entity(_);
      A.setPropertyByFieldName(_,C);
    }
    A=C;
  }
  return C;
};
Entity.prototype.getPropertyByXpath=function(C){
  var $=(C+"").split("/"),B=this;
  for(var A=0;A<$.length;A++){
    var _=$[A];
    if(B instanceof Entity){
      B=B.getPropertyByFieldName(_);
    }else {
      B=B[_];
    }
    if(B===null||B===undefined){
      break ;
    }
  }
  return B;
};
function deepEntity($){
  Entity.prototype.getProperty=Entity.prototype.getPropertyByFieldName;
  Entity.prototype.setProperty=Entity.prototype.setPropertyByFieldName;
  if($){
    Entity.prototype.getProperty=Entity.prototype.getPropertyByXpath;
    Entity.prototype.setProperty=Entity.prototype.setPropertyByXpath;
  }
}
deepEntity(false);
Entity.prototype.clone=function($){
  var _=new Entity(this.name);
  _.__type=this.__type;
  for(var B=0;B<this.keys.length;B++){
    var A=this.keys[B],C=this.getProperty(A);
    if(C instanceof Dataset||C instanceof Entity){
      _.setProperty(A,C.clone($));
    }else {
      _.setValue(A,C);
    }
  }
  return _;
};
Entity.prototype.toXMLString=function(E){
  E=E!==false;
  var J=new StringBuffer(),A="",I=this.name;
  if(E){
    if(this.name&&this.name.indexOf("/")>0){
      A=this.name.split("/")[0];
      I=this.name.split("/")[1];
      J.append("<").append(A).append(">\n");
    }
    J.append("<").append(I);
    if(this.__type){
      J.append(" __type=\"").append(this.__type).append("\"");
    }
    if(this.id){
    }
    J.append(">\n");
  }
  var C=this.getKeys(),$={};
  for(var H=0;H<C.length;H++){
    var K=this.getProperty(C[H]),F=K instanceof Dataset,D=K instanceof Entity;
    if(K===null||K===undefined||K===""){
      if(C[H].indexOf("/")>0){
        var _=C[H].split("/");
        if(!$[_[0]]){
          $[_[0]]=[];
        }
        var B="<"+_[1]+" __isNullOrEmpty=\"null\"/>\n";
        $[_[0]].push(B);
      }else {
        J.append("<").append(C[H]).append(" __isNullOrEmpty=\"null\"/>\n");
      }
    }else {
      if(!F&&!D){
        if(C[H].indexOf("/")>0){
          _=C[H].split("/");
          if(!$[_[0]]){
            $[_[0]]=[];
          }
          B="<"+_[1]+">"+xmlConversion(K+"")+"</"+_[1]+">\n";
          $[_[0]].push(B);
        }else {
          J.append("<").append(C[H]).append(">");
          if(K||K===0){
            J.append(xmlConversion(K.toString()));
          }
          J.append("</").append(C[H]).append(">\n");
        }
      }else {
        J.append(K+"");
      }
    }
  }
  for(var G in $){
    if($.hasOwnProperty(G)){
      J.append("<").append(G).append(">");
      J.append($[G].join(""));
      J.append("</").append(G).append(">\n");
    }
  }
  if(E){
    J.append("</").append(I).append(">\n");
    if(A){
      J.append("</").append(A).append(">\n");
    }
  }
  return J.toString();
};
Entity.prototype.toString=Entity.prototype.toXMLString;
Entity.prototype.getKeys=function(){
  return this.keys;
};
Entity.prototype.xpathParse=function(A){
  A+="";
  var $=A.indexOf("/");
  if($<0){
    return A;
  }else {
    var _=A.split("/");
    if(_.length!=2){
      $error(" \u93c6\u509b\u6902\u6d93\u5d86\u656e\u93b8\u4f79\u6d3f\u6fb6\u6c31\u9a87\u9428\u524epath ");
    }
    return _;
  }
};
Entity.prototype.setValue=function($,_){
  var A=false;
  if(!this.values[$]){
    if(!this.__keys[$]){
      this.keys.push($);
      this.__keys[$]=true;
    }
    A=true;
  }else {
    if(this.values[$]!=_){
      A=true;
    }
  }
  this.values[$]=_;
  return A;
};
Entity.prototype.getValue=function($){
  return this.values[$];
};
Entity.prototype.update=function(){
  if(this.parent){
    if(this.parent instanceof Dataset){
      this.parent.onUpdateEntity(this);
    }
    this.parent.update();
  }
  this.__onUpdate();
  this.onUpdate();
};
Entity.prototype.onUpdate=function(){
};
Entity.prototype.__onUpdate=function(){
};
function Dataset($){
  this.values=[];
  this.entityType=$;
}
Dataset.prototype.addEntity=function($,_){
  this.values.push($);
  $.parent=this;
  if(_!==false){
    $.status=Entity.STATUS_NEW;
  }
  this.onAddEntity($);
  this.update();
};
Dataset.prototype.addBlankEntity=function(){
  var $=new Entity(this.entityType);
  if(this.getLength()!=0){
    $=this.get(0).clone();
  }
  this.values.push($);
  $.parent=this;
  $.status=Entity.STATUS_NEW;
  this.onAddEntity($);
  this.update();
  return $;
};
Dataset.prototype.removeEntity=function(B,A){
  if(this.onDeleteEntity(B)){
    if(A===true){
      for(var _=0;_<this.values.length;_++){
        var $=this.values[_];
        if($===B){
          this.values.splice(_,1);
          break ;
        }
      }
    }else {
      B.status=Entity.STATUS_DELT;
    }
    this.update();
  }
};
Dataset.prototype.getAlltEntities=function(){
  var A=[];
  for(var $=0;$<this.values.length;$++){
    var _=this.values[$];
    A.push(_);
  }
  return A;
};
Dataset.prototype.setValue=function(A,$,_){
  A.setValue($,_);
};
Dataset.prototype.getModifiedEntities=function(){
  var A=[];
  for(var $=0;$<this.values.length;$++){
    var _=this.values[$];
    if(_.status==Entity.STATUS_MODIFIED){
      A.push(_);
    }
  }
  return A;
};
Dataset.prototype.getInsertEntities=function(){
  var A=[];
  for(var $=0;$<this.values.length;$++){
    var _=this.values[$];
    if(_.status==Entity.STATUS_NEW){
      A.push(_);
    }
  }
  return A;
};
Dataset.prototype.getRemovedEntities=function(){
  var A=[];
  for(var $=0;$<this.values.length;$++){
    var _=this.values[$];
    if(_.status==Entity.STATUS_DELT){
      A.push(_);
    }
  }
  return A;
};
Dataset.prototype.filter=function(A,C){
  var _=[];
  for(var B=0;B<this.values.length;B++){
    var $=this.values[B];
    if($.getProperty(A)==C){
      _.push($);
    }
  }
  return _;
};
Dataset.prototype.findEntity=function($,A){
  for(var _=0;_<this.values.length;_++){
    var B=this.values[_];
    if(B.getProperty($)==A){
      return B;
    }
  }
  return null;
};
Dataset.prototype.toString=function(){
  var _=new StringBuffer();
  for(var $=0;$<this.values.length;$++){
    var A=this.values[$];
    _.append(A.toString());
  }
  return _.toString();
};
Dataset.prototype.clear=function(){
  this.values=[];
  this.update();
};
Dataset.prototype.appendDataset=function(_){
  for(var $=0;$<_.getLength();$++){
    var A=_.get($).clone(true);
    this.addEntity(A);
    A.status=Entity.STATUS_INIT;
  }
  this.update();
};
Dataset.prototype.init=function($){
  if($.length==1){
    var _=$[0].getAttribute("__isNullOrEmpty");
    if(_=="empty"||_=="null"){
      return ;
    }
  }
  for(var A=0;A<$.length;A++){
    var B=$[A];
    if(!B.tagName){
      continue ;
    }
    var C=Dataset.initEntity(B);
    C.id=B.getAttribute(Entity.LINK_SIGN)?B.getAttribute(Entity.LINK_SIGN):null;
    this.onFillEntity(C);
    this.addEntity(C);
    C.status=Entity.STATUS_INIT;
  }
};
Dataset.initEntity=function(_){
  if(!_){
    return null;
  }
  var B=new Entity(_.tagName);
  B.__type=_.getAttribute("__type");
  var D=_.childNodes,$=[];
  $["type"]=null;
  for(var A=0;A<D.length;A++){
    var C=D[A];
    if(C.nodeType==1){
      if(!C.getAttribute(Entity.LINK_SIGN)){
        Dataset.initField(B,C);
      }else {
        if($["type"]==C.tagName){
          $.push(C);
        }else {
          if($["type"]!=null){
            var E=new Dataset($["type"]);
            E.init($);
            B.setProperty($["type"],E);
          }
          $=[];
          $["type"]=C.tagName;
          $.push(C);
        }
      }
    }
  }
  if($["type"]!=null){
    E=new Dataset($["type"]);
    E.init($);
    B.setProperty($["type"],E);
  }
  $=[];
  return B;
};
Dataset.initField=function($,C){
  if(C.childNodes.length>1){
    for(var B=0;B<C.childNodes.length;B++){
      var A=C.childNodes[B];
      if(A.nodeType==1){
        var _=C.tagName+"/"+A.tagName;
        $.setProperty(_,getNodeValue(A));
      }
    }
  }else {
    $.setProperty(C.tagName,getNodeValue(C));
  }
};
Dataset.prototype.slice=function(B,C){
  var $=new Dataset(this.entityType);
  if(!C){
    C=this.getLength();
  }
  if(C>this.getLength()){
    C=this.getLength();
  }
  for(var A=B;A<C;A++){
    var _=this.get(A).clone();
    $.addEntity(_);
  }
  return $;
};
Dataset.prototype.update=function(){
  this.onUpdate();
};
Dataset.prototype.getEntities=function(){
  return this.values;
};
Dataset.prototype.getLength=function(){
  return this.getEntities().length;
};
Dataset.prototype.get=function($){
  return this.values[$];
};
Dataset.prototype.onFillEntity=function($){
};
Dataset.prototype.onUpdateEntity=function($){
};
Dataset.prototype.onAddEntity=function($){
};
Dataset.prototype.onDeleteEntity=function($){
  return true;
};
Dataset.prototype.onUpdate=function(){
};
Dataset.prototype.clone=function($){
  var A=new Dataset(this.entityType);
  if(!$){
    return A;
  }else {
    for(var _=0;_<this.values.length;_++){
      var B=this.values[_].clone($);
      A.values.push(B);
    }
    return A;
  }
};
Dataset.prototype.getInsertDataset=function(){
  var _=new Dataset(this.entityType),A=this.getInsertEntities();
  for(var $=0;$<A.length;$++){
    _.addEntity(A[$].clone(true));
  }
  return _;
};
Dataset.prototype.getRemovedDataset=function(){
  var _=new Dataset(this.entityType),A=this.getRemovedEntities();
  for(var $=0;$<A.length;$++){
    _.addEntity(A[$].clone(true));
  }
  return _;
};
Dataset.prototype.getModifiedDataset=function(){
  var _=new Dataset(this.entityType),A=this.getModifiedEntities();
  for(var $=0;$<A.length;$++){
    _.addEntity(A[$].clone(true));
  }
  return _;
};
Dataset.prototype.getSubmitXML=function(B){
  var C=B.getSubmitXpath();
  if(C){
    for(var $=0;$<this.values.length;$++){
      var _=this.values[$];
      _.name=C;
    }
  }
  _=new StringBuffer();
  if(B.xpath=="map"){
    var A=this.getInsertEntities();
    if(A.length>0){
      for($=0;$<A.length;$++){
        A[$].name="insertEntities";
        _.append(A[$].toString());
      }
    }
    A=this.getModifiedEntities();
    if(A.length>0){
      for($=0;$<A.length;$++){
        A[$].name="updateEntities";
        _.append(A[$].toString());
      }
    }
    A=this.getRemovedEntities();
    if(A.length>0){
      for($=0;$<A.length;$++){
        A[$].name="deleteEntities";
        _.append(A[$].toString());
      }
    }
    _.append("<xpath>map</xpath>");
  }else {
    A=this.getInsertEntities();
    _.append("<entry><string>insertEntities</string><list>");
    if(A.length>0){
      for($=0;$<A.length;$++){
        _.append(A[$].toString());
      }
    }
    _.append("</list></entry>");
    A=this.getModifiedEntities();
    _.append("<entry><string>updateEntities</string><list>");
    if(A.length>0){
      for($=0;$<A.length;$++){
        _.append(A[$].toString());
      }
    }
    _.append("</list></entry>");
    A=this.getRemovedEntities();
    _.append("<entry><string>deleteEntities</string><list>");
    if(A.length>0){
      for($=0;$<A.length;$++){
        _.append(A[$].toString());
      }
    }
    _.append("</list></entry>");
  }
  return _.toString();
};
Dataset.create=function(A,_,B){
  var $=null;
  $debug("type of xml"+typeof (A));
  if(typeof (A)=="string"){
    $=createXmlDom();
    $.loadXML(A);
  }else {
    if(typeof (A)=="object"){
      $=A;
    }else {
      $error("error xml of "+A);
      return null;
    }
  }
  if(_.indexOf("/")!=0){
    _="/"+_;
  }
  if(_.indexOf("/root/data")<0){
    _="/root/data"+_;
  }
  var D=$.selectNodes(_);
  if(D){
    if(!B){
      var E=_.split("/");
      B=E[E.length-1];
    }
    var C=new Dataset(B);
    C.init(D);
    return C;
  }else {
    $error("no node found by xpath:"+_+"in xml:"+$.xml);
    return null;
  }
};
function Dataset2Array(G,F,B){
  var A=[];
  if(!G){
    return A;
  }
  F=F||"";
  var E=G.getLength();
  for(var _=0;_<E;_++){
    var I=G.get(_);
    if(B){
      if(I.status==Entity.STATUS_DELT||I.status==Entity.STATUS_HIDDEN){
        continue ;
      }
    }
    var $=Entity2Array(I),D=_+1;
    for(var H=0;H<$.length;H++){
      var C=F+"["+D+"]/"+$[H].key;
      A.push({key:C,value:$[H].value});
    }
  }
  return A;
}
function Entity2Array(B){
  var $=[];
  if(!B){
    return $;
  }
  if(B.__type){
    $.push({key:"__type",value:B.__type});
  }
  var C=B.keys.length;
  for(var E=0;E<C;E++){
    var F=B.keys[E],_=B.getProperty(F);
    if(_ instanceof Entity){
      var H=Entity2Array(_);
      for(var A=0;A<H.length;A++){
        var G=F+"/"+H[A].key;
        $.push({key:G,value:H[A].value});
      }
    }else {
      if(_ instanceof Dataset){
        var D=Dataset2Array(_,F);
        for(A=0;A<D.length;A++){
          F=D[A].key;
          $.push({key:F,value:D[A].value});
        }
      }else {
        F=F;
        $.push({key:F,value:_});
      }
    }
  }
  return $;
}
function isEmptyObject(_){
  if(typeof (_)!="object"){
    return false;
  }
  for(var $ in _){
    return false;
  }
  return true;
}
function Json2Entity($,B){
  if(!$){
    return $;
  }
  B=B||new Entity();
  for(var _ in $){
    if($.hasOwnProperty(_)){
      var A=$[_];
      if(isArray(A)){
        A=Json2Dataset(A,_);
      }else {
        if(isEmptyObject(A)){
          A=null;
        }else {
          if(A!==null&&A!==undefined&&typeof (A)=="object"){
            A=Json2Entity(A);
            A.parent=B;
            A.name=_;
          }
        }
      }
      B.setProperty(_,A);
    }
  }
  return B;
}
function Json2Dataset(A,B,D){
  D=D||new Dataset();
  A=A||[];
  if(!isArray(A)){
    A=[A];
  }
  for(var C=0,_=A.length;C<_;C++){
    var $=Json2Entity(A[C]);
    $.name=B||$.name;
    D.addEntity($,false);
  }
  return D;
}
function Entity2Json(A,_){
  _=_||{};
  var B=A.getKeys();
  for(var C=0,$=B.length;C<$;C++){
    var D=A.getProperty(B[C]);
    if(D instanceof Entity){
      D=Entity2Json(D);
    }else {
      if(D instanceof Dataset){
        D=Dataset2Json(D);
      }
    }
    _[B[C]]=D;
  }
  return _;
}
function Dataset2Json(D,A,C){
  A=A||[];
  for(var B=0,_=D.values.length;B<_;B++){
    var $=D.values[B];
    if(C!==true&&($.status==Entity.STATUS_DELT||$.status==Entity.STATUS_HIDDEN)){
      continue ;
    }
    A.push(Entity2Json($));
  }
  return A;
}
function SimpleEditor($){
  var _=null;
  if($){
    if(!$.getAttribute("__eos__editor__inited")){
      if($.tagName&&$.tagName.toLowerCase()=="form"){
        _=initFormEditor($);
      }else {
        _=initSimpleEditor($);
      }
      $.setAttribute("__eos__editor__inited",true);
    }
  }
  return _;
}
function initSimpleEditor($){
  $.onmouseover=function(){
    this.mouseOver=true;
  };
  $.onmouseout=function(){
    this.mouseOver=false;
  };
  $.onmousedown=function(){
    eventManager.stopEvent();
  };
  $.setValue=function(B){
    if(this.type=="select-multiple"){
      var C,_=[];
      if(B instanceof Array){
        C=B;
      }else {
        C=B.split(",");
      }
      for(var A=0;A<this.options.length;A++){
        for(var $=0;$<C.length;$++){
          if(C[$]==this.options[A].value){
            this.options[A].selected=true;
            break ;
          }
        }
      }
    }else {
      if(B===null||B===undefined){
        this.value="";
      }else {
        this.value=B;
      }
    }
  };
  $.getValue=function(){
    if(this.type=="select-multiple"){
      var _=[];
      for(var $=0;$<this.options.length;$++){
        if(this.options[$].selected==true){
          _.push(this.options[$].value);
        }
      }
      return _;
    }
    return this.value;
  };
  $.showEditor=function(){
    this.style.display="";
    try{
      this.focus();
    }
    catch(_){
    }
    var $=this;
    setTimeout(function(){
      try{
        $.focus();
      }
      catch(_){
      }
    },1);
  };
  $.hideEditor=function(){
    this.style.display="none";
    this.hiddenMessage();
  };
  $.setPosition=function($,A,B,C){
    this.style.position="absolute";
    var _=getMaxZindex(document);
    if(this.style.zIndex!=_){
      this.style.zIndex=_;
    }
    this.style.top="0px";
    this.style.left="0px";
    setElementXY(this,[$,A]);
    if(this.type=="text"||this.type=="password"||this.type=="select-one"){
      this.style.width=B+"px";
      this.style.height=C+"px";
    }else {
      if(this.type=="select-multiple"){
        this.style.width=B+"px";
      }
    }
  };
  $.isFocus=function(){
    return this.mouseOver;
  };
  $.validate=function(){
    return checkInput(this);
  };
  $.onkeyup=function(){
    if(this.validate()==false){
      var _=this,$=this.timeout;
      clearTimeout($);
      this.timeout=setTimeout(function(){
        return f_alert_hidden_message.apply(_,[_]);
      },3500);
    }
  };
  $.hiddenMessage=function(){
    f_alert_verify_successful(this);
  };
  $.getDisplayValue=function(B){
    if(!B){
      return null;
    }
    if(this.type=="select-one"){
      for(var A=0;A<this.options.length;A++){
        if(this.options[A].value==B){
          return this.options[A].text;
        }
      }
    }else {
      if(this.type=="select-multiple"){
        var C,_=[];
        if(B instanceof Array){
          C=B;
        }else {
          C=B.split(",");
        }
        for(A=0;A<this.options.length;A++){
          for(var $=0;$<C.length;$++){
            if(C[$]==this.options[A].value){
              _.push(this.options[A].text);
              break ;
            }
          }
        }
        if(B instanceof Array){
          return _;
        }else {
          return _.join(",");
        }
      }else {
        if(this.type=="password"){
          return "******";
        }
      }
    }
    return B;
  };
  return $;
}
function initFormEditor(_){
  _.value=null;
  _.onmouseover=function(){
    this.mouseOver=true;
  };
  _.onmouseout=function(){
    this.mouseOver=false;
  };
  _.setValue=function($){
    _.value=$;
    setTimeout(A,50);
  };
  _.getValue=function(){
    return this.value;
  };
  _.showEditor=function(){
    this.style.display="";
  };
  _.hideEditor=function(){
    this.style.display="none";
  };
  _.setPosition=function($,A,_,B){
    setPosition(this,[$,A]);
    if(this.type=="text"||this.type=="password"||this.type=="select-one"||this.type=="select-multiple"){
      this.style.width=_+"px";
      this.style.height=B+"px";
    }
  };
  _.isFocus=function(){
    return this.mouseOver;
  };
  _.validate=function(){
    return checkForm(this);
  };
  _.getDisplayValue=function($){
    return $;
  };
  _.freshFromEntity=function($){
    this.value=$;
    A();
  };
  _.update=function(){
    B();
  };
  function B(){
    if(_.value){
      for(var $ in _.editors){
        var A=_.editors[$];
        _.value.setProperty($,A.getValue());
      }
    }
  }
  function F(){
  }
  function A(){
    if(_.value){
      for(var $ in _.editors){
        var A=_.editors[$];
        A.setValue(_.value.getProperty($));
      }
    }
  }
  _.editors={};
  for(var E=0;E<_.elements.length;E++){
    var D=_.elements[E];
    if(D.name){
      var C=D.getAttribute("jsid");
      if(C&&C.length>0){
        var $=$id(C);
        if($){
          _.editors[D.name]=$;
        }
      }else {
        _.editors[D.name]=(SimpleEditor(D));
      }
    }
  }
  return _;
}
function TimeSelect($){
  this.id=$;
  PageControl.add(this.id,this);
  this.name=null;
  this.hour="00";
  this.minute="00";
  this.second="00";
  this.hourInput=null;
  this.minuteInput=null;
  this.secondInput=null;
  this.container=null;
  this.upButton=null;
  this.downButton=null;
  this.hiddenInput=null;
  this.dateTime=null;
  this.defaultValue=null;
  this.__privateFormat="HH:mm:ss";
  this.format="HH:mm:ss";
  this.tabIndex=null;
  this.accesskey=null;
  this.currInput=null;
  this.BUTTON_UP_ID="BUTTON_UP"+$;
  this.BUTTON_DOWN_ID="BUTTON_DOWN"+$;
  this.INPUT_HOUR_ID="INPUT_HOUR"+$;
  this.INPUT_MINUTE_ID="INPUT_MINUTE"+$;
  this.INPUT_SECOND_ID="INPUT_SECOND"+$;
  this.INPUT_HIDDEN_ID="INPUT_HIDDEN"+$;
  this.maxValue="23:59:59";
  this.minValue="00:00:00";
  this.maxHour=23;
  this.minHour=0;
  this.maxMinute=59;
  this.minMinute=0;
  this.maxSecond=59;
  this.minSecond=0;
  this.readonly=false;
  this.isOver=false;
  this.disabled=false;
  this.allowNull=true;
}
TimeSelect.prototype.setReadOnly=function($){
  this.readonly=$;
  if($){
    this.hourInput.readOnly=true;
    this.minuteInput.readOnly=true;
    this.secondInput.readOnly=true;
  }else {
    this.hourInput.readOnly=false;
    this.minuteInput.readOnly=false;
    this.secondInput.readOnly=false;
  }
};
TimeSelect.prototype.getReadOnly=function(){
  return this.readonly;
};
TimeSelect.prototype.setDisabled=function($){
  this.disabled=$;
  if($){
    this.hiddenInput.disabled=true;
    this.hourInput.disabled=true;
    this.minuteInput.disabled=true;
    this.secondInput.disabled=true;
  }else {
    this.hiddenInput.disabled=false;
    this.hourInput.disabled=false;
    this.minuteInput.disabled=false;
    this.secondInput.disabled=false;
  }
};
TimeSelect.prototype.getDisabled=function(){
  return this.disabled;
};
TimeSelect.prototype.init=function(){
  var _=this.toHtml(),$=$create("div");
  $.innerHTML=_;
  this.container=$id(this.id+"_container");
  this.container.appendChild($);
  this.container.className="eos-time";
  this.container.onmouseover=function(){
    this.className="eos-time-over";
    this.isOver=true;
  };
  this.container.onmouseout=function(){
    this.className="eos-time";
    this.isOver=false;
  };
  this.container.onmousedown=function(){
  };
  if(!this.defaultValue&&!this.defaultNull){
    this.defaultValue="00:00:00";
  }
  if(this.defaultValue&&timeFormat(this.defaultValue,this.format)){
    if(this.maxValue&&this.defaultValue>this.maxValue){
      this.defaultValue=this.maxValue;
    }
    if(this.minValue&&this.defaultValue<this.minValue){
      this.defaultValue=this.minValue;
    }
  }
  this.initObject();
  this.setReadOnly(this.readonly);
  this.setDisabled(this.disabled);
  this.currInput=null;
};
TimeSelect.prototype.initObject=function(){
  this.setCheckValue();
  this.hourInput=$id(this.INPUT_HOUR_ID);
  this.minuteInput=$id(this.INPUT_MINUTE_ID);
  this.secondInput=$id(this.INPUT_SECOND_ID);
  this.upButton=$id(this.BUTTON_UP_ID);
  this.downButton=$id(this.BUTTON_DOWN_ID);
  this.hiddenInput=$id(this.INPUT_HIDDEN_ID);
  var $=this;
  this.hourInput.onkeydown=function(){
    var _=eventManager.getKeyCode();
    return $.downkeyCode(_);
  };
  this.minuteInput.onkeydown=function(){
    var _=eventManager.getKeyCode();
    return $.downkeyCode(_);
  };
  this.secondInput.onkeydown=function(){
    var _=eventManager.getKeyCode();
    return $.downkeyCode(_);
  };
  this.setValue(this.defaultValue);
  this.hourInputPid=$id(this.INPUT_HOUR_ID+"_pid");
  this.secondInputPid=$id(this.INPUT_SECOND_ID+"_pid");
  if(this.srcFormat){
    this.__privateFormat=this.srcFormat;
    this.format=this.srcFormat;
  }
  if(this.srcFormat=="mm:ss"){
    this.hourInputPid.style.display="none";
    $id(this.id+"_time_container").style.width="70px";
    $id(this.id+"_pid").style.width="51px";
  }
  if(this.srcFormat=="HH:mm"){
    this.secondInputPid.style.display="none";
    $id(this.id+"_time_container").style.width="70px";
    $id(this.id+"_pid").style.width="51px";
  }
};
TimeSelect.prototype.setCheckValue=function(){
  if(isFormatTime(this.maxValue,this.format)){
    this.maxValue=timeFormat(this.maxValue,this.format,this.__privateFormat);
  }
  if(!isFormatTime(this.maxValue,this.__privateFormat)){
    this.maxValue="23:59:59";
  }
  var _=this.maxValue.split(":");
  this.maxHour=_[0];
  this.maxMinute=_[1];
  this.maxSecond=_[2];
  if(isFormatTime(this.minValue,this.format)){
    this.minValue=timeFormat(this.minValue,this.format,this.__privateFormat);
  }
  if(!isFormatTime(this.minValue,this.__privateFormat)){
    this.minValue="00:00:00";
  }
  var $=this.minValue.split(":");
  this.minHour=$[0];
  this.minMinute=$[1];
  this.minSecond=$[2];
};
TimeSelect.prototype.downkeyCode=function($){
  if($==38){
    this.up();
    return false;
  }
  if($==40){
    this.down();
    return false;
  }
  if($==9||$==8||$==37||$==39){
    return true;
  }
  if($==229){
    return false;
  }
  if($<106&&$>95){
    return true;
  }
  if($==46){
    return true;
  }
  if($>47&&$<58){
    return true;
  }else {
    return false;
  }
};
TimeSelect.prototype.clear=function(){
  if(this.allowNull){
    this.setValue(null);
  }
};
TimeSelect.prototype.up=function(){
  if(this.readonly||this.disabled){
    return ;
  }
  if(!this.currInput){
    if(this.srcFormat=="HH:mm"){
      this.currInput=this.minuteInput;
    }else {
      this.currInput=this.secondInput;
    }
    if(allTrim(this.hourInput.value)==""&&allTrim(this.minuteInput.value)==""){
      this.hourInput.value="00";
      this.minuteInput.value="00";
    }
  }
  var $=this.currInput.value*1+1;
  this.currInput.value=dateToStringValue($);
  this.prevent(this.currInput);
  this.currInput.focus();
  this.currInput.select();
};
TimeSelect.prototype.down=function(){
  if(this.readonly||this.disabled){
    return ;
  }
  $debug(this.currInput);
  if(!this.currInput){
    if(this.srcFormat=="HH:mm"){
      this.currInput=this.minuteInput;
    }else {
      this.currInput=this.secondInput;
    }
    if(this.hourInput.value==""&&this.minuteInput.value==""){
      this.hourInput.value="00";
      this.minuteInput.value="00";
    }
  }
  var $=this.currInput.value*1-1;
  this.currInput.value=dateToStringValue($);
  this.prevent(this.currInput);
  this.currInput.focus();
  this.currInput.select();
};
TimeSelect.prototype.getHours=function(){
  this.refreshValue();
  return dateToStringValue(this.hourInput.value);
};
TimeSelect.prototype.getMinutes=function(){
  this.refreshValue();
  return dateToStringValue(this.minuteInput.value);
};
TimeSelect.prototype.getSeconds=function(){
  this.refreshValue();
  return dateToStringValue(this.secondInput.value);
};
TimeSelect.prototype.setHours=function($){
  this.hourInput.value=dateToStringValue($);
  this.refreshValue();
};
TimeSelect.prototype.setMinutes=function($){
  this.minuteInput.value=dateToStringValue($);
  this.refreshValue();
};
TimeSelect.prototype.setSeconds=function($){
  this.secondInput.value=dateToStringValue($);
  this.refreshValue();
};
TimeSelect.prototype.getValue=function(){
  this.refreshValue();
  if(this.srcFormat=="mm:ss"||this.srcFormat=="HH:mm"){
    if(this.dateTime){
      return this.dateTime;
    }
  }else {
    if(this.dateTime){
      return timeFormat(this.dateTime,this.__privateFormat,this.format);
    }
  }
  return null;
};
TimeSelect.prototype.setValue=function($){
  if($===null||$===undefined||$==""){
    this.dateTime=null;
  }else {
    var _=timeFormat2($,this.srcFormat);
    if(_==1){
      this.dateTime=$;
    }else {
      if(_==0){
        alert(TIME_FORMAT_ERROR);
      }else {
        this.dateTime=timeFormat($,this.format,this.__privateFormat);
      }
    }
  }
  this.refresh();
};
function timeFormat2(_,$){
  if($=="mm:ss"){
    var A=_.split(":");
    if(A.length==2){
      if(!isNumber(A[0])||A[0]<0||A[0]>59){
        return 0;
      }
      if(!isNumber(A[1])||A[1]<0||A[1]>59){
        return 0;
      }
    }else {
      return 0;
    }
    return 1;
  }else {
    if($=="HH:mm"){
      A=_.split(":");
      if(A.length==2){
        if(!isNumber(A[0])||A[0]<0||A[0]>23){
          return 0;
        }
        if(!isNumber(A[1])||A[1]<0||A[1]>59){
          return 0;
        }
      }else {
        return 0;
      }
      return 1;
    }
  }
  return 2;
}
TimeSelect.prototype.setFocus=function(){
  this.hourInput.focus();
};
TimeSelect.prototype.isFocus=function(){
  return this.isOver;
};
TimeSelect.prototype.showEditor=function(){
  this.container.style.display="";
};
TimeSelect.prototype.hideEditor=function(){
  this.container.style.display="none";
};
TimeSelect.prototype.getDisplayValue=function($){
  return $;
};
TimeSelect.prototype.setPosition=function($,A,B,C){
  if(this.container){
    this.container.style.position="absolute";
    var _=getMaxZindex(document);
    if(this.container.style.zIndex!=_){
      this.container.style.zIndex=_;
    }
    setElementXY(this.container,[$,A]);
  }
};
TimeSelect.prototype.refresh=function(){
  if(this.dateTime){
    var $=this.dateTime.split(":");
    $[0]=$[0]||"00";
    $[1]=$[1]||"00";
    $[2]=$[2]||"00";
    this.hourInput.value=$[0];
    this.minuteInput.value=$[1];
    this.secondInput.value=$[2];
  }else {
    this.hourInput.value="";
    this.minuteInput.value="";
    this.secondInput.value="";
  }
  this.refreshValue();
};
TimeSelect.prototype.refreshValue=function(){
  var $=dateToStringValue(this.hourInput.value),_=dateToStringValue(this.minuteInput.value),A=dateToStringValue(this.secondInput.value);
  if(this.srcFormat=="mm:ss"){
    if(_&&A){
      this.dateTime=_+":"+A;
      this.hiddenInput.value=this.dateTime;
    }
  }else {
    if(this.srcFormat=="HH:mm"){
      if($&&_){
        this.dateTime=$+":"+_;
        this.hiddenInput.value=this.dateTime;
      }
    }else {
      if($&&_&&A){
        this.dateTime=$+":"+_+":"+A;
        this.hiddenInput.value=this.dateTime;
      }
    }
  }
};
TimeSelect.prototype.toHtml=function(){
  var _="tabIndex=\""+this.tabIndex+"\" accesskey=\""+this.accesskey+"\" ",$=_+"type=\"text\" autocomplete=\"off\" onpropertychange=\"$o('"+this.id+"').setForObjValue(this)\"  maxlength=\"2\" onclick=\"this.focus();this.select()\" onfocus=\"$o('";
  $+=this.id+"').Focus(this);\" onkeyup=\"$o('"+this.id+"').keyPress(this)\"";
  $+=" onpaste=\"return false\" ondragenter=\"return false\" onchange=\"$o('";
  $+=this.id+"').setForObjValue(this)\" onblur=\"$o('"+this.id+"').Blur(this)\"";
  var A="";
  A+="<div id=\""+this.id+"_time_container\" class=\"eos-time-container\" style=\"background-color:#ffffff;width: 91px;height:22px;float:left;overflow:hidden\">";
  A+="<div id=\""+this.id+"_pid\" style=\"font-size:11pt;float:left;width: 72px;height:22px;overflow:hidden;float:left;padding-top:1px;padding-buttom:3px;padding-left:3px;\">";
  A+="<span id=\""+this.INPUT_HOUR_ID+"_pid\"><input numtype=\"h\" id=\""+this.INPUT_HOUR_ID+"\"  "+$+" style=\"ime-mode:disabled;width:18px;height:14px;vertical-align:middle;background-color:transparent;border:0px solid black;font-size: 9pt;text-align: center;\"><b>:</b></span>";
  A+="<input numtype=\"m\" id=\""+this.INPUT_MINUTE_ID+"\"  "+$+" style=\"ime-mode:disabled;width:18px;height:14px;vertical-align:middle;background-color:transparent;border:0px solid black;font-size: 9pt;text-align: center;\">";
  A+="<span id=\""+this.INPUT_SECOND_ID+"_pid\"><b>:</b><input numtype=\"s\" id=\""+this.INPUT_SECOND_ID+"\"  "+$+" style=\"ime-mode:disabled;width:18px;height:14px;vertical-align:middle;background-color:transparent;border:0px solid black;font-size: 9pt;text-align: center;\"></span>";
  A+="</div>";
  A+="<div style=\"width: 16px;height:22px;overflow:hidden;float:right;padding-top:0px;\">";
  A+="<div style=\"width: 16px;height:11px;overflow:hidden;\">";
  A+="<div onclick=\"$id('"+this.id+"').controlTime(true)\" id=\""+this.BUTTON_UP_ID+"\" class=\"eos-time-arrow-up\" onmouseover=\"this.className='eos-time-arrow-up-over'\" onmouseout=\"this.className='eos-time-arrow-up'\" style=\"width: 16px;height:10px;\">";
  A+="</div>";
  A+="</div>";
  A+="<div style=\"width: 16px;height:11px;overflow:hidden;\">";
  A+="<div onclick=\"$id('"+this.id+"').controlTime(false)\" id=\""+this.BUTTON_DOWN_ID+"\" class=\"eos-time-arrow-down\" onmouseover=\"this.className='eos-time-arrow-down-over'\" onmouseout=\"this.className='eos-time-arrow-down'\" style=\"width: 16px;height:10px;\">";
  A+="</div>";
  A+="</div>";
  A+="</div>";
  A+="</div>";
  A+="<div><input validateAttr='type=eos_time'  type='hidden' ";
  if(this.name){
    A+="name='"+this.name+"'";
  }
  A+="id='"+this.INPUT_HIDDEN_ID+"' value='"+this.dateTime+"'></div>";
  return A;
};
TimeSelect.prototype.keyPress=function($){
  if($.value.length==2){
    this.prevent($);
  }
};
TimeSelect.prototype.Blur=function(_){
  this.lostFocus=true;
  var $=this;
  if(_.value.length==2){
    this.prevent(_);
  }else {
    if(_.value.length==1){
      _.value="0"+_.value;
      this.prevent(_);
    }
  }
  window.tmpTimeout=setTimeout(function(){
    if($.lostFocus){
      $.validate();
    }
  },300);
};
TimeSelect.prototype.Focus=function($){
  this.currInput=$;
  this.lostFocus=false;
};
TimeSelect.prototype.validate=function(){
  if(allTrim(this.hourInput.value+this.minuteInput.value+this.secondInput.value)==""){
    if(this.allowNull){
      f_alert_verify_successful($id(this.id+"_time_container"));
      return true;
    }else {
      f_alert($id(this.id+"_time_container"),CHECK_MUST_INPUT);
      return false;
    }
  }
  if(this.srcFormat=="mm:ss"){
    if(allTrim(this.minuteInput.value)==""||allTrim(this.secondInput.value)==""){
      f_alert($id(this.id+"_time_container"),TIME_INPUTTIME+this.minuteInput.value+":"+this.secondInput.value+"\u4e0d\u7b26\u5408\u683c\u5f0f "+this.srcFormat);
      return false;
    }
  }else {
    if(this.srcFormat=="HH:mm"){
      if(allTrim(this.hourInput.value)==""||allTrim(this.minuteInput.value)==""){
        f_alert($id(this.id+"_time_container"),TIME_INPUTTIME+this.hourInput.value+":"+this.minuteInput.value+"\u4e0d\u7b26\u5408\u683c\u5f0f "+this.srcFormat);
        return false;
      }
    }else {
      if(allTrim(this.hourInput.value)==""||allTrim(this.minuteInput.value)==""||allTrim(this.secondInput.value)==""){
        f_alert($id(this.id+"_time_container"),TIME_INPUTTIME+this.hourInput.value+":"+this.minuteInput.value+":"+this.secondInput.value+TIME_FORMATERROR);
        return false;
      }
    }
  }
  if(parseInt(this.maxHour*3600+this.maxMinute*60+this.maxMinute)<parseInt(this.hourInput.value*3600+this.minuteInput.value*60+this.secondInput.value)){
    f_alert($id(this.id+"_time_container"),TIME_INPUTTIME+this.hourInput.value+":"+this.minuteInput.value+":"+this.secondInput.value+TIME_LESSTHAN+this.maxValue);
    return false;
  }
  if((this.minHour*3600+this.minMinute*60+this.minMinute)>(this.hourInput.value*3600+this.minuteInput.value*60+this.secondInput.value)){
    f_alert($id(this.id+"_time_container"),TIME_INPUTTIME+this.hourInput.value+":"+this.minuteInput.value+":"+this.secondInput.value+TIME_MORETHAN+this.minValue);
    return false;
  }
  f_alert_verify_successful($id(this.id+"_time_container"));
  return true;
};
TimeSelect.prototype.prevent=function($){
  if($.value==""||$.value===null||$.value===undefined){
    return ;
  }
  numtype=$.getAttribute("numtype");
  if(numtype=="h"){
    if($.value*1>23){
      $.value="00";
    }else {
      if($.value*1<0){
        $.value="23";
      }else {
        if(isNaN($.value)){
          $.value="00";
        }
      }
    }
  }else {
    if(numtype=="m"||numtype=="s"){
      if($.value*1>59){
        $.value="00";
      }else {
        if($.value*1<0){
          $.value="59";
        }else {
          if(isNaN($.value)){
            $.value="00";
          }
        }
      }
    }
  }
};
TimeSelect.prototype.setForObjValue=function($){
  this.prevent($);
  this.refreshValue();
};
TimeSelect.prototype.controlTime=function($){
  if($){
    this.up();
  }else {
    this.down();
  }
  this.refreshValue();
};
function f_check_eos_time($){
  if($.id){
    var A=$.id;
    if(A.indexOf("INPUT_HIDDEN")>=0){
      A=A.replace("INPUT_HIDDEN","");
      var _=$id(A);
      if(_){
        return _.validate();
      }
    }
  }
  return true;
}
function Calendar($,_){
  this.showMoreDay=true;
  this.id=$;
  this.Obj=$;
  this.date=null;
  this.mouseOffset=null;
  this.timer=null;
  this.timeSelect=null;
  this.format=isDateFormat(_)?_:"yyyy-MM-dd";
  if(isTimeFormat(this.format)){
    this.showTime=true;
    this.__privateFormat="yyyy-MM-dd HH:mm:ss";
    this.maxValue=dateFormat("2500-12-31 23:59:59","yyyy-MM-dd HH:mm:ss",this.format);
    this.minValue=dateFormat("1700-01-01 00:00:00","yyyy-MM-dd HH:mm:ss",this.format);
  }else {
    this.showTime=false;
    this.__privateFormat="yyyy-MM-dd";
    this.maxValue=dateFormat("2500-12-31","yyyy-MM-dd",this.format);
    this.minValue=dateFormat("1700-01-01","yyyy-MM-dd",this.format);
  }
  this.submitFormat=null;
  this.inputObject=null;
  this.eventObject=null;
  this.hiddenObject=null;
  this.canClose=true;
  this.container=null;
  this.focusStatus=false;
  this.defaultNull=false;
  this.allowInput=true;
  this.onSelectFunc=null;
  this.value=null;
  PageControl.add(this.id,this);
}
Calendar.currentCalendar=null;
Calendar.editorContainer=null;
Calendar.doc=document;
Calendar.prototype.init=function(){
  this.container=$id(this.id+"_container");
  this.inputObject=$id(this.id+"_input");
  this.hiddenObject=$id(this.id+"_hidden");
  this.eventObject=$id(this.id+"_button");
  this.button=this.eventObject;
  this.text=this.inputObject;
  this.hidden=this.hiddenObject;
  this.setReadOnly(this.readOnly);
  this.setDisabled(this.isDisabled);
  if(this.width!=null){
    this.text.style.width=this.width;
  }
  this.refreshDisplayValue();
  this.refreshHiddenValue();
  this.initEvent();
  if(this.maxValue){
    this.maxValue=dateFormat(this.maxValue,this.format,this.__privateFormat);
  }
  if(this.minValue){
    this.minValue=dateFormat(this.minValue,this.format,this.__privateFormat);
  }
};
Calendar.prototype.setReadOnly=function($){
  this.readOnly=$;
  this.inputObject.readOnly=$||!this.allowInput;
  if($){
    this.eventObject.style.cursor="default";
  }else {
    this.eventObject.style.cursor="pointer";
    this.eventObject.style.cursor="hand";
  }
};
Calendar.prototype.getReadOnly=function(){
  return this.readOnly;
};
Calendar.prototype.setDisabled=function($){
  this.isDisabled=$;
  if($){
    this.disabled();
    this.eventObject.style.cursor="default";
  }else {
    this.enable();
    this.eventObject.style.cursor="pointer";
    this.eventObject.style.cursor="hand";
  }
};
Calendar.prototype.getDisabled=function(){
  return this.isDisabled;
};
Calendar.prototype.refreshDisplayValue=function(){
  if(this.value){
    this.inputObject.value=dateFormat(this.value,this.__privateFormat,this.format);
    f_alert_verify_successful(this.inputObject);
  }
};
Calendar.prototype.refreshHiddenValue=function(){
  if(this.value){
    var $=this.__privateFormat;
    if(this.submitFormat&&this.submitFormat!=this.__privateFormat){
      $=this.submitFormat;
    }
    this.hiddenObject.value=dateFormat(this.value,this.__privateFormat,$);
  }else {
    this.hiddenObject.value="";
  }
};
Calendar.prototype.isFocus=function(){
  return this.focusStatus;
};
Calendar.prototype.initEvent=function(){
  var $=this;
  this.inputObject.onkeyup=function(){
    if(checkInput(this)==false){
      return ;
    }
    if(!$.validate()){
      f_alert($.eventObject,CALENDAR_ERROR_BETWEEN.replace("{0}",this.value).replace("{1}",$.format).replace("{2}",$.minValue).replace("{3}",$.maxValue));
    }else {
      f_alert_verify_successful(this);
      $.date=dateFormat(this.value,$.format,$.__privateFormat);
      if(this.value==""){
        $.value="";
      }else {
        $.value=dateFormat(this.value,$.format,$.__privateFormat);
      }
      $.refreshHiddenValue();
    }
  };
  this.inputObject.onblur=this.inputObject.onkeyup;
  this.eventObject.src=contextPath+"/common/skins/skin0/images/calendar/calendar_button.gif";
  this.eventObject.onmouseover=function(){
    if($.getDisabled()||$.getReadOnly()){
      return ;
    }
    this.src=contextPath+"/common/skins/skin0/images/calendar/calendar_button_over.gif";
  };
  this.eventObject.onmouseout=function(){
    if($.getDisabled()||$.getReadOnly()){
      return ;
    }
    this.src=contextPath+"/common/skins/skin0/images/calendar/calendar_button.gif";
  };
  this.eventObject.onmousedown=function(){
    if($.getDisabled()||$.getReadOnly()){
      return ;
    }
    this.src=contextPath+"/common/skins/skin0/images/calendar/calendar_button_down.gif";
  };
  this.eventObject.onmouseup=function(){
    if($.getDisabled()||$.getReadOnly()){
      return ;
    }
    this.src=contextPath+"/common/skins/skin0/images/calendar/calendar_button_over.gif";
    if(CalendarEditor.inited===false){
      CalendarEditor.init();
      CalendarEditor.inited=true;
    }
    if(CalendarEditor.getCalendar()==$){
      if(CalendarEditor.showStatus){
        $.hide();
      }else {
        $.show();
      }
    }else {
      $.show();
    }
    eventManager.stopPropagation();
  };
};
Calendar.prototype.show=function(){
  PageControl.addtoStack(this);
  var C={maxValue:this.maxValue,minValue:this.minValue,value:this.value,format:this.__privateFormat,showTime:this.showTime};
  CalendarEditor.reFreshEditor(C);
  CalendarEditor.showEditor();
  var E=getPosition(this.container),B=E.left,$=E.top;
  if(window!=CalendarEditor.win){
    if(isIE){
      var D=getAbsPos(this.inputObject,CalendarEditor.win);
      B=D.left;
      $=D.top;
      if(CalendarEditor.win!=window){
        B=B-2;
        $=$-2;
      }
    }else {
      D=getScreenPos(window,CalendarEditor.win);
      B=B+D.left-document.body.scrollLeft;
      $=$+D.top-document.body.scrollTop;
    }
  }
  $=$*1+this.inputObject.offsetHeight*1;
  CalendarEditor.setPosition(B,$);
  CalendarEditor.setCurrEditor(this);
  if(!this.inputObject){
    $error("err parameters of date in Calendar:"+this.id+".show");
    return ;
  }
  var A=this.inputObject.value,_=false;
  if(A){
    if(isDate(A,this.format)){
      if(this.showTime){
        if(isFormatTime(A,this.format)){
          A=dateFormat(A,this.format,this.__privateFormat);
          _=true;
        }
      }else {
        A=dateFormat(A,this.format,this.__privateFormat);
        _=true;
      }
    }
  }
  if(!_){
    A=dateToString(new Date(),this.__privateFormat);
  }
  this.focusStatus=true;
};
Calendar.prototype.hide=function(){
  try{
    CalendarEditor.hideEditor();
    this.focusStatus=false;
  }
  catch($){
  }
};
Calendar.prototype.getValue=function(){
  if(this.inDatacell){
    return this.hiddenObject.value;
  }else {
    return this.value;
  }
};
Calendar.prototype.setValue=function($){
  if(!$||$==""){
    this.value=null;
    this.inputObject.value="";
    this.hiddenObject.value="";
    return ;
  }
  if((typeof ($))!="string"){
    this.value=dateToString($,this.__privateFormat);
    this.refreshDisplayValue();
    this.refreshHiddenValue();
  }else {
    if(isDate($,this.__privateFormat)){
      this.value=$;
      this.refreshDisplayValue();
      this.refreshHiddenValue();
    }else {
      if(this.submitFormat&&isDate($,this.submitFormat)){
        this.value=dateFormat($,this.submitFormat,this.__privateFormat);
        this.refreshDisplayValue();
        this.refreshHiddenValue();
      }
    }
    if(isDate($,"yyyy-MM-dd HH:mm:ss.S")||isDate($,"yyyy-MM-dd HH:mm:ss")||isDate($,"yyyyMMddHH")||isDate($,"yyyyMMddHHmm")||isDate($,"yyyyMMddHHmmss")||isDate($,"yyyyMMddHHmmssS")||isDate($,"yyyy-MM-dd")||isDate($,"yyyy-MM-dd HH")||isDate($,"yyyy-MM-dd HH:mm")){
      this.value=dateFormat($,"yyyy-MM-dd HH:mm:ss",this.__privateFormat);
      this.refreshDisplayValue();
      this.refreshHiddenValue();
    }else {
      this.inputObject.value="";
      this.hiddenObject.value="";
    }
  }
};
Calendar.prototype.setPosition=function($,A,B,C){
  this.hide();
  if(this.container){
    this.container.style.display="";
    this.container.style.position="absolute";
    var _=getMaxZindex(document);
    if(this.container.style.zIndex!=_){
      this.container.style.zIndex=_;
    }
    this.container.style.left=$;
    this.container.style.top=A;
    this.eventObject.style.height=C;
    this.container.style.height=C;
    this.container.style.width=B;
    this.inputObject.style.height=C;
    this.inputObject.style.width=B-17;
  }
};
Calendar.prototype.setFocus=function(){
};
Calendar.prototype.onValidate=function(_,$){
  return true;
};
Calendar.prototype.validate=function(){
  var C=this.inputObject.value;
  if(C){
    var A=isDate(this.inputObject.value,this.format),B=true;
    if(this.showTime){
      B=isFormatTime(this.inputObject.value,this.format);
    }
    if(A&&B){
      var $=stringToDate(this.maxValue,this.__privateFormat),D=stringToDate(this.minValue,this.__privateFormat),_=stringToDate(C,this.format);
      if(_-$>0){
        f_alert(this.inputObject,CALENDAR_ERROR_MIN.replace("{0}",C).replace("{1}",this.maxValue));
        return false;
      }
      if(_-D<0){
        f_alert(this.inputObject,CALENDAR_ERROR_MAX.replace("{0}",C).replace("{1}",this.minValue));
        return false;
      }
    }else {
      f_alert(this.inputObject,CALENDAR_ERROR_RORMAT.replace("{0}",C).replace("{1}",this.format));
      return false;
    }
  }
  return this.onValidate(this.hidden.value,this.text.value);
};
Calendar.prototype.hideEditor=function(){
  f_alert_verify_successful(this.inputObject);
  this.hide();
  this.container.style.display="none";
  this.eventObject.style.display="none";
};
Calendar.prototype.showEditor=function(){
  this.container.style.display="";
  this.eventObject.style.display="";
};
Calendar.prototype.disabled=function(){
  this.inputObject.disabled=true;
  this.eventObject.disabled=true;
  this.hiddenObject.disabled=true;
  this.container.disabled=true;
};
Calendar.prototype.getDisplayValue=function($){
  if($===null||$===undefined||$==""){
    return "";
  }
  if(isDate($,this.__privateFormat)){
    return dateFormat($,this.__privateFormat,this.format);
  }else {
    if(isDate($,"yyyy-MM-dd")){
      return dateFormat($,"yyyy-MM-dd",this.format);
    }
    if(isDate($,"yyyy-MM-dd HH:mm:ss")){
      return dateFormat($,"yyyy-MM-dd HH:mm:ss",this.format);
    }
    if(isDate($,"yyyy-MM-dd HH:mm:ss.S")){
      return dateFormat($,"yyyy-MM-dd HH:mm:ss",this.format);
    }
    return $;
  }
};
Calendar.prototype.enable=function(){
  this.inputObject.disabled=false;
  this.eventObject.disabled=false;
  this.hiddenObject.disabled=false;
  this.container.disabled=false;
};
function ShowCalendar(_){
  var $=$o(_);
  if($){
    $.show();
  }
}
function CalendarEditor(){
}
CalendarEditor.container=null;
CalendarEditor.value=null;
CalendarEditor.maxValue="2500-12-31";
CalendarEditor.minValue="1700-01-01";
CalendarEditor.format="yyyy-MM-dd";
CalendarEditor.showTime=false;
CalendarEditor.MAX_YEAR=2500;
CalendarEditor.MIN_YEAR=1700;
CalendarEditor.lastYear=null;
CalendarEditor.nextYear=null;
CalendarEditor.year=null;
CalendarEditor.month=null;
CalendarEditor.lastMonth=null;
CalendarEditor.nextMonth=null;
CalendarEditor.today=null;
CalendarEditor.body=null;
CalendarEditor.showStatus=false;
CalendarEditor.inited=false;
CalendarEditor.currCell=null;
CalendarEditor.init=function(){
  var E="";
  if(window.isWebkit){
    E="\"eos-button-gc\"";
  }else {
    E="\"eos-button\"";
  }
  CalendarEditor.win=_get_top_window()||window;
  CalendarEditor.doc=CalendarEditor.win.document||document;
  var C=$id("_eos_webcomp_calendar_container",CalendarEditor.doc);
  if(C){
    CalendarEditor.container=C;
  }else {
    var _="";
    if(isIE){
      _+="<iframe style=\"position:absolute;z-index:-1;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>";
    }
    _+="<div id=\"_eos_calendar_editor_container\" onmousedown=\"eventManager.stopPropagation();\" oncontextmenu=\"return false\" class=\"eos-celendar-editor-container\" style=\" width: 228px;\">";
    _+="    <div class=\"eos-celendar-editor-container2\" style=\" width: 100%;\">";
    _+="    <table style=\"width: 100%\" cellspacing=\"0\" cellpadding=\"0\">";
    _+="        <tr>";
    _+="            <td class=\"eos-calendar-head\" style=\"height:26px\">";
    _+="            <div>";
    _+="            <table  cellspacing=\"0\" cellpadding=\"0\" style=\"width: 100%\">";
    _+="                <tr>";
    _+="                    <td>&nbsp;</td>";
    _+="                    <td style=\"width: 11px\">";
    _+="                        <img style=\"cursor:hand;cursor:pointer;\" id=\"_eos_calendar_last_year\" alt=\"\" src=\""+PICTURE_ARROW_LEFT+"\" width=\"11\" height=\"10\" /></td>";
    _+="                    <td align=\"center\" style=\"width: 41px\">";
    _+="                        <input maxLength=\"4\" id=\"_eos_calendar_year\" class=\"eos-calendar-editor-date\" onmouseover=\"eventManager.stopPropagation();\" onmousedown=\"eventManager.stopPropagation();this.select();\" onclick=\"eventManager.stopPropagation();this.select();\" onmouseup=\"eventManager.stopPropagation();this.select();\"   type=\"text\" style=\"width:35px;padding-left:3;height:20px\" />";
    _+="                    </td>";
    _+="                    <td style=\"width: 10px\">";
    _+="                        <img style=\"cursor:hand;cursor:pointer;\" id=\"_eos_calendar_next_year\" alt=\"\" src=\""+PICTURE_ARROW_RIGHT+"\" width=\"11\" height=\"10\" /></td>";
    _+="                    <td width=\"30px\">&nbsp;</td>";
    _+="                    <td style=\"width: 10px\">";
    _+="                        <img style=\"cursor:hand;cursor:pointer;\" id=\"_eos_calendar_last_month\" alt=\"\" src=\""+PICTURE_ARROW_LEFT+"\" width=\"11\" height=\"10\" /></td>";
    _+="                    <td align=\"center\"style=\"width:41px\">";
    _+="                        <input maxLength=\"2\" id=\"_eos_calendar_month\"class=\"eos-calendar-editor-date\" onmouseover=\"eventManager.stopPropagation();\" onmousedown=\"eventManager.stopPropagation();this.select();\" onclick=\"eventManager.stopPropagation();this.select();\" onmouseup=\"eventManager.stopPropagation();this.select();\" type=\"text\" style=\"width:35px;padding-left:9;height:20px\" /></td>";
    _+="                    <td style=\"width: 9px\">";
    _+="                        <img style=\"cursor:hand;cursor:pointer;\" id=\"_eos_calendar_next_month\" alt=\"\" src=\""+PICTURE_ARROW_RIGHT+"\" width=\"11\" height=\"10\" /></td>";
    _+="                    <td>&nbsp;</td>";
    _+="                </tr>";
    _+="            </table>";
    _+="            </div>";
    _+="            </td>";
    _+="        </tr>";
    _+="        <tr>";
    _+="            <td style=\"height: 144px\"><div style=\"height: 144px\">";
    _+="            <table  style=\"width:"+(isIE?"100%":"228px")+";height:24px\" cellspacing=\"0\" cellpadding=\"0\">";
    _+="                ";
    _+="                <tr>";
    _+="                    <td style=\"width: "+(isIE?"31":"33")+"px; height: 24px\" class=\"eos-calendar-editor-week-left\">"+CALENDAR_SUNDAY+"</td>";
    _+="                    <td style=\"width: "+(isIE?"31":"32")+"px; height: 24px\" class=\"eos-calendar-editor-week\">"+CALENDAR_MONDAY+"</td>";
    _+="                    <td style=\"width: "+(isIE?"31":"33")+"px; height: 24px\" class=\"eos-calendar-editor-week\">"+CALENDAR_TUESDAY+"</td>";
    _+="                    <td style=\"width: 31px; height: 24px\" class=\"eos-calendar-editor-week\">"+CALENDAR_WEDNESDAY+"</td>";
    _+="                    <td style=\"width: 31px; height: 24px\" class=\"eos-calendar-editor-week\">"+CALENDAR_THURSDAY+"</td>";
    _+="                    <td style=\"width: 31px; height: 24px\" class=\"eos-calendar-editor-week\">"+CALENDAR_FRIDAY+"</td>";
    _+="                    <td style=\"width: "+(isIE?"32":"33")+"px; height: 24px\" class=\"eos-calendar-editor-week-right\">"+CALENDAR_SATURDAY+"</td>";
    _+="                </tr>";
    _+="                </table>";
    _+="            <table id=\"_eos_calendar_editor_body\" style=\"width:224;height:120px;table-layout:fixed\" cellspacing=\"0\" cellpadding=\"0\" class=\"eos-calendar-editor-body\">";
    if(isIE){
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day0\" style=\"width: 32px; height: 18px\" >25</td>";
      _+="                    <td id=\"_eos_calendar_day1\" style=\"width: 32px; height: 18px\" >26</td>";
      _+="                    <td id=\"_eos_calendar_day2\" style=\"width: 32px; height: 18px\" >27</td>";
      _+="                    <td id=\"_eos_calendar_day3\" style=\"width: 32px; height: 18px\" >28</td>";
      _+="                    <td id=\"_eos_calendar_day4\" style=\"width: 32px; height: 18px\" >29</td>";
      _+="                    <td id=\"_eos_calendar_day5\" style=\"width: 32px; height: 18px\" >30</td>";
      _+="                    <td id=\"_eos_calendar_day6\" style=\"width: 32px; height: 18px\" >1</td>";
      _+="                </tr>";
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day7\" style=\"width: 32px; height: 18px\" >2</td>";
      _+="                    <td id=\"_eos_calendar_day8\" style=\"width: 32px; height: 18px\" >3</td>";
      _+="                    <td id=\"_eos_calendar_day9\" style=\"width: 32px; height: 18px\" >4</td>";
      _+="                    <td id=\"_eos_calendar_day10\" style=\"width: 32px; height: 18px\" >5</td>";
      _+="                    <td id=\"_eos_calendar_day11\" style=\"width: 32px; height: 18px\" >6</td>";
      _+="                    <td id=\"_eos_calendar_day12\" style=\"width: 32px; height: 18px\" >7</td>";
      _+="                    <td id=\"_eos_calendar_day13\" style=\"width: 32px; height: 18px\" >8</td>";
      _+="                </tr>";
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day14\" style=\"width: 32px; height: 18px\" >9</td>";
      _+="                    <td id=\"_eos_calendar_day15\" style=\"width: 32px; height: 18px\" >10</td>";
      _+="                    <td id=\"_eos_calendar_day16\" style=\"width: 32px; height: 18px\" >11</td>";
      _+="                    <td id=\"_eos_calendar_day17\" style=\"width: 32px; height: 18px\" >12</td>";
      _+="                    <td id=\"_eos_calendar_day18\" style=\"width: 32px; height: 18px\" >13</td>";
      _+="                    <td id=\"_eos_calendar_day19\" style=\"width: 32px; height: 18px\" >14</td>";
      _+="                    <td id=\"_eos_calendar_day20\" style=\"width: 32px; height: 18px\" >15</td>";
      _+="                </tr>";
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day21\" style=\"width: 32px; height: 18px\" >16</td>";
      _+="                    <td id=\"_eos_calendar_day22\" style=\"width: 32px; height: 18px\" >17</td>";
      _+="                    <td id=\"_eos_calendar_day23\" style=\"width: 32px; height: 18px\" >18</td>";
      _+="                    <td id=\"_eos_calendar_day24\" style=\"width: 32px; height: 18px\" >19</td>";
      _+="                    <td id=\"_eos_calendar_day25\" style=\"width: 32px; height: 18px\" >20</td>";
      _+="                    <td id=\"_eos_calendar_day26\" style=\"width: 32px; height: 18px\" >21</td>";
      _+="                    <td id=\"_eos_calendar_day27\" style=\"width: 32px; height: 18px\" >22</td>";
      _+="                </tr>";
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day28\" style=\"width: 32px; height: 18px\" >23</td>";
      _+="                    <td id=\"_eos_calendar_day29\" style=\"width: 32px; height: 18px\" >24</td>";
      _+="                    <td id=\"_eos_calendar_day30\" style=\"width: 32px; height: 18px\" >25</td>";
      _+="                    <td id=\"_eos_calendar_day31\" style=\"width: 32px; height: 18px\" >26</td>";
      _+="                    <td id=\"_eos_calendar_day32\" style=\"width: 32px; height: 18px\" >27</td>";
      _+="                    <td id=\"_eos_calendar_day33\" style=\"width: 32px; height: 18px\" >28</td>";
      _+="                    <td id=\"_eos_calendar_day34\" style=\"width: 32px; height: 18px\" >29</td>";
      _+="                </tr>";
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day35\" style=\"width: 32px; height: 18px\" >30</td>";
      _+="                    <td id=\"_eos_calendar_day36\" style=\"width: 32px; height: 18px\" >1</td>";
      _+="                    <td id=\"_eos_calendar_day37\" style=\"width: 32px; height: 18px\" >2</td>";
      _+="                    <td id=\"_eos_calendar_day38\" style=\"width: 32px; height: 18px\" >3</td>";
      _+="                    <td id=\"_eos_calendar_day39\" style=\"width: 32px; height: 18px\" >4</td>";
      _+="                    <td id=\"_eos_calendar_day40\" style=\"width: 32px; height: 18px\" >5</td>";
      _+="                    <td id=\"_eos_calendar_day41\" style=\"width: 32px; height: 18px\" >6</td>";
      _+="                </tr>";
    }else {
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day0\" style=\"width: 32px; height: 18px\" >25</td>";
      _+="                    <td id=\"_eos_calendar_day1\" style=\"width: 32px; height: 18px\" >26</td>";
      _+="                    <td id=\"_eos_calendar_day2\" style=\"width: 32px; height: 18px\" >27</td>";
      _+="                    <td id=\"_eos_calendar_day3\" style=\"width: 31px; height: 18px\" >28</td>";
      _+="                    <td id=\"_eos_calendar_day4\" style=\"width: 31px; height: 18px\" >29</td>";
      _+="                    <td id=\"_eos_calendar_day5\" style=\"width: 31px; height: 18px\" >30</td>";
      _+="                    <td id=\"_eos_calendar_day6\" style=\"width: 31px; height: 18px\" >1</td>";
      _+="                </tr>";
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day7\" style=\"width: 32px; height: 18px\" >2</td>";
      _+="                    <td id=\"_eos_calendar_day8\" style=\"width: 32px; height: 18px\" >3</td>";
      _+="                    <td id=\"_eos_calendar_day9\" style=\"width: 32px; height: 18px\" >4</td>";
      _+="                    <td id=\"_eos_calendar_day10\" style=\"width: 31px; height: 18px\" >5</td>";
      _+="                    <td id=\"_eos_calendar_day11\" style=\"width: 31px; height: 18px\" >6</td>";
      _+="                    <td id=\"_eos_calendar_day12\" style=\"width: 31px; height: 18px\" >7</td>";
      _+="                    <td id=\"_eos_calendar_day13\" style=\"width: 31px; height: 18px\" >8</td>";
      _+="                </tr>";
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day14\" style=\"width: 32px; height: 18px\" >9</td>";
      _+="                    <td id=\"_eos_calendar_day15\" style=\"width: 32px; height: 18px\" >10</td>";
      _+="                    <td id=\"_eos_calendar_day16\" style=\"width: 32px; height: 18px\" >11</td>";
      _+="                    <td id=\"_eos_calendar_day17\" style=\"width: 31px; height: 18px\" >12</td>";
      _+="                    <td id=\"_eos_calendar_day18\" style=\"width: 31px; height: 18px\" >13</td>";
      _+="                    <td id=\"_eos_calendar_day19\" style=\"width: 31px; height: 18px\" >14</td>";
      _+="                    <td id=\"_eos_calendar_day20\" style=\"width: 31px; height: 18px\" >15</td>";
      _+="                </tr>";
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day21\" style=\"width: 32px; height: 18px\" >16</td>";
      _+="                    <td id=\"_eos_calendar_day22\" style=\"width: 32px; height: 18px\" >17</td>";
      _+="                    <td id=\"_eos_calendar_day23\" style=\"width: 32px; height: 18px\" >18</td>";
      _+="                    <td id=\"_eos_calendar_day24\" style=\"width: 31px; height: 18px\" >19</td>";
      _+="                    <td id=\"_eos_calendar_day25\" style=\"width: 31px; height: 18px\" >20</td>";
      _+="                    <td id=\"_eos_calendar_day26\" style=\"width: 31px; height: 18px\" >21</td>";
      _+="                    <td id=\"_eos_calendar_day27\" style=\"width: 31px; height: 18px\" >22</td>";
      _+="                </tr>";
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day28\" style=\"width: 32px; height: 18px\" >23</td>";
      _+="                    <td id=\"_eos_calendar_day29\" style=\"width: 32px; height: 18px\" >24</td>";
      _+="                    <td id=\"_eos_calendar_day30\" style=\"width: 32px; height: 18px\" >25</td>";
      _+="                    <td id=\"_eos_calendar_day31\" style=\"width: 31px; height: 18px\" >26</td>";
      _+="                    <td id=\"_eos_calendar_day32\" style=\"width: 31px; height: 18px\" >27</td>";
      _+="                    <td id=\"_eos_calendar_day33\" style=\"width: 31px; height: 18px\" >28</td>";
      _+="                    <td id=\"_eos_calendar_day34\" style=\"width: 31px; height: 18px\" >29</td>";
      _+="                </tr>";
      _+="                <tr>";
      _+="                    <td id=\"_eos_calendar_day35\" style=\"width: 32px; height: 18px\" >30</td>";
      _+="                    <td id=\"_eos_calendar_day36\" style=\"width: 32px; height: 18px\" >1</td>";
      _+="                    <td id=\"_eos_calendar_day37\" style=\"width: 32px; height: 18px\" >2</td>";
      _+="                    <td id=\"_eos_calendar_day38\" style=\"width: 31px; height: 18px\" >3</td>";
      _+="                    <td id=\"_eos_calendar_day39\" style=\"width: 31px; height: 18px\" >4</td>";
      _+="                    <td id=\"_eos_calendar_day40\" style=\"width: 31px; height: 18px\" >5</td>";
      _+="                    <td id=\"_eos_calendar_day41\" style=\"width: 31px; height: 18px\" >6</td>";
      _+="                </tr>";
    }
    _+="            </table>";
    _+="            </div></td>";
    _+="        </tr>";
    _+="        <tr id=\"_eos_calendar_time_container\">";
    _+="            <td style=\"height: 26px\" align=\"left\" class=\"eos-calendar-time\">";
    _+="              <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tr><td width=\"150px\"><div style=\"float:left\" align=\"left\" id=\"_eos_calendar_time_editor_container\"></div></td>";
    _+="              <td align=\"right\">";
    _+="              </td></tr></table>";
    _+="            </td>";
    _+="        </tr>";
    _+="        <tr>";
    _+="            <td class=\"eos-calendar-bottom\" style=\"height: 30px\" align=\"right\">";
    _+="<div  id=\"_eos_calendar_ok\" class="+E+" onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">"+CALENDAR_BUTTON_OK+"</div></div>&nbsp;";
    _+="<div  id=\"_eos_calendar_today\" class="+E+" onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">"+CALENDAR_TODAY+"</div></div>&nbsp;";
    _+="<div  id=\"_eos_calendar_clear\" class="+E+" onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">"+CLEAR+"</div></div>";
    _+="            </td>";
    _+="        </tr>        ";
    _+="    </table>";
    _+="    </div>";
    _+="</div>";
    var B=$create("div",CalendarEditor.doc);
    B.onmousedown=function(){
      eventManager.stopPropagation();
    };
    B.style.height="222px";
    B.style.width="228px";
    B.style.position="absolute";
    B.id="_eos_webcomp_calendar_container";
    B.innerHTML=_;
    B.style.display="none";
    CalendarEditor.container=B;
    function $(){
      CalendarEditor.doc.body.appendChild(B);
      CalendarEditor.container.onkeydown=function(){
        var $=eventManager.getKeyCode();
        if($==13){
          if(CalendarEditor.showTime){
            CalendarEditor.onOk();
          }
        }
        if($==27){
          CalendarEditor.hideEditor();
        }
      };
    }
    try{
      $();
    }
    catch(A){
      CalendarEditor.win.eventManager.add(window,"load",$);
    }
    if(document!=CalendarEditor.doc){
      moveCss(document,CalendarEditor.doc);
    }
    if(CalendarEditor.win["createTimeSelect"]){
      var D=CalendarEditor.win["createTimeSelect"]("_eos_calendar_time_editor");
      CalendarEditor.setTimeSelect(D);
      D.init();
    }
  }
  CalendarEditor.okBtn=$id("_eos_calendar_ok",CalendarEditor.doc);
  CalendarEditor.okBtn.onclick=CalendarEditor.onOk;
  CalendarEditor.lastYear=$id("_eos_calendar_last_year",CalendarEditor.doc);
  CalendarEditor.nextYear=$id("_eos_calendar_next_year",CalendarEditor.doc);
  CalendarEditor.year=$id("_eos_calendar_year",CalendarEditor.doc);
  CalendarEditor.month=$id("_eos_calendar_month",CalendarEditor.doc);
  CalendarEditor.lastMonth=$id("_eos_calendar_last_month",CalendarEditor.doc);
  CalendarEditor.nextMonth=$id("_eos_calendar_next_month",CalendarEditor.doc);
  CalendarEditor.today=$id("_eos_calendar_today",CalendarEditor.doc);
  CalendarEditor.clear=$id("_eos_calendar_clear",CalendarEditor.doc);
  CalendarEditor.body=$id("_eos_calendar_editor_body",CalendarEditor.doc);
  CalendarEditor.timeContainer=$id("_eos_calendar_time_container",CalendarEditor.doc);
  CalendarEditor.timeSelect=CalendarEditor.getTimeSelect();
  CalendarEditor.lastYear.onmouseover=function(){
    this.src=PICTURE_ARROW_LEFT_OVER;
  };
  CalendarEditor.lastYear.onmouseout=function(){
    this.src=PICTURE_ARROW_LEFT;
  };
  CalendarEditor.nextYear.onmouseover=function(){
    this.src=PICTURE_ARROW_RIGHT_OVER;
  };
  CalendarEditor.nextYear.onmouseout=function(){
    this.src=PICTURE_ARROW_RIGHT;
  };
  CalendarEditor.lastMonth.onmouseover=function(){
    this.src=PICTURE_ARROW_LEFT_OVER;
  };
  CalendarEditor.lastMonth.onmouseout=function(){
    this.src=PICTURE_ARROW_LEFT;
  };
  CalendarEditor.nextMonth.onmouseover=function(){
    this.src=PICTURE_ARROW_RIGHT_OVER;
  };
  CalendarEditor.nextMonth.onmouseout=function(){
    this.src=PICTURE_ARROW_RIGHT;
  };
  if(isFF){
    buttonForFF(CalendarEditor.okBtn);
    buttonForFF(CalendarEditor.today);
    buttonForFF(CalendarEditor.clear);
  }
};
function calendarUnload(){
  try{
    CalendarEditor.hideEditor();
  }
  catch(_){
    var $=$id("_eos_webcomp_calendar_container",CalendarEditor.doc);
    $.style.display="none";
  }
}
eventManager.add(window,"unload",calendarUnload);
CalendarEditor.initEvent=function(){
};
CalendarEditor.setTimeSelect=function($){
  CalendarEditor.win["_eos_calendar_time_select"]=$;
};
CalendarEditor.getTimeSelect=function(){
  return CalendarEditor.win["_eos_calendar_time_select"];
};
CalendarEditor.showEditor=function(){
  if(CalendarEditor.showTime){
    CalendarEditor.timeContainer.style.display="";
    CalendarEditor.okBtn.style.display="";
    if(isFF){
      CalendarEditor.container.style.height="226px";
      CalendarEditor.container.firstChild.style.height="226px";
    }
  }else {
    CalendarEditor.timeContainer.style.display="none";
    CalendarEditor.okBtn.style.display="none";
    if(isFF){
      CalendarEditor.container.style.height="200px";
      CalendarEditor.container.firstChild.style.height="200px";
    }
  }
  CalendarEditor.initCalendarEvent();
  CalendarEditor.freshDate(CalendarEditor.value);
  CalendarEditor.freshTimeComp();
  CalendarEditor.showStatus=true;
  CalendarEditor.container.style.display="";
  var $=$id("_eos_calendar_editor_container",CalendarEditor.doc);
  initShadow($,CalendarEditor.doc);
};
CalendarEditor.initCalendarEvent=function(){
  CalendarEditor.lastYear.onclick=function(){
    CalendarEditor.changeYear(CalendarEditor.year.value-1);
  };
  CalendarEditor.nextYear.onclick=function(){
    CalendarEditor.changeYear(CalendarEditor.year.value*1+1);
  };
  CalendarEditor.year.onkeyup=function(){
    CalendarEditor.changeYear(CalendarEditor.year.value);
  };
  CalendarEditor.year.onblur=function(){
    CalendarEditor.changeYear(CalendarEditor.year.value);
  };
  CalendarEditor.month.onkeyup=function(){
    CalendarEditor.changeMonth(CalendarEditor.month.value);
  };
  CalendarEditor.month.onblur=function(){
    CalendarEditor.changeMonth(CalendarEditor.month.value);
  };
  CalendarEditor.lastMonth.onclick=function(){
    CalendarEditor.changeMonth(dateToStringValue(CalendarEditor.month.value-1));
  };
  CalendarEditor.nextMonth.onclick=function(){
    CalendarEditor.changeMonth(dateToStringValue(CalendarEditor.month.value*1+1));
  };
  CalendarEditor.today.onclick=function(){
    CalendarEditor.returnValue(new Date());
  };
  CalendarEditor.clear.onclick=function(){
    var $=CalendarEditor.getCalendar();
    if($){
      $.inputObject.value="";
      $.hiddenObject.value="";
      $.value=null;
    }
    CalendarEditor.hideEditor();
  };
};
CalendarEditor.hideEditor=function(){
  if(CalendarEditor.container){
    CalendarEditor.container.style.display="none";
    CalendarEditor.showStatus=false;
  }
};
CalendarEditor.setPosition=function(_,C,D,$){
  CalendarEditor.container.style.position="absolute";
  var A=getMaxZindex(CalendarEditor.doc);
  if(CalendarEditor.container.style.zIndex!=A){
    CalendarEditor.container.style.zIndex=A;
  }
  var B;
  if(isIE){
    B=CalendarEditor.doc.body.offsetHeight+CalendarEditor.doc.body.scrollTop;
  }else {
    B=CalendarEditor.win.screen.availHeight+CalendarEditor.doc.body.scrollTop;
  }
  if((C+228)>B){
    if(CalendarEditor.showTime){
      if(C-258>=0){
        C=C-258;
      }
    }else {
      if(C-228>=0){
        C=C-228;
      }
    }
  }
  CalendarEditor.container.style.left=_;
  CalendarEditor.container.style.top=C;
};
CalendarEditor.reFreshEditor=function($){
  CalendarEditor.maxValue=$.maxValue;
  CalendarEditor.minValue=$.minValue;
  CalendarEditor.format=$.format;
  CalendarEditor.value=$.value;
  CalendarEditor.showTime=$.showTime;
};
CalendarEditor.setCurrEditor=function($){
  CalendarEditor.win["_eos_curr_calendar"]=$;
};
CalendarEditor.getCalendar=function($){
  return CalendarEditor.win["_eos_curr_calendar"];
};
CalendarEditor.getMaxYear=function(){
  if(isDate(CalendarEditor.maxValue,CalendarEditor.format)){
    var $=stringToDate(CalendarEditor.maxValue,CalendarEditor.format);
    return $.getFullYear();
  }
  return CalendarEditor.MAX_YEAR;
};
CalendarEditor.getMaxMonth=function(){
  if(isDate(CalendarEditor.maxValue,CalendarEditor.format)){
    var $=stringToDate(CalendarEditor.maxValue,CalendarEditor.format);
    return $.getMonth();
  }
  return 11;
};
CalendarEditor.getMaxDay=function(){
  if(isDate(CalendarEditor.maxValue,CalendarEditor.format)){
    var $=stringToDate(CalendarEditor.maxValue,CalendarEditor.format);
    return $.getDate();
  }
  return 31;
};
CalendarEditor.getMinYear=function(){
  if(isDate(CalendarEditor.minValue,CalendarEditor.format)){
    var $=stringToDate(CalendarEditor.minValue,CalendarEditor.format);
    return $.getFullYear();
  }
  return CalendarEditor.MIN_YEAR;
};
CalendarEditor.getMinMonth=function(){
  if(isDate(CalendarEditor.minValue,CalendarEditor.format)){
    var $=stringToDate(CalendarEditor.minValue,CalendarEditor.format);
    return $.getMonth();
  }
  return 0;
};
CalendarEditor.getMinDay=function(){
  if(isDate(CalendarEditor.minValue,CalendarEditor.format)){
    var $=stringToDate(CalendarEditor.minValue,CalendarEditor.format);
    return $.getDate();
  }
  return 1;
};
CalendarEditor.isHoliday=function(A,_,$){
  var B=new Date(A,_,$);
  B.setFullYear(A);
  return (B.getDay()==6||B.getDay()==0);
};
CalendarEditor.freshTimeComp=function(){
  if(!CalendarEditor.showTime){
    return ;
  }
  var $=CalendarEditor.value.getHours(),_=CalendarEditor.value.getMinutes(),A=CalendarEditor.value.getSeconds();
  CalendarEditor.timeSelect.setHours($);
  CalendarEditor.timeSelect.setMinutes(_);
  CalendarEditor.timeSelect.setSeconds(A);
};
CalendarEditor.refreshTime=function($){
  if(CalendarEditor.showTime){
    $.setHours(CalendarEditor.timeSelect.getHours());
    $.setMinutes(CalendarEditor.timeSelect.getMinutes());
    $.setSeconds(CalendarEditor.timeSelect.getSeconds());
  }
  return $;
};
CalendarEditor.freshHeader=function(){
  CalendarEditor.month.value=dateToStringValue(CalendarEditor.value.getMonth()+1);
  CalendarEditor.year.value=CalendarEditor.value.getFullYear();
};
CalendarEditor.changeMonth=function(C){
  if(isNaN(C)){
    return ;
  }
  if(C==""){
    return ;
  }
  if(C.length!=2){
    return ;
  }
  C=C-1;
  var F=CalendarEditor.getMaxMonth(),E=CalendarEditor.getMinMonth(),$=CalendarEditor.value.getFullYear(),B=CalendarEditor.getMaxYear(),D=CalendarEditor.getMinYear();
  if(C>F&&$==B){
    C=F;
  }
  if(C<E&&$==D){
    C=E;
  }
  var A=CalendarEditor.value.getDate(),_=new Date($,C,A);
  _.setFullYear($);
  _=CalendarEditor.refreshTime(_);
  CalendarEditor.freshDate(dateToString(_,CalendarEditor.format));
};
CalendarEditor.changeYear=function(B){
  if(isNaN(B)){
    return ;
  }
  if(B.length<=3){
    return ;
  }
  var A=CalendarEditor.getMaxYear(),C=CalendarEditor.getMinYear();
  if(B>A){
    B=A;
  }
  if(B<C){
    B=C;
  }
  var D=CalendarEditor.value.getMonth(),_=CalendarEditor.value.getDate();
  if(D==1&&_==29){
    if(B%4!=0){
      _=28;
    }
  }
  var $=new Date(B,D,_);
  $.setFullYear(B);
  $=CalendarEditor.refreshTime($);
  CalendarEditor.freshDate(dateToString($,CalendarEditor.format));
};
CalendarEditor.onClick=function($){
  if($.innerHTML!=""){
    var _=$.value;
    if(CalendarEditor.showTime){
      if(CalendarEditor.currCell){
        removeClass(CalendarEditor.currCell,"eos-calendar-editor-currday");
      }
      addClass($,"eos-calendar-editor-currday");
      CalendarEditor.currCell=$;
      CalendarEditor.value=_;
    }else {
      CalendarEditor.returnValue(_);
    }
  }
};
CalendarEditor.onOk=function(){
  if(CalendarEditor.value!=null){
    CalendarEditor.returnValue(CalendarEditor.value);
  }
};
CalendarEditor.outClick=function($){
  try{
    CalendarEditor.hideEditor();
  }
  catch(_){
  }
};
CalendarEditor.returnValue=function(_date){
  _date=CalendarEditor.refreshTime(_date);
  if(isDate(CalendarEditor.maxValue,CalendarEditor.format)){
    var maxDate=stringToDate(CalendarEditor.maxValue,CalendarEditor.format);
    if(_date-maxDate>0){
      return ;
    }
  }
  if(isDate(CalendarEditor.minValue,CalendarEditor.format)){
    var minDate=stringToDate(CalendarEditor.minValue,CalendarEditor.format);
    if(_date-minDate<0){
      return ;
    }
  }
  var calendar=CalendarEditor.getCalendar();
  if(calendar){
    calendar.setValue(_date);
    if(calendar.onSelectFunc){
      try{
        eval(calendar.onSelectFunc+"(_date);");
      }
      catch(e){
        alert(e);
      }
    }
    calendar.validate();
  }
  CalendarEditor.hideEditor();
};
CalendarEditor.hideTime=function(){
};
CalendarEditor.isShow=function(){
  return CalendarEditor.showStatus;
};
CalendarEditor.freshDate=function(F){
  var P=new Array(31,30,31,30,31,30,31,31,30,31,30,31),O=new Date();
  if(isDate(F,CalendarEditor.format)){
    O=stringToDate(F,CalendarEditor.format);
  }
  var I=null,H=null;
  if(isDate(CalendarEditor.maxValue,CalendarEditor.format)){
    I=stringToDate(CalendarEditor.maxValue,CalendarEditor.format);
    if(O-I>0){
      O=I;
    }
  }
  if(isDate(CalendarEditor.minValue,CalendarEditor.format)){
    H=stringToDate(CalendarEditor.minValue,CalendarEditor.format);
    if(O-H<0){
      O=H;
    }
  }
  CalendarEditor.value=O;
  CalendarEditor.freshHeader();
  var Q=O.getFullYear(),B=O.getMonth(),D=1,G=new Date(Q,B,1).getDay(),$=B==0?Q-1:Q,S=B==0?11:B-1,N=P[S];
  if(S==1){
    N=(($%4==0)&&($%100!=0)||($%400==0))?29:28;
  }
  N-=G-1;
  var M=1;
  P[1]=((Q%4==0)&&(Q%100!=0)||(Q%400==0))?29:28;
  for(i=0;i<42;i++){
    var R=$id("_eos_calendar_day"+i,CalendarEditor.doc);
    R.onmouseover=function(){
      addClass(this,"eos-calendar-editor-overday");
    };
    R.onmouseout=function(){
      removeClass(this,"eos-calendar-editor-overday");
    };
    R.onclick=function(){
      CalendarEditor.onClick(this);
    };
    R.className="eos-calendar-editor-day";
    if(i<G){
      var L=new Date(Q,B-1,N);
      R.innerHTML=N;
      R.title=dateToString(L,CALENDAR_DEFAULT_FORMAT);
      R.value=L;
      R.className="eos-calendar-editor-moreday";
      N++;
    }else {
      if(D>P[B]){
        var K=new Date(Q,B+1,M);
        R.innerHTML=M;
        R.title=dateToString(K,CALENDAR_DEFAULT_FORMAT);
        R.value=K;
        R.className="eos-calendar-editor-moreday";
        M++;
      }else {
        if(i>=new Date(Q,B,1).getDay()&&D<=P[B]){
          var T=new Date(Q,B,D);
          T.setFullYear(Q);
          R.title=dateToString(T,CALENDAR_DEFAULT_FORMAT);
          R.value=T;
          R.innerHTML=D;
          var E=new Date(),A=R.value;
          if(CalendarEditor.isHoliday(Q,B,D)){
            R.className="eos-calendar-editor-holiday";
          }
          D++;
        }else {
          R.innerHTML="";
          R.title="";
        }
      }
    }
    if(R.value){
      E=new Date();
      if(R.value.getYear()==E.getYear()&&R.value.getMonth()==E.getMonth()&&R.value.getDate()==E.getDate()){
        addClass(R,"eos-calendar-editor-today");
        R.onmouseover=null;
        R.onmouseout=null;
      }
      var _=CalendarEditor.value;
      if(R.value.getYear()==_.getYear()&&R.value.getMonth()==_.getMonth()&&R.value.getDate()==_.getDate()){
        addClass(R,"eos-calendar-editor-currday");
        CalendarEditor.currCell=R;
        R.onmouseover=null;
        R.onmouseout=null;
      }else {
        removeClass(R,"eos-calendar-editor-currday");
      }
      var C=false;
      if(I!=null){
        if(R.value-I>0){
          C=true;
        }
      }
      if(H!=null){
        var J=CalendarEditor.refreshTime(R.value);
        if(J-H<0){
          C=true;
        }
      }
      if(C){
        R.onclick=null;
        R.onmouseover=null;
        R.onmouseout=null;
        R.title="";
      }
    }
  }
};
function createTimeSelect(_){
  var $=new TimeSelect(_);
  return $;
}
function f_check_calendar(_){
  if(_.id){
    var A=_.id;
    if(A.indexOf("_input")>0){
      A=A.replace("_input","");
      var $=$id(A);
      if($){
        return $.validate();
      }
    }
  }
  return true;
}
function LookUp($){
  this.id=$;
  PageControl.add($,this);
  this.value=null;
  this.lookupBtn=null;
  this.lookupText=null;
  this.lookupUrl=null;
  this.lookupParam=null;
  this.lookupHidden=null;
  this.width=300;
  this.height=200;
  this.left=null;
  this.top=null;
  this.center=true;
  this.buttonImg=null;
  this.params=[];
  this.displayValue=null;
  this.container=null;
  this.onReturnFunc=null;
  this.useIeModel=false;
  this.dialogTitle=null;
  this.disabled=false;
  this.allowInput=true;
  this.readOnly=false;
}
LookUp.prototype.init=function(){
  this.container=$id(this.id+"_container");
  this.lookupHidden=$id(this.id+"_hidden");
  this.lookupText=$id(this.id+"_input");
  this.lookupBtn=$id(this.id+"_button");
  this.button=this.lookupBtn;
  this.text=this.lookupText;
  this.hidden=this.lookupHidden;
  if(this.lookupWidth!=null){
    this.text.style.width=this.lookupWidth;
  }
  var _=this;
  this.lookupBtn.src=contextPath+"/common/skins/skin0/images/lookup/lookup_button.gif";
  this.lookupBtn.onmouseover=function(){
    if(_.getDisabled()||_.getReadOnly()){
      return ;
    }
    this.src=contextPath+"/common/skins/skin0/images/lookup/lookup_button_over.gif";
  };
  this.lookupBtn.onmouseout=function(){
    if(_.getDisabled()||_.getReadOnly()){
      return ;
    }
    this.src=contextPath+"/common/skins/skin0/images/lookup/lookup_button.gif";
  };
  this.lookupBtn.onmousedown=function(){
    if(_.getDisabled()||_.getReadOnly()){
      return ;
    }
    this.src=contextPath+"/common/skins/skin0/images/lookup/lookup_button_down.gif";
  };
  this.lookupBtn.onmouseup=function(){
    if(_.getDisabled()||_.getReadOnly()){
      return ;
    }
    _.lookupBtn.src=contextPath+"/common/skins/skin0/images/lookup/lookup_button.gif";
    _.show();
  };
  function $(){
    if(_.lookupText.value!=_.displayValue){
      _.displayValue=_.lookupText.value;
      _.value=_.lookupText.value;
      _.lookupHidden.value=_.value;
    }
  }
  this.setReadOnly(this.readOnly);
  this.setDisabled(this.disabled);
  eventManager.add(this.lookupText,"keyup",$);
};
LookUp.prototype.setReadOnly=function($){
  this.readOnly=$;
  this.lookupText.readOnly=$||!this.allowInput;
  if($){
    this.lookupBtn.style.cursor="default";
  }else {
    this.lookupBtn.style.cursor="pointer";
    this.lookupBtn.style.cursor="hand";
  }
};
LookUp.prototype.getReadOnly=function(){
  return this.readOnly;
};
LookUp.prototype.getValue=function(){
  this.refreshValue();
  return this.value;
};
LookUp.prototype.setValue=function($){
  this.value=$;
  this.displayValue=$;
  this.refreshInput();
};
LookUp.prototype.setDisplayValue=function($){
  this.displayValue=$;
  this.refreshInput();
};
LookUp.prototype.setFocus=function(){
};
LookUp.prototype.lostFocus=function(){
};
LookUp.prototype.show=function(){
  var lookup=this;
  if(this.disabled){
    return ;
  }
  if(lookup.beforeOpenDialog){
    if(lookup.beforeOpenDialog(lookup)===false){
      return ;
    }
  }
  this.refreshValue();
  var urlStr=this.getParamURL(),argument=[this.value,this.displayValue];
  function callBack(returnValue){
    try{
      if(lookup.onReturnFunc){
        var func=lookup.onReturnFunc;
        if((typeof lookup.onReturnFunc)=="string"){
          func=eval(lookup.onReturnFunc);
        }
        if(func(returnValue)){
          lookup.value=returnValue[0];
          lookup.displayValue=returnValue[1];
        }
      }else {
        lookup.value=returnValue[0];
        lookup.displayValue=returnValue[1];
      }
    }
    catch(e){
      $handle(e);
      $error("returnValue of dialog is not a array");
    }
    lookup.refreshInput();
  }
  if(this.useIeModel){
    var retValue=window.showModalDialog(urlStr,argument,"width:"+this.width+";"+"height:"+this.height+";"+"left:"+this.left+";"+"top:"+this.top+";");
    callBack(retValue);
  }else {
    showModal(urlStr,argument,callBack,this.width,this.height,this.left||"",this.top||"",this.dialogTitle);
  }
};
LookUp.prototype.refreshInput=function(){
  var $=this.displayValue!==null&&this.displayValue!==undefined?this.displayValue:this.value;
  this.lookupHidden.value=this.value;
  this.lookupText.value=$;
};
LookUp.prototype.refreshValue=function(){
  this.value=this.lookupHidden.value;
  this.displayValue=this.lookupText.value;
};
LookUp.prototype.getParamURL=function(){
  var A="";
  for(var _=0;_<this.params.length;_++){
    var B=this.params[_];
    A+="&"+B.key+"="+B.value;
  }
  var $=addContextPath(this.lookupUrl);
  if($.indexOf("?")>-1){
    $+=A;
  }else {
    $+="?"+A.replace("&","");
  }
  return $;
};
LookUp.prototype.addParam=function($,_){
  this.params.push({key:$,value:encodeURIComponent(_)});
};
LookUp.prototype.clearParam=function(){
  this.params=[];
};
LookUp.prototype.setDisabled=function($){
  this.disabled=$;
  if($){
    this.lookupText.disabled=true;
    this.lookupHidden.disabled=true;
    this.lookupBtn.style.cursor="default";
  }else {
    this.lookupText.disabled=false;
    this.lookupHidden.disabled=false;
    this.lookupBtn.style.cursor="pointer";
    this.lookupBtn.style.cursor="hand";
  }
};
LookUp.prototype.getDisabled=function(){
  return this.disabled;
};
LookUp.prototype.setPosition=function($,A,B,C){
  if(this.container){
    this.container.style.display="";
    this.container.style.position="absolute";
    this.container.style.left=$;
    this.container.style.top=A;
    var _=getMaxZindex(document);
    if(this.container.style.zIndex!=_){
      this.container.style.zIndex=_;
    }
    this.lookupText.style.width=B-17;
    this.lookupText.style.height=C;
    this.container.style.width=B;
    this.container.style.height=C;
    this.lookupBtn.style.height=C;
  }
};
LookUp.prototype.hideEditor=function(){
  this.container.style.display="none";
  this.lookupBtn.style.display="none";
};
LookUp.prototype.showEditor=function(){
  this.container.style.display="";
  this.lookupBtn.style.display="";
};
LookUp.prototype.validate=function(){
  return true;
};
LookUp.prototype.isFocus=function(){
  return false;
};
LookUp.prototype.getDisplayValue=function($){
  if($==this.value){
    return this.displayValue;
  }
  return $;
};
function MultiBox($){
  this.id=$;
  this.value=null;
  this.container=$id(this.id+"_container");
  this.checkboxes=[];
  var B=this.container.getElementsByTagName("input");
  for(var _=0;_<B.length;_++){
    var A=B[_];
    if(A.type=="checkbox"){
      this.checkboxes.push(A);
    }
  }
  this.hiddenInput=null;
  this.splitChar=",";
  this.jsonObj=null;
  PageControl.add($,this);
}
MultiBox.prototype.init=function(){
  this.hiddenInput=$id(this.id+"_hidden");
  this.initEvent();
};
MultiBox.prototype.initEvent=function(){
  var C=this;
  function $(){
    C.refreshValue();
  }
  function _(){
    var A=eventManager.getEvent(),D=A.keyCode;
    if(!(D==37||D==39)){
      return ;
    }
    var B=C.checkboxes;
    for(var $=0;$<B.length;$++){
      var _=B[$];
      if((isIE?document.activeElement:A.explicitOriginalTarget)==_&&_.type=="checkbox"){
        if(D==37){
          if($==0){
            B[B.length-1].focus();
          }else {
            B[$-1].focus();
          }
        }
        if(D==39){
          if($==B.length-1){
            B[0].focus();
          }else {
            B[$+1].focus();
          }
        }
        break ;
      }
    }
  }
  for(var A=0;A<this.checkboxes.length;A++){
    var B=this.checkboxes[A];
    eventManager.add(B,"click",$);
  }
  eventManager.add(this.container,"keyup",_);
};
MultiBox.prototype.refreshInput=function(){
  if(this.hiddenInput){
    this.hiddenInput.value=this.value;
  }
  str=this.splitChar+this.value+this.splitChar;
  for(var $=0;$<this.checkboxes.length;$++){
    var _=this.checkboxes[$],A=this.splitChar+_.value+this.splitChar;
    if(str.indexOf(A)>-1){
      _.checked=true;
    }else {
      _.checked=false;
    }
  }
};
MultiBox.prototype.refreshValue=function(){
  var A="";
  for(var $=0;$<this.checkboxes.length;$++){
    var _=this.checkboxes[$];
    if(_.checked&&_.type=="checkbox"){
      A+=_.value+this.splitChar;
    }
  }
  if(A.length>1){
    A=A.substr(0,A.length-1);
  }
  this.value=A;
  if(this.hiddenInput){
    this.hiddenInput.value=A;
  }
};
MultiBox.prototype.setValue=function($){
  this.value=$;
  this.refreshInput();
};
MultiBox.prototype.getValue=function(){
  this.refreshValue();
  return this.value;
};
MultiBox.prototype.setFocus=function(){
  this.checkboxes[0].focus();
};
MultiBox.prototype.lostFocus=function(){
};
MultiBox.prototype.showEditor=function(){
  var $=getMaxZindex();
  this.container.style.zIndex=$;
  this.container.style.display="";
  addClass(this.container.firstChild,"dict_comp");
  if(this.isDcEdit==true){
    var _=this.container.firstChild;
    _.style.width=_.firstChild.offsetWidth;
    initShadow(_);
  }
  this.setFocus();
};
MultiBox.prototype.hideEditor=function(){
  this.container.style.display="none";
};
MultiBox.prototype.setPosition=function($,A,_,B){
  this.container.style.position="absolute";
  this.container.zIndex=9999;
  this.container.style.left=$+"px";
  this.container.style.top=A+"px";
};
MultiBox.prototype.isFocus=function(){
  return true;
};
MultiBox.prototype.validate=function(){
  return true;
};
EOS_DICT_DISPLAY_SEPERATOR=null;
MultiBox.prototype.getDisplayValue=function($){
  if($==null){
    $=this.getValue();
  }
  var C;
  if(EOS_DICT_DISPLAY_SEPERATOR){
    C=EOS_DICT_DISPLAY_SEPERATOR;
  }else {
    C=this.splitChar;
  }
  var _=$.split(C),A="",B=[];
  for(var D=0;D<_.length;D++){
    B.push(this.jsonObj[_[D]]||_[D]);
  }
  A=B.join(C);
  return A;
};
function MultiSelect($){
  this.id=$;
  this.container=$id(this.id+"_container");
  this.container.onmousedown=function(){
    eventManager.stopPropagation();
  };
  this.value=null;
  this.select=$id(this.id+"_select");
  this.hiddenInput=null;
  this.splitChar=",";
  this.jsonObj=null;
  PageControl.add($,this);
}
MultiSelect.prototype.init=function(){
  this.hiddenInput=$id(this.id+"_hidden");
  this.initEvent();
};
MultiSelect.prototype.initEvent=function(){
  var $=this;
  function _(){
    $.refreshValue();
  }
  eventManager.add(this.select,"change",_);
};
MultiSelect.prototype.refreshInput=function(){
  str=this.value+this.splitChar;
  if(this.hiddenInput){
    this.hiddenInput.value=this.value;
  }
  for(var $=0;$<this.select.options.length;$++){
    var _=this.select.options[$],A=_.value+this.splitChar;
    if(str.indexOf(A)>-1){
      _.selected=true;
    }else {
      _.selected=false;
    }
  }
};
MultiSelect.prototype.refreshValue=function(){
  var A="";
  for(var $=0;$<this.select.options.length;$++){
    var _=this.select.options[$];
    if(_.selected){
      A+=_.value+this.splitChar;
    }
  }
  if(A.length>1){
    A=A.substr(0,A.length-1);
  }
  this.value=A;
  if(this.hiddenInput){
    this.hiddenInput.value=A;
  }
};
MultiSelect.prototype.setValue=function($){
  this.hiddenInput.value=$;
  this.refreshInput();
};
MultiSelect.prototype.getValue=function(){
  this.refreshValue();
  return this.value;
};
MultiSelect.prototype.setFocus=function(){
};
MultiSelect.prototype.lostFocus=function(){
};
MultiSelect.prototype.showEditor=function(){
  var $=getMaxZindex();
  this.container.style.zIndex=$;
  this.container.style.display="";
};
MultiSelect.prototype.hideEditor=function(){
  this.container.style.display="none";
};
MultiSelect.prototype.setPosition=function($,A,_,B){
  this.container.style.position="absolute";
  this.container.zIndex=9999;
  this.container.style.left=$+"px";
  this.container.style.top=A+"px";
  this.container.style.width=_+"px";
};
MultiSelect.prototype.isFocus=function(){
  return true;
};
MultiSelect.prototype.validate=function(){
  return true;
};
EOS_DICT_DISPLAY_SEPERATOR=null;
MultiSelect.prototype.getDisplayValue=function(C){
  if(C==null){
    C=this.getValue();
  }
  var A;
  if(EOS_DICT_DISPLAY_SEPERATOR){
    A=EOS_DICT_DISPLAY_SEPERATOR;
  }else {
    A=this.splitChar;
  }
  var $=C.split(A),_="";
  for(var B=0;B<$.length;B++){
    _=_+(this.jsonObj[$[B]]||$[B])+A;
  }
  if(_.length>1){
    _=_.substr(0,_.length-1);
  }
  return _;
};
function PopMenu($){
  this.id=$;
  PageControl.add($,this);
  this.container=null;
  this.subMenuContainer=null;
  this.submenu=[];
  this.canClose=true;
  this.currMenu=null;
  this.level=0;
  this.openLevel=[];
  this.menuContainer=null;
  this.doc=document;
  this.win=window;
  this.onClickFunc=null;
  this.args=null;
  this.isShow=false;
}
PopMenu.prototype.init=function(){
  this.win=_get_top_window()||window;
  this.doc=this.win.document;
  var _=getDocumentId(document);
  this.container=$id(this.id+_+"_container",this.doc);
  if(!this.container){
    this.container=$create("<div id='"+this.id+_+"_container' style='width:164px;overflow:hidden;position:absolute;display:none'></div>",this.doc);
    bodyAddNode(this.container,this.doc);
  }
  this.container.style.width="164px";
  this.container.style.overflow="hidden";
  var B=getMaxZindex(this.win.document);
  this.container.style.zIndex=B+1;
  var E=$create("div",this.doc);
  this.menuContainer=$create("div",this.doc);
  if(isIE){
    this.menuContainer.innerHTML="<iframe style=\"position:absolute;z-index:"+this.zIndex+";width:expression(this.nextSibling.childNodes[0].offsetWidth);height:expression(this.nextSibling.childNodes[0].offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>";
  }
  this.menuContainer.appendChild(this.container);
  var $=$create("ul",this.doc),C=$create("div",this.doc);
  this.shadowdiv=C;
  $.className="eos-popmenu-list";
  this.container.appendChild(C);
  C.appendChild($);
  C.className="eos-popmenu";
  E.appendChild(this.menuContainer);
  bodyAddNode(E,this.doc);
  E.style.zIndex=B+1;
  for(var D=0;D<this.submenu.length;D++){
    var A=this.submenu[D];
    $.appendChild(A.container);
    A.container.style.zIndex=B+1;
    A.zIndex=B+1;
    A.root=this;
    A.level=this.level+1;
    A.init();
  }
  this.initEvent();
  this.openLevel[0]=this;
};
PopMenu.prototype.appendChild=function($){
  this.submenu.push($);
  $.parent=this;
};
PopMenu.prototype.initEvent=function(){
  var _=this;
  function $(){
    _.hide(true);
  }
};
PopMenu.prototype.addObject=function($){
  if(!$){
    return ;
  }
  var _=this;
};
PopMenu.prototype.show=function(){
  PageControl.setFocus(this);
  this.container.style.display="";
  this.isShow=true;
  if(this.isShadowInit!=true){
    if(isFF){
      initShadow(this.shadowdiv,this.doc);
      this.shadowdiv.style.width=this.shadowdiv.parentNode.offsetWidth-7;
    }else {
      initShadowIe(this.shadowdiv,this.doc);
    }
    this.isShadowInit=true;
  }
};
PopMenu.prototype.setPosition=function(_,$){
  this.container.style.left=_+"px";
  this.container.style.top=$+"px";
};
PopMenu.prototype.hide=function(A){
  if(this.canClose||A){
    for(var $=0;$<this.submenu.length;$++){
      var _=this.submenu[$];
      _.hide(A);
    }
    this.container.style.display="none";
    this.isShow=false;
    return true;
  }else {
    return false;
  }
};
PopMenu.prototype.openByLevel=function($){
  $debug($);
  for(var _=0;_<this.openLevel.length;_++){
    var A=this.openLevel[_];
    if(!A){
      continue ;
    }
    if(_<=$){
      A.show();
    }else {
      A.hide(true);
    }
  }
};
function PopMenuItem($){
  this.id=$;
  PageControl.add($,this);
  this.parent=null;
  this.submemu=[];
  this.win=_get_top_window()||window;
  this.doc=this.win.document;
  this.container=$create("li",this.doc);
  this.subMenuContainer=null;
  this.url=null;
  this.name=null;
  this.seperator=false;
  this.params=[];
  this.icon=null;
  this.canClose=true;
  this.currMenu=null;
  this.level=0;
  this.root=null;
  this.onClickFunc=null;
  this.menuKey=null;
  this.onRefreshFunc=null;
  this.zIndex=0;
}
PopMenuItem.prototype.init=function(){
  if(this.seperator){
    var $="<span></span>";
    this.container.className="eos-popmenu-line";
    this.container.innerHTML=$;
    if(isIE){
      this.container.style.height="2px";
    }else {
      this.container.style.height="1px";
    }
  }else {
    this.initContainer();
    this.initEvent();
    this.initSubMenu();
  }
};
PopMenuItem.prototype.initSubMenu=function(){
  if(this.hasChild()){
    var C=$create("div",this.doc);
    this.subMenuContainer=$create("div",this.doc);
    this.subMenuContainer.style.width="164px";
    this.subMenuContainer.style.overflow="hidden";
    if(isIE){
      C.innerHTML="<iframe style=\"position:absolute;z-index:"+this.zIndex+";width:expression(this.nextSibling.childNodes[0].offsetWidth);height:expression(this.nextSibling.childNodes[0].offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>";
    }
    C.appendChild(this.subMenuContainer);
    this.subMenuContainer.style.display="none";
    this.subMenuContainer.style.position="absolute";
    this.subMenuContainer.style.zIndex=parseInt(this.zIndex)+1;
    var $=$create("ul",this.doc),A=$create("div",this.doc);
    $.className="eos-popmenu-list";
    this.shadowdiv=A;
    this.subMenuContainer.appendChild(A);
    A.appendChild($);
    for(var B=0;B<this.submemu.length;B++){
      var _=this.submemu[B];
      _.root=this.root;
      _.level=this.level+1;
      _.container.style.zIndex=parseInt(this.zIndex)+1;
      _.zIndex=this.zIndex+1;
      _.init();
      $.appendChild(_.container);
    }
    this.root.menuContainer.appendChild(C);
    A.className="eos-popmenu";
  }
};
PopMenuItem.prototype.initContainer=function(){
  var $=$create("a",this.doc);
  $.className="eos-popmenu-item";
  $.hidefocus=true;
  $.unselectable="on";
  $.href="#";
  this.container.appendChild($);
  $.innerHTML=this.getNomalDiv();
  this.container.className="eos-popmenu-list-item";
};
PopMenuItem.prototype.getNomalDiv=function(){
  var A="";
  A+="<table cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;height:25px;background-color:transparent;border-style:none;border:-width:0px\">";
  A+="    <tr>";
  A+="    <td  class=\"overLeft\" style=\"width: 5px;height:25px;\"></td>";
  A+="        <td  class=\"overCenter\"style=\"width: 20px;height:25px;\">";
  A+="            <div style=\"width: 20px;height:25px;overflow:hidden;background-color:transparent;border-style:none;border:-width:0px\">";
  this.icon=this.icon?addContextPath(this.icon):PICTURE_BLANK;
  A+="<img border='0' width='16px' height='20px' src='"+this.icon+"'>";
  A+="            </div>";
  A+="        </td>";
  A+="        <td class=\"overCenter\" style=\"padding-left:10px;height:25px;background-color:transparent;border-style:none;border:-width:0px\">";
  var $=this.getContent();
  if(!$||$==null||$=="null"){
    $="";
  }
  A+="           <div>"+$+"</div>";
  A+="        </td>";
  A+="        <td class=\"overCenter\" align=\"right\" style=\"padding-right:10px;height:25px;background-color:transparent;border-style:none;border:-width:0px\">";
  var _=PICTURE_BLANK;
  if(this.hasChild()){
    _=POPMENU_ARROW_RIGHT;
  }
  A+="<img border='0' src='"+_+"'></td>";
  A+="    <td  class=\"overRight\" style=\"width: 5px;height:25px;\"></td></tr></table>";
  return A;
};
PopMenuItem.prototype.getContent=function(){
  if(this.onRefreshFunc){
    try{
      return eval(this.onRefreshFunc+"(this);");
    }
    catch(e){
      alert(e);
      return this.name;
    }
  }
  var url=this.getURL();
  if(url){
    if(this.target){
      return "<a href='"+url+"' target='"+this.target+"'>"+this.name+"</a>";
    }else {
      return "<a href='"+url+"'>"+this.name+"</a>";
    }
  }else {
    return this.name;
  }
};
PopMenuItem.prototype.initEvent=function(){
  var A=this;
  function $(){
    A.hide();
  }
  function _(){
    A.parent.canClose=true;
    var $=A.parent.currMenu;
    if($&&$!=A){
      A.parent.currMenu.hide(true);
    }
    $=A.root.openLevel[A.level];
    if($&&$!=A){
      $.hide(true);
    }
  }
  function B(){
    var $=A.parent.currMenu;
    if($&&$!=A){
      A.parent.currMenu.hide(true);
    }
    $=A.root.openLevel[A.level];
    if($&&$!=A){
      $.hide(true);
    }
    if(A.root.isShow){
      A.root.openLevel[A.level]=A;
      A.root.openByLevel(A.level);
      A.parent.canClose=false;
      A.parent.currMenu=A;
    }
    return false;
  }
  this.container.onmouseover=function(){
    eventManager.stopEvent();
    B();
    return false;
  };
  this.container.onmouseout=function(){
    eventManager.stopEvent();
    _();
    A.root.openLevel[A.level]=null;
    return false;
  };
  this.container.onclick=function(){
    eventManager.stopEvent();
    A.root.hide(true);
    A.onClick();
    return false;
  };
};
PopMenuItem.prototype.getURL=function(){
  if(this.url){
    var A="";
    for(var $=0;$<this.params.length;$++){
      var _=this.params[$];
      A+="&"+_.key+"="+_.value;
    }
    if(this.url.indexOf("?")<0){
      if(A.length>0){
        A="?"+A;
      }
      A=A.replace("&","");
    }
    return this.url+A;
  }
  return null;
};
PopMenuItem.prototype.addParam=function($,_){
  this.params.push({key:$,value:_});
};
PopMenuItem.prototype.hasChild=function(){
  return this.submemu.length!=0;
};
PopMenuItem.prototype.appendChild=function($){
  this.submemu.push($);
  $.parent=this;
};
PopMenuItem.prototype.showSubMenu=function(){
  if(this.hasChild()){
    var $=getPosition(this.container);
    this.subMenuContainer.style.left=($.left-3+this.container.offsetWidth)+"px";
    this.subMenuContainer.style.top=$.top+"px";
    this.subMenuContainer.style.display="";
    if(this.subMenuContainer.offsetWidth<this.subMenuContainer.scrollWidth){
      this.subMenuContainer.style.width=this.subMenuContainer.scrollWidth+"px";
    }
    var _=getPosition(this.subMenuContainer),D=this.doc||document,C=_.left+this.subMenuContainer.offsetWidth,A=D.body.clientWidth;
    if(C>A){
      var B=$.left-this.subMenuContainer.offsetWidth;
      if(B<0){
        B=0;
      }
      this.subMenuContainer.style.left=B+"px";
    }
    if(this.isShadowInit!=true){
      if(isFF){
        initShadow(this.shadowdiv,this.doc);
        $debug(this.shadowdiv.parentNode.offsetWidth);
        this.shadowdiv.style.width=this.shadowdiv.parentNode.offsetWidth-7;
      }else {
        initShadowIe(this.shadowdiv,this.doc);
      }
      this.isShadowInit=true;
    }
  }
};
PopMenuItem.prototype.onClick=function(){
  var root=this.root;
  if(this.onClickFunc){
    try{
      eval(this.onClickFunc+"(this,root.args)");
    }
    catch(e){
    }
  }
  if(root.onClickFunc){
    try{
      eval(root.onClickFunc+"(this.menuKey,root.args)");
    }
    catch(e){
    }
  }
};
PopMenuItem.prototype.hide=function(A){
  if(this.canClose||A){
    if(this.hasChild()){
      for(var $=0;$<this.submemu.length;$++){
        var _=this.submemu[$];
        _.hide(A);
      }
      this.subMenuContainer.style.display="none";
    }
  }
};
PopMenuItem.prototype.show=function(){
  this.showSubMenu();
};
function showPopMenu(D,$){
  var A=$o(D);
  if(A){
    A.args=$;
    var E=eventManager.getEvent(),B=E.x||E.clientX,_=E.y||E.clientY;
    if(isIE){
      var C=getAbsPos(document.body,A.win);
      B=B+C.left+A.doc.body.scrollLeft;
      _=_+C.top+A.doc.body.scrollTop;
    }else {
      C=getScreenPos(window,A.win);
      B=B+C.left;
      _=_+C.top;
    }
    if(A.hide(true)){
      A.show();
      A.setPosition(B,_);
    }
  }
}
function hideMenu($){
  var _=$o($);
  if(_){
    _.hide(true);
  }
}
function getDocumentId(_){
  if(!top.docs){
    top.docs=[];
  }
  var $=0;
  for(;$<top.docs.length;$++){
    if(top.docs[$]==_){
      return $;
    }
  }
  top.docs.push(_);
  return top.docs.length-1;
}
PopMenu.prototype.bind=function($,_){
  var A=this.id;
  if($!=null){
    $.oncontextmenu=function(){
      showPopMenu(A,_);
      return false;
    };
  }
};
function initShadowIe(E,_){
  _=_||document;
  var D=E.nextSibling;
  if(D==null||!D.isShadow){
    var $=E.parentNode,A=E.offsetWidth,B=E.offsetHeight,C=$createElement("div",{doc:_});
    C.isShadow=true;
    $.style.width=A;
    $.style.height=B;
    C.style.width=A-8;
    C.style.height=B-8;
    C.style.position="absolute";
    C.style.left=0;
    C.style.top=0;
    C.style.zIndex=-999;
    C.style.background="#777";
    C.style.filter="progid:DXImageTransform.Microsoft.alpha(opacity=50) progid:DXImageTransform.Microsoft.Blur(pixelradius=4)";
    $.insertBefore(C,E.nextSibling);
    E.shadowContainter=C;
    E.style.width=A-4;
    E.style.height=B-4;
  }
}
function RTree(B,C,E,_){
  var $=new Object();
  $.hasRoot=_;
  var A=new RTreeView(B,$,C,E);
  $.setNodeExpandAction=_rtreemodel_setNodeExpandAction;
  $.getNodeExpandAction=_rtreemodel_getNodeExpandAction;
  $.getExpandRetXpaths=_rtreemodel_getExpandRetXpaths;
  $.getExpandParam=_rtreemodel_getExpandParam;
  $.loadNodeChild=_rtreemodel_loadNodeChild;
  $.initAttachParam=_rtreemodel_initAttachParam;
  $.getTreeNodeName=_rtreemodel_getTreeNodeName;
  $.getTreeNodeIcon=_rtreemodel_getTreeNodeIcon;
  $.setEntityInfo=_rtreemodel_setEntityInfo;
  $.getEntityInfo=_rtreemodel_getEntityInfo;
  $.showNodeMenu=_rtreemodel_showNodeMenu;
  $.addMenuItem=_rtreemodel_addMenuItem;
  $.onmenuclick=_rtreemodel_onmenuclick;
  $.getMovemenu=_rtreemodel_getMovemenu;
  $.isNodeMovable=_rtreemodel_isNodeMovable;
  $.moveNode=_rtreemodel_moveNode;
  $.setMoveAction=_rtreemodel_setMoveAction;
  $.getMoveAction=_rtreemodel_getMoveAction;
  $.getMoveParam=_rtreemodel_getMoveParam;
  $.clearup=_rtreemodel_clearup;
  $.setExpandInitParamFunc=_rtreemodel_setExpandInitParamFunc;
  $.setMoveInitParamFunc=_rtreemodel_setMoveInitParamFunc;
  $.expandRoot=_rtreemodel_expandRoot;
  $.preLoad=_rtreemodel_preload;
  $.treeView=A;
  $.nodeExpandActions=new Object();
  $.entityInfos=new Object();
  $.move_node=null;
  $.moveto_node=null;
  if(isFF){
    var D=$id(B.getAttribute("id")+"_container");
    if(registerTopContainer(D)){
      EOSResizeObjects.push(A);
    }
  }
  return A;
}
function _rtreemodel_loadNodeChild($,G){
  var E;
  if($.isloadData!==true){
    var C=this.getNodeExpandAction($),I=this.getExpandParam($),K=this.getExpandRetXpaths($);
    if(!(C&&K)){
      $.icon.src=$.openIconSrc;
      $.loadFinished=true;
      return ;
    }
    var M=new HideSubmit(C);
    M.submitXML(I);
    var D=M.getXMLDom();
    E=Dataset.create(D,"root/data/"+K[0]);
    for(var B=1;B<K.length;B++){
      E.appendDataset(Dataset.create(D,"root/data/"+K[B]));
    }
  }else {
    E=$.datasetExp;
  }
  var N=E.getEntities();
  if(!$.isroot){
    if(N==undefined||N==null||N.length==0){
      $.hasChild=false;
      $.isleaf=true;
      $.refreshExpendIcon();
      $.icon.src=$.openIconSrc;
      $.childrenContainer.style.display="none";
      $.loadFinished=true;
      if(G){
        G($);
      }
      return ;
    }else {
      $.hasChild=true;
      $.isleaf=false;
      $.refreshExpendIcon();
    }
  }else {
    if(N.length!=0){
      $.hasChild=true;
      $.isleaf=false;
    }
  }
  var A,_,P,F,L,J,H,O;
  for(B=0;B<N.length;B++){
    if(B==0){
      L=true;
    }else {
      L=false;
    }
    if(B==N.length-1){
      J=true;
    }else {
      J=false;
    }
    _=N[B],P=this.getTreeNodeName(_),F=this.getTreeNodeIcon(_.name),O=this.getEntityInfo(_.name).iconType;
    if(!(this.getEntityInfo(_.name)).expandAction){
      H=false;
    }else {
      H=true;
    }
    A=new RTreeNode($,P,F,_,L,J,H,this.hasRoot,O);
    $.childrenContainer.appendChild(A);
    if(H&&this.getEntityInfo(_.name).preload==true){
      if(this.preLoad(A)==false){
        A.setLeaf();
      }
    }
    A.refresh();
  }
  $.childrenContainer.style.display="";
  $.icon.src=$.openIconSrc;
  if(G){
    G($);
  }
  $.loadFinished=true;
}
function _rtreemodel_preload(F){
  var E=this.getNodeExpandAction(F),B=this.getExpandParam(F),D=this.getExpandRetXpaths(F);
  F.isloadData=true;
  if(!(E&&D)){
    F.icon.src=F.openIconSrc;
    F.loadFinished=true;
    return false;
  }
  var $=new HideSubmit(E);
  $.submitXML(B);
  var _=$.getXMLDom(),A=Dataset.create(_,"root/data/"+D[0]);
  for(var C=1;C<D.length;C++){
    A.appendDataset(Dataset.create(_,"root/data/"+D[C]));
  }
  F.datasetExp=A;
  var G=A.values;
  if(G.length==0){
    return false;
  }else {
    return true;
  }
}
function _rtreemodel_expandRoot(){
  var $=new RTreeRootNode(new Entity("root"),this);
  $.level=0;
  this.treeView.rootNode=$;
  this.treeView.appendChild($);
  this.loadNodeChild($);
}
function _rtreemodel_setNodeExpandAction($,B,_){
  var A=this.getEntityInfo($);
  A.expandAction=B;
  A.childEntityXpaths=_;
}
function _rtreemodel_setExpandInitParamFunc(A,$){
  var _=this.getEntityInfo(A);
  _.InitExpandParamFunc=$;
}
function _rtreemodel_setMoveInitParamFunc(A,$){
  var _=this.getEntityInfo(A);
  _.InitMoveParamFunc=$;
}
function _rtreemodel_getNodeExpandAction(_){
  var A=_.entity.name,$=this.getEntityInfo(A);
  return $.expandAction;
}
function _rtreemodel_getExpandRetXpaths(_){
  var A=_.entity.name,$=this.getEntityInfo(A);
  return $.childEntityXpaths;
}
function _rtreemodel_getExpandParam(A){
  var F=A.entity.name,$=this.getEntityInfo(F),D=$.InitExpandParamFunc,E=$.submitXpath,B="",_;
  B=fireUserEvent(D,[A]);
  if(E==""||E==null){
    _=A.entity.toString();
  }else {
    var C=A.entity.name;
    A.entity.name=E;
    _=A.entity.toString();
    A.entity.name=C;
  }
  return "<root><data>"+B+_+"</data></root>";
}
function _rtreemodel_initAttachParam($){
  if($&&isUserEventDefined($)){
    return fireUserEvent($,[]);
  }else {
    return "";
  }
}
function _rtreemodel_getTreeNodeName($){
  var _=$.name,C=this.getEntityInfo(_),B=C.expression,A=$.getProperty(B);
  if(A==null){
    return B;
  }else {
    return A;
  }
}
function _rtreemodel_getTreeNodeIcon(_){
  var $=this.getEntityInfo(_);
  return $.iconSrc;
}
function _rtreemodel_getEntityInfo(entityName){
  entityName=entityName.toLowerCase();
  var entityInfos=this.entityInfos,_entityInfo;
  eval("_entityInfo = entityInfos."+entityName);
  if(!_entityInfo){
    _entityInfo=new _rtree_EntityInfo();
    eval("entityInfos."+entityName+"=_entityInfo;");
  }
  return _entityInfo;
}
function _rtreemodel_setEntityInfo(_,A,H,$,E,I,G,F,C,B,K,D){
  _=_.toLowerCase();
  var J=this.getEntityInfo(_);
  J.expression=A;
  J.iconSrc=H;
  J.onrefresh=E;
  J.onclick=$;
  J.ondblclick=I;
  J.InitExpandParamFunc=F;
  J.childEntityXpaths=C;
  J.expandAction=B;
  J.iconType=G;
  J.submitXpath=K;
  J.preload=D;
}
function _rtreemodel_setMoveAction(moveEntity,toEntity,bizAction,InitParamFunc){
  var moveActions=this.getEntityInfo(moveEntity).moveActions,index;
  eval("index = moveActions."+toEntity+";");
  if(!index){
    index=moveActions.length;
    eval("moveActions."+toEntity+" = index;");
  }
  var moveObj=new Object();
  moveObj.action=bizAction;
  moveObj.InitParamFunc=InitParamFunc;
  moveActions[index]=moveObj;
}
function _rtreemodel_isNodeMovable(B){
  var $=B.entity.name,_=this.getEntityInfo($),A=_.moveActions;
  if(A.length>0){
    return true;
  }else {
    return false;
  }
}
function _rtreemodel_moveNode(){
  if(isCanMoveTo(document.moveModel,document.movetoModel)==false){
    return ;
  }
  var F=this.move_node,H=document.movetoModel.moveto_node;
  this.move_node=null;
  this.moveto_node=null;
  this.move_menu.hide();
  var D=F.getParent(),G=this.getMoveAction(F,H);
  if(G){
    var A=F.getTree().beforeMove;
    if(A){
      if(A(F,H)==false){
        return ;
      }
    }
    var C=this.getMoveParam(F,H),E=new HideSubmit(G);
    E.submitXML(C);
    var $=F.getTree(),_=$.afterMove;
    if(_){
      if($.afterMove(E)==false){
        return ;
      }
    }
    var I,B;
    I=!F.getParent().isChildOf(H);
    B=!H.isChildOf(F.getParent());
    if(I){
      F.getParent().reloadChild();
    }
    if(B){
      H.reloadChild();
    }
  }
}
function _rtreemodel_getMoveAction(move_node,moveto_node){
  var toTreeId=findRTree(moveto_node).id,entityName=move_node.entity.name,toEntityName=moveto_node.entity.name,entityInfo=this.getEntityInfo(entityName),moveActions=entityInfo.moveActions,index;
  eval("index = moveActions."+toTreeId+"_"+toEntityName+";");
  if(typeof (index)!="undefined"){
    return moveActions[index].action;
  }else {
    return null;
  }
}
function _rtreemodel_getMoveParam(move_node,moveto_node){
  var toTreeId=findRTree(moveto_node).id,entityName=move_node.entity.name,toEntityName=moveto_node.entity.name,entityInfo=this.getEntityInfo(entityName),InitParamFunc="",moveActions=entityInfo.moveActions,index;
  eval("index = moveActions."+toTreeId+"_"+toEntityName+";");
  if(typeof (index)!="undefined"){
    InitParamFunc=moveActions[index].InitParamFunc;
  }
  var from_node=move_node.getParent(),FuncParam="";
  FuncParam=fireUserEvent(InitParamFunc,[move_node,moveto_node]);
  var fromNodeName=from_node.entity.name,movetoNodeName=moveto_node.entity.name;
  from_node.entity.name="from";
  moveto_node.entity.name="to";
  var returnValue="<root><data>"+FuncParam+this.initParam+from_node.entity+moveto_node.entity+move_node.entity+"</data></root>";
  from_node.entity.name=fromNodeName;
  moveto_node.entity.name=movetoNodeName;
  return returnValue;
}
function _rtreemodel_clearup(){
  var $=this;
  $.setNodeExpandAction=null;
  $.getNodeExpandAction=null;
  $.getExpandRetXpaths=null;
  $.getExpandParam=null;
  $.loadNodeChild=null;
  $.initAttachParam=null;
  $.getTreeNodeName=null;
  $.getTreeNodeIcon=null;
  $.setEntityInfo=null;
  $.getEntityInfo=null;
  $.showNodeMenu=null;
  $.addMenuItem=null;
  $.onmenuclick=null;
  $.getMovemenu=null;
  $.isNodeMovable=null;
  $.moveNode=null;
  $.setMoveAction=null;
  $.getMoveAction=null;
  $.getMoveParam=null;
  $.clearup=null;
  $.treeView=null;
  $.attachParam=null;
  $.nodeExpandActions=null;
  $.entityInfos=null;
  $.initParam=null;
  $.menu=null;
  $.move_node=null;
  $.moveto_node=null;
  $.move_menu=null;
}
function _rtree_EntityInfo(){
  this.expandAction=null;
  this.moveActions=new Array();
  this.childEntityXpaths=[];
  this.expression="";
  this.iconSrc="";
  this.onrefresh="";
  this.onclick="";
  this.ondblclick="";
  this.menuItems=new Array();
  this.InitMoveParamFunc=null;
  this.InitExpaneParamFunc=null;
  this.submitXpath="";
  return this;
}
function _rtreemodel_addMenuItem($,B,A){
  var _=this.getEntityInfo($);
  _.menuItems.push(new EOSTreeMenuItem(B,A));
}
function _rtreemodel_showNodeMenu(A){
  var $=A.getTree();
  if($.beforeShowMenu&&$.beforeShowMenu(A)===false){
    return ;
  }
  var F=A.entity.name,_=this.getEntityInfo(F),B=_.menuItems;
  if(B.length>0){
    for(var C=0;C<B.length;C++){
      if(C!=0){
        var E=_get_top_window(),D=E.document.createElement("div");
        D.className="rtree-popmenu-item-line";
        this.menu.insertItem(D);
      }
      this.menu.insertItem(B[C]);
    }
    $.afterShowMenu&&$.afterShowMenu(A,this.menu);
    this.menu.show();
  }
}
function _rtreemodel_onmenuclick($){
  fireUserEvent($,[this.treeView.cur_node]);
}
function _rtreemodel_getMovemenu(_){
  if(isIE){
    if(_.icon){
      this.move_menu.nodeView.innerHTML=_.icon.outerHTML+_.cell.outerHTML;
    }else {
      this.move_menu.nodeView.innerHTML=_.cell.outerHTML;
    }
    var $=this.move_menu.nodeView.getElementsByTagName("span");
    addClass($[0],"RC_TREE_CELL");
    removeClass($[0],"RC_TREE_ACTIVENODE");
  }else {
    if(_.icon){
      this.move_menu.nodeView.innerHTML="<img style='vertical-align:bottom'src='"+_.icon.src+"'>"+_.cell.innerHTML;
    }else {
      this.move_menu.nodeView.innerHTML=_.cell.innerHTML;
    }
    addClass(this.move_menu.nodeView,"RC_TREE_CELL");
  }
  this.move_menu.style.display="";
  this.move_menu.container.style.width=1;
  this.move_menu.style.width=1;
  initShadow(this.move_menu.container);
  if(isFF){
    this.move_menu.container.nextSibling.style.zIndex="999";
    this.move_menu.container.nextSibling.style.width=this.move_menu.offsetWidth+5;
  }
  this.move_menu.style.display="none";
  return this.move_menu;
}
function RTreeView(B,A,E,_){
  var C=B,$=$id(B.getAttribute("id")+"_container");
  if(isFF&&_=="100%"){
    var D=$;
    while(true){
      if(D==null){
        break ;
      }
      if(D.tagName=="TABLE"&&D.getAttribute("class")=="eos-panel-table"){
        if(D.getAttribute("height")!=null){
          $.style.height=_;
        }
        break ;
      }
      D=D.offsetParent;
    }
  }else {
    $.style.height=_;
  }
  $.style.width=E;
  C.style.overflow="auto";
  C.style.width=E;
  C.style.height=_;
  C.className="RC_TREE";
  C.setAttribute("richclientType","RTREE");
  C.findTreeNode=_rtreeview_findTreeNode;
  C.expandLevel=_rree_expand_level;
  C.getRootNode=function(){
    return this.rootNode;
  };
  C.getSelectNode=_rree_getSelectNode;
  C.moveNode=_rtree_move;
  C.clearup=_rtreeview_clearup;
  C.model=A;
  C.cur_node=null;
  C.onselectstart=function(){
    return false;
  };
  C.onselect=function(){
    return false;
  };
  C.onmouseup=_rtreeview_onmouseup;
  C.onmousemove=_rtreeview_onmousemove;
  C.onmouseout=_rtreeview_onmouseout;
  C.onkeydown=_rtreeview_onkeydown;
  C.setMenuStyle=function(_,$){
    this.model.menu.style[_]=$;
  };
  C.autoResizeS1=_rree_auto_resize_step1;
  C.autoResizeS2=_rree_auto_resize_step2;
  C.isInCurrPanel=_rtree_isInCurrPanel;
  C.init=_rtree_init;
  return C;
}
function _rtree_isInCurrPanel(){
  if(_eos_curr_open_panel!=null){
    var $=$id(this.getAttribute("id")+"_container").offsetParent;
    while(true){
      if($==null){
        return false;
      }
      if($==_eos_curr_open_panel.table){
        return true;
      }
      $=$.offsetParent;
    }
  }else {
    return false;
  }
}
function _rtree_init(){
  var $=this.model;
  $.menu=new EOSTreeMenu($);
  EOSTreeMenu.register($.menu);
  $.move_menu=new RTreeMoveMenu();
  $.expandRoot();
}
function _rtreeview_findTreeNode(_,$,A){
  function B(A,H,C,_){
    var D=A.entity.name;
    if(D==H){
      var F=A.entity.getProperty(C);
      if(F&&(F==_)){
        return A;
      }
    }
    var G=A.getChildren(),$;
    for(var E=0;E<G.length;E++){
      $=B(G[E],H,C,_);
      if($){
        return $;
      }
    }
    return null;
  }
  var C=this.rootNode;
  return B(C,_,$,A);
}
function _rree_getSelectNode(){
  return this.cur_node;
}
function _rtree_move(A,F){
  var E=A.getTree(),B=E.model.getMoveAction(A,F);
  if(B){
    var C=E.model.getMoveParam(A,F),D=new HideSubmit(B);
    D.submitXML(C);
    var $=E.afterMove;
    if($){
      if(E.afterMove(D)==false){
        return ;
      }
    }
    var G,_;
    G=!A.getParent().isChildOf(F);
    _=!F.isChildOf(A.getParent());
    if(G){
      A.getParent().reloadChild();
    }
    if(_){
      F.reloadChild();
    }
  }
}
function _rtreeview_clearup(){
  this.rootNode.clearup();
  this.model.clearup();
}
function _rtreeview_onmouseout(){
  if(!__isStartDrag()){
    return ;
  }
  if(this.cur_node){
    this.cur_node.cell.className="RC_TREE_CELL";
    document.movetoModel=null;
  }
}
function _rtreeview_onmouseup(){
}
var doc_onmousemove=null,doc_onmouseup=null,doc_onmouseout=null;
function _rtreeview_onmousemove(){
  var _=document.moveModel;
  if(!__isStartDrag()){
    return ;
  }
  var $=_.move_menu;
}
function _rtreeview_onkeydown(){
  function B($){
    if($){
      sibling=$.nextSibling;
      if(sibling){
        return sibling;
      }else {
        return B($.getParent());
      }
    }else {
      return null;
    }
  }
  function C(A){
    if(!A){
      return null;
    }
    var $=A.getChildren(),_=$.length;
    if(A.isExpanded()&&(_>0)){
      return C($[_-1]);
    }else {
      return A;
    }
  }
  var _=eventManager.getEvent();
  eventManager.stopPropagation();
  var A=this,$=A.cur_node;
  if(!$){
    return ;
  }
  switch(_.keyCode){
  case 40:
    if($.getChildren()[0]&&$.isExpanded()){
      $.getChildren()[0].selected();
    }else {
      var D=B($);
      if(D){
        D.selected();
      }
    }
    A.cur_node.scrollIntoView();
    break ;
  case 38:
    if($.previousSibling){
      D=C($.previousSibling);
      if(D){
        D.selected();
      }
    }else {
      if($.getParent()){
        $.getParent().selected();
      }
    }
    A.cur_node.scrollIntoView();
    break ;
  case 37:
    if($.isExpanded()){
      $.collapseNode();
    }else {
      if($.getParent()){
        $.getParent().selected();
      }
    }
    A.cur_node.scrollIntoView();
    break ;
  case 39:
    if(!$.isExpanded()){
      $.expandNode();
    }else {
      if($.getChildren()[0]){
        $.getChildren()[0].selected();
      }
    }
    A.cur_node.scrollIntoView();
    break ;
  }
  return false;
}
function RTreeNode(C,G,F,A,H,$,_,D,B){
  var E=document.createElement("DIV");
  E.noWrap=true;
  E.openIconSrc=STREE_DEFAULTOPEN_ICON;
  E.closeIconSrc=STREE_DEFAULTCLOSE_ICON;
  E.plusIconSrc="";
  E.minusIconSrc="";
  E.isFirstNode=H;
  E.isLastNode=$;
  E.hasChild=_;
  E.imgLevel=C.imgLevel;
  E.isleaf=!_;
  E.hasRoot=D;
  _rtreeNode_processNodeStatus(E,A,H,$,D,F,B,G);
  E.childLoaded=false;
  E.entity=A;
  E.isroot=false;
  E.addChildNode=_rtreeNode_addChildNodes;
  E.expandNode=_rtreeNode_expandNode;
  E.collapseNode=_rtreeNode_collapseNode;
  E.clearChildren=_rtreeNode_clearChildren;
  E.selected=_rtreeNode_selected;
  E.select=_rtreeNode_selected;
  E.refresh=_rtreeNode_refresh;
  E.refreshExpendIcon=_rtreeNoderefreshExpendIcon;
  E.isChildOf=_rtreeNode_isChildOf;
  E.isExpanded=_rtreeNode_isExpanded;
  E.getParent=_rtreeNode_getParent;
  E.getChildren=_rtreeNode_getChildren;
  E.getTree=_rtreeNode_getTree;
  E.reloadChild=_rtreeNode_reloadChild;
  E.clearup=_rtreeNode_clearup;
  E.getProperty=_rtreeNode_getProperty;
  E.setIcon=_rtreeNode_setIcon;
  E.setText=_rtreeNode_setText;
  E.getText=_rtreeNode_getText;
  E.setLeaf=_rtreeNode_setLeaf;
  E.isLeaf=_rtreeNode_isLeaf;
  E.isRootNode=_rtreeNode_isRoot;
  E.getEntity=_rtreeNode_getEntity;
  E.hasChildNode=_rtreeNode_hasChildNode;
  E.onclick=_rtreenode_onclick;
  E.ondblclick=_rtreenode_ondblclick;
  E.oncontextmenu=_rtreenode_oncontextmenu;
  E.onmousedown=_rtreenode_onmousedown;
  E.onmouseover=_rtreenode_onmouseover;
  return E;
}
function _rtreeNode_hasChildNode(){
  return this.hasChild;
}
function _rtreeNode_getEntity(){
  return this.entity;
}
function _rtreeNode_isRoot(){
  return this.isroot;
}
function _rtreeNode_isLeaf(){
  return this.isleaf;
}
function _rtreeNode_getProperty($){
  return this.entity.getProperty($);
}
function _rtreeNode_setText($){
  this.cell.innerHTML=$;
}
function _rtreeNode_getText(){
  return this.cell.innerHTML;
}
function _rtreeNode_setIcon($,_){
  this.openIconSrc=_rtree_addContextPath($);
  if(_){
    this.closeIconSrc=_rtree_addContextPath(_);
  }else {
    this.closeIconSrc=this.openIconSrc;
  }
  if(this.closeIconSrc=="null"){
    this.icon.style.display="none";
  }
  this.icon.src=this.closeIconSrc;
}
function _rtreeNode_setLeaf(){
  this.isleaf=true;
  this.hasChild=false;
  this.childLoaded=true;
  this.refreshExpendIcon();
}
function RTreeRootNode(A,G){
  var D=G.hasRoot,I=G.getTreeNodeName(A),H=G.getTreeNodeIcon(A.name),_=document.createElement("DIV");
  _.noWrap=true;
  var C=document.createElement("DIV"),$;
  $=document.createElement("IMG");
  if(H=="null"){
    $.style.display="none";
  }
  if(H==""){
    H=STREE_DEFAULTOPEN_ICON;
  }else {
    H=_rtree_addContextPath(H);
  }
  _.openIconSrc=H;
  $.src=H;
  $.style.verticalAlign="middle";
  $.ondrag=function(){
    eventManager.stopPropagation();
    return false;
  };
  C.appendChild($);
  var E=document.createElement("span");
  E.innerHTML=I;
  E.className="RC_TREE_CELL";
  E.style.verticalAlign="middle";
  C.appendChild(E);
  var F=document.createElement("IMG");
  F.style.verticalAlign="middle";
  F.src=STREE_BLANK_ICON;
  C.appendChild(F);
  var B=document.createElement("DIV");
  _.appendChild(C);
  _.appendChild(B);
  if(!D){
    $.style.display="none";
    E.style.display="none";
  }
  _.setAttribute("richclientType","RTREENODE");
  _.expandIcon=new Object();
  _.icon=$;
  _.cell=E;
  _.childLoaded=false;
  _.entity=A;
  _.isroot=true;
  _.isleaf=false;
  _.level=false;
  _.childrenContainer=B;
  _.imgLevel="";
  _.addChildNode=_rtreeNode_addChildNodes;
  _.expandNode=_rtreeNode_expandNode;
  _.collapseNode=_rtreeNode_collapseNode;
  _.clearChildren=_rtreeNode_clearChildren;
  _.selected=_rtreeNode_selected;
  _.select=_rtreeNode_selected;
  _.refresh=_rtreeNode_refresh;
  _.isChildOf=_rtreeNode_isChildOf;
  _.isExpanded=_rtreeNode_isExpanded;
  _.getParent=_rtreeNode_getParent;
  _.getChildren=_rtreeNode_getChildren;
  _.getTree=_rtreeNode_getTree;
  _.reloadChild=_rtreeNode_reloadChild;
  _.clearup=_rtreeNode_clearup;
  _.isRootNode=_rtreeNode_isRoot;
  _.getEntity=_rtreeNode_getEntity;
  _.hasChildNode=_rtreeNode_hasChildNode;
  _.setText=_rtreeNode_setText;
  _.getText=_rtreeNode_getText;
  _.onclick=_rtreenode_onclick;
  _.ondblclick=_rtreenode_ondblclick;
  _.oncontextmenu=_rtreenode_oncontextmenu;
  _.onmousedown=_rtreenode_onmousedown;
  _.onmouseover=_rtreenode_onmouseover;
  return _;
}
function _rtree_addContextPath($){
  if($==null){
    return "";
  }
  if($.indexOf("/")==0){
    return contextPath+$;
  }else {
    return $;
  }
}
function _rtreeNode_processNodeStatus(A,J,L,B,$,F,O,N){
  var C=A.imgLevel,H=F,G=O;
  if(H!=""&&H!="null"){
    var E=H.split(",");
    if(G=="xpath"){
      if(E.length!=2){
        if(J.getProperty(E[0])!=null){
          A.openIconSrc=_rtree_addContextPath(J.getProperty(E[0]));
        }
        A.closeIconSrc=A.openIconSrc;
      }else {
        if(J.getProperty(E[0])!=null){
          A.openIconSrc=_rtree_addContextPath(J.getProperty(E[0]));
        }
        if(J.getProperty(E[1])!=null){
          A.closeIconSrc=_rtree_addContextPath(J.getProperty(E[1]));
        }
      }
    }else {
      if(E.length!=2){
        A.openIconSrc=_rtree_addContextPath(E[0]);
        A.closeIconSrc=A.openIconSrc;
      }else {
        A.openIconSrc=_rtree_addContextPath(E[0]);
        A.closeIconSrc=_rtree_addContextPath(E[1]);
      }
    }
  }
  if(C==""&&!$&&L){
    if(B){
      A.plusIconSrc=STREE_ROOTPLUS_ICON;
      A.minusIconSrc=STREE_ROOTMINUS_ICON;
    }else {
      if(A.hasChild){
        A.plusIconSrc=STREE_FPLUS_ICON;
        A.minusIconSrc=STREE_FMINUS_ICON;
      }else {
        A.plusIconSrc=STREE_FLEAF_ICON;
        A.minusIconSrc="";
      }
    }
  }else {
    if(B){
      if(A.hasChild){
        A.plusIconSrc=STREE_LPLUS_ICON;
        A.minusIconSrc=STREE_LMINUS_ICON;
      }else {
        A.plusIconSrc=STREE_LLEAF_ICON;
        A.minusIconSrc="";
      }
    }else {
      if(A.hasChild){
        A.plusIconSrc=STREE_PLUS_ICON;
        A.minusIconSrc=STREE_MINUS_ICON;
      }else {
        A.plusIconSrc=STREE_LEAF_ICON;
        A.minusIconSrc="";
      }
    }
  }
  var D=document.createElement("DIV");
  for(var M=0;M<C.length;M++){
    var K=document.createElement("IMG");
    K.style.verticalAlign="middle";
    if(C.charAt(M)=="1"){
      K.src=STREE_LINE_ICON;
    }else {
      K.src=STREE_BLANK_ICON;
    }
    D.appendChild(K);
  }
  if(B){
    A.imgLevel=C+"0";
  }else {
    A.imgLevel=C+"1";
  }
  K=document.createElement("IMG");
  K.src=A.plusIconSrc;
  K.style.verticalAlign="middle";
  D.appendChild(K);
  var F;
  F=document.createElement("IMG");
  if(H=="null"){
    F.style.display="none";
  }else {
    F.style.verticalAlign="middle";
    F.src=A.closeIconSrc;
  }
  D.appendChild(F);
  var _=document.createElement("span");
  _.innerHTML=N;
  _.className="RC_TREE_CELL";
  _.style.verticalAlign="middle";
  D.appendChild(_);
  var I=document.createElement("DIV");
  I.style.display="none";
  A.appendChild(D);
  A.appendChild(I);
  A.setAttribute("richclientType","RTREENODE");
  K.style.cursor="pointer";
  K.style.cursor="hand";
  A.expandIcon=K;
  A.icon=F;
  A.cell=_;
  A.childLoaded=false;
  A.childrenContainer=I;
}
function _rtreeNode_selected(){
  var $=findRTree(this);
  if($.cur_node){
    $.cur_node.cell.className="RC_TREE_CELL";
  }
  this.cell.className="RC_TREE_ACTIVENODE";
  $.cur_node=this;
}
function _rtreeNoderefreshExpendIcon(){
  var _=this.imgLevel,A=this.hasRoot,$=this.isFirstNode,B=this.isLastNode;
  if(this.getParent().isroot&&!A&&$){
    if(B){
      this.plusIconSrc=STREE_ROOTPLUS_ICON;
      this.minusIconSrc=STREE_ROOTMINUS_ICON;
    }else {
      if(this.hasChild){
        this.plusIconSrc=STREE_PLUS_ICON;
        this.minusIconSrc=STREE_MINUS_ICON;
      }else {
        this.plusIconSrc=STREE_FLEAF_ICON;
        this.minusIconSrc=STREE_FLEAF_ICON;
      }
    }
  }else {
    if(B){
      if(this.hasChild){
        this.plusIconSrc=STREE_LPLUS_ICON;
        this.minusIconSrc=STREE_LMINUS_ICON;
      }else {
        this.plusIconSrc=STREE_LLEAF_ICON;
        this.minusIconSrc=STREE_LLEAF_ICON;
      }
    }else {
      if(this.hasChild){
        this.plusIconSrc=STREE_PLUS_ICON;
        this.minusIconSrc=STREE_MINUS_ICON;
      }else {
        this.plusIconSrc=STREE_LEAF_ICON;
        this.minusIconSrc=STREE_LEAF_ICON;
      }
    }
  }
  this.expandIcon.src=this.minusIconSrc;
}
function _rtreeNode_addChildNodes($){
  this.childrenContainer.appendChild($);
}
function _rtreeNode_expandNode(B){
  if(this.isleaf){
    return ;
  }
  var A=this.getTree().beforeExpand;
  if(A){
    if(A(this)==false){
      return ;
    }
  }
  var C=this,$=findRTree(C).model;
  this.expandIcon.src=this.minusIconSrc;
  if(!C.childLoaded){
    this.icon.src=RTREE_DROP_LOADING;
    setTimeout(function(){
      return _rtreemodel_loadNodeChild.apply($,[C,B]);
    },1);
    C.childLoaded=true;
  }else {
    if(!C.isleaf){
      C.childrenContainer.style.display="";
      this.icon.src=this.openIconSrc;
    }
  }
  var _=this.getTree().afterExpand;
  if(_){
    _(this);
  }
}
function _rtreeNode_collapseNode(){
  this.expandIcon.src=this.plusIconSrc;
  this.icon.src=this.closeIconSrc;
  this.childrenContainer.style.display="none";
}
function _rtreeNode_clearChildren(){ 
  var A=this.childrenContainer.childNodes;
  for(var $=A.length-1;$>=0;$--){
    if(window.isIE){
      A[$].removeNode(true);
    }else {
      var _=A[$].parentNode;
      _.removeChild(A[$]);
    }
  }
  this.expandIcon.src=RTree.COLLAPS_ICON;
  this.childrenContainer.style.display="none";
  this.isleaf=false;
  this.childLoaded=false;
  this.isloadData=false;
}
function _rtreeNode_refresh(C){
  var A=findRTree(this),$=A.model;
  if(C){
    this.entity=C;
  }
  if(this.isroot){
    this.level=0;
  }else {
    this.level=this.getParent().level+1;
  }
  this.cell.innerHTML=$.getTreeNodeName(this.entity);
  var _=$.getEntityInfo(this.entity.name),B=_.onrefresh;
  if(!B){
    return ;
  }
  fireUserEvent(B,[this,this.cell]);
}
function _rtreeNode_getParent(){
  if(this.isroot){
    return null;
  }else {
    return this.parentNode.parentNode;
  }
}
function _rtreeNode_getChildren(){
  return this.childrenContainer.childNodes;
}
function _rtreeNode_getTree(){
  return findRTree(this);
}
function _rtreeNode_isChildOf($){
  var _=this;
  while(_=_.getParent()){
    if(_==$){
      return true;
    }
  }
  return false;
}
function _rtreeNode_isExpanded(){
  if(this.childrenContainer.style.display=="none"){
    return false;
  }else {
    return true;
  }
}
function _rtreeNode_reloadChild($){
  if(this.childLoaded||this.isRootNode()){
    this.clearChildren();
    this.expandNode($);
  }else {
    this.expandNode($);
  }
}
function _rtreeNode_clearup(){
  var _=this,A=_.getChildren();
  for(var $=A.length-1;$>=0;$--){
    A[$].clearup(true);
  }
  _.expandIcon=null;
  _.icon=null;
  _.cell=null;
  _.entity=null;
  _.childrenContainer=null;
  _.addChildNode=null;
  _.expandNode=null;
  _.collapseNode=null;
  _.clearChildren=null;
  _.selected=null;
  _.refresh=null;
  _.isChildOf=null;
  _.isExpanded=null;
  _.getParent=null;
  _.getChildren=null;
  _.getTree=null;
  _.reloadChild=null;
  _.clearup=null;
  _.onclick=null;
  _.ondblclick=null;
  _.oncontextmenu=null;
  _.onmousedown=null;
  _.onmouseover=null;
  _.onmouseup=null;
  _.removeNode(true);
}
function _rtreenode_onclick(){
  var B=findRTree(this),$=B.model;
  $.menu.hide();
  function _(){
    if(C.isleaf){
      return ;
    }
    if(C.childrenContainer.style.display=="none"){
      C.expandNode();
    }else {
      C.collapseNode();
    }
  }
  var C=this,D=eventManager.getElement();
  if(D==this.cell||D==this.expandIcon||D==this.icon){
    this.selected();
  }
  if(D==this.cell){
    var A=$.getEntityInfo(this.entity.name).onclick;
    if(A){
      fireUserEvent(A,[this]);
    }
  }
  if(D==this.expandIcon){
    _();
  }
}
function _rtreenode_ondblclick(){
  eventManager.stopPropagation();
  var A=findRTree(this),$=A.model,B=this,C=eventManager.getElement();
  if(C==this.cell){
    var _=$.getEntityInfo(this.entity.name).ondblclick;
    if(_){
      fireUserEvent(_,[this]);
    }
  }
}
function _rtreenode_oncontextmenu(){
  eventManager.stopPropagation();
  var _=findRTree(this),B=_.model,$=B.menu,A=this;
  A.selected();
  if(eventManager.getElement()==this.cell){
    B.showNodeMenu(A);
  }
  return false;
}
function _rtreenode_onmousedown(){
  var C=eventManager.getEvent();
  if(isFF){
    if(C.button!=0){
      return ;
    }
  }else {
    if(C.button!=1){
      return ;
    }
  }
  if(isFF){
    eventManager.stopPropagation();
  }
  var B=eventManager.getElement();
  if(B!=this.cell){
    return ;
  }
  var _=findRTree(this),$=_.model;
  if($.isNodeMovable(this)){
    $.move_node=this;
    document.moveModel=$;
    doc_onmousemove=document.onmousemove;
    doc_onmouseup=document.onmouseup;
    doc_onmouseout=document.onmouseout;
    document.onmousemove=function(){
      A.show();
      var $=eventManager.getEvent();
      if(isFF){
        A.style.top=eventManager.getPointY();
        A.style.left=eventManager.getPointX()+12;
      }else {
        A.style.posTop=$.y+document.body.scrollTop;
        A.style.posLeft=$.x+document.body.scrollLeft+12;
      }
      if(isCanMoveTo(document.moveModel,document.movetoModel)!=false){
        A.statusIcon.src=RTREE_DROP_YES;
      }else {
        A.statusIcon.src=RTREE_DROP_NO;
      }
    };
    document.onmouseup=function(){
      var _=document.moveModel;
      if(isCanMoveTo(document.moveModel,document.movetoModel)==false){
        var $;
        if(_.move_node.icon.style.display=="none"){
          $=getPosition(_.move_node.cell);
        }else {
          $=getPosition(_.move_node.icon);
        }
        if(A.style.display!="none"){
          A.statusIcon.style.display="none";
          A.reset($.top,$.left);
        }
      }else {
        document.onmousemove=doc_onmousemove;
        document.onmouseup=doc_onmouseup;
        document.onmouseout=doc_onmouseout;
        A.hide();
        _.moveNode();
        document.moveModel=null;
        document.movetoModel=null;
        return ;
      }
      document.onmousemove=doc_onmousemove;
      document.onmouseup=doc_onmouseup;
      document.onmouseout=doc_onmouseout;
      document.moveModel=null;
      document.movetoModel=null;
    };
    document.onmouseout=function(){
      if(isFF){
        if(eventManager.getElement().tagName!="HTML"){
          return ;
        }
      }
      try{
        if(!(C.clientX<=0||C.clientX>=document.body.clientWidth||C.clientY<=0||C.clientY>=document.body.clientHeight)){
          return ;
        }
        var B=getPosition($.move_node.icon);
        if(A.style.display!="none"){
          A.statusIcon.style.display="none";
          A.reset(B.top,B.left);
        }
        document.onmousemove=doc_onmousemove;
        document.onmouseup=doc_onmouseup;
        document.onmouseout=doc_onmouseout;
        document.moveModel=null;
        document.movetoModel=null;
      }
      catch(_){
      }
    };
    var A=$.getMovemenu($.move_node);
    A.statusIcon.style.display="";
  }
}
function _rtreenode_onmouseover(){
  if(eventManager.getElement()!=this.cell){
    return ;
  }
  var _=findRTree(this),$=_.model;
  if(__isStartDrag()){
    eventManager.stopPropagation();
    $.moveto_node=this;
    document.movetoModel=$;
    this.selected();
  }
}
function _rtreenode_onmouseup(){
}
function findRTree(_){
  var A=_,$;
  while(A=A.parentNode){
    $=A.getAttribute("richclientType");
    if($=="RTREE"){
      return A;
    }
  }
  return null;
}
function EOSTreeMenu(A){
  var _=_get_top_window(),B=_.document.createElement("div"),$=_.document.createElement("div");
  B.appendChild($);
  B.container=$;
  $.className="eos-popmenu";
  B.style.width="120px";
  B.style.cursor="pointer";
  B.style.cursor="hand";
  B.style.position="absolute";
  B.style.display="none";
  _.document.body.appendChild(B);
  B.clearItems=_treemenu_clearItems;
  B.insertItem=_treemenu_insertItem;
  B.addMenuItem=_treemenu_addMenuItem;
  B.removeMenuItem=_treemenu_removeMenuItem;
  B.hide=function(){
    this.clearItems();
    this.style.display="none";
  };
  B.show=function(){
    PageControl.setFocus(this);
    this.style.display="";
    initShadow(this.container,_.document);
    if(isFF){
      this.container.nextSibling.style.width=this.container.nextSibling.offsetWidth+5;
    }
    this.container.nextSibling.style.zIndex="-1";
    this.style.zIndex="0";
    if(isFF){
      setOpacity(this,0);
    }
    this.style.zindex=999;
    var A=eventManager.getEvent();
    if(isIE){
      this.style.posTop=A.screenY-_.screenTop+_.document.body.scrollTop;
      this.style.posLeft=A.screenX-_.screenLeft+_.document.body.scrollLeft;
    }else {
      var $=A.x||A.clientX,C=A.y||A.clientY,B=getScreenPos(window,_);
      $=$+B.left+_.document.body.scrollLeft;
      C=C+B.top+_.document.body.scrollTop;
      this.style.top=C;
      this.style.left=$;
    }
    this.tabIndex=1;
    if(isFF){
      fx_fadeIn(this,null,500);
    }
  };
  B.model=A;
  B.oncontextmenu=function(){
    return false;
  };
  B.onselectstart=function(){
    return false;
  };
  B.onblur=function(){
    EOSTreeMenu.hideAll();
  };
  return B;
}
function _treemenu_addMenuItem(_,A){
  var $=new EOSTreeMenuItem(_,A);
  this.insertItem($);
}
function _treemenu_removeMenuItem(_){
  var A=this.container.children||this.container.childNodes;
  for(var $=0;$<A.length;$++){
    if(A[$].innerHTML==_){
      this.container.removeChild(A[$]);
      return ;
    }
  }
}
EOSTreeMenu.register=function($){
  if(!this.menus){
    this.menus=new Array();
  }
  this.menus.push($);
};
EOSTreeMenu.hideAll=function(){
  if(!this.menus){
    return ;
  }
  for(var $=0;$<this.menus.length;$++){
    this.menus[$].hide();
  }
};
function EOSTreeMenuItem($,A){
  var _=_get_top_window(),B=_.document.createElement("div");
  B.className="rtree-popmenu-item";
  if(isFF){
    B.style.marginBottom="-6px";
  }
  B.innerHTML=$;
  B.onclickFunction=A;
  B.onmousedown=_treemenuitem_onclick;
  B.onmouseover=_treemenuitem_onmouseover;
  B.onmouseout=_treemenuitem_onmouseout;
  return B;
}
function _treemenu_clearItems(){
  var $=this.container.children||this.container.childNodes;
  if($){
    while($.length>0){
      this.container.removeChild($[0]);
    }
  }
}
function _treemenu_insertItem($){
  this.container.appendChild($);
}
function _treemenuitem_onclick(){
  var $=this.parentNode.parentNode;
  removeClass(this,"rtree-popmenu-item-mouseover");
  $.hide();
  $.model.onmenuclick(this.onclickFunction);
  this.style.backgroundColor="";
  this.style.color="black";
}
function _treemenuitem_onmouseover(){
  addClass(this,"rtree-popmenu-item-mouseover");
  this.style.color="white";
}
function _treemenuitem_onmouseout(){
  removeClass(this,"rtree-popmenu-item-mouseover");
  this.style.color="black";
}
function RTreeMoveMenu(){
  var C=document.createElement("div"),A=document.createElement("div"),B=document.createElement("nobr");
  A.appendChild(B);
  A.className="RC_TREE_DRAGNODE";
  C.onselectstart=function(){
    eventManager.stopPropagation();
    return false;
  };
  C.style.position="absolute";
  C.style.display="none";
  document.body.appendChild(C);
  var _=document.createElement("img");
  _.src=RTREE_DROP_NO;
  _.style.verticalAlign="middle";
  var $=document.createElement("span");
  B.appendChild(_);
  B.appendChild($);
  C.appendChild(A);
  C.nodeView=$;
  C.statusIcon=_;
  C.container=A;
  C.show=_rtreemovemenu_show;
  C.hide=_rtreemovemenu_hide;
  C.reset=_rtreemovemenu_reset;
  return C;
}
function _rtreemovemenu_show(){
  this.style.display="";
}
function _rtreemovemenu_reset(_,A){
  this.hide();
  return ;
  if(isFF){
    this.hide();
    return ;
  }
  var $=this;
  if(this.style.posTop==_&&this.style.posLeft==A){
    this.hide();
    return ;
  }
  if(this.style.posTop>_){
    if(this.style.posTop-_<=3){
      this.style.posTop=this.style.posTop-1;
    }else {
      this.style.posTop=this.style.posTop-(this.style.posTop-_)*0.3;
    }
  }
  if(this.style.posTop<_){
    if(_-this.style.posTop<=3){
      this.style.posTop=this.style.posTop+1;
    }else {
      this.style.posTop=this.style.posTop+(_-this.style.posTop)*0.3;
    }
  }
  if(this.style.posLeft>A){
    if(this.style.posLeft-A<=3){
      this.style.posLeft=this.style.posLeft-1;
    }else {
      this.style.posLeft=this.style.posLeft-(this.style.posLeft-A)*0.3;
    }
  }
  if(this.style.posLeft<A){
    if(A-this.style.posLeft<=3){
      this.style.posLeft=this.style.posLeft+1;
    }else {
      this.style.posLeft=this.style.posLeft+(A-this.style.posLeft)*0.3;
    }
  }
  setTimeout(function(){
    return _rtreemovemenu_reset.apply($,[_,A]);
  },10);
}
function _rtreemovemenu_hide(){
  this.style.display="none";
}
function fireUserEvent(function_name,param){
  var result,paramstr="";
  for(i=0;i<param.length;i++){
    if(i==0){
      paramstr="param["+i+"]";
    }else {
      paramstr=paramstr+",param["+i+"]";
    }
  }
  if(isUserEventDefined(function_name)){
    eval("result="+function_name+"("+paramstr+");");
  }
  if(!result){
    result="";
  }
  return result;
}
function isUserEventDefined(function_name){
  if(function_name==""||function_name==undefined){
    return false;
  }
  var result;
  eval("result=(typeof("+function_name+")!=\"undefined\");");
  if(!result){
    alert(STREE_MOT_FOUND+function_name+"!");
  }
  return result;
}
function __isStartDrag(){
  if(document.moveModel!=null){
    return true;
  }else {
    return false;
  }
}
function isCanMoveTo(_,E){
  if(!_){
    return false;
  }
  if(!E){
    return false;
  }
  var D=_.move_node,$=E.moveto_node;
  if($==null){
    return false;
  }
  if(D==null){
    return false;
  }
  if(D==$){
    return false;
  }
  var C=D.getParent();
  if($==C){
    return false;
  }
  if($.isChildOf(D)){
    return false;
  }
  var A=_.getMoveAction(D,$),B=D.getTree().isCanMove;
  if(!A){
    return false;
  }
  if(B){
    if(B(D,$)==false){
      return false;
    }else {
      return true;
    }
  }
}
function _rree_expand_level($,A){
  if(A==null){
    A=this.getRootNode();
  }
  if(!A.isroot){
    A.expandNode();
  }
  var _=this;
  setTimeout(function(){
    return isLoadFinish.apply(_,[$,A]);
  },10);
}
function isLoadFinish(_,B){
  if(B.loadFinished!=true){
    setTimeout(function(){
      return isLoadFinish.apply(tree,[_,B]);
    },10);
    return ;
  }
  _=_-1;
  var $=B.getChildren();
  if(_==0){
    return ;
  }else {
    for(var A=0;A<$.length;A++){
      if($[A].isleaf){
        continue ;
      }
      _rree_expand_level(_,$[A]);
    }
  }
}
function _rree_auto_resize_step1(){
  $id(this.id+"_container").style.display="none";
}
function _rree_auto_resize_step2(){
  var _=$id(this.getAttribute("id")+"_container"),$=_.parentNode;
  while($!=null){
    if($.getAttribute&&$.getAttribute("layout")!=null){
      _.style.height=$.offsetHeight;
      _.style.display="";
      $=null;
    }else {
      $=$.parentNode;
    }
  }
  _.style.display="";
}
function Relation($,_,A,B){
  this.entityName=$;
  this.entityField=_;
  this.parentEntity=A;
  this.parnetFeild=B;
}
function EntityInfo(D,F,_,A,G,$,E,B,C){
  this.icon=D;
  this.iconType=F;
  this.showField=_;
  this.onclick=A;
  this.refresh=G;
  this.oncheck=$;
  this.submitXpath=E;
  this.url=B;
  this.target=C;
}
function CheckedInfo(B,_,A,$){
  this.entityName=B;
  this.entityField=_;
  this.checkedEntity=A;
  this.checkedField=$;
}
function CheckList($){
  this.entityType=$;
  this.list=new Array();
}
CheckList.prototype.push=function($){
  this.list.push($);
};
CheckList.prototype.getLength=function(){
  return this.list.length;
};
CheckList.prototype.getNodeType=function(){
  return this.entityType;
};
CheckList.prototype.toString=function(){
  var _="";
  for(var $=0;$<this.list.length;$++){
    _=_+this.list[$];
  }
  return "<list entityType='"+this.entityType+"'>"+_+"</list>";
};
function STree(E,D,A,_,C,$){
  if(C!="null"){
    E.style.width=C;
  }
  if($!="null"){
    E.style.height=$;
  }
  var B=E.id.substr(0,E.id.indexOf("_container"));
  PageControl.add(B,this);
  this.hasCheckbox=D;
  this.checkType=A;
  this.treeContainer=E;
  this.hasRoot=_;
  this.datasetList=new Object();
  this.datasetList.root=new Object();
  this.entityInfoList=new Object();
  this.relationList=new Array();
  this.checkedInfoList=new Array();
}
STree.prototype.addRelation=function(_,A,B,$){
  var C=new Relation(_,A,B,$);
  this.relationList.push(C);
};
STree.prototype.addCheckedInfo=function(C,A,B,_){
  var $=new CheckedInfo(C,A,B,_);
  this.checkedInfoList.push($);
};
STree.prototype.addDataset=function($,_){
  var A=createXmlDom();
  A.loadXML(_.innerHTML);
  var B=new Dataset($);
  B.init(A.selectSingleNode("/root/data").childNodes);
  this.datasetList[$]=B;
};
STree.prototype.addEntityInfo=function(I,D,G,A,B,H,_,$,F,C){
  var E=new EntityInfo(D,G,A,B,H,_,$,F,C);
  this.entityInfoList[I]=E;
};
STree.prototype.getDataset=function($){
  return this.datasetList[$];
};
STree.prototype.getEntityInfo=function($){
  return this.entityInfoList[$];
};
STree.prototype.processRelation=function(){
  var _=this.relationList;
  for(var $=0;$<_.length;$++){
    var A=_[$];
    if(A.parentEntity=="root"){
      _stree_linkToRoot(A,this.getDataset(A.entityName),this.getDataset(A.parentEntity));
    }else {
      _stree_linkToParnet(A,this.getDataset(A.entityName),this.getDataset(A.parentEntity));
    }
  }
};
STree.prototype.processCheckBox=function(){
  var $=this.checkedInfoList;
  for(var B=0;B<$.length;B++){
    var A=$[B];
    if(A.entityName==A.checkedEntity){
      var E=this.getDataset(A.entityName),M=E.values,L=A.checkedField,K=A.entityField,G=E.getLength();
      for(var F=0;F<G;F++){
        var J=M[F].getProperty(K);
        if(J==L){
          M[F].checked="1";
        }
      }
    }else {
      var E=this.getDataset(A.entityName),M=E.values,G=E.getLength(),D=this.getDataset(A.checkedEntity),H=D.values,_=D.getLength(),K=A.entityField,I=A.checkedField;
      for(F=0;F<G;F++){
        J=M[F].getProperty(K);
        for(var C=0;C<_;C++){
          L=H[C].getProperty(I);
          if(L==J){
            M[F].checked="1";
          }
        }
      }
    }
  }
  this.processParnetCheckBox();
};
STree.prototype.processParnetCheckBox=function(){
  if(this.checkType=="simple"){
    return ;
  }
  _stree_processParnetCheckBox(this.getDataset("root"));
};
function _stree_processParnetCheckBox($){
  var C="3",B=$.childList;
  if(B!=null){
    for(var A=0;A<B.length;A++){
      var _=_stree_processParnetCheckBox(B[A]);
      if(C=="3"){
        C=_;
      }else {
        if(C!="2"){
          if(C!=_){
            C=2;
          }
        }
      }
    }
    $.checked=C;
    return C;
  }else {
    if($.checked=="1"){
      return 1;
    }else {
      return 0;
    }
  }
}
function _stree_linkToRoot(G,A,_){
  if(_.childList==null){
    _.childList=new Array();
  }
  var $=_.childList,I=A.getLength(),F=A.values,H=G.entityName,E=G.parnetFeild,D=G.entityField;
  if(E==="ADD_ALL"){
    for(var C=0;C<I;C++){
      var B=F[C];
      B.type=H;
      $.push(B);
    }
  }else {
    if(E===""){
      E="null";
    }
    for(C=0;C<I;C++){
      B=F[C];
      if(B.getProperty(D)+""==E){
        B.type=H;
        $.push(B);
      }
    }
  }
}
function _stree_linkToParnet(I,$,D){
  var N=$.getLength(),H=$.values,J=D.getLength(),E=D.values,_=I.entityName,L=I.parnetFeild,M=I.entityField,B=L.split(","),C=M.split(",");
  if(B.length==1){
    for(var K=0;K<N;K++){
      var A=H[K].getProperty(M);
      for(var G=0;G<J;G++){
        var F=E[G].getProperty(L);
        if(F==A){
          H[K].type=_;
          if(E[G].childList==null){
            E[G].childList=new Array();
          }
          E[G].childList.push(H[K]);
          break ;
        }
      }
    }
  }else {
    for(K=0;K<N;K++){
      A=H[K].getProperty(M);
      for(G=0;G<J;G++){
        F=E[G].getProperty(L);
        if(F==A){
          H[K].type=_;
          if(E[G].childList==null){
            E[G].childList=new Array();
          }
          E[G].childList.push(H[K]);
          break ;
        }
      }
    }
  }
}
function _streeNode_collapseNode(){
  this.expandIcon.src=this.plusIconSrc;
  this.icon.src=this.closeIconSrc;
  this.childrenContainer.style.display="none";
}
function _streeNode_expandNode(){
  this.expandIcon.src=this.minusIconSrc;
  this.icon.src=this.openIconSrc;
  if(!this.isChildLoaded){
    this.addChildNode();
  }else {
    this.childrenContainer.style.display="";
  }
}
function _streeNode_addChildNodes(){
  var C=this.entity.childList;
  if(C==null){
    return ;
  }
  for(var A=0;A<C.length;A++){
    var $=false,B=false;
    if(A==0){
      $=true;
    }
    if(A==C.length-1){
      B=true;
    }
    var _=new STreeNode(this,C[A],$,B);
    _.refresh();
    this.childrenContainer.appendChild(_);
  }
  this.childrenContainer.style.display="";
  this.isChildLoaded=true;
}
function STreeRootNode(A){
  var _=document.createElement("DIV");
  _.noWrap=true;
  _.level="";
  _.tree=A;
  _.hasCheckbox=A.hasCheckbox;
  _.checkType=A.checkType;
  _.entity=A.datasetList.root;
  _.openIconSrc=STREE_DEFAULTOPEN_ICON;
  _.closeIconSrc=STREE_DEFAULTCLOSE_ICON;
  _.plusIconSrc=STREE_ROOTPLUS_ICON;
  _.minusIconSrc=STREE_FMINUS_ICON;
  _.entityInfo=A.getEntityInfo("root");
  _.hasChild=(_.entity.childList!=null);
  _.isChildLoaded=true;
  _.isRoot=true;
  var D=document.createElement("div"),B=_.entityInfo.icon;
  if(B!=""){
    _.openIconSrc=_stree_addContextPath(B);
  }
  var $;
  $=document.createElement("IMG");
  $.style.verticalAlign="middle";
  if(_.openIconSrc=="null"){
    $.style.display="none";
  }
  $.src=_.openIconSrc;
  D.appendChild($);
  var C=null;
  if(_.hasCheckbox){
    C=document.createElement("IMG");
    C.style.verticalAlign="middle";
    if(_.entity.checked==null||_.entity.checked=="0"){
      C.src=STREE_CHECKBOX_FALSE_ICON;
    }else {
      if(_.entity.checked=="1"){
        C.src=STREE_CHECKBOX_TRUE_ICON;
      }else {
        C.src=STREE_CHECKBOX_TRUE_ICON1;
      }
    }
    D.appendChild(C);
  }
  var E;
  if(_.entityInfo.url!=""){
    E=document.createElement("A");
    E.href=_stree_addContextPath(_.entityInfo.url);
    E.innerHTML=_.entityInfo.showField;
    if(_.entityInfo.target!=""){
      E.target=_.entityInfo.target;
    }
  }else {
    E=document.createElement("SPAN");
    E.innerHTML=_.entityInfo.showField;
  }
  E.className="RC_TREE_CELL";
  D.appendChild(E);
  var G=document.createElement("IMG");
  G.style.verticalAlign="middle";
  G.src=STREE_BLANK_ICON;
  D.appendChild(G);
  var F=document.createElement("DIV");
  _.appendChild(D);
  _.appendChild(F);
  _.setAttribute("richclientType","RTREENODE");
  _.icon=$;
  _.cell=E;
  _.checkbox=C;
  _.childLoaded=true;
  if(!A.hasRoot){
    if(_.hasCheckbox){
      C.style.display="none";
    }
    $.style.display="none";
    E.style.display="none";
  }
  _.childrenContainer=F;
  _.addChildNode=_streeNode_addChildNodes;
  _.expandNode=_streeNode_expandNode;
  _.collapseNode=_streeNode_collapseNode;
  _.select=_streeNode_selected;
  _.refresh=_streeNode_refresh;
  _.setIcon=_streeNode_setIcon;
  _.setText=_streeNode_setNodeText;
  _.getText=_streeNode_getNodeText;
  _.getProperty=_streeNode_getProperty;
  _.checkbox_click=_streeNode_checkbox_onchick;
  _.getParent=_streeNode_getParent;
  _.getChildren=_streeNode_getChildren;
  _.isChecked=_streeNode_isChecked;
  _.getEntity=_streeNode_getEntity;
  _.isRootNode=_streeNode_isRoot;
  _.hasChildNode=_streeNode_hasChild;
  _.getTree=_streeNode_getTree;
  _.onmouseover=_streeNode_onmouseover;
  _.onmouseout=_streeNode_onmouseout;
  _.onclick=_streeNode_onclick;
  return _;
}
function STreeNode(B,_,A,$){
  var D=B.tree,C=document.createElement("DIV");
  C.noWrap=true;
  C.level=B.level;
  C.tree=D;
  C.hasCheckbox=D.hasCheckbox;
  C.checkType=D.checkType;
  C.entity=_;
  C.openIconSrc=STREE_DEFAULTOPEN_ICON;
  C.closeIconSrc=STREE_DEFAULTCLOSE_ICON;
  C.plusIconSrc="";
  C.minusIconSrc="";
  C.entityInfo=D.getEntityInfo(C.entity.type);
  C.hasChild=(_.childList!=null);
  C.isRoot=false;
  _streeNode_processNodeStatus(C,_,A,$);
  C.addChildNode=_streeNode_addChildNodes;
  C.expandNode=_streeNode_expandNode;
  C.collapseNode=_streeNode_collapseNode;
  C.select=_streeNode_selected;
  C.refresh=_streeNode_refresh;
  C.setIcon=_streeNode_setIcon;
  C.setText=_streeNode_setNodeText;
  C.getText=_streeNode_getNodeText;
  C.getProperty=_streeNode_getProperty;
  C.checkbox_click=_streeNode_checkbox_onchick;
  C.getParent=_streeNode_getParent;
  C.getChildren=_streeNode_getChildren;
  C.isChecked=_streeNode_isChecked;
  C.getEntity=_streeNode_getEntity;
  C.isRootNode=_streeNode_isRoot;
  C.hasChildNode=_streeNode_hasChild;
  C.getTree=_streeNode_getTree;
  C.onmouseover=_streeNode_onmouseover;
  C.onmouseout=_streeNode_onmouseout;
  C.onclick=_streeNode_onclick;
  return C;
}
function _streeNode_getTree(){
  return this.tree;
}
function _streeNode_hasChild(){
  return this.hasChild;
}
function _streeNode_isRoot(){
  return this.isRoot;
}
function _streeNode_getEntity(){
  return this.entity;
}
function _streeNode_getProperty($){
  return this.entity.getProperty($);
}
function _streeNode_processNodeStatus($,A,J,_){
  var C=$.level,H=$.entityInfo.icon,G=$.entityInfo.iconType;
  if(H!=""){
    var D=H.split(",");
    if(G=="xpath"){
      if(D.length!=2){
        $.openIconSrc=_stree_addContextPath(A.getProperty(D[0]));
        $.closeIconSrc=$.openIconSrc;
      }else {
        $.openIconSrc=_stree_addContextPath(A.getProperty(D[0]));
        $.closeIconSrc=_stree_addContextPath(A.getProperty(D[1]));
      }
    }else {
      if(D.length!=2){
        $.openIconSrc=_stree_addContextPath(D[0]);
        $.closeIconSrc=$.openIconSrc;
      }else {
        $.openIconSrc=_stree_addContextPath(D[0]);
        $.closeIconSrc=_stree_addContextPath(D[1]);
      }
    }
  }
  if(C==""&&!$.tree.hasRoot&&J){
    if(_){
      $.plusIconSrc=STREE_ROOTPLUS_ICON;
      $.minusIconSrc=STREE_ROOTMINUS_ICON;
    }else {
      if($.hasChild){
        $.plusIconSrc=STREE_FPLUS_ICON;
        $.minusIconSrc=STREE_FMINUS_ICON;
      }else {
        $.plusIconSrc=STREE_FLEAF_ICON;
        $.minusIconSrc="";
      }
    }
  }else {
    if(_){
      if($.hasChild){
        $.plusIconSrc=STREE_LPLUS_ICON;
        $.minusIconSrc=STREE_LMINUS_ICON;
      }else {
        $.plusIconSrc=STREE_LLEAF_ICON;
        $.minusIconSrc="";
      }
    }else {
      if($.hasChild){
        $.plusIconSrc=STREE_PLUS_ICON;
        $.minusIconSrc=STREE_MINUS_ICON;
      }else {
        $.plusIconSrc=STREE_LEAF_ICON;
        $.minusIconSrc="";
      }
    }
  }
  var M=document.createElement("div");
  for(var K=0;K<C.length;K++){
    var B=document.createElement("IMG");
    B.style.verticalAlign="middle";
    if(C.charAt(K)=="1"){
      B.src=STREE_LINE_ICON;
    }else {
      B.src=STREE_BLANK_ICON;
    }
    M.appendChild(B);
  }
  if(_){
    $.level=C+"0";
  }else {
    $.level=C+"1";
  }
  B=document.createElement("IMG");
  B.src=$.plusIconSrc;
  B.style.verticalAlign="middle";
  M.appendChild(B);
  var F;
  F=document.createElement("IMG");
  F.style.verticalAlign="middle";
  if($.closeIconSrc=="null"){
    F.style.display="none";
  }
  F.src=$.closeIconSrc;
  M.appendChild(F);
  var L=null;
  if($.hasCheckbox){
    L=document.createElement("IMG");
    L.style.verticalAlign="middle";
    if($.entity.checked==null||$.entity.checked=="0"){
      L.src=STREE_CHECKBOX_FALSE_ICON;
    }else {
      if($.entity.checked=="1"){
        L.src=STREE_CHECKBOX_TRUE_ICON;
      }else {
        L.src=STREE_CHECKBOX_TRUE_ICON1;
      }
    }
    M.appendChild(L);
  }
  var E;
  if($.entityInfo.url!=""){
    E=document.createElement("A");
    var N=compileTemplate($.entityInfo.url);
    E.href=_stree_addContextPath(runExpression(N,A));
    if($.entityInfo.target!=""){
      E.target=$.entityInfo.target;
    }
  }else {
    E=document.createElement("span");
  }
  E.innerHTML=$.entity.getProperty($.entityInfo.showField);
  E.className="RC_TREE_CELL";
  E.style.verticalAlign="middle";
  M.appendChild(E);
  var I=document.createElement("DIV");
  I.style.display="none";
  $.appendChild(M);
  $.appendChild(I);
  $.setAttribute("richclientType","RTREENODE");
  B.style.cursor="pointer";
  B.style.cursor="hand";
  $.expandIcon=B;
  $.icon=F;
  $.cell=E;
  $.checkbox=L;
  $.childLoaded=false;
  $.childrenContainer=I;
}
function _stree_addContextPath($){
  if($==null){
    return "";
  }
  if($.indexOf("/")==0){
    return contextPath+$;
  }else {
    return $;
  }
}
function _streeNode_refresh(){
  var functionName=this.entityInfo.refresh;
  if(functionName!=""){
    eval(functionName+"(this)");
  }
}
function _streeNode_onmouseover(){
  addClass(this.cell,"RC_TREE_MOUSEOVER");
  eventManager.stopPropagation();
}
function _streeNode_onmouseout(){
  removeClass(this.cell,"RC_TREE_MOUSEOVER");
  eventManager.stopPropagation();
}
function _streeNode_onclick(){
  var src=eventManager.getElement();
  if(src==this.cell||src==this.expandIcon||src==this.icon||src==this.checkbox){
    this.select();
  }
  if(src==this.cell){
    var functionName=this.entityInfo.onclick;
    if(functionName!=""){
      eval(functionName+"(this)");
    }
  }
  if(src==this.expandIcon){
    if(this.childrenContainer.style.display=="none"){
      if(this.hasChild){
        this.expandNode();
      }
    }else {
      this.collapseNode();
    }
  }
  if(src==this.checkbox){
    this.checkbox_click();
  }
}
function _streeNode_checkbox_onchick(){
  if(this.checkType=="simple"){
    if(this.entity.checked=="1"){
      this.entity.checked=0;
      this.checkbox.src=STREE_CHECKBOX_FALSE_ICON;
    }else {
      this.entity.checked=1;
      this.checkbox.src=STREE_CHECKBOX_TRUE_ICON;
    }
  }else {
    if(this.entity.checked=="1"){
      _stree_uncheckAllChild(this);
    }else {
      _stree_checkAllChild(this);
    }
    _stree_process_parentNode(this);
  }
  var functionName=this.entityInfo.oncheck;
  if(functionName!=""){
    eval(functionName+"(this)");
  }
}
function _stree_checkAllChild(A){
  A.entity.checked=1;
  A.checkbox.src=STREE_CHECKBOX_TRUE_ICON;
  if(A.hasChild){
    if(A.isChildLoaded){
      var $=A.getChildren();
      for(var _=0;_<$.length;_++){
        _stree_checkAllChild($[_]);
      }
    }else {
      $=A.entity.childList;
      for(_=0;_<$.length;_++){
        _stree_unload_checkAllChild($[_]);
      }
    }
  }
  return ;
}
function _stree_unload_checkAllChild(A){
  A.checked=1;
  if(A.childList){
    var $=A.childList;
    for(var _=0;_<$.length;_++){
      _stree_unload_checkAllChild($[_]);
    }
  }
  return ;
}
function _stree_uncheckAllChild(A){
  A.entity.checked=0;
  A.checkbox.src=STREE_CHECKBOX_FALSE_ICON;
  if(A.hasChild){
    if(A.isChildLoaded){
      var $=A.getChildren();
      for(var _=0;_<$.length;_++){
        _stree_uncheckAllChild($[_]);
      }
    }else {
      $=A.entity.childList;
      for(_=0;_<$.length;_++){
        _stree_unload_uncheckAllChild($[_]);
      }
    }
  }
  return ;
}
function _stree_unload_uncheckAllChild(A){
  A.checked=0;
  if(A.childList){
    var $=A.childList;
    for(var _=0;_<$.length;_++){
      _stree_unload_uncheckAllChild($[_]);
    }
  }
  return ;
}
function _stree_process_parentNode($){
  var C=$.getParent();
  if(C==null){
    return ;
  }
  var _=$.entity.checked;
  if(_=="2"){
    C.entity.checked="2";
    C.checkbox.src=STREE_CHECKBOX_TRUE_ICON1;
    _stree_process_parentNode(C);
    return ;
  }
  var A=C.getChildren();
  for(var B=0;B<A.length;B++){
    if(A[B].entity.checked==null){
      A[B].entity.checked="0";
    }
    if(A[B].entity.checked!=_){
      C.entity.checked="2";
      C.checkbox.src=STREE_CHECKBOX_TRUE_ICON1;
      _stree_process_parentNode(C);
      return ;
    }
  }
  if(_=="1"){
    C.entity.checked=1;
    C.checkbox.src=STREE_CHECKBOX_TRUE_ICON;
  }else {
    C.entity.checked=0;
    C.checkbox.src=STREE_CHECKBOX_FALSE_ICON;
  }
  _stree_process_parentNode(C);
}
function _streeNode_getChildren(){
  return this.childrenContainer.childNodes;
}
function _streeNode_getParent(){
  if(this.isRoot){
    return null;
  }else {
    return this.parentNode.parentNode;
  }
}
function _streeNode_selected(){
  var $=this.tree;
  if($.cur_node){
    $.cur_node.cell.className="RC_TREE_CELL";
  }
  this.cell.className="RC_TREE_ACTIVENODE";
  $.cur_node=this;
}
STree.prototype.getCheckedList=function(F){
  if(F!=null){
    var E=this.getDataset(F);
    if(E==null){
      alert(F+STREE_ENTITY_NOT_EXIST);
      return ;
    }
    var A=new CheckList(F),_=E.values,B=E.getLength();
    for(var D=0;D<B;D++){
      var $=_[D].checked;
      if($=="1"||$=="2"){
        A.push(_[D]);
      }
    }
    return A;
  }else {
    var C=new Array();
    for(F in this.datasetList){
      if(F=="root"){
        continue ;
      }
      E=this.getDataset(F);
      if(E==null){
        alert(F+STREE_ENTITY_NOT_EXIST);
        return ;
      }
      A=new CheckList(F),_=E.values,B=E.getLength();
      for(D=0;D<B;D++){
        $=_[D].checked;
        if($=="1"||$=="2"){
          A.push(_[D]);
        }
      }
      C.push(A);
    }
    return C;
  }
};
STree.prototype.getUncheckedList=function(F){
  if(F!=null){
    var E=this.getDataset(F);
    if(E==null){
      alert(F+STREE_ENTITY_NOT_EXIST);
      return ;
    }
    var A=new CheckList(F),_=E.values,B=E.getLength();
    for(var D=0;D<B;D++){
      var $=_[D].checked;
      if($=="0"||$==null){
        A.push(_[D]);
      }
    }
    return A;
  }else {
    var C=new Array();
    for(F in this.datasetList){
      if(F=="root"){
        continue ;
      }
      E=this.getDataset(F);
      if(E==null){
        alert(F+STREE_ENTITY_NOT_EXIST);
        return ;
      }
      A=new CheckList(F),_=E.values,B=E.getLength();
      for(D=0;D<B;D++){
        $=_[D].checked;
        if($=="0"||$==null){
          A.push(_[D]);
        }
      }
      C.push(A);
    }
    return C;
  }
};
STree.prototype.init=function(){
  this.processRelation();
  this.processCheckBox();
  var _=new STreeRootNode(this);
  this.rootNode=_;
  _.refresh();
  this.treeContainer.appendChild(_);
  var $=document.createElement("div");
  this.treeContainer.appendChild($);
  this.hidden=$;
  _.addChildNode();
  if(isFF){
    if(registerTopContainer(this.treeContainer.parentNode)){
      EOSResizeObjects.push(this);
    }
  }
};
STree.prototype.getRootNode=function(){
  return this.rootNode;
};
STree.prototype.getSelectedNode=function(){
  return this.cur_node;
};
function _streeNode_setIcon($,_){
  this.openIconSrc=_stree_addContextPath($);
  if(_){
    this.closeIconSrc=_stree_addContextPath(_);
  }else {
    this.closeIconSrc=this.openIconSrc;
  }
  if(this.closeIconSrc=="null"){
    this.icon.style.display="none";
  }
  this.icon.src=this.closeIconSrc;
}
function _streeNode_isChecked(){
  var $=this.entity.checked;
  if($=="1"||$=="2"){
    return true;
  }else {
    return false;
  }
}
function _streeNode_setNodeText($){
  this.cell.innerHTML=$;
}
function _streeNode_getNodeText(){
  return this.cell.innerHTML;
}
STree.prototype.createHiddenData=function(){
  var E=new StringBuffer();
  for(entityType in this.datasetList){
    if(entityType=="root"){
      continue ;
    }
    var C=1,A=this.getDataset(entityType);
    if(A==null){
      alert(entityType+STREE_ENTITY_NOT_EXIST);
      return ;
    }
    var F=A.values,G=A.getLength();
    for(var D=0;D<G;D++){
      var B=F[D].checked;
      if(B=="1"||B=="2"){
        var _=F[D].type,$=this.getEntityInfo(_).submitXpath;
        if($!=""){
          _=$;
        }
        E.append(_stree_create_hiddenStr(C,F[D],_));
        C=C+1;
      }
    }
  }
  this.hidden.innerHTML=E;
};
function _stree_create_hiddenStr(D,B,A){
  var _=new StringBuffer(),C=B.getKeys();
  for(var E=0;E<C.length;E++){
    var $=B.getProperty(C[E]);
    _.append("<input type='hidden' name='").append(A).append("[").append(D).append("]/").append(C[E]).append("' value='").append($).append("'/>");
  }
  return _;
}
STree.prototype.autoResizeS1=function(){
  this.treeContainer.parentNode.style.display="none";
};
STree.prototype.autoResizeS2=function(){
  var _=this.treeContainer.parentNode,$=_.parentNode;
  while($!=null){
    if($.getAttribute&&$.getAttribute("layout")!=null){
      _.style.height=$.offsetHeight;
      $=null;
    }else {
      $=$.parentNode;
    }
  }
  _.style.display="";
};
STree.prototype.isInCurrPanel=function(){
  if(_eos_curr_open_panel!=null){
    var $=this.treeContainer.offsetParent;
    while(true){
      if($==null){
        return false;
      }
      if($==_eos_curr_open_panel.table){
        return true;
      }
      $=$.offsetParent;
    }
  }else {
    return false;
  }
};
function SwitchCheckbox($){
  this.id=$;
  this.checkbox=null;
  this.hiddenInput=null;
  this.trueValue="1";
  this.falseValue="0";
  this.defaultValue="0";
  this.container=null;
  PageControl.add($,this);
}
SwitchCheckbox.prototype.init=function(){
  this.checkbox=$id(this.id+"_checkbox");
  this.hiddenInput=$id(this.id);
  this.container=$id(this.id+"_container");
  if(this.checkbox){
    var _=this;
    function $(){
      if(_.checkbox.checked){
        _.onSelect();
      }else {
        _.onUnSelect();
      }
      _.refreshValue();
    }
    eventManager.add(this.checkbox,"click",$);
  }
};
SwitchCheckbox.prototype.refreshInput=function(){
  if(this.hiddenInput.value==this.trueValue){
    this.checkbox.checked=true;
  }else {
    this.checkbox.checked=false;
  }
};
SwitchCheckbox.prototype.refreshValue=function(){
  if(this.checkbox.checked){
    this.hiddenInput.value=this.trueValue;
  }else {
    this.hiddenInput.value=this.falseValue;
  }
};
SwitchCheckbox.prototype.onSelect=function(){
};
SwitchCheckbox.prototype.onUnSelect=function(){
};
SwitchCheckbox.prototype.setValue=function($){
  if(this.trueValue==$){
    this.hiddenInput.value=this.trueValue;
  }else {
    this.hiddenInput.value=this.falseValue;
  }
  this.refreshInput();
};
SwitchCheckbox.prototype.getValue=function(){
  this.refreshValue();
  return this.hiddenInput.value;
};
SwitchCheckbox.prototype.setFocus=function(){
  this.checkbox.focus();
};
SwitchCheckbox.prototype.lostFocus=function(){
};
var TABPANE_TYPE_DIV="DIV",TABPANE_TYPE_IFRAME="IFRAME",TABPANE_STATUS_NOMAL=1,TABPANE_STATUS_OVER=2,TABPANE_STATUS_ACTIVE=3,TABPANE_STATUS_DISABLED=4,TABPANE_STATUS_CLOSED=5,TABPANE_STATUS_HIDDEN=5,TAB_STYLE_NOMAL="nomal",TAB_STYLE_OVER="hover",TAB_STYLE_ACTIVE="active",TAB_STYLE_DISABLED="disabled",TAB_STYLE_LEFT="left",TAB_STYLE_MID="mid",TAB_STYLE_RIGHT="right",TAB_STYLE_TABLE="container";
function TabPane($){
  this.id=$;
  PageControl.add($,this);
  this.subTabs=new Array();
  this.currTab=null;
  this.defaultTab=0;
  this.isTitleOut=false;
  this.titleContainer=null;
  this.bodyContainer=null;
  this.bgColor="#f1f1f1";
  this.borderColor="#C0C0C0";
  this.container=null;
  this.width="100%";
  this.height="100%";
  this.titleWidth=120;
  this.__titleTR=null;
  this.noCacheIframe=$create("<iframe name='"+this.id+"'/>");
  this.noCacheIframe.name=this.id;
  this.noCacheIframe.style.border=0;
  this.noCacheIframe.frameBorder=0;
  this.noCacheIframe.style.width="100%";
  this.noCacheIframe.style.height="100%";
  this.pilot=null;
  this.pilotIndex=0;
  this.firstButton=null;
  this.nextButton=null;
  this.privousButton=null;
  this.lastButton=null;
  this.maxTabs=0;
  this.inited=false;
  this.pilotInited=false;
  this._titleTD=null;
  this.titleAlign="top";
  this.styleSuffix="";
}
TabPane.prototype.append=function($){
  this.subTabs.push($);
  $.group=this;
};
TabPane.prototype.getLength=function(){
  return this.subTabs.length;
};
TabPane.prototype.getTabs=function(){
  return this.subTabs;
};
TabPane.prototype.getActiveTab=function(){
  return this.currTab;
};
TabPane.prototype.getActiveTabIndex=function(){
  return this.getCurrIndex();
};
TabPane.prototype.remove=function(A){
  for(var _=0;_<this.subTabs.length;_++){
    if(this.subTabs[_]==A){
      this.subTabs.splice(_,1);
      if(this.currTab==A){
        this.currTab=null;
        var $=_-1;
        $=$<0?0:$;
        this.selectTab($);
      }
      return true;
    }
  }
  return false;
};
TabPane.prototype.buildTitles=function(){
  var _=this.getWidth(),A=0;
  for(var $=0;$<this.subTabs.length;$++){
    this.appendTitle(this.subTabs[$]);
    A+=this.subTabs[$].getWidth();
    if(_-A<60){
      this.subTabs[$].hideTitle();
      this.isTitleOut=true;
    }else {
      this.subTabs[$].showTitle();
    }
  }
  if(this.isTitleOut){
    this.initPilot();
  }
  this.inited=true;
};
TabPane.prototype.getWidth=function(){
  var $=0;
  if(String(this.width).indexOf("%")>0){
    $=parseInt(this.container.offsetWidth);
  }else {
    $=parseInt(this.width);
  }
  if($==0){
    var A=this.container.parentNode;
    while(A){
      var _=parseInt(A.offsetWidth);
      if(_!=0){
        return _;
      }
      A=A.parentNode;
    }
    return document.body.offsetWidth;
  }else {
    return $;
  }
};
TabPane.prototype.getIndex=function(_){
  for(var $=0;$<this.subTabs.length;$++){
    if(this.subTabs[$]==_){
      return $;
    }
  }
  return -1;
};
TabPane.prototype.getCurrIndex=function(){
  return this.getIndex(this.currTab);
};
TabPane.prototype.getShowTabIndex=function(){
  for(var $=0;$<this.subTabs.length;$++){
    if(!this.subTabs[$].isHidden){
      return $;
    }
  }
  return -1;
};
TabPane.prototype.showTitleEnd=function(){
  var $=this.subTabs[this.subTabs.length-1];
  if($){
    return !$.isHidden;
  }
  return true;
};
TabPane.prototype.refreshTitles=function(){
  this.isTitleOut=false;
  var B=this.getWidth(),$=0,_=this.getShowTabIndex();
  for(var A=0;A<this.subTabs.length;A++){
    if(A<_){
      this.subTabs[A].hideTitle();
    }else {
      $+=this.subTabs[A].getWidth();
      if(B-$<60){
        this.subTabs[A].hideTitle();
        this.isTitleOut=true;
      }else {
        this.subTabs[A].showTitle();
      }
    }
  }
  if(this.isTitleOut){
    if(!this.pilotInited){
      this.initPilot();
    }
    this.showPilot(this.pilotIndex);
    this.pilot.style.display="";
  }else {
    if(this.pilot){
      this.pilot.style.display="none";
    }
  }
};
TabPane.showNextTab=function(A){
  var _=$id(A);
  if(!_.showTitleEnd()){
    var $=_.getShowTabIndex()+1;
    _.showPilot($);
  }
};
TabPane.showPrivousTab=function(A){
  var _=$id(A),$=_.getShowTabIndex()-1;
  _.showPilot($);
};
TabPane.prototype.initPilot=function(){
  this.pilot=$id(this.id+"_nav_pilot");
  var $="<table  border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"width:40px;height:20px;overflow:hidden\"><tr><td>";
  $+="<div onclick=\"TabPane.showPrivousTab('"+this.id+"')\" onmouseover=\"this.className='button-privous-hover'\" onmouseout=\"this.className='button-privous'\" class=\"button-privous\">&nbsp;</div>";
  $+="</td><td>";
  $+="<div onclick=\"TabPane.showNextTab('"+this.id+"')\" class=\"button-next\" onmouseover=\"this.className='button-next-hover'\" onmouseout=\"this.className='button-next'\">&nbsp;</div>";
  $+="</td></tr></table>";
  this.pilot.innerHTML=$;
  this.pilot.style.display="";
  this.pilotInited=true;
};
TabPane.prototype.showPilot=function(_){
  var B=this.getWidth(),$=0;
  for(var A=0;A<this.subTabs.length;A++){
    if(A<_){
      this.subTabs[A].hideTitle();
      this.isTitleOut=true;
    }else {
      $+=this.subTabs[A].getWidth();
      if(B-$<60){
        this.subTabs[A].hideTitle();
        this.isTitleOut=true;
      }else {
        this.subTabs[A].showTitle();
      }
    }
  }
  this.pilotIndex=_;
};
TabPane.prototype.appendTitle=function(_){
  var $=$create("td"),A="<table class=\""+TAB_STYLE_TABLE+"\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr>";
  A+="<td><div class=\""+TAB_STYLE_LEFT+"\"></div></td>";
  A+="<td nowarp class=\""+TAB_STYLE_MID+"\">";
  A+="<div style=\"white-space:nowrap;page-break-before: always;page-break-after: always;\">";
  A+=_.getTitle();
  A+="</div></td>";
  if(_.showCloseButton){
    A+="<td nowarp class=\""+TAB_STYLE_MID+"\">";
    A+="<a class=\"close\" href=\"#\" onclick=\"$o('"+this.id+"').getTab('"+_.id+"').close();\">";
    if(isIE){
      A+="<div></div></a>";
    }else {
      A+="<div class=\"close\"></div></a>";
    }
    A+="</td>";
  }
  if(_.allowReload){
    A+="<td nowarp class=\""+TAB_STYLE_MID+"\">";
    A+="<a class=\"reload\" href=\"#\" onclick=\"$o('"+this.id+"').getTab('"+_.id+"').reload();\">";
    if(isIE){
      A+="<div></div></a>";
    }else {
      A+="<div class=\"reload\"></div></a>";
    }
    A+="</td>";
  }
  A+="<td><div class=\""+TAB_STYLE_RIGHT+"\"></div></div>";
  A+="</td></tr></table>";
  $.className=TAB_STYLE_NOMAL+this.styleSuffix;
  $.innerHTML=A;
  _.__titleTD=$;
  _.setTitleTD();
  _.initStatus();
  this.__titleTR.insertBefore($,this._titleTD);
  _.titleWidth=$.offsetWidth;
};
TabPane.prototype.init=function(){
  this.container=$id(this.id+"_container");
  this.titleContainer=$id(this.id+"_title_container");
  this.bodyContainer=$id(this.id+"_body");
  this.__titleTR=$id(this.id+"_nav_tr");
  this._titleTD=$create("td");
  this.__titleTR.style.width="100%";
  this.__pilotContainer=$id(this.id+"_nav_pilot_container");
  if(this.titleAlign=="top"){
    this.styleSuffix="";
    this._titleTD.className="right-top";
    this._titleTD.style.overflow="hidden";
  }else {
    this.styleSuffix="-down";
    this.bodyContainer.className="tab-body-down";
    this._titleTD.className="right-buttom";
  }
  this.__titleTR.insertBefore(this._titleTD,this.__pilotContainer);
  this.buildTitles();
  this._titleTD.innerHTML="&nbsp;";
  this._titleTD.style.width="100%";
  this.selectTab(this.defaultTab);
};
TabPane.prototype.closeTab=function(_){
  var $;
  if(typeof (_)=="object"){
    $=_;
  }else {
    $=this.getTab(_);
  }
  if($ instanceof TabPage){
    $.close();
  }
};
TabPane.prototype.selectTab=function(_){
  var $;
  if(typeof (_)=="object"){
    $=_;
  }else {
    $=this.getTab(_);
  }
  if($ instanceof TabPage){
    this.activeTab($);
    if(this.isTitleOut){
      this.showPilot(this.pilotIndex);
    }
  }
};
TabPane.prototype.getTab=function(_){
  if(typeof (_)=="object"){
    if(_ instanceof TabPage){
      return _;
    }
  }else {
    for(var $=0;$<this.subTabs.length;$++){
      if(this.subTabs[$].id==_){
        return this.subTabs[$];
      }
    }
    if(!isNaN(_)){
      if(this.subTabs[_]){
        return this.subTabs[_];
      }
    }
  }
  return null;
};
TabPane.prototype.activeTab=function($){
  if(!$.allowSelect){
    return false;
  }
  if(this.currTab){
    if(this.currTab==$){
      return false;
    }
    if(this.currTab.__onUnselect()){
      if($.show()){
        this.currTab.hide();
        this.currTab=$;
        return true;
      }
    }
  }else {
    $.show();
    this.currTab=$;
    return true;
  }
  return false;
};
TabPane.prototype.createBlankTab=function(I,B,A,G,F,E){
  I=I||this.id+Math.random();
  var _=this.getTab(I);
  if(_){
    alert("The TabPage of id:"+I+",title:"+_.getTitle()+" is exists!");
    return null;
  }
  var H="";
  H+="<table border='0' cellspacing='0' cellpadding='0' width='100%' height='100%'>";
  H+="\t<tr>";
  H+="\t\t<td id='"+I+"_container' valign='top'  style='display:none;width:100%;height:100%'>";
  H+="\t\t</td>";
  H+="\t</tr>";
  H+="</table>";
  var D=$create(H);
  D.style.width="100%";
  D.style.height="100%";
  var C=firstElement(this.bodyContainer);
  C.appendChild(D);
  var $=new TabPage(I);
  $.type=A;
  $.src=G;
  $.title=B;
  $.cache=E;
  $.showCloseButton=F;
  $.init();
  this.append($);
  if(this.inited){
    this.appendTitle($);
  }
  this.refreshTitles();
  return $;
};
TabPane.prototype.createIframeTab=function(A,B,_,$){
  return this.createBlankTab($,A,"IFRAME",B,false,_);
};
TabPane.prototype.createDivTab=function(B,A,$){
  var _=this.createBlankTab($,B,"DIV",null,false,null);
  _.container.innerHTML=A;
  return _;
};
function TabPage($){
  this.id=$;
  PageControl.add($,this);
  this.title=$;
  this.type=TABPANE_TYPE_DIV;
  this.src=null;
  this.container=null;
  this.iframe=null;
  this.onSelectFunc=null;
  this.onUnSelectFunc=null;
  this.onRefreshFunc=null;
  this.__titleTD=null;
  this.showCloseButton=true;
  this.status=TABPANE_STATUS_NOMAL;
  this.group=null;
  this.cache=false;
  this.allowSelect=true;
  this.params=[];
  this.containerTable=null;
  this.titleWidth=80;
  this.isHidden=false;
  this.isLoad=false;
  this.allowReload=false;
}
TabPage.prototype.__onSelect=function(){
  if(this.onSelectFunc){
    try{
      return eval(this.onSelectFunc+"(this"+")");
    }
    catch(e){
      $handle(e);
      return false;
    }
  }
  return true;
};
TabPage.prototype.__onUnselect=function(){
  if(this.onUnSelectFunc){
    try{
      var ret=eval(this.onUnSelectFunc+"(this"+")");
      if(ret===false){
        return false;
      }else {
        this.container.style.display="none";
        return true;
      }
    }
    catch(e){
      $handle(e);
      return false;
    }
  }
  return true;
};
TabPage.prototype.reload=function(){
  if(this.type==TABPANE_TYPE_IFRAME){
    if(this.isLoad){
      var $=this.getUrl();
      if($.indexOf("?")>=0){
        $+="&_date="+new Date();
      }else {
        $+="?_date="+new Date();
      }
      this.iframe.src=$;
    }else {
      this.iframe.src=this.getUrl();
      this.isLoad=true;
    }
  }
};
TabPage.prototype.disabled=function(){
  if(this.status==TABPANE_STATUS_NOMAL){
    this.allowSelect=false;
    this.status=TABPANE_STATUS_DISABLED;
    this.__titleTD.className=TAB_STYLE_DISABLED+this.group.styleSuffix;
  }
};
TabPage.prototype.enabled=function(){
  if(this.status==TABPANE_STATUS_DISABLED){
    this.allowSelect=true;
    this.status=TABPANE_STATUS_NOMAL;
    this.__titleTD.className=TAB_STYLE_NOMAL+this.group.styleSuffix;
  }
};
TabPage.prototype.initStatus=function(){
  if(this.allowSelect){
    this.enabled();
  }else {
    this.disabled();
  }
};
TabPage.prototype.init=function(){
  this.container=$id(this.id+"_container");
  this.containerTable=getParentByTagName(this.container,"table");
  if(!isIE){
    this.containerTable.style.display="none";
  }
  if(this.type==TABPANE_TYPE_IFRAME){
    if(this.cache){
      this.iframe=$create("<iframe name='"+this.id+"'/>");
      this.iframe.style.border=0;
      this.iframe.frameBorder=0;
      this.iframe.style.width="100%";
      this.iframe.style.height="100%";
      this.container.appendChild(this.iframe);
      this.isLoad=false;
    }
  }
};
TabPage.prototype.load=function($){
  this.src=$;
  this.group.selectTab(this);
  if(this.type==TABPANE_TYPE_IFRAME){
    if(this.cache){
      this.iframe.src=this.getUrl();
    }else {
      this.group.noCacheIframe.src=this.getUrl();
    }
  }
};
TabPage.prototype.getTitle=function(){
  if(this.onRefreshFunc){
    try{
      return eval(this.onRefreshFunc+"(this.title,this)");
    }
    catch(e){
    }
  }
  return this.title;
};
TabPage.prototype.onClose=function(){
};
TabPage.prototype.close=function(){
  try{
    this.onClose();
  }
  catch($){
    $handle($);
    return false;
  }
  if(this.group.getLength()>1){
    this.group.remove(this);
    this.__titleTD.parentNode.removeChild(this.__titleTD);
    this.container.id="";
    this.container.style.display="none";
    if(!isIE){
      this.containerTable.style.display="none";
    }
    this.group.refreshTitles();
  }
};
TabPage.prototype.active=function(){
  var $=this.getTabPane();
  $.activeTab(this);
};
TabPage.prototype.hide=function(){
  this.container.style.display="none";
  if(!isIE){
    this.containerTable.style.display="none";
  }
  if(this.status!=TABPANE_STATUS_DISABLED){
    this.status=TABPANE_STATUS_NOMAL;
    this.__titleTD.className=TAB_STYLE_NOMAL+this.group.styleSuffix;
  }else {
    this.__titleTD.className=TAB_STYLE_DISABLED+this.group.styleSuffix;
  }
  this.setTitleTD();
};
TabPage.prototype.show=function(){
  var $=this.__onSelect();
  if($===false){
    return false;
  }
  this.container.style.display="";
  if(!isIE){
    this.containerTable.style.display="";
  }
  if(this.type==TABPANE_TYPE_IFRAME){
    if(!this.cache){
      this.container.appendChild(this.group.noCacheIframe);
      ChaGetToPostByForm(this.group.noCacheIframe,this.getUrl());
    }else {
      if(!this.isLoad){
        this.reload();
        this.isLoad=true;
      }
    }
  }
  this.status=TABPANE_STATUS_ACTIVE;
  this.__titleTD.className=TAB_STYLE_ACTIVE+this.group.styleSuffix;
  this.setTitleTD();
  return true;
};
TabPage.prototype.setTitleTD=function(){
  var _=this.group,$=this.__titleTD,A=this;
  $.onclick=function(){
    var $=this.childNodes[0].rows[0],B=$.cells[0].getElementsByTagName("div")[0];
    B.style.backgroundImage="";
    B=$.cells[$.cells.length-1].getElementsByTagName("div")[0];
    for(var C=1;C<$.cells.length-1;C++){
      $.cells[C].style.backgroundImage="";
    }
    B.style.backgroundImage="";
    _.selectTab(A);
  };
  $.onmouseover=function(){
    if(A.status==TABPANE_STATUS_NOMAL){
      var C=this.childNodes[0].rows[0],$=C.cells[0].getElementsByTagName("div")[0],_=getCurrentStyle($,"backgroundImage");
      $.style.backgroundImage=_.replace("tabs_nomal_left","tabs_hover_left");
      $=C.cells[C.cells.length-1].getElementsByTagName("div")[0];
      for(var B=1;B<C.cells.length-1;B++){
        C.cells[B].style.backgroundImage=_.replace("tabs_nomal_left","tabs_hover_center");
      }
      $.style.backgroundImage=_.replace("tabs_nomal_left","tabs_hover_right");
    }
    eventManager.stopPropagation();
  };
  $.onmouseout=function(){
    if(A.status==TABPANE_STATUS_NOMAL){
      var B=this.childNodes[0].rows[0],$=B.cells[0].getElementsByTagName("div")[0];
      $.style.backgroundImage="";
      $=B.cells[B.cells.length-1].getElementsByTagName("div")[0];
      for(var _=1;_<B.cells.length-1;_++){
        B.cells[_].style.backgroundImage="";
      }
      $.style.backgroundImage="";
    }
    eventManager.stopPropagation();
  };
};
TabPage.prototype.addParam=function($,_){
  this.params.push({key:$,value:_});
};
TabPage.prototype.getUrl=function(){
  if(this.src){
    var $="";
    for(var _=0;_<this.params.length;_++){
      var A=this.params[_];
      $+="&"+A.key+"="+A.value;
    }
    var B=this.src;
    if(B.indexOf("/")==0){
      if(B.indexOf(contextPath)!=0){
        B=contextPath+B;
      }
    }
    if(B.indexOf("?")<0){
      if($.length>0){
        $="?"+$;
      }
      $=$.replace("&","");
    }
    return B+$;
  }
  return null;
};
TabPage.prototype.hideTitle=function(){
  this.isHidden=true;
  this.__titleTD.style.display="none";
};
TabPage.prototype.showTitle=function(){
  this.isHidden=false;
  this.__titleTD.style.display="";
};
TabPage.prototype.getWindow=function(){
  var $=window;
  if(this.type==TABPANE_TYPE_IFRAME){
    if(this.cache){
      $=this.iframe.contentWindow;
    }else {
      $=this.group.noCacheIframe.contentWindow;
    }
  }
  return $;
};
TabPage.prototype.getDocument=function(){
  var $=document;
  if(this.type==TABPANE_TYPE_IFRAME){
    if(this.cache){
      $=this.iframe.document||this.iframe.contentDocument;
    }else {
      $=this.group.noCacheIframe.document||this.group.noCacheIframe.contentDocument;
    }
  }
  return $;
};
TabPage.prototype.getTabPane=function(){
  return this.group;
};
TabPage.prototype.getWidth=function(){
  if(this.titleWidth==0){
    if(this.title){
      return (getTextWidth(this.title)+12);
    }else {
      return 80;
    }
  }
  return this.titleWidth;
};
function getTextWidth(_){
  var A=document.createElement("span");
  A.innerHTML=_;
  document.body.appendChild(A);
  var $=A.offsetWidth;
  document.body.removeChild(A);
  return $;
}
function ComboSelect($){
  this.id=$;
  this.Obj=$;
  this.inputObject=null;
  this.eventObject=null;
  this.hiddenObject=null;
  this.canClose=true;
  this.container=null;
  this.focusStatus=false;
  this.mouseOffset=null;
  this.showStatus=false;
  this.win=window;
  this.textField=null;
  this.valueField=null;
  this.filterField=null;
  this.submitXpath=null;
  this.xpath=null;
  this.dataXML=null;
  this.showMoreDay=true;
  this.date=null;
  this.timer=null;
  this.timeSelect=null;
  this.width=100;
  this.optionsWidth=null;
  this.optionsHeight="180px";
  this.allowInput=false;
  this.filterField=null;
  this.startWith=true;
  this.isFirst=true;
  this.__subComponent=[];
  this.defaultText="";
  this.validateFunc=function(){
    return true;
  };
  this.validate=this.validateFunc;
  this.isFirstLoad=true;
  this.initParamFunc=null;
  this.nullText=null;
  this.filterType="startwith";
  this.showValue=false;
  this._showAll=true;
  PageControl.add(this.id,this);
}
window.setTimeoutEosWeb=function(A,B){
  var $=null;
  if(window.oldSetTimeout){
    $=window.oldSetTimeout;
  }else {
    $=window.setTimeout;
  }
  if(typeof A=="function"){
    var _=Array.prototype.slice.call(arguments,2),C=function(){
      return A.apply(null,_);
    };
    return $(C,B);
  }
  return $(A,B);
};
ComboSelect.prototype.freshInitFunc=function(B){
  if(this.beforeRefresh()===false){
    return ;
  }
  var $=this.datasetExp?this.datasetExp.getLength():0,F=["<div class=\"eos-combo-optiondiv-core\"><table  class=\"eos-combo-optiontable\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" ><tbody>"];
  for(var C=0;C<$;C++){
    var _=this.datasetExp.get(C),D=_.getProperty(this.valueField),A=_.getProperty(this.textField);
    F.push("<tr __entity_rowno=\""+C+"\" >");
    F.push("<td><div><nobr>");
    F.push(A==""?" ":A);
    F.push("</nobr></div></td>");
    if(this.showValue){
      F.push("<td><div>");
      F.push(D);
      F.push("</div></td>");
    }
    F.push("</tr>");
  }
  F.push("</tbody></table ></div>");
  var E=window.isIE?"<iframe SCROLLING=\"no\" style=\"overflow:hidden;position:absolute;z-index:-1;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft)\" frameborder=\"0\" ></iframe>":"<span style=\"display:none;\"></span>";
  this.optionsDiv.innerHTML=E+F.join("\n");
  this.optionsDivCore=this.optionsDiv.childNodes[1];
  this.optionsTable=this.optionsDivCore.firstChild;
  this.optionsDiv.childNodes[1].style.height=this.optionsDiv.style.height;
  this.optionsDiv.childNodes[1].style.width=this.optionsDiv.style.width;
  if(!B){
    this.setSubComponent(this.getEntityByValue(this.value));
  }
  this.afterRefresh();
};
function timeFilter_enter($){
  var A=$.inputObject.value;
  if($.isinit!=true&&!$.showStatus){
    $.show(true);
    $.version=new Date().getTime();
    var B=new Date().getTime();
    timeFilter($,B);
  }else {
    eventManager.stopEvent();
    if($.inputObject.stateType!=false){
      if($.beforeHEnter&&$.beforeHEnter()==false){
      }else {
        var _=$.getFilterNum();
        if(_>0||!$.allowInput){
          $.selectOption($.actionOptionRow);
        }
      }
      $.hide();
      $.inputObject.select();
    }
    $.inputObject.stateType=true;
  }
}
function timeFilter($,C){
  var _=$.inputObject.value,B=false;
  if(($.beforeFilter&&$.beforeFilter(_,$.filterModel)==true)||($.beforeFilter==undefined&&_&&$.filterModel&&_.length>=$.filterModel)){
    B=true;
  }
  if(B){
    if($.oldIOV==""||_.indexOf($.oldIOV)!=0){
      if($.version>C){
        return ;
      }else {
        $.version_v=null;
      }
      $.loadData();
      $.freshInitFunc();
    }
  }
  A=$.filter($.version);
  return A;
}
ComboSelect.prototype.onPageLoad=function(){
  PageControl.registerRelation(this.linkId,this.id);
  var _=this;
  function $(){
    _.init();
    _.loadData();
    _.refresh();
  }
  eventManager.add(window,"load",$);
};
ComboSelect.currentComboSelect=null;
ComboSelect.editorContainer=null;
ComboSelect.doc=document;
ComboSelect.prototype.showEditor=function($){
  this.container.style.display="";
  try{
    this.inputObject.focus();
  }
  catch(_){
  }
};
ComboSelect.prototype.hideEditor=function($){
  this.hide();
  if(this.container){
    this.container.style.display="none";
  }
};
ComboSelect.prototype.getSelectEntity=function(){
  return this.selectEntity;
};
ComboSelect.prototype.setValue=function(A,_,D){
  var $=this.getEntityByValue(A);
  if(D){
    if(!$){
      return ;
    }
  }
  if(A){
    if($){
    }else {
      if(this.filterModel){
        this.inputObject.value=A;
        this.hiddenObject.value=A;
        this.initInput=A;
        this.version=new Date().getTime();
        timeFilter(this,this.version,A);
        this.initInput=null;
        $=this.getEntityByValue(A);
      }
    }
  }
  if($&&A){
  }else {
    if(this.activeOptionRow){
      removeClass(this.activeOptionRow,"eos-selectoption");
      this.activeOptionRow=null;
    }
    this.selectEntity=new Entity();
    if(this.allowInput){
      this.inputObject.value=A;
      this.hiddenObject.value=A;
    }else {
      if(this.nullText&&this.filterModel){
      }else {
        this.inputObject.value="";
        this.hiddenObject.value="";
      }
    }
    this.initInput="";
    return ;
  }
  var C=$.__index,B=this.optionsTable.tBodies[0].rows;
  if(!isNaN(C)&&B&&B.length>0){
    this.selectOption(B[C],_);
  }
};
ComboSelect.prototype.getValue=function(){
  if(!this.allowInput){
    return this.hiddenObject.value;
  }
  var $=this.getEntityByText();
  return $?$.getProperty(this.valueField):this.inputObject.value;
};
ComboSelect.prototype.getDisplayValue=function(_){
  if(_){
  }else {
    return "";
  }
  var $=this.getEntityByValue(_);
  return $?$.getProperty(this.textField):_;
};
ComboSelect.prototype.getText=function(){
  return this.inputObject.value;
};
ComboSelect.prototype.getEntityByValue=function(A,D){
  var _=this.datasetExp?this.datasetExp.getLength():0;
  A=""+(A||this.hiddenObject.value||"");
  (D===true)&&(A=A.toLowerCase());
  for(var B=0;B<_;B++){
    var $=this.datasetExp.get(B),C=$?$.getProperty(this.valueField):null;
    if(C!==undefined&&C!==null){
      (D===true)&&(C=(""+C).toLowerCase());
      if(A===C){
        $.__index=B;
        return $;
      }
    }
  }
  return null;
};
ComboSelect.prototype.getEntityByText=function(A,D){
  var _=this.datasetExp?this.datasetExp.getLength():0;
  A=""+(A||this.inputObject.value||"");
  (D===true)&&(A=A.toLowerCase());
  for(var B=0;B<_;B++){
    var $=this.datasetExp.get(B),C=$?$.getProperty(this.textField):null;
    if(C!==undefined&&C!==null){
      (D===true)&&(C=(""+C).toLowerCase());
      if(A===C){
        $.__index=B;
        return $;
      }
    }
  }
  return null;
};
ComboSelect.prototype.setWidth=function($){
  this.width=$+"";
  this.initSize();
};
ComboSelect.prototype.getWidth=function(){
  return this.width;
};
ComboSelect.prototype.initSize=function(){
  if(this.width){
    this.width=this.width+"";
    this.container.style.width=this.width;
    if(this.container.offsetWidth!=0){
      if(this.width.indexOf("%")!=-1){
        if(isFF){
          this.inputObject.style.width=this.width;
        }else {
          if(isIE){
            this.container.style.width="";
            this.inputObject.style.width=this.width;
          }
        }
      }else {
        if(this.width.indexOf("px")!=-1){
          this.inputObject.style.width=this.width.replace("px","")-17;
        }else {
          this.inputObject.style.width=this.width-17;
        }
      }
    }
  }
};
ComboSelect.prototype.setPosition=function($,B,C,D){
  try{
    this.hide();
    if(this.container){
      this.width=C||this.width;
      this.height=D||this.height;
      this.container.style.position="absolute";
      this.container.style.width=C;
      var _=getMaxZindex(document);
      if(this.container.style.zIndex!=_){
        this.container.style.zIndex=_;
      }
      this.container.style.display=isFF?"-moz-inline-box":"inline";
      this.container.style.top=B;
      this.container.style.left=$;
      this.eventObject.style.height=D;
      this.container.style.height=D;
      this.inputObject.style.height=D;
      this.inputObject.style.width=C-17;
    }
  }
  catch(A){
  }
};
ComboSelect.prototype.init=function(){
  if(this.inited===true||this.beforeInit()===false){
    return ;
  }
  this.filterField=this.filterField||this.textField;
  this.container=$id(this.id+"_container");
  this.inputObject=$id(this.id+"_input");
  this.hiddenObject=$id(this.id+"_hidden");
  this.readonly=(this.readonly===true||this.readonly===false)?this.readonly:this.readOnly;
  this.inputObject.readOnly=!!this.readonly;
  this.hiddenObject.readOnly=!!this.readonly;
  this.inputObject.disabled=!!this.disabled;
  this.hiddenObject.disabled=!!this.disabled;
  this.eventObject=$id(this.id+"_button");
  this.button=this.eventObject;
  this.text=this.inputObject;
  this.hidden=this.hiddenObject;
  var comboSelect=this;
  this.eventObject.src=contextPath+"/common/skins/skin0/images/comboselect/comboSelect_button.gif";
  this.eventObject.onmouseover=function(){
    if(comboSelect.getDisabled()||comboSelect.getReadOnly()){
      return ;
    }
    this.src=contextPath+"/common/skins/skin0/images/comboselect/comboSelect_button_over.gif";
  };
  this.eventObject.onmouseout=function(){
    if(comboSelect.getDisabled()||comboSelect.getReadOnly()){
      return ;
    }
    this.src=contextPath+"/common/skins/skin0/images/comboselect/comboSelect_button.gif";
  };
  this.eventObject.onmousedown=function(){
    if(comboSelect.getDisabled()||comboSelect.getReadOnly()){
      return ;
    }
    this.src=contextPath+"/common/skins/skin0/images/comboselect/comboSelect_button_down.gif";
  };
  this.initSize();
  this.inputObject.value=this.defaultText;
  this.validate=(typeof this.validateFunc)=="string"?eval(this.validateFunc):this.validateFunc;
  var fts=this.filterType.toLowerCase().split(","),array=Array(fts.length);
  for(var i=0;i<fts.length;i++){
    array[i]=(fts[i]!="like");
  }
  this.startWith=array;
  this.initEvent();
  this.inited=true;
  this.afterInit();
};
ComboSelect.prototype.isFocus=function(){
  return this.focusStatus;
};
ComboSelect.prototype.cancleFilter=function(A){
  var _=this.optionsTable.tBodies[0].rows;
  for(var $=0;$<_.length;$++){
    _[$].style.display="";
  }
  return _.length;
};
ComboSelect.prototype.filter=function(A){
  var $=this.inputObject.value,_=0;
  if($){
    _=this.doFilter($,null,A);
  }else {
    _=this.cancleFilter($);
  }
  return _;
};
ComboSelect.prototype.getFilterNum=function(){
  var A=0,_=this.optionsTable.tBodies[0].rows;
  for(var $=0;$<_.length;$++){
    if(_[$].style.display!="none"){
      A++;
    }
  }
  return A;
};
ComboSelect.prototype.doFilter=function(L,$,N){
  var F=this.optionsTable.tBodies[0].rows;
  L=L||this.inputObject.value||"";
  L=L.toLowerCase();
  var _=0,J=null,E=false,O=false;
  for(var B=0;B<F.length;B++){
    if(N&&this.version!=N){
      break ;
    }
    if(O){
      F[B].style.display="none";
      continue ;
    }
    var K=this.datasetExp.get(B);
    if(this.filterField){
      var P=(this.filterField).split(","),K=this.datasetExp.get(B);
      if(P[Q]&&L&&K){
        continue ;
      }
      var C=new Array();
      for(var Q=0;Q<P.length;Q++){
        var H=K.getProperty(P[Q]);
        if(H){
          C[C.length]=H;
        }
      }
      F[B].style.display=($==false?"":"none");
      if(this.selectOptionRow==F[B]){
        E=true;
      }
      if(C!==undefined&&C!==null&&C.length>0){
        var G=false;
        for(var M=0;M<C.length;M++){
          D=(""+C[M]).toLowerCase();
          var A=D.indexOf(L),I=true;
          if(this.startWith.splice){
            if(this.startWith.lenth<=M){
              I=this.startWith[this.startWith.lenth-1];
            }else {
              I=this.startWith[M];
            }
          }else {
            I=this.startWith;
          }
          if(I&&A==0||!I&&A>=0){
            G=true;
          }
        }
        if(G){
          F[B].style.display="";
          if(!J){
            J=F[B];
            this.activeOption(J);
            this.inputObject.activeOptionRow=J;
          }
          _++;
          if(_>=20){
            O=true;
          }
        }
      }
    }
  }
  if(E){
  }
  return _;
};
ComboSelect.prototype._syncInputValue=function(){
  if(this.hiddenObject.value!==this.inputObject.value){
    this.hiddenObject.value=this.inputObject.value;
    this.selectEntity=new Entity();
    this.selectEntity.setProperty(this.valueField,this.hiddenObject.value);
    this.selectEntity.setProperty(this.textField,this.hiddenObject.value);
    return true;
  }
  return false;
};
ComboSelect.prototype._onChange=function(){
  if(this.onChangeFunc){
    if(typeof (this.onChangeFunc)=="string"){
      this.onChangeFunc=eval(this.onChangeFunc);
    }
    this.onChangeFunc(this.selectEntity,this);
  }
};
ComboSelect.prototype.onInputBlur=function(){
  if(this.beforeInputBlur&&this.beforeInputBlur()==false){
    return false;
  }
  var A=this;
  if(checkInput(this.inputObject)==false){
    return ;
  }
  var E=true;
  if(this.validate){
    E=this.validate();
  }
  if(this.inputObject.value){
  }else {
    if(this.nullText!==null){
      var F=new Entity();
      F.setProperty(this.valueField,"");
      F.setProperty(this.textField,"this.nullText");
      this.datasetExp.addEntity(F,0);
      var G=this.optionsTable.tBodies[0].rows;
      this.selectOption(G[0]);
    }else {
      this.selectEntity=null;
      this.selectOptionRow=null;
      this.inputObject.value="";
      this.hiddenObject.value="";
    }
    return ;
  }
  if(E){
    if(!A.showStatus){
      var D=A.getFilterNum(),B=A.hiddenObject.value,$=A.getEntityByRow(A.activeOptionRow),_,C;
      if($){
        _=$.getProperty(A.valueField);
        C=$.getProperty(A.textField);
      }
      if(B!=_&&C==A.inputObject.value){
        A.selectOption();
      }else {
        if(A.allowInput){
        }else {
          if(!A.allowInput){
            A.selectOption(A.selectOptionRow,(B!=_));
            if(B!=_){
              A._onChange();
            }
          }
        }
      }
    }
  }
};
ComboSelect.prototype.reset=function(){
};
ComboSelect.prototype.initEvent=function(){
  var $=this,_=","+[EOSKey.TAB,EOSKey.ENTER,EOSKey.SHIFT,EOSKey.CTRL,EOSKey.PAUSE,EOSKey.CAPSLOCK,EOSKey.PAGEUP,EOSKey.PAGEDOWN,EOSKey.END,EOSKey.HOME,EOSKey.LEFT,EOSKey.UP,EOSKey.RIGHT,EOSKey.DOWN,EOSKey.INSERT,EOSKey.WIN,EOSKey.WIN_R,EOSKey.MENU,EOSKey.F1,EOSKey.F2,EOSKey.F3,EOSKey.F4,EOSKey.F5,EOSKey.F6,EOSKey.F7,EOSKey.F8,EOSKey.F9,EOSKey.F10,EOSKey.F11,EOSKey.F12,EOSKey.NUMLOCK,EOSKey.SCROLLLOCK].join(",")+",";
  function D($){
  }
  function E(_){
    if($.getDisabled()||$.getReadOnly()){
      return ;
    }
    _=_||window.event;
    if(_.keyCode==EOSKey.ENTER){
      eventManager.stopEvent();
    }else {
      if(_.keyCode==EOSKey.UP){
        if(!$.showStatus){
          $.show(true);
        }else {
          $.activePrevOption();
        }
        eventManager.stopEvent(_);
      }else {
        if(_.keyCode==EOSKey.DOWN){
          if(!$.showStatus){
            $.show(true);
          }else {
            $.activeNextOption();
          }
          eventManager.stopEvent(_);
        }
      }
    }
  }
  function F(D){
    if($.getDisabled()||$.getReadOnly()){
      return ;
    }
    if(this.optionsTable&&this.optionsTable.tBodies&&this.optionsTable.tBodies[0]){
      return ;
    }
    D=D||window.event;
    var A=(D.keyCode==EOSKey.ENTER);
    if(!A){
      eventManager.stopEvent(D);
    }
    if(A){
      if($.inputObject.stateType!=false){
        if(!$.showStatus){
          $.show(true);
        }else {
          if($.beforeHEnter&&$.beforeHEnter()==false){
          }else {
            var C=$.getFilterNum();
            if(C>0||!$.allowInput){
              $.selectOption($.activeOptionRow);
            }
          }
          $.hide();
        }
      }
      $.inputObject.stateType=true;
    }else {
      if(D.keyCode==EOSKey.ESC){
        if($.showStatus){
          $.hide();
        }else {
          PageControl.setFocus($);
          $.show(true);
        }
        eventManager.stopEvent(D);
      }else {
        if(D.keyCode==EOSKey.UP){
        }else {
          if(D.keyCode==EOSKey.DOWN){
          }else {
            if(_.indexOf(","+D.keyCode+",")<0){
              if($.filterModel==-1){
                $.isinit=false;
                return ;
              }
              var B=$.inputObject.value;
              if($.showStatus==false){
                if($.filterModel||$.beforeFilter){
                  var E=false;
                  if(($.beforeFilter&&$.beforeFilter(B,$.filterModel)==true)||($.beforeFilter==undefined&&B&&B.length>=$.filterModel)){
                    E=true;
                  }
                  if(E){
                    $.show();
                  }
                }else {
                  $.show();
                }
              }else {
                if($.showStatus==true&&($.filterModel||$.beforeFilter)){
                  B=$.inputObject.value,E=false;
                  if(($.beforeFilter&&$.beforeFilter(B,$.filterModel)!=true)||($.beforeFilter==undefined&&B&&B.length<$.filterModel)){
                    E=true;
                  }
                  if(E){
                    $.hide();
                  }
                }
              }
              eventManager.stopEvent(D);
              if($.afterFilter){
                $.afterFilter($);
              }
              $.version=new Date().getTime();
              $.version_v=$.version;
              window.setTimeoutEosWeb(timeFilter,300,$,$.version);
              if($.allowInput){
                if($._syncInputValue()){
                  $._onChange();
                }
              }
            }
          }
        }
      }
    }
    if(!A){
      eventManager.stopEvent();
    }
  }
  function C(C){
    if($.getDisabled()||$.getReadOnly()){
      return ;
    }
    if(C.keyCode!=EOSKey.ESC){
      return ;
    }
    C=C||window.event;
    if(C.keyCode==EOSKey.ENTER){
      eventManager.stopEvent();
      var B=$.getFilterNum();
      if(B<1&&$.allowInput){
        if($._syncInputValue()){
          $._onChange();
        }
      }
    }else {
      if(C.keyCode==EOSKey.TAB){
        $.onInputBlur();
      }else {
        if(_.indexOf(","+C.keyCode+",")<0){
          var A=$.inputObject.value;
          if($.beforeFilter&&$.beforeFilter(A,$.filterModel)==false){
            return false;
          }
          B=$.filter();
          eventManager.stopEvent(C);
          if($.afterFilter){
            $.afterFilter($);
          }
          $.show();
        }
      }
    }
  }
  function B(A){
    if($.getDisabled()||$.getReadOnly()){
      return ;
    }
    A=A||window.event;
    var _=eventManager.getWheel(A);
    if(_<0){
      $.activeNextOption();
      $.selectOption();
    }else {
      if(_>0){
        $.activePrevOption();
        $.selectOption();
      }
    }
    eventManager.stopEvent();
  }
  function A(){
    eventManager.stopEvent();
    if($.getDisabled()||$.getReadOnly()){
      return ;
    }
    $.eventObject.src=contextPath+"/common/skins/skin0/images/comboselect/comboSelect_button_over.gif";
    if($.showStatus){
      $.hide();
    }else {
      $.show(true);
    }
  }
  if(!this.inputObject.readOnly){
    eventManager.add(this.inputObject,"blur",function(_){
      $.onInputBlur(_);
    });
  }
  this.container.onmousedown=function(){
    var _=eventManager.getElement();
    if(_==$.inputObject){
      if($.showStatus){
        eventManager.stopEvent();
      }
      try{
        $.inputObject.focus();
      }
      catch(A){
      }
    }else {
      if(_==$.eventObject){
        if($.showStatus){
          eventManager.stopEvent();
        }
      }
    }
  };
  eventManager.add(this.inputObject,"keydown",E);
  eventManager.add(this.inputObject,"keyup",F);
  eventManager.add(this.eventObject,"click",function(){
    eventManager.stopEvent();
  });
  eventManager.add(this.eventObject,"mouseup",A);
  eventManager.add(this.inputObject,"focus",function(){
    $.inputObject.select();
  });
};
ComboSelect.prototype.show=function(_){
  PageControl.addtoStack(this);
  if(this.optionsDiv&&this.optionsDiv.style&&!this.disabled){
    if(this.optionsWidth&&this.optionsWidth.indexOf("%")>0){
      this.optionsDiv.style.width=this.container.clientWidth*parseInt(this.optionsWidth)/100;
    }else {
      this.optionsDiv.style.width=this.optionsWidth||isFF?this.container.offsetWidth-8:this.container.offsetWidth;
    }
    if(_===true){
      if(this.optionsTable&&this.optionsTable.tBodies&&this.optionsTable.tBodies[0]){
        this.cancleFilter();
      }
    }
    this.optionsDiv.style.display="block";
    var B=getMaxZindex(document);
    if(this.optionsDiv.style.zIndex!=B){
      this.optionsDiv.style.zIndex=B;
    }
    var A=getScreenPosition(this.inputObject,this.win);
    if(A[0]>=0){
      setElementXY(this.optionsDiv,[isFF?A[0]:A[0],A[1]+this.inputObject.offsetHeight]);
    }
    try{
      this.inputObject.focus();
    }
    catch($){
    }
    this.showStatus=true;
    var C=this.optionsDiv.firstChild.nextSibling;
    initShadow(C);
    if(isFF){
      C.nextSibling.style.width=this.optionsDiv.offsetWidth+7;
    }
  }
};
ComboSelect.prototype.hide=function(){
  if(this.optionsDiv&&this.optionsDiv.style){
    this.optionsDiv.style.display="none";
  }
  this.showStatus=false;
};
ComboSelect.prototype.onLoadData=function(){
};
ComboSelect.prototype.addParam=function($,_){
  this.paramList=this.paramList||[];
  this.paramList.push({key:$,value:_});
};
ComboSelect.prototype.loadData=function(){
  if(this.beforeLoadData()===false){
    return ;
  }
  if(!this.linkId&&!this.queryAction&&this.xpath){
    var xmlZone=document.getElementById(this.id+"_xml");
    this.dataXML=xmlZone?xmlZone.innerHTML:null;
  }else {
    if(this.linkId&&this.linkId.indexOf("xml:")==0){
      xmlZone=document.getElementById(this.linkId.substring(4));
      this.dataXML=xmlZone?xmlZone.innerHTML:null;
    }else {
      if(this.linkId&&this.isFirstLoad){
        this.isFirstLoad=false;
        return ;
      }
    }
  }
  var xmlDom,_dataXML=this.dataXML;
  this.datasetExp=new Dataset();
  if(this.nullText!==null){
    var emptyEntity=new Entity();
    emptyEntity.setProperty(this.valueField,"");
    emptyEntity.setProperty(this.textField,this.nullText);
    this.setValue("",null,true);
    this.datasetExp.addEntity(emptyEntity);
  }
  if(_dataXML){
    this.datasetExp.appendDataset(Dataset.create(_dataXML,this.xpath));
    return ;
  }
  if(this.filterModel){
    if(this.version){
    }else {
      return ;
    }
  }
  if(this.onLoadData()!==false){
    if(!this.queryAction){
      return ;
    }
    var ajax=new HideSubmit(this.queryAction),param="";
    ajax.sumbitType="4";
    if(this.queryParam){
      param+=this.queryParam;
    }
    if(this.pageParam){
      param+=this.pageParam;
    }
    if(this.initParamFunc){
      try{
        param+=eval(this.initParamFunc+"(this)");
      }
      catch(e){
        $handle(e);
      }
    }
    if(param==""){
      param=null;
    }
    if(this.filterID){
      var form=$id(this.filterID);
      if(form){
        for(var i=0;i<form.elements.length;i++){
          var elem=form.elements[i];
          if(elem.name){
            ajax.addParam(elem.name,elem.value);
          }
        }
      }
    }
    if(this.paramList){
      for(i=0;i<this.paramList.length;i++){
        elem=this.paramList[i];
        if(elem){
          ajax.addParam(elem.key,elem.value);
        }
      }
    }
    ajax.loadData(param);
    this.xmlDom=ajax.retDom;
    this.isinit=true;
    if(this.xmlDom){
      var B=this.xmlDom.selectSingleNode("root/data/fieldMessage"),fieldMessage=getNodeValue(B);
      if(B&&fieldMessage){
        this.fieldMessage=fieldMessage;
        var fms=fieldMessage.split("$");
        if(fms.length>0){
          this.valueField=fms[0];
        }
        if(fms.length>1){
          this.textField=fms[1];
        }
        if(fms.length>2){
          this.filterField=fms[2];
        }
        if(this.nullText!==null){
          emptyEntity=new Entity();
          emptyEntity.setProperty(this.valueField,"");
          emptyEntity.setProperty(this.textField,this.nullText);
          this.datasetExp.values[0]=emptyEntity;
        }
      }
    }
    this.datasetExp.appendDataset(Dataset.create(this.xmlDom,this.xpath,null));
  }
};
ComboSelect.prototype.getEntityByRow=function($){
  if(!$){
    return null;
  }
  var A=$.getAttribute("__entity_rowno")/1,_=this.datasetExp.get(A);
  return _;
};
ComboSelect.prototype.disable=function(){
  this.disabled=true;
  this.inputObject.disabled=true;
  this.hiddenObject.disabled=true;
  this.hide();
};
ComboSelect.prototype.enable=function(){
  this.disabled=false;
  this.inputObject.disabled=false;
  this.hiddenObject.disabled=false;
};
ComboSelect.prototype.setDisabled=function($){
  if($){
    this.disable();
  }else {
    this.enable();
  }
  if($){
    this.eventObject.style.cursor="default";
  }else {
    this.eventObject.style.cursor="pointer";
    this.eventObject.style.cursor="hand";
  }
};
ComboSelect.prototype.getDisabled=function($){
  return this.disabled;
};
ComboSelect.prototype.setReadonly=function($){
  this.inputObject.readOnly=$||true;
};
ComboSelect.prototype.setReadOnly=function($){
  this.readonly=$;
  this.inputObject.readOnly=$;
  if($){
    this.eventObject.style.cursor="default";
  }else {
    this.eventObject.style.cursor="pointer";
    this.eventObject.style.cursor="hand";
  }
};
ComboSelect.prototype.getReadOnly=function($){
  return this.readonly;
};
ComboSelect.prototype.firstOptionRow=function(B,$){
  var A=this.optionsTable.tBodies[0].rows;
  B=B||0;
  $=$||A.length;
  B=B<0?0:B;
  $=$<0?0:$;
  $=$>A.length?A.length:$;
  B=B>$?$:B;
  for(var _=B;_<$;_++){
    if(A[_].style.display!="none"){
      return A[_];
    }
  }
  return null;
};
ComboSelect.prototype.lastOptionRow=function(B,$){
  var A=this.optionsTable.tBodies[0].rows;
  B=B||0;
  $=$===0?0:($||A.length);
  B=B<0?0:B;
  $=$>A.length-1?A.length-1:$;
  $=$<0?0:$;
  B=B>$?$:B;
  if(A.length>0){
    for(var _=$;_>=B;_--){
      if(A[_].style.display!="none"){
        return A[_];
      }
    }
  }
  return null;
};
ComboSelect.prototype.activePrevOption=function(_){
  _=_||this.activeOptionRow;
  var A=0;
  if(_){
    A=_.rowIndex-1;
  }
  var $=this.lastOptionRow(0,A);
  if($&&$!=_){
    this.activeOption($);
  }
};
ComboSelect.prototype.activeNextOption=function(_){
  _=_||this.activeOptionRow;
  var A=0;
  if(_){
    A=_.rowIndex+1;
  }
  var $=this.firstOptionRow(A);
  if($&&$!=_){
    this.activeOption($);
  }
};
ComboSelect.prototype.activeOption=function($){
  if(this.activeOptionRow){
    removeClass(this.activeOptionRow,"eos-selectoption");
  }
  addClass($,"eos-selectoption");
  this.autoScroll($);
  this.activeOptionRow=$;
};
ComboSelect.prototype.selectOption=function(row,refreshSub){
  this._showAll=true;
  var _change=this.selectOptionRow!=row;
  row=row||this.activeOptionRow;
  if(!row){
    return ;
  }
  var entity=this.getEntityByRow(row);
  if(entity){
    if(this.activeOptionRow){
      removeClass(this.activeOptionRow,"eos-selectoption");
    }
    if(row.offsetTop<this.optionsDivCore.scrollTop+2){
      this.optionsDivCore.scrollTop=row.offsetTop;
    }
    if(row.offsetTop>this.optionsDivCore.scrollTop+this.optionsDivCore.clientHeight-4){
      this.optionsDivCore.scrollTop=row.offsetTop-this.optionsDivCore.clientHeight+row.offsetHeight;
    }
    this.selectEntity=entity;
    this.inputObject.value=this.selectEntity.getProperty(this.textField);
    this.hiddenObject.value=this.selectEntity.getProperty(this.valueField);
    if(this.activeOptionRow){
      removeClass(this.activeOptionRow,"eos-selectoption");
    }
    this.selectOptionRow=row;
    this.activeOptionRow=row;
    addClass(this.activeOptionRow,"eos-selectoption");
    if(this.onChangeFunc&&!this.isFirst){
      if(typeof (this.onChangeFunc)=="string"){
        this.onChangeFunc=eval(this.onChangeFunc);
      }
      this.isFirst=false;
      _change&&this.onChangeFunc(this.selectEntity,this);
    }
    this.isFirst=false;
  }
  if(refreshSub!==false){
    this.setSubComponent(entity);
  }
};
ComboSelect.prototype.registerSubComponent=function(_){
  var $=PageControl.getOne(_);
  this.__subComponent.push($);
};
ComboSelect.prototype.setSubComponent=function(A){
  var $;
  if(A){
    $=A.name;
    A.name=this.submitXpath||$;
  }
  for(var _=0;_<this.__subComponent.length;_++){
    var B=this.__subComponent[_];
    if(B&&B.freshFromEntity){
      B.freshFromEntity(A);
    }
  }
  if(A){
    A.name=$;
  }
};
ComboSelect.prototype.freshFromEntity=function($){
  if(this.queryAction){
    if($){
      this.queryParam=$.toString();
    }else {
      this.queryParam=null;
    }
    if(this.filterModel){
      if(this.isFreshFromEntity){
        return ;
      }else {
        this.isFreshFromEntity=true;
      }
    }
    this.loadData();
  }else {
    if(this.linkField){
      if($){
        this.datasetExp=$.getProperty(this.linkField);
        if(!this.datasetExp){
          this.datasetExp=new Dataset(this.linkField);
          $.setProperty(this.linkField,this.datasetExp);
        }
      }else {
        this.datasetExp=new Dataset(this.linkField);
      }
    }
  }
  this.refresh();
};
ComboSelect.prototype.refresh=function($){
  this.init();
  if(this.beforeRefresh()===false){
    return ;
  }
  var L=this;
  this.datasetExp=$||this.datasetExp;
  var _=this.datasetExp?this.datasetExp.getLength():0;
  if(this.noOrgOptionsHeight==true){
    this.optionsHeight=((_>10?10:_)*21+2);
  }
  if(this.optionsDiv){
    removeElement(this.optionsDiv);
  }
  this.optionsDiv=$createElement("div");
  this.optionsDiv.className="eos-combo-optiondiv";
  this.inputObject.value="";
  this.hiddenObject.value="";
  function G(){
    var $=eventManager.getElement();
    if($!=L.eventObject&&!L.inputObject.getAttribute("_isFocus")&&!L.eventObject.getAttribute("_isFocus")){
      if(!L.allowInput){
        L.inputObject.value=L.selectEntity.getProperty(L.textField);
        L.hiddenObject.value=L.selectEntity.getProperty(L.valueField);
      }
      L.hide();
    }
  }
  if(!this.optionsHeight){
    this.noOrgOptionsHeight=true;
    this.optionsHeight=((_>10?10:_)*21+2);
  }
  this.optionsDiv.style.height=this.optionsHeight;
  document.body.appendChild(this.optionsDiv);
  this.optionsDiv.onmousedown=function(){
    eventManager.stopEvent();
  };
  function I(A){
    A=A||window.event;
    var $=A.srcElement||A.target;
    eventManager.stopPropagation();
    if($.tagName.toLowerCase()!="tr"){
      while(($=$.parentNode)){
        if($.tagName&&$.tagName.toLowerCase()=="tr"&&$.getAttribute("__entity_rowno")){
          break ;
        }
      }
    }
    var _=getParentByTagName($,"table");
    return _&&_.className=="eos-combo-optiontable"?$:null;
  }
  function K($){
    if(L.selectOptionRow){
      removeClass(L.selectOptionRow,"eos-selectoption");
    }
    L.optionsDiv.setAttribute("_isOver",true);
    var _=I($);
    addClass(_,"eos-selectoption");
    if(L.activeOptionRow==_){
      return ;
    }
    if(L.activeOptionRow){
      removeClass(L.activeOptionRow,"eos-selectoption");
    }
    L.activeOptionRow=_;
  }
  function H($){
    L.optionsDiv.setAttribute("_isOver",false);
  }
  function J($){
    var _=I($);
    if(_){
      L.selectOption(_);
    }
    L.hide();
    try{
      L.inputObject.focus();
    }
    catch(A){
    }
  }
  eventManager.add(this.optionsDiv,"mouseover",K);
  eventManager.add(this.optionsDiv,"mouseout",function(){
    setTimeout(H,300);
  });
  eventManager.add(this.optionsDiv,"click",J);
  function E(){
    eventManager.stopPropagation();
  }
  eventManager.add(this.optionsDiv,"mousedown",E);
  var M=["<div class=\"eos-combo-optiondiv-core\"><table  class=\"eos-combo-optiontable\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" ><tbody>"];
  for(var O=0;O<_;O++){
    var A=this.datasetExp.get(O),D=A.getProperty(this.valueField),B=A.getProperty(this.textField);
    M.push("<tr __entity_rowno=\""+O+"\" >");
    M.push("<td><div><nobr>");
    M.push(B==""?" ":B);
    M.push("</nobr></div></td>");
    if(this.showValue){
      M.push("<td><div>");
      M.push(D);
      M.push("</div></td>");
    }
    M.push("</tr>");
  }
  M.push("</tbody></table ></div>");
  var F=window.isIE?"<iframe SCROLLING=\"no\" style=\"overflow:hidden;position:absolute;z-index:-1;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft)\" frameborder=\"0\" ></iframe>":"<span style=\"display:none;\"></span>";
  this.optionsDiv.innerHTML=F+M.join("\n");
  this.optionsDiv.childNodes[1].style.height=this.optionsDiv.style.height;
  this.optionsDiv.childNodes[1].style.width=this.optionsDiv.style.width;
  this.optionsDivCore=this.optionsDiv.childNodes[1];
  this.optionsTable=this.optionsDivCore.firstChild;
  var N=false;
  if(this.value){
    this.setValue(this.value,false);
  }else {
    if(this.optionsTable&&this.optionsTable.tBodies&&this.optionsTable.tBodies[0]){
      var C=this.optionsTable.tBodies[0].rows;
      if(C&&C.length>0){
        this.selectOption(C[0]);
        N=true;
      }
    }
  }
  if(!N){
    this.setSubComponent(this.getEntityByValue(this.value));
  }
  this.afterRefresh();
};
ComboSelect.prototype.autoScroll=function(A){
  var _=A.offsetTop,$=_+A.offsetHeight,C=this.optionsDivCore.scrollTop,B=C+this.optionsDivCore.clientHeight;
  if(_<C){
    this.optionsDivCore.scrollTop=_;
  }else {
    if($>B){
      this.optionsDivCore.scrollTop=C+$-B;
    }
  }
};
ComboSelect.prototype.boundTree=function(A,$,B){
  if($id(A)==null){
    alert("Can not find tree:"+treeID);
    return ;
  }
  var _;
  if($id(A).model!=null){
    _=$id(A+"_container");
  }else {
    _=$id(A+"_container").parentNode;
  }
  $=$||"";
  B=B||"";
  this.afterRefresh=function(){
    this.hidden.value=$;
    this.text.value=B;
    this.optionsDiv.childNodes[1].appendChild(_);
    _.onclick=function(){
      eventManager.stopPropagation();
    };
  };
};
ComboSelect.prototype.beforeInit=function(){
};
ComboSelect.prototype.afterInit=function(){
};
ComboSelect.prototype.beforeLoadData=function(){
};
ComboSelect.prototype.afterLoadData=function(){
};
ComboSelect.prototype.beforeRefresh=function(){
};
ComboSelect.prototype.afterRefresh=function(){
};
var DATACELL_STYLE_ROW1="EOS_table_row",DATACELL_STYLE_ROW2="ROW_STYLE2_NOCSS",DATE_FORMAT="yyyy-MM-dd",TIME_FORMAT="HH:mm:ss",DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss",PICTURE_BLANK=PICTURE_BLANK||"";
function Datacell($){
  this.id=$;
  PageControl.add($,this);
  this.activeRow=null;
  this.activeCell=null;
  this.table=null;
  this.container=null;
  this.pilot=null;
  this.thead=null;
  this.tbody=null;
  this.isCount=false;
  this.entityName=null;
  this.queryAction=null;
  this.xpath=null;
  this.submitAction=null;
  this.datasetExp=null;
  this.sortParam=null;
  this.queryParam=null;
  this.pageParam=null;
  this.editor=null;
  this.fields=new Array();
  this.keys=new Object();
  this.bodyDiv=null;
  this.scrollEvent=true;
  this.fixedLeft=0;
  this.editorDiv=null;
  this.editorContainer=null;
  this.styleEntity=null;
  this.__subComponent=new Array();
  this.linkField=null;
  this.linkId=null;
  this.filterId=null;
  this.paramFormId=null;
  this.rowStyles=[DATACELL_STYLE_ROW1,DATACELL_STYLE_ROW2];
  this.styleRow=null;
  this.status=Datacell.STATUS_BLANK;
  this.isModefied=false;
  this.cellHeight=20;
  this.heightExceptBody=23+25;
  this.headHeight=23;
  this.toolBarHeight=25;
  this.showIndex=true;
  this.indexCol=null;
  this.sortArrow=null;
  this.__pagePilot=[];
  this.allowAdd=true;
  this.allowDel=true;
  this.readonly=false;
  this.initParamFunc=null;
  this.SortByServer=false;
  this.submitXpath=null;
  this.minColumnWidth=30;
  this.toolbarLocation=null;
  this.toolsList="nav,pagesize,edit,custom,info";
  this.pageSizeList="none";
  this.defaultColumnWidth=100;
  this.topToolbarDiv=$createElement("div",{className:"eos-grid-toolbar"});
  this.topToolbarDiv.style.display="none";
  this.bottomToolbarDiv=$createElement("div",{className:"eos-grid-toolbar"});
  this.topToolbarDiv.style.display="none";
  this.isFirstLoad=true;
  this.pageSize=10;
  this.datacellContainer=null;
  this.quickEdit=true;
  this.sortAt="client";
  this.rowSelectStyleClass="eos-row-selected";
  this.rowEvenStyleClass="eos-row-even";
  this.rowStyleClass="eos-row";
  this.headTextAlign="left";
  this.width=500;
  this.height=300;
  this.entityType=null;
  this.sortNotNum=0;
}
Datacell.prototype.syncRefresh=true;
Datacell.prototype.refreshCell=function(_,A){
  var C=this,B=_.getAttribute("fieldId");
  A=A||C.keys[B];
  if(B==Datacell.__INDEX_NAME||C.beforeRefreshCell(_,A)===false){
    return ;
  }
  var D=_.firstChild,$=A.viewType;
  D.innerHTML=C.createCellValue(_,A);
};
Datacell.prototype.beforeKeyDown=function(){
};
Datacell.prototype.deletetrfun=function(){
  if(this.newTR){
    this.datacellTable.deleteRow(this.newTR.rowIndex);
    this.freezeBodyTable.deleteRow(this.newFRow.rowIndex);
    this.newTR=null;
  }
};
Datacell.prototype.clicktrfun=function(_){
  if(this.newTR){
    this.deletetrfun();
    if(this.tempTr==_){
      return ;
    }
  }
  if(this.getEntity(_).status==Entity.STATUS_NEW){
    return ;
  }
  this.tempTr=_;
  this.newTR=this.datacellTable.insertRow(_.rowIndex+1);
  var $=this.newTR.insertCell(0);
  $.colSpan=_.cells.length;
  this.newFRowt=this.styleFreezeRow.cloneNode(true);
  this.newFRow=this.freezeBodyTable.insertRow(_.rowIndex+1);
  this.newFRow.className=this.newFRowt.className;
  var A=this.newFRow.insertCell(0);
  this.newFRow.style.height=this.newFRow_height;
  $.style.height=this.newFRow_height;
  this.iframeStr=this.organ_url(this.iframeStr,this.newTR,this.newFRow,this.getEntity(_));
  $.innerHTML=this.iframeStr;
};
Datacell.prototype.arfresh_initparam=function(_,$){
  var A=this.activeRow;
  this.datacellTable=A.parentNode;
  this.newFRow_height=$;
  this.iframeStr=_;
  this.newTR=null;
  while(this.datacellTable.tagName!="TABLE"){
    this.datacellTable=this.datacellTable.parentNode;
  }
  this.onClickHead=function(A,_,$,B){
    this.deletetrfun();
  };
  this.onClickRow=function($,A,_,B){
    this.clicktrfun($);
  };
  this.beforeAdd=function($){
    this.deletetrfun();
  };
  this.beforeDel=function($){
    this.deletetrfun();
  };
};
Datacell.prototype.organ_url=function(A,_,$){
  return A;
};
Datacell.prototype.addRow_ExtEasy=function(_){
  var B=_||this.styleEntity.clone(true);
  B.status=Entity.STATUS_NEW;
  if(!this.datasetExp){
    return null;
  }
  var $=this.styleRow.cloneNode(true);
  $.setAttribute("__entity_rowno",this.datasetExp.getLength());
  this.datasetExp.addEntity(B);
  this.tbody.appendChild($);
  for(var A=0;A<$.cells.length;A++){
    $.cells[A].setAttribute("fieldId",this.fields[A].fieldId);
  }
  addClass($,(this.tbody.rows.length+1)%2==0?this.rowEvenStyleClass:"");
  var C=this.styleFreezeRow.cloneNode(true);
  C.className=$.className;
  C.appendChild(this.styleIndexColumn.cloneNode(true));
  this.freezeBodyTable.tBodies[0].appendChild(C);
  this.refreshRow($);
  this.afterAdd_Ext($);
  return $;
};
Datacell.prototype.deleteRow_ExtEasy=function($){
  if($&&$.parentNode){
    $.parentNode.removeChild($);
  }
  if(this.checkgroup&&this.checkgroup.getRows()&&$&&$.rowbox){
    $remove(this.checkgroup.getRows(),$.rowbox);
  }
};
Datacell.prototype.activeMe=function(){
  Datacell.activeGrid=this;
};
Datacell.prototype.onPageLoad=function(){
  PageControl.registerRelation(this.linkId,this.id);
  var _=this;
  function $(){
    _.init();
    _.loadData();
    _.refresh();
    if(isFF){
      _.autoResizeS1();
      _.autoResizeS2();
    }
  }
  eventManager.add(window,"load",$);
};
Datacell.prototype.createCellValue=function(cell,field){
  if(!cell){
    return ;
  }
  var row=cell.parentNode,entity=this.getEntity(row),key=cell.getAttribute("fieldId");
  if(key==Datacell.__INDEX_NAME){
    return ;
  }
  var field=this.keys[key],cellText=entity.getProperty(field.fieldName);
  if(field.onRefreshFunc){
    if(typeof (field.onRefreshFunc)=="string"){
      field.onRefreshFunc=eval(field.onRefreshFunc);
    }
    cellText=field.onRefreshFunc(cellText,entity,cell.parentNode.rowIndex,cell.cellIndex,this);
  }else {
    if(field.expression){
      cellText=runExpression(field.template,entity);
    }else {
      if(field.editor){
        cellText=htmlConversion(field.editor.getDisplayValue(cellText));
      }else {
        cellText=htmlConversion(cellText===undefined||cellText===null?field.nullText:cellText);
      }
    }
  }
  cellText=cellText===undefined||cellText===null?htmlConversion(field.nullText):cellText;
  return cellText;
};
Datacell.prototype.clear=function(){
  if(this.tbody&&this.table){
    var $=this.tbody.cloneNode(false);
    this.table.replaceChild($,this.tbody);
    this.tbody=$;
  }
  if(this.datasetExp){
    this.datasetExp.clear();
  }
  this.datasetExp=null;
  this.styleRow=null;
};
Datacell.prototype.clearup=function(){
  for(var $ in this){
    try{
      this[$]=null;
    }
    catch(_){
      $handle(_);
    }
  }
};
Datacell.prototype.getField=function(_){
  if(_===null||_===undefined){
    return _;
  }
  if(typeof _=="number"){
    return this.fields[_];
  }
  if(typeof _=="string"){
    return this.getFieldByName(_);
  }
  if(String(_.tagName).toLowerCase()=="td"){
    var $=_.getAttribute("fieldId");
    if($==Datacell.__INDEX_NAME){
      return null;
    }
    var A=this.keys[$];
    return A;
  }
  return _;
};
Datacell.prototype.getFieldByName=function(_){
  for(var $=0;$<this.fields.length;$++){
    if(this.fields[$].fieldName==_){
      return this.fields[$];
    }
  }
  return null;
};
Datacell.prototype.getFieldsByName=function(A){
  var _=[];
  for(var $=0;$<this.fields.length;$++){
    if(this.fields[$].fieldName==A){
      _.push(this.fields[$]);
    }
  }
  return _;
};
Datacell.prototype.getFieldCount=function(){
  return this.fields.length;
};
Datacell.prototype.setTitle=function(A,$){
  A=this.getField(A);
  if(A){
    A.labelSpan.innerHTML=$;
    var _=A.frozeHeadCell;
    if(_&&_.firstChild&&_.firstChild.firstChild){
      _.firstChild.firstChild.innerHTML=$;
    }
  }
};
Datacell.prototype.setAllowModify=function(_,$){
  _=this.getField(_);
  if(_){
    _.allowModify=$;
  }
};
Datacell.prototype.copyRow=function($){
  return this.cloneEntity(null,$);
};
Datacell.prototype.cloneEntity=function(_,$){
  _=_||this.getEntity($||this.activeRow);
  if(_){
    _=_.clone();
    _.status=Entity.STATUS_NEW;
  }
  return _;
};
Datacell.prototype.getCellValue=function(B){
  var A=B.parentNode,C=A?this.getEntity(A):null;
  if(C){
    var $=this.getField(B),_=$.fieldName;
    return C.getProperty(_);
  }
  return null;
};
Datacell.prototype.setCellValue=function(D,B){
  if(!D){
    return false;
  }
  var C=D.parentNode,$=C?this.getEntity(C):null;
  if($){
    var _=this.getField(D),A=_.fieldName;
    $.setProperty(A,B);
    this.refreshCell(D);
    this.refreshRow(C);
    if($.status!=Entity.STATUS_NEW){
      $.status=Entity.STATUS_MODIFIED;
    }
    this.isModefied=true;
    return true;
  }
  return false;
};
Datacell.prototype.getCell=function($,B){
  var _=parseInt($);
  if(!isNaN(_)){
    var A=this.tbody.rows[_];
    if(A){
      return this.getCellByRow(A,B);
    }
  }
  return null;
};
Datacell.prototype.getCellByRow=function(A,B){
  if(isNaN(B)){
    var $=B;
    for(var _=0;_<A.cells.length;_++){
      if(A.cells[_].getAttribute("fieldId")==$){
        return A.cells[_];
      }
    }
  }else {
    return A.cells[B];
  }
};
Datacell.prototype.getCellAtRow=function(A,_){
  var $=this.getRowByCell(A);
  return this.getCellByRow($,_);
};
Datacell.prototype.getCellByCurrRow=function($){
  if(this.activeRow){
    return this.getCellByRow(this.activeRow,$);
  }else {
    return null;
  }
};
Datacell.prototype.getRow=function($){
  if(typeof $=="number"){
    return this.getRowAt($);
  }
  if($.tagName&&$.tagName.toLowerCase()=="td"){
    return $.parentNode;
  }
  return $;
};
Datacell.prototype.getRowAt=function($){
  try{
    return this.table.tBodies[0].rows[$];
  }
  catch(_){
    return null;
  }
};
Datacell.prototype.getRowByCell=function($){
  if($){
    if($.parentNode&&$.parentNode.tagName&&$.parentNode.tagName.toLowerCase()=="tr"){
      return $.parentNode;
    }
  }
  return null;
};
Datacell.prototype.getEntityByCell=function(_){
  var $=this.getRowByCell(_);
  if($){
    return this.getEntity($);
  }
  return null;
};
Datacell.prototype.getCurrRow=function(){
  return this.activeRow;
};
Datacell.prototype.getValueForEdit=function($,_){
  return $.getProperty(_);
};
Datacell.prototype.init=function(){
  if(this.inited===true||this.beforeInit()===false){
    return ;
  }
  if(this.entityType&&!(this.entityType.indexOf("sdo:")==0||this.entityType.indexOf("java:")==0)){
    this.entityType="sdo:"+this.entityType;
  }
  this.PAGE_INFO=this.PAGE_INFO||PAGE_INFO;
  this.PAGE_RECORDINFO=this.PAGE_RECORDINFO||PAGE_RECORDINFO;
  this.PAGE_INFO_NOCOUNT=this.PAGE_INFO_NOCOUNT||PAGE_INFO_NOCOUNT;
  this.PAGE_RECORDINFO_NOCOUNT=this.PAGE_RECORDINFO_NOCOUNT||PAGE_RECORDINFO_NOCOUNT;
  this.activeMe();
  this.datacellContainer=$id(this.id+"_container");
  if(this.freezeNumber>0){
    this.showIndex=true;
  }
  this.tableMarginLeft=this.showIndex?12:0;
  this.containerTable=$id(this.id+"_container_table");
  if(isIE){
    this.containerTable.className="eos-b-ie "+this.containerTable.className;
  }
  if((""+this.width).indexOf("%")<1&&(""+this.width).indexOf("p")<1){
    this.width=parseInt(this.width)+"px";
  }
  if((""+this.height).indexOf("%")<1&&(""+this.height).indexOf("p")<1){
    this.height=parseInt(this.height)+"px";
  }
  if(this.freezeNumber&&this.freezeNumber>this.fields.length){
    this.freezeNumber=this.fields.length;
  }
  this.paramFormId=this.paramFormId||this.filterId;
  this.cTop=0;
  if(!this.toolbarLocation||this.toolbarLocation=="null"){
    this.containerTD=this.containerTable.tBodies[0].rows[0].cells[0];
    this.containerTable.tBodies[0].rows[1].style.height="0px";
    this.containerTable.tBodies[0].rows[1].style.display="none";
  }else {
    if(this.toolbarLocation=="top"){
      this.containerTD=this.containerTable.tBodies[0].rows[1].cells[0];
      this.toolbarParent=this.containerTable.tBodies[0].rows[0].cells[0];
      this.toolbarParent.appendChild(this.topToolbarDiv);
      this.toolbarParent.style.height="25px";
      this.topToolbarDiv.style.display="block";
      this.topToolbarDiv.style.borderBottomWidth="1px";
      this.cTop=this.topToolbarDiv.offsetHeight;
    }else {
      this.containerTD=this.containerTable.tBodies[0].rows[0].cells[0];
      this.toolbarParent=this.containerTable.tBodies[0].rows[1].cells[0];
      this.toolbarParent.appendChild(this.bottomToolbarDiv);
      this.toolbarParent.style.height="25px";
      this.bottomToolbarDiv.style.display="block";
      this.bottomToolbarDiv.style.borderTopWidth="1px";
    }
  }
  this.container=$createElement("div");
  this.container.className="eos-grid";
  this.containerTD.appendChild(this.container);
  this.container.innerHTML="";
  this.container.style.display="";
  this.viewport=$createElement("div");
  this.viewport.className="eos-viewport";
  this.container.style.width="100%";
  this.containerTable.style.width=this.width;
  this.containerTable.style.height=this.height;
  this.bodyDiv=$createElement("div");
  this.bodyDiv.className="eos-body-div";
  this.bodyDivHeight=this.containerTD.offsetHeight-this.headHeight;
  this.bodyDivHeight=parseInt(this.bodyDivHeight);
  if(this.bodyDivHeight<0){
    this.bodyDivHeight=parseInt(this.height)-(this.headHeight+this.toolBarHeight);
  }
  if(this.bodyDivHeight>0){
    this.bodyDiv.style.height=this.bodyDivHeight+"px";
  }
  this.headDiv=$createElement("div");
  this.headDiv.className="eos-head-div";
  this.headWrap=$createElement("div");
  this.headWrap.style.width="3000px";
  this.headDiv.appendChild(this.headWrap);
  this.container.appendChild(this.viewport);
  this.cTop=0;
  if(this.toolbarLocation=="top"){
    this.topToolbarDiv.style.display="block";
    this.topToolbarDiv.style.borderBottomWidth="1px";
    this.cTop=this.topToolbarDiv.offsetHeight;
  }else {
    this.bottomToolbarDiv.style.display="block";
    this.bottomToolbarDiv.style.borderTopWidth="1px";
  }
  this.viewport.appendChild(this.headDiv);
  this.viewport.appendChild(this.bodyDiv);
  this.viewport.style.width="100%";
  this.headDiv.style.width="100%";
  this.bodyDiv.style.width="100%";
  this.container.style.display="";
  this.splitLine=$createElement("div",{className:"eos-split-line"});
  this.splitLine.style.top=this.cTop+"px";
  this.container.appendChild(this.splitLine);
  this.gridMask=$createElement("div",{className:"eos-grid-mask"});
  this.gridMask.style.width=this.container.style.width;
  this.gridMask.style.height=this.container.style.height;
  this.container.appendChild(this.gridMask);
  this.initPageParam();
  this.initCSS();
  this.initHead();
  this.initFreezeZone();
  this.initEvent();
  for(var $=0;$<this.__pagePilot.length;$++){
    var _=this.__pagePilot[$];
    _.build();
  }
  this.inited=true;
  this.afterInit();
};
Datacell.prototype.selectEventRow=function(){
  var _=eventManager.getElement(),$=getParentByTagName(_,"tr");
  this.selectRow($);
  return $;
};
Datacell.prototype.showCellEditor=function(B,A){
  if(this.activeEditor&&this.activeCell){
    this.editorContainer=$id(this.id+"_"+this.activeField.fieldId+"_field_editor_container")||$id(this.activeField.fieldId+"_field_editor_container");
    var B=this.getValueForEdit(this.getEntity(this.activeCell),this.activeField.fieldName);
    if(this.dict){
      B=getDictKey(this.dict,B);
    }
    try{
      if(this.editorContainer.parentNode!=document.body){
        document.body.appendChild(this.editorContainer);
      }
      this.activeEditor.hideEditor();
      var $=getPosition(this.activeCell);
      if(this.activeEditor.setPosition){
        this.editorContainer.style.display="";
        this.editorContainer.onmousedown=function(){
          eventManager.stopEvent();
        };
        this.activeEditor.setPosition($.left,$.top,this.activeCell.offsetWidth,this.activeCell.offsetHeight);
        var C={};
        C.editor=this.activeEditor;
        C.datacell=this;
        C.hide=function(){
          if(this.datacell){
            this.datacell.hideCellEditor();
            Datacell.activeGrid=null;
          }
        };
        PageControl.addtoStack(C);
        this.activeEditor.showEditor(this);
      }
      this.activeEditor.setValue(B===undefined||B===null?"":B);
    }
    catch(_){
    }
  }
};
Datacell.prototype.hideCellEditor=function(){
  if(this.activeCell&&this.activeEditor){
    if(this.editing){
      var $=this.activeCell.parentNode,E=this.activeEditor.getValue(),A=this.activeValue,D=E;
      if(A!=D){
        var C=this.activeCell.getAttribute("fieldId");
        if(C==Datacell.__INDEX_NAME){
          return true;
        }
        if(!this.activeEditor.validate()){
          this.editing=false;
          this.unlocked();
          this.endEdit();
          return false;
        }
        if(this.onUpdateRow(this.activeCell.parentNode)){
          var B=this.onUpdateCell(this.activeCell,this.activeEntity,this.activeField.fieldName,E);
          (B!==false)&&this.updateEntity(this.activeEntity,this.activeField.fieldName,E);
          this.refreshAfterEdit();
          this.isModefied=true;
          if(this.linkId&&this.linkField){
            var _=PageControl.getOne(this.linkId);
            if(_){
              if(_.activeRow){
                _.getEntity(_.activeRow).status=Entity.STATUS_MODIFIED;
              }
              _.refreshAfterEdit();
              _.isModefied=true;
            }
          }
          this.activeValue=E;
        }
      }
      this.afterEdit(D,A,this);
    }
    this.editing=false;
    try{
      this.activeEditor.hideEditor();
    }
    catch(F){
    }
    return true;
  }
  return true;
};
Datacell.prototype.refreshAfterEdit=function(){
  if(this.syncRefresh){
    this.refreshRow(this.activeCell?this.activeCell.parentNode:this.activeRow);
  }else {
    this.refreshCell(this.activeCell,this.activeField);
  }
};
Datacell.prototype.startEdit=function(){
  if(!this.activeEditor||!this.activeField||!this.activeEntity||!this.activeField.allowModify&&this.activeEntity.status!=Entity.STATUS_NEW){
    return ;
  }
  this.activeMe();
  var $=this.getActiveCell();
  if(this.beforeEdit($,$.cellIndex,$.parentNode.rowIndex)==false){
    return ;
  }
  this.editing=true;
  this.showCellEditor(this.activeValue,this.activeEntity);
};
Datacell.prototype.endEdit=function(){
  this.hideCellEditor();
  this.editing=false;
  this.beforeShowEditor=false;
};
Datacell.prototype._onKeyDown=function(D){
  if(this.beforeKeyDown&&this.beforeKeyDown()){
    return false;
  }
  D=D||window.event;
  var $=this.activeCell,C=null,A=D.keyCode,B=A;
  if(B==EOSKey.ENTER){
    A=EOSKey.TAB;
  }else {
    if(B==EOSKey.TAB){
      A=EOSKey.ENTER;
    }
  }
  if(A==EOSKey.ESC){
    eventManager.stopEvent();
    this.editing=false;
    this.endEdit();
  }else {
    if(A==EOSKey.ENTER){
      var F=eventManager.getElement();
      if(F!=null&&F.tagName.toLowerCase()=="textarea"){
        return ;
      }
      eventManager.stopEvent();
      this.editing?this.endEdit():this.startEdit();
    }else {
      if(A==EOSKey.TAB){
        var G=this.editing;
        eventManager.stopEvent();
        this.endEdit();
        C=nextElement($);
        while(C&&this.getField(C.cellIndex).width<1){
          C=nextElement(C);
        }
        if(!C){
          C=nextElement($.parentNode);
          if(C){
            C=C.cells[0];
          }
        }
        this.setActiveCell(D,C);
        if(G){
          var _=this;
          window.setTimeout(function(){
            _.startEdit();
          },10);
        }
      }else {
        if($&&!this.editing){
          switch(A){
          case EOSKey.LEFT:
            eventManager.stopEvent();
            C=prevElement($);
            if(!C){
              C=prevElement($.parentNode);
              if(C){
                C=C.cells[C.cells.length-1];
              }
            }
            break ;
          case EOSKey.RIGHT:
            eventManager.stopEvent();
            C=nextElement($);
            if(!C){
              C=nextElement($.parentNode);
              if(C){
                C=C.cells[0];
              }
            }
            break ;
          case EOSKey.DOWN:
            eventManager.stopEvent();
            C=nextElement($.parentNode);
            if(C){
              C=C.cells[$.cellIndex];
            }
            break ;
          case EOSKey.UP:
            eventManager.stopEvent();
            C=prevElement($.parentNode);
            if(C){
              C=C.cells[$.cellIndex];
            }
            break ;
          }
          if(C){
            eventManager.stopEvent();
            this.setActiveCell(D,C);
            var E=this.getActiveCell(),H=this.getRowByCell(E);
            if(H!=this.getActiveRow()){
              this.selectRow(H);
            }
          }
        }
      }
    }
  }
};
Datacell.prototype.autoScroll=function(){
  var D=this.activeCell.offsetLeft+this.tableMarginLeft,G=D+this.activeCell.offsetWidth,F=this.activeCell.offsetTop,_=F+this.activeCell.offsetHeight,B=this.bodyDiv.scrollLeft,$=this.bodyDiv.scrollTop,A=B+this.bodyDiv.clientWidth,E=$+this.bodyDiv.clientHeight,C=this.freezeHeadDiv.offsetWidth;
  if(D<B){
    this.bodyDiv.scrollLeft=D-this.tableMarginLeft;
  }else {
    if(G>A){
      this.bodyDiv.scrollLeft=B+G-A;
    }
  }
  if(F<$){
    this.bodyDiv.scrollTop=F;
  }else {
    if(_>E){
      this.bodyDiv.scrollTop=$+_-E;
    }
  }
};
Datacell.prototype.setActiveCell=function($,_){
  if(arguments.length==1){
    if($!=null&&$.tagName.toLowerCase()=="td"){
      _=$;
      $=null;
    }
  }
  this.activeMe();
  $=$||window.event;
  var A=$?($.srcElement||$.target):null;
  _=_||getEventTargetByTagName($,"td");
  if(_){
    var B=_.innerHTML.toLowerCase();
    if(_!=this.activeCell){
      this.activeField=this.getField(_);
      this.activeEditor=this.activeField?this.activeField.editor:null;
      removeClass(this.activeCell,"eos-cell-actived");
      if(B.indexOf("<select")<0){
        addClass(_,"eos-cell-actived");
      }
      this.activeCell=_;
    }
    this.activeEditRow=this.activeCell.parentNode;
    this.activeEntity=this.getEntity(this.activeEditRow);
    this.activeEntityBackup=this.activeEntity.clone();
    if(this.activeEntity&&this.activeField){
      this.activeValue=this.activeEntity.getProperty(this.activeField.fieldName);
    }
    if(B.indexOf("<select")<0){
      this.autoScroll();
    }
  }
  return _;
};
Datacell.prototype.initEvent=function(){
  var C=this;
  function $($){
    C.activeMe();
    C.syncScroll();
    if(C.beforeShowEditor!==true){
      C.endEdit();
    }
  }
  eventManager.add(this.bodyDiv,"scroll",$);
  function E($){
    C.activeMe();
    C.endEdit();
    var D=getEventTargetByTagName($,"td"),B=D?D.parentNode:null;
    if(D&&D!==C.containerTD){
      C.selectRow(B);
      C.setActiveCell($,D);
      C.processCheckBox($,D);
      var _=D.cellIndex,A=B.rowIndex;
      C.onClickCell(D,_,A,C.activeEntity,C.fields[_],C);
      C.onClickRow(B,A,C.activeEntity,C);
      C.beforeShowEditor=true;
      C.startEdit();
    }
  }
  eventManager.add(this.bodyDiv,"click",E);
  function B($){
    C.activeMe();
    C.endEdit();
  }
  eventManager.add(this.headDiv,"click",B);
  function A(){
    C.activeMe();
  }
  try{
    eventManager.add(this.viewport,"mouseup",A);
    eventManager.add(this.headDiv,"mouseup",A);
    eventManager.add(this.bodyDiv,"mouseup",A);
    eventManager.add(this.datacellContainer,"mouseup",A);
    eventManager.add(this.containerTable,"mouseup",A);
    eventManager.add(this.container,"mouseup",A);
    eventManager.add(this.topToolbarDiv,"mouseup",A);
    eventManager.add(this.bottomToolbarDiv,"mouseup",A);
    eventManager.add(this.toolbarParent,"mouseup",A);
  }
  catch(G){
  }
  function F($){
    C.activeMe();
    $=$||window.event;
    var D=$.srcElement||$.target;
    eventManager.stopPropagation();
    if(D&&D.tagName.toLowerCase()!="td"){
      while((D=D.parentNode)){
        if(D.tagName&&D.tagName.toLowerCase()=="td"){
          break ;
        }
      }
    }
    if(!D.parentNode){
      return ;
    }
    var A=D.parentNode.rowIndex,B=D.cellIndex-1;
    if(B<0){
      C.selectRow(C.table.tBodies[0].rows[A],true);
      return ;
    }
    var _=C.table.tBodies[0].rows[A].cells[B];
    if(C.activeCell==_){
      C.activeFreezeCell=D;
      return ;
    }
    C.activeFreezeCell=D;
  }
  eventManager.add(this.freezeBodyDiv,"click",F);
  function D(){
    Datacell.onWindowResize(C);
  }
  if(isIE){
    if((""+C.width).indexOf("%")>0||(""+C.height).indexOf("%")>0){
      eventManager.add(window,"resize",D);
    }
  }
  if(isFF){
    if((""+C.width).indexOf("%")>0||(""+C.height).indexOf("%")>0){
      if(registerTopContainer(this.datacellContainer)){
        EOSResizeObjects.push(this);
      }
    }
  }
  function _(){
    C.clearup();
  }
  eventManager.add(window,"unload",_);
  if(!Datacell.initGlobalEvent.hasInit){
    Datacell.initGlobalEvent();
    Datacell.initGlobalEvent.hasInit=true;
  }
};
Datacell.prototype.processCheckBox=function($,C){
  var B=this,A=$.srcElement||$.target;
  if("INPUT"==A.tagName.toUpperCase()){
    if(A.getAttribute("swidthCheckbox")=="true"&&A.getAttribute("type")=="checkbox"){
      var D=B.getCellValue(C),_=B.fields[C.cellIndex];
      D=D==_.checkedValue?_.unCheckedValue:_.checkedValue;
      B.setCellValue(C,D);
    }
  }
};
Datacell.prototype.startColumnResize=function(_,A){
  if(A.allowResize===false){
    return ;
  }
  _=_||window.event;
  this.viewportXY=getElementXY(this.viewport);
  this.isColumnResizing=true;
  var $=eventManager.getPointX();
  A.oldRightX=$-this.viewportXY[0];
  this.splitLine.style.left=A.oldRightX+"px";
  this.splitLine.style.height=this.viewport.offsetHeight+"px";
  this.splitLine.style.display="block";
  this.headDiv.style.cursor="col-resize";
  this.gridMask.style.cursor="col-resize";
  this.gridMask.style.display="block";
  this.resizeColumn=A;
};
Datacell.prototype.syncScroll=function(){
  this.headDiv.scrollLeft=this.bodyDiv.scrollLeft;
  this.freezeBodyDiv.scrollTop=this.bodyDiv.scrollTop;
};
Datacell.prototype.setColumnWidth=function(_,$){
  _=this.getField(_);
  $=$<this.minColumnWidth?this.minColumnWidth:$;
  _.width=$;
  _.rules.style.width=_.width+"px";
};
Datacell.prototype.setFieldEditor=function(C,A){
  C=this.getField(C);
  var _=null;
  A=A||C.editId;
  if(A){
    _=Datacell.$editor($id(A));
  }
  if(!_){
    var B=$id(this.id+"_"+C.fieldId+"_field_editor_container")||$id(C.fieldId+"_field_editor_container"),$=firstElement(B);
    if(B&&$){
      A=$.id;
      if(A&&A.indexOf("_container")>0){
        A=A.substring(0,A.length-10);
        _=Datacell.$editor($o(A)||$id(A));
      }else {
        _=Datacell.$editor($);
      }
    }
  }
  C.editId=A;
  C.editor=_;
  if(_){
    _.hideEditor();
  }
};
Datacell.prototype.initCSS=function(){
  this.viewportXY=getElementXY(this.viewport);
  var B=[],_=this;
  for(var A=0;A<this.fields.length;A++){
    var $=this.fields[A];
    $.width=parseInt($.width||this.defaultColumnWidth);
    $.styleClass="eos-dc-col-"+this.id+"-"+A;
    $.rules=createStyle($.styleClass,document);
    $.align=$.align||null;
    $.alignStyle=$.align?"text-align:"+$.align+";":"";
    B[A]="."+$.styleClass+" { width:"+$.width+"px;  "+$.alignStyle+" }";
    $.rules.style.width=$.width+"px";
    if($.align){
      $.rules.style.textAlign=$.align;
    }
    $.allowModify=$.allowModify===undefined||$.allowModify===null?!_.readonly:!!$.allowModify;
    this.setFieldEditor($);
  }
  if(1==2&&!this.addedCSS){
    CSSUtil.createStyleSheet(B.join("\n"),this.id+"_style");
    this.SCROLLBAR_WIDTH=16;
    this.addedCSS=true;
  }
};
Datacell.prototype.initAllRows=function(A){
  if(!A&&this.allTR&&this.allTR.length>0){
    return this.allTR;
  }
  this.allTR=[];
  var _=this.table.tBodies[0].rows;
  for(var $=0;$<_.length;$++){
    this.allTR[$]=_[$];
  }
  return this.allTR;
};
Datacell.prototype.refreshRowStyle=function(){
  this.initAllRows(true);
  var B=this.freezeHeadTable?this.freezeBodyTable.tBodies[0].rows:[];
  for(var _=0;_<this.allTR.length;_++){
    var $=this.allTR[_];
    $.className=$.className.replace(this.rowEvenStyleClass,"");
    if(_%2==0){
      $.className=$.className+" "+this.rowEvenStyleClass;
    }else {
      $.className=$.className.replace(this.rowEvenStyleClass,"");
    }
    var A=B[_]||{};
    A.className=$.className;
  }
};
Datacell.prototype.getCurrentRowCount=function(){
  var $=this.table.tBodies[0].rows;
  return $?$.length:0;
};
Datacell.prototype.getPageRowCount=function(){
  var $=0;
  if(this.datasetExp){
    $=this.getCurrentRowCount();
    $=$-(this.datasetExp.getInsertEntities()?this.datasetExp.getInsertEntities().length:0);
    $=$+(this.datasetExp.getRemovedEntities()?this.datasetExp.getRemovedEntities().length:0);
  }
  return $;
};
Datacell.prototype.getTotalRowCount=function(){
  if(this.__pagePilot&&this.__pagePilot[0]){
    return this.__pagePilot[0].count||0;
  }
  return -1;
};
Datacell.prototype.getAllRows=function($){
  this.initAllRows($);
  return this.allTR;
};
Datacell.prototype.freezeColumn=function(E){
  var F=this.freezeHeadTable.tBodies[0].rows;
  for(var D=0;D<E;D++){
    var _=this.fields[D];
    _.frozeHeadCell=this.headTable.tBodies[0].rows[0].cells[D].cloneNode(true);
    _.frozeSortIcon=_.frozeHeadCell.getElementsByTagName("span")[1];
    _.frozeSplitter=_.frozeSortIcon.nextSibling;
    if(F.length>0){
      F[0].appendChild(_.frozeHeadCell);
    }
  }
  this.initAllRows();
  F=this.freezeBodyTable.tBodies[0].rows;
  var $=this.allTR.length;
  for(var C=F.length;C<$-F.length;C++){
    var G=this.styleFreezeRow.cloneNode(true);
    G.className=this.allTR[C].className;
    G.appendChild(this.styleIndexColumn.cloneNode(true));
    this.freezeBodyTable.tBodies[0].appendChild(G);
  }
  for(var A=0;A<$;A++){
    for(D=0;D<E;D++){
      var B=this.allTR[A].cells[D].cloneNode(true);
      F[A].appendChild(B);
    }
  }
};
Datacell.prototype.quickSort=function($,A,_,H,G){
  var C=this,D=$,F=A+1,J=F,B=C.allTR,K=B[$],I=_(this,K,H),E;
  if(G){
    while(true){
      while(++D<A&&_(this,B[D],H)>=I){
      }
      while(--F>$&&_(this,B[F],H)<=I){
      }
      if(D>=F){
        break ;
      }
      E=B[D];
      B[D]=B[F];
      B[F]=E;
    }
  }else {
    while(true){
      while(++D<A&&_(this,B[D],H)<=I){
      }
      while(--F>$&&_(this,B[F],H)>=I){
      }
      if(D>=F){
        break ;
      }
      E=B[D];
      B[D]=B[F];
      B[F]=E;
    }
  }
  B[$]=B[F];
  B[F]=K;
  F-=1;
  if($<F){
    C.quickSort($,F,_,H,G);
  }
  F+=2;
  if(F<A){
    C.quickSort(F,A,_,H,G);
  }
  return B;
};
Datacell.prototype.doSort=function(I){
  if(!I.sortAt||I.sortAt=="null"||I.sortAt=="none"||I.sortAt=="undefined"){
    return ;
  }
  this.initAllRows();
  if(this.allTR.length<1){
    return ;
  }
  if(this.lastSortField){
    removeClass(this.lastSortField.sortIcon,"eos-hd-asc");
    removeClass(this.lastSortField.frozeSortIcon,"eos-hd-asc");
    removeClass(this.lastSortField.sortIcon,"eos-hd-desc");
    removeClass(this.lastSortField.frozeSortIcon,"eos-hd-desc");
  }
  if(I.sorted=="asc"){
    I.sorted="desc";
    addClass(I.sortIcon,"eos-hd-desc");
    addClass(I.frozeSortIcon,"eos-hd-desc");
  }else {
    if(I.sorted=="desc"){
      I.sorted="";
    }else {
      I.sorted="asc";
      addClass(I.sortIcon,"eos-hd-asc");
      addClass(I.frozeSortIcon,"eos-hd-asc");
    }
  }
  this.lastSortField=I;
  if(I.sortAt){
    I.sortAt=(""+I.sortAt).toLowerCase();
    if(I.sortAt=="server"||I.sortAt.indexOf("s")==0){
      var C=["<_orderby>","<_property>",I.fieldName,"</_property>","<_sort>",I.sorted,"</_sort>","</_orderby>"];
      this.sortParam=C.join("\n");
      try{
        this.endEdit();
      }
      catch(F){
      }
      var H=0;
      if(!this.isCount){
        H=-1;
      }
      var G=this.setPageParam(0,this.pageSize,H,this.isCount);
      this.gotoPage(G);
      return ;
    }
  }
  if(I.sorted){
    this.quickSort(0,this.allTR.length-1-this.sortNotNum,Datacell.getSortValue,I.fieldName,I.sorted=="desc");
  }else {
    this.quickSort(0,this.allTR.length-1-this.sortNotNum,Datacell.getSortDefaultValue,I.fieldName,I.sorted=="desc");
  }
  var K=this.freezeBodyTable.tBodies[0].rows,B=this.showIndex?1:0,_=document.createDocumentFragment();
  for(var J=0;J<this.allTR.length;J++){
    var E=this.allTR[J];
    E.className=E.className.replace(this.rowEvenStyleClass,"");
    if(J%2==0){
      E.className=E.className+" "+this.rowEvenStyleClass;
    }else {
      E.className=E.className.replace(this.rowEvenStyleClass,"");
    }
    for(var $=0;$<this.freezeNumber;$++){
      var D=K[J].cells[$+B],A=E.cells[$].cloneNode(true);
      K[J].className=E.className;
      K[J].replaceChild(A,D);
    }
    _.appendChild(E);
  }
  this.table.tBodies[0].appendChild(_);
};
Datacell.prototype.initHeadEvent=function(){
  if(this.hasInitHeadEvent){
    return ;
  }
  var _=this,B=_.fields.length;
  for(var A=0;A<B;A++){
    var $=_.fields[A];
    (function($){
      eventManager.add($.splitter,"mousedown",function(A){
        _.startColumnResize(A,$);
      });
      eventManager.add($.frozeSplitter,"mousedown",function(A){
        _.startColumnResize(A,$);
      });
      eventManager.add($.headCell,"mouseover",function(_){
        addClass($.headCell,"eos-hd-row-over");
      });
      eventManager.add($.frozeHeadCell,"mouseover",function(_){
        addClass($.frozeHeadCell,"eos-hd-row-over");
      });
      eventManager.add($.headCell,"mouseout",function(_){
        removeClass($.headCell,"eos-hd-row-over");
      });
      eventManager.add($.frozeHeadCell,"mouseout",function(_){
        removeClass($.frozeHeadCell,"eos-hd-row-over");
      });
      eventManager.add($.headCell,"click",function(B){
        _.onClickHead($.headCell,A,$,_);
        _.doSort($);
      });
      eventManager.add($.frozeHeadCell,"click",function(A){
        _.doSort($);
      });
    })($);
  }
  this.hasInitHeadEvent=true;
};
Datacell.prototype.initHead=function(){
  this.headTable=$createElement("table",{className:"eos-head-table",cellSpacing:"0",cellPadding:"0",border:"0"});
  this.headWrap.appendChild(this.headTable);
  this.headTbody=$createElement("tbody");
  this.headTable.appendChild(this.headTbody);
  var J=this.fields.length,A=[];
  for(var B=0;B<J;B++){
    this.keys[this.fields[B].fieldId]=this.fields[B];
    A.push(this.fields[B].fieldId);
  }
  var C=$createElement("tr");
  C.className="eos-hd-row";
  this.headTbody.appendChild(C);
  this.styleRow=$createElement("tr",{className:this.rowStyleClass});
  this.styleFreezeRow=$createElement("tr",{className:this.rowStyleClass});
  var G=this.getSubmitXpath(),D=false;
  if(!this.styleEntity){
    this.styleEntity=this.styleEntity||new Entity(G);
    this.styleEntity.id=0;
    this.styleEntity.__type=this.entityType;
    D=true;
  }
  for(var I=0;I<J;I++){
    var _=$createElement("td"),K=this.fields[I];
    if(D){
      this.initStyleField(K);
    }
    _.className=K.styleClass;
    if(K.width<1){
      _.style.display="none";
    }
    _.style.textAlign=this.headTextAlign;
    var E=K.align;
    if(E){
    }else {
      E="left";
    }
    _.innerHTML="<div class=\"eos-hd-inner "+K.styleClass+"\" unselectable=\"on\" style=\"text-align:"+E+"\" >"+"<span>"+K.label+"</span>"+"<div class=\"eos-hd-tool\" ><span class=\"eos-hd-icon\"></span><span class=\"eos-hd-split\">&#160;</span></div>"+"</div>";
    C.appendChild(_);
    K.headCell=_;
    K.labelSpan=_.firstChild.firstChild;
    K.sortIcon=_.getElementsByTagName("span")[1];
    K.splitter=K.sortIcon.nextSibling;
    K.splitter.colID=I;
    K.splitter.style.cursor="col-resize";
    K.defaultValue=K.defaultValue||"";
    K.sortAt=K.sortAt||"client";
    K.nullText=K.nullText||"";
    K.template=K.expression?compileTemplate(K.expression):null;
    var F=$createElement("td",{innerHTML:"<div class=\"eos-inner "+K.styleClass+"\"  ></div>",className:K.styleClass});
    F.setAttribute("name",K.fieldId);
    this.styleRow.appendChild(F);
  }
  var H=false;
  if(!this.datasetExp){
    this.datasetExp=new Dataset(this.entityName);
  }
  var $=0;
  return ;
};
Datacell.prototype.initStyleField=function(_){
  if(_.editType=="entity"){
    var $=new Entity(_.fieldName);
    this.styleEntity.setProperty(_.fieldName,$);
  }else {
    if(_.editType=="dataset"){
      $=new Dataset(_.fieldName);
      this.styleEntity.setProperty(_.fieldName,$);
    }else {
      if(_.defaultValue!==null&&_.defaultValue!==undefined){
        this.styleEntity.setProperty(_.fieldName,_.defaultValue);
      }else {
        this.styleEntity.setProperty(_.fieldName,"");
      }
    }
  }
};
Datacell.prototype.initFreezeZone=function(){
  this.freezeHeadDiv=$createElement("div",{className:"eos-freeze-div"});
  this.freezeBodyDiv=$createElement("div",{className:"eos-freeze-div"});
  this.viewport.appendChild(this.freezeHeadDiv);
  this.viewport.appendChild(this.freezeBodyDiv);
  this.cTop=0;
  this.freezeHeadDiv.style.top=this.cTop+"px";
  this.freezeBodyDiv.style.top=this.cTop+this.headHeight+"px";
  this.freezeHeadTable=$createElement("table",{id:this.headTable.id+"_freeze",className:"eos-head-table",cellSpacing:"0",cellPadding:"0",border:"0"});
  this.freezeHeadDiv.appendChild(this.freezeHeadTable);
};
Datacell.prototype.initBody=function(){
  var tableStartHTML="<table class=\"eos-table\" cellSpacing=\"0\"  cellPadding=\"0\" border=\"0\" ><tbody>",trHTML=["<tr class=\""+this.rowStyleClass," "+this.rowEvenStyleClass,"\" __entity_rowno=\"","\" >\n","</tr>\n"],colNum=this.fields.length,rn=0,tableHTML=[],rowHTML,freezeTableHTML=[tableStartHTML],indexColumnWidth=this.tableMarginLeft-(isBorderBox?0:2),indexColumnDivWidth=this.tableMarginLeft-(isBorderBox?0:4),tdStyleClass=isIE?"style=\"width:"+this.tableMarginLeft+"px;\"":"",divStyleClass=isIE?"":"style=\"width:"+(this.tableMarginLeft-(isBorderBox?0:4))+"px;\"",indexColumnCell=["<td class=\"eos-index-col\" "+tdStyleClass+" ><div class=\"eos-inner\" style=\"width:"+indexColumnDivWidth+"px\" >","</div></td>"];
  this.styleIndexColumn=$createElement("td",{className:"eos-index-col"});
  if(isIE){
    this.styleIndexColumn.style.width=this.tableMarginLeft+"px";
  }
  this.styleIndexColumn.innerHTML="<div class=\"eos-inner\" "+divStyleClass+" >&#160;</div>";
  if(this.freezeBodyTable){
    removeElement(this.freezeBodyTable);
    removeElement(this.freezeHeadTable.tBodies[0]);
  }
  this.freezeHeadTable.appendChild($createElement("tbody"));
  if(this.showIndex){
    var headRow=$createElement("tr",{className:"eos-hd-row"});
    this.freezeHeadTable.tBodies[0].appendChild(headRow);
    var colN=$createElement("td",{innerHTML:"<div class=\"eos-hd-inner\" style=\"width:"+(indexColumnDivWidth-1)+"px\" >&#160;</div>",className:"eos-index-col"});
    if(isIE){
      colN.style.width=indexColumnWidth+"px";
    }
    headRow.appendChild(colN);
    this.freezeHeadDiv.style.display="block";
    this.freezeHeadDiv.style.left="0px";
  }
  var datasetLength=!this.datasetExp?0:this.datasetExp.getLength(),rowNum=this.pageSize||datasetLength;
  rowNum=(rowNum<0||datasetLength<rowNum)?datasetLength:rowNum;
  var colName=[];
  for(var i=0;i<colNum;i++){
    colName.push(this.fields[i].fieldId);
  }
  tableHTML.push(tableStartHTML);
  for(rn=0;rn<rowNum;){
    var entity=this.datasetExp.get(rn);
    rowHTML=[trHTML[0]+(rn%2==0?trHTML[1]:"")+trHTML[2]+rn+trHTML[3]];
    if(this.showIndex){
      freezeTableHTML.push(rowHTML[0]);
      freezeTableHTML.push(indexColumnCell[0]);
      freezeTableHTML.push("&#160;"+indexColumnCell[1]);
      freezeTableHTML.push("</tr>\n");
    }
    for(var cn=0;cn<colNum;cn++){
      var field=this.fields[cn],cellText=entity.getProperty(field.fieldName),fieStr="";
      if(field.width<1){
        fieStr=" style=\"display: none;\"";
      }
      if(field.onRefreshFunc){
        if(typeof (field.onRefreshFunc)=="string"){
          field.onRefreshFunc=eval(field.onRefreshFunc);
        }
        cellText=field.onRefreshFunc(cellText,entity,rn,cn,this);
      }else {
        if(field.expression){
          cellText=runExpression(field.template,entity);
        }else {
          if(field.editor){
            cellText=htmlConversion(field.editor.getDisplayValue(cellText));
          }else {
            cellText=htmlConversion(cellText===undefined||cellText===null?field.nullText:cellText);
          }
        }
      }
      cellText=cellText===undefined||cellText===null?field.nullText:cellText;
      rowHTML.push("<td "+fieStr+" fieldId=\""+colName[cn]+"\" class=\""+field.styleClass+" \"><div class=\"eos-inner "+field.styleClass+"\" >"+cellText+"</div></td>");
    }
    rowHTML.push(trHTML[4]);
    tableHTML.push(rowHTML.join(""));
    rn++;
  }
  for(var bi=0;bi<this.pageSize-datasetLength;bi++){
    for(cn=0;cn<colNum;cn++){
    }
  }
  tableHTML.push("</tbody></table>");
  this.bodyDiv.innerHTML=tableHTML.join("");
  this.table=this.bodyDiv.firstChild;
  this.table.datacell=this;
  this.tbody=this.table.tBodies[0];
  this.freezeBodyDiv.innerHTML=freezeTableHTML.join("");
  this.freezeBodyTable=this.freezeBodyDiv.firstChild;
  this.isModefied=false;
  if(this.showIndex){
    this.headTable.style.marginLeft=this.tableMarginLeft+1+"px";
    this.table.style.marginLeft=this.tableMarginLeft+1+"px";
  }else {
    this.headTable.style.marginLeft=0+"px";
    this.table.style.marginLeft=0+"px";
  }
  this.initAllRows(true);
  if(this.freezeNumber>0){
    this.freezeColumn(this.freezeNumber);
  }
  this.initHeadEvent();
  var datacell=this;
  function tt(){
    datacell.freezeBodyDiv.style.height=datacell.bodyDivHeight-0+"px";
    if(datacell.headDiv.clientHeight>10){
      datacell.freezeBodyDiv.style.height=datacell.bodyDiv.clientHeight+"px";
    }
  }
  tt();
  return ;
};
Datacell.prototype.initStyleRow=function(){
  if(!this.rowStyles instanceof Array){
    this.rowStyles=this.rowStyles.split(",");
  }
};
Datacell.prototype.locked=function(){
  this.endEdit();
  var $=$id(Datacell.BLOLK_ID),A=$id(Datacell.BLOLK_ID+"_IMG");
  if(!$){
    $=$createElement("div");
    document.body.appendChild($);
    $.id=Datacell.BLOLK_ID;
  }
  if(!A&&PICTURE_BLANK){
    A=$createElement("img");
    A.src=PICTURE_BLANK;
    A.id=Datacell.BLOLK_ID+"_IMG";
    $.appendChild(A);
  }
  $.style.cursor="wait";
  $.style.position="absolute";
  $.style.zIndex="1";
  var _=getPosition(this.container);
  $.style.top=_.top;
  $.style.left=_.left;
  $.style.width=this.container.offsetWidth;
  $.style.height=this.container.offsetHeight;
  if(A){
    A.style.width=$.style.width;
    A.style.height=$.style.height;
    A.border="0";
  }
  $.style.display="block";
};
Datacell.prototype.unlocked=function(){
  var $=$id(Datacell.BLOLK_ID);
  if($){
    $.style.display="none";
  }
};
Datacell.prototype.insertEmptyRow=function(){
  return this.insertRow(this.styleEntity.clone(true));
};
Datacell.prototype.insertRow=function(_,$){
  if(typeof $=="number"){
    $=this.getRowAt($);
  }
  _=_||this.cloneEntity()||this.styleEntity.clone(true);
  if($&&_){
    _.status=Entity.STATUS_NEW;
    var E=$.rowIndex;
    removeClass(this.activeCell,"eos-cell-actived");
    var C=$.cloneNode(true);
    addClass(this.activeCell,"eos-cell-actived");
    this.datasetExp.addEntity(_);
    this.tbody.insertBefore(C,$);
    C.setAttribute("__entity_rowno",this.datasetExp.getLength()-1);
    var D=this.freezeBodyTable.tBodies[0].rows[E],B=this.styleFreezeRow.cloneNode(true);
    B.className=C.className;
    B.appendChild(this.styleIndexColumn.cloneNode(true));
    this.freezeBodyTable.tBodies[0].insertBefore(B,D);
    this.refreshRow(C);
    this.selectRow(C);
    this.isModefied=true;
    if(this.linkId&&this.linkField){
      var A=PageControl.getOne(this.linkId);
      if(A){
        A.isModefied=true;
      }
    }
    return C;
  }
  return this.addRow(_);
};
Datacell.prototype.addRow=function(A){
  this.endEdit();
  if(!this.allowAdd||this.readonly){
    return null;
  }
  var C=this.tbody.rows.length;
  if(C<0){
    C=0;
  }
  var G=A||this.styleEntity.clone(true);
  G.status=Entity.STATUS_NEW;
  if(!this.datasetExp){
    return null;
  }
  var _=this.styleRow.cloneNode(true);
  _.setAttribute("__entity_rowno",this.datasetExp.getLength());
  if(this.beforeAdd(_)===false){
    _=null;
    delete _;
    return null;
  }
  this.datasetExp.addEntity(G);
  this.tbody.appendChild(_);
  for(var D=0;D<_.cells.length;D++){
    var F=_.cells[D];
    F.setAttribute("fieldId",this.fields[D].fieldId);
    var J=this.fields[D];
    if(J.width<1){
      F.style.display="none";
    }
  }
  C=(this.tbody.rows.length+1)%this.rowStyles.length;
  addClass(_,(this.tbody.rows.length+1)%2==0?this.rowEvenStyleClass:"");
  var I=this.styleFreezeRow.cloneNode(true);
  I.className=_.className;
  I.appendChild(this.styleIndexColumn.cloneNode(true));
  this.freezeBodyTable.tBodies[0].appendChild(I);
  for(D=0;D<this.freezeNumber;D++){
    var $=_.cells[D].cloneNode(true);
    $.setAttribute("fieldId",this.fields[D].fieldId);
    I.appendChild($);
  }
  this.refreshRow(_);
  this.isModefied=true;
  this.initAllRows(true);
  this.bodyDiv.scrollTop=this.bodyDiv.scrollHeight;
  this.freezeBodyDiv.scrollTop=this.bodyDiv.scrollTop;
  this.selectRow(_);
  for(var E=0;E<this.fields.length;E++){
    var B=this.fields[E];
    if(B.editor){
      this.setActiveCell(null,_.cells[E]);
      this.startEdit();
      break ;
    }
  }
  if(this.linkId&&this.linkField){
    var H=PageControl.getOne(this.linkId);
    if(H){
      H.isModefied=true;
    }
  }
  this.refreshRowStyle();
  this.afterAdd(_);
  return _;
};
Datacell.prototype.deleteRow=function(_){
  if(!this.allowDel||this.readonly){
    return ;
  }
  _=_===0?0:(_||this.activeRow);
  if(_==null){
    return ;
  }
  _=this.getRow(_);
  if(this.beforeDel(_)!==false&&_){
    this.selectNextRow();
    if(this.activeCell){
      this.endEdit();
    }
    if(this.getEntity(_).status==Entity.STATUS_NEW){
      this.getEntity(_).status=Entity.STATUS_HIDDEN;
    }else {
      var B=false;
      if(this.linkId&&this.linkField){
        var $=PageControl.getOne(this.linkId);
        if($){
          $.isModefied=true;
          B=true;
        }
      }
      this.datasetExp.removeEntity(this.getEntity(_),B);
    }
    var A=this.freezeBodyTable.tBodies[0].rows[_.rowIndex];
    if(_&&_.parentNode){
      _.parentNode.removeChild(_);
    }
    if(A&&A.parentNode){
      A.parentNode.removeChild(A);
    }
    this.isModefied=true;
    this.initAllRows(true);
    this.refreshRowStyle();
    this.afterDel(_);
    this.afterDelMess(_);
  }
};
Datacell.prototype.updateEntity=function(_,A,$){
  _.setProperty(A,$);
};
Datacell.prototype.getEntity=function(A){
  var _=A;
  if(typeof (_)=="number"){
    _=this.table.tBodies[0].rows[_];
  }
  if(!_){
    return null;
  }
  if(_.tagName.toLowerCase()=="td"){
    _=_.parentNode;
  }
  var $=_.getAttribute("__entity_rowno")/1,B=this.datasetExp.get($);
  return B;
};
Datacell.prototype.selectRow=function(B,C){
  this.endEdit();
  B=this.getRow(B);
  if(this.beforeSelectRow(B)===false){
    return false;
  }
  var $=true;
  if(this.activeRow!=null){
    if(this.activeRow==B){
      $=false;
    }else {
      if(!this.unSelectRow()){
        this.afterSelectRow(B);
        return false;
      }
    }
  }
  this.activeRow=this.activeRow||B;
  addClass(B,this.rowSelectStyleClass);
  if($||C){
    this.setSubComponent(this.getEntity(B));
  }
  var _=this.getEntity(B);
  this.onSelectRow(B,_);
  var A=this;
  if(_){
    _.__onUpdate=function(){
      if(A.getEntity(B).status!=Entity.STATUS_NEW){
        A.getEntity(B).status=Entity.STATUS_MODIFIED;
      }
      if(A.syncRefresh){
        A.refreshRow(B);
      }else {
        A.refreshCell(A.activeCell,A.activeField);
      }
      A.isModefied=true;
    };
  }
  this.afterSelectRow(this.activeRow);
  return true;
};
Datacell.prototype.setSubComponent=function(A){
  if(this.submitXpath&&A){
    A.name=this.getSubmitXpath();
  }
  var $=this;
  setTimeout(_,100);
  function _(){
    for(var C=0;C<$.__subComponent.length;C++){
      var _=$.__subComponent[C];
      try{
        _.freshFromEntity(A);
      }
      catch(B){
      }
    }
  }
};
Datacell.prototype.freshFromEntity=function($){
  if(this.queryAction){
    if($){
      this.queryParam=$.toString();
    }else {
      this.queryParam=null;
    }
    this.loadData();
  }else {
    if(this.linkField){
      if($){
        this.datasetExp=$.getProperty(this.linkField);
        if(!this.datasetExp){
          this.datasetExp=new Dataset(this.linkField);
          $.setProperty(this.linkField,this.datasetExp);
        }
      }else {
        this.datasetExp=new Dataset(this.linkField);
      }
    }
  }
  this.refresh();
};
Datacell.prototype.registerSubComponent=function(_){
  var $=PageControl.getOne(_);
  this.__subComponent.push($);
};
Datacell.prototype.initCell=function(B,C){
  var A=B.getAttribute("fieldId");
  if(A==Datacell.__INDEX_NAME){
    return ;
  }
  var $=this.keys[A],_=$.fieldName;
  this.refreshCell(B,$);
};
Datacell.prototype.refreshRow=function($,A){
  var G=$.cells.length;
  A=A||this.getEntity($);
  for(var _=0;_<G;_++){
    cell=$.cells[_];
    var C=cell.getAttribute("fieldId");
    if(C==Datacell.__INDEX_NAME){
      continue ;
    }
    var B=this.keys[C],F=B.fieldName,D=A.getProperty(F)?A.getProperty(F):"";
    if(A==this.activeEntity&&D!=this.activeEntityBackup.getProperty(F)){
      try{
        var E=cell.parentNode.rowIndex;
        if(this.showIndex){
          addClass(this.freezeBodyTable.tBodies[0].rows[E].cells[0],"eos-row-modefied");
        }else {
          addClass(cell.parentNode,"eos-row-modefied");
        }
      }
      catch(H){
        addClass(cell.parentNode,"eos-row-modefied");
      }
    }
    this.refreshCell(cell,B);
  }
};
Datacell.prototype.refresh=function(){
  this.init();
  if(this.beforeRefresh()===false){
    return ;
  }
  this.activeEditor=null;
  this.activeCell=null;
  this.activeRow=null;
  this.activeField=null;
  this.activeValue=null;
  this.initBody();
  this.status=Datacell.STATUS_BLANK;
  this.onComplete();
  this.selectFirstRow();
  this.afterRefresh();
};
Datacell.prototype.reSortBody=function(_){
  var A=this.tbody.cloneNode(false);
  for(var $=0;$<_.length;$++){
    A.appendChild(_[$]);
  }
  this.table.replaceChild(A,this.tbody);
  this.tbody=A;
};
Datacell.prototype.serverSort=function(_){
  var $=_.field;
  _.sortType=_.sortType=="_desc"?"_asc":"_desc";
  this.sortParam="<_orderby><_property>"+$.fieldName+"</_property><_sort>"+_.sortType+"</_sort></_orderby>";
  this.reload();
};
Datacell.prototype.refreshSortTh=function($){
  if(!this.sortArrow){
    this.sortArrow=$createElement("img");
  }
  bodyAddNode(this.sortArrow);
  $.container.appendChild(this.sortArrow);
  if($.sortType=="_desc"){
    this.sortArrow.src=PICTURE_SORT_DOWN;
  }else {
    if($.sortType=="_asc"){
      this.sortArrow.src=PICTURE_SORT_UP;
    }
  }
};
Datacell.prototype.setPageParam=function($,B,A,_){
  this.pageParam="<page><begin>"+$+"</begin><length>"+B+"</length><count>"+A+"</count><isCount>"+(!!_)+"</isCount></page>";
  return this.pageParam;
};
Datacell.prototype.initPageParam=function(){
  if(this.pageSizeList&&this.pageSizeList!="null"&&this.pageSizeList!="none"&&(","+this.pageSizeList+",").indexOf(","+this.pageSize+",")<0){
    this.pageSizeList+=","+this.pageSize;
  }
  var $=0;
  if(!this.isCount){
    $=-1;
  }
  this.setPageParam(0,this.pageSize,$,this.isCount);
};
Datacell.prototype.initAttachParam=function(){
};
Datacell.prototype.getQueryForm=function(){
  var $="<criteria><_entity>"+(this.entityName||"")+"</_entity>";
  if(this.sortParam){
    $+=this.sortParam;
  }
  $+="</criteria>";
  return $;
};
Datacell.prototype.setJsonDataset=function(json){
  if(typeof (json)=="string"){
    json=eval(json);
  }
  if(!(json instanceof Dataset)){
    json=Json2Dataset(json);
  }
  this.datasetExp=json;
  this.dataXML="<json/>";
};
Datacell.prototype.loadJsonData=function(){
};
Datacell.prototype.addParam=function(key,value){
  this.paramList=this.paramList||[];
  var keyJson=eval("this.paramList."+key);
  if(keyJson){
    keyJson.value=value;
  }else {
    keyJson={key:key,value:value};
    this.paramList.push(keyJson);
    eval("this.paramList."+key+"=keyJson;");
  }
};
Datacell.prototype.loadData=function(){
  if(this.beforeLoadData()===false){
    return ;
  }
  if(this.dataXML=="<json/>"){
    return ;
  }
  if(!this.linkId&&!this.queryAction&&this.xpath){
    var xmlZone=document.getElementById(this.id+"_xml");
    this.dataXML=xmlZone?xmlZone.innerHTML:null;
  }else {
    if(this.linkId&&this.linkId.indexOf("xml:")==0){
      xmlZone=document.getElementById(this.linkId.substring(4));
      this.dataXML=xmlZone?xmlZone.innerHTML:null;
    }else {
      if(this.linkId&&this.isFirstLoad){
        this.isFirstLoad=false;
        return ;
      }
    }
  }
  var xmlDom;
  if(this.dataXML&&this.dataXML!=="<json/>"){
    this.datasetExp=Dataset.create(this.dataXML,this.xpath);
    xmlDom=createXmlDom();
    xmlDom.loadXML(this.dataXML);
    var pagecond=xmlDom.selectSingleNode("/root/data/page");
    this.freshPagePilot(pagecond);
    return ;
  }
  if(this.onLoadData()!==false){
    if(!this.queryAction){
      return ;
    }
    var ajax=new HideSubmit(this.queryAction);
    ajax.sumbitType="2";
    var param=this.getQueryForm();
    if(this.queryParam){
      param+=this.queryParam;
    }
    if(this.pageParam){
      param+=this.pageParam;
    }
    if(this.initParamFunc){
      try{
        param+=eval(this.initParamFunc+"()");
      }
      catch(e){
        $handle(e);
      }
    }
    if(param==""){
      param=null;
    }
    if(this.paramFormId){
      var form=$id(this.paramFormId);
      if(form){
        for(var i=0;i<form.elements.length;i++){
          var elem=form.elements[i];
          if(elem.name){
            ajax.addParam(elem.name,getElementValue(elem));
          }
        }
      }
    }
    if(this.paramList){
      for(i=0;i<this.paramList.length;i++){
        elem=this.paramList[i];
        if(elem){
          ajax.addParam(elem.key,elem.value);
        }
      }
    }
    ajax.loadData(param);
    xmlDom=ajax.retDom;
    this.datasetExp=Dataset.create(xmlDom,this.xpath,this.getSubmitXpath());
    pagecond=xmlDom.selectSingleNode("/root/data/page");
    if(pagecond){
      this.freshPagePilot(pagecond);
    }
    this.afterLoadData(ajax);
  }
};
Datacell.prototype.freshPagePilot=function(B){
  var $=Dataset.initEntity(B);
  if(!B){
    var A=1,D=this.datasetExp?this.datasetExp.getLength():0;
    if(D<A){
      A=0;
      D=A;
    }
    for(var C=0;C<this.__pagePilot.length;C++){
      var _=this.__pagePilot[C];
      if(_.gotoPageText){
        _.gotoPageText.value=1;
      }
      if(_.pageInfoDiv){
        _.pageInfoDiv.innerHTML="<nobr>";
        _.pageInfoDiv.innerHTML+="<nobr>"+this.PAGE_RECORDINFO_NOCOUNT.replace("{0}",A).replace("{1}",this.datasetExp.getLength())+"</nobr>";
        _.pageInfoDiv.innerHTML+="</nobr>";
      }
      _.freshPilot($,this.readonly,this.allowAdd,this.allowDel);
    }
    return ;
  }
  this.totalPage=Number($.getProperty("totalPage"));
  this.begin=Number($.getProperty("begin"));
  this.pageSize=Number((!this.pageSize||this.pageSize<1)?$.getProperty("length"):this.pageSize);
  this.currentPage=Number($.getProperty("currentPage"));
  if(parseInt(this.currentPage)<1){
    this.currentPage=1;
    $.setProperty("currentPage",1);
  }
  var E=Number($.getProperty("totalPage")),F=Number($.getProperty("begin"));
  if((""+E)!="-1"){
    this.SortByServer=true;
  }else {
    if((""+F)=="0"){
      this.SortByServer=false;
    }else {
      this.SortByServer=true;
    }
  }
  for(C=0;C<this.__pagePilot.length;C++){
    _=this.__pagePilot[C];
    _.freshPilot($,this.readonly,this.allowAdd,this.allowDel);
  }
};
Datacell.prototype.registerPagePilot=function($){
  this.__pagePilot.push($);
  $.datacell=this;
};
Datacell.prototype.reload=function($,_){
  $=$!==undefined&&$!==null?$:this.recount;
  if($){
    if(_){
      this.begin=_;
    }else {
      this.begin=0;
    }
    this.setPageParam(this.begin,this.pageSize,-1,this.isCount);
  }
  this.loadData();
  this.refresh();
};
Datacell.prototype.selectNextRow=function(){
  if(this.activeRow){
    var _=nextElement(this.activeRow),$=prevElement(this.activeRow);
    if(_){
      this.selectRow(_);
    }else {
      if($){
        this.selectRow($);
      }
    }
  }
};
Datacell.prototype.selectPreRow=function(){
  if(this.activeRow){
    var _=nextElement(this.activeRow),$=prevElement(this.activeRow);
    if($){
      this.selectRow($);
    }else {
      if(_){
        this.selectRow(_);
      }
    }
  }
};
Datacell.prototype.selectFirstRow=function(){
  if(this.tbody.rows.length>0){
    var $=this.tbody.rows[0];
    this.selectRow($);
  }else {
    this.setSubComponent(null);
  }
};
Datacell.prototype.selectLastRow=function(){
  if(this.tbody.rows.length>0){
    var $=this.tbody.rows[this.tbody.rows.length-1];
    this.selectRow($);
  }
};
Datacell.prototype.gotoPage=function($){
  if(this.isModefied){
    if(window.confirm(DATACELL_MODIFY_CONFIRM)){
      this.submit();
    }
  }
  this.pageParam=$;
  this.reload();
  this.onPageChange();
};
Datacell.prototype.validatePage=function(){
  return true;
};
Datacell.prototype.validateAll=function(){
  var _=this.activeEntity,I=this.getActiveCell(),H=this.activeRow;
  for(var D=0;D<this.table.tBodies[0].rows.length;D++){
    var $=this.table.tBodies[0].rows[D],B=this.getEntity($);
    if(B.status==Entity.STATUS_NEW||B.status==Entity.STATUS_MODIFIED){
      for(var F=0;F<this.fields.length;F++){
        var G=this.fields[F];
        if(G.editor){
          var K=G.editor,E=B.getProperty(G.fieldName);
          K.setValue(E);
          var A;
          try{
            A=K.validate();
          }
          catch(C){
            A=false;
          }
          if(!A){
            this.setActiveCell($.cells[F]);
            this.startEdit();
            try{
              A=K.validate();
            }
            catch(J){
              A=false;
            }
            this.unlocked();
            return false;
          }
        }
      }
    }
  }
  this.activeEntity=_;
  this.activeCell=I;
  this.activeRow=H;
  return true;
};
Datacell.prototype.save=function(){
  if(this.beforeSave()!==false){
    this.submit();
    this.afterSave();
  }
};
Datacell.prototype.submit=function(){
  if(this.beforeSubmit()===false||!this.submitAction){
    return ;
  }
  this.locked();
  this.endEdit();
  if(!this.isModefied){
    alert(DATACELL_MODIFY_NO);
  }else {
    if(!this.validateAll()){
      return false;
    }
    var C=false;
    if(!C){
      var B=new HideSubmit(this.submitAction);
      B.sumbitType="3";
      if(this.paramFormId){
        var _=$id(this.paramFormId);
        if(_){
          for(var D=0;D<_.elements.length;D++){
            var E=_.elements[D];
            if(E.name){
              B.addParam(E.name,getElementValue(E));
            }
          }
        }
      }
      if(this.paramList){
        for(D=0;D<this.paramList.length;D++){
          E=this.paramList[D];
          if(E){
            B.addParam(E.key,E.value);
          }
        }
      }
      B.loadData(this.datasetExp.getSubmitXML(this));
      if(B.retDom){
        var $=B.retDom.selectNodes("\rootexception");
        if($&&$.length>0){
          var A=getNodeValue($[0]);
          if(A&&(""+A).indexOf("e")>=0){
            alert(DATACELL_SAVE_ERR);
          }
        }
      }
      this.afterSubmit(B);
      this.dataXML=null;
      this.loadData();
      this.refresh();
    }
  }
  this.unlocked();
  return true;
};
Datacell.prototype.reset=function(){
};
Datacell.prototype.processSortIcon=function($){
};
Datacell.prototype.addcheckbox=function(){
};
Datacell.prototype.setReadonly=function($){
  this.readonly=$||true;
};
Datacell.prototype.getEntitiesXML=function($,B){
  var A=new StringBuffer();
  if(B.length>0){
    for(var _=0;_<B.length;_++){
      B[_].name=$;
      A.append(B[_].toString());
    }
  }
  return A.toString();
};
Datacell.prototype.getSubmitXpath=function(){
  var _=this.submitXpath;
  if(!_){
    if(this.xpath){
      var $=this.xpath.split("/");
      _=$[$.length-1];
    }else {
      if(this.linkField){
        _=this.linkField;
      }
    }
  }
  return _||"entity";
};
Datacell.prototype.submitAllByXML=function(){
  if(this.allEntitiesXMLDiv){
    this.allEntitiesXMLDiv.parentNode.removeChild(this.allEntitiesXMLDiv);
  }
  var _=$createElement("div",{style:{display:"none"}});
  this.allEntitiesXMLDiv=_;
  var A=this.getSubmitXpath(),$=$createElement("input",{type:"hidden",name:"insertEntities",value:this.getEntitiesXML(A,this.datasetExp.getAlltEntities())});
  _.appendChild($);
  this.container.appendChild(_);
};
Datacell.prototype.submitModifyByXML=function(A,C,B,E){
  if(!E){
    this.submitModifyBy3XML(A,C,B);
    return ;
  }
  if(this.modifyEntitiesXMLDiv){
    this.modifyEntitiesXMLDiv.parentNode.removeChild(this.modifyEntitiesXMLDiv);
  }
  var D=$createElement("div",{style:{display:"none"}});
  this.modifyEntitiesXMLDiv=D;
  var $=[];
  $[0]=this.getEntitiesXML(A||"insertEntities",this.datasetExp.getInsertEntities());
  $[1]=this.getEntitiesXML(C||"updateEntities",this.datasetExp.getModifiedEntities());
  $[2]=this.getEntitiesXML(B||"deleteEntities",this.datasetExp.getRemovedEntities());
  var _=$createElement("input",{type:"hidden",name:E||"modifyEntities",value:$.join("")});
  D.appendChild(_);
  this.container.appendChild(D);
};
Datacell.prototype.submitModifyBy3XML=function($,C,A){
  if(this.modifyEntitiesXMLDiv){
    this.modifyEntitiesXMLDiv.parentNode.removeChild(this.modifyEntitiesXMLDiv);
  }
  var D=$createElement("div",{style:{display:"none"}});
  this.modifyEntitiesXMLDiv=D;
  var _=$createElement("input",{type:"hidden",name:$||"insertEntities",value:this.getEntitiesXML($||"insertEntities",this.datasetExp.getInsertEntities())}),B=$createElement("input",{type:"text",name:C||"updateEntities",value:this.getEntitiesXML(C||"updateEntities",this.datasetExp.getModifiedEntities())}),E=$createElement("input",{type:"hidden",name:A||"deleteEntities",value:this.getEntitiesXML(A||"deleteEntities",this.datasetExp.getRemovedEntities())});
  D.appendChild(_);
  D.appendChild(B);
  D.appendChild(E);
  this.container.appendChild(D);
};
Datacell.prototype.submitAllByHidden=function(){
  if(!this.validateAll()){
    return false;
  }
  if(this.allEntitiesHiddenDiv){
    this.allEntitiesHiddenDiv.parentNode.removeChild(this.allEntitiesHiddenDiv);
  }
  var _=$createElement("div",{style:{display:"none"}});
  this.container.appendChild(_);
  this.allEntitiesHiddenDiv=_;
  var A=this.datasetExp.name,B=this.getSubmitXpath(),$=Dataset2Array(this.datasetExp,B,true);
  this.addHiddenByArray($,_);
};
Datacell.prototype.submitModifyByHidden=function(C,A,$){
  if(!this.validateAll()){
    return false;
  }
  if(this.modifyEntitiesHiddenDiv){
    this.modifyEntitiesHiddenDiv.parentNode.removeChild(this.modifyEntitiesHiddenDiv);
  }
  var B=$createElement("div",{style:{display:"none"}});
  this.container.appendChild(B);
  this.modifyEntitiesHiddenDiv=B;
  var _=Dataset2Array(this.datasetExp.getInsertDataset(),C||"insertEntities");
  this.addHiddenByArray(_,B);
  _=Dataset2Array(this.datasetExp.getModifiedDataset(),A||"updateEntities");
  this.addHiddenByArray(_,B);
  _=Dataset2Array(this.datasetExp.getRemovedDataset(),$||"deleteEntities");
  this.addHiddenByArray(_,B);
};
Datacell.prototype.addHiddenByArray=function(C,A){
  for(var B=0;B<C.length;B++){
    if(C[B].value==null){
      continue ;
    }
    var _=C[B].key;
    _=_.replaceAll("/",".");
    var $=$createElement("input",{type:"hidden",name:_,value:C[B].value});
    A.appendChild($);
  }
};
Datacell.prototype.unSelectRow=function(){
  var $=this.activeRow;
  if(!$){
    return true;
  }else {
    this.endEdit();
  }
  if(this.onUnSelectRow($,this.getEntity($))){
    removeClass($,this.rowSelectStyleClass);
    this.activeRow=null;
    return true;
  }else {
    return false;
  }
};
Datacell.prototype.onUnSelectRow=function($,_){
  return true;
};
Datacell.prototype.setValue=function($){
  this.datasetExp=$;
  $info("datacell setvalue "+$);
  this.refresh();
};
Datacell.prototype.getValue=function(){
  return this.datasetExp;
};
Datacell.prototype.setFocus=function(){
};
Datacell.prototype.lostFocus=function(){
  this.endEdit();
};
function Pilot($){
  this.id=$;
  PageControl.add($,this);
  this.container=null;
  this.currPage=1;
  this.totalPages=null;
  this.currRecord=null;
  this.totalRecords=null;
  this.width="100%";
  this.height="100%";
  this.inputClass="INPUT";
  this.readonly=false;
  this.addButton=null;
  this.deleteButton=null;
  this.firstPageButton=null;
  this.nextPageButton=null;
  this.prePageButton=null;
  this.lastPageButton=null;
  this.gotoPageText=null;
  this.gotoPageButton=null;
  this.saveButton=null;
  this.reloadButton=null;
  this.begin=0;
  this.length=10;
  this.count=-1;
  this.isCount=false;
  this.totalPage=-1;
  this.currentPage=-1;
  this.isLast=false;
  this.isFirst=true;
  this.first_able=true;
  this.pre_able=true;
  this.next_able=true;
  this.last_able=true;
  this.add_able=true;
  this.delete_able=true;
}
Datacell.prototype.setCustomTool=function($,A){
  var A=A||this.__pagePilot[0].id;
  this.customToolHTML=$;
  var _=$id(A+"_custom_zone");
  if(_){
    _.innerHTML=this.customToolHTML;
    return true;
  }
  return false;
};
Datacell.prototype.hiddenButtons=function($){
};
Pilot.prototype.init=function(){
};
Pilot.prototype.build=function(){
  if(this.datacell.toolbarLocation=="top"){
    this.container=this.datacell.topToolbarDiv;
  }else {
    this.container=this.datacell.bottomToolbarDiv;
  }
  var E=this.datacell,T=E.toolsList.split(",");
  str=[];
  var D=(""+this.datacell.pageSizeList)!="none"&&(""+this.datacell.pageSizeList)!="null";
  for(var R=0;R<T.length;R++){
    var A=T[R].split(":"),F=A[1]||null;
    A=A[0];
    if(F){
      F=" "+F+" ";
    }
    switch(A){
    case "nav":
      str=str.concat(["<div class=\"eos-tool-zone\" id=\""+this.id+"_nav_zone\" >",(F&&F.indexOf(" first ")<0)?"":"<a title=\""+TOOLTIP_FIRST_PAGE+"\" id=\""+this.id+"_first\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-first\"></div></a>",(F&&F.indexOf(" prev ")<0)?"":"<a title=\""+TOOLTIP_PRIVOUS_PAGE+"\" id=\""+this.id+"_previous\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-prev\"></div></a>",(F&&F.indexOf(" next ")<0)?"":"<a title=\""+TOOLTIP_NEXT_PAGE+"\" id=\""+this.id+"_next\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-next\"></div></a>",(F&&F.indexOf(" last ")<0)?"":"<a title=\""+TOOLTIP_LAST_PAGE+"\" id=\""+this.id+"_last\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-last\"></div></a>",(F&&F.indexOf(" goto ")<0)?"":"<input type=\"text\" size=\"3\" maxlength=\"4\" class=\"eos-grid-tool-goto-input\" id=\""+this.id+"_input\" /><a title=\""+TOOLTIP_GOTO+"\" id=\""+this.id+"_goto\" href=\"javascript:void(0);\" style=\"margin-left:0px;\"><div class=\"eos-grid-tool-goto\"></div></a>","</div>"]);
      break ;
    case "pagesize":
      if(D){
        str=str.concat(["<div >","<div class=\"eos-grid-tool-pageinfo\" id=\""+this.id+"_pagesize\"  >","</div>","</div>"]);
      }
      break ;
    case "custom":
      str=str.concat(["<div class=\"eos-tool-zone\" id=\""+this.id+"_custom_zone\" >",E.customToolHTML||"","</div>"]);
      break ;
    case "edit":
      str=str.concat(["<div class=\"eos-tool-zone\">",(F&&F.indexOf(" add ")<0)?"":"<a title=\""+TOOLTIP_ADD+"\" id=\""+this.id+"_add\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-add\"></div></a>",(F&&F.indexOf(" del ")<0)?"":"<a title=\""+TOOLTIP_DELETE+"\" id=\""+this.id+"_del\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-del\"></div></a>",(F&&F.indexOf(" save ")<0)?"":"<a title=\""+TOOLTIP_SAVE+"\" id=\""+this.id+"_save\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-save\"></div></a>",(F&&F.indexOf(" reload ")<0)?"":"<a title=\""+TOOLTIP_RELOAD+"\" id=\""+this.id+"_reload\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-reload\"></div></a>","</div>"]);
      break ;
    case "info":
      str=str.concat(["<div class=\"eos-tool-zone\">","<div class=\"eos-grid-tool-pageinfo\" id=\""+this.id+"_pageinfo\"  >","</div>","</div>"]);
      break ;
    }
  }
  this.container.innerHTML=str.join("\n");
  this.firstPageButton=$id(this.id+"_first");
  this.nextPageButton=$id(this.id+"_next");
  this.prePageButton=$id(this.id+"_previous");
  this.lastPageButton=$id(this.id+"_last");
  this.gotoPageText=$id(this.id+"_input");
  this.gotoPageButton=$id(this.id+"_goto");
  var J=$id(this.id+"_pagesize");
  if(D&&J){
    var H={},I=this.datacell.pageSizeList.split(",");
    for(R=0;R<I.length;R++){
      H[I[R]]=I[R];
    }
    this.pageSizeSelect=createSelect(H,this.datacell.pageSize);
    this.pageSizeSelect.id=this.id+"_size";
    this.pageSizeSelect.className="eos-pagesize-select";
    J.appendChild(this.pageSizeSelect);
  }
  this.addButton=$id(this.id+"_add");
  this.deleteButton=$id(this.id+"_del");
  this.saveButton=$id(this.id+"_save");
  this.reloadButton=$id(this.id+"_reload");
  this.pageInfoDiv=$id(this.id+"_pageinfo");
  var G=this;
  if(D&&this.pageSizeSelect){
    function S(){
      G.datacell.endEdit();
      G.datacell.pageSize=Number(G.pageSizeSelect.value);
      G.gotoPage(1);
    }
    eventManager.add(this.pageSizeSelect,"change",S);
  }
  function N(){
    var $=eventManager.getKeyCode();
    if($==27){
      eventManager.stopEvent();
      G.gotoInputPage();
    }
  }
  eventManager.add(this.gotoPageText,"keypress",N);
  function _(){
    G.addRow();
  }
  eventManager.add(this.addButton,"click",_);
  function P(){
    E.endEdit();
    G.deleteRow();
  }
  eventManager.add(this.deleteButton,"click",P);
  function O(){
    E.endEdit();
    if(G.firstPageButton._disabled){
      return ;
    }
    G.gotoFirstPage();
  }
  eventManager.add(this.firstPageButton,"click",O);
  function Q(){
    E.endEdit();
    if(G.nextPageButton._disabled){
      return ;
    }
    G.gotoNextPage();
  }
  eventManager.add(this.nextPageButton,"click",Q);
  function L(){
    E.endEdit();
    if(G.prePageButton._disabled){
      return ;
    }
    G.gotoPrePage();
  }
  eventManager.add(this.prePageButton,"click",L);
  function $(){
    E.endEdit();
    if(G.lastPageButton._disabled){
      return ;
    }
    G.gotoLastPage();
  }
  eventManager.add(this.lastPageButton,"click",$);
  function M(){
    E.endEdit();
    if(G.gotoPageButton._disabled){
      return ;
    }
    G.gotoInputPage();
  }
  eventManager.add(this.gotoPageButton,"click",M);
  function B(){
    eventManager.stopEvent();
    E.endEdit();
    if(G.saveButton._disabled){
      return ;
    }
    G.datacell.save();
  }
  eventManager.add(this.saveButton,"click",B);
  function C(){
    E.endEdit();
    if(G.reloadButton._disabled){
      return ;
    }
    var $=true;
    if(G.datacell.isModefied&&confirm(DATACELL_MODIFY_CONFIRM)){
      G.datacell.submit();
    }else {
      G.datacell.reload();
    }
  }
  eventManager.add(this.reloadButton,"click",C);
  function K(){
    G.clearup();
  }
  eventManager.add(window,"unload",K);
};
Pilot.prototype.freshPilot=function(G,_,L,J){
  var C=true,H=true,D=true,A=true,E=false;
  if(G){
    E=true;
    this.begin=parseInt(G.getProperty("begin"))||0;
    this.datacell.pageSize=Number(this.datacell.pageSize<1?parseInt(G.getProperty("length")):this.datacell.pageSize);
    this.count=parseInt(G.getProperty("count"));
    this.datacell.isCount=(G.getProperty("isCount")=="true");
    this.totalPage=parseInt(G.getProperty("totalPage"));
    this.currentPage=parseInt(G.getProperty("currentPage"))||1;
    this.isLast=(G.getProperty("isLast")=="true");
    this.isFirst=(G.getProperty("isFirst")=="true");
    this.currentPage=this.currentPage<1?1:this.currentPage;
    if(!this.datacell.isCount){
      this.currentPage=parseInt(this.begin/this.datacell.pageSize)+1;
    }
    var I=isNaN(this.currentPage/1)||this.currentPage<1?1:this.currentPage,K=isNaN(this.totalPage/1)||this.totalPage<1?1:this.totalPage,B=isNaN(this.count/1)||this.count<0?0:this.count,$=Number(isNaN(this.begin/1)||this.begin<0?0:this.begin)+1,F=$+this.datacell.pageSize-1;
    F=F>B?B:F;
    F=F<$?$:F;
    if(this.gotoPageText){
      this.gotoPageText.value=I;
    }
    if(this.pageInfoDiv){
      this.pageInfoDiv.innerHTML="<nobr>";
      if(!this.datacell.isCount||B<0){
        this.pageInfoDiv.innerHTML+="<nobr>"+this.datacell.PAGE_RECORDINFO_NOCOUNT.replace("{0}",$).replace("{1}",F)+"</nobr>";
      }else {
        this.pageInfoDiv.innerHTML+=this.datacell.PAGE_RECORDINFO.replace("{0}",$).replace("{1}",F).replace("{2}",B);
        this.pageInfoDiv.innerHTML+="&#160;&#160;<nobr>"+this.datacell.PAGE_INFO.replace("{0}",K)+"</nobr>";
      }
      this.pageInfoDiv.innerHTML+="</nobr>";
    }
  }
  if(this.firstPageButton){
    this.firstPageButton._disabled=this.isFirst||!E;
  }
  if(this.prePageButton){
    this.prePageButton._disabled=this.isFirst||!E;
  }
  if(this.nextPageButton){
    this.nextPageButton._disabled=this.isLast||!E;
  }
  if(this.lastPageButton){
    this.lastPageButton._disabled=this.isLast||this.count<0||!E;
  }
  if(this.isFirst||!E){
    setOpacity(this.firstPageButton,0.5);
    setOpacity(this.prePageButton,0.5);
  }else {
    setOpacity(this.firstPageButton,1);
    setOpacity(this.prePageButton,1);
  }
  if(this.isLast||!E){
    setOpacity(this.nextPageButton,0.5);
    setOpacity(this.lastPageButton,0.5);
  }else {
    setOpacity(this.nextPageButton,1);
    setOpacity(this.lastPageButton,1);
  }
  if(this.count<0||!E){
    setOpacity(this.lastPageButton,0.5);
  }
  if(!E||!G.getProperty("begin")){
    if(this.gotoPageButton){
      this.gotoPageButton._disabled=true;
      this.gotoPageText.disabled=true;
      setOpacity(this.gotoPageButton,0.5);
    }
  }
};
Pilot.prototype.clearup=function(){
  for(var $ in this){
    try{
      this[$]=null;
    }
    catch(_){
      $handle(_);
    }
  }
};
Pilot.prototype.gotoPage=function(_){
  if(this.datacell.isModefied){
    if(!this.datacell.validateAll()){
      return false;
    }
    if(!this.datacell.validatePage()){
      return false;
    }
  }
  _=Number(_);
  if(isNaN(_)){
    _=1;
  }
  _=parseInt(_,10);
  this.currentPage=_;
  if(_<1){
    _=1;
  }
  var $=(_-1)*Number(this.datacell.pageSize);
  if($<0){
    $=0;
  }
  var A=this.count;
  if(!this.datacell.isCount){
    A=-1;
  }
  var B=this.datacell.setPageParam($,this.datacell.pageSize,A,this.datacell.isCount);
  this.datacell.gotoPage(B);
};
Pilot.prototype.gotoInputPage=function(){
  var $=Number(this.gotoPageText.value);
  if(isNaN($)){
    alert(DATACELL_PAGENUM_ERR);
  }else {
    this.gotoPage($);
  }
};
Pilot.prototype.gotoFirstPage=function(){
  if(this.first_able){
    this.gotoPage(1);
  }
};
Pilot.prototype.gotoNextPage=function(){
  if(this.next_able){
    this.gotoPage(this.getCurrPage()+1);
  }
};
Pilot.prototype.gotoPrePage=function(){
  if(this.pre_able){
    this.gotoPage(this.getCurrPage()-1);
  }
};
Pilot.prototype.gotoLastPage=function(){
  if(this.last_able){
    this.gotoPage(this.getPageCount());
  }
};
Pilot.prototype.addRow=function(){
  this.datacell.addRow();
};
Pilot.prototype.deleteRow=function(){
  this.datacell.deleteRow();
};
Pilot.prototype.reloadData=function(){
  this.datacell.reload();
};
Pilot.prototype.submit=function(){
  this.datacell.submit();
};
Pilot.prototype.setButton=function(A,B,_,D,C,$,E){
  if(A){
    this.first_able=true;
    this.firstPageButton.src=this.iconPath+PICTURE_FIRST_BUTTON;
  }else {
    this.first_able=false;
    this.firstPageButton.src=this.iconPath+PICTURE_FIRST_DISABLE;
  }
  if(B){
    this.pre_able=true;
    this.prePageButton.src=this.iconPath+PICTURE_PRIVOUS_BUTTON;
  }else {
    this.pre_able=false;
    this.prePageButton.src=this.iconPath+PICTURE_PRIVOUS_DISABLE;
  }
  if(_){
    this.next_able=true;
    this.nextPageButton.src=this.iconPath+PICTURE_NEXT_BUTTON;
  }else {
    this.next_able=false;
    this.nextPageButton.src=this.iconPath+PICTURE_NEXT_DISABLE;
  }
  if(D){
    this.last_able=true;
    this.lastPageButton.src=this.iconPath+PICTURE_LAST_BUTTON;
  }else {
    this.last_able=false;
    this.lastPageButton.src=this.iconPath+PICTURE_LAST_DISABLE;
  }
  if($||C){
    this.addButton.src=this.iconPath+PICTURE_ADD_DISABLE;
  }else {
    this.addButton.src=this.iconPath+PICTURE_ADD_BUTTON;
  }
  if(!E||C){
    this.deleteButton.src=this.iconPath+PICTURE_DELETE_DISABLE;
  }else {
    this.deleteButton.src=this.iconPath+PICTURE_DELETE_BUTTON;
  }
};
Pilot.prototype.getPageCount=function(){
  return this.totalPage;
};
Pilot.prototype.getCurrPage=function(){
  return this.currentPage;
};
Datacell.prototype.rowMoveUp=function(C){
  C=C||this.activeRow;
  if(!C){
    return ;
  }
  var A=C.parentNode,D=C.rowIndex,$=C.getAttribute("__entity_rowno");
  if(D<1){
    return ;
  }
  var _=A.rows[D-1],B=_.getAttribute("__entity_rowno");
  A.insertBefore(C,_);
};
Datacell.prototype.rowMoveDown=function(C){
  C=C||this.activeRow;
  if(!C){
    return ;
  }
  var A=C.parentNode,D=C.rowIndex,$=C.getAttribute("__entity_rowno");
  if(D>=A.rows.length-1){
    return ;
  }
  var _=A.rows[D+1],B=_.getAttribute("__entity_rowno");
  A.insertBefore(_,C);
};
Datacell.prototype.getActiveRow=function(){
  return this.activeRow;
};
Datacell.prototype.getActiveCell=function(){
  return this.activeCell;
};
Datacell.prototype.getActiveEntity=function(){
  return this.getEntity(this.getActiveRow());
};
Datacell.prototype.showEditor=function(){
  PageControl.addtoStack(this);
  this.datacellContainer.style.display="";
  this.datacellContainer.onmousedown=function(){
    eventManager.stopEvent();
  };
};
Datacell.prototype.hideEditor=function(){
  if(this.activeEditor){
    this.activeEditor.hideEditor();
  }
  if(this.datacellContainer){
    this.datacellContainer.style.display="none";
    this.datacellContainer.onmousedown=null;
  }
};
Datacell.prototype.hide=function(){
  this.hideEditor();
};
Datacell.prototype.setPosition=function(D,B,C,$){
  this.datacellContainer.style.position="absolute";
  var _=getMaxZindex(),A=getMaxZindex(document);
  if(this.datacellContainer.style.zIndex!=A){
    this.datacellContainer.style.zIndex=A;
  }
  setElementXY(this.datacellContainer,[D,B]);
};
Datacell.prototype.getDisplayValue=function($){
  return $;
};
Datacell.prototype.isFocus=function(){
  return false;
};
Datacell.prototype.validate=function(){
  return true;
};
Datacell.prototype.onSelectRow=function($,_){
};
Datacell.prototype.onDeleteRow=function($,_){
  return true;
};
Datacell.prototype.onSubmit=function(){
  return true;
};
Datacell.prototype.onAddRow=function($){
  return true;
};
Datacell.prototype.onUpdateCell=function(){
};
Datacell.prototype.onLoadData=function(){
  return true;
};
Datacell.prototype.onUpdateRow=function($){
  return true;
};
Datacell.prototype.onUpdate=function(){
  return true;
};
Datacell.prototype.beforeInit=function(){
};
Datacell.prototype.afterInit=function(){
};
Datacell.prototype.beforeLoadData=function(){
};
Datacell.prototype.afterLoadData=function(){
};
Datacell.prototype.beforeRefresh=function(){
};
Datacell.prototype.afterRefresh=function(){
};
Datacell.prototype.beforeSubmit=function(){
};
Datacell.prototype.afterSubmit=function(){
};
Datacell.prototype.beforeSubmit=function(){
};
Datacell.prototype.beforeSave=function(){
};
Datacell.prototype.afterSave=function(){
};
Datacell.prototype.beforeRefreshCell=function(){
};
Datacell.prototype.beforeAdd=function(){
};
Datacell.prototype.afterAdd=function(){
};
Datacell.prototype.beforeEdit=function(_,A,$){
};
Datacell.prototype.afterEdit=function($,A,_){
};
Datacell.prototype.beforeDel=function(){
};
Datacell.prototype.afterDel=function(){
};
Datacell.prototype.afterDelMess=function(){
};
Datacell.prototype.beforeSelectRow=function(){
};
Datacell.prototype.afterSelectRow=function(){
};
Datacell.prototype.beforeUnselectRow=function(){
};
Datacell.prototype.afterUnselectRow=function(){
};
Datacell.prototype.onComplete=function(){
};
Datacell.prototype.onPageChange=function(){
};
Datacell.prototype.onClickHead=function(A,B,$,_){
};
Datacell.prototype.onClickCell=function(A,C,$,D,_,B){
};
Datacell.prototype.onClickRow=function($,B,A,_){
};
Datacell.activeGrid=null;
Datacell.STATUS_BLANK=0;
Datacell.STATUS_INITED=1;
Datacell.STATUS_MODEFIED=2;
Datacell.BLOLK_ID="__DATACELL_BLOCK";
Datacell.__INDEX_NAME="__INDEX";
Datacell.outClick=function(C,J,B){
  var D=C.datacell,A=D.editorContainer;
  if(A&&J){
    var F=B.x,I=B.y,$=getPosition(A),_=$.top,G=$.left,E=G+A.offsetWidth,H=_+A.offsetHeight;
    if(!A.contains(J)){
      if(_>I||G>F||E<F||H<I){
        D.endEdit();
      }
    }
  }else {
    D.endEdit();
  }
};
Datacell.isCell=function($){
  if($&&$.tagName){
    var _=$.tagName.toLowerCase()=="td",A=$.getAttribute("fieldId");
    if(A&&_){
      return true;
    }
    return false;
  }
  return false;
};
Datacell.isHead=function($){
  if($.tagName){
    var _=$.tagName.toLowerCase()=="th",A=$.getAttribute("__type")=="head";
    if(A&&_){
      return true;
    }
    return false;
  }
  return false;
};
Datacell.getDatacellByCell=function(B){
  try{
    var _=getParentByTagName(B,"table"),A=_.datacell;
    return A;
  }
  catch($){
    $handle($);
    return null;
  }
};
Datacell.checkCell=function(E,B,F,$,C,_,H){
  var D="",A=H;
  if(E==_){
    D="checked";
    A=_;
  }
  A=A||A===0?A:"";
  var G="fieldFunc_"+C.id+"_"+$;
  Datacell[G]=function(A){
    var D=C.fields[$].fieldName;
    if(A.checked){
      B.setProperty(D,_);
    }else {
      B.setProperty(D,H);
    }
  };
  return "<input type=\"checkbox\" "+D+" value=\""+(""+A).replace(/\"/igm,"\\\"").replace(/\n/igm,"\\n")+"\" "+" onclick=\"Datacell['"+G+"'](this)\"  />";
};
Datacell.checkCellHandler=function($){
};
Datacell.getFormatValue=function($,_){
  if(isDateFormat(_)){
    if(isTimeFormat(_)){
      return dateFormat($,DATETIME_FORMAT,_);
    }else {
      return dateFormat($,DATE_FORMAT,_);
    }
  }else {
    if(isTimeFormat(_)){
      return timeFormat($,TIME_FORMAT,_);
    }else {
      return formatNumber($,_);
    }
  }
};
Datacell.$editor=function($){
  if(!$||($&&$.getValue)){
    return $;
  }
  return SimpleEditor($);
};
Datacell.getSortValue=function(C,B,_){
  var $=C.datasetExp.get(B.getAttribute("__entity_rowno")/1).getProperty(_),A=$?Number($):$;
  $=isNaN(A)?$:A;
  return $;
};
Datacell.getSortDefaultValue=function(_,$,A){
  return $.getAttribute("__entity_rowno")/1;
};
Datacell.initGlobalEvent=function(){
  eventManager.add(document.body,"keyup",function($){
    if(Datacell.activeGrid&&Datacell.activeGrid.getActiveCell()){
      Datacell.activeGrid&&Datacell.activeGrid._onKeyDown($);
    }
  });
  eventManager.add(document.body,"click",function($){
    if(Datacell.activeGrid&&Datacell.activeGrid.headDiv.clientHeight>10){
    }
  });
  eventManager.add(document.body,"mousemove",function($){
    Datacell.activeGrid&&Datacell.doDocGridHandler($,Datacell.activeGrid);
  });
  eventManager.add(document.body,"mouseup",function($){
    Datacell.activeGrid&&Datacell.endDocGridHandler($,Datacell.activeGrid);
  });
};
Datacell.doDocGridHandler=function(B,_){
  B=B||window.event;
  var A=eventManager.getPointX();
  if(_.splitLine.style.display=="block"){
    var $=A-_.viewportXY[0];
    _.splitLine.style.left=$+"px";
  }
};
Datacell.endDocGridHandler=function(_,C){
  _=_||window.event;
  var E=eventManager.getPointX();
  if(C.splitLine.style.display=="block"){
    var A=C.resizeColumn;
    A.newRightX=E-C.viewportXY[0];
    var B=A.newRightX-A.oldRightX,D=parseInt(CSSUtil.getRule("."+A.styleClass,true).style.width),$=B+D;
    C.setColumnWidth(A,$);
    C.resizeColumn=false;
    C.splitLine.style.display="none";
    C.headDiv.style.cursor="auto";
    C.gridMask.style.cursor="auto";
    C.gridMask.style.display="none";
    C.syncScroll();
  }
};
Datacell.onWindowResize=function($){
  if(_eos_curr_open_panel!=null){
    if(!$.isInCurrPanel()){
      return ;
    }
  }
  $.bodyDivHeight=$.containerTD.offsetHeight-$.headHeight;
  var _=false;
  if($.width.indexOf("%")!=-1){
    $.bodyDiv.style.width="1px";
    _=true;
  }
  if($.height.indexOf("%")!=-1){
    $.container.style.height="100%";
    $.bodyDiv.style.height="1px";
    $.freezeBodyDiv.style.height=$.bodyDiv.clientHeight+"px";
    _=true;
  }else {
    if($.bodyDivHeight>0){
      $.bodyDiv.style.height=$.bodyDivHeight+"px";
    }
    if($.bodyDiv.clientHeight>0){
      $.freezeBodyDiv.style.height=$.bodyDiv.clientHeight+"px";
    }
  }
  if(_){
    setTimeout(function(){
      return Datacell.autoResize.apply($,[$]);
    },1);
  }
  $.syncScroll();
};
Datacell.autoResize=function(){
  var $=this;
  if($.height.indexOf("%")!=-1){
    $.bodyDivHeight=$.containerTD.offsetHeight-$.headHeight;
    if($.bodyDivHeight>0){
      $.bodyDiv.style.height=$.bodyDivHeight+"px";
    }
    if($.bodyDiv.clientHeight>0){
      $.freezeBodyDiv.style.height=$.bodyDiv.clientHeight+"px";
    }
  }
  if($.width.indexOf("%")!=-1){
    $.bodyDiv.style.width=$.containerTD.offsetWidth;
  }
};
Datacell.prototype.autoResizeS1=function(){
  var $=this;
  $.bodyDivHeight=$.containerTD.offsetHeight-$.headHeight;
  var _=false;
  if($.width.indexOf("%")!=-1){
    $.bodyDiv.style.width="1px";
    _=true;
  }
  if($.height.indexOf("%")!=-1){
    $.container.style.height="100%";
    $.datacellContainer.style.height="100%";
    $.bodyDiv.style.height="1px";
    $.freezeBodyDiv.style.height=$.bodyDiv.clientHeight+"px";
    _=true;
  }else {
    if($.bodyDivHeight>0){
      $.bodyDiv.style.height=$.bodyDivHeight+"px";
    }
    if($.bodyDiv.clientHeight>0){
      $.freezeBodyDiv.style.height=$.bodyDiv.clientHeight+"px";
    }
  }
};
Datacell.prototype.autoResizeS2=Datacell.autoResize;
Datacell.prototype.isInCurrPanel=function(){
  if(_eos_curr_open_panel!=null){
    var $=this.datacellContainer.offsetParent;
    while(true){
      if($==null){
        return false;
      }
      if($==_eos_curr_open_panel.table){
        return true;
      }
      $=$.offsetParent;
    }
  }else {
    return false;
  }
};
Datacell.createSwidthCheckBox=function(E,F,B,A,C){
  var G=C.getField(A),$=G.checkedValue,_=G.unCheckedValue,D="";
  if($==E){
    D="checked";
  }
  return "<input type=\"checkbox\" "+D+" swidthCheckbox=\"true\"/>";
};
function VerifyCode($){
  this.config=$;
}
VerifyCode.prototype.init=function(){
  this.container=$id(this.config.name+"_container");
  if(this.config.hasInput==true){
    this.initInput();
  }
  this.initImg();
  PageControl.add(this.config.name,this);
};
VerifyCode.prototype.initInput=function(){
  if(this.config.style){
    this.text=$create("<input style="+this.config.style+">");
  }else {
    this.text=$create("input");
  }
  this.text.className=this.config.className||"";
  this.text.name=this.config.name;
  this.text.validateAttr=this.config.validateAttr||"";
  this.container.appendChild(this.text);
};
VerifyCode.prototype.initImg=function(){
  this.codeImage=$create("img");
  this.container.appendChild(this.codeImage);
  this.codeImage.style.verticalAlign="text-bottom";
  this.codeImage.style.marginLeft="6px";
  this.codeImage.title=VERIFYCODETITLE;
  this.imageUrl=contextPath+"/common/jsp/codeImage.jsp?name="+this.config.name+"&imageHeight="+this.config.imageHeight+"&length="+this.config.length+"&type="+this.config.type;
  this.codeImage.src=this.imageUrl;
  var $=this;
  eventManager.add(this.codeImage,"click",function(){
    return function(){
      this.codeImage.src=this.imageUrl+"&timestamp="+Math.random();
    }.call($);
  });
};