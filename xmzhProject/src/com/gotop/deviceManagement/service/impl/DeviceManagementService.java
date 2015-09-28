package com.gotop.deviceManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gotop.deviceManagement.dao.IDeviceManagementDAO;
import com.gotop.deviceManagement.model.DevicePo;
import com.gotop.deviceManagement.service.IDeviceManagementService;
import com.primeton.utils.Page;

public class DeviceManagementService implements IDeviceManagementService{

	protected Logger log = Logger.getLogger(DeviceManagementService.class);
	
	protected IDeviceManagementDAO deviceManagementDAO;
	
	public IDeviceManagementDAO getDeviceManagementDAO() {
		return deviceManagementDAO;
	}

	public void setDeviceManagementDAO(IDeviceManagementDAO deviceManagementDAO) {
		this.deviceManagementDAO = deviceManagementDAO;
	}

	@Override
	public List<DevicePo> deviceList(DevicePo device, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(device != null){
			if( device.getOrgcode() != null && !"".equals(device.getOrgcode())){
				map.put("orgcode", device.getOrgcode());
			}
			if( device.getDeviceName() != null && !"".equals(device.getDeviceName())){
				map.put("deviceName", device.getDeviceName());
			}
			if( device.getDeviceModel() != null && !"".equals(device.getDeviceModel())){
				map.put("deviceModel", device.getDeviceModel());
			}
			if( device.getDeviceState() != null && !"".equals(device.getDeviceState())){
				map.put("deviceState", device.getDeviceState());
			}
			if( device.getMemoryMin() != null && !"".equals(device.getMemoryMin())){
				map.put("memoryMin", device.getMemoryMin());
			}
			if( device.getMemoryMax() != null && !"".equals(device.getMemoryMax())){
				map.put("memoryMax", device.getMemoryMax());
			}
			if( device.getHardDiskMin() != null && !"".equals(device.getHardDiskMin())){
				map.put("hardDiskMin", device.getHardDiskMin());
			}
			if( device.getHardDiskMax() != null && !"".equals(device.getHardDiskMax())){
				map.put("hardDiskMax", device.getHardDiskMax());
			}
			if( device.getOsVersion() != null && !"".equals(device.getOsVersion())){
				map.put("osVersion", device.getOsVersion());
			}
			if( device.getSoftwareVersion() != null && !"".equals(device.getSoftwareVersion())){
				map.put("softwareVersion", device.getSoftwareVersion());
			}
			if( device.getIeVersion() != null && !"".equals(device.getIeVersion())){
				map.put("ieVersion", device.getIeVersion());
			}
			if( device.getUseful() != null && !"".equals(device.getUseful())){
				map.put("useful", device.getUseful());
			}
			if( device.getPlugIn() != null && !"".equals(device.getPlugIn())){
				map.put("plugIn", device.getPlugIn());
			}
			if( device.getPeripheral() != null && !"".equals(device.getPeripheral())){
				map.put("peripheral", device.getPeripheral());
			}
		}
		
		List list = deviceManagementDAO.deviceList(map, page);
        return list;
	}

	@Override
	public DevicePo getDeviceByDeviceId(DevicePo device) {
		DevicePo devicePo = deviceManagementDAO.selectByPrimaryKey(device.getDeviceId());
		return devicePo;
	}

	@Override
	public void save(DevicePo device) {
		if(device.getDeviceId() == null){
			device.setDeviceState("0"); //新增设备时默认设备状态为可用（即为0）
			deviceManagementDAO.insert(device);
		}else{
			deviceManagementDAO.updateByPrimaryKey(device);
		}
		
	}

	@Override
	public void delete(DevicePo device) {
		deviceManagementDAO.deleteByPrimaryKey(device.getDeviceId());
	}

	@Override
	public List queryDict(String dicttypeid) {
		List datas= deviceManagementDAO.queryDict(dicttypeid);
		return datas;
	}
	
}
