package com.gotop.jbpm.service;

import com.gotop.jbpm.dao.ITProcessTaskAssigneeDAO;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.model.TProcessTaskAssignee;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ITProcessTaskAssigneeService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settProcessTaskAssigneeDAO(ITProcessTaskAssigneeDAO tProcessTaskAssigneeDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITProcessTaskAssigneeDAO gettProcessTaskAssigneeDAO() throws Exception;

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    List queryDataGrid(HashMap map, Page page) throws Exception;

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    void update(TProcessTaskAssignee obj) throws Exception;

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    Long insert(TProcessTaskAssignee obj) throws Exception;

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    void delete(TProcessTaskAssignee obj) throws Exception;

    /**
     * 批量更新数据
     * @abatorgenerated
     */
    void updateBatch(List abs) throws Exception;

    /**
     * 批量插入数据
     * @abatorgenerated
     */
    void insertBatch(List abs) throws Exception;

    /**
     * 批量删除数据
     * @abatorgenerated
     */
    void deleteBatch(List abs) throws Exception;

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    void updateDataGrid(HashMap hmp) throws Exception;

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    List queryAllDataList(HashMap map) throws Exception;

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    List queryPageDataList(HashMap map, Page page) throws Exception;

	List<TProcessTaskAssignee> queryMyToDoTasksList(String relationids, TProcessTaskAssignee taskAssignee,Page page);

	List<TProcessTaskAssignee> queryMyCompletedTasksList(String relationids,String empId,
			TProcessTaskAssignee taskAssignee, Page page);

	List<TProcessTaskAssignee> queryMyDraftsList(String empId,
			TProcessTaskAssignee taskAssignee, Page page);

	List<TProcessTaskAssignee> querySuperviseList(HashMap<String, Object> map,
			String[] posicode, TProcessTaskAssignee taskAssignee, Page page);
	
	  /**
     * 我发起的流程
     * @param empId
     * @param page
     * @return
     */
	List<TProcessTaskAssignee> myStartProcessList(Long empId,TProcessTaskAssignee taskAssignee, Page page);
    /**
     * 
     *
     * 首页督办提醒,点击更多时使用,更多时,显示字段不一样,需要关联更多的表,为了效率,单独现在写一个过程
     *
     * @param map
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
    public List<TProcessTaskAssignee>querySuperviseRemMore(HashMap<String, Object> map,String[] posicode,TProcessTaskAssignee taskAssignee, Page page) throws Exception;

	List<TProcessTaskAssignee> downexl(String relationids, String empId,
			TProcessTaskAssignee taskAssignee) throws Exception;

	/**
	 * 根据节点执行对象主键查询对应的TProcessTaskAssignee信息
	 * @param id
	 * @param string 
	 * @return
	 */
	List<TProcessTaskAssignee> selectByTaskExeConfigId(Long id, String string);
    
}