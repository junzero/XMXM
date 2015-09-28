package com.gotop.jbpm.dao;

import com.gotop.jbpm.dto.ProcessDeployDto;
import com.gotop.jbpm.model.TProcessConfig;
import com.gotop.jbpm.model.TProcessConfigExample;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ITProcessConfigDAO {
    /**
     * 插入一条新数据.
     * @abatorgenerated
     */
	Long insert(TProcessConfig record);

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    int updateByPrimaryKey(TProcessConfig record);

    /**
     * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(TProcessConfig record);

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    List selectByExample(TProcessConfigExample example);

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByMapAndPage(HashMap example);

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByExampleAndPage(TProcessConfig record, TProcessConfigExample example, Page page);

    /**
     * 根据查询模板的查询结果扩展一个实例
     * @abatorgenerated
     * @param example 条件
     */
    TProcessConfig expandEntityByTemplate(TProcessConfig example);

    /**
     * 根据Bean数据模板查询条件查询所有记录
     * @abatorgenerated
     * @param example 条件
     */
    List queryEntitiesByTemplate(TProcessConfig example);

    /**
     * 根据Bean数据模板分页查询
     * @abatorgenerated
     * @param example 条件
     * @param page 分页信息
     */
    List queryEntitiesByTemplateWithPage(TProcessConfig example, Page page);

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    TProcessConfig selectByPrimaryKey(Long id);

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    int deleteByExample(TProcessConfigExample example);

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param example 条件
     */
    int deleteByTemplate(TProcessConfig example);

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    int countByExample(TProcessConfigExample example);

    /**
     * 通过查询Bean数据模板，统计总数
     * @abatorgenerated
     * @param example 条件
     */
    int countByTemplate(TProcessConfig example);

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    int updateByExampleSelective(TProcessConfig record, TProcessConfigExample example);

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    int updateByExample(TProcessConfig record, TProcessConfigExample example);

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param record 值
     * @param example 条件
     */
    int updateEntityByTemplate(TProcessConfig record, TProcessConfig example);

    /**
     * 分页查询信息
     * @abatorgenerated
     */
    List selectByDynamic(HashMap map, Page page) throws Exception;

    /**
     * 批量提交开始
     * @abatorgenerated
     */
    void startBatch() throws Exception;

    /**
     * 批量提交
     * @abatorgenerated
     */
    void executeBatch() throws Exception;

	List<TProcessConfig> queryMyProcessList(Map<String, Object> map,Page page);

	/**
	 * 发布流程列表
	 * @param page 
	 * @param map 
	 * @return
	 */
	List<TProcessConfig> queryProcessConfigList(Map<String, Object> map, Page page);

	TProcessConfig getProcessConfigByDefinitionId(Map<String, Object> map);
	
	
	public List<TProcessConfig> queryProcessDefinitionId(String businessType)throws Exception;
}