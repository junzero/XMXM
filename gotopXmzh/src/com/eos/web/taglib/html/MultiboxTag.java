// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   MultiboxTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseInputTag

public class MultiboxTag extends BaseInputTag
{

    private String mutiValue;

    public MultiboxTag()
    {
        mutiValue = null;
    }

    public void setMutiValue(String mutiValue)
    {
        this.mutiValue = mutiValue;
    }

    public String getMutiValue()
    {
        return mutiValue;
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("multibox", getModelField());
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 1;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        mutiValue = XpathUtil.getStringByXpathSupport(getRootObj(), mutiValue);
    }

    public void release()
    {
        super.release();
        mutiValue = null;
    }

    protected void prepareAttributes()
    {
        super.prepareAttributes();
        if(getPropertyValue() != null)
            setValue((String)getPropertyValue());
        if(mutiValue != null)
            component.setAttribute("mutiValue", mutiValue);
    }
}
