<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="javascript" src="/scripts/DateDialog.js" ></script>
<script language="javascript" src="/scripts/tools.js"></script>
<title>日期选择</title>

<style type="text/css">
	.table_dropshadow{FILTER:dropshadow(color=#EDEDF8,offx=3.3,offy=3.3,positive=1);}
	.font_dropshadow{FILTER: dropshadow(color=#F2F8DA,offx=1,offy=1);}
	td { font-size: 10pt; color: #666600}
	tr { height: 25px}
	.table_head_font{font-size: 14px;  color: #7B7B00}
	a:active { text-decoration: underline overline; color: #5F506D}
	span{ text-decoration: underline overline; color: black}
	.table_dashed { border: 1px dashed #333333; background-color: #FFF3E8}
	input.button{
	background-color: #eeeeee;
	font-weight: bold;
	font-family: "Tahoma";
	font-size: 10px;
	}
</style>
</head>
<body topmargin="5" leftmargin="5" onLoad="toggleDatePicker('daysOfMonthDiv',datearg.formElt)">
<table width="100%" border="0" cellspacing="0" cellpadding="1">
  <tr>
    <td bgcolor="#F0F0F0">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#FFFFFF">
          <td height="30" width="120" align="center">直接输入日期</td>
          <td height="30" width="100">
            <input type="text" id="InputDate" name="InputDate" size="12"  onblur="InputDate_Blur('InputDate')"  maxlength="10">
          </td>
          <td height="30" width="40">
            <input type="button" name="btnChang" value=" OK " class="button" onClick="setSrcDay()">
          </td>
          <td height="30">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<DIV id="daysOfMonthDiv"  style="POSITION:absolute;" ></DIV>
</body>
<script language="javascript">

function InputDate_Blur(src)
{
	var o=eval("document.getElementById('" + src+"')");
	if(!checkDateStr(o.value,"\\."))
	{
		alert("日期格式不对,请重新输入！格式:(yyyy.mm.dd)");
		o.focus();
		return;
	}
	toggleDatePickerFromInput('daysOfMonthDiv',datearg.formElt);
}
	function toggleDatePickerFromInput(eltName,formElt)
	{
		
		var x = formElt.indexOf('.');
		
		var formName = formElt.substring(0,x);
		var formEltName = formElt.substring(x+1);
		
		today = new getToday(document.getElementById("InputDate"));
		displayYear = today.year;
		displayMonth = today.month;
		
		newCalendar(eltName,datearg.win.document.forms[formName].elements[formEltName]);
	}

	 function setInputDate(day)
	 {
	   	var strMonth, strDay;
	   	if ((displayMonth+1) < 10) 
	   		strMonth = "0" + (displayMonth + 1); 
	   	else 
	   		strMonth = (displayMonth +1) + "";
		if ( day < 10)
		{
	   		strDay = "0" + clearZero(day);
	   	}
	   	else
	   	{
	   		strDay = day + "";
	   	}
		document.getElementById("InputDate").value = displayYear + "." + strMonth + "." + strDay;
	 }

	function clearZero(v)
	{
		v = "0"+v;
		var rg = /(^0*)/ig;
		return v.replace(rg,'');
	}

	 function onMouseDbl(day)
	 {
	   	var strMonth, strDay;
	   	if ((displayMonth+1) < 10) strMonth = "0" + (displayMonth + 1); else strMonth = (displayMonth +1) + "";
	   	if ( day < 10) strDay = "0" + day; else strDay = day + "";
	 	document.getElementById("InputDate").value = displayYear + "." + strMonth + "." + strDay;
		setSrcDay();
	 }

	function setSrcDay()
	{
		var o = eval("datearg.win.document." + datearg.formElt);
		o.value = InputDate.value;
		hideElementDiv(datearg.formElt);
	}

	function init()
	{
		var o = eval("datearg.win.document." + datearg.formElt);
		var v = o.value ;
		if( v == null || v == undefined || v == ""  || !checkDateStr(v,"\\."))
		{
			v = getCurDate();
		}
	    document.getElementById("InputDate").value = v;
	    
	}
	
        function toggleDatePicker(eltName,formElt)
        {

              
        	btnChang.focus();
            
          	var x = formElt.indexOf('.');
          	var formName = formElt.substring(0,x);
          	var formEltName = formElt.substring(x+1);
        	today = new getToday(datearg.win.document.forms[formName].elements[formEltName]);
        	displayYear = today.year;
        	displayMonth = today.month;
        	newCalendar(eltName,datearg.win.document.forms[formName].elements[formEltName]);
        }

	
	function getCurDate()           
	{
		tmpDate = new Date();
		date = tmpDate.getDate();
		var strDay ;
                if (date < 10)
                    strDay = "0" + date;
                else
                    strDay = date ;
		month= tmpDate.getMonth() + 1 ;
		var strMonth ;
            if (month < 10)
                strMonth = "0" + month;
            else
                strMonth = nowMonth ;
		year= tmpDate.getYear();
		alert("datetime is " + year + "." + strMonth + "." + strDay);
		return year + "." + strMonth + "." + strDay;

	}
	init();
</script>

</html>