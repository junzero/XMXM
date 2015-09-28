package com.gotop.util.dataSource;

import java.sql.Connection;
import java.sql.SQLException;

import com.gotop.util.exception.DaoException;


/**
 * <p>Title: 测试类</p>
 *
 * <p>Description: 用来测试数据库的连接，项目上线前要删</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company:GOTOP</p>
 *
 * @author zhanglei
 *
 * @date Jul 20, 2009
 *
 * @version 1.0
 **/
public class TestDataSource {
	/**
	 * 测试数据库连接
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			Connection con = DataSourceFactory.getDataSource().getConnection();
			for(int i=0;i<3;i++){
				Connection con = DataConnect.getConnect();
				System.out.println(con.isClosed());
				DataConnect.closeConnect(con);
				System.out.println(con.isClosed());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
