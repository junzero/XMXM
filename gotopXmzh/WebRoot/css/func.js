
//*******************************************************
//功能：页面消息处理
//作者：周钉剑
//时间：2006.02.15
//*******************************************************

function ShowMessage(Message)
{
 var ThisMessage = document.all("MessageDiv");
 if (ThisMessage == null) return; 
 var ThisTd = document.all("MessageTd");
 ThisTd.innerHTML = Message;
 if (window.event != null) window.event.cancelBubble = true;
 var left = 0, top = 0, width = 300, height = 18;
 left = (screen.width - width) / 2;
 top = (screen.height - height - 55) / 2;
 ThisMessage.style.left = "" + left + "px";
 ThisMessage.style.top = "" + top + "px";
 ThisMessage.style.display = "block";	
}
function HideMessage_ByColor(path_img,bk_color,border_color)
{
 var ThisMessage = document.all("MessageDiv");
 var bkcolor = bk_color;
 var bordercolor = border_color;
 if (ThisMessage == null)
 {      
  var tmpStr = "<div id='MessageDiv' style='display:none;position:absolute;width:300px; height:18px; z-index:1; background-color: " + bkcolor + "; layer-background-color: " + bkcolor + "; border: 1px none #000000;'> ";
  tmpStr = tmpStr + "<table border=1 cellspacing='0' width=300 height=18 bordercolordark='" + bordercolor + "' bordercolorlight='#FFFFFF'><tr><td><table width='98%' border='0' cellspacing='0'>";
  tmpStr = tmpStr + "<tr><td width='18' height='18' align='right'><img src='" + path_img + "/images/process.gif' width='32' height='32'></td>";
  tmpStr = tmpStr + "<td>&nbsp;<label id='MessageTd'></label></td></tr></table></tr></td></table></div>";
  document.write(tmpStr); 
  return;  
 }
 ThisMessage.style.display = "none";
 if (window.event != null) window.event.cancelBubble = true;
}
function HideMessage(path_img)
{
  var bkcolor = '#00EA75';
  var bordercolor = '#1EBF29';
  HideMessage_ByColor(path_img,bkcolor,bordercolor);
}

function DetectActiveX(act_name)
{
  try
  {
    var comActiveX = new ActiveXObject(act_name);     
  }catch(failed) { return false; }
  return true;
}
//*******************************************************
//功能：有关窗口处理
//作者：周钉剑
//时间：2005.07.15
//*******************************************************

function window_onbeforeunload(url,message)
{
   alert("ddddd");
}
function RefreshParent()
{
   window.opener.location.href=window.opener.location.href;
}

function OpenWindow(Width,Height,Url)
{
  var left = 0,top = 0;
  if (Width == 0)
    Width = screen.width - 10;
  else
    left = (screen.width - Width) / 2;
  if (Height == 0)
    Height = screen.height - 55;
  else
    top = (screen.height - Height - 55) / 2;
  var feature = "left=" + left + ",top=" + top + ",width=" + Width + ",height=" + Height + ",status=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
  window.open(Url,"",feature);
}
function OpenDialogWindow(Width,Height,Url)
{
  var feature = "dialogWidth:" + Width + "px;dialogHeight:" + Height + "px;center:yes;status:no";
  retStr = window.showModalDialog(Url,"对话框",feature);
  return retStr;
}

//*******************************************************
//功能：Form中checkbox控件处理
//作者：周钉剑
//时间：2005.08.15
//*******************************************************

function DeleteItemAlert(FormName,FieldName,alertStr)
{
 var destform = document.forms(FormName);
 var retStr = "";
 if (confirm(alertStr) == false) return false;
 if (destform == null) return false;
 var len = destform.elements.length;
 for (i = 0 ; i < len ; i++)
 {
	if (destform.elements[i].type == "checkbox")
		if (destform.elements[i].checked)
			if (retStr == "") retStr = destform.elements[i].value;
			else retStr = retStr + "," + destform.elements[i].value;
 }
 destform(FieldName).value = retStr;
 return true;
}

function DeleteItem(FormName,FieldName)
{
 var destform = document.forms(FormName);
 var retStr = "";
 if (confirm("是否需要真的删除?") == false) return false;
 if (destform == null) return false;
 var len = destform.elements.length;
 for (i = 0 ; i < len ; i++)
 {
	if (destform.elements[i].type == "checkbox")
		if (destform.elements[i].checked)
			if (retStr == "") retStr = destform.elements[i].value;
			else retStr = retStr + "," + destform.elements[i].value;
 }
 destform(FieldName).value = retStr;
 return true;
}

