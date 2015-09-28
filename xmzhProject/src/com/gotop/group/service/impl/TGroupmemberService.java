package com.gotop.group.service.impl;

import com.gotop.group.dao.ITGroupmemberDAO;
import com.gotop.group.model.TGroupmember;
import com.gotop.group.model.TGroupmemberExample;
import com.gotop.group.service.ITGroupmemberService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TGroupmemberService implements ITGroupmemberService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TGroupmemberService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITGroupmemberDAO tGroupmemberDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settGroupmemberDAO(ITGroupmemberDAO tGroupmemberDAO) throws Exception {
        this.tGroupmemberDAO = tGroupmemberDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITGroupmemberDAO gettGroupmemberDAO() throws Exception {
        return this.tGroupmemberDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TGroupmemberExample example = new TGroupmemberExample();
        TGroupmemberExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TGroupmember record = new TGroupmember();
        List list = tGroupmemberDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TGroupmember obj) throws Exception {
        this.tGroupmemberDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TGroupmember obj) throws Exception {
        this.tGroupmemberDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TGroupmember obj) throws Exception {
        TGroupmember key = new TGroupmember();
        key.setGroupid(obj.getGroupid());
        key.setMemberid(obj.getMemberid());
        this.tGroupmemberDAO.deleteByPrimaryKey(key);
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
            	TGroupmember tObject = (TGroupmember)abs.get(i);
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
            	TGroupmember tObject = (TGroupmember)abs.get(i);
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
            	TGroupmember tObject = (TGroupmember)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tGroupmemberDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tGroupmemberDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TGroupmemberExample example = new TGroupmemberExample();
        TGroupmemberExample.Criteria criteria = example.createCriteria();
        List list = tGroupmemberDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tGroupmemberDAO.selectByDynamic(map,page);
        return list;
    }
}