package com.gotop.tyjg.datadictionary.dao.imp;

import java.util.List;

import com.gotop.util.dataSource.SqlMapClientDao;

import com.gotop.tyjg.datadictionary.dao.IDictEntryDao;
import com.gotop.tyjg.datadictionary.model.DictEntry;
import com.gotop.tyjg.datadictionary.model.DictType;
import com.primeton.utils.Page;

public class DictEntryDao extends SqlMapClientDao implements IDictEntryDao {

	@Override
	public boolean deleteDictEntry(DictEntry dictEntry) throws Exception {
		this.delete("DATADICTIONARY.delete_dict_entry", dictEntry);
		return false;
	}

	@Override
	public boolean insertDictEntry(DictEntry dictEntry) throws Exception {
		this.insert("DATADICTIONARY.insert_dict_entry", dictEntry);
		return false;
	}

	@Override
	public List<DictEntry> queryDictEntryList(DictEntry dictEntry, Page page)
			throws Exception {
		List<DictEntry> dictEntryList = (List<DictEntry>)this.queryForList("DATADICTIONARY.query_dict_entry_list_page", dictEntry, page.getBegin(), page.getLength());
		return dictEntryList;
	}
	@Override
	public boolean updateDictEntry(DictEntry dictEntry) throws Exception {
		this.insert("DATADICTIONARY.update_dict_entry", dictEntry);
		return false;
	}

	@Override
	public int queryDictEntryCount(DictEntry dictEntry) throws Exception {
		return (Integer)this.queryForObject("DATADICTIONARY.query_dict_entry_count",dictEntry);
		
	}

	@Override
	public List<DictEntry> queryDictEntryList(DictEntry dictEntry)
			throws Exception {
		List<DictEntry> dictEntryList = (List<DictEntry>)this.queryForList("DATADICTIONARY.query_dict_entry_list", dictEntry);
		return dictEntryList;
	}

	@Override
	public int queryDictTypeCount(DictType dictType) throws Exception {
		return (Integer)this.queryForObject("DATADICTIONARY.query_dict_type_count",dictType);
		
	}

	@Override
	public List<DictType> queryDictTypeList(DictType dictType, Page page)
			throws Exception {
		List<DictType> dictTypeList = (List<DictType>)this.queryForList("DATADICTIONARY.query_dict_type_list_page", dictType, page.getBegin(), page.getLength());
		return dictTypeList;
	}

	@Override
	public List<DictType> queryDictTypeList(DictType dictType) throws Exception {
		return (List<DictType>)this.queryForList("DATADICTIONARY.query_dict_type_list", dictType);
	}

	@Override
	public boolean deleteDictType(DictType dictType) throws Exception {
		int flag = 0;
		flag = this.delete("DATADICTIONARY.delete_dict_type", dictType);
		if(flag == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean insertDictType(DictType dictType) throws Exception {
		this.insert("DATADICTIONARY.insert_dict_type", dictType);
		return true;
	}

	@Override
	public boolean updateDictType(DictType dictType) throws Exception {
		int flag = 0;
		flag = this.update("DATADICTIONARY.update_dict_type", dictType);
		if(flag == 1) {
			return true;
		}else {
			return false;
		}
	}


}