function ZxItem(FormName,FieldName)
{
 var destform = document.forms(FormName);
 var retStr = "";
 if (confirm("是否需要真的注销?") == false) return false;
 if (destform == null) return false;
 var len = destform.elements.length;
 for (i = 0 ; i < len ; i++)
 {
	if (destform.elements[i].type == "checkbox")
		if (destform.elements[i].checked)
			if (retStr == "") retStr = destform.elements[i].value;
			else retStr = retStr + "," + destform.elements[i].value;
 }
 destform(FieldName).value = retStr;
 return true;
}

function RenewItem(FormName,FieldName)
{
 var destform = document.forms(FormName);
 var retStr = "";
 if (confirm("是否需要真的启用?") == false) return false;
 if (destform == null) return false;
 var len = destform.elements.length;
 for (i = 0 ; i < len ; i++)
 {
	if (destform.elements[i].type == "checkbox")
		if (destform.elements[i].checked)
			if (retStr == "") retStr = destform.elements[i].value;
			else retStr = retStr + "," + destform.elements[i].value;
 }
 destform(FieldName).value = retStr;
 return true;
}

function SelectItem(FormName,FieldName)
{
 var destform = document.forms(FormName);
 var retStr = "";
 if (destform == null) return false;
 var len = destform.elements.length;
 for (i = 0 ; i < len ; i++)
 {
	if (destform.elements[i].type == "checkbox")
		if (destform.elements[i].checked)
			if (retStr == "") retStr = destform.elements[i].value;
			else retStr = retStr + "," + destform.elements[i].value;
 }
 destform(FieldName).value = retStr;
 return true;
}

function GetSelectAllValue(FormName,FieldName)
{
 var retStr = "";
 var Field;
 if (FormName != "")
 {
  var destform = document.forms(FormName);
  Field = destform(FieldName);
 }
 else
   Field = document.all(FieldName); 
 if (Field == null) return "";
 var len = Field.options.length;
 var tmpStr = "";
 for (i = 0; i < len; i++)
 {
  if (tmpStr == "") tmpStr = Field.options[i].value;
  else tmpStr = tmpStr + "," + Field.options[i].value;
 }
 return tmpStr;
}

function GetSelectAllText(FormName,FieldName)
{
 var retStr = "";
 var Field;
 if (FormName != "")
 {
  var destform = document.forms(FormName);
  Field = destform(FieldName);
 }
 else
   Field = document.all(FieldName); 
 if (Field == null) return "";
 var len = Field.options.length;
 var tmpStr = "";
 for (i = 0; i < len; i++)
 {
  if (tmpStr == "") tmpStr = Field.options[i].text;
  else tmpStr = tmpStr + "," + Field.options[i].text;
 }
 return tmpStr;
}
function GetRadioValue(FormName,FieldName)
{
 var destform = document.forms(FormName);
 if (destform == null) return "";
 var retStr = "";
 var len = destform.elements.length;
 for (i = 0; i < len; i++)
 {
	if (destform.elements[i].type == "radio")
		if (destform.elements[i].checked)
			if (retStr == "") retStr = destform.elements[i].value;
			else retStr = retStr + "," + destform.elements[i].value;
 }
 destform(FieldName).value = retStr;
 return true; 
}
function GetRadioValueNew(FormName,FieldName)
{
 var destform = document.forms(FormName);
 if (destform == null) return "";
 var retStr = "";
 var len = destform.elements.length;
 for (i = 0; i < len; i++)
 {
	if (destform.elements[i].type == "radio")
		if (destform.elements[i].checked)
			if (retStr == "") retStr = destform.elements[i].value;
			else retStr = retStr + "," + destform.elements[i].value;
 }
 destform(FieldName).value = retStr;
 //return true;
 return retStr;
}
function GetSelectValue(FormName,FieldName)
{
  if (FormName == "")
  {
   var FieldObj = document.all(FieldName);
   if (FieldObj == null) return "";
   return FieldObj.options[FieldObj.selectedIndex].value;
  }
  var destform = document.forms(FormName);  
  if (destform(FieldName) == null) return "";
  if (destform(FieldName).selectedIndex == -1) return ""
  return destform(FieldName).options[destform(FieldName).selectedIndex].value;
}

