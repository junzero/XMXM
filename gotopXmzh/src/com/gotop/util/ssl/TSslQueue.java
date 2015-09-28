package com.gotop.util.ssl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import org.apache.log4j.Logger;
public class TSslQueue {
	protected static Logger log = Logger.getLogger(TSslQueue.class);
//	public static String serv_jks = "D:/temp/zyb/serv.jks";
//	public static String password = "changeit";
//	public static String host="10.214.40.233";
//	public static int port=9990;
	public static String serv_jks = "/privatestore.jks";
	public static String client_jks= "/publicstore.jks";
	public static String passw = "keystoregotopord";
	public static String host="172.30.9.173"; 
//	public static String host="127.0.0.1";
	public static int port=4444;
	private static Queue<String> queue_mess = new LinkedList<String>();
	private static Queue<String> queue_failure = new LinkedList<String>();
	private static Queue<String> queue_success = new LinkedList<String>();
	
	public static int merId = 0;
	public static int index = 0;
	public static Long cSDate;
	public static Long cEDate;
	
	public static int corePoolSize=10;
	public static int maximumPoolSize=100;
	
	
	static {
		
		ClassLoader loader = TSslQueue.class.getClassLoader();// 类加载器
		Properties proper = new Properties();
		
		//String cpath = TSslQueue.class.getResource("/").getPath();
		//serv_jks = loader.getResource("/")+"com/gotop/util/ssl/privatestore.jks";
		//client_jks = loader.getResource("/")+"com/gotop/util/ssl/publicstore.jks";
		java.io.InputStream jiis = null;
		try {
			jiis = loader.getResourceAsStream("ipConfig.properties");
			proper.load(jiis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(jiis!=null){
				try {
					jiis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		TSslQueue.host=proper.getProperty("ip")==null?TSslQueue.host:proper.getProperty("ip");
		TSslQueue.port=proper.getProperty("port")==null?TSslQueue.port:Integer.parseInt(proper.getProperty("port"));
		serv_jks = proper.getProperty("serv_jks");
		client_jks = proper.getProperty("client_jks");
		System.out.println("serv_jks地址:"+TSslQueue.serv_jks+"===========================client_jks地址:"+TSslQueue.client_jks);
		System.out.println("连接地址:"+TSslQueue.host+"===========================连接杜端口号:"+TSslQueue.port);
	}
	
	public static void offer_mess(String s){
		queue_mess.offer(s);
	}
	public static String poll_mess(){
		return queue_mess.poll();
	}
	public static void offer_failure(String s){
		queue_failure.offer(s);
	}
	public static String poll_failure(){
		return queue_failure.poll();
	}
	public static int poll_size(){
		return queue_failure.size();
	}
	public static void offer_success(String s){
		queue_success.offer(s);
	}
	public static String poll_success(){
		return queue_success.poll();
	}
	
	
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer("Hello");
        queue.offer("World!");
        queue.offer("你好！");
        System.out.println(queue.size());
        String str;
        while((str=queue.poll())!=null){
            System.out.print("  "+str);
        }
        System.out.println();
        System.out.println(queue.size());
    }
}