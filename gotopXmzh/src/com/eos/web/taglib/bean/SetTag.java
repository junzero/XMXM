// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   SetTag.java

package com.eos.web.taglib.bean;

import com.eos.data.xpath.XPathLocator;
import com.eos.web.Constants;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.XpathUtil;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public class SetTag extends BaseIteratorTagSupport
{

    private static final long serialVersionUID = 0x5fc25c5f85b3d2d2L;
    private String name;
    private String propertyType;
    private String toScope;

    public SetTag()
    {
        name = null;
        propertyType = "scope";
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

    public String getPropertyType()
    {
        return propertyType;
    }

    public void setPropertyType(String valueType)
    {
        propertyType = valueType;
    }

    public String getToScope()
    {
        return toScope;
    }

    public void setToScope(String toScope)
    {
        this.toScope = toScope;
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        if(toScope.startsWith("f"))
        {
            throw new JspException("b:set tag unsupport set flowcontext");
        } else
        {
            Object data = getDataSrc();
            setDataDes(data);
            return 0;
        }
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        if(propertyType != null)
        {
            propertyType = XpathUtil.getStringByXpathSupport(getRootObj(), propertyType);
            propertyType = propertyType.toLowerCase();
            Constants.validatePropertyType(propertyType);
        }
        name = XpathUtil.getStringByXpathSupport(getRootObj(), name);
        if(name == null || name.equals(""))
            throw new JspException("b:set tag, property name's value not validate!");
        else
            return;
    }

    private Object getDataSrc()
    {
        Object propertyObject = null;
        if(propertyType.equalsIgnoreCase("scope"))
            propertyObject = getPropertyValue();
        else
        if(propertyType.equalsIgnoreCase("parameter"))
            propertyObject = pageContext.getRequest().getParameter(getProperty());
        else
        if(propertyType.equalsIgnoreCase("cookie"))
        {
            Cookie cookies[] = ((HttpServletRequest)pageContext.getRequest()).getCookies();
            Cookie arr$[] = cookies;
            int len$ = arr$.length;
            int i$ = 0;
            do
            {
                if(i$ >= len$)
                    break;
                Cookie cookie = arr$[i$];
                String name = cookie.getName();
                if(name.equals(getProperty()))
                {
                    propertyObject = cookie.getValue();
                    break;
                }
                i$++;
            } while(true);
        } else
        if(propertyType.equalsIgnoreCase("header"))
            propertyObject = ((HttpServletRequest)pageContext.getRequest()).getHeader(getProperty());
        if(propertyObject != null)
            return String.valueOf(propertyObject);
        else
            return getValue();
    }

    private void setDataDes(Object src)
    {
        Object rootObj = XpathUtil.getDataContextRoot(toScope, pageContext);
        XPathLocator.getInstance().setValue(rootObj, name, src, true);
    }

    public void release()
    {
        super.release();
        name = null;
        propertyType = "scope";
        toScope = "request";
    }
}
