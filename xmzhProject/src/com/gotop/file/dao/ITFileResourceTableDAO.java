
package com.gotop.file.dao;

import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.model.TFileResourceTableExample;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ITFileResourceTableDAO {
    /**
     * 插入一条新数据.
     * @abatorgenerated
     */
    String insert(TFileResourceTable record);

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    int updateByPrimaryKey(TFileResourceTable record);

    /**
     * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(TFileResourceTable record);

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    List selectByExample(TFileResourceTableExample example);

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByMapAndPage(HashMap example);

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByExampleAndPage(TFileResourceTable record, TFileResourceTableExample example, Page page);

    /**
     * 根据查询模板的查询结果扩展一个实例
     * @abatorgenerated
     * @param example 条件
     */
    TFileResourceTable expandEntityByTemplate(TFileResourceTable example);

    /**
     * 根据Bean数据模板查询条件查询所有记录
     * @abatorgenerated
     * @param example 条件
     */
    List queryEntitiesByTemplate(TFileResourceTable example);

    /**
     * 根据Bean数据模板分页查询
     * @abatorgenerated
     * @param example 条件
     * @param page 分页信息
     */
    List queryEntitiesByTemplateWithPage(TFileResourceTable example, Page page);

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    TFileResourceTable selectByPrimaryKey(Long fileId);

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    int deleteByExample(TFileResourceTableExample example);

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param example 条件
     */
    int deleteByTemplate(TFileResourceTable example);

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    int deleteByPrimaryKey(Long fileId);

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    int countByExample(TFileResourceTableExample example);

    /**
     * 通过查询Bean数据模板，统计总数
     * @abatorgenerated
     * @param example 条件
     */
    int countByTemplate(TFileResourceTable example);

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    int updateByExampleSelective(TFileResourceTable record, TFileResourceTableExample example);

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    int updateByExample(TFileResourceTable record, TFileResourceTableExample example);

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param record 值
     * @param example 条件
     */
    int updateEntityByTemplate(TFileResourceTable record, TFileResourceTable example);

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
     * 获取文件信息列表
     * @return
     */
	List<TFileResourceTable> queryProcessFileLists();
	
	/**
	 * 
	 * @param fileId
	 * @return
	 */
	TFileResourceTable getFileResource(String fileId);
	
	/**
	 * 
	 * @param id
	 * @param resourceType
	 * @return
	 */
	List<TFileResourceTable> queryFileByIdandType(Long id, String resourceType,String fileType);
	
	/**
	 * 
	 * @param file
	 * @throws Exception
	 */
	void deleteFileToFileName(TFileResourceTable file)throws Exception;
	
	/**
	 * 保存文件
	 * @param tfile
	 * @throws Exception
	 */
	public void insertTFileResource(TFileResourceTable tfile) throws Exception;
}