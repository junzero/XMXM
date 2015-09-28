package com.gotop.tyjg.ygfjxxChaXun.dao;
import java.util.HashMap;
import java.util.List;

import com.gotop.vo.tyjg.Abftygfjxxb;


public interface IYgfjxxChaXunDao {
	
	/**
	 * 根据条件查询员工附加信息
	 * @return
	 * @throws Exception
	 */	
	@SuppressWarnings("unchecked")
	public List queryYgfjxxByCondit(HashMap<String,String> cdMap) throws Exception;
	
	/**
	 * 根据条件统计员工附加信息记录数
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public long countYgfjxx(HashMap<String,String> cdMap) throws Exception;
	
	/**
	 * 删除Abftygfjxxb对象
	 * @param ygfjxx
	 * @return
	 * @throws Exception
	 */
	public int delYgfjxxEntity(Abftygfjxxb ygfjxx) throws Exception;
	
	/**
	 * 新增Abftygfjxxb对象
	 * @param ygfjxx
	 * @return
	 * @throws Exception
	 */
	public boolean insertYgfjxxEntity(Abftygfjxxb ygfjxx) throws Exception;
	
	/**
	 * 修改Abftygfjxxb对象
	 * @param ygfjxx
	 * @return
	 * @throws Exception
	 */
	public int updateYgfjxxEntity(Abftygfjxxb ygfjxx) throws Exception;
	
	
}
