package com.gotop.tyjg.stable.service.impl;

import com.gotop.tyjg.stable.dao.IAtFileuploadDAO;
import com.gotop.tyjg.stable.model.AtFileupload;
import com.gotop.tyjg.stable.model.AtFileuploadExample;
import com.gotop.tyjg.stable.service.IAtFileuploadService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;


public class AtFileuploadService implements IAtFileuploadService {
    /**
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(AtFileuploadService.class);
	/**
	 * 通过spring注入的DAO对象.
	 * @abatorgenerated
	 */
	protected IAtFileuploadDAO atFileuploadDAO;

	/**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setAtFileuploadDAO(IAtFileuploadDAO atFileuploadDAO) throws Exception {
        this.atFileuploadDAO = atFileuploadDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IAtFileuploadDAO getAtFileuploadDAO() throws Exception {
        return this.atFileuploadDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        AtFileuploadExample example = new AtFileuploadExample();
        AtFileuploadExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        AtFileupload record = new AtFileupload();
        List list = atFileuploadDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(AtFileupload obj) throws Exception {
        this.atFileuploadDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(AtFileupload obj) throws Exception {
        this.atFileuploadDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(AtFileupload obj) throws Exception {
        java.lang.String key = obj.getFileId();
        this.atFileuploadDAO.deleteByPrimaryKey(key);
    }

    /**
     * 批量更新数据
     * @abatorgenerated
     */
    public void updateBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	AtFileupload tObject = (AtFileupload)abs.get(i);
            	this.update(tObject);
        }
    }

    /**
     * 批量插入数据
     * @abatorgenerated
     */
    public void insertBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	AtFileupload tObject = (AtFileupload)abs.get(i);
            	this.insert(tObject);
        }
    }

    /**
     * 批量删除数据
     * @abatorgenerated
     */
    public void deleteBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	AtFileupload tObject = (AtFileupload)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.atFileuploadDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.atFileuploadDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        AtFileuploadExample example = new AtFileuploadExample();
        AtFileuploadExample.Criteria criteria = example.createCriteria();
        List list = atFileuploadDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = atFileuploadDAO.selectByDynamic(map,page);
        return list;
    }
}