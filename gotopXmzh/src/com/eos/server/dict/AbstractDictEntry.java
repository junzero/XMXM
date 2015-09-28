package com.eos.server.dict;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDictEntry implements DictEntry {
	private String dictId = null;
	private String dictName = null;
	private int level = 1;
	private DictEntry parent = null;
	private List<DictEntry> children = null;

	public AbstractDictEntry(String dictId, String dictName) {
		this.dictId = dictId;
		this.dictName = dictName;
	}

	public String getDictId() {
		return this.dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public DictEntry getParent() {
		return this.parent;
	}

	public void setParent(DictEntry parent) {
		this.parent = parent;
	}

	public void addChild(DictEntry child) {
		if (this.children == null)
			this.children = new ArrayList();
		this.children.add(child);
	}

	public List<DictEntry> getChildren() {
		return this.children;
	}
}