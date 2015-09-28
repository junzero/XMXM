package com.gotop.messageReceive.service.impl;

import com.gotop.messageReceive.dao.ITMessageReceiveDAO;
import com.gotop.messageReceive.model.TMessageReceive;
import com.gotop.messageReceive.model.TMessageReceiveExample;
import com.gotop.messageReceive.service.ITMessageReceiveService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TMessageReceiveService implements ITMessageReceiveService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TMessageReceiveService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITMessageReceiveDAO tMessageReceiveDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settMessageReceiveDAO(ITMessageReceiveDAO tMessageReceiveDAO) throws Exception {
        this.tMessageReceiveDAO = tMessageReceiveDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITMessageReceiveDAO gettMessageReceiveDAO() throws Exception {
        return this.tMessageReceiveDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TMessageReceiveExample example = new TMessageReceiveExample();
        TMessageReceiveExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TMessageReceive record = new TMessageReceive();
        List list = tMessageReceiveDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TMessageReceive obj) throws Exception {
        this.tMessageReceiveDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TMessageReceive obj) throws Exception {
        this.tMessageReceiveDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TMessageReceive obj) throws Exception {
        java.lang.Long key = obj.getRecId();
        this.tMessageReceiveDAO.deleteByPrimaryKey(key);
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
            	TMessageReceive tObject = (TMessageReceive)abs.get(i);
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
            	TMessageReceive tObject = (TMessageReceive)abs.get(i);
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
            	TMessageReceive tObject = (TMessageReceive)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tMessageReceiveDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tMessageReceiveDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TMessageReceiveExample example = new TMessageReceiveExample();
        TMessageReceiveExample.Criteria criteria = example.createCriteria();
        List list = tMessageReceiveDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tMessageReceiveDAO.selectByDynamic(map,page);
        return list;
    }
}