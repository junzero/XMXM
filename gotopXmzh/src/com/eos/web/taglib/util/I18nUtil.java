// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   I18nUtil.java

package com.eos.web.taglib.util;

import com.eos.data.datacontext.DataContextManager;
import com.eos.web.Constants;
import com.gotop.util.security.ForUtil;

import java.io.File;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

// Referenced classes of package com.eos.web.taglib.util:
//            RequestUtil

public class I18nUtil
{

    public I18nUtil()
    {
    }

    public String getFile(String src, PageContext pageContext, String defaultLanguage)
    {
        if(src.indexOf("://") != -1)
            return src;
        String path = pageContext.getServletContext().getRealPath("/");
        String language = getLanguage(getLocale(defaultLanguage, pageContext).toString());
        StringBuilder file = new StringBuilder("");
        if(src.startsWith("/"))
        {
            file.append(src.substring(0, src.lastIndexOf("/"))).append("/").append(language).append(src.substring(src.lastIndexOf("/")));
            File f = ForUtil.createFile((new StringBuilder()).append(path).append(file).toString());
            if(f.exists())
                return (new StringBuilder()).append(RequestUtil.getContextPath(pageContext)).append(file.toString()).toString();
            else
                return (new StringBuilder()).append(RequestUtil.getContextPath(pageContext)).append(src).toString();
        }
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        String requestURI = request.getServletPath();
        int index = requestURI.lastIndexOf('/');
        String requestPath = requestURI.substring(0, index);
        File f;
        if(src.indexOf('/') == -1)
        {
            file.append(language).append(src);
            f = ForUtil.createFile((new StringBuilder()).append(path).append(requestPath).append("/").append(file).toString());
            if(f.exists())
                return file.toString();
            else
                return src;
        }
        file.append(src.substring(0, src.lastIndexOf("/"))).append("/").append(language).append(src.substring(src.lastIndexOf("/")));
        f = ForUtil.createFile((new StringBuilder()).append(path).append(requestPath).append("/").append(file).toString());
        if(f.exists())
            return file.toString();
        else
            return src;
    }

    public Locale getLocale(String locale, PageContext pageContext)
    {
        if(locale != null)
            try
            {
                return getLocale(locale);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        Locale defaultLocale = DataContextManager.current().getCurrentLocale();
        if(defaultLocale == null)
            defaultLocale = request.getLocale();
        return defaultLocale;
    }

    private String getLanguage(String defaultLanguage)
    {
        String language = null;
        language = defaultLanguage != null ? defaultLanguage : Constants.LOCALE.toString();
        return language;
    }

    public static Locale getLocale(String locale)
        throws Exception
    {
        String v[] = locale.split("_");
        if(v.length > 3)
            throw new Exception();
        if(v.length == 1)
            return new Locale(v[0]);
        if(v.length == 2)
            return new Locale(v[0], v[1]);
        else
            return new Locale(v[0], v[1], v[2]);
    }
}
