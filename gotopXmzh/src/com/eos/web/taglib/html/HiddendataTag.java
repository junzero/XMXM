// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   HiddendataTag.java

package com.eos.web.taglib.html;

import com.eos.web.exception.EOSTagExceptionUtil;
import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.jsp.JspException;

public class HiddendataTag extends BaseTagSupport
{

    private static final long serialVersionUID = 0xa0c4b397dbcb556cL;
    private String property;
    private String name;
    private Object propertyValue;
    private String excludeProp;
    private String allowType;
    private boolean _allowType;

    public HiddendataTag()
    {
        property = null;
        name = null;
        propertyValue = null;
        excludeProp = null;
        allowType = "false";
        _allowType = false;
    }

    public Object getPropertyValue()
    {
        return propertyValue;
    }

    public String getProperty()
    {
        return property;
    }

    public void setProperty(String property)
    {
        this.property = property;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        property = XpathUtil.getStringByXpathSupport(getRootObj(), property);
        name = XpathUtil.getStringByXpathSupport(getRootObj(), name);
        propertyValue = XpathUtil.getObjectByXpath(getRootObj(), property);
        excludeProp = XpathUtil.getStringByXpathSupport(getRootObj(), excludeProp);
        _allowType = XpathUtil.getBooleanByXpathSupport(getRootObj(), allowType, false, "allowType");
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        StringBuilder buff = new StringBuilder();
        if(propertyValue != null)
        {
            String submitName = name;
            if(submitName == null)
                submitName = property;
            try
            {
                Map dataMap = XpathHelper.Object2Map(propertyValue, submitName);
                Set dataSet = dataMap.entrySet();
                Iterator i$ = dataSet.iterator();
                do
                {
                    if(!i$.hasNext())
                        break;
                    java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
                    String key = (String)entry.getKey();
                    if(_allowType || !key.endsWith("__type"))
                        if(excludeProp != null)
                        {
                            String propEx = (new StringBuilder()).append(',').append(excludeProp).append(',').toString();
                            String xpath = name;
                            if(xpath == null)
                                xpath = property;
                            if(xpath != null && key.length() > xpath.length())
                                key = key.substring(xpath.length());
                            int startPos = 0;
                            int endPos = key.length();
                            if(key.startsWith("/"))
                            {
                                startPos = 1;
                                endPos = key.indexOf("/", 1);
                            } else
                            {
                                endPos = key.indexOf("/");
                            }
                            String rootKey = null;
                            if(endPos > 0)
                                rootKey = (new StringBuilder()).append(',').append(key.substring(startPos, endPos)).append(',').toString();
                            else
                                rootKey = (new StringBuilder()).append(',').append(key.substring(startPos)).append(',').toString();
                            if(propEx.indexOf(rootKey) <= -1)
                            {
                                Object value = entry.getValue();
                                String keyStr = (String)entry.getKey();
                                if(value instanceof Date)
                                {
                                    String formatPattern = DateUtil.getDefaultDisplayDateTimeFormatPattern(pageContext);
                                    value = (new SimpleDateFormat(formatPattern)).format(value);
                                }
                                if(keyStr.indexOf("/")>-1){
                                	keyStr = keyStr.replace("/", ".");
                                }
                                buff.append("<input type=\"hidden\" name=\"").append(keyStr).append("\" value=\"").append(value).append("\"/>");
                            }
                        } else
                        {
                            Object value = entry.getValue();
                            String keyStr = (String)entry.getKey();
                            if(value instanceof Date)
                            {
                                String formatPattern = DateUtil.getDefaultDisplayDateTimeFormatPattern(pageContext);
                                value = (new SimpleDateFormat(formatPattern)).format(value);
                            }
                            if(keyStr.indexOf("/")>-1){
                            	keyStr = keyStr.replace("/", ".");
                            }
                            buff.append("<input type=\"hidden\" name=\"").append(keyStr).append("\" value=\"").append(value).append("\"/>");
                        }
                } while(true);
            }
            catch(Exception e)
            {
                EOSTagExceptionUtil.throwEOSTagException("17030203", "h:hiddendata object does not dataobject !");
            }
        }
        ResponseUtil.write(pageContext, buff.toString());
        buff = null;
        return 0;
    }

    public void release()
    {
        super.release();
        property = null;
        propertyValue = null;
        name = null;
        allowType = "false";
        _allowType = false;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getExcludeProp()
    {
        return excludeProp;
    }

    public void setExcludeProp(String excludeProp)
    {
        this.excludeProp = excludeProp;
    }

    public String getAllowType()
    {
        return allowType;
    }

    public void setAllowType(String allowType)
    {
        this.allowType = allowType;
    }
}
