var digitalBit = 12;
var normalBit = 3;
var xsmxBit = 1;
var phoneBit = 7;
var htBit = 20;



if(ComboSelect){
    
    
	ComboSelect.prototype.init = function() {
		if (this.inited === true || this.beforeInit() === false)
			return;
		this.filterField = this.filterField || this.textField;
		this.container = $id(this.id + "_container");
		this.inputObject = $id(this.id + "_input");
		this.hiddenObject = $id(this.id + "_hidden");
		this.readonly = (this.readonly === true || this.readonly === false)
				? this.readonly
				: this.readOnly;
		this.inputObject.readOnly = !!this.readonly;
		this.hiddenObject.readOnly = !!this.readonly;
		this.inputObject.disabled = !!this.disabled;
		this.hiddenObject.disabled = !!this.disabled;
		this.eventObject = $id(this.id + "_button");
		this.button = this.eventObject;
		this.text = this.inputObject;
		this.hidden = this.hiddenObject; 
		var comboSelect = this;
		this.eventObject.src = contextPath
				+ "/common/skins/skin0/images/comboselect/comboSelect_button.gif";
		this.eventObject.onmouseover = function() {
			if (comboSelect.getDisabled() || comboSelect.getReadOnly())
				return;
			this.src = contextPath
					+ "/common/skins/skin0/images/comboselect/comboSelect_button_over.gif"
		};
		this.eventObject.onmouseout = function() {
			if (comboSelect.getDisabled() || comboSelect.getReadOnly())
				return;
			this.src = contextPath
					+ "/common/skins/skin0/images/comboselect/comboSelect_button.gif"
		};
		this.eventObject.onmousedown = function() {
			if (comboSelect.getDisabled() || comboSelect.getReadOnly())
				return;
			this.src = contextPath
					+ "/common/skins/skin0/images/comboselect/comboSelect_button_down.gif"
		};
		this.initSize();
		this.inputObject.value = this.defaultText;
		this.validate = (typeof this.validateFunc) == "string"
				? eval(this.validateFunc)
				: this.validateFunc;
//      修改成多条过滤
		var fts = this.filterType.toLowerCase().split(",");
		var array = Array(fts.length);
		for(var i=0;i<fts.length;i++){
			array[i]=(fts[i] != "like");
		}
		this.startWith = array;
		
		this.initEvent();
		this.inited = true;
		this.afterInit()
	};
    /*
    * 重写系统的setTimeOut使其可接受参数。
    */
	window.setTimeoutEosWeb = function(fun,tCount){
		var psto = null;//保留系统原方法句柄
		if(window.oldSetTimeout){
			psto = window.oldSetTimeout;
		}else{
			psto = window.setTimeout
		}
		if(typeof fun =='function'){
			var args = Array.prototype.slice.call(arguments,2);//截取从第三个参数开始的所有参数
			var funPushP = function(){
				return fun.apply(null,args);//把获取的参数传递给 传入的函数句柄      
			};
			return psto(funPushP,tCount);
		}
		return psto(fun,tCount);
	}
	/**
	* 增加版本控件version
	*/
	ComboSelect.prototype.filter = function(version) {
		var A = this.inputObject.value, B = 0;
		if (A)
			B = this.doFilter(A,null,version);
		else
			B = this.cancleFilter(A);
		return B
	};
	ComboSelect.prototype.initEvent = function() {
		var D = this, E = ","
				+ [EOSKey.TAB, EOSKey.ENTER, EOSKey.SHIFT, EOSKey.CTRL,
						EOSKey.PAUSE, EOSKey.CAPSLOCK, EOSKey.PAGEUP,
						EOSKey.PAGEDOWN, EOSKey.END, EOSKey.HOME, EOSKey.LEFT,
						EOSKey.UP, EOSKey.RIGHT, EOSKey.DOWN, EOSKey.INSERT,
						EOSKey.WIN, EOSKey.WIN_R, EOSKey.MENU, EOSKey.F1,
						EOSKey.F2, EOSKey.F3, EOSKey.F4, EOSKey.F5, EOSKey.F6,
						EOSKey.F7, EOSKey.F8, EOSKey.F9, EOSKey.F10, EOSKey.F11,
						EOSKey.F12, EOSKey.NUMLOCK, EOSKey.SCROLLLOCK].join(",")
				+ ",";
		function B(A) {
		}
		function C(A) {
			if (D.getDisabled() || D.getReadOnly())
				return;
			A = A || window.event;
			if (A.keyCode == EOSKey.ENTER)
				eventManager.stopEvent();
			else if (A.keyCode == EOSKey.UP) {//适用于连续跳步
				if (!D.showStatus)
					D.show(true);
				else
					D.activePrevOption();
				eventManager.stopEvent(A)
			} else if (A.keyCode == EOSKey.DOWN) {//适用于连续跳步
				if (!D.showStatus)
					D.show(true);
				else
					D.activeNextOption();
				eventManager.stopEvent(A)
			}
		}
		function H(B) {
			if (D.getDisabled() || D.getReadOnly())
				return;
			if (this.optionsTable && this.optionsTable.tBodies
					&& this.optionsTable.tBodies[0])
				return;
			B = B || window.event;
			$error("key:" + B.keyCode);
			eventManager.stopEvent(B);
			if (B.keyCode == EOSKey.ENTER) {
				eventManager.stopEvent();
//				避免重复执行展开收缩操作，影响性能
				if(D.inputObject.stateType!=false){
					if (!D.showStatus){
						D.show(true);
					}else {
						if(D.beforeHEnter && D.beforeHEnter()==false){}else{
							var A = D.getFilterNum();
							if (A > 0 || !D.allowInput)
								D.selectOption(D.actionOptionRow);
						}
						D.hide()
					}
				}
				D.inputObject.stateType = true;
			} else if (B.keyCode == EOSKey.ESC) {
				if (D.showStatus)
					D.hide();
				else {
					PageControl.setFocus(D);
					D.show(true)
				}
				eventManager.stopEvent(B)
			}else if (B.keyCode == EOSKey.UP) {//适用于单步
//				if (!D.showStatus)
//					D.show(true);
//				else
//					D.activePrevOption();
//				eventManager.stopEvent(A)
			}else if (B.keyCode == EOSKey.DOWN) {//适用于单步
//				if (!D.showStatus)
//					D.show(true);
//				else
//					D.activeNextOption();
//				eventManager.stopEvent(B)
			} else if (E.indexOf("," + B.keyCode + ",") < 0) {
				if(D.beforeFilter && D.beforeFilter(D, B.keyCode)==false){
					return false;
				}
				if(D.showStatus==false){
					if(D.filterModel){
						var iv = D.inputObject.value;
						if(iv && iv.length>=D.filterModel){
							D.show();
						}
					}else{
						D.show();
					}
				}
				if(D.showStatus==true && D.filterModel){
					var iv = D.inputObject.value;
					if(iv && iv.length<D.filterModel){
						D.hide();
					}
				}
				eventManager.stopEvent(B);
				if (D.afterFilter)
					D.afterFilter(D);
//              调用扩展的过滤方法，使用setTimeout异步执行
				window.setTimeoutEosWeb(timeFilter,10,D);
				if (D.allowInput)
					if (D._syncInputValue())
						D._onChange()
			}
			eventManager.stopEvent()
		}
		function A(B) {
			if (D.getDisabled() || D.getReadOnly())
				return;
			if (B.keyCode != EOSKey.ESC)
				return;
			B = B || window.event;
			if (B.keyCode == EOSKey.ENTER) {
				eventManager.stopEvent();
				var A = D.getFilterNum();
				if (A < 1 && D.allowInput)
					if (D._syncInputValue())
						D._onChange()
			} else if (B.keyCode == EOSKey.TAB)
				D.onInputBlur();
			else if (E.indexOf("," + B.keyCode + ",") < 0) {
				if(D.beforeFilter && D.beforeFilter(D, B.keyCode)==false){
					return false;
				}
				A = D.filter();
				eventManager.stopEvent(B);
				if (D.afterFilter)
					D.afterFilter(D);
				D.show()
			}
		}
		function G(B) {
			if (D.getDisabled() || D.getReadOnly())
				return;
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
			if (D.getDisabled() || D.getReadOnly())
				return;
			D.eventObject.src = contextPath
					+ "/common/skins/skin0/images/comboselect/comboSelect_button_over.gif";
			if (D.showStatus)
				D.hide();
			else
				D.show(true)
		}
		if (!this.inputObject.readOnly)
			eventManager.add(this.inputObject, "blur", function(A) {
				D.onInputBlur(A)
			});
		this.container.onmousedown = function() {
			var A = eventManager.getElement();
			if (A == D.inputObject) {
				if (D.showStatus)
					eventManager.stopEvent();
				try {
					D.inputObject.focus()
				} catch (B) {
				}
			} else if (A == D.eventObject)
				if (D.showStatus)
					eventManager.stopEvent()
		};
		eventManager.add(this.inputObject, "keydown", C);
		eventManager.add(this.inputObject, "keyup", H);
		eventManager.add(this.eventObject, "click", function() {
			eventManager.stopEvent()
		});
		eventManager.add(this.eventObject, "mouseup", F);
		eventManager.add(this.inputObject, "focus", function() {
			D.inputObject.select()
		})
	};
	ComboSelect.prototype.loadData = function() {
		if (this.beforeLoadData() === false)
			return;
		if (!this.linkId && !this.queryAction && this.xpath) {
			var xmlZone = document.getElementById(this.id + "_xml");
			this.dataXML = xmlZone ? xmlZone.innerHTML : null
		} else if (this.linkId && this.linkId.indexOf("xml:") == 0) {
			xmlZone = document.getElementById(this.linkId.substring(4));
			this.dataXML = xmlZone ? xmlZone.innerHTML : null
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
			this.setValue("",null,true);
			this.datasetExp.addEntity(emptyEntity)
		}
		if (_dataXML) {
			this.datasetExp.appendDataset(Dataset.create(_dataXML, this.xpath));
			return
		}
		if (this.onLoadData() !== false) {
			if (!this.queryAction)
				return;
			var ajax = new HideSubmit(this.queryAction), param = "";
			if (this.queryParam)
				param += this.queryParam;
			if (this.pageParam)
				param += this.pageParam;
			if (this.initParamFunc) {
				try {
					param += eval(this.initParamFunc + "(this)")
				} catch (e) {
					$handle(e)
				}
			}
			if (param == "")
				param = null;
			if (this.filterID) {
				var form = $id(this.filterID);
				if (form)
					for (var i = 0; i < form.elements.length; i++) {
						var elem = form.elements[i];
						if (elem.name)
							ajax.addParam(elem.name, elem.value)
					}
			}
			if (this.paramList)
				for (i = 0; i < this.paramList.length; i++) {
					elem = this.paramList[i];
					if (elem)
						ajax.addParam(elem.key, elem.value)
				}
			ajax.loadData(param);
			
//          通过返回字段设置valueField、textField、filterField
			this.xmlDom = ajax.retDom;
			if(this.xmlDom){
				var B =this.xmlDom.selectSingleNode("root/data/fieldMessage");
				var fieldMessage = getNodeValue(B);
				if(B && fieldMessage){
					this.fieldMessage = fieldMessage;
					var fms = fieldMessage.split("$");
					if(fms.length>0){
						this.valueField = fms[0];
					}
					if(fms.length>1){
						this.textField = fms[1];
					}
					if(fms.length>2){
						this.filterField = fms[2];
					}
					if (this.nullText !== null) {
						var emptyEntity = new Entity();
						emptyEntity.setProperty(this.valueField, "");
						emptyEntity.setProperty(this.textField, this.nullText);
//						this.setValue("");
						this.datasetExp.values[0] = emptyEntity;
					}
				}
			}
			this.datasetExp.appendDataset(Dataset.create(this.xmlDom, this.xpath, null));
		}
	};
   /**
    * 2009-7-6   LZ   1、下拉框过滤改造2、将activeOptionRow存入inputObject.activeOptionRow
    * @param {} J
    * @param {} E
    * @return {}
    */
   ComboSelect.prototype.doFilter = function(J, E,version) { 
		var C = this.optionsTable.tBodies[0].rows;
		J = J || this.inputObject.value || "";
		J = J.toLowerCase();
		var F = 0, H = null, B = false, pass=false;
		for (var A = 0; A < C.length; A++) {
			if(version && this.version!=version){
				break;
			}
			if(pass){
				C[A].style.display="none";
				continue;
			}
			var I = this.datasetExp.get(A);
			if(this.filterField){
			var ffArra = (this.filterField).split(",");
			var I = this.datasetExp.get(A)
			if(ffArra[temp] && J && I){continue;}
			var DArra = new Array();
			for(var temp = 0;temp<ffArra.length;temp++){
				var tD = I.getProperty(ffArra[temp]);
				if(tD){
					DArra[DArra.length]=tD;
				}
			}
			C[A].style.display = (E == false ? "" : "none");
			if (this.selectOptionRow == C[A])
				B = true;
			// 多个过滤条件
			if (DArra !== undefined && DArra !== null && DArra.length>0) {
				var opType = false;
				for(var i=0;i<DArra.length;i++){
					D = ("" + DArra[i]).toLowerCase();
					var G = D.indexOf(J);
					var swh = true;
					//多个filterField
					if(this.startWith.splice){
						if(this.startWith.lenth<=i){
							swh = this.startWith[this.startWith.lenth-1];
						}else{
							swh = this.startWith[i];
						}
					}else{
						swh = this.startWith;
					}
					if (swh && G == 0 || !swh && G >= 0) {
						opType=true;
					}
				}
				if (opType) {
					C[A].style.display = "";
					if (!H) {
						H = C[A];
						this.activeOption(H)
						this.inputObject.activeOptionRow=H;
					}
					F++;
					if(F>=20){
						pass = true;
					}
				}
			}
			}
		}
		if (B);
		return F;
	};
	//生成新列表
  	ComboSelect.prototype.freshInitFunc = function(hasRefreshSub){
  		if (this.beforeRefresh()===false){
			return;
		}
  		var E = this.datasetExp ? this.datasetExp.getLength() : 0;
  		var K = ["<div class=\"eos-combo-optiondiv-core\"><table  class=\"eos-combo-optiontable\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" ><tbody>"];
		for (var P = 0; P < E; P++) {
			var F = this.datasetExp.get(P), B = F.getProperty(this.valueField), G = F.getProperty(this.textField);
			K.push("<tr __entity_rowno=\"" + P + "\" >");
			K.push("<td><div><nobr>");
			K.push(G == "" ? " " : G);
			K.push("</nobr></div></td>");
			if (this.showValue) {
				K.push("<td><div>");
				K.push(B);
				K.push("</div></td>")
			}
			K.push("</tr>")
		}
		K.push("</tbody></table ></div>");
		var L = window.isIE
				? "<iframe SCROLLING=\"no\" style=\"overflow:hidden;position:absolute;z-index:-1;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft)\" frameborder=\"0\" ></iframe>"
				: "<span style=\"display:none;\"></span>";
		this.optionsDiv.innerHTML = L + K.join("\n");
		this.optionsDivCore = this.optionsDiv.childNodes[1];
		this.optionsTable = this.optionsDivCore.firstChild;
		this.optionsDiv.childNodes[1].style.height = this.optionsDiv.style.height; 
		this.optionsDiv.childNodes[1].style.width = this.optionsDiv.style.width;
		if (!hasRefreshSub)	{
			this.setSubComponent(this.getEntityByValue(this.value));
		}
		this.afterRefresh();
  	}
  	//过滤中转函数
	function timeFilter(D){
	    
		D.version = new Date().getTime();
		var iv = D.inputObject.value;
	
				
	    if(/^(\-?)(\d+)$/.test(iv)) {
	      
	         if(D.id == "fxs"){
    	       D.filterModel = digitalBit;
    	     }else if(D.id=="gys"){
    	       if(iv.length>3){
    	       D.filterModel = phoneBit;
    	       }else{
    	       D.filterModel = normalBit;
    	       }
    	     }else {
    	       if(iv.length<=3){
    	        D.filterModel = xsmxBit;
    	       }else{
    	        if(D.id == "ht"){
    	         D.filterModel = htBit; 
    	        }else{
    	         D.filterModel = phoneBit;
    	         }
    	       }
    	     }
    	}else{
               
    	       D.filterModel = normalBit;
    	      
    	}
    
		if(iv && D.filterModel && iv.length>=D.filterModel){
			//if(iv.indexOf(D.oldIOV)!=0){  相同：0  不同：-1
			  
	  			D.loadData();
				D.freshInitFunc();
			//}
		}
		A = D.filter(D.version);
		return A;
	}
	ComboSelect.prototype.setValue = function(A, E, HT) {
		var D = this.getEntityByValue(A);
		if(HT){
			if (!D)
				return;
		}
		if(A){
			if(D){}else{
				if(this.filterModel){
					this.inputObject.value=A;
					this.hiddenObject.value=A;
					this.initInput=A;
					timeFilter(this,A);
					this.initInput=null;
					D = this.getEntityByValue(A);
				}
			}
		}
	    if (D && A){}else{
	    	//当ComboSelect出现在datacell中时,请将nullText属性去掉
	    	if (this.activeOptionRow){
				removeClass(this.activeOptionRow, "eos-selectoption");
				this.activeOptionRow = null;
	    	}
	        this.selectEntity = new Entity();
//			this.selectOptionRow = null;
	    	if(this.allowInput){
		        this.inputObject.value = A;
		        this.hiddenObject.value = A;
	    	}else{
	    		if(this.nullText && this.filterModel){}else{
	   	            this.inputObject.value = "";
			        this.hiddenObject.value = "";
	    		}
	    	}
	        this.initInput="";
	        return;
	    }
		var C = D.__index, B = this.optionsTable.tBodies[0].rows;
		if (!isNaN(C) && B && B.length > 0)
			this.selectOption(B[C], E)
	};
	ComboSelect.prototype.onInputBlur = function() {
		if(this.beforeInputBlur && this.beforeInputBlur()==false){
			return false;
		}
		var F = this;
		if (checkInput(this.inputObject) == false)
			return;
		var C = true;
		if (this.validate)
			C = this.validate();
		if (C)
			if (!F.showStatus) {
				var B = F.getFilterNum(), G = F.hiddenObject.value, D = F
						.getEntityByRow(F.activeOptionRow), E, A;
				if (D) {
					E = D.getProperty(F.valueField);
					A = D.getProperty(F.textField)
				}
				if (G != E && A == F.inputObject.value)
					F.selectOption();
				else if (F.allowInput)
					;
				else if (!F.allowInput)
					F.selectOption(F.selectOptionRow, (G != E))
			}
	};
}
if(Datacell){
	Datacell.prototype._onKeyDown = function(B) {
		B = B || window.event;
		var D = this.activeCell, A = null, F = B.keyCode;
		var ET = F;
		if(ET==EOSKey.ENTER){
			F = EOSKey.TAB;
		}else if(ET==EOSKey.TAB){
			F = EOSKey.ENTER;
		}
		if (F == EOSKey.ESC) {
			eventManager.stopEvent();
			this.editing = false;
			this.endEdit()
		} else if (F == EOSKey.ENTER) {
			var C = eventManager.getElement();
			if (C != null && C.tagName.toLowerCase() == "textarea")
				return;
			eventManager.stopEvent();
			this.editing ? this.endEdit() : this.startEdit()
		} else if (F == EOSKey.TAB) {
			var edit = this.editing;
			eventManager.stopEvent();
			this.endEdit();
			A = nextElement(D);
			if (!A) {
				A = nextElement(D.parentNode);
				if (A)
					A = A.cells[0]
			}
			this.setActiveCell(B, A);
			if(edit){
				var E = this;
				window.setTimeout(function() {
					E.startEdit()
				}, 10)
			}
		} else if (D && !this.editing) {
			switch (F) {
				case EOSKey.LEFT :
					eventManager.stopEvent();
					A = prevElement(D);
					if (!A) {
						A = prevElement(D.parentNode);
						if (A)
							A = A.cells[A.cells.length - 1]
					}
					break;
				case EOSKey.RIGHT :
					eventManager.stopEvent();
					A = nextElement(D);
					if (!A) {
						A = nextElement(D.parentNode);
						if (A)
							A = A.cells[0]
					}
					break;
				case EOSKey.DOWN :
					eventManager.stopEvent();
					A = nextElement(D.parentNode);
					if (A)
						A = A.cells[D.cellIndex];
					break;
				case EOSKey.UP :
					eventManager.stopEvent();
					A = prevElement(D.parentNode);
					if (A)
						A = A.cells[D.cellIndex];
					break
			}
			if (A) {
				eventManager.stopEvent();
				this.setActiveCell(B, A);
				var cell = this.getActiveCell();
				var row = this.getRowByCell(cell); 
				if(row!=this.getActiveRow()){
					this.selectRow(row)
				}
			}
		}
	};
}
if(Ajax){
	/**
	 * 设置 提交的form
	 * @param {} name form名
	 */
	Ajax.prototype.formToAjax = function(name) {
		var E = "";
		D = $id(name);
		if(D){}else{
			D = $name(name);
		}
		if(D){}else{
			alert(D+" 对象未找到，请联系管理员!");
			return;
		}
		for (i = 0; i < D.elements.length; i++) {
			var B = D.elements[i];
			if (B.type == "radio" || B.type == "checkbox")
				if (!B.checked)
					continue;
			var F = D.elements[i].name || D.elements[i].id, A = getElementValue(D.elements[i]);
			if (F)
				this.addParam(F, A)
		}
	}
}
if(TabPage){
	/**
	* <w:tabPanel>标签面板 修改提交方式，由get改成post。
	* 需在web-common.js引入之后
	*/
	TabPage.prototype.show = function() {
		var A = this.__onSelect();
		if (A === false)
			return false;
		this.container.style.display = "";
		if (!isIE)
			this.containerTable.style.display = "";
		if (this.type == TABPANE_TYPE_IFRAME)
			if (!this.cache) {
				this.container.appendChild(this.group.noCacheIframe);
				ChaGetToPostByForm(this.group.noCacheIframe,this.getUrl());
	//			this.group.noCacheIframe.src = this.getUrl()
			} else if (!this.isLoad) {
				this.reload();
				this.isLoad = true
			}
		this.status = TABPANE_STATUS_ACTIVE;
		this.__titleTD.className = TAB_STYLE_ACTIVE + this.group.styleSuffix;
		this.setTitleTD();
		return true
	}
}