package com.gotop.dataIssued.dao;


import com.gotop.dataIssued.model.TSendData;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ITSendDataDAO {

	/**
	 * 插入下发表
	 * @param sendData
	 * @return
	 * @throws Exception
	 */
	Long insert(TSendData sendData) throws Exception;
	
	/**
	 * 根据主键获得下发表
	 * @param businessKey
	 * @return
	 * @throws Exception
	 */
	TSendData getIssuedById(Long businessKey)  throws Exception;
	
	/**
	 * 根据主键更新下发表
	 * @param sendData
	 * @return
	 * @throws Exception
	 */
	int update(TSendData sendData) throws Exception;

	/**
	 * 根据登录人编号获取所有下发表
	 * @param page 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<TSendData> queryIssuedByEmpId(Page page, HashMap<String, Object> map) throws Exception;

	TSendData getIssuedByFlowId(String executionId) throws Exception;

	TSendData selectForDown(Long resourceId, Long userId);
	
}