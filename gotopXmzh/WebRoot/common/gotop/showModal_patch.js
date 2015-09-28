function isloadfun(F, C, D){
	try{
		var E = getModalDialog(F);
	    var A = $id(E.id + "__model_dialog_frame", E.win.document);
	    if (A) {
	    	var pasw = parseInt(A.style.width,10);
     		if(pasw<1){
    			window.setTimeout(function(){
    				reloadFramefun(F, C, D);
				},500);
     		}
	    }
	}catch(e){}
}
function reloadFramefun(F, C, D){
	try{
		var E = getModalDialog(F);
	    var A = $id(E.id + "__model_dialog_frame", E.win.document);
	    if (A) {
	        if (D - 28 >= 0){
	        	A.style.height = D - 32 + "px";
	        }
	        A.style.width = C - 14 + "px";
	    }
	}catch(e){}
}
function __showModal(Z, G, C, O, Y, L, V, B) {
	var maximizeButton = false;
	var closeButton = true;
	if(C.Argument){
		if(C.Argument.maximizeButton==true){
			maximizeButton = true;
		}
		if(C.Argument.closeButton==false){
			closeButton=false;
		}
	}
	var mdc = "			<DIV id=popupControls class='eos-popwin-head-button'> ";
		if(maximizeButton){
			mdc+= "					<span> "
				+ "						<a id='"+Z+"_openWin' href='javascript:void(0);' style=\"background-image: url('"+contextPath+"/common/skins/default/images/basic/small.png');display: none;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> "
				+ "					</span> "
				+ "					<span> "
				+ "						<a id='"+Z+"_openMinWin' href='javascript:void(0);' style=\"background-image: url('"+contextPath+"/common/skins/default/images/basic/big.png');\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> "
				+ "					</span> ";
		}
		if(closeButton){
			mdc+= "					<a id='"+Z+"_closeWin' href='javascript:void(0);' style=\"background-image: url('"+contextPath+"/common/skins/default/images/basic/title_close_btn.gif');\">&#160;</a> "
				;
		}
		mdc+= "			</DIV> ";
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
    var S = [F, "<div style=\"width:100%;height:100%\">", "<div id=\"" + Z + "__model_dialog_mask\" class=\"eos-div-mask\" style=\"position:absolute;top:0px;left:0px;display:none;\"></div>", "<TABLE  cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" height=\"100%\" class=\"eos-dialog\"><TR><TD ", M, " class=\"left-top-conner\"><div class=\"ieBlank\"></div></TD><TD  ", M, " class=\"top\"></TD><TD  ", M, " class=\"right-top-conner\"><div class=\"ieBlank\"></div></TD></TR><TR><TD  ", M, " class=\"left\"><div class=\"ieBlank\"></div></TD><TD style=\"background-color: #eaf0f2;\">", "<div style=\"width:100%;cursor:move\" id=\"" + Z + "__model_dialog_head\" class=\"eos-popwin-head\" ><a id=\"" + Z + "__model_dialog_focus\" href=\"#f\"></a>", "<div class=\"eos-popwin-head-icon\">&#160;</div>", "<div class=\"eos-popwin-head-title\" id=\"eos-popwin-head-title\">" + (B || " Dialog Window") + "</div>"
    ,mdc
    , "</div>", "<div id=\"" + Z + "__model_dialog_body\" class=\"eos-popwin-body\">", "</div>", "</TD><TD  ", M, " class=\"right\" id=\"", Z, "_right\"><div class=\"ieBlank\"></div></TD></TR><TR><TD  ", M, " class=\"left-bottom-conner\"></TD><TD  ", M, " class=\"bottom\" id=\"", Z, "_bottom\"></TD><TD  ", M, " class=\"right-bottom-conner\" id=\"", Z, "_rightBottom\"></TD></TR></TABLE>", "</div>"].join(""),
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

	var cwd = T.contentWindow;
	if(maximizeButton){
		var openMinWin = C.win.document.getElementById(Z+"_openMinWin");
		var openWin = C.win.document.getElementById(Z+"_openWin");
		openMinWin.onmousedown=function(){
			var width=C.win.document.body.scrollWidth;
			var height=C.win.document.body.scrollHeight;
			cwd.resize(width,height);
			cwd.moveModelDialog(Z,0,0);
			openWin.style.display="";
			openMinWin.style.display="none";
			return false;
		}
		openWin.onmousedown=function(){
			cwd.resize(O,Y);
			cwd.moveCenter();
			try{
				var B = cwd["_eos_dialog_id"];
	    		var A = getModalDialog(B);
	    		if(parseInt(A.container.style.top,10)<0){
	    			A.container.style.top="0px";
	    		}
			}catch(e){}
			openWin.style.display="none";
			openMinWin.style.display="";
			return false;
		}
	}
	if(closeButton){
		var closeWin = C.win.document.getElementById(Z+"_closeWin");
		closeWin.onmousedown=function(){//onmousedown
			if(cwd.beforeClose){
				if(cwd.beforeClose()==false){
					return false;
				}
			}
			cwd.returnValue="";
			cwd.closeD();
		};
	}
	isloadfun(Z, O, Y);
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
//                B.pActiveElement && B.pActiveElement.focus();
                B.pActiveElement = null
            } catch(E) {}
        }
        D.onresize = D._bakresize
    }
}