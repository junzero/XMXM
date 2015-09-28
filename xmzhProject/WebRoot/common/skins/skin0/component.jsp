<%@ taglib uri="http://eos.primeton.com/tags/webcomp" prefix="w"%>
<%@ taglib uri="http://eos.primeton.com/tags/richweb" prefix="r"%>
<%@ taglib uri="http://eos.primeton.com/tags/dict" prefix="d"%>
<%@ taglib uri="http://eos.primeton.com/tags/logic" prefix="l"%>
<%@ taglib uri="http://gotop.net.cn/tags/permission" prefix="p"%>
<%@ page import="com.gotop.util.security.ForUtil"%>
<link href="/common/theme/style-sys.css" type="text/css" rel="stylesheet" >
<script type="text/javascript">
	var contextPath = "<%=request.getContextPath()%>"; // you should define the contextPath of web application
	var EOSDEBUG = false;
	var FINAL_PANEL_HEIGHT = 22;
	var IFRAME_MODIFY_HEIGHE = 35;
	var TREE_MODIFY_HEIGHT = 22;
</script>
<script  type="text/javascript"  src="/common/javascripts/zh_CN/message.js" ></script>
<link href="/common/skins/skin0/theme/style-component.css" type="text/css" rel="stylesheet" >
<link href="/common/skins/skin0/theme/style.css" type="text/css" rel="stylesheet" >

<script  type="text/javascript"  src="/common/skins/skin0/scripts/resource.js" ></script>
<script  type="text/javascript"  src="/common/fckeditor/fckeditor.js" ></script>
<script  type="text/javascript"  src="/common/scripts/eos-web.js" ></script>
<script  type="text/javascript"  src="/common/lib/mootools.js" ></script>
<script  type="text/javascript"  src="/common/gotop/web-common.js" ></script>
<script  type="text/javascript"  src="/common/gotop/eos-web.js" ></script>
<script  type="text/javascript"  src="/common/gotop/role_path.js" ></script>
<script  type="text/javascript"  src="/common/gotop/webkit_path.js" ></script>
<script  type="text/javascript"  src="/common/gotop/showModal_patch.js" ></script>
<script  type="text/javascript"  src="/common/skins/default/scripts/common.js" ></script>
<script type="text/javascript" src="/common/scripts/swfupload/swfupload.js"></script>
<script type="text/javascript" src="/common/scripts/swfupload/swfupload.swfobject.js"></script>
<script type="text/javascript" src="/common/scripts/swfupload/swfupload.queue.js"></script>
<script type="text/javascript" src="/common/scripts/swfupload/fileprogress.js"></script>
<script type="text/javascript" src="/common/scripts/swfupload/handlers.js"></script>
<script type="text/javascript" src="/common/lib/jquery/jquery1.9.js"></script>
