function login(){
	var doc = document;
	var userName = doc.getElementById("loginname").value;
	if(userName==""){
		alert("用户姓名不能为空！");
		doc.getElementById("loginname").focus();
		return;
	}
	var password = doc.getElementById("pwd").value;
	if(password==""){
		alert("密码不能为空！");
		doc.getElementById("pwd").focus();
		return;
	}	
	var myAjax = new Ajax("/login/queryLoginUserOrg_login.action");
	myAjax.addParam("username",userName);
	myAjax.submit();
	var resultValue = myAjax.getText("/root/data");
	var xmlDoc = createXmlDom();
	xmlDoc.loadXML(resultValue);
    xmlDoc.async=false;
    xmlDoc.documentElement;
    var mapArray = xmlDoc.getElementsByTagName("map");
    var size = mapArray.length;
    if(size== 0){
  	  alert("该用户不存在");
    }else{	
    	  if(size == 1){//说明没有重名的
             var ORGNAME = mapArray[0].childNodes[2].firstChild.nodeValue;
             var USERID = mapArray[0].childNodes[3].firstChild.nodeValue;
             var EMPNAME = mapArray[0].childNodes[0].firstChild.nodeValue;
             var ORGID = mapArray[0].childNodes[1].firstChild.nodeValue;
             document.getElementById("userid").value = USERID;
             document.getElementById("orgid").value = ORGID;
             submitPortal();
          }else{
	     	  delSpan("C_SPAN");
		      var SPAN = document.createElement("span");
		      SPAN.setAttribute('id','C_SPAN');
		      var DIV = "";
		      var show_info = "";
		      var oRect = document.getElementById("loginname").getBoundingClientRect();   
		      var left = oRect.left-2;
		      var top = oRect.top+18;
	          DIV = "<DIV id=\"S_UnitName\" style=\"background: #ffffff;border:1px solid #000000;Z-INDEX: 10; POSITION: absolute;border-color:#000000; width:170px;height:92px;overflow:auto;left:"+left+";top:"+top+"\">";
	          show_info = DIV+"<table id='orgtb' width='90%' bgcolor='#ffffff' border=0 cellspacing='1' cellPadding='0 width='100%'>";
	          for(var j=0;j<size;j++){
	          	  var EMPNAME=mapArray[j].childNodes[0].firstChild.nodeValue;
	          	  var ORGID=mapArray[j].childNodes[1].firstChild.nodeValue;
	              var ORGNAME=mapArray[j].childNodes[2].firstChild.nodeValue;
	              var USERID=mapArray[j].childNodes[3].firstChild.nodeValue;
	              show_info = show_info+"<tr id='orgtr"+j+"' style='font-size:10pt;cursor:hand;' onclick=\"selectLoginType('"+USERID+"','"+ORGID+"')\" onmouseover=\"this.bgColor='#EECCEE'\" onmouseout=\"this.bgColor='#ffffff'\">";
	              show_info = show_info+"<td>"+ORGNAME+"</td>";
	              show_info = show_info+"</tr>";
	          }
	          show_info = show_info+"</table></DIV>";
	          SPAN.innerHTML = show_info;
	          document.body.appendChild(SPAN); 
	          document.getElementById("orgtb").focus();
	          return false;
          }
  }
}
//创建解析xml对象
function createXmlDoc(){    
     var xmlDoc = false;
     try //Internet Explorer
     {
          xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
     }catch(e){
        try //Firefox, Mozilla, Opera, etc.
        {
            xmlDoc=document.implementation.createDocument("","",null);
        }catch(e){}
     }
     return xmlDoc;
}
//移除浮层操作
function delSpan(spanid){
	var span = document.getElementById(spanid);
	if(span!=null){
	   span.parentNode.removeChild(span);
	}
}
function selectLoginType(USERID,orgid){  
   document.getElementById("userid").value = USERID;
   document.getElementById("orgid").value = orgid;
   delSpan("C_SPAN");
   submitPortal();
}
//任意点击时关闭该控件 
function spanClick(){
   var bEvent = eventManager.getEvent();
   var aEle = bEvent.srcElement || bEvent.target;
   if(aEle.name!="check")
   {
       var span = document.getElementById("C_SPAN");
       if(span!=null)
       {
          delSpan("C_SPAN");
       }
   }
} 
eventManager.add(document, "click", spanClick);


function nextTag(){
   if(event.keyCode==13){
      event.keyCode=9;
   }
}
function submitPortal(){
    if(document.getElementById("userid").value=="")
    {
       return false;
    }
    var form = document.forms[0];
        form.submit();
}