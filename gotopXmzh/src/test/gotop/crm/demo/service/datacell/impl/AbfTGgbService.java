package test.gotop.crm.demo.service.datacell.impl;

import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import test.gotop.crm.demo.dao.datacell.IAbfTGgbDAO;
import test.gotop.crm.demo.model.datacell.AbfTGgb;
import test.gotop.crm.demo.model.datacell.AbfTGgbExample;
import test.gotop.crm.demo.service.datacell.IAbfTGgbService;

public class AbfTGgbService implements IAbfTGgbService {

	/**
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(AbfTGgbService.class);
	/**
	 * 通过spring注入的DAO对象.
	 * @abatorgenerated
	 */
	protected IAbfTGgbDAO abfTGgbDAO;

	/**
	 * 通过spring注入DAO的set类.
	 * @abatorgenerated
	 */
	
	public void setAbfTGgbDAO(IAbfTGgbDAO abfTGgbDAO) throws Exception {
		this.abfTGgbDAO = abfTGgbDAO;
	}

	/**
	 * 通过spring注入DAO的get类.
	 * @abatorgenerated
	 */
	public IAbfTGgbDAO getAbfTGgbDAO() throws Exception {
		return this.abfTGgbDAO;
	}

	/**
	 * 动态查询实例，分页查询数据并返回list
	 * @abatorgenerated
	 */
	public List queryDataGrid(HashMap map, Page page) throws Exception {
		AbfTGgbExample example = new AbfTGgbExample();
		AbfTGgbExample.Criteria criteria = example.createCriteria();
		example.setOracleStart(page.getBegin());
		example.setOracleEnd(page.getBegin() + page.getLength());
		AbfTGgb record = new AbfTGgb();
		List list = abfTGgbDAO.selectByExampleAndPage(record, example);
		int count = abfTGgbDAO.countByExample(example);
		page.setCount(count);
		return list;
	}

	/**
	 * 更新单条记录，通过主键
	 * @abatorgenerated
	 */
	public void update(AbfTGgb obj) throws Exception {
		this.abfTGgbDAO.updateByPrimaryKey(obj);
	}

	/**
	 * 插入单条记录
	 * @abatorgenerated
	 */
	public void insert(AbfTGgb obj) throws Exception {
		this.abfTGgbDAO.insert(obj);
	}

	/**
	 * 删除单条记录
	 * @abatorgenerated
	 */
	public void delete(AbfTGgb obj) throws Exception {
		AbfTGgb key = new AbfTGgb();
		key.setiGgbh(obj.getiGgbh());
		key.setvGgxx(obj.getvGgxx());
		this.abfTGgbDAO.deleteByPrimaryKey(key);
	}

	/**
	 * 批量更新数据
	 * @abatorgenerated
	 */
	public void updateBatch(List abs) throws Exception {
		for (int i = 0; i < abs.size(); i++) {
			AbfTGgb tObject = (AbfTGgb) abs.get(i);
			this.update(tObject);
		}
	}

	/**
	 * 批量插入数据
	 * @abatorgenerated
	 */
	public void insertBatch(List abs) throws Exception {
		for (int i = 0; i < abs.size(); i++) {
			AbfTGgb tObject = (AbfTGgb) abs.get(i);
			this.insert(tObject);
		}
	}

	/**
	 * 批量删除数据
	 * @abatorgenerated
	 */
	public void deleteBatch(List abs) throws Exception {
		for (int i = 0; i < abs.size(); i++) {
			AbfTGgb tObject = (AbfTGgb) abs.get(i);
			this.delete(tObject);
		}
	}

	/**
	 * datacell方式批量更新数据
	 * @abatorgenerated
	 */
	public void updateDataGrid(HashMap hmp) throws Exception {
		this.abfTGgbDAO.startBatch();
		List insertEntities = (List) hmp.get("insertEntities");
		List deleteEntities = (List) hmp.get("deleteEntities");
		List updateEntities = (List) hmp.get("updateEntities");
		this.updateBatch(insertEntities);
		this.insertBatch(updateEntities);
		this.deleteBatch(deleteEntities);
		this.abfTGgbDAO.executeBatch();
	}

	/**
	 * 查询所有数据并返回List
	 * @abatorgenerated
	 */
	public List queryAllDataList(HashMap map) throws Exception {
		AbfTGgbExample example = new AbfTGgbExample();
		AbfTGgbExample.Criteria criteria = example.createCriteria();
		List list = abfTGgbDAO.selectByExample(example);
		return list;
	}

	/**
	 * 分页方式查询列表数据
	 * @abatorgenerated
	 */
	public List queryPageDataList(HashMap map, Page page) throws Exception {
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin() + page.getLength());
		List list = abfTGgbDAO.selectByDynamic(map);
		return list;
	}
}