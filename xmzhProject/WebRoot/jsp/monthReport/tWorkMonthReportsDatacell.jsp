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
    <style type="text/css">
    </style>                                                               
  </head>                                                                    
  <body topmargin="0">
  	 <e:datasource name="monthReports" type="bean" path="/xmzhProject/src/com/gotop/monthReport/model/TWorkMonthReports.java" />                                         
     <queryform id="8cf82903-39b3-4dd6-9c1e-ffaff96fa1af">
		<h:form name="form1" action="/monthReports/tMessagePublishAction_queryTMessagePublishList.action"
			checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
			<w:panel id="panel1" width="100%" title="月报查询" expand="true">
			<table align="center" border="0" width="100%" class="form_table">
					<tr>
						<td class="form_label" align="right">月报标题:</td>
						<td colspan="1">
							<h:text  property="monthReports.reportTitle" style="width:148px;" />
						</td>
						<td class="form_label" align="right">月份:</td>
						<td colspan="1">
				        	<h:text property="monthReports.reportMonth" id="reportMonth" style="width:40%;"/>	
				        </td>
					</tr>
                     <tr>
						<td class="form_label" align="right">发布开始时间:</td>
						<td colspan="1">
						<w:date  id="createBeginDate" submitFormat="yyyyMMdd" format="yyyy-MM-dd" readonly="true" property="monthReports.createBeginDate" />
						</td>
						<td class="form_label" align="right">发布结束时间:</td>
						<td colspan="1">
						<w:date  id="createEndDate" submitFormat="yyyyMMdd" format="yyyy-MM-dd" readonly="true" property="monthReports.createEndDate" />
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
            <h:hiddendata property="monthReports"/>
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
	             	 选择
	              <!--
	              <b:message key="l_select"></b:message>
	              -->
	            </th>
	            <th nowrap="nowrap">   
	              	月报编号
	            </th>
	            <th nowrap="nowrap">
	            	月报标题
	            </th>
	            <th nowrap="nowrap">
	             	月份
	            </th>	
	            <th nowrap="nowrap">
	            	月报内容
	            </th>            
	            <th nowrap="nowrap">   
	              	月报申请人
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
              <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />">
                <td align="center" nowrap="nowrap">
                    <w:rowCheckbox>
                        <h:param name='reportId' iterateId='id1' property='reportId'/>
                        <h:param name='reportMonth' iterateId='id1' property='reportMonth'/>
                        <h:param name='flowId' iterateId='id1' property='flowId'/>
                         <h:param name='status' iterateId='id1' property='status'/>
                    </w:rowCheckbox>
                </td>            
                <td nowrap="nowrap">  
                  <b:write iterateId="id1" property="reportId"/>
                </td>
                <td nowrap="nowrap"> 
                 <a href="#" onclick="doLink('<b:write iterateId="id1" property="reportId"/>')"> 
                 <b:write iterateId="id1" property="reportTitle"/></a>
                </td>
                <td nowrap="nowrap">
                 <d:write iterateId="id1" property="reportMonth"/>
                </td>
                <td nowrap="nowrap">
                	<b:write iterateId="id1" property="content"/> 
                </td>
                <td nowrap="nowrap">
                	<b:write iterateId="id1" property="createName"/> 
                </td> 
                <td nowrap="nowrap">
                	<b:write iterateId="id1" property="createDate"/> 
                </td>  
                <td nowrap="nowrap">
                <b:write iterateId="id1" property="status"/> 
                </td>
                </tr>
            </l:iterate>
            </w:checkGroup>
            <tr>
              <td colspan="23" class="command_sort_area">
              	<div class="h3">
              	<l:greaterThan property="page.count" targetValue="0" compareType="number" >
	                   <input type="button" class="button" value='新增' onclick="add();"  id="insertButton">
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
  </body>
 <script type="text/javascript">
	function callBack() {
		$id("querys").click();
	}

	function allItem() {
		var group = $id("group1");
		if (document.getElementById("xuanze").checked) {
			group.selectAll();
		} else {
			group.disSelectAll();
		}
	}

	function viewTaskProcess() {
		var gop = $id("group1");
		var len = gop.getSelectLength();
		if (len == 0) {
			alert("请选择一条流程信息");
			return;
		} else if (len > 1) {
			alert("查看流程只能选择一条记录!");
			return;
		} else {
			var rows = gop.getSelectRows();
			var flowId = rows[0].getParam("flowId");
			if (flowId != "") {
				var strUrl = "/jbpm/jbpmDemoAction_viewTask.action?executionId="
						+ flowId;
				showModalCenter(strUrl, null, null, 900, 400, '当前流程进度');
			} else {
				alert("查询不到流程");
			}
		}
	}

	function transmit() {
		var gop = $id("group1");
		var len = gop.getSelectLength();
		if (len == 0) {
			alert("请选择一条流程信息");
			return;
		} else {
			var rows = gop.getSelectRows();
			var messageIds = "";
			for ( var i = 0; i < rows.length; i++) {
				messageIds += rows[i].getParam("messageId") + ",";
			}
			if (messageIds != "") {
				messageIds = messageIds.substring(0,
						messageIds.length - 1);
				var strUrl = "/messagePublish/tMessagePublishAction_transmitEmp.action?messageId="
						+ messageIds;
				showModalCenter(strUrl, null, null, 400, 150, '转发');
			}
		}
	}
	function readlyRead() {
		var gop = $id("group1");
		var len = gop.getSelectLength();
		if (len == 0) {
			alert("请选择一条流程信息");
			return;
		}

		var rows = gop.getSelectRows();
		var messageIds = "";
		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].getParam("status") == '已阅') {
				alert("你选择的记录中存在已阅信息，请重新选择");
				return false;
			} else {
				messageIds += rows[i].getParam("messageId") + ",";
			}

		}
		if (messageIds != "") {
			messageIds = messageIds.substring(0, messageIds.length - 1);
			$
					.ajax({
						url : "/messagePublish/tMessagePublishAction_insertMessageReadPer.action?messageId="
								+ messageIds,
						async : false,
						type : 'post',
						dataType : 'text',
						timeout : 60000,
						success : function(data) {
							if (data.indexOf("success") >= 0) {
								alert("已阅毕!");
							} else {
								alert("操作失败!");
							}
						},
						error : function(data) {
							alert("系统出错，请联系管理员");
						}
					});
		}
	}
</script>
</html>
