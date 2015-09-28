package com.gotop.tyjg.datadictionary.service.imp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.gotop.tyjg.datadictionary.model.DictEntry;
import com.gotop.tyjg.datadictionary.model.DictType;
import com.gotop.tyjg.datadictionary.dao.*;
import com.gotop.tyjg.datadictionary.service.IDictEntryService;
import com.primeton.utils.Page;

public class DictEntryService implements IDictEntryService {


	private IDictEntryDao dictEntryDao;

	@Override
	public boolean deleteDictEntry(HashMap object) throws Exception {
		DictEntry dictEntry = new DictEntry();
		System.out.println(object);
		dictEntry.setDicttypeid(object.get("dictEntry.dicttypeid").toString());
		dictEntry.setDictid(object.get("dictEntry.dictid").toString());
		dictEntry.setParentid(object.get("dictEntry.parentid").toString());
		this.getDictEntryDao().deleteDictEntry(dictEntry);
		return true;
	}

	public IDictEntryDao getDictEntryDao() {
		return dictEntryDao;
	}

	@Override
	public boolean insertDictEntry(HashMap object) throws Exception {
		DictEntry dictEntry = new DictEntry();
		dictEntry.setDictid((String)object.get("dictEntry.dictid"));
		dictEntry.setDictname((String)object.get("dictEntry.dictname"));
		dictEntry.setDicttypeid((String)object.get("dictEntry.dicttypeid"));
		if(object.get("dictEntry.parentid")!=null && !"".equals(object.get("dictEntry.parentid"))) {
			dictEntry.setParentid((String)object.get("dictEntry.parentid"));
		}
		if(object.get("dictEntry.sortno")!=null && !"".equals(object.get("dictEntry.sortno"))) {
			dictEntry.setSortno(Long.parseLong(String.valueOf(object.get("dictEntry.sortno"))));
		}
		dictEntry.setStatus(Long.parseLong(String.valueOf(object.get("dictEntry.status"))));
		if(dictEntryDao.queryDictEntryList(dictEntry).size() == 0) {
			dictEntry.setRank(1L);
			dictEntry.setSeqno("." + dictEntry.getDictid() + ".");
			dictEntryDao.insertDictEntry(dictEntry);
		} else {
			return false;
		}
		return true;
	}

