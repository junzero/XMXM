package com.gotop.group.service.impl;

import com.fr.report.core.A.g;
import com.gotop.group.dao.ITGroupDAO;
import com.gotop.group.dao.ITGroupmemberDAO;
import com.gotop.group.model.TGroup;
import com.gotop.group.model.TGroupExample;
import com.gotop.group.model.TGroupmember;
import com.gotop.group.model.TGroupmemberKey;
import com.gotop.group.service.ITGroupService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TGroupService implements ITGroupService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TGroupService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITGroupDAO tGroupDAO;
    
    protected ITGroupmemberDAO groupmemberDAO;

    public ITGroupmemberDAO getGroupmemberDAO() {
		return groupmemberDAO;
	}

	public void setGroupmemberDAO(ITGroupmemberDAO groupmemberDAO) {
		this.groupmemberDAO = groupmemberDAO;
	}

	/**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settGroupDAO(ITGroupDAO tGroupDAO) throws Exception {
        this.tGroupDAO = tGroupDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITGroupDAO gettGroupDAO() throws Exception {
        return this.tGroupDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TGroupExample example = new TGroupExample();
        TGroupExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TGroup record = new TGroup();
        List list = tGroupDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TGroup obj) throws Exception {
        this.tGroupDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public void insert(TGroup obj) throws Exception {
        this.tGroupDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TGroup obj) throws Exception {
        java.lang.Long key = obj.getRecId();
        this.tGroupDAO.deleteByPrimaryKey(key);
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
            	TGroup tObject = (TGroup)abs.get(i);
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
            	TGroup tObject = (TGroup)abs.get(i);
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
            	TGroup tObject = (TGroup)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tGroupDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tGroupDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TGroupExample example = new TGroupExample();
        TGroupExample.Criteria criteria = example.createCriteria();
        List list = tGroupDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tGroupDAO.selectByDynamic(map,page);
        return list;
    }

	@Override
	public List<TGroup> myGroupList(TGroup group, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(group != null && group.getGroupName() != null && !"".equals(group.getGroupName())){
			map.put("groupName", group.getGroupName());
		}
		map.put("empId", group.getGroupCreator());
		List list = tGroupDAO.myGroupList(map,page);
        return list;
	}

	@Override
	public void saveGroup(TGroup group) {
		if(group.getRecId() == null){
			//保存群组信息
			tGroupDAO.insert(group);
			String empIds = group.getEmpIds();
			String[] empIdsArray = empIds.split(",");
			
			if(empIdsArray.length != 0){
				for (String empId : empIdsArray) {
					TGroupmember groupmember = new TGroupmember();
					groupmember.setGroupid(group.getRecId());
					groupmember.setMemberid(Long.parseLong(empId));
					groupmemberDAO.insert(groupmember);
				}
			}
		}else{
			tGroupDAO.updateByPrimaryKey(group);
			
			String empIds = group.getEmpIds();
			String[] empIdsArray = empIds.split(",");
			
			TGroupmember groupmember = new TGroupmember();
			groupmember.setGroupid(group.getRecId());
			List<TGroupmember> groupmembers = groupmemberDAO.selectByGroupId(groupmember);
			for (TGroupmember tGroupmember : groupmembers) {
				TGroupmemberKey key = new TGroupmember();
				key.setGroupid(group.getRecId());
				key.setMemberid(tGroupmember.getMemberid());
				groupmemberDAO.deleteByPrimaryKey(key);
			}
			
			if(empIdsArray.length != 0){
				for (String empId : empIdsArray) {
					TGroupmember groupmember1 = new TGroupmember();
					groupmember1.setGroupid(group.getRecId());
					groupmember1.setMemberid(Long.parseLong(empId));
					groupmemberDAO.insert(groupmember1);
				}
			}
		}
		
		
	}

	@Override
	public TGroup getGroupByRecId(TGroup group) {
		TGroup tGroup = tGroupDAO.selectByPrimaryKey(group.getRecId());
		TGroupmember groupmember = new TGroupmember();
		groupmember.setGroupid(tGroup.getRecId());
		String empIds = "";
		String empNames = "";
		List<TGroupmember> groupmembers = groupmemberDAO.selectByGroupId(groupmember);
		if(groupmembers.size() != 0){
			for (TGroupmember tGroupmember : groupmembers) {
				empIds +=tGroupmember.getMemberid().toString() + ",";
				empNames +=tGroupmember.getEmpName() + ",";
			}
		}
		empIds = empIds.substring(0, empIds.length()-1);
		empNames = empNames.substring(0, empNames.length()-1);
		tGroup.setEmpIds(empIds);
		tGroup.setEmpNames(empNames);
		return tGroup;
	}

	@Override
	public void deleteGroup(TGroup group) {
		tGroupDAO.deleteByPrimaryKey(group.getRecId());
		TGroupmember groupmember = new TGroupmember();
		groupmember.setGroupid(group.getRecId());
		groupmemberDAO.deleteByTemplate(groupmember);
	}
}