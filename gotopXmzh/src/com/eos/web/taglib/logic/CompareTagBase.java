// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   CompareTagBase.java

package com.eos.web.taglib.logic;

import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.XpathUtil;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public abstract class CompareTagBase extends BaseTagSupport
{

    private String iterateId;
    private String targetId;
    private String value;
    private String targetValue;
    private String property;
    private String targetProperty;
    private String propertyType;
    private String targetPropertyType;
    private Object targetPropertyValue;
    private Object propertyValue;
    private Object valueObject;
    private Object targetValueObject;

    public CompareTagBase()
    {
        iterateId = null;
        targetId = null;
        value = null;
        targetValue = null;
        property = null;
        targetProperty = null;
        propertyType = "scope";
        targetPropertyType = "scope";
        targetPropertyValue = null;
        propertyValue = null;
        valueObject = null;
        targetValueObject = null;
    }

    public String getIterateId()
    {
        return iterateId;
    }

    public void setIterateId(String iterateId)
    {
        this.iterateId = iterateId;
    }

    public String getProperty()
    {
        return property;
    }

    public void setProperty(String property)
    {
        this.property = property;
    }

    public String getPropertyType()
    {
        return propertyType;
    }

    public void setPropertyType(String propertyType)
    {
        this.propertyType = propertyType;
    }

    public Object getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(Object propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    public String getTargetId()
    {
        return targetId;
    }

    public void setTargetId(String targetId)
    {
        this.targetId = targetId;
    }

    public String getTargetProperty()
    {
        return targetProperty;
    }

    public void setTargetProperty(String targetProperty)
    {
        this.targetProperty = targetProperty;
    }

    public String getTargetPropertyType()
    {
        return targetPropertyType;
    }

    public void setTargetPropertyType(String targetPropertyType)
    {
        this.targetPropertyType = targetPropertyType;
    }

    public Object getTargetPropertyValue()
    {
        return targetPropertyValue;
    }

    public void setTargetPropertyValue(Object targetPropertyValue)
    {
        this.targetPropertyValue = targetPropertyValue;
    }

    public String getTargetValue()
    {
        return targetValue;
    }

    public void setTargetValue(String targetValue)
    {
        this.targetValue = targetValue;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Object initProperty(String id, String property, String propertyType, String attrName)
        throws JspException
    {
        Object output = null;
        if(propertyType.equals("scope"))
        {
            output = initPropertyFromScope(id, property);
            return output;
        }
        if(property == null)
            return null;
        if(propertyType.equals("parameter"))
            output = pageContext.getRequest().getParameter(property);
        else
        if(propertyType.equals("cookie"))
            output = initPropertyFromCookie(property);
        else
        if(propertyType.equals("header"))
            output = ((HttpServletRequest)pageContext.getRequest()).getHeader(property);
        else
            throw new JspException((new StringBuilder()).append("The attribute ").append(attrName).append(":").append(propertyType).append(" is Invalid!").toString());
        return output;
    }

    public Object initPropertyFromScope(String id, String property)
    {
        if(id == null)
            if(property == null)
                return null;
            else
                return XpathUtil.getObjectByXpath(getRootObj(), property);
        Object rootObj = pageContext.getAttribute(id);
        if(property == null)
            return rootObj;
        else
            return XpathUtil.getObjectByXpath(rootObj, property);
    }

    public Object initPropertyFromCookie(String property)
    {
        Cookie cookies[] = ((HttpServletRequest)pageContext.getRequest()).getCookies();
        Cookie arr$[] = cookies;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Cookie cookie = arr$[i$];
            String name = cookie.getName();
            if(name.equals(property))
                return cookie.getValue();
        }

        return null;
    }

    public int doStartTag()
        throws JspException
    {
        return super.doStartTag();
    }

    public void init()
        throws JspException
    {
        initAttributes();
        property = XpathUtil.getStringByXpathSupport(getRootObj(), property);
        targetProperty = XpathUtil.getStringByXpathSupport(getRootObj(), targetProperty);
        propertyType = XpathUtil.getStringByXpathSupport(getRootObj(), propertyType);
        targetPropertyType = XpathUtil.getStringByXpathSupport(getRootObj(), targetPropertyType);
        propertyValue = initProperty(iterateId, property, propertyType, "propertyType");
        targetPropertyValue = initProperty(targetId, targetProperty, targetPropertyType, "targetPropertyType");
        valueObject = XpathUtil.getObjectByXpathSupport(getRootObj(), value);
        targetValueObject = XpathUtil.getObjectByXpathSupport(getRootObj(), targetValue);
    }

    public Object getTargetValueObject()
    {
        return targetValueObject;
    }

    public void setTargetValueObject(Object targetValueObject)
    {
        this.targetValueObject = targetValueObject;
    }

    public Object getValueObject()
    {
        return valueObject;
    }

    public void setValueObject(Object valueObject)
    {
        this.valueObject = valueObject;
    }

    public void release()
    {
        super.release();
        id = null;
        targetId = null;
        value = null;
        targetValue = null;
        property = null;
        targetProperty = null;
        propertyType = "scope";
        targetPropertyType = "scope";
        targetPropertyValue = null;
        propertyValue = null;
        valueObject = null;
        targetValueObject = null;
    }
}