function GetSelectText(FormName,FieldName)
{
  if (FormName == "")
  {
   var FieldObj = document.all(FieldName);
   if (FieldObj == null) return "";
   return FieldObj.options[FieldObj.selectedIndex].text;
  }
  var destform = document.forms(FormName);  
  if (destform(FieldName) == null) return "";
  if (destform(FieldName).selectedIndex == -1) return ""
  return destform(FieldName).options[destform(FieldName).selectedIndex].text;
}

function CheckVal(DesVal,Val)
{
 var pur_val = document.all(DesVal);
 var i;
 for (i = 0; i < pur_val.options.length; i++)
  if (pur_val.options[i].value == Val) return true;
 return false;
}

function AddId(SurVal,DesVal,IsDelete)
{
 var pur_sel = document.all(SurVal);
 var pur_val = document.all(DesVal);
 if (pur_val.disabled == true) return;
 if (pur_sel.selectedIndex == -1) return;
 var tmpval = pur_sel.options[pur_sel.selectedIndex].value; 
 if (CheckVal(DesVal,tmpval) == false)
 {
   pur_val.options[pur_val.options.length] = new Option(pur_sel.options[pur_sel.selectedIndex].text,tmpval,false,false);
   if (IsDelete == true)  DelId(SurVal);
 }
}

function AddAllId(SurVal,DesVal,IsDelete)
{
 var pur_sel = document.all(SurVal);
 var pur_val = document.all(DesVal);
 if (pur_val.disabled == true) return;
 var len = pur_sel.options.length;
 for (i = 0; i < len; i++)
 {
  if (IsDelete == true) tmpval = pur_sel.options[0].value; 
  else tmpval = pur_sel.options[i].value; 
  if (CheckVal(DesVal,tmpval) == false)
  {
   if (IsDelete == true)
   {
    pur_val.options[pur_val.options.length] = new Option(pur_sel.options[0].text,tmpval,false,false);
    pur_sel.options.remove(0);
   }
   else
    pur_val.options[pur_val.options.length] = new Option(pur_sel.options[i].text,tmpval,false,false);
  }
 }
}

function AddSelectedId(SurVal,DesVal,SelectedId)
{
 var pur_sel = document.all(SurVal);
 var pur_val = document.all(DesVal);
 if (pur_val.disabled == true) return;
 var len = pur_sel.options.length;
 for (i = 0; i < len; i++)
 {
  var tmpval = pur_sel.options[i].value; 
  if (CheckVal(DesVal,tmpval) == false)
  {
   if (SelectedId.indexOf(tmpval) >= 0)
   {
    pur_val.options[pur_val.options.length] = new Option(pur_sel.options[i].text,tmpval,false,false);
   } 
  }
 }
}

function DelId(DesVal)
{
 var pur_val = document.all(DesVal);
 if (pur_val.selectedIndex == -1) return;
 var tmpval = pur_val.options[pur_val.selectedIndex].value;
 if (tmpval != "") pur_val.options.remove(pur_val.selectedIndex);
}

//*******************************************************
//功能：Form中控件检查，改变状态处理
//作者：周钉剑
//时间：2005.07.15
//*******************************************************

function ChangeButtonEnable(FormName,Enabled)
{
 var destform = document.forms(FormName);
 if (destform == null) return;
 var len = destform.elements.length;
 for (i = 0 ; i < len ; i++)
 {
	if (destform.elements[i].type == "button" || destform.elements[i].type == "submit")
    destform.elements[i].disabled = Enabled;
 } 
}
function CheckInputIsBlank(FormName,FieldName,Message)
{
 var forms = document.forms(FormName);
 if (forms(FieldName) == null) return true;
 if (Trim(forms(FieldName).value) == "")
 {
  alert(Message);
  forms(FieldName).select();
  return false;
 }
 return true;
}

function CheckSelectIsBlank(FormName,FieldName,Message)
{
 var forms = document.forms(FormName);
 if (forms(FieldName) == null) return true;
 if (forms(FieldName).selectedIndex == -1)
 {
  alert(Message);
  forms(FieldName).focus();
  return false;  
 }
 if (forms(FieldName).options[forms(FieldName).selectedIndex].value == "")
 {
  alert(Message);
  forms(FieldName).focus();
  return false;
 }
 return true;
}

