<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="com.eos.data.datacontext.IUserObject"%>
<% 
   String _headskin = "default";
   IUserObject user = (IUserObject)session.getAttribute(IUserObject.KEY_IN_CONTEXT);
   if(user!=null){
		java.util.Map map = (java.util.Map)user.getAttributes();
		if (map!=null){
		     String skin = (String) user.getAttributes().get("skin");
		     if(skin!=null&&!"".equals(skin.trim())){
		        _headskin = skin;
		     }
		}
   }
   String contextPathStr = request.getContextPath();
   String topJspStr = contextPathStr+"/common/skins/"+_headskin +"/top.jsp";
//   String toolbarJspStr = contextPathStr+"/common/skins/"+_headskin +"/toolbar.jsp";
   String toolbarJspStr = "";
   String slideJspStr = contextPathStr+"/common/skins/"+_headskin +"/slide.jsp";
   String bodyJspStr = contextPathStr+"/common/skins/"+_headskin +"/homeselectorg.jsp";
//   String bodyJspStr = "org.gocom.abframe.auth.Frame.flow";
   String copyrightJspStr = contextPathStr+"/common/skins/"+_headskin +"/copyright.jsp";
%>
<html>
<head>
<title>出版物连锁经营系统</title>
</head>

<frameset rows="70,21,*,18" frameborder="no" border="0" framespacing="0" id="frameMain" name="frameMain">
	<frame src='<%=topJspStr %>' scrolling="NO" frameborder="0" name="topFrame" id="topFrame" noresize />
	<frame src='<%=toolbarJspStr %>' scrolling="NO" name="toolbarFrame" noresize id="toolbarFrame"/>

	<frameset cols="220,10,*" frameborder="0"  border="0" framespacing="0"	id="contentFrame">
		<frame src='' name="menuFrame" id="menuFrame"/>
		<frame src='<%=slideJspStr %>' name="slideFrame" noresize="noresize" scrolling="no"/>
		<frame src='<%=bodyJspStr %>' name="bodyFrame" />
	</frameset>
	<frame src='<%=copyrightJspStr %>' scrolling="no" name="copyrightFrame" />
</frameset>
<body>
<b:message key="mframesetsupport" />
</body>
</html>
