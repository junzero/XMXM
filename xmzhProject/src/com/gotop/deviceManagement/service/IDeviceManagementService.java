package com.gotop.deviceManagement.service;

import java.util.HashMap;
import java.util.List;

import com.gotop.deviceManagement.dao.IDeviceManagementDAO;
import com.gotop.deviceManagement.model.DevicePo;
import com.primeton.utils.Page;

public interface IDeviceManagementService {

	IDeviceManagementDAO getDeviceManagementDAO();

	void setDeviceManagementDAO(IDeviceManagementDAO deviceManagementDAO);
	
	List<DevicePo> deviceList(DevicePo device, Page page);

	DevicePo getDeviceByDeviceId(DevicePo device);

	void save(DevicePo device);

	void delete(DevicePo device);

	List queryDict(String dicttypeid);


}
