// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DictTypeImpl.java

package com.eos.server.dict.impl;

import com.eos.server.dict.AbstractDictType;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.eos.server.dict.impl:
//            DictEntryImpl, FilterExpressionUtil

public class DictTypeImpl extends AbstractDictType
{
	private String roles;
	
    public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public DictTypeImpl(String dictTypeId)
    {
        super(dictTypeId);
    }

    public DictTypeImpl(String dictTypeId, String dictTypeName)
    {
        super(dictTypeId, dictTypeName);
    }

    public List getDictEntries(String filterOp, String filterStr,String roles)
    {
        return getDictEntries(filterOp, null, filterStr,roles);
    }

    public List getDictEntries(String filterOp, String filterField, String filterStr,String roles)
    {
        List outlist = new ArrayList();
        List dicts = getDictEntries(roles);
        if(filterOp.equals("="))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.eq(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.equals("!="))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.ne(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.equals("<"))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.lt(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.equals("<="))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.le(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.equals(">"))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.gt(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.equals(">="))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.ge(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.toLowerCase().equals("between"))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.between(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.toLowerCase().equals("!between"))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.notBetween(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.toLowerCase().equals("in"))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.in(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.toLowerCase().equals("!in"))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.notIn(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.toLowerCase().equals("like"))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.like(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.toLowerCase().equals("!like"))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.notLike(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.toLowerCase().equals("match"))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.matches(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        } else
        if(filterOp.toLowerCase().equals("!match"))
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntryImpl dict = (DictEntryImpl)dicts.get(i);
                if(FilterExpressionUtil.notMatches(dict.getFilterValue(filterField), filterStr))
                    outlist.add(dict);
            }

        }
        return outlist;
    }
}
