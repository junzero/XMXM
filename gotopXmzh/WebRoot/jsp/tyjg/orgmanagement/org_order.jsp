<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      机构排序
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<h:script src="/js/tyjg/orgmanagement/orgorder.js"/>
  </head>
  <body>
  	<table align="center" border="0" width="100%" class="EOS_table">
  		<h:hidden property="tomorganization.orgId" id="tomorganization.orgId"/>
	      <tr>
	        <th align="center">
	          机构列表(可多选)
	        </th>
	      </tr>
	      <tr>
	      	<td align="center">
				<select id=orderSel style="width: 291px;" size=24 multiple="multiple"><!--由你读出来的记录的个数决定-->
					<l:iterate property="orgList" id="id1">
						<option value="<b:write iterateId="id1" property="orgId"/>">
							<b:write iterateId="id1" property="orgName"/>&nbsp;(<b:write iterateId="id1" property="orgCode"/>)
						</option>
					</l:iterate>
				</select>
	        </td>
	      </tr>
	      <tr>
	      	<td align="center">
				<!--以上这段换成从数据库里面读的具体内容-->
				<button class="button" onclick="up(orderSel,this)">上移↑</button>
				<button class="button" onclick="saveOrder(orderSel,this)">保存顺序</button>
				<button class="button" onclick="down(orderSel,this)">下移↓</button>
	        </td>
	      </tr>
	  </table>
	  
  </body>
</html>
