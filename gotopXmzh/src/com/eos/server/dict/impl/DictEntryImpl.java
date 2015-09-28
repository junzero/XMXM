// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DictEntryImpl.java

package com.eos.server.dict.impl;

import com.eos.server.dict.AbstractDictEntry;
import com.eos.server.dict.DictType;

public class DictEntryImpl extends AbstractDictEntry
{

    private String filter1;
    private String filter2;
    private DictType dictType;

    public DictEntryImpl(String dictId, String dictName)
    {
        super(dictId, dictName);
        filter1 = null;
        filter2 = null;
    }

    public String getFilter1()
    {
        return filter1;
    }

    public void setFilter1(String filter1)
    {
        this.filter1 = filter1;
    }

    public String getFilter2()
    {
        return filter2;
    }

    public void setFilter2(String filter2)
    {
        this.filter2 = filter2;
    }

    public DictType getDictType() {
		return dictType;
	}

	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}

	public String getFilterValue(String filterField)
    {
        if(filterField == null || filterField.toLowerCase().equals("dictid"))
            return getDictId();
        if(filterField.toLowerCase().equals("filter1"))
            return getFilter1();
        if(filterField.toLowerCase().equals("filter2"))
            return getFilter2();
        if(filterField.toLowerCase().equals("dictname"))
            return getDictName();
        else
            return null;
    }
}
