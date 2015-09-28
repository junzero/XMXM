package com.gotop.util.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class ConUtils {
	protected static Logger log = Logger.getLogger(ConUtils.class);
	private static DataSource dataSource;
	public static Connection getConn() throws Exception {
		Connection conn;
		if(dataSource==null){
			conn = getConnection();
			return conn;
		}
		conn = dataSource.getConnection();
		if(conn==null){
			conn = getConnection();
		}
		return conn;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		ConUtils.dataSource = dataSource;
	}
	private static Connection getConnection() throws ClassNotFoundException,SQLException {
//		String driver = "oracle.jdbc.driver.OracleDriver";
//		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
//		String user = "tyjg";
//		String passw = "tyjg";
//
//		Class.forName(driver);
//		Connection conn = DriverManager.getConnection(url, user, passw);
		return null;
	}
}
