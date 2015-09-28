package com.gotop.deviceManagement.action;

import java.util.HashMap;
import java.util.List;

import com.gotop.crm.util.BaseAction;
import com.gotop.dataUser.service.ITRangeUserService;
import com.gotop.deviceManagement.model.DevicePo;
import com.gotop.deviceManagement.service.IDeviceManagementService;
import com.gotop.util.Struts2Utils;
import com.gotop.vo.system.MUOUserSession;

public class DeviceManagementAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	private DevicePo device;
	private List<DevicePo> devices;
	private List usefuls;
	protected IDeviceManagementService deviceManagermentService;
	public DevicePo getDevice() {
		return device;
	}

	public void setDevice(DevicePo device) {
		this.device = device;
	}

	public List<DevicePo> getDevices() {
		return devices;
	}

	public void setDevices(List<DevicePo> devices) {
		this.devices = devices;
	}

	public IDeviceManagementService getDeviceManagermentService() {
		return deviceManagermentService;
	}

	public void setDeviceManagermentService(
			IDeviceManagementService deviceManagermentService) {
		this.deviceManagermentService = deviceManagermentService;
	}

	public String deviceList(){
//		String dicttypeid = "DEVICE_USEFUL";
//		usefuls = deviceManagermentService.queryDict(dicttypeid);
		
    	if(device == null){
    		device = new DevicePo();
    	}
    	devices = deviceManagermentService.deviceList(device,this.getPage());
    	this.setDevices(devices);
    	return "deviceList";
    }
	
	public String toDevice(){
    	if(device != null){
    		device = deviceManagermentService.getDeviceByDeviceId(device);
    	}
    	this.setDevice(device);
    	return "device";
    }
	
	public void save() throws Exception{
    	String info ="success";
    	try {
    		this.deviceManagermentService.save(device);
    	} catch (Exception e) {
			info="fails";
			log.error("[保存设备信息失败！]", e);
			throw e;
		}finally{	
		}
		Struts2Utils.renderText(info);
    }
	
	public void delete() throws Exception{
    	String info ="success";
    	try {
    		this.deviceManagermentService.delete(device);
    	} catch (Exception e) {
			info="fails";
			log.error("[删除设备信息失败！]", e);
			throw e;
		}finally{	
		}
		Struts2Utils.renderText(info);
    }

	public List getUsefuls() {
		return usefuls;
	}

	public void setUsefuls(List usefuls) {
		this.usefuls = usefuls;
	}

	
	
}
