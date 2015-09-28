package com.gotop.opinion.service;

import com.gotop.opinion.dao.ITDefaultOpinionDAO;
import com.gotop.opinion.model.TDefaultOpinion;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;

public interface ITDefaultOpinionService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settDefaultOpinionDAO(ITDefaultOpinionDAO tDefaultOpinionDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITDefaultOpinionDAO gettDefaultOpinionDAO() throws Exception;

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    List queryDataGrid(HashMap map, Page page) throws Exception;

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    void update(TDefaultOpinion obj) throws Exception;

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    void insert(TDefaultOpinion obj) throws Exception;

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    void delete(TDefaultOpinion obj) throws Exception;

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
    List queryAllDataList(Long empid) throws Exception;

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    List queryPageDataList(HashMap map, Page page) throws Exception;

    /**
     * 根据登录人id获取默认意见
     * @param empid
     * @return
     * @throws Exception
     */
	List<TDefaultOpinion> queryDefaultOps(Long empid) throws Exception;

	List<TDefaultOpinion> queryDefaultOpsForshow(Long empid) throws Exception;
}