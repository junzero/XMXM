// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ScriptTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.*;
import javax.servlet.jsp.JspException;

public class ScriptTag extends BaseTagSupport
{

    private String src;
    private String language;
    private String inContext;
    private boolean _inContext;
    private String locale;
    private String charset;
    private String i18n;
    public boolean _i18n;

    public ScriptTag()
    {
        src = null;
        language = "javascript";
        inContext = "true";
        _inContext = true;
        locale = null;
        charset = null;
        i18n = null;
        _i18n = false;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getSrc()
    {
        return src;
    }

    public void setSrc(String src)
    {
        this.src = src;
    }

    public String getI18n()
    {
        return i18n;
    }

    public void setI18n(String i18n)
    {
        this.i18n = i18n;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _i18n = XpathUtil.getBooleanByXpathSupport(getRootObj(), i18n, false);
        src = XpathUtil.getStringByXpathSupport(getRootObj(), src);
        language = XpathUtil.getStringByXpathSupport(getRootObj(), language);
        locale = XpathUtil.getStringByXpathSupport(getRootObj(), locale, null);
        _inContext = XpathUtil.getBooleanByXpathSupport(getRootObj(), inContext, true);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        StringBuilder sb = new StringBuilder();
        if(language == null)
            language = "javascript";
        sb.append("<script  type=\"text/");
        sb.append((new StringBuilder()).append(language).append("\" ").toString());
        if(src != null)
        {
            if(_inContext)
                if(_i18n)
                    src = (new I18nUtil()).getFile(src, pageContext, getLocale());
                else
                if(src.charAt(0) == '/')
                    src = (new StringBuilder()).append(RequestUtil.getContextPath(pageContext)).append(src).toString();
            sb.append((new StringBuilder()).append(" src=\"").append(src).append("\" ").toString());
        }
        if(charset != null)
            sb.append("charset=\"").append(charset).append("\" ");
        sb.append(">");
        ResponseUtil.write(pageContext, sb.toString());
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        ResponseUtil.write(pageContext, "</script>");
        release();
        return 6;
    }

    public void release()
    {
        super.release();
        src = null;
        language = "javascript";
        inContext = "true";
        _inContext = true;
        locale = null;
        charset = null;
        i18n = null;
        _i18n = false;
    }

    public String getInContext()
    {
        return inContext;
    }

    public void setInContext(String context)
    {
        inContext = context;
    }

    public boolean is_inContext()
    {
        return _inContext;
    }

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }
}
