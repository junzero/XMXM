package com.gotop.tyjg.stable.dao.impl;

import com.gotop.tyjg.stable.dao.IAtFileuploadDAO;
import com.gotop.tyjg.stable.model.AtFileupload;
import com.gotop.tyjg.stable.model.AtFileuploadExample;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;


public class AtFileuploadDAO extends SqlMapClientDao implements IAtFileuploadDAO {
    /**
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(AtFileuploadDAO.class);

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table AT_FILEUPLOAD
	 * @abatorgenerated  
	 */
	private static class UpdateByExampleParms extends AtFileuploadExample {
		private Object record;

		public UpdateByExampleParms(Object record, AtFileuploadExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	/**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table AT_FILEUPLOAD
     *
     * @abatorgenerated 
     */
    public AtFileuploadDAO() {
        super();
    }

    /**
     * 插入一条新数据
     * @abatorgenerated
     */
    public void insert(AtFileupload record) {
        this.insert("AT_FILEUPLOAD_SqlMap.abatorgenerated_insert", record);
    }

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    public int updateByPrimaryKey(AtFileupload record) {
        int rows = this.update("AT_FILEUPLOAD_SqlMap.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
     * @abatorgenerated
     */
    public int updateByPrimaryKeySelective(AtFileupload record) {
        int rows = this.update("AT_FILEUPLOAD_SqlMap.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    public List selectByExample(AtFileuploadExample example) {
        List list = queryForList("AT_FILEUPLOAD_SqlMap.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    public List selectByMapAndPage(HashMap example) {
        List list = queryForList("AT_FILEUPLOAD_SqlMap.abatorgenerated_selectByMapAndPage", example);
        return list;
    }

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    public List selectByExampleAndPage(AtFileupload record, AtFileuploadExample example, Page page) {
         UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
         List list = queryForList("AT_FILEUPLOAD_SqlMap.abatorgenerated_selectByExampleAndPage",parms,page);
        return list;
    }

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    public AtFileupload selectByPrimaryKey(String fileId) {
        AtFileupload key = new AtFileupload();
        key.setFileId(fileId);
        AtFileupload record = (AtFileupload) queryForObject("AT_FILEUPLOAD_SqlMap.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    public int deleteByExample(AtFileuploadExample example) {
        int rows = this.delete("AT_FILEUPLOAD_SqlMap.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    public int deleteByPrimaryKey(String fileId) {
        AtFileupload key = new AtFileupload();
        key.setFileId(fileId);
        int rows = this.delete("AT_FILEUPLOAD_SqlMap.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    public int countByExample(AtFileuploadExample example) {
        Integer count = (Integer)  queryForObject("AT_FILEUPLOAD_SqlMap.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    public int updateByExampleSelective(AtFileupload record, AtFileuploadExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = this.update("AT_FILEUPLOAD_SqlMap.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    public int updateByExample(AtFileupload record, AtFileuploadExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = this.update("AT_FILEUPLOAD_SqlMap.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * 分页查询信息
     * @abatorgenerated
     */
    public List selectByDynamic(HashMap map, Page page) throws Exception {
        List list = queryForList("AT_FILEUPLOAD_SqlMap.abatorgenerated_selectByDynamic", map, page);
        return list;
    }

}