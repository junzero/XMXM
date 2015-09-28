package com.gotop.monthReport.dao;

import com.gotop.monthReport.model.TWorkMonthReports;
import com.gotop.monthReport.model.TWorkMonthReportsExample;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ITWorkMonthReportsDAO {
    /**
     * 插入一条新数据.
     * @return 
     * @abatorgenerated
     */
    Long insert(TWorkMonthReports record);

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    int updateByPrimaryKey(TWorkMonthReports record);

    /**
     * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(TWorkMonthReports record);

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    List selectByExample(TWorkMonthReportsExample example);

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByMapAndPage(HashMap example);

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByExampleAndPage(TWorkMonthReports record, TWorkMonthReportsExample example, Page page);

    /**
     * 根据查询模板的查询结果扩展一个实例
     * @abatorgenerated
     * @param example 条件
     */
    TWorkMonthReports expandEntityByTemplate(TWorkMonthReports example);

    /**
     * 根据Bean数据模板查询条件查询所有记录
     * @abatorgenerated
     * @param example 条件
     */
    List queryEntitiesByTemplate(TWorkMonthReports example);

    /**
     * 根据Bean数据模板分页查询
     * @abatorgenerated
     * @param example 条件
     * @param page 分页信息
     */
    List queryEntitiesByTemplateWithPage(TWorkMonthReports example, Page page);

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    TWorkMonthReports selectByPrimaryKey(Long reportId);

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    int deleteByExample(TWorkMonthReportsExample example);

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param example 条件
     */
    int deleteByTemplate(TWorkMonthReports example);

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    int deleteByPrimaryKey(Long reportId);

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    int countByExample(TWorkMonthReportsExample example);

    /**
     * 通过查询Bean数据模板，统计总数
     * @abatorgenerated
     * @param example 条件
     */
    int countByTemplate(TWorkMonthReports example);

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    int updateByExampleSelective(TWorkMonthReports record, TWorkMonthReportsExample example);

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    int updateByExample(TWorkMonthReports record, TWorkMonthReportsExample example);

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param record 值
     * @param example 条件
     */
    int updateEntityByTemplate(TWorkMonthReports record, TWorkMonthReports example);

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

    /**
     * 
     *
     * TODO 动态查询，分页查询数据并返回list.
     *
     * @param monthReports
     * @param page
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-30    yyx    新建
     * </pre>
     */
    List<TWorkMonthReports> queryMessagePublishList(
            TWorkMonthReports monthReports, Page page);

    /**
     * 
     *
     * TODO 获取月报详情.
     *
     * @param reportId
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-31    yyx    新建
     * </pre>
     */
    TWorkMonthReports queryMonthReportInfo(Long reportId);

    TWorkMonthReports getMonthReportByFlowId(String flowId);

    /**
     * 
     *
     * TODO 根据主键更改月报.
     *
     * @param monthReports
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-31    yyx    新建
     * </pre>
     */
    void update(TWorkMonthReports monthReports);
}