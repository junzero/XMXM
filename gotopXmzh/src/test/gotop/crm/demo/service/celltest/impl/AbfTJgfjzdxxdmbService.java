package test.gotop.crm.demo.service.celltest.impl;

import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import test.gotop.crm.demo.dao.celltest.IAbfTJgfjzdxxdmbDAO;
import test.gotop.crm.demo.model.celltest.AbfTJgfjzdxxdmb;
import test.gotop.crm.demo.model.celltest.AbfTJgfjzdxxdmbExample;
import test.gotop.crm.demo.service.celltest.IAbfTJgfjzdxxdmbService;

public class AbfTJgfjzdxxdmbService implements IAbfTJgfjzdxxdmbService {

	/**
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(AbfTJgfjzdxxdmbService.class);
	/**
	 * 通过spring注入的DAO对象.
	 * @abatorgenerated
	 */
	protected IAbfTJgfjzdxxdmbDAO abfTJgfjzdxxdmbDAO;

	/**
	 * 通过spring注入DAO的set类.
	 * @abatorgenerated
	 */
	
	public void setAbfTJgfjzdxxdmbDAO(IAbfTJgfjzdxxdmbDAO abfTJgfjzdxxdmbDAO)
			throws Exception {
		this.abfTJgfjzdxxdmbDAO = abfTJgfjzdxxdmbDAO;
	}

	/**
	 * 通过spring注入DAO的get类.
	 * @abatorgenerated
	 */
	public IAbfTJgfjzdxxdmbDAO getAbfTJgfjzdxxdmbDAO() throws Exception {
		return this.abfTJgfjzdxxdmbDAO;
	}

	/**
	 * 动态查询实例，分页查询数据并返回list
	 * @abatorgenerated
	 */
	public List queryDataGrid(HashMap map, Page page) throws Exception {
		AbfTJgfjzdxxdmbExample example = new AbfTJgfjzdxxdmbExample();
		AbfTJgfjzdxxdmbExample.Criteria criteria = example.createCriteria();
		example.setOracleStart(page.getBegin());
		example.setOracleEnd(page.getBegin() + page.getLength());
		AbfTJgfjzdxxdmb record = new AbfTJgfjzdxxdmb();
		List list = abfTJgfjzdxxdmbDAO.selectByExampleAndPage(record, example);
		int count = abfTJgfjzdxxdmbDAO.countByExample(example);
		page.setCount(count);
		return list;
	}

	/**
	 * 更新单条记录，通过主键
	 * @abatorgenerated
	 */
	public void update(AbfTJgfjzdxxdmb obj) throws Exception {
		this.abfTJgfjzdxxdmbDAO.updateByPrimaryKey(obj);
	}

	/**
	 * 插入单条记录
	 * @abatorgenerated
	 */
	public void insert(AbfTJgfjzdxxdmb obj) throws Exception {
		this.abfTJgfjzdxxdmbDAO.insert(obj);
	}

	/**
	 * 删除单条记录
	 * @abatorgenerated
	 */
	public void delete(AbfTJgfjzdxxdmb obj) throws Exception {
		java.lang.Integer key = obj.getId();
		this.abfTJgfjzdxxdmbDAO.deleteByPrimaryKey(key);
	}

	/**
	 * 批量更新数据
	 * @abatorgenerated
	 */
	public void updateBatch(List abs) throws Exception {
		for (int i = 0; i < abs.size(); i++) {
			AbfTJgfjzdxxdmb tObject = (AbfTJgfjzdxxdmb) abs.get(i);
			this.update(tObject);
		}
	}

	/**
	 * 批量插入数据
	 * @abatorgenerated
	 */
	public void insertBatch(List abs) throws Exception {
		for (int i = 0; i < abs.size(); i++) {
			AbfTJgfjzdxxdmb tObject = (AbfTJgfjzdxxdmb) abs.get(i);
			this.insert(tObject);
		}
	}

	/**
	 * 批量删除数据
	 * @abatorgenerated
	 */
	public void deleteBatch(List abs) throws Exception {
		for (int i = 0; i < abs.size(); i++) {
			AbfTJgfjzdxxdmb tObject = (AbfTJgfjzdxxdmb) abs.get(i);
			this.delete(tObject);
		}
	}

	/**
	 * datacell方式批量更新数据
	 * @abatorgenerated
	 */
	public void updateDataGrid(HashMap hmp) throws Exception {
		this.abfTJgfjzdxxdmbDAO.startBatch();
		List insertEntities = (List) hmp.get("insertEntities");
		List deleteEntities = (List) hmp.get("deleteEntities");
		List updateEntities = (List) hmp.get("updateEntities");
		this.updateBatch(insertEntities);
		this.insertBatch(updateEntities);
		this.deleteBatch(deleteEntities);
		this.abfTJgfjzdxxdmbDAO.executeBatch();
	}

	/**
	 * 查询所有数据并返回List
	 * @abatorgenerated
	 */
	public List queryAllDataList(HashMap map) throws Exception {
		AbfTJgfjzdxxdmbExample example = new AbfTJgfjzdxxdmbExample();
		AbfTJgfjzdxxdmbExample.Criteria criteria = example.createCriteria();
		List list = abfTJgfjzdxxdmbDAO.selectByExample(example);
		return list;
	}

	/**
	 * 分页方式查询列表数据
	 * @abatorgenerated
	 */
	public List queryPageDataList(HashMap map, Page page) throws Exception {
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin() + page.getLength());
		List list = abfTJgfjzdxxdmbDAO.selectByDynamic(map);
		return list;
	}
}