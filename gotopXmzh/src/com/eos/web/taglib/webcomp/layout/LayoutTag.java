// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   LayoutTag.java

package com.eos.web.taglib.webcomp.layout;

import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.ResponseUtil;
import javax.servlet.jsp.JspException;

public class LayoutTag extends BaseTagSupport
{

    protected String type;
    protected String width;
    protected String height;
    protected boolean isHorizontal;

    public LayoutTag()
    {
        type = "horizontal";
        width = null;
        height = null;
        isHorizontal = true;
    }

    public boolean isHorizontal()
    {
        return isHorizontal;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int doEndTag()
        throws JspException
    {
        if(isHorizontal())
            ResponseUtil.write(pageContext, "</tr>");
        ResponseUtil.write(pageContext, "</table>");
        return super.doEndTag();
    }

    public void release()
    {
        type = "horizontal";
        width = null;
        height = null;
        super.release();
    }

    public int doStartTag()
        throws JspException
    {
        if(type.equals("horizontal"))
            isHorizontal = true;
        else
            isHorizontal = false;
        StringBuilder outStr = new StringBuilder("<table cellpadding=\"0\" cellSpacing=\"0\"  class=\"eos_layout_class\"");
        if(width != null)
            outStr.append((new StringBuilder()).append(" width=\"").append(width).append("\"").toString());
        if(height != null)
            outStr.append((new StringBuilder()).append(" height=\"").append(height).append("\"").toString());
        outStr.append(">");
        if(isHorizontal())
            outStr.append("<tr  layout=\"1\">");
        ResponseUtil.write(pageContext, outStr.toString());
        return 1;
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
}
