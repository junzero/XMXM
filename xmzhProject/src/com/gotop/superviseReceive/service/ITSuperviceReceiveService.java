package com.gotop.superviseReceive.service;

import com.gotop.superviseReceive.dao.ITSuperviceReceiveDAO;
import com.gotop.superviseReceive.model.TSuperviceReceive;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;

public interface ITSuperviceReceiveService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settSuperviceReceiveDAO(ITSuperviceReceiveDAO tSuperviceReceiveDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITSuperviceReceiveDAO gettSuperviceReceiveDAO() throws Exception;

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    List queryDataGrid(HashMap map, Page page) throws Exception;

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    void update(TSuperviceReceive obj) throws Exception;

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    void insert(TSuperviceReceive obj) throws Exception;

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    void delete(TSuperviceReceive obj) throws Exception;

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
}