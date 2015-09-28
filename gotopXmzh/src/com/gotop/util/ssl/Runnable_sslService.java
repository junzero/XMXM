package com.gotop.util.ssl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.log4j.Logger;

public class Runnable_sslService implements Runnable{
	protected static Logger log = Logger.getLogger(Runnable_sslService.class);
	private Socket ssls;
	private ThreadPoolExecutor executor;
	private String merId;
	/**
	 * 
	 * @param ssls
	 * @param executor
	 * @param merId 定单编号
	 */
	public Runnable_sslService(Socket ssls,ThreadPoolExecutor executor,String merId){
		this.ssls = ssls;
		this.executor = executor;
		this.merId = merId;
	}
	public void run() {  
		try {
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(ssls.getInputStream()));
			PrintStream socketOut = new PrintStream(ssls.getOutputStream());
			
			String s = socketIn.readLine();
			
//			Thread.sleep(1000);//模拟处理时间，

			s = "-service-"+System.currentTimeMillis()+"----客户发的信息-"+s;
			
//			StringBuffer sbr = new StringBuffer();
//			for(int i=0;i<10000;i++){
//				sbr.append(s);
//			}
//			s = sbr.toString();
//			log.info("---------service_len--"+sbr.toString().getBytes().length);
			
			
			socketOut.println(s);
			socketIn.close();
			socketOut.close();
			ssls.close();
			
			log.info("-Client-To-Service--"+s +"-----"+executor.getActiveCount()+"----merId-"+merId+"-------"+new Date().toLocaleString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
