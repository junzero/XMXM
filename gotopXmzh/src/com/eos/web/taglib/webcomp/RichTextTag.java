// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   RichTextTag.java

package com.eos.web.taglib.webcomp;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.webcomp:
//            BaseComponentTag

public class RichTextTag extends BaseComponentTag
{

    private String width;
    private String height;
    private String toolBar;

    public RichTextTag()
    {
        width = null;
        height = null;
        toolBar = null;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        width = XpathUtil.getStringByXpathSupport(getRootObj(), width);
        height = XpathUtil.getStringByXpathSupport(getRootObj(), height);
        toolBar = XpathUtil.getStringByXpathSupport(getRootObj(), toolBar);
    }

    protected void prepareAttributes()
    {
        super.prepareAttributes();
        component.setAttribute("width", width);
        component.setAttribute("height", height);
        component.setAttribute("toolBar", toolBar);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("richtext", getModelField());
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
        width = null;
        height = null;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public String getToolBar()
    {
        return toolBar;
    }

    public void setToolBar(String toolBar)
    {
        this.toolBar = toolBar;
    }
}
