// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   AjaxBaseTag.java

package com.eos.web.taglib.richweb;

import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.XpathUtil;
import javax.servlet.jsp.JspException;

public class AjaxBaseTag extends BaseTagSupport
{

    private static final long serialVersionUID = 0x7d2a38bee6830726L;
    private String queryAction;
    private String xpath;

    public AjaxBaseTag()
    {
        queryAction = null;
        xpath = null;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        queryAction = XpathUtil.getStringByXpathSupport(getRootObj(), queryAction);
        xpath = XpathUtil.getStringByXpathSupport(getRootObj(), xpath);
    }

    public void release()
    {
        super.release();
        queryAction = null;
        xpath = null;
    }

    public String getQueryAction()
    {
        return queryAction;
    }

    public void setQueryAction(String queryAction)
    {
        this.queryAction = queryAction;
    }

    public String getXpath()
    {
        return xpath;
    }

    public void setXpath(String xpath)
    {
        this.xpath = xpath;
    }
}
