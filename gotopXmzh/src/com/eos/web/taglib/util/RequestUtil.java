// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   RequestUtil.java

package com.eos.web.taglib.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class RequestUtil
{

    public RequestUtil()
    {
    }

    public static String getContextPath(PageContext pageContext)
    {
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        return request.getContextPath();
    }

    public static String builderURL(PageContext pageContext, String url, boolean addContextPath)
    {
        if(url == null)
            return null;
        if(!addContextPath)
            return url;
        if(url.startsWith("/"))
        {
            String contextPath = getContextPath(pageContext);
            return (new StringBuilder()).append(contextPath).append(url).toString();
        } else
        {
            return url;
        }
    }
}
