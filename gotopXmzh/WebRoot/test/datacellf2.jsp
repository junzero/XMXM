<%@page pageEncoding="UTF-8"%><%@page import="com.primeton.utils.*"%><%@page import="java.util.HashMap"%><%
	AjaxSet ajax = new AjaxSet(request);
	ajax.init();
	java.util.Map map = ajax.getValues();
	Page pageCond = new Page();
	System.out.println(map);
	if(map.get("page/begin")!=null){
		pageCond.setBegin(Integer.parseInt(map.get("page/begin").toString()));
	}
	if(map.get("page/length")!=null){
		pageCond.setLength(Integer.parseInt(map.get("page/length").toString()));
	}
	HashMap hm = new HashMap();
/*
	out.print(dddo.getData(pageCond,hm));
	String data = dddo.getData(pageCond,hm);
	System.out.println(data);
*/
	pageCond.setBegin(0);
	pageCond.setLength(10);
%>
