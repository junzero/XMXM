package com.gotop.jbpm.service.impl;

import com.gotop.jbpm.dao.IJbpm4TaskDAO;
import com.gotop.jbpm.model.Jbpm4Task;
import com.gotop.jbpm.model.Jbpm4TaskExample;
import com.gotop.jbpm.service.IJbpm4TaskService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class Jbpm4TaskService implements IJbpm4TaskService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(Jbpm4TaskService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected IJbpm4TaskDAO jbpm4TaskDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setJbpm4TaskDAO(IJbpm4TaskDAO jbpm4TaskDAO) throws Exception {
        this.jbpm4TaskDAO = jbpm4TaskDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IJbpm4TaskDAO getJbpm4TaskDAO() throws Exception {
        return this.jbpm4TaskDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        Jbpm4TaskExample example = new Jbpm4TaskExample();
        Jbpm4TaskExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        Jbpm4Task record = new Jbpm4Task();
        List list = jbpm4TaskDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(Jbpm4Task obj) throws Exception {
        this.jbpm4TaskDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(Jbpm4Task obj) throws Exception {
        this.jbpm4TaskDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(Jbpm4Task obj) throws Exception {
        java.math.BigDecimal key = obj.getDbid();
        this.jbpm4TaskDAO.deleteByPrimaryKey(key);
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
            	Jbpm4Task tObject = (Jbpm4Task)abs.get(i);
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
            	Jbpm4Task tObject = (Jbpm4Task)abs.get(i);
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
            	Jbpm4Task tObject = (Jbpm4Task)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.jbpm4TaskDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.jbpm4TaskDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        Jbpm4TaskExample example = new Jbpm4TaskExample();
        Jbpm4TaskExample.Criteria criteria = example.createCriteria();
        List list = jbpm4TaskDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = jbpm4TaskDAO.selectByDynamic(map,page);
        return list;
    }
}