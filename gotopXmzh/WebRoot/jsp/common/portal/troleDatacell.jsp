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
  </head>                                                                    
  <body topmargin="0">                                                     
    <r:datacell id="cell1" queryAction="/common/queryDataGrid_trole.action" submitAction="/common/updateDataGrid_trole.action" width="100%" xpath="com.gotop.crm.common.model.portal.Trole">
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
    </r:datacell>
  </body>
</html>
