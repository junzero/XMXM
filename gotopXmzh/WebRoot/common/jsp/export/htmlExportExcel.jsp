<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common.jsp" %>
<html>
<head>
	<title></title>
	<%
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:01 GMT");
	%>
	<link href="/css/style1/style-custom.css" type="text/css" rel="stylesheet" >
	<h:css href="css/style1/style-custom.css"/>
	<h:script src="/common/gotop/datacellHtml.js"></h:script><!-- 引用导出函数 -->
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<!-- css -->
	<script language="javascript1.2">
	
		var dCell=null;
		var callBack=null;
		var args = null;
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
			$id("fields").value = dCell.fields;
//			$id("fieldsTD").value = dCell.fields;
//			$id("paramsTD").value = dCell.params;
			$id('entityType').value=dCell.entityType
			if(dCell.initParamFunc){
				try {
					$id('params').value += eval("args[2]."+dCell.initParamFunc + "()");
				} catch (e) {
					$handle(e)
				}
			}
			var pageSize = dCell.pageSize;
			var totalRow;
			if(dCell.getTotalRowCount!=null && dCell.getTotalRowCount!='undefined'){
				totalRow = dCell.getTotalRowCount();
			}else{
				totalRow = dCell.totalRow;
			}
			$id('totalRow').value=totalRow;
			createPageSelect(totalRow,pageSize);
			createTableHTML("tableHTML");
		}
		/**
		* 生成页数
		**/
		function createPageSelect(totalRow,pageSize){
			//总页数
			var pageCount = Math.floor(totalRow/pageSize);
			if(totalRow%pageSize!=0){
				pageCount+=1;
			}
			//导出页数 starpage endpage
			var starpage = $id('starpage');
			var endpage = $id('endpage');
			for(var i=1;i<=pageCount;i++){
				var opt = document.createElement("OPTION");
				opt.text = i;
				opt.value = i;
				starpage.add(opt);
				opt = document.createElement("OPTION");
				opt.text = i;
				opt.value = i;
				endpage.add(opt);
			}		
			var XmlDom = createXmlDom(dCell.params);
			var nodeData = XmlDom.selectNodes("root/selectTable/selectObj");
			
			if(nodeData && nodeData.length){
				var pageSelectDiv = $id('pageSelectDiv');
				pageSelectDiv.innerHTML = "导出总记录数:<input type='text' disabled='disabled' size='4' value='"+nodeData.length+"'>条";
				selectDiv.style.display = "none";
			}
		}
		
		/**
		* 执行导出功能
		*/
	    function exportOrgs() {
	     if($id('totalRow').value==0){
	     	alert('没有找到需要导出的数据');
	     	return false;
	     }
	     //判断是否有空
	     // var g = $id("group1");
	      var frm = $name("viewlist1");
	      frm.elements["_eosFlowAction"].value = "exportExcel";
	      
	      //起启页与结束页
		  var starpage = $id('starpage');
		  var endpage = $id('endpage');
		  var starpageIndex = starpage.options[starpage.selectedIndex].value
		  var endpageIndex = endpage.options[endpage.selectedIndex].value
		  if(starpageIndex>endpageIndex){
		  	alert('选择的页数无效！');
		  	return;
		  }
		  var begin = starpageIndex-1;
		  var length = (endpageIndex-starpageIndex+1)*($id('pageSize').value);
	      $id('page/begin').value=begin;
	      $id('page/length').value=length;
		  var E = dCell.params;
		  var pageName = "page";
		  if(args.length>2 && args[3]){
		  	pageName = args[3];
		  }else if(dCell.pageName){
		  	pageName = dCell.pageName;
		  }
		  if(callBack){
			  var callBackStr="";
			  if(eval("args[2]."+callBack)){
				  callBackStr = "args[2]."+callBack + "(begin,length,E,pageName)";
			  }else{
				  callBackStr = "pageBack(begin,length,E,pageName)";
			  }
			  $id("params").value=eval(callBackStr);
		  }else{
		  	$id("params").value=E;
		  }
	      frm.submit();
	    }
	    /**
	    * 获取初始XML数据
	    **/
	    function getParam(){
	    	if(dCell.getQueryForm){
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
	    function createTableHTML(tableName){
	    	var XmlDom = createXmlDom(dCell.params);
	    	var nodeData = XmlDom.selectNodes("root/data/QLExportBean/ListData");
	    	var tableObj = document.getElementById(tableName);
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
	    eventManager.add(window, "load", init);
	</script>
</head>
<body>
<h:form name="viewlist1" action="com.primeton.purview.htmlExport.flowx" method="post">
	<table height="100%" width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td bgcolor="#F3F3F3">
				<form name="submitForm" id="submitForm" method="post">
					<input name="xpath" id="xpath" type="hidden"/>
					<input name="queryAction" id="queryAction" type="hidden"/>
					<input name="pageSize" id="pageSize" type="hidden"/>
					<input name="totalRow" id="totalRow" type="hidden"/>
					<input name="fields" id="fields" type="hidden"/>
					<input name="_eosFlowAction" id="_eosFlowAction" type="hidden" value="exportExcel"/>
	    			<input name="page/begin" id="page/begin" type="hidden" />
	    			<input name="page/length" id="page/length" type="hidden" />
	    			<input name="params" id="params" type="hidden"/>
	    			<input name="tempFormParamLZ" id="tempFormParamLZ" type="hidden"/>
	    			
	    			<input name="entityType" id="entityType" type="hidden"/>
	    			
					<table width="100%"  border="0" align="center" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="2" align="center" valign="top">
								<table width="100%" border="1" cellpadding="0" cellspacing="1" bordercolor="#CECECE" bgcolor="" style="word-wrap: break-word;word-break: break-all;border-collapse:collapse;" align="center">
									<tr>
										<td colspan="4" align="center">
											 <table border="0" style="" bordercolor="#111111" cellpadding="1" cellspacing="2">
												<tr>
													<td id="tableHTML" valign="middle" align="center">
													</td>
												 </tr>
												 <tr>
													 <td colspan="5" align="center">
													 	<div id="selectDiv">导出从
															<select name='starpage' id='starpage' size='1' style='width:70;height:20;'>
															</select>
															&nbsp;到&nbsp;
															<select name='endpage' id='endpage' size='1' style='width:70;height:20;'>
															</select>
														</div>
														<div id="pageSelectDiv"/>
													 </td>
												 </tr>
												</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr height="30">
							<td valign="middle" align="right">
								<input style="width:55" name='btok' id='btok' type='button' class='button' value='导 出' style='margin-right: 30px;' onClick="exportOrgs();">
							</td>
							<td valign="middle" align="left">
								<input style="width:55" name='btclose' type='button' class='button' value='关 闭' style='margin-left: 30px;' onClick='window.closeD();'>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</h:form>
</body>
</html>
