<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>
<%--
- Author(s): lz
- Date: 2011-10-05 16:16:53
- Description:
--%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      EOS tree
    </title>
  </head>
  <body>
	<r:rtree id="orgSrcTree"  width="100%" height="375">
		<r:treeRoot childEntities="oaOrg" display="单位机构树"
			initParamFunc="getInitParam"
			icon="/common/images/icons/arrow_merge.png"
			action="org.gocom.abframe.org.organ.newcomponent.queryMngorgOfLogon.biz">
		</r:treeRoot>
		<r:treeNode nodeType="oaOrg" showField="orgname"
			childEntities="oaOrg;oaPosi;oaEmp" initParamFunc="getInitOrgParam"
			icon="/common/images/icons/chart_organisation.gif"
			submitXpath="oParentOrg"
			action="org.gocom.abframe.org.publicom.abftzzjggxsbbiz.queryChildNodeOfOrgAll.biz">
			<r:treeMenu>
				<r:treeMenuItem display="查看" onClickFunc="checkSrcFun"/>
			</r:treeMenu>
		</r:treeNode>
		<r:treeNode nodeType="oaPosi" showField="posiname" initParamFunc="getInitPosiParam"
			childEntities="oaPosi;oaEmp" icon="/common/images/icons/application_form.png"
			submitXpath="oParentPosi" 
			action="org.gocom.abframe.org.publicom.abftzzjggxsbbiz.queryChileNodeOfPosiAll.biz"
			>
			<r:treeMenu>
				<r:treeMenuItem display="查看" onClickFunc="checkSrcFun"/>
			</r:treeMenu>
		</r:treeNode>
		<r:treeNode nodeType="oaEmp" showField="empname" onDblclickFunc="rtnSelectVal"
			icon="/common/images/icons/user.gif" onRefreshFunc="empListRefresh">
			<r:treeMenu>
				<r:treeMenuItem display="查看" onClickFunc="checkSrcFun"/>
			</r:treeMenu>
		</r:treeNode>
	</r:rtree>
	<script type="text/javascript">
		function getInitParam(){
			
		}
	</script>
  </body>
</html>
