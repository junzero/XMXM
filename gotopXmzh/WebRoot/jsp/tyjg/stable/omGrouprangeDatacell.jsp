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
    <r:datacell id="cell1" queryAction="/stable/omGrouprangeAction_queryDataGrid.action" submitAction="/stable/omGrouprangeAction_updateDataGrid.action" width="100%" xpath="com.gotop.tyjg.stable.model.OmGrouprange">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>      
      <r:field fieldName="flag" label="属性">                       
        <h:text/>
      </r:field>
      <r:field fieldName="grangeid" label="范围编号">                       
        <h:text/>
      </r:field>
      <r:field fieldName="groupid" label="工作组编号">                       
        <h:text/>
      </r:field>
    </r:datacell>
  </body>
</html>
