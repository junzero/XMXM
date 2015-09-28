package com.gotop.deviceManagement.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gotop.deviceManagement.dao.IDeviceManagementDAO;
import com.gotop.deviceManagement.model.DevicePo;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;


/**
 * 
 * @author wujiajun
 *
 */
public class DeviceManagementDAO extends SqlMapClientDao implements IDeviceManagementDAO {
	
/**
 * 已经通过集成类 ，获取到了 访问数据库的实例！  可以获取到对象进行DAO操作
 */
	protected Logger log = Logger.getLogger(DeviceManagementDAO.class);

	
	public DeviceManagementDAO() {
	super();
	// TODO 自动生成的构造函数存根
}


	@Override
	public List deviceList(Map<String, Object> map, Page page) {
		List list = queryForList("T_DEVICE_SqlMap.queryList", map, page);
	      return list;
	}


	@Override
	public DevicePo selectByPrimaryKey(Long deviceId) {
		DevicePo key = new DevicePo();
        key.setDeviceId(deviceId);
        DevicePo record = (DevicePo) queryForObject("T_DEVICE_SqlMap.selectByPrimaryKey", key);
        return record;
	}


	@Override
	public void insert(DevicePo device) {
		getSqlMapClientTemplate().insert("T_DEVICE_SqlMap.insert", device);
	}


	@Override
	public int updateByPrimaryKey(DevicePo device) {
		 int rows = getSqlMapClientTemplate().update("T_DEVICE_SqlMap.updateByPrimaryKey", device);
	        return rows;
	}


	@Override
	public int deleteByPrimaryKey(Long deviceId) {
		DevicePo key = new DevicePo();
        key.setDeviceId(deviceId);
        int rows = getSqlMapClientTemplate().delete("T_DEVICE_SqlMap.deleteByPrimaryKey", key);
        return rows;
	}


	@Override
	public List queryDict(String dicttypeid) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("dicttypeid", dicttypeid);
		List datas= queryForList("T_DEVICE_SqlMap.queryDict", map);
//		HashMap<String, String> datas=new HashMap<String, String>();
	      return datas;
	}
}
