package com.gotop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;






/**
 * <p>Title: 配置参数加载类</p>
 *
 * <p>Description: 该类用于加载配置文件，系统中可通过该类，根据配置参数的Key值来取得Value</p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author phc
 *
 * @date 2011-3-8
 *
 * @version 1.0
 **/
public class ParseProperties {
	private static HttpServletRequest httpServletRequest = null;
	private static ParseProperties config = null;
	private Properties properties;//持久的属性集
	/**
	 * 构造函数
	 */
	private ParseProperties() {
		init();
	}
	private ParseProperties(HttpServletRequest request){
		init(request);
	}

	/**
	 * 初始化方法
	 */
	private void init() {
		ClassLoader loader = this.getClass().getClassLoader();//类加载器
		Properties properties = new Properties();
		java.io.InputStream lgr = null;
		try {
			lgr = loader.getResourceAsStream(Constants.CONFIGFILE);
			properties.load(lgr);//将Constants的文件用类加载器转为输入流，并用properties的load方法读取为键值对
			setProperties(properties);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(lgr!=null){
				try {
					lgr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void init(HttpServletRequest request) {
		ServletContext context = httpServletRequest.getSession().getServletContext();
		Properties properties = new Properties();
		InputStream ips = null;
		try {
			ips = context.getResourceAsStream(Constants.WEBCONFIGFILE);
			properties.load(ips);//将Constants的文件用类加载器转为输入流，并用properties的load方法读取为键值对
			setProperties(properties);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(ips!=null){
					ips.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * 获取ParseProperties的实例
	 * @return ParseProperties的实例
	 */
	public static ParseProperties getInstance(){
		if(config == null){
			config = new ParseProperties();
			return config;
		}else{
			return config;
		}
	}
	public static ParseProperties getInstance(HttpServletRequest request){
		httpServletRequest = request;
		if(config == null){
			config = new ParseProperties();
			return config;
		}else{
			return config;
		}
	}

	public String getProperty(String key) {
		if (key != null) {
			return properties.getProperty(key);
		} else {
			return "";
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
