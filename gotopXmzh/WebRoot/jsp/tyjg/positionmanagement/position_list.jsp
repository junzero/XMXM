<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      岗位查询列表
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
  
  
  	<e:datasource name="omPosition" type="bean" path="com.gotop.vo.tyjg.OmPosition"/>
  	<queryform id="8cf82903-39b3-4dd6-9c1e-ffaff96fa1af">
		<h:form name="form1" action="/positionmanagement/positionAction_queryPositionList.action"
			checkType="blur" target="_self" method="post" onsubmit="return checkForm(this);">
			<w:panel id="panel1" width="100%" title="查询条件" expand="true">
			<table align="center" border="0" width="100%" class="form_table">
					<tr>
						<td class="form_label" align="right">岗位名称：</td>
						<td colspan="1">
							<h:text id="posiname" property="omPosition.posiName" style="width:148px;" />
						</td>
						<td class="form_label"  align="right">岗位代码：</td>
						<td colspan="1">
							<h:text id="posicode" property="omPosition.posiCode" style="width:148px;" />
						</td>
					</tr>
					<tr>
						<td class="form_label"  align="right">
							岗位属性：
						</td>
						<td>
							<d:select id="posisort" dictTypeId="ABF_ROLETYPEFIELD" property="omPosition.posiSort" 
								style="width:148px;" nullLabel="请选择">
							</d:select>
						</td>
						<td class="form_label"  align="right">
							岗位层级：
						</td>
						<td>
							<d:select id="posilevel" dictTypeId="ABF_ORGDEGREE" nullLabel="请选择" 
								property="omPosition.posiLevel" style="width:148px;">
							</d:select>
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
	 <h:form name="data_form" action="/positionmanagement/positionAction_queryPositionList.action" method="post">
            <h:hiddendata property="omPosition"/>
            <h:hidden property="page.begin"/>
		    <h:hidden property="page.length"/>
		    <h:hidden property="page.count"/>
		    <h:hidden property="page.isCount"/>
           <table align="center" border="0" width="100%" class="EOS_table">
           <tr>
		       <td colspan="7" class="eos-panel-title">&nbsp;岗位查询结果</td>
		      </tr>
           <tr>
	            <th align="center">
	              <l:greaterThan property="page.count" targetValue="0" compareType="number">
	                 <h:checkbox id="xuanze" onclick="allItem();"/>
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
	            <th>   
	              	岗位属性
	            </th>
	            <th>   
	              	岗位说明
	            </th>
	            <th>   
	              	排序级别
	            </th>
	          </tr>
          <w:checkGroup id="group1">
          <l:iterate property="omPositionList" id="id1">
              <tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />">
                <td align="center">
                    <w:rowCheckbox>
                        <h:param name='positionid' iterateId='id1' property='positionId'/>
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
                <td>
                	<d:write iterateId="id1" dictTypeId="ABF_ROLETYPEFIELD" property="posiSort"/> 
                </td> 
                <td>
                	<b:write iterateId="id1" property="posiDesc"/>
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
	                <input type="button" class="button" style='margin-top:2' value='<b:message key="l_add"/>' onclick="addRecord();" >
	                <l:greaterThan property="page.count" targetValue="0" compareType="number" >
	                   <input type="button" class="button" value='<b:message key="l_update"/>' onclick="updateRecord();"  id="updateButton">
	                </l:greaterThan>
	                <l:greaterThan property="page.count" targetValue="0" compareType="number">
	                   <input type="button" class="button" value='<b:message key="l_delete"/>' onclick="deleteRecord();" id="deleteButton">
	                </l:greaterThan>
                </div>
                <div class="h4">
	                <l:equal property="page.isCount" targetValue="true" >
	                  <b:message key="l_total"></b:message>
	                  <b:write property="page.count" />
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
        <script type="text/javascript">
        		//增加岗位
		        function addRecord(){
		        	var strUrl = "/jsp/tyjg/positionmanagement/add_or_updat_eposition.jsp?_ts=" +new Date();
		        	showModalCenter(strUrl, null, callBack, 550, 190, '<b:message key="positionManager_l_title_addPosition"/>');  
		        }
		        //修改
		        function updateRecord(){
		        	var gop = $id("group1");
			  		var len = gop.getSelectLength();
			  		if(len == 0){
			  			alert("至少选择一个岗位信息");
			  			return;
			  		} else if(len > 1){
			  			alert("只能选择一个岗位信息进行修改");
			  			return;
			  		}else{
			  			var rows = gop.getSelectRows();
	  					var positionid = rows[0].getParam("positionid");
			        	var strUrl = "/positionmanagement/positionAction_querySinglePosition.action?omPosition.positionId="+positionid+"&_ts=" +new Date();
			        	showModalCenter(strUrl, null, callBack, 550, 190, '修改岗位信息');  
		        	}
		        }
		        function callBack(){
		        	$id("querys").click();
		        }
		        function clears(){
					$id("posiname").value="";
					$id("posicode").value="";
					$id("posilevel").value="";
					$id("posisort").value="";
				}
				function allItem(){
					var group =$id("group1");
					if(document.getElementById("xuanze").checked){
						group.selectAll();
					}else{
						group.disSelectAll();
					}
				}
				function deleteRecord(){
					var gop = $id("group1");
			  		var len = gop.getSelectLength();
			  		if(len == 0){
			  			alert("至少选择一个岗位信息");
			  			return;
			  		}else{
			  			if(confirm("删除岗位会同时删除人员岗位信息，是否确定要删除岗位信息？")){
				  			var rows = gop.getSelectRows();
		  					var params = null;
		  					for(var i = 0; i <len; i++){
		  						if(i == 0){
		  							params = rows[i].getParam("positionid");
		  						}else{
		  							params += "," + rows[i].getParam("positionid");
		  						}
		  					}
		  					var myAjax = new Ajax("/positionmanagement/positionAction_deleteBeathPosition.action");
		  					myAjax.addParam("positionids",params);
		  					myAjax.submit();
		  					var rtun = myAjax.getValue("root/data/rtun");
		  					if(rtun == "success"){
		  						alert("岗位删除成功！");
		  						callBack();
		  					}else{
		  						alert("岗位删除失败！");
		  					}
			        	}
		        	}
				}
        </script>
  </body>
</html>
