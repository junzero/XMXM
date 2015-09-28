<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
<head>
<h:script src="/common/gotop/lookup-group.js"/>
<h:script src="/common/skins/default/scripts/common.js" />
<title></title>
<%
	//岗位查询结果
	String posi_query_result = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("positionManager_l_title_position")
	                         + com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_query_result");
%>
<script type="text/javascript">
	/*
	 * 实现全选复选框
	 */
	function checkSelectAll(){
		if ($id("checkSelect").checked){
			selectAll("group1");
		}else{
			selectNone("group1");
		}
	}
	/*
	 *  自定义初始化按钮样式
	 */
	function custInit(){
		initButtonStyle();
	}
	/*
	*	初始化选中框
	*/	
	CheckGroup.prototype.afterInit = function() {
		this.selectBoxID = $id("selPosi");//已选框ID
		this.submitParamName = "positionId";//lookup的值与CheckGroup的对应h:param
		this.displayParamName = "posiName";//lookup的显示名与CheckGroup的对应h:param
		this.titleParamName = "posiCode";//title
		this.initLookUp_CheckGroup();
	}
	function rtnSelectVal() {
		window.returnValue = $id("group1").getReturnValue();//从CheckGroup中取得返回值
		window.closeD();
	}
</script>
</head>
<body class="eos-panel-table">
<form name="form1" action="/empMngr/positionSelectWin2_empMngr.action" method="post" onsubmit="return checkForm(this);">
    <table align="center" border="0" width="100%" class="form_table">
     <tr>
          <td class="form_label" width="30%">
            <b:message key="positionManager_OmPosition.posiname"></b:message><b:message key="l_colon"/>
          </td>
          <td width="30%">
            <h:text property="omPosition.posiName" />
          </td>
          <td width="40%" colspan="2">
            &nbsp;<input type="submit" value="查询" />
            &nbsp;<input type="button" value="删除" onclick="$id('group1').batchDeleteOption()"/>
          </td>
      </tr>
    </table>
</form>
<h:form name="data_form" action="/empMngr/positionSelectWin2_empMngr.action" method="post" >
	<h:hidden property="page.begin"/>
	    <h:hidden property="page.length"/>
	    <h:hidden property="page.count"/>
	    <h:hidden property="page.isCount"/>
	    <h:hidden property="omPosition.posiName" />
   	<table align="center" border="0" width="100%" height="90%" class="EOS_table">
   		<tr>
   			<td valign="top">
			   <table align="center" border="0" width="100%" class="EOS_table">
			       <tr>
			            <th align="center">
			              <l:greaterThan property="page.size" targetValue="0" compareType="number">
			                  <input  type="checkbox" id="checkSelect" name="checkSelect" onclick="checkSelectAll('checkSelect','group1');"> 
			              </l:greaterThan>
			              <b:message key="l_select"></b:message>
			            </th>
			            <th>   <!-- 岗位名称 -->
			              <b:message key="positionManager_OmPosition.posiname"></b:message>
			            </th>
			            <th> <!--  岗位代码 -->
			              <b:message key="positionManager_OmPosition.posicode"></b:message>
			            </th>
			            <th>
			            	岗位层级
			            </th>	            
			            <th>   <!-- 所属机构 -->
			              岗位说明
			            </th>
			            <th>   <!-- 所属职务 -->
			              岗位属性
			            </th>
			            <th>
			            	排序级别
			            </th>
			          </tr>
			      <w:checkGroup id="group1">
			      <l:iterate property="oaPosiList" id="id1">
			          <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o' />" height="100%">
			            <td align="center">
			                <w:rowCheckbox beforeSelectFunc="lookUp_selectFunc"  beforeUnSelectFunc="lookUp_unSelectFunc">
			                    <h:param name='positionId' iterateId='id1' property='positionId' indexed="true"/>
			                    <h:param name='posiName' iterateId='id1' property='posiName' indexed="true"/>
			                    <h:param name='posiCode' iterateId='id1' property='posiCode' indexed="true"/>
			                </w:rowCheckbox>
			            </td>                
			            <td>  <!--  岗位名称 -->
			              <b:write iterateId="id1" property="posiName"/>
			            </td>
			            <td>  <!--  岗位代码 -->
			              <b:write iterateId="id1" property="posiCode"/>
			            </td>
			            <td>
			            	<d:write iterateId="id1" dictTypeId="ABF_ORGDEGREE" property="posiLevel"/>
			            </td>
			            <td>  <!-- 所属机构 -->
			              <b:write iterateId="id1" property="posiDesc"/>
			            </td>
			            <td>  <!--  所属职务 -->
			              <d:write iterateId="id1" dictTypeId="ABF_ROLETYPEFIELD" property="posiType"/>
			            </td>
			            <td>
			            	<b:write iterateId="id1" property="sortLevel"/>
			            </td>
			            </tr>
			        </l:iterate>
			        </w:checkGroup>
			        <tr>
			          <td colspan="7" class="command_sort_area">
			          	<div class="h3">
			            	<input type="button" style='margin-top:2' value='确定返回' onclick="rtnSelectVal();" >
			            </div>
			            <div class="h4">
			            <l:equal property="page.isCount" targetValue="true" >
			              <b:message key="l_total"></b:message>
			              <b:write property="page/count" />
			              <b:message key="l_recordNO."></b:message>
			              <b:write property="page.currentPage" />
			              <b:message key="l_page"></b:message>/
			              <b:write property="page.totalPage" />
			              <b:message key="l_page"></b:message>
			            </l:equal>
			            <l:equal property="page.isCount" targetValue="false" >
			              <b:message key="l_NO."></b:message>
			              <b:write property="page.currentPage" />
			              <b:message key="l_page"></b:message>
			            </l:equal>
			            
			            <input type="button" onclick="firstPage('page', '', null, null, 'data_form');" value='<b:message key="l_firstPage"></b:message>'  <l:equal property="page.isFirst"  targetValue="true">disabled</l:equal> >
			            <input type="button" onclick="prevPage('page', '', null, null, 'data_form');" value='<b:message key="l_upPage"></b:message>' <l:equal property="page.isFirst"  targetValue="true">disabled</l:equal> >
			            <input type="button" onclick="nextPage('page', '', null, null, 'data_form');" value='<b:message key="l_nextPage"></b:message>' <l:equal property="page.isLast"  targetValue="true">disabled</l:equal> >
			            <l:equal property="page.isCount" targetValue="true">
			              <input type="button" onclick="lastPage('page', '', null, null, 'data_form');" value='<b:message key="l_lastPage"></b:message>' <l:equal property="page.isLast"  targetValue="true">disabled</l:equal> >
			            </l:equal>
			          </div>
			          </td>
			        </tr>
			  </table>
   			</td>
   			<td width="50px" valign="top">
			   <table align="center" border="0" width="100%" class="EOS_table">
			       <tr>
				       <td width="100%" class="eos-panel-title">&nbsp;已选项机构</td>
				   </tr>
			       <tr>
			       	   <td width="100%">
						  <SELECT id="selPosi" size="22" multiple="multiple" style="width: 200px" ondblclick="$id('group1').deleteOption(this)">
					      </SELECT>
					   </td>
				  </tr>
				</table>
   			</td>
   		</tr>
   	</table>
</h:form>
<script type="text/javascript">
	eventManager.add(window,"load",custInit);
</script> 
</body>
</html>
