<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<title>jQuery EasyUI</title>

<link rel="stylesheet" type="text/css"

href="../themes/default/easyui.css">

<link rel="stylesheet" type="text/css" href="../themes/icon.css">

<script type="text/javascript" src="../jquery-1.4.2.min.js"></script>

<script type="text/javascript" src="../jquery.easyui.min.js"></script>

<script>

function loadData(){

jQuery('#cc').combobox({

url:'combobox_data.json',//该文件内容在下面

valueField:'id',

textField:'text'

});

}

function setValue(){

jQuery('#cc').combobox('setValue','2');

}

function getValue(){

var val = jQuery('#cc').combobox('getValue');

alert(val);

}

function disable(){

jQuery('#cc').combobox('disable');

}

function enable(){

jQuery('#cc').combobox('enable');

}

jQuery( function() {

jQuery('#cc').combobox( {

width:150,

listWidth:150,

listHeight:100,

//valuefield:'value',

//textField:'text',

//url:'combobox_data.json',

editable:false

});

});

</script>

</head>

<body>

<h1>ComboBox</h1>

<div style="margin-bottom: 10px;"><a href="#" onclick="loadData()">loadData</a>

<a href="#" onclick="setValue()">setValue</a> <a href="#"

onclick="getValue()">getValue</a> <a href="#" onclick="disable()">disable</a>

<a href="#" onclick="enable()">enable</a></div>


<span>Options: </span>

<select id="cc" name="dept" required="true">

<option value="">==请选择==</option>

<option value="0">苹果</option>

<option value="1">香蕉</option>

<option value="2">鸭梨</option>

<option value="3">西瓜</option>

<option value="4">芒果</option>

</select>

</body>

</html>

