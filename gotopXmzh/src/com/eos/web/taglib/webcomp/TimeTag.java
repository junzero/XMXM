// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   TimeTag.java

package com.eos.web.taglib.webcomp;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.webcomp:
//            BaseComponentTag

public class TimeTag extends BaseComponentTag
{

    private String maxValue;
    private String minValue;
    private String srcFormat;
    private boolean _allowNull;
    private boolean _defaultNull;
    private String allowNull;
    private String defaultNull;
    private String disabled;
    private String readOnly;
    private Boolean _readOnly;
    private boolean _disabled;

    public TimeTag()
    {
        maxValue = null;
        minValue = null;
        srcFormat = null;
        _allowNull = true;
        _defaultNull = true;
        allowNull = "true";
        defaultNull = "true";
        disabled = "false";
        readOnly = "false";
        _readOnly = Boolean.valueOf(false);
        _disabled = false;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        srcFormat = XpathUtil.getStringByXpathSupport(getRootObj(), srcFormat);
        maxValue = XpathUtil.getStringByXpathSupport(getRootObj(), maxValue);
        minValue = XpathUtil.getStringByXpathSupport(getRootObj(), minValue);
        _disabled = XpathUtil.getBooleanByXpathSupport(getRootObj(), disabled, false);
        _allowNull = XpathUtil.getBooleanByXpathSupport(getRootObj(), allowNull, false);
        _defaultNull = XpathUtil.getBooleanByXpathSupport(getRootObj(), defaultNull, false);
        _readOnly = Boolean.valueOf(XpathUtil.getBooleanByXpathSupport(getRootObj(), readOnly, false));
    }

    protected void prepareAttributes()
    {
        super.prepareAttributes();
        component.setAttribute("srcFormat", srcFormat);
        component.setAttribute("maxValue", maxValue);
        component.setAttribute("minValue", minValue);
        component.setAttribute("defaultNull", (new StringBuilder()).append(_defaultNull).append("").toString());
        component.setAttribute("allowNull", (new StringBuilder()).append(_allowNull).append("").toString());
        if(getPropertyValue() != null)
            component.setValue(getPropertyValue());
        else
            component.setValue(getValue());
        if(_readOnly.booleanValue())
            component.setAttribute("readonly", (new StringBuilder()).append(_readOnly).append("").toString());
        if(is_disabled())
            component.setAttribute("disabled", "true");
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("time", getModelField());
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 1;
    }

    public void release()
    {
        super.release();
        srcFormat = null;
        maxValue = null;
        minValue = null;
        component = null;
        disabled = "false";
        _disabled = false;
    }

    public String getMaxValue()
    {
        return maxValue;
    }

    public void setMaxValue(String maxValue)
    {
        this.maxValue = maxValue;
    }

    public String getMinValue()
    {
        return minValue;
    }

    public void setMinValue(String minValue)
    {
        this.minValue = minValue;
    }

    public String getSrcFormat()
    {
        return srcFormat;
    }

    public void setSrcFormat(String srcFormat)
    {
        this.srcFormat = srcFormat;
    }

    public boolean is_disabled()
    {
        return _disabled;
    }

    public String getDisabled()
    {
        return disabled;
    }

    public void setDisabled(String disabled)
    {
        this.disabled = disabled;
    }

    public String getAllowNull()
    {
        return allowNull;
    }

    public void setAllowNull(String allowNull)
    {
        this.allowNull = allowNull;
    }

    public String getDefaultNull()
    {
        return defaultNull;
    }

    public void setDefaultNull(String defaultNull)
    {
        this.defaultNull = defaultNull;
    }

    public String getReadOnly()
    {
        return readOnly;
    }

    public void setReadOnly(String readOnly)
    {
        this.readOnly = readOnly;
    }
}
