package com.gotop.util.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportSource {
	
	private String fileName = ""; //对应报表文件路径
	
	private String reportTitle = ""; //报表标题
	
	private List dataSource = new ArrayList();	//数据源
	
	private List subReport = new ArrayList();	//子报表对象
	
	private Map<String,Object> paramMap = new HashMap<String,Object>();

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public List getDataSource() {
		return dataSource;
	}

	public void setDataSource(List dataSource) {
		this.dataSource = dataSource;
	}

	public List getSubReport() {
		return subReport;
	}

	public void setSubReport(List subReport) {
		this.subReport = subReport;
	}

	public Map<String,Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String,Object> paramMap) {
		this.paramMap = paramMap;
	} 
	
}
