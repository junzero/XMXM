<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>流程节点别名配置</title>
</head>
<body topmargin="0" leftmargin="0">
<form id="qfHavenot">
<h:hidden id="definitionId" property="taskAssgineeDto.definitionId" /> 
<h:hidden id="taskName" property="taskAssgineeDto.taskName" />
</form> 
		
	<r:datacell id="cell1" queryAction="/jbpm/jbpmDemoAction_queryTaskNameConfig.action" submitAction="/jbpm/jbpmDemoAction_saveTaskNameConfig.action" width="100%" height="250" xpath="com.gotop.jbpm.dto.ActivityDto"  entityType="com.gotop.jbpm.dto.ActivityDto" initParamFunc="initPlanCell" paramFormId="qfHavenot" >
      <r:toolbar location="bottom" tools="edit:save"/>      
      <r:field fieldName="beforeName" label="开始节点名称">                       
      </r:field>
      <r:field fieldName="destinationName" label="结束节点名称">                       
      </r:field>
      <r:field fieldName="actShowName" label="显示名称" >       
      <h:text id="actShowName" validateAttr="maxLength=255;"/>                
      </r:field>
    </r:datacell>
<script type="text/javascript">
function initPlanCell(){
	var queryCond="";
	queryCond += "<definitionId>"+ $("#definitionId").val()+"</definitionId>";
	queryCond += "<taskName>"+ $("#taskName").val()+"</taskName>";
	return queryCond;
}
</script>
</body>
</html>