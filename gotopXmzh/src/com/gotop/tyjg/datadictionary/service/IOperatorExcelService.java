package com.gotop.tyjg.datadictionary.service;

import java.util.HashMap;

import com.gotop.tyjg.datadictionary.model.DictType;
import com.primeton.utils.Page;

public interface IOperatorExcelService {
      public String readExport(String filePath);
      
      public String importExcel(String filePath, String fields, String entityType) throws Exception;
      
      public String exportExcel(String fields, DictType dictType, Page page) throws Exception;
      
      public String importDictTypeExcel(String filePath, String entityType) throws Exception;
      
      public String importDictEntryExcel(String filePath, String entityType) throws Exception;
}
