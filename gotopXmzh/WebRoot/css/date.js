
/*******************************************************/
//功能：本文档负责收集所有日期相关处理函数
/*******************************************************/
function getDate()
{
   var date = new Date();
   var y = date.getFullYear();
   var m = date.getMonth() + 1;
   var d = date.getDate();
   return "" + y + (m<10?"0":"")+ m +(d<10?"0":"")+ d;
}

function getPreDate(CurrDate)
{
  //CurrDate格式如下：yyyymmdd
  nYear = CurrDate.substring(0,4);
  nMonth = CurrDate.substring(4,6);
  nDay = CurrDate.substring(6,8);
  tmpDate = nYear + "/" + nMonth + "/" + nDay;
  preDate = new Date(tmpDate);
  preDate.setDate(preDate.getDate() - 1);
  sYear = preDate.getYear();
  sMonth = preDate.getMonth() + 1;
  sDay = preDate.getDate();
  sYear = "20" + sYear;
  sMonth = "00" + sMonth;
  sDay = "00" + sDay;
  sYear = sYear.substring(sYear.length - 4,sYear.length);
  sMonth = sMonth.substring(sMonth.length - 2,sMonth.length);
  sDay = sDay.substring(sDay.length - 2,sDay.length);
  return sYear + sMonth + sDay; 
}

function getPreMonth(CurrMonth)
{
  //CurrDate格式如下：yyyymmdd
  nYear = CurrMonth.substring(0,4);
  nMonth = CurrMonth.substring(4,6);
  tmpDate = nYear + "/" + nMonth + "/01";
  preDate = new Date(tmpDate);
  preDate.setDate(preDate.getDate() - 1);
  sYear = preDate.getYear();
  sMonth = preDate.getMonth() + 1;
  sYear = "20" + sYear;
  sMonth = "00" + sMonth;
  sYear = sYear.substring(sYear.length - 4,sYear.length);
  sMonth = sMonth.substring(sMonth.length - 2,sMonth.length);
  return sYear + sMonth; 
}

function getMonthLastDay(CurrMonth)
{
   //CurMonth = 20050607
   var nYear,nMonth;
   var sYear,sMonth,sDay;
   nYear = parseInt(CurrMonth.substring(0,4));
   nMonth = parseInt(CurrMonth.substring(4,6));
   //传入的月份是1－12，实际计算月份是0－11
   if (nMonth == 12) { nYear++; nMonth = "01";}
   else nMonth = nMonth + 1;
   sMonth = "00" + nMonth;
   sMonth = sMonth.substring(sMonth.length - 2,sMonth.length);
   nDate = new Date(nYear + "/" + sMonth + "/01");
   nDate.setDate(nDate.getDate() - 1);
   sYear = nDate.getYear();
   sMonth = nDate.getMonth() + 1;
   sYear = "20" + sYear;
   sMonth = "00" + sMonth;
   sDay = "00" + nDate.getDate();
   sYear = sYear.substring(sYear.length - 4,sYear.length);
   sMonth = sMonth.substring(sMonth.length - 2,sMonth.length);
   sDay = sDay.substring(sDay.length - 2,sDay.length);
   return sYear + sMonth + sDay;
}

function  getDaysBetween(sDate1,  sDate2)
{   
  //sDate1和sDate2是20080101格式  
  var  miStart = Date.parse(sDate1.substring(0,4) + "/" + sDate1.substring(4,6) + "/" + sDate1.substring(6,8));   
  var  miEnd = Date.parse(sDate2.substring(0,4) + "/" + sDate2.substring(4,6) + "/" + sDate2.substring(6,8));   
  return (miEnd-miStart)/(1000   *   24   *   3600);   
}    
function getMonthLDay(CurrMonth)
{
   //CurMonth = 20050607
   var nYear,nMonth,nDate;
   var sYear,sMonth,sDay;
   nYear = parseInt(CurrMonth.substring(0,4));
   nMonth = parseInt(CurrMonth.substring(4,6));
   nMonth++;
   //传入的月份是1－12，实际计算月份是0－11
   if (nMonth == 13) { nYear++; nMonth = 0;}
   nDate = new Date(nYear + "/" + nMonth + "/01");
   nDate.setDate(nDate.getDate() - 1);
   sYear = nDate.getYear();
   sMonth = nDate.getMonth() + 1;
   sYear = "20" + sYear;
   sMonth = "00" + sMonth;
   sDay = "00" + nDate.getDate();
   sYear = sYear.substring(sYear.length - 4,sYear.length);
   sMonth = sMonth.substring(sMonth.length - 2,sMonth.length);
   sDay = sDay.substring(sDay.length - 2,sDay.length);
   return sYear + sMonth + sDay;
}

function getSeasonFMonth(CurrDay)
{
   //CurMonth = 20050607
   var iYear,iMonth;
   var sDay;
   iYear = parseInt(CurrDay.substring(0,4));
   iMonth = parseInt(CurrDay.substring(4,6));
   var iSeason = (int)(Math.floor((iMonth + 2) / 3));//取本月所在季度
   
   if(iSeason==4)
      sDay = iYear + String.valueOf((iSeason-1) * 3 + 1);//取本季度第一月
   else
      sDay = iYear + "0" + String.valueOf((iSeason-1) * 3 + 1);//取本季度第一月
 
   return sDay;
}