//*******************************************************
//功能：Form中数据类型检查
//作者：周钉剑
//时间：2005.07.15
//*******************************************************

function ValIsNumber(ValStr,Message)
{
  if (ValStr == "") return true;
  for (I = 0; I < ValStr.length; I++)
  {
    NumStr = ValStr.substring(I,I + 1);
	if (NumStr < "0" || NumStr > "9")
	{
     alert(Message);
     return false;
	}
  }
  return true;
}
function IsNumber(FormName,FieldName,Message)
{
  var forms = document.forms(FormName);
  if (forms == null) return true;
  var I,NumStr;
  if (forms(FieldName) == null) return true;
  SurStr = forms(FieldName).value;
  ret = ValIsNumber(SurStr,Message);
  if (ret == false) forms(FieldName).select();
  return ret;
}

function IsDouble(FormName,FieldName,Message)
{
  var forms = document.forms(FormName);
  if (forms == null) return true;
  var I,NumStr;
  if (forms(FieldName) == null) return true;
  SurStr = forms(FieldName).value;
  ret = ValIsDouble(SurStr,Message);
  if (ret == false) forms(FieldName).select();
  return ret;
}

function ValIsDouble(SurStr,Message)
{
  var I,NumStr;
  nPos = SurStr.indexOf(".");
  if (nPos == -1)
  {
    return ValIsNumber(SurStr,Message);
  }
  ZsNumStr = SurStr.substring(0,nPos);
  XsNumStr = SurStr.substring(nPos + 1,SurStr.length);
  if (ZsNumStr == "" || XsNumStr == "")
  {
	 alert(Message);
	 return false;
  }
  return true;
}
function compareNumber(Number1,Number2)
{
  Number1 = TrimStrLeft(Number1,'0');
  Number2 = TrimStrLeft(Number2,'0');
  if (Number1.length > Number2.length) return 1;
  if (Number1.length < Number2.length) return -1;
  if (Number1.length == Number2.length)
  {
	for (i = 0; i < Number1.length; i++)
	{
	  if (Number1.charAt(i) < Number2.charAt(i)) return -1;
	  if (Number1.charAt(i) > Number2.charAt(i)) return 1;
	}
	return 0;
  }
  return -1;
}
//*******************************************************
//功能：字符串处理
//作者：周钉剑
//时间：2001.07.15
//*******************************************************

function Trim(Str)
{
  var I;
  var SrcStr = Str;
  for (I = 0; I < SrcStr.length && SrcStr.charAt(I) == " "; I++ );
    var DestStr = SrcStr.substring(I,SrcStr.length);
  for (I = DestStr.length - 1; I >= 0 & DestStr.charAt(I) == " ";I--);
    return DestStr.substring(0,I + 1);
}

function TrimStrLeft(Str,Chr)
{
  var I;
  var SrcStr = Str;
  for (I = 0; I < SrcStr.length && SrcStr.charAt(I) == Chr; I++ );
  return SrcStr.substring(I,SrcStr.length);
}
function TrimStrRight(Str,Chr)
{
  var I;
  var SrcStr = Str;
  for (I = SrcStr.length - 1; I >= 0 & SrcStr.charAt(I) == Chr;I--);
  return SrcStr.substring(0,I + 1);  
}
function TrimStr(Str,Chr)
{
  var SrcStr = TrimStrLeft(Str,Chr);
  return TrimStrRight(SrcStr,Chr);
}

function TrimChar_All(Str,ChrCode)
{
  var I = 0;
  var retStr = "";
  for (I = 0; I < Str.length; I++)
  {
    if (Str.charCodeAt(I) == ChrCode) continue;
    else retStr = retStr + Str.charAt(I);
  }
  return retStr;
}

function StrRight(Str,nLen)
{
  //从右边取指定长度的字符
  var retStr = "";
  for (i = 0; i < nLen ; i++)
  {
    retStr = "" + Str.charAt(Str.length - 1 - i) + retStr;
  }
  return retStr;
}

String.prototype.length_byte = function() 
{
  var cArr = this.match(/[^\x00-\xff]/ig);
  return this.length + (cArr == null ? 0 : cArr.length);
}

