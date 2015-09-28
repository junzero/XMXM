<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://eos.primeton.com/tags/logic" prefix="l"%>
<%@ taglib uri="http://eos.primeton.com/tags/bean" prefix="b"%>
<%@ taglib uri="http://eos.primeton.com/tags/dict" prefix="d"%>
<%@ taglib uri="http://eos.primeton.com/tags/html" prefix="h"%>
<%@page import="com.gotop.util.time.TimeUtil"%>
<%
	response.setContentType("application/x-msdownload;charset=UTF-8");
	//设置Excel的名称;	
	String date = TimeUtil.today()+TimeUtil.now();
	response.setHeader("Content-Disposition",
			"attachment; filename=\""+date+".xls\"");
	out.flush();
%>
<table width="98%" border="1" cellspacing="1" cellpadding="1">
		<tr height="34">
		  <th height="34" width="200" align="center" class="form_label">申请标题</th>
		  <th height="34" width="77" align="center" class="form_label">申请人</th>
		  <th height="34" width="150" align="center" class="form_label">申请内容</th>
		  <th height="34" width="200" align="center" class="form_label">数据使用人员</th>
		  <th height="34" width="100" align="center" class="form_label">数据主管部门</th>
		  <th height="34" width="77" align="center" class="form_label">提取频度</th>
		  <th height="34" width="200" align="center" class="form_label">提取范围</th>
		  <th height="34" width="100" align="center" class="form_label">申请时间</th>
		</tr>
			<l:iterate property="applyDataList" id="applyData">
			<tr class="<l:output evenOutput='EOS_table_row' />" id="applyData">
			  <td style="vnd.ms-excel.numberformat:@">
			    	<b:write iterateId="applyData" property="daTitle"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    	<b:write iterateId="applyData" property="empName"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="applyData" property="daContent" />
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="applyData" property="username"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    	<b:write iterateId="applyData" property="eOrgName"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <l:equal iterateId="applyData" property="daFreq" targetValue="01">
		     		<d:write iterateId="applyData" dictTypeId="ZHPT_FREQUENCY_EXT" property="daFreq"/>
		     	</l:equal>
		     	<l:notEqual iterateId="applyData" property="daFreq" targetValue="01">
		     		<b:write iterateId="applyData" property="freqNo"/>次/<d:write iterateId="applyData"  dictTypeId="ZHPT_FREQUENCY_TYPE" property="freqType"/>
		     	</l:notEqual>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			   <b:write iterateId="applyData" property="daRange"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			   <b:write iterateId="applyData" property="createDate" formatPattern="yyyy-MM-dd"/>
		      </td>
			</tr>
			</l:iterate>
		</table>
   