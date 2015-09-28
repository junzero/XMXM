<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="/js/commonUtil.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
    <title>
           首页
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="/css/global.css" type="text/css"/>
  </head>
  <body style="padding: 0;margin: 0px;overflow: hidden;" scroll="no">
     
<!-- header -->
<div class="header" region="north" border="true" >
<div class="logo fleft"><img src="/common/skins/default/images/main_2.png" height="76px" width="100%"/></div>
<div class="header_left">欢迎你：厦门银行 sysadmin 登陆日期：2014年08月28日</div>
<div class="header_right">
<ul>
  <li><img src="${pageContext.request.contextPath }/common/images/icons/house.png" title="返回首页" width="16" height="16" border="0"/>
			<nobr><a href="#" onclick="showIndex();">返回首页</a></nobr></li>
  <li><img src="${pageContext.request.contextPath }/images/e_edit.gif" width="16" title="密码修改" height="16" border="0"/>
			<nobr><a href="#" onClick="javascript:modify_emp();return false;">密码修改</a></nobr></li>
  <li><img src="${pageContext.request.contextPath }/common/skins/default/images/relogin.gif" width="16" title="注销" height="16" border="0"/>
			<nobr><a href="#" onclick="exitSystem();">注销</a></nobr></li>
</ul>
</div>
</div>
<!-- 菜单 -->
<div id="nav" class="menus">
   <ul>
   	<li class="inactive" >
   	    <a href="#">流程管理</a>
   	    <ul>
   	       <li><a href="#">我的流程</a></li>
   	       <li><a href="#">我的草稿</a></li>
   	       <li><a href="#">我的代办</a></li>
   	       <li><a href="#">流程发布</a></li>
   	       <li><a href="#">我的已办</a></li>
   	    </ul>
   	</li>
    <li class="inactive" >
        <a href="#">信息管理</a>
        <ul>
   	       <li><a href="#">信息发布查询</a></li>
   	    </ul>
   	</li>
    <li class="inactive" >
        <a href="#">会议信息管理</a>
         <ul>
   	       <li><a href="#">会议信息查询</a></li>
   	    </ul>
    </li>
    <li class="inactive" >
          <a href="#">数据下发管理</a>
           <ul>
   	       <li><a href="#">数据下发查询</a></li>
   	       </ul>
   	</li>
    <li class="inactive" >
         <a href="#">人员机构管理</a>
         <ul>
   	       <li><a href="#">人员管理</a></li>
   	       <li><a href="#">机构管理</a></li>
   	    </ul>
    </li>
    <li class="inactive" >
        <a href="#">系统管理</a>
         <ul>
   	       <li><a href="#">应用菜单管理</a></li>
   	       <li><a href="#">字典管理</a></li>
   	       <li><a href="#">角色权限管理</a></li>
   	       <li><a href="#">岗位管理</a></li>
   	    </ul>
    </li>
   </ul>
