/*
 * Compressed by JSA(www.xidea.org)
 */
if (EOSKey != null) alert(EOSCANNOTINCLUDE);
var EOSKey = {
    BACKSPACE: 8,
    TAB: 9,
    ENTER: 13,
    SHIFT: 16,
    CTRL: 17,
    PAUSE: 19,
    CAPSLOCK: 20,
    ESC: 27,
    SPACE: 33,
    PAGEUP: 33,
    PAGEDOWN: 34,
    END: 35,
    HOME: 36,
    LEFT: 37,
    UP: 38,
    RIGHT: 39,
    DOWN: 40,
    INSERT: 45,
    DELETE: 46,
    WIN: 91,
    WIN_R: 92,
    MENU: 93,
    F1: 112,
    F2: 113,
    F3: 114,
    F4: 115,
    F5: 116,
    F6: 117,
    F7: 118,
    F8: 119,
    F9: 120,
    F10: 121,
    F11: 122,
    F12: 123,
    NUMLOCK: 144,
    SCROLLLOCK: 145
},
EOSKeyPress = {}; (function() {
    window.undefined = window.undefined;
    document.head = document.getElementsByTagName("head")[0];
    window.fAppVersion = parseFloat(navigator.appVersion);
    window.sUserAgent = navigator.userAgent.toLowerCase();
    var A = navigator.userAgent.toLowerCase();
    window.isIE9 = A.indexOf("msie 9.0") > -1;
    window.isIE10 = A.indexOf("msie 10.0") > -1;
    if (window.isIE9 || window.isIE10) {
        window.isIE = false;
    } else {
        window.isIE = A.indexOf("msie") > -1;
    }
    window.isIE7 = A.indexOf("msie 7") > -1;
    window.isFF = A.indexOf("firefox") > -1;
    window.isOpera = A.indexOf("opera") > -1;
    window.isWebkit = (/webkit|khtml/).test(A);
    window.isSafari = A.indexOf("safari") > -1 || window.isWebkit;
    window.isGecko = window.isMoz = !window.isSafari && A.indexOf("gecko") > -1;
    window.isStrict = document.compatMode == "CSS1Compat";
    window.isBorderBox = window.isIE && !window.isStrict;
    window.isSecure = window.location.href.toLowerCase().indexOf("https") === 0;
    window.isWindows = (A.indexOf("windows") != -1 || A.indexOf("win32") != -1);
    window.isMac = (A.indexOf("macintosh") != -1 || A.indexOf("mac os x") != -1);
    window.isLinux = (A.indexOf("linux") != -1);
    if (!Array.prototype.push) Array.prototype.push = function(A) {
        this[this.length] = A
    };
    if ((window.isGecko || window.isFF) && HTMLElement) HTMLElement.prototype.__defineGetter__("innerText",
    function() {
        return this.textContent
    });
    if (window.isMoz) {
        Document.prototype.readyState = 0;
        Document.prototype.onreadystatechange = null;
        Document.prototype.__changeReadyState__ = function(A) {
            this.readyState = A;
            if (typeof this.onreadystatechange == "function") this.onreadystatechange()
        };
        Document.prototype.__initError__ = function() {
            this.parseError.errorCode = 0;
            this.parseError.filepos = -1;
            this.parseError.line = -1;
            this.parseError.linepos = -1;
            this.parseError.reason = null;
            this.parseError.srcText = null;
            this.parseError.url = null
        };
        Document.prototype.__checkForErrors__ = function() {
            if (this.documentElement.tagName == "parsererror") {
                var A = />([\s\S]*?)Location:([\s\S]*?)Line Number (\d+), Column (\d+):<sourcetext>([\s\S]*?)(?:\-*\^)/;
                A.test(this.xml);
                this.parseError.errorCode = -999999;
                this.parseError.reason = RegExp.$1;
                this.parseError.url = RegExp.$2;
                this.parseError.line = parseInt(RegExp.$3);
                this.parseError.linepos = parseInt(RegExp.$4);
                this.parseError.srcText = RegExp.$5
            }
        };
        Document.prototype.loadXML = function(E) {
            this.__initError__();
            this.__changeReadyState__(1);
            var D = new DOMParser(),
            B = D.parseFromString(E, "text/xml");
            while (this.firstChild) this.removeChild(this.firstChild);
            for (var C = 0; C < B.childNodes.length; C++) {
                var A = this.importNode(B.childNodes[C], true);
                this.appendChild(A)
            }
            this.__checkForErrors__();
            this.__changeReadyState__(4)
        };
        Document.prototype.__load__ = Document.prototype.load;
        Document.prototype.load = function(A) {
            this.__initError__();
            this.__changeReadyState__(1);
            this.__load__(A)
        };
        Node.prototype.__defineGetter__("xml",
        function() {
            return (new XMLSerializer()).serializeToString(this, "text/xml")
        })
    }
    if (document.implementation.hasFeature("XPath", "3.0")) {
        XMLDocument.prototype.selectNodes = function(F, C) {
            if (!C) C = this;
            var E = this.createNSResolver(this.documentElement),
            D = this.evaluate(F, C, E, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null),
            B = [];
            for (var A = 0; A < D.snapshotLength; A++) B[A] = D.snapshotItem(A);
            return B
        };
        XMLDocument.prototype.selectSingleNode = function(C, B) {
            if (!B) B = this;
            var A = this.selectNodes(C, B);
            if (A.length > 0) return A[0];
            else return null
        };
        Element.prototype.selectNodes = function(A) {
            if (this.ownerDocument.selectNodes) return this.ownerDocument.selectNodes(A, this);
            else $warn("dom\u934f\u51aa\u790c\u6d93\u5d86\u656e\u93b8\u4e7belectNodes\u93c2\u89c4\u7876.")
        };
        Element.prototype.selectSingleNode = function(A) {
            if (this.ownerDocument.selectSingleNode) return this.ownerDocument.selectSingleNode(A, this);
            else $warn("dom\u934f\u51aa\u790c\u6d93\u5d86\u656e\u93b8\u4e7belectSingleNode\u93c2\u89c4\u7876.")
        }
    }
})();
function createXmlDom(E) {
    if (window.ActiveXObject) {
        var B = ["MSXML2.DOMDocument.5.0", "MSXML2.DOMDocument.4.0", "MSXML2.DOMDocument.3.0", "MSXML2.DOMDocument", "Microsoft.XmlDom"];
        for (var D = 0; D < B.length; D++) {
            try {
                var A = new ActiveXObject(B[D]);
                if (E) A.loadXML(E);
                return A
            } catch(C) {}
        }
        throw new Error("MSXML is not installed on your system.")
    } else if (document.implementation && document.implementation.createDocument) {
        A = document.implementation.createDocument("", "", null);
        A.parseError = {
            valueOf: function() {
                return this.errorCode
            },
            toString: function() {
                return this.errorCode.toString()
            }
        };
        A.__initError__();
        A.addEventListener("load",
        function() {
            this.__checkForErrors__();
            this.__changeReadyState__(4)
        },
        false);
        if (E) A.loadXML(E);
        return A
    } else throw new Error("Your browser doesn't support an XML DOM object.")
}
function $e(C, A) {
    A = A || document;
    if (typeof(C) == "object") return C;
    var B = $name(C) || $id(C);
    return B
}
function $id(F, B) {
    B = B || document;
    if (typeof(F) == "object") return F;
    var C = B.getElementById(F),
    E;
    if ((isIE || isIE9 || isIE10) && C) {
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
function $ids(D) {
    if (D.indexOf("[*]") > -1) {
        var C = [];
        for (var A = 1; A < Number.MAX_VALUE; A++) {
            var E = D.replace("[*]", "[" + A + "]"),
            B = $id(E);
            if (B) C.push(B);
            else break
        }
        return C
    } else {
        C = [],
        B = $id(D);
        if (B) C.push(B);
        return C
    }
}
function $names(E) {
    if (typeof(E) == "object") return E;
    if (E.indexOf("[*]") > -1) {
        var D = [];
        for (var A = 1; A < Number.MAX_VALUE; A++) {
            var F = E.replace("[*]", "[" + A + "]"),
            B = $name(F);
            if (B) D.push(B);
            else break
        }
        return D
    } else {
        var C = document.getElementsByName(E);
        if ((isIE || isIE9 || isIE10)) {
            D = [];
            for (A = 0; A < C.length; A++) if (C[A].name == E) D.push(C[A]);
            return D
        } else return C
    }
}
function $name(C) {
    if (typeof(C) == "object") return C;
    var B = document.getElementsByName(C);
    if (!B) return null;
    if ((isIE || isIE9 || isIE10)) {
        for (var A = 0; A < B.length; A++) if (B[A].name == C) return B[A]
    } else if (B.length > 0) return B[0];
    return null
}
function $indexOf(D, A) {
    for (var B = 0,
    C = D.length; B < C; B++) if (D[B] == A) return B;
    return - 1
}
function $contains(B, A) {
    return $indexOf(B, A) >= 0
}
function $remove(C, A) {
    var B = $indexOf(C, A);
    if (B >= 0) return C.splice(B, 1)
}
function isArray(A) {
    return A && typeof(A.sort) === "function" && typeof(A.join) === "function"
}
var setInnerHTML = function(el, htmlCode, doc) {
    el.innerHTML = "";
    doc = doc || document;
    var back_write = doc.write;
    doc.write = function() {};
    var cacheScripts = doc.getElementsByTagName("script"),
    cacheSRC = {};
    for (var i = 0; i < cacheScripts.length; i++) if (cacheScripts[i].src) cacheSRC[cacheScripts[i].src] = true;
    else cacheSRC[cacheScripts[i].src] = false;
    if ((isIE || isIE9 || isIE10)) {
        htmlCode = "<div style=\"display:none\">for IE</div>" + htmlCode;
        var div = $create("div", doc);
        div.innerHTML = htmlCode;
        var scripts = div.getElementsByTagName("script"),
        execScripts = [],
        srcArray = [];
        for (i = scripts.length - 1; i > -1; i--) {
            var script = scripts[i];
            if (script.src) {
                if (!cacheSRC[script.src]) {
                    srcArray.push(script.src);
                    cacheSRC[script.src] = true
                }
                script.parentNode.removeChild(script)
            } else execScripts.push(script)
        }
        function setHTML() {
            el.innerHTML = div.innerHTML;
            el.removeChild(el.firstChild);
            for (var i = execScripts.length - 1; i >= 0; i--) eval(execScripts[i].innerHTML)
        }
        var index = srcArray.length - 1;
        loadNext();
        function loadNext() {
            if (srcArray[index]) {
                var A = $create("script", doc);
                A.src = srcArray[index];
                var B = doc.body.firstChild;
                if (B) doc.body.insertBefore(A, B);
                else doc.body.appendChild(A);
                index--;
                A.onreadystatechange = function() {
                    if (A.readyState == "loaded" || A.readyState == "complete") {
                        A.onreadystatechange = null;
                        loadNext()
                    }
                }
            } else setHTML()
        }
    } else {
        var el_next = el.nextSibling,
        el_parent = el.parentNode;
        el_parent.removeChild(el);
        el.innerHTML = htmlCode;
        if (el_next) el_parent.insertBefore(el, el_next);
        else el_parent.appendChild(el)
    }
};
function setMaskSize(C) {
    var A = document.getElementsByTagName("BODY")[0],
    D = getViewportHeight(),
    B = getViewportWidth();
    if (D > A.scrollHeight) popHeight = D;
    else popHeight = A.scrollHeight;
    if (B > A.scrollWidth) popWidth = B;
    else popWidth = A.scrollWidth;
    C.style.height = popHeight + "px";
    C.style.width = popWidth + "px"
}
function getViewportHeight() {
    if (window.innerHeight != window.undefined) return window.innerHeight;
    if (document.compatMode == "CSS1Compat") return document.documentElement.clientHeight;
    if (document.body) return document.body.clientHeight;
    return window.undefined
}
function getViewportWidth() {
    if (window.innerWidth != window.undefined) return window.innerWidth;
    if (document.compatMode == "CSS1Compat") return document.documentElement.clientWidth;
    if (document.body) return document.body.clientWidth;
    return window.undefined
}
function toLength(A) {
    if (isNaN(Number(A))) return A;
    else return A + "px"
}
var __page__components;
if (typeof(__page__components) == "undefined") __page__components = {};
function PageControl() {}
PageControl.add = function(E, B) {
    if (!__page__components[E]) __page__components[E] = B;
    else if (__page__components[E + "__is__array__"]) __page__components[E].push(B);
    else {
        var D = [];
        D.push(__page__components[E]);
        D.push(B);
        __page__components[E] = D;
        __page__components[E + "__is__array__"] = true
    }
    var C = __page__components_rel;
    if (B.registerSubComponent && C[E]) for (var A = 0; A < C[E].length; A++) if (C[E][A]) {
        B.registerSubComponent(C[E][A]);
        C[E][A] = null
    }
};
var __page__components_rel;
if (typeof(__page__components_rel) == "undefined") __page__components_rel = {};
PageControl.registerRelation = function(D, E) {
    if (!D || !E) return;
    var C = PageControl.getOne(D),
    A = PageControl.getOne(E);
    if (C && C.registerSubComponent) C.registerSubComponent(E);
    else if ((D + "").indexOf("xml:") != 0) {
        var B = __page__components_rel;
        B[D] = B[D] || [];
        B[D].push(E)
    }
};
top.currStack = top.currStack || [];
PageControl.setFocus = function(B, E) {
    var D;
    if (B) D = B.id;
    var A = top.currStack.length - 1;
    for (; A > -1; A--) {
        var C = top.currStack[A];
        if (C) if (C == B) break;
        else if (C == E) break;
        else {
            if (C.hide) {
                try {
                    C.hide()
                } catch(F) {}
            }
            if (A >= 0) {
                try {
                    top.currStack.splice(A, 1)
                } catch(F) {}
            }
        }
    }
    if (B) top.currStack.push(B)
};
PageControl.getCurrComp = function() {
    if (top.currStack.length > 0) return top.currStack[top.currStack.length - 1];
    else return null
};
PageControl.addtoStack = function(A) {
    if (!$contains(top.currStack, A)) top.currStack.push(A)
};
PageControl.get = function(A) {
    return __page__components[A]
};
PageControl.getOne = function(A) {
    if (__page__components[A + "__is__array__"]) return __page__components[A][0] || null;
    else return __page__components[A] || null
};
$o = PageControl.getOne;
var EOS_FunctionCache = {};
function $function(D, C, B) {
    var A = $getFunction(D) || window[D];
    if (typeof(A) == "function") return A.apply(C || this, B)
}
function $setFunction(B, A) {
    EOS_FunctionCache[B] = A
}
function $getFunction(A) {
    return EOS_FunctionCache[A]
}
function $removeFunction(A) {
    EOS_FunctionCache[A] = null;
    delete EOS_FunctionCache[A]
}
var EVENTMANAGER_CONTIANS_ERROR = "Element \u9428\ufffd tontains\u7481\u5267\u7586\u6fb6\u8fab\u89e6!";
if (!Array.prototype.push) Array.prototype.push = function(A) {
    this[this.length] = A
};
if (navigator.product == "Gecko") {
    Document.prototype.elementFromPoint = function(E, D) {
        this.addEventListener("mousemove", this.elementFromPoint__handler, false);
        var C = this.createEvent("MouseEvents"),
        B = this.getBoxObjectFor(this.documentElement),
        A = {
            x: B.screenX,
            y: B.screenY
        };
        C.initMouseEvent("mousemove", true, false, this.defaultView, 0, E + A.x, D + A.y, E, D, false, false, false, false, 0, null);
        this.dispatchEvent(C);
        this.removeEventListener("mousemove", this.elementFromPoint__handler, false);
        return this.elementFromPoint__target
    };
    Document.prototype.elementFromPoint__handler = function(A) {
        this.elementFromPoint__target = A.explicitOriginalTarget;
        if (this.elementFromPoint__target.nodeType == Node.TEXT_NODE) this.elementFromPoint__target = this.elementFromPoint__target.parentNode;
        if (this.elementFromPoint__target.nodeName.toUpperCase() == "HTML" && this.documentElement.nodeName.toUpperCase() == "HTML") this.elementFromPoint__target = this.getElementsByTagName("BODY").item(0);
        A.preventDefault();
        A.stopPropagation()
    };
    Document.prototype.elementFromPoint__target = null
}
if (!document.all) {
    try {
        if (window.Node) Node.prototype.contains = function(D) {
            if (!D) return false;
            if (this == D) return true;
            var C = D.tagName,
            A = this.getElementsByTagName(C),
            E = A.length;
            for (var B = 0; B < E; B++) if (A[B] == D) return true;
            return false
        }
    } catch(e) {
        alert(EVENTMANAGER_CONTIANS_ERROR)
    }
}
if (typeof(_registryEvent) == "undefined") _registryEvent = [];
if (typeof(_registryOutClick) == "undefined") _registryOutClick = [];
function eventManager() {}
eventManager.onLoadFuncList = [];
eventManager.onLoadFunc = function() {
    var C = this;
    for (var A = 0; A < eventManager.onLoadFuncList.length; A++) {
        var B = eventManager.onLoadFuncList[A];
        B.apply(C, arguments)
    }
};
eventManager.onLoadFunc.hasAdd = false;
eventManager.onLoad = function(D, B, C) {
    eventManager.onLoadFuncList.push(B);
    if (!eventManager.onLoadFunc.hasAdd) {
        eventManager.onLoadFunc.hasAdd = true;
        var A = "load";
        B = eventManager.onLoadFunc;
        if (D.addEventListener) {
            D.addEventListener(A, B, C);
            _registryEvent.push({
                obj: D,
                type: A,
                fn: B,
                useCapture: C
            });
            return true
        } else if (D.attachEvent && D.attachEvent("on" + A, B)) {
            C = false;
            _registryEvent.push({
                obj: D,
                type: A,
                fn: B,
                useCapture: C
            });
            return true
        }
    }
    return false
};
eventManager.add = function(A, B, D, C) {
    if (typeof A == "string") A = document.getElementById(A);
    if (A == null || D == null) return false;
    if (B == "mousewheel" || B == "mousescroll") B = (window.isGecko) ? "DOMMouseScroll": "mousewheel";
    else if (B == "load") return eventManager.onLoad(A, D, C);
    if (A.addEventListener) {
        A.addEventListener(B, D, C);
        _registryEvent.push({
            obj: A,
            type: B,
            fn: D,
            useCapture: C
        });
        return true
    }
    if (A.attachEvent && A.attachEvent("on" + B, D)) {
        _registryEvent.push({
            obj: A,
            type: B,
            fn: D,
            useCapture: false
        });
        return true
    }
    return false
};
eventManager.addOutClick = function(A, C, B) {
    _registryOutClick.push({
        obj: A,
        fn: C,
        params: B
    })
};
eventManager.outClick = function() {
    PageControl.setFocus()
};
eventManager.remove = function(A, B, D, C) {
    if (A.removeEventListener) A.removeEventListener(B, D, C);
    else if (A.detachEvent) A.detachEvent("on" + B, D);
    if (A["on" + B]) A["on" + B] = null
};
eventManager.CleanUp = function() {
    if (_registryEvent) for (var A = 0; A < _registryEvent.length; A++) {
        eventManager.remove(_registryEvent[A].obj, _registryEvent[A].type, _registryEvent[A].fn);
        _registryEvent[A].obj = null;
        _registryEvent[A].fn = null
    }
    ReleaseEvents();
    _registryEvent = null;
    _registryOutClick = null;
    __page__components = null;
    window.onerror = null;
    eventManager.outClick = null;
    if (window["CollectGarbage"]) CollectGarbage()
};
eventManager.getElement = function(C) {
    C = C || window;
    var B;
    if (C.event) B = C.event.srcElement;
    else {
        var A = eventManager.getEvent(C);
        B = A.target
    }
    return B.nodeType == 3 ? B.parentNode: B
};
eventManager.stopPropagation = function(B) {
    B = B || window;
    var A = B.event ? B.event: eventManager.getEvent(B);
    if (!A) return;
    if (A.stopPropagation) {
        A.preventDefault();
        A.stopPropagation()
    } else {
        A.returnValue = false;
        A.cancelBubble = true
    }
};
eventManager.stopEvent = eventManager.stopPropagation;
eventManager.getPageScroll = function(E) {
    E = E || window;
    var A = E.document.documentElement,
    B = E.document.body,
    D = 0,
    C = 0;
    D = Math.max(A.scrollLeft, B.scrollLeft);
    C = Math.max(A.scrollTop, B.scrollTop);
    return [D, C]
};
eventManager.getPointX = function(C) {
    C = C || window;
    var A = eventManager.getEvent(C),
    B = A.pageX;
    if (!B && 0 !== B) {
        B = A.clientX || 0;
        if ((isIE || isIE9 || isIE10)) B += eventManager.getPageScroll(C)[0]
    }
    return B
};
eventManager.getPointY = function(C) {
    C = C || window;
    var A = eventManager.getEvent(C),
    B = A.pageY;
    if (!B && 0 !== B) {
        B = A.clientY || 0;
        if ((isIE || isIE9 || isIE10)) B += eventManager.getPageScroll(C)[1]
    }
    return B
};
eventManager.getWheel = function(A) {
    A = A || window.event || eventManager.getEvent();
    return (A.wheelDelta) ? A.wheelDelta / 120 : -(A.detail || 0) / 3
};
eventManager.getEvent = function(C) {
    C = C || window;
    if (C.event) return C.event;
    else {
        var A = eventManager.getEvent.caller;
        while (A != null) {
            var B = A.arguments[0];
            if (B) if (window["Event"] && B instanceof Event) return B;
            A = A.caller
        }
        return null
    }
};
eventManager.getKeyCode = function(B) {
    B = B || window;
    if (B.event) return B.event.keyCode;
    else {
        var A = eventManager.getEvent(B);
        if (!A) return null;
        return A.keyCode
    }
};
function ReleaseEvents() {
    var B = ["focus", "blur", "click", "mousedown", "mouseup", "mouseover", "keydown", "keypress", "keyup", "mouseout", "change"],
    A = function(C) {
        var A;
        for (A = 0; A < B.length; A++) if (C["on" + B[A]]) C["on" + B[A]] = null
    },
    D = document.all || document.getElementsByTagName("*");
    if (D) for (var C = 0; C < D.length; C++) A(D[C])
}
function ReleaseElemEvents(C) {
    var B = ["focus", "blur", "click", "mousedown", "mouseup", "mouseover", "keydown", "keypress", "keyup", "mouseout", "change"],
    A = function(C) {
        var A;
        for (A = 0; A < B.length; A++) if (C["on" + B[A]]) C["on" + B[A]] = null
    };
    A(C);
    var E = C.getElementsByTagName("*");
    if (E) for (var D = 0; D < E.length; D++) A(E[D])
}
eventManager.add(window, "unload", eventManager.CleanUp);
eventManager.add(document, "mousedown", eventManager.outClick);
eventManager.stopBubble = function(B) {
    B = B || window;
    var A = B.event ? B.event: eventManager.getEvent(B);
    if (!A) return;
    if (A.stopPropagation) A.stopPropagation();
    else A.cancelBubble = true
};
function StringBuffer() {
    this.values = new Array()
}
StringBuffer.prototype.append = function(A) {
    this.values.push(A);
    return this
};
StringBuffer.prototype.clear = function() {
    return this.values = []
};
StringBuffer.prototype.toString = function() {
    return this.values.join("")
};
function allTrim(A) {
    if (A) return (A + "").replace(/ /g, "");
    else {
        if (A == "") return A;
        return null
    }
}
function trim(C, A) {
    if (C !== "" && (!C.replace || !C.length)) return C;
    var B = (A > 0) ? (/^\s+/) : (A < 0) ? (/\s+$/) : (/^\s+|\s+$/g);
    return C.replace(B, "")
}
function lTrim(A) {
    return trim(A, 1)
}
function rTrim(A) {
    return trim(A, -1)
}
function substringAfter(C, B) {
    if (!C || !B) return null;
    else {
        var A = C.indexOf(B);
        if (A >= 0) {
            A = A + B.length;
            return C.substr(A)
        } else return ""
    }
}
function substringBefore(C, B) {
    if (!C || !B) return null;
    else {
        var A = C.indexOf(B);
        if (A >= 0) return C.substring(0, A);
        else return ""
    }
}
function getBytesLen(B) {
    if (B) {
        var A = /[^\x00-\xff]/g;
        return (B + "").replace(A, "aa").length
    } else {
        if (B == "") return 0;
        return - 1
    }
}
function get2BytesCharsLen(A) {
    if (A) return A.length;
    else {
        if (A == "") return 0;
        return - 1
    }
}
function xmlConversion(A) {
    if (A) return (A + "").replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\'/g, "&apos;").replace(/\"/g, "&quot;");
    else {
        if (A == "") return A;
        return null
    }
}
function htmlConversion(A) {
    if (A) return (A + "").replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(" ", "&nbsp;");
    else {
        if (A === "") return A;
        return null
    }
}
function dateFormat(C, D, A) {
    var B = stringToDate(C, D);
    if (isNaN(B)) alert(DATE_FORMAT_ERROR);
    else return dateToString(B, A);
    return null
}
function isDate(D, A) {
    if (D === null || D === undefined || D == "") return false;
    D = formatSupport(D, A);
    var C = A.replace("yyyy", "\\d{4}").replace(/MM/, "\\d{2}").replace("dd", "\\d{2}").replace("HH", "\\d{2}").replace(/mm/, "\\d{2}").replace("ss", "\\d{2}").replace(".S", ".\\d{1}");
    if (! (new RegExp(C)).test(D)) return false;
    var B = isFormatDate(D, A),
    E = isFormatTime(D, A);
    if (B || E) return true;
    return false
}
function isTimeFormat(A) {
    if (typeof(A) == "string") return A.indexOf("HH") != -1 && A.indexOf("mm") != -1 && A.indexOf("ss") != -1;
    else return false
}
function isDateFormat(A) {
    if (typeof(A) == "string") return A.indexOf("yyyy") != -1 && A.indexOf("MM") != -1 && A.indexOf("dd") != -1;
    else return false
}
function timeFormat(C, D, A) {
    var B = "00:00:00";
    if (isFormatTime(C, D)) {
        if (isTimeFormat(A)) B = setTime(C, D, A);
        return B
    } else alert(TIME_FORMAT_ERROR);
    return B
}
function isFormatTime(E, D) {
    if (!E || !D) return false;
    if (E.length != D.length) return false;
    var C = D.indexOf("HH");
    if (C == -1) return false;
    var B = E.substring(C, C + 2),
    A = D.indexOf("mm");
    if (A == -1) return false;
    var F = E.substring(A, A + 2),
    G = D.indexOf("ss");
    if (G == -1) return false;
    var H = E.substring(G, G + 2);
    if (!isNumber(B) || B < 0 || B > 23) return false;
    if (!isNumber(F) || F < 0 || F > 59) return false;
    if (!isNumber(H) || H < 0 || H > 59) return false;
    return true
}
function isFormatDate(H, E) {
    if (!H || !E) return false;
    if (H.length != E.length) return false;
    var G = E.indexOf("yyyy");
    if (G == -1) return false;
    var D = H.substring(G, G + 4),
    B = E.indexOf("MM");
    if (B == -1) return false;
    var A = H.substring(B, B + 2),
    F = E.indexOf("dd");
    if (F == -1) return false;
    var C = H.substring(F, F + 2);
    if (!isNumber(D) || D > 10000 || D < 999) return false;
    if (!isNumber(A) || A > 12 || A < 1) return false;
    if (C > getMaxDay(D, A) || C < 1) return false;
    return true
}
function formatSupport(B, A) {
    if (A == "yyyyMMddHHmmss") {
        if (B.length == 8) B = B + "000000"
    } else if (A == "yyyyMMdd") {
        if (B.length == 14) B = B.substring(0, 8)
    } else if (A == "yyyy-MM-dd HH:mm:ss") {
        if (B.length == 10) B = B + " 00:00:00"
    } else if (A == "yyyy-MM-dd") if (B.length == 20) B = B.substring(0, 10);
    return B
}
function stringToDate(X, C) {
    var I = new Date();
    if (!X || X == "") return I;
    if (!C || C == "") C = "yyyy-MM-dd";
    var E = C.replace(/[^y|Y]/g, ""),
    G = C.replace(/[^M]/g, ""),
    N = C.replace(/[^d]/g, ""),
    Q = C.indexOf(E),
    W = E.length,
    S = X.substring(Q, Q + W) * 1;
    if (W == 2) if (S < 50) S += 2000;
    else S += 1900;
    var R = C.indexOf(G),
    H = X.substring(R, R + G.length) * 1 - 1,
    K = C.indexOf(N),
    J = X.substring(K, K + N.length) * 1,
    T = C.replace(/[^H]/g, ""),
    L = C.replace(/[^h]/g, ""),
    U = C.replace(/[^m]/g, ""),
    F = C.replace(/[^S|s]/g, ""),
    P = 0;
    if (T && T != "") {
        var O = C.indexOf(T);
        if (O > -1) P = X.substring(O, O + T.length)
    }
    if (L && L != "") {
        var A = C.indexOf(L);
        if (A > -1) P = X.substring(A, A + L.length)
    }
    var V = 0;
    if (U && U != "") {
        var B = C.indexOf(U);
        if (B > -1) V = X.substring(B, B + U.length)
    }
    var M = 0;
    if (F && F != "") {
        var D = C.indexOf(F);
        if (D > -1) M = X.substring(D, D + F.length)
    }
    return new Date(S, H, J, P, V, M)
}
function dateToString(C, E) {
    if (!E || E == "") E = "yyyy-MM-dd";
    C = C || new Date();
    var I = C.getFullYear().toString(),
    A = (C.getMonth() + 1).toString(),
    B = C.getDate().toString(),
    N = C.getMinutes().toString(),
    F = C.getSeconds().toString(),
    H = C.getHours().toString(),
    M = E.replace(/[^y|Y]/g, "");
    if (I.length < 4) I = "0" + I;
    if (M.length == 2) I = I.substring(2, 4);
    var P = E.replace(/[^M]/g, "");
    if (P.length > 1) if (A.length == 1) A = "0" + A;
    var G = E.replace(/[^d]/g, "");
    if (G.length > 1) if (B.length == 1) B = "0" + B;
    var J = E.replace(/[^H]/g, "");
    if (J && J.length > 1) if (H.length == 1) H = "0" + H;
    var D = E.replace(/[^h]/g, "");
    if (D && D.length > 1) {
        if (H - 12 > 0) H = (H - 12) + "";
        if (H.length == 1) H = "0" + H
    }
    var K = E.replace(/[^m]/g, "");
    if (K && K.length > 1) if (N.length == 1) N = "0" + N;
    var L = E.replace(/[^S|s]/g, "");
    if (L && L.length > 1) if (F.length == 1) F = "0" + F;
    var O = E;
    if (M) O = O.replace(M, I);
    if (P) O = O.replace(P, A);
    if (G) O = O.replace(G, B);
    if (D) O = O.replace(D, H);
    if (J) O = O.replace(J, H);
    if (K) O = O.replace(K, N);
    if (L) O = O.replace(L, F);
    return O
}
function dateToStringValue(A) {
    var B = String(A);
    if (B.length == 1) B = "0" + B;
    return B
}
function getMaxDay(B, A) {
    if (A == 4 || A == 6 || A == 9 || A == 11) return "30";
    if (A == 2) if (B % 4 == 0 && B % 100 != 0 || B % 400 == 0) return "29";
    else return "28";
    return "31"
}
function isNumber(B) {
    var A = /^(\d+)$/;
    return A.test(B)
}
function setDate(I, J, C) {
    var H = J.indexOf("yyyy"),
    E = I.substring(H, H + 4),
    B = J.indexOf("MM"),
    A = I.substring(B, B + 2),
    G = J.indexOf("dd"),
    D = I.substring(G, G + 2),
    F = C;
    F = F.replace(/yyyy/i, E);
    F = F.replace(/MM/i, A);
    F = F.replace(/dd/i, D);
    return F
}
function setTime(H, I, F) {
    var C = "00",
    E = "00",
    B = "00",
    D = I.indexOf("HH"),
    A = I.indexOf("mm"),
    G = I.indexOf("ss");
    if (G != -1 && D != -1 && A != -1) {
        B = H.substring(G, G + 2);
        E = H.substring(A, A + 2);
        C = H.substring(D, D + 2);
        if (!isNumber(C) || C > "23" || C < "00") {
            alert(DATE_FORMAT_ERROR);
            return ""
        }
        if (!isNumber(E) || E > "59" || E < "00") {
            alert(DATE_FORMAT_ERROR);
            return ""
        }
        if (!isNumber(B) || B > "59" || B < "00") {
            alert(DATE_FORMAT_ERROR);
            return ""
        }
    } else if (! (G == -1 && D == -1 && A == -1)) {
        alert(DATE_FORMAT_ERROR);
        return ""
    }
    F = F.replace(/HH/i, C);
    F = F.replace(/mm/i, E);
    F = F.replace(/ss/i, B);
    return F
}
Number.prototype.NAN0 = function() {
    if (isNaN(this)) return 0;
    else return this
};
function numberToMoney(A, D) {
    A = A.toString();
    D = D ? D: NUMBER_MONEY_PREFIX;
    if (isNaN(A)) A = "0";
    var E = (A == (A = Math.abs(A)));
    A = Math.floor(A * 100 + 0.50000000001);
    var C = A % 100;
    A = Math.floor(A / 100).toString();
    if (C < 10) C = "0" + C;
    for (var B = 0; B < Math.floor((A.length - (1 + B)) / 3); B++) A = A.substring(0, String(A).length - (4 * B + 3)) + "," + A.substring(A.length - (4 * B + 3));
    return (((E) ? "": "-") + D + A + "." + C)
}
function numberToChinese(J) {
    var O = 99999999999.99,
    I, K, N = "",
    B, M, D, C, E, F, L, H, A, Q, G, P = J;
    P = P.toString();
    if (P == "") {
        alert(NUMBER_CHINESE_ERROR_NULL);
        return ""
    }
    if (P.indexOf("-") == 0) {
        P = P.substr(1);
        N = CN_MINUS
    }
    if (P.match(/[^,.\d]/) != null) {
        alert(NUMBER_CHINESE_ERROR_CHARATER);
        return ""
    }
    if ((P).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
        alert(NUMBER_CHINESE_ERROR_CHARATER);
        return ""
    }
    P = P.replace(/,/g, "");
    P = P.replace(/^0+/, "");
    if (Number(P) > O) {
        alert(NUMBER_CHINESE_ERROR_LARGE);
        return ""
    }
    B = P.split(".");
    if (B.length > 1) {
        I = B[0];
        K = B[1];
        K = K.substr(0, 2)
    } else {
        I = B[0];
        K = ""
    }
    M = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
    D = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
    C = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
    E = new Array(CN_TEN_CENT, CN_CENT);
    if (Number(I) > 0) {
        F = 0;
        for (L = 0; L < I.length; L++) {
            H = I.length - L - 1;
            A = I.substr(L, 1);
            Q = H / 4;
            G = H % 4;
            if (A == "0") F++;
            else {
                if (F > 0) N += M[0];
                F = 0;
                N += M[Number(A)] + D[G]
            }
            if (G == 0 && F < 4) N += C[Q]
        }
        N += CN_DOLLAR
    }
    if (K != "") for (L = 0; L < K.length; L++) {
        A = K.substr(L, 1);
        if (A != "0") N += M[Number(A)] + E[L]
    }
    if (N == "") N = CN_ZERO + CN_DOLLAR;
    if (K == "") N += CN_INTEGER;
    N = CN_SYMBOL + N;
    return N
}
function formatNumber(A, E, C) {
    var D = E.split(";"),
    B;
    if (D.length == 1) B = singleFormat(A, E, C);
    else if (D.length == 2) {
        if (A > 0) B = singleFormat(A, D[0], C);
        else B = singleFormat(A, D[1], C)
    } else if (D.length == 3) {
        if (A < 0) B = singleFormat(A, D[1], C);
        else if (A > 0) B = singleFormat(A, D[0], C);
        else B = singleFormat(A, D[2], C)
    } else alert("error format " + E);
    return B
}
function singleFormat(J, R, M) {
    J = Number(J);
    if (isNaN(J)) return J;
    var K, F, L, B, H = "",
    I = "",
    D = "",
    E = "",
    G, O, N, P, A = 0,
    C = 0,
    Q = "";
    R = (R) ? R + "": "";
    if (R.indexOf(",") >= 0) B = ",";
    if (R.indexOf("%") >= 0) {
        H = "%";
        J = J / 100
    }
    if (M) Q = M;
    s = R.split(".");
    G = ((s[0] == "" || s[0] == null || s[0] == "undefinded") ? "": s[0]);
    G = G.split("").reverse().join("");
    O = (s[1] == "" || s[1] == null || s[1] == "undefinded") ? "": s[1];
    J = toFixedFunc(J, O.length);
    if (J < 0) I = "-";
    J += "";
    if (I != "") J = J.replace("-", "");
    s = J.split(".");
    N = ((s[0] == "" || s[0] == null || s[0] == "undefinded") ? "": s[0]);
    N = N.split("").reverse().join("");
    P = (s[1] == "" || s[1] == null || s[1] == "undefinded") ? "": s[1];
    if (N) A = N.length;
    if (G.length > A) A = G.length;
    for (K = 0; K < A; K++) {
        F = N.charAt(K);
        L = G.charAt(K);
        C++;
        if (C == 4 && B && (F || L == "0")) E += B;
        if (L == "0" && !F) E += "0";
        else if (F) E += F;
        if (C == 4) C = 1
    }
    if (O) A = O.length;
    for (K = 0; K < A; K++) {
        F = P.charAt(K);
        L = O.charAt(K);
        if (L == "0" && !F) D += "0";
        else if ((L == "#" || L == "0") && F) D += F
    }
    L = ((E + "").split("").reverse().join("")) + ((D) ? "." + D: "");
    if (H == "%") L += H;
    else L = H + L;
    return Q + I + L
}
function toFixedFunc(A, B) {
    return A.toFixed(B)
}
function linkConfirm(B, C, A) {
    var D = $id("__eos_confirm_form");
    if (!D) {
        D = $create("form");
        D.id = "__eos_confirm_form";
        bodyAddNode(D)
    }
    D.action = C;
    D.method = "post";
    D.target = A;
    if (window.confirm(B)) D.submit()
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
function moveScript(F, B) {
    var E = F.getElementsByTagName("script");
    if (B.parentWindow && F.parentWindow) B.parentWindow.contextPath = F.parentWindow.contextPath;
    var D = "";
    for (var A = 0; A < E.length; A++) if (E[A].src != "") D += "<script src='" + E[A].src + "'></script>";
    var C = $create("div", B);
    C.style.display = "none";
    B.body.appendChild(C);
    setInnerHTML(C, D, B)
}
function moveCss(H, B) {
	if(true){return;}
    var G = H.getElementsByTagName("link");
    for (var A = 0; A < G.length; A++) {
        var F = G[A].href;
        if (B.createStyleSheet) {
            var D = B.createStyleSheet();
            D.addImport(F);
            B.styleSheets.item[B.styleSheets.length] = D
        } else {
            var C = B.styleSheets.length,
            E = $create("style", B);
            E.setAttribute("type", "text/css");
            B.documentElement.childNodes[0].appendChild(E);
            B.styleSheets[C].insertRule("@import url(" + F + ");", 0)
        }
    }
}
function addContextPath(A) {
    if (A) if (A.indexOf("/") == 0) {
        var B = window["contextPath"];
        if (A.indexOf(B) != 0) return B + A
    }
    return A
}
function getParentByTagName(A, B) {
    if (B && A) {
        B = B.toLowerCase();
        while ((A = A.parentNode)) if (A.tagName && A.tagName.toLowerCase() == B) return A;
        return null
    } else return A ? A: null
}
function getEventTargetByTagName(B, D, C) {
    var A = B.srcElement || B.target;
    if (!A) return null;
    C = C || 5;
    var D = D.toLowerCase();
    do
    if (A.tagName && A.tagName.toLowerCase() == D) return A;
    while ((A = A.parentNode) && ((C--) > 0));
    return null
}
function getScreenPosition(B, E) {
    var F = window,
    D = getElementXY(B),
    C = D[0],
    A = D[1];
    while (F != E && F != top) {
        if (F.frameElement) {
            D = getElementXY(F.frameElement);
            C = C + D[0];
            A = A + D[1]
        }
        F = F.parent
    }
    return [C, A]
}
function addButtonToText(K) {
    if (K) {
        var F = $create("div");
        F.hideFocus = true;
        F.style.display = "block";
        F.style.zIndex = 99999;
        K.className = "eos-drop-down-text";
        function M() {
            bodyAddNode(F);
            document.body.appendChild(F)
        }
        if ((isIE || isIE9 || isIE10)) {
            if (document.readyState == "complete") M();
            else eventManager.add(window, "load", M)
        } else {
            try {
                M()
            } catch(N) {
                eventManager.add(window, "load", M)
            }
        }
        function E() {
            if (!K.getAttribute("_isFocus") && !K.disabled) {
                F.className = "eos-drop-down-button-over";
                setButtonPosition(K, F);
                K.className = "eos-drop-down-text-over"
            }
        }
        function G() {
            if (!K.disabled && !K.readOnly) {
                F.className = "eos-drop-down-button-focus";
                setButtonPosition(K, F);
                K.className = "eos-drop-down-text-focus"
            }
        }
        function H() {
            if (!K.getAttribute("_isOver") && !K.getAttribute("_isFocus") && !F.getAttribute("_isOver")) K.className = "eos-drop-down-text"
        }
        function C() {
            K.setAttribute("_isOver", true);
            E()
        }
        function J() {
            K.setAttribute("_isFocus", true);
            G();
            eventManager.stopPropagation()
        }
        function O() {
            F.setAttribute("_isOver", true);
            E()
        }
        function A() {
            F.setAttribute("_isFocus", true);
            G()
        }
        function I() {
            K.setAttribute("_isOver", false);
            setTimeout(H, 300)
        }
        function D() {
            K.setAttribute("_isFocus", false);
            setTimeout(H, 300)
        }
        function L() {
            F.setAttribute("_isOver", false);
            setTimeout(H, 300)
        }
        function B() {
            F.setAttribute("_isFocus", false);
            setTimeout(H, 300)
        }
        eventManager.add(K, "mouseover", C);
        eventManager.add(K, "focus", J);
        eventManager.add(K, "click", J);
        eventManager.add(F, "mouseover", O);
        eventManager.add(F, "focus", A);
        eventManager.add(F, "click", A);
        eventManager.add(K, "mouseout", I);
        eventManager.add(K, "blur", D);
        eventManager.add(F, "mouseout", L);
        eventManager.add(F, "blur", B);
        eventManager.addOutClick(K, D);
        return F
    }
    return null
}
function hideButton(B, D) {
    var C = B.offsetHeight,
    A = B.offsetWidth;
    B.style.width = A + "px"
}
function setButtonPosition(C, G) {
    var D = C.offsetHeight,
    B = C.offsetWidth;
    C.style.width = B + "px";
    var F = getElementXY(C);
    if (F) {
        var A = F[1];
        G.style.position = "absolute";
        var E = F[0] * 1 + B * 1 - D;
        D = D - (isBorderBox ? 0 : 2);
        G.style.width = D + "px";
        G.style.height = D + "px";
        G.style.overflow = "hidden";
        G.style.top = "0px";
        G.style.left = "0px";
        G.style.display = "";
        setElementXY(G, [E, A])
    }
}
function getRect(B) {
    var C = getPosition(B),
    A = C.left + B.offsetWidth,
    D = C.top + B.offsetHeight;
    return {
        left: C.left,
        top: C.top,
        right: A,
        bottom: D
    }
}
function moveSelectedOptions(C, E, B) {
    B = Number(B);
    if (isNaN(B)) B = Number.MAX_VALUE;
    var F = [];
    for (var A = 0; A < C.options.length; A++) {
        var D = C.options[A];
        if (D.selected) F.push(D)
    }
    for (A = 0; A < F.length; A++) if (E.options.length >= B) break;
    else {
        D = F[A];
        E.appendChild(D)
    }
}
function moveAllOptions(C, E, B) {
    B = Number(B);
    if (isNaN(B)) B = Number.MAX_VALUE;
    var F = [];
    for (var A = 0; A < C.options.length; A++) {
        var D = C.options[A];
        F.push(D)
    }
    for (A = 0; A < F.length; A++) {
        if (E.options.length >= B) break;
        D = F[A];
        E.appendChild(D)
    }
}
function compileTemplate(template, t_start, t_end, t_script, varName) {
    var TEMPLATE_START = t_start || "$[",
    TEMPLATE_END = t_end || "]",
    TEMPLATE_SCRIPT = t_script || "$",
    startLength = TEMPLATE_START.length,
    endLength = TEMPLATE_END.length,
    scriptLength = TEMPLATE_SCRIPT.length,
    templateC = [],
    snippets = [],
    current = 0;
    while (true) {
        var start = template.indexOf(TEMPLATE_START, current),
        sBegin = start + startLength,
        sEnd = template.indexOf(TEMPLATE_END, sBegin);
        if (sBegin >= startLength && sEnd > sBegin) {
            templateC.push(template.substring(current, start));
            var sn = template.substring(sBegin, sEnd);
            if (sn.indexOf(TEMPLATE_SCRIPT) == 0) sn = eval(sn.substring(scriptLength));
            else snippets.push(templateC.length);
            templateC.push(sn)
        } else {
            templateC.push(template.substring(current));
            break
        }
        current = sEnd + endLength
    }
    templateC.snippets = snippets;
    templateC.varName = varName;
    return templateC
}
function runExpression(C, F) {
    var B = C.snippets,
    G = [];
    for (var A = 0,
    E = 0,
    D = C.length; A < D; A++) if (B[E] == A) {
        G[A] = F.getProperty(C[A]);
        E++
    } else G[A] = C[A];
    return G.join("")
}
function sortTableByCol(J, B, H) {
    J = $id(J);
    var C = getParentByTagName(J, "table");
    if (!J || !C) return;
    var G;
    try {
        G = C.getElementsByTagName("thead");
        G = G ? G[0] : null
    } catch(Q) {
        G = null
    }
    var F = C.tBodies[0],
    K = G && G.rows.length > 0 ? 0 : (J.parentNode.rowIndex + 1),
    D = F.rows,
    N = [];
    for (var M = K; M < D.length; M++) {
        N[M - K] = D[M];
        if (!N[M - K].getAttribute("__eos_orgorder")) N[M - K].setAttribute("__eos_orgorder", M + 1 + "")
    }
    var P = I;
    function E(A) {
        return A.getAttribute("value")
    }
    function I(A, B) {
        return A.innerText
    }
    function A(A, B) {
        return B.getAttribute("__eos_orgorder")
    }
    H = H == "asc" ? "desc": "asc";
    if (C.sortCol == J) {
        H = J.getAttribute("__eos_sort");
        if (H == "desc") {
            P = A;
            H = "default";
            N.sort(sortCompareTRs(J.cellIndex, B, H, P))
        } else if (H == "default") {
            H = "asc";
            N.sort(sortCompareTRs(J.cellIndex, B, H, P))
        } else {
            N.reverse();
            H = H == "asc" ? "desc": "asc"
        }
    } else N.sort(sortCompareTRs(J.cellIndex, B, H, P));
    var O = document.createDocumentFragment();
    for (M = 0; M < N.length; M++) O.appendChild(N[M]);
    F.appendChild(O);
    if (C.sortFlag && C.sortCol != J) C.sortFlag.className = "eos-sorttable-default";
    C.sortCol = J;
    var L = J.getElementsByTagName("span");
    if (L.length < 1 || (L[L.length - 1].className + "").indexOf("eos-sorttable-") < 0) {
        C.sortFlag = $createElement("span");
        J.appendChild(C.sortFlag)
    } else C.sortFlag = L[L.length - 1];
    J.setAttribute("__eos_sort", H);
    J.style.position = "relative";
    C.sortFlag.className = "eos-sorttable-" + H;
    C.sortFlag.innerHTML = "&#160;"
}
function sortCompareTRs(E, C, G, B) {
    var F = 1;
    if (G == "desc" || G == "d") F = -1;
    function A(B, A) {
        switch (A) {
        case "int":
            return parseInt(B);
        case "float":
            return parseFloat(B);
        case "date":
            return "" + B;
        default:
            return "" + B
        }
    }
    return function D(D, J) {
        var G, I;
        G = A(B(D.cells[E], D), C);
        I = A(B(J.cells[E], J), C);
        if (G < I) return - 1 * F;
        else if (G > I) return 1 * F;
        else return 0
    }
}
function submitFormBy(D, B, A, C) {
    if (D) {
        D.action = A || D.action;
        D.target = C || D.target;
        if (D.elements["_eosFlowAction"]) D.elements["_eosFlowAction"].value = B;
        D.submit()
    }
}
function gotoPage(F, K, C, B, D, A) {
    var H;
    if (A) H = $id(A) || $name(A);
    var L, J, I;
    if (H) {
        L = H.elements[F + "/begin"];
        J = H.elements[F + "/length"];
        I = H.elements[F + "/count"]
    } else {
        L = $e(F + "/begin");
        J = $e(F + "/length");
        I = $e(F + "/count");
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
    if (G) F = G.elements[E + "/begin"];
    else {
        F = $e(E + "/begin");
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
        J = K.elements[I + "/begin"];
        H = K.elements[I + "/length"]
    } else {
        J = $e(I + "/begin");
        H = $e(I + "/length");
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
        I = J.elements[H + "/begin"];
        G = J.elements[H + "/length"]
    } else {
        I = $e(H + "/begin");
        G = $e(H + "/length");
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
        I = J.elements[H + "/begin"];
        G = J.elements[H + "/length"];
        D = J.elements[H + "/count"]
    } else {
        I = $e(H + "/begin");
        G = $e(H + "/length");
        D = $e(H + "/count");
        J = getParentByTagName(I, "form")
    }
    var E = Math.floor(((D.value / 1) + (G.value / 1) - 1) / (G.value / 1));
    I.value = (E - 1) * (G.value / 1);
    J = getParentByTagName(I, "form");
    submitFormBy(J, C, B, F)
}
function getMaxZindex(E) {
    E = E || document;
    var C = E.all || E.getElementsByTagName("*"),
    B = C.length,
    G = 0;
    if ((isIE || isIE9 || isIE10)) {
        for (var D = 0;; D++) {
            var F = C[D];
            if (F == null) break;
            var A = parseInt(GetCurrentStyle(F, "zIndex"));
            if (!isNaN(A)) if (A > G) G = A
        }
    } else for (D = 0; D < B; D++) {
        F = C[D],
        A = parseInt(GetCurrentStyle(F, "zIndex"));
        if (!isNaN(A)) if (A > G) G = A
    }
    return G + 1
}
function getCurrentStyle(A, B) {
    if (A.currentStyle) return A.currentStyle[B];
    else if (window.getComputedStyle) {
        B = B.replace(/([A-Z])/g, "-$1");
        B = B.toLowerCase();
        var C = window.getComputedStyle(A, "");
        if (C) return C.getPropertyValue(B);
        else return null
    }
    return null
}
var GetCurrentStyle = getCurrentStyle;
function setOpacity(B, A) {
    B = $e(B);
    if (!B) return B;
    A = A > 1 ? 1 : (A < 0 ? 0 : A);
    if (!B.currentStyle || !B.currentStyle.hasLayout) B.style.zoom = 1;
    if ((isIE || isIE9 || isIE10)) B.style.filter = (A == 1) ? "": "alpha(opacity=" + A * 100 + ")";
    B.style.opacity = A;
    if (A === 0) {
        if (B.style.visibility != "hidden") B.style.visibility = "hidden"
    } else if (B.style.visibility != "visible") B.style.visibility = "visible";
    return B
}
function fx_size(G, C, F, B, A) {
    var E = $(G),
    D = E.effects({
        duration: A || 400
    }),
    H = {};
    if (C !== null && C !== undefined) H.width = C;
    if (F !== null && F !== undefined) H.height = F;
    D.start(H).chain(B ||
    function() {})
}
function fx_fadeIn(E, B, A) {
    var D = $(E);
    D.setOpacity(0);
    D.setStyle("display", "");
    var C = D.effects({
        duration: A || 400
    }),
    F = {
        "opacity": 1
    };
    C.start(F).chain(B ||
    function() {})
}
function fx_fadeOut(E, B, A) {
    var D = $(E),
    C = D.effects({
        duration: A || 400
    }),
    F = {
        "opacity": 0
    };
    C.start(F).chain(B ||
    function() {
        D.setOpacity(0);
        D.setStyle("display", "none")
    })
}
function fx_slideIn(A, B) {
    B = B || new Fx.Slide(A);
    B.slideIn();
    return B
}
function fx_slideOut(A, B) {
    B = B || new Fx.Slide(A);
    B.slideOut();
    return B
}
function fx_DD(B, C) {
    C = C || {};
    var A = new Drag.Move($(B), C)
}
function getScreenPosition(B, E) {
    var F = window,
    D = getElementXY(B),
    C = D[0],
    A = D[1];
    while (F != E && F != top) {
        if (F.frameElement) {
            D = getElementXY(F.frameElement, F.parent);
            C = C + D[0];
            A = A + D[1]
        }
        F = F.parent
    }
    return [C, A]
}
function accordionCollapse(B) {
    if (B.processaccordion) return;
    if (B.style.display == "none") return;
    B.processaccordion = true;
    var A = B.nextSibling;
    if (A == null || !A.isAccordion) {
        A = document.createElement("div");
        A.isAccordion = true;
        A.style.overflow = "hidden";
        var C = B.parentNode;
        C.insertBefore(A, B.nextSibling)
    }
    A.style.width = B.offsetWidth;
    A.style.height = B.offsetHeight;
    A.style.display = "";
    B.style.width = B.clientWidth;
    B.style.position = "absolute";
    var D = 1;
    setTimeout(function() {
        return accordionIn.apply(B, [{
            "div": A,
            "acc": B,
            "height": B.offsetHeight,
            "progress": 0,
            "step": D
        }])
    },
    41)
}
function accordionExpand(B) {
    if (B.processaccordion) return;
    if (B.style.display != "none") return;
    B.processAccoridion = true;
    var A = B.nextSibling;
    if (A == null || !A.isAccordion) {
        A = document.createElement("div");
        A.isAccordion = true;
        var C = B.parentNode;
        A.style.overflow = "hidden";
        C.insertBefore(A, B.nextSibling)
    }
    B.style.display = "";
    B.style.width = B.clientWidth;
    B.style.position = "absolute";
    A.style.width = B.offsetWidth;
    A.style.height = 0;
    A.style.display = "";
    var E = B.offsetHeight,
    F = 5,
    D = 5;
    while (true) {
        D = D + F * 3;
        if (D >= E) break;
        else F = F * 3
    }
    var G = E - (D - F * 3);
    A.style.height = G;
    B.style.marginTop = G * -1;
    B.style.clip = "rect(" + G + " auto auto auto)";
    setTimeout(function() {
        return accordionOut.apply(B, [{
            "div": A,
            "acc": B,
            "height": E,
            "progress": G,
            "step": F
        }])
    },
    41)
}
function accordionIn(A) {
    A.progress = A.progress + A.step;
    if (A.step < 12) A.step = A.step + 1;
    else A.step = A.step * 3;
    A.acc.style.clip = "rect(" + A.progress + " auto auto auto)";
    A.acc.style.marginTop = A.progress * -1;
    if (A.height - A.progress < 0) A.div.style.display = "none";
    else A.div.style.height = A.height - A.progress;
    if (A.progress >= A.height) {
        A.acc.style.display = "none";
        A.div.style.display = "none";
        A.acc.processaccordion = false
    } else setTimeout(function() {
        return accordionIn.apply(A, [A])
    },
    41)
}
function accordionOut(A) {
    if (A.height - A.progress <= 5) A.progress = A.progress + 1;
    else {
        A.progress = A.progress + A.step;
        A.step = A.step / 3
    }
    A.acc.style.clip = "rect(" + (A.height - A.progress) + " auto auto auto)";
    A.acc.style.marginTop = A.progress - A.height;
    A.div.style.height = A.progress;
    if (A.progress >= A.height) {
        A.acc.style.position = "static";
        A.div.style.display = "none";
        A.acc.processaccordion = false;
        return
    } else setTimeout(function() {
        return accordionOut.apply(A, [A])
    },
    41)
}
function msgbox(A) {
    showModalCenter(contextPath + "/common/jsp/msgBox.jsp", [A], null, null, null, "\u93bb\u612e\u305a\u6dc7\u2103\u4f05")
}
function checkOnsubmit(B) {
    var C = B.submit,
    A = B.onsubmit;
    B.oldsubmit = C;
    B.oldOnsubmit = A;
    B.submit = function() {
        if (!checkForm(this)) return;
        else this.oldsubmit()
    };
    B.onsubmit = function() {
        if (checkForm(this)) {
            if (this.oldOnsubmit) return this.oldOnsubmit()
        } else return false
    }
}
var moveDivToCenter = function(B) {
    var A = _get_top_window() || window;
    B.style.top = ((A.document.body.clientHeight - B.offsetHeight) / 2 + A.document.body.scrollTop) + "px";
    B.style.left = ((A.document.body.clientWidth - B.offsetWidth) / 2 + A.document.body.scrollLeft) + "px"
};
function initShadow(C, E) {
    if (isFF) {
        E = E || document;
        var B = C.nextSibling;
        if (B == null || !B.isShadow) {
            var D = C.parentNode,
            F = C.offsetWidth,
            G = C.offsetHeight,
            A = $createElement("div", {
                doc: E
            });
            A.isShadow = true;
            D.style.width = F;
            D.style.height = G;
            A.style.width = F + 5;
            A.style.height = G + 5;
            A.style.position = "absolute";
            A.style.overflow = "hidden";
            A.style.left = 0;
            A.style.top = 0;
            A.style.zIndex = -999;
            D.insertBefore(A, C.nextSibling);
            C.shadowContainter = A;
            A.innerHTML = "<TABLE style=\"width: 100%;height:100%\" cellspacing=\"0\" cellpadding=\"0\"><TR height=\"5px\"><TD width=\"5px\"></TD><TD></TD><TD width=\"5px\" class=\"eos-shadow-right-top\"></TD></TR><TR><TD></TD><TD ><div ></div></TD><TD width=\"5px\"  class=\"eos-shadow-right\"></TD></TR><TR height=\"5px\"><TD width=\"5px\" class=\"eos-shadow-left-bottom\"></TD><TD class=\"eos-shadow-bottom\"></TD><TD width=\"5px\" class=\"eos-shadow-corner\"></TD></TR></TABLE>";
            var H = A.getElementsByTagName("div")[0];
            H.style.width = F - 5;
            H.style.height = G - 5;
            A.center = H;
            D.style.width = F + 5;
            D.style.height = G + 5
        } else {
            D = C.parentNode,
            F = C.offsetWidth,
            G = C.offsetHeight,
            A = C.shadowContainter;
            A.center.style.width = F - 5;
            A.center.style.height = G - 5;
            A.style.width = F + 5;
            A.style.height = G + 5;
            A.style.left = 0;
            A.style.top = 0;
            D.style.width = F + 5;
            D.style.height = G + 5
        }
    } else {
        E = E || document;
        B = C.nextSibling;
        if (B == null || !B.isShadow) {
            D = C.parentNode,
            F = C.offsetWidth,
            G = C.offsetHeight,
            A = $createElement("div", {
                doc: E
            });
            A.isShadow = true;
            D.style.width = F;
            D.style.height = G;
            A.style.width = F - 4;
            A.style.height = G - 4;
            A.style.position = "absolute";
            A.style.left = 0;
            A.style.top = 0;
            A.style.zIndex = -999;
            A.style.background = "#777";
            A.style.filter = "progid:DXImageTransform.Microsoft.alpha(opacity=50) progid:DXImageTransform.Microsoft.Blur(pixelradius=4)";
            D.insertBefore(A, C.nextSibling);
            C.shadowContainter = A;
            if (C.style.width == "") C.style.width = F
        } else {
            D = C.parentNode,
            F = C.offsetWidth,
            G = C.offsetHeight,
            A = C.shadowContainter;
            A.isShadow = true;
            D.style.width = F;
            D.style.height = G;
            A.style.width = F - 4;
            A.style.height = G - 4
        }
    }
}
function buttonMouseOver(A) {
    addClass(A, "eos-button-over");
    addClass(A.firstChild, "eos-button-inner-over")
}
function buttonMouseOut(A) {
    removeClass(A, "eos-button-over");
    removeClass(A.firstChild, "eos-button-inner-over")
}
function buttonMouseUp(A) {
    if (isIE) {
        removeClass(A, "eos-button-down");
        removeClass(A.firstChild, "eos-button-inner-down")
    }
}
function buttonMouseDown(A) {
    if (isIE) {
        addClass(A, "eos-button-down");
        addClass(A.firstChild, "eos-button-inner-down")
    }
}
function buttonForFF(A) {
    addClass(A, "eos-button-ff");
    addClass(A.firstChild, "eos-button-inner-ff")
}
function setEosControlStyleforIE() {
    if (isIE) {
        var F = document["styleSheets"],
        D = F.length;
        for (var A = 0; A < D; A++) {
            var C = F[A].rules,
            B = C.length;
            for (var E = 0; E < B; E++) if (C[E].selectorText == ".eos-ic-button") C[E].style.verticalAlign = "text-bottom"
        }
    }
}
eventManager.add(window, "load", setEosControlStyleforIE);
function flowSubmit(A) {
    var C = A.parentNode;
    while (C != null) if (C.tagName && C.tagName.toLowerCase() == "form") break;
    else C = C.parentNode;
    if (C == null) {
        alert("can not find form tag!");
        return
    }
    var B = $name("_eosFlowAction", C);
    if (B == null) {
        B = $createElement("input", {
            type: "hidden",
            name: "_eosFlowAction"
        });
        C.appendChild(B)
    }
    B.value = A.getAttribute("flowAction");
    C.submit()
}
function getCurrentStyle(A, B) {
    if (A.currentStyle) return A.currentStyle[B];
    else if (window.getComputedStyle) {
        propprop = B.replace(/([A-Z])/g, "-$1");
        propprop = B.toLowerCase();
        return document.defaultView.getComputedStyle(A, null)[B]
    }
}
var Drag = {
    obj: null,
    init: function(A, H, J, B, D, I, G, F, E, C) {
        A.onmousedown = Drag.start;
        A.hmode = G ? false: true;
        A.vmode = F ? false: true;
        A.root = H && H != null ? H: A;
        if (A.hmode && isNaN(parseInt(A.root.style.left))) A.root.style.left = "0px";
        if (A.vmode && isNaN(parseInt(A.root.style.top))) A.root.style.top = "0px";
        if (!A.hmode && isNaN(parseInt(A.root.style.right))) A.root.style.right = "0px";
        if (!A.vmode && isNaN(parseInt(A.root.style.bottom))) A.root.style.bottom = "0px";
        A.minX = typeof J != "undefined" ? J: null;
        A.minY = typeof D != "undefined" ? D: null;
        A.maxX = typeof B != "undefined" ? B: null;
        A.maxY = typeof I != "undefined" ? I: null;
        A.xMapper = E ? E: null;
        A.yMapper = C ? C: null;
        A.root.onDragStart = new Function();
        A.root.onDragEnd = new Function();
        A.root.onDrag = new Function()
    },
    start: function(D) {
        var A = Drag.obj = this;
        D = Drag.fixE(D);
        var B = parseInt(A.vmode ? A.root.style.top: A.root.style.bottom),
        C = parseInt(A.hmode ? A.root.style.left: A.root.style.right);
        A.root.onDragStart(C, B);
        A.lastMouseX = D.clientX;
        A.lastMouseY = D.clientY;
        if (A.hmode) {
            if (A.minX != null) A.minMouseX = D.clientX - C + A.minX;
            if (A.maxX != null) A.maxMouseX = A.minMouseX + A.maxX - A.minX
        } else {
            if (A.minX != null) A.maxMouseX = -A.minX + D.clientX + C;
            if (A.maxX != null) A.minMouseX = -A.maxX + D.clientX + C
        }
        if (A.vmode) {
            if (A.minY != null) A.minMouseY = D.clientY - B + A.minY;
            if (A.maxY != null) A.maxMouseY = A.minMouseY + A.maxY - A.minY
        } else {
            if (A.minY != null) A.maxMouseY = -A.minY + D.clientY + B;
            if (A.maxY != null) A.minMouseY = -A.maxY + D.clientY + B
        }
        document.onmousemove = Drag.drag;
        document.onmouseup = Drag.end;
        return false
    },
    drag: function(H) {
        H = Drag.fixE(H);
        var A = Drag.obj,
        B = H.clientY,
        F = H.clientX,
        D = parseInt(A.vmode ? A.root.style.top: A.root.style.bottom),
        G = parseInt(A.hmode ? A.root.style.left: A.root.style.right),
        E,
        C;
        if (A.minX != null) F = A.hmode ? Math.max(F, A.minMouseX) : Math.min(F, A.maxMouseX);
        if (A.maxX != null) F = A.hmode ? Math.min(F, A.maxMouseX) : Math.max(F, A.minMouseX);
        if (A.minY != null) B = A.vmode ? Math.max(B, A.minMouseY) : Math.min(B, A.maxMouseY);
        if (A.maxY != null) B = A.vmode ? Math.min(B, A.maxMouseY) : Math.max(B, A.minMouseY);
        E = G + ((F - A.lastMouseX) * (A.hmode ? 1 : -1));
        C = D + ((B - A.lastMouseY) * (A.vmode ? 1 : -1));
        if (A.xMapper) E = A.xMapper(D);
        else if (A.yMapper) C = A.yMapper(G);
        Drag.obj.root.style[A.hmode ? "left": "right"] = E + "px";
        Drag.obj.root.style[A.vmode ? "top": "bottom"] = C + "px";
        Drag.obj.lastMouseX = F;
        Drag.obj.lastMouseY = B;
        Drag.obj.root.onDrag(E, C);
        return false
    },
    end: function() {
        document.onmousemove = null;
        document.onmouseup = null;
        Drag.obj.root.onDragEnd(parseInt(Drag.obj.root.style[Drag.obj.hmode ? "left": "right"]), parseInt(Drag.obj.root.style[Drag.obj.vmode ? "top": "bottom"]));
        Drag.obj = null
    },
    fixE: function(A) {
        if (typeof A == "undefined") A = window.event;
        if (typeof A.layerX == "undefined") A.layerX = A.offsetX;
        if (typeof A.layerY == "undefined") A.layerY = A.offsetY;
        return A
    }
};
function $create(C, A) {
    A = A || document;
    if (C.indexOf("<") > -1) {
        var B = A.createElement("div");
        B.innerHTML = C;
        return firstElement(B)
    }
    return A.createElement(C)
}
function $createElement(F, E) {
    var B = E ? (E.doc || document) : document;
    if (F.indexOf("<") > -1) {
        var C = B.createElement("div");
        C.innerHTML = F;
        F = C.firstChild
    } else if ((isIE || isIE9 || isIE10) && E && (E.name || E.type)) {
        var G = (E.name) ? " name=\"" + E.name + "\"": "",
        D = (E.type) ? " type=\"" + E.type + "\"": "";
        delete E.name;
        delete E.type;
        F = "<" + F + G + D + ">";
        F = B.createElement(F)
    } else F = B.createElement(F);
    if (E) {
        if (E.style) {
            for (var A in E.style) F.style[A] = E.style[A];
            delete E.style
        }
        for (A in E) F[A] = E[A]
    }
    return F
}
function createSelect(D, C, E) {
    var F = $createElement("select", E || {});
    for (var B in D) {
        var A = $createElement("option", {
            "value": B,
            "text": "" + D[B],
            innerHTML: D[B]
        });
        if ((C || C === 0) && B == C) A.selected = true;
        F.appendChild(A)
    }
    return F
}
function getViewportHeight() {
    if (window.innerHeight != window.undefined) return window.innerHeight;
    if (document.compatMode == "CSS1Compat") return document.documentElement.clientHeight;
    if (document.body) return document.body.clientHeight;
    return window.undefined
}
function getViewportWidth() {
    if (window.innerWidth != window.undefined) return window.innerWidth;
    if (document.compatMode == "CSS1Compat") return document.documentElement.clientWidth;
    if (document.body) return document.body.clientWidth;
    return window.undefined
}
function bodyAddNode(B, A) {
    if (!B) return;
    A = A || document;
    var C = A.body.firstChild;
    if (C) A.body.insertBefore(B, C);
    else A.body.appendChild(B)
}
function addClass(B, D) {
    if (!B) return;
    var C = B.className.split(" ");
    for (var A = 0; A < C.length; A++) if (C[A] == D) return;
    C.push(D);
    B.className = C.join(" ")
}
function removeClass(B, D) {
    if (!B) return;
    var C = B.className.split(" ");
    for (var A = 0; A < C.length; A++) if (C[A] == D) C.splice(A, 1);
    B.className = C.join(" ")
}
function getPosition(A) {
    var B = A.offsetTop,
    C = A.offsetLeft,
    D = A;
    while (D = D.offsetParent) {
        if (D.tagName == "BODY") continue;
        B += D.offsetTop - D.scrollTop;
        C += D.offsetLeft - D.scrollLeft
    }
    return {
        "top": B,
        "left": C
    }
}
function getAbsPos(C) {
    var B = C.offsetTop,
    E = C.offsetLeft,
    F = C;
    while (F = F.offsetParent) {
        if (F && F.tagName == "BODY") {
            B -= F.scrollTop;
            E -= F.scrollLeft;
            continue
        }
        B += F.offsetTop - F.scrollTop;
        E += F.offsetLeft - F.scrollLeft
    }
    var D = _get_top_window();
    if (D != window) if ((isIE || isIE9 || isIE10)) {
        var H = window;
        do {
            var A = H.frameElement;
            if (A) {
                var G = getPosition(A);
                B = B + G.top - (H.scrollTop || 0);
                E = E + G.left - (H.scrollLeft || 0)
            }
            H = H.parent
        } while ( H && H != D );
        E += D.scrollLeft || 0;
        B += D.scrollTop || 0;
        if (isBorderBox) {
            E = E + 2;
            B = B + 2
        }
    } else {
        B = window.screen.top - D.screen.top + B;
        E = window.screen.left - D.screen.left + E
    }
    return {
        "top": B,
        "left": E
    }
}
function getScreenPos(F, C) {
    var E = 0,
    A = 0;
    if (F == C) return {
        left: 0,
        top: 0
    };
    else {
        var H = F;
        while (H && H != C) {
            var B = H.frameElement,
            G = H.parent;
            if (B) {
                var D = getPosition(B);
                E = E + D.left;
                A = A + D.top
            }
            H = H.parent
        }
    }
    return {
        left: E,
        top: A
    }
}
function createStyle(H, B, E) {
    var F = "display:",
    D;
    B = B || document;
    if (B.styleSheets.length == 0) {
        var C = B.createElement("style");
        B.body.appendChild(C)
    }
    var G = B.styleSheets[0];
    if (!E) H = "." + H;
    if (G.insertRule) {
        var A = G.insertRule(H + "{" + F + "}", G.length);
        return G.cssRules[A]
    } else {
        A = G.addRule(H, F, -1);
        if (A == -1) A = G.rules.length - 1;
        return G.rules[A]
    }
}
function getScroll(C) {
    var A = C,
    B = document;
    if (A == B || A == B.body) {
        var E = window.pageXOffset || B.documentElement.scrollLeft || B.body.scrollLeft || 0,
        D = window.pageYOffset || B.documentElement.scrollTop || B.body.scrollTop || 0;
        return [E, D]
    } else return [A.scrollLeft, A.scrollTop]
}
function translatePoints(B, G, D) {
    if (typeof G == "object" || G instanceof Array) {
        D = G[1];
        G = G[0]
    }
    var E = B.style.position,
    A = getElementXY(B),
    F = parseInt(B.style.left, 10),
    C = parseInt(B.style.top, 10);
    if (isNaN(F)) F = (E == "relative") ? 0 : B.offsetLeft;
    if (isNaN(C)) C = (E == "relative") ? 0 : B.offsetTop;
    return {
        left: (G - A[0] + F),
        top: (D - A[1] + C)
    }
}
function getElementXY(H, N) {
    var B, F, D, K, E = document.body;
    if (H && H.style.display == "none") return [0, 0];
    if (H.getBoundingClientRect) {
        try {
            D = H.getBoundingClientRect();
            K = getScroll(document);
            return [D.left + K[0], D.top + K[1]]
        } catch(M) {}
    }
    var L = 0,
    J = 0;
    B = H;
    N = N || E;
    var G = H.style.position == "absolute";
    while (B) {
        L += B.offsetLeft;
        J += B.offsetTop;
        if (!G && B.style.position == "absolute") G = true;
        if (isGecko) {
            F = B;
            var I = parseInt(F.style.borderTopWidth, 10) || 0,
            C = parseInt(F.style.borderLeftWidth, 10) || 0;
            L += C;
            J += I;
            if (B != H && F.style.overflow != "visible") {
                L += C;
                J += I
            }
        }
        B = B.offsetParent
    }
    if (isSafari && G) {
        L -= E.offsetLeft;
        J -= E.offsetTop
    }
    if (isGecko && !G) {
        var A = E;
        L += parseInt(A.style.borderTopWidth, 10) || 0;
        J += parseInt(A.style.borderTopWidth, 10) || 0
    }
    B = H.parentNode;
    while (B && B != E) {
        if (!isOpera || (B.tagName.toUpperCase() != "TR" && B.style.display != "inline")) {
            L -= B.scrollLeft || 0;
            J -= B.scrollTop || 0
        }
        B = B.parentNode
    }
    return [L, J]
}
function setElementXY(C, B, A) {
    if (C.style.position == "static") C.style.position = "relative";
    if (A !== true) {
        C.style.left = "0px";
        C.style.top = "0px"
    }
    var D = translatePoints(C, B);
    if (B[0] !== false) C.style.left = D.left + "px";
    if (B[1] !== false) C.style.top = D.top + "px"
}
function firstElement(C, A) {
    if (C) {
        A = A || 1;
        var B = 0,
        D = C.firstChild;
        while (D) {
            if (D.nodeType == 1) if (++B == A) return D;
            D = D.nextSibling
        }
    }
    return null
}
function prevElement(B, A) {
    if (B) {
        var C = B.previousSibling;
        while (C) {
            if (C.nodeType == 1) return C;
            C = C.previousSibling
        }
    }
    return null
}
function nextElement(B, A) {
    if (B) {
        var C = B.nextSibling;
        while (C) {
            if (C.nodeType == 1) return C;
            C = C.nextSibling
        }
    }
    return null
}
function getNodeValue(C) {
    var B = null;
    if (!C) return null;
    var A = C.getAttribute("__isNullOrEmpty");
    if (A == "null") return null;
    if (A == "empty") return "";
    if (C.text) B = C.text;
    else if (C.childNodes.length > 1) B = C.xml.replace(/<\/?[^>]+>/gi, "");
    else if (C.firstChild) B = C.firstChild.data;
    return B
}
function removeElement(C) {
    if (!C) return;
    var B = ["onclick", "ondblclick", "onmousedown", "onmouseup", "onmouseover", "onmouseout", "onchange", "onfocus", "onblur", "onkeypress", "onkeydown", "onkeyup"];
    for (var A = 0; A < B.length; A++) {
        C.setAttribute(B[A], null);
        C[B[A]] = null
    }
    if (C.parentNode) C.parentNode.removeChild(C)
}
function getFormElementsValue(C) {
    C = $e(C);
    var D = {};
    for (var B = 0; B < C.elements.length; B++) {
        var A = C.elements[B];
        if (!A.disabled && A.name) {
            var E = getElementValue(A);
            if (D[A.name] !== undefined) {
                D[A.name] = [].concat(D[A.name]);
                D[A.name].push(E)
            } else D[A.name] = E
        }
    }
    return D
}
function getElementValue(A) {
    A = $e(A);
    var E = A.tagName.toLowerCase();
    function B(A) {
        return A.checked ? A.value: null
    }
    function C(A) {
        return A.getAttribute("value") != null ? A.value: A.text
    }
    var D = {
        input: function(A) {
            switch (A.type.toLowerCase()) {
            case "checkbox":
            case "radio":
                return B(A);
            default:
                return A.value
            }
        },
        textarea: function(A) {
            return A.value
        },
        select: function(B) {
            switch (B.type.toLowerCase()) {
            case "select-one":
                var A = B.selectedIndex;
                return A >= 0 ? C(B.options[A]) : null;
            default:
                var F, E = B.length;
                if (!E) return null;
                for (var D = 0,
                F = []; D < E; D++) {
                    var G = B.options[D];
                    if (G.selected) F.push(C(G))
                }
                return F
            }
            return this[B.type == "select-one" ? "selectOne": "selectMany"](B)
        }
    },
    F = D[E];
    if (F == null) return null;
    return F(A)
}
var CSSUtil = function(D) {
    var C = null,
    B = D || document,
    E = /(-[a-z])/gi,
    A = function(A, B) {
        return B.charAt(1).toUpperCase()
    };
    return {
        createStyleSheet: function(A, H, F) {
            var G;
            F = F || B;
            var E = F.getElementsByTagName("head");
            if (!E || E.length < 1) {
                F.documentElement.insertBefore(F.createElement("head"), F.body);
                E = F.getElementsByTagName("head")
            }
            var D = E[0],
            C = F.createElement("style");
            C.setAttribute("type", "text/css");
            if (H) C.setAttribute("id", H);
            if (isIE) {
                D.appendChild(C);
                G = C.styleSheet;
                try {
                    G.cssText = A
                } catch(I) {}
            } else {
                try {
                    C.appendChild(F.createTextNode(A))
                } catch(I) {
                    C.cssText = A
                }
                D.appendChild(C);
                G = C.styleSheet ? C.styleSheet: (C.sheet || F.styleSheets[F.styleSheets.length - 1])
            }
            this.cacheStyleSheet(G);
            return G
        },
        removeStyleSheet: function(D, C) {
            C = C || B;
            var A = C.getElementById(D);
            if (A) A.parentNode.removeChild(A)
        },
        swapStyleSheet: function(E, A, C) {
            C = C || B;
            this.removeStyleSheet(E);
            var D = C.createElement("link");
            D.setAttribute("rel", "stylesheet");
            D.setAttribute("type", "text/css");
            D.setAttribute("id", E);
            D.setAttribute("href", A);
            C.getElementsByTagName("head")[0].appendChild(D)
        },
        refreshCache: function() {
            return this.getRules(true)
        },
        cacheStyleSheet: function(D) {
            if (!C) C = {};
            try {
                var A = D.cssRules || D.rules;
                for (var B = A.length - 1; B >= 0; --B) C[A[B].selectorText] = A[B]
            } catch(E) {}
        },
        getRules: function(F, G) {
            G = G || B;
            if (C == null || F) {
                C = {};
                var E = G.styleSheets;
                for (var D = 0,
                A = E.length; D < A; D++) {
                    try {
                        this.cacheStyleSheet(E[D])
                    } catch(H) {}
                }
            }
            return C
        },
        getRule: function(C, B) {
            var D = this.getRules(B);
            if (! (C instanceof Array)) return D[C];
            for (var A = 0; A < C.length; A++) if (D[C[A]]) return D[C[A]];
            return null
        },
        updateRule: function(F, G, C) {
            if (! (F instanceof Array)) {
                var D = this.getRule(F);
                if (D) {
                    D.style[G.replace(E, A)] = C;
                    return true
                }
            } else for (var B = 0; B < F.length; B++) if (this.updateRule(F[B], G, C)) return true;
            return false
        },
        getCssText: function(B) {
            var A = CSSUtil.getRule(B);
            return A ? B + " { " + CSSUtil.getRule(B).style.cssText + " } ": ""
        }
    }
} (),
EOSLOG_LEVEL = EOSLOG_LEVEL || "debug",
EOSDEBUG = EOSDEBUG == undefined ? false: EOSDEBUG;
function escapeHTML(A) {
    if (typeof(A) == "string") return A.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\t/g, "&nbsp;&nbsp;").replace(/\n/g, "<br>");
    else return A
}
if (typeof(EOSLog) != "function") {
    EOSLog = function() {};
    EOSLog.styles = new Array();
    EOSLog.messages = [];
    EOSLog.ID = "EOS_LOG_CONSOLE";
    EOSLog.BODY_ID = "EOS_LOG_CONSOLE_BODY";
    EOSLog.BUTTONS_ID = "EOS_LOG_CONSOLE_BUTTONS";
    EOSLog.EVAL_ID = "EOS_LOG_EVAL_ID";
    EOSLog.EVAL_INPUT_ID = "EOS_LOG_EVAL_INPUT_ID";
    EOSLog.EVAL_RUN_ID = "EOS_LOG_EVAL_RUN_ID";
    EOSLog.EVAL_CLEAR_ID = "EOS_LOG_EVAL_CLEAR_ID";
    EOSLog.EVAL_SIGN_ID = "EVAL_SIGN_ID";
    EOSLog.EVAL_SIGN_SELECT_ID = "EVAL_SIGN_SELECT_ID";
    EOSLog.index = 0;
    EOSLog.iDiffX = 0;
    EOSLog.iDiffY = 0;
    EOSLog.hasOut = false;
    EOSLog.timeLog = new Array();
    EOSLog.panel = null;
    EOSLog.topWin = window;
    EOSLog.doc = document
}
EOSLog.log = function(A, C) {
    var B = new Date();
    EOSLog.messages.push({
        level: A,
        time: B,
        message: C,
        out: false
    });
    if (EOSLog.panel) EOSLog.refresh()
};
EOSLog.refresh = function() {
    if (EOSLog.panel) {
        var A;
        for (A = EOSLog.index; A < EOSLog.messages.length; A++) EOSLog.print(EOSLog.messages[A]);
        EOSLog.index = A == 0 ? 0 : A - 1
    }
};
EOSLog.info = function(A) {
    EOSLog.log("info", escapeHTML(A))
};
EOSLog.error = function(A) {
    EOSLog.log("error", escapeHTML(A))
};
EOSLog.debug = function(A) {
    EOSLog.log("debug", escapeHTML(A))
};
EOSLog.warn = function(A) {
    EOSLog.log("warn", escapeHTML(A))
};
EOSLog.handle = function(B) {
    var A = new StringBuffer();
    A.append(EOSLOG_ERROR_TYPE).append(B.name).append(".");
    A.append(EOSLOG_ERROR_INFO).append(B.message).append(".");
    if (B.number) A.append(EOSLOG_ERROR_CODE).append(B.number).append(".>");
    if (B.fileName) A.append(EOSLOG_ERROR_URL).append(B.fileName).append(".");
    if (B.lineNumber) A.append(EOSLOG_ERROR_LINE).append(B.lineNumber).append(".");
    if (B.stack) A.append(EOSLOG_ERROR_STACK).append(B.stack.replace(/ /g, "&nbsp;").replace(/\n/g, "<br>").replace(/\t/g, "&nbsp;&nbsp;&nbsp;&nbsp;")).append("<br>");
    if (B.description) A.append(EOSLOG_ERROR_DETAIL).append(B.description).append("<br>");
    if (EOSLog.handle.caller && EOSLog.handle.caller.caller) A.append(EOSLOG_ERROR_FUNCTION).append("<br>").append(EOSLog.handle.caller.caller.toString().replace(/ /g, "&nbsp;").replace(/\n/g, "<br>").replace(/\t/g, "&nbsp;&nbsp;&nbsp;&nbsp;")).append("<br>");
    EOSLog.log("error", A.toString())
};
window.onerror = function(E, B, C) {
    if (EOSDEBUG) {
        var D = new StringBuffer();
        if (isIE) {
            try {
                var A = EOSLog.getDetailError(window.onerror.caller, E, B, C);
                D.append(EOSLOG_ERROR_INFO).append(A.sMessage).append("<br> ");
                D.append(EOSLOG_ERROR_URL).append(A.sUrl).append("<br> ");
                D.append(EOSLOG_ERROR_LINE).append(A.sLine).append("<br> ");
                D.append(EOSLOG_ERROR_FUNCTION).append("<br>").append(A.errFunc).append("<br>")
            } catch(F) {}
        } else {
            D.append(EOSLOG_ERROR_INFO).append(E).append("<br> ");
            D.append(EOSLOG_ERROR_URL).append(B).append("<br> ");
            D.append(EOSLOG_ERROR_LINE).append(C).append("<br> ")
        }
        EOSLog.log("error", D.toString())
    }
};
EOSLog.isShow = function(C, A) {
    var B = EOSLog.styles[C];
    if (A.checked) B.style.display = "block";
    else B.style.display = "none"
};
EOSLog.clear = function() {
    var A = $id(EOSLog.BODY_ID, EOSLog.doc);
    A.innerHTML = ""
};
EOSLog.console = function() {
	var eButton ="";
	if(window.isWebkit){
		eButton = "\"eos-button-gc\"";
	}else{
		eButton = "\"eos-button\"";
	}
    var E = $create("div", EOSLog.doc);
    E.style.position = "absolute";
    E.style.left = "300px";
    E.style.top = "300px";
    var C = new StringBuffer();
    if (isIE) C.append("<iframe style=\"position:absolute;z-index:-1;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>");
    var G = "",
    D = navigator.userAgent.toLowerCase().indexOf("msie 6") != -1;
    if (D) G = "style=\"filter:alpha(opacity=50)\"";
    C.append("<div style=\"z-index:1000\">");
    C.append("<TABLE  cellspacing=\"0\" cellpadding=\"0\"  class=\"eos-dialog\"><TR><TD " + G + " class=\"left-top-conner\"><div class=\"ieBlank\"></div></TD><TD  " + G + " class=\"top\"></TD><TD  " + G + " class=\"right-top-conner\"><div class=\"ieBlank\"></div></TD></TR><TR><TD  " + G + " class=\"left\"><div class=\"ieBlank\"></div></TD><TD style=\"background-color: #eaf0f2;\">");
    C.append("<div style='display:none;position: absolute;height=200px;overflow-y:auto; z-index: 1000;' id='").append(EOSLog.EVAL_SIGN_ID).append("'>");
    C.append("<select class='EOS-LOG-SIGN-DIV' size='10' id='").append(EOSLog.EVAL_SIGN_SELECT_ID).append("'></select></div>");
    C.append("<div onmouseup=\"style.cursor='auto'\" onmousedown=\"style.cursor='move';\" class='EOS-LOG-BUTTONS' style=\"width:100%\"id='");
    C.append(EOSLog.BUTTONS_ID).append("'><table width=\"100%\"align='right' border='0' style='font-size:9pt'><tr><td width=\"300px\">");
    C.append("<input type=\"checkbox\" checked onmousedown=\"EOSLog.isShow('time',this)\" name=\"time\" value=\"1\">TIME");
    C.append("<input type=\"checkbox\" checked onmousedown=\"EOSLog.isShow('info',this)\" name=\"info\" value=\"1\">INFO");
    C.append("<input type=\"checkbox\" checked onmousedown=\"EOSLog.isShow('debug',this)\" name=\"degbug\" value=\"1\">DEBUG");
    C.append("<input type=\"checkbox\" checked onmousedown=\"EOSLog.isShow('warn',this)\" name=\"warn\" value=\"1\">WARN");
    C.append("<input type=\"checkbox\" checked onmousedown=\"EOSLog.isShow('error',this)\" name=\"error\" value=\"1\">ERROR</td><td align='right'>");
    C.append("&nbsp;<div  id=\"eos_log_button0\"  class="+eButton+" onclick=\"EOSLog.clear()\" onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">Clear</div></div>&nbsp;");
    C.append("<div id=\"eos_log_button1\"  class="+eButton+" onclick=\"EOSLog.command()\" onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">Cmd</div></div>&nbsp;");
    C.append("<div id=\"eos_log_button2\"  class="+eButton+" onclick=\"EOSLog.hide()\"onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">Close</div></div>&nbsp;");
    C.append("</td></tr></table></div>");
    C.append("<div status='hide' class='EOS-LOG-EVAL' id='").append(EOSLog.EVAL_ID).append("' style='width:100%;display:none'><div>");
    C.append("<textarea style='width:100%;height:100px;font-size: 12px' id='").append(EOSLog.EVAL_INPUT_ID).append("'></textarea>");
    C.append("</div><div height='20'>");
    C.append("<div  id=\"eos_log_button3\"  class="+eButton+" onclick=\"EOSLog.runEval()\"onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">Run</div></div>&nbsp;");
    C.append("<div  id=\"eos_log_button4\"  class="+eButton+" onclick=\"EOSLog.clearEval()\"onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">Clear</div></div>&nbsp;");
    C.append("</div>");
    C.append("</div>");
    C.append("<div style='border-top:#C0C0C0 solid 1px;overflow:scroll' class='EOS-LOG-BODY' id='").append(EOSLog.BODY_ID).append("' style='overflow:auto'></div>");
    C.append("</TD><TD  " + G + " class=\"right\" id=\"eos_log_right\"><div class=\"ieBlank\"></div></TD></TR><TR><TD  " + G + " class=\"left-bottom-conner\"></TD><TD  " + G + " class=\"bottom\" id=\"eos_log_bottom\"></TD><TD  " + G + " class=\"right-bottom-conner\" id=\"eos_log_rightBottom\"></TD></TR></TABLE>");
    C.append("</div>");
    E.innerHTML = C.toString();
    E.id = EOSLog.ID;
    EOSLog.doc.body.appendChild(E);
    var F = $id(EOSLog.BUTTONS_ID, EOSLog.doc);
    E.style.display = "none";
    fx_DD(E, {
        handle: F
    });
    EOSLog.hide();
    var B = $id(EOSLog.EVAL_INPUT_ID, EOSLog.doc);
    eventManager.add(B, "keyup", EOSLog.evalKeyUp);
    eventManager.add(B, "keydown", EOSLog.evalKeyDown);
    EOSLog.hasOut = true;
    EOSLog.panel = $id(EOSLog.BODY_ID, EOSLog.doc);
    EOSLog.refresh();
    initDrag(E);
    if (isFF) for (var A = 0; A < 5; A++) buttonForFF(EOSLog.doc.getElementById("eos_log_button" + A))
};
EOSLog.command = function() {
    var A = $id(EOSLog.EVAL_ID, EOSLog.doc);
    if (A.style.display == "none") A.style.display = "";
    else A.style.display = "none"
};
EOSLog.runEval = function() {
    var cmd = $id(EOSLog.EVAL_INPUT_ID, EOSLog.doc),
    value = cmd.value;
    try {
        eval(value)
    } catch(err) {
        var buffer = new StringBuffer();
        buffer.append(EOSLOG_ERROR_TYPE).append(err.name).append("<br>");
        buffer.append(EOSLOG_ERROR_INFO).append(err.message).append("<br>");
        if (err.number) buffer.append(EOSLOG_ERROR_CODE).append(err.number).append("<br>");
        if (err.fileName) buffer.append(EOSLOG_ERROR_URL).append(err.fileName).append("<br>");
        if (err.lineNumber) buffer.append(EOSLOG_ERROR_LINE).append(err.lineNumber).append("<br>");
        if (err.stack) buffer.append(EOSLOG_ERROR_STACK).append(err.stack).append("<br>");
        if (err.description) buffer.append(EOSLOG_ERROR_DETAIL).append(err.description).append("<br>");
        buffer.append(EOSLOG_ERROR_FUNCTION).append("<br>").append(value).append("<br>");
        EOSLog.log("error", EOSLOG_EVAL_ERROR + "<br>" + buffer.toString())
    }
};
EOSLog.clearEval = function() {
    var A = $id(EOSLog.EVAL_INPUT_ID, EOSLog.doc);
    A.value = ""
};
EOSLog.print = function(A) {
    if (A.out) return;
    var C = $create("div", EOSLog.doc);
    C.className = "EOS-LOG-" + A.level;
    var B = new StringBuffer();
    B.append("<table border='0'><tr><td valign='top' width='20px'>").append("<div class='EOS-LOG-TITLE-").append(A.level).append("'>");
    B.append(A.level).append("</div></td><td class='EOS-LOG-WORD'>").append("<div class='EOS-LOG-TIME'>runtime:").append(A.time.toLocaleTimeString());
    B.append("</div>").append(A.message).append("</td></tr></table>");
    var D = EOSLog.panel;
    C.innerHTML = B.toString();
    if (D.firstChild) D.insertBefore(C, D.firstChild);
    else D.appendChild(C);
    A.out = true
};
EOSLog.init = function() {
    if (!EOSDEBUG) return false;
    if (window["EOSLog_inited"]) return;
    EOSLog.topWin = _get_top_window();
    EOSLog.doc = EOSLog.topWin.document;
    eventManager.add(document, "keyup", EOSLog.keypress);
    if (EOSLog.topWin == window) {
        EOSLog.styles["debug"] = createStyle("EOS-LOG-debug", EOSLog.doc);
        EOSLog.styles["info"] = createStyle("EOS-LOG-info", EOSLog.doc);
        EOSLog.styles["error"] = createStyle("EOS-LOG-error", EOSLog.doc);
        EOSLog.styles["time"] = createStyle("EOS-LOG-time", EOSLog.doc);
        EOSLog.styles["warn"] = createStyle("EOS-LOG-warn", EOSLog.doc);
        EOSLog.console();
        EOSLog.hide()
    } else if (!EOSLog.topWin["EOSLog"]) {
        moveScript(document, EOSLog.doc);
        moveCss(document, EOSLog.doc)
    }
    window["EOSLog_inited"] = true
};
EOSLog.keypress = function() {
    var A = eventManager.getKeyCode();
    if (A == 123) {
        EOSLog.show();
        return false
    }
};
EOSLog.show = function() {
    var A = $id(EOSLog.ID, EOSLog.doc);
    A.style.display = "block";
    A.style.zIndex = getMaxZindex(EOSLog.doc)
};
EOSLog.hide = function() {
    var A = $id(EOSLog.ID, EOSLog.doc);
    A.style.display = "none"
};
EOSLog.time = function(H, G, E) {
    if (!H) H = "SYSTEM";
    if (!EOSLog.timeLog[H]) {
        var D = new Date();
        EOSLog.timeLog[H] = {
            time: D,
            msg: G
        }
    } else {
        var F = new Date(),
        C = EOSLog.timeLog[H],
        A = F - C.time,
        B = new StringBuffer();
        B.append("ID:").append(H).append("<br>");
        if (C.msg) B.append("start:").append(C.msg).append("<br>");
        B.append(" \u9470\u6941\u6902:<b>").append(A).append("ms</b><br>");
        if (G) B.append("end:").append(G);
        EOSLog.log("time", B.toString());
        if (E) EOSLog.timeLog[H] = null
    }
};
function $info(B) {
    if ("info".indexOf(EOSLOG_LEVEL) >= 0) {
        var A = EOSLog.topWin["EOSLog"] || EOSLog;
        A.info(B)
    }
}
function $debug(B) {
    if ("info,debug".indexOf(EOSLOG_LEVEL) >= 0) {
        var A = EOSLog.topWin["EOSLog"] || EOSLog;
        A.debug(B)
    }
}
function $warn(B) {
    if ("info,debug,warn".indexOf(EOSLOG_LEVEL) >= 0) {
        var A = EOSLog.topWin["EOSLog"] || EOSLog;
        A.warn(B)
    }
}
function $error(B) {
    if ("info,debug,warn,error".indexOf(EOSLOG_LEVEL) >= 0) {
        var A = EOSLog.topWin["EOSLog"] || EOSLog;
        A.error(B)
    }
}
function $time(B) {
    var A = EOSLog.topWin["EOSLog"] || EOSLog;
    A.time(B)
}
function $handle(B) {
    var A = EOSLog.topWin["EOSLog"] || EOSLog;
    A.handle(B)
}
EOSLog.enable = function() {};
EOSLog.evalKeyUp = function() {
    var C = $id(EOSLog.EVAL_INPUT_ID, EOSLog.doc),
    D = eventManager.getKeyCode(),
    B = $id(EOSLog.EVAL_SIGN_ID, EOSLog.doc);
    if (D == 190) {
        var A = EOSLog.getkeyWord(C);
        EOSLog.sign(C, A)
    }
};
EOSLog.selectUpOption = function(C) {
    var A = C.selectedIndex - 1;
    if (A > -1) {
        var B = C.options[A];
        if (B) B.selected = true
    } else {
        B = C.options[0];
        if (B) B.selected = true
    }
};
EOSLog.selectDownOption = function(C) {
    var A = C.selectedIndex + 1,
    B = C.options[A];
    if (B) B.selected = true;
    else {
        B = C.options[0];
        if (B) B.selected = true
    }
};
EOSLog.evalKeyDown = function() {
    var C = $id(EOSLog.EVAL_INPUT_ID, EOSLog.doc),
    D = eventManager.getKeyCode(),
    B = $id(EOSLog.EVAL_SIGN_SELECT_ID, EOSLog.doc),
    A = $id(EOSLog.EVAL_SIGN_ID, EOSLog.doc);
    if (D == 9) {
        eventManager.stopPropagation();
        EOSLog.insertAtCaret(C, "    ")
    } else if (A.style.display != "none") if (D == 38) {
        eventManager.stopPropagation();
        EOSLog.selectUpOption(B);
        return false
    } else if (D == 40) {
        eventManager.stopPropagation();
        EOSLog.selectDownOption(B);
        return false
    } else if (D == 13) {
        eventManager.stopPropagation();
        EOSLog.insertAtCaret(C, B.value);
        A.style.display = "none";
        return false
    } else A.style.display = "none"
};
EOSLog.sign = function(ob, keyword) {
    var div = $id(EOSLog.EVAL_SIGN_ID, EOSLog.doc),
    props = false;
    try {
        var tempObj;
        if (keyword) if (keyword.indexOf(".") >= 0) {
            var arr = keyword.split(".");
            tempObj = window[arr[0]] || eval(arr[0]);
            for (var i = 1; i < arr.length; i++) if (tempObj) tempObj = tempObj[arr[i]];
            else tempObj = null
        } else tempObj = window[keyword] || eval(keyword);
        var select1 = $id(EOSLog.EVAL_SIGN_SELECT_ID, EOSLog.doc);
        select1.length = 0;
        var values = [];
        for (var a in tempObj) if (! (a + "").indexOf("__") == 0) {
            values.push(a);
            props = true
        }
        values.sort();
        for (i = 0; i < values.length; i++) {
            var option, a = values[i];
            option = new Option(a, a);
            select1.options.add(option)
        }
        if (select1.length < 10) select1.size = select1.length;
        else select1.size = 10;
        if (props) {
            div.style.display = "block";
            function select1Change() {
                EOSLog.insertAtCaret(ob, select1.value);
                select1.length = 0;
                div.style.display = "none"
            }
            eventManager.add(select1, "change", select1Change)
        } else div.style.display = "none"
    } catch(e) {
        div.style.display = "none"
    }
};
EOSLog.getkeyWord = function(C) {
    var H = $id(EOSLog.EVAL_SIGN_ID, EOSLog.doc);
    if (EOSLog.doc.selection) {
        var D = EOSLog.doc.selection.createRange(),
        E = EOSLog.doc.body.createTextRange();
        E.moveToElementText(C);
        for (end = 0; E.compareEndPoints("StartToEnd", D) < 0; end++) {
            E.moveStart("character", 1);
            for (var G = 0; G <= end; G++) if (C.value.charAt(G) == "\n") end++
        }
        var M = EOSLog.getSingWordByLeft(C, end);
        if (H.parentNode.tagName.toLocaleUpperCase() != "BODY") EOSLog.doc.body.appendChild(H);
        H.style.top = D["offsetTop"] + 16 + EOSLog.doc.body.scrollTop;
        H.style.left = D["offsetLeft"] + EOSLog.doc.body.scrollLeft;
        return M
    } else {
        var L = C;
        if (typeof(L.selectionStart) == "number") {
            start = L.selectionStart;
            end = L.selectionEnd;
            if (start == end) {
                var J = L.value.substring(0, end),
                K = EOSLog.getSingWordByLeft(L, end),
                A = J.split("\n"),
                B = A[A.length - 1],
                F = B.length * 6 - L.scrollLeft,
                I = A.length * 16 - L.scrollTop;
                if (F > L.offsetWidth) while (F > 0 && F > L.offsetWidth) {
                    F = F - L.offsetWidth;
                    I = I + 16
                }
                H.style.top = C.offsetTop + I;
                H.style.left = C.offsetLeft + F;
                return K
            }
        }
    }
    return null
};
EOSLog.getSingWordByLeft = function(D, C) {
    var A = D.value.substring(0, end),
    B = /([\w|\.]+)\.$/;
    B.test(A);
    return RegExp.$1
};
EOSLog.insertAtCaret = function(G, A) {
    if (isIE) {
        G.focus();
        EOSLog.doc.selection.createRange().text += A
    } else if (G.setSelectionRange) {
        var F = G.selectionStart,
        C = G.selectionEnd,
        B = G.value.substring(0, F),
        E = G.value.substring(C);
        G.value = B + A + E;
        var D = F + A.length;
        G.focus();
        G.setSelectionRange(D, D)
    } else alert("This   version   of   Mozilla   based   browser   does   not   support   setSelectionRange")
};
if (!window["EOSLog_inited"]) {
    try {
        EOSLog.init()
    } catch(e) {
        if (eventManager) eventManager.add(window, "load", EOSLog.init)
    }
}
EOSLog.scriptObject = {};
EOSLog.getScriptStr = function(A) {
    if (!EOSLog.scriptObject[A]) EOSLog.scriptObject[A] = getRemoteResource(A);
    return EOSLog.scriptObject[A]
};
function EOSDebugInfo() {
    this.sLine = null;
    this.sUrl = null;
    this.sMessage = null;
    this.errFunc = null;
    this.errLine = null;
    this.stackFunc = []
}
EOSLog.getDetailError = function(F, J, G, I) {
    var K = new EOSDebugInfo();
    K.sLine = I;
    K.sUrl = G;
    K.sMessage = J;
    K.errFunc = F;
    var A = null;
    if (F) {
        var L = document.documentElement.outerHTML;
        if (L.indexOf(F) > 0) A = getRemoteResource(window.location);
        else {
            K.sLine = I - 1;
            var B = document.getElementsByTagName("script");
            for (var H = 0; H < B.length; H++) {
                var C = B[H];
                if (C.src) {
                    var E = EOSLog.getScriptStr(C.src);
                    if (E && E.indexOf(F.toString()) >= 0) {
                        K.sUrl = C.src;
                        A = E;
                        break
                    }
                }
            }
        }
        if (A) {
            var D = A.split("\n");
            K.errLine = D[K.sLine - 1];
            K.errFunc = getDisplayStr(K.errFunc);
            K.errLine = getDisplayStr(K.errLine);
            if (K.errLine && K.errFunc) if (K.errFunc.indexOf(K.errLine) >= 0) K.errFunc = K.errFunc.replace(K.errLine, "<b><span style='color:red;background-color:#eeeeee;font-size:larger'>" + K.errLine + "</span></b>")
        }
    }
    return K
};
function getDisplayStr(A) {
    if (A) {
        A = A.toString();
        return A.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/ /g, "&nbsp;").replace(/\n/g, "<br>").replace(/\t/g, "&nbsp;&nbsp;&nbsp;&nbsp;")
    }
    return A
}
function getRemoteResource(A) {
    var B = createXMLHttp();
    B.open("GET", A, false);
    B.send(null);
    return B.responseText
}
function initDrag(C) {
    var B = EOSLog.topWin.fx_DD || fx_DD,
    D = $id("eos_log_right", EOSLog.doc),
    E = $id("eos_log_bottom", EOSLog.doc),
    A = $id("eos_log_rightBottom", EOSLog.doc);
    B(C, {
        handle: E,
        modifiers: true,
        onDrag: function() {
            var B = this.mouse.start["y"],
            A = this.mouse.now["y"];
            if (A - B + this.value.now["y"] >= 27) this.element.style.height = A - B + this.value.now["y"];
            resizeLogPanel(parseInt(C.style.height))
        },
        onComplete: function() {},
        onStart: function() {
            this.value.now["y"] = this.element.getStyle("height").toInt()
        }
    });
    B(C, {
        handle: D,
        modifiers: true,
        onDrag: function() {
            var B = this.mouse.start["x"],
            A = this.mouse.now["x"];
            if (A - B + this.value.now["x"] >= 150) this.element.style.width = A - B + this.value.now["x"];
            resizeLogPanel(null, parseInt(C.style.width))
        },
        onComplete: function() {},
        onStart: function() {
            this.value.now["x"] = this.element.getStyle("width").toInt()
        }
    });
    B(C, {
        handle: A,
        modifiers: true,
        onStart: function() {
            this.value.now["x"] = this.element.getStyle("width").toInt();
            this.value.now["y"] = this.element.getStyle("height").toInt()
        },
        onDrag: function() {
            var E = this.mouse.start["y"],
            A = this.mouse.now["y"];
            if (A - E + this.value.now["y"] >= 27) C.style.height = A - E + this.value.now["y"];
            var D = this.mouse.start["x"],
            B = this.mouse.now["x"];
            if (B - D + this.value.now["x"] >= 150) C.style.width = B - D + this.value.now["x"];
            resizeLogPanel(parseInt(C.style.height), parseInt(C.style.width))
        },
        onComplete: function() {}
    })
}
function resizeLogPanel(B, A) {
    if (B != null && B != "") EOSLog.panel.style.height = B - 27;
    if (A != null && A != "") EOSLog.panel.style.width = A
}
EOSLog.formatXML = function(H) {
    return H;
    try {
        var D = H.split(">"),
        G = "",
        F = 0;
        for (var A = 0; A < D.length - 1; A++) {
            var B = D[A];
            if (B.indexOf("<") != 0) {
                var C = B.split("<");
                for (var E = 0; E < F; E++) G = G + "\t";
                G = G + C[0] + "\n";
                F = F - 1;
                for (E = 0; E < F; E++) G = G + "\t";
                G = G + "<" + C[1] + ">\n"
            } else if (B.indexOf("</") != 0) {
                for (E = 0; E < F; E++) G = G + "\t";
                G = G + D[A] + ">\n";
                if (B.indexOf("<!") == -1 && B.indexOf("<?") == -1) F = F + 1
            } else {
                F = F - 1;
                for (E = 0; E < F; E++) G = G + "\t";
                G = G + D[A] + ">\n"
            }
        }
        return G
    } catch(I) {
        return H
    }
};
var EOSResizeObjects = [],
EOSResizeContainer = [];
function getTopContainer(A) {
    var B = null;
    while (true) {
        A = A.offsetParent;
        if (A == null) {
            if (B != null) if (B.getAttribute("height") != null || B.style.height != "") return B;
            break
        }
        if (A.tagName.toLowerCase() == "table") B = A
    }
    return null
}
function registerTopContainer(B) {
    var C = getTopContainer(B);
    if (C == null) return false;
    for (var A = 0; A < EOSResizeContainer.length; A++) if (EOSResizeContainer[A] == C) return true;
    EOSResizeContainer.push(C);
    return true
}
function eos_layout_doHResize() {
    if (isFF) {
        for (var A = 0; A < EOSResizeObjects.length; A++) {
            if (_eos_curr_open_panel != null) if (!EOSResizeObjects[A].isInCurrPanel()) {
                EOSResizeObjects[A].doAutoResizeS2 = false;
                continue
            }
            EOSResizeObjects[A].autoResizeS1()
        }
        for (A = 0; A < EOSResizeContainer.length; A++) EOSResizeContainer[A].style.height = EOSResizeContainer[A].offsetHeight - 1;
        setTimeout(_layout_auto_resize, 1)
    }
}
function _layout_auto_resize() {
    for (var A = 0; A < EOSResizeContainer.length; A++) EOSResizeContainer[A].style.height = EOSResizeContainer[A].getAttribute("height");
    for (A = 0; A < EOSResizeObjects.length; A++) {
        if (EOSResizeObjects[A].doAutoResizeS2 === false) {
            EOSResizeObjects[A].doAutoResizeS2 = true;
            continue
        }
        EOSResizeObjects[A].autoResizeS2()
    }
}
function _layout_setResised() {
    for (var A = 0; A < _eos_colsed_panel.length; A++) _eos_colsed_panel[A].needResize = true
}
eventManager.add(window, "resize", _layout_setResised);
if (isFF) {
    eventManager.add(window, "load", eos_layout_doHResize);
    eventManager.add(window, "resize", eos_layout_doHResize)
}
function createXMLHttp() {
    if (window.XMLHttpRequest) return (new XMLHttpRequest());
    var B = ["MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP"];
    for (var A = 0; A < B.length; A++) {
        try {
            xmlhttp_ver = B[A];
            return new ActiveXObject(B[A])
        } catch(C) {}
    }
    return null
}
function Ajax(A, B) {
    this.url = A;
    this.param = [];
    this.method = "POST";
    this.encoding = "utf-8";
    this.async = B || false;
    this.xmlParam = "";
    this.submitParam = null;
    this.isHTML = false;
    this.submitType = "xml";
    this.isCommonUrl = false;
    this.reset()
}
Ajax.prototype.initParam = function() {
    var B = "",
    E = "<root>";
    for (var A = 0; A < this.param.length; A++) {
        var D = this.param[A];
        B += "<param><key>" + D.key + "</key><value>" + D.value + "</value></param>"
    }
    E += "<params>" + B + "</params>\n";
    var C = this.xmlParam;
    if (C && C.indexOf("<root><data>") == 0) {
        C = C.substr("<root><data>".length);
        C = C.substr(0, C.lastIndexOf("</data></root>"))
    }
    E += "<data>" + C + "</data></root>";
    this.submitParam = E
};
Ajax.prototype.setAsync = function(A) {
    if (A == null) A = true;
    this.async = A
};
Ajax.prototype.wrapperUrl = function() {
    return this.isCommonUrl ? this.url: this.url + ".ajax?time=" + (new Date())
};
Ajax.prototype.wrapperData = function() {
    return "<?xml version=\"1.0\" encoding=\"" + this.encoding + "\"?>" + this.submitParam
};
Ajax.prototype.setRequestHeaders = function() {
    if (isMoz && this.xmlHttp.overrideMimeType) this.xmlHttp.overrideMimeType("text/html;charset=utf-8");
    this.xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + this.encoding);
    this.xmlHttp.setRequestHeader("X-Requested-With", "XMLHttpRequest");
    this.xmlHttp.setRequestHeader("_eosAjax", this.submitType);
    this.xmlHttp.setRequestHeader("encoding", this.encoding)
};
Ajax.prototype.reset = function() {
    this.retDom = null;
    this.responseText = null;
    this.xmlHttp = createXMLHttp()
};
Ajax.prototype.cancel = function() {
    if (!this.running) return this;
    this.running = false;
    this.xmlHttp.abort();
    this.reset();
    return this
};
Ajax.prototype.isSuccess = function() {
    return !! !this.getException()
};
Ajax.prototype.onSuccess = function() {};
Ajax.prototype.onFailure = function(A) {
    alert(AJAX_EXCEPTION + "\n\n" + A)
};
Ajax.prototype.callBack = function() {
    var A = false,
    C = null,
    B = this.getExceptionInvalid();
    if (B === true || B === "true") {
        C = this.getLoginPage();
        if (C) A = true;
        B = AJAX_EXCEPTION_INVALID + "\n\n" + this.url
    } else B = this.getExceptionMessage() || this.getException();
    if (A) {
        if (window.contextPath && C.indexOf(window.contextPath + "/") != 0) C = window.contextPath + (C.indexOf("/") == 0 ? "": "/") + C;
        top.location = C;
        return
    }
    if (B && this.onFailure) return this.onFailure(B);
    return this.onSuccess()
};
Ajax.prototype.onStateChange = function() {
    if (!this.xmlHttp || this.xmlHttp.readyState != 4 || !this.running) return;
    try {
        $debug("\n return XML:\n " + EOSLog.formatXML(this.xmlHttp.responseText));
        this.responseText = this.xmlHttp.responseText;
        this.status = this.xmlHttp.readyState;
        this.retDom = createXmlDom();
        this.retDom.loadXML(this.responseText);
        return this.callBack.apply(this, arguments)
    } catch(A) {
        $handle(A);
        return this.onFailure(A)
    } finally {
        this.running = false;
        this.xmlHttp.onreadystatechange = function() {}
    }
};
Ajax.prototype.submit = function(A) {
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
        var C = createXmlDom();
        C.loadXML(D);
        this.xmlHttp.send(C);
        if (!this.async) return this.onStateChange()
    } catch(F) {
        $error("xmlHttp ajax request error.");
        this.running = false
    }
};
Ajax.prototype.getXMLDom = function() {
    if (this.retDom) return this.retDom
};
Ajax.prototype.getText = function() {
    if (this.xmlHttp) return this.xmlHttp.responseText
};
Ajax.prototype.getValue = function(B) {
    if (this.retDom == null) return null;
    if (B == null || B == "") return null;
    var A = this.retDom.selectSingleNode(B);
    if (A != null) return getNodeValue(A);
    else return null
};
Ajax.prototype.getValues = function(D) {
    var B = new Array();
    if (this.retDom == null) return B;
    if (D == null || D == "") return B;
    var E = this.retDom.selectNodes(D);
    for (var A = 0; A < E.length; A++) {
        var C = E[A];
        B[A] = getNodeValue(C)
    }
    return B
};
Ajax.prototype.getDataset = function(B, C) {
    var C = C || B,
    A = new Dataset(C);
    A.init(this.retDom.selectNodes(B))
};
Ajax.prototype.setForm = function(C, D) {
    for (var A = 0; A < C.elements.length; A++) {
        var B = C.elements[A];
        this.setObjectValue(B, null, D)
    }
};
Ajax.prototype.setObjectValue = function(B, E, F) {
    if (! (B) || B == null) return;
    var C = E;
    if (C == null) if (B.name && B.name != null && B.name != "") if (F != null) C = F + "/" + B.name;
    else C = B.name;
    if (! (B.type)) {
        if (C == null) if (B.name && B.name != "") C = B.name;
        else if (B.id && B.id != "") C = B.id;
        if (C != null) {
            var I = this.getProperty(C);
            if (I != null) B.innerText = I
        }
        return
    }
    if (C != null) {
        if (B.type == "text" || B.type == "hidden") {
            I = this.getProperty(C);
            if (I != null) B.value = I;
            return
        }
        if (B.type == "radio" || B.type == "checkbox") {
            var H = this.dom.selectNodes("root/data/" + C);
            for (var G = 0; G < H.length; G++) if (B.value == H[G].text) B.checked = true;
            else B.checked = false;
            return
        }
        if (B.type == "select-one") {
            I = this.getProperty(C);
            for (G = 0; G < B.options.length; G++) if (I == B.options[G].value) B.options[G].selected = true;
            return
        }
        if (B.type == "select-multiple") {
            var D = this.getValues("root/data/" + C);
            if (D.length > 0) for (G = 0; G < B.options.length; G++) {
                B.options[G].selected = false;
                for (var A = 0; A < D.length; A++) if (D[A] == B.options[G].value) B.options[G].selected = true
            }
            return
        }
    }
};
Ajax.prototype.Updater = function(C) {
    this.isHTML = true;
    this.submitType = "html";
    this.submit("utf-8");
    var B = this.retDom.selectSingleNode("root/data"),
    A = getNodeValue(B);
    setInnerHTML(C, A)
};
Ajax.prototype.addParam = function(A, B) {
    this.param.push({
        key: A,
        value: B
    })
};
Ajax.prototype.clearParam = function() {
    this.param = []
};
Ajax.prototype.getProperty = function(A) {
    if (this.retDom == null) return null;
    if (A == null || A == "") return null;
    var B = this.retDom.selectSingleNode("root/data/" + A);
    if (B != null) return getNodeValue(B);
    else return null
};
Ajax.prototype.submitForm = function(D, C) {
    var E = "";
    D = $id(D);
    for (i = 0; i < D.elements.length; i++) {
        var B = D.elements[i];
        if (B.type == "radio" || B.type == "checkbox") if (!B.checked) continue;
        var F = D.elements[i].name || D.elements[i].id,
        A = getElementValue(D.elements[i]);
        if (F) this.addParam(F, A)
    }
    this.submit(C);
    if (this.retDom == null) return false;
    return true
};
Ajax.prototype.loadData = function(A) {
    this.submitXML(A)
};
Ajax.prototype.submitXML = function(B, A) {
    if (B) this.xmlParam = B;
    this.submit(A)
};
Ajax.prototype.getResponseXMLDom = function() {
    return this.retDom
};
Ajax.prototype.getReturnNodeValue = function(B) {
    var A = this.getResponseXMLDom(),
    C = A ? A.selectNodes(B) : null;
    if (C && C.length > 0) return getNodeValue(C[0]);
    return null
};
Ajax.prototype.getException = function() {
    var A = "/root/exceptions";
    return this.getReturnNodeValue(A)
};
Ajax.prototype.getExceptionCode = function() {
    var A = "/root/exceptions/exception/code";
    return this.getReturnNodeValue(A)
};
Ajax.prototype.getExceptionMessage = function() {
    var A = "/root/exceptions/exception/message";
    return this.getReturnNodeValue(A)
};
Ajax.prototype.getExceptionInvalid = function() {
    var A = "/root/exceptions/invalid";
    return this.getReturnNodeValue(A)
};
Ajax.prototype.getLoginPage = function() {
    var B = "/root/exceptions/loginPage",
    A = this.getReturnNodeValue(B);
    A = A ? trim(A + "") : null;
    return A
};
var HideSubmit = Ajax;
function ServiceCaller() {
    this.service_URL_SUFFIX = ".service";
    this.ajax = null;
    this.encoding = "utf-8"
}
ServiceCaller.submitXMLTemplate = function(B, D, A) {
    var C = ["<root><data>", "\t<ajaxCall>", "\t\t<serviceName>" + xmlConversion(B) + "</serviceName>", "\t\t<operateName>" + xmlConversion(D) + "</operateName>", "\t\t<params>", A || "", "\t\t</params>", "\t\t<return></return>", "\t\t<exception></exception>", "\t</ajaxCall>", "</data></root>"];
    return C.join("\n")
};
ServiceCaller.getParamesArray = function(D, B, C) {
    var E = [];
    D = D || [];
    C = C || D.length;
    for (var A = B; A < C; A++) E.push(D[A]);
    return E
};
ServiceCaller.returnConversion = function(D) {
    var F = D.getAttribute("__category"),
    B = D.getAttribute("__type");
    if (F == "xml") return D.xml;
    else if (F == "entity") return Dataset.initEntity(D);
    else if (F == "dataset") {
        var E = new Dataset(D.tagName);
        E.init(D.childNodes);
        return E
    } else if (F == "collection") {
        var G = D.selectNodes("./entry"),
        C = [];
        for (var A = 0; A < G.length; A++) {
            var D = G[A];
            if (D.nodeType == 1) C.push(getNodeValue(D))
        }
        return C
    }
    return getNodeValue(D)
};
ServiceCaller.parameConversion = function(F) {
    var C = null,
    G = null,
    E = typeof(F),
    H = "primeval",
    D = {
        data: "",
        type: H
    };
    if (F === null || F === undefined) G = "null";
    else if (F === "") G = "empty";
    if (G) {
        D.data = "<value __isNullOrEmpty=\"" + G + "\" />";
        D.type = H;
        return D
    }
    if (E == "string" || E == "number" || E == "boolean") {
        H = "primeval";
        D.data = xmlConversion("" + F)
    } else if (E == "object" && F.xml && (F.async === false || F.async === true)) {
        H = "xml";
        D.data = F.xml
    } else if (F instanceof Entity) {
        H = "entity";
        D.data = F.toXMLString(false)
    } else {
        if (E == "object" && F.tagName && F.tagName.toUpperCase() == "FORM") F = getFormElementsValue(F);
        if (F instanceof Dataset) F = F.getEntities();
        if (F instanceof Array) {
            H = "collection";
            C = [];
            for (var B = 0; B < F.length; B++) C.push(ServiceCaller.parameConversion(F[B]).data);
            D.data = C.join("\n")
        } else {
            H = "form";
            C = [];
            for (var A in F) {
                C.push("<property>");
                C.push("<key>");
                C.push(xmlConversion(A));
                C.push("</key>");
                C.push("<value>");
                C.push(xmlConversion(F[A] + ""));
                C.push("</value>");
                C.push("</property>")
            }
            D.data = C.join("\n")
        }
    }
    D.data = (H == "collection" || H == "form") ? D.data: "<value>" + D.data + "</value>";
    D.type = H;
    return D
};
ServiceCaller.getSubmitXML = function(B) {
    var C = [];
    for (var A = 0; A < B.length; A++) {
        result = ServiceCaller.parameConversion(B[A]);
        C.push("<param __category=\"" + result.type + "\">");
        C.push(result.data);
        C.push("</param>")
    }
    return C.join("\n")
};
ServiceCaller.prototype.callBack = function() {
    var A = false,
    C = null,
    B = this.getExceptionInvalid();
    if (B === true || B === "true") {
        C = this.getLoginPage();
        if (C) A = true;
        B = AJAX_EXCEPTION_INVALID + "\n\n" + this.serviceName + "." + this.operateName
    } else B = this.getExceptionMessage() || this.getException();
    if (A) {
        if (window.contextPath && C.indexOf(window.contextPath + "/") != 0) C = window.contextPath + (C.indexOf("/") == 0 ? "": "/") + C;
        top.location = C;
        return
    }
    if (B && this.onFailure) return this.onFailure(B);
    this.onSuccess();
    return this.getReturnValue()
};
ServiceCaller.prototype._callCore = function(D, F, A, E, B) {
    this.ajax = new Ajax(D + this.service_URL_SUFFIX, A);
    this.ajax.callBack = E || this.ajax.callBack;
    var C = ServiceCaller.getSubmitXML(B);
    this.serviceName = D;
    this.operateName = F;
    this.ajax.submitXML(ServiceCaller.submitXMLTemplate(D, F, C), this.encoding)
};
function form2Object(H, E) {
    var D = {};
    D = getFormElementsValue(H);
    var B = D;
    if (E) {
        B = {};
        E = E + "";
        E = E.lastIndexOf("/") == E.length - 1 ? E: E + "/";
        if (E.indexOf("[*]") < 0) {
            for (var F in D) if (F.indexOf(E) == 0) B[F] = D[F]
        } else {
            var C = false;
            for (F in D) for (var A = 1; A < Number.MAX_VALUE; A++) {
                var G = E.replace("[*]", "[" + A + "]");
                if (F.indexOf(G) == 0) {
                    B[F] = D[F];
                    break
                }
            }
        }
    }
    return B
}
ServiceCaller.prototype.call = function(B, C, A) {
    this._callCore(B, C, false, null, ServiceCaller.getParamesArray(arguments, 2));
    return this.callBack()
};
ServiceCaller.prototype.isSuccess = function() {
    return !! !this.getException()
};
ServiceCaller.prototype.onSuccess = function() {};
ServiceCaller.prototype.onFailure = function(A) {
    alert(AJAX_EXCEPTION + "\n\n" + A)
};
ServiceCaller.prototype.callAsyn = function(C, D, B) {
    var A = this;
    this._callCore(C, D, true,
    function() {
        A.callBack()
    },
    ServiceCaller.getParamesArray(arguments, 2))
};
ServiceCaller.prototype.getResponseXMLDom = function() {
    return this.ajax.getResponseXMLDom()
};
ServiceCaller.prototype.getReturnValue = function(A, E) {
    var F = "/root/data/ajaxCall/return/value";
    E = E || this.getResponseXMLDom();
    var G = E ? E.selectNodes(F) : [],
    D = [];
    for (var B = 0; B < G.length; B++) {
        var C = G[B];
        if (C.nodeType == 1) {
            C = A === false ? C: ServiceCaller.returnConversion(C);
            D.push(C)
        }
    }
    if (D.length == 0) D = null;
    return D
};
ServiceCaller.prototype.getReturnNodeValue = function(A) {
    return this.ajax ? this.ajax.getReturnNodeValue(A) : null
};
ServiceCaller.prototype.getReturn = function() {
    var A = "/root/data/ajaxCall/return";
    return this.getReturnNodeValue(A)
};
ServiceCaller.prototype.getException = function() {
    var A = "/root/data/ajaxCall/exceptions/ecxeption";
    return this.getReturnNodeValue(A)
};
ServiceCaller.prototype.getExceptionMessage = function() {
    var A = "/root/data/ajaxCall/exceptions/exception/message";
    return this.getReturnNodeValue(A)
};
ServiceCaller.prototype.getExceptionCode = function() {
    var A = "/root/data/ajaxCall/exceptions/exception/code";
    return this.getReturnNodeValue(A)
};
ServiceCaller.prototype.getExceptionInvalid = function() {
    var A = "/root/data/ajaxCall/exceptions/invalid";
    return this.getReturnNodeValue(A)
};
ServiceCaller.prototype.getLoginPage = function() {
    var B = "/root/data/ajaxCall/exceptions/loginPage",
    A = this.getReturnNodeValue(B);
    A = A ? trim(A + "") : null;
    return A
};
var Eos_All_Message = new Array();
function isNull(B) {
    if (typeof(B) == "object") B = B.value;
    var A;
    if (B.length == 0) return true;
    for (A = 0; A < B.length; A++) if (B.charAt(A) != " ") return false;
    return true
}
function isIP(B) {
    if (typeof(B) == "object") B = B.value;
    var A = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
    if (A.test(B)) {
        if (RegExp.$1.length > 3 || RegExp.$2.length > 3 || RegExp.$3.length > 3 || RegExp.$4.length > 3) return false;
        if (RegExp.$1 > 0 && RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 255) return true
    }
    return false
}
function isInteger(A) {
    if (typeof(A) == "object") A = A.value;
    if (/^(\+|-)?\d+$/.test(A)) return true;
    else return false
}
function isPositiveInteger(A) {
    if (typeof(A) == "object") A = A.value;
    if (/^(\+)?\d+$/.test(A)) return true;
    else return false
}
function isURL(B) {
    if (typeof(B) == "object") B = B.value;
    if (B.indexOf("://") != -1) {
        var A = B.substr(0, B.indexOf("://"));
        if (A == "") return false;
        A = B.substr(B.indexOf("://") + 3, A.length);
        if (A == "") return false
    } else return false;
    return true
}
function isDecimal(D, A, C) {
    if (typeof(D) == "object") D = D.value;
    if (/^(\+|-)?\d+($|\.\d+$)/.test(D)) {
        var B = D.indexOf(".");
        if (A != null) {
            var E = D.length;
            if (B != -1) E = E - 1;
            if (A < E) return false
        }
        if (C != null) {
            if (B == -1) return true;
            if (D.length - B - 1 > C) return false
        }
        return true
    } else return false
}
function isPort(A) {
    if (typeof(A) == "object") A = A.value;
    if (/^\d+$/.test(A)) {
        if (parseInt(A, 10) > 65535 || parseInt(A, 10) <= 0) return false;
        return true
    } else return false
}
function isEmail(B) {
    if (typeof(B) == "object") B = B.value;
    var A = /^([-_A-Za-z0-9\.]+)@[A-Za-z0-9]{1}[_A-Za-z0-9\.]*\.[A-Za-z0-9]+$/;
    if (A.test(B)) return true;
    return false
}
function isLastMatch(C, B) {
    if (typeof(C) == "object") C = C.value;
    if (typeof(B) == "object") B = B.value;
    var A = new RegExp(B + "$");
    if (A.test(C)) return true;
    else return false
}
function isFirstMatch(C, B) {
    if (typeof(C) == "object") C = C.value;
    if (typeof(B) == "object") B = B.value;
    var A = new RegExp("^" + B);
    if (A.test(C)) return true;
    else return false
}
function isMatch(C, B) {
    if (typeof(C) == "object") C = C.value;
    if (typeof(B) == "object") B = B.value;
    var A = new RegExp(B);
    if (A.test(C)) return true;
    else return false
}
function isChinaMobileNo(C) {
    if (typeof(C) == "object") C = C.value;
    var A = /(^[1][35][0-9]{9}$)|(^0[1][35][0-9]{9}$)/,
    B = new RegExp(A);
    if (B.test(C)) return true;
    return false
}
function isPhoneNo(C) {
    if (typeof(C) == "object") C = C.value;
    var A = /(^([0][1-9]{2,3}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0][1-9]{2,3}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/,
    B = new RegExp(A);
    if (B.test(C)) return true;
    return false
}
function isNumberOr_Letter(A) {
    if (typeof(A) == "object") A = A.value;
    if (/[^(\w*)]/.test(A)) return false;
    return true
}
function isNumberOrLetter(A) {
    if (typeof(A) == "object") A = A.value;
    if (/[^(a-z)*(A-Z)*(0-9)*]/.test(A)) return false;
    return true
}
function isFolder(C) {
    if (typeof(C) == "object") C = C.value;
    var A = /(^[^\.])/,
    B = new RegExp(A);
    if (!B.test(C)) return false;
    A = /(^[^\\\/\?\*\"\<\>\:\|]*$)/;
    B = new RegExp(A);
    if (B.test(C)) return true;
    return false
}
function isChinaOrNumbOrLett(C) {
    if (typeof(C) == "object") C = C.value;
    var A = "^[0-9a-zA-Z\u4e00-\u9fa5]+$",
    B = new RegExp(A);
    if (B.test(C)) return true;
    return false
}
function isChinaZipcode(A) {
    if (typeof(A) == "object") A = A.value;
    if (!isInteger(A)) return false;
    if (A.length != 6) return false;
    return true
}
function isChinaIDNo(I) {
    if (typeof(I) == "object") I = I.value;
    var H = EOS_CITY_LIST,
    F = 0,
    D = "",
    G = I,
    E = G.length;
    if (!/^\d{17}(\d|X|x)$/i.test(G) && !/^\d{15}$/i.test(G)) return false;
    G = G.replace(/X|x$/i, "a");
    if (H[parseInt(G.substr(0, 2))] == null) return false;
    if (E == 18) {
        sBirthday = G.substr(6, 4) + "-" + Number(G.substr(10, 2)) + "-" + Number(G.substr(12, 2));
        var B = new Date(sBirthday.replace(/-/g, "/"));
        if (sBirthday != (B.getFullYear() + "-" + (B.getMonth() + 1) + "-" + B.getDate())) return false;
        for (var C = 17; C >= 0; C--) F += (Math.pow(2, C) % 11) * parseInt(G.charAt(17 - C), 11);
        if (F % 11 != 1) return false
    } else if (E == 15) {
        sBirthday = "19" + G.substr(6, 2) + "-" + Number(G.substr(8, 2)) + "-" + Number(G.substr(10, 2));
        var B = new Date(sBirthday.replace(/-/g, "/")),
        A = B.getFullYear().toString() + "-" + (B.getMonth() + 1) + "-" + B.getDate();
        if (sBirthday != A) return false
    }
    return true
}
function checkPeriod(B, A) {
    if (typeof(B) == "object") B = B.value;
    if (typeof(A) == "object") A = A.value;
    if (!isDate(B)) return false;
    if (!isDate(A)) return false;
    if (A >= B) return true;
    else return false
}
function matchRegExp(D, C, B) {
    if (typeof(D) == "object") D = D.value;
    if (C == null || C == "") return false;
    var A;
    if (B == null || B == "") A = new RegExp(C);
    else {
        if (/[^mig]/.test(B)) return false;
        A = new RegExp(C, B)
    }
    if (A.test(D)) return true;
    return false
}
function checkInput(obj) {
    if (processExtAttr(obj)) return true;
    var is_Null = obj.getAttr("allowNull");
    if (is_Null == "false") {
        if (isNull(obj)) {
            f_alert(obj, CHECK_MUST_INPUT);
            return false
        }
    } else if (isNull(obj)) {
        f_alert_verify_successful(obj);
        return true
    }
    var maxLength = obj.getAttr("maxLength"),
    minLength = obj.getAttr("minLength");
    if (maxLength != null) if (obj.value.length > maxLength) {
        f_alert(obj, CHECK_INPUT_LENGTH_CANNOT_MORE_THAN + maxLength);
        return false
    }
    if (minLength != null) if (obj.value.length < minLength) {
        f_alert(obj, CHECK_INPUT_LENGTH_CANNOT_LESS_THAN + minLength);
        return false
    }
    var type = obj.getAttr("type");
    if (type != null) {
        try {
            if (!eval("f_check_" + type + "(obj)")) return false
        } catch(o) {
            alert(type + ":" + CHECK_ERROROCCUR);
            return false
        }
    }
    var maxValue = obj.getAttr("maxValue"),
    minValue = obj.getAttr("minValue");
    if (! (maxValue == null && minValue == null)) {
        var inputValue, oMinValue, oMaxValue;
        if (isNaN(parseFloat(obj.value))) inputValue = obj.value;
        else inputValue = parseFloat(obj.value);
        if (maxValue != null) {
            if (isNaN(parseFloat(maxValue)) || minValue == null || isNaN(parseFloat(minValue)) || isNaN(parseFloat(obj.value))) oMaxValue = maxValue;
            else oMaxValue = parseFloat(maxValue);
            if (inputValue > oMaxValue) {
                f_alert(obj, CHECK_NOT_MORE_THAN + maxValue);
                return false
            }
        }
        if (minValue != null) {
            if (isNaN(parseFloat(minValue)) || maxValue == null || isNaN(parseFloat(maxValue)) || isNaN(parseFloat(obj.value))) oMinValue = minValue;
            else oMinValue = parseFloat(minValue);
            if (inputValue < oMinValue) {
                f_alert(obj, CHECK_NOT_LESS_THAN + minValue);
                return false
            }
        }
    }
    f_alert_verify_successful(obj);
    return true
}
function processExtAttr(C) {
    if (C.EOS_extendedAttribute != null) return false;
    var B = C.validateAttr || C.getAttribute("validateAttr");
    if (!B) return true;
    var D = new Object(),
    H = B.split(";");
    for (var A = 0; A < H.length; A++) {
        var E = H[A].split("=");
        if (E.length != 2) continue;
        var G = E[0],
        F = E[1];
        D[G] = F
    }
    C.EOS_extendedAttribute = D;
    C.getAttr = function(A) {
        return this.EOS_extendedAttribute[A]
    }
}
function f_check_number(A) {
    if (/^\d+$/.test(A.value)) return true;
    else {
        f_alert(A, CHECK_INPUT_NUMBER);
        return false
    }
}
function f_check_naturalNumber(A) {
    var B = A.value;
    if (B.length > 1 && B.charAt(0) == "0") {
        f_alert(A, CHECK_INPUT_NATURALNUMBER);
        return false
    }
    if (/^[0-9]+$/.test(B)) return true;
    else {
        f_alert(A, CHECK_INPUT_NATURALNUMBER);
        return false
    }
}
function f_check_integer(A) {
    if (isInteger(A)) return true;
    else {
        f_alert(A, CHECK_IUPUT_INT);
        return false
    }
}
function f_check_float(A) {
    if (/^(\+|-)?\d+($|\.\d+$)/.test(A.value)) return true;
    else {
        f_alert(A, CHECK_INPUT_FLOAT);
        return false
    }
}
function f_check_zh(A) {
    if (/^[\u4e00-\u9fa5]+$/.test(A.value)) return true;
    f_alert(A, CHECK_INPUT_ZH);
    return false
}
function f_check_letter(A) {
    if (/^[A-Za-z]+$/.test(A.value)) return true;
    f_alert(A, CHECK_INPUT_LETTER);
    return false
}
function f_check_IP(A) {
    if (isIP(A.value)) return true;
    f_alert(A, CHECK_INVALID_IP);
    return false
}
function f_check_port(A) {
    if (isPort(A.value)) return true;
    f_alert(A, CHECK_INVALID_PORT);
    return false
}
function f_check_URL(A) {
    if (isURL(A.value)) return true;
    f_alert(A, CHECK_INVALID_WEBSITE);
    return false
}
function f_check_email(A) {
    if (isEmail(A.value)) return true;
    f_alert(A, CHECK_INVALID_EMAIL);
    return false
}
function f_check_folder(A) {
    if (isFolder(A.value)) return true;
    f_alert(A, CHECK_INVALID_FOLDER);
    return false
}
function f_check_chinaMobile(A) {
    if (isChinaMobileNo(A.value)) return true;
    f_alert(A, CHECK_INVALD_HANDPHONE);
    return false
}
function f_check_phone(A) {
    if (isPhoneNo(A.value)) return true;
    f_alert(A, CHECK_INVALID_PHONE);
    return false
}
function f_check_chinaZipcode(A) {
    if (!/^\d+$/.test(A.value)) {
        f_alert(A, CHECK_POSTALCODE_MUST_NUMBER);
        return false
    }
    if (A.value.length != 6) {
        f_alert(A, CHECK_INVAILID_POSTALCODE_LENGTH);
        return false
    }
    return true
}
function f_check_chinaIDNo(A) {
    if (isChinaIDNo(A)) return true;
    else {
        f_alert(A, CHECK_INVALID_ID);
        return false
    }
}
function f_check_formatStr(A) {
    var B = A.getAttr("regExpr");
    if (matchRegExp(A, B)) return true;
    f_alert(A, CHECK_INVALID_EXP);
    return false
}
function f_check_double(B) {
    var A = B.getAttr("totalDigit"),
    D = B.getAttr("fracDigit");
    if (isDecimal(B, A, D)) return true;
    var C = CHECK_INPUT_NUMBER;
    if (A != null) C = C + CHECK_LEGNT_NOT_THAN + A + CHECK_WEI;
    if (D != null) C = C + CHECK_FRACTION_LENGTH_NOT_THAN + D + CHECK_WEI;
    f_alert(B, C);
    return false
}
function f_alert(A, C) {
    try {
        checkTabPage(A)
    } catch(D) {}
    var B;
    if (A.getAttr) B = A.getAttr("message");
    if (B != null) C = B;
    f_alert_verify_failure(A, C);
    f_alert_resetMessagePos()
}
function checkTabPage(B) {
    var D = B.parentNode;
    while (D && D.getAttribute) {
        var C = D.eos_tabpage_id || D.getAttribute("eos_tabpage_id");
        if (C) {
            var A = $id(C);
            if (A) {
                try {
                    A.getTabPane().selectTab(A)
                } catch(E) {}
            }
            break
        }
        D = D.parentNode
    }
}
function checkForm(E) {
    var D = true,
    C = null,
    A;
    for (A = E.elements.length - 1; A >= 0; A--) {
        if ((E.elements[A].validateAttr || E.elements[A].getAttribute("validateAttr")) + "" == "undefined") continue;
        if (E.elements[A].disabled == true) continue;
        if (E.elements[A].name == null || E.elements[A].name == "") if (! (E.elements[A].id != null && E.elements[A].id.indexOf("_input") != -1)) continue;
        if (checkInput(E.elements[A]) == false) {
            C = E.elements[A];
            D = false
        }
    }
    if (D) return D;
    var B = C.type;
    if (B == "text" || B == "TEXT" || B == "textarea" || B == "TEXTAREA") C.select();
    return D
}
function f_alert_getPosition(B) {
    var G = B.id;
    if (G != null) {
        var E = G.indexOf("_input");
        if (E != -1) B = $id(G.substr(0, E) + "_container") || B
    }
    var A = B.offsetTop,
    C = B.offsetLeft,
    D = B,
    F = true;
    while (D = D.offsetParent) {
        if (D.tagName == "BODY") continue;
        A += D.offsetTop - D.scrollTop;
        C += D.offsetLeft - D.scrollLeft;
        if (D.style.position.toLowerCase() == "absolute") F = false
    }
    C = C + B.offsetWidth + 10;
    return {
        left: C,
        top: A
    }
}
function f_alert_create_Message_plane(A, D) {
    var B = document.createElement("DIV");
    B.className = "alert_message";
    var C = f_alert_getPosition(A);
    B.style.top = C.top;
    B.style.left = C.left;
    B.style.height = isFF ? A.offsetHeight - 4 : A.offsetHeight;
    B.style.whiteSpace = "nowrap";
    B.onclick = function() {
        this.hidden()
    };
    B.show = function(A) {
        this.innerHTML = "&nbsp;" + A + "&nbsp;";
        if (B.isLoading) {
            if (B.timeout) clearTimeout(B.timeout);
            B.timeout = setTimeout(function() {
                return _showMessage.apply(B, [B, A])
            },
            10)
        } else if (!this.isShow) {
            document.body.appendChild(this);
            this.isShow = true;
            this.isLoading = true;
            setOpacity(this, 0);
            fx_fadeIn(this,
            function() {
                return loadingFinished.apply(B, [B])
            },
            500)
        }
    };
    B.hidden = function() {
        if (B.isLoading) {
            if (B.timeout) clearTimeout(B.timeout);
            B.timeout = setTimeout(function() {
                return _hiddenMessage.apply(B, [B])
            },
            10)
        } else {
            this.isLoading = true;
            this.isShow = false;
            fx_fadeOut(this,
            function() {
                try {
                    document.body.removeChild(B)
                } catch(A) {}
                return loadingFinished.apply(B, [B])
            },
            400)
        }
    };
    B.setPos = function(A) {
        this.style.top = A.top;
        this.style.left = A.left
    };
    document.body.appendChild(B);
    B.show(D);
    A.Eos_Message = B;
    Eos_All_Message[Eos_All_Message.length] = A
}
function loadingFinished(A) {
    this.isLoading = false
}
function _hiddenMessage(A) {
    if (A.isLoading) A.timeout = setTimeout(function() {
        return _hiddenMessage.apply(A, [A])
    },
    10);
    else A.hidden()
}
function _showMessage(A, B) {
    if (A.isLoading) A.timeout = setTimeout(function() {
        return _showMessage.apply(A, [A, B])
    },
    10);
    else A.show(B)
}
function f_alert_show_message(A, B) {
    if (A.Eos_Message != null) {
        f_alert_resetMessagePos();
        A.Eos_Message.show(B)
    } else f_alert_create_Message_plane(A, B)
}
function f_alert_hidden_message(A) {
    if (A.Eos_Message != null) A.Eos_Message.hidden()
}
function f_alert_verify_failure(A, B) {
    removeClass(A, "verify_successful");
    addClass(A, "verify_failure");
    f_alert_show_message(A, B)
}
function f_alert_verify_successful(A) {
    removeClass(A, "verify_failure");
    addClass(A, "verify_successful");
    f_alert_hidden_message(A)
}
function f_alert_resetMessagePos() {
    for (var A = 0; A < Eos_All_Message.length; A++) {
        var B = f_alert_getPosition(Eos_All_Message[A]);
        Eos_All_Message[A].Eos_Message.setPos(B)
    }
}
function f_alert_hidden_all_message() {
    f_alert_resetMessagePos();
    for (var A = 0; A < Eos_All_Message.length; A++) Eos_All_Message[A].Eos_Message.hidden()
}
eventManager.add(window, "resize", f_alert_resetMessagePos);
function regCheckEvent(D, C) {
    if (D == null) return;
    C = C || "blur";
    var A;
    for (A = D.elements.length - 1; A >= 0; A--) {
        if ((D.elements[A].validateAttr || D.elements[A].getAttribute("validateAttr")) + "" == "undefined") continue;
        var B = D.elements[A];
        eventManager.add(B, C,
        function(B) {
            B = eventManager.getEvent() || B;
            var A = B.srcElement || B.target;
            checkInput(A)
        })
    }
}
function checkOnblur(A) {
    regCheckEvent(A)
}
function checkOnkeypress(A) {
    regCheckEvent(A, "keyup");
    regCheckEvent(A, "focus")
}
var EOSMaskCache = {};
function Mask(C, B) {
    var A = this;
    this.id = C;
    this.inited = false;
    EOSMaskCache[this.id] = this;
    this.isFrameSetPage = false;
    this.win = B || window;
    this.init = function() {
        this.frameList = this.win.document.getElementsByTagName("frame");
        if (this.frameList.length > 0) {
            this.isFrameSetPage = true;
            this.inited = true;
            return this.inited
        }
        this.container = this.container || this.win.document.body;
        if (!this.inited && this.win.document.body) {
            this.core = this.win.document.createElement("div");
            this.core.id = this.id + "_div";
            this.core.onmousedown = function() {
                eventManager.stopBubble()
            };
            if (isIE) {
                var A = "<iframe style=\"filter:Alpha(Opacity=0);position:absolute;z-index:-1;width:expression(this.parentNode.offsetWidth);height:expression(this.parentNode.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>";
                A += "<div style=\"width:expression(this.parentNode.offsetWidth);height:expression(this.parentNode.offsetHeight);z-index:1\" class=\"eos-page-mask\"></div>";
                this.core.innerHTML = A
            }
            this.core.className = "eos-page-mask";
            this.core.style.display = "none";
            this.win.document.body.appendChild(this.core);
            this.inited = true
        }
        return this.inited
    };
    this.setContainer = function(A) {
        if (this.container && this.container.setAttribute) this.container.setAttribute("_mask", null);
        this.container = A;
        if (this.container && this.container.setAttribute) this.container.setAttribute("_mask", this.id)
    };
    this.isHidden = function() {
        return this.core.style.display == "none"
    };
    this.autoResize = function() {
        if (this.isHidden()) return;
        var E = parseInt(this.container.style.marginTop),
        G = parseInt(this.container.style.marginLeft);
        E = E || 0;
        G = G || 0;
        var C = 0,
        F = 0,
        A, B;
        if (this.container == this.win.document.body) {
            A = this.win.screen.width;
            B = this.win.screen.height
        } else {
            C = getElementXY(this.container);
            F = C[0];
            C = C[1];
            A = this.container.offsetWidth;
            B = this.container.offsetHeight
        }
        A += E;
        B += G;
        var D = this.core;
        D.style.top = C - E + "px";
        D.style.left = F - G + "px";
        D.style.width = A + "px";
        D.style.height = B + "px";
        if (this.container == this.win.document.body) {
            this.container.scrollTop = 0;
            this.container.scrollLeft = 0
        }
    },
    this.show = function(B) {
        if (this.isFrameSetPage) {
            for (var A = 0; A < this.frameList.length; A++) {
                try {
                    if (!this.frameList[A].contentWindow.eos_pageMask) this.frameList[A].contentWindow.eos_pageMask = new Mask("eos_pageMask" + "_f_" + A, this.frameList[A].contentWindow);
                    this.frameList[A].contentWindow.eos_pageMask.show()
                } catch(D) {}
            }
            return
        }
        if (!this.init() || !this.isHidden()) return;
        if (B) this.setContainer(B);
        this.oldOverFlowX = this.container.style.overflowX;
        this.oldOverFlowY = this.container.style.overflowY;
        this.container.style.overflowX = "hidden";
        this.container.style.overflowY = "hidden";
        var C = getMaxZindex(this.container.ownerDocument || document);
        C++;
        this.container.style.zIndex = C;
        this.core.style.display = "block";
        C++;
        this.core.style.zIndex = C;
        this.core.focus();
        this.autoResize()
    };
    this.hide = function() {
        if (this.isFrameSetPage) {
            for (var A = 0; A < this.frameList.length; A++) {
                try {
                    if (!this.frameList[A].contentWindow.eos_pageMask) this.frameList[A].contentWindow.eos_pageMask = new Mask("frame_" + this.frameList[A].contentWindow.location, this.frameList[A].contentWindow);
                    this.frameList[A].contentWindow.eos_pageMask.hide()
                } catch(B) {}
            }
            return
        }
        if (this.isHidden() || !this.container) return;
        this.container.style.overflowX = this.oldOverFlowX;
        this.container.style.overflowY = this.oldOverFlowY;
        this.core.style.display = "none"
    };
    this.hideAfterTime = function(B) {
        this.win.setTimeout(function() {
            A.hide()
        },
        B || 100)
    };
    if (window.addEventListener) window.addEventListener("load",
    function() {
        A.init()
    },
    false);
    else window.attachEvent("onload",
    function() {
        A.init()
    })
}
window.eos_pageMask = new Mask("eos_pageMask");
function maskElement(E, G) {
    G = G || window;
    E = $id(E);
    E.maskId = "_eos_" + E.id + "_mask";
    var C = $id(E.maskId, G.document),
    B = E.offsetWidth,
    D = E.offsetHeight;
    if (!C) {
        C = $create("div", G.document);
        C.style.display = "none";
        C.id = E.maskId;
        if (document != G.document) moveCss(document, G.document);
        G.document.body.appendChild(C)
    }
    C.style.position = "absolute";
    C.style.width = B + "px";
    C.style.height = D + "px";
    var A = getElementXY(E);
    setElementXY(C, A);
    C.className = "eos-page-mask";
    C.style.display = "block";
    G._bakscroll = G.onscroll;
    var F = getMaxZindex(G.document);
    C.onmousedown = function() {
        eventManager.stopBubble()
    };
    C.onkeydown = function() {
        eventManager.stopBubble()
    };
    C.onkeypress = function() {
        eventManager.stopBubble()
    };
    C.style.zIndex = F + 2;
    C.pActiveElement = document.activeElement;
    try {
        C.focus()
    } catch(H) {}
    try {
        C.getElementsByTagName("a")[0].focus()
    } catch(H) {}
}
function unMaskElement(B, C) {
    C = C || window;
    B = $id(B);
    var A = $id(B.maskId);
    if (A) A.style.display = "none"
}
function maskTop() {
    maskWindow(top)
}
function maskWindow(F) {
    F = F || window;
    hideSelect(F);
    if (isFrameSet(F)) {
        var D = F.document.getElementsByTagName("frame");
        for (var A = 0; A < D.length; A++) {
            try {
                maskWindow(D[A].contentWindow)
            } catch(G) {}
        }
    } else {
        var B = $id("_eos_page_mask", F.document);
        if (!B) {
            B = $create("div", F.document);
            B.style.display = "none";
            B.id = "_eos_page_mask";
            B.className = "eos-page-mask";
            if (document != F.document) moveCss(document, F.document);
            F.document.body.appendChild(B)
        }
        function C() {
            var G = parseInt(F.document.body.style.marginTop),
            J = parseInt(F.document.body.style.marginLeft);
            G = G || 0;
            J = J || 0;
            var E = 0,
            H = 0,
            A, D, C = (Math.max(F.document.documentElement.scrollLeft, F.document.body.scrollLeft) || 0),
            I = (Math.max(F.document.documentElement.scrollTop, F.document.body.scrollTop) || 0);
            A = Math.max(F.document.body.scrollWidth, (F.document.body.clientWidth + C));
            D = Math.max(F.document.body.scrollHeight, (F.document.body.clientHeight + I));
            A += G;
            D += J;
            B.style.top = E - G + "px";
            B.style.left = H - J + "px";
            B.style.width = A + "px";
            B.style.height = D + "px"
        }
        B.className = "eos-page-mask";
        B.style.display = "block";
        C();
        F._bakresize = F.onresize;
        window.onresize = function() {
            C()
        };
        var E = getMaxZindex(F.document);
        B.onmousedown = function() {
            eventManager.stopBubble()
        };
        B.onkeydown = function() {
            eventManager.stopBubble()
        };
        B.onkeypress = function() {
            eventManager.stopBubble()
        };
        B.style.zIndex = E + 2;
        B.pActiveElement = document.activeElement;
        try {
            B.focus()
        } catch(G) {}
        try {
            B.getElementsByTagName("a")[0].focus()
        } catch(G) {}
    }
}
function hideSelect(C) {
    if (C) {
        if (!C["mask-select-style"]) C["mask-select-style"] = createStyle("select", C.document, true);
        if (C["mask-select-style"]) C["mask-select-style"].style.visibility = "hidden";
        var B = C.document.getElementsByTagName("frame");
        for (var A = 0; A < B.length; A++) {
            try {
                hideSelect(B[A].contentWindow)
            } catch(D) {}
        }
        var E = C.document.getElementsByTagName("iframe");
        for (A = 0; A < E.length; A++) {
            try {
                hideSelect(E[A].contentWindow)
            } catch(D) {}
        }
    }
}
function showSelect(C) {
    if (C) {
        if (!C["mask-select-style"]) C["mask-select-style"] = createStyle("select", C.document, true);
        if (C["mask-select-style"]) C["mask-select-style"].style.visibility = "visible";
        var B = C.document.getElementsByTagName("frame");
        for (var A = 0; A < B.length; A++) {
            try {
                showSelect(B[A].contentWindow)
            } catch(D) {}
        }
        var E = C.document.getElementsByTagName("iframe");
        for (A = 0; A < E.length; A++) {
            try {
                showSelect(E[A].contentWindow)
            } catch(D) {}
        }
    }
}
function unMaskWindow(D) {
    D = D || window;
    showSelect(D);
    if (isFrameSet(D)) {
        var C = D.document.getElementsByTagName("frame");
        for (var A = 0; A < C.length; A++) {
            try {
                unMaskWindow(C[A].contentWindow)
            } catch(E) {}
        }
    } else {
        var B = $id("_eos_page_mask", D.document);
        if (B) {
            try {
                B.style.display = "none";
                B.pActiveElement && B.pActiveElement.focus();
                B.pActiveElement = null
            } catch(E) {}
        }
        D.onresize = D._bakresize
    }
}
function unMaskTop() {
    unMaskWindow(top)
}
function isFrameSet(A) {
    return A.document.getElementsByTagName("frameset").length != 0
}
var EOSProgressCache = {},
ProgressBar = function(D) {
    D = D || "eos_pageProgressBar";
    this.winWidth = 160;
    this.winHeight = 60;
    this.id = D;
    var A = "__progressbar_" + D + "_title";
    EOSProgressCache[D] = this;
    var B = $id(D);
    if (!B) {
        B = $create("div");
        B.id = D;
        B.className = "eos-popwin";
        B.style.width = "100px";
        var C = ["<div  class=\"eos-popwin-body\">", "<div style=\"margin:5px;margin-top:15px\" ><div class=\"eos-progress-icon\"></div>", "<span id=\"__progressbar_" + D + "_title\" ></span></div>", "</div>"].join("\n");
        B.innerHTML = C;
        document.body.appendChild(B)
    }
    this.isHidden = true;
    this.container = B;
    this.titleBar = $id("__progressbar_" + D + "_title");
    this.show = function(B, D, C) {
        B = B || "Please Waiting ...";
        this.titleBar.innerHTML = B;
        var A;
        this.container.style.width = this.winWidth + "px";
        this.container.style.height = this.winHeight + "px";
        this.container.style.display = "";
        if (D && C) {
            this.container.style.left = D + "px";
            this.container.style.top = C + "px"
        } else if (typeof(D) == "object" && D.offsetLeft) {
            A = D;
            D = A.offsetLeft + (A.offsetWidth - this.winWidth) / 2;
            C = A.offsetTop + (A.offsetHeight - this.winHeight) / 2;
            this.container.style.left = D + "px";
            this.container.style.top = C + "px"
        } else moveDivToCenter(this.container);
        try {
            this.container.focus()
        } catch(E) {}
        this.isHidden = false
    };
    this.hide = function() {
        if (this.container) this.container.style.display = "none";
        this.isHidden = true
    }
};
function showProgressBar(E, B, D, C) {
    var A = EOSProgressCache[E] || null;
    if (!A) A = new ProgressBar(E);
    A.show(B, D, C);
    return A
}
function hideProgressBar(B) {
    B = B || "eos_pageProgressBar";
    var A = EOSProgressCache[B] || null;
    if (A) A.hide();
    return A
}
function hideAllProgressBar() {
    for (var A in EOSProgressCache) EOSProgressCache[A].hide()
}
var EOStopWin = EOStopWin || _get_top_window() || window;
EOStopWin["_eos_modal_dialog"] = EOStopWin["_eos_modal_dialog"] || [];
function getModalDialogId() {
    return "_eos_modal_dialog" + Math.random() + EOStopWin["_eos_modal_dialog"].length
}
var getCurrentModalArguments = function() {
    var A = EOStopWin["_eos_modal_dialog"].length;
    if (A > 0) return EOStopWin["_eos_modal_dialog"][A - 1];
    else return null
},
getModalDialog = function(D) {
    var A = EOStopWin["_eos_modal_dialog"].length;
    for (var B = 0; B < A; B++) {
        var C = EOStopWin["_eos_modal_dialog"][B];
        if (C.id == D) return C
    }
    return null
},
lockLatestModelDialog = function() {
    var A = getCurrentModalArguments();
    if (A) {
        A.showMask();
        hideSelect(A.win)
    } else maskTop()
},
unLockLatestModelDialog = function() {
    var A = getCurrentModalArguments();
    if (A) {
        A.hideMask();
        showSelect(A.win)
    } else unMaskTop()
},
hideModelDialog = function(E, A) {
    try {
        var D = getModalDialog(E);
        EOStopWin["_eos_modal_dialog"].pop();
        if (!D) return;
        var C = D.frameWin || D.iframe.contentWindow;
        top.currStack = C.bfCurrStack || [];
        try {
            var B = C["returnValue"]
        } catch(F) {}
        if (D.container) D.container.style.display = "none";
        if (D.win) unLockLatestModelDialog()
    } catch(F) {}
    if (!A) {
        try {
            if (D["callBack"]) D["callBack"](B)
        } catch(F) {}
    }
    try {
        D.iframe.src = "";
        D.ddDiv.style.display = "none";
        ReleaseElemEvents(D.container);
        ReleaseElemEvents(D.ddDiv);
        D.ddDiv.parentNode.removeChild(D.ddDiv);
        D.container.parentNode.removeChild(D.container)
    } catch(F) {}
},
resizeModelDialog = function(F, C, D) {
    var E = getModalDialog(F);
    E.container.style.width = C + "px";
    E.container.style.height = D + "px";
    E.maskDiv.style.width = C + "px";
    E.maskDiv.style.height = D + "px";
    var B = $id(E.id + "__model_dialog_body", E.win.document);
    if (D - 27 > 0) B.style.height = D - 27 - 7 + "px";
    var A = $id(E.id + "__model_dialog_frame", E.win.document);
    if (A) {
        if (D - 28 >= 0) A.style.height = D - 32 + "px";
        A.style.width = C - 14 + "px"
    }
    try {
        E.ddDiv.style.width = C + "px";
        E.ddDiv.style.height = D + "px"
    } catch(G) {}
},
moveModelDialog = function(D, B, A) {
    var C = getModalDialog(D);
    C.container.style.left = B + "px";
    C.container.style.top = A + "px";
    try {
        C.ddDiv.style.left = B + "px";
        C.ddDiv.style.top = A + "px"
    } catch(E) {}
},
moveModelDialogToCenter = function(B) {
    var A = getModalDialog(B);
    A.container.style.top = ((EOStopWin.document.body.clientHeight - A.container.offsetHeight) / 2 + EOStopWin.document.body.scrollTop) + "px";
    A.container.style.left = ((EOStopWin.document.body.clientWidth - A.container.offsetWidth) / 2 + EOStopWin.document.body.scrollLeft) + "px"
};
function ModalArguments(A) {
    this.id = A;
    this.win = null;
    this.parentWin = null;
    this.callBack = null;
    this.Argument = null;
    this.iframe = null;
    this.container = null;
    this.mask = null;
    this.frameWin = null;
    this.maskDiv = null;
    this.ddDiv = null
}
ModalArguments.prototype.init = function() {};
ModalArguments.prototype.showMask = function() {
    this.maskDiv.style.display = ""
};
ModalArguments.prototype.hideMask = function() {
    this.maskDiv.style.display = "none"
};
function showModalCenter(H, F, A, E, G, B) {
    var D = null,
    C = null;
    return showModal(H, F, A, E, G, D, C, B)
}
function showModal(G, D, H, E, L, K, J, B) {
    E = E ? parseInt(E, 10) : 300;
    L = L ? parseInt(L, 10) : 200;
    E = E + 14;
    L = L + 14;
    var I = _get_top_window(),
    A = I.document.getElementsByTagName("BODY")[0];
    if (K == null || K == "") {
        K = ((I.document.body.clientWidth - E) / 2 + I.document.body.scrollLeft);
        if (K < 0) K = 0
    } else K = parseInt(K, 10);
    if (J == null || J == "") {
        J = ((EOStopWin.document.body.clientHeight - L) / 2 + I.document.body.scrollTop);
        if (J < 0) J = 0
    } else J = parseInt(J, 10);
    var F = I.document.body.scrollTop,
    M = getModalDialogId(),
    C = new ModalArguments(M);
    C.parentWin = window;
    C.callBack = H;
    C.Argument = D;
    C.win = I;
    C.init();
    try {
        lockLatestModelDialog()
    } catch(N) {}
    EOStopWin["_eos_modal_dialog"].push(C);
    __showModal(M, G, C, E, L, K, J, B);
    I.document.body.scrollTop = F
}
function __showModal(Z, G, C, O, Y, L, V, B) {
    var J = $create("div", C.win.document);
    J.id = Z + "__model_dialog_container";
    J.className = "eos-popwin";
    J.onfocus = function() {
        J.blur()
    };
    var F = "";
    if (isIE) F = "<iframe style=\"z-index:-1;filter:Alpha(Opacity=0);position:absolute;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>";
    var A = navigator.userAgent.toLowerCase().indexOf("msie 6") != -1,
    M = "";
    if (A) M = "style=\"filter:alpha(opacity=50)\"";
    var S = [F, "<div style=\"width:100%;height:100%\">", "<div id=\"" + Z + "__model_dialog_mask\" class=\"eos-div-mask\" style=\"position:absolute;top:0px;left:0px;display:none;\"></div>", "<TABLE  cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" height=\"100%\" class=\"eos-dialog\"><TR><TD ", M, " class=\"left-top-conner\"><div class=\"ieBlank\"></div></TD><TD  ", M, " class=\"top\"></TD><TD  ", M, " class=\"right-top-conner\"><div class=\"ieBlank\"></div></TD></TR><TR><TD  ", M, " class=\"left\"><div class=\"ieBlank\"></div></TD><TD style=\"background-color: #eaf0f2;\">", "<div style=\"width:100%;cursor:move\" id=\"" + Z + "__model_dialog_head\" class=\"eos-popwin-head\" ><a id=\"" + Z + "__model_dialog_focus\" href=\"#f\"></a>", "<div class=\"eos-popwin-head-icon\">&#160;</div>", "<div class=\"eos-popwin-head-title\">" + (B || " Dialog Window") + "</div>", "<div id=\"popupControls\" class=\"eos-popwin-head-button\"  >", "<a href=\"javascript:void(0);\" id=\"" + Z + "__model_dialog_closebutton\" onmousedown=\"window.returnValue=undefined;hideModelDialog('" + Z + "',true)\">&#160;</a>", "</div>", "</div>", "<div id=\"" + Z + "__model_dialog_body\" class=\"eos-popwin-body\">", "</div>", "</TD><TD  ", M, " class=\"right\" id=\"", Z, "_right\"><div class=\"ieBlank\"></div></TD></TR><TR><TD  ", M, " class=\"left-bottom-conner\"></TD><TD  ", M, " class=\"bottom\" id=\"", Z, "_bottom\"></TD><TD  ", M, " class=\"right-bottom-conner\" id=\"", Z, "_rightBottom\"></TD></TR></TABLE>", "</div>"].join(""),
    P = ["<iframe type=\"_eos_modal_dialog\" eosid=\"" + Z + "\" name=\"" + Z + "__model_dialog_frame\" id=\"" + Z + "__model_dialog_frame\" scrolling=\"auto\" ", "  HSPACE =\"0\" VSPACE=\"0\" MARGINHEIGHT=\"0\" MARGINWIDTH=\"0\" ", " class=\"eos-popwin-body-iframe\"  border =\"0\"   ", "frameborder=\"0\" allowtransparency=\"true\" ></iframe>"].join("\n"),
    D = getMaxZindex(C.win.document);
    J.style.width = O + "px";
    J.style.height = Y + "px";
    J.style.left = L + "px";
    J.style.top = V + "px";
    J.style.overflow = "hidden";
    J.innerHTML = S;
    C.win.document.body.appendChild(J);
    var H = getMaxZindex(C.win.document || document);
    H++;
    J.tabIndex = H;
    J.onmousedown = function() {
        try {
            this.focus()
        } catch(A) {}
        eventManager.stopEvent()
    };
    J.onkeypress = function() {
        eventManager.stopEvent()
    };
    J.onkeydown = function(B) {
        var A = (eventManager.getEvent(C.win) || B).keyCode;
        if (A == 27) {
            eventManager.stopEvent();
            window.returnValue = undefined;
            hideModelDialog(Z)
        }
    };
    var a = $id(Z + "__model_dialog_mask", C.win.document);
    a.style.width = O + "px";
    a.style.height = Y + "px";
    C.maskDiv = a;
    a.style.zIndex = D + 2;
    J.style.zIndex = D + 1;
    var N = $id(Z + "__model_dialog_body", C.win.document),
    R = N.offsetWidth;
    N.style.height = (Y - 27) > 0 ? (Y - 27 - 7) : 0 + "px";
    N.innerHTML = P;
    var T = $id(Z + "__model_dialog_frame", C.win.document);
    if (T) {
        T.src = G;
        T.style.height = (Y - 28) > 0 ? (Y - 28 - 7) : 0 + "px";
        T.style.width = R;
        T.style.zIndex = D + 1
    }
    var X = $id(Z + "__model_dialog_head", C.win.document);
    X.onmousedown = function() {
        try {
            J.focus()
        } catch(A) {}
        eventManager.stopEvent()
    };
    var Q = $create("div", C.win.document);
    Q.style.position = "absolute";
    Q.style.width = O + "px";
    Q.style.height = Y + "px";
    Q.style.left = L + "px";
    Q.style.top = (V) + "px";
    Q.style.cursor = "move";
    Q.onmousedown = function() {
        eventManager.stopEvent()
    };
    Q.style.zIndex = D;
    C.win.document.body.appendChild(Q);
    var U = C.win.fx_DD || fx_DD;
    if (U) U(Q, {
        handle: X,
        limit: {
            x: [0, C.win.offsetWidth],
            y: [0, C.win.offsetHeight]
        },
        onStart: function() {
            Q.style.height = J.style.height;
            Q.style.zIndex = (Q.style.zIndex * 1) + 2;
            C.win.document.body.onselectstart = function() {
                return false
            }
        },
        onDrag: function() {
            Q.style.border = "1px dotted #000093";
            J.style.display = "none"
        },
        onComplete: function() {
            this.isdraging = false;
            Q.style.height = "24px";
            J.style.left = Q.style.left;
            J.style.top = Q.style.top;
            Q.style.zIndex = Q.style.zIndex - 2;
            Q.style.border = "";
            J.style.display = "";
            C.win.document.body.onselectstart = null
        }
    });
    C.iframe = T;
    C.container = J;
    C.ddDiv = Q;
    J.style.display = "none";
    var W = $id(Z + "__model_dialog_focus", C.win.document),
    K = $id(Z + "_right", C.win.document),
    E = $id(Z + "_bottom", C.win.document),
    I = $id(Z + "rightBottom", C.win.document);
    U(J, {
        handle: E,
        modifiers: true,
        onDrag: function() {
            var B = this.mouse.start["y"],
            A = this.mouse.now["y"];
            if (A - B + this.value.now["y"] >= 27) this.element.style.height = A - B + this.value.now["y"];
            resizeModelDialog(Z, parseInt(J.style.width), parseInt(J.style.height));
            C.iframe.style.display = "none"
        },
        onComplete: function() {
            C.iframe.style.display = ""
        },
        onStart: function() {
            this.value.now["y"] = this.element.getStyle("height").toInt();
            C.iframe.style.display = "none"
        }
    });
    U(J, {
        handle: K,
        modifiers: true,
        onDrag: function() {
            var B = this.mouse.start["x"],
            A = this.mouse.now["x"];
            if (A - B + this.value.now["x"] >= 150) this.element.style.width = A - B + this.value.now["x"];
            resizeModelDialog(Z, parseInt(J.style.width), parseInt(J.style.height));
            C.iframe.style.display = "none"
        },
        onComplete: function() {
            C.iframe.style.display = ""
        },
        onStart: function() {
            this.value.now["x"] = this.element.getStyle("width").toInt();
            C.iframe.style.display = "none"
        }
    });
    U(J, {
        handle: I,
        modifiers: true,
        onStart: function() {
            this.value.now["x"] = this.element.getStyle("width").toInt();
            this.value.now["y"] = this.element.getStyle("height").toInt();
            C.iframe.style.display = "none"
        },
        onDrag: function() {
            var E = this.mouse.start["y"],
            A = this.mouse.now["y"];
            if (A - E + this.value.now["y"] >= 27) J.style.height = A - E + this.value.now["y"];
            var D = this.mouse.start["x"],
            B = this.mouse.now["x"];
            if (B - D + this.value.now["x"] >= 150) J.style.width = B - D + this.value.now["x"];
            resizeModelDialog(Z, parseInt(J.style.width), parseInt(J.style.height));
            C.iframe.style.display = "none"
        },
        onComplete: function() {
            C.iframe.style.display = ""
        }
    });
    fx_fadeIn(J,
    function() {},
    550)
}
function openNewWindow(J, H, I, G, F, E, B, C, D, A) {
    E = (E || E == "yes") ? "yes": "no";
    B = (B || B == "yes") ? "yes": "no";
    C = (C || C == "yes") ? "yes": "no";
    D = (D || D == "yes") ? "yes": "no";
    A = (A || A == "yes") ? "yes": "no";
    newwin = window.open(J, "popupnav", "width=" + H + "," + "height=" + I + "," + "left=" + G + "," + "top=" + F + "," + "status=" + E + "," + "menubar=" + B + "," + "location=" + C + "," + "toolbar=" + D + "," + "scrollbars=" + A);
    newwin.focus();
    return newwin
}
var dialogArguments;
if (window.frameElement) {
    var windowType, eosid;
    if (isIE) {
        windowType = window.frameElement.type;
        eosid = window.frameElement.eosid
    } else {
        windowType = window.frameElement.getAttribute("type");
        eosid = window.frameElement.getAttribute("eosid")
    }
    if (windowType == "_eos_modal_dialog") {
        var modal = getModalDialog(eosid);
        window["dialogArguments"] = modal["Argument"];
        window["returnValue"] = null;
        window["parentWindow"] = modal.parentWin;
        window["_eos_dialog_id"] = modal.id;
        modal.frameWin = window;
        initDialogWindowEvent();
        window.bfCurrStack = top.currStack;
        top.currStack = [];
        eventManager.add(window, "unload",
        function() {
            top.currStack = window.bfCurrStack || []
        })
    }
}
function initDialogWindowEvent() {
    var A = window.close;
    window.close = function() {
        hideModelDialog(window["_eos_dialog_id"])
    };
    window.resize = function(A, B) {
        resizeModelDialog(window["_eos_dialog_id"], A, B)
    };
    var B = window.moveTo;
    window.moveTo = function(B, A) {
        moveModelDialog(window["_eos_dialog_id"], B, A)
    };
    window.moveCenter = function() {
        moveModelDialogToCenter(window["_eos_dialog_id"])
    }
}
function loadParent(A) {
    if (window.parentWindow) if (A) window.parentWindow.location = A;
    else window.parentWindow.location = window.parentWindow.location
}
function hideAllSelect(B) {
    if (isIE && B) {
        var D = B.getElementsByTagName("select");
        for (var A = 0; A < D.length; A++) {
            D[A].back_display = D[A].style.display;
            D[A].style.display = "none"
        }
        var C = getFrameDocuments(B);
        for (A = 0; A < C.length; A++) hideAllSelect(C[A])
    }
}
function showAllSelect(B) {
    if (isIE && B) {
        var D = B.getElementsByTagName("select");
        for (var A = 0; A < D.length; A++) D[A].style.display = D[A].back_display;
        var C = getFrameDocuments(B);
        for (A = 0; A < C.length; A++) showAllSelect(C[A])
    }
}
function getFrameDocuments(B) {
    var D = [];
    if (isIE) {
        var C = B.frames;
        for (var A = 0; A < C.length; A++) D.push(C[A].document)
    } else {
        C = B.getElementsByTagName("iframe");
        for (A = 0; A < C.length; A++) D.push(C[A].contentDocument);
        C = B.getElementsByTagName("frame");
        for (A = 0; A < C.length; A++) D.push(C[A].contentDocument)
    }
    return D
}
var _eos_colsed_panel = [],
_eos_curr_open_panel = null;
function Panel(A) {
    this.id = A;
    PageControl.add(A, this);
    this.container = null;
    this.title = null;
    this.onExpandFunc = null;
    this.onCloseFunc = null;
    this.status = null;
    this.button = null
}
Panel.OPEN_STYLE_BUTTON = "PANEL_OPEN_BUTTON";
Panel.CLOSE_STYLE_BUTTON = "PANEL_CLOSE_BUTTON";
Panel.prototype.init = function() {
    this.container = $id(this.id + "_container");
    this.button = $id(this.id + "_button");
    this.titleObj = $id(this.id + "_title");
    this.table = $id("_" + this.id + "_panel_table");
    this.freshButton();
    var A = this;
    this.button.onclick = function() {
        A.changeStatus()
    }
};
Panel.prototype.setTitle = function(A) {
    this.title = A;
    this.titleObj.innerHTML = A
};
Panel.prototype.getTitle = function() {
    return this.title
};
Panel.prototype.freshButton = function() {
    if (this.status) this.button.className = Panel.CLOSE_STYLE_BUTTON;
    else this.button.className = Panel.OPEN_STYLE_BUTTON
};
Panel.prototype.changeStatus = function() {
    var A = this.button;
    if (A) if (!this.status) this.open(A);
    else this.close(A)
};
Panel.prototype.open = function(obj) {
    var table = $id("_" + this.id + "_panel_table");
    if (!table) return;
    _eos_curr_open_panel = this;
    if (this.onExpandFunc) {
        try {
            var result = eval(this.onExpandFunc + "();");
            if (result != false) {
                table.rows[1].style.display = "";
                if (table.backupHeight != null) {
                    table.height = table.backupHeight;
                    this.container.style.height = this.height
                }
                this.status = true;
                this.freshButton()
            }
        } catch(e) {}
    } else {
        table.rows[1].style.display = "";
        if (table.backupHeight != null) {
            table.height = table.backupHeight;
            this.container.style.height = this.height
        }
        this.status = true;
        this.freshButton();
        if (this.needResize === true) {
            _eos_colsed_panel.pop(this);
            for (var i = 0; i < _registryEvent.length; i++) {
                var e = _registryEvent[i];
                if (e.obj == window && e.type == "resize") e.fn()
            }
            this.needResize = false
        }
    }
    _eos_curr_open_panel = null
};
Panel.prototype.close = function(obj) {
    var table = $id("_" + this.id + "_panel_table");
    if (!table) return;
    if (this.onCloseFunc) {
        try {
            _eos_colsed_panel.push(this);
            var result = eval(this.onCloseFunc + "();");
            if (result != false) {
                table.rows[1].style.display = "none";
                table.backupHeight = table.height;
                table.height = "20px";
                this.height = this.container.style.height;
                this.container.style.height = "20px";
                this.status = false;
                this.freshButton()
            }
        } catch(e) {}
    } else {
        _eos_colsed_panel.push(this);
        table.rows[1].style.display = "none";
        table.backupHeight = table.height;
        table.height = "20px";
        this.height = this.container.style.height;
        this.container.style.height = "20px";
        this.status = false;
        this.freshButton()
    }
};
Panel.prototype.collapse = Panel.prototype.close;
Panel.prototype.expand = Panel.prototype.open;
function RichTextEditor(A) {
    this.id = A;
    this.name = "";
    this.value = "";
    this.width = "100%";
    this.height = 200;
    this.value = "";
    this.BasePath = contextPath + "/common/fckeditor/";
    this.hiddenInput = null;
    this.toolbarSet = "Default";
    this.container = null;
    this.richEditor = null;
    PageControl.add(A, this)
}
RichTextEditor.prototype.init = function() {
    var A = new FCKeditor(this.id + "_textarea");
    A.BasePath = this.BasePath;
    this.container = $id(this.id + "_container");
    if (isNaN(parseInt(this.width))) this.width = "100%";
    if (isNaN(parseInt(this.height))) this.height = 200;
    A.Height = this.height;
    A.Width = this.width;
    A.Value = this.value;
    A.ToolbarSet = this.toolbarSet;
    A.ReplaceTextarea();
    this.richEditor = A
};
RichTextEditor.prototype.getValue = function() {
    var B = this.getFCKEditor(),
    A = B.GetHTML();
    if (A && this.value) if (A == "<p>" + this.value + "</p>") return this.value;
    if (A == "" || A == "<p></p>") return this.value;
    return A
};
RichTextEditor.prototype.getFCKEditor = function() {
    var A = FCKeditorAPI.GetInstance(this.id + "_textarea");
    return A
};
RichTextEditor.prototype.setValue = function(A) {
    if (A != null && A != undefined) {
        var B = this.getFCKEditor();
        B.SetHTML(A)
    } else B.SetHTML("");
    this.value = A
};
RichTextEditor.prototype.setFocus = function() {};
RichTextEditor.prototype.lostFocus = function() {};
RichTextEditor.prototype.showEditor = function() {
    this.container.style.display = ""
};
RichTextEditor.prototype.hideEditor = function() {
    this.container.style.display = "none"
};
RichTextEditor.prototype.hide = function() {
    this.hideEditor()
};
RichTextEditor.prototype.validate = function() {
    return true
};
RichTextEditor.prototype.isFocus = function() {
    return false
};
RichTextEditor.prototype.getDisplayValue = function(A) {
    return A
};
RichTextEditor.prototype.setPosition = function(D, A, B, C) {
    this.container.style.position = "absolute";
    this.container.style.top = "0px";
    this.container.style.left = "0px";
    var E = getMaxZindex(document);
    if (this.container.style.zIndex != E) this.container.style.zIndex = E;
    setElementXY(this.container, [D, A])
};
function DictRadioGroup(A) {
    this.id = A;
    this.value = null;
    this.container = $id(this.id + "_container");
    this.radioes = this.container.getElementsByTagName("input");
    this.container.onmousedown = function() {
        eventManager.stopPropagation()
    };
    this.hiddenInput = new Object();
    this.jsonObj = null;
    this.initEvent();
    PageControl.add(A, this)
}
DictRadioGroup.prototype.init = function() {};
DictRadioGroup.prototype.initEvent = function() {
    var C = this;
    function D() {
        C.refreshValue()
    }
    for (var A = 0; A < this.radioes.length; A++) {
        var B = this.radioes[A];
        eventManager.add(B, "click", D)
    }
    eventManager.add(this.container, "keypress", D)
};
DictRadioGroup.prototype.refreshInput = function() {
    str = this.hiddenInput.value;
    for (var A = 0; A < this.radioes.length; A++) {
        var B = this.radioes[A];
        if (B.value == str) B.checked = true;
        else B.checked = false
    }
};
DictRadioGroup.prototype.refreshValue = function() {
    var C;
    for (var A = 0; A < this.radioes.length; A++) {
        var B = this.radioes[A];
        if (B.checked) {
            C = B.value;
            break
        }
    }
    this.hiddenInput.value = C
};
DictRadioGroup.prototype.setValue = function(A) {
    this.hiddenInput.value = A;
    this.refreshInput()
};
DictRadioGroup.prototype.getValue = function() {
    this.refreshValue();
    return this.hiddenInput.value
};
DictRadioGroup.prototype.setFocus = function() {
    var B = false;
    for (var A = 0; A < this.radioes.length; A++) {
        var C = this.radioes[A];
        if (C.checked == true) {
            C.focus();
            B = true;
            break
        }
        if (!B) this.radioes[0].focus()
    }
};
DictRadioGroup.prototype.lostFocus = function() {};
DictRadioGroup.prototype.showEditor = function() {
    var A = getMaxZindex();
    this.container.style.zIndex = A;
    this.container.style.display = "";
    addClass(this.container.firstChild, "dict_comp");
    if (this.isDcEdit == true) {
        var B = this.container.firstChild;
        B.style.width = B.firstChild.offsetWidth;
        initShadow(B)
    }
    this.setFocus()
};
DictRadioGroup.prototype.hideEditor = function() {
    this.container.style.display = "none"
};
DictRadioGroup.prototype.setPosition = function(D, B, A, C) {
    this.container.style.position = "absolute";
    this.container.zIndex = 9999;
    this.container.style.left = D + "px";
    this.container.style.top = B + "px"
};
DictRadioGroup.prototype.isFocus = function() {
    return true
};
DictRadioGroup.prototype.validate = function() {
    return true
};
DictRadioGroup.prototype.getDisplayValue = function(A) {
    if (A == null) A = this.getValue();
    if (!this.jsonObj) return A;
    var B = this.jsonObj[A];
    return B !== undefined && B !== null ? B: A
};
function CheckGroup(A) {
    this.id = A;
    PageControl.add(A, this);
    this.subCheck = new Array();
    this.selectCheck = new Array()
}
CheckGroup.prototype.getRows = function() {
    return this.subCheck
};
CheckGroup.prototype.init = function() {
    for (var A = 0; A < this.subCheck.length; A++) {
        var B = this.subCheck[A];
        B.inited = true;
        if (B.isChecked) B.setSelected();
        else B.disSelected()
    }
    this.afterInit()
};
CheckGroup.prototype.getSelectLength = function() {
    var B = 0;
    for (var A = 0; A < this.subCheck.length; A++) if (this.subCheck[A].getStatus()) B++;
    return B
};
CheckGroup.prototype.getIndex = function(B) {
    var C = -1;
    for (var A = 0; A < this.subCheck.length; A++) if (this.subCheck[A] == B) {
        C = A;
        break
    }
    return C
};
CheckGroup.prototype.getLength = function() {
    return this.subCheck.length
};
CheckGroup.prototype.getSelectRows = function() {
    var B = [];
    for (var A = 0; A < this.subCheck.length; A++) if (this.subCheck[A].getStatus()) B.push(this.subCheck[A]);
    return B
};
CheckGroup.prototype.getSelectParams = function(D) {
    var E = this.getSelectRows(),
    C = [];
    for (var A = 0; A < E.length; A++) {
        var B = E[A].getParam(D);
        C.push(B)
    }
    return C
};
CheckGroup.prototype.selectAll = function() {
    for (var A = 0; A < this.subCheck.length; A++) {
        var B = this.subCheck[A];
        B.setSelected()
    }
};
CheckGroup.prototype.selectReverse = function() {
    for (var A = 0; A < this.subCheck.length; A++) {
        var B = this.subCheck[A];
        B.reverseSelect()
    }
};
CheckGroup.prototype.disSelectAll = function() {
    for (var A = 0; A < this.subCheck.length; A++) {
        var B = this.subCheck[A];
        B.disSelected()
    }
};
CheckGroup.prototype.get = function(A) {
    return this.subCheck[A]
};
CheckGroup.prototype.register = function(A) {
    this.subCheck.push(A)
};
CheckGroup.prototype.afterInit = function() {};
function rowCheckBox(A) {
    this.id = A;
    this.isChecked = false;
    PageControl.add(A, this);
    this.params = [];
    this.checkbox = null;
    this.container = null;
    this.subboxes = new Array();
    this.row = null;
    this.selectStyle = "";
    this.rowSelect = true;
    this.rowEvent = "click";
    this.onSelectFunc = null;
    this.onUnSelectFunc = null;
    this.groupid = null;
    this.tagName = "tr";
    this.showCheckBox = true;
    this.inited = false;
    this.backClassName = null;
    this.beforeSelectFunc = null;
    this.afterSelectFunc = null;
    this.beforeUnSelectFunc = null;
    this.afterUnSelectFunc = null
}
rowCheckBox.prototype.init = function() {
    this.container = $id(this.id + "_container");
    this.checkbox = $createElement("input", {
        id: this.id + "_checkbox",
        type: "checkbox"
    });
    this.container.appendChild(this.checkbox);
    for (var A = 0; A < this.params.length; A++) {
        var C = $createElement("input", {
            name: this.params[A].name,
            value: this.params[A].value,
            type: "checkbox",
            style: {
                display: "none"
            }
        });
        this.container.appendChild(C)
    }
    if (this.showCheckBox) this.checkbox.style.display = "";
    else this.checkbox.style.display = "none";
    var G = this.container.getElementsByTagName("input");
    if (G) for (A = 0; A < G.length; A++) {
        var F = G[A];
        if (F.id != this.id) {
            this.subboxes.push(F);
            F.checked = this.value
        }
    }
    var E = this;
    this.checkbox.onclick = function() {
        setTimeout(D, 1);
        eventManager.stopPropagation();
        return false
    };
    function D() {
        E.reverseSelect()
    }
    if (this.rowSelect) {
        this.row = getRow(this.container, this.tagName);
        this.backClassName = this.row.className;
        eventManager.add(this.row, this.rowEvent, D)
    }
    var B = PageControl.getOne(this.groupid);
    B.register(this)
};
rowCheckBox.prototype.changeStatus = function() {
    for (var A = 0; A < this.subboxes.length; A++) {
        var B = this.subboxes[A];
        B.checked = this.value
    }
    if (this.row) if (this.value) this.row.className = this.selectStyle;
    else this.row.className = this.backClassName
};
rowCheckBox.prototype.getStatus = function() {
    return this.value
};
rowCheckBox.prototype.getIndex = function() {
    return this.group.getIndex(this)
};
rowCheckBox.prototype.getParam = function(C) {
    for (var A = 0; A < this.subboxes.length; A++) {
        var B = this.subboxes[A];
        if (B.name.replace(/\[\d*\]/, "[*]") == C || B.name.replace(/\[\d*\]/, "") == C) return B.value
    }
    return null
};
rowCheckBox.prototype.reverseSelect = function() {
    if (this.value) this.disSelected();
    else this.setSelected()
};
rowCheckBox.prototype.setSelected = function() {
    if (this.beforeSelectFunc && this.inited) if (eval(this.beforeSelectFunc + "(this)") === false) return;
    this.checkbox.checked = true;
    this.value = true;
    this.changeStatus();
    if (this.onSelectFunc && this.inited) if (typeof(this.onSelectFunc) == "string") eval(this.onSelectFunc + "(this)");
    else this.onSelectFunc(this);
    if (this.afterSelectFunc && this.inited) eval(this.afterSelectFunc + "(this)")
};
rowCheckBox.prototype.disSelected = function() {
    if (this.beforeUnSelectFunc && this.inited) if (eval(this.beforeUnSelectFunc + "(this)") === false) return;
    if (this.onUnSelectFunc && this.inited) if (typeof(this.onUnSelectFunc) == "string") eval(this.onUnSelectFunc + "(this)");
    else this.onUnSelectFunc(this);
    this.checkbox.removeAttribute("checked");
    this.value = false;
    this.changeStatus();
    if (this.afterUnSelectFunc && this.inited) eval(this.afterUnSelectFunc + "(this)")
};
function getRow(B, C) {
    C = C ? C.toUpperCase() : "TR";
    var A = B;
    while (A) {
        if (A.tagName.toUpperCase() == C) return A;
        A = A.parentNode
    }
}
function selectAll(B) {
    var A = PageControl.getOne(B);
    A.selectAll()
}
function selectOther(B) {
    var A = PageControl.getOne(B);
    A.selectReverse()
}
function selectReverse(A) {
    selectOther(A)
}
function selectNone(B) {
    var A = PageControl.getOne(B);
    A.disSelectAll()
}
function disSelectAll(A) {
    selectNone(A)
}
function controlCheckGroup(A, B) {
    if (A.checked) selectAll(B);
    else selectNone(B)
}
function RadioGroup(A) {
    this.id = A;
    PageControl.add(A, this);
    this.subRadio = new Array();
    this.currRadio = null;
    this.defaultRow = null
}
RadioGroup.prototype.getRows = function() {
    return this.subRadio
};
RadioGroup.prototype.init = function() {
    for (var A = 0; A < this.subRadio.length; A++) {
        if (this.subRadio[A].isChecked) this.subRadio[A].reverseSelect();
        else this.subRadio[A].disSelected();
        this.subRadio[A].inited = true
    }
    this.afterInit()
};
RadioGroup.prototype.getParam = function(B) {
    var A = this.getSelectRow();
    if (A) return A.getParam(B);
    return null
};
RadioGroup.prototype.getIndex = function(B) {
    var C = -1;
    for (var A = 0; A < this.subRadio.length; A++) if (this.subRadio[A] == B) return A;
    return C
};
RadioGroup.prototype.getSelectRow = function() {
    return this.currRadio
};
RadioGroup.prototype.get = function(A) {
    return this.subRadio[A]
};
RadioGroup.prototype.getLength = function() {
    return this.subRadio.length
};
RadioGroup.prototype.getSelectLength = function() {
    if (this.currRadio) return 1;
    else return 0
};
RadioGroup.prototype.register = function(A) {
    this.subRadio.push(A)
};
RadioGroup.prototype.refresh = function(jsRadio) {
    if (this.currRadio != jsRadio) if (jsRadio.setSelected() !== false) {
        if (this.currRadio) this.currRadio.disSelected();
        this.currRadio = jsRadio;
        if (jsRadio.afterSelectFunc && jsRadio.inited) eval(jsRadio.afterSelectFunc + "(this)")
    }
};
RadioGroup.prototype.afterInit = function() {};
function rowRadio(A) {
    this.id = A;
    this.isChecked = false;
    PageControl.add(A, this);
    this.params = [];
    this.radio = null;
    this.container = null;
    this.subboxes = new Array();
    this.row = null;
    this.selectStyle = "";
    this.rowSelect = true;
    this.rowEvent = "click";
    this.onSelectFunc = null;
    this.onUnSelectFunc = null;
    this.groupid = null;
    this.group = null;
    this.tagName = "tr";
    this.showRadio = true;
    this.inited = false;
    this.backClassName = null;
    this.beforeSelectFunc = null;
    this.afterSelectFunc = null
}
rowRadio.prototype.init = function() {
    this.container = $id(this.id + "_container");
    this.radio = $createElement("input", {
        id: this.id + "_radio",
        type: "radio"
    });
    this.container.appendChild(this.radio);
    for (var A = 0; A < this.params.length; A++) {
        var C = $createElement("input", {
            name: this.params[A].name,
            value: this.params[A].value,
            type: "checkbox",
            style: {
                display: "none"
            }
        });
        this.container.appendChild(C)
    }
    if (this.showRadio) this.radio.style.display = "";
    else this.radio.style.display = "none";
    var G = this.container.getElementsByTagName("input");
    if (G) for (A = 0; A < G.length; A++) {
        var F = G[A];
        if (F.id != this.id) {
            this.subboxes.push(F);
            F.checked = this.value
        }
    }
    var E = this;
    this.radio.onclick = function() {
        eventManager.stopPropagation();
        setTimeout(D, 1);
        return false
    };
    function D() {
        E.reverseSelect()
    }
    if (this.rowSelect) {
        this.row = getRow(this.container, this.tagName);
        this.backClassName = this.row.className;
        eventManager.add(this.row, this.rowEvent, D)
    }
    var B = $o(this.groupid);
    B.register(this);
    this.group = B
};
rowRadio.prototype.changeStatus = function() {
    for (var A = 0; A < this.subboxes.length; A++) {
        var B = this.subboxes[A];
        B.checked = this.value
    }
    if (this.row) if (this.value) this.row.className = this.selectStyle;
    else this.row.className = this.backClassName
};
rowRadio.prototype.getStatus = function() {
    return this.value
};
rowRadio.prototype.getParam = function(C) {
    for (var A = 0; A < this.subboxes.length; A++) {
        var B = this.subboxes[A];
        if (B.name.replace(/\[\d*\]/, "[*]") == C || B.name.replace(/\[\d*\]/, "") == C) return B.value
    }
    return null
};
rowRadio.prototype.reverseSelect = function() {
    this.group.refresh(this)
};
rowRadio.prototype.getIndex = function() {
    return this.group.getIndex(this)
};
rowRadio.prototype.setSelected = function() {
    if (this.beforeSelectFunc && this.inited) if (eval(this.beforeSelectFunc + "(this)") === false) return false;
    this.radio.checked = true;
    this.value = true;
    this.changeStatus();
    if (this.onSelectFunc && this.inited) if (typeof(this.onSelectFunc) == "string") eval(this.onSelectFunc + "(this)");
    else this.onSelectFunc(this)
};
rowRadio.prototype.disSelected = function() {
    if (this.onUnSelectFunc && this.inited) if (typeof(this.onUnSelectFunc) == "string") eval(this.onUnSelectFunc + "(this)");
    else this.onUnSelectFunc(this);
    this.radio.removeAttribute("checked");
    this.value = false;
    this.changeStatus()
};
function Entity(A) {
    if (!A) A = "entity";
    this.status = Entity.STATUS_INIT;
    this.name = A;
    this.values = {};
    this.parent = new Dataset(A);
    this.keys = [];
    this.__keys = {};
    this.__type = null;
    this.id = null
}
Entity.STATUS_NEW = 0;
Entity.STATUS_INIT = 1;
Entity.STATUS_MODIFIED = 2;
Entity.STATUS_DELT = 3;
Entity.STATUS_HIDDEN = 4;
Entity.XPATH_LEVEL = 2;
Entity.LINK_SIGN = "__collection";
Entity.prototype.getPropertyByFieldName = function(A) {
    return this.values[A]
};
Entity.prototype.setPropertyByFieldName = function(C, A) {
    var B = false;
    B = this.setValue(C, A);
    if (B) this.update()
};
Entity.prototype.setPropertyByXpath = function(B, A) {
    var C = (B + "").split("/"),
    E = C[C.length - 1];
    if (C.length == 1) {
        this.setPropertyByFieldName(E, A);
        return
    }
    C = C.slice(0, C.length - 1);
    B = C.join("/");
    var D = this.getPropertyByXpath(B);
    if (D instanceof Entity) D.setPropertyByFieldName(E, A);
    else if (D !== undefined && D !== null) {
        oldValue = D[E];
        D[E] = A
    } else {
        D = this.createEntityTree(B);
        D.setPropertyByFieldName(E, A)
    }
};
Entity.prototype.createEntityTree = function(C) {
    var D = (C + "").split("/"),
    F = this,
    B;
    for (var A = 0; A < D.length; A++) {
        var E = D[A];
        B = F.getPropertyByFieldName(E);
        if (B === null || B === undefined) {
            B = new Entity(E);
            F.setPropertyByFieldName(E, B)
        }
        F = B
    }
    return B
};
Entity.prototype.getPropertyByXpath = function(C) {
    var D = (C + "").split("/"),
    B = this;
    for (var A = 0; A < D.length; A++) {
        var E = D[A];
        if (B instanceof Entity) B = B.getPropertyByFieldName(E);
        else B = B[E];
        if (B === null || B === undefined) break
    }
    return B
};
function deepEntity(A) {
    Entity.prototype.getProperty = Entity.prototype.getPropertyByFieldName;
    Entity.prototype.setProperty = Entity.prototype.setPropertyByFieldName;
    if (A) {
        Entity.prototype.getProperty = Entity.prototype.getPropertyByXpath;
        Entity.prototype.setProperty = Entity.prototype.setPropertyByXpath
    }
}
deepEntity(false);
Entity.prototype.clone = function(D) {
    var E = new Entity(this.name);
    E.__type = this.__type;
    for (var B = 0; B < this.keys.length; B++) {
        var A = this.keys[B],
        C = this.getProperty(A);
        if (C instanceof Dataset || C instanceof Entity) E.setProperty(A, C.clone(D));
        else E.setValue(A, C)
    }
    return E
};
Entity.prototype.toXMLString = function(C) {
    C = C !== false;
    var J = new StringBuffer(),
    F = "",
    I = this.name;
    if (C) {
        if (this.name && this.name.indexOf("/") > 0) {
            F = this.name.split("/")[0];
            I = this.name.split("/")[1];
            J.append("<").append(F).append(">\n")
        }
        J.append("<").append(I);
        if (this.__type) J.append(" __type=\"").append(this.__type).append("\"");
        if (this.id);
        J.append(">\n")
    }
    var A = this.getKeys(),
    D = {};
    for (var H = 0; H < A.length; H++) {
        var K = this.getProperty(A[H]),
        L = K instanceof Dataset,
        B = K instanceof Entity;
        if (K === null || K === undefined || K === "") {
            if (A[H].indexOf("/") > 0) {
                var E = A[H].split("/");
                if (!D[E[0]]) D[E[0]] = [];
                var G = "<" + E[1] + " __isNullOrEmpty=\"null\"/>\n";
                D[E[0]].push(G)
            } else J.append("<").append(A[H]).append(" __isNullOrEmpty=\"null\"/>\n")
        } else if (!L && !B) {
            if (A[H].indexOf("/") > 0) {
                E = A[H].split("/");
                if (!D[E[0]]) D[E[0]] = [];
                G = "<" + E[1] + ">" + xmlConversion(K + "") + "</" + E[1] + ">\n";
                D[E[0]].push(G)
            } else {
                J.append("<").append(A[H]).append(">");
                if (K || K === 0) J.append(xmlConversion(K.toString()));
                J.append("</").append(A[H]).append(">\n")
            }
        } else J.append(K + "")
    }
    for (var M in D) if (D.hasOwnProperty(M)) {
        J.append("<").append(M).append(">");
        J.append(D[M].join(""));
        J.append("</").append(M).append(">\n")
    }
    if (C) {
        J.append("</").append(I).append(">\n");
        if (F) J.append("</").append(F).append(">\n")
    }
    return J.toString()
};
Entity.prototype.toString = Entity.prototype.toXMLString;
Entity.prototype.getKeys = function() {
    return this.keys
};
Entity.prototype.xpathParse = function(C) {
    C += "";
    var A = C.indexOf("/");
    if (A < 0) return C;
    else {
        var B = C.split("/");
        if (B.length != 2) $error(" \u93c6\u509b\u6902\u6d93\u5d86\u656e\u93b8\u4f79\u6d3f\u6fb6\u6c31\u9a87\u9428\u524epath ");
        return B
    }
};
Entity.prototype.setValue = function(A, B) {
    var C = false;
    if (!this.values[A]) {
        if (!this.__keys[A]) {
            this.keys.push(A);
            this.__keys[A] = true
        }
        C = true
    } else if (this.values[A] != B) C = true;
    this.values[A] = B;
    return C
};
Entity.prototype.getValue = function(A) {
    return this.values[A]
};
Entity.prototype.update = function() {
    if (this.parent) {
        if (this.parent instanceof Dataset) this.parent.onUpdateEntity(this);
        this.parent.update()
    }
    this.__onUpdate();
    this.onUpdate()
};
Entity.prototype.onUpdate = function() {};
Entity.prototype.__onUpdate = function() {};
function Dataset(A) {
    this.values = [];
    this.entityType = A
}
Dataset.prototype.addEntity = function(A, B) {
    this.values.push(A);
    A.parent = this;
    if (B !== false) A.status = Entity.STATUS_NEW;
    this.onAddEntity(A);
    this.update()
};
Dataset.prototype.addBlankEntity = function() {
    var A = new Entity(this.entityType);
    if (this.getLength() != 0) A = this.get(0).clone();
    this.values.push(A);
    A.parent = this;
    A.status = Entity.STATUS_NEW;
    this.onAddEntity(A);
    this.update();
    return A
};
Dataset.prototype.removeEntity = function(C, B) {
    if (this.onDeleteEntity(C)) {
        if (B === true) {
            for (var A = 0; A < this.values.length; A++) {
                var D = this.values[A];
                if (D === C) {
                    this.values.splice(A, 1);
                    break
                }
            }
        } else C.status = Entity.STATUS_DELT;
        this.update()
    }
};
Dataset.prototype.getAlltEntities = function() {
    var C = [];
    for (var A = 0; A < this.values.length; A++) {
        var B = this.values[A];
        C.push(B)
    }
    return C
};
Dataset.prototype.setValue = function(C, A, B) {
    C.setValue(A, B)
};
Dataset.prototype.getModifiedEntities = function() {
    var C = [];
    for (var A = 0; A < this.values.length; A++) {
        var B = this.values[A];
        if (B.status == Entity.STATUS_MODIFIED) C.push(B)
    }
    return C
};
Dataset.prototype.getInsertEntities = function() {
    var C = [];
    for (var A = 0; A < this.values.length; A++) {
        var B = this.values[A];
        if (B.status == Entity.STATUS_NEW) C.push(B)
    }
    return C
};
Dataset.prototype.getRemovedEntities = function() {
    var C = [];
    for (var A = 0; A < this.values.length; A++) {
        var B = this.values[A];
        if (B.status == Entity.STATUS_DELT) C.push(B)
    }
    return C
};
Dataset.prototype.filter = function(A, C) {
    var E = [];
    for (var B = 0; B < this.values.length; B++) {
        var D = this.values[B];
        if (D.getProperty(A) == C) E.push(D)
    }
    return E
};
Dataset.prototype.findEntity = function(D, B) {
    for (var A = 0; A < this.values.length; A++) {
        var C = this.values[A];
        if (C.getProperty(D) == B) return C
    }
    return null
};
Dataset.prototype.toString = function() {
    var B = new StringBuffer();
    for (var A = 0; A < this.values.length; A++) {
        var C = this.values[A];
        B.append(C.toString())
    }
    return B.toString()
};
Dataset.prototype.clear = function() {
    this.values = [];
    this.update()
};
Dataset.prototype.appendDataset = function(B) {
    for (var A = 0; A < B.getLength(); A++) {
        var C = B.get(A).clone(true);
        this.addEntity(C);
        C.status = Entity.STATUS_INIT
    }
    this.update()
};
Dataset.prototype.init = function(D) {
    if (D.length == 1) {
        var E = D[0].getAttribute("__isNullOrEmpty");
        if (E == "empty" || E == "null") return
    }
    for (var A = 0; A < D.length; A++) {
        var B = D[A];
        if (!B.tagName) continue;
        var C = Dataset.initEntity(B);
        C.id = B.getAttribute(Entity.LINK_SIGN) ? B.getAttribute(Entity.LINK_SIGN) : null;
        this.onFillEntity(C);
        this.addEntity(C);
        C.status = Entity.STATUS_INIT
    }
};
Dataset.initEntity = function(E) {
    if (!E) return null;
    var G = new Entity(E.tagName);
    G.__type = E.getAttribute("__type");
    var B = E.childNodes,
    D = [];
    D["type"] = null;
    for (var F = 0; F < B.length; F++) {
        var A = B[F];
        if (A.nodeType == 1) if (!A.getAttribute(Entity.LINK_SIGN)) Dataset.initField(G, A);
        else if (D["type"] == A.tagName) D.push(A);
        else {
            if (D["type"] != null) {
                var C = new Dataset(D["type"]);
                C.init(D);
                G.setProperty(D["type"], C)
            }
            D = [];
            D["type"] = A.tagName;
            D.push(A)
        }
    }
    if (D["type"] != null) {
        C = new Dataset(D["type"]);
        C.init(D);
        G.setProperty(D["type"], C)
    }
    D = [];
    return G
};
Dataset.initField = function(D, C) {
    if (C.childNodes.length > 1) {
        for (var B = 0; B < C.childNodes.length; B++) {
            var A = C.childNodes[B];
            if (A.nodeType == 1) {
                var E = C.tagName + "/" + A.tagName;
                D.setProperty(E, getNodeValue(A))
            }
        }
    } else D.setProperty(C.tagName, getNodeValue(C))
};
Dataset.prototype.slice = function(B, C) {
    var D = new Dataset(this.entityType);
    if (!C) C = this.getLength();
    if (C > this.getLength()) C = this.getLength();
    for (var A = B; A < C; A++) {
        var E = this.get(A).clone();
        D.addEntity(E)
    }
    return D
};
Dataset.prototype.update = function() {
    this.onUpdate()
};
Dataset.prototype.getEntities = function() {
    return this.values
};
Dataset.prototype.getLength = function() {
    return this.getEntities().length
};
Dataset.prototype.get = function(A) {
    return this.values[A]
};
Dataset.prototype.onFillEntity = function(A) {};
Dataset.prototype.onUpdateEntity = function(A) {};
Dataset.prototype.onAddEntity = function(A) {};
Dataset.prototype.onDeleteEntity = function(A) {
    return true
};
Dataset.prototype.onUpdate = function() {};
Dataset.prototype.clone = function(D) {
    var B = new Dataset(this.entityType);
    if (!D) return B;
    else {
        for (var A = 0; A < this.values.length; A++) {
            var C = this.values[A].clone(D);
            B.values.push(C)
        }
        return B
    }
};
Dataset.prototype.getInsertDataset = function() {
    var B = new Dataset(this.entityType),
    C = this.getInsertEntities();
    for (var A = 0; A < C.length; A++) B.addEntity(C[A].clone(true));
    return B
};
Dataset.prototype.getRemovedDataset = function() {
    var B = new Dataset(this.entityType),
    C = this.getRemovedEntities();
    for (var A = 0; A < C.length; A++) B.addEntity(C[A].clone(true));
    return B
};
Dataset.prototype.getModifiedDataset = function() {
    var B = new Dataset(this.entityType),
    C = this.getModifiedEntities();
    for (var A = 0; A < C.length; A++) B.addEntity(C[A].clone(true));
    return B
};
Dataset.prototype.getSubmitXML = function() {
    var C = this.getInsertEntities(),
    B = new StringBuffer();
    if (C.length > 0) for (var A = 0; A < C.length; A++) {
        C[A].name = "insertEntities";
        B.append(C[A].toString())
    }
    C = this.getModifiedEntities();
    if (C.length > 0) for (A = 0; A < C.length; A++) {
        C[A].name = "updateEntities";
        B.append(C[A].toString())
    }
    C = this.getRemovedEntities();
    if (C.length > 0) for (A = 0; A < C.length; A++) {
        C[A].name = "deleteEntities";
        B.append(C[A].toString())
    }
    return B.toString()
};
Dataset.create = function(F, E, G) {
    var D = null;
    $debug("type of xml" + typeof(F));
    if (typeof(F) == "string") {
        D = createXmlDom();
        D.loadXML(F)
    } else if (typeof(F) == "object") D = F;
    else {
        $error("error xml of " + F);
        return null
    }
    if (E.indexOf("/") != 0) E = "/" + E;
    if (E.indexOf("/root/data") < 0) E = "/root/data" + E;
    var B = D.selectNodes(E);
    if (B) {
        if (!G) {
            var C = E.split("/");
            G = C[C.length - 1]
        }
        var A = new Dataset(G);
        A.init(B);
        return A
    } else {
        $error("no node found by xpath:" + E + "in xml:" + D.xml);
        return null
    }
};
function Dataset2Array(I, H, G) {
    var F = [];
    if (!I) return F;
    H = H || "";
    var C = I.getLength();
    for (var E = 0; E < C; E++) {
        var K = I.get(E);
        if (G) if (K.status == Entity.STATUS_DELT || K.status == Entity.STATUS_HIDDEN) continue;
        var D = Entity2Array(K),
        B = E + 1;
        for (var J = 0; J < D.length; J++) {
            var A = H + "[" + B + "]/" + D[J].key;
            F.push({
                key: A,
                value: D[J].value
            })
        }
    }
    return F
}
function Entity2Array(G) {
    var D = [];
    if (!G) return D;
    if (G.__type) D.push({
        key: "__type",
        value: G.__type
    });
    var A = G.keys.length;
    for (var C = 0; C < A; C++) {
        var H = G.keys[C],
        E = G.getProperty(H);
        if (E instanceof Entity) {
            var J = Entity2Array(E);
            for (var F = 0; F < J.length; F++) {
                var I = H + "/" + J[F].key;
                D.push({
                    key: I,
                    value: J[F].value
                })
            }
        } else if (E instanceof Dataset) {
            var B = Dataset2Array(E, H);
            for (F = 0; F < B.length; F++) {
                H = B[F].key;
                D.push({
                    key: H,
                    value: B[F].value
                })
            }
        } else {
            H = H;
            D.push({
                key: H,
                value: E
            })
        }
    }
    return D
}
function isEmptyObject(B) {
    if (typeof(B) != "object") return false;
    for (var A in B) return false;
    return true
}
function Json2Entity(D, C) {
    if (!D) return D;
    C = C || new Entity();
    for (var A in D) if (D.hasOwnProperty(A)) {
        var B = D[A];
        if (isArray(B)) B = Json2Dataset(B, A);
        else if (isEmptyObject(B)) B = null;
        else if (B !== null && B !== undefined && typeof(B) == "object") {
            B = Json2Entity(B);
            B.parent = C;
            B.name = A
        }
        C.setProperty(A, B)
    }
    return C
}
function Json2Dataset(F, A, C) {
    C = C || new Dataset();
    F = F || [];
    if (!isArray(F)) F = [F];
    for (var B = 0,
    E = F.length; B < E; B++) {
        var D = Json2Entity(F[B]);
        D.name = A || D.name;
        C.addEntity(D, false)
    }
    return C
}
function Entity2Json(F, E) {
    E = E || {};
    var A = F.getKeys();
    for (var B = 0,
    D = A.length; B < D; B++) {
        var C = F.getProperty(A[B]);
        if (C instanceof Entity) C = Entity2Json(C);
        else if (C instanceof Dataset) C = Dataset2Json(C);
        E[A[B]] = C
    }
    return E
}
function Dataset2Json(C, F, B) {
    F = F || [];
    for (var A = 0,
    E = C.values.length; A < E; A++) {
        var D = C.values[A];
        if (B !== true && (D.status == Entity.STATUS_DELT || D.status == Entity.STATUS_HIDDEN)) continue;
        F.push(Entity2Json(D))
    }
    return F
}
function SimpleEditor(A) {
    var B = null;
    if (A) if (!A.getAttribute("__eos__editor__inited")) {
        if (A.tagName && A.tagName.toLowerCase() == "form") B = initFormEditor(A);
        else B = initSimpleEditor(A);
        A.setAttribute("__eos__editor__inited", true)
    }
    return B
}
function initSimpleEditor(A) {
    A.onmouseover = function() {
        this.mouseOver = true
    };
    A.onmouseout = function() {
        this.mouseOver = false
    };
    A.onmousedown = function() {
        eventManager.stopEvent()
    };
    A.setValue = function(B) {
        if (this.type == "select-multiple") {
            var C, E = [];
            if (B instanceof Array) C = B;
            else C = B.split(",");
            for (var A = 0; A < this.options.length; A++) for (var D = 0; D < C.length; D++) if (C[D] == this.options[A].value) {
                this.options[A].selected = true;
                break
            }
        } else if (B === null || B === undefined) this.value = "";
        else this.value = B
    };
    A.getValue = function() {
        if (this.type == "select-multiple") {
            var B = [];
            for (var A = 0; A < this.options.length; A++) if (this.options[A].selected == true) B.push(this.options[A].value);
            return B
        }
        return this.value
    };
    A.showEditor = function() {
        this.style.display = "";
        try {
            this.focus()
        } catch(B) {}
        var A = this;
        setTimeout(function() {
            try {
                A.focus()
            } catch(B) {}
        },
        1)
    };
    A.hideEditor = function() {
        this.style.display = "none";
        this.hiddenMessage()
    };
    A.setPosition = function(D, A, B, C) {
        this.style.position = "absolute";
        var E = getMaxZindex(document);
        if (this.style.zIndex != E) this.style.zIndex = E;
        this.style.top = "0px";
        this.style.left = "0px";
        setElementXY(this, [D, A]);
        if (this.type == "text" || this.type == "password" || this.type == "select-one") {
            this.style.width = B + "px";
            this.style.height = C + "px"
        } else if (this.type == "select-multiple") this.style.width = B + "px"
    };
    A.isFocus = function() {
        return this.mouseOver
    };
    A.validate = function() {
        return checkInput(this)
    };
    A.onkeyup = function() {
        if (this.validate() == false) {
            var B = this,
            A = this.timeout;
            clearTimeout(A);
            this.timeout = setTimeout(function() {
                return f_alert_hidden_message.apply(B, [B])
            },
            3500)
        }
    };
    A.hiddenMessage = function() {
        f_alert_verify_successful(this)
    };
    A.getDisplayValue = function(B) {
        if (!B) return null;
        if (this.type == "select-one") {
            for (var A = 0; A < this.options.length; A++) if (this.options[A].value == B) return this.options[A].text
        } else if (this.type == "select-multiple") {
            var C, E = [];
            if (B instanceof Array) C = B;
            else C = B.split(",");
            for (A = 0; A < this.options.length; A++) for (var D = 0; D < C.length; D++) if (C[D] == this.options[A].value) {
                E.push(this.options[A].text);
                break
            }
            if (B instanceof Array) return E;
            else return E.join(",")
        } else if (this.type == "password") return "******";
        return B
    };
    return A
}
function initFormEditor(E) {
    E.value = null;
    E.onmouseover = function() {
        this.mouseOver = true
    };
    E.onmouseout = function() {
        this.mouseOver = false
    };
    E.setValue = function(A) {
        E.value = A;
        setTimeout(F, 50)
    };
    E.getValue = function() {
        return this.value
    };
    E.showEditor = function() {
        this.style.display = ""
    };
    E.hideEditor = function() {
        this.style.display = "none"
    };
    E.setPosition = function(D, B, A, C) {
        setPosition(this, [D, B]);
        if (this.type == "text" || this.type == "password" || this.type == "select-one" || this.type == "select-multiple") {
            this.style.width = A + "px";
            this.style.height = C + "px"
        }
    };
    E.isFocus = function() {
        return this.mouseOver
    };
    E.validate = function() {
        return checkForm(this)
    };
    E.getDisplayValue = function(A) {
        return A
    };
    E.freshFromEntity = function(A) {
        this.value = A;
        F()
    };
    E.update = function() {
        G()
    };
    function G() {
        if (E.value) for (var A in E.editors) {
            var B = E.editors[A];
            E.value.setProperty(A, B.getValue())
        }
    }
    function H() {}
    function F() {
        if (E.value) for (var A in E.editors) {
            var B = E.editors[A];
            B.setValue(E.value.getProperty(A))
        }
    }
    E.editors = {};
    for (var C = 0; C < E.elements.length; C++) {
        var B = E.elements[C];
        if (B.name) {
            var A = B.getAttribute("jsid");
            if (A && A.length > 0) {
                var D = $id(A);
                if (D) E.editors[B.name] = D
            } else E.editors[B.name] = (SimpleEditor(B))
        }
    }
    return E
}
function TimeSelect(A) {
    this.id = A;
    PageControl.add(this.id, this);
    this.name = null;
    this.hour = "00";
    this.minute = "00";
    this.second = "00";
    this.hourInput = null;
    this.minuteInput = null;
    this.secondInput = null;
    this.container = null;
    this.upButton = null;
    this.downButton = null;
    this.hiddenInput = null;
    this.dateTime = null;
    this.defaultValue = null;
    this.__privateFormat = "HH:mm:ss";
    this.format = "HH:mm:ss";
    this.tabIndex = null;
    this.accesskey = null;
    this.currInput = null;
    this.BUTTON_UP_ID = "BUTTON_UP" + A;
    this.BUTTON_DOWN_ID = "BUTTON_DOWN" + A;
    this.INPUT_HOUR_ID = "INPUT_HOUR" + A;
    this.INPUT_MINUTE_ID = "INPUT_MINUTE" + A;
    this.INPUT_SECOND_ID = "INPUT_SECOND" + A;
    this.INPUT_HIDDEN_ID = "INPUT_HIDDEN" + A;
    this.maxValue = "23:59:59";
    this.minValue = "00:00:00";
    this.maxHour = 23;
    this.minHour = 0;
    this.maxMinute = 59;
    this.minMinute = 0;
    this.maxSecond = 59;
    this.minSecond = 0;
    this.readonly = false;
    this.isOver = false;
    this.disabled = false;
    this.allowNull = true
}
TimeSelect.prototype.setReadOnly = function(A) {
    this.readonly = A;
    if (A) {
        this.hourInput.readOnly = true;
        this.minuteInput.readOnly = true;
        this.secondInput.readOnly = true
    } else {
        this.hourInput.readOnly = false;
        this.minuteInput.readOnly = false;
        this.secondInput.readOnly = false
    }
};
TimeSelect.prototype.getReadOnly = function() {
    return this.readonly
};
TimeSelect.prototype.setDisabled = function(A) {
    this.disabled = A;
    if (A) {
        this.hiddenInput.disabled = true;
        this.hourInput.disabled = true;
        this.minuteInput.disabled = true;
        this.secondInput.disabled = true
    } else {
        this.hiddenInput.disabled = false;
        this.hourInput.disabled = false;
        this.minuteInput.disabled = false;
        this.secondInput.disabled = false
    }
};
TimeSelect.prototype.getDisabled = function() {
    return this.disabled
};
TimeSelect.prototype.init = function() {
    var B = this.toHtml(),
    A = $create("div");
    A.innerHTML = B;
    this.container = $id(this.id + "_container");
    this.container.appendChild(A);
    this.container.className = "eos-time";
    this.container.onmouseover = function() {
        this.className = "eos-time-over";
        this.isOver = true
    };
    this.container.onmouseout = function() {
        this.className = "eos-time";
        this.isOver = false
    };
    this.container.onmousedown = function() {};
    if (!this.defaultValue && !this.defaultNull) this.defaultValue = "00:00:00";
    if (this.defaultValue && timeFormat(this.defaultValue, this.format)) {
        if (this.maxValue && this.defaultValue > this.maxValue) this.defaultValue = this.maxValue;
        if (this.minValue && this.defaultValue < this.minValue) this.defaultValue = this.minValue
    }
    this.initObject();
    this.setReadOnly(this.readonly);
    this.setDisabled(this.disabled);
    this.currInput = null
};
TimeSelect.prototype.initObject = function() {
    this.setCheckValue();
    this.hourInput = $id(this.INPUT_HOUR_ID);
    this.minuteInput = $id(this.INPUT_MINUTE_ID);
    this.secondInput = $id(this.INPUT_SECOND_ID);
    this.upButton = $id(this.BUTTON_UP_ID);
    this.downButton = $id(this.BUTTON_DOWN_ID);
    this.hiddenInput = $id(this.INPUT_HIDDEN_ID);
    var A = this;
    this.hourInput.onkeydown = function() {
        var B = eventManager.getKeyCode();
        return A.downkeyCode(B)
    };
    this.minuteInput.onkeydown = function() {
        var B = eventManager.getKeyCode();
        return A.downkeyCode(B)
    };
    this.secondInput.onkeydown = function() {
        var B = eventManager.getKeyCode();
        return A.downkeyCode(B)
    };
    this.setValue(this.defaultValue)
};
TimeSelect.prototype.setCheckValue = function() {
    if (isFormatTime(this.maxValue, this.format)) this.maxValue = timeFormat(this.maxValue, this.format, this.__privateFormat);
    if (!isFormatTime(this.maxValue, this.__privateFormat)) this.maxValue = "23:59:59";
    var B = this.maxValue.split(":");
    this.maxHour = B[0];
    this.maxMinute = B[1];
    this.maxSecond = B[2];
    if (isFormatTime(this.minValue, this.format)) this.minValue = timeFormat(this.minValue, this.format, this.__privateFormat);
    if (!isFormatTime(this.minValue, this.__privateFormat)) this.minValue = "00:00:00";
    var A = this.minValue.split(":");
    this.minHour = A[0];
    this.minMinute = A[1];
    this.minSecond = A[2]
};
TimeSelect.prototype.downkeyCode = function(A) {
    if (A == 38) {
        this.up();
        return false
    }
    if (A == 40) {
        this.down();
        return false
    }
    if (A == 9 || A == 8 || A == 37 || A == 39) return true;
    if (A == 229) return false;
    if (A < 106 && A > 95) return true;
    if (A == 46) return true;
    if (A > 47 && A < 58) return true;
    else return false
};
TimeSelect.prototype.clear = function() {
    if (this.allowNull) this.setValue(null)
};
TimeSelect.prototype.up = function() {
    if (this.readonly || this.disabled) return;
    if (!this.currInput) {
        this.currInput = this.secondInput;
        if (allTrim(this.hourInput.value) == "" && allTrim(this.minuteInput.value) == "") {
            this.hourInput.value = "00";
            this.minuteInput.value = "00"
        }
    }
    var A = this.currInput.value * 1 + 1;
    this.currInput.value = dateToStringValue(A);
    this.prevent(this.currInput);
    this.currInput.focus();
    this.currInput.select()
};
TimeSelect.prototype.down = function() {
    if (this.readonly || this.disabled) return;
    $debug(this.currInput);
    if (!this.currInput) {
        this.currInput = this.secondInput;
        if (this.hourInput.value == "" && this.minuteInput.value == "") {
            this.hourInput.value = "00";
            this.minuteInput.value = "00"
        }
    }
    var A = this.currInput.value * 1 - 1;
    this.currInput.value = dateToStringValue(A);
    this.prevent(this.currInput);
    this.currInput.focus();
    this.currInput.select()
};
TimeSelect.prototype.getHours = function() {
    this.refreshValue();
    return dateToStringValue(this.hourInput.value)
};
TimeSelect.prototype.getMinutes = function() {
    this.refreshValue();
    return dateToStringValue(this.minuteInput.value)
};
TimeSelect.prototype.getSeconds = function() {
    this.refreshValue();
    return dateToStringValue(this.secondInput.value)
};
TimeSelect.prototype.setHours = function(A) {
    this.hourInput.value = dateToStringValue(A);
    this.refreshValue()
};
TimeSelect.prototype.setMinutes = function(A) {
    this.minuteInput.value = dateToStringValue(A);
    this.refreshValue()
};
TimeSelect.prototype.setSeconds = function(A) {
    this.secondInput.value = dateToStringValue(A);
    this.refreshValue()
};
TimeSelect.prototype.getValue = function() {
    this.refreshValue();
    if (this.dateTime) return timeFormat(this.dateTime, this.__privateFormat, this.format);
    else return null
};
TimeSelect.prototype.setValue = function(A) {
    if (A === null || A === undefined || A == "") this.dateTime = null;
    else this.dateTime = timeFormat(A, this.format, this.__privateFormat);
    this.refresh()
};
TimeSelect.prototype.setFocus = function() {
    this.hourInput.focus()
};
TimeSelect.prototype.isFocus = function() {
    return this.isOver
};
TimeSelect.prototype.showEditor = function() {
    this.container.style.display = ""
};
TimeSelect.prototype.hideEditor = function() {
    this.container.style.display = "none"
};
TimeSelect.prototype.getDisplayValue = function(A) {
    return A
};
TimeSelect.prototype.setPosition = function(D, A, B, C) {
    if (this.container) {
        this.container.style.position = "absolute";
        var E = getMaxZindex(document);
        if (this.container.style.zIndex != E) this.container.style.zIndex = E;
        setElementXY(this.container, [D, A])
    }
};
TimeSelect.prototype.refresh = function() {
    if (this.dateTime) {
        var A = this.dateTime.split(":");
        A[0] = A[0] || "00";
        A[1] = A[1] || "00";
        A[2] = A[2] || "00";
        this.hourInput.value = A[0];
        this.minuteInput.value = A[1];
        this.secondInput.value = A[2]
    } else {
        this.hourInput.value = "";
        this.minuteInput.value = "";
        this.secondInput.value = ""
    }
    this.refreshValue()
};
TimeSelect.prototype.refreshValue = function() {
    var A = dateToStringValue(this.hourInput.value),
    B = dateToStringValue(this.minuteInput.value),
    C = dateToStringValue(this.secondInput.value);
    if (A && B && C) {
        this.dateTime = A + ":" + B + ":" + C;
        this.hiddenInput.value = this.dateTime
    }
};
TimeSelect.prototype.toHtml = function() {
    var B = "tabIndex=\"" + this.tabIndex + "\" accesskey=\"" + this.accesskey + "\" ",
    A = B + "type=\"text\" autocomplete=\"off\" onpropertychange=\"$o('" + this.id + "').setForObjValue(this)\"  maxlength=\"2\" onclick=\"this.focus();this.select()\" onfocus=\"$o('";
    A += this.id + "').Focus(this);\" onkeyup=\"$o('" + this.id + "').keyPress(this)\"";
    A += " onpaste=\"return false\" ondragenter=\"return false\" onchange=\"$o('";
    A += this.id + "').setForObjValue(this)\" onblur=\"$o('" + this.id + "').Blur(this)\"";
    var C = "";
    C += "<div id=\"" + this.id + "_time_container\" class=\"eos-time-container\" style=\"background-color:#ffffff;width: 91px;height:22px;float:left;overflow:hidden\">";
    C += "<div style=\"font-size:11pt;float:left;width: 72px;height:22px;overflow:hidden;float:left;padding-top:1px;padding-buttom:3px;padding-left:3px;\">";
    C += "<input numtype=\"h\" id=\"" + this.INPUT_HOUR_ID + "\"  " + A + " style=\"ime-mode:disabled;width:18px;height:14px;vertical-align:middle;background-color:transparent;border:0px solid black;font-size: 9pt;text-align: center;\"><b>:</b>";
    C += "<input numtype=\"m\" id=\"" + this.INPUT_MINUTE_ID + "\"  " + A + " style=\"ime-mode:disabled;width:18px;height:14px;vertical-align:middle;background-color:transparent;border:0px solid black;font-size: 9pt;text-align: center;\"><b>:</b>";
    C += "<input numtype=\"s\" id=\"" + this.INPUT_SECOND_ID + "\"  " + A + " style=\"ime-mode:disabled;width:18px;height:14px;vertical-align:middle;background-color:transparent;border:0px solid black;font-size: 9pt;text-align: center;\">";
    C += "</div>";
    C += "<div style=\"width: 16px;height:22px;overflow:hidden;float:right;padding-top:0px;\">";
    C += "<div style=\"width: 16px;height:11px;overflow:hidden;\">";
    C += "<div onclick=\"$id('" + this.id + "').controlTime(true)\" id=\"" + this.BUTTON_UP_ID + "\" class=\"eos-time-arrow-up\" onmouseover=\"this.className='eos-time-arrow-up-over'\" onmouseout=\"this.className='eos-time-arrow-up'\" style=\"width: 16px;height:10px;\">";
    C += "</div>";
    C += "</div>";
    C += "<div style=\"width: 16px;height:11px;overflow:hidden;\">";
    C += "<div onclick=\"$id('" + this.id + "').controlTime(false)\" id=\"" + this.BUTTON_DOWN_ID + "\" class=\"eos-time-arrow-down\" onmouseover=\"this.className='eos-time-arrow-down-over'\" onmouseout=\"this.className='eos-time-arrow-down'\" style=\"width: 16px;height:10px;\">";
    C += "</div>";
    C += "</div>";
    C += "</div>";
    C += "</div>";
    C += "<div><input validateAttr='type=eos_time'  type='hidden' ";
    if (this.name) C += "name='" + this.name + "'";
    C += "id='" + this.INPUT_HIDDEN_ID + "' value='" + this.dateTime + "'></div>";
    return C
};
TimeSelect.prototype.keyPress = function(A) {
    if (A.value.length == 2) this.prevent(A)
};
TimeSelect.prototype.Blur = function(B) {
    this.lostFocus = true;
    var A = this;
    if (B.value.length == 2) this.prevent(B);
    else if (B.value.length == 1) {
        B.value = "0" + B.value;
        this.prevent(B)
    }
    window.tmpTimeout = setTimeout(function() {
        if (A.lostFocus) A.validate()
    },
    300)
};
TimeSelect.prototype.Focus = function(A) {
    this.currInput = A;
    this.lostFocus = false
};
TimeSelect.prototype.validate = function() {
    if (allTrim(this.hourInput.value + this.minuteInput.value + this.secondInput.value) == "") if (this.allowNull) {
        f_alert_verify_successful($id(this.id + "_time_container"));
        return true
    } else {
        f_alert($id(this.id + "_time_container"), CHECK_MUST_INPUT);
        return false
    }
    if (allTrim(this.hourInput.value) == "" || allTrim(this.minuteInput.value) == "" || allTrim(this.secondInput.value) == "") {
        f_alert($id(this.id + "_time_container"), TIME_INPUTTIME + this.hourInput.value + ":" + this.minuteInput.value + ":" + this.secondInput.value + TIME_FORMATERROR);
        return false
    }
    if (parseInt(this.maxHour * 3600 + this.maxMinute * 60 + this.maxMinute) < parseInt(this.hourInput.value * 3600 + this.minuteInput.value * 60 + this.secondInput.value)) {
        f_alert($id(this.id + "_time_container"), TIME_INPUTTIME + this.hourInput.value + ":" + this.minuteInput.value + ":" + this.secondInput.value + TIME_LESSTHAN + this.maxValue);
        return false
    }
    if ((this.minHour * 3600 + this.minMinute * 60 + this.minMinute) > (this.hourInput.value * 3600 + this.minuteInput.value * 60 + this.secondInput.value)) {
        f_alert($id(this.id + "_time_container"), TIME_INPUTTIME + this.hourInput.value + ":" + this.minuteInput.value + ":" + this.secondInput.value + TIME_MORETHAN + this.minValue);
        return false
    }
    f_alert_verify_successful($id(this.id + "_time_container"));
    return true
};
TimeSelect.prototype.prevent = function(A) {
    if (A.value == "" || A.value === null || A.value === undefined) return;
    numtype = A.getAttribute("numtype");
    if (numtype == "h") {
        if (A.value * 1 > 23) A.value = "00";
        else if (A.value * 1 < 0) A.value = "23";
        else if (isNaN(A.value)) A.value = "00"
    } else if (numtype == "m" || numtype == "s") if (A.value * 1 > 59) A.value = "00";
    else if (A.value * 1 < 0) A.value = "59";
    else if (isNaN(A.value)) A.value = "00"
};
TimeSelect.prototype.setForObjValue = function(A) {
    this.prevent(A);
    this.refreshValue()
};
TimeSelect.prototype.controlTime = function(A) {
    if (A) this.up();
    else this.down();
    this.refreshValue()
};
function f_check_eos_time(A) {
    if (A.id) {
        var C = A.id;
        if (C.indexOf("INPUT_HIDDEN") >= 0) {
            C = C.replace("INPUT_HIDDEN", "");
            var B = $id(C);
            if (B) return B.validate()
        }
    }
    return true
}
function Calendar(A, B) {
    this.showMoreDay = true;
    this.id = A;
    this.Obj = A;
    this.date = null;
    this.mouseOffset = null;
    this.timer = null;
    this.timeSelect = null;
    this.format = isDateFormat(B) ? B: "yyyy-MM-dd";
    if (isTimeFormat(this.format)) {
        this.showTime = true;
        this.__privateFormat = "yyyy-MM-dd HH:mm:ss";
        this.maxValue = dateFormat("2500-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss", this.format);
        this.minValue = dateFormat("1700-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss", this.format)
    } else {
        this.showTime = false;
        this.__privateFormat = "yyyy-MM-dd";
        this.maxValue = dateFormat("2500-12-31", "yyyy-MM-dd", this.format);
        this.minValue = dateFormat("1700-01-01", "yyyy-MM-dd", this.format)
    }
    this.submitFormat = null;
    this.inputObject = null;
    this.eventObject = null;
    this.hiddenObject = null;
    this.canClose = true;
    this.container = null;
    this.focusStatus = false;
    this.defaultNull = false;
    this.allowInput = true;
    this.onSelectFunc = null;
    this.value = null;
    PageControl.add(this.id, this)
}
Calendar.currentCalendar = null;
Calendar.editorContainer = null;
Calendar.doc = document;
Calendar.prototype.init = function() {
    this.container = $id(this.id + "_container");
    this.inputObject = $id(this.id + "_input");
    this.hiddenObject = $id(this.id + "_hidden");
    this.eventObject = $id(this.id + "_button");
    this.button = this.eventObject;
    this.text = this.inputObject;
    this.hidden = this.hiddenObject;
    this.setReadOnly(this.readOnly);
    this.setDisabled(this.isDisabled);
    if (this.width != null) this.text.style.width = this.width;
    this.refreshDisplayValue();
    this.refreshHiddenValue();
    this.initEvent();
    if (this.maxValue) this.maxValue = dateFormat(this.maxValue, this.format, this.__privateFormat);
    if (this.minValue) this.minValue = dateFormat(this.minValue, this.format, this.__privateFormat)
};
Calendar.prototype.setReadOnly = function(A) {
    this.readOnly = A;
    this.inputObject.readOnly = A || !this.allowInput;
    if (A) this.eventObject.style.cursor = "default";
    else this.eventObject.style.cursor = "pointer"
};
Calendar.prototype.getReadOnly = function() {
    return this.readOnly
};
Calendar.prototype.setDisabled = function(A) {
    this.isDisabled = A;
    if (A) {
        this.disabled();
        this.eventObject.style.cursor = "default"
    } else {
        this.enable();
        this.eventObject.style.cursor = "pointer"
    }
};
Calendar.prototype.getDisabled = function() {
    return this.isDisabled
};
Calendar.prototype.refreshDisplayValue = function() {
    if (this.value) {
        this.inputObject.value = dateFormat(this.value, this.__privateFormat, this.format);
        f_alert_verify_successful(this.inputObject)
    }
};
Calendar.prototype.refreshHiddenValue = function() {
    if (this.value) {
        var A = this.__privateFormat;
        if (this.submitFormat && this.submitFormat != this.__privateFormat) A = this.submitFormat;
        this.hiddenObject.value = dateFormat(this.value, this.__privateFormat, A)
    } else this.hiddenObject.value = ""
};
Calendar.prototype.isFocus = function() {
    return this.focusStatus
};
Calendar.prototype.initEvent = function() {
    var A = this;
    this.inputObject.onkeyup = function() {
        if (checkInput(this) == false) return;
        if (!A.validate()) f_alert(A.eventObject, CALENDAR_ERROR_BETWEEN.replace("{0}", this.value).replace("{1}", A.format).replace("{2}", A.minValue).replace("{3}", A.maxValue));
        else {
            f_alert_verify_successful(this);
            A.date = dateFormat(this.value, A.format, A.__privateFormat);
            if (this.value == "") A.value = "";
            else A.value = dateFormat(this.value, A.format, A.__privateFormat);
            A.refreshHiddenValue()
        }
    };
    this.inputObject.onblur = this.inputObject.onkeyup;
    this.eventObject.src = contextPath + "/common/skins/skin0/images/calendar/calendar_button.gif";
    this.eventObject.onmouseover = function() {
        if (A.getDisabled() || A.getReadOnly()) return;
        this.src = contextPath + "/common/skins/skin0/images/calendar/calendar_button_over.gif"
    };
    this.eventObject.onmouseout = function() {
        if (A.getDisabled() || A.getReadOnly()) return;
        this.src = contextPath + "/common/skins/skin0/images/calendar/calendar_button.gif"
    };
    this.eventObject.onmousedown = function() {
        if (A.getDisabled() || A.getReadOnly()) return;
        this.src = contextPath + "/common/skins/skin0/images/calendar/calendar_button_down.gif"
    };
    this.eventObject.onmouseup = function() {
        if (A.getDisabled() || A.getReadOnly()) return;
        this.src = contextPath + "/common/skins/skin0/images/calendar/calendar_button_over.gif";
        if (CalendarEditor.inited === false) {
            CalendarEditor.init();
            CalendarEditor.inited = true
        }
        if (CalendarEditor.getCalendar() == A) {
            if (CalendarEditor.showStatus) A.hide();
            else A.show()
        } else A.show();
        eventManager.stopPropagation()
    }
};
Calendar.prototype.show = function() {
    PageControl.addtoStack(this);
    var A = {
        maxValue: this.maxValue,
        minValue: this.minValue,
        value: this.value,
        format: this.__privateFormat,
        showTime: this.showTime
    };
    CalendarEditor.reFreshEditor(A);
    CalendarEditor.showEditor();
    var C = getPosition(this.container),
    G = C.left,
    D = C.top;
    if (window != CalendarEditor.win) if (isIE) {
        var B = getAbsPos(this.inputObject, CalendarEditor.win);
        G = B.left;
        D = B.top;
        if (CalendarEditor.win != window) {
            G = G - 2;
            D = D - 2
        }
    } else {
        B = getScreenPos(window, CalendarEditor.win);
        G = G + B.left - document.body.scrollLeft;
        D = D + B.top - document.body.scrollTop
    }
    D = D * 1 + this.inputObject.offsetHeight * 1;
    CalendarEditor.setPosition(G, D);
    CalendarEditor.setCurrEditor(this);
    if (!this.inputObject) {
        $error("err parameters of date in Calendar:" + this.id + ".show");
        return
    }
    var F = this.inputObject.value,
    E = false;
    if (F) if (isDate(F, this.format)) if (this.showTime) {
        if (isFormatTime(F, this.format)) {
            F = dateFormat(F, this.format, this.__privateFormat);
            E = true
        }
    } else {
        F = dateFormat(F, this.format, this.__privateFormat);
        E = true
    }
    if (!E) F = dateToString(new Date(), this.__privateFormat);
    this.focusStatus = true
};
Calendar.prototype.hide = function() {
    try {
        CalendarEditor.hideEditor();
        this.focusStatus = false
    } catch(A) {}
};
Calendar.prototype.getValue = function() {
    return this.value
};
Calendar.prototype.setValue = function(A) {
    if (!A || A == "") {
        this.value = null;
        this.inputObject.value = "";
        this.hiddenObject.value = "";
        return
    }
    if ((typeof(A)) != "string") {
        this.value = dateToString(A, this.__privateFormat);
        this.refreshDisplayValue();
        this.refreshHiddenValue()
    } else {
        if (isDate(A, this.__privateFormat)) {
            this.value = A;
            this.refreshDisplayValue();
            this.refreshHiddenValue()
        } else if (this.submitFormat && isDate(A, this.submitFormat)) {
            this.value = dateFormat(A, this.submitFormat, this.__privateFormat);
            this.refreshDisplayValue();
            this.refreshHiddenValue()
        }
        if (isDate(A, "yyyy-MM-dd HH:mm:ss.S") || isDate(A, "yyyy-MM-dd HH:mm:ss")) {
            this.value = dateFormat(A, "yyyy-MM-dd HH:mm:ss", this.__privateFormat);
            this.refreshDisplayValue();
            this.refreshHiddenValue()
        } else {
            this.inputObject.value = "";
            this.hiddenObject.value = ""
        }
    }
};
Calendar.prototype.setPosition = function(D, A, B, C) {
    this.hide();
    if (this.container) {
        this.container.style.display = "";
        this.container.style.position = "absolute";
        var E = getMaxZindex(document);
        if (this.container.style.zIndex != E) this.container.style.zIndex = E;
        this.container.style.left = D;
        this.container.style.top = A;
        this.eventObject.style.height = C;
        this.container.style.height = C;
        this.container.style.width = B;
        this.inputObject.style.height = C;
        this.inputObject.style.width = B - 17
    }
};
Calendar.prototype.setFocus = function() {};
Calendar.prototype.onValidate = function(B, A) {
    return true
};
Calendar.prototype.validate = function() {
    var B = this.inputObject.value;
    if (B) {
        var F = isDate(this.inputObject.value, this.format),
        A = true;
        if (this.showTime) A = isFormatTime(this.inputObject.value, this.format);
        if (F && A) {
            var D = stringToDate(this.maxValue, this.__privateFormat),
            C = stringToDate(this.minValue, this.__privateFormat),
            E = stringToDate(B, this.format);
            if (E - D > 0) {
                f_alert(this.inputObject, CALENDAR_ERROR_MIN.replace("{0}", B).replace("{1}", this.maxValue));
                return false
            }
            if (E - C < 0) {
                f_alert(this.inputObject, CALENDAR_ERROR_MAX.replace("{0}", B).replace("{1}", this.minValue));
                return false
            }
        } else {
            f_alert(this.inputObject, CALENDAR_ERROR_RORMAT.replace("{0}", B).replace("{1}", this.format));
            return false
        }
    }
    return this.onValidate(this.hidden.value, this.text.value)
};
Calendar.prototype.hideEditor = function() {
    f_alert_verify_successful(this.inputObject);
    this.hide();
    this.container.style.display = "none";
    this.eventObject.style.display = "none"
};
Calendar.prototype.showEditor = function() {
    this.container.style.display = "";
    this.eventObject.style.display = ""
};
Calendar.prototype.disabled = function() {
    this.inputObject.disabled = true;
    this.eventObject.disabled = true;
    this.hiddenObject.disabled = true;
    this.container.disabled = true
};
Calendar.prototype.getDisplayValue = function(A) {
    if (A === null || A === undefined || A == "") return "";
    if (isDate(A, this.__privateFormat)) return dateFormat(A, this.__privateFormat, this.format);
    else {
        if (isDate(A, "yyyy-MM-dd")) return dateFormat(A, "yyyy-MM-dd", this.format);
        if (isDate(A, "yyyy-MM-dd HH:mm:ss")) return dateFormat(A, "yyyy-MM-dd HH:mm:ss", this.format);
        if (isDate(A, "yyyy-MM-dd HH:mm:ss.S")) return dateFormat(A, "yyyy-MM-dd HH:mm:ss", this.format);
        return A
    }
};
Calendar.prototype.enable = function() {
    this.inputObject.disabled = false;
    this.eventObject.disabled = false;
    this.hiddenObject.disabled = false;
    this.container.disabled = false
};
function ShowCalendar(B) {
    var A = $o(B);
    if (A) A.show()
}
function CalendarEditor() {}
CalendarEditor.container = null;
CalendarEditor.value = null;
CalendarEditor.maxValue = "2500-12-31";
CalendarEditor.minValue = "1700-01-01";
CalendarEditor.format = "yyyy-MM-dd";
CalendarEditor.showTime = false;
CalendarEditor.MAX_YEAR = 2500;
CalendarEditor.MIN_YEAR = 1700;
CalendarEditor.lastYear = null;
CalendarEditor.nextYear = null;
CalendarEditor.year = null;
CalendarEditor.month = null;
CalendarEditor.lastMonth = null;
CalendarEditor.nextMonth = null;
CalendarEditor.today = null;
CalendarEditor.body = null;
CalendarEditor.showStatus = false;
CalendarEditor.inited = false;
CalendarEditor.currCell = null;
CalendarEditor.init = function() {
	var eButton ="";
	if(window.isWebkit){
		eButton = "\"eos-button-gc\"";
	}else{
		eButton = "\"eos-button\"";
	}
    CalendarEditor.win = _get_top_window() || window;
    CalendarEditor.doc = CalendarEditor.win.document || document;
    var B = $id("_eos_webcomp_calendar_container", CalendarEditor.doc);
    if (B) CalendarEditor.container = B;
    else {
        var E = "";
        if (isIE) E += "<iframe style=\"position:absolute;z-index:-1;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>";
        E += "<div id=\"_eos_calendar_editor_container\" onmousedown=\"eventManager.stopPropagation();\" oncontextmenu=\"return false\" class=\"eos-celendar-editor-container\" style=\" width: 228px;\">";
        E += "    <div class=\"eos-celendar-editor-container2\" style=\" width: 100%;\">";
        E += "    <table style=\"width: 100%\" cellspacing=\"0\" cellpadding=\"0\">";
        E += "        <tr>";
        E += "            <td class=\"eos-calendar-head\" style=\"height:26px\">";
        E += "            <div>";
        E += "            <table  cellspacing=\"0\" cellpadding=\"0\" style=\"width: 100%\">";
        E += "                <tr>";
        E += "                    <td>&nbsp;</td>";
        E += "                    <td style=\"width: 11px\">";
        E += "                        <img style=\"cursor:hand;cursor:pointer;\" id=\"_eos_calendar_last_year\" alt=\"\" src=\"" + PICTURE_ARROW_LEFT + "\" width=\"11\" height=\"10\" /></td>";
        E += "                    <td align=\"center\" style=\"width: 41px\">";
        E += "                        <input maxLength=\"4\" id=\"_eos_calendar_year\" class=\"eos-calendar-editor-date\" onmouseover=\"eventManager.stopPropagation();\" onmousedown=\"eventManager.stopPropagation();this.select();\" onclick=\"eventManager.stopPropagation();this.select();\" onmouseup=\"eventManager.stopPropagation();this.select();\"   type=\"text\" style=\"width:35px;padding-left:3;height:20px\" />";
        E += "                    </td>";
        E += "                    <td style=\"width: 10px\">";
        E += "                        <img style=\"cursor:hand;cursor:pointer;\" id=\"_eos_calendar_next_year\" alt=\"\" src=\"" + PICTURE_ARROW_RIGHT + "\" width=\"11\" height=\"10\" /></td>";
        E += "                    <td width=\"30px\">&nbsp;</td>";
        E += "                    <td style=\"width: 10px\">";
        E += "                        <img style=\"cursor:hand;cursor:pointer;\" id=\"_eos_calendar_last_month\" alt=\"\" src=\"" + PICTURE_ARROW_LEFT + "\" width=\"11\" height=\"10\" /></td>";
        E += "                    <td align=\"center\"style=\"width:41px\">";
        E += "                        <input maxLength=\"2\" id=\"_eos_calendar_month\"class=\"eos-calendar-editor-date\" onmouseover=\"eventManager.stopPropagation();\" onmousedown=\"eventManager.stopPropagation();this.select();\" onclick=\"eventManager.stopPropagation();this.select();\" onmouseup=\"eventManager.stopPropagation();this.select();\" type=\"text\" style=\"width:35px;padding-left:9;height:20px\" /></td>";
        E += "                    <td style=\"width: 9px\">";
        E += "                        <img style=\"cursor:hand;cursor:pointer;\" id=\"_eos_calendar_next_month\" alt=\"\" src=\"" + PICTURE_ARROW_RIGHT + "\" width=\"11\" height=\"10\" /></td>";
        E += "                    <td>&nbsp;</td>";
        E += "                </tr>";
        E += "            </table>";
        E += "            </div>";
        E += "            </td>";
        E += "        </tr>";
        E += "        <tr>";
        E += "            <td style=\"height: 144px\"><div style=\"height: 144px\">";
        E += "            <table  style=\"width:" + (isIE ? "100%": "228px") + ";height:24px\" cellspacing=\"0\" cellpadding=\"0\">";
        E += "                ";
        E += "                <tr>";
        E += "                    <td style=\"width: " + (isIE ? "31": "33") + "px; height: 24px\" class=\"eos-calendar-editor-week-left\">" + CALENDAR_SUNDAY + "</td>";
        E += "                    <td style=\"width: " + (isIE ? "31": "32") + "px; height: 24px\" class=\"eos-calendar-editor-week\">" + CALENDAR_MONDAY + "</td>";
        E += "                    <td style=\"width: " + (isIE ? "31": "33") + "px; height: 24px\" class=\"eos-calendar-editor-week\">" + CALENDAR_TUESDAY + "</td>";
        E += "                    <td style=\"width: 31px; height: 24px\" class=\"eos-calendar-editor-week\">" + CALENDAR_WEDNESDAY + "</td>";
        E += "                    <td style=\"width: 31px; height: 24px\" class=\"eos-calendar-editor-week\">" + CALENDAR_THURSDAY + "</td>";
        E += "                    <td style=\"width: 31px; height: 24px\" class=\"eos-calendar-editor-week\">" + CALENDAR_FRIDAY + "</td>";
        E += "                    <td style=\"width: " + (isIE ? "32": "33") + "px; height: 24px\" class=\"eos-calendar-editor-week-right\">" + CALENDAR_SATURDAY + "</td>";
        E += "                </tr>";
        E += "                </table>";
        E += "            <table id=\"_eos_calendar_editor_body\" style=\"width:224;height:120px;table-layout:fixed\" cellspacing=\"0\" cellpadding=\"0\" class=\"eos-calendar-editor-body\">";
        if (isIE) {
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day0\" style=\"width: 32px; height: 18px\" >25</td>";
            E += "                    <td id=\"_eos_calendar_day1\" style=\"width: 32px; height: 18px\" >26</td>";
            E += "                    <td id=\"_eos_calendar_day2\" style=\"width: 32px; height: 18px\" >27</td>";
            E += "                    <td id=\"_eos_calendar_day3\" style=\"width: 32px; height: 18px\" >28</td>";
            E += "                    <td id=\"_eos_calendar_day4\" style=\"width: 32px; height: 18px\" >29</td>";
            E += "                    <td id=\"_eos_calendar_day5\" style=\"width: 32px; height: 18px\" >30</td>";
            E += "                    <td id=\"_eos_calendar_day6\" style=\"width: 32px; height: 18px\" >1</td>";
            E += "                </tr>";
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day7\" style=\"width: 32px; height: 18px\" >2</td>";
            E += "                    <td id=\"_eos_calendar_day8\" style=\"width: 32px; height: 18px\" >3</td>";
            E += "                    <td id=\"_eos_calendar_day9\" style=\"width: 32px; height: 18px\" >4</td>";
            E += "                    <td id=\"_eos_calendar_day10\" style=\"width: 32px; height: 18px\" >5</td>";
            E += "                    <td id=\"_eos_calendar_day11\" style=\"width: 32px; height: 18px\" >6</td>";
            E += "                    <td id=\"_eos_calendar_day12\" style=\"width: 32px; height: 18px\" >7</td>";
            E += "                    <td id=\"_eos_calendar_day13\" style=\"width: 32px; height: 18px\" >8</td>";
            E += "                </tr>";
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day14\" style=\"width: 32px; height: 18px\" >9</td>";
            E += "                    <td id=\"_eos_calendar_day15\" style=\"width: 32px; height: 18px\" >10</td>";
            E += "                    <td id=\"_eos_calendar_day16\" style=\"width: 32px; height: 18px\" >11</td>";
            E += "                    <td id=\"_eos_calendar_day17\" style=\"width: 32px; height: 18px\" >12</td>";
            E += "                    <td id=\"_eos_calendar_day18\" style=\"width: 32px; height: 18px\" >13</td>";
            E += "                    <td id=\"_eos_calendar_day19\" style=\"width: 32px; height: 18px\" >14</td>";
            E += "                    <td id=\"_eos_calendar_day20\" style=\"width: 32px; height: 18px\" >15</td>";
            E += "                </tr>";
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day21\" style=\"width: 32px; height: 18px\" >16</td>";
            E += "                    <td id=\"_eos_calendar_day22\" style=\"width: 32px; height: 18px\" >17</td>";
            E += "                    <td id=\"_eos_calendar_day23\" style=\"width: 32px; height: 18px\" >18</td>";
            E += "                    <td id=\"_eos_calendar_day24\" style=\"width: 32px; height: 18px\" >19</td>";
            E += "                    <td id=\"_eos_calendar_day25\" style=\"width: 32px; height: 18px\" >20</td>";
            E += "                    <td id=\"_eos_calendar_day26\" style=\"width: 32px; height: 18px\" >21</td>";
            E += "                    <td id=\"_eos_calendar_day27\" style=\"width: 32px; height: 18px\" >22</td>";
            E += "                </tr>";
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day28\" style=\"width: 32px; height: 18px\" >23</td>";
            E += "                    <td id=\"_eos_calendar_day29\" style=\"width: 32px; height: 18px\" >24</td>";
            E += "                    <td id=\"_eos_calendar_day30\" style=\"width: 32px; height: 18px\" >25</td>";
            E += "                    <td id=\"_eos_calendar_day31\" style=\"width: 32px; height: 18px\" >26</td>";
            E += "                    <td id=\"_eos_calendar_day32\" style=\"width: 32px; height: 18px\" >27</td>";
            E += "                    <td id=\"_eos_calendar_day33\" style=\"width: 32px; height: 18px\" >28</td>";
            E += "                    <td id=\"_eos_calendar_day34\" style=\"width: 32px; height: 18px\" >29</td>";
            E += "                </tr>";
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day35\" style=\"width: 32px; height: 18px\" >30</td>";
            E += "                    <td id=\"_eos_calendar_day36\" style=\"width: 32px; height: 18px\" >1</td>";
            E += "                    <td id=\"_eos_calendar_day37\" style=\"width: 32px; height: 18px\" >2</td>";
            E += "                    <td id=\"_eos_calendar_day38\" style=\"width: 32px; height: 18px\" >3</td>";
            E += "                    <td id=\"_eos_calendar_day39\" style=\"width: 32px; height: 18px\" >4</td>";
            E += "                    <td id=\"_eos_calendar_day40\" style=\"width: 32px; height: 18px\" >5</td>";
            E += "                    <td id=\"_eos_calendar_day41\" style=\"width: 32px; height: 18px\" >6</td>";
            E += "                </tr>"
        } else {
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day0\" style=\"width: 32px; height: 18px\" >25</td>";
            E += "                    <td id=\"_eos_calendar_day1\" style=\"width: 32px; height: 18px\" >26</td>";
            E += "                    <td id=\"_eos_calendar_day2\" style=\"width: 32px; height: 18px\" >27</td>";
            E += "                    <td id=\"_eos_calendar_day3\" style=\"width: 31px; height: 18px\" >28</td>";
            E += "                    <td id=\"_eos_calendar_day4\" style=\"width: 31px; height: 18px\" >29</td>";
            E += "                    <td id=\"_eos_calendar_day5\" style=\"width: 31px; height: 18px\" >30</td>";
            E += "                    <td id=\"_eos_calendar_day6\" style=\"width: 31px; height: 18px\" >1</td>";
            E += "                </tr>";
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day7\" style=\"width: 32px; height: 18px\" >2</td>";
            E += "                    <td id=\"_eos_calendar_day8\" style=\"width: 32px; height: 18px\" >3</td>";
            E += "                    <td id=\"_eos_calendar_day9\" style=\"width: 32px; height: 18px\" >4</td>";
            E += "                    <td id=\"_eos_calendar_day10\" style=\"width: 31px; height: 18px\" >5</td>";
            E += "                    <td id=\"_eos_calendar_day11\" style=\"width: 31px; height: 18px\" >6</td>";
            E += "                    <td id=\"_eos_calendar_day12\" style=\"width: 31px; height: 18px\" >7</td>";
            E += "                    <td id=\"_eos_calendar_day13\" style=\"width: 31px; height: 18px\" >8</td>";
            E += "                </tr>";
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day14\" style=\"width: 32px; height: 18px\" >9</td>";
            E += "                    <td id=\"_eos_calendar_day15\" style=\"width: 32px; height: 18px\" >10</td>";
            E += "                    <td id=\"_eos_calendar_day16\" style=\"width: 32px; height: 18px\" >11</td>";
            E += "                    <td id=\"_eos_calendar_day17\" style=\"width: 31px; height: 18px\" >12</td>";
            E += "                    <td id=\"_eos_calendar_day18\" style=\"width: 31px; height: 18px\" >13</td>";
            E += "                    <td id=\"_eos_calendar_day19\" style=\"width: 31px; height: 18px\" >14</td>";
            E += "                    <td id=\"_eos_calendar_day20\" style=\"width: 31px; height: 18px\" >15</td>";
            E += "                </tr>";
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day21\" style=\"width: 32px; height: 18px\" >16</td>";
            E += "                    <td id=\"_eos_calendar_day22\" style=\"width: 32px; height: 18px\" >17</td>";
            E += "                    <td id=\"_eos_calendar_day23\" style=\"width: 32px; height: 18px\" >18</td>";
            E += "                    <td id=\"_eos_calendar_day24\" style=\"width: 31px; height: 18px\" >19</td>";
            E += "                    <td id=\"_eos_calendar_day25\" style=\"width: 31px; height: 18px\" >20</td>";
            E += "                    <td id=\"_eos_calendar_day26\" style=\"width: 31px; height: 18px\" >21</td>";
            E += "                    <td id=\"_eos_calendar_day27\" style=\"width: 31px; height: 18px\" >22</td>";
            E += "                </tr>";
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day28\" style=\"width: 32px; height: 18px\" >23</td>";
            E += "                    <td id=\"_eos_calendar_day29\" style=\"width: 32px; height: 18px\" >24</td>";
            E += "                    <td id=\"_eos_calendar_day30\" style=\"width: 32px; height: 18px\" >25</td>";
            E += "                    <td id=\"_eos_calendar_day31\" style=\"width: 31px; height: 18px\" >26</td>";
            E += "                    <td id=\"_eos_calendar_day32\" style=\"width: 31px; height: 18px\" >27</td>";
            E += "                    <td id=\"_eos_calendar_day33\" style=\"width: 31px; height: 18px\" >28</td>";
            E += "                    <td id=\"_eos_calendar_day34\" style=\"width: 31px; height: 18px\" >29</td>";
            E += "                </tr>";
            E += "                <tr>";
            E += "                    <td id=\"_eos_calendar_day35\" style=\"width: 32px; height: 18px\" >30</td>";
            E += "                    <td id=\"_eos_calendar_day36\" style=\"width: 32px; height: 18px\" >1</td>";
            E += "                    <td id=\"_eos_calendar_day37\" style=\"width: 32px; height: 18px\" >2</td>";
            E += "                    <td id=\"_eos_calendar_day38\" style=\"width: 31px; height: 18px\" >3</td>";
            E += "                    <td id=\"_eos_calendar_day39\" style=\"width: 31px; height: 18px\" >4</td>";
            E += "                    <td id=\"_eos_calendar_day40\" style=\"width: 31px; height: 18px\" >5</td>";
            E += "                    <td id=\"_eos_calendar_day41\" style=\"width: 31px; height: 18px\" >6</td>";
            E += "                </tr>"
        }
        E += "            </table>";
        E += "            </div></td>";
        E += "        </tr>";
        E += "        <tr id=\"_eos_calendar_time_container\">";
        E += "            <td style=\"height: 26px\" align=\"left\" class=\"eos-calendar-time\">";
        E += "              <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tr><td width=\"150px\"><div style=\"float:left\" align=\"left\" id=\"_eos_calendar_time_editor_container\"></div></td>";
        E += "              <td align=\"right\">";
        E += "              </td></tr></table>";
        E += "            </td>";
        E += "        </tr>";
        E += "        <tr>";
        E += "            <td class=\"eos-calendar-bottom\" style=\"height: 30px\" align=\"right\">";
        E += "<div  id=\"_eos_calendar_ok\" class="+eButton+" onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">" + CALENDAR_BUTTON_OK + "</div></div>&nbsp;";
        E += "<div  id=\"_eos_calendar_today\" class="+eButton+" onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">" + CALENDAR_TODAY + "</div></div>&nbsp;";
        E += "<div  id=\"_eos_calendar_clear\" class="+eButton+" onmouseover=\"buttonMouseOver(this)\" onmouseout=\"buttonMouseOut(this)\" onmousedown=\"buttonMouseDown(this)\" onmouseup=\"buttonMouseUp(this)\"><div align=\"center\" class=\"eos-button-inner\">" + CLEAR + "</div></div>";
        E += "            </td>";
        E += "        </tr>        ";
        E += "    </table>";
        E += "    </div>";
        E += "</div>";
        var A = $create("div", CalendarEditor.doc);
        A.onmousedown = function() {
            eventManager.stopPropagation()
        };
        A.style.height = "222px";
        A.style.width = "228px";
        A.style.position = "absolute";
        A.id = "_eos_webcomp_calendar_container";
        A.innerHTML = E;
        A.style.display = "none";
        CalendarEditor.container = A;
        function D() {
            CalendarEditor.doc.body.appendChild(A);
            CalendarEditor.container.onkeydown = function() {
                var A = eventManager.getKeyCode();
                if (A == 13) if (CalendarEditor.showTime) CalendarEditor.onOk();
                if (A == 27) CalendarEditor.hideEditor()
            }
        }
        try {
            D()
        } catch(F) {
            CalendarEditor.win.eventManager.add(window, "load", D)
        }
        if (document != CalendarEditor.doc) moveCss(document, CalendarEditor.doc);
        if (CalendarEditor.win["createTimeSelect"]) {
            var C = CalendarEditor.win["createTimeSelect"]("_eos_calendar_time_editor");
            CalendarEditor.setTimeSelect(C);
            C.init()
        }
    }
    CalendarEditor.okBtn = $id("_eos_calendar_ok", CalendarEditor.doc);
    CalendarEditor.okBtn.onclick = CalendarEditor.onOk;
    CalendarEditor.lastYear = $id("_eos_calendar_last_year", CalendarEditor.doc);
    CalendarEditor.nextYear = $id("_eos_calendar_next_year", CalendarEditor.doc);
    CalendarEditor.year = $id("_eos_calendar_year", CalendarEditor.doc);
    CalendarEditor.month = $id("_eos_calendar_month", CalendarEditor.doc);
    CalendarEditor.lastMonth = $id("_eos_calendar_last_month", CalendarEditor.doc);
    CalendarEditor.nextMonth = $id("_eos_calendar_next_month", CalendarEditor.doc);
    CalendarEditor.today = $id("_eos_calendar_today", CalendarEditor.doc);
    CalendarEditor.clear = $id("_eos_calendar_clear", CalendarEditor.doc);
    CalendarEditor.body = $id("_eos_calendar_editor_body", CalendarEditor.doc);
    CalendarEditor.timeContainer = $id("_eos_calendar_time_container", CalendarEditor.doc);
    CalendarEditor.timeSelect = CalendarEditor.getTimeSelect();
    CalendarEditor.lastYear.onmouseover = function() {
        this.src = PICTURE_ARROW_LEFT_OVER
    };
    CalendarEditor.lastYear.onmouseout = function() {
        this.src = PICTURE_ARROW_LEFT
    };
    CalendarEditor.nextYear.onmouseover = function() {
        this.src = PICTURE_ARROW_RIGHT_OVER
    };
    CalendarEditor.nextYear.onmouseout = function() {
        this.src = PICTURE_ARROW_RIGHT
    };
    CalendarEditor.lastMonth.onmouseover = function() {
        this.src = PICTURE_ARROW_LEFT_OVER
    };
    CalendarEditor.lastMonth.onmouseout = function() {
        this.src = PICTURE_ARROW_LEFT
    };
    CalendarEditor.nextMonth.onmouseover = function() {
        this.src = PICTURE_ARROW_RIGHT_OVER
    };
    CalendarEditor.nextMonth.onmouseout = function() {
        this.src = PICTURE_ARROW_RIGHT
    };
    if (isFF) {
        buttonForFF(CalendarEditor.okBtn);
        buttonForFF(CalendarEditor.today);
        buttonForFF(CalendarEditor.clear)
    }
};
function calendarUnload() {
    try {
        CalendarEditor.hideEditor()
    } catch(B) {
        var A = $id("_eos_webcomp_calendar_container", CalendarEditor.doc);
        A.style.display = "none"
    }
}
eventManager.add(window, "unload", calendarUnload);
CalendarEditor.initEvent = function() {};
CalendarEditor.setTimeSelect = function(A) {
    CalendarEditor.win["_eos_calendar_time_select"] = A
};
CalendarEditor.getTimeSelect = function() {
    return CalendarEditor.win["_eos_calendar_time_select"]
};
CalendarEditor.showEditor = function() {
    if (CalendarEditor.showTime) {
        CalendarEditor.timeContainer.style.display = "";
        CalendarEditor.okBtn.style.display = "";
        if (isFF) {
            CalendarEditor.container.style.height = "226px";
            CalendarEditor.container.firstChild.style.height = "226px"
        }
    } else {
        CalendarEditor.timeContainer.style.display = "none";
        CalendarEditor.okBtn.style.display = "none";
        if (isFF) {
            CalendarEditor.container.style.height = "200px";
            CalendarEditor.container.firstChild.style.height = "200px"
        }
    }
    CalendarEditor.initCalendarEvent();
    CalendarEditor.freshDate(CalendarEditor.value);
    CalendarEditor.freshTimeComp();
    CalendarEditor.showStatus = true;
    CalendarEditor.container.style.display = "";
    var A = $id("_eos_calendar_editor_container", CalendarEditor.doc);
    initShadow(A, CalendarEditor.doc)
};
CalendarEditor.initCalendarEvent = function() {
    CalendarEditor.lastYear.onclick = function() {
        CalendarEditor.changeYear(CalendarEditor.year.value - 1)
    };
    CalendarEditor.nextYear.onclick = function() {
        CalendarEditor.changeYear(CalendarEditor.year.value * 1 + 1)
    };
    CalendarEditor.year.onkeyup = function() {
        CalendarEditor.changeYear(CalendarEditor.year.value)
    };
    CalendarEditor.year.onblur = function() {
        CalendarEditor.changeYear(CalendarEditor.year.value)
    };
    CalendarEditor.month.onkeyup = function() {
        CalendarEditor.changeMonth(CalendarEditor.month.value)
    };
    CalendarEditor.month.onblur = function() {
        CalendarEditor.changeMonth(CalendarEditor.month.value)
    };
    CalendarEditor.lastMonth.onclick = function() {
        CalendarEditor.changeMonth(dateToStringValue(CalendarEditor.month.value - 1))
    };
    CalendarEditor.nextMonth.onclick = function() {
        CalendarEditor.changeMonth(dateToStringValue(CalendarEditor.month.value * 1 + 1))
    };
    CalendarEditor.today.onclick = function() {
        CalendarEditor.returnValue(new Date())
    };
    CalendarEditor.clear.onclick = function() {
        var A = CalendarEditor.getCalendar();
        if (A) {
            A.inputObject.value = "";
            A.hiddenObject.value = "";
            A.value = null
        }
        CalendarEditor.hideEditor()
    }
};
CalendarEditor.hideEditor = function() {
    if (CalendarEditor.container) {
        CalendarEditor.container.style.display = "none";
        CalendarEditor.showStatus = false
    }
};
CalendarEditor.setPosition = function(E, B, C, D) {
    CalendarEditor.container.style.position = "absolute";
    var F = getMaxZindex(CalendarEditor.doc);
    if (CalendarEditor.container.style.zIndex != F) CalendarEditor.container.style.zIndex = F;
    var A;
    if (isIE) A = CalendarEditor.doc.body.offsetHeight + CalendarEditor.doc.body.scrollTop;
    else A = CalendarEditor.win.screen.availHeight + CalendarEditor.doc.body.scrollTop;
    if ((B + 228) > A) if (CalendarEditor.showTime) {
        if (B - 258 >= 0) B = B - 258
    } else if (B - 228 >= 0) B = B - 228;
    CalendarEditor.container.style.left = E;
    CalendarEditor.container.style.top = B
};
CalendarEditor.reFreshEditor = function(A) {
    CalendarEditor.maxValue = A.maxValue;
    CalendarEditor.minValue = A.minValue;
    CalendarEditor.format = A.format;
    CalendarEditor.value = A.value;
    CalendarEditor.showTime = A.showTime
};
CalendarEditor.setCurrEditor = function(A) {
    CalendarEditor.win["_eos_curr_calendar"] = A
};
CalendarEditor.getCalendar = function(A) {
    return CalendarEditor.win["_eos_curr_calendar"]
};
CalendarEditor.getMaxYear = function() {
    if (isDate(CalendarEditor.maxValue, CalendarEditor.format)) {
        var A = stringToDate(CalendarEditor.maxValue, CalendarEditor.format);
        return A.getFullYear()
    }
    return CalendarEditor.MAX_YEAR
};
CalendarEditor.getMaxMonth = function() {
    if (isDate(CalendarEditor.maxValue, CalendarEditor.format)) {
        var A = stringToDate(CalendarEditor.maxValue, CalendarEditor.format);
        return A.getMonth()
    }
    return 11
};
CalendarEditor.getMaxDay = function() {
    if (isDate(CalendarEditor.maxValue, CalendarEditor.format)) {
        var A = stringToDate(CalendarEditor.maxValue, CalendarEditor.format);
        return A.getDate()
    }
    return 31
};
CalendarEditor.getMinYear = function() {
    if (isDate(CalendarEditor.minValue, CalendarEditor.format)) {
        var A = stringToDate(CalendarEditor.minValue, CalendarEditor.format);
        return A.getFullYear()
    }
    return CalendarEditor.MIN_YEAR
};
CalendarEditor.getMinMonth = function() {
    if (isDate(CalendarEditor.minValue, CalendarEditor.format)) {
        var A = stringToDate(CalendarEditor.minValue, CalendarEditor.format);
        return A.getMonth()
    }
    return 0
};
CalendarEditor.getMinDay = function() {
    if (isDate(CalendarEditor.minValue, CalendarEditor.format)) {
        var A = stringToDate(CalendarEditor.minValue, CalendarEditor.format);
        return A.getDate()
    }
    return 1
};
CalendarEditor.isHoliday = function(B, A, D) {
    var C = new Date(B, A, D);
    C.setFullYear(B);
    return (C.getDay() == 6 || C.getDay() == 0)
};
CalendarEditor.freshTimeComp = function() {
    if (!CalendarEditor.showTime) return;
    var A = CalendarEditor.value.getHours(),
    B = CalendarEditor.value.getMinutes(),
    C = CalendarEditor.value.getSeconds();
    CalendarEditor.timeSelect.setHours(A);
    CalendarEditor.timeSelect.setMinutes(B);
    CalendarEditor.timeSelect.setSeconds(C)
};
CalendarEditor.refreshTime = function(A) {
    if (CalendarEditor.showTime) {
        A.setHours(CalendarEditor.timeSelect.getHours());
        A.setMinutes(CalendarEditor.timeSelect.getMinutes());
        A.setSeconds(CalendarEditor.timeSelect.getSeconds())
    }
    return A
};
CalendarEditor.freshHeader = function() {
    CalendarEditor.month.value = dateToStringValue(CalendarEditor.value.getMonth() + 1);
    CalendarEditor.year.value = CalendarEditor.value.getFullYear()
};
CalendarEditor.changeMonth = function(A) {
    if (isNaN(A)) return;
    if (A == "") return;
    if (A.length != 2) return;
    A = A - 1;
    var H = CalendarEditor.getMaxMonth(),
    C = CalendarEditor.getMinMonth(),
    D = CalendarEditor.value.getFullYear(),
    G = CalendarEditor.getMaxYear(),
    B = CalendarEditor.getMinYear();
    if (A > H && D == G) A = H;
    if (A < C && D == B) A = C;
    var F = CalendarEditor.value.getDate(),
    E = new Date(D, A, F);
    E.setFullYear(D);
    E = CalendarEditor.refreshTime(E);
    CalendarEditor.freshDate(dateToString(E, CalendarEditor.format))
};
CalendarEditor.changeYear = function(A) {
    if (isNaN(A)) return;
    if (A.length <= 3) return;
    var F = CalendarEditor.getMaxYear(),
    B = CalendarEditor.getMinYear();
    if (A > F) A = F;
    if (A < B) A = B;
    var C = CalendarEditor.value.getMonth(),
    E = CalendarEditor.value.getDate();
    if (C == 1 && E == 29) if (A % 4 != 0) E = 28;
    var D = new Date(A, C, E);
    D.setFullYear(A);
    D = CalendarEditor.refreshTime(D);
    CalendarEditor.freshDate(dateToString(D, CalendarEditor.format))
};
CalendarEditor.onClick = function(A) {
    if (A.innerHTML != "") {
        var B = A.value;
        if (CalendarEditor.showTime) {
            if (CalendarEditor.currCell) removeClass(CalendarEditor.currCell, "eos-calendar-editor-currday");
            addClass(A, "eos-calendar-editor-currday");
            CalendarEditor.currCell = A;
            CalendarEditor.value = B
        } else CalendarEditor.returnValue(B)
    }
};
CalendarEditor.onOk = function() {
    if (CalendarEditor.value != null) CalendarEditor.returnValue(CalendarEditor.value)
};
CalendarEditor.outClick = function(A) {
    try {
        CalendarEditor.hideEditor()
    } catch(B) {}
};
CalendarEditor.returnValue = function(_date) {
    _date = CalendarEditor.refreshTime(_date);
    if (isDate(CalendarEditor.maxValue, CalendarEditor.format)) {
        var maxDate = stringToDate(CalendarEditor.maxValue, CalendarEditor.format);
        if (_date - maxDate > 0) return
    }
    if (isDate(CalendarEditor.minValue, CalendarEditor.format)) {
        var minDate = stringToDate(CalendarEditor.minValue, CalendarEditor.format);
        if (_date - minDate < 0) return
    }
    var calendar = CalendarEditor.getCalendar();
    if (calendar) {
        calendar.setValue(_date);
        if (calendar.onSelectFunc) {
            try {
                eval(calendar.onSelectFunc + "(_date);")
            } catch(e) {
                alert(e)
            }
        }
        calendar.validate()
    }
    CalendarEditor.hideEditor()
};
CalendarEditor.hideTime = function() {};
CalendarEditor.isShow = function() {
    return CalendarEditor.showStatus
};
CalendarEditor.freshDate = function(L) {
    var V = new Array(31, 30, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31),
    T = new Date();
    if (isDate(L, CalendarEditor.format)) T = stringToDate(L, CalendarEditor.format);
    var O = null,
    N = null;
    if (isDate(CalendarEditor.maxValue, CalendarEditor.format)) {
        O = stringToDate(CalendarEditor.maxValue, CalendarEditor.format);
        if (T - O > 0) T = O
    }
    if (isDate(CalendarEditor.minValue, CalendarEditor.format)) {
        N = stringToDate(CalendarEditor.minValue, CalendarEditor.format);
        if (T - N < 0) T = N
    }
    CalendarEditor.value = T;
    CalendarEditor.freshHeader();
    var Q = T.getFullYear(),
    G = T.getMonth(),
    B = 1,
    M = new Date(Q, G, 1).getDay(),
    D = G == 0 ? Q - 1 : Q,
    S = G == 0 ? 11 : G - 1,
    U = V[S];
    if (S == 1) U = ((D % 4 == 0) && (D % 100 != 0) || (D % 400 == 0)) ? 29 : 28;
    U -= M - 1;
    var K = 1;
    V[1] = ((Q % 4 == 0) && (Q % 100 != 0) || (Q % 400 == 0)) ? 29 : 28;
    for (i = 0; i < 42; i++) {
        var P = $id("_eos_calendar_day" + i, CalendarEditor.doc);
        P.onmouseover = function() {
            addClass(this, "eos-calendar-editor-overday")
        };
        P.onmouseout = function() {
            removeClass(this, "eos-calendar-editor-overday")
        };
        P.onclick = function() {
            CalendarEditor.onClick(this)
        };
        P.className = "eos-calendar-editor-day";
        if (i < M) {
            var J = new Date(Q, G - 1, U);
            P.innerHTML = U;
            P.title = dateToString(J, CALENDAR_DEFAULT_FORMAT);
            P.value = J;
            P.className = "eos-calendar-editor-moreday";
            U++
        } else if (B > V[G]) {
            var I = new Date(Q, G + 1, K);
            P.innerHTML = K;
            P.title = dateToString(I, CALENDAR_DEFAULT_FORMAT);
            P.value = I;
            P.className = "eos-calendar-editor-moreday";
            K++
        } else if (i >= new Date(Q, G, 1).getDay() && B <= V[G]) {
            var R = new Date(Q, G, B);
            R.setFullYear(Q);
            P.title = dateToString(R, CALENDAR_DEFAULT_FORMAT);
            P.value = R;
            P.innerHTML = B;
            var C = new Date(),
            F = P.value;
            if (CalendarEditor.isHoliday(Q, G, B)) P.className = "eos-calendar-editor-holiday";
            B++
        } else {
            P.innerHTML = "";
            P.title = ""
        }
        if (P.value) {
            C = new Date();
            if (P.value.getYear() == C.getYear() && P.value.getMonth() == C.getMonth() && P.value.getDate() == C.getDate()) {
                addClass(P, "eos-calendar-editor-today");
                P.onmouseover = null;
                P.onmouseout = null
            }
            var E = CalendarEditor.value;
            if (P.value.getYear() == E.getYear() && P.value.getMonth() == E.getMonth() && P.value.getDate() == E.getDate()) {
                addClass(P, "eos-calendar-editor-currday");
                CalendarEditor.currCell = P;
                P.onmouseover = null;
                P.onmouseout = null
            } else removeClass(P, "eos-calendar-editor-currday");
            var A = false;
            if (O != null) if (P.value - O > 0) A = true;
            if (N != null) {
                var H = CalendarEditor.refreshTime(P.value);
                if (H - N < 0) A = true
            }
            if (A) {
                P.onclick = null;
                P.onmouseover = null;
                P.onmouseout = null;
                P.title = ""
            }
        }
    }
};
function createTimeSelect(B) {
    var A = new TimeSelect(B);
    return A
}
function f_check_calendar(B) {
    if (B.id) {
        var C = B.id;
        if (C.indexOf("_input") > 0) {
            C = C.replace("_input", "");
            var A = $id(C);
            if (A) return A.validate()
        }
    }
    return true
}
function LookUp(A) {
    this.id = A;
    PageControl.add(A, this);
    this.value = null;
    this.lookupBtn = null;
    this.lookupText = null;
    this.lookupUrl = null;
    this.lookupParam = null;
    this.lookupHidden = null;
    this.width = 300;
    this.height = 200;
    this.left = null;
    this.top = null;
    this.center = true;
    this.buttonImg = null;
    this.params = [];
    this.displayValue = null;
    this.container = null;
    this.onReturnFunc = null;
    this.useIeModel = false;
    this.dialogTitle = null;
    this.disabled = false;
    this.allowInput = true;
    this.readOnly = false
}
LookUp.prototype.init = function() {
    this.container = $id(this.id + "_container");
    this.lookupHidden = $id(this.id + "_hidden");
    this.lookupText = $id(this.id + "_input");
    this.lookupBtn = $id(this.id + "_button");
    this.button = this.lookupBtn;
    this.text = this.lookupText;
    this.hidden = this.lookupHidden;
    if (this.lookupWidth != null) this.text.style.width = this.lookupWidth;
    var B = this;
    this.lookupBtn.src = contextPath + "/common/skins/skin0/images/lookup/lookup_button.gif";
    this.lookupBtn.onmouseover = function() {
        if (B.getDisabled() || B.getReadOnly()) return;
        this.src = contextPath + "/common/skins/skin0/images/lookup/lookup_button_over.gif"
    };
    this.lookupBtn.onmouseout = function() {
        if (B.getDisabled() || B.getReadOnly()) return;
        this.src = contextPath + "/common/skins/skin0/images/lookup/lookup_button.gif"
    };
    this.lookupBtn.onmousedown = function() {
        if (B.getDisabled() || B.getReadOnly()) return;
        this.src = contextPath + "/common/skins/skin0/images/lookup/lookup_button_down.gif"
    };
    this.lookupBtn.onmouseup = function() {
        if (B.getDisabled() || B.getReadOnly()) return;
        B.lookupBtn.src = contextPath + "/common/skins/skin0/images/lookup/lookup_button.gif";
        B.show()
    };
    function A() {
        if (B.lookupText.value != B.displayValue) {
            B.displayValue = B.lookupText.value;
            B.value = B.lookupText.value;
            B.lookupHidden.value = B.value
        }
    }
    this.setReadOnly(this.readOnly);
    this.setDisabled(this.disabled);
    eventManager.add(this.lookupText, "keyup", A)
};
LookUp.prototype.setReadOnly = function(A) {
    this.readOnly = A;
    this.lookupText.readOnly = A || !this.allowInput;
    if (A) this.lookupBtn.style.cursor = "default";
    else this.lookupBtn.style.cursor = "pointer"
};
LookUp.prototype.getReadOnly = function() {
    return this.readOnly
};
LookUp.prototype.getValue = function() {
    this.refreshValue();
    return this.value
};
LookUp.prototype.setValue = function(A) {
    this.value = A;
    this.displayValue = A;
    this.refreshInput()
};
LookUp.prototype.setDisplayValue = function(A) {
    this.displayValue = A;
    this.refreshInput()
};
LookUp.prototype.setFocus = function() {};
LookUp.prototype.lostFocus = function() {};
LookUp.prototype.show = function() {
    var lookup = this;
    if (this.disabled) return;
    if (lookup.beforeOpenDialog) if (lookup.beforeOpenDialog(lookup) === false) return;
    this.refreshValue();
    var urlStr = this.getParamURL(),
    argument = [this.value, this.displayValue];
    function callBack(returnValue) {
        try {
            if (lookup.onReturnFunc) {
                var func = lookup.onReturnFunc;
                if ((typeof lookup.onReturnFunc) == "string") func = eval(lookup.onReturnFunc);
                if (func(returnValue)) {
                    lookup.value = returnValue[0];
                    lookup.displayValue = returnValue[1]
                }
            } else {
                lookup.value = returnValue[0];
                lookup.displayValue = returnValue[1]
            }
        } catch(e) {
            $handle(e);
            $error("returnValue of dialog is not a array")
        }
        lookup.refreshInput()
    }
    if (this.useIeModel) {
        var retValue = window.showModalDialog(urlStr, argument, "width:" + this.width + ";" + "height:" + this.height + ";" + "left:" + this.left + ";" + "top:" + this.top + ";");
        callBack(retValue)
    } else showModal(urlStr, argument, callBack, this.width, this.height, this.left || "", this.top || "", this.dialogTitle)
};
LookUp.prototype.refreshInput = function() {
    var A = this.displayValue !== null && this.displayValue !== undefined ? this.displayValue: this.value;
    this.lookupHidden.value = this.value;
    this.lookupText.value = A
};
LookUp.prototype.refreshValue = function() {
    this.value = this.lookupHidden.value;
    this.displayValue = this.lookupText.value
};
LookUp.prototype.getParamURL = function() {
    var B = "";
    for (var A = 0; A < this.params.length; A++) {
        var C = this.params[A];
        B += "&" + C.key + "=" + C.value
    }
    var D = addContextPath(this.lookupUrl);
    if (D.indexOf("?") > -1) D += B;
    else D += "?" + B.replace("&", "");
    return D
};
LookUp.prototype.addParam = function(A, B) {
    this.params.push({
        key: A,
        value: encodeURIComponent(B)
    })
};
LookUp.prototype.clearParam = function() {
    this.params = []
};
LookUp.prototype.setDisabled = function(A) {
    this.disabled = A;
    if (A) {
        this.lookupText.disabled = true;
        this.lookupHidden.disabled = true;
        this.lookupBtn.style.cursor = "default"
    } else {
        this.lookupText.disabled = false;
        this.lookupHidden.disabled = false;
        this.lookupBtn.style.cursor = "pointer"
    }
};
LookUp.prototype.getDisabled = function() {
    return this.disabled
};
LookUp.prototype.setPosition = function(D, A, B, C) {
    if (this.container) {
        this.container.style.display = "";
        this.container.style.position = "absolute";
        this.container.style.left = D;
        this.container.style.top = A;
        var E = getMaxZindex(document);
        if (this.container.style.zIndex != E) this.container.style.zIndex = E;
        this.lookupText.style.width = B - 17;
        this.lookupText.style.height = C;
        this.container.style.width = B;
        this.container.style.height = C;
        this.lookupBtn.style.height = C
    }
};
LookUp.prototype.hideEditor = function() {
    this.container.style.display = "none";
    this.lookupBtn.style.display = "none"
};
LookUp.prototype.showEditor = function() {
    this.container.style.display = "";
    this.lookupBtn.style.display = ""
};
LookUp.prototype.validate = function() {
    return true
};
LookUp.prototype.isFocus = function() {
    return false
};
LookUp.prototype.getDisplayValue = function(A) {
    if (A == this.value) return this.displayValue;
    return A
};
function MultiBox(D) {
    this.id = D;
    this.value = null;
    this.container = $id(this.id + "_container");
    this.checkboxes = [];
    var C = this.container.getElementsByTagName("input");
    for (var A = 0; A < C.length; A++) {
        var B = C[A];
        if (B.type == "checkbox") this.checkboxes.push(B)
    }
    this.hiddenInput = null;
    this.splitChar = ",";
    this.jsonObj = null;
    PageControl.add(D, this)
}
MultiBox.prototype.init = function() {
    this.hiddenInput = $id(this.id + "_hidden");
    this.initEvent()
};
MultiBox.prototype.initEvent = function() {
    var C = this;
    function D() {
        C.refreshValue()
    }
    function E() {
        var F = eventManager.getEvent(),
        B = F.keyCode;
        if (! (B == 37 || B == 39)) return;
        var A = C.checkboxes;
        for (var D = 0; D < A.length; D++) {
            var E = A[D];
            if ((isIE ? document.activeElement: F.explicitOriginalTarget) == E && E.type == "checkbox") {
                if (B == 37) if (D == 0) A[A.length - 1].focus();
                else A[D - 1].focus();
                if (B == 39) if (D == A.length - 1) A[0].focus();
                else A[D + 1].focus();
                break
            }
        }
    }
    for (var A = 0; A < this.checkboxes.length; A++) {
        var B = this.checkboxes[A];
        eventManager.add(B, "click", D)
    }
    eventManager.add(this.container, "keyup", E)
};
MultiBox.prototype.refreshInput = function() {
    if (this.hiddenInput) this.hiddenInput.value = this.value;
    str = this.splitChar + this.value + this.splitChar;
    for (var A = 0; A < this.checkboxes.length; A++) {
        var B = this.checkboxes[A],
        C = this.splitChar + B.value + this.splitChar;
        if (str.indexOf(C) > -1) B.checked = true;
        else B.checked = false
    }
};
MultiBox.prototype.refreshValue = function() {
    var C = "";
    for (var A = 0; A < this.checkboxes.length; A++) {
        var B = this.checkboxes[A];
        if (B.checked && B.type == "checkbox") C += B.value + this.splitChar
    }
    if (C.length > 1) C = C.substr(0, C.length - 1);
    this.value = C;
    if (this.hiddenInput) this.hiddenInput.value = C
};
MultiBox.prototype.setValue = function(A) {
    this.value = A;
    this.refreshInput()
};
MultiBox.prototype.getValue = function() {
    this.refreshValue();
    return this.value
};
MultiBox.prototype.setFocus = function() {
    this.checkboxes[0].focus()
};
MultiBox.prototype.lostFocus = function() {};
MultiBox.prototype.showEditor = function() {
    var A = getMaxZindex();
    this.container.style.zIndex = A;
    this.container.style.display = "";
    addClass(this.container.firstChild, "dict_comp");
    if (this.isDcEdit == true) {
        var B = this.container.firstChild;
        B.style.width = B.firstChild.offsetWidth;
        initShadow(B)
    }
    this.setFocus()
};
MultiBox.prototype.hideEditor = function() {
    this.container.style.display = "none"
};
MultiBox.prototype.setPosition = function(D, B, A, C) {
    this.container.style.position = "absolute";
    this.container.zIndex = 9999;
    this.container.style.left = D + "px";
    this.container.style.top = B + "px"
};
MultiBox.prototype.isFocus = function() {
    return true
};
MultiBox.prototype.validate = function() {
    return true
};
EOS_DICT_DISPLAY_SEPERATOR = null;
MultiBox.prototype.getDisplayValue = function(D) {
    if (D == null) D = this.getValue();
    var B;
    if (EOS_DICT_DISPLAY_SEPERATOR) B = EOS_DICT_DISPLAY_SEPERATOR;
    else B = this.splitChar;
    var E = D.split(B),
    F = "",
    A = [];
    for (var C = 0; C < E.length; C++) A.push(this.jsonObj[E[C]] || E[C]);
    F = A.join(B);
    return F
};
function MultiSelect(A) {
    this.id = A;
    this.container = $id(this.id + "_container");
    this.container.onmousedown = function() {
        eventManager.stopPropagation()
    };
    this.value = null;
    this.select = $id(this.id + "_select");
    this.hiddenInput = null;
    this.splitChar = ",";
    this.jsonObj = null;
    PageControl.add(A, this)
}
MultiSelect.prototype.init = function() {
    this.hiddenInput = $id(this.id + "_hidden");
    this.initEvent()
};
MultiSelect.prototype.initEvent = function() {
    var A = this;
    function B() {
        A.refreshValue()
    }
    eventManager.add(this.select, "change", B)
};
MultiSelect.prototype.refreshInput = function() {
    str = this.value + this.splitChar;
    if (this.hiddenInput) this.hiddenInput.value = this.value;
    for (var A = 0; A < this.select.options.length; A++) {
        var B = this.select.options[A],
        C = B.value + this.splitChar;
        if (str.indexOf(C) > -1) B.selected = true;
        else B.selected = false
    }
};
MultiSelect.prototype.refreshValue = function() {
    var C = "";
    for (var A = 0; A < this.select.options.length; A++) {
        var B = this.select.options[A];
        if (B.selected) C += B.value + this.splitChar
    }
    if (C.length > 1) C = C.substr(0, C.length - 1);
    this.value = C;
    if (this.hiddenInput) this.hiddenInput.value = C
};
MultiSelect.prototype.setValue = function(A) {
    this.hiddenInput.value = A;
    this.refreshInput()
};
MultiSelect.prototype.getValue = function() {
    this.refreshValue();
    return this.value
};
MultiSelect.prototype.setFocus = function() {};
MultiSelect.prototype.lostFocus = function() {};
MultiSelect.prototype.showEditor = function() {
    var A = getMaxZindex();
    this.container.style.zIndex = A;
    this.container.style.display = ""
};
MultiSelect.prototype.hideEditor = function() {
    this.container.style.display = "none"
};
MultiSelect.prototype.setPosition = function(D, B, A, C) {
    this.container.style.position = "absolute";
    this.container.zIndex = 9999;
    this.container.style.left = D + "px";
    this.container.style.top = B + "px";
    this.container.style.width = A + "px"
};
MultiSelect.prototype.isFocus = function() {
    return true
};
MultiSelect.prototype.validate = function() {
    return true
};
EOS_DICT_DISPLAY_SEPERATOR = null;
MultiSelect.prototype.getDisplayValue = function(C) {
    if (C == null) C = this.getValue();
    var A;
    if (EOS_DICT_DISPLAY_SEPERATOR) A = EOS_DICT_DISPLAY_SEPERATOR;
    else A = this.splitChar;
    var D = C.split(A),
    E = "";
    for (var B = 0; B < D.length; B++) E = E + (this.jsonObj[D[B]] || D[B]) + A;
    if (E.length > 1) E = E.substr(0, E.length - 1);
    return E
};
function PopMenu(A) {
    this.id = A;
    PageControl.add(A, this);
    this.container = null;
    this.subMenuContainer = null;
    this.submenu = [];
    this.canClose = true;
    this.currMenu = null;
    this.level = 0;
    this.openLevel = [];
    this.menuContainer = null;
    this.doc = document;
    this.win = window;
    this.onClickFunc = null;
    this.args = null;
    this.isShow = false
}
PopMenu.prototype.init = function() {
    this.win = _get_top_window() || window;
    this.doc = this.win.document;
    var E = getDocumentId(document);
    this.container = $id(this.id + E + "_container", this.doc);
    if (!this.container) {
        this.container = $create("<div id='" + this.id + E + "_container' style='width:164px;overflow:hidden;position:absolute;display:none'></div>", this.doc);
        bodyAddNode(this.container, this.doc)
    }
    this.container.style.width = "164px";
    this.container.style.overflow = "hidden";
    var G = getMaxZindex(this.win.document);
    this.container.style.zIndex = G + 1;
    var C = $create("div", this.doc);
    this.menuContainer = $create("div", this.doc);
    if (isIE) this.menuContainer.innerHTML = "<iframe style=\"position:absolute;z-index:" + this.zIndex + ";width:expression(this.nextSibling.childNodes[0].offsetWidth);height:expression(this.nextSibling.childNodes[0].offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>";
    this.menuContainer.appendChild(this.container);
    var D = $create("ul", this.doc),
    A = $create("div", this.doc);
    this.shadowdiv = A;
    D.className = "eos-popmenu-list";
    this.container.appendChild(A);
    A.appendChild(D);
    A.className = "eos-popmenu";
    C.appendChild(this.menuContainer);
    bodyAddNode(C, this.doc);
    C.style.zIndex = G + 1;
    for (var B = 0; B < this.submenu.length; B++) {
        var F = this.submenu[B];
        D.appendChild(F.container);
        F.container.style.zIndex = G + 1;
        F.zIndex = G + 1;
        F.root = this;
        F.level = this.level + 1;
        F.init()
    }
    this.initEvent();
    this.openLevel[0] = this
};
PopMenu.prototype.appendChild = function(A) {
    this.submenu.push(A);
    A.parent = this
};
PopMenu.prototype.initEvent = function() {
    var B = this;
    function A() {
        B.hide(true)
    }
};
PopMenu.prototype.addObject = function(A) {
    if (!A) return;
    var B = this
};
PopMenu.prototype.show = function() {
    PageControl.setFocus(this);
    this.container.style.display = "";
    this.isShow = true;
    if (this.isShadowInit != true) {
        if (isFF) {
            initShadow(this.shadowdiv, this.doc);
            this.shadowdiv.style.width = this.shadowdiv.parentNode.offsetWidth - 7
        } else initShadowIe(this.shadowdiv, this.doc);
        this.isShadowInit = true
    }
};
PopMenu.prototype.setPosition = function(B, A) {
    this.container.style.left = B + "px";
    this.container.style.top = A + "px"
};
PopMenu.prototype.hide = function(C) {
    if (this.canClose || C) {
        for (var A = 0; A < this.submenu.length; A++) {
            var B = this.submenu[A];
            B.hide(C)
        }
        this.container.style.display = "none";
        this.isShow = false;
        return true
    } else return false
};
PopMenu.prototype.openByLevel = function(A) {
    $debug(A);
    for (var B = 0; B < this.openLevel.length; B++) {
        var C = this.openLevel[B];
        if (!C) continue;
        if (B <= A) C.show();
        else C.hide(true)
    }
};
function PopMenuItem(A) {
    this.id = A;
    PageControl.add(A, this);
    this.parent = null;
    this.submemu = [];
    this.win = _get_top_window() || window;
    this.doc = this.win.document;
    this.container = $create("li", this.doc);
    this.subMenuContainer = null;
    this.url = null;
    this.name = null;
    this.seperator = false;
    this.params = [];
    this.icon = null;
    this.canClose = true;
    this.currMenu = null;
    this.level = 0;
    this.root = null;
    this.onClickFunc = null;
    this.menuKey = null;
    this.onRefreshFunc = null;
    this.zIndex = 0
}
PopMenuItem.prototype.init = function() {
    if (this.seperator) {
        var A = "<span></span>";
        this.container.className = "eos-popmenu-line";
        this.container.innerHTML = A;
        if (isIE) this.container.style.height = "2px";
        else this.container.style.height = "1px"
    } else {
        this.initContainer();
        this.initEvent();
        this.initSubMenu()
    }
};
PopMenuItem.prototype.initSubMenu = function() {
    if (this.hasChild()) {
        var C = $create("div", this.doc);
        this.subMenuContainer = $create("div", this.doc);
        this.subMenuContainer.style.width = "164px";
        this.subMenuContainer.style.overflow = "hidden";
        if (isIE) C.innerHTML = "<iframe style=\"position:absolute;z-index:" + this.zIndex + ";width:expression(this.nextSibling.childNodes[0].offsetWidth);height:expression(this.nextSibling.childNodes[0].offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);\" frameborder=\"0\" ></iframe>";
        C.appendChild(this.subMenuContainer);
        this.subMenuContainer.style.display = "none";
        this.subMenuContainer.style.position = "absolute";
        this.subMenuContainer.style.zIndex = parseInt(this.zIndex) + 1;
        var D = $create("ul", this.doc),
        A = $create("div", this.doc);
        D.className = "eos-popmenu-list";
        this.shadowdiv = A;
        this.subMenuContainer.appendChild(A);
        A.appendChild(D);
        for (var B = 0; B < this.submemu.length; B++) {
            var E = this.submemu[B];
            E.root = this.root;
            E.level = this.level + 1;
            E.container.style.zIndex = parseInt(this.zIndex) + 1;
            E.zIndex = this.zIndex + 1;
            E.init();
            D.appendChild(E.container)
        }
        this.root.menuContainer.appendChild(C);
        A.className = "eos-popmenu"
    }
};
PopMenuItem.prototype.initContainer = function() {
    var A = $create("a", this.doc);
    A.className = "eos-popmenu-item";
    A.hidefocus = true;
    A.unselectable = "on";
    A.href = "#";
    this.container.appendChild(A);
    A.innerHTML = this.getNomalDiv();
    this.container.className = "eos-popmenu-list-item"
};
PopMenuItem.prototype.getNomalDiv = function() {
    var C = "";
    C += "<table cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;height:25px;background-color:transparent;border-style:none;border:-width:0px\">";
    C += "    <tr>";
    C += "    <td  class=\"overLeft\" style=\"width: 5px;height:25px;\"></td>";
    C += "        <td  class=\"overCenter\"style=\"width: 20px;height:25px;\">";
    C += "            <div style=\"width: 20px;height:25px;overflow:hidden;background-color:transparent;border-style:none;border:-width:0px\">";
    this.icon = this.icon ? addContextPath(this.icon) : PICTURE_BLANK;
    C += "<img border='0' width='16px' height='20px' src='" + this.icon + "'>";
    C += "            </div>";
    C += "        </td>";
    C += "        <td class=\"overCenter\" style=\"padding-left:10px;height:25px;background-color:transparent;border-style:none;border:-width:0px\">";
    var A = this.getContent();
    if (!A || A == null || A == "null") A = "";
    C += "           <div>" + A + "</div>";
    C += "        </td>";
    C += "        <td class=\"overCenter\" align=\"right\" style=\"padding-right:10px;height:25px;background-color:transparent;border-style:none;border:-width:0px\">";
    var B = PICTURE_BLANK;
    if (this.hasChild()) B = POPMENU_ARROW_RIGHT;
    C += "<img border='0' src='" + B + "'></td>";
    C += "    <td  class=\"overRight\" style=\"width: 5px;height:25px;\"></td></tr></table>";
    return C
};
PopMenuItem.prototype.getContent = function() {
    if (this.onRefreshFunc) {
        try {
            return eval(this.onRefreshFunc + "(this);")
        } catch(e) {
            alert(e);
            return this.name
        }
    }
    var url = this.getURL();
    if (url) {
        if (this.target) return "<a href='" + url + "' target='" + this.target + "'>" + this.name + "</a>";
        else return "<a href='" + url + "'>" + this.name + "</a>"
    } else return this.name
};
PopMenuItem.prototype.initEvent = function() {
    var B = this;
    function D() {
        B.hide()
    }
    function A() {
        B.parent.canClose = true;
        var A = B.parent.currMenu;
        if (A && A != B) B.parent.currMenu.hide(true);
        A = B.root.openLevel[B.level];
        if (A && A != B) A.hide(true)
    }
    function C() {
        var A = B.parent.currMenu;
        if (A && A != B) B.parent.currMenu.hide(true);
        A = B.root.openLevel[B.level];
        if (A && A != B) A.hide(true);
        if (B.root.isShow) {
            B.root.openLevel[B.level] = B;
            B.root.openByLevel(B.level);
            B.parent.canClose = false;
            B.parent.currMenu = B
        }
        return false
    }
    this.container.onmouseover = function() {
        eventManager.stopEvent();
        C();
        return false
    };
    this.container.onmouseout = function() {
        eventManager.stopEvent();
        A();
        B.root.openLevel[B.level] = null;
        return false
    };
    this.container.onclick = function() {
        eventManager.stopEvent();
        B.root.hide(true);
        B.onClick();
        return false
    }
};
PopMenuItem.prototype.getURL = function() {
    if (this.url) {
        var C = "";
        for (var A = 0; A < this.params.length; A++) {
            var B = this.params[A];
            C += "&" + B.key + "=" + B.value
        }
        if (this.url.indexOf("?") < 0) {
            if (C.length > 0) C = "?" + C;
            C = C.replace("&", "")
        }
        return this.url + C
    }
    return null
};
PopMenuItem.prototype.addParam = function(A, B) {
    this.params.push({
        key: A,
        value: B
    })
};
PopMenuItem.prototype.hasChild = function() {
    return this.submemu.length != 0
};
PopMenuItem.prototype.appendChild = function(A) {
    this.submemu.push(A);
    A.parent = this
};
PopMenuItem.prototype.showSubMenu = function() {
    if (this.hasChild()) {
        var D = getPosition(this.container);
        this.subMenuContainer.style.left = (D.left - 3 + this.container.offsetWidth) + "px";
        this.subMenuContainer.style.top = D.top + "px";
        this.subMenuContainer.style.display = "";
        if (this.subMenuContainer.offsetWidth < this.subMenuContainer.scrollWidth) this.subMenuContainer.style.width = this.subMenuContainer.scrollWidth + "px";
        var E = getPosition(this.subMenuContainer),
        C = this.doc || document,
        B = E.left + this.subMenuContainer.offsetWidth,
        F = C.body.clientWidth;
        if (B > F) {
            var A = D.left - this.subMenuContainer.offsetWidth;
            if (A < 0) A = 0;
            this.subMenuContainer.style.left = A + "px"
        }
        if (this.isShadowInit != true) {
            if (isFF) {
                initShadow(this.shadowdiv, this.doc);
                $debug(this.shadowdiv.parentNode.offsetWidth);
                this.shadowdiv.style.width = this.shadowdiv.parentNode.offsetWidth - 7
            } else initShadowIe(this.shadowdiv, this.doc);
            this.isShadowInit = true
        }
    }
};
PopMenuItem.prototype.onClick = function() {
    var root = this.root;
    if (this.onClickFunc) {
        try {
            eval(this.onClickFunc + "(this,root.args)")
        } catch(e) {}
    }
    if (root.onClickFunc) {
        try {
            eval(root.onClickFunc + "(this.menuKey,root.args)")
        } catch(e) {}
    }
};
PopMenuItem.prototype.hide = function(C) {
    if (this.canClose || C) if (this.hasChild()) {
        for (var A = 0; A < this.submemu.length; A++) {
            var B = this.submemu[A];
            B.hide(C)
        }
        this.subMenuContainer.style.display = "none"
    }
};
PopMenuItem.prototype.show = function() {
    this.showSubMenu()
};
function showPopMenu(B, D) {
    var F = $o(B);
    if (F) {
        F.args = D;
        var C = eventManager.getEvent(),
        G = C.x || C.clientX,
        E = C.y || C.clientY;
        if (isIE) {
            var A = getAbsPos(document.body, F.win);
            G = G + A.left + F.doc.body.scrollLeft;
            E = E + A.top + F.doc.body.scrollTop
        } else {
            A = getScreenPos(window, F.win);
            G = G + A.left;
            E = E + A.top
        }
        if (F.hide(true)) {
            F.show();
            F.setPosition(G, E)
        }
    }
}
function hideMenu(A) {
    var B = $o(A);
    if (B) B.hide(true)
}
function getDocumentId(B) {
    if (!top.docs) top.docs = [];
    var A = 0;
    for (; A < top.docs.length; A++) if (top.docs[A] == B) return A;
    top.docs.push(B);
    return top.docs.length - 1
}
PopMenu.prototype.bind = function(A, B) {
    var C = this.id;
    if (A != null) A.oncontextmenu = function() {
        showPopMenu(C, B);
        return false
    }
};
function initShadowIe(C, E) {
    E = E || document;
    var B = C.nextSibling;
    if (B == null || !B.isShadow) {
        var D = C.parentNode,
        F = C.offsetWidth,
        G = C.offsetHeight,
        A = $createElement("div", {
            doc: E
        });
        A.isShadow = true;
        D.style.width = F;
        D.style.height = G;
        A.style.width = F - 8;
        A.style.height = G - 8;
        A.style.position = "absolute";
        A.style.left = 0;
        A.style.top = 0;
        A.style.zIndex = -999;
        A.style.background = "#777";
        A.style.filter = "progid:DXImageTransform.Microsoft.alpha(opacity=50) progid:DXImageTransform.Microsoft.Blur(pixelradius=4)";
        D.insertBefore(A, C.nextSibling);
        C.shadowContainter = A;
        C.style.width = F - 4;
        C.style.height = G - 4
    }
}
function RTree(G, A, C, E) {
    var D = new Object();
    D.hasRoot = E;
    var F = new RTreeView(G, D, A, C);
    D.setNodeExpandAction = _rtreemodel_setNodeExpandAction;
    D.getNodeExpandAction = _rtreemodel_getNodeExpandAction;
    D.getExpandRetXpaths = _rtreemodel_getExpandRetXpaths;
    D.getExpandParam = _rtreemodel_getExpandParam;
    D.loadNodeChild = _rtreemodel_loadNodeChild;
    D.initAttachParam = _rtreemodel_initAttachParam;
    D.getTreeNodeName = _rtreemodel_getTreeNodeName;
    D.getTreeNodeIcon = _rtreemodel_getTreeNodeIcon;
    D.setEntityInfo = _rtreemodel_setEntityInfo;
    D.getEntityInfo = _rtreemodel_getEntityInfo;
    D.showNodeMenu = _rtreemodel_showNodeMenu;
    D.addMenuItem = _rtreemodel_addMenuItem;
    D.onmenuclick = _rtreemodel_onmenuclick;
    D.getMovemenu = _rtreemodel_getMovemenu;
    D.isNodeMovable = _rtreemodel_isNodeMovable;
    D.moveNode = _rtreemodel_moveNode;
    D.setMoveAction = _rtreemodel_setMoveAction;
    D.getMoveAction = _rtreemodel_getMoveAction;
    D.getMoveParam = _rtreemodel_getMoveParam;
    D.clearup = _rtreemodel_clearup;
    D.setExpandInitParamFunc = _rtreemodel_setExpandInitParamFunc;
    D.setMoveInitParamFunc = _rtreemodel_setMoveInitParamFunc;
    D.expandRoot = _rtreemodel_expandRoot;
    D.preLoad = _rtreemodel_preload;
    D.treeView = F;
    D.nodeExpandActions = new Object();
    D.entityInfos = new Object();
    D.move_node = null;
    D.moveto_node = null;
    if (isFF) {
        var B = $id(G.getAttribute("id") + "_container");
        if (registerTopContainer(B)) EOSResizeObjects.push(F)
    }
    return F
}
function _rtreemodel_loadNodeChild(D, M) {
    var C;
    if (D.isloadData !== true) {
        var A = this.getNodeExpandAction(D),
        O = this.getExpandParam(D),
        I = this.getExpandRetXpaths(D);
        if (! (A && I)) {
            D.icon.src = D.openIconSrc;
            D.loadFinished = true;
            return
        }
        var K = new HideSubmit(A);
        K.submitXML(O);
        var B = K.getXMLDom();
        C = Dataset.create(B, "root/data/" + I[0]);
        for (var G = 1; G < I.length; G++) C.appendDataset(Dataset.create(B, "root/data/" + I[G]))
    } else {
    	C = D.datasetExp;
	}
    var Q = C.getEntities();
    if (!D.isroot) {
        if (Q == undefined || Q ==null || Q.length == 0) {
            D.hasChild = false;
            D.isleaf = true;
            D.refreshExpendIcon();
            D.icon.src = D.openIconSrc;
            D.childrenContainer.style.display = "none";
            D.loadFinished = true;
            if (M) M(D);
            return
        } else {
            D.hasChild = true;
            D.isleaf = false;
            D.refreshExpendIcon()
        }
    } else if (Q.length != 0) {
        D.hasChild = true;
        D.isleaf = false
    }
    var F, E, R, L, J, H, N, P;
    for (G = 0; G < Q.length; G++) {
        if (G == 0) J = true;
        else J = false;
        if (G == Q.length - 1) H = true;
        else H = false;
        E = Q[G],
        R = this.getTreeNodeName(E),
        L = this.getTreeNodeIcon(E.name),
        P = this.getEntityInfo(E.name).iconType;
        if (! (this.getEntityInfo(E.name)).expandAction) N = false;
        else N = true;
        F = new RTreeNode(D, R, L, E, J, H, N, this.hasRoot, P);
        D.childrenContainer.appendChild(F);
        if (N && this.getEntityInfo(E.name).preload == true) if (this.preLoad(F) == false) F.setLeaf();
        F.refresh()
    }
    D.childrenContainer.style.display = "";
    D.icon.src = D.openIconSrc;
    if (M) M(D);
    D.loadFinished = true
}
function _rtreemodel_preload(H) {
    var C = this.getNodeExpandAction(H),
    G = this.getExpandParam(H),
    B = this.getExpandRetXpaths(H);
    H.isloadData = true;
    if (! (C && B)) {
        H.icon.src = H.openIconSrc;
        H.loadFinished = true;
        return false
    }
    var D = new HideSubmit(C);
    D.submitXML(G);
    var E = D.getXMLDom(),
    F = Dataset.create(E, "root/data/" + B[0]);
    for (var A = 1; A < B.length; A++) F.appendDataset(Dataset.create(E, "root/data/" + B[A]));
    H.datasetExp = F;
    var I = F.values;
    if (I.length == 0) return false;
    else return true
}
function _rtreemodel_expandRoot() {
    var A = new RTreeRootNode(new Entity("root"), this);
    A.level = 0;
    this.treeView.rootNode = A;
    this.treeView.appendChild(A);
    this.loadNodeChild(A)
}
function _rtreemodel_setNodeExpandAction(D, C, A) {
    var B = this.getEntityInfo(D);
    B.expandAction = C;
    B.childEntityXpaths = A
}
function _rtreemodel_setExpandInitParamFunc(C, A) {
    var B = this.getEntityInfo(C);
    B.InitExpandParamFunc = A
}
function _rtreemodel_setMoveInitParamFunc(C, A) {
    var B = this.getEntityInfo(C);
    B.InitMoveParamFunc = A
}
function _rtreemodel_getNodeExpandAction(B) {
    var C = B.entity.name,
    A = this.getEntityInfo(C);
    return A.expandAction
}
function _rtreemodel_getExpandRetXpaths(B) {
    var C = B.entity.name,
    A = this.getEntityInfo(C);
    return A.childEntityXpaths
}
function _rtreemodel_getExpandParam(F) {
    var H = F.entity.name,
    D = this.getEntityInfo(H),
    B = D.InitExpandParamFunc,
    C = D.submitXpath,
    G = "",
    E;
    G = fireUserEvent(B, [F]);
    if (C == "" || C == null) E = F.entity.toString();
    else {
        var A = F.entity.name;
        F.entity.name = C;
        E = F.entity.toString();
        F.entity.name = A
    }
    return "<root><data>" + G + E + "</data></root>"
}
function _rtreemodel_initAttachParam(A) {
    if (A && isUserEventDefined(A)) return fireUserEvent(A, []);
    else return ""
}
function _rtreemodel_getTreeNodeName(D) {
    var E = D.name,
    C = this.getEntityInfo(E),
    B = C.expression,
    A = D.getProperty(B);
    if (A == null) return B;
    else return A
}
function _rtreemodel_getTreeNodeIcon(B) {
    var A = this.getEntityInfo(B);
    return A.iconSrc
}
function _rtreemodel_getEntityInfo(entityName) {
    entityName = entityName.toLowerCase();
    var entityInfos = this.entityInfos,
    _entityInfo;
    eval("_entityInfo = entityInfos." + entityName);
    if (!_entityInfo) {
        _entityInfo = new _rtree_EntityInfo();
        eval("entityInfos." + entityName + "=_entityInfo;")
    }
    return _entityInfo
}
function _rtreemodel_setEntityInfo(E, F, H, D, C, I, M, L, A, G, K, B) {
    E = E.toLowerCase();
    var J = this.getEntityInfo(E);
    J.expression = F;
    J.iconSrc = H;
    J.onrefresh = C;
    J.onclick = D;
    J.ondblclick = I;
    J.InitExpandParamFunc = L;
    J.childEntityXpaths = A;
    J.expandAction = G;
    J.iconType = M;
    J.submitXpath = K;
    J.preload = B
}
function _rtreemodel_setMoveAction(moveEntity, toEntity, bizAction, InitParamFunc) {
    var moveActions = this.getEntityInfo(moveEntity).moveActions,
    index;
    eval("index = moveActions." + toEntity + ";");
    if (!index) {
        index = moveActions.length;
        eval("moveActions." + toEntity + " = index;")
    }
    var moveObj = new Object();
    moveObj.action = bizAction;
    moveObj.InitParamFunc = InitParamFunc;
    moveActions[index] = moveObj
}
function _rtreemodel_isNodeMovable(C) {
    var D = C.entity.name,
    A = this.getEntityInfo(D),
    B = A.moveActions;
    if (B.length > 0) return true;
    else return false
}
function _rtreemodel_moveNode() {
    if (isCanMoveTo(document.moveModel, document.movetoModel) == false) return;
    var H = this.move_node,
    J = document.movetoModel.moveto_node;
    this.move_node = null;
    this.moveto_node = null;
    this.move_menu.hide();
    var B = H.getParent(),
    I = this.getMoveAction(H, J);
    if (I) {
        var F = H.getTree().beforeMove;
        if (F) if (F(H, J) == false) return;
        var A = this.getMoveParam(H, J),
        C = new HideSubmit(I);
        C.submitXML(A);
        var D = H.getTree(),
        E = D.afterMove;
        if (E) if (D.afterMove(C) == false) return;
        var K, G;
        K = !H.getParent().isChildOf(J);
        G = !J.isChildOf(H.getParent());
        if (K) H.getParent().reloadChild();
        if (G) J.reloadChild()
    }
}
function _rtreemodel_getMoveAction(move_node, moveto_node) {
    var toTreeId = findRTree(moveto_node).id,
    entityName = move_node.entity.name,
    toEntityName = moveto_node.entity.name,
    entityInfo = this.getEntityInfo(entityName),
    moveActions = entityInfo.moveActions,
    index;
    eval("index = moveActions." + toTreeId + "_" + toEntityName + ";");
    if (typeof(index) != "undefined") return moveActions[index].action;
    else return null
}
function _rtreemodel_getMoveParam(move_node, moveto_node) {
    var toTreeId = findRTree(moveto_node).id,
    entityName = move_node.entity.name,
    toEntityName = moveto_node.entity.name,
    entityInfo = this.getEntityInfo(entityName),
    InitParamFunc = "",
    moveActions = entityInfo.moveActions,
    index;
    eval("index = moveActions." + toTreeId + "_" + toEntityName + ";");
    if (typeof(index) != "undefined") InitParamFunc = moveActions[index].InitParamFunc;
    var from_node = move_node.getParent(),
    FuncParam = "";
    FuncParam = fireUserEvent(InitParamFunc, [move_node, moveto_node]);
    var fromNodeName = from_node.entity.name,
    movetoNodeName = moveto_node.entity.name;
    from_node.entity.name = "from";
    moveto_node.entity.name = "to";
    var returnValue = "<root><data>" + FuncParam + this.initParam + from_node.entity + moveto_node.entity + move_node.entity + "</data></root>";
    from_node.entity.name = fromNodeName;
    moveto_node.entity.name = movetoNodeName;
    return returnValue
}
function _rtreemodel_clearup() {
    var A = this;
    A.setNodeExpandAction = null;
    A.getNodeExpandAction = null;
    A.getExpandRetXpaths = null;
    A.getExpandParam = null;
    A.loadNodeChild = null;
    A.initAttachParam = null;
    A.getTreeNodeName = null;
    A.getTreeNodeIcon = null;
    A.setEntityInfo = null;
    A.getEntityInfo = null;
    A.showNodeMenu = null;
    A.addMenuItem = null;
    A.onmenuclick = null;
    A.getMovemenu = null;
    A.isNodeMovable = null;
    A.moveNode = null;
    A.setMoveAction = null;
    A.getMoveAction = null;
    A.getMoveParam = null;
    A.clearup = null;
    A.treeView = null;
    A.attachParam = null;
    A.nodeExpandActions = null;
    A.entityInfos = null;
    A.initParam = null;
    A.menu = null;
    A.move_node = null;
    A.moveto_node = null;
    A.move_menu = null
}
function _rtree_EntityInfo() {
    this.expandAction = null;
    this.moveActions = new Array();
    this.childEntityXpaths = [];
    this.expression = "";
    this.iconSrc = "";
    this.onrefresh = "";
    this.onclick = "";
    this.ondblclick = "";
    this.menuItems = new Array();
    this.InitMoveParamFunc = null;
    this.InitExpaneParamFunc = null;
    this.submitXpath = "";
    return this
}
function _rtreemodel_addMenuItem(D, C, B) {
    var A = this.getEntityInfo(D);
    A.menuItems.push(new EOSTreeMenuItem(C, B))
}
function _rtreemodel_showNodeMenu(F) {
    var D = F.getTree();
    if (D.beforeShowMenu && D.beforeShowMenu(F) === false) return;
    var H = F.entity.name,
    E = this.getEntityInfo(H),
    G = E.menuItems;
    if (G.length > 0) {
        for (var A = 0; A < G.length; A++) {
            if (A != 0) {
                var C = _get_top_window(),
                B = C.document.createElement("div");
                B.className = "rtree-popmenu-item-line";
                this.menu.insertItem(B)
            }
            this.menu.insertItem(G[A])
        }
        D.afterShowMenu && D.afterShowMenu(F, this.menu);
        this.menu.show()
    }
}
function _rtreemodel_onmenuclick(A) {
    fireUserEvent(A, [this.treeView.cur_node])
}
function _rtreemodel_getMovemenu(B) {
    if (isIE) {
        if (B.icon) this.move_menu.nodeView.innerHTML = B.icon.outerHTML + B.cell.outerHTML;
        else this.move_menu.nodeView.innerHTML = B.cell.outerHTML;
        var A = this.move_menu.nodeView.getElementsByTagName("span");
        addClass(A[0], "RC_TREE_CELL");
        removeClass(A[0], "RC_TREE_ACTIVENODE")
    } else {
        if (B.icon) this.move_menu.nodeView.innerHTML = "<img style='vertical-align:bottom'src='" + B.icon.src + "'>" + B.cell.innerHTML;
        else this.move_menu.nodeView.innerHTML = B.cell.innerHTML;
        addClass(this.move_menu.nodeView, "RC_TREE_CELL")
    }
    this.move_menu.style.display = "";
    this.move_menu.container.style.width = 1;
    this.move_menu.style.width = 1;
    initShadow(this.move_menu.container);
    if (isFF) {
        this.move_menu.container.nextSibling.style.zIndex = "999";
        this.move_menu.container.nextSibling.style.width = this.move_menu.offsetWidth + 5
    }
    this.move_menu.style.display = "none";
    return this.move_menu
}
function RTreeView(G, F, C, E) {
    var A = G,
    D = $id(G.getAttribute("id") + "_container");
    if (isFF && E == "100%") {
        var B = D;
        while (true) {
            if (B == null) break;
            if (B.tagName == "TABLE" && B.getAttribute("class") == "eos-panel-table") {
                if (B.getAttribute("height") != null) D.style.height = E;
                break
            }
            B = B.offsetParent
        }
    } else D.style.height = E;
    D.style.width = C;
    A.style.overflow = "auto";
    A.style.width = C;
    A.style.height = E;
    A.className = "RC_TREE";
    A.setAttribute("richclientType", "RTREE");
    A.findTreeNode = _rtreeview_findTreeNode;
    A.expandLevel = _rree_expand_level;
    A.getRootNode = function() {
        return this.rootNode
    };
    A.getSelectNode = _rree_getSelectNode;
    A.moveNode = _rtree_move;
    A.clearup = _rtreeview_clearup;
    A.model = F;
    A.cur_node = null;
    A.onselectstart = function() {
        return false
    };
    A.onselect = function() {
        return false
    };
    A.onmouseup = _rtreeview_onmouseup;
    A.onmousemove = _rtreeview_onmousemove;
    A.onmouseout = _rtreeview_onmouseout;
    A.onkeydown = _rtreeview_onkeydown;
    A.setMenuStyle = function(B, A) {
        this.model.menu.style[B] = A
    };
    A.autoResizeS1 = _rree_auto_resize_step1;
    A.autoResizeS2 = _rree_auto_resize_step2;
    A.isInCurrPanel = _rtree_isInCurrPanel;
    A.init = _rtree_init;
    return A
}
function _rtree_isInCurrPanel() {
    if (_eos_curr_open_panel != null) {
        var A = $id(this.getAttribute("id") + "_container").offsetParent;
        while (true) {
            if (A == null) return false;
            if (A == _eos_curr_open_panel.table) return true;
            A = A.offsetParent
        }
    } else return false
}
function _rtree_init() {
    var A = this.model;
    A.menu = new EOSTreeMenu(A);
    EOSTreeMenu.register(A.menu);
    A.move_menu = new RTreeMoveMenu();
    A.expandRoot()
}
function _rtreeview_findTreeNode(E, D, A) {
    function B(F, J, G, E) {
        var A = F.entity.name;
        if (A == J) {
            var H = F.entity.getProperty(G);
            if (H && (H == E)) return F
        }
        var I = F.getChildren(),
        D;
        for (var C = 0; C < I.length; C++) {
            D = B(I[C], J, G, E);
            if (D) return D
        }
        return null
    }
    var C = this.rootNode;
    return B(C, E, D, A)
}
function _rree_getSelectNode() {
    return this.cur_node
}
function _rtree_move(F, H) {
    var C = F.getTree(),
    G = C.model.getMoveAction(F, H);
    if (G) {
        var A = C.model.getMoveParam(F, H),
        B = new HideSubmit(G);
        B.submitXML(A);
        var D = C.afterMove;
        if (D) if (C.afterMove(B) == false) return;
        var I, E;
        I = !F.getParent().isChildOf(H);
        E = !H.isChildOf(F.getParent());
        if (I) F.getParent().reloadChild();
        if (E) H.reloadChild()
    }
}
function _rtreeview_clearup() {
    this.rootNode.clearup();
    this.model.clearup()
}
function _rtreeview_onmouseout() {
    if (!__isStartDrag()) return;
    if (this.cur_node) {
        this.cur_node.cell.className = "RC_TREE_CELL";
        document.movetoModel = null
    }
}
function _rtreeview_onmouseup() {}
var doc_onmousemove = null,
doc_onmouseup = null,
doc_onmouseout = null;
function _rtreeview_onmousemove() {
    var B = document.moveModel;
    if (!__isStartDrag()) return;
    var A = B.move_menu
}
function _rtreeview_onkeydown() {
    function A(B) {
        if (B) {
            sibling = B.nextSibling;
            if (sibling) return sibling;
            else return A(B.getParent())
        } else return null
    }
    function B(C) {
        if (!C) return null;
        var D = C.getChildren(),
        A = D.length;
        if (C.isExpanded() && (A > 0)) return B(D[A - 1]);
        else return C
    }
    var E = eventManager.getEvent();
    eventManager.stopPropagation();
    var F = this,
    D = F.cur_node;
    if (!D) return;
    switch (E.keyCode) {
    case 40:
        if (D.getChildren()[0] && D.isExpanded()) D.getChildren()[0].selected();
        else {
            var C = A(D);
            if (C) C.selected()
        }
        F.cur_node.scrollIntoView();
        break;
    case 38:
        if (D.previousSibling) {
            C = B(D.previousSibling);
            if (C) C.selected()
        } else if (D.getParent()) D.getParent().selected();
        F.cur_node.scrollIntoView();
        break;
    case 37:
        if (D.isExpanded()) D.collapseNode();
        else if (D.getParent()) D.getParent().selected();
        F.cur_node.scrollIntoView();
        break;
    case 39:
        if (!D.isExpanded()) D.expandNode();
        else if (D.getChildren()[0]) D.getChildren()[0].selected();
        F.cur_node.scrollIntoView();
        break
    }
    return false
}
function RTreeNode(A, I, H, F, J, D, E, B, G) {
    var C = document.createElement("DIV");
    C.noWrap = true;
    C.openIconSrc = STREE_DEFAULTOPEN_ICON;
    C.closeIconSrc = STREE_DEFAULTCLOSE_ICON;
    C.plusIconSrc = "";
    C.minusIconSrc = "";
    C.isFirstNode = J;
    C.isLastNode = D;
    C.hasChild = E;
    C.imgLevel = A.imgLevel;
    C.isleaf = !E;
    C.hasRoot = B;
    _rtreeNode_processNodeStatus(C, F, J, D, B, H, G, I);
    C.childLoaded = false;
    C.entity = F;
    C.isroot = false;
    C.addChildNode = _rtreeNode_addChildNodes;
    C.expandNode = _rtreeNode_expandNode;
    C.collapseNode = _rtreeNode_collapseNode;
    C.clearChildren = _rtreeNode_clearChildren;
    C.selected = _rtreeNode_selected;
    C.select = _rtreeNode_selected;
    C.refresh = _rtreeNode_refresh;
    C.refreshExpendIcon = _rtreeNoderefreshExpendIcon;
    C.isChildOf = _rtreeNode_isChildOf;
    C.isExpanded = _rtreeNode_isExpanded;
    C.getParent = _rtreeNode_getParent;
    C.getChildren = _rtreeNode_getChildren;
    C.getTree = _rtreeNode_getTree;
    C.reloadChild = _rtreeNode_reloadChild;
    C.clearup = _rtreeNode_clearup;
    C.getProperty = _rtreeNode_getProperty;
    C.setIcon = _rtreeNode_setIcon;
    C.setText = _rtreeNode_setText;
    C.getText = _rtreeNode_getText;
    C.setLeaf = _rtreeNode_setLeaf;
    C.isLeaf = _rtreeNode_isLeaf;
    C.isRootNode = _rtreeNode_isRoot;
    C.getEntity = _rtreeNode_getEntity;
    C.hasChildNode = _rtreeNode_hasChildNode;
    C.onclick = _rtreenode_onclick;
    C.ondblclick = _rtreenode_ondblclick;
    C.oncontextmenu = _rtreenode_oncontextmenu;
    C.onmousedown = _rtreenode_onmousedown;
    C.onmouseover = _rtreenode_onmouseover;
    return C
}
function _rtreeNode_hasChildNode() {
    return this.hasChild
}
function _rtreeNode_getEntity() {
    return this.entity
}
function _rtreeNode_isRoot() {
    return this.isroot
}
function _rtreeNode_isLeaf() {
    return this.isleaf
}
function _rtreeNode_getProperty(A) {
    return this.entity.getProperty(A)
}
function _rtreeNode_setText(A) {
    this.cell.innerHTML = A
}
function _rtreeNode_getText() {
    return this.cell.innerHTML
}
function _rtreeNode_setIcon(A, B) {
    this.openIconSrc = _rtree_addContextPath(A);
    if (B) this.closeIconSrc = _rtree_addContextPath(B);
    else this.closeIconSrc = this.openIconSrc;
    if (this.closeIconSrc == "null") this.icon.style.display = "none";
    this.icon.src = this.closeIconSrc
}
function _rtreeNode_setLeaf() {
    this.isleaf = true;
    this.hasChild = false;
    this.childLoaded = true;
    this.refreshExpendIcon()
}
function RTreeRootNode(F, I) {
    var B = I.hasRoot,
    K = I.getTreeNodeName(F),
    J = I.getTreeNodeIcon(F.name),
    E = document.createElement("DIV");
    E.noWrap = true;
    var A = document.createElement("DIV"),
    D;
    D = document.createElement("IMG");
    if (J == "null") D.style.display = "none";
    if (J == "") J = STREE_DEFAULTOPEN_ICON;
    else J = _rtree_addContextPath(J);
    E.openIconSrc = J;
    D.src = J;
    D.style.verticalAlign = "middle";
    D.ondrag = function() {
        eventManager.stopPropagation();
        return false
    };
    A.appendChild(D);
    var C = document.createElement("span");
    C.innerHTML = K;
    C.className = "RC_TREE_CELL";
    C.style.verticalAlign = "middle";
    A.appendChild(C);
    var H = document.createElement("IMG");
    H.style.verticalAlign = "middle";
    H.src = STREE_BLANK_ICON;
    A.appendChild(H);
    var G = document.createElement("DIV");
    E.appendChild(A);
    E.appendChild(G);
    if (!B) {
        D.style.display = "none";
        C.style.display = "none"
    }
    E.setAttribute("richclientType", "RTREENODE");
    E.expandIcon = new Object();
    E.icon = D;
    E.cell = C;
    E.childLoaded = false;
    E.entity = F;
    E.isroot = true;
    E.isleaf = false;
    E.level = false;
    E.childrenContainer = G;
    E.imgLevel = "";
    E.addChildNode = _rtreeNode_addChildNodes;
    E.expandNode = _rtreeNode_expandNode;
    E.collapseNode = _rtreeNode_collapseNode;
    E.clearChildren = _rtreeNode_clearChildren;
    E.selected = _rtreeNode_selected;
    E.select = _rtreeNode_selected;
    E.refresh = _rtreeNode_refresh;
    E.isChildOf = _rtreeNode_isChildOf;
    E.isExpanded = _rtreeNode_isExpanded;
    E.getParent = _rtreeNode_getParent;
    E.getChildren = _rtreeNode_getChildren;
    E.getTree = _rtreeNode_getTree;
    E.reloadChild = _rtreeNode_reloadChild;
    E.clearup = _rtreeNode_clearup;
    E.isRootNode = _rtreeNode_isRoot;
    E.getEntity = _rtreeNode_getEntity;
    E.hasChildNode = _rtreeNode_hasChildNode;
    E.setText = _rtreeNode_setText;
    E.getText = _rtreeNode_getText;
    E.onclick = _rtreenode_onclick;
    E.ondblclick = _rtreenode_ondblclick;
    E.oncontextmenu = _rtreenode_oncontextmenu;
    E.onmousedown = _rtreenode_onmousedown;
    E.onmouseover = _rtreenode_onmouseover;
    return E
}
function _rtree_addContextPath(A) {
    if (A == null) return "";
    if (A.indexOf("/") == 0) return contextPath + A;
    else return A
}
function _rtreeNode_processNodeStatus(F, H, J, G, D, L, P, Q) {
    var A = F.imgLevel,
    N = L,
    M = P;
    if (N != "" && N != "null") {
        var C = N.split(",");
        if (M == "xpath") {
            if (C.length != 2) {
                if (H.getProperty(C[0]) != null) F.openIconSrc = _rtree_addContextPath(H.getProperty(C[0]));
                F.closeIconSrc = F.openIconSrc
            } else {
                if (H.getProperty(C[0]) != null) F.openIconSrc = _rtree_addContextPath(H.getProperty(C[0]));
                if (H.getProperty(C[1]) != null) F.closeIconSrc = _rtree_addContextPath(H.getProperty(C[1]))
            }
        } else if (C.length != 2) {
            F.openIconSrc = _rtree_addContextPath(C[0]);
            F.closeIconSrc = F.openIconSrc
        } else {
            F.openIconSrc = _rtree_addContextPath(C[0]);
            F.closeIconSrc = _rtree_addContextPath(C[1])
        }
    }
    if (A == "" && !D && J) {
        if (G) {
            F.plusIconSrc = STREE_ROOTPLUS_ICON;
            F.minusIconSrc = STREE_ROOTMINUS_ICON
        } else if (F.hasChild) {
            F.plusIconSrc = STREE_FPLUS_ICON;
            F.minusIconSrc = STREE_FMINUS_ICON
        } else {
            F.plusIconSrc = STREE_FLEAF_ICON;
            F.minusIconSrc = ""
        }
    } else if (G) {
        if (F.hasChild) {
            F.plusIconSrc = STREE_LPLUS_ICON;
            F.minusIconSrc = STREE_LMINUS_ICON
        } else {
            F.plusIconSrc = STREE_LLEAF_ICON;
            F.minusIconSrc = ""
        }
    } else if (F.hasChild) {
        F.plusIconSrc = STREE_PLUS_ICON;
        F.minusIconSrc = STREE_MINUS_ICON
    } else {
        F.plusIconSrc = STREE_LEAF_ICON;
        F.minusIconSrc = ""
    }
    var B = document.createElement("DIV");
    for (var K = 0; K < A.length; K++) {
        var I = document.createElement("IMG");
        I.style.verticalAlign = "middle";
        if (A.charAt(K) == "1") I.src = STREE_LINE_ICON;
        else I.src = STREE_BLANK_ICON;
        B.appendChild(I)
    }
    if (G) F.imgLevel = A + "0";
    else F.imgLevel = A + "1";
    I = document.createElement("IMG");
    I.src = F.plusIconSrc;
    I.style.verticalAlign = "middle";
    B.appendChild(I);
    var L;
    L = document.createElement("IMG");
    if (N == "null") L.style.display = "none";
    else {
        L.style.verticalAlign = "middle";
        L.src = F.closeIconSrc
    }
    B.appendChild(L);
    var E = document.createElement("span");
    E.innerHTML = Q;
    E.className = "RC_TREE_CELL";
    E.style.verticalAlign = "middle";
    B.appendChild(E);
    var O = document.createElement("DIV");
    O.style.display = "none";
    F.appendChild(B);
    F.appendChild(O);
    F.setAttribute("richclientType", "RTREENODE");
    I.style.cursor = "hand";
    F.expandIcon = I;
    F.icon = L;
    F.cell = E;
    F.childLoaded = false;
    F.childrenContainer = O
}
function _rtreeNode_selected() {
    var A = findRTree(this);
    if (A.cur_node) A.cur_node.cell.className = "RC_TREE_CELL";
    this.cell.className = "RC_TREE_ACTIVENODE";
    A.cur_node = this
}
function _rtreeNoderefreshExpendIcon() {
    var A = this.imgLevel,
    B = this.hasRoot,
    D = this.isFirstNode,
    C = this.isLastNode;
    if (this.getParent().isroot && !B && D) {
        if (C) {
            this.plusIconSrc = STREE_ROOTPLUS_ICON;
            this.minusIconSrc = STREE_ROOTMINUS_ICON
        } else if (this.hasChild) {
            this.plusIconSrc = STREE_PLUS_ICON;
            this.minusIconSrc = STREE_MINUS_ICON
        } else {
            this.plusIconSrc = STREE_FLEAF_ICON;
            this.minusIconSrc = STREE_FLEAF_ICON
        }
    } else if (C) {
        if (this.hasChild) {
            this.plusIconSrc = STREE_LPLUS_ICON;
            this.minusIconSrc = STREE_LMINUS_ICON
        } else {
            this.plusIconSrc = STREE_LLEAF_ICON;
            this.minusIconSrc = STREE_LLEAF_ICON
        }
    } else if (this.hasChild) {
        this.plusIconSrc = STREE_PLUS_ICON;
        this.minusIconSrc = STREE_MINUS_ICON
    } else {
        this.plusIconSrc = STREE_LEAF_ICON;
        this.minusIconSrc = STREE_LEAF_ICON
    }
    this.expandIcon.src = this.minusIconSrc
}
function _rtreeNode_addChildNodes(A) {
    this.childrenContainer.appendChild(A)
}
function _rtreeNode_expandNode(B) {
    if (this.isleaf) return;
    var A = this.getTree().beforeExpand;
    if (A) if (A(this) == false) return;
    var C = this,
    D = findRTree(C).model;
    this.expandIcon.src = this.minusIconSrc;
    if (!C.childLoaded) {
        this.icon.src = RTREE_DROP_LOADING;
        setTimeout(function() {
            return _rtreemodel_loadNodeChild.apply(D, [C, B])
        },
        1);
        C.childLoaded = true
    } else if (!C.isleaf) {
        C.childrenContainer.style.display = "";
        this.icon.src = this.openIconSrc
    }
    var E = this.getTree().afterExpand;
    if (E) E(this)
}
function _rtreeNode_collapseNode() {
    this.expandIcon.src = this.plusIconSrc;
    this.icon.src = this.closeIconSrc;
    this.childrenContainer.style.display = "none"
}
function _rtreeNode_clearChildren() {
    var C = this.childrenContainer.childNodes;
    for (var A = C.length - 1; A >= 0; A--) if (isFF) {
        var B = C[A].parentNode;
        B.removeChild(C[A])
    } else C[A].removeNode(true);
    this.expandIcon.src = RTree.COLLAPS_ICON;
    this.childrenContainer.style.display = "none";
    this.isleaf = false;
    this.childLoaded = false;
    this.isloadData = false
}
function _rtreeNode_refresh(C) {
    var A = findRTree(this),
    D = A.model;
    if (C) this.entity = C;
    if (this.isroot) this.level = 0;
    else this.level = this.getParent().level + 1;
    this.cell.innerHTML = D.getTreeNodeName(this.entity);
    var E = D.getEntityInfo(this.entity.name),
    B = E.onrefresh;
    if (!B) return;
    fireUserEvent(B, [this, this.cell])
}
function _rtreeNode_getParent() {
    if (this.isroot) return null;
    else return this.parentNode.parentNode
}
function _rtreeNode_getChildren() {
    return this.childrenContainer.childNodes
}
function _rtreeNode_getTree() {
    return findRTree(this)
}
function _rtreeNode_isChildOf(A) {
    var B = this;
    while (B = B.getParent()) if (B == A) return true;
    return false
}
function _rtreeNode_isExpanded() {
    if (this.childrenContainer.style.display == "none") return false;
    else return true
}
function _rtreeNode_reloadChild(A) {
    if (this.childLoaded || this.isRootNode()) {
        this.clearChildren();
        this.expandNode(A)
    } else this.expandNode(A)
}
function _rtreeNode_clearup() {
    var B = this,
    C = B.getChildren();
    for (var A = C.length - 1; A >= 0; A--) C[A].clearup(true);
    B.expandIcon = null;
    B.icon = null;
    B.cell = null;
    B.entity = null;
    B.childrenContainer = null;
    B.addChildNode = null;
    B.expandNode = null;
    B.collapseNode = null;
    B.clearChildren = null;
    B.selected = null;
    B.refresh = null;
    B.isChildOf = null;
    B.isExpanded = null;
    B.getParent = null;
    B.getChildren = null;
    B.getTree = null;
    B.reloadChild = null;
    B.clearup = null;
    B.onclick = null;
    B.ondblclick = null;
    B.oncontextmenu = null;
    B.onmousedown = null;
    B.onmouseover = null;
    B.onmouseup = null;
    B.removeNode(true)
}
function _rtreenode_onclick() {
    var A = findRTree(this),
    D = A.model;
    D.menu.hide();
    function E() {
        if (B.isleaf) return;
        if (B.childrenContainer.style.display == "none") B.expandNode();
        else B.collapseNode()
    }
    var B = this,
    C = eventManager.getElement();
    if (C == this.cell || C == this.expandIcon || C == this.icon) this.selected();
    if (C == this.cell) {
        var F = D.getEntityInfo(this.entity.name).onclick;
        if (F) fireUserEvent(F, [this])
    }
    if (C == this.expandIcon) E()
}
function _rtreenode_ondblclick() {
    eventManager.stopPropagation();
    var A = findRTree(this),
    D = A.model,
    B = this,
    C = eventManager.getElement();
    if (C == this.cell) {
        var E = D.getEntityInfo(this.entity.name).ondblclick;
        if (E) fireUserEvent(E, [this])
    }
}
function _rtreenode_oncontextmenu() {
    eventManager.stopPropagation();
    var A = findRTree(this),
    C = A.model,
    D = C.menu,
    B = this;
    B.selected();
    if (eventManager.getElement() == this.cell) C.showNodeMenu(B);
    return false
}
function _rtreenode_onmousedown() {
    var C = eventManager.getEvent();
    if (isFF) {
        if (C.button != 0) return
    } else if (C.button != 1) return;
    if (isFF) eventManager.stopPropagation();
    var B = eventManager.getElement();
    if (B != this.cell) return;
    var E = findRTree(this),
    D = E.model;
    if (D.isNodeMovable(this)) {
        D.move_node = this;
        document.moveModel = D;
        doc_onmousemove = document.onmousemove;
        doc_onmouseup = document.onmouseup;
        doc_onmouseout = document.onmouseout;
        document.onmousemove = function() {
            A.show();
            var B = eventManager.getEvent();
            if (isFF) {
                A.style.top = eventManager.getPointY();
                A.style.left = eventManager.getPointX() + 12
            } else {
                A.style.posTop = B.y + document.body.scrollTop;
                A.style.posLeft = B.x + document.body.scrollLeft + 12
            }
            if (isCanMoveTo(document.moveModel, document.movetoModel) != false) A.statusIcon.src = RTREE_DROP_YES;
            else A.statusIcon.src = RTREE_DROP_NO
        };
        document.onmouseup = function() {
            var C = document.moveModel;
            if (isCanMoveTo(document.moveModel, document.movetoModel) == false) {
                var B;
                if (C.move_node.icon.style.display == "none") B = getPosition(C.move_node.cell);
                else B = getPosition(C.move_node.icon);
                if (A.style.display != "none") {
                    A.statusIcon.style.display = "none";
                    A.reset(B.top, B.left)
                }
            } else {
                document.onmousemove = doc_onmousemove;
                document.onmouseup = doc_onmouseup;
                document.onmouseout = doc_onmouseout;
                A.hide();
                C.moveNode();
                document.moveModel = null;
                document.movetoModel = null;
                return
            }
            document.onmousemove = doc_onmousemove;
            document.onmouseup = doc_onmouseup;
            document.onmouseout = doc_onmouseout;
            document.moveModel = null;
            document.movetoModel = null
        };
        document.onmouseout = function() {
            if (isFF) if (eventManager.getElement().tagName != "HTML") return;
            try {
                if (! (C.clientX <= 0 || C.clientX >= document.body.clientWidth || C.clientY <= 0 || C.clientY >= document.body.clientHeight)) return;
                var B = getPosition(D.move_node.icon);
                if (A.style.display != "none") {
                    A.statusIcon.style.display = "none";
                    A.reset(B.top, B.left)
                }
                document.onmousemove = doc_onmousemove;
                document.onmouseup = doc_onmouseup;
                document.onmouseout = doc_onmouseout;
                document.moveModel = null;
                document.movetoModel = null
            } catch(E) {}
        };
        var A = D.getMovemenu(D.move_node);
        A.statusIcon.style.display = ""
    }
}
function _rtreenode_onmouseover() {
    if (eventManager.getElement() != this.cell) return;
    var B = findRTree(this),
    A = B.model;
    if (__isStartDrag()) {
        eventManager.stopPropagation();
        A.moveto_node = this;
        document.movetoModel = A;
        this.selected()
    }
}
function _rtreenode_onmouseup() {}
function findRTree(B) {
    var C = B,
    A;
    while (C = C.parentNode) {
        A = C.getAttribute("richclientType");
        if (A == "RTREE") return C
    }
    return null
}
function EOSTreeMenu(B) {
    var A = _get_top_window(),
    C = A.document.createElement("div"),
    D = A.document.createElement("div");
    C.appendChild(D);
    C.container = D;
    D.className = "eos-popmenu";
    C.style.width = "120px";
    C.style.cursor = " hand";
    C.style.position = "absolute";
    C.style.display = "none";
    A.document.body.appendChild(C);
    C.clearItems = _treemenu_clearItems;
    C.insertItem = _treemenu_insertItem;
    C.addMenuItem = _treemenu_addMenuItem;
    C.removeMenuItem = _treemenu_removeMenuItem;
    C.hide = function() {
        this.clearItems();
        this.style.display = "none"
    };
    C.show = function() {
        PageControl.setFocus(this);
        this.style.display = "";
        initShadow(this.container, A.document);
        if (isFF) this.container.nextSibling.style.width = this.container.nextSibling.offsetWidth + 5;
        this.container.nextSibling.style.zIndex = "-1";
        this.style.zIndex = "0";
        if (isFF) setOpacity(this, 0);
        this.style.zindex = 999;
        var E = eventManager.getEvent();
        if (isIE) {
            this.style.posTop = E.screenY - A.screenTop + A.document.body.scrollTop;
            this.style.posLeft = E.screenX - A.screenLeft + A.document.body.scrollLeft
        } else {
            var D = E.x || E.clientX,
            C = E.y || E.clientY,
            B = getScreenPos(window, A);
            D = D + B.left + A.document.body.scrollLeft;
            C = C + B.top + A.document.body.scrollTop;
            this.style.top = C;
            this.style.left = D
        }
        this.tabIndex = 1;
        if (isFF) fx_fadeIn(this, null, 500)
    };
    C.model = B;
    C.oncontextmenu = function() {
        return false
    };
    C.onselectstart = function() {
        return false
    };
    C.onblur = function() {
        EOSTreeMenu.hideAll()
    };
    return C
}
function _treemenu_addMenuItem(B, C) {
    var A = new EOSTreeMenuItem(B, C);
    this.insertItem(A)
}
function _treemenu_removeMenuItem(B) {
    var C = this.container.children || this.container.childNodes;
    for (var A = 0; A < C.length; A++) if (C[A].innerHTML == B) {
        this.container.removeChild(C[A]);
        return
    }
}
EOSTreeMenu.register = function(A) {
    if (!this.menus) this.menus = new Array();
    this.menus.push(A)
};
EOSTreeMenu.hideAll = function() {
    if (!this.menus) return;
    for (var A = 0; A < this.menus.length; A++) this.menus[A].hide()
};
function EOSTreeMenuItem(D, B) {
    var A = _get_top_window(),
    C = A.document.createElement("div");
    C.className = "rtree-popmenu-item";
    if (isFF) C.style.marginBottom = "-6px";
    C.innerHTML = D;
    C.onclickFunction = B;
    C.onmousedown = _treemenuitem_onclick;
    C.onmouseover = _treemenuitem_onmouseover;
    C.onmouseout = _treemenuitem_onmouseout;
    return C
}
function _treemenu_clearItems() {
    var A = this.container.children || this.container.childNodes;
    if (A) while (A.length > 0) this.container.removeChild(A[0])
}
function _treemenu_insertItem(A) {
    this.container.appendChild(A)
}
function _treemenuitem_onclick() {
    var A = this.parentNode.parentNode;
    removeClass(this, "rtree-popmenu-item-mouseover");
    A.hide();
    A.model.onmenuclick(this.onclickFunction);
    this.style.backgroundColor = "";
    this.style.color = "black"
}
function _treemenuitem_onmouseover() {
    addClass(this, "rtree-popmenu-item-mouseover");
    this.style.color = "white"
}
function _treemenuitem_onmouseout() {
    removeClass(this, "rtree-popmenu-item-mouseover");
    this.style.color = "black"
}
function RTreeMoveMenu() {
    var C = document.createElement("div"),
    A = document.createElement("div"),
    B = document.createElement("nobr");
    A.appendChild(B);
    A.className = "RC_TREE_DRAGNODE";
    C.onselectstart = function() {
        eventManager.stopPropagation();
        return false
    };
    C.style.position = "absolute";
    C.style.display = "none";
    document.body.appendChild(C);
    var E = document.createElement("img");
    E.src = RTREE_DROP_NO;
    E.style.verticalAlign = "middle";
    var D = document.createElement("span");
    B.appendChild(E);
    B.appendChild(D);
    C.appendChild(A);
    C.nodeView = D;
    C.statusIcon = E;
    C.container = A;
    C.show = _rtreemovemenu_show;
    C.hide = _rtreemovemenu_hide;
    C.reset = _rtreemovemenu_reset;
    return C
}
function _rtreemovemenu_show() {
    this.style.display = ""
}
function _rtreemovemenu_reset(B, C) {
    this.hide();
    return;
    if (isFF) {
        this.hide();
        return
    }
    var A = this;
    if (this.style.posTop == B && this.style.posLeft == C) {
        this.hide();
        return
    }
    if (this.style.posTop > B) if (this.style.posTop - B <= 3) this.style.posTop = this.style.posTop - 1;
    else this.style.posTop = this.style.posTop - (this.style.posTop - B) * 0.3;
    if (this.style.posTop < B) if (B - this.style.posTop <= 3) this.style.posTop = this.style.posTop + 1;
    else this.style.posTop = this.style.posTop + (B - this.style.posTop) * 0.3;
    if (this.style.posLeft > C) if (this.style.posLeft - C <= 3) this.style.posLeft = this.style.posLeft - 1;
    else this.style.posLeft = this.style.posLeft - (this.style.posLeft - C) * 0.3;
    if (this.style.posLeft < C) if (C - this.style.posLeft <= 3) this.style.posLeft = this.style.posLeft + 1;
    else this.style.posLeft = this.style.posLeft + (C - this.style.posLeft) * 0.3;
    setTimeout(function() {
        return _rtreemovemenu_reset.apply(A, [B, C])
    },
    10)
}
function _rtreemovemenu_hide() {
    this.style.display = "none"
}
function fireUserEvent(function_name, param) {
    var result, paramstr = "";
    for (i = 0; i < param.length; i++) if (i == 0) paramstr = "param[" + i + "]";
    else paramstr = paramstr + ",param[" + i + "]";
    if (isUserEventDefined(function_name)) eval("result=" + function_name + "(" + paramstr + ");");
    if (!result) result = "";
    return result
}
function isUserEventDefined(function_name) {
    if (function_name == "" || function_name == undefined) return false;
    var result;
    eval("result=(typeof(" + function_name + ")!=\"undefined\");");
    if (!result) alert(STREE_MOT_FOUND + function_name + "!");
    return result
}
function __isStartDrag() {
    if (document.moveModel != null) return true;
    else return false
}
function isCanMoveTo(E, C) {
    if (!E) return false;
    if (!C) return false;
    var B = E.move_node,
    D = C.moveto_node;
    if (D == null) return false;
    if (B == null) return false;
    if (B == D) return false;
    var A = B.getParent();
    if (D == A) return false;
    if (D.isChildOf(B)) return false;
    var F = E.getMoveAction(B, D),
    G = B.getTree().isCanMove;
    if (!F) return false;
    if (G) if (G(B, D) == false) return false;
    else return true
}
function _rree_expand_level(A, C) {
    if (C == null) C = this.getRootNode();
    if (!C.isroot) C.expandNode();
    var B = this;
    setTimeout(function() {
        return isLoadFinish.apply(B, [A, C])
    },
    10)
}
function isLoadFinish(A, C) {
    if (C.loadFinished != true) {
        setTimeout(function() {
            return isLoadFinish.apply(tree, [A, C])
        },
        10);
        return
    }
    A = A - 1;
    var D = C.getChildren();
    if (A == 0) return;
    else for (var B = 0; B < D.length; B++) {
        if (D[B].isleaf) continue;
        _rree_expand_level(A, D[B])
    }
}
function _rree_auto_resize_step1() {
    $id(this.id + "_container").style.display = "none"
}
function _rree_auto_resize_step2() {
    var B = $id(this.getAttribute("id") + "_container"),
    A = B.parentNode;
    while (A != null) if (A.getAttribute && A.getAttribute("layout") != null) {
        B.style.height = A.offsetHeight;
        B.style.display = "";
        A = null
    } else A = A.parentNode;
    B.style.display = ""
}
function Relation(D, A, B, C) {
    this.entityName = D;
    this.entityField = A;
    this.parentEntity = B;
    this.parnetFeild = C
}
function EntityInfo(B, H, E, F, I, D, C, G, A) {
    this.icon = B;
    this.iconType = H;
    this.showField = E;
    this.onclick = F;
    this.refresh = I;
    this.oncheck = D;
    this.submitXpath = C;
    this.url = G;
    this.target = A
}
function CheckedInfo(C, A, B, D) {
    this.entityName = C;
    this.entityField = A;
    this.checkedEntity = B;
    this.checkedField = D
}
function CheckList(A) {
    this.entityType = A;
    this.list = new Array()
}
CheckList.prototype.push = function(A) {
    this.list.push(A)
};
CheckList.prototype.getLength = function() {
    return this.list.length
};
CheckList.prototype.getNodeType = function() {
    return this.entityType
};
CheckList.prototype.toString = function() {
    var B = "";
    for (var A = 0; A < this.list.length; A++) B = B + this.list[A];
    return "<list entityType='" + this.entityType + "'>" + B + "</list>"
};
function STree(C, B, F, E, A, D) {
    if (A != "null") C.style.width = A;
    if (D != "null") C.style.height = D;
    var G = C.id.substr(0, C.id.indexOf("_container"));
    PageControl.add(G, this);
    this.hasCheckbox = B;
    this.checkType = F;
    this.treeContainer = C;
    this.hasRoot = E;
    this.datasetList = new Object();
    this.datasetList.root = new Object();
    this.entityInfoList = new Object();
    this.relationList = new Array();
    this.checkedInfoList = new Array()
}
STree.prototype.addRelation = function(E, A, B, D) {
    var C = new Relation(E, A, B, D);
    this.relationList.push(C)
};
STree.prototype.addCheckedInfo = function(C, A, B, E) {
    var D = new CheckedInfo(C, A, B, E);
    this.checkedInfoList.push(D)
};
STree.prototype.addDataset = function(D, A) {
    var B = createXmlDom();
    B.loadXML(A.innerHTML);
    var C = new Dataset(D);
    C.init(B.selectSingleNode("/root/data").childNodes);
    this.datasetList[D] = C
};
STree.prototype.addEntityInfo = function(K, B, I, F, G, J, E, D, H, A) {
    var C = new EntityInfo(B, I, F, G, J, E, D, H, A);
    this.entityInfoList[K] = C
};
STree.prototype.getDataset = function(A) {
    return this.datasetList[A]
};
STree.prototype.getEntityInfo = function(A) {
    return this.entityInfoList[A]
};
STree.prototype.processRelation = function() {
    var B = this.relationList;
    for (var A = 0; A < B.length; A++) {
        var C = B[A];
        if (C.parentEntity == "root") _stree_linkToRoot(C, this.getDataset(C.entityName), this.getDataset(C.parentEntity));
        else _stree_linkToParnet(C, this.getDataset(C.entityName), this.getDataset(C.parentEntity))
    }
};
STree.prototype.processCheckBox = function() {
    var D = this.checkedInfoList;
    for (var G = 0; G < D.length; G++) {
        var F = D[G];
        if (F.entityName == F.checkedEntity) {
            var C = this.getDataset(F.entityName),
            K = C.values,
            J = F.checkedField,
            I = F.entityField,
            M = C.getLength();
            for (var L = 0; L < M; L++) {
                var H = K[L].getProperty(I);
                if (H == J) K[L].checked = "1"
            }
        } else {
            var C = this.getDataset(F.entityName),
            K = C.values,
            M = C.getLength(),
            B = this.getDataset(F.checkedEntity),
            N = B.values,
            E = B.getLength(),
            I = F.entityField,
            O = F.checkedField;
            for (L = 0; L < M; L++) {
                H = K[L].getProperty(I);
                for (var A = 0; A < E; A++) {
                    J = N[A].getProperty(O);
                    if (J == H) K[L].checked = "1"
                }
            }
        }
    }
    this.processParnetCheckBox()
};
STree.prototype.processParnetCheckBox = function() {
    if (this.checkType == "simple") return;
    _stree_processParnetCheckBox(this.getDataset("root"))
};
function _stree_processParnetCheckBox(D) {
    var C = "3",
    B = D.childList;
    if (B != null) {
        for (var A = 0; A < B.length; A++) {
            var E = _stree_processParnetCheckBox(B[A]);
            if (C == "3") C = E;
            else if (C != "2") if (C != E) C = 2
        }
        D.checked = C;
        return C
    } else if (D.checked == "1") return 1;
    else return 0
}
function _stree_linkToRoot(I, F, E) {
    if (E.childList == null) E.childList = new Array();
    var D = E.childList,
    K = F.getLength(),
    H = F.values,
    J = I.entityName,
    C = I.parnetFeild,
    B = I.entityField;
    if (C === "ADD_ALL") {
        for (var A = 0; A < K; A++) {
            var G = H[A];
            G.type = J;
            D.push(G)
        }
    } else {
        if (C === "") C = "null";
        for (A = 0; A < K; A++) {
            G = H[A];
            if (G.getProperty(B) + "" == C) {
                G.type = J;
                D.push(G)
            }
        }
    }
}
function _stree_linkToParnet(O, D, B) {
    var P = D.getLength(),
    N = D.values,
    H = B.getLength(),
    C = B.values,
    E = O.entityName,
    J = O.parnetFeild,
    K = O.entityField,
    G = J.split(","),
    A = K.split(",");
    if (G.length == 1) {
        for (var I = 0; I < P; I++) {
            var F = N[I].getProperty(K);
            for (var M = 0; M < H; M++) {
                var L = C[M].getProperty(J);
                if (L == F) {
                    N[I].type = E;
                    if (C[M].childList == null) C[M].childList = new Array();
                    C[M].childList.push(N[I]);
                    break
                }
            }
        }
    } else for (I = 0; I < P; I++) {
        F = N[I].getProperty(K);
        for (M = 0; M < H; M++) {
            L = C[M].getProperty(J);
            if (L == F) {
                N[I].type = E;
                if (C[M].childList == null) C[M].childList = new Array();
                C[M].childList.push(N[I]);
                break
            }
        }
    }
}
function _streeNode_collapseNode() {
    this.expandIcon.src = this.plusIconSrc;
    this.icon.src = this.closeIconSrc;
    this.childrenContainer.style.display = "none"
}
function _streeNode_expandNode() {
    this.expandIcon.src = this.minusIconSrc;
    this.icon.src = this.openIconSrc;
    if (!this.isChildLoaded) this.addChildNode();
    else this.childrenContainer.style.display = ""
}
function _streeNode_addChildNodes() {
    var C = this.entity.childList;
    if (C == null) return;
    for (var A = 0; A < C.length; A++) {
        var D = false,
        B = false;
        if (A == 0) D = true;
        if (A == C.length - 1) B = true;
        var E = new STreeNode(this, C[A], D, B);
        E.refresh();
        this.childrenContainer.appendChild(E)
    }
    this.childrenContainer.style.display = "";
    this.isChildLoaded = true
}
function STreeRootNode(F) {
    var E = document.createElement("DIV");
    E.noWrap = true;
    E.level = "";
    E.tree = F;
    E.hasCheckbox = F.hasCheckbox;
    E.checkType = F.checkType;
    E.entity = F.datasetList.root;
    E.openIconSrc = STREE_DEFAULTOPEN_ICON;
    E.closeIconSrc = STREE_DEFAULTCLOSE_ICON;
    E.plusIconSrc = STREE_ROOTPLUS_ICON;
    E.minusIconSrc = STREE_FMINUS_ICON;
    E.entityInfo = F.getEntityInfo("root");
    E.hasChild = (E.entity.childList != null);
    E.isChildLoaded = true;
    E.isRoot = true;
    var B = document.createElement("div"),
    G = E.entityInfo.icon;
    if (G != "") E.openIconSrc = _stree_addContextPath(G);
    var D;
    D = document.createElement("IMG");
    D.style.verticalAlign = "middle";
    if (E.openIconSrc == "null") D.style.display = "none";
    D.src = E.openIconSrc;
    B.appendChild(D);
    var A = null;
    if (E.hasCheckbox) {
        A = document.createElement("IMG");
        A.style.verticalAlign = "middle";
        if (E.entity.checked == null || E.entity.checked == "0") A.src = STREE_CHECKBOX_FALSE_ICON;
        else if (E.entity.checked == "1") A.src = STREE_CHECKBOX_TRUE_ICON;
        else A.src = STREE_CHECKBOX_TRUE_ICON1;
        B.appendChild(A)
    }
    var C;
    if (E.entityInfo.url != "") {
        C = document.createElement("A");
        C.href = _stree_addContextPath(E.entityInfo.url);
        C.innerHTML = E.entityInfo.showField;
        if (E.entityInfo.target != "") C.target = E.entityInfo.target
    } else {
        C = document.createElement("SPAN");
        C.innerHTML = E.entityInfo.showField
    }
    C.className = "RC_TREE_CELL";
    B.appendChild(C);
    var I = document.createElement("IMG");
    I.style.verticalAlign = "middle";
    I.src = STREE_BLANK_ICON;
    B.appendChild(I);
    var H = document.createElement("DIV");
    E.appendChild(B);
    E.appendChild(H);
    E.setAttribute("richclientType", "RTREENODE");
    E.icon = D;
    E.cell = C;
    E.checkbox = A;
    E.childLoaded = true;
    if (!F.hasRoot) {
        if (E.hasCheckbox) A.style.display = "none";
        D.style.display = "none";
        C.style.display = "none"
    }
    E.childrenContainer = H;
    E.addChildNode = _streeNode_addChildNodes;
    E.expandNode = _streeNode_expandNode;
    E.collapseNode = _streeNode_collapseNode;
    E.select = _streeNode_selected;
    E.refresh = _streeNode_refresh;
    E.setIcon = _streeNode_setIcon;
    E.setText = _streeNode_setNodeText;
    E.getText = _streeNode_getNodeText;
    E.getProperty = _streeNode_getProperty;
    E.checkbox_click = _streeNode_checkbox_onchick;
    E.getParent = _streeNode_getParent;
    E.getChildren = _streeNode_getChildren;
    E.isChecked = _streeNode_isChecked;
    E.getEntity = _streeNode_getEntity;
    E.isRootNode = _streeNode_isRoot;
    E.hasChildNode = _streeNode_hasChild;
    E.getTree = _streeNode_getTree;
    E.onmouseover = _streeNode_onmouseover;
    E.onmouseout = _streeNode_onmouseout;
    E.onclick = _streeNode_onclick;
    return E
}
function STreeNode(A, E, F, D) {
    var C = A.tree,
    B = document.createElement("DIV");
    B.noWrap = true;
    B.level = A.level;
    B.tree = C;
    B.hasCheckbox = C.hasCheckbox;
    B.checkType = C.checkType;
    B.entity = E;
    B.openIconSrc = STREE_DEFAULTOPEN_ICON;
    B.closeIconSrc = STREE_DEFAULTCLOSE_ICON;
    B.plusIconSrc = "";
    B.minusIconSrc = "";
    B.entityInfo = C.getEntityInfo(B.entity.type);
    B.hasChild = (E.childList != null);
    B.isRoot = false;
    _streeNode_processNodeStatus(B, E, F, D);
    B.addChildNode = _streeNode_addChildNodes;
    B.expandNode = _streeNode_expandNode;
    B.collapseNode = _streeNode_collapseNode;
    B.select = _streeNode_selected;
    B.refresh = _streeNode_refresh;
    B.setIcon = _streeNode_setIcon;
    B.setText = _streeNode_setNodeText;
    B.getText = _streeNode_getNodeText;
    B.getProperty = _streeNode_getProperty;
    B.checkbox_click = _streeNode_checkbox_onchick;
    B.getParent = _streeNode_getParent;
    B.getChildren = _streeNode_getChildren;
    B.isChecked = _streeNode_isChecked;
    B.getEntity = _streeNode_getEntity;
    B.isRootNode = _streeNode_isRoot;
    B.hasChildNode = _streeNode_hasChild;
    B.getTree = _streeNode_getTree;
    B.onmouseover = _streeNode_onmouseover;
    B.onmouseout = _streeNode_onmouseout;
    B.onclick = _streeNode_onclick;
    return B
}
function _streeNode_getTree() {
    return this.tree
}
function _streeNode_hasChild() {
    return this.hasChild
}
function _streeNode_isRoot() {
    return this.isRoot
}
function _streeNode_getEntity() {
    return this.entity
}
function _streeNode_getProperty(A) {
    return this.entity.getProperty(A)
}
function _streeNode_processNodeStatus(D, F, H, E) {
    var A = D.level,
    N = D.entityInfo.icon,
    M = D.entityInfo.iconType;
    if (N != "") {
        var B = N.split(",");
        if (M == "xpath") {
            if (B.length != 2) {
                D.openIconSrc = _stree_addContextPath(F.getProperty(B[0]));
                D.closeIconSrc = D.openIconSrc
            } else {
                D.openIconSrc = _stree_addContextPath(F.getProperty(B[0]));
                D.closeIconSrc = _stree_addContextPath(F.getProperty(B[1]))
            }
        } else if (B.length != 2) {
            D.openIconSrc = _stree_addContextPath(B[0]);
            D.closeIconSrc = D.openIconSrc
        } else {
            D.openIconSrc = _stree_addContextPath(B[0]);
            D.closeIconSrc = _stree_addContextPath(B[1])
        }
    }
    if (A == "" && !D.tree.hasRoot && H) {
        if (E) {
            D.plusIconSrc = STREE_ROOTPLUS_ICON;
            D.minusIconSrc = STREE_ROOTMINUS_ICON
        } else if (D.hasChild) {
            D.plusIconSrc = STREE_FPLUS_ICON;
            D.minusIconSrc = STREE_FMINUS_ICON
        } else {
            D.plusIconSrc = STREE_FLEAF_ICON;
            D.minusIconSrc = ""
        }
    } else if (E) {
        if (D.hasChild) {
            D.plusIconSrc = STREE_LPLUS_ICON;
            D.minusIconSrc = STREE_LMINUS_ICON
        } else {
            D.plusIconSrc = STREE_LLEAF_ICON;
            D.minusIconSrc = ""
        }
    } else if (D.hasChild) {
        D.plusIconSrc = STREE_PLUS_ICON;
        D.minusIconSrc = STREE_MINUS_ICON
    } else {
        D.plusIconSrc = STREE_LEAF_ICON;
        D.minusIconSrc = ""
    }
    var K = document.createElement("div");
    for (var I = 0; I < A.length; I++) {
        var G = document.createElement("IMG");
        G.style.verticalAlign = "middle";
        if (A.charAt(I) == "1") G.src = STREE_LINE_ICON;
        else G.src = STREE_BLANK_ICON;
        K.appendChild(G)
    }
    if (E) D.level = A + "0";
    else D.level = A + "1";
    G = document.createElement("IMG");
    G.src = D.plusIconSrc;
    G.style.verticalAlign = "middle";
    K.appendChild(G);
    var L;
    L = document.createElement("IMG");
    L.style.verticalAlign = "middle";
    if (D.closeIconSrc == "null") L.style.display = "none";
    L.src = D.closeIconSrc;
    K.appendChild(L);
    var J = null;
    if (D.hasCheckbox) {
        J = document.createElement("IMG");
        J.style.verticalAlign = "middle";
        if (D.entity.checked == null || D.entity.checked == "0") J.src = STREE_CHECKBOX_FALSE_ICON;
        else if (D.entity.checked == "1") J.src = STREE_CHECKBOX_TRUE_ICON;
        else J.src = STREE_CHECKBOX_TRUE_ICON1;
        K.appendChild(J)
    }
    var C;
    if (D.entityInfo.url != "") {
        C = document.createElement("A");
        var P = compileTemplate(D.entityInfo.url);
        C.href = _stree_addContextPath(runExpression(P, F));
        if (D.entityInfo.target != "") C.target = D.entityInfo.target
    } else C = document.createElement("span");
    C.innerHTML = D.entity.getProperty(D.entityInfo.showField);
    C.className = "RC_TREE_CELL";
    C.style.verticalAlign = "middle";
    K.appendChild(C);
    var O = document.createElement("DIV");
    O.style.display = "none";
    D.appendChild(K);
    D.appendChild(O);
    D.setAttribute("richclientType", "RTREENODE");
    G.style.cursor = "hand";
    D.expandIcon = G;
    D.icon = L;
    D.cell = C;
    D.checkbox = J;
    D.childLoaded = false;
    D.childrenContainer = O
}
function _stree_addContextPath(A) {
    if (A == null) return "";
    if (A.indexOf("/") == 0) return contextPath + A;
    else return A
}
function _streeNode_refresh() {
    var functionName = this.entityInfo.refresh;
    if (functionName != "") eval(functionName + "(this)")
}
function _streeNode_onmouseover() {
    addClass(this.cell, "RC_TREE_MOUSEOVER");
    eventManager.stopPropagation()
}
function _streeNode_onmouseout() {
    removeClass(this.cell, "RC_TREE_MOUSEOVER");
    eventManager.stopPropagation()
}
function _streeNode_onclick() {
    var src = eventManager.getElement();
    if (src == this.cell || src == this.expandIcon || src == this.icon || src == this.checkbox) this.select();
    if (src == this.cell) {
        var functionName = this.entityInfo.onclick;
        if (functionName != "") eval(functionName + "(this)")
    }
    if (src == this.expandIcon) if (this.childrenContainer.style.display == "none") {
        if (this.hasChild) this.expandNode()
    } else this.collapseNode();
    if (src == this.checkbox) this.checkbox_click()
}
function _streeNode_checkbox_onchick() {
    if (this.checkType == "simple") {
        if (this.entity.checked == "1") {
            this.entity.checked = 0;
            this.checkbox.src = STREE_CHECKBOX_FALSE_ICON
        } else {
            this.entity.checked = 1;
            this.checkbox.src = STREE_CHECKBOX_TRUE_ICON
        }
    } else {
        if (this.entity.checked == "1") _stree_uncheckAllChild(this);
        else _stree_checkAllChild(this);
        _stree_process_parentNode(this)
    }
    var functionName = this.entityInfo.oncheck;
    if (functionName != "") eval(functionName + "(this)")
}
function _stree_checkAllChild(C) {
    C.entity.checked = 1;
    C.checkbox.src = STREE_CHECKBOX_TRUE_ICON;
    if (C.hasChild) if (C.isChildLoaded) {
        var A = C.getChildren();
        for (var B = 0; B < A.length; B++) _stree_checkAllChild(A[B])
    } else {
        A = C.entity.childList;
        for (B = 0; B < A.length; B++) _stree_unload_checkAllChild(A[B])
    }
    return
}
function _stree_unload_checkAllChild(C) {
    C.checked = 1;
    if (C.childList) {
        var A = C.childList;
        for (var B = 0; B < A.length; B++) _stree_unload_checkAllChild(A[B])
    }
    return
}
function _stree_uncheckAllChild(C) {
    C.entity.checked = 0;
    C.checkbox.src = STREE_CHECKBOX_FALSE_ICON;
    if (C.hasChild) if (C.isChildLoaded) {
        var A = C.getChildren();
        for (var B = 0; B < A.length; B++) _stree_uncheckAllChild(A[B])
    } else {
        A = C.entity.childList;
        for (B = 0; B < A.length; B++) _stree_unload_uncheckAllChild(A[B])
    }
    return
}
function _stree_unload_uncheckAllChild(C) {
    C.checked = 0;
    if (C.childList) {
        var A = C.childList;
        for (var B = 0; B < A.length; B++) _stree_unload_uncheckAllChild(A[B])
    }
    return
}
function _stree_process_parentNode(D) {
    var C = D.getParent();
    if (C == null) return;
    var E = D.entity.checked;
    if (E == "2") {
        C.entity.checked = "2";
        C.checkbox.src = STREE_CHECKBOX_TRUE_ICON1;
        _stree_process_parentNode(C);
        return
    }
    var A = C.getChildren();
    for (var B = 0; B < A.length; B++) {
        if (A[B].entity.checked == null) A[B].entity.checked = "0";
        if (A[B].entity.checked != E) {
            C.entity.checked = "2";
            C.checkbox.src = STREE_CHECKBOX_TRUE_ICON1;
            _stree_process_parentNode(C);
            return
        }
    }
    if (E == "1") {
        C.entity.checked = 1;
        C.checkbox.src = STREE_CHECKBOX_TRUE_ICON
    } else {
        C.entity.checked = 0;
        C.checkbox.src = STREE_CHECKBOX_FALSE_ICON
    }
    _stree_process_parentNode(C)
}
function _streeNode_getChildren() {
    return this.childrenContainer.childNodes
}
function _streeNode_getParent() {
    if (this.isRoot) return null;
    else return this.parentNode.parentNode
}
function _streeNode_selected() {
    var A = this.tree;
    if (A.cur_node) A.cur_node.cell.className = "RC_TREE_CELL";
    this.cell.className = "RC_TREE_ACTIVENODE";
    A.cur_node = this
}
STree.prototype.getCheckedList = function(H) {
    if (H != null) {
        var C = this.getDataset(H);
        if (C == null) {
            alert(H + STREE_ENTITY_NOT_EXIST);
            return
        }
        var F = new CheckList(H),
        E = C.values,
        G = C.getLength();
        for (var B = 0; B < G; B++) {
            var D = E[B].checked;
            if (D == "1" || D == "2") F.push(E[B])
        }
        return F
    } else {
        var A = new Array();
        for (H in this.datasetList) {
            if (H == "root") continue;
            C = this.getDataset(H);
            if (C == null) {
                alert(H + STREE_ENTITY_NOT_EXIST);
                return
            }
            F = new CheckList(H),
            E = C.values,
            G = C.getLength();
            for (B = 0; B < G; B++) {
                D = E[B].checked;
                if (D == "1" || D == "2") F.push(E[B])
            }
            A.push(F)
        }
        return A
    }
};
STree.prototype.getUncheckedList = function(H) {
    if (H != null) {
        var C = this.getDataset(H);
        if (C == null) {
            alert(H + STREE_ENTITY_NOT_EXIST);
            return
        }
        var F = new CheckList(H),
        E = C.values,
        G = C.getLength();
        for (var B = 0; B < G; B++) {
            var D = E[B].checked;
            if (D == "0" || D == null) F.push(E[B])
        }
        return F
    } else {
        var A = new Array();
        for (H in this.datasetList) {
            if (H == "root") continue;
            C = this.getDataset(H);
            if (C == null) {
                alert(H + STREE_ENTITY_NOT_EXIST);
                return
            }
            F = new CheckList(H),
            E = C.values,
            G = C.getLength();
            for (B = 0; B < G; B++) {
                D = E[B].checked;
                if (D == "0" || D == null) F.push(E[B])
            }
            A.push(F)
        }
        return A
    }
};
STree.prototype.init = function() {
    this.processRelation();
    this.processCheckBox();
    var B = new STreeRootNode(this);
    this.rootNode = B;
    B.refresh();
    this.treeContainer.appendChild(B);
    var A = document.createElement("div");
    this.treeContainer.appendChild(A);
    this.hidden = A;
    B.addChildNode();
    if (isFF) if (registerTopContainer(this.treeContainer.parentNode)) EOSResizeObjects.push(this)
};
STree.prototype.getRootNode = function() {
    return this.rootNode
};
STree.prototype.getSelectedNode = function() {
    return this.cur_node
};
function _streeNode_setIcon(A, B) {
    this.openIconSrc = _stree_addContextPath(A);
    if (B) this.closeIconSrc = _stree_addContextPath(B);
    else this.closeIconSrc = this.openIconSrc;
    if (this.closeIconSrc == "null") this.icon.style.display = "none";
    this.icon.src = this.closeIconSrc
}
function _streeNode_isChecked() {
    var A = this.entity.checked;
    if (A == "1" || A == "2") return true;
    else return false
}
function _streeNode_setNodeText(A) {
    this.cell.innerHTML = A
}
function _streeNode_getNodeText() {
    return this.cell.innerHTML
}
STree.prototype.createHiddenData = function() {
    var C = new StringBuffer();
    for (entityType in this.datasetList) {
        if (entityType == "root") continue;
        var A = 1,
        F = this.getDataset(entityType);
        if (F == null) {
            alert(entityType + STREE_ENTITY_NOT_EXIST);
            return
        }
        var H = F.values,
        I = F.getLength();
        for (var B = 0; B < I; B++) {
            var G = H[B].checked;
            if (G == "1" || G == "2") {
                var E = H[B].type,
                D = this.getEntityInfo(E).submitXpath;
                if (D != "") E = D;
                C.append(_stree_create_hiddenStr(A, H[B], E));
                A = A + 1
            }
        }
    }
    this.hidden.innerHTML = C
};
function _stree_create_hiddenStr(B, G, F) {
    var E = new StringBuffer(),
    A = G.getKeys();
    for (var C = 0; C < A.length; C++) {
        var D = G.getProperty(A[C]);
        E.append("<input type='hidden' name='").append(F).append("[").append(B).append("]/").append(A[C]).append("' value='").append(D).append("'/>")
    }
    return E
}
STree.prototype.autoResizeS1 = function() {
    this.treeContainer.parentNode.style.display = "none"
};
STree.prototype.autoResizeS2 = function() {
    var B = this.treeContainer.parentNode,
    A = B.parentNode;
    while (A != null) if (A.getAttribute && A.getAttribute("layout") != null) {
        B.style.height = A.offsetHeight;
        A = null
    } else A = A.parentNode;
    B.style.display = ""
};
STree.prototype.isInCurrPanel = function() {
    if (_eos_curr_open_panel != null) {
        var A = this.treeContainer.offsetParent;
        while (true) {
            if (A == null) return false;
            if (A == _eos_curr_open_panel.table) return true;
            A = A.offsetParent
        }
    } else return false
};
function SwitchCheckbox(A) {
    this.id = A;
    this.checkbox = null;
    this.hiddenInput = null;
    this.trueValue = "1";
    this.falseValue = "0";
    this.defaultValue = "0";
    this.container = null;
    PageControl.add(A, this)
}
SwitchCheckbox.prototype.init = function() {
    this.checkbox = $id(this.id + "_checkbox");
    this.hiddenInput = $id(this.id);
    this.container = $id(this.id + "_container");
    if (this.checkbox) {
        var B = this;
        function A() {
            if (B.checkbox.checked) B.onSelect();
            else B.onUnSelect();
            B.refreshValue()
        }
        eventManager.add(this.checkbox, "click", A)
    }
};
SwitchCheckbox.prototype.refreshInput = function() {
    if (this.hiddenInput.value == this.trueValue) this.checkbox.checked = true;
    else this.checkbox.checked = false
};
SwitchCheckbox.prototype.refreshValue = function() {
    if (this.checkbox.checked) this.hiddenInput.value = this.trueValue;
    else this.hiddenInput.value = this.falseValue
};
SwitchCheckbox.prototype.onSelect = function() {};
SwitchCheckbox.prototype.onUnSelect = function() {};
SwitchCheckbox.prototype.setValue = function(A) {
    if (this.trueValue == A) this.hiddenInput.value = this.trueValue;
    else this.hiddenInput.value = this.falseValue;
    this.refreshInput()
};
SwitchCheckbox.prototype.getValue = function() {
    this.refreshValue();
    return this.hiddenInput.value
};
SwitchCheckbox.prototype.setFocus = function() {
    this.checkbox.focus()
};
SwitchCheckbox.prototype.lostFocus = function() {};
var TABPANE_TYPE_DIV = "DIV",
TABPANE_TYPE_IFRAME = "IFRAME",
TABPANE_STATUS_NOMAL = 1,
TABPANE_STATUS_OVER = 2,
TABPANE_STATUS_ACTIVE = 3,
TABPANE_STATUS_DISABLED = 4,
TABPANE_STATUS_CLOSED = 5,
TABPANE_STATUS_HIDDEN = 5,
TAB_STYLE_NOMAL = "nomal",
TAB_STYLE_OVER = "hover",
TAB_STYLE_ACTIVE = "active",
TAB_STYLE_DISABLED = "disabled",
TAB_STYLE_LEFT = "left",
TAB_STYLE_MID = "mid",
TAB_STYLE_RIGHT = "right",
TAB_STYLE_TABLE = "container";
function TabPane(A) {
    this.id = A;
    PageControl.add(A, this);
    this.subTabs = new Array();
    this.currTab = null;
    this.defaultTab = 0;
    this.isTitleOut = false;
    this.titleContainer = null;
    this.bodyContainer = null;
    this.bgColor = "#f1f1f1";
    this.borderColor = "#C0C0C0";
    this.container = null;
    this.width = "100%";
    this.height = "100%";
    this.titleWidth = 120;
    this.__titleTR = null;
    this.noCacheIframe = $create("<iframe name='" + this.id + "'/>");
    this.noCacheIframe.name = this.id;
    this.noCacheIframe.style.border = 0;
    this.noCacheIframe.frameBorder = 0;
    this.noCacheIframe.style.width = "100%";
    this.noCacheIframe.style.height = "100%";
    this.pilot = null;
    this.pilotIndex = 0;
    this.firstButton = null;
    this.nextButton = null;
    this.privousButton = null;
    this.lastButton = null;
    this.maxTabs = 0;
    this.inited = false;
    this.pilotInited = false;
    this._titleTD = null;
    this.titleAlign = "top";
    this.styleSuffix = ""
}
TabPane.prototype.append = function(A) {
    this.subTabs.push(A);
    A.group = this
};
TabPane.prototype.getLength = function() {
    return this.subTabs.length
};
TabPane.prototype.getTabs = function() {
    return this.subTabs
};
TabPane.prototype.getActiveTab = function() {
    return this.currTab
};
TabPane.prototype.getActiveTabIndex = function() {
    return this.getCurrIndex()
};
TabPane.prototype.remove = function(C) {
    for (var B = 0; B < this.subTabs.length; B++) if (this.subTabs[B] == C) {
        this.subTabs.splice(B, 1);
        if (this.currTab == C) {
            this.currTab = null;
            var A = B - 1;
            A = A < 0 ? 0 : A;
            this.selectTab(A)
        }
        return true
    }
    return false
};
TabPane.prototype.buildTitles = function() {
    var B = this.getWidth(),
    C = 0;
    for (var A = 0; A < this.subTabs.length; A++) {
        this.appendTitle(this.subTabs[A]);
        C += this.subTabs[A].getWidth();
        if (B - C < 60) {
            this.subTabs[A].hideTitle();
            this.isTitleOut = true
        } else this.subTabs[A].showTitle()
    }
    if (this.isTitleOut) this.initPilot();
    this.inited = true
};
TabPane.prototype.getWidth = function() {
    var A = 0;
    if (String(this.width).indexOf("%") > 0) A = parseInt(this.container.offsetWidth);
    else A = parseInt(this.width);
    if (A == 0) {
        var C = this.container.parentNode;
        while (C) {
            var B = parseInt(C.offsetWidth);
            if (B != 0) return B;
            C = C.parentNode
        }
        return document.body.offsetWidth
    } else return A
};
TabPane.prototype.getIndex = function(B) {
    for (var A = 0; A < this.subTabs.length; A++) if (this.subTabs[A] == B) return A;
    return - 1
};
TabPane.prototype.getCurrIndex = function() {
    return this.getIndex(this.currTab)
};
TabPane.prototype.getShowTabIndex = function() {
    for (var A = 0; A < this.subTabs.length; A++) if (!this.subTabs[A].isHidden) return A;
    return - 1
};
TabPane.prototype.showTitleEnd = function() {
    var A = this.subTabs[this.subTabs.length - 1];
    if (A) return ! A.isHidden;
    return true
};
TabPane.prototype.refreshTitles = function() {
    this.isTitleOut = false;
    var C = this.getWidth(),
    D = 0,
    A = this.getShowTabIndex();
    for (var B = 0; B < this.subTabs.length; B++) if (B < A) this.subTabs[B].hideTitle();
    else {
        D += this.subTabs[B].getWidth();
        if (C - D < 60) {
            this.subTabs[B].hideTitle();
            this.isTitleOut = true
        } else this.subTabs[B].showTitle()
    }
    if (this.isTitleOut) {
        if (!this.pilotInited) this.initPilot();
        this.showPilot(this.pilotIndex);
        this.pilot.style.display = ""
    } else if (this.pilot) this.pilot.style.display = "none"
};
TabPane.showNextTab = function(C) {
    var B = $id(C);
    if (!B.showTitleEnd()) {
        var A = B.getShowTabIndex() + 1;
        B.showPilot(A)
    }
};
TabPane.showPrivousTab = function(C) {
    var B = $id(C),
    A = B.getShowTabIndex() - 1;
    B.showPilot(A)
};
TabPane.prototype.initPilot = function() {
    this.pilot = $id(this.id + "_nav_pilot");
    var A = "<table  border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"width:40px;height:20px;overflow:hidden\"><tr><td>";
    A += "<div onclick=\"TabPane.showPrivousTab('" + this.id + "')\" onmouseover=\"this.className='button-privous-hover'\" onmouseout=\"this.className='button-privous'\" class=\"button-privous\">&nbsp;</div>";
    A += "</td><td>";
    A += "<div onclick=\"TabPane.showNextTab('" + this.id + "')\" class=\"button-next\" onmouseover=\"this.className='button-next-hover'\" onmouseout=\"this.className='button-next'\">&nbsp;</div>";
    A += "</td></tr></table>";
    this.pilot.innerHTML = A;
    this.pilot.style.display = "";
    this.pilotInited = true
};
TabPane.prototype.showPilot = function(A) {
    var C = this.getWidth(),
    D = 0;
    for (var B = 0; B < this.subTabs.length; B++) if (B < A) {
        this.subTabs[B].hideTitle();
        this.isTitleOut = true
    } else {
        D += this.subTabs[B].getWidth();
        if (C - D < 60) {
            this.subTabs[B].hideTitle();
            this.isTitleOut = true
        } else this.subTabs[B].showTitle()
    }
    this.pilotIndex = A
};
TabPane.prototype.appendTitle = function(B) {
    var A = $create("td"),
    C = "<table class=\"" + TAB_STYLE_TABLE + "\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr>";
    C += "<td><div class=\"" + TAB_STYLE_LEFT + "\"></div></td>";
    C += "<td nowarp class=\"" + TAB_STYLE_MID + "\">";
    C += "<div style=\"white-space:nowrap;page-break-before: always;page-break-after: always;\">";
    C += B.getTitle();
    C += "</div></td>";
    if (B.showCloseButton) {
        C += "<td nowarp class=\"" + TAB_STYLE_MID + "\">";
        C += "<a class=\"close\" href=\"#\" onclick=\"$o('" + this.id + "').getTab('" + B.id + "').close();\">";
        if (isIE) C += "<div></div></a>";
        else C += "<div class=\"close\"></div></a>";
        C += "</td>"
    }
    if (B.allowReload) {
        C += "<td nowarp class=\"" + TAB_STYLE_MID + "\">";
        C += "<a class=\"reload\" href=\"#\" onclick=\"$o('" + this.id + "').getTab('" + B.id + "').reload();\">";
        if (isIE) C += "<div></div></a>";
        else C += "<div class=\"reload\"></div></a>";
        C += "</td>"
    }
    C += "<td><div class=\"" + TAB_STYLE_RIGHT + "\"></div></div>";
    C += "</td></tr></table>";
    A.className = TAB_STYLE_NOMAL + this.styleSuffix;
    A.innerHTML = C;
    B.__titleTD = A;
    B.setTitleTD();
    B.initStatus();
    this.__titleTR.insertBefore(A, this._titleTD);
    B.titleWidth = A.offsetWidth
};
TabPane.prototype.init = function() {
    this.container = $id(this.id + "_container");
    this.titleContainer = $id(this.id + "_title_container");
    this.bodyContainer = $id(this.id + "_body");
    this.__titleTR = $id(this.id + "_nav_tr");
    this._titleTD = $create("td");
    this.__titleTR.style.width = "100%";
    this.__pilotContainer = $id(this.id + "_nav_pilot_container");
    if (this.titleAlign == "top") {
        this.styleSuffix = "";
        this._titleTD.className = "right-top";
        this._titleTD.style.overflow = "hidden"
    } else {
        this.styleSuffix = "-down";
        this.bodyContainer.className = "tab-body-down";
        this._titleTD.className = "right-buttom"
    }
    this.__titleTR.insertBefore(this._titleTD, this.__pilotContainer);
    this.buildTitles();
    this._titleTD.innerHTML = "&nbsp;";
    this._titleTD.style.width = "100%";
    this.selectTab(this.defaultTab)
};
TabPane.prototype.closeTab = function(B) {
    var A;
    if (typeof(B) == "object") A = B;
    else A = this.getTab(B);
    if (A instanceof TabPage) A.close()
};
TabPane.prototype.selectTab = function(B) {
    var A;
    if (typeof(B) == "object") A = B;
    else A = this.getTab(B);
    if (A instanceof TabPage) {
        this.activeTab(A);
        if (this.isTitleOut) this.showPilot(this.pilotIndex)
    }
};
TabPane.prototype.getTab = function(B) {
    if (typeof(B) == "object") {
        if (B instanceof TabPage) return B
    } else {
        for (var A = 0; A < this.subTabs.length; A++) if (this.subTabs[A].id == B) return this.subTabs[A];
        if (!isNaN(B)) if (this.subTabs[B]) return this.subTabs[B]
    }
    return null
};
TabPane.prototype.activeTab = function(A) {
    if (!A.allowSelect) return false;
    if (this.currTab) {
        if (this.currTab == A) return false;
        if (this.currTab.__onUnselect()) if (A.show()) {
            this.currTab.hide();
            this.currTab = A;
            return true
        }
    } else {
        A.show();
        this.currTab = A;
        return true
    }
    return false
};
TabPane.prototype.createBlankTab = function(K, G, F, I, H, C) {
    K = K || this.id + Math.random();
    var E = this.getTab(K);
    if (E) {
        alert("The TabPage of id:" + K + ",title:" + E.getTitle() + " is exists!");
        return null
    }
    var J = "";
    J += "<table border='0' cellspacing='0' cellpadding='0' width='100%' height='100%'>";
    J += "\t<tr>";
    J += "\t\t<td id='" + K + "_container' valign='top'  style='display:none;width:100%;height:100%'>";
    J += "\t\t</td>";
    J += "\t</tr>";
    J += "</table>";
    var B = $create(J);
    B.style.width = "100%";
    B.style.height = "100%";
    var A = firstElement(this.bodyContainer);
    A.appendChild(B);
    var D = new TabPage(K);
    D.type = F;
    D.src = I;
    D.title = G;
    D.cache = C;
    D.showCloseButton = H;
    D.init();
    this.append(D);
    if (this.inited) this.appendTitle(D);
    this.refreshTitles();
    return D
};
TabPane.prototype.createIframeTab = function(B, C, A, D) {
    return this.createBlankTab(D, B, "IFRAME", C, false, A)
};
TabPane.prototype.createDivTab = function(C, B, D) {
    var A = this.createBlankTab(D, C, "DIV", null, false, null);
    A.container.innerHTML = B;
    return A
};
function TabPage(A) {
    this.id = A;
    PageControl.add(A, this);
    this.title = A;
    this.type = TABPANE_TYPE_DIV;
    this.src = null;
    this.container = null;
    this.iframe = null;
    this.onSelectFunc = null;
    this.onUnSelectFunc = null;
    this.onRefreshFunc = null;
    this.__titleTD = null;
    this.showCloseButton = true;
    this.status = TABPANE_STATUS_NOMAL;
    this.group = null;
    this.cache = false;
    this.allowSelect = true;
    this.params = [];
    this.containerTable = null;
    this.titleWidth = 80;
    this.isHidden = false;
    this.isLoad = false;
    this.allowReload = false
}
TabPage.prototype.__onSelect = function() {
    if (this.onSelectFunc) {
        try {
            return eval(this.onSelectFunc + "(this" + ")")
        } catch(e) {
            $handle(e);
            return false
        }
    }
    return true
};
TabPage.prototype.__onUnselect = function() {
    if (this.onUnSelectFunc) {
        try {
            var ret = eval(this.onUnSelectFunc + "(this" + ")");
            if (ret === false) return false;
            else {
                this.container.style.display = "none";
                return true
            }
        } catch(e) {
            $handle(e);
            return false
        }
    }
    return true
};
TabPage.prototype.reload = function() {
    if (this.type == TABPANE_TYPE_IFRAME) if (this.isLoad) {
        var A = this.getUrl();
        if (A.indexOf("?") >= 0) A += "&_date=" + new Date();
        else A += "?_date=" + new Date();
        this.iframe.src = A
    } else {
        this.iframe.src = this.getUrl();
        this.isLoad = true
    }
};
TabPage.prototype.disabled = function() {
    if (this.status == TABPANE_STATUS_NOMAL) {
        this.allowSelect = false;
        this.status = TABPANE_STATUS_DISABLED;
        this.__titleTD.className = TAB_STYLE_DISABLED + this.group.styleSuffix
    }
};
TabPage.prototype.enabled = function() {
    if (this.status == TABPANE_STATUS_DISABLED) {
        this.allowSelect = true;
        this.status = TABPANE_STATUS_NOMAL;
        this.__titleTD.className = TAB_STYLE_NOMAL + this.group.styleSuffix
    }
};
TabPage.prototype.initStatus = function() {
    if (this.allowSelect) this.enabled();
    else this.disabled()
};
TabPage.prototype.init = function() {
    this.container = $id(this.id + "_container");
    this.containerTable = getParentByTagName(this.container, "table");
    if (!isIE) this.containerTable.style.display = "none";
    if (this.type == TABPANE_TYPE_IFRAME) if (this.cache) {
        this.iframe = $create("<iframe name='" + this.id + "'/>");
        this.iframe.style.border = 0;
        this.iframe.frameBorder = 0;
        this.iframe.style.width = "100%";
        this.iframe.style.height = "100%";
        this.container.appendChild(this.iframe);
        this.isLoad = false
    }
};
TabPage.prototype.load = function(A) {
    this.src = A;
    this.group.selectTab(this);
    if (this.type == TABPANE_TYPE_IFRAME) if (this.cache) this.iframe.src = this.getUrl();
    else this.group.noCacheIframe.src = this.getUrl()
};
TabPage.prototype.getTitle = function() {
    if (this.onRefreshFunc) {
        try {
            return eval(this.onRefreshFunc + "(this.title,this)")
        } catch(e) {}
    }
    return this.title
};
TabPage.prototype.onClose = function() {};
TabPage.prototype.close = function() {
    try {
        this.onClose()
    } catch(A) {
        $handle(A);
        return false
    }
    if (this.group.getLength() > 1) {
        this.group.remove(this);
        this.__titleTD.parentNode.removeChild(this.__titleTD);
        this.container.id = "";
        this.container.style.display = "none";
        if (!isIE) this.containerTable.style.display = "none";
        this.group.refreshTitles()
    }
};
TabPage.prototype.active = function() {
    var A = this.getTabPane();
    A.activeTab(this)
};
TabPage.prototype.hide = function() {
    this.container.style.display = "none";
    if (!isIE) this.containerTable.style.display = "none";
    if (this.status != TABPANE_STATUS_DISABLED) {
        this.status = TABPANE_STATUS_NOMAL;
        this.__titleTD.className = TAB_STYLE_NOMAL + this.group.styleSuffix
    } else this.__titleTD.className = TAB_STYLE_DISABLED + this.group.styleSuffix;
    this.setTitleTD()
};
TabPage.prototype.show = function() {
    var A = this.__onSelect();
    if (A === false) return false;
    this.container.style.display = "";
    if (!isIE) this.containerTable.style.display = "";
    if (this.type == TABPANE_TYPE_IFRAME) if (!this.cache) {
        this.container.appendChild(this.group.noCacheIframe);
        this.group.noCacheIframe.src = this.getUrl()
    } else if (!this.isLoad) {
        this.reload();
        this.isLoad = true
    }
    this.status = TABPANE_STATUS_ACTIVE;
    this.__titleTD.className = TAB_STYLE_ACTIVE + this.group.styleSuffix;
    this.setTitleTD();
    return true
};
TabPage.prototype.setTitleTD = function() {
    var B = this.group,
    A = this.__titleTD,
    C = this;
    A.onclick = function() {
        var D = this.childNodes[0].rows[0],
        E = D.cells[0].getElementsByTagName("div")[0];
        E.style.backgroundImage = "";
        E = D.cells[D.cells.length - 1].getElementsByTagName("div")[0];
        for (var A = 1; A < D.cells.length - 1; A++) D.cells[A].style.backgroundImage = "";
        E.style.backgroundImage = "";
        B.selectTab(C)
    };
    A.onmouseover = function() {
        if (C.status == TABPANE_STATUS_NOMAL) {
            var B = this.childNodes[0].rows[0],
            D = B.cells[0].getElementsByTagName("div")[0],
            E = getCurrentStyle(D, "backgroundImage");
            D.style.backgroundImage = E.replace("tabs_nomal_left", "tabs_hover_left");
            D = B.cells[B.cells.length - 1].getElementsByTagName("div")[0];
            for (var A = 1; A < B.cells.length - 1; A++) B.cells[A].style.backgroundImage = E.replace("tabs_nomal_left", "tabs_hover_center");
            D.style.backgroundImage = E.replace("tabs_nomal_left", "tabs_hover_right")
        }
        eventManager.stopPropagation()
    };
    A.onmouseout = function() {
        if (C.status == TABPANE_STATUS_NOMAL) {
            var B = this.childNodes[0].rows[0],
            D = B.cells[0].getElementsByTagName("div")[0];
            D.style.backgroundImage = "";
            D = B.cells[B.cells.length - 1].getElementsByTagName("div")[0];
            for (var A = 1; A < B.cells.length - 1; A++) B.cells[A].style.backgroundImage = "";
            D.style.backgroundImage = ""
        }
        eventManager.stopPropagation()
    }
};
TabPage.prototype.addParam = function(A, B) {
    this.params.push({
        key: A,
        value: B
    })
};
TabPage.prototype.getUrl = function() {
    if (this.src) {
        var D = "";
        for (var A = 0; A < this.params.length; A++) {
            var B = this.params[A];
            D += "&" + B.key + "=" + B.value
        }
        var C = this.src;
        if (C.indexOf("/") == 0) if (C.indexOf(contextPath) != 0) C = contextPath + C;
        if (C.indexOf("?") < 0) {
            if (D.length > 0) D = "?" + D;
            D = D.replace("&", "")
        }
        return C + D
    }
    return null
};
TabPage.prototype.hideTitle = function() {
    this.isHidden = true;
    this.__titleTD.style.display = "none"
};
TabPage.prototype.showTitle = function() {
    this.isHidden = false;
    this.__titleTD.style.display = ""
};
TabPage.prototype.getWindow = function() {
    var A = window;
    if (this.type == TABPANE_TYPE_IFRAME) if (this.cache) A = this.iframe.contentWindow;
    else A = this.group.noCacheIframe.contentWindow;
    return A
};
TabPage.prototype.getDocument = function() {
    var A = document;
    if (this.type == TABPANE_TYPE_IFRAME) if (this.cache) A = this.iframe.document || this.iframe.contentDocument;
    else A = this.group.noCacheIframe.document || this.group.noCacheIframe.contentDocument;
    return A
};
TabPage.prototype.getTabPane = function() {
    return this.group
};
TabPage.prototype.getWidth = function() {
    if (this.titleWidth == 0) if (this.title) return (getTextWidth(this.title) + 12);
    else return 80;
    return this.titleWidth
};
function getTextWidth(B) {
    var C = document.createElement("span");
    C.innerHTML = B;
    document.body.appendChild(C);
    var A = C.offsetWidth;
    document.body.removeChild(C);
    return A
}
function ComboSelect(A) {
    this.id = A;
    this.Obj = A;
    this.inputObject = null;
    this.eventObject = null;
    this.hiddenObject = null;
    this.canClose = true;
    this.container = null;
    this.focusStatus = false;
    this.mouseOffset = null;
    this.showStatus = false;
    this.win = window;
    this.textField = null;
    this.valueField = null;
    this.filterField = null;
    this.submitXpath = null;
    this.xpath = null;
    this.dataXML = null;
    this.showMoreDay = true;
    this.date = null;
    this.timer = null;
    this.timeSelect = null;
    this.width = 100;
    this.optionsWidth = null;
    this.optionsHeight = "180px";
    this.allowInput = false;
    this.filterField = null;
    this.startWith = true;
    this.isFirst = true;
    this.__subComponent = [];
    this.defaultText = "";
    this.validateFunc = function() {
        return true
    };
    this.validate = this.validateFunc;
    this.isFirstLoad = true;
    this.initParamFunc = null;
    this.nullText = null;
    this.filterType = "startwith";
    this.showValue = false;
    this._showAll = true;
    PageControl.add(this.id, this)
}
ComboSelect.prototype.onPageLoad = function() {
    PageControl.registerRelation(this.linkId, this.id);
    var B = this;
    function A() {
        B.init();
        B.loadData();
        B.refresh()
    }
    eventManager.add(window, "load", A)
};
ComboSelect.currentComboSelect = null;
ComboSelect.editorContainer = null;
ComboSelect.doc = document;
ComboSelect.prototype.showEditor = function(A) {
    this.container.style.display = "";
    try {
        this.inputObject.focus()
    } catch(B) {}
};
ComboSelect.prototype.hideEditor = function(A) {
    this.hide();
    if (this.container) this.container.style.display = "none"
};
ComboSelect.prototype.getSelectEntity = function() {
    return this.selectEntity
};
ComboSelect.prototype.setValue = function(A, E) {
    var D = this.getEntityByValue(A);
    if (!D) return;
    var C = D.__index,
    B = this.optionsTable.tBodies[0].rows;
    if (!isNaN(C) && B && B.length > 0) this.selectOption(B[C], E)
};
ComboSelect.prototype.getValue = function() {
    if (!this.allowInput) return this.hiddenObject.value;
    var A = this.getEntityByText();
    return A ? A.getProperty(this.valueField) : this.inputObject.value
};
ComboSelect.prototype.getDisplayValue = function(B) {
    var A = this.getEntityByValue(B);
    return A ? A.getProperty(this.textField) : B
};
ComboSelect.prototype.getText = function() {
    return this.inputObject.value
};
ComboSelect.prototype.getEntityByValue = function(F, C) {
    var E = this.datasetExp ? this.datasetExp.getLength() : 0;
    F = "" + (F || this.hiddenObject.value || ""); (C === true) && (F = F.toLowerCase());
    for (var A = 0; A < E; A++) {
        var D = this.datasetExp.get(A),
        B = D ? D.getProperty(this.valueField) : null;
        if (B !== undefined && B !== null) { (C === true) && (B = ("" + B).toLowerCase());
            if (F === B) {
                D.__index = A;
                return D
            }
        }
    }
    return null
};
ComboSelect.prototype.getEntityByText = function(F, C) {
    var E = this.datasetExp ? this.datasetExp.getLength() : 0;
    F = "" + (F || this.inputObject.value || ""); (C === true) && (F = F.toLowerCase());
    for (var A = 0; A < E; A++) {
        var D = this.datasetExp.get(A),
        B = D ? D.getProperty(this.textField) : null;
        if (B !== undefined && B !== null) { (C === true) && (B = ("" + B).toLowerCase());
            if (F === B) {
                D.__index = A;
                return D
            }
        }
    }
    return null
};
ComboSelect.prototype.setWidth = function(A) {
    this.width = A + "";
    this.initSize()
};
ComboSelect.prototype.getWidth = function() {
    return this.width
};
ComboSelect.prototype.initSize = function() {
    if (this.width) {
        this.width = this.width + "";
        this.container.style.width = this.width;
        if (this.container.offsetWidth != 0) if (this.width.indexOf("%") != -1) {
            if (isFF) this.inputObject.style.width = this.width;
            else if (isIE) {
                this.container.style.width = "";
                this.inputObject.style.width = this.width
            }
        } else if (this.width.indexOf("px") != -1) this.inputObject.style.width = this.width.replace("px", "") - 17;
        else this.inputObject.style.width = this.width - 17
    }
};
ComboSelect.prototype.setPosition = function(D, A, B, C) {
    try {
        this.hide();
        if (this.container) {
            this.width = B || this.width;
            this.height = C || this.height;
            this.container.style.position = "absolute";
            this.container.style.width = B;
            var E = getMaxZindex(document);
            if (this.container.style.zIndex != E) this.container.style.zIndex = E;
            this.container.style.display = isFF ? "-moz-inline-box": "inline";
            this.container.style.top = A;
            this.container.style.left = D;
            this.eventObject.style.height = C;
            this.container.style.height = C;
            this.inputObject.style.height = C;
            this.inputObject.style.width = B - 17
        }
    } catch(F) {}
};
ComboSelect.prototype.init = function() {
    if (this.inited === true || this.beforeInit() === false) return;
    this.filterField = this.filterField || this.textField;
    this.container = $id(this.id + "_container");
    this.inputObject = $id(this.id + "_input");
    this.hiddenObject = $id(this.id + "_hidden");
    this.readonly = (this.readonly === true || this.readonly === false) ? this.readonly: this.readOnly;
    this.inputObject.readOnly = !!this.readonly;
    this.hiddenObject.readOnly = !!this.readonly;
    this.inputObject.disabled = !!this.disabled;
    this.hiddenObject.disabled = !!this.disabled;
    this.eventObject = $id(this.id + "_button");
    this.button = this.eventObject;
    this.text = this.inputObject;
    this.hidden = this.hiddenObject;
    var comboSelect = this;
    this.eventObject.src = contextPath + "/common/skins/skin0/images/comboselect/comboSelect_button.gif";
    this.eventObject.onmouseover = function() {
        if (comboSelect.getDisabled() || comboSelect.getReadOnly()) return;
        this.src = contextPath + "/common/skins/skin0/images/comboselect/comboSelect_button_over.gif"
    };
    this.eventObject.onmouseout = function() {
        if (comboSelect.getDisabled() || comboSelect.getReadOnly()) return;
        this.src = contextPath + "/common/skins/skin0/images/comboselect/comboSelect_button.gif"
    };
    this.eventObject.onmousedown = function() {
        if (comboSelect.getDisabled() || comboSelect.getReadOnly()) return;
        this.src = contextPath + "/common/skins/skin0/images/comboselect/comboSelect_button_down.gif"
    };
    this.initSize();
    this.inputObject.value = this.defaultText;
    this.validate = (typeof this.validateFunc) == "string" ? eval(this.validateFunc) : this.validateFunc;
    this.startWith = (this.filterType.toLowerCase() != "like");
    this.initEvent();
    this.inited = true;
    this.afterInit()
};
ComboSelect.prototype.isFocus = function() {
    return this.focusStatus
};
ComboSelect.prototype.cancleFilter = function(C) {
    var B = this.optionsTable.tBodies[0].rows;
    for (var A = 0; A < B.length; A++) B[A].style.display = "";
    return B.length
};
ComboSelect.prototype.filter = function() {
    var A = this.inputObject.value,
    B = 0;
    if (A !== undefined && A !== null) B = this.doFilter(A);
    else B = this.cancleFilter(A);
    return B
};
ComboSelect.prototype.getFilterNum = function() {
    var C = 0,
    B = this.optionsTable.tBodies[0].rows;
    for (var A = 0; A < B.length; A++) if (B[A].style.display != "none") C++;
    return C
};
ComboSelect.prototype.doFilter = function(J, E) {
    var C = this.optionsTable.tBodies[0].rows;
    J = J || this.inputObject.value || "";
    J = J.toLowerCase();
    var F = 0,
    H = null,
    B = false;
    for (var A = 0; A < C.length; A++) {
        var I = this.datasetExp.get(A),
        D = I ? I.getProperty(this.filterField) : null;
        C[A].style.display = E === false ? "": "none";
        if (this.selectOptionRow == C[A]) B = true;
        if (D !== undefined && D !== null) {
            D = ("" + D).toLowerCase();
            var G = D.indexOf(J);
            if (this.startWith && G == 0 || !this.startWith && G >= 0) {
                C[A].style.display = "";
                if (!H) {
                    H = C[A];
                    this.activeOption(H)
                }
                F++
            }
        }
    }
    if (B);
    return F
};
ComboSelect.prototype._syncInputValue = function() {
    if (this.hiddenObject.value !== this.inputObject.value) {
        this.hiddenObject.value = this.inputObject.value;
        this.selectEntity = new Entity();
        this.selectEntity.setProperty(this.valueField, this.hiddenObject.value);
        this.selectEntity.setProperty(this.textField, this.hiddenObject.value);
        return true
    }
    return false
};
ComboSelect.prototype._onChange = function() {
    if (this.onChangeFunc) {
        if (typeof(this.onChangeFunc) == "string") this.onChangeFunc = eval(this.onChangeFunc);
        this.onChangeFunc(this.selectEntity, this)
    }
};
ComboSelect.prototype.onInputBlur = function() {
    var F = this;
    if (checkInput(this.inputObject) == false) return;
    var C = true;
    if (this.validate) C = this.validate();
    if (C) if (!F.showStatus) {
        var B = F.getFilterNum(),
        G = F.hiddenObject.value,
        D = F.getEntityByRow(F.activeOptionRow),
        E,
        A;
        if (D) {
            E = D.getProperty(F.valueField);
            A = D.getProperty(F.textField)
        }
        if (G != E && A == F.inputObject.value) F.selectOption();
        else if (F.allowInput);
        else if (!F.allowInput) F.selectOption(F.selectOptionRow, (G != E))
    }
};
ComboSelect.prototype.reset = function() {};
ComboSelect.prototype.initEvent = function() {
    var D = this,
    E = "," + [EOSKey.TAB, EOSKey.ENTER, EOSKey.SHIFT, EOSKey.CTRL, EOSKey.PAUSE, EOSKey.CAPSLOCK, EOSKey.PAGEUP, EOSKey.PAGEDOWN, EOSKey.END, EOSKey.HOME, EOSKey.LEFT, EOSKey.UP, EOSKey.RIGHT, EOSKey.DOWN, EOSKey.INSERT, EOSKey.WIN, EOSKey.WIN_R, EOSKey.MENU, EOSKey.F1, EOSKey.F2, EOSKey.F3, EOSKey.F4, EOSKey.F5, EOSKey.F6, EOSKey.F7, EOSKey.F8, EOSKey.F9, EOSKey.F10, EOSKey.F11, EOSKey.F12, EOSKey.NUMLOCK, EOSKey.SCROLLLOCK].join(",") + ",";
    function B(A) {}
    function C(A) {
        if (D.getDisabled() || D.getReadOnly()) return;
        A = A || window.event;
        if (A.keyCode == EOSKey.ENTER) eventManager.stopEvent();
        else if (A.keyCode == EOSKey.UP) {
            if (!D.showStatus) D.show(true);
            else D.activePrevOption();
            eventManager.stopEvent(A)
        } else if (A.keyCode == EOSKey.DOWN) {
            if (!D.showStatus) D.show(true);
            else D.activeNextOption();
            eventManager.stopEvent(A)
        }
    }
    function H(B) {
        if (D.getDisabled() || D.getReadOnly()) return;
        if (this.optionsTable && this.optionsTable.tBodies && this.optionsTable.tBodies[0]) return;
        B = B || window.event;
        $error("key:" + B.keyCode);
        eventManager.stopEvent(B);
        if (B.keyCode == EOSKey.ENTER) {
            eventManager.stopEvent();
            if (!D.showStatus) D.show(true);
            else {
                var A = D.getFilterNum();
                if (A > 0 || !D.allowInput) D.selectOption(D.actionOptionRow);
                D.hide()
            }
        } else if (B.keyCode == EOSKey.ESC) {
            if (D.showStatus) D.hide();
            else {
                PageControl.setFocus(D);
                D.show(true)
            }
            eventManager.stopEvent(B)
        } else if (B.keyCode == EOSKey.DOWN) {
            if (!D.showStatus) D.show(true);
            else D.activeNextOption();
            eventManager.stopEvent(B)
        } else if (E.indexOf("," + B.keyCode + ",") < 0 && (!D.beforeFilter || D.beforeFilter(D, B.keyCode) !== false)) {
            D.show();
            eventManager.stopEvent(B);
            if (D.afterFilter) D.afterFilter(D);
            if (D.afterFilter) D.afterFilter(D);
            A = D.filter();
            if (D.allowInput) if (D._syncInputValue()) D._onChange()
        }
        eventManager.stopEvent()
    }
    function A(B) {
        if (D.getDisabled() || D.getReadOnly()) return;
        if (B.keyCode != EOSKey.ESC) return;
        B = B || window.event;
        if (B.keyCode == EOSKey.ENTER) {
            eventManager.stopEvent();
            var A = D.getFilterNum();
            if (A < 1 && D.allowInput) if (D._syncInputValue()) D._onChange()
        } else if (B.keyCode == EOSKey.TAB) D.onInputBlur();
        else if (E.indexOf("," + B.keyCode + ",") < 0 && (!D.beforeFilter || D.beforeFilter(D, B.keyCode) !== false)) {
            A = D.filter();
            eventManager.stopEvent(B);
            if (D.afterFilter) D.afterFilter(D);
            D.show()
        }
    }
    function G(B) {
        if (D.getDisabled() || D.getReadOnly()) return;
        B = B || window.event;
        var A = eventManager.getWheel(B);
        if (A < 0) {
            D.activeNextOption();
            D.selectOption()
        } else if (A > 0) {
            D.activePrevOption();
            D.selectOption()
        }
        eventManager.stopEvent()
    }
    function F() {
        eventManager.stopEvent();
        if (D.getDisabled() || D.getReadOnly()) return;
        D.eventObject.src = contextPath + "/common/skins/skin0/images/comboselect/comboSelect_button_over.gif";
        if (D.showStatus) D.hide();
        else D.show(true)
    }
    if (!this.inputObject.readOnly) eventManager.add(this.inputObject, "blur",
    function(A) {
        D.onInputBlur(A)
    });
    this.container.onmousedown = function() {
        var A = eventManager.getElement();
        if (A == D.inputObject) {
            if (D.showStatus) eventManager.stopEvent();
            try {
                D.inputObject.focus()
            } catch(B) {}
        } else if (A == D.eventObject) if (D.showStatus) eventManager.stopEvent()
    };
    eventManager.add(this.inputObject, "keydown", C);
    eventManager.add(this.inputObject, "keyup", H);
    eventManager.add(this.eventObject, "click",
    function() {
        eventManager.stopEvent()
    });
    eventManager.add(this.eventObject, "mouseup", F);
    eventManager.add(this.inputObject, "focus",
    function() {
        D.inputObject.select()
    })
};
ComboSelect.prototype.show = function(E) {
    PageControl.addtoStack(this);
    if (this.optionsDiv && this.optionsDiv.style && !this.disabled) {
        if (this.optionsWidth && this.optionsWidth.indexOf("%") > 0) this.optionsDiv.style.width = this.container.clientWidth * parseInt(this.optionsWidth) / 100;
        else this.optionsDiv.style.width = this.optionsWidth || isFF ? this.container.offsetWidth - 8 : this.container.offsetWidth;
        if (E === true) if (this.optionsTable && this.optionsTable.tBodies && this.optionsTable.tBodies[0]) this.cancleFilter();
        this.optionsDiv.style.display = "block";
        var B = getMaxZindex(document);
        if (this.optionsDiv.style.zIndex != B) this.optionsDiv.style.zIndex = B;
        var A = getScreenPosition(this.inputObject, this.win);
        if (A[0] >= 0) setElementXY(this.optionsDiv, [isFF ? A[0] : A[0], A[1] + this.inputObject.offsetHeight]);
        try {
            this.inputObject.focus()
        } catch(D) {}
        this.showStatus = true;
        var C = this.optionsDiv.firstChild.nextSibling;
        initShadow(C);
        if (isFF) C.nextSibling.style.width = this.optionsDiv.offsetWidth + 7
    }
};
ComboSelect.prototype.hide = function() {
    if (this.optionsDiv && this.optionsDiv.style) this.optionsDiv.style.display = "none";
    this.showStatus = false
};
ComboSelect.prototype.onLoadData = function() {};
ComboSelect.prototype.addParam = function(A, B) {
    this.paramList = this.paramList || [];
    this.paramList.push({
        key: A,
        value: B
    })
};
ComboSelect.prototype.loadData = function() {
    if (this.beforeLoadData() === false) return;
    if (!this.linkId && !this.queryAction && this.xpath) {
        var xmlZone = document.getElementById(this.id + "_xml");
        this.dataXML = xmlZone ? xmlZone.innerHTML: null
    } else if (this.linkId && this.linkId.indexOf("xml:") == 0) {
        xmlZone = document.getElementById(this.linkId.substring(4));
        this.dataXML = xmlZone ? xmlZone.innerHTML: null
    } else if (this.linkId && this.isFirstLoad) {
        this.isFirstLoad = false;
        return
    }
    var xmlDom, _dataXML = this.dataXML;
    this.datasetExp = new Dataset();
    if (this.nullText !== null) {
        var emptyEntity = new Entity();
        emptyEntity.setProperty(this.valueField, "");
        emptyEntity.setProperty(this.textField, this.nullText);
        this.setValue("");
        this.datasetExp.addEntity(emptyEntity)
    }
    if (_dataXML) {
        this.datasetExp.appendDataset(Dataset.create(_dataXML, this.xpath));
        return
    }
    if (this.onLoadData() !== false) {
        if (!this.queryAction) return;
        var ajax = new HideSubmit(this.queryAction),
        param = "";
        if (this.queryParam) param += this.queryParam;
        if (this.pageParam) param += this.pageParam;
        if (this.initParamFunc) {
            try {
                param += eval(this.initParamFunc + "()")
            } catch(e) {
                $handle(e)
            }
        }
        if (param == "") param = null;
        if (this.filterID) {
            var form = $id(this.filterID);
            if (form) for (var i = 0; i < form.elements.length; i++) {
                var elem = form.elements[i];
                if (elem.name) ajax.addParam(elem.name, elem.value)
            }
        }
        if (this.paramList) for (i = 0; i < this.paramList.length; i++) {
            elem = this.paramList[i];
            if (elem) ajax.addParam(elem.key, elem.value)
        }
        ajax.loadData(param);
        xmlDom = ajax.retDom;
        this.datasetExp.appendDataset(Dataset.create(xmlDom, this.xpath, null))
    }
};
ComboSelect.prototype.getEntityByRow = function(A) {
    if (!A) return null;
    var C = A.getAttribute("__entity_rowno") / 1,
    B = this.datasetExp.get(C);
    return B
};
ComboSelect.prototype.disable = function() {
    this.disabled = true;
    this.inputObject.disabled = true;
    this.hiddenObject.disabled = true;
    this.hide()
};
ComboSelect.prototype.enable = function() {
    this.disabled = false;
    this.inputObject.disabled = false;
    this.hiddenObject.disabled = false
};
ComboSelect.prototype.setDisabled = function(A) {
    if (A) this.disable();
    else this.enable();
    if (A) this.eventObject.style.cursor = "default";
    else this.eventObject.style.cursor = "pointer"
};
ComboSelect.prototype.getDisabled = function(A) {
    return this.disabled
};
ComboSelect.prototype.setReadonly = function(A) {
    this.inputObject.readOnly = A || true
};
ComboSelect.prototype.setReadOnly = function(A) {
    this.readonly = A;
    this.inputObject.readOnly = A;
    if (A) this.eventObject.style.cursor = "default";
    else this.eventObject.style.cursor = "pointer"
};
ComboSelect.prototype.getReadOnly = function(A) {
    return this.readonly
};
ComboSelect.prototype.firstOptionRow = function(C, D) {
    var B = this.optionsTable.tBodies[0].rows;
    C = C || 0;
    D = D || B.length;
    C = C < 0 ? 0 : C;
    D = D < 0 ? 0 : D;
    D = D > B.length ? B.length: D;
    C = C > D ? D: C;
    for (var A = C; A < D; A++) if (B[A].style.display != "none") return B[A];
    return null
};
ComboSelect.prototype.lastOptionRow = function(C, D) {
    var B = this.optionsTable.tBodies[0].rows;
    C = C || 0;
    D = D === 0 ? 0 : (D || B.length);
    C = C < 0 ? 0 : C;
    D = D > B.length - 1 ? B.length - 1 : D;
    D = D < 0 ? 0 : D;
    C = C > D ? D: C;
    if (B.length > 0) for (var A = D; A >= C; A--) if (B[A].style.display != "none") return B[A];
    return null
};
ComboSelect.prototype.activePrevOption = function(B) {
    B = B || this.activeOptionRow;
    var C = 0;
    if (B) C = B.rowIndex - 1;
    var A = this.lastOptionRow(0, C);
    if (A && A != B) this.activeOption(A)
};
ComboSelect.prototype.activeNextOption = function(B) {
    B = B || this.activeOptionRow;
    var C = 0;
    if (B) C = B.rowIndex + 1;
    var A = this.firstOptionRow(C);
    if (A && A != B) this.activeOption(A)
};
ComboSelect.prototype.activeOption = function(A) {
    if (this.activeOptionRow) removeClass(this.activeOptionRow, "eos-selectoption");
    addClass(A, "eos-selectoption");
    this.autoScroll(A);
    this.activeOptionRow = A
};
ComboSelect.prototype.selectOption = function(row, refreshSub) {
    this._showAll = true;
    var _change = this.selectOptionRow != row;
    row = row || this.activeOptionRow;
    if (!row) return;
    var entity = this.getEntityByRow(row);
    if (entity) {
        if (this.activeOptionRow) removeClass(this.activeOptionRow, "eos-selectoption");
        if (row.offsetTop < this.optionsDivCore.scrollTop + 2) this.optionsDivCore.scrollTop = row.offsetTop;
        if (row.offsetTop > this.optionsDivCore.scrollTop + this.optionsDivCore.clientHeight - 4) this.optionsDivCore.scrollTop = row.offsetTop - this.optionsDivCore.clientHeight + row.offsetHeight;
        this.selectEntity = entity;
        this.inputObject.value = this.selectEntity.getProperty(this.textField);
        this.hiddenObject.value = this.selectEntity.getProperty(this.valueField);
        if (this.activeOptionRow) removeClass(this.activeOptionRow, "eos-selectoption");
        this.selectOptionRow = row;
        this.activeOptionRow = row;
        addClass(this.activeOptionRow, "eos-selectoption");
        if (this.onChangeFunc && !this.isFirst) {
            if (typeof(this.onChangeFunc) == "string") this.onChangeFunc = eval(this.onChangeFunc);
            this.isFirst = false;
            _change && this.onChangeFunc(this.selectEntity, this)
        }
        this.isFirst = false
    }
    if (refreshSub !== false) this.setSubComponent(entity)
};
ComboSelect.prototype.registerSubComponent = function(B) {
    var A = PageControl.getOne(B);
    this.__subComponent.push(A)
};
ComboSelect.prototype.setSubComponent = function(B) {
    var D;
    if (B) {
        D = B.name;
        B.name = this.submitXpath || D
    }
    for (var A = 0; A < this.__subComponent.length; A++) {
        var C = this.__subComponent[A];
        if (C && C.freshFromEntity) C.freshFromEntity(B)
    }
    if (B) B.name = D
};
ComboSelect.prototype.freshFromEntity = function(A) {
    if (this.queryAction) {
        if (A) this.queryParam = A.toString();
        else this.queryParam = null;
        this.loadData()
    } else if (this.linkField) if (A) {
        this.datasetExp = A.getProperty(this.linkField);
        if (!this.datasetExp) {
            this.datasetExp = new Dataset(this.linkField);
            A.setProperty(this.linkField, this.datasetExp)
        }
    } else this.datasetExp = new Dataset(this.linkField);
    this.refresh()
};
ComboSelect.prototype.refresh = function(D) {
    this.init();
    if (this.beforeRefresh() === false) return;
    var J = this;
    this.datasetExp = D || this.datasetExp;
    var E = this.datasetExp ? this.datasetExp.getLength() : 0;
    if (this.noOrgOptionsHeight == true) this.optionsHeight = ((E > 10 ? 10 : E) * 21 + 2);
    if (this.optionsDiv) removeElement(this.optionsDiv);
    this.optionsDiv = $createElement("div");
    this.optionsDiv.className = "eos-combo-optiondiv";
    this.inputObject.value = "";
    this.hiddenObject.value = "";
    function M() {
        var A = eventManager.getElement();
        if (A != J.eventObject && !J.inputObject.getAttribute("_isFocus") && !J.eventObject.getAttribute("_isFocus")) {
            if (!J.allowInput) {
                J.inputObject.value = J.selectEntity.getProperty(J.textField);
                J.hiddenObject.value = J.selectEntity.getProperty(J.valueField)
            }
            J.hide()
        }
    }
    if (!this.optionsHeight) {
        this.noOrgOptionsHeight = true;
        this.optionsHeight = ((E > 10 ? 10 : E) * 21 + 2)
    }
    this.optionsDiv.style.height = this.optionsHeight;
    document.body.appendChild(this.optionsDiv);
    this.optionsDiv.onmousedown = function() {
        eventManager.stopEvent()
    };
    function O(C) {
        C = C || window.event;
        var A = C.srcElement || C.target;
        eventManager.stopPropagation();
        if (A.tagName.toLowerCase() != "tr") while ((A = A.parentNode)) if (A.tagName && A.tagName.toLowerCase() == "tr" && A.getAttribute("__entity_rowno")) break;
        var B = getParentByTagName(A, "table");
        return B && B.className == "eos-combo-optiontable" ? A: null
    }
    function I(A) {
        if (J.selectOptionRow) removeClass(J.selectOptionRow, "eos-selectoption");
        J.optionsDiv.setAttribute("_isOver", true);
        var B = O(A);
        addClass(B, "eos-selectoption");
        if (J.activeOptionRow == B) return;
        if (J.activeOptionRow) removeClass(J.activeOptionRow, "eos-selectoption");
        J.activeOptionRow = B
    }
    function N(A) {
        J.optionsDiv.setAttribute("_isOver", false)
    }
    function H(A) {
        var B = O(A);
        if (B) J.selectOption(B);
        J.hide();
        try {
            J.inputObject.focus()
        } catch(C) {}
    }
    eventManager.add(this.optionsDiv, "mouseover", I);
    eventManager.add(this.optionsDiv, "mouseout",
    function() {
        setTimeout(N, 300)
    });
    eventManager.add(this.optionsDiv, "click", H);
    function C() {
        eventManager.stopPropagation()
    }
    eventManager.add(this.optionsDiv, "mousedown", C);
    var K = ["<div class=\"eos-combo-optiondiv-core\"><table  class=\"eos-combo-optiontable\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" ><tbody>"];
    for (var P = 0; P < E; P++) {
        var F = this.datasetExp.get(P),
        B = F.getProperty(this.valueField),
        G = F.getProperty(this.textField);
        K.push("<tr __entity_rowno=\"" + P + "\" >");
        K.push("<td><div><nobr>");
        K.push(G == "" ? " ": G);
        K.push("</nobr></div></td>");
        if (this.showValue) {
            K.push("<td><div>");
            K.push(B);
            K.push("</div></td>")
        }
        K.push("</tr>")
    }
    K.push("</tbody></table ></div>");
    var L = window.isIE ? "<iframe SCROLLING=\"no\" style=\"overflow:hidden;position:absolute;z-index:-1;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft)\" frameborder=\"0\" ></iframe>": "<span style=\"display:none;\"></span>";
    this.optionsDiv.innerHTML = L + K.join("\n");
    this.optionsDiv.childNodes[1].style.height = this.optionsDiv.style.height;
    this.optionsDiv.childNodes[1].style.width = this.optionsDiv.style.width;
    this.optionsDivCore = this.optionsDiv.childNodes[1];
    this.optionsTable = this.optionsDivCore.firstChild;
    var Q = false;
    if (this.value) this.setValue(this.value, false);
    else if (this.optionsTable && this.optionsTable.tBodies && this.optionsTable.tBodies[0]) {
        var A = this.optionsTable.tBodies[0].rows;
        if (A && A.length > 0) {
            this.selectOption(A[0]);
            Q = true
        }
    }
    if (!Q) this.setSubComponent(this.getEntityByValue(this.value));
    this.afterRefresh()
};
ComboSelect.prototype.autoScroll = function(A) {
    var E = A.offsetTop,
    D = E + A.offsetHeight,
    C = this.optionsDivCore.scrollTop,
    B = C + this.optionsDivCore.clientHeight;
    if (E < C) this.optionsDivCore.scrollTop = E;
    else if (D > B) this.optionsDivCore.scrollTop = C + D - B
};
ComboSelect.prototype.boundTree = function(B, D, C) {
    if ($id(B) == null) {
        alert("Can not find tree:" + treeID);
        return
    }
    var A;
    if ($id(B).model != null) A = $id(B + "_container");
    else A = $id(B + "_container").parentNode;
    D = D || "";
    C = C || "";
    this.afterRefresh = function() {
        this.hidden.value = D;
        this.text.value = C;
        this.optionsDiv.childNodes[1].appendChild(A);
        A.onclick = function() {
            eventManager.stopPropagation()
        }
    }
};
ComboSelect.prototype.beforeInit = function() {};
ComboSelect.prototype.afterInit = function() {};
ComboSelect.prototype.beforeLoadData = function() {};
ComboSelect.prototype.afterLoadData = function() {};
ComboSelect.prototype.beforeRefresh = function() {};
ComboSelect.prototype.afterRefresh = function() {};
var DATACELL_STYLE_ROW1 = "EOS_table_row",
DATACELL_STYLE_ROW2 = "ROW_STYLE2_NOCSS",
DATE_FORMAT = "yyyy-MM-dd",
TIME_FORMAT = "HH:mm:ss",
DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss",
PICTURE_BLANK = PICTURE_BLANK || "";
function Datacell(A) {
    this.id = A;
    PageControl.add(A, this);
    this.activeRow = null;
    this.activeCell = null;
    this.table = null;
    this.container = null;
    this.pilot = null;
    this.thead = null;
    this.tbody = null;
    this.isCount = false;
    this.entityName = null;
    this.queryAction = null;
    this.xpath = null;
    this.submitAction = null;
    this.datasetExp = null;
    this.sortParam = null;
    this.queryParam = null;
    this.pageParam = null;
    this.editor = null;
    this.fields = new Array();
    this.keys = new Object();
    this.bodyDiv = null;
    this.scrollEvent = true;
    this.fixedLeft = 0;
    this.editorDiv = null;
    this.editorContainer = null;
    this.styleEntity = null;
    this.__subComponent = new Array();
    this.linkField = null;
    this.linkId = null;
    this.filterId = null;
    this.paramFormId = null;
    this.rowStyles = [DATACELL_STYLE_ROW1, DATACELL_STYLE_ROW2];
    this.styleRow = null;
    this.status = Datacell.STATUS_BLANK;
    this.isModefied = false;
    this.cellHeight = 20;
    this.heightExceptBody = 23 + 25;
    this.headHeight = 23;
    this.toolBarHeight = 25;
    this.showIndex = true;
    this.indexCol = null;
    this.sortArrow = null;
    this.__pagePilot = [];
    this.allowAdd = true;
    this.allowDel = true;
    this.readonly = false;
    this.initParamFunc = null;
    this.SortByServer = false;
    this.submitXpath = null;
    this.minColumnWidth = 30;
    this.toolbarLocation = null;
    this.toolsList = "nav,pagesize,edit,custom,info";
    this.pageSizeList = "none";
    this.defaultColumnWidth = 100;
    this.topToolbarDiv = $createElement("div", {
        className: "eos-grid-toolbar"
    });
    this.topToolbarDiv.style.display = "none";
    this.bottomToolbarDiv = $createElement("div", {
        className: "eos-grid-toolbar"
    });
    this.topToolbarDiv.style.display = "none";
    this.isFirstLoad = true;
    this.pageSize = 10;
    this.datacellContainer = null;
    this.quickEdit = true;
    this.sortAt = "client";
    this.rowSelectStyleClass = "eos-row-selected";
    this.rowEvenStyleClass = "eos-row-even";
    this.rowStyleClass = "eos-row";
    this.headTextAlign = "left";
    this.width = 500;
    this.height = 300;
    this.entityType = null
}
Datacell.prototype.syncRefresh = true;
Datacell.prototype.refreshCell = function(E, F) {
    var B = this,
    A = E.getAttribute("fieldId");
    F = F || B.keys[A];
    if (A == Datacell.__INDEX_NAME || B.beforeRefreshCell(E, F) === false) return;
    var C = E.firstChild,
    D = F.viewType;
    C.innerHTML = B.createCellValue(E, F)
};
Datacell.prototype.activeMe = function() {
    Datacell.activeGrid = this
};
Datacell.prototype.onPageLoad = function() {
    PageControl.registerRelation(this.linkId, this.id);
    var B = this;
    function A() {
        B.init();
        B.loadData();
        B.refresh();
        if (isFF) {
            B.autoResizeS1();
            B.autoResizeS2()
        }
    }
    eventManager.add(window, "load", A)
};
Datacell.prototype.createCellValue = function(cell, field) {
    if (!cell) return;
    var row = cell.parentNode,
    entity = this.getEntity(row),
    key = cell.getAttribute("fieldId");
    if (key == Datacell.__INDEX_NAME) return;
    var field = this.keys[key],
    cellText = entity.getProperty(field.fieldName);
    if (field.onRefreshFunc) {
        if (typeof(field.onRefreshFunc) == "string") field.onRefreshFunc = eval(field.onRefreshFunc);
        cellText = field.onRefreshFunc(cellText, entity, cell.parentNode.rowIndex, cell.cellIndex, this)
    } else if (field.expression) cellText = runExpression(field.template, entity);
    else if (field.editor) cellText = htmlConversion(field.editor.getDisplayValue(cellText));
    else cellText = htmlConversion(cellText === undefined || cellText === null ? field.nullText: cellText);
    cellText = cellText === undefined || cellText === null ? htmlConversion(field.nullText) : cellText;
    return cellText
};
Datacell.prototype.clear = function() {
    if (this.tbody && this.table) {
        var A = this.tbody.cloneNode(false);
        this.table.replaceChild(A, this.tbody);
        this.tbody = A
    }
    if (this.datasetExp) this.datasetExp.clear();
    this.datasetExp = null;
    this.styleRow = null
};
Datacell.prototype.clearup = function() {
    for (var A in this) {
        try {
            this[A] = null
        } catch(B) {
            $handle(B)
        }
    }
};
Datacell.prototype.getField = function(B) {
    if (B === null || B === undefined) return B;
    if (typeof B == "number") return this.fields[B];
    if (typeof B == "string") return this.getFieldByName(B);
    if (String(B.tagName).toLowerCase() == "td") {
        var A = B.getAttribute("fieldId");
        if (A == Datacell.__INDEX_NAME) return null;
        var C = this.keys[A];
        return C
    }
    return B
};
Datacell.prototype.getFieldByName = function(B) {
    for (var A = 0; A < this.fields.length; A++) if (this.fields[A].fieldName == B) return this.fields[A];
    return null
};
Datacell.prototype.getFieldsByName = function(C) {
    var B = [];
    for (var A = 0; A < this.fields.length; A++) if (this.fields[A].fieldName == C) B.push(this.fields[A]);
    return B
};
Datacell.prototype.getFieldCount = function() {
    return this.fields.length
};
Datacell.prototype.setTitle = function(C, A) {
    C = this.getField(C);
    if (C) {
        C.labelSpan.innerHTML = A;
        var B = C.frozeHeadCell;
        if (B && B.firstChild && B.firstChild.firstChild) B.firstChild.firstChild.innerHTML = A
    }
};
Datacell.prototype.setAllowModify = function(B, A) {
    B = this.getField(B);
    if (B) B.allowModify = A
};
Datacell.prototype.copyRow = function(A) {
    return this.cloneEntity(null, A)
};
Datacell.prototype.cloneEntity = function(B, A) {
    B = B || this.getEntity(A || this.activeRow);
    if (B) {
        B = B.clone();
        B.status = Entity.STATUS_NEW
    }
    return B
};
Datacell.prototype.getCellValue = function(B) {
    var A = B.parentNode,
    C = A ? this.getEntity(A) : null;
    if (C) {
        var D = this.getField(B),
        E = D.fieldName;
        return C.getProperty(E)
    }
    return null
};
Datacell.prototype.setCellValue = function(C, A) {
    if (!C) return false;
    var B = C.parentNode,
    D = B ? this.getEntity(B) : null;
    if (D) {
        var E = this.getField(C),
        F = E.fieldName;
        D.setProperty(F, A);
        this.refreshCell(C);
        this.refreshRow(B);
        D.status = Entity.STATUS_MODIFIED;
        this.isModefied = true;
        return true
    }
    return false
};
Datacell.prototype.getCell = function(D, C) {
    var A = parseInt(D);
    if (!isNaN(A)) {
        var B = this.tbody.rows[A];
        if (B) return this.getCellByRow(B, C)
    }
    return null
};
Datacell.prototype.getCellByRow = function(B, C) {
    if (isNaN(C)) {
        var D = C;
        for (var A = 0; A < B.cells.length; A++) if (B.cells[A].getAttribute("fieldId") == D) return B.cells[A]
    } else return B.cells[C]
};
Datacell.prototype.getCellAtRow = function(C, B) {
    var A = this.getRowByCell(C);
    return this.getCellByRow(A, B)
};
Datacell.prototype.getCellByCurrRow = function(A) {
    if (this.activeRow) return this.getCellByRow(this.activeRow, A);
    else return null
};
Datacell.prototype.getRow = function(A) {
    if (typeof A == "number") return this.getRowAt(A);
    if (A.tagName && A.tagName.toLowerCase() == "td") return A.parentNode;
    return A
};
Datacell.prototype.getRowAt = function(A) {
    try {
        return this.table.tBodies[0].rows[A]
    } catch(B) {
        return null
    }
};
Datacell.prototype.getRowByCell = function(A) {
    if (A) if (A.parentNode && A.parentNode.tagName && A.parentNode.tagName.toLowerCase() == "tr") return A.parentNode;
    return null
};
Datacell.prototype.getEntityByCell = function(B) {
    var A = this.getRowByCell(B);
    if (A) return this.getEntity(A);
    return null
};
Datacell.prototype.getCurrRow = function() {
    return this.activeRow
};
Datacell.prototype.getValueForEdit = function(A, B) {
    return A.getProperty(B)
};
Datacell.prototype.init = function() {
    if (this.inited === true || this.beforeInit() === false) return;
    if (this.entityType && !(this.entityType.indexOf("sdo:") == 0 || this.entityType.indexOf("java:") == 0)) this.entityType = "sdo:" + this.entityType;
    this.PAGE_INFO = this.PAGE_INFO || PAGE_INFO;
    this.PAGE_RECORDINFO = this.PAGE_RECORDINFO || PAGE_RECORDINFO;
    this.PAGE_INFO_NOCOUNT = this.PAGE_INFO_NOCOUNT || PAGE_INFO_NOCOUNT;
    this.PAGE_RECORDINFO_NOCOUNT = this.PAGE_RECORDINFO_NOCOUNT || PAGE_RECORDINFO_NOCOUNT;
    this.activeMe();
    this.datacellContainer = $id(this.id + "_container");
    if (this.freezeNumber > 0) this.showIndex = true;
    this.tableMarginLeft = this.showIndex ? 12 : 0;
    this.containerTable = $id(this.id + "_container_table");
    if (isIE){
    	this.containerTable.className = "eos-b-ie " + this.containerTable.className;
    }else{
//    	this.containerTable.className = "eos-b-ie9 " + this.containerTable.className;
    }
    if (("" + this.width).indexOf("%") < 1 && ("" + this.width).indexOf("p") < 1) this.width = parseInt(this.width) + "px";
    if (("" + this.height).indexOf("%") < 1 && ("" + this.height).indexOf("p") < 1) this.height = parseInt(this.height) + "px";
    if (this.freezeNumber && this.freezeNumber > this.fields.length) this.freezeNumber = this.fields.length;
    this.paramFormId = this.paramFormId || this.filterId;
    this.cTop = 0;
    if (!this.toolbarLocation || this.toolbarLocation == "null") {
        this.containerTD = this.containerTable.tBodies[0].rows[0].cells[0];
        this.containerTable.tBodies[0].rows[1].style.height = "0px";
        this.containerTable.tBodies[0].rows[1].style.display = "none"
    } else if (this.toolbarLocation == "top") {
        this.containerTD = this.containerTable.tBodies[0].rows[1].cells[0];
        this.toolbarParent = this.containerTable.tBodies[0].rows[0].cells[0];
        this.toolbarParent.appendChild(this.topToolbarDiv);
        this.toolbarParent.style.height = "25px";
        this.topToolbarDiv.style.display = "block";
        this.topToolbarDiv.style.borderBottomWidth = "1px";
        this.cTop = this.topToolbarDiv.offsetHeight
    } else {
        this.containerTD = this.containerTable.tBodies[0].rows[0].cells[0];
        this.toolbarParent = this.containerTable.tBodies[0].rows[1].cells[0];
        this.toolbarParent.appendChild(this.bottomToolbarDiv);
        this.toolbarParent.style.height = "25px";
        this.bottomToolbarDiv.style.display = "block";
        this.bottomToolbarDiv.style.borderTopWidth = "1px"
    }
    this.container = $createElement("div");
    this.container.className = "eos-grid";
    this.containerTD.appendChild(this.container);
    this.container.innerHTML = "";
    this.container.style.display = "";
    this.viewport = $createElement("div");
    this.viewport.className = "eos-viewport";
    this.container.style.width = "100%";
    this.containerTable.style.width = this.width;
    this.containerTable.style.height = this.height;
    this.bodyDiv = $createElement("div");
    this.bodyDiv.className = "eos-body-div";
    this.bodyDivHeight = this.containerTD.offsetHeight - this.headHeight;
    this.bodyDivHeight = parseInt(this.bodyDivHeight);
    if (this.bodyDivHeight < 0) this.bodyDivHeight = parseInt(this.height) - (this.headHeight + this.toolBarHeight);
    if (this.bodyDivHeight > 0) this.bodyDiv.style.height = this.bodyDivHeight + "px";
    this.headDiv = $createElement("div");
    this.headDiv.className = "eos-head-div";
    this.headWrap = $createElement("div");
    this.headWrap.style.width = "3000px";
    this.headDiv.appendChild(this.headWrap);
    this.container.appendChild(this.viewport);
    this.cTop = 0;
    if (this.toolbarLocation == "top") {
        this.topToolbarDiv.style.display = "block";
        this.topToolbarDiv.style.borderBottomWidth = "1px";
        this.cTop = this.topToolbarDiv.offsetHeight
    } else {
        this.bottomToolbarDiv.style.display = "block";
        this.bottomToolbarDiv.style.borderTopWidth = "1px"
    }
    this.viewport.appendChild(this.headDiv);
    this.viewport.appendChild(this.bodyDiv);
    this.viewport.style.width = "100%";
    this.headDiv.style.width = "100%";
    this.bodyDiv.style.width = "100%";
    this.container.style.display = "";
    this.splitLine = $createElement("div", {
        className: "eos-split-line"
    });
    this.splitLine.style.top = this.cTop + "px";
    this.container.appendChild(this.splitLine);
    this.gridMask = $createElement("div", {
        className: "eos-grid-mask"
    });
    this.gridMask.style.width = this.container.style.width;
    this.gridMask.style.height = this.container.style.height;
    this.container.appendChild(this.gridMask);
    this.initPageParam();
    this.initCSS();
    this.initHead();
    this.initFreezeZone();
    this.initEvent();
    for (var A = 0; A < this.__pagePilot.length; A++) {
        var B = this.__pagePilot[A];
        B.build()
    }
    this.inited = true;
    this.afterInit()
};
Datacell.prototype.selectEventRow = function() {
    var B = eventManager.getElement(),
    A = getParentByTagName(B, "tr");
    this.selectRow(A);
    return A
};
Datacell.prototype.showCellEditor = function(B, A) {
    if (this.activeEditor && this.activeCell) {
        this.editorContainer = $id(this.id + "_" + this.activeField.fieldId + "_field_editor_container") || $id(this.activeField.fieldId + "_field_editor_container");
        var B = this.getValueForEdit(this.getEntity(this.activeCell), this.activeField.fieldName);
        if (this.dict) B = getDictKey(this.dict, B);
        try {
            if (this.editorContainer.parentNode != document.body) document.body.appendChild(this.editorContainer);
            this.activeEditor.hideEditor();
            var D = getPosition(this.activeCell);
            if (this.activeEditor.setPosition) {
                this.editorContainer.style.display = "";
                this.editorContainer.onmousedown = function() {
                    eventManager.stopEvent()
                };
                this.activeEditor.setPosition(D.left, D.top, this.activeCell.offsetWidth, this.activeCell.offsetHeight);
                var C = {};
                C.editor = this.activeEditor;
                C.datacell = this;
                C.hide = function() {
                    if (this.datacell) {
                        this.datacell.hideCellEditor();
                        Datacell.activeGrid = null
                    }
                };
                PageControl.addtoStack(C);
                this.activeEditor.showEditor(this)
            }
            this.activeEditor.setValue(B === undefined || B === null ? "": B)
        } catch(E) {}
    }
};
Datacell.prototype.hideCellEditor = function() {
    if (this.activeCell && this.activeEditor) {
        if (this.editing) {
            var D = this.activeCell.parentNode,
            C = this.activeEditor.getValue(),
            F = this.activeValue,
            B = C;
            if (F != B) {
                var A = this.activeCell.getAttribute("fieldId");
                if (A == Datacell.__INDEX_NAME) return true;
                if (!this.activeEditor.validate()) {
                    this.editing = false;
                    this.unlocked();
                    this.endEdit();
                    return false
                }
                if (this.onUpdateRow(this.activeCell.parentNode)) {
                    var G = this.onUpdateCell(this.activeCell, this.activeEntity, this.activeField.fieldName, C); (G !== false) && this.updateEntity(this.activeEntity, this.activeField.fieldName, C);
                    this.refreshAfterEdit();
                    this.isModefied = true;
                    if (this.linkId && this.linkField) {
                        var E = PageControl.getOne(this.linkId);
                        if (E) {
                            if (E.activeRow) E.getEntity(E.activeRow).status = Entity.STATUS_MODIFIED;
                            E.refreshAfterEdit();
                            E.isModefied = true
                        }
                    }
                    this.activeValue = C
                }
            }
            this.afterEdit(B, F, this)
        }
        this.editing = false;
        try {
            this.activeEditor.hideEditor()
        } catch(H) {}
        return true
    }
    return true
};
Datacell.prototype.refreshAfterEdit = function() {
    if (this.syncRefresh) this.refreshRow(this.activeCell ? this.activeCell.parentNode: this.activeRow);
    else this.refreshCell(this.activeCell, this.activeField)
};
Datacell.prototype.startEdit = function() {
    if (!this.activeEditor || !this.activeField || !this.activeEntity || !this.activeField.allowModify && this.activeEntity.status != Entity.STATUS_NEW) return;
    this.activeMe();
    var A = this.getActiveCell();
    if (this.beforeEdit(A, A.cellIndex, A.parentNode.rowIndex) == false) return;
    this.editing = true;
    this.showCellEditor(this.activeValue, this.activeEntity)
};
Datacell.prototype.endEdit = function() {
    this.hideCellEditor();
    this.editing = false;
    this.beforeShowEditor = false
};
Datacell.prototype._onKeyDown = function(B) {
    B = B || window.event;
    var D = this.activeCell,
    A = null,
    F = B.keyCode;
    if (F == EOSKey.ESC) {
        eventManager.stopEvent();
        this.editing = false;
        this.endEdit()
    } else if (F == EOSKey.ENTER) {
        var C = eventManager.getElement();
        if (C != null && C.tagName.toLowerCase() == "textarea") return;
        eventManager.stopEvent();
        this.editing ? this.endEdit() : this.startEdit()
    } else if (this.editing && F == EOSKey.TAB) {
        eventManager.stopEvent();
        this.endEdit();
        A = nextElement(D);
        if (!A) {
            A = nextElement(D.parentNode);
            if (A) A = A.cells[0]
        }
        this.setActiveCell(B, A);
        var E = this;
        window.setTimeout(function() {
            E.startEdit()
        },
        10)
    } else if (D && !this.editing) {
        switch (F) {
        case EOSKey.LEFT:
            eventManager.stopEvent();
            A = prevElement(D);
            if (!A) {
                A = prevElement(D.parentNode);
                if (A) A = A.cells[A.cells.length - 1]
            }
            break;
        case EOSKey.TAB:
        case EOSKey.RIGHT:
            eventManager.stopEvent();
            A = nextElement(D);
            if (!A) {
                A = nextElement(D.parentNode);
                if (A) A = A.cells[0]
            }
            break;
        case EOSKey.DOWN:
            eventManager.stopEvent();
            A = nextElement(D.parentNode);
            if (A) A = A.cells[D.cellIndex];
            break;
        case EOSKey.UP:
            eventManager.stopEvent();
            A = prevElement(D.parentNode);
            if (A) A = A.cells[D.cellIndex];
            break
        }
        if (A) {
            eventManager.stopEvent();
            this.setActiveCell(B, A)
        }
    }
};
Datacell.prototype.autoScroll = function() {
    var B = this.activeCell.offsetLeft + this.tableMarginLeft,
    I = B + this.activeCell.offsetWidth,
    H = this.activeCell.offsetTop,
    E = H + this.activeCell.offsetHeight,
    G = this.bodyDiv.scrollLeft,
    D = this.bodyDiv.scrollTop,
    F = G + this.bodyDiv.clientWidth,
    C = D + this.bodyDiv.clientHeight,
    A = this.freezeHeadDiv.offsetWidth;
    if (B < G) this.bodyDiv.scrollLeft = B - this.tableMarginLeft;
    else if (I > F) this.bodyDiv.scrollLeft = G + I - F;
    if (H < D) this.bodyDiv.scrollTop = H;
    else if (E > C) this.bodyDiv.scrollTop = D + E - C
};
Datacell.prototype.setActiveCell = function(D, A) {
    if (arguments.length == 1) if (D != null && D.tagName.toLowerCase() == "td") {
        A = D;
        D = null
    }
    this.activeMe();
    D = D || window.event;
    var B = D ? (D.srcElement || D.target) : null;
    A = A || getEventTargetByTagName(D, "td");
    if (A) {
        var C = A.innerHTML.toLowerCase();
        if (A != this.activeCell) {
            this.activeField = this.getField(A);
            this.activeEditor = this.activeField ? this.activeField.editor: null;
            removeClass(this.activeCell, "eos-cell-actived");
            if (C.indexOf("<select") < 0) addClass(A, "eos-cell-actived");
            this.activeCell = A
        }
        this.activeEditRow = this.activeCell.parentNode;
        this.activeEntity = this.getEntity(this.activeEditRow);
        this.activeEntityBackup = this.activeEntity.clone();
        if (this.activeEntity && this.activeField) this.activeValue = this.activeEntity.getProperty(this.activeField.fieldName);
        if (C.indexOf("<select") < 0) this.autoScroll()
    }
    return A
};
Datacell.prototype.initEvent = function() {
    var A = this;
    function D(B) {
        A.activeMe();
        A.syncScroll();
        if (A.beforeShowEditor !== true) A.endEdit()
    }
    eventManager.add(this.bodyDiv, "scroll", D);
    function C(D) {
        A.activeMe();
        A.endEdit();
        var C = getEventTargetByTagName(D, "td"),
        B = C ? C.parentNode: null;
        if (C && C !== A.containerTD) {
            A.selectRow(B);
            A.setActiveCell(D, C);
            A.processCheckBox(D, C);
            var E = C.cellIndex,
            F = B.rowIndex;
            A.onClickCell(C, E, F, A.activeEntity, A.fields[E], A);
            A.onClickRow(B, F, A.activeEntity, A);
            A.beforeShowEditor = true;
            A.startEdit()
        }
    }
    eventManager.add(this.bodyDiv, "click", C);
    function G(B) {
        A.activeMe();
        A.endEdit()
    }
    eventManager.add(this.headDiv, "click", G);
    function F() {
        A.activeMe()
    }
    try {
        eventManager.add(this.viewport, "mouseup", F);
        eventManager.add(this.headDiv, "mouseup", F);
        eventManager.add(this.bodyDiv, "mouseup", F);
        eventManager.add(this.datacellContainer, "mouseup", F);
        eventManager.add(this.containerTable, "mouseup", F);
        eventManager.add(this.container, "mouseup", F);
        eventManager.add(this.topToolbarDiv, "mouseup", F);
        eventManager.add(this.bottomToolbarDiv, "mouseup", F);
        eventManager.add(this.toolbarParent, "mouseup", F)
    } catch(I) {}
    function H(D) {
        A.activeMe();
        D = D || window.event;
        var C = D.srcElement || D.target;
        eventManager.stopPropagation();
        if (C && C.tagName.toLowerCase() != "td") while ((C = C.parentNode)) if (C.tagName && C.tagName.toLowerCase() == "td") break;
        if (!C.parentNode) return;
        var F = C.parentNode.rowIndex,
        B = C.cellIndex - 1;
        if (B < 0) {
            A.selectRow(A.table.tBodies[0].rows[F], true);
            return
        }
        var E = A.table.tBodies[0].rows[F].cells[B];
        if (A.activeCell == E) {
            A.activeFreezeCell = C;
            return
        }
        A.activeFreezeCell = C
    }
    eventManager.add(this.freezeBodyDiv, "click", H);
    function B() {
        Datacell.onWindowResize(A)
    }
    if (isIE) if (("" + A.width).indexOf("%") > 0 || ("" + A.height).indexOf("%") > 0) eventManager.add(window, "resize", B);
    if (isFF) if (("" + A.width).indexOf("%") > 0 || ("" + A.height).indexOf("%") > 0) if (registerTopContainer(this.datacellContainer)) EOSResizeObjects.push(this);
    function E() {
        A.clearup()
    }
    eventManager.add(window, "unload", E);
    if (!Datacell.initGlobalEvent.hasInit) {
        Datacell.initGlobalEvent();
        Datacell.initGlobalEvent.hasInit = true
    }
};
Datacell.prototype.processCheckBox = function(D, B) {
    var A = this,
    F = D.srcElement || D.target;
    if ("INPUT" == F.tagName.toUpperCase()) if (F.getAttribute("swidthCheckbox") == "true" && F.getAttribute("type") == "checkbox") {
        var C = A.getCellValue(B),
        E = A.fields[B.cellIndex];
        C = C == E.checkedValue ? E.unCheckedValue: E.checkedValue;
        A.setCellValue(B, C)
    }
};
Datacell.prototype.startColumnResize = function(B, C) {
    if (C.allowResize === false) return;
    B = B || window.event;
    this.viewportXY = getElementXY(this.viewport);
    this.isColumnResizing = true;
    var A = eventManager.getPointX();
    C.oldRightX = A - this.viewportXY[0];
    this.splitLine.style.left = C.oldRightX + "px";
    this.splitLine.style.height = this.viewport.offsetHeight + "px";
    this.splitLine.style.display = "block";
    this.headDiv.style.cursor = "col-resize";
    this.gridMask.style.cursor = "col-resize";
    this.gridMask.style.display = "block";
    this.resizeColumn = C
};
Datacell.prototype.syncScroll = function() {
    this.headDiv.scrollLeft = this.bodyDiv.scrollLeft;
    this.freezeBodyDiv.scrollTop = this.bodyDiv.scrollTop
};
Datacell.prototype.setColumnWidth = function(B, A) {
    B = this.getField(B);
    A = A < this.minColumnWidth ? this.minColumnWidth: A;
    B.width = A;
    B.rules.style.width = B.width + "px"
};
Datacell.prototype.setFieldEditor = function(C, A) {
    C = this.getField(C);
    var E = null;
    A = A || C.editId;
    if (A) E = Datacell.$editor($id(A));
    if (!E) {
        var B = $id(this.id + "_" + C.fieldId + "_field_editor_container") || $id(C.fieldId + "_field_editor_container"),
        D = firstElement(B);
        if (B && D) {
            A = D.id;
            if (A && A.indexOf("_container") > 0) {
                A = A.substring(0, A.length - 10);
                E = Datacell.$editor($o(A) || $id(A))
            } else E = Datacell.$editor(D)
        }
    }
    C.editId = A;
    C.editor = E;
    if (E) E.hideEditor()
};
Datacell.prototype.initCSS = function() {
    this.viewportXY = getElementXY(this.viewport);
    var C = [],
    A = this;
    for (var B = 0; B < this.fields.length; B++) {
        var D = this.fields[B];
        D.width = parseInt(D.width || this.defaultColumnWidth);
        D.styleClass = "eos-dc-col-" + this.id + "-" + B;
        D.rules = createStyle(D.styleClass, document);
        D.align = D.align || null;
        D.alignStyle = D.align ? "text-align:" + D.align + ";": "";
        C[B] = "." + D.styleClass + " { width:" + D.width + "px;  " + D.alignStyle + " }";
        D.rules.style.width = D.width + "px";
        if (D.align) D.rules.style.textAlign = D.align;
        D.allowModify = D.allowModify === undefined || D.allowModify === null ? !A.readonly: !!D.allowModify;
        this.setFieldEditor(D)
    }
    if (1 == 2 && !this.addedCSS) {
        CSSUtil.createStyleSheet(C.join("\n"), this.id + "_style");
        this.SCROLLBAR_WIDTH = 16;
        this.addedCSS = true
    }
};
Datacell.prototype.initAllRows = function(C) {
    if (!C && this.allTR && this.allTR.length > 0) return this.allTR;
    this.allTR = [];
    var B = this.table.tBodies[0].rows;
    for (var A = 0; A < B.length; A++) this.allTR[A] = B[A];
    return this.allTR
};
Datacell.prototype.refreshRowStyle = function() {
    this.initAllRows(true);
    var C = this.freezeHeadTable ? this.freezeBodyTable.tBodies[0].rows: [];
    for (var A = 0; A < this.allTR.length; A++) {
        var D = this.allTR[A];
        D.className = D.className.replace(this.rowEvenStyleClass, "");
        if (A % 2 == 0) D.className = D.className + " " + this.rowEvenStyleClass;
        else D.className = D.className.replace(this.rowEvenStyleClass, "");
        var B = C[A] || {};
        B.className = D.className
    }
};
Datacell.prototype.getCurrentRowCount = function() {
    var A = this.table.tBodies[0].rows;
    return A ? A.length: 0
};
Datacell.prototype.getPageRowCount = function() {
    var A = 0;
    if (this.datasetExp) {
        A = this.getCurrentRowCount();
        A = A - (this.datasetExp.getInsertEntities() ? this.datasetExp.getInsertEntities().length: 0);
        A = A + (this.datasetExp.getRemovedEntities() ? this.datasetExp.getRemovedEntities().length: 0)
    }
    return A
};
Datacell.prototype.getTotalRowCount = function() {
    if (this.__pagePilot && this.__pagePilot[0]) return this.__pagePilot[0].count || 0;
    return - 1
};
Datacell.prototype.getAllRows = function(A) {
    this.initAllRows(A);
    return this.allTR
};
Datacell.prototype.freezeColumn = function(C) {
    var H = this.freezeHeadTable.tBodies[0].rows;
    for (var B = 0; B < C; B++) {
        var E = this.fields[B];
        E.frozeHeadCell = this.headTable.tBodies[0].rows[0].cells[B].cloneNode(true);
        E.frozeSortIcon = E.frozeHeadCell.getElementsByTagName("span")[1];
        E.frozeSplitter = E.frozeSortIcon.nextSibling;
        if (H.length > 0) H[0].appendChild(E.frozeHeadCell)
    }
    this.initAllRows();
    H = this.freezeBodyTable.tBodies[0].rows;
    var D = this.allTR.length;
    for (var A = H.length; A < D - H.length; A++) {
        var I = this.styleFreezeRow.cloneNode(true);
        I.className = this.allTR[A].className;
        I.appendChild(this.styleIndexColumn.cloneNode(true));
        this.freezeBodyTable.tBodies[0].appendChild(I)
    }
    for (var F = 0; F < D; F++) for (B = 0; B < C; B++) {
        var G = this.allTR[F].cells[B].cloneNode(true);
        H[F].appendChild(G)
    }
};
Datacell.prototype.quickSort = function(D, F, E, H, M) {
    var A = this,
    B = D,
    L = F + 1,
    J = L,
    G = A.allTR,
    K = G[D],
    I = E(this, K, H),
    C;
    if (M) {
        while (true) {
            while (++B < F && E(this, G[B], H) >= I);
            while (--L > D && E(this, G[L], H) <= I);
            if (B >= L) break;
            C = G[B];
            G[B] = G[L];
            G[L] = C
        }
    } else while (true) {
        while (++B < F && E(this, G[B], H) <= I);
        while (--L > D && E(this, G[L], H) >= I);
        if (B >= L) break;
        C = G[B];
        G[B] = G[L];
        G[L] = C
    }
    G[D] = G[L];
    G[L] = K;
    L -= 1;
    if (D < L) A.quickSort(D, L, E, H, M);
    L += 2;
    if (L < F) A.quickSort(L, F, E, H, M);
    return G
};
Datacell.prototype.doSort = function(I) {
    if (!I.sortAt || I.sortAt == "null" || I.sortAt == "none" || I.sortAt == "undefined") return;
    this.initAllRows();
    if (this.allTR.length < 1) return;
    if (this.lastSortField) {
        removeClass(this.lastSortField.sortIcon, "eos-hd-asc");
        removeClass(this.lastSortField.frozeSortIcon, "eos-hd-asc");
        removeClass(this.lastSortField.sortIcon, "eos-hd-desc");
        removeClass(this.lastSortField.frozeSortIcon, "eos-hd-desc")
    }
    if (I.sorted == "asc") {
        I.sorted = "desc";
        addClass(I.sortIcon, "eos-hd-desc");
        addClass(I.frozeSortIcon, "eos-hd-desc")
    } else if (I.sorted == "desc") I.sorted = "";
    else {
        I.sorted = "asc";
        addClass(I.sortIcon, "eos-hd-asc");
        addClass(I.frozeSortIcon, "eos-hd-asc")
    }
    this.lastSortField = I;
    if (I.sortAt) {
        I.sortAt = ("" + I.sortAt).toLowerCase();
        if (I.sortAt == "server" || I.sortAt.indexOf("s") == 0) {
            var A = ["<_orderby>", "<_property>", I.fieldName, "</_property>", "<_sort>", I.sorted, "</_sort>", "</_orderby>"];
            this.sortParam = A.join("\n");
            try {
                this.endEdit()
            } catch(L) {}
            var H = 0;
            if (!this.isCount) H = -1;
            var M = this.setPageParam(0, this.pageSize, H, this.isCount);
            this.gotoPage(M);
            return
        }
    }
    if (I.sorted) this.quickSort(0, this.allTR.length - 1, Datacell.getSortValue, I.fieldName, I.sorted == "desc");
    else this.quickSort(0, this.allTR.length - 1, Datacell.getSortDefaultValue, I.fieldName, I.sorted == "desc");
    var K = this.freezeBodyTable.tBodies[0].rows,
    G = this.showIndex ? 1 : 0,
    E = document.createDocumentFragment();
    for (var J = 0; J < this.allTR.length; J++) {
        var C = this.allTR[J];
        C.className = C.className.replace(this.rowEvenStyleClass, "");
        if (J % 2 == 0) C.className = C.className + " " + this.rowEvenStyleClass;
        else C.className = C.className.replace(this.rowEvenStyleClass, "");
        for (var D = 0; D < this.freezeNumber; D++) {
            var B = K[J].cells[D + G],
            F = C.cells[D].cloneNode(true);
            K[J].className = C.className;
            K[J].replaceChild(F, B)
        }
        E.appendChild(C)
    }
    this.table.tBodies[0].appendChild(E)
};
Datacell.prototype.initHeadEvent = function() {
    if (this.hasInitHeadEvent) return;
    var A = this,
    C = A.fields.length;
    for (var B = 0; B < C; B++) {
        var D = A.fields[B]; (function(C) {
            eventManager.add(C.splitter, "mousedown",
            function(B) {
                A.startColumnResize(B, C)
            });
            eventManager.add(C.frozeSplitter, "mousedown",
            function(B) {
                A.startColumnResize(B, C)
            });
            eventManager.add(C.headCell, "mouseover",
            function(A) {
                addClass(C.headCell, "eos-hd-row-over")
            });
            eventManager.add(C.frozeHeadCell, "mouseover",
            function(A) {
                addClass(C.frozeHeadCell, "eos-hd-row-over")
            });
            eventManager.add(C.headCell, "mouseout",
            function(A) {
                removeClass(C.headCell, "eos-hd-row-over")
            });
            eventManager.add(C.frozeHeadCell, "mouseout",
            function(A) {
                removeClass(C.frozeHeadCell, "eos-hd-row-over")
            });
            eventManager.add(C.headCell, "click",
            function(D) {
                A.onClickHead(C.headCell, B, C, A);
                A.doSort(C)
            });
            eventManager.add(C.frozeHeadCell, "click",
            function(B) {
                A.doSort(C)
            })
        })(D)
    }
    this.hasInitHeadEvent = true
};
Datacell.prototype.initHead = function() {
    this.headTable = $createElement("table", {
        className: "eos-head-table",
        cellSpacing: "0",
        cellPadding: "0",
        border: "0"
    });
    this.headWrap.appendChild(this.headTable);
    this.headTbody = $createElement("tbody");
    this.headTable.appendChild(this.headTbody);
    var J = this.fields.length,
    F = [];
    for (var G = 0; G < J; G++) {
        this.keys[this.fields[G].fieldId] = this.fields[G];
        F.push(this.fields[G].fieldId)
    }
    var A = $createElement("tr");
    A.className = "eos-hd-row";
    this.headTbody.appendChild(A);
    this.styleRow = $createElement("tr", {
        className: this.rowStyleClass
    });
    this.styleFreezeRow = $createElement("tr", {
        className: this.rowStyleClass
    });
    var L = this.getSubmitXpath(),
    B = false;
    if (!this.styleEntity) {
        this.styleEntity = this.styleEntity || new Entity(L);
        this.styleEntity.id = 0;
        this.styleEntity.__type = this.entityType;
        B = true
    }
    for (var I = 0; I < J; I++) {
        var E = $createElement("td"),
        K = this.fields[I];
        if (B) this.initStyleField(K);
        E.className = K.styleClass;
		if(K.width<1){
			E.style.display="none";
		}
        E.style.textAlign = this.headTextAlign;
        E.innerHTML = "<div class=\"eos-hd-inner " + K.styleClass + "\" unselectable=\"on\" style=\"text-align:left\" >" + "<span>" + K.label + "</span>" + "<div class=\"eos-hd-tool\" ><span class=\"eos-hd-icon\"></span><span class=\"eos-hd-split\">&#160;</span></div>" + "</div>";
        A.appendChild(E);
        K.headCell = E;
        K.labelSpan = E.firstChild.firstChild;
        K.sortIcon = E.getElementsByTagName("span")[1];
        K.splitter = K.sortIcon.nextSibling;
        K.splitter.colID = I;
        K.splitter.style.cursor = "col-resize";
        K.defaultValue = K.defaultValue || "";
        K.sortAt = K.sortAt || "client";
        K.nullText = K.nullText || "";
        K.template = K.expression ? compileTemplate(K.expression) : null;
        var C = $createElement("td", {
            innerHTML: "<div class=\"eos-inner " + K.styleClass + "\"  ></div>",
            className: K.styleClass
        });
        C.setAttribute("name", K.fieldId);
        this.styleRow.appendChild(C)
    }
    var H = false;
    if (!this.datasetExp) this.datasetExp = new Dataset(this.entityName);
    var D = 0;
    return
};
Datacell.prototype.initStyleField = function(B) {
    if (B.editType == "entity") {
        var A = new Entity(B.fieldName);
        this.styleEntity.setProperty(B.fieldName, A)
    } else if (B.editType == "dataset") {
        A = new Dataset(B.fieldName);
        this.styleEntity.setProperty(B.fieldName, A)
    } else if (B.defaultValue !== null && B.defaultValue !== undefined) this.styleEntity.setProperty(B.fieldName, B.defaultValue);
    else this.styleEntity.setProperty(B.fieldName, "")
};
Datacell.prototype.initFreezeZone = function() {
    this.freezeHeadDiv = $createElement("div", {
        className: "eos-freeze-div"
    });
    this.freezeBodyDiv = $createElement("div", {
        className: "eos-freeze-div"
    });
    this.viewport.appendChild(this.freezeHeadDiv);
    this.viewport.appendChild(this.freezeBodyDiv);
    this.cTop = 0;
    this.freezeHeadDiv.style.top = this.cTop + "px";
    this.freezeBodyDiv.style.top = this.cTop + this.headHeight + "px";
    this.freezeHeadTable = $createElement("table", {
        id: this.headTable.id + "_freeze",
        className: "eos-head-table",
        cellSpacing: "0",
        cellPadding: "0",
        border: "0"
    });
    this.freezeHeadDiv.appendChild(this.freezeHeadTable)
};
Datacell.prototype.initBody = function() {
    var tableStartHTML = "<table class=\"eos-table\" cellSpacing=\"0\"  cellPadding=\"0\" border=\"0\" ><tbody>",
    trHTML = ["<tr class=\"" + this.rowStyleClass, " " + this.rowEvenStyleClass, "\" __entity_rowno=\"", "\" >\n", "</tr>\n"],
    colNum = this.fields.length,
    rn = 0,
    tableHTML = [],
    rowHTML,
    freezeTableHTML = [tableStartHTML],
    indexColumnWidth = this.tableMarginLeft - (isBorderBox ? 0 : 2),
    indexColumnDivWidth = this.tableMarginLeft - (isBorderBox ? 0 : 4),
    tdStyleClass = isIE ? "style=\"width:" + this.tableMarginLeft + "px;\"": "",
    divStyleClass = isIE ? "": "style=\"width:" + (this.tableMarginLeft - (isBorderBox ? 0 : 4)) + "px;\"",
    indexColumnCell = ["<td class=\"eos-index-col\" " + tdStyleClass + " ><div class=\"eos-inner\" style=\"width:" + indexColumnDivWidth + "px\" >", "</div></td>"];
    this.styleIndexColumn = $createElement("td", {
        className: "eos-index-col"
    });
    if (isIE) this.styleIndexColumn.style.width = this.tableMarginLeft + "px";
    this.styleIndexColumn.innerHTML = "<div class=\"eos-inner\" " + divStyleClass + " >&#160;</div>";
    if (this.freezeBodyTable) {
        removeElement(this.freezeBodyTable);
        removeElement(this.freezeHeadTable.tBodies[0])
    }
    this.freezeHeadTable.appendChild($createElement("tbody"));
    if (this.showIndex) {
        var headRow = $createElement("tr", {
            className: "eos-hd-row"
        });
        this.freezeHeadTable.tBodies[0].appendChild(headRow);
        var colN = $createElement("td", {
            innerHTML: "<div class=\"eos-hd-inner\" style=\"width:" + (indexColumnDivWidth - 1) + "px\" >&#160;</div>",
            className: "eos-index-col"
        });
        if (isIE) colN.style.width = indexColumnWidth + "px";
        headRow.appendChild(colN);
        this.freezeHeadDiv.style.display = "block";
        this.freezeHeadDiv.style.left = "0px"
    }
    var datasetLength = !this.datasetExp ? 0 : this.datasetExp.getLength(),
    rowNum = this.pageSize || datasetLength;
    rowNum = (rowNum < 0 || datasetLength < rowNum) ? datasetLength: rowNum;
    var colName = [];
    for (var i = 0; i < colNum; i++) colName.push(this.fields[i].fieldId);
    tableHTML.push(tableStartHTML);
    for (rn = 0; rn < rowNum;) {
        var entity = this.datasetExp.get(rn);
        rowHTML = [trHTML[0] + (rn % 2 == 0 ? trHTML[1] : "") + trHTML[2] + rn + trHTML[3]];
        if (this.showIndex) {
            freezeTableHTML.push(rowHTML[0]);
            freezeTableHTML.push(indexColumnCell[0]);
            freezeTableHTML.push("&#160;" + indexColumnCell[1]);
            freezeTableHTML.push("</tr>\n")
        }
        for (var cn = 0; cn < colNum; cn++) {
            var field = this.fields[cn],
            cellText = entity.getProperty(field.fieldName);
            if(field.width<1){
				continue;            
            }
            if (field.onRefreshFunc) {
                if (typeof(field.onRefreshFunc) == "string") field.onRefreshFunc = eval(field.onRefreshFunc);
                cellText = field.onRefreshFunc(cellText, entity, rn, cn, this)
            } else if (field.expression) cellText = runExpression(field.template, entity);
            else if (field.editor) cellText = htmlConversion(field.editor.getDisplayValue(cellText));
            else cellText = htmlConversion(cellText === undefined || cellText === null ? field.nullText: cellText);
            cellText = cellText === undefined || cellText === null ? field.nullText: cellText;
            rowHTML.push("<td fieldId=\"" + colName[cn] + "\" class=\"" + field.styleClass + "\"><div class=\"eos-inner " + field.styleClass + "\" >" + cellText + "</div></td>")
        }
        rowHTML.push(trHTML[4]);
        tableHTML.push(rowHTML.join(""));
        rn++
    }
    for (var bi = 0; bi < this.pageSize - datasetLength; bi++) for (cn = 0; cn < colNum; cn++);
    tableHTML.push("</tbody></table>");
    this.bodyDiv.innerHTML = tableHTML.join("");
    this.table = this.bodyDiv.firstChild;
    this.table.datacell = this;
    this.tbody = this.table.tBodies[0];
    this.freezeBodyDiv.innerHTML = freezeTableHTML.join("");
    this.freezeBodyTable = this.freezeBodyDiv.firstChild;
    this.isModefied = false;
    if (this.showIndex) {
        this.headTable.style.marginLeft = this.tableMarginLeft + 1 + "px";
        this.table.style.marginLeft = this.tableMarginLeft + 1 + "px"
    } else {
        this.headTable.style.marginLeft = 0 + "px";
        this.table.style.marginLeft = 0 + "px"
    }
    this.initAllRows(true);
    if (this.freezeNumber > 0) this.freezeColumn(this.freezeNumber);
    this.initHeadEvent();
    var datacell = this;
    function tt() {
        datacell.freezeBodyDiv.style.height = datacell.bodyDivHeight - 0 + "px";
        if (datacell.headDiv.clientHeight > 10) datacell.freezeBodyDiv.style.height = datacell.bodyDiv.clientHeight + "px"
    }
    tt();
    return
};
Datacell.prototype.initStyleRow = function() {
    if (!this.rowStyles instanceof Array) this.rowStyles = this.rowStyles.split(",")
};
Datacell.prototype.locked = function() {
    this.endEdit();
    var A = $id(Datacell.BLOLK_ID),
    C = $id(Datacell.BLOLK_ID + "_IMG");
    if (!A) {
        A = $createElement("div");
        document.body.appendChild(A);
        A.id = Datacell.BLOLK_ID
    }
    if (!C && PICTURE_BLANK) {
        C = $createElement("img");
        C.src = PICTURE_BLANK;
        C.id = Datacell.BLOLK_ID + "_IMG";
        A.appendChild(C)
    }
    A.style.cursor = "wait";
    A.style.position = "absolute";
    A.style.zIndex = "1";
    var B = getPosition(this.container);
    A.style.top = B.top;
    A.style.left = B.left;
    A.style.width = this.container.offsetWidth;
    A.style.height = this.container.offsetHeight;
    if (C) {
        C.style.width = A.style.width;
        C.style.height = A.style.height;
        C.border = "0"
    }
    A.style.display = "block"
};
Datacell.prototype.unlocked = function() {
    var A = $id(Datacell.BLOLK_ID);
    if (A) A.style.display = "none"
};
Datacell.prototype.insertEmptyRow = function() {
    return this.insertRow(this.styleEntity.clone(true))
};
Datacell.prototype.insertRow = function(E, D) {
    if (typeof D == "number") D = this.getRowAt(D);
    E = E || this.cloneEntity() || this.styleEntity.clone(true);
    if (D && E) {
        E.status = Entity.STATUS_NEW;
        var C = D.rowIndex;
        removeClass(this.activeCell, "eos-cell-actived");
        var A = D.cloneNode(true);
        addClass(this.activeCell, "eos-cell-actived");
        this.datasetExp.addEntity(E);
        this.tbody.insertBefore(A, D);
        A.setAttribute("__entity_rowno", this.datasetExp.getLength() - 1);
        var B = this.freezeBodyTable.tBodies[0].rows[C],
        G = this.styleFreezeRow.cloneNode(true);
        G.className = A.className;
        G.appendChild(this.styleIndexColumn.cloneNode(true));
        this.freezeBodyTable.tBodies[0].insertBefore(G, B);
        this.refreshRow(A);
        this.selectRow(A);
        this.isModefied = true;
        if (this.linkId && this.linkField) {
            var F = PageControl.getOne(this.linkId);
            if (F) F.isModefied = true
        }
        return A
    }
    return this.addRow(E)
};
Datacell.prototype.addRow = function(F) {
    this.endEdit();
    if (!this.allowAdd || this.readonly) return null;
    var A = this.tbody.rows.length;
    if (A < 0) A = 0;
    var H = F || this.styleEntity.clone(true);
    H.status = Entity.STATUS_NEW;
    if (!this.datasetExp) return null;
    var E = this.styleRow.cloneNode(true);
    E.setAttribute("__entity_rowno", this.datasetExp.getLength());
    if (this.beforeAdd(E) === false) {
        E = null;
        delete E;
        return null
    }
    this.datasetExp.addEntity(H);
    this.tbody.appendChild(E);
    for (var B = 0; B < E.cells.length; B++) E.cells[B].setAttribute("fieldId", this.fields[B].fieldId);
    A = (this.tbody.rows.length + 1) % this.rowStyles.length;
    addClass(E, (this.tbody.rows.length + 1) % 2 == 0 ? this.rowEvenStyleClass: "");
    var J = this.styleFreezeRow.cloneNode(true);
    J.className = E.className;
    J.appendChild(this.styleIndexColumn.cloneNode(true));
    this.freezeBodyTable.tBodies[0].appendChild(J);
    for (B = 0; B < this.freezeNumber; B++) {
        var D = E.cells[B].cloneNode(true);
        D.setAttribute("fieldId", this.fields[B].fieldId);
        J.appendChild(D)
    }
    this.refreshRow(E);
    this.isModefied = true;
    this.initAllRows(true);
    this.bodyDiv.scrollTop = this.bodyDiv.scrollHeight;
    this.freezeBodyDiv.scrollTop = this.bodyDiv.scrollTop;
    this.selectRow(E);
    for (var C = 0; C < this.fields.length; C++) {
        var G = this.fields[C];
        if (G.editor) {
            this.setActiveCell(null, E.cells[C]);
            this.startEdit();
            break
        }
    }
    if (this.linkId && this.linkField) {
        var I = PageControl.getOne(this.linkId);
        if (I) I.isModefied = true
    }
    this.refreshRowStyle();
    this.afterAdd(E);
    return E
};
Datacell.prototype.deleteRow = function(A) {
    if (!this.allowDel || this.readonly) return;
    A = A === 0 ? 0 : (A || this.activeRow);
    if (A == null) return;
    A = this.getRow(A);
    if (this.beforeDel(A) !== false && A) {
        this.selectNextRow();
        if (this.activeCell) this.endEdit();
        if (this.getEntity(A).status == Entity.STATUS_NEW) this.getEntity(A).status = Entity.STATUS_HIDDEN;
        else {
            var C = false;
            if (this.linkId && this.linkField) {
                var D = PageControl.getOne(this.linkId);
                if (D) {
                    D.isModefied = true;
                    C = true
                }
            }
            this.datasetExp.removeEntity(this.getEntity(A), C)
        }
        var B = this.freezeBodyTable.tBodies[0].rows[A.rowIndex];
        if (A && A.parentNode) A.parentNode.removeChild(A);
        if (B && B.parentNode) B.parentNode.removeChild(B);
        this.isModefied = true;
        this.initAllRows(true);
        this.refreshRowStyle();
        this.afterDel(A)
    }
};
Datacell.prototype.updateEntity = function(B, C, A) {
    B.setProperty(C, A)
};
Datacell.prototype.getEntity = function(B) {
    var A = B;
    if (typeof(A) == "number") A = this.table.tBodies[0].rows[A];
    if (!A) return null;
    if (A.tagName.toLowerCase() == "td") A = A.parentNode;
    var D = A.getAttribute("__entity_rowno") / 1,
    C = this.datasetExp.get(D);
    return C
};
Datacell.prototype.selectRow = function(B, C) {
    this.endEdit();
    B = this.getRow(B);
    if (this.beforeSelectRow(B) === false) return false;
    var D = true;
    if (this.activeRow != null) if (this.activeRow == B) D = false;
    else if (!this.unSelectRow()) {
        this.afterSelectRow(B);
        return false
    }
    this.activeRow = this.activeRow || B;
    addClass(B, this.rowSelectStyleClass);
    if (D || C) this.setSubComponent(this.getEntity(B));
    var E = this.getEntity(B);
    this.onSelectRow(B, E);
    var A = this;
    if (E) E.__onUpdate = function() {
        if (A.getEntity(B).status != Entity.STATUS_NEW) A.getEntity(B).status = Entity.STATUS_MODIFIED;
        if (A.syncRefresh) A.refreshRow(B);
        else A.refreshCell(A.activeCell, A.activeField);
        A.isModefied = true
    };
    this.afterSelectRow(this.activeRow);
    return true
};
Datacell.prototype.setSubComponent = function(C) {
    if (this.submitXpath && C) C.name = this.getSubmitXpath();
    var A = this;
    setTimeout(B, 100);
    function B() {
        for (var B = 0; B < A.__subComponent.length; B++) {
            var D = A.__subComponent[B];
            try {
                D.freshFromEntity(C)
            } catch(E) {}
        }
    }
};
Datacell.prototype.freshFromEntity = function(A) {
    if (this.queryAction) {
        if (A) this.queryParam = A.toString();
        else this.queryParam = null;
        this.loadData()
    } else if (this.linkField) if (A) {
        this.datasetExp = A.getProperty(this.linkField);
        if (!this.datasetExp) {
            this.datasetExp = new Dataset(this.linkField);
            A.setProperty(this.linkField, this.datasetExp)
        }
    } else this.datasetExp = new Dataset(this.linkField);
    this.refresh()
};
Datacell.prototype.registerSubComponent = function(B) {
    var A = PageControl.getOne(B);
    this.__subComponent.push(A)
};
Datacell.prototype.initCell = function(B, C) {
    var A = B.getAttribute("fieldId");
    if (A == Datacell.__INDEX_NAME) return;
    var D = this.keys[A],
    E = D.fieldName;
    this.refreshCell(B, D)
};
Datacell.prototype.refreshRow = function(D, F) {
    var I = D.cells.length;
    F = F || this.getEntity(D);
    for (var E = 0; E < I; E++) {
        cell = D.cells[E];
        var A = cell.getAttribute("fieldId");
        if (A == Datacell.__INDEX_NAME) continue;
        var G = this.keys[A],
        H = G.fieldName,
        B = F.getProperty(H) ? F.getProperty(H) : "";
        if (F == this.activeEntity && B != this.activeEntityBackup.getProperty(H)) {
            try {
                var C = cell.parentNode.rowIndex;
                if (this.showIndex) addClass(this.freezeBodyTable.tBodies[0].rows[C].cells[0], "eos-row-modefied");
                else addClass(cell.parentNode, "eos-row-modefied")
            } catch(J) {
                addClass(cell.parentNode, "eos-row-modefied")
            }
        }
        this.refreshCell(cell, G)
    }
};
Datacell.prototype.refresh = function() {
    this.init();
    if (this.beforeRefresh() === false) return;
    this.activeEditor = null;
    this.activeCell = null;
    this.activeRow = null;
    this.activeField = null;
    this.activeValue = null;
    this.initBody();
    this.status = Datacell.STATUS_BLANK;
    this.onComplete();
    this.selectFirstRow();
    this.afterRefresh()
};
Datacell.prototype.reSortBody = function(B) {
    var C = this.tbody.cloneNode(false);
    for (var A = 0; A < B.length; A++) C.appendChild(B[A]);
    this.table.replaceChild(C, this.tbody);
    this.tbody = C
};
Datacell.prototype.serverSort = function(B) {
    var A = B.field;
    B.sortType = B.sortType == "_desc" ? "_asc": "_desc";
    this.sortParam = "<_orderby><_property>" + A.fieldName + "</_property><_sort>" + B.sortType + "</_sort></_orderby>";
    this.reload()
};
Datacell.prototype.refreshSortTh = function(A) {
    if (!this.sortArrow) this.sortArrow = $createElement("img");
    bodyAddNode(this.sortArrow);
    A.container.appendChild(this.sortArrow);
    if (A.sortType == "_desc") this.sortArrow.src = PICTURE_SORT_DOWN;
    else if (A.sortType == "_asc") this.sortArrow.src = PICTURE_SORT_UP
};
Datacell.prototype.setPageParam = function(D, C, B, A) {
    this.pageParam = "<page><begin>" + D + "</begin><length>" + C + "</length><count>" + B + "</count><isCount>" + ( !! A) + "</isCount></page>";
    return this.pageParam
};
Datacell.prototype.initPageParam = function() {
    if (this.pageSizeList && this.pageSizeList != "null" && this.pageSizeList != "none" && ("," + this.pageSizeList + ",").indexOf("," + this.pageSize + ",") < 0) this.pageSizeList += "," + this.pageSize;
    var A = 0;
    if (!this.isCount) A = -1;
    this.setPageParam(0, this.pageSize, A, this.isCount)
};
Datacell.prototype.initAttachParam = function() {};
Datacell.prototype.getQueryForm = function() {
    var A = "<criteria><_entity>" + (this.entityName || "") + "</_entity>";
    if (this.sortParam) A += this.sortParam;
    A += "</criteria>";
    return A
};
Datacell.prototype.setJsonDataset = function(json) {
    if (typeof(json) == "string") json = eval(json);
    if (! (json instanceof Dataset)) json = Json2Dataset(json);
    this.datasetExp = json;
    this.dataXML = "<json/>"
};
Datacell.prototype.loadJsonData = function() {};
Datacell.prototype.addParam = function(A, B) {
    this.paramList = this.paramList || [];
    this.paramList.push({
        key: A,
        value: B
    })
};
Datacell.prototype.loadData = function() {
    if (this.beforeLoadData() === false) return;
    if (this.dataXML == "<json/>") return;
    if (!this.linkId && !this.queryAction && this.xpath) {
        var xmlZone = document.getElementById(this.id + "_xml");
        this.dataXML = xmlZone ? xmlZone.innerHTML: null
    } else if (this.linkId && this.linkId.indexOf("xml:") == 0) {
        xmlZone = document.getElementById(this.linkId.substring(4));
        this.dataXML = xmlZone ? xmlZone.innerHTML: null
    } else if (this.linkId && this.isFirstLoad) {
        this.isFirstLoad = false;
        return
    }
    var xmlDom;
    if (this.dataXML && this.dataXML !== "<json/>") {
        this.datasetExp = Dataset.create(this.dataXML, this.xpath);
        xmlDom = createXmlDom();
        xmlDom.loadXML(this.dataXML);
        var pagecond = xmlDom.selectSingleNode("/root/data/page");
        this.freshPagePilot(pagecond);
        return
    }
    if (this.onLoadData() !== false) {
        if (!this.queryAction) return;
        var ajax = new HideSubmit(this.queryAction),
        param = this.getQueryForm();
        if (this.queryParam) param += this.queryParam;
        if (this.pageParam) param += this.pageParam;
        if (this.initParamFunc) {
            try {
                param += eval(this.initParamFunc + "()")
            } catch(e) {
                $handle(e)
            }
        }
        if (param == "") param = null;
        if (this.paramFormId) {
            var form = $id(this.paramFormId);
            if (form) for (var i = 0; i < form.elements.length; i++) {
                var elem = form.elements[i];
                if (elem.name) ajax.addParam(elem.name, getElementValue(elem))
            }
        }
        if (this.paramList) for (i = 0; i < this.paramList.length; i++) {
            elem = this.paramList[i];
            if (elem) ajax.addParam(elem.key, elem.value)
        }
        ajax.loadData(param);
        xmlDom = ajax.retDom;
        this.datasetExp = Dataset.create(xmlDom, this.xpath, this.getSubmitXpath());
        pagecond = xmlDom.selectSingleNode("/root/data/page");
        this.freshPagePilot(pagecond);
        this.afterLoadData(ajax)
    }
};
Datacell.prototype.freshPagePilot = function(G) {
    var D = Dataset.initEntity(G);
    if (!G) {
        var F = 1,
        B = this.datasetExp ? this.datasetExp.getLength() : 0;
        if (B < F) {
            F = 0;
            B = F
        }
        for (var A = 0; A < this.__pagePilot.length; A++) {
            var E = this.__pagePilot[A];
            if (E.gotoPageText) E.gotoPageText.value = 1;
            if (E.pageInfoDiv) {
                E.pageInfoDiv.innerHTML = "<nobr>";
                E.pageInfoDiv.innerHTML += "<nobr>" + this.PAGE_RECORDINFO_NOCOUNT.replace("{0}", F).replace("{1}", this.datasetExp.getLength()) + "</nobr>";
                E.pageInfoDiv.innerHTML += "</nobr>"
            }
            E.freshPilot(D, this.readonly, this.allowAdd, this.allowDel)
        }
        return
    }
    this.totalPage = Number(D.getProperty("totalPage"));
    this.begin = Number(D.getProperty("begin"));
    this.pageSize = Number((!this.pageSize || this.pageSize < 1) ? D.getProperty("length") : this.pageSize);
    this.currentPage = Number(D.getProperty("currentPage"));
    if (parseInt(this.currentPage) < 1) {
        this.currentPage = 1;
        D.setProperty("currentPage", 1)
    }
    var C = Number(D.getProperty("totalPage")),
    H = Number(D.getProperty("begin"));
    if (("" + C) != "-1") this.SortByServer = true;
    else if (("" + H) == "0") this.SortByServer = false;
    else this.SortByServer = true;
    for (A = 0; A < this.__pagePilot.length; A++) {
        E = this.__pagePilot[A];
        E.freshPilot(D, this.readonly, this.allowAdd, this.allowDel)
    }
};
Datacell.prototype.registerPagePilot = function(A) {
    this.__pagePilot.push(A);
    A.datacell = this
};
Datacell.prototype.reload = function(A) {
    A = A !== undefined && A !== null ? A: this.recount;
    if (A) this.setPageParam(this.begin, this.pageSize, -1, this.isCount);
    this.loadData();
    this.refresh()
};
Datacell.prototype.selectNextRow = function() {
    if (this.activeRow) {
        var B = nextElement(this.activeRow),
        A = prevElement(this.activeRow);
        if (B) this.selectRow(B);
        else if (A) this.selectRow(A)
    }
};
Datacell.prototype.selectPreRow = function() {
    if (this.activeRow) {
        var B = nextElement(this.activeRow),
        A = prevElement(this.activeRow);
        if (A) this.selectRow(A);
        else if (B) this.selectRow(B)
    }
};
Datacell.prototype.selectFirstRow = function() {
    if (this.tbody.rows.length > 0) {
        var A = this.tbody.rows[0];
        this.selectRow(A)
    } else this.setSubComponent(null)
};
Datacell.prototype.selectLastRow = function() {
    if (this.tbody.rows.length > 0) {
        var A = this.tbody.rows[this.tbody.rows.length - 1];
        this.selectRow(A)
    }
};
Datacell.prototype.gotoPage = function(A) {
    if (this.isModefied) if (window.confirm(DATACELL_MODIFY_CONFIRM)) this.submit();
    this.pageParam = A;
    this.reload();
    this.onPageChange()
};
Datacell.prototype.validateAll = function() {
    var E = this.activeEntity,
    J = this.getActiveCell(),
    I = this.activeRow;
    for (var A = 0; A < this.table.tBodies[0].rows.length; A++) {
        var D = this.table.tBodies[0].rows[A],
        G = this.getEntity(D);
        if (G.status == Entity.STATUS_NEW || G.status == Entity.STATUS_MODIFIED) {
            this.activeEntity = G;
            for (var C = 0; C < this.fields.length; C++) {
                var H = this.fields[C];
                if (H.editor) {
                    this.setActiveCell(D.cells[C]);
                    var B = G.getProperty(H.fieldName);
                    H.editor.setValue(B);
                    var F;
                    try {
                        F = H.editor.validate()
                    } catch(K) {
                        F = false
                    }
                    if (!F) {
                        this.setActiveCell(null, D.cells[C]);
                        this.startEdit();
                        try {
                            F = H.editor.validate()
                        } catch(K) {
                            F = false
                        }
                        this.unlocked();
                        this.activeEntity = E;
                        return false
                    }
                }
            }
        }
    }
    this.activeEntity = E;
    this.activeCell = J;
    this.activeRow = I;
    return true
};
Datacell.prototype.save = function() {
    if (this.beforeSave() !== false) {
        this.submit();
        this.afterSave()
    }
};
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
            G.loadData(this.datasetExp.getSubmitXML());
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
Datacell.prototype.reset = function() {};
Datacell.prototype.processSortIcon = function(A) {};
Datacell.prototype.addcheckbox = function() {};
Datacell.prototype.setReadonly = function(A) {
    this.readonly = A || true
};
Datacell.prototype.getEntitiesXML = function(D, C) {
    var B = new StringBuffer();
    if (C.length > 0) for (var A = 0; A < C.length; A++) {
        C[A].name = D;
        B.append(C[A].toString())
    }
    return B.toString()
};
Datacell.prototype.getSubmitXpath = function() {
    var B = this.submitXpath;
    if (!B) if (this.xpath) {
        var A = this.xpath.split("/");
        B = A[A.length - 1]
    } else if (this.linkField) B = this.linkField;
    return B || "entity"
};
Datacell.prototype.submitAllByXML = function() {
    if (this.allEntitiesXMLDiv) this.allEntitiesXMLDiv.parentNode.removeChild(this.allEntitiesXMLDiv);
    var B = $createElement("div", {
        style: {
            display: "none"
        }
    });
    this.allEntitiesXMLDiv = B;
    var C = this.getSubmitXpath(),
    A = $createElement("input", {
        type: "hidden",
        name: "insertEntities",
        value: this.getEntitiesXML(C, this.datasetExp.getAlltEntities())
    });
    B.appendChild(A);
    this.container.appendChild(B)
};
Datacell.prototype.submitModifyByXML = function(F, A, G, C) {
    if (!C) {
        this.submitModifyBy3XML(F, A, G);
        return
    }
    if (this.modifyEntitiesXMLDiv) this.modifyEntitiesXMLDiv.parentNode.removeChild(this.modifyEntitiesXMLDiv);
    var B = $createElement("div", {
        style: {
            display: "none"
        }
    });
    this.modifyEntitiesXMLDiv = B;
    var D = [];
    D[0] = this.getEntitiesXML(F || "insertEntities", this.datasetExp.getInsertEntities());
    D[1] = this.getEntitiesXML(A || "updateEntities", this.datasetExp.getModifiedEntities());
    D[2] = this.getEntitiesXML(G || "deleteEntities", this.datasetExp.getRemovedEntities());
    var E = $createElement("input", {
        type: "hidden",
        name: C || "modifyEntities",
        value: D.join("")
    });
    B.appendChild(E);
    this.container.appendChild(B)
};
Datacell.prototype.submitModifyBy3XML = function(D, A, F) {
    if (this.modifyEntitiesXMLDiv) this.modifyEntitiesXMLDiv.parentNode.removeChild(this.modifyEntitiesXMLDiv);
    var B = $createElement("div", {
        style: {
            display: "none"
        }
    });
    this.modifyEntitiesXMLDiv = B;
    var E = $createElement("input", {
        type: "hidden",
        name: D || "insertEntities",
        value: this.getEntitiesXML(D || "insertEntities", this.datasetExp.getInsertEntities())
    }),
    G = $createElement("input", {
        type: "text",
        name: A || "updateEntities",
        value: this.getEntitiesXML(A || "updateEntities", this.datasetExp.getModifiedEntities())
    }),
    C = $createElement("input", {
        type: "hidden",
        name: F || "deleteEntities",
        value: this.getEntitiesXML(F || "deleteEntities", this.datasetExp.getRemovedEntities())
    });
    B.appendChild(E);
    B.appendChild(G);
    B.appendChild(C);
    this.container.appendChild(B)
};
Datacell.prototype.submitAllByHidden = function() {
    if (!this.validateAll()) return false;
    if (this.allEntitiesHiddenDiv) this.allEntitiesHiddenDiv.parentNode.removeChild(this.allEntitiesHiddenDiv);
    var A = $createElement("div", {
        style: {
            display: "none"
        }
    });
    this.container.appendChild(A);
    this.allEntitiesHiddenDiv = A;
    var B = this.datasetExp.name,
    C = this.getSubmitXpath(),
    D = Dataset2Array(this.datasetExp, C, true);
    this.addHiddenByArray(D, A)
};
Datacell.prototype.submitModifyByHidden = function(C, A, D) {
    if (!this.validateAll()) return false;
    if (this.modifyEntitiesHiddenDiv) this.modifyEntitiesHiddenDiv.parentNode.removeChild(this.modifyEntitiesHiddenDiv);
    var B = $createElement("div", {
        style: {
            display: "none"
        }
    });
    this.container.appendChild(B);
    this.modifyEntitiesHiddenDiv = B;
    var E = Dataset2Array(this.datasetExp.getInsertDataset(), C || "insertEntities");
    this.addHiddenByArray(E, B);
    E = Dataset2Array(this.datasetExp.getModifiedDataset(), A || "updateEntities");
    this.addHiddenByArray(E, B);
    E = Dataset2Array(this.datasetExp.getRemovedDataset(), D || "deleteEntities");
    this.addHiddenByArray(E, B)
};
Datacell.prototype.addHiddenByArray = function(D, B) {
    for (var A = 0; A < D.length; A++) {
        if (D[A].value == null) continue;
        var C = $createElement("input", {
            type: "hidden",
            name: D[A].key,
            value: D[A].value
        });
        B.appendChild(C)
    }
};
Datacell.prototype.unSelectRow = function() {
    var A = this.activeRow;
    if (!A) return true;
    else this.endEdit();
    if (this.onUnSelectRow(A, this.getEntity(A))) {
        removeClass(A, this.rowSelectStyleClass);
        this.activeRow = null;
        return true
    } else return false
};
Datacell.prototype.onUnSelectRow = function(A, B) {
    return true
};
Datacell.prototype.setValue = function(A) {
    this.datasetExp = A;
    $info("datacell setvalue " + A);
    this.refresh()
};
Datacell.prototype.getValue = function() {
    return this.datasetExp
};
Datacell.prototype.setFocus = function() {};
Datacell.prototype.lostFocus = function() {
    this.endEdit()
};
function Pilot(A) {
    this.id = A;
    PageControl.add(A, this);
    this.container = null;
    this.currPage = 1;
    this.totalPages = null;
    this.currRecord = null;
    this.totalRecords = null;
    this.width = "100%";
    this.height = "100%";
    this.inputClass = "INPUT";
    this.readonly = false;
    this.addButton = null;
    this.deleteButton = null;
    this.firstPageButton = null;
    this.nextPageButton = null;
    this.prePageButton = null;
    this.lastPageButton = null;
    this.gotoPageText = null;
    this.gotoPageButton = null;
    this.saveButton = null;
    this.reloadButton = null;
    this.begin = 0;
    this.length = 10;
    this.count = -1;
    this.isCount = false;
    this.totalPage = -1;
    this.currentPage = -1;
    this.isLast = false;
    this.isFirst = true;
    this.first_able = true;
    this.pre_able = true;
    this.next_able = true;
    this.last_able = true;
    this.add_able = true;
    this.delete_able = true
}
Datacell.prototype.setCustomTool = function(A, C) {
    var C = C || this.__pagePilot[0].id;
    this.customToolHTML = A;
    var B = $id(C + "_custom_zone");
    if (B) {
        B.innerHTML = this.customToolHTML;
        return true
    }
    return false
};
Datacell.prototype.hiddenButtons = function(A) {};
Pilot.prototype.init = function() {};
Pilot.prototype.build = function() {
    if (this.datacell.toolbarLocation == "top") this.container = this.datacell.topToolbarDiv;
    else this.container = this.datacell.bottomToolbarDiv;
    var C = this.datacell,
    R = C.toolsList.split(",");
    str = [];
    var B = ("" + this.datacell.pageSizeList) != "none" && ("" + this.datacell.pageSizeList) != "null";
    for (var P = 0; P < R.length; P++) {
        var F = R[P].split(":"),
        L = F[1] || null;
        F = F[0];
        if (L) L = " " + L + " ";
        switch (F) {
        case "nav":
            str = str.concat(["<div class=\"eos-tool-zone\" id=\"" + this.id + "_nav_zone\" >", (L && L.indexOf(" first ") < 0) ? "": "<a title=\"" + TOOLTIP_FIRST_PAGE + "\" id=\"" + this.id + "_first\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-first\"></div></a>", (L && L.indexOf(" prev ") < 0) ? "": "<a title=\"" + TOOLTIP_PRIVOUS_PAGE + "\" id=\"" + this.id + "_previous\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-prev\"></div></a>", (L && L.indexOf(" next ") < 0) ? "": "<a title=\"" + TOOLTIP_NEXT_PAGE + "\" id=\"" + this.id + "_next\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-next\"></div></a>", (L && L.indexOf(" last ") < 0) ? "": "<a title=\"" + TOOLTIP_LAST_PAGE + "\" id=\"" + this.id + "_last\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-last\"></div></a>", (L && L.indexOf(" goto ") < 0) ? "": "<input type=\"text\" size=\"3\" maxlength=\"4\" class=\"eos-grid-tool-goto-input\" id=\"" + this.id + "_input\" /><a title=\"" + TOOLTIP_GOTO + "\" id=\"" + this.id + "_goto\" href=\"javascript:void(0);\" style=\"margin-left:0px;\"><div class=\"eos-grid-tool-goto\"></div></a>", "</div>"]);
            break;
        case "pagesize":
            if (B) str = str.concat(["<div >", "<div class=\"eos-grid-tool-pageinfo\" id=\"" + this.id + "_pagesize\"  >", "</div>", "</div>"]);
            break;
        case "custom":
            str = str.concat(["<div class=\"eos-tool-zone\" id=\"" + this.id + "_custom_zone\" >", C.customToolHTML || "", "</div>"]);
            break;
        case "edit":
            str = str.concat(["<div class=\"eos-tool-zone\">", (L && L.indexOf(" add ") < 0) ? "": "<a title=\"" + TOOLTIP_ADD + "\" id=\"" + this.id + "_add\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-add\"></div></a>", (L && L.indexOf(" del ") < 0) ? "": "<a title=\"" + TOOLTIP_DELETE + "\" id=\"" + this.id + "_del\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-del\"></div></a>", (L && L.indexOf(" save ") < 0) ? "": "<a title=\"" + TOOLTIP_SAVE + "\" id=\"" + this.id + "_save\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-save\"></div></a>", (L && L.indexOf(" reload ") < 0) ? "": "<a title=\"" + TOOLTIP_RELOAD + "\" id=\"" + this.id + "_reload\" href=\"javascript:void(0);\"><div class=\"eos-grid-tool-reload\"></div></a>", "</div>"]);
            break;
        case "info":
            str = str.concat(["<div class=\"eos-tool-zone\">", "<div class=\"eos-grid-tool-pageinfo\" id=\"" + this.id + "_pageinfo\"  >", "</div>", "</div>"]);
            break
        }
    }
    this.container.innerHTML = str.join("\n");
    this.firstPageButton = $id(this.id + "_first");
    this.nextPageButton = $id(this.id + "_next");
    this.prePageButton = $id(this.id + "_previous");
    this.lastPageButton = $id(this.id + "_last");
    this.gotoPageText = $id(this.id + "_input");
    this.gotoPageButton = $id(this.id + "_goto");
    var H = $id(this.id + "_pagesize");
    if (B && H) {
        var N = {},
        O = this.datacell.pageSizeList.split(",");
        for (P = 0; P < O.length; P++) N[O[P]] = O[P];
        this.pageSizeSelect = createSelect(N, this.datacell.pageSize);
        this.pageSizeSelect.id = this.id + "_size";
        this.pageSizeSelect.className = "eos-pagesize-select";
        H.appendChild(this.pageSizeSelect)
    }
    this.addButton = $id(this.id + "_add");
    this.deleteButton = $id(this.id + "_del");
    this.saveButton = $id(this.id + "_save");
    this.reloadButton = $id(this.id + "_reload");
    this.pageInfoDiv = $id(this.id + "_pageinfo");
    var M = this;
    if (B && this.pageSizeSelect) {
        function S() {
            M.datacell.endEdit();
            M.datacell.pageSize = Number(M.pageSizeSelect.value);
            M.gotoPage(1)
        }
        eventManager.add(this.pageSizeSelect, "change", S)
    }
    function U() {
        var A = eventManager.getKeyCode();
        if (A == 27) {
            eventManager.stopEvent();
            M.gotoInputPage()
        }
    }
    eventManager.add(this.gotoPageText, "keypress", U);
    function E() {
        M.addRow()
    }
    eventManager.add(this.addButton, "click", E);
    function V() {
        C.endEdit();
        M.deleteRow()
    }
    eventManager.add(this.deleteButton, "click", V);
    function T() {
        C.endEdit();
        if (M.firstPageButton._disabled) return;
        M.gotoFirstPage()
    }
    eventManager.add(this.firstPageButton, "click", T);
    function Q() {
        C.endEdit();
        if (M.nextPageButton._disabled) return;
        M.gotoNextPage()
    }
    eventManager.add(this.nextPageButton, "click", Q);
    function J() {
        C.endEdit();
        if (M.prePageButton._disabled) return;
        M.gotoPrePage()
    }
    eventManager.add(this.prePageButton, "click", J);
    function D() {
        C.endEdit();
        if (M.lastPageButton._disabled) return;
        M.gotoLastPage()
    }
    eventManager.add(this.lastPageButton, "click", D);
    function K() {
        C.endEdit();
        if (M.gotoPageButton._disabled) return;
        M.gotoInputPage()
    }
    eventManager.add(this.gotoPageButton, "click", K);
    function G() {
        eventManager.stopEvent();
        C.endEdit();
        if (M.saveButton._disabled) return;
        M.datacell.save()
    }
    eventManager.add(this.saveButton, "click", G);
    function A() {
        C.endEdit();
        if (M.reloadButton._disabled) return;
        var A = true;
        if (M.datacell.isModefied && confirm(DATACELL_MODIFY_CONFIRM)) M.datacell.submit();
        else M.datacell.reload()
    }
    eventManager.add(this.reloadButton, "click", A);
    function I() {
        M.clearup()
    }
    eventManager.add(window, "unload", I)
};
Pilot.prototype.freshPilot = function(M, E, K, I) {
    var A = true,
    N = true,
    B = true,
    F = true,
    C = false;
    if (M) {
        C = true;
        this.begin = parseInt(M.getProperty("begin")) || 0;
        this.datacell.pageSize = Number(this.datacell.pageSize < 1 ? parseInt(M.getProperty("length")) : this.datacell.pageSize);
        this.count = parseInt(M.getProperty("count"));
        this.datacell.isCount = (M.getProperty("isCount") == "true");
        this.totalPage = parseInt(M.getProperty("totalPage"));
        this.currentPage = parseInt(M.getProperty("currentPage")) || 1;
        this.isLast = (M.getProperty("isLast") == "true");
        this.isFirst = (M.getProperty("isFirst") == "true");
        this.currentPage = this.currentPage < 1 ? 1 : this.currentPage;
        if (!this.datacell.isCount) this.currentPage = parseInt(this.begin / this.datacell.pageSize) + 1;
        var H = isNaN(this.currentPage / 1) || this.currentPage < 1 ? 1 : this.currentPage,
        J = isNaN(this.totalPage / 1) || this.totalPage < 1 ? 1 : this.totalPage,
        G = isNaN(this.count / 1) || this.count < 0 ? 0 : this.count,
        D = Number(isNaN(this.begin / 1) || this.begin < 0 ? 0 : this.begin) + 1,
        L = D + this.datacell.pageSize - 1;
        L = L > G ? G: L;
        L = L < D ? D: L;
        if (this.gotoPageText) this.gotoPageText.value = H;
        if (this.pageInfoDiv) {
            this.pageInfoDiv.innerHTML = "<nobr>";
            if (!this.datacell.isCount || G < 0) this.pageInfoDiv.innerHTML += "<nobr>" + this.datacell.PAGE_RECORDINFO_NOCOUNT.replace("{0}", D).replace("{1}", L) + "</nobr>";
            else {
                this.pageInfoDiv.innerHTML += this.datacell.PAGE_RECORDINFO.replace("{0}", D).replace("{1}", L).replace("{2}", G);
                this.pageInfoDiv.innerHTML += "&#160;&#160;<nobr>" + this.datacell.PAGE_INFO.replace("{0}", J) + "</nobr>"
            }
            this.pageInfoDiv.innerHTML += "</nobr>"
        }
    }
    if (this.firstPageButton) this.firstPageButton._disabled = this.isFirst || !C;
    if (this.prePageButton) this.prePageButton._disabled = this.isFirst || !C;
    if (this.nextPageButton) this.nextPageButton._disabled = this.isLast || !C;
    if (this.lastPageButton) this.lastPageButton._disabled = this.isLast || this.count < 0 || !C;
    if (this.isFirst || !C) {
        setOpacity(this.firstPageButton, 0.5);
        setOpacity(this.prePageButton, 0.5)
    } else {
        setOpacity(this.firstPageButton, 1);
        setOpacity(this.prePageButton, 1)
    }
    if (this.isLast || !C) {
        setOpacity(this.nextPageButton, 0.5);
        setOpacity(this.lastPageButton, 0.5)
    } else {
        setOpacity(this.nextPageButton, 1);
        setOpacity(this.lastPageButton, 1)
    }
    if (this.count < 0 || !C) setOpacity(this.lastPageButton, 0.5);
    if (!C || !M.getProperty("begin")) if (this.gotoPageButton) {
        this.gotoPageButton._disabled = true;
        this.gotoPageText.disabled = true;
        setOpacity(this.gotoPageButton, 0.5)
    }
};
Pilot.prototype.clearup = function() {
    for (var A in this) {
        try {
            this[A] = null
        } catch(B) {
            $handle(B)
        }
    }
};
Pilot.prototype.gotoPage = function(A) {
    A = Number(A);
    if (isNaN(A)) A = 1;
    A = parseInt(A, 10);
    this.currentPage = A;
    if (A < 1) A = 1;
    var D = (A - 1) * Number(this.datacell.pageSize);
    if (D < 0) D = 0;
    var B = this.count;
    if (!this.datacell.isCount) B = -1;
    var C = this.datacell.setPageParam(D, this.datacell.pageSize, B, this.datacell.isCount);
    this.datacell.gotoPage(C)
};
Pilot.prototype.gotoInputPage = function() {
    var A = Number(this.gotoPageText.value);
    if (isNaN(A)) alert(DATACELL_PAGENUM_ERR);
    else this.gotoPage(A)
};
Pilot.prototype.gotoFirstPage = function() {
    if (this.first_able) this.gotoPage(1)
};
Pilot.prototype.gotoNextPage = function() {
    if (this.next_able) this.gotoPage(this.getCurrPage() + 1)
};
Pilot.prototype.gotoPrePage = function() {
    if (this.pre_able) this.gotoPage(this.getCurrPage() - 1)
};
Pilot.prototype.gotoLastPage = function() {
    if (this.last_able) this.gotoPage(this.getPageCount())
};
Pilot.prototype.addRow = function() {
    this.datacell.addRow()
};
Pilot.prototype.deleteRow = function() {
    this.datacell.deleteRow()
};
Pilot.prototype.reloadData = function() {
    this.datacell.reload()
};
Pilot.prototype.submit = function() {
    this.datacell.submit()
};
Pilot.prototype.setButton = function(F, G, E, B, A, D, C) {
    if (F) {
        this.first_able = true;
        this.firstPageButton.src = this.iconPath + PICTURE_FIRST_BUTTON
    } else {
        this.first_able = false;
        this.firstPageButton.src = this.iconPath + PICTURE_FIRST_DISABLE
    }
    if (G) {
        this.pre_able = true;
        this.prePageButton.src = this.iconPath + PICTURE_PRIVOUS_BUTTON
    } else {
        this.pre_able = false;
        this.prePageButton.src = this.iconPath + PICTURE_PRIVOUS_DISABLE
    }
    if (E) {
        this.next_able = true;
        this.nextPageButton.src = this.iconPath + PICTURE_NEXT_BUTTON
    } else {
        this.next_able = false;
        this.nextPageButton.src = this.iconPath + PICTURE_NEXT_DISABLE
    }
    if (B) {
        this.last_able = true;
        this.lastPageButton.src = this.iconPath + PICTURE_LAST_BUTTON
    } else {
        this.last_able = false;
        this.lastPageButton.src = this.iconPath + PICTURE_LAST_DISABLE
    }
    if (D || A) this.addButton.src = this.iconPath + PICTURE_ADD_DISABLE;
    else this.addButton.src = this.iconPath + PICTURE_ADD_BUTTON;
    if (!C || A) this.deleteButton.src = this.iconPath + PICTURE_DELETE_DISABLE;
    else this.deleteButton.src = this.iconPath + PICTURE_DELETE_BUTTON
};
Pilot.prototype.getPageCount = function() {
    return this.totalPage
};
Pilot.prototype.getCurrPage = function() {
    return this.currentPage
};
Datacell.prototype.rowMoveUp = function(B) {
    B = B || this.activeRow;
    if (!B) return;
    var F = B.parentNode,
    C = B.rowIndex,
    D = B.getAttribute("__entity_rowno");
    if (C < 1) return;
    var E = F.rows[C - 1],
    A = E.getAttribute("__entity_rowno");
    F.insertBefore(B, E)
};
Datacell.prototype.rowMoveDown = function(B) {
    B = B || this.activeRow;
    if (!B) return;
    var F = B.parentNode,
    C = B.rowIndex,
    D = B.getAttribute("__entity_rowno");
    if (C >= F.rows.length - 1) return;
    var E = F.rows[C + 1],
    A = E.getAttribute("__entity_rowno");
    F.insertBefore(E, B)
};
Datacell.prototype.getActiveRow = function() {
    return this.activeRow
};
Datacell.prototype.getActiveCell = function() {
    return this.activeCell
};
Datacell.prototype.getActiveEntity = function() {
    return this.getEntity(this.getActiveRow())
};
Datacell.prototype.showEditor = function() {
    PageControl.addtoStack(this);
    this.datacellContainer.style.display = "";
    this.datacellContainer.onmousedown = function() {
        eventManager.stopEvent()
    }
};
Datacell.prototype.hideEditor = function() {
    if (this.activeEditor) this.activeEditor.hideEditor();
    if (this.datacellContainer) {
        this.datacellContainer.style.display = "none";
        this.datacellContainer.onmousedown = null
    }
};
Datacell.prototype.hide = function() {
    this.hideEditor()
};
Datacell.prototype.setPosition = function(C, A, B, D) {
    this.datacellContainer.style.position = "absolute";
    var E = getMaxZindex(),
    F = getMaxZindex(document);
    if (this.datacellContainer.style.zIndex != F) this.datacellContainer.style.zIndex = F;
    setElementXY(this.datacellContainer, [C, A])
};
Datacell.prototype.getDisplayValue = function(A) {
    return A
};
Datacell.prototype.isFocus = function() {
    return false
};
Datacell.prototype.validate = function() {
    return true
};
Datacell.prototype.onSelectRow = function(A, B) {};
Datacell.prototype.onDeleteRow = function(A, B) {
    return true
};
Datacell.prototype.onSubmit = function() {
    return true
};
Datacell.prototype.onAddRow = function(A) {
    return true
};
Datacell.prototype.onUpdateCell = function() {};
Datacell.prototype.onLoadData = function() {
    return true
};
Datacell.prototype.onUpdateRow = function(A) {
    return true
};
Datacell.prototype.onUpdate = function() {
    return true
};
Datacell.prototype.beforeInit = function() {};
Datacell.prototype.afterInit = function() {};
Datacell.prototype.beforeLoadData = function() {};
Datacell.prototype.afterLoadData = function() {};
Datacell.prototype.beforeRefresh = function() {};
Datacell.prototype.afterRefresh = function() {};
Datacell.prototype.beforeSubmit = function() {};
Datacell.prototype.afterSubmit = function() {};
Datacell.prototype.beforeSubmit = function() {};
Datacell.prototype.beforeSave = function() {};
Datacell.prototype.afterSave = function() {};
Datacell.prototype.beforeRefreshCell = function() {};
Datacell.prototype.beforeAdd = function() {};
Datacell.prototype.afterAdd = function() {};
Datacell.prototype.beforeEdit = function(B, C, A) {};
Datacell.prototype.afterEdit = function(A, C, B) {};
Datacell.prototype.beforeDel = function() {};
Datacell.prototype.afterDel = function() {};
Datacell.prototype.beforeSelectRow = function() {};
Datacell.prototype.afterSelectRow = function() {};
Datacell.prototype.beforeUnselectRow = function() {};
Datacell.prototype.afterUnselectRow = function() {};
Datacell.prototype.onComplete = function() {};
Datacell.prototype.onPageChange = function() {};
Datacell.prototype.onClickHead = function(B, C, D, A) {};
Datacell.prototype.onClickCell = function(F, B, D, C, E, A) {};
Datacell.prototype.onClickRow = function(D, C, B, A) {};
Datacell.activeGrid = null;
Datacell.STATUS_BLANK = 0;
Datacell.STATUS_INITED = 1;
Datacell.STATUS_MODEFIED = 2;
Datacell.BLOLK_ID = "__DATACELL_BLOCK";
Datacell.__INDEX_NAME = "__INDEX";
Datacell.outClick = function(A, K, G) {
    var B = A.datacell,
    F = B.editorContainer;
    if (F && K) {
        var L = G.x,
        J = G.y,
        D = getPosition(F),
        E = D.top,
        H = D.left,
        C = H + F.offsetWidth,
        I = E + F.offsetHeight;
        if (!F.contains(K)) if (E > J || H > L || C < L || I < J) B.endEdit()
    } else B.endEdit()
};
Datacell.isCell = function(A) {
    if (A && A.tagName) {
        var B = A.tagName.toLowerCase() == "td",
        C = A.getAttribute("fieldId");
        if (C && B) return true;
        return false
    }
    return false
};
Datacell.isHead = function(A) {
    if (A.tagName) {
        var B = A.tagName.toLowerCase() == "th",
        C = A.getAttribute("__type") == "head";
        if (C && B) return true;
        return false
    }
    return false
};
Datacell.getDatacellByCell = function(C) {
    try {
        var A = getParentByTagName(C, "table"),
        B = A.datacell;
        return B
    } catch(D) {
        $handle(D);
        return null
    }
};
Datacell.checkCell = function(C, G, H, D, A, E, J) {
    var B = "",
    F = J;
    if (C == E) {
        B = "checked";
        F = E
    }
    F = F || F === 0 ? F: "";
    var I = "fieldFunc_" + A.id + "_" + D;
    Datacell[I] = function(B) {
        var C = A.fields[D].fieldName;
        if (B.checked) G.setProperty(C, E);
        else G.setProperty(C, J)
    };
    return "<input type=\"checkbox\" " + B + " value=\"" + ("" + F).replace(/\"/igm, "\\\"").replace(/\n/igm, "\\n") + "\" " + " onclick=\"Datacell['" + I + "'](this)\"  />"
};
Datacell.checkCellHandler = function(A) {};
Datacell.getFormatValue = function(A, B) {
    if (isDateFormat(B)) {
        if (isTimeFormat(B)) return dateFormat(A, DATETIME_FORMAT, B);
        else return dateFormat(A, DATE_FORMAT, B)
    } else if (isTimeFormat(B)) return timeFormat(A, TIME_FORMAT, B);
    else return formatNumber(A, B)
};
Datacell.$editor = function(A) {
    if (!A || (A && A.getValue)) return A;
    return SimpleEditor(A)
};
Datacell.getSortValue = function(C, B, E) {
    var D = C.datasetExp.get(B.getAttribute("__entity_rowno") / 1).getProperty(E),
    A = D ? Number(D) : D;
    D = isNaN(A) ? D: A;
    return D
};
Datacell.getSortDefaultValue = function(B, A, C) {
    return A.getAttribute("__entity_rowno") / 1
};
Datacell.initGlobalEvent = function() {
    eventManager.add(document, "keydown",
    function(A) {
        Datacell.activeGrid && Datacell.activeGrid._onKeyDown(A)
    });
    eventManager.add(document.body, "click",
    function(A) {
        if (Datacell.activeGrid && Datacell.activeGrid.headDiv.clientHeight > 10);
    });
    eventManager.add(document.body, "mousemove",
    function(A) {
        Datacell.activeGrid && Datacell.doDocGridHandler(A, Datacell.activeGrid)
    });
    eventManager.add(document.body, "mouseup",
    function(A) {
        Datacell.activeGrid && Datacell.endDocGridHandler(A, Datacell.activeGrid)
    })
};
Datacell.doDocGridHandler = function(C, A) {
    C = C || window.event;
    var B = eventManager.getPointX();
    if (A.splitLine.style.display == "block") {
        var D = B - A.viewportXY[0];
        A.splitLine.style.left = D + "px"
    }
};
Datacell.endDocGridHandler = function(E, A) {
    E = E || window.event;
    var C = eventManager.getPointX();
    if (A.splitLine.style.display == "block") {
        var F = A.resizeColumn;
        F.newRightX = C - A.viewportXY[0];
        var G = F.newRightX - F.oldRightX,
        B = parseInt(CSSUtil.getRule("." + F.styleClass, true).style.width),
        D = G + B;
        A.setColumnWidth(F, D);
        A.resizeColumn = false;
        A.splitLine.style.display = "none";
        A.headDiv.style.cursor = "auto";
        A.gridMask.style.cursor = "auto";
        A.gridMask.style.display = "none";
        A.syncScroll()
    }
};
Datacell.onWindowResize = function(A) {
    if (_eos_curr_open_panel != null) if (!A.isInCurrPanel()) return;
    A.bodyDivHeight = A.containerTD.offsetHeight - A.headHeight;
    var B = false;
    if (A.width.indexOf("%") != -1) {
        A.bodyDiv.style.width = "1px";
        B = true
    }
    if (A.height.indexOf("%") != -1) {
        A.container.style.height = "100%";
        A.bodyDiv.style.height = "1px";
        A.freezeBodyDiv.style.height = A.bodyDiv.clientHeight + "px";
        B = true
    } else {
        if (A.bodyDivHeight > 0) A.bodyDiv.style.height = A.bodyDivHeight + "px";
        if (A.bodyDiv.clientHeight > 0) A.freezeBodyDiv.style.height = A.bodyDiv.clientHeight + "px"
    }
    if (B) setTimeout(function() {
        return Datacell.autoResize.apply(A, [A])
    },
    1);
    A.syncScroll()
};
Datacell.autoResize = function() {
    var A = this;
    if (A.height.indexOf("%") != -1) {
        A.bodyDivHeight = A.containerTD.offsetHeight - A.headHeight;
        if (A.bodyDivHeight > 0) A.bodyDiv.style.height = A.bodyDivHeight + "px";
        if (A.bodyDiv.clientHeight > 0) A.freezeBodyDiv.style.height = A.bodyDiv.clientHeight + "px"
    }
    if (A.width.indexOf("%") != -1) A.bodyDiv.style.width = A.containerTD.offsetWidth
};
Datacell.prototype.autoResizeS1 = function() {
    var A = this;
    A.bodyDivHeight = A.containerTD.offsetHeight - A.headHeight;
    var B = false;
    if (A.width.indexOf("%") != -1) {
        A.bodyDiv.style.width = "1px";
        B = true
    }
    if (A.height.indexOf("%") != -1) {
        A.container.style.height = "100%";
        A.datacellContainer.style.height = "100%";
        A.bodyDiv.style.height = "1px";
        A.freezeBodyDiv.style.height = A.bodyDiv.clientHeight + "px";
        B = true
    } else {
        if (A.bodyDivHeight > 0) A.bodyDiv.style.height = A.bodyDivHeight + "px";
        if (A.bodyDiv.clientHeight > 0) A.freezeBodyDiv.style.height = A.bodyDiv.clientHeight + "px"
    }
};
Datacell.prototype.autoResizeS2 = Datacell.autoResize;
Datacell.prototype.isInCurrPanel = function() {
    if (_eos_curr_open_panel != null) {
        var A = this.datacellContainer.offsetParent;
        while (true) {
            if (A == null) return false;
            if (A == _eos_curr_open_panel.table) return true;
            A = A.offsetParent
        }
    } else return false
};
Datacell.createSwidthCheckBox = function(C, H, G, F, A) {
    var I = A.getField(F),
    D = I.checkedValue,
    E = I.unCheckedValue,
    B = "";
    if (D == C) B = "checked";
    return "<input type=\"checkbox\" " + B + " swidthCheckbox=\"true\"/>"
};
function VerifyCode(A) {
    this.config = A
}
VerifyCode.prototype.init = function() {
    this.container = $id(this.config.name + "_container");
    if (this.config.hasInput == true) this.initInput();
    this.initImg();
    PageControl.add(this.config.name, this)
};
VerifyCode.prototype.initInput = function() {
    if (this.config.style) this.text = $create("<input style=" + this.config.style + ">");
    else this.text = $create("input");
    this.text.className = this.config.className || "";
    this.text.name = this.config.name;
    this.text.validateAttr = this.config.validateAttr || "";
    this.container.appendChild(this.text)
};
VerifyCode.prototype.initImg = function() {
    this.codeImage = $create("img");
    this.container.appendChild(this.codeImage);
    this.codeImage.style.verticalAlign = "text-bottom";
    this.codeImage.style.marginLeft = "6px";
    this.codeImage.title = VERIFYCODETITLE;
    this.imageUrl = contextPath + "/common/jsp/codeImage.jsp?name=" + this.config.name + "&imageHeight=" + this.config.imageHeight + "&length=" + this.config.length + "&type=" + this.config.type;
    this.codeImage.src = this.imageUrl;
    var A = this;
    eventManager.add(this.codeImage, "click",
    function() {
        return function() {
            this.codeImage.src = this.imageUrl + "&timestamp=" + Math.random()
        }.call(A)
    })
}