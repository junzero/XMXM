package com.gotop.tyjg.stable.service.impl;

import com.gotop.tyjg.stable.dao.IOmGroupDAO;
import com.gotop.tyjg.stable.model.OmGroup;
import com.gotop.tyjg.stable.model.OmGroupExample;
import com.gotop.tyjg.stable.service.IOmGroupService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;


public class OmGroupService implements IOmGroupService {
    /**
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(OmGroupService.class);
	/**
	 * 通过spring注入的DAO对象.
	 * @abatorgenerated
	 */
	protected IOmGroupDAO omGroupDAO;

	/**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setOmGroupDAO(IOmGroupDAO omGroupDAO) throws Exception {
        this.omGroupDAO = omGroupDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IOmGroupDAO getOmGroupDAO() throws Exception {
        return this.omGroupDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        OmGroupExample example = new OmGroupExample();
        OmGroupExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        OmGroup record = new OmGroup();
        List list = omGroupDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(OmGroup obj) throws Exception {
        this.omGroupDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(OmGroup obj) throws Exception {
        this.omGroupDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(OmGroup obj) throws Exception {
        java.lang.Long key = obj.getGroupid();
        this.omGroupDAO.deleteByPrimaryKey(key);
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
            	OmGroup tObject = (OmGroup)abs.get(i);
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
            	OmGroup tObject = (OmGroup)abs.get(i);
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
            	OmGroup tObject = (OmGroup)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.omGroupDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.omGroupDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        OmGroupExample example = new OmGroupExample();
        OmGroupExample.Criteria criteria = example.createCriteria();
        List list = omGroupDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = omGroupDAO.selectByDynamic(map,page);
        return list;
    }
}