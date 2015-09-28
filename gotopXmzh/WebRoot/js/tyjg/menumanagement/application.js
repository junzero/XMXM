// 新增应用
  	function addApp() {
		var url = "org.gocom.abframe.rights.application.ApplicationManager.flow";
		url = url + "?_eosFlowAction=add";
    	url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
    	
    	//弹出新增应用模态框
    	showModalCenter(url, "", callBackTypeAdd, 500, 350, '<%=application_add %>');//增加应用
  	}
	
	//新增完成回调函数
	function callBackTypeAdd()
    {
    	$id("DTree").getRootNode().reloadChild();
    }
    
    //查询应用
  	function queryApp(node){
    	parent.window.frames["IFRAMEAppFuncInfo"].location.href="org.gocom.abframe.rights.application.ApplicationManager.flow?_eosFlowAction=entry&appid="+node.getProperty("appid");
  	} 
	
	//删除应用
  	function deleteAppAjax(node){
		var parentNode=node.getParent();
		if(confirm('<b:message key="applicationManager_m_appdelete_confirm"/>')){
			var myAjax = new Ajax("org.gocom.abframe.rights.application.ApplicationManager.deleteApplication.biz");
			myAjax.addParam("application/appid", node.getProperty("appid"));
			myAjax.submit();
			var returnNode =myAjax.getResponseXMLDom();

			if( returnNode ) {
	        	if( myAjax.getValue("root/data/appDeleteResult") == 1 ) {
	                alert('<b:message key="applicationManager_m_delete_success"/>');
	        	} else {
	                alert('<b:message key="applicationManager_m_delete_failure"/>');
	          	}

	    	}
	    	//刷新树更节点
			callBackTypeAdd(); 
		}
  	} 
	
	//查询功能
  	function queryFunc(node)
  	{
    	parent.window.frames["IFRAMEAppFuncInfo"].location.href="org.gocom.abframe.rights.application.ApplicationManager.flow?_eosFlowAction=auto";
  	} 

	//查询功能组信息及其子功能组列表
  	function queryFuncGroup(node)
  	{
    	parent.window.frames["IFRAMEAppFuncInfo"].location.href="org.gocom.abframe.rights.application.FunctionGroupManager.flow?_eosFlowAction=entry&funcgroupid="+node.getProperty("funcgroupid")+"&funcgroupname=%u6388%u6743%u8BA4%u8BC1";
  	}
  	
	//新增功能组
	function addFuncGroup(node) {
		var url = "/jsp/tyjg/menumanagement/add_func_group.jsp";
	    url = url + "?appid="+node.getProperty("appId");
        url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
        //弹出新增应用模态框
        showModalCenter(url, "", callBackFuncGroup, 500, 350, '新增功能组');//增加功能组
	} 
	
	//新增完成回调函数
	function callBackFuncGroup()
    {
    	$id("DTree").getSelectNode().reloadChild();
    }
   	
   	//增加功能
   	function addFunction(node)
   	{
   		var url="org.gocom.abframe.rights.application.FunctionAdd.flow?_eosFlowAction=add&funcgroupid="+node.getProperty("funcgroupid");
		showModalCenter(url,null,null,520,250,'<b:message key="applicationManager_l_functionAdd"></b:message>');
   	}
   	
   	//新增子功能组 
  	function addSubFuncGroup(node)
  	{
   	 	var url = "org.gocom.abframe.rights.application.FuncGroupSubAdd.flow";
		url = url + "?_eosFlowAction=add";
		url = url + "&funcgroupid="+node.getProperty("funcgroupid");
		url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
		    	
		//弹出新增功能组模态框
		showModalCenter(url, "", callBackFuncGroup, 450, 200, '<%=subfuncGroup_add%>');//增加子功能组
  	}
  	
	//删除功能组
  	function deleteFuncGroup(node)
  	{
    	var parentNode=node.getParent();
		if(confirm('<b:message key="applicationManager_m_functdelete_confirm"/>')){
			var myAjax = new Ajax("org.gocom.abframe.rights.application.FuncGroupManager.deleteFuncGroup.biz");
			myAjax.addParam("funcGroup/funcgroupid", node.getProperty("funcgroupid"));
			myAjax.submit();
			var returnNode =myAjax.getResponseXMLDom();

			if( returnNode ) {
	        	if( myAjax.getValue("root/data/funcGroupDelResult") == 1 ) {
	                alert('<b:message key="applicationManager_m_delete_success"/>');
	        	} else {
	                alert('<b:message key="applicationManager_m_delete_failure"/>');
	          	}
	    	}
       		parentNode.reloadChild(); // 刷新父节点
        	if(node.getProperty("grouplevel") > 1){ //若删除的功能组不是一级功能组节点，刷新父节点的同时查询父节点
          		parentFuncgroupid = parentNode.getProperty("funcgroupid");
          		parent.window.frames["IFRAMEAppFuncInfo"].location.href="org.gocom.abframe.rights.application.FunctionGroupManager.flow?_eosFlowAction=query&funcgroupid="+parentFuncgroupid;
        	}
    	}
	}

  	function queryFuncResource(node)
  	{
    	parent.window.frames["IFRAMEAppFuncInfo"].location.href="org.gocom.abframe.rights.application.ResourceManager.flow?_eosFlowAction=entry&funccode="+node.getProperty("funccode");
  	}
	
	//刷新根
  	function refresh(root)
  	{
    	window.parent.frames["IFRAMEAppFuncTree"].location.reload();
  	} 
	
	//刷新节点
  	function refreshNode(node)
  	{
    	node.reloadChild();
  	} 
	
	//双击树节点，重载树
  	function dbClickNode(node)
  	{
		if(node.isExpanded()){
	   		node.collapseNode(); //关闭当前节点
		}else{
	   		node.expandNode(); //展开当前节点的下级子节点
	 	}
  	} 

  	/*
   	*  自定义初始化树高度
   	*/
  	function custInit()
  	{
    	var height = document.body.clientHeight - FINAL_PANEL_HEIGHT;
    	document.getElementById("DTree").style.height =height;
  	}
	/**
	*  增加锁定功能
	**/
	function addDummyPermission(node){
		alert('222')
	}
	
	function addFuncDummy(node){
   		var url="org.gocom.abframe.rights.acfuncdummy.AcFuncDummyMaintain.flow?_eosFlowAction=insert"
   		+"&acfuncdummy/acFunction/funccode="+node.getProperty("funccode")
   		+"&acfuncdummy/funcgroupid="+node.getProperty("acFuncgroup/funcgroupid");
		url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
		
		showModalCenter(url,null,null,500,200,'新增虚拟节点');
	}
	
	function updateFuncDummy(node){
		var url = "org.gocom.abframe.rights.acfuncdummy.AcFuncDummyMaintain.flow?_eosFlowAction=update&acfuncdummy/funccode="+node.getProperty("funccode");
		url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
		parent.window.frames["IFRAMEAppFuncInfo"].location.href=url;
	}
	
	function deleteFuncDummy(node){
    	var parentNode=node.getParent();
		if(confirm('是否确定删除虚拟功能节点')){
			var myAjax = new Ajax("org.gocom.abframe.rights.acfuncdummy.acfuncdummybiz.deleteAcFuncDummys.biz");
			myAjax.addParam("acfuncdummys/funccode", node.getProperty("funccode"));
			myAjax.submit();
			var returnNode =myAjax.getResponseXMLDom();

			if( returnNode ) {
	        	if( myAjax.getValue("root/data/funcGroupDelResult") == 1 ) {
	                alert('<b:message key="applicationManager_m_delete_success"/>');
	        	} else {
	                alert('<b:message key="applicationManager_m_delete_failure"/>');
	          	}
	    	}
       		parentNode.reloadChild(); // 刷新父节点
        	if(node.getProperty("grouplevel") > 1){ //若删除的功能组不是一级功能组节点，刷新父节点的同时查询父节点
          		queryFsuncDummy(parentNode)
        	}
    	}

	}
	
	function deleteFunction(node){
		if(confirm('是否确定删除当前功能节点')){
			var myAjax = new Ajax("org.gocom.abframe.rights.application.FuncGroupManager.deleteFunction.biz");
			myAjax.addParam("function/funccode", node.getProperty("funccode"));
			myAjax.submit();
			var returnNode =myAjax.getResponseXMLDom();

			if( returnNode ) {
	        	if( myAjax.getValue("root/data/rtn") == 1 ) {
	                alert('<b:message key="applicationManager_m_delete_success"/>');
	        	} else {
	                alert('<b:message key="applicationManager_m_delete_failure"/>');
	          	}
	    	}
       		node.getParent().reloadChild(); // 刷新父节点
		}
	}
	
	function queryFsuncDummy(node){
    	parent.window.frames["IFRAMEAppFuncInfo"].location.href="org.gocom.abframe.rights.acfuncdummy.AcFuncDummyMaintain.flow?_eosFlowAction=update&select_objs[1]/funccode="+node.getProperty("funccode");
	}
	setTimeout(custInit,3000);