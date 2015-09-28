package com.gotop.util.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.garyman.db.util.ResultPack;
import com.gotop.exception.GenericServiceException;
import com.gotop.util.exception.DaoException;
import com.gotop.util.jdbc.BeanPropertyRowMapper;
import com.gotop.util.security.ForUtil;


public class HibernatePersistenceManager extends HibernateDaoSupport implements
		IPersistenceManager {
	private JdbcTemplate jdbcTemplate = null;

	public void create(Serializable entity) {
		getHibernateTemplate().save(entity);
	}

	public void update(Serializable entity) {
		getHibernateTemplate().update(entity);
	}

	public void delete(Serializable entity) {
		try {
			getHibernateTemplate().delete(entity);
		} catch (Exception e) {
			
		}
	}

	public void delete(String sql) {
		try {
			getHibernateTemplate().delete(sql);
		} catch (Exception e) {
			
		}
	}

	public void saveOrUpdate(Serializable entity) {
		getHibernateTemplate().save(entity);
	}

	public void evict(Serializable entity) {
		getHibernateTemplate().evict(entity);
	}

	public void execute(String sql, Object[] params) {
		jdbcTemplate.update(sql, params);
	}
	
	public List find(String sql, Object[] params){
//		System.out.println("-----"+sql);
		return jdbcTemplate.queryForList(sql,params);
	}
	public List find(String sql){
//		System.out.println("-----"+sql);
		return jdbcTemplate.queryForList(sql);
	}

	public ResultPack findAll(String query, int pageNo, int pageCount) {
		jdbcTemplate.setMaxRows((pageNo + 1) * pageCount);
		ResultSetDAO dao = new ResultSetDAO(pageNo, pageCount);
		System.out.println("-----"+query);
		List list = (List) jdbcTemplate.query(query, dao);
		int nCount = list.size();//getTotalCount(query);
		ResultPack rpk = new ResultPack();
		rpk.populate(list, pageNo, pageCount, nCount);
		return rpk;

	}
	
	public ResultPack find(String sql,Object[] params, int pageNo, int pageCount) {
		jdbcTemplate.setMaxRows((pageNo + 1) * pageCount);
		ResultSetDAO dao = new ResultSetDAO(pageNo, pageCount);
//		System.out.println("-----"+sql);
//		for(int i=0;i<params.length;i++){
//			System.out.println("--"+params[i]);
//		}
		List list = (List) jdbcTemplate.query(sql,params, dao);
		//int nCount = list.size();//getTotalCount(query);
		int nCount = getTotalCount(sql,params);
		ResultPack rpk = new ResultPack();
		rpk.populate(list, pageNo, pageCount, nCount);
		return rpk;

	}
	
	/**
	 * 根据表名查询该表所有的字段
	 * @param tTableName 表名
	 * @return 表
	 * @throws DaoException
	 */
	public List<TableInfoDTO> queryDTO(String tTableName)throws GenericServiceException{
		List<TableInfoDTO> list = null;
		String querySql = "SELECT * FROM tableInfo WHERE tTableName = ? ORDER BY tDisplayNum ";
		String[] strs  = {tTableName};

//		logger.info("查询表信息的SQL语句："+querySql);
		try {
			list = jdbcTemplate.query(querySql, strs, new BeanPropertyRowMapper(TableInfoDTO.class));
		} catch (Exception e) {
			throw new GenericServiceException(e.getMessage());
		}
		
		return list;
	}	

	private int getTotalCount(String strsql,Object[] params) {
		int sql_from = strsql.toLowerCase().lastIndexOf("from");
		int sql_orderby = strsql.lastIndexOf("order by");// 为了改进
		String countStr = "";
//		if (sql_orderby > 0) {
//			countStr = "select count(*) "
//					+ strsql.substring(sql_from, sql_orderby);
//		} else {
//			countStr = "select count(*) " + strsql.substring(sql_from);
//		}
		countStr = "select count(*) from (" + strsql + ")";
		int nCount = 0;
		if(params.length>0){
			nCount = jdbcTemplate.queryForInt(countStr,params);
		}else{
			nCount = jdbcTemplate.queryForInt(countStr);
		}
		return nCount;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Criteria createCriteria(String entityName){
		return ForUtil.CSession(this).createCriteria(entityName);
	}
	
	public Crit createCrit(String entityName){
		Crit crit = new Crit(entityName, ForUtil.CSession(this));
		return crit;
	}
	
	public Crit createCrit(Class clazz){
		Crit crit = new Crit(clazz, ForUtil.CSession(this));
		return crit;
	}
	
	public Crit createCrit(Class clazz,boolean isClazz){
		Crit crit = new Crit(clazz, this.getSession(),isClazz);
		return crit;
	}

	public Session getHSession(){
		return this.getSession();
	}
}
