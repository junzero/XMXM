<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>
<%--
- Author(s): lz
- Date: 2011-10-05 16:16:53
- Description:
--%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      EOS Rich Web
    </title>
  </head>
  <body leftmargin="0" topmargin="0" rightmargin="0" scroll="no">
  <w:tabPanel defaultTab="webcomp" id="tabPan" height="100%" width="100%">
	<w:tabPage cache="false" tabType="iframe" id="webcomp" title="webcomp类控件" url="/richweb_demo.do?method=initSTree">
		<h:param name="action_type" value="webcomp" />
	</w:tabPage>
	<w:tabPage cache="false" tabType="iframe" id="ajax" title="ajax类控件" url="/test/richweb_demo_tld.jsp">
		<h:param name="action_type" value="ajax" />
	</w:tabPage>
	<w:tabPage cache="false" tabType="iframe" id="dict" title="dict类标签" url="/test/dict_demo_tld.jsp">
		<h:param name="action_type" value="dict" />
	</w:tabPage>
	<w:tabPage cache="false" tabType="iframe" id="datacell" title="普通页面1" url="/test/AbfTDbsybDatacell2.jsp">
		<h:param name="action_type" value="datacell" />
	</w:tabPage>
	<w:tabPage cache="false" tabType="iframe" id="viewlist" title="普通页面2" url="/richweb_demo.do?method=viewDataList">
		<h:param name="action_type" value="viewlist" />
	</w:tabPage>
	<w:tabPage cache="false" tabType="iframe" id="other" title="其它标签" url="/test/other_demo_tld.jsp">
		<h:param name="action_type" value="other" />
	</w:tabPage>
  </w:tabPanel>
  <%--
  <iframe style="display: none;" src="/reloadEosDict.flow"></iframe>
  --%>
  </body>
</html>
