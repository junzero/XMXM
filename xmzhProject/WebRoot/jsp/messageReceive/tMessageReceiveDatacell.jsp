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
    <r:datacell id="cell1" queryAction="/messageReceive/tMessageReceiveAction_queryDataGrid.action" submitAction="/messageReceive/tMessageReceiveAction_updateDataGrid.action" width="100%" xpath="com.gotop.messageReceive.model.TMessageReceive">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>      
      <r:field fieldName="recId" label="接收编号">                       
        <h:text/>
      </r:field>
      <r:field fieldName="messageId" label="信息编号">                       
        <h:text/>
      </r:field>
      <r:field fieldName="type" label="1：转发">                       
        <h:text/>
      </r:field>
      <r:field fieldName="receiveObject" label="接收对象（人员、机构、角色）">                       
        <h:text/>
      </r:field>
      <r:field fieldName="objOrgcode" label="用于选择角色发布时，指定发送给机构下某些角色。">                       
        <h:text/>
      </r:field>
      <r:field fieldName="receiveStatus" label="0：未接受
1：已接收">                       
        <h:text/>
      </r:field>
      <r:field fieldName="receiveDate" label="YYYY-MM-DD">                       
        <h:text/>
      </r:field>
      <r:field fieldName="receiveTime" label="RECEIVE_TIME">                       
        <h:text/>
      </r:field>
      <r:field fieldName="submitEmp" label="提交人">                       
        <h:text/>
      </r:field>
      <r:field fieldName="submitTime" label="提交时间">                       
        <h:text/>
      </r:field>
    </r:datacell>
  </body>
</html>
