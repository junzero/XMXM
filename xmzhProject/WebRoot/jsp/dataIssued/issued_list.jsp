<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>下发单列表</title>
</head>
  
  <body>
  	<h:form id="downForm" action=""></h:form>
    <h:form id="issuedForm" name="issuedForm" action="/dataIssued/tSendDataAction_queryIssuedByEmpId.action" method="post">
		<w:panel id="panel1" title="查询条件">
    	<table align="center" border="0" width="100%" class="form_table">
				<tr>
					<td class="form_label" align="right" width="20%">使用部门：</td>
					<td colspan="1"  width="30%"><h:text id="userOrg" property="sendData.userOrg" /></td>
					<td class="form_label" align="right" width="20%">数据类型：</td>
					<td colspan="1"  width="30%"><d:select id="dataType" dictTypeId="ZHPT_DATA_TYPE" name="sendData.dataType" property="sendData.dataType" nullLabel="请选择" style="width:150px"></d:select></td>
					</tr>
					<tr>
					<td class="form_label" align="right" width="20%">数据使用期限：</td>
					<td colspan="1"  width="30%">
					<w:date format="yyyy-MM-dd"submitFormat="yyyyMMdd" id="d1" name="sendData.startTime" property="sendData.startTime" />
					到<w:date format="yyyy-MM-dd"submitFormat="yyyyMMdd" id="d2" name="sendData.endTime" property="sendData.endTime" />
					</td>
					<td class="form_label" align="right" width="20%">数据是否销毁：</td>
					<td colspan="1"  width="30%"><d:select id="isDes" dictTypeId="ZHPT_DATA_ISDES" name="sendData.isDes" property="sendData.isDes" nullLabel="请选择"  style="width:150px"></d:select></td>
				</tr>
				<tr>
					<td class="form_label" align="right" width="20%">下发时间：</td>
					<td colspan="1"  width="30%">
					<w:date format="yyyy-MM-dd"submitFormat="yyyyMMdd" id="d3" name="sendData.beginTime" property="sendData.beginTime" />
					到<w:date format="yyyy-MM-dd"submitFormat="yyyyMMdd" id="d4" name="sendData.closeTime" property="sendData.closeTime" />
					</td>					
				</tr>
				<tr class="form_bottom">
						<td colspan="4" class="form_bottom">
						    <b:message key="l_display_per_page"></b:message>
					       <h:text id="pageLeng" size="2" property="page.length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
					        <h:hidden property="page.begin" value="0"/>
					        <h:hidden property="page.isCount" value="true"/>
							<input id="querys" type="submit" value="查 询" class="button">
							<input type="button" value="清 空" class="button" onclick="clears();">
		          			<input id="downexl" type="button" class="button" value="导出列表" onclick="downExl();"></td>
					</tr>			
			</table>
			</w:panel>
			</h:form>
			<div id="hh" class="divList">
    <w:panel width="100%" title="下载列表">
    	<viewlist id="e2c61865-3b56-470d-bd42-fff792fb9493">
  		<%-- <h:form name="searchForm" id="searchForm" checkType="blur"  method="post" action="/leave/tApplyLeaveAction_searchByMap.action">
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
	    </h:form> --%>
  	<h:form name="form1" action="" method="post">
		<h:hiddendata property="page"  />
	     <table class="EOS_table" width="100%" style="overflow:scroll">
		<tr>
		  <th><b:message key="l_select"></b:message></th>
		  <th nowrap="nowrap">数据标题</th>
		  <th nowrap="nowrap">数据使用人</th>
		  <th nowrap="nowrap">所属机构</th>
		  <th nowrap="nowrap">数据类型</th>
		  <th nowrap="nowrap">数据使用期限</th>
		  <th nowrap="nowrap">是否销毁</th>
		  <th nowrap="nowrap">销毁日期</th>
		  <th nowrap="nowrap">下发日期</th>
		</tr>
		<w:radioGroup id="group1">
			<l:iterate property="sendDataList" id="issued">
			<tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />" id="issuedTr">
			  <td style="text-align: center;height: 30px;width:5%">
			  	<w:rowRadio>
	               <h:param name='id' iterateId='issued' property='dsId'/>
	               <h:param name='userId' iterateId='issued' property='dataUser'/>
	            </w:rowRadio>
			  </td>
			  <td style="text-align: center;width: 20%">
			    <div style="padding:0px;overflow:hidden" nowrap>
			    	<nobr><b:write iterateId="issued" property="dsTitle"/></nobr>
			    </div>
			  </td>
			  <td style="text-align: center;width: 10%">
			    <div style="padding:0px;overflow:hidden" nowrap>
			    	<nobr><b:write iterateId="issued" property="username"/></nobr>
			    </div>
			  </td>
			  <td style="text-align: center;width: 10%">
			    <div style="padding:0px;overflow:hidden" nowrap>
			    	<nobr><b:write iterateId="issued" property="userOrg"/></nobr>
			    </div>
			  </td>
			  <td style="text-align: center;width: 10%">
			    <nobr><d:write dictTypeId="ZHPT_DATA_TYPE" iterateId="issued" property="dataType" /></nobr>
			  </td>
			  <td style="text-align: center;width: 15%">
			    <nobr><b:write iterateId="issued" property="timeLimit" maxLength="10" formatPattern="yyyy-MM-dd"/></nobr>
			  </td>
			  <td style="text-align: center;width: 10%">
			    <nobr><d:write dictTypeId="ZHPT_DATA_ISDES" iterateId="issued" property="isDes"/></nobr>
			  </td>
			  <td style="text-align: center;width: 10%">
			   <nobr><b:write iterateId="issued" property="desTime" maxLength="10" formatPattern="yyyy-MM-dd"/></nobr>
		      </td>
		      <td style="text-align: center;width: 10%">
			   <nobr><b:write iterateId="issued" property="createDate" maxLength="10" formatPattern="yyyy-MM-dd"/></nobr>
		      </td>
			</tr>
			</l:iterate>
		</w:radioGroup>
		 <tr>
		        <td colspan="9" class="command_sort_area">
		        	<div class="h3">
		          <input id="detailBu" type="button" class="button" value="详情" onclick="operate();">
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
		            	onclick="firstPage('page', '', null, null, '_self');" 
		            	value='<b:message key='l_firstPage' />'  
		            	<l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
		            <input type="button" class="button" 
		              onclick="prevPage('page', '', null, null, '_self');" 
		              value='<b:message key='l_upPage' />' 
		              <l:equal property="page.isFirst" targetValue="true">disabled</l:equal> >
		            <input type="button" class="button"   
		            onclick="nextPage('page', '', null, null, '_self');" 
		            value='<b:message key='l_nextPage' />' <l:equal property="page.isLast" 
		            targetValue="true">disabled</l:equal> >
		            <l:equal property="page.isCount" targetValue="true">
		              <input type="button" class="button"   
		              onclick="lastPage('page', '', null, null, '_self');"
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
   </div>
  </body>
  <script type="text/javascript">
	  $(document).ready(function(){
		  if($id("issuedTr") == null)
			$("#detailBu").hide();
			var sHeight = window.screen.height;
			if(sHeight<700)
				$("#hh").attr("class","divList2");
		  });
  
	function operate(){
		var gop = $id("group1");
  		var len = gop.getSelectLength();
  		if(len == 0){
  			alert("至少选一条下发信息");
  			return;
  		}else{
  			var rows = gop.getSelectRow();
	  		var resourceId=rows.getParam("id");
	  		var userId=rows.getParam("userId");
			var url = "/dataIssued/tSendDataAction_getIssuedByid.action?resourceId="+resourceId+"&userId="+userId;
			showModalCenter(url,null,callBack,1050,550,'下发表详情');
		}
	}
	function callBack(arg){
		if(arg!='')
		window.location.reload();}

	function downExl(){
		var url = "/dataIssued/tSendDataAction_downexl.action?";
		var isDes = $("#isDes").val();
		var userOrg = $("#userOrg").val();
		var dataType = $("#dataType").val();
		var startTime = $("#d1_input").val().replaceAll("-","");
		var endTime = $("#d2_input").val().replaceAll("-","");
		url = url+"sendData.isDes="+isDes+"&sendData.userOrg="+encodeURI(userOrg)+"&sendData.dataType="+dataType+"&sendData.startTime="+startTime+"&sendData.endTime="+endTime;
		window.location.href=url;
	  	
	}
	function clears(){
		$(':input','#issuedForm').not(':button,:submit,#pageLeng')
		.val("")
		.removeAttr("checked");
		}
  </script>
</html>
