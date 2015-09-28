<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.Date"%>
<%@page import="com.gotop.util.time.TimeUtil"%>
<h:css href="/css/style1/style-custom.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="/common/gotop/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/fileDown.css">
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/fileDown.js"></script>
<title></title>
</head>
  
  <body>
  <w:panel>
    <h:form id="issuedForm" name="issuedForm" action="/dataIssued/tSendDataAction_destroyIssued.action" method="post" enctype="multipart/form-data">
<table align="center" border="0" width="100%" class="form_table">
       <tr>
         <td  colspan="4" style="text-align: center;font-weight:bold;font-size:12pt;height:50px" >
        		 下发单
        		 <h:hidden id="userId" name="userId" property="sendData/userId"/>
        		 <h:hidden id="dsId" name="dsId" property="sendData/dsId"/>
        </td>
      </tr>
      <tr>
      	<td class="form_label" align="right">
         标　　　 题：
        </td>
        <td colspan="3">
          <b:write property="sendData/dsTitle"/>
        </td>
      </tr>
      <tr>
        <td class="form_label" align="right">
         数 据 来 源：
        </td>
        <td colspan="1">
        	<d:write dictTypeId="ZHPT_DATA_RESOURCE" property="sendData.dataSource"/>
        </td>
     	<td class="form_label" align="right">数 据 类 型：</td>
     	<td colspan="1">
     		<d:write dictTypeId="ZHPT_DATA_TYPE" property="sendData.dataType"/>
     	</td>
      </tr>
      <tr>
         <td class="form_label" align="right">
          申 请 部 门：
        </td>
        <td colspan="1">
         <b:write property="login_user/orgentityname" scope="session" />
        </td>
        <td class="form_label" align="right">
      	<nobr>
       销    毁    人：</nobr>
      	</td>
        <td colspan="1"> 
        	<h:hidden property="sendData/dsId" id="dsId" name="approveOpninion.resourceId" />
         	<b:write property="sendData/desName" />
        </td>
      </tr>
      <tr>
       <td class="form_label" align="right">申 请 流 程：</td>
     	<td colspan="3">
     		<b:write property="sendData/daPro" />
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right">使 用 期 限：</td>
     	<td>
     		<b:write property="sendData/timeLimit" formatPattern="yyyy-MM-dd"/>	
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right">下 发 说 明：</td>
     	<td colspan="3">
	     	<b:write property="sendData/dsExpl"/>
     	</td>
     </tr>
     <tr>
     	<td class="form_label" align="right">附　　　 件：</td>
     	<td colspan="3">
	     	<div id="tag"></div>
     	</td>
     </tr>
     <tbody id="desRow">
      <tr>
     	<td class="form_label" align="right">销 毁 时 间：</td>
     	<td colspan="1">
     		<h:text readonly="true" id="desTime"/>
     		<h:hidden id="hiddenTime" name="desTime"/>
	     	<font style="color: red">*</font>
     	</td>
     	<td class="form_label" align="right">销 毁 人 员：</td>
     	<td colspan="1">
     		<h:text id="dataUser_show" readonly="true" validateAttr="allowNull=false" />
     		<h:hidden id="dataUser" name="dataUser"/>
     		<font style="color: red">*</font>
     	</td>
     </tr>
     <tr class="form_bottom">
       <td colspan="4">
         <input type="button" value="记录销毁信息" id="pass" class="button" onclick="doSubmit();">
        </td>
     </tr>
     </tbody>
    </table>
    </h:form>
    </w:panel>
    <table align="left" border="0" id="app_table" height="100%" width="100%" cellpadding="0" cellspacing="0">
		<tr>	
			<!-- 人员id -->
			<input type="hidden" id="empid" name="empid" value="<s:property value='empid'/>">
			<!-- 人员名称 -->
			<input type="hidden" id="empname" name="empname" value="<s:property value='empname'/>">
			<!-- 机构id -->
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>">
			<!-- 机构名称 -->
			<input type="hidden" id="orgname" name="orgname" value="<s:property value='orgname'/>">
				
		  <!--  <td id="" valign="top" width="60%" >
		      <w:panel height="450">
		      <iframe frameBorder="0" style="width:100%;height:100%" scrolling="no" src="" name="assignerTab"> </iframe>
		      </w:panel>
		   </td> -->
		</tr>
		</table>
  </body>
  <script type="text/javascript">
		$(document).ready(function(){
			$id("dataUser_show").value='${sessionScope.login_user.empname}';
			$id("empname").value='${sessionScope.login_user.empname}';
			$id("dataUser").value='${sessionScope.login_user.empid}';
			$id("empid").value='${sessionScope.login_user.empid}';
			var date = new Date();
			var result = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
			var result2 = date.getFullYear()+(date.getMonth()+1)+date.getDate();
			$("#atime").val(result);
			result = '<%=TimeUtil.getCntDtStr(new Date(),"yyyy-MM-dd")%>';
			$("#desTime").val(result);
			$("#hiddenTime").val('<%=TimeUtil.getCntDtStr(new Date(),"yyyyMMdd")%>');
			$.ajax({
		        url: '/file/tFileResourceTableAction_queryFileList.action',
		        async: false,
		        type: 'post',
		        data: "resourceType=06&resourceId=<b:write property="sendData/dsId" />",
		        dataType: 'json',
		        timeout: 60000,
		        success: function (files) {
			        if(files!=null){
			         	$.each(files,function( i,item ){
			         		if('${sendData.desId}'!='' || ${sendData.timeLimit}<$("#hiddenTime").val())
			    	        	$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId,unUse:1});
			    	        else
			    	        	$("#tag").fileDown({filename:item.fileName,filevalue:item.fileId});
			          	});
			        }
		        }
	      });
		if(${sendData.timeLimit}<$("#hiddenTime").val() && '${sendData.desId}'==''){
			alert('您已超过数据使用期限，请尽快销毁数据');
		}
		if('${sendData.desId}'!=''){
			$("#desRow").hide();
			}
		});
		 function openTree(){
			  var strUrl = "/tree/initMainTree_mainTree.action?changeTree.showTabOrg=1&changeTree.showTabGroup=0&changeTree.showTabRole=0&changeTree.orgType=6&changeTree.showSelBox=2&changeTree.checkcount=1";
				var peArgument = [];
				//人员
		    	var paramEntity = new ParamEntity('Employee');
				paramEntity.setProperty('empid',$id("empid").value);
				paramEntity.setProperty('empname',$id("empname").value);
				peArgument[0]=[paramEntity,'empname','empid'];
				//调用并传参
				strUrl += "&time="+new Date().getTime();
				//$name("assignerTab").src = strUrl;	 
				showModalCenter(strUrl,peArgument,openNewEmpTreeCallBack,500,430,'办理人选择');
		}
	
		function openNewEmpTreeCallBack(arg){
			var empids;
			var empnames;
			
			//回调方法
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
				$id("dataUser_show").value=empnames;
				$id("dataUser").value=empids;
			}else{
				$id("empid").value = "";
				$id("empname").value = "";
			}
		}
		function doSubmit(){
			maskTop();
			var options = {
	  		  		type : "post",
	  		  		cache : "false",
	  			  	success : function(data){
	  					try {  
	  					  	if(data.indexOf("success")>=0){
	      					  	alert("操作成功!");
  	 					  		unMaskTop();
	  					  		//reload();
	  					  		window.returnValue="1";
	      					    window.close();
	  					  	}else if(data.indexOf("fails")>=0){
	      					  	alert("操作失败!");
  	 					  		unMaskTop();
	  					  	}else{	 
	  					  	    alert("操作失败!");
  	 					  		unMaskTop();
	  					  	}
	  	   				} catch (Exception) {  
	  					}  
	  				},  
	  			  	error : function(data){
	  				  alert("审核失败请联系管理员！");
					  		unMaskTop();
	  			   	}  
	  		};
	  	  	$("#issuedForm").ajaxSubmit(options);
			
		}		
  </script>
</html>
