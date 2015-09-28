package com.gotop.jbpm.service;

import com.gotop.jbpm.dao.ITProcessConfigPersonDAO;
import com.gotop.jbpm.model.TProcessConfigPerson;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;

public interface ITProcessConfigPersonService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settProcessConfigPersonDAO(ITProcessConfigPersonDAO tProcessConfigPersonDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITProcessConfigPersonDAO gettProcessConfigPersonDAO() throws Exception;

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    void insert(TProcessConfigPerson obj) throws Exception;

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    void delete(TProcessConfigPerson obj) throws Exception;

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

	List<TProcessConfigPerson> getProcessConfigPersons(Long id);

}