// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DateTag.java

package com.eos.web.taglib.webcomp;

import com.eos.web.taglib.html.BaseInputTag;
import com.eos.web.taglib.util.DateUtil;
import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

public class DateTag extends BaseInputTag
{

    private static final long serialVersionUID = 1L;
    private String format;
    private String srcFormat;
    private String submitFormat;
    private String maxValue;
    private String minValue;
    private String defaultNull;
    private boolean _defaultNull;
    private String onSelectFunc;
    private String allowNull;
    private boolean _allowNull;
    private String readOnly;
    private String allowInput;
    private Boolean _allowInput;
    private Boolean _readOnly;
    private String width;
    private String inDatacell;
    private String addMm;
    private String addDd;
    private String addSs;

    public DateTag()
    {
        format = null;
        srcFormat = null;
        submitFormat = null;
        maxValue = null;
        minValue = null;
        defaultNull = null;
        _defaultNull = false;
        onSelectFunc = null;
        allowNull = "true";
        _allowNull = true;
        readOnly = "false";
        allowInput = "true";
        _allowInput = Boolean.valueOf(true);
        _readOnly = Boolean.valueOf(false);
        width = null;
        addMm = null;
        addDd = null;
        addSs = null;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public boolean is_allowNull()
    {
        return _allowNull;
    }

    public String getAllowNull()
    {
        return allowNull;
    }

    public void setAllowNull(String allowNull)
    {
        this.allowNull = allowNull;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        if(format == null)
            format = DateUtil.getDefaultDisplayDateFormatPattern(pageContext);
        format = XpathUtil.getStringByXpathSupport(getRootObj(), format);
        srcFormat = XpathUtil.getStringByXpathSupport(getRootObj(), srcFormat);
        maxValue = XpathUtil.getStringByXpathSupport(getRootObj(), maxValue);
        minValue = XpathUtil.getStringByXpathSupport(getRootObj(), minValue);
        onSelectFunc = XpathUtil.getStringByXpathSupport(getRootObj(), onSelectFunc);
        submitFormat = XpathUtil.getStringByXpathSupport(getRootObj(), submitFormat);
        _defaultNull = XpathUtil.getBooleanByXpathSupport(getRootObj(), defaultNull, false);
        _allowNull = XpathUtil.getBooleanByXpathSupport(getRootObj(), allowNull, false);
        _allowInput = Boolean.valueOf(XpathUtil.getBooleanByXpathSupport(getRootObj(), allowInput, true));
        _readOnly = Boolean.valueOf(XpathUtil.getBooleanByXpathSupport(getRootObj(), readOnly, false));
        width = XpathUtil.getStringByXpathSupport(getRootObj(), width);
        inDatacell = XpathUtil.getStringByXpathSupport(getRootObj(), inDatacell);
        
        addMm = XpathUtil.getStringByXpathSupport(getRootObj(), addMm);
        addDd = XpathUtil.getStringByXpathSupport(getRootObj(), addDd);
        addSs = XpathUtil.getStringByXpathSupport(getRootObj(), addSs);
        
    }

    protected void prepareAttributes()
    {
        super.prepareAttributes();
        component.setAttribute("tagformat", format);
        component.setAttribute("srcFormat", srcFormat);
        component.setAttribute("maxValue", maxValue);
        component.setAttribute("minValue", minValue);
        component.setAttribute("onSelectFunc", onSelectFunc);
        component.setAttribute("width", width);
        component.setAttribute("submitFormat", submitFormat);
    	component.setAttribute("inDatacell", inDatacell);
        component.setAttribute("allowNull", (new StringBuilder()).append(allowNull).append("").toString());
        component.setAttribute("readOnly", (new StringBuilder()).append(_readOnly).append("").toString());
        if(!_allowInput.booleanValue())
            component.setAttribute("readonly", "true");
        if(defaultNull != null)
            if(is_defaultNull())
                component.setAttribute("defaultNull", "true");
            else
                component.setAttribute("defaultNull", "false");
        
        component.setAttribute("addMm", addMm);
        component.setAttribute("addDd", addDd);
        component.setAttribute("addSs", addSs);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        if(format == null)
            format = DateUtil.getDefaultDisplayDateFormatPattern(pageContext);
        try
        {
            component = ComponentFactory.createWebComponent("date", getModelField());
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 1;
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

    public String getFormat()
    {
        return format;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }

    public String getSrcFormat()
    {
        return srcFormat;
    }

    public void setSrcFormat(String srcFormat)
    {
        this.srcFormat = srcFormat;
    }

    public boolean is_defaultNull()
    {
        return _defaultNull;
    }

    public String getDefaultNull()
    {
        return defaultNull;
    }

    public void setDefaultNull(String defaultNull)
    {
        this.defaultNull = defaultNull;
    }

    public String getOnSelectFunc()
    {
        return onSelectFunc;
    }

    public void setOnSelectFunc(String onSelectFunc)
    {
        this.onSelectFunc = onSelectFunc;
    }

    public void release()
    {
        super.release();
        format = null;
        srcFormat = null;
        maxValue = null;
        minValue = null;
        defaultNull = null;
        _defaultNull = false;
        onSelectFunc = null;
        submitFormat = null;
        allowNull = "true";
        _allowNull = true;
        readOnly = "false";
        allowInput = "true";
        width = null;
        
        addMm = null;
        addDd = null;
        addSs = null;
    }

    public String getSubmitFormat()
    {
        return submitFormat;
    }

    public void setSubmitFormat(String submitFormat)
    {
        this.submitFormat = submitFormat;
    }

    public String getAllowInput()
    {
        return allowInput;
    }

    public void setAllowInput(String allowInput)
    {
        this.allowInput = allowInput;
    }

    public String getReadOnly()
    {
        return readOnly;
    }

    public void setReadOnly(String readOnly)
    {
        this.readOnly = readOnly;
    }

	public String getInDatacell() {
		return inDatacell;
	}

	public void setInDatacell(String inDatacell) {
		this.inDatacell = inDatacell;
	}

	public String getAddMm() {
		return addMm;
	}

	public void setAddMm(String addMm) {
		this.addMm = addMm;
	}

	public String getAddDd() {
		return addDd;
	}

	public void setAddDd(String addDd) {
		this.addDd = addDd;
	}

	public String getAddSs() {
		return addSs;
	}

	public void setAddSs(String addSs) {
		this.addSs = addSs;
	}
}
