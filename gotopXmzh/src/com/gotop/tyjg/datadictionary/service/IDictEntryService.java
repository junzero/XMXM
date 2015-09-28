package com.gotop.tyjg.datadictionary.service;

import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.datadictionary.model.*;
import com.primeton.utils.Page;

public interface IDictEntryService {
	
	public boolean saveDictType(HashMap object) throws Exception;
	
	public boolean saveDictEntry(HashMap object,HashMap object1) throws Exception;
	
	public List<DictType> queryDictTypeList(HashMap object, Page page) throws Exception;
	
	public List<DictType> queryDictTypeList(DictType dictType) throws Exception;
	
	public List<DictType> queryDictTypeNode(HashMap object) throws Exception;
	
	public List<DictEntry> queryDictEntryNode(HashMap object) throws Exception;
	
	public List<DictEntry> queryDictEntryList(HashMap object, Page page) throws Exception;
	
	public List<DictEntry> queryDictEntryList(HashMap object) throws Exception;
	
	public DictEntry queryDictEntry(HashMap object) throws Exception;
	
	public boolean insertDictEntry(HashMap object) throws Exception;
	
	public boolean updateDictEntry(HashMap object) throws Exception;
	
	public boolean deleteDictEntry(HashMap object) throws Exception;
}
