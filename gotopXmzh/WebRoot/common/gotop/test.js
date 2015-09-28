
	/**
	*	通过实体,得到Url
	*   entity实体
	*   EName变量的前缀,如果为空并且OpType!=1则取默认的实体名
	*   filterNull是否过滤空对象
	*   OpType:1不进行变量的前缀
	*   isNotEncodeURI:是不否进行URL编码
	**/
	function getUrlByEntity(entity,EName,filterNull,OpType,isNotEncodeURI){
		if(EName){
			EName += "/";
		}else{
	  		EName = entity.name+"/";
		}
		if(OpType==1){
			EName="";
		}
	 	var url = "";
	 	if(filterNull){}else{filterNull=false;};
		var keys = entity.getKeys();
		for(var i=0;i<keys.length;i++){
			var key = keys[i];
			var value = entity.getProperty(key);//setProperty
	
			if((value=="null" || isBlank(value)) && filterNull){
				continue;
			}else{
				url += "&"+EName+key+"=";
				if(isNotEncodeURI){
					url += value;
				}else{
					url += encodeURI(value);
				}
			}
	
		}
		if(url.indexOf("&")==0){
			url = url.substring(1);
		}
		return url;
	}
	/**
	*	radioName:radio名称
	*   cheValue:欲设置的值
	*/
	function setRadioValue(radioName,cheValue){
		var radioObj;
		if(typeof(radioName)=='string'){
			radioObj = document.getElementsByName(radioName);
		}else{
			radioObj = document.getElementsByName(radioName.name);
		}
		for(var i=0;i<radioObj.length;i++){
			if(radioObj[i].value==cheValue){
				radioObj[i].checked="checked";
				return true;
			}else{
				radioObj[i].checked="";
			}
		}
		return false;
	}
	/**
	*	radioName:radio名称
	*   cheValue:欲设置的值
	*/
	function getRadioValue(radioName){
		var radioObj;
		if(typeof(radioName)=='string'){
			radioObj = document.getElementsByName(radioName);
		}else{
			radioObj = document.getElementsByName(radioName.name);
		}
		for(var i=0;i<radioObj.length;i++){
			if(radioObj[i].checked){
				return radioObj[i].value;
			}
		}
		return false;
	}

  /* 隐藏函数*/  
    function clickDiv(id1,id2,flag){
       var div1 = document.getElementById(id1);
       var div2 = document.getElementById(id2);
  
         if(flag == 0){
    
           div1.style.display = "none";
           div2.style.display = "none";
        }else if(flag == 1){
     
            div1.style.display = "block";
            div2.style.display = "block";
        }else{
      //none;
         }
       }
    /**
    * 隐藏函数,批量设置对象的display属性
    * flag 欲设置的属性 如:none、block
    * args 欲设置的对象,可以无限加
    **/
	function showOrHideByID(flag){
		var args = Array.prototype.slice.call(arguments,1);//截取从第三个参数开始的所有参数
		for(var i=0;i<args.length;i++){
			if(args[i]){
				if(typeof(args[i])=="string"){
					var obj = document.getElementById(args[i]);
					if(obj){
						obj = document.getElementsByName(args[i]);
						if(obj){
							obj = obj[0];
						}
					}
				}else{
					obj = args[i];
				}
				obj.style.display=flag;
			}
		}
	}
       
