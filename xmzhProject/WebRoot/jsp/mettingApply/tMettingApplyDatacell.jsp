<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="/js/commonUtil.js"></script>
    <title>
                 会议信息查询
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <style>
  	.command_sort_area .h3{
  		float: left;
  	}
  	.command_sort_area .h4{
  		float: right;
  	}
  </style>
  <body style="margin: 0px;">
  	<e:datasource name="meet" type="bean" path="com.gotop.mettingApply.model.TMettingApply" />
  	<queryform id="8cf82903-39b3-4dd6-9c1e-ffaff96fa1af">
		<h:form name="form1" action="/mettingApply/tMettingApplyAction_queryMettingApplyList.action"
			checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
			<w:panel id="panel1" width="100%" title="信息查询" expand="true">
			<table align="center" border="0" width="100%" class="form_table">
					<tr>
						<td class="form_label" align="right">会议标题:</td>
						<td colspan="3">
							<h:text  property="meet.title" style="width:148px;" />
						</td>
					</tr>
                     <tr>
						<td class="form_label" align="right">会议开始时间:</td>
						<td colspan="1">
						<w:date  id="createDate" submitFormat="yyyy-MM-dd" format="yyyyMMdd" readonly="true" property="meet.createDate" />
						</td>
						<td class="form_label" align="right">会议结束时间:</td>
						<td colspan="1">
						<w:date  id="createDate1" submitFormat="yyyy-MM-dd" format="yyyyMMdd" readonly="true" property="meet.createDate1" />
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
	 <h:form name="data_form" action="/mettingApply/tMettingApplyAction_queryMettingApplyList.action" method="post">
            <h:hiddendata property="meet"/>
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
	              <!--
	              <b:message key="l_select"></b:message>
	              -->
	            </th>
	            <th nowrap="nowrap">   
	              	会议标题
	            </th>
	            <th nowrap="nowrap"> 
	                                          会议类型
	            </th>
	            <th nowrap="nowrap">
	                                           会议时间
	            </th>	
	            <th nowrap="nowrap">
	            	申请人
	            </th>            
	            <th nowrap="nowrap">   
	              	状态
	            </th>
	          </tr>
          <w:checkGroup id="group1">
          <l:iterate property="results" id="id1">
              <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />">
                <td align="center" nowrap="nowrap">
                    <w:rowCheckbox>
                        <h:param name='mettingId' iterateId='id1' property='mettingId'/>
                        <h:param name='recId' iterateId='id1' property='recId'/>
                        <h:param name='status' iterateId='id1' property='flowId'/>
                         <h:param name='status' iterateId='id1' property='status'/>
                    </w:rowCheckbox>
                </td>            
                <td nowrap="nowrap"> 
                 <a href="#" onclick="viewSupervise('<b:write iterateId="id1" property="recId"/>','<b:write iterateId="id1" property="status"/>','<b:write iterateId="id1" property="mettingId"/>')"> <b:write iterateId="id1" property="title"/></a>
                </td>
                <td nowrap="nowrap">
                 <d:write iterateId="id1" dictTypeId="ZHPT_METTING_TYPE" property="type"/>
                </td>
                <td nowrap="nowrap">
                	<b:write iterateId="id1" property="mettingTime"/> 
                </td>
                <td nowrap="nowrap">
                	<b:write iterateId="id1" property="empName"/> 
                </td> 
                <td nowrap="nowrap">
                	<b:write iterateId="id1" property="statusName"/> 
                </td>  
                </tr>
            </l:iterate>
            </w:checkGroup>
            <tr>
              <td colspan="23" class="command_sort_area">
              	<div class="h3">
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
        <script type="text/javascript">

		        function callBack(){
		        	$id("querys").click();
		        }
		       
				function allItem(){
					var group =$id("group1");
					if(document.getElementById("xuanze").checked){
						group.selectAll();
					}else{
						group.disSelectAll();
					}
				}
				
	function viewSupervise(recId,status,mettingId){
		//alert(recId+","+status+","+superviseId);
	  			var strUrl = "/mettingApply/tMettingApplyAction_info.action?meet.recId="+recId+"&meet.status="+status+"&meet.mettingId="+mettingId;
		  		showModalCenter(strUrl, null, callBack, 900, 550, '当前流程进度'); 
	}	
        </script>
  </body>
</html>
