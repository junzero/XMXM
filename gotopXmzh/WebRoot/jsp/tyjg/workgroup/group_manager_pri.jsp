<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<html>
<head>
<title><b:message key="workgroupManager_l_title_group_manager"/></title> <!-- 工作组管理 -->
</head>
<body topmargin="0" leftmargin="0">
<table align="left" border="0" id="tableID" height="100%" width="100%" cellpadding="0" cellspacing="0">
  <tr>
    <td vAlign="top" width="25%">
   		<iframe name="groupTree" id="groupTree" align="top" scrolling="no" 
   			src="org.gocom.abframe.org.workgroup.WorkgroupManager.flow?_eosFlowAction=groupTreePri" style="width:100%" frameBorder=0>
		</iframe>		
    </td>
    <td vAlign="top" width="75%">
       <IFRAME  name="groupTabs" id="groupTabs" style="width:100%"  frameBorder="0"
	   		scrolling="auto" src="org.gocom.abframe.org.workgroup.priworkgroupManager.flow?_eosFlowAction=grouptabs&grouptype=personal"></iframe> 
    </td>
  </tr>
</table>
 
	<SCRIPT language="javascript">
		document.getElementById("groupTree").height = document.body.clientHeight;
		document.getElementById("groupTabs").height = document.body.clientHeight;
		$id("tableID").style.height = document.body.clientHeight;
	</SCRIPT>
	
</body>
</html>