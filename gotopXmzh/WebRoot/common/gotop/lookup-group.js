	function lookUp_selectFunc(row){
		if(row.getStatus()){}else{
			var selObj = row.group.selectBox;
			if(selObj){
				var positionid = row.getParam(row.group.submitParamName);
				var posiname = row.getParam(row.group.displayParamName);
				oOption = document.createElement("OPTION");
				selObj.options.add(oOption);
				oOption.innerText = posiname;
				oOption.value = positionid;
				if(row.group.titleParamName){
					var titlename = row.getParam(row.group.titleParamName);
					oOption.title=titlename;
				}else if(row.group.initTitle){
					oOption.title=positionid;
				}
				if(row.group.hiddenParamName){
					var hiddenValue = row.getParam(row.group.hiddenParamName);
					oOption.hiddenValue=hiddenValue;
				}
				if (row.group.callBackSelectFunc){
					if(row.group.callBackSelectFunc.indexOf(")")<0){
						eval(row.group.callBackSelectFunc + "(oOption,row)");
					}else{
						eval(row.group.callBackSelectFunc);
					}
				}
				oOption.row = row;
			}
		}
	}
	function lookUp_unSelectFunc(row){
		if(row.getStatus()){
			var selObj = row.group.selectBox;
			if(selObj){
				var positionid = row.getParam(row.group.submitParamName);
				for(var i=0;selObj!=null && i<selObj.options.length;i++){
					if((selObj.options[i]).value==positionid){
						selObj.options[i]=null;
						break;
					}
				}
			}
		}
	}
	CheckGroup.prototype.getReturnValue = function(){
		var selObj = this.selectBox;
		var ids=new Array();
		var names=new Array();
		var titles = new Array();
		var hiddens = new Array();
		
		for(var i=0;i<selObj.options.length;i++){
	        ids.push(selObj.options[i].value);
	        names.push(selObj.options[i].innerText);
			titles.push(selObj.options[i].title);
			hiddens.push(selObj.options[i].hiddenValue);
		}
		return [ ids.join(","),names.join(","),titles.join(","),hiddens.join(",")];
	}
	CheckGroup.prototype.initLookUp_CheckGroup = function(){
		this.inited = true;
		if(this.selectBox){}else{
			var swin = window;
			if(this.callType=='parent'){
				swin = window.parent.window;
			}
			this.selectBox = swin.$id(this.selectBoxID);
			if(this.selectBox){}else{
				this.selectBox = swin.$name(this.selectBoxName);
			}
		}
		if(this.selectBox){
			this.selectBox.options.length=0;
		}else{
			alert("未定义已选框,请联系开发人员!");
			return;
		}		
		if(this.callType=='parent'){
			dialogArguments = window.parent.window["dialogArguments"];
		}else{
			dialogArguments = window["dialogArguments"];
		}
		if(dialogArguments && dialogArguments.length>1){}else{
			window["dialogArguments"]=["","",""];
		}
		var submitValue = dialogArguments[0];
		var displayValue = dialogArguments[1];
		var titleValue = "";
		if(dialogArguments.length>2 && dialogArguments[2]){
			titleValue=dialogArguments[2].toString();
		}
		if(submitValue){
			var submitArra = submitValue.split(",");
			var displayArra = displayValue.split(",");
			var titleArra = titleValue.split(",");
			var sRows = this.getRows();
			var rowArg = null;
			if(dialogArguments.length>3 && dialogArguments[3]){
				hiddenValue=dialogArguments[3].toString();
				if(hiddenValue){
					rowArg = hiddenValue.split(",");
				}
			}
			for(var i=0;i<submitArra.length;i++){
				var oOption = document.createElement("OPTION");
				this.selectBox.options.add(oOption);
				oOption.innerText = displayArra[i];
				oOption.value = submitArra[i];
				if(titleArra.length>i){
					oOption.title = titleArra[i];
				}else if(row.group.initTitle){
					oOption.title = submitArra[i];
				}
				var checked = false;
				for(var j=0;j<sRows.length;j++){
					var row = sRows[j];
					if(!row.getStatus()){
						var positionid = row.getParam(this.submitParamName);
						if(positionid==submitArra[i]){
						    row.checkbox.checked = true;
		    				row.value = true;
							oOption.row = row;
							checked = true;
							break;
						}
					}
				}
				if(rowArg && rowArg.length>i){
					oOption.hiddenValue = rowArg[i];
				}
			}
		}
		cgObj = this;
		window.onunload = function(){
			var arguments = cgObj.getSelBoxResult();
			if(arguments.length>0){
				dialogArguments[0]=arguments[0];
			}
			if(arguments.length>1){
				dialogArguments[1]=arguments[1];
			}
			if(arguments.length>2){
				dialogArguments[2]=arguments[2];
			}
			if(arguments.length>3){
				dialogArguments[3]=arguments[3];
			}
			if(cgObj.callType=='parent'){
				window.parent.window["dialogArguments"] = dialogArguments;
			}else{
				window["dialogArguments"] = dialogArguments;
			}
		}
	}
	CheckGroup.prototype.initLookUp_Datacell = function(){
		if(this.inited!=true){
			this.initLookUp_CheckGroup();
			return;
		}	
		var arguments = this.getSelBoxResult();
		var submitValue = null;
		if(arguments.length>0){
			submitValue=arguments[0];
		}
		if(submitValue){
			var submitArra = submitValue.split(",");
			var sRows = this.getRows();
			for(var i=0;i<submitArra.length;i++){
				for(var j=0;j<sRows.length;j++){
					var row = sRows[j];
					if(!row.getStatus()){
						var positionid = row.getParam(this.submitParamName);
						if(positionid==submitArra[i]){
						    row.checkbox.checked = true;
		    				row.value = true;
							break;
						}
					}
				}
			}
		}
	}
	CheckGroup.prototype.getSelBoxResult =function(){
		var selObj = this.selectBox;
		var value = [];
		var text = [];
		var title = [];
		var hidden = [];
		for(var i=0;i<selObj.options.length;i++){
			var option = selObj.options[i];
			value.push(option.value);
			text.push(option.innerText);
			title.push(option.title);
			hidden.push(option.hiddenValue);
		}
		return [value.toString(),text.toString(),title.toString(),hidden.toString()];
	}
	CheckGroup.prototype.deleteOption = function(selObj){
		var index = selObj.options.selectedIndex;
		if(index>=0){
			var row = selObj.options[index].row;
			if(row){
				selObj.options[index].row.reverseSelect();
			}else{
				selObj.options[index]=null;			
			}
		}
	}
	CheckGroup.prototype.batchDeleteOption = function(selObj){
		if(selObj){}else{
			selObj = this.selectBox;
		}
		if(selObj){
			this.selectedDeleteOption(selObj);
		}
	}
	CheckGroup.prototype.selectedDeleteOption = function(selObj){
		for (var i = selObj.options.length-1; i >= 0; i--) {
			if(selObj.options[i].selected){
				var row = selObj.options[i].row;
				if(row){
					selObj.options[i].row.reverseSelect();
				}else{
					selObj.options[i]=null;
				}
			} 
		}
	}
	CheckGroup.prototype.allDeleteOption = function(selObj){
		if(selObj){}else{
			selObj = this.selectBox;
		}
		if(selObj){
			this.deleteOptionOfAll(selObj);
		}
	}
	CheckGroup.prototype.deleteOptionOfAll = function(selObj){
		for (var i = selObj.options.length-1; i >= 0; i--) {
			var row = selObj.options[i].row;
			if(row){
				selObj.options[i].row.reverseSelect();
			}else{
				selObj.options[i]=null;
			}
		}
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
	    this.group = B;
	};