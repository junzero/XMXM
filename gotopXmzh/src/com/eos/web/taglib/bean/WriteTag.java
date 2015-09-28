// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   WriteTag.java

package com.eos.web.taglib.bean;

import com.eos.web.Constants;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.*;
import com.primeton.ext.data.vmodel.*;
import com.primeton.web.core.control.util.FormatException;
import com.primeton.web.core.control.util.FormatUtil;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public class WriteTag extends BaseIteratorTagSupport
{

    private static final long serialVersionUID = 0x7d31f02d3568e96L;
    private String propertyType;
    private String modelField;
    private String filter;
    private boolean _filter;
    private boolean _ignore;
    private String ignore;
    private String formatPattern;
    private String srcFormatPattern;
    private String maxLength;
    private int _maxLength;
    private String maxLengthString;

    public WriteTag()
    {
        propertyType = "scope";
        modelField = null;
        filter = null;
        _filter = true;
        _ignore = true;
        ignore = null;
        formatPattern = null;
        srcFormatPattern = null;
        maxLength = null;
        _maxLength = -1;
        maxLengthString = null;
    }

    public void setPropertyType(String propertyType)
    {
        this.propertyType = propertyType;
    }

    public String getPropertyType()
    {
        return propertyType;
    }

    public void setFilter(String filter)
    {
        this.filter = filter;
    }

    public String getFilter()
    {
        return filter;
    }

    public void setIgnore(String ignore)
    {
        this.ignore = ignore;
    }

    public String getIgnore()
    {
        return ignore;
    }

    public void setFormatPattern(String formatPattern)
    {
        this.formatPattern = formatPattern;
    }

    public String getFormatPattern()
    {
        return formatPattern;
    }

    public void setSrcFormatPattern(String srcFormatPattern)
    {
        this.srcFormatPattern = srcFormatPattern;
    }

    public String getSrcFormatPatten()
    {
        return srcFormatPattern;
    }

    public void setMaxLength(String maxLength)
    {
        this.maxLength = maxLength;
    }

    public String getMaxLength()
    {
        return maxLength;
    }

    public void setMaxLengthString(String maxLengthString)
    {
        this.maxLengthString = maxLengthString;
    }

    public String getMaxLengthString()
    {
        return maxLengthString;
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        Object value = getOutput();
        if(value == null)
            if(!_ignore)
                throw new JspException("b:write tag, can not found object !");
            else
                return 0;
        String outPutValue = null;
        if(value instanceof Date)
        {
            if(formatPattern == null)
                formatPattern = DateUtil.getDefaultDisplayDateTimeFormatPattern(pageContext);
            outPutValue = (new SimpleDateFormat(formatPattern)).format(value);
        } else
        if(srcFormatPattern != null)
        {
            if(formatPattern == null)
                formatPattern = DateUtil.getDefaultDisplayDateTimeFormatPattern(pageContext);
            outPutValue = format(value);
        } else
        if(formatPattern != null)
            outPutValue = format(value);
        else
            outPutValue = String.valueOf(value);
        if(_filter)
            outPutValue = StringUtil.htmlFilter(outPutValue);
        if(maxLength != null && outPutValue.length() > _maxLength)
        {
            outPutValue = outPutValue.substring(0, _maxLength);
            outPutValue = (new StringBuilder()).append(outPutValue).append(maxLengthString).toString();
        }
        if(outPutValue != null && !outPutValue.equals("null"))
            ResponseUtil.write(pageContext, outPutValue);
        return 0;
    }

    private String format(Object obj)
    {
        String str = "";
        if("".equals(obj))
            return "";
        try
        {
            if(srcFormatPattern != null)
                str = FormatUtil.format(obj, srcFormatPattern, formatPattern);
            else
                str = FormatUtil.format(obj, formatPattern);
        }
        catch(FormatException fe)
        {
            fe.printStackTrace();
        }
        return str;
    }

    private Object getOutput()
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
            return propertyObject;
        else
            return getValue();
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _ignore = XpathUtil.getBooleanByXpathSupport(getRootObj(), ignore, true, "ignore");
        propertyType = XpathUtil.getStringByXpathSupport(getRootObj(), propertyType);
        if(propertyType != null)
        {
            propertyType = propertyType.toLowerCase();
            Constants.validatePropertyType(propertyType);
        }
        _filter = XpathUtil.getBooleanByXpathSupport(getRootObj(), filter, true, "filter");
        formatPattern = XpathUtil.getStringByXpathSupport(getRootObj(), formatPattern);
        srcFormatPattern = XpathUtil.getStringByXpathSupport(getRootObj(), srcFormatPattern);
        if(maxLength != null)
        {
            if(maxLength.charAt(0) == '-')
                throw new JspException((new StringBuilder()).append("The attribute maxLength:").append(maxLength).append(" is invalid!").toString());
            _maxLength = XpathUtil.getIntByXpathSupport(getRootObj(), maxLength, -1, "maxLength");
        }
        maxLengthString = XpathUtil.getStringByXpathSupport(getRootObj(), maxLengthString, "...");
        modelField = XpathUtil.getStringByXpathSupport(getRootObj(), modelField);
        if(getModelField() != null)
            getAttrFromVielModel();
    }

    public void release()
    {
        super.release();
        propertyType = "scope";
        filter = null;
        _filter = true;
        ignore = null;
        _ignore = true;
        formatPattern = null;
        srcFormatPattern = null;
        maxLength = null;
        _maxLength = -1;
        maxLengthString = null;
        modelField = null;
    }

    private void getAttrFromVielModel()
    {
        Map attrs = new HashMap();
        Field field = ViewModelHelper.getField(getModelField());
        List viewAttrs = field.getView().getAttr();
        ControlAttr viewAttr;
        for(Iterator i$ = viewAttrs.iterator(); i$.hasNext(); attrs.put(viewAttr.getName(), viewAttr.getValue()))
            viewAttr = (ControlAttr)i$.next();

        if(ignore == null)
        {
            String ignoreStr = (String)attrs.get("ignore");
            if(ignoreStr != null)
                _ignore = Boolean.parseBoolean(ignoreStr);
        }
        if(maxLength == null)
        {
            String maxLengthStr = (String)attrs.get("maxLength");
            if(maxLengthStr != null)
            {
                _maxLength = Integer.parseInt(maxLengthStr);
                maxLength = "";
            }
        }
        if(filter == null)
        {
            String filterStr = (String)attrs.get("filter");
            if(filterStr != null)
                _filter = Boolean.parseBoolean(filterStr);
        }
        if(formatPattern == null)
            formatPattern = (String)attrs.get("formatPattern");
        if(srcFormatPattern == null)
            srcFormatPattern = (String)attrs.get("srcFormatPattern");
        if(getValue() == null)
            setValue((String)attrs.get("value"));
        if(maxLengthString == null || maxLengthString.equals("..."))
        {
            String maxLengthStringStr = (String)attrs.get("maxLengthString");
            if(maxLengthStringStr != null)
                maxLengthString = maxLengthStringStr;
        }
    }

    public String getModelField()
    {
        return modelField;
    }

    public void setModelField(String modelField)
    {
        this.modelField = modelField;
    }
}
