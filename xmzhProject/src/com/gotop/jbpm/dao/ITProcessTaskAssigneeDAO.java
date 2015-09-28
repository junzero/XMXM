package com.gotop.jbpm.dao;

import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.model.TProcessTaskAssignee;
import com.gotop.jbpm.model.TProcessTaskAssigneeExample;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ITProcessTaskAssigneeDAO {
    /**
     * 插入一条新数据.
     * @abatorgenerated
     */
	Long insert(TProcessTaskAssignee record);

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    int updateByPrimaryKey(TProcessTaskAssignee record);

    /**
     * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(TProcessTaskAssignee record);

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    List selectByExample(TProcessTaskAssigneeExample example);

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByMapAndPage(HashMap example);

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByExampleAndPage(TProcessTaskAssignee record, TProcessTaskAssigneeExample example, Page page);

    /**
     * 根据查询模板的查询结果扩展一个实例
     * @abatorgenerated
     * @param example 条件
     */
    TProcessTaskAssignee expandEntityByTemplate(TProcessTaskAssignee example);

    /**
     * 根据Bean数据模板查询条件查询所有记录
     * @abatorgenerated
     * @param example 条件
     */
    List queryEntitiesByTemplate(TProcessTaskAssignee example);

    /**
     * 根据Bean数据模板分页查询
     * @abatorgenerated
     * @param example 条件
     * @param page 分页信息
     */
    List queryEntitiesByTemplateWithPage(TProcessTaskAssignee example, Page page);

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    TProcessTaskAssignee selectByPrimaryKey(Long id);

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    int deleteByExample(TProcessTaskAssigneeExample example);

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param example 条件
     */
    int deleteByTemplate(TProcessTaskAssignee example);

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    int countByExample(TProcessTaskAssigneeExample example);

    /**
     * 通过查询Bean数据模板，统计总数
     * @abatorgenerated
     * @param example 条件
     */
    int countByTemplate(TProcessTaskAssignee example);

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    int updateByExampleSelective(TProcessTaskAssignee record, TProcessTaskAssigneeExample example);

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    int updateByExample(TProcessTaskAssignee record, TProcessTaskAssigneeExample example);

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param record 值
     * @param example 条件
     */
    int updateEntityByTemplate(TProcessTaskAssignee record, TProcessTaskAssignee example);

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

	List<TProcessTaskAssignee> queryMyToDoTasksList(Map<String, Object> map,Page page);

	List<TProcessTaskAssignee> queryMyCompletedTasksList(
			Map<String, Object> map, Page page);

	List<TProcessTaskAssignee> queryMyDraftsList(Map<String, Object> map,
			Page page);

	List<TProcessTaskAssignee> querySuperviseList(Map<String, Object> map,
			Page page);
    /**
     * 
     *
     * 首页督办提醒,点击更多时使用,更多时,显示字段不一样,需要关联更多的表,为了效率,单独现在写一个过程
     *
     * @param map,包括empid,orgcode
     * @param posicode
     * @param taskAssignee
     * @param page
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-9-11    黄开忠    新建
     * </pre>
     */
	   List<TProcessTaskAssignee> querySuperviseRemMore(Map<String, Object> map,Page page) throws Exception;
	List<TProcessTaskAssignee> myStartProcessList(Map<String, Object> map, Page page);

	List<TProcessTaskAssignee> downexl(Map<String, Object> map) throws Exception;

	/**
	 * 根据节点执行对象主键查询对应的TProcessTaskAssignee信息
	 * @param id
	 * @return
	 */
	List<TProcessTaskAssignee> selectByTaskExeConfigId(Map<String, Object> map);
}