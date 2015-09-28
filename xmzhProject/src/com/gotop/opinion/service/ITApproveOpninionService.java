package com.gotop.opinion.service;

import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.OpninionBean;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;

public interface ITApproveOpninionService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settApproveOpninionDAO(ITApproveOpninionDAO tApproveOpninionDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITApproveOpninionDAO gettApproveOpninionDAO() throws Exception;

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    List queryDataGrid(HashMap map, Page page) throws Exception;

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    void update(TApproveOpninion obj) throws Exception;

    /**
     * 插入单条记录
     * @param map 
     * @param taskId 
     * @return 
     * @abatorgenerated
     */
    Long insert(TApproveOpninion obj, HashMap<String, Object> map, String taskId) throws Exception;

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    void delete(TApproveOpninion obj) throws Exception;

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
    
    /**
     * 获取审核列表
     * @param hm
     * @param page
     * @return
     */
	List queryOpninions(HashMap hm, Page page);
	
	/**
	 * 插入意见
	 * @param bean
	 * @param muo
	 * @throws Exception
	 */
    public void insertOpninionInfo(OpninionBean bean ,MUOUserSession muo)throws Exception;

    /**
     * 插入结束流程意见
     * @param opninion
     */
	void insertDeleteProcessOpninions(TApproveOpninion opninion);

	String receiveResourceId(String resourceFlow, String resourceType);

}