package com.gotop.util.net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

/**
 * <p>Title: Socket客户端</p>
 *
 * <p>Description: 通过该客户端来实现和其他主机的Socket连接</p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author phc
 *
 * @date 2011-3-9
 *
 * @version 1.0
 **/
public class SocketClient {
	private static Logger logger = Logger.getLogger(SocketClient.class);
	/** Socket连接的IP地址 */
	private String ip;
	/** Socket连接的端口号 */
	private int port;
	/** 连接超时限制默认为60秒 */
	private int timeout = 1800000;
	
	/**
	 * 默认构造方法
	 */
	public SocketClient(){
	}

	/**
	 * 构造方法
	 * @param ip IP地址
	 * @param port 端口号 
	 */
	public SocketClient(String ip, int port){
		this.ip = ip;
		this.port = port;
	}

	/**
	 * Socket通讯报文发送函数
	 * @param request 请求报文
	 * @return 响应报文
	 * @throws Exception
	 */
	public String sendRequest(String request) throws Exception {
		StringBuffer response = new StringBuffer("");
		Socket server = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			Calendar rightNow4 = Calendar.getInstance();
		    SimpleDateFormat fmt4 = new SimpleDateFormat("yyyyMMddhhmmss");
		    String sysDatetime4 = fmt4.format(rightNow4.getTime()); 
		    System.out.println("in进入函数的时间："+sysDatetime4);
		    
			server = new Socket(ip, port);
			/* 连接超时设置 */
			server.setSoTimeout(getTimeout());
			
			logger.info("成功建立与主机[" + ip + "]在端口[" + port + "]的Socket连接.");
			Calendar rightNow5 = Calendar.getInstance();
		    SimpleDateFormat fmt5 = new SimpleDateFormat("yyyyMMddhhmmss");
		    String sysDatetime5 = fmt5.format(rightNow5.getTime()); 
		    System.out.println("out建立Socket的时间："+sysDatetime5);
			
			/* BufferedReader对象,用来读取从后台返回的响应信息 */
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			/* PrintWriter对象,用来发送从本地服务器向后台发送的请求信息 */
			out = new PrintWriter(server.getOutputStream());
			
			out.println(request);
			out.flush();
			logger.info("发送的请求报文：" + request);
			
			Calendar rightNow2 = Calendar.getInstance();
		    SimpleDateFormat fmt2 = new SimpleDateFormat("yyyyMMddhhmmss");
		    String sysDatetime2 = fmt2.format(rightNow2.getTime()); 
		    System.out.println("发送的请求报文时间："+sysDatetime2);
			
			String tmp = null;
			while ((tmp = in.readLine()) != null) {
				response.append(tmp);
			}
			logger.info("返回的响应报文：" + response);
		} catch (UnknownHostException e) {
			throw new Exception("找不到服务器[" + ip + "] ", e);
		} catch (IOException e) {
			throw new Exception("不能获得Socket的读入与写出器 ", e);
		} finally {
			try {
				if(in != null) 
					in.close();
				if(out != null) 
					out.close();
				if(server != null) 
					server.close();
				
				logger.info("成功断开与主机[" + ip + "]在端口[" + port + "]的Socket连接.");
			}catch (IOException e) {
				throw new Exception("断开与主机[" + ip + "]在端口[" + port + "]的Socket连接时出错 ", e);
			}
		}

		return response.toString();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}