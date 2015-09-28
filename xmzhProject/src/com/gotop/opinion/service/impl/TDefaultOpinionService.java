package com.gotop.opinion.service.impl;

import com.gotop.opinion.dao.ITDefaultOpinionDAO;
import com.gotop.opinion.model.TDefaultOpinion;
import com.gotop.opinion.model.TDefaultOpinionExample;
import com.gotop.opinion.service.ITDefaultOpinionService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TDefaultOpinionService implements ITDefaultOpinionService {
    /**
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(TDefaultOpinionService.class);
	/**
	 * 通过spring注入的DAO对象.
	 * @abatorgenerated
	 */
	protected ITDefaultOpinionDAO tDefaultOpinionDAO;

	/**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settDefaultOpinionDAO(ITDefaultOpinionDAO tDefaultOpinionDAO) throws Exception {
        this.tDefaultOpinionDAO = tDefaultOpinionDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITDefaultOpinionDAO gettDefaultOpinionDAO() throws Exception {
        return this.tDefaultOpinionDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TDefaultOpinionExample example = new TDefaultOpinionExample();
        TDefaultOpinionExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TDefaultOpinion record = new TDefaultOpinion();
        List list = tDefaultOpinionDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TDefaultOpinion obj) throws Exception {
        this.tDefaultOpinionDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TDefaultOpinion obj) throws Exception {
        this.tDefaultOpinionDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TDefaultOpinion obj) throws Exception {
        java.lang.Long key = obj.getRecId();
        this.tDefaultOpinionDAO.deleteByPrimaryKey(key);
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
            	TDefaultOpinion tObject = (TDefaultOpinion)abs.get(i);
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
            	TDefaultOpinion tObject = (TDefaultOpinion)abs.get(i);
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
            	TDefaultOpinion tObject = (TDefaultOpinion)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tDefaultOpinionDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tDefaultOpinionDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(Long empid) throws Exception {
        List list = tDefaultOpinionDAO.selectByExample(empid);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tDefaultOpinionDAO.selectByDynamic(map,page);
        return list;
    }

	@Override
	public List<TDefaultOpinion> queryDefaultOps(Long empid) throws Exception {
		List<TDefaultOpinion> list = tDefaultOpinionDAO.queryDefaultOps(empid);
		return list;
	}

	@Override
	public List<TDefaultOpinion> queryDefaultOpsForshow(Long empid)
			throws Exception {
		List<TDefaultOpinion> list = tDefaultOpinionDAO.queryDefaultOpsForshow(empid);
		return list;
	}
}