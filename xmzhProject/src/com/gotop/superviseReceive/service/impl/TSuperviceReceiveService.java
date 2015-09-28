package com.gotop.superviseReceive.service.impl;

import com.gotop.superviseReceive.dao.ITSuperviceReceiveDAO;
import com.gotop.superviseReceive.model.TSuperviceReceive;
import com.gotop.superviseReceive.model.TSuperviceReceiveExample;
import com.gotop.superviseReceive.service.ITSuperviceReceiveService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TSuperviceReceiveService implements ITSuperviceReceiveService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TSuperviceReceiveService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITSuperviceReceiveDAO tSuperviceReceiveDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settSuperviceReceiveDAO(ITSuperviceReceiveDAO tSuperviceReceiveDAO) throws Exception {
        this.tSuperviceReceiveDAO = tSuperviceReceiveDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITSuperviceReceiveDAO gettSuperviceReceiveDAO() throws Exception {
        return this.tSuperviceReceiveDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TSuperviceReceiveExample example = new TSuperviceReceiveExample();
        TSuperviceReceiveExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TSuperviceReceive record = new TSuperviceReceive();
        List list = tSuperviceReceiveDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TSuperviceReceive obj) throws Exception {
        this.tSuperviceReceiveDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TSuperviceReceive obj) throws Exception {
        this.tSuperviceReceiveDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TSuperviceReceive obj) throws Exception {
        java.lang.Long key = obj.getRecId();
        this.tSuperviceReceiveDAO.deleteByPrimaryKey(key);
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
            	TSuperviceReceive tObject = (TSuperviceReceive)abs.get(i);
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
            	TSuperviceReceive tObject = (TSuperviceReceive)abs.get(i);
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
            	TSuperviceReceive tObject = (TSuperviceReceive)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tSuperviceReceiveDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tSuperviceReceiveDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TSuperviceReceiveExample example = new TSuperviceReceiveExample();
        TSuperviceReceiveExample.Criteria criteria = example.createCriteria();
        List list = tSuperviceReceiveDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tSuperviceReceiveDAO.selectByDynamic(map,page);
        return list;
    }
}