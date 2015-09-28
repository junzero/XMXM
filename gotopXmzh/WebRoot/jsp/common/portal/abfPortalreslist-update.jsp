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
      EOS datacell 整合                                                      
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
            window['returnValue'] = [row.getProperty("id"),row.getProperty("id")];
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
    <r:datacell id="cell1" queryAction="/common/queryDataGrid_abfPortalreslist.action" submitAction="/common/updateDataGrid_abfPortalreslist.action" width="100%" xpath="com.gotop.crm.common.model.portal.AbfPortalreslist">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>      
      <r:field fieldName="id" label="ID">                       
      </r:field>
      <r:field fieldName="beanid" label="在spring中配置bean id">                       
        <h:text/>
      </r:field>
      <r:field fieldName="bizfun" label="逻辑方法">                       
        <h:text/>
      </r:field>
      <r:field fieldName="sqlid" label="ibatis SQL id" width="200">                       
        <h:text/>
      </r:field>
      <r:field fieldName="countid" label="ibatis 统计id" width="200">                       
        <h:text/>
      </r:field>
      <r:field fieldName="ptype" label="那种查询方式 0、biz 1、sqlid">                       
        <h:text/>
      </r:field>
    </r:datacell><br/>
    双击返回  </span>   
  </body>
</html>
