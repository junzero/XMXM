<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>
/*
Configure menu styles below
NOTE: To edit the link colors, go to the STYLE tags and edit the ssm2Items colors

YOffset=350; // 初始y坐标
XOffset=0; //初始x坐标
Direction=0;//方向0为左到右，1为从下到上
staticYOffset=30; //静态位置
slideSpeed=20 // 展开速度
waitTime=100; // 滞留时间，鼠标离开后多久菜单收回
menuBGColor="blue";//菜单背景颜色，缺省为black
menuIsStatic="yes"; //是否静态菜单
menuWidth=150; // 菜单宽带为10的倍数
menuCols=2;//菜单列数
hdrFontFamily="verdana";//菜单标题字体
hdrFontSize="2";//菜单标题大小
hdrFontColor="white";//菜单标题字体颜色
hdrBGColor="D2E9F9";//"#170088";菜单标题背景颜色
hdrAlign="middle";//左右对齐方式
hdrVAlign="center";//上下对齐方式
hdrHeight="15";//高度
linkFontFamily="Verdana";
linkFontSize="2";
linkBGColor="white";
linkOverBGColor="#FFFF99";
linkTarget="_top";
linkAlign="Left";
barBGColor="D2E9F9";//"#444444";侧边背景颜色
barFontFamily="Verdana";//侧边字体
barFontSize="2";//侧边字体大小
barFontColor="white";//侧边字体颜色
barVAlign="center";//对齐方式
barWidth=5; // 侧边宽度
barText="快捷菜单"; // <IMG> tag supported. Put exact html for an image to show.

///////////////////////////
// ssmItems[...]=[name, link, target, colspan, endrow?] - leave 'link' and 'target' blank to make a header
ssmItems.push(["我的快捷菜单"]); //create header
<l:iterate property="quickmenus" id="qmlist">
ssmItems.push(["<b:write property="funcname" iterateId="qmlist"/>", "<b:write property="funcaction" iterateId="qmlist"/>", "bodyFrame"]);
</l:iterate>
ssmItems.push(["帮助", "help.jsp", "bodyFrame"]); 

ssmItems.push(["系统功能"]); //create header
ssmItems.push(["重新登陆", "org.gocom.abframe.auth.Login.flow", "_top",1,"no"]);
ssmItems.push(["退出", "org.gocom.abframe.auth.Login.flow?_eosFlowAction=logout", "bodyFrame",1]);
buildMenu();

*/