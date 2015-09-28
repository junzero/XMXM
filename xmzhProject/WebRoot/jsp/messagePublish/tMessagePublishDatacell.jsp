<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="/js/commonUtil.js"></script>
    <title>
     信息发布
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <style>
  	.command_sort_area .h3{
  		float: left;
  	}
  	.command_sort_area .h4{
  		float: right;
  	}
  </style>
  <body style="margin: 0px;">
  	<e:datasource name="message" type="bean" path="com.gotop.messagePublish.model.TMessagePublish"/>
  	<queryform id="8cf82903-39b3-4dd6-9c1e-ffaff96fa1af">
		<h:form name="form1" action="/messagePublish/tMessagePublishAction_queryTMessagePublishList.action"
			checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
			<w:panel id="panel1" width="100%" title="信息查询" expand="true">
			<table align="center" border="0" width="100%" class="form_table">
					<tr>
						<td class="form_label" align="right">信息标题：</td>
						<td colspan="1">
							<h:text  property="message.messageTitle" style="width:132px;" />
						</td>
						<td class="form_label" align="right">信息类型：</td>
						<td colspan="1">
						<d:select dictTypeId="ZHPT_MESSAGE_TYPE" property="message.messageType" nullLabel="请选择"></d:select>
						</td>
					 </tr>
					 <tr>
				       <td class="form_label" align="right">发布人：</td>
				     	<td colspan="1">
				     		  <h:text id="empname" property="empname" readonly="true" />
			                  <h:hidden id="empid" property="message.createEmpid" />
			                  <a href="#" onclick="open_newyw_tree_fun2()">选择</a>
				     	</td>
				        <td class="form_label" align="right">发布部门：</td>
				            <td colspan="1">
				              <h:text id="orgname" property="message.orgname"   readonly="true" />
			                  <h:hidden id="orgcode" property="message.orgcode" />
			                  <h:hidden id="orgid" property="message.objName" />
			  <a href="#" onclick="open_newyw_tree_fun1();">选择</a>
				            </td>
                    </tr>
                     <tr>
						<td class="form_label" align="right">发布开始时间：</td>
						<td colspan="1">
						<w:date  id="createDate" submitFormat="yyyyMMdd" format="yyyy-MM-dd" readonly="true" property="message.createDate" />
						</td>
						<td class="form_label" align="right">发布结束时间：</td>
						<td colspan="1">
						<w:date  id="createDate1" submitFormat="yyyyMMdd" format="yyyy-MM-dd" readonly="true" property="message.createDate1" />
						</td>
					</tr>
					<tr>
						<td class="form_label" align="right">状态：</td>
						<td colspan="1">
							<select id="tstatus" name="message.status" style="width:80px;">
							<option value="">请选择</option>
							<option value="0">未阅</option>
							<option value="1">已阅</option>
							</select>
						</td>
					 </tr>
					<tr class="form_bottom">
						<td colspan="4" class="form_bottom">
						    <b:message key="l_display_per_page"></b:message>
					        <h:text size="2" property="page.length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
					        <input type="hidden" name="page.begin" value="0">
					        <input type="hidden" name="page.isCount" value="true">
							<input id="querys" type="submit" value="查 询" class="button">
							<input type="button" value="清 空" class="button" onclick="clears();"></td>
					</tr>
				</table>
			</w:panel>
		</h:form>
	</queryform>
	<DIV class="divList">
	 <h:form name="data_form" action="/messagePublish/tMessagePublishAction_queryTMessagePublishList.action" method="post">
            <h:hiddendata property="message"/>
            <h:hidden property="page.begin"/>
		    <h:hidden property="page.length"/>
		    <h:hidden property="page.count"/>
		    <h:hidden property="page.isCount"/>
           <table align="center" border="0" width="100%" class="EOS_table">
           <tr>
		       <td colspan="23" class="eos-panel-title">&nbsp;查询结果</td>
		      </tr>
           <tr>
	            <th align="center" nowrap="nowrap">
	              <l:greaterThan property="page.count" targetValue="0" compareType="number">
	                 <h:checkbox id="xuanze" onclick="allItem();"/>
	              </l:greaterThan>
	              <!--
	              <b:message key="l_select"></b:message>
	              -->
	            </th>
	            <th nowrap="nowrap"> 
	                                          信息标题
	            </th>
	            <th nowrap="nowrap">
	                                           信息类型
	            </th>	
	            <th nowrap="nowrap">
	            	发布部门
	            </th> 
	             <th nowrap="nowrap">
	            	是否转发
	            </th>            
	            <th nowrap="nowrap">   
	              	信息发布人
	            </th>
	            <th nowrap="nowrap">   
	              	发布时间
	            </th>
	            <th nowrap="nowrap">   
	              	发布状态
	            </th>
	          </tr>
          <w:checkGroup id="group1">
          <l:iterate property="results" id="id1">
              <tr class="<l:output  oddOutput='EOS_table_row_o'  evenOutput='EOS_table_row' />">
                <td align="center" nowrap="nowrap">
                    <w:rowCheckbox>
                        <h:param name='messageId' iterateId='id1' property='messageId'/>
                        <h:param name='messageType' iterateId='id1' property='messageType'/>
                        <h:param name='flowId' iterateId='id1' property='flowId'/>
                        <h:param name='status' iterateId='id1' property='status'/>
                    </w:rowCheckbox>
                </td>            
                <td nowrap="nowrap"> 
                 <a href="#" onclick="doLink('<b:write iterateId="id1" property="messageId"/>','<b:write iterateId="id1" property="status"/>','<b:write iterateId="id1" property='messageType'/>','<b:write iterateId="id1" property='flowId' />');"> <b:write iterateId="id1" property="messageTitle"/></a>
                </td>
                <td nowrap="nowrap">
                 <d:write iterateId="id1" dictTypeId="ZHPT_MESSAGE_TYPE" property="messageType"/>
                </td>
                <td nowrap="nowrap">
                	<b:write iterateId="id1" property="objOrgcode"/> 
                </td>
                <td nowrap="nowrap">
                	<b:write iterateId="id1" property="returnType"/> 
                </td>
                <td nowrap="nowrap">
                	<b:write iterateId="id1" property="nodeName1"/> 
                </td> 
                <td nowrap="nowrap">
                	<b:write iterateId="id1" property="createDate"/> 
                </td>  
                <td nowrap="nowrap">
                <b:write iterateId="id1" property="statusName"/> 
                </td>
                </tr>
            </l:iterate>
            </w:checkGroup>
            <tr>
              <td colspan="23" class="command_sort_area">
              	<div class="h3">
              	<l:greaterThan property="page.count" targetValue="0" compareType="number" >
	                   <input type="button" class="button" value='查看流程' onclick="viewTaskProcess();"  id="searchButton">
	                   <input type="button" class="button" value='转发' onclick="transmit();"  id="searchButton">
	                   <input type="button" class="button" value='阅毕' onclick="readlyRead();"  id="updateButton">
	                </l:greaterThan>
	                </div>
                <div class="h4">
	                <l:equal property="page.isCount" targetValue="true" >
	                  <b:message key="l_total"></b:message>
	                  <b:write property="page.count" />
	                  <b:message key="l_recordNO."></b:message>
	                  <b:write property="page.currentPage" />
	                  <b:message key="l_page"></b:message>
	                  <b:write property="page.totalPage" />
	                  <b:message key="l_page"></b:message>
	                </l:equal>
	                <l:equal property="page.isCount" targetValue="false" >
	                  <b:message key="l_NO."></b:message>
	                  <b:write property="page.currentPage" />
	                  <b:message key="l_page"></b:message>
	                </l:equal>
	                <input type="button" class="button" onclick="firstPage('page', '', null, null, 'data_form');" value='<b:message key="l_firstPage"></b:message>'  <l:equal property="page.isFirst"  targetValue="true">disabled</l:equal> >
	                <input type="button" class="button" onclick="prevPage('page', '', null, null, 'data_form');" value='<b:message key="l_upPage"></b:message>' <l:equal property="page.isFirst"  targetValue="true">disabled</l:equal> >
	                <input type="button" class="button" onclick="nextPage('page', '', null, null, 'data_form');" value='<b:message key="l_nextPage"></b:message>' <l:equal property="page.isLast"  targetValue="true">disabled</l:equal> >
	                <l:equal property="page.isCount" targetValue="true">
	                  <input type="button" class="button" onclick="lastPage('page', '', null, null, 'data_form');" value='<b:message key="l_lastPage"></b:message>' <l:equal property="page.isLast"  targetValue="true">disabled</l:equal> >
	                </l:equal>
              </div>
              </td>
            </tr>
            </table>
          </h:form> 
          </DIV>
        <script type="text/javascript">

        $(function(){
            $("#tstatus").val('${message.status}')
        });
		        function callBack(){
		        	$id("querys").click();
		        }
		       
				function allItem(){
					var group =$id("group1");
					if(document.getElementById("xuanze").checked){
						group.selectAll();
					}else{
						group.disSelectAll();
					}
				}
				
	function viewTaskProcess(){
		var gop = $id("group1");
  		var len = gop.getSelectLength();
  		if(len == 0){
  			alert("请选择一条信息");
  			return;
  		}else if(len>1){
  			alert("查看流程只能选择一条记录!");
  			return;
  	  		}else{
  			var rows=gop.getSelectRows();
	  		var flowId = rows[0].getParam("flowId");
	  		if(flowId!=""){
	  			var strUrl = "/jbpm/jbpmDemoAction_viewTask.action?executionId="+flowId;
	  			showModalCenter(strUrl, null, null, 900, 400, '当前流程进度'); 
	  		}else{
		  	alert("查询不到流程");
	  		}
		  	}
	}

	function transmit(){
		var gop = $id("group1");
  		var len = gop.getSelectLength();
  		if(len == 0){
  			alert("请选择一条信息");
  			return;
  		}else{
  			var rows=gop.getSelectRows();
  			var messageIds="";
  			var status1 = "";
  	  		for(var i=0;i<rows.length;i++){
  	  		  messageIds += rows[i].getParam("messageId")+",";
  	  		  status1 += rows[i].getParam("status")+",";
  	  		}
  	  	if(messageIds!=""){
  			messageIds=messageIds.substring(0,messageIds.length-1);
  			status1=status1.substring(0,status1.length-1);
  			var strUrl = "/messagePublish/tMessagePublishAction_transmitEmp.action?messageId="+messageIds+"&status="+status1;
	  		showModalCenter(strUrl, null, callBack, 400, 150, '转发'); 
  	  	}
  		}
	}
	function readlyRead(){
		var gop = $id("group1");
  		var len = gop.getSelectLength();
  		if(len == 0){
  			alert("请选择一条流程信息");
  			return;
  		}
  	
  		var rows=gop.getSelectRows();
  		var messageIds="";
  		for(var i=0;i<rows.length;i++){
  	  		if(rows[i].getParam("status")=='1'){
  	  	  		alert("你选择的记录中存在已阅信息，请重新选择");
  	  	  		return false;
  	  		}else{
  	  		  messageIds += rows[i].getParam("messageId")+",";
  	  		}
  		 
  		}
  		if(messageIds!=""){
  			messageIds=messageIds.substring(0,messageIds.length-1);
		$.ajax({
	        url: "/messagePublish/tMessagePublishAction_insertMessageReadPer.action?messageId="+messageIds,
	        async: false,
	        type: 'post',
	        dataType: 'text',
	        timeout: 60000,
			success : function(data){
			    if(data.indexOf("success")>=0){
				    alert("已阅毕!");
				    $id("querys").click();
			    }else{
				   alert("操作失败!");
			    }
				},  
			error : function(data){
				 alert("系统出错，请联系管理员");
			   }  
	    });
  		}	
	}
	function open_newyw_tree_fun1(){//方法名
	     strUrl ="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.checkcount=1&changeTree.orgType=4&changeTree.showSelBox=4";
	var peArgument = [];
   //机构
   	var paramEntity = new ParamEntity('Organization');
   	paramEntity.setProperty('orgcode',$id("orgcode").value);
		paramEntity.setProperty('orgname',$id("orgname").value);
		paramEntity.setProperty('orgid',$id("orgid").value);
		peArgument[3]=[paramEntity,'orgname','orgcode','orgid'];
       //调用并传参
       strUrl += "&time="+new Date().getTime();
		showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack1,500,430,'选择机构');
}
	
