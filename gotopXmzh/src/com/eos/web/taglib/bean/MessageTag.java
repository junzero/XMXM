// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   MessageTag.java

package com.eos.web.taglib.bean;

import com.eos.data.datacontext.DataContextManager;
import com.eos.web.exception.EOSTagExceptionUtil;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.*;
import com.primeton.ext.system.utility.international.ResourceMessageUtil;
import java.text.MessageFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public class MessageTag extends BaseIteratorTagSupport
{

    private String key;
    private String ignore;
    private boolean _ignore;
    private String locale;
    private Locale defaultLocale;
    List list;

    public MessageTag()
    {
        key = null;
        ignore = "true";
        _ignore = true;
        locale = null;
        defaultLocale = null;
        list = null;
    }

    public String isIgnore()
    {
        return ignore;
    }

    public void setIgnore(String ignore)
    {
        this.ignore = ignore;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _ignore = Boolean.parseBoolean(ignore);
        key = XpathUtil.getStringByXpathSupport(getRootObj(), key);
        locale = XpathUtil.getStringByXpathSupport(getRootObj(), locale);
    }

    public void addParameter(String value)
    {
        if(list == null)
            list = new ArrayList();
        if(value != null)
            list.add(value);
    }

    public int doEndTag()
        throws JspException
    {
        String message = null;
        initAttributes();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        try
        {
            if(locale == null)
            {
                defaultLocale = DataContextManager.current().getCurrentLocale();
                if(defaultLocale == null)
                    defaultLocale = request.getLocale();
            } else
            {
                defaultLocale = I18nUtil.getLocale(locale);
            }
            message = ResourceMessageUtil.getI18nResourceMessage(key, defaultLocale);
            if(list != null && list.size() > 0)
            {
                Object args[] = list.toArray();
                message = MessageFormat.format(message, args);
            }
            if(message == null)
                if(_ignore)
                    message = "";
                else
                    EOSTagExceptionUtil.throwEOSTagException("17030021", (new StringBuilder()).append("b:message tag, can not found resource of !").append(key).toString());
        }
        catch(Exception e)
        {
            if(!_ignore)
                EOSTagExceptionUtil.throwEOSTagException("17030022", "b:message tag, can not found resource !");
        }
        ResponseUtil.write(pageContext, message);
        list = null;
        return 1;
    }

    public void release()
    {
        super.release();
        _ignore = true;
        ignore = "true";
        key = null;
        locale = null;
        defaultLocale = null;
    }
}
