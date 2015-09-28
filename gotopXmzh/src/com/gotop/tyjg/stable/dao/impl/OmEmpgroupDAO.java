package com.gotop.tyjg.stable.dao.impl;

import com.gotop.tyjg.stable.dao.IOmEmpgroupDAO;
import com.gotop.tyjg.stable.model.OmEmpgroupExample;
import com.gotop.tyjg.stable.model.OmEmpgroupKey;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;


public class OmEmpgroupDAO extends SqlMapClientDao implements IOmEmpgroupDAO {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(OmEmpgroupDAO.class);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table OM_EMPGROUP
     *
     * @abatorgenerated 
     */
    public OmEmpgroupDAO() {
        super();
    }

    /**
     * 插入一条新数据
     * @abatorgenerated
     */
    public void insert(OmEmpgroupKey record) {
        this.insert("OM_EMPGROUP_SqlMap.abatorgenerated_insert", record);
    }

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    public List selectByExample(OmEmpgroupExample example) {
        List list = queryForList("OM_EMPGROUP_SqlMap.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    public List selectByMapAndPage(HashMap example) {
        List list = queryForList("OM_EMPGROUP_SqlMap.abatorgenerated_selectByMapAndPage", example);
        return list;
    }

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    public List selectByExampleAndPage(OmEmpgroupKey record, OmEmpgroupExample example, Page page) {
         UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
         List list = queryForList("OM_EMPGROUP_SqlMap.abatorgenerated_selectByExampleAndPage",parms,page);
        return list;
    }

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    public int deleteByExample(OmEmpgroupExample example) {
        int rows = this.delete("OM_EMPGROUP_SqlMap.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    public int deleteByPrimaryKey(OmEmpgroupKey key) {
        int rows = this.delete("OM_EMPGROUP_SqlMap.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    public int countByExample(OmEmpgroupExample example) {
        Integer count = (Integer)  queryForObject("OM_EMPGROUP_SqlMap.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    public int updateByExampleSelective(OmEmpgroupKey record, OmEmpgroupExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = this.update("OM_EMPGROUP_SqlMap.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    public int updateByExample(OmEmpgroupKey record, OmEmpgroupExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = this.update("OM_EMPGROUP_SqlMap.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * 分页查询信息
     * @abatorgenerated
     */
    public List selectByDynamic(HashMap map, Page page) throws Exception {
        List list = queryForList("OM_EMPGROUP_SqlMap.abatorgenerated_selectByDynamic", map, page);
        return list;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table OM_EMPGROUP
     *
     * @abatorgenerated 
     */
    private static class UpdateByExampleParms extends OmEmpgroupExample {
        private Object record;

        public UpdateByExampleParms(Object record, OmEmpgroupExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}