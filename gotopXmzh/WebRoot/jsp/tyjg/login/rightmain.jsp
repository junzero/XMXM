<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<link  type="text/css" rel="stylesheet"  href="/css/style1/style-custom.css">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link  type="text/css" rel="stylesheet"  href="/common/skins/skin0/theme/public.css">
    <script type="text/javascript" src="/js/commonUtil.js"></script>
    <title>
      内容页
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<style>	
.divcss{
 height:165px;
 border:1px solid #AEC2CD;
 overflow:hidden; 
}
	</style>
  </head>
  <body style="margin: 0px;">
<div>
  <div class="">
	<div class=" h30 pt5 lineS1_b lh25 f16 tabTitle tc c2">
		<span class="w80 h30 lh30 bc5 c1 fl fb">待阅信息</span>
	</div>
 <div class="divcss">
 <table cellpadding="0" cellspacing="0" width="100%" class="tableBox" id="dbxx">
    <thead>
 	<tr>
 		<th nowrap="nowrap" >信息栏目</th>
 		<th nowrap="nowrap" >信息标题</th>
 		<th nowrap="nowrap" >发布部门</th>
 		<th nowrap="nowrap" >发布人</th>
 		<th nowrap="nowrap" >发布时间</th>
 		<th nowrap="nowrap" >附件</th>
 	</tr>
 	</thead>
 	<tbody>
 	</tbody>
 </table> 		
  </div>
 </div>
  <div class="">
	<div class=" h30 pt5 lineS1_b lh25 f16 tabTitle tc c2">
		<span class="w80 h30 lh30 bc5 c1 fl fb">待办事件</span>
	</div>
<div class="divcss">
<table align="center" cellpadding="0" cellspacing="0" width="100%" class="tableBox" id="dbsj">
  <thead>
 	<tr>
 		<!-- <th nowrap="nowrap" >工作事项类别</th> -->
 		<th nowrap="nowrap" >流程名称</th>
 		<th nowrap="nowrap" >标题</th>
 		<th nowrap="nowrap" >发起部门</th>
 		<th nowrap="nowrap" >上一处理人</th>
 		<th nowrap="nowrap" >提交时间</th>
 	</tr> 
  </thead>
  <tbody>
  </tbody> 	
  </table>
 </div>
 </div>
 <!-- 20140904新增显示窗口 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr><td width="49%" valign="top">
 <div class="">
	<div class=" h30 pt5 lineS1_b lh25 f16 tabTitle tc c2">
		<span class="w80 h30 lh30 bc5 c1 fl fb">督办提醒</span>
	</div>
<div class="divcss">
 <table align="center" cellpadding="0" cellspacing="0" width="100%"  class="tableBox" id="dbtx">
   <thead>
 	<tr>
 		<th nowrap="nowrap" >标题</th>
 		<th nowrap="nowrap" >完成时限</th>
 	</tr> 
 	</thead>
 	<tbody></tbody>
 	</table>
 </div>
 </div>
 </td>
 <td class="w10"> &nbsp;</td>
