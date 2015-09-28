package com.gotop.util.net;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.gotop.util.Constants;
import com.gotop.util.ParseProperties;
import com.gotop.util.string.StringUtil;

/**
 * <p>Title: 请求发送类</p>
 *
 * <p>Description: 该类用于向后台发送请求报文、接收响应和解析响应报文，以及通过FTP上传下载文件 </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author phc
 *
 * @date Jul 20, 2009
 *
 * @version 1.0
 **/
public class Sender {
	private SocketClient socketClient;
	private FtpClient ftpClient;
	private String username;
	private String password;
	private String uploadLocalPath;
	private String uploadServerPath;
	private String downloadLocalPath;
	private String downloadServerPath;
	
	public Sender(){
		init();
	}
	
	public Sender(HttpServletRequest request){
		init(request);
	}
	
	/**
	 * 初始化方法
	 */
	private void init(){
		ParseProperties config = ParseProperties.getInstance();
		String socketIP = config.getProperty(Constants.SOCKET_IP);
		String ftpIP = config.getProperty(Constants.FTP_IP);
		int socketPort = Integer.parseInt(config.getProperty(Constants.SOCKET_PORT));
		
		username = config.getProperty(Constants.FTP_USERNAME);
		password = config.getProperty(Constants.FTP_PASSWORD);
		uploadLocalPath = config.getProperty(Constants.FTP_UPLOAD_LOCALPATH);
		uploadServerPath = config.getProperty(Constants.FTP_UPLOAD_SERVERPATH);
		downloadLocalPath = config.getProperty(Constants.FTP_DOWNLOAD_LOCALPATH);
		downloadServerPath = config.getProperty(Constants.FTP_DOWNLOAD_SERVERPATH);
		
		socketClient = new SocketClient(socketIP, socketPort);
		ftpClient = new FtpClient(ftpIP);
	}
	
	/**
	 * 根据request取得配置文件
	 * @param request
	 */
	private void init(HttpServletRequest request){
		ParseProperties config = ParseProperties.getInstance(request);
		String socketIP = config.getProperty(Constants.SOCKET_IP);
		String ftpIP = config.getProperty(Constants.FTP_IP);
		int socketPort = Integer.parseInt(config.getProperty(Constants.SOCKET_PORT));
		
		username = config.getProperty(Constants.FTP_USERNAME);
		password = config.getProperty(Constants.FTP_PASSWORD);
		uploadLocalPath = config.getProperty(Constants.FTP_UPLOAD_LOCALPATH);
		uploadServerPath = config.getProperty(Constants.FTP_UPLOAD_SERVERPATH);
		downloadLocalPath = config.getProperty(Constants.FTP_DOWNLOAD_LOCALPATH);
		downloadServerPath = config.getProperty(Constants.FTP_DOWNLOAD_SERVERPATH);
		
		socketClient = new SocketClient(socketIP, socketPort);
		ftpClient = new FtpClient(ftpIP);
	}
	
	/**
	 * 报文发送函数，发送单条报文
	 * @param request 请求报文
	 * @return 响应报文
	 * @throws Exception 
	 */
	public String sendRequest(String request) throws Exception {
		String response = null;
		
		try {
			response = socketClient.sendRequest(request);
			
		} catch (Exception e) {
			throw new Exception("发送报文出错 " + e.getMessage(), e);
		}

		return response;
	}
	
	/**
	 * 报文发送函数，发送单条报文
	 * @param request 请求报文
	 * @param timeout 超时时间(单位毫秒)
	 * @return 响应报文
	 * @throws Exception 
	 */
	public String sendRequest(String request, int timeout) throws Exception {
		String response = null;
		
		try {
			this.socketClient.setTimeout(timeout);
			response = this.socketClient.sendRequest(request);
		} catch (Exception e) {
			throw new Exception("发送报文出错 " + e.getMessage(), e);
		}

		return response;
	}
	
	/**
	 * 报文发送函数，发送一组报文
	 * @param request 请求报文数组
	 * @throws Exception 
	 */
	public void sendRequest(String[] request) throws Exception {
		try {
			for(int i=0; i<request.length; i++){
				this.sendRequest(request[i]);
			}
		} catch (Exception e) {
			throw new Exception("发送报文出错 " + e.getMessage(), e);
		}
	}
	
	/**
	 * 报文发送函数，发送一组报文
	 * @param request 请求报文数组
	 * @param timeout 超时时间(单位毫秒)
	 * @throws Exception 
	 */
	public void sendRequest(String[] request, int timeout) throws Exception {
		try {
			for(int i=0; i<request.length; i++){
				this.socketClient.setTimeout(timeout);
				this.sendRequest(request[i]);
			}
		} catch (Exception e) {
			throw new Exception("发送报文出错 " + e.getMessage(), e);
		}
	}
	
