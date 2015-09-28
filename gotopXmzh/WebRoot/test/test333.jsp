<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://eos.primeton.com/tags/webcomp" prefix="w"%>
<%@ taglib uri="http://eos.primeton.com/tags/richweb" prefix="r"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<%@ include file="/common.jsp"%>

		<title>jQuery EasyUI tree</title>
		<script>
		    jQuery(document).ready(function(){
//				$(":button").trigger("click");
//	    			$(":button").get(0).click();
//				alert($(":button:eq(0)").context);
//				alert($(":button").get(0).context);
				
//				$("#images").text("");						
//				$("#images").empty();						
//				$(":images").remove();
//				$(":images").empty();
//				alert(document.getElementById("ff").click());
				jQuery("#ff").click(function(e){
//					alert(e.keyCode);
				});
				jQuery("#ff").click();
				jQuery("#ff").trigger("click");
		    });
		    function ff(e){
		    	alert("-srcElement-"+window.event.srcElement);
		    	alert("-srcElement-"+window.event.type);
		    	alert("-srcElement-"+window.event.target);
		    }
		    function dd(){
		    	jQuery(":button").get(0).click();
		    }
		    
Ajax.prototype.submit = function(A) {
    this.running = true;
    this.encoding = A || this.encoding;
    this.initParam();
    try {
        var B = this.wrapperUrl();
        this.xmlHttp.open(this.method, B, this.async);
        var D = this.wrapperData(),
        E = this;
        if (this.async) this.xmlHttp.onreadystatechange = function() {
            E.onStateChange.apply(E, arguments)
        };
        this.setRequestHeaders();
        $debug("submit url:" + this.url + "\n submit XML:\n" + EOSLog.formatXML(D));
        var C = createXmlDom();
        C.loadXML(D);
        this.xmlHttp.send(C);
        if (!this.async) return this.onStateChange()
    } catch(F) {
        $error("xmlHttp ajax request error.");
        this.running = false
    }
};
		function testAjaxFun(){
			var myAjax = new Ajax("com.primeton.sample.webui.datacell.queryCustomer.biz");
			myAjax.addParam("param", "abc123");
		    myAjax.submit();
			var returnNode =myAjax.getValue("root/data");
			alert('---'+returnNode);
		}    
		</script>
	</head>
	<body>
		<div id="images">1111</div>
		<input id="ff" type="button" value="ff">
		<input type="button" onclick="dd()" value="dd">
		<table>
			<tr>
				<td>
					<div id="date_4533802_container" class="eos-ic-container"><input class="eos-calendar-editor-text" type="text" id="date_4533802_input" validateAttr="type=calendar;allowNull=true" tagformat="yyyy-MM-dd" allowNull="true" readOnly="false"/><img id='date_4533802_button' class="eos-ic-button"/><input type="hidden" name="acoperator/enddate" id="date_4533802_hidden"/></div><script language="javascript"> 
					var _date =new Calendar("date_4533802","yyyy-MM-dd");
					_date.readOnly = false;
					_date.init();
					</script>
				
				</td>
				<td>
					<w:date format="yyyy-MM-dd HH:mm:ss"/><w:time/>
				</td>
				<td>
					<r:datacell entityType="com.primeton.purview.tools.AbfTDbsyb" id="cell1">
					  <r:toolbar/>
					  <r:field fieldName="vXtmc" label="系统名称">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="iDbsybh" label="待办事宜编号">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="vDbsymc" label="待办事宜名称">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="dDbrq" label="待办日期">
					    <w:date/>
					  </r:field>
					  <r:field fieldName="vDbrbh" label="待办人编号">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="vDbzy" label="待办摘要">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="vDbsywebljggxx" label="待办事宜WEB链接">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="cYdbz" label="阅读标志">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="vCdbh" label="待办来源菜单编号">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="vLyrbh" label="来源人编号">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="vLyrdw" label="来源人单位编号">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="vLyrbm" label="来源人部门">
					    <h:text/>
					  </r:field>
					  <r:field fieldName="filler" label="预留项">
					    <h:text/>
					  </r:field>
					</r:datacell>
				</td>
				<td>
					<r:comboSelect allowInput="false" id="ss" nullText="--请选择--">
					</r:comboSelect>
				</td>
			</tr>
		</table>
	
	
	</body>
</html>
