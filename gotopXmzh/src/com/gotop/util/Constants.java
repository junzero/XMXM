package com.gotop.util;

/**
 * <p>Title: 常量配置类</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: </p>
 *
 * @author phc
 *
 * @date 2011-3-8
 *
 * @version 1.0
 **/
public class Constants {
	/**
	 * 数据库及通讯的相关配置
	 */
	public static final String CONFIGFILE = "config.properties";
	public static final String WEBCONFIGFILE = "/config/config.properties";
	
	/**数据库配置文件中的相关KEY*/
	public static final String DBCLASSDRIVER = "db_driverClass";
	public static final String DBURL = "db_url";
	public static final String DBUSER = "db_username";
	public static final String DBPASS = "db_password";
	public static final String DBACTIVE = "db_maxActive";
	/**数据库重新连接的次数*/
	public static final int DB_COUNT = 8;
	
	/** FTP连接IP地址的参数名 */
	public static final String FTP_IP = "ftp_ip";
	
	/** Socket连接IP地址的参数名 */
	public static final String SOCKET_IP = "socket_ip";
	/** Socket连接端口号的参数名 */
	public static final String SOCKET_PORT = "socket_port";
	/** Socket连接端口号的参数名1 */
	public static final String SOCKET_PORT1 = "socket_port1";
	/** FTP连接用户名的参数名 */
	public static final String FTP_USERNAME = "ftp_username";
	/** FTP连接用户密码的参数名 */
	public static final String FTP_PASSWORD = "ftp_password";
	
	/** FTP文件上传 本地存放路径的参数名 */
	public static final String FTP_UPLOAD_LOCALPATH = "ftp_upload_localPath";
	/** FTP文件上传 后台服务器存放路径的参数名 */
	public static final String FTP_UPLOAD_SERVERPATH = "ftp_upload_serverPath";
	/** FTP文件下载 本地存放路径的参数名 */
	public static final String FTP_DOWNLOAD_LOCALPATH = "ftp_download_localPath";
	/** FTP文件下载 后台服务器存放路径的参数名 */
	public static final String FTP_DOWNLOAD_SERVERPATH = "ftp_download_serverPath";
	
	/** 交易成功响应码*/
	public static final String CODE_SUCCESS = "";
	
}

