    if (document.implementation.hasFeature("XPath", "3.0")) {
        XMLDocument.prototype.selectNodes = function(path, C) {
			if (C){}else{C = this};
			if(path){}else{
				alert("xpath路径不能为空");
				return;
			}
			var pathArra = path.split("/");
			var clientArra = [];
			var index = 1;
			if(pathArra[0]){
				index=0;
			}
			selectNodes_recurrence(C,pathArra,clientArra,index);
			return clientArra;
        };
        XMLDocument.prototype.selectSingleNode = function(path, C) {
			if (C){}else{C = this};
			if(path){}else{
				alert("xpath路径不能为空");
				return;
			}
			var pathArra = path.split("/");
			var index = 1;
			if(pathArra[0]){
				index=0;
			}
			var node = selectSingleNode_recurrence(C,pathArra,index);
			return node;
        };
        Element.prototype.selectNodes = function(path,C) {
			if (C){}else{C = this};
			if(path){}else{
				alert("xpath路径不能为空");
				return;
			}
			var pathArra = path.split("/");
			var index = 1;
			if(pathArra[0]){
				index=0;
			}
			var clientArra = [];
			selectNodes_recurrence(C,pathArra,clientArra,index);
			return clientArra;
        };
        Element.prototype.selectSingleNode = function(path,C) {
			if (C){}else{C = this};
			if(path){}else{
				alert("xpath路径不能为空");
				return;
			}
			var pathArra = path.split("/");
			var index = 1;
			if(pathArra[0]){
				index=0;
			}
			var node = selectSingleNode_recurrence(C,pathArra,index);
			return node;
        };
        if(Node){
			Node.prototype.selectNodes = function(path,C){
				if (C){}else{C = this};
				if(path){}else{
					alert("xpath路径不能为空");
					return;
				}
				var pathArra = path.split("/");
				var index = 1;
				if(pathArra[0]){
					index=0;
				}
				var clientArra = [];
				selectNodes_recurrence(C,pathArra,clientArra,index);
				return clientArra;
			}
			Node.prototype.selectSingleNode = function(path,C){
				if (C){}else{C = this};
				if(path){}else{
					alert("xpath路径不能为空");
					return;
				}
				var pathArra = path.split("/");
				var index = 1;
				if(pathArra[0]){
					index=0;
				}
				var node = selectSingleNode_recurrence(C,pathArra,index);
				return node;
			}
		}
    }
		function createXmlDom(E) {
		    if (isIEFun()) {
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
//		        A.__initError__();
		        A.addEventListener("load",
		        function() {
		            this.__checkForErrors__();
		            this.__changeReadyState__(4)
		        },
		        false);
		        A.loadXML = function(E) {
		            var D = new DOMParser(),
		            B = D.parseFromString(E, "text/xml");
		            while (this.firstChild) this.removeChild(this.firstChild);
		            for (var C = 0; C < B.childNodes.length; C++) {
		                var A = this.importNode(B.childNodes[C], true);
		                this.appendChild(A)
		            }
		        };
		        if (E) A.loadXML(E);
		        return A
		    } else throw new Error("Your browser doesn't support an XML DOM object.")
		}
		function selectNodes_recurrence(nds,parra,carra,index){
			if(nds==null){
				return;
			}
			var plen = parra.length;
			if(plen<index){
				return;
			}
			var cnode = nds.childNodes;
			for(var i=0;i<cnode.length;i++){
				var node = cnode.item(i);
//				alert(nds.nodeName+"----"+i+"---"+node.nodeName+"=="+parra[index]);
				if(node.nodeName==parra[index]){
					if(plen==(index+1)){
						carra.push(node);
					}else{
						if(node.hasChildNodes()){
							selectNodes_recurrence(node,parra,carra,index+1);
						}
					}
				}
			}
		}
		function selectSingleNode_recurrence(nds,parra,index){
			if(nds==null){
				return;
			}
			var plen = parra.length;
			if(plen<index){
				return;
			}
			var cnode = nds.childNodes;
			for(var i=0;i<cnode.length;i++){
				var node = cnode.item(i);
//				alert(nds.nodeName+"----"+i+"---"+node.nodeName+"=="+parra[index]);
				if(node.nodeName==parra[index]){
					if(plen==(index+1)){
						return node;
					}else{
						if(node.hasChildNodes()){
							var node = selectSingleNode_recurrence(node,parra,index+1);
							if(node){
								return node;
							}
						}
					}
				}
			}
			return null;
		}
		function getNodeValue(C) {
			if(window.ActiveXObject){
			    var B = null;
			    if (!C) return null;
			    var A = C.getAttribute("__isNullOrEmpty");
			    if (A == "null") return null;
			    if (A == "empty") return "";
			    if (C.text) B = C.text;
			    else if (C.childNodes.length > 1) B = C.xml.replace(/<\/?[^>]+>/gi, "");
			    else if (C.firstChild) B = C.firstChild.data;
			    return B
		    }else{
			    var B = null;
			    if (!C) return null;
			    var A = C.getAttribute("__isNullOrEmpty");
			    if (A == "null") return null;
			    if (A == "empty") return "";
			    if (C.textContent){
			  		B = C.textContent;
				}
			    if (C.firstChild){
			    	B = C.firstChild.nodeValue;
			    }
			    return B
		    }
		}
		function $createElement(F, E) {
		    var B = E ? (E.doc || document) : document;
		    if (F.indexOf("<") > -1) {
		        var C = B.createElement("div");
		        C.innerHTML = F;
		        F = C.firstChild
		    } else if (isIE && E && (E.name || E.type)) {
		        var G = (E.name) ? " name=\"" + E.name + "\"": "";
		        var D = (E.type) ? " type=\"" + E.type + "\"": "";
//		        F = "<" + F + G + D + ">";
//		        F = B.createElement(F);

				var eName = E.name;
				var eType = E.type;

		        delete E.name;
		        delete E.type;

				
				F = B.createElement(F);
				if(eName){
					F.name= eName;
				}
				if(eType){
					F.type= eType;
				}
		    } else F = B.createElement(F);
		    if (E) {
		        if (E.style) {
		            for (var A in E.style) F.style[A] = E.style[A];
		            delete E.style
		        }
		        for (A in E){
		        	F[A] = E[A];
		        }
			    if(E.value){
			    	F.value=E.value;
			    }
		    }
		    return F
		}