function openNewEmpTreeCallBack1(arg){//回调方法
	if(arg['Organization']){ //原写法无需判断是否为空
 		var sorgidArra  = arg['Organization'].slice(0);//人员数组
 		argRes=[[],[],[],[]];
		for(var i=0;i<sorgidArra.length;i++){
			argRes[0].push(sorgidArra[i].getProperty("orgcode"));
			argRes[1].push(sorgidArra[i].getProperty("orgname"));
			argRes[2].push(sorgidArra[i].getProperty("orgid"));
		}
		$id("orgcode").value = argRes[0];
		$id("orgname").value = argRes[1];
		$id("orgid").value = argRes[2];
	}else{
		$id("orgcode").value = "";
		$id("orgname").value = "";
		$id("orgid").value = "";
	}
}

function open_newyw_tree_fun2(){//方法名
	 var strUrl="/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.orgType=6&changeTree.showSelBox=2";	
		var peArgument = {};
 	  var paramEntity = new ParamEntity('Employee');	
 	paramEntity.setProperty("empid",$("#empid").value);
 	paramEntity.setProperty("empname",$("#empname").value);
 	peArgument[2]=[paramEntity,'empname','empid'];
 	showModalCenter(strUrl,peArgument,openEmpCheckcountCallBack,500,430,'选择人员');
 }
 function openEmpCheckcountCallBack(arg){
		var sorgidArra  = arg['Employee'].slice(0);//人员数组
		argRes=[[],[],[],[]];
		for(var i=0;i<sorgidArra.length;i++){
			argRes[0].push(sorgidArra[i].getProperty("empid"));
			argRes[1].push(sorgidArra[i].getProperty("empname"));
		}
			 $("#empid").val(argRes[0]);
			 $("#empname").val(argRes[1]);

 }
        </script>
  </body>
</html>
