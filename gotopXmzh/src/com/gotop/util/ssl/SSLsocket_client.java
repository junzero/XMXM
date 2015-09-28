package com.gotop.util.ssl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.security.KeyStore;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

import org.apache.log4j.Logger;

import com.gotop.util.security.ForUtil;
/**
 * 双向认证
 * @author gotop
 *
 */
public class SSLsocket_client {
	protected static Logger log = Logger.getLogger(SSLsocket_client.class);
	private static SSLsocket_client ssls;
	
	private static ThreadPoolExecutor executor;
	private SocketFactory ssf;
	private boolean stoped;
	public static SSLsocket_client getInstance(){
		if(ssls==null){
			ssls = new SSLsocket_client();
		}
		return ssls;
	}
	private SSLsocket_client(){
		initExecutor();
		log.info("------------客户初始化---");
	}
	/**
	 * 初始化证书等信息
	 */
	public void initExecutor(){
		try {
			//线程数
			BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();   
			executor = new ThreadPoolExecutor(10, 200, 1, TimeUnit.SECONDS, queue);
			
			//构造SSL环境，指定SSL版本为3.0，也可以使用TLSv1，但是SSLv3更加常用。
			SSLContext ctx = SSLContext.getInstance("SSL");
			//创建用于管理JKS密钥库的X.509密钥管理器。
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			//访问Java密钥库，JKS是keytool创建的Java密钥库，保存密钥。
			KeyStore ks = KeyStore.getInstance("JKS");
			KeyStore tks = KeyStore.getInstance("JKS");
			
			ks.load(ForUtil.createFileInputStream(TSslQueue.client_jks), TSslQueue.passw.toCharArray());
			tks.load(ForUtil.createFileInputStream(TSslQueue.client_jks), TSslQueue.passw.toCharArray());
			kmf.init(ks, TSslQueue.passw.toCharArray());
			tmf.init(tks);
			ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
			
			ssf = ctx.getSocketFactory();
			stoped = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ThreadPoolExecutor getExecutor(){
		return executor;
	}
	/**
	 * 放入任务列表，并开始处理任务
	 */
	public void addSslClient(String mess){
		TSslQueue.offer_mess(mess);
		addSslClient();
	}
	/**
	 * 开始处理任务
	 */
	public void addSslClient(){
		if(stoped){
			log.info("-------------------任务已关闭--");
		}else{
			if(executor==null){
				initExecutor();
			}
			String mess = "";
			executor.execute(new Runnable_sslClient(ssf,executor,mess));
		}
	}
	/**
	 * 
	 */
	public String querySslClient1(String cMess){
		String result = null;;
		try {
			SSLSocket csocket = (SSLSocket) ssf.createSocket(TSslQueue.host, TSslQueue.port);
			// 以下代码同socket通讯实例中的代码
//			BufferedReader socketIn = new BufferedReader(new InputStreamReader(csocket.getInputStream()));// 接受到的信息
			PrintStream socketOut = new PrintStream(csocket.getOutputStream());// 要发送的信息
			
			socketOut.println(cMess);
			
			BufferedInputStream socketIn = new BufferedInputStream(csocket.getInputStream());
			
			StringBuffer sbr = new StringBuffer();
			byte[] b = new byte[1024];
			while (socketIn.read(b)!=-1){
				String sgb = new String(b);
				sbr.append(sgb);
				b = new byte[1024];
			}
			result = sbr.toString().trim();
			
//			System.out.println("--------result.getBytes().length---"+result.toString().trim().getBytes().length);
//			System.out.println("--------result2.toString().length()---"+result.toString().getBytes().length);
			socketOut.println("[end]");
			socketOut.close();
			socketIn.close();
			csocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	/**
	 * 
	 */
	public String querySslClient(String cMess){
		String result = null;;
		try {
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
			result = sbr.toString();
			socketOut.println("[end]");
			
			socketIn.close();
			socketOut.close();
			csocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
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
					ssf=null;
					executor = null;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 开始测试
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SSLsocket_client sslc = SSLsocket_client.getInstance();
		try {
//			TestClientMutliThread tcmt = new TestClientMutliThread("客户端暂时测试");
//			tcmt.start();
			
			TSslQueue.index = 0;
			TSslQueue.merId = 0;
			TSslQueue.cSDate = System.currentTimeMillis();
			for(int i=0;i<500;i++){
				String mess = "-client---"+i;
				mess = "HEAD=2601#|00#|3509000010002814#|19#|35000503#|35000503#|111111111111#|11111111#|CS01#|CS01#|000128#|BODYDATA=11021101#|85511113";
				sslc.addSslClient(mess);
				TSslQueue.index++;
			}
			executor.shutdown();
			while(!executor.isTerminated()){
				Thread.sleep(1);
			}
			TSslQueue.cEDate = System.currentTimeMillis();
			long cse = TSslQueue.cEDate - TSslQueue.cSDate;
			cse = cse/1000;
			if(cse<1){
				cse=1;
			}
			double db = ((double)cse)/(TSslQueue.index);
			double ttl = ((double)TSslQueue.index)/(cse);
			System.out.println("---时间-"+cse+"秒" +"----执行次数-"+TSslQueue.index+"----平均-"+(db)+"(秒/个)"+"---"+ttl+"(个/秒)--执行总数-"+executor.getCompletedTaskCount()+"-----失败个数-"+TSslQueue.poll_size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/**
 * 测试暂停功能
 * @author gotop
 */
class TestClientMutliThread extends Thread{

    private int ticket=100;//每个线程都拥有100张票

    TestClientMutliThread(String name){
        super(name);//调用父类带参数的构造方法

    }

    public void run(){
    	try {
			Thread.sleep(10000);
			SSLsocket_client ssls = SSLsocket_client.getInstance();
			ssls.stopService();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}