/*
*	将html转化为可提交文本
*/
	function HtmlEscape(A) {
		if (typeof(A) == "string"){
			return A.replace(/\t/g,"&nbsp;&nbsp;&nbsp;&nbsp;")
					.replace(/\s\n/g, "%0D%3Cbr%3E")
					.replace(/</g, "&lt;")
					.replace(/>/g, "&gt;")
					.replace(/\"/g,"&quot;")
					.replace(/\'/g,"&#39;")
					.replace(/\s/g,"&nbsp;")
					.replace(/&/g,"&amp;");
		}else{
			return A
		}
	}
    /*
    * 刷新comboSelect,参数为comboSelect对象或name/ID
    */
    function refreshComBS(name){
    	if(name){}else{
    		alert("对象没有找到,请联系开发人员!");
    		return false;
    	}
    	var cbs = name;
    	if(typeof(cbs) == "string"){
    		cbs = $id(name) || $name(name);
    	}
    	cbs.init();
		cbs.loadData();
		cbs.refresh();
		return true;
    }
    /*
    * 使页面上的对象Enter执行TAB,参数为范围对象的name/ID
    */
    function tabEnter(name){
    	var ebnT = null;
		if(name && typeof(name) == "string"){
			nameObj = $id(name) || $name(name);
			ebnT = nameObj.getElementsByTagName("input");
		}else{
	    	ebnT = document.getElementsByTagName("input");
		}
    	for(var i=0;i<ebnT.length;i++){
    		if(ebnT[i].type!='text'){
    			continue;
    		}
    		ebnT[i].oldOnkeydown = ebnT[i].onkeydown;
    		ebnT[i].onkeydown=function(){
    			if(this.oldOnkeydown){
    				var okd = this.oldOnkeydown();
    				if(okd==false){
    					return false;
    				}
    			}
    			if(event.keyCode==13){
	    				event.keyCode=9;
    			}
    		}
    	}
    	return true;
    }
	if(window.oldSetTimeout){}else{
		window.oldSetTimeout = window.setTimeout;
	}
	var psto = window.setTimeout;
    /*
    * 重写系统的setTimeOut使其可接受参数。
    */
	window.setTimeout = function(fun,tCount){
		if(typeof fun =='function'){
			var args = Array.prototype.slice.call(arguments,2);//截取从第三个参数开始的所有参数
			var funPushP = function(){
				return fun.apply(null,args);//把获取的参数传递给 传入的函数句柄      
			};
			return psto(funPushP,tCount);
		}
		return psto(fun,tCount);
	}

    /*
    * 使页面上的对象Enter执行TAB,参数为范围对象的name/ID
    */
    function tabEnterjs(name){
    	var ebnT = null;
		if(name && typeof(name) == "string"){
			nameObj = $id(name) || $name(name);
			ebnT = nameObj.getElementsByTagName("input");
		}else{
	    	ebnT = document.getElementsByTagName("input");
		}

    	for(var i=0;i<ebnT.length;i++){
    		ebnT[i].oldTabIndex = ebnT[i].tabIndex;
    		if(ebnT[i].disabled || ebnT[i].readOnly){
		    	ebnT[i].tabIndex=-1;
    		}
    		if(ebnT[i].type=='text' || ebnT[i].type=='password'){
	    		ebnT[i].oldOnkeydown = ebnT[i].onkeydown;
	    		ebnT[i].onkeydown=function(){
	    			if(this.oldOnkeydown){
	    				var okd = this.oldOnkeydown();
	    				if(okd==false){
	    					return false;
	    				}
	    			}
	    			var event = eventManager.getEvent();
	    			if(this.className && (this.className.indexOf('eos-combo-select-editor-text')>-1) && this.id){
	    				var lio = this.id.lastIndexOf("_");
	    				var comboScelet = $o(this.id.substr(0,lio));
	    				if(comboScelet.showStatus){
	    					return true;
	    				}else if(event.keyCode==13){
	    					comboScelet.hide();
			    			keyCode9(this);
	    				}
	    			}else if(event.keyCode==13){
	    				keyCode9(this);
	    			}
		    		replaceKeyCode37(this);
	    		}
	    		replaceOnFocus(ebnT[i]);
				replaceOnblur(ebnT[i]);
				replaceOnchange(ebnT[i]);
    		}
    		if(ebnT[i].type=='radio'){
    		
				ebnT[i].onkeydown=function(){
					if((event.keyCode>=48 && event.keyCode<=57) || (event.keyCode>=65 && event.keyCode<=90) || (event.keyCode>=96 && event.keyCode<=105)){
						var yjxx_vBmjb = document.getElementsByName(this.name);
						var keyvalue = String.fromCharCode(event.keyCode);
						if(event.keyCode>=96 && event.keyCode<=105){
							keyvalue = event.keyCode-96;
						}
						for(var j=0;j<yjxx_vBmjb.length;j++){
							var yv = yjxx_vBmjb[j];
							if(yv.value){
								var startStr = yv.value.substr(0,1);
								if(startStr==keyvalue){
									yv.checked="checked";
									break;
								}
							}
						}
					}else if(event.keyCode==13){
						keyCode9(this);
	    			}
					replaceKeyCode37(this);
				}
				replaceOnFocus(ebnT[i]);
				replaceOnblur(ebnT[i]);
				replaceOnchange(ebnT[i]);
    		}
    		if(ebnT[i].type=='checkbox'){
	    		ebnT[i].oldOnkeydown = ebnT[i].onkeydown;
	    		ebnT[i].onkeydown=function(){
	    			if(this.oldOnkeydown){
	    				var okd = this.oldOnkeydown();
	    				if(okd==false){
	    					return false;
	    				}
	    			}
	    			
	    			if(event.keyCode==13 && event.ctrlKey){
		    			this.checked="checked";
		    			return;
	    			}
	    			
	    			if(event.keyCode==13){
						keyCode9(this);
	    			}
		    		replaceKeyCode37(this);
	    		}
	    		replaceOnFocus(ebnT[i]);
	    		replaceOnblur(ebnT[i]);
				replaceOnchange(ebnT[i]);
    		}
    		if(ebnT[i].type=='button' || ebnT[i].type=='submit'){
				ebnT[i].oldOnclick = ebnT[i].onclick;
				ebnT[i].onclick=function(){
					var okd;
					this.oldDisabled = this.disabled;
					this.disabled = true;
					try{
		    			if(this.oldOnclick){
		    				okd = this.oldOnclick();
		    			}
					}catch(e){
						alert(e.name + ": " + e.message + "异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
						throw e;
					}
					this.disabled = this.oldDisabled;
					return okd;
				}
    		}
    	}
    	
    	
		var ebnT = document.getElementsByTagName("select");
		for(var i=0;i<ebnT.length;i++){
			ebnT[i].onkeydown=function(){
				if((event.keyCode>=48 && event.keyCode<=57) || (event.keyCode>=65 && event.keyCode<=90) || (event.keyCode>=96 && event.keyCode<=105)){
					var keyvalue = String.fromCharCode(event.keyCode);
					if(event.keyCode>=96 && event.keyCode<=105){
						keyvalue = event.keyCode-96;
					}
					for(var i=0;i<this.options.length;i++){
						var option = this.options[i];
						if(option.value){
							var startStr = option.value.substr(0,1);
							if(startStr==keyvalue){
								this.value = option.value;
							}
						}
					}
				}else if(event.keyCode==13){
					keyCode9(this);
	    		}
				replaceKeyCode37(this);
			}
			replaceOnFocus(ebnT[i]);
    		replaceOnblur(ebnT[i]);
			replaceOnchange(ebnT[i]);
		}
    	return true;
    }
    //移去焦点时
    function replaceOnblur(obj){
		obj.oldOnblur = obj.onblur;
		obj.onblur=function(){
			if(this.keyOnblurTpye==false){
				this.keyOnblurTpye=null;
				return;
			}
			if(this.keyOnblurTpye==true){
    			this.keyOnblurTpye=false;
			}
			var okd = null;
			try{
   				this.style.backgroundColor='';
    			if(this.oldOnblur){
    				okd = this.oldOnblur();
    			}
			}catch(e){
				alert(e.name + ": " + e.message + "异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
				throw e;
			}
			if(okd!=null){
				return okd;
			}
		}
    }
    //获得焦点时
    function replaceOnFocus(obj){
		obj.oldOnfocus = obj.onfocus;
		var okd;
		obj.onfocus=function(){
			this.state="start";
			if(this.oldOnfocus){
				okd = this.oldOnfocus();
   				if(okd==false){
   					return false;
   				}
			}
			try{
				this.style.backgroundColor="#FFFFFF";
    			this.select();
			}catch(e){
				
			}
		}
    }
    /**
    *	替换onChange事件
    */
    function replaceOnchange(obj){
		obj.oldOnchange = obj.onchange;
		obj.onchange=function(){
			if(this.keyOnchangeTpye==false){
				this.keyOnchangeTpye=null;
				return;
			}
			if(this.keyOnchangeTpye==true){
    			this.keyOnchangeTpye=false;
			}
			var okd = null;
			try{
    			if(this.oldOnchange){
    				okd = this.oldOnchange();
    			}
			}catch(e){
				alert(e.name + ": " + e.message + "异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
				throw e;
			}
			if(okd!=null){
				return okd;
			}
		}
    }
	/**
	*  制作快捷键Shift + <-
	*/
	function replaceKeyCode37(obj){
		obj.keyOnchangeTpye=false;
		obj.keyOnblurTpye=false;
		var event = eventManager.getEvent();
	    if(event!=null && (event.shiftKey)&&(event.keyCode==37)){
	    	event.keyCode=9;
	    }
	    obj.style.backgroundColor="";
	}

	function keyCode9(obj){
		if(obj.state=="end"){
			return;
		}
		obj.keyOnchangeTpye=true;
		if(obj.onchange!=null && obj.onchange()==false){
			return false;
		}
		obj.keyOnblurTpye=true;
		if((obj.onblur!=null && obj.onblur()==false)){
			return false;
		};
		if (checkInput(obj) != false) {
			var event = eventManager.getEvent();
			event.keyCode=9;
			obj.state="end";
		}
	}
	
	/**
	*  插入合计项  tableName表ID
	*/
	  function cumTable(tableName){
	  	var table=$id(tableName)||$name(tableName);
	  	var thArra = table.getElementsByTagName("th");
	  	var trIndex=table.rows.length-1;
	  	for(;trIndex>=0;trIndex--){
	  		var trObj = table.rows[trIndex];
  			var ebnT = trObj.getElementsByTagName("input");
  			var i=0;
  			for(;i<ebnT.length;i++){
	    		if(ebnT[i].type=='button'){
	    			break;
	    		}
	    	}
	    	if(i==ebnT.length || ebnT.length==0){
	    		break;
	    	}
	  	}
	  	
	  	trIndex += 1;
	  	var tr = null;
	  	if(trIndex<0){
	  		return false;
	  	}else{
	  		tr = table.insertRow(trIndex);
	  	}
		var colspanIndex = 0;
		var colsnum = 0;
		var defnum=0;
	  	if(thArra.length>0){
	  		var cells = thArra[0].parentNode.cells;
	  		var thO = thArra[0];
	  		for(var i=thO.cellIndex-1;i>=0;i--){ 
	  			var tempTd = cells[i];
	  			if(tempTd.colSpan){
	  				colspanIndex += tempTd.colSpan;
	  			}else{
	  				colspanIndex += 1;
	  			}
  				defnum += 1;
	  		}
	  		colsnum = 0;
	  		for(var i=cells.length-1;i>=0;i--){
	  			var tempTd = cells[i];
	  			if(tempTd.colSpan){
	  				colsnum += tempTd.colSpan;
	  			}else{
	  				colsnum += 1;
	  			}
	  		}
	  	}
	  	for(var i=0;i<colspanIndex;i++){
	  		var td = document.createElement("td");
	  		tr.appendChild(td);
	  	}
	  	for(var i=0;i<thArra.length;i++){
      		
      		var td = document.createElement("td");
  			tr.appendChild(td);
	  	}
      	for(var i=0;i<thArra.length;i++){
  			var th = thArra[i];
  			td = tr.cells[i];
      		if(th.tableType=='sum'){
	      		var sum = 0;
	      		try{
		      		for(var j=1;j<table.rows.length-1;j++){
		      			var cel=table.rows[j].cells[th.cellIndex+(colspanIndex-defnum)-(colsnum-table.rows[j].cells.length)];//(colspanIndex-defnum)-(colsnum-table.rows[j].cells.length)  行差值还要再加上起始差值
		      			if(cel && cel.innerText){}else{
							continue;	      				
		      			}
		      			var value = cel.innerText;
		      			value = value.replaceAll(",","").trim();
  						if(isDecimal(value)){
  							if(value.indexOf(".")<0){
								sum += parseInt(value,10);			
  							}else{
				      			sum += parseFloat(value,10);
  							}
  						}else{
// 							alert("非数值");
  						}
		      		}
		      		if(th.formatNumberStr){
		      			sum = formatNumber(sum,th.formatNumberStr);
		      		}else{
			      		if(isDecimal(""+sum) && !isInteger(sum)){
			      			sum = FormatNumberWS(sum,2);
			      		}
		      		}
	      		}catch(e){throw e}
				td.innerHTML=sum;
      		}else{
      			if(th.tableType){
      				td.innerHTML=th.tableType;
      			}else{
      				td.innerHTML="&nbsp;";
      			}
      		}
    		try{
      			var rowIndex = trIndex-1;
      			if(rowIndex>0){
		      		var cel = table.rows[rowIndex].cells[th.cellIndex];
		      		if(cel.align){
		      			td.align = cel.align;
		      		}
      			}
	      	}catch(e){}
      	}
//		table.appendChild(tr);
	}
	/**
	*  在右列插入合计项  tableName表ID
	*  如果你不想强制加上一个th表头，我会自动加上一列。自动加上去的一列，为可选格式后三列如下：
	*  cumTableRow("tableID",'###,###,##0.0','center','合计',"sum");
	*/
	function cumTableRow(tableName,formatNumberStr,align,title,tableType){
	  	var table=$id(tableName)||$name(tableName);
	  	var trInput=table.rows.length-1;
	  	for(;trInput>=0;trInput--){
	  		var trObj = table.rows[trInput];
  			var ebnT = trObj.getElementsByTagName("input");
  			var i=0;
  			for(;i<ebnT.length;i++){
	    		if(ebnT[i].type=='button'){
	    			trInput = trObj.rowIndex;
	    			break;
	    		}
	    	}
	    	if(i==ebnT.length || ebnT.length==0){
	    		break;
	    	}
	  	}
	  	
	  	var thArra = table.getElementsByTagName("th");
	  	var newTh = null;
	  	var trIndex=null;
	  	var deftype =0;
	  	if(thArra.length<1){
	  		return null;
	  	}else{
	  		for(var i=0;i<thArra.length;i++){
	  			if(thArra[i].tableRowType=='result'){
	  				newTh=thArra[i];
	  				break;
	  			}
	  		}
	  		if(newTh){}else{
		  		var thRow = thArra[0].parentNode;
		  		deftype=1;
		  		newTh = document.createElement("th");
		  		if(title){}else{
		  			title = "小计";
		  		}
		  		if(formatNumberStr){
		  			newTh.formatNumberStr=formatNumberStr;
		  		}
		  		if(align){
		  			newTh.align = align;
		  		}
		  		if(tableType){
		  			newTh.tableType = tableType;
		  		}else{
		  			newTh.tableType = 'sum';
		  		}
		  		newTh.innerText=title;
		  		newTh.tableRowType=='result'
		  		thRow.appendChild(newTh);
	  		}
	  		trIndex = newTh.parentNode.rowIndex;
	  	}
		var colspanIndex = 0;
		var colsnum = 0;
		var defnum=0;
	  	if(thArra.length>0){
	  		var cells = thArra[0].parentNode.cells;
	  		var thO = thArra[0];
	  		for(var i=thO.cellIndex-1;i>=0;i--){ 
	  			var tempTd = cells[i];
	  			if(tempTd.colSpan){
	  				colspanIndex += tempTd.colSpan;
	  			}else{
	  				colspanIndex += 1;
	  			}
  				defnum += 1;
	  		}
	  		colsnum = 0;
	  		for(var i=cells.length-1;i>=0;i--){
	  			var tempTd = cells[i];
	  			if(tempTd.colSpan){
	  				colsnum += tempTd.colSpan;
	  			}else{
	  				colsnum += 1;
	  			}
	  		}
	  	}
  		try{
      		for(var j=trIndex+1;j<table.rows.length;j++){
      			var trow = table.rows[j];
	      		var sum = 0;
	      		var value = null;
				var k=0;
      			for(var ki=0;ki<thArra.length;ki++){
      				var th = thArra[ki];
      				k = th.cellIndex+(colspanIndex-defnum)-(colsnum-(table.rows[j].cells.length+deftype));
      				var cell = trow.cells[k];
      				if(th && cell && th.tableRowType && (th.tableRowType!='result') && (value = cell.innerText)){
		      			value = value.replaceAll(",","").trim();
  						if(isDecimal(value)){
  							if(value.indexOf(".")<0){
								sum = eval("sum"+th.tableRowType+"parseInt(value,10)");
  							}else{
  								sum = eval("sum"+th.tableRowType+"parseFloat(value,10)");
  							}
  						}
      				}
      			}
	      		var newTd=null;
  				if(deftype){
      				newTd = document.createElement("td");
		      		trow.appendChild(newTd);
  				}else{
  					newTd = trow.cells[trow.cells.length-1];
  				}
      			
      			
	      		if(newTh.formatNumberStr){
	      			sum = formatNumber(sum,newTh.formatNumberStr);
	      		}else{
		      		if(isDecimal(""+sum) && !isInteger(sum)){
		      			sum = FormatNumberWS(sum,2);
		      		}
	      		}
	      		if(trInput>=j){
		      		newTd.innerText=sum;
	      		}
	    		try{
		      		if(newTh.align){
		      			newTd.align = newTh.align;
		      		}
		      	}catch(e){}
      		}
  		}catch(e){throw e}
	}
	/**
	*	插入至第一行.参数说明与cumTableRow相同
	**/
	function cumTableRow2(tableName,formatNumberStr,align,title){
	  	var table=$id(tableName)||$name(tableName);
	  	var thArra = table.getElementsByTagName("th");
	  	var newTh = null;
	  	var trIndex=null;
	  	if(thArra.length<1){
	  		return null;
	  	}else{
	  		for(var i=0;i<thArra.length;i++){
	  			if(thArra[i].tableRowType=='result'){
	  				newTh=thArra[i];
	  				break;
	  			}
	  		}
	  		if(newTh){}else{
	  			var per = document.getElementById("per");
		  		var thRow = thArra[0].parentNode;
		  		newTh = document.createElement("th");
		  		if(title){}else{
		  			title = "小计";
		  		}
		  		if(formatNumberStr){
		  			newTh.formatNumberStr=formatNumberStr;
		  		}
		  		if(align){
		  			newTh.align = align;
		  		}
		  		newTh.innerText=title;
		  		newTh.tableRowType=='result'
		  		thRow.insertBefore(newTh,per);
	  		}
	  		trIndex = newTh.parentNode.rowIndex;
	  	}
  		try{
      		for(var j=trIndex+1;j<table.rows.length-1;j++){
      			var trow = table.rows[j];
	      		var sum = 0;
	      		var value = null;
      			for(var k=0;k<trow.cells.length;k++){
      				var cell = trow.cells[k];
      				if(thArra[k+1].tableRowType && (thArra[k+1].tableRowType!='result') && (value = cell.innerText)){
		      			value = value.replaceAll(",","").trim();
  						if(isDecimal(value)){
  							if(value.indexOf(".")<0){
								sum = eval("sum"+thArra[k+1].tableRowType+"parseInt(value,10)");
  							}else{
  								sum = eval("sum"+thArra[k+1].tableRowType+"parseFloat(value,10)");
  							}
  						}
      				}
      			}
	      		var newTd = document.createElement("td");
	      		var ename = "pers"+j;
	      		var per   = document.getElementById(ename);
	      		if(newTh.formatNumberStr){
	      			sum = formatNumber(sum,newTh.formatNumberStr);
	      		}else{
		      		if(isDecimal(""+sum) && !isInteger(sum)){
		      			sum = FormatNumberWS(sum,2);
		      		}
	      		}
	      		newTd.innerText=sum;
	    		try{
		      		if(newTh.align){
		      			newTd.align = newTh.align;
		      		}
		      	}catch(e){}
	      		trow.insertBefore(newTd,per);
      		}
  		}catch(e){throw e}
	}
	
	/**
	* 格式化小数
	**/
	function FormatNumberWS(srcStr,nAfterDot){ 
	  　　var   srcStr,nAfterDot;   
	  　　var   resultStr,nTen;   
	  　　srcStr   =   ""+srcStr+"";   
	  　　strLen   =   srcStr.length;   
	  　　dotPos   =   srcStr.indexOf(".",0);   
	  　　if   (dotPos   ==   -1){   
	  　　　　resultStr   =   srcStr+".";   
	  　　　　for   (i=0;i<nAfterDot;i++){   
	  　　　　　　resultStr   =   resultStr+"0";   
	  　　　　}   
	  　　　　return   resultStr;   
	  　　}   
	  　　else{   
	  　　　　if   ((strLen   -   dotPos   -   1)   >=   nAfterDot){   
	  　　　　　　nAfter   =   dotPos   +   nAfterDot   +   1;   
	  　　　　　　nTen   =1;   
	  　　　　　　for(j=0;j<nAfterDot;j++){   
	  　　　　　　　　nTen   =   nTen*10;   
	  　　　　　　}   
	  　　　　　　resultStr   =   Math.round(parseFloat(srcStr)*nTen)/nTen;   
	  　　　　　　return   resultStr;   
	  　　　　}   
	  　　　　else{   
	  　　　　　　resultStr   =   srcStr;   
	  　　　　　　for   (i=0;i<(nAfterDot   -   strLen   +   dotPos   +   1);i++){   
	  　　　　　　　　resultStr   =   resultStr+"0";   
	  　　　　　　}   
	  　　　　　　return   resultStr;   
	  　　　　}   
	  　　}   
	}
      
	String.prototype.replaceAll = function(AFindText,ARepText){
		raRegExp = new RegExp(AFindText,"g");
		return this.replace(raRegExp,ARepText)
	}
	/**  
	*删除左右两端的空格  
	*/  
	String.prototype.trim=function(){      
	    return this.replace(/(^\s*)|(\s*$)/g, '');   
	}     
	/**  
	*删除左边的空格  
	*/  
	String.prototype.ltrim=function()   
	{   
	  return this.replace(/(^s*)/g,'');   
	}   
	/**  
	*删除右边的空格  
	*/  
	String.prototype.rtrim=function()   
	{   
	  return this.replace(/(s*$)/g,'');   
	}
	/**
	* 将URL转化为form方式提交
	* iframeObj:欲提交的iframe...最后做为form.target
	* urlStr:需转化的url
	*/
	function ChaGetToPostByForm(iframeObj,urlStr){
		var targetStr="";
		if(iframeObj){}else{
			alert('frame定义不完整');
			return;
		}
		if(typeof(iframeObj)=='string'){
			targetStr = iframeObj;
		}else if(iframeObj.tagName=='IFRAME' && iframeObj.name){
			targetStr = iframeObj.name;
		}else{
			alert('frame定义不完整');
			return;
		}
		var _form = document.getElementById("hiddenFormUrlToInput");
		if(_form){
			_form.removeNode(true);
		}
		_form= document.createElement("form");
	    _form.setAttribute('name','hiddenFormUrlToInput');
	    _form.setAttribute('id','hiddenFormUrlToInput');
	    _form.setAttribute('method','post');
	    _form.style.display="none";
	    _form.target = targetStr;
		if(urlStr.indexOf("?")>0){
			var url = urlStr.substr(0,urlStr.indexOf("?"));
			_form.action=url;
			var substrH = urlStr.substr(urlStr.indexOf("?")+1);
			var params = substrH.split("&");
			for(var i=0;i<params.length;i++){
				var param = params[i].split("=");
				var _input=document.createElement('input');
				    _input.setAttribute('type', 'hidden');
				    _input.setAttribute('name', param[0]);
				    _input.setAttribute('value', param[1]);
				_form.appendChild(_input);
			}
		}else{
			_form.action=urlStr;
		}
	    document.body.appendChild(_form);
		_form.submit();
	}
	
	/**
	*是否为空,为空返加true
	*/
	function isBlank(obj) {
		if (typeof obj == "undefined") {
			return true;
		} else {
			if (typeof obj == "number") {
				if (obj == null || obj == "" || obj == 0 || ("" + obj).toLowerCase() == "undefined") {
					return true;
				} else {
					return false;
				}
			} else {
				if (typeof obj == "boolean") {
					return !obj;
				} else {
					if (typeof obj == "function") {
						if (("" + obj).toLowerCase() == "function(){}") {
							return true;
						} else {
							return false;
						}
					} else {//string object
						if (obj && (("" + obj).toLowerCase() != "undefined")) {
							if(obj.constructor == window.Array && obj.length<1){
								return true;
							}
							return false;
						} else {
							return true;
						}
					}
				}
			}
		}
	}
	
	/**
	* 获取绝对位置
	*/
	function getParentNode(obj,cw) {
		this.top = 0;
		this.left = 0;
		var tempObj = obj;
		if (tempObj.offsetParent) {
			while (!isBlank(tempObj)) {
				if (!isBlank(tempObj.offsetTop)) {
					this.top += parseInt(tempObj.offsetTop);
					this.top -= parseInt(tempObj.scrollTop);
				}
				if (!isBlank(tempObj.offsetLeft)) {
					this.left += parseInt(tempObj.offsetLeft);
					this.left -= parseInt(tempObj.scrollLeft);
				}
				tempObj = tempObj.offsetParent;
			}
		} else {
			this.top = tempObj.x;
			this.left = tempObj.y;
		}
	
		try{
			if(cw){
				obj = cw;
			}else{
				obj=obj.document.parentWindow;
			}
			do{
				this.top -= obj.document.body.scrollTop;
				this.left -= obj.document.body.scrollLeft;
				tempObj = obj;
				obj = obj.parent;
			}while(!isBlank(obj) && tempObj!= obj);
		}catch(e){alert(e)}
	}
    /**
    * 通过关键字查找viewlist里的关键字，关在找到后打上勾
    * param1:opType    tableAll、rowParam、cell。tableAll是整张表查找关键字;  rowParam是按h:param name=';  cell是根据某列查找，起启标志为0
    * param2:groupId    w:checkGroup id="group1">
    * param3:keyValue     需查找的关键字,可以为数据,但最后与paramName,cellIndex合作
    * param4:iframeId   table所在的框架，如果在函数本页则为空。        为可选参数
    * param5:paramName     h:param name='可为数组                           为可选参数
    * param6:cellIndex    所在列         可为数组                           为可选参数
    * param7:startRow     查找的起启行                                      为可选参数
    * return true：找到  false：未找到
    * 操作说明
    *              tableAll 必需参数为:opType,groupId,keyValue,paramName。如:findFKeyFun("tableAll","group1","638");
    *              rowParam 必需参数为:opType,groupId,keyValue,paramName如:findFKeyFun("rowParam","group1",7408,null,"selectedObjects/nYjid");
    *              cell     必需参数为:opType,groupId,keyValue,paramName如:findFKeyFun("cell","group1","638",null,null,1);
    */
	function findFKeyFun(opType,groupId,keyValue,iframeId,paramObj,cellIndex,startRow,endRow){
		var cw = null;
		var group = null;
		var returnValue=false;
		if(iframeId){
			try{
				cw = $id(iframeId).contentWindow;
			}catch(e){}
		}else{
			cw = window;
		}
		group = cw.$id(groupId);
		var tableObj = null;
		var formObj = null;
		var temp = 0;
		if(startRow){
			temp = startRow;
		}else{
			temp = group.getRows().length;
		}
		if(temp>0){
			var trObj = group.get(0);
			temp=trObj.checkbox.parentNode.parentNode.parentNode.rowIndex;
//			tableObj = trObj.checkbox.parentNode.parentNode.parentNode.parentNode;
			var obj = trObj.checkbox;
			var tag = "TABLE";
		    while(obj!=null && typeof(obj.tagName)!="undefind"){   
				if(obj.tagName==tag.toUpperCase()){
					tableObj = obj;
					break;
				}
				obj=obj.parentElement;
			}
		}
		if(opType == "rowParam"){
			var rows = group.getRows();
		    if(paramObj && paramObj.splice){}else{
		    	paramName = paramObj;
		    	paramObj = new Array();
		    	paramObj[0] = paramName;
		    }
		    if(keyValue && keyValue.splice){}else{
		    	keyValueName = keyValue;
		    	keyValue = new Array();
		    	keyValue[0] = keyValueName;
		    }
			for(var i=0;i<rows.length;i++){
				if(rows[i].getStatus()){
					continue;
				}
			    var j=0;
			    for(;j<paramObj.length;j++){
			    	paramName = paramObj[j];
			    	if(paramName){}else{continue;}
					var rowObj = rows[i].getParam(paramName);
					var keyName = keyValue;
					if(keyValue.length>j){
						keyName = keyValue[j];
					}else{
						keyName = keyValue[keyValue.length-1];
					}
					if(keyName != rowObj){
						break;
					}
			    }
			    if(j==paramObj.length){
					findFKeyOP(rows[i],iframeId,tableObj,cw);
					returnValue = true;
			    }
			}
		}
		
		if(opType == "tableAll"){
			var rows = tableObj.rows;
			if(keyValue && keyValue.splice){}else{
		    	keyValueName = keyValue;
		    	keyValue = new Array();
		    	keyValue[0] = keyValueName;
		    }
			for(var i=temp;i<rows.length;i++){
				var rowObj = group.get(rows[i].rowIndex-temp);
				if(rowObj.getStatus()){
					continue;
				}
				var cells = rows[i].cells;
				for(var j=0;j<cells.length;j++){
					var cell = cells[j];
					for(var k=0;k<keyValue.length;k++){
						keyValueName = keyValue[k];
						if(trim(cell.innerText) == trim(keyValueName)){
							findFKeyOP(rowObj,iframeId,tableObj,cw);
							returnValue = true;
							break;
						}
					}
				}
			}
		}
		
		if(opType == "cell"){
			var rows = tableObj.rows;
		    if(cellIndex && cellIndex.splice){}else{
		    	cellName = cellIndex;
		    	cellIndex = new Array();
		    	cellIndex[0] = cellName;
		    }
		    if(keyValue && keyValue.splice){}else{
		    	keyValueName = keyValue;
		    	keyValue = new Array();
		    	keyValue[0] = keyValueName;
		    }
			for(var i=temp;i<rows.length;i++){
				var rowObj = group.get(rows[i].rowIndex-temp);
				if(rowObj.getStatus()){
					continue;
				}
			    var j=0;
			    for(;j<cellIndex.length;j++){
			    	cellName = cellIndex[j];
			    	if(cellName){}else{continue;}
			    	var cell = rows[i].cells[cellName];
					var keyName = keyValue;
					if(keyValue.length>j){
						keyName = keyValue[j];
					}else{
						keyName = keyValue[keyValue.length-1];
					}
					if(trim(cell.innerText) != trim(keyName)){
						break;
					}
			    }
			    if(j==cellIndex.length){
					findFKeyOP(rowObj,iframeId,tableObj,cw);
					returnValue = true;
			    }
				
			}
		}
		return returnValue;
	}
    /**
    * 通过关键字查找viewlist里的关键字，关在找到后打上勾
    * param1:opType    tableAll、rowParam、cell。tableAll是整张表查找关键字;  rowParam是按h:param name=';  cell是根据某列查找，起启标志为0
    * param2:groupId    w:checkGroup id="group1">
    * param3:startValue     需查找的关键字,可以为数据,但最后与paramName,cellIndex合作
    * param4:endValue     需查找的关键字,可以为数据,但最后与paramName,cellIndex合作
    * param5:iframeId   table所在的框架，如果在函数本页则为空。        为可选参数
    * param6:paramName     h:param name='可为数组                           为可选参数
    * param7:cellIndex    所在列         可为数组                           为可选参数
    * param8:startRow     查找的起启行                                      为可选参数
    * return true：找到  false：未找到
    * 操作说明
    *              cell     必需参数为:opType,groupId,keyValue,paramName如:findFKeyFun("cell","group1","638",null,null,1);
    */
	function findFKeyFun2(opType,groupId,startValue,endValue,iframeId,paramObj,cellIndex,startRow,endRow){
		var cw = null;
		var group = null;
		var returnValue=false;
		if(iframeId){
			try{
				cw = $id(iframeId).contentWindow;
			}catch(e){}
		}else{
			cw = window;
		}
		group = cw.$id(groupId);
		var tableObj = null;
		var formObj = null;
		var temp = 0;
		if(startRow){
			temp = startRow;
		}else{
			temp = group.getRows().length;
		}
		if(temp>0){
			var trObj = group.get(0);
			temp=trObj.checkbox.parentNode.parentNode.parentNode.rowIndex;
			var obj = trObj.checkbox;
			var tag = "TABLE";
		    while(obj!=null && typeof(obj.tagName)!="undefind"){   
				if(obj.tagName==tag.toUpperCase()){
					tableObj = obj;
					break;
				}
				obj=obj.parentElement;
			}
		}
		if(opType == "cell"){
			var rows = tableObj.rows;
		    if(cellIndex){}else{
		    	alert('请输入列号!');
		    	return;
		    }
		    if(startValue && endValue){}else{
		    	alert('请输入正确值参数!');
		    	return;
		    }
		    var isfirst = true;
			for(var i=temp;i<rows.length;i++){
				var rowObj = group.get(rows[i].rowIndex-temp);
				if(rowObj.getStatus()){
					continue;
				}
			    var j=0;
		    	var cell = rows[i].cells[cellIndex];
				var iText = trim(cell.innerText);
				if(iText >= trim(startValue)){
					if(iText<=trim(endValue)){
						if(isfirst){
							isfirst=false;
							findFKeyOP(rowObj,iframeId,tableObj,cw);
						}else{
							rowObj.setSelected();
						}
					}else{
						return;
					}
				}
			}
		}
		return returnValue;
	}
	function findFKeyOP(rowObj,iframeId,tableObj,cw){
	
		cw.scrollTo(0,0);
		var gpn = new cw.getParentNode(rowObj.checkbox,cw);
		cw.scrollTo(gpn.left,gpn.top);
		if(iframeId){
			window.scrollTo(0,0);
			var gpniframe = new getParentNode($id(iframeId));
			window.scrollTo(gpniframe.left,gpniframe.top);
		}
		
		rowObj.setSelected();
//		rowObj.checkbox.focus();//功能与上面scrollTo重复
	}  
	/**
*	初始化comBoSelect过滤参数。控制comBoSelect输入几位后才去库时查找数据，需与web-common.js、eos-web.js合做
*   comboId comBoSelect的ID(必选)
*   marker  标示，默认某个值的FilterModel的值,当marker无值时到comboId（可选）
*   length  输入的位数,当有这个参数时，marker失效（可选）
*/
   function initComboFilterModel(comboId,length,marker){
   	if(length){}else{
	   	if(marker){}else{
	   		marker = comboId;
	   	}
   		if(marker=='get_user_jdzh'){//使用标识
   			length=2;
   		}
   	}
	var obj = [];
	obj.filterModel = length;
	window[comboId]=obj;
   }
	/**
	*	机要用户初始化_临时方法,之后可能将由new comboselect替换,目前暂定第二位开始过滤
	*/

    function paramFunc(comboSobj){
        var filterModel;
    	if(window[comboSobj.id]){
	    	filterModel = window[comboSobj.id].filterModel;
    	}
    	if(filterModel){}else{
  			filterModel = 1;
		}
		
    	comboSobj.filterModel=filterModel;
  		var strFf = "<filterModel>"+filterModel+"</filterModel>";
  		if(comboSobj.version){}else{
  			return strFf;
  		}
  		var ff = comboSobj.filterField;
  		var ffArra = ff.split(",");
  		var giv = comboSobj.inputObject.value;
  		comboSobj.oldIOV = giv;
  		strFf += "<combopara>";
  		if(comboSobj.valueField && comboSobj.hiddenObject.value && comboSobj.initInput){
  			strFf += "<"+comboSobj.valueField+">"+comboSobj.initInput+"</"+comboSobj.valueField+">";
  		}else{
	  		for(var i=0;i<ffArra.length;i++){
	  			strFf += "<"+ffArra[i]+">"+giv+"</"+ffArra[i]+">";
	  		}
  		}
  		strFf += "</combopara>";
  		
		return strFf;
    	
		
    	 
  	}
  	/**
  	*	回调方法
  	*/
	function comboSelectCallBackFun(){
		return window.paramCallBackReturn;
	}
	/**
	* 树对象上级进行单击事件
	**/
	function nodeParentClick(node){
		var np = node.getParent();
		var H = np.entity.name;
		var E = np.getTree().model.getEntityInfo(H);
		eval(E.onclick+"(node)");
	}
	/**
	* 获取当前节点所处级别
	*/
	function getTreeLevel(node){
		var i=0;
		while(node.isRootNode()){
			node = node.getParent();
			i++;
		}
		return i;
	}
	/**
	* 在表格内通过关键字查找第几列
	* obj:开始对象
	* rowInd:需取数据的列编号数组
	* return 列内容值
	*/
	function getTextByCell(obj,rowInd){
		while(obj.tagName && obj.tagName!="TR"){
			obj = obj.parentNode;
		}
		var oix = obj.rowIndex;
		var trObj = obj;
		if(isBlank(trObj)){
			alert("不在表格tr中,请联系管理员");
			return null;
		}
		
		while(obj.tagName && obj.tagName!="TABLE"){
			obj = obj.parentNode;
		}
		var tableObj = obj;
		if(isBlank(tableObj)){
			alert("不在表格中,请联系管理员");
			return null;
		}
		if(rowInd && rowInd.length<1){
			alert("没有需查寻的字段!请联系管理员!");
			return null;
		}else if(isBlank(rowInd.splice) && typeof(rowInd)=='string'){
			rowInd = rowInd.split(",");
		}
	
		var thArra = tableObj.getElementsByTagName("th");
		for(var i=0;i<thArra.length;i++){
	      	if(thArra[i].id){
	      		var indexTemp = $indexOf(rowInd,thArra[i].id);
	      		rowInd[indexTemp]=i;
	      	}else if(thArra[i].name){
	      		var indexTemp = $indexOf(rowInd,thArra[i].name);
	      		rowInd[indexTemp]=i;
	      	}
		}
		for(var i=0;i<rowInd.length;i++){
			rowInd[i] = trim(trObj.cells[rowInd[i]].innerText);
		}
		return rowInd
	}
	/**
	* 在表格内通过关键字查找第几列
	* obj:开始对象
	* rowInd:需取数据的列编号数组
	* return 列内容值
	*/
	function getTextByCheckbox(obj,rowInd){
		while(obj.tagName && obj.tagName!="TR"){
			obj = obj.parentNode;
		}
		var trObj = obj;
		if(isBlank(trObj) || trObj.cells.length<=rowInd || rowInd<0){
			alert("不存的tr/td,请联系管理员");
			return null;
		}
		return trObj.cells(rowInd).innerText
	}
	/**
		弹出进度条，显示指定位置。 
		发布状态：public 
		输入参数
		id：进度条的标识 
		title：进度条内显示的文字，支持HTML代码 
		winLeft：进度条左侧坐标 
		winTop：进度条上侧坐标 
**/
	function showProgressBar(E, B, D, C,W) {
		if(W){}else{
			W = top;
		}
		if(W.EOSProgressCache){}else{
			W.EOSProgressCache=new Object();
		}
		var A = W.EOSProgressCache[E] || null;
		if (A){}else{
			A = new ProgressBar(E);
			W.EOSProgressCache[E]=A;
		}
		try{
			A.show(B, D, C);
		}catch(e){
			W.EOSProgressCache[E]=null;
			A=null;
		}
		return A
	}
	/**
		hideProgressBar(id)
		功能说明
		隐藏指定的进度条。 
		发布状态：public 
	**/
	function hideProgressBar(B,W) {
		if(W){}else{
			W = top;
		}
		B = B || "eos_pageProgressBar";
		
		if(W.EOSProgressCache){
			var A = W.EOSProgressCache[B] || null;
			try{
				if (A)
					A.hide();
			}catch(e){}
			W.EOSProgressCache[B] = null;
		}
		return A
	}
	/**
	* 判断js对象是否为数组
	* return:true数组 false不是数组
	**/
	function isArray(a){ 
	    var result = a && 
				typeof a === 'object' && 
				typeof a.length === 'number' && 
				typeof a.splice === 'function' && 
				!(a.propertyIsEnumerable('length'));
		
		return result;
	}
	String.prototype.startWith=function(str){
		if(isBlank(str)){
			return false;
		}
		var reg=new RegExp("^"+str);     
		return reg.test(this);        
	}
	String.prototype.endWith=function(str){
		if(isBlank(str)){
			return false;
		}
		var reg=new RegExp(str+"$");
		return reg.test(this);        
	}
	
	function ParamEntity(A) {
	    this.id = A;
	    this.arraEntity=[];
	    this.relatedSelID="";
	}
	ParamEntity.prototype.setProperty = function(key,values) {
		if(values){
		    var valueArra = values.split(",");
		    for(var i=0;i<valueArra.length;i++){
		    	var entity = this.arraEntity[i];
		    	if(isBlank(entity)){
					entity = this.arraEntity[i] = new Entity();	
		    	}
	    		entity.setProperty(key,valueArra[i]);
		    }
		    this[key]=values;
	    }
	};
	ParamEntity.prototype.toString = function() {
	    return this.arraEntity;
	};
	/**
	*	radioName:checkbox的name
	*   cheValue:欲设置的值
	*/
	function setCheckboxValue(radioName,cheValue){
		if(cheValue){}else{
			return;
		}
		var radioObj;
		if(typeof(radioName)=='string'){
			radioObj = document.getElementsByName(radioName);
		}else{
			radioObj = document.getElementsByName(radioName.name);
		}
		for(var i=0;i<radioObj.length;i++){
			if((","+cheValue+",").indexOf(","+radioObj[i].value+",")>-1){
				radioObj[i].checked="checked";
			}else{
				radioObj[i].checked="";
			}
		}
		return false;
	}