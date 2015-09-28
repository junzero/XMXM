<%@page pageEncoding="UTF-8"%>
<%@page import="javax.servlet.ServletOutputStream"%>
<%@page import="java.io.*"%>
<%@ page import="com.gotop.util.security.ForUtil"%>
<%@page import="com.eos.web.taglib.util.*" %><%
	  //获取标签中使用的国际化资源信息
      String localFile = request.getParameter("downloadFile");
      String fileName = request.getParameter("fileName");
      if(fileName==null || fileName.trim().equals("")){
    	  fileName = localFile;
      }
      System.out.println(">>>>download file is "+localFile);
	  byte[] buffer = new byte[512]; 
	  int size = 0; 
	  response.reset();
	  ForUtil.downloadXls(response,fileName);
	  ServletOutputStream os = null;
	  FileInputStream in = null;
	  try {
	     os = response.getOutputStream();
	     File downloadFile=ForUtil.createFile(localFile);
	     if(downloadFile!=null&&downloadFile.exists()){
		     in = new FileInputStream(ForUtil.createFile(localFile));
		     while ((size = in.read(buffer)) != -1) { 
		       os.write(buffer, 0, size);
		       os.flush(); 
		     }
		    out.clear();
         	out = pageContext.pushBody();
	     }
  	   } catch(Exception e) {
          e.printStackTrace();
       } finally {
            try {
             if(in!=null)in.close();
		     if(os!=null)os.close();
		     File file=ForUtil.createFile(localFile);
		     if (file!=null&&file.isFile()&&file.exists()) {
		       file.delete();
		     }

		   } catch (IOException e) {
		     e.printStackTrace();
		   }
       }
%>