function StrLeftCount_Byte(SurStr,Count)
{
  retStr = "",tmpStr = "";
  k = 0,tmpK = 0;
  var len = SurStr.length;
  for (i = 0; i < len; i++)
  {
    tmpStr = SurStr.charAt(i);
	  tmpK = tmpStr.length_byte();
	  if (k + tmpK > Count) return retStr;
	  k = k + tmpK;
	  retStr = retStr + tmpStr;
  }
  return retStr;
}
//**********************************
//功能：取字符串的字符值个数
//作者：周钉剑
//日期：2008.03.20
//**********************************
function GetStrNum(SurStr,inteStr)
{
  I = 0,str_len = 0,num = 0,nPos = 0;
  SurStr = Trim(SurStr);
  str_len = SurStr.length;
  num = 0;
  nPos = SurStr.indexOf(inteStr);
  while (nPos > 0)
  {
   num = num + 1;
   SurStr = SurStr.substring(nPos + inteStr.length,SurStr.length);
   nPos = SurStr.indexOf(inteStr);;
  }
  if (SurStr.length > 0) num = num + 1;
  return num;    
}
//**********************************
//功能：取字符串某个位置的的字符值
//作者：周钉剑
//日期：2008.03.20
//**********************************
function GetStrValueByNo(SurStr,no,inteStr)
{
  var num = 0,nPos = 0;
  var retStr = "",tmpStr = "";
  SurStr = Trim(SurStr);
  num = 0;
  if (SurStr.length == 0) return "";
  nPos = SurStr.indexOf(inteStr);
  while (nPos > 0)
  {
   if (num == no)
   {
    retStr = SurStr.substring(0,nPos);
    if (retStr == inteStr) return "";
    else return retStr;
   }
   num = num + 1;
   SurStr = SurStr.substring(nPos + 1,SurStr.length);
   nPos = SurStr.indexOf(inteStr);
  }
  if (SurStr.length > 0 && num == no)  return SurStr;
  else  return "";
}
//*******************************************************
//功能：日期类处理
//作者：周钉剑
//时间：2001.07.15
//*******************************************************

function IsTime(FormName,FieldName,Message)
{
  var forms = document.forms(FormName);
  if (forms == null) return true;
  var I,NumStr;
  if (forms(FieldName) == null) return true;
  SurStr = forms(FieldName).value;
  return ValIsTime(SurStr,Message);
}

function ValIsTime(SurStr,Message)
{
  var I,NumStr;
  var hourStr = "",mineStr = "";
  hourStr = TrimStrLeft(SurStr.substring(0,2),"0");
  if (ValIsNumber(hourStr,"请输入合法的小时数字") == false)
    return false;
  var hour = parseInt(hourStr);
  if (hour >= 24)
  {
    alert("请输入合法的小时,小时范围为00－23...");
	return false;
  }
  var InteChr = SurStr.substring(2,3);
  if (InteChr != ":")
  {
    alert("请输入合法的时间格式，01:30表示1点30分...");
	return false;
  }
  mineStr = SurStr.substring(3,5);
  mineStr = TrimStrLeft(mineStr,"0");
  if (ValIsNumber(mineStr,"请输入合法的分钟数字") == false)
    return false;
  var mine = parseInt(mineStr);
  if (mine >= 60)
  {
    alert("请输入合法的分钟,分钟范围为00－59...");
	return false;
  }
  return true;
}
function compareDate(Date1,Date2)
{
  return compareNumber(Date1,Date2);
}
/*****************************************************************
 函数名                     IsDate
 输入参数                   SurStr = 日期字符串
 返回值                     boolean = true,false
 作者                       周钉剑
 时间                       2002.10.22
 版本                       ver 1.0
 功能描述                   判断字符串是否为日期
*****************************************************************/
function IsDate(FormName,FieldName,Message)
{
 var forms = document.forms(FormName);
 var I,NumStr;
 if (forms(FieldName) == null) return true;
 SurStr = forms(FieldName).value;
 if (ValIsDate(SurStr,Message) == false)
 {
   forms(FieldName).select();
   return false;
 }
 return true;
}

function GetPreDay(CurrDate)
{
}

