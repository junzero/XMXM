function padContextpath(url,parainfo,contextPath){
	  var headexpr = new RegExp("^/");
	  var tailexpr = new RegExp("\\.jsp(\\?.*){0,1}$");
	  var root = contextPath;
	  if(root=="/") root="";
	  if(parainfo != null){
	  	if(url.indexOf("?")>=0){
	  		url += "&"+parainfo;
	  	}else{
	  		url += "?"+parainfo;
	  	}
	  }
	  if(tailexpr.test(url)){
	    if(headexpr.test(url)){
	      return root+url;
	    }else{
	      return root+"/"+url;
	    }
	  }else{
	    return url;
	  }
}

function menuClick(node){
	  var target = 'mainFrame';
	  var url = node.getProperty("FUNCACTION");
	  var isleaf = node.getProperty("ISLEAF");
	  var parainfo = node.getProperty('PARAINFO');
	  if(isleaf=='y' && url!=null){
	     parent.frames[target].location.href = padContextpath(url,parainfo,"/");
	  }else{
		//不是最后一个节点，菜单组
		  url='acFunctionGroup.funcGroupId='+ node.getProperty("FUNCCODE")+"&acFunctionGroup.groupLevel="+node.getProperty("GROUPLEVEL");
		parent.frames[target].location.href ="/login/getFuncGroupInfo_login.action?"+url;
	  	node.expandNode();
	  }
}

function _stree_linkToParnet(O, D, B) {
	var P = D.getLength(), N = D.values, H = B.getLength(), C = B.values, E = O.entityName, J = O.parnetFeild, K = O.entityField, G = J
			.split(","), A = K.split(",");
			
	if (G.length == 1) {
		for (var I = 0; I < P; I++) {
			var F = N[I].getProperty(K);
			var Fstr = N[I].getProperty("FUNCNAME");
			for (var M = 0; M < H; M++) {
				var L = C[M].getProperty(J);
				var Lstr = C[M].getProperty("FUNCNAME");
				if (L!=undefined && F!=undefined && L == F) {
					N[I].type = E;
					if (C[M].childList == null)
						C[M].childList = new Array();
					C[M].childList.push(N[I]);
					break
				}
			}
		}
	} else
		for (I = 0; I < P; I++) {
			F = N[I].getProperty(K);
			for (M = 0; M < H; M++) {
				L = C[M].getProperty(J);
				if (L!=undefined && F!=undefined && L == F) {
					N[I].type = E;
					if (C[M].childList == null)
						C[M].childList = new Array();
					C[M].childList.push(N[I]);
					break
				}
			}
		}
}

function treeNodeRefresh(node){//    
   //设为叶子节点
   if(node.getProperty("ISLEAF")=="y"){
   	   node.setIcon("/common/skins/default/images/building.gif");
	   node.setLeaf();
   }
}