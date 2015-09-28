// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   WebFlowTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.util.ResponseUtil;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class WebFlowTag extends TagSupport
{

    public WebFlowTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        StringBuilder buffer = new StringBuilder();
        Object _eosFlowKey = pageContext.getRequest().getAttribute("_eosFlowKey");
        if(_eosFlowKey != null)
            buffer.append("<input type='hidden' name='").append("_eosFlowKey").append("' value='").append(_eosFlowKey).append("'/>");
        Object _eosFlowDataContext = pageContext.getRequest().getAttribute("_eosFlowDataContext");
        if(_eosFlowDataContext != null)
            buffer.append("<input type='hidden' name='").append("_eosFlowDataContext").append("' value='").append(_eosFlowDataContext).append("'/>");
        ResponseUtil.write(pageContext, buffer.toString());
        return 0;
    }
}
