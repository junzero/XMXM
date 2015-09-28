package com.gotop.util.wss4j;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.handler.AbstractHandler;
import org.jdom.Element;

public class AuthenticationHandler extends AbstractHandler {

	public void invoke(MessageContext cfx) throws Exception { 
		  // TODO Auto-generated method stub 
		  if(cfx.getInMessage().getHeader() == null) 
		           if(cfx.getInMessage().getHeader()==null){ 
		                throw new org.codehaus.xfire.fault.XFireFault("请求必须包含验证信息",org.codehaus.xfire.fault.XFireFault.SENDER); 
		            } 
		            Element token=cfx.getInMessage().getHeader().getChild("AuthenticationToken"); 
		            if (token == null){ 
		             throw new org.codehaus.xfire.fault.XFireFault("请求必须包含身份验证信息", org.codehaus.xfire.fault.XFireFault.SENDER); 
		            } 

		               String username = token.getChild("Username").getValue(); 
		               String passw = token.getChild("Password").getValue(); 
		               try{ 
		                   //进行身份验证 ，只有abcd@1234的用户为授权用户 
		                  if("test".equals(username) && "test".equals(passw)) 
		                   //这语句不显示 
		                   System.out.println("身份验证通过"); 
		                  else throw new Exception(); 
		               } 
		               catch (Exception e){ 
		                   throw new   org.codehaus.xfire.fault.XFireFault("非法的用户名和密码",   org.codehaus.xfire.fault.XFireFault.SENDER); 
		               } 


		 } 

		} 
