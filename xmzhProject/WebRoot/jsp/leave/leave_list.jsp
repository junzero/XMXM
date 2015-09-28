<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>请假单列表</title>
</head>
  
  <body>
    <w:panel width="100%" title="请假列表">
    	<viewlist id="e2c61865-3b56-470d-bd42-fff792fb9493">
  		<h:form name="searchForm" id="searchForm" checkType="blur"  method="post" action="/leave/tApplyLeaveAction_searchByMap.action">
			<table border="0" width="100%" class="EOS_table" cellpadding="0" cellspacing="0">
				<tr>
					<td class="form_label"><nobr>请假申请人</nobr></td>
					<td><input id="applicant" name="applicant" type="text" value="<b:write property="applicant"/>"></td>
					<td class="form_label"><nobr>申请标题</nobr></td>
					<td><input type="text" id="lv_title" name="lv_title" value="<b:write property="lv_title"/>" /></td>
					<td class="form_label"><nobr>申请原因</nobr></td>
					<td><input type="text" id="lv_reason" name="lv_reason" value="<b:write property="lv_reason"/>" /></td>
				</tr>
				<tr>
					<td class="form_label"><nobr>请假类型</nobr></td>
					<td class="form_label">
						<h:select id="lv_type" name="lv_type"  property="lv_type">
							<h:option label="未选择" value="00"  />
							<h:option label="病假" value="01" />
							<h:option label="事假" value="02" />
							<h:option label="其他" value="03" />
						</h:select>
					</td>
					<td colspan="2" align="right"><input calss="submit" type="submit" style="width:40" value="查询"></td>
				</tr>
			</table>
	    </h:form>
  	<h:form name="form1" action="" method="post">
		<h:hiddendata property="page"  />
	     <table style="table-layout:fixed" class="EOS_table" width="100%">
		<tr>
		  <th>&nbsp;</th>
		  <th nowrap="nowrap" style="font-size:12px;font-weight: bolder;">申请编号</th>
		  <th nowrap="nowrap" style="font-size:12px;font-weight: bolder;">申请人</th>
		  <th nowrap="nowrap" style="font-size:12px;font-weight: bolder;">申请标题</th>
		  <th nowrap="nowrap" style="font-size:12px;font-weight: bolder;">开始请假时间</th>
		  <th nowrap="nowrap" style="font-size:12px;font-weight: bolder;">请假天数</th>
		  <th nowrap="nowrap" style="font-size:12px;font-weight: bolder;">请假原因</th>
		</tr>
		<w:checkGroup id="group1">
			<l:iterate property="leaveList" id="leave">
			<tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />">
			  <td style="text-align: center;height: 30px;width:5%">
			  	<w:rowCheckbox>
	               <h:param name='id' iterateId='leave' property='lvId'/>
	               <h:param name='emp' iterateId='leave' property='empName'/>
	               <h:param name='title' iterateId='leave' property='lvTitle'/>
	               <h:param name='start' iterateId='leave' property='lvStart'/>
	               <h:param name='days' iterateId='leave' property='lvDays'/>
	               <h:param name='reason' iterateId='leave' property='lvReson'/>
	            </w:rowCheckbox>
			  </td>
			  <td style="text-align: center;width: 15%">
			    <b:write iterateId="leave" property="lvId"/>
			  </td>
			  <td style="text-align: center;width: 10%">
			    <b:write iterateId="leave" property="empName" maxLength="15"/>
			  </td>
			  <td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;text-align: center;width: 20%">
			    <b:write iterateId="leave" property="lvTitle" maxLength="15"/>
			  </td>
			  <td style="text-align: center;width: 10%">
			    <b:write iterateId="leave" property="lvStart" maxLength="50" formatPattern="yyyy-MM-dd" />
			  </td>
			  <td style="text-align: center;width: 10%">
			    <b:write iterateId="leave" property="lvDays" maxLength="3"/>
			  </td>
			  <td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;text-align: center;width: 35%">
			    <b:write iterateId="leave" property="lvReason"/>
		      </td> 
			</tr>
			</l:iterate>
		</w:checkGroup>
		 <tr>
		        <td colspan="7" class="command_sort_area">
		        	<div class="h3">
		          <input type="button" class="button" value="详情" onclick="detail();">
		            <input type="button" class="button" value="通过" onclick="pass();" id="passButton">
		            <input type="button" class="button" value="不通过" onclick="refuse();" id="refuseButton">
	              </div>
	              <div class="h4">
		            <l:equal property="page.isCount" targetValue="true">
		              <b:message key="l_total"/>
		              <b:write property="page.count"/>
		              <b:message key="l_recordNO."/>
		              <b:write property="page.currentPage"/>
		              <b:message key="l_page"/>
		              <b:write property="page.totalPage"/>
		              <b:message key="l_page"/>
		            </l:equal>
		            <l:equal property="page.isCount" targetValue="false">
		              <b:message key="l_NO."/>
		              <b:write property="page.currentPage"/>
		              <b:message key="l_page"/>
		            </l:equal>
		            <input type="button" class="button"   
		            	onclick="firstPage('page', null, './', '_self');" 
		            	value='<b:message key='l_firstPage' />'  
		            	<l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
		            <input type="button" class="button" 
		              onclick="prevPage('page', null, './', '_self');" 
		              value='<b:message key='l_upPage' />' 
		              <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
		            <input type="button" class="button"   
		            onclick="nextPage('page', null, './', '_self');" 
		            value='<b:message key='l_nextPage' />' <l:equal property="page.isLast" 
		            targetValue="true">disabled</l:equal> >
		            <l:equal property="page.isCount" targetValue="true">
		              <input type="button" class="button"   
		              onclick="lastPage('page', null, './', '_self');"
		               value='<b:message key='l_lastPage' />' 
		               <l:equal property="page.isLast" targetValue="true">disabled</l:equal> >
		            </l:equal>
		          </div>
		        </td>
		      </tr>
	      </table>
	   </h:form>
	   </viewlist>
   </w:panel>
  </body>
  <script type="text/javascript">
	function detail(){
		var gop = $id("group1");
  		var len = gop.getSelectLength();
  		if(len == 0){
  			alert("至少选一条流程信息");
  			return;
  		}else if(len>1){
	  		alert("只能选择一条流程记录");
	  		return false;
	  	}else{
			var rows = gop.getSelectRows();
	  		var id=rows[0].getParam("id");
			var url = "/leave/tApplyLeaveAction_getLeaveById.action?id="+id;
			window.location.href=url;
		}
	}
  </script>
</html>
