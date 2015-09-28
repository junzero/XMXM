package com.gotop.tyjg.stable.service.impl;

import com.gotop.tyjg.stable.dao.IOmGrouprangeDAO;
import com.gotop.tyjg.stable.model.OmGrouprangeExample;
import com.gotop.tyjg.stable.model.OmGrouprangeKey;
import com.gotop.tyjg.stable.service.IOmGrouprangeService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;


public class OmGrouprangeService implements IOmGrouprangeService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(OmGrouprangeService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected IOmGrouprangeDAO omGrouprangeDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setOmGrouprangeDAO(IOmGrouprangeDAO omGrouprangeDAO) throws Exception {
        this.omGrouprangeDAO = omGrouprangeDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IOmGrouprangeDAO getOmGrouprangeDAO() throws Exception {
        return this.omGrouprangeDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        OmGrouprangeExample example = new OmGrouprangeExample();
        OmGrouprangeExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        OmGrouprangeKey record = new OmGrouprangeKey();
        List list = omGrouprangeDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(OmGrouprangeKey obj) throws Exception {
//        this.omGrouprangeDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(OmGrouprangeKey obj) throws Exception {
        this.omGrouprangeDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(OmGrouprangeKey obj) throws Exception {
    	OmGrouprangeKey key = new OmGrouprangeKey();
        key.setFlag(obj.getFlag());
        key.setGrangeid(obj.getGrangeid());
        key.setGroupid(obj.getGroupid());
        this.omGrouprangeDAO.deleteByPrimaryKey(key);
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
        	OmGrouprangeKey tObject = (OmGrouprangeKey)abs.get(i);
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
        	OmGrouprangeKey tObject = (OmGrouprangeKey)abs.get(i);
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
        	OmGrouprangeKey tObject = (OmGrouprangeKey)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.omGrouprangeDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.omGrouprangeDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        OmGrouprangeExample example = new OmGrouprangeExample();
        OmGrouprangeExample.Criteria criteria = example.createCriteria();
        List list = omGrouprangeDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = omGrouprangeDAO.selectByDynamic(map,page);
        return list;
    }
}