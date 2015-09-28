<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<%@ taglib uri="/struts-tags" prefix="s" %>
<script src="<%=request.getContextPath() %>/common/gotop/jquery.min.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>流程配置文件列表</title>
	</head>
	<body topmargin="0" leftmargin="0">
	<input type="button" id="btn" class="button" onclick="queryProcessFileLists();" value='查询'>
	<DIV style="width: 100%; height: 300px; OVERFLOW: auto">
	<w:panel id="panel" width="100%" title="流程配置文件列表">
				<viewlist id="e2c61865-3b56-470d-bd42-fff792fb9493">
				<h:form name="page_form"
					action="/file/tFileResourceTableAction_queryProcessFileLists.action" method="post">
					<h:hidden property="page.begin" />
					<h:hidden property="page.length" />
					<h:hidden property="page.count" />
					<h:hidden property="page.isCount" />
					<h:hiddendata property="integral" />
					
					<input type="hidden" id="empid" name="empid" value="<s:property value='empid'/>">
					<input type="hidden" id="empname" name="empname" value="<s:property value='empname'/>">
					<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>">
					<input type="hidden" id="orgname" name="orgname" value="<s:property value='orgname'/>">
					
					<table align="center" border="0" width="100%" class="EOS_table">
						<tr>
						<th nowrap="nowrap">
									<l:greaterThan property="page.count" targetValue="0" compareType="number">
									<h:checkbox id="xuanze" onclick="allItem();" />
								</l:greaterThan>
								<b:message key="l_select"></b:message>
							</th>
							<th nowrap="nowrap">
								文件编号
							</th>
							<th nowrap="nowrap">
								来源编号
							</th>
							<th nowrap="nowrap">
								文件名称
							</th>
						</tr>
						<w:checkGroup id="group1">
                           <l:iterate property="fileResourceTables" id="id1">
							<tr class="<l:output oddOutput='EOS_table_row_o'  evenOutput='EOS_table_row' />">
								<td align="center" nowrap="nowrap">
									<w:rowCheckbox>
										<h:param name='fileId' iterateId='id1' property='fileId' />
										<h:param name='resourceId' iterateId='id1' property='resourceId' />
										<h:param name='fileName' iterateId='id1' property='fileName' />
									</w:rowCheckbox>
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="fileId" />
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="resourceId" />
								</td>
								<td nowrap="nowrap"> 
									<b:write iterateId="id1" property="fileName" />
								</td>
							</tr>
						</l:iterate>
						</w:checkGroup>
						<tr>
							<td colspan="18" class="command_sort_area">
							<l:greaterThan property="page.count" targetValue="0" compareType="number" >
								&nbsp; &nbsp;
								<input type="button" class="button" value="发布流程" onclick="deployProcess();"/>
								</l:greaterThan>
							 &nbsp;
								<input type="button" class="button" value="新增流程配置" onclick="addProcessFile();"/>
							<l:greaterThan property="page.count" targetValue="0" compareType="number" >
								&nbsp; &nbsp;
								<input type="button" class="button" value="删除流程配置" onclick="deleteProcessDefinition();"/>
								</l:greaterThan>
								<h4>
									<l:equal property="page.isCount" targetValue="true"> 共   <b:write
											property="page.count" /> 条记录 第 <b:write
											property="page.currentPage" />页/<b:write
											property="page.totalPage" />                页
                  </l:equal>
									<l:equal property="page.isCount" targetValue="false"> 第<b:write
											property="page.currentPage" />
                    页
                  </l:equal>
                  <l:equal property="page.isCount" targetValue="true">
									<input type="button" class="button"
										onclick="firstPage('page', 'pageQuery', null, null, 'page_form');"
										value="首页"
										<l:equal property="page.isFirst" targetValue="true">disabled</l:equal>>
									<input type="button" class="button"
										onclick="prevPage('page', 'pageQuery', null, null, 'page_form');"
										value="上页"
										<l:equal property="page.isFirst" targetValue="true">disabled</l:equal>>
									<input type="button" class="button"
										onclick="nextPage('page', 'pageQuery', null, null, 'page_form');"
										value="下页"
										<l:equal property="page.isLast" targetValue="true">disabled</l:equal>>
									<l:equal property="page.isCount" targetValue="true">
										<input type="button" class="button"
											onclick="lastPage('page', 'pageQuery', null, null, 'page_form');"
											value="尾页"
											<l:equal property="page.isLast" targetValue="true">disabled</l:equal>>
									</l:equal>
									</l:equal>
								</h4>
							</td>
						</tr>
					</table>
				</h:form>
				</viewlist>
			</w:panel>	
	</DIV>
	</body>
	<script type="text/javascript">
		function allItem(){
			var group =$id("group1");
			if(document.getElementById("xuanze").checked){
				group.selectAll();
			}else{
				group.disSelectAll();
			}
		}
		
		function queryProcessFileLists(){
			  var frm= $name("page_form");
		       frm.submit();
			}
		
		function callBack(){
		   // if(arg=="1") {
			$name("page_form").submit();
			window.close();
		 //   }else{
		  //  $name("page_form").submit();
		 //   }
		}
		//新增流程配置
		function addProcessFile(){
		    var url="/file/tFileResourceTableAction_toAddProcessFile.action";
		    showModalCenter(url, "",callBack, 500, 200, '新增流程配置');
		}

		//发布流程
		function deployProcess(){
			var gop = $id("group1");
	  		var len = gop.getSelectLength();
	  		if(len == 0){
	  			alert("至少选一条流程配置");
	  			return;
	  		}else if(len>1){
		  		alert("只能选择一条流程配置记录");
		  		return false;
		  	}else{
				//选择人员配置
		  		open_newyw_tree_fun();
			  }
			}

		function open_newyw_tree_fun(){//方法名
  		    var strUrl = "/tree/initMainTree_mainTree.action?changeTree.checkcount=3&changeTree.showTabOrg=1&changeTree.showTabGroup=0&changeTree.showTabRole=1&changeTree.orgType=6";
  				var peArgument = [];
  		//人员
  		    	var paramEntity = new ParamEntity('Employee');
  				paramEntity.setProperty('empid',$id("empid").value);
  				paramEntity.setProperty('empname',$id("empname").value);
  				peArgument[0]=[paramEntity,'empname','empid']; 
  		//机构
  		    	var paramEntity = new ParamEntity('Organization');
  				paramEntity.setProperty('orgid',$id("orgid").value);
  				paramEntity.setProperty('orgname',$id("orgname").value);
  				peArgument[2]=[paramEntity,'orgname','orgid'];
  	    //调用并传参
  				strUrl += "&time="+new Date().getTime();
  				showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack,500,430,'选择发布范围')
  	    }

		function openNewEmpTreeCallBack(arg){
			var empids;
			var empnames;
			
			//回调方法
			if(arg!=''){
		    	if(arg['Employee']){ 
			  		var sorgidArra  = arg['Employee'].slice(0);//人员数组
			  		argRes=[[],[],[],[]];
					for(var i=0;i<sorgidArra.length;i++){
						argRes[0].push(sorgidArra[i].getProperty("empid"));
						argRes[1].push(sorgidArra[i].getProperty("empname"));
					}
					$id("empid").value = argRes[0];
					$id("empname").value = argRes[1];
					empids = argRes[0];
					empnames=argRes[1];
					var group =$id("group1");
					var rows = group.getSelectRows();
			  		var fileId=rows[0].getParam("fileId");
					var myAjax = new Ajax("/jbpm/tProcessConfigAction_saveProcessConfig.action?empids="+empids+"&empnames="+empnames+"&fileId="+fileId);
					myAjax.submit();
	
					
		    	}else{
					$id("empid").value = "";
					$id("empname").value = "";
		    	}
			}
	}
	</script>
</html>