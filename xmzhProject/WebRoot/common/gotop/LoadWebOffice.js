var FramerControl1=null;
var FCObj=null;
function palpitantRecalling(){
	var myAjax = new Ajax("org.gocom.abframe.tools.FileUploadManager.palpitantRecalling.biz");
	myAjax.submit();
	window.setTimeout("palpitantRecalling()",600000);
}
function loadWebOffice(){
	FramerControl1 = document.getElementById("FramerControl1");
	if(FramerControl1){
		FramerControl1.Close();
		FramerControl1.parentNode.removeChild(FramerControl1);
	}
	var s = "";
	var tempDiv = "teimDiv"+new Date().getTime();
	
	
	document.write("<div id='"+tempDiv+"' />");
	var tempObj = document.getElementById(tempDiv);
	var tempObjPN = tempObj.parentNode;
	var width = tempObjPN.webOfficeWidth;
	var height = tempObjPN.webOfficeHeight;
	if(width){}else{width='100%'};
	if(height){}else{height='100%'};
	tempObjPN.removeChild(tempObjPN.childNodes[0]);
	s += "<object id=FramerControl1 width='"+width+"' height='"+height+"' style='LEFT: 0px; TOP: 0px'  classid='clsid:E77E049B-23FC-4DB8-B756-60529A35FAD5' codebase="+getAppPath()+"/common/gotop/WebOffice.cab#V6,0,0,0>"
	s +="<param name='_ExtentX' value='6350'><param name='_ExtentY' value='6350'>"
	s +="</object>";
	document.write(s);
	FramerControl1 = document.getElementById("FramerControl1");
	FramerControl1.Open=openWF;
//	FramerControl1.ActiveDocument = FramerControl1.GetDocumentObject();
	FCObj = new Object(FramerControl1.ActiveDocument);
	
	FramerControl1.HideMenuItem(0x01); //Hide New
	FramerControl1.HideMenuItem(0x02); //Hide Show
	FramerControl1.HideMenuItem(0x04); //Hide Save
}
function openWF(Document,ReadOnly,ProgId,WebUsername,WebPassword){
	if(ProgId=='Excel.Sheet'){
		ProgId='xls';
	}
	this.OptionFlag |= 128;
	this.LoadOriginalFile(Document,ProgId);
	this.ActiveDocument = this.GetDocumentObject();
	FCObj = new Object(this.ActiveDocument);
//	rechCell(FCObj);
}
function getAppPath(){
	var path = window.top.location;
	path = path + "";
	var i = 0;
	var index = path.indexOf("/");
	while(index != -1)
	{
		i++;
		var retStr = path.substring(0,index);
		if(i == 4)
		{
			return retStr;
		}
		index = path.indexOf("/",index+1);
	}
}
/**
*	解决文件上传后,再经过POI复制后,文件损坏问题
**/
function rechCell(FCObj){
    try{
		var count = FCObj.Application.Worksheets.count;
		for(var i=1;i<=count;i++){
			try{
				var sheet = FCObj.Application.Worksheets(i);
				sheet.cells(1, 1).value = sheet.cells(1, 1).value;
			}catch(e){}
		}
/*
		var fawc = FCObj.Application.Worksheets.count;
		var ws = FCObj.Application.Worksheets(fawc)		
		ure = ws.UsedRange;
		var cellCont = ure.Cells.Count;
		var rowsCont = ure.Rows.Count;
		if(cellCont>13){
			cellCont=13;
		}
		if(rowsCont>26){
			rowsCont=26;
		}
		for(var i=1;i<=cellCont;i++){
			for(var j=1;j<=rowsCont;j++){
			    if(ws.Cells(i,j)){
			    	var cell = ws.Cells(i,j);
					var value = cell.value;
					var ha = cell.HorizontalAlignment
					if(value && ha!=1 && cell.MergeCells){   //-4108
						cell.value=value+1;
					}
			    }
			}
		}
*/
		FCObj.Application.ActiveWorkbook.save();
	}catch(e){}
}
loadWebOffice();
palpitantRecalling();