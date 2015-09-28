package com.gotop.util.ssl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.concurrent.ThreadPoolExecutor;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class Runnable_sslClient implements Runnable{
	protected static Logger log = Logger.getLogger(Runnable_sslClient.class);
	private SocketFactory ssf;
	private ThreadPoolExecutor executor;
	private String cMess;
	public Runnable_sslClient(SocketFactory ssf,ThreadPoolExecutor executor,String mess){
		this.ssf = ssf;
		this.executor = executor;
		this.cMess = mess;
	}
	public void runSocket(){
		try {
			if(StringUtils.isBlank(cMess)){
				log.info("-------内容为空--");
				return;
			}
			SSLSocket csocket = (SSLSocket) ssf.createSocket(TSslQueue.host, TSslQueue.port);
			// 以下代码同socket通讯实例中的代码
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(csocket.getInputStream()));// 接受到的信息
			PrintStream socketOut = new PrintStream(csocket.getOutputStream());// 要发送的信息
			socketOut.println(cMess);
			
			StringBuffer sbr = new StringBuffer();
			String valueString = null;
			while ((valueString=socketIn.readLine())!=null){
				sbr.append(valueString);
			}
			String e = sbr.toString();
			
			socketOut.println("[end]");
			
			socketIn.close();
			socketOut.close();
			csocket.close();
			TSslQueue.offer_success(e);
			log.info("---active-"+executor.getActiveCount()+"---count-"+executor.getCompletedTaskCount()+"--接收到的内容---"+e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("--错误编号-cMess-"+cMess,e);
		}
	}
	
	public void run(){
		runSocket();
	}
}
