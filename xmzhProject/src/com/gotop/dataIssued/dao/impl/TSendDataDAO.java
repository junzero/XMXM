package com.gotop.dataIssued.dao.impl;

import com.gotop.dataIssued.dao.ITSendDataDAO;
import com.gotop.dataIssued.model.TSendData;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TSendDataDAO extends SqlMapClientDao implements ITSendDataDAO {

	/**
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(TSendDataDAO.class);

	@Override
	public Long insert(TSendData sendData) throws Exception {
		// TODO 自动生成的方法存根
		return (Long) insert("T_SEND_DATA_SqlMap.insertSendData", sendData);
	}

	@Override
	public TSendData getIssuedById(Long businessKey) throws Exception {
		// TODO 自动生成的方法存根
		return (TSendData) queryForObject("T_SEND_DATA_SqlMap.selectByPrimaryKey",businessKey);
	}

	@Override
	public int update(TSendData sendData) throws Exception {
		// TODO 自动生成的方法存根
		return update("T_SEND_DATA_SqlMap.updateByPrimaryKey",sendData);
	}

	@Override
	public List<TSendData> queryIssuedByEmpId(Page page, HashMap<String, Object> map) throws Exception {
		if(page!=null)
			return queryForList("T_SEND_DATA_SqlMap.queryIssuedByEmpId", map,page);
		else
			return queryForList("T_SEND_DATA_SqlMap.queryIssuedByEmpId", map);
	}

	@Override
	public TSendData getIssuedByFlowId(String executionId) throws Exception {
		// TODO 自动生成的方法存根
		return (TSendData) queryForObject("T_SEND_DATA_SqlMap.getIssuedByFlowId",executionId);
	}

	@Override
	public TSendData selectForDown(Long resourceId, Long userId) {
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("resourceId", resourceId);
		paraMap.put("userId", userId);
		TSendData sendData =  (TSendData) queryForObject("T_SEND_DATA_SqlMap.selectForDown",paraMap);
		if(sendData.getDesId()!=null && !"".equals(sendData.getDesId())){
			String empid = sendData.getDesId();
			HashMap map = (HashMap) queryForObject("T_SEND_DATA_SqlMap.selectDesname",empid);
			sendData.setDesName((String) map.get("EMPNAME"));
		}
		return sendData;
	}
   
}