<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
     <div style="width:90%;">
		<a href="#" onclick="showDetailopinion('<b:write property='taskAssgineeDto.businessKey'/>','<b:write property='taskAssgineeDto.businessType'/>','<b:write property='taskAssgineeDto.executionId'/>');">查看详细流程列表</a>
    <r:datacell
		 id="planning20" xpath="TApproveOpninion" width="2" width="100%" height="150" pageSize="50"
		 queryAction="/messagePublish/tMessagePublishAction_queryOpninions.action"
		 entityType="com.gotop.leave.model.TApproveOpninion"
		 initParamFunc="initPlanCell20">
			<r:field fieldName="empname" label="操作人员" allowModify="false" width="80" onRefreshFunc="showAll">
				<h:text readonly="true"/>
			</r:field>
			<r:field fieldName="opninionContent" label="意见" allowModify="false" width="300" onRefreshFunc="freshFunc">
				<h:text readonly="true"/>
			</r:field>
			<r:field fieldName="operatorType" label="操作类型" allowModify="false" width="100">
				<d:select dictTypeId="ZHPT_APPROVE_STATUS"  />
			</r:field>
			<r:field fieldName="orgname" label="操作人机构" allowModify="false" width="100" onRefreshFunc="showAll">
				<h:text readonly="true"/>
			</r:field>
			<r:field fieldName="operaterDate" label="操作日期" allowModify="false" width="80" onRefreshFunc="showAll">
				<h:text readonly="true"/>
			</r:field>
			<r:field fieldName="nextOprName" label="下一操作人" allowModify="false" width="80" onRefreshFunc="showAll">
				<h:text readonly="true"/>
			</r:field>
			<r:field fieldName="nextorgname" label="所属机构" allowModify="false" width="100" onRefreshFunc="showAll">
				<h:text readonly="true"/>
			</r:field>
		</r:datacell>
		</div>
		
      <div id="mask" class="mask"> 
</div> 
<!-- 弹出基本资料详细DIV层 --> 
<div class="sample_popup" id="popup" style="visibility: hidden; display: none;"> 
<div class="bc4 fb mt0 h25 lh25 tc" id="popup_drag"> 	
 <span id="tx" class="">详细意见</span>
</div>
<div>
<div class="menu_form_body mt2 bc6" id="ct">
</div> 
</div>
<div class="tc bc6" >
 	<input type="button" class="button" id="popup_exit" value="关闭" />
 	<div class="h6">&nbsp;</div>
 </div>
 
<!-- <input type="button" class="button" value="复制" onclick="coptct();">	 -->
</div> 
<input type="button" id="exitButton" value="aaaa" style="display:none" />
