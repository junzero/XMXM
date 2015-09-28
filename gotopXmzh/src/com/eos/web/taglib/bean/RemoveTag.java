// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   RemoveTag.java

package com.eos.web.taglib.bean;

import com.eos.data.xpath.XPathLocator;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.XpathUtil;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public class RemoveTag extends BaseIteratorTagSupport
{

    private String ignore;
    private boolean _ignore;

    public RemoveTag()
    {
        ignore = "true";
        _ignore = true;
    }

    public String isIgnore()
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
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        if(getScope().startsWith("f"))
            throw new JspException("b:remove tag ,unsupport remove flow context");
        Object value = getPropertyValue();
        if(value == null)
        {
            if(!_ignore)
                throw new JspException("b:remove tag, can not found oject !");
        } else
        {
            try
            {
                if(getIterateId() != null)
                    XPathLocator.getInstance().deleteValue(pageContext.getAttribute(getIterateId()), getProperty());
                else
                    XPathLocator.getInstance().deleteValue(getRootObj(), getProperty());
            }
            catch(Exception e)
            {
                throw new JspException("b:remove tag,remove object exception !");
            }
        }
        return 0;
    }

    public void release()
    {
        super.release();
        ignore = "true";
    }
}
