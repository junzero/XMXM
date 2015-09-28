
package com.gotop.file.dao.impl;

import com.gotop.file.dao.ITFileResourceTableDAO;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.model.TFileResourceTableExample;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TFileResourceTableDAO extends SqlMapClientDao implements ITFileResourceTableDAO {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TFileResourceTableDAO.class);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table T_FILE_RESOURCE_TABLE
     *
     * @abatorgenerated 
     */
    public TFileResourceTableDAO() {
        super();
    }

    /**
     * 插入一条新数据.
     * @abatorgenerated
     */
    public String insert(TFileResourceTable record) {
        getSqlMapClientTemplate().insert("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_insert", record);
        return "1";
    }

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    public int updateByPrimaryKey(TFileResourceTable record) {
        int rows = getSqlMapClientTemplate().update("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
     * @abatorgenerated
     */
    public int updateByPrimaryKeySelective(TFileResourceTable record) {
        int rows = getSqlMapClientTemplate().update("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    public List selectByExample(TFileResourceTableExample example) {
        List list = queryForList("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    public List selectByMapAndPage(HashMap example) {
        List list = queryForList("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_selectByMapAndPage", example);
        return list;
    }

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    public List selectByExampleAndPage(TFileResourceTable record, TFileResourceTableExample example, Page page) {
         UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
         List list = queryForList("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_selectByExampleAndPage",parms,page);
        return list;
    }

    /**
     * 根据查询模板的查询结果扩展一个实例
     * @abatorgenerated
     * @param example 条件
     */
    public TFileResourceTable expandEntityByTemplate(TFileResourceTable example) {
        TFileResourceTable result = (TFileResourceTable)queryForObject("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_expandEntityByTemplate", example);
        return result;
    }

    /**
     * 根据Bean数据模板查询条件查询所有记录
     * @abatorgenerated
     * @param example 条件
     */
    public List queryEntitiesByTemplate(TFileResourceTable example) {
        List<TFileResourceTable> result = (List<TFileResourceTable>)queryForList("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_queryEntitiesByTemplate", example);
        return result;
    }

    /**
     * 根据Bean数据模板分页查询
     * @abatorgenerated
     * @param example 条件
     * @param page 分页信息
     */
    public List queryEntitiesByTemplateWithPage(TFileResourceTable example, Page page) {
        List<TFileResourceTable> result = (List<TFileResourceTable>)queryForList("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_queryEntitiesByTemplate", example,page);
        return result;
    }

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    public TFileResourceTable selectByPrimaryKey(Long fileId) {
        TFileResourceTable key = new TFileResourceTable();
        key.setFileId(fileId);
        TFileResourceTable record = (TFileResourceTable) queryForObject("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    public int deleteByExample(TFileResourceTableExample example) {
        int rows = getSqlMapClientTemplate().delete("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param example 条件
     */
    public int deleteByTemplate(TFileResourceTable example) {
        int rows = getSqlMapClientTemplate().delete("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_deleteByTemplate", example);
        return rows;
    }

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    public int deleteByPrimaryKey(Long fileId) {
        TFileResourceTable key = new TFileResourceTable();
        key.setFileId(fileId);
        int rows = getSqlMapClientTemplate().delete("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    public int countByExample(TFileResourceTableExample example) {
        Integer count = (Integer)  queryForObject("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * 通过查询Bean数据模板，统计总数
     * @abatorgenerated
     * @param example 条件
     */
    public int countByTemplate(TFileResourceTable example) {
        Integer rows = (Integer)queryForObject("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_countByTemplate", example);
        return rows.intValue();
    }

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    public int updateByExampleSelective(TFileResourceTable record, TFileResourceTableExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    public int updateByExample(TFileResourceTable record, TFileResourceTableExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * 根据Bean数据模板查询条件更新记录
     * @abatorgenerated
     * @param record 值
     * @param example 条件
     */
    public int updateEntityByTemplate(TFileResourceTable record, TFileResourceTable example) {
        HashMap<String,TFileResourceTable> params = new HashMap<String,TFileResourceTable>();
        params.put("record", record);
        params.put("template", example);
        int rows = getSqlMapClientTemplate().update("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_updateEntityByTemplate", params);
        return rows;
    }

    /**
     * 分页查询信息
     * @abatorgenerated
     */
    public List selectByDynamic(HashMap map, Page page) throws Exception {
        List list = queryForList("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_selectByDynamic", map, page);
        return list;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table T_FILE_RESOURCE_TABLE
     *
     * @abatorgenerated 
     */
    private static class UpdateByExampleParms extends TFileResourceTableExample {
        private Object record;

        public UpdateByExampleParms(Object record, TFileResourceTableExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

	@Override
	public List<TFileResourceTable> queryProcessFileLists() {
		TFileResourceTable tFileResourceTable = new TFileResourceTable();
		tFileResourceTable.setResourceType("10");
		List<TFileResourceTable> tFileResourceTables = queryForList("T_FILE_RESOURCE_TABLE_SqlMap.queryProcessFileLists", tFileResourceTable);
		return tFileResourceTables;
	}

	@Override
	public TFileResourceTable getFileResource(String fileId) {
		TFileResourceTable fileResourceTable = new TFileResourceTable();
		fileResourceTable.setFileId(Long.valueOf(fileId));
		fileResourceTable = (TFileResourceTable) queryForObject("T_FILE_RESOURCE_TABLE_SqlMap.abatorgenerated_selectByPrimaryKey", fileResourceTable);
		return fileResourceTable;
	}

	@Override
	public List<TFileResourceTable> queryFileByIdandType(Long id,
			String resourceType,String fileType) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("resourceType", resourceType);
		if(fileType!=null&&!"".equals(fileType)){
			map.put("fileType", fileType);
		}else{
			map.put("fileType", "0");
		}
		List<TFileResourceTable> fileList = queryForList("T_FILE_RESOURCE_TABLE_SqlMap.queryFileByIdandType", map);
		return fileList;
	}

	@Override
	public void deleteFileToFileName(TFileResourceTable file) throws Exception {
		this.delete("T_FILE_RESOURCE_TABLE_SqlMap.delete_file_from_filename", file);
	}

	@Override
	public void insertTFileResource(TFileResourceTable tfile) throws Exception {
		 getSqlMapClientTemplate().insert("T_FILE_RESOURCE_TABLE_SqlMap.tfileresource_insert", tfile);
		
	}
}