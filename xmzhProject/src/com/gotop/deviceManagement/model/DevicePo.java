package com.gotop.deviceManagement.model;

import java.io.Serializable;

public class DevicePo  implements  Serializable{
	
	/**
	 * @author wujiajun
	 */
	private static final long serialVersionUID = 1L;
	
	//设备ID
	private Long deviceId;
	
	//设备名称
	private String deviceName;
	//设备型号
	private String deviceModel;
	//ip地址
	private String ipAdress;
	//生产机器名称
	private   String productionMachineName;
	//cpu型号
	private  String  cpuCode;
	//内存容量
	private  String memory ;
	//硬盘容量
	private String hardDisk;
	//操作系统版本
	private String osVersion;
	//内置软件版本
	private  String  softwareVersion;
	//ie版本
	private  String ieVersion;
	//用途
	private   String useful;
	//终端号
	private   String  terminalNumber;
	
	//使用人
	private String  user;
	//安装的插件（多个）
	private String plugIn;
	//对应的外设
	private String peripheral;
	
	//其他属性1
	private  String otherOne;
	//备注1
	private  String remarksOne;

	//机构部门名称
	private  String orgname;
	//机构部门代码
	private  String orgcode;
	
	//设备状态
	private  String deviceState;
	
	//内存容量 最小值
	private  String  memoryMin ;
	//内存容量 最大值
	private  String  memoryMax ;
	//硬盘容量 最小值
	private String hardDiskMin;	
	//硬盘容量 最大值
	private String hardDiskMax;
	
	
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public String getProductionMachineName() {
		return productionMachineName;
	}
	public void setProductionMachineName(String productionMachineName) {
		this.productionMachineName = productionMachineName;
	}
	public String getCpuCode() {
		return cpuCode;
	}
	public void setCpuCode(String cpuCode) {
		this.cpuCode = cpuCode;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getHardDisk() {
		return hardDisk;
	}
	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	public String getIeVersion() {
		return ieVersion;
	}
	public void setIeVersion(String ieVersion) {
		this.ieVersion = ieVersion;
	}
	public String getUseful() {
		return useful;
	}
	public void setUseful(String useful) {
		this.useful = useful;
	}
	public String getTerminalNumber() {
		return terminalNumber;
	}
	public void setTerminalNumber(String terminalNumber) {
		this.terminalNumber = terminalNumber;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPlugIn() {
		return plugIn;
	}
	public void setPlugIn(String plugIn) {
		this.plugIn = plugIn;
	}
	public String getPeripheral() {
		return peripheral;
	}
	public void setPeripheral(String peripheral) {
		this.peripheral = peripheral;
	}
	public String getOtherOne() {
		return otherOne;
	}
	public void setOtherOne(String otherOne) {
		this.otherOne = otherOne;
	}
	
	public String getRemarksOne() {
		return remarksOne;
	}
	public void setRemarksOne(String remarksOne) {
		this.remarksOne = remarksOne;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getDeviceState() {
		return deviceState;
	}
	public void setDeviceState(String deviceState) {
		this.deviceState = deviceState;
	}
	public String getMemoryMin() {
		return memoryMin;
	}
	public void setMemoryMin(String memoryMin) {
		this.memoryMin = memoryMin;
	}
	public String getMemoryMax() {
		return memoryMax;
	}
	public void setMemoryMax(String memoryMax) {
		this.memoryMax = memoryMax;
	}
	public String getHardDiskMin() {
		return hardDiskMin;
	}
	public void setHardDiskMin(String hardDiskMin) {
		this.hardDiskMin = hardDiskMin;
	}
	public String getHardDiskMax() {
		return hardDiskMax;
	}
	public void setHardDiskMax(String hardDiskMax) {
		this.hardDiskMax = hardDiskMax;
	}

	
}
