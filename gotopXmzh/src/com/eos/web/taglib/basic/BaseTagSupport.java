// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BaseTagSupport.java

package com.eos.web.taglib.basic;

import com.eos.web.Constants;
import com.eos.web.taglib.util.XpathUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public abstract class BaseTagSupport extends BodyTagSupport
{

    private String scope;
    private Object rootObj;

    public BaseTagSupport()
    {
        scope = null;
        rootObj = null;
    }

    public void initAttributes()
        throws JspException
    {
        if(!Constants.hasImprimatur)
            try
            {
                String contextPath = ((HttpServletRequest)pageContext.getRequest()).getContextPath();
                ((HttpServletResponse)pageContext.getResponse()).sendRedirect((new StringBuilder()).append(contextPath).append("/common/noImprimatur.jsp?module=RichWeb").toString());
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        if(scope == null)
            scope = (String)pageContext.getAttribute("scope");
        if(scope == null)
            scope = "request";
        rootObj = XpathUtil.getDataContextRoot(scope, pageContext);
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }

    public Object getRootObj()
    {
        return rootObj;
    }

    public void setRootObj(Object rootObj)
    {
        this.rootObj = rootObj;
    }

    public int doEndTag()
        throws JspException
    {
        release();
        return 0;
    }

    public void release()
    {
        super.release();
        rootObj = null;
        scope = null;
    }
}
