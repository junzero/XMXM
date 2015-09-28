package com.gotop.util.dataSource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.gotop.util.Constants;
import com.gotop.util.ParseProperties;



/**
 * <p>Title: 数据库连接池</p>
 *
 * <p>Description: 用于与数据库的连接</p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author phc
 *
 * @date 2011-3-8
 *
 * @version 1.0
 **/
public class DataSourceFactory {

	
	private static BasicDataSource bds;

	public static DataSource getDataSource() {
		if (bds == null) {
			bds = new BasicDataSource();
			ParseProperties prop = ParseProperties.getInstance();
			String driver = prop.getProperty(Constants.DBCLASSDRIVER);
			String url = prop.getProperty(Constants.DBURL);
			String username = prop.getProperty(Constants.DBUSER);
			String password = prop.getProperty(Constants.DBPASS);
			int maxActive = Integer.parseInt(prop.getProperty(Constants.DBACTIVE));
			bds.setDriverClassName(driver);
			bds.setUrl(url);
			bds.setUsername(username);
			bds.setPassword(password);
			bds.setMaxActive(maxActive);
		}
		return bds;
	}
	public static DataSource getDataSource(HttpServletRequest request) {
		if (bds == null) {
			bds = new BasicDataSource();
			ParseProperties prop = ParseProperties.getInstance(request);
			String driver = prop.getProperty(Constants.DBCLASSDRIVER);
			String url = prop.getProperty(Constants.DBURL);
			String username = prop.getProperty(Constants.DBUSER);
			String password = prop.getProperty(Constants.DBPASS);
			int maxActive = Integer.parseInt(prop.getProperty(Constants.DBACTIVE));
			bds.setDriverClassName(driver);
			bds.setUrl(url);
			bds.setUsername(username);
			bds.setPassword(password);
			bds.setMaxActive(maxActive);
		}
		return bds;
	}
}
