// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BaseDictTag.java

package com.eos.web.taglib.dict;

import com.eos.data.datacontext.DataContextManager;
import com.eos.server.dict.DictManager;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.*;
import com.primeton.web.core.control.WebComponent;
import java.util.Locale;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public abstract class BaseDictTag extends BaseIteratorTagSupport
{

    protected WebComponent component;
    protected String modelField;
    private String dictTypeId;
    private String filter;
    private String locale;
    private boolean _filter;
    private String seperator;

    public BaseDictTag()
    {
        component = null;
        modelField = null;
        dictTypeId = null;
        filter = "true";
        locale = null;
        _filter = true;
        seperator = ",";
    }

    public String getDictTypeId()
    {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId)
    {
        this.dictTypeId = dictTypeId;
    }

    public boolean is_filter()
    {
        return _filter;
    }

    public String getFilter()
    {
        return filter;
    }

    public void setFilter(String filter)
    {
        this.filter = filter;
    }

    public String getSeperator()
    {
        return seperator;
    }

    public void setSeperator(String seperator)
    {
        this.seperator = seperator;
    }

    protected void prepareValue()
    {
        Object tmpValue = getPropertyValue();
        if(tmpValue != null)
            component.setValue(tmpValue);
        else
        if(getValue() != null)
            component.setValue(getValue());
    }

    protected void prepareAttributes()
        throws JspException
    {
        component.setDict(true);
        if(getDictTypeId() != null)
            component.setDictTypeId(getDictTypeId());
        if(DictManager.getDictType(component.getDictTypeId()) == null)
            throw new JspException((new StringBuilder()).append("DictType:").append(component.getDictTypeId()).append(" not exist!").toString());
        prepareValue();
        if(is_filter())
            component.setAttribute("filter", "true");
        if(!getSeperator().equals(","))
            component.setAttribute("seperator", getSeperator());
        component.setAttribute("datacellEditor", "true");
        Locale defaultLocale = null;
        if(locale == null)
        {
//            defaultLocale = DataContextManager.current().getCurrentLocale();
            if(defaultLocale == null)
                defaultLocale = pageContext.getRequest().getLocale();
        } else
        {
            try
            {
                defaultLocale = I18nUtil.getLocale(locale);
            }
            catch(Exception e) { }
        }
        if(defaultLocale!=null){
        	component.setAttribute("locale", defaultLocale.toString());
        }
    }

    public int doEndTag()
        throws JspException
    {
        if(component == null)
        {
            ResponseUtil.write(pageContext, "&nbsp;");
            return 6;
        }
        try
        {
            ResponseUtil.write(pageContext, component.toHtml());
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        release();
        return 6;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        dictTypeId = XpathUtil.getStringByXpathSupport(getRootObj(), dictTypeId);
        seperator = XpathUtil.getStringByXpathSupport(getRootObj(), seperator);
        _filter = XpathUtil.getBooleanByXpathSupport(getRootObj(), filter, true, "filter");
        modelField = XpathUtil.getStringByXpathSupport(getRootObj(), modelField);
        locale = XpathUtil.getStringByXpathSupport(getRootObj(), locale);
    }

    public void release()
    {
        super.release();
        component = null;
        dictTypeId = null;
        filter = "true";
        _filter = true;
        seperator = ",";
        modelField = null;
        locale = null;
    }

    public String getModelField()
    {
        return modelField;
    }

    public void setModelField(String modelField)
    {
        this.modelField = modelField;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }
}
