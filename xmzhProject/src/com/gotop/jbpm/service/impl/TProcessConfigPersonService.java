package com.gotop.jbpm.service.impl;

import com.gotop.jbpm.dao.ITProcessConfigPersonDAO;
import com.gotop.jbpm.model.TProcessConfigPerson;
import com.gotop.jbpm.model.TProcessConfigPersonExample;
import com.gotop.jbpm.service.ITProcessConfigPersonService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TProcessConfigPersonService implements ITProcessConfigPersonService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TProcessConfigPersonService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITProcessConfigPersonDAO tProcessConfigPersonDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settProcessConfigPersonDAO(ITProcessConfigPersonDAO tProcessConfigPersonDAO) throws Exception {
        this.tProcessConfigPersonDAO = tProcessConfigPersonDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITProcessConfigPersonDAO gettProcessConfigPersonDAO() throws Exception {
        return this.tProcessConfigPersonDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TProcessConfigPersonExample example = new TProcessConfigPersonExample();
        TProcessConfigPersonExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TProcessConfigPerson record = new TProcessConfigPerson();
        List list = tProcessConfigPersonDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TProcessConfigPerson obj) throws Exception {
        this.tProcessConfigPersonDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TProcessConfigPerson obj) throws Exception {
        this.tProcessConfigPersonDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TProcessConfigPerson obj) throws Exception {
        java.lang.Long key = obj.getId();
        this.tProcessConfigPersonDAO.deleteByPrimaryKey(key);
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
            	TProcessConfigPerson tObject = (TProcessConfigPerson)abs.get(i);
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
            	TProcessConfigPerson tObject = (TProcessConfigPerson)abs.get(i);
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
            	TProcessConfigPerson tObject = (TProcessConfigPerson)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tProcessConfigPersonDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tProcessConfigPersonDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TProcessConfigPersonExample example = new TProcessConfigPersonExample();
        TProcessConfigPersonExample.Criteria criteria = example.createCriteria();
        List list = tProcessConfigPersonDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tProcessConfigPersonDAO.selectByDynamic(map,page);
        return list;
    }

	@Override
	public List<TProcessConfigPerson> getProcessConfigPersons(Long id) {
		 List<TProcessConfigPerson> list = tProcessConfigPersonDAO.getProcessConfigPersons(id);
	        return list;
	}

}