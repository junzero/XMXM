  	// 新增应用
  	function addRecord() {
		var url = "org.gocom.abframe.rights.application.ApplicationManager.flow";
		url = url + "?_eosFlowAction=add";
    	url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
    	
    	//弹出新增业务字典类型模态框
    	showModalCenter(url, "", callBackTypeAdd, 500, 420, '<%=application_add%>');//增加应用
  	}
	
	//新增完成回调函数
	function callBackTypeAdd()
    {
    	$name("app_viewlist").submit();
    	window.parent.parent.parent.frames["IFRAMEAppFuncTree"].DTree.getRootNode().reloadChild();
    }
     
    //修改应用
  	function modiRecord() {
  		var appids = $id("group1").getSelectRows();
  		var appid = appids[0].getParam("app_selectedObjects/appid");
    	var url = "org.gocom.abframe.rights.application.ApplicationManager.flow";
		url = url + "?_eosFlowAction=update";
		url = url + "&appid=" + appid;
    	url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
    	
    	//弹出新增业务字典类型模态框
    	showModalCenter(url, "", callBackTypeAdd, 500, 420, '<%=application_update%>');//修改应用
  	}

	// 删除应用
  	function delRecord() {
	    var g = $id("group1");
	    if (g.getSelectLength() < 1) {
	      alert('<b:message key="m_delete_illegalSelect"/>');
	      return;
	    }
	    var frm = $name("app_viewlist");
	    frm.elements["_eosFlowAction"].value = "buttonDelete";
	    if(confirm('<b:message key="applicationManager_m_applicationdelete_confirm"/>')){
	      frm.submit();
	    }
  	}

  	/* 
   	*  实现全选复选框
   	*/
  	function checkSelectAll(){
		if (docuemnt.getElementById("checkSelect").checked){
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
  	
  	setTimeout(custInit,3000);