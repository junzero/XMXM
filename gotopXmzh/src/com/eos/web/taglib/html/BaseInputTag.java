// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BaseInputTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseHandlerTag

public class BaseInputTag extends BaseHandlerTag
{

    private String modelField;
    private String size;
    private String maxlength;
    private String validateAttr;
    private String extAttr;

    public BaseInputTag()
    {
        modelField = null;
        size = null;
        maxlength = null;
        validateAttr = null;
        extAttr = null;
    }

    public String getExtAttr()
    {
        return extAttr;
    }

    public void setExtAttr(String extAttr)
    {
        this.extAttr = extAttr;
    }

    public String getMaxlength()
    {
        return maxlength;
    }

    public void setMaxlength(String maxlength)
    {
        this.maxlength = maxlength;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    protected void prepareFocusEvents()
    {
        super.prepareFocusEvents();
    }

    protected void prepareExtAttributes()
    {
        if(getValidateAttr() != null)
            component.setAttribute("validateAttr", getValidateAttr());
        if(getExtAttr() != null)
            component.setAttributesText(getExtAttr().replace(';', ' '));
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
    {
        super.prepareAttributes();
        prepareValue();
        if(component.getName() == null)
            component.setName(getProperty());
        if(getSize() != null)
            component.setAttribute("size", getSize());
        if(getMaxlength() != null)
            component.setAttribute("maxlength", getMaxlength());
        prepareExtAttributes();
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        size = XpathUtil.getStringByXpathSupport(getRootObj(), size);
        maxlength = XpathUtil.getStringByXpathSupport(getRootObj(), maxlength);
        extAttr = XpathUtil.getStringByXpathSupport(getRootObj(), extAttr);
        validateAttr = XpathUtil.getStringByXpathSupport(getRootObj(), validateAttr);
        modelField = XpathUtil.getStringByXpathSupport(getRootObj(), modelField);
    }

    public void release()
    {
        super.release();
        size = null;
        maxlength = null;
        extAttr = null;
        validateAttr = null;
        modelField = null;
    }

    public String getValidateAttr()
    {
        return validateAttr;
    }

    public void setValidateAttr(String validateAttr)
    {
        this.validateAttr = validateAttr;
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
