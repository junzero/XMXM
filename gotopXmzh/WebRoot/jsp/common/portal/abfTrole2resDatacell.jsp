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
      角色-资源维护                                                     
    </title>                                                                 
  </head>                                                                    
  <body topmargin="0">                                                     
    <r:datacell id="cell1" queryAction="/common/queryDataGrid_abfTrole2res.action" submitAction="/common/updateDataGrid_abfTrole2res.action" width="100%" xpath="com.gotop.crm.common.model.portal.AbfTrole2res">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>      
      <r:field fieldName="resid" label="RESID">                       
        <h:text/>
      </r:field>
      <r:field fieldName="rid" label="RID">                       
        <h:text/>
      </r:field>
      <r:field fieldName="sqsj" label="SQSJ">                       
        <h:text/>
      </r:field>
      <r:field fieldName="sqr" label="SQR">                       
        <h:text/>
      </r:field>
    </r:datacell>
  </body>
</html>
