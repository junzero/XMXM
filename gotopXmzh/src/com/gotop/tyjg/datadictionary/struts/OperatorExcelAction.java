package com.gotop.tyjg.datadictionary.struts;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.gotop.crm.util.BaseAction;
import com.gotop.tyjg.datadictionary.model.DictType;
import com.gotop.tyjg.datadictionary.service.IOperatorExcelService;
import com.gotop.util.security.ForUtil;
import com.primeton.utils.Page;

public class OperatorExcelAction extends BaseAction{
    private DictType dictType;
    private String downloadFile;
    private String entityType;
    private String excelBody;
    private String fields;
    private IOperatorExcelService operatorExcelService;
    private String params;
    private File readFile;
	public String exportExcel(){
    	Page page = this.getPage();
    	FileInputStream fis = null;
    	try {
			downloadFile = this.getOperatorExcelService().exportExcel(fields, dictType, page);
			fis = ForUtil.createFileInputStream(downloadFile);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		} finally {
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
    	return "success";
    }

	public DictType getDictType() {
		return dictType;
	}
	
	public String getDownloadFile() {
		return downloadFile;
	}
    public String getEntityType() {
		return entityType;
	}

	public String getExcelBody() {
		return excelBody;
	}

	public String getFields() {
		return fields;
	}

	public IOperatorExcelService getOperatorExcelService() {
		return operatorExcelService;
	}
	
	
    public String getParams() {
		return params;
	}
	public File getReadFile() {
		return readFile;
	}

	public String importExcel(){
		String entityName = entityType.split(":")[1];
    	try {
			String flag = this.getOperatorExcelService().importExcel(readFile.getPath(), fields, entityName);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		readFile();
    	return "success";
    }

	public String readFile() {
		String filePath = this.readFile.getPath();
		System.out.println(filePath);
		this.excelBody = this.getOperatorExcelService().readExport(filePath);
		return "readfile";
	}
    
	public String importDictTypeExcel() throws Exception {
		System.out.println(readFile.getPath());
		String flag = this.getOperatorExcelService().importDictTypeExcel(readFile.getPath(), "com.gotop.tyjg.datadictionary.model.DictType");
		return null;
	}
	public String importDictEntryExcel() throws Exception {
		String flag = this.getOperatorExcelService().importDictEntryExcel(readFile.getPath(), "com.gotop.tyjg.datadictionary.model.DictEntry");
		return null;
	}
	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}

	public void setDownloadFile(String downloadFile) {
		this.downloadFile = downloadFile;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public void setExcelBody(String excelBody) {
		this.excelBody = excelBody;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public void setOperatorExcelService(IOperatorExcelService operatorExcelService) {
		this.operatorExcelService = operatorExcelService;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public void setReadFile(File readFile) {
		this.readFile = readFile;
	}
}
