package com.gotop.leave.service;

import com.gotop.dto.BusinessDto;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.leave.dao.ITApplyLeaveDAO;
import com.gotop.leave.model.TApplyLeave;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface ITApplyLeaveService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settApplyLeaveDAO(ITApplyLeaveDAO tApplyLeaveDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITApplyLeaveDAO gettApplyLeaveDAO() throws Exception;

    /**
     * 查询
     * @param page
     * @param map
     * @return
     * @throws Exception
     */
	List<TApplyLeave> queryByMap(Page page, HashMap<String, Object> map) throws Exception;
	
	/**
	 * 获得请假表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	TApplyLeave getLeaveById(Long id) throws Exception;
	
	/**
	 * 获取请假列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List queryAllDataList(Page page) throws Exception;

	void insertApprove(TApproveOpninion approveOpninion,
			TaskAssgineeDto taskAssgineeDto, String taskId, int flag) throws Exception;

	TApplyLeave getLeaveByFlowId(String executionId) throws Exception;

	/**
	 * 根据主键更新请假表
	 * @param tapplyLeave
	 * @param muo
	 * @param businessDto
	 * @param taskAssgineeDto
	 * @return
	 * @throws Exception
	 */
	int update(TApplyLeave tapplyLeave, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception;

	/**
	 * 插入数据
	 * @param tapplyLeave
	 * @param muo
	 * @param businessDto
	 * @param taskAssgineeDto
	 * @return
	 * @throws Exception
	 */
	Long insert(TApplyLeave tapplyLeave, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception;
}
