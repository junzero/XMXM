var tempRow = null;
var isnull=false;
Datacell.prototype.beforeSelectRow = function(row) {
	try{
		if(tempRow==null || this.getActiveRow().rowIndex!=row.rowIndex){
			var dcheck = this.DCheck(this.getActiveRow());
			if(!dcheck){
				return false;
			}
		}
		tempRow = this.getActiveRow();
	}catch(e){}

};
Datacell.prototype.afterSelectRow = function(row) {
	try{
		if(isnull){
			isnull=false;
			return;
		}
		if(tempRow!=null && tempRow.rowIndex!=row.rowIndex){
			if(this.dirSubmit()==false){
				return false;
			}
	 	}
	}catch(e){}
};
Datacell.prototype.afterAdd = function(row){
	tempRow=row;
	isnull=false;
}
Datacell.prototype.afterDel = function(row){
	if(this.dirSubmit()==false){
		return false;
	}
	isnull=false;
}
Datacell.prototype.beforeAdd = function(row){
	if(this.dirSubmit()==false){
		return false;
	}
	isnull=true;
}
Datacell.prototype.dirSubmit = function(){
	this.endEdit();
	if(this.isModefied){
		var myAjax = new Ajax(this.submitAction);
		if (this.paramFormId) {
			var E = $id(this.paramFormId);
			if (E)
				for (var B = 0; B < E.elements.length; B++) {
					var C = E.elements[B];
					if (C.name)
						myAjax.addParam(C.name, getElementValue(C))
				}
		}
		if (this.paramList){
			for (B = 0; B < this.paramList.length; B++) {
				C = this.paramList[B];
				if (C)
					myAjax.addParam(C.key, C.value)
			}
		}
		myAjax.loadData(this.datasetExp.getSubmitXML());
		rowEntity = this.getEntity(tempRow);
		rowEntity.status=Entity.STATUS_INIT
		this.isModefied=false;
		this.refreshRow(tempRow);
	}
}
Datacell.prototype.initEvent = function() {
	var A = this;
	function D(B) {
		A.activeMe();
		A.syncScroll();
		if (A.beforeShowEditor !== true)
			A.endEdit()
	}
	eventManager.add(this.bodyDiv, "scroll", D);
	function C(D) {
		A.activeMe();
		A.endEdit();
		var C = getEventTargetByTagName(D, "td"), B = C ? C.parentNode : null;
		if (C && C !== A.containerTD) {
			var sRow = A.selectRow(B);
			if(!sRow){
				return false;
			}
			A.setActiveCell(D, C);
			A.processCheckBox(D, C);
			var E = C.cellIndex, F = B.rowIndex;
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
	} catch (I) {
	}
	function H(D) {
		A.activeMe();
		D = D || window.event;
		var C = D.srcElement || D.target;
		eventManager.stopPropagation();
		if (C && C.tagName.toLowerCase() != "td")
			while ((C = C.parentNode))
				if (C.tagName && C.tagName.toLowerCase() == "td")
					break;
		if (!C.parentNode)
			return;
		var F = C.parentNode.rowIndex, B = C.cellIndex - 1;
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
	if (isIE)
		if (("" + A.width).indexOf("%") > 0 || ("" + A.height).indexOf("%") > 0)
			eventManager.add(window, "resize", B);
	if (isFF)
		if (("" + A.width).indexOf("%") > 0 || ("" + A.height).indexOf("%") > 0)
			if (registerTopContainer(this.datacellContainer))
				EOSResizeObjects.push(this);
	function E() {
		A.clearup()
	}
	eventManager.add(window, "unload", E);
	if (!Datacell.initGlobalEvent.hasInit) {
		Datacell.initGlobalEvent();
		Datacell.initGlobalEvent.hasInit = true
	}
};

Datacell.prototype.DCheck = function(D) {
	var G = this.getEntity(D);
	if(G==null){
		return true;
	}
	if (G.status == Entity.STATUS_NEW || G.status == Entity.STATUS_MODIFIED) {
		this.activeEntity = G;
		for (var C = 0; C < this.fields.length; C++) {
			var H = this.fields[C];
			if (H.editor) {
				var B = G.getProperty(H.fieldName);
				H.editor.setValue(B);
				var F;
				try {
					F = H.editor.validate()
				} catch (K) {
					F = false
				}
				if (!F) {
					this.setActiveCell(null, D.cells[C]);
					this.startEdit();
					try {
						F = H.editor.validate()
					} catch (K) {
						F = false
					}
					this.unlocked();
					return false
				}
			}
		}
	}
	return true;
};