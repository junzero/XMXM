package com.gotop.dataApply.service;

import com.gotop.dataApply.dao.ITApplyDataDAO;
import com.gotop.dataApply.model.TApplyData;
import com.gotop.dto.BusinessDto;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface ITApplyDataService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settApplyDataDAO(ITApplyDataDAO tApplyDataDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITApplyDataDAO gettApplyDataDAO() throws Exception;
   
    /**
     * 根据主键获得申请表
     * @param businessKey
     * @return
     * @throws Exception
     */
	TApplyData getApplyDataById(Long businessKey) throws Exception;
	
	/**
	 * 根据流程实例获取数据申请表
	 * @param executionId
	 * @return
	 * @throws Exception
	 */
	TApplyData getLeaveByFlowId(String executionId) throws Exception;
	
	/**
	 * 获得包含提取可行性分析人的申请表
	 * @param businessKey
	 * @return
	 * @throws Exception
	 */
	TApplyData getApplyDataAnalyst(Long businessKey) throws Exception;

	/**
	 * 申请人完成节点
	 * @param taskId
	 * @param taskAssgineeDto
	 * @throws Exception
	 */
	void completeTask(String taskId, TaskAssgineeDto taskAssgineeDto) throws Exception;

	/**
	 * 获取含风险可行性分析员的数据
	 * @param businessKey
	 * @return
	 * @throws Exception
	 */
	TApplyData getApplyRiskAnalyst(Long businessKey) throws Exception;
	
	/**
	 * 获取含所有分析人的数据
	 * @param businessKey
	 * @return
	 * @throws Exception
	 */
	TApplyData getApplyAnalyst(Long businessKey) throws Exception;

	/**
	 * 插入单条记录
	 * @param applyData
	 * @param muo
	 * @param businessDto
	 * @param taskAssgineeDto
	 * @param approveOpninion 
	 * @return
	 * @throws Exception
	 */
	Long insert(TApplyData applyData, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto, TApproveOpninion approveOpninion) throws Exception;

	/**
	 * 根据主键更新单条记录
	 * @param applyData
	 * @param muo
	 * @param businessDto
	 * @param taskAssgineeDto
	 * @throws Exception
	 */
	void update(TApplyData applyData, MUOUserSession muo,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto, TApproveOpninion approveOpninion) throws Exception;

	/**
	 * 插入审核信息
	 * @param applyData 
	 * @param approveOpninion
	 * @param businessDto
	 * @param taskAssgineeDto
	 * @return
	 * @throws Exception
	 */
	int insertApprove(TApplyData applyData, TApproveOpninion approveOpninion,
			BusinessDto businessDto, TaskAssgineeDto taskAssgineeDto) throws Exception;

	/**
	 * 可行性报告提交
	 * @param taskAssgineeDto
	 * @param businessDto
	 * @param approveOpninion 
	 * @throws Exception
	 */
	void submitAnalysis(TaskAssgineeDto taskAssgineeDto, BusinessDto businessDto, TApproveOpninion approveOpninion) throws Exception;

	List<TApplyData> queryAllDataApply(TApplyData applyData, Page page);
}