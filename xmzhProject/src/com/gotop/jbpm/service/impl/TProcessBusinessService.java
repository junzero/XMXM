package com.gotop.jbpm.service.impl;

import com.gotop.jbpm.dao.ITProcessBusinessDAO;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.model.TProcessBusinessExample;
import com.gotop.jbpm.service.ITProcessBusinessService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TProcessBusinessService implements ITProcessBusinessService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TProcessBusinessService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITProcessBusinessDAO tProcessBusinessDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settProcessBusinessDAO(ITProcessBusinessDAO tProcessBusinessDAO) throws Exception {
        this.tProcessBusinessDAO = tProcessBusinessDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITProcessBusinessDAO gettProcessBusinessDAO() throws Exception {
        return this.tProcessBusinessDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TProcessBusinessExample example = new TProcessBusinessExample();
        TProcessBusinessExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TProcessBusiness record = new TProcessBusiness();
        List list = tProcessBusinessDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TProcessBusiness obj) throws Exception {
        this.tProcessBusinessDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TProcessBusiness obj) throws Exception {
        this.tProcessBusinessDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TProcessBusiness obj) throws Exception {
        java.lang.Long key = obj.getId();
        this.tProcessBusinessDAO.deleteByPrimaryKey(key);
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
            	TProcessBusiness tObject = (TProcessBusiness)abs.get(i);
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
            	TProcessBusiness tObject = (TProcessBusiness)abs.get(i);
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
            	TProcessBusiness tObject = (TProcessBusiness)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tProcessBusinessDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tProcessBusinessDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TProcessBusinessExample example = new TProcessBusinessExample();
        TProcessBusinessExample.Criteria criteria = example.createCriteria();
        List list = tProcessBusinessDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tProcessBusinessDAO.selectByDynamic(map,page);
        return list;
    }

}