function ValIsDate(SurStr,Message)
{
 if (SurStr == "")
  return true;
 var RegExpObj = /[1-3][0-9][0-9][0-9][0-1][0-9][0-3][0-9]/;
 if (SurStr.length > 8)
 {
   alert("日期只有8位，请重新输入...");
   return false;
 }
 if (RegExpObj.exec(SurStr) == null)
 {
  alert("日期不是正常的格式，规定格式为 20010101\r年份范围为1970-3000，月份范围为01-12，天数范围为01-31");
  return false;
 }
 var dYear = parseInt(TrimStrLeft(SurStr.substring(0,4),"0"));
 var dMonth = parseInt(TrimStrLeft(SurStr.substring(4,6),"0"));
 var dDay = parseInt(TrimStrLeft(SurStr.substring(6,SurStr.length),"0")); 
 if (dYear < 1970 || dYear > 3000)
 {
  alert("年份错误,年份范围为1970-3000...");
  return false;
 }
 if (dMonth > 12 || dMonth < 1)
 {
  alert("月份错误,月份范围为01-12...");
  return false;
 }
 if (dDay > 31)
 {
  alert("天数错误,天数范围为01-31...");
  return false;
 }
 if (dMonth == 4 || dMonth == 6 || dMonth == 9 || dMonth == 11)
  if (dDay > 30)
   {
    alert("天数错误,小月天数最大为30，大月最大天数31...");
    return false;
   }
 if (dMonth == 2)
 {
  alertStr = "闰月二月份的天数最大为29,平年二月份的天数最大为28...";
  if (dDay > 29)
  {
   alert(alertStr);
   return false;
  }
  if (dDay == 29)
  {
   if ((dYear % 100 != 0 && dYear % 4 == 0) || (dYear % 400 == 0)) return true;
   else
   {
    alert(alertStr);
    return false;
   }
  }
  return true;
 }
}

function IsMonth(FormName,FieldName,Message)
{
 var forms = document.forms(FormName);
 var I,NumStr;
 if (forms == null) return true;
 fields = forms(FieldName);
 if (fields == null) return true;
 if (ValIsMonth(fields.value,Message) == false)
 {
   forms(FieldName).select();
   return false;
 }
 return true; 
}
function ValIsMonth(SurStr,Message)
{
 var I,NumStr;
 if (SurStr == "")
  return true;
 var RegExpObj = /[1-3][0-9][0-9][0-9][0-1][0-9]/;
 if (SurStr.length > 6)
 {
   alert("月份只有6位，请重新输入...");
   return false;
 }
 if (RegExpObj.exec(SurStr) == null)
 {
  alert("月份不是正常的格式，规定格式为 200101\r年份范围为1970-3000，月份范围为01-12");
  return false;
 }
 var Year = parseInt(TrimStrLeft(SurStr.substring(0,4),"0"));
 var Month = parseInt(TrimStrLeft(SurStr.substring(4,6),"0"));
 if (Year < 1970 || Year > 3000)
 {
  alert("年份错误,年份范围为1970-3000...");
  return false;
 }
 if (Month > 12 || Month < 1)
 {
  alert("月份错误,月份范围为01-12...");
  return false;
 }
 return true;
}

/*****************************************************************
 函数名                     onKeyDown
 输入参数                   无
 返回值                     无
 作者                       周钉剑
 时间                       2002.10.22
 版本                       ver 1.0
 功能描述                   按回车键自动跳到下一个控件
 举例                      onKeyDown="KeyNext()"
*****************************************************************/
function onKeyDown()
{
  if(event.keyCode==13 && event.srcElement.type!='button' 
    && event.srcElement.type!='submit' 
	&& event.srcElement.type!='reset' 
	&& event.srcElement.type!='textarea' 
	&& event.srcElement.type!='')
   event.keyCode=9;
}
document.onkeydown = onKeyDown;
/******************************************************
//功能：      产生NumLen位随机数
//参数：      NumLen = 位数
//返回值：    随机数
//作者：      周钉剑
//时间：      2004-06-27
//修改者：    周钉剑
//修改：      2004-06-27
******************************************************/
function GetRandNum(NumLen)
{
  var ResultStr = "";
  var NumDot = 1;
  var NumLen = 5;
  var i = 0,RandomStr = "";
  var Num = "",pos = 0;
  for (i = 0;i < NumLen;i++)  NumDot = NumDot * 10;
  while(pos < NumLen)
  {
   RandomStr = Math.random() * NumDot;
   RandomStr = RandomStr.toString();
   pos = RandomStr.indexOf(".");
  }
  RandomStr = RandomStr.substring(0,pos);
  return RandomStr;
}

