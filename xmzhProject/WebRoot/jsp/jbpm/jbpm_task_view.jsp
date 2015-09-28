<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h:css href="/css/style1/style-custom.css" />
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  
  <body>
<img src="/jbpm/jbpmDemoAction_viewTaskProcess.action?executionId=<s:property value='executionId'/>">
  <s:if test="activityCoordinates != null">
  	 <div style="position:absolute;border:1px solid red; left:<s:property value='activityCoordinates.x'/>px; top:<s:property value='activityCoordinates.y'/>px; width:<s:property value='activityCoordinates.width'/>px;height:<s:property value='activityCoordinates.height'/>px;"></div>
  </s:if>  
 <s:if test="activityHisCoordinates != null">
      <s:iterator value="activityHisCoordinates" id="a" >  
       <div  style="position:absolute;border:3px solid blue;left:${a.x }px;top:${a.y }px;width:${a.width }px;height:${a.height}px;"></div>  
       </s:iterator>  
  </s:if>  
 </body>
  <script type="text/javascript">
  </script>
</html>
