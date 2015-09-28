// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   RadioTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseInputTag

public class RadioTag extends BaseInputTag
{

    private String checked;

    public RadioTag()
    {
        checked = null;
    }

    public String getChecked()
    {
        return checked;
    }

    public void setChecked(String checked)
    {
        this.checked = checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = String.valueOf(checked);
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        checked = XpathUtil.getStringByXpathSupport(getRootObj(), checked);
    }

    protected void prepareValue()
    {
        if(checked != null && checked.equalsIgnoreCase("true"))
            component.setAttribute("checked", "true");
        String tmpValue = getValue();
        if(tmpValue != null)
        {
            if(getPropertyValue() != null && tmpValue.equals(getPropertyValue()))
                component.setAttribute("checked", "true");
            component.setValue(tmpValue);
        } else
        if(getPropertyValue() != null)
            component.setValue(getPropertyValue());
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("radio", getModelField());
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
        checked = null;
    }
}