	/**
	 * 解析响应报文  
	 * 如果交易成功，则返回成功响应信息
	 * @param response 响应报文
	 * @return 成功响应信息
	 * @throws Exception 
	 */
	public String parseResponse(String response) throws Exception{
		String[] tmpStr = null;
		response = StringUtil.trim(response);
		
		if("".equals(response)){
			throw new Exception("响应报文为空！");
		}
		
		tmpStr = response.split("\\|");
		
		if(tmpStr.length < 2){
			throw new Exception("响应报文格式错误！");
		} else if(!Constants.CODE_SUCCESS.equals(StringUtil.trim(tmpStr[0]))){
			throw new Exception(StringUtil.trim(tmpStr[1]));
		}
		
		return StringUtil.trim(tmpStr[1]);
	}
	
	/**
	 * 上传本地文件到远程服务器
	 * @param fileName 文件名
	 * @throws Exception 
	 */
	public void uploadFile(String fileName) throws Exception{
		String localFile = fileName;
		
		if(uploadLocalPath == null || "".equals(uploadLocalPath.trim())){
			localFile = fileName;
		}else {
			localFile = uploadLocalPath + fileName;
		}
		
		try {
			ftpClient.login(username, password);
			ftpClient.upload(localFile, uploadServerPath, fileName);
		} catch (Exception e) {
			throw new Exception("上传文件出错 " + e.getMessage(), e);
		} finally{
			if(ftpClient != null) ftpClient.quit();
		}
	}
	/**
	 * 上传文件流到远程服务器
	 * @param ins
	 * @param fileName
	 * @throws Exception
	 */
	public void uploadFile(InputStream ins,String fileName) throws Exception{
		try {
			ftpClient.login(username, password);
			ftpClient.upload(ins, uploadServerPath, fileName);
		} catch (Exception e) {
			throw new Exception("上传文件出错 " + e.getMessage(), e);
		}finally{
			if(ftpClient != null) ftpClient.quit();
		}
	}
	/**
	 * 下载结果数据文件，文件下载完成后删除服务器上的文件
	 * @param fileName 文件名
	 * @throws Exception 
	 */
	public void downloadFile(String fileName) throws Exception{
		this.downloadFile(fileName, true);
	}
	
	/**
	 * 下载结果数据文件
	 * @param fileName 文件名
	 * @param deleteFile 文件下载完成后是否删除服务器上的文件
	 * @throws Exception 
	 */
	public void downloadFile(String fileName, boolean deleteFile) throws Exception{
		String localFile = fileName;
		
		if(downloadLocalPath == null || "".equals(downloadLocalPath.trim())){
			localFile = fileName;
		}else {
			localFile = downloadLocalPath + fileName;
		}
		
		try {
			ftpClient.login(username, password);
			ftpClient.download(localFile, downloadServerPath, fileName, deleteFile);
		} catch (Exception e) {
			throw new Exception("下载文件出错 " + e.getMessage(), e);
		} finally{
			if(ftpClient != null) ftpClient.quit();
		}
	}

	/**
	 * 下载结果数据文件 byte流
	 * @param remoteFileName 文件名
	 * @param deleteFile 文件下载完成后是否删除服务器上的文件
	 * @return byte[] 下载的文件流
	 * @throws Exception
	 */
	public byte[] downloadFileIns(String remoteFileName, boolean deleteFile) throws Exception{
		try {
			ftpClient.login(username, password);
			byte[] ftpGet=ftpClient.download(downloadServerPath, remoteFileName, deleteFile);
			return ftpGet;
		} catch (Exception e) {
			throw new Exception("下载文件出错 " + e.getMessage(), e);
		} finally{
			if(ftpClient != null) ftpClient.quit();
		}
	}
	public String getDownloadLocalPath() {
		return downloadLocalPath;
	}

	public void setDownloadLocalPath(String downloadLocalPath) {
		this.downloadLocalPath = downloadLocalPath;
	}

	public String getDownloadServerPath() {
		return downloadServerPath;
	}

	public void setDownloadServerPath(String downloadServerPath) {
		this.downloadServerPath = downloadServerPath;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUploadLocalPath() {
		return uploadLocalPath;
	}

	public void setUploadLocalPath(String uploadLocalPath) {
		this.uploadLocalPath = uploadLocalPath;
	}

	public String getUploadServerPath() {
		return uploadServerPath;
	}

	public void setUploadServerPath(String uploadServerPath) {
		this.uploadServerPath = uploadServerPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
//	public static void main(String[] args) {
//		
//		
//		Sender sender=new Sender();
//		String str="01050800222009-02-0609.01.24306123        00000841        1002      GM001111111111111111111100000000000000123";
//		try {
//			sender.sendRequest(str);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.print("出错");
//		}
//	}
}