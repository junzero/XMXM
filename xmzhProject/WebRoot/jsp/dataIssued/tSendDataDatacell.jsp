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
    <r:datacell id="cell1" queryAction="/dataIssued/tSendDataAction_queryDataGrid.action" submitAction="/dataIssued/tSendDataAction_updateDataGrid.action" width="100%" xpath="com.gotop.dataIssued.model.TSendData">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>      
      <r:field fieldName="dsId" label="下发表编号">                       
        <h:text/>
      </r:field>
      <r:field fieldName="sEmpId" label="下发人编号">                       
        <h:text/>
      </r:field>
      <r:field fieldName="flowId" label="流程id">                       
        <h:text/>
      </r:field>
      <r:field fieldName="dataSource" label="数据来源">                       
        <h:text/>
      </r:field>
      <r:field fieldName="daPro" label="数据申请流程">                       
        <h:text/>
      </r:field>
      <r:field fieldName="dataType" label="数据类型">                       
        <h:text/>
      </r:field>
      <r:field fieldName="rvType" label="数据接收类型">                       
        <h:text/>
      </r:field>
      <r:field fieldName="dsTitle" label="数据下发标题">                       
        <h:text/>
      </r:field>
      <r:field fieldName="dsExpl" label="数据下发说明">                       
        <h:text/>
      </r:field>
      <r:field fieldName="timeLimit" label="数据使用时限">                       
        <h:text/>
      </r:field>
      <r:field fieldName="createDate" label="提交日期">                       
        <h:text/>
      </r:field>
      <r:field fieldName="createTime" label="提交日期">                       
        <h:text/>
      </r:field>
      <r:field fieldName="dataUser" label="数据使用人员编号">                       
        <h:text/>
      </r:field>
      <r:field fieldName="username" label="数据使用人员姓名">                       
        <h:text/>
      </r:field>
    </r:datacell>
  </body>
</html>
