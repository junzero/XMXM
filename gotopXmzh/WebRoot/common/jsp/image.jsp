<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.io.FileInputStream,java.io.File"%>
<%
String fileId=request.getParameter("id");
if(!fileId.startsWith("image")) return;
byte[] output=(byte[])session.getAttribute(fileId);
session.removeAttribute(fileId);
response.setContentType("image");
BufferedOutputStream bos = null;
try {
bos = new BufferedOutputStream(response.getOutputStream());
bos.write(output,0,output.length);
} catch(final IOException e) {
System.out.println ( "����IOException."+ e );
} finally {
if (bos != null)
bos.close();
}
out.clear();
out = pageContext.pushBody();
%>

