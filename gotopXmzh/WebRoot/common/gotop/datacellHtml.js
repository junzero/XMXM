		/**
		* 回调函数,一般用于处理分页
		*/
		function pageBack(begin,length,params,pageName){
			if(params==null || params=='undefined'){
				return "";
			}
			
			var leIndexSta = params.lastIndexOf("<page>");
			var leIndexEnd = params.lastIndexOf("</page>");
			
			var newParams = params.substr(0,leIndexSta) 
						+ "<page><begin>"+begin+"</begin><length>"+length+"</length><count>0</count><isCount>false</isCount>"
						+ params.substr(leIndexEnd);
			
			return newParams;//关闭xml解析方式
			
//-----------------------------------------------------暂时关闭-xml解析方式-----------------------------------------------------------------

			alert("----params:"+params);			
			var XmlDom = createXmlDom(params);
			alert("----XmlDom.xml:"+XmlDom.xml);			
			var node = XmlDom.selectSingleNode("root/data/"+pageName);
			if(node==null){//通过查询参数方式
				node=XmlDom.createElement(pageName);
				var data = XmlDom.selectSingleNode("root/data");
				data.appendChild(node);
			}
			{//通过分页方式
				var beginNode = node.selectSingleNode("begin");
				var lengthNode = node.selectSingleNode("length");
			
				if(beginNode==null){
					beginNode=XmlDom.createElement('begin');
					node.appendChild(beginNode);
				}
				beginNode.text=begin;
				
				if(lengthNode==null){
					lengthNode=XmlDom.createElement('length');
					node.appendChild(lengthNode);
				}
				console.log("---lengthNode.text:"+lengthNode.text+"---lengthNode.nodeValue:"+lengthNode.nodeValue
								+"---lengthNode.innerText:"+lengthNode.innerText+"---lengthNode.InnerText:"+lengthNode.InnerText
				);
				lengthNode.text=length;
				lengthNode.nodeValue=length;
				lengthNode.innerText=length;
				lengthNode.InnerText=length;
				console.log("-----------------lengthNodeText:"+lengthNode.text);
			}
			if(XmlDom.xml){//不在IE中,会返回undefined
				console.log("-----------------XmlDom.xml:"+XmlDom.xml);
				console.log("-----------------new XMLSerializer().serializeToString(XmlDom):"+new XMLSerializer().serializeToString(XmlDom));
			      return XmlDom.xml;
			}else{
			      return new XMLSerializer().serializeToString(XmlDom);
			}
		}
		  /**
		  * 获取表头
		  **/
	      function getTHead(tableName){
	      	var TestList = $id(tableName) || $name(tableName);
	      	
			var trarra = TestList.getElementsByTagName("TR");
			var thParent = new Array();
			for(var i=0;i<trarra.length;i++){
				var trObj = trarra[i].getElementsByTagName("TH");
				if(trObj.length>0){
					thParent.push(trarra[i])
				}
			}
			
			var trExportThear = document.getElementsByName("trExportThear");
			for(var i=0;trExportThear && i<trExportThear.length;i++){
				thParent.push(trExportThear[i]);
			}
			
			var theadStr = "<QLExportBean>";
			for(var i=0;i<thParent.length;i++){
	
				theadStr += "<ListData><CodeHeight>"+thParent[i].offsetHeight+"</CodeHeight>";
				var tdObj = thParent[i].childNodes;
				for(var j=0;j<tdObj.length;j++){
					var tdTag = tdObj[j];
					theadStr += "<ListTH>";
					theadStr += "<Colspan>"+tdTag.colSpan+"</Colspan>";
					theadStr += "<Rowspan>"+tdTag.rowSpan+"</Rowspan>";
					theadStr += "<Value>"+tdTag.innerText+"</Value>";
					theadStr += "<CodeValue>"+tdTag.innerText+"</CodeValue>";
					theadStr += "<CellType>"+1+"</CellType>";
					theadStr += "<CodeWidth>"+tdTag.offsetWidth+"</CodeWidth>";
					theadStr += "</ListTH>";
				}
				theadStr += "</ListData>"
			}
			theadStr += "</QLExportBean>"
			return theadStr;
	      }
		/**
		*  获取需要提交的from数据,将from对象将转化为xml方式
		*/
		function getFormParamByHtml(formObj){
			//string object
			if(typeof formObj == 'string'){
				formObj = $id(formObj) || $name(formObj) ;
			}else if((typeof formObj == "undefined") || (formObj.elements =='undefined')){
				return "";
			}
			var params=new Array();
			if (formObj) {
				var form = formObj;
				if (form)
					for (var i = 0; i < form.elements.length; i++) {
						var elem = form.elements[i];
						if(elem.tagName=='TABLE' 
							|| elem.tagName=='TR' 
							|| elem.tagName=='TD' 
							|| elem.tagName=='TH'){
							continue;	
						}
						if (elem.name){
							params.push({
								key : elem.name,
								value : getElementValue(elem)
							})
						}
					}
			}
			var B = "";
			for (var A = 0; A < params.length; A++) {
				var D = params[A];
				B += "<param><key>" + D.key + "</key><value>" + D.value + "</value></param>"
			}
			return B;
		}
		
		/**
		*  获取需要提交的from数据
		*/
		function getTableParamByHtml(tableObj,selectObjName){
			//string object
			if(typeof tableObj == 'string'){
				tableObj = $id(tableObj) || $name(tableObj) ;
			}else if((typeof tableObj == "undefined") || (tableObj.childNodes =='undefined')){
				return "";
			}
			var params=new Array();
			if (tableObj) {
//				TestList.insertRow()
				var B = "";
				for (var i = 0; i < tableObj.rows.length; i++) {
					//取tr里的提交对象
					var inputObj = tableObj.rows[i].getElementsByTagName("input");
					B += "<selectObj>";
					for(var j=0;j<inputObj.length;j++){
						if (inputObj[j].name){
							var elemName = inputObj[j].name.substr(0,selectObjName.length);
							//被选择的列表
							if(elemName!=selectObjName || (inputObj[j].type=='checkbox' && !inputObj[j].checked)){
								continue;
							}
							var nameSpl = (inputObj[j].name).split('/');
							if(nameSpl.length<2){
								nameSpl.push(inputObj[j].name); 
							}
							
							B += "<param><key>" + nameSpl[1] + "</key><value>" + getElementValue(inputObj[j]) + "</value></param>"
						}
					}
					B += "</selectObj>";
				}
			}
			B = B.replaceAll("<selectObj></selectObj>","");
			return B;
		}
			
		String.prototype.replaceAll = stringReplaceAll;
		/**
		* 全部替换
		*/
		function stringReplaceAll(AFindText,ARepText){
			raRegExp = new RegExp(AFindText,"g");
			return this.replace(raRegExp,ARepText)
		}
		