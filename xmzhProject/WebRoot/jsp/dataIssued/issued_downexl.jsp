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
		  <th height="34" width="200" align="center" class="form_label">下发标题</th>
		  <th height="34" width="200" align="center" class="form_label">下发时间</th>
		  <th height="34" width="77" align="center" class="form_label">数据使用人</th>
		  <th height="34" width="150" align="center" class="form_label">所属机构</th>
		  <th height="34" width="77" align="center" class="form_label">数据类型</th>
		  <th height="34" width="100" align="center" class="form_label">数据使用期限</th>
		  <th height="34" width="77" align="center" class="form_label">是否销毁</th>
		  <th height="34" width="100" align="center" class="form_label">销毁日期</th>
		  <th height="34" width="100" align="center" class="form_label">提交日期</th>
		</tr>
			<l:iterate property="sendDataList" id="issued">
			<tr class="<l:output evenOutput='EOS_table_row' />" id="issuedTr">
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="issued" property="dsTitle"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="issued" property="createDate" formatPattern="yyyy-MM-dd"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="issued" property="username"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="issued" property="userOrg"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <d:write dictTypeId="ZHPT_DATA_TYPE" iterateId="issued" property="dataType" />
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <b:write iterateId="issued" property="timeLimit" maxLength="10" formatPattern="yyyy-MM-dd"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			    <d:write dictTypeId="ZHPT_DATA_ISDES" iterateId="issued" property="isDes"/>
			  </td>
			  <td style="vnd.ms-excel.numberformat:@">
			   <b:write iterateId="issued" property="desTime" maxLength="10" formatPattern="yyyy-MM-dd"/>
		      </td>
		       <td style="vnd.ms-excel.numberformat:@">
			   <b:write iterateId="issued" property="createDate" maxLength="10" formatPattern="yyyy-MM-dd"/>
		      </td>
			</tr>
			</l:iterate>
		</table>
   