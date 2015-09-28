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
		  <th height="34" width="200" align="center" class="form_label">标题</th>
		  <th height="34" width="77" align="center" class="form_label">工作事项类型</th>
		  <th height="34" width="77" align="center" class="form_label">申请人姓名</th>
		  <th height="34" width="150" align="center" class="form_label">申请人所属机构</th>
		  <th height="34" width="200" align="center" class="form_label">开始时间</th>
		  <th height="34" width="200" align="center" class="form_label">结束时间</th>
		</tr>
			<l:iterate property="processTaskAssignees" id="id1">
			<tr class="<l:output evenOutput='EOS_table_row' />" id="issuedTr">
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="id1" property="businessTitle"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <d:write dictTypeId="ZHPT_BUSINESS_TYPE"  property="businessType" iterateId="id1" />
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="id1" property="preTaskAssingeeName"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="id1" property="preTaskOrgName" />
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="id1" property="startTime" formatPattern="yyyy-MM-dd HH:mm:ss"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="id1" property="endTime"  formatPattern="yyyy-MM-dd HH:mm:ss"/>
			  </td>
			</tr>
			</l:iterate>
		</table>
   