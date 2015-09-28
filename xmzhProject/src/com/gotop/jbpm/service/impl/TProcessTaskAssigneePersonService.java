package com.gotop.jbpm.service.impl;

import com.gotop.jbpm.dao.ITProcessTaskAssigneePersonDAO;
import com.gotop.jbpm.model.TProcessTaskAssigneePerson;
import com.gotop.jbpm.model.TProcessTaskAssigneePersonExample;
import com.gotop.jbpm.service.ITProcessTaskAssigneePersonService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TProcessTaskAssigneePersonService implements ITProcessTaskAssigneePersonService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TProcessTaskAssigneePersonService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITProcessTaskAssigneePersonDAO tProcessTaskAssigneePersonDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settProcessTaskAssigneePersonDAO(ITProcessTaskAssigneePersonDAO tProcessTaskAssigneePersonDAO) throws Exception {
        this.tProcessTaskAssigneePersonDAO = tProcessTaskAssigneePersonDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITProcessTaskAssigneePersonDAO gettProcessTaskAssigneePersonDAO() throws Exception {
        return this.tProcessTaskAssigneePersonDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TProcessTaskAssigneePersonExample example = new TProcessTaskAssigneePersonExample();
        TProcessTaskAssigneePersonExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TProcessTaskAssigneePerson record = new TProcessTaskAssigneePerson();
        List list = tProcessTaskAssigneePersonDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TProcessTaskAssigneePerson obj) throws Exception {
        this.tProcessTaskAssigneePersonDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TProcessTaskAssigneePerson obj) throws Exception {
        this.tProcessTaskAssigneePersonDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TProcessTaskAssigneePerson obj) throws Exception {
        java.lang.Long key = obj.getId();
        this.tProcessTaskAssigneePersonDAO.deleteByPrimaryKey(key);
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
            	TProcessTaskAssigneePerson tObject = (TProcessTaskAssigneePerson)abs.get(i);
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
            	TProcessTaskAssigneePerson tObject = (TProcessTaskAssigneePerson)abs.get(i);
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
            	TProcessTaskAssigneePerson tObject = (TProcessTaskAssigneePerson)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tProcessTaskAssigneePersonDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tProcessTaskAssigneePersonDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TProcessTaskAssigneePersonExample example = new TProcessTaskAssigneePersonExample();
        TProcessTaskAssigneePersonExample.Criteria criteria = example.createCriteria();
        List list = tProcessTaskAssigneePersonDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tProcessTaskAssigneePersonDAO.selectByDynamic(map,page);
        return list;
    }
}