package com.gotop.leave.dao;

import com.gotop.leave.model.TApplyLeave;
import com.gotop.leave.model.TApplyLeaveExample;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ITApplyLeaveDAO {
	
	/**
	 * 分页查询所有请假表
	 * @param page
	 * @return
	 */
	List selectAllLeave(Page page) throws Exception;
	
	/**
	 * 分页条件查询请假表
	 * @param page
	 * @param map
	 * @return
	 */
	List queryByMap(Page page, HashMap<String,Object> map) throws Exception;
	
	/**
	 * 根据主键获取请假表
	 * @param id
	 * @return
	 */
	TApplyLeave getLeaveById(Long id) throws Exception;
	
	/**
	 * 根据主键更新请假表
	 * @param obj
	 */
	void update(TApplyLeave obj) throws Exception;
	
	/**
	 * 插入请假表
	 * @param tapplyLeave
	 * @return
	 */
	Long insert(TApplyLeave tapplyLeave) throws Exception;

	TApplyLeave getLeaveByFlowId(String executionId) throws Exception;
}