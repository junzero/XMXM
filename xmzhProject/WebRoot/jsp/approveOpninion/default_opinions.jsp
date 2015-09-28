<%@page pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin0/component.jsp"%>
<h:css href="/css/style1/style-custom.css" />
<script type="text/javascript" src="/js/commonUtil.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>默认意见列表</title>
</head>
  
  <body>
  	<h:form name="form1" action="" method="post">
	     <table id="table1" class="EOS_table" width="100%">
		<tr>
		  <th nowrap="nowrap">意见</th>
		  <th nowrap="nowrap">操作</th>
		</tr>
			<l:iterate property="opinionList" id="opinion">
			<tr class="<l:output evenOutput='EOS_table_row' oddOutput='EOS_table_row_o'  />" id="opinionTr${opinion.recId }">
			  <td style="text-align: center;width:80%">
			    <nobr><h:text style="width:100%" id="opinion${opinion.recId }" iterateId="opinion" property="opinion"/></nobr>
			  </td>
			  <td style="text-align: center">
			    	<nobr><input class="button" type="button" value="保存" onclick="doSave('<b:write iterateId='opinion' property='recId'/>');"/>&nbsp;&nbsp;<input class="button" type="button" value="删除" onclick="doDelete('<b:write iterateId='opinion' property='recId'/>');"/></nobr>
			  </td>
			</tr>
			</l:iterate>
	      </table>
	      <div class="h3" style="width:100%;text-align: right">
		     <input id="detailBu" type="button" class="button" value="新增" onclick="addTr();">
	       </div>
	   </h:form>
  </body>
  <script type="text/javascript">
  	var count = 'a';
	function doSave(recId){
		var name = "opinion"+recId;
		var opinion = $id(name).value;
		if(opinion==""){
			alert("请填写意见");
			return;
		}
		if(!/^\d+$/.test(recId)){
			recId="";
		}
	  $.ajax({
	      url: "/opinion/tDefaultOpinionAction_saveOrUpdate.action?recId="+recId+"&opinion="+encodeURI(opinion),
	      async: false,
	      type: 'post',
	      dataType: 'json',
	      timeout: 60000,
	      success: function (data) {
	      	if(data.indexOf("success")>=0){
					  	alert("操作成功!");
				  		unMaskTop();
				  		window.location.reload();
				  		//reload();
				  	//WEB.turnMainFrame();20140905改
				  	}else if(data.indexOf("fails")>=0){
					  	alert("操作失败!");
				  		unMaskTop();
				  	}else{	 
				  	    alert("操作失败!"); 
				  		unMaskTop();
				  	}
	      }
	});
	};

	function doDelete(recId){
		if(/^\d+$/.test(recId)){
		 $.ajax({
		      url: '/opinion/tDefaultOpinionAction_deleteOp.action',
		      async: false,
		      type: 'post',
		      data: "recId="+recId,
		      dataType: 'json',
		      timeout: 60000,
		      success: function (data) {
		      	if(data.indexOf("success")>=0){
			      		$("#table1 tr[id='opinionTr"+recId+"']").remove();
						  	alert("操作成功!");
					  		unMaskTop();
					  		//reload();
					  	//WEB.turnMainFrame();20140905改
					  	}else if(data.indexOf("fails")>=0){
						  	alert("操作失败!");
					  		unMaskTop();
					  	}else{	 
					  	    alert("操作失败!"); 
					  		unMaskTop();
					  	}
		      }
		});
		}else{
      		$("#table1 tr[id='opinionTr"+recId+"']").remove();
      	}
	}
	function addTr(){
		$("#table1").append("<tr id='opinionTr"+count+"'>"+
				"<td style='text-align: center'><nobr><input type='text' style='width:100%' id='opinion"+count+"'/></nobr></td>"
				  +"<td style='text-align: center'><input class='button' type='button' value='保存' onclick=\"doSave('"+count+"');\"/>&nbsp;&nbsp;"
				  +"<input class='button' type='button' value='删除' onclick=\"doDelete('"+count+"');\"/></td></tr>");
		count+='a';
		}
  </script>
</html>
