package test.develop.ibatis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;
import org.springframework.util.ObjectUtils;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapConfigParser;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapParser;
import com.ibatis.sqlmap.engine.builder.xml.XmlParserState;

public class DySqlMapClientFactoryBean extends SqlMapClientFactoryBean {

	private List<Resource> resourceList = new ArrayList<Resource>();
	private SqlMapParser mapParser = null;

	public DySqlMapClientFactoryBean() {
		super();
	}

	/**
	 * 将ibatis的配置文件保存起来
	 */
	@Override
	public void setConfigLocation(Resource configLocation) {
		super.setConfigLocation(configLocation);
		if (configLocation != null && !resourceList.contains(configLocation)) {
			resourceList.add(configLocation);
		}
	}

	/**
	 * 将ibatis的配置文件保存起来
	 */
	@Override
	public void setConfigLocations(Resource[] configLocations) {
		super.setConfigLocations(configLocations);
		for (int i = 0; i < configLocations.length; i++) {
			if (configLocations[i] != null && !resourceList.contains(configLocations[i])) {
				resourceList.add(configLocations[i]);
			}
		}
	}

	@Override
	protected SqlMapClient buildSqlMapClient(Resource[] configLocations, Resource[] mappingLocations, Properties properties) throws IOException {
		if (ObjectUtils.isEmpty(configLocations)) {
			throw new IllegalArgumentException("At least 1 'configLocation' entry is required");
		}
		SqlMapClient client = null;
		SqlMapConfigParser configParser = new SqlMapConfigParser();

		for (int i = 0; i < configLocations.length; i++) {
			InputStream is = null;
			try {
				is = configLocations[i].getInputStream();
				client = configParser.parse(is, properties);
			} catch (RuntimeException ex) {
				throw new NestedIOException("Failed to parse config resource: "
						+ configLocations[i], ex.getCause());
			}finally{
				if(is!=null){
					is.close();
				}
			}
		}
		mapParser = SqlMapParserFactory.createSqlMapParser(configParser);
		client = new DySqlMapClient(client, mapParser, configParser, configLocations);
		return client;
	}
	
	/**
	 * Inner class to avoid hard-coded iBATIS 2.3.2 dependency (XmlParserState class).
	 */
	private static class SqlMapParserFactory {

		public static SqlMapParser createSqlMapParser(SqlMapConfigParser configParser) {
			// Ideally: XmlParserState state = configParser.getState();
			// Should raise an enhancement request with iBATIS...
			XmlParserState state = null;
			try {
				Field stateField = SqlMapConfigParser.class.getDeclaredField("state");
				stateField.setAccessible(true);
				state = (XmlParserState) stateField.get(configParser);
			}
			catch (Exception ex) {
				throw new IllegalStateException("iBATIS 2.3.2 'state' field not found in SqlMapConfigParser class - " +
						"please upgrade to IBATIS 2.3.2 or higher in order to use the new 'mappingLocations' feature. " + ex);
			}
			return new SqlMapParser(state);
		}
	}
}