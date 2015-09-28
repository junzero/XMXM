<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/common.jsp" %>

<%@ include file="/common/skins/skin1/component.jsp" %>

<head>

</head>
<html>
<body>

    <script language="JavaScript">

        function init(){

            //取得主页面传过来的参数

            $id('inArg').value= window["dialogArguments"];

        }

		eventManager.add(window, "load", init);

        function ok(){

            // 定义窗口关闭时的返回值

            window['returnValue'] = $id('outArg').value;

            window.closeD();

        }

    </script>



    主页面传入的值:<input type="text" id="inArg">

    <br />

    传回给主页面的值:<input type="text" id="outArg" value='out args' >

    <br />

    <input type="button" value='确定并关闭' onclick="ok()" />

</body>

</html>