<td width="50%" valign="top">
 <div class="">
	<div class=" h30 pt5 lineS1_b lh25 f16 tabTitle tc c2">
		<span class="w80 h30 lh30 bc5 c1 fl fb">会议通知</span>
	</div>
	<div class="divcss">
 <table align="center" cellpadding="0" cellspacing="0" width="100%" class="tableBox" id="mttx">
 <thead>
 	<tr>
 		<th nowrap="nowrap" >标题</th>
 		<th nowrap="nowrap" >发起部门</th>
 		<th nowrap="nowrap" >会议时间</th>
 	</tr> 
  </thead>
  <tbody>
  
  </tbody>	
  </table>
 </div>
 </div>
 </td>
 </tr>
 </table>
  </div>
  <script>
  //*载入待办信息
  function loadDbxx(){
	 var dbxx= $("#dbxx") ;
	 var tbody=$("#dbxx tbody") ;
	 tbody.html("");
	 $.ajax({
	        url: "/home/homeInfoAction_getHomeInfo.action",
	        async: false,
	        type: "POST",
	        dataType: "json",
	        timeout: 60000,
			success : function(json){
				var dataObj=eval("("+json+")");
				if(json=='[]'){
					 var tr="<table><tr id=tr>"; 
					 var td="<td  colspan='6' style='color:red'>无待办信息</td>";
					 tr=tr+td;
					 var tr1=$(tr).find("tr");   
					 tbody.append(tr1);
				}else{
					    var pg="";
						$.each(dataObj,function(idx,item){
							var tr="<table><tr id=trdbxx"+idx+">"; 
							var td="";
							td=td+"<td nowrap='nowrap' class='tc  h22    lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].typeName+"</td>";
							td=td+"<td nowrap='nowrap' class='tl  h22    lh25 overh pl10 pr10 lineD_b'><a href='javascript:void(0)' class='f1' onclick=\"doLink('"+dataObj[idx].messageId+"','"+dataObj[idx].status+"','"+dataObj[idx].messageType+"','"+dataObj[idx].flowId+"')\"><div class='w400 slh'><nobr>"+dataObj[idx].messageTitle+"</nobr></div></a></td>";
							td=td+"<td nowrap='nowrap' class='tc  h22    lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].orgname+"</td>";
							td=td+"<td nowrap='nowrap' class='tc  h22    lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].empname+"</td>";
							td=td+"<td nowrap='nowrap' class='tc  h22    lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].createDate+"</td>";
							if(dataObj[idx].existsFile=="1"){
								td=td+"<td class='tc  h22    lh25 overh pl10 pr10 lineD_b'><img src='/images/filepng.png' style='width:20px;height:20px;'/></td>";
							}else{
							    td=td+"<td class='tc  h22    lh25 overh pl10 pr10 lineD_b'>&nbsp</td>";
                            }
							td=td+"</tr>";
							if(dataObj.length==4){
								//增加更多信息
								pg="<table><tr><td colspan='6' style='text-align:right;height: 22px;'><a href='/messagePublish/tMessagePublishAction_queryTMessagePublishList.action?message.status=0'>更多...</a></td></tr></table>"	;				
								}
							
							td=td+"</table>";
							tr=tr+td;
							var tr1=$(tr).find("tr");   
							tbody.append(tr1);	
						});
						
						var pg1=$(pg).find("tr");   
						tbody.append(pg1);
						
					}
			},
			error : function(json){
				 
			   }
	 });
	  }
  //*载入待办事件
  function loadDbsj(){
		 var dbsj= $("#dbsj") ;
		 var dbsjtbody=$("#dbsj tbody") ;
		 dbsjtbody.html("");
		 $.ajax({
		        url: "/home/homeInfoAction_getSupAssigneesList.action",
		        async: false,
		        type: "POST",
		        dataType: "json",
		        timeout: 60000,
				success : function(json){
					var dataObj=eval("("+json+")");
					if(json=='[]'){
						 var tr="<table><tr id=tr>"; 
						 var td="<td  colspan='5' style='color:red'>无待办事件</td>";
						 tr=tr+td;
						 var tr1=$(tr).find("tr");   
						 dbsjtbody.append(tr1);
					}else{
						  var pg="";
					 	$.each(dataObj,function(idx,item){
								var tr="<table><tr id=tr>"; 
								var td="";
								td=td+"<td nowrap='nowrap' class='tc  h22 lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].processName+"</td>";
								td=td+"<td nowrap='nowrap' class='tl  h22   lh25 overh pl10 pr10 lineD_b'><a href='javascript:void(0)' class='f1' onclick=\"javascript:viewBussinessDetail('"+dataObj[idx].businessKey+"','"+dataObj[idx].nextTaskId+"','"+dataObj[idx].executionId+"','"+dataObj[idx].businessType+"','"+dataObj[idx].preTaskAssingee+"','"+dataObj[idx].assignee+"','"+dataObj[idx].processTaskAssigneeId+"','"+dataObj[idx].parentId+"','"+dataObj[idx].isChild+"')\"><div class='w400 slh'><nobr>"+dataObj[idx].businessTitle+"</nobr></div></a></td>";
								td=td+"<td nowrap='nowrap' class='tc  h22    lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].preTaskOrgName+"</td>";
								td=td+"<td nowrap='nowrap' class='tc  h22    lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].preTaskAssingeeName+"</td>";
								td=td+"<td nowrap='nowrap' class='tc  h22    lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].preTaskTime+"</td>";
								td=td+"</tr>";								
								td=td+"</table>";

								tr=tr+td;
								var tr1=$(tr).find("tr");   
								dbsjtbody.append(tr1);	
							});
						if(dataObj.length==4){
							pg="<table><tr><td colspan='5' style='text-align:right;height: 22px;'><a href='/jbpm/tProcessTaskAssigneeAction_queryMyToDoTasksList.action'>更多...</a></td></tr></table>"	;				
							}
						var pg1=$(pg).find("tr");   
						dbsjtbody.append(pg1);
						}
				},
				error : function(json){
					 
				   }
		 });
	
	  }
  //*载入督办提醒
  function loadDbtx(){

	  var dbsj= $("#dbtx") ;
		 var dbsjtbody=$("#dbtx tbody") ;
		 dbsjtbody.html("");
		 $.ajax({
		        url: "/home/homeInfoAction_getSuperviseList.action",
		        async: false,
		        type: "POST",
		        dataType: "json",
		        timeout: 60000,
				success : function(json){
					var dataObj=eval("("+json+")");
					if(json=='[]'){
						 var tr="<table><tr id=tr>"; 
						 var td="<td  colspan='2' style='color:red'>无督办事件</td>";
						 tr=tr+td;
						 var tr1=$(tr).find("tr");   
						 dbsjtbody.append(tr1);
					}else{
						  var pg="";
					 	$.each(dataObj,function(idx,item){
								var tr="<table><tr id=tr>"; 
								var td="";
								td=td+"<td nowrap='nowrap' class='tl  h22   lh25 overh pl10 pr10 lineD_b'><a href='javascript:void(0)' class='f1' onclick=\"javascript:viewBussinessDetail('"+dataObj[idx].businessKey+"','"+dataObj[idx].nextTaskId+"','"+dataObj[idx].executionId+"','"+dataObj[idx].businessType+"','','"+dataObj[idx].assignee+"','"+dataObj[idx].processTaskAssigneeId+"','"+dataObj[idx].parentId+"','"+dataObj[idx].isChild+"')\"><div class='w200 slh'><nobr>"+dataObj[idx].businessTitle+"</nobr></div></a></td>";
								td=td+"<td nowrap='nowrap' class='tc  h22    lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].preTaskTime+"</td>";
								td=td+"</tr>";								
								td=td+"</table>";
								tr=tr+td;
								var tr1=$(tr).find("tr");   
								dbsjtbody.append(tr1);	
							});
						if(dataObj.length==4){
							pg="<table><tr><td colspan='2' style='text-align:right;height: 22px;'><a href='/jbpm/tProcessTaskAssigneeAction_queryMySupTasksList.action'>更多...</a></td></tr></table>"	;				
							}
						var pg1=$(pg).find("tr");   
						dbsjtbody.append(pg1);
						}
				},
				error : function(json){
					 
				   }
		 });
	
	  }
  //*载入会议提醒
  function loadMttx(){
	  var dbsj= $("#mttx") ;
		 var dbsjtbody=$("#mttx tbody") ;
		 dbsjtbody.html("");
		 $.ajax({
		        url: "/home/homeInfoAction_getMettingLists.action",
		        async: false,
		        type: "POST",
		        dataType: "json",
		        timeout: 60000,
				success : function(json){
					var dataObj=eval("("+json+")");
					if(json=='[]'){
						 var tr="<table><tr id=tr>"; 
						 var td="<td  colspan='3' style='color:red'>无会议通知</td>";
						 tr=tr+td;
						 var tr1=$(tr).find("tr");   
						 dbsjtbody.append(tr1);
					}else{
						  var pg="";
					 	$.each(dataObj,function(idx,item){
								var tr="<table><tr id=tr>"; 
								var td="";
								td=td+"<td nowrap='nowrap' class='tl  h22   lh25 overh pl10 pr10 lineD_b'><a href='javascript:void(0)' class='f1' onclick=\"javascript:viewMettingDetail('"+dataObj[idx].recId+"','"+dataObj[idx].status+"','"+dataObj[idx].mettingId+"')\"><div class='w200 slh'><nobr>"+dataObj[idx].title+"</nobr></div></a></td>";
								td=td+"<td nowrap='nowrap' class='tc  h22    lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].orgname+"</td>";
								td=td+"<td nowrap='nowrap' class='tc  h22    lh25 overh pl10 pr10 lineD_b'>"+dataObj[idx].mettingTime+"</td>";
								td=td+"</tr>";								
								td=td+"</table>";
								tr=tr+td;
								var tr1=$(tr).find("tr");   
								dbsjtbody.append(tr1);	
							});
						if(dataObj.length==4){
							pg="<table><tr><td colspan='3' style='text-align:right;height: 22px;'><a href='/mettingApply/tMettingApplyAction_queryMettingApplyList.action'>更多...</a></td></tr></table>"	;				
							}
						var pg1=$(pg).find("tr");   
						dbsjtbody.append(pg1);
						}
				},
				error : function(json){
					 
				   }
		 });
	
	  }
  function callBack(){	 
	  maskAllFrame();
		loadDbxx();
		loadDbsj();
		loadDbtx();
		loadMttx();
	  hideProgressBar("doAction");
	  unMaskTop();
	  }
  /**
	* 2014.9.1 改为弹出框'<b:write iterateId="ids" property="processTaskAssigneeId"/>'
	**/
  function viewBussinessDetail(businessKey,nextTaskId,executionId,businessType,preTaskAssingee,assignee,processTaskAssigneeId,parentId,isChild){
	  var strUrl = "/jbpm/jbpmDemoAction_handle.action?taskAssgineeDto.businessKey="+businessKey+"&taskAssgineeDto.nextTaskId="+nextTaskId+"&taskAssgineeDto.executionId="+executionId+"&taskAssgineeDto.businessType="+businessType+"&taskAssgineeDto.taskAssingee="+preTaskAssingee+"&taskAssgineeDto.preTaskAssingee="+assignee+"&taskAssgineeDto.processTaskAssigneeId="+processTaskAssigneeId+"&taskAssgineeDto.parentId="+parentId+"&taskAssgineeDto.isChild="+isChild;
		showModalCenter(encodeURI(strUrl), null, callBack, clientX*0.8, clientY*0.65, '详情');
	  }
  function viewMettingDetail(recId,status,mettingId){
	  var strUrl = "/mettingApply/tMettingApplyAction_info.action?meet.recId="+recId+"&meet.status="+status+"&meet.mettingId="+mettingId;
		showModalCenter(encodeURI(strUrl), null, callBack, clientX*0.8, clientY*0.65, '会议详情');
	  }
  </script>
	<script>
	$(document).ready(function(){	
		maskAllFrame();	
		loadDbxx();
		loadDbsj();
		loadDbtx();
		loadMttx();
		  hideProgressBar("doAction");
		  unMaskTop();

	});
	function maskAllFrame(){		
		showProgressBar("doAction","<img src='<%=request.getContextPath()%>/common/images/loading.gif'> 正在加载数据，请稍后");
		maskTop();
	} 
	</script> 
	
   </body>
</html>
