package com.eos.server.dict;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDictType implements DictType {
	private String dictTypeId = null;
	private String dictTypeName = null;
	private int level = 1;
	private DictType parent = null;
	private DictType child = null;
	private List<DictEntry> dictEntries = null;

	public AbstractDictType() {
		this.dictEntries = new ArrayList();
	}

	public AbstractDictType(String dictTypeId) {
		this.dictTypeId = dictTypeId;
		this.dictEntries = new ArrayList();
	}

	public AbstractDictType(String dictTypeId, String dictTypeName) {
		this.dictTypeId = dictTypeId;
		this.dictTypeName = dictTypeName;
		this.dictEntries = new ArrayList();
	}

	public String getDictTypeId() {
		return this.dictTypeId;
	}

	public void setDictTypeId(String dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public String getDictTypeName() {
		return this.dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public DictType getParent() {
		return this.parent;
	}

	public void setParent(DictType parent) {
		this.parent = parent;
	}

	public void setChild(DictType child) {
		this.child = child;
	}

	public DictType getChild() {
		return this.child;
	}

	public List<DictEntry> getDictEntries(String roles) {
//		System.out.println("--------dictTypeName--"+dictTypeName+"----roles-"+roles);
		return this.dictEntries;
	}

	public DictEntry getDictEntryById(String dictId) {
		for (int i = 0; i < this.dictEntries.size(); i++) {
			if (dictId.equals(((DictEntry) this.dictEntries.get(i)).getDictId())) {
				return (DictEntry) this.dictEntries.get(i);
			}
		}
		return null;
	}

	public void setDictEntries(List<DictEntry> dictEntries) {
		this.dictEntries = dictEntries;
	}

	public void addDictEntry(DictEntry dictEntry) {
		this.dictEntries.add(dictEntry);
	}

	public void removeDictEntry(DictEntry dictEntry) {
		this.dictEntries.remove(dictEntry);
	}
}