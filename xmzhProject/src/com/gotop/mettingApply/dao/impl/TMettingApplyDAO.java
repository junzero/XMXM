package com.gotop.mettingApply.dao.impl;

import com.gotop.mettingApply.dao.ITMettingApplyDAO;
import com.gotop.mettingApply.model.TMettingApply;
import com.gotop.mettingApply.model.TMettingApplyExample;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TMettingApplyDAO extends SqlMapClientDao implements ITMettingApplyDAO {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TMettingApplyDAO.class);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table T_METTING_APPLY
     *
     * @abatorgenerated 
     */
    public TMettingApplyDAO() {
        super();
    }

    /**
     * 插入一条新数据.
     * @abatorgenerated
     */
    public void insert(TMettingApply record) {
        getSqlMapClientTemplate().insert("T_METTING_APPLY_SqlMap.insert_metting_apply_info", record);
    }

    /**
     * 查询列表
     */
	@Override
	public List<TMettingApply> queryMettingAppList(TMettingApply meet, Page page)
			throws Exception {
		return  queryForList("T_METTING_APPLY_SqlMap.query_metting_apply_list", meet, page);
	}

	/**
	 * 修改信息
	 */
	@Override
	public void updateMetingInfo(TMettingApply meet) throws Exception {
	    update("T_METTING_APPLY_SqlMap.update_metting_apply_info", meet);
	}

	/**
	 * 查询信息
	 */
	@Override
	public TMettingApply queryMettingApply(TMettingApply meet) throws Exception {
		
		return (TMettingApply) queryForObject("T_METTING_APPLY_SqlMap.query_metting_apply_info", meet);
	}

	@Override
	public TMettingApply queryMettingApply1(TMettingApply meet)
			throws Exception {
		return (TMettingApply) queryForObject("T_METTING_APPLY_SqlMap.query_metting_apply_info1", meet);
	}

	@Override
	public List<TMettingApply> queryHomeMettingList(TMettingApply meet,
			Page page) {
		return queryForList("T_METTING_APPLY_SqlMap.query_home_metting_list", meet, page);
	}

   

  
}