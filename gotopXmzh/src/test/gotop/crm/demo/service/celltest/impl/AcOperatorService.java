package test.gotop.crm.demo.service.celltest.impl;

import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import test.gotop.crm.demo.dao.celltest.IAcOperatorDAO;
import test.gotop.crm.demo.model.celltest.AcOperator;
import test.gotop.crm.demo.model.celltest.AcOperatorExample;
import test.gotop.crm.demo.service.celltest.IAcOperatorService;

public class AcOperatorService implements IAcOperatorService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(AcOperatorService.class);;

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected IAcOperatorDAO acOperatorDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setAcOperatorDAO(IAcOperatorDAO acOperatorDAO) throws Exception {
        this.acOperatorDAO = acOperatorDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IAcOperatorDAO getAcOperatorDAO() throws Exception {
        return this.acOperatorDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        AcOperatorExample example = new AcOperatorExample();
        AcOperatorExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        AcOperator record = new AcOperator();
        List list = acOperatorDAO.selectByExampleAndPage(record,example);
        int count = acOperatorDAO.countByExample(example);
        page.setCount(count);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(AcOperator obj) throws Exception {
        this.acOperatorDAO.updateByPrimaryKey(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(AcOperator obj) throws Exception {
        this.acOperatorDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(AcOperator obj) throws Exception {
        java.lang.Integer key = obj.getOperatorid();
        this.acOperatorDAO.deleteByPrimaryKey(key);
    }

    /**
     * 批量更新数据
     * @abatorgenerated
     */
    public void updateBatch(List abs) throws Exception {
        for(int i=0;i<abs.size();i++){
            	AcOperator tObject = (AcOperator)abs.get(i);
            	this.update(tObject);
        }
    }

    /**
     * 批量插入数据
     * @abatorgenerated
     */
    public void insertBatch(List abs) throws Exception {
        for(int i=0;i<abs.size();i++){
            	AcOperator tObject = (AcOperator)abs.get(i);
            	this.insert(tObject);
        }
    }

    /**
     * 批量删除数据
     * @abatorgenerated
     */
    public void deleteBatch(List abs) throws Exception {
        for(int i=0;i<abs.size();i++){
            	AcOperator tObject = (AcOperator)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.acOperatorDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(insertEntities);
        this.insertBatch(updateEntities);
        this.deleteBatch(deleteEntities);
        this.acOperatorDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        AcOperatorExample example = new AcOperatorExample();
        AcOperatorExample.Criteria criteria = example.createCriteria();
        List list = acOperatorDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        map.put("oracleStart", page.getBegin());
        map.put("oracleEnd", page.getBegin()+page.getLength());
        List list = acOperatorDAO.selectByDynamic(map);
        return list;
    }
}