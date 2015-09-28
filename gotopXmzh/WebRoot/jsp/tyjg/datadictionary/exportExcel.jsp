<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
	<title></title>
	<%
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:01 GMT");
	%>
	<link href="/eos-default/css/style1/style-custom.css" type="text/css" rel="stylesheet" >
	<h:css href="css/style1/style-custom.css"/>
	<h:script src="/common/gotop/datacellHtml.js"></h:script><!-- 引用导出函数 -->
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
							if(ObjSource.options.removeChild){
								ObjSource.options.removeChild(ObjSource.options[i--]);
							}else{
								ObjSource.options[i--] = null;
							}
						}else{
							alert("当前项目已被选择!");
						}
					}else if(direction == 2){//删除
						if(!isExistObj(opt,ObjTarget)){
							ObjTarget.add(opt);
						}
						if(ObjSource.options.removeChild){
							ObjSource.options.removeChild(ObjSource.options[i--]);
						}else{
							ObjSource.options[i--] = null;
						}
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
            $id('dictType.dicttypeid').value=args[3]
            $id('dictType.dicttypename').value=args[4]
			if(dCell.initParamFunc){
				try {
					$id('params').value += eval("args[2]."+dCell.initParamFunc + "()");
				} catch (e) {
					$handle(e)
				}
			}
			
			//原表字段列表
			var Target = $id('Target');
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
				Target.add(opt);
			}
			var pageSize = dCell.pageSize;
			var totalRow;
			if(dCell.getTotalRowCount!=null && dCell.getTotalRowCount!='undefined'){
				totalRow = dCell.getTotalRowCount();
			}else{
				totalRow = dCell.getTotalRowCount;
			}
			$id('totalRow').value=totalRow;
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
			starpage.value=dCell.currentPage;
			endpage.value=dCell.currentPage;
		}
		/**
		* 执行导出功能
		* 操作类型optype=0或不真，通过记录数来定导出数，optype=1导出全部
		*/
	    function exportOrgs(optype) {
	     if($id('totalRow').value==0){
	     	alert('没有找到需要导出的数据');
	     	return false;
	     }
	     //判断是否有空
	     // var g = $id("group1");
	      var frm = $name("viewlist1");
	      frm.action = "/tyjg/datadictionary/exportExcel_action.action";
	      
	      var fields = "";
	      var starpage = $id("Target").options;
	      
	      if(starpage.length<1){
	      	alert('请选择导出字段！');
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
	      $id("fields").value = fields;
	      //起启页与结束页
		  var starpage = $id('starpage');
		  var endpage = $id('endpage');
		  
		  var starpage_selectedIndex=0;
		  var endpage_selectedIndex=0;
		  if(optype==1){
				starpage_selectedIndex = 0;
				endpage_selectedIndex = endpage.options.length-1;
		  }else{
				starpage_selectedIndex = starpage.selectedIndex;
				endpage_selectedIndex = endpage.selectedIndex;
		  }
		  
		  var starpageIndex = starpage.options[starpage_selectedIndex].value
		  var endpageIndex = endpage.options[endpage_selectedIndex].value
		  
		  if(starpageIndex>endpageIndex){
		  	alert('选择的页数无效！');
		  	return;
		  }
		  var begin = (starpageIndex-1)*($id('pageSize').value);
		  var length = (endpageIndex-starpageIndex+1)*($id('pageSize').value);
		  
	      $id('page.begin').value=begin;
	      $id('page.length').value=length;
	   
     
		  var E = getParam();
		  if(callBack){
			  var pageName = "page";
			  if(args.length>2 && args[3]){
			  	pageName = args[3];
			  }else if(dCell.pageName){
			  	pageName = dCell.pageName;
			  }
			  var callBackStr = "";
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
        eventManager.add(window, "load", init);
	</script>
</head>
<body>
<h:form name="viewlist1" action="/tyjg/datadictionary/exportExcel_action.action" method="post">
	<table height="100%" width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="5" bgcolor="#CECECE"></td>
			<td bgcolor="#F3F3F3">
				<form name="submitForm" id="submitForm" method="post">
					<input name="xpath" id="xpath" type="hidden"/>
					<input name="queryAction" id="queryAction" type="hidden"/>
					<input name="pageSize" id="pageSize" type="hidden"/>
					<input name="totalRow" id="totalRow" type="hidden"/>
					<input name="fields" id="fields" type="hidden"/>
					<input name="_eosFlowAction" id="_eosFlowAction" type="hidden" value="exportExcel"/>
	    			<input name="page.begin" id="page.begin" type="hidden" />
	    			<input name="page.length" id="page.length" type="hidden" />
	    			<input name="dictType.dicttypeid" id="dictType.dicttypeid" type="hidden" />
	    			<input name="dictType.dicttypename" id="dictType.dicttypename" type="hidden" />
	    			<input name="params" id="params" type="hidden"/>
	    			<input name="tempFormParamLZ" id="tempFormParamLZ" type="hidden"/>
	    			
	    			<input name="entityType" id="entityType" type="hidden"/>
	    			
					<table width="95%"  border="0" align="center" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="3" align="center" valign="top">
								<table width="100%" border="1" cellpadding="0" cellspacing="1" bordercolor="#CECECE" bgcolor="" style="word-wrap: break-word;word-break: break-all;border-collapse:collapse;" align="center">
									<tr>
										<td colspan="4" align="center">
											 <table border="0" style="" bordercolor="#111111" cellpadding="1" cellspacing="2">
													<tr height="20">
													 <td width="50"></td>
													 <td width="150" align="">原表字段列表</td>
													 <td width="50"></td>
													 <td width="150" align="">导出字段列表</td>
													 <td width="50"></td>
													</tr>
													<tr>
													 <td width="50"></td>
													 <td id="sourceDatasTD" style="" align="center" valign="middle">
														 <select id="Source" name="Source" multiple="true" size="10" class="" style="width:150px;height: 260px" ondblclick="Add(document.getElementById('Source'),document.getElementById('Target'),1)">
														 <select>
													 </td>
													 <td valign="middle" align="center">
														<p style="width:100%" align="center">
															<input class="button" type="button" value="->" onClick="Add(document.getElementById('Source'),document.getElementById('Target'),1)" title="添加">
														</p>
														<p style="width:100%" align="center">
															<input class="button" type="button" value=">>" onClick="AddAll(document.getElementById('Source'),document.getElementById('Target'),1)" title="添加全部">
														</p>
														<p style="width:100%" align="center">
															<input class="button" type="button" value="<-" onClick="Add(document.getElementById('Target'),document.getElementById('Source'),2)" title="删除">
														</p>
														<p style="width:100%" align="center">
															<input class="button" type="button" value="<<" onClick="AddAll(document.getElementById('Target'),document.getElementById('Source'),2)" title="删除全部">
														</p>
													</td>
													<td align="center">
														<select id="Target" name="Target" multiple="multiple" size="10" style="width:150px;height: 260px" ondblclick="Add(document.getElementById('Target'), document.getElementById('Source'),2)">
														</select>
													</td>
													<td width="50" valign="middle" align="center"></td>
												 </tr>
												 <tr>
													 <td colspan="5" align="center">
															<select name='starpage' id='starpage' size='1' style='width:70;height:20;'>
															</select>
															&nbsp;到&nbsp;
															<select name='endpage' id='endpage' size='1' style='width:70;height:20;'>
															</select>
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
								<input style="width:55" name='exportAll' id='exportAll' type='button' class='button' value='导出全部' style='margin-right: 30px;' onClick="exportOrgs(1);">
							</td>
							<td valign="middle" align="center">
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
