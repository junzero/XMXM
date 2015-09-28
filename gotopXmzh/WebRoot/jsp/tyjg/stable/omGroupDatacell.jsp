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
    <r:datacell id="cell1" queryAction="/stable/omGroupAction_queryDataGrid.action" submitAction="/stable/omGroupAction_updateDataGrid.action" width="100%" xpath="com.gotop.tyjg.stable.model.OmGroup">
      <r:toolbar location="bottom" tools="nav,edit,pagesize,info"/>      
      <r:field fieldName="groupid" label="GROUPID">                       
        <h:text/>
      </r:field>
      <r:field fieldName="orgid" label="ORGID">                       
        <h:text/>
      </r:field>
      <r:field fieldName="parentgroupid" label="PARENTGROUPID">                       
        <h:text/>
      </r:field>
      <r:field fieldName="grouplevel" label="GROUPLEVEL">                       
        <h:text/>
      </r:field>
      <r:field fieldName="groupname" label="GROUPNAME">                       
        <h:text/>
      </r:field>
      <r:field fieldName="groupdesc" label="GROUPDESC">                       
        <h:text/>
      </r:field>
      <r:field fieldName="grouptype" label="GROUPTYPE">                       
        <h:text/>
      </r:field>
      <r:field fieldName="groupseq" label="GROUPSEQ">                       
        <h:text/>
      </r:field>
      <r:field fieldName="startdate" label="STARTDATE">                       
        <h:text/>
      </r:field>
      <r:field fieldName="enddate" label="ENDDATE">                       
        <h:text/>
      </r:field>
      <r:field fieldName="groupstatus" label="GROUPSTATUS">                       
        <h:text/>
      </r:field>
      <r:field fieldName="manager" label="MANAGER">                       
        <h:text/>
      </r:field>
      <r:field fieldName="createtime" label="CREATETIME">                       
        <h:text/>
      </r:field>
      <r:field fieldName="lastupdate" label="LASTUPDATE">                       
        <h:text/>
      </r:field>
      <r:field fieldName="updator" label="UPDATOR">                       
        <h:text/>
      </r:field>
      <r:field fieldName="isleaf" label="ISLEAF">                       
        <h:text/>
      </r:field>
      <r:field fieldName="subcount" label="SUBCOUNT">                       
        <h:text/>
      </r:field>
      <r:field fieldName="createuserid" label="CREATEUSERID">                       
        <h:text/>
      </r:field>
    </r:datacell>
  </body>
</html>
