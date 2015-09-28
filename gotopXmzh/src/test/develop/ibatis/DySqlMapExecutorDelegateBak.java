package test.develop.ibatis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import test.develop.FileDesc;

import com.gotop.util.security.ForUtil;
import com.ibatis.sqlmap.client.event.RowHandler;
import com.ibatis.sqlmap.engine.cache.CacheModel;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;

public class DySqlMapExecutorDelegateBak extends SqlMapExecutorDelegate {

	private DySqlMapClient client = null;

	public void setClient(DySqlMapClient client) {
		this.client = client;
	}

	public DySqlMapExecutorDelegateBak(SqlMapExecutorDelegate delegate) {
		super();
	}

	protected void checkAndRefreshSqlMap(String id) {
		FileDesc fd = client.getFileDescUseId(id);
		if (fd == null) {
			return;
		}
		String path = fd.getPath();
		File file = ForUtil.createFile(path);
		if (file.lastModified() > fd.getTm()) {
			InputStream is = null;
			try {
				 is = ForUtil.createFileInputStream(file);
				client.reParseSqlMap(is);
				client.refreshFileDesc(id, file.lastModified());
				System.out.println(file.getName() + " has been reloaded");
			} catch (FileNotFoundException e) {
				System.out.println(file.getName() + " reload failed! " + e.getMessage());
			} finally {
				if(is!=null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public int delete(SessionScope sessionScope, String id, Object param) throws SQLException {
		checkAndRefreshSqlMap(id);
		return super.delete(sessionScope, id, param);
	}

	@Override
	public void flushDataCache(String id) {
		super.flushDataCache(id);
	}

	@Override
	public CacheModel getCacheModel(String id) {
		return super.getCacheModel(id);
	}

	@Override
	public MappedStatement getMappedStatement(String id) {
		return super.getMappedStatement(id);
	}

	@Override
	public Object insert(SessionScope sessionScope, String id, Object param) throws SQLException {
		checkAndRefreshSqlMap(id);
		return super.insert(sessionScope, id, param);
	}

	@Override
	public List<?> queryForList(SessionScope sessionScope, String id, Object paramObject, int skip, int max) throws SQLException {
		checkAndRefreshSqlMap(id);
		return super.queryForList(sessionScope, id, paramObject, skip, max);
	}

	@Override
	public List<?> queryForList(SessionScope sessionScope, String id, Object paramObject) throws SQLException {
		checkAndRefreshSqlMap(id);
		return super.queryForList(sessionScope, id, paramObject);
	}

	@Override
	public Map<?, ?> queryForMap(SessionScope sessionScope, String id, Object paramObject, String keyProp, String valueProp) throws SQLException {
		checkAndRefreshSqlMap(id);
		return super.queryForMap(sessionScope, id, paramObject, keyProp, valueProp);
	}

	@Override
	public Map<?, ?> queryForMap(SessionScope sessionScope, String id, Object paramObject, String keyProp) throws SQLException {
		checkAndRefreshSqlMap(id);
		return super.queryForMap(sessionScope, id, paramObject, keyProp);
	}

	@Override
	public Object queryForObject(SessionScope sessionScope, String id,
			Object paramObject, Object resultObject) throws SQLException {
		checkAndRefreshSqlMap(id);
		return super.queryForObject(sessionScope, id, paramObject, resultObject);
	}

	@Override
	public Object queryForObject(SessionScope sessionScope, String id, Object paramObject) throws SQLException {
		checkAndRefreshSqlMap(id);
		return super.queryForObject(sessionScope, id, paramObject);
	}

//	@Override
//	public PaginatedList queryForPaginatedList(SessionScope sessionScope, String id, Object paramObject, int pageSize) throws SQLException {
//		checkAndRefreshSqlMap(id);
//		return super.queryForPaginatedList(sessionScope, id, paramObject, pageSize);
//	}

	@Override
	public void queryWithRowHandler(SessionScope sessionScope, String id, Object paramObject, RowHandler rowHandler) throws SQLException {
		checkAndRefreshSqlMap(id);
		super.queryWithRowHandler(sessionScope, id, paramObject, rowHandler);
	}

	@Override
	public int update(SessionScope sessionScope, String id, Object param) throws SQLException {
		checkAndRefreshSqlMap(id);
		return super.update(sessionScope, id, param);
	}

}