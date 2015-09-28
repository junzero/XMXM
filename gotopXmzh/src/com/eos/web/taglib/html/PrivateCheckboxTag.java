// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   PrivateCheckboxTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseInputTag

public class PrivateCheckboxTag extends BaseInputTag
{

    private String checked;
    private String checkedValue;

    public PrivateCheckboxTag()
    {
        checked = null;
        checkedValue = null;
    }

    public String getCheckedValue()
    {
        return checkedValue;
    }

    public void setCheckedValue(String checkedValue)
    {
        this.checkedValue = checkedValue;
    }

    public String getChecked()
    {
        return checked;
    }

    public void setChecked(String checked)
    {
        this.checked = checked;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        checked = XpathUtil.getStringByXpathSupport(getRootObj(), checked);
        checkedValue = XpathUtil.getStringByXpathSupport(getRootObj(), checkedValue);
    }

    protected void prepareValue()
    {
        if(checked != null && checked.equalsIgnoreCase("true"))
            component.setAttribute("checked", "true");
        String tmpValue = getValue();
        if(getPropertyValue() != null)
            tmpValue = String.valueOf(getPropertyValue());
        if(tmpValue != null)
        {
            if((new StringBuilder()).append(",").append(getCheckedValue()).append(",").toString().indexOf((new StringBuilder()).append(",").append(tmpValue).append(",").toString()) != -1)
                component.setAttribute("checked", "true");
            component.setValue(tmpValue);
        }
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("checkbox", getModelField());
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
        checkedValue = null;
    }
}