	@Override
	public DictEntry queryDictEntry(HashMap object) throws Exception {
		DictEntry dictEntry = new DictEntry();
		dictEntry.setDictid(object.get("dictEntry.dictid").toString());
		dictEntry.setDicttypeid(object.get("dictEntry.dicttypeid").toString());
		if(object.get("dictEntry.parentid") != null && !"".equals(object.get("dictEntry.parentid").toString())) {
			dictEntry.setParentid(object.get("dictEntry.parentid").toString());
		}
		List<DictEntry> dictEntryList = this.getDictEntryDao().queryDictEntryList(dictEntry);
		if(dictEntryList.size() == 1) {
			return dictEntryList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<DictEntry> queryDictEntryList(HashMap object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DictEntry> queryDictEntryList(HashMap object, Page page)
			throws Exception {
		DictEntry dictEntry = new DictEntry();
		dictEntry.setDicttypeid(object.get("dictEntry.dicttypeid").toString());
		if(object.get("dictEntry.parentid")!=null && !"".equals(object.get("dictEntry.parentid"))) {
			dictEntry.setParentid((String)object.get("dictEntry.parentid"));
		}
		int count = dictEntryDao.queryDictEntryCount(dictEntry);
		page.setCount(count);
		List<DictEntry> dictEntryList = this.getDictEntryDao().queryDictEntryList(dictEntry, page);
		return dictEntryList;
	}

	@Override
	public List<DictEntry> queryDictEntryNode(HashMap object) throws Exception {
		DictEntry dictEntry = new DictEntry();
		dictEntry.setDicttypeid(object.get("dicttypeid").toString());
		if(object.get("dictid") != null && !"".equals(object.get("dictid"))) {
			dictEntry.setParentid(object.get("dictid").toString());
		}
		List<DictEntry> dictEntryList = this.getDictEntryDao().queryDictEntryList(dictEntry);
		return dictEntryList;
	}

	@Override
	public List<DictType> queryDictTypeList(DictType dictType) throws Exception {
		return this.queryDictTypeList(dictType);
	}

	@Override
	public List<DictType> queryDictTypeList(HashMap object, Page page) throws Exception {
		DictType dictType = new DictType();
		if(object.get("dictType.dicttypeid") != null) {
			dictType.setDicttypeid(object.get("dictType.dicttypeid").toString());
		}
		if(object.get("dictType.dicttypename") != null) {
			dictType.setDicttypename(object.get("dictType.dicttypename").toString());
		}
		int count = dictEntryDao.queryDictTypeCount(dictType);
		page.setCount(count);
		return this.getDictEntryDao().queryDictTypeList(dictType, page);
	}

	@Override
	public List<DictType> queryDictTypeNode(HashMap object)
			throws Exception {
		DictType dictType = new DictType();
		dictType.setDicttypeid(object.get("dicttypeid").toString());
		List<DictType> dictTypeList = this.getDictEntryDao().queryDictTypeList(dictType);
		return dictTypeList;
	}


	@Override
	public boolean saveDictEntry(HashMap object, HashMap object1) throws Exception {
		List<DictEntry> insertDictEntryList = (List<DictEntry>)object.get("insertEntities");
		List<DictEntry> updateDictEntryList = (List<DictEntry>)object.get("updateEntities");
		List<DictEntry> deleteDictEntryList = (List<DictEntry>)object.get("deleteEntities");
		DictEntry dictEntry = null;
		for(int i=0; i<deleteDictEntryList.size(); i++) {
			dictEntry = deleteDictEntryList.get(i);
			dictEntryDao.deleteDictEntry(dictEntry);
		}
		for(int i=0; i<insertDictEntryList.size(); i++) {
			dictEntry = insertDictEntryList.get(i);
			dictEntry.setDicttypeid(object1.get("dictEntry.dicttypeid").toString());
			if(object1.get("dictEntry.parentid")!=null && !"".equals(object1.get("dictEntry.parentid"))) {
				dictEntry.setParentid((String)object.get("dictEntry.parentid"));
			}
			if(dictEntryDao.queryDictEntryList(dictEntry).size() == 0) {
				dictEntry.setRank(1L);
				dictEntry.setSeqno("." + dictEntry.getDictid() + ".");
				dictEntryDao.insertDictEntry(dictEntry);
			}
		}
		for(int i=0; i<updateDictEntryList.size(); i++) {
			dictEntry = updateDictEntryList.get(i);
			dictEntryDao.updateDictEntry(dictEntry);
		}
		return false;
	}

	@Override
	public boolean saveDictType(HashMap object) throws Exception {
		List<DictType> insertDictTypeList = (List<DictType>)object.get("insertEntities");
		List<DictType> deleteDictTypeList = (List<DictType>)object.get("deleteEntities");
		List<DictType> updateDictTypeList = (List<DictType>)object.get("updateEntities");
		DictType dictType = null;
		DictEntry dictEntry = new DictEntry();
		for(int i=0; i<deleteDictTypeList.size(); i++) {
			dictType = deleteDictTypeList.get(i);
			dictEntry.setDicttypeid(dictType.getDicttypeid());
			dictEntryDao.deleteDictEntry(dictEntry);
			dictEntryDao.deleteDictType(dictType);
		}
		for(int i=0; i<insertDictTypeList.size(); i++) {
			dictType = insertDictTypeList.get(i);
			if(dictEntryDao.queryDictTypeList(dictType).size() == 0) {
				dictType.setRank(1);
				dictType.setSeqno("." + dictType.getDicttypeid() + ".");
				dictEntryDao.insertDictType(dictType);
			}
		}
		for(int i=0; i<updateDictTypeList.size(); i++) {
			dictType = updateDictTypeList.get(i);
			dictEntryDao.updateDictType(dictType);
		}
		return true;
	}

	public void setDictEntryDao(IDictEntryDao dictEntryDao) {
		this.dictEntryDao = dictEntryDao;
	}

	@Override
	public boolean updateDictEntry(HashMap object) throws Exception {
		DictEntry dictEntry = new DictEntry();
		dictEntry.setDictid(object.get("dictEntry.dictid").toString());
		dictEntry.setDictname(object.get("dictEntry.dictname").toString());
		dictEntry.setDicttypeid(object.get("dictEntry.dicttypeid").toString());
		if(object.get("dictEntry.parentid") != null && !"".equals(object.get("dictEntry.parentid"))) {
			dictEntry.setDicttypeid(object.get("dictEntry.parentid").toString());
		}
		
		dictEntry.setSortno(Long.parseLong(String.valueOf(object.get("dictEntry.sortno"))));
		dictEntry.setStatus(Long.parseLong(String.valueOf(object.get("dictEntry.status"))));
		this.getDictEntryDao().updateDictEntry(dictEntry);
		return true;
	}

}
