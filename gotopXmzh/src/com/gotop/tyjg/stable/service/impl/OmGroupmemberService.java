package com.gotop.tyjg.stable.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.gotop.tyjg.stable.dao.IOmGroupmemberDAO;
import com.gotop.tyjg.stable.model.OmGroupmemberExample;
import com.gotop.tyjg.stable.model.OmGroupmemberKey;
import com.gotop.tyjg.stable.service.IOmGroupmemberService;
import com.primeton.utils.Page;

public class OmGroupmemberService implements IOmGroupmemberService {
	/**
	 * 
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(OmGroupmemberService.class);

	/**
	 * 通过spring注入的DAO对象.
	 * 
	 * @abatorgenerated
	 */
	protected IOmGroupmemberDAO omGroupmemberDAO;

	/**
	 * 通过spring注入DAO的set类.
	 * 
	 * @abatorgenerated
	 */
	public void setOmGroupmemberDAO(IOmGroupmemberDAO omGroupmemberDAO)
			throws Exception {
		this.omGroupmemberDAO = omGroupmemberDAO;
	}

	/**
	 * 通过spring注入DAO的get类.
	 * 
	 * @abatorgenerated
	 */
	public IOmGroupmemberDAO getOmGroupmemberDAO() throws Exception {
		return this.omGroupmemberDAO;
	}

	/**
	 * 动态查询实例，分页查询数据并返回list
	 * 
	 * @abatorgenerated
	 */
	public List queryDataGrid(HashMap map, Page page) throws Exception {
		OmGroupmemberExample example = new OmGroupmemberExample();
		OmGroupmemberExample.Criteria criteria = example.createCriteria();
		example.setOracleStart(page.getBegin());
		example.setOracleEnd(page.getBegin() + page.getLength());
		OmGroupmemberKey record = new OmGroupmemberKey();
		List list = omGroupmemberDAO.selectByExampleAndPage(record, example, page);
		return list;
	}

	/**
	 * 更新单条记录，通过主键
	 * 
	 * @abatorgenerated
	 */
	public void update(OmGroupmemberKey obj) throws Exception {
//		this.omGroupmemberDAO.updateByPrimaryKeySelective(obj);
	}

	/**
	 * 插入单条记录
	 * 
	 * @abatorgenerated
	 */
	public void insert(OmGroupmemberKey obj) throws Exception {
		this.omGroupmemberDAO.insert(obj);
	}

	/**
	 * 删除单条记录
	 * 
	 * @abatorgenerated
	 */
	public void delete(OmGroupmemberKey obj) throws Exception {
		OmGroupmemberKey key = new OmGroupmemberKey();
		key.setFlag(obj.getFlag());
		key.setGroupid(obj.getGroupid());
		key.setMemberid(obj.getMemberid());
		this.omGroupmemberDAO.deleteByPrimaryKey(key);
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
			OmGroupmemberKey tObject = (OmGroupmemberKey) abs.get(i);
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
			OmGroupmemberKey tObject = (OmGroupmemberKey) abs.get(i);
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
			OmGroupmemberKey tObject = (OmGroupmemberKey) abs.get(i);
			this.delete(tObject);
		}
	}

	/**
	 * datacell方式批量更新数据
	 * 
	 * @abatorgenerated
	 */
	public void updateDataGrid(HashMap hmp) throws Exception {
		this.omGroupmemberDAO.startBatch();
		List insertEntities = (List) hmp.get("insertEntities");
		List deleteEntities = (List) hmp.get("deleteEntities");
		List updateEntities = (List) hmp.get("updateEntities");
		this.updateBatch(updateEntities);
		this.insertBatch(insertEntities);
		this.deleteBatch(deleteEntities);
		this.omGroupmemberDAO.executeBatch();
	}

	/**
	 * 查询所有数据并返回List
	 * 
	 * @abatorgenerated
	 */
	public List queryAllDataList(HashMap map) throws Exception {
		OmGroupmemberExample example = new OmGroupmemberExample();
		OmGroupmemberExample.Criteria criteria = example.createCriteria();
		List list = omGroupmemberDAO.selectByExample(example);
		return list;
	}

	/**
	 * 分页方式查询列表数据
	 * 
	 * @abatorgenerated
	 */
	public List queryPageDataList(HashMap map, Page page) throws Exception {
		List list = omGroupmemberDAO.selectByDynamic(map, page);
		return list;
	}
}