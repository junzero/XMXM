<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>申请单列表</title>
</head>
  
  <body>
    <h:form id="appQuery" name="appQuery"	action="/dataApply/tApplyDataAction_queryAllDataApply.action" method="post">
		<w:panel id="panel1" title="查询条件">
    	<table align="center" border="0" width="100%" class="form_table">
				<tr>
					<td class="form_label" align="right" width="20%">标题：</td>
					<td colspan="1"  width="30%"><h:text id="daTitle" property="applyData.daTitle" /></td>
					<td class="form_label" align="right" width="20%">申请人：</td>
					<td colspan="1"  width="30%"><h:text id="empName" property="applyData.empName" /></td>
					</tr>
					<tr>
					<td class="form_label" align="right" width="20%">申请提交时间：</td>
					<td colspan="1"  width="30%">
					<w:date format="yyyy-MM-dd"submitFormat="yyyyMMdd" id="d1" name="applyData.startTime" property="applyData.startTime" />
					到<w:date format="yyyy-MM-dd"submitFormat="yyyyMMdd" id="d2" name="applyData.endTime" property="applyData.endTime" />
					</td>
					<td class="form_label" align="right" width="20%">提取频度：</td>
					<td colspan="1"  width="30%"><d:select dictTypeId="ZHPT_FREQUENCY_EXT" id="daFreq" name="applyData.daFreq" property="applyData.daFreq" nullLabel="请选择"></d:select></td>
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
    <w:panel width="100%" title="申请">
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
	     <table class="EOS_table" width="100%">
		<tr>
		  <th>&nbsp;</th>
		  <th nowrap="nowrap">申请标题</th>
		  <th nowrap="nowrap">申请人</th>
		  <th nowrap="nowrap">申请内容</th>
		  <th nowrap="nowrap">数据使用人员</th>
		  <th nowrap="nowrap">数据主管部门</th>
		  <th nowrap="nowrap">提取频度</th>
		  <th nowrap="nowrap">提取范围</th>
		  <th nowrap="nowrap">申请时间</th>
		</tr>
		<w:radioGroup id="group1">
			<l:iterate property="applyDataList" id="applyData">
			<tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />"    id="applyDataTr">
			  <td style="text-align: center;height: 30px;width:5%">
			  	<w:rowRadio>
	               <h:param name='daid' iterateId='applyData' property='daId'/>
	               <h:param name='username' iterateId='applyData' property='username'/>
	               <h:param name='dataUser' iterateId='applyData' property='dataUser'/>
	               <h:param name='title' iterateId='applyData' property='daTitle'/>
	               <h:param name='useTlimit' iterateId='applyData' property='useTlimit'/>
	            </w:rowRadio>
			  </td>
			  <td nowrap="nowrap">
			    <div style="padding:0px;overflow:hidden" nowrap>
			    	<b:write iterateId="applyData" property="daTitle"/>
			    </div>
			  </td>
			  <td nowrap="nowrap">
			    <div style="padding:0px;overflow:hidden" nowrap>
			    	<b:write iterateId="applyData" property="empName"/>
			    </div>
			  </td>
			  <td nowrap="nowrap">
			    <b:write iterateId="applyData" property="daContent" />
			  </td>
			  <td nowrap="nowrap">
			    <b:write iterateId="applyData" property="username"/>
			  </td>
			  <td nowrap="nowrap">
			    <div style="padding:0px;overflow:hidden" nowrap>
			    	<b:write iterateId="applyData" property="eOrgName"/>
			    </div>
			  </td>
			  <td nowrap="nowrap">
			    <l:equal iterateId="applyData" property="daFreq" targetValue="01">
		     		<d:write iterateId="applyData" dictTypeId="ZHPT_FREQUENCY_EXT" property="daFreq"/>
		     	</l:equal>
		     	<l:notEqual iterateId="applyData" property="daFreq" targetValue="01">
		     		<b:write iterateId="applyData" property="freqNo"/>次/<d:write iterateId="applyData" dictTypeId="ZHPT_FREQUENCY_TYPE" property="freqType"/>
		     	</l:notEqual>
			  </td>
			  <td nowrap="nowrap">
			   <b:write iterateId="applyData" property="daRange"/>
		      </td>
		      <td nowrap="nowrap">
			   <b:write iterateId="applyData" property="createDate" formatPattern="yyyy-MM-dd"/>
		      </td>
			</tr>
			</l:iterate>
		</w:radioGroup>
		 <tr>
		        <td colspan="9" class="command_sort_area">
		        	<div class="h3">
		          <input id="detailBu" type="button" class="button" value="详情" onclick="operate();"/>
		          <l:notEqual property="isView" targetValue="1">
		          	<input id="todown" type="button" class="button" value="取出到下发单" onclick="toIssued();">
		          </l:notEqual>
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
  </body>
  <script type="text/javascript">
	  $(document).ready(function(){
		  if($id("applyDataTr") == null)
			$("#detailBu").hide();
		  });
  
	function operate(){
		var gop = $id("group1");
  		var len = gop.getSelectLength();
  		if(len == 0){
  			alert("至少选一条下发信息");
  			return;
  		}else{
  			var rows = gop.getSelectRow();
	  		var daid=rows.getParam("daid");
	  		var url = "/dataApply/tApplyDataAction_getApplyDataforShow.action?taskAssgineeDto.businessKey="+daid+"&isView=1";
  	  		showModalCenter(url,null,null,1050,450,'数据申请单详情');
		}
	}

	function toIssued(){
		var gop = $id("group1");
  		var len = gop.getSelectLength();
		if(len == 0){
  			alert("至少选一条下发信息");
  			return;
  		}else{
  			var rows = gop.getSelectRow();
	  		var userName=rows.getParam("username");
	  		var dataUser=rows.getParam("dataUser");
	  		var title=rows.getParam("title");
	  		var useTlimit=rows.getParam("useTlimit");
	  		var obj = new Object();
	  		obj.userName = userName;
	  		obj.dataUser = dataUser;
	  		obj.title = title;
	  		obj.useTlimit = useTlimit;
			window.returnValue = obj;
			window.close();
		}

	}
	function clears(){
		$(':input','#appQuery').not(':button,:submit,#pageLeng')
		.val("")
		.removeAttr("checked");
		}

	function downExl(){
		var url = "/dataApply/tApplyDataAction_downexl.action?";
		var daTitle = $("#daTitle").val();
		var empName = $("#empName").val();
		var daFreq = $("#daFreq").val();
		var startTime = $("#d1_input").val().replaceAll("-","");
		var endTime = $("#d2_input").val().replaceAll("-","");
		url = url+"applyData.daTitle="+encodeURI(daTitle)+"&applyData.empName="+encodeURI(empName)+"&applyData.daFreq="+daFreq+"&applyData.startTime="+startTime+"&applyData.endTime="+endTime;
		window.location.href=url;
	  	
	}
  </script>
</html>
