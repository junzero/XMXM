// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   SizeTag.java

package com.eos.web.taglib.bean;

import com.eos.data.xpath.XPathLocator;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import javax.servlet.jsp.JspException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SizeTag extends BaseIteratorTagSupport
{

    private static final long serialVersionUID = 0x6a06474d0104e1a6L;
    private boolean _ignore;
    private String name;
    private String toScope;
    private String ignore;

    public SizeTag()
    {
        _ignore = true;
        name = null;
        toScope = "request";
        ignore = "true";
    }

    public String getIgnore()
    {
        return ignore;
    }

    public void setIgnore(String ignore)
    {
        this.ignore = ignore;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _ignore = XpathUtil.getBooleanByXpathSupport(getRootObj(), ignore, true, "ignore");
        if(name != null)
        {
            name = XpathUtil.getStringByXpathSupport(getRootObj(), name);
            if(name == null || name.equals(""))
                throw new JspException("b:size tag, property name's value not validate!");
        }
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        Object value = null;
        int size = -1;
        value = getPropertyValue();
        if(value == null)
        {
            if(!_ignore)
                throw new JspException("b:size tag, can not found oject !");
            size = -1;
        } else
        if(value.getClass().isArray())
            size = Array.getLength(value);
        else
        if(value instanceof Map)
            size = ((Map)value).size();
        else
        if(value instanceof Collection)
            size = ((Collection)value).size();
        else
        if(value instanceof Node)
            size = ((Node)value).getChildNodes().getLength();
        else
        if(value instanceof NodeList)
            size = ((NodeList)value).getLength();
        else
            throw new JspException("b:size tag, data type error !");
        if(name != null)
        {
            Object rootObj = XpathUtil.getDataContextRoot(toScope, pageContext);
            XPathLocator.getInstance().setValue(rootObj, name, Integer.valueOf(size), true);
        } else
        {
            ResponseUtil.write(pageContext, String.valueOf(size));
        }
        return 0;
    }

    public void release()
    {
        super.release();
        ignore = "false";
        _ignore = false;
        name = null;
        toScope = "request";
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getToScope()
    {
        return toScope;
    }

    public void setToScope(String toScope)
    {
        this.toScope = toScope;
    }
}
