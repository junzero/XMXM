package com.gotop.dataApply.dao;

import com.gotop.dataApply.model.TApplyData;
import com.gotop.dataApply.model.TApplyDataExample;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ITApplyDataDAO {
    /**
     * 插入一条新数据.
     * @return 
     * @abatorgenerated
     */
    Long insert(TApplyData record);

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    int updateByPrimaryKey(TApplyData record);

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    TApplyData selectByPrimaryKey(Long daId);

	/**
	 * 根据流程实例获取数据申请表
	 * @param executionId
	 * @return
	 * @throws Exception
	 */
	TApplyData getLeaveByFlowId(String executionId) throws Exception;
	
	/**
	 * 获得包含提取可行性分析人的数据申请表
	 * @param businessKey
	 * @return
	 * @throws Exception
	 */
	TApplyData getApplyDataAnalyst(Long businessKey) throws Exception;

	/**
	 * 获得包含所有分析人的数据申请表
	 * @param businessKey
	 * @return
	 * @throws Exception
	 */
	TApplyData getApplyAnalyst(Long businessKey) throws Exception;

	/**
	 * 获得包含风险可行性分析人的数据申请表
	 * @param businessKey
	 * @return
	 * @throws Exception
	 */
	TApplyData getApplyRiskAnalyst(Long businessKey) throws Exception;

	List<TApplyData> queryAllDataApply(TApplyData applyData, Page page);
}