<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>人员树排序</title>
<%
	String iOrgid =request.getParameter("iOrgid");
%>
<h:css href="/css/style1/style-custom.css"/>
</head>
	<body>
		<h:hidden property="iOrgid" value="<%=iOrgid%>" />
	    <table align="center" border="0" width="100%" class="EOS_table">
	      <tr>
	        <th align="center">
	          人员列表(可多选)
	        </th>
	      </tr>
	      <tr>
	      	<td align="center">
				<select id=orderSel style="width: 291px;" size=21 multiple="multiple"><!--由你读出来的记录的个数决定-->
					<l:iterate property="oaEmp" id="id1" scope="r">
						<option value="<b:write iterateId="id1" property="empid"/>">
							<b:write iterateId="id1" property="empname"/>&nbsp;(<b:write iterateId="id1" property="empcode"/>)
						</option>
					</l:iterate>
				</select>
	        </td>
	      </tr>
	      <tr>
	      	<td align="center">
				<!--以上这段换成从数据库里面读的具体内容-->
				<input type="button" class="button" onclick="up(orderSel,this);" value="上移↑"/>
				<input type="button" class="button" onclick="saveOrder(orderSel,this);" value="保存顺序"/>
				<input type="button" class="button" onclick="down(orderSel,this);" value="下移↓"/>
	        </td>
	      </tr>
	  </table>

			<!--显示函数最为你最后提交并处理的方法（比如跳转到一个具体的处理action中）-->
			
			<script>
				function up(obj,but){
					but.disabled=true;
					var options = obj.options;
					for(var i=1;i<options.length;i++){
						if(options[i].selected){
							obj.options[i].swapNode(obj.options[i-1])//向上移动
						}
					}
					but.disabled=false;
				}
				function down(obj,but){
					but.disabled=true;
					var options = obj.options;
					for(var i=options.length-2;i>=0;i--){
						if(options[i].selected){
							obj.options[i].swapNode(obj.options[i+1])//向上移动
						}
					}
					but.disabled=false;
				}
				/**
				*	保存显示序列
				*/
				function saveOrder(obj,but){
					 //判断是否应用综合管理平台
		    		var orgid=$name("iOrgid").value;
					var options = obj.options;
					var submitXml = "";
					var n = (""+options.length).length;
					for(var i=0;i<options.length;i++){
						var pad = i < Math.pow(10, n-1) ? new Array(n-(''+i).length+1).join('0') + i : i;
			    		submitXml += "<DOGroup __type='com.gotop.tyjg.empMngr.model.EmpUnOrgBean'><orgid>"+orgid+"</orgid><empid>"+options[i].value+"</empid><displayorder>"+pad+"</displayorder></DOGroup>";
					}
	//				var url="/empMngr/empMngrStruts/upDataGridOrder_empMngr.action";
					var url="/empMngr/upDataGridOrder_empMngr.action";
					var myAjax = new Ajax(url);
					myAjax.submitXML(submitXml.toString());
			//		var returnNode = myAjax.getResponseXMLDom();
					var info =myAjax.getText();
				    if( info == '1' ) {
				        alert('保存成功！');
				        window.closeD();
				    } 
				    else {
				        alert( '保存失败！');
				    }
					
				}
				
				
				function pad(num, n) {
				  return Array(n>num?(n-(''+num).length+1):0).join(0)+num;
				}
			</script>
	</body>
</html>