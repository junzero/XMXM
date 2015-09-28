<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
 <head id="Head1" runat="server">
     <link href="css/print.css" rel="stylesheet" type="text/css" />
     <title class="hidenElement"></title>
     <script language="javascript" type="text/javascript">     
        function doPrint(){   
           window.print();
         }
    </script>
	<script language="JavaScript">
		var hkey_root,hkey_path,hkey_key
		hkey_root="HKEY_CURRENT_USER"
		hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\"
		//设置网页打印的页眉页脚为空
		function pagesetup_null()
		{
		  try{
		    var RegWsh = new ActiveXObject("WScript.Shell")
		    hkey_key="header"    
		    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
		    hkey_key="footer"
		    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
		  }catch(e){}
		}
		//设置网页打印的页眉页脚为默认值
		function pagesetup_default()
		{
		  try{
		    var RegWsh = new ActiveXObject("WScript.Shell")
		    hkey_key="header"    
		    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"&w&b页码，&p/&P")
		    hkey_key="footer"
		    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"&u&b&d")
		  }catch(e){}
		}
		</script>
    <style type="text/css">
		@media print
		 {
		  .hidenElement{
		   display:none;
		  }
		 }
    </style>
</head>
 <script language="javascript" type="text/javascript">
	function aaa() {
	   document.getElementById('s1').style.display='none';
	   document.getElementById('s2').style.display='none';
	} 
	function doPrint(){
      window.print();
    }
 </script>
 <body >
     <form id="_form" runat="server">
     <div style="display:none;">
        
    </div>
     <div class="hidenElement">
         <table width="100%">
             <tr>
                 <td align="left">
						<input type="button" value="清空页码" onclick="pagesetup_null()">
						<input type="button" value="恢复页码" onclick="pagesetup_default()">
						<input id="btnPrint" type="button" value="点击此按钮开始打印" onclick="aaa();doPrint(); " />
                 </td> 
            </tr> 
        </table> 
     </div>
     <hr class="hidenElement" />   
    <h1 id="s1" class="hidenElement">显示打印</h1>
     <h2>显示打印显示打印显示打印显示打印</h2>
     <div id="s2" class="hidenElement">显示打印显示打印显示打印显示打印显示打印</div>
 
</form>
 </body>
 </html> 