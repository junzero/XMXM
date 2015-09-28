/**
* datacell使其支持复选框功能,在页面上至少得先定义第几列为checkbox复选框
* 如var checkgroup = new CheckGroup('group1');
* checkgroup.cellnum = 0;  //指定那一个列为复选框,从0开始
**/
Datacell.prototype.onComplete = function() {
	var groupidvar = this.id+"_group";
	var checkgroup = $id(groupidvar);
	if(checkgroup){}else{return;}
	
	var cellnum = checkgroup.cellnum;
	if(cellnum>-1 && isFinite(cellnum)){
	
	}else{
		alert('请指定那一列成为checkbox');return;
	}
	this.setAllowModify(cellnum,false);
	var field=this.getField(cellnum);
	field.labelSpan.parentNode.style.padding = "2px 2px 2px 3px;";
	var title = '<INPUT id=checkSelect_'+groupidvar+' onclick=checkSelectAll(\"'+groupidvar+'\",this) type=checkbox name=checkSelect>';
	this.setTitle(field,title);
	if(field.align){
		field.labelSpan.parentNode.style.textAlign=field.align;
	}
	if(field.editor && field.editor.parentNode){
		field.editor.parentNode.innerHTML="";
	}
	field.sortAt = 'none';
	checkgroup.field = field;
	checkgroup.getRows().length=0;
	for(var i=0;i<this.getPageRowCount();i++){
		cRowCheckBox(this,i,cellnum,checkgroup,field);
	}
	this.checkgroup=checkgroup;
	checkgroup.datacell = this;
};
function cRowCheckBox(dataObj,rowIndex,cellIndex,checkgroup,field){
	if(field){
		field = checkgroup.field;
	}
	var cell = dataObj.getCell(rowIndex,cellIndex);
	cell.setAttribute("fieldId",Datacell.__INDEX_NAME);
	cell.firstChild.id = checkgroup.id+"_"+rowIndex+"_container";
	var value = cell.firstChild.innerText;
	cell.firstChild.innerHTML="";
	var eos_rowbox = new rowCheckBox(checkgroup.id+"_"+rowIndex);
	eos_rowbox.entity = dataObj.getEntityByCell(cell);
	var row_d = dataObj.getRowByCell(cell);
	row_d.rowbox = eos_rowbox;
	eos_rowbox.row_d = row_d;
	eos_rowbox.cell=cell;
	with(eos_rowbox){
		params = [];
		selectStyle = "eos-table-selectrow";
		isChecked = false;
		groupid = checkgroup.id;
		rowEvent = "click";
		showCheckBox = true;
		tagName = "tr";
		inited = true;
		afterSelectFunc = checkgroup.afterSelectFunc;
		beforeSelectFunc = checkgroup.beforeSelectFunc;
		afterUnSelectFunc = checkgroup.afterUnSelectFunc;
		beforeUnSelectFunc = checkgroup.beforeUnSelectFunc;
		onSelectFunc = checkgroup.onSelectFunc;
		onUnSelectFunc = checkgroup.onUnSelectFunc;
		rowSelect=false;
		init();
	}
	eos_rowbox.checkgroup=checkgroup;
	eos_rowbox.checkbox.value=value;
	cell.defaultValue=cell.firstChild.innerHTML;
	return eos_rowbox;
}
/**
*  增加如果为check列,就不进行过滤
**/
Datacell.prototype.createCellValue = function(cell, field) {
	var checkgroup = this.checkgroup;
	if(checkgroup){
		if (!cell)
			return;
		var row = cell.parentNode, entity = this.getEntity(row), key = cell.getAttribute("fieldId");
		if (key == Datacell.__INDEX_NAME)
			return;
		var field = this.keys[key], cellText = entity.getProperty(field.fieldName);
		if(checkgroup.field==field){
			return cell.firstChild.innerHTML;
		}
		if (field.onRefreshFunc) {
			if (typeof(field.onRefreshFunc) == "string")
				field.onRefreshFunc = eval(field.onRefreshFunc);
			cellText = field.onRefreshFunc(cellText, entity,
					cell.parentNode.rowIndex, cell.cellIndex, this)
		} else if (field.expression)
			cellText = runExpression(field.template, entity);
		else if (field.editor)
			cellText = htmlConversion(field.editor.getDisplayValue(cellText));
		else
			cellText = htmlConversion(cellText === undefined || cellText === null
					? field.nullText
					: cellText);
		cellText = cellText === undefined || cellText === null
				? htmlConversion(field.nullText)
				: cellText;
		return cellText;
	}else{
		if (!cell)
			return;
		var row = cell.parentNode, entity = this.getEntity(row), key = cell.getAttribute("fieldId");
		if (key == Datacell.__INDEX_NAME)
			return;
		var field = this.keys[key], cellText = entity.getProperty(field.fieldName);
		if (field.onRefreshFunc) {
			if (typeof(field.onRefreshFunc) == "string")
				field.onRefreshFunc = eval(field.onRefreshFunc);
			cellText = field.onRefreshFunc(cellText, entity,
					cell.parentNode.rowIndex, cell.cellIndex, this)
		} else if (field.expression)
			cellText = runExpression(field.template, entity);
		else if (field.editor)
			cellText = htmlConversion(field.editor.getDisplayValue(cellText));
		else
			cellText = htmlConversion(cellText === undefined || cellText === null
					? field.nullText
					: cellText);
		cellText = cellText === undefined || cellText === null
				? htmlConversion(field.nullText)
				: cellText;
		return cellText
	}
};
/**
* 取值过滤,移除字段/之前的值
**/
rowCheckBox.prototype.getParam = function(C) {
	if(this.checkgroup){
		if(C){
			C = C.substring(C.indexOf("/")+1);
			return this.entity.getProperty(C);
		}else{
			alert('查询字段不能为空');
			return null;
		}
	}else{
	    for (var A = 0; A < this.subboxes.length; A++) {
	        var B = this.subboxes[A];
	        if (B.name.replace(/\[\d*\]/, "[*]") == C || B.name.replace(/\[\d*\]/, "") == C) return B.value
	    }
	    return null
	}
};
/**
* 移除eval报错
**/
rowCheckBox.prototype.setSelected = function() {
	if(this.checkgroup){
		if (this.beforeSelectFunc && this.inited)
			if (eval(this.beforeSelectFunc) === false)
				return;
		this.checkbox.checked = true;
		this.value = true;
		this.changeStatus();
		if (this.onSelectFunc && this.inited)
			if (typeof(this.onSelectFunc) == "string")
				eval(this.onSelectFunc);
			else
				this.onSelectFunc(this);
		if (this.afterSelectFunc && this.inited){
			eval(this.afterSelectFunc)
		}
	}else{
	    if (this.beforeSelectFunc && this.inited) if (eval(this.beforeSelectFunc + "(this)") === false) return;
	    this.checkbox.checked = true;
	    this.value = true;
	    this.changeStatus();
	    if (this.onSelectFunc && this.inited) if (typeof(this.onSelectFunc) == "string") eval(this.onSelectFunc + "(this)");
	    else this.onSelectFunc(this);
	    if (this.afterSelectFunc && this.inited) eval(this.afterSelectFunc + "(this)")
	}

};
/**
* 移除eval报错
**/
rowCheckBox.prototype.disSelected = function() {
	if(this.checkgroup){
		if (this.beforeUnSelectFunc && this.inited)
			if (eval(this.beforeUnSelectFunc) === false)
				return;
		if (this.onUnSelectFunc && this.inited)
			if (typeof(this.onUnSelectFunc) == "string")
				eval(this.onUnSelectFunc);
			else
				this.onUnSelectFunc(this);
		this.checkbox.removeAttribute("checked");
		this.value = false;
		this.changeStatus();
		if (this.afterUnSelectFunc && this.inited)
			eval(this.afterUnSelectFunc)
	}else{
	    if (this.beforeUnSelectFunc && this.inited) if (eval(this.beforeUnSelectFunc + "(this)") === false) return;
	    if (this.onUnSelectFunc && this.inited) if (typeof(this.onUnSelectFunc) == "string") eval(this.onUnSelectFunc + "(this)");
	    else this.onUnSelectFunc(this);
	    this.checkbox.removeAttribute("checked");
	    this.value = false;
	    this.changeStatus();
	    if (this.afterUnSelectFunc && this.inited) eval(this.afterUnSelectFunc + "(this)")
	}

};
/* 
*  实现全选复选框
*/
function checkSelectAll(cgroupid,checksobj){
	if(checksobj){}else{
		checksobj = $id('checkSelect_'+cgroupid);
		if(checksobj){}else{
			checksobj = $name('checkSelect');
		}
	}
	if (checksobj.checked){
		selectAll(cgroupid);
	}else{
		selectNone(cgroupid);
	}
}
/**
* 取值过滤,移除字段/之前的值
**/
CheckGroup.prototype.getSelectParams = function(C) {
	if(this.datacell){
		if(C){
			C = C.substring(C.indexOf("/")+1);
			CA = [];
			var srs = this.getSelectRows();
			for(var i=0;i<srs.length;i++){
				var prop = srs[i].entity.getProperty(C);
				CA.push(prop)
			}
			return CA;
		}else{
			alert('查询字段不能为空');
			return null;
		}
	}else{
	    var E = this.getSelectRows(),
	    C = [];
	    for (var A = 0; A < E.length; A++) {
	        var B = E[A].getParam(D);
	        C.push(B)
	    }
	    return C
	}
};
/**
* 删除改造
**/
Datacell.prototype.deleteRowExt = function(A) {
	if(this.checkgroup){}else{return;}
	var checkgroup = this.checkgroup;
	if(checkgroup.getSelectLength() < 1 ) {
	     alert("至少选择一行！");
	     return;
	}
	var srs = checkgroup.getSelectRows();
	var rows = checkgroup.getRows();
	var dRow = 0;
	for(var i=srs.length-1;i>=0;i--){
		var row = this.getRowByCell(srs[i].cell);
		if(this.deleteRow(row,true)==false){
			continue;
		}
		dRow++;
		$remove(rows,srs[i]);//删除后清空
		$remove(srs,srs[i]);//删除后清空
	}
	if(dRow>0){
		for(var i=0;i<rows.length;i++){
			rows[i].id=this.id+ "_group_"+i;
			rows[i].cell.firstChild.id = rows[i].id+"_container";
		}
		if (checkgroup.afterUnSelectFunc && this.inited){
			eval(checkgroup.afterUnSelectFunc + "(this)")
		}
		if (checkgroup.beforeUnSelectFunc && this.inited){
			eval(checkgroup.beforeUnSelectFunc + "(this)")
		}
		this.afterDelMess(A);
	}
}
/**
* 清空事件
**/
function CleanButtonEvent(obj){
	if(_registryEvent){
		var status = false;
		for (var i = 0; i < _registryEvent.length; i++){
			if(_registryEvent[i].obj==obj){
			    eventManager.remove(_registryEvent[i].obj,_registryEvent[i].type,_registryEvent[i].fn);
				_registryEvent[i].obj = null;
		        _registryEvent[i].fn = null;
				status = true;
			}
		}
	}
};
Datacell.prototype.deleteRow = function(A,isAll) {
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
        this.afterDel(A);
        if(isAll!=true){
        	this.afterDelMess(A);
        }
    }else{
    	return false;
    }
    if(this.checkgroup && this.checkgroup.getRows() && A && A.rowbox){
	    $remove(this.checkgroup.getRows(),A.rowbox);
    }
};
/**
* 替换删除事件
**/
Datacell.prototype.afterInit = function() {
	var groupidvar = this.id+"_group";
	var checkgroup = $id(groupidvar);
	if(checkgroup){}else{return;}
	var M = this;
	function V() {
		M.endEdit();
		M.deleteRowExt();
	}
	for(var i=0;i<this.__pagePilot.length;i++){
		var pilot = this.__pagePilot[i];
		CleanButtonEvent(pilot.deleteButton);
		eventManager.add(pilot.deleteButton, "click", V);
	}
	if(this.afterInitCallBack){
		this.afterInitCallBack();
	}
}
/**
* 增加方法中加入复框
**/
Datacell.prototype.afterAdd_Ext = function(row){
	if(this.checkgroup){}else{return;}
	var cellnum = this.checkgroup.cellnum;
	if(cellnum<0 || !isFinite(cellnum)){
		return;
	}
	var eos_rowbox = cRowCheckBox(this,row.rowIndex,cellnum,this.checkgroup);
}
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
    for (var B = 0; B < E.cells.length; B++){
    	var ecb = E.cells[B];
    	ecb.setAttribute("fieldId", this.fields[B].fieldId);
    	var K = this.fields[B];
    	if(K.width<1){
			ecb.style.display="none";
		}
    }
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
    this.afterAdd_Ext(E);
    this.afterAdd(E);
    return E
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
/**
* 使用datacell checkbox处于单选模式
**/
function _lookUp_selectRadioFunc(cgp){
	var csRows = cgp.getSelectRows();
	for(var i=0;i<csRows.length;i++){
		var row = csRows[i];
		row.reverseSelect();
	}
}