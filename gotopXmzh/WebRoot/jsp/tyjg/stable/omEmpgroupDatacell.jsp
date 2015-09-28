<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>   
<%@include file="/common/common.jsp"%>                                     
<%@include file="/common/skins/skin1/component.jsp" %>                     
<h:css href="/css/style1/style-custom.css"/>                               
<html>                                                                       
  <head>                                                                     
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/> 
    <title>                                                                  
      GOTOP datacell 整合                                                      
    </title>
  </head>                                                                    
  <body topmargin="0">
    <r:datacell id="cell1" queryAction="/stable/omEmpgroupAction_queryDataGrid.action" submitAction="/stable/omEmpgroupAction_updateDataGrid.action" width="100%" xpath="com.gotop.tyjg.stable.model.OmEmpgroupKey">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>      
      <r:field fieldName="empid" label="EMPID">                       
        <h:text/>
      </r:field>
      <r:field fieldName="groupid" label="GROUPID">                       
        <h:text/>
      </r:field>
    </r:datacell>
    
	<form action="/stable/omEmpgroupAction_queryDataGrid.action" name="test">
		<h:text property="oes"/>
		<input type="submit" value="submit"/>
	</form>
    
  </body>
</html>
