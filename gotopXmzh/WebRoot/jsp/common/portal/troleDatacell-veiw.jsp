<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>   
<%@include file="/common/common.jsp"%>                                     
<%@include file="/common/skins/skin1/component.jsp" %>                     
<h:css href="/css/style1/style-custom.css"/>                               
<%--                                                                         
- Author(s): abator                                                          
- Date: 2011-10-05 16:16:53                                                  
- Description:                                                               
--%>                                                                         
<html>                                                                       
  <head>                                                                     
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/> 
    <title>                                                                  
      角色资源                                                    
    </title>       
    <script type="text/javascript">
        function init(){
            var arg = window["dialogArguments"];
            var id = arg[0];
            $id("rid").innerHTML=id;
        }
        function ok(){
        	var row = $id('cell1').getActiveEntity();
        	if(row==null){
        		window['returnValue'] = window["dialogArguments"];
        	}
            // 定义窗口关闭时的返回值
            window['returnValue'] = [row.getProperty("rid"),row.getProperty("rid")];
            window.closeD();
        }
//        window.onload = function(){
//        	init();
//        }
        eventManager.add(window, "load", init);
    </script>
  </head>                                                                    
  <body topmargin="0">    
  当前选中为  id:<b><span id="rid"></span></b><br/><span ondblclick="ok()">
    <r:datacell id="cell1" queryAction="/common/queryDataGrid_trole.action" submitAction="/common/updateDataGrid_trole.action" 
    			width="100%" xpath="com.gotop.crm.common.model.portal.Trole" readonly="true">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>      
      <r:field fieldName="rid" label="RID">                       
        <h:text/>
      </r:field>
      <r:field fieldName="rolename" label="ROLENAME">                       
        <h:text/>
      </r:field>
      <r:field fieldName="note" label="NOTE">                       
        <h:text/>
      </r:field>
      <r:field fieldName="jszt" label="JSZT">                       
        <h:text/>
      </r:field>
      <r:field fieldName="yyzt" label="YYZT">                       
        <h:text/>
      </r:field>
      <r:field fieldName="orgid" label="ORGID">                       
        <h:text/>
      </r:field>
    </r:datacell><br/><br/>
    双击返回
  </span>      
  </body>
</html>
