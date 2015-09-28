package com.gotop.jbpm.service.impl;

import com.eos.server.dict.DictManager;
import com.gotop.jbpm.dao.ITProcessTaskAssigneeDAO;
import com.gotop.jbpm.dao.ITProcessTaskAssigneePersonDAO;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.model.TProcessBusiness;
import com.gotop.jbpm.model.TProcessTaskAssignee;
import com.gotop.jbpm.model.TProcessTaskAssigneeExample;
import com.gotop.jbpm.model.TProcessTaskAssigneePerson;
import com.gotop.jbpm.service.ITProcessTaskAssigneeService;
import com.gotop.util.string.Obj2StrUtils;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TProcessTaskAssigneeService implements ITProcessTaskAssigneeService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TProcessTaskAssigneeService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITProcessTaskAssigneeDAO tProcessTaskAssigneeDAO;
    
	/**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settProcessTaskAssigneeDAO(ITProcessTaskAssigneeDAO tProcessTaskAssigneeDAO) throws Exception {
        this.tProcessTaskAssigneeDAO = tProcessTaskAssigneeDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITProcessTaskAssigneeDAO gettProcessTaskAssigneeDAO() throws Exception {
        return this.tProcessTaskAssigneeDAO;
    }

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TProcessTaskAssigneeExample example = new TProcessTaskAssigneeExample();
        TProcessTaskAssigneeExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TProcessTaskAssignee record = new TProcessTaskAssignee();
        List list = tProcessTaskAssigneeDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TProcessTaskAssignee obj) throws Exception {
        this.tProcessTaskAssigneeDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public Long insert(TProcessTaskAssignee obj) throws Exception {
        return this.tProcessTaskAssigneeDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TProcessTaskAssignee obj) throws Exception {
        java.lang.Long key = obj.getId();
        this.tProcessTaskAssigneeDAO.deleteByPrimaryKey(key);
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
            	TProcessTaskAssignee tObject = (TProcessTaskAssignee)abs.get(i);
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
            	TProcessTaskAssignee tObject = (TProcessTaskAssignee)abs.get(i);
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
            	TProcessTaskAssignee tObject = (TProcessTaskAssignee)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tProcessTaskAssigneeDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tProcessTaskAssigneeDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TProcessTaskAssigneeExample example = new TProcessTaskAssigneeExample();
        TProcessTaskAssigneeExample.Criteria criteria = example.createCriteria();
        List list = tProcessTaskAssigneeDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tProcessTaskAssigneeDAO.selectByDynamic(map,page);
        return list;
    }

	@Override
	public List<TProcessTaskAssignee> queryMyToDoTasksList(String relationids, TProcessTaskAssignee taskAssignee ,Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin()+page.getLength());
		if(relationids!=null&&!"".equals(relationids)){
			map.put("relationids", relationids);
		}
		if(taskAssignee!=null){
			if(taskAssignee.getPreTaskAssingeeName()!=null && !"".equals(taskAssignee.getPreTaskAssingeeName())){
				map.put("preTaskAssingeeName", taskAssignee.getPreTaskAssingeeName());
			}
			if(taskAssignee.getPreTaskOrgName()!=null && !"".equals(taskAssignee.getPreTaskOrgName())){
				map.put("preTaskOrgName", taskAssignee.getPreTaskOrgName());
			}
			if(taskAssignee.getPreTaskTimeStart()!=null && !"".equals(taskAssignee.getPreTaskTimeStart())){
				map.put("preTaskTimeStart", taskAssignee.getPreTaskTimeStart());
			}
			if(taskAssignee.getPreTaskTimeEnd()!=null && !"".equals(taskAssignee.getPreTaskTimeEnd())){
				map.put("preTaskTimeEnd", taskAssignee.getPreTaskTimeEnd());
			}
			if(taskAssignee.getBusinessType()!=null && !"".equals(taskAssignee.getBusinessType())){
				map.put("businessType", taskAssignee.getBusinessType());
			}
		}
		
		return this.tProcessTaskAssigneeDAO.queryMyToDoTasksList(map,page);
	}

	@Override
	public List<TProcessTaskAssignee> queryMyCompletedTasksList(
			String relationids, String empId,TProcessTaskAssignee taskAssignee, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin()+page.getLength());
		if(relationids!=null&&!"".equals(relationids)){
			map.put("relationids", relationids);
		}
		if(empId!=null&&!"".equals(empId)){
			map.put("empId", empId);
		}
		if(taskAssignee!=null){
			if(taskAssignee.getPreTaskAssingeeName()!=null && !"".equals(taskAssignee.getPreTaskAssingeeName())){
				map.put("preTaskAssingeeName", taskAssignee.getPreTaskAssingeeName());
			}
			if(taskAssignee.getPreTaskOrgName()!=null && !"".equals(taskAssignee.getPreTaskOrgName())){
				map.put("preTaskOrgName", taskAssignee.getPreTaskOrgName());
			}
			if(taskAssignee.getBusinessType()!=null && !"".equals(taskAssignee.getBusinessType())){
				map.put("businessType", taskAssignee.getBusinessType());
			}
			/**
			 * 20140905 已办开始时间
			 */
			if(taskAssignee.getStartTime()!=null && !"".equals(taskAssignee.getStartTime())){
				map.put("startTime", taskAssignee.getStartTime());
			}
			if(taskAssignee.getStartTimeAfter()!=null && !"".equals(taskAssignee.getStartTimeAfter())){
				map.put("startTimeAfter", taskAssignee.getStartTimeAfter());
			}
			/**
			 * 20140905 已办结束时间
			 */
			if(taskAssignee.getEndTime()!=null && !"".equals(taskAssignee.getEndTime())){
				map.put("endTime", taskAssignee.getEndTime());
			}
			if(taskAssignee.getEndTimeAfter()!=null && !"".equals(taskAssignee.getEndTimeAfter())){
				map.put("endTimeAfter", taskAssignee.getEndTimeAfter());
			}
		}
		return this.tProcessTaskAssigneeDAO.queryMyCompletedTasksList(map,page);
	}

	@Override
	public List<TProcessTaskAssignee> queryMyDraftsList(String empId,
			TProcessTaskAssignee taskAssignee, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin()+page.getLength());
		if(empId!=null&&!"".equals(empId)){
			map.put("empId", empId);
		}
		if(taskAssignee != null){
			if(taskAssignee.getProcessName()!=null &&!"".equals(taskAssignee.getProcessName())){
				map.put("processName", taskAssignee.getProcessName());
			}
			if(taskAssignee.getBusinessType()!=null && !"".equals(taskAssignee.getBusinessType())){
				map.put("businessType", taskAssignee.getBusinessType());
			}
		}
		
		return this.tProcessTaskAssigneeDAO.queryMyDraftsList(map,page);
	}
    /*
     *首页督办提醒
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-9-11    黄开忠    新建
     * </pre>
     */
	public List<TProcessTaskAssignee> querySuperviseList(HashMap<String, Object> map,String[] posicode,TProcessTaskAssignee taskAssignee, Page page) {
	    int flag=0;
	    flag=CheckPosiCode(posicode);
	    map.put("flag", flag);
	    return this.tProcessTaskAssigneeDAO.querySuperviseList(map,page);   
	}
    /**
     * 
     *
     * 首页督办提醒,点击更多时使用,更多时,显示字段不一样,需要关联更多的表,为了效率,单独现在写一个过程
     *
     * @param map,包括empid,orgcode
     * @param posicode
     * @param taskAssignee
     * @param page
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-9-11    黄开忠    新建
     * </pre>
     */
	public List<TProcessTaskAssignee>querySuperviseRemMore(HashMap<String, Object> map,String[] posicode,TProcessTaskAssignee taskAssignee, Page page) throws Exception{
	       int flag=0;
	        flag=CheckPosiCode(posicode);
	        map.put("flag", flag);
	        if(taskAssignee==null){
	            taskAssignee=new TProcessTaskAssignee();
	        }
	        if(!(taskAssignee.getPreTaskAssingeeName()==null || taskAssignee.getPreTaskAssingeeName().equals(""))){
	            map.put("preTaskAssingeeName", taskAssignee.getPreTaskAssingeeName());
            }
	        if(!(taskAssignee.getPreTaskOrgName()==null||taskAssignee.getPreTaskOrgName().equals(""))){
	            map.put("preTaskOrgName", taskAssignee.getPreTaskOrgName());
	        }
	        if(!(taskAssignee.getPreTaskTimeStart()==null ||taskAssignee.getPreTaskTimeStart().equals(""))){
	            map.put("preTaskTimeStart", taskAssignee.getPreTaskTimeStart());
	        }
            if(!(taskAssignee.getPreTaskTimeEnd()==null||taskAssignee.getPreTaskTimeEnd().equalsIgnoreCase(""))){
                map.put("preTaskTimeEnd",taskAssignee.getPreTaskTimeEnd() );
            }
	        return this.tProcessTaskAssigneeDAO.querySuperviseRemMore(map,page);   
	}
	/**
	 * 
	 *
	 * 检查当前登录人员所在的角色与数据字典配置的角色是否有相等情况，用来判断督办提醒信息
	 *
	 * @param posicode
	 * @return
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2014-9-11    黄开忠    新建
	 * </pre>
	 */
	private int CheckPosiCode(String[] posicode) {
	       //查询全部，指督办人员      
        String dbry=","+DictManager.getDictName("ZHPT_SUP_SHOW","01");
        //行领导，指查询有勾选提醒的督办
        String hld=","+DictManager.getDictName("ZHPT_SUP_SHOW","02");
        //部门领导,指查询主办与协办是本部门的督办
        String bmld=","+DictManager.getDictName("ZHPT_SUP_SHOW","03");
        //加办人员,指当前处理人是登录人员的
        String dqry="0";
        log.info("bmld="+bmld);
        log.info("dbry="+dbry);
        log.info("hld="+hld);
        try{
            log.info("posicode="+Obj2StrUtils.join(posicode, String.class, ","));
	}catch(Exception e){
	    log.info("");
	}
        int flag=0;//1督办人员，2行领导，3部门领导，0当前人员
        //判断当前人员的岗位是否在数据字典配置的值，
        for(int i=0;i<posicode.length;i++){
            log.info(dbry.indexOf(posicode[i]));
            if(dbry.indexOf(posicode[i])>0){
                flag=1;
                break;
            }
        }
        if(flag==0){
            for(int i=0;i<posicode.length;i++){
                if(bmld.indexOf(posicode[i])>0){
                    flag=2;
                    break;
                }
            }
        }
        if(flag==0){
                for(int i=0;i<posicode.length;i++){
                    if(hld.indexOf(posicode[i])>0){
                        flag=3;
                        break;
                    }
                }
        }
        log.info("flag="+flag);
	    return flag;
	    
	}
	/*
	 *得到当前登录人员督办提醒跟会议提醒
	 * <pre>
	 * 1.得到当前人员对应的岗位并与数据字典配置相比较判断，是得到全部数据还是部分数据
	 * 2.根据条件查询数据
	 * 修改日期        修改人    修改原因
	 * 2014-9-11    黄开忠    新建
	 * </pre>
	 */
	public List<TProcessTaskAssignee> myStartProcessList(Long empId,TProcessTaskAssignee taskAssignee, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin()+page.getLength());
		if(empId!=null){
			map.put("empId", empId);
		}
		if(taskAssignee!=null){
			if(taskAssignee.getPreTaskTimeStart()!=null&&!"".equals(taskAssignee.getPreTaskTimeStart())){
				map.put("submitTime", taskAssignee.getPreTaskTimeStart());
			}
			if(taskAssignee.getPreTaskTimeEnd()!=null&&!"".equals(taskAssignee.getPreTaskTimeEnd())){
				map.put("submitTimeAfter", taskAssignee.getPreTaskTimeEnd());
			}
			if(taskAssignee.getBusinessType()!=null&&!"".equals(taskAssignee.getBusinessType())){
				map.put("businessType", taskAssignee.getBusinessType());
			}
		}
		return tProcessTaskAssigneeDAO.myStartProcessList(map,page);
	}

	@Override
	public List<TProcessTaskAssignee> downexl(String relationids, String empId,
			TProcessTaskAssignee taskAssignee) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(relationids!=null&&!"".equals(relationids)){
			map.put("relationids", relationids);
		}
		if(empId!=null&&!"".equals(empId)){
			map.put("empId", empId);
		}
		if(taskAssignee!=null){
			if(taskAssignee.getPreTaskAssingeeName()!=null && !"".equals(taskAssignee.getPreTaskAssingeeName())){
				map.put("preTaskAssingeeName", taskAssignee.getPreTaskAssingeeName());
			}
			if(taskAssignee.getPreTaskOrgName()!=null && !"".equals(taskAssignee.getPreTaskOrgName())){
				map.put("preTaskOrgName", taskAssignee.getPreTaskOrgName());
			}
			if(taskAssignee.getBusinessType()!=null && !"".equals(taskAssignee.getBusinessType())){
				map.put("businessType", taskAssignee.getBusinessType());
			}
			/**
			 * 20140905 已办开始时间
			 */
			if(taskAssignee.getStartTime()!=null && !"".equals(taskAssignee.getStartTime())){
				map.put("startTime", taskAssignee.getStartTime());
			}
			if(taskAssignee.getStartTimeAfter()!=null && !"".equals(taskAssignee.getStartTimeAfter())){
				map.put("startTimeAfter", taskAssignee.getStartTimeAfter());
			}
			/**
			 * 20140905 已办结束时间
			 */
			if(taskAssignee.getEndTime()!=null && !"".equals(taskAssignee.getEndTime())){
				map.put("endTime", taskAssignee.getEndTime());
			}
			if(taskAssignee.getEndTimeAfter()!=null && !"".equals(taskAssignee.getEndTimeAfter())){
				map.put("endTimeAfter", taskAssignee.getEndTimeAfter());
			}
		}
		return this.tProcessTaskAssigneeDAO.downexl(map);
	}

	@Override
	public List<TProcessTaskAssignee> selectByTaskExeConfigId(Long id,
			String string) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskExeConfigId", id);
		map.put("executionId", string);
		return this.tProcessTaskAssigneeDAO.selectByTaskExeConfigId(map);
	}
}