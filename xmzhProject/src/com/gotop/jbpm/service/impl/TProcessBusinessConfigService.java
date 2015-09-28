package com.gotop.jbpm.service.impl;

import com.gotop.jbpm.dao.ITProcessBusinessConfigDAO;
import com.gotop.jbpm.model.TProcessBusinessConfig;
import com.gotop.jbpm.model.TProcessBusinessConfigExample;
import com.gotop.jbpm.service.ITProcessBusinessConfigService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TProcessBusinessConfigService implements ITProcessBusinessConfigService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TProcessBusinessConfigService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITProcessBusinessConfigDAO tProcessBusinessConfigDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settProcessBusinessConfigDAO(ITProcessBusinessConfigDAO tProcessBusinessConfigDAO) throws Exception {
        this.tProcessBusinessConfigDAO = tProcessBusinessConfigDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITProcessBusinessConfigDAO gettProcessBusinessConfigDAO() throws Exception {
        return this.tProcessBusinessConfigDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TProcessBusinessConfigExample example = new TProcessBusinessConfigExample();
        TProcessBusinessConfigExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TProcessBusinessConfig record = new TProcessBusinessConfig();
        List list = tProcessBusinessConfigDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TProcessBusinessConfig obj) throws Exception {
        this.tProcessBusinessConfigDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TProcessBusinessConfig obj) throws Exception {
        this.tProcessBusinessConfigDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TProcessBusinessConfig obj) throws Exception {
        java.lang.Long key = obj.getId();
        this.tProcessBusinessConfigDAO.deleteByPrimaryKey(key);
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
            	TProcessBusinessConfig tObject = (TProcessBusinessConfig)abs.get(i);
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
            	TProcessBusinessConfig tObject = (TProcessBusinessConfig)abs.get(i);
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
            	TProcessBusinessConfig tObject = (TProcessBusinessConfig)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tProcessBusinessConfigDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tProcessBusinessConfigDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TProcessBusinessConfigExample example = new TProcessBusinessConfigExample();
        TProcessBusinessConfigExample.Criteria criteria = example.createCriteria();
        List list = tProcessBusinessConfigDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tProcessBusinessConfigDAO.selectByDynamic(map,page);
        return list;
    }
}