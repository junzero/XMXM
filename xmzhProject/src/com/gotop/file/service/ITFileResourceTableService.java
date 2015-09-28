
package com.gotop.file.service;

import com.gotop.file.dao.ITFileResourceTableDAO;
import com.gotop.file.model.FileBean;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.tyjg.datadictionary.dao.IDictEntryDao;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public interface ITFileResourceTableService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settFileResourceTableDAO(ITFileResourceTableDAO tFileResourceTableDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITFileResourceTableDAO gettFileResourceTableDAO() throws Exception;
    
    public IDictEntryDao getDictEntryDao()throws Exception;
    
    public void setDictEntryDao(IDictEntryDao dictEntryDao)throws Exception;

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    List queryDataGrid(HashMap map, Page page) throws Exception;

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    void update(TFileResourceTable obj) throws Exception;

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    String insert(TFileResourceTable obj) throws Exception;

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    void delete(TFileResourceTable obj) throws Exception;

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
     * 查询流程配置文件列表
     * @return
     */
	List<TFileResourceTable> queryProcessFileLists();
	
	/**
	 * 根据主键获得文件信息
	 * @param fileId
	 * @return
	 */
	public TFileResourceTable getFileResource(String fileId);
	
	/**
	 * 根据主键和类型获得文件信息
	 * @param id
	 * @param resourceType
	 * @return
	 */
	List<TFileResourceTable> queryFileByIdandType(Long id, String resourceType,String fileType);
	
	/**
	 * 删除文件信息
	 * @param file
	 * @throws Exception
	 */
	void deleteFileToFileName(TFileResourceTable file)throws Exception;
	
	public String getFileUpload(File upload,String uploadFileName) throws FileNotFoundException;
	/**
	 * 文件保存(单个)
	 * @param tfile
	 * @param file
	 * @param fileName
	 * @param muo
	 * @return
	 * @throws Exception
	 */
	public String fileUpload(FileBean tfile,File file,String filesFileName,MUOUserSession muo)throws Exception;
	/**
	 * 文件保存(多个)
	 * @param tfile
	 * @param file
	 * @param fileName
	 * @param muo
	 * @return
	 * @throws Exception
	 */
	public String fileUploads(FileBean tfile,File[] file,String[] filesFileName,MUOUserSession muo)throws Exception;
}