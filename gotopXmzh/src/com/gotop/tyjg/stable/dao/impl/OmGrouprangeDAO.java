package com.gotop.tyjg.stable.dao.impl;

import com.gotop.tyjg.stable.dao.IOmGrouprangeDAO;
import com.gotop.tyjg.stable.model.OmGrouprangeExample;
import com.gotop.tyjg.stable.model.OmGrouprangeKey;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;


public class OmGrouprangeDAO extends SqlMapClientDao implements IOmGrouprangeDAO {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(OmGrouprangeDAO.class);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table OM_GROUPRANGE
     *
     * @abatorgenerated 
     */
    public OmGrouprangeDAO() {
        super();
    }

    /**
     * 插入一条新数据
     * @abatorgenerated
     */
    public void insert(OmGrouprangeKey record) {
        this.insert("OM_GROUPRANGE_SqlMap.abatorgenerated_insert", record);
    }

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    public List selectByExample(OmGrouprangeExample example) {
        List list = queryForList("OM_GROUPRANGE_SqlMap.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    public List selectByMapAndPage(HashMap example) {
        List list = queryForList("OM_GROUPRANGE_SqlMap.abatorgenerated_selectByMapAndPage", example);
        return list;
    }

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    public List selectByExampleAndPage(OmGrouprangeKey record, OmGrouprangeExample example, Page page) {
         UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
         List list = queryForList("OM_GROUPRANGE_SqlMap.abatorgenerated_selectByExampleAndPage",parms,page);
        return list;
    }

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    public int deleteByExample(OmGrouprangeExample example) {
        int rows = this.delete("OM_GROUPRANGE_SqlMap.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    public int deleteByPrimaryKey(OmGrouprangeKey key) {
        int rows = this.delete("OM_GROUPRANGE_SqlMap.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    public int countByExample(OmGrouprangeExample example) {
        Integer count = (Integer)  queryForObject("OM_GROUPRANGE_SqlMap.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    public int updateByExampleSelective(OmGrouprangeKey record, OmGrouprangeExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = this.update("OM_GROUPRANGE_SqlMap.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    public int updateByExample(OmGrouprangeKey record, OmGrouprangeExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = this.update("OM_GROUPRANGE_SqlMap.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * 分页查询信息
     * @abatorgenerated
     */
    public List selectByDynamic(HashMap map, Page page) throws Exception {
        List list = queryForList("OM_GROUPRANGE_SqlMap.abatorgenerated_selectByDynamic", map, page);
        return list;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table OM_GROUPRANGE
     *
     * @abatorgenerated 
     */
    private static class UpdateByExampleParms extends OmGrouprangeExample {
        private Object record;

        public UpdateByExampleParms(Object record, OmGrouprangeExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}