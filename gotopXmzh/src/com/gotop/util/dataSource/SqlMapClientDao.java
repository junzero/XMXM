package com.gotop.util.dataSource;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

public class SqlMapClientDao extends SqlMapClientDaoSupport {
	/**
	 * 批量提交开始
	 * 
	 * @abatorgenerated
	 */
	public void startBatch() throws Exception {
		this.getSqlMapClient().startBatch();
	}

	/**
	 * 批量提交
	 * 
	 * @abatorgenerated
	 */
	public void executeBatch() throws Exception {
		this.getSqlMapClient().executeBatch();
	}
	/**
	 * 批量提交
	 * 
	 * @abatorgenerated
	 */
	public int executeBatchNum() throws Exception {
		return this.getSqlMapClient().executeBatch();
	}

	public int update(final String statementName, final Object parameterObject)
			throws DataAccessException {
		return getSqlMapClientTemplate().update(statementName, parameterObject);
	}

	public Object insert(final String statementName,
			final Object parameterObject) throws DataAccessException {
		return getSqlMapClientTemplate().insert(statementName, parameterObject);
	}

	public int delete(final String statementName, final Object parameterObject)
			throws DataAccessException {
		return getSqlMapClientTemplate().delete(statementName, parameterObject);
	}

	public int delete(final String statementName) throws DataAccessException {
		return getSqlMapClientTemplate().delete(statementName);
	}

	public Object queryForObject(final String statementName,
			final Object parameterObject) throws DataAccessException {
		return getSqlMapClientTemplate().queryForObject(statementName,
				parameterObject);
	}

	public Object queryForObject(final String statementName) throws DataAccessException {
		return getSqlMapClientTemplate().queryForObject(statementName);
	}
	public List queryForList(final String statementName,
			final Object parameterObject, Page page) throws DataAccessException {
		List result = getSqlMapClientTemplate().queryForList(statementName,
				parameterObject, page.getBegin(), page.getLength());
		if ("true".equals(page.getIsCount()) && page.getCount() < 1) {
			List resultCount = getSqlMapClientTemplate().queryForList(
					statementName, parameterObject, 0,
					SqlExecutor.COUNT_MUM_RESULTS);
			Integer count = (Integer) resultCount.get(0);
			page.setCount(count);
		}
		if ("true".equals(page.getIsCount())) {
			pageCondExpand pce = new pageCondExpand();
			pce.putPageCond(page);
		}
		return result;
	}

	public List queryForList(final String statementName,
			final Object parameterObject, int begin, int length)
			throws DataAccessException {
		List result = getSqlMapClientTemplate().queryForList(statementName,
				parameterObject, begin, length);
		return result;
	}

	public Integer queryForCount(final String statementName,
			final Object parameterObject) throws DataAccessException {
		List resultCount = getSqlMapClientTemplate().queryForList(
				statementName, parameterObject, 0,
				SqlExecutor.COUNT_MUM_RESULTS);
		Integer count = (Integer) resultCount.get(0);
		return count;
	}

	public List queryForList(final String statementName,
			final Object parameterObject) throws DataAccessException {
		List result = getSqlMapClientTemplate().queryForList(statementName,
				parameterObject);
		return result;
	}
}
