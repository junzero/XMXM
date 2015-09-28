package com.gotop.opinion.service.impl;

import com.gotop.jbpm.service.JbpmService;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.OpninionBean;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.opinion.model.TApproveOpninionExample;
import com.gotop.opinion.service.ITApproveOpninionService;
import com.gotop.util.time.TimeUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TApproveOpninionService implements ITApproveOpninionService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TApproveOpninionService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITApproveOpninionDAO tApproveOpninionDAO;
    
    private JbpmService jbpmService;

    public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	/**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settApproveOpninionDAO(ITApproveOpninionDAO tApproveOpninionDAO) throws Exception {
        this.tApproveOpninionDAO = tApproveOpninionDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITApproveOpninionDAO gettApproveOpninionDAO() throws Exception {
        return this.tApproveOpninionDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TApproveOpninionExample example = new TApproveOpninionExample();
        TApproveOpninionExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TApproveOpninion record = new TApproveOpninion();
        List list = tApproveOpninionDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TApproveOpninion obj) throws Exception {
        this.tApproveOpninionDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public Long insert(TApproveOpninion obj, HashMap<String, Object> map, String taskId) throws Exception {
    	Long id = this.tApproveOpninionDAO.insert(obj);
    	jbpmService.completeTask(taskId,"审核",map);
        return id;
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TApproveOpninion obj) throws Exception {
        java.lang.Long key = obj.getRecId();
        this.tApproveOpninionDAO.deleteByPrimaryKey(key);
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
            	TApproveOpninion tObject = (TApproveOpninion)abs.get(i);
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
            	TApproveOpninion tObject = (TApproveOpninion)abs.get(i);
            	this.insert(tObject,null,null);
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
            	TApproveOpninion tObject = (TApproveOpninion)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tApproveOpninionDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tApproveOpninionDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TApproveOpninionExample example = new TApproveOpninionExample();
        TApproveOpninionExample.Criteria criteria = example.createCriteria();
        List list = tApproveOpninionDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tApproveOpninionDAO.selectByDynamic(map,page);
        return list;
    }

	@Override
	public List queryOpninions(HashMap hm, Page page) {
        List<TApproveOpninion> list = tApproveOpninionDAO.queryOpninions(hm,page);
        return list;
	}

	@Override
	public void insertOpninionInfo(OpninionBean bean, MUOUserSession muo)
			throws Exception {
		if(bean!=null&&bean.getResourceId()!=null&&!"".equals(bean.getResourceId())){
			if(bean.getOpninionContent()!=null&&!"".equals(bean.getOpninionContent())){
		    	TApproveOpninion opninion=new TApproveOpninion();
		    	opninion.setResourceId(bean.resourceId);
		    	opninion.setOperator(muo.getEmpid());
		    	opninion.setOrgid(String.valueOf(muo.getOrgid()));
		    	opninion.setResourceType(bean.getResourceType());
		    	opninion.setOperaterDate(TimeUtil.today());
		    	opninion.setOperaterTime(TimeUtil.now());
		    	opninion.setOpninionContent(bean.getOpninionContent());
		    	opninion.setOperatorType(bean.getOperatorType());
		    	if(bean.getNextOprName().equals("null")){
		    		opninion.setNextOprName("");
		    	}else{
		    		opninion.setNextOprName(bean.getNextOprName());
		    	}
		    	//需要taskId
		    	opninion.setNodeId(bean.getNodeId());
		    	opninion.setNodeName(bean.getNodeName());
		    	tApproveOpninionDAO.insert(opninion);
			}
		}
		
	}

	@Override
	public void insertDeleteProcessOpninions(TApproveOpninion opninion) {
		tApproveOpninionDAO.insert(opninion);
	}

	@Override
	public String receiveResourceId(String resourceFlow, String resourceType) {
		HashMap map = new HashMap();
		map.put("resourceFlow", resourceFlow);
		map.put("resourceType", resourceType);
		return tApproveOpninionDAO.receiveResourceId(map);
	}
}