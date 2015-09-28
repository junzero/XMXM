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
				log.info("-------����Ϊ��--");
				return;
			}
			SSLSocket csocket = (SSLSocket) ssf.createSocket(TSslQueue.host, TSslQueue.port);
			// ���´���ͬsocketͨѶʵ���еĴ���
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(csocket.getInputStream()));// ���ܵ�����Ϣ
			PrintStream socketOut = new PrintStream(csocket.getOutputStream());// Ҫ���͵���Ϣ
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
			log.info("---active-"+executor.getActiveCount()+"---count-"+executor.getCompletedTaskCount()+"--���յ�������---"+e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("--������-cMess-"+cMess,e);
		}
	}
	
	public void run(){
		runSocket();
	}
}
