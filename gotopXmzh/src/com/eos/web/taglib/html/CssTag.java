// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   CssTag.java

package com.eos.web.taglib.html;

import com.eos.web.exception.EOSTagExceptionUtil;
import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.*;
import javax.servlet.jsp.JspException;

public class CssTag extends BaseTagSupport
{

    private String href;
    private String inContext;
    private boolean _inContext;
    private String locale;
    private String charset;
    private String i18n;
    public boolean _i18n;

    public CssTag()
    {
        href = null;
        inContext = "true";
        _inContext = true;
        locale = null;
        charset = null;
        i18n = null;
        _i18n = false;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
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
        _inContext = XpathUtil.getBooleanByXpathSupport(getRootObj(), inContext, true);
        href = XpathUtil.getStringByXpathSupport(getRootObj(), href);
        locale = XpathUtil.getStringByXpathSupport(getRootObj(), locale, null);
        charset = XpathUtil.getStringByXpathSupport(getRootObj(), charset);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        StringBuilder sb = new StringBuilder();
        if(href == null)
        {
            EOSTagExceptionUtil.throwEOSTagException("17030021", "h:css tag, href attribute does not alow null!");
        } else
        {
            sb.append("<link href=\"");
            if(_inContext)
                if(_i18n)
                    href = (new I18nUtil()).getFile(href, pageContext, getLocale());
                else
                if(href.charAt(0) == '/')
                    href = (new StringBuilder()).append(RequestUtil.getContextPath(pageContext)).append(href).toString();
            sb.append(href);
            sb.append("\" ");
            if(charset != null)
                sb.append("charset=\"").append(charset).append("\" ");
            sb.append("type=\"text/css\" rel=\"stylesheet\" >");
        }
        ResponseUtil.write(pageContext, sb.toString());
        return 6;
    }

    public void release()
    {
        super.release();
        href = null;
        locale = null;
        inContext = "true";
        _inContext = true;
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
