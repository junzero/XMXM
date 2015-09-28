package com.gotop.dataUser.service.impl;

import com.gotop.dataUser.dao.ITRangeUserDAO;
import com.gotop.dataUser.model.TRangeUser;
import com.gotop.dataUser.model.TRangeUserExample;
import com.gotop.dataUser.service.ITRangeUserService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TRangeUserService implements ITRangeUserService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TRangeUserService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITRangeUserDAO tRangeUserDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settRangeUserDAO(ITRangeUserDAO tRangeUserDAO) throws Exception {
        this.tRangeUserDAO = tRangeUserDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITRangeUserDAO gettRangeUserDAO() throws Exception {
        return this.tRangeUserDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TRangeUserExample example = new TRangeUserExample();
        TRangeUserExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TRangeUser record = new TRangeUser();
        List list = tRangeUserDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TRangeUser obj) throws Exception {
        this.tRangeUserDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TRangeUser obj) throws Exception {
        this.tRangeUserDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TRangeUser obj) throws Exception {
        java.lang.Long key = obj.getUserId();
        this.tRangeUserDAO.deleteByPrimaryKey(key);
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
            	TRangeUser tObject = (TRangeUser)abs.get(i);
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
            	TRangeUser tObject = (TRangeUser)abs.get(i);
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
            	TRangeUser tObject = (TRangeUser)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tRangeUserDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tRangeUserDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TRangeUserExample example = new TRangeUserExample();
        TRangeUserExample.Criteria criteria = example.createCriteria();
        List list = tRangeUserDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tRangeUserDAO.selectByDynamic(map,page);
        return list;
    }
}