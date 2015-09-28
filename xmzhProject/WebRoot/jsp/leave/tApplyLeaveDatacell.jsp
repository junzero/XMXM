<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>   
<%@include file="/common/common.jsp"%>                                     
<%@include file="/common/skins/skin0/component.jsp" %>                     
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
    <r:datacell id="cell1" queryAction="/leave/tApplyLeaveAction_queryDataGrid.action" submitAction="/leave/tApplyLeaveAction_updateDataGrid.action" width="100%" xpath="com.gotop.leave.model.TApplyLeave">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>      
      <r:field fieldName="lvId" label="LV_ID">                       
        <h:text/>
      </r:field>
      <r:field fieldName="empId" label="EMP_ID">                       
        <h:text/>
      </r:field>
      <r:field fieldName="lvTitle" label="LV_TITLE">                       
        <h:text/>
      </r:field>
      <r:field fieldName="phoneNo" label="PHONE_NO">                       
        <h:text/>
      </r:field>
      <r:field fieldName="lvType" label="LV_TYPE">                       
        <h:text/>
      </r:field>
      <r:field fieldName="lvReason" label="LV_REASON">                       
        <h:text/>
      </r:field>
      <r:field fieldName="lvStart" label="LV_START">                       
        <h:text/>
      </r:field>
      <r:field fieldName="lvDays" label="LV_DAYS">                       
        <h:text/>
      </r:field>
      <r:field fieldName="atime" label="ATIME">                       
        <h:text/>
      </r:field>
    </r:datacell>
  </body>
</html>
