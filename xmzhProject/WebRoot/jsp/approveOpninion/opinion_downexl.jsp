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
		  <th height="34" width="77" align="center" class="form_label">操作人</th>
		  <th height="34" width="300" align="center" class="form_label">意见</th>
		  <th height="34" width="77" align="center" class="form_label">操作类型</th>
		  <th height="34" width="150" align="center" class="form_label">操作机构</th>
		  <th height="34" width="150" align="center" class="form_label">操作日期</th>
		  <th height="34" width="77" align="center" class="form_label">下一操作人</th>
		  <th height="34" width="100" align="center" class="form_label">所属机构</th>
		</tr>
			<l:iterate property="data" id="opinion">
			<tr class="<l:output evenOutput='EOS_table_row' />" id="opinion">
			  <td style="vnd.ms-excel.numberformat:@">
			    	<b:write iterateId="opinion" property="empname"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    	<b:write iterateId="opinion" property="opninionContent"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <d:write iterateId="opinion" dictTypeId="ZHPT_APPROVE_STATUS" property="operatorType" />
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="opinion" property="orgname"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    	<b:write iterateId="opinion" property="operaterDate"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
		     	<b:write iterateId="opinion" property="nextOprName"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			   <b:write iterateId="opinion" property="nextorgname"/>
			  </td>
			</tr>
			</l:iterate>
		</table>
   