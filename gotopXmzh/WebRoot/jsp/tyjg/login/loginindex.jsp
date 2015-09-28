<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<%@page import="java.util.*"%>
<h:css href="/css/style1/style-custom.css"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      厦门综合管理系统
    </title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		/* table页面自适应，但是宽度无法设置  */
		/* html,body{height:100%;}
		td {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;}
		#main{width:100%;min-height:100%;height:100%;overflow:hidden !important;overflow: visible;} */
		

	#framecontentTop
	{
		position: absolute;
		top: 0;
		left: 0;
		height: 79px;
		width: 100%;
		overflow: hidden;
	}

	#framecontentTop2
	{
		position: absolute;
		top: 79px;
		left: 0;
		height: 30px;
		width: 100%;
		overflow: hidden;
	}

	#framecontentLeft
	{
		position: absolute;
		top: 109px;
		left: 0;
		height: 100%;
		width: 190px;
		overflow: hidden;
		vertical-align: top;
	}

	#framecontentSplit
	{
		position: absolute;
		top: 109px;
		left: 190px;
		height: 100%;
		width: 10px;
		overflow: hidden;
		vertical-align: top;
	}

	#maincontent
	{
		position: absolute;
		left: 200px;/*Set left value to WidthOfLeftFrameDiv*/
		top: 109px;/*Set top value to HeightOfTopFrameDiv*/
		right: 0;
		bottom: 0;
		height: 100%;
		overflow: auto;
	}

	#framecontentButtom
	{
		bottom:0;
		position:fixed;
		_position:absolute;
		z-index:9999; 
		bottom:0px;      
        _top:expression(eval(document.compatMode &&     
        document.compatMode=='CSS1Compat') ?     
        documentElement.scrollTop     
        +(documentElement.clientHeight-this.clientHeight) - 0    
        : document.body.scrollTop     
        +(document.body.clientHeight-this.clientHeight) - 0);
        width:100%; 
        z-index:1;
	}
	div{margin:0px;padding:0px;border: 0px;}
	</style>

  </head>
	<%-- <frameset rows="79,30,*,20" cols="*"  frameborder="no" border="0" framespacing="0">
	  <frame src="${pageContext.request.contextPath }/jsp/tyjg/login/top.jsp" name="topFrame"   id="topFrame" />
	  <frame src="${pageContext.request.contextPath }/jsp/tyjg/login/topbar.jsp" name="topFrame2"   id="topFrame2" />
	  <frameset rows="*" cols="190,10,*" framespacing="0" frameborder="no" border="0" id="bottomFrame">
	    <frame src="${pageContext.request.contextPath }/jsp/tyjg/login/leftmenu.jsp" name="leftFrame"   id="leftFrame"/>
	    <frame src="${pageContext.request.contextPath }/jsp/tyjg/login/split.jsp" name="splitFrame"   id="splitFrame"/>
	    <frame src="${pageContext.request.contextPath }/jsp/tyjg/login/rightmain.jsp" name="mainFrame" id="mainFrame" />
	  </frameset>
	  <frame src="${pageContext.request.contextPath }/jsp/tyjg/login/buttom.jsp" name="buttom"   id="buttom" />
	</frameset>
	<noframes> --%>
		<body>
			<div id="framecontentTop">
				<iframe width="100%" scrolling="no" frameborder="0" height="79px" src="${pageContext.request.contextPath }/jsp/tyjg/login/top.jsp" name="topFrame" id="topFrame" ></iframe>
			</div>
			<div id="framecontentTop2">
				<iframe width="100%" scrolling="no" frameborder="0" height="30px"  src="${pageContext.request.contextPath }/jsp/tyjg/login/topbar.jsp" name="topFrame2" id="topFrame2" ></iframe>
			</div>
			<div id="framecontentLeft">	
				<iframe width="190px" scrolling="no" frameborder="0" height="100%" src="${pageContext.request.contextPath }/jsp/tyjg/login/leftmenu.jsp" name="leftFrame" id="leftFrame" ></iframe>
			</div>
			<div id="framecontentSplit">
				<iframe width="10px" scrolling="no" frameborder="0" height="100%" src="${pageContext.request.contextPath }/jsp/tyjg/login/split.jsp" name="splitFrame" id="splitFrame" ></iframe>
			</div>
			<div id="maincontent">
				<iframe width="100%" scrolling="no" frameborder="0" height="100%" src="${pageContext.request.contextPath }/jsp/tyjg/login/rightmain.jsp" name="mainFrame" id="mainFrame" ></iframe>
			</div>
			<div id="framecontentButtom">
				<iframe width="100%" scrolling="no" frameborder="0" height="20px" src="${pageContext.request.contextPath }/jsp/tyjg/login/buttom.jsp" name="buttom" id="buttom" ></iframe>
			</div>
		</body>
	<!-- </noframes> -->
</html>
