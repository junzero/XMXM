package com.gotop.mettingRec.service.impl;

import com.gotop.mettingRec.dao.ITMettingReceiveDAO;
import com.gotop.mettingRec.model.TMettingReceive;
import com.gotop.mettingRec.model.TMettingReceiveExample;
import com.gotop.mettingRec.service.ITMettingReceiveService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TMettingReceiveService implements ITMettingReceiveService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TMettingReceiveService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITMettingReceiveDAO tMettingReceiveDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settMettingReceiveDAO(ITMettingReceiveDAO tMettingReceiveDAO) throws Exception {
        this.tMettingReceiveDAO = tMettingReceiveDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITMettingReceiveDAO gettMettingReceiveDAO() throws Exception {
        return this.tMettingReceiveDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TMettingReceiveExample example = new TMettingReceiveExample();
        TMettingReceiveExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TMettingReceive record = new TMettingReceive();
        List list = tMettingReceiveDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TMettingReceive obj) throws Exception {
        this.tMettingReceiveDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TMettingReceive obj) throws Exception {
        this.tMettingReceiveDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TMettingReceive obj) throws Exception {
        java.lang.Long key = obj.getRecId();
        this.tMettingReceiveDAO.deleteByPrimaryKey(key);
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
            	TMettingReceive tObject = (TMettingReceive)abs.get(i);
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
            	TMettingReceive tObject = (TMettingReceive)abs.get(i);
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
            	TMettingReceive tObject = (TMettingReceive)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tMettingReceiveDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tMettingReceiveDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TMettingReceiveExample example = new TMettingReceiveExample();
        TMettingReceiveExample.Criteria criteria = example.createCriteria();
        List list = tMettingReceiveDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tMettingReceiveDAO.selectByDynamic(map,page);
        return list;
    }
}