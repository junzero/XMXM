package com.gotop.tyjg.stable.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.gotop.tyjg.stable.dao.IOmEmpgroupDAO;
import com.gotop.tyjg.stable.model.OmEmpgroupExample;
import com.gotop.tyjg.stable.model.OmEmpgroupKey;
import com.gotop.tyjg.stable.service.IOmEmpgroupService;
import com.primeton.utils.Page;

public class OmEmpgroupService implements IOmEmpgroupService {
	/**
	 * 
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(OmEmpgroupService.class);

	/**
	 * 通过spring注入的DAO对象.
	 * 
	 * @abatorgenerated
	 */
	protected IOmEmpgroupDAO omEmpgroupDAO;

	/**
	 * 通过spring注入DAO的set类.
	 * 
	 * @abatorgenerated
	 */
	public void setOmEmpgroupDAO(IOmEmpgroupDAO omEmpgroupDAO) throws Exception {
		this.omEmpgroupDAO = omEmpgroupDAO;
	}

	/**
	 * 通过spring注入DAO的get类.
	 * 
	 * @abatorgenerated
	 */
	public IOmEmpgroupDAO getOmEmpgroupDAO() throws Exception {
		return this.omEmpgroupDAO;
	}

	/**
	 * 动态查询实例，分页查询数据并返回list
	 * 
	 * @abatorgenerated
	 */
	public List queryDataGrid(HashMap map, Page page) throws Exception {
		OmEmpgroupExample example = new OmEmpgroupExample();
		OmEmpgroupExample.Criteria criteria = example.createCriteria();
		example.setOracleStart(page.getBegin());
		example.setOracleEnd(page.getBegin() + page.getLength());
		OmEmpgroupKey record = new OmEmpgroupKey();
		List list = omEmpgroupDAO.selectByExampleAndPage(record, example, page);
		return list;
	}

	/**
	 * 更新单条记录，通过主键
	 * 
	 * @abatorgenerated
	 */
	public void update(OmEmpgroupKey obj) throws Exception {
		// this.omEmpgroupDAO.updateByPrimaryKeySelective(obj);
	}

	/**
	 * 插入单条记录
	 * 
	 * @abatorgenerated
	 */
	public void insert(OmEmpgroupKey obj) throws Exception {
		this.omEmpgroupDAO.insert(obj);
	}

	/**
	 * 删除单条记录
	 * 
	 * @abatorgenerated
	 */
	public void delete(OmEmpgroupKey obj) throws Exception {
		OmEmpgroupKey key = new OmEmpgroupKey();
		key.setEmpid(obj.getEmpid());
		key.setGroupid(obj.getGroupid());
		this.omEmpgroupDAO.deleteByPrimaryKey(key);
	}

	/**
	 * 批量更新数据
	 * 
	 * @abatorgenerated
	 */
	public void updateBatch(List abs) throws Exception {
		if (abs == null) {
			return;
		}
		for (int i = 0; i < abs.size(); i++) {
			OmEmpgroupKey tObject = (OmEmpgroupKey) abs.get(i);
			this.update(tObject);
		}
	}

	/**
	 * 批量插入数据
	 * 
	 * @abatorgenerated
	 */
	public void insertBatch(List abs) throws Exception {
		if (abs == null) {
			return;
		}
		for (int i = 0; i < abs.size(); i++) {
			OmEmpgroupKey tObject = (OmEmpgroupKey) abs.get(i);
			this.insert(tObject);
		}
	}

	/**
	 * 批量删除数据
	 * 
	 * @abatorgenerated
	 */
	public void deleteBatch(List abs) throws Exception {
		if (abs == null) {
			return;
		}
		for (int i = 0; i < abs.size(); i++) {
			OmEmpgroupKey tObject = (OmEmpgroupKey) abs.get(i);
			this.delete(tObject);
		}
	}

	/**
	 * datacell方式批量更新数据
	 * 
	 * @abatorgenerated
	 */
	public void updateDataGrid(HashMap hmp) throws Exception {
		this.omEmpgroupDAO.startBatch();
		List insertEntities = (List) hmp.get("insertEntities");
		List deleteEntities = (List) hmp.get("deleteEntities");
		List updateEntities = (List) hmp.get("updateEntities");
		this.updateBatch(updateEntities);
		this.insertBatch(insertEntities);
		this.deleteBatch(deleteEntities);
		this.omEmpgroupDAO.executeBatch();
	}

	/**
	 * 查询所有数据并返回List
	 * 
	 * @abatorgenerated
	 */
	public List queryAllDataList(HashMap map) throws Exception {
		OmEmpgroupExample example = new OmEmpgroupExample();
		OmEmpgroupExample.Criteria criteria = example.createCriteria();
		List list = omEmpgroupDAO.selectByExample(example);
		return list;
	}

	/**
	 * 分页方式查询列表数据
	 * 
	 * @abatorgenerated
	 */
	public List queryPageDataList(HashMap map, Page page) throws Exception {
		List list = omEmpgroupDAO.selectByDynamic(map, page);
		return list;
	}
}