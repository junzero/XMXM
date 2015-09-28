package com.gotop.util.hibernate;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import com.garyman.db.util.ResultPack;
/**
 * PM
 * @author	garyman
 * @create	2010-04-18
 */
public interface IPersistenceManager {
	/**
	 * 添加记录
	 * @param entity 
	 */
	public void create(Serializable entity);
	/**
	 * 修改记录
	 * @param entity
	 */
	public void update(Serializable entity);
	/**
	 * 删除记录
	 * @param entity
	 */
	public void delete(Serializable entity);
	/**
	 * 删除记录
	 * @param sql
	 */
	public void delete(String sql);
	/**
	 * 保存记录
	 * @param entity
	 */
	public void saveOrUpdate(Serializable entity);
	/**
	 * 在进行update时如果之前你进行过getInfo操作，必须进行些操作后才能update
	 * @param entity
	 */
	public void evict(Serializable entity);
	/**
	 * 直接执行更新或是删除sql语句(jdbc api)
	 * @param sql sql批量更新或是批量删除操作
	 * @param params 参数
	 */
	public void execute(String sql,Object[] params);	
	/**
	 * 
	 */
	public List find(String sql, Object[] params);
	
	public List find(String sql);
	/**
	 * 目前用做报表分组查询，目的结果集是Map
	 * @param query
	 * @param pageNo
	 * @param pageCount
	 * @return
	 */
	public ResultPack findAll(String query, int pageNo, int pageCount);
	
	/**
	 * 带分页和查询参数的分页查询
	 * @param sql
	 * @param params
	 * @param pageNo
	 * @param pageCount
	 * @return
	 */
	public ResultPack find(String sql,Object[] params, int pageNo, int pageCount);
	
	public List<TableInfoDTO> queryDTO(String tTableName);
	
	public JdbcTemplate getJdbcTemplate();
	
	public Crit createCrit(String entityName);
	
	public Crit createCrit(Class clazz);
	
	public Crit createCrit(Class clazz,boolean isClazz);
	
	public Session getHSession();
}
