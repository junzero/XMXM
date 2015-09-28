package com.gotop.jbpm.service.impl;

import com.gotop.jbpm.dao.ITProcessConfigDAO;
import com.gotop.jbpm.dto.ProcessDeployDto;
import com.gotop.jbpm.model.TProcessConfig;
import com.gotop.jbpm.model.TProcessConfigExample;
import com.gotop.jbpm.service.ITProcessConfigService;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TProcessConfigService implements ITProcessConfigService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TProcessConfigService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITProcessConfigDAO tProcessConfigDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settProcessConfigDAO(ITProcessConfigDAO tProcessConfigDAO) throws Exception {
        this.tProcessConfigDAO = tProcessConfigDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITProcessConfigDAO gettProcessConfigDAO() throws Exception {
        return this.tProcessConfigDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TProcessConfigExample example = new TProcessConfigExample();
        TProcessConfigExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TProcessConfig record = new TProcessConfig();
        List list = tProcessConfigDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TProcessConfig obj) throws Exception {
        this.tProcessConfigDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public Long insert(TProcessConfig obj) throws Exception {
        return this.tProcessConfigDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TProcessConfig obj) throws Exception {
        java.lang.Long key = obj.getId();
        this.tProcessConfigDAO.deleteByPrimaryKey(key);
    }

    /**
     * 批量更新数据
     * @abatorgenerated
     */
    public void updateBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	TProcessConfig tObject = (TProcessConfig)abs.get(i);
            	this.update(tObject);
        }
    }

    /**
     * 批量插入数据
     * @abatorgenerated
     */
    public void insertBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	TProcessConfig tObject = (TProcessConfig)abs.get(i);
            	this.insert(tObject);
        }
    }

    /**
     * 批量删除数据
     * @abatorgenerated
     */
    public void deleteBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	TProcessConfig tObject = (TProcessConfig)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tProcessConfigDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tProcessConfigDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TProcessConfigExample example = new TProcessConfigExample();
        TProcessConfigExample.Criteria criteria = example.createCriteria();
        List list = tProcessConfigDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tProcessConfigDAO.selectByDynamic(map,page);
        return list;
    }

	@Override
	public List<TProcessConfig> queryMyProcessList(String relationids,String orgId,TProcessConfig processConfig,Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin()+page.getLength());

		if(relationids!=null&&!"".equals(relationids)){
			map.put("relationids", relationids);
		}
		if(orgId!=null&&!"".equals(orgId)){
			map.put("orgid", orgId);
		}
		if(processConfig!=null){
			if(processConfig.getProcessName()!=null){
				map.put("processName", processConfig.getProcessName());
			}
		}else{
			map.put("processName", "");
		}
		return tProcessConfigDAO.queryMyProcessList(map,page);
	}

	public List<TProcessConfig> queryProcessConfigList(TProcessConfig processConfig, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin()+page.getLength());
		if(processConfig!=null){
			if(processConfig.getProcessName()!=null){
				map.put("processName", processConfig.getProcessName());
			}
			if(processConfig.getBusinessType()!=null && !"".equals(processConfig.getBusinessType())){
				map.put("businessType", processConfig.getBusinessType());
			}
		}
		return tProcessConfigDAO.queryProcessConfigList(map,page);
	}

	@Override
	public TProcessConfig getProcessConfigByDefinitionId(String definitionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("definitionId", definitionId);
		return tProcessConfigDAO.getProcessConfigByDefinitionId(map);
	}

	@Override
	public List<TProcessConfig> queryProcessDefinitionId(String businessType)
			throws Exception {
		return tProcessConfigDAO.queryProcessDefinitionId(businessType);
	}

}