</div>
 <!-- 内容 -->
 <div id="content">
 <div id="content_main">
  <div class="tabList">
	<ul>
		<li class="cur">待办信息</li>
		<li>待办事件</li>
		<li>已办信息</li>
		<li>我的流程</li>
	</ul>
  </div>
  <div class="tabCon">
	<div class="cur" style="width:99%;height:100%">
	<table align="center" cellpadding="0" cellspacing="0" width="100%"  class="EOS_table1">
 	<tr>
 		<th nowrap="nowrap" >信息栏目</th>
 		<th nowrap="nowrap" >信息标题</th>
 		<th nowrap="nowrap" >发布部门</th>
 		<th nowrap="nowrap" >发布人</th>
 		<th nowrap="nowrap" >发布时间</th>
 		<th nowrap="nowrap" >附件</th>
 	</tr>
 	<tr  class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="#" onclick="doLink('','')">测试信息发布1</a></td>
 		<td nowrap="nowrap">厦门支行</td>
 		<td nowrap="nowrap">测试人员1</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 		<td nowrap="nowrap">
         <img src="/images/filepng.png" style="width:20px;height:20px;"/>
 		 </td>
 	</tr> 
 	<tr  class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="#" onclick="doLink('','')">测试信息发布2</a></td>
 		<td nowrap="nowrap">厦门支行</td>
 		<td nowrap="nowrap">测试人员1</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 		<td nowrap="nowrap">
         <img src="/images/filepng.png" style="width:20px;height:20px;"/>
 		 </td>
 	</tr> 
 	<tr  class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="#" onclick="doLink('','')">测试信息发布3</a></td>
 		<td nowrap="nowrap">厦门支行</td>
 		<td nowrap="nowrap">测试人员1</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 		<td nowrap="nowrap">
         <img src="/images/filepng.png" style="width:20px;height:20px;"/>
 		 </td>
 	</tr> 
 	<tr  class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="#" onclick="doLink('','')">测试信息发布4</a></td>
 		<td nowrap="nowrap">厦门支行</td>
 		<td nowrap="nowrap">测试人员1</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 		<td nowrap="nowrap">
         <img src="/images/filepng.png" style="width:20px;height:20px;"/>
 		 </td>
 	</tr> 
 	<tr  class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="#" onclick="doLink('','')">测试信息发布5</a></td>
 		<td nowrap="nowrap">厦门支行</td>
 		<td nowrap="nowrap">测试人员1</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 		<td nowrap="nowrap">
         <img src="/images/filepng.png" style="width:20px;height:20px;"/>
 		 </td>
 	</tr> 
 	<tr  class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="#" onclick="doLink('','')">测试信息发布6</a></td>
 		<td nowrap="nowrap">厦门支行</td>
 		<td nowrap="nowrap">测试人员1</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 		<td nowrap="nowrap">
         <img src="/images/filepng.png" style="width:20px;height:20px;"/>
 		 </td>
 	</tr> 
 	<tr  class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="#" onclick="doLink('','')">测试信息发布7</a></td>
 		<td nowrap="nowrap">厦门支行</td>
 		<td nowrap="nowrap">测试人员1</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 		<td nowrap="nowrap">
         <img src="/images/filepng.png" style="width:20px;height:20px;"/>
 		 </td>
 	</tr> 
 	<tr  class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="#" onclick="doLink('','')">测试信息发布8</a></td>
 		<td nowrap="nowrap">厦门支行</td>
 		<td nowrap="nowrap">测试人员1</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 		<td nowrap="nowrap">
         <img src="/images/filepng.png" style="width:20px;height:20px;"/>
 		 </td>
 	</tr> 
 	<tr  class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="#" onclick="doLink('','')">测试信息发布9</a></td>
 		<td nowrap="nowrap">厦门支行</td>
 		<td nowrap="nowrap">测试人员1</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 		<td nowrap="nowrap">
         <img src="/images/filepng.png" style="width:20px;height:20px;"/>
 		 </td>
 	</tr> 
 	<tr  class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="#" onclick="doLink('','')">测试信息发布10</a></td>
 		<td nowrap="nowrap">厦门支行</td>
 		<td nowrap="nowrap">测试人员1</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 		<td nowrap="nowrap">
         <img src="/images/filepng.png" style="width:20px;height:20px;"/>
 		 </td>
 	</tr> 	
 	<tr><td colspan="6" style="text-align:right;height: 22px;"><a href="/messagePublish/tMessagePublishAction_queryTMessagePublishList.action?message.status=0">更多...</a></td></tr>
 </table>
 <table height="10px;"><tr></tr></table>
  <table align="center" cellpadding="0" cellspacing="0" width="100%"  class="EOS_table1">
 	<tr>
 		<th nowrap="nowrap" >工作事项类别</th>
 		<th nowrap="nowrap" >标题</th>
 		<th nowrap="nowrap" >处理事项</th>
 		<th nowrap="nowrap" >发起部门</th>
 		<th nowrap="nowrap" >上一处理人</th>
 		<th nowrap="nowrap" >提交时间</th>
 	</tr> 
 	<tr class="EOS_table_row1">
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap"><a href="/jbpm/jbpmDemoAction_handle.action?taskAssgineeDto.businessKey=<b:write iterateId="ids" property="businessKey"/>&taskAssgineeDto.nextTaskId=<b:write iterateId="ids" property="nextTaskId"/>&taskAssgineeDto.executionId=<b:write iterateId="ids" property="executionId"/>&taskAssgineeDto.businessType=<b:write iterateId="ids" property="businessType"/>&taskAssgineeDto.taskAssingee=<b:write iterateId="ids" property="preTaskAssingee"/>">信息发布1</a></td>
 		<td nowrap="nowrap">信息发布</td>
 		<td nowrap="nowrap">厦门分行</td>
 		<td nowrap="nowrap">李小玲</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 	</tr>
 	<tr class="EOS_table_row1">
 		<td nowrap="nowrap">督办单</td>
 		<td nowrap="nowrap"><a href="/jbpm/jbpmDemoAction_handle.action?taskAssgineeDto.businessKey=<b:write iterateId="ids" property="businessKey"/>&taskAssgineeDto.nextTaskId=<b:write iterateId="ids" property="nextTaskId"/>&taskAssgineeDto.executionId=<b:write iterateId="ids" property="executionId"/>&taskAssgineeDto.businessType=<b:write iterateId="ids" property="businessType"/>&taskAssgineeDto.taskAssingee=<b:write iterateId="ids" property="preTaskAssingee"/>">信息发布1</a></td>
 		<td nowrap="nowrap">督办单</td>
 		<td nowrap="nowrap">厦门分行</td>
 		<td nowrap="nowrap">李小玲</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 	</tr> 	
 	<tr class="EOS_table_row1">
 		<td nowrap="nowrap">数据下发申请</td>
 		<td nowrap="nowrap"><a href="/jbpm/jbpmDemoAction_handle.action?taskAssgineeDto.businessKey=<b:write iterateId="ids" property="businessKey"/>&taskAssgineeDto.nextTaskId=<b:write iterateId="ids" property="nextTaskId"/>&taskAssgineeDto.executionId=<b:write iterateId="ids" property="executionId"/>&taskAssgineeDto.businessType=<b:write iterateId="ids" property="businessType"/>&taskAssgineeDto.taskAssingee=<b:write iterateId="ids" property="preTaskAssingee"/>">信息发布1</a></td>
 		<td nowrap="nowrap">数据下发申请</td>
 		<td nowrap="nowrap">厦门分行</td>
 		<td nowrap="nowrap">李小玲</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 	</tr> 	
 	<tr class="EOS_table_row1">
 		<td nowrap="nowrap">设备申请</td>
 		<td nowrap="nowrap"><a href="/jbpm/jbpmDemoAction_handle.action?taskAssgineeDto.businessKey=<b:write iterateId="ids" property="businessKey"/>&taskAssgineeDto.nextTaskId=<b:write iterateId="ids" property="nextTaskId"/>&taskAssgineeDto.executionId=<b:write iterateId="ids" property="executionId"/>&taskAssgineeDto.businessType=<b:write iterateId="ids" property="businessType"/>&taskAssgineeDto.taskAssingee=<b:write iterateId="ids" property="preTaskAssingee"/>">信息发布1</a></td>
 		<td nowrap="nowrap">设备申请</td>
 		<td nowrap="nowrap">厦门分行</td>
 		<td nowrap="nowrap">李小玲</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 	</tr> 	
 	<tr class="EOS_table_row1">
 		<td nowrap="nowrap">会议申请</td>
 		<td nowrap="nowrap"><a href="/jbpm/jbpmDemoAction_handle.action?taskAssgineeDto.businessKey=<b:write iterateId="ids" property="businessKey"/>&taskAssgineeDto.nextTaskId=<b:write iterateId="ids" property="nextTaskId"/>&taskAssgineeDto.executionId=<b:write iterateId="ids" property="executionId"/>&taskAssgineeDto.businessType=<b:write iterateId="ids" property="businessType"/>&taskAssgineeDto.taskAssingee=<b:write iterateId="ids" property="preTaskAssingee"/>">信息发布1</a></td>
 		<td nowrap="nowrap">会议申请</td>
 		<td nowrap="nowrap">厦门分行</td>
 		<td nowrap="nowrap">李小玲</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 	</tr> 	
 	<tr class="EOS_table_row1">
 		<td nowrap="nowrap">请假单</td>
 		<td nowrap="nowrap"><a href="/jbpm/jbpmDemoAction_handle.action?taskAssgineeDto.businessKey=<b:write iterateId="ids" property="businessKey"/>&taskAssgineeDto.nextTaskId=<b:write iterateId="ids" property="nextTaskId"/>&taskAssgineeDto.executionId=<b:write iterateId="ids" property="executionId"/>&taskAssgineeDto.businessType=<b:write iterateId="ids" property="businessType"/>&taskAssgineeDto.taskAssingee=<b:write iterateId="ids" property="preTaskAssingee"/>">信息发布1</a></td>
 		<td nowrap="nowrap">工作</td>
 		<td nowrap="nowrap">请假单</td>
 		<td nowrap="nowrap">李小玲</td>
 		<td nowrap="nowrap">2014/08/29 10:10:10</td>
 	</tr> 	 	
 	<tr><td colspan="6" style="text-align:right;height: 22px;"><a href="/jbpm/tProcessTaskAssigneeAction_queryMyToDoTasksList.action">更多...</a></td></tr>
  </table> 
 </div>
	<div style="width:99%;height:100%">
	 <table align="center" cellpadding="0" cellspacing="0" width="100%"  class="EOS_table1">
 	<tr>
 		<th nowrap="nowrap" >工作事项类别</th>
 		<th nowrap="nowrap" >标题</th>
 		<th nowrap="nowrap" >处理事项</th>
 		<th nowrap="nowrap" >发起部门</th>
 		<th nowrap="nowrap" >上一处理人</th>
 		<th nowrap="nowrap" >提交时间</th>
 	</tr> 
 	<l:notEmpty property="processTaskAssignees">
 	<l:iterate id="ids" property="processTaskAssignees">
 	<tr class="<l:output evenOutput='EOS_table_row1' oddOutput='EOS_table_row_o' />">
 		<td nowrap="nowrap"><d:write iterateId="ids" property="businessType" dictTypeId="ZHPT_BUSINESS_TYPE"/></td>
 		<td nowrap="nowrap"><a href="/jbpm/jbpmDemoAction_handle.action?taskAssgineeDto.businessKey=<b:write iterateId="ids" property="businessKey"/>&taskAssgineeDto.nextTaskId=<b:write iterateId="ids" property="nextTaskId"/>&taskAssgineeDto.executionId=<b:write iterateId="ids" property="executionId"/>&taskAssgineeDto.businessType=<b:write iterateId="ids" property="businessType"/>&taskAssgineeDto.taskAssingee=<b:write iterateId="ids" property="preTaskAssingee"/>"><b:write iterateId="ids" property="businessTitle" /></a></td>
 		<td nowrap="nowrap"><b:write iterateId="ids" property="processName"/></td>
 		<td nowrap="nowrap"><b:write iterateId="ids" property="preTaskOrgName"/></td>
 		<td nowrap="nowrap"><b:write iterateId="ids" property="preTaskAssingeeName"/></td>
 		<td nowrap="nowrap"><b:write iterateId="ids" property="preTaskTime"  formatPattern="yyyy-MM-dd HH:mm:ss"/></td>
 	</tr> 	
 	</l:iterate> 	
 	<tr><td colspan="6" style="text-align:right;height: 22px;"><a href="/jbpm/tProcessTaskAssigneeAction_queryMyToDoTasksList.action">更多...</a></td></tr>
 	</l:notEmpty>
  	<l:empty property="processTaskAssignees">
 	<tr><td colspan="6" style="color:red">无待办事件</td></tr>
 	</l:empty>
  </table>
  </div>
	<div>被风吹过的夏天、江南、一千年以后</div>
	<div>十年、K歌之王、浮夸</div>
  </div>
 </div>
 </div>
  </body>
  <script type="text/javascript">
  window.onload = function() {
	    var oDiv = document.getElementById("content_main");
	    var oLi = oDiv.getElementsByTagName("div")[0].getElementsByTagName("li");
	    var aCon = oDiv.getElementsByTagName("div")[1].getElementsByTagName("div");
	    var timer = null;
	    for (var i = 0; i < oLi.length; i++) {
	        oLi[i].index = i;
	        oLi[i].onclick = function() {
	            show(this.index);
	        }
	    }
	    function show(a) {
	        index = a;
	        var alpha = 0;
	        for (var j = 0; j < oLi.length; j++) {
	            oLi[j].className = "";
	            aCon[j].className = "";
	            aCon[j].style.opacity = 0;
	            aCon[j].style.filter = "alpha(opacity=0)";
	        }
	        oLi[index].className = "cur";
	        clearInterval(timer);
	        timer = setInterval(function() {
	            alpha += 2;
	            alpha > 100 && (alpha = 100);
	            aCon[index].style.opacity = alpha / 100;
	            aCon[index].style.filter = "alpha(opacity=" + alpha + ")";
	            alpha == 100 && clearInterval(timer);
	        },
	        5)
	    }
	}
  </script>
</html>
