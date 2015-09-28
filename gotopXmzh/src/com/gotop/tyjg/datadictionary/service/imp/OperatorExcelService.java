package com.gotop.tyjg.datadictionary.service.imp;


import java.io.File;
import java.util.*;

import com.gotop.tyjg.datadictionary.dao.imp.DictEntryDao;
import com.gotop.tyjg.datadictionary.model.DictEntry;
import com.gotop.tyjg.datadictionary.model.DictType;
import com.gotop.tyjg.datadictionary.service.IOperatorExcelService;
import com.gotop.util.export.*;
import com.gotop.util.security.ForUtil;
import com.primeton.utils.Page;
import commonj.sdo.DataObject;


public class OperatorExcelService implements IOperatorExcelService {
    private DictEntryDao dictEntryDao;

	@Override
	public String exportExcel(String fields, DictType dictType, Page page) throws Exception {
		if("".equals(dictType.getDicttypeid())) {
			dictType.setDicttypeid(null);
		}
		if("".equals(dictType.getDicttypename())) {
			dictType.setDicttypename(null);
		}
		List<DictType> dictTypeList = this.getDictEntryDao().queryDictTypeList(dictType, page);
		String exportFile = new JavaCallLogic().createTemplate(fields);
		String downloadFile = ExportExcel.exportExcel(dictTypeList, null, exportFile, false, false, true, null);
		File file = ForUtil.createFile(exportFile);
		file.delete();
		return downloadFile;
	}

	public DictEntryDao getDictEntryDao() {
		return dictEntryDao;
	}
	@Override
	public String importDictEntryExcel(String filePath, String entityType) throws Exception {
		ExcelTemplate template=new ExcelTemplate();
	    List[] dictEntryss = template.importData(filePath, entityType, 5000);
	    List dictEntrys = null;
	    if(dictEntryss.length == 1) {
	    	dictEntrys = dictEntryss[1];
	    }
	    DictEntry dictEntry;
		for(int i=0; dictEntrys!=null && i<dictEntrys.size(); i++) {
			dictEntry = (DictEntry)dictEntrys.get(i);
			if(dictEntryDao.queryDictEntryList(dictEntry).size() == 0) {
				dictEntry.setRank(1L);
				dictEntry.setStatus(1L);
				dictEntry.setSeqno("." + dictEntry.getDictid() + ".");
				dictEntryDao.insertDictEntry(dictEntry);
			}
		}	

		return null;
	}

	@Override
	public String importDictTypeExcel(String filePath, String entityType) throws Exception{
		ExcelTemplate template=new ExcelTemplate();
		List[] dictTypess = template.importData(filePath, entityType, 5000);
		List dictTypes;
		if(dictTypess.length == 1) {
			dictTypes = dictTypess[0];
		}else {
			return "false";
		}
		DictType dictType;
		for(int i=0; i<dictTypes.size(); i++) {
			dictType = (DictType)dictTypes.get(i);
			if(dictEntryDao.queryDictTypeList(dictType).size() == 0) {
				dictType.setRank(1);
				dictType.setSeqno("." + dictType.getDicttypeid() + ".");
				dictEntryDao.insertDictType(dictType);
			}
		}
		return "true";
	}

	@Override
	public String importExcel(String filePath, String fields, String entityType) throws Exception {
		String templateExcel = new JavaCallLogic().importTemplateExcel(fields, filePath);
		ExcelTemplate template=new ExcelTemplate();
		List[] dictTypess = template.importData(templateExcel, entityType, 5000);
		File file = ForUtil.createFile(templateExcel);
		file.delete();
		List dictTypes;
		if(dictTypess.length == 1) {
			dictTypes = dictTypess[0];
		}else {
			return "false";
		}
		DictType dictType;
		for(int i=0; i<dictTypes.size(); i++) {
			dictType = (DictType)dictTypes.get(i);
			if(dictEntryDao.queryDictTypeList(dictType).size() == 0) {
				dictType.setRank(1);
				dictType.setSeqno("." + dictType.getDicttypeid() + ".");
				dictEntryDao.insertDictType(dictType);
			}
		}
		return "true";
	}

	@Override
	public String readExport(String filePath) {
		String str = new JavaCallLogic().readExcel(filePath);
		return str;
	}

	public void setDictEntryDao(DictEntryDao dictEntryDao) {
		this.dictEntryDao = dictEntryDao;
	}
    

}
