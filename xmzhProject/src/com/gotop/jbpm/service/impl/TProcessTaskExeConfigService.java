package com.gotop.jbpm.service.impl;

import com.gotop.jbpm.dao.ITProcessTaskExeConfigDAO;
import com.gotop.jbpm.dto.ActivityDto;
import com.gotop.jbpm.model.TProcessTaskExeConfig;
import com.gotop.jbpm.model.TProcessTaskExeConfigExample;
import com.gotop.jbpm.service.ITProcessTaskExeConfigService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TProcessTaskExeConfigService implements ITProcessTaskExeConfigService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TProcessTaskExeConfigService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITProcessTaskExeConfigDAO tProcessTaskExeConfigDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settProcessTaskExeConfigDAO(ITProcessTaskExeConfigDAO tProcessTaskExeConfigDAO) throws Exception {
        this.tProcessTaskExeConfigDAO = tProcessTaskExeConfigDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITProcessTaskExeConfigDAO gettProcessTaskExeConfigDAO() throws Exception {
        return this.tProcessTaskExeConfigDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TProcessTaskExeConfigExample example = new TProcessTaskExeConfigExample();
        TProcessTaskExeConfigExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TProcessTaskExeConfig record = new TProcessTaskExeConfig();
        List list = tProcessTaskExeConfigDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TProcessTaskExeConfig obj) throws Exception {
        this.tProcessTaskExeConfigDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TProcessTaskExeConfig obj) throws Exception {
        this.tProcessTaskExeConfigDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TProcessTaskExeConfig obj) throws Exception {
        java.lang.Long key = obj.getId();
        this.tProcessTaskExeConfigDAO.deleteByPrimaryKey(key);
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
            	TProcessTaskExeConfig tObject = (TProcessTaskExeConfig)abs.get(i);
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
            	TProcessTaskExeConfig tObject = (TProcessTaskExeConfig)abs.get(i);
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
            	TProcessTaskExeConfig tObject = (TProcessTaskExeConfig)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tProcessTaskExeConfigDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tProcessTaskExeConfigDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TProcessTaskExeConfigExample example = new TProcessTaskExeConfigExample();
        TProcessTaskExeConfigExample.Criteria criteria = example.createCriteria();
        List list = tProcessTaskExeConfigDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tProcessTaskExeConfigDAO.selectByDynamic(map,page);
        return list;
    }

	@Override
	public TProcessTaskExeConfig getTaskExeConfig(String definitionId, String taskName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("definitionId", definitionId);
		map.put("activityName", taskName);
		return tProcessTaskExeConfigDAO.getTaskExeConfig(map);
	}

	@Override
	public TProcessTaskExeConfig getProcessTaskExeConfigById(Long businessKey) {
		return tProcessTaskExeConfigDAO.selectByPrimaryKey(businessKey);
	}

	@Override
	public void saveTaskNameConfig(String definitionId,
			List<ActivityDto> updateList) {
		
		for (ActivityDto activityDto : updateList) {
			
			if(judgeTaskNameConfig(definitionId, activityDto.getBeforeName(), activityDto.getDestinationName()).size() != 0){
				//如果存在exeConfig记录，则更新actShowName
				List<TProcessTaskExeConfig> list = judgeTaskNameConfig(definitionId, activityDto.getBeforeName(), activityDto.getDestinationName());
				TProcessTaskExeConfig config = list.get(0);
				config.setActShowName(activityDto.getActShowName());
				this.tProcessTaskExeConfigDAO.updateByPrimaryKey1(config);
			}else{
				if(judgeTaskNameConfig(definitionId,null, activityDto.getDestinationName()).size() != 0){
					List<TProcessTaskExeConfig> list = judgeTaskNameConfig(definitionId, null, activityDto.getDestinationName());
					TProcessTaskExeConfig config = list.get(0);
					if(config.getBeforeName() != null){
						config.setId(null);
						config.setBeforeName(activityDto.getBeforeName());
						config.setActShowFlag(activityDto.getActShowName());
						config.setActShowName(activityDto.getActShowName());
						this.tProcessTaskExeConfigDAO.insert1(config);
					}else{
						config.setBeforeName(activityDto.getBeforeName());
						config.setActShowName(activityDto.getActShowName());
						this.tProcessTaskExeConfigDAO.updateByPrimaryKey1(config);
					}
					//config.setBeforeName(activityDto.getBeforeName());
					
				}else{
					
				}
			}
			
		}
		
	}
	
	public List<TProcessTaskExeConfig> judgeTaskNameConfig(String definitionId,String beforeName, String destinationName){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("definitionId", definitionId);
		if(beforeName != null){
			map.put("beforeName", beforeName);	
		}
		map.put("destinationName", destinationName);
		return this.tProcessTaskExeConfigDAO.judgeTaskNameConfig(map);
		
	}

}