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
    <script type="text/javascript">
		function downExl(){
			var resourceId = $("#resourceId").val();
			var resourceType = $("#resourceType").val();
			var resourceFlow = $("#resourceFlow").val();
			var strUrl = "/opinion/tApproveOpninionAction_downExl.action?resourceId="+resourceId+"&resourceType="+resourceType+"&resourceFlow="+resourceFlow;
			window.location.href=strUrl;}
    </script>                                                                 
  </head>                                                                    
  <body>
  <h:form name="form2" action="/opinion/tApproveOpninionAction_queryViewList.action" method="post">
    	<h:hidden id="resourceId" property="resourceId"/>
    	<h:hidden id="resourceType" property="resourceType"/>
		<h:hidden id="resourceFlow" property="resourceFlow"/>
  </h:form>                                                     
    <h:form name="form1" action="" method="post">
		<h:hiddendata property="page"  />
	     <table class="EOS_table" width="100%">
		<tr>
		  <th nowrap="nowrap">操作人</th>
		  <th nowrap="nowrap">意见</th>
		  <th nowrap="nowrap">操作类型</th>
		  <th nowrap="nowrap">操作人机构</th>
		  <th nowrap="nowrap">操作日期</th>
		  <th nowrap="nowrap">下一操作人</th>
		  <th nowrap="nowrap">所属机构</th>
		</tr>
			<l:iterate property="data" id="opinion">
			<tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />    id="applyDataTr">
			  <%-- <td style="text-align: center;height: 30px;width:5%">
			  	<w:rowRadio>
	               <h:param name='daid' iterateId='applyData' property='daId'/>
	               <h:param name='username' iterateId='applyData' property='username'/>
	               <h:param name='dataUser' iterateId='applyData' property='dataUser'/>
	               <h:param name='title' iterateId='applyData' property='daTitle'/>
	               <h:param name='useTlimit' iterateId='applyData' property='useTlimit'/>
	            </w:rowRadio>
			  </td> --%>
			  <td nowrap="nowrap">
			    <div style="padding:0px;overflow:hidden" nowrap>
			    	<b:write iterateId="opinion" property="empname"/>
			    </div>
			  </td>
			  <td nowrap="nowrap">
			    <div style="padding:0px;overflow:hidden" nowrap>
			    	<b:write iterateId="opinion" property="opninionContent"/>
			    </div>
			  </td>
			  <td nowrap="nowrap">
			    <d:write iterateId="opinion" dictTypeId="ZHPT_APPROVE_STATUS" property="operatorType" />
			  </td>
			  <td nowrap="nowrap">
			    <b:write iterateId="opinion" property="orgname"/>
			  </td>
			  <td nowrap="nowrap">
			    <div style="padding:0px;overflow:hidden" nowrap>
			    	<b:write iterateId="opinion" property="operaterDate"/>
			    </div>
			  </td>
			  <td nowrap="nowrap">
		     	<b:write iterateId="opinion" property="nextOprName"/>
			  </td>
			  <td nowrap="nowrap">
			   <b:write iterateId="opinion" property="nextorgname"/>
		      </td>
			</tr>
			</l:iterate>
		 <tr>
		        <td colspan="9" class="command_sort_area">
		        	<div class="h3">
		          		<input id="detailBu" type="button" class="button" value="导出意见" onclick="downExl();">
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
  </body>
</html>
