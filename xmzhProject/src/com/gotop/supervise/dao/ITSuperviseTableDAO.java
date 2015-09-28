package com.gotop.supervise.dao;

import com.gotop.supervise.model.TSuperviseTable;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;

public interface ITSuperviseTableDAO {
   
	/**
	 * 新增
	 * @param sup
	 * @throws Exception
	 */
	public void insertSuperviseTable(TSuperviseTable sup)throws Exception;
	
	/**
	 * 查询
	 * @param sup
	 * @return
	 * @throws Exception
	 */
	public TSuperviseTable  querySuperviseTable(TSuperviseTable sup)throws Exception;
	
	/**
	 * 查询
	 * @param sup
	 * @return
	 * @throws Exception
	 */
	public TSuperviseTable  querySuperviseTable1(TSuperviseTable sup)throws Exception;
	
	/**
	 * 修改
	 * @param sup
	 * @throws Exception
	 */
	public void updateSuperviseTable(TSuperviseTable sup)throws Exception;
	
	/**
	 * 查询接收列表
	 * @param sup
	 * @return
	 * @throws Exception
	 */
	public List<TSuperviseTable> querySuperviseTables(TSuperviseTable sup,Page page)throws Exception;
	
	/**
	 * 修改阅读状态
	 * @param sup
	 * @throws Exception
	 */
	public void updateSuperviseStatus(TSuperviseTable sup)throws Exception;
	
	/**
	 * 获取部室下副负责人
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	public List<TSuperviseTable> queryTaskAssignPerson(HashMap<String, Object> hmp)throws Exception;
	
	/**
	 * 获取主要负责人
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	public TSuperviseTable queryTaskAssignZyPerson(HashMap<String, Object> hmp)throws Exception;

	/**
	 * 查询岗位为行领导的人
	 * @param hmp
	 * @return
	 */
	public List<TSuperviseTable> queryLeader(HashMap<String, Object> hmp);

}