function GetConvNum_Ch(SurStr)
{
  var NumStr = "０１２３４５６７８９";
  var ResultStr = "";
  var i;
  for (i = 0;i < SurStr.length;i++)
  {
   Num = parseInt(SurStr.substring(i,i + 1));
   ResultStr = ResultStr + NumStr.substring(Num,Num + 1);
  }
  return ResultStr;
}
//****************************************************
//功能：      在表格中移动悬浮(导航条的变化)。
//参数：      CellName = 表格名称
//返回值：    新字符串
//作者：      周钉剑
//时间：      2002-06-5
//修改者：    周钉剑
//修改：      2002-06-5
//****************************************************
function CellOver(CellName,ImageName)
{
 var Cell = document.all(CellName);
 if (Cell != null)
 {
  Cell.background = ImageName;
  Cell.style.cursor = 'hand';
 }
}
//****************************************************
//功能：      在表格中移动离开(导航条的变化)。
//参数：      CellName = 表格名称
//返回值：    新字符串
//作者：      周钉剑
//时间：      2002-06-5
//修改者：    周钉剑
//修改：      2002-06-5
//****************************************************
function CellOut(CellName,ImageName,DefaultImageName)
{
 var Cell = document.all(CellName);
 if (Cell != null)
 {
  if (ImageName == "") Cell.background = DefaultImageName;
  else Cell.background = ImageName;
  Cell.style.cursor = 'hand';
 }
}
//****************************************************
//功能：      四则混合运算
//参数：      InputStr=运算表达式
//返回值：    运算值
//作者：      周钉剑
//时间：      2005-03-16
//修改者：    周钉剑
//修改：      2005-03-16
//****************************************************
 function exc(InputStr)
 {
  var stock = new Array(20);
  var index = 0,curindex = 0,tmp_index = 0;
  var ExStr = InputStr + "#";
  var num1,num2,num3;
  var tmpEx;
  var len = ExStr.length;
  stock[index++] = "#";
  for (i = 0; i < len; i++)
  {
   tmpEx = ExStr.substring(i,i + 1);
   if (tmpEx == "(") stock[index++] = tmpEx;
   if (tmpEx == "+"  || tmpEx == "-") stock[index++] = tmpEx;
   if (tmpEx <= "9" && tmpEx >= "0")
   {
    tmp_index = i + 1;
	while(ExStr.substring(tmp_index,tmp_index + 1) >= "0" && ExStr.substring(tmp_index,tmp_index + 1) <= "9") tmp_index++;
	stock[index++] = parseInt(ExStr.substring(i,tmp_index + 1));
	i = --tmp_index;
   }
   if (tmpEx == "*"  || tmpEx == "/")
   {
	if (ExStr.substring(i + 1,i + 2) == "(") stock[index++] = tmpEx;
	else
	{
	 tmp_index = i + 1;
	 while(ExStr.substring(tmp_index,tmp_index + 1) >= "0" && ExStr.substring(tmp_index,tmp_index + 1) <= "9") tmp_index++;
 	 num1 = parseFloat(ExStr.substring(i + 1,tmp_index + 1));
	 if (tmpEx == "*") num3 = parseFloat(num1) * parseFloat(stock[--index]); 
     if (tmpEx == "/") num3 =  parseFloat(stock[--index]) / parseFloat(num1);
	 stock[index++] = num3;
	 i = --tmp_index;   
    }
   }
  if (tmpEx == ")") 
  {
   curindex = index;
   while (stock[index] != "("){ index--;}
   num3 = 0;
   for (j = index + 1; j < curindex; j++)
   {
    if (j == index + 1) num3 = parseFloat(num3) + parseFloat(stock[j]);
    if (stock[j] == "+")
	{
	 num3 = num3 + parseFloat(stock[j+1]);
	 j++;
	}
	if (stock[j] == "-")
	{
	 num3 = num3 - parseFloat(stock[j+1]);
	 j++;
	}
   }
   stock[index++] = num3;
  }
  if (tmpEx == "#") 
  {
   curindex = index;
   while (stock[index] != "#"){ index--;}
   num3 = 0;
   for (j = index + 1; j < curindex; j++)
   {
    if (j == index + 1) num3 = parseFloat(num3) + parseFloat(stock[j]);
    if (stock[j] == "+")
	{
	 num3 = num3 + parseFloat(stock[j+1]);
	 j++;
	}
	if (stock[j] == "-")
	{
	 num3 = num3 - parseFloat(stock[j+1]);
	 j++;
	}
   }
   stock[index++] = num3;
  }
 }
 return stock[0];
}
