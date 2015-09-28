<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<html>
<head>
	<title></title>
	<%
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:01 GMT");
	%>
	<link href="/eos-default/css/style1/style-custom.css" type="text/css" rel="stylesheet" >
	<h:css href="css/style1/style-custom.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<!-- css -->
	<script language="javascript1.2">
	
		var dCell=null;
		var callBack=null;
		var args = null;

		function AddAll(ObjSource,ObjTarget,direction){
			SelectAll(ObjSource);
			Add(ObjSource,ObjTarget,direction);
		}
		
		function SelectAll(ObjSource){
			for(var i=0;i<ObjSource.length;i++){
				ObjSource.options[i].selected = true;
			}
		}

		function isExistObj(opt,optionlist){
			var tag = false;
			for(var i = 0;i < optionlist.length;i++){
				if(optionlist.options[i].value == opt.value){
					tag = true;
				}
			}
			//
			return tag;
		}

		/*********************************两个列表框操作*****************************************/
		function Add(ObjSource,ObjTarget,direction){
			for(var i=0;i<ObjSource.length;i++){
				if(ObjSource.options[i].selected){
					var opt = document.createElement("OPTION");
					opt.value = ObjSource.options[i].value;
					opt.text = ObjSource.options[i].text;
					
					opt.width = ObjSource.options[i].width;
					opt.colspan = ObjSource.options[i].colspan;
					opt.rowspan = ObjSource.options[i].rowspan;
					
					//alert(opt.value);
					//
					if(direction == 1){//选择
						if(!isExistObj(opt,ObjTarget)){
							ObjTarget.add(opt);
							ObjSource.options.removeChild(ObjSource.options[i--]);
						}else{
							alert("当前项目已被选择!");
						}
					}else if(direction == 2){//删除
						if(!isExistObj(opt,ObjTarget)){
							ObjTarget.add(opt);
						}
						ObjSource.options.removeChild(ObjSource.options[i--]);
					}
				}
			}
		}
		/**
		* 窗口弹出时，初始化数据
		**/
		function init(){
			args = window["dialogArguments"];
			//取得主页面传过来的参数
			
			dCell = args[2].$id(args[0]);
			callBack=args[1];
			
			$id('xpath').value=dCell.xpath;
			$id('queryAction').value=dCell.queryAction;
			$id('pageSize').value=dCell.pageSize;
			$id('entityType').value=dCell.entityType
			if(dCell.initParamFunc){
				try {
					$id('params').value += eval("args[2]."+dCell.initParamFunc + "()");
				} catch (e) {
					$handle(e)
				}
			}
			
			//原表字段列表
			var Source = $id('Source');
			var field = dCell.fields;
			for(var i=0;i<field.length;i++){
				var opt = document.createElement("OPTION");
				opt.text = field[i].label;
				opt.value = field[i].fieldName;
				if(field[i].width!=null && field[i].width!='undefined'){
					opt.width = field[i].width;
				}
				if(field[i].colspan!=null && field[i].colspan!='undefined'){
					opt.colspan = field[i].colspan;
				}
				if(field[i].rowspan!=null && field[i].rowspan!='undefined'){
					opt.rowspan = field[i].rowspan;
				}
				Source.add(opt);
			}
		}
		/**
		* 执行导出功能
		*/
	    function exportOrgs() {
		  //判断是否有空
		  var frm = $name("submitForm");
<%--  
		  var fields = "";
		  var starpage = $id("Target").options;
		  
		  if(starpage.length<1){
		  	alert('请选择导入的字段！');
		  	return;
		  }
		  //[0]=text,[1]=value,[2]=width,[3]=colspan,[4]=rowspan
		  for(var i=0;i<starpage.length;i++){
		  	fields += starpage[i].text + ":" + starpage[i].value + ":";
		  	if(starpage[i].width!=null && starpage[i].width!='undefined'){
		  		fields += starpage[i].width + ":";
		  	}
		  	if(starpage[i].colspan!=null && starpage[i].colspan!='undefined'){
		  		fields += starpage[i].colspan + ":";
		  	}
		  	if(starpage[i].rowspan!=null && starpage[i].rowspan!='undefined'){
		  		fields += starpage[i].rowspan;
		  	}
		  	fields += ";";
		  }
		  $id("fields").value = fields;--%>
	  
			var reInfo = checkSubmitCondition();
			if (reInfo == "") {
				frm.action = "com.primeton.purview.importSimpleExcel.flow";
				frm.target = "querylistFrame";
//				$id('_eosFlowAction').value = "importExcel";
				frm.elements["_eosFlowAction"].value = "runExcel";
				frm.submit();
			} else {
				alert(reInfo);
			}
	    }
	    /**
	    * 获取初始XML数据
	    **/
	    function getParam(){
	    	if(dCell.getQueryForm!=null && dCell.getQueryForm!='undefined'){
				param = dCell.getQueryForm();
	    	}
			if (dCell.queryParam)
				param += dCell.queryParam;
			if (dCell.pageParam)
				param += dCell.pageParam;
				
			if (dCell.initParamFunc) {
				try {
					param += eval("args[2]."+dCell.initParamFunc + "()");
				} catch (e) {
					$handle(e)
				}
			}
	
			var E = "<root>"+getFormParam(dCell);
			var C = param;
			if (C && C.indexOf("<root><data>") == 0) {
				C = C.substr("<root><data>".length);
				C = C.substr(0, C.lastIndexOf("</data></root>"))
			}
			E += "<data>" + C + "</data></root>";
			return E;
	    }
	    /**
	    *  获取需要提交的from数据
	    */
	    function getFormParam(dCell){
			var params=new Array();
			if (dCell.paramFormId) {
				var form = args[2].$id(dCell.paramFormId);
				if (form)
					for (var i = 0; i < form.elements.length; i++) {
						var elem = form.elements[i];
						if (elem.name){
							params.push({
								key : elem.name,
								value : getElementValue(elem)
							})
						}
					}
			}
			if (dCell.paramList){
				for (i = 0; i < dCell.paramList.length; i++) {
					elem = dCell.paramList[i];
					if (elem){
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
			B += "<params>" + B + "</params>\n";
			
			return B;
	    }

	    /**
	    *读取XML数据并生成表格
	    **/
	    function createThearHTML(fieldsName){//'Target'
	    	var XmlDom = $id(fieldsName);
	    	var tableStr = "<table class=EOS_table width='560' align=center border=1><TBODY>";
	    	for(var i=0;i<nodeData.length;i++){
	    		var codeHeight = nodeData[i].selectSingleNode("CodeHeight").text;
	    		tableStr += "<tr height='"+codeHeight+"'>";
	    		var listTH = nodeData[i].selectNodes("ListTH");
	    		for(var j=0;j<listTH.length;j++){
	    			var nodeTH = listTH[j];
					var colspanVar = nodeTH.selectSingleNode("Colspan").text;
					var rowspanVar = nodeTH.selectSingleNode("Rowspan").text;
					var codeWidthVar = nodeTH.selectSingleNode("CodeWidth").text;
					tableStr += "<th colSpan='"+colspanVar+"' rowspan='"+rowspanVar+"' width='"+codeWidthVar+"'>";
					tableStr += nodeTH.selectSingleNode("CodeValue").text;
					tableStr += "</th>";
	    		}
	    		tableStr += "</tr>";
	    	}
	    	if(dCell.fields){
		    		var fieldStr = dCell.fields.split(';');
		    		tableStr += "<tr align='center'>";
		    		for(var k=0;k<fieldStr.length;k++){
		    			tableStr += "<td>";
		    			tableStr += "#"+fieldStr[k];
		    			tableStr += "</td>";
		    		}
		    		tableStr += "</tr>";
	    	}
	    	tableStr += "</TBODY></table>";
	    	
	    	tableObj.innerHTML=tableStr;
	    }
	    
		  function htmlExport(tkjh){
			var myAjax = new Ajax("com.primeton.purview.exportTest.htmlExport.biz");
			myAjax.addParam("queryAction", "com.primeton.eos2.testbiz.queryTests.biz");
			myAjax.addParam("fields", "orgcode;orgname;orgleader");
			myAjax.addParam("xpath", "tests");
			var param = "<root><data>"+getTHead()+"</data><params>"+getFormParamByHtml(tkjh.form)+"</params></root>";
			myAjax.addParam("params", param);
			myAjax.submit();
			var returnNode = myAjax.getResponseXMLDom();
			if( returnNode ) {
			    document.getElementById("fileDiv").innerHTML = myAjax.getValue("root/data/downloadFile");
			}
		  }
		  
		function previewClick(){
			var formObj = document.getElementById("submitForm");
			var reInfo = checkSubmitCondition();
			if (reInfo == "") {
				formObj.action = "com.primeton.purview.importSimpleExcel.flow";
				formObj.target = "querylistFrame";
				formObj.submit();
				$id('_eosFlowAction').value = "readFile";
			} else {
				alert(reInfo);
			}
		}
		/**
		* 检查提交内容是否完整
		**/
		function checkSubmitCondition(){
			var formObj = document.getElementById("submitForm");
			var reInfo = "";
			
			var readFileStr = $id("readFile").value;
			
			if(readFileStr){
				readFileStr = readFileStr.substr(readFileStr.length-4);
				if(readFileStr.toLowerCase()!='.xls'){
					reInfo = "您选择的EXCEL文件不正确！";
				}
			}else{
				reInfo = "请选择导入的EXCEL文件！";
			}
			return reInfo;
		}
		function exportSql(){
			var querylistFrame = document.getElementById("querylistFrame");
			querylistFrame.contentWindow.exportSql();
		}
	</script>
</head>
<body>
<h:form name="submitForm" id="submitForm" action="com.primeton.purview.importSimpleExcel.flowx" method="post" enctype="multipart/form-data">
	<table height="95" width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="5" bgcolor="#CECECE">
			</td>
		</tr>
		<tr>
			<td bgcolor="#F3F3F3" valign="top">
				<input name="xpath" id="xpath" type="hidden"/>
				<input name="queryAction" id="queryAction" type="hidden"/>
				<input name="pageSize" id="pageSize" type="hidden"/>
				<input name="totalRow" id="totalRow" type="hidden"/>
				<input name="fields" id="fields" type="hidden"/>
				<input name="_eosFlowAction" id="_eosFlowAction" type="hidden" value="readFile"/>
    			<input name="page/begin" id="page/begin" type="hidden" />
    			<input name="page/length" id="page/length" type="hidden" />
    			<input name="params" id="params" type="hidden"/>
    			
    			<input name="entityType" id="entityType" type="hidden"/>
    			<input name="submitAction" id="submitAction" type="hidden"/>
    			
				<table width="95%"  border="1" align="center" cellspacing="0" cellpadding="0">
					<tr>
						<td width="50">导入Excel</td>
						<td align="center" valign="top">
							<input type="file" name="readFile" id="readFile" size="60" value="C:\机构1005_A1.xls">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" valign="top">

						</td>
					</tr>
					<tr align="center">
						<td valign="middle" align="center" colspan="2">
							<input style="width:55" name='yr' id='yr' type='button' class='button' value='预览' onClick="previewClick();">
							<input style="width:55" name='btok' id='btok' type='button' class='button' value='导 入' onClick="exportSql()" disabled="disabled">
							<input style="width:55" name='btclose' type='button' class='button' value='关 闭' onClick='window.closeD();'>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</h:form>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CECECE" align="center">
		<tr>
			<td align="center" valign="top">
				 <table border="0" style="" bordercolor="#111111" width="100%">
					<tr>
						<td width="100%" height="435" id="tableHTML">
							<iframe width="99%" height="100%"  border="1" frameborder="NO" scrolling="NO" src="" name="querylistFrame" id="querylistFrame">
							</iframe>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
