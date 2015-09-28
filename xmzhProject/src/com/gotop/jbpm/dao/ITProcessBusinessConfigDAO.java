package com.gotop.jbpm.dao;

import com.gotop.jbpm.model.TProcessBusinessConfig;
import com.gotop.jbpm.model.TProcessBusinessConfigExample;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ITProcessBusinessConfigDAO {
    /**
     * 插入一条新数据.
     * @abatorgenerated
     */
    void insert(TProcessBusinessConfig record);

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    int updateByPrimaryKey(TProcessBusinessConfig record);

    /**
     * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(TProcessBusinessConfig record);

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    List selectByExample(TProcessBusinessConfigExample example);

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByMapAndPage(HashMap example);

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByExampleAndPage(TProcessBusinessConfig record, TProcessBusinessConfigExample example, Page page);

    /**
     * 根据查询模板的查询结果扩展一个实例
     * @abatorgenerated
     * @param example 条件
     */
    TProcessBusinessConfig expandEntityByTemplate(TProcessBusinessConfig example);

    /**
     * 根据Bean数据模板查询条件查询所有记录
     * @abatorgenerated
     * @param example 条件
     */
    List queryEntitiesByTemplate(TProcessBusinessConfig example);

    /**
     * 根据Bean数据模板分页查询
     * @abatorgenerated
     * @param example 条件
     * @param page 分页信息
     */
    List queryEntitiesByTemplateWithPage(TProcessBusinessConfig example, Page page);

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    TProcessBusinessConfig selectByPrimaryKey(Long id);

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    int deleteByExample(TProcessBusinessConfigExample example);

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param example 条件
     */
    int deleteByTemplate(TProcessBusinessConfig example);

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    int countByExample(TProcessBusinessConfigExample example);

    /**
     * 通过查询Bean数据模板，统计总数
     * @abatorgenerated
     * @param example 条件
     */
    int countByTemplate(TProcessBusinessConfig example);

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    int updateByExampleSelective(TProcessBusinessConfig record, TProcessBusinessConfigExample example);

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    int updateByExample(TProcessBusinessConfig record, TProcessBusinessConfigExample example);

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param record 值
     * @param example 条件
     */
    int updateEntityByTemplate(TProcessBusinessConfig record, TProcessBusinessConfig example);

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
}