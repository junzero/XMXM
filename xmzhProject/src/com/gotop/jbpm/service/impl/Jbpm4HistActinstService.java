package com.gotop.jbpm.service.impl;

import com.gotop.jbpm.dao.IJbpm4HistActinstDAO;
import com.gotop.jbpm.model.Jbpm4HistActinst;
import com.gotop.jbpm.model.Jbpm4HistActinstExample;
import com.gotop.jbpm.service.IJbpm4HistActinstService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class Jbpm4HistActinstService implements IJbpm4HistActinstService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(Jbpm4HistActinstService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected IJbpm4HistActinstDAO jbpm4HistActinstDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setJbpm4HistActinstDAO(IJbpm4HistActinstDAO jbpm4HistActinstDAO) throws Exception {
        this.jbpm4HistActinstDAO = jbpm4HistActinstDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IJbpm4HistActinstDAO getJbpm4HistActinstDAO() throws Exception {
        return this.jbpm4HistActinstDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        Jbpm4HistActinstExample example = new Jbpm4HistActinstExample();
        Jbpm4HistActinstExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        Jbpm4HistActinst record = new Jbpm4HistActinst();
        List list = jbpm4HistActinstDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(Jbpm4HistActinst obj) throws Exception {
        this.jbpm4HistActinstDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(Jbpm4HistActinst obj) throws Exception {
        this.jbpm4HistActinstDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(Jbpm4HistActinst obj) throws Exception {
        java.math.BigDecimal key = obj.getDbid();
        this.jbpm4HistActinstDAO.deleteByPrimaryKey(key);
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
            	Jbpm4HistActinst tObject = (Jbpm4HistActinst)abs.get(i);
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
            	Jbpm4HistActinst tObject = (Jbpm4HistActinst)abs.get(i);
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
            	Jbpm4HistActinst tObject = (Jbpm4HistActinst)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.jbpm4HistActinstDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.jbpm4HistActinstDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        Jbpm4HistActinstExample example = new Jbpm4HistActinstExample();
        Jbpm4HistActinstExample.Criteria criteria = example.createCriteria();
        List list = jbpm4HistActinstDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = jbpm4HistActinstDAO.selectByDynamic(map,page);
        return list;
    }
}