<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>   
<%@include file="/common/common.jsp"%>                                     
<%@include file="/common/skins/skin1/component.jsp" %>                     
<h:css href="/css/style1/style-custom.css"/>                               
<%--                                                                         
- Author(s): gotop                                                           
- Date: 2012-10-05 16:16:53                                                  
- Description:                                                               
--%>                                                                         
<html>                                                                       
  <head>                                                                     
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/> 
    <title>                                                                  
      GOTOP datacell 整合                                                      
    </title>                                                                 
  </head>                                                                    
  <body topmargin="0">                                                     
    <r:datacell id="cell1" queryAction="/stable/omGroupmemberAction_queryDataGrid.action" submitAction="/stable/omGroupmemberAction_updateDataGrid.action" width="100%" xpath="com.gotop.tyjg.stable.model.OmGroupmemberKey">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>      
      <r:field fieldName="flag" label="属性">                       
        <h:text/>
      </r:field>
      <r:field fieldName="groupid" label="工作组编号">                       
        <h:text/>
      </r:field>
      <r:field fieldName="memberid" label="成员编号">                       
        <h:text/>
      </r:field>
    </r:datacell>
	<input type="button" value="导出" onclick="XlsFun()"/>
	<input type="button" value="打印" onclick="PritFun()"/>
	<script type="text/javascript">
		function XlsFun(){
			var argument=new Array(3);
			argument[0] = "cell1";//datacell的ID
			argument[1] = "pageBack";//回调函数 一般用于处理分页信息
			argument[2] = window;//这个为必须，table所在窗口
			showModalCenter("/common/jsp/exportExcel.jsp",argument,null,600,360,"导出字段选择");
		}
		function PritFun(){
			var argument=new Array(3);
			argument[0] = "cell1";//datacell的ID
			argument[1] = "pageBack";//回调函数 一般用于处理分页信息
			argument[2] = window;//这个为必须，table所在窗口
			showModalCenter("/common/jsp/printApplet.jsp",argument,null,600,360,"导出字段选择");
		}
	</script>
  </body>
</html>
