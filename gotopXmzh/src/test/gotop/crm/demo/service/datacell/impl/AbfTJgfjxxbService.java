package test.gotop.crm.demo.service.datacell.impl;

import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import test.gotop.crm.demo.dao.datacell.IAbfTJgfjxxbDAO;
import test.gotop.crm.demo.model.datacell.AbfTJgfjxxb;
import test.gotop.crm.demo.model.datacell.AbfTJgfjxxbExample;
import test.gotop.crm.demo.service.datacell.IAbfTJgfjxxbService;

public class AbfTJgfjxxbService implements IAbfTJgfjxxbService {

	/**
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(AbfTJgfjxxbService.class);
	/**
	 * 通过spring注入的DAO对象.
	 * @abatorgenerated
	 */
	protected IAbfTJgfjxxbDAO abfTJgfjxxbDAO;

	/**
	 * 通过spring注入DAO的set类.
	 * @abatorgenerated
	 */
	public void setAbfTJgfjxxbDAO(IAbfTJgfjxxbDAO abfTJgfjxxbDAO)
			throws Exception {
		this.abfTJgfjxxbDAO = abfTJgfjxxbDAO;
	}

	/**
	 * 通过spring注入DAO的get类.
	 * @abatorgenerated
	 */
	public IAbfTJgfjxxbDAO getAbfTJgfjxxbDAO() throws Exception {
		return this.abfTJgfjxxbDAO;
	}

	/**
	 * 动态查询实例，分页查询数据并返回list
	 * @abatorgenerated
	 */
	public List queryDataGrid(HashMap map, Page page) throws Exception {
		AbfTJgfjxxbExample example = new AbfTJgfjxxbExample();
		AbfTJgfjxxbExample.Criteria criteria = example.createCriteria();
		example.setOracleStart(page.getBegin());
		example.setOracleEnd(page.getBegin() + page.getLength());
		AbfTJgfjxxb record = new AbfTJgfjxxb();
		List list = abfTJgfjxxbDAO.selectByExampleAndPage(record, example);
		int count = abfTJgfjxxbDAO.countByExample(example);
		page.setCount(count);
		return list;
	}

	/**
	 * 更新单条记录，通过主键
	 * @abatorgenerated
	 */
	public void update(AbfTJgfjxxb obj) throws Exception {
		this.abfTJgfjxxbDAO.updateByPrimaryKey(obj);
	}

	/**
	 * 插入单条记录
	 * @abatorgenerated
	 */
	public void insert(AbfTJgfjxxb obj) throws Exception {
		this.abfTJgfjxxbDAO.insert(obj);
	}

	/**
	 * 删除单条记录
	 * @abatorgenerated
	 */
	public void delete(AbfTJgfjxxb obj) throws Exception {
		java.lang.Integer key = obj.getOrgid();
		this.abfTJgfjxxbDAO.deleteByPrimaryKey(key);
	}

	/**
	 * 批量更新数据
	 * @abatorgenerated
	 */
	public void updateBatch(List abs) throws Exception {
		for (int i = 0; i < abs.size(); i++) {
			AbfTJgfjxxb tObject = (AbfTJgfjxxb) abs.get(i);
			this.update(tObject);
		}
	}

	/**
	 * 批量插入数据
	 * @abatorgenerated
	 */
	public void insertBatch(List abs) throws Exception {
		for (int i = 0; i < abs.size(); i++) {
			AbfTJgfjxxb tObject = (AbfTJgfjxxb) abs.get(i);
			this.insert(tObject);
		}
	}

	/**
	 * 批量删除数据
	 * @abatorgenerated
	 */
	public void deleteBatch(List abs) throws Exception {
		for (int i = 0; i < abs.size(); i++) {
			AbfTJgfjxxb tObject = (AbfTJgfjxxb) abs.get(i);
			this.delete(tObject);
		}
	}

	/**
	 * datacell方式批量更新数据
	 * @abatorgenerated
	 */
	public void updateDataGrid(HashMap hmp) throws Exception {
		this.abfTJgfjxxbDAO.startBatch();
		List insertEntities = (List) hmp.get("insertEntities");
		List deleteEntities = (List) hmp.get("deleteEntities");
		List updateEntities = (List) hmp.get("updateEntities");
		this.updateBatch(insertEntities);
		this.insertBatch(updateEntities);
		this.deleteBatch(deleteEntities);
		this.abfTJgfjxxbDAO.executeBatch();
	}

	/**
	 * 查询所有数据并返回List
	 * @abatorgenerated
	 */
	public List queryAllDataList(HashMap map) throws Exception {
		AbfTJgfjxxbExample example = new AbfTJgfjxxbExample();
		AbfTJgfjxxbExample.Criteria criteria = example.createCriteria();
		List list = abfTJgfjxxbDAO.selectByExample(example);
		return list;
	}

	/**
	 * 分页方式查询列表数据
	 * @abatorgenerated
	 */
	public List queryPageDataList(HashMap map, Page page) throws Exception {
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin() + page.getLength());
		List list = abfTJgfjxxbDAO.selectByDynamic(map);
		return list;
	}
}