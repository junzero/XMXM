package com.gotop.util.net;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.enterprisedt.net.ftp.FTPClient;
import com.enterprisedt.net.ftp.FTPConnectMode;
import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FTPMessageCollector;
import com.enterprisedt.net.ftp.FTPTransferType;

/**
 * <p>Title: FTP客户端</p>
 *
 * <p>Description: 通过该客户端来实现和其他主机的FTP连接</p>
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
public class FtpClient {
	private static Logger logger = Logger.getLogger(FtpClient.class);
	/** FTP服务器的IP地址 */
	private String host;
	/** 
	 * 文件传输方式
	 * 默认使用BINARY方式
	 * (传输一些如txt文本文件使用ASCII方式，传输非文本文件如zip、jpg等文件需要使用BINARY方式)
	 */
	private FTPTransferType type;
	/** FTP客户端 */
	private FTPClient ftp;
	
	
	/**
	 * 默认构造方法
	 * @throws FTPException 
	 */
	public FtpClient(){
		this.type = FTPTransferType.ASCII;
		this.ftp = new FTPClient();
		try {
			ftp.setControlEncoding("GB2312");//FTP客户端转码
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//转码
	}
	
	/**
	 * 构造方法
	 * @param host FTP服务器的IP地址
	 * @throws FTPException 
	 */
	public FtpClient(String host){
		this.host = host;
		this.type = FTPTransferType.ASCII;
		this.ftp = new FTPClient();
		try {
			ftp.setControlEncoding("GB2312");//FTP客户端转码
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//转码
	}
	
	/**
	 * 登录FTP服务器
	 * @throws IOException 
	 * @throws FTPException 
	 */
	public void login(String username, String password) throws IOException, FTPException {
		// 设置服务器地址
		ftp.setRemoteHost(this.getHost());
		ftp.setMessageListener(new FTPMessageCollector());
		// 连接服务器
		ftp.connect();

		logger.info("成功连接FTP服务器[" + this.getHost() + "]");

		// 登录服务器
		ftp.login(username, password);

		logger.info("成功登录FTP服务器[" + this.getHost() + "]，登录用户[" + username + "]");

		// 设置连接模式
		ftp.setConnectMode(FTPConnectMode.PASV);
	}
	
	/**
	 * 上传本地文件到远程服务器
	 * @param localFile 本地文件全路径
	 * @param remoteFilePath 远程服务器上传文件所在的目录
	 * @param remoteFileName 远程文件名
	 * @throws IOException
	 * @throws FTPException
	 */
	public void upload(String localFile, String remoteFilePath, String remoteFileName) throws IOException, FTPException {
		// 设置文件传输方式
		ftp.setType(this.getType());
//		ftp.setTransferBufferSize(4096);

		// 跳转到下载文件所在的目录
		ftp.chdir(remoteFilePath);
		logger.info("跳转到FTP服务器[" + this.getHost() + "]的目录[" + remoteFilePath + "]");
		
		// 上传文件到远程服务器
		ftp.put(localFile, remoteFileName);
		logger.info("成功上传本地文件[" + localFile + "]到FTP服务器[" + this.getHost() + "]，文件名[" + remoteFileName + "]");
	}
	/**
	 * 上传文件流到远程服务器
	 * @param ins
	 * @param remoteFilePath
	 * @param remoteFileName
	 * @throws IOException
	 * @throws FTPException
	 */
	public void upload(InputStream ins, String remoteFilePath, String remoteFileName) throws IOException, FTPException {
		ftp.setType(this.getType());
		ftp.setTransferBufferSize(4096);
		// 跳转到下载文件所在的目录
		ftp.chdir(remoteFilePath);
		logger.info("跳转到FTP服务器[" + this.getHost() + "]的目录[" + remoteFilePath + "]");
		// 上传文件到远程服务器
		
		ftp.put(ins, remoteFileName);
		logger.info("成功文件[" + remoteFileName+ "]到FTP服务器[" + this.getHost() + "]");
	}
	
	/**
	 * 下载远程服务器上的文件，文件下载完成后删除服务器上的文件
	 * @param localFile 本地文件全路径
	 * @param remoteFilePath 远程服务器下载文件所在的目录
	 * @param remoteFileName 远程文件名
	 * @throws IOException
	 * @throws FTPException
	 */
	public void download(String localFile, String remoteFilePath, String remoteFileName) throws IOException, FTPException {
		download(localFile, remoteFilePath, remoteFileName, true);
	}
	
	/**
	 * 下载远程服务器上的文件
	 * @param localFile 本地文件全路径
	 * @param remoteFilePath 远程服务器下载文件所在的目录
	 * @param remoteFileName 远程文件名
	 * @param deleteFile 文件删除标志 true为删除, false为不删除
	 * @throws IOException
	 * @throws FTPException
	 */
	public void download(String localFile, String remoteFilePath, String remoteFileName, boolean deleteFile) throws IOException, FTPException {
		// 设置文件传输方式
		ftp.setType(this.getType());
		
		// 跳转到下载文件所在的目录
		ftp.chdir(remoteFilePath);
		logger.info("跳转到FTP服务器[" + this.getHost() + "]的目录[" + remoteFilePath + "]");
		
		// 下载远程服务器上的文件
		ftp.get(localFile, remoteFileName);
		logger.info("成功下载FTP服务器[" + this.getHost() + "]上的文件[" + remoteFileName + "]到[" + localFile + "]");
		
		// 如果删除文件标志为true 则删除远程服务器上的文件
		if(deleteFile) {
			ftp.delete(remoteFileName);
			logger.info("成功删除FTP服务器[" + this.getHost() + "]上的文件[" + remoteFileName + "]");
		}
	}
	
	
	/**
	 * @param remoteFilePath
	 * @param remoteFileName
	 * @param deleteFile
	 * @return InputStream
	 * @throws IOException
	 * @throws FTPException
	 */
	public byte[] download(String remoteFilePath, String remoteFileName, boolean deleteFile) throws IOException, FTPException {
		// 设置文件传输方式
		ftp.setType(this.getType());
		
		// 跳转到下载文件所在的目录
		ftp.chdir(remoteFilePath);
		logger.info("跳转到FTP服务器[" + this.getHost() + "]的目录[" + remoteFilePath + "]");
		
		// 下载远程服务器上的文件
		byte[] ftpGet=ftp.get(remoteFileName);
		logger.info("成功下载FTP服务器[" + this.getHost() + "]上的文件[" + remoteFileName + "]");
		
		// 如果删除文件标志为true 则删除远程服务器上的文件
		if(deleteFile) {
			ftp.delete(remoteFileName);
			logger.info("成功删除FTP服务器[" + this.getHost() + "]上的文件[" + remoteFileName + "]");
		}
		return ftpGet;
	}
	/**
	 * 断开FTP服务器连接
	 * @throws IOException
	 * @throws FTPException
	 */
	public void quit() throws IOException, FTPException {
		ftp.quit();
		logger.info("成功断开FTP服务器[" + this.getHost() + "]的连接");
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public FTPTransferType getType() {
		return type;
	}

	public void setType(FTPTransferType type) {
		this.type = type;
	}
}