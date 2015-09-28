package com.gotop.util.strtsu2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

/**
 * @author sl
 * email:shileijava@sohu.com
 */
public class PropertyReader {
	private static String pathDefault = "/configdebugdefault.properties";
	private static String path = null ==ConfigDebugInterceptor.configFileLocation ? "" : ConfigDebugInterceptor.configFileLocation;//在配置拦截器的时候，用户自定义指定属性文件的位置及名称
	private static Properties pDefault = new Properties();//默认的属性文件对象
	private static Properties p = new Properties();//用户自定义的属性文件对象
	InputStream inLog = null;
	static {
		InputStream pDefaultPath = null;
		InputStream pPath = null;
		try {
//			System.out.println("加载的属性文件:"+ConfigDebugInterceptor.configFileLocation);
			pDefaultPath = PropertyReader.class.getResourceAsStream(pathDefault);
			if(null != pDefaultPath){
				pDefault.load(pDefaultPath);
			}
			if("".equals(path)){
				System.out.println("用户没有指定属性文件的位置,将使用默认的属性配置!!!");
			}else{
				pPath = PropertyReader.class.getResourceAsStream(path);//path不能为null，否则会抛出异常
				if(null == pPath){
					pPath = ServletActionContext.getServletContext().getResourceAsStream(path);//属性文件放到WEB-INF下的情况
				}
			
				
				if(null == pPath){
					System.out.println("找不到用户自定义的属性文件："+path+",将使用默认的属性配置!!!");
				}else{
					p.load(pPath);
				}
			}
			
		} catch (Exception e) {
			System.out.println("找不到属性文件："+ConfigDebugInterceptor.configFileLocation);
			throw new ExceptionInInitializerError("read properties exception");
		} finally {
			if (pDefaultPath != null){
				try {
					pDefaultPath.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (pPath != null){
				try {
					pPath.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
		}
	}
	/**
	 * 如果用户没有自定义属性，就是使用默认的，如果用户自定义了，就是用用户自定义的
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return null == p.getProperty(key) || "".equals(p.getProperty(key).trim()) ? pDefault.getProperty(key) : p.getProperty(key);
	}
	
	public static void main(String[] args) {
		
		PropertyReader.getValue("xx");
		System.out.println("ok");
	}
	
}
