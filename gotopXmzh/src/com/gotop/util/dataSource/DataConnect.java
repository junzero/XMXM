package com.gotop.util.dataSource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.gotop.util.Constants;
import com.gotop.util.exception.DaoException;


public class DataConnect {
	private static Logger logger = Logger.getLogger(DataConnect.class);

	/**
	 * 建立数据库连接,连接N（Constants.DB_COUNT）次后，如果不成功抛出异常
	 * @return 数据库连接对象 Connection
	 * @throws DaoException 
	 */
	public static Connection getConnect() throws DaoException{
		Connection con = null;
		for (int i = 0; i < Constants.DB_COUNT; i++) {
			try {
				con = DataSourceFactory.getDataSource().getConnection();
				if (con == null) {
					System.out.println("第" + (i + 1) + "次连接数据库");
					logger.error("第" + (i + 1) + "次连接数据库");
					continue;
				} else {
					logger.info((i == 0 ? "" : "第" + (i + 1) + "次")+ "连接数据库成功");
				}
			} catch (SQLException e) {
				logger.error("建立数据库连接失败，请稍后重试！");
				if (con == null) {
					try {
						//十秒后重新连接数据库
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.err.println("第" + (i + 1) + "次重新连接数据库");
					logger.error("第" + (i + 1) + "次重新连接数据库");
					continue;
				}
			}
			break;
		}
		if(con==null){
			throw new DaoException("建立数据库连接失败，请稍后重试！");
		}
		return con;
	}
		
	/**
	 * 根据request对象建立数据库连接,返回数据库连接对象Connection
	 * @param request
	 * @return 数据库连接对象
	 * @throws DaoException
	 */
	public static Connection getConnect(HttpServletRequest request) throws DaoException{
		Connection con = null;
		for (int i = 0; i < Constants.DB_COUNT; i++) {
			try {
				con = DataSourceFactory.getDataSource(request).getConnection();
				if (con == null) {
					System.out.println("第" + (i + 1) + "次连接数据库");
					logger.error("第" + (i + 1) + "次连接数据库");
					continue;
				} else {
					logger.info((i == 0 ? "" : "第" + (i + 1) + "次")+ "连接数据库成功");
				}
			} catch (SQLException e) {
				logger.error("建立数据库连接失败，请稍后重试！");
				if (con == null) {
					try {
						//十秒后重新连接数据库
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.err.println("第" + (i + 1) + "次重新连接数据库");
					logger.error("第" + (i + 1) + "次重新连接数据库");
					continue;
				}
			}
			break;
		}
		if(con==null){
			throw new DaoException("建立数据库连接失败，请稍后重试！");
		}
		return con;
	}
	/**
	 * 关闭数据库连接
	 * 
	 * @param con
	 *            成功的数据库连接
	 * @throws DaoException
	 * 
	 */
	public static void closeConnect(Connection con) throws DaoException {
		try {
			if (!con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			logger.error("关闭数据库连接出错：" + e);
			throw new DaoException("关闭数据库连接出错：" + e);
		}
	}
}
