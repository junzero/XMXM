<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%
String str = "福州|厦门|莆田|三明|泉州|漳州|南平|龙岩|宁德";
String param = request.getParameter("pmain");
if("2".equals(param)){
	str = "南昌市|景德镇|九江|鹰潭|萍乡|新馀|赣州|吉安|宜春|抚州|上饶";
}else if("3".equals(param)){
	str = "济南|青岛|淄博|枣庄|东营|烟台|潍坊|济宁|泰安|威海|日照|莱芜|临沂|德州|聊城|滨州|菏泽";
}
String[] fz = str.split("\\|");
String ostr = "[";
for(int i=0;i<fz.length;i++){
	ostr += "{\"id\":\""+fz[i]+"\",\"text\":\""+fz[i]+"\"}";
	if(i<fz.length-1){
		ostr += ",";
	}
}
ostr += "]";
out.print(ostr);
%>