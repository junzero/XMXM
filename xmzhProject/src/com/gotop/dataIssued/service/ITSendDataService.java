package com.gotop.dataIssued.service;

import com.gotop.dataIssued.dao.ITSendDataDAO;
import com.gotop.dataIssued.model.TSendData;
import com.gotop.dto.BusinessDto;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface ITSendDataService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settSendDataDAO(ITSendDataDAO tSendDataDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITSendDataDAO gettSendDataDAO() throws Exception;

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    void update(TSendData obj) throws Exception;
	
	/**
	 * 通过主键获得下发表
	 * @param businessKey
	 * @return
	 */
	TSendData getIssuedById(Long businessKey) throws Exception;
	
	/**
	 * 根据登录用户获取下发表
	 * @param page 
	 * @param sendData
	 * @return 
	 * @throws Exception
	 */
	List<TSendData> queryIssuedByEmpId(Page page, TSendData sendData) throws Exception;

	/**
	 * 将销毁信息更新到人员表
	 * @param desId
	 * @param desTime
	 * @param userId
	 * @param dsId 
	 * @throws Exception
	 */
	void updateUser(Long desId, String desTime, String userId, String dsId) throws Exception;

	TSendData getIssuedByFlowId(String executionId) throws Exception;

	/**
	 * 保存数据下发表
	 * @param sendData
	 * @param muo
	 * @param businessDto
	 * @param taskAssgineeDto
	 * @return
	 * @throws Exception
	 */
	Long insert(TSendData sendData, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception;

	/**
	 * 更新数据下发表
	 * @param sendData
	 * @param muo
	 * @param businessDto
	 * @param taskAssgineeDto
	 * @throws Exception
	 */
	int update(TSendData sendData, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception;

	/**
	 * 审核意见
	 * @param approveOpninion
	 * @param businessDto
	 * @param taskAssgineeDto
	 */
	int insertApprove(TApproveOpninion approveOpninion,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception;

	TSendData selectForDown(Long resourceId, Long userId);
}