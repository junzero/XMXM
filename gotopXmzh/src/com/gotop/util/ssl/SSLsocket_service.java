package com.gotop.util.ssl;

import java.net.Socket;
import java.security.KeyStore;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.TrustManagerFactory;

import org.apache.log4j.Logger;

import com.gotop.util.security.ForUtil;
/**
 * 双向认证
 * @author gotop
 *
 */
public class SSLsocket_service {
	protected static Logger log = Logger.getLogger(SSLsocket_service.class);
	private static SSLsocket_service ssls;
	
	private ThreadPoolExecutor executor;
	private SSLServerSocket serverSocket;
	private boolean stoped;
	
	private SSLsocket_service(){
		initExecutor();
		log.info("------------服务器初始化---");
	}
	public static SSLsocket_service getInstance(){
		if(ssls==null){
			ssls = new SSLsocket_service();
		}
		return ssls;
	}
	/**
	 * 初始化证书等信息
	 */
	public void initExecutor(){
		try {
			//线程数
			BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();   
			
			ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 1000, 1, TimeUnit.DAYS, queue);
			
			SSLContext ctx = SSLContext.getInstance("SSL");
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			KeyStore ks = KeyStore.getInstance("JKS");
			KeyStore tks = KeyStore.getInstance("JKS");
			ks.load(ForUtil.createFileInputStream(TSslQueue.serv_jks), TSslQueue.passw.toCharArray());
			tks.load(ForUtil.createFileInputStream(TSslQueue.serv_jks), TSslQueue.passw.toCharArray());
			kmf.init(ks, TSslQueue.passw.toCharArray());
			tmf.init(tks);
			ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
			SSLServerSocket serverSocket = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(TSslQueue.port);
			serverSocket.setNeedClientAuth(true);
			
			this.executor = executor;
			this.serverSocket = serverSocket;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 开始监听服务
	 */
	public void startService(){
		stoped = false;
		if(serverSocket==null){
			initExecutor();
		}
		try {
			while(true){
				if(stoped){
					break;
				}
				UUID uuid = UUID.randomUUID();
				String merId = uuid.toString();//订单编号
				
				Socket socket = serverSocket.accept();
				executor.execute(new Runnable_sslService(socket,executor,merId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 停服务
	 */
	public void stopService(){
		stoped = true;
		long stm = System.currentTimeMillis();
		try {
			log.info("-----------------开始停服务--");
			while(!executor.isTerminated()){
				long etm = System.currentTimeMillis();
				log.info("----服务暂停中--消耗："+((etm-stm)/1000)+"秒------仍有："+executor.getTaskCount()+"---"+executor.getActiveCount()+"---"+executor.getCompletedTaskCount()+"条线程活动中--"+stoped);
				Thread.sleep(1000);
				if(executor.getActiveCount()<1){
					executor.shutdown();
					serverSocket.close();
					serverSocket=null;
					executor = null;
					
					//测试关掉后还能不能开启
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 重启服务
	 */
	public void resetService(){
		stoped = true;
		long stm = System.currentTimeMillis();
		try {
			log.info("-----------------开始停服务--");
			while(!executor.isTerminated()){
				long etm = System.currentTimeMillis();
				log.info("----服务暂停中--消耗："+((etm-stm)/1000)+"秒------仍有："+executor.getTaskCount()+"---"+executor.getActiveCount()+"---"+executor.getCompletedTaskCount()+"条线程活动中--"+stoped);
				Thread.sleep(1000);
				if(executor.getActiveCount()<1){
					executor.shutdown();
					serverSocket.close();
					serverSocket=null;
					executor = null;
					
					startService();
					//测试关掉后还能不能开启
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)  {
		SSLsocket_service ssls = SSLsocket_service.getInstance();
		
//		TestServiceMutliThread mtd = new TestServiceMutliThread("停服务测试");
//		mtd.start();
		
		ssls.startService();
		
		log.info("------------------------执行完成--");
	}
}
/**
 * 测试暂停功能
 * @author gotop
 */
class TestServiceMutliThread extends Thread{

    private int ticket=100;//每个线程都拥有100张票

    TestServiceMutliThread(String name){
        super(name);//调用父类带参数的构造方法

    }

    public void run(){
    	try {
			Thread.sleep(5000);
			SSLsocket_service ssls = SSLsocket_service.getInstance();
			ssls.stopService();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
