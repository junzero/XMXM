// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   FlowActionTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseInputTag

public class FlowActionTag extends BaseInputTag
{

    private String type;
    private String text;

    public FlowActionTag()
    {
        type = "hidden";
        text = "";
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        type = XpathUtil.getStringByXpathSupport(getRootObj(), type);
        text = XpathUtil.getStringByXpathSupport(getRootObj(), text);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("flowAction", getModelField());
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 1;
    }

    protected void prepareAttributes()
    {
        super.prepareAttributes();
        component.setAttribute("type", getType());
        component.setAttribute("text", getText());
    }

    public void release()
    {
        super.release();
        String type = "hidden";
        String text = "